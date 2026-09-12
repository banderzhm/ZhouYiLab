/**
 * @file qi_men_pan.cppm
 * @brief 时家转盘奇门排盘器接口。
 */
export module ZhouYi.QiMen.Pan;

import ZhouYi.QiMen;
import ZhouYi.GanZhi;
import std;

export namespace ZhouYi::QiMen {

/**
 * @brief 生成时家转盘奇门盘。
 *
 * 排盘器只接受已经由历法层确定的节气与日、时干支，不自行推算历法，
 * 因而可以独立进行固定盘回归。
 */
class QiMenPanGenerator final {
public:
  /**
   * @brief 按节气及日时干支生成完整九宫盘。
   * @param solar_term 排盘所用节气。
   * @param day_gan 日干。
   * @param day_zhi 日支。
   * @param hour_gan 时干。
   * @param hour_zhi 时支。
   * @return 完整盘面；干支不成六十甲子时返回错误。
   */
  [[nodiscard]] static auto
  generate_pan(SolarTerm solar_term, ZhouYi::GanZhi::TianGan day_gan,
               ZhouYi::GanZhi::DiZhi day_zhi, ZhouYi::GanZhi::TianGan hour_gan,
               ZhouYi::GanZhi::DiZhi hour_zhi)
      -> std::expected<QiMenPan, std::string>;

  /** 兼容旧调用方的数字干支入口。 */
  [[nodiscard]] static auto
  generate_pan(SolarTerm solar_term, std::uint8_t day_gan, std::uint8_t day_zhi,
               std::uint8_t hour_gan, std::uint8_t hour_zhi)
      -> std::expected<QiMenPan, std::string>;
};

/** 将九宫盘格式化为适合人读的 Markdown 表格。 */
[[nodiscard]] std::string format_qi_men_pan(const QiMenPan &pan);

} // namespace ZhouYi::QiMen
