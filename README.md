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

#### 核心规范
- **强制 C++23**：所有代码必须符合 C++23 标准
- **模块优先**：除 `main.cpp` 外，所有源文件必须使用 `.cppm` 扩展名
- **导入顺序规范**：第三方库模块 → 自定义模块 → `import std;`（最后）

#### 算法规范 ⭐
- **优先使用 Ranges**：所有容器操作优先使用 `std::ranges` 算法
  - ✅ `std::ranges::transform`, `std::ranges::filter`, `std::ranges::for_each`
  - ✅ `std::ranges::find_if`, `std::ranges::count_if`, `std::ranges::any_of`
  - ❌ 避免传统的 `std::transform`, `std::find` 等算法
  - ❌ 减少手写 `for` 循环（除非必要）

#### 输出规范 ⭐
- **统一使用 std::println**：禁止使用其他输出方式
  - ✅ `std::println("{}", value);`
  - ✅ `std::print("{}", value);` (不换行时使用)
  - ❌ `std::cout`, `printf`, `fmt::print` 等均禁止

#### 错误处理规范 ⭐
- **优先使用 std::expected**：可恢复的错误使用 `std::expected<T, E>`
  - ✅ 函数返回值可能失败时使用 `std::expected`
  - ✅ 可选值使用 `std::optional<T>`
  - ❌ 避免使用异常（除非必要）
  - ❌ 避免返回错误码或 `nullptr`

```cpp
// ✅ 正确：使用 std::expected
auto parse_number(std::string_view str) -> std::expected<int, std::string> {
    // ... 解析逻辑
    if (failed) {
        return std::unexpected("Parse error");
    }
    return result;
}

// ✅ 正确：使用 std::optional
auto find_user(int id) -> std::optional<User> {
    // ... 查找逻辑
    return user; // 或 std::nullopt
}
```

## 第三方库

本项目使用以下第三方库（通过 Git 子模块管理）：

