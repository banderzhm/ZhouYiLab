# 紫微斗数运限分析使用指南

## 概述

紫微斗数运限分析模块整合了 **tyme4cpp** 库，提供完整的大限、小限、流年、流月、流日、流时分析功能，准确推导天干地支，计算四化及宫位变化。

## 技术架构

### 模块依赖

```
ZiWei.Controller
    ├── ZhouYi.tyme (天干地支推导)
    ├── ZhouYi.ZiWei.Horoscope (运限计算)
    └── ZhouYi.ZiWei (核心排盘)
```

### 核心技术

1. **tyme库集成**
   - `SolarDay::from_ymd()` - 创建公历日期
   - `get_sixty_cycle_day()` - 获取干支日
   - `get_lunar_day()` - 转换为农历
   - `get_heaven_stem()` / `get_earth_branch()` - 提取天干地支

2. **运限计算流程**
   ```
   公历日期 → tyme库 → 天干地支 → horoscope模块 → 宫位+四化
   ```

## 接口说明

### 1. 大限分析

```cpp
void display_da_xian_analysis(const ZiWeiResult& result);
```

**功能**：显示12个大限的起止年龄、行进方向、对应宫位

**口诀**：
- 大限由命宫起，阳男阴女顺行，阴男阳女逆行
- 每十年过一宫限
- 起运年龄依五行局而定

**示例输出**：
```
第1限：4岁-13岁 (顺行) - 大限宫位：第0宫 命宫
第2限：14岁-23岁 (顺行) - 大限宫位：第1宫 兄弟宫
...
```

### 2. 小限分析

```cpp
void display_xiao_xian_analysis(const ZiWeiResult& result, int current_age);
```

**功能**：显示指定虚岁的小限宫位

**口诀**：
- 小限从寅宫起1岁
- 阳男阴女顺行，阴男阳女逆行
- 每岁一宫

**使用示例**：
```cpp
auto result = pai_pan_solar(1990, 5, 20, 14, true);
display_xiao_xian_analysis(result, 30); // 显示30岁小限
```

### 3. 流年分析

```cpp
void display_liu_nian_analysis(const ZiWeiResult& result, int target_year, int current_age);
```

**功能**：显示指定年份的流年天干地支、宫位、四化

**技术实现**：
```cpp
// 1. 使用tyme库获取年份天干地支
auto solar_day = tyme::SolarDay::from_ymd(target_year, 1, 1);
auto sixty_cycle_day = solar_day.get_sixty_cycle_day();
auto year_cycle = sixty_cycle_day.get_year();

TianGan tian_gan = static_cast<TianGan>(year_cycle.get_heaven_stem().get_index());
DiZhi di_zhi = static_cast<DiZhi>(year_cycle.get_earth_branch().get_index());

// 2. 调用horoscope模块
auto liu_nian_data = get_liu_nian(target_year, tian_gan, di_zhi, ming_gong_index);
```

**使用示例**：
```cpp
auto result = pai_pan_solar(1990, 5, 20, 14, true);
display_liu_nian_analysis(result, 2025, 36); // 显示2025年流年（36岁）
```

**示例输出**：
```
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
       2025年流年分析（36岁）
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

流年干支：乙巳 (2025年)
流年宫位：第5宫 巳宫

流年四化：
  化禄 - 天机
  化权 - 天梁
  化科 - 紫微
  化忌 - 太阴

流年宫位详情：
【巳宫 (乙巳)】
  主星：天机 [庙] 化禄
  ...
```

### 4. 流月分析

```cpp
void display_liu_yue_analysis(const ZiWeiResult& result, 
                               int target_year, int target_month, int current_age);
```

**功能**：显示指定月份的流月天干地支、宫位、四化

