# ZhouYiLab - 周易实验室

> **一个基于现代 C++23 Modules 的传统文化算法库，使用纯模块化设计实现**

[![C++23](https://img.shields.io/badge/C%2B%2B-23-blue.svg)](https://en.cppreference.com/w/cpp/23)
[![CMake](https://img.shields.io/badge/CMake-4.1.2+-green.svg)](https://cmake.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

---

## 📖 项目简介

ZhouYiLab 致力于用现代化的编程方式实现和研究传统周易文化相关的算法，包括：

- 🔮 **大六壬** - 古代占卜术数之一
- 📿 **六爻** - 周易卦象推演系统
- ⭐ **紫微斗数** - 传统命理学
- 🎴 **八字排盘** - 四柱命理计算
- 📅 **农历历法** - 公历农历互转、节气、干支等

---

## ✨ 核心特性

### 🚀 完全模块化设计

- ✅ **零传统头文件**：所有依赖使用 `import` 语句
- ✅ **C++23 标准库模块**：`import std;`
- ✅ **第三方库模块化**：
  - `import fmt;` - 现代格式化输出
  - `import magic_enum;` - 编译期枚举反射
  - `import nlohmann.json;` - JSON 序列化
- ✅ **自定义模块系统**：所有业务代码使用 `.cppm` 模块接口文件

### 🏗️ 现代构建系统

- **CMake 4.1.2+**：完整支持 C++ Modules
  - `FILE_SET CXX_MODULES` 模块管理
  - `CXX_MODULE_GENERATION_MODE SEPARATE` 编译优化
  - `CMAKE_EXPERIMENTAL_CXX_IMPORT_STD` 标准库模块支持
- **Git Submodules**：精确管理第三方依赖版本
- **跨平台构建**：统一的构建体验（Windows、Linux、macOS）

### 🎯 严格的代码规范

- **强制 C++23**：充分利用最新语言特性
- **模块优先**：纯模块化架构，告别传统头文件
- **Ranges 优先**：使用现代标准库算法
- **std::expected**：优雅的错误处理
- **std::println**：统一的输出接口

---

## 🔧 构建要求

### 必需工具

- **CMake 4.1.2+**（推荐）或 **CMake 3.30+**（最低兼容版本）
- 支持 C++23 modules 和 `import std;` 的编译器：
  - **GCC 14+** ✅ 完全支持
  - **Clang 18+** ✅ 完全支持
  - **MSVC 2022 17.10+** ⚠️ 实验性支持（需要最新版本）

### 第三方依赖

本项目使用以下第三方库（通过 Git 子模块管理）：

| 库 | 版本 | 功能 | 模块支持 |
|---|---|---|---|
| [fmt](https://github.com/fmtlib/fmt) | Latest | 现代化格式化输出、彩色输出 | ✅ `import fmt;` |
| [magic_enum](https://github.com/Neargye/magic_enum) | Latest | 编译期枚举反射，零开销 | ✅ `import magic_enum;` |
| [nlohmann/json](https://github.com/nlohmann/json) | Latest | JSON 序列化/反序列化 | ✅ `import nlohmann.json;` |
| [tyme4cpp](https://github.com/6tail/tyme4cpp) | Latest | 强大的日历工具库，支持农历、干支、节气 | 🔄 通过 `ZhouYi.LunarCalendar` 模块封装 |

---

## 🚀 快速开始

### 1️⃣ 克隆项目

```bash
# 克隆项目（包含子模块）
git clone --recursive https://github.com/banderzhm/ZhouYiLab.git
cd ZhouYiLab
```

如果已经克隆但没有子模块：

```bash
git submodule update --init --recursive
```

### 2️⃣ 构建项目

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

### 3️⃣ 运行示例

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

---

## 📁 项目结构

```
ZhouYiLab/
├── 3rdparty/                    # 第三方库（Git 子模块）
│   ├── fmt/                    # 格式化库（支持 import fmt;）
│   ├── magic_enum/             # 枚举反射库（支持 import magic_enum;）
│   ├── nlohmann_json/          # JSON 库（支持 import nlohmann.json;）
│   └── tyme4cpp/               # 农历日历库（通过模块包装）
├── cmake/                       # CMake 工具链配置
│   ├── clang.toolchain.cmake   # Clang 工具链
│   ├── gcc.toolchain.cmake     # GCC 工具链
│   └── vs.toolchain.cmake      # MSVC 工具链
├── src/                         # 源代码（纯模块化）
│   ├── common/                 # 通用模块
│   │   ├── tian_gan/          # 天干地支系统
│   │   └── tyme/              # 农历历法封装
│   ├── ba_zi/                  # 八字模块
│   ├── da_liu_ren/            # 大六壬模块
│   ├── liu_yao/               # 六爻模块
│   ├── zi_wei/                # 紫微斗数模块
│   └── main.cpp               # ⚠️ 唯一允许的 .cpp 文件
├── tests/                       # 单元测试
├── docs/                        # 文档
├── CMakeLists.txt              # CMake 主配置
├── .gitmodules                 # Git 子模块配置
└── README.md                   # 本文档
```

### 模块依赖关系

```
main.cpp
  ├─→ import fmt;                    (第三方库：格式化输出)
  ├─→ import magic_enum;             (第三方库：枚举反射)
  ├─→ import nlohmann.json;          (第三方库：JSON)
  ├─→ import ZhouYi.TianGan;         (自定义模块：天干)
  ├─→ import ZhouYi.DiZhi;           (自定义模块：地支)
  ├─→ import ZhouYi.GanZhi;          (自定义模块：完整干支系统 ⭐核心)
  ├─→ import ZhouYi.LunarCalendar;   (自定义模块：农历日历)
  └─→ import std;                    (标准库，最后导入！)

ZhouYi.GanZhi ⭐核心干支模块
  ├─→ import magic_enum;             (反射支持)
  └─→ import std;                    (标准库，最后导入)
  功能：
    - 天干地支枚举定义
    - 五行阴阳属性
    - 生克制化关系
    - 冲合刑害判断
    - 六十甲子系统
    - 纳音五行
    - 贵人寄宫等
```

---

## 💡 使用示例

### 🔮 大六壬排盘

```cpp
import ZhouYi.DaLiuRen;
import ZhouYi.DaLiuRen.Controller;
import std;

using namespace ZhouYi::DaLiuRen;

// 方式1：使用排盘引擎（非交互式）
auto result = DaLiuRenEngine::pai_pan(2024, 10, 13, 14);  // 公历

// 方式2：从农历排盘
auto result_lunar = DaLiuRenEngine::pai_pan_lunar(2024, 9, 11, 7);

// 方式3：交互式排盘（控制台输入）
Controller::DaLiuRenController::interactive_pai_pan();

// 访问排盘结果
std::println("月将: {}", result.yue_jiang);
std::println("贵人: {}", result.gui_ren);

// 获取四课信息
const auto& si_ke = result.si_ke;
std::println("第一课: {}", si_ke.first.to_string());
std::println("第二课: {}", si_ke.second.to_string());
std::println("第三课: {}", si_ke.third.to_string());
std::println("第四课: {}", si_ke.fourth.to_string());

// 获取三传信息
const auto& san_chuan = result.san_chuan;
std::println("初传: {}", san_chuan.get_chu_chuan());
std::println("中传: {}", san_chuan.get_zhong_chuan());
std::println("末传: {}", san_chuan.get_mo_chuan());
std::println("课式: {}", san_chuan.get_ke_shi());

// 获取天地盘信息
const auto& tian_di_pan = result.tian_di_pan;
auto tian_pan = tian_di_pan.get_tian_pan();
auto di_pan = tian_di_pan.get_di_pan();

// 显示格式化结果
Controller::DaLiuRenController::display_result_detailed(result);

// 导出为 JSON
nlohmann::json json_result = result.to_json();
std::println("{}", json_result.dump(2));
```

大六壬模块功能特性：
- ✅ 完整的天地盘系统（月将、贵人、十二神将）
- ✅ 四课计算（干上神、支上神、阴神、阳神）
- ✅ 三传取法（贼克、比用、涉害、遥克、昴星、别责等）
- ✅ 神煞系统（完整的神煞判定）
- ✅ 卦体判断（蒿矢、重审、元首等）
- ✅ 课式分类（九宗门、十二神将课等）

### 📿 六爻排盘

```cpp
import ZhouYi.LiuYaoController;
import ZhouYi.BaZiBase;
import std;

using namespace ZhouYi::LiuYaoController;
using namespace ZhouYi::BaZiBase;

// 创建八字（用于计算旺衰、神煞）
auto bazi = BaZi::from_solar(2024, 10, 13, 14, 30);

// 方式1：直接使用主卦代码排盘
// 主卦代码："010101"（6位二进制，'0'=阴爻，'1'=阳爻，从下到上）
// 动爻：第3爻和第5爻动
auto result = calculate_liu_yao("010101", bazi, {3, 5});

// 方式2：从爻辞生成主卦代码
std::vector<int> yao_ci = {7, 8, 6, 9, 7, 8};  // 从下到上
// 6=老阴(动), 7=少阳, 8=少阴, 9=老阳(动)
std::vector<int> changing_lines;
auto code = yao_ci_to_hexagram_code(yao_ci, changing_lines);
auto result2 = calculate_liu_yao(code, bazi, changing_lines);

// 方式3：从数字卦生成（金钱卦、梅花易数等）
std::vector<int> numbers = {3, 2, 3, 1, 2, 3};  // 从下到上
auto code3 = numbers_to_hexagram_code(numbers, true);  // true=奇数为阳爻
auto result3 = calculate_liu_yao(code3, bazi);

// 访问排盘结果
std::println("卦象信息:");
for (const auto& yao : result.yao_list) {
    std::println("第{}爻:", yao.position);
    std::println("  本卦: {} {} {}", 
        yao.mainRelative,           // 六亲
        yao.mainPillar.to_string(), // 纳甲干支
        yao.mainElement);           // 五行
    
    if (yao.isChanging) {
        std::println("  变爻: {} {} {}", 
            yao.changedRelative,
            yao.changedPillar.to_string(),
            yao.changedElement);
    }
    
    std::println("  六神: {}", yao.spirit);
    std::println("  旺衰: {}", yao.wangShuai);
    std::println("  世应: {}", yao.shiYingMark);
}

// 获取卦象基本信息（不进行完整排盘）
auto hexagram_info = get_hexagram_info("010101");
std::println("卦名: {}", hexagram_info.name);
std::println("卦义: {}", hexagram_info.meaning);
std::println("五行: {}", hexagram_info.fiveElement);
std::println("世爻: {}, 应爻: {}", 
    hexagram_info.shiYaoPosition, 
    hexagram_info.yingYaoPosition);

// 批量排盘
std::vector<std::tuple<std::string, BaZi, std::vector<int>>> batch_requests = {
    {"010101", bazi, {3, 5}},
    {"111000", bazi, {1}},
    {"000111", bazi, {}}
};
auto batch_results = batch_calculate_liu_yao(batch_requests);

// 导出为 JSON
std::println("{}", result.json_data.dump(2));
```

六爻模块功能特性：
- ✅ 完整的纳甲装卦（本卦、变卦、伏神）
- ✅ 六亲关系计算（兄弟、子孙、妻财、官鬼、父母）
- ✅ 六神配置（青龙、朱雀、勾陈、螣蛇、白虎、玄武）
- ✅ 世应标记（自动识别世爻、应爻位置）
- ✅ 旺衰判定（基于月令五行）
- ✅ 神煞系统（日禄、贵人、驿马、桃花等）
- ✅ 多种起卦方式（二进制码、爻辞、数字卦）

### 📅 农历日历功能

```cpp
import ZhouYi.LunarCalendar;
import std;

// 从公历创建
auto solar = ZhouYi::Lunar::SolarDate::from_ymd(1986, 5, 29);
auto lunar = solar.to_lunar();

// 获取农历信息
std::println("农历: {}", lunar.to_string());        // 农历甲寅年四月廿一
std::println("年干支: {}", lunar.get_year_gan_zhi()); // 丙寅
std::println("生肖: {}", lunar.get_zodiac());        // 虎

// 从农历创建
auto lunar2 = ZhouYi::Lunar::LunarDate::from_lunar(2025, 1, 1);
auto [y, m, d] = lunar2.to_solar();  // 转换为公历

// 二十四节气
auto terms = ZhouYi::Lunar::SolarTerm::get_terms_of_year(2025);
for (const auto& [name, date] : terms) {
    std::println("{}: {}", name, date);
}
``` 

基于 [tyme4cpp](https://github.com/6tail/tyme4cpp) 库实现。

### 🎴 完整干支系统

```cpp
import ZhouYi.GanZhi;
using namespace ZhouYi::GanZhi;

// 定义天干地支
auto jia = TianGan::Jia;
auto zi = DiZhi::Zi;

// 获取中文名称
auto name = Mapper::to_zh(jia);  // "甲"

// 获取五行属性
auto wu_xing = get_wu_xing(jia);  // WuXing::Mu (木)
auto yin_yang = get_yin_yang(jia); // YinYang::Yang (阳)

// 判断地支关系
bool chong = is_chong(DiZhi::Zi, DiZhi::Wu);  // 子午相冲
bool he = is_he(DiZhi::Zi, DiZhi::Chou);      // 子丑相合
bool xing = is_xing(DiZhi::Zi, DiZhi::Mao);   // 子卯相刑

// 地支三合
auto [is_san_he, he_wx] = is_san_he(
    DiZhi::Shen, DiZhi::Zi, DiZhi::Chen
);  // 申子辰合水局

// 五行生克
bool sheng = wu_xing_sheng(WuXing::Mu, WuXing::Huo);  // 木生火
bool ke = wu_xing_ke(WuXing::Mu, WuXing::Tu);          // 木克土

// 六十甲子
auto jz = LiuShiJiaZi::from_index(0);  // 甲子
auto na_yin = jz.get_na_yin();          // 获取纳音五行

// 天干贵人
auto gui = get_gui_ren(TianGan::Jia, true);  // 甲日阳贵人

// 天干寄宫
auto gong = get_ji_gong(TianGan::Jia);  // 甲寄寅宫

// 地支藏干
auto cang = get_cang_gan(DiZhi::Yin);   // 寅藏甲丙戊
```

完整的模块功能包括：
- ✅ 完整的枚举定义和运算符重载
- ✅ 五行阴阳属性查询
- ✅ 生克制化关系判断
- ✅ 冲合刑害完整判断
- ✅ 六十甲子系统（含纳音）
- ✅ 贵人寄宫藏干等高级功能

---

## 📋 开发规范

### 1️⃣ C++23 标准强制

所有代码必须使用 C++23 特性：
- ✅ `std::expected`、`std::optional`、`std::mdspan`
- ✅ Ranges 和 Views（`std::ranges`、`std::views`）
- ✅ `std::print` / `std::println`
- ✅ 概念和约束（`concept`、`requires`）

### 2️⃣ 模块化规范

- **强制使用模块**：除 `main.cpp` 外，所有源文件必须使用 `.cppm` 扩展名
- **导入顺序规范**：
  ```cpp
  // 1. 第三方库模块
  import fmt;
  import magic_enum;
  import nlohmann.json;
  
  // 2. 自定义模块
  import ZhouYi.GanZhi;
  import ZhouYi.LunarCalendar;
  
  // 3. 标准库（必须最后！）
  import std;
  ```
- **禁止混用头文件**：本项目采用纯模块化设计

### 3️⃣ 算法规范

**优先使用 Ranges**：所有容器操作优先使用 `std::ranges` 算法

✅ **推荐**：
```cpp
std::ranges::transform(vec, std::back_inserter(result), fn);
std::ranges::filter(vec, predicate);
std::ranges::for_each(vec, action);
std::ranges::find_if(vec, condition);
std::ranges::count_if(vec, predicate);
std::ranges::any_of(vec, predicate);
```

❌ **避免**：
```cpp
std::transform(vec.begin(), vec.end(), ...);  // 传统算法
for (auto& item : vec) { ... }                // 手写循环（除非必要）
```

### 4️⃣ 输出规范

**统一使用 std::println**：禁止使用其他输出方式

✅ **推荐**：
```cpp
std::println("{}", value);      // 带换行
std::print("{}", value);        // 不换行
```

❌ **禁止**：
```cpp
std::cout << value << std::endl;  // 禁止
printf("%d", value);              // 禁止
fmt::print("{}", value);          // 禁止
```

### 5️⃣ 错误处理规范

**优先使用 std::expected**：可恢复的错误使用 `std::expected<T, E>`

✅ **推荐**：
```cpp
// 函数返回值可能失败时
std::expected<Result, Error> process_data() {
    if (error_condition) {
        return std::unexpected(Error{"错误信息"});
    }
    return Result{...};
}

// 可选值
std::optional<Value> find_value() {
    if (found) return value;
    return std::nullopt;
}
```

❌ **避免**：
```cpp
Result* process_data();           // 返回 nullptr
int process_data(Result& out);    // 返回错误码
throw std::exception("error");    // 使用异常（除非必要）
```

---

## 🐛 常见问题

<details>
<summary><b>Q: 为什么 <code>import std;</code> 必须放在最后？</b></summary>

A: 标准库模块会导出大量符号，如果先导入 `std`，可能与第三方库的内部实现产生冲突。特别是 `nlohmann::json` 内部使用了传统头文件，会导致符号重定义错误。
</details>

<details>
<summary><b>Q: 可以混用 <code>.h</code> 头文件和模块吗？</b></summary>

A: 不可以。本项目采用纯模块化设计，所有代码必须使用模块系统。这确保了编译性能和符号隔离。
</details>

<details>
<summary><b>Q: MSVC 提示 <code>warning C5050</code> 怎么办？</b></summary>

A: 这是 MSVC 的已知警告，已在 `CMakeLists.txt` 中使用 `/wd5050` 抑制，不影响编译。
</details>

<details>
<summary><b>Q: 编译时间太长怎么办？</b></summary>

A: 这是 C++ Modules 的当前限制。建议：
- 使用增量编译（默认已启用）
- 使用 Ninja 构建系统（比 Make 快）
- 启用编译缓存（ccache/sccache）
- 使用多核编译 `cmake --build build -j$(nproc)`
</details>

---

## 🔧 项目维护

### 更新第三方库

```bash
# 更新所有子模块到最新版本
git submodule update --remote --merge

# 更新指定子模块
git submodule update --remote --merge 3rdparty/fmt
```

### 清理构建

```bash
# 完全清理
rm -rf build/

# 重新构建
cmake -B build -DCMAKE_BUILD_TYPE=Release
cmake --build build
```

---

## 🤝 贡献指南

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

---

## 📚 相关资源

### 官方文档
- [C++23 Modules 官方文档](https://en.cppreference.com/w/cpp/language/modules)
- [CMake Modules 支持](https://cmake.org/cmake/help/latest/manual/cmake-cxxmodules.7.html)
- [C++23 特性概览](https://en.cppreference.com/w/cpp/23)

### 第三方库文档
- [fmt 库文档](https://fmt.dev/) - 现代格式化输出
- [magic_enum 库文档](https://github.com/Neargye/magic_enum) - 编译期枚举反射
- [nlohmann/json 文档](https://json.nlohmann.me/) - JSON 序列化
- [tyme4cpp 库文档](https://6tail.cn/tyme.html) - 强大的日历工具库

### 学习资源
- [magic_enum 示例](https://github.com/Neargye/magic_enum/tree/master/example)
- [C++23 Ranges 教程](https://en.cppreference.com/w/cpp/ranges)

---

## 📄 许可证

本项目采用 MIT 许可证 - 详见 [LICENSE](LICENSE) 文件

---

<p align="center">
  <b>⚡ 技术栈</b><br>
  C++23 | CMake 4.1.2 | Git Submodules | Pure Modules Design | Compile-time Reflection
</p>

<p align="center">
  <i>打造现代化的传统文化算法库 🌟</i>
</p>
