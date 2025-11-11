# ZhouYiLab 构建结构说明

## 📦 项目架构

项目采用**核心库 + 可执行文件**的模块化架构：

```
ZhouYiLab 项目
├── ZhouYiLabCore（静态库）    ← 核心库，包含所有模块和实现
│   ├── 所有 .cppm 模块接口文件
│   ├── 所有 .cpp 实现文件（除 main.cpp）
│   ├── 第三方库（fmt, nlohmann_json, magic_enum）
│   └── 标准库模块（std, std.compat）
│
├── ZhouYiLab（可执行文件）     ← 主程序
│   ├── src/main.cpp
│   └── 链接 → ZhouYiLabCore
│
├── ZhouYiLabTests（可执行文件） ← 测试程序
│   ├── tests/*.cpp
│   ├── 链接 → ZhouYiLabCore
│   └── 链接 → doctest
│
└── Examples（可执行文件组）     ← 示例程序
    ├── example_ba_zi
    ├── example_da_liu_ren
    ├── example_liu_yao
    ├── example_zi_wei
    └── 每个都链接 → ZhouYiLabCore
```

## 🔧 构建目标

### 1. **ZhouYiLabCore** (核心库)
- **类型**: 静态库 (STATIC)
- **包含**: 
  - 所有 C++23 模块 (`.cppm`)
  - 所有实现文件 (`.cpp`，除了 `main.cpp`)
  - tyme 模块实现
  - magic_enum 模块
  - 标准库模块 (`std`, `std.compat`)
- **链接库**: 
  - `fmt::fmt` (PUBLIC)
  - `nlohmann_json_modules` (PUBLIC)
- **作用**: 
  - 提供所有核心功能和模块
  - 被主程序、测试程序、示例程序共享

### 2. **ZhouYiLab** (主程序)
- **类型**: 可执行文件
- **包含**: `src/main.cpp`
- **链接**: `ZhouYiLabCore` (PRIVATE)
- **作用**: 主程序入口

### 3. **ZhouYiLabTests** (测试程序)
- **类型**: 可执行文件
- **包含**: 
  - `tests/test_main.cpp`
  - `tests/test_*.cpp`
- **链接**: 
  - `ZhouYiLabCore` (PRIVATE)
  - `doctest::doctest` (PRIVATE)
- **作用**: 单元测试

### 4. **Examples** (示例程序)
- **类型**: 多个可执行文件
- **包含**: 
  - `example_ba_zi.cpp`
  - `example_da_liu_ren.cpp`
  - `example_liu_yao.cpp`
  - `example_zi_wei.cpp`
- **链接**: `ZhouYiLabCore` (PRIVATE)
- **输出位置**: `${CMAKE_BINARY_DIR}/examples/`

## 🎯 优势

### 1. **模块复用**
- 核心库只编译一次
- 所有可执行文件共享同一份编译后的模块
- 大幅减少编译时间

### 2. **依赖传递**
- 核心库使用 `PUBLIC` 链接第三方库
- 所有链接到核心库的目标自动继承依赖
- 无需重复配置

### 3. **清晰分离**
- 核心功能 (ZhouYiLabCore)
- 主程序入口 (ZhouYiLab)
- 测试代码 (ZhouYiLabTests)
- 示例代码 (Examples)

### 4. **易于扩展**
- 添加新模块：只需加入核心库
- 添加新示例：在 `examples/` 目录添加文件，自动识别

## 🚀 使用方式

### 构建所有目标
```bash
cmake --build <build-dir> -j
```

### 构建特定目标
```bash
# 只构建核心库
cmake --build <build-dir> --target ZhouYiLabCore

# 只构建主程序
cmake --build <build-dir> --target ZhouYiLab

# 只构建测试程序
cmake --build <build-dir> --target ZhouYiLabTests

# 只构建特定示例
cmake --build <build-dir> --target example_ba_zi
```

### 运行程序
```bash
# 运行主程序
./ZhouYiLab

# 运行测试
./ZhouYiLabTests
# 或
ctest

# 运行示例
./examples/example_ba_zi
./examples/example_da_liu_ren
./examples/example_liu_yao
./examples/example_zi_wei
```

## 📝 开发注意事项

### 添加新模块
1. 在 `src/` 相应目录创建 `.cppm` 和 `.cpp` 文件
2. 模块会自动被 `file(GLOB ...)` 收集
3. 核心库自动包含新模块

### 添加新示例
1. 在 `examples/` 目录创建 `example_xxx.cpp`
2. 在 `examples/CMakeLists.txt` 的 `EXAMPLES` 列表中添加 `example_xxx`
3. 自动链接到 `ZhouYiLabCore`，可以使用所有模块

### 模块生成模式
- 所有目标都使用 `SEPARATE` 模式
- 模块接口单元的编译与其他翻译单元分离
- 提高编译效率

## 🔍 故障排除

### 问题：示例程序找不到模块
**解决方案**: 确保示例程序链接到 `ZhouYiLabCore`
```cmake
target_link_libraries(example_xxx PRIVATE ZhouYiLabCore)
```

### 问题：示例程序找不到模块（已解决）
**原因**: 
- 多次调用 `target_sources` 使用相同的 FILE_SET 名称会相互覆盖
- 标准库模块覆盖了自定义模块

**解决方案**: ✅
- 将所有模块（自定义、第三方、标准库）合并到同一个 `target_sources` 调用中
- 使用多个 `BASE_DIRS` 来支持不同路径的模块
```cmake
target_sources(ZhouYiLabCore
    PUBLIC
    FILE_SET cxx_modules TYPE CXX_MODULES 
    BASE_DIRS ${CMAKE_CURRENT_SOURCE_DIR} ${LIBCXX_MODULE_DIRS}
    FILES 
        ${MODULE_INTERFACE_FILES}
        ${CMAKE_CURRENT_SOURCE_DIR}/3rdparty/magic_enum/module/magic_enum.cppm
        ${LIBCXX_MODULE_DIRS}/std.cppm 
        ${LIBCXX_MODULE_DIRS}/std.compat.cppm
)
```

### 问题：重复定义错误
**解决方案**: 
- 核心库使用 `PUBLIC` 链接第三方库
- 可执行文件使用 `PRIVATE` 链接核心库
- 避免在可执行文件中重复链接第三方库

### 问题：编译警告 `-stdlib=libc++` unused（已解决）
**解决方案**: ✅
- 已从编译选项移除（使用了 `-nostdinc++`）
- 保留在链接选项中
- 警告已解决

## 📊 构建输出

```
cmake-build-xxx/
├── ZhouYiLabCore.a          # 核心静态库
├── ZhouYiLab                # 主程序
├── ZhouYiLabTests           # 测试程序
└── examples/
    ├── example_ba_zi
    ├── example_da_liu_ren
    ├── example_liu_yao
    └── example_zi_wei
```

---

**最后更新**: 2025-10-14
**架构版本**: 2.0 (Library + Executables)

