# 六爻模块 (ZhouYi.LiuYao)

六爻排盘系统的 C++23 模块实现。

## 模块依赖

### 导入的通用模块

```cpp
import ZhouYi.BaZiBase;        // 八字基础数据结构（Pillar, BaZi）
import ZhouYi.WuXingUtils;     // 五行工具（五行关系、地支五行映射）
import ZhouYi.DiZhiRelations;  // 地支关系（冲、合、刑、害、三合）
```

### 导入的第三方模块

```cpp
import fmt;                    // 格式化输出
import nlohmann.json;          // JSON 序列化
import std;                    // 标准库
```

## 主要数据结构

### YaoDetails - 爻详细信息

存储每一爻的完整信息：

```cpp
struct YaoDetails {
    int position;                  // 爻位 (1-6)
    
    // 本卦信息
    Pillar mainPillar;             // 本卦纳甲干支
    std::string mainElement;       // 本卦爻支五行
    std::string mainRelative;      // 本卦六亲
    
    // 伏神信息
    Pillar hiddenPillar;           // 伏神干支
    std::string hiddenRelative;    // 伏神六亲
    std::string hiddenElement;     // 伏神五行
    
    // 变爻/变卦信息
    bool isChanging;               // 是否为动爻
    Pillar changedPillar;          // 变出之爻的干支
    std::string changedElement;    // 变出之爻地支五行
    std::string changedRelative;   // 变出之爻六亲
    
    // 其他辅助信息
    std::string spirit;            // 六神
    std::string wangShuai;         // 旺衰
    std::string shiYingMark;       // 世/应标记
    char mainYaoType;              // 本卦爻阴阳
    std::string changeMark;        // 动爻标记
};
```

### HexagramInfo - 卦象信息

存储64卦的基本信息：

```cpp
struct HexagramInfo {
    std::string name;              // 卦名
    std::string meaning;           // 简要含义
    std::string fiveElement;       // 五行属性
    int shiYaoPosition;            // 世爻位置
    int yingYaoPosition;           // 应爻位置
    bool isYangHexagram;           // 阳卦/阴卦
    std::string palaceType;        // 所属宫位
    std::string innerHexagram;     // 内卦
    std::string outerHexagram;     // 外卦
    std::string structureType;     // 卦结构类型
};
```

## 主要功能函数

### sixYaoDivination - 六爻排盘

```cpp
std::pair<std::vector<YaoDetails>, nlohmann::json> sixYaoDivination(
    const std::string& mainHexagramCode,       // 本卦代码（如"111111"）
    const BaZi& bazi,                          // 四柱八字
    const std::vector<int>& changingLineIndices // 动爻位置
);
```

**功能**：
- 根据本卦代码和动爻信息进行六爻排盘
- 自动计算变卦、伏神
- 计算六亲、六神、旺衰
- 分析刑冲克害关系
- 返回爻详情和 JSON 数据

### buildShenShaMap - 构建神煞图

```cpp
std::map<std::string, std::vector<std::string>> buildShenShaMap(const BaZi& bazi);
```

**功能**：
- 根据四柱八字计算各种神煞
- 包括：驿马、桃花、日禄、贵人、文昌、羊刃、将星、劫煞、灾煞等

### analyzeXingChongKeHaiHe - 关系分析

```cpp
std::string analyzeXingChongKeHaiHe(
    const BaZi& bazi,
    const std::vector<YaoDetails>& yaoList
);
```

**功能**：
- 分析日月与各爻的刑冲克害关系
- 分析动爻与其他爻的关系
- 分析四柱内部的关系
- 分析回头关系

## 使用示例

```cpp
import ZhouYi.LiuYao;
import fmt;

int main() {
    using namespace ZhouYi::LiuYao;
    
    // 构建八字信息
    BaZi bazi{
        .year = {"甲", "子"},
        .month = {"丙", "寅"},
        .day = {"戊", "辰"},
        .hour = {"庚", "申"},
        .xun_kong_1 = "戌",
        .xun_kong_2 = "亥"
    };
    
    // 本卦代码（乾为天）
    std::string hexagramCode = "111111";
    
    // 动爻位置（初爻和四爻）
    std::vector<int> changingLines = {1, 4};
    
    // 排盘
    auto [yaoList, json] = sixYaoDivination(hexagramCode, bazi, changingLines);
    
    // 输出结果
    fmt::print("本卦：{}\n", json["ben_gua_name"].get<std::string>());
    fmt::print("变卦：{}\n", json["bian_gua_name"].get<std::string>());
    
    // 遍历六爻
    for (const auto& yao : yaoList) {
        fmt::print("第{}爻：{}{} {} {}\n",
            yao.position,
            yao.mainRelative,
            yao.mainPillar.stem,
            yao.mainPillar.branch,
            yao.spirit
        );
    }
    
    return 0;
}
```

## 模块结构优化

本模块已经过重构，将通用功能抽取到 common 模块：

1. **Pillar、BaZi** → `ZhouYi.BaZiBase`
2. **五行相关** → `ZhouYi.WuXingUtils`
3. **地支关系** → `ZhouYi.DiZhiRelations`

这样的设计使得：
- 代码复用性更高
- 模块职责更清晰
- 维护更容易

## 编译要求

- C++23 或更高版本
- 支持 C++ Modules 的编译器（Clang 17+, GCC 14+, MSVC 19.36+）
- CMake 4.0+ (用于模块扫描)

## 许可证

本项目采用 MIT 许可证。

