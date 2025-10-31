// example_stacktrace.cpp - std::stacktrace 功能测试示例
// 展示 C++23 std::stacktrace 的各种用法
// 注意：std::stacktrace 目前主要在 MSVC (Windows) 上完整支持

#ifdef _WIN32

import std;

// 自定义异常类，包含 stacktrace
class traced_error : public std::exception {
    std::string message_;
    std::stacktrace trace_;
    
public:
    explicit traced_error(std::string msg)
        : message_{std::move(msg)}
        , trace_{std::stacktrace::current()} {}
    
    [[nodiscard]] auto what() const noexcept -> char const* override {
        return message_.c_str();
    }
    
    [[nodiscard]] auto trace() const noexcept -> std::stacktrace const& {
        return trace_;
    }
};

// 递归函数，用于生成深层调用栈
auto recursive_function(int depth) -> void {
    if (depth <= 0) {
        std::println("📍 深度为 0，捕获当前调用栈:");
        auto trace = std::stacktrace::current();
        std::println("  调用栈深度: {}", trace.size());
        
        // 打印前 10 帧
        auto const max_frames = std::min(std::size_t{10}, trace.size());
        for (std::size_t i = 0z; i < max_frames; ++i) {
            auto const& entry = trace[i];
            std::println("  [{}] {}", i, entry);
        }
        return;
    }
    
    recursive_function(depth - 1);
}

// 打印详细的 stacktrace 信息（包括源文件、函数名、行号）
auto print_detailed_stacktrace(std::stacktrace const& trace, std::string_view title = "详细调用栈") -> void {
    std::println("\n📋 {}:", title);
    std::println("{'=':#>60}", "");
    
    if (trace.empty()) {
        std::println("  (调用栈为空)");
        return;
    }
    
    for (std::size_t i = 0z; i < trace.size(); ++i) {
        auto const& entry = trace[i];
        
        std::println("\n  帧 #{}", i);
        std::println("  {'─':#>58}", "");
        
        // 获取源文件路径
        auto const source_file = entry.source_file();
        if (not source_file.empty()) {
            std::println("  📄 源文件: {}", source_file);
            
            // 获取行号
            auto const line_num = entry.source_line();
            if (line_num != 0) {
                std::println("  📍 行号: {}", line_num);
            }
        }
        
        // 获取函数描述（包含函数名、符号等）
        auto const description = entry.description();
        if (not description.empty()) {
            std::println("  🔧 函数: {}", description);
        }
        
        // 完整的 stacktrace_entry 信息
        std::println("  📊 完整信息: {}", entry);
        
        // 如果只显示前几帧
        if (i >= 9) {
            std::size_t remaining = trace.size() - i - 1;
            if (remaining > 0) {
                std::println("\n  ... 还有 {} 帧未显示", remaining);
            }
            break;
        }
    }
    
    std::println("\n{'=':#>60}", "");
}

// 抛出异常的函数
auto function_that_throws() -> void {
    throw traced_error("这是一个带有堆栈跟踪的异常!");
}

// 调用抛出异常的函数
auto caller_function() -> void {
    function_that_throws();
}

// 使用 std::expected 返回错误和 stacktrace
struct error_with_trace {
    std::string message;
    std::stacktrace trace;
};

auto risky_operation(bool should_fail) -> std::expected<int, error_with_trace> {
    if (should_fail) {
        return std::unexpected(error_with_trace{
            .message = "操作失败",
            .trace = std::stacktrace::current()
        });
    }
    return 42;
}

// 测试 stacktrace 的各种操作
auto test_stacktrace_operations() -> void {
    std::println("\n🔬 测试 stacktrace 基本操作:");
    
    auto trace = std::stacktrace::current();
    
    std::println("  • 当前调用栈大小: {}", trace.size());
    std::println("  • 是否为空: {}", trace.empty());
    
    if (not trace.empty()) {
        std::println("  • 第一帧: {}", trace[0]);
        
        // 打印前几帧
        if (trace.size() >= 5) {
            std::println("  • 显示前 5 帧:");
            for (std::size_t i = 0z; i < 5; ++i) {
                std::println("    [{}] {}", i, trace[i]);
            }
        }
    }
}

// 测试 stacktrace 分配器
auto test_stacktrace_with_allocator() -> void {
    std::println("\n🎯 测试带自定义分配器的 stacktrace:");
    
    // 使用默认分配器
    auto trace = std::stacktrace::current(std::allocator<std::stacktrace_entry>{});
    std::println("  • 成功创建带分配器的 stacktrace，大小: {}", trace.size());
}

// 比较两个 stacktrace
auto test_stacktrace_comparison() -> void {
    std::println("\n⚖️  测试 stacktrace 比较:");
    
    auto trace1 = std::stacktrace::current();
    auto trace2 = std::stacktrace::current();
    
    std::println("  • trace1 == trace2: {}", trace1 == trace2);
    std::println("  • trace1 != trace2: {}", trace1 != trace2);
}

// 测试 stacktrace 迭代器
auto test_stacktrace_iteration() -> void {
    std::println("\n🔄 测试 stacktrace 迭代:");
    
    auto trace = std::stacktrace::current();
    
    std::println("  • 使用范围 for 循环遍历前 5 帧:");
    std::size_t count = 0z;
    for (auto const& entry : trace) {
        if (count >= 5) break;
        std::println("    帧 {}: {}", count++, entry);
    }
}

