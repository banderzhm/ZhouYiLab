# 八字模块变更记录

## 2024-10-14 - 实现 tyme 库完整功能访问

### 变更说明

实现对 tyme 库的完整功能访问，包括童限详细信息、大运对象、小运对象的完整 API。

### 新增功能

#### 1. 童限详细信息访问

**新增结构体**：`DaYunSystem::ChildLimitDetail`
```cpp
struct ChildLimitDetail {
    int start_age;                  // 起运年龄（虚岁）
    int year_count;                 // 年数
    int month_count;                // 月数
    int day_count;                  // 日数
    int hour_count;                 // 小时数
    int minute_count;               // 分钟数
    tyme::SolarTime start_time;     // 出生时刻
    tyme::SolarTime end_time;       // 起运时刻
};
```

**新增方法**：
- `DaYunSystem::get_child_limit_detail()` - 获取童限详细信息
- `BaZiResult::get_child_limit_detail()` - 便捷访问童限信息

#### 2. tyme 大运对象访问

**新增方法**：
- `DaYunSystem::get_tyme_decade_fortune(int index)` - 获取单个大运
- `DaYunSystem::get_all_tyme_decade_fortunes(int count)` - 获取多个大运
- `BaZiResult::get_tyme_decade_fortune(int index)` - 便捷访问
- `BaZiResult::get_all_tyme_decade_fortunes(int count)` - 便捷访问

**可访问的 tyme::DecadeFortune 功能**：
- 干支名称、起止年龄
- 起止干支年
- 六十甲子对象（天干、地支）
- 起始小运
- 链式访问下一大运

#### 3. tyme 小运对象访问

**新增方法**：
- `BaZiResult::get_tyme_fortune(int index)` - 获取小运对象

**可访问的 tyme::Fortune 功能**：
- 年龄、干支名称
- 干支年、六十甲子
- 链式访问下一小运

#### 4. 控制器显示函数

**新增函数**：
- `display_child_limit_detail(result)` - 显示童限详细信息
- `display_tyme_decade_fortune(result, index)` - 显示大运详细信息

### 使用示例

#### 童限详细信息

```cpp
auto detail = result.get_child_limit_detail();
std::println("起运: {}岁 ({}年{}月{}天{}时{}分)", 
             detail.start_age,
             detail.year_count, detail.month_count, 
             detail.day_count, detail.hour_count, detail.minute_count);
std::println("出生: {}", detail.start_time.to_string());
std::println("起运: {}", detail.end_time.to_string());

// 或使用显示函数
display_child_limit_detail(result);
```

#### tyme 大运对象

```cpp
// 获取单个大运
auto decade = result.get_tyme_decade_fortune(0);
std::println("大运: {} ({}-{}岁)", 
             decade.get_name(),
             decade.get_start_age(),
             decade.get_end_age());

// 干支信息
auto sixty_cycle = decade.get_sixty_cycle();
std::println("天干: {}", sixty_cycle.get_heaven_stem().get_name());
std::println("地支: {}", sixty_cycle.get_earth_branch().get_name());

// 起止年份
auto start_year = decade.get_start_sixty_cycle_year();
std::println("起始年份: {}", start_year.get_name());

// 链式访问
auto next = decade.next(1);
std::println("下一大运: {}", next.get_name());

// 获取所有大运
auto all = result.get_all_tyme_decade_fortunes(10);
```

#### tyme 小运对象

```cpp
auto fortune = result.get_tyme_fortune(0);
std::println("小运: {} {}岁", fortune.get_name(), fortune.get_age());

auto next_fortune = fortune.next(1);
std::println("下一小运: {}", next_fortune.get_name());
```

### 新增文件

- `API.md` - 完整的 API 文档

### 更新文件

- `ba_zi.cppm` - 新增方法
- `ba_zi_controller.cppm` - 新增显示函数声明
- `ba_zi_controller.cpp` - 新增显示函数实现
- `README.md` - 更新使用示例
- `SUMMARY.md` - 更新功能列表

### 优势

1. **完整访问**：可以访问 tyme 库的所有功能
2. **灵活性**：支持链式调用和批量获取
3. **详细信息**：提供起运的精确计算（年月日时分）
4. **易用性**：提供便捷的显示函数

### API 稳定性

✅ 所有新增 API 都是向后兼容的
✅ 原有 API 保持不变
✅ 新增功能为可选功能

---

## 2024-10-14 - 集成 tyme 库计算起运年龄

### 变更说明

使用 `tyme4cpp` 库的 `ChildLimit` 系统精确计算起运年龄，替换原来的简化算法（固定8岁）。

### 变更内容

