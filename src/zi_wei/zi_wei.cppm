// 紫微斗数核心排盘模块
export module ZhouYi.ZiWei;

import std;
import ZhouYi.GanZhi;
import ZhouYi.BaZiBase;
import ZhouYi.ZiWei.Constants;
import ZhouYi.ZiWei.Palace;
import ZhouYi.ZiWei.Star;
import ZhouYi.ZiWei.Horoscope;
import ZhouYi.ZhMapper;
import ZhouYi.tyme;
import fmt;

export namespace ZhouYi::ZiWei {
    using namespace std;
    using namespace ZhouYi::GanZhi;
    using namespace ZhouYi::BaZiBase;
    using namespace ZhouYi::Mapper;

    /**
     * @brief 宫位完整数据（包含星耀）
     */
    struct PalaceInfo {
        GongWeiData gong_data;           // 宫位基本信息
        vector<StarData> zhu_xing;       // 主星列表
        vector<StarData> fu_xing;        // 辅星列表
        vector<StarData> sha_xing;       // 煞星列表
        
        string to_string() const {
            string result = fmt::format("【{}】", gong_data.to_string());
            
            if (!zhu_xing.empty()) {
                result += "\n  主星：";
                for (const auto& star : zhu_xing) {
                    result += star.to_string() + " ";
                }
            }
            
            if (!fu_xing.empty()) {
                result += "\n  辅星：";
                for (const auto& star : fu_xing) {
                    result += star.to_string() + " ";
                }
            }
            
            if (!sha_xing.empty()) {
                result += "\n  煞星：";
                for (const auto& star : sha_xing) {
                    result += star.to_string() + " ";
                }
            }
            
            return result;
        }
    };

    /**
     * @brief 紫微斗数排盘结果（本命盘/地盘）
     * 
     * 天地人三盘说明：
     * - 地盘（Origin/本命盘）：出生时的命盘，固定不变
     * - 天盘（Decadal/大限盘）：10年一大限，大限宫位起算
     * - 人盘（Yearly/流年盘）：流年、流月、流日、流时
     */
    struct ZiWeiResult {
        // 基本信息
        tyme::SolarDay solar_day;        // 阳历日期
        tyme::LunarDay lunar_day;        // 农历日期
        tyme::LunarHour lunar_hour;      // 农历时辰
        bool is_male;                    // 性别
        
        // 四柱
        Pillar year_pillar;              // 年柱
        Pillar month_pillar;             // 月柱
        Pillar day_pillar;               // 日柱
        Pillar hour_pillar;              // 时柱
        
        // 命盘核心数据
        int ming_gong_index;             // 命宫索引（以寅宫为0）
        int shen_gong_index;             // 身宫索引
        WuXingJu wu_xing_ju;             // 五行局
        int zi_wei_index;                // 紫微星索引
        int tian_fu_index;               // 天府星索引
        
        // 十二宫详细信息
        array<PalaceInfo, 12> palaces;          // 从寅宫开始的十二宫（地盘）
        array<DaXianData, 12> da_xian_data;     // 大限数据（天盘）
        
        /**
         * @brief 根据宫位类型获取宫位信息
         */
        const PalaceInfo& get_palace(GongWei gw) const {
            for (const auto& palace : palaces) {
                if (palace.gong_data.gong_wei == gw) {
                    return palace;
                }
            }
            throw runtime_error("未找到指定宫位");
        }
        
        /**
         * @brief 根据索引获取宫位信息
         */
        const PalaceInfo& get_palace_by_index(int index) const {
            return palaces[fix_index(index)];
        }
        
        /**
         * @brief 获取指定虚岁的运限盘（天地人三盘）
         * 
         * @param target_year 目标公历年份
         * @param target_month 目标农历月份  
         * @param target_day 目标农历日
         * @param target_hour 目标时辰
         * @param current_age 当前虚岁
         * @return 运限结果（包含大限、小限、流年、流月、流日、流时）
         */
        HoroscopeResult get_horoscope(
            int target_year,
            int target_month,
            int target_day,
            DiZhi target_hour,
            int current_age
        ) const;
        
        /**
         * @brief 格式化输出命盘
         */
        string to_string() const {
            string result = "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n";
            result += "           紫微斗数命盘\n";
            result += "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n\n";
            
            result += fmt::format("阳历：{}\n", solar_day.to_string());
            result += fmt::format("农历：{} {}\n", lunar_day.to_string(), 
                                lunar_hour.to_string());
            result += fmt::format("性别：{}\n", is_male ? "男" : "女");
            result += fmt::format("四柱：{} {} {} {}\n",
                                year_pillar.to_string(),
                                month_pillar.to_string(),
                                day_pillar.to_string(),
                                hour_pillar.to_string());
            result += fmt::format("五行局：{}\n", 
                                string(to_zh(wu_xing_ju)));
            result += fmt::format("命宫：{}{}\n",
                                string(to_zh(palaces[ming_gong_index].gong_data.tian_gan)),
                                string(to_zh(palaces[ming_gong_index].gong_data.di_zhi)));
            result += fmt::format("身宫：{}{}\n\n",
                                string(to_zh(palaces[shen_gong_index].gong_data.tian_gan)),
                                string(to_zh(palaces[shen_gong_index].gong_data.di_zhi)));
            
            result += "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n";
            result += "           十二宫详情\n";
            result += "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n\n";
            
            for (int i = 0; i < 12; ++i) {
                result += palaces[i].to_string() + "\n\n";
            }
            
            return result;
        }
    };

