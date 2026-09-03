// C++23 - 盲派墓库与旬空规则实现
module ZhouYi.BaZiAnalysis.MangPai.MuKu;

import ZhouYi.BaZiBase;
import ZhouYi.GanZhi;
import std;

namespace ZhouYi::BaZiAnalysis::MangPai::MuKu {
namespace {
using namespace ZhouYi::GanZhi;
namespace Mapper = ZhouYi::GanZhi::Mapper;

// 盲派墓库按五行归库判断，不直接套十二长生的阴干“墓”位。
bool is_blind_tomb(TianGan stem, DiZhi branch) {
  switch (get_wu_xing(stem)) {
  case WuXing::Mu:
    return branch == DiZhi::Wei;
  case WuXing::Huo:
    return branch == DiZhi::Xu;
  case WuXing::Jin:
    return branch == DiZhi::Chou;
  case WuXing::Shui:
    return branch == DiZhi::Chen;
  case WuXing::Tu:
    return branch == DiZhi::Chen || branch == DiZhi::Xu ||
           branch == DiZhi::Chou || branch == DiZhi::Wei;
  }
  return false;
}
} // namespace

void build(AnalysisResult &result, const BaZi &chart) {
  result.kong_wang.branches = get_kong_wang(chart.day.gan, chart.day.zhi);
  const auto zz = ZhouYi::BaZiBase::get_branches(chart);
  for (int i = 0; i < 4; ++i) {
    if (is_kong_wang(zz[static_cast<std::size_t>(i)], chart.day.gan,
                     chart.day.zhi)) {
      result.kong_wang.affected_positions.push_back(i);
      result.blind_analysis->warnings.push_back(
          ZhouYi::BaZiBase::pillar_position_name(i) +
          "落旬空，需复核填实或冲空");
    }
  }
  const auto ss = ZhouYi::BaZiBase::get_stems(chart);
  const auto tomb_relation = [&](DiZhi tomb) {
    for (const auto branch : zz) {
      // 墓支自身不作为开库关系来源，避免自刑被误判为开库。
      if (branch == tomb)
        continue;
      if (is_chong(tomb, branch))
        return std::pair<std::string, std::string>{"冲", "开库"};
      if (is_xing(tomb, branch))
        return std::pair<std::string, std::string>{"刑", "开库"};
      if (is_he(tomb, branch))
        return std::pair<std::string, std::string>{"合", "待复核"};
      if (is_hai(tomb, branch))
        return std::pair<std::string, std::string>{"穿", "破库"};
    }
    return std::pair<std::string, std::string>{"无", "闭库"};
  };
  for (int branch_index = 0; branch_index < 4; ++branch_index)
    for (int stem_index = 0; stem_index < 4; ++stem_index)
      if (is_blind_tomb(ss[static_cast<std::size_t>(stem_index)],
                        zz[static_cast<std::size_t>(branch_index)])) {
        const auto [relation, state] =
            tomb_relation(zz[static_cast<std::size_t>(branch_index)]);
        result.blind_analysis->tombs.push_back(
            {std::string(
                 Mapper::to_zh(zz[static_cast<std::size_t>(branch_index)])),
             std::string(
                 Mapper::to_zh(ss[static_cast<std::size_t>(stem_index)])),
             branch_index, stem_index, relation, state, state == "开库",
             relation == "无" ? "原局未见冲刑合穿，暂按闭库处理"
                              : "原局地支与墓库发生" + relation +
                                    "，按关系类型判定墓库状态"});
        result.blind_analysis->structures.push_back(
            ZhouYi::BaZiBase::pillar_position_name(branch_index) + "为" +
            ZhouYi::BaZiBase::pillar_position_name(stem_index) +
            "天干之墓，需结合冲刑判断开闭");
      }
}
} // namespace ZhouYi::BaZiAnalysis::MangPai::MuKu
