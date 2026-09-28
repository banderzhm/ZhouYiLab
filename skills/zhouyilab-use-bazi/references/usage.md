# 八字怎么排盘、分析和输出

## 先认清一张盘的排列

八字的四柱顺序在项目里是固定契约：**年柱、月柱、日柱、时柱**。不要以数组下标猜柱位，也不要把显示顺序改成日、月、年、时；`BaZiBase::get_pillars(chart.ba_zi)` 返回的四项也严格遵循这个顺序。

| 柱位 | 读取字段 | 柱内字段 | 排盘中保留的资料 | 分析时的基准 |
| --- | --- | --- | --- | --- |
| 年柱 | `chart.ba_zi.year` | `gan`、`zhi` | 年干、年支及年支藏干 | 相对日干取十神 |
| 月柱 | `chart.ba_zi.month` | `gan`、`zhi` | 月干、月支及月令资料 | 节令、人元司令、格局取用的时间上下文 |
| 日柱 | `chart.ba_zi.day` | `gan`、`zhi` | 日干、日支及日支藏干 | `day.gan` 是十神参照的日主；不要把日干在报告中机械写成“日柱透比肩” |
| 时柱 | `chart.ba_zi.hour` | `gan`、`zhi` | 时干、时支及时支藏干 | 与出生时刻、后段运限资料共同保留 |

每一柱都是 `BaZiBase::Pillar`：`pillar.gan` 是天干枚举，`pillar.zhi` 是地支枚举，`pillar.to_string()` 生成如“甲子”的中文干支。地支藏干不在柱对象内缓存，统一由 `GanZhi::get_cang_gan(pillar.zhi)` 取得；返回顺序是基础层维护的**主气、中气、余气**，展示或分析不可另抄藏干表。

以日干为参照时，透干十神用 `GanZhi::get_shi_shen(chart.ba_zi.day.gan, pillar.gan)`；支中各藏干也逐个传入同一函数。纳音由 `BaZiBase::calculate_pillar_nayin(pillar)` 给出名称与五行；旬空直接读 `chart.ba_zi.xun_kong_1`、`chart.ba_zi.xun_kong_2`。这些都是排盘资料，不能由子平或盲派分析器各自重算。

## 出生时间怎样传入

| 入口 | 参数排列 | 适合的资料 | 时间与地点口径 |
| --- | --- | --- | --- |
| `BaZiController::pai_pan_solar(y, m, d, h, minute, is_male)` | 公历年、月、日、24 小时制、分钟、性别 | 已知公历出生时刻 | 未传地点时，原时刻直接排盘 |
| `pai_pan_solar(..., location)` | 在上述参数后追加 `std::optional<Time::Location>` | 已知经度、纬度、时区 | 传入地点才走现有真太阳时修正；调用方仍应保存原民用时刻和修正依据 |
| `BaZiController::pai_pan_lunar(y, m, d, h, minute, is_male)` | 农历年、月、日、小时、分钟、性别 | 已知农历出生资料 | 当前没有地点重载；闰月沿用负月份口径 |
| `batch_pai_pan` | 年、月、日、时、性别的 tuple | 批量且分钟均为 0 的场景 | 不适合保留“16:30”这类分钟资料 |

返回的 `BaZiResult` 同时保存排盘用的公历字段 `birth_year` 到 `birth_second`、农历字段 `lunar_year` 到 `lunar_day` 与性别。地点修正重载当前没有把完整修正轨迹写回 `BaZiResult`；要在报告中并列原时与真太阳时，应在调用层保留 `TrueSolarTime` 的返回值，不能从 `birth_*` 反推。

## 排盘、原局分析与中文报告

```cpp
import ZhouYi.BaZi;
import ZhouYi.BaZiController;
import ZhouYi.BaZiPresenter;
import ZhouYi.BaZiAnalysis.Contract;
import ZhouYi.BaZiAnalysis.ZiPing.Contract;
import ZhouYi.BaZiAnalysis.Report;
import std;

int main() {
  const auto chart = ZhouYi::BaZiController::pai_pan_lunar(
      2000, 6, 15, 16, 30, true);

  ZhouYi::BaZiAnalysis::AnalysisRequest request;
  request.method = ZhouYi::BaZiAnalysis::AnalysisMethod::Ziping;
  const auto analysis = ZhouYi::BaZiController::analyze_ba_zi(chart, request);

  // 完整 txt 先写排盘，再写分析；分析报告本身不重复输出四柱。
  ZhouYi::BaZiPresenter::display_result(chart);
  ZhouYi::BaZiAnalysis::write_zh(std::cout, analysis);
}
```

`analyze_ba_zi(chart)` 不传请求时默认子平。传 `AnalysisRequest` 后由 `method` 选择流派：

| `AnalysisRequest.method` | 原局资料的读取重点 | 不应混用的内容 |
| --- | --- | --- |
| `Ziping` | `element_balance`、`climate`、`pattern`、`strength`、`useful_gods`、`relations`、`fortune_impacts` 等子平契约 | 不能把盲派做功结论当扶抑分值解释 |
| `BlindSchool` | `blind_analysis` 中的宾主、体用、墓库、做功与宫位资料 | 盲派岁运不走子平喜忌、强弱与净分模型 |

