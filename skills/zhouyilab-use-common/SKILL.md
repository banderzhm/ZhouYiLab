---
name: zhouyilab-use-common
description: 查询干支关系、十神、藏干、四柱、卦名，换算公农历和真太阳时的用法。
---

## 干支、四柱和时间这些基础功能怎么用

查生克、刑冲合害、十神和藏干，用 `GanZhi`。只要四柱用 `BaZiBase`；需要大运再用八字模块。公农历换算用 `tyme`，真太阳时用 `TrueSolarTime`。

[基础模块使用说明](references/usage.md)里列了函数、参数顺序和返回内容。这里特别要注意生克的方向、半合所缺支，以及没传地点时不要修正时间。
