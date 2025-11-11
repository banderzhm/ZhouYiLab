// C++23 - 八字控制器实现
module;

// 预编译部分（如果需要）

module ZhouYi.BaZiController;

// 导入需要的模块
import fmt;
import ZhouYi.BaZiBase;
import ZhouYi.BaZi;
import ZhouYi.GanZhi;
import ZhouYi.tyme;
import std;

namespace ZhouYi::BaZiController {

using namespace ZhouYi::BaZi;
using namespace ZhouYi::BaZiBase;

/**
 * @brief 从公历排盘（实现）
 */
BaZiResult pai_pan_solar(int year, int month, int day, int hour, 
                         int minute, bool is_male) {
    // 创建八字
    auto bazi = BaZi::from_solar(year, month, day, hour, minute);
    
    // 创建完整结果（包含分钟信息用于准确计算起运年龄）
    return BaZiResult(bazi, is_male, year, month, day, hour, minute, 0);
}

/**
 * @brief 从农历排盘（实现）
 */
BaZiResult pai_pan_lunar(int year, int month, int day, int hour,
                         int minute, bool is_male) {
    // 创建八字
    auto bazi = BaZi::from_lunar(year, month, day, hour, minute);
    
    // 将农历转换为公历以获取正确的公历年份
    auto lunar_hour = tyme::LunarHour::from_ymd_hms(year, month, day, hour, minute, 0);
    auto solar_time = lunar_hour.get_solar_time();
    
    // 使用公历日期创建结果
    return BaZiResult(bazi, is_male, 
                     solar_time.get_year(), 
                     solar_time.get_month(), 
                     solar_time.get_day(), 
                     hour, minute, 0);
}

/**
 * @brief 显示八字排盘结果（实现）
 */
void display_result(const BaZiResult& result) {
    fmt::println("==================== 八字排盘 ====================");
    fmt::println("");
    
    // 基本信息
    fmt::println("【基本信息】");
    fmt::println("性别: {}", result.is_male ? "男" : "女");
    fmt::println("出生: {}年{}月{}日 {}时", 
                 result.birth_year, result.birth_month, 
                 result.birth_day, result.birth_hour);
    fmt::println("");
    
    // 四柱八字
    fmt::println("【四柱八字】");
    const auto& bazi = result.ba_zi;
    
    // 打印表头
    fmt::println("{:>8} {:>8} {:>8} {:>8}", "年柱", "月柱", "日柱", "时柱");
    fmt::println("{:>8} {:>8} {:>8} {:>8}", 
                 bazi.year.to_string(),
                 bazi.month.to_string(),
                 bazi.day.to_string(),
                 bazi.hour.to_string());
    
    // 十神
    auto shi_shen_arr = result.get_si_zhu_shi_shen();
    fmt::println("{:>8} {:>8} {:>8} {:>8}",
                 shi_shen_to_zh(shi_shen_arr[0]),
                 shi_shen_to_zh(shi_shen_arr[1]),
                 shi_shen_to_zh(shi_shen_arr[2]),
                 shi_shen_to_zh(shi_shen_arr[3]));
    
    // 旬空
    if (!bazi.xun_kong_1.empty()) {
        fmt::println("\n旬空: {}{}", bazi.xun_kong_1, bazi.xun_kong_2);
    }
    
    fmt::println("");
    
    // 大运信息
    fmt::println("【大运】");
    const auto& da_yun_system = result.da_yun_system;
    fmt::println("起运年龄: {}岁", da_yun_system.get_qi_yun_age());
    fmt::println("排运方式: {}", da_yun_system.is_shun_pai() ? "顺排" : "逆排");
    fmt::println("");
    
    // 打印前5个大运
    const auto& da_yun_list = da_yun_system.get_da_yun_list();
    int count = std::min(5, static_cast<int>(da_yun_list.size()));
    for (int i = 0; i < count; ++i) {
        const auto& dy = da_yun_list[i];
        fmt::println("  {}", dy.to_string());
    }
    
    fmt::println("");
    fmt::println("==================================================");
}

/**
 * @brief 显示大运信息（实现）
 */
void display_da_yun(const BaZiResult& result, int max_count) {
    fmt::println("==================== 大运信息 ====================");
    fmt::println("");
    
    const auto& da_yun_list = result.da_yun_system.get_da_yun_list();
    int count = std::min(max_count, static_cast<int>(da_yun_list.size()));
    
    fmt::println("{:<12} {:<14} {:<14} {:<8} {:<8}", 
                 "干支", "年龄", "年份", "天干十神", "地支十神");
    fmt::println("{:-<68}", "");
    
    for (int i = 0; i < count; ++i) {
        const auto& dy = da_yun_list[i];
        fmt::println("{:<12} {:<14} {:<14} {:<8} {:<8}",
                     dy.pillar.to_string(),
                     fmt::format("{}-{:>2}岁", dy.start_age, dy.end_age),
                     fmt::format("{}-{}年", dy.start_year, dy.end_year),
                     shi_shen_to_zh(dy.gan_shi_shen),
                     shi_shen_to_zh(dy.zhi_shi_shen));
    }
    
    fmt::println("");
    fmt::println("==================================================");
}

/**
 * @brief 显示流年信息（实现）
 */
void display_liu_nian(const BaZiResult& result, int start_year, int count) {
    fmt::println("==================== 流年信息 ====================");
    fmt::println("");
    
    fmt::println("{:<8} {:<8} {:<6} {:<8} {:<8}", 
                 "年份", "干支", "年龄", "天干十神", "地支十神");
    fmt::println("{:-<48}", "");
    
    for (int i = 0; i < count; ++i) {
        int year = start_year + i;
        auto liu_nian = result.get_liu_nian(year);
        
        fmt::println("{:<8} {:<8} {:<6} {:<8} {:<8}",
                     year,
                     liu_nian.pillar.to_string(),
                     fmt::format("{}岁", liu_nian.age),
                     shi_shen_to_zh(liu_nian.gan_shi_shen),
                     shi_shen_to_zh(liu_nian.zhi_shi_shen));
    }
    
    fmt::println("");
    fmt::println("==================================================");
}

/**
 * @brief 显示流月信息（实现）- 按节气月显示
 */
void display_liu_yue(const BaZiResult& result, int year) {
    fmt::println("==================== {}年流月信息（节气月）====================", year);
    fmt::println("");
    
    fmt::println("{:<8} {:<8} {:<14} {:<8} {:<8}", 
                 "农历月份", "干支", "公历起始日期", "天干十神", "地支十神");
    fmt::println("{:-<56}", "");
    
    auto liu_yue_list = result.get_liu_yue_list(year);
    
    for (const auto& liu_yue : liu_yue_list) {
        fmt::println("{:<8} {:<8} {:<14} {:<8} {:<8}",
                     fmt::format("{}月", liu_yue.lunar_month_index),
                     liu_yue.pillar.to_string(),
                     liu_yue.start_date,
                     shi_shen_to_zh(liu_yue.gan_shi_shen),
                     shi_shen_to_zh(liu_yue.zhi_shi_shen));
    }
    
    fmt::println("");
    fmt::println("========================================================");
}

/**
 * @brief 显示童限详细信息（实现）
 */
void display_child_limit_detail(const BaZiResult& result) {
    fmt::println("==================== 童限详细信息 ====================");
    fmt::println("");
    
    auto detail = result.get_child_limit_detail();
    
    fmt::println("【起运信息】");
    fmt::println("起运年龄: {}岁", detail.start_age);
    fmt::println("");
    
    fmt::println("【精确计算】");
    fmt::println("年: {}年", detail.year_count);
    fmt::println("月: {}月", detail.month_count);
    fmt::println("日: {}天", detail.day_count);
    fmt::println("时: {}小时", detail.hour_count);
    fmt::println("分: {}分钟", detail.minute_count);
    fmt::println("");
    
    fmt::println("【出生时刻】");
    fmt::println("{}", detail.start_time.to_string());
    fmt::println("");
    
    fmt::println("【起运时刻】");
    fmt::println("{}", detail.end_time.to_string());
    fmt::println("");
    
    fmt::println("==================================================");
}

/**
 * @brief 显示 tyme 库大运详细信息（实现）
 */
void display_tyme_decade_fortune(const BaZiResult& result, int index) {
    fmt::println("==================== 第{}大运详细信息 ====================", index + 1);
    fmt::println("");
    
    auto decade_fortune = result.get_tyme_decade_fortune(index);
    
    fmt::println("【基本信息】");
    fmt::println("干支: {}", decade_fortune.get_name());
    fmt::println("起始年龄: {}岁", decade_fortune.get_start_age());
    fmt::println("结束年龄: {}岁", decade_fortune.get_end_age());
    fmt::println("");
    
    fmt::println("【起止年份】");
    auto start_year = decade_fortune.get_start_sixty_cycle_year();
    auto end_year = decade_fortune.get_end_sixty_cycle_year();
    fmt::println("开始: {}", start_year.get_name());
    fmt::println("结束: {}", end_year.get_name());
    fmt::println("");
    
    fmt::println("【小运起点】");
    auto start_fortune = decade_fortune.get_start_fortune();
    fmt::println("年龄: {}岁", start_fortune.get_age());
    fmt::println("干支: {}", start_fortune.get_name());
    fmt::println("");
    
    fmt::println("========================================================");
}

/**
 * @brief 批量排盘（实现）
 */
std::vector<BaZiResult> batch_pai_pan(
    const std::vector<std::tuple<int, int, int, int, bool>>& requests) {
    
    std::vector<BaZiResult> results;
    results.reserve(requests.size());
    
    for (const auto& [year, month, day, hour, is_male] : requests) {
        results.push_back(pai_pan_solar(year, month, day, hour, 0, is_male));
    }
    
    return results;
}

} // namespace ZhouYi::BaZiController