1. **修改文件**
   - `src/ba_zi/ba_zi.cppm`
     - `DaYunSystem` 类添加 `tyme::ChildLimit` 成员变量
     - 添加 `create_child_limit()` 静态方法创建童限对象
     - 删除 `calculate_qi_yun_age()` 简化方法
     - 使用 `child_limit_.get_start_age()` 获取精确起运年龄
     - 添加 `get_child_limit()` 和 `get_tyme_decade_fortune()` 访问方法
   
   - `BaZiResult` 结构体
     - 添加 `birth_minute` 和 `birth_second` 成员
     - 构造函数传递分钟和秒参数到 `DaYunSystem`
   
   - `src/ba_zi/ba_zi_controller.cpp`
     - 更新 `pai_pan_solar()` 和 `pai_pan_lunar()` 传递分钟参数

2. **功能提升**
   - ✅ 精确计算起运年龄（基于出生时间到节气的距离）
   - ✅ 支持多种童限计算流派
   - ✅ 计算精确到年、月、日、时、分

### 使用说明

```cpp
// 创建八字结果（包含精确起运年龄）
auto result = pai_pan_solar(1990, 1, 1, 12, 30, true);

// 获取起运年龄（自动从 tyme 库计算）
int qi_yun_age = result.da_yun_system.get_qi_yun_age();

// 获取童限对象（访问更多信息）
const auto& child_limit = result.da_yun_system.get_child_limit();

// 从 tyme 库获取大运对象
auto decade_fortune = result.da_yun_system.get_tyme_decade_fortune(0);
```

### API 变化

| 功能 | 旧实现 | 新实现 |
|-----|-------|-------|
| 起运年龄计算 | 固定8岁（简化） | `tyme::ChildLimit` 精确计算 |
| 大运构造 | `DaYunSystem(bazi, is_male, y, m, d, h)` | `DaYunSystem(bazi, is_male, y, m, d, h, min, sec)` |
| BaZiResult 构造 | `BaZiResult(bazi, is_male, y, m, d, h)` | `BaZiResult(bazi, is_male, y, m, d, h, min, sec)` |

### 依赖

新增依赖 `tyme` 库的以下类：
- `tyme::ChildLimit` - 童限系统
- `tyme::DecadeFortune` - 大运
- `tyme::SolarTime` - 公历时间
- `tyme::Gender` - 性别

---

## 2024-10-14 - 重构十神系统

### 变更说明

将十神相关定义从 `ba_zi` 模块移到公共模块 `ganzhi.cppm` 中，统一使用全局定义。

### 变更内容

1. **删除文件**
   - 删除 `src/common/tian_gan/shi_shen.cppm`（重复定义）

2. **修改文件**
   - `src/ba_zi/ba_zi.cppm`
     - 移除了十神枚举定义
     - 移除了 `import ZhouYi.ShiShen;`
     - 将所有 `calculate()` 改为 `get_shi_shen()`
     - 将所有 `calculate_from_zhi()` 改为使用 `get_cang_gan()` + `get_shi_shen()`
     - 将所有 `to_zh()` 改为 `shi_shen_to_zh()`
   
   - `src/ba_zi/ba_zi_controller.cppm`
     - 将 `using ZhouYi::BaZi::ShiShen;` 改为 `using ZhouYi::GanZhi::ShiShen;`
     - 将 `using ZhouYi::BaZi::shi_shen_to_zh;` 改为 `using ZhouYi::GanZhi::shi_shen_to_zh;`

### 使用说明

现在所有模块都应该统一使用 `ganzhi.cppm` 中定义的十神系统：

```cpp
import ZhouYi.GanZhi;

using namespace ZhouYi::GanZhi;

// 计算十神
auto ss = get_shi_shen(day_gan, target_gan);

// 获取中文名称
auto name = shi_shen_to_zh(ss);
```

### API 变化

| 旧 API（ba_zi 模块） | 新 API（ganzhi 模块） |
|---------------------|----------------------|
| `calculate(day_gan, target_gan)` | `get_shi_shen(day_gan, target_gan)` |
| `calculate_from_zhi(day_gan, zhi)` | 使用 `get_cang_gan(zhi)` + `get_shi_shen()` |
| `to_zh(shi_shen)` | `shi_shen_to_zh(shi_shen)` |
| `ZhouYi::BaZi::ShiShen` | `ZhouYi::GanZhi::ShiShen` |

### 影响范围

- ✅ `ba_zi.cppm` - 大运、流年、流月、流日、流时
- ✅ `ba_zi_controller.cppm` - 控制器接口
- ✅ `ba_zi_controller.cpp` - 控制器实现

### 测试建议

1. 测试大运计算中的十神是否正确
2. 测试流年、流月、流日、流时的十神计算
3. 测试 JSON 序列化中的十神输出
4. 测试控制器接口的十神显示

### 好处

1. **避免重复定义**：全局只有一个十神定义
2. **统一接口**：所有模块使用相同的十神 API
3. **易于维护**：修改十神逻辑只需改一处
4. **符合设计原则**：公共定义放在公共模块

