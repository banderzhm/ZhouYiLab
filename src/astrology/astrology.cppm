export module ZhouYi.Astrology;
import std;

export namespace ZhouYi::Astrology {
/** @brief 星体与黄道点编号。 */
enum class Body {
  Sun,     /**< 太阳。 */
  Moon,    /**< 月亮。 */
  Mercury, /**< 水星。 */
  Venus,   /**< 金星。 */
  Mars,    /**< 火星。 */
  Jupiter, /**< 木星。 */
  Saturn,  /**< 土星。 */
  Uranus,  /**< 天王星。 */
  Neptune, /**< 海王星。 */
  Pluto    /**< 冥王星。 */
};
/** @brief 十二宫划分方式。 */
enum class HouseSystem {
  WholeSign, /**< 整宫制，上升星座从零度起第一宫。 */
  Equal,     /**< 等宫制，上升点起每宫三十度。 */
  Placidus   /**< 普拉西德时间分宫，极区返回错误。 */
};
/** @brief 出生或事件的时间地点与排盘配置。 */
struct ChartRequest {
  /** @brief UTC 儒略日，支持 [2415020.5, 2488434.5)，即 1900—2100 年。 */
  double julian_day_utc{2451545.0};
  /** @brief 地理东经为正，范围 [-180,180] 度。 */
  double longitude{};
  /** @brief 地理北纬为正，范围 (-90,90) 度。 */
  double latitude{};
  /** @brief 宫制，默认普拉西德。 */
  HouseSystem houses{HouseSystem::Placidus};
  /** @brief
   * 恒星黄道岁差值，单位度；空值为热带黄道，提供时由调用者指定该时刻数值。 */
  std::optional<double> ayanamsa;
  /** @brief 本时刻的 TT−UT1 秒数；空值采用 Astronomy Engine 默认模型。 */
  std::optional<double> delta_t_seconds;
  /** @brief 可选 JPL SPK Type 2 文件路径；提供时使用高精度星历，空值使用
   * Astronomy Engine。 */
  std::optional<std::string> ephemeris_file;
};
/** @brief 单个星体的地心视黄道位置。 */
struct BodyPosition {
  /** @brief 星体编号。 */
  Body body{};
  /** @brief 黄经，[0,360) 度。 */
  double longitude{};
  /** @brief 黄纬，[-90,90] 度。 */
  double latitude{};
  /** @brief 地心距离，AU；中点组合盘无物理距离时为空。 */
  std::optional<double> distance_au;
  /** @brief 黄经速度，度每天；负值为逆行。 */
  double speed{};
  /** @brief 星座编号，白羊为 0，双鱼为 11。 */
  int sign{};
  /** @brief 宫位，1—12。 */
  int house{};
};
/** @brief 相位定义。 */
struct AspectRule {
  /** @brief 相位目标角度，范围 [0,180]。 */
  double angle{};
  /** @brief 容许度，范围 [0,15]，单位度。 */
  double orb{};
};
/** @brief 相位参与项与精度。 */
struct Aspect {
  /** @brief 第一张盘的星体。 */
  Body first{};
  /** @brief 同盘或第二张盘的星体。 */
  Body second{};
  /** @brief 目标角度，单位度。 */
  double angle{};
  /** @brief 实际最短角距，[0,180] 度。 */
  double separation{};
  /** @brief 偏离精确相位的绝对度数。 */
  double orb{};
  /** @brief 按两星当前黄经速度判定是否接近精确相位。 */
  bool applying{};
  /** @brief 以 0.001 日线性步长判断，相位偏差变化小于 1e-6 度时为近静止。 */
  bool stationary{};
};
/** @brief 完整星盘，统一供分析和展示使用。 */
struct Chart {
  /** @brief 输入与计算配置。 */
  ChartRequest request;
  /** @brief 太阳至冥王星的十个位置。 */
  std::array<BodyPosition, 10> bodies;
  /** @brief 第一至第十二宫宫头，单位度。 */
  std::array<double, 12> cusps;
  /** @brief 上升点黄经，单位度。 */
  double ascendant{};
  /** @brief 天顶黄经，单位度。 */
  double midheaven{};
  /** @brief 本命、次限、太阳弧或中点组合盘等计算类型。 */
  std::string method{"本命盘"};
  /** @brief 生成本盘所用的出生盘或参与盘配置，按调用顺序保存。 */
  std::vector<ChartRequest> sources;
  /** @brief 推运或行运的目标 UTC 儒略日，原生时刻星盘为空。 */
  std::optional<double> target_julian_day;
  /** @brief 计算实际采用的 TT−UT1 秒数，供复核时间口径。 */
  double delta_t_seconds{};
  /** @brief 实际采用的星历后端名称与版本口径。 */
  std::string ephemeris_model;
};
/** @brief 双盘比较结果，保留参与盘及相位方向。 */
struct ChartComparison {
  /** @brief 本命盘或第一参与盘，相位 first 字段对应此盘。 */
  Chart first;
  /** @brief 行运盘或第二参与盘，相位 second 字段对应此盘。 */
  Chart second;
  /** @brief 双盘之间的相位。 */
  std::vector<Aspect> aspects;
  /** @brief 是否固定第一盘，用于行运判断。 */
  bool first_fixed{};
};
/** @brief 黄经归一化至 [0,360)；非有限输入抛出 invalid_argument。 @param
 * degrees 输入度数。 @return 归一化度数。 */
double normalize_longitude(double degrees);
/** @brief 民用时间转换 UTC 儒略日，公历且不接受闰秒。 @param year 年。 @param
 * month 月。 @param day 日。 @param hour 时。 @param minute 分。 @param second
 * 秒。 @param utc_offset_minutes 当时的 UTC 偏移分钟，包含夏令时。 @return UTC
 * 儒略日。 @throws std::invalid_argument 日期或偏移无效。 */
double civil_to_julian(int year, int month, int day, int hour, int minute,
                       double second, int utc_offset_minutes);
/** @brief 计算地心视黄经。 @param body 星体。 @param julian_day_utc UTC
 * 儒略日。 @param delta_t_seconds 显式 TT−UT1 秒数，空值使用默认模型。 @param
 * ephemeris_file 可选的 UTF-8 SPK 文件路径，空值使用 Astronomy Engine。 @return
 * 热带黄道视黄经。 */
double body_longitude(Body body, double julian_day_utc,
                      std::optional<double> delta_t_seconds = std::nullopt,
                      std::optional<std::string> ephemeris_file = std::nullopt);
/** @brief 计算十星、四轴、宫头与落宫。 @param request 时间地点与黄道口径。
 * @return 完整星盘。 @throws std::invalid_argument
 * 输入无效或宫制在当地不可计算。 @throws std::runtime_error
 * 指定的星历文件无效或不覆盖计算时刻。 */
Chart calculate_chart(const ChartRequest &request);
/** @brief 主要与常用次要相位默认容许度。 @return 可由调用者修改的规则数组。 */
std::vector<AspectRule> default_aspect_rules();
/** @brief 检测同盘相位，同星跳过且每对只输出一次。 @param chart 星盘。 @param
 * rules 相位角及容许度。 @return 相位集合。 */
std::vector<Aspect> natal_aspects(const Chart &chart,
                                  std::span<const AspectRule> rules);
/** @brief 检测比较盘或行运对本命的相位，保留参与盘方向。 @param first
 * 第一张盘。 @param second 第二张盘。 @param rules 相位规则。 @return
 * 包含同名星体之间相位的集合。 */
std::vector<Aspect> cross_aspects(const Chart &first, const Chart &second,
                                  std::span<const AspectRule> rules);
/** @brief 检测行运对本命的相位，本命星体固定，入出相只采用行运速度。 @param
 * natal 本命盘。 @param transit 行运盘。 @param rules 相位规则。 @return
 * 第一参与项为本命星体的相位集合。 */
std::vector<Aspect> transit_aspects(const Chart &natal, const Chart &transit,
                                    std::span<const AspectRule> rules);
/** @brief 生成包含两张盘的比较盘或行运结果。 @param first 第一盘。 @param
 * second 第二盘。 @param rules 相位规则。 @param first_fixed 行运时设为
 * true，本命星体固定。 @return 可完整输出双盘的比较结果。 */
ChartComparison compare_charts(const Chart &first, const Chart &second,
                               std::span<const AspectRule> rules,
                               bool first_fixed = false);
/** @brief 中点组合盘，取短弧中点并重新判定落宫。 @param first 第一张盘。 @param
 * second 第二张盘。 @return 中点组合盘。 */
Chart composite_chart(const Chart &first, const Chart &second);
/** @brief 次限推运，一回归年对应一日，宫轴采用推运时刻的出生地点。 @param natal
 * 出生配置。 @param target_julian_day 目标 UTC 日。 @param
 * progressed_delta_t_seconds 推运星历时刻的 ΔT，空值使用默认模型，与出生 ΔT
 * 独立。 @return 次限盘。 */
Chart secondary_progression(
    const ChartRequest &natal, double target_julian_day,
    std::optional<double> progressed_delta_t_seconds = std::nullopt);
/** @brief 真太阳弧推运，所有星体与宫头统一移动次限太阳弧。 @param natal
 * 出生配置。 @param target_julian_day 目标 UTC 日。 @param
 * progressed_delta_t_seconds 次限太阳所用星历时刻的 ΔT，空值使用默认模型。
 * @return 太阳弧盘。 */
Chart solar_arc_progression(
    const ChartRequest &natal, double target_julian_day,
    std::optional<double> progressed_delta_t_seconds = std::nullopt);
/** @brief 搜索太阳或月亮首次到达指定黄经的 UTC 日。 @param body 太阳或月亮。
 * @param target_longitude 热带黄经。 @param start_julian_day 搜索起始 UTC
 * 日，包含端点。 @param limit_days 搜索长度，大于零且不超过 400 日。 @param
 * delta_t_seconds 搜索区间使用的固定 ΔT 秒数，空值逐时采用默认模型。 @param
 * ephemeris_file 搜索全程使用的 UTF-8 SPK 文件路径，空值使用 Astronomy Engine。
 * @return 精确到约 0.1 秒的候选时刻。 @throws std::invalid_argument 输入无效。
 * @throws std::runtime_error 区间内没有候选。 */
double find_return(Body body, double target_longitude, double start_julian_day,
                   double limit_days,
                   std::optional<double> delta_t_seconds = std::nullopt,
                   std::optional<std::string> ephemeris_file = std::nullopt);
/** @brief 太阳或月亮返照星盘。 @param natal 出生盘配置。 @param body
 * 太阳或月亮。 @param start 搜索起点 UTC 日。 @param longitude 返照地点东经。
 * @param latitude 返照地点北纬。 @param return_delta_t_seconds
 * 返照搜索与排盘所用 ΔT 秒数，空值逐时采用默认模型，与出生时刻独立。 @return
 * 返照盘。 */
Chart return_chart(const ChartRequest &natal, Body body, double start,
                   double longitude, double latitude,
                   std::optional<double> return_delta_t_seconds = std::nullopt);
/** @brief 星体中文名。 @param body 星体。 @return 中文名。 */
std::string_view body_name(Body body);
/** @brief 星座中文名。 @param sign 0—11。 @return 中文名。 */
std::string_view sign_name(int sign);
} // namespace ZhouYi::Astrology
