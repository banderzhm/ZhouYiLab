/**
 * @file mei_hua_controller.cppm
 * @brief 梅花易数统一起卦与占断入口。
 */
export module ZhouYi.MeiHua.Controller;

export import ZhouYi.MeiHua;
export import ZhouYi.MeiHua.Analysis;
export import ZhouYi.TrueSolarTime;
import std;

export namespace ZhouYi::MeiHuaController {

/** 时间采用的历法。 */
enum class CalendarKind {
  Solar, ///< 公历输入。
  Lunar  ///< 农历输入。
};

/** 时间起卦输入。 */
struct TimeCastingRequest {
  CalendarKind calendar{CalendarKind::Solar};     ///< 输入历法。
  int year{};                                     ///< 年。
  int month{};                                    ///< 月。
  int day{};                                      ///< 日。
  int hour{};                                     ///< 时（0—23）。
  int minute{};                                   ///< 分（0—59）。
  std::optional<ZhouYi::Time::Location> location; ///< 可选地点；缺省不校正。
};

/** 排盘与占断的组合结果。 */
struct MeiHuaAnalysisResult {
  ZhouYi::MeiHua::MeiHuaPan pan;                   ///< 梅花排盘。
  ZhouYi::MeiHuaAnalysis::AnalysisResult analysis; ///< 体用占断。
};

/** 两数起卦；未提供第三数时，以两数之和取动爻。 */
ZhouYi::MeiHua::MeiHuaPan
cast_by_numbers(long long upper_number, long long lower_number,
                std::optional<long long> moving_number = std::nullopt);

/**
 * 按上下字组的笔画合数起卦；动爻数缺省时取两组笔画之和。
 *
 * 字形拆分及繁体笔画数由调用方按所采用的字书口径给出，本模块不暗自换算。
 */
ZhouYi::MeiHua::MeiHuaPan
cast_by_strokes(long long upper_group_strokes, long long lower_group_strokes,
                std::optional<long long> moving_number = std::nullopt);

/**
 * 闻声起卦；声数取上卦，声数加时支序数取下卦并定动爻。
 *
 * @param sound_count 连续可辨的一组声数。
 * @param hour_branch_number 子一至亥十二的时支序数。
 */
ZhouYi::MeiHua::MeiHuaPan cast_by_sound(long long sound_count,
                                        int hour_branch_number);

/** 按农历年月日时数起卦；地点缺省时直接采用输入时刻。 */
ZhouYi::MeiHua::MeiHuaPan cast_by_time(const TimeCastingRequest &request);

/** 两数起卦并完成体用、互变、卦象与应期分析。 */
MeiHuaAnalysisResult
analyze_by_numbers(long long upper_number, long long lower_number,
                   std::optional<long long> moving_number,
                   const ZhouYi::MeiHuaAnalysis::AnalysisRequest &request = {});

/** 字占笔画起卦并完成占断。 */
MeiHuaAnalysisResult
analyze_by_strokes(long long upper_group_strokes, long long lower_group_strokes,
                   std::optional<long long> moving_number,
                   const ZhouYi::MeiHuaAnalysis::AnalysisRequest &request = {});

/** 闻声起卦并完成占断。 */
MeiHuaAnalysisResult
analyze_by_sound(long long sound_count, int hour_branch_number,
                 const ZhouYi::MeiHuaAnalysis::AnalysisRequest &request = {});

/** 时间起卦并完成体用、互变、卦象与应期分析。 */
MeiHuaAnalysisResult analyze_by_time(
    const TimeCastingRequest &time_request,
    const ZhouYi::MeiHuaAnalysis::AnalysisRequest &analysis_request = {});

} // namespace ZhouYi::MeiHuaController
