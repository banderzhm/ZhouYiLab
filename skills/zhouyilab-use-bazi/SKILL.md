---
name: zhouyilab-use-bazi
description: 八字公农历排盘、子平与盲派切换、岁运分析和中文报告的用法。
---

## 八字怎么排盘和分析

排盘用 `BaZiController::pai_pan_solar` 或 `pai_pan_lunar`。`AnalysisRequest.method` 选子平或盲派；看岁运时，子平用 `analyze_sui_yun`，盲派用自己的 `Transit::analyze`。

[八字使用说明](references/usage.md)里写了出生时间怎么传、怎么切换流派、怎么看大运，以及排盘和分析怎么一起输出。要改格局、做功或岁运，先看说明末尾的文件分工。
