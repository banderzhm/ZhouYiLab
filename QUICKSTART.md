# 🚀 快速开始指南

## 一键构建和运行

### Windows

```powershell
# 克隆项目
git clone --recursive https://github.com/banderzhm/ZhouYiLab.git
cd ZhouYiLab

# 使用 Ninja 构建（推荐）
cmake -B build -G "Ninja" -DCMAKE_BUILD_TYPE=Release
cmake --build build

# 运行
.\build\bin\ZhouYiLab.exe --demo
```

### Linux / macOS

```bash
# 克隆项目
git clone --recursive https://github.com/banderzhm/ZhouYiLab.git
cd ZhouYiLab

# 构建
cmake -B build -G Ninja -DCMAKE_BUILD_TYPE=Release
cmake --build build -j$(nproc)

# 运行
./build/bin/ZhouYiLab --demo
```

## 📋 最低要求

| 组件 | 版本 |
|------|------|
| CMake | 3.30+ |
| GCC | 14.0+ |
| Clang | 18.0+ |
| MSVC | 19.40+ (VS 2022 17.10+) |

## ✨ C++23 特性

本项目展示以下 C++23 现代特性：

### 1. 标准库模块 (`import std;`)

```cpp
import std;  // 不再需要 #include <iostream>, <vector> 等

int main() {
    std::vector<int> numbers = {1, 2, 3};
    std::cout << "Hello, C++23!\n";
}
```

### 2. 自定义模块

```cpp
// 天干模块
import ZhouYi.TianGan;

auto tian_gan = ZhouYi::get_all_tian_gan();
for (const auto& gan : tian_gan) {
    std::cout << gan.to_string() << " ";
}
```

### 3. 第三方库模块

```cpp
// fmt 和 nlohmann_json 都启用了模块支持
#include <fmt/core.h>        // 格式化输出
#include <nlohmann/json.hpp> // JSON 处理
```

## 🎯 项目亮点

- ✅ **完整的 C++23 modules 支持**（包括 `import std;`）
- ✅ **第三方库模块化**（fmt + nlohmann_json）
- ✅ **Git 子模块管理依赖**
- ✅ **跨平台支持**（Windows / Linux / macOS）
- ✅ **现代 CMake 构建系统**（3.30+）
- ✅ **传统文化算法实现**（天干、地支、六十甲子）

## 📂 项目结构

```
ZhouYiLab/
├── 3rdparty/                 # 第三方库（Git submodules）
│   ├── fmt/                 # 格式化库（module 支持）
│   └── nlohmann_json/       # JSON 库（module 支持）
├── src/                      # 源代码
│   ├── main.cpp             # 主程序（使用 import std）
│   ├── example_module.cppm  # 天干模块
│   ├── dizhi_module.cppm    # 地支模块
│   ├── ganzhi_module.cppm   # 干支模块
│   └── modules_demo.cpp     # 完整演示
├── include/                  # 公共头文件
├── CMakeLists.txt           # CMake 配置（启用 modules）
├── README.md                # 项目说明
├── MODULES_GUIDE.md         # Modules 详细指南
└── BUILD.md                 # 构建指南
```

## 🔧 CMake 配置要点

项目的 `CMakeLists.txt` 关键配置：

```cmake
cmake_minimum_required(VERSION 3.30)

# 启用 import std 支持（基于 CMake 官方文档）
set(CMAKE_EXPERIMENTAL_CXX_IMPORT_STD
    "0e5b6991-d74f-4b3d-a41c-cf096e0b2508")

# C++23 标准
set(CMAKE_CXX_STANDARD 23)
set(CMAKE_CXX_SCAN_FOR_MODULES ON)

# 启用第三方库 modules
set(FMT_MODULE ON CACHE BOOL "Build fmt as a C++ module" FORCE)
set(NLOHMANN_JSON_BUILD_MODULES ON CACHE BOOL "" FORCE)

# 目标属性
set_target_properties(${PROJECT_NAME} PROPERTIES
    CXX_MODULE_STD ON  # 启用 import std
    CXX_SCAN_FOR_MODULES ON
)
```

参考：[CMake CXX_MODULE_STD 文档](https://cmake.org/cmake/help/latest/prop_tgt/CXX_MODULE_STD.html)

## 📖 运行示例

运行程序将展示：

```
===========================================
    欢迎使用 ZhouYiLab - 周易实验室
===========================================

项目信息:
{
  "name": "ZhouYiLab",
  "version": "1.0.0",
  "description": "传统文化算法库",
  ...
}

天干: 甲 乙 丙 丁 戊 己 庚 辛 壬 癸
地支: 子 丑 寅 卯 辰 巳 午 未 申 酉 戌 亥

公历 2025 年对应: 乙巳 年

使用 C++23 Modules 计算:
干支: 乙巳
天干: 乙 (五行: 木, 阴)
地支: 巳 (生肖: 蛇, 五行: 火, 阴)
时辰: 09-11时
纳音五行: 覆灯火

运行 modules 完整演示？ (y/n):
```

使用 `--demo` 参数查看完整的六十甲子表和详细信息。

## 🐛 常见问题

### CMake 版本过低

```bash
# 下载最新 CMake
wget https://github.com/Kitware/CMake/releases/download/v3.30.0/cmake-3.30.0-linux-x86_64.sh
sh cmake-3.30.0-linux-x86_64.sh --prefix=/usr/local --skip-license
```

### 编译器版本不够

```bash
# Ubuntu 安装 GCC 14
sudo add-apt-repository ppa:ubuntu-toolchain-r/test
sudo apt update
sudo apt install gcc-14 g++-14

# 指定编译器
cmake -B build -DCMAKE_CXX_COMPILER=g++-14
```

### 子模块未初始化

```bash
git submodule update --init --recursive
```

## 📚 进一步阅读

- [MODULES_GUIDE.md](MODULES_GUIDE.md) - C++23 Modules 详细指南
- [BUILD.md](BUILD.md) - 完整构建文档
- [README.md](README.md) - 项目总览

## 🤝 贡献

欢迎提交 Issue 和 Pull Request！

## 📄 许可证

MIT License - 详见 [LICENSE](LICENSE)

---

**⭐ 如果这个项目对你有帮助，请给个 Star！**

