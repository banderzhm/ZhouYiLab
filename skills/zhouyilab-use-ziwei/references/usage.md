# 紫微怎么排盘、看十二宫、星曜和格局

## 一张紫微盘的三套排列

紫微结果 `ZiWeiResult` 同时保留历法资料、四柱与十二宫。它们不能混成同一个索引体系：四柱按**年、月、日、时**排列；十二宫数组按**寅、卯、辰、巳、午、未、申、酉、戌、亥、子、丑**地支位置排列；宫职（命、兄弟、夫妻等）则在每个宫的 `gong_data.gong_wei` 中标识。

| 资料层 | 固定顺序/入口 | 读取字段 | 说明 |
| --- | --- | --- | --- |
| 历法与时辰 | 公历日、农历日、农历时辰 | `solar_day`、`lunar_day`、`lunar_hour` | 排盘原始历法资料；不要只拿四柱文本反推农历日时 |
| 四柱 | 年柱、月柱、日柱、时柱 | `year_pillar`、`month_pillar`、`day_pillar`、`hour_pillar` | 每项都是 `BaZiBase::Pillar`，有 `gan`、`zhi`、`to_string()` |
| 十二地支宫位 | 寅=0 → 卯=1 → … → 丑=11 | `palaces[0..11]` | 这是地盘地支索引，既不是子宫起算，也不是命宫起算 |
| 十二宫职 | 命宫起顺序由盘决定 | `palace.gong_data.gong_wei` | 用 `get_palace(GongWei::MingGong)` 等按宫职取，不要将 `GongWei` 枚举值直接当 `palaces` 下标 |
| 命、身所在位置 | 同样采用寅起索引 | `ming_gong_index`、`shen_gong_index` | 用 `get_palace_by_index` 取相应地支宫，再读其宫职与星曜 |

因此“命宫”是一个随生月与时辰落到某个地支宫的位置，不是 `palaces[0]`。想遍历完整盘时遍历 `palaces`；想读取命宫、夫妻宫、官禄宫等固定宫职时用 `get_palace(GongWei)`；想按地支位置取宫（如寅宫、申宫）时使用 `get_palace_by_index`。

## 每个宫格里有什么

`PalaceInfo` 是一个宫格的完整排盘资料，先读星曜，再读空宫、神煞与运限，不要从 `to_string()` 的文字反查字段。

| 宫格区域 | 字段 | 排盘含义 |
| --- | --- | --- |
| 宫干支与宫职 | `gong_data.gong_wei`、`gong_data.tian_gan`、`gong_data.di_zhi`、`gong_data.is_body_palace` | 当前地支位置上安置的宫职、宫干支以及是否身宫 |
| 主辅煞杂曜 | `zhu_xing`、`fu_xing`、`sha_xing`、`za_yao` | 每项为 `StarData`；星名、亮度及四化等随星数据保存在其中 |
| 空宫借星 | `is_empty`、`opposite_index`、`borrowed_from_index`、`borrowed_stars` | 无十四主星时，明确记录对宫索引和借入主星；不能把空宫当作没有资料 |
| 十二神系统 | `chang_sheng`、`bo_shi`、`sui_qian`、`jiang_qian` | 可选值；未安入时保持空，不在展示层临时补表 |
| 运限落点 | `da_xian_start`、`da_xian_end`、`xiao_xian_ages`、`liu_nian_ages` | 当前宫在地盘上承接的大限、小限与流年虚岁资料 |

全盘核心还包括 `wu_xing_ju`、`zi_wei_index`、`tian_fu_index`、`ming_zhu_xing`、`shen_zhu_xing`。十二宫的布局与星曜落点属于本命地盘；大限、流年等另在运限数据中叠加，不要把运限星曜写回本命 `palaces`。

## 排盘参数、时间和流派配置

```cpp
import ZhouYi.ZiWei;
import ZhouYi.ZiWei.Controller;
import std;

int main() {
  const auto chart = ZhouYi::ZiWei::pai_pan_lunar(2000, 6, 15, 16, true);
  std::cout << chart.to_string();
  ZhouYi::ZiWei::display_ge_ju_analysis(chart);
  const std::string report_json = ZhouYi::ZiWei::export_to_json_full(chart);
}
```

