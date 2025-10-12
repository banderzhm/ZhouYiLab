// 主程序入口 - 唯一允许的 .cpp 文件

// 导入第三方库模块（优先）
import fmt;
import magic_enum;
import nlohmann.json;

// 导入自定义模块
import ZhouYi.TianGan;
import ZhouYi.DiZhi;
import ZhouYi.GanZhi;
import ZhouYi.tyme;

// 导入标准库模块（最后）
import std;

using namespace fmt;
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
    auto solar = tyme::SolarDay::from_ymd(1986, 5, 29);
    auto lunar = solar.get_lunar_day();
    
    fmt::print(fg(fmt::color::green), "公历日期: {}\n", solar.to_string());
    fmt::print(fg(fmt::color::green), "农历日期: {}\n", lunar.to_string());
    
    // 获取年月日的干支信息
    auto lunar_month = lunar.get_lunar_month();
    auto lunar_year = lunar_month.get_lunar_year();
    
    fmt::print(fg(fmt::color::yellow), "年份干支: {}\n", lunar_year.get_sixty_cycle().get_name());
    fmt::print(fg(fmt::color::yellow), "月份干支: {}\n", lunar_month.get_sixty_cycle().get_name());
    fmt::print(fg(fmt::color::yellow), "日期干支: {}\n", lunar.get_sixty_cycle().get_name());
    fmt::print(fg(fmt::color::magenta), "生　　肖: {}\n", lunar_year.get_zodiac().get_name());
    
    fmt::print("\n");
    
    // ==================== 完整干支系统演示 ====================
    fmt::print(fg(fmt::color::cyan) | fmt::emphasis::bold, 
               "【4】完整干支系统演示（ZhouYi.GanZhi）\n");
    fmt::print("----------------------------------------------\n");
    
    // 展示六十甲子表
    auto jia_zi_list = ZhouYi::GanZhi::get_liu_shi_jia_zi();
    fmt::print(fg(fmt::color::yellow), "六十甲子表:\n");
    for (std::size_t i = 0; i < jia_zi_list.size(); ++i) {
        const auto& jz = jia_zi_list[i];
        fmt::print("{:2d}.{} ", i + 1, jz.to_string());
        if ((i + 1) % 10 == 0) {
            fmt::print("\n");
        }
    }
    fmt::print("\n");
    
    // 演示干支关系
    fmt::print(fg(fmt::color::green) | fmt::emphasis::bold, "干支关系演示:\n");
    
    // 地支六冲
    auto zi = ZhouYi::GanZhi::DiZhi::Zi;
    auto wu = ZhouYi::GanZhi::DiZhi::Wu;
    fmt::print("子午相冲: {}\n", 
               ZhouYi::GanZhi::is_chong(zi, wu) ? "是" : "否");
    
    // 地支六合
    auto chou = ZhouYi::GanZhi::DiZhi::Chou;
    fmt::print("子丑相合: {}\n", 
               ZhouYi::GanZhi::is_he(zi, chou) ? "是" : "否");
    
    // 地支三合
    auto shen = ZhouYi::GanZhi::DiZhi::Shen;
    auto chen = ZhouYi::GanZhi::DiZhi::Chen;
    auto [is_san_he, he_wx] = ZhouYi::GanZhi::is_san_he(shen, zi, chen);
    if (is_san_he) {
        fmt::print("申子辰三合: {} (合化为{})\n", "是", 
                   ZhouYi::GanZhi::Mapper::to_zh(he_wx));
    }
    
    // 五行生克
    auto mu = ZhouYi::GanZhi::WuXing::Mu;
    auto huo = ZhouYi::GanZhi::WuXing::Huo;
    fmt::print("木生火: {}\n", 
               ZhouYi::GanZhi::wu_xing_sheng(mu, huo) ? "是" : "否");
    
    fmt::print("\n");
    
    // 天干贵人
    fmt::print(fg(fmt::color::magenta), "天干贵人:\n");
    auto jia = ZhouYi::GanZhi::TianGan::Jia;
    auto yang_gui = ZhouYi::GanZhi::get_gui_ren(jia, true);
    auto yin_gui = ZhouYi::GanZhi::get_gui_ren(jia, false);
    fmt::print("甲日阳贵: {}, 阴贵: {}\n",
               ZhouYi::GanZhi::Mapper::to_zh(yang_gui),
               ZhouYi::GanZhi::Mapper::to_zh(yin_gui));
    
    fmt::print("\n");
    
    // ==================== 二十四节气演示 ====================
    fmt::print(fg(fmt::color::cyan) | fmt::emphasis::bold, 
               "【5】农历信息演示\n");
    fmt::print("----------------------------------------------\n");
    
    // 展示一些农历信息
    fmt::print(fg(fmt::color::green), "农历年: {}年\n", lunar_year.get_year());
    fmt::print(fg(fmt::color::green), "农历月数: {} 个月\n", lunar_year.get_month_count());
    fmt::print(fg(fmt::color::green), "农历天数: {} 天\n", lunar_year.get_day_count());
    fmt::print(fg(fmt::color::yellow), "本月天数: {} 天\n", lunar_month.get_day_count());
    
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
    fmt::print("五行数量: {}\n", magic_enum::enum_count<ZhouYi::GanZhi::WuXing>());
    fmt::print("六十甲子: {} 个\n", jia_zi_list.size());
    fmt::print("二十四节气: {} 个\n", terms_2025.size());
    
    fmt::print("\n");
    fmt::print(fg(fmt::color::magenta) | fmt::emphasis::italic, 
               "✨ 所有功能演示完成！\n");
    fmt::print(fg(fmt::color::green), 
               "🎉 C++23 Modules + 反射 + 中文映射 + 农历日历完美运行！\n");
    
    return 0;
}