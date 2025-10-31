// 六爻排盘实例演示
// 展示六爻排盘的实际应用和输出效果

// Use global module fragment to isolate doctest from C++23 modules
module;
#include <doctest/doctest.h>

import ZhouYi.LiuYaoController;
import ZhouYi.BaZiBase;
import std;

using namespace ZhouYi::LiuYaoController;
using namespace ZhouYi::BaZiBase;

// 辅助函数：格式化爻象
std::string format_yao_line(char yao_type, const std::string& change_mark) {
    std::string line = (yao_type == '1') ? "▅▅▅▅▅" : "▅▅ ▅▅";
    if (change_mark != " ") {
        return line + " " + change_mark;
    }
    return line;
}

// 辅助函数：打印排盘结果
void print_liu_yao_result(const LiuYaoPaiPanResult& result, const std::string& title) {
    std::println("\n{}", std::string(60, '='));
    std::println("【{}】", title);
    std::println("{}", std::string(60, '='));
    
    // 输出卦名
    if (result.json_data.contains("ben_gua_name")) {
        std::println("本卦：{}", result.json_data["ben_gua_name"].get<std::string>());
    }
    if (result.json_data.contains("bian_gua_name")) {
        std::println("变卦：{}", result.json_data["bian_gua_name"].get<std::string>());
    }
    
    // 输出八字信息
    if (result.json_data.contains("ba_zi")) {
        auto bazi = result.json_data["ba_zi"];
        // 八字中的年月日时是 Pillar 对象，需要获取其字符串表示
        std::string year_str, month_str, day_str, hour_str;
        
        if (bazi.contains("year") && bazi["year"].is_object()) {
            // 从 Pillar 对象中获取干支字符串
            if (bazi["year"].contains("stem") && bazi["year"].contains("branch")) {
                year_str = bazi["year"]["stem"].get<std::string>() + bazi["year"]["branch"].get<std::string>();
            }
        }
        if (bazi.contains("month") && bazi["month"].is_object()) {
            if (bazi["month"].contains("stem") && bazi["month"].contains("branch")) {
                month_str = bazi["month"]["stem"].get<std::string>() + bazi["month"]["branch"].get<std::string>();
            }
        }
        if (bazi.contains("day") && bazi["day"].is_object()) {
            if (bazi["day"].contains("stem") && bazi["day"].contains("branch")) {
                day_str = bazi["day"]["stem"].get<std::string>() + bazi["day"]["branch"].get<std::string>();
            }
        }
        if (bazi.contains("hour") && bazi["hour"].is_object()) {
            if (bazi["hour"].contains("stem") && bazi["hour"].contains("branch")) {
                hour_str = bazi["hour"]["stem"].get<std::string>() + bazi["hour"]["branch"].get<std::string>();
            }
        }
        
        std::println("\n八字：{} {} {} {}", year_str, month_str, day_str, hour_str);
    }
    
    // 输出表格标题
    std::println("\n{:<8} {:<12} {:<22} {:<18}", "六神", "伏神", "本卦 (飞神)", "变卦");
    std::println("{:-<8}+{:-<12}+{:-<22}+{:-<18}", "", "", "", "");
    
    // 从上爻到初爻输出（六爻到一爻）
    for (int i = 5; i >= 0; --i) {
        const auto& yao = result.yao_list[i];
        
        // 六神列
        std::string spirit_col = yao.spirit;
        
        // 伏神列
        std::string fu_shen_col = yao.hiddenRelative + 
                                  yao.hiddenPillar.stem() + 
                                  yao.hiddenPillar.branch() + 
                                  yao.hiddenElement;
        
        // 本卦列
        std::string ben_gua_text = yao.mainRelative + 
                                   yao.mainPillar.stem() + 
                                   yao.mainPillar.branch() + 
                                   yao.mainElement;
        std::string change_marker = (yao.changeMark == " ") ? " " : (" " + yao.changeMark);
        std::string shi_ying_marker = (yao.shiYingMark == " ") ? "" : (" " + yao.shiYingMark);
        std::string ben_gua_col = ben_gua_text + 
                                  " " + format_yao_line(yao.mainYaoType, yao.changeMark) + 
                                  shi_ying_marker;
        
        // 变卦列
        std::string bian_gua_col = "";
        if (yao.isChanging) {
            char final_yao_type = (yao.mainYaoType == '0' ? '1' : '0');
            bian_gua_col = yao.changedRelative + 
                           yao.changedPillar.stem() + 
                           yao.changedPillar.branch() + 
                           yao.changedElement + 
                           " " + format_yao_line(final_yao_type, " ");
        }
        
        // 打印当前行
        std::println("{:<8} {:<12} {:<22} {:<18}", 
                     spirit_col, fu_shen_col, ben_gua_col, bian_gua_col);
    }
    
    // 输出神煞信息
    if (result.json_data.contains("shen_sa")) {
        std::println("\n【神煞】");
        auto shen_sa = result.json_data["shen_sa"];
        for (auto it = shen_sa.begin(); it != shen_sa.end(); ++it) {
            const std::string& name = it.key();
            const auto& branches = it.value();
            
            std::string branch_list;
            for (const auto& branch : branches) {
                if (!branch_list.empty()) branch_list += " ";
                branch_list += branch.get<std::string>();
            }
            if (!branch_list.empty()) {
                std::println("{}: {}", name, branch_list);
            }
        }
    }
    
    std::println("");
}

