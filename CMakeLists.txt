cmake_minimum_required(VERSION 4.0.0)

project(ZhouYiLab 
    VERSION 1.0.0
    DESCRIPTION "周易实验室 - 传统文化算法库"
    LANGUAGES CXX
)

set(CMAKE_CXX_STANDARD 23)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_SCAN_FOR_MODULES ON)
set(CMAKE_EXPORT_COMPILE_COMMANDS ON)

# Currently CMake requires extensions enabled when using import std.
# https://gitlab.kitware.com/cmake/cmake/-/issues/25916
# https://gitlab.kitware.com/cmake/cmake/-/issues/25539
set(CMAKE_CXX_EXTENSIONS OFF)



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
file(GLOB_RECURSE CPP_SOURCES "src/*.cpp")
file(GLOB_RECURSE MODULE_FILES 
    "src/*.cppm"           # C++23 module 接口文件
    "src/*.ixx"            # MSVC module 接口文件
    "common/tyme/*.cppm"   # common/tyme 模块文件
)

# 创建可执行文件
add_executable(ZhouYiLab ${CPP_SOURCES} ${MODULE_FILES})

# 添加自定义模块和第三方模块到 FILE_SET
target_sources(ZhouYiLab
    PUBLIC
    FILE_SET cxx_modules TYPE CXX_MODULES FILES
    ${MODULE_FILES}
    # 添加 magic_enum 模块
    ${CMAKE_CURRENT_SOURCE_DIR}/3rdparty/magic_enum/module/magic_enum.cppm
)
message(STATUS "已添加 ${CMAKE_CURRENT_LIST_LENGTH} 个 C++ 模块文件")

# 定义 MAGIC_ENUM_USE_STD_MODULE 以使用 import std
target_compile_definitions(ZhouYiLab PRIVATE MAGIC_ENUM_USE_STD_MODULE)


# 链接第三方库
target_link_libraries(ZhouYiLab
    PRIVATE 
        fmt::fmt 
        nlohmann_json_modules
)

# 解决 CLion 无法识别 import std
# 直接将标准库模块添加到主目标的 FILE_SET 中
if (UNIX)
    # Linux/Unix: 使用 libc++ 标准库模块
    set(LIBCXX_MODULE_DIRS "/usr/lib/llvm-21/share/libc++/v1")
    target_sources(${PROJECT_NAME}
            PUBLIC
            FILE_SET CXX_MODULES
            BASE_DIRS ${LIBCXX_MODULE_DIRS}
            FILES ${LIBCXX_MODULE_DIRS}/std.cppm ${LIBCXX_MODULE_DIRS}/std.compat.cppm)
    message(STATUS "Added libc++ standard library modules from ${LIBCXX_MODULE_DIRS}")

elseif (WIN32)
    # Windows: 使用 MSVC 标准库模块
    set(LIBCXX_MODULE_DIRS "F:/program/visul_studio/idea/VC/Tools/MSVC/14.44.35207/modules")
    target_sources(${PROJECT_NAME}
            PUBLIC
            FILE_SET CXX_MODULES
            BASE_DIRS ${LIBCXX_MODULE_DIRS}
            FILES ${LIBCXX_MODULE_DIRS}/std.ixx ${LIBCXX_MODULE_DIRS}/std.compat.ixx)
    message(STATUS "Added MSVC standard library modules from ${LIBCXX_MODULE_DIRS}")
endif ()


# 分离模块生成与编译（CMake 4.0+ 特性）
# SEPARATE: 模块接口单元的编译与其他翻译单元分离
# COMBINED: 模块接口单元与导入它们的翻译单元一起编译（默认）
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