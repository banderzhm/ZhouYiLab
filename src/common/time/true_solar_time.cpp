// C++23 - 真太阳时公共计算实现
module ZhouYi.TrueSolarTime;

namespace ZhouYi::Time {

TrueSolarTime correct(const int year, const int month, const int day,
                      const int hour, const int minute, const int second,
                      const std::optional<Location> &location) {
  const auto civil =
      tyme::SolarTime::from_ymd_hms(year, month, day, hour, minute, second);
  // 未提供出生地点时直接采用民用时间，不执行真太阳时计算。
  if (!location)
    return {civil, civil, Location{}, 0.0};
  // 经度时差：每度 4 分钟；均时差采用常用近似式（分钟）。
  static constexpr int month_days[] = {0,  31, 28, 31, 30, 31, 30,
                                       31, 31, 30, 31, 30, 31};
  int day_of_year = civil.get_solar_day().get_day();
  for (int m = 1; m < civil.get_month(); ++m)
    day_of_year += month_days[m];
  const bool leap = civil.get_year() % 4 == 0 && (civil.get_year() % 100 != 0 ||
                                                  civil.get_year() % 400 == 0);
  if (leap && civil.get_month() > 2)
    ++day_of_year;
  const double angle =
      2.0 * 3.14159265358979323846 * (day_of_year - 81.0) / 365.0;
  const double equation_of_time = 9.87 * std::sin(2.0 * angle) -
                                  7.53 * std::cos(angle) -
                                  1.5 * std::sin(angle);
  const double offset =
      4.0 * (location->longitude - 15.0 * location->timezone) +
      equation_of_time;
  const auto adjusted = civil.next(static_cast<int>(offset * 60.0));
  return {civil, adjusted, location.value_or(Location{}), offset};
}

} // namespace ZhouYi::Time
