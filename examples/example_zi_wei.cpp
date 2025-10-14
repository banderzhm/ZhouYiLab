// 紫微斗数系统示例
import ZhouYi.ZiWei.Controller;
import ZhouYi.GanZhi;
import fmt;
import std;

using namespace ZhouYi::ZiWei;
using namespace ZhouYi::GanZhi;
using namespace std;

int main() {
    fmt::print("\n");
    fmt::print("╔════════════════════════════════════════════════════════════╗\n");
    fmt::print("║                紫微斗数系统示例演示                        ║\n");
    fmt::print("╚════════════════════════════════════════════════════════════╝\n");
    fmt::print("\n");

    try {
        // 示例1：阳历排盘
        fmt::print("【示例1】阳历排盘\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        fmt::print("出生时间：1990年5月20日 14时 男命\n\n");
        pai_pan_and_print_solar(1990, 5, 20, 14, true);
        fmt::print("\n");

        // 示例2：农历排盘
        fmt::print("【示例2】农历排盘\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        fmt::print("出生时间：农历1990年四月二十六日 未时 女命\n\n");
        pai_pan_and_print_lunar(1990, 4, 26, 13, false);
        fmt::print("\n");

        // 示例3：查看命盘详细信息
        fmt::print("【示例3】命盘详细信息\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        auto result = pai_pan_solar(1990, 5, 20, 14, true);
        
        fmt::print("命主：{}\n", result.ming_zhu);
        fmt::print("身主：{}\n", result.shen_zhu);
        fmt::print("五行局：{}\n", string(Mapper::to_zh(result.wu_xing_ju)));
        fmt::print("命宫索引：{}\n", result.ming_gong_index);
        fmt::print("身宫索引：{}\n", result.shen_gong_index);
        fmt::print("\n");

        // 示例4：查看指定宫位详情
        fmt::print("【示例4】命宫详情\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        display_palace_detail(result, GongWei::MingGong);
        fmt::print("\n");

        // 示例5：三方四正分析
        fmt::print("【示例5】命宫三方四正\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        display_ming_gong_san_fang_si_zheng(result);
        fmt::print("\n");

        // 示例6：格局分析
        fmt::print("【示例6】格局分析\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        display_ge_ju_analysis(result);
        fmt::print("\n");

        // 示例7：四化分析
        fmt::print("【示例7】宫干四化分析\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        display_gong_gan_si_hua(result);
        fmt::print("\n");

        // 示例8：大限分析
        fmt::print("【示例8】大限分析\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        display_da_xian_analysis(result);
        fmt::print("\n");

        // 示例9：流年分析
        fmt::print("【示例9】2025年流年分析（36岁）\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        display_liu_nian_analysis(result, 2025, 36);
        fmt::print("\n");

        // 示例10：流月分析
        fmt::print("【示例10】2025年6月流月分析\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        display_liu_yue_analysis(result, 2025, 6, 36);
        fmt::print("\n");

        // 示例11：星耀特性查询
        fmt::print("【示例11】星耀特性查询\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        display_star_info("紫微");
        display_star_info("天机");
        fmt::print("\n");

        // 示例12：完整命盘分析
        fmt::print("【示例12】完整命盘分析报告\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        fmt::print("(此示例会输出完整的命盘分析报告)\n");
        // display_full_analysis(result);  // 取消注释可查看完整报告
        fmt::print("\n");

        // 示例13：运限完整分析
        fmt::print("【示例13】2025年6月15日未时运限分析\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        fmt::print("(此示例会输出大限、小限、流年、流月、流日、流时的完整分析)\n");
        // display_yun_xian_full_analysis(result, 2025, 6, 15, DiZhi::Wei, 36);
        fmt::print("\n");

        // 示例14：导出JSON
        fmt::print("【示例14】导出JSON格式\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        auto json_str = export_to_json(result);
        fmt::print("{}\n", json_str.substr(0, min(size_t(300), json_str.size())));
        fmt::print("...(JSON内容已截断)\n\n");

        // 示例15：导出完整JSON（包含格局、四化等）
        fmt::print("【示例15】导出完整JSON（包含格局分析）\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        auto json_full = export_to_json_full(result);
        fmt::print("{}\n", json_full.substr(0, min(size_t(300), json_full.size())));
        fmt::print("...(完整JSON内容已截断)\n\n");

    } catch (const exception& e) {
        fmt::print("❌ 错误：{}\n", e.what());
        return 1;
    }

    fmt::print("✅ 紫微斗数系统示例演示完成！\n\n");
    return 0;
}

