// C++23 Module - 盲派墓库与旬空规则接口
export module ZhouYi.BaZiAnalysis.MangPai.MuKu;

import ZhouYi.BaZiAnalysis;
import std;

export namespace ZhouYi::BaZiAnalysis::MangPai::MuKu {

/** 计算原局旬空并生成墓库状态的基础证据。 */
void build(AnalysisResult &result, const BaZi &chart);

} // namespace ZhouYi::BaZiAnalysis::MangPai::MuKu
