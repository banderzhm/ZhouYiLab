/**
 * @file qi_men.cppm
 * @brief 奇门遁甲公共类型与基础规则接口。
 *
 * 本模块只公开稳定数据契约。排盘、历法适配和格局分析分别位于独立模块，
 * 避免接口 BMI 承载实现细节。
 */
export module ZhouYi.QiMen;

import ZhouYi.BaZiBase;
import ZhouYi.GanZhi;
import ZhouYi.ZhMapper;
import nlohmann.json;
import std;

export namespace ZhouYi::QiMen {

using ZhouYi::BaZiBase::BaZi;
using ZhouYi::GanZhi::DiZhi;
using ZhouYi::GanZhi::TianGan;
using ZhouYi::GanZhi::WuXing;

/** 后天洛书九宫，枚举值即宫数。 */
enum class Palace : std::uint8_t {
  North = 1,     ///< 坎一宫，正北。
  SouthWest = 2, ///< 坤二宫，西南。
  East = 3,      ///< 震三宫，正东。
  SouthEast = 4, ///< 巽四宫，东南。
  Center = 5,    ///< 中五宫。
  NorthWest = 6, ///< 乾六宫，西北。
  West = 7,      ///< 兑七宫，正西。
  NorthEast = 8, ///< 艮八宫，东北。
  South = 9      ///< 离九宫，正南。
};

/** 八门；中宫使用 {@code None}。 */
enum class Gate : std::uint8_t {
  Xiu,                  ///< 休门。
  Sheng,                ///< 生门。
  Shang,                ///< 伤门。
  Du,                   ///< 杜门。
  Jing,                 ///< 景门。
  Si,                   ///< 死门。
  JingGate,             ///< 惊门。
  Kai,                  ///< 开门。
  None,                 ///< 中宫无门。
  Jing_Gate = JingGate, ///< 旧版惊门兼容别名。
  Jing_Center = None    ///< 旧版中宫无门兼容别名。
};

/** 九星。 */
enum class Star : std::uint8_t {
  TianPeng,  ///< 天蓬星。
  TianRui,   ///< 天芮星。
  TianChong, ///< 天冲星。
  TianFu,    ///< 天辅星。
  TianQin,   ///< 天禽星。
  TianXin,   ///< 天心星。
  TianZhu,   ///< 天柱星。
  TianRen,   ///< 天任星。
  TianYing   ///< 天英星。
};

/** 八神；中宫使用 {@code None}。 */
enum class Spirit : std::uint8_t {
  ZhiFu,   ///< 值符。
  TengShe, ///< 腾蛇。
  TaiYin,  ///< 太阴。
  LiuHe,   ///< 六合。
  BaiHu,   ///< 白虎。
  XuanWu,  ///< 玄武。
  JiuDi,   ///< 九地。
  JiuTian, ///< 九天。
  None     ///< 中宫无神。
};

/** 阴遁、阳遁。 */
enum class Dun : std::uint8_t {
  Yang, ///< 阳遁。
  Yin   ///< 阴遁。
};
/** 上元、中元、下元。 */
enum class Yuan : std::uint8_t {
  Shang, ///< 上元。
  Zhong, ///< 中元。
  Xia    ///< 下元。
};

/** 二十四节气，顺序与 tyme 的冬至起序一致。 */
enum class SolarTerm : std::uint8_t {
  DongZhi,     ///< 冬至。
  XiaoHan,     ///< 小寒。
  DaHan,       ///< 大寒。
  LiChun,      ///< 立春。
  YuShui,      ///< 雨水。
  JingZhe,     ///< 惊蛰。
  ChunFen,     ///< 春分。
  QingMing,    ///< 清明。
  GuYu,        ///< 谷雨。
  LiXia,       ///< 立夏。
  XiaoMan,     ///< 小满。
  MangZhong,   ///< 芒种。
  XiaZhi,      ///< 夏至。
  XiaoShu,     ///< 小暑。
  DaShu,       ///< 大暑。
  LiQiu,       ///< 立秋。
  ChuShu,      ///< 处暑。
  BaiLu,       ///< 白露。
  QiuFen,      ///< 秋分。
  HanLu,       ///< 寒露。
  ShuangJiang, ///< 霜降。
  LiDong,      ///< 立冬。
  XiaoXue,     ///< 小雪。
  DaXue        ///< 大雪。
};

/** 六甲旬首。 */
enum class JiaXun : std::uint8_t {
  JiaZi,   ///< 甲子旬。
  JiaXu,   ///< 甲戌旬。
  JiaShen, ///< 甲申旬。
  JiaWu,   ///< 甲午旬。
  JiaChen, ///< 甲辰旬。
  JiaYin   ///< 甲寅旬。
};
/** 宫内旺衰状态。 */
enum class Strength : std::uint8_t {
  Wang,   ///< 旺。
  Xiang,  ///< 相。
  Xiu,    ///< 休。
  Qiu,    ///< 囚。
  Fei,    ///< 废。
  Si,     ///< 死。
  Unknown ///< 尚未判定。
};
/** 格局吉凶属性。 */
enum class PatternNature : std::uint8_t {
  Auspicious,   ///< 吉格。
  Inauspicious, ///< 凶格。
  Neutral       ///< 中性结构。
};

/** 单条已经成格的盘局记录。 */
struct PatternFinding {
  std::string rule_id;           ///< 稳定规则编号。
  std::string name;              ///< 格局名称。
  PatternNature nature{};        ///< 吉凶属性。
  Palace palace{Palace::Center}; ///< 格局所在宫。
  std::string pan_ju_basis;      ///< 成格依据。
};

/** 单宫完整盘面。 */
struct PalaceInfo {
  Palace palace{Palace::Center};         ///< 宫位。
  Star star{Star::TianQin};              ///< 天盘九星主星。
  std::optional<Star> extra_star;        ///< 天禽寄宫时的附加星。
  Gate gate{Gate::None};                 ///< 人盘八门。
  Spirit spirit{Spirit::None};           ///< 神盘八神。
  TianGan tian_gan{TianGan::Jia};        ///< 天盘奇仪主干。
  std::optional<TianGan> extra_tian_gan; ///< 天禽寄宫附加天盘干。
  TianGan di_gan{TianGan::Jia};          ///< 地盘奇仪主干。
  std::optional<TianGan> extra_di_gan;   ///< 中五寄出的附加地盘干。
  std::optional<TianGan> hidden_gan;     ///< 暗干。
  std::array<DiZhi, 2> branches{DiZhi::Zi, DiZhi::Zi}; ///< 宫位地支。
  std::uint8_t branch_count{};                         ///< 有效地支数。
  bool is_zhi_fu{};                                    ///< 是否值符落宫。
  bool is_zhi_shi{};                                   ///< 是否值使落宫。
  bool is_void{};                                      ///< 是否旬空临宫。
  bool is_post_horse{};                                ///< 是否驿马临宫。
  bool is_ji_gong{};                                   ///< 是否承接中五寄宫。
  bool has_liu_yi_ji_xing{};                           ///< 是否六仪击刑。
  bool has_gate_pressure{};                            ///< 是否门迫。
  bool has_san_qi_tomb{};                              ///< 是否三奇入墓。
  Strength star_strength{Strength::Unknown};           ///< 九星旺衰。
  Strength gate_strength{Strength::Unknown};           ///< 八门旺衰。
  std::vector<PatternFinding> patterns;                ///< 本宫格局。
};

/** 时家转盘奇门完整盘面。 */
struct QiMenPan {
  Dun dun{Dun::Yang};                                        ///< 阴阳遁。
  Yuan yuan{Yuan::Shang};                                    ///< 上中下元。
  std::uint8_t ju{1};                                        ///< 局数，1..9。
  SolarTerm solar_term{SolarTerm::DongZhi};                  ///< 所用节气。
  JiaXun xun_shou{JiaXun::JiaZi};                            ///< 时柱旬首。
  TianGan xun_hidden_gan{TianGan::Wu};                       ///< 旬首所遁六仪。
  std::array<DiZhi, 2> void_branches{DiZhi::Xu, DiZhi::Hai}; ///< 时柱旬空。
  std::array<PalaceInfo, 9> palaces{};                       ///< 九宫。
  Star zhi_fu_star{Star::TianPeng};                          ///< 值符星。
  Gate zhi_shi_gate{Gate::Xiu};                              ///< 值使门。
  Palace zhi_fu_palace{Palace::North};                       ///< 值符落宫。
  Palace zhi_shi_palace{Palace::North};                      ///< 值使落宫。
  int solar_year{};
  int solar_month{};
  int solar_day{}; ///< 公历日期。
  int hour{};
  int minute{}; ///< 排盘时分。
  int lunar_year{};
  int lunar_month{};
  int lunar_day{};                             ///< 农历日期。
  bool is_leap_month{};                        ///< 是否闰月。
  std::optional<BaZi> ba_zi;                   ///< 四柱及旬空。
  std::vector<PatternFinding> global_patterns; ///< 全局格局。
};

std::string_view palace_name(Palace palace) noexcept;
std::string_view trigram_name(Palace palace) noexcept;
std::string_view gate_name(Gate gate) noexcept;
std::string_view star_name(Star star) noexcept;
std::string_view spirit_name(Spirit spirit) noexcept;
std::string_view solar_term_name(SolarTerm term) noexcept;
std::string_view yuan_name(Yuan yuan) noexcept;
std::string_view jia_xun_name(JiaXun xun) noexcept;
std::string_view strength_name(Strength strength) noexcept;

Dun get_dun_from_solar_term(SolarTerm term) noexcept;
std::uint8_t get_ju_from_solar_term_and_yuan(SolarTerm term,
                                             Yuan yuan) noexcept;
Yuan get_yuan_from_gan_zhi(TianGan day_gan, DiZhi day_zhi);
JiaXun get_jia_xun_from_gan_zhi(TianGan gan, DiZhi zhi);
TianGan get_liu_yi_from_jia_xun(JiaXun xun) noexcept;
std::array<DiZhi, 2> get_void_branches(JiaXun xun) noexcept;
Palace get_palace_from_number(std::uint8_t number);
std::uint8_t get_number_from_palace(Palace palace) noexcept;
Star get_star_at_palace(Palace palace);
Gate get_gate_at_palace(Palace palace) noexcept;
WuXing palace_element(Palace palace);
WuXing gate_element(Gate gate);
WuXing star_element(Star star);
std::pair<std::array<DiZhi, 2>, std::uint8_t>
palace_branches(Palace palace) noexcept;
Palace post_horse_palace(DiZhi hour_branch) noexcept;
void to_json(nlohmann::json &json, const QiMenPan &pan);

} // namespace ZhouYi::QiMen

namespace ZhouYi::Mapper {

/** 阴遁、阳遁的中文映射。 */
template <> struct ZhMap<ZhouYi::QiMen::Dun> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 2>{"阳遁", "阴遁"};
  }
};

/** 格局吉凶属性的中文映射；中性结构对应“中”。 */
template <> struct ZhMap<ZhouYi::QiMen::PatternNature> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 3>{"吉", "凶", "中"};
  }
};

} // namespace ZhouYi::Mapper
