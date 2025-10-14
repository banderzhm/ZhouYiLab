# CMake 配置说明

## 标准库模块路径配置

### 集中式变量定义

所有平台特定的标准库模块路径已在 **主 CMakeLists.txt 头部集中定义**（第19-33行）：

```cmake
# ============= 平台特定的标准库模块路径配置 =============
if (UNIX)
    # Linux/Unix: libc++ 标准库模块路径
    set(LIBCXX_MODULE_DIRS "/usr/lib/llvm-21/share/libc++/v1" CACHE PATH "Path to libc++ modules")
    set(LIBCXX_INCLUDE_DIRS "/usr/lib/llvm-21/include/c++/v1" CACHE PATH "Path to libc++ headers")
    message(STATUS "Platform: Linux/Unix")
    message(STATUS "  - libc++ module directory: ${LIBCXX_MODULE_DIRS}")
    message(STATUS "  - libc++ include directory: ${LIBCXX_INCLUDE_DIRS}")
elseif (WIN32)
    # Windows: MSVC 标准库模块路径
    set(LIBCXX_MODULE_DIRS "F:/program/visul_studio/idea/VC/Tools/MSVC/14.44.35207/modules" CACHE PATH "Path to MSVC modules")
    message(STATUS "Platform: Windows")
    message(STATUS "  - MSVC module directory: ${LIBCXX_MODULE_DIRS}")
endif()
# =======================================================
```

### 变量使用位置

这些变量在以下位置被使用：

#### 1. 主项目目标 (ZhouYiLab/CMakeLists.txt 第120-153行)

```cmake
if (UNIX)
    # Linux/Unix: 使用 libc++ 标准库模块
    # 注意：LIBCXX_MODULE_DIRS 和 LIBCXX_INCLUDE_DIRS 已在头部定义
    
    target_include_directories(${PROJECT_NAME} SYSTEM
        PRIVATE ${LIBCXX_INCLUDE_DIRS}
    )
    
    target_sources(${PROJECT_NAME}
        PUBLIC FILE_SET CXX_MODULES
        BASE_DIRS ${LIBCXX_MODULE_DIRS}
        FILES ${LIBCXX_MODULE_DIRS}/std.cppm ${LIBCXX_MODULE_DIRS}/std.compat.cppm
    )

elseif (WIN32)
    # Windows: 使用 MSVC 标准库模块
    # 注意：LIBCXX_MODULE_DIRS 已在头部定义
    
    target_sources(${PROJECT_NAME}
        PUBLIC FILE_SET CXX_MODULES
        BASE_DIRS ${LIBCXX_MODULE_DIRS}
        FILES ${LIBCXX_MODULE_DIRS}/std.ixx ${LIBCXX_MODULE_DIRS}/std.compat.ixx
    )
endif()
```

#### 2. 测试目标 (ZhouYiLab/CMakeLists.txt 第239-262行)

```cmake
if (UNIX)
    target_include_directories(ZhouYiLabTests SYSTEM
        PRIVATE ${LIBCXX_INCLUDE_DIRS}
    )
    
    target_sources(ZhouYiLabTests
        PUBLIC FILE_SET CXX_MODULES
        BASE_DIRS ${LIBCXX_MODULE_DIRS}
        FILES ${LIBCXX_MODULE_DIRS}/std.cppm ${LIBCXX_MODULE_DIRS}/std.compat.cppm
    )

elseif (WIN32)
    target_sources(ZhouYiLabTests
        PUBLIC FILE_SET CXX_MODULES
        BASE_DIRS ${LIBCXX_MODULE_DIRS}
        FILES ${LIBCXX_MODULE_DIRS}/std.ixx ${LIBCXX_MODULE_DIRS}/std.compat.ixx
    )
endif()
```

#### 3. 示例程序目标 (ZhouYiLab/examples/CMakeLists.txt 第53-90行)

```cmake
if(UNIX)
    # Linux/Unix: 使用 libc++ 标准库模块
    # 注意：LIBCXX_MODULE_DIRS 和 LIBCXX_INCLUDE_DIRS 已在主 CMakeLists.txt 中定义
    
    target_include_directories(${EXAMPLE} SYSTEM
        PRIVATE ${LIBCXX_INCLUDE_DIRS}
    )
    
    target_sources(${EXAMPLE}
        PUBLIC FILE_SET CXX_MODULES
        BASE_DIRS ${LIBCXX_MODULE_DIRS}
        FILES ${LIBCXX_MODULE_DIRS}/std.cppm ${LIBCXX_MODULE_DIRS}/std.compat.cppm
    )

elseif(WIN32)
    # Windows: 使用 MSVC 标准库模块
    # 注意：LIBCXX_MODULE_DIRS 已在主 CMakeLists.txt 中定义
    
    target_sources(${EXAMPLE}
        PUBLIC FILE_SET CXX_MODULES
        BASE_DIRS ${LIBCXX_MODULE_DIRS}
        FILES ${LIBCXX_MODULE_DIRS}/std.ixx ${LIBCXX_MODULE_DIRS}/std.compat.ixx
    )
endif()
```

## 配置优势

### 1. 集中管理
- ✅ 所有路径在**一处定义**
- ✅ 修改路径只需改动一个地方
- ✅ 避免路径不一致的问题

### 2. CACHE 变量
```cmake
set(LIBCXX_MODULE_DIRS "/path/to/modules" CACHE PATH "Description")
```
- ✅ 可通过 CMake GUI 或命令行覆盖
- ✅ 支持不同开发环境的路径配置
- ✅ 持久化保存在 CMakeCache.txt

