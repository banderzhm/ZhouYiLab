// 奇门遁甲控制器 - 提供高级 API
// 整合排盘算法和数据处理

export module ZhouYi.QiMen.Controller;

import ZhouYi.GanZhi;
import ZhouYi.QiMen;
import ZhouYi.QiMen.Pan;
import ZhouYi.tyme;
import fmt;
import std;

/**
 * @brief 奇门遁甲控制器
 */
export namespace ZhouYi::QiMen {

/**
 * @brief 奇门遁甲排盘控制器
 * 
 * 提供简洁的 API 用于生成和查询奇门盘
 * 支持公历和农历两种日期格式
 */
class QiMenController {
public:
    /**
     * @brief 从公历日期排盘
     * 
     * @param year 公历年
     * @param month 公历月（1-12）
     * @param day 公历日（1-31）
     * @param hour 公历时（0-23）
     * @param minute 公历分（0-59，默认0）
     * @return 生成的奇门盘或错误信息
     * 
     * @example
     * // 排一个公历日期的奇门盘
     * auto result = QiMenController::pai_pan_solar(2011, 6, 18, 3, 56);
     * if (result) {
     *     const auto& pan = result.value();
     *     std::println("阴阳遁：{}", pan.dun == Dun::Yang ? "阳遁" : "阴遁");
     * }
     */
    [[nodiscard]] static auto pai_pan_solar(
        int year,
        int month,
        int day,
        int hour,
        int minute = 0
    ) -> std::expected<QiMenPan, std::string> {
        
        // 参数验证
        if (month < 1 || month > 12) {
            return std::unexpected("月份必须在 1-12 之间");
        }
        if (day < 1 || day > 31) {
            return std::unexpected("日期必须在 1-31 之间");
        }
        if (hour > 23) {
            return std::unexpected("小时必须在 0-23 之间");
        }
        if (minute > 59) {
            return std::unexpected("分钟必须在 0-59 之间");
        }
        
        // 声明变量
        std::uint8_t tian_gan_day, di_zhi_day;
        SolarTerm solar_term;
        
        // 从 tyme 模块获取节气、天干、地支信息
        try {
            // 创建公历时间
            auto solar_time = tyme::SolarTime::from_ymd_hms(year, month, day, hour, minute, 0);
            
            // 获取农历时间
            auto lunar_hour = solar_time.get_lunar_hour();
            
            // 获取八字
            auto eight_char = lunar_hour.get_eight_char();
            
            // 获取日柱（干支）
            auto day_cycle = eight_char.get_day();
            auto day_stem = day_cycle.get_heaven_stem();
            auto day_branch = day_cycle.get_earth_branch();
            
            // 转换天干地支为数字（0-9 和 0-11）
            tian_gan_day = static_cast<std::uint8_t>(day_stem.get_index());
            di_zhi_day = static_cast<std::uint8_t>(day_branch.get_index());
            
            // 获取节气信息
            auto solar_day = solar_time.get_solar_day();
            auto term = solar_day.get_term();
            
            // 将 tyme 的节气转换为奇门的节气枚举
            solar_term = convert_tyme_term_to_qimen(term);
            
        } catch (const std::exception& e) {
            return std::unexpected(fmt::format("从 tyme 模块获取信息失败: {}", e.what()));
        }
        
        // 根据小时计算时天干和时地支
        auto [tian_gan_hour, di_zhi_hour] = calculate_hour_gan_zhi(hour);
        
        // 调用排盘生成器
        return QiMenPanGenerator::generate_pan(
            solar_term,
            tian_gan_day,
            di_zhi_day,
            tian_gan_hour,
            di_zhi_hour
        );
    }
    
