---
name: zhouyilab-use-daliuren
description: 大六壬起课、设置本命行年和类神、查看四课三传关系与应期、输出报告的用法。
---

## 大六壬怎么起课、看四课三传和应期

先用 `DaLiuRenController` 起课，再把排盘、占问和已知年命传给 `DaLiuRenAnalysis::analyze`。输出报告时，`write_zh` 要同时传排盘和分析结果。

[大六壬使用说明](references/usage.md)里写了本命、行年和类神怎么传，四课三传、天地盘交互及应期从哪些字段读取。年命不知道就留空，不从起课日期猜。
