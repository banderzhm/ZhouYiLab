// 大六壬系统示例
import ZhouYi.DaLiuRen.Controller;
import ZhouYi.GanZhi;
import fmt;
import std;

using namespace ZhouYi::DaLiuRen;
using namespace ZhouYi::GanZhi;
using namespace std;

int main() {
    fmt::print("\n");
    fmt::print("╔════════════════════════════════════════════════════════════╗\n");
    fmt::print("║                  大六壬系统示例演示                        ║\n");
    fmt::print("╚════════════════════════════════════════════════════════════╝\n");
    fmt::print("\n");

    try {
        // 示例1：阳历起课
        fmt::print("【示例1】阳历起课\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        fmt::print("起课时间：2025年6月15日 14时\n\n");
        qi_ke_and_print_solar(2025, 6, 15, 14);
        fmt::print("\n");

        // 示例2：农历起课
        fmt::print("【示例2】农历起课\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        fmt::print("起课时间：农历2025年五月二十日 午时\n\n");
        qi_ke_and_print_lunar(2025, 5, 20, 11);
        fmt::print("\n");

        // 示例3：指定干支起课
        fmt::print("【示例3】指定干支起课\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        fmt::print("起课干支：甲子日 丙寅时\n\n");
        qi_ke_and_print_by_ganzhi(TianGan::Jia, DiZhi::Zi, DiZhi::Yin);
        fmt::print("\n");

        // 示例4：获取课式对象并查看详细信息
        fmt::print("【示例4】课式详细信息\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        auto result = qi_ke_solar(2025, 6, 15, 14);
        
        fmt::print("日干：{}\n", string(tian_gan_to_zh(result.ri_gan)));
        fmt::print("日支：{}\n", string(di_zhi_to_zh(result.ri_zhi)));
        fmt::print("时支：{}\n", string(di_zhi_to_zh(result.shi_zhi)));
        
        fmt::print("\n四课：\n");
        for (size_t i = 0; i < result.si_ke.size(); ++i) {
            fmt::print("  第{}课：{}\n", i + 1, result.si_ke[i].to_string());
        }
        
        fmt::print("\n三传：\n");
        for (size_t i = 0; i < result.san_chuan.size(); ++i) {
            fmt::print("  {}传：{}\n", 
                i == 0 ? "初" : (i == 1 ? "中" : "末"),
                result.san_chuan[i].to_string());
        }
        fmt::print("\n");

        // 示例5：导出JSON
        fmt::print("【示例5】导出JSON格式\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        auto json_str = export_to_json(result);
        fmt::print("{}\n", json_str.substr(0, min(size_t(200), json_str.size())));
        fmt::print("...(JSON内容已截断)\n\n");

    } catch (const exception& e) {
        fmt::print("❌ 错误：{}\n", e.what());
        return 1;
    }

    fmt::print("✅ 大六壬系统示例演示完成！\n\n");
    return 0;
}

