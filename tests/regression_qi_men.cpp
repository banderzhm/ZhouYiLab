/**
 * @file example_qi_men.cpp
 * @brief 奇门排盘、格局闭环与外部参考实现多案例回归。
 */
import ZhouYi.GanZhi;
import ZhouYi.QiMen;
import ZhouYi.QiMen.Analysis;
import ZhouYi.QiMen.Analysis.Presenter;
import ZhouYi.QiMen.Controller;
import ZhouYi.QiMen.Pan;
import ZhouYi.ZhMapper;
import nlohmann.json;
import fmt;
import std;

namespace {
using namespace ZhouYi::QiMen;
using ZhouYi::GanZhi::DiZhi;
using ZhouYi::GanZhi::TianGan;

/** 外部参考实现导出的端到端盘局摘要。 */
struct ReferenceCase {
  int month;
  int day;
  int hour;
  int minute;
  std::string_view term;
  std::string_view dun;
  int ju;
  std::string_view yuan;
  std::string_view xun;
  std::string_view zhi_fu;
  int zhi_fu_palace;
  std::string_view zhi_shi;
  int zhi_shi_palace;
};

/** 格局规则在真实历法案例中的首个命中记录。 */
struct PatternCoverage {
  std::size_t count{};      ///< 命中次数。
  std::string first_case;   ///< 首次命中时刻。
  std::string palace;       ///< 首次命中宫位。
  std::string name;         ///< 专业格局名。
  std::string pan_ju_basis; ///< 完整成格依据。
};

constexpr std::array<ReferenceCase, 24> kReferenceCases{
    {{1, 8, 1, 30, "小寒", "阳遁", 8, "中元", "甲申旬", "天蓬", 9, "休门", 6},
     {1, 23, 14, 30, "大寒", "阳遁", 9, "中元", "甲午旬", "天冲", 8, "伤门", 4},
     {2, 8, 1, 30, "立春", "阳遁", 5, "中元", "甲午旬", "天任", 8, "生门", 6},
     {2, 23, 14, 30, "雨水", "阳遁", 6, "中元", "甲辰旬", "天蓬", 3, "休门", 4},
     {3, 8, 1, 30, "惊蛰", "阳遁", 7, "中元", "甲申旬", "天英", 8, "景门", 8},
     {3, 23, 14, 30, "春分", "阳遁", 9, "中元", "甲午旬", "天冲", 8, "伤门", 4},
     {4, 8, 1, 30, "清明", "阳遁", 1, "中元", "甲午旬", "天辅", 4, "杜门", 2},
     {4, 23, 14, 30, "谷雨", "阳遁", 2, "中元", "甲辰旬", "天心", 8, "开门", 9},
     {5, 8, 1, 30, "立夏", "阳遁", 1, "中元", "甲午旬", "天辅", 4, "杜门", 2},
     {5, 23, 14, 30, "小满", "阳遁", 2, "中元", "甲辰旬", "天心", 8, "开门", 9},
     {6, 8, 1, 30, "芒种", "阳遁", 3, "中元", "甲辰旬", "天柱", 8, "惊门", 7},
     {6, 23, 14, 30, "夏至", "阴遁", 3, "中元", "甲寅旬", "天柱", 2, "惊门", 2},
     {7, 8, 1, 30, "小暑", "阴遁", 2, "中元", "甲辰旬", "天柱", 6, "惊门", 7},
     {7, 23, 14, 30, "大暑", "阴遁", 1, "中元", "甲寅旬", "天禽", 9, "死门", 6},
     {8, 8, 1, 30, "立秋", "阴遁", 8, "下元", "甲子旬", "天任", 9, "生门", 7},
     {8, 23, 14, 30, "处暑", "阴遁", 7, "下元", "甲子旬", "天柱", 4, "惊门", 9},
     {9, 8, 1, 30, "白露", "阴遁", 6, "下元", "甲戌旬", "天禽", 9, "死门", 8},
     {9, 23, 14, 30, "秋分", "阴遁", 4, "下元", "甲戌旬", "天冲", 8, "伤门", 3},
     {10, 8, 1, 30, "秋分", "阴遁", 4, "下元", "甲戌旬", "天冲", 7, "伤门", 9},
     {10, 23, 14, 30, "霜降", "阴遁", 2, "下元", "甲戌旬", "天蓬", 6, "休门",
      1},
     {11, 8, 1, 30, "立冬", "阴遁", 3, "下元", "甲申旬", "天蓬", 2, "休门", 2},
     {11, 23, 14, 30, "小雪", "阴遁", 2, "下元", "甲午旬", "天任", 3, "生门",
      7},
     {12, 8, 1, 30, "大雪", "阴遁", 1, "下元", "甲申旬", "天任", 9, "生门", 3},
     {12, 23, 14, 30, "冬至", "阳遁", 4, "下元", "甲午旬", "天柱", 3, "惊门",
      8}}};

bool matches(const QiMenPan &pan, const ReferenceCase &expected) {
  return solar_term_name(pan.solar_term) == expected.term &&
         (pan.dun == Dun::Yang ? "阳遁" : "阴遁") == expected.dun &&
         pan.ju == expected.ju && yuan_name(pan.yuan) == expected.yuan &&
         jia_xun_name(pan.xun_shou) == expected.xun &&
         star_name(pan.zhi_fu_star) == expected.zhi_fu &&
         get_number_from_palace(pan.zhi_fu_palace) == expected.zhi_fu_palace &&
         gate_name(pan.zhi_shi_gate) == expected.zhi_shi &&
         get_number_from_palace(pan.zhi_shi_palace) == expected.zhi_shi_palace;
}

std::string run_reference_regression() {
  std::string report = "【24节气跨盘回归】\n";
  report +=
      "参考：3meta 2.6.0，固定版本 9be1238cbb7b0118826a689f9d3f8100284f6df3\n";
  report += "| 序 | 时刻 | 遁局 | 三元 | 旬首 | 值符 | 值使 | 结果 "
            "|\n|---:|---|---|---|---|---|---|---|\n";
  int passed = 0;
  std::map<std::string, PatternCoverage> coverage;
  for (std::size_t i = 0; i < kReferenceCases.size(); ++i) {
    const auto &expected = kReferenceCases[i];
    const auto result = QiMenController::pai_pan_solar(
        2024, expected.month, expected.day, expected.hour, expected.minute);
    const bool ok = result && matches(*result, expected);
    passed += ok;
    if (result) {
      const std::string case_name =
          fmt::format("2024-{:02}-{:02} {:02}:{:02}", expected.month,
                      expected.day, expected.hour, expected.minute);
      const auto record = [&](const PatternFinding &finding,
                              std::string_view palace) {
        if (finding.rule_id.empty() || finding.name.empty() ||
            finding.pan_ju_basis.empty())
          throw std::runtime_error("格局记录缺少规则号、专业名称或成格依据");
        auto [it, inserted] = coverage.try_emplace(
            finding.rule_id,
            PatternCoverage{0, case_name, std::string(palace), finding.name,
                            finding.pan_ju_basis});
        ++it->second.count;
      };
      for (const auto &finding : result->global_patterns)
        record(finding, "全局");
      for (const auto &palace : result->palaces) {
        std::set<std::string> palace_rules;
        for (const auto &finding : palace.patterns) {
          if (!palace_rules.insert(finding.rule_id).second)
            throw std::runtime_error(
                fmt::format("{} {}重复输出格局规则{}", case_name,
                            palace_name(palace.palace), finding.rule_id));
          record(finding, palace_name(palace.palace));
        }
      }
      report += fmt::format(
          "| {} | 2024-{:02}-{:02} {:02}:{:02} | {}{}局 | {} | {} | {}落{} | "
          "{}落{} | {} |\n",
          i + 1, expected.month, expected.day, expected.hour, expected.minute,
          result->dun == Dun::Yang ? "阳遁" : "阴遁", result->ju,
          yuan_name(result->yuan), jia_xun_name(result->xun_shou),
          star_name(result->zhi_fu_star),
          get_number_from_palace(result->zhi_fu_palace),
          gate_name(result->zhi_shi_gate),
          get_number_from_palace(result->zhi_shi_palace),
          ok ? "通过" : "不一致");
    } else {
      report += fmt::format("| {} | 2024-{:02}-{:02} {:02}:{:02} | — | — | — | "
                            "— | — | 失败：{} |\n",
                            i + 1, expected.month, expected.day, expected.hour,
                            expected.minute, result.error());
    }
  }
  report += fmt::format("\n通过：{}/{}。\n", passed, kReferenceCases.size());
  if (passed != static_cast<int>(kReferenceCases.size()))
    throw std::runtime_error(
        fmt::format("参考盘回归仅通过 {}/{}", passed, kReferenceCases.size()));
  report += "\n【格局闭环覆盖】\n";
  report += "闭环条件：规则号、专业格局名、宫位、成格依据四项齐全；同宫同规则不"
            "得重复。\n";
  report += "| 规则号 | 格局 | 命中数 | 首个真实案例 | 宫位 | 成格依据 |\n";
  report += "|---|---|---:|---|---|---|\n";
  for (const auto &[rule_id, item] : coverage)
    report += fmt::format("| {} | {} | {} | {} | {} | {} |\n", rule_id,
                          item.name, item.count, item.first_case, item.palace,
                          item.pan_ju_basis);
  report +=
      fmt::format("\n24 个端到端盘共覆盖 {} 种格局规则。\n", coverage.size());
  if (coverage.size() < 30)
    throw std::runtime_error(
        fmt::format("格局覆盖不足：仅命中 {} 种规则", coverage.size()));
  return report;
}

std::string run_fixed_palace_regression() {
  auto result = QiMenPanGenerator::generate_pan(
      SolarTerm::ShuangJiang, TianGan::Wu, DiZhi::Shen, TianGan::Wu, DiZhi::Wu);
  if (!result)
    throw std::runtime_error(result.error());
  auto &pan = *result;
  analyze_qi_men_pan(pan, {.year_gan = TianGan::Wu,
                           .month_gan = TianGan::Ren,
                           .month_zhi = DiZhi::Xu,
                           .day_gan = TianGan::Wu,
                           .day_zhi = DiZhi::Shen,
                           .hour_gan = TianGan::Wu,
                           .hour_zhi = DiZhi::Wu});
  const std::array<TianGan, 9> earth{
      TianGan::Ji,  TianGan::Wu,  TianGan::Yi,  TianGan::Bing, TianGan::Ding,
      TianGan::Gui, TianGan::Ren, TianGan::Xin, TianGan::Geng};
  const std::array<TianGan, 9> heaven{TianGan::Yi, TianGan::Gui,  TianGan::Geng,
                                      TianGan::Wu, TianGan::Ding, TianGan::Xin,
                                      TianGan::Ji, TianGan::Bing, TianGan::Ren};
  const std::array<Star, 9> stars{
      Star::TianChong, Star::TianXin, Star::TianYing,
      Star::TianRui,   Star::TianQin, Star::TianRen,
      Star::TianPeng,  Star::TianFu,  Star::TianZhu};
  const std::array<Gate, 9> gates{Gate::Shang, Gate::Kai,  Gate::Jing,
                                  Gate::Si,    Gate::None, Gate::Sheng,
                                  Gate::Xiu,   Gate::Du,   Gate::JingGate};
  for (std::size_t i = 0; i < 9; ++i)
    if (pan.palaces[i].di_gan != earth[i] ||
        pan.palaces[i].tian_gan != heaven[i] ||
        pan.palaces[i].star != stars[i] || pan.palaces[i].gate != gates[i])
      throw std::runtime_error(
          fmt::format("阴遁二局固定盘第{}宫不一致", i + 1));
  if (pan.zhi_fu_star != Star::TianXin ||
      pan.zhi_fu_palace != Palace::SouthWest || pan.zhi_shi_gate != Gate::Kai ||
      pan.zhi_shi_palace != Palace::SouthWest)
    throw std::runtime_error("阴遁二局值符值使不一致");
  std::string report = "\n【固定九宫逐宫回归】\n";
  report +=
      "案例：2008-11-04 12:30，强制霜降、阴遁二局；日柱戊申，时柱戊午。\n";
  report +=
      "结果：九宫地盘、天盘、九星、八门以及值符值使全部与参考实现一致。\n\n";
  report += format_qi_men_pan(pan) + "\n" + format_qi_men_analysis(pan);
  return report;
}

std::string run_detailed_analysis_examples() {
  const auto generated = QiMenController::pai_pan_solar(2000, 7, 16, 16, 30);
  if (!generated)
    throw std::runtime_error(generated.error());

  struct ExampleQuestion {
    ZhouYi::QiMenAnalysis::QuestionKind kind;
    std::string_view question;
  };
  constexpr std::array<ExampleQuestion, 9> questions{{
      {ZhouYi::QiMenAnalysis::QuestionKind::FanZhan, "察此时盘局总势"},
      {ZhouYi::QiMenAnalysis::QuestionKind::GongMing, "所谋事业门径如何"},
      {ZhouYi::QiMenAnalysis::QuestionKind::QiuCai, "所谋财源能否推进"},
      {ZhouYi::QiMenAnalysis::QuestionKind::HunLian, "所问关系如何发展"},
      {ZhouYi::QiMenAnalysis::QuestionKind::JiBing, "所问病神与医药救应"},
      {ZhouYi::QiMenAnalysis::QuestionKind::ChuXing, "所问出行道路与动静"},
      {ZhouYi::QiMenAnalysis::QuestionKind::GuanSong, "所问争议能否化解"},
      {ZhouYi::QiMenAnalysis::QuestionKind::XueYe, "所问学业考试门径"},
      {ZhouYi::QiMenAnalysis::QuestionKind::XunWu, "所问失物隐匿与寻获"},
  }};

  std::ostringstream output;
  output << "# 奇门遁甲分门占断多案例\n\n";
  for (std::size_t index = 0; index < questions.size(); ++index) {
    const auto &question = questions[index];
    const ZhouYi::QiMenAnalysis::AnalysisRequest request{
        .question_kind = question.kind,
        .question = std::string(question.question),
        .nian_ming = TianGan::Geng};
    const auto analysis = ZhouYi::QiMenAnalysis::analyze(*generated, request);
    if (analysis.yong_shen.empty() || analysis.palace_readings.empty() ||
        analysis.conclusion.empty() || analysis.manifestations.empty() ||
        analysis.timing.empty())
      throw std::runtime_error("奇门分门占断未形成完整用神、宫位或断语");
    for (std::size_t timing_index = 0; timing_index < analysis.timing.size();
         ++timing_index)
      if (analysis.timing[timing_index].priority !=
          static_cast<int>(timing_index + 1))
        throw std::runtime_error("奇门应期候选次序不连续");
    output << "\n## 案例 " << index + 1 << "\n\n";
    ZhouYi::QiMenAnalysis::write_zh(output, *generated, analysis);
  }
  return output.str();
}
/** @brief 逐值比较枚举中文映射，返回不一致描述；全部一致时为空串。 */
template <typename E, std::size_t N>
std::string zh_map_diff(const std::array<E, N> &values,
                        const std::array<std::string_view, N> &expected) {
  std::string diff;
  for (std::size_t index = 0; index < N; ++index) {
    const std::string_view actual = ZhouYi::Mapper::to_zh(values[index]);
    if (actual.empty() || actual != expected[index]) {
      if (!diff.empty())
        diff += "；";
      diff += fmt::format("第{}项 期望「{}」实际「{}」", index, expected[index],
                          actual);
    }
  }
  return diff;
}

/** @brief 文本是否包含指定中文串。 */
bool text_has(const std::string &text, std::string_view value) {
  return text.find(value) != std::string::npos;
}

/**
 * @brief 奇门七个枚举的中文映射逐值核对与 presenter 同源核对
 *
 * 一、逐值：每个取值都必须映射到约定的中文，空串或回退英文都算不一致。
 * 二、同源：用探针结果渲染 presenter 报告与中文 JSON，断言其中出现的中文与
 *     映射表逐字相同，presenter 不再自带一份中文表。
 */
std::string run_enum_zh_map_contract() {
  std::string report = "\n【枚举中文映射与 presenter 逐值核对】\n";
  report +=
      "映射来源：ZhouYi.ZhMapper 的 ZhMap 特化；presenter 直接调用 to_zh。\n\n";

  const std::array<Dun, 2> duns{Dun::Yang, Dun::Yin};
  const std::array<std::string_view, 2> dun_names{"阳遁", "阴遁"};
  const std::array<PatternNature, 3> nature_values{PatternNature::Auspicious,
                                                   PatternNature::Inauspicious,
                                                   PatternNature::Neutral};
  const std::array<std::string_view, 3> nature_names{"吉", "凶", "中"};
  const std::array<ZhouYi::QiMenAnalysis::QuestionKind, 9> question_values{
      ZhouYi::QiMenAnalysis::QuestionKind::FanZhan,
      ZhouYi::QiMenAnalysis::QuestionKind::GongMing,
      ZhouYi::QiMenAnalysis::QuestionKind::QiuCai,
      ZhouYi::QiMenAnalysis::QuestionKind::HunLian,
      ZhouYi::QiMenAnalysis::QuestionKind::JiBing,
      ZhouYi::QiMenAnalysis::QuestionKind::ChuXing,
      ZhouYi::QiMenAnalysis::QuestionKind::GuanSong,
      ZhouYi::QiMenAnalysis::QuestionKind::XueYe,
      ZhouYi::QiMenAnalysis::QuestionKind::XunWu};
  const std::array<std::string_view, 9> question_names{
      "泛占",     "功名事业", "求财经营", "婚恋关系", "疾病医药",
      "出行迁动", "官讼争议", "学业考试", "寻人失物"};
  const std::array<ZhouYi::QiMenAnalysis::YongShenRole, 9> role_values{
      ZhouYi::QiMenAnalysis::YongShenRole::RiGan,
      ZhouYi::QiMenAnalysis::YongShenRole::ShiGan,
      ZhouYi::QiMenAnalysis::YongShenRole::NianMing,
      ZhouYi::QiMenAnalysis::YongShenRole::ZhiFu,
      ZhouYi::QiMenAnalysis::YongShenRole::ZhiShi,
      ZhouYi::QiMenAnalysis::YongShenRole::ZhuanYongMen,
      ZhouYi::QiMenAnalysis::YongShenRole::ZhuanYongXing,
      ZhouYi::QiMenAnalysis::YongShenRole::ZhuanYongShen,
      ZhouYi::QiMenAnalysis::YongShenRole::ZhuanYongGan};
  const std::array<std::string_view, 9> role_names{
      "日干",   "时干",   "年命",   "值符",    "值使",
      "专用门", "专用星", "专用神", "专用奇仪"};
  const std::array<ZhouYi::QiMenAnalysis::PalaceRelation, 6> relation_values{
      ZhouYi::QiMenAnalysis::PalaceRelation::TongGong,
      ZhouYi::QiMenAnalysis::PalaceRelation::BiHe,
      ZhouYi::QiMenAnalysis::PalaceRelation::ZhuShengKe,
      ZhouYi::QiMenAnalysis::PalaceRelation::KeShengZhu,
      ZhouYi::QiMenAnalysis::PalaceRelation::ZhuKeKe,
      ZhouYi::QiMenAnalysis::PalaceRelation::KeKeZhu};
  const std::array<std::string_view, 6> relation_names{
      "同宫", "比和", "主生客", "客生主", "主克客", "客克主"};
  const std::array<ZhouYi::QiMenAnalysis::EffectNature, 4> effect_values{
      ZhouYi::QiMenAnalysis::EffectNature::ZhuLi,
      ZhouYi::QiMenAnalysis::EffectNature::ZhiAi,
      ZhouYi::QiMenAnalysis::EffectNature::YinDong,
      ZhouYi::QiMenAnalysis::EffectNature::DaiBian};
  const std::array<std::string_view, 4> effect_names{"助力", "制碍", "引动",
                                                     "待辨"};
  const std::array<ZhouYi::QiMenAnalysis::Judgment, 5> judgment_values{
      ZhouYi::QiMenAnalysis::Judgment::DeShi,
      ZhouYi::QiMenAnalysis::Judgment::YouLi,
      ZhouYi::QiMenAnalysis::Judgment::JiXiongBingJian,
      ZhouYi::QiMenAnalysis::Judgment::ShouZu,
      ZhouYi::QiMenAnalysis::Judgment::DaiDing};
  const std::array<std::string_view, 5> judgment_names{
      "得势", "有利", "吉凶并见", "受阻", "待定"};

  std::string diff;
  const auto merge = [&diff](const std::string &part) {
    if (!part.empty())
      diff += (diff.empty() ? "" : "；") + part;
  };
  merge(zh_map_diff(duns, dun_names));
  merge(zh_map_diff(nature_values, nature_names));
  merge(zh_map_diff(question_values, question_names));
  merge(zh_map_diff(role_values, role_names));
  merge(zh_map_diff(relation_values, relation_names));
  merge(zh_map_diff(effect_values, effect_names));
  merge(zh_map_diff(judgment_values, judgment_names));
  report +=
      fmt::format("逐值映射：{} 个取值。\n",
                  duns.size() + nature_values.size() + question_values.size() +
                      role_values.size() + relation_values.size() +
                      effect_values.size() + judgment_values.size());

  // presenter 同源核对：用探针结果逐个取值渲染，断言中文原样出现。
  const auto generated = QiMenController::pai_pan_solar(2024, 6, 21, 12, 30);
  if (!generated)
    throw std::runtime_error(generated.error());
  const QiMenPan probe_pan = *generated;
  std::size_t probe_checks = 0;
  const auto render = [](const QiMenPan &pan,
                         const ZhouYi::QiMenAnalysis::AnalysisResult &result) {
    std::ostringstream output;
    ZhouYi::QiMenAnalysis::write_zh(output, pan, result);
    return output.str();
  };

  for (std::size_t index = 0; index < duns.size(); ++index) {
    QiMenPan pan = probe_pan;
    pan.dun = duns[index];
    ++probe_checks;
    if (!text_has(render(pan, {}), "遁局：" + std::string(dun_names[index])))
      merge(fmt::format("阴阳遁「{}」未出现在遁局行", dun_names[index]));
    const nlohmann::json pan_json = pan;
    if (pan_json.at("dun").get<std::string>() != dun_names[index])
      merge(
          fmt::format("阴阳遁「{}」与 JSON dun 字段不一致", dun_names[index]));
  }

  for (std::size_t index = 0; index < nature_values.size(); ++index) {
    QiMenPan pan = probe_pan;
    pan.global_patterns.push_back(PatternFinding{.rule_id = "probe.nature",
                                                 .name = "探针格局",
                                                 .nature = nature_values[index],
                                                 .palace = Palace::Center,
                                                 .pan_ju_basis = "探针依据"});
    ++probe_checks;
    const std::string expected =
        "[" + std::string(nature_names[index]) + "] 探针格局";
    if (!text_has(format_qi_men_analysis(pan), expected))
      merge(fmt::format("格局吉凶「{}」未出现在格局闭环", nature_names[index]));
  }

  for (std::size_t index = 0; index < question_values.size(); ++index) {
    ZhouYi::QiMenAnalysis::AnalysisResult result;
    result.request.question_kind = question_values[index];
    result.request.question = "探针占问";
    ++probe_checks;
    if (!text_has(render(probe_pan, result),
                  "占类：" + std::string(question_names[index])))
      merge(fmt::format("占问门类「{}」未出现在报告", question_names[index]));
    const nlohmann::json zh_json =
        ZhouYi::QiMenAnalysis::to_zh_json(probe_pan, result);
    if (zh_json.at("占问").at("门类").get<std::string>() !=
        question_names[index])
      merge(fmt::format("占问门类「{}」与中文 JSON 不一致",
                        question_names[index]));
  }

  for (std::size_t index = 0; index < role_values.size(); ++index) {
    ZhouYi::QiMenAnalysis::AnalysisResult result;
    result.yong_shen.push_back(
        ZhouYi::QiMenAnalysis::YongShenSelection{.role = role_values[index],
                                                 .name = "探针用神",
                                                 .palace = Palace::Center,
                                                 .basis = "探针取用"});
    ++probe_checks;
    const std::string expected =
        "| " + std::string(role_names[index]) + " | 探针用神 |";
    if (!text_has(render(probe_pan, result), expected))
      merge(fmt::format("用神职责「{}」未出现在用神表", role_names[index]));
  }

  for (std::size_t index = 0; index < relation_values.size(); ++index) {
    ZhouYi::QiMenAnalysis::AnalysisResult result;
    result.main_guest.relation = relation_values[index];
    result.main_guest.interpretation = "探针断意";
    ++probe_checks;
    if (!text_has(render(probe_pan, result),
                  "主客：" + std::string(relation_names[index]) + "。"))
      merge(fmt::format("主客生克「{}」未出现在日时主客行",
                        relation_names[index]));
  }

  for (std::size_t index = 0; index < effect_values.size(); ++index) {
    ZhouYi::QiMenAnalysis::AnalysisResult result;
    result.favorable.push_back(
        ZhouYi::QiMenAnalysis::PanJuBasis{.rule = "探针规则",
                                          .detail = "探针依据",
                                          .nature = effect_values[index],
                                          .palace = Palace::Center});
    ++probe_checks;
    const std::string expected =
        "【" + std::string(effect_names[index]) + "】：探针依据";
    if (!text_has(render(probe_pan, result), expected))
      merge(fmt::format("占断作用「{}」未出现在依据行", effect_names[index]));
  }

  for (std::size_t index = 0; index < judgment_values.size(); ++index) {
    ZhouYi::QiMenAnalysis::AnalysisResult result;
    result.judgment = judgment_values[index];
    result.conclusion = "探针断语";
    ++probe_checks;
    if (!text_has(render(probe_pan, result),
                  "盘势：" + std::string(judgment_names[index])))
      merge(fmt::format("综合盘势「{}」未出现在总断", judgment_names[index]));
  }

  report += fmt::format("presenter 同源核对：{} 项。\n", probe_checks);
  report += "| 枚举 | 取值数 | 结论 |\n| --- | --- | --- |\n";
  const std::array<std::pair<std::string_view, std::size_t>, 7> summary{
      {{"阴阳遁", duns.size()},
       {"格局吉凶", nature_values.size()},
       {"占问门类", question_values.size()},
       {"用神职责", role_values.size()},
       {"主客生克", relation_values.size()},
       {"占断作用", effect_values.size()},
       {"综合盘势", judgment_values.size()}}};
  const std::array<bool, 7> summary_ok{
      zh_map_diff(duns, dun_names).empty(),
      zh_map_diff(nature_values, nature_names).empty(),
      zh_map_diff(question_values, question_names).empty(),
      zh_map_diff(role_values, role_names).empty(),
      zh_map_diff(relation_values, relation_names).empty(),
      zh_map_diff(effect_values, effect_names).empty(),
      zh_map_diff(judgment_values, judgment_names).empty()};
  for (std::size_t index = 0; index < summary.size(); ++index)
    report += fmt::format("| {} | {} | {} |\n", summary[index].first,
                          summary[index].second,
                          summary_ok[index] ? "逐值一致" : "不一致");

  if (!diff.empty())
    throw std::runtime_error("奇门枚举中文映射核对失败：" + diff);
  return report;
}
} // namespace

int main() {
  try {
    std::string report = "# 奇门遁甲多案例参考回归与格局闭环\n\n";
    report += run_reference_regression();
    report += run_fixed_palace_regression();
    report += run_enum_zh_map_contract();
    const auto analysis_report = run_detailed_analysis_examples();
    std::filesystem::create_directories("docs/qimen");
    std::ofstream output("docs/qimen/example_qi_men_reference_regression.txt",
                         std::ios::binary);
    if (!output)
      throw std::runtime_error("无法创建奇门回归报告");
    output << report;
    std::ofstream analysis_output(
        "docs/qimen/example_qi_men_analysis_output.txt", std::ios::binary);
    if (!analysis_output)
      throw std::runtime_error("无法创建奇门占断报告");
    analysis_output << analysis_report;
    fmt::print("{}", report);
    fmt::print("{}", analysis_report);
    fmt::println(
        "\n报告已写入 docs/qimen/example_qi_men_reference_regression.txt");
    fmt::println(
        "占断报告已写入 docs/qimen/example_qi_men_analysis_output.txt");
    return 0;
  } catch (const std::exception &error) {
    fmt::println("奇门回归失败：{}", error.what());
    return 1;
  }
}
