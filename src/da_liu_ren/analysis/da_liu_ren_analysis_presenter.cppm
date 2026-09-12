// C++23 Module - 大六壬中文分析报告
export module ZhouYi.DaLiuRenAnalysis.Presenter;

export import ZhouYi.DaLiuRenAnalysis.Contract;

import ZhouYi.DaLiuRen;
import nlohmann.json;
import std;

export namespace ZhouYi::DaLiuRenAnalysis {

/** @brief 将排盘和占断结果转换为中文字段 JSON。 */
nlohmann::json to_zh_json(const ZhouYi::DaLiuRen::DaLiuRenResult &pan,
                          const AnalysisResult &analysis);

/** @brief 写出包含完整排盘与占断依据的中文文本报告。 */
void write_zh(std::ostream &output, const ZhouYi::DaLiuRen::DaLiuRenResult &pan,
              const AnalysisResult &analysis);

} // namespace ZhouYi::DaLiuRenAnalysis
