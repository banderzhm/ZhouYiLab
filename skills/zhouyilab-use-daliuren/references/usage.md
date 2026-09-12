# 大六壬怎么起课、分析和输出

## 起一课，带上本命行年分析

```cpp
import ZhouYi.DaLiuRen;
import ZhouYi.DaLiuRen.Controller;
import ZhouYi.DaLiuRenAnalysis;
import ZhouYi.DaLiuRenAnalysis.Presenter;
import ZhouYi.GanZhi;
import nlohmann.json;
import std;

int main() {
  const auto pan = ZhouYi::DaLiuRen::Controller::DaLiuRenController::pai_pan_solar(
      2024, 1, 1, 10, 0);
  ZhouYi::DaLiuRenAnalysis::AnalysisRequest request;
  request.question_kind = ZhouYi::DaLiuRenAnalysis::QuestionKind::QiuCai;
  request.question = "所问交易何时有进展";
  request.ben_ming = ZhouYi::GanZhi::DiZhi::Shen;
  request.xing_nian = ZhouYi::GanZhi::DiZhi::Zi;
  const auto analysis = ZhouYi::DaLiuRenAnalysis::analyze(pan, request);
  ZhouYi::DaLiuRenAnalysis::write_zh(std::cout, pan, analysis);
  const auto json = ZhouYi::DaLiuRenAnalysis::to_zh_json(pan, analysis);
}
```

本命申、行年子仅为演示输入，不由上面的起课日期推得。实际求测者没有提供时留空，不推测。

## 时间、本命、行年和类神怎么传

- 公历入口 `(year,month,day,hour,minute=0,location=nullopt)` 支持可选地点；小时为 0—23。
- 农历入口当前仅 `(year,month,day,hour)`，没有 minute/location。需要完整分钟或地点时先转换公历，再调用公历入口，不发明农历重载。
- `AnalysisRequest` 包含占类、占问、本命地支、行年地支、可选 `SeekerGender` 及人工 `specified_lei_shen`（GanZhi::LiuQin）。年命不能用奇门的出生年干代替。
- 起课返回 `DaLiuRenResult`，分析返回独立 `AnalysisResult`，不是两者组合结构；因此 presenter 接收两项对象。应用边界处理历法等异常，非交互程序不要调用 `interactive_pai_pan()`。

## 四课三传、天地盘和应期从哪里看

| 字段 | 里面是什么 |
| --- | --- |
| `method/lesson_patterns` | 九宗门取传法与已有课式 |
| `lessons/transmissions` | 四课上下作用、初中末三传 |
| `personal_markers/eight_doors/lei_shen` | 本命行年临盘、八门与类神 |
| `palaces` | 天地盘逐宫作用 |
| `transmission_relations/cross_layer_relations` | 三传内部及天地盘—四课—三传交互 |
| `patterns/favorable/unfavorable` | 课体、救应、阻隔 |
| `timing` | 应期候选及补齐、出空、合冲触发条件 |
| `review_points` | 缺失条件及待辨点 |

要看“半合所缺支在天盘临什么、地盘临什么，是否在四课三传、月建或年命里”，读取跨层关系和应期中的参与位置、所缺支及依据。具体字段看 `da_liu_ren_analysis_contract.cppm`，不用从报告文字倒推。
`write_zh(out,pan,analysis)` 已组织完整盘局和占断，写 txt 使用检查过的 `ofstream`；JSON 使用 `to_zh_json` 返回对象后 `.dump(2)`。
controller 里的 `display_result*` 属于旧兼容显示，不作为新分析报告实现位置。

## 怎么跑示例、保存报告

`src/da_liu_ren/da_liu_ren_controller.cppm`、`analysis/da_liu_ren_analysis.cppm`、`analysis/da_liu_ren_analysis_contract.cppm`、`analysis/da_liu_ren_analysis_presenter.cppm`。
构建 `cmake --build build --config Debug --target example_da_liu_ren`。
`examples/example_da_liu_ren.cpp` 会输出工作目录的 `docs/da_liuren/example_da_liu_ren_output.txt`。先核对四课三传和取传法，再核对跨层关系与应期。当前没有独立大六壬 CTest，示例成功不等于关系全覆盖。

## 要改取传、关系、类神或报告，去哪里改

以下文件在 `src/da_liu_ren/` 下：

- 起课调用：`da_liu_ren_controller.cpp`；四课、三传等排盘计算查 `da_liu_ren.cpp`。
- 占断流程：`analysis/da_liu_ren_analysis.cpp`。
- 四课三传、天地盘之间的结构关系：`analysis/da_liu_ren_structure.cpp`。
- 按占问取类神：`analysis/da_liu_ren_lei_shen.cpp`。
- 报告表格、应期列表和中文 JSON：`analysis/da_liu_ren_analysis_presenter.cpp`。

改应期时先查 `timing` 的生成位置，再改判断；不要只在报告里补一句结论。
