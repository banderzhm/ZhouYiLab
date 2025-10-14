// 八字系统示例
import ZhouYi.BaZi.Controller;
import ZhouYi.GanZhi;
import fmt;
import std;

using namespace ZhouYi::BaZi;
using namespace ZhouYi::GanZhi;
using namespace std;

int main() {
    fmt::print("\n");
    fmt::print("╔════════════════════════════════════════════════════════════╗\n");
    fmt::print("║                  八字系统示例演示                          ║\n");
    fmt::print("╚════════════════════════════════════════════════════════════╝\n");
    fmt::print("\n");

    try {
        // 示例1：排盘并显示完整八字信息
        fmt::print("【示例1】排盘并显示八字信息\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        pai_pan_and_print(1990, 5, 20, 14, true);
        fmt::print("\n");

        // 示例2：获取八字对象并查看详细信息
        fmt::print("【示例2】查看详细信息\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        auto ba_zi = get_ba_zi(1990, 5, 20, 14, true);
        
        // 显示四柱
        fmt::print("年柱：{}{}\n", 
            string(tian_gan_to_zh(ba_zi.year_pillar.tian_gan)),
            string(di_zhi_to_zh(ba_zi.year_pillar.di_zhi)));
        fmt::print("月柱：{}{}\n", 
            string(tian_gan_to_zh(ba_zi.month_pillar.tian_gan)),
            string(di_zhi_to_zh(ba_zi.month_pillar.di_zhi)));
        fmt::print("日柱：{}{}\n", 
            string(tian_gan_to_zh(ba_zi.day_pillar.tian_gan)),
            string(di_zhi_to_zh(ba_zi.day_pillar.di_zhi)));
        fmt::print("时柱：{}{}\n", 
            string(tian_gan_to_zh(ba_zi.hour_pillar.tian_gan)),
            string(di_zhi_to_zh(ba_zi.hour_pillar.di_zhi)));
        fmt::print("\n");

        // 示例3：查看十神关系
        fmt::print("【示例3】十神关系分析\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        fmt::print("年干对日干：{}\n", 
            string(shi_shen_to_zh(get_shi_shen(ba_zi.day_pillar.tian_gan, ba_zi.year_pillar.tian_gan))));
        fmt::print("月干对日干：{}\n", 
            string(shi_shen_to_zh(get_shi_shen(ba_zi.day_pillar.tian_gan, ba_zi.month_pillar.tian_gan))));
        fmt::print("时干对日干：{}\n", 
            string(shi_shen_to_zh(get_shi_shen(ba_zi.day_pillar.tian_gan, ba_zi.hour_pillar.tian_gan))));
        fmt::print("\n");

        // 示例4：查看大运信息（如果有童限数据）
        fmt::print("【示例4】大运流年信息\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        if (ba_zi.child_limit) {
            fmt::print("童限起运年龄：{}岁{}个月\n", 
                ba_zi.child_limit->start_year,
                ba_zi.child_limit->start_month);
            fmt::print("童限结束年龄：{}岁{}个月\n",
                ba_zi.child_limit->end_year,
                ba_zi.child_limit->end_month);
        }
        
        if (!ba_zi.da_yun_list.empty()) {
            fmt::print("\n前3个大运：\n");
            for (size_t i = 0; i < min(size_t(3), ba_zi.da_yun_list.size()); ++i) {
                const auto& dy = ba_zi.da_yun_list[i];
                fmt::print("  第{}步大运（{}岁-{}岁）：{}{}\n",
                    i + 1,
                    dy.start_age,
                    dy.end_age,
                    string(tian_gan_to_zh(dy.gan_zhi.tian_gan)),
                    string(di_zhi_to_zh(dy.gan_zhi.di_zhi)));
            }
        }
        fmt::print("\n");

        // 示例5：导出JSON
        fmt::print("【示例5】导出JSON格式\n");
        fmt::print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        auto json_str = export_to_json(ba_zi);
        fmt::print("{}\n", json_str.substr(0, min(size_t(200), json_str.size())));
        fmt::print("...(JSON内容已截断)\n\n");

    } catch (const exception& e) {
        fmt::print("❌ 错误：{}\n", e.what());
        return 1;
    }

    fmt::print("✅ 八字系统示例演示完成！\n\n");
    return 0;
}

