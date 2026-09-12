// 大六壬系统示例
import ZhouYi.DaLiuRen.Controller;
import ZhouYi.DaLiuRenAnalysis;
import ZhouYi.DaLiuRenAnalysis.Presenter;
import ZhouYi.GanZhi;
import fmt;
import std;

using namespace ZhouYi::DaLiuRen::Controller;
using namespace ZhouYi::GanZhi;
using namespace std;

int main() {
  fmt::print("\n");
  fmt::print("\n");
  fmt::print("                  大六壬系统示例演示                        \n");
  fmt::print("\n");
  fmt::print("\n");

  try {
    // 示例1：阳历起课
    fmt::print("【示例1】阳历起课\n");
    fmt::print("\n");
    fmt::print("起课时间：2024年1月1日 10时\n\n");
    auto result1 = DaLiuRenController::pai_pan_solar(2024, 1, 1, 10);
    DaLiuRenController::display_result(result1);
    fmt::print("\n");

    /*
    // 示例2：农历起课
    fmt::print("【示例2】农历起课\n");
    fmt::print("\n");
    fmt::print("起课时间：农历2025年五月二十日 午时\n\n");
    auto result2 = DaLiuRenController::pai_pan_lunar(2025, 5, 20, 11);
    DaLiuRenController::display_result(result2);
    fmt::print("\n");

    // 示例3：查看详细排盘信息
    fmt::print("【示例3】查看详细排盘信息\n");
    fmt::print("\n");
    auto result3 = DaLiuRenController::pai_pan_solar(2025, 6, 15, 14);
    DaLiuRenController::display_result_detailed(result3);
    fmt::print("\n");
    */

    // 示例4：查看四课和三传
    fmt::print("【示例4】查看四课和三传\n");
    fmt::print("\n");
    auto result4 = DaLiuRenController::pai_pan_solar(2024, 1, 1, 10);
    DaLiuRenController::display_si_ke(result4.si_ke);
    fmt::print("\n");
    DaLiuRenController::display_san_chuan(
        result4.san_chuan, result4.ba_zi.day.gan, result4.ba_zi.day.zhi);
    fmt::print("\n");
    DaLiuRenController::display_result_detailed(result4);

    // 示例5：按大六壬自身的八门、课传与类神体系生成分析报告。
    // 分析模块不复用八字扶抑或六爻用神评分，也不以神煞单独定吉凶。
    ZhouYi::DaLiuRenAnalysis::AnalysisRequest request{
        .question_kind = ZhouYi::DaLiuRenAnalysis::QuestionKind::QiuCai,
        .question = "本次合作项目能否顺利推进并回款",
        // 下列本命、行年仅用于展示变体门与临盘联断，不代表真实求测者。
        .ben_ming = ZhouYi::GanZhi::DiZhi::Shen,
        .xing_nian = ZhouYi::GanZhi::DiZhi::Zi,
        .gender = ZhouYi::DaLiuRenAnalysis::SeekerGender::Nan,
    };
    const auto analysis = ZhouYi::DaLiuRenAnalysis::analyze(result4, request);
    std::filesystem::create_directories("docs/da_liuren");
    std::ofstream report("docs/da_liuren/example_da_liu_ren_output.txt");
    if (!report) {
      throw std::runtime_error("无法创建大六壬分析报告文件");
    }
    ZhouYi::DaLiuRenAnalysis::write_zh(report, result4, analysis);
    fmt::print(
        "\n分析报告已写入 docs/da_liuren/example_da_liu_ren_output.txt\n");
  } catch (const std::exception &e) {
    fmt::print(" 错误：{}\n", e.what());
    return 1;
  }

  fmt::print(" 大六壬系统示例演示完成！\n\n");
  return 0;
}
