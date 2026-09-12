// 六爻地支关系分析实现
module ZhouYi.LiuYaoAnalysis.Relations;

import ZhouYi.GanZhi;
import std;

namespace ZhouYi::LiuYaoAnalysis::Detail {
namespace {

using ZhouYi::GanZhi::DiZhi;
using ZhouYi::LiuYao::YaoDetails;

struct BranchEntity {
  DiZhi branch;                ///< 参与地支。
  std::string label;           ///< 月建、日辰、本卦某爻或变爻。
  std::optional<int> position; ///< 所属爻位；月日为空。
  bool active{};               ///< 月日、动爻和变爻为主动层。
  bool changed{};              ///< 是否变爻。
};

std::string branch_name(DiZhi branch) {
  return std::string(ZhouYi::GanZhi::Mapper::to_zh(branch));
}

std::vector<int> positions_of(const BranchEntity &left,
                              const BranchEntity &right) {
  std::vector<int> positions;
  if (left.position)
    positions.push_back(*left.position);
  if (right.position && right.position != left.position)
    positions.push_back(*right.position);
  return positions;
}

void add_pair(std::vector<GuaYaoBasis> &result, std::string rule,
              const BranchEntity &left, const BranchEntity &right,
              EffectNature nature, std::string suffix = {}) {
  std::string detail = left.label + branch_name(left.branch) + "与" +
                       right.label + branch_name(right.branch) + rule;
  if (!suffix.empty())
    detail += "；" + suffix;
  result.push_back(GuaYaoBasis{.rule = std::move(rule),
                               .detail = std::move(detail),
                               .nature = nature,
                               .positions = positions_of(left, right)});
}

std::string half_kind_name(ZhouYi::GanZhi::SanHeHalfKind kind) {
  switch (kind) {
  case ZhouYi::GanZhi::SanHeHalfKind::ShengWang:
    return "生旺半合";
  case ZhouYi::GanZhi::SanHeHalfKind::WangMu:
    return "旺墓半合";
  case ZhouYi::GanZhi::SanHeHalfKind::ShengMu:
    return "生墓拱合";
  }
  return "三合半局";
}

std::string entity_key(const BranchEntity &entity) {
  return entity.label + ":" + std::to_string(static_cast<int>(entity.branch));
}

} // namespace

std::vector<GuaYaoBasis>
analyze_branch_relations(const std::vector<YaoDetails> &yao_list,
                         const ZhouYi::BaZiBase::BaZi &bazi,
                         std::optional<int> yong_shen_position) {
  std::vector<GuaYaoBasis> result;
  std::vector<BranchEntity> entities;
  entities.reserve(14);
  entities.push_back(
      {.branch = bazi.month.zhi, .label = "月建", .active = true});
  entities.push_back({.branch = bazi.day.zhi, .label = "日辰", .active = true});

  for (const auto &line : yao_list) {
    const auto position = line.position;
    std::string role;
    if (line.shiYingMark == "世")
      role = "世爻";
    else if (line.shiYingMark == "应")
      role = "应爻";
    else if (yong_shen_position == position)
      role = "用神爻";
    else
      role = "第" + std::to_string(position) + "爻";
    entities.push_back({.branch = line.mainPillar.zhi,
                        .label = std::move(role),
                        .position = position,
                        .active = line.isChanging});
    if (line.isChanging) {
      entities.push_back({.branch = line.changedPillar.zhi,
                          .label = "第" + std::to_string(position) + "爻之变爻",
                          .position = position,
                          .active = true,
                          .changed = true});
    }
  }

  std::set<std::string> pair_seen;
  for (std::size_t left_index = 0; left_index < entities.size(); ++left_index) {
    for (std::size_t right_index = left_index + 1;
         right_index < entities.size(); ++right_index) {
      const auto &left = entities[left_index];
      const auto &right = entities[right_index];
      if (left.position && left.position == right.position &&
          left.changed == right.changed)
        continue;

      const bool both_static_lines =
          left.position && right.position && !left.active && !right.active;
      const std::string suffix =
          both_static_lines ? "两静爻只记结构，逢日月或发动引起时方论实效" : "";
      const auto key = entity_key(left) + "|" + entity_key(right);
      if (!pair_seen.insert(key).second)
        continue;

      if (ZhouYi::GanZhi::is_he(left.branch, right.branch))
        add_pair(result, "六合", left, right, EffectNature::YinDong, suffix);
      if (ZhouYi::GanZhi::is_chong(left.branch, right.branch))
        add_pair(result, "六冲", left, right, EffectNature::KeZhi, suffix);
      if (ZhouYi::GanZhi::is_xing(left.branch, right.branch) ||
          ZhouYi::GanZhi::is_xing(right.branch, left.branch))
        add_pair(result, left.branch == right.branch ? "自刑" : "相刑", left,
                 right, EffectNature::KeZhi, suffix);
      if (ZhouYi::GanZhi::is_hai(left.branch, right.branch))
        add_pair(result, "相害", left, right, EffectNature::KeZhi, suffix);
    }
  }

  const auto has_branch = [&](DiZhi branch) {
    return std::ranges::any_of(
        entities, [&](const auto &item) { return item.branch == branch; });
  };
  std::set<std::array<int, 2>> half_seen;
  for (std::size_t left_index = 0; left_index < entities.size(); ++left_index) {
    for (std::size_t right_index = left_index + 1;
         right_index < entities.size(); ++right_index) {
      const auto &left = entities[left_index];
      const auto &right = entities[right_index];
      if (!left.active && !right.active)
        continue;
      const auto half =
          ZhouYi::GanZhi::get_san_he_half(left.branch, right.branch);
      if (!half || has_branch(half->missing_branch))
        continue;
      std::array key = {static_cast<int>(left.branch),
                        static_cast<int>(right.branch)};
      std::ranges::sort(key);
      if (!half_seen.insert(key).second)
        continue;
      const auto relation = half_kind_name(half->kind);
      add_pair(result, relation, left, right, EffectNature::YinDong,
               "同属" + branch_name(left.branch) + branch_name(right.branch) +
                   branch_name(half->missing_branch) + "三合体系，尚缺" +
                   branch_name(half->missing_branch) + "，不作完整三合局");
    }
  }

  std::set<std::array<int, 3>> triple_seen;
  for (std::size_t first = 0; first < entities.size(); ++first) {
    for (std::size_t second = first + 1; second < entities.size(); ++second) {
      for (std::size_t third = second + 1; third < entities.size(); ++third) {
        const auto &a = entities[first];
        const auto &b = entities[second];
        const auto &c = entities[third];
        if (!a.active && !b.active && !c.active)
          continue;
        if ((a.position && a.position == b.position) ||
            (a.position && a.position == c.position) ||
            (b.position && b.position == c.position))
          continue;
        const auto [matched, element] =
            ZhouYi::GanZhi::is_san_he(a.branch, b.branch, c.branch);
        if (!matched)
          continue;
        std::array key = {static_cast<int>(a.branch),
                          static_cast<int>(b.branch),
                          static_cast<int>(c.branch)};
        std::ranges::sort(key);
        if (!triple_seen.insert(key).second)
          continue;
        std::vector<int> positions;
        for (const auto *entity : {&a, &b, &c})
          if (entity->position &&
              !std::ranges::contains(positions, *entity->position))
            positions.push_back(*entity->position);
        result.push_back(GuaYaoBasis{
            .rule = "三合局",
            .detail = a.label + branch_name(a.branch) + "、" + b.label +
                      branch_name(b.branch) + "、" + c.label +
                      branch_name(c.branch) + "三合" +
                      std::string(ZhouYi::GanZhi::Mapper::to_zh(element)) +
                      "局；成局强弱仍须审月令、动静与冲破",
            .nature = EffectNature::YinDong,
            .positions = std::move(positions)});
      }
    }
  }
  return result;
}

} // namespace ZhouYi::LiuYaoAnalysis::Detail
