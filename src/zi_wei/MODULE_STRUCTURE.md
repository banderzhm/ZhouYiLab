# 紫微斗数模块结构说明

## 模块概览

ZhouYiLab的紫微斗数模块采用**C++23模块化**设计，功能完整且层次清晰。

## 核心模块列表

### 1. 基础模块

#### `zi_wei_constants.cppm`
- **功能**：定义所有枚举和常量
- **包含**：
  - 宫位枚举（GongWei）
  - 主星枚举（ZhuXing）
  - 辅星枚举（FuXing）
  - 煞星枚举（ShaXing）
  - 杂耀枚举（ZaYao）
  - 四化枚举（SiHua）
  - 五行局枚举（WuXingJu）
  - 亮度枚举（LiangDu）
  - 各种神煞12星枚举
  - 盘类型（PanType：天盘、地盘、人盘）
- **特点**：所有枚举都通过`ZhMapper`支持中文映射

#### `zi_wei_palace.cppm`
- **功能**：宫位计算和管理
- **包含**：
  - 宫位数据结构（GongWeiData）
  - 宫位信息结构（PalaceInfo）
  - 命宫身宫计算
  - 五行局计算
  - 宫位名称获取

### 2. 星耀模块

#### `zi_wei_star.cppm + zi_wei_star.cpp`
- **功能**：星耀安置算法
- **包含**：
  - 紫微诸星安置
  - 天府诸星安置
  - 六吉星安置（左辅右弼、文昌文曲、天魁天钺）
  - 六煞星安置（擎羊陀罗、火星铃星、地空地劫）
  - 杂耀安置（禄存、天马、红鸾天喜等）
  - 长生12神、博士12神、岁前12神、将前12神
  - 流耀（流年诸星）
  - 星耀亮度计算
  - 生年四化

#### `zi_wei_star_description.cppm + zi_wei_star_description.cpp`
- **功能**：星耀属性和特性描述
- **包含**：
  - 星耀五行属性
  - 星耀阴阳属性
  - 星耀特性标注（桃花、财、权、文、寿、驿马、孤、吉、煞等）
  - 星情描述
  - 主事说明
- **特点**：采用**内嵌JSON数据库**，方便扩展，无需修改代码

### 3. 分析模块

#### `zi_wei_sihua.cppm + zi_wei_sihua.cpp`
- **功能**：四化系统
- **包含**：
  - 宫干四化（每个宫位天干的四化）
  - 自化判断
  - 飞化分析（支持多层飞化，最多4层）
  - 飞化回本宫判断
- **核心类**：`SiHuaSystem`

#### `zi_wei_sanfang.cppm + zi_wei_sanfang.cpp`
- **功能**：三方四正系统
- **包含**：
  - 三方四正计算（本宫、对宫、财帛、官禄）
  - 夹宫分析
  - 空宫借星功能
  - 吉星夹/煞星夹判断
- **核心类**：`SanFangAnalyzer`

#### `zi_wei_geju.cppm + zi_wei_geju.cpp`
- **功能**：格局判断系统
- **包含**：
  - **富贵格局**：紫府同宫、紫府朝垣、君臣庆会、机月同梁、日月并明、月朗天门、明珠出海、阳梁昌禄、火贪铃贪等
  - **权贵格局**：三奇嘉会、双禄夹命、双禄夹财、科权禄夹、左右夹命、昌曲夹命、魁钺夹命、禄马交驰、权禄巡逢
  - **凶格**：铃昌陀武、巨机同宫、巨日同宫、马头带箭、羊陀夹命、火铃夹命、空劫夹命、羊陀夹忌、四煞冲命等
  - 双星组合分析
  - 命盘总分计算
- **核心类**：`GeJuAnalyzer`

#### `zi_wei_horoscope.cppm + zi_wei_horoscope.cpp`
- **功能**：运限系统（天地人三盘）
- **包含**：
  - 大限计算（10年一大限）
  - 小限计算（流年宫位）
  - 流年分析
  - 流月分析
  - 流日分析
  - 流时分析
  - 天地人三盘概念
- **特点**：整合tyme4cpp进行精确时间计算

### 4. 核心排盘模块

#### `zi_wei.cppm`
- **功能**：主排盘逻辑
- **包含**：
  - `ZiWeiResult` 结构体（完整命盘数据）
  - 阳历排盘（`pai_pan_solar`）
  - 农历排盘（`pai_pan_lunar`）
  - 十二宫数据
  - 大限数据
  - 运限分析接口
- **依赖**：整合所有子模块

### 5. 控制器模块

#### `zi_wei_controller.cppm + zi_wei_controller.cpp`
- **功能**：用户接口层
- **包含**：

##### 基础排盘功能
- `pai_pan_and_print_solar()` - 阳历排盘并输出
- `pai_pan_and_print_lunar()` - 农历排盘并输出
- `display_palace_detail()` - 显示宫位详情
- `display_ming_gong_san_fang_si_zheng()` - 显示命宫三方四正

