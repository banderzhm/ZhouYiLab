// C++23 Module - 八字岁运评估策略接口
export module ZhouYi.BaZiAnalysis.Fortune;

import ZhouYi.BaZiAnalysis;
import std;

export namespace ZhouYi::BaZiAnalysis::Detail {

std::vector<FortuneImpact>
evaluate_fortunes(const std::vector<Pillar> &fortunes,
                  const AnalysisResult &analysis);

TransitAnalysis evaluate_transit(const TransitContext &context,
                                 const AnalysisResult &analysis);

void annotate_sample_extremes(std::vector<TransitAnalysis> &analyses);

} // namespace ZhouYi::BaZiAnalysis::Detail
