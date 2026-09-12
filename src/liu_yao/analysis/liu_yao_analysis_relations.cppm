// C++23 Module - 六爻地支关系分析接口
export module ZhouYi.LiuYaoAnalysis.Relations;

import ZhouYi.BaZiBase;
import ZhouYi.LiuYao;
import ZhouYi.LiuYaoAnalysis.Contract;
import std;

export namespace ZhouYi::LiuYaoAnalysis::Detail {

/**
 * @brief 检测月日、原卦诸爻及动变之间的地支结构。
 *
 * 六合、六冲、刑、害、三合和半合的地支关系全部委托
 * ZhouYi.GanZhi 判定；本函数只补充六爻参与层次和专业断语。
 */
std::vector<GuaYaoBasis> analyze_branch_relations(
    const std::vector<ZhouYi::LiuYao::YaoDetails> &yao_list,
    const ZhouYi::BaZiBase::BaZi &bazi, std::optional<int> yong_shen_position);

} // namespace ZhouYi::LiuYaoAnalysis::Detail
