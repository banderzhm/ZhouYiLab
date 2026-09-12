/**
 * @file mei_hua_analysis_presenter.cppm
 * @brief 梅花易数中文排盘与占断报告接口。
 */
export module ZhouYi.MeiHua.Analysis.Presenter;

export import ZhouYi.MeiHua.Analysis.Contract;
import nlohmann.json;
import std;

export namespace ZhouYi::MeiHuaAnalysis {

/** 将梅花排盘和占断转换成中文字段 JSON。 */
nlohmann::json to_zh_json(const ZhouYi::MeiHua::MeiHuaPan &pan,
                          const AnalysisResult &analysis);

/** 写出适合人和大模型阅读的中文梅花易数报告。 */
void write_zh(std::ostream &output, const ZhouYi::MeiHua::MeiHuaPan &pan,
              const AnalysisResult &analysis);

} // namespace ZhouYi::MeiHuaAnalysis
