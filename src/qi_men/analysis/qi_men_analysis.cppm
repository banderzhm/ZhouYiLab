/**
 * @file qi_men_analysis.cppm
 * @brief 奇门宫位状态与格局闭环分析接口。
 */
export module ZhouYi.QiMen.Analysis;

export import ZhouYi.QiMen.Analysis.Contract;

import ZhouYi.GanZhi;
import ZhouYi.QiMen;
import std;

export namespace ZhouYi::QiMen {

/** 格局分析所需的四柱上下文。 */
struct QiMenAnalysisContext {
  ZhouYi::GanZhi::TianGan year_gan;  ///< 年干，供岁格判断。
  ZhouYi::GanZhi::TianGan month_gan; ///< 月干，供月格判断。
  ZhouYi::GanZhi::DiZhi month_zhi;   ///< 月建，供星门旺衰判断。
  ZhouYi::GanZhi::TianGan day_gan;   ///< 日干，供日格判断。
  ZhouYi::GanZhi::DiZhi day_zhi;     ///< 日支，供旬首遁甲与占断复核。
  ZhouYi::GanZhi::TianGan hour_gan;  ///< 时干，供时格判断。
  ZhouYi::GanZhi::DiZhi hour_zhi;    ///< 时支，供全局格局判断。
};

/**
 * @brief 为盘面补齐门迫、击刑、入墓及常用格局。
 *
 * 每次调用会先清空派生结果，因此同一盘可安全重复分析，不会产生重复格局。
 */
void analyze_qi_men_pan(QiMenPan &pan, const QiMenAnalysisContext &context);

/** 输出格局清单；只列已经满足全部条件的格局。 */
[[nodiscard]] std::string format_qi_men_analysis(const QiMenPan &pan);

} // namespace ZhouYi::QiMen

export namespace ZhouYi::QiMenAnalysis {

/** 依奇门用神、主客和格局制化分析已经起成的盘局。 */
AnalysisResult analyze(const ZhouYi::QiMen::QiMenPan &pan,
                       const AnalysisRequest &request = {});

} // namespace ZhouYi::QiMenAnalysis