TEST_SUITE("六爻排盘实例演示") {
    
    TEST_CASE("基础卦象排盘展示") {
        std::println("六爻排盘系统 - 基础卦象演示");
        std::println("========================");
        
        // 创建八字 - 2024年10月13日14时30分（甲辰年 甲戌月 丁巳日 丁未时）
        auto bazi = BaZi::from_solar(2024, 10, 13, 14, 30);
        
        SUBCASE("乾为天卦（无动爻）") {
            auto result = calculate_liu_yao("111111", bazi, {});
            print_liu_yao_result(result, "乾为天卦 - 无动爻");
        }
        
        SUBCASE("坤为地卦（三爻动）") {
            auto result = calculate_liu_yao("000000", bazi, {3});
            print_liu_yao_result(result, "坤为地卦 - 三爻动");
        }
        
        SUBCASE("水火既济（二、四爻动）") {
            auto result = calculate_liu_yao("101010", bazi, {2, 4});
            print_liu_yao_result(result, "水火既济 - 二、四爻动");
        }
        
        SUBCASE("天地否卦（初、上爻动）") {
            auto result = calculate_liu_yao("000111", bazi, {1, 6});
            print_liu_yao_result(result, "天地否卦 - 初、上爻动");
        }
        
        SUBCASE("雷风恒卦（六爻皆动）") {
            auto result = calculate_liu_yao("011100", bazi, {1, 2, 3, 4, 5, 6});
            print_liu_yao_result(result, "雷风恒卦 - 六爻皆动");
        }
    }
    
    TEST_CASE("从爻辞生成卦象演示") {
        std::println("\n{}", std::string(60, '='));
        std::println("【从爻辞生成卦象演示】");
        std::println("{}", std::string(60, '='));
        
        auto bazi = BaZi::from_solar(2024, 10, 13, 14, 30);
        
        // 爻辞：少阳、少阴、老阳、老阴、少阳、少阴
        std::vector<int> yao_ci = {7, 8, 9, 6, 7, 8};
        std::vector<int> changing_lines;
        auto code = yao_ci_to_hexagram_code(yao_ci, changing_lines);
        
        std::println("爻辞：{} {} {} {} {} {}", yao_ci[0], yao_ci[1], yao_ci[2], yao_ci[3], yao_ci[4], yao_ci[5]);
        std::println("生成卦象代码：{}", code);
        std::print("动爻位置：");
        for (int pos : changing_lines) {
            std::print("{} ", pos);
        }
        std::println("");
        
        auto result = calculate_liu_yao(code, bazi, changing_lines);
        print_liu_yao_result(result, "从爻辞生成的卦象");
    }
    
    TEST_CASE("金钱卦排盘演示") {
        std::println("\n{}", std::string(60, '='));
        std::println("【金钱卦演示】");
        std::println("{}", std::string(60, '='));
        
        auto bazi = BaZi::from_solar(2024, 10, 13, 14, 30);
        
        // 金钱卦结果：3个正面、2个正面、1个正面...
        std::vector<int> coin_results = {3, 2, 1, 3, 2, 3};
        auto coin_code = numbers_to_hexagram_code(coin_results, true); // 奇数为阳
        
        std::println("金钱卦结果：{} {} {} {} {} {}", 
                     coin_results[0], coin_results[1], coin_results[2], 
                     coin_results[3], coin_results[4], coin_results[5]);
        std::println("生成卦象代码：{}", coin_code);
        
        auto coin_result = calculate_liu_yao(coin_code, bazi, {});
        print_liu_yao_result(coin_result, "金钱卦排盘");
    }
    
    TEST_CASE("批量排盘演示") {
        std::println("\n{}", std::string(60, '='));
        std::println("【批量排盘演示】");
        std::println("{}", std::string(60, '='));
        
        auto bazi1 = BaZi::from_solar(2024, 10, 13, 14, 30);
        auto bazi2 = BaZi::from_solar(2024, 11, 1, 10, 0);
        
        std::vector<std::tuple<std::string, BaZi, std::vector<int>>> batch_requests = {
            {"111111", bazi1, {}},
            {"000000", bazi2, {1, 3}},
            {"010101", bazi1, {2, 4, 6}}
        };
        
        auto batch_results = batch_calculate_liu_yao(batch_requests);
        std::println("批量处理了 {} 个卦象", batch_results.size());
        
        for (size_t i = 0; i < batch_results.size(); ++i) {
            const auto& result = batch_results[i];
            if (!result.json_data.contains("error")) {
                std::println("第 {} 个卦象排盘成功", i + 1);
                // 可以选择性地输出部分结果
                if (i == 0) { // 只详细显示第一个结果
                    print_liu_yao_result(result, "批量排盘示例 - 乾为天");
                }
            } else {
                std::println("第 {} 个卦象排盘失败：{}", i + 1, 
                             result.json_data["error"].get<std::string>());
            }
        }
    }
    
    TEST_CASE("经典卦象展示") {
        auto bazi = BaZi::from_solar(2025, 1, 15, 9, 30); // 使用不同时间
        
        SUBCASE("火雷噬嗑（上九动）") {
            auto result = calculate_liu_yao("100101", bazi, {6});
            print_liu_yao_result(result, "火雷噬嗑 - 上九动");
        }
        
        SUBCASE("风水涣（初六、九二动）") {
            auto result = calculate_liu_yao("010011", bazi, {1, 2});
            print_liu_yao_result(result, "风水涣 - 初六、九二动");
        }
        
        SUBCASE("山天大畜（三、四、五爻动）") {
            auto result = calculate_liu_yao("111001", bazi, {3, 4, 5});
            print_liu_yao_result(result, "山天大畜 - 三、四、五爻动");
        }
    }
    
    TEST_CASE("特殊情况展示") {
        auto bazi = BaZi::from_solar(2024, 12, 21, 15, 45); // 冬至时节
        
        SUBCASE("泽天夬（全动）") {
            auto result = calculate_liu_yao("111110", bazi, {1, 2, 3, 4, 5, 6});
            print_liu_yao_result(result, "泽天夬 - 全动（之坤为地）");
        }
        
        SUBCASE("地水师（无动爻）") {
            auto result = calculate_liu_yao("010000", bazi, {});
            print_liu_yao_result(result, "地水师 - 静卦");
        }
        
        SUBCASE("测试错误处理") {
            std::println("\n【测试输入验证】");
            try {
                // 这应该会抛出异常
                auto result = calculate_liu_yao("12345", bazi, {});
            } catch (const std::exception& e) {
                std::println("成功捕获无效输入错误：{}", e.what());
            }
            
            try {
                // 这也应该会抛出异常
                auto result = calculate_liu_yao("111111", bazi, {7, 8});
            } catch (const std::exception& e) {
                std::println("成功捕获无效动爻错误：{}", e.what());
            }
        }
    }
}