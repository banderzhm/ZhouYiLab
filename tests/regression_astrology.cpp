import std;
import nlohmann.json;
import ZhouYi.Astrology;
import ZhouYi.Astrology.Presenter;

namespace {
using namespace ZhouYi::Astrology;
std::size_t phase_sensitive_cases{}, aspect_boundary_cases{},
    house_boundary_cases{};
void require(bool condition, std::string_view message) {
  if (!condition)
    throw std::runtime_error(std::string(message));
}
double error(double a, double b) {
  return std::abs(std::remainder(a - b, 360.0));
}
void compare_aspects(const std::vector<Aspect> &actual,
                     const nlohmann::json &expected) {
  const auto rules = default_aspect_rules();
  for (const auto &e : expected) {
    const auto first = static_cast<Body>(e.at("first").get<int>());
    const auto second = static_cast<Body>(e.at("second").get<int>());
    const double angle = e.at("angle").get<double>();
    const auto rule = std::ranges::find_if(
        rules, [&](const AspectRule &r) { return r.angle == angle; });
    require(rule != rules.end(), "参考相位规则不受支持");
    const auto found = std::ranges::find_if(actual, [&](const Aspect &a) {
      return a.first == first && a.second == second && a.angle == angle;
    });
    if (found == actual.end()) {
      ++aspect_boundary_cases;
      require(rule->orb - e.at("orb").get<double>() < 0.05,
              "遗漏非边界参考相位");
    } else {
      require(std::abs(found->orb - e.at("orb").get<double>()) < 0.05,
              "相位偏差超限");
      const bool threshold_sensitive =
          (found->stationary ||
           e.at("movement").get<std::string>() == "Static") &&
          e.at("relative_speed").get<double>() < 0.002;
      if (threshold_sensitive || found->orb <= 0.05)
        ++phase_sensitive_cases;
      if (found->orb > 0.05 && !threshold_sensitive &&
          found->applying != e.at("applying").get<bool>())
        throw std::runtime_error(std::format(
            "星体 {}-{} 相位 {} 偏差 {} 本地入相 {} 参考入相 {}",
            static_cast<int>(first), static_cast<int>(second), angle,
            found->orb, found->applying, e.at("applying").get<bool>()));
    }
  }
  for (const auto &a : actual) {
    const bool found = std::ranges::any_of(expected, [&](const auto &e) {
      return e.at("first").template get<int>() == static_cast<int>(a.first) &&
             e.at("second").template get<int>() == static_cast<int>(a.second) &&
             e.at("angle").template get<double>() == a.angle;
    });
    if (!found) {
      ++aspect_boundary_cases;
      const auto r = std::ranges::find_if(
          rules, [&](const AspectRule &r) { return r.angle == a.angle; });
      require(r->orb - a.orb < 0.05, "多报非边界相位");
    }
  }
}
template <class F> void invalid(F operation) {
  try {
    operation();
  } catch (const std::invalid_argument &) {
    return;
  }
  throw std::runtime_error("非法输入未拒绝");
}
void verify_features() {
  require(civil_to_julian(2000, 1, 1, 12, 0, 0, 0) == 2451545,
          "J2000 日期转换错误");
  require(civil_to_julian(2000, 1, 1, 17, 45, 0, 345) == 2451545,
          "45 分钟时区错误");
  require(civil_to_julian(2000, 1, 1, 17, 30, 0, 330) == 2451545,
          "半小时时区错误");
  require(civil_to_julian(2000, 1, 2, 2, 0, 0, 840) == 2451545, "跨日偏移错误");
  require(std::abs((civil_to_julian(2024, 11, 3, 1, 30, 0, -300) -
                    civil_to_julian(2024, 11, 3, 1, 30, 0, -240)) *
                       24 -
                   1) < 1e-8,
          "夏令时重复小时未区分");
  invalid([] { civil_to_julian(1900, 2, 29, 0, 0, 0, 0); });
  invalid([] { civil_to_julian(2000, 257, 1, 0, 0, 0, 0); });
  invalid([] { civil_to_julian(2100, 2, 29, 0, 0, 0, 0); });
  invalid([] { civil_to_julian(2000, 1, 1, 0, 0, 60, 0); });
  ChartRequest request{2451545.0, 120, 30};
  auto chart = calculate_chart(request);
  auto custom = request;
  custom.delta_t_seconds = 100;
  const auto configured = calculate_chart(custom);
  require(configured.delta_t_seconds == 100, "显式 ΔT 未应用");
  const auto evolved = secondary_progression(custom, 2455197.5, 200);
  require(evolved.delta_t_seconds == 200 &&
              evolved.sources[0].delta_t_seconds == 100,
          "推运误用出生 ΔT");
  custom.delta_t_seconds = std::numeric_limits<double>::quiet_NaN();
  invalid([&] { calculate_chart(custom); });
  custom.delta_t_seconds = 100;
  custom.latitude = 91;
  invalid([&] { calculate_chart(custom); });
  custom.latitude = request.latitude;
  require(error(calculate_chart(request).bodies[1].longitude,
                chart.bodies[1].longitude) < 1e-12,
          "异常调用污染时间配置");
  custom.delta_t_seconds = 100;
  auto parallel = std::async(std::launch::async, [&] {
    for (int i = 0; i < 20; ++i)
      require(error(calculate_chart(custom).bodies[1].longitude,
                    configured.bodies[1].longitude) < 1e-12,
              "并发显式时间被污染");
  });
  for (int i = 0; i < 20; ++i)
    require(error(calculate_chart(request).bodies[1].longitude,
                  chart.bodies[1].longitude) < 1e-12,
            "并发默认时间被污染");
  parallel.get();
  auto sidereal = request;
  sidereal.ayanamsa = 24;
  const auto shifted = calculate_chart(sidereal);
  for (int i = 0; i < 10; ++i)
    require(error(chart.bodies[i].longitude - 24, shifted.bodies[i].longitude) <
                1e-10,
            "恒星黄道转换错误");
  for (int i = 0; i < 12; ++i)
    require(error(chart.cusps[i] - 24, shifted.cusps[i]) < 1e-10,
            "恒星黄道宫头错误");
  auto polar = request;
  polar.latitude = 80;
  invalid([&] { calculate_chart(polar); });
  for (auto system : {HouseSystem::Equal, HouseSystem::WholeSign}) {
    polar.houses = system;
    auto p = calculate_chart(polar);
    for (const auto &body : p.bodies)
      require(body.house >= 1 && body.house <= 12, "高纬落宫错误");
  }
  auto bad = request;
  bad.latitude = std::numeric_limits<double>::quiet_NaN();
  invalid([&] { calculate_chart(bad); });
  auto future = request;
  future.julian_day_utc += 3652.422;
  const auto progress = secondary_progression(request, future.julian_day_utc);
  require(std::abs(progress.request.julian_day_utc - request.julian_day_utc -
                   10) < 1e-9,
          "次限比例错误");
  const auto solar_arc = solar_arc_progression(request, future.julian_day_utc);
  const double arc = std::remainder(
      progress.bodies[0].longitude - chart.bodies[0].longitude, 360.0);
  for (int i = 0; i < 10; ++i)
    require(error(chart.bodies[i].longitude + arc,
                  solar_arc.bodies[i].longitude) < 1e-9,
            "太阳弧方向错误");
  for (auto body : {Body::Sun, Body::Moon}) {
    const auto ret =
        return_chart(request, body, future.julian_day_utc, 120, 30);
    require(error(body_longitude(body, request.julian_day_utc),
                  body_longitude(body, ret.request.julian_day_utc)) < 0.00002,
            "返照根不准确");
  }
  const auto composite = composite_chart(chart, chart);
  for (int i = 0; i < 10; ++i)
    require(error(composite.bodies[i].longitude, chart.bodies[i].longitude) <
                1e-10,
            "自组合盘错误");
  const auto rules = default_aspect_rules();
  const auto aspects = natal_aspects(chart, rules);
  require(!aspects.empty(), "缺失本命相位");
  const auto cross = cross_aspects(chart, chart, rules);
  require(std::ranges::count_if(cross,
                                [](const Aspect &a) {
                                  return a.first == a.second && a.angle == 0;
                                }) == 10,
          "比较盘同名星体相位遗漏");
  const auto json = Presenter::to_json(chart, aspects);
  const auto comparison =
      compare_charts(chart, calculate_chart(future), rules, true);
  const auto comparison_json = Presenter::to_json(comparison);
  require(comparison_json.at("first").at("bodies").size() == 10 &&
              comparison_json.at("second").at("bodies").size() == 10 &&
              comparison_json.at("first_fixed").get<bool>(),
          "双盘输出遗漏参与盘");
  require(json.at("bodies").size() == 10 && json.at("cusps").size() == 12 &&
              json.at("aspects").size() == aspects.size(),
          "JSON 完整性错误");
  std::ostringstream text;
  Presenter::write_zh(text, chart, aspects);
  for (const auto &body : chart.bodies)
    require(text.str().contains(body_name(body.body)), "中文遗漏星体");
  invalid([&] { natal_aspects(chart, std::array{AspectRule{0, -1}}); });
  auto wrap = chart;
  wrap.bodies[0].longitude = 359;
  wrap.bodies[0].speed = 0;
  wrap.bodies[1].longitude = 1;
  wrap.bodies[1].speed = -1;
  const auto exact_rules = std::array{AspectRule{0, 2}};
  const auto wrap_aspects = natal_aspects(wrap, exact_rules);
  const auto pair = std::ranges::find_if(wrap_aspects, [](const Aspect &a) {
    return a.first == Body::Sun && a.second == Body::Moon;
  });
  require(pair != wrap_aspects.end() && pair->orb == 2 && pair->applying,
          "跨零度或精确容许度边界错误");
  wrap.bodies[0].longitude = 0;
  wrap.bodies[1].longitude = 180;
  const auto opposed = composite_chart(wrap, chart);
  require(opposed.sources.size() == 2 && !opposed.bodies[0].distance_au,
          "组合盘来源或距离错误");
  const auto opposed_json = Presenter::to_json(opposed, {});
  require(opposed_json.at("julian_day_utc").is_null() &&
              opposed_json.at("location").is_null(),
          "组合盘虚拟时空输出错误");
}
} // namespace
int main(int argc, char **argv) {
  try {
    if (argc != 3 && argc != 5)
      return 2;
    std::ifstream file(argv[1]);
    const auto reference = nlohmann::json::parse(file);
    const auto &cases = reference.at("cases");
    const auto &expected = reference.at("expected");
    std::ifstream context_file(argv[2]);
    const auto contexts = nlohmann::json::parse(context_file).at("contexts");
    nlohmann::json precise_contexts;
    if (argc == 5) {
      std::ifstream precise_file(argv[3]);
      precise_contexts = nlohmann::json::parse(precise_file).at("contexts");
      require(precise_contexts.size() == cases.size(),
              "高精度对照语料数量错误");
    }
    require(contexts.size() == cases.size(), "时间口径语料数量错误");
    require(cases.size() == 300 && expected.size() == 300, "对照语料数量错误");
    double max_body = 0, max_cusp = 0, max_speed = 0;
    double max_aligned_longitude = 0, max_aligned_cusp = 0;
    double max_precise_longitude = 0, max_precise_latitude = 0,
           max_precise_distance = 0;
    std::vector<Chart> charts;
    for (std::size_t i = 0; i < cases.size(); ++i) {
      const auto &c = cases[i];
      const auto &e = expected[i];
      const auto chart = calculate_chart(
          {c.at("jd").get<double>(), c.at("lon").get<double>(),
           c.at("lat").get<double>(),
           static_cast<HouseSystem>(c.at("houses").get<int>())});
      charts.push_back(chart);
      if (argc == 5) {
        auto request = chart.request;
        request.delta_t_seconds =
            precise_contexts[i].at("delta_t_seconds").get<double>();
        request.ephemeris_file = argv[4];
        const auto precise = calculate_chart(request);
        const auto &expected = precise_contexts[i].at("expected");
        for (int k = 0; k < 10; ++k) {
          const double lon = error(precise.bodies[k].longitude,
                                   expected.at("longitude")[k].get<double>());
          const double lat = std::abs(precise.bodies[k].latitude -
                                      expected.at("latitude")[k].get<double>());
          const double distance = expected.at("distance_au")[k].get<double>();
          const double distance_error =
              std::abs(*precise.bodies[k].distance_au - distance) / distance;
          require(lon < 0.000005 && lat < 0.000005,
                  "JPL 地心视位置超出高精度阈值");
          require(distance_error < 0.000001, "JPL 地心距离相对误差超限");
          require(std::abs(precise.bodies[k].speed -
                           expected.at("speed")[k].get<double>()) < 0.0001,
                  "JPL 星体速度超限");
          max_precise_longitude = std::max(max_precise_longitude, lon);
          max_precise_latitude = std::max(max_precise_latitude, lat);
          max_precise_distance = std::max(max_precise_distance, distance_error);
        }
        const auto metadata = Presenter::to_json(precise, {});
        require(metadata.at("engine").get<std::string>().contains("JPL"),
                "高精度后端输出缺失");
        require(error(calculate_chart(chart.request).bodies[8].longitude,
                      chart.bodies[8].longitude) < 1e-12,
                "JPL 配置污染默认后端");
      }
      auto aligned_request = chart.request;
      aligned_request.delta_t_seconds =
          contexts[i].at("delta_t_seconds").get<double>();
      const auto aligned = calculate_chart(aligned_request);
      const auto &aligned_expected = contexts[i].at("expected");
      for (int k = 0; k < 10; ++k) {
        const double e =
            error(aligned.bodies[k].longitude,
                  aligned_expected.at("longitude")[k].get<double>());
        require(e < 0.006, "同时间口径星体黄经超限");
        max_aligned_longitude = std::max(max_aligned_longitude, e);
      }
      for (int k = 0; k < 12; ++k) {
        const double e = error(aligned.cusps[k],
                               aligned_expected.at("cusps")[k].get<double>());
        require(e < 0.000005, "同 IAU 标准宫头超限");
        max_aligned_cusp = std::max(max_aligned_cusp, e);
      }
      const auto repeated = calculate_chart(chart.request);
      require(error(repeated.bodies[1].longitude, chart.bodies[1].longitude) <
                  1e-12,
              "显式 ΔT 泄漏到默认请求");
      require(std::abs(aligned.delta_t_seconds -
                       *aligned_request.delta_t_seconds) < 1e-9,
              "ΔT 结果元数据错误");
      if (e.contains("aspects"))
        compare_aspects(natal_aspects(chart, default_aspect_rules()),
                        e.at("aspects"));
      for (int k = 0; k < 10; ++k) {
        const double difference = error(chart.bodies[k].longitude,
                                        e.at("longitude")[k].get<double>());
        require(std::isfinite(difference) &&
                    std::isfinite(chart.bodies[k].speed) &&
                    std::isfinite(chart.bodies[k].latitude),
                "星体返回非有限数值");
        if (difference > 0.025)
          throw std::runtime_error(
              std::format("案例 {} 星体 {} 黄经差 {}", i, k, difference));
        max_body = std::max(max_body, difference);
        require(error(chart.bodies[k].latitude,
                      e.at("latitude")[k].get<double>()) < 0.025,
                "星体黄纬超出容差");
        const double reference_distance = e.at("distance_au")[k].get<double>();
        require(
            chart.bodies[k].distance_au.has_value() &&
                std::abs(*chart.bodies[k].distance_au - reference_distance) /
                        reference_distance <
                    0.001,
            "星体地心距离相对差超出千分之一");
        const double speed_error =
            std::abs(chart.bodies[k].speed - e.at("speed")[k].get<double>());
        require(speed_error < 0.002, "星体速度超出容差");
        max_speed = std::max(max_speed, speed_error);
        if (chart.bodies[k].house != e.at("house")[k].get<int>()) {
          ++house_boundary_cases;
          double nearest = 180;
          for (double cusp : chart.cusps)
            nearest = std::min(nearest, error(chart.bodies[k].longitude, cusp));
          require(nearest < 0.05, "非宫头边界的落宫不一致");
        }
      }
      for (int k = 0; k < 12; ++k) {
        const double difference =
            error(chart.cusps[k], e.at("cusps")[k].get<double>());
        const double cusp_tolerance =
            std::abs(chart.request.latitude) >= 60 ? 0.01 : 0.002;
        if (difference > cusp_tolerance)
          throw std::runtime_error(
              std::format("案例 {} 宫头 {} 差 {}", i, k + 1, difference));
        max_cusp = std::max(max_cusp, difference);
      }
      const double asc_tolerance =
          std::abs(chart.request.latitude) >= 60 ? 0.01 : 0.002;
      if (error(chart.ascendant, e.at("ascendant").get<double>()) >=
          asc_tolerance)
        throw std::runtime_error(
            std::format("案例 {} 上升点差 {}，本地 {}，参考 {}", i,
                        error(chart.ascendant, e.at("ascendant").get<double>()),
                        chart.ascendant, e.at("ascendant").get<double>()));
      require(error(chart.midheaven, e.at("midheaven").get<double>()) < 0.005,
              "天顶错误");
    }
    for (const auto &polar : reference.at("polar_cases")) {
      const auto p = calculate_chart(
          {polar.at("jd").get<double>(), polar.at("lon").get<double>(),
           polar.at("lat").get<double>(),
           static_cast<HouseSystem>(polar.at("houses").get<int>())});
      for (int k = 0; k < 12; ++k)
        require(error(p.cusps[k], polar.at("cusps")[k].get<double>()) < 0.01,
                "极区宫头对照失败");
      require(error(p.ascendant, polar.at("ascendant").get<double>()) < 0.01,
              "极区上升点对照失败");
    }
    double max_return_seconds = 0;
    for (const auto &technique : reference.at("techniques")) {
      const auto &first = charts.at(technique.at("first").get<std::size_t>());
      const auto &second = charts.at(technique.at("second").get<std::size_t>());
      const auto composite = composite_chart(first, second);
      const auto reverse = composite_chart(second, first);
      const auto progression = secondary_progression(
          first.request, technique.at("target_jd").get<double>());
      const auto solar_arc = solar_arc_progression(
          first.request, technique.at("target_jd").get<double>());
      for (int k = 0; k < 10; ++k) {
        require(error(composite.bodies[k].longitude,
                      reverse.bodies[k].longitude) < 1e-10,
                "组合盘交换参与者后结果不同");
        require(error(progression.bodies[k].longitude,
                      technique.at("progressed_longitudes")[k].get<double>()) <
                    0.025,
                "次限星体独立对照不一致");
        require(error(solar_arc.bodies[k].longitude,
                      technique.at("solar_arc_longitudes")[k].get<double>()) <
                    0.025,
                "太阳弧星体独立对照不一致");
      }
      for (int k = 0; k < 12; ++k)
        require(error(progression.cusps[k],
                      technique.at("progressed_cusps")[k].get<double>()) < 0.01,
                "次限宫头独立对照不一致");
      for (int k = 0; k < 10; ++k)
        require(error(composite.bodies[k].longitude,
                      technique.at("composite_longitudes")[k].get<double>()) <
                    0.025,
                "组合盘星体中点不一致");
      for (int k = 0; k < 12; ++k)
        require(error(composite.cusps[k],
                      technique.at("composite_cusps")[k].get<double>()) < 0.01,
                "组合盘宫头不一致");
      const auto rules = default_aspect_rules();
      compare_aspects(cross_aspects(first, second, rules),
                      technique.at("synastry"));
      compare_aspects(transit_aspects(first, second, rules),
                      technique.at("transits"));
      for (auto body : {Body::Sun, Body::Moon}) {
        const double start = civil_to_julian(2025, 1, 1, 0, 0, 0, 0);
        const auto ret =
            return_chart(first.request, body, start, first.request.longitude,
                         first.request.latitude);
        const double expected_jd =
            technique
                .at(body == Body::Sun ? "solar_return_jd" : "lunar_return_jd")
                .get<double>();
        const double seconds =
            std::abs(expected_jd - ret.request.julian_day_utc) * 86400;
        require(seconds < 300, "跨库返照时间差超过 300 秒");
        max_return_seconds = std::max(max_return_seconds, seconds);
      }
    }
    std::println(
        "十组比较盘、行运、组合盘及二十次返照通过，最大返照时间差 {} 秒",
        max_return_seconds);
    verify_features();
    if (argc == 5) {
      auto source = charts.at(2).request;
      source.delta_t_seconds =
          precise_contexts[2].at("delta_t_seconds").get<double>();
      source.ephemeris_file = argv[4];
      const double target = civil_to_julian(2025, 1, 1, 0, 0, 0, 0);
      const auto progressed = secondary_progression(source, target, 85);
      require(progressed.request.ephemeris_file == source.ephemeris_file &&
                  progressed.delta_t_seconds == 85,
              "推运丢失 JPL 后端或时间口径");
      for (auto body : {Body::Sun, Body::Moon}) {
        const auto ret = return_chart(source, body, target, 120, 30, 69);
        require(ret.request.ephemeris_file == source.ephemeris_file &&
                    ret.delta_t_seconds == 69,
                "返照丢失 JPL 后端或时间口径");
        require(
            error(body_longitude(body, source.julian_day_utc,
                                 source.delta_t_seconds, source.ephemeris_file),
                  body_longitude(body, ret.request.julian_day_utc, 69,
                                 source.ephemeris_file)) < 0.00002,
            "JPL 返照未收敛至本命黄经");
      }
      auto bad = source;
      bad.ephemeris_file = std::string(argv[4]) + ".missing";
      bool rejected = false;
      try {
        calculate_chart(bad);
      } catch (const std::runtime_error &) {
        rejected = true;
      }
      require(rejected, "无效星历文件未报错");
      require(error(calculate_chart(charts[2].request).bodies[0].longitude,
                    charts[2].bodies[0].longitude) < 1e-12,
              "JPL 失败污染后续默认请求");
    }
    if (argc == 5)
      std::println(
          "JPL 对 Swiss 正式星历，黄经最大差 {}°，黄纬差 {}°，距离相对差 {}",
          max_precise_longitude, max_precise_latitude, max_precise_distance);
    std::println("相同 ΔT 和 IAU 标准，最大黄经差 {}°，宫头差 {}°",
                 max_aligned_longitude, max_aligned_cusp);
    std::println("边界分类，相位阶段 {}，相位容许度 {}，宫头落宫 {}",
                 phase_sensitive_cases, aspect_boundary_cases,
                 house_boundary_cases);
    std::println(
        "300 张独立星盘通过，最大黄经差 {}°，宫头差 {}°，速度差 {}°/日",
        max_body, max_cusp, max_speed);
  } catch (const std::exception &error) {
    std::println(std::cerr, "{}", error.what());
    return 1;
  }
}
