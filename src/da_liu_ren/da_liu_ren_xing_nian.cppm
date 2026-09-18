// C++23 Module - 大六壬行年（年宫）推算
module;

export module ZhouYi.DaLiuRen.XingNian;

import ZhouYi.GanZhi;
import ZhouYi.tyme;
import std;

export namespace ZhouYi::DaLiuRen {

using ZhouYi::GanZhi::DiZhi;
using ZhouYi::GanZhi::TianGan;

/**
 * @brief 行年（年宫）推算口径。
 *
 * 两种口径的**起点不同**，同一个人算出的行年可能不同，因此必须显式选择，
 * 不能由实现替调用方默认：
 * - LiuJiaClassic：《大六壬类集·六甲起行年法》，按生年所值之旬定起点；
 * - Modern：现代通行法，男不问生年一律一岁起丙寅、女一律一岁起壬申。
 */
enum class XingNianRule {
  LiuJiaClassic, ///< 《大六壬类集》古法：分旬定起点，男顺女逆。
  Modern         ///< 现代通行法：男女各一固定起点，男顺女逆。
};

/** @brief 行年推算输入：求测者出生时刻 + 起课时刻。 */
struct XingNianInput {
  /** @brief 出生公历年份。 */
  int birth_year{};
  /** @brief 出生公历月份，1—12。 */
  int birth_month{};
  /** @brief 出生公历日。 */
  int birth_day{};
  /** @brief 出生小时，0—23。 */
  int birth_hour{};
  /** @brief 出生分钟，0—59。 */
  int birth_minute{};
  /** @brief 求测者是否男命：决定行年的顺行/逆行，必需。 */
  bool is_male{true};
  /** @brief 起课公历年份，用于求虚岁。 */
  int casting_year{};
  /** @brief 起课公历月份，1—12。 */
  int casting_month{};
  /** @brief 起课公历日。 */
  int casting_day{};
  /** @brief 起课小时，0—23。 */
  int casting_hour{};
  /** @brief 起课分钟，0—59。 */
  int casting_minute{};
};

/** @brief 行年推算结果。 */
struct XingNianResult {
  /** @brief 虚岁：出生当年记一岁，按太岁（立春换年）计。 */
  int nominal_age{};
  /** @brief 生年所值之太岁年（已按立春换年）。 */
  int birth_cycle_year{};
  /** @brief 本命：生年地支，即常说的属相。 */
  DiZhi ben_ming{DiZhi::Zi};
  /** @brief 行年地支：六壬断课实际取用的那一支。 */
  DiZhi xing_nian{DiZhi::Zi};
  /** @brief 行年干支全称（如 "丙辰"）；六壬重地支，干支供展示与核对。 */
  std::string xing_nian_gan_zhi;
  /** @brief 采用的推算口径。 */
  XingNianRule rule{XingNianRule::LiuJiaClassic};
};

/**
 * @brief 推算行年与本命。
 *
 * 年柱一律以**立春**换年（六壬与命理同口径）：出生时刻早于当年立春的按上一年计。
 * 虚岁 = 起课太岁年 − 出生太岁年 + 1，出生当年即为一岁。
 *
 * 古法（《大六壬类集·六甲起行年法》原文）：
 * @verbatim
 * 甲子旬内生人（男起丙寅，女起壬申）男顺女逆行至本命位上，即其行年也。
 * 甲戌旬起（丙子，壬午） 甲申旬起（丙戌，壬辰） 甲午旬起（丙申，壬寅）
 * 甲辰旬起（丙午，壬子） 甲寅旬起（丙辰，壬戌） 皆从起盘数起。
 * @endverbatim
 * 即：男命起点为「丙」配本旬首支前一位循环，女命起点为男命起点冲位（相差六支）；
 * 男逐年顺行一位、女逐年逆行一位，六十甲子周而复始。
 *
 * 现代通行法：男一岁起丙寅顺行，女一岁起壬申逆行。
 *
 * @param input 出生时刻、性别与起课时刻。
 * @param rule 推算口径；默认古法。
 * @return 本命、行年与虚岁。
 * @throws std::invalid_argument 出生时刻或起课时刻不是合法日期时间。
 */
XingNianResult calculate_xing_nian(const XingNianInput &input,
                                   XingNianRule rule =
                                       XingNianRule::LiuJiaClassic);

/**
 * @brief 按出生时刻取本命地支（生年地支，立春换年）。
 * @param year 出生公历年。
 * @param month 出生公历月。
 * @param day 出生公历日。
 * @param hour 出生小时。
 * @param minute 出生分钟。
 * @return 生年地支。
 */
DiZhi ben_ming_of(int year, int month, int day, int hour, int minute);

} // namespace ZhouYi::DaLiuRen
