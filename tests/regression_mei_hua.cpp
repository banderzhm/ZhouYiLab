/**
 * @file example_mei_hua.cpp
 * @brief 梅花易数时间起卦、象数起卦与中文报告示例。
 */
import ZhouYi.MeiHua.Controller;
import ZhouYi.MeiHua.Analysis.Presenter;
import fmt;
import std;

namespace {
/**
 * 核验手工列定的本、互、变卦爻序，爻码自初爻至上爻排列。
 * @throws std::runtime_error 任一卦码或动爻位置偏离预期。
 */
void verify_hexagram_fixtures() {
  struct Fixture {
    int upper;                 ///< 上卦先天数。
    int lower;                 ///< 下卦先天数。
    int moving;                ///< 动爻爻位。
    std::string_view original; ///< 本卦爻码。
    std::string_view mutual;   ///< 互卦爻码。
    std::string_view changed;  ///< 变卦爻码。
  };
  constexpr std::array<Fixture, 4> fixtures{{
      {1, 1, 1, "111111", "111111", "011111"},
      {8, 8, 6, "000000", "000000", "000001"},
      {2, 3, 5, "101110", "011111", "101100"},
      {6, 3, 3, "101010", "010101", "100010"},
  }};
  for (const auto &expected : fixtures) {
    const auto pan = ZhouYi::MeiHua::compose_pan(
        ZhouYi::MeiHua::CastingMethod::ThreeNumbers, expected.upper,
        expected.lower, expected.moving);
    if (pan.ben_gua.code != expected.original ||
        pan.hu_gua.code != expected.mutual ||
        pan.bian_gua.code != expected.changed ||
        pan.moving_line != expected.moving)
      throw std::runtime_error("梅花本互变卦固定爻序核验失败");
  }
}

/**
 * @brief 穷举八乘八上下卦和六个动爻，校验卦码、互卦、变卦及体用闭环。
 * @return 通过的排盘组合数；完整结果应为 384。
 */
int run_pan_closure() {
  int passed = 0;
  for (int upper = 1; upper <= 8; ++upper) {
    for (int lower = 1; lower <= 8; ++lower) {
      for (int movement = 1; movement <= 6; ++movement) {
        const auto pan = ZhouYi::MeiHua::compose_pan(
            ZhouYi::MeiHua::CastingMethod::ThreeNumbers, upper, lower,
            movement);
        const bool codes_complete = pan.ben_gua.code.size() == 6 &&
                                    pan.hu_gua.code.size() == 6 &&
                                    pan.bian_gua.code.size() == 6;
        const bool names_complete = !pan.ben_gua.name.empty() &&
                                    !pan.hu_gua.name.empty() &&
                                    !pan.bian_gua.name.empty();
        const bool body_use_correct =
            movement <= 3 ? pan.ti_yong.yong == pan.ben_gua.lower
                          : pan.ti_yong.yong == pan.ben_gua.upper;
        if (!codes_complete || !names_complete || !body_use_correct)
          throw std::runtime_error("梅花易数排盘闭环校验失败");
        ++passed;
      }
    }
  }
  return passed;
}

/**
 * @brief 穷举全部排盘与二十类占问策略，校验分析契约均能落成。
 * @return 通过的“排盘 × 占问”组合数；完整结果应为 7680。
 */
int run_analysis_closure() {
  int passed = 0;
  for (int upper = 1; upper <= 8; ++upper) {
    for (int lower = 1; lower <= 8; ++lower) {
      for (int movement = 1; movement <= 6; ++movement) {
        const auto pan = ZhouYi::MeiHua::compose_pan(
            ZhouYi::MeiHua::CastingMethod::ThreeNumbers, upper, lower,
            movement);
        for (std::size_t kind = 0;
             kind < ZhouYi::MeiHuaAnalysis::question_kind_count; ++kind) {
          const auto analysis = ZhouYi::MeiHuaAnalysis::analyze(
              pan,
              {.question_kind =
                   static_cast<ZhouYi::MeiHuaAnalysis::QuestionKind>(kind)});
          std::array<bool, 5> timing_kinds{};
          for (const auto &timing : analysis.timing) {
            const auto timing_index = static_cast<std::size_t>(timing.kind);
            if (timing_index >= timing_kinds.size() ||
                timing_kinds[timing_index])
              throw std::runtime_error("梅花易数应期门类未形成五层闭环");
            timing_kinds[timing_index] = true;
          }
          const double party_difference =
              std::abs(analysis.party_balance.ti_force -
                       analysis.party_balance.yong_force);
          const bool party_state_correct =
              party_difference < 0.35
                  ? analysis.party_balance.dominant ==
                        ZhouYi::MeiHuaAnalysis::PartySide::XiangChi
              : analysis.party_balance.ti_force >
                      analysis.party_balance.yong_force
                  ? analysis.party_balance.dominant ==
                        ZhouYi::MeiHuaAnalysis::PartySide::Ti
                  : analysis.party_balance.dominant ==
                        ZhouYi::MeiHuaAnalysis::PartySide::Yong;
          if (analysis.party_balance.members.size() != 5 ||
              analysis.mei_hua_basis.size() < 5 ||
              analysis.judgment_detail.components.size() != 4 ||
              analysis.question_reading.focus.empty() ||
              analysis.question_reading.interpretation.empty() ||
              analysis.timing.size() != 5 || !party_state_correct ||
              analysis.conclusion.empty())
            throw std::runtime_error("梅花易数分门占断闭环校验失败");
          ++passed;
        }
      }
    }
  }
  return passed;
}

/** 校验非法象数、溢出保护、闰月及真太阳时跨日等边界。 */
int run_boundary_regression() {
  using namespace ZhouYi::MeiHuaController;
  int passed = 0;
  const auto expect_error = [&passed](auto &&operation) {
    try {
      operation();
    } catch (const std::exception &) {
      ++passed;
      return;
    }
    throw std::runtime_error("梅花易数边界输入未按契约拒绝");
  };
  expect_error([] { (void)cast_by_numbers(0, 8); });
  expect_error(
      [] { (void)cast_by_numbers(std::numeric_limits<long long>::max(), 1); });

  const auto explicit_movement =
      cast_by_numbers(std::numeric_limits<long long>::max(),
                      std::numeric_limits<long long>::max(), 1);
  if (explicit_movement.moving_line != 1)
    throw std::runtime_error("显式动数不应触发两数合计溢出");
  ++passed;

  const auto leap_pan = cast_by_time({.calendar = CalendarKind::Lunar,
                                      .year = 2023,
                                      .month = -2,
                                      .day = 1,
                                      .hour = 12,
                                      .minute = 0});
  if (!leap_pan.lunar_leap_month ||
      leap_pan.lunar_date.find("闰2月") == std::string::npos)
    throw std::runtime_error("农历闰月标记回归失败");
  ++passed;

  const auto crossing_pan =
      cast_by_time({.calendar = CalendarKind::Solar,
                    .year = 2024,
                    .month = 1,
                    .day = 2,
                    .hour = 0,
                    .minute = 10,
                    .location = ZhouYi::Time::Location{
                        .longitude = 0.0, .latitude = 0.0, .timezone = 8}});
  if (!crossing_pan.true_solar_time_applied ||
      crossing_pan.casting_time.substr(0, 10) ==
          crossing_pan.civil_time.substr(0, 10))
    throw std::runtime_error("真太阳时跨日回归失败");
  ++passed;
  return passed;
}
} // namespace