- [fmt](https://github.com/fmtlib/fmt) - 现代化的格式化库，支持彩色输出
- [magic_enum](https://github.com/Neargye/magic_enum) - 编译期枚举反射，零开销
- [nlohmann/json](https://github.com/nlohmann/json) - JSON 序列化/反序列化库
- [tyme4cpp](https://github.com/6tail/tyme4cpp) - 强大的日历工具库，支持农历、干支、节气等

fmt、magic_enum、nlohmann_json 支持 C++23 Modules，可直接使用 `import` 导入。tyme4cpp 通过自定义模块 `ZhouYi.LunarCalendar` 封装。

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
│   ├── magic_enum/             # 枚举反射库（支持 import magic_enum;）
│   ├── nlohmann_json/          # JSON 库（支持 import nlohmann.json;）
│   └── tyme4cpp/               # 农历日历库（通过模块包装）
├── cmake/                       # CMake 工具链配置
│   ├── clang.toolchain.cmake   # Clang 工具链
│   ├── gcc.toolchain.cmake     # GCC 工具链
│   └── vs.toolchain.cmake      # MSVC 工具链
├── src/                         # 源代码（纯模块化）
│   ├── main.cpp                # ⚠️ 唯一允许的 .cpp 文件
│   ├── example_module.cppm     # 示例：天干模块（含反射）
│   ├── dizhi_module.cppm       # 示例：地支模块（含反射）
│   ├── ganzhi.cppm             # 完整干支系统模块（核心）
│   ├── zh_mapper.cppm          # 中文映射辅助模块
│   ├── lunar_calendar.cppm     # 农历日历模块（封装 tyme4cpp）
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
  ├─→ import fmt;                    (第三方库：格式化输出)
  ├─→ import magic_enum;             (第三方库：枚举反射)
  ├─→ import nlohmann.json;          (第三方库：JSON)
  ├─→ import ZhouYi.TianGan;         (自定义模块：天干示例)
  ├─→ import ZhouYi.DiZhi;           (自定义模块：地支示例)
  ├─→ import ZhouYi.GanZhi;          (自定义模块：完整干支系统 ⭐核心)
  ├─→ import ZhouYi.LunarCalendar;   (自定义模块：农历日历)
  └─→ import std;                    (标准库，最后导入！)

ganzhi.cppm (GanZhi) ⭐核心干支模块
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

example_module.cppm (TianGan)
  ├─→ import magic_enum;             (反射支持)
  └─→ import std;                    (标准库，最后导入)

dizhi_module.cppm (DiZhi)
  ├─→ import magic_enum;             (反射支持)
  └─→ import std;                    (标准库，最后导入)

zh_mapper.cppm (ZhMapper)
  ├─→ import magic_enum;             (基于 magic_enum 实现)
  └─→ import std;                    (标准库，最后导入)

lunar_calendar.cppm (LunarCalendar)
  ├─→ #include <tyme.h>              (封装 tyme4cpp 库)
  ├─→ import magic_enum;             (反射支持)
  └─→ import std;                    (标准库，最后导入)
```

## 📋 开发规范

### 🔒 强制性规范

#### 1️⃣ 模块导入顺序（重要！）

**必须严格按照以下顺序导入模块，避免符号冲突：**

```cpp
// ✅ 正确的导入顺序
// 第一步：导入第三方库模块（如果需要）
import magic_enum;       // 枚举反射库
import nlohmann.json;    // JSON 库

// 第二步：导入自定义业务模块
import ZhouYi.TianGan;
import ZhouYi.DiZhi;

// 第三步：最后导入标准库模块（必须最后）
import std;

int main() {
    // ✅ 使用 std::println 进行输出
    std::println("Hello, ZhouYiLab!");
    
    // ✅ 使用 ranges 算法
    auto numbers = std::vector{1, 2, 3, 4, 5};
    auto sum = std::ranges::fold_left(numbers, 0, std::plus{});
    std::println("Sum: {}", sum);
    
    return 0;
}
```

**❌ 错误示例：**
```cpp
// ❌ 错误：std 在前会导致符号冲突
import std;
import nlohmann.json;  // 可能导致编译错误
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
// ✅ 正确：使用 std::println
std::println("Hello, {}!", name);
std::print("Processing... ");  // 不换行
std::println("Done!");

// ❌ 错误：禁止使用其他输出方式
std::cout << "Hello" << std::endl;    // 不允许
fmt::print("Hello\n");                 // 不允许  
printf("Hello\n");                     // 不允许
```

#### 4️⃣ Ranges 算法优先

```cpp
// ✅ 正确：使用 ranges 算法
auto numbers = std::vector{1, 2, 3, 4, 5};

// 过滤偶数
auto evens = numbers 
    | std::views::filter([](int n) { return n % 2 == 0; })
    | std::ranges::to<std::vector>();

// 转换并累加
auto sum = std::ranges::fold_left(
    numbers | std::views::transform([](int n) { return n * 2; }),
    0, std::plus{}
);

// 查找元素
auto it = std::ranges::find_if(numbers, [](int n) { return n > 3; });

// ❌ 避免：传统循环和算法
for (auto& n : numbers) { /* ... */ }  // 尽量避免
std::find(numbers.begin(), numbers.end(), 3);  // 不推荐
```

#### 5️⃣ C++23 标准强制

所有代码必须使用 C++23 特性：
- ✅ `std::expected`、`std::optional`、`std::mdspan`
- ✅ Ranges 和 Views（`std::ranges`、`std::views`）
- ✅ `std::print` / `std::println`
- ✅ 概念和约束（`concept`、`requires`）

### 📝 创建新模块示例

**1. 创建模块文件**：`src/example.cppm`

```cpp
// 导入标准库（最后）
import std;

// 导出模块
export module ZhouYi.Example;

// 导出的函数
export namespace ZhouYi {
    void hello() {
        std::println("Hello from Example module!");
    }
    
    auto get_data() -> std::vector<int> {
        return {1, 2, 3, 4, 5};
    }
    
    // 使用 std::expected 处理错误
    auto parse_int(std::string_view str) -> std::expected<int, std::string> {
        try {
            return std::stoi(std::string(str));
        } catch (...) {
            return std::unexpected(std::format("Failed to parse: {}", str));
        }
    }
    
    // 使用 ranges 算法
    auto filter_evens(const std::vector<int>& nums) -> std::vector<int> {
        return nums 
            | std::views::filter([](int n) { return n % 2 == 0; })
            | std::ranges::to<std::vector>();
    }
}
```

**2. 在 `main.cpp` 中使用**：

```cpp
import ZhouYi.Example;  // 导入自定义模块
import std;

int main() {
    ZhouYi::hello();
    
    auto data = ZhouYi::get_data();
    std::println("Data size: {}", data.size());
    
    // 使用 std::expected
    auto result = ZhouYi::parse_int("123");
    if (result) {
        std::println("Parsed: {}", *result);
    } else {
        std::println("Error: {}", result.error());
    }
    
    // 使用 ranges
    auto evens = ZhouYi::filter_evens(data);
    std::println("Evens: {}", evens);
    
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

**Q: 如何使用 magic_enum 进行枚举反射？**

A: magic_enum 提供编译期零开销的枚举反射功能：

```cpp
import magic_enum;
import std;

enum class Color { Red, Green, Blue };

// 枚举 → 字符串
auto name = magic_enum::enum_name(Color::Red);  // "Red"

// 字符串 → 枚举
auto color = magic_enum::enum_cast<Color>("Green");  // std::optional<Color>

// 获取所有枚举值
constexpr auto values = magic_enum::enum_values<Color>();

// 获取枚举数量
constexpr auto count = magic_enum::enum_count<Color>();  // 3

// 枚举迭代（使用 ranges）
std::ranges::for_each(
    magic_enum::enum_values<Color>(),
    [](auto value) {
        std::println("{}", magic_enum::enum_name(value));
    }
);
```

**Q: 如何实现枚举到中文的映射？**

A: 使用命名空间函数封装中文映射：

```cpp
// 在模块中定义
export namespace MyEnumMapper {
    constexpr auto to_zh(MyEnum value) -> std::string_view {
        constexpr std::array<std::string_view, N> names = {"中文1", "中文2"};
        return names[magic_enum::enum_integer(value)];
    }
    
    constexpr auto from_zh(std::string_view zh_name) -> std::optional<MyEnum> {
        // 遍历查找...
    }
}

// 使用
auto zh_name = MyEnumMapper::to_zh(value);
```

参考 `src/example_module.cppm` 和 `src/dizhi_module.cppm` 中的实现。

**Q: 如何使用农历日历功能？**

A: 使用 `ZhouYi.LunarCalendar` 模块进行公历与农历互转：

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
    // 处理每个节气...
}

// 六十甲子
auto cycles = ZhouYi::Lunar::GanZhi::get_sixty_cycles();
```

基于 [tyme4cpp](https://github.com/6tail/tyme4cpp) 库实现。

**Q: 如何使用完整的干支系统？**

A: 使用 `ZhouYi.GanZhi` 模块，这是从旧代码剥离整合的核心模块：

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

这个模块整合了旧代码中的所有天干地支核心功能，包括：
- ✅ 完整的枚举定义和运算符重载
- ✅ 五行阴阳属性查询
- ✅ 生克制化关系判断
- ✅ 冲合刑害完整判断
- ✅ 六十甲子系统（含纳音）
- ✅ 贵人寄宫藏干等高级功能

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

### 📚 官方文档
- [C++23 Modules 官方文档](https://en.cppreference.com/w/cpp/language/modules)
- [CMake Modules 支持](https://cmake.org/cmake/help/latest/manual/cmake-cxxmodules.7.html)

### 🛠️ 第三方库
- [fmt 库文档](https://fmt.dev/) - 现代格式化输出
- [magic_enum 库文档](https://github.com/Neargye/magic_enum) - 编译期枚举反射
- [nlohmann/json 文档](https://json.nlohmann.me/) - JSON 序列化
- [tyme4cpp 库文档](https://6tail.cn/tyme.html) - 强大的日历工具库

### 📖 学习资源
- [magic_enum 示例](https://github.com/Neargye/magic_enum/tree/master/example)
- [C++23 特性概览](https://en.cppreference.com/w/cpp/23)

---

**⚡ 技术栈**: C++23 | CMake 4.1.2 | Git Submodules | Pure Modules Design | Compile-time Reflection

