// 农历日历模块 - 包装 tyme4cpp 库
// tyme4cpp: https://github.com/6tail/tyme4cpp

export module ZhouYi.LunarCalendar;

// 导入第三方库（tyme4cpp 使用传统头文件）
#include <tyme.h>

// 导入反射库
import magic_enum;

// 导入标准库（最后）
import std;

/**
 * @brief 农历日历命名空间
 * 
 * 基于 tyme4cpp 实现，提供：
 * - 公历与农历互转
 * - 干支纪年、月、日
 * - 二十四节气
 * - 生肖、星座
 * - 法定假日
 */
export namespace ZhouYi::Lunar {

/**
 * @brief 农历日期类
 * 
 * 封装 tyme::LunarDay，提供简洁的 API
 */
class LunarDate {
public:
    /**
     * @brief 从公历日期创建农历日期
     * @param year 公历年
     * @param month 公历月
     * @param day 公历日
     * @return 农历日期对象
     */
    static LunarDate from_solar(int year, int month, int day) {
        auto solar = tyme::SolarDay::from_ymd(year, month, day);
        return LunarDate(solar.get_lunar_day());
    }
    
    /**
     * @brief 从农历日期创建
     * @param year 农历年
     * @param month 农历月（负数表示闰月）
     * @param day 农历日
     * @return 农历日期对象
     */
    static LunarDate from_lunar(int year, int month, int day) {
        auto lunar = tyme::LunarDay::from_ymd(year, month, day);
        return LunarDate(lunar);
    }
    
    /**
     * @brief 获取农历年份
     */
    int get_year() const {
        return lunar_day_.get_lunar_month().get_lunar_year().get_year();
    }
    
    /**
     * @brief 获取农历月份（负数表示闰月）
     */
    int get_month() const {
        return lunar_day_.get_lunar_month().get_month();
    }
    
    /**
     * @brief 获取农历日
     */
    int get_day() const {
        return lunar_day_.get_day();
    }
    
    /**
     * @brief 是否为闰月
     */
    bool is_leap_month() const {
        return lunar_day_.get_lunar_month().get_month() < 0;
    }
    
    /**
     * @brief 获取农历年干支
     * @return 如 "甲子"
     */
    std::string get_year_gan_zhi() const {
        return lunar_day_.get_lunar_month().get_lunar_year().get_sixty_cycle().get_name();
    }
    
    /**
     * @brief 获取农历月干支
     */
    std::string get_month_gan_zhi() const {
        return lunar_day_.get_lunar_month().get_sixty_cycle().get_name();
    }
    
    /**
     * @brief 获取农历日干支
     */
    std::string get_day_gan_zhi() const {
        return lunar_day_.get_sixty_cycle().get_name();
    }
    
    /**
     * @brief 获取农历年份字符串
     * @return 如 "农历甲子年"
     */
    std::string get_year_name() const {
        return lunar_day_.get_lunar_month().get_lunar_year().get_name();
    }
    
    /**
     * @brief 获取农历月份字符串
     * @return 如 "正月"、"闰四月"
     */
    std::string get_month_name() const {
        return lunar_day_.get_lunar_month().get_name();
    }
    
    /**
     * @brief 获取农历日字符串
     * @return 如 "初一"、"十五"、"廿三"
     */
    std::string get_day_name() const {
        return lunar_day_.get_name();
    }
    
    /**
     * @brief 获取生肖
     * @return 如 "虎"
     */
    std::string get_zodiac() const {
        return lunar_day_.get_lunar_month().get_lunar_year().get_zodiac().get_name();
    }
    
    /**
     * @brief 获取完整农历日期字符串
     * @return 如 "农历甲寅年四月廿一"
     */
    std::string to_string() const {
        return lunar_day_.to_string();
    }
    
    /**
     * @brief 转换为公历日期
     */
    std::tuple<int, int, int> to_solar() const {
        auto solar = lunar_day_.get_solar_day();
        return {solar.get_year(), solar.get_month(), solar.get_day()};
    }
    
    /**
     * @brief 获取原始 tyme::LunarDay 对象
     */
    const tyme::LunarDay& get_lunar_day() const {
        return lunar_day_;
    }

private:
    explicit LunarDate(const tyme::LunarDay& lunar_day) 
        : lunar_day_(lunar_day) {}
    
    tyme::LunarDay lunar_day_;
};

/**
 * @brief 公历日期类
 */
class SolarDate {
public:
    /**
     * @brief 从公历日期创建
     */
    static SolarDate from_ymd(int year, int month, int day) {
        return SolarDate(tyme::SolarDay::from_ymd(year, month, day));
    }
    
    /**
     * @brief 获取今天的公历日期
     */
    static SolarDate today() {
        auto now = std::chrono::system_clock::now();
        auto time = std::chrono::system_clock::to_time_t(now);
        auto tm = *std::localtime(&time);
        return from_ymd(tm.tm_year + 1900, tm.tm_mon + 1, tm.tm_mday);
    }
    
