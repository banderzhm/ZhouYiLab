# 八字模块 API 文档

## BaZiResult 类 API

### 基本信息访问

```cpp
// 获取四柱八字
const BaZi& ba_zi;

// 获取性别
bool is_male;

// 获取出生信息
int birth_year;
int birth_month;
int birth_day;
int birth_hour;
int birth_minute;
int birth_second;

// 获取大运系统
const DaYunSystem& da_yun_system;
```

### 十神相关

```cpp
// 获取四柱十神
std::array<ShiShen, 4> get_si_zhu_shi_shen() const;
// 返回: [年柱十神, 月柱十神, 日柱十神(比肩), 时柱十神]
```

### 大运查询

```cpp
// 获取所有大运列表
const std::vector<DaYun>& get_da_yun_list() const;

// 根据年龄获取当前大运
std::optional<DaYun> get_current_da_yun(int age) const;
```

### 流年流月流日流时

```cpp
// 获取指定年份的流年
LiuNian get_liu_nian(int year) const;

// 获取指定年月的流月
LiuYue get_liu_yue(int year, int month) const;

// 获取指定日期的流日
LiuRi get_liu_ri(int year, int month, int day) const;

// 获取指定时辰的流时
LiuShi get_liu_shi(int year, int month, int day, int hour) const;
```

### 童限详细信息 ⭐核心功能

```cpp
// 获取童限详细信息结构体
DaYunSystem::ChildLimitDetail get_child_limit_detail() const;

// ChildLimitDetail 结构体成员：
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

**示例**：
```cpp
auto detail = result.get_child_limit_detail();
std::println("起运年龄: {}岁", detail.start_age);
std::println("精确到: {}年{}月{}天{}时{}分", 
             detail.year_count, detail.month_count, 
             detail.day_count, detail.hour_count, detail.minute_count);
std::println("出生时刻: {}", detail.start_time.to_string());
std::println("起运时刻: {}", detail.end_time.to_string());
```

### tyme 库完整访问 ⭐核心功能

#### 获取单个大运对象

```cpp
// 获取指定索引的大运（tyme::DecadeFortune）
tyme::DecadeFortune get_tyme_decade_fortune(int index) const;
// index: 0开始，0=第一个大运，1=第二个大运...
```

**tyme::DecadeFortune 可用方法**：
```cpp
auto decade = result.get_tyme_decade_fortune(0);

// 基本信息
std::string get_name() const;           // 干支名称，如"甲子"
int get_start_age() const;              // 起始年龄
int get_end_age() const;                // 结束年龄

// 起止年份
tyme::SixtyCycleYear get_start_sixty_cycle_year() const;  // 起始干支年
tyme::SixtyCycleYear get_end_sixty_cycle_year() const;    // 结束干支年

// 干支信息
tyme::SixtyCycle get_sixty_cycle() const;  // 六十甲子对象
// - get_heaven_stem(): 天干
// - get_earth_branch(): 地支

// 小运
tyme::Fortune get_start_fortune() const;   // 起始小运

// 下一大运
tyme::DecadeFortune next(int n) const;     // n=1 下一个，n=-1 上一个
```

**示例**：
```cpp
auto decade = result.get_tyme_decade_fortune(0);

std::println("干支: {}", decade.get_name());
std::println("年龄: {}-{}岁", decade.get_start_age(), decade.get_end_age());

auto start_year = decade.get_start_sixty_cycle_year();
auto end_year = decade.get_end_sixty_cycle_year();
std::println("年份: {} 至 {}", start_year.get_name(), end_year.get_name());

auto sixty_cycle = decade.get_sixty_cycle();
std::println("天干: {}", sixty_cycle.get_heaven_stem().get_name());
std::println("地支: {}", sixty_cycle.get_earth_branch().get_name());