    /**
     * @brief 从农历日期排盘
     * 
     * @param year 农历年
     * @param month 农历月（1-12，负数表示闰月）
     * @param day 农历日（1-30）
     * @param hour 时辰（0-23）
     * @param minute 分钟（0-59，默认0）
     * @return 生成的奇门盘或错误信息
     * 
     * @example
     * // 排一个农历日期的奇门盘
     * auto result = QiMenController::pai_pan_lunar(2023, 5, 5, 14, 30);
     * if (result) {
     *     const auto& pan = result.value();
     *     std::println("局数：第{}局", pan.ju);
     * }
     */
    [[nodiscard]] static auto pai_pan_lunar(
        int year,
        int month,
        int day,
        int hour,
        int minute = 0
    ) -> std::expected<QiMenPan, std::string> {
        
        // 参数验证
        if (month < -12 || month > 12 || month == 0) {
            return std::unexpected("农历月份必须在 -12 到 12 之间（负数表示闰月）");
        }
        if (day < 1 || day > 30) {
            return std::unexpected("农历日期必须在 1-30 之间");
        }
        if (hour > 23) {
            return std::unexpected("小时必须在 0-23 之间");
        }
        if (minute > 59) {
            return std::unexpected("分钟必须在 0-59 之间");
        }
        
        // 声明变量
        std::uint8_t tian_gan_day, di_zhi_day;
        SolarTerm solar_term;
        
        // 从 tyme 模块进行农历到公历的转换，获取节气、天干、地支信息
        try {
            // 创建农历时间
            auto lunar_hour = tyme::LunarHour::from_ymd_hms(year, month, day, hour, minute, 0);
            
            // 获取八字
            auto eight_char = lunar_hour.get_eight_char();
            
            // 获取日柱（干支）
            auto day_cycle = eight_char.get_day();
            auto day_stem = day_cycle.get_heaven_stem();
            auto day_branch = day_cycle.get_earth_branch();
            
            // 转换天干地支为数字（0-9 和 0-11）
            tian_gan_day = static_cast<std::uint8_t>(day_stem.get_index());
            di_zhi_day = static_cast<std::uint8_t>(day_branch.get_index());
            
            // 获取对应的公历时间以获取节气信息
            auto solar_time = lunar_hour.get_solar_time();
            auto solar_day = solar_time.get_solar_day();
            auto term = solar_day.get_term();
            
            // 将 tyme 的节气转换为奇门的节气枚举
            solar_term = convert_tyme_term_to_qimen(term);
            
        } catch (const std::exception& e) {
            return std::unexpected(fmt::format("从 tyme 模块获取信息失败: {}", e.what()));
        }
        
        // 根据小时计算时天干和时地支
        auto [tian_gan_hour, di_zhi_hour] = calculate_hour_gan_zhi(hour);
        
        // 调用排盘生成器
        return QiMenPanGenerator::generate_pan(
            solar_term,
            tian_gan_day,
            di_zhi_day,
            tian_gan_hour,
            di_zhi_hour
        );
    }
    
    /**
     * @brief 查询奇门盘中特定宫位的信息
     */
    [[nodiscard]] static auto query_palace_info(
        const QiMenPan& pan,
        Palace palace
    ) -> std::expected<PalaceInfo, std::string> {
        
        std::size_t idx = static_cast<std::size_t>(palace);
        if (idx < 1 || idx > 9) {
            return std::unexpected("无效的宫位");
        }
        
        return pan.palaces[idx - 1];
    }
    
    /**
     * @brief 获取奇门盘的完整文本描述
     */
    [[nodiscard]] static auto get_pan_description(const QiMenPan& pan) -> std::string {
        return format_qi_men_pan(pan);
    }
    
    /**
     * @brief 判断两个奇门盘是否相同
     */
    [[nodiscard]] static constexpr bool is_same_pan(
        const QiMenPan& pan1,
        const QiMenPan& pan2
    ) noexcept {
        return pan1.dun == pan2.dun &&
               pan1.yuan == pan2.yuan &&
               pan1.ju == pan2.ju &&
               pan1.solar_term == pan2.solar_term;
    }

private:
    /**
     * @brief 将 tyme 的节气转换为奇门的节气枚举
     */
    [[nodiscard]] static auto convert_tyme_term_to_qimen(const tyme::SolarTerm& term) -> SolarTerm {
        // tyme 的节气索引与奇门的节气枚举一一对应
        // 0=冬至, 1=小寒, 2=大寒, 3=立春, ..., 23=大雪
        int term_index = term.get_index();
        
        // 确保索引在有效范围内
        if (term_index < 0 || term_index > 23) {
            return SolarTerm::DongZhi;  // 默认返回冬至
        }
        
        return static_cast<SolarTerm>(term_index);
    }
    
