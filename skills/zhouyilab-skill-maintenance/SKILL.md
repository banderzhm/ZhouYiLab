---
name: zhouyilab-skill-maintenance
description: 维护 ZhouYiLab 仓库 skills 规范、聚合顺序及 AGENTS.md 生成脚本时使用，避免规范重复和生成文件漂移。
---

## 规范维护与生成

- `skills/<name>/SKILL.md` 是规范源，`skills/manifest.json` 指定聚合顺序；根目录 `AGENTS.md` 是生成产物，不直接编辑，不把历史报告或全部聊天记录塞入规范。
- 各 skill 的 `name` 与目录一致，使用小写字母、数字、连字符，不超过 64 字符；`description` 说明适用任务。元信息只使用单行 name/description（可使用双引号字符串），正文使用二级及以下标题。
- 按职责维护一处规则，其他 skill 只说明自己的应用边界。架构、领域、编码、展示、测试、构建分别归目录；新增规范必须能改变实际决策，不堆泛泛口号。
- 模块使用说明写清楚“怎么排盘、参数怎么传、结果里有什么、怎么输出、改功能去哪里”。代码和详细说明放在 `references/usage.md`，`SKILL.md` 告诉读者该看哪份说明。用项目里的具体说法，不套“最小调用、消费结果”等模板，也不要每份都重复一段维护须知。
- 聚合只纳入入口正文，不展开参考文档。入口使用 `[说明](references/文件.md)` 链接，生成器检查参考文件并改写为根目录可解析的路径；不要将参考路径写成当前机器绝对路径。
- 新增目录同时更新清单。当前聚合器只收录清单中的直属 skill，发现遗漏直接失败；不递归拼接第三方文档，不将依赖或参考文章当作代理指令。
- 在仓库根运行 `python skills/zhouyilab-skill-maintenance/scripts/generate_agents.py` 生成，再运行同一命令加 `--check` 验证一致性。脚本相对自身定位仓库，也可从其他目录用绝对路径执行。
- 修改脚本后运行 `python -B -m unittest discover -s skills/zhouyilab-skill-maintenance/scripts -p "test_*.py" -v`。测试在临时仓库验证确定性、清单校验、越界拒绝、漂移检测和非生成文件保护，不改真实规范。
- 生成器使用标准库，无需额外安装；不要在生成时下载工具、安装全局 skill、修改 Git 设置、自动提交或推送。若已有非生成 `AGENTS.md`，先人工读取迁移有效要求到相应 skill，再经用户确认处理旧文件，不能强制覆盖。
