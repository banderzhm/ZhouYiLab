# 紫微怎么排盘、看星曜和格局

## 排一个紫微盘，输出排盘和格局

`src/zi_wei/zi_wei.cppm` 导出 `ZhouYi.ZiWei`；命名空间为 `ZhouYi::ZiWei`，排盘不是 Controller 类的静态函数。

```cpp
import ZhouYi.ZiWei;
import ZhouYi.ZiWei.Controller;
import std;

int main() {
  const auto chart = ZhouYi::ZiWei::pai_pan_lunar(2000, 6, 15, 16, true);
  std::cout << chart.to_string();
  ZhouYi::ZiWei::display_ge_ju_analysis(chart);
  const std::string json = ZhouYi::ZiWei::export_to_json_full(chart);
}
```

`export_to_json_full` 返回 JSON **字符串**，不是 json 对象，不能对它调用 `.dump()`。需要对象再 import `nlohmann.json` 并解析；仅需要报告直接写字符串即可。

## 日期、亮度和宫位顺序怎么设置

| API/字段 | 当前语义 |
| --- | --- |
| `pai_pan_solar(y,m,d,h,is_male,location=nullopt,config={})` | 公历，小时参数，当前没有 minute 参数 |
| `pai_pan_lunar(y,m,d,h,is_male,is_leap_month=false,config={})` | 农历；有兼容闰月 bool 参数，但当前实现未使用该 bool，实际转历依赖月份正负号 |
| `ZiWeiConfig::brightness_school` | 默认 `BrightnessSchool::MetisDefault` |
| `ZiWeiConfig::kui_yue_school` | 默认 `KuiYueSchool::QuanShuOne` |
| `palaces[0..11]` | 从寅宫起，不是子宫起，也不是命宫为 0 |
| `ming_gong_index/shen_gong_index` | 同一寅起宫序；不是 GongWei 枚举值 |

指定配置时 import `ZhouYi.ZiWei.Brightness`、`ZhouYi.ZiWei.Constants` 并查所属枚举，不照猜名称。
`get_palace(GongWei)` 按宫职查；`get_palace_by_index(index)` 按寅起宫序查。十二宫各含 `gong_data`、主辅煞杂曜、空宫借星、十二神及大小限数据。

16:30 在未修正时可归 16 时所在时辰，但接口不能保存分钟。真太阳时可能跨时辰，不能把 16:30 直接截成 16:00 后再修正：调用 `Time::correct` 时必须用完整分钟，取得修正年月日小时后调用核心公历入口且 location 传空，防止二次修正。另存原输入/偏移，明确这仍是上层适配，不是核心已支持分钟。
农历 location 当前没有入口；需先转公历。闰月对照应使用已验证负月份语料，不能假设 `is_leap_month=true` 生效。

## 格局、四化和运限怎么查

- `display_ge_ju_analysis`、`display_gong_gan_si_hua`、`display_zi_hua_analysis`、`display_san_fang_si_zheng` 是当前 `ZhouYi.ZiWei.Controller` 的兼容显示函数，写控制台；不是一个不存在的统一分析 presenter。
- `export_to_json_full(chart)` 导出的 JSON 包含格局、四化等；`export_to_json(chart)` 只导出基础资料，要完整报告就用前者。
- 深入格局规则用 `ZhouYi.ZiWei.GeJu` 的 `GeJuAnalyzer(PatternChart)` → `analyze_all()`；不能直接传 `ZiWeiResult`。当前 `prepare_pattern_chart` 在 controller 实现内部，并非公开转换 API，不应在调用示例里杜撰其可调用性。
- `GeJuInfo` 读取 `status/level/basis.required/basis.bonus/basis.breaking/source`；兼容 `score` 不能替代成格/破格条件。
- `chart.get_horoscope(target_year,target_month,target_day,target_hour,current_age)` 使用公历目标年、农历目标月日、地支时辰、虚岁。使用时显式 import `ZhouYi.ZiWei.Horoscope` 与 `ZhouYi.GanZhi`，不要混成全公历参数。

## 怎么跑示例和对照测试

```sh
cmake --build build --config Debug --target example_zi_wei regression_zi_wei
ctest --test-dir build -C Debug -R regression_zi_wei --output-on-failure --no-tests=error
```

`examples/example_zi_wei.cpp` 输出工作目录下 `docs/ziwei/example_zi_wei_output.txt`；`tests/regression_zi_wei.cpp` 包含网站对照、亮度与格局正反例。先核对历法、性别、时辰、闰月和配置，再比十二宫、星曜、亮度、四化与格局，不能只比较总分或宫名文本。

## 要改安星、亮度、格局或报告，去哪里改

以下文件在 `src/zi_wei/` 下：

- 总排盘流程：`zi_wei.cppm` 中仍有原有实现；安星函数查 `zi_wei_star.cpp`，不要因本项目要求 cppm/cpp 分离就误认旧实现已经全部拆完。
- 星曜亮度：`zi_wei_brightness.cpp`。
- 格局条件：`zi_wei_geju.cpp`；格局所用的命盘资料和索引：`zi_wei_pattern.cpp`。
- 四化：`zi_wei_sihua.cpp`；三方四正：`zi_wei_sanfang.cpp`；运限：`zi_wei_horoscope.cpp`。
- 现有控制台展示和 JSON 导出：`zi_wei_controller.cpp`；逐宫文字还有一部分在 `zi_wei.cppm` 的 `to_string()` 中。

这是当前文件分工。后续拆分实现时保留原注释，并同步修改这里的路径。
