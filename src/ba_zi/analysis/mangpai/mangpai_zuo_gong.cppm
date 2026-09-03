// C++23 Module - 盲派做功规则接口
export module ZhouYi.BaZiAnalysis.MangPai.ZuoGong;

import ZhouYi.BaZiAnalysis;
import std;

export namespace ZhouYi::BaZiAnalysis::MangPai::ZuoGong {

/** 识别干支关系并构建盲派主、辅做功链。 */
void build(AnalysisResult &result, const BaZi &chart);

} // namespace ZhouYi::BaZiAnalysis::MangPai::ZuoGong
