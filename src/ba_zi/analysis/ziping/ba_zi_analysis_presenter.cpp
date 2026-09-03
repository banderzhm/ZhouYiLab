// C++23 Module - 八字分析结果展示实现
module ZhouYi.BaZiAnalysis.Presenter;

import magic_enum;
import ZhouYi.GanZhi;
import ZhouYi.ZhMapper;
import std;

namespace ZhouYi::BaZiAnalysis {

namespace {

namespace Mapper = ZhouYi::GanZhi::Mapper;
using ZhouYi::GanZhi::shi_shen_to_zh;

std::string element_name(WuXing element) {
  return std::string(Mapper::to_zh(element));
}

std::string stem_name(TianGan stem) { return std::string(Mapper::to_zh(stem)); }

std::string branch_name(DiZhi branch) {
  return std::string(Mapper::to_zh(branch));
}

template <typename E> std::string enum_code(E value) {
  return std::string(magic_enum::enum_name(value));
}

template <typename E> std::string zh_text(E value) {
  return std::string(ZhouYi::Mapper::to_zh(value));
}

std::string zh_special_pattern_status(SpecialPatternStatus status) {
  return std::string(ZhouYi::Mapper::to_zh(status));
}

std::string zh_useful_god_status(UsefulGodStatus status) {
  return std::string(ZhouYi::Mapper::to_zh(status));
}

std::string zh_severity(ComboSeverity severity) {
  return std::string(ZhouYi::Mapper::to_zh(severity));
}

std::string zh_pattern_condition(PatternCondition condition) {
  return std::string(ZhouYi::Mapper::to_zh(condition));
}

double untransformed_combine_penalty(const StemRelation &relation,
                                     const AnalysisConfig &config) {
  if (relation.effective)
    return 15.0;
  const int distance =
      std::abs(relation.first_position - relation.second_position);
  if (distance == 1)
    return config.adjacent_stem_combine_penalty;
  if (distance == 2)
    return config.distant_stem_combine_penalty;
  return config.distant_stem_combine_penalty * 0.5;
}

nlohmann::json evidence_json(const Evidence &evidence) {
  return {{"rule", evidence.rule},
          {"subject", evidence.subject},
          {"relation", evidence.relation},
          {"points", evidence.points},
          {"reason", evidence.reason}};
}

nlohmann::json candidate_json(const ShenCandidate &candidate) {
  nlohmann::json reasons = nlohmann::json::array();
  nlohmann::json conflicts = nlohmann::json::array();
  for (const auto &item : candidate.reasons)
    reasons.push_back(evidence_json(item));
  for (const auto &item : candidate.conflicts)
    conflicts.push_back(evidence_json(item));
  nlohmann::json effects = nlohmann::json::array();
  for (const auto &item : candidate.effects) {
    effects.push_back({{"type", enum_code(item.type)},
                       {"type_zh", zh_text(item.type)},
                       {"score", item.score},
                       {"requires_capacity", item.requires_capacity},
                       {"reason", item.reason}});
  }
  return {{"stem", stem_name(candidate.stem)},
          {"element", element_name(candidate.element)},
          {"role", candidate.role},
          {"raw_score", candidate.raw_score},
          {"effective_score", candidate.effective_score},
          {"percent", candidate.percent},
          {"present_in_chart", candidate.present_in_chart},
          {"effects", effects},
          {"reasons", reasons},
          {"conflicts", conflicts}};
}

} // namespace

nlohmann::json Detail::render_json(const AnalysisResult &result) {
  if (result.method == AnalysisMethod::BlindSchool && result.blind_analysis) {
    const auto &blind = *result.blind_analysis;
    nlohmann::json works = nlohmann::json::array();
    for (const auto &work : blind.work_chains) {
      nlohmann::json evidence = nlohmann::json::array();
      for (const auto &item : work.evidence)
        evidence.push_back(evidence_json(item));
      works.push_back({{"source", work.source},
                       {"target", work.target},
                       {"direction", work.direction},
                       {"relation", work.relation},
                       {"result", work.result},
                       {"source_position", work.source_position},
                       {"target_position", work.target_position},
                       {"source_ten_god", work.source_ten_god},
                       {"target_ten_god", work.target_ten_god},
                       {"effective_power", work.effective_power},
                       {"effective", work.effective},
                       {"evidence", evidence}});
    }
    nlohmann::json palaces = nlohmann::json::array();
    for (const auto &palace : blind.palaces)
      palaces.push_back({{"position", palace.position},
                         {"palace", palace.palace},
                         {"role", palace.role},
                         {"reason", palace.reason}});
    nlohmann::json occurrences = nlohmann::json::array();
    for (const auto &occurrence : result.ten_god_occurrences)
      occurrences.push_back(
          {{"stem", stem_name(occurrence.stem)},
           {"ten_god", std::string(shi_shen_to_zh(occurrence.ten_god))},
           {"position", occurrence.position},
           {"pillar_position", occurrence.pillar_position},
           {"exposed", occurrence.exposed},
           {"hidden_level", enum_code(occurrence.hidden_level)},
           {"raw_power", occurrence.raw_power},
           {"effective_power", occurrence.effective_power},
           {"kong_wang", occurrence.kong_wang},
           {"clashed", occurrence.clashed}});
    nlohmann::json relations = nlohmann::json::array();
    for (const auto &relation : result.relations) {
      nlohmann::json evidence = nlohmann::json::array();
      for (const auto &item : relation.evidence)
        evidence.push_back(evidence_json(item));
      relations.push_back(
          {{"type", enum_code(relation.type)},
           {"type_zh", std::string(ZhouYi::Mapper::to_zh(relation.type))},
           {"first", branch_name(relation.first)},
           {"second", branch_name(relation.second)},
           {"first_position", relation.first_position},
           {"second_position", relation.second_position},
           {"symmetric", relation.symmetric},
           {"effective", relation.effective},
           {"direction", relation.direction},
           {"impact", relation.impact},
           {"evidence", evidence}});
    }
    nlohmann::json stem_relations = nlohmann::json::array();
    for (const auto &relation : result.stem_relations) {
      nlohmann::json evidence = nlohmann::json::array();
      for (const auto &item : relation.evidence)
        evidence.push_back(evidence_json(item));
      stem_relations.push_back({{"type", enum_code(relation.type)},
                                {"first", stem_name(relation.first)},
                                {"second", stem_name(relation.second)},
                                {"first_position", relation.first_position},
                                {"second_position", relation.second_position},
                                {"transform", relation.transform},
                                {"effective", relation.effective},
                                {"evidence", evidence}});
    }
    return {
        {"schema_version", result.schema_version},
        {"rule_set", blind.rule_set},
        {"method", "BlindSchool"},
        {"method_zh", "盲派法"},
        {"input_pillars",
         {result.input_pillars[0].to_string(),
          result.input_pillars[1].to_string(),
          result.input_pillars[2].to_string(),
          result.input_pillars[3].to_string()}},
        {"day_master", stem_name(result.day_master)},
        {"palaces", palaces},
        {"birth_context",
         {{"has_exact_time", result.birth_context.has_exact_time},
          {"year", result.birth_context.year},
          {"month", result.birth_context.month},
          {"day", result.birth_context.day},
          {"hour", result.birth_context.hour},
          {"minute", result.birth_context.minute},
          {"second", result.birth_context.second},
          {"previous_jie", result.birth_context.previous_jie},
          {"days_since_jie", result.birth_context.days_since_jie},
          {"human_command",
           result.birth_context.human_command
               ? nlohmann::json(stem_name(*result.birth_context.human_command))
               : nlohmann::json{}},
          {"human_command_reason", result.birth_context.human_command_reason}}},
        {"occurrences", occurrences},
        {"relations", relations},
        {"stem_relations", stem_relations},
        {"body", blind.body},
        {"targets", blind.targets},
        {"work_chains", works},
        {"structures", blind.structures},
        {"symbols", blind.symbols},
        {"useful_gods", blind.useful_gods},
        {"waste_gods", blind.waste_gods},
        {"event_impacts",
         [&] {
           nlohmann::json events = nlohmann::json::array();
           for (const auto &event : blind.event_impacts)
             events.push_back({{"type", event.type},
                               {"pressure", event.pressure},
                               {"triggers", event.triggers},
                               {"evidence", event.evidence}});
           return events;
         }()},
        {"tombs",
         [&] {
           nlohmann::json tombs = nlohmann::json::array();
           for (const auto &tomb : blind.tombs)
             tombs.push_back(
                 {{"tomb_branch", tomb.tomb_branch},
                  {"stored_stem", tomb.stored_stem},
                  {"tomb_position", tomb.tomb_position},
                  {"stored_stem_position", tomb.stored_stem_position},
                  {"relation", tomb.relation},
                  {"state", tomb.state},
                  {"opened", tomb.opened},
                  {"evidence", tomb.evidence}});
           return tombs;
         }()},
        {"transit_impacts", blind.transit_impacts},
        {"warnings", blind.warnings}};
  }
  nlohmann::json balance = nlohmann::json::array();
  for (const auto &stat : result.element_balance) {
    nlohmann::json evidence = nlohmann::json::array();
    for (const auto &item : stat.evidence)
      evidence.push_back(evidence_json(item));
    balance.push_back({{"element", element_name(stat.element)},
                       {"raw", stat.raw},
                       {"percent", stat.percent},
                       {"evidence", evidence}});
  }
  nlohmann::json relations = nlohmann::json::array();
  for (const auto &relation : result.relations) {
    nlohmann::json evidence = nlohmann::json::array();
    for (const auto &item : relation.evidence)
      evidence.push_back(evidence_json(item));
    nlohmann::json members = nlohmann::json::array();
    for (const auto branch : relation.members)
      members.push_back(branch_name(branch));
    relations.push_back(
        {{"type", enum_code(relation.type)},
         {"type_zh", std::string(ZhouYi::Mapper::to_zh(relation.type))},
         {"first", branch_name(relation.first)},
         {"second", branch_name(relation.second)},
         {"first_position", relation.first_position},
         {"second_position", relation.second_position},
         {"symmetric", relation.symmetric},
         {"effective", relation.effective},
         {"direction", relation.direction},
         {"strength", enum_code(relation.strength)},
         {"strength_zh", zh_text(relation.strength)},
         {"impact", relation.impact},
         {"evidence", evidence},
         {"members", members},
         {"virtual_branch",
          relation.virtual_branch
              ? nlohmann::json(branch_name(*relation.virtual_branch))
              : nlohmann::json{}}});
  }
  nlohmann::json stem_relations = nlohmann::json::array();
  for (const auto &relation : result.stem_relations) {
    nlohmann::json evidence = nlohmann::json::array();
    for (const auto &item : relation.evidence)
      evidence.push_back(evidence_json(item));
    stem_relations.push_back(
        {{"type", enum_code(relation.type)},
         {"type_zh", std::string(ZhouYi::Mapper::to_zh(relation.type))},
         {"first", stem_name(relation.first)},
         {"second", stem_name(relation.second)},
         {"transform", relation.transform},
         {"effective", relation.effective},
         {"evidence", evidence}});
  }
  nlohmann::json candidates = nlohmann::json::array();
  for (const auto &candidate : result.useful_gods.candidates)
    candidates.push_back(candidate_json(candidate));
  nlohmann::json capacity_evidence = nlohmann::json::array();
  for (const auto &item : result.carrying_capacity.evidence)
    capacity_evidence.push_back(evidence_json(item));
  nlohmann::json capacity_penalties = nlohmann::json::array();
  for (const auto &item : result.carrying_capacity.penalties)
    capacity_penalties.push_back(evidence_json(item));
  nlohmann::json strength_evidence = nlohmann::json::array();
  for (const auto &item : result.strength.evidence)
    strength_evidence.push_back(evidence_json(item));
  nlohmann::json nayin = nlohmann::json::array();
  for (const auto &item : result.nayin) {
    nayin.push_back({{"pillar", item.pillar},
                     {"name", item.name},
                     {"element", element_name(item.element)}});
  }
  nlohmann::json climate_evidence = nlohmann::json::array();
  for (const auto &item : result.climate.evidence)
    climate_evidence.push_back(evidence_json(item));
  nlohmann::json climate_stems = nlohmann::json::array();
  for (const auto stem : result.climate.preferred_stems)
    climate_stems.push_back(stem_name(stem));
  nlohmann::json climate_present_stems = nlohmann::json::array();
  for (const auto stem : result.climate.present_stems)
    climate_present_stems.push_back(stem_name(stem));
  nlohmann::json pattern_evidence = nlohmann::json::array();
  for (const auto &item : result.pattern.evidence)
    pattern_evidence.push_back(evidence_json(item));
  nlohmann::json competing_patterns = nlohmann::json::array();
  for (const auto item : result.pattern.competing_patterns) {
    competing_patterns.push_back(
        {{"name", enum_code(item)}, {"name_zh", zh_text(item)}});
  }
  nlohmann::json special = nlohmann::json::array();
  for (const auto &item : result.special_patterns) {
    nlohmann::json evidence = nlohmann::json::array();
    for (const auto &entry : item.evidence)
      evidence.push_back(evidence_json(entry));
    special.push_back(
        {{"name", enum_code(item.name)},
         {"name_zh", zh_text(item.name)},
         {"status", enum_code(item.status)},
         {"element", item.element ? nlohmann::json(element_name(*item.element))
                                  : nlohmann::json{}},
         {"evidence", evidence},
         {"reasons", item.reasons}});
  }
  nlohmann::json combos = nlohmann::json::array();
  for (const auto &item : result.ten_god_combos) {
    nlohmann::json evidence = nlohmann::json::array();
    for (const auto &entry : item.evidence)
      evidence.push_back(evidence_json(entry));
    combos.push_back(
        {{"kind", enum_code(item.kind)},
         {"name_zh", std::string(ZhouYi::Mapper::to_zh(item.kind))},
         {"severity", enum_code(item.severity)},
         {"note", item.note},
         {"evidence", evidence}});
  }
  nlohmann::json occurrences = nlohmann::json::array();
  for (const auto &item : result.ten_god_occurrences) {
    occurrences.push_back({{"stem", stem_name(item.stem)},
                           {"ten_god", shi_shen_to_zh(item.ten_god)},
                           {"position", item.position},
                           {"pillar_position", item.pillar_position},
                           {"exposed", item.exposed},
                           {"hidden_level", enum_code(item.hidden_level)},
                           {"hidden_level_zh", zh_text(item.hidden_level)},
                           {"raw_power", item.raw_power},
                           {"effective_power", item.effective_power},
                           {"kong_wang", item.kong_wang},
                           {"clashed", item.clashed}});
  }
  nlohmann::json kong_evidence = nlohmann::json::array();
  for (const auto &item : result.kong_wang.evidence)
    kong_evidence.push_back(evidence_json(item));
  nlohmann::json kong_positions = result.kong_wang.affected_positions;
  nlohmann::json chain = nlohmann::json::array();
  for (const auto element : result.sheng_ke_chain.chain)
    chain.push_back(element_name(element));
  nlohmann::json chain_evidence = nlohmann::json::array();
  for (const auto &item : result.sheng_ke_chain.evidence)
    chain_evidence.push_back(evidence_json(item));
  nlohmann::json fortunes = nlohmann::json::array();
  for (const auto &item : result.fortune_impacts) {
    nlohmann::json evidence = nlohmann::json::array();
    for (const auto &entry : item.evidence)
      evidence.push_back(evidence_json(entry));
    fortunes.push_back({{"pillar", item.pillar.to_string()},
                        {"label", item.label},
                        {"score", item.score},
                        {"reasons", item.reasons},
                        {"review_notes", item.review_notes},
                        {"evidence", evidence}});
  }
  nlohmann::json input_pillars = nlohmann::json::array();
  for (const auto &pillar : result.input_pillars)
    input_pillars.push_back(pillar.to_string());
  nlohmann::json verification;
  if (result.useful_gods.verification) {
    nlohmann::json evidence = nlohmann::json::array();
    nlohmann::json exact_roots = nlohmann::json::array();
    nlohmann::json same_element_support = nlohmann::json::array();
    for (const auto &item : result.useful_gods.verification->evidence)
      evidence.push_back(evidence_json(item));
    for (const auto &item : result.useful_gods.verification->exact_roots)
      exact_roots.push_back(evidence_json(item));
    for (const auto &item :
         result.useful_gods.verification->same_element_support)
      same_element_support.push_back(evidence_json(item));
    verification = {
        {"root_level", result.useful_gods.verification->root_level},
        {"tou_gan", result.useful_gods.verification->tou_gan},
        {"is_chonged", result.useful_gods.verification->is_chonged},
        {"is_he", result.useful_gods.verification->is_he},
        {"is_transformed", result.useful_gods.verification->is_transformed},
        {"effective", result.useful_gods.verification->effective},
        {"power", result.useful_gods.verification->power},
        {"combine_penalty", result.useful_gods.verification->combine_penalty},
        {"carrying_capacity",
         result.useful_gods.verification->carrying_capacity},
        {"exact_roots", exact_roots},
        {"same_element_support", same_element_support},
        {"evidence", evidence}};
  }

  return {
      {"schema_version", result.schema_version},
      {"rule_set", result.rule_set},
      {"analysis_config",
       {{"stem_weight", result.config.stem_weight},
        {"main_hidden_stem_weight", result.config.main_hidden_stem_weight},
        {"middle_hidden_stem_weight", result.config.middle_hidden_stem_weight},
        {"residual_hidden_stem_weight",
         result.config.residual_hidden_stem_weight},
        {"month_branch_multiplier", result.config.month_branch_multiplier},
        {"clashed_root_multiplier", result.config.clashed_root_multiplier},
        {"kong_wang_root_multiplier", result.config.kong_wang_root_multiplier},
        {"climate_sufficient_power", result.config.climate_sufficient_power},
        {"climate_excessive_percent", result.config.climate_excessive_percent},
        {"adjacent_arch_weight", result.config.adjacent_arch_weight},
        {"distant_arch_weight", result.config.distant_arch_weight},
        {"adjacent_stem_combine_penalty",
         result.config.adjacent_stem_combine_penalty},
        {"distant_stem_combine_penalty",
         result.config.distant_stem_combine_penalty},
        {"low_capacity_threshold", result.config.low_capacity_threshold},
        {"useful_god_effective_threshold",
         result.config.useful_god_effective_threshold},
        {"review_score_gap", result.config.review_score_gap}}},
      {"birth_context",
       {{"has_exact_time", result.birth_context.has_exact_time},
        {"year", result.birth_context.year},
        {"month", result.birth_context.month},
        {"day", result.birth_context.day},
        {"hour", result.birth_context.hour},
        {"minute", result.birth_context.minute},
        {"second", result.birth_context.second},
        {"is_male", result.birth_context.is_male},
        {"previous_jie", result.birth_context.previous_jie},
        {"days_since_jie", result.birth_context.days_since_jie},
        {"command_table_version", result.birth_context.command_table_version},
        {"human_command",
         result.birth_context.human_command
             ? nlohmann::json(stem_name(*result.birth_context.human_command))
             : nlohmann::json{}},
        {"human_command_reason", result.birth_context.human_command_reason}}},
      {"input_pillars", input_pillars},
      {"day_master", stem_name(result.day_master)},
      {"nayin", nayin},
      {"element_balance", balance},
      {"relations", relations},
      {"stem_relations", stem_relations},
      {"kong_wang",
       {{"branches",
         {branch_name(result.kong_wang.branches[0]),
          branch_name(result.kong_wang.branches[1])}},
        {"affected_positions", kong_positions},
        {"root_multiplier", result.kong_wang.root_multiplier},
        {"evidence", kong_evidence}}},
      {"climate",
       {{"needed", result.climate.needed},
        {"present", result.climate.present},
        {"element", element_name(result.climate.element)},
        {"preferred_stems", climate_stems},
        {"present_stems", climate_present_stems},
        {"state", enum_code(result.climate.state)},
        {"usable_power", result.climate.usable_power},
        {"urgency", enum_code(result.climate.urgency)},
        {"reason", result.climate.reason},
        {"evidence", climate_evidence}}},
      {"pattern",
       {{"name", enum_code(result.pattern.name)},
        {"name_zh", zh_text(result.pattern.name)},
        {"type", enum_code(result.pattern.pattern_type)},
        {"type_zh", zh_text(result.pattern.pattern_type)},
        {"status", enum_code(result.pattern.status)},
        {"cheng_ge", result.pattern.cheng_ge},
        {"condition", enum_code(result.pattern.condition)},
        {"disease_power", result.pattern.disease_power},
        {"medicine_power", result.pattern.medicine_power},
        {"evidence", pattern_evidence},
        {"supports", result.pattern.supports},
        {"conflicts", result.pattern.conflicts},
        {"basis", enum_code(result.pattern.basis)},
        {"pattern_stem",
         result.pattern.pattern_stem
             ? nlohmann::json(stem_name(*result.pattern.pattern_stem))
             : nlohmann::json{}},
        {"human_command",
         result.pattern.human_command
             ? nlohmann::json(stem_name(*result.pattern.human_command))
             : nlohmann::json{}},
        {"competing_patterns", competing_patterns}}},
      {"special_patterns", special},
      {"ten_god_combos", combos},
      {"ten_god_occurrences", occurrences},
      {"strength",
       {{"score", result.strength.score},
        {"level", enum_code(result.strength.level)},
        {"level_zh", zh_text(result.strength.level)},
        {"month_command", result.strength.month_command},
        {"roots", result.strength.roots},
        {"month_tomb", result.strength.month_tomb},
        {"tomb_opened", result.strength.tomb_opened},
        {"tomb_opening_cause", result.strength.tomb_opening_cause},
        {"stem_support", result.strength.stem_support},
        {"season_adjustment", result.strength.season_adjustment},
        {"relation_adjustment", result.strength.relation_adjustment},
        {"evidence", strength_evidence}}},
      {"carrying_capacity",
       {{"overall", result.carrying_capacity.overall},
        {"root_stability", result.carrying_capacity.root_stability},
        {"climate_workability", result.carrying_capacity.climate_workability},
        {"circulation", result.carrying_capacity.circulation},
        {"penalties", capacity_penalties},
        {"evidence", capacity_evidence}}},
      {"useful_gods",
       {{"status", enum_code(result.useful_gods.status)},
        {"method", enum_code(result.useful_gods.method)},
        {"reason", result.useful_gods.reason},
        {"route", enum_code(result.useful_gods.route)},
        {"selected", result.useful_gods.yong_shen
                         ? candidate_json(*result.useful_gods.yong_shen)
                         : nlohmann::json{}},
        {"xi_shen", result.useful_gods.xi_shen
                        ? candidate_json(*result.useful_gods.xi_shen)
                        : nlohmann::json{}},
        {"ji_shen", result.useful_gods.ji_shen
                        ? candidate_json(*result.useful_gods.ji_shen)
                        : nlohmann::json{}},
        {"chou_shen", result.useful_gods.chou_shen
                          ? candidate_json(*result.useful_gods.chou_shen)
                          : nlohmann::json{}},
        {"xian_shen", result.useful_gods.xian_shen
                          ? candidate_json(*result.useful_gods.xian_shen)
                          : nlohmann::json{}},
        {"verification", verification},
        {"candidates", candidates},
        {"role_notes", result.useful_gods.role_notes},
        {"warnings", result.useful_gods.warnings}}},
      {"sheng_ke_chain",
       {{"chain", chain},
        {"breaks", result.sheng_ke_chain.breaks},
        {"smooth", result.sheng_ke_chain.smooth},
        {"evidence", chain_evidence}}},
      {"fortune_impacts", fortunes},
      {"warnings", result.warnings}};
}

std::string Detail::render_zh(const AnalysisResult &result) {
  std::ostringstream output;
  if (result.method == AnalysisMethod::BlindSchool && result.blind_analysis) {
    const auto &blind = *result.blind_analysis;
    output << "# 八字分析报告（盲派法，可解释版）\n\n";
    output << "## 命局与理论入口\n- 四柱：";
    for (const auto &pillar : result.input_pillars)
      output << pillar.to_string() << ' ';
    output << "\n- 日主：" << stem_name(result.day_master)
           << "；分析体系：宾主—体用—做功；规则集：" << blind.rule_set
           << "；报告格式版本：" << result.schema_version << "。\n";
    output << "- 宫位主宾：";
    for (const auto &palace : blind.palaces)
      output << palace.palace << "=" << palace.role << "（" << palace.reason
             << "）；";
    output << "\n";
    output << "- 纳音：";
    for (const auto &item : result.nayin)
      output << item.pillar << "为" << item.name << "（"
             << element_name(item.element) << "）；";
    output << "\n- 旬空：" << branch_name(result.kong_wang.branches[0]) << "、"
           << branch_name(result.kong_wang.branches[1]) << "。\n";
    if (result.birth_context.has_exact_time)
      output << "- 出生时刻：" << result.birth_context.year << "年"
             << result.birth_context.month << "月" << result.birth_context.day
             << "日 " << result.birth_context.hour << "时"
             << result.birth_context.minute << "分。\n";
    output << "\n## 体与目标用\n- 体与主位资源：";
    for (const auto &item : blind.body)
      if (!item.empty())
        output << item << "、";
    output << "\n- 目标用与宾位对象：";
    for (const auto &item : blind.targets)
      output << item << "、";
    output << "\n\n## 做功链\n";
    if (blind.work_chains.empty() ||
        std::none_of(blind.work_chains.begin(), blind.work_chains.end(),
                     [](const auto &work) { return work.effective; }))
      output << "- "
                "当前未形成有效做功链，已列出关系记录，需结合根气、空亡和岁运复"
                "核。\n";
    for (const auto &work : blind.work_chains) {
      output << "- " << work.source << " → " << work.target << "："
             << work.relation << "（" << work.direction << "），" << work.result
             << "；十神：" << work.source_ten_god << "→" << work.target_ten_god
             << "；有效力量：" << work.effective_power << "。";
      if (!work.evidence.empty()) {
        output << "  依据：";
        for (const auto &item : work.evidence)
          output << item.reason << "；";
      }
      output << "\n";
    }
    output << "\n## 功神与废神\n- 功神：";
    for (const auto &item : blind.useful_gods)
      output << item << "、";
    output << "\n- 废神：";
    for (const auto &item : blind.waste_gods)
      output << item << "、";
    output << "\n\n## 结构与取象\n";
    for (const auto &item : blind.structures)
      output << "- 结构：" << item << "。\n";
    for (const auto &item : blind.symbols)
      output << "- 象：" << item << "。\n";
    output << "\n## 墓库状态\n";
    for (const auto &tomb : blind.tombs)
      output << "- " << tomb.tomb_branch << "收" << tomb.stored_stem << "："
             << tomb.relation << "，" << tomb.state << "（" << tomb.evidence
             << "）。\n";
    output << "\n## 应事分项\n";
    for (const auto &event : blind.event_impacts) {
      output << "- " << event.type << "：作用压力 " << event.pressure
             << "。触发：";
      for (const auto &trigger : event.triggers)
        output << trigger << "；";
      output << "证据：";
      for (const auto &evidence : event.evidence)
        output << evidence << "；";
      output << "\n";
    }
    output << "\n## 岁运引动\n";
    if (blind.transit_impacts.empty())
      output << "- 未传入或未识别到岁运直接引动。\n";
    for (const auto &impact : blind.transit_impacts)
      output << "- " << impact << "。\n";
    for (const auto &item : blind.warnings)
      output << "- 待复核：" << item << "。\n";
    return output.str();
  }
  const auto candidate_name =
      [&](const std::optional<ShenCandidate> &candidate) {
        if (!candidate)
          return std::string("未定");
        return stem_name(candidate->stem) + "（" +
               element_name(candidate->element) + "）";
      };
  const auto members_name = [](const BranchRelation &relation) {
    std::string value;
    for (const auto branch : relation.members)
      value += branch_name(branch);
    return value.empty()
               ? branch_name(relation.first) + branch_name(relation.second)
               : value;
  };

  output << "# 八字分析报告（子平法，可解释版）\n\n";
  output << "## 命局与判定范围\n";
  output << "- 四柱：";
  for (const auto &pillar : result.input_pillars)
    output << pillar.to_string() << ' ';
  output << "\n- 日主：" << stem_name(result.day_master)
         << "；分析体系：子平格局病药法（兼取调候）；报告格式版本："
         << result.schema_version << "。\n";
  if (result.birth_context.has_exact_time) {
    output << "- 出生时刻：" << result.birth_context.year << "年"
           << result.birth_context.month << "月" << result.birth_context.day
           << "日 " << result.birth_context.hour << "时"
           << result.birth_context.minute << "分；前一节："
           << result.birth_context.previous_jie << "；"
           << (result.birth_context.human_command
                   ? "人元司令：" +
                         stem_name(*result.birth_context.human_command)
                   : "人元司令未定")
           << "。\n";
    output << "  - 司令依据：" << result.birth_context.human_command_reason
           << "。\n";
  } else {
    output << "- 出生时刻：未传入；只论月令本气取格，不冒称人元司令。\n";
  }
  output << "- 纳音：";
  for (const auto &item : result.nayin)
    output << item.pillar << item.name << "（" << element_name(item.element)
           << "） ";
  output << "\n\n";

  output << "【算法口径：天干各计 1.0；地支本气/中气/余气分别计 "
            "1.0/0.6/0.3；月令藏干再乘 1.5，合计后归一化为五行占比。】\n";
  output << "【算法口径：日主强弱 = 得令 40% + 得地 30% + 得势 15% + 季节 10% "
            "+ 关系 5%。】\n";
  output << "【算法口径：承载能力 = 日主强弱 40% + 根气稳定 30% + 调候可发挥 "
            "20% + 生克流通 10%。】\n";
  output << "## 五行与日主承载\n";
  output << "- 五行占比：";
  for (const auto &stat : result.element_balance) {
    output << element_name(stat.element) << ' ' << std::fixed
           << std::setprecision(1) << stat.percent << "% ";
  }
  output << "\n- 日主强弱：" << zh_text(result.strength.level) << "，"
         << result.strength.score << "/100"
         << "（得令 " << result.strength.month_command << "、得地 "
         << result.strength.roots << "、得势 " << result.strength.stem_support
         << "、季节 " << result.strength.season_adjustment << "、关系 "
         << result.strength.relation_adjustment << "）。\n";
  if (result.strength.month_tomb) {
    output << "- 月令墓库："
           << (result.strength.tomb_opened
                   ? "已开，开库原因：" + result.strength.tomb_opening_cause
                   : "未开")
           << "。\n";
  }
  output << "- 承载能力：" << result.carrying_capacity.overall << "/100"
         << "（根气稳定 " << result.carrying_capacity.root_stability
         << "、寒暖燥湿可发挥 " << result.carrying_capacity.climate_workability
         << "、生克流通 " << result.carrying_capacity.circulation << "）。\n\n";

  output << "## 调候、格局与特殊结构\n";
  output << "- 调候：" << result.climate.reason << "；优先级："
         << ZhouYi::Mapper::to_zh(result.climate.urgency) << "；状态："
         << ZhouYi::Mapper::to_zh(result.climate.state) << "；有效力量 "
         << result.climate.usable_power << "。\n";
  output << "- 格局：" << zh_text(result.pattern.name) << "（"
         << zh_text(result.pattern.pattern_type) << "，"
         << zh_pattern_condition(result.pattern.condition) << "）。\n";
  output << "  - 取格依据：" << ZhouYi::Mapper::to_zh(result.pattern.basis);
  if (result.pattern.pattern_stem)
    output << "，格神" << stem_name(*result.pattern.pattern_stem);
  if (result.pattern.human_command)
    output << "；分日司令" << stem_name(*result.pattern.human_command);
  output << "。\n";
  if (result.pattern.disease_power > 0.0 ||
      result.pattern.medicine_power > 0.0) {
    output << "  - 病药强弱：病神有效力量 " << result.pattern.disease_power
           << "，药神有效力量 " << result.pattern.medicine_power << "。\n";
  }
  for (const auto &support : result.pattern.supports)
    output << "  - 有利条件：" << support << "。\n";
  for (const auto &conflict : result.pattern.conflicts)
    output << "  - 格局病处：" << conflict << "。\n";
  for (const auto &special : result.special_patterns) {
    output << "- " << zh_text(special.name) << "："
           << zh_special_pattern_status(special.status);
    if (special.element)
      output << "，对应" << element_name(*special.element) << "。";
    else
      output << "。";
    for (const auto &reason : special.reasons)
      output << " " << reason << "。";
    output << "\n";
  }
  output << "\n";

  output << "【算法口径：格局病药与调候分层计分。调候未见时最高40分，已见不足时"
            "5～25分，已足0分，太过时扣分；格局病药独立加减，不被“夏月固定加水”"
            "覆盖。】\n";
  output << "【算法口径：取用权重 = 单个候选的命局有效分 ÷ "
            "全部正向候选命局有效分之和 × "
            "100%；权重只用于候选排序，不等同吉凶概率。】\n";
  output << "## 用神候选与依据\n";
  output << "- 取用结论：" << zh_useful_god_status(result.useful_gods.status)
         << "；取用方法：" << zh_text(result.useful_gods.method) << "；理由："
         << result.useful_gods.reason << "。\n";
  output << "- 主取用路线：" << zh_text(result.useful_gods.route) << "。\n";
  output << "- 角色：用神 " << candidate_name(result.useful_gods.yong_shen)
         << "；喜神 " << candidate_name(result.useful_gods.xi_shen) << "；忌神 "
         << candidate_name(result.useful_gods.ji_shen) << "；仇神 "
         << candidate_name(result.useful_gods.chou_shen) << "；闲神 "
         << candidate_name(result.useful_gods.xian_shen) << "。\n";
  for (const auto &note : result.useful_gods.role_notes)
    output << "  - 分干定性：" << note << "。\n";
  if (result.useful_gods.route == UsefulGodRoute::OfficerProtectsWealth) {
    output << "  - "
              "角色相容：土财生金官，故土列喜；木比劫列忌，水印生比并泄官列仇；"
              "火能生财但同时克官，丙为伤官尤忌直犯庚官，故火只作受限闲神，不与"
              "庚并列为喜。\n";
  }
  const std::size_t candidate_count =
      std::min<std::size_t>(5, result.useful_gods.candidates.size());
  for (std::size_t index = 0; index < candidate_count; ++index) {
    const auto &candidate = result.useful_gods.candidates[index];
    output << "- 候选 " << index + 1 << "：" << stem_name(candidate.stem)
           << "（" << element_name(candidate.element) << "），"
           << "取用初评 " << candidate.raw_score << "分，命局有效分 "
           << candidate.effective_score << "分，取用权重 " << candidate.percent
           << "%";
    if (!candidate.present_in_chart)
      output << "【原局未见本干，仅表示该五行方向的理论候选】";
    output << "。";
    if (!candidate.effects.empty()) {
      output << " 作用：";
      for (const auto &effect : candidate.effects) {
        output << effect.reason << "（" << zh_text(effect.type)
               << (effect.requires_capacity ? "，受日主承载能力折算"
                                            : "，直接计入")
               << "，" << (effect.score >= 0.0 ? "+" : "") << effect.score
               << "分）；";
      }
    }
    output << "\n";
  }
  if (result.useful_gods.verification) {
    output
        << "【算法口径：用神效力只验该候选本干。本气/中气/余气根为60/40/"
           "20，所在支受冲或落旬空各自折减；本干精确透出+20，再加承载能力的20%"
           "；天干合化扣15，合而不化按紧贴12、隔位6、远隔3折减；效力达到"
        << result.config.useful_god_effective_threshold
        << "分且有根、未被合化，视为可以定用。同五行异干只记助力，不冒充透干或"
           "本干根。】\n";
    const auto &verification = *result.useful_gods.verification;
    output << "- 用神验证：" << verification.root_level << "，"
           << (verification.tou_gan ? "已透干" : "未透干") << "，"
           << (verification.is_chonged ? "根气受冲" : "根气未受冲") << "，"
           << (verification.is_transformed
                   ? "参与有效合化"
                   : (verification.is_he
                          ? "参与天干合而未化，合绊折减 " +
                                [&] {
                                  std::ostringstream value;
                                  value << std::fixed << std::setprecision(1)
                                        << verification.combine_penalty;
                                  return value.str();
                                }()
                          : "未参与天干五合"))
           << "，"
           << "效力 " << verification.power << "/100，"
           << (verification.effective ? "验证通过" : "需要岁运或人工复核")
           << "。\n";
    for (const auto &root : verification.exact_roots)
      output << "  - 本干根：" << root.subject << "，" << root.reason << "。\n";
    for (const auto &support : verification.same_element_support)
      output << "  - 同类助力：" << support.subject << "，" << support.reason
             << "。\n";
  }
  output << "\n";

  output << "## 合冲刑害、十神组合与流通\n";
  output << "- 旬空：" << branch_name(result.kong_wang.branches[0]) << "、"
         << branch_name(result.kong_wang.branches[1]) << "；命局受影响柱位数："
         << result.kong_wang.affected_positions.size() << "；根气保留系数 "
         << result.kong_wang.root_multiplier << "。\n";
  if (result.relations.empty())
    output << "- 原局未检测到本规则集收录的地支关系。\n";
  for (const auto &relation : result.relations) {
    output << "- 地支" << ZhouYi::Mapper::to_zh(relation.type) << "："
           << members_name(relation);
    if (!relation.direction.empty() &&
        (relation.type == BranchRelationKind::Punishment ||
         relation.type == BranchRelationKind::SelfPunishment ||
         relation.type == BranchRelationKind::ThreePunishment)) {
      output << "（" << relation.direction << "）";
    }
    if (relation.strength != RelationStrength::None)
      output << "，结构强度：" << zh_text(relation.strength);
    if (relation.virtual_branch)
      output << "，虚拱" << branch_name(*relation.virtual_branch);
    output << "，"
           << (relation.effective
                   ? "已满足成局/成化条件"
                   : (relation.virtual_branch
                          ? "按小幅虚拱权重修正，不按实支满额计力"
                          : "仅记录结构，未直接改力量"))
           << "。\n";
  }
  for (const auto &relation : result.stem_relations) {
    output << "- 天干五合：" << stem_name(relation.first)
           << stem_name(relation.second) << "，" << relation.transform << "，"
           << (relation.effective ? "化气条件成立" : "化气条件不成立")
           << "；对相关候选按柱位折减 "
           << untransformed_combine_penalty(relation, result.config)
           << " 分。\n";
  }
  for (const auto &combo : result.ten_god_combos) {
    output << "- 十神组合：" << ZhouYi::Mapper::to_zh(combo.kind) << "（"
           << zh_severity(combo.severity) << "）：" << combo.note << "。\n";
  }
  output << "- 生克链：";
  for (const auto element : result.sheng_ke_chain.chain)
    output << element_name(element) << "→";
  output << element_name(result.sheng_ke_chain.chain.front()) << "。"
         << (result.sheng_ke_chain.smooth ? "五行流通顺畅。"
                                          : "五行流通受阻：");
  for (const auto &item : result.sheng_ke_chain.breaks)
    output << item << "；";
  output << "\n\n";

  output << "【算法口径：岁运先按具体天干与运支本气分十神，不以同五行异干冒充"
            "同一角色；运干再按通根、十二长生和盖头截脚修正承载。七杀先论攻"
            "身，再查食神制杀或印化杀，救应不抹去先伤。填实以原局旬空为参照"
            "且只标应期；冲提纲、冲日支、冲用神根分别处理；天干五合须辨合喜"
            "用、合忌仇、合调候及争合；地支六合须区分合势归向、合提纲和合绊"
            "用神根，不能见合即认化；三合、三会、刑、伏反吟和岁运并临在联"
            "合分析中另判。调候字兼具忌仇十神身份时分层计算：未见或不足才补"
            "调候分，已备则不重复补分，仍按格局身份评价。分数只作结构排序，不"
            "是吉凶概率。】\n";
  output << "## 大运/流年候选作用\n";
  if (result.fortune_impacts.empty())
    output << "- 未传入大运或流年柱，未生成岁运评分。\n";
  for (const auto &impact : result.fortune_impacts) {
    output << "- " << impact.pillar.to_string() << "：规则作用分 "
           << impact.score << "。";
    for (const auto &reason : impact.reasons)
      output << reason << "；";
    for (const auto &note : impact.review_notes)
      output << "【重点复核：" << note << "】";
    output << "\n";
  }
  if (!result.warnings.empty()) {
    output << "\n## 待复核提示\n";
    for (const auto &warning : result.warnings)
      output << "- " << warning << "。\n";
  }
  output << "\n说明：以上为规则化的传统命理分析证据，不是确定性预测；即使用神可"
            "定，仍须结合原局药力、合冲空亡及具体岁运复核其可用程度。\n";
  return output.str();
}

std::string Detail::render_summary(const AnalysisResult &result) {
  std::ostringstream output;
  output << "【八字分析（可解释候选版）】\n";
  output << "日主：" << stem_name(result.day_master) << "\n";
  output << "纳音：";
  for (const auto &item : result.nayin)
    output << item.pillar << item.name << ' ';
  output << "\n";
  output << "调候："
         << (result.climate.needed ? result.climate.reason
                                   : "未锁定单一调候五行")
         << "（" << ZhouYi::Mapper::to_zh(result.climate.state) << "，"
         << ZhouYi::Mapper::to_zh(result.climate.urgency) << "）\n";
  output << "格局候选：" << zh_text(result.pattern.name) << "（"
         << ZhouYi::Mapper::to_zh(result.pattern.status) << "）\n";
  output << "强弱：" << zh_text(result.strength.level) << "（" << std::fixed
         << std::setprecision(1) << result.strength.score << "/100）\n";
  if (result.strength.month_tomb) {
    output << "月令墓库："
           << (result.strength.tomb_opened
                   ? "已开（" + result.strength.tomb_opening_cause + "）"
                   : "未开")
           << "\n";
  }
  output << "承载能力：" << result.carrying_capacity.overall << "/100"
         << "（根气 " << result.carrying_capacity.root_stability << "，环境 "
         << result.carrying_capacity.climate_workability << "，流通 "
         << result.carrying_capacity.circulation << "）\n";
  output << "五行占比：";
  for (const auto &stat : result.element_balance) {
    output << element_name(stat.element) << ' ' << stat.percent << "%  ";
  }
  output << "\n候选状态：" << ZhouYi::Mapper::to_zh(result.useful_gods.status)
         << "\n";
  output << "取用方法：" << zh_text(result.useful_gods.method);
  if (!result.useful_gods.reason.empty())
    output << "（" << result.useful_gods.reason << "）";
  output << "\n";
  const std::size_t count =
      std::min<std::size_t>(3, result.useful_gods.candidates.size());
  for (std::size_t index = 0; index < count; ++index) {
    const auto &candidate = result.useful_gods.candidates[index];
    output << "  " << index + 1 << ". " << stem_name(candidate.stem) << "（"
           << element_name(candidate.element) << "）：理论 "
           << candidate.raw_score << "，有效 " << candidate.effective_score
           << "，候选占比 " << candidate.percent << "%";
    if (!candidate.present_in_chart)
      output << "【原局未见本干，仅为五行方向候选】";
    output << "\n";
  }
  const auto print_role = [&](std::string_view role,
                              const std::optional<ShenCandidate> &candidate) {
    if (candidate)
      output << role << "：" << stem_name(candidate->stem) << "（"
             << element_name(candidate->element) << "） ";
  };
  print_role("喜神", result.useful_gods.xi_shen);
  print_role("忌神", result.useful_gods.ji_shen);
  print_role("仇神", result.useful_gods.chou_shen);
  print_role("闲神", result.useful_gods.xian_shen);
  output << "\n";
  if (result.useful_gods.verification) {
    const auto &verification = *result.useful_gods.verification;
    output << "用神验证：" << verification.root_level << "，透干 "
           << (verification.tou_gan ? "是" : "否") << "，受冲 "
           << (verification.is_chonged ? "是" : "否") << "，合化 "
           << (verification.is_transformed ? "是" : "否") << "，效力 "
           << verification.power << "/100"
           << "（" << (verification.effective ? "通过" : "待复核") << "）\n";
  }
  if (!result.ten_god_combos.empty()) {
    output << "十神组合：";
    for (const auto &combo : result.ten_god_combos) {
      output << ZhouYi::Mapper::to_zh(combo.kind) << "（"
             << ZhouYi::Mapper::to_zh(combo.severity) << "） ";
    }
    output << "\n";
  }
  for (const auto &warning : result.useful_gods.warnings)
    output << "提示：" << warning << "\n";
  if (!result.sheng_ke_chain.smooth) {
    output << "生克链待复核：";
    for (const auto &item : result.sheng_ke_chain.breaks)
      output << item << "；";
    output << "\n";
  }
  return output.str();
}

nlohmann::json Detail::render_transit_json(const TransitAnalysis &result) {
  const auto channel_json = [](const TransitEffectChannel &channel) {
    nlohmann::json domains = nlohmann::json::array();
    for (const auto domain : channel.domains) {
      domains.push_back({{"code", enum_code(domain)}, {"zh", zh_text(domain)}});
    }
    return nlohmann::json{{"rule", channel.rule},
                          {"subject", channel.subject},
                          {"name", channel.name},
                          {"nature", enum_code(channel.nature)},
                          {"nature_zh", zh_text(channel.nature)},
                          {"score", channel.score},
                          {"domains", domains},
                          {"reason", channel.reason}};
  };
  nlohmann::json layers = nlohmann::json::array();
  for (const auto &impact : result.layer_impacts) {
    nlohmann::json evidence = nlohmann::json::array();
    for (const auto &item : impact.evidence)
      evidence.push_back(evidence_json(item));
    nlohmann::json channels = nlohmann::json::array();
    for (const auto &channel : impact.channels)
      channels.push_back(channel_json(channel));
    layers.push_back({{"pillar", impact.pillar.to_string()},
                      {"label", impact.label},
                      {"score", impact.score},
                      {"reasons", impact.reasons},
                      {"review_notes", impact.review_notes},
                      {"evidence", evidence},
                      {"channels", channels}});
  }
  nlohmann::json interactions = nlohmann::json::array();
  for (const auto &item : result.interactions) {
    interactions.push_back({{"rule", item.rule},
                            {"subject", item.subject},
                            {"name", item.name},
                            {"score", item.score},
                            {"reason", item.reason},
                            {"requires_review", item.requires_review},
                            {"state", enum_code(item.state)},
                            {"state_zh", zh_text(item.state)},
                            {"effectiveness", item.effectiveness}});
  }
  nlohmann::json channels = nlohmann::json::array();
  for (const auto &channel : result.channels)
    channels.push_back(channel_json(channel));
  nlohmann::json events = nlohmann::json::array();
  for (const auto &event : result.event_impacts) {
    events.push_back({{"type", enum_code(event.type)},
                      {"type_zh", zh_text(event.type)},
                      {"favorable_score", event.favorable_score},
                      {"adverse_score", event.adverse_score},
                      {"net_score", event.net_score},
                      {"rescue_offsets_adverse", event.rescue_offsets_adverse},
                      {"reasons", event.reasons}});
  }
  nlohmann::json finances = nlohmann::json::array();
  for (const auto &finance : result.finance_impacts) {
    finances.push_back({{"type", enum_code(finance.type)},
                        {"type_zh", zh_text(finance.type)},
                        {"favorable_score", finance.favorable_score},
                        {"adverse_score", finance.adverse_score},
                        {"reasons", finance.reasons}});
  }
  nlohmann::json spills = nlohmann::json::array();
  for (const auto &spill : result.source_spills) {
    nlohmann::json domains = nlohmann::json::array();
    for (const auto domain : spill.domains) {
      domains.push_back({{"code", enum_code(domain)}, {"zh", zh_text(domain)}});
    }
    spills.push_back({{"source", spill.source},
                      {"mechanism", spill.mechanism},
                      {"score", spill.score},
                      {"domains", domains},
                      {"reason", spill.reason}});
  }
  const auto pillar_json = [](const std::optional<Pillar> &pillar) {
    return pillar ? nlohmann::json(pillar->to_string()) : nlohmann::json{};
  };
  return {{"schema_version", result.schema_version},
          {"context",
           {{"da_yun", pillar_json(result.context.da_yun)},
            {"liu_nian", pillar_json(result.context.liu_nian)},
            {"liu_yue", pillar_json(result.context.liu_yue)},
            {"da_yun_start_year", result.context.da_yun_start_year},
            {"current_year", result.context.current_year}}},
          {"layer_impacts", layers},
          {"interactions", interactions},
          {"effect_channels", channels},
          {"event_impacts", events},
          {"finance_impacts", finances},
          {"source_spills", spills},
          {"projected_strength", result.projected_strength},
          {"requires_reselection", result.requires_reselection},
          {"total_score", result.total_score},
          {"risk", enum_code(result.risk)},
          {"risk_zh", zh_text(result.risk)},
          {"sample_peak_type",
           result.sample_peak_type
               ? nlohmann::json(enum_code(*result.sample_peak_type))
               : nlohmann::json{}},
          {"sample_peak_type_zh",
           result.sample_peak_type
               ? nlohmann::json(zh_text(*result.sample_peak_type))
               : nlohmann::json{}},
          {"sample_peak_score", result.sample_peak_score},
          {"review_notes", result.review_notes}};
}

std::string Detail::render_transit_zh(const TransitAnalysis &result) {
  std::ostringstream output;
  output << "# 岁运联合分析\n\n";
  output << "- 数据契约版本：" << result.schema_version << "。\n";
  output << "【算法口径：大运定基调、流年定应期、流月作短期触发；天干与地支"
            "本中余气分层评价，同一干支允许吉凶双通道并存；六合、半合须按具体"
            "十神并处理合冲竞见。总分仅表示结构合计，身体、财务、事业、规则、"
            "关系、迁动另行分项，不以总分互相替代；财务再按收入受阻、支出债务、"
            "收入机会分型，同一结构来源经多条规则进入两个以上类型时标记一源多"
            "溢，并允许多个来源并列。】\n";
  output << "- 大运："
         << (result.context.da_yun ? result.context.da_yun->to_string()
                                   : "未传入")
         << "；流年："
         << (result.context.liu_nian ? result.context.liu_nian->to_string()
                                     : "未传入")
         << "；流月："
         << (result.context.liu_yue ? result.context.liu_yue->to_string()
                                    : "未传入")
         << "。\n";
  output << "- 综合提示：" << zh_text(result.risk)
         << "；兼容结构净分（不可跨应事类型比较） " << result.total_score
         << "；岁运后强弱估计 " << result.projected_strength << "/100。\n";
  if (result.sample_peak_type) {
    output << "- 单线极值：" << zh_text(*result.sample_peak_type) << " "
           << result.sample_peak_score << "（本批回归样本最深）。\n";
  }
  if (result.requires_reselection)
    output << "- 强弱跨越阈值：必须重新取用复核，不得机械沿用原局喜忌。\n";
  if (result.context.da_yun_start_year && result.context.current_year &&
      *result.context.da_yun_start_year == *result.context.current_year) {
    output << "- 交运：" << *result.context.current_year
           << "年为当前大运首年，大运层既有作用按1.15系数放大。\n";
  }
  output << "\n## 分层作用\n";
  for (const auto &impact : result.layer_impacts) {
    output << "- " << impact.label << impact.pillar.to_string() << "："
           << impact.score << "分。";
    for (const auto &reason : impact.reasons)
      output << reason << "；";
    output << "\n";
  }
  output << "\n## 应事类型分项\n";
  for (const auto &event : result.event_impacts) {
    output << "- " << zh_text(event.type) << "：";
    if (!event.rescue_offsets_adverse) {
      output << "原始压力 " << event.adverse_score << "，救应 "
             << event.favorable_score
             << "（伤与救分列，救应说明转机或缓解，不抵销先伤）。\n";
    } else {
      output << "有利通道 " << event.favorable_score << "，不利通道 "
             << event.adverse_score << "，参考净值 " << event.net_score
             << "。\n";
    }
  }
  output << "\n## 财务机制分项\n";
  for (const auto &finance : result.finance_impacts) {
    output << "- " << zh_text(finance.type) << "：有利通道 "
           << finance.favorable_score << "，不利通道 " << finance.adverse_score
           << "。\n";
  }
  output << "\n## 一源多溢\n";
  if (result.source_spills.empty())
    output << "- 未见同一结构来源进入两个以上应事类型。\n";
  for (const auto &spill : result.source_spills) {
    output << "- " << spill.mechanism << "（" << spill.source << "）：";
    for (std::size_t index = 0; index < spill.domains.size(); ++index) {
      if (index > 0)
        output << "、";
      output << zh_text(spill.domains[index]);
    }
    output << "；" << spill.reason << "。\n";
  }
  output << "\n## 吉凶并行通道\n";
  for (const auto &channel : result.channels) {
    output << "- 【" << zh_text(channel.nature) << "】" << channel.name << "（"
           << channel.subject << "）：" << (channel.score >= 0.0 ? "+" : "")
           << channel.score << "；" << channel.reason << "。\n";
  }
  output << "\n## 组合级作用\n";
  if (result.interactions.empty())
    output << "- 未检测到层间或岁运与原局的重大组合。\n";
  for (const auto &item : result.interactions) {
    output << "- " << item.name << "（" << item.subject << "）："
           << (item.score >= 0.0 ? "+" : "") << item.score << "分；"
           << item.reason;
    if (item.rule == "transit.branch_six_combine" ||
        item.rule == "transit.branch_half_combine" ||
        item.rule == "transit.branch_combine_useful_root")
      output << "；成势状态：" << zh_text(item.state) << "，有效系数 "
             << item.effectiveness;
    output << "。\n";
  }
  if (!result.review_notes.empty()) {
    output << "\n## 重点复核\n";
    for (const auto &note : result.review_notes)
      output << "- " << note << "。\n";
  }
  output << "\n说明：风险等级仅表示传统规则的结构叠加程度，不是现实事件的确定性"
            "预测。\n";
  return output.str();
}

} // namespace ZhouYi::BaZiAnalysis
