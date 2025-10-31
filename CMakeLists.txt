cmake_minimum_required(VERSION 3.28)

project(ZhouYiLab 
    VERSION 1.0.0
    DESCRIPTION "周易实验室 - 传统文化算法库"
    LANGUAGES CXX
)

# ==============================================================================
# C++ 标准配置
# ==============================================================================
set(CMAKE_CXX_STANDARD 23)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)
set(CMAKE_CXX_SCAN_FOR_MODULES ON)
set(CMAKE_EXPORT_COMPILE_COMMANDS ON)

# ==============================================================================
# 自动检测平台特定的标准库模块路径
# ==============================================================================

if(UNIX)
    # Linux/Unix: 自动检测 libc++ 标准库模块路径
    
    # 尝试查找 libc++ 模块目录 (LLVM 19-21)
    find_path(LIBCXX_MODULE_DIRS
        NAMES std.cppm
        PATHS
            /usr/lib/llvm-21/share/libc++/v1
            /usr/lib/llvm-20/share/libc++/v1
            /usr/lib/llvm-19/share/libc++/v1
            /usr/local/lib/llvm-21/share/libc++/v1
            /usr/local/lib/llvm-20/share/libc++/v1
            /usr/local/lib/llvm-19/share/libc++/v1
            /opt/llvm/share/libc++/v1
        DOC "Path to libc++ standard library modules"
    )
    
    # 尝试查找 libc++ 头文件目录 (LLVM 19-21)
    find_path(LIBCXX_INCLUDE_DIRS
        NAMES __config
        PATHS
            /usr/lib/llvm-21/include/c++/v1
            /usr/lib/llvm-20/include/c++/v1
            /usr/lib/llvm-19/include/c++/v1
            /usr/local/lib/llvm-21/include/c++/v1
            /usr/local/lib/llvm-20/include/c++/v1
            /usr/local/lib/llvm-19/include/c++/v1
            /opt/llvm/include/c++/v1
            /usr/include/c++/v1
        DOC "Path to libc++ headers"
    )
    
    if(LIBCXX_MODULE_DIRS AND LIBCXX_INCLUDE_DIRS)
        message(STATUS "自动检测到 libc++ 路径:")
        message(STATUS "  模块目录: ${LIBCXX_MODULE_DIRS}")
        message(STATUS "  头文件目录: ${LIBCXX_INCLUDE_DIRS}")
        
        # 强制所有目标使用 libc++ (必须在添加第三方库之前设置)
        add_compile_options(
            -nostdinc++
            -isystem ${LIBCXX_INCLUDE_DIRS}
        )
        add_link_options(-stdlib=libc++ -lc++ -lc++abi)
    else()
        message(WARNING "无法自动检测 libc++ 路径。请手动设置:")
        message(WARNING "  cmake -DLIBCXX_MODULE_DIRS=<path> -DLIBCXX_INCLUDE_DIRS=<path> ..")
    endif()
    
elseif(WIN32)
    # Windows: 自动检测 MSVC 标准库模块路径
    
    # 优先级1: 尝试从 VCToolsInstallDir 环境变量获取
    if(DEFINED ENV{VCToolsInstallDir})
        set(LIBCXX_MODULE_DIRS "$ENV{VCToolsInstallDir}/modules" CACHE PATH "Path to MSVC modules")
        if(EXISTS "${LIBCXX_MODULE_DIRS}/std.ixx")
            message(STATUS "从 VCToolsInstallDir 环境变量检测到 MSVC 模块目录: ${LIBCXX_MODULE_DIRS}")
        else()
            message(WARNING "找到 VCToolsInstallDir 但 std.ixx 不存在于: ${LIBCXX_MODULE_DIRS}")
            unset(LIBCXX_MODULE_DIRS CACHE)
        endif()
    endif()
    
    # 优先级2: 如果环境变量未找到或无效，尝试从编译器路径检测
    if(NOT LIBCXX_MODULE_DIRS)
        # 从编译器路径提取 MSVC 版本目录
        # cl.exe 位于: VC/Tools/MSVC/{version}/bin/Hostx64/x64/cl.exe
        # modules 位于: VC/Tools/MSVC/{version}/modules/
        # 所以需要从 cl.exe 目录向上3级
        get_filename_component(COMPILER_DIR "${CMAKE_CXX_COMPILER}" DIRECTORY)
        get_filename_component(COMPILER_HOST_DIR "${COMPILER_DIR}" DIRECTORY)
        get_filename_component(COMPILER_BIN_DIR "${COMPILER_HOST_DIR}" DIRECTORY)
        get_filename_component(MSVC_VERSION_DIR "${COMPILER_BIN_DIR}" DIRECTORY)
        
        # 首先尝试从编译器路径提取的路径
        set(DETECTED_MODULE_PATH "${MSVC_VERSION_DIR}/modules")
        
        if(EXISTS "${DETECTED_MODULE_PATH}/std.ixx")
            set(LIBCXX_MODULE_DIRS "${DETECTED_MODULE_PATH}" CACHE PATH "Path to MSVC modules")
            message(STATUS "从编译器路径自动检测到 MSVC 模块目录: ${LIBCXX_MODULE_DIRS}")
        else()
            # 后备方案: 在常见的 Visual Studio 安装位置搜索
            message(STATUS "无法从编译器路径检测，搜索常见安装位置...")
            find_path(LIBCXX_MODULE_DIRS
                NAMES std.ixx
                PATHS
                    "C:/Program Files/Microsoft Visual Studio/2022/Community/VC/Tools/MSVC"
                    "C:/Program Files/Microsoft Visual Studio/2022/Professional/VC/Tools/MSVC"
                    "C:/Program Files/Microsoft Visual Studio/2022/Enterprise/VC/Tools/MSVC"
                    "F:/program/visul_studio/idea/VC/Tools/MSVC"
                PATH_SUFFIXES
                    "14.44.35207/modules"
                    "14.43.34601/modules"
                    "14.42.34433/modules"
                DOC "Path to MSVC standard library modules"
            )
            
            if(LIBCXX_MODULE_DIRS)
                message(STATUS "在常见位置找到 MSVC 模块目录: ${LIBCXX_MODULE_DIRS}")
            else()
                message(WARNING "无法自动检测 MSVC 模块路径。请手动设置:")
                message(WARNING "  cmake -DLIBCXX_MODULE_DIRS=<path> ..")
            endif()
        endif()
    endif()
