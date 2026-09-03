// C++23 Module - 八字分析结果展示接口
export module ZhouYi.BaZiAnalysis.Presenter;

import ZhouYi.BaZiAnalysis;
import nlohmann.json;
import std;

export namespace ZhouYi::BaZiAnalysis::Detail {

// 展示器只负责将既有分析结果投影为不同输出格式，不参与命理计算。
nlohmann::json render_json(const AnalysisResult &result);
nlohmann::json render_transit_json(const TransitAnalysis &result);
std::string render_zh(const AnalysisResult &result);
std::string render_transit_zh(const TransitAnalysis &result);
std::string render_summary(const AnalysisResult &result);

} // namespace ZhouYi::BaZiAnalysis::Detail
