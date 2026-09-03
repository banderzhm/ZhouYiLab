// C++23 Module - 八字基础数据结构
// 提供干支柱、八字等通用数据结构（基于枚举，类型安全）
export module ZhouYi.BaZiBase;
import ZhouYi.TrueSolarTime;

// 导入第三方库模块
import nlohmann.json;
import fmt;

// 导入干支系统模块
import ZhouYi.GanZhi;

// 导入农历时间库模块
import ZhouYi.tyme;

// 导入标准库模块（最后）
import std;

/**
 * @brief 八字基础命名空间
 *
 * 提供玄学算法中通用的基础数据结构
 * 所有结构体基于枚举类型，提供编译期类型安全
 */
export namespace ZhouYi::BaZiBase {

// 使用干支系统的类型
using ZhouYi::GanZhi::DiZhi;
using ZhouYi::GanZhi::TianGan;
using ZhouYi::GanZhi::WuXing;
// Mapper 是命名空间，使用 using namespace 或直接用全限定名
namespace Mapper = ZhouYi::GanZhi::Mapper;

/**
 * @brief 干支柱结构体（基于枚举，类型安全）
 *
 * 表示一个天干地支的组合（如"甲子"）
 * 使用枚举类型，提供编译期类型检查，避免字符串错误
 */
struct Pillar {
  TianGan gan; ///< 天干枚举。
  DiZhi zhi;   ///< 地支枚举。

  // ==================== 构造函数 ====================

  /**
   * @brief 默认构造函数（甲子）
   */
  Pillar() : gan(TianGan::Jia), zhi(DiZhi::Zi) {}

  /**
   * @brief 枚举构造函数（推荐使用）
   */
  Pillar(TianGan g, DiZhi z) : gan(g), zhi(z) {}

  /**
   * @brief 从字符串构造（辅助构造函数）
   *
   * 用于从字符串创建干支柱，主要用于解析外部数据
   *
   * @param stem_str 天干字符串（如 "甲"）
   * @param branch_str 地支字符串（如 "子"）
   * @throws std::invalid_argument 如果字符串无法映射到枚举
   *
   * @example
   * Pillar p1("甲", "子");  // 创建甲子
   * Pillar p2("乙", "丑");  // 创建乙丑
   */
  Pillar(const std::string &stem_str, const std::string &branch_str);

  /**
   * @brief 从字符串视图构造
   */
  Pillar(std::string_view stem_str, std::string_view branch_str);

  // ==================== 比较运算符 ====================

  auto operator<=>(const Pillar &other) const = default;
  bool operator==(const Pillar &other) const = default;

  // ==================== 转换函数 ====================

  /**
   * @brief 转换为完整字符串
   * @return 如 "甲子"
   */
  std::string to_string() const {
    return std::string(Mapper::to_zh(gan)) + std::string(Mapper::to_zh(zhi));
  }

  /**
   * @brief 获取天干字符串
   * @return 如 "甲"
   */
  std::string stem() const { return std::string(Mapper::to_zh(gan)); }

  /**
   * @brief 获取地支字符串
   * @return 如 "子"
   */
  std::string branch() const { return std::string(Mapper::to_zh(zhi)); }

  // ==================== 输出运算符 ====================

  /**
   * @brief 重载 << 操作符
   */
  friend std::ostream &operator<<(std::ostream &os, const Pillar &obj) {
    return os << Mapper::to_zh(obj.gan) << Mapper::to_zh(obj.zhi);
  }

  // ==================== JSON 序列化 ====================

  /**
   * @brief JSON 序列化支持
   */
  friend void to_json(nlohmann::json &j, const Pillar &p) {
    j = {{"stem", p.stem()}, {"branch", p.branch()}};
  }

