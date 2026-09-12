# 八字怎么排盘、分析和输出

## 需要导入哪些模块

- 排盘/编排：`ZhouYi.BaZiController` → `src/ba_zi/ba_zi_controller.cppm`。
- 排盘结果：`ZhouYi.BaZi` → `src/ba_zi/ba_zi.cppm`；公共请求：`ZhouYi.BaZiAnalysis.Contract`。
- 原局统一结果目前仍定义于 `ZhouYi.BaZiAnalysis.ZiPing.Contract`（`ziping_contract.cppm`），包括 `method` 和可选 `blind_analysis`。这是当前兼容契约，不应宣称原局已返回完全独立的 variant。
- 中文排盘：`ZhouYi.BaZiPresenter`；分析中文/JSON：`ZhouYi.BaZiAnalysis.Report`。

## 排一个八字，按子平分析并输出

```cpp
import ZhouYi.BaZi;
import ZhouYi.BaZiController;
import ZhouYi.BaZiPresenter;
import ZhouYi.BaZiAnalysis.Contract;
import ZhouYi.BaZiAnalysis.ZiPing.Contract;
import ZhouYi.BaZiAnalysis.Report;
import nlohmann.json;
import std;

int main() {
  const auto chart = ZhouYi::BaZiController::pai_pan_lunar(2000, 6, 15, 16, 30, true);
  ZhouYi::BaZiAnalysis::AnalysisRequest request;
  request.method = ZhouYi::BaZiAnalysis::AnalysisMethod::Ziping;
  const auto analysis = ZhouYi::BaZiController::analyze_ba_zi(chart, request);
  ZhouYi::BaZiPresenter::display_result(chart);
  ZhouYi::BaZiAnalysis::write_zh(std::cout, analysis);
  const auto json = ZhouYi::BaZiAnalysis::to_json(analysis);
}
```

将 `method` 改为 `BlindSchool` 切换原局盲派。不传请求的 `analyze_ba_zi(chart)` 默认子平。
请求中的 `birth_context` 缺省由完整排盘结果填充；人工覆盖时必须保证和排盘出生时刻一致。`config` 是子平模型参数，不是让盲派复用扶抑结论的开关。

## 出生时间怎么传，结果里有什么

| 输入/API | 语义 |
| --- | --- |
| `pai_pan_solar(y,m,d,h,minute,is_male)` | 公历，小时 0—23，男 true |
| 同函数最后追加 `optional<Location>` | 可选修正；无地点原时刻不变 |
| `pai_pan_lunar(y,m,d,h,minute,is_male)` | 农历；当前没有 location 重载，闰月沿用负月份 |
| `batch_pai_pan` | tuple 为年、月、日、时、性别，分钟固定 0，不适合保留 16:30 的批处理 |

`chart.ba_zi` 保存四柱与旬空；`birth_*` 为公历时间，`lunar_*` 保留农历来源；`da_yun_system.get_da_yun_list()` 得到大运，`get_liu_nian(year)` 得到流年。读取 `analysis.method` 后选择子平字段或 `blind_analysis`，不要把盲派结果中的兼容默认数值当作扶抑判断。
当前地点重载结果未完整保留修正元数据；若报告需要原时刻和偏移，调用层须另外保存 `TrueSolarTime`，不能从 `birth_*` 反推。

## 子平和盲派的岁运分别怎么调用

子平：`BaZiController::analyze_sui_yun(chart, fortune.pillar, year, optional_liu_yue, config)`，后三者中流月默认空、配置默认值；得到 `TransitAnalysis`，用 `BaZiAnalysis::write_zh` / `to_json`。

盲派另 import `ZhouYi.BaZiAnalysis.MangPai.Transit`、`ZhouYi.BaZiBase`，调用：

```cpp
// chart 已由上面的排盘入口生成；这是函数内的岁运片段。
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

这是按公历起运年标记的现有口径，不是精确到交运日。别把示例的固定大运下标用于任意出生案例。盲派岁运字段为 `triggers/palaces/work_changes/symbols/event_types`，目前公开展示接口为 `write_zh`，没有专用 `to_json` 重载。

## 怎么跑示例，怎么保存报告

构建 `cmake --build build --config Debug --target example_ba_zi example_ba_zi_mangpai`。
参考 `examples/example_ba_zi.cpp`、`examples/example_ba_zi_mangpai.cpp`：前者主体写 stdout，后者写工作目录下 `docs/example_ba_zi_mangpai_output.txt`，运行前准备 `docs/`。
分析 `write_zh` 不包含完整排盘，制作 txt 必须同时输出排盘；需要流输出而非控制台时按示例读取排盘结构，不存在通用的 `BaZiPresenter::write_zh(out, chart)`。
当前未注册独立八字 CTest；不能以其他术数三套测试通过替代八字结果对照。

## 要改格局、做功或报告，去哪里改

以下文件都在 `src/ba_zi/` 下。函数声明看同名 `.cppm`，算法改 `.cpp`。

| 要改的功能 | 文件 |
| --- | --- |
| 排盘调用、流派选择、把出生时间传给分析器 | `ba_zi_controller.cpp` |
| 子平原局分析 | `analysis/ziping/ba_zi_analysis.cpp` |
| 子平干支关系、岁运 | `analysis/ziping/ba_zi_analysis_relations.cpp`、`ba_zi_analysis_fortune.cpp` |
| 盲派宾主、墓库、做功、岁运 | `analysis/mangpai/` 下的 `mangpai_bing_zhu.cpp`、`mangpai_muku.cpp`、`mangpai_zuo_gong.cpp`、`mangpai_transit.cpp` |
| 基本排盘显示 | `presenter/ba_zi_presenter.cpp` |
| 分析报告 | 对外函数在 `analysis/presenter/ba_zi_report.cpp`，具体排版在 `analysis/ziping/ba_zi_analysis_presenter.cpp` |

报告发现判断错误，回到对应分析文件修改；只是表格或措辞问题，再改展示文件。
