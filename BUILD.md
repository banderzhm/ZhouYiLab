# 构建指南

本文档详细说明如何构建 ZhouYiLab 项目。

## 前置要求

### 编译器要求

项目使用 C++23 标准和模块特性，需要以下编译器之一：

| 编译器 | 最低版本 | C++23 Modules 支持 |
|--------|---------|-------------------|
| GCC    | 14.0+   | 完全支持           |
| Clang  | 17.0+   | 完全支持           |
| MSVC   | 19.35+  | 实验性支持         |

### 构建工具

- **CMake**: 3.28 或更高版本
- **Git**: 用于管理子模块

### 验证环境

```bash
# 检查 CMake 版本
cmake --version

# 检查编译器版本
g++ --version      # GCC
clang++ --version  # Clang
cl                 # MSVC (需要在 Visual Studio Developer Command Prompt 中)

# 检查 Git 版本
git --version
```

## 克隆项目

### 方法1：直接克隆（推荐）

```bash
git clone --recursive https://github.com/banderzhm/ZhouYiLab.git
cd ZhouYiLab
```

### 方法2：分步克隆

```bash
git clone https://github.com/banderzhm/ZhouYiLab.git
cd ZhouYiLab
git submodule update --init --recursive
```

## 构建项目

### Windows 平台

#### 使用 Visual Studio 2022

```powershell
# 生成 Visual Studio 解决方案
cmake -B build -G "Visual Studio 17 2022" -A x64

# 构建项目（Debug 配置）
cmake --build build --config Debug

# 构建项目（Release 配置）
cmake --build build --config Release

# 运行程序
.\build\bin\Debug\ZhouYiLab.exe    # Debug 版本
.\build\bin\Release\ZhouYiLab.exe  # Release 版本
```

#### 使用 Ninja

```powershell
# 生成 Ninja 构建文件
cmake -B build -G "Ninja" -DCMAKE_BUILD_TYPE=Release

# 构建项目
cmake --build build

# 运行程序
.\build\bin\ZhouYiLab.exe
```

### Linux 平台

#### 使用 GCC

```bash
# 配置项目
cmake -B build \
  -DCMAKE_BUILD_TYPE=Release \
  -DCMAKE_CXX_COMPILER=g++

# 构建项目
cmake --build build -j$(nproc)

# 运行程序
./build/bin/ZhouYiLab
```

#### 使用 Clang

```bash
# 配置项目
cmake -B build \
  -DCMAKE_BUILD_TYPE=Release \
  -DCMAKE_CXX_COMPILER=clang++

# 构建项目
cmake --build build -j$(nproc)

# 运行程序
./build/bin/ZhouYiLab
```

### macOS 平台

```bash
# 配置项目
cmake -B build -DCMAKE_BUILD_TYPE=Release

# 构建项目
cmake --build build -j$(sysctl -n hw.ncpu)

# 运行程序
./build/bin/ZhouYiLab
```

## 构建类型

CMake 支持以下构建类型：

| 构建类型 | 描述 | 优化级别 | 调试信息 |
|---------|------|---------|---------|
| Debug | 调试版本 | 无优化 | 完整 |
| Release | 发布版本 | 最大优化 | 无 |
| RelWithDebInfo | 带调试信息的发布版本 | 优化 | 有 |
| MinSizeRel | 最小大小发布版本 | 大小优化 | 无 |

指定构建类型：

```bash
cmake -B build -DCMAKE_BUILD_TYPE=Release
```

## 清理构建

```bash
# 删除构建目录
rm -rf build

# 或者在 Windows PowerShell 中
Remove-Item -Recurse -Force build
```

## IDE 集成

### Visual Studio Code

1. 安装扩展：
   - CMake Tools
   - C/C++
   - C++ Modules (可选)

2. 打开项目文件夹
3. 使用 `Ctrl+Shift+P` 打开命令面板
4. 选择 "CMake: Configure"
5. 选择 "CMake: Build"

### CLion

1. 打开项目
2. CLion 会自动检测 CMakeLists.txt
3. 选择构建配置
4. 点击构建按钮

### Visual Studio

1. 使用 "打开文件夹" 打开项目
2. Visual Studio 会自动配置 CMake
3. 选择构建配置
4. 按 F7 构建

## 常见问题

### 1. CMake 版本过低

**错误**: `CMake 3.28 or higher is required`

**解决**: 从 [CMake 官网](https://cmake.org/download/) 下载最新版本

### 2. 编译器不支持 C++23

**错误**: `C++23 features are not supported`

**解决**: 更新编译器到支持的版本

### 3. 子模块未初始化

**错误**: `third_party/fmt not found`

**解决**:
```bash
git submodule update --init --recursive
```

### 4. MSVC 模块支持

如果使用 MSVC，确保：
- 安装了最新的 Visual Studio 2022
- 启用了 C++ Modules 实验性功能

### 5. 编码问题（Windows）

CMakeLists.txt 已配置 UTF-8 支持：
```cmake
add_compile_options("$<$<C_COMPILER_ID:MSVC>:/utf-8>")
add_compile_options("$<$<CXX_COMPILER_ID:MSVC>:/utf-8>")
```

## 更新依赖

更新第三方库到最新版本：

```bash
git submodule update --remote --merge
git add 3rdparty
git commit -m "Update dependencies"
```

## 性能优化

### Release 构建优化

```bash
cmake -B build \
  -DCMAKE_BUILD_TYPE=Release \
  -DCMAKE_CXX_FLAGS="-O3 -march=native"
```

### 链接时优化 (LTO)

```bash
cmake -B build \
  -DCMAKE_BUILD_TYPE=Release \
  -DCMAKE_INTERPROCEDURAL_OPTIMIZATION=ON
```

## 安装

```bash
# 构建后安装到系统
sudo cmake --install build

# 或安装到自定义目录
cmake --install build --prefix /path/to/install
```

## 交叉编译

### 为 ARM 架构编译（Linux）

```bash
cmake -B build \
  -DCMAKE_TOOLCHAIN_FILE=toolchain-arm.cmake \
  -DCMAKE_BUILD_TYPE=Release
```

## 持续集成

项目准备好集成到 CI/CD 系统：

- GitHub Actions
- GitLab CI
- Jenkins
- Azure Pipelines

示例 GitHub Actions 配置将在 `.github/workflows` 目录中提供。

## 技术支持

如果遇到构建问题，请：

1. 查看本文档的常见问题部分
2. 在 GitHub Issues 中搜索类似问题
3. 创建新的 Issue 并提供：
   - 操作系统和版本
   - 编译器和版本
   - CMake 版本
   - 完整的错误信息

## 参考资源

- [CMake 文档](https://cmake.org/documentation/)
- [C++23 标准](https://en.cppreference.com/w/cpp/23)
- [fmt 库文档](https://fmt.dev/)
- [nlohmann/json 文档](https://json.nlohmann.me/)

