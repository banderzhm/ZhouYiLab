module ZhouYi.QiMen.Controller;

import ZhouYi.BaZiBase;
import ZhouYi.GanZhi;
import ZhouYi.QiMen.Analysis;
import ZhouYi.tyme;
import fmt;

namespace ZhouYi::QiMen {
namespace {
using ZhouYi::GanZhi::DiZhi;
using ZhouYi::GanZhi::TianGan;

void validate_time(int month, int day, int hour, int minute) {
  if (month < 1 || month > 12)
    throw std::invalid_argument("月份必须在 1..12 范围内");
  if (day < 1 || day > 31)
    throw std::invalid_argument("日期必须在 1..31 范围内");
  if (hour < 0 || hour > 23)
    throw std::invalid_argument("小时必须在 0..23 范围内");
  if (minute < 0 || minute > 59)
    throw std::invalid_argument("分钟必须在 0..59 范围内");
}

SolarTerm convert_term(const tyme::SolarTerm &term) {
  const int index = term.get_index();
  if (index < 0 || index > 23)
    throw std::runtime_error("tyme 返回了无效节气序号");
  return static_cast<SolarTerm>(index);
}

auto build_from_solar(int year, int month, int day, int hour, int minute)
    -> std::expected<QiMenPan, std::string> {
  try {
    validate_time(month, day, hour, minute);
    const auto solar =
        tyme::SolarTime::from_ymd_hms(year, month, day, hour, minute, 0);
    const auto lunar_hour = solar.get_lunar_hour();
    const auto eight_char = lunar_hour.get_eight_char();
    const auto year_cycle = eight_char.get_year();
    const auto month_cycle = eight_char.get_month();
    const auto day_cycle = eight_char.get_day();
    const auto hour_cycle = eight_char.get_hour();
    auto generated = QiMenPanGenerator::generate_pan(
        convert_term(solar.get_term()),
        static_cast<TianGan>(day_cycle.get_heaven_stem().get_index()),
        static_cast<DiZhi>(day_cycle.get_earth_branch().get_index()),
        static_cast<TianGan>(hour_cycle.get_heaven_stem().get_index()),
        static_cast<DiZhi>(hour_cycle.get_earth_branch().get_index()));
    if (!generated)
      return generated;

    auto &pan = *generated;
    pan.solar_year = year;
    pan.solar_month = month;
    pan.solar_day = day;
    pan.hour = hour;
    pan.minute = minute;
    const auto lunar_day = lunar_hour.get_lunar_day();
    pan.lunar_year = lunar_day.get_year();
    pan.lunar_month = std::abs(lunar_day.get_month());
    pan.lunar_day = lunar_day.get_day();
    pan.is_leap_month = lunar_day.get_lunar_month().is_leap();
    pan.ba_zi =
        ZhouYi::BaZiBase::BaZi::from_solar(year, month, day, hour, minute, 0);
    analyze_qi_men_pan(
        pan,
        {.year_gan =
             static_cast<TianGan>(year_cycle.get_heaven_stem().get_index()),
         .month_gan =
             static_cast<TianGan>(month_cycle.get_heaven_stem().get_index()),
         .month_zhi =
             static_cast<DiZhi>(month_cycle.get_earth_branch().get_index()),
         .day_gan =
             static_cast<TianGan>(day_cycle.get_heaven_stem().get_index()),
         .day_zhi =
             static_cast<DiZhi>(day_cycle.get_earth_branch().get_index()),
         .hour_gan =
             static_cast<TianGan>(hour_cycle.get_heaven_stem().get_index()),
         .hour_zhi =
             static_cast<DiZhi>(hour_cycle.get_earth_branch().get_index())});
    return generated;
  } catch (const std::exception &error) {
    return std::unexpected(fmt::format("奇门排盘失败：{}", error.what()));
  }
}
} // namespace

auto QiMenController::pai_pan_solar(
    int year, int month, int day, int hour, int minute,
    const std::optional<ZhouYi::Time::Location> &location)
    -> std::expected<QiMenPan, std::string> {
  if (!location)
    return build_from_solar(year, month, day, hour, minute);
  try {
    const auto corrected =
        ZhouYi::Time::correct(year, month, day, hour, minute, 0, location);
    return build_from_solar(
        corrected.solar_time.get_year(), corrected.solar_time.get_month(),
        corrected.solar_time.get_day(), corrected.solar_time.get_hour(),
        corrected.solar_time.get_minute());
  } catch (const std::exception &error) {
    return std::unexpected(fmt::format("真太阳时修正失败：{}", error.what()));
  }
}

auto QiMenController::pai_pan_lunar(
    int year, int month, int day, int hour, int minute,
    const std::optional<ZhouYi::Time::Location> &location)
    -> std::expected<QiMenPan, std::string> {
  try {
    if (month == 0 || month < -12 || month > 12)
      return std::unexpected("农历月份必须为 -12..-1 或 1..12");
    if (day < 1 || day > 30 || hour < 0 || hour > 23 || minute < 0 ||
        minute > 59)
      return std::unexpected("农历日期或时分超出有效范围");
    const auto lunar =
        tyme::LunarHour::from_ymd_hms(year, month, day, hour, minute, 0);
    const auto solar = lunar.get_solar_time();
    return pai_pan_solar(solar.get_year(), solar.get_month(), solar.get_day(),
                         solar.get_hour(), solar.get_minute(), location);
  } catch (const std::exception &error) {
    return std::unexpected(fmt::format("农历转换失败：{}", error.what()));
  }
}

auto QiMenController::query_palace_info(const QiMenPan &pan, Palace palace)
    -> std::expected<PalaceInfo, std::string> {
  const auto number = get_number_from_palace(palace);
  if (number < 1 || number > 9)
    return std::unexpected("无效宫位");
  return pan.palaces[number - 1];
}
std::string QiMenController::get_pan_description(const QiMenPan &pan) {
  return format_qi_men_pan(pan);
}
std::string QiMenController::get_pan_json(const QiMenPan &pan) {
  nlohmann::json json = pan;
  return json.dump(2);
}
std::string QiMenController::get_pan_json_ordered(const QiMenPan &pan) {
  return get_pan_json(pan);
}
nlohmann::json QiMenController::get_pan_json_object(const QiMenPan &pan) {
  return pan;
}
bool QiMenController::is_same_pan(const QiMenPan &left,
                                  const QiMenPan &right) noexcept {
  return left.dun == right.dun && left.yuan == right.yuan &&
         left.ju == right.ju && left.solar_term == right.solar_term &&
         left.xun_shou == right.xun_shou &&
         left.zhi_fu_palace == right.zhi_fu_palace &&
         left.zhi_shi_palace == right.zhi_shi_palace;
}

auto QiMenAnalyzer::analyze_auspiciousness(const QiMenPan &pan, Palace palace)
    -> std::expected<std::string, std::string> {
  const auto queried = QiMenController::query_palace_info(pan, palace);
  if (!queried)
    return std::unexpected(queried.error());
  const auto &p = *queried;
  return fmt::format("{}：{}、{}、{}，天盘{}加地盘{}。", palace_name(p.palace),
                     spirit_name(p.spirit), star_name(p.star),
                     gate_name(p.gate),
                     ZhouYi::GanZhi::Mapper::to_zh(p.tian_gan),
                     ZhouYi::GanZhi::Mapper::to_zh(p.di_gan));
}
std::string QiMenAnalyzer::get_summary(const QiMenPan &pan) {
  return fmt::format(
      "【奇门遁甲排盘摘要】\n节气：{}\n遁局：{}{}局 "
      "{}\n旬首：{}\n值符：{}落{}\n值使：{}落{}\n",
      solar_term_name(pan.solar_term), pan.dun == Dun::Yang ? "阳遁" : "阴遁",
      pan.ju, yuan_name(pan.yuan), jia_xun_name(pan.xun_shou),
      star_name(pan.zhi_fu_star), palace_name(pan.zhi_fu_palace),
      gate_name(pan.zhi_shi_gate), palace_name(pan.zhi_shi_palace));
}
} // namespace ZhouYi::QiMen
