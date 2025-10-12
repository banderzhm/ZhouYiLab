cmake_minimum_required(VERSION 3.30)

# 启用 C++23 import std 实验性功能
set(CMAKE_EXPERIMENTAL_CXX_IMPORT_STD
    "0e5b6991-d74f-4b3d-a41c-cf096e0b2508")

project(ZhouYiLab 
    VERSION 1.0.0
    DESCRIPTION "周易实验室 - 传统文化算法库"
    LANGUAGES CXX
)

# 设置 C++ 标准为 C++23
set(CMAKE_CXX_STANDARD 23)
set(CMAKE_CXX_STANDARD_REQUIRED YES)
set(CMAKE_CXX_EXTENSIONS OFF)

# 启用 C++23 modules 扫描
set(CMAKE_CXX_SCAN_FOR_MODULES ON)

# 设置输出目录
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/bin)
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/lib)
set(CMAKE_ARCHIVE_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/lib)

# 编译选项
if(MSVC)
    add_compile_options(/utf-8 /W4)
    # MSVC 对 C++23 modules 的支持
    add_compile_options(/experimental:module)
else()
    add_compile_options(-Wall -Wextra -pedantic)
endif()

# 配置第三方库的 C++ modules 支持
# 启用 fmt 的 module 模式
set(FMT_MODULE ON CACHE BOOL "Build fmt as a C++ module" FORCE)

# 启用 nlohmann_json 的 module 支持
set(NLOHMANN_JSON_BUILD_MODULES ON CACHE BOOL "Build nlohmann_json C++ module" FORCE)

# 添加第三方库
add_subdirectory(3rdparty/fmt)
add_subdirectory(3rdparty/nlohmann_json)

# 包含头文件目录
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/include)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/3rdparty/nlohmann_json/include)

# 收集源文件
file(GLOB_RECURSE SOURCES 
    "src/*.cpp"
    "src/*.cppm"  # C++23 module 接口文件
    "src/*.ixx"   # MSVC module 接口文件
)

# 创建可执行文件
add_executable(${PROJECT_NAME} ${SOURCES})

# 链接第三方库
target_link_libraries(${PROJECT_NAME} 
    PRIVATE 
        fmt::fmt 
        nlohmann_json::nlohmann_json
)

# 设置目标属性
set_target_properties(${PROJECT_NAME} PROPERTIES
    CXX_STANDARD 23
    CXX_STANDARD_REQUIRED YES
    CXX_EXTENSIONS OFF
    CXX_SCAN_FOR_MODULES ON
    CXX_MODULE_STD ON  # 启用 import std 支持
)

# 安装规则
install(TARGETS ${PROJECT_NAME}
    RUNTIME DESTINATION bin
    LIBRARY DESTINATION lib
    ARCHIVE DESTINATION lib
)

# 打印配置信息
message(STATUS "==========================================")
message(STATUS "项目名称: ${PROJECT_NAME}")
message(STATUS "项目版本: ${PROJECT_VERSION}")
message(STATUS "C++ 标准: C++${CMAKE_CXX_STANDARD}")
message(STATUS "编译器: ${CMAKE_CXX_COMPILER_ID} ${CMAKE_CXX_COMPILER_VERSION}")
message(STATUS "构建类型: ${CMAKE_BUILD_TYPE}")
message(STATUS "==========================================")

