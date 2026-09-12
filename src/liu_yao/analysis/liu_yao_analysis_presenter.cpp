// 六爻分析展示实现
module ZhouYi.LiuYaoAnalysis.Presenter;

import ZhouYi.ZhMapper;

namespace ZhouYi::LiuYaoAnalysis {
namespace {

std::string_view zh(auto value) { return ZhouYi::Mapper::to_zh(value); }

nlohmann::json evidence_json(const GuaYaoBasis &gua_yao_basis) {
  return {{"规则", gua_yao_basis.rule},
          {"断法", gua_yao_basis.detail},
          {"作用", zh(gua_yao_basis.nature)},
          {"爻位", gua_yao_basis.positions}};
}

} // namespace

nlohmann::json to_zh_json(const AnalysisResult &result) {
  nlohmann::json output;
  output["契约版本"] = result.schema_version;
  output["占问"] = {{"门类", zh(result.request.question_kind)},
                    {"原文", result.request.question}};
  output["卦象"] = {{"本卦", result.main_hexagram},
                    {"变卦", result.changed_hexagram.value_or("无变卦")}};
  output["用神"] = {{"取用", result.yong_shen.target},
                    {"状态", zh(result.yong_shen.state)},
                    {"爻位", result.yong_shen.position.value_or(0)},
                    {"地支", result.yong_shen.branch},
                    {"气势", result.yong_shen.strength
                                 ? std::string(zh(*result.yong_shen.strength))
                                 : std::string("未定")},
                    {"人工指定", result.yong_shen.manually_specified},
                    {"依据", result.yong_shen.reason}};

  output["逐爻审察"] = nlohmann::json::array();
  for (const auto &line : result.yao) {
    nlohmann::json gua_yao_basis = nlohmann::json::array();
    for (const auto &item : line.gua_yao_basis)
      gua_yao_basis.push_back(evidence_json(item));
    output["逐爻审察"].push_back({{"爻位", line.position},
                                  {"六亲", line.relative},
                                  {"地支", line.branch},
                                  {"气势", zh(line.strength)},
                                  {"世爻", line.is_shi},
                                  {"应爻", line.is_ying},
                                  {"动爻", line.is_changing},
                                  {"旬空", line.is_xun_kong},
                                  {"月破", line.is_month_broken},
                                  {"日破", line.is_day_broken},
                                  {"动化", line.transformation},
                                  {"依据", std::move(gua_yao_basis)}});
  }

  output["作用关系"] = nlohmann::json::array();
  for (const auto &relation : result.relations)
    output["作用关系"].push_back(evidence_json(relation));
  output["断卦"] = {{"趋向", zh(result.judgment)},
                    {"断语", result.conclusion},
                    {"复核", result.review_points}};
  return output;
}

void write_zh(std::ostream &output, const AnalysisResult &result) {
  output << "==================== 六爻分析 ====================\n\n";
  output << "【占问】\n";
  output << "门类：" << zh(result.request.question_kind) << '\n';
  if (!result.request.question.empty())
    output << "所问：" << result.request.question << '\n';

  output << "\n【卦象】\n";
  output << "本卦：" << result.main_hexagram << '\n';
  output << "变卦：" << result.changed_hexagram.value_or("无变卦") << '\n';

  output << "\n【取用】\n";
  output << "用神：" << result.yong_shen.target << '\n';
  output << "状态：" << zh(result.yong_shen.state) << '\n';
  if (result.yong_shen.position)
    output << "落爻：第" << *result.yong_shen.position << "爻\n";
  if (!result.yong_shen.branch.empty())
    output << "地支：" << result.yong_shen.branch << '\n';
  if (result.yong_shen.strength)
    output << "气势：" << zh(*result.yong_shen.strength) << '\n';
  output << "依据：" << result.yong_shen.reason << '\n';

  output << "\n【六爻旺衰与动变】\n";
  for (auto it = result.yao.rbegin(); it != result.yao.rend(); ++it) {
    output << "第" << it->position << "爻 " << it->relative << it->branch;
    if (it->is_shi)
      output << " 世";
    if (it->is_ying)
      output << " 应";
    if (it->is_changing)
      output << " 动";
    output << "：" << zh(it->strength);
    if (it->is_xun_kong)
      output << "、旬空";
    if (it->is_month_broken)
      output << "、月破";
    if (it->is_day_broken)
      output << "、日破";
    if (!it->transformation.empty())
      output << "、" << it->transformation;
    output << '\n';
    for (const auto &gua_yao_basis : it->gua_yao_basis)
      output << "  - " << gua_yao_basis.rule << "：" << gua_yao_basis.detail
             << '\n';
  }

  output << "\n【日月、爻际、动变与世用关系】\n";
  if (result.relations.empty()) {
    output << "未见显著作用。\n";
  } else {
    for (const auto &relation : result.relations)
      output << "- " << relation.rule << "【" << zh(relation.nature) << "】："
             << relation.detail << '\n';
  }

  output << "\n【断卦】\n";
  output << "趋向：" << zh(result.judgment) << '\n';
  output << "断语：" << result.conclusion << '\n';
  if (!result.review_points.empty()) {
    output << "复核：\n";
    for (const auto &point : result.review_points)
      output << "- " << point << '\n';
  }
}

} // namespace ZhouYi::LiuYaoAnalysis
