/**
 * @file mei_hua_omen.cppm
 * @brief 梅花易数外应参断接口。
 */
export module ZhouYi.MeiHua.Analysis.Omen;

export import ZhouYi.MeiHua.Analysis.Contract;
import std;

export namespace ZhouYi::MeiHuaAnalysis {

/** 将起卦同时所得外应归入八卦，并与体卦合参。 */
std::vector<OmenReading>
analyze_external_omens(const ZhouYi::MeiHua::MeiHuaPan &pan,
                       const std::vector<ExternalOmen> &omens);

} // namespace ZhouYi::MeiHuaAnalysis
