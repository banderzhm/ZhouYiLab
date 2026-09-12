/**
 * @file qi_men_analysis_presenter.cppm
 * @brief 奇门遁甲中文占断报告接口。
 */
export module ZhouYi.QiMen.Analysis.Presenter;

export import ZhouYi.QiMen.Analysis.Contract;

import ZhouYi.QiMen;
import nlohmann.json;
import std;

export namespace ZhouYi::QiMenAnalysis {

/** 将排盘与占断转换为中文字段 JSON。 */
nlohmann::json to_zh_json(const ZhouYi::QiMen::QiMenPan &pan,
                          const AnalysisResult &analysis);

/** 写出包含九宫排盘、用神落宫与断验依据的中文文本报告。 */
void write_zh(std::ostream &output, const ZhouYi::QiMen::QiMenPan &pan,
              const AnalysisResult &analysis);

} // namespace ZhouYi::QiMenAnalysis