请求中的 `birth_context` 默认由完整排盘结果构造；只有在调用方能保证与本次出生时刻完全一致时才覆盖。它用于节令和人元司令等需要精确时间的判断，不能用月支本气替代缺失的时间上下文。

## 大运、流年、流月、流日、流时如何接到原局

八字的时间层不是另一份四柱，而是围绕原局的叠加资料。读取顺序如下：

| 层次 | 从 `BaZiResult` 取得 | 关键字段 | 与原局衔接方式 |
| --- | --- | --- | --- |
| 大运 | `chart.da_yun_system.get_da_yun_list()` | `pillar`、`start_age`、`end_age`、`start_year`、`end_year`、干支十神 | 先以年份筛选当前大运，禁止硬编码示例中的运序下标 |
| 流年 | `chart.get_liu_nian(year)` | `year`、`pillar`、`age`、干支十神 | 与已筛到的大运、原局四柱共同进入岁运分析 |
| 流月 | `chart.get_liu_yue_list(year)` | 节气月序、`pillar`、起止日期 | 子平岁运入口可选传一柱流月；不传即只论大运、流年 |
| 流日 | `chart.get_liu_ri(y, m, d)` | 公历日期、`pillar`、干支十神 | 供需要日级排定的展示或后续规则使用 |
| 流时 | `chart.get_liu_shi(y, m, d, h)` | 小时、`pillar`、干支十神 | 与流日同用，不把钟点字符串当时支 |

子平岁运调用 `BaZiController::analyze_sui_yun(chart, da_yun.pillar, year, liu_yue, config)`。`liu_yue` 是 `std::optional<Pillar>`，为空时只排大运与流年。返回 `TransitAnalysis` 后仍用 `BaZiAnalysis::write_zh` 或 `to_json` 输出。

盲派岁运需另 import `ZhouYi.BaZiAnalysis.MangPai.Transit`，并按“岁运引动—宾主变化—做功链变化—宫位取象—应事类型”读取：

```cpp
const int year = 2027;
for (const auto &fortune : chart.da_yun_system.get_da_yun_list()) {
  if (year < fortune.start_year || year > fortune.end_year)
    continue;
  const auto transit = ZhouYi::BaZiAnalysis::MangPai::Transit::analyze(
      chart.ba_zi, fortune.pillar, chart.get_liu_nian(year).pillar,
      year, year == fortune.start_year);
  ZhouYi::BaZiAnalysis::MangPai::Transit::write_zh(std::cout, transit);
  break;
}
```

这里的 `year == fortune.start_year` 是现有“交运年”标记口径，精度到公历起运年，不代表精确交运日。盲派岁运结果的 `triggers`、`palaces`、`work_changes`、`symbols`、`event_types` 是结构化取象项；当前只有 `write_zh` 中文输出，没有专用 JSON 重载。

## 报告应怎样完整落盘

完整八字 txt 的顺序应是：出生公农历与性别 → 四柱（年/月/日/时）→ 各支藏干与十神 → 旬空、纳音与大运 → 原局分析 → 选定大运、流年、流月分析。`BaZiPresenter::display_result` 当前写控制台，`BaZiAnalysis::write_zh` 写任意 `std::ostream`，因此落文件时需要由例程先用排盘结构写基础资料，再把分析结果交给 `write_zh`；不能只输出分析而漏掉四柱。

构建示例：`cmake --build build --config Debug --target example_ba_zi example_ba_zi_mangpai`。参考 `examples/example_ba_zi.cpp`、`examples/example_ba_zi_mangpai.cpp`；后者会在运行工作目录写 `docs/example_ba_zi_mangpai_output.txt`。当前没有独立八字 CTest，不能以其他术数的回归测试替代八字排盘或报告对照。

## 改哪一层

以下路径均在 `src/ba_zi/` 下；声明看 `.cppm`，实现和规则表看同名 `.cpp` 或语义对应的实现单元。

| 需要调整的内容 | 文件职责 |
| --- | --- |
| 排盘调用、时间上下文、流派分派 | `ba_zi_controller.cpp` |
| 子平原局 | `analysis/ziping/ba_zi_analysis.cpp` |
| 子平干支关系与岁运 | `analysis/ziping/ba_zi_analysis_relations.cpp`、`ba_zi_analysis_fortune.cpp` |
| 盲派宾主、墓库、做功、岁运 | `analysis/mangpai/mangpai_bing_zhu.cpp`、`mangpai_muku.cpp`、`mangpai_zuo_gong.cpp`、`mangpai_transit.cpp` |
| 基础排盘展示 | `presenter/ba_zi_presenter.cpp` |
| 分析中文与 JSON | `analysis/presenter/ba_zi_report.cpp`、`analysis/ziping/ba_zi_analysis_presenter.cpp` |
| 四柱、旬空、纳音、人元司令 | `src/common/tian_gan/ba_zi_base.*` |
| 藏干、十神、刑冲合害、三合三会 | `src/common/tian_gan/ganzhi.*` |

排盘资料有误时先查基础或排盘层；格局、做功、岁运结论有误时回到对应流派分析器；只是中文排版或字段显示有误才改 presenter/report。