  /**
   * @brief JSON 反序列化支持
   */
  friend void from_json(const nlohmann::json &j, Pillar &p) {
    std::string stem_str = j["stem"];
    std::string branch_str = j["branch"];
    p = Pillar(stem_str, branch_str);
  }
};

/** 单柱纳音基础事实，由基础层统一计算，分析层不得重复维护六十甲子表。 */
struct PillarNayin {
  std::string name;
  WuXing element;
};

/** 精确出生时刻对应的节后日数与人元司令。 */
struct HumanCommandInfo {
  bool available{};
  TianGan stem{TianGan::Jia};
  std::string previous_jie;
  double days_since_jie{};
  std::string table_version{"ziping-renyuan-v1"};
  std::string reason;
};

/**
 * @brief 返回四柱位置的统一中文名称。
 *
 *
 * 四柱位置属于八字基础数据，不应由各分析流派重复维护名称表。
 * @param position
 * 位置索引，0=年柱、1=月柱、2=日柱、3=时柱。
 * @throws std::out_of_range
 * 当索引不在 0~3 范围内时抛出。
 */
std::string pillar_position_name(std::size_t position);

/** @brief 计算单柱纳音及其所属五行。 */
PillarNayin calculate_pillar_nayin(const Pillar &pillar);

/**
 * @brief 按出生公历时刻计算人元司令。
 * @param year 公历年份。
 * @param
 * month 公历月份。
 * @param day 公历日期。
 * @param hour 公历小时。
 * @param
 * minute 分钟。
 * @param second 秒。
 */
HumanCommandInfo calculate_human_command(int year, int month, int day, int hour,
                                         int minute = 0, int second = 0);
/**
 * @brief 四柱八字结构体
 *
 * 表示一个完整的四柱八字信息（年月日时）
 */
struct BaZi {
  Pillar year;            ///< 年柱。
  Pillar month;           ///< 月柱。
  Pillar day;             ///< 日柱。
  Pillar hour;            ///< 时柱。
  std::string xun_kong_1; ///< 旬空地支一。
  std::string xun_kong_2; ///< 旬空地支二。

  // 默认构造函数
  BaZi() = default;

  // 完整构造函数
  BaZi(const Pillar &y, const Pillar &m, const Pillar &d, const Pillar &h,
       const std::string &xk1 = "", const std::string &xk2 = "")
      : year(y), month(m), day(d), hour(h), xun_kong_1(xk1), xun_kong_2(xk2) {}

  // 比较运算符（注意：由于 std::string 在模块环境下的 <=> 问题，只提供 ==）
  bool operator==(const BaZi &other) const = default;

  /**
   * @brief 重载 << 操作符，用于输出 BaZi
   */
  friend std::ostream &operator<<(std::ostream &os, const BaZi &bazi) {
    os << "年柱: " << bazi.year << "\n"
       << "月柱: " << bazi.month << "\n"
       << "日柱: " << bazi.day << "\n"
       << "时柱: " << bazi.hour << "\n"
       << "旬空: " << bazi.xun_kong_1 << bazi.xun_kong_2;
    return os;
  }

  /**
   * @brief JSON 序列化支持
   */
  friend void to_json(nlohmann::json &j, const BaZi &b) {
    j = {{"year", b.year},
         {"month", b.month},
         {"day", b.day},
         {"hour", b.hour},
         {"xun_kong_1", b.xun_kong_1},
         {"xun_kong_2", b.xun_kong_2}};
  }

  /**
   * @brief JSON 反序列化支持
   */
  friend void from_json(const nlohmann::json &j, BaZi &b) {
    b.year = j["year"];
    b.month = j["month"];
    b.day = j["day"];
    b.hour = j["hour"];
    std::string xk1 = j["xun_kong_1"];
    std::string xk2 = j["xun_kong_2"];
    b.xun_kong_1 = xk1;
    b.xun_kong_2 = xk2;
  }

  /**
   * @brief 从公历日期时间创建八字
   *
   * @param year 公历年（如 2024）
   * @param month 公历月（1-12）
   * @param day 公历日（1-31）
   * @param hour 公历时（0-23）
   * @param minute 公历分（0-59，默认0）
   * @param second 公历秒（0-59，默认0）
   * @return 八字对象（包含旬空信息）
   *
   * @example
   * auto bazi = BaZi::from_solar(2024, 1, 15, 12);  // 2024年1月15日12时
   */
  static BaZi from_solar(int year, int month, int day, int hour, int minute = 0,
                         int second = 0);

