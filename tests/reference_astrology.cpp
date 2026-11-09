import std;
import nlohmann.json;
import ZhouYi.Astrology;
import ZhouYi.Astrology.Presenter;
int main(int argc, char **argv) {
  using namespace ZhouYi::Astrology;
  if (argc != 2)
    return 2;
  try {
    std::ifstream input(argv[1]);
    auto cases = nlohmann::json::parse(input);
    nlohmann::json output = nlohmann::json::array();
    for (const auto &c : cases) {
      ChartRequest request{c.at("jd").get<double>(), c.at("lon").get<double>(),
                           c.at("lat").get<double>(),
                           static_cast<HouseSystem>(c.at("houses").get<int>())};
      if (c.contains("ayanamsa"))
        request.ayanamsa = c.at("ayanamsa").get<double>();
      if (c.contains("delta_t_seconds"))
        request.delta_t_seconds = c.at("delta_t_seconds").get<double>();
      if (c.contains("ephemeris_file"))
        request.ephemeris_file = c.at("ephemeris_file").get<std::string>();
      auto chart = calculate_chart(request);
      auto rules = default_aspect_rules();
      output.push_back(Presenter::to_json(chart, natal_aspects(chart, rules)));
    }
    std::println("{}", output.dump());
  } catch (const std::exception &error) {
    std::println(std::cerr, "{}", error.what());
    return 1;
  }
}
