// C++23 Module - 盲派八字分析接口
export module ZhouYi.BaZiAnalysis.MangPai;

import ZhouYi.BaZiAnalysis.Contract;
import ZhouYi.BaZiAnalysis.ZiPing.Contract;
import ZhouYi.BaZiBase;
import std;

export namespace ZhouYi::BaZiAnalysis::MangPai {

/**
 * @brief 执行盲派理论分析。
 *
 * 该入口只负责盲派的宾主、体用和做功解释；四柱关系由基础关系模块提供。
 */
ZhouYi::BaZiAnalysis::AnalysisResult
analyze(const ZhouYi::BaZiBase::BaZi &chart,
        const ZhouYi::BaZiAnalysis::BirthContext &birth_context,
        const std::vector<ZhouYi::BaZiBase::Pillar> &fortunes = {},
        const ZhouYi::BaZiAnalysis::AnalysisConfig &config = {});

} // namespace ZhouYi::BaZiAnalysis::MangPai