// 测试 stacktrace 的 hash 支持
auto test_stacktrace_hash() -> void {
    std::println("\n#️⃣  测试 stacktrace 哈希:");
    
    auto trace1 = std::stacktrace::current();
    auto trace2 = std::stacktrace::current();
    
    std::hash<std::stacktrace> hasher;
    auto hash1 = hasher(trace1);
    auto hash2 = hasher(trace2);
    
    std::println("  • trace1 的哈希值: 0x{:x}", hash1);
    std::println("  • trace2 的哈希值: 0x{:x}", hash2);
    std::println("  • 哈希值相同: {}", hash1 == hash2);
}

// 在 unordered_map 中使用 stacktrace
auto test_stacktrace_in_map() -> void {
    std::println("\n🗺️  测试在 unordered_map 中使用 stacktrace:");
    
    std::unordered_map<std::stacktrace, int> trace_map;
    
    auto trace1 = std::stacktrace::current();
    trace_map[trace1] = 100;
    
    auto trace2 = std::stacktrace::current();
    trace_map[trace2] = 200;
    
    std::println("  • Map 大小: {}", trace_map.size());
    std::println("  • trace1 对应的值: {}", trace_map[trace1]);
}

auto main() -> int {
    std::println("════════════════════════════════════════");
    std::println("   🔍 C++23 std::stacktrace 功能测试");
    std::println("════════════════════════════════════════\n");
    
    try {
        // 1. 测试基本的 stacktrace 捕获
        std::println("1️⃣  测试基本 stacktrace 捕获:");
        auto current_trace = std::stacktrace::current();
        std::println("  ✓ 成功捕获当前调用栈，深度: {}\n", current_trace.size());
        
        // 2. 测试递归调用栈
        std::println("2️⃣  测试递归调用栈:");
        recursive_function(5);
        std::println("  ✓ 递归调用栈测试完成\n");
        
        // 3. 测试异常中的 stacktrace
        std::println("3️⃣  测试异常中的 stacktrace:");
        try {
            caller_function();
        } catch (traced_error const& e) {
            std::println("  ✓ 捕获到异常: {}", e.what());
            std::println("  ✓ 异常抛出时的调用栈深度: {}", e.trace().size());
            
            // 打印异常调用栈的前 5 帧（简单格式）
            std::println("\n  简单格式输出（前5帧）:");
            auto const max_frames = std::min(std::size_t{5}, e.trace().size());
            for (std::size_t i = 0z; i < max_frames; ++i) {
                std::println("    [{}] {}", i, e.trace()[i]);
            }
            
            // 打印详细的异常块代码信息
            print_detailed_stacktrace(e.trace(), "异常抛出位置的详细调用栈");
        }
        std::println();
        
        // 4. 测试 std::expected 与 stacktrace
        std::println("4️⃣  测试 std::expected 与 stacktrace:");
        auto result = risky_operation(true);
        
        if (result.has_value()) {
            std::println("  ✓ 操作成功，值: {}", result.value());
        } else {
            auto const& err = result.error();
            std::println("  ✓ 捕获到错误: {}", err.message);
            std::println("  ✓ 错误发生时的调用栈深度: {}", err.trace.size());
        }
        std::println();
        
        // 5. 测试 stacktrace 基本操作
        test_stacktrace_operations();
        
        // 6. 测试带分配器的 stacktrace
        test_stacktrace_with_allocator();
        
        // 7. 测试 stacktrace 比较
        test_stacktrace_comparison();
        
        // 8. 测试 stacktrace 迭代
        test_stacktrace_iteration();
        
        // 9. 测试 stacktrace 哈希
        test_stacktrace_hash();
        
        // 10. 测试在容器中使用 stacktrace
        test_stacktrace_in_map();
        
        std::println("\n════════════════════════════════════════");
        std::println("   ✅ 所有测试完成!");
        std::println("════════════════════════════════════════");
        
        return 0;
        
    } catch (std::exception const& e) {
        std::println("\n❌ 发生异常: {}", e.what());
        
        // 捕获当前位置的 stacktrace
        auto crash_trace = std::stacktrace::current();
        std::println("❌ 崩溃时的调用栈（简单格式）:");
        auto const max_frames = std::min(std::size_t{10}, crash_trace.size());
        for (std::size_t i = 0z; i < max_frames; ++i) {
            std::println("  [{}] {}", i, crash_trace[i]);
        }
        
        // 打印详细的崩溃信息
        print_detailed_stacktrace(crash_trace, "崩溃位置的详细调用栈");
        
        return 1;
    } catch (...) {
        std::println("\n❌ 发生未知异常!");
        
        auto crash_trace = std::stacktrace::current();
        std::println("❌ 崩溃时的调用栈（简单格式，前10帧）:");
        auto const max_frames = std::min(std::size_t{10}, crash_trace.size());
        for (std::size_t i = 0z; i < max_frames; ++i) {
            std::println("  [{}] {}", i, crash_trace[i]);
        }
        
        // 打印详细的崩溃信息
        print_detailed_stacktrace(crash_trace, "未知异常的详细调用栈");
        
        return 1;
    }
}

#else  // !_WIN32

import std;

auto main() -> int {
    std::println("════════════════════════════════════════");
    std::println("   ⚠️  std::stacktrace 测试跳过");
    std::println("════════════════════════════════════════\n");
    
    std::println("std::stacktrace 目前主要在 Windows (MSVC) 上完整支持。");
    std::println("Linux/Unix 平台的支持仍在发展中。");
    std::println("\n如需在 Linux 上测试 stacktrace，请使用：");
    std::println("  - libbacktrace");
    std::println("  - boost::stacktrace");
    std::println("  - 或等待 libc++/libstdc++ 的完整实现");
    
    std::println("\n════════════════════════════════════════");
    
    return 0;
}

#endif  // _WIN32

