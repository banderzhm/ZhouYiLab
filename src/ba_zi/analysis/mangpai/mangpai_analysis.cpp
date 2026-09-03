// C++23 - 盲派八字分析编排实现
module ZhouYi.BaZiAnalysis.MangPai;

import ZhouYi.BaZiAnalysis.MangPai.BingZhu;
import ZhouYi.BaZiAnalysis.MangPai.MuKu;
import ZhouYi.BaZiAnalysis.MangPai.Structure;
import ZhouYi.BaZiAnalysis.MangPai.Transit;
import ZhouYi.BaZiAnalysis.MangPai.ZuoGong;
import ZhouYi.BaZiBase;
import ZhouYi.GanZhi;
import std;

namespace ZhouYi::BaZiAnalysis::MangPai {

namespace {
void build_palaces(AnalysisResult &result, const BaZi &chart) {
  auto &palaces = result.blind_analysis->palaces;
  // 通行盲派口径：年月为宾，日时为主；时柱是结果、子女和晚景的我宫。
  palaces = {{0, "年柱", "宾位", "年柱代表祖上、早年和外部环境"},
             {1, "月柱", "宾位", "月柱代表父母、同辈、平台和工作环境"},
             {2, "日柱", "主位", "日支为身体、家庭和夫妻宫，日干为命主立极点"},
             {3, "时柱", "主位", "时柱代表子女、成果、晚景和最终兑现"}};
}

} // namespace

AnalysisResult analyze(const BaZi &chart, const BirthContext &birth_context,
                       const std::vector<Pillar> &fortunes,
                       const AnalysisConfig &config) {
  AnalysisResult result;
  // 盲派结果已包含宫位、透藏、墓库证据和岁运摘要，使用对应契约版本。
  result.schema_version = "1.1";
  result.rule_set = "mangpai-v1";
  result.method = AnalysisMethod::BlindSchool;
  result.config = config;
  result.birth_context =
      ZhouYi::BaZiAnalysis::resolve_birth_context(birth_context);
  result.input_pillars = ZhouYi::BaZiBase::get_pillars(chart);
  result.day_master = chart.day.gan;
  for (std::size_t index = 0; index < result.input_pillars.size(); ++index) {
    const auto nayin =
        ZhouYi::BaZiBase::calculate_pillar_nayin(result.input_pillars[index]);
    result.nayin[index] = {result.input_pillars[index].to_string(), nayin.name,
                           nayin.element};
  }
  result.blind_analysis.emplace();
  build_palaces(result, chart);

  MuKu::build(result, chart);
  BingZhu::build(result, chart);
  ZuoGong::build(result, chart);
  Structure::build(result);
  Transit::build(result, chart, fortunes);

  if (std::none_of(result.blind_analysis->work_chains.begin(),
                   result.blind_analysis->work_chains.end(),
                   [](const auto &work) { return work.effective; })) {
    const auto warning = "原局未识别出已完成的干支做功链，需人工复核";
    const bool already_reported = std::any_of(
        result.blind_analysis->warnings.begin(),
        result.blind_analysis->warnings.end(), [](const std::string &item) {
          return item.find("做功链") != std::string::npos;
        });
    if (!already_reported)
      result.blind_analysis->warnings.push_back(warning);
  }
  if (!fortunes.empty() && result.blind_analysis->transit_impacts.empty())
    result.blind_analysis->warnings.push_back(
        "已传入岁运，但未识别出可解释的直接引动，需结合流月流日复核");
  return result;
}

} // namespace ZhouYi::BaZiAnalysis::MangPai
