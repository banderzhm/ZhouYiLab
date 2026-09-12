---
name: zhouyilab-use-qimen
description: 奇门公农历排盘、九宫查询、按占问分析和输出中文报告的用法。
---

## 奇门怎么排盘、查宫和分析占问

先用 `QiMenController::pai_pan_solar` 或 `pai_pan_lunar` 排盘。返回值可能是错误，要先检查成功，再调用 `QiMenAnalysis::analyze`。

[奇门使用说明](references/usage.md)里写了九宫查询、占问门类、年命和中文报告。这里的 `nian_ming` 是出生年干，不是大六壬的本命地支。
