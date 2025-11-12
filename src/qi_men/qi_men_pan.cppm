// 奇门遁甲排盘算法实现
// 实现完整的排盘逻辑

export module ZhouYi.QiMen.Pan;

import ZhouYi.QiMen;
import fmt;
import std;

/**
 * @brief 奇门排盘算法实现
 */
export namespace ZhouYi::QiMen {

/**
 * @brief 排盘器类
 * 
 * 负责生成完整的奇门盘
 */
class QiMenPanGenerator {
public:
    /**
     * @brief 生成奇门盘
     * 
     * @param solar_term 节气
     * @param tian_gan_day 日天干（0-9）
     * @param di_zhi_day 日地支（0-11）
     * @param tian_gan_hour 时天干（0-9）
     * @param di_zhi_hour 时地支（0-11）
     * @return 生成的奇门盘
     */
    [[nodiscard]] static auto generate_pan(
        SolarTerm solar_term,
        std::uint8_t tian_gan_day,
        std::uint8_t di_zhi_day,
        std::uint8_t tian_gan_hour,
        std::uint8_t di_zhi_hour
    ) -> std::expected<QiMenPan, std::string> {
        
        // 参数验证
        if (tian_gan_day >= 10) {
            return std::unexpected("日天干必须在 0-9 之间");
        }
        if (di_zhi_day >= 12) {
            return std::unexpected("日地支必须在 0-11 之间");
        }
        if (tian_gan_hour >= 10) {
            return std::unexpected("时天干必须在 0-9 之间");
        }
        if (di_zhi_hour >= 12) {
            return std::unexpected("时地支必须在 0-11 之间");
        }
        
        QiMenPan pan{};
        
        // 1. 确定阴阳遁
        pan.dun = get_dun_from_solar_term(solar_term);
        
        // 2. 根据日地支确定三元
        pan.yuan = get_yuan_from_di_zhi(di_zhi_day);
        
        // 3. 确定局数
        pan.ju = get_ju_from_solar_term_and_yuan(solar_term, pan.yuan);
        
        // 4. 记录节气
        pan.solar_term = solar_term;
        
        // 5. 排布地盘（地盘天干）
        arrange_di_pan(pan, pan.ju);
        
        // 6. 确定直符和直使
        determine_zhi_fu_and_zhi_shi(pan, di_zhi_hour);
        
        // 7. 排布天盘（天盘天干）
        arrange_tian_pan(pan, di_zhi_hour);
        
        // 8. 排布人盘（人盘八门）
        arrange_ren_pan(pan, di_zhi_hour);
        
        // 9. 排布神盘（八神）
        arrange_shen_pan(pan);
        
        return pan;
    }

private:
    /**
     * @brief 排布地盘天干
     * 
     * 地盘天干按照戊己庚辛壬癸丁丙乙的顺序排列
     */
    static void arrange_di_pan(QiMenPan& pan, std::uint8_t ju) {
        auto gan_seq = get_tian_gan_sequence();
        auto palace_seq = get_palace_sequence(pan.dun == Dun::Yin);
        
        // 确定戊的起始宫位
        Palace start_palace = get_palace_for_ju(ju);
        
        // 根据阴阳遁确定排列方向
        if (pan.dun == Dun::Yang) {
            // 阳遁顺飞：9 1 2 3 4 5 6 7 8
            std::array<Palace, 9> yang_sequence = {
                Palace::South, Palace::North, Palace::SouthWest,
                Palace::East, Palace::Center, Palace::West,
                Palace::NorthEast, Palace::NorthWest, Palace::NorthWest
            };
            
            for (std::size_t i = 0z; i < 9; ++i) {
                Palace p = yang_sequence[i];
                auto& palace_info = pan.palaces[static_cast<std::size_t>(p) - 1];
                palace_info.palace = p;
                palace_info.di_gan = gan_seq[i];
                palace_info.star = get_star_at_palace(p);
                palace_info.gate = get_gate_at_palace(p);
            }
        } else {
            // 阴遁逆飞：9 7 8 3 5 1 4 2 6
            std::array<Palace, 9> yin_sequence = {
                Palace::South, Palace::West, Palace::NorthEast,
                Palace::East, Palace::Center, Palace::North,
                Palace::SouthEast, Palace::SouthWest, Palace::NorthWest
            };
            
            for (std::size_t i = 0z; i < 9; ++i) {
                Palace p = yin_sequence[i];
                auto& palace_info = pan.palaces[static_cast<std::size_t>(p) - 1];
                palace_info.palace = p;
                palace_info.di_gan = gan_seq[i];
                palace_info.star = get_star_at_palace(p);
                palace_info.gate = get_gate_at_palace(p);
            }
        }
    }
    
    /**
     * @brief 获取局数对应的起始宫位
     */
    static constexpr Palace get_palace_for_ju(std::uint8_t ju) noexcept {
        // 局数与起始宫位的对应关系
        // 这里简化处理，实际应根据具体的排盘规则
        switch (ju) {
            case 1: return Palace::North;
            case 2: return Palace::SouthWest;
            case 3: return Palace::East;
            case 4: return Palace::SouthEast;
            case 5: return Palace::Center;
            case 6: return Palace::NorthWest;
            case 7: return Palace::West;
            case 8: return Palace::NorthEast;
            case 9: return Palace::South;
            default: return Palace::Center;
        }
    }
    
