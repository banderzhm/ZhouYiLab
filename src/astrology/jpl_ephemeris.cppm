export module ZhouYi.Astrology.JplEphemeris;
import std;
export namespace ZhouYi::Astrology::JplEphemeris {
/** @brief J2000/ICRF 太阳系质心状态。 */
struct State {
  /** @brief 位置分量，单位千米。 */
  std::array<double, 3> position{};
  /** @brief 速度分量，单位千米每秒。 */
  std::array<double, 3> velocity{};
};
/** @brief 地心视黄道坐标。 */
struct ApparentPosition {
  /** @brief 日期真黄道黄经，[0,360) 度。 */
  double longitude{};
  /** @brief 黄纬，单位度。 */
  double latitude{};
  /** @brief 地心光行时距离，AU。 */
  double distance_au{};
};
/** @brief 读取 SPK Type 2 几何状态，按目标与中心链递归至太阳系质心。
 * @param file DE440s 文件路径。
 * @param target NAIF 目标编号，0 为太阳系质心，399 为地球。
 * @param tdb_days J2000 起算的 TDB 日数。
 * @return 几何质心位置和速度。
 * @throws std::runtime_error 文件格式、覆盖范围或目标不受支持。
 */
State barycentric_state(std::string_view file, int target, double tdb_days);
/** @brief 计算经光行时、太阳引力偏折及光行差修正的地心视位置。
 * @param file DE440s 文件路径。
 * @param body
 * 太阳、月亮、水星、金星、火星、木星、土星、天王星、海王星、冥王星，编号 0—9。
 * @param tt_days J2000 起算 TT 日数。
 * @param ut_days J2000 起算 UT1 日数。
 * @return 日期真黄道坐标。
 * @throws std::invalid_argument 星体或时间无效。
 * @throws std::runtime_error 星历文件无效或不覆盖该时刻。
 */
ApparentPosition apparent_position(std::string_view file, int body,
                                   double tt_days, double ut_days);
} // namespace ZhouYi::Astrology::JplEphemeris
