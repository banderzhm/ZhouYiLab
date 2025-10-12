// 主程序入口 - 唯一允许的 .cpp 文件

// 导入第三方库模块（优先）
import fmt;
import magic_enum;
import nlohmann.json;

// 导入自定义模块
import ZhouYi.TianGan;
import ZhouYi.DiZhi;
import ZhouYi.LunarCalendar;

// 导入标准库模块（最后）
import std;

int main() {
    // 使用 fmt 模块进行彩色输出
    fmt::print(fg(fmt::color::green) | fmt::emphasis::bold, 
               "=== ZhouYiLab C++23 Modules Demo ===\n\n");
    
    // ==================== 反射功能演示 ====================
    fmt::print(fg(fmt::color::cyan) | fmt::emphasis::bold, 
               "【1】magic_enum 反射功能演示\n");
    fmt::print("----------------------------------------------\n");
    
    // 获取天干枚举的所有值
    fmt::print(fg(fmt::color::yellow), "天干枚举值列表（英文名）:\n");
    constexpr auto tian_gan_values = magic_enum::enum_values<ZhouYi::TianGan::Type>();
    for (auto value : tian_gan_values) {
        auto name = magic_enum::enum_name(value);
        auto index = magic_enum::enum_integer(value);
        fmt::print("  [{:2d}] {}\n", index, name);
    }
    
    fmt::print("\n");
    
    // ==================== 中文映射演示 ====================
    fmt::print(fg(fmt::color::cyan) | fmt::emphasis::bold, 
               "【2】中文映射功能演示\n");
    fmt::print("----------------------------------------------\n");
    
    // 天干中英文对照
    fmt::print(fg(fmt::color::magenta), "天干中英文对照表:\n");
    for (auto value : tian_gan_values) {
        auto en_name = magic_enum::enum_name(value);
        auto zh_name = ZhouYi::TianGanMapper::to_zh(value);
        fmt::print("  {} → {}\n", en_name, zh_name);
    }
    
    fmt::print("\n");
    
    // 地支中英文对照
    fmt::print(fg(fmt::color::magenta), "地支中英文对照表（含生肖）:\n");
    constexpr auto di_zhi_values = magic_enum::enum_values<ZhouYi::DiZhi::Type>();
    for (auto value : di_zhi_values) {
        auto en_name = magic_enum::enum_name(value);
        auto zh_name = ZhouYi::DiZhiMapper::to_zh(value);
        auto sheng_xiao = ZhouYi::DiZhiMapper::sheng_xiao_zh(value);
        fmt::print("  {} → {} ({})\n", en_name, zh_name, sheng_xiao);
    }
    
    fmt::print("\n");
    
    // ==================== 农历功能演示 ====================
    fmt::print(fg(fmt::color::cyan) | fmt::emphasis::bold, 
               "【3】农历日历功能演示（tyme4cpp）\n");
    fmt::print("----------------------------------------------\n");
    
    // 示例日期：1986年5月29日
    auto solar = ZhouYi::Lunar::SolarDate::from_ymd(1986, 5, 29);
    auto lunar = solar.to_lunar();
    
    fmt::print(fg(fmt::color::green), "公历日期: {}\n", solar.to_string());
    fmt::print(fg(fmt::color::green), "农历日期: {}\n", lunar.to_string());
    fmt::print(fg(fmt::color::yellow), "年份干支: {}\n", lunar.get_year_gan_zhi());
    fmt::print(fg(fmt::color::yellow), "月份干支: {}\n", lunar.get_month_gan_zhi());
    fmt::print(fg(fmt::color::yellow), "日期干支: {}\n", lunar.get_day_gan_zhi());
    fmt::print(fg(fmt::color::magenta), "生　　肖: {}\n", lunar.get_zodiac());
    fmt::print(fg(fmt::color::magenta), "星　　座: {}\n", solar.get_constellation());
    fmt::print(fg(fmt::color::magenta), "星　　期: {}\n", solar.get_week_name());
    
    // 节气信息
    auto term = solar.get_solar_term();
    if (term.has_value()) {
        fmt::print(fg(fmt::color::cyan), "节　　气: {}\n", term.value());
    }
    
    fmt::print("\n");
    
    // 今天的日期
    fmt::print(fg(fmt::color::green) | fmt::emphasis::bold, "今日日期信息:\n");
    auto today = ZhouYi::Lunar::SolarDate::today();
    auto today_lunar = today.to_lunar();
    fmt::print("公历: {}\n", today.to_string());
    fmt::print("农历: {}\n", today_lunar.to_string());
    fmt::print("干支: {} {} {}\n", 
               today_lunar.get_year_gan_zhi(),
               today_lunar.get_month_gan_zhi(),
               today_lunar.get_day_gan_zhi());
    
    fmt::print("\n");
    
    // ==================== 六十甲子演示 ====================
    fmt::print(fg(fmt::color::cyan) | fmt::emphasis::bold, 
               "【4】六十甲子表\n");
    fmt::print("----------------------------------------------\n");
    
    auto sixty_cycles = ZhouYi::Lunar::GanZhi::get_sixty_cycles();
    fmt::print(fg(fmt::color::yellow), "六十甲子:\n");
    for (std::size_t i = 0; i < sixty_cycles.size(); ++i) {
        fmt::print("{:2d}.{} ", i + 1, sixty_cycles[i]);
        if ((i + 1) % 10 == 0) {
            fmt::print("\n");
        }
    }
    fmt::print("\n");
    
    // ==================== 二十四节气演示 ====================
    fmt::print(fg(fmt::color::cyan) | fmt::emphasis::bold, 
               "【5】2025年二十四节气表\n");
    fmt::print("----------------------------------------------\n");
    
    auto terms_2025 = ZhouYi::Lunar::SolarTerm::get_terms_of_year(2025);
    for (const auto& [term_name, date] : terms_2025) {
        auto [y, m, d] = date;
        fmt::print(fg(fmt::color::green), "{:6s} → {}年{:2d}月{:2d}日\n", 
                   term_name, y, m, d);
    }
    
    fmt::print("\n");
    
    // ==================== JSON 序列化演示 ====================
    fmt::print(fg(fmt::color::cyan) | fmt::emphasis::bold, 
               "【6】JSON 序列化演示\n");
    fmt::print("----------------------------------------------\n");
    
    // 构建项目信息 JSON
    nlohmann::json project_info = {
        {"name", "ZhouYiLab"},
        {"version", "1.0.0"},
        {"description", "基于 C++23 Modules 的传统文化算法库"},
        {"features", {
            "完全模块化设计",
            "magic_enum 反射支持",
            "中英文双向映射",
            "农历公历互转",
            "干支纪年月日",
            "二十四节气",
            "fmt 格式化输出",
            "nlohmann::json 序列化"
        }},
        {"modules", {
            {"standard", "import std"},
            {"third_party", {
                {"fmt", "import fmt"},
                {"magic_enum", "import magic_enum"},
                {"nlohmann_json", "import nlohmann.json"}
            }},
            {"custom", {
                {"TianGan", "import ZhouYi.TianGan"},
                {"DiZhi", "import ZhouYi.DiZhi"},
                {"LunarCalendar", "import ZhouYi.LunarCalendar"}
            }}
        }},
        {"libraries", {
            {"tyme4cpp", {
                {"url", "https://github.com/6tail/tyme4cpp"},
                {"description", "强大的日历工具库"}
            }}
        }}
    };
    
    fmt::print("{}\n\n", project_info.dump(2));
    
    // ==================== 统计信息 ====================
    fmt::print(fg(fmt::color::cyan) | fmt::emphasis::bold, 
               "【7】统计信息\n");
    fmt::print("----------------------------------------------\n");
    
    fmt::print("天干数量: {}\n", magic_enum::enum_count<ZhouYi::TianGan::Type>());
    fmt::print("地支数量: {}\n", magic_enum::enum_count<ZhouYi::DiZhi::Type>());
    fmt::print("六十甲子: {} 个\n", sixty_cycles.size());
    fmt::print("二十四节气: {} 个\n", terms_2025.size());
    
    fmt::print("\n");
    fmt::print(fg(fmt::color::magenta) | fmt::emphasis::italic, 
               "✨ 所有功能演示完成！\n");
    fmt::print(fg(fmt::color::green), 
               "🎉 C++23 Modules + 反射 + 中文映射 + 农历日历完美运行！\n");
    
    return 0;
}
