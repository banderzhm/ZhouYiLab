// 六爻系统示例
import ZhouYi.LiuYaoController;
import ZhouYi.BaZiBase;
import ZhouYi.GanZhi;
import fmt;
import std;

using namespace ZhouYi::LiuYaoController;
using namespace ZhouYi::BaZiBase;
using namespace ZhouYi::GanZhi;
using namespace std;

int main() {
    fmt::print("\n");
    fmt::print("╔════════════════════════════════════════════════════════════╗\n");
    fmt::print("║                  六爻系统示例演示                          ║\n");
    fmt::print("╚════════════════════════════════════════════════════════════╝\n");
    fmt::print("\n");

    try {
        // 创建八字（用于六爻排盘）
        auto bazi = BaZi::from_solar(2025, 6, 15, 14, 0);
        
        // 示例1：基本六爻排盘（无动爻）
        fmt::print("【示例1】基本六爻排盘（乾卦，无动爻）\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        fmt::print("主卦代码：111111（六爻皆阳）\n\n");
        
        auto result1 = calculate_liu_yao("111111", bazi);
        
        fmt::print("六爻信息（从下到上）：\n");
        for (const auto& yao : result1.yao_list) {
            fmt::print("  第{}爻: {}\n", yao.position, yao.mainPillar.to_string());
        }
        fmt::print("\n");

        // 示例2：带动爻的六爻排盘
        fmt::print("【示例2】带动爻的六爻排盘（第3爻和第5爻动）\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        fmt::print("主卦代码：010101\n\n");
        
        auto result2 = calculate_liu_yao("010101", bazi, {3, 5});
        
        fmt::print("六爻信息（从下到上）：\n");
        for (const auto& yao : result2.yao_list) {
            fmt::print("  第{}爻: {}\n", yao.position, yao.mainPillar.to_string());
        }
        fmt::print("\n");

        // 示例3：从爻辞生成卦象
        fmt::print("【示例3】从爻辞生成卦象\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        
        vector<int> yao_ci = {7, 8, 6, 9, 7, 8};  // 从下到上：少阳、少阴、老阴（动）、老阳（动）、少阳、少阴
        vector<int> changing_lines;
        auto code = yao_ci_to_hexagram_code(yao_ci, changing_lines);
        
        fmt::print("爻辞：{}\n", fmt::join(yao_ci, ", "));
        fmt::print("主卦代码：{}\n", code);
        fmt::print("动爻位置：{}\n\n", fmt::join(changing_lines, ", "));
        
        auto result3 = calculate_liu_yao(code, bazi, changing_lines);
        fmt::print("六爻信息（从下到上）：\n");
        for (const auto& yao : result3.yao_list) {
            fmt::print("  第{}爻: {}\n", yao.position, yao.mainPillar.to_string());
        }
        fmt::print("\n");

        // 示例4：获取卦象信息
        fmt::print("【示例4】获取卦象信息\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        
        auto hexagram_info = get_hexagram_info("111111");
        fmt::print("卦象代码：111111\n");
        fmt::print("卦名：{}\n", hexagram_info.name);
        fmt::print("含义：{}\n", hexagram_info.meaning);
        fmt::print("五行：{}\n", hexagram_info.fiveElement);
        fmt::print("所属宫位：{}\n", hexagram_info.palaceType);
        fmt::print("世爻位置：第{}爻\n", hexagram_info.shiYaoPosition);
        fmt::print("应爻位置：第{}爻\n", hexagram_info.yingYaoPosition);
        fmt::print("阴阳属性：{}\n", hexagram_info.isYangHexagram ? "阳卦" : "阴卦");
        fmt::print("\n");

        // 示例5：导出JSON
        fmt::print("【示例5】导出JSON格式\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        auto json_str = result1.json_data.dump(2);
        fmt::print("{}\n", json_str.substr(0, min(size_t(300), json_str.size())));
        if (json_str.size() > 300) {
            fmt::print("...(JSON内容已截断)\n");
        }
        fmt::print("\n");

    } catch (const exception& e) {
        fmt::print("❌ 错误：{}\n", e.what());
        return 1;
    }

    fmt::print("✅ 六爻系统示例演示完成！\n\n");
    return 0;
}
