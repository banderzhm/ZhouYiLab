module;
#include "erfa.h"
module ZhouYi.Astrology.JplEphemeris;
import std;
namespace ZhouYi::Astrology::JplEphemeris {
namespace {
constexpr double au = 149597870.7;
constexpr double light_speed = 299792.458;
constexpr double rad = std::numbers::pi / 180;
struct Segment {
  double start, end;
  int target, center;
  std::size_t begin, last;
};
class Kernel {
  std::vector<unsigned char> bytes_;
  std::vector<Segment> segments_;
  std::uint64_t integer(std::size_t offset, std::size_t size) const {
    if (offset > bytes_.size() || size > bytes_.size() - offset)
      throw std::runtime_error("SPK 地址越界");
    std::uint64_t value = 0;
    if constexpr (std::endian::native == std::endian::little)
      std::memcpy(&value, bytes_.data() + offset, size);
    else
      for (std::size_t i = 0; i < size; ++i)
        value |= std::uint64_t(bytes_[offset + i]) << (8 * i);
    return value;
  }
  double real(std::size_t offset) const {
    const double value = std::bit_cast<double>(integer(offset, 8));
    if (!std::isfinite(value))
      throw std::runtime_error("SPK 含非有限数值");
    return value;
  }
  std::size_t count(double value) const {
    if (value < 0 || value > bytes_.size() || std::floor(value) != value)
      throw std::runtime_error("SPK 记录计数无效");
    return static_cast<std::size_t>(value);
  }
  double word(std::size_t address) const {
    if (address == 0 || address > bytes_.size() / 8)
      throw std::runtime_error("SPK 字地址无效");
    return real((address - 1) * 8);
  }

public:
  explicit Kernel(const std::filesystem::path &file) {
    std::ifstream input(file, std::ios::binary | std::ios::ate);
    if (!input)
      throw std::runtime_error("无法打开 JPL 星历文件");
    const auto length = input.tellg();
    if (length < 1024 || length > 128 * 1024 * 1024)
      throw std::runtime_error("SPK 文件长度无效");
    bytes_.resize(static_cast<std::size_t>(length));
    input.seekg(0);
    if (!input.read(reinterpret_cast<char *>(bytes_.data()), length))
      throw std::runtime_error("SPK 文件读取不完整");
    const std::string_view header(reinterpret_cast<const char *>(bytes_.data()),
                                  bytes_.size());
    if (header.substr(0, 8) != "DAF/SPK " ||
        header.substr(88, 8) != "LTL-IEEE" || integer(8, 4) != 2 ||
        integer(12, 4) != 6)
      throw std::runtime_error("仅支持小端 DAF/SPK Type 2，ND=2、NI=6");
    std::size_t record = integer(76, 4);
    std::set<std::size_t> visited;
    while (record) {
      if (record > bytes_.size() / 1024 || !visited.insert(record).second)
        throw std::runtime_error("SPK 摘要链无效");
      const auto offset = (record - 1) * 1024;
      const auto n = count(real(offset + 16));
      if (n > 25)
        throw std::runtime_error("SPK 摘要数超限");
      for (std::size_t i = 0; i < n; ++i) {
        const auto p = offset + 24 + i * 40;
        if (integer(p + 24, 4) != 1 || integer(p + 28, 4) != 2)
          throw std::runtime_error("SPK 坐标框架或类型不受支持");
        Segment s{real(p),
                  real(p + 8),
                  static_cast<int>(integer(p + 16, 4)),
                  static_cast<int>(integer(p + 20, 4)),
                  integer(p + 32, 4),
                  integer(p + 36, 4)};
        if (s.start >= s.end || s.begin < 1 || s.last < s.begin + 4 ||
            s.last > bytes_.size() / 8)
          throw std::runtime_error("SPK 段边界无效");
        segments_.push_back(s);
      }
      record = count(real(offset));
    }
    if (segments_.empty())
      throw std::runtime_error("SPK 未含星历段");
  }
  State state(int target, double seconds, int depth = 0) const {
    if (target == 0)
      return {};
    if (depth > 8)
      throw std::runtime_error("SPK 中心链循环或过深");
    const auto found = std::find_if(
        segments_.rbegin(), segments_.rend(), [&](const Segment &s) {
          return s.target == target && seconds >= s.start && seconds <= s.end;
        });
    if (found == segments_.rend())
      throw std::runtime_error("SPK 未覆盖目标或时刻");
    const auto &s = *found;
    const double start = word(s.last - 3), interval = word(s.last - 2);
    const auto size = count(word(s.last - 1)), records = count(word(s.last));
    if (interval <= 0 || size < 5 || (size - 2) % 3 || records == 0 ||
        records > (s.last - s.begin - 3) / size)
      throw std::runtime_error("SPK 多项式目录无效");
    const auto record =
        std::min(count(std::floor((seconds - start) / interval)), records - 1);
    const auto address = s.begin + record * size;
    const double middle = word(address), radius = word(address + 1);
    if (radius <= 0)
      throw std::runtime_error("SPK 多项式时间半径无效");
    const double x = (seconds - middle) / radius;
    if (std::abs(x) > 1.000000001)
      throw std::runtime_error("SPK 多项式超出定义区间");
    State result = state(s.center, seconds, depth + 1);
    const auto n = (size - 2) / 3;
    for (std::size_t axis = 0; axis < 3; ++axis) {
      double t0 = 1, t1 = x, d0 = 0, d1 = 1, position = 0, velocity = 0;
      for (std::size_t k = 0; k < n; ++k) {
        double t, derivative;
        if (k == 0) {
          t = t0;
          derivative = d0;
        } else if (k == 1) {
          t = t1;
          derivative = d1;
        } else {
          t = 2 * x * t1 - t0;
          derivative = 2 * t1 + 2 * x * d1 - d0;
          t0 = t1;
          t1 = t;
          d0 = d1;
          d1 = derivative;
        }
        const double coefficient = word(address + 2 + axis * n + k);
        position += coefficient * t;
        velocity += coefficient * derivative / radius;
      }
      result.position[axis] += position;
      result.velocity[axis] += velocity;
    }
    return result;
  }
};
std::shared_ptr<const Kernel> load(std::string_view file) {
  static std::mutex mutex;
  static std::filesystem::path cached_path;
  static std::shared_ptr<const Kernel> cached;
  const auto requested =
      std::filesystem::absolute(
          std::filesystem::path(std::u8string(file.begin(), file.end())))
          .lexically_normal();
  std::lock_guard lock(mutex);
  if (cached && requested.is_absolute() && requested == cached_path)
    return cached;
  const auto path = std::filesystem::weakly_canonical(requested);
  if (!cached || cached_path != path) {
    auto next = std::make_shared<Kernel>(path);
    cached_path = path;
    cached = std::move(next);
  }
  return cached;
}
double norm(const std::array<double, 3> &a) {
  return std::hypot(a[0], a[1], a[2]);
}
} // namespace
State barycentric_state(std::string_view file, int target, double days) {
  if (!std::isfinite(days) || !std::isfinite(days * 86400) || target < 0)
    throw std::invalid_argument("JPL 目标或时间无效");
  return load(file)->state(target, days * 86400);
}
ApparentPosition apparent_position(std::string_view file, int body, double tt,
                                   double ut) {
  if (body < 0 || body > 9 || !std::isfinite(tt) || !std::isfinite(ut))
    throw std::invalid_argument("JPL 星体或时间无效");
  constexpr std::array ids{10, 301, 199, 299, 4, 5, 6, 7, 8, 9};
  const auto kernel = load(file);
  const double seconds =
      tt * 86400 +
      eraDtdb(2451545.0, tt, ut + 0.5 - std::floor(ut + 0.5), 0, 0, 0);
  const auto earth = kernel->state(399, seconds);
  const auto sun = kernel->state(10, seconds);
  double delay = 0;
  State target;
  std::array<double, 3> ray{};
  for (int iteration = 0; iteration < 12; ++iteration) {
    target = kernel->state(ids[body], seconds - delay);
    for (int k = 0; k < 3; ++k)
      ray[k] = target.position[k] - earth.position[k];
    const double next = norm(ray) / light_speed;
    if (std::abs(next - delay) < 1e-8)
      break;
    delay = next;
    if (iteration == 11)
      throw std::runtime_error("JPL 光行时迭代未收敛");
  }
  const double distance = norm(ray);
  std::array<double, 3> e{}, q{};
  for (int k = 0; k < 3; ++k) {
    e[k] = earth.position[k] - sun.position[k];
    q[k] = target.position[k] - sun.position[k];
  }
  const double em = norm(e) / au, qm = norm(q);
  double p[3], natural[3], velocity[3], proper[3], true_equator[3];
  for (int k = 0; k < 3; ++k) {
    p[k] = ray[k] / distance;
    e[k] /= em * au;
    velocity[k] = earth.velocity[k] / light_speed;
  }
  if (body != 0) {
    for (double &component : q)
      component /= qm;
    eraLd(1, p, q.data(), e.data(), em, 1e-6, natural);
  } else
    std::copy(p, p + 3, natural);
  double vv = 0;
  for (double v : velocity)
    vv += v * v;
  eraAb(natural, velocity, em, std::sqrt(1 - vv), proper);
  double matrix[3][3];
  eraPnm06a(2451545.0, tt, matrix);
  eraRxp(matrix, proper, true_equator);
  double dpsi, deps;
  eraNut06a(2451545.0, tt, &dpsi, &deps);
  const double eps = eraObl06(2451545.0, tt) + deps;
  const double y =
      std::cos(eps) * true_equator[1] + std::sin(eps) * true_equator[2];
  const double z =
      -std::sin(eps) * true_equator[1] + std::cos(eps) * true_equator[2];
  double longitude = std::atan2(y, true_equator[0]) / rad;
  if (longitude < 0)
    longitude += 360;
  return {longitude, std::atan2(z, std::hypot(true_equator[0], y)) / rad,
          distance / au};
}
} // namespace ZhouYi::Astrology::JplEphemeris
