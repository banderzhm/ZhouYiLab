// 演示 C++23 modules 使用示例

// 导入 C++23 标准库模块
import std;

// 导入自定义模块
import ZhouYi.TianGan;
import ZhouYi.DiZhi;
import ZhouYi.GanZhi;

// 使用传统头文件引入第三方库
#include <fmt/core.h>
#include <fmt/color.h>

using namespace ZhouYi;

void demo_tian_gan() {
    fmt::print(fg(fmt::color::cyan) | fmt::emphasis::bold,
               "\n=== 天干演示 ===\n");
    
    auto tian_gan_list = get_all_tian_gan();
    fmt::print("十天干: ");
    for (const auto& gan : tian_gan_list) {
        fmt::print("{} ", gan.to_string());
    }
    fmt::print("\n\n");
    
    // 显示天干详细信息
    fmt::print("天干详细信息:\n");
    for (const auto& gan : tian_gan_list) {
        fmt::print("  {} - 五行:{:2} {}\n",
                   gan.to_string(),
                   gan.wu_xing(),
                   gan.is_yang() ? "阳" : "阴");
    }
}

void demo_di_zhi() {
    fmt::print(fg(fmt::color::magenta) | fmt::emphasis::bold,
               "\n=== 地支演示 ===\n");
    
    auto di_zhi_list = get_all_di_zhi();
    fmt::print("十二地支: ");
    for (const auto& zhi : di_zhi_list) {
        fmt::print("{} ", zhi.to_string());
    }
    fmt::print("\n\n");
    
    // 显示地支详细信息
    fmt::print("地支详细信息:\n");
    for (const auto& zhi : di_zhi_list) {
        fmt::print("  {} ({}) - 五行:{:2} {:8} {}\n",
                   zhi.to_string(),
                   zhi.sheng_xiao(),
                   zhi.wu_xing(),
                   zhi.shi_chen(),
                   zhi.is_yang() ? "阳" : "阴");
    }
}

void demo_gan_zhi() {
    fmt::print(fg(fmt::color::yellow) | fmt::emphasis::bold,
               "\n=== 干支演示 ===\n");
    
    // 计算今年的干支
    int year = 2025;
    auto gan_zhi = GanZhi::from_year(year);
    
    fmt::print(fg(fmt::color::green),
               "公历 {} 年:\n", year);
    fmt::print("{}\n", gan_zhi.get_info());
    fmt::print("纳音五行: {}\n", gan_zhi.na_yin());
}

void demo_liu_shi_jia_zi() {
    fmt::print(fg(fmt::color::light_blue) | fmt::emphasis::bold,
               "\n=== 六十甲子 ===\n");
    
    print_liu_shi_jia_zi();
}

int main_modules_demo() {
    fmt::print(fg(fmt::color::cyan) | fmt::emphasis::bold,
               "╔════════════════════════════════════════════╗\n");
    fmt::print(fg(fmt::color::yellow) | fmt::emphasis::bold,
               "║   ZhouYiLab - C++23 Modules 演示程序      ║\n");
    fmt::print(fg(fmt::color::cyan) | fmt::emphasis::bold,
               "╚════════════════════════════════════════════╝\n");
    
    demo_tian_gan();
    demo_di_zhi();
    demo_gan_zhi();
    demo_liu_shi_jia_zi();
    
    fmt::print(fg(fmt::color::cyan) | fmt::emphasis::italic,
               "\n✨ C++23 modules 演示完成！\n");
    
    return 0;
}

