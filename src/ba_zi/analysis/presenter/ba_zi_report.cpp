// C++23 Module - 八字中文报告门面实现
module ZhouYi.BaZiAnalysis.Report;

import ZhouYi.BaZiAnalysis.Presenter;

namespace ZhouYi::BaZiAnalysis {

nlohmann::json to_json(const AnalysisResult &result) {
  return Detail::render_json(result);
}

nlohmann::json to_json(const TransitAnalysis &result) {
  return Detail::render_transit_json(result);
}

std::string to_zh(const AnalysisResult &result) {
  return Detail::render_zh(result);
}

std::string to_zh(const TransitAnalysis &result) {
  return Detail::render_transit_zh(result);
}

void write_zh(std::ostream &output, const AnalysisResult &result) {
  output << Detail::render_zh(result);
}

void write_zh(std::ostream &output, const TransitAnalysis &result) {
  output << Detail::render_transit_zh(result);
}

std::string format_analysis(const AnalysisResult &result) {
  return Detail::render_summary(result);
}

} // namespace ZhouYi::BaZiAnalysis
