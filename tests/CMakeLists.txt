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
