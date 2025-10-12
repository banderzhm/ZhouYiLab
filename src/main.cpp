// 使用 C++23 标准库模块
import std;

// 导入自定义模块
import ZhouYi.GanZhi;

// 使用传统头文件引入第三方库
#include <fmt/core.h>
#include <fmt/color.h>
#include <nlohmann/json.hpp>

using json = nlohmann::json;

// 声明模块演示函数
int main_modules_demo();

int main(int argc, char* argv[]) {
    // 使用 fmt 库进行彩色输出
    fmt::print(fg(fmt::color::cyan) | fmt::emphasis::bold,
               "===========================================\n");
    fmt::print(fg(fmt::color::yellow) | fmt::emphasis::bold,
               "    欢迎使用 ZhouYiLab - 周易实验室\n");
    fmt::print(fg(fmt::color::cyan) | fmt::emphasis::bold,
               "===========================================\n\n");
    
    // 项目信息
    json project_info = {
        {"name", "ZhouYiLab"},
        {"version", "1.0.0"},
        {"description", "传统文化算法库"},
        {"features", {
            "大六壬",
            "六爻",
            "紫微斗数",
            "八字排盘",
            "农历历法"
        }},
        {"technology", {
            {"language", "C++23"},
            {"build_system", "CMake 3.28+"},
            {"modules", true}
        }}
    };
    
    // 打印项目信息
    fmt::print(fg(fmt::color::green), "项目信息:\n");
    fmt::print("{}\n\n", project_info.dump(2));
    
    // 示例：天干地支
    std::vector<std::string> tian_gan = {
        "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"
    };
    
    std::vector<std::string> di_zhi = {
        "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"
    };
    
    fmt::print(fg(fmt::color::magenta), "天干: ");
    for (const auto& gan : tian_gan) {
        fmt::print("{} ", gan);
    }
    fmt::print("\n");
    
    fmt::print(fg(fmt::color::magenta), "地支: ");
    for (const auto& zhi : di_zhi) {
        fmt::print("{} ", zhi);
    }
    fmt::print("\n\n");
    
    // 计算今年的干支
    int year = 2025;
    int gan_index = (year - 4) % 10;
    int zhi_index = (year - 4) % 12;
    
    fmt::print(fg(fmt::color::yellow), "公历 {} 年对应: {}{} 年\n", 
               year, tian_gan[gan_index], di_zhi[zhi_index]);
    
    // 使用 C++23 modules 获取干支信息
    fmt::print("\n");
    fmt::print(fg(fmt::color::green) | fmt::emphasis::bold, 
               "使用 C++23 Modules 计算:\n");
    auto gan_zhi = ZhouYi::GanZhi::from_year(year);
    fmt::print(fg(fmt::color::white), "{}\n", gan_zhi.get_info());
    fmt::print(fg(fmt::color::yellow), "纳音五行: {}\n", gan_zhi.na_yin());
    
    // 菜单选项
    fmt::print("\n");
    fmt::print(fg(fmt::color::cyan), "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
    fmt::print(fg(fmt::color::white), "运行 modules 完整演示？ (y/n): ");
    
    if (argc > 1 && std::string(argv[1]) == "--demo") {
        fmt::print("y\n");
        return main_modules_demo();
    } else {
        std::string input;
        std::getline(std::cin, input);
        if (input == "y" || input == "Y") {
            return main_modules_demo();
        }
    }
    
    fmt::print(fg(fmt::color::cyan) | fmt::emphasis::italic,
               "\n感谢使用！使用 '--demo' 参数运行完整演示。\n");
    
    return 0;
}

