/**
 * @file qi_men_judgment.cppm
 * @brief 奇门主客、宫位与格局合参接口。
 */
export module ZhouYi.QiMen.Analysis.Judgment;

import ZhouYi.QiMen.Analysis.Contract;
import ZhouYi.QiMen;

export namespace ZhouYi::QiMenAnalysis {

/** 依用神落宫、主客生克及门星神仪组合完成占断。 */
AnalysisResult judge(const ZhouYi::QiMen::QiMenPan &pan,
                     const AnalysisRequest &request);

} // namespace ZhouYi::QiMenAnalysis
