# ZhouYiLab 示例程序 CMakeLists.txt

cmake_minimum_required(VERSION 3.28)

# 如果单独编译examples，需要设置项目名
if(NOT PROJECT_NAME)
    project(ZhouYiLab_Examples
        VERSION 1.0.0
        LANGUAGES CXX
    )
    
    # 设置C++23标准
    set(CMAKE_CXX_STANDARD 23)
    set(CMAKE_CXX_STANDARD_REQUIRED ON)
    set(CMAKE_CXX_EXTENSIONS OFF)
    
    # 添加父目录以访问主项目的模块
    add_subdirectory(${CMAKE_CURRENT_SOURCE_DIR}/.. ${CMAKE_CURRENT_BINARY_DIR}/ZhouYiLab)
endif()

# 确保支持C++模块
if(NOT CMAKE_CXX_STANDARD EQUAL 23)
    message(FATAL_ERROR "Examples require C++23 support")
endif()

# 示例程序列表
set(EXAMPLES
    example_asio
    example_ba_zi
    example_da_liu_ren
    example_liu_yao
    example_zi_wei
)

# 为每个示例创建可执行文件
foreach(EXAMPLE ${EXAMPLES})
    add_executable(${EXAMPLE} ${EXAMPLE}.cpp)
    
    # 设置模块依赖
    target_compile_features(${EXAMPLE} PRIVATE cxx_std_23)
    
    # 链接核心库（包含所有模块）
    target_link_libraries(${EXAMPLE}
        PRIVATE
        ZhouYiLabCore
    )
    
    # 设置输出目录
    set_target_properties(${EXAMPLE} PROPERTIES
        RUNTIME_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/examples
    )
    
    # 设置模块生成模式
    set_property(TARGET ${EXAMPLE} PROPERTY
        CXX_MODULE_GENERATION_MODE "SEPARATE"
    )
    
endforeach()

# 提示信息
message(STATUS "Examples configured:")
foreach(EXAMPLE ${EXAMPLES})
    message(STATUS "  - ${EXAMPLE}")
endforeach()