    /**
     * @brief 获取年份
     */
    int get_year() const {
        return solar_day_.get_year();
    }
    
    /**
     * @brief 获取月份
     */
    int get_month() const {
        return solar_day_.get_month();
    }
    
    /**
     * @brief 获取日
     */
    int get_day() const {
        return solar_day_.get_day();
    }
    
    /**
     * @brief 获取星期
     * @return 0=周日, 1=周一, ..., 6=周六
     */
    int get_week() const {
        return solar_day_.get_week().get_index();
    }
    
    /**
     * @brief 获取星期字符串
     * @return 如 "星期一"
     */
    std::string get_week_name() const {
        return solar_day_.get_week().get_name();
    }
    
    /**
     * @brief 获取星座
     * @return 如 "白羊座"
     */
    std::string get_constellation() const {
        return solar_day_.get_constellation().get_name();
    }
    
    /**
     * @brief 获取节气（如果当天是节气）
     * @return optional，如果不是节气则为空
     */
    std::optional<std::string> get_solar_term() const {
        auto term = solar_day_.get_term();
        if (term.has_value()) {
            return term.value()->get_name();
        }
        return std::nullopt;
    }
    
    /**
     * @brief 获取公历日期字符串
     * @return 如 "1986年5月29日"
     */
    std::string to_string() const {
        return solar_day_.to_string();
    }
    
    /**
     * @brief 转换为农历日期
     */
    LunarDate to_lunar() const {
        return LunarDate::from_solar(get_year(), get_month(), get_day());
    }
    
    /**
     * @brief 获取原始 tyme::SolarDay 对象
     */
    const tyme::SolarDay& get_solar_day() const {
        return solar_day_;
    }

private:
    explicit SolarDate(const tyme::SolarDay& solar_day) 
        : solar_day_(solar_day) {}
    
    tyme::SolarDay solar_day_;
};

/**
 * @brief 二十四节气工具类
 */
class SolarTerm {
public:
    /**
     * @brief 获取指定年份的所有节气
     * @param year 年份
     * @return 节气名称和对应日期的列表
     */
    static std::vector<std::pair<std::string, std::tuple<int, int, int>>> get_terms_of_year(int year) {
        std::vector<std::pair<std::string, std::tuple<int, int, int>>> result;
        
        // 遍历12个月
        for (int month = 1; month <= 12; ++month) {
            auto solar_month = tyme::SolarMonth::from_ym(year, month);
            auto terms = solar_month.get_terms();
            
            for (const auto& term : terms) {
                auto solar = term->get_julian_day().get_solar_day();
                result.emplace_back(
                    term->get_name(),
                    std::make_tuple(solar.get_year(), solar.get_month(), solar.get_day())
                );
            }
        }
        
        return result;
    }
    
    /**
     * @brief 获取指定月份的节气
     * @param year 年份
     * @param month 月份
     * @return 该月的节气列表
     */
    static std::vector<std::pair<std::string, int>> get_terms_of_month(int year, int month) {
        std::vector<std::pair<std::string, int>> result;
        
        auto solar_month = tyme::SolarMonth::from_ym(year, month);
        auto terms = solar_month.get_terms();
        
        for (const auto& term : terms) {
            auto solar = term->get_julian_day().get_solar_day();
            result.emplace_back(term->get_name(), solar.get_day());
        }
        
        return result;
    }
};

/**
 * @brief 干支工具类
 */
class GanZhi {
public:
    /**
     * @brief 从索引获取天干
     * @param index 0-9
     * @return 天干名称
     */
    static std::string get_tian_gan(int index) {
        auto gan = tyme::HeavenStem::from_index(index);
        return gan.get_name();
    }
    
    /**
     * @brief 从索引获取地支
     * @param index 0-11
     * @return 地支名称
     */
    static std::string get_di_zhi(int index) {
        auto zhi = tyme::EarthBranch::from_index(index);
        return zhi.get_name();
    }
    
    /**
     * @brief 从索引获取六十甲子
     * @param index 0-59
     * @return 干支名称，如 "甲子"
     */
    static std::string get_sixty_cycle(int index) {
        auto cycle = tyme::SixtyCycle::from_index(index);
        return cycle.get_name();
    }
    
    /**
     * @brief 获取所有天干
     */
    static std::vector<std::string> get_all_tian_gan() {
        std::vector<std::string> result;
        for (int i = 0; i < 10; ++i) {
            result.push_back(get_tian_gan(i));
        }
        return result;
    }
    
    /**
     * @brief 获取所有地支
     */
    static std::vector<std::string> get_all_di_zhi() {
        std::vector<std::string> result;
        for (int i = 0; i < 12; ++i) {
            result.push_back(get_di_zhi(i));
        }
        return result;
    }
    
    /**
     * @brief 获取六十甲子表
     */
    static std::vector<std::string> get_sixty_cycles() {
        std::vector<std::string> result;
        for (int i = 0; i < 60; ++i) {
            result.push_back(get_sixty_cycle(i));
        }
        return result;
    }
};

} // namespace ZhouYi::Lunar