**技术实现**：
```cpp
// 1. 获取农历月份和月干支
auto solar_day = tyme::SolarDay::from_ymd(target_year, target_month, 15);
auto lunar_day = solar_day.get_lunar_day();
int lunar_month = lunar_day.get_lunar_month().get_month();

auto sixty_cycle_day = solar_day.get_sixty_cycle_day();
auto month_cycle = sixty_cycle_day.get_month();

TianGan month_gan = static_cast<TianGan>(month_cycle.get_heaven_stem().get_index());
DiZhi month_zhi = static_cast<DiZhi>(month_cycle.get_earth_branch().get_index());

// 2. 调用horoscope模块
auto liu_yue_data = get_liu_yue(lunar_month, birth_month, month_gan, 
                                  month_zhi, year_zhi, ming_gong_index);
```

**使用示例**：
```cpp
auto result = pai_pan_solar(1990, 5, 20, 14, true);
display_liu_yue_analysis(result, 2025, 6, 36); // 2025年6月流月
```

### 5. 流日分析

```cpp
void display_liu_ri_analysis(const ZiWeiResult& result, 
                              int target_year, int target_month, int target_day, 
                              int current_age);
```

**功能**：显示指定日期的流日天干地支、宫位、四化

**技术实现**：
```cpp
// 1. 获取日干支
auto solar_day = tyme::SolarDay::from_ymd(target_year, target_month, target_day);
auto sixty_cycle_day = solar_day.get_sixty_cycle_day();
auto day_cycle = sixty_cycle_day.get_sixty_cycle();

TianGan day_gan = static_cast<TianGan>(day_cycle.get_heaven_stem().get_index());
DiZhi day_zhi = static_cast<DiZhi>(day_cycle.get_earth_branch().get_index());

// 2. 先获取流月宫位，再计算流日
auto liu_yue_data = get_liu_yue(...);
auto liu_ri_data = get_liu_ri(lunar_day_num, day_gan, day_zhi, 
                                liu_yue_data.gong_index);
```

**使用示例**：
```cpp
auto result = pai_pan_solar(1990, 5, 20, 14, true);
display_liu_ri_analysis(result, 2025, 6, 15, 36); // 2025年6月15日
```

### 6. 流时分析

```cpp
void display_liu_shi_analysis(const ZiWeiResult& result, 
                               int target_year, int target_month, int target_day, 
                               DiZhi target_hour, int current_age);
```

**功能**：显示指定时辰的流时天干地支、宫位、四化

**技术实现**：
```cpp
// 1. 获取日干
auto solar_day = tyme::SolarDay::from_ymd(target_year, target_month, target_day);
auto sixty_cycle_day = solar_day.get_sixty_cycle_day();
auto day_cycle = sixty_cycle_day.get_sixty_cycle();

// 2. 五鼠遁日起时法计算时干
int day_gan_index = day_cycle.get_heaven_stem().get_index();
int hour_zhi_index = static_cast<int>(target_hour);
int hour_gan_index = (day_gan_index % 5 * 2 + hour_zhi_index) % 10;

TianGan hour_gan = static_cast<TianGan>(hour_gan_index);

// 3. 先获取流月、流日宫位，再计算流时
auto liu_ri_data = get_liu_ri(...);
auto liu_shi_data = get_liu_shi(target_hour, hour_gan, liu_ri_data.gong_index);
```

**使用示例**：
```cpp
auto result = pai_pan_solar(1990, 5, 20, 14, true);
display_liu_shi_analysis(result, 2025, 6, 15, DiZhi::Wei, 36); // 未时
```

### 7. 完整运限分析

```cpp
void display_yun_xian_full_analysis(const ZiWeiResult& result, 
                                     int target_year, int target_month, int target_day, 
                                     DiZhi target_hour, int current_age);
```

**功能**：一次性显示大限、小限、流年、流月、流日、流时的完整分析

**使用示例**：
```cpp
auto result = pai_pan_solar(1990, 5, 20, 14, true);
display_yun_xian_full_analysis(result, 2025, 6, 15, DiZhi::Wei, 36);
```

