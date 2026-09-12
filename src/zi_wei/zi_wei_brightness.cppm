// 紫微斗数星曜亮度流派模块（接口）
export module ZhouYi.ZiWei.Brightness;

import std;
import ZhouYi.ZiWei.Constants;

export namespace ZhouYi::ZiWei {

/**
 * @brief 星曜亮度表口径。
 *
 * 同一星曜在同一地支的庙旺可能因流派资料不同而异。调用方必须明确所用
 * 口径；排盘默认采用 Metis 当前网站的“混合亮度表”。
 */
enum class BrightnessSchool {
  MetisDefault = 0, ///< Metis 混合亮度表：斗数全书与文墨口径交叉校验。
  Iztro,            ///< iztro 2.5.8 原始七级亮度表。
};

/**
 * @brief 获取十四主星十二宫亮度表。
 * @param star 十四主星。
 * @param school 亮度流派。
 * @return 以寅宫为 0，依次至丑宫的十二格亮度。
 */
std::array<LiangDu, 12> get_zhu_xing_liang_du_table(
    ZhuXing star, BrightnessSchool school = BrightnessSchool::MetisDefault);

/**
 * @brief 获取辅星十二宫亮度表。
 * @param star 辅星。
 * @param school 亮度流派。
 * @return 以寅宫为 0，依次至丑宫的十二格亮度；该流派未定级者为空。
 */
std::array<LiangDu, 12> get_fu_xing_liang_du_table(
    FuXing star, BrightnessSchool school = BrightnessSchool::MetisDefault);

/**
 * @brief 获取煞星十二宫亮度表。
 * @param star 煞星。
 * @param school 亮度流派。
 * @return 以寅宫为 0，依次至丑宫的十二格亮度；不可达或未定级者为“平”。
 */
std::array<LiangDu, 12> get_sha_xing_liang_du_table(
    ShaXing star, BrightnessSchool school = BrightnessSchool::MetisDefault);

/**
 * @brief 获取杂曜十二宫亮度表。
 * @param star 杂曜。
 * @param school 亮度流派。
 * @return 以寅宫为 0，依次至丑宫的十二格亮度；该流派未定级者为“平”。
 */
std::array<std::optional<LiangDu>, 12> get_za_yao_liang_du_table(
    ZaYao star, BrightnessSchool school = BrightnessSchool::MetisDefault);

} // namespace ZhouYi::ZiWei
