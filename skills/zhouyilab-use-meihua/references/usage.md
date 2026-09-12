# 梅花怎么起卦、看体用和应期

## 用时间、数字、笔画还是声音起卦

所有下列函数在 `ZhouYi::MeiHuaController`，import `ZhouYi.MeiHua.Controller`。

| 输入 | 仅起卦 | 起卦并分析 |
| --- | --- | --- |
| 年月日时 | `cast_by_time(time_request)` | `analyze_by_time(time_request,analysis_request={})` |
| 上下两数 | `cast_by_numbers(upper,lower,moving=nullopt)` | `analyze_by_numbers(upper,lower,moving,request={})` |
| 上下字组笔画合数 | `cast_by_strokes(upper,lower,moving=nullopt)` | `analyze_by_strokes(upper,lower,moving,request={})` |
| 声数、时支序数 | `cast_by_sound(count,hour_branch_number)` | `analyze_by_sound(count,hour_branch_number,request={})` |

两数/笔画分析入口的第三数参数没有默认实参；不提供第三数须显式传 `std::nullopt`。仅起卦返回 `MeiHuaPan`，分析入口返回 `MeiHuaAnalysisResult{pan,analysis}`。

## 按农历时间起一卦并输出分析

```cpp
import ZhouYi.MeiHua.Controller;
import ZhouYi.MeiHua.Analysis.Presenter;
import nlohmann.json;
import std;

int main() {
  const ZhouYi::MeiHuaController::TimeCastingRequest time{
      .calendar = ZhouYi::MeiHuaController::CalendarKind::Lunar,
      .year = 2000, .month = 6, .day = 15, .hour = 16, .minute = 30,
      .location = std::nullopt};
  ZhouYi::MeiHuaAnalysis::AnalysisRequest request;
  request.question_kind = ZhouYi::MeiHuaAnalysis::QuestionKind::QiuCai;
  request.question = "所谋合作能否得财";
  const auto result = ZhouYi::MeiHuaController::analyze_by_time(time, request);
  ZhouYi::MeiHuaAnalysis::write_zh(std::cout, result.pan, result.analysis);
  const auto json = ZhouYi::MeiHuaAnalysis::to_zh_json(result.pan, result.analysis);
}
```

## 这些参数别传错

- `TimeCastingRequest` 接收钟表小时 0—23、分钟 0—59，历法用 `CalendarKind` 指定；即使传公历也由控制器转换为农历数起卦。地点缺省不修正。
- 闻声 `hour_branch_number` 是子一至亥十二，申时是 9，不是 16。笔画由用户按字书口径给出，不提供自动汉字转笔画 API。
- 控制器象数要求正数，和数溢出抛 `std::overflow_error`，非正数/无效时支序数抛 `std::invalid_argument`；不要把底层取余支持任意整数误当作控制器接受负数。
- 数字卦没有自然生成的月令与精确时刻，不能把默认 `lunar_month=0` 当某个月，也不要据此显示确定日期的应期。
- `AnalysisRequest` 包含占类、问题、`event_pace`（默认自动）、`external_omens`；外应要给观察内容与已知卦象/数，不能捏造所见所闻。文字 question 用于留档，不自动替代占类枚举。

## 本互变、体用和应期从哪里看

`pan.ben_gua/hu_gua/bian_gua` 是本互变，`moving_line` 为 1—6，`ti_yong` 是体用分判；`source_numbers` 和 `upper_total/lower_total/moving_total` 保留计算来源。
时间卦另有 `civil_time/casting_time/lunar_date/true_solar_time_applied/time_offset_minutes`，报告直接用这些字段，不再次修正。
`analysis` 读取 `ben_yong/hu_influences/bian_influence`、`moving_line`、`party_balance`、`question_reading`、`omen_readings`、`timing`、`judgment_detail` 和 `review_points`；不能只取总断忽略应期成立条件。
已有盘需要重新选择占类时 import `ZhouYi.MeiHua.Analysis` 后调用 `ZhouYi::MeiHuaAnalysis::analyze(pan,request)`，不必重新起卦。

## 怎么跑示例和测试

`src/mei_hua/mei_hua_controller.cppm` 为入口，`mei_hua.cppm` 为盘局，`analysis/mei_hua_analysis_contract.cppm` 与 `mei_hua_analysis_presenter.cppm` 为契约/报告。

```sh
cmake --build build --config Debug --target example_mei_hua regression_mei_hua
ctest --test-dir build -C Debug -R regression_mei_hua --output-on-failure --no-tests=error
```

`examples/example_mei_hua.cpp` 展示时间、两数、笔画、闻声，输出工作目录下 `docs/meihua/example_mei_hua_output.txt`。运行回归仍须通过 CTest 的隔离目录，不能覆盖这个示例报告。

## 要改起卦、体用或应期，去哪里改

以下文件在 `src/mei_hua/` 下：

- 各种起卦输入及检查：`mei_hua_controller.cpp`；本互变等基础计算：`mei_hua.cpp`。
- 分析流程：`analysis/mei_hua_analysis.cpp`。
- 体用判断：`analysis/mei_hua_ti_yong.cpp`。
- 按占类判断：`analysis/mei_hua_question_rules.cpp`。
- 外应：`analysis/mei_hua_omen.cpp`；应期：`analysis/mei_hua_timing.cpp`。
- 中文报告和 JSON：`analysis/mei_hua_analysis_presenter.cpp`。

对外函数和数据结构在对应 `.cppm` 中；改文案与改算法分开，不在报告里另算体用。
