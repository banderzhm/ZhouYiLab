// C++23 Module - 大六壬分析统一入口
export module ZhouYi.DaLiuRenAnalysis;

export import ZhouYi.DaLiuRenAnalysis.Contract;

import ZhouYi.DaLiuRen;

export namespace ZhouYi::DaLiuRenAnalysis {

/**
 * @brief 按大六壬八门、四课、三传、类神、课体和应期分析排盘。
 *
 * 本入口不重新起课，不使用单一吉凶分数，也不让神煞越过四课三传定论。
 */
AnalysisResult analyze(const ZhouYi::DaLiuRen::DaLiuRenResult &pan,
                       const AnalysisRequest &request = {});

} // namespace ZhouYi::DaLiuRenAnalysis
