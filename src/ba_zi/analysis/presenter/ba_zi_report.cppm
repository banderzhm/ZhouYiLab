// C++23 Module - 八字中文报告门面接口
// 负责结构化序列化与中文报告，不参与分析规则计算。
export module ZhouYi.BaZiAnalysis.Report;

import nlohmann.json;
import ZhouYi.BaZiAnalysis.ZiPing.Contract;
import std;

export namespace ZhouYi::BaZiAnalysis {

/** @brief 序列化完整子平分析结果。 */
nlohmann::json to_json(const AnalysisResult &result);

/** @brief 序列化联合岁运结果。 */
nlohmann::json to_json(const TransitAnalysis &result);

/** @brief 生成完整中文子平报告。 */
std::string to_zh(const AnalysisResult &result);

/** @brief 生成联合岁运中文报告。 */
std::string to_zh(const TransitAnalysis &result);

/** @brief 将完整中文子平报告写入输出流。 */
void write_zh(std::ostream &output, const AnalysisResult &result);

/** @brief 将联合岁运中文报告写入输出流。 */
void write_zh(std::ostream &output, const TransitAnalysis &result);

/** @brief 生成兼容控制台场景的简明摘要。 */
std::string format_analysis(const AnalysisResult &result);

} // namespace ZhouYi::BaZiAnalysis
