// C++23 - 八字控制器实现
module;

// 预编译部分（如果需要）

module ZhouYi.BaZiController;

// 导入需要的模块
import fmt;
import ZhouYi.BaZiBase;
import ZhouYi.BaZi;
import ZhouYi.GanZhi;
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
    
    // 注意：这里使用农历年份，实际应用中可能需要转换为公历
    return BaZiResult(bazi, is_male, year, month, day, hour, minute, 0);
}

/**
 * @brief 显示八字排盘结果（实现）
 */
void display_result(const BaZiResult& result) {
    std::println("==================== 八字排盘 ====================");
    std::println("");
    
    // 基本信息
    std::println("【基本信息】");
    std::println("性别: {}", result.is_male ? "男" : "女");
    std::println("出生: {}年{}月{}日 {}时", 
                 result.birth_year, result.birth_month, 
                 result.birth_day, result.birth_hour);
    std::println("");
    
    // 四柱八字
    std::println("【四柱八字】");
    const auto& bazi = result.ba_zi;
    
    // 打印表头
    std::println("{:>8} {:>8} {:>8} {:>8}", "年柱", "月柱", "日柱", "时柱");
    std::println("{:>8} {:>8} {:>8} {:>8}", 
                 bazi.year.to_string(),
                 bazi.month.to_string(),
                 bazi.day.to_string(),
                 bazi.hour.to_string());
    
    // 十神
    auto shi_shen_arr = result.get_si_zhu_shi_shen();
    std::println("{:>8} {:>8} {:>8} {:>8}",
                 shi_shen_to_zh(shi_shen_arr[0]),
                 shi_shen_to_zh(shi_shen_arr[1]),
                 shi_shen_to_zh(shi_shen_arr[2]),
                 shi_shen_to_zh(shi_shen_arr[3]));
    
    // 旬空
    if (!bazi.xun_kong_1.empty()) {
        std::println("\n旬空: {}{}", bazi.xun_kong_1, bazi.xun_kong_2);
    }
    
    std::println("");
    
    // 大运信息
    std::println("【大运】");
    const auto& da_yun_system = result.da_yun_system;
    std::println("起运年龄: {}岁", da_yun_system.get_qi_yun_age());
    std::println("排运方式: {}", da_yun_system.is_shun_pai() ? "顺排" : "逆排");
    std::println("");
    
    // 打印前5个大运
    const auto& da_yun_list = da_yun_system.get_da_yun_list();
    int count = std::min(5, static_cast<int>(da_yun_list.size()));
    for (int i = 0; i < count; ++i) {
        const auto& dy = da_yun_list[i];
        std::println("  {}", dy.to_string());
    }
    
    std::println("");
    std::println("==================================================");
}

/**
 * @brief 显示大运信息（实现）
 */
void display_da_yun(const BaZiResult& result, int max_count) {
    std::println("==================== 大运信息 ====================");
    std::println("");
    
    const auto& da_yun_list = result.da_yun_system.get_da_yun_list();
    int count = std::min(max_count, static_cast<int>(da_yun_list.size()));
    
    std::println("{:<12} {:<8} {:<8} {:<8}", "干支", "年龄", "天干十神", "地支十神");
    std::println("{:-<48}", "");
    
    for (int i = 0; i < count; ++i) {
        const auto& dy = da_yun_list[i];
        std::println("{:<12} {}-{:>2}岁 {:<8} {:<8}",
                     dy.pillar.to_string(),
                     dy.start_age, dy.end_age,
                     shi_shen_to_zh(dy.gan_shi_shen),
                     shi_shen_to_zh(dy.zhi_shi_shen));
    }
    
    std::println("");
    std::println("==================================================");
}

/**
 * @brief 显示流年信息（实现）
 */
void display_liu_nian(const BaZiResult& result, int start_year, int count) {
    std::println("==================== 流年信息 ====================");
    std::println("");
    
    std::println("{:<8} {:<8} {:<6} {:<8} {:<8}", 
                 "年份", "干支", "年龄", "天干十神", "地支十神");
    std::println("{:-<48}", "");
    
    for (int i = 0; i < count; ++i) {
        int year = start_year + i;
        auto liu_nian = result.get_liu_nian(year);
        
        std::println("{:<8} {:<8} {:<6} {:<8} {:<8}",
                     year,
                     liu_nian.pillar.to_string(),
                     std::format("{}岁", liu_nian.age),
                     shi_shen_to_zh(liu_nian.gan_shi_shen),
                     shi_shen_to_zh(liu_nian.zhi_shi_shen));
    }
    
    std::println("");
    std::println("==================================================");
}

/**
 * @brief 显示流月信息（实现）
 */
void display_liu_yue(const BaZiResult& result, int year) {
    std::println("==================== {}年流月信息 ====================", year);
    std::println("");
    
    std::println("{:<8} {:<8} {:<8} {:<8}", 
                 "月份", "干支", "天干十神", "地支十神");
    std::println("{:-<40}", "");
    
    for (int month = 1; month <= 12; ++month) {
        auto liu_yue = result.get_liu_yue(year, month);
        
        std::println("{:<8} {:<8} {:<8} {:<8}",
                     std::format("{}月", month),
                     liu_yue.pillar.to_string(),
                     shi_shen_to_zh(liu_yue.gan_shi_shen),
                     shi_shen_to_zh(liu_yue.zhi_shi_shen));
    }
    
    std::println("");
    std::println("========================================================");
}

/**
 * @brief 显示童限详细信息（实现）
 */
void display_child_limit_detail(const BaZiResult& result) {
    std::println("==================== 童限详细信息 ====================");
    std::println("");
    
    auto detail = result.get_child_limit_detail();
    
    std::println("【起运信息】");
    std::println("起运年龄: {}岁", detail.start_age);
    std::println("");
    
    std::println("【精确计算】");
    std::println("年: {}年", detail.year_count);
    std::println("月: {}月", detail.month_count);
    std::println("日: {}天", detail.day_count);
    std::println("时: {}小时", detail.hour_count);
    std::println("分: {}分钟", detail.minute_count);
    std::println("");
    
    std::println("【出生时刻】");
    std::println("{}", detail.start_time.to_string());
    std::println("");
    
    std::println("【起运时刻】");
    std::println("{}", detail.end_time.to_string());
    std::println("");
    
    std::println("==================================================");
}

/**
 * @brief 显示 tyme 库大运详细信息（实现）
 */
void display_tyme_decade_fortune(const BaZiResult& result, int index) {
    std::println("==================== 第{}大运详细信息 ====================", index + 1);
    std::println("");
    
    auto decade_fortune = result.get_tyme_decade_fortune(index);
    
    std::println("【基本信息】");
    std::println("干支: {}", decade_fortune.get_name());
    std::println("起始年龄: {}岁", decade_fortune.get_start_age());
    std::println("结束年龄: {}岁", decade_fortune.get_end_age());
    std::println("");
    
    std::println("【起止年份】");
    auto start_year = decade_fortune.get_start_sixty_cycle_year();
    auto end_year = decade_fortune.get_end_sixty_cycle_year();
    std::println("开始: {}", start_year.get_name());
    std::println("结束: {}", end_year.get_name());
    std::println("");
    
    std::println("【小运起点】");
    auto start_fortune = decade_fortune.get_start_fortune();
    std::println("年龄: {}岁", start_fortune.get_age());
    std::println("干支: {}", start_fortune.get_name());
    std::println("");
    
    std::println("========================================================");
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

