// C++23 Module - 八字岁运评估策略实现
module ZhouYi.BaZiAnalysis.Fortune;

import ZhouYi.BaZiBase;
import ZhouYi.GanZhi;
import std;

namespace ZhouYi::BaZiAnalysis {

namespace {

std::string element_name(WuXing element) {
  return std::string(ZhouYi::GanZhi::Mapper::to_zh(element));
}

std::string branch_name(DiZhi branch) {
  return std::string(ZhouYi::GanZhi::Mapper::to_zh(branch));
}

std::string stem_name(TianGan stem) {
  return std::string(ZhouYi::GanZhi::Mapper::to_zh(stem));
}

std::string_view event_type_name(TransitEventType type) {
  static constexpr std::array<std::string_view, 6> names = {
      "身体伤灾", "财务借贷", "事业技艺", "合同规则", "人际感情", "迁动变更"};
  return names[static_cast<std::size_t>(type)];
}

void replace_all(std::string &text, std::string_view from,
                 std::string_view to) {
  std::size_t position = 0;
  while ((position = text.find(from, position)) != std::string::npos) {
    text.replace(position, from.size(), to);
    position += to.size();
  }
}

void relabel_fortune_layer(FortuneImpact &impact, TransitLayer layer) {
  if (layer == TransitLayer::DaYun)
    return;
  const std::string_view stem_label =
      layer == TransitLayer::LiuNian ? "岁干" : "月干";
  const std::string_view branch_label =
      layer == TransitLayer::LiuNian ? "岁支" : "月支";
  const auto relabel = [&](std::string &text) {
    replace_all(text, "运干", stem_label);
    replace_all(text, "运支", branch_label);
  };
  for (auto &reason : impact.reasons)
    relabel(reason);
  for (auto &note : impact.review_notes)
    relabel(note);
  for (auto &evidence : impact.evidence) {
    relabel(evidence.subject);
    relabel(evidence.reason);
  }
  for (auto &channel : impact.channels) {
    relabel(channel.subject);
    relabel(channel.reason);
  }
}

double officer_route_score(ShiShen god) {
  switch (god) {
  case ShiShen::BiJian:
    return -6.0;
  case ShiShen::JieCai:
    return -10.0;
  case ShiShen::ShiShen:
    return -2.0;
  case ShiShen::ShangGuan:
    return -10.0;
  case ShiShen::PianCai:
    return 4.0;
  case ShiShen::ZhengCai:
    return 6.0;
  case ShiShen::QiSha:
    return -12.0;
  case ShiShen::ZhengGuan:
    return 8.0;
  case ShiShen::PianYin:
    return -4.0;
  case ShiShen::ZhengYin:
    return -6.0;
  }
  return 0.0;
}

auto element_from_relation(std::string_view relation) -> std::optional<WuXing> {
  for (const auto element :
       {WuXing::Mu, WuXing::Huo, WuXing::Tu, WuXing::Jin, WuXing::Shui}) {
    if (relation.ends_with(element_name(element)))
      return element;
  }
  return std::nullopt;
}

auto evidence_domains(const Evidence &evidence, const AnalysisResult &analysis)
    -> std::vector<TransitEventType> {
  if (evidence.rule == "fortune.qi_sha_attack" ||
      evidence.rule == "fortune.qi_sha_rescue")
    return {TransitEventType::BodySafety};
  if (evidence.rule == "fortune.output_generates_wealth")
    return {TransitEventType::CareerDirection, TransitEventType::WealthDebt};
  if (evidence.rule == "fortune.hurting_officer_meets_officer")
    return {TransitEventType::RulesDisputes};
  if (evidence.rule == "fortune.clash_day_branch")
    return {TransitEventType::BodySafety, TransitEventType::Relationship,
            TransitEventType::MovementChange};
  if (evidence.rule == "fortune.day_branch_joined_group")
    return {TransitEventType::Relationship};
  if (evidence.rule == "fortune.clash_month_command")
    return {TransitEventType::CareerDirection,
            TransitEventType::MovementChange};
  if (evidence.rule == "fortune.competing_combine")
    return {TransitEventType::CareerDirection};
  if (evidence.rule == "fortune.stem_relation")
    return {TransitEventType::CareerDirection, TransitEventType::RulesDisputes};
  if (evidence.rule == "fortune.complete_branch_group") {
    const auto element = element_from_relation(evidence.relation);
    if (element) {
      const auto day_element = get_wu_xing(analysis.day_master);
      if (*element == day_element)
        return {TransitEventType::WealthDebt};
      if (wu_xing_ke(day_element, *element))
        return {TransitEventType::WealthDebt};
      if (wu_xing_ke(*element, day_element))
        return {TransitEventType::BodySafety, TransitEventType::RulesDisputes};
      if (wu_xing_sheng(day_element, *element))
        return {TransitEventType::CareerDirection,
                TransitEventType::WealthDebt};
    }
    return {TransitEventType::CareerDirection};
  }
  if (evidence.rule == "fortune.branch_relation" ||
      evidence.rule == "fortune.exact_stem_role" ||
      evidence.rule == "fortune.control" ||
      evidence.rule == "fortune.climate_dual_role")
    return {TransitEventType::CareerDirection};
  if (evidence.rule == "fortune.route_ten_god") {
    if (evidence.relation == "比肩" || evidence.relation == "劫财")
      return {TransitEventType::WealthDebt, TransitEventType::CareerDirection};
    if (evidence.relation == "正财" || evidence.relation == "偏财")
      return {TransitEventType::WealthDebt, TransitEventType::CareerDirection};
    if (evidence.relation == "食神")
      return {TransitEventType::CareerDirection};
    if (evidence.relation == "伤官")
      return {TransitEventType::RulesDisputes};
    if (evidence.relation == "正官" || evidence.relation == "七杀")
      return {TransitEventType::CareerDirection,
              TransitEventType::RulesDisputes};
    return {TransitEventType::CareerDirection};
  }
  return {};
}

void build_effect_channels(FortuneImpact &impact,
                           const AnalysisResult &analysis) {
  for (const auto &evidence : impact.evidence) {
    auto domains = evidence_domains(evidence, analysis);
    if (domains.empty())
      continue;
    const auto nature = evidence.points > 0.0   ? TransitEffectNature::Favorable
                        : evidence.points < 0.0 ? TransitEffectNature::Adverse
                                                : TransitEffectNature::Neutral;
    impact.channels.push_back({evidence.rule, evidence.subject,
                               evidence.relation, nature, evidence.points,
                               std::move(domains), evidence.reason});
  }
}

auto useful_god_domains(const AnalysisResult &analysis)
    -> std::vector<TransitEventType> {
  if (!analysis.useful_gods.yong_shen)
    return {TransitEventType::CareerDirection};
  switch (
      get_shi_shen(analysis.day_master, analysis.useful_gods.yong_shen->stem)) {
  case ShiShen::ZhengCai:
  case ShiShen::PianCai:
    return {TransitEventType::WealthDebt};
  case ShiShen::ZhengGuan:
    return {TransitEventType::CareerDirection, TransitEventType::RulesDisputes};
  case ShiShen::QiSha:
    return {TransitEventType::BodySafety, TransitEventType::RulesDisputes};
  case ShiShen::ShiShen:
    return {TransitEventType::CareerDirection, TransitEventType::WealthDebt};
  case ShiShen::ShangGuan:
    return {TransitEventType::CareerDirection, TransitEventType::RulesDisputes};
  case ShiShen::BiJian:
  case ShiShen::JieCai:
    return {TransitEventType::WealthDebt, TransitEventType::Relationship};
  case ShiShen::ZhengYin:
  case ShiShen::PianYin:
    return {TransitEventType::CareerDirection};
  }
  return {TransitEventType::CareerDirection};
}

auto interaction_domains(const TransitInteraction &interaction,
                         const AnalysisResult &analysis)
    -> std::vector<TransitEventType> {
  if (interaction.rule == "transit.self_punishment" ||
      interaction.rule == "transit.multiple_killer_attack")
    return {TransitEventType::BodySafety};
  if (interaction.rule == "transit.three_punishment" ||
      interaction.rule == "transit.heaven_control_earth_clash" ||
      interaction.rule == "transit.layer_war")
    return {TransitEventType::BodySafety, TransitEventType::MovementChange};
  if (interaction.rule == "transit.layer_branch_clash_peer")
    return {TransitEventType::CareerDirection,
            TransitEventType::MovementChange};
  if (interaction.rule == "transit.layer_branch_clash")
    return {TransitEventType::CareerDirection,
            TransitEventType::MovementChange};
  if (interaction.rule == "transit.food_controls_killer" ||
      interaction.rule == "transit.seal_transforms_killer")
    return {TransitEventType::BodySafety};
  if (interaction.rule == "transit.branch_combine_month_command")
    return {TransitEventType::CareerDirection,
            TransitEventType::MovementChange};
  if (interaction.rule == "transit.branch_combine_useful_root")
    return useful_god_domains(analysis);
  if (interaction.rule == "transit.branch_six_combine" ||
      interaction.rule == "transit.branch_half_combine") {
    if (interaction.reason.find("七杀") != std::string::npos)
      return {TransitEventType::BodySafety};
    if (interaction.reason.find("正官") != std::string::npos)
      return {TransitEventType::CareerDirection,
              TransitEventType::RulesDisputes};
    if (interaction.reason.find("财") != std::string::npos)
      return {TransitEventType::WealthDebt};
    return {TransitEventType::CareerDirection, TransitEventType::Relationship};
  }
  if (interaction.rule == "transit.branch_relation_competition" ||
      interaction.rule == "transit.joint_competing_combine" ||
      interaction.rule == "transit.fu_yin" ||
      interaction.rule == "transit.same_pillar")
    return {TransitEventType::CareerDirection, TransitEventType::Relationship};
  if (interaction.rule == "transit.seal_controls_output")
    return {TransitEventType::CareerDirection, TransitEventType::RulesDisputes};
  if (interaction.rule == "transit.wealth_damages_climate_seal")
    return {TransitEventType::WealthDebt, TransitEventType::CareerDirection};
  return {TransitEventType::CareerDirection};
}

} // namespace

std::vector<FortuneImpact>
Detail::evaluate_fortunes(const std::vector<Pillar> &fortunes,
                          const AnalysisResult &analysis) {
  std::vector<FortuneImpact> result;
  const auto &useful = analysis.useful_gods;
  const auto &original_pillars = analysis.input_pillars;
  const auto &kong_wang = analysis.kong_wang;
  if (!useful.yong_shen)
    return result;
  const auto yong_element = useful.yong_shen->element;
  const auto xi_element = useful.xi_shen
                              ? std::optional<WuXing>{useful.xi_shen->element}
                              : std::nullopt;
  const auto ji_element = useful.ji_shen
                              ? std::optional<WuXing>{useful.ji_shen->element}
                              : std::nullopt;
  const auto chou_element =
      useful.chou_shen ? std::optional<WuXing>{useful.chou_shen->element}
                       : std::nullopt;
  const auto day_stem = original_pillars[2].gan;
  const auto ten_god_power = [&](std::initializer_list<ShiShen> gods) {
    return std::accumulate(
        analysis.ten_god_occurrences.begin(),
        analysis.ten_god_occurrences.end(), 0.0,
        [&](double total, const TenGodOccurrence &occurrence) {
          return std::find(gods.begin(), gods.end(), occurrence.ten_god) !=
                         gods.end()
                     ? total + occurrence.effective_power
                     : total;
        });
  };
  const double food_power = ten_god_power({ShiShen::ShiShen});
  const double resource_power =
      ten_god_power({ShiShen::ZhengYin, ShiShen::PianYin});
  const double wealth_power =
      ten_god_power({ShiShen::ZhengCai, ShiShen::PianCai});
  const double peer_power = ten_god_power({ShiShen::BiJian, ShiShen::JieCai});
  for (const auto &pillar : fortunes) {
    FortuneImpact impact{pillar, "大运/流年候选作用", 0.0, {}, {}};
    const auto branch_hidden = get_cang_gan(pillar.zhi);
    const TianGan branch_main_stem = branch_hidden.front();
    const double active_food_power =
        food_power +
        (get_shi_shen(day_stem, pillar.gan) == ShiShen::ShiShen ? 1.0 : 0.0) +
        (get_shi_shen(day_stem, branch_main_stem) == ShiShen::ShiShen ? 1.0
                                                                      : 0.0);
    const double active_resource_power =
        resource_power +
        ((get_shi_shen(day_stem, pillar.gan) == ShiShen::ZhengYin ||
          get_shi_shen(day_stem, pillar.gan) == ShiShen::PianYin)
             ? 1.0
             : 0.0) +
        ((get_shi_shen(day_stem, branch_main_stem) == ShiShen::ZhengYin ||
          get_shi_shen(day_stem, branch_main_stem) == ShiShen::PianYin)
             ? 1.0
             : 0.0);
    std::string stem_strength_basis;
    const auto stem_strength_multiplier = [&] {
      const auto life_stage = get_shi_er_chang_sheng(pillar.gan, pillar.zhi);
      const std::string life_stage_name(
          ZhouYi::GanZhi::ShiErChangShengMapper::to_zh(life_stage));
      if (std::find(branch_hidden.begin(), branch_hidden.end(), pillar.gan) !=
          branch_hidden.end()) {
        stem_strength_basis = "坐支藏同干，通根有力";
        return 1.20;
      }
      switch (life_stage) {
      case ZhouYi::GanZhi::ShiErChangSheng::LinGuan:
      case ZhouYi::GanZhi::ShiErChangSheng::DiWang:
        stem_strength_basis = "十二长生处" + life_stage_name + "，得地有力";
        return 1.25;
      case ZhouYi::GanZhi::ShiErChangSheng::ChangSheng:
      case ZhouYi::GanZhi::ShiErChangSheng::GuanDai:
        stem_strength_basis = "十二长生处" + life_stage_name + "，气势有承载";
        return 1.15;
      case ZhouYi::GanZhi::ShiErChangSheng::Mu:
        stem_strength_basis = "十二长生处墓库，有根但闭藏";
        return 1.05;
      case ZhouYi::GanZhi::ShiErChangSheng::Si:
      case ZhouYi::GanZhi::ShiErChangSheng::Jue:
        stem_strength_basis = "十二长生处" + life_stage_name + "，承载明显不足";
        return 0.60;
      case ZhouYi::GanZhi::ShiErChangSheng::Shuai:
      case ZhouYi::GanZhi::ShiErChangSheng::Bing:
        stem_strength_basis = "十二长生处" + life_stage_name + "，承载偏弱";
        return 0.80;
      default:
        break;
      }
      const auto stem_element = get_wu_xing(pillar.gan);
      const auto branch_element = get_wu_xing(branch_main_stem);
      if (wu_xing_ke(branch_element, stem_element)) {
        stem_strength_basis = "坐支本气克运干，按截脚折减";
        return 0.60;
      }
      if (wu_xing_sheng(branch_element, stem_element)) {
        stem_strength_basis = "坐支本气生运干，承载增强";
        return 1.15;
      }
      if (wu_xing_sheng(stem_element, branch_element)) {
        stem_strength_basis = "运干下泄于坐支，作用略减";
        return 0.85;
      }
      if (wu_xing_ke(stem_element, branch_element)) {
        stem_strength_basis = "运干制坐支，承载略有消耗";
        return 0.90;
      }
      stem_strength_basis = "干支之间无额外承载修正";
      return 1.0;
    }();
    const auto apply_stem = [&](TianGan arriving_stem, std::string_view source,
                                double strength_multiplier) {
      const auto evidence_begin = impact.evidence.size();
      const auto god = get_shi_shen(day_stem, arriving_stem);
      const auto element = get_wu_xing(arriving_stem);
      const std::string subject =
          std::string(source) + stem_name(arriving_stem);
      double contribution = 0.0;
      bool exact_role = false;
      const auto apply_exact =
          [&](const std::optional<ShenCandidate> &candidate, double points,
              std::string_view role) {
            if (!candidate || candidate->stem != arriving_stem)
              return false;
            contribution = points;
            impact.reasons.push_back(subject + "为当前" + std::string(role));
            impact.evidence.push_back(
                {"fortune.exact_stem_role", subject, std::string(role) + "到位",
                 points, "岁运按具体天干匹配五神角色，不以同五行异干替代"});
            return true;
          };
      exact_role = apply_exact(useful.yong_shen, 10.0, "用神") ||
                   apply_exact(useful.xi_shen, 6.0, "喜神") ||
                   apply_exact(useful.ji_shen, -10.0, "忌神") ||
                   apply_exact(useful.chou_shen, -6.0, "仇神");

      if (!exact_role &&
          useful.route == UsefulGodRoute::OfficerProtectsWealth) {
        switch (god) {
        case ShiShen::QiSha: {
          constexpr double attack = -12.0;
          double rescue_score = 0.0;
          std::string rescue = "原局未见足量食神制杀或印星化杀";
          if (active_food_power >= 1.0) {
            rescue_score = 8.0;
            rescue = "原局或同柱食神有效，可制杀但不抹去七杀先攻身";
          } else if (active_resource_power >= 1.0) {
            rescue_score = 6.0;
            rescue = "原局或同柱印星有效，可化杀但仍属有救而先伤";
          }
          contribution = attack + rescue_score;
          impact.reasons.push_back(subject + "为七杀，分干论攻身与制化");
          impact.review_notes.push_back("七杀到位：" + rescue +
                                        "；须结合岁运是否再伤制化之神复核");
          impact.evidence.push_back(
              {"fortune.qi_sha_attack", subject, "七杀攻身", attack,
               "七杀攻身先按不利结构记账；后续制化只另记救应，不冲销先伤"});
          if (rescue_score > 0.0) {
            impact.evidence.push_back(
                {"fortune.qi_sha_rescue", subject,
                 active_food_power >= 1.0 ? "食神制杀" : "印化杀", rescue_score,
                 rescue});
          }
          break;
        }
        case ShiShen::BiJian:
        case ShiShen::JieCai:
        case ShiShen::ZhengCai:
        case ShiShen::PianCai:
        case ShiShen::ZhengYin:
        case ShiShen::PianYin:
        case ShiShen::ShiShen:
        case ShiShen::ShangGuan:
        case ShiShen::ZhengGuan:
          contribution = officer_route_score(god);
          break;
        }
        if (god != ShiShen::QiSha) {
          impact.reasons.push_back(subject + "按官制比护财路线分十神论");
          impact.evidence.push_back(
              {"fortune.route_ten_god", subject,
               std::string(ZhouYi::GanZhi::shi_shen_to_zh(god)), contribution,
               "同五行异十神不沿用同一角色分，按当前主取用路线分别评价"});
        }
      } else if (!exact_role && wu_xing_ke(element, yong_element)) {
        contribution = -8.0;
        impact.reasons.push_back(subject + "克制当前候选用神");
        impact.evidence.push_back({"fortune.control", subject, "克用", -8.0,
                                   "未命中具体五神角色时，以五行克用作兜底"});
      } else if (!exact_role && (element == yong_element ||
                                 (xi_element && element == *xi_element) ||
                                 (ji_element && element == *ji_element) ||
                                 (chou_element && element == *chou_element))) {
        impact.reasons.push_back(subject +
                                 "与五神之一同五行但非同干，不直接套分");
        impact.evidence.push_back(
            {"fortune.same_element_different_stem", subject, "同五行异干", 0.0,
             "仅记录五行方向，不冒充已分配角色的具体天干"});
      }

      if ((god == ShiShen::ShiShen || god == ShiShen::ShangGuan) &&
          wealth_power >= 1.0 && peer_power >= 1.0) {
        const double circulation = god == ShiShen::ShangGuan ? 6.0 : 5.0;
        contribution += circulation;
        impact.reasons.push_back(subject + "另有泄比生财通道");
        impact.evidence.push_back(
            {"fortune.output_generates_wealth", subject, "食伤泄比生财",
             circulation,
             "原局比劫与财星均有实际力量；食伤虽可能损官，也可泄比并转生财"
             "星，吉凶两条通道分别保留"});
      }

      if (std::find(analysis.climate.preferred_stems.begin(),
                    analysis.climate.preferred_stems.end(),
                    arriving_stem) != analysis.climate.preferred_stems.end()) {
        double climate_adjustment = 0.0;
        std::string climate_reason;
        switch (analysis.climate.state) {
        case ClimateState::Missing:
          climate_adjustment = 8.0;
          climate_reason = "原局调候未见，岁运调候字到位，兼作调候补救";
          break;
        case ClimateState::Insufficient:
          climate_adjustment = 4.0;
          climate_reason = "原局调候已见不足，岁运调候字到位，作有限补救";
          break;
        case ClimateState::Satisfied:
          climate_reason =
              "原局调候已备，不重复加调候分；本层仍按其格局十神身份计分";
          break;
        case ClimateState::Excessive:
          climate_adjustment = -6.0;
          climate_reason = "原局调候同类已过量，岁运再临反增偏性";
          break;
        case ClimateState::NotApplicable:
          climate_reason = "当前命局未锁定该干为必要调候字";
          break;
        }
        contribution += climate_adjustment;
        impact.reasons.push_back(subject + "兼具调候与十神双重身份");
        impact.evidence.push_back({"fortune.climate_dual_role", subject,
                                   "调候与格局分层", climate_adjustment,
                                   climate_reason});
      }
      contribution *= strength_multiplier;
      for (auto index = evidence_begin; index < impact.evidence.size(); ++index)
        impact.evidence[index].points *= strength_multiplier;
      if (std::abs(strength_multiplier - 1.0) > 0.01) {
        impact.evidence.push_back(
            {"fortune.stem_branch_strength", subject, "干支力度修正",
             contribution,
             "按透藏层级或干支承载系数修正该作用；" + stem_strength_basis});
      }
      impact.score += contribution;
      return contribution;
    };

    apply_stem(pillar.gan, "运干", stem_strength_multiplier);
    static const std::array<double, 3> hidden_weights = {1.0, 0.6, 0.3};
    static const std::array<std::string_view, 3> hidden_names = {
        "运支本气", "运支中气", "运支余气"};
    double branch_contribution = 0.0;
    for (std::size_t hidden_index = 0; hidden_index < branch_hidden.size();
         ++hidden_index) {
      branch_contribution +=
          apply_stem(branch_hidden[hidden_index], hidden_names[hidden_index],
                     hidden_weights[hidden_index]);
    }
    if (pillar.zhi == kong_wang.branches[0] ||
        pillar.zhi == kong_wang.branches[1]) {
      const std::string fill_nature = branch_contribution > 0.0   ? "吉神填实"
                                      : branch_contribution < 0.0 ? "凶神填实"
                                                                  : "填实待辨";
      impact.reasons.push_back("运支值原局旬空，触发" + fill_nature);
      impact.review_notes.push_back(
          "空亡填实属于应期标记；吉凶随运支本气及原局制化，不重复加减分");
      impact.evidence.push_back({"fortune.kong_wang_filled",
                                 branch_name(pillar.zhi), fill_nature, 0.0,
                                 "岁运地支与原局旬空支同支，记为填实应期"});
    }
    if (get_shi_shen(day_stem, pillar.gan) == ShiShen::ShangGuan &&
        get_shi_shen(day_stem, useful.yong_shen->stem) == ShiShen::ZhengGuan) {
      impact.score -= 10.0;
      impact.reasons.push_back("运干伤官直犯原局官用");
      impact.review_notes.push_back(
          "伤官见官，以天干之克为主，地支喜神不得完全抵销");
      impact.evidence.push_back({"fortune.hurting_officer_meets_officer",
                                 pillar.to_string(), "伤官见官", -10.0,
                                 "运干为伤官且候选用神为正官，另作组合级折减"});
    }
    const bool clashes_month_command =
        is_chong(pillar.zhi, original_pillars[1].zhi);
    if (clashes_month_command) {
      impact.score -= 12.0;
      impact.reasons.push_back("运支冲月令提纲");
      impact.review_notes.push_back(
          "冲提纲主环境与格局根基变动，不按普通冲忌神根作纯加分");
      impact.evidence.push_back(
          {"fortune.clash_month_command",
           branch_name(pillar.zhi) + branch_name(original_pillars[1].zhi),
           "冲提纲", -12.0, "月令为提纲，受冲单列结构风险"});
    }
    const auto original_has_branch = [&](DiZhi branch) {
      return std::any_of(
          original_pillars.begin(), original_pillars.end(),
          [&](const Pillar &original) { return original.zhi == branch; });
    };
    if (!original_has_branch(pillar.zhi)) {
      std::array<std::array<bool, 6>, 2> scored{};
      for (std::size_t first = 0; first < original_pillars.size(); ++first) {
        for (std::size_t second = first + 1; second < original_pillars.size();
             ++second) {
          const auto evaluate_group = [&](bool harmony) {
            const auto [matched, element] =
                harmony
                    ? ZhouYi::GanZhi::is_san_he(original_pillars[first].zhi,
                                                original_pillars[second].zhi,
                                                pillar.zhi)
                    : ZhouYi::GanZhi::is_san_hui(original_pillars[first].zhi,
                                                 original_pillars[second].zhi,
                                                 pillar.zhi);
            const std::size_t kind_index = harmony ? 0 : 1;
            const std::size_t element_index = static_cast<std::size_t>(element);
            if (!matched || scored[kind_index][element_index])
              return;
            scored[kind_index][element_index] = true;
            const std::string group_name = harmony ? "三合局" : "三会局";
            double group_score = 0.0;
            if (element == yong_element)
              group_score = 14.0;
            else if (xi_element && element == *xi_element)
              group_score = 8.0;
            else if (ji_element && element == *ji_element)
              group_score = -14.0;
            else if (chou_element && element == *chou_element)
              group_score = -12.0;
            else if (wu_xing_ke(element, yong_element))
              group_score = -12.0;
            impact.score += group_score;
            impact.reasons.push_back("运支补成原局" + group_name + "，" +
                                     element_name(element) + "气成势");
            impact.review_notes.push_back(
                "岁运参与成局，须按组合后的五行气势复核，不能只看运支本身");
            impact.evidence.push_back(
                {"fortune.complete_branch_group", pillar.to_string(),
                 group_name + element_name(element), group_score,
                 "运支补足原局两支，按组合级作用另行加减"});
            const bool includes_day_branch = first == 2 || second == 2;
            if (includes_day_branch) {
              const bool joins_peer_group =
                  element == get_wu_xing(analysis.day_master);
              const double palace_score = joins_peer_group ? -10.0 : 0.0;
              impact.score += palace_score;
              impact.reasons.push_back(
                  joins_peer_group ? "原局日支被合入比劫局，宫位另行记账"
                                   : "原局日支参与岁运成局，宫位另行记账");
              impact.evidence.push_back(
                  {"fortune.day_branch_joined_group", pillar.to_string(),
                   joins_peer_group ? "日支合入比劫局" : "日支参与成局",
                   palace_score,
                   joins_peer_group
                       ? "日支宫位被岁运合入比劫成势，感情与合作关系另作不利"
                         "折减；不与成局财务分重复归类"
                       : "日支宫位参与成局，先记录关系结构被引动；吉凶仍随成"
                         "局方向复核"});
            }
          };
          evaluate_group(true);
          evaluate_group(false);
        }
      }
    }
    // 原局根被运支冲，才作为明确的减分；普通冲合仍仅保留结构证据。
    for (std::size_t original_index = 0;
         original_index < original_pillars.size(); ++original_index) {
      const auto &original = original_pillars[original_index];
      const auto hidden = get_cang_gan(original.zhi);
      const bool contains_yong_root =
          std::any_of(hidden.begin(), hidden.end(), [&](TianGan hidden) {
            return get_wu_xing(hidden) == yong_element;
          });
      const bool contains_ji_root =
          ji_element &&
          std::any_of(hidden.begin(), hidden.end(), [&](TianGan hidden) {
            return get_wu_xing(hidden) == *ji_element;
          });
      const bool clashes_original = is_chong(pillar.zhi, original.zhi);
      const bool original_is_kong =
          std::find(kong_wang.affected_positions.begin(),
                    kong_wang.affected_positions.end(),
                    static_cast<int>(original_index)) !=
          kong_wang.affected_positions.end();
      if (clashes_original && original_index == 2) {
        impact.score -= 6.0;
        impact.reasons.push_back("运支冲原局日支");
        impact.review_notes.push_back(
            "日支为配偶宫兼日主坐支，受冲不按普通冲忌神根直接作吉论；"
            "按宫位与日主坐支变动单列折减，具体应事另行复核");
        impact.evidence.push_back(
            {"fortune.clash_day_branch",
             pillar.to_string() + branch_name(original.zhi), "冲日支", -6.0,
             "日支受冲按宫位与日主坐支变动单列折减"});
      }
      if (clashes_original && contains_yong_root) {
        impact.score -= 12.0;
        impact.reasons.push_back(original_is_kong ? "运支冲原局旬空用神根"
                                                  : "运支冲原局用神根");
        if (original_is_kong) {
          impact.review_notes.push_back("该用神根原落旬空；冲空有冲起、冲实等不"
                                        "同口径，当前规则从严仍按冲用神根折减");
        }
        impact.evidence.push_back(
            {"fortune.branch_relation",
             pillar.to_string() + branch_name(original.zhi),
             original_is_kong ? "冲旬空用神根" : "冲用神根", -12.0,
             original_is_kong
                 ? "用神根原落旬空；虽有冲起、冲实异说，本规则采用从严折减"
                 : "运支冲击含候选用神根气的原局地支"});
      }
      if (clashes_original && contains_ji_root && original_index != 1 &&
          original_index != 2) {
        impact.score += 8.0;
        impact.reasons.push_back("运支冲原局忌神根");
        impact.evidence.push_back(
            {"fortune.branch_relation",
             pillar.to_string() + branch_name(original.zhi), "冲忌神根", 8.0,
             "运支冲击含候选忌神根气的原局地支"});
      }
      if (ZhouYi::GanZhi::stem_combine_element(pillar.gan, original.gan)) {
        double combine_score = 0.0;
        std::string target = original_index == 2 ? "日主" : "普通天干";
        if (useful.yong_shen && original.gan == useful.yong_shen->stem) {
          combine_score = -6.0;
          target = "用神";
        } else if (useful.xi_shen && original.gan == useful.xi_shen->stem) {
          combine_score = -5.0;
          target = "喜神";
        } else if (useful.ji_shen && original.gan == useful.ji_shen->stem) {
          combine_score = 6.0;
          target = "忌神";
        } else if (useful.chou_shen && original.gan == useful.chou_shen->stem) {
          combine_score = 4.0;
          target = "仇神";
        } else if (std::find(analysis.climate.preferred_stems.begin(),
                             analysis.climate.preferred_stems.end(),
                             original.gan) !=
                   analysis.climate.preferred_stems.end()) {
          combine_score = -8.0;
          target = "调候用字";
        }
        impact.score += combine_score;
        impact.reasons.push_back("运干合原局" + target);
        impact.evidence.push_back(
            {"fortune.stem_relation",
             pillar.to_string() + stem_name(original.gan), "天干五合",
             combine_score,
             "合喜用、调候为牵绊，合忌仇为制约；是否化气仍须另验月令与根气"});

        const auto competing_count =
            std::count_if(original_pillars.begin(), original_pillars.end(),
                          [&](const Pillar &other) {
                            return other.gan != original.gan &&
                                   ZhouYi::GanZhi::stem_combine_element(
                                       other.gan, original.gan);
                          });
        if (competing_count > 0) {
          impact.score -= 6.0;
          impact.reasons.push_back("岁运天干参与原局争合");
          impact.review_notes.push_back(
              "同一原局天干同时受日主或其他天干争合，按用神受牵制重点复核");
          impact.evidence.push_back(
              {"fortune.competing_combine", pillar.to_string(), "争合", -6.0,
               "岁运加入后形成一干多合，不能按普通单一五合处理"});
        }
      }
    }
    build_effect_channels(impact, analysis);
    result.push_back(std::move(impact));
  }
  return result;
}

TransitAnalysis Detail::evaluate_transit(const TransitContext &context,
                                         const AnalysisResult &analysis) {
  TransitAnalysis result;
  result.context = context;
  struct LayerPillar {
    TransitLayer layer;
    Pillar pillar;
  };
  std::vector<LayerPillar> layers;
  if (context.da_yun)
    layers.push_back({TransitLayer::DaYun, *context.da_yun});
  if (context.liu_nian)
    layers.push_back({TransitLayer::LiuNian, *context.liu_nian});
  if (context.liu_yue)
    layers.push_back({TransitLayer::LiuYue, *context.liu_yue});

  const bool is_da_yun_transition_year =
      context.da_yun && context.da_yun_start_year && context.current_year &&
      *context.da_yun_start_year == *context.current_year;

  const auto layer_name = [](TransitLayer layer) -> std::string_view {
    switch (layer) {
    case TransitLayer::DaYun:
      return "大运";
    case TransitLayer::LiuNian:
      return "流年";
    case TransitLayer::LiuYue:
      return "流月";
    }
    return "岁运";
  };
  const auto add = [&](std::string rule, std::string subject, std::string name,
                       double score, std::string reason, bool review = true) {
    result.interactions.push_back({std::move(rule), std::move(subject),
                                   std::move(name), score, std::move(reason),
                                   review});
    return result.interactions.size() - 1;
  };
  struct RelationCandidate {
    std::size_t interaction_index{};
    std::vector<std::size_t> layer_indices;
    std::vector<std::size_t> dependent_indices;
  };
  std::vector<RelationCandidate> relation_candidates;
  const auto directional_ten_god = [&](WuXing element, DiZhi left, DiZhi right,
                                       double strength) {
    std::vector<TianGan> matching_stems;
    const auto collect = [&](DiZhi branch, bool require_branch_element) {
      if (require_branch_element && get_wu_xing(branch) != element)
        return;
      for (const auto stem : get_cang_gan(branch)) {
        if (get_wu_xing(stem) == element) {
          matching_stems.push_back(stem);
          break;
        }
      }
    };
    collect(left, true);
    collect(right, true);
    if (matching_stems.empty()) {
      collect(left, false);
      collect(right, false);
    }
    if (matching_stems.empty())
      return std::pair{0.0,
                       std::string("合势五行未落实到具体藏干，暂不定正偏")};
    const auto god = get_shi_shen(analysis.day_master, matching_stems.front());
    const bool mixed = std::any_of(
        matching_stems.begin() + 1, matching_stems.end(), [&](TianGan stem) {
          return get_shi_shen(analysis.day_master, stem) != god;
        });
    if (mixed)
      return std::pair{
          0.0, std::string("合势同时牵涉正偏十神，按官杀或正偏混见复核")};
    const double score =
        std::clamp(officer_route_score(god) * strength, -6.0, 4.0);
    return std::pair{
        score, "合势按" + std::string(ZhouYi::GanZhi::shi_shen_to_zh(god)) +
                   "评价，不以同五行冒充既定用神本干"};
  };

  for (std::size_t layer_index = 0; layer_index < layers.size();
       ++layer_index) {
    const auto &item = layers[layer_index];
    auto impacts = evaluate_fortunes({item.pillar}, analysis);
    if (!impacts.empty()) {
      relabel_fortune_layer(impacts.front(), item.layer);
      impacts.front().label = std::string(layer_name(item.layer));
      if (item.layer == TransitLayer::DaYun && is_da_yun_transition_year) {
        constexpr double transition_factor = 1.15;
        impacts.front().score *= transition_factor;
        for (auto &evidence : impacts.front().evidence)
          evidence.points *= transition_factor;
        for (auto &channel : impacts.front().channels)
          channel.score *= transition_factor;
        impacts.front().reasons.push_back(
            "交运首年按1.15系数放大大运层既有吉凶作用");
        impacts.front().review_notes.push_back(
            "交运年只放大大运层已经成立的作用，不凭空制造吉凶");
      }
      result.total_score += impacts.front().score;
      result.layer_impacts.push_back(std::move(impacts.front()));
    }
    for (std::size_t index = 0; index < analysis.input_pillars.size();
         ++index) {
      const auto &original = analysis.input_pillars[index];
      if (item.pillar.gan == original.gan && item.pillar.zhi == original.zhi) {
        add("transit.fu_yin",
            std::string(layer_name(item.layer)) + item.pillar.to_string() +
                ZhouYi::BaZiBase::pillar_position_name(index),
            "伏吟", -8.0, "岁运柱与原局柱完全相同，主同一结构反复引动");
      }
      const bool stem_war =
          wu_xing_ke(get_wu_xing(item.pillar.gan), get_wu_xing(original.gan)) ||
          wu_xing_ke(get_wu_xing(original.gan), get_wu_xing(item.pillar.gan));
      if (stem_war && is_chong(item.pillar.zhi, original.zhi)) {
        add("transit.heaven_control_earth_clash",
            item.pillar.to_string() + original.to_string(), "天克地冲", -14.0,
            "岁运与原局柱天干相克且地支相冲，按反吟级结构重点复核");
      }
      if (const auto combine_element =
              ZhouYi::GanZhi::get_he_wu_xing(item.pillar.zhi, original.zhi)) {
        const std::string subject =
            std::string(layer_name(item.layer)) + branch_name(item.pillar.zhi) +
            "与" + ZhouYi::BaZiBase::pillar_position_name(index) +
            branch_name(original.zhi);
        const auto [direction_score, ten_god_reason] = directional_ten_god(
            *combine_element, item.pillar.zhi, original.zhi, 0.5);
        const bool fills_natal_kong =
            item.pillar.zhi == analysis.kong_wang.branches[0] ||
            item.pillar.zhi == analysis.kong_wang.branches[1];
        const auto relation_index = add(
            "transit.branch_six_combine", subject, "地支六合", direction_score,
            "两支相合，传统合化方向为" + element_name(*combine_element) + "；" +
                ten_god_reason + "；当前只按合势评价，不直接认定合化" +
                (fills_natal_kong ? "；岁运支同时填实原局旬空" : ""),
            false);
        relation_candidates.push_back({relation_index, {layer_index}, {}});

        if (index == 1) {
          add("transit.branch_combine_month_command", subject, "六合提纲", -4.0,
              "岁运支合月令提纲，另记提纲受牵绊与事情胶着；不得被合化归向"
              "的有利分完全抵销");
        }
        if (analysis.useful_gods.yong_shen) {
          const auto hidden = get_cang_gan(original.zhi);
          const bool combines_yong_root =
              std::find(hidden.begin(), hidden.end(),
                        analysis.useful_gods.yong_shen->stem) != hidden.end();
          if (combines_yong_root) {
            const auto dependent_index =
                add("transit.branch_combine_useful_root", subject, "合绊用神根",
                    -6.0,
                    "原局该支藏候选用神本干，六合先按根气受牵绊另行折减；是否"
                    "合化仍须复核");
            relation_candidates.back().dependent_indices.push_back(
                dependent_index);
          }
        }
      }
    }
  }

  for (std::size_t left = 0; left < layers.size(); ++left) {
    for (std::size_t right = left + 1; right < layers.size(); ++right) {
      const auto &a = layers[left];
      const auto &b = layers[right];
      const std::string subject =
          std::string(layer_name(a.layer)) + a.pillar.to_string() +
          std::string(layer_name(b.layer)) + b.pillar.to_string();
      if (a.pillar.gan == b.pillar.gan && a.pillar.zhi == b.pillar.zhi) {
        add("transit.same_pillar", subject, "岁运并临", -10.0,
            "两个岁运层干支相同，同一作用重复到位");
      }
      const bool stem_war =
          wu_xing_ke(get_wu_xing(a.pillar.gan), get_wu_xing(b.pillar.gan)) ||
          wu_xing_ke(get_wu_xing(b.pillar.gan), get_wu_xing(a.pillar.gan));
      const bool branch_clash = is_chong(a.pillar.zhi, b.pillar.zhi);
      if (branch_clash) {
        const auto left_god = get_shi_shen(analysis.day_master,
                                           get_cang_gan(a.pillar.zhi).front());
        const auto right_god = get_shi_shen(analysis.day_master,
                                            get_cang_gan(b.pillar.zhi).front());
        const bool peer_involved =
            left_god == ShiShen::BiJian || left_god == ShiShen::JieCai ||
            right_god == ShiShen::BiJian || right_god == ShiShen::JieCai;
        add(peer_involved ? "transit.layer_branch_clash_peer"
                          : "transit.layer_branch_clash",
            subject, "岁运相战（支冲）", -8.0,
            "两个岁运层的地支正冲，阶段气势与当期应事相互冲击；"
            "本条只记事业决裂与迁动，财务和关系须由比劫成局、日支宫位"
            "等具体结构另行归因");
      }
      if (stem_war && branch_clash) {
        add("transit.layer_war", subject, "岁运天克地冲增量", -4.0,
            "岁运支冲已经计入基础折减，天干同时相克再追加相战增量；"
            "两项合计仍相当于完整天克地冲的从严处理");
      }
    }
  }

  for (std::size_t target_index = 0;
       target_index < analysis.input_pillars.size(); ++target_index) {
    const auto target = analysis.input_pillars[target_index].gan;
    std::vector<TianGan> contenders;
    for (std::size_t original_index = 0;
         original_index < analysis.input_pillars.size(); ++original_index) {
      if (original_index == target_index)
        continue;
      const auto stem = analysis.input_pillars[original_index].gan;
      if (ZhouYi::GanZhi::stem_combine_element(stem, target))
        contenders.push_back(stem);
    }
    for (const auto &layer : layers) {
      if (ZhouYi::GanZhi::stem_combine_element(layer.pillar.gan, target))
        contenders.push_back(layer.pillar.gan);
    }
    if (contenders.size() < 2)
      continue;
    const bool same_stem = std::ranges::all_of(
        contenders, [&](TianGan stem) { return stem == contenders.front(); });
    static const std::array<std::string_view, 6> counts = {"零", "一", "二",
                                                           "三", "四", "五"};
    const std::string count_name = contenders.size() < counts.size()
                                       ? std::string(counts[contenders.size()])
                                       : std::to_string(contenders.size());
    const std::string subject =
        same_stem ? count_name + stem_name(contenders.front()) + "争合" +
                        stem_name(target)
                  : count_name + "干争合" + stem_name(target);
    add("transit.joint_competing_combine", subject, "天干争合", 0.0,
        "原局与多层岁运合并后共有" + std::to_string(contenders.size()) +
            "个天干同时争合一干；单层牵绊分不再重复追加，联合层明确其结构");
  }

  std::vector<DiZhi> all_branches;
  for (const auto &pillar : analysis.input_pillars)
    all_branches.push_back(pillar.zhi);
  for (const auto &item : layers)
    all_branches.push_back(item.pillar.zhi);
  for (const auto branch : {DiZhi::Chen, DiZhi::Wu, DiZhi::You, DiZhi::Hai}) {
    if (std::count(all_branches.begin(), all_branches.end(), branch) >= 2) {
      add("transit.self_punishment", branch_name(branch) + branch_name(branch),
          "自刑", -6.0, "原局与岁运或两个岁运层形成自刑");
    }
  }
  const auto has_branch = [&](DiZhi branch) {
    return std::find(all_branches.begin(), all_branches.end(), branch) !=
           all_branches.end();
  };

  struct BranchEntity {
    DiZhi branch;
    std::string label;
    std::optional<std::size_t> layer_index;
  };
  std::vector<BranchEntity> branch_entities;
  for (std::size_t index = 0; index < analysis.input_pillars.size(); ++index) {
    branch_entities.push_back(
        {analysis.input_pillars[index].zhi,
         ZhouYi::BaZiBase::pillar_position_name(index) +
             branch_name(analysis.input_pillars[index].zhi),
         std::nullopt});
  }
  for (std::size_t index = 0; index < layers.size(); ++index) {
    branch_entities.push_back({layers[index].pillar.zhi,
                               std::string(layer_name(layers[index].layer)) +
                                   branch_name(layers[index].pillar.zhi),
                               index});
  }
  for (std::size_t left = 0; left < branch_entities.size(); ++left) {
    for (std::size_t right = left + 1; right < branch_entities.size();
         ++right) {
      const auto &a = branch_entities[left];
      const auto &b = branch_entities[right];
      if (!a.layer_index && !b.layer_index)
        continue;
      const auto half = ZhouYi::GanZhi::get_san_he_half(a.branch, b.branch);
      if (!half || has_branch(half->missing_branch))
        continue;
      const double strength =
          half->kind == ZhouYi::GanZhi::SanHeHalfKind::ShengWang ? 0.50
          : half->kind == ZhouYi::GanZhi::SanHeHalfKind::WangMu  ? 0.35
                                                                 : 0.20;
      const auto [score, ten_god_reason] =
          directional_ten_god(half->element, a.branch, b.branch, strength);
      const std::string relation_name =
          half->kind == ZhouYi::GanZhi::SanHeHalfKind::ShengMu ? "三合拱局"
                                                               : "三合半局";
      const auto relation_index =
          add("transit.branch_half_combine", a.label + "与" + b.label,
              relation_name, score,
              "两支同属" + element_name(half->element) + "三合体系，尚缺" +
                  branch_name(half->missing_branch) + "；" + ten_god_reason +
                  "；不按完整三合局计力");
      result.interactions[relation_index].state = TransitRelationState::Partial;
      result.interactions[relation_index].effectiveness = strength;
      std::vector<std::size_t> participating_layers;
      if (a.layer_index)
        participating_layers.push_back(*a.layer_index);
      if (b.layer_index)
        participating_layers.push_back(*b.layer_index);
      relation_candidates.push_back(
          {relation_index, std::move(participating_layers), {}});
    }
  }

  std::set<std::size_t> contested_relations;
  for (std::size_t layer_index = 0; layer_index < layers.size();
       ++layer_index) {
    std::vector<std::size_t> participating;
    for (const auto &candidate : relation_candidates) {
      if (std::find(candidate.layer_indices.begin(),
                    candidate.layer_indices.end(),
                    layer_index) != candidate.layer_indices.end())
        participating.push_back(candidate.interaction_index);
    }
    bool has_clash = false;
    for (std::size_t entity_index = 0; entity_index < branch_entities.size();
         ++entity_index) {
      const auto &entity = branch_entities[entity_index];
      if (entity.layer_index && *entity.layer_index == layer_index)
        continue;
      if (is_chong(layers[layer_index].pillar.zhi, entity.branch)) {
        has_clash = true;
        break;
      }
    }
    if (participating.size() + (has_clash ? 1U : 0U) <= 1)
      continue;
    contested_relations.insert(participating.begin(), participating.end());
    add("transit.branch_relation_competition",
        std::string(layer_name(layers[layer_index].layer)) +
            branch_name(layers[layer_index].pillar.zhi),
        "合冲竞见", 0.0,
        "同一岁运支同时参与多组合或另受冲，相关合势不得全部按满额成立");
  }
  for (const auto &candidate : relation_candidates) {
    if (!contested_relations.contains(candidate.interaction_index))
      continue;
    contested_relations.insert(candidate.dependent_indices.begin(),
                               candidate.dependent_indices.end());
  }
  for (const auto interaction_index : contested_relations) {
    auto &interaction = result.interactions[interaction_index];
    interaction.score *= 0.5;
    interaction.effectiveness *= 0.5;
    interaction.state = TransitRelationState::Contested;
    interaction.reason += "；因同支合冲竞见，当前合力再折半";
  }

  if (has_branch(DiZhi::Yin) && has_branch(DiZhi::Si) &&
      has_branch(DiZhi::Shen)) {
    add("transit.three_punishment", "寅巳申", "无恩之刑", -12.0,
        "原局与岁运共同补齐寅巳申三刑");
  }
  if (has_branch(DiZhi::Chou) && has_branch(DiZhi::Xu) &&
      has_branch(DiZhi::Wei)) {
    add("transit.three_punishment", "丑戌未", "恃势之刑", -12.0,
        "原局与岁运共同补齐丑戌未三刑；涉及月令时须复核提纲与财库");
  }

  std::vector<TianGan> transit_stems;
  for (const auto &item : layers) {
    transit_stems.push_back(item.pillar.gan);
    const auto hidden = get_cang_gan(item.pillar.zhi);
    transit_stems.insert(transit_stems.end(), hidden.begin(), hidden.end());
  }
  const auto has_god = [&](ShiShen god) {
    return std::any_of(transit_stems.begin(), transit_stems.end(),
                       [&](TianGan stem) {
                         return get_shi_shen(analysis.day_master, stem) == god;
                       });
  };
  if (has_god(ShiShen::QiSha) && has_god(ShiShen::ShiShen)) {
    add("transit.food_controls_killer", "岁运食神与七杀", "食神制杀", 8.0,
        "食神与七杀在同一岁运上下文到位，作动态救应但不抹去先伤");
  } else if (has_god(ShiShen::QiSha) &&
             (has_god(ShiShen::ZhengYin) || has_god(ShiShen::PianYin))) {
    add("transit.seal_transforms_killer", "岁运印星与七杀", "印化杀", 6.0,
        "印星与七杀在同一岁运上下文到位，作有救而先伤", false);
  }
  if (has_god(ShiShen::ShangGuan) &&
      (has_god(ShiShen::ZhengYin) || has_god(ShiShen::PianYin))) {
    add("transit.seal_controls_output", "岁运伤官与印星", "伤官配印", 6.0,
        "印星可约束伤官，但仍须检查印是否被财星损伤", false);
  }
  if ((has_god(ShiShen::ZhengCai) || has_god(ShiShen::PianCai)) &&
      analysis.climate.present && analysis.climate.element == WuXing::Shui) {
    add("transit.wealth_damages_climate_seal", "岁运财星与原局调候水",
        "财坏印并伤调候", -6.0, "土财到位会制约已在原局出现的水印与调候用字");
  }

  double killer_pressure = 0.0;
  for (const auto &item : layers) {
    const double layer_weight = item.layer == TransitLayer::DaYun     ? 1.0
                                : item.layer == TransitLayer::LiuNian ? 0.65
                                                                      : 0.35;
    if (get_shi_shen(analysis.day_master, item.pillar.gan) == ShiShen::QiSha)
      killer_pressure += layer_weight;
    static const std::array<double, 3> hidden_weights = {1.0, 0.6, 0.3};
    const auto hidden = get_cang_gan(item.pillar.zhi);
    for (std::size_t index = 0; index < hidden.size(); ++index) {
      if (get_shi_shen(analysis.day_master, hidden[index]) == ShiShen::QiSha)
        killer_pressure += layer_weight * hidden_weights[index];
    }
  }
  const bool doubled_filled_killer = std::any_of(
      analysis.kong_wang.branches.begin(), analysis.kong_wang.branches.end(),
      [&](DiZhi empty_branch) {
        return std::count_if(
                   layers.begin(), layers.end(), [&](const auto &item) {
                     return item.pillar.zhi == empty_branch &&
                            get_shi_shen(
                                analysis.day_master,
                                get_cang_gan(item.pillar.zhi).front()) ==
                                ShiShen::QiSha;
                   }) >= 2;
      });
  if (killer_pressure >= 1.5) {
    const double escalation = doubled_filled_killer ? -14.0 : -8.0;
    add("transit.multiple_killer_attack", "岁运七杀有效压力",
        doubled_filled_killer ? "双杀填实并叠攻" : "杀势叠临", escalation,
        "七杀有效压力达到" + std::format("{:.2f}", killer_pressure) +
            "；多层七杀按递增风险计，不作简单线性相加" +
            (doubled_filled_killer
                 ? "；同一旬空杀支在大运、流年重复填实，再加重一级"
                 : ""));
  }

  if (is_da_yun_transition_year) {
    add("transit.da_yun_transition_year", "大运起始年", "交运年", 0.0,
        "当前公历年等于大运起始年；大运层既有作用已按1.15系数放大，"
        "用于表达新旧运气交接的不稳定性",
        false);
  }

  double strength_delta = 0.0;
  for (const auto &item : layers) {
    const double layer_weight = item.layer == TransitLayer::DaYun     ? 1.0
                                : item.layer == TransitLayer::LiuNian ? 0.65
                                                                      : 0.35;
    const auto add_strength = [&](TianGan stem, double hidden_weight) {
      const auto god = get_shi_shen(analysis.day_master, stem);
      const bool supports = god == ShiShen::BiJian || god == ShiShen::JieCai ||
                            god == ShiShen::ZhengYin || god == ShiShen::PianYin;
      strength_delta += (supports ? 4.0 : -4.0) * layer_weight * hidden_weight;
    };
    add_strength(item.pillar.gan, 1.0);
    static const std::array<double, 3> hidden_weights = {1.0, 0.6, 0.3};
    const auto hidden = get_cang_gan(item.pillar.zhi);
    for (std::size_t index = 0; index < hidden.size(); ++index)
      add_strength(hidden[index], hidden_weights[index]);
  }
  result.projected_strength =
      std::clamp(analysis.strength.score + strength_delta, 0.0, 100.0);
  const auto strength_band = [](double score) {
    return score < 45.0 ? -1 : score > 55.0 ? 1 : 0;
  };
  result.requires_reselection = strength_band(result.projected_strength) !=
                                strength_band(analysis.strength.score);
  if (result.requires_reselection) {
    add("transit.reselect_useful_god", "岁运后强弱估计", "重新取用复核", 0.0,
        "岁运使日主强弱跨越扶抑阈值，原局静态喜忌不得机械沿用");
  }

  for (const auto &impact : result.layer_impacts) {
    result.channels.insert(result.channels.end(), impact.channels.begin(),
                           impact.channels.end());
  }
  for (const auto &interaction : result.interactions) {
    const auto nature = interaction.score > 0.0 ? TransitEffectNature::Favorable
                        : interaction.score < 0.0
                            ? TransitEffectNature::Adverse
                            : TransitEffectNature::Neutral;
    result.channels.push_back({interaction.rule, interaction.subject,
                               interaction.name, nature, interaction.score,
                               interaction_domains(interaction, analysis),
                               interaction.reason});
  }

  for (std::size_t index = 0; index < 6; ++index)
    result.event_impacts.push_back(
        {static_cast<TransitEventType>(index),
         0.0,
         0.0,
         0.0,
         index != static_cast<std::size_t>(TransitEventType::BodySafety),
         {}});
  for (const auto &channel : result.channels) {
    for (const auto domain : channel.domains) {
      auto &event = result.event_impacts[static_cast<std::size_t>(domain)];
      if (channel.score > 0.0)
        event.favorable_score += channel.score;
      else if (channel.score < 0.0)
        event.adverse_score += -channel.score;
      event.reasons.push_back(channel.name + "：" + channel.reason);
    }
  }
  for (auto &event : result.event_impacts)
    event.net_score = event.favorable_score - event.adverse_score;

  for (std::size_t index = 0; index < 4; ++index) {
    result.finance_impacts.push_back(
        {static_cast<TransitFinanceSubtype>(index), 0.0, 0.0, {}});
  }
  const auto contains_domain = [](const TransitEffectChannel &channel,
                                  TransitEventType domain) {
    return std::find(channel.domains.begin(), channel.domains.end(), domain) !=
           channel.domains.end();
  };
  const bool peer_group_surrounds_wealth =
      std::any_of(result.channels.begin(), result.channels.end(),
                  [&](const TransitEffectChannel &channel) {
                    if (channel.rule != "fortune.complete_branch_group" ||
                        channel.score >= 0.0)
                      return false;
                    const auto group_element =
                        element_from_relation(channel.name);
                    return group_element &&
                           *group_element == get_wu_xing(analysis.day_master);
                  });
  const auto finance_subtype = [&](const TransitEffectChannel &channel) {
    if (channel.score > 0.0)
      return TransitFinanceSubtype::IncomeOpportunity;
    if (channel.rule == "fortune.complete_branch_group") {
      const auto group_element = element_from_relation(channel.name);
      if (group_element && *group_element == get_wu_xing(analysis.day_master))
        return TransitFinanceSubtype::IncomeObstruction;
    }
    if (channel.name == "比肩" || channel.name == "劫财")
      return peer_group_surrounds_wealth
                 ? TransitFinanceSubtype::IncomeObstruction
                 : TransitFinanceSubtype::ExpenseDebt;
    if (channel.rule == "fortune.competing_combine")
      return TransitFinanceSubtype::ExpenseDebt;
    return TransitFinanceSubtype::GeneralChange;
  };
  struct SpillAccumulator {
    double score{};
    std::vector<TransitEventType> domains;
    std::vector<std::string> mechanisms;
  };
  std::map<std::string, SpillAccumulator> spill_accumulators;
  for (const auto &channel : result.channels) {
    if (contains_domain(channel, TransitEventType::WealthDebt)) {
      auto &finance = result.finance_impacts[static_cast<std::size_t>(
          finance_subtype(channel))];
      if (channel.score > 0.0)
        finance.favorable_score += channel.score;
      else if (channel.score < 0.0)
        finance.adverse_score += -channel.score;
      finance.reasons.push_back(channel.name + "：" + channel.reason);
    }

    const bool spill_eligible =
        channel.rule == "fortune.complete_branch_group" ||
        channel.rule == "fortune.day_branch_joined_group" ||
        channel.rule == "fortune.clash_day_branch" ||
        channel.rule == "transit.layer_branch_clash_peer" ||
        channel.rule == "transit.layer_branch_clash";
    if (channel.score >= 0.0 || !spill_eligible)
      continue;
    auto &spill = spill_accumulators[channel.subject];
    spill.score += channel.score;
    spill.domains.insert(spill.domains.end(), channel.domains.begin(),
                         channel.domains.end());
    if (std::find(spill.mechanisms.begin(), spill.mechanisms.end(),
                  channel.name) == spill.mechanisms.end())
      spill.mechanisms.push_back(channel.name);
  }
  for (auto &[source, spill] : spill_accumulators) {
    std::ranges::sort(spill.domains);
    const auto unique_end = std::ranges::unique(spill.domains).begin();
    spill.domains.erase(unique_end, spill.domains.end());
    if (spill.domains.size() < 2)
      continue;
    result.source_spills.push_back(
        {source,
         std::accumulate(spill.mechanisms.begin(), spill.mechanisms.end(),
                         std::string{},
                         [](std::string joined, const std::string &name) {
                           if (!joined.empty())
                             joined += "＋";
                           return joined + name;
                         }),
         spill.score, std::move(spill.domains),
         "同一结构来源经不同规则进入两个以上应事类型，按一源多溢聚合；"
         "并列来源分别保留，不把成局与相战混为一个原因"});
  }

  for (const auto &interaction : result.interactions) {
    result.total_score += interaction.score;
    if (interaction.requires_review)
      result.review_notes.push_back(interaction.name + "：" +
                                    interaction.reason);
  }
  const auto severe_count = std::count_if(
      result.interactions.begin(), result.interactions.end(),
      [](const TransitInteraction &item) { return item.score <= -8.0; });
  const auto adverse_score = [&](TransitEventType type) {
    return result.event_impacts[static_cast<std::size_t>(type)].adverse_score;
  };
  const double body_adverse = adverse_score(TransitEventType::BodySafety);
  const auto max_event = std::ranges::max_element(
      result.event_impacts, {}, &TransitEventImpact::adverse_score);
  const double max_adverse = max_event->adverse_score;
  const auto pressured_domain_count =
      std::count_if(result.event_impacts.begin(), result.event_impacts.end(),
                    [](const TransitEventImpact &event) {
                      return event.adverse_score >= 8.0;
                    });
  const bool single_line_extreme = max_adverse >= 50.0;
  const bool deep_multi_pressure =
      pressured_domain_count >= 3 &&
      (result.total_score <= -40.0 || single_line_extreme);
  if (doubled_filled_killer || single_line_extreme || deep_multi_pressure) {
    result.risk = TransitRiskLevel::Critical;
    if (single_line_extreme)
      result.review_notes.push_back(
          "单线极值：" + std::string(event_type_name(max_event->type)) +
          std::format("{:.1f}，达到50分极值阈值", max_adverse));
    if (deep_multi_pressure)
      result.review_notes.push_back(
          "三个以上应事类型达到显著不利阈值，且兼容结构分不高于-40或"
          "存在单线极值，按深度高压矩阵列重点预警");
  } else if (body_adverse >= 10.0 || max_adverse >= 18.0 ||
             pressured_domain_count >= 3 || severe_count >= 3)
    result.risk = TransitRiskLevel::Warning;
  else if (max_adverse >= 8.0 || severe_count >= 1 ||
           !result.review_notes.empty())
    result.risk = TransitRiskLevel::Watch;
  return result;
}

void Detail::annotate_sample_extremes(std::vector<TransitAnalysis> &analyses) {
  for (auto &analysis : analyses) {
    analysis.sample_peak_type.reset();
    analysis.sample_peak_score = 0.0;
  }
  if (analyses.empty())
    return;

  double deepest_score = 0.0;
  for (const auto &analysis : analyses) {
    for (const auto &event : analysis.event_impacts)
      deepest_score = std::max(deepest_score, event.adverse_score);
  }
  if (deepest_score < 50.0)
    return;

  for (auto &analysis : analyses) {
    const auto deepest_event = std::ranges::max_element(
        analysis.event_impacts, {}, &TransitEventImpact::adverse_score);
    if (deepest_event != analysis.event_impacts.end() &&
        std::abs(deepest_event->adverse_score - deepest_score) < 0.001) {
      analysis.sample_peak_type = deepest_event->type;
      analysis.sample_peak_score = deepest_score;
    }
  }
}

} // namespace ZhouYi::BaZiAnalysis
