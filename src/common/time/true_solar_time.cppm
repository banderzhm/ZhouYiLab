// C++23 Module - 真太阳时公共输入接口
export module ZhouYi.TrueSolarTime;

import ZhouYi.tyme;
import std;

export namespace ZhouYi::Time {

/** 出生地点及真太阳时修正参数。 */
struct Location {
  double longitude{120.0}; ///< 东经为正，单位：度。
  double latitude{0.0};    ///< 北纬为正，单位：度；用于记录地点。
  int timezone{8};         ///< 法定时区，东八区为 8。
};

/** 原始时间、修正后时间及修正量。 */
struct TrueSolarTime {
  tyme::SolarTime civil_time; ///< 输入的当地民用时间。
  tyme::SolarTime solar_time; ///< 按经度和均时差修正后的真太阳时。
  Location location;          ///< 计算使用的地点。
  double offset_minutes{};     ///< 修正量，正值表示真太阳时晚于民用时。
};

/** 计算真太阳时；不改变输入对象，结果供所有术数模块统一消费。 */
TrueSolarTime correct(int year, int month, int day, int hour, int minute,
                      int second,
                      const std::optional<Location> &location = std::nullopt);

} // namespace ZhouYi::Time
