// C++23 Module - 八字分析引擎实现
module ZhouYi.BaZiAnalysis;

import nlohmann.json;
import magic_enum;
import ZhouYi.BaZiAnalysis.Fortune;
import ZhouYi.BaZiAnalysis.Presenter;
import ZhouYi.BaZiAnalysis.Relations;
import ZhouYi.GanZhi;
import std;

namespace ZhouYi::BaZiAnalysis {

using ZhouYi::GanZhi::get_cang_gan;
using ZhouYi::GanZhi::get_shi_er_chang_sheng;
using ZhouYi::GanZhi::get_shi_shen;
using ZhouYi::GanZhi::get_wu_xing;
using ZhouYi::GanZhi::is_chong;
using ZhouYi::GanZhi::is_hai;
using ZhouYi::GanZhi::is_he;
using ZhouYi::GanZhi::is_mu_ku;
using ZhouYi::GanZhi::is_xing;
using ZhouYi::GanZhi::shi_shen_to_zh;
using ZhouYi::GanZhi::ShiErChangSheng;
using ZhouYi::GanZhi::ShiShen;
using ZhouYi::GanZhi::wu_xing_ke;
using ZhouYi::GanZhi::wu_xing_sheng;
namespace Mapper = ZhouYi::GanZhi::Mapper;

namespace {

constexpr std::array<WuXing, 5> kElements = {
    WuXing::Mu, WuXing::Huo, WuXing::Tu, WuXing::Jin, WuXing::Shui};

std::size_t element_index(WuXing element) {
  return static_cast<std::size_t>(static_cast<int>(element) - 1);
}

std::string element_name(WuXing element) {
  return std::string(Mapper::to_zh(element));
}

std::string stem_name(TianGan stem) { return std::string(Mapper::to_zh(stem)); }

std::string branch_name(DiZhi branch) {
  return std::string(Mapper::to_zh(branch));
}

template <typename E> std::string enum_code(E value) {
  return std::string(magic_enum::enum_name(value));
}

template <typename E> std::string zh_text(E value) {
  return std::string(ZhouYi::Mapper::to_zh(value));
}

double clamp_score(double value) { return std::clamp(value, 0.0, 100.0); }

bool is_winter(DiZhi branch) {
  return branch == DiZhi::Hai || branch == DiZhi::Zi || branch == DiZhi::Chou;
}

bool is_summer(DiZhi branch) {
  return branch == DiZhi::Si || branch == DiZhi::Wu || branch == DiZhi::Wei;
}

double hidden_weight(std::size_t index, const AnalysisConfig &config) {
  if (index == 0)
    return config.main_hidden_stem_weight;
  if (index == 1)
    return config.middle_hidden_stem_weight;
  return config.residual_hidden_stem_weight;
}

double arch_weight(const BranchRelation &relation,
                   const AnalysisConfig &config) {
  if (relation.type != BranchRelationKind::Arching || !relation.virtual_branch)
    return 0.0;
  return relation.strength == RelationStrength::Arching
             ? config.adjacent_arch_weight
             : config.distant_arch_weight;
}

double untransformed_combine_penalty(const StemRelation &relation,
                                     const AnalysisConfig &config) {
  if (relation.effective)
    return 15.0;
  const int distance =
      std::abs(relation.first_position - relation.second_position);
  if (distance == 1)
    return config.adjacent_stem_combine_penalty;
  if (distance == 2)
    return config.distant_stem_combine_penalty;
  return config.distant_stem_combine_penalty * 0.5;
}

std::string position_name(std::size_t pillar, bool exposed,
                          std::size_t hidden_index = 0) {
  const std::string pillar_name =
      ZhouYi::BaZiBase::pillar_position_name(pillar);
  if (exposed)
    return pillar_name + "干";
  static constexpr std::array<std::string_view, 3> levels = {"本气", "中气",
                                                             "余气"};
  return pillar_name + "支" +
         std::string(levels[std::min<std::size_t>(hidden_index, 2)]);
}

KongWangResult calculate_kong_wang(const BaZi &chart,
                                   const AnalysisConfig &config) {
  KongWangResult result;
  const auto branches =
      ZhouYi::GanZhi::get_kong_wang(chart.day.gan, chart.day.zhi);
  result.branches = branches;
  result.root_multiplier = config.kong_wang_root_multiplier;
  const auto chart_branches = ZhouYi::BaZiBase::get_branches(chart);
  for (std::size_t position = 0; position < chart_branches.size(); ++position) {
    if (chart_branches[position] != result.branches[0] &&
        chart_branches[position] != result.branches[1])
      continue;
    result.affected_positions.push_back(static_cast<int>(position));
    result.evidence.push_back({"kong_wang.branch",
                               position_name(position, false), "旬空", 0.0,
                               branch_name(chart_branches[position]) +
                                   "落旬空，只折减该支承载的根气与十神"});
  }
  return result;
}

bool position_is_kong_wang(std::size_t position,
                           const KongWangResult &kong_wang) {
  return std::find(kong_wang.affected_positions.begin(),
                   kong_wang.affected_positions.end(),
                   static_cast<int>(position)) !=
         kong_wang.affected_positions.end();
}

} // namespace

BirthContext resolve_birth_context(const BirthContext &input) {
  BirthContext result = input;
  if (!input.has_exact_time) {
    result.human_command_reason = "未传入精确公历出生时刻，不判分日司令";
    return result;
  }
  const auto command = ZhouYi::BaZiBase::calculate_human_command(
      input.year, input.month, input.day, input.hour, input.minute,
      input.second);
  result.previous_jie = command.previous_jie;
  result.days_since_jie = command.days_since_jie;
  result.command_table_version = command.table_version;
  if (command.available)
    result.human_command = command.stem;
  result.human_command_reason = command.reason;
  return result;
}
namespace {
bool branch_is_clashed(DiZhi branch,
                       const std::vector<BranchRelation> &relations) {
  return std::any_of(
      relations.begin(), relations.end(), [branch](const auto &relation) {
        return relation.type == BranchRelationKind::Clash &&
               (relation.first == branch || relation.second == branch);
      });
}

std::optional<WuXing> climate_needed_element(DiZhi month_branch) {
  if (is_winter(month_branch))
    return WuXing::Huo;
  if (is_summer(month_branch))
    return WuXing::Shui;
  return std::nullopt;
}

std::vector<TenGodOccurrence> build_ten_god_occurrences(
    const BaZi &chart, const std::vector<BranchRelation> &relations,
    const KongWangResult &kong_wang, const AnalysisConfig &config) {
  std::vector<TenGodOccurrence> result;
  const auto stems = ZhouYi::BaZiBase::get_stems(chart);
  const auto branches = ZhouYi::BaZiBase::get_branches(chart);
  for (std::size_t position = 0; position < stems.size(); ++position) {
    if (position == 2)
      continue; // 日干是参照物，不重复算比肩
    result.push_back({stems[position],
                      get_shi_shen(chart.day.gan, stems[position]),
                      position_name(position, true), static_cast<int>(position),
                      true, HiddenStemLevel::None, config.stem_weight,
                      config.stem_weight, false, false});
  }
  for (std::size_t position = 0; position < branches.size(); ++position) {
    const auto hidden = get_cang_gan(branches[position]);
    const bool clashed = branch_is_clashed(branches[position], relations);
    const bool empty = position_is_kong_wang(position, kong_wang);
    for (std::size_t index = 0; index < hidden.size(); ++index) {
      const double raw = hidden_weight(index, config) *
                         (position == 1 ? config.month_branch_multiplier : 1.0);
      double effective = raw;
      if (clashed)
        effective *= config.clashed_root_multiplier;
      if (empty)
        effective *= config.kong_wang_root_multiplier;
      result.push_back({hidden[index],
                        get_shi_shen(chart.day.gan, hidden[index]),
                        position_name(position, false, index),
                        static_cast<int>(position), false,
                        index == 0 ? HiddenStemLevel::MainQi
                                   : (index == 1 ? HiddenStemLevel::MiddleQi
                                                 : HiddenStemLevel::ResidualQi),
                        raw, effective, empty, clashed});
    }
  }
  return result;
}

std::vector<const TenGodOccurrence *>
occurrences_of(const std::vector<TenGodOccurrence> &occurrences,
               std::initializer_list<ShiShen> gods, double minimum = 0.30) {
  std::vector<const TenGodOccurrence *> result;
  for (const auto &occurrence : occurrences) {
    if (occurrence.effective_power < minimum)
      continue;
    if (std::find(gods.begin(), gods.end(), occurrence.ten_god) != gods.end())
      result.push_back(&occurrence);
  }
  return result;
}

double
occurrence_power(const std::vector<const TenGodOccurrence *> &occurrences) {
  return std::accumulate(occurrences.begin(), occurrences.end(), 0.0,
                         [](double total, const TenGodOccurrence *item) {
                           return total + item->effective_power;
                         });
}

bool can_form_ten_god_combo(const TenGodOccurrence &first,
                            const TenGodOccurrence &second) {
  if (!first.exposed && !second.exposed)
    return false;
  if (std::abs(first.pillar_position - second.pillar_position) > 2)
    return false;
  const auto hidden_is_effective = [](const TenGodOccurrence &item) {
    return item.exposed || item.effective_power >= 1.0 ||
           item.hidden_level == HiddenStemLevel::MainQi;
  };
  return first.effective_power >= 0.5 && second.effective_power >= 0.5 &&
         hidden_is_effective(first) && hidden_is_effective(second);
}

bool groups_form_combo(const std::vector<const TenGodOccurrence *> &first,
                       const std::vector<const TenGodOccurrence *> &second) {
  return std::any_of(first.begin(), first.end(), [&](const auto *left) {
    return std::any_of(second.begin(), second.end(), [&](const auto *right) {
      return can_form_ten_god_combo(*left, *right);
    });
  });
}

ClimateResult assess_climate(const BaZi &chart,
                             const std::vector<TenGodOccurrence> &occurrences,
                             const std::array<ElementStat, 5> &balance,
                             const AnalysisConfig &config) {
  ClimateResult result;
  const auto month = chart.month.zhi;
  result.urgency = ClimateUrgency::None;
  result.reason = "本规则未锁定单一调候天干";
  if (chart.day.gan == TianGan::Yi && month == DiZhi::Wei) {
    result.needed = true;
    result.element = WuXing::Shui;
    result.preferred_stems = {TianGan::Gui, TianGan::Ren};
    result.urgency = ClimateUrgency::Urgent;
    result.reason = "六月乙木先取癸水滋润，壬水次用";
  } else if (is_winter(month)) {
    result.needed = true;
    result.element = WuXing::Huo;
    result.preferred_stems = {TianGan::Bing, TianGan::Ding};
    result.urgency = ClimateUrgency::Urgent;
    result.reason = "冬月寒湿，以火暖局";
  } else if (is_summer(month)) {
    result.needed = true;
    result.element = WuXing::Shui;
    result.preferred_stems = {TianGan::Ren, TianGan::Gui};
    result.urgency = ClimateUrgency::Urgent;
    result.reason = "夏月炎燥，以水润局";
  } else if (month == DiZhi::Yin || month == DiZhi::Mao) {
    result.needed = true;
    result.element = WuXing::Huo;
    result.preferred_stems = {TianGan::Bing};
    result.urgency = ClimateUrgency::Secondary;
    result.reason = "春月余寒，丙火为辅";
  }
  if (!result.needed)
    return result;

  for (const auto preferred : result.preferred_stems) {
    double stem_power = 0.0;
    bool found = false;
    for (const auto &occurrence : occurrences) {
      if (occurrence.stem != preferred)
        continue;
      found = true;
      stem_power += occurrence.effective_power;
      result.evidence.push_back(
          {"climate.exact_stem", occurrence.position, stem_name(preferred),
           occurrence.effective_power,
           occurrence.exposed ? "调候字精确透干"
                              : (occurrence.kong_wang || occurrence.clashed
                                     ? "调候字藏支且受冲或落空"
                                     : "调候字藏于地支")});
    }
    if (found)
      result.present_stems.push_back(preferred);
    result.usable_power += stem_power;
  }
  result.present = !result.present_stems.empty();
  const double same_element = balance[element_index(result.element)].percent;
  if (!result.present)
    result.state = ClimateState::Missing;
  else if (same_element > config.climate_excessive_percent &&
           result.usable_power >= config.climate_sufficient_power)
    result.state = ClimateState::Excessive;
  else if (result.usable_power < config.climate_sufficient_power)
    result.state = ClimateState::Insufficient;
  else
    result.state = ClimateState::Satisfied;
  const std::string state_reason =
      result.state == ClimateState::Missing        ? "调候字未见"
      : result.state == ClimateState::Insufficient ? "调候字已见但有效力量不足"
      : result.state == ClimateState::Satisfied
          ? "调候已备，不再重复补药"
          : "调候同类已过量，继续增加反加重偏性";
  result.evidence.push_back(
      {"climate.state", branch_name(month), enum_code(result.state),
       result.state == ClimateState::Missing ? 40.0 : 0.0, state_reason});
  return result;
}

std::vector<TenGodCombo>
detect_ten_god_combos(const std::vector<TenGodOccurrence> &occurrences,
                      const StrengthResult &strength) {
  const auto guan = occurrences_of(occurrences, {ShiShen::ZhengGuan});
  const auto sha = occurrences_of(occurrences, {ShiShen::QiSha});
  const auto shang = occurrences_of(occurrences, {ShiShen::ShangGuan});
  const auto food = occurrences_of(occurrences, {ShiShen::ShiShen});
  const auto xiao = occurrences_of(occurrences, {ShiShen::PianYin});
  const auto yin =
      occurrences_of(occurrences, {ShiShen::ZhengYin, ShiShen::PianYin});
  const auto wealth =
      occurrences_of(occurrences, {ShiShen::ZhengCai, ShiShen::PianCai});
  const auto peers =
      occurrences_of(occurrences, {ShiShen::BiJian, ShiShen::JieCai});
  std::vector<TenGodCombo> result;
  const auto add =
      [&](TenGodComboKind kind, ComboSeverity severity, std::string note,
          const std::vector<const TenGodOccurrence *> &evidence_items) {
        TenGodCombo combo{kind, severity, std::move(note), {}};
        for (const auto *item : evidence_items) {
          combo.evidence.push_back(
              {"ten_god.occurrence", item->position, stem_name(item->stem),
               item->effective_power,
               item->exposed ? "透干十神"
                             : zh_text(item->hidden_level) + "十神"});
        }
        result.push_back(std::move(combo));
      };
  if (!guan.empty() && !sha.empty())
    add(TenGodComboKind::MixedOfficerKiller, ComboSeverity::Warning,
        "官杀均有效，需检查去留与制化", {guan.front(), sha.front()});
  if (groups_form_combo(shang, guan)) {
    const bool rescued =
        groups_form_combo(shang, yin) || groups_form_combo(shang, wealth);
    add(TenGodComboKind::HurtingOfficerMeetsOfficer,
        rescued ? ComboSeverity::Resolved : ComboSeverity::Warning,
        rescued ? "有效印或财参与通关" : "未见有效通关",
        {shang.front(), guan.front()});
  }
  if (groups_form_combo(xiao, food)) {
    const bool rescued = groups_form_combo(wealth, xiao);
    add(TenGodComboKind::OwlSeizesFood,
        rescued ? ComboSeverity::Resolved : ComboSeverity::Warning,
        rescued ? "有效财星参与制枭" : "枭食有效作用且无财制",
        {xiao.front(), food.front()});
  }
  if (groups_form_combo(sha, yin))
    add(TenGodComboKind::KillerSealGenerate, ComboSeverity::Favorable,
        "杀印均有效且可相作用", {sha.front(), yin.front()});
  if (groups_form_combo(food, sha))
    add(TenGodComboKind::FoodControlsKiller, ComboSeverity::Favorable,
        "食神与七杀均有效", {food.front(), sha.front()});
  if (groups_form_combo(shang, yin))
    add(TenGodComboKind::HurtingOfficerWithSeal, ComboSeverity::Favorable,
        "伤官与印星均有效", {shang.front(), yin.front()});

  const double peer_power = occurrence_power(peers);
  const double wealth_power = occurrence_power(wealth);
  const double officer_power = occurrence_power(guan) + occurrence_power(sha);
  if (peer_power >= 1.0 && wealth_power >= 0.6) {
    const bool medicine_sufficient = officer_power >= peer_power * 0.90;
    add(TenGodComboKind::PeersSeizeWealth,
        medicine_sufficient ? ComboSeverity::Resolved : ComboSeverity::Warning,
        medicine_sufficient
            ? "见官杀制比护财，药力与比劫之病相当"
            : (officer_power > 0.0
                   ? "官杀虽见，但有效力量不及比劫，属于有药而药轻"
                   : "比劫有力而未见有效官杀制比"),
        {peers.front(), wealth.front()});
  }
  if (wealth_power >= 2.2) {
    add(TenGodComboKind::WealthExcess,
        strength.score < 45.0 ? ComboSeverity::Warning
                              : ComboSeverity::Resolved,
        strength.score < 45.0 ? "财星力量较高且日主偏弱"
                              : "财星力量较高，但日主未落入身弱阈值",
        wealth);
  }
  return result;
}

std::array<ElementStat, 5>
calculate_balance(const BaZi &chart, const AnalysisConfig &config,
                  const std::vector<BranchRelation> &branch_relations,
                  const std::vector<StemRelation> &stem_relations) {
  std::array<ElementStat, 5> balance{};
  for (const auto element : kElements) {
    balance[element_index(element)].element = element;
  }

  const auto stems = ZhouYi::BaZiBase::get_stems(chart);
  const auto branches = ZhouYi::BaZiBase::get_branches(chart);
  for (std::size_t i = 0; i < stems.size(); ++i) {
    const auto element = get_wu_xing(stems[i]);
    auto &stat = balance[element_index(element)];
    stat.raw += config.stem_weight;
    stat.evidence.push_back({"element.stem", stem_name(stems[i]), "天干",
                             config.stem_weight, "天干按配置权重计入五行力量"});
  }

  for (std::size_t position = 0; position < branches.size(); ++position) {
    const auto hidden = get_cang_gan(branches[position]);
    const double month_multiplier =
        position == 1 ? config.month_branch_multiplier : 1.0;
    for (std::size_t index = 0; index < hidden.size(); ++index) {
      const double contribution =
          hidden_weight(index, config) * month_multiplier;
      const auto element = get_wu_xing(hidden[index]);
      auto &stat = balance[element_index(element)];
      stat.raw += contribution;
      stat.evidence.push_back(
          {"element.hidden_stem",
           branch_name(branches[position]) + stem_name(hidden[index]),
           index == 0 ? "本气" : (index == 1 ? "中气" : "余气"), contribution,
           position == 1 ? "月令藏干已乘季节系数" : "地支藏干按层级权重计入"});
    }
  }

  // 虚拱只按很小的结构权重计入中神本气，不能把虚支当成实支满额计根。
  for (const auto &relation : branch_relations) {
    if (!relation.virtual_branch)
      continue;
    double contribution = arch_weight(relation, config);
    const bool disrupted =
        branch_is_clashed(relation.first, branch_relations) ||
        branch_is_clashed(relation.second, branch_relations);
    if (disrupted)
      contribution *= config.clashed_root_multiplier;
    const auto hidden = get_cang_gan(*relation.virtual_branch);
    if (hidden.empty() || contribution <= 0.0)
      continue;
    const auto element = get_wu_xing(hidden.front());
    auto &stat = balance[element_index(element)];
    stat.raw += contribution;
    stat.evidence.push_back(
        {"element.arching",
         branch_name(relation.first) + branch_name(relation.second),
         "虚拱" + branch_name(*relation.virtual_branch), contribution,
         disrupted ? "虚拱受冲，仅按折减后的象意力量计入"
                   : "虚拱只计中神本气的小幅结构增量，不等同实支"});
  }

  // 只有已通过月令、根气、争合和克破校验的五合，才从原五行迁移两干力量至化神。
  for (const auto &relation : stem_relations) {
    if (!relation.effective)
      continue;
    const auto transformed =
        ZhouYi::GanZhi::stem_combine_element(relation.first, relation.second);
    if (!transformed)
      continue;
    for (const auto stem : {relation.first, relation.second}) {
      const auto original = get_wu_xing(stem);
      balance[element_index(original)].raw = std::max(
          0.0, balance[element_index(original)].raw - config.stem_weight);
      balance[element_index(original)].evidence.push_back(
          {"element.stem_transform", stem_name(stem), "合化移出",
           -config.stem_weight, "已确认天干五合，力量按化神重计"});
      balance[element_index(*transformed)].raw += config.stem_weight;
      balance[element_index(*transformed)].evidence.push_back(
          {"element.stem_transform", stem_name(stem), "合化移入",
           config.stem_weight, "已确认天干五合，力量按化神重计"});
    }
  }

  double total = 0.0;
  for (const auto &stat : balance)
    total += stat.raw;
  for (auto &stat : balance)
    stat.percent = total == 0.0 ? 0.0 : stat.raw / total * 100.0;
  return balance;
}

struct TombOpeningResult {
  bool opened{};
  std::string cause{"none"}; // 冲 / 刑 / 冲刑；未开时为 none
};

TombOpeningResult
tomb_opening_result(DiZhi month_branch,
                    const std::vector<BranchRelation> &relations) {
  const auto involves_month = [&](const BranchRelation &relation) {
    return relation.first == month_branch || relation.second == month_branch ||
           std::find(relation.members.begin(), relation.members.end(),
                     month_branch) != relation.members.end();
  };
  const bool has_clash = std::any_of(
      relations.begin(), relations.end(), [&](const BranchRelation &relation) {
        return relation.type == BranchRelationKind::Clash &&
               involves_month(relation);
      });
  const bool has_punishment = std::any_of(
      relations.begin(), relations.end(), [&](const BranchRelation &relation) {
        return (relation.type == BranchRelationKind::Punishment ||
                relation.type == BranchRelationKind::SelfPunishment ||
                relation.type == BranchRelationKind::ThreePunishment) &&
               involves_month(relation);
      });
  if (has_clash && has_punishment)
    return {true, "冲刑"};
  if (has_clash)
    return {true, "冲"};
  if (has_punishment)
    return {true, "刑"};
  return {};
}

double month_command_score(TianGan day_stem, DiZhi month_branch,
                           const std::vector<BranchRelation> &relations,
                           StrengthResult &result) {
  const auto day_element = get_wu_xing(day_stem);
  const auto month_element = get_wu_xing(month_branch);
  if (is_mu_ku(day_stem, month_branch)) {
    result.month_tomb = true;
    const auto opening = tomb_opening_result(month_branch, relations);
    result.tomb_opened = opening.opened;
    result.tomb_opening_cause = opening.cause;
    const double score = opening.opened ? 60.0 : 35.0;
    result.evidence.push_back(
        {"strength.month_tomb", branch_name(month_branch),
         opening.opened ? "墓库已开" : "墓库未开", score,
         opening.opened
             ? "月令为日主墓库，由" + opening.cause + "开库，按半得令计"
             : "月令为日主墓库且未开，降低得令分"});
    return score;
  }
  if (day_element == month_element)
    return 85.0;
  if (wu_xing_sheng(month_element, day_element))
    return 80.0;
  if (wu_xing_sheng(day_element, month_element))
    return 35.0;
  if (wu_xing_ke(month_element, day_element))
    return 20.0;
  return 45.0;
}

double root_stability_score(const BaZi &chart, TianGan day_stem,
                            const std::vector<BranchRelation> &relations,
                            const KongWangResult &kong_wang,
                            const AnalysisConfig &config,
                            std::vector<Evidence> &evidence) {
  const auto day_element = get_wu_xing(day_stem);
  const auto branches = ZhouYi::BaZiBase::get_branches(chart);
  double raw = 0.0;
  for (std::size_t position = 0; position < branches.size(); ++position) {
    const auto hidden = get_cang_gan(branches[position]);
    const bool clashed = branch_is_clashed(branches[position], relations);
    const bool empty = position_is_kong_wang(position, kong_wang);
    for (std::size_t index = 0; index < hidden.size(); ++index) {
      const auto hidden_element = get_wu_xing(hidden[index]);
      const bool same = hidden_element == day_element;
      const bool resource = wu_xing_sheng(hidden_element, day_element);
      if (!same && !resource)
        continue;

      double contribution =
          hidden_weight(index, config) *
          (position == 1 ? config.month_branch_multiplier : 1.0);
      if (resource)
        contribution *= 0.75;
      if (clashed)
        contribution *= config.clashed_root_multiplier;
      if (empty)
        contribution *= config.kong_wang_root_multiplier;
      raw += contribution;
      evidence.push_back(
          {"capacity.root",
           branch_name(branches[position]) + stem_name(hidden[index]),
           same ? "同类根" : "印根", contribution,
           clashed && empty ? "根气所在支受冲且落空，双重折减"
           : clashed        ? "根气所在支受冲，按折减权重计入"
           : empty          ? "根气所在支落旬空，仅折减该支根气"
                            : "有效根气计入承载能力"});
    }
    const auto life_stage =
        get_shi_er_chang_sheng(day_stem, branches[position]);
    double life_adjustment = 0.0;
    std::string life_reason;
    if (life_stage == ShiErChangSheng::ChangSheng ||
        life_stage == ShiErChangSheng::LinGuan ||
        life_stage == ShiErChangSheng::DiWang) {
      life_adjustment = 0.25;
      life_reason = "十二长生旺相，增强得地";
    } else if (life_stage == ShiErChangSheng::Mu ||
               life_stage == ShiErChangSheng::Jue ||
               life_stage == ShiErChangSheng::Si) {
      life_adjustment = -0.15;
      life_reason = "十二长生墓绝死，降低得地";
    }
    if (life_adjustment != 0.0) {
      raw = std::max(0.0, raw + life_adjustment);
      evidence.push_back({"strength.twelve_life",
                          branch_name(branches[position]), "十二长生",
                          life_adjustment, std::move(life_reason)});
    }
  }
  for (const auto &relation : relations) {
    if (!relation.virtual_branch)
      continue;
    const auto hidden = get_cang_gan(*relation.virtual_branch);
    if (hidden.empty())
      continue;
    const auto virtual_element = get_wu_xing(hidden.front());
    const bool same = virtual_element == day_element;
    const bool resource = wu_xing_sheng(virtual_element, day_element);
    if (!same && !resource)
      continue;
    double contribution = arch_weight(relation, config);
    const bool disrupted = branch_is_clashed(relation.first, relations) ||
                           branch_is_clashed(relation.second, relations);
    if (resource)
      contribution *= 0.75;
    if (disrupted)
      contribution *= config.clashed_root_multiplier;
    raw += contribution;
    evidence.push_back(
        {"strength.arching",
         branch_name(relation.first) + branch_name(relation.second),
         "虚拱" + branch_name(*relation.virtual_branch), contribution,
         same ? "虚拱日主同类，仅按小幅暗助计入得地"
              : "虚拱印星，仅按小幅暗助计入得地"});
  }
  // 四支满额主根并考虑月令时约为 4.5；上限用于稳定映射到 0..100。
  return clamp_score(raw / 4.5 * 100.0);
}

double stem_support_score(const BaZi &chart, WuXing day_element,
                          std::vector<Evidence> &evidence) {
  double score = 50.0;
  const auto stems = ZhouYi::BaZiBase::get_stems(chart);
  for (std::size_t index = 0; index < stems.size(); ++index) {
    if (index == 2)
      continue;
    const auto element = get_wu_xing(stems[index]);
    double delta = 0.0;
    std::string relation;
    if (element == day_element || wu_xing_sheng(element, day_element)) {
      delta = 12.0;
      relation = "比印帮扶";
    } else if (wu_xing_sheng(day_element, element)) {
      delta = -8.0;
      relation = "泄身";
    } else if (wu_xing_ke(element, day_element)) {
      delta = -12.0;
      relation = "克身";
    } else {
      delta = -6.0;
      relation = "耗身";
    }
    score += delta;
    evidence.push_back({"strength.stem", stem_name(stems[index]), relation,
                        delta, "天干对日主的直接作用"});
  }
  return clamp_score(score);
}

StrengthResult assess_strength(const BaZi &chart,
                               const std::vector<BranchRelation> &relations,
                               const KongWangResult &kong_wang,
                               const AnalysisConfig &config) {
  const auto day_element = get_wu_xing(chart.day.gan);
  StrengthResult result;
  result.month_command =
      month_command_score(chart.day.gan, chart.month.zhi, relations, result);
  result.evidence.push_back(
      {"strength.month_command", branch_name(chart.month.zhi), "月令",
       result.month_command, "月令按日主五行的生、扶、泄、克关系归一化评分"});
  result.roots = root_stability_score(chart, chart.day.gan, relations,
                                      kong_wang, config, result.evidence);
  result.stem_support = stem_support_score(chart, day_element, result.evidence);
  result.season_adjustment = 60.0;
  if (is_winter(chart.month.zhi) && day_element == WuXing::Huo)
    result.season_adjustment = 30.0;
  if (is_summer(chart.month.zhi) && day_element == WuXing::Shui)
    result.season_adjustment = 30.0;
  const int clashes = static_cast<int>(std::count_if(
      relations.begin(), relations.end(), [](const auto &relation) {
        return relation.type == BranchRelationKind::Clash;
      }));
  result.relation_adjustment = clamp_score(60.0 - clashes * 12.0);
  result.evidence.push_back({"strength.season", branch_name(chart.month.zhi),
                             "季节", result.season_adjustment - 60.0,
                             "季节对日主可发挥程度的归一化修正"});
  result.evidence.push_back({"strength.relations", "四支", "冲合刑害",
                             result.relation_adjustment - 60.0,
                             "冲关系降低根气稳定性；合刑害仅保留结构证据"});
  result.score =
      clamp_score(0.40 * result.month_command + 0.30 * result.roots +
                  0.15 * result.stem_support + 0.10 * result.season_adjustment +
                  0.05 * result.relation_adjustment);
  if (result.score >= 75.0)
    result.level = StrengthLevel::ExtremelyStrong;
  else if (result.score >= 60.0)
    result.level = StrengthLevel::Strong;
  else if (result.score >= 40.0)
    result.level = StrengthLevel::Balanced;
  else if (result.score >= 25.0)
    result.level = StrengthLevel::Weak;
  else
    result.level = StrengthLevel::ExtremelyWeak;
  return result;
}

double climate_workability_score(WuXing day_element, DiZhi month_branch,
                                 std::vector<Evidence> &evidence) {
  double value = 60.0;
  if (is_winter(month_branch)) {
    if (day_element == WuXing::Huo)
      value = 30.0;
    else if (day_element == WuXing::Shui)
      value = 85.0;
    else if (day_element == WuXing::Mu)
      value = 45.0;
  } else if (is_summer(month_branch)) {
    if (day_element == WuXing::Shui)
      value = 30.0;
    else if (day_element == WuXing::Huo)
      value = 85.0;
    else if (day_element == WuXing::Jin)
      value = 45.0;
  }
  evidence.push_back({"capacity.climate", branch_name(month_branch), "寒暖燥湿",
                      value - 60.0, "按日主五行与季节环境评估可发挥程度"});
  return value;
}

WuXing generated_element(WuXing element) {
  for (const auto candidate : kElements) {
    if (wu_xing_sheng(element, candidate))
      return candidate;
  }
  return element;
}

WuXing resource_element(WuXing element) {
  for (const auto candidate : kElements) {
    if (wu_xing_sheng(candidate, element))
      return candidate;
  }
  return element;
}

WuXing controller_element(WuXing element) {
  for (const auto candidate : kElements) {
    if (wu_xing_ke(candidate, element))
      return candidate;
  }
  return element;
}

double circulation_score(WuXing day_element,
                         const std::array<ElementStat, 5> &balance,
                         std::vector<Evidence> &evidence) {
  const double support =
      balance[element_index(day_element)].percent +
      balance[element_index(resource_element(day_element))].percent;
  const double pressure =
      balance[element_index(controller_element(day_element))].percent +
      balance[element_index(generated_element(day_element))].percent * 0.5;
  const double score = clamp_score(50.0 + (support - 40.0) * 0.45 -
                                   std::max(0.0, pressure - 25.0) * 0.55);
  evidence.push_back({"capacity.circulation", element_name(day_element),
                      "生克流通", score - 50.0,
                      "比较印比支持与官杀、泄耗压力"});
  return score;
}

CarryingCapacity assess_capacity(const BaZi &chart,
                                 const StrengthResult &strength,
                                 const std::vector<BranchRelation> &relations,
                                 const KongWangResult &kong_wang,
                                 const std::array<ElementStat, 5> &balance,
                                 const AnalysisConfig &config) {
  CarryingCapacity result;
  std::vector<Evidence> root_evidence;
  result.root_stability = root_stability_score(
      chart, chart.day.gan, relations, kong_wang, config, root_evidence);
  std::vector<Evidence> climate_evidence;
  result.climate_workability = climate_workability_score(
      get_wu_xing(chart.day.gan), chart.month.zhi, climate_evidence);
  std::vector<Evidence> circulation_evidence;
  result.circulation = circulation_score(get_wu_xing(chart.day.gan), balance,
                                         circulation_evidence);
  result.overall = clamp_score(
      0.40 * strength.score + 0.30 * result.root_stability +
      0.20 * result.climate_workability + 0.10 * result.circulation);
  result.evidence.insert(result.evidence.end(), root_evidence.begin(),
                         root_evidence.end());
  result.evidence.insert(result.evidence.end(), climate_evidence.begin(),
                         climate_evidence.end());
  result.evidence.insert(result.evidence.end(), circulation_evidence.begin(),
                         circulation_evidence.end());
  for (const auto &item : result.evidence) {
    if (item.points < 0.0 || item.reason.find("受冲") != std::string::npos) {
      result.penalties.push_back(item);
    }
  }
  return result;
}

bool stem_is_present(TianGan candidate, const BaZi &chart) {
  const auto stems = ZhouYi::BaZiBase::get_stems(chart);
  return std::find(stems.begin(), stems.end(), candidate) != stems.end();
}

void add_effect(ShenCandidate &candidate, CandidateEffectType type,
                double score, bool requires_capacity, std::string reason) {
  candidate.effects.push_back(
      {type, score, requires_capacity, std::move(reason)});
  candidate.reasons.push_back({"useful_god.effect", stem_name(candidate.stem),
                               "候选作用", score,
                               candidate.effects.back().reason});
}

void apply_capacity(ShenCandidate &candidate,
                    const CarryingCapacity &capacity) {
  double direct = 0.0;
  double active = 0.0;
  for (const auto &effect : candidate.effects) {
    if (effect.requires_capacity)
      active += effect.score;
    else
      direct += effect.score;
  }
  const double factor = std::clamp(capacity.overall / 100.0, 0.20, 1.0);
  candidate.raw_score = direct + active;
  candidate.effective_score = direct + active * factor;
  if (active > 0.0 && factor < 1.0) {
    const double penalty = candidate.effective_score - candidate.raw_score;
    candidate.conflicts.push_back(
        {"useful_god.carrying_capacity", stem_name(candidate.stem), "承载不足",
         penalty,
         "日主承载能力 " + std::to_string(capacity.overall) +
             "/100，需要日主参与的作用已折减"});
  }
}

void sort_and_percent_candidates(std::vector<ShenCandidate> &candidates) {
  std::sort(candidates.begin(), candidates.end(),
            [](const auto &left, const auto &right) {
              if (left.effective_score != right.effective_score)
                return left.effective_score > right.effective_score;
              if (left.raw_score != right.raw_score)
                return left.raw_score > right.raw_score;
              return static_cast<int>(left.stem) < static_cast<int>(right.stem);
            });
  double total = 0.0;
  for (const auto &candidate : candidates)
    total += std::max(0.0, candidate.effective_score);
  for (auto &candidate : candidates) {
    candidate.percent =
        total == 0.0 ? 0.0
                     : std::max(0.0, candidate.effective_score) / total * 100.0;
  }
}

std::vector<ShenCandidate>
generate_candidates(const BaZi &chart, const StrengthResult &strength,
                    const CarryingCapacity &capacity,
                    const std::array<ElementStat, 5> &balance,
                    const PatternResult &pattern, const ClimateResult &climate,
                    const std::vector<TenGodCombo> &combos,
                    const std::vector<TenGodOccurrence> &occurrences,
                    const std::vector<StemRelation> &stem_relations,
                    const AnalysisConfig &config) {
  const auto day_element = get_wu_xing(chart.day.gan);
  const auto climate_need =
      climate.needed ? std::optional<WuXing>{climate.element} : std::nullopt;
  std::vector<ShenCandidate> candidates;
  candidates.reserve(10);

  for (int value = 0; value < 10; ++value) {
    const auto stem = static_cast<TianGan>(value);
    const auto element = get_wu_xing(stem);
    const bool exact_present = std::any_of(
        occurrences.begin(), occurrences.end(),
        [&](const auto &occurrence) { return occurrence.stem == stem; });
    ShenCandidate candidate{stem, element, "子平用神候选", 0.0,
                            0.0,  0.0,     exact_present};

    // 同一五行的不同天干不能永久同分：透干、月令藏根和余气根分别保留证据，
    // 但只在该候选本身已有正向作用时参与最终分数，避免把忌神“有根”误加成喜用。
    double stem_availability = 0.0;
    for (const auto &occurrence : occurrences) {
      if (occurrence.stem != stem)
        continue;
      if (occurrence.exposed) {
        const double points = occurrence.pillar_position == 1 ? 5.0 : 3.0;
        stem_availability += points;
        candidate.reasons.push_back(
            {"useful_god.stem_availability", stem_name(stem), "精确透干",
             points, occurrence.position + "透" + stem_name(stem)});
        continue;
      }
      const double level_points =
          occurrence.hidden_level == HiddenStemLevel::MainQi     ? 4.0
          : occurrence.hidden_level == HiddenStemLevel::MiddleQi ? 2.5
                                                                 : 1.0;
      const double points =
          level_points * std::min(1.0, occurrence.effective_power);
      stem_availability += points;
      candidate.reasons.push_back({"useful_god.stem_availability",
                                   occurrence.position + stem_name(stem),
                                   zh_text(occurrence.hidden_level) + "精确根",
                                   points,
                                   occurrence.kong_wang || occurrence.clashed
                                       ? "候选本干有根，但因冲或旬空已折减"
                                       : "候选本干的有效藏根"});
    }

    const bool preferred_for_climate =
        std::find(climate.preferred_stems.begin(),
                  climate.preferred_stems.end(),
                  stem) != climate.preferred_stems.end();
    if (climate_need && preferred_for_climate) {
      const bool warm = *climate_need == WuXing::Huo;
      double climate_score = 0.0;
      if (climate.state == ClimateState::Missing)
        climate_score = 40.0;
      else if (climate.state == ClimateState::Insufficient) {
        climate_score = std::clamp(
            25.0 * (1.0 - climate.usable_power /
                              std::max(0.01, config.climate_sufficient_power)),
            5.0, 25.0);
      } else if (climate.state == ClimateState::Excessive)
        climate_score = -12.0;
      const auto preferred_position =
          std::find(climate.preferred_stems.begin(),
                    climate.preferred_stems.end(), stem) -
          climate.preferred_stems.begin();
      if (climate_score > 0.0)
        climate_score -= static_cast<double>(preferred_position) * 2.0;
      if (climate_score != 0.0) {
        add_effect(candidate,
                   warm ? CandidateEffectType::ClimateWarmth
                        : CandidateEffectType::ClimateMoisture,
                   climate_score, false,
                   climate.state == ClimateState::Missing ? climate.reason
                   : climate.state == ClimateState::Insufficient
                       ? "调候字已见但力量不足，按不足程度补分"
                       : "调候同类已过量，继续增加扣分");
      } else {
        candidate.reasons.push_back({"climate.satisfied", stem_name(stem),
                                     "调候已备", 0.0,
                                     "原局调候已足，不再固定加40分"});
      }
    }
    if (element == day_element && strength.score < 60.0) {
      add_effect(candidate, CandidateEffectType::PeerSupport, 20.0, true,
                 "同类天干可助日主");
    }
    if (wu_xing_sheng(element, day_element) && strength.score < 60.0) {
      add_effect(candidate, CandidateEffectType::ResourceActivation, 18.0, true,
                 "印星生身，但须日主火力和环境能够承接");
    }
    if (wu_xing_sheng(day_element, element) && strength.score >= 60.0) {
      add_effect(candidate, CandidateEffectType::OutputDrain, 18.0, true,
                 "身强时可泄秀流通");
    }
    if (wu_xing_ke(day_element, element) && strength.score >= 65.0) {
      add_effect(candidate, CandidateEffectType::ControlWealth, 16.0, true,
                 "身强时可任财耗身");
    }

    const double existing_percent = balance[element_index(element)].percent;
    if (!climate_need || element != *climate_need) {
      if (existing_percent > 30.0) {
        const double penalty = -(existing_percent - 30.0) * 0.5;
        candidate.effects.push_back({CandidateEffectType::ResourceActivation,
                                     penalty, false, "原局该五行已偏多"});
        candidate.conflicts.push_back(
            {"useful_god.balance", stem_name(stem), "偏旺", penalty,
             "原局" + element_name(element) + "占比偏高，避免继续加重失衡"});
      }
    }
    if (wu_xing_ke(element, day_element) && strength.score < 40.0) {
      candidate.effects.push_back({CandidateEffectType::ResourceActivation,
                                   -12.0, false, "身弱不宜再受官杀克制"});
      candidate.conflicts.push_back({"useful_god.weak_day_master",
                                     stem_name(stem), "克身", -12.0,
                                     "日主偏弱，克身五行不作为优先候选"});
    }
    for (const auto &relation : stem_relations) {
      if (relation.first != stem && relation.second != stem)
        continue;
      const double penalty = untransformed_combine_penalty(relation, config);
      candidate.effects.push_back(
          {CandidateEffectType::PatternMedicine, -penalty, false,
           relation.effective
               ? "候选本干参与有效合化，原作用迁移"
               : "候选本干参与天干五合而未化，按柱位距离折减合绊"});
      candidate.conflicts.push_back(
          {"useful_god.original_combine", stem_name(stem),
           relation.effective ? "合化" : "合绊", -penalty,
           relation.effective
               ? "候选本干已随化神迁移"
               : "原局五合不化仍会牵制候选本干，隔位合轻于紧贴合"});
    }
    const bool has_positive_effect = std::any_of(
        candidate.effects.begin(), candidate.effects.end(),
        [](const CandidateEffect &effect) { return effect.score > 0.0; });
    if (has_positive_effect && stem_availability > 0.0) {
      candidate.effects.push_back({CandidateEffectType::ResourceActivation,
                                   stem_availability, false,
                                   "候选天干透干、藏根层级与月令位置修正"});
    }
    candidates.push_back(std::move(candidate));
  }

  // 子平法：月令取格为先。这里的加减分是格局“相神/破格”方向，
  // 不取代承载能力、调候和原局偏旺校验。
  const auto add_pattern_effect = [&](WuXing element, double score,
                                      std::string reason) {
    for (auto &candidate : candidates) {
      if (candidate.element != element)
        continue;
      candidate.effects.push_back(
          {CandidateEffectType::PatternMedicine, score, false, reason});
      candidate.reasons.push_back({"ziping.pattern", stem_name(candidate.stem),
                                   "格局相神", score, reason});
    }
  };
  const auto add_pattern_stem_effect = [&](TianGan stem, double score,
                                           std::string reason) {
    for (auto &candidate : candidates) {
      if (candidate.stem != stem)
        continue;
      candidate.effects.push_back(
          {CandidateEffectType::PatternMedicine, score, false, reason});
      candidate.reasons.push_back({"ziping.pattern_medicine", stem_name(stem),
                                   "格局病药", score, reason});
    }
  };
  const auto resource = resource_element(day_element);
  const auto output = generated_element(day_element);
  const auto wealth = generated_element(output);
  const auto officer = generated_element(wealth);
  if (pattern.name == PatternKind::ZhengGuan) {
    add_pattern_effect(wealth, 14.0, "正官格以财生官为相神方向");
    add_pattern_effect(resource, 8.0, "正官格可取印护官、生身");
    add_pattern_effect(output, -16.0, "伤食泄秀可能伤官，作为破格风险");
  } else if (pattern.name == PatternKind::QiSha) {
    add_pattern_effect(output, 15.0, "七杀格以食伤制杀为相神方向");
    add_pattern_effect(resource, 12.0, "七杀格可取印化杀、生身");
  } else if (pattern.name == PatternKind::ZhengCai ||
             pattern.name == PatternKind::PianCai) {
    add_pattern_effect(output, 14.0, "财格以食伤生财为相神方向");
    add_pattern_effect(officer, 8.0, "财格可取官杀护财");
    for (int value = 0; value < 10; ++value) {
      const auto stem = static_cast<TianGan>(value);
      if (get_shi_shen(chart.day.gan, stem) == ShiShen::ZhengGuan) {
        add_pattern_stem_effect(stem, 8.0,
                                "财格见正官，制比护财的病药方向优于再增印比");
      }
    }
    add_pattern_effect(day_element, -14.0, "财格比劫偏多有夺财风险");
  } else if (pattern.name == PatternKind::ZhengYin ||
             pattern.name == PatternKind::PianYin) {
    add_pattern_effect(officer, 12.0, "印格可取官杀生印");
    add_pattern_effect(wealth, -14.0, "财星克印，作为破格风险");
  } else if (pattern.name == PatternKind::ShiShen) {
    add_pattern_effect(wealth, 14.0, "食神格以食神生财为相神方向");
    add_pattern_effect(resource, -10.0, "偏印夺食风险须复核");
  } else if (pattern.name == PatternKind::ShangGuan) {
    add_pattern_effect(wealth, 14.0, "伤官格可取生财");
    add_pattern_effect(resource, 10.0, "伤官格可取配印");
    add_pattern_effect(officer, -16.0, "伤官见官为破格风险");
  } else if (pattern.pattern_type == PatternType::JianLu ||
             pattern.pattern_type == PatternType::YueJie) {
    add_pattern_effect(officer, 12.0,
                       zh_text(pattern.name) + "优先取官杀制比劫");
    add_pattern_effect(output, 10.0, zh_text(pattern.name) + "可取食伤泄秀");
    add_pattern_effect(wealth, 8.0, zh_text(pattern.name) + "可取财耗旺身");
  } else if (pattern.pattern_type == PatternType::YangRen) {
    add_pattern_effect(officer, 16.0, "羊刃格优先取官杀制刃");
    add_pattern_effect(output, 8.0, "羊刃有制时可兼取食伤泄秀");
  }
  // 病药不只作为输出标签：仅对已检测到的结构风险，给出对应的制化/通关候选方向。
  const auto has_combo = [&](TenGodComboKind kind, bool warning_only = true) {
    return std::any_of(
        combos.begin(), combos.end(), [&](const TenGodCombo &combo) {
          return combo.kind == kind &&
                 (!warning_only || combo.severity == ComboSeverity::Warning);
        });
  };
  if (has_combo(TenGodComboKind::HurtingOfficerMeetsOfficer)) {
    add_pattern_effect(resource, 14.0, "伤官见官，取印通关为病药方向");
    add_pattern_effect(wealth, 8.0, "伤官见官，可取财泄伤、生官为辅助方向");
  }
  if (has_combo(TenGodComboKind::OwlSeizesFood)) {
    add_pattern_effect(wealth, 14.0, "枭印夺食，取财制枭为病药方向");
  }
  if (has_combo(TenGodComboKind::PeersSeizeWealth, false)) {
    add_pattern_effect(officer, 14.0, "比劫夺财，取官杀制比为病药方向");
    if (pattern.name == PatternKind::ZhengCai ||
        pattern.name == PatternKind::PianCai) {
      add_pattern_effect(wealth, 10.0,
                         "官制比护财路线中，财星可生官、护官并归于财格本体");
    }
    if (climate.state == ClimateState::Satisfied ||
        climate.state == ClimateState::Excessive) {
      add_pattern_effect(resource, -16.0,
                         "调候已备而比劫夺财，再增印星会生比劫、加重财格之病");
    }
  }
  for (auto &candidate : candidates)
    apply_capacity(candidate, capacity);

  sort_and_percent_candidates(candidates);
  return candidates;
}

bool apply_confirmed_special_pattern(
    std::vector<ShenCandidate> &candidates,
    const std::vector<SpecialPatternResult> &special_patterns,
    std::vector<std::string> &warnings) {
  auto confirmed = std::find_if(
      special_patterns.begin(), special_patterns.end(), [](const auto &item) {
        return item.name == SpecialPatternKind::HuaQi &&
               item.status == SpecialPatternStatus::Confirmed &&
               item.element.has_value();
      });
  if (confirmed == special_patterns.end())
    confirmed = std::find_if(
        special_patterns.begin(), special_patterns.end(), [](const auto &item) {
          return item.status == SpecialPatternStatus::Confirmed &&
                 item.element.has_value();
        });
  if (confirmed == special_patterns.end())
    return false;
  const double priority =
      confirmed->name == SpecialPatternKind::HuaQi ? 80.0 : 60.0;
  for (auto &candidate : candidates) {
    if (candidate.element != *confirmed->element)
      continue;
    candidate.raw_score += priority;
    candidate.effective_score += priority;
    candidate.reasons.push_back(
        {"special_pattern.priority", stem_name(candidate.stem),
         zh_text(confirmed->name), priority,
         "已确认特殊格局，按其顺势/化气方向锁定取用优先级"});
  }
  sort_and_percent_candidates(candidates);
  warnings.push_back("已确认" + zh_text(confirmed->name) + "，用神候选按" +
                     element_name(*confirmed->element) + "方向锁定");
  return true;
}

PatternKind pattern_name(ShiShen god) {
  switch (god) {
  case ShiShen::ZhengGuan:
    return PatternKind::ZhengGuan;
  case ShiShen::QiSha:
    return PatternKind::QiSha;
  case ShiShen::ZhengYin:
    return PatternKind::ZhengYin;
  case ShiShen::PianYin:
    return PatternKind::PianYin;
  case ShiShen::ZhengCai:
    return PatternKind::ZhengCai;
  case ShiShen::PianCai:
    return PatternKind::PianCai;
  case ShiShen::ShiShen:
    return PatternKind::ShiShen;
  case ShiShen::ShangGuan:
    return PatternKind::ShangGuan;
  case ShiShen::BiJian:
    return PatternKind::JianLu;
  case ShiShen::JieCai:
    return PatternKind::YueJie;
  }
  return PatternKind::JianLu;
}

bool is_jian_lu(TianGan day_stem, DiZhi month_branch) {
  switch (day_stem) {
  case TianGan::Jia:
    return month_branch == DiZhi::Yin;
  case TianGan::Yi:
    return month_branch == DiZhi::Mao;
  case TianGan::Bing:
  case TianGan::Wu:
    return month_branch == DiZhi::Si;
  case TianGan::Ding:
  case TianGan::Ji:
    return month_branch == DiZhi::Wu;
  case TianGan::Geng:
    return month_branch == DiZhi::Shen;
  case TianGan::Xin:
    return month_branch == DiZhi::You;
  case TianGan::Ren:
    return month_branch == DiZhi::Hai;
  case TianGan::Gui:
    return month_branch == DiZhi::Zi;
  }
  return false;
}

bool is_yang_blade(TianGan day_stem, DiZhi month_branch) {
  switch (day_stem) {
  case TianGan::Jia:
    return month_branch == DiZhi::Mao;
  case TianGan::Bing:
  case TianGan::Wu:
    return month_branch == DiZhi::Wu;
  case TianGan::Geng:
    return month_branch == DiZhi::You;
  case TianGan::Ren:
    return month_branch == DiZhi::Zi;
  default:
    return false;
  }
}

PatternResult
determine_pattern(const BaZi &chart, const std::vector<TenGodCombo> &combos,
                  const std::vector<StemRelation> &stem_relations,
                  const std::vector<BranchRelation> &branch_relations,
                  const std::vector<TenGodOccurrence> &occurrences,
                  const BirthContext &birth_context,
                  const AnalysisConfig &config) {
  const auto month_hidden = get_cang_gan(chart.month.zhi);
  const auto stems = ZhouYi::BaZiBase::get_stems(chart);
  const auto branches = ZhouYi::BaZiBase::get_branches(chart);
  const auto is_transformed = [&](TianGan stem) {
    return std::any_of(stem_relations.begin(), stem_relations.end(),
                       [&](const StemRelation &relation) {
                         return relation.effective && (relation.first == stem ||
                                                       relation.second == stem);
                       });
  };
  const auto root_power = [&](TianGan stem) {
    return std::accumulate(
        occurrences.begin(), occurrences.end(), 0.0,
        [&](double power, const TenGodOccurrence &occurrence) {
          return power +
                 (occurrence.stem == stem ? occurrence.effective_power : 0.0);
        });
  };
  TianGan pattern_stem = month_hidden.front();
  bool main_transformed = is_transformed(month_hidden.front());
  std::vector<TianGan> exposed;
  for (const auto hidden : month_hidden) {
    if (hidden == chart.day.gan || is_transformed(hidden))
      continue;
    const auto god = get_shi_shen(chart.day.gan, hidden);
    if (god == ShiShen::BiJian || god == ShiShen::JieCai)
      continue;
    if (std::find(stems.begin(), stems.end(), hidden) != stems.end())
      exposed.push_back(hidden);
  }
  if (!exposed.empty()) {
    pattern_stem = *std::max_element(
        exposed.begin(), exposed.end(), [&](TianGan left, TianGan right) {
          return root_power(left) < root_power(right);
        });
  } else if (main_transformed) {
    const auto fallback =
        std::find_if(std::next(month_hidden.begin()), month_hidden.end(),
                     [&](TianGan hidden) { return !is_transformed(hidden); });
    if (fallback != month_hidden.end())
      pattern_stem = *fallback;
  }
  const auto month_god = get_shi_shen(chart.day.gan, pattern_stem);
  PatternResult result;
  if (is_yang_blade(chart.day.gan, chart.month.zhi)) {
    result.name = PatternKind::YangRen;
    result.pattern_type = PatternType::YangRen;
  } else if (is_jian_lu(chart.day.gan, chart.month.zhi)) {
    result.name = PatternKind::JianLu;
    result.pattern_type = PatternType::JianLu;
  } else if (get_shi_shen(chart.day.gan, month_hidden.front()) ==
             ShiShen::JieCai) {
    result.name = PatternKind::YueJie;
    result.pattern_type = PatternType::YueJie;
  } else {
    result.name = pattern_name(month_god);
    result.pattern_type = PatternType::Regular;
  }
  result.status = PatternStatus::Candidate;
  result.pattern_stem = pattern_stem;
  result.basis = pattern_stem == month_hidden.front()
                     ? PatternBasis::MonthMainQi
                     : PatternBasis::ExposedHidden;
  result.human_command = birth_context.human_command;
  for (const auto hidden : exposed) {
    if (hidden != pattern_stem)
      result.competing_patterns.push_back(
          pattern_name(get_shi_shen(chart.day.gan, hidden)));
  }
  result.evidence.push_back(
      {"pattern.month_command",
       branch_name(chart.month.zhi) + stem_name(pattern_stem), "月令取格", 0.0,
       main_transformed
           ? "月令本气已有效合化，改从未化藏干取格"
           : (exposed.size() > 1 ? "月令多个藏干同透，按透干与根气强弱取格"
                                 : (pattern_stem == month_hidden.front()
                                        ? "月令本气取格"
                                        : "月令藏干透出，优先以透干取格"))});
  result.evidence.push_back(
      {"pattern.human_command", branch_name(chart.month.zhi), "分日司令", 0.0,
       birth_context.human_command
           ? birth_context.human_command_reason
           : "无精确出生时刻，只能论月令本气取格，不称某星司令"});
  const auto has_combo = [&](TenGodComboKind kind) {
    return std::any_of(combos.begin(), combos.end(),
                       [&](const auto &combo) { return combo.kind == kind; });
  };
  const auto combo_is_warning = [&](TenGodComboKind kind) {
    return std::any_of(combos.begin(), combos.end(), [&](const auto &combo) {
      return combo.kind == kind && combo.severity == ComboSeverity::Warning;
    });
  };
  const auto has_god = [&](ShiShen god) {
    return std::any_of(occurrences.begin(), occurrences.end(),
                       [&](const auto &occurrence) {
                         return occurrence.ten_god == god &&
                                occurrence.effective_power >= 0.30;
                       });
  };
  const bool has_yin = has_god(ShiShen::ZhengYin) || has_god(ShiShen::PianYin);
  const bool has_cai = has_god(ShiShen::ZhengCai) || has_god(ShiShen::PianCai);
  const bool has_guan_sha =
      has_god(ShiShen::ZhengGuan) || has_god(ShiShen::QiSha);
  const bool has_shi_shang =
      has_god(ShiShen::ShiShen) || has_god(ShiShen::ShangGuan);
  const auto ten_god_power = [&](std::initializer_list<ShiShen> gods,
                                 bool apply_combine) {
    double power = 0.0;
    for (const auto &occurrence : occurrences) {
      if (std::find(gods.begin(), gods.end(), occurrence.ten_god) == gods.end())
        continue;
      double contribution = occurrence.effective_power;
      if (apply_combine && occurrence.exposed) {
        for (const auto &relation : stem_relations) {
          if (relation.first != occurrence.stem &&
              relation.second != occurrence.stem)
            continue;
          const int distance =
              std::abs(relation.first_position - relation.second_position);
          contribution *=
              relation.effective ? 0.35 : (distance == 1 ? 0.80 : 0.90);
        }
      }
      power += contribution;
    }
    for (const auto &relation : branch_relations) {
      if (!relation.virtual_branch)
        continue;
      const auto hidden = get_cang_gan(*relation.virtual_branch);
      if (hidden.empty())
        continue;
      const auto virtual_god = get_shi_shen(chart.day.gan, hidden.front());
      if (std::find(gods.begin(), gods.end(), virtual_god) != gods.end()) {
        power += arch_weight(relation, config);
      }
    }
    return power;
  };

  if (result.name == PatternKind::ZhengGuan) {
    result.supports.push_back("月令取正官为格");
    if (combo_is_warning(TenGodComboKind::HurtingOfficerMeetsOfficer))
      result.conflicts.push_back("伤官见官无印、财通关");
    else if (has_combo(TenGodComboKind::HurtingOfficerMeetsOfficer))
      result.supports.push_back("伤官见官而有印或财通关");
    if (combo_is_warning(TenGodComboKind::MixedOfficerKiller))
      result.conflicts.push_back("官杀混杂，未见去留或制化");
    if (has_yin)
      result.supports.push_back("命局见印，可护官生身");
    if (has_cai)
      result.supports.push_back("命局见财，可生官辅格");
  } else if (result.name == PatternKind::QiSha) {
    if (has_combo(TenGodComboKind::FoodControlsKiller))
      result.supports.push_back("食神制杀");
    if (has_combo(TenGodComboKind::KillerSealGenerate))
      result.supports.push_back("杀印相生");
    if (!has_combo(TenGodComboKind::FoodControlsKiller) &&
        !has_combo(TenGodComboKind::KillerSealGenerate))
      result.conflicts.push_back("七杀无制无化");
  } else if (result.name == PatternKind::ShiShen) {
    result.supports.push_back("月令取食神，以泄秀为格");
    if (combo_is_warning(TenGodComboKind::OwlSeizesFood))
      result.conflicts.push_back("枭神夺食而无财制枭");
    else if (has_combo(TenGodComboKind::OwlSeizesFood))
      result.supports.push_back("枭神见财制，有救应");
    if (has_cai)
      result.supports.push_back("食神生财");
  } else if (result.name == PatternKind::ShangGuan) {
    if (has_combo(TenGodComboKind::HurtingOfficerWithSeal))
      result.supports.push_back("伤官配印");
    if (has_cai)
      result.supports.push_back("伤官生财");
    if (combo_is_warning(TenGodComboKind::HurtingOfficerMeetsOfficer))
      result.conflicts.push_back("伤官见官而无通关");
    if (!has_yin && !has_cai)
      result.conflicts.push_back("伤官未见配印或生财成局");
  } else if (result.name == PatternKind::ZhengCai ||
             result.name == PatternKind::PianCai) {
    result.supports.push_back("月令本气或透干取财为格");
    result.disease_power =
        ten_god_power({ShiShen::BiJian, ShiShen::JieCai}, false);
    result.medicine_power =
        ten_god_power({ShiShen::ZhengGuan, ShiShen::QiSha}, true);
    if (result.disease_power >= 1.0) {
      if (result.medicine_power <= 0.0) {
        result.condition = PatternCondition::Broken;
        result.conflicts.push_back("比劫夺财而未见官杀制比");
      } else if (result.medicine_power >= result.disease_power * 0.90) {
        result.condition = PatternCondition::DiseasedRelieved;
        result.supports.push_back("比劫为病，官杀药力与病神相当，可制比护财");
      } else {
        result.condition = PatternCondition::MedicineWeak;
        result.supports.push_back("比劫夺财而见官杀为药");
        result.conflicts.push_back(
            "官杀虽见，但合绊、根空等折减后药力不及比劫，属于有药而药轻");
      }
    } else {
      result.condition = PatternCondition::Established;
    }
    if (combo_is_warning(TenGodComboKind::WealthExcess))
      result.conflicts.push_back("财多身弱，日主不任财");
    const double output_power =
        ten_god_power({ShiShen::ShiShen, ShiShen::ShangGuan}, false);
    if (output_power >= 1.0) {
      result.supports.push_back("食伤有力，可泄比生财");
    } else if (output_power > 0.0) {
      result.conflicts.push_back("食伤仅藏而力微，尚不足独任泄比生财");
    }
  } else if (result.name == PatternKind::ZhengYin ||
             result.name == PatternKind::PianYin) {
    result.supports.push_back("月令取印星为格");
    if (has_cai && !has_guan_sha)
      result.conflicts.push_back("财星坏印，未见官杀通关");
    if (has_guan_sha)
      result.supports.push_back("官杀生印");
    if (result.name == PatternKind::PianYin &&
        combo_is_warning(TenGodComboKind::OwlSeizesFood))
      result.conflicts.push_back("枭神夺食而无财制");
  } else if (result.name == PatternKind::JianLu ||
             result.name == PatternKind::YueJie) {
    result.supports.push_back(
        result.name == PatternKind::JianLu ? "日主临官得禄" : "月令本气为劫财");
    if (has_guan_sha)
      result.supports.push_back("见官杀制比劫");
    if (has_shi_shang)
      result.supports.push_back("见食伤泄秀");
    if (has_cai)
      result.supports.push_back("见财星耗旺身");
    if (!has_guan_sha && !has_shi_shang && !has_cai)
      result.conflicts.push_back("印比聚而无制、无泄、无耗");
  } else if (result.name == PatternKind::YangRen) {
    result.supports.push_back("阳干月令逢刃");
    if (has_guan_sha)
      result.supports.push_back("官杀制刃");
    else
      result.conflicts.push_back("羊刃无官杀制伏");
  }
  if (!result.conflicts.empty() &&
      result.condition == PatternCondition::Established) {
    result.condition = PatternCondition::Broken;
  }
  result.cheng_ge = result.condition == PatternCondition::Established ||
                    result.condition == PatternCondition::DiseasedRelieved;
  result.status =
      result.cheng_ge ? PatternStatus::Confirmed : PatternStatus::NeedsReview;
  return result;
}

std::vector<SpecialPatternResult>
evaluate_special_patterns(const BaZi &chart, const StrengthResult &strength,
                          const CarryingCapacity &capacity,
                          const std::array<ElementStat, 5> &balance) {
  std::vector<SpecialPatternResult> result;
  const auto stems = ZhouYi::BaZiBase::get_stems(chart);
  const auto branches = ZhouYi::BaZiBase::get_branches(chart);
  const auto day_element = get_wu_xing(chart.day.gan);
  const auto output_element = generated_element(day_element);
  const auto wealth_element = generated_element(output_element);
  const auto officer_element = controller_element(day_element);
  const auto resource = resource_element(day_element);
  const auto percent_of = [&](WuXing element) {
    return balance[element_index(element)].percent;
  };
  const bool has_yin_bi_stem =
      std::any_of(stems.begin(), stems.end(), [&](TianGan stem) {
        return stem != chart.day.gan &&
               (get_wu_xing(stem) == day_element ||
                wu_xing_sheng(get_wu_xing(stem), day_element));
      });

  // 从格重“无根无助、异党成势”。综合强弱分含固定季节/关系基线，
  // 因此以 33/40 配合严格根气阈值，不能沿用单一的 25 分极弱边界。
  const bool follow_base = strength.score < 33.0 &&
                           capacity.root_stability < 20.0 && !has_yin_bi_stem;
  const bool follow_candidate = strength.score < 40.0 &&
                                capacity.root_stability < 30.0 &&
                                !has_yin_bi_stem;
  const auto add_follow_pattern =
      [&](SpecialPatternKind name, WuXing target, bool direction_ok,
          std::string direction_reason, std::string obstruction) {
        const double target_percent = percent_of(target);
        const bool dominant = target_percent >= 40.0;
        SpecialPatternStatus status = SpecialPatternStatus::Excluded;
        std::vector<std::string> reasons;
        if (follow_base && dominant && direction_ok) {
          status = SpecialPatternStatus::Confirmed;
          reasons.push_back("日主极弱无根，天干不见印比援身");
          reasons.push_back(direction_reason);
        } else if (follow_candidate && target_percent >= 32.0 && direction_ok) {
          status = SpecialPatternStatus::NeedsReview;
          reasons.push_back("从势条件临界，须复核余气根、合化与岁运反局");
          reasons.push_back(direction_reason);
        } else {
          if (!follow_candidate)
            reasons.push_back("日主并非极弱无依，或仍见印比根援");
          if (target_percent < 32.0)
            reasons.push_back(element_name(target) + "未形成主导气势");
          if (!direction_ok)
            reasons.push_back(std::move(obstruction));
        }
        SpecialPatternResult item{
            name,
            status,
            {{"special.follow", stem_name(chart.day.gan), zh_text(name),
              target_percent,
              "日主强弱 " + std::to_string(strength.score) + "，根气 " +
                  std::to_string(capacity.root_stability) + "，所从五行占比 " +
                  std::to_string(target_percent)}},
            std::move(reasons)};
        item.element = target;
        result.push_back(std::move(item));
      };

  add_follow_pattern(
      SpecialPatternKind::FollowWealth, wealth_element,
      percent_of(wealth_element) > percent_of(officer_element) &&
          percent_of(wealth_element) >= percent_of(output_element),
      "财星成势，以财星为所从之神", "财星未压过官杀与食伤，不能定为从财");
  const bool killer_is_dominant =
      percent_of(officer_element) > percent_of(wealth_element) &&
      percent_of(officer_element) > percent_of(output_element);
  const bool output_can_control_killer = percent_of(output_element) >= 18.0;
  add_follow_pattern(SpecialPatternKind::FollowKiller, officer_element,
                     killer_is_dominant && !output_can_control_killer,
                     "官杀成势，且食伤不足以逆制官杀",
                     killer_is_dominant
                         ? "食伤有力制杀，官杀之势不纯"
                         : "官杀未压过财星与食伤，尚未形成从杀气势");
  add_follow_pattern(
      SpecialPatternKind::FollowOutput, output_element,
      percent_of(output_element) > percent_of(wealth_element) &&
          percent_of(output_element) > percent_of(officer_element) &&
          percent_of(resource) < 15.0 && percent_of(officer_element) < 18.0,
      "食伤成势，印星与官杀不足以逆局", "食伤未成主势，或仍有印星、官杀逆局");

  struct ZhuanWangRule {
    SpecialPatternKind name;
    WuXing element;
    std::array<DiZhi, 4> seasonal_branches;
    std::size_t seasonal_count;
  };
  const std::array<ZhuanWangRule, 5> zhuan_wang_rules = {{
      {SpecialPatternKind::QuZhi,
       WuXing::Mu,
       {DiZhi::Yin, DiZhi::Mao, DiZhi::Chen, DiZhi::Chen},
       3},
      {SpecialPatternKind::YanShang,
       WuXing::Huo,
       {DiZhi::Si, DiZhi::Wu, DiZhi::Wei, DiZhi::Wei},
       3},
      {SpecialPatternKind::JiaSe,
       WuXing::Tu,
       {DiZhi::Chen, DiZhi::Xu, DiZhi::Chou, DiZhi::Wei},
       4},
      {SpecialPatternKind::CongGe,
       WuXing::Jin,
       {DiZhi::Shen, DiZhi::You, DiZhi::Xu, DiZhi::Xu},
       3},
      {SpecialPatternKind::RunXia,
       WuXing::Shui,
       {DiZhi::Hai, DiZhi::Zi, DiZhi::Chou, DiZhi::Chou},
       3},
  }};
  const bool has_officer_stem =
      std::any_of(stems.begin(), stems.end(), [&](TianGan stem) {
        return stem != chart.day.gan && get_wu_xing(stem) == officer_element;
      });
  for (const auto &rule : zhuan_wang_rules) {
    const bool same_day = day_element == rule.element;
    const bool season_ok =
        std::find(rule.seasonal_branches.begin(),
                  rule.seasonal_branches.begin() +
                      static_cast<std::ptrdiff_t>(rule.seasonal_count),
                  chart.month.zhi) !=
        rule.seasonal_branches.begin() +
            static_cast<std::ptrdiff_t>(rule.seasonal_count);
    const double same_percent = percent_of(rule.element);
    const bool confirmed = same_day && season_ok && strength.score >= 75.0 &&
                           capacity.root_stability >= 65.0 &&
                           same_percent >= 42.0 && !has_officer_stem;
    const bool candidate = same_day && season_ok && strength.score >= 65.0 &&
                           capacity.root_stability >= 55.0 &&
                           same_percent >= 35.0;
    const auto status = confirmed
                            ? SpecialPatternStatus::Confirmed
                            : (candidate ? SpecialPatternStatus::NeedsReview
                                         : SpecialPatternStatus::Excluded);
    std::vector<std::string> reasons;
    if (!same_day)
      reasons.push_back("日主五行不属" + element_name(rule.element));
    if (same_day && !season_ok)
      reasons.push_back("月令未得专旺方局之气");
    if (same_day && same_percent < 35.0)
      reasons.push_back(element_name(rule.element) + "未形成专旺气势");
    if (same_day && has_officer_stem)
      reasons.push_back("天干见有力克神破其专旺");
    if (confirmed)
      reasons.push_back("得令、得根、同党成势且天干不见克神破局");
    if (!confirmed && candidate)
      reasons.push_back("专旺条件接近成格，仍须复核异党根气与合冲");
    SpecialPatternResult item{
        rule.name,
        status,
        {{"special.zhuan_wang", stem_name(chart.day.gan), zh_text(rule.name),
          same_percent,
          "日主强弱 " + std::to_string(strength.score) + "，根气 " +
              std::to_string(capacity.root_stability) + "，同党五行占比 " +
              std::to_string(same_percent)}},
        std::move(reasons)};
    item.element = rule.element;
    result.push_back(std::move(item));
  }

  const auto month_element = get_wu_xing(chart.month.zhi);
  bool hua_confirmed = false;
  for (std::size_t i = 0; i < stems.size() && !hua_confirmed; ++i) {
    for (std::size_t j = i + 1; j < stems.size() && !hua_confirmed; ++j) {
      if (stems[i] != chart.day.gan && stems[j] != chart.day.gan)
        continue;
      const auto hua_element =
          ZhouYi::GanZhi::stem_combine_element(stems[i], stems[j]);
      if (!hua_element)
        continue;
      const bool month_ok = month_element == *hua_element;
      const bool root_ok =
          std::any_of(branches.begin(), branches.end(), [&](DiZhi branch) {
            return get_wu_xing(branch) == *hua_element;
          });
      const bool no_zheng_he =
          std::count(stems.begin(), stems.end(), stems[i]) == 1 &&
          std::count(stems.begin(), stems.end(), stems[j]) == 1;
      const bool no_ke_po =
          !std::any_of(stems.begin(), stems.end(), [&](TianGan stem) {
            return stem != stems[i] && stem != stems[j] &&
                   wu_xing_ke(get_wu_xing(stem), *hua_element);
          });
      if (month_ok && root_ok && no_zheng_he && no_ke_po) {
        result.push_back(
            {SpecialPatternKind::HuaQi,
             SpecialPatternStatus::Confirmed,
             {{"special.hua_qi", stem_name(stems[i]) + stem_name(stems[j]),
               "五合化气", 1.0, "月令引化、化神有根、无争合且无克破"}},
             {}});
        result.back().element = *hua_element;
        hua_confirmed = true;
      } else {
        std::vector<std::string> reasons;
        if (!month_ok)
          reasons.push_back("月令未引化");
        if (!root_ok)
          reasons.push_back("化神无根");
        if (!no_zheng_he)
          reasons.push_back("存在争合");
        if (!no_ke_po)
          reasons.push_back("化神受克破");
        result.push_back(
            {SpecialPatternKind::HuaQi,
             SpecialPatternStatus::Excluded,
             {{"special.hua_qi", stem_name(stems[i]) + stem_name(stems[j]),
               "五合", 0.0, "五合存在但不满足化气完整条件"}},
             std::move(reasons)});
        result.back().element = *hua_element;
      }
    }
  }
  if (result.empty() ||
      std::none_of(result.begin(), result.end(), [](const auto &item) {
        return item.name == SpecialPatternKind::HuaQi;
      })) {
    result.push_back({SpecialPatternKind::HuaQi,
                      SpecialPatternStatus::Excluded,
                      {},
                      {"命局未见天干五合"}});
  }
  return result;
}

ShengKeChainResult analyze_chain(TianGan day_master,
                                 const std::array<ElementStat, 5> &balance) {
  const auto day = get_wu_xing(day_master);
  ShengKeChainResult result;
  result.chain = {resource_element(day), day, generated_element(day),
                  generated_element(generated_element(day)),
                  controller_element(day)};
  for (std::size_t index = 0; index + 1 < result.chain.size(); ++index) {
    const auto element = result.chain[index];
    if (balance[element_index(element)].percent < 8.0) {
      result.breaks.push_back(
          element_name(element) + "力量不足，" + element_name(element) + "→" +
          element_name(result.chain[index + 1]) + "链段待复核");
    }
  }
  result.smooth = result.breaks.empty();
  result.evidence.push_back(
      {"sheng_ke_chain", stem_name(day_master), "五行流通",
       result.smooth ? 1.0 : -1.0,
       result.smooth ? "各链段均达到最低力量阈值" : "存在力量不足链段"});
  return result;
}

UsefulGodRoute determine_use_route(const BaZi &chart,
                                   const PatternResult &pattern,
                                   const ShenCandidate &yong,
                                   bool special_locked) {
  if (special_locked)
    return UsefulGodRoute::FollowSpecialPattern;
  const auto day = get_wu_xing(chart.day.gan);
  const auto output = generated_element(day);
  const auto wealth = generated_element(output);
  const auto officer = generated_element(wealth);
  if ((pattern.name == PatternKind::ZhengCai ||
       pattern.name == PatternKind::PianCai) &&
      yong.element == officer) {
    return UsefulGodRoute::OfficerProtectsWealth;
  }
  if ((pattern.name == PatternKind::ZhengCai ||
       pattern.name == PatternKind::PianCai) &&
      yong.element == output) {
    return UsefulGodRoute::OutputGeneratesWealth;
  }
  return UsefulGodRoute::CandidatePrimaryEffect;
}

void apply_route_consistency(UsefulGodResult &useful,
                             const CarryingCapacity &capacity) {
  if (!useful.yong_shen)
    return;
  const auto yong_element = useful.yong_shen->element;
  for (auto &candidate : useful.candidates) {
    if (candidate.element == yong_element ||
        !wu_xing_ke(candidate.element, yong_element))
      continue;
    const double penalty =
        useful.route == UsefulGodRoute::OfficerProtectsWealth ? -18.0 : -10.0;
    candidate.effects.push_back(
        {CandidateEffectType::PatternMedicine, penalty, false,
         "该候选五行直接克制当前主用，不能同时列为无条件喜神"});
    candidate.conflicts.push_back({"useful_god.route_conflict",
                                   stem_name(candidate.stem), "克制主用",
                                   penalty,
                                   "取用路线已锁定为“" + zh_text(useful.route) +
                                       "”，须先保证主用不受喜神反克"});
    apply_capacity(candidate, capacity);
  }
  sort_and_percent_candidates(useful.candidates);
  useful.yong_shen = useful.candidates.front();
  useful.yong_shen->role = "用神";
}

void assign_shen_roles(UsefulGodResult &useful, const BaZi &chart) {
  if (useful.candidates.empty())
    return;
  useful.yong_shen = useful.candidates.front();
  useful.yong_shen->role = "用神";
  std::vector<WuXing> used_elements{useful.yong_shen->element};
  const auto unused = [&](WuXing element) {
    return std::find(used_elements.begin(), used_elements.end(), element) ==
           used_elements.end();
  };
  const auto find_candidate =
      [&](auto predicate) -> std::optional<ShenCandidate> {
    for (const auto &candidate : useful.candidates) {
      if (predicate(candidate))
        return candidate;
    }
    return std::nullopt;
  };
  const auto find_element =
      [&](WuXing element, std::optional<ShiShen> preferred = std::nullopt) {
        if (preferred) {
          const auto exact = find_candidate([&](const auto &candidate) {
            return unused(candidate.element) && candidate.element == element &&
                   get_shi_shen(chart.day.gan, candidate.stem) == *preferred;
          });
          if (exact)
            return exact;
        }
        return find_candidate([&](const auto &candidate) {
          return unused(candidate.element) && candidate.element == element;
        });
      };

  const auto day = get_wu_xing(chart.day.gan);
  const auto output = generated_element(day);
  const auto wealth = generated_element(output);
  const auto resource = resource_element(day);
  if (useful.route == UsefulGodRoute::OfficerProtectsWealth) {
    useful.role_notes.push_back("丙火为伤官，直克庚官，按近忌论");
    useful.role_notes.push_back("丁火为食神，虽可生财但仍制官，只作受限闲神");
    useful.xi_shen = find_element(wealth);
    if (useful.xi_shen) {
      useful.xi_shen->role = "喜神";
      used_elements.push_back(useful.xi_shen->element);
    }
    useful.ji_shen = find_element(day, ShiShen::JieCai);
    if (useful.ji_shen) {
      useful.ji_shen->role = "忌神";
      used_elements.push_back(useful.ji_shen->element);
    }
    useful.chou_shen = find_element(resource, ShiShen::ZhengYin);
    if (useful.chou_shen) {
      useful.chou_shen->role = "仇神";
      used_elements.push_back(useful.chou_shen->element);
    }
    useful.xian_shen = find_element(output, ShiShen::ShiShen);
    if (useful.xian_shen)
      useful.xian_shen->role = "闲神（受限）";
    return;
  }

  useful.xi_shen = find_candidate([&](const auto &candidate) {
    return unused(candidate.element) && candidate.effective_score > 0.0 &&
           !wu_xing_ke(candidate.element, useful.yong_shen->element);
  });
  if (useful.xi_shen) {
    useful.xi_shen->role = "喜神";
    used_elements.push_back(useful.xi_shen->element);
  }
  for (auto it = useful.candidates.rbegin(); it != useful.candidates.rend();
       ++it) {
    if (!unused(it->element))
      continue;
    useful.ji_shen = *it;
    useful.ji_shen->role = "忌神";
    used_elements.push_back(it->element);
    break;
  }
  if (useful.ji_shen) {
    useful.chou_shen = find_candidate([&](const auto &candidate) {
      return unused(candidate.element) &&
             (wu_xing_sheng(candidate.element, useful.ji_shen->element) ||
              wu_xing_ke(candidate.element, useful.yong_shen->element));
    });
    if (!useful.chou_shen) {
      useful.chou_shen = find_candidate(
          [&](const auto &candidate) { return unused(candidate.element); });
    }
    if (useful.chou_shen) {
      useful.chou_shen->role = "仇神";
      used_elements.push_back(useful.chou_shen->element);
    }
  }
  useful.xian_shen = find_candidate(
      [&](const auto &candidate) { return unused(candidate.element); });
  if (useful.xian_shen)
    useful.xian_shen->role = "闲神";
}

void explain_yong_selection(UsefulGodResult &useful, bool special_locked,
                            const ClimateResult &climate,
                            const PatternResult &pattern,
                            const std::vector<TenGodCombo> &combos,
                            const StrengthResult &strength) {
  if (!useful.yong_shen)
    return;
  if (special_locked) {
    useful.method = UsefulGodMethod::SpecialPattern;
    useful.reason = "特殊格局已确认，按顺势或化气方向锁定候选";
    return;
  }
  if (useful.route == UsefulGodRoute::OfficerProtectsWealth) {
    useful.method = UsefulGodMethod::PatternMedicine;
    useful.reason =
        "财格以比劫夺财为病，锁定官制比护财路线；喜神须生扶官星，不得反克主用";
    return;
  }
  if (useful.route == UsefulGodRoute::OutputGeneratesWealth) {
    useful.method = UsefulGodMethod::PatternCirculation;
    useful.reason =
        "财格锁定食伤泄比生财路线，不再同时把受食伤克制的官星列为主用";
    return;
  }
  if (climate.needed && climate.urgency == ClimateUrgency::Urgent &&
      (climate.state == ClimateState::Missing ||
       climate.state == ClimateState::Insufficient) &&
      useful.yong_shen->element == climate.element) {
    useful.method = UsefulGodMethod::Climate;
    useful.reason = climate.reason + "；该候选的直接环境作用优先保留";
    return;
  }
  const bool has_disease =
      std::any_of(combos.begin(), combos.end(), [](const auto &combo) {
        return combo.severity == ComboSeverity::Warning;
      });
  if (has_disease || !pattern.conflicts.empty()) {
    useful.method = UsefulGodMethod::PatternMedicine;
    useful.reason =
        "命局存在格局或十神组合风险，候选按制化、通关与格局相神方向排序";
    return;
  }
  if (strength.level == StrengthLevel::Strong ||
      strength.level == StrengthLevel::ExtremelyStrong) {
    useful.method = UsefulGodMethod::Balance;
    useful.reason = "日主偏强，优先考察克、泄、耗的有效作用";
  } else if (strength.level == StrengthLevel::Weak ||
             strength.level == StrengthLevel::ExtremelyWeak) {
    useful.method = UsefulGodMethod::Balance;
    useful.reason = "日主偏弱，优先考察印比帮扶及其承载能力";
  } else {
    useful.method = UsefulGodMethod::PatternCirculation;
    useful.reason = "日主中和，以月令格局、五行流通和候选有效分共同取用";
  }
}

UsefulGodVerification
verify_yong_shen(const BaZi &chart, const ShenCandidate &candidate,
                 const std::vector<BranchRelation> &relations,
                 const std::vector<StemRelation> &stem_relations,
                 const KongWangResult &kong_wang,
                 const CarryingCapacity &capacity,
                 const AnalysisConfig &config) {
  UsefulGodVerification result;
  result.root_level = "无根";
  result.carrying_capacity = capacity.overall;
  const auto branches = ZhouYi::BaZiBase::get_branches(chart);
  const auto stems = ZhouYi::BaZiBase::get_stems(chart);
  int root_rank = 0;
  double exact_root_power = 0.0;
  for (std::size_t position = 0; position < branches.size(); ++position) {
    const auto hidden = get_cang_gan(branches[position]);
    for (std::size_t index = 0; index < hidden.size(); ++index) {
      if (hidden[index] != candidate.stem) {
        if (get_wu_xing(hidden[index]) == candidate.element) {
          result.same_element_support.push_back(
              {"useful_god.same_element_support",
               branch_name(branches[position]) + stem_name(hidden[index]),
               "同五行助力", 0.0,
               "与候选同五行，但不冒充" + stem_name(candidate.stem) +
                   "的本干根"});
        }
        continue;
      }
      const int rank = index == 0 ? 3 : (index == 1 ? 2 : 1);
      double root_points = rank == 3 ? 60.0 : (rank == 2 ? 40.0 : 20.0);
      const bool clashed = branch_is_clashed(branches[position], relations);
      const bool empty = position_is_kong_wang(position, kong_wang);
      if (clashed)
        root_points *= 0.5;
      if (empty)
        root_points *= kong_wang.root_multiplier;
      if (rank > root_rank) {
        root_rank = rank;
        result.root_level =
            rank == 3 ? "本气强根" : (rank == 2 ? "中气中根" : "余气弱根");
      }
      exact_root_power = std::max(exact_root_power, root_points);
      if (clashed)
        result.is_chonged = true;
      Evidence root{"useful_god.verify_exact_root",
                    branch_name(branches[position]) + stem_name(hidden[index]),
                    result.root_level, root_points,
                    clashed && empty ? "候选本干根受冲且落空，双重折减"
                    : clashed        ? "候选本干根所在支受冲"
                    : empty          ? "候选本干根落旬空，根气折减不归零"
                                     : "候选本干在原局有根"};
      result.exact_roots.push_back(root);
      result.evidence.push_back(std::move(root));
    }
  }
  result.tou_gan = std::any_of(stems.begin(), stems.end(), [&](TianGan stem) {
    return stem == candidate.stem;
  });
  result.is_he = std::any_of(stem_relations.begin(), stem_relations.end(),
                             [&](const StemRelation &relation) {
                               return relation.first == candidate.stem ||
                                      relation.second == candidate.stem;
                             });
  result.is_transformed = std::any_of(
      stem_relations.begin(), stem_relations.end(),
      [&](const StemRelation &relation) {
        return relation.effective && (relation.first == candidate.stem ||
                                      relation.second == candidate.stem);
      });
  for (const auto &relation : stem_relations) {
    if (relation.first != candidate.stem && relation.second != candidate.stem)
      continue;
    result.combine_penalty =
        std::max(result.combine_penalty,
                 untransformed_combine_penalty(relation, config));
  }
  result.power = exact_root_power + (result.tou_gan ? 20.0 : 0.0) +
                 capacity.overall * 0.20;
  result.power -= result.combine_penalty;
  result.power = clamp_score(result.power);
  result.effective = exact_root_power >= 10.0 && !result.is_transformed &&
                     result.power >= config.useful_god_effective_threshold;
  result.evidence.push_back(
      {"useful_god.verify_transparency", stem_name(candidate.stem), "透干",
       result.tou_gan ? 20.0 : 0.0,
       result.tou_gan ? "候选本干已精确透于原局；同五行异干不代替"
                      : "候选本干未透于原局"});
  result.evidence.push_back(
      {"useful_god.verify_relation", stem_name(candidate.stem),
       result.is_transformed ? "合化" : (result.is_he ? "合绊" : "无合"),
       -result.combine_penalty,
       result.is_transformed
           ? "候选本干参与有效合化，原有作用迁移"
           : (result.is_he ? "候选本干见五合不化，按相邻/隔位距离折减"
                           : "未见候选本干参与天干五合")});
  result.evidence.push_back(
      {"useful_god.verify_capacity", stem_name(candidate.stem), "承载能力",
       capacity.overall, "承载能力与根气、冲合共同决定候选是否可兑现"});
  return result;
}

} // namespace

AnalysisResult analyze(const BaZi &chart, const BirthContext &birth_context,
                       const std::vector<Pillar> &fortunes,
                       const AnalysisConfig &config) {
  AnalysisResult result;
  result.rule_set = "ziping-v3-route-consistent";
  result.config = config;
  result.birth_context = resolve_birth_context(birth_context);
  result.day_master = chart.day.gan;
  result.input_pillars = ZhouYi::BaZiBase::get_pillars(chart);
  for (std::size_t index = 0; index < result.input_pillars.size(); ++index) {
    const auto nayin =
        ZhouYi::BaZiBase::calculate_pillar_nayin(result.input_pillars[index]);
    result.nayin[index] = {result.input_pillars[index].to_string(), nayin.name,
                           nayin.element};
  }
  result.relations = Detail::detect_branch_relations(chart);
  result.stem_relations = Detail::detect_stem_relations(chart);
  result.kong_wang = calculate_kong_wang(chart, config);
  result.element_balance =
      calculate_balance(chart, config, result.relations, result.stem_relations);
  result.ten_god_occurrences = build_ten_god_occurrences(
      chart, result.relations, result.kong_wang, config);
  result.climate = assess_climate(chart, result.ten_god_occurrences,
                                  result.element_balance, config);
  result.strength =
      assess_strength(chart, result.relations, result.kong_wang, config);
  result.carrying_capacity =
      assess_capacity(chart, result.strength, result.relations,
                      result.kong_wang, result.element_balance, config);
  result.ten_god_combos =
      detect_ten_god_combos(result.ten_god_occurrences, result.strength);
  result.pattern = determine_pattern(
      chart, result.ten_god_combos, result.stem_relations, result.relations,
      result.ten_god_occurrences, result.birth_context, config);
  result.special_patterns = evaluate_special_patterns(
      chart, result.strength, result.carrying_capacity, result.element_balance);
  result.useful_gods.candidates = generate_candidates(
      chart, result.strength, result.carrying_capacity, result.element_balance,
      result.pattern, result.climate, result.ten_god_combos,
      result.ten_god_occurrences, result.stem_relations, config);
  const bool special_locked = apply_confirmed_special_pattern(
      result.useful_gods.candidates, result.special_patterns,
      result.useful_gods.warnings);

  if (!result.useful_gods.candidates.empty()) {
    result.useful_gods.yong_shen = result.useful_gods.candidates.front();
    result.useful_gods.route = determine_use_route(
        chart, result.pattern, *result.useful_gods.yong_shen, special_locked);
    apply_route_consistency(result.useful_gods, result.carrying_capacity);
  }

  if (!result.useful_gods.candidates.empty()) {
    result.useful_gods.yong_shen = result.useful_gods.candidates.front();
    const double gap =
        result.useful_gods.candidates.size() > 1
            ? result.useful_gods.candidates[0].effective_score -
                  result.useful_gods.candidates[1].effective_score
            : 100.0;
    const bool route_locked =
        result.useful_gods.route == UsefulGodRoute::OfficerProtectsWealth &&
        (result.pattern.condition == PatternCondition::MedicineWeak ||
         result.pattern.condition == PatternCondition::DiseasedRelieved);
    if (special_locked) {
      result.useful_gods.status = UsefulGodStatus::Confirmed;
    } else if (route_locked) {
      result.useful_gods.status = UsefulGodStatus::Candidate;
    } else if (result.carrying_capacity.overall <
                   config.low_capacity_threshold ||
               gap < config.review_score_gap) {
      result.useful_gods.status = UsefulGodStatus::NeedsReview;
      result.useful_gods.warnings.push_back(
          "承载能力不足或候选分差较小，保留候选而不锁定唯一用神");
    } else {
      result.useful_gods.status = UsefulGodStatus::Candidate;
    }
  } else {
    result.useful_gods.status = UsefulGodStatus::NeedsReview;
    result.useful_gods.warnings.push_back(
        "未产生有效候选；请检查输入与规则配置");
  }
  assign_shen_roles(result.useful_gods, chart);
  explain_yong_selection(result.useful_gods, special_locked, result.climate,
                         result.pattern, result.ten_god_combos,
                         result.strength);
  if (result.useful_gods.yong_shen) {
    result.useful_gods.verification =
        verify_yong_shen(chart, *result.useful_gods.yong_shen, result.relations,
                         result.stem_relations, result.kong_wang,
                         result.carrying_capacity, config);
    const bool route_locked =
        result.useful_gods.route == UsefulGodRoute::OfficerProtectsWealth &&
        (result.pattern.condition == PatternCondition::MedicineWeak ||
         result.pattern.condition == PatternCondition::DiseasedRelieved);
    if (!special_locked && route_locked &&
        result.useful_gods.verification->effective) {
      result.useful_gods.status = UsefulGodStatus::Confirmed;
      result.useful_gods.role_notes.push_back(
          "庚官可定为用，但原局病重药轻，仍喜岁运土金扶助");
    } else if (!special_locked && !result.useful_gods.verification->effective) {
      result.useful_gods.status = UsefulGodStatus::NeedsReview;
      result.useful_gods.warnings.push_back(
          "候选用神在原局根气、冲合或承载验证未通过，需结合岁运复核");
    }
  }
  result.sheng_ke_chain = analyze_chain(chart.day.gan, result.element_balance);
  result.fortune_impacts = Detail::evaluate_fortunes(fortunes, result);
  result.warnings = result.useful_gods.warnings;
  return result;
}

AnalysisResult analyze(const BaZi &chart, const AnalysisConfig &config) {
  return analyze(chart, BirthContext{}, {}, config);
}

AnalysisResult analyze(const BaZi &chart, const std::vector<Pillar> &fortunes,
                       const AnalysisConfig &config) {
  return analyze(chart, BirthContext{}, fortunes, config);
}

AnalysisResult analyze(const BaZi &chart, const BirthContext &birth_context,
                       const AnalysisConfig &config) {
  return analyze(chart, birth_context, {}, config);
}

FortuneImpact analyze_fortune(const Pillar &fortune,
                              const AnalysisResult &analysis) {
  const auto impacts = Detail::evaluate_fortunes({fortune}, analysis);
  return impacts.empty() ? FortuneImpact{fortune,
                                         "流年/大运候选作用",
                                         0.0,
                                         {"当前无可用用神候选"},
                                         {}}
                         : impacts.front();
}

TransitAnalysis analyze_transit(const TransitContext &context,
                                const AnalysisResult &analysis) {
  return Detail::evaluate_transit(context, analysis);
}

void annotate_sample_extremes(std::vector<TransitAnalysis> &analyses) {
  Detail::annotate_sample_extremes(analyses);
}

nlohmann::json to_json(const AnalysisResult &result) {
  return Detail::render_json(result);
}

nlohmann::json to_json(const TransitAnalysis &result) {
  return Detail::render_transit_json(result);
}

std::string to_zh(const AnalysisResult &result) {
  return Detail::render_zh(result);
}

std::string to_zh(const TransitAnalysis &result) {
  return Detail::render_transit_zh(result);
}

void write_zh(std::ostream &output, const AnalysisResult &result) {
  output << Detail::render_zh(result);
}

void write_zh(std::ostream &output, const TransitAnalysis &result) {
  output << Detail::render_transit_zh(result);
}

std::string format_analysis(const AnalysisResult &result) {
  return Detail::render_summary(result);
}

} // namespace ZhouYi::BaZiAnalysis
