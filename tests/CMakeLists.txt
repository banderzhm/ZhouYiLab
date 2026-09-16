# 独立回归程序复用生产模块；输出仅写入构建目录。
foreach(system IN ITEMS zi_wei qi_men mei_hua)
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