    /**
     * @brief 紫微斗数排盘（阳历）
     * 
     * @param year 阳历年
     * @param month 阳历月
     * @param day 阳历日
     * @param hour 时辰（0-23）
     * @param is_male 性别（true为男性）
     * @return 排盘结果
     */
    inline ZiWeiResult pai_pan_solar(
        int year, int month, int day, int hour, bool is_male
    ) {
        // 创建阳历日期
        tyme::SolarDay solar_day = tyme::SolarDay::from_ymd(year, month, day);
        
        // 转换为农历并获取四柱
        auto solar_time = tyme::SolarTime::from_ymd_hms(year, month, day, hour, 0, 0);
        tyme::LunarDay lunar_day = solar_day.get_lunar_day();
        tyme::LunarHour lunar_hour = solar_time.get_lunar_hour();
        tyme::EightChar bazi = lunar_hour.get_eight_char();
         
        // 转换为我们的 Pillar 类型
        auto convert_cycle = [](const tyme::SixtyCycle& cycle) -> Pillar {
            return Pillar(
                cycle.get_heaven_stem().get_name(),
                cycle.get_earth_branch().get_name()
            );
        };
        
        Pillar year_pillar = convert_cycle(bazi.get_year());
        Pillar month_pillar = convert_cycle(bazi.get_month());
        Pillar day_pillar = convert_cycle(bazi.get_day());
        Pillar hour_pillar = convert_cycle(bazi.get_hour());
        
        // 获取农历月份和日期
        int lunar_month = lunar_day.get_month();
        int lunar_day_num = lunar_day.get_day();
        
        // 获取时辰地支
        DiZhi hour_zhi = hour_pillar.zhi;
        
        // 排布十二宫
        vector<GongWeiData> palaces = arrange_twelve_palaces(
            year_pillar.gan, lunar_month, hour_zhi
        );
        
        // 找到命宫索引
        int ming_index = -1;
        int shen_index = -1;
        for (size_t i = 0; i < palaces.size(); ++i) {
            if (palaces[i].is_ming_palace) {
                ming_index = palaces[i].index;
            }
            if (palaces[i].is_body_palace) {
                shen_index = palaces[i].index;
            }
        }
        
        // 获取五行局
        WuXingJu wu_xing_ju = palaces[ming_index].wu_xing_ju;
        
        // 定紫微星和天府星位置
        int zi_wei_idx = get_zi_wei_index(lunar_day_num, wu_xing_ju);
        int tian_fu_idx = get_tian_fu_index(zi_wei_idx);
        
        // 安主星
        auto zi_wei_group = arrange_zi_wei_group(zi_wei_idx);
        auto tian_fu_group = arrange_tian_fu_group(tian_fu_idx);
        
        // 获取四化表
        auto si_hua_table = get_si_hua_table(year_pillar.gan);
        
        // 安辅星
        auto [zuo_idx, you_idx] = get_zuo_you_index(lunar_month);
        auto [chang_idx, qu_idx] = get_chang_qu_index(hour_zhi);
        auto [kui_idx, yue_idx] = get_kui_yue_index(year_pillar.gan);
        
        // 安煞星
        int lu_cun_idx = get_lu_cun_index(year_pillar.gan);
        auto [yang_idx, tuo_idx] = get_yang_tuo_index(lu_cun_idx);
        auto [huo_idx, ling_idx] = get_huo_ling_index(year_pillar.zhi, hour_zhi);
        auto [kong_idx, jie_idx] = get_kong_jie_index(hour_zhi);
        
        // 创建结果对象（使用聚合初始化）
        ZiWeiResult result{
            .solar_day = solar_day,
            .lunar_day = lunar_day,
            .lunar_hour = lunar_hour,
            .is_male = is_male,
            .year_pillar = year_pillar,
            .month_pillar = month_pillar,
            .day_pillar = day_pillar,
            .hour_pillar = hour_pillar,
            .ming_gong_index = ming_index,
            .shen_gong_index = shen_index,
            .wu_xing_ju = wu_xing_ju,
            .zi_wei_index = zi_wei_idx,
            .tian_fu_index = tian_fu_idx,
            .palaces = {},
            .da_xian_data = {}
        };
        
        // 填充每个宫位的星耀
        for (int i = 0; i < 12; ++i) {
            PalaceInfo palace_info;
            palace_info.gong_data = palaces[i];
            
            // 添加紫微星系主星
            for (const auto& [star, idx] : zi_wei_group) {
                if (idx == i) {
                    auto liang_du_table = get_zhu_xing_liang_du_table(star);
                    StarData star_data;
                    star_data.name = string(to_zh(star));
                    star_data.liang_du = liang_du_table[i];
                    star_data.gong_index = i; 
                    
                    // 检查四化
                    if (si_hua_table.contains(star)) {
                        star_data.si_hua = si_hua_table[star];
                    }
                    
                    palace_info.zhu_xing.push_back(star_data);
                }
            }
            
            // 添加天府星系主星
            for (const auto& [star, idx] : tian_fu_group) {
                if (idx == i) {
                    auto liang_du_table = get_zhu_xing_liang_du_table(star);
                    StarData star_data;
                    star_data.name = string(to_zh(star));
                    star_data.liang_du = liang_du_table[i];
                    star_data.gong_index = i;
                    
                    // 检查四化
                    if (si_hua_table.contains(star)) {
                        star_data.si_hua = si_hua_table[star];
                    }
                    
                    palace_info.zhu_xing.push_back(star_data);
                }
            }
            
            // 添加辅星
            if (i == zuo_idx) {
                palace_info.fu_xing.push_back(StarData{
                    .name = string(to_zh(FuXing::ZuoFu)),
                    .liang_du = LiangDu::Ping,
                    .gong_index = i
                });
            }
            if (i == you_idx) {
                palace_info.fu_xing.push_back(StarData{
                    .name = string(to_zh(FuXing::YouBi)),
                    .liang_du = LiangDu::Ping,
                    .gong_index = i
                });
            }
            if (i == chang_idx) {
                palace_info.fu_xing.push_back(StarData{
                    .name = string(to_zh(FuXing::WenChang)),
                    .liang_du = LiangDu::Ping,
                    .gong_index = i
                });
            }
            if (i == qu_idx) {
                palace_info.fu_xing.push_back(StarData{
                    .name = string(to_zh(FuXing::WenQu)),
                    .liang_du = LiangDu::Ping,
                    .gong_index = i
                });
            }
            if (i == kui_idx) {
                palace_info.fu_xing.push_back(StarData{
                    .name = string(to_zh(FuXing::TianKui)),
                    .liang_du = LiangDu::Ping,
                    .gong_index = i
                });
            }
            if (i == yue_idx) {
                palace_info.fu_xing.push_back(StarData{
                    .name = string(to_zh(FuXing::TianYue)),
                    .liang_du = LiangDu::Ping,
                    .gong_index = i
                });
            }
            
            // 添加煞星
            if (i == yang_idx) {
                palace_info.sha_xing.push_back(StarData{
                    .name = string(to_zh(ShaXing::QingYang)),
                    .liang_du = LiangDu::Ping,
                    .gong_index = i
                });
            }
            if (i == tuo_idx) {
                palace_info.sha_xing.push_back(StarData{
                    .name = string(to_zh(ShaXing::TuoLuo)),
                    .liang_du = LiangDu::Ping,
                    .gong_index = i
                });
            }
            if (i == huo_idx) {
                palace_info.sha_xing.push_back(StarData{
                    .name = string(to_zh(ShaXing::HuoXing)),
                    .liang_du = LiangDu::Ping,
                    .gong_index = i
                });
            }
            if (i == ling_idx) {
                palace_info.sha_xing.push_back(StarData{
                    .name = string(to_zh(ShaXing::LingXing)),
                    .liang_du = LiangDu::Ping,
                    .gong_index = i
                });
            }
            if (i == kong_idx) {
                palace_info.sha_xing.push_back(StarData{
                    .name = string(to_zh(ShaXing::DiKong)),
                    .liang_du = LiangDu::Ping,
                    .gong_index = i
                });
            }
            if (i == jie_idx) {
                palace_info.sha_xing.push_back(StarData{
                    .name = string(to_zh(ShaXing::DiJie)),
                    .liang_du = LiangDu::Ping,
                    .gong_index = i
                });
            }
            
            result.palaces[i] = palace_info;
        }
        
        return result;
    }

    /**
     * @brief 紫微斗数排盘（农历）
     * 
     * @param year 农历年
     * @param month 农历月
     * @param day 农历日
     * @param hour 时辰（0-23）
     * @param is_male 性别（true为男性）
     * @param is_leap_month 是否闰月
     * @return 排盘结果
     */
    inline ZiWeiResult pai_pan_lunar(
        int year, int month, int day, int hour, 
        bool is_male, bool is_leap_month = false
    ) {
        // 创建农历日期
        tyme::LunarDay lunar_day = tyme::LunarDay::from_ymd(year, month, day);
        
        // 转换为阳历
        tyme::SolarDay solar_day = lunar_day.get_solar_day();
        
        // 调用阳历排盘
        return pai_pan_solar(
            solar_day.get_year(), 
            solar_day.get_month(), 
            solar_day.get_day(),
            hour, 
            is_male
        );
    }

} // namespace ZhouYi::ZiWei

