/**
 * @file mei_hua_timing.cppm
 * @brief 梅花易数应期候选推演接口。
 */
export module ZhouYi.MeiHua.Analysis.Timing;

export import ZhouYi.MeiHua.Analysis.Contract;
import std;

export namespace ZhouYi::MeiHuaAnalysis {

/**
 * 依事情迟速、动爻内外、用变旺衰及全卦之数生成五项有序应期。
 *
 * 仅给出象数候选和触发口径，不伪造唯一公历日期。
 */
std::vector<TimingCandidate> build_timing_candidates(
    const ZhouYi::MeiHua::MeiHuaPan &pan, const AnalysisRequest &request,
    const TrigramReading &ben_yong, const TrigramReading &bian_influence);

} // namespace ZhouYi::MeiHuaAnalysis
