# C++23 Modules 使用指南

本项目完全启用了 C++23 modules 支持，包括标准库模块和第三方库模块。

## 📚 目录

- [概述](#概述)
- [启用的模块](#启用的模块)
- [CMake 配置](#cmake-配置)
- [使用示例](#使用示例)
- [模块依赖关系](#模块依赖关系)
- [编译要求](#编译要求)
- [故障排除](#故障排除)

## 概述

本项目使用 C++23 最新的模块特性：

1. **`import std;`** - 标准库模块化
2. **fmt module** - 格式化库模块
3. **nlohmann_json module** - JSON 库模块
4. **自定义模块** - 项目自身的模块化代码

## 启用的模块

### 标准库模块

```cpp
import std;  // 导入整个 C++ 标准库
```

通过设置 `CMAKE_EXPERIMENTAL_CXX_IMPORT_STD` 启用，基于 [CMake 文档](https://cmake.org/cmake/help/latest/prop_tgt/CXX_MODULE_STD.html)。

### 第三方库模块

#### fmt 模块

```cmake
set(FMT_MODULE ON CACHE BOOL "Build fmt as a C++ module" FORCE)
```

fmt 库提供 C++20 module 支持，可以这样使用：

```cpp
// 传统方式（仍然支持）
#include <fmt/core.h>
#include <fmt/color.h>

// 模块方式（当 FMT_MODULE=ON 时）
import fmt;
```

#### nlohmann_json 模块

```cmake
set(NLOHMANN_JSON_BUILD_MODULES ON CACHE BOOL "Build nlohmann_json C++ module" FORCE)
```

nlohmann_json 库提供 C++ module 支持（需要 CMake 3.28+）：

```cpp
// 传统方式（仍然支持）
#include <nlohmann/json.hpp>

// 模块方式（当 NLOHMANN_JSON_BUILD_MODULES=ON 时）
import nlohmann.json;
```

### 自定义模块

项目提供以下自定义模块：

| 模块名称 | 文件 | 描述 |
|---------|------|------|
| `ZhouYi.TianGan` | `src/example_module.cppm` | 天干相关功能 |
| `ZhouYi.DiZhi` | `src/dizhi_module.cppm` | 地支相关功能 |
| `ZhouYi.GanZhi` | `src/ganzhi_module.cppm` | 干支综合功能 |

使用示例：

```cpp
import ZhouYi.TianGan;
import ZhouYi.DiZhi;
import ZhouYi.GanZhi;

using namespace ZhouYi;

auto gan_zhi = GanZhi::from_year(2025);
```

## CMake 配置

### 主 CMakeLists.txt 配置

```cmake
cmake_minimum_required(VERSION 3.30)

# 启用 C++23 import std 实验性功能
set(CMAKE_EXPERIMENTAL_CXX_IMPORT_STD
    "0e5b6991-d74f-4b3d-a41c-cf096e0b2508")

project(ZhouYiLab LANGUAGES CXX)

# 设置 C++ 标准
set(CMAKE_CXX_STANDARD 23)
set(CMAKE_CXX_STANDARD_REQUIRED YES)
set(CMAKE_CXX_EXTENSIONS OFF)

# 启用 modules 扫描
set(CMAKE_CXX_SCAN_FOR_MODULES ON)

# 配置第三方库的 modules 支持
set(FMT_MODULE ON CACHE BOOL "Build fmt as a C++ module" FORCE)
set(NLOHMANN_JSON_BUILD_MODULES ON CACHE BOOL "Build nlohmann_json C++ module" FORCE)

# 添加第三方库
add_subdirectory(3rdparty/fmt)
add_subdirectory(3rdparty/nlohmann_json)

# 创建可执行文件
file(GLOB_RECURSE SOURCES "src/*.cpp" "src/*.cppm" "src/*.ixx")
add_executable(${PROJECT_NAME} ${SOURCES})

# 设置目标属性
set_target_properties(${PROJECT_NAME} PROPERTIES
    CXX_STANDARD 23
    CXX_STANDARD_REQUIRED YES
    CXX_EXTENSIONS OFF
    CXX_SCAN_FOR_MODULES ON
    CXX_MODULE_STD ON  # 启用 import std 支持
)

# 链接第三方库
target_link_libraries(${PROJECT_NAME} 
    PRIVATE 
        fmt::fmt 
        nlohmann_json::nlohmann_json
)
```

### 关键配置项说明

| 配置项 | 说明 | 参考文档 |
|-------|------|---------|
| `CMAKE_EXPERIMENTAL_CXX_IMPORT_STD` | 启用 `import std;` 支持 | [CXX_MODULE_STD](https://cmake.org/cmake/help/latest/prop_tgt/CXX_MODULE_STD.html) |
| `CMAKE_CXX_SCAN_FOR_MODULES` | 全局启用模块扫描 | [CXX_SCAN_FOR_MODULES](https://cmake.org/cmake/help/latest/prop_tgt/CXX_SCAN_FOR_MODULES.html) |
| `CXX_MODULE_STD` | 目标级别的 `import std` 支持 | 同上 |
| `FMT_MODULE` | 构建 fmt 为模块 | fmt 库选项 |
| `NLOHMANN_JSON_BUILD_MODULES` | 构建 nlohmann_json 模块 | nlohmann_json 库选项 |

## 使用示例

### 基本示例

```cpp
// main.cpp
import std;  // 导入标准库

// 导入自定义模块
import ZhouYi.GanZhi;

// 仍然使用传统 include（第三方库暂时保持兼容）
#include <fmt/core.h>
#include <fmt/color.h>

int main() {
    // 使用标准库（通过 import std）
    std::cout << "Hello, C++23 Modules!\n";
    std::vector<int> numbers = {1, 2, 3, 4, 5};
    
    // 使用 fmt
    fmt::print(fg(fmt::color::green), "这是绿色文字\n");
    
    // 使用自定义模块
    auto gan_zhi = ZhouYi::GanZhi::from_year(2025);
    fmt::print("今年是：{}\n", gan_zhi.to_string());
    
    return 0;
}
```

### 创建模块示例

```cpp
// my_module.cppm
export module MyModule;

import std;  // 导入标准库

export namespace MyNamespace {
    class MyClass {
    public:
        std::string get_message() {
            return "Hello from MyModule!";
        }
    };
}
```

### 模块依赖示例

```cpp
// advanced_module.cppm
export module Advanced;

import std;              // 标准库
import ZhouYi.TianGan;   // 依赖其他模块
import ZhouYi.DiZhi;

export namespace Advanced {
    void print_gan_zhi_info() {
        auto gan_list = ZhouYi::get_all_tian_gan();
        auto zhi_list = ZhouYi::get_all_di_zhi();
        
        std::cout << "天干数量: " << gan_list.size() << "\n";
        std::cout << "地支数量: " << zhi_list.size() << "\n";
    }
}
```

## 模块依赖关系

```
main.cpp
  ├── import std
  ├── import ZhouYi.GanZhi
  │     ├── import std
  │     ├── import ZhouYi.TianGan
  │     │     └── import std
  │     └── import ZhouYi.DiZhi
  │           └── import std
  ├── #include <fmt/core.h>
  └── #include <nlohmann/json.hpp>
```

## 编译要求

### CMake 版本

- **最低版本**: 3.30
- **推荐版本**: 最新稳定版

### 编译器要求

| 编译器 | 最低版本 | `import std` 支持 | 备注 |
|--------|---------|------------------|------|
| **GCC** | 14.0 | ✅ 完全支持 | 推荐 |
| **Clang** | 18.0 | ✅ 完全支持 | 推荐 |
| **MSVC** | 19.40+ (VS 2022 17.10+) | ⚠️ 实验性支持 | 需要最新预览版 |

### 构建系统

- **Ninja**: ✅ 推荐（最佳 modules 支持）
- **Unix Makefiles**: ✅ 支持
- **Visual Studio**: ⚠️ 部分支持（建议使用 Ninja）

## 编译命令

### Linux / macOS (推荐使用 Ninja)

```bash
# 安装 Ninja (如果没有)
# Ubuntu/Debian: sudo apt install ninja-build
# macOS: brew install ninja

# 配置
cmake -B build -G Ninja \
  -DCMAKE_BUILD_TYPE=Release \
  -DCMAKE_CXX_COMPILER=g++-14

# 构建
cmake --build build -j$(nproc)

# 运行
./build/bin/ZhouYiLab --demo
```

### Windows (Visual Studio)

```powershell
# 配置
cmake -B build -G "Ninja" `
  -DCMAKE_BUILD_TYPE=Release `
  -DCMAKE_CXX_COMPILER=cl

# 或使用 Visual Studio 2022
cmake -B build -G "Visual Studio 17 2022" -A x64

# 构建
cmake --build build --config Release

# 运行
.\build\bin\Release\ZhouYiLab.exe --demo
```

## 故障排除

### 问题 1: CMake 版本过低

```
CMake Error: CMake 3.30 or higher is required
```

**解决方案**: 从 [cmake.org](https://cmake.org/download/) 下载最新版本

### 问题 2: 编译器不支持 modules

```
error: C++23 modules are not supported
```

**解决方案**: 
- GCC: 升级到 14.0+
- Clang: 升级到 18.0+
- MSVC: 安装 Visual Studio 2022 最新预览版

### 问题 3: import std 失败

```
error: module 'std' not found
```

**解决方案**: 确保设置了实验性标志：

```cmake
set(CMAKE_EXPERIMENTAL_CXX_IMPORT_STD
    "0e5b6991-d74f-4b3d-a41c-cf096e0b2508")
```

### 问题 4: 第三方库模块未构建

```
Building nlohmann.json C++ module - Skipping
```

**检查**:
1. CMake 版本 >= 3.28
2. 选项已设置：
   ```cmake
   set(NLOHMANN_JSON_BUILD_MODULES ON CACHE BOOL "" FORCE)
   ```

### 问题 5: 模块依赖错误

```
error: module 'ZhouYi.GanZhi' depends on module 'ZhouYi.TianGan'
```

**解决方案**: 确保 CMake 正确扫描了所有 `.cppm` 文件：

```cmake
file(GLOB_RECURSE SOURCES "src/*.cpp" "src/*.cppm" "src/*.ixx")
```

### 问题 6: MSVC 模块编译失败

**解决方案**: 添加 MSVC 特定标志：

```cmake
if(MSVC)
    add_compile_options(/experimental:module)
    add_compile_options(/utf-8)
endif()
```

## 性能优势

使用 C++23 modules 的优势：

1. **编译速度**: 模块只需编译一次，显著减少重复编译时间
2. **隔离性**: 模块内部实现细节不会泄露到导入方
3. **依赖管理**: 显式的 `import` 语句让依赖关系更清晰
4. **二进制大小**: 避免头文件重复展开，减小最终二进制大小

## 参考资源

- [CMake CXX_MODULE_STD 文档](https://cmake.org/cmake/help/latest/prop_tgt/CXX_MODULE_STD.html)
- [CMake CXX_SCAN_FOR_MODULES 文档](https://cmake.org/cmake/help/latest/prop_tgt/CXX_SCAN_FOR_MODULES.html)
- [C++23 Modules (cppreference)](https://en.cppreference.com/w/cpp/language/modules)
- [fmt 库文档](https://fmt.dev/)
- [nlohmann/json 文档](https://json.nlohmann.me/)
- [GCC C++23 支持](https://gcc.gnu.org/projects/cxx-status.html#cxx23)
- [Clang C++23 支持](https://clang.llvm.org/cxx_status.html#cxx23)
- [MSVC C++23 支持](https://learn.microsoft.com/en-us/cpp/overview/visual-cpp-language-conformance)

## 贡献

欢迎对模块化设计提出改进建议！请：

1. Fork 本仓库
2. 创建特性分支
3. 提交 Pull Request

## 许可证

MIT License - 详见 [LICENSE](LICENSE) 文件

