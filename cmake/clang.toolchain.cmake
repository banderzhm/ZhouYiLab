# 指定系统名称为 Linux
set(CMAKE_SYSTEM_NAME Linux)

# 设置使用 Ninja 作为构建工具
set(CMAKE_GENERATOR "Ninja" CACHE STRING "Ninja" FORCE)

# 设置 Ninja 的参数
set(CMAKE_NINJA_FLAGS "--verbose --time")

# 设置 C 编译器及其选项
set(CMAKE_C_COMPILER "/usr/bin/clang-21")
set(CMAKE_C_FLAGS "-Wall -std=c99")
set(CMAKE_C_FLAGS_DEBUG "-g")
set(CMAKE_C_FLAGS_MINSIZEREL "-Os -DNDEBUG")
set(CMAKE_C_FLAGS_RELEASE "-O3 -DNDEBUG -march=native -flto")  # 注意：-O4 不是标准优化级别，通常使用 -O3
set(CMAKE_C_FLAGS_RELWITHDEBINFO "-O2 -g -march=native -flto")

# 设置 C++ 编译器及其选项，并指定使用 libc++
set(CMAKE_CXX_COMPILER "/usr/bin/clang++-21")
set(CMAKE_CXX_FLAGS "-Wall -stdlib=libc++")
set(CMAKE_CXX_FLAGS_DEBUG "-g")
set(CMAKE_CXX_FLAGS_MINSIZEREL "-Os -DNDEBUG")
set(CMAKE_CXX_FLAGS_RELEASE "-O3 -DNDEBUG -march=native -flto")  # 同样，这里使用 -O3 而不是 -O4  -march=native 进行优化
set(CMAKE_CXX_FLAGS_RELWITHDEBINFO "-O2 -g -march=native -flto")

# 设置链接器标志以使用 libc++
set(CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAGS} -stdlib=libc++ -fuse-ld=mold")
set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS} -stdlib=libc++ -fuse-ld=mold")
set(CMAKE_MODULE_LINKER_FLAGS "${CMAKE_MODULE_LINKER_FLAGS} -stdlib=libc++ -fuse-ld=mold")
set(CMAKE_CXX_STANDARD_LIBRARIES "-lc++ -lc++abi")

# 设置 LLVM 工具链路径
set(CMAKE_AR      "/usr/bin/llvm-ar-21" CACHE FILEPATH "Archiver")
set(CMAKE_RANLIB  "/usr/bin/llvm-ranlib-21" CACHE FILEPATH "Ranlib")
set(CMAKE_NM      "/usr/bin/llvm-nm-21" CACHE FILEPATH "NM")
set(CMAKE_OBJDUMP "/usr/bin/llvm-objdump-21" CACHE FILEPATH "Objdump")
#GNU gold 最慢  LLVM lld其次  mold 最快  [readelf -p .comment 可执行文件（判断link使用的方法）]
#set(CMAKE_LINKER  "/usr/bin/llvm-ld-20" CACHE FILEPATH "Linker")
set(CMAKE_LINKER  "/usr/bin/mold" CACHE FILEPATH "Linker")

# 确保 CMake 使用正确的库进行链接
set(CMAKE_FIND_ROOT_PATH_MODE_PROGRAM NEVER)
set(CMAKE_FIND_ROOT_PATH_MODE_LIBRARY ONLY)
set(CMAKE_FIND_ROOT_PATH_MODE_INCLUDE ONLY)

# 启用 C++20 模块支持
# 3.31 set(CMAKE_EXPERIMENTAL_CXX_IMPORT_STD "0e5b6991-d74f-4b3d-a41c-cf096e0b2508")

# 4.0.3
#set(CMAKE_EXPERIMENTAL_CXX_IMPORT_STD "d0edc3af-4c50-42ea-a356-e2862fe7a444")
#set(CMAKE_CXX_MODULE_STD ON)


#[[set(CMAKE_EXPERIMENTAL_CXX_IMPORT_STD "a9e1cf81-9932-4810-974b-6eccaf14e457")
set(CMAKE_CXX_MODULE_STD ON)]]
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -stdlib=libc++")

# 为所有目标添加编译选项
add_compile_options(
        -Wno-unused-const-variable  # 禁用未使用的常量变量警告
        -Wno-unused-private-field   # 禁用未使用的私有字段警告
)

function(setting)
endfunction()