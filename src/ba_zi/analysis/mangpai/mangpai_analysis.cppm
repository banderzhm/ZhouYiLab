// C++23 Module - 盲派八字分析接口
export module ZhouYi.BaZiAnalysis.MangPai;

import ZhouYi.BaZiAnalysis;
import std;

export namespace ZhouYi::BaZiAnalysis::MangPai {

/**
 * @brief 执行盲派理论分析。
 *
 * 该入口只负责盲派的宾主、体用和做功解释；四柱关系由基础关系模块提供。
 */
AnalysisResult analyze(const BaZi &chart, const BirthContext &birth_context,
                       const std::vector<Pillar> &fortunes = {},
                       const AnalysisConfig &config = {});

} // namespace ZhouYi::BaZiAnalysis::MangPai