| 入口/配置 | 参数排列与当前行为 |
| --- | --- |
| `pai_pan_solar(y, m, d, h, is_male, location = nullopt, config = {})` | 公历年、月、日、小时、性别、可选地点、安星配置。当前核心没有 minute 参数。未提供 `location` 时不做真太阳时修正。 |
| `pai_pan_lunar(y, m, d, h, is_male, is_leap_month = false, config = {})` | 农历年、月、日、小时、性别、兼容闰月布尔值、安星配置。当前实现实际以月份正负号参与农历转换，不能假定 `is_leap_month=true` 已生效。 |
| `ZiWeiConfig::brightness_school` | 星曜亮度资料口径，默认 `BrightnessSchool::MetisDefault`。 |
| `ZiWeiConfig::kui_yue_school` | 辛年天魁、天钺起例口径，默认 `KuiYueSchool::QuanShuOne`。 |

若出生记录为“16:30”，核心接口只能接收 `16`。需要地点修正时，不应先把它截成 `16:00` 再修正：先以完整分钟调用 `Time::correct`，取得修正后的年月日小时，再以 `location = nullopt` 调核心公历入口，避免重复修正；原民用时刻、地点和偏移应由上层同时保存。农历入口目前没有地点参数，如需真太阳时应先完成历法与时间适配后再排盘。

## 命宫、星曜亮度、四化和格局怎样读取

```cpp
const auto &ming = chart.get_palace_by_index(chart.ming_gong_index);
const auto &spouse = chart.get_palace(ZhouYi::ZiWei::GongWei::FuQiGong);
// ming.gong_data 说明命宫落点；ming.zhu_xing / fu_xing / sha_xing / za_yao 为安星结果。
```

亮度随 `StarData` 保留，必须连同 `ZiWeiConfig::brightness_school` 的口径阅读；不能只比对星名而忽略亮度资料来源。四化、三方四正、宫干自化的现有控制台入口为：

- `display_gong_gan_si_hua(chart)`：宫干四化；
- `display_zi_hua_analysis(chart)`：自化资料；
- `display_san_fang_si_zheng(chart)`：三方四正；
- `display_ge_ju_analysis(chart)`：格局分析。

这些函数是当前 `ZhouYi.ZiWei.Controller` 的兼容控制台入口，不是一个统一分析 presenter。

- `chart.get_horoscope(target_year,target_month,target_day,target_hour,current_age)`：`target_year` 同时是流年的干支年与目标农历月日所属的农历年，`target_month` 为 1-12 的农历月序（闰月按本月月序传入），`target_day` 为农历日，`target_hour` 为地支时辰（子时按早子时口径），`current_age` 为虚岁且最小为 1。使用时显式 import `ZhouYi.ZiWei.Horoscope` 与 `ZhouYi.GanZhi`，不要混成全公历参数。
- `HoroscopeResult` 除大限／小限／流年／流月／流日／流时与五组流曜外，还有 `palace_tags[0..11]`：按寅起宫序给出每宫的 `is_da_xian`／`is_xiao_xian`／`is_liu_nian` 角标，以及**目标流年口径**的 `sui_qian`／`jiang_qian`。本命盘的岁前／将前仍在 `palaces[i].sui_qian` 与 `palaces[i].jiang_qian`，两者口径不同，前端角标取 `palace_tags`。大限干支就是该限宫的真实宫干支（五虎遁宫干＋寅起宫支），与 `palaces[da_xian.gong_index].gong_data` 逐宫一致，可直接当宫干支用；`arrange_da_xian` 需要按寅起宫序传入十二宫数据。
- 小限按生年支三合局起宫（`get_xiao_xian_start_gong`：寅午戌起辰宫、申子辰起戌宫、亥卯未起丑宫、巳酉丑起未宫），男顺女逆；它与 `palaces[i].xiao_xian_ages` 同源，同一虚岁在两处必须落同一个宫，不要再按“寅宫起 1 岁”或 1-6 岁童限表另算一套。
- 运限四化在 `si_hua_entries`（`vector<SiHuaEntry>`，星曜键 `SiHuaXing` 覆盖十四主星与左辅、右弼、文昌、文曲），顺序固定为化禄、化权、化科、化忌，`si_hua` 是它的星名序列；辅曜四化（如辛年文曲化科、文昌化忌）只能从 `si_hua_entries` 读到。`get_si_hua_table()` 仍可用，但只装十四主星。

`export_to_json_full(chart)` 返回的是 JSON **字符串**，包含格局、四化等完整报告资料；`export_to_json(chart)` 只给基础排盘。若调用方需要 JSON 对象，先 import `nlohmann.json` 后自行解析字符串，不能对字符串调用 `.dump()`。

深入格局规则时可使用 `ZhouYi.ZiWei.GeJu` 的 `GeJuAnalyzer(PatternChart)` 与 `analyze_all()`；但 `PatternChart` 的装配目前在 controller 实现内，没有公开的 `ZiWeiResult → PatternChart` 转换入口。普通调用方应使用上述展示/JSON 入口；改格局规则时才沿内部数据准备路径修改，不能在应用代码中杜撰一个可调用的转换函数。

