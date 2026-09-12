// C++23 Module - 六爻分析展示接口
export module ZhouYi.LiuYaoAnalysis.Presenter;

export import ZhouYi.LiuYaoAnalysis.Contract;
import nlohmann.json;
import std;

export namespace ZhouYi::LiuYaoAnalysis {

/** @brief 将六爻分析转换为带中文字段的 JSON。 */
nlohmann::json to_zh_json(const AnalysisResult &result);

/** @brief 写出适合人和大模型阅读的中文六爻分析报告。 */
void write_zh(std::ostream &output, const AnalysisResult &result);

} // namespace ZhouYi::LiuYaoAnalysis
