# 现代西方占星

生产入口为 `ZhouYi.Astrology`，天文计算使用随仓库保存的 Astronomy Engine v2.1.19。展示模块为 `ZhouYi.Astrology.Presenter`，示例 `example_astrology` 从公历时间生成完整星盘、行运、组合盘、次限、太阳弧以及太阳和月亮返照报告。数据覆盖 1900—2100 年。生产构建仅需 C++ 工具链，Python 对照工具独立运行。

## 输入与输出

`civil_to_julian` 接收公历年月日时分秒及当时的 UTC 偏移分钟。偏移必须包含夏令时，支持半小时和 45 分钟时区。时间转换采用 UTC，位置计算采用地心视坐标和日期真黄道。`ChartRequest` 接收 UTC 儒略日、地理经纬度、宫制及可选的岁差值。

`calculate_chart` 返回太阳、月亮、水星、金星、火星、木星、土星、天王星、海王星、冥王星的黄经、黄纬、距离、黄经速度、星座和落宫，并保留上升点、天顶及十二宫宫头。下降点与天底按对轴输出。宫制支持整宫、等宫、普拉西德。普拉西德在超出可计算纬度时抛出异常，调用者可显式选择其他宫制。

默认采用热带黄道。提供 `ayanamsa` 时采用调用者明确指定的恒星黄道岁差值，单位度。当前接口支持显式数值配置，命名岁差模型及其逐时演化由调用者提供。次限、返照等派生时刻若需变动岁差值，应以目标时刻的值重新配置星盘。

## 现代西占功能

| 功能 | 入口与计算口径 |
| --- | --- |
| 本命盘 | `calculate_chart` |
| 行运盘 | 用行运 UTC 时刻调用 `calculate_chart`，`transit_aspects` 固定本命星体，按行运速度判断阶段 |
| 比较盘 | `cross_aspects`，保留第一盘与第二盘的参与方向，双方速度都参与阶段判断 |
| 中点组合盘 | `composite_chart`，对应星体与宫头取短弧中点，宫头按第一宫起点整理顺序；正好对冲时采用较小黄经加九十度的确定口径 |
| 次限推运 | `secondary_progression`，365.2422 日对应一星历日，宫轴采用推运时刻及出生地点 |
| 太阳弧推运 | `solar_arc_progression`，取次限太阳与本命太阳之弧，统一移动星体与宫轴 |
| 太阳/月亮返照 | `find_return` 搜索目标热带黄经，`return_chart` 按返照地点重算宫位 |
| 相位 | 合、半六合、半刑、六合、五分、刑、拱、补八分、倍五分、梅花及冲，角度与容许度可配置 |
| 输出 | `Presenter::to_json`、`Presenter::write_zh` 同时使用已算星盘与相位，保留派生来源与推运目标 |

组合盘的地心距离为空。相位阶段使用 0.001 日线性速度投影，偏差变化小于 1e-6 度标为近静止。宫头使用半开区间，精确落在宫头时归该宫。十星输出是本模块的星体范围。

## 独立对照

参考库为 Kerykeion 5.12.6，其底层为 pyswisseph 2.10.3.2。独立语料在 `tests/reference/astrology_kerykeion.json`。生成器使用固定随机种子、100 个时刻、南北半球与国际日期变更线附近地点，分别计算三种宫制，共 300 张盘。另加 40 张极区参考盘，直接由 Swiss Ephemeris 求值，覆盖南北纬 66.5°、80°。它们曾检出并修复极圈内上升点交点选择错误。另含 100 组本命相位、十组比较盘/行运/组合盘、二十次返照。次限与太阳弧的星历预期额外通过 Swiss Ephemeris 独立求值。

参考生成命令为 `python tests/reference/generate_astrology_kerykeion.py`，依赖版本在同目录 `requirements-astrology.txt`。运行 CTest 的 `regression_astrology` 即可核验固定语料，无须安装参考库。`reference_astrology` 可从 JSON 案例导出生产结果，`tests/reference/compare_astrology.py` 将逐字段误差汇总到调用者指定的构建目录。

在当前语料中，最大黄经差约 0.019192 度，最大宫头差约 0.006926 度，最大速度差约 0.000507 度/日。两个引擎采用不同的天文近似与时间模型，星盘角度采用容差比较。测试黄经容差 0.025 度、宫头容差在绝对纬度六十度以下为 0.002 度，高纬度为 0.01 度。相位与落宫处于边界 0.05 度以内时单列为边界敏感；近静止阈值附近的相对速度另行处理。返照时刻与参考库的分钟精度模型存在舍入差，跨库回归容差为 300 秒，搜索器自身收敛区间小于 0.05 秒。

## 时间与宫轴精度优化

宫轴计算采用 ERFA 2.0.1 的 IAU 2006 岁差、IAU 2000A 章动和 Greenwich apparent sidereal time。ERFA 的原始来源、版本及许可保存在 `3rdparty/erfa/`。Astronomy Engine 继续计算星体位置。

`ChartRequest.delta_t_seconds` 可显式提供本时刻的 TT−UT1，单位秒，空值使用 Espenak–Meeus 默认模型。`body_longitude`、`find_return` 以及推运、返照入口也接受可选的 ΔT。出生时刻与推运或返照时刻分别配置，后端回调在互斥锁内按作用域恢复。JSON 与中文输出记录实际使用的数值。当前输入 UTC 作为 UT1 的近似，实际观测精度还受 DUT1 与未来地球自转预测影响。