`GeJuInfo` 重点读取 `status`、`level` 与 `basis.required`、`basis.bonus`、`basis.breaking`、`basis.source`。兼容 `score` 不能取代成格、加会、破格条件的逐项说明。

## 运限资料按哪一层读

紫微的运限结果为“本命地盘 + 大限天盘 + 流年以下人盘”的组合，字段次序如下。

| 层次 | 来源 | 关键字段 | 时间口径 |
| --- | --- | --- | --- |
| 大限 | `chart.da_xian_data[0..11]` 或 `get_horoscope(...).da_xian` | `start_age`、`end_age`、`gong_index`、干支、`si_hua` | 十年一段，虚岁范围 |
| 小限 | `get_horoscope(...).xiao_xian` | `age`、`gong_index` | 虚岁对应的宫位 |
| 流年 | `get_horoscope(...).liu_nian` | 公历 `year`、干支、`gong_index`、`si_hua` | 目标公历年 |
| 流月 | `get_horoscope(...).liu_yue` | 农历 `month`、干支、宫位、四化 | 参数是目标农历月 |
| 流日 | `get_horoscope(...).liu_ri` | 农历 `day`、干支、宫位、四化 | 参数是目标农历日 |
| 流时 | `get_horoscope(...).liu_shi` | 时支、干支、宫位、四化 | 参数是 `GanZhi::DiZhi`，不是钟表整数 |

调用 `chart.get_horoscope(target_year, target_month, target_day, target_hour, current_age)` 时，`target_year` 是公历年，`target_month`、`target_day` 是农历月日，`target_hour` 是时支，`current_age` 是虚岁。不要误传成全公历日期，也不要用周岁替代虚岁。

## 报告、示例与对照

`chart.to_string()` 输出基础资料、四柱和十二宫文字；需要逐宫、四化、格局与运限展示时组合 controller 的显示函数或使用 `export_to_json_full`。完整输出的顺序应是：历法与性别 → 年月日时四柱 → 命宫/身宫、五行局、命主身主 → 十二宫星曜和空宫借星 → 四化、三方四正、格局 → 目标运限。不要为了输出格局而漏掉十二宫排盘资料。

`display_yun_xian_full_analysis(result, year, month, day, hour_zhi, virtual_age)` 现在会在原有逐层资料后附加【命局—限年同参】：以本命宫垣为根，逐层列大限、小限、流年、流月、流日、流时的落宫、三方四正、主辅煞曜、空宫借星与本层四化，并标出限年同宫。`year/month/day` 是目标公历日期，`hour_zhi` 为时支，`virtual_age` 为虚岁；这是一套排盘结构同参，不把同宫或煞曜直接写成确定的人事结论。

```sh
cmake --build build --config Debug --target example_zi_wei regression_zi_wei
ctest --test-dir build -C Debug -R regression_zi_wei --output-on-failure --no-tests=error
```

`examples/example_zi_wei.cpp` 会写工作目录下 `docs/ziwei/example_zi_wei_output.txt`。`tests/regression_zi_wei.cpp` 覆盖网站对照、亮度和格局正反例。对照外部排盘时按“历法、性别、时辰、闰月、亮度/天魁天钺配置 → 十二宫地支位置 → 星曜及亮度 → 四化 → 格局”的顺序核验，不能只比较宫名、总分或一两颗主星。

## 改哪一层

| 需要调整的内容 | 文件职责 |
| --- | --- |
| 排盘总流程与 `ZiWeiResult` | `src/zi_wei/zi_wei.cppm`；其中仍保留既有实现，重构时先保持接口兼容 |
| 命宫、身宫、十二宫位置与宫干支 | `src/zi_wei/zi_wei_palace.*` |
| 主辅煞杂曜安置 | `src/zi_wei/zi_wei_star.*` 与分星实现 |
| 星曜亮度 | `src/zi_wei/zi_wei_brightness.*` |
| 四化、三方四正 | `src/zi_wei/zi_wei_sihua.*`、`zi_wei_sanfang.*` |
| 格局条件与格局用盘资料 | `src/zi_wei/zi_wei_geju.*`、`zi_wei_pattern.*` |
| 大限、小限、流年、流月、流日、流时 | `src/zi_wei/zi_wei_horoscope.*` |
| 控制台展示与 JSON 导出 | `src/zi_wei/zi_wei_controller.*` |

安星、亮度、格局结论错误应修改对应排盘/规则层；字段顺序、中文表格或 JSON 形态错误才修改 controller 或展示层。不要让展示层临时重算宫位、星曜或格局。