### 3. 平台自动检测
```cmake
if (UNIX)
    # Linux/Unix 配置
elseif (WIN32)
    # Windows 配置
endif()
```
- ✅ 自动识别操作系统
- ✅ 应用正确的编译器和标准库
- ✅ 跨平台构建无需手动配置

## 如何自定义路径

### 方法1：修改 CMakeLists.txt

直接编辑主 CMakeLists.txt 第19-33行：

```cmake
if (UNIX)
    set(LIBCXX_MODULE_DIRS "/your/custom/path" CACHE PATH "Path to libc++ modules")
    set(LIBCXX_INCLUDE_DIRS "/your/custom/include" CACHE PATH "Path to libc++ headers")
elseif (WIN32)
    set(LIBCXX_MODULE_DIRS "C:/your/msvc/path/modules" CACHE PATH "Path to MSVC modules")
endif()
```

### 方法2：通过 CMake 命令行

```bash
cmake -DLIBCXX_MODULE_DIRS="/custom/path" ..
```

### 方法3：通过 CMake GUI

1. 打开 CMake GUI
2. 找到 `LIBCXX_MODULE_DIRS` 和 `LIBCXX_INCLUDE_DIRS`
3. 修改路径
4. 重新配置和生成

## 常见路径配置

### Linux (LLVM/Clang)

```cmake
# LLVM 16
set(LIBCXX_MODULE_DIRS "/usr/lib/llvm-16/share/libc++/v1")
set(LIBCXX_INCLUDE_DIRS "/usr/lib/llvm-16/include/c++/v1")

# LLVM 17
set(LIBCXX_MODULE_DIRS "/usr/lib/llvm-17/share/libc++/v1")
set(LIBCXX_INCLUDE_DIRS "/usr/lib/llvm-17/include/c++/v1")

# LLVM 21 (当前使用)
set(LIBCXX_MODULE_DIRS "/usr/lib/llvm-21/share/libc++/v1")
set(LIBCXX_INCLUDE_DIRS "/usr/lib/llvm-21/include/c++/v1")
```

### Windows (MSVC)

```cmake
# Visual Studio 2022 (17.10+)
set(LIBCXX_MODULE_DIRS "C:/Program Files/Microsoft Visual Studio/2022/Community/VC/Tools/MSVC/14.40.33807/modules")

# Visual Studio 2022 (17.12+)
set(LIBCXX_MODULE_DIRS "C:/Program Files/Microsoft Visual Studio/2022/Community/VC/Tools/MSVC/14.44.35207/modules")

# 自定义安装路径
set(LIBCXX_MODULE_DIRS "F:/program/visul_studio/idea/VC/Tools/MSVC/14.44.35207/modules")
```

## 依赖库配置

### fmt 库
```cmake
# 启用模块模式
set(FMT_MODULE ON CACHE BOOL "Build fmt as a C++ module" FORCE)
add_subdirectory(3rdparty/fmt)

# 链接
target_link_libraries(YourTarget PRIVATE fmt::fmt)

# 导入
import fmt;
```

### nlohmann_json 库
```cmake
# 启用模块支持
set(NLOHMANN_JSON_BUILD_MODULES ON CACHE BOOL "Build nlohmann_json C++ module" FORCE)
add_subdirectory(3rdparty/nlohmann_json)

# 链接
target_link_libraries(YourTarget PRIVATE nlohmann_json_modules)

# 导入
import nlohmann.json;
```

## 标准库模块导入顺序

⚠️ **重要规范**：`import std;` 必须在最后导入

```cpp
// ✅ 正确顺序
import ZhouYi.XXX;        // 项目模块
import fmt;               // 第三方模块
import nlohmann.json;     // 第三方模块
import std;               // 标准库（最后）

// ❌ 错误顺序
import std;               // 不能在最前面
import ZhouYi.XXX;
import fmt;
```

## 构建命令

### 标准构建
```bash
cd ZhouYiLab
mkdir build
cd build
cmake ..
cmake --build .
```

### 自定义路径构建
```bash
cmake -DLIBCXX_MODULE_DIRS="/custom/path" ..
cmake --build .
```

### 构建示例程序
```bash
cmake --build . --target example_ba_zi
cmake --build . --target example_zi_wei
```

### 运行测试
```bash
ctest
# 或
./ZhouYiLabTests
```

## 故障排除

### 问题1：找不到标准库模块

**症状**：
```
error: module 'std' not found
```

**解决方案**：
1. 检查 `LIBCXX_MODULE_DIRS` 路径是否正确
2. 确认标准库模块文件是否存在
3. 检查编译器版本是否支持 C++23 模块

### 问题2：路径中包含空格

**症状**：
```
error: cannot find file ...
```

**解决方案**：
使用引号包裹路径：
```cmake
set(LIBCXX_MODULE_DIRS "C:/Program Files/..." CACHE PATH "...")
```

### 问题3：Linux 上找不到 libc++

**症状**：
```
fatal error: 'iostream' file not found
```

**解决方案**：
1. 安装 libc++：
   ```bash
   sudo apt install libc++-21-dev libc++abi-21-dev
   ```
2. 或使用系统默认路径：
   ```cmake
   set(LIBCXX_INCLUDE_DIRS "/usr/include/c++/v1")
   ```

## 总结

- ✅ **所有路径集中在主 CMakeLists.txt 头部定义**
- ✅ **使用 CACHE 变量便于自定义**
- ✅ **平台自动检测，无需手动切换**
- ✅ **主项目、测试、示例统一使用相同配置**
- ✅ **遵循 import std; 在最后的规范**

配置完成后，整个项目的标准库模块路径管理清晰、统一、易于维护！🎉

