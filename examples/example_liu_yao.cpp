// 六爻系统示例
import ZhouYi.LiuYao.Controller;
import ZhouYi.GanZhi;
import fmt;
import std;

using namespace ZhouYi::LiuYao;
using namespace ZhouYi::GanZhi;
using namespace std;

int main() {
    fmt::print("\n");
    fmt::print("╔════════════════════════════════════════════════════════════╗\n");
    fmt::print("║                  六爻系统示例演示                          ║\n");
    fmt::print("╚════════════════════════════════════════════════════════════╝\n");
    fmt::print("\n");

    try {
        // 示例1：手动起卦（数字起卦）
        fmt::print("【示例1】数字起卦\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        fmt::print("上卦数：7，下卦数：5，动爻数：6\n\n");
        qi_gua_and_print_by_numbers(7, 5, 6);
        fmt::print("\n");

        // 示例2：时间起卦（阳历）
        fmt::print("【示例2】时间起卦（阳历）\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        fmt::print("起卦时间：2025年6月15日 14时30分\n\n");
        qi_gua_and_print_solar(2025, 6, 15, 14, 30);
        fmt::print("\n");

        // 示例3：时间起卦（农历）
        fmt::print("【示例3】时间起卦（农历）\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        fmt::print("起卦时间：农历2025年五月二十日 午时\n\n");
        qi_gua_and_print_lunar(2025, 5, 20, 11);
        fmt::print("\n");

        // 示例4：金钱卦起卦
        fmt::print("【示例4】金钱卦起卦\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        fmt::print("六次摇卦结果：老阳、少阴、老阴、少阳、少阳、少阴\n\n");
        vector<YaoType> yao_list = {
            YaoType::OldYang,   // 初爻
            YaoType::YoungYin,  // 二爻
            YaoType::OldYin,    // 三爻
            YaoType::YoungYang, // 四爻
            YaoType::YoungYang, // 五爻
            YaoType::YoungYin   // 六爻
        };
        qi_gua_and_print_by_coins(yao_list);
        fmt::print("\n");

        // 示例5：获取卦象对象并查看详细信息
        fmt::print("【示例5】卦象详细信息\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        auto result = qi_gua_by_numbers(7, 5, 6);
        
        fmt::print("本卦：{}\n", result.ben_gua.gua_name);
        fmt::print("卦宫：{}\n", result.ben_gua.gua_gong);
        fmt::print("世爻：{}爻\n", result.ben_gua.shi_yao);
        fmt::print("应爻：{}爻\n", result.ben_gua.ying_yao);
        
        if (result.bian_gua) {
            fmt::print("\n变卦：{}\n", result.bian_gua->gua_name);
        } else {
            fmt::print("\n无变卦（静卦）\n");
        }
        
        fmt::print("\n六爻详情：\n");
        for (size_t i = 0; i < result.ben_gua.liu_yao.size(); ++i) {
            const auto& yao = result.ben_gua.liu_yao[i];
            fmt::print("  {}爻：{}（{}）{} {}\n",
                i == 0 ? "初" : (i == 5 ? "上" : to_string(i + 1)),
                yao.yao_xing == YaoXing::Yang ? "—" : "- -",
                string(di_zhi_to_zh(yao.di_zhi)),
                yao.liu_qin,
                yao.liu_shen);
        }
        fmt::print("\n");

        // 示例6：导出JSON
        fmt::print("【示例6】导出JSON格式\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        auto json_str = export_to_json(result);
        fmt::print("{}\n", json_str.substr(0, min(size_t(200), json_str.size())));
        fmt::print("...(JSON内容已截断)\n\n");

    } catch (const exception& e) {
        fmt::print("❌ 错误：{}\n", e.what());
        return 1;
    }

    fmt::print("✅ 六爻系统示例演示完成！\n\n");
    return 0;
}

