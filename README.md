# ZhouYiLab - 周易实验室

> **一个基于现代 C++23 Modules 的传统文化算法库，使用纯模块化设计实现**

## 项目简介

ZhouYiLab 致力于用现代化的编程方式实现和研究传统周易文化相关的算法，包括但不限于：

- 🔮 大六壬
- 📿 六爻
- ⭐ 紫微斗数
- 🎴 八字排盘
- 📅 农历历法

## 核心技术特性

### 🚀 完全模块化设计
- ✅ **零传统头文件**：所有依赖使用 `import` 语句
- ✅ **C++23 标准库模块**：`import std;`
- ✅ **第三方库模块化**：`import fmt;`、`import nlohmann.json;`
- ✅ **自定义模块系统**：所有业务代码使用 `.cppm` 模块接口文件

### 🏗️ 现代构建系统
- **CMake 4.1.2+**：完整支持 C++ Modules
  - `FILE_SET CXX_MODULES` 模块管理
  - `CXX_MODULE_GENERATION_MODE SEPARATE` 编译优化
  - `CMAKE_EXPERIMENTAL_CXX_IMPORT_STD` 标准库模块支持
- **Git Submodules**：精确管理第三方依赖版本
- **跨平台构建**：统一的构建体验（Windows、Linux、macOS）

### 🎯 严格的代码规范
- **强制 C++23**：所有代码必须符合 C++23 标准
- **模块优先**：除 `main.cpp` 外，所有源文件必须使用 `.cppm` 扩展名
- **fmt 专用输出**：禁止使用 `std::cout`/`std::print`，统一使用 `fmt::print`
- **导入顺序规范**：第三方库模块 → 自定义模块 → `import std;`（最后）

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

## 📁 项目结构

```
ZhouYiLab/
├── 3rdparty/                    # 第三方库（Git 子模块）
│   ├── fmt/                    # 格式化库（支持 import fmt;）
│   └── nlohmann_json/          # JSON 库（支持 import nlohmann.json;）
├── cmake/                       # CMake 工具链配置
│   ├── clang.toolchain.cmake   # Clang 工具链
│   ├── gcc.toolchain.cmake     # GCC 工具链
│   └── vs.toolchain.cmake      # MSVC 工具链
├── src/                         # 源代码（纯模块化）
│   ├── main.cpp                # ⚠️ 唯一允许的 .cpp 文件
│   ├── example_module.cppm     # 示例：天干模块
│   ├── dizhi_module.cppm       # 示例：地支模块
│   └── *.cppm                  # 所有其他源文件必须是 .cppm
├── include/                     # 公共接口（可选，优先使用模块）
├── CMakeLists.txt              # CMake 主配置
├── .gitignore                  # Git 忽略规则
├── .gitmodules                 # Git 子模块配置
└── README.md                   # 本文档
```

### 📦 模块依赖关系

```
main.cpp
  ├─→ import fmt;                    (第三方库)
  ├─→ import nlohmann.json;          (第三方库)
  ├─→ import ZhouYi.TianGan;         (自定义模块)
  ├─→ import ZhouYi.DiZhi;           (自定义模块)
  └─→ import std;                    (标准库，最后导入！)

example_module.cppm (TianGan)
  ├─→ import fmt;
  └─→ import std;

dizhi_module.cppm (DiZhi)
  ├─→ import fmt;
  └─→ import std;
```

## 📋 开发规范

### 🔒 强制性规范

#### 1️⃣ 模块导入顺序（重要！）

**必须严格按照以下顺序导入模块，避免符号冲突：**

```cpp
// ✅ 正确的导入顺序
// 第一步：导入第三方库模块
import fmt;
import nlohmann.json;

// 第二步：导入自定义业务模块
import ZhouYi.TianGan;
import ZhouYi.DiZhi;

// 第三步：最后导入标准库模块（避免冲突）
import std;

int main() {
    // ⚠️ 只能使用 fmt 进行输出
    fmt::print("Hello, ZhouYiLab!\n");
    
    // ❌ 禁止使用标准库输出
    // std::cout << "No!" << std::endl;  // 编译错误
    // std::print("No!\n");              // 编译错误
    
    return 0;
}
```

**❌ 错误示例：**
```cpp
// ❌ 错误：std 在前会导致符号冲突
import std;
import fmt;  // 可能导致编译错误
```

