cmake_minimum_required(VERSION 4.1.2)

project(ZhouYiLab 
    VERSION 1.0.0
    DESCRIPTION "周易实验室 - 传统文化算法库"
    LANGUAGES CXX
)

set(CMAKE_CXX_STANDARD 23)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_SCAN_FOR_MODULES ON)
set(CMAKE_EXPORT_COMPILE_COMMANDS ON)
#set(CMAKE_CXX_MODULE_STD ON)
# Currently CMake requires extensions enabled when using import std.
# https://gitlab.kitware.com/cmake/cmake/-/issues/25916
# https://gitlab.kitware.com/cmake/cmake/-/issues/25539
set(CMAKE_CXX_EXTENSIONS ON)

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

# 在 Linux/Unix 上强制使用 libc++（必须在添加第三方库之前设置）
if (UNIX)
    # 设置全局编译选项，确保所有目标（包括第三方库）都使用 libc++
    # 注意：由于使用了 -nostdinc++，-stdlib=libc++ 只在链接阶段有用，因此只添加到链接选项中
    add_compile_options(
        -nostdinc++
        -isystem /usr/lib/llvm-21/include/c++/v1
    )
    
    # 设置全局链接选项
    add_link_options(-stdlib=libc++ -lc++ -lc++abi)
    
    message(STATUS "Forcing libc++ for all targets (including third-party libraries)")
endif()

message(STATUS "C++ compiler: ${CMAKE_CXX_COMPILER}")
message(STATUS "C++ standard: ${CMAKE_CXX_STANDARD}")


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

# 包含头文件目录
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/include)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/3rdparty/nlohmann_json/include)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/3rdparty/magic_enum/include)

# 收集源文件
# 分离普通 C++ 源文件和模块文件
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
file(GLOB_RECURSE CPP_SOURCES "src/*.cpp")

# 收集模块接口文件（.cppm）
file(GLOB_RECURSE MODULE_INTERFACE_FILES 
    "src/*.cppm"           # C++23 module 接口文件（递归搜索所有子目录）
    "src/*.ixx"            # MSVC module 接口文件
)

# 收集 tyme 模块实现文件（.cpp）- tyme.cpp 和 util.cpp
file(GLOB TYME_IMPL_FILES "src/common/tyme/*.cpp")

# 排除测试文件（它有自己的 main 函数）
list(REMOVE_ITEM CPP_SOURCES "${CMAKE_CURRENT_SOURCE_DIR}/src/test_tyme_module.cpp")

# 排除主程序入口文件
list(REMOVE_ITEM CPP_SOURCES "${CMAKE_CURRENT_SOURCE_DIR}/src/main.cpp")

# ============= 创建核心库 =============
# 创建静态库包含所有核心模块和实现（除了 main.cpp）
add_library(ZhouYiLabCore STATIC ${CPP_SOURCES} ${MODULE_INTERFACE_FILES} ${TYME_IMPL_FILES})

# 定义 MAGIC_ENUM_USE_STD_MODULE 以使用 import std
target_compile_definitions(ZhouYiLabCore PUBLIC
    MAGIC_ENUM_USE_STD_MODULE
)

# 添加自定义模块、第三方模块和标准库模块到 FILE_SET（必须在同一个 target_sources 调用中）
if (UNIX)
    # Linux/Unix: 使用 libc++ 标准库模块
    # 注意：LIBCXX_MODULE_DIRS 和 LIBCXX_INCLUDE_DIRS 已在头部定义
    
    # 添加 libc++ 头文件路径（系统级，不会覆盖其他路径）
    target_include_directories(ZhouYiLabCore SYSTEM
        PUBLIC
        ${LIBCXX_INCLUDE_DIRS}
    )
    
    # 添加所有模块到同一个 FILE_SET（避免覆盖）
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
    
    message(STATUS "Added libc++ standard library modules from ${LIBCXX_MODULE_DIRS}")
    message(STATUS "Added libc++ include directory: ${LIBCXX_INCLUDE_DIRS}")