    /**
     * @brief 根据小时计算时天干和时地支
     * 
     * 十二时辰对应十二地支：
     * 子(0) 丑(1) 寅(2) 卯(3) 辰(4) 巳(5)
     * 午(6) 未(7) 申(8) 酉(9) 戌(10) 亥(11)
     * 
     * 时天干根据日天干推算
     */
    [[nodiscard]] static constexpr auto calculate_hour_gan_zhi(
        std::uint8_t hour
    ) noexcept -> std::pair<std::uint8_t, std::uint8_t> {
        
        // 计算时地支（每两小时一个地支）
        std::uint8_t di_zhi_hour = (hour + 1) / 2 % 12;
        
        // 时天干根据日天干推算
        // 这里简化处理，实际应根据日天干进行推算
        // 甲己日：子时为甲
        // 乙庚日：子时为丙
        // 丙辛日：子时为戊
        // 丁壬日：子时为庚
        // 戊癸日：子时为壬
        std::uint8_t tian_gan_hour = (di_zhi_hour * 2) % 10;
        
        return {tian_gan_hour, di_zhi_hour};
    }
};

/**
 * @brief 奇门盘分析器
 * 
 * 提供对奇门盘的深度分析功能
 */
class QiMenAnalyzer {
public:
    /**
     * @brief 分析奇门盘的吉凶
     * 
     * @param pan 奇门盘
     * @param palace 要分析的宫位
     * @return 分析结果字符串
     */
    [[nodiscard]] static auto analyze_auspiciousness(
        const QiMenPan& pan,
        Palace palace
    ) -> std::expected<std::string, std::string> {
        
        auto palace_info_result = QiMenController::query_palace_info(pan, palace);
        if (!palace_info_result) {
            return std::unexpected(palace_info_result.error());
        }
        
        const auto& info = palace_info_result.value();
        std::string result;
        
        result += fmt::format("宫位: {}\n", palace_name(palace));
        result += fmt::format("九星: {}\n", star_name(info.star));
        result += fmt::format("八门: {}\n", gate_name(info.gate));
        result += fmt::format("八神: {}\n", spirit_name(info.spirit));
        result += fmt::format("地盘天干: {}\n", GanZhi::Mapper::to_zh(static_cast<GanZhi::TianGan>(info.di_gan)));
        result += fmt::format("天盘天干: {}\n", GanZhi::Mapper::to_zh(static_cast<GanZhi::TianGan>(info.tian_gan)));
        
        // 简单的吉凶判断逻辑
        result += "\n吉凶判断:\n";
        
        // 根据九星判断
        switch (info.star) {
            case Star::TianYing:
            case Star::TianFu:
                result += "- 九星：吉\n";
                break;
            case Star::TianRui:
            case Star::TianChong:
                result += "- 九星：中\n";
                break;
            case Star::TianPeng:
                result += "- 九星：凶\n";
                break;
            default:
                result += "- 九星：平\n";
                break;
        }
        
        // 根据八门判断
        switch (info.gate) {
            case Gate::Kai:
            case Gate::Sheng:
                result += "- 八门：吉\n";
                break;
            case Gate::Xiu:
                result += "- 八门：平\n";
                break;
            case Gate::Du:
            case Gate::Si:
                result += "- 八门：凶\n";
                break;
            default:
                result += "- 八门：中\n";
                break;
        }
        
        return result;
    }
    
    /**
     * @brief 获取奇门盘的核心信息摘要
     */
    [[nodiscard]] static auto get_summary(const QiMenPan& pan) -> std::string {
        std::string summary;
        
        summary += fmt::format("【奇门遁甲排盘摘要】\n");
        summary += fmt::format("节气: {}\n", solar_term_name(pan.solar_term));
        summary += fmt::format("遁法: {}\n", pan.dun == Dun::Yang ? "阳遁" : "阴遁");
        summary += fmt::format("三元: ");
        
        switch (pan.yuan) {
            case Yuan::Shang: summary += "上元"; break;
            case Yuan::Zhong: summary += "中元"; break;
            case Yuan::Xia: summary += "下元"; break;
        }
        
        summary += fmt::format("\n局数: 第{}局\n", pan.ju);
        summary += fmt::format("直符: {}\n", star_name(pan.zhi_fu_star));
        summary += fmt::format("直使: {}\n", gate_name(pan.zhi_shi_gate));
        
        return summary;
    }
};

}  // namespace ZhouYi::QiMen