/**
 * @file example_zi_wei.cpp
 * @brief 紫微斗数完整排盘与中文报告示例；参考案例校验见 tests。
 */
import ZhouYi.ZiWei;
import ZhouYi.ZiWei.Controller;
import fmt;
import std;

/**
 * 生成农历二〇〇〇年六月十五申时男命的完整排盘报告。
 * @return 成功返回零；排盘或文件写入失败返回一。
 */
int main() {
  try {
    const auto result = ZhouYi::ZiWei::pai_pan_lunar(2000, 6, 15, 16, true);
    std::filesystem::create_directories("docs/ziwei");
    std::ofstream output("docs/ziwei/example_zi_wei_output.txt");
    if (!output)
      throw std::runtime_error("无法创建紫微斗数排盘报告");
    output << "输入：农历2000年六月十五日16:30，男命\n"
           << "时间口径：未提供地点，直接采用输入时刻，归申时\n\n"
           << result.to_string()
           << "\n【完整结构化结果（含四化、格局与运限）】\n"
           << ZhouYi::ZiWei::export_to_json_full(result) << '\n';
    output.flush();
    if (!output)
      throw std::runtime_error("紫微斗数报告写入失败");
    fmt::print("{}", result.to_string());
    ZhouYi::ZiWei::display_ge_ju_analysis(result);
    fmt::println("报告已写入 docs/ziwei/example_zi_wei_output.txt");
    return 0;
  } catch (const std::exception &error) {
    fmt::println("紫微斗数示例失败：{}", error.what());
    return 1;
  }
}
