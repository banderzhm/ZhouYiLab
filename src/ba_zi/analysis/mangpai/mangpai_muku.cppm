// C++23 Module - 盲派墓库与旬空规则接口
export module ZhouYi.BaZiAnalysis.MangPai.MuKu;

import ZhouYi.BaZiAnalysis.ZiPing.Contract;
import ZhouYi.BaZiBase;

export namespace ZhouYi::BaZiAnalysis::MangPai::MuKu {

/** 计算原局旬空并生成墓库状态的基础证据。 */
void build(ZhouYi::BaZiAnalysis::AnalysisResult &result,
           const ZhouYi::BaZiBase::BaZi &chart);

} // namespace ZhouYi::BaZiAnalysis::MangPai::MuKu