// 链式访问下一个大运
auto next = decade.next(1);
std::println("下一大运: {}", next.get_name());
```

#### 获取所有大运对象

```cpp
// 获取多个大运对象列表
std::vector<tyme::DecadeFortune> get_all_tyme_decade_fortunes(int count = 10) const;
```

**示例**：
```cpp
auto fortunes = result.get_all_tyme_decade_fortunes(10);
for (size_t i = 0; i < fortunes.size(); ++i) {
    std::println("大运{}: {} ({}-{}岁)", 
                 i + 1,
                 fortunes[i].get_name(),
                 fortunes[i].get_start_age(),
                 fortunes[i].get_end_age());
}
```

#### 获取小运对象

```cpp
// 获取指定索引的小运（tyme::Fortune）
tyme::Fortune get_tyme_fortune(int index) const;
```

**tyme::Fortune 可用方法**：
```cpp
auto fortune = result.get_tyme_fortune(0);

// 基本信息
int get_age() const;                    // 年龄
std::string get_name() const;           // 干支名称
tyme::SixtyCycleYear get_sixty_cycle_year() const;  // 干支年
tyme::SixtyCycle get_sixty_cycle() const;            // 六十甲子

// 下一小运
tyme::Fortune next(int n) const;
```

**示例**：
```cpp
auto fortune = result.get_tyme_fortune(0);
std::println("小运: {} {}岁", fortune.get_name(), fortune.get_age());

auto next_fortune = fortune.next(1);
std::println("下一小运: {} {}岁", next_fortune.get_name(), next_fortune.get_age());
```

### JSON 导出

```cpp
// 转换为 JSON
nlohmann::json to_json() const;
```

**JSON 结构**：
```json
{
  "ba_zi": {
    "year": {"stem": "甲", "branch": "子"},
    "month": {"stem": "丙", "branch": "寅"},
    "day": {"stem": "戊", "branch": "申"},
    "hour": {"stem": "壬", "branch": "子"},
    "xun_kong_1": "戌",
    "xun_kong_2": "亥"
  },
  "is_male": true,
  "birth_date": {
    "year": 1990,
    "month": 1,
    "day": 1,
    "hour": 12
  },
  "da_yun": {
    "qi_yun_age": 8,
    "shun_pai": true,
    "list": [...]
  },
  "shi_shen": {
    "year": "偏财",
    "month": "食神",
    "day": "比肩",
    "hour": "比肩"
  }
}
```

## DaYunSystem 类 API

### 基本信息

```cpp
// 获取起运年龄
int get_qi_yun_age() const;

// 是否顺排
bool is_shun_pai() const;

// 获取大运列表
const std::vector<DaYun>& get_da_yun_list() const;

// 根据年龄查找大运
std::optional<DaYun> get_da_yun_by_age(int age) const;
```

### 童限访问

```cpp
// 获取童限对象引用
const tyme::ChildLimit& get_child_limit() const;

// 获取童限详细信息（推荐使用）
ChildLimitDetail get_child_limit_detail() const;
```

### tyme 库访问

```cpp
// 获取单个大运
tyme::DecadeFortune get_tyme_decade_fortune(int index) const;

// 获取多个大运
std::vector<tyme::DecadeFortune> get_all_tyme_decade_fortunes(int count = 10) const;
```

## 控制器 API (ZhouYi::BaZiController)

### 排盘接口

```cpp
// 公历排盘
BaZiResult pai_pan_solar(int year, int month, int day, int hour, 
                         int minute = 0, bool is_male = true);

// 农历排盘
BaZiResult pai_pan_lunar(int year, int month, int day, int hour,
                         int minute = 0, bool is_male = true);

// 批量排盘
std::vector<BaZiResult> batch_pai_pan(
    const std::vector<std::tuple<int, int, int, int, bool>>& requests);
```

### 显示接口

```cpp
// 显示完整结果
void display_result(const BaZiResult& result);

// 显示大运列表
void display_da_yun(const BaZiResult& result, int max_count = 10);

// 显示流年
void display_liu_nian(const BaZiResult& result, int start_year, int count = 10);

// 显示流月
void display_liu_yue(const BaZiResult& result, int year);

// 显示童限详细信息 ⭐新增
void display_child_limit_detail(const BaZiResult& result);

