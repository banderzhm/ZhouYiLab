# ZhouYiLab 示例程序实现说明

## 完成的工作

### 1. ✅ 模块导入规范修正

按照项目要求，所有实现文件（.cpp）已修正为：
- **禁止使用 `#include`**，全部改为 `import`
- **`import std;` 必须在最后导入**，避免代码异常

#### 修改的文件：
1. `ZhouYiLab/src/zi_wei/zi_wei_controller.cpp`
2. `ZhouYiLab/src/zi_wei/zi_wei_star_description.cpp`
3. `ZhouYiLab/src/zi_wei/zi_wei_sihua.cpp`
4. `ZhouYiLab/src/zi_wei/zi_wei_sanfang.cpp`
5. `ZhouYiLab/src/zi_wei/zi_wei_geju.cpp`
6. `ZhouYiLab/src/zi_wei/zi_wei_horoscope.cpp`

#### 修改前：
```cpp
module;
#include <fmt/core.h>
#include <nlohmann/json.hpp>

module ZhouYi.ZiWei.Controller;

import std;
import ZhouYi.ZiWei;
```

#### 修改后：
```cpp
module ZhouYi.ZiWei.Controller;

import ZhouYi.ZiWei;
import fmt;
import nlohmann.json;
import std;  // 最后导入
```

### 2. ✅ 创建 examples 目录

创建了独立的示例程序目录：`ZhouYiLab/examples/`

### 3. ✅ 实现四个完整示例

#### 3.1 八字系统示例 (`example_ba_zi.cpp`)
- 八字排盘
- 四柱查看
- 十神关系分析
- 大运流年信息
- JSON导出

#### 3.2 大六壬系统示例 (`example_da_liu_ren.cpp`)
- 阳历起课
- 农历起课
- 指定干支起课
- 四课三传详情
- JSON导出

#### 3.3 六爻系统示例 (`example_liu_yao.cpp`)
- 数字起卦
- 时间起卦（阳历/农历）
- 金钱卦起卦
- 卦象详细信息
- 六爻查看
- JSON导出

#### 3.4 紫微斗数系统示例 (`example_zi_wei.cpp`)
- 阳历/农历排盘
- 命盘详细信息
- 宫位查询
- 三方四正分析
- 格局判断
- 四化分析
- 大限、流年、流月分析
- 星耀特性查询
- 完整命盘分析
- JSON导出

### 4. ✅ 配套文档

创建了三个文档：

1. **README.md**：使用说明和编译指南
2. **CMakeLists.txt**：CMake构建配置
3. **IMPLEMENTATION_NOTES.md**（本文档）：实现说明

## 技术特点

### 1. 纯模块化设计
所有示例都使用C++23模块系统：
```cpp
import ZhouYi.XXX.Controller;
import fmt;
import std;  // 最后导入
```

### 2. 无 include 头文件
完全遵守项目规范，不使用任何 `#include` 指令

### 3. 友好的输出格式
使用 `fmt` 库提供美观的格式化输出：
```cpp
fmt::print("╔════════════════════════════════════════╗\n");
fmt::print("║          八字系统示例演示              ║\n");
fmt::print("╚════════════════════════════════════════╝\n");
```

### 4. 完善的异常处理
所有示例都包含 try-catch 块：
```cpp
try {
    // 示例代码
} catch (const exception& e) {
    fmt::print("❌ 错误：{}\n", e.what());
    return 1;
}
```

### 5. 分类清晰的演示
每个示例都分为多个小节，清晰展示不同功能

## 导入顺序规范

根据项目要求，模块导入必须遵循以下顺序：

```cpp
// 1. 项目模块
import ZhouYi.XXX;

// 2. 第三方模块
import fmt;
import nlohmann.json;

// 3. 标准库（必须最后）
import std;
```

### 错误示例 ❌
```cpp
import std;              // 错误：std不能在最前面
import ZhouYi.ZiWei;
import fmt;
```

### 正确示例 ✅
```cpp
import ZhouYi.ZiWei;
import fmt;
import std;              // 正确：std在最后
```

## 为什么 import std; 必须在最后？

这是因为：
1. C++23模块系统要求标准库模块在其他模块之后导入
2. 避免标准库的宏定义与其他模块冲突
3. 确保模块的正确初始化顺序

## 编译要求

### 编译器支持
- **Clang 16+** with `-std=c++23 -fmodules`
- **GCC 14+** with `-std=c++23 -fmodules-ts`
- **MSVC 2022** with `/std:c++latest`

### 依赖库
- `fmt` (模块版本)
- `nlohmann_json` (模块版本)
- `tyme4cpp`

## 与 main.cpp 的区别

### 之前（main.cpp 中的示例）：
- 所有示例混在一起
- 难以单独测试某个功能
- 代码冗长不易维护

### 现在（examples 目录）：
- 每个模块独立示例文件
- 可单独编译和运行
- 代码清晰，易于学习
- 便于作为模板复制使用

## 使用建议

### 学习顺序
1. 先看 `example_ba_zi.cpp`（最简单）
2. 再看 `example_da_liu_ren.cpp`
3. 然后看 `example_liu_yao.cpp`
4. 最后看 `example_zi_wei.cpp`（最复杂）

### 快速开始
```bash
# 编译单个示例
cd ZhouYiLab/examples
clang++ -std=c++23 -fmodules example_ba_zi.cpp -o example_ba_zi

# 运行
./example_ba_zi
```

### 修改参数
直接编辑 cpp 文件，修改函数参数即可：
```cpp
// 修改出生日期和性别
pai_pan_and_print_solar(1990, 5, 20, 14, true);  // 男
// 改为
pai_pan_and_print_solar(1995, 8, 15, 10, false); // 女
```

## 后续扩展

可以继续添加的示例：

1. **高级分析示例**
   - 八字格局深度分析
   - 紫微斗数批命详解
   - 六爻断卦实战

2. **Web API示例**
   - HTTP服务器集成
   - RESTful API设计
   - JSON响应处理

3. **数据库集成示例**
   - 存储命盘数据
   - 批量分析
   - 统计查询

4. **批量处理示例**
   - 批量排盘
   - 数据分析
   - 报表生成

## 总结

本次实现完成了：
1. ✅ 所有源文件的模块导入规范修正
2. ✅ 创建独立的 examples 目录
3. ✅ 四个完整的模块示例程序
4. ✅ 完善的文档和编译配置

所有代码都遵循项目规范：
- **无 #include 指令**
- **import std; 在最后**
- **纯C++23模块系统**
- **清晰的代码结构**
- **友好的输出格式**

示例程序可作为：
- 学习教程
- 功能测试
- 集成模板
- API参考

