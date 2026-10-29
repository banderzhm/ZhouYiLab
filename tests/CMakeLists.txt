# 独立回归程序复用生产模块；输出仅写入构建目录。
foreach(system IN ITEMS zi_wei qi_men mei_hua da_liu_ren)
    add_executable(regression_${system} regression_${system}.cpp)
    target_link_libraries(regression_${system} PRIVATE ZhouYiLabCore)
    target_compile_features(regression_${system} PRIVATE cxx_std_23)
    set(report_dir "${CMAKE_CURRENT_BINARY_DIR}/reports/${system}")
    file(MAKE_DIRECTORY "${report_dir}")
    add_test(NAME regression_${system} COMMAND regression_${system})
    set_tests_properties(regression_${system} PROPERTIES
        WORKING_DIRECTORY "${report_dir}"
        TIMEOUT 300
        LABELS "regression")
endforeach()

# 紫微运限 get_horoscope 单列一个回归目标：它与三套术数回归关注点不同，
# 单独注册便于按目标运行，同时仍复用生产模块与同一报告隔离目录。
add_executable(regression_zi_wei_horoscope regression_zi_wei_horoscope.cpp)
target_link_libraries(regression_zi_wei_horoscope PRIVATE ZhouYiLabCore)
target_compile_features(regression_zi_wei_horoscope PRIVATE cxx_std_23)
set(horoscope_report_dir "${CMAKE_CURRENT_BINARY_DIR}/reports/zi_wei")
file(MAKE_DIRECTORY "${horoscope_report_dir}")
add_test(NAME regression_zi_wei_horoscope COMMAND regression_zi_wei_horoscope)
set_tests_properties(regression_zi_wei_horoscope PROPERTIES
    WORKING_DIRECTORY "${horoscope_report_dir}"
    TIMEOUT 300
    LABELS "regression")

# 外部参考实现对照数据导出器。该目标不注册为 CTest：iztro 是独立下载的
# 可选参考实现，CI 不应因未安装 Node 包而失败。配套脚本见
# tests/reference/compare_zi_wei_iztro.cjs。
add_executable(reference_zi_wei_iztro reference_zi_wei_iztro.cpp)
target_link_libraries(reference_zi_wei_iztro PRIVATE ZhouYiLabCore)
target_compile_features(reference_zi_wei_iztro PRIVATE cxx_std_23)

# 大六壬跨实现对照数据导出器。外部参考库保持为手工运行依赖，
# 不把 Node/Python 包带入普通 CTest 与三平台生产构建。
add_executable(reference_da_liu_ren reference_da_liu_ren.cpp)
target_link_libraries(reference_da_liu_ren PRIVATE ZhouYiLabCore)
target_compile_features(reference_da_liu_ren PRIVATE cxx_std_23)

# Tyme 模块导入与日期转换的冒烟测试，不作为历法准确性的对照测试。
add_executable(test_tyme_module tyme/test_tyme_module.cpp)
target_link_libraries(test_tyme_module PRIVATE ZhouYiLabCore)
target_compile_features(test_tyme_module PRIVATE cxx_std_23)
add_test(NAME test_tyme_module COMMAND test_tyme_module)
set_tests_properties(test_tyme_module PROPERTIES
    WORKING_DIRECTORY "${CMAKE_CURRENT_BINARY_DIR}"
    TIMEOUT 30
    LABELS "smoke;tyme")
