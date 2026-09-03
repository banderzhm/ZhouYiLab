// C++23 - 盲派做功规则实现
module ZhouYi.BaZiAnalysis.MangPai.ZuoGong;

import ZhouYi.BaZiAnalysis.Relations;
import ZhouYi.BaZiAnalysis.MangPai.Common;
import ZhouYi.BaZiBase;
import ZhouYi.ZhMapper;
import ZhouYi.GanZhi;
import std;

namespace ZhouYi::BaZiAnalysis::MangPai::ZuoGong {
namespace {
namespace Mapper = ZhouYi::GanZhi::Mapper;

double position_power(const AnalysisResult &result, int position) {
  double total = 0.0;
  for (const auto &occurrence : result.ten_god_occurrences)
    if (occurrence.pillar_position == position)
      total += occurrence.effective_power;
  return total;
}
} // namespace

void build(AnalysisResult &result, const BaZi &chart) {
  result.relations = Detail::detect_branch_relations(chart);
  result.stem_relations = Detail::detect_stem_relations(chart);
  for (const auto &relation : result.relations) {
    BlindWorkSummary work;
    work.source =
        ZhouYi::BaZiBase::pillar_position_name(relation.first_position) +
        std::string(Mapper::to_zh(relation.first));
    work.target =
        ZhouYi::BaZiBase::pillar_position_name(relation.second_position) +
        std::string(Mapper::to_zh(relation.second));
    work.direction = relation.symmetric ? "双向" : relation.direction;
    work.relation = std::string(ZhouYi::Mapper::to_zh(relation.type));
    work.source_position = relation.first_position;
    work.target_position = relation.second_position;
    const auto stems = ZhouYi::BaZiBase::get_stems(chart);
    work.source_ten_god = std::string(shi_shen_to_zh(get_shi_shen(
        chart.day.gan,
        stems[static_cast<std::size_t>(relation.first_position)])));
    work.target_ten_god = std::string(shi_shen_to_zh(get_shi_shen(
        chart.day.gan,
        stems[static_cast<std::size_t>(relation.second_position)])));
    work.effective_power =
        std::min(position_power(result, relation.first_position),
                 position_power(result, relation.second_position));
    work.effective = relation.effective;
    work.result =
        relation.effective
            ? (relation.first_position == 2 || relation.second_position == 2
                   ? "主位被引动，需结合十神取象"
                   : "宾位之间发生结构作用，需结合主位复核")
            : "关系存在但有效性不足，需结合根气、空亡和岁运复核";
    work.evidence = relation.evidence;
    result.blind_analysis->work_chains.push_back(work);
    if (relation.effective) {
      result.blind_analysis->useful_gods.push_back(work.source);
      result.blind_analysis->useful_gods.push_back(work.target);
    }
    result.blind_analysis->structures.push_back(
        relation.type == BranchRelationKind::Combine
            ? "合用/合绊：主宾关系被牵动"
        : relation.type == BranchRelationKind::Clash
            ? "冲动：对应宫位和关系发生变化"
        : relation.type == BranchRelationKind::Harm
            ? "穿害：暗中损耗或关系不畅"
            : "刑、合局或会局：结构力量被引动");
    if (relation.effective &&
        (relation.first_position == 2 || relation.second_position == 2))
      result.blind_analysis->symbols.push_back("日支被" + work.relation +
                                               "，身体/夫妻宫被引动");
  }
  const auto stems = ZhouYi::BaZiBase::get_stems(chart);
  for (const auto &relation : result.stem_relations) {
    result.blind_analysis->structures.push_back(
        "天干五合：" + std::string(Mapper::to_zh(relation.first)) +
        std::string(Mapper::to_zh(relation.second)) +
        (relation.effective ? "合化" : "合而不化"));
    // 盲派将日主与宾位官星的五合视为直接做功，即使不化也不能仅作
    // “关系存在”处理：这是“官来就我”的主线，须单独进入做功链。
    const int subject_position =
        relation.first_position == 2    ? relation.first_position
        : relation.second_position == 2 ? relation.second_position
                                        : -1;
    const int object_position = subject_position == relation.first_position
                                    ? relation.second_position
                                    : relation.first_position;
    if (subject_position == 2 && object_position != 2) {
      const auto subject_god = get_shi_shen(
          chart.day.gan, stems[static_cast<std::size_t>(subject_position)]);
      const auto object_god = get_shi_shen(
          chart.day.gan, stems[static_cast<std::size_t>(object_position)]);
      BlindWorkSummary work;
      work.source = ZhouYi::BaZiBase::pillar_position_name(subject_position) +
                    std::string(Mapper::to_zh(stems[subject_position]));
      work.target = ZhouYi::BaZiBase::pillar_position_name(object_position) +
                    std::string(Mapper::to_zh(stems[object_position]));
      work.source_position = subject_position;
      work.target_position = object_position;
      work.source_ten_god = "日主";
      work.target_ten_god = std::string(shi_shen_to_zh(object_god));
      work.direction = "主→宾";
      work.relation = "合";
      work.effective_power = position_power(result, object_position);
      work.effective = true;
      work.result =
          "日主合官，官来就我；职业取象偏向依附平台、规则或组织取得结果";
      work.evidence = relation.evidence;
      result.blind_analysis->work_chains.push_back(std::move(work));
      result.blind_analysis->useful_gods.push_back(
          "日柱" + std::string(Mapper::to_zh(stems[subject_position])));
      result.blind_analysis->useful_gods.push_back(
          ZhouYi::BaZiBase::pillar_position_name(object_position) +
          std::string(Mapper::to_zh(stems[object_position])));
      result.blind_analysis->structures.push_back(
          "日主合官做功：官来就我，需结合官根、空亡和岁运引动判断兑现程度");
      result.blind_analysis->symbols.push_back(
          "日主合宾位官星，形成依附平台/规则取事的职业象");
    }
  }
}
} // namespace ZhouYi::BaZiAnalysis::MangPai::ZuoGong
