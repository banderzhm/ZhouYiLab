// C++23 Module - 八字原局关系识别接口
export module ZhouYi.BaZiAnalysis.Relations;

import ZhouYi.BaZiAnalysis.ZiPing.Contract;
import ZhouYi.BaZiBase;
import std;

export namespace ZhouYi::BaZiAnalysis::Detail {

std::vector<ZhouYi::BaZiAnalysis::BranchRelation>
detect_branch_relations(const ZhouYi::BaZiBase::BaZi &chart);
std::vector<ZhouYi::BaZiAnalysis::StemRelation>
detect_stem_relations(const ZhouYi::BaZiBase::BaZi &chart);

} // namespace ZhouYi::BaZiAnalysis::Detail
