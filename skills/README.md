# ZhouYiLab 项目 skills

本目录同时维护开发规范和模块使用方法，各子目录中的 `SKILL.md` 可独立阅读。
根目录 `AGENTS.md` 聚合各 skill 的入口正文；具体 API、调用样例与现有局限放在对应 `references/usage.md`，按需读取，不把整本调用手册塞进每次任务上下文。不要手工修改生成文件。
`skills/` 是仓库规范目录，不意味着客户端会自动将其中的 skill 安装为全局技能。

| 目录 | 职责 |
| --- | --- |
| `zhouyilab-workflow/` | 工作范围、工具选择、交付约束 |
| `zhouyilab-modules/` | 模块依赖、cppm 接口与 cpp 实现、设计模式 |
| `zhouyilab-domain/` | 排盘基础复用、流派隔离、历法与真太阳时 |
| `zhouyilab-cpp-style/` | 性能、命名、JavaDoc 风格注释、格式化 |
| `zhouyilab-reporting/` | 中文映射、结构化输出与报告一致性 |
| `zhouyilab-testing/` | 回归基准、示例隔离、验证层次 |
| `zhouyilab-build/` | CMake、三平台模块构建与 CI |
| `zhouyilab-skill-maintenance/` | 规范维护及聚合脚本、脚本测试 |

## 模块使用 skills

| Skill | 使用指南 | 主要调用任务 |
| --- | --- | --- |
| [共用基础](zhouyilab-use-common/SKILL.md) | [调用指南](zhouyilab-use-common/references/usage.md) | 干支、四柱、卦序、历法、中文映射、真太阳时 |
| [八字](zhouyilab-use-bazi/SKILL.md) | [调用指南](zhouyilab-use-bazi/references/usage.md) | 排盘、子平/盲派切换、两路岁运与报告 |
| [紫微斗数](zhouyilab-use-ziwei/SKILL.md) | [调用指南](zhouyilab-use-ziwei/references/usage.md) | 十二宫、亮度、四化、格局、运限 |
| [六爻](zhouyilab-use-liuyao/SKILL.md) | [调用指南](zhouyilab-use-liuyao/references/usage.md) | 爻码、动爻、装卦、取用与占断 |
| [大六壬](zhouyilab-use-daliuren/SKILL.md) | [调用指南](zhouyilab-use-daliuren/references/usage.md) | 起课、年命、课传跨层关系与应期 |
| [奇门遁甲](zhouyilab-use-qimen/SKILL.md) | [调用指南](zhouyilab-use-qimen/references/usage.md) | expected 排盘、九宫、分门占断 |
| [梅花易数](zhouyilab-use-meihua/SKILL.md) | [调用指南](zhouyilab-use-meihua/references/usage.md) | 时间/象数/笔画/闻声、体用互变、报告 |

想用哪个术数，就打开对应的使用说明：里面写了怎么传参数、结果里有什么、怎么输出报告，以及改功能该找哪个文件。
说明中的 C++ 代码放在已配置好的项目中编译，并链接 `ZhouYiLabCore`。不能单独用 `g++ example.cpp` 编译这些模块，具体写法参考 `examples/CMakeLists.txt`。
首次构建参照仓库 README 选择工具链并启用 `BUILD_TESTING`；Ninja 示例通常在 `build/examples/`，Visual Studio 多配置在 `build/examples/Debug/`（Windows 加 `.exe`）。运行时可创建隔离工作目录，用可执行文件绝对路径启动；指南标出的 `docs/...` 均相对该工作目录。

## 生成与检查

需要 Python 3.9+，聚合脚本和单元测试仅使用标准库。在仓库根目录运行：

```sh
python skills/zhouyilab-skill-maintenance/scripts/generate_agents.py
python skills/zhouyilab-skill-maintenance/scripts/generate_agents.py --check
python -B -m unittest discover -s skills/zhouyilab-skill-maintenance/scripts -p "test_*.py" -v
```

脚本根据自身位置定位仓库，不依赖当前工作目录。输出采用 UTF-8、LF、固定顺序，无时间戳；内容相同不重复写入。
`--check` 只读，缺文件、内容不同、源文件非法均返回非零状态。生成前先验证全部 skill，再原子替换输出；拒绝覆盖非本脚本生成的 `AGENTS.md`。

新增 skill 后，将目录名加入 `manifest.json`，顺序即聚合顺序。每个 skill 必须具有 `name`、`description` 单行 YAML 元信息，`name` 与目录同名，正文从二级标题开始（聚合文件统一持有一级标题）。
生成器校验目录越界、符号链接、重复项、清单遗漏及元信息，并检查 skill 入口链接的参考文件存在；聚合后把 `references/` 链接改写到仓库内对应目录，避免根目录 AGENTS 链接失效。详细步骤见维护 skill。
