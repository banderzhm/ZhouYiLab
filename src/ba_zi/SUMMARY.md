# 八字模块实现总结

## 📋 概述

八字模块提供完整的四柱八字排盘功能，支持大运、流年、流月、流日、流时计算。

## ✅ 核心功能

### 1. 四柱八字
- 从公历创建：`BaZi::from_solar(year, month, day, hour, minute)`
- 从农历创建：`BaZi::from_lunar(year, month, day, hour, minute)`
- 包含：年柱、月柱、日柱、时柱、旬空

### 2. 十神系统
- **来源**：使用 `ganzhi.cppm` 公共模块定义
- **函数**：`get_shi_shen(day_gan, target_gan)`
- **类型**：比肩、劫财、食神、伤官、偏财、正财、七杀、正官、偏印、正印
- **应用**：四柱、大运、流年、流月、流日、流时都计算十神

### 3. 大运系统
- **起运年龄**：使用 `tyme::ChildLimit` 精确计算
- **顺逆规则**：阳男阴女顺排，阴男阳女逆排
- **大运跨度**：每个大运10年
- **信息包含**：干支、十神、起止年龄

### 4. 流年流月流日流时
- **流年**：每年的干支和十神
- **流月**：每月的干支和十神
- **流日**：每日的干支和十神
- **流时**：每时的干支和十神

### 5. 童限详细信息 ⭐新增
- **起运年龄**：精确到年月日时分
- **出生时刻**：完整的公历时间
- **起运时刻**：开始起运的公历时间
- **童限信息**：年数、月数、日数、时数、分钟数

### 6. tyme 库完整访问 ⭐新增
- **大运对象**：`tyme::DecadeFortune` 完整功能
- **小运对象**：`tyme::Fortune` 完整功能
- **干支年**：起止年份的干支表示
- **六十甲子**：完整的干支信息
- **下一大运**：支持链式访问

## 🏗️ 架构设计

### 模块依赖

```
ZhouYi.BaZi (八字模块)
  ├── ZhouYi.BaZiBase (八字基础 - Pillar, BaZi)
  ├── ZhouYi.GanZhi (干支系统 - 包含十神)
  ├── ZhouYi.WuXingUtils (五行工具)
  ├── ZhouYi.tyme (时间库 - 童限、大运)
  ├── nlohmann.json (JSON支持)
  └── fmt (格式化输出)
```

### 核心类

```
DaYun (大运信息)
  ├── pillar: Pillar
  ├── start_age: int
  ├── end_age: int
  ├── gan_shi_shen: ShiShen
  └── zhi_shi_shen: ShiShen

DaYunSystem (大运系统)
  ├── da_yun_list: vector<DaYun>
  ├── qi_yun_age: int
  ├── shun_pai: bool
  └── child_limit: tyme::ChildLimit

LiuNian (流年)
  ├── year: int
  ├── pillar: Pillar
  ├── age: int
  ├── gan_shi_shen: ShiShen
  └── zhi_shi_shen: ShiShen

BaZiResult (完整结果)
  ├── ba_zi: BaZi
  ├── is_male: bool
  ├── birth info: year, month, day, hour, minute, second
  └── da_yun_system: DaYunSystem
```

## 🔧 技术特点

### 1. 使用 tyme 库精确计算
- **童限系统**：`tyme::ChildLimit` 精确计算起运年龄
- **节气计算**：根据出生时间和节气距离
- **多流派支持**：默认、元亨利贞、Lunar流派1、Lunar流派2

### 2. 模块化设计
- **公共定义**：十神定义在 `ganzhi.cppm`，避免重复
- **功能分离**：核心逻辑（ba_zi.cppm）和接口（ba_zi_controller.cppm）分离
- **清晰依赖**：所有依赖明确声明

### 3. 类型安全
- 使用枚举类型：`TianGan`, `DiZhi`, `ShiShen`
- 编译期类型检查
- 避免字符串错误

### 4. 现代 C++23
- 使用 Modules 系统
- `std::expected` 错误处理（待实现）
- `std::ranges` 算法
- `std::println` 输出

## 📊 使用流程

```
1. 创建八字
   ↓
2. 排盘（创建 BaZiResult）
   ├── 计算童限（tyme::ChildLimit）
   ├── 计算起运年龄
   ├── 生成大运列表
   └── 计算十神
   ↓
3. 查询信息
   ├── 四柱十神
   ├── 大运列表
   ├── 流年信息
   ├── 流月信息
   └── 流日流时
   ↓
4. 导出结果
   ├── 控制台显示
   ├── JSON 导出
   └── 批量处理
```

## 🎯 使用示例

### 基本排盘

```cpp
import ZhouYi.BaZiController;

// 排盘：1990年1月1日12时30分，男性
auto result = pai_pan_solar(1990, 1, 1, 12, 30, true);

// 显示结果
display_result(result);
```

### 获取大运

```cpp
// 获取起运年龄
int qi_yun_age = result.da_yun_system.get_qi_yun_age();

// 获取所有大运
for (const auto& da_yun : result.da_yun_system.get_da_yun_list()) {
    std::println("大运: {} ({}-{}岁)", 
                 da_yun.pillar.to_string(),
                 da_yun.start_age, da_yun.end_age);
}

// 查询35岁的大运
auto current_da_yun = result.get_current_da_yun(35);
```

### 获取流年流月

```cpp
// 2024年流年
auto liu_nian = result.get_liu_nian(2024);
std::println("流年: {} {}岁", liu_nian.pillar.to_string(), liu_nian.age);

// 2024年6月流月
auto liu_yue = result.get_liu_yue(2024, 6);
std::println("流月: {}", liu_yue.pillar.to_string());
```

### 访问童限和 tyme 库

```cpp
// 童限详细信息
auto detail = result.get_child_limit_detail();
std::println("起运: {}岁 ({}年{}月{}天)", 
             detail.start_age,
             detail.year_count,
             detail.month_count,
             detail.day_count);

// 获取 tyme 大运对象
auto decade = result.get_tyme_decade_fortune(0);
std::println("大运: {} ({}-{}岁)", 
             decade.get_name(),
             decade.get_start_age(),
             decade.get_end_age());

// 获取小运
auto fortune = result.get_tyme_fortune(0);
std::println("小运: {}", fortune.get_name());
```

## 📈 性能特点

- ✅ 编译期计算（constexpr）
- ✅ 零拷贝设计（使用引用和移动语义）
- ✅ 高效查找（使用 map 和 unordered_map）
- ✅ 批量处理支持

## 🔮 未来规划

1. **神煞系统**
   - 禄神、羊刃、桃花、驿马
   - 天乙贵人、华盖、将星
   - 空亡、劫煞、灾煞

2. **格局判断**
   - 正官格、偏财格等八格
   - 外格（从格、化格）
   - 格局高低分析

3. **用神分析**
   - 自动确定用神
   - 喜忌神判断
   - 调候用神

4. **吉凶判断**
   - 大运流年吉凶
   - 流月流日判断
   - 运势预测

## 📚 参考资料

- `tyme4cpp` 库文档：https://github.com/6tail/tyme4cpp
- 八字命理学经典著作
- 传统起运算法

## 🤝 贡献

欢迎贡献代码和建议！

---

**最后更新**：2024-10-14
**版本**：v1.0.0
**作者**：ZhouYiLab Team

