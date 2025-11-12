# 奇门遁甲排盘模块

## 概述

本模块实现了完整的奇门遁甲排盘算法，包括：

- **定局算法**：根据节气、日期确定阴阳遁、三元、局数
- **排盘算法**：生成地盘、天盘、人盘、神盘
- **分析工具**：提供吉凶分析和盘面查询功能

## 核心概念

### 九宫（Palace）

按照后天八卦方位排列：

```
西北(6)  北(1)  东北(8)
西(7)    中(5)  东(3)
西南(2)  南(9)  东南(4)
```

### 八门（Gate）

- **杜门**（巽四宫）：关闭、阻滞
- **景门**（离九宫）：光明、显露
- **死门**（坤二宫）：死亡、终结
- **伤门**（震三宫）：伤害、损失
- **惊门**（兑七宫）：惊吓、变化
- **生门**（艮八宫）：生长、生机
- **休门**（坎一宫）：休息、停止
- **开门**（乾六宫）：开放、展开

### 九星（Star）

- **天英**：吉星
- **天辅**：吉星
- **天冲**：中等
- **天芮**：中等
- **天柱**：中等
- **天任**：中等
- **天禽**：中等
- **天蓬**：凶星
- **天心**：中等

### 八神（Spirit）

- **直符**：主事
- **腾蛇**：变化
- **太阴**：隐蔽
- **六合**：和合
- **白虎**：凶恶
- **玄武**：隐蔽
- **九地**：低陷
- **九天**：高升

### 阴阳遁

- **阳遁**：冬至后至夏至前使用，天干顺飞
- **阴遁**：夏至后至冬至前使用，天干逆飞

### 三元

根据日地支确定：

- **上元**：子午卯酉（四仲）
- **中元**：寅申巳亥（四孟）
- **下元**：辰戌丑未（四季）

## 模块结构

### 1. `qi_men.cppm` - 核心定义

定义了所有枚举、数据结构和基础工具函数：

```cpp
// 枚举定义
enum class Palace : std::uint8_t { /* ... */ };
enum class Gate : std::uint8_t { /* ... */ };
enum class Star : std::uint8_t { /* ... */ };
enum class Spirit : std::uint8_t { /* ... */ };
enum class Dun : std::uint8_t { Yang = 0, Yin = 1 };
enum class Yuan : std::uint8_t { Shang = 0, Zhong = 1, Xia = 2 };
enum class SolarTerm : std::uint8_t { /* 24个节气 */ };

// 数据结构
struct PalaceInfo { /* 宫位信息 */ };
struct QiMenPan { /* 完整排盘信息 */ };
```

### 2. `qi_men_pan.cppm` - 排盘算法

实现了 `QiMenPanGenerator` 类，提供排盘生成功能：

```cpp
class QiMenPanGenerator {
public:
    static auto generate_pan(
        SolarTerm solar_term,
        std::uint8_t tian_gan_day,
        std::uint8_t di_zhi_day,
        std::uint8_t tian_gan_hour,
        std::uint8_t di_zhi_hour
    ) -> std::expected<QiMenPan, std::string>;
};
```

### 3. `qi_men_controller.cppm` - 高级 API

提供了 `QiMenController` 和 `QiMenAnalyzer` 类：

```cpp
class QiMenController {
    // 从日期时间生成排盘
    static auto generate_from_date_time(...) -> std::expected<QiMenPan, std::string>;
    
    // 查询宫位信息
    static auto query_palace_info(...) -> std::expected<PalaceInfo, std::string>;
    
    // 获取排盘描述
    static auto get_pan_description(...) -> std::string;
};

class QiMenAnalyzer {
    // 分析吉凶
    static auto analyze_auspiciousness(...) -> std::expected<std::string, std::string>;
    
    // 获取摘要
    static auto get_summary(...) -> std::string;
};
```

## 使用示例

### 基础排盘

```cpp
import ZhouYi.QiMen;
import ZhouYi.QiMen.Pan;

using namespace ZhouYi::QiMen;

// 生成排盘
auto result = QiMenPanGenerator::generate_pan(
    SolarTerm::MangZhong,  // 芒种
    0,                      // 日天干：甲
    6,                      // 日地支：午
    2,                      // 时天干：丙
    2                       // 时地支：寅
);

if (result) {
    const auto& pan = result.value();
    std::println("局数：{}", pan.ju);
    std::println("阴阳遁：{}", pan.dun == Dun::Yang ? "阳遁" : "阴遁");
} else {
    std::println("错误：{}", result.error());
}
```

### 使用控制器

```cpp
import ZhouYi.QiMen.Controller;

using namespace ZhouYi::QiMen;

// 从日期时间生成
auto result = QiMenController::generate_from_date_time(
    2011, 6, 18, 3, 56,
    SolarTerm::MangZhong,
    0, 6
);

if (result) {
    auto summary = QiMenAnalyzer::get_summary(result.value());
    std::println("{}", summary);
}
```

### 分析吉凶

```cpp
auto analysis = QiMenAnalyzer::analyze_auspiciousness(pan, Palace::South);
if (analysis) {
    std::println("{}", analysis.value());
}
```

## 定局规则

### 阳遁歌

```
冬至惊蛰一七四，小寒二八五同推；
大寒春分三九六，芒种六三九是真；
谷雨小满五二八，立春八五二相随；
清明立夏四一七，九六三从雨水期。
```

### 阴遁歌

```
夏至白露九三六，小暑八二五重逢；
大暑秋分七一四，立秋二五八流通；
霜降小雪五八二，大雪四七一相同；
处暑排来一四七，立冬寒露六九三。
```

## 排盘步骤

1. **确定阴阳遁**：根据节气判断
2. **确定三元**：根据日地支判断
3. **确定局数**：根据节气和三元查表
4. **排布地盘**：天干按戊己庚辛壬癸丁丙乙顺序排列
5. **确定直符和直使**：根据时地支确定
6. **排布天盘**：天盘天干随直符旋转
7. **排布人盘**：八门根据时辰旋转
8. **排布神盘**：八神根据直符位置排列

## 天干地支编码

### 天干（0-9）

```
0: 甲  1: 乙  2: 丙  3: 丁  4: 戊
5: 己  6: 庚  7: 辛  8: 壬  9: 癸
```

### 地支（0-11）

```
0: 子  1: 丑  2: 寅  3: 卯  4: 辰  5: 巳
6: 午  7: 未  8: 申  9: 酉  10: 戌 11: 亥
```

## 错误处理

所有生成函数都返回 `std::expected<T, std::string>`，使用 C++23 标准的错误处理机制：

```cpp
auto result = QiMenPanGenerator::generate_pan(...);

if (result) {
    // 成功
    const auto& pan = result.value();
} else {
    // 失败
    std::println("错误：{}", result.error());
}
```

## 性能特性

- 所有数据结构使用栈分配
- 编译期常量函数用于定局算法
- 无动态内存分配
- 支持 constexpr 评估

## 扩展功能

### 待实现

1. **置闰法**：处理符头与节气的差异
2. **拆补法**：以节气为核心的定局方法
3. **随机起局**：支持用户输入数字起局
4. **详细解读**：提供详细的吉凶判断和预测
5. **历史查询**：支持查询历史排盘

## 参考资源

- 张志春《奇门遁甲预测学》
- 《奇门遁甲排盘详解》
- 传统奇门遁甲文献

## 注意事项

1. 本实现基于传统时家奇门排盘法
2. 节气时间需要根据实际农历转换
3. 时辰计算基于十二时辰制
4. 吉凶分析仅供参考，不作为决策依据
