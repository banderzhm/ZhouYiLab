# ZhouYiLab 示例程序

本目录包含 ZhouYiLab 各个模块的使用示例。

## 示例列表

### 1. 八字系统示例 (`example_ba_zi.cpp`)

**功能演示：**
- 八字排盘
- 四柱查看
- 十神关系分析
- 大运流年查询
- JSON导出

**运行示例：**
```bash
./example_ba_zi
```

**示例输出：**
```
╔════════════════════════════════════════════════════════════╗
║                  八字系统示例演示                          ║
╚════════════════════════════════════════════════════════════╝

【示例1】排盘并显示八字信息
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
...
```

### 2. 大六壬系统示例 (`example_da_liu_ren.cpp`)

**功能演示：**
- 阳历起课
- 农历起课
- 指定干支起课
- 四课三传查看
- JSON导出

**运行示例：**
```bash
./example_da_liu_ren
```

### 3. 六爻系统示例 (`example_liu_yao.cpp`)

**功能演示：**
- 数字起卦
- 时间起卦（阳历/农历）
- 金钱卦起卦
- 卦象详细信息
- 六爻查看
- JSON导出

**运行示例：**
```bash
./example_liu_yao
```

### 4. 紫微斗数系统示例 (`example_zi_wei.cpp`)

**功能演示：**
- 阳历排盘
- 农历排盘
- 命盘详细信息
- 宫位查询
- 三方四正分析
- 格局判断
- 四化分析
- 大限分析
- 流年流月流日流时分析
- 星耀特性查询
- 完整命盘分析
- JSON导出

**运行示例：**
```bash
./example_zi_wei
```

## 编译说明

### 使用CMake编译

1. 在项目根目录创建构建目录：
```bash
cd ZhouYiLab
mkdir build-examples
cd build-examples
```

2. 配置CMake：
```bash
cmake -DBUILD_EXAMPLES=ON ..
```

3. 编译：
```bash
cmake --build .
```

4. 运行示例：
```bash
# Windows
.\examples\example_ba_zi.exe
.\examples\example_da_liu_ren.exe
.\examples\example_liu_yao.exe
.\examples\example_zi_wei.exe

# Linux/macOS
./examples/example_ba_zi
./examples/example_da_liu_ren
./examples/example_liu_yao
./examples/example_zi_wei
```

### 手动编译单个示例

以八字系统为例：

```bash
# 使用支持C++23模块的编译器
clang++ -std=c++23 -fmodules \
    example_ba_zi.cpp \
    -I../3rdparty/fmt/include \
    -I../3rdparty/nlohmann_json/include \
    -lfmt \
    -o example_ba_zi
```

## 代码结构说明

所有示例都遵循以下结构：

```cpp
// 1. 导入模块（import std; 必须在最后）
import ZhouYi.XXX.Controller;
import ZhouYi.GanZhi;
import fmt;
import std;

// 2. 使用命名空间
using namespace ZhouYi::XXX;
using namespace std;

// 3. 主函数
int main() {
    // 演示代码
    try {
        // 各种功能示例
    } catch (const exception& e) {
        fmt::print("❌ 错误：{}\n", e.what());
        return 1;
    }
    return 0;
}
```

## 模块依赖说明

### 导入顺序
根据项目规范，**import std; 必须在最后导入**，其他模块按需导入即可：

```cpp
import ZhouYi.XXX;      // 项目模块
import fmt;              // 第三方模块
import nlohmann.json;    // 第三方模块
import std;              // 标准库（最后）
```

### 禁止使用 #include
项目严格使用C++23模块系统，**不允许出现 #include**：

❌ 错误写法：
```cpp
#include <iostream>
#include <fmt/core.h>
```

✅ 正确写法：
```cpp
import fmt;
import std;
```

## 常见问题

### Q1: 编译时提示找不到模块？
**A:** 确保项目已正确编译，所有模块都已生成。建议先编译主项目：
```bash
cd ZhouYiLab
cmake --build build
```

### Q2: 运行时输出乱码？
**A:** Windows下确保控制台支持UTF-8编码：
```bash
chcp 65001
```

### Q3: 如何修改示例参数？
**A:** 直接编辑对应的 `.cpp` 文件，修改函数调用的参数即可。例如修改出生日期：
```cpp
// 修改前
pai_pan_and_print_solar(1990, 5, 20, 14, true);

// 修改为自己的出生日期
pai_pan_and_print_solar(1995, 8, 15, 10, false);
```

### Q4: 能否将示例集成到自己的项目中？
**A:** 可以！只需导入对应的Controller模块即可使用。例如：
```cpp
import ZhouYi.ZiWei.Controller;

int main() {
    auto result = ZhouYi::ZiWei::pai_pan_solar(1990, 5, 20, 14, true);
    // 使用result进行后续处理
    return 0;
}
```

## 进阶使用

### 组合使用多个模块

可以在同一个程序中组合使用多个模块：

```cpp
import ZhouYi.BaZi.Controller;
import ZhouYi.ZiWei.Controller;
import fmt;
import std;

int main() {
    // 先排八字
    auto ba_zi = ZhouYi::BaZi::get_ba_zi(1990, 5, 20, 14, true);
    
    // 再排紫微斗数
    auto zi_wei = ZhouYi::ZiWei::pai_pan_solar(1990, 5, 20, 14, true);
    
    // 综合分析
    fmt::print("八字日主：{}\n", ba_zi.day_pillar.to_string());
    fmt::print("紫微命宫：{}\n", zi_wei.get_palace(ZhouYi::ZiWei::GongWei::MingGong).to_string());
    
    return 0;
}
```

### Web API封装示例

可以将这些功能封装成Web API：

```cpp
import ZhouYi.ZiWei.Controller;
import nlohmann.json;

string api_pai_pan(const string& request_json) {
    auto req = json::parse(request_json);
    int year = req["year"];
    int month = req["month"];
    int day = req["day"];
    int hour = req["hour"];
    bool is_male = req["is_male"];
    
    auto result = ZhouYi::ZiWei::pai_pan_solar(year, month, day, hour, is_male);
    return ZhouYi::ZiWei::export_to_json_full(result);
}
```

## 贡献示例

欢迎贡献更多实用示例！请遵循以下规范：

1. 使用 `example_xxx.cpp` 命名
2. 遵守项目的模块导入规范（import std; 在最后）
3. 添加详细的注释和输出说明
4. 更新本 README.md 文档

## 许可证

本示例代码遵循项目主许可证。详见根目录 LICENSE 文件。

