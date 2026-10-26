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
