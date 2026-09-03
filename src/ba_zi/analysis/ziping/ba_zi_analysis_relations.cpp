// C++23 Module - 八字原局关系识别实现
module ZhouYi.BaZiAnalysis.Relations;

import ZhouYi.BaZiBase;
import ZhouYi.GanZhi;
import std;

namespace ZhouYi::BaZiAnalysis {

namespace {

using ZhouYi::GanZhi::get_cang_gan;
using ZhouYi::GanZhi::get_wu_xing;
using ZhouYi::GanZhi::is_chong;
using ZhouYi::GanZhi::is_hai;
using ZhouYi::GanZhi::is_xing;
using ZhouYi::GanZhi::wu_xing_ke;
namespace Mapper = ZhouYi::GanZhi::Mapper;

std::string element_name(WuXing element) {
  return std::string(Mapper::to_zh(element));
}

std::string stem_name(TianGan stem) { return std::string(Mapper::to_zh(stem)); }

std::string branch_name(DiZhi branch) {
  return std::string(Mapper::to_zh(branch));
}

bool branch_is_clashed(DiZhi branch,
                       const std::vector<BranchRelation> &relations) {
  return std::any_of(
      relations.begin(), relations.end(), [branch](const auto &relation) {
        return relation.type == BranchRelationKind::Clash &&
               (relation.first == branch || relation.second == branch);
      });
}

} // namespace

std::vector<BranchRelation> Detail::detect_branch_relations(const BaZi &chart) {
  const auto branches = ZhouYi::BaZiBase::get_branches(chart);
  std::vector<BranchRelation> result;
  for (int i = 0; i < 4; ++i) {
    for (int j = i + 1; j < 4; ++j) {
      const auto add = [&](BranchRelationKind type, bool symmetric) {
        const auto subject =
            branch_name(branches[i]) + branch_name(branches[j]);
        result.push_back(
            {type,
             branches[i],
             branches[j],
             i,
             j,
             symmetric,
             "仅记录结构关系；未直接改变五行力量",
             {Evidence{"relation.detect", subject, result.empty() ? "" : "关系",
                       0.0, "需结合月令、根气和成化条件再判断实际影响"}}});
        result.back().members = {branches[i], branches[j]};
        result.back().effective = false;
        result.back().direction = symmetric ? "双向"
                                            : branch_name(branches[i]) + "→" +
                                                  branch_name(branches[j]);
      };
      if (is_chong(branches[i], branches[j]))
        add(BranchRelationKind::Clash, true);
      if (ZhouYi::GanZhi::get_he_wu_xing(branches[i], branches[j]))
        add(BranchRelationKind::Combine, true);
      if (is_hai(branches[i], branches[j]))
        add(BranchRelationKind::Harm, true);
      if (is_xing(branches[i], branches[j])) {
        add(branches[i] == branches[j] ? BranchRelationKind::SelfPunishment
                                       : BranchRelationKind::Punishment,
            is_xing(branches[j], branches[i]));
        if (branches[i] == branches[j])
          result.back().direction = "自刑";
      }
    }
  }
  const auto position_of = [&](DiZhi branch) {
    return static_cast<int>(
        std::find(branches.begin(), branches.end(), branch) - branches.begin());
  };
  const auto has_branch = [&](DiZhi branch) {
    return std::find(branches.begin(), branches.end(), branch) !=
           branches.end();
  };
  const auto no_member_clash = [&](const std::vector<DiZhi> &members) {
    return std::none_of(members.begin(), members.end(), [&](DiZhi branch) {
      return branch_is_clashed(branch, result);
    });
  };
  const auto add_group = [&](BranchRelationKind type,
                             const std::vector<DiZhi> &members, WuXing element,
                             bool complete, RelationStrength strength) {
    const auto stems = ZhouYi::BaZiBase::get_stems(chart);
    const bool transform_stem_exposed =
        std::any_of(stems.begin(), stems.end(),
                    [&](TianGan stem) { return get_wu_xing(stem) == element; });
    const bool effective = complete &&
                           get_wu_xing(chart.month.zhi) == element &&
                           transform_stem_exposed && no_member_clash(members);
    std::string subject;
    for (const auto branch : members)
      subject += branch_name(branch);
    const std::string impact =
        effective ? subject + "满足月令同气且未见成员受冲，按" +
                        element_name(element) + "局候选计入后续人工复核"
                  : subject + "为" + element_name(element) +
                        "局结构，尚未满足成局条件，不直接改变五行力量";
    result.push_back(
        {type,
         members.front(),
         members.size() > 1 ? members[1] : members.front(),
         position_of(members.front()),
         members.size() > 1 ? position_of(members[1])
                            : position_of(members.front()),
         true,
         impact,
         {Evidence{"relation.group", subject, complete ? "全局" : "半局",
                   effective ? 1.0 : 0.0,
                   effective
                       ? "月令同气、化神透干且成员未受冲"
                       : "需月令同气、化神透干且成员不受冲，才可认定有效"}},
         members,
         effective,
         "双向",
         strength});
  };
  for (int first = 0; first < 10; ++first) {
    for (int second = first + 1; second < 11; ++second) {
      for (int third = second + 1; third < 12; ++third) {
        const std::array group = {static_cast<DiZhi>(first),
                                  static_cast<DiZhi>(second),
                                  static_cast<DiZhi>(third)};
        const auto [is_harmony, harmony_element] =
            ZhouYi::GanZhi::is_san_he(group[0], group[1], group[2]);
        if (is_harmony) {
          std::vector<DiZhi> present;
          for (const auto branch : group)
            if (has_branch(branch))
              present.push_back(branch);
          const auto middle =
              *std::find_if(group.begin(), group.end(), [&](DiZhi branch) {
                return get_wu_xing(branch) == harmony_element;
              });
          if (present.size() == 3) {
            add_group(BranchRelationKind::ThreeHarmony, present,
                      harmony_element, true, RelationStrength::VeryStrong);
          } else if (present.size() == 2 && has_branch(middle)) {
            add_group(BranchRelationKind::HalfHarmony, present, harmony_element,
                      false, RelationStrength::Medium);
          } else if (present.size() == 2 && !has_branch(middle)) {
            const int first_position = position_of(present[0]);
            const int second_position = position_of(present[1]);
            const bool adjacent =
                std::abs(first_position - second_position) == 1;
            const std::string subject =
                branch_name(present[0]) + branch_name(present[1]);
            BranchRelation arching{
                BranchRelationKind::Arching,
                present[0],
                present[1],
                first_position,
                second_position,
                true,
                subject + "缺" + branch_name(middle) + "，只记拱" +
                    branch_name(middle) + "之象，虚支不当作实支满额计根",
                {{"relation.arching", subject, "拱" + branch_name(middle),
                  adjacent ? 0.25 : 0.12,
                  adjacent ? "生支与墓支紧贴，虚拱中神"
                           : "生支与墓支隔位，拱力更弱"}},
                present,
                false,
                "双向",
                adjacent ? RelationStrength::Arching
                         : RelationStrength::DistantArching};
            arching.virtual_branch = middle;
            result.push_back(std::move(arching));
          }
        }

        const auto [is_meeting, meeting_element] =
            ZhouYi::GanZhi::is_san_hui(group[0], group[1], group[2]);
        if (is_meeting && std::all_of(group.begin(), group.end(), has_branch)) {
          add_group(BranchRelationKind::ThreeMeeting,
                    {group.begin(), group.end()}, meeting_element, true,
                    RelationStrength::VeryStrong);
        }
      }
    }
  }
  const auto add_xing_group = [&](BranchRelationKind type,
                                  const std::vector<DiZhi> &members,
                                  std::string note) {
    std::string subject;
    for (const auto branch : members)
      subject += branch_name(branch);
    result.push_back({type,
                      members.front(),
                      members.back(),
                      position_of(members.front()),
                      position_of(members.back()),
                      false,
                      "刑关系仅记录结构，需结合宫位与喜忌解释",
                      {Evidence{"relation.xing", subject, "刑", 0.0, note}},
                      members,
                      false,
                      std::move(note)});
  };
  if (has_branch(DiZhi::Yin) && has_branch(DiZhi::Si) &&
      has_branch(DiZhi::Shen)) {
    add_xing_group(BranchRelationKind::ThreePunishment,
                   {DiZhi::Yin, DiZhi::Si, DiZhi::Shen},
                   "寅→巳→申→寅（无恩之刑）");
  }
  if (has_branch(DiZhi::Chou) && has_branch(DiZhi::Xu) &&
      has_branch(DiZhi::Wei)) {
    add_xing_group(BranchRelationKind::ThreePunishment,
                   {DiZhi::Chou, DiZhi::Xu, DiZhi::Wei},
                   "丑→戌→未→丑（恃势之刑）");
  }
  return result;
}

std::vector<StemRelation> Detail::detect_stem_relations(const BaZi &chart) {
  const auto stems = ZhouYi::BaZiBase::get_stems(chart);
  const auto branches = ZhouYi::BaZiBase::get_branches(chart);
  std::vector<StemRelation> result;
  for (int i = 0; i < 4; ++i) {
    for (int j = i + 1; j < 4; ++j) {
      const auto element =
          ZhouYi::GanZhi::stem_combine_element(stems[i], stems[j]);
      if (!element)
        continue;
      const bool month_ok = get_wu_xing(chart.month.zhi) == *element;
      const bool root_ok =
          std::any_of(branches.begin(), branches.end(), [&](DiZhi branch) {
            const auto hidden = get_cang_gan(branch);
            return std::any_of(hidden.begin(), hidden.end(),
                               [&](TianGan hidden_stem) {
                                 return get_wu_xing(hidden_stem) == *element;
                               });
          });
      const bool no_competing_combine =
          std::count(stems.begin(), stems.end(), stems[i]) == 1 &&
          std::count(stems.begin(), stems.end(), stems[j]) == 1;
      const bool no_ke_po =
          !std::any_of(stems.begin(), stems.end(), [&](TianGan stem) {
            return stem != stems[i] && stem != stems[j] &&
                   wu_xing_ke(get_wu_xing(stem), *element);
          });
      const bool effective =
          month_ok && root_ok && no_competing_combine && no_ke_po;
      std::vector<std::string> missing;
      if (!month_ok)
        missing.emplace_back("月令未引化");
      if (!root_ok)
        missing.emplace_back("化神无根");
      if (!no_competing_combine)
        missing.emplace_back("存在争合");
      if (!no_ke_po)
        missing.emplace_back("化神受克破");
      result.push_back(
          {stems[i],
           stems[j],
           i,
           j,
           StemRelationKind::FiveCombine,
           effective ? "合化" + element_name(*element) : "仅合不化",
           effective,
           {Evidence{"relation.stem_combine",
                     stem_name(stems[i]) + stem_name(stems[j]), "五合",
                     effective ? 1.0 : 0.0,
                     effective
                         ? "月令引化、化神有根、无争合且无克破"
                         : "仅合不化：" +
                               std::accumulate(std::next(missing.begin()),
                                               missing.end(), missing.front(),
                                               [](std::string left,
                                                  const std::string &right) {
                                                 return std::move(left) + "、" +
                                                        right;
                                               })}}});
    }
  }
  return result;
}

} // namespace ZhouYi::BaZiAnalysis
