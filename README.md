# ZhouYiLab - 周易实验室

一个基于现代 C++23 的传统文化算法库，使用模块化设计实现。

## 项目简介

ZhouYiLab 致力于用现代化的编程方式实现和研究传统周易文化相关的算法，包括但不限于：

- 大六壬
- 六爻
- 紫微斗数
- 八字排盘
- 农历历法

## 技术特性

- **C++23 Modules**: 使用最新的 C++23 模块系统和 `import std;`
- **现代 CMake**: 基于 CMake 3.30+ 构建系统，完整支持 C++ modules
- **Git Submodules**: 使用子模块管理第三方依赖
- **跨平台支持**: Windows、Linux、macOS
- **标准库模块化**: 启用 `CMAKE_EXPERIMENTAL_CXX_IMPORT_STD` 支持

## 第三方库

本项目使用以下第三方库（通过 Git 子模块管理）：

- [fmt](https://github.com/fmtlib/fmt) - 现代化的格式化库
- [nlohmann/json](https://github.com/nlohmann/json) - JSON 处理库

## 构建要求

- **CMake 4.1.2** 或更高版本（完整支持 C++ modules 和 `import std;`）
  - 最低兼容版本：CMake 3.30（部分特性）
- 支持 C++23 modules 和 `import std;` 的编译器：
  - **GCC 14+** (完全支持)
  - **Clang 18+** (完全支持)
  - **MSVC 2022 17.10+** (实验性支持，需要最新版本)

## 快速开始

### 1. 克隆项目（包含子模块）

```bash
git clone --recursive https://github.com/banderzhm/ZhouYiLab.git
cd ZhouYiLab
```

如果已经克隆但没有子模块：

```bash
git submodule update --init --recursive
```

### 2. 构建项目

#### Windows (使用 Visual Studio)

```bash
cmake -B build -G "Visual Studio 17 2022"
cmake --build build --config Release
```

#### Linux / macOS

```bash
cmake -B build -DCMAKE_BUILD_TYPE=Release
cmake --build build
```

### 3. 运行

```bash
# 基本运行
./build/bin/ZhouYiLab

# 运行完整的 C++23 modules 演示
./build/bin/ZhouYiLab --demo
```

程序将展示：
- 使用 `import std;` 的现代 C++ 代码
- 自定义模块（天干、地支、干支）
- 六十甲子表
- 纳音五行计算

## 项目结构

```
ZhouYiLab/
├── 3rdparty/              # 第三方库（Git 子模块）
│   ├── fmt/              # 格式化库
│   └── nlohmann_json/    # JSON 库
├── include/              # 公共头文件
├── src/                  # 源代码
│   └── main.cpp         # 主程序入口
├── CMakeLists.txt       # CMake 配置文件
├── .gitignore           # Git 忽略文件
├── .gitmodules          # Git 子模块配置
└── README.md            # 项目说明
```

## 开发指南

### 添加新模块

1. 在 `src/` 目录下创建模块接口文件 (`.cppm` 或 `.ixx`)
2. 实现模块功能
3. 在 `CMakeLists.txt` 中添加源文件

### 更新子模块

```bash
git submodule update --remote --merge
```

## 许可证

[MIT License](LICENSE)

## 贡献

欢迎贡献代码！请先阅读贡献指南。

## 联系方式

- GitHub: [banderzhm](https://github.com/banderzhm)

## 致谢

感谢所有为传统文化传承和现代技术发展做出贡献的人们。

