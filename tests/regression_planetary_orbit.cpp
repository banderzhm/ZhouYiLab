import std;
import ZhouYi.Astrology.PlanetaryOrbit;

int main(int argc, char **argv) {
  using namespace ZhouYi::Astrology;
  if (argc != 2)
    return 2;
  std::ifstream input(argv[1]);
  if (!input)
    return 2;
  int planet;
  double tt, x, y, z;
  double maximum = 0;
  std::size_t count = 0;
  while (input >> planet >> tt >> x >> y >> z) {
    const auto actual = heliocentric_position(static_cast<Planet>(planet), tt);
    const double error =
        std::max({std::abs(actual.x - x), std::abs(actual.y - y),
                  std::abs(actual.z - z)});
    if (!std::isfinite(error) || error > 1e-11) {
      std::println("行星 {} TT {} 差值 {} AU", planet, tt, error);
      return 1;
    }
    maximum = std::max(maximum, error);
    ++count;
  }
  if (!input.eof() || count != 40816)
    return 1;
  for (const double invalid :
       {std::numeric_limits<double>::quiet_NaN(),
        std::numeric_limits<double>::infinity(), -36525.0, 36891.0}) {
    try {
      heliocentric_position(Planet::Earth, invalid);
      return 1;
    } catch (const std::invalid_argument &) {
    }
  }
  try {
    heliocentric_position(static_cast<Planet>(8), 0);
    return 1;
  } catch (const std::invalid_argument &) {
  }
  std::println("核验 {} 个日心位置，最大分量差 {} AU", count, maximum);
}
