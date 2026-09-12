---
name: zhouyilab-use-ziwei
description: 紫微排盘、十二宫星曜亮度、四化、格局和运限的查询与输出。
---

## 紫微怎么排盘、看星曜和格局

先用 `ZhouYi::ZiWei::pai_pan_solar` 或 `pai_pan_lunar` 排盘。十二宫和星曜在 `ZiWeiResult` 里，格局、四化和运限也有现成的查询与输出函数。

[紫微使用说明](references/usage.md)里写了宫位顺序、亮度配置和报告输出。和网站对照前，先核对历法、闰月、时间和安星口径；当前接口不支持的参数也单独列明了。