  /** 按出生经纬度修正真太阳时后排盘。 */
  static BaZi from_solar(int year, int month, int day, int hour, int minute,
                         int second,
                         const std::optional<ZhouYi::Time::Location> &location);

  /**
   * @brief 从农历日期时间创建八字
   *
   * @param year 农历年
   * @param month 农历月（1-12，负数表示闰月）
   * @param day 农历日（1-30）
   * @param hour 时辰（0-23）
   * @param minute 分钟（0-59，默认0）
   * @param second 秒（0-59，默认0）
   * @return 八字对象（包含旬空信息）
   *
   * @example
   * auto bazi = BaZi::from_lunar(2024, 1, 1, 0);  // 农历2024年正月初一子时
   */
  static BaZi from_lunar(int year, int month, int day, int hour, int minute = 0,
                         int second = 0);
};

/**
 * @brief 按固定顺序返回四柱快照。
 *
 *
 * 顺序始终为年、月、日、时，供各分析流派共享，避免重复维护柱位数组。

 */
std::array<Pillar, 4> get_pillars(const BaZi &chart);

/** @brief 返回四柱天干，顺序为年、月、日、时。 */
std::array<TianGan, 4> get_stems(const BaZi &chart);

/** @brief 返回四柱地支，顺序为年、月、日、时。 */
std::array<DiZhi, 4> get_branches(const BaZi &chart);

/**
 * @brief 公历日期结构体
 *
 * 简单封装公历日期，方便使用
 */
struct SolarDate {
  int year;  ///< 公历年。
  int month; ///< 公历月（1-12）。
  int day;   ///< 公历日（1-31）。

  /**
   * @brief 转换为农历日期
   */
  std::string to_lunar_string() const {
    auto solar_day = tyme::SolarDay::from_ymd(year, month, day);
    auto lunar_day = solar_day.get_lunar_day();
    return lunar_day.to_string();
  }

  /**
   * @brief 获取八字（按中午12点计算）
   */
  BaZi to_bazi() const { return BaZi::from_solar(year, month, day, 12); }
};

/**
 * @brief 农历日期结构体
 *
 * 简单封装农历日期，方便使用
 */
struct LunarDate {
  int year;  ///< 农历年。
  int month; ///< 农历月（正数为正常月份，负数表示闰月）。
  int day;   ///< 农历日（1-30）。

  /**
   * @brief 转换为公历日期字符串
   */
  std::string to_solar_string() const {
    auto lunar_day = tyme::LunarDay::from_ymd(year, month, day);
    auto solar_day = lunar_day.get_solar_day();
    return solar_day.to_string();
  }

  /**
   * @brief 获取八字（按子时初计算）
   */
  BaZi to_bazi() const { return BaZi::from_lunar(year, month, day, 0); }
};

} // namespace ZhouYi::BaZiBase

// ==================== fmt 格式化支持（模块外） ====================

// 为 Pillar 提供 fmt 格式化支持
template <>
struct fmt::formatter<ZhouYi::BaZiBase::Pillar> : fmt::formatter<std::string> {
  auto format(const ZhouYi::BaZiBase::Pillar &p,
              fmt::format_context &ctx) const {
    return fmt::formatter<std::string>::format(p.to_string(), ctx);
  }
};

// 为 BaZi 提供 fmt 格式化支持
template <>
struct fmt::formatter<ZhouYi::BaZiBase::BaZi> : fmt::formatter<std::string> {
  auto format(const ZhouYi::BaZiBase::BaZi &b, fmt::format_context &ctx) const {
    std::string result =
        fmt::format("年柱: {}\n月柱: {}\n日柱: {}\n时柱: {}\n旬空: {}{}",
                    b.year.to_string(), b.month.to_string(), b.day.to_string(),
                    b.hour.to_string(), b.xun_kong_1, b.xun_kong_2);
    return fmt::formatter<std::string>::format(result, ctx);
  }
};
