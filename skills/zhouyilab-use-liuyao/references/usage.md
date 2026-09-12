# 六爻怎么传卦、取用神和输出

## 传入起卦时间、本卦和动爻

```cpp
import ZhouYi.BaZiBase;
import ZhouYi.LiuYaoController;
import ZhouYi.LiuYaoAnalysis.Contract;
import ZhouYi.LiuYaoAnalysis.Presenter;
import nlohmann.json;
import std;

int main() {
  const auto casting_time = ZhouYi::BaZiBase::BaZi::from_solar(2025, 4, 7, 17, 3);
  ZhouYi::LiuYaoAnalysis::AnalysisRequest request;
  request.question_kind = ZhouYi::LiuYaoAnalysis::QuestionKind::CaiYun;
  request.question = "所问回款能否落实";
  const auto result = ZhouYi::LiuYaoController::analyze_liu_yao(
      "110001", casting_time, {1}, request, true);
  ZhouYi::LiuYaoAnalysis::write_zh(std::cout, result.analysis);
  const auto chart_json = result.pai_pan.ai_read_json_data;
  const auto analysis_json = ZhouYi::LiuYaoAnalysis::to_zh_json(result.analysis);
}
```

这些四柱是起卦时刻，不是出生八字。若有地点，使用 `BaZiBase::BaZi::from_solar` 带 second、location 的重载，然后把修正后的四柱传入；六爻控制器本身没有额外的地点参数。

## 爻码、动爻和用神怎么填

- 主卦码恰为六位 `0/1`，从初爻到上爻，0 阴、1 阳；报告表格可自上向下展示，但不能倒转传入字符串。
- 动爻位置是 1—6，不是 0—5；空列表代表静卦，调用者传去重后的动爻集合。
- 只有原始摇卦数时用 `yao_ci_to_hexagram_code(vector<int>, out_changing_lines)`，6 老阴动、7 少阳、8 少阴、9 老阳动，返回本卦码并输出动爻。不要把 6 当阳、9 当阴。
- `numbers_to_hexagram_code(numbers, odd_is_yang=true)` 只按奇偶生成爻码，不会推断老阴老阳或动爻，不能代替上一函数。
- 人工用神通过 `request.specified_yong_shen_position` 指定 1—6；留空按 `question_kind` 取用。枚举见 `src/liu_yao/analysis/liu_yao_analysis_contract.cppm`，财运名为 `CaiYun`，不是其他术数的 `QiuCai`。
- 无效爻码、位置或原始数触发异常；应用边界捕获 `std::exception`，不能假设返回 expected。

## 装卦和分析结果在哪里，怎么输出

纯装卦 `calculate_liu_yao(code,bazi,changing={},generate_ai_json=false)` 返回 `LiuYaoPaiPanResult`，含 `yao_list/json_data/ai_read_json_data`。`analyze_liu_yao` 返回组合对象 `.pai_pan` 与 `.analysis`。
分析结果包括 `.yao`、`.yong_shen`、`.relations`、`.judgment`、`.conclusion` 和 `.review_points`；逐爻查看 `.is_changing`、旬空月破及动化依据。
中文 JSON 分两份：原排盘仅当 `generate_ai_json=true` 生成，分析始终可以由 presenter `to_zh_json(analysis)` 生成；两者不是同一格式。
`write_zh(out, analysis)` 只接收分析对象，不能传 `(out, pan, analysis)`。完整 txt 的原排盘表还需从 `.pai_pan.yao_list` 输出六神、世应、动静、本变干支等；已有示例演示装卦明细，不要声称该 presenter 已单独输出所有装卦字段。

## 怎么跑示例

装卦入口：`src/liu_yao/liu_yao_controller.cppm`；断卦入口及关系：`src/liu_yao/analysis/liu_yao_analysis*.cppm`；示例：`examples/example_liu_yao.cpp`。
构建 `cmake --build build --config Debug --target example_liu_yao`。当前示例写 stdout，不会自动创建六爻 txt；需要文件时在调用程序用输出流组织完整排盘和分析，或明确重定向完整示例 stdout。
尚无独立六爻 CTest，新增关系必须另补断言，尤其检查半合、三合、动变与月日参与的区别。

## 要改装卦、断卦或报告，去哪里改

以下文件在 `src/liu_yao/` 下：

- 爻码检查、动爻转换和调用流程：`liu_yao_controller.cpp`；具体装卦功能再查 `liu_yao.cppm`，其中仍有原有实现，不要在控制器另算一份。
- 用神选择和断卦：`analysis/liu_yao_analysis.cpp`。
- 爻与爻、月日和卦爻之间的关系：`analysis/liu_yao_analysis_relations.cpp`；基础刑冲合害仍调用共用 GanZhi。
- 中文报告和 JSON：`analysis/liu_yao_analysis_presenter.cpp`。
- 增加返回字段：先改 `analysis/liu_yao_analysis_contract.cppm`，再补计算和输出。
