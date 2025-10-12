// 导入标准库模块
import std;

// 导入第三方库模块
import fmt;
import nlohmann.json;

int main() {
    // 使用 std::print
    std::print("Hello World from import std!\n\n");
    
    // 使用 fmt 模块
    fmt::print(fg(fmt::color::green) | fmt::emphasis::bold, 
               "=== ZhouYiLab C++23 Modules Demo ===\n\n");
    
    // 使用 nlohmann::json 模块
    nlohmann::json project_info = {
        {"name", "ZhouYiLab"},
        {"version", "1.0.0"},
        {"modules", {
            "import std",
            "import fmt",
            "import nlohmann.json"
        }},
        {"features", {
            "C++23 modules",
            "CMake 4.0+",
            "Cross-platform"
        }}
    };
    
    fmt::print(fg(fmt::color::cyan), "Project Info:\n");
    fmt::print("{}\n\n", project_info.dump(2));
    
    // 测试容器
    std::vector<int> numbers = {1, 2, 3, 4, 5};
    fmt::print(fg(fmt::color::yellow), "Vector size: {}\n", numbers.size());
    fmt::print(fg(fmt::color::yellow), "Numbers: [");
    for (size_t i = 0; i < numbers.size(); ++i) {
        fmt::print("{}{}", numbers[i], (i < numbers.size() - 1) ? ", " : "");
    }
    fmt::print("]\n\n");
    
    fmt::print(fg(fmt::color::magenta) | fmt::emphasis::italic, 
               "✨ All modules working perfectly!\n");
    
    return 0;
}