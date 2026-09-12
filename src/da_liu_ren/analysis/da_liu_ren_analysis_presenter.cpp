// 大六壬中文分析报告实现
module ZhouYi.DaLiuRenAnalysis.Presenter;

import ZhouYi.GanZhi;
import ZhouYi.ZhMapper;
import std;

namespace ZhouYi::DaLiuRenAnalysis {
namespace {

std::string_view zh(auto value) { return ZhouYi::Mapper::to_zh(value); }
std::string zhi(DiZhi value) {
  return std::string(ZhouYi::GanZhi::Mapper::to_zh(value));
}

nlohmann::json evidence_json(const KeChuanBasis &item) {
  return {
      {"规则", item.rule}, {"断法", item.detail}, {"作用", zh(item.nature)}};
}

void write_evidence(std::ostream &output,
                    const std::vector<KeChuanBasis> &items) {
  for (const auto &item : items)
    output << "  - " << item.rule << "【" << zh(item.nature) << "】："
           << item.detail << '\n';
}

std::string joined_patterns(const std::vector<std::string> &patterns) {
  std::string result;
  for (std::size_t index = 0; index < patterns.size(); ++index) {
    if (index != 0)
      result += "、";
    result += patterns[index];
  }
  return result.empty() ? "未见已登记课式" : result;
}

std::string
joined_relation_names(const std::vector<BranchRelationAnalysis> &relations) {
  std::string result;
  for (std::size_t index = 0; index < relations.size(); ++index) {
    if (index)
      result += "、";
    result += zh(relations[index].kind);
  }
  return result.empty() ? "无显著作用" : result;
}

std::vector<const BranchRelationAnalysis *>
relations_between(const std::vector<BranchRelationAnalysis> &relations,
                  std::string_view left_source, std::string_view right_source) {
  std::vector<const BranchRelationAnalysis *> result;
  for (const auto &relation : relations) {
    if (relation.sources.size() == 2 && relation.sources[0] == left_source &&
        relation.sources[1] == right_source)
      result.push_back(&relation);
  }
  return result;
}

bool is_one_of(BranchRelationKind kind,
               std::initializer_list<BranchRelationKind> expected) {
  return std::ranges::find(expected, kind) != expected.end();
}

std::string
relation_cell(const std::vector<const BranchRelationAnalysis *> &relations,
              std::initializer_list<BranchRelationKind> kinds,
              bool show_detail = false) {
  std::string result;
  for (const auto *relation : relations) {
    if (!is_one_of(relation->kind, kinds))
      continue;
    if (!result.empty())
      result += show_detail ? "<br>" : "、";
    result += show_detail ? relation->detail : std::string(zh(relation->kind));
    if (show_detail && relation->missing_branch)
      result += "（缺" + zhi(*relation->missing_branch) + "）";
  }
  return result.empty() ? "—" : result;
}

bool is_landing_relation(const BranchRelationAnalysis &relation) {
  if (relation.sources.size() != 2)
    return false;
  return relation.sources[1] == "所临地盘" ||
         relation.sources[1].ends_with("下神所居地盘");
}

bool is_group_relation(const BranchRelationAnalysis &relation) {
  return is_one_of(relation.kind,
                   {BranchRelationKind::BanHe, BranchRelationKind::SanHe,
                    BranchRelationKind::SanHui});
}

template <typename Value>
std::string joined_zh_values(const std::vector<Value> &values) {
  std::string result;
  for (std::size_t index = 0; index < values.size(); ++index) {
    if (index)
      result += "、";
    result += zh(values[index]);
  }
  return result.empty() ? "未见" : result;
}

std::string calendar_positions(const MissingBranchContext &context) {
  std::vector<std::string> positions;
  if (context.is_month_command)
    positions.emplace_back("月建");
  if (context.is_day_branch)
    positions.emplace_back("日辰");
  if (context.is_hour_branch)
    positions.emplace_back("占时");
  if (context.is_month_general)
    positions.emplace_back("月将");
  std::string result;
  for (std::size_t index = 0; index < positions.size(); ++index) {
    if (index)
      result += "、";
    result += positions[index];
  }
  return result.empty() ? "未见" : result;
}

nlohmann::json missing_context_json(const MissingBranchContext &context) {
  return {{"所缺支", zhi(context.branch)},
          {"天盘所临地盘", zhi(context.heaven_lands_on_earth)},
          {"地盘本宫上神", zhi(context.heaven_over_earth)},
          {"四课上神", joined_zh_values(context.lesson_upper_positions)},
          {"四课下神", joined_zh_values(context.lesson_lower_positions)},
          {"三传", joined_zh_values(context.transmission_positions)},
          {"月建日辰占时月将", calendar_positions(context)},
          {"旬空", context.is_void},
          {"判断", context.assessment}};
}

} // namespace

nlohmann::json to_zh_json(const ZhouYi::DaLiuRen::DaLiuRenResult &pan,
                          const AnalysisResult &analysis) {
  nlohmann::json output;
  output["契约版本"] = analysis.schema_version;
  output["占问"] = {
      {"门类", zh(analysis.request.question_kind)},
      {"原文", analysis.request.question},
      {"本命",
       analysis.request.ben_ming ? zhi(*analysis.request.ben_ming) : "未提供"},
      {"行年", analysis.request.xing_nian ? zhi(*analysis.request.xing_nian)
                                          : "未提供"}};
  output["排盘"] = pan.to_json();
  output["九宗门"] = zh(analysis.method);
  output["课式"] = analysis.lesson_patterns;
  output["四课分析"] = nlohmann::json::array();
  for (const auto &lesson : analysis.lessons) {
    nlohmann::json ke_chuan_basis = nlohmann::json::array();
    for (const auto &item : lesson.ke_chuan_basis)
      ke_chuan_basis.push_back(evidence_json(item));
    output["四课分析"].push_back(
        {{"课位", zh(lesson.position)},
         {"所属", zh(lesson.door)},
         {"下神", lesson.lower},
         {"地盘宫", zhi(lesson.lower_palace)},
         {"上神", zhi(lesson.upper)},
         {"六亲", ZhouYi::GanZhi::liu_qin_to_zh(lesson.relative)},
         {"天将", zh(lesson.general)},
         {"上下", zh(lesson.relation)},
         {"发用", lesson.sends_initial},
         {"依据", ke_chuan_basis}});
  }
  output["三传分析"] = nlohmann::json::array();
  for (const auto &transmission : analysis.transmissions) {
    output["三传分析"].push_back(
        {{"阶段", zh(transmission.stage)},
         {"传神", zhi(transmission.branch)},
         {"所临地盘", zhi(transmission.earth_position)},
         {"六亲", ZhouYi::GanZhi::liu_qin_to_zh(transmission.relative)},
         {"天将", zh(transmission.general)},
         {"旺衰", transmission.seasonal_state},
         {"旬空", transmission.is_void},
         {"神煞", transmission.shen_sha}});
  }
  output["本命行年临盘"] = nlohmann::json::array();
  for (const auto &marker : analysis.personal_markers) {
    nlohmann::json relations = nlohmann::json::array();
    for (const auto &relation : marker.transmission_relations) {
      relations.push_back(
          {{"关系", zh(relation.kind)},
           {"来源", relation.sources},
           {"断法", relation.detail},
           {"所缺支",
            relation.missing_branch ? zhi(*relation.missing_branch) : "无"},
           {"作用", zh(relation.nature)}});
    }
    output["本命行年临盘"].push_back(
        {{"项目", zh(marker.kind)},
         {"本支", zhi(marker.branch)},
         {"地盘本宫上神", zhi(marker.upper)},
         {"天将", zh(marker.general)},
         {"六亲", ZhouYi::GanZhi::liu_qin_to_zh(marker.relative)},
         {"本支旬空", marker.branch_is_void},
         {"上神旬空", marker.upper_is_void},
         {"与三传作用", std::move(relations)}});
  }
  output["天地盘逐宫"] = nlohmann::json::array();
  for (const auto &palace : analysis.palaces) {
    nlohmann::json relations = nlohmann::json::array();
    for (const auto &relation : palace.relations)
      relations.push_back({{"关系", zh(relation.kind)},
                           {"断法", relation.detail},
                           {"作用", zh(relation.nature)}});
    output["天地盘逐宫"].push_back(
        {{"地盘", zhi(palace.earth)},
         {"天盘", zhi(palace.heaven)},
         {"天将", zh(palace.general)},
         {"六亲", ZhouYi::GanZhi::liu_qin_to_zh(palace.relative)},
         {"上下作用", std::move(relations)}});
  }
  output["课传关系"] = nlohmann::json::array();
  for (const auto &relation : analysis.transmission_relations) {
    nlohmann::json branches = nlohmann::json::array();
    for (const auto branch : relation.branches)
      branches.push_back(zhi(branch));
    const auto missing_context =
        relation.missing_context
            ? missing_context_json(*relation.missing_context)
            : nlohmann::json(nullptr);
    output["课传关系"].push_back(
        {{"关系", zh(relation.kind)},
         {"传神", std::move(branches)},
         {"所缺支",
          relation.missing_branch ? zhi(*relation.missing_branch) : "无"},
         {"所缺支落处", missing_context},
         {"断法", relation.detail},
         {"作用", zh(relation.nature)}});
  }
  output["天地盘四课三传联断"] = nlohmann::json::array();
  output["四课临盘作用"] = nlohmann::json::array();
  output["三传临盘作用"] = nlohmann::json::array();
  output["四课三传合局"] = nlohmann::json::array();
  for (const auto &relation : analysis.cross_layer_relations) {
    const auto missing_context =
        relation.missing_context
            ? missing_context_json(*relation.missing_context)
            : nlohmann::json(nullptr);
    const nlohmann::json item = {{"关系", zh(relation.kind)},
                                 {"来源", relation.sources},
                                 {"断法", relation.detail},
                                 {"所缺支", relation.missing_branch
                                                ? zhi(*relation.missing_branch)
                                                : "无"},
                                 {"所缺支落处", missing_context},
                                 {"作用", zh(relation.nature)}};
    output["天地盘四课三传联断"].push_back(item);
    if (is_group_relation(relation))
      output["四课三传合局"].push_back(item);
    if (is_landing_relation(relation)) {
      if (!relation.sources.empty() &&
          relation.sources.front().find("课") != std::string::npos)
        output["四课临盘作用"].push_back(item);
      else
        output["三传临盘作用"].push_back(item);
    }
  }
  output["应期候选"] = nlohmann::json::array();
  for (const auto &timing : analysis.timing) {
    nlohmann::json ke_chuan_basis = nlohmann::json::array();
    for (const auto &item : timing.ke_chuan_basis)
      ke_chuan_basis.push_back(evidence_json(item));
    output["应期候选"].push_back({{"次序", timing.priority},
                                  {"触发支", zhi(timing.trigger)},
                                  {"法则", zh(timing.rule)},
                                  {"所应阶段", zh(timing.phase)},
                                  {"迟速", zh(timing.pace)},
                                  {"候期层级", timing.time_scope},
                                  {"触发条件", timing.condition},
                                  {"主要应象", timing.manifestation},
                                  {"限制", timing.constraints},
                                  {"排序依据", timing.priority_reason},
                                  {"摘要", timing.description},
                                  {"依据", std::move(ke_chuan_basis)}});
  }
  output["总断"] = {{"课象", zh(analysis.judgment)},
                    {"断语", analysis.conclusion},
                    {"复核", analysis.review_points}};
  return output;
}

void write_zh(std::ostream &output, const ZhouYi::DaLiuRen::DaLiuRenResult &pan,
              const AnalysisResult &analysis) {
  output << "==================== 大六壬排盘与占断 ====================\n\n";
  output << "【占问资料】\n";
  output << "占类：" << zh(analysis.request.question_kind) << '\n';
  if (!analysis.request.question.empty())
    output << "所问：" << analysis.request.question << '\n';
  output << "本命："
         << (analysis.request.ben_ming ? zhi(*analysis.request.ben_ming)
                                       : "未提供")
         << '\n';
  output << "行年："
         << (analysis.request.xing_nian ? zhi(*analysis.request.xing_nian)
                                        : "未提供")
         << '\n';

  output << "\n【起课资料】\n";
  output << "四柱：" << pan.ba_zi.year.to_string() << " / "
         << pan.ba_zi.month.to_string() << " / " << pan.ba_zi.day.to_string()
         << " / " << pan.ba_zi.hour.to_string() << '\n';
  output << "占时：" << zhi(pan.ba_zi.hour.zhi) << '\n';
  output << "月将：" << zhi(pan.yue_jiang) << '\n';
  output << "贵人：" << zhi(pan.gui_ren) << "（"
         << (pan.is_day ? "昼贵" : "夜贵") << "）\n";
  output << "旬空：" << pan.ba_zi.xun_kong_1 << pan.ba_zi.xun_kong_2 << '\n';

  output << "\n【天地盘】\n";
  output << "| 宫位 | 地盘 | 天盘 | 天将 | 六亲 | 天地作用 |\n";
  output << "| --- | --- | --- | --- | --- | --- |\n";
  for (const auto &palace : analysis.palaces)
    output << "| " << zhi(palace.earth) << "宫 | " << zhi(palace.earth) << " | "
           << zhi(palace.heaven) << " | " << zh(palace.general) << " | "
           << ZhouYi::GanZhi::liu_qin_to_zh(palace.relative) << " | "
           << joined_relation_names(palace.relations) << " |\n";

  output << "\n【四课】\n";
  output
      << "| 课位 | 门类 | 下神 | 地盘宫 | 上神 | 六亲 | 天将 | 上下 | 发用 |\n";
  output << "| --- | --- | --- | --- | --- | --- | --- | --- | --- |\n";
  for (const auto &lesson : analysis.lessons) {
    output << "| " << zh(lesson.position) << " | " << zh(lesson.door) << " | "
           << lesson.lower << " | " << zhi(lesson.lower_palace) << " | "
           << zhi(lesson.upper) << " | "
           << ZhouYi::GanZhi::liu_qin_to_zh(lesson.relative) << " | "
           << zh(lesson.general) << " | " << zh(lesson.relation) << " | "
           << (lesson.sends_initial ? "是" : "否") << " |\n";
  }
  output << "四课断法：\n";
  for (const auto &lesson : analysis.lessons) {
    output << "- " << zh(lesson.position) << "（地盘"
           << zhi(lesson.lower_palace) << "宫）：\n";
    write_evidence(output, lesson.ke_chuan_basis);
  }

  output << "\n【三传】\n";
  output << "| 阶段 | 六亲 | 遁干 | 传神 | 所临地盘 | 天将 | 旺衰 | 空亡 | "
            "相关神煞 |\n";
  output << "| --- | --- | --- | --- | --- | --- | --- | --- | --- |\n";
  for (const auto &transmission : analysis.transmissions) {
    output << "| " << zh(transmission.stage) << " | "
           << ZhouYi::GanZhi::liu_qin_to_zh(transmission.relative) << " | "
           << (transmission.hidden_stem
                   ? std::string(ZhouYi::GanZhi::Mapper::to_zh(
                         *transmission.hidden_stem))
                   : "空")
           << " | " << zhi(transmission.branch) << " | "
           << zhi(transmission.earth_position) << " | "
           << zh(transmission.general) << " | " << transmission.seasonal_state
           << " | " << (transmission.is_void ? "旬空" : "不空") << " | ";
    for (std::size_t index = 0; index < transmission.shen_sha.size(); ++index) {
      if (index)
        output << "、";
      output << transmission.shen_sha[index];
    }
    output << " |\n";
  }
  output << "三传断法：\n";
  for (const auto &transmission : analysis.transmissions) {
    output << "- " << zh(transmission.stage) << zhi(transmission.branch)
           << "临地盘" << zhi(transmission.earth_position) << "宫：\n";
    if (transmission.ke_chuan_basis.empty())
      output << "  - 本传不空；递传作用另见课传关系。\n";
    else
      write_evidence(output, transmission.ke_chuan_basis);
  }

  output << "\n【本命行年临盘】\n";
  if (analysis.personal_markers.empty()) {
    output << "未提供本命与行年，不推测变体门。\n";
  } else {
    output << "| 项目 | 本支 | 地盘本宫上神 | 天将 | 六亲 | 本支空亡 | "
              "上神空亡 |\n";
    output << "| --- | --- | --- | --- | --- | --- | --- |\n";
    for (const auto &marker : analysis.personal_markers)
      output << "| " << zh(marker.kind) << " | " << zhi(marker.branch) << " | "
             << zhi(marker.upper) << " | " << zh(marker.general) << " | "
             << ZhouYi::GanZhi::liu_qin_to_zh(marker.relative) << " | "
             << (marker.branch_is_void ? "旬空" : "不空") << " | "
             << (marker.upper_is_void ? "旬空" : "不空") << " |\n";

    output << "\n本命、行年所乘上神与三传作用：\n";
    output << "| 项目 | 上神 | 传位 | 传神 | 五行生克 | 刑冲合害 | 半合 | "
              "临传断法 |\n";
    output << "| --- | --- | --- | --- | --- | --- | --- | --- |\n";
    for (const auto &marker : analysis.personal_markers) {
      const auto marker_source = std::string(zh(marker.kind)) + "上神";
      for (const auto &transmission : analysis.transmissions) {
        const auto transmission_source = std::string(zh(transmission.stage));
        const auto relations = relations_between(
            marker.transmission_relations, marker_source, transmission_source);
        output << "| " << zh(marker.kind) << " | " << zhi(marker.upper) << " | "
               << transmission_source << " | " << zhi(transmission.branch)
               << " | "
               << relation_cell(relations, {BranchRelationKind::Sheng,
                                            BranchRelationKind::Ke,
                                            BranchRelationKind::BiHe})
               << " | "
               << relation_cell(relations, {BranchRelationKind::TongLin,
                                            BranchRelationKind::Xing,
                                            BranchRelationKind::Chong,
                                            BranchRelationKind::LiuHe,
                                            BranchRelationKind::Hai})
               << " | " << relation_cell(relations, {BranchRelationKind::BanHe})
               << " | "
               << relation_cell(
                      relations,
                      {BranchRelationKind::TongLin, BranchRelationKind::Sheng,
                       BranchRelationKind::Ke, BranchRelationKind::BiHe,
                       BranchRelationKind::Xing, BranchRelationKind::Chong,
                       BranchRelationKind::LiuHe, BranchRelationKind::Hai,
                       BranchRelationKind::BanHe},
                      true)
               << " |\n";
      }
    }
  }

  output << "\n【发用与课式】\n";
  output << "九宗门：" << zh(analysis.method) << '\n';
  output << "课式：" << joined_patterns(analysis.lesson_patterns) << '\n';
  output << "说明：当前版本保留取传结果；后续须由三传排盘层直接提供候选课、比用"
            "及涉害深浅的完整轨迹。\n";

  output << "\n【课传生克刑冲合害】\n";
  output << "审察范围：相生、相克、比和、相刑、六冲、六合、六害、半合、三合、三"
            "会；未列者即本课不成立。\n";
  if (analysis.transmission_relations.empty()) {
    output << "三传之间未见已登记的显著关系。\n";
  } else {
    for (const auto &relation : analysis.transmission_relations) {
      output << "- " << zh(relation.kind) << "：" << relation.detail;
      if (relation.missing_branch)
        output << "；所缺支为" << zhi(*relation.missing_branch);
      output << '\n';
    }
  }

  output << "\n【四课临盘作用】\n";
  output << "| 课位 | 上神 | 所临地盘 | 五行生克 | 刑冲合害 | 半合成局 | "
            "临盘断法 |\n";
  output << "| --- | --- | --- | --- | --- | --- | --- |\n";
  for (const auto &lesson : analysis.lessons) {
    const auto source = std::string(zh(lesson.position));
    const auto relations =
        relations_between(analysis.cross_layer_relations, source + "上神",
                          source + "下神所居地盘");
    output << "| " << source << " | " << zhi(lesson.upper) << " | "
           << zhi(lesson.lower_palace) << " | "
           << relation_cell(relations,
                            {BranchRelationKind::Sheng, BranchRelationKind::Ke,
                             BranchRelationKind::BiHe})
           << " | "
           << relation_cell(relations, {BranchRelationKind::TongLin,
                                        BranchRelationKind::Xing,
                                        BranchRelationKind::Chong,
                                        BranchRelationKind::LiuHe,
                                        BranchRelationKind::Hai})
           << " | "
           << relation_cell(relations, {BranchRelationKind::BanHe,
                                        BranchRelationKind::SanHe,
                                        BranchRelationKind::SanHui})
           << " | "
           << relation_cell(
                  relations,
                  {BranchRelationKind::TongLin, BranchRelationKind::Sheng,
                   BranchRelationKind::Ke, BranchRelationKind::BiHe,
                   BranchRelationKind::Xing, BranchRelationKind::Chong,
                   BranchRelationKind::LiuHe, BranchRelationKind::Hai,
                   BranchRelationKind::BanHe, BranchRelationKind::SanHe,
                   BranchRelationKind::SanHui},
                  true)
           << " |\n";
  }

  output << "\n【三传临盘作用】\n";
  output << "| 传位 | 传神 | 所临地盘 | 五行生克 | 刑冲合害 | 半合成局 | "
            "临盘断法 |\n";
  output << "| --- | --- | --- | --- | --- | --- | --- |\n";
  for (const auto &transmission : analysis.transmissions) {
    const auto source = std::string(zh(transmission.stage));
    const auto relations =
        relations_between(analysis.cross_layer_relations, source, "所临地盘");
    output << "| " << source << " | " << zhi(transmission.branch) << " | "
           << zhi(transmission.earth_position) << " | "
           << relation_cell(relations,
                            {BranchRelationKind::Sheng, BranchRelationKind::Ke,
                             BranchRelationKind::BiHe})
           << " | "
           << relation_cell(relations, {BranchRelationKind::TongLin,
                                        BranchRelationKind::Xing,
                                        BranchRelationKind::Chong,
                                        BranchRelationKind::LiuHe,
                                        BranchRelationKind::Hai})
           << " | "
           << relation_cell(relations, {BranchRelationKind::BanHe,
                                        BranchRelationKind::SanHe,
                                        BranchRelationKind::SanHui})
           << " | "
           << relation_cell(
                  relations,
                  {BranchRelationKind::TongLin, BranchRelationKind::Sheng,
                   BranchRelationKind::Ke, BranchRelationKind::BiHe,
                   BranchRelationKind::Xing, BranchRelationKind::Chong,
                   BranchRelationKind::LiuHe, BranchRelationKind::Hai,
                   BranchRelationKind::BanHe, BranchRelationKind::SanHe,
                   BranchRelationKind::SanHui},
                  true)
           << " |\n";
  }

  output << "\n【四课三传半合与成局】\n";
  output << "| 来源 | 参与支神 | 结构 | 成局状态 | 断法 |\n";
  output << "| --- | --- | --- | --- | --- |\n";
  bool has_group_relation = false;
  const auto write_group = [&](const BranchRelationAnalysis &relation) {
    std::string sources;
    for (std::size_t index = 0; index < relation.sources.size(); ++index) {
      if (index)
        sources += "、";
      sources += relation.sources[index];
    }
    std::string branches;
    for (std::size_t index = 0; index < relation.branches.size(); ++index) {
      if (index)
        branches += "、";
      branches += zhi(relation.branches[index]);
    }
    output << "| " << (sources.empty() ? "三传" : sources) << " | " << branches
           << " | " << zh(relation.kind) << " | "
           << (relation.missing_branch ? "待补" + zhi(*relation.missing_branch)
                                       : "完整成局")
           << " | " << relation.detail << " |\n";
    has_group_relation = true;
  };
  const auto write_groups_by_kind = [&](BranchRelationKind kind) {
    for (const auto &relation : analysis.transmission_relations)
      if (relation.kind == kind)
        write_group(relation);
    for (const auto &relation : analysis.cross_layer_relations)
      if (relation.kind == kind)
        write_group(relation);
  };
  write_groups_by_kind(BranchRelationKind::SanHe);
  write_groups_by_kind(BranchRelationKind::SanHui);
  write_groups_by_kind(BranchRelationKind::BanHe);
  if (!has_group_relation)
    output << "本课四课、三传及其临盘关系未见半合、三合或三会成立。\n";

  std::map<int, const MissingBranchContext *> missing_contexts;
  const auto collect_missing_context = [&](const auto &relations) {
    for (const auto &relation : relations)
      if (relation.missing_context)
        missing_contexts.emplace(
            static_cast<int>(relation.missing_context->branch),
            &*relation.missing_context);
  };
  collect_missing_context(analysis.transmission_relations);
  collect_missing_context(analysis.cross_layer_relations);
  output << "\n【半合所缺支查验】\n";
  if (missing_contexts.empty()) {
    output << "本课没有需要追查的半合所缺支。\n";
  } else {
    output << "| 所缺支 | 该支在天盘所临地盘 | 地盘本宫所乘天盘 | 四课上神 | "
              "四课下神 | 三传 | 月建日辰占时月将 | 空亡 | 补局判断 |\n";
    output << "| --- | --- | --- | --- | --- | --- | --- | --- | --- |\n";
    for (const auto &[_, context] : missing_contexts)
      output << "| " << zhi(context->branch) << " | "
             << zhi(context->heaven_lands_on_earth) << " | "
             << zhi(context->heaven_over_earth) << " | "
             << joined_zh_values(context->lesson_upper_positions) << " | "
             << joined_zh_values(context->lesson_lower_positions) << " | "
             << joined_zh_values(context->transmission_positions) << " | "
             << calendar_positions(*context) << " | "
             << (context->is_void ? "旬空" : "不空") << " | "
             << context->assessment << " |\n";
  }

  output << "\n【四课—三传交互作用】\n";
  output << "| 传位 | 传神 | 课位 | 上神 | 五行生克 | 刑冲合害 | 半合成局 | "
            "交互断法 |\n";
  output << "| --- | --- | --- | --- | --- | --- | --- | --- |\n";
  for (const auto &transmission : analysis.transmissions) {
    for (const auto &lesson : analysis.lessons) {
      const auto transmission_source = std::string(zh(transmission.stage));
      const auto lesson_source = std::string(zh(lesson.position));
      const auto relations =
          relations_between(analysis.cross_layer_relations, transmission_source,
                            lesson_source + "上神");
      if (relations.empty())
        continue;
      output << "| " << transmission_source << " | " << zhi(transmission.branch)
             << " | " << lesson_source << " | " << zhi(lesson.upper) << " | "
             << relation_cell(relations, {BranchRelationKind::Sheng,
                                          BranchRelationKind::Ke,
                                          BranchRelationKind::BiHe})
             << " | "
             << relation_cell(relations, {BranchRelationKind::TongLin,
                                          BranchRelationKind::Xing,
                                          BranchRelationKind::Chong,
                                          BranchRelationKind::LiuHe,
                                          BranchRelationKind::Hai})
             << " | " << relation_cell(relations, {BranchRelationKind::BanHe})
             << " | "
             << relation_cell(
                    relations,
                    {BranchRelationKind::TongLin, BranchRelationKind::Sheng,
                     BranchRelationKind::Ke, BranchRelationKind::BiHe,
                     BranchRelationKind::Xing, BranchRelationKind::Chong,
                     BranchRelationKind::LiuHe, BranchRelationKind::Hai,
                     BranchRelationKind::BanHe},
                    true)
             << " |\n";
    }
  }

  output << "\n【八门占断】\n";
  for (const auto &door : analysis.eight_doors)
    output << zh(door.door) << "（" << door.subject << "）：" << door.conclusion
           << '\n';

  output << "\n【类神定位】\n";
  output << "类神：" << analysis.lei_shen.name << '\n';
  output << "依据：" << analysis.lei_shen.reason << '\n';
  output << "入课入传：";
  if (analysis.lei_shen.appearances.empty())
    output << "未见";
  for (std::size_t index = 0; index < analysis.lei_shen.appearances.size();
       ++index) {
    if (index)
      output << ' ';
    output << zhi(analysis.lei_shen.appearances[index]);
  }
  output << '\n';
  output << "天盘所在：";
  if (analysis.lei_shen.plate_positions.empty())
    output << "未定";
  for (std::size_t index = 0; index < analysis.lei_shen.plate_positions.size();
       ++index) {
    if (index)
      output << ' ';
    output << zhi(analysis.lei_shen.plate_positions[index]);
  }
  output << '\n';

  output << "\n【课体与毕法结构】\n";
  if (analysis.patterns.empty())
    output << "未见当前规则库已登记课体。\n";
  for (const auto &pattern : analysis.patterns)
    output << "- " << pattern.name << "：" << pattern.implication << '\n';

  output << "\n【生扶与救应】\n";
  if (analysis.favorable.empty())
    output << "未见明确生扶通道。\n";
  else
    write_evidence(output, analysis.favorable);
  output << "\n【克制与阻隔】\n";
  if (analysis.unfavorable.empty())
    output << "未见明确克制通道。\n";
  else
    write_evidence(output, analysis.unfavorable);

  output << "\n【应期候选】\n";
  output
      << "| 次序 | 候支 | 应期法 | 所应阶段 | 迟速 | 候期层级 | 触发条件 |\n";
  output << "| --- | --- | --- | --- | --- | --- | --- |\n";
  for (const auto &timing : analysis.timing)
    output << "| " << timing.priority << " | " << zhi(timing.trigger) << " | "
           << zh(timing.rule) << " | " << zh(timing.phase) << " | "
           << zh(timing.pace) << " | " << timing.time_scope << " | "
           << timing.condition << " |\n";
  output << "应期详断：\n";
  for (const auto &timing : analysis.timing) {
    output << timing.priority << "、" << zhi(timing.trigger) << "（"
           << zh(timing.rule) << "）\n";
    output << "  - 排序依据：" << timing.priority_reason << '\n';
    output << "  - 所应之象：" << timing.manifestation << '\n';
    output << "  - 迟速限制：" << timing.constraints << '\n';
    output << "  - 推断依据：\n";
    for (const auto &item : timing.ke_chuan_basis)
      output << "    - " << item.rule << "【" << zh(item.nature) << "】："
             << item.detail << '\n';
  }

  output << "\n【总断】\n";
  output << "课象：" << zh(analysis.judgment) << '\n';
  output << "断语：" << analysis.conclusion << '\n';
  if (!analysis.review_points.empty()) {
    output << "复核：\n";
    for (const auto &point : analysis.review_points)
      output << "- " << point << '\n';
  }
}

} // namespace ZhouYi::DaLiuRenAnalysis