##### 四化分析功能
- `display_gong_gan_si_hua()` - 宫干四化分析
- `display_zi_hua_analysis()` - 自化分析
- `display_fei_hua_analysis()` - 飞化链分析

##### 格局分析功能
- `display_ge_ju_analysis()` - 完整格局分析
- `display_ji_ge()` - 吉格列表
- `display_xiong_ge()` - 凶格列表
- `display_shuang_xing_zu_he()` - 双星组合分析

##### 三方四正分析功能
- `display_san_fang_si_zheng()` - 指定宫位的三方四正
- `display_kong_gong_jie_xing()` - 空宫借星分析

##### 星耀特性查询功能
- `display_star_info()` - 星耀详细信息
- `display_tao_hua_xing()` - 所有桃花星
- `display_cai_xing()` - 所有财星

##### 运限分析功能
- `display_da_xian_analysis()` - 大限分析
- `display_liu_nian_analysis()` - 流年分析

##### 综合分析功能
- `display_full_analysis()` - 完整命盘分析报告
- `export_to_json_full()` - 导出完整JSON（包含格局、四化等）
- `export_to_json()` - 导出基础JSON

## 模块依赖关系

```
依赖层次（从下到上）：

1. 底层基础
   - Constants（常量定义）
   - Palace（宫位计算）
   - Star（星耀安置）
   - StarDescription（星耀描述）

2. 中间分析层
   - SiHua（四化系统）← Constants, Star
   - SanFang（三方四正）← Constants
   - GeJu（格局判断）← Constants, SanFang
   - Horoscope（运限系统）← Constants, Star

3. 核心排盘层
   - ZiWei（主模块）← Constants, Palace, Star, Horoscope

4. 接口层
   - Controller（控制器）← ZiWei, SiHua, SanFang, GeJu, StarDescription
```

## 模块特色

### 1. 纯C++23模块设计
- 所有模块使用`export module`和`import`
- 无传统头文件依赖
- 编译速度快，依赖清晰

### 2. 中文化支持
- 所有枚举通过`ZhMapper`支持中文映射
- 输出完全中文化
- 符合传统命理术语

### 3. 数据驱动
- 星耀特性采用JSON数据库
- 格局规则可扩展
- 易于维护和更新

### 4. 功能完整
- ✅ 基础排盘
- ✅ 星耀安置（所有星耀）
- ✅ 亮度计算
- ✅ 四化系统（生年四化、宫干四化、自化、飞化）
- ✅ 三方四正系统
- ✅ 格局判断（30+种格局）
- ✅ 空宫借星
- ✅ 天地人三盘
- ✅ 大限小限流年流月流日流时
- ✅ 星耀特性标注

### 5. 接口丰富
- 命令行输出接口
- JSON导出接口
- 分析接口（四化、格局、三方四正等）
- 查询接口（星耀特性、桃花星、财星等）

## 文档文件

### `MISSING_FEATURES.md`
- 记录尚未实现的功能
- 主要包括：宫干四化、三方四正、格局判断等（已完成）
- 当前重点：飞星四化的深入应用、更多格局的实现

### `XINGYAO_DATABASE_GUIDE.md`
- 星耀数据库扩展指南
- 详细说明如何添加新星耀
- JSON格式说明

### `MODULE_STRUCTURE.md` (本文档)
- 模块结构总览
- 依赖关系说明
- 功能清单

## 使用示例

### 基础排盘
```cpp
import ZhouYi.ZiWei.Controller;

// 阳历排盘
pai_pan_and_print_solar(1990, 5, 20, 14, true);

// 农历排盘
pai_pan_and_print_lunar(1990, 4, 26, 14, true);
```

### 格局分析
```cpp
auto result = pai_pan_solar(1990, 5, 20, 14, true);
display_ge_ju_analysis(result);
```

### 四化分析
```cpp
auto result = pai_pan_solar(1990, 5, 20, 14, true);
display_gong_gan_si_hua(result);
display_zi_hua_analysis(result);
```

### 完整分析
```cpp
auto result = pai_pan_solar(1990, 5, 20, 14, true);
display_full_analysis(result);
```

### 导出JSON
```cpp
auto result = pai_pan_solar(1990, 5, 20, 14, true);
auto json = export_to_json_full(result);
// 保存到文件或发送到前端
```

## 后续扩展方向

1. **格局判断**：添加更多传统格局
2. **断语系统**：根据格局和四化生成断语
3. **宫位飞化**：深度飞化分析工具
4. **大运流年详批**：详细的运势分析
5. **Web接口**：提供HTTP API服务
6. **图形化输出**：生成SVG/PNG命盘图

## 总结

紫微斗数模块是一个**功能完整、设计优良、易于扩展**的C++23实现，涵盖了紫微斗数的核心功能，包括排盘、星耀安置、四化分析、格局判断、运限分析等，可以满足专业紫微斗数研究和应用的需求。

