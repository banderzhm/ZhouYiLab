// 八字系统示例
import ZhouYi.BaZiController;
import ZhouYi.GanZhi;
import ZhouYi.ZhMapper;
import fmt;
import std;

using namespace ZhouYi::BaZiController;
using namespace ZhouYi::GanZhi;
using namespace ZhouYi::Mapper;
using namespace std;

int main() {
    fmt::print("\n");
    fmt::print("╔════════════════════════════════════════════════════════════╗\n");
    fmt::print("║                  八字系统示例演示                          ║\n");
    fmt::print("╚════════════════════════════════════════════════════════════╝\n");
    fmt::print("\n");

    try {
        // 示例1：排盘并显示完整八字信息
        fmt::print("【示例1】公历排盘：1990年5月20日14时（男）\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        auto result = pai_pan_solar(1990, 5, 20, 14, 0, true);
        display_result(result);
        fmt::print("\n");

        // 示例2：查看详细信息
        fmt::print("【示例2】查看八字详细信息\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        const auto& ba_zi = result.ba_zi;
        
        // 显示四柱
        fmt::print("年柱：{}\n", ba_zi.year.to_string());
        fmt::print("月柱：{}\n", ba_zi.month.to_string());
        fmt::print("日柱：{}\n", ba_zi.day.to_string());
        fmt::print("时柱：{}\n", ba_zi.hour.to_string());
        fmt::print("\n");

        // 示例3：查看十神关系
        fmt::print("【示例3】十神关系分析\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        fmt::print("年干对日干：{}\n", 
            string(to_zh(get_shi_shen(ba_zi.day.gan, ba_zi.year.gan))));
        fmt::print("月干对日干：{}\n", 
            string(to_zh(get_shi_shen(ba_zi.day.gan, ba_zi.month.gan))));
        fmt::print("时干对日干：{}\n", 
            string(to_zh(get_shi_shen(ba_zi.day.gan, ba_zi.hour.gan))));
        fmt::print("\n");

        // 示例4：查看童限和大运信息
        fmt::print("【示例4】童限和大运信息\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        display_child_limit_detail(result);
        fmt::print("\n");
        display_da_yun(result, 5);  // 显示前5个大运
        fmt::print("\n");

        // 示例5：查看流年信息
        fmt::print("【示例5】流年信息（2020-2025）\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        display_liu_nian(result, 2020, 6);
        fmt::print("\n");

        // 示例6：查看流月信息
        fmt::print("【示例6】2024年流月信息\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        display_liu_yue(result, 2024);
        fmt::print("\n");

    } catch (const exception& e) {
        fmt::print("❌ 错误：{}\n", e.what());
        return 1;
    }

    fmt::print("✅ 八字系统示例演示完成！\n\n");
    return 0;
}