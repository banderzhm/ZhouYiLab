# 八字模块

## 简介

本模块提供完整的八字排盘功能，包括：

- **四柱八字**：年柱、月柱、日柱、时柱
- **十神系统**：比肩、劫财、食神、伤官、偏财、正财、七杀、正官、偏印、正印
- **大运系统**：10年一个大运，自动计算起运年龄和顺逆
- **流年**：每年的运势干支
- **流月**：每月的运势干支
- **流日**：每日的运势干支
- **流时**：每时的运势干支

## 模块文件

- `ba_zi.cppm` - 核心模块（大运、流年、流月、流时）
- `ba_zi_controller.cppm` - 控制器接口
- `ba_zi_controller.cpp` - 控制器实现

## 使用示例

### 基本排盘

```cpp
import ZhouYi.BaZiController;
import std;

using namespace ZhouYi::BaZiController;

// 排盘：1990年1月1日12时出生的男性
auto result = pai_pan_solar(1990, 1, 1, 12, 0, true);

// 显示完整结果
display_result(result);
```

### 访问八字信息

```cpp
// 获取四柱
const auto& bazi = result.ba_zi;
std::println("年柱: {}", bazi.year.to_string());  // 如：己巳
std::println("月柱: {}", bazi.month.to_string()); // 如：丙子
std::println("日柱: {}", bazi.day.to_string());   // 如：戊申
std::println("时柱: {}", bazi.hour.to_string());  // 如：戊午

// 获取旬空
std::println("旬空: {}{}", bazi.xun_kong_1, bazi.xun_kong_2);

// 获取十神
auto shi_shen_arr = result.get_si_zhu_shi_shen();
std::println("年干十神: {}", shi_shen_to_zh(shi_shen_arr[0]));
std::println("月干十神: {}", shi_shen_to_zh(shi_shen_arr[1]));
std::println("日干十神: {}", shi_shen_to_zh(shi_shen_arr[2])); // 日干为比肩
std::println("时干十神: {}", shi_shen_to_zh(shi_shen_arr[3]));
```

### 大运查询

```cpp
// 获取大运系统
const auto& da_yun_system = result.da_yun_system;

// 基本信息
std::println("起运年龄: {}岁", da_yun_system.get_qi_yun_age());
std::println("排运方式: {}", da_yun_system.is_shun_pai() ? "顺排" : "逆排");

// 获取所有大运
for (const auto& da_yun : da_yun_system.get_da_yun_list()) {
    std::println("大运: {} ({}-{}岁)", 
                 da_yun.pillar.to_string(),
                 da_yun.start_age,
                 da_yun.end_age);
    std::println("  天干十神: {}", shi_shen_to_zh(da_yun.gan_shi_shen));
    std::println("  地支十神: {}", shi_shen_to_zh(da_yun.zhi_shi_shen));
}

// 查询特定年龄的大运
auto current_da_yun = result.get_current_da_yun(35); // 35岁的大运
if (current_da_yun.has_value()) {
    std::println("35岁大运: {}", current_da_yun->to_string());
}

// 使用辅助函数显示大运
display_da_yun(result, 10);  // 显示10个大运
```

### 流年查询

```cpp
// 获取指定年份的流年
auto liu_nian_2024 = result.get_liu_nian(2024);
std::println("2024年流年:");
std::println("  干支: {}", liu_nian_2024.pillar.to_string());
std::println("  年龄: {}岁", liu_nian_2024.age);
std::println("  天干十神: {}", shi_shen_to_zh(liu_nian_2024.gan_shi_shen));
std::println("  地支十神: {}", shi_shen_to_zh(liu_nian_2024.zhi_shi_shen));

// 批量查询多年
display_liu_nian(result, 2020, 10);  // 显示2020-2029年的流年
```

### 流月查询

```cpp
// 获取指定年月的流月
auto liu_yue = result.get_liu_yue(2024, 6);
std::println("2024年6月流月:");
std::println("  干支: {}", liu_yue.pillar.to_string());
std::println("  天干十神: {}", shi_shen_to_zh(liu_yue.gan_shi_shen));
std::println("  地支十神: {}", shi_shen_to_zh(liu_yue.zhi_shi_shen));

// 查询全年流月
display_liu_yue(result, 2024);  // 显示2024年12个月的流月
```

### 流日和流时查询

```cpp
// 流日
auto liu_ri = result.get_liu_ri(2024, 6, 15);
std::println("2024年6月15日流日: {}", liu_ri.to_string());

// 流时
auto liu_shi = result.get_liu_shi(2024, 6, 15, 14);
std::println("2024年6月15日14时流时: {}", liu_shi.to_string());
```

### 童限详细信息

```cpp
// 获取童限详细信息
auto child_detail = result.get_child_limit_detail();

std::println("起运年龄: {}岁", child_detail.start_age);
std::println("精确计算: {}年{}月{}天{}时{}分", 
             child_detail.year_count,
             child_detail.month_count,
             child_detail.day_count,
             child_detail.hour_count,
             child_detail.minute_count);

std::println("出生时刻: {}", child_detail.start_time.to_string());
std::println("起运时刻: {}", child_detail.end_time.to_string());

// 使用辅助函数显示
display_child_limit_detail(result);
```

### 访问 tyme 库完整功能

