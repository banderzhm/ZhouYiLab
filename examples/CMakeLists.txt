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
    
    # 链接必要的库
    target_link_libraries(${EXAMPLE}
        PRIVATE
        fmt::fmt
        nlohmann_json_modules
    )
    
    # 设置输出目录
    set_target_properties(${EXAMPLE} PROPERTIES
        RUNTIME_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/examples
    )
    
    # 平台特定的标准库模块配置（使用主 CMakeLists.txt 中定义的变量）
    if(UNIX)
        # Linux/Unix: 使用 libc++ 标准库模块
        # 注意：LIBCXX_MODULE_DIRS 和 LIBCXX_INCLUDE_DIRS 已在主 CMakeLists.txt 中定义
        
        # 添加 libc++ 头文件路径（系统级，不会覆盖其他路径）
        target_include_directories(${EXAMPLE} SYSTEM
            PRIVATE
            ${LIBCXX_INCLUDE_DIRS}
        )
        
        # 添加标准库模块
        target_sources(${EXAMPLE}
            PUBLIC
            FILE_SET CXX_MODULES
            BASE_DIRS ${LIBCXX_MODULE_DIRS}
            FILES ${LIBCXX_MODULE_DIRS}/std.cppm ${LIBCXX_MODULE_DIRS}/std.compat.cppm
        )
        
        # 注意：编译和链接选项已在全局设置中配置（add_compile_options/add_link_options）
        # 这样确保第三方库（如 nlohmann_json）也使用 libc++
        
    elseif(WIN32)
        # Windows: 使用 MSVC 标准库模块
        # 注意：LIBCXX_MODULE_DIRS 已在主 CMakeLists.txt 中定义
        
        target_sources(${EXAMPLE}
            PUBLIC
            FILE_SET CXX_MODULES
            BASE_DIRS ${LIBCXX_MODULE_DIRS}
            FILES ${LIBCXX_MODULE_DIRS}/std.ixx ${LIBCXX_MODULE_DIRS}/std.compat.ixx
        )
        
        # 如果使用MSVC，设置UTF-8编码
        if(MSVC)
            target_compile_options(${EXAMPLE} PRIVATE /utf-8)
        endif()
    endif()
    
endforeach()

# 提示信息
message(STATUS "Examples configured:")
foreach(EXAMPLE ${EXAMPLES})
    message(STATUS "  - ${EXAMPLE}")
endforeach()

