// C++23 Module - 八字基础事实实现
module ZhouYi.BaZiBase;

import ZhouYi.WuXingUtils;
import ZhouYi.tyme;
import std;

namespace ZhouYi::BaZiBase {

namespace {
Pillar convert_sixty_cycle_to_pillar(const tyme::SixtyCycle &cycle) {
  return Pillar(cycle.get_heaven_stem().get_name(),
                cycle.get_earth_branch().get_name());
}
} // namespace

Pillar::Pillar(const std::string &stem_str, const std::string &branch_str)
    : Pillar(std::string_view(stem_str), std::string_view(branch_str)) {}

Pillar::Pillar(std::string_view stem_str, std::string_view branch_str) {
  const auto stem = Mapper::from_zh_gan(stem_str);
  if (!stem)
    throw std::invalid_argument("无效的天干: " + std::string(stem_str));
  const auto branch = Mapper::from_zh_zhi(branch_str);
  if (!branch)
    throw std::invalid_argument("无效的地支: " + std::string(branch_str));
  gan = *stem;
  zhi = *branch;
}

std::string pillar_position_name(std::size_t position) {
  static constexpr std::array<std::string_view, 4> names = {"年柱", "月柱",
                                                            "日柱", "时柱"};
  return std::string(names.at(position));
}

std::array<Pillar, 4> get_pillars(const BaZi &chart) {
  return {chart.year, chart.month, chart.day, chart.hour};
}

std::array<TianGan, 4> get_stems(const BaZi &chart) {
  return {chart.year.gan, chart.month.gan, chart.day.gan, chart.hour.gan};
}

std::array<DiZhi, 4> get_branches(const BaZi &chart) {
  return {chart.year.zhi, chart.month.zhi, chart.day.zhi, chart.hour.zhi};
}

BaZi BaZi::from_solar(int year, int month, int day, int hour, int minute,
                      int second) {
  const auto lunar_hour =
      tyme::SolarTime::from_ymd_hms(year, month, day, hour, minute, second)
          .get_lunar_hour();
  const auto eight_char = lunar_hour.get_eight_char();
  const auto year_cycle = eight_char.get_year();
  const auto month_cycle = eight_char.get_month();
  const auto day_cycle = eight_char.get_day();
  const auto hour_cycle = eight_char.get_hour();
  const auto empty = day_cycle.get_extra_earth_branches();
  return BaZi(convert_sixty_cycle_to_pillar(year_cycle),
              convert_sixty_cycle_to_pillar(month_cycle),
              convert_sixty_cycle_to_pillar(day_cycle),
              convert_sixty_cycle_to_pillar(hour_cycle),
              empty.size() > 0 ? empty[0].get_name() : "",
              empty.size() > 1 ? empty[1].get_name() : "");
}

BaZi BaZi::from_solar(int year, int month, int day, int hour, int minute,
                      int second,
                      const std::optional<ZhouYi::Time::Location> &location) {
  const auto corrected =
      ZhouYi::Time::correct(year, month, day, hour, minute, second, location);
  return from_solar(
      corrected.solar_time.get_year(), corrected.solar_time.get_month(),
      corrected.solar_time.get_day(), corrected.solar_time.get_hour(),
      corrected.solar_time.get_minute(), corrected.solar_time.get_second());
}

BaZi BaZi::from_lunar(int year, int month, int day, int hour, int minute,
                      int second) {
  const auto lunar_hour =
      tyme::LunarHour::from_ymd_hms(year, month, day, hour, minute, second);
  const auto eight_char = lunar_hour.get_eight_char();
  const auto year_cycle = eight_char.get_year();
  const auto month_cycle = eight_char.get_month();
  const auto day_cycle = eight_char.get_day();
  const auto hour_cycle = eight_char.get_hour();
  const auto empty = day_cycle.get_extra_earth_branches();
  return BaZi(convert_sixty_cycle_to_pillar(year_cycle),
              convert_sixty_cycle_to_pillar(month_cycle),
              convert_sixty_cycle_to_pillar(day_cycle),
              convert_sixty_cycle_to_pillar(hour_cycle),
              empty.size() > 0 ? empty[0].get_name() : "",
              empty.size() > 1 ? empty[1].get_name() : "");
}

namespace {

DiZhi month_branch_from_term(const tyme::SolarTerm &jie) {
  // 十二节自立春起依次对应寅至丑；tyme 的节索引为奇数。
  const int branch_index =
      ((jie.get_index() - 3) / 2 + static_cast<int>(DiZhi::Yin)) % 12;
  return static_cast<DiZhi>((branch_index + 12) % 12);
}

} // namespace

PillarNayin calculate_pillar_nayin(const Pillar &pillar) {
  const auto cycle = tyme::SixtyCycle::from_name(pillar.to_string());
  const auto name = cycle.get_sound().get_name();
  return {name, ZhouYi::WuXingUtils::element_from_name(name)};
}

HumanCommandInfo calculate_human_command(int year, int month, int day, int hour,
                                         int minute, int second) {
  HumanCommandInfo result;
  const auto birth =
      tyme::SolarTime::from_ymd_hms(year, month, day, hour, minute, second);
  auto jie = birth.get_term();
  while (jie.get_index() % 2 == 0)
    jie = jie.next(-1);
  const auto jie_time = jie.get_julian_day().get_solar_time();
  result.previous_jie = jie.get_name();
  result.days_since_jie =
      static_cast<double>(birth.subtract(jie_time)) / 86400.0;

  const auto segments =
      ZhouYi::GanZhi::get_human_command_segments(month_branch_from_term(jie));
  int boundary = 0;
  for (const auto &segment : segments) {
    boundary += segment.days;
    if (result.days_since_jie < static_cast<double>(boundary)) {
      result.available = true;
      result.stem = segment.stem;
      break;
    }
  }
  if (!result.available && !segments.empty()) {
    result.available = true;
    result.stem = segments.back().stem;
  }
  result.reason = result.available
                      ? "距" + result.previous_jie + " " +
                            std::to_string(result.days_since_jie) +
                            "日，按子平人元司令分日表判" +
                            std::string(Mapper::to_zh(result.stem)) + "司令"
                      : "未取得有效节令分段，不能判人元司令";
  return result;
}

} // namespace ZhouYi::BaZiBase