int main() {
  try {
    verify_hexagram_fixtures();
    using namespace ZhouYi::MeiHuaController;
    const TimeCastingRequest time_request{
        .calendar = CalendarKind::Lunar,
        .year = 2000,
        .month = 6,
        .day = 15,
        .hour = 16,
        .minute = 30,
        .location = std::nullopt,
    };
    const ZhouYi::MeiHuaAnalysis::AnalysisRequest question{
        .question_kind = ZhouYi::MeiHuaAnalysis::QuestionKind::GongMing,
        .question = "所谋软件项目后续能否顺利推进",
        .event_pace = ZhouYi::MeiHuaAnalysis::EventPace::ZhongQi,
        .external_omens = {{.kind = ZhouYi::MeiHuaAnalysis::OmenKind::ShengYin,
                            .observation = "起卦时闻三声敲击",
                            .trigram = std::nullopt,
                            .number = 3},
                           {.kind = ZhouYi::MeiHuaAnalysis::OmenKind::FangWei,
                            .observation = "来人自东方至",
                            .trigram = ZhouYi::MeiHua::Trigram::Zhen,
                            .number = std::nullopt}},
    };
    const auto time_result = analyze_by_time(time_request, question);
    if (time_result.pan.ben_gua.name != "泽火革" ||
        time_result.pan.hu_gua.name != "天风姤" ||
        time_result.pan.bian_gua.name != "雷火丰" ||
        time_result.pan.moving_line != 5)
      throw std::runtime_error("年月日时起卦金标准案例回归失败");
    const auto json = ZhouYi::MeiHuaAnalysis::to_zh_json(time_result.pan,
                                                         time_result.analysis);
    if (json.at("契约版本") != "mei-hua-analysis/2.0" ||
        json.at("占断依据").size() < 5 ||
        json.at("卦势判定").at("分项").size() != 4 ||
        json.at("应期候选").size() != 5)
      throw std::runtime_error("梅花易数中文 JSON 契约回归失败");
    auto located_request = time_request;
    located_request.location = ZhouYi::Time::Location{
        .longitude = 104.0665, .latitude = 30.5723, .timezone = 8};
    const auto corrected_pan = cast_by_time(located_request);
    if (!corrected_pan.true_solar_time_applied)
      throw std::runtime_error("梅花时间起卦未执行可选真太阳时校正");
    const auto number_result = analyze_by_numbers(
        23, 41, 17,
        {.question_kind = ZhouYi::MeiHuaAnalysis::QuestionKind::QiuCai,
         .question = "所谋合作能否得财",
         .event_pace = ZhouYi::MeiHuaAnalysis::EventPace::JinQi});
    const auto stroke_result = analyze_by_strokes(
        13, 19, std::nullopt,
        {.question_kind = ZhouYi::MeiHuaAnalysis::QuestionKind::XueYe,
         .question = "所习技艺能否有所进境",
         .event_pace = ZhouYi::MeiHuaAnalysis::EventPace::ZhongQi});
    const auto sound_result = analyze_by_sound(
        3, 9,
        {.question_kind = ZhouYi::MeiHuaAnalysis::QuestionKind::FanZhan,
         .question = "闻声之应所示何如",
         .event_pace = ZhouYi::MeiHuaAnalysis::EventPace::JiYing});

    std::filesystem::create_directories("docs/meihua");
    std::ofstream report("docs/meihua/example_mei_hua_output.txt");
    if (!report)
      throw std::runtime_error("无法创建梅花易数分析报告文件");
    report << "【排盘闭环回归】\n\n";
    report << "八乘八上下卦 × 六动爻：" << run_pan_closure() << "/384 通过\n\n";
    report << "三百八十四卦态 × 二十类占问：" << run_analysis_closure()
           << "/7680 通过\n\n";
    report << "边界输入与历法校正：" << run_boundary_regression()
           << "/5 通过\n\n";
    report << "可选地点真太阳时：成都经纬度校正 "
           << fmt::format("{:.2f}", corrected_pan.time_offset_minutes)
           << " 分钟，起卦时 " << corrected_pan.casting_time << "\n\n";
    report << "【案例一：农历时间起卦】\n\n";
    ZhouYi::MeiHuaAnalysis::write_zh(report, time_result.pan,
                                     time_result.analysis);
    report << "\n\n【案例二：三数起卦】\n\n";
    ZhouYi::MeiHuaAnalysis::write_zh(report, number_result.pan,
                                     number_result.analysis);
    report << "\n\n【案例三：字占笔画起卦】\n\n";
    ZhouYi::MeiHuaAnalysis::write_zh(report, stroke_result.pan,
                                     stroke_result.analysis);
    report << "\n\n【案例四：闻声起卦】\n\n";
    ZhouYi::MeiHuaAnalysis::write_zh(report, sound_result.pan,
                                     sound_result.analysis);
    fmt::print("梅花易数报告已写入 "
               "docs/meihua/example_mei_hua_output.txt\n");
  } catch (const std::exception &error) {
    fmt::print("梅花易数示例失败：{}\n", error.what());
    return 1;
  }
  return 0;
}
