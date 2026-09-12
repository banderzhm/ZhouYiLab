---
name: zhouyilab-build
description: 修改 ZhouYiLab 的 CMake、依赖、模块构建和 GitHub Actions 时保持三平台兼容及真实运行验证。
---

## 模块工具链

- 以 `CMakeLists.txt`、`cmake/DetectStdLibModulePaths.cmake` 和 `.github/workflows/` 为实际构建依据，不从旧文档猜路径。新 CMake policy 先 `if(POLICY ...)` 检查，避免旧版本配置阶段退出。
- `LOCAL` 构建本项目标准库/JSON 模块；`SHARED` 使用预构建模块。BMI 依赖编译器版本、标准库、构建参数和平台，不能跨不兼容工具链复用。
- libc++ 使用实际接受的 `STDLIB_MODULE_DIRS`、`STDLIB_INCLUDE_DIRS`，找不到必需模块时尽早失败，不留到编译时才报 `import std` 缺失。
- macOS 使用配套 LLVM 标准库、ABI 库及 unwinder，验证异常捕获实际运行；不能只凭链接成功判断运行库组合有效。Homebrew 版本与 runner 支持需查当前官方信息，不盲升最新版或默认源码编 LLVM。
- MSVC 错误先核对签名类型的直接 import、完整限定及声明/定义一致性；本地新版 MSVC 通过不能替代 CI 所用版本。优先使用用户提供的本地 BuildTools 做真实构建，读取实际版本，不硬编码某台机器路径到项目。

## CI 与产物

- CI 保留 `BUILD_EXAMPLES=ON`、`BUILD_TESTING=ON` 和 CTest 的 `--no-tests=error`；不通过关测试、跳过失败示例、吞退出码解决故障。
- 普通源码 CI 的纯 Markdown/`docs/**` 改动不触发已有约定的全平台构建；混合源码与文档仍需构建。新增脚本、CMake 或工作流不是纯文档，路径过滤不能漏掉。
- 构建库、主程序、全部已注册示例及回归目标，校验实际目标产物路径后上传；测试失败也保留报告和日志。单配置与多配置目录分别处理。
- CI 故障从第一个实质错误分析，区分安装、配置、扫描、编译、链接、运行和上传阶段；修复后重跑相应阶段。只在用户授权推送后跟踪远端运行，不能把旧提交的绿色状态当作新提交通过。
