module;
#include "vsop_coefficients.inc"
module ZhouYi.Astrology.PlanetaryOrbit;
import std;

namespace ZhouYi::Astrology {
OrbitVector heliocentric_position(Planet planet, double tt_days) {
  const auto index = static_cast<int>(planet);
  if (index < 0 || index >= 8 || !std::isfinite(tt_days) ||
      tt_days < -36524.5 || tt_days > 36890.5)
    throw std::invalid_argument("行星或 TT 日数超出支持范围");
  const double t = tt_days / 365250.0;
  double coordinates[3]{};
  // 系数按坐标、幂次分组，逐组求和后再乘时间幂。
  for (const auto &group : orbit_groups[index]) {
    double sum = 0.0;
    for (int i = group.begin; i < group.end; ++i) {
      const auto &term = orbit_terms[i];
      sum += term.amplitude * std::cos(term.phase + term.frequency * t);
    }
    const double value = sum * std::pow(t, group.power);
    coordinates[group.coordinate] +=
        group.coordinate == 0 ? std::fmod(value, 2.0 * std::numbers::pi)
                              : value;
  }
  const double projected = coordinates[2] * std::cos(coordinates[1]);
  const double x = projected * std::cos(coordinates[0]);
  const double y = projected * std::sin(coordinates[0]);
  const double z = coordinates[2] * std::sin(coordinates[1]);
  // VSOP 黄道框架至 FK5 J2000 赤道框架的固定旋转系数。
  return {x + 0.000000440360 * y - 0.000000190919 * z,
          -0.000000479966 * x + 0.917482137087 * y - 0.397776982902 * z,
          0.397776982902 * y + 0.917482137087 * z};
}
} // namespace ZhouYi::Astrology
