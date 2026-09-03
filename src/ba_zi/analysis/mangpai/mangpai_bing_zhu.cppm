// C++23 Module - 盲派宾主体用规则接口
export module ZhouYi.BaZiAnalysis.MangPai.BingZhu;

import ZhouYi.BaZiAnalysis;
import std;

export namespace ZhouYi::BaZiAnalysis::MangPai::BingZhu {

/** 构建盲派透干、藏干、体神和目标用明细。 */
void build(AnalysisResult &result, const BaZi &chart);

} // namespace ZhouYi::BaZiAnalysis::MangPai::BingZhu
