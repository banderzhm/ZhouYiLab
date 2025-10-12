# Tyme 模块构建配置
cmake_minimum_required(VERSION 4.0.0)

# 创建 tyme 静态库
add_library(tyme_modules STATIC)

# 添加模块文件到 FILE_SET
target_sources(tyme_modules
    PUBLIC
    FILE_SET CXX_MODULES
    TYPE CXX_MODULES
    FILES
        util_def.cppm
        util.cppm
        tyme_def.cppm
        tyme.cppm
)

# 设置 C++23 标准
target_compile_features(tyme_modules PUBLIC cxx_std_23)

# 设置模块属性
set_target_properties(tyme_modules PROPERTIES
    CXX_STANDARD 23
    CXX_STANDARD_REQUIRED YES
    CXX_EXTENSIONS OFF
    CXX_SCAN_FOR_MODULES ON
)

# 分离模块编译
set_property(TARGET tyme_modules PROPERTY
    CXX_MODULE_GENERATION_MODE "SEPARATE"
)
