module;
#include "astronomy.h"
#include "erfa.h"
module ZhouYi.Astrology;
import std;
import ZhouYi.Astrology.JplEphemeris;

namespace ZhouYi::Astrology {
namespace {
constexpr double rad = std::numbers::pi / 180.0;
constexpr double epoch = 2451545.0;
// Astronomy Engine has internal mutable planetary caches. Serialize calls here.
std::recursive_mutex engine_mutex;
std::optional<double> active_delta_t;
std::optional<std::string> active_ephemeris_file;
double configured_delta_t(double ut) {
  return active_delta_t.value_or(Astronomy_DeltaT_EspenakMeeus(ut));
}
// 全局后端回调只在互斥锁内调整；异常和嵌套调用均恢复原时间配置。
class DeltaTGuard {
  std::optional<double> previous_;
  std::optional<std::string> previous_file_;

public:
  explicit DeltaTGuard(std::optional<double> value,
                       std::optional<std::string> file = std::nullopt)
      : previous_(active_delta_t), previous_file_(active_ephemeris_file) {
    if (value && (!std::isfinite(*value) || std::abs(*value) > 10000))
      throw std::invalid_argument("ΔT 必须为绝对值不超过 10000 的有限秒数");
    active_delta_t = value;
    active_ephemeris_file = std::move(file);
    Astronomy_SetDeltaTFunction(configured_delta_t);
  }
  ~DeltaTGuard() {
    active_delta_t = previous_;
    active_ephemeris_file = std::move(previous_file_);
    Astronomy_SetDeltaTFunction(previous_ ? configured_delta_t
                                          : Astronomy_DeltaT_EspenakMeeus);
  }
};
constexpr std::array bodies{
    BODY_SUN,     BODY_MOON,   BODY_MERCURY, BODY_VENUS,   BODY_MARS,
    BODY_JUPITER, BODY_SATURN, BODY_URANUS,  BODY_NEPTUNE, BODY_PLUTO};
double signed_angle(double a) { return std::remainder(a, 360.0); }
void validate_time(double jd) {
  if (!std::isfinite(jd) || jd < 2415020.5 || jd >= 2488434.5)
    throw std::invalid_argument("UTC 儒略日超出 1900—2100 支持范围");
}
struct EclipticPosition {
  double elon, elat, distance_au;
};
EclipticPosition position(Body body, double jd) {
  validate_time(jd);
  const int i = static_cast<int>(body);
  if (i < 0 || i >= 10)
    throw std::invalid_argument("未知星体");
  if (active_ephemeris_file) {
    const auto time = Astronomy_TimeFromDays(jd - epoch);
    const auto p = JplEphemeris::apparent_position(*active_ephemeris_file, i,
                                                   time.tt, time.ut);
    return {p.longitude, p.latitude, p.distance_au};
  }
  auto vector = Astronomy_GeoVector(
      bodies[i], Astronomy_TimeFromDays(jd - epoch), ABERRATION);
  auto result = Astronomy_Ecliptic(vector);
  if (result.status != ASTRO_SUCCESS)
    throw std::runtime_error("天文位置计算失败");
  return {result.elon, result.elat,
          std::hypot(result.vec.x, result.vec.y, result.vec.z)};
}
int house_at(double longitude, const std::array<double, 12> &cusps) {
  for (int i = 0; i < 12; ++i) {
    const double length = normalize_longitude(cusps[(i + 1) % 12] - cusps[i]);
    if (normalize_longitude(longitude - cusps[i]) < length)
      return i + 1;
  }
  throw std::runtime_error("宫头无法覆盖黄道");
}
void assign_positions(Chart &chart) {
  for (auto &p : chart.bodies) {
    p.longitude = normalize_longitude(p.longitude);
    p.sign = static_cast<int>(p.longitude / 30.0);
    p.house = house_at(p.longitude, chart.cusps);
  }
}
double midpoint(double a, double b) {
  if (std::abs(std::abs(signed_angle(b - a)) - 180.0) < 1e-10)
    return normalize_longitude(std::min(a, b) + 90.0);
  return normalize_longitude(a + signed_angle(b - a) / 2.0);
}
void compatible(const Chart &a, const Chart &b) {
  if (a.request.ayanamsa.has_value() != b.request.ayanamsa.has_value() ||
      a.request.houses != b.request.houses)
    throw std::invalid_argument("参与星盘须采用相同黄道类型与宫制");
}
void validate_rules(std::span<const AspectRule> rules) {
  std::vector<double> angles;
  for (const auto &r : rules) {
    if (!std::isfinite(r.angle) || !std::isfinite(r.orb) || r.angle < 0 ||
        r.angle > 180 || r.orb < 0 || r.orb > 15 ||
        std::ranges::find(angles, r.angle) != angles.end())
      throw std::invalid_argument("相位角、容许度或重复规则无效");
    angles.push_back(r.angle);
  }
}
void append_aspect(std::vector<Aspect> &output, const BodyPosition &a,
                   const BodyPosition &b, std::span<const AspectRule> rules) {
  const double delta = signed_angle(b.longitude - a.longitude);
  const double distance = std::abs(delta);
  const AspectRule *selected = nullptr;
  double best = std::numeric_limits<double>::infinity();
  for (const auto &rule : rules) {
    const double orb = std::abs(distance - rule.angle);
    if (orb <= rule.orb && orb < best) {
      best = orb;
      selected = &rule;
    }
  }
  if (selected) {
    const double projected =
        std::abs(signed_angle(delta + (b.speed - a.speed) * 0.001));
    const double change = std::abs(projected - selected->angle) - best;
    const bool stationary = std::abs(change) < 1e-6;
    output.push_back({a.body, b.body, selected->angle, distance, best,
                      !stationary && change < 0, stationary});
  }
}
} // namespace
double normalize_longitude(double degrees) {
  if (!std::isfinite(degrees))
    throw std::invalid_argument("角度必须为有限数值");
  const double result = std::fmod(degrees, 360.0);
  return result < 0 ? result + 360 : result;
}
double civil_to_julian(int year, int month, int day, int hour, int minute,
                       double second, int offset) {
  const std::chrono::year_month_day date{std::chrono::year(year),
                                         std::chrono::month(month),
                                         std::chrono::day(day)};
  if (year < 1900 || year > 2100 || month < 1 || month > 12 || day < 1 ||
      day > 31 || !date.ok() || hour < 0 || hour > 23 || minute < 0 ||
      minute > 59 || !std::isfinite(second) || second < 0 || second >= 60 ||
      offset < -840 || offset > 840)
    throw std::invalid_argument("民用日期、时间或 UTC 偏移无效");
  const auto days = std::chrono::sys_days(date).time_since_epoch().count();
  const double jd =
      2440587.5 + static_cast<double>(days) +
      (hour * 3600 + minute * 60 + second - offset * 60) / 86400.0;
  validate_time(jd);
  return jd;
}
double body_longitude(Body body, double jd, std::optional<double> delta_t,
                      std::optional<std::string> file) {
  std::lock_guard lock(engine_mutex);
  DeltaTGuard time_model(delta_t, std::move(file));
  return position(body, jd).elon;
}
Chart calculate_chart(const ChartRequest &request) {
  std::lock_guard lock(engine_mutex);
  DeltaTGuard time_model(request.delta_t_seconds, request.ephemeris_file);
  validate_time(request.julian_day_utc);
  if (!std::isfinite(request.latitude) || !std::isfinite(request.longitude) ||
      std::abs(request.latitude) >= 90 || std::abs(request.longitude) > 180 ||
      (request.ayanamsa && (!std::isfinite(*request.ayanamsa) ||
                            *request.ayanamsa < 0 || *request.ayanamsa >= 360)))
    throw std::invalid_argument("经纬度或岁差值无效");
  if (request.houses != HouseSystem::WholeSign &&
      request.houses != HouseSystem::Equal &&
      request.houses != HouseSystem::Placidus)
    throw std::invalid_argument("未知宫制");
  Chart chart{};
  chart.request = request;
  chart.ephemeris_model = request.ephemeris_file ? "JPL SPK Type 2 + ERFA 2.0.1"
                                                 : "Astronomy Engine v2.1.19";
  chart.sources.push_back(request);
  auto time = Astronomy_TimeFromDays(request.julian_day_utc - epoch);
  double dpsi, deps;
  eraNut06a(epoch, time.tt, &dpsi, &deps);
  const double eps = eraObl06(epoch, time.tt) + deps;
  chart.delta_t_seconds = configured_delta_t(time.ut);
  const double theta =
      normalize_longitude(eraGst06a(epoch, time.ut, epoch, time.tt) / rad +
                          request.longitude) *
      rad;
  const double phi = request.latitude * rad;
  chart.midheaven = normalize_longitude(
      std::atan2(std::sin(theta), std::cos(theta) * std::cos(eps)) / rad);
  chart.ascendant = normalize_longitude(
      std::atan2(-std::cos(theta), std::sin(theta) * std::cos(eps) +
                                       std::tan(phi) * std::sin(eps)) /
          rad +
      180);
  if (std::hypot(std::cos(theta), std::sin(theta) * std::cos(eps) +
                                      std::tan(phi) * std::sin(eps)) < 1e-12)
    throw std::invalid_argument("黄道与地平圈重合，上升点不唯一");
  // 极圈内黄道与地平圈交点的分支会翻转，必须选择东半天交点。
  const double asc_rad = chart.ascendant * rad;
  const double east = -std::sin(theta) * std::cos(asc_rad) +
                      std::cos(theta) * std::sin(asc_rad) * std::cos(eps);
  if (east < 0)
    chart.ascendant = normalize_longitude(chart.ascendant + 180);
  const double shift = request.ayanamsa.value_or(0);
  if (request.houses == HouseSystem::Placidus) {
    if (std::abs(phi) + eps >= std::numbers::pi / 2)
      throw std::invalid_argument("普拉西德宫制在此纬度不可计算");
    chart.cusps[0] = chart.ascendant;
    chart.cusps[9] = chart.midheaven;
    const auto cusp = [&](double a, double b) {
      double lower = 0, upper = 180;
      for (int i = 0; i < 70; ++i) {
        const double offset = (lower + upper) / 2;
        const double ra = theta + offset * rad;
        const double lon =
            std::atan2(std::sin(ra), std::cos(ra) * std::cos(eps));
        const double dec = std::asin(std::sin(eps) * std::sin(lon));
        const double h = std::acos(-std::tan(phi) * std::tan(dec)) / rad;
        if (offset - a - b * h > 0)
          upper = offset;
        else
          lower = offset;
      }
      const double ra = theta + (lower + upper) * rad / 2;
      return normalize_longitude(
          std::atan2(std::sin(ra), std::cos(ra) * std::cos(eps)) / rad);
    };
    chart.cusps[10] = cusp(0, 1.0 / 3);
    chart.cusps[11] = cusp(0, 2.0 / 3);
    chart.cusps[1] = cusp(60, 2.0 / 3);
    chart.cusps[2] = cusp(120, 1.0 / 3);
    for (int i : {0, 1, 2, 9, 10, 11})
      chart.cusps[(i + 6) % 12] = normalize_longitude(chart.cusps[i] + 180);
    for (auto &longitude : chart.cusps)
      longitude = normalize_longitude(longitude - shift);
  } else {
    const double asc = normalize_longitude(chart.ascendant - shift);
    const double first = request.houses == HouseSystem::WholeSign
                             ? std::floor(asc / 30) * 30
                             : asc;
    for (int i = 0; i < 12; ++i)
      chart.cusps[i] = normalize_longitude(first + i * 30);
  }
  chart.ascendant = normalize_longitude(chart.ascendant - shift);
  chart.midheaven = normalize_longitude(chart.midheaven - shift);
  for (int i = 0; i < 10; ++i) {
    const auto body = static_cast<Body>(i);
    const auto p = position(body, request.julian_day_utc);
    const double lo = std::max(2415020.5, request.julian_day_utc - 0.0005);
    const double hi = std::min(std::nextafter(2488434.5, 0.0),
                               request.julian_day_utc + 0.0005);
    chart.bodies[i] = {
        body,
        normalize_longitude(p.elon - shift),
        p.elat,
        p.distance_au,
        signed_angle(position(body, hi).elon - position(body, lo).elon) /
            (hi - lo),
        0,
        0};
  }
  assign_positions(chart);
  return chart;
}
std::vector<AspectRule> default_aspect_rules() {
  return {{0, 8},   {30, 2},  {45, 2},  {60, 6},  {72, 2}, {90, 7},
          {120, 7}, {135, 2}, {144, 2}, {150, 3}, {180, 8}};
}
std::vector<Aspect> natal_aspects(const Chart &chart,
                                  std::span<const AspectRule> rules) {
  validate_rules(rules);
  std::vector<Aspect> result;
  for (int i = 0; i < 10; ++i)
    for (int j = i + 1; j < 10; ++j)
      append_aspect(result, chart.bodies[i], chart.bodies[j], rules);
  return result;
}
std::vector<Aspect> cross_aspects(const Chart &first, const Chart &second,
                                  std::span<const AspectRule> rules) {
  compatible(first, second);
  validate_rules(rules);
  std::vector<Aspect> result;
  for (const auto &a : first.bodies)
    for (const auto &b : second.bodies)
      append_aspect(result, a, b, rules);
  return result;
}
Chart composite_chart(const Chart &first, const Chart &second) {
  compatible(first, second);
  Chart result = first;
  result.method = "中点组合盘";
  result.sources = {first.request, second.request};
  result.ascendant = midpoint(first.ascendant, second.ascendant);
  result.midheaven = midpoint(first.midheaven, second.midheaven);
  // 逐宫短弧中点可能乱序，按第一宫起点整理黄道顺序。
  for (int i = 0; i < 12; ++i)
    result.cusps[i] = midpoint(first.cusps[i], second.cusps[i]);
  const double origin = result.cusps[0];
  std::ranges::sort(result.cusps.begin() + 1, result.cusps.end(),
                    [&](double a, double b) {
                      return normalize_longitude(a - origin) <
                             normalize_longitude(b - origin);
                    });
  for (int i = 0; i < 12; ++i)
    if (normalize_longitude(result.cusps[(i + 1) % 12] - result.cusps[i]) <
        1e-10)
      throw std::invalid_argument("组合盘宫头重合，无法唯一划分宫位");
  for (int i = 0; i < 10; ++i) {
    auto &p = result.bodies[i];
    const auto &b = second.bodies[i];
    p.longitude = midpoint(p.longitude, b.longitude);
    p.latitude = (p.latitude + b.latitude) / 2;
    p.distance_au = std::nullopt;
    p.speed = (p.speed + b.speed) / 2;
  }
  assign_positions(result);
  return result;
}
std::vector<Aspect> transit_aspects(const Chart &natal, const Chart &transit,
                                    std::span<const AspectRule> rules) {
  auto fixed = natal;
  for (auto &body : fixed.bodies)
    body.speed = 0;
  return cross_aspects(fixed, transit, rules);
}
ChartComparison compare_charts(const Chart &first, const Chart &second,
                               std::span<const AspectRule> rules,
                               bool first_fixed) {
  return {first, second,
          first_fixed ? transit_aspects(first, second, rules)
                      : cross_aspects(first, second, rules),
          first_fixed};
}
Chart secondary_progression(const ChartRequest &natal, double target,
                            std::optional<double> delta_t) {
  validate_time(target);
  auto progressed = natal;
  progressed.delta_t_seconds = delta_t;
  progressed.julian_day_utc =
      natal.julian_day_utc + (target - natal.julian_day_utc) / 365.2422;
  auto result = calculate_chart(progressed);
  result.method = "次限推运（日年法，推运时刻宫轴）";
  result.sources = {natal};
  result.target_julian_day = target;
  return result;
}
Chart solar_arc_progression(const ChartRequest &natal, double target,
                            std::optional<double> delta_t) {
  auto result = calculate_chart(natal);
  const auto progressed = secondary_progression(natal, target, delta_t);
  const double arc =
      signed_angle(progressed.bodies[0].longitude - result.bodies[0].longitude);
  for (auto &p : result.bodies) {
    p.longitude = normalize_longitude(p.longitude + arc);
    p.speed = progressed.bodies[0].speed / 365.2422;
  }
  for (auto &cusp : result.cusps)
    cusp = normalize_longitude(cusp + arc);
  result.ascendant = normalize_longitude(result.ascendant + arc);
  result.midheaven = normalize_longitude(result.midheaven + arc);
  result.method = "真太阳弧推运";
  result.target_julian_day = target;
  assign_positions(result);
  return result;
}
double find_return(Body body, double target, double start, double limit,
                   std::optional<double> delta_t,
                   std::optional<std::string> file) {
  std::lock_guard lock(engine_mutex);
  if ((body != Body::Sun && body != Body::Moon) || !std::isfinite(limit) ||
      limit <= 0 || limit > 400)
    throw std::invalid_argument("返照星体或搜索长度无效");
  validate_time(start);
  validate_time(start + limit);
  target = normalize_longitude(target);
  const double step = body == Body::Moon ? 0.25 : 1.0;
  double left = start;
  double before =
      signed_angle(body_longitude(body, left, delta_t, file) - target);
  if (std::abs(before) < 1e-9)
    return start;
  while (left < start + limit) {
    double right = std::min(start + limit, left + step);
    const double after =
        signed_angle(body_longitude(body, right, delta_t, file) - target);
    if (before <= 0 && after >= 0 && after - before < 180) {
      while ((right - left) * 86400 > 0.05) {
        const double middle = (left + right) / 2;
        if (signed_angle(body_longitude(body, middle, delta_t, file) - target) <
            0)
          left = middle;
        else
          right = middle;
      }
      return (left + right) / 2;
    }
    left = right;
    before = after;
  }
  throw std::runtime_error("区间内没有找到返照");
}
Chart return_chart(const ChartRequest &natal, Body body, double start,
                   double longitude, double latitude,
                   std::optional<double> delta_t) {
  auto request = natal;
  validate_time(start);
  const double remaining = std::nextafter(2488434.5, 0.0) - start;
  request.julian_day_utc =
      find_return(body,
                  body_longitude(body, natal.julian_day_utc,
                                 natal.delta_t_seconds, natal.ephemeris_file),
                  start, std::min(body == Body::Sun ? 367.0 : 30.0, remaining),
                  delta_t, natal.ephemeris_file);
  request.delta_t_seconds = delta_t;
  request.longitude = longitude;
  request.latitude = latitude;
  auto result = calculate_chart(request);
  result.method = body == Body::Sun ? "太阳返照" : "月亮返照";
  result.sources = {natal};
  return result;
}
std::string_view body_name(Body body) {
  constexpr std::array names{"太阳", "月亮", "水星",   "金星",   "火星",
                             "木星", "土星", "天王星", "海王星", "冥王星"};
  return names.at(static_cast<std::size_t>(body));
}
std::string_view sign_name(int sign) {
  constexpr std::array names{"白羊座", "金牛座", "双子座", "巨蟹座",
                             "狮子座", "处女座", "天秤座", "天蝎座",
                             "射手座", "摩羯座", "水瓶座", "双鱼座"};
  return names.at(sign);
}
} // namespace ZhouYi::Astrology
