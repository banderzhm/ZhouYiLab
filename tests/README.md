# ZhouYiLab 测试文档

本目录包含 ZhouYiLab 项目的所有测试代码，使用 [doctest](https://github.com/doctest/doctest) 测试框架。

## 测试文件结构

```
tests/
├── README.md              # 本文档
├── test_main.cpp          # 测试主入口（包含 doctest main 函数）
├── test_ganzhi.cpp        # 干支系统测试
├── test_bazi.cpp          # 八字计算测试
└── test_liu_yao.cpp       # 六爻排盘测试
```

## 编译测试

### 1. 配置项目

```bash
cd ZhouYiLab
cmake -S . -B build -G Ninja
```

### 2. 编译测试

```bash
cmake --build build --target ZhouYiLabTests
```

## 运行测试

### 运行所有测试

```bash
# 方法1：使用 ctest
cd build
ctest

# 方法2：直接运行测试可执行文件
./ZhouYiLabTests
```

### 运行特定测试套件

```bash
./ZhouYiLabTests --test-suite="干支系统测试"
./ZhouYiLabTests --test-suite="八字系统测试"
./ZhouYiLabTests --test-suite="六爻排盘系统测试"
```

### 运行特定测试用例

```bash
./ZhouYiLabTests --test-case="天干枚举反射"
./ZhouYiLabTests --test-case="公历转八字"
./ZhouYiLabTests --test-case="六爻排盘基本功能"
```

### 显示详细输出

```bash
./ZhouYiLabTests -s  # 成功的测试也显示输出
```

### 列出所有测试

```bash
./ZhouYiLabTests --list-test-cases
./ZhouYiLabTests --list-test-suites
```

## 测试覆盖范围

### 1. 干支系统测试 (`test_ganzhi.cpp`)

- ✅ 天干枚举反射
- ✅ 地支枚举反射
- ✅ 中文映射功能
  - 天干中文映射
  - 地支中文映射
  - 反向映射
  - 无效映射处理
- ✅ 地支关系测试
  - 六冲关系
  - 六合关系
  - 相刑关系（包括自刑）
  - 六害关系
- ✅ 十二长生测试
  - 长生位置
  - 帝旺位置
  - 墓库位置
  - 绝地位置
  - 完整长生状态

### 2. 八字系统测试 (`test_bazi.cpp`)

- ✅ Pillar 基本功能
  - 枚举构造
  - 字符串构造
  - string_view 构造
  - 中文名称获取
- ✅ BaZi 结构
- ✅ 公历转八字
- ✅ 农历转八字
- ✅ 旬空计算
- ✅ 日期转换
  - 公历转农历
  - 农历转公历
- ✅ JSON 序列化
  - Pillar 序列化/反序列化
  - BaZi 序列化/反序列化

### 3. 六爻排盘系统测试 (`test_liu_yao.cpp`)

- ✅ 六爻排盘基本功能
  - 无动爻排盘
  - 有动爻排盘
  - JSON 输出验证
- ✅ 卦象代码验证
  - 有效代码
  - 无效代码（长度/字符错误）
- ✅ 动爻位置验证
- ✅ 爻辞转卦象代码
  - 无动爻
  - 有动爻
  - 值验证
  - 数量验证
- ✅ 数字卦转卦象代码
  - 奇数为阳/偶数为阳
  - 金钱卦示例
- ✅ 获取卦象信息
- ✅ 批量排盘
- ✅ 爻详细信息验证
  - 基本信息
  - 动爻信息

## 添加新测试

### 1. 创建新测试文件

在 `tests/` 目录下创建 `test_<module>.cpp` 文件：

```cpp
// test_new_module.cpp
#include <doctest/doctest.h>

import YourModule;
import std;

TEST_SUITE("新模块测试") {
    TEST_CASE("测试用例1") {
        CHECK(1 + 1 == 2);
    }
    
    TEST_CASE("测试用例2") {
        SUBCASE("子用例A") {
            REQUIRE(true);
        }
        
        SUBCASE("子用例B") {
            CHECK_FALSE(false);
        }
    }
}
```

### 2. 重新编译

CMake 会自动检测新的 `test_*.cpp` 文件并添加到测试中。

```bash
cmake --build build --target ZhouYiLabTests
```

## doctest 常用断言

```cpp
CHECK(expression);              // 检查表达式为真
CHECK_FALSE(expression);         // 检查表达式为假
REQUIRE(expression);             // 必须为真，失败则中断当前测试用例
CHECK_EQ(a, b);                 // 检查 a == b
CHECK_NE(a, b);                 // 检查 a != b
CHECK_LT(a, b);                 // 检查 a < b
CHECK_GT(a, b);                 // 检查 a > b
CHECK_THROWS(expression);        // 检查表达式抛出异常
CHECK_THROWS_AS(expr, type);    // 检查表达式抛出特定类型异常
CHECK_NOTHROW(expression);       // 检查表达式不抛出异常
```

## 测试组织

### TEST_SUITE

用于组织相关测试用例：

```cpp
TEST_SUITE("测试套件名") {
    TEST_CASE("测试用例1") { ... }
    TEST_CASE("测试用例2") { ... }
}
```

### SUBCASE

用于在同一测试用例中创建不同的测试路径：

```cpp
TEST_CASE("主测试") {
    // 共享的设置代码
    
    SUBCASE("场景A") {
        // 测试场景A
    }
    
    SUBCASE("场景B") {
        // 测试场景B
    }
}
```

## CI/CD 集成

### GitHub Actions 示例

```yaml
- name: Run Tests
  run: |
    cmake --build build --target ZhouYiLabTests
    cd build
    ctest --output-on-failure
```

## 参考资料

- [doctest 官方文档](https://github.com/doctest/doctest/blob/master/doc/markdown/readme.md)
- [doctest 教程](https://github.com/doctest/doctest/blob/master/doc/markdown/tutorial.md)
- [doctest 断言参考](https://github.com/doctest/doctest/blob/master/doc/markdown/assertions.md)

