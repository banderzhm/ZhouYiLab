/**
 * @file example_mei_hua.cpp
 * @brief 梅花易数时间起卦、象数起卦与中文报告示例。
 */
import ZhouYi.MeiHua.Controller;
import ZhouYi.MeiHua.Analysis.Presenter;
import fmt;
import std;

int main() {
  try {
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
    auto located_request = time_request;
    located_request.location = ZhouYi::Time::Location{
        .longitude = 104.0665, .latitude = 30.5723, .timezone = 8};
    const auto corrected_pan = cast_by_time(located_request);
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