#### 2️⃣ 文件扩展名规范

| 文件类型 | 扩展名 | 使用场景 | 是否允许 |
|---------|--------|---------|---------|
| 模块接口文件 | `.cppm` | 所有自定义模块 | ✅ **强制** |
| 主程序入口 | `.cpp` | 仅限 `main.cpp` | ✅ 允许 |
| 传统源文件 | `.cpp` | 其他源文件 | ❌ **禁止** |
| 传统头文件 | `.h` / `.hpp` | 任何场景 | ❌ **禁止** |

#### 3️⃣ 输出规范

```cpp
// ✅ 正确：使用 fmt::print
fmt::print("Hello, {}!\n", name);
fmt::print(fg(fmt::color::green), "Success!\n");

// ❌ 错误：禁止使用标准库输出
std::cout << "Hello" << std::endl;    // 不允许
std::print("Hello\n");                 // 不允许
printf("Hello\n");                     // 不允许
```

#### 4️⃣ C++23 标准强制

所有代码必须使用 C++23 特性：
- ✅ `std::print_format`、`std::expected`、`std::mdspan`
- ✅ Ranges 和 Views（`std::ranges`）
- ✅ 协程（`co_await`、`co_yield`）
- ✅ 概念和约束（`concept`、`requires`）

### 📝 创建新模块示例

**1. 创建模块文件**：`src/example.cppm`

```cpp
// 导入依赖（第三方库）
import fmt;

// 导入标准库（最后）
import std;

// 导出模块
export module ZhouYi.Example;

// 导出的函数
export namespace ZhouYi {
    void hello() {
        fmt::print(fg(fmt::color::cyan), "Hello from Example module!\n");
    }
    
    auto get_data() -> std::vector<int> {
        return {1, 2, 3, 4, 5};
    }
}
```

**2. 在 `main.cpp` 中使用**：

```cpp
import fmt;
import ZhouYi.Example;  // 导入自定义模块
import std;

int main() {
    ZhouYi::hello();
    auto data = ZhouYi::get_data();
    fmt::print("Data size: {}\n", data.size());
    return 0;
}
```

**3. CMake 自动处理**：

CMake 会自动扫描 `src/*.cppm` 文件，无需手动配置。

### 🔧 项目维护

#### 更新第三方库

```bash
# 更新所有子模块到最新版本
git submodule update --remote --merge

# 更新指定子模块
git submodule update --remote --merge 3rdparty/fmt
```

#### 清理构建

```bash
# 完全清理
rm -rf build/

# 重新构建
cmake -B build -DCMAKE_BUILD_TYPE=Release
cmake --build build
```

### 🐛 常见问题

**Q: 为什么 `import std;` 必须放在最后？**

A: 标准库模块会导出大量符号，如果先导入 `std`，可能与第三方库的内部实现产生冲突。特别是 `nlohmann::json` 内部使用了传统头文件，会导致符号重定义错误。

**Q: 可以混用 `.h` 头文件和模块吗？**

A: 不可以。本项目采用纯模块化设计，所有代码必须使用模块系统。这确保了编译性能和符号隔离。

**Q: MSVC 提示 `warning C5050` 怎么办？**

A: 这是 MSVC 的已知警告，已在 `CMakeLists.txt` 中使用 `/wd5050` 抑制，不影响编译。

### 🤝 贡献指南

欢迎贡献代码！请确保：

1. ✅ 遵守上述所有开发规范
2. ✅ 代码通过编译且无警告
3. ✅ 提交前运行格式化工具
4. ✅ 提供清晰的提交信息

```bash
# 提交代码模板
git add .
git commit -m "feat(module): 添加新的天干地支计算模块"
git push
```

## 📄 许可证

本项目采用 MIT 许可证 - 详见 [LICENSE](LICENSE) 文件

## 🔗 相关资源

- [C++23 Modules 官方文档](https://en.cppreference.com/w/cpp/language/modules)
- [CMake Modules 支持](https://cmake.org/cmake/help/latest/manual/cmake-cxxmodules.7.html)
- [fmt 库文档](https://fmt.dev/)
- [nlohmann/json 文档](https://json.nlohmann.me/)

---

**⚡ 技术栈**: C++23 | CMake 4.1.2 | Git Submodules | Pure Modules Design

