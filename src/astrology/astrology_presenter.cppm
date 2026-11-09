export module ZhouYi.Astrology.Presenter;
import std;
import nlohmann.json;
import ZhouYi.Astrology;
export namespace ZhouYi::Astrology::Presenter {
/** @brief 从同一份星盘和相位生成 JSON。 @param chart 星盘。 @param aspects
 * 已算相位。 @return 含配置、四轴、宫头、星体与相位的 JSON。 */
nlohmann::json to_json(const Chart &chart, std::span<const Aspect> aspects);
/** @brief 输出完整中文星盘与相位表。 @param output 输出流。 @param chart
 * 星盘。 @param aspects 已算相位。 */
void write_zh(std::ostream &output, const Chart &chart,
              std::span<const Aspect> aspects);
/** @brief 将两张完整星盘和交叉相位输出为 JSON。 @param comparison 双盘结果。
 * @return 保留 first/second 所属的 JSON。 */
nlohmann::json to_json(const ChartComparison &comparison);
/** @brief 输出两张完整星盘和交叉相位。 @param output 输出流。 @param comparison
 * 双盘结果。 */
void write_zh(std::ostream &output, const ChartComparison &comparison);
} // namespace ZhouYi::Astrology::Presenter
