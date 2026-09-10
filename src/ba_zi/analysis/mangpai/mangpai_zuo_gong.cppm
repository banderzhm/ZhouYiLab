// C++23 Module - 盲派做功规则接口
export module ZhouYi.BaZiAnalysis.MangPai.ZuoGong;

import ZhouYi.BaZiAnalysis.ZiPing.Contract;
import ZhouYi.BaZiBase;

export namespace ZhouYi::BaZiAnalysis::MangPai::ZuoGong {

/** 识别干支关系并构建盲派主、辅做功链。 */
void build(ZhouYi::BaZiAnalysis::AnalysisResult &result,
           const ZhouYi::BaZiBase::BaZi &chart);

} // namespace ZhouYi::BaZiAnalysis::MangPai::ZuoGong
