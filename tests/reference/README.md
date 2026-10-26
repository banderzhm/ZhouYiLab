# 紫微斗数 iztro 跨实现对照

本目录的脚本把 ZhouYiLab 生产模块导出的固定命例，与锁定版本的
`SylarLong/iztro` 逐字段比较。外部实现只作为独立参考，不进入
ZhouYiLab 的生产依赖，也不注册为默认 CTest。

当前命例共 20 个，覆盖男女、阴阳年、十二时辰、春节与立春前后、
1900 年历法下界、2090 年远期日期、2033 闰十一月、其他闰月出生、
大限起止虚岁、正月初一和腊月三十。比较范围包括本命十四主星、
亮度、大限、小限、流年、流月、流日、四化和各层流曜。

先在仓库外安装锁定参考版本：

```powershell
npm install --prefix E:\github\iztro-reference-2.6.3 --ignore-scripts --no-audit --no-fund iztro@2.6.3
```

构建 `reference_zi_wei_iztro` 后运行：

```powershell
node tests/reference/compare_zi_wei_iztro.cjs `
  --probe cmake-build-debug-msvc-local-2/tests/reference_zi_wei_iztro.exe `
  --iztro-root E:\github\iztro-reference-2.6.3 `
  --output-dir cmake-build-debug-msvc-local-2/tests/reports/zi_wei/iztro-2.6.3
```

发现差异时脚本返回非零状态，并同时生成 Markdown 明细、JSON 断言和
ZhouYiLab 原始输出。差异表示两个锁定实现的口径或结果不同，不单独
证明任一流派绝对正确。

## 大六壬：kinliuren + Crazycreate/liuyao 三角对照

参考实现不作为 ZhouYiLab 的构建依赖。先分别下载并锁定提交，再构建
`reference_da_liu_ren`，运行：

- `kentang2017/kinliuren`：`557a9ad3ced7b0551b4e623a63dc74536a190a7e`
- `Crazycreate/liuyao`：`a43983b2bf77b48be7e9d668012127e15ec6a0d1`
- 规则文档旁证 `jingrongx/liuren`：`0c5b4cdd2c596fff81c05847b6eb78264c423cd4`

```powershell
node tests/reference/compare_da_liu_ren.cjs `
  --probe cmake-build-debug-msvc-local-2/tests/reference_da_liu_ren.exe `
  --liuyao-root C:/path/to/Crazycreate-liuyao `
  --kinliuren-root C:/path/to/kinliuren `
  --output-dir cmake-build-debug-msvc-local-2/tests/reports/da_liu_ren/reference
```

脚本做两种方向的核验：外部参考实现领入完整六十日循环、十二时辰、中气附近、
闰日和远期日期；再按 ZhouYiLab 自己识别的九宗门挑选代表盘，反向交给两个参考
实现复核。JSON/Markdown 报告会区分历法、月将加时、四课、三传、贵人和昼夜。
`Crazycreate/liuyao` 的当前三传源码在遥克之后直接进入昴星，没有独立的别责、
八专分支；这两类差异会标为 `reference-gap`，不能当作 ZhouYiLab 算错。子初换日、
月将换将以及涉害同深时按“所临孟仲位”还是按“上神本身孟仲”取用等明确口径差异
标为 `school`，由上游差异导致的后续字段标为 `cascade`，
伏吟按古籍“有克从克、无克刚干柔支、自刑换路、再自刑取冲”裁决后标为
`classical-adjudicated`；八专的五个日柱、禁用遥克及阴阳顺逆三位也按古籍归入该类。
ZhouYiLab 与另一外部实现一致、仅第三方离群的差异标为 `consensus-adjudicated`，其余
未裁决差异标为 `strict`。
