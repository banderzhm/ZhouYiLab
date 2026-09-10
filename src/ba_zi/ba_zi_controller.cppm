// C++23 Module - 八字应用控制器接口
// 仅负责排盘与分析用例编排，不承担任何控制台或文本展示职责。
export module ZhouYi.BaZiController;

import ZhouYi.BaZi;
import ZhouYi.BaZiAnalysis;
import ZhouYi.BaZiAnalysis.Common;
import ZhouYi.BaZiAnalysis.ZiPing.Contract;
import ZhouYi.BaZiBase;
import ZhouYi.TrueSolarTime;
import std;

export namespace ZhouYi::BaZiController {

using ZhouYi::BaZi::BaZiResult;
using ZhouYi::BaZiAnalysis::AnalysisConfig;
using ZhouYi::BaZiAnalysis::AnalysisRequest;
using ZhouYi::BaZiAnalysis::AnalysisResult;
using ZhouYi::BaZiAnalysis::FortuneImpact;
using ZhouYi::BaZiAnalysis::TransitAnalysis;

/**
 * @brief 按民用时间完成公历八字排盘。
 * @param year 公历年。
 * @param month 公历月，范围 1-12。
 * @param day 公历日。
 * @param hour 民用时小时，范围 0-23。
 * @param minute 分钟，范围 0-59。
 * @param is_male 是否为男命。
 */
BaZiResult pai_pan_solar(int year, int month, int day, int hour, int minute = 0,
                         bool is_male = true);

/**
 * @brief 按可选出生地排盘；未提供 location 时直接使用传入民用时间。
 */
BaZiResult pai_pan_solar(int year, int month, int day, int hour, int minute,
                         bool is_male,
                         const std::optional<ZhouYi::Time::Location> &location);

/** @brief 按农历出生时间完成八字排盘。 */
BaZiResult pai_pan_lunar(int year, int month, int day, int hour, int minute = 0,
                         bool is_male = true);

/** @brief 使用默认子平配置分析排盘结果。 */
AnalysisResult analyze_ba_zi(const BaZiResult &result,
                             const AnalysisConfig &config = {});

/** @brief 根据请求中的流派与出生上下文统一调度分析器。 */
AnalysisResult analyze_ba_zi(const BaZiResult &result,
                             const AnalysisRequest &request);

/** @brief 以子平原局结果分析指定公历流年。 */
FortuneImpact analyze_liu_nian(const BaZiResult &result, int year,
                               const AnalysisConfig &config = {});

/** @brief 联合分析大运、流年和可选流月。 */
TransitAnalysis analyze_sui_yun(
    const BaZiResult &result, const ZhouYi::BaZiBase::Pillar &da_yun, int year,
    const std::optional<ZhouYi::BaZiBase::Pillar> &liu_yue = std::nullopt,
    const AnalysisConfig &config = {});

/** @brief 批量执行只含整点出生信息的公历排盘请求。 */
std::vector<BaZiResult> batch_pai_pan(
    const std::vector<std::tuple<int, int, int, int, bool>> &requests);

} // namespace ZhouYi::BaZiController
