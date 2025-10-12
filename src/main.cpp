#include <iostream>
#include <fmt/core.h>
#include <fmt/color.h>
#include <nlohmann/json.hpp>

using json = nlohmann::json;

int main() {
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
    
    fmt::print(fg(fmt::color::cyan) | fmt::emphasis::italic,
               "\n感谢使用！更多功能敬请期待...\n");
    
    return 0;
}