elseif (WIN32)
    # Windows: 使用 MSVC 标准库模块
    # 注意：LIBCXX_MODULE_DIRS 已在头部定义
    
    # 添加所有模块到同一个 FILE_SET（避免覆盖）
    target_sources(ZhouYiLabCore
        PUBLIC
        FILE_SET cxx_modules TYPE CXX_MODULES 
        BASE_DIRS ${CMAKE_CURRENT_SOURCE_DIR} ${LIBCXX_MODULE_DIRS}
        FILES 
            ${MODULE_INTERFACE_FILES}
            ${CMAKE_CURRENT_SOURCE_DIR}/3rdparty/magic_enum/module/magic_enum.cppm
            ${LIBCXX_MODULE_DIRS}/std.ixx 
            ${LIBCXX_MODULE_DIRS}/std.compat.ixx
    )
    
    message(STATUS "Added MSVC standard library modules from ${LIBCXX_MODULE_DIRS}")
endif ()

message(STATUS "已添加 ${CMAKE_CURRENT_LIST_LENGTH} 个 C++ 模块文件到核心库")

# ============= 创建主可执行文件 =============
# 创建可执行文件，只包含 main.cpp
add_executable(ZhouYiLab src/main.cpp)

# 链接核心库
target_link_libraries(ZhouYiLab PRIVATE ZhouYiLabCore)

# 链接第三方库到核心库
target_link_libraries(ZhouYiLabCore
        PUBLIC
        fmt::fmt
        nlohmann_json_modules
)

# 分离模块生成与编译（CMake 4.0+ 特性）
# SEPARATE: 模块接口单元的编译与其他翻译单元分离
# COMBINED: 模块接口单元与导入它们的翻译单元一起编译（默认）
set_property(TARGET ZhouYiLabCore PROPERTY
    CXX_MODULE_GENERATION_MODE "SEPARATE"
)
set_property(TARGET ZhouYiLab PROPERTY
    CXX_MODULE_GENERATION_MODE "SEPARATE"
)


# 打印配置信息
message(STATUS "==========================================")
message(STATUS "项目名称: ${PROJECT_NAME}")
message(STATUS "项目版本: ${PROJECT_VERSION}")
message(STATUS "C++ 标准: C++${CMAKE_CXX_STANDARD}")
message(STATUS "编译器: ${CMAKE_CXX_COMPILER_ID} ${CMAKE_CXX_COMPILER_VERSION}")
message(STATUS "构建类型: ${CMAKE_BUILD_TYPE}")
message(STATUS "==========================================")
message(STATUS "C++ Modules 配置:")
message(STATUS "  - CXX_SCAN_FOR_MODULES: ON")
message(STATUS "  - CXX_MODULE_STD: ON (import std 支持)")
message(STATUS "  - CXX_MODULE_GENERATION_MODE: SEPARATE")
message(STATUS "  - FMT_MODULE: ${FMT_MODULE}")
message(STATUS "  - NLOHMANN_JSON_BUILD_MODULES: ${NLOHMANN_JSON_BUILD_MODULES}")
if(TARGET nlohmann_json_modules)
    message(STATUS "  - nlohmann_json_modules: ✓ Available")
else()
    message(STATUS "  - nlohmann_json_modules: ✗ Not available (use #include)")
endif()
list(LENGTH MODULE_FILES MODULE_COUNT)
message(STATUS "  - 自定义模块文件数量: ${MODULE_COUNT}")
message(STATUS "==========================================")

# ==============================================
# 测试配置
# ==============================================
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
    target_link_libraries(ZhouYiLabTests
        PRIVATE
        ZhouYiLabCore
        doctest::doctest
    )
    
    # 设置模块生成模式
    set_property(TARGET ZhouYiLabTests PROPERTY
        CXX_MODULE_GENERATION_MODE "SEPARATE"
    )
    
    # 添加测试
    add_test(NAME AllTests COMMAND ZhouYiLabTests)
    
    message(STATUS "==========================================")
    message(STATUS "测试配置:")
    message(STATUS "  - doctest: ✓ Enabled")
    message(STATUS "  - 测试文件数量: ${TEST_SOURCES}")
    message(STATUS "  - 运行测试: ctest 或 ./ZhouYiLabTests")
    message(STATUS "==========================================")
endif ()

# ============= 示例程序配置 =============
# 添加 examples 子目录
add_subdirectory(examples)
message(STATUS "==========================================")
message(STATUS "示例程序:")
message(STATUS "  - 示例程序已启用，位于 examples/ 目录")
message(STATUS "  - 构建后位于: ${CMAKE_BINARY_DIR}/examples/")
message(STATUS "==========================================")