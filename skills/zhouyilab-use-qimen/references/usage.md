# 奇门怎么排盘、查宫和分析

## 排盘成功后，再分析占问

```cpp
import ZhouYi.QiMen;
import ZhouYi.QiMen.Pan;
import ZhouYi.QiMen.Controller;
import ZhouYi.QiMen.Analysis;
import ZhouYi.QiMen.Analysis.Presenter;
import ZhouYi.GanZhi;
import nlohmann.json;
import std;

int main() {
  const auto pan = ZhouYi::QiMen::QiMenController::pai_pan_solar(2000, 7, 16, 16, 30);
  if (!pan) {
    std::cerr << pan.error() << '\n';
    return 1;
  }
  ZhouYi::QiMenAnalysis::AnalysisRequest request;
  request.question_kind = ZhouYi::QiMenAnalysis::QuestionKind::GongMing;
  request.question = "所谋事业门径如何";
  request.nian_ming = ZhouYi::GanZhi::TianGan::Geng;
  const auto analysis = ZhouYi::QiMenAnalysis::analyze(*pan, request);
  ZhouYi::QiMenAnalysis::write_zh(std::cout, *pan, analysis);
  const auto json = ZhouYi::QiMenAnalysis::to_zh_json(*pan, analysis);
}
```

`nian_ming` 是求测者出生年干，不是地支，也不是由起局年份自动充填。未知可空。

## 日期怎么传，九宫怎么查

- `QiMenController::pai_pan_solar/lunar(year,month,day,hour,minute=0,location=nullopt)` 都返回 `std::expected<QiMenPan,std::string>`。
- 农历 month 负数表示闰月；小时 0—23，分钟 0—59；地点空时使用原时刻。不可对失败的 expected 解引用。
- 查询 `QiMenController::query_palace_info(*pan, Palace)` 也返回 expected，要独立检查；宫序使用 `Palace`，不能用紫微十二宫索引代替。
- 当前入口由时间自动排局，没有公开“指定局数/任意流派”参数；若有此需求先查 `QiMen.Pan` 与实现，不编造 config 字段。

## 分析结果里有什么，怎么输出报告

需要用神、主客、格局和应期分析时，调用 `ZhouYi::QiMenAnalysis::analyze`；旧 `QiMenAnalyzer::get_summary` 只提供摘要。
`AnalysisResult` 的 `yong_shen` 是用神落宫，`palace_readings` 是主用诸宫门星神仪及格局，`main_guest` 是日时主客，`global_configuration` 是全局结构；另有助碍、`manifestations` 分门取象、`timing` 应期与方位和 `review_points`。
占问门类用 `question_kind` 设置，程序不会根据 `question` 的文字自动判断占类。九类占问的枚举名称见 `qi_men_analysis_contract.cppm`。
`Dun`、`PatternNature`、`QuestionKind`、`YongShenRole`、`PalaceRelation`、`EffectNature`、`Judgment` 都有 `ZhouYi.ZhMapper` 的 `ZhMap` 特化，下游取中文名直接 import `ZhouYi.ZhMapper` 后调用 `ZhouYi::Mapper::to_zh(value)`，不要自己抄一份中文表；presenter 的中文与此同源。
完整文本用 `write_zh(out,pan,analysis)`；完整中文 JSON 用 `to_zh_json(pan,analysis)`。若只要盘面，兼容 controller 的 `get_pan_description`、`get_pan_json` 返回字符串，`get_pan_json_object` 才返回对象。

## 怎么跑示例和测试

入口在 `src/qi_men/qi_men_controller.cppm`，分析在 `src/qi_men/analysis/qi_men_analysis.cppm`，契约和 presenter 在同目录。

```sh
cmake --build build --config Debug --target example_qi_men regression_qi_men
ctest --test-dir build -C Debug -R regression_qi_men --output-on-failure --no-tests=error
```

`examples/example_qi_men.cpp` 演示同盘九类占问，写工作目录下 `docs/qimen/example_qi_men_analysis_output.txt`。示例没有 `--date` 参数；要换日期，改示例中的排盘参数，或者在自己的程序里调用排盘函数。

## 要改排局、取用或报告，去哪里改

以下文件在 `src/qi_men/` 下：

- 公农历输入：`qi_men_controller.cpp`；具体排盘计算查 `qi_men_pan.cpp` 和 `qi_men.cpp`。
- 分析流程：`analysis/qi_men_analysis.cpp`。
- 用神落宫：`analysis/qi_men_yong_shen.cpp`。
- 占断判断：`analysis/qi_men_judgment.cpp`。
- 中文报告和 JSON：`analysis/qi_men_analysis_presenter.cpp`。

增加分析字段时同步改 `qi_men_analysis_contract.cppm`，不要在展示层单独计算。