endif()


# ==============================================================================
# 第三方库配置
# ==============================================================================

# 临时禁用模块扫描，避免 CMake 尝试扫描第三方库的编译器特性测试
set(CMAKE_CXX_SCAN_FOR_MODULES_BACKUP ${CMAKE_CXX_SCAN_FOR_MODULES})
set(CMAKE_CXX_SCAN_FOR_MODULES OFF)

# 配置第三方库的 C++ modules 支持
# 启用 fmt 的 module 模式（稳定支持）
set(FMT_MODULE ON CACHE BOOL "Build fmt as a C++ module" FORCE)
# 启用 nlohmann_json 的 module 支持
# 注意：在 MSVC 上，nlohmann_json 模块可能存在兼容性问题
# 如果遇到问题，请在代码中使用 #include <nlohmann/json.hpp> 代替 import nlohmann.json
set(NLOHMANN_JSON_BUILD_MODULES ON CACHE BOOL "Build nlohmann_json C++ module" FORCE)

# 添加第三方库
add_subdirectory(3rdparty/fmt)
add_subdirectory(3rdparty/nlohmann_json)
add_subdirectory(3rdparty/asio)

# 恢复模块扫描
set(CMAKE_CXX_SCAN_FOR_MODULES ${CMAKE_CXX_SCAN_FOR_MODULES_BACKUP})

# 包含头文件目录
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/include)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/3rdparty/nlohmann_json/include)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/3rdparty/magic_enum/include)

# ==============================================================================
# 源文件收集
# ==============================================================================
#
# 源码目录结构：
#   src/               - 主程序入口（main.cpp）和测试文件
#   src/common/        - 通用基础模块（天干、地支、干支、中文映射等）
#   src/liu_yao/       - 六爻相关模块
#   src/da_liu_ren/    - 大六壬相关模块
#   src/ba_zi/         - 八字相关模块
#   src/zi_wei/        - 紫微斗数相关模块
#   src/common/tyme/   - 农历时间库模块
#

# 收集所有 C++ 源文件
file(GLOB_RECURSE CPP_SOURCES "src/*.cpp")

# 收集模块接口文件
file(GLOB_RECURSE MODULE_INTERFACE_FILES 
    "src/*.cppm"    # C++23 module 接口文件
    "src/*.ixx"     # MSVC module 接口文件
)

# 排除测试文件（它有自己的 main 函数）
list(REMOVE_ITEM CPP_SOURCES "${CMAKE_CURRENT_SOURCE_DIR}/src/test_tyme_module.cpp")

# 排除主程序入口文件
list(REMOVE_ITEM CPP_SOURCES "${CMAKE_CURRENT_SOURCE_DIR}/src/main.cpp")

# ==============================================================================
# 核心库目标
# ==============================================================================

# 创建静态库，包含所有核心模块和实现（除了 main.cpp）
add_library(ZhouYiLabCore STATIC 
    ${CPP_SOURCES} 
    ${MODULE_INTERFACE_FILES}
)

# 定义 MAGIC_ENUM_USE_STD_MODULE 以使用 import std
target_compile_definitions(ZhouYiLabCore PUBLIC
    MAGIC_ENUM_USE_STD_MODULE
)

