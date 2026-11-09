import std;
import nlohmann.json;
import ZhouYi.Astrology.JplEphemeris;
int main(int argc, char **argv) {
  using namespace ZhouYi::Astrology::JplEphemeris;
  try {
    if (argc != 3)
      return 2;
    std::ifstream input(argv[2]);
    const auto reference = nlohmann::json::parse(input);
    if (reference.at("rows").size() != 1918)
      throw std::runtime_error("SPK 独立语料记录数量错误");
    double max_position = 0, max_velocity = 0;
    for (const auto &row : reference.at("rows")) {
      const auto actual = barycentric_state(
          argv[1], row.at("target").get<int>(), row.at("days").get<double>());
      for (int k = 0; k < 3; ++k) {
        const double dp =
            std::abs(actual.position[k] - row.at("position")[k].get<double>());
        const double dv =
            std::abs(actual.velocity[k] - row.at("velocity")[k].get<double>());
        if (!std::isfinite(dp) || !std::isfinite(dv) || dp > 0.00001 ||
            dv > 1e-8)
          throw std::runtime_error(std::format(
              "SPK 独立对照超限，位置 {} km，速度 {} km/s", dp, dv));
        max_position = std::max(max_position, dp);
        max_velocity = std::max(max_velocity, dv);
      }
    }
    bool rejected = false;
    try {
      barycentric_state(argv[1], 399, 1e9);
    } catch (const std::runtime_error &) {
      rejected = true;
    }
    if (!rejected)
      throw std::runtime_error("SPK 未拒绝覆盖范围外的时间");
    rejected = false;
    try {
      barycentric_state(argv[1], 999999, 0);
    } catch (const std::runtime_error &) {
      rejected = true;
    }
    if (!rejected)
      throw std::runtime_error("SPK 未拒绝未知目标");
    const auto scratch =
        std::filesystem::temp_directory_path() /
        std::format(
            "zhouyilab-spk-{}.bsp",
            std::chrono::steady_clock::now().time_since_epoch().count());
    {
      std::ofstream broken(scratch, std::ios::binary);
      const std::array<char, 1024> zeros{};
      broken.write(zeros.data(), zeros.size());
    }
    rejected = false;
    try {
      barycentric_state(scratch.generic_string(), 399, 0);
    } catch (const std::runtime_error &) {
      rejected = true;
    }
    std::filesystem::remove(scratch);
    if (!rejected)
      throw std::runtime_error("SPK 未拒绝损坏文件头");
    const auto restored = barycentric_state(argv[1], 399, 0);
    if (!std::isfinite(restored.position[0]))
      throw std::runtime_error("坏文件影响已有星历读取");
    std::println("JPL 原始状态 {} 组通过，位置最大差 {} km，速度最大差 {} km/s",
                 reference.at("rows").size(), max_position, max_velocity);
  } catch (const std::exception &error) {
    std::println(std::cerr, "{}", error.what());
    return 1;
  }
}