**示例输出**：
```
╔════════════════════════════════════════════════════════════╗
║              完整运限分析报告                              ║
║       2025年6月15日 未时 （36岁）                         ║
╚════════════════════════════════════════════════════════════╝

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
       大限分析
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
第1限：4岁-13岁 (顺行) - 大限宫位：第0宫 命宫
...

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
       小限分析（36岁）
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
小限宫位：第3宫 子宫
...

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
       2025年流年分析（36岁）
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
流年干支：乙巳 (2025年)
...

[依次显示流月、流日、流时分析]
```

## 完整使用示例

```cpp
#include <iostream>
import ZhouYi.ZiWei.Controller;
import ZhouYi.GanZhi;

int main() {
    using namespace ZhouYi::ZiWei;
    using namespace ZhouYi::GanZhi;
    
    // 1. 排盘：1990年5月20日14时 男命
    auto result = pai_pan_solar(1990, 5, 20, 14, true);
    
    // 2. 查看本命盘
    pai_pan_and_print_solar(1990, 5, 20, 14, true);
    
    // 3. 查看大限分析
    display_da_xian_analysis(result);
    
    // 4. 查看当前虚岁（36岁）的小限
    display_xiao_xian_analysis(result, 36);
    
    // 5. 查看2025年流年
    display_liu_nian_analysis(result, 2025, 36);
    
    // 6. 查看2025年6月流月
    display_liu_yue_analysis(result, 2025, 6, 36);
    
    // 7. 查看2025年6月15日流日
    display_liu_ri_analysis(result, 2025, 6, 15, 36);
    
    // 8. 查看2025年6月15日未时流时
    display_liu_shi_analysis(result, 2025, 6, 15, DiZhi::Wei, 36);
    
    // 9. 或者一次性查看完整运限
    display_yun_xian_full_analysis(result, 2025, 6, 15, DiZhi::Wei, 36);
    
    return 0;
}
```

## 关键技术点

### 1. tyme库类型转换

```cpp
// tyme::HeavenStem → ZhouYi::GanZhi::TianGan
TianGan tian_gan = static_cast<TianGan>(heaven_stem.get_index());

// tyme::EarthBranch → ZhouYi::GanZhi::DiZhi  
DiZhi di_zhi = static_cast<DiZhi>(earth_branch.get_index());
```

### 2. 五鼠遁日起时法

```cpp
// 根据日干和时辰地支计算时干
int hour_gan_index = (day_gan_index % 5 * 2 + hour_zhi_index) % 10;
```

口诀：
- 甲己起甲子
- 乙庚起丙子
- 丙辛起戊子
- 丁壬起庚子
- 戊癸起壬子

### 3. 运限宫位计算链

```
命宫 → 流年宫 → 流月宫 → 流日宫 → 流时宫
       ↓          ↓         ↓         ↓
     地支定    逆数生月   顺数日    顺数时
```

## 注意事项

1. **虚岁计算**：需要根据出生年份和当前年份正确计算虚岁
2. **农历转换**：流月、流日使用农历计数
3. **闰月处理**：tyme库自动处理闰月情况
4. **四化显示**：按化禄、化权、化科、化忌顺序显示
5. **性能优化**：重复计算可以缓存结果

## 扩展功能

未来可以添加：
1. **运限四化对照表**：显示本命盘vs流年盘的四化对照
2. **宫位对照分析**：地盘、天盘、人盘三盘对照
3. **流年大事提醒**：根据四化自动提示重要事件
4. **批量运限计算**：一次性计算未来10年运势
5. **JSON导出**：导出完整的运限数据供前端使用

## 总结

紫微斗数运限分析模块通过整合tyme4cpp库，实现了完整的天干地支推导和运限计算功能，提供了从大限到流时的六级运限分析，配合四化系统，可以进行精确的命理分析和运势预测。

