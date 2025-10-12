// 主程序入口 - 唯一允许的 .cpp 文件

// 导入第三方库模块（优先）
import fmt;
import magic_enum;
import nlohmann.json;

// 导入自定义模块
import ZhouYi.TianGan;
import ZhouYi.DiZhi;

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
    
    // ==================== 反向查询演示 ====================
    fmt::print(fg(fmt::color::cyan) | fmt::emphasis::bold, 
               "【3】反向查询功能演示\n");
    fmt::print("----------------------------------------------\n");
    
    // 从中文名称查询天干
    std::vector<std::string_view> test_zh_names = {"甲", "丙", "庚", "癸"};
    fmt::print(fg(fmt::color::green), "从中文名称查询天干:\n");
    for (auto zh_name : test_zh_names) {
        auto result = ZhouYi::TianGanMapper::from_zh(zh_name);
        if (result.has_value()) {
            auto en_name = magic_enum::enum_name(result.value());
            auto index = magic_enum::enum_integer(result.value());
            fmt::print("  \"{}\" → {} [index: {}]\n", zh_name, en_name, index);
        } else {
            fmt::print("  \"{}\" → 未找到\n", zh_name);
        }
    }
    
    fmt::print("\n");
    
    // 从英文名称查询地支
    std::vector<std::string_view> test_en_names = {"Zi", "Mao", "Wu", "You"};
    fmt::print(fg(fmt::color::green), "从英文名称查询地支:\n");
    for (auto en_name : test_en_names) {
        auto result = ZhouYi::DiZhiMapper::from_en(en_name);
        if (result.has_value()) {
            auto zh_name = ZhouYi::DiZhiMapper::to_zh(result.value());
            auto sheng_xiao = ZhouYi::DiZhiMapper::sheng_xiao_zh(result.value());
            fmt::print("  \"{}\" → {} ({})\n", en_name, zh_name, sheng_xiao);
        } else {
            fmt::print("  \"{}\" → 未找到\n", en_name);
        }
    }
    
    fmt::print("\n");
    
    // ==================== JSON 序列化演示 ====================
    fmt::print(fg(fmt::color::cyan) | fmt::emphasis::bold, 
               "【4】JSON 序列化演示\n");
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
                {"ZhMapper", "import ZhouYi.ZhMapper"}
            }}
        }}
    };
    
    fmt::print("{}\n\n", project_info.dump(2));
    
    // ==================== 统计信息 ====================
    fmt::print(fg(fmt::color::cyan) | fmt::emphasis::bold, 
               "【5】统计信息\n");
    fmt::print("----------------------------------------------\n");
    
    fmt::print("天干数量: {}\n", magic_enum::enum_count<ZhouYi::TianGan::Type>());
    fmt::print("地支数量: {}\n", magic_enum::enum_count<ZhouYi::DiZhi::Type>());
    fmt::print("六十甲子: {} × {} = {}\n", 
               magic_enum::enum_count<ZhouYi::TianGan::Type>(),
               magic_enum::enum_count<ZhouYi::DiZhi::Type>(),
               magic_enum::enum_count<ZhouYi::TianGan::Type>() * 
               magic_enum::enum_count<ZhouYi::DiZhi::Type>() / 2);
    
    fmt::print("\n");
    fmt::print(fg(fmt::color::magenta) | fmt::emphasis::italic, 
               "✨ 所有功能演示完成！\n");
    fmt::print(fg(fmt::color::green), 
               "🎉 C++23 Modules + magic_enum 反射 + 中文映射完美运行！\n");
    
    return 0;
}