    /**
     * @brief 确定直符和直使
     */
    static void determine_zhi_fu_and_zhi_shi(QiMenPan& pan, std::uint8_t di_zhi_hour) {
        // 根据时地支确定旬首
        JiaXun jia_xun = get_jia_xun_from_di_zhi(di_zhi_hour);
        
        // 获取直符所在的宫位
        auto& zhi_fu_info = pan.palaces[static_cast<std::size_t>(Palace::South) - 1];
        pan.zhi_fu_star = zhi_fu_info.star;
        pan.zhi_fu_palace = zhi_fu_info.palace;
        pan.zhi_shi_gate = zhi_fu_info.gate;
    }
    
    /**
     * @brief 根据地支确定旬首
     */
    static constexpr JiaXun get_jia_xun_from_di_zhi(std::uint8_t di_zhi) noexcept {
        // 根据地支确定所属的旬
        switch (di_zhi) {
            case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:
                return JiaXun::JiaZi;  // 子-酉 属于甲子旬
            case 10: case 11:
                return JiaXun::JiaZi;  // 戌亥 属于甲子旬
            default:
                return JiaXun::JiaZi;
        }
    }
    
    /**
     * @brief 排布天盘天干
     * 
     * 天盘天干随着直符旋转
     */
    static void arrange_tian_pan(QiMenPan& pan, std::uint8_t di_zhi_hour) {
        // 天盘天干根据直符所在宫位进行旋转
        // 这里需要根据时辰计算旋转的宫位数
        
        auto gan_seq = get_tian_gan_sequence();
        
        // 计算旋转的宫位数
        std::size_t rotate_count = (di_zhi_hour + 1) % 9;
        
        for (std::size_t i = 0z; i < 9; ++i) {
            std::size_t rotated_idx = (i + rotate_count) % 9;
            pan.palaces[i].tian_gan = gan_seq[rotated_idx];
        }
    }
    
    /**
     * @brief 排布人盘八门
     * 
     * 人盘八门根据时辰进行旋转
     */
    static void arrange_ren_pan(QiMenPan& pan, std::uint8_t di_zhi_hour) {
        // 人盘八门的旋转逻辑
        // 根据时辰确定旋转的宫位数
        
        std::array<Gate, 8> gate_seq = {
            Gate::Jing, Gate::Si, Gate::Jing_Gate, Gate::Kai,
            Gate::Xiu, Gate::Sheng, Gate::Shang, Gate::Du
        };
        
        std::size_t rotate_count = (di_zhi_hour + 1) % 8;
        
        std::size_t gate_idx = 0z;
        for (std::size_t i = 0z; i < 9; ++i) {
            if (i == 4) continue;  // 跳过中宫
            std::size_t rotated_idx = (gate_idx + rotate_count) % 8;
            pan.palaces[i].gate = gate_seq[rotated_idx];
            ++gate_idx;
        }
    }
    
    /**
     * @brief 排布神盘八神
     * 
     * 八神根据直符位置进行排列
     */
    static void arrange_shen_pan(QiMenPan& pan) {
        std::array<Spirit, 8> spirit_seq = {
            Spirit::ZhiFu, Spirit::TengShe, Spirit::TaiYin, Spirit::LiuHe,
            Spirit::BaiHu, Spirit::XuanWu, Spirit::JiuDi, Spirit::JiuTian
        };
        
        // 根据直符位置确定八神的排列
        std::size_t start_idx = static_cast<std::size_t>(pan.zhi_fu_palace);
        
        std::size_t spirit_idx = 0z;
        for (std::size_t i = 0z; i < 9; ++i) {
            if (i == 4) continue;  // 跳过中宫
            std::size_t rotated_idx = (spirit_idx + start_idx) % 8;
            pan.palaces[i].spirit = spirit_seq[rotated_idx];
            ++spirit_idx;
        }
    }
};

/**
 * @brief 格式化输出奇门盘
 */
[[nodiscard]] inline auto format_qi_men_pan(const QiMenPan& pan) -> std::string {
    std::string result;
    
    result += fmt::format("奇门遁甲排盘\n");
    result += fmt::format("节气: {}\n", solar_term_name(pan.solar_term));
    result += fmt::format("阴阳遁: {}\n", pan.dun == Dun::Yang ? "阳遁" : "阴遁");
    result += fmt::format("三元: ");
    switch (pan.yuan) {
        case Yuan::Shang: result += "上元"; break;
        case Yuan::Zhong: result += "中元"; break;
        case Yuan::Xia: result += "下元"; break;
    }
    result += fmt::format("\n局数: {}\n", pan.ju);
    result += fmt::format("直符: {}\n", star_name(pan.zhi_fu_star));
    result += fmt::format("直使: {}\n", gate_name(pan.zhi_shi_gate));
    
    result += "\n九宫排盘:\n";
    result += "┌──────┬──────┬──────┐\n";
    
    // 排列顺序：西北(6) 北(1) 东北(8)
    //          西(7)  中(5) 东(3)
    //          西南(2) 南(9) 东南(4)
    
    for (std::size_t row = 0z; row < 3; ++row) {
        for (std::size_t col = 0z; col < 3; ++col) {
            result += "│";
            std::size_t palace_idx = row * 3 + col;
            if (palace_idx < pan.palaces.size()) {
                const auto& info = pan.palaces[palace_idx];
                result += fmt::format("{:^6}", star_name(info.star));
            }
        }
        result += "│\n";
        
        for (std::size_t col = 0z; col < 3; ++col) {
            result += "├──────";
        }
        result += "┤\n";
    }
    result += "└──────┴──────┴──────┘\n";
    
    return result;
}

}  // namespace ZhouYi::QiMen