```cpp
// 获取 tyme 库的大运对象（可访问更多功能）
auto decade_fortune = result.get_tyme_decade_fortune(0);

// 基本信息
std::println("干支: {}", decade_fortune.get_name());
std::println("起始年龄: {}岁", decade_fortune.get_start_age());
std::println("结束年龄: {}岁", decade_fortune.get_end_age());

// 起止年份（干支年）
auto start_year = decade_fortune.get_start_sixty_cycle_year();
auto end_year = decade_fortune.get_end_sixty_cycle_year();
std::println("开始年份: {}", start_year.get_name());
std::println("结束年份: {}", end_year.get_name());

// 获取大运的干支对象
auto sixty_cycle = decade_fortune.get_sixty_cycle();
std::println("天干: {}", sixty_cycle.get_heaven_stem().get_name());
std::println("地支: {}", sixty_cycle.get_earth_branch().get_name());

// 获取该大运的起始小运
auto start_fortune = decade_fortune.get_start_fortune();
std::println("小运年龄: {}岁", start_fortune.get_age());
std::println("小运干支: {}", start_fortune.get_name());

// 获取下一个大运
auto next_decade = decade_fortune.next(1);
std::println("下一个大运: {}", next_decade.get_name());

// 使用辅助函数显示大运详细信息
display_tyme_decade_fortune(result, 0);

// 获取所有大运的 tyme 对象
auto all_fortunes = result.get_all_tyme_decade_fortunes(10);
for (size_t i = 0; i < all_fortunes.size(); ++i) {
    std::println("大运{}: {} ({}-{}岁)", 
                 i + 1,
                 all_fortunes[i].get_name(),
                 all_fortunes[i].get_start_age(),
                 all_fortunes[i].get_end_age());
}

// 获取小运
auto fortune = result.get_tyme_fortune(0);
std::println("小运: {} {}岁", fortune.get_name(), fortune.get_age());
```

### 农历排盘

```cpp
// 从农历排盘
auto result_lunar = pai_pan_lunar(1990, 1, 1, 0, 0, true);
display_result(result_lunar);
```

### JSON 导出

```cpp
// 导出为 JSON
nlohmann::json json_result = result.to_json();
std::println("{}", json_result.dump(2));

// JSON 结构：
// {
//   "ba_zi": { ... },
//   "is_male": true,
//   "birth_date": { "year": 1990, "month": 1, "day": 1, "hour": 12 },
//   "da_yun": {
//     "qi_yun_age": 8,
//     "shun_pai": true,
//     "list": [ ... ]
//   },
//   "shi_shen": {
//     "year": "偏财",
//     "month": "食神",
//     "day": "比肩",
//     "hour": "比肩"
//   }
// }
```

### 批量排盘

```cpp
// 批量排盘
std::vector<std::tuple<int, int, int, int, bool>> batch_requests = {
    {1990, 1, 1, 12, true},   // 男性
    {1992, 6, 15, 8, false},  // 女性
    {1988, 10, 20, 18, true}  // 男性
};

auto batch_results = batch_pai_pan(batch_requests);

for (const auto& result : batch_results) {
    display_result(result);
    std::println("");
}
```

## 功能特性

### ✅ 已实现

- [x] 四柱八字计算（公历/农历）
- [x] 十神系统完整实现（使用 ganzhi.cppm 公共定义）
- [x] 大运起运年龄计算（使用 tyme 库精确计算）
- [x] 大运顺逆判断（阳男阴女顺排，阴男阳女逆排）
- [x] 流年、流月、流日、流时计算
- [x] 旬空计算
- [x] JSON 序列化支持
- [x] 批量排盘
- [x] 集成 tyme 库童限系统（ChildLimit）

### 🚧 待实现

- [ ] 神煞系统（禄神、羊刃、桃花等）
- [ ] 格局判断（正官格、偏财格等）
- [ ] 用神分析
- [ ] 喜忌神判断
- [ ] 大运流年吉凶判断

## 技术细节

### 大运计算规则

1. **起运年龄**：使用 tyme 库的 `ChildLimit` 精确计算
   - 根据出生时间和下一个节气计算准确的起运年龄
   - 支持多种童限计算流派（默认、元亨利贞、Lunar流派1、Lunar流派2）
   - 计算精确到年、月、日、时、分
2. **顺逆规则**：
   - 阳男阴女：顺排（月柱往后推）
   - 阴男阳女：逆排（月柱往前推）
3. **大运跨度**：每个大运10年

### 起运年龄计算

本模块使用 `tyme4cpp` 库的童限系统计算起运年龄：

```cpp
// 创建童限对象
auto solar_time = tyme::SolarTime::from_ymd_hms(year, month, day, hour, minute, second);
auto gender = tyme::Gender::from_code(is_male ? 1 : 0);
auto child_limit = tyme::ChildLimit::from_solar_time(solar_time, gender);

// 获取起运年龄
int qi_yun_age = child_limit.get_start_age();

// 获取童限详细信息
auto info = child_limit.get_info();
int year_count = info.get_year_count();   // 年数
int month_count = info.get_month_count(); // 月数
int day_count = info.get_day_count();     // 日数
```

### 十神计算规则

根据日干与其他天干的关系：

- **同五行**：比肩（同阴阳）、劫财（异阴阳）
- **我生**：食神（同阴阳）、伤官（异阴阳）
- **我克**：偏财（同阴阳）、正财（异阴阳）
- **克我**：七杀（同阴阳）、正官（异阴阳）
- **生我**：偏印（同阴阳）、正印（异阴阳）

## 依赖

- `ZhouYi.BaZiBase` - 八字基础数据结构
- `ZhouYi.GanZhi` - 干支系统
- `ZhouYi.WuXingUtils` - 五行工具
- `ZhouYi.tyme` - 时间转换库
- `nlohmann.json` - JSON 支持
- `fmt` - 格式化输出

## 注意事项

1. **时辰问题**：23:00-23:59 属于次日子时，需要注意日柱的计算
2. **节气问题**：月柱以节气为准，不以农历月为准
3. **真太阳时**：精确八字需要考虑真太阳时，本模块目前使用平太阳时
4. **起运年龄**：目前简化为固定值，精确算法需要计算出生日到节气的距离