# 平台特定的模块配置
if(UNIX AND LIBCXX_MODULE_DIRS AND LIBCXX_INCLUDE_DIRS)
    # Linux/Unix: 使用 libc++ 标准库模块
    
    # 添加 libc++ 头文件路径（系统级，不会覆盖其他路径）
    target_include_directories(ZhouYiLabCore SYSTEM PUBLIC
        ${LIBCXX_INCLUDE_DIRS}
    )
    
    # 添加所有模块到 FILE_SET（避免覆盖）
    target_sources(ZhouYiLabCore PUBLIC
        FILE_SET cxx_modules TYPE CXX_MODULES 
        BASE_DIRS ${CMAKE_CURRENT_SOURCE_DIR} ${LIBCXX_MODULE_DIRS}
        FILES 
            ${MODULE_INTERFACE_FILES}
            ${CMAKE_CURRENT_SOURCE_DIR}/3rdparty/magic_enum/module/magic_enum.cppm
            ${LIBCXX_MODULE_DIRS}/std.cppm 
            ${LIBCXX_MODULE_DIRS}/std.compat.cppm
    )
    
elseif(WIN32 AND LIBCXX_MODULE_DIRS)
    # Windows: 使用 MSVC 标准库模块
    
    # 添加所有模块到 FILE_SET（避免覆盖）
    target_sources(ZhouYiLabCore PUBLIC
        FILE_SET cxx_modules TYPE CXX_MODULES 
        BASE_DIRS ${CMAKE_CURRENT_SOURCE_DIR} ${LIBCXX_MODULE_DIRS}
        FILES 
            ${MODULE_INTERFACE_FILES}
            ${CMAKE_CURRENT_SOURCE_DIR}/3rdparty/magic_enum/module/magic_enum.cppm
            ${LIBCXX_MODULE_DIRS}/std.ixx 
            ${LIBCXX_MODULE_DIRS}/std.compat.ixx
    )
    
else()
    message(WARNING "标准库模块未配置。模块支持可能受限。")
endif()

# 链接第三方库到核心库
target_link_libraries(ZhouYiLabCore PUBLIC
    fmt::fmt
    nlohmann_json_modules
    asio::asio
)

# Windows 平台需要链接网络库
if(WIN32)
    target_link_libraries(ZhouYiLabCore PUBLIC ws2_32 mswsock)
endif()

# 模块生成模式（CMake 4.0+ 特性）
# SEPARATE: 模块接口单元的编译与其他翻译单元分离
set_property(TARGET ZhouYiLabCore PROPERTY
    CXX_MODULE_GENERATION_MODE "SEPARATE"
)

# ==============================================================================
# 主可执行文件目标
# ==============================================================================

# 创建可执行文件，只包含 main.cpp
add_executable(ZhouYiLab src/main.cpp)

# 链接核心库
target_link_libraries(ZhouYiLab PRIVATE ZhouYiLabCore)

# 设置模块生成模式
set_property(TARGET ZhouYiLab PROPERTY
    CXX_MODULE_GENERATION_MODE "SEPARATE"
)

# ==============================================================================
# 构建信息汇总
# ==============================================================================

message(STATUS "==========================================")
message(STATUS "项目: ${PROJECT_NAME} v${PROJECT_VERSION}")
message(STATUS "C++ 标准: C++${CMAKE_CXX_STANDARD}")
message(STATUS "编译器: ${CMAKE_CXX_COMPILER_ID} ${CMAKE_CXX_COMPILER_VERSION}")
message(STATUS "构建类型: ${CMAKE_BUILD_TYPE}")
if(UNIX)
    if(LIBCXX_MODULE_DIRS)
        message(STATUS "libc++ 模块: ${LIBCXX_MODULE_DIRS}")
    endif()
elseif(WIN32)
    if(LIBCXX_MODULE_DIRS)
        message(STATUS "MSVC 模块: ${LIBCXX_MODULE_DIRS}")
    endif()
endif()
message(STATUS "==========================================")

# ==============================================================================
# 测试配置
# ==============================================================================
enable_testing()

# 添加 doctest
add_subdirectory(3rdparty/doctest EXCLUDE_FROM_ALL)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/3rdparty/doctest)

# 收集测试文件
file(GLOB TEST_SOURCES "tests/test_*.cpp")

# 创建测试可执行文件
if(TEST_SOURCES)
    add_executable(ZhouYiLabTests
        tests/test_main.cpp
        ${TEST_SOURCES}
    )
    
    # 链接核心库和 doctest
    target_link_libraries(ZhouYiLabTests PRIVATE
        ZhouYiLabCore
        doctest::doctest
    )
    
    # 设置模块生成模式
    set_property(TARGET ZhouYiLabTests PROPERTY
        CXX_MODULE_GENERATION_MODE "SEPARATE"
    )
    
    # 添加测试
    add_test(NAME AllTests COMMAND ZhouYiLabTests)
    
    message(STATUS "测试已启用 (运行: ctest 或 ./ZhouYiLabTests)")
endif()

# ==============================================================================
# 示例程序配置
# ==============================================================================

if(EXISTS "${CMAKE_CURRENT_SOURCE_DIR}/examples/CMakeLists.txt")
    add_subdirectory(examples)
    message(STATUS "示例程序已启用 (位于 examples/ 目录)")
endif()