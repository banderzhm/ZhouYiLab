/**
 * @file qi_men_controller.cppm
 * @brief 奇门遁甲历法适配与应用入口。
 */
export module ZhouYi.QiMen.Controller;

import ZhouYi.QiMen;
import ZhouYi.QiMen.Pan;
import ZhouYi.TrueSolarTime;
import nlohmann.json;
import std;

export namespace ZhouYi::QiMen {

/** 奇门排盘应用服务。 */
class QiMenController final {
public:
  /**
   * @brief 按公历时刻排盘。
   * @param location
   * 可选出生地；未提供时严格使用输入钟表时刻，不作真太阳时修正。
   */
  [[nodiscard]] static auto pai_pan_solar(
      int year, int month, int day, int hour, int minute = 0,
      const std::optional<ZhouYi::Time::Location> &location = std::nullopt)
      -> std::expected<QiMenPan, std::string>;

  /**
   * @brief 按农历时刻排盘。
   * @param month 正数为常月，负数为闰月。
   * @param location 可选出生地；未提供时不作真太阳时修正。
   */
  [[nodiscard]] static auto pai_pan_lunar(
      int year, int month, int day, int hour, int minute = 0,
      const std::optional<ZhouYi::Time::Location> &location = std::nullopt)
      -> std::expected<QiMenPan, std::string>;

  /** 查询指定宫位。 */
  [[nodiscard]] static auto query_palace_info(const QiMenPan &pan,
                                              Palace palace)
      -> std::expected<PalaceInfo, std::string>;
  /** 输出人读 Markdown 盘面。 */
  [[nodiscard]] static std::string get_pan_description(const QiMenPan &pan);
  /** 输出 JSON 文本。 */
  [[nodiscard]] static std::string get_pan_json(const QiMenPan &pan);
  /** 兼容原有有序 JSON 接口。 */
  [[nodiscard]] static std::string get_pan_json_ordered(const QiMenPan &pan);
  /** 返回 JSON 对象。 */
  [[nodiscard]] static nlohmann::json get_pan_json_object(const QiMenPan &pan);
  /** 比较决定盘局的核心字段。 */
  [[nodiscard]] static bool is_same_pan(const QiMenPan &left,
                                        const QiMenPan &right) noexcept;
};

/** 兼容入口；深度格局由独立分析模块提供。 */
class QiMenAnalyzer final {
public:
  [[nodiscard]] static auto analyze_auspiciousness(const QiMenPan &pan,
                                                   Palace palace)
      -> std::expected<std::string, std::string>;
  [[nodiscard]] static std::string get_summary(const QiMenPan &pan);
};

} // namespace ZhouYi::QiMen