// 显示 tyme 大运详细信息 ⭐新增
void display_tyme_decade_fortune(const BaZiResult& result, int index);
```

## 数据结构

### DaYun (大运)

```cpp
struct DaYun {
    Pillar pillar;              // 大运干支
    int start_age;              // 起运年龄
    int end_age;                // 结束年龄
    ShiShen gan_shi_shen;       // 天干十神
    ShiShen zhi_shi_shen;       // 地支十神
    
    bool contains_age(int age) const;      // 判断年龄是否在范围内
    std::string to_string() const;         // 转换为字符串
};
```

### LiuNian (流年)

```cpp
struct LiuNian {
    int year;                   // 公历年份
    Pillar pillar;              // 流年干支
    int age;                    // 虚岁年龄
    ShiShen gan_shi_shen;       // 天干十神
    ShiShen zhi_shi_shen;       // 地支十神
    
    std::string to_string() const;
};
```

### LiuYue (流月)

```cpp
struct LiuYue {
    int year;                   // 公历年份
    int month;                  // 公历月份
    Pillar pillar;              // 流月干支
    ShiShen gan_shi_shen;       // 天干十神
    ShiShen zhi_shi_shen;       // 地支十神
    
    std::string to_string() const;
};
```

### LiuRi (流日)

```cpp
struct LiuRi {
    int year;                   // 公历年份
    int month;                  // 公历月份
    int day;                    // 公历日
    Pillar pillar;              // 流日干支
    ShiShen gan_shi_shen;       // 天干十神
    ShiShen zhi_shi_shen;       // 地支十神
    
    std::string to_string() const;
};
```

### LiuShi (流时)

```cpp
struct LiuShi {
    int hour;                   // 时辰（0-23）
    Pillar pillar;              // 流时干支
    ShiShen gan_shi_shen;       // 天干十神
    ShiShen zhi_shi_shen;       // 地支十神
    
    std::string to_string() const;
};
```

## 完整使用示例

```cpp
import ZhouYi.BaZiController;
import std;

using namespace ZhouYi::BaZiController;

// 1. 排盘
auto result = pai_pan_solar(1990, 1, 1, 12, 30, true);

// 2. 基本信息
display_result(result);

// 3. 童限详细信息
auto detail = result.get_child_limit_detail();
std::println("起运: {}岁 ({}年{}月{}天{}时{}分)", 
             detail.start_age,
             detail.year_count, detail.month_count, 
             detail.day_count, detail.hour_count, detail.minute_count);

// 4. 大运信息（tyme 对象）
auto decade = result.get_tyme_decade_fortune(0);
std::println("第一大运: {} ({}-{}岁)", 
             decade.get_name(),
             decade.get_start_age(),
             decade.get_end_age());

// 5. 流年信息
auto liu_nian = result.get_liu_nian(2024);
std::println("2024年流年: {}", liu_nian.to_string());

// 6. JSON 导出
auto json = result.to_json();
std::println("{}", json.dump(2));
```

## 注意事项

1. **时间精度**：排盘时提供完整的时间（包括分钟）可以获得更精确的起运年龄
2. **索引规则**：大运和小运的索引都从 0 开始
3. **内存管理**：tyme 对象按值返回，注意大对象的拷贝开销
4. **线程安全**：所有 const 方法都是线程安全的
5. **性能**：批量排盘比多次调用单次排盘更高效

## 扩展功能

### 访问原始 tyme 对象

如果需要 tyme 库的完整功能，可以直接访问：

```cpp
const auto& child_limit = result.da_yun_system.get_child_limit();
// 现在可以使用 tyme::ChildLimit 的所有方法
```

### 自定义童限计算流派

tyme 库支持多种童限计算流派，可以通过设置 `ChildLimit::provider` 切换：

```cpp
// 默认流派
tyme::ChildLimit::provider = new tyme::DefaultChildLimitProvider();

// 元亨利贞流派
tyme::ChildLimit::provider = new tyme::China95ChildLimitProvider();

// Lunar 流派1
tyme::ChildLimit::provider = new tyme::LunarSect1ChildLimitProvider();

// Lunar 流派2
tyme::ChildLimit::provider = new tyme::LunarSect2ChildLimitProvider();
```

---

**最后更新**：2024-10-14
**版本**：v1.1.0（新增 tyme 完整访问）

