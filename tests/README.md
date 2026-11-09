# 回归测试

通过 `-DBUILD_TESTING=ON` 编译，再使用 `ctest --test-dir build -C Debug --output-on-failure --no-tests=error` 执行。

| 测试 | 核验内容 |
| --- | --- |
| regression_zi_wei | 固定种子命盘、参考网站八案例、星曜亮度表、24 张格局语料及规则正反例 |
| regression_qi_men | 参考盘局、多格局覆盖、固定九宫逐宫预期、中五寄宫决策、分门占断契约 |
| regression_mei_hua | 固定本互变爻码、农历金标准案例、384 卦态、7680 分门组合、边界和真太阳时 |
| regression_da_liu_ren | 固定遥克盘例、四课三传、年干年支月支日干日支全表，以及 8640 组跨层去重检查 |
| regression_liu_yao | 十干、十二支和十二月神煞全表、公共天乙贵人规则及中英文 JSON 一致性 |
| test_tyme_module | Tyme 模块导入、公历转农历能否正常运行（冒烟测试，不核验固定历法预期） |
| regression_planetary_orbit | 八大行星 40816 条几何日心位置对照、TT 边界与非法输入；同源系数，坐标分量容差 1e-11 AU |
| regression_astrology | Kerykeion 的 300 张参考盘、40 张极区盘、100 组本命相位、10 组比较盘/行运/组合盘/推运、20 次返照，以及时区、高纬度和无效输入 |
| regression_jpl_ephemeris | jplephem 独立生成的 1918 组 SPK 状态、插值边界、覆盖边界、坏文件和未知目标 |
| regression_astrology_precision | JPL DE440s 对 Swiss 正式星历的 3000 个星体位置，检查黄经、黄纬、距离、速度、后端隔离及推运返照传递 |

占星回归还核验 `astrology_time_context.json` 中 300 组同口径结果，采用显式 ΔT 与 IAU 2006/2000A。十星黄经阈值为 0.006 度，宫头阈值为 0.000005 度，并检查时间配置在正常、异常和并发调用后的隔离。

塔罗原书素材另运行 `python -B tests/test_tarot_assets.py`，检查 78 张牌的编号、名称、JPEG 格式及 SHA-256。行星轨道对照的范围与参考版本见 [占星接口与验证说明](../docs/astrology/README.md)。

Tyme 测试源文件在 `tests/tyme/test_tyme_module.cpp`，不再放在生产源码目录。
可单独构建 `test_tyme_module`，再用 `ctest --test-dir build -C Debug -R test_tyme_module --output-on-failure --no-tests=error` 运行。

测试只链接生产模块，不定义另一份排盘算法。固定预期是对照基准，不能为迎合当前实现自动更新。
固定案例的逐值断言验证排盘结果；穷举中的字段与数量检查验证输出契约，二者不能互相替代。

CTest 为五套术数回归程序创建 `build/tests/reports/<术数>/` 工作目录，程序产生的 `docs/` 报告均位于该目录下。Tyme 冒烟测试只写控制台，不生成报告文件。
原先随示例输出的紫微对照报告、奇门参考回归报告现由测试生成；示例仍输出完整排盘和中文展示。
需要手动运行测试程序时，也应切换到该独立工作目录，避免覆盖仓库内的报告。

奇门另有手工跨实现工具 `reference_qi_men` 与
`reference/compare_qi_men.cjs`。它以 3meta 2.6.0 提交 `9be1238` 和
idavailable/qimen 提交 `e02a63c` 为外部参考，覆盖六十日十二时辰、二十四节气
附近、子初换日、闰日及远期年份；外部仓库路径通过命令行传入，不进入普通
CTest 和 CI 依赖。

CI 在各平台编译示例及回归目标、核验产物，并执行 CTest；失败返回非零退出码。
报告与 `LastTest.log` 会作为独立 artifact 上传，便于查阅实际值与预期值。
