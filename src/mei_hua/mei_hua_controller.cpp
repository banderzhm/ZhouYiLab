/**
 * @file mei_hua_controller.cpp
 * @brief 梅花易数统一起卦与占断入口实现。
 */
module ZhouYi.MeiHua.Controller;

import ZhouYi.MeiHua;
import ZhouYi.MeiHua.Analysis;
import ZhouYi.TrueSolarTime;
import ZhouYi.tyme;
import ZhouYi.GanZhi;
import fmt;
import std;

namespace ZhouYi::MeiHuaController {
namespace {
long long checked_positive_sum(long long left, long long right,
                               std::string_view context) {
  if (left <= 0 || right <= 0)
    throw std::invalid_argument(std::string(context) + "须使用正数");
  if (left > std::numeric_limits<long long>::max() - right)
    throw std::overflow_error(std::string(context) + "超出象数范围");
  return left + right;
}

int branch_number_from_hour(int hour) { return ((hour + 1) / 2) % 12 + 1; }

int branch_number_from_year(int lunar_year) {
  const int offset = (lunar_year - 4) % 12;
  return (offset < 0 ? offset + 12 : offset) + 1;
}

std::string branch_name(int number) {
  return std::string(ZhouYi::GanZhi::Mapper::to_zh(
      static_cast<ZhouYi::GanZhi::DiZhi>(number - 1)));
}

std::string solar_time_text(const tyme::SolarTime &time) {
  return fmt::format("{:04}-{:02}-{:02} {:02}:{:02}", time.get_year(),
                     time.get_month(), time.get_day(), time.get_hour(),
                     time.get_minute());
}
} // namespace

ZhouYi::MeiHua::MeiHuaPan
cast_by_numbers(long long upper_number, long long lower_number,
                std::optional<long long> moving_number) {
  if (upper_number <= 0 || lower_number <= 0 ||
      (moving_number && *moving_number <= 0))
    throw std::invalid_argument("梅花象数须为正数");
  const auto movement =
      moving_number
          ? *moving_number
          : checked_positive_sum(upper_number, lower_number, "两数起卦合数");
  auto pan = ZhouYi::MeiHua::compose_pan(
      moving_number ? ZhouYi::MeiHua::CastingMethod::ThreeNumbers
                    : ZhouYi::MeiHua::CastingMethod::TwoNumbers,
      upper_number, lower_number, movement);
  pan.source_numbers = {upper_number, lower_number};
  if (moving_number)
    pan.source_numbers.push_back(*moving_number);
  return pan;
}

ZhouYi::MeiHua::MeiHuaPan
cast_by_strokes(long long upper_group_strokes, long long lower_group_strokes,
                std::optional<long long> moving_number) {
  if (upper_group_strokes <= 0 || lower_group_strokes <= 0 ||
      (moving_number && *moving_number <= 0))
    throw std::invalid_argument("字占笔画合数须为正数");
  const auto movement =
      moving_number ? *moving_number
                    : checked_positive_sum(upper_group_strokes,
                                           lower_group_strokes, "字占笔画合数");
  auto pan = ZhouYi::MeiHua::compose_pan(
      ZhouYi::MeiHua::CastingMethod::StrokeCount, upper_group_strokes,
      lower_group_strokes, movement);
  pan.source_numbers = {upper_group_strokes, lower_group_strokes};
  if (moving_number)
    pan.source_numbers.push_back(*moving_number);
  return pan;
}

ZhouYi::MeiHua::MeiHuaPan cast_by_sound(long long sound_count,
                                        int hour_branch_number) {
  if (sound_count <= 0)
    throw std::invalid_argument("闻声起卦的声数须为正数");
  if (hour_branch_number < 1 || hour_branch_number > 12)
    throw std::invalid_argument("闻声起卦的时支序数须在一至十二之间");
  const long long total =
      checked_positive_sum(sound_count, hour_branch_number, "闻声起卦合数");
  auto pan = ZhouYi::MeiHua::compose_pan(
      ZhouYi::MeiHua::CastingMethod::SoundCount, sound_count, total, total);
  pan.source_numbers = {sound_count, hour_branch_number};
  pan.hour_branch_number = hour_branch_number;
  return pan;
}

ZhouYi::MeiHua::MeiHuaPan cast_by_time(const TimeCastingRequest &request) {
  const auto solar_day =
      request.calendar == CalendarKind::Lunar
          ? tyme::LunarDay::from_ymd(request.year, request.month, request.day)
                .get_solar_day()
          : tyme::SolarDay::from_ymd(request.year, request.month, request.day);
  const auto civil = tyme::SolarTime::from_ymd_hms(
      solar_day.get_year(), solar_day.get_month(), solar_day.get_day(),
      request.hour, request.minute, 0);
  const auto correction = ZhouYi::Time::correct(
      civil.get_year(), civil.get_month(), civil.get_day(), civil.get_hour(),
      civil.get_minute(), 0, request.location);
  const auto actual = correction.solar_time;
  const auto lunar = actual.get_solar_day().get_lunar_day();
  const auto lunar_month_value = lunar.get_lunar_month();
  const int lunar_year = lunar.get_year();
  const int lunar_month = lunar_month_value.get_month();
  const bool is_leap_month = lunar_month_value.is_leap();
  const int lunar_day = lunar.get_day();
  const int year_branch = branch_number_from_year(lunar_year);
  const int hour_branch = branch_number_from_hour(actual.get_hour());
  const long long upper_sum = year_branch + lunar_month + lunar_day;
  const long long total_sum = upper_sum + hour_branch;
  auto pan =
      ZhouYi::MeiHua::compose_pan(ZhouYi::MeiHua::CastingMethod::LunarTime,
                                  upper_sum, total_sum, total_sum);
  pan.source_numbers = {year_branch, lunar_month, lunar_day, hour_branch};
  pan.civil_time = solar_time_text(civil);
  pan.casting_time = solar_time_text(actual);
  pan.lunar_date = fmt::format("农历{}年{}{}月{}日{}时", lunar_year,
                               is_leap_month ? "闰" : "", lunar_month,
                               lunar_day, branch_name(hour_branch));
  pan.lunar_month = lunar_month;
  pan.lunar_leap_month = is_leap_month;
  pan.year_branch_number = year_branch;
  pan.hour_branch_number = hour_branch;
  pan.true_solar_time_applied = request.location.has_value();
  pan.time_offset_minutes = correction.offset_minutes;
  return pan;
}

MeiHuaAnalysisResult
analyze_by_numbers(long long upper_number, long long lower_number,
                   std::optional<long long> moving_number,
                   const ZhouYi::MeiHuaAnalysis::AnalysisRequest &request) {
  auto pan = cast_by_numbers(upper_number, lower_number, moving_number);
  auto analysis = ZhouYi::MeiHuaAnalysis::analyze(pan, request);
  return {.pan = std::move(pan), .analysis = std::move(analysis)};
}

MeiHuaAnalysisResult
analyze_by_strokes(long long upper_group_strokes, long long lower_group_strokes,
                   std::optional<long long> moving_number,
                   const ZhouYi::MeiHuaAnalysis::AnalysisRequest &request) {
  auto pan =
      cast_by_strokes(upper_group_strokes, lower_group_strokes, moving_number);
  auto analysis = ZhouYi::MeiHuaAnalysis::analyze(pan, request);
  return {.pan = std::move(pan), .analysis = std::move(analysis)};
}

MeiHuaAnalysisResult
analyze_by_sound(long long sound_count, int hour_branch_number,
                 const ZhouYi::MeiHuaAnalysis::AnalysisRequest &request) {
  auto pan = cast_by_sound(sound_count, hour_branch_number);
  auto analysis = ZhouYi::MeiHuaAnalysis::analyze(pan, request);
  return {.pan = std::move(pan), .analysis = std::move(analysis)};
}

MeiHuaAnalysisResult analyze_by_time(
    const TimeCastingRequest &time_request,
    const ZhouYi::MeiHuaAnalysis::AnalysisRequest &analysis_request) {
  auto pan = cast_by_time(time_request);
  auto analysis = ZhouYi::MeiHuaAnalysis::analyze(pan, analysis_request);
  return {.pan = std::move(pan), .analysis = std::move(analysis)};
}

} // namespace ZhouYi::MeiHuaController
