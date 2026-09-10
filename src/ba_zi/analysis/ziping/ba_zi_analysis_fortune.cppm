// C++23 Module - 八字岁运评估策略接口
export module ZhouYi.BaZiAnalysis.Fortune;

import ZhouYi.BaZiAnalysis.ZiPing.Contract;
import ZhouYi.BaZiBase;
import std;

export namespace ZhouYi::BaZiAnalysis::Detail {

std::vector<ZhouYi::BaZiAnalysis::FortuneImpact>
evaluate_fortunes(const std::vector<ZhouYi::BaZiBase::Pillar> &fortunes,
                  const ZhouYi::BaZiAnalysis::AnalysisResult &analysis);

ZhouYi::BaZiAnalysis::TransitAnalysis
evaluate_transit(const ZhouYi::BaZiAnalysis::TransitContext &context,
                 const ZhouYi::BaZiAnalysis::AnalysisResult &analysis);

void annotate_sample_extremes(
    std::vector<ZhouYi::BaZiAnalysis::TransitAnalysis> &analyses);

} // namespace ZhouYi::BaZiAnalysis::Detail
