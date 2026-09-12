# 干支、四柱和时间怎么用

## 要查什么，用哪个模块

| 需求 | import | API / 源码 |
| --- | --- | --- |
| 天干地支、五行、十神、藏干、合冲刑害 | `ZhouYi.GanZhi` | `src/common/tian_gan/ganzhi.cppm` |
| 四柱与旬空、纳音、司令时间计算 | `ZhouYi.BaZiBase` | `src/common/tian_gan/ba_zi_base.cppm` |
| 中文五行名称解析 | `ZhouYi.WuXingUtils` | `element_from_name`，不是重新计算纳音 |
| 公农历对象转换 | `ZhouYi.tyme` | `src/common/tyme/tyme.cppm`，命名空间为 `tyme` |
| 可选地点与真太阳时 | `ZhouYi.TrueSolarTime` | `src/common/time/true_solar_time.cppm` |
| 六十四卦资料 | `ZhouYi.YiJing.HexagramCatalog` | `hexagram_info(code)`、`hexagram_catalog()` |
| 通用枚举中文映射 | `ZhouYi.ZhMapper` | `ZhouYi::Mapper::to_zh(value)`，同时 import 该枚举的映射所属模块 |

## 排出四柱，再查十神、藏干和三合

```cpp
import ZhouYi.BaZiBase;
import ZhouYi.GanZhi;
import ZhouYi.TrueSolarTime;
import ZhouYi.YiJing.HexagramCatalog;
import std;

int main() {
  const auto chart = ZhouYi::BaZiBase::BaZi::from_solar(2000, 7, 16, 16, 30);
  const auto ten_god = ZhouYi::GanZhi::get_shi_shen(chart.day.gan, chart.year.gan);
  const auto hidden = ZhouYi::GanZhi::get_cang_gan(chart.day.zhi);
  const auto [complete, element] = ZhouYi::GanZhi::is_san_he(
      ZhouYi::GanZhi::DiZhi::Hai, ZhouYi::GanZhi::DiZhi::Mao,
      ZhouYi::GanZhi::DiZhi::Wei);
  const auto &hexagram = ZhouYi::YiJing::hexagram_info("111111");
  std::cout << chart.day.to_string() << ' ' << hexagram.name << '\n';
}
```

`BaZiBase::BaZi` 只含四柱基础，不包含 `BaZi::BaZiResult` 的大运系统。
`from_solar(year, month, day, hour, minute=0, second=0)`；同形的 `from_lunar` 月份用负数表示闰月。小时是 0—23 的钟表小时，不是子一至亥十二序号。

## 生克方向、半合和司令怎么查

- `wu_xing_sheng(x,y)` 表示 x 生 y，`wu_xing_ke(x,y)` 表示 x 克 y；`get_shi_shen(self_gan, other_gan)` 首参是日主。
- `is_chong`、`is_hai`、`is_he` 返回 bool；`is_xing(first,second)` 按有方向相刑处理，需要双向信息时分别查询。
- `get_he_wu_xing` 和 `stem_combine_element` 返回可空五行，只有存在值时才读取；值代表合化方向，不代表已经成化。
- `is_san_he(a,b,c)` / `is_san_hui(a,b,c)` 返回 `pair<bool,WuXing>`，先判断 bool，不使用失败时的五行占位值。半合用 `get_san_he_half(a,b)`，返回 `optional<SanHeHalfResult>`，读取 `.element/.kind/.missing_branch`；不能传重复第三支伪造完整三合。
- `get_human_command_segments(month_branch)` 返回只读分日段；时间适配用 `BaZiBase::calculate_human_command`。纳音用 `calculate_pillar_nayin(pillar)`；中文名称解析使用已有 `WuXingUtils::element_from_name`，不在分析器重复解析。
- 藏干读取优先强类型 `get_cang_gan`，不要将旧字符串映射表当成另一份可修改的规则源。

## 有地点时怎么算真太阳时

```cpp
// 以下片段放在函数内；除上述模块外还需 import ZhouYi.tyme。
const std::optional<ZhouYi::Time::Location> location = ZhouYi::Time::Location{
    .longitude = 104.0665, .latitude = 30.5723, .timezone = 8};
const auto correction = ZhouYi::Time::correct(2000, 7, 16, 16, 30, 0, location);
const auto corrected_chart = ZhouYi::BaZiBase::BaZi::from_solar(
    2000, 7, 16, 16, 30, 0, location);
```

这里两个调用各自产生结果，只将其中一个修正后的结果继续用于排盘，不能把修正时刻连同 location 再送入修正重载。
`Location.longitude` 东经为正，`latitude` 北纬为正且当前仅记录地点，`timezone` 是整数小时偏移。半小时时区不能直接表达，不可悄悄截断。
`correct(..., std::nullopt)` 保留原时刻，检查 `civil_time`、`solar_time`、`offset_minutes`；缺地点不能拿 `Location{}` 冒充未提供，因为后者会触发修正。
只有农历输入且某上层缺少地点重载时，先用 `tyme::LunarHour::from_ymd_hms(...).get_solar_time()` 转公历，再选择支持分钟和地点的入口；明确保存原始历法及修正量。

## 怎么编译和检查

调用者链接 CMake 目标 `ZhouYiLabCore`，不能单独用普通头文件方式编译这些 import。
已有六爻、八字、梅花示例均调用本层；基础变更运行所有现有 CTest。逐项检查输入合法性、方向性与时间边界；具体异常以所属实现为准，不假定本层统一返回 expected。

## 要改这些功能，去哪里改

| 要改的功能 | 文件 |
| --- | --- |
| 生克、刑冲合害、半合三合、十神、藏干、司令分日表 | `src/common/tian_gan/ganzhi.cpp` |
| 四柱组合、单柱纳音、按出生时间计算司令 | `src/common/tian_gan/ba_zi_base.cpp` |
| 五行名称解析 | `src/common/tian_gan/wu_xing_utils.cpp` |
| 真太阳时修正 | `src/common/time/true_solar_time.cpp` |
| 六十四卦固定资料 | `src/common/yi_jing/hexagram_catalog.cpp` |

函数声明在同名 `.cppm`。改这层会影响多个术数，别只跑一个示例就结束。
