/**
 * @file mei_hua_analysis.cppm
 * @brief 梅花易数体用占断接口。
 */
export module ZhouYi.MeiHua.Analysis;

export import ZhouYi.MeiHua.Analysis.Contract;

export namespace ZhouYi::MeiHuaAnalysis {

/** 依体用、互变、旺衰和动爻推演梅花卦势。 */
AnalysisResult analyze(const ZhouYi::MeiHua::MeiHuaPan &pan,
                       const AnalysisRequest &request = {});

} // namespace ZhouYi::MeiHuaAnalysis
