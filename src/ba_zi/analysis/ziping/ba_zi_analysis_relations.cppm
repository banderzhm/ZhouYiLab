// C++23 Module - 八字原局关系识别接口
export module ZhouYi.BaZiAnalysis.Relations;

import ZhouYi.BaZiAnalysis;
import std;

export namespace ZhouYi::BaZiAnalysis::Detail {

std::vector<BranchRelation> detect_branch_relations(const BaZi &chart);
std::vector<StemRelation> detect_stem_relations(const BaZi &chart);

} // namespace ZhouYi::BaZiAnalysis::Detail
