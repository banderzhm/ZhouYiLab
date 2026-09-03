// C++23 Module - 盲派岁运引动接口
export module ZhouYi.BaZiAnalysis.MangPai.Transit;

import ZhouYi.BaZiAnalysis;
import std;

export namespace ZhouYi::BaZiAnalysis::MangPai::Transit {

/** 盲派岁运分析结果；不包含子平强弱、用神或量化评分字段。 */
struct Analysis {
  std::string fortune;                   ///< 大运干支。
  std::string year;                      ///< 流年干支。
  std::vector<std::string> triggers;     ///< 岁运引动的干支关系。
  std::vector<std::string> palaces;      ///< 宾主及宫位变化。
  std::vector<std::string> work_changes; ///< 做功链变化。
  std::vector<std::string> symbols;      ///< 盲派取象。
  std::vector<std::string> event_types;  ///< 应事类型。
};

/** 按“引动—宾主—做功—宫位—应事”分析单个大运流年。 */
Analysis analyze(const BaZi &chart, const Pillar &fortune, const Pillar &year,
                 int calendar_year, bool transition_year = false);

/** 输出盲派岁运中文报告。 */
void write_zh(std::ostream &output, const Analysis &result);

/** 将传入的大运、流年与原局关系转换为盲派应期摘要。 */
void build(AnalysisResult &result, const BaZi &chart,
           const std::vector<Pillar> &fortunes);

} // namespace ZhouYi::BaZiAnalysis::MangPai::Transit