原始 Kerykeion 参考语料保持独立。另有 `astrology_time_context.json`，通过 `generate_astrology_time_context.py` 生成。该工具配置 Swiss Ephemeris 的模型字符串 `5,8,8,3,3,0,0,3`，明确使用 IAU 2006、IAU 2000A、IERS 2010 恒星时，并记录每例的 ΔT。原始默认模型对照与同口径对照均注册在回归程序中。参考库原来的长期恒星时模型在 2050 年后与 IAU 标准结果有差异，因此两类对照分别报告。

在同一批 300 张盘的同口径对照中，月亮最大黄经差为约 0.000906 度，全部十星最大黄经差约 0.005136 度，最大宫头差约 0.000001203 度。这里的时间参数来自独立参考输入。默认请求仍按其默认时间模型计算，跨模型差异另行保留。固定验收阈值为十星黄经 0.006 度、宫头 0.000005 度，另检查显式参数恢复、异常和并发隔离。

同口径报告的生成命令为 `python tests/reference/compare_astrology.py <reference_astrology路径> <构建报告目录> --aligned-time`。省略最后一个参数即可核验原始参考库的默认模型。

## JPL 高精度后端

`ChartRequest.ephemeris_file` 指定 JPL SPK Type 2 文件路径时，十星采用该文件的高精度状态。空值采用 Astronomy Engine。生产入口、次限、太阳弧、行运和返照均传递这一配置，JSON 与中文报告记录实际后端和文件来源。星历文件失败时抛出异常。

仓库附有 NASA/JPL DE440s 原始文件 `assets/ephemeris/de440s.bsp`，约 32 MB。下载来源、SHA-256 和复现命令见同目录 README。`./tools/fetch_de440s.ps1` 核验文件或获取固定版本。文件随 CMake 安装至 `share/zhouyilab/ephemeris`，应用通过路径配置选择它。

解析器位于 `jpl_ephemeris.cppm/.cpp`，实现 DAF 摘要、SPK Type 2 切比雪夫位置及导数求值，并沿目标与中心链恢复太阳系质心状态。视位置另处理 TT 至 TDB、光行时迭代、有限距离太阳引力偏折、光行差，以及 ICRF 至日期真黄道转换。缓存最多保存一个不可变内核。格式、边界、未知目标和损坏文件返回明确错误。

独立验证包含两部分。`jpl_states.json` 由 jplephem 2.24 从原始文件生成，覆盖 1918 组位置和速度，含文件覆盖端点及插值区间附近。`astrology_jpl_reference.json` 由 Swiss Ephemeris 正式星历文件 `sepl_18.se1`、`semo_18.se1` 生成，记录文件哈希，并检查计算返回标志必须包含 `FLG_SWIEPH`。原有参考配置曾回退到 Moshier，相关语料与高精度语料分别保存。

同 ΔT 与 IAU 标准下，300 张盘、3000 个星体位置的十星最大黄经差约 `0.000001969°`，海王星最大差约 `0.000000134°`。宫头最大差约 `0.000001203°`。高精度回归阈值为黄经、黄纬各 `0.000005°`，速度 `0.0001°/日`，距离相对差 `1e-6`。这些数值表示两套计算的一致性，实际天空位置还受时间输入、DUT1、观测地点和星历本身影响。

复现命令如下。

```sh
python tests/reference/generate_jpl_state_reference.py
python tests/reference/generate_astrology_time_context.py --ephe-directory <Swiss星历目录> --output tests/reference/astrology_jpl_reference.json
python tests/reference/compare_astrology.py <reference_astrology路径> <构建报告目录> --aligned-time --jpl
example_astrology assets/ephemeris/de440s.bsp
```

## 日心轨道旧接口

`ZhouYi.Astrology.PlanetaryOrbit` 暴露 `heliocentric_position(Planet, tt_days)`。输入为 J2000 起算的地球时 TT 日数，结果为日心几何位置，采用 J2000 赤道框架，单位 AU。公开接口范围暂定为 1900 年至 2100 年，精确数值界限见接口与实现。调用者必须先准备 TT，不能直接传民用日期或 UTC 日数。

求值器由项目实现。466 个 VSOP 数值项及框架旋转系数取自 Astronomy Engine v2.1.19，原始 MIT 许可保存在 `assets/licenses/astronomy-engine-MIT.txt`。系数与参考实现同源，因此对照结果只证明当前求值与坐标转换的一致性。天文模型的独立准确度还需其他星历作基准。

该低层接口保留水星、金星、地球、火星、木星、土星、天王星、海王星的几何日心位置。现代西占入口使用上文的完整天文后端，两个入口的时间与坐标口径分别明确。

`tests/reference/generate_astronomy_reference.cjs` 用固定版本的参考引擎生成 40,816 条预期，包含连续网格、固定种子随机时间及 J2000 两侧边界。时间、坐标口径和参考文件 SHA-256 保存在配套 JSON。普通 CTest 直接读取固定语料，通过 `regression_planetary_orbit` 核验生产入口，分量容差为 `1e-11 AU`。

生成系数命令为 `python tools/generate_vsop_coefficients.py path/to/astronomy.c`。生成预期命令为 `node tests/reference/generate_astronomy_reference.cjs path/to/astronomy.cjs`。参考源均应使用 https://github.com/cosinekitty/astronomy/tree/v2.1.19 。
