/**
 * @file example_qi_men.cpp
 * @brief 奇门排盘与分门占断中文报告示例。
 */
import ZhouYi.GanZhi;
import ZhouYi.QiMen;
import ZhouYi.QiMen.Analysis;
import ZhouYi.QiMen.Analysis.Presenter;
import ZhouYi.QiMen.Controller;
import ZhouYi.QiMen.Pan;
import fmt;
import std;

namespace {
using namespace ZhouYi::QiMen;
using ZhouYi::GanZhi::TianGan;

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
    output << "\n## 案例 " << index + 1 << "\n\n";
    ZhouYi::QiMenAnalysis::write_zh(output, *generated, analysis);
  }
  return output.str();
}
} // namespace

int main() {
  try {
    const auto analysis_report = run_detailed_analysis_examples();
    std::filesystem::create_directories("docs/qimen");
    std::ofstream analysis_output(
        "docs/qimen/example_qi_men_analysis_output.txt", std::ios::binary);
    if (!analysis_output)
      throw std::runtime_error("无法创建奇门占断报告");
    analysis_output << analysis_report;
    fmt::print("{}", analysis_report);
    fmt::println(
        "占断报告已写入 docs/qimen/example_qi_men_analysis_output.txt");
    return 0;
  } catch (const std::exception &error) {
    fmt::println("奇门示例失败：{}", error.what());
    return 1;
  }
}
