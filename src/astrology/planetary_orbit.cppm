export module ZhouYi.Astrology.PlanetaryOrbit;
import std;

export namespace ZhouYi::Astrology {
/** @brief VSOP 行星编号。 */
enum class Planet {
  Mercury, /**< 水星。 */
  Venus,   /**< 金星。 */
  Earth,   /**< 地球。 */
  Mars,    /**< 火星。 */
  Jupiter, /**< 木星。 */
  Saturn,  /**< 土星。 */
  Uranus,  /**< 天王星。 */
  Neptune  /**< 海王星。 */
};
/** @brief J2000 赤道坐标系中的日心位置，单位 AU。 */
struct OrbitVector {
  /** @brief 春分点方向分量，单位 AU。 */
  double x{};
  /** @brief 赤道平面内垂直于 x 的分量，单位 AU。 */
  double y{};
  /** @brief 北天极方向分量，单位 AU。 */
  double z{};
};
/**
 * @brief 根据地球时计算几何日心位置。
 * @param planet 水星至海王星，含地球。
 * @param tt_days J2000 起算的 TT 日数，支持 1900—2100 年约定区间。
 * @return 无光行时与光行差修正的 J2000 赤道坐标。
 * @throws std::invalid_argument 非法行星或非有限、超范围的时间。
 */
OrbitVector heliocentric_position(Planet planet, double tt_days);
} // namespace ZhouYi::Astrology
