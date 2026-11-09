module ZhouYi.Astrology.Presenter;
import std;
import nlohmann.json;
import ZhouYi.Astrology;
namespace ZhouYi::Astrology::Presenter {
namespace {
std::string_view house_system(HouseSystem system) {
  switch (system) {
  case HouseSystem::WholeSign:
    return "整宫制";
  case HouseSystem::Equal:
    return "等宫制";
  case HouseSystem::Placidus:
    return "普拉西德宫制";
  }
  throw std::invalid_argument("未知宫制");
}
} // namespace
nlohmann::json to_json(const Chart &chart, std::span<const Aspect> aspects) {
  nlohmann::json result;
  result["method"] = chart.method;
  result["sources"] = nlohmann::json::array();
  for (const auto &source : chart.sources) {
    nlohmann::json entry = {{"julian_day_utc", source.julian_day_utc},
                            {"longitude", source.longitude},
                            {"latitude", source.latitude},
                            {"house_system", house_system(source.houses)}};
    if (source.ayanamsa)
      entry["ayanamsa"] = *source.ayanamsa;
    if (source.delta_t_seconds)
      entry["delta_t_seconds"] = *source.delta_t_seconds;
    if (source.ephemeris_file)
      entry["ephemeris_file"] = *source.ephemeris_file;
    result["sources"].push_back(entry);
  }
  if (chart.target_julian_day)
    result["target_julian_day"] = *chart.target_julian_day;
  result["engine"] = chart.ephemeris_model;
  if (chart.request.ephemeris_file)
    result["ephemeris_file"] = *chart.request.ephemeris_file;
  result["orientation_model"] = "ERFA 2.0.1 IAU 2006/2000A";
  result["delta_t_seconds"] = chart.delta_t_seconds;
  result["delta_t_model"] =
      chart.request.delta_t_seconds ? "显式 TT−UT1" : "Espenak–Meeus 默认模型";
  result["julian_day_utc"] = chart.request.julian_day_utc;
  result["location"] = {{"longitude", chart.request.longitude},
                        {"latitude", chart.request.latitude}};
  if (chart.sources.size() > 1) {
    result["julian_day_utc"] = nullptr;
    result["location"] = nullptr;
    result["delta_t_seconds"] = nullptr;
  }
  result["house_system"] = house_system(chart.request.houses);
  result["zodiac"] =
      chart.request.ayanamsa ? "恒星黄道（显式岁差值）" : "热带黄道";
  if (chart.request.ayanamsa)
    result["ayanamsa"] = *chart.request.ayanamsa;
  result["angles"] = {
      {"ascendant", chart.ascendant},
      {"midheaven", chart.midheaven},
      {"descendant", normalize_longitude(chart.ascendant + 180)},
      {"imum_coeli", normalize_longitude(chart.midheaven + 180)}};
  result["cusps"] = chart.cusps;
  result["bodies"] = nlohmann::json::array();
  for (const auto &p : chart.bodies)
    result["bodies"].push_back(
        {{"id", static_cast<int>(p.body)},
         {"name", body_name(p.body)},
         {"longitude", p.longitude},
         {"latitude", p.latitude},
         {"distance_au", p.distance_au ? nlohmann::json(*p.distance_au)
                                       : nlohmann::json(nullptr)},
         {"speed_degrees_per_day", p.speed},
         {"retrograde", p.speed < 0},
         {"sign", p.sign},
         {"sign_name", sign_name(p.sign)},
         {"house", p.house}});
  result["aspects"] = nlohmann::json::array();
  for (const auto &a : aspects)
    result["aspects"].push_back({{"first", static_cast<int>(a.first)},
                                 {"second", static_cast<int>(a.second)},
                                 {"angle", a.angle},
                                 {"separation", a.separation},
                                 {"orb", a.orb},
                                 {"applying", a.applying},
                                 {"stationary", a.stationary}});
  return result;
}
void write_zh(std::ostream &out, const Chart &chart,
              std::span<const Aspect> aspects) {
  out << "# 西方占星 · " << chart.method << "\n\n";
  out << "星历后端 " << chart.ephemeris_model << "。\n\n";
  out << std::setprecision(12);
  if (chart.sources.size() == 1)
    out << "计算 UTC 儒略日 " << chart.request.julian_day_utc << "，经度 "
        << chart.request.longitude << "°，纬度 " << chart.request.latitude
        << "°。\n\n";
  for (std::size_t i = 0; i < chart.sources.size(); ++i) {
    const auto &source = chart.sources[i];
    out << "来源 " << i + 1 << " 的 UTC 儒略日 " << source.julian_day_utc
        << "，经度 " << source.longitude << "°，纬度 " << source.latitude
        << "°。\n\n";
  }
  if (chart.target_julian_day)
    out << "推运目标 UTC 儒略日 " << *chart.target_julian_day << "。\n\n";
  out << (chart.request.ayanamsa ? "恒星黄道" : "热带黄道") << "，"
      << house_system(chart.request.houses) << "。\n\n";
  if (chart.sources.size() == 1)
    out << "ΔT " << chart.delta_t_seconds
        << " 秒，宫轴采用 IAU 2006/2000A。\n\n";
  out << "上升点 " << chart.ascendant << "°，天顶 " << chart.midheaven
      << "°，下降点 " << normalize_longitude(chart.ascendant + 180)
      << "°，天底 " << normalize_longitude(chart.midheaven + 180) << "°。\n\n";
  out << "| 星体 | 星座 | 黄经 | 黄纬 | 宫位 | 运行 |\n| --- | --- | --- | --- "
         "| --- | --- |\n";
  for (const auto &p : chart.bodies)
    out << "| " << body_name(p.body) << " | " << sign_name(p.sign) << " | "
        << p.longitude << "° | " << p.latitude << "° | " << p.house << " | "
        << (p.speed < 0 ? "逆行" : "顺行") << " |\n";
  out << "\n| 宫位 | 宫头黄经 |\n| --- | --- |\n";
  for (int i = 0; i < 12; ++i)
    out << "| " << i + 1 << " | " << chart.cusps[i] << "° |\n";
  if (!aspects.empty()) {
    out << "\n| 星体一 | 星体二 | 相位角 | 容许度内偏差 | 阶段 |\n| --- | --- "
           "| --- | --- | --- |\n";
    for (const auto &a : aspects)
      out << "| " << body_name(a.first) << " | " << body_name(a.second) << " | "
          << a.angle << "° | " << a.orb << "° | "
          << (a.stationary ? "近静止"
              : a.applying ? "入相"
                           : "出相或精确")
          << " |\n";
  }
}
nlohmann::json to_json(const ChartComparison &comparison) {
  return {
      {"method", comparison.first_fixed ? "行运比较" : "双盘比较"},
      {"first", to_json(comparison.first, {})},
      {"second", to_json(comparison.second, {})},
      {"aspects", to_json(comparison.second, comparison.aspects).at("aspects")},
      {"first_fixed", comparison.first_fixed}};
}
void write_zh(std::ostream &output, const ChartComparison &comparison) {
  output << (comparison.first_fixed ? "# 行运对本命\n\n" : "# 比较盘\n\n");
  output << "第一参与盘\n\n";
  write_zh(output, comparison.first, {});
  output << "第二参与盘，交叉相位表的第一星体取自第一参与盘。\n\n";
  write_zh(output, comparison.second, comparison.aspects);
}
} // namespace ZhouYi::Astrology::Presenter
