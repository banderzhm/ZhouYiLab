// C++23 - 盲派岁运引动实现
module ZhouYi.BaZiAnalysis.MangPai.Transit;

import ZhouYi.BaZiBase;
import ZhouYi.GanZhi;
import std;

namespace ZhouYi::BaZiAnalysis::MangPai::Transit {
namespace {
using namespace ZhouYi::GanZhi;
namespace Mapper = ZhouYi::GanZhi::Mapper;

std::string relation_text(DiZhi fortune, DiZhi original) {
  if (is_chong(fortune, original))
    return "冲";
  if (is_xing(fortune, original))
    return "刑";
  if (is_hai(fortune, original))
    return "穿害";
  if (is_he(fortune, original))
    return "合";
  return {};
}

bool stem_clashes(TianGan first, TianGan second) {
  return wu_xing_ke(get_wu_xing(first), get_wu_xing(second)) ||
         wu_xing_ke(get_wu_xing(second), get_wu_xing(first));
}

std::string format_weight(double value) {
  std::ostringstream output;
  output << std::fixed << std::setprecision(2) << value;
  return output.str();
}
} // namespace

Analysis analyze(const BaZi &chart, const Pillar &fortune, const Pillar &year,
                 int calendar_year, bool transition_year) {
  Analysis result{fortune.to_string(), year.to_string()};
  const auto original = ZhouYi::BaZiBase::get_branches(chart);
  const auto original_stems = ZhouYi::BaZiBase::get_stems(chart);
  const std::array<std::pair<std::string_view, DiZhi>, 4> palace_names = {
      {{"年柱", original[0]},
       {"月柱", original[1]},
       {"日柱", original[2]},
       {"时柱", original[3]}}};
  const std::array<std::pair<std::string_view, Pillar>, 2> layers = {
      {{"大运", fortune}, {"流年", year}}};
  const auto add_unique = [](std::vector<std::string> &items,
                             std::string value) {
    if (std::find(items.begin(), items.end(), value) == items.end())
      items.push_back(std::move(value));
  };
  for (const auto &[layer_name, pillar] : layers) {
    add_unique(result.triggers, std::string(layer_name) + pillar.to_string() +
                                    "进入岁运层，先看其与原局的引动");
    for (const auto &[palace, branch] : palace_names) {
      const auto relation = pillar.zhi == branch
                                ? std::string("伏吟")
                                : relation_text(pillar.zhi, branch);
      if (relation.empty())
        continue;
      add_unique(result.triggers, std::string(layer_name) + pillar.to_string() +
                                      "引动" + std::string(palace) + relation);
      const auto palace_index = static_cast<std::size_t>(
          std::find(original.begin(), original.end(), branch) -
          original.begin());
      if (palace_index < original_stems.size() &&
          stem_clashes(pillar.gan, original_stems[palace_index]) &&
          relation == "冲")
        add_unique(result.triggers,
                   std::string(layer_name) + pillar.to_string() + "与" +
                       std::string(palace) + "天克地冲，继承为组合级重大引动");
      if (palace == "日柱")
        add_unique(result.palaces,
                   std::string(layer_name) + "引动日支，身体与夫妻宫进入应期");
      else if (palace == "时柱")
        add_unique(result.palaces,
                   std::string(layer_name) +
                       "引动时柱主位，子女、成果与兑现主题变化");
      else
        add_unique(result.palaces, std::string(layer_name) + "引动" +
                                       std::string(palace) +
                                       "宾位，外部环境或平台变化");
      const auto hidden_stems = get_cang_gan(branch);
      const auto palace_god =
          hidden_stems.empty()
              ? ShiShen::BiJian
              : get_shi_shen(chart.day.gan, hidden_stems.front());
      add_unique(result.symbols, std::string(palace) + relation + "，牵动" +
                                     std::string(shi_shen_to_zh(palace_god)) +
                                     "：对应该宫的人事与资源主题");
      if (relation == "冲" || relation == "刑" || relation == "穿害")
        add_unique(result.event_types,
                   palace == "日柱" ? "身体/夫妻宫变动" : "环境与关系变动");
      else if (relation == "伏吟")
        add_unique(result.event_types,
                   palace == "日柱" ? "身体/夫妻宫伏吟" : "宫位主题伏吟");
      else
        add_unique(result.event_types,
                   palace == "日柱" ? "夫妻宫合动" : "资源关系牵动");
    }
    const auto stem = get_shi_shen(chart.day.gan, pillar.gan);
    add_unique(result.work_changes, std::string(layer_name) + "天干" +
                                        std::string(Mapper::to_zh(pillar.gan)) +
                                        "为" +
                                        std::string(shi_shen_to_zh(stem)) +
                                        "，进入岁运层并参与原局做功");
    if (stem == ShiShen::ZhengGuan || stem == ShiShen::QiSha)
      add_unique(result.work_changes,
                 std::string(layer_name) +
                     "官杀引动日主合官链，官根空亡决定兑现程度");
    if (stem == ShiShen::ShangGuan)
      add_unique(result.work_changes,
                 std::string(layer_name) +
                     "伤官引动，冲断或制约原局合官主做功链");
    if (stem == ShiShen::ShiShen)
      add_unique(result.work_changes,
                 std::string(layer_name) +
                     "食神引动，泄身生财，不直接冲断合官链");
  }
  if (fortune.zhi == year.zhi && fortune.gan == year.gan)
    add_unique(result.triggers,
               "岁运并临：大运干支与流年干支全同，主题重复并加重");
  else if (fortune.zhi == year.zhi && is_xing(fortune.zhi, year.zhi))
    add_unique(result.triggers, "岁运同支自刑：干异支同，触发自刑而非岁运并临");
  if (is_chong(fortune.zhi, year.zhi))
    add_unique(result.triggers,
               "岁运相战：大运支与流年支相冲，先看决裂、迁动与环境切换");
  const auto cross_relation = relation_text(fortune.zhi, year.zhi);
  if (!cross_relation.empty() && cross_relation != "冲")
    add_unique(result.triggers,
               "岁运支之间" + cross_relation + "，两层主题相互牵动");
  if (stem_clashes(fortune.gan, year.gan) && is_chong(fortune.zhi, year.zhi))
    add_unique(result.triggers, "岁运天克地冲：大运与流年干支同时相克相冲");
  if (transition_year)
    add_unique(result.triggers,
               "交运首年：大运主题首次落地，需观察起运前后变化");
  const auto kong = get_kong_wang(chart.day.gan, chart.day.zhi);
  for (const auto branch : kong)
    if (fortune.zhi == branch || year.zhi == branch)
      add_unique(result.triggers, "岁运填实原局旬空" +
                                      std::string(Mapper::to_zh(branch)) +
                                      "，原局空亡主题进入应期");
  const auto has_branch = [&](DiZhi branch) {
    return std::find(original.begin(), original.end(), branch) !=
               original.end() ||
           fortune.zhi == branch || year.zhi == branch;
  };
  const auto add_triple = [&](DiZhi a, DiZhi b, DiZhi c, std::string text) {
    if (has_branch(a) && has_branch(b) && has_branch(c))
      add_unique(result.triggers, std::move(text));
  };
  add_triple(DiZhi::Hai, DiZhi::Mao, DiZhi::Wei,
             "亥卯未三合木局补齐，比劫成势，财务与关系主题同步放大");
  add_triple(DiZhi::Yin, DiZhi::Si, DiZhi::Shen,
             "寅巳申三刑补齐，刑动主位与外部环境，需重点复核");
  add_triple(DiZhi::Chou, DiZhi::Xu, DiZhi::Wei,
             "丑戌未三刑补齐，墓库与提纲被刑动，需重点复核");
  const auto add_pair = [&](DiZhi a, DiZhi b, std::string text) {
    const bool layer_has_a = fortune.zhi == a || year.zhi == a;
    const bool layer_has_b = fortune.zhi == b || year.zhi == b;
    if ((layer_has_a && has_branch(b)) || (layer_has_b && has_branch(a)))
      add_unique(result.triggers, std::move(text));
  };
  add_pair(DiZhi::Hai, DiZhi::Wei,
           "亥未拱卯：日支与岁运未形成木局半拱，关系与财务取象加强");
  add_pair(DiZhi::Si, DiZhi::You,
           "巳酉半合金：岁运半合引动官杀做功，尚缺丑不作完整三合");
  if ((fortune.gan == TianGan::Yi || year.gan == TianGan::Yi) &&
      chart.year.gan == TianGan::Geng)
    add_unique(result.work_changes,
               "乙木岁运与日主同来争合庚官，主做功链被分夺");
  if ((fortune.gan == TianGan::Ji || year.gan == TianGan::Ji) &&
      chart.hour.gan == TianGan::Jia)
    add_unique(result.work_changes, "己土岁运合时干甲木，财与劫财发生直接做功");
  if (result.event_types.empty())
    result.event_types.push_back("财务、事业和关系需结合具体做功链取象");
  (void)calendar_year;
  return result;
}

void write_zh(std::ostream &output, const Analysis &result) {
  output << "大运：" << result.fortune << "；流年：" << result.year << "\n";
  output << "【岁运引动】\n";
  for (const auto &item : result.triggers)
    output << "- " << item << "\n";
  output << "【宾主变化】\n";
  for (const auto &item : result.palaces)
    output << "- " << item << "\n";
  output << "【做功链变化】\n";
  for (const auto &item : result.work_changes)
    output << "- " << item << "\n";
  output << "【宫位取象】\n";
  for (const auto &item : result.symbols)
    output << "- " << item << "\n";
  output << "【应事类型】\n";
  for (const auto &item : result.event_types)
    output << "- " << item << "\n";
}

void build(AnalysisResult &result, const BaZi &chart,
           const std::vector<Pillar> &fortunes) {
  const auto original = ZhouYi::BaZiBase::get_branches(chart);
  // 旬空已由墓库基础阶段计算，此处只消费统一结果，避免重复推导。
  const auto &kong = result.kong_wang.branches;
  for (const auto &fortune : fortunes) {
    bool triggered = false;
    const auto fortune_god = get_shi_shen(chart.day.gan, fortune.gan);
    result.blind_analysis->transit_impacts.push_back(
        fortune.to_string() + "岁运干" +
        std::string(Mapper::to_zh(fortune.gan)) + "为" +
        std::string(shi_shen_to_zh(fortune_god)) + "，需与运支关系合并判断");
    const auto hidden = get_cang_gan(fortune.zhi);
    for (std::size_t index = 0; index < hidden.size(); ++index) {
      const double weight = index == 0 ? 1.0 : (index == 1 ? 0.6 : 0.3);
      const auto level = index == 0 ? "本气" : (index == 1 ? "中气" : "余气");
      result.blind_analysis->transit_impacts.push_back(
          fortune.to_string() + "运支藏" + level +
          std::string(Mapper::to_zh(hidden[index])) + "（" +
          std::string(
              shi_shen_to_zh(get_shi_shen(chart.day.gan, hidden[index]))) +
          "，基础权重" + format_weight(weight) + "）");
    }
    for (const auto empty_branch : kong) {
      if (fortune.zhi == empty_branch) {
        result.blind_analysis->transit_impacts.push_back(
            fortune.to_string() + "填实原局旬空" +
            std::string(Mapper::to_zh(empty_branch)) +
            "，原局相关吉凶结构进入应期");
        triggered = true;
      } else if (is_chong(fortune.zhi, empty_branch)) {
        result.blind_analysis->transit_impacts.push_back(
            fortune.to_string() + "冲空" +
            std::string(Mapper::to_zh(empty_branch)) +
            "，按冲起处理并保留两种传统口径待复核");
        triggered = true;
      } else if (is_he(fortune.zhi, empty_branch)) {
        result.blind_analysis->transit_impacts.push_back(
            fortune.to_string() + "合空" +
            std::string(Mapper::to_zh(empty_branch)) +
            "，空亡被牵绊，待复核是否填实");
        triggered = true;
      }
    }
    for (int i = 0; i < 4; ++i) {
      if (fortune.zhi == original[static_cast<std::size_t>(i)]) {
        result.blind_analysis->transit_impacts.push_back(
            fortune.to_string() + "与" +
            ZhouYi::BaZiBase::pillar_position_name(i) +
            "伏吟，原有主题重复并加重");
        triggered = true;
      }
      const auto relation =
          relation_text(fortune.zhi, original[static_cast<std::size_t>(i)]);
      if (relation.empty())
        continue;
      triggered = true;
      auto text = fortune.to_string() + "引动" +
                  ZhouYi::BaZiBase::pillar_position_name(i) + "" + relation;
      if (i == 2)
        text += "，涉及身体/夫妻宫";
      else if (i == 1)
        text += "，涉及父母/平台/工作环境";
      result.blind_analysis->transit_impacts.push_back(std::move(text));
      if (is_chong(fortune.zhi, original[static_cast<std::size_t>(i)]))
        result.blind_analysis->transit_impacts.push_back(
            fortune.to_string() + "与" +
            ZhouYi::BaZiBase::pillar_position_name(i) +
            "反吟倾向，先标结构动荡");
    }
    const auto original_stems = ZhouYi::BaZiBase::get_stems(chart);
    for (int i = 0; i < 4; ++i)
      if (stem_clashes(fortune.gan,
                       original_stems[static_cast<std::size_t>(i)]) &&
          is_chong(fortune.zhi, original[static_cast<std::size_t>(i)]))
        result.blind_analysis->transit_impacts.push_back(
            fortune.to_string() + "与" +
            ZhouYi::BaZiBase::pillar_position_name(i) +
            "天克地冲，列为组合级重大引动");
    if (!triggered)
      result.blind_analysis->transit_impacts.push_back(
          fortune.to_string() + "未直接引动原局四支，需结合天干和藏干复核");
  }
}

} // namespace ZhouYi::BaZiAnalysis::MangPai::Transit
