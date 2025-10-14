// 紫微斗数星耀定位模块（实现）
module ZhouYi.ZiWei.Star;

import ZhouYi.GanZhi;
import ZhouYi.ZiWei.Constants;
import ZhouYi.ZhMapper;
import fmt;
import std;

namespace ZhouYi::ZiWei {
    using namespace std;
    using namespace ZhouYi::GanZhi;
    using namespace ZhouYi::Mapper;

    // ============= StarData 实现 =============

    string StarData::to_string() const {
        string result = fmt::format("{} [{}]", name, string(to_zh(liang_du)));
        if (si_hua.has_value()) {
            result += fmt::format(" {}", string(to_zh(*si_hua)));
        }
        return result;
    }

    // ============= 主星定位算法 =============

    /**
     * @brief 起紫微星诀
     * 
     * 口诀：
     * 六五四三二，酉午亥辰丑，
     * 局数除日数，商数宫前走；
     * 若见数无余，便要起虎口，
     * 日数小于局，还直宫中守。
     * 
     * 解释：
     * - 水二局从丑开始，木三局从辰开始，金四局从亥开始
     * - 土五局从午开始，火六局从酉开始
     * - 以农历日数除以局数，商数从寅宫起数
     * - 如果有余数，余数为偶数顺行，奇数逆行
     */
    int get_zi_wei_index(int lunar_day, WuXingJu wu_xing_ju) {
        int ju_num = static_cast<int>(wu_xing_ju);
        
        int offset = 0;
        int remainder = -1;
        
        do {
            int divisor = lunar_day + offset;
            remainder = divisor % ju_num;
            if (remainder == 0) {
                break;
            }
            offset++;
        } while (true);
        
        int quotient = (lunar_day + offset) / ju_num;
        int zi_wei_index = (quotient - 1) % 12;
        
        if (offset % 2 == 0) {
            zi_wei_index = fix_index(zi_wei_index + offset);
        } else {
            zi_wei_index = fix_index(zi_wei_index - offset);
        }
        
        return zi_wei_index;
    }

    /**
     * @brief 安紫微诸星诀
     * 
     * 口诀：
     * 紫微逆去天机星，隔一太阳武曲辰，
     * 连接天同空二宫，廉贞居处方是真。
     * 
     * 排列：紫微 → 天机(逆1) → 空 → 太阳(逆3) → 武曲(逆4) → 
     *       天同(逆5) → 空 → 空 → 廉贞(逆8)
     */
    map<ZhuXing, int> arrange_zi_wei_group(int zi_wei_index) {
        map<ZhuXing, int> positions;
        
        positions[ZhuXing::ZiWei] = zi_wei_index;
        positions[ZhuXing::TianJi] = fix_index(zi_wei_index - 1);
        positions[ZhuXing::TaiYang] = fix_index(zi_wei_index - 3);
        positions[ZhuXing::WuQu] = fix_index(zi_wei_index - 4);
        positions[ZhuXing::TianTong] = fix_index(zi_wei_index - 5);
        positions[ZhuXing::LianZhen] = fix_index(zi_wei_index - 8);
        
        return positions;
    }

    /**
     * @brief 安天府诸星诀
     * 
     * 口诀：
     * 天府顺行有太阴，贪狼而后巨门临，
     * 随来天相天梁继，七杀空三是破军。
     * 
     * 排列：天府 → 太阴(顺1) → 贪狼(顺2) → 巨门(顺3) → 天相(顺4) → 
     *       天梁(顺5) → 七杀(顺6) → 空 → 空 → 空 → 破军(顺10)
     */
    map<ZhuXing, int> arrange_tian_fu_group(int tian_fu_index) {
        map<ZhuXing, int> positions;
        
        positions[ZhuXing::TianFu] = tian_fu_index;
        positions[ZhuXing::TaiYin] = fix_index(tian_fu_index + 1);
        positions[ZhuXing::TanLang] = fix_index(tian_fu_index + 2);
        positions[ZhuXing::JuMen] = fix_index(tian_fu_index + 3);
        positions[ZhuXing::TianXiang] = fix_index(tian_fu_index + 4);
        positions[ZhuXing::TianLiang] = fix_index(tian_fu_index + 5);
        positions[ZhuXing::QiSha] = fix_index(tian_fu_index + 6);
        positions[ZhuXing::PoJun] = fix_index(tian_fu_index + 10);
        
        return positions;
    }

    array<LiangDu, 12> get_zhu_xing_liang_du_table(ZhuXing star) {
        using LD = LiangDu;
        
        switch (star) {
            case ZhuXing::ZiWei:
                return {LD::Wang, LD::Wang, LD::De, LD::Wang, LD::Miao, LD::Miao,
                        LD::Wang, LD::Wang, LD::De, LD::Wang, LD::Ping, LD::Miao};
            case ZhuXing::TianJi:
                return {LD::De, LD::Wang, LD::Li, LD::Ping, LD::Miao, LD::Xian,
                        LD::De, LD::Wang, LD::Li, LD::Ping, LD::Miao, LD::Xian};
            case ZhuXing::TaiYang:
                return {LD::Wang, LD::Miao, LD::Wang, LD::Wang, LD::Wang, LD::De,
                        LD::De, LD::Xian, LD::Bu, LD::Xian, LD::Xian, LD::Bu};
            case ZhuXing::WuQu:
                return {LD::De, LD::Li, LD::Miao, LD::Ping, LD::Wang, LD::Miao,
                        LD::De, LD::Li, LD::Miao, LD::Ping, LD::Wang, LD::Miao};
            case ZhuXing::TianTong:
                return {LD::Li, LD::Ping, LD::Ping, LD::Miao, LD::Xian, LD::Bu,
                        LD::Wang, LD::Ping, LD::Ping, LD::Miao, LD::Wang, LD::Bu};
            case ZhuXing::LianZhen:
                return {LD::Miao, LD::Ping, LD::Li, LD::Xian, LD::Ping, LD::Li,
                        LD::Miao, LD::Ping, LD::Li, LD::Xian, LD::Ping, LD::Li};
            case ZhuXing::TianFu:
                return {LD::Miao, LD::De, LD::Miao, LD::De, LD::Wang, LD::Miao,
                        LD::De, LD::Wang, LD::Miao, LD::De, LD::Miao, LD::Miao};
            case ZhuXing::TaiYin:
                return {LD::Wang, LD::Xian, LD::Xian, LD::Xian, LD::Bu, LD::Bu,
                        LD::Li, LD::Bu, LD::Wang, LD::Miao, LD::Miao, LD::Miao};
            case ZhuXing::TanLang:
                return {LD::Ping, LD::Li, LD::Miao, LD::Xian, LD::Wang, LD::Miao,
                        LD::Ping, LD::Li, LD::Miao, LD::Xian, LD::Wang, LD::Miao};
            case ZhuXing::JuMen:
                return {LD::Miao, LD::Miao, LD::Xian, LD::Wang, LD::Wang, LD::Bu,
                        LD::Miao, LD::Miao, LD::Xian, LD::Wang, LD::Wang, LD::Bu};
            case ZhuXing::TianXiang:
                return {LD::Miao, LD::Xian, LD::De, LD::De, LD::Miao, LD::De,
                        LD::Miao, LD::Xian, LD::De, LD::De, LD::Miao, LD::Miao};
            case ZhuXing::TianLiang:
                return {LD::Miao, LD::Miao, LD::Miao, LD::Xian, LD::Miao, LD::Wang,
                        LD::Xian, LD::De, LD::Miao, LD::Xian, LD::Miao, LD::Wang};
            case ZhuXing::QiSha:
                return {LD::Miao, LD::Wang, LD::Miao, LD::Ping, LD::Wang, LD::Miao,
                        LD::Miao, LD::Miao, LD::Miao, LD::Ping, LD::Wang, LD::Miao};
            case ZhuXing::PoJun:
                return {LD::De, LD::Xian, LD::Wang, LD::Ping, LD::Miao, LD::Wang,
                        LD::De, LD::Xian, LD::Wang, LD::Ping, LD::Miao, LD::Wang};
            default:
                return {LD::Ping, LD::Ping, LD::Ping, LD::Ping, LD::Ping, LD::Ping,
                        LD::Ping, LD::Ping, LD::Ping, LD::Ping, LD::Ping, LD::Ping};
        }
    }

    // ============= 辅星和煞星定位算法 =============

    /**
     * @brief 安左辅右弼诀
     * 
     * 口诀：
     * 辰上顺正寻左辅，
     * 戌上逆正右弼当。
     * 
     * 解释：从辰宫起正月顺数为左辅，从戌宫起正月逆数为右弼
     */
    constexpr pair<int, int> get_zuo_you_index(int lunar_month) {
        // 辰宫索引为2（寅0卯1辰2）
        int zuo_index = fix_index(2 + lunar_month - 1);
        // 戌宫索引为8
        int you_index = fix_index(8 - (lunar_month - 1));
        return {zuo_index, you_index};
    }

    /**
     * @brief 安文昌文曲诀
     * 
     * 口诀：
     * 戌上逆时觅文昌，
     * 辰上顺时文曲位。
     * 
     * 解释：从戌宫起子时逆数为文昌，从辰宫起子时顺数为文曲
     */
    constexpr pair<int, int> get_chang_qu_index(DiZhi hour_zhi) {
        int hour_index = static_cast<int>(hour_zhi);
        // 戌宫索引为8，逆数时辰得文昌
        int chang_index = fix_index(8 - hour_index);
        // 辰宫索引为2，顺数时辰得文曲
        int qu_index = fix_index(2 + hour_index);
        return {chang_index, qu_index};
    }

    /**
     * @brief 安天魁天钺诀
     * 
     * 口诀：
     * 甲戊庚之年丑未，乙己之年子申，
     * 丙丁之年亥酉，辛年午寅，
     * 壬癸之年卯巳。
     * 
     * 说明：天魁天钺按年干起，前者为魁后者为钺
     */
    pair<int, int> get_kui_yue_index(TianGan year_gan) {
        // 地支索引映射（以寅宫为0）：
        // 寅0 卯1 辰2 巳3 午4 未5 申6 酉7 戌8 亥9 子10 丑11
        
        switch (year_gan) {
            case TianGan::Jia:
            case TianGan::Wu:
            case TianGan::Geng:
                return {11, 5}; // 丑未
            case TianGan::Yi:
            case TianGan::Ji:
                return {10, 6}; // 子申
            case TianGan::Bing:
            case TianGan::Ding:
                return {9, 7};  // 亥酉
            case TianGan::Xin:
                return {4, 0};  // 午寅
            case TianGan::Ren:
            case TianGan::Gui:
                return {1, 3};  // 卯巳
            default:
                return {0, 0};
        }
    }

    /**
     * @brief 定禄存诀
     * 
     * 口诀：
     * 甲禄到寅宫，乙禄居卯府。
     * 丙戊禄在巳，丁己禄在午。
     * 庚禄定居申，辛禄酉上补。
     * 壬禄亥中藏，癸禄居子户。
     */
    constexpr int get_lu_cun_index(TianGan year_gan) {
        switch (year_gan) {
            case TianGan::Jia: return 0;  // 寅
            case TianGan::Yi: return 1;   // 卯
            case TianGan::Bing:
            case TianGan::Wu: return 3;   // 巳
            case TianGan::Ding:
            case TianGan::Ji: return 4;   // 午
            case TianGan::Geng: return 6; // 申
            case TianGan::Xin: return 7;  // 酉
            case TianGan::Ren: return 9;  // 亥
            case TianGan::Gui: return 10; // 子
            default: return 0;
        }
    }

    /**
     * @brief 定擎羊陀罗诀
     * 
     * 口诀：
     * 禄前羊刃当，禄后陀罗府。
     * 
     * 解释：擎羊在禄存前一位，陀罗在禄存后一位
     */
    pair<int, int> get_yang_tuo_index(int lu_cun_index) {
        return {
            fix_index(lu_cun_index + 1),  // 擎羊在禄存前一位
            fix_index(lu_cun_index - 1)   // 陀罗在禄存后一位
        };
    }

    /**
     * @brief 安火星铃星诀
     * 
     * 口诀：
     * 申子辰人寅戌扬，寅午戌人丑卯方，
     * 巳酉丑人卯戌位，亥卯未人酉戌房。
     * 
     * 解释：前者为火星起始位，后者为铃星起始位，再从时辰顺数
     */
    pair<int, int> get_huo_ling_index(DiZhi year_zhi, DiZhi hour_zhi) {
        int hour_offset = static_cast<int>(hour_zhi);
        int huo_start, ling_start;
        
        if (year_zhi == DiZhi::Shen || year_zhi == DiZhi::Zi || year_zhi == DiZhi::Chen) {
            huo_start = 0;  // 寅
            ling_start = 8; // 戌
        } else if (year_zhi == DiZhi::Yin || year_zhi == DiZhi::Wu || year_zhi == DiZhi::Xu) {
            huo_start = 11; // 丑
            ling_start = 1;  // 卯
        } else if (year_zhi == DiZhi::Si || year_zhi == DiZhi::You || year_zhi == DiZhi::Chou) {
            huo_start = 1;  // 卯
            ling_start = 8; // 戌
        } else { // 亥卯未
            huo_start = 7;  // 酉
            ling_start = 8; // 戌
        }
        
        return {
            fix_index(huo_start + hour_offset),
            fix_index(ling_start + hour_offset)
        };
    }

    /**
     * @brief 安地空地劫诀
     * 
     * 口诀：
     * 亥上子时顺安劫，
     * 逆回便是地空亡。
     * 
     * 解释：从亥宫起子时，顺数为地劫，逆数为地空
     */
    pair<int, int> get_kong_jie_index(DiZhi hour_zhi) {
        int hour_index = static_cast<int>(hour_zhi);
        int hai_index = 9; // 亥宫索引
        
        return {
            fix_index(hai_index - hour_index), // 地空（逆数）
            fix_index(hai_index + hour_index)  // 地劫（顺数）
        };
    }

    map<ZhuXing, SiHua> get_si_hua_table(TianGan year_gan) {
        map<ZhuXing, SiHua> table;
        
        switch (year_gan) {
            case TianGan::Jia:
                table[ZhuXing::LianZhen] = SiHua::Lu;
                table[ZhuXing::PoJun] = SiHua::Quan;
                table[ZhuXing::WuQu] = SiHua::Ke;
                table[ZhuXing::TaiYang] = SiHua::Ji;
                break;
            case TianGan::Yi:
                table[ZhuXing::TianJi] = SiHua::Lu;
                table[ZhuXing::TianLiang] = SiHua::Quan;
                table[ZhuXing::ZiWei] = SiHua::Ke;
                table[ZhuXing::TaiYin] = SiHua::Ji;
                break;
            case TianGan::Bing:
                table[ZhuXing::TianTong] = SiHua::Lu;
                table[ZhuXing::TianJi] = SiHua::Quan;
                table[ZhuXing::LianZhen] = SiHua::Ji;
                break;
            case TianGan::Ding:
                table[ZhuXing::TaiYin] = SiHua::Lu;
                table[ZhuXing::TianTong] = SiHua::Quan;
                table[ZhuXing::TianJi] = SiHua::Ke;
                table[ZhuXing::JuMen] = SiHua::Ji;
                break;
            case TianGan::Wu:
                table[ZhuXing::TanLang] = SiHua::Lu;
                table[ZhuXing::TaiYin] = SiHua::Quan;
                table[ZhuXing::TianJi] = SiHua::Ji;
                break;
            case TianGan::Ji:
                table[ZhuXing::WuQu] = SiHua::Lu;
                table[ZhuXing::TanLang] = SiHua::Quan;
                table[ZhuXing::TianLiang] = SiHua::Ke;
                break;
            case TianGan::Geng:
                table[ZhuXing::TaiYang] = SiHua::Lu;
                table[ZhuXing::WuQu] = SiHua::Quan;
                table[ZhuXing::TaiYin] = SiHua::Ke;
                table[ZhuXing::TianTong] = SiHua::Ji;
                break;
            case TianGan::Xin:
                table[ZhuXing::JuMen] = SiHua::Lu;
                table[ZhuXing::TaiYang] = SiHua::Quan;
                break;
            case TianGan::Ren:
                table[ZhuXing::TianLiang] = SiHua::Lu;
                table[ZhuXing::ZiWei] = SiHua::Quan;
                table[ZhuXing::WuQu] = SiHua::Ji;
                break;
            case TianGan::Gui:
                table[ZhuXing::PoJun] = SiHua::Lu;
                table[ZhuXing::JuMen] = SiHua::Quan;
                table[ZhuXing::TaiYin] = SiHua::Ke;
                table[ZhuXing::TanLang] = SiHua::Ji;
                break;
        }
        
        return table;
    }

    // ============= 杂耀星定位算法 =============

    // 地支转索引辅助函数
    constexpr int di_zhi_to_index(DiZhi zhi) {
        // 寅0 卯1 辰2 巳3 午4 未5 申6 酉7 戌8 亥9 子10 丑11
        constexpr array<int, 12> mapping = {10, 11, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        return mapping[static_cast<int>(zhi)];
    }

    /**
     * @brief 安红鸾天喜诀
     * 
     * 口诀：
     * 卯上起子逆数之，数到当生太岁支，
     * 坐守此宫红鸾位，对宫天喜不差移。
     * 
     * 解释：从卯宫起子年逆数至出生年支为红鸾，对宫为天喜
     */
    pair<int, int> get_hong_luan_tian_xi_index(DiZhi year_zhi) {
        // 卯上起子逆数之，数到当生太岁支
        int zhi_idx = static_cast<int>(year_zhi);
        int hong_luan_index = fix_index(1 - zhi_idx); // 卯宫为1
        int tian_xi_index = fix_index(hong_luan_index + 6); // 对宫
        return {hong_luan_index, tian_xi_index};
    }

    /**
     * @brief 安三台八座诀
     * 
     * 口诀：
     * 由左辅之宫位起初一，顺行至生日安三台。
     * 由右弼之宫位起初一，逆行至生日安八座。
     */
    pair<int, int> get_san_tai_ba_zuo_index(int lunar_month, int lunar_day, DiZhi hour_zhi) {
        auto [zuo_idx, you_idx] = get_zuo_you_index(lunar_month);
        
        // 晚子时需要加一天
        int day_offset = (hour_zhi == DiZhi::Zi && static_cast<int>(hour_zhi) >= 12) ? 1 : 0;
        int day_index = (lunar_day + day_offset - 1) % 12;
        
        int san_tai_index = fix_index(zuo_idx + day_index);
        int ba_zuo_index = fix_index(you_idx - day_index);
        
        return {san_tai_index, ba_zuo_index};
    }

    /**
     * @brief 安恩光天贵诀
     * 
     * 口诀：
     * 由文昌之宫位起初一，顺行至生日再退一步起恩光。
     * 由文曲之宫位起初一，顺行至生日再退一步起天贵。
     */
    pair<int, int> get_en_guang_tian_gui_index(int lunar_month, int lunar_day, DiZhi hour_zhi) {
        auto [chang_idx, qu_idx] = get_chang_qu_index(hour_zhi);
        
        int day_offset = (hour_zhi == DiZhi::Zi && static_cast<int>(hour_zhi) >= 12) ? 1 : 0;
        int day_index = (lunar_day + day_offset - 1) % 12;
        
        int en_guang_index = fix_index((chang_idx + day_index) - 1);
        int tian_gui_index = fix_index((qu_idx + day_index) - 1);
        
        return {en_guang_index, tian_gui_index};
    }

    /**
     * @brief 安龙池凤阁诀
     * 
     * 口诀：
     * 龙池从辰宫起子，顺至本生年支安之。
     * 凤阁从戌宫起子，逆行至本生年支安之。
     */
    pair<int, int> get_long_chi_feng_ge_index(DiZhi year_zhi) {
        int zhi_idx = static_cast<int>(year_zhi);
        int long_chi_index = fix_index(2 + zhi_idx); // 辰宫为2
        int feng_ge_index = fix_index(8 - zhi_idx);  // 戌宫为8
        return {long_chi_index, feng_ge_index};
    }

    /**
     * @brief 安天才天寿诀
     * 
     * 口诀：
     * 天才由命宫起子，顺行至本生年支安之。
     * 天寿由身宫起子，顺行至本生年支安之。
     */
    pair<int, int> get_tian_cai_tian_shou_index(DiZhi year_zhi, int ming_index, int shen_index) {
        int zhi_idx = static_cast<int>(year_zhi);
        int tian_cai_index = fix_index(ming_index + zhi_idx);
        int tian_shou_index = fix_index(shen_index + zhi_idx);
        return {tian_cai_index, tian_shou_index};
    }

    /**
     * @brief 安台辅封诰诀
     * 
     * 台辅：午宫起子时，顺数至生时安之
     * 封诰：寅宫起子时，顺数至生时安之
     */
    pair<int, int> get_tai_fu_feng_gao_index(DiZhi hour_zhi) {
        int hour_idx = static_cast<int>(hour_zhi);
        int tai_fu_index = fix_index(4 + hour_idx);  // 午宫为4
        int feng_gao_index = fix_index(0 + hour_idx); // 寅宫为0
        return {tai_fu_index, feng_gao_index};
    }

    /**
     * @brief 安天官天福诀
     * 
     * 口诀：
     * 甲喜羊鸡乙龙猴，丙年蛇鼠一窝谋。
     * 丁虎擒猪戊玉兔，己鸡居然与虎俦。
     * 庚猪马辛鸡蛇走，壬犬马癸马蛇游。
     * 
     * 说明：前者为天官，后者为天福
     */
    pair<int, int> get_tian_guan_tian_fu_index(TianGan year_gan) {
        // 天官天福口诀
        constexpr array<pair<int, int>, 10> mapping = {
            pair{5, 7},   // 甲：未酉
            pair{2, 6},   // 乙：辰申
            pair{3, 10},  // 丙：巳子
            pair{0, 9},   // 丁：寅亥
            pair{1, 1},   // 戊：卯卯
            pair{7, 0},   // 己：酉寅
            pair{9, 4},   // 庚：亥午
            pair{7, 3},   // 辛：酉巳
            pair{8, 4},   // 壬：戌午
            pair{4, 3}    // 癸：午巳
        };
        return mapping[static_cast<int>(year_gan)];
    }

    /**
     * @brief 安天厨诀
     * 
     * 口诀：
     * 甲丁食蛇口，乙戊辛马方。
     * 丙从鼠口得，己食于猴房。
     * 庚食虎头上，壬鸡癸猪堂。
     */
    int get_tian_chu_index(TianGan year_gan) {
        // 天厨口诀：甲丁食蛇口，乙戊辛马方...
        constexpr array<int, 10> mapping = {3, 4, 10, 3, 4, 6, 0, 4, 7, 9};
        return mapping[static_cast<int>(year_gan)];
    }

    /**
     * @brief 安华盖诀
     * 
     * 口诀：
     * 寅午戌年在戌，申子辰年在辰，
     * 巳酉丑年在丑，亥卯未年在未。
     */
    int get_hua_gai_index(DiZhi year_zhi) {
        // 华盖口诀
        if (year_zhi == DiZhi::Yin || year_zhi == DiZhi::Wu || year_zhi == DiZhi::Xu) {
            return 8; // 戌
        } else if (year_zhi == DiZhi::Shen || year_zhi == DiZhi::Zi || year_zhi == DiZhi::Chen) {
            return 2; // 辰
        } else if (year_zhi == DiZhi::Si || year_zhi == DiZhi::You || year_zhi == DiZhi::Chou) {
            return 11; // 丑
        } else { // 亥卯未
            return 5; // 未
        }
    }

    /**
     * @brief 安咸池诀
     * 
     * 口诀：
     * 寅午戌年在卯，申子辰年在酉，
     * 巳酉丑年在午，亥卯未年在子。
     */
    int get_xian_chi_index(DiZhi year_zhi) {
        // 咸池口诀
        if (year_zhi == DiZhi::Yin || year_zhi == DiZhi::Wu || year_zhi == DiZhi::Xu) {
            return 1; // 卯
        } else if (year_zhi == DiZhi::Shen || year_zhi == DiZhi::Zi || year_zhi == DiZhi::Chen) {
            return 7; // 酉
        } else if (year_zhi == DiZhi::Si || year_zhi == DiZhi::You || year_zhi == DiZhi::Chou) {
            return 4; // 午
        } else { // 亥卯未
            return 10; // 子
        }
    }

    /**
     * @brief 安孤辰寡宿诀
     * 
     * 口诀：
     * 寅卯辰年安巳丑，巳午未年安申辰，
     * 申酉戌年安亥未，亥子丑年安寅戌。
     * 
     * 说明：前者为孤辰，后者为寡宿
     */
    pair<int, int> get_gu_chen_gua_su_index(DiZhi year_zhi) {
        // 孤辰寡宿口诀
        int gu_chen, gua_su;
        if (year_zhi == DiZhi::Yin || year_zhi == DiZhi::Mao || year_zhi == DiZhi::Chen) {
            gu_chen = 3;  // 巳
            gua_su = 11;  // 丑
        } else if (year_zhi == DiZhi::Si || year_zhi == DiZhi::Wu || year_zhi == DiZhi::Wei) {
            gu_chen = 6;  // 申
            gua_su = 2;   // 辰
        } else if (year_zhi == DiZhi::Shen || year_zhi == DiZhi::You || year_zhi == DiZhi::Xu) {
            gu_chen = 9;  // 亥
            gua_su = 5;   // 未
        } else { // 亥子丑
            gu_chen = 0;  // 寅
            gua_su = 8;   // 戌
        }
        return {gu_chen, gua_su};
    }

    /**
     * @brief 安蜚廉诀
     * 
     * 口诀：
     * 子丑寅年在申酉戌，卯辰巳年在巳午未，
     * 午未申年在寅卯辰，酉戌亥年在亥子丑。
     */
    int get_fei_lian_index(DiZhi year_zhi) {
        // 蜚廉按年支
        constexpr array<int, 12> mapping = {6, 7, 8, 3, 4, 5, 0, 1, 2, 9, 10, 11};
        return mapping[static_cast<int>(year_zhi)];
    }

    /**
     * @brief 安破碎诀
     * 
     * 口诀：
     * 子午卯酉年安巳宫，寅申巳亥年安酉宫，
     * 辰戌丑未年安丑宫。
     */
    int get_po_sui_index(DiZhi year_zhi) {
        // 破碎：子午卯酉年在巳，寅申巳亥年在酉，辰戌丑未年在丑
        int zhi_idx = static_cast<int>(year_zhi);
        constexpr array<int, 12> mapping = {3, 11, 7, 7, 11, 11, 3, 11, 7, 7, 3, 11};
        return mapping[zhi_idx];
    }

    /**
     * @brief 安天刑诀
     * 
     * 口诀：
     * 天刑从酉起正月，顺至生月便安之。
     */
    int get_tian_xing_index(int lunar_month) {
        // 天刑从酉起正月，顺至生月
        return fix_index(7 + (lunar_month - 1)); // 酉宫为7
    }

    /**
     * @brief 安天姚诀
     * 
     * 口诀：
     * 天姚丑宫起正月，顺到生月即停留。
     */
    int get_tian_yao_index(int lunar_month) {
        // 天姚从丑起正月，顺至生月
        return fix_index(11 + (lunar_month - 1)); // 丑宫为11
    }

    /**
     * @brief 安解神诀（月解）
     * 
     * 口诀：
     * 正二在申三四在戌，五六在子七八在寅，
     * 九十月坐于辰宫，十一十二在午宫。
     */
    int get_jie_shen_index(int lunar_month) {
        // 解神（月解）
        constexpr array<int, 6> positions = {6, 6, 8, 8, 10, 10}; // 申申戌戌子子
        return positions[(lunar_month - 1) / 2];
    }

    /**
     * @brief 安天巫诀
     * 
     * 口诀：
     * 正五九月在巳，二六十月在申，
     * 三七十一在寅，四八十二在亥。
     */
    int get_tian_wu_index(int lunar_month) {
        // 天巫：正五九在巳，二六十在申，三七十一在寅，四八十二在亥
        constexpr array<int, 4> positions = {3, 6, 0, 9}; // 巳申寅亥
        return positions[(lunar_month - 1) % 4];
    }

    /**
     * @brief 安天月诀
     * 
     * 口诀：
     * 一犬二蛇三在龙，四虎五羊六兔宫。
     * 七猪八羊九在虎，十马冬犬腊寅中。
     */
    int get_tian_yue_index(int lunar_month) {
        // 天月：一犬二蛇三在龙...
        constexpr array<int, 12> mapping = {8, 3, 2, 0, 5, 1, 9, 5, 0, 4, 8, 0};
        return mapping[lunar_month - 1];
    }

    /**
     * @brief 安阴煞诀
     * 
     * 口诀：
     * 正七月在寅，二八月在子，三九月在戌，
     * 四十月在申，五十一在午，六十二在辰。
     */
    int get_yin_sha_index(int lunar_month) {
        // 阴煞：正七在寅，二八在子，三九在戌...
        constexpr array<int, 6> positions = {0, 10, 8, 6, 4, 2};
        return positions[(lunar_month - 1) % 6];
    }

    /**
     * @brief 安天德月德诀
     * 
     * 天德：从酉宫起子年，顺数至生年
     * 月德：从巳宫起子年，顺数至生年
     */
    pair<int, int> get_tian_de_yue_de_index(DiZhi year_zhi) {
        int zhi_idx = static_cast<int>(year_zhi);
        int tian_de_index = fix_index(7 + zhi_idx);  // 酉宫起
        int yue_de_index = fix_index(3 + zhi_idx);   // 巳宫起
        return {tian_de_index, yue_de_index};
    }

    /**
     * @brief 安天空诀
     * 
     * 口诀：
     * 生年支顺数的前一位就是。
     */
    int get_tian_kong_index(DiZhi year_zhi) {
        // 天空：生年支顺数前一位
        return fix_index(static_cast<int>(year_zhi) + 1);
    }

    /**
     * @brief 安旬空诀
     * 
     * 算法：
     * 从生年地支顺数，加上（9-年干序号）再加1
     * 若阴阳属性不同，再加1
     */
    int get_xun_kong_index(TianGan year_gan, DiZhi year_zhi) {
        // 旬空计算
        int gan_idx = static_cast<int>(year_gan);
        int zhi_idx = static_cast<int>(year_zhi);
        int xun_kong_idx = fix_index(zhi_idx + (9 - gan_idx) + 1);
        
        // 阴阳属性检查
        if ((zhi_idx % 2) != (xun_kong_idx % 2)) {
            xun_kong_idx = fix_index(xun_kong_idx + 1);
        }
        
        return xun_kong_idx;
    }

    /**
     * @brief 安截路空亡诀
     * 
     * 口诀：
     * 甲己之年申酉，乙庚之年午未，
     * 丙辛之年辰巳，丁壬之年寅卯，
     * 戊癸之年子丑。
     * 
     * 说明：前者为截路，后者为空亡
     */
    pair<int, int> get_jie_lu_kong_wang_index(TianGan year_gan) {
        // 截路空亡
        constexpr array<pair<int, int>, 5> mapping = {
            pair{6, 7},   // 甲己：申酉
            pair{4, 5},   // 乙庚：午未
            pair{2, 3},   // 丙辛：辰巳
            pair{0, 1},   // 丁壬：寅卯
            pair{10, 11}  // 戊癸：子丑
        };
        return mapping[static_cast<int>(year_gan) % 5];
    }

    /**
     * @brief 安天哭天虚诀
     * 
     * 口诀：
     * 天哭天虚起午宫，午宫起子两分踪，
     * 哭逆行兮虚顺转，数到生年便停留。
     * 
     * 说明：天哭从午宫起子年逆数，天虚从午宫起子年顺数
     */
    pair<int, int> get_tian_ku_tian_xu_index(DiZhi year_zhi) {
        int zhi_idx = static_cast<int>(year_zhi);
        int tian_ku_index = fix_index(4 - zhi_idx);  // 午宫起逆数
        int tian_xu_index = fix_index(4 + zhi_idx);  // 午宫起顺数
        return {tian_ku_index, tian_xu_index};
    }

    /**
     * @brief 安天使天伤诀
     * 
     * 口诀：
     * 天伤奴仆、天使疾厄、夹迁移宫最易寻得。
     * 凡阳男阴女，皆依此诀，但若为阴男阳女，
     * 则改为天伤居疾厄、天使居奴仆。
     * 
     * 说明：夹迁移宫，阳男阴女天伤在迁移后一位、天使在迁移前一位
     */
    pair<int, int> get_tian_shi_tian_shang_index(int ming_index, bool is_male, DiZhi year_zhi) {
        // 天使天伤：夹迁移宫
        int qian_yi_index = fix_index(ming_index + 6); // 迁移宫在命宫对宫
        
        // 阳男阴女：天伤在奴仆(迁移后一位)，天使在疾厄(迁移前一位)
        // 阴男阳女：相反
        int zhi_idx = static_cast<int>(year_zhi);
        bool yang_zhi = (zhi_idx % 2 == 0);
        bool same_yin_yang = (is_male == yang_zhi);
        
        int tian_shi_index, tian_shang_index;
        if (same_yin_yang) {
            tian_shang_index = fix_index(qian_yi_index + 1);
            tian_shi_index = fix_index(qian_yi_index - 1);
        } else {
            tian_shi_index = fix_index(qian_yi_index + 1);
            tian_shang_index = fix_index(qian_yi_index - 1);
        }
        
        return {tian_shi_index, tian_shang_index};
    }

    /**
     * @brief 安年解诀
     * 
     * 口诀：
     * 解神从戌上起子，逆数至当生年太岁上是也。
     */
    int get_nian_jie_index(DiZhi year_zhi) {
        // 年解：从戌起子，逆数至当生年太岁
        constexpr array<int, 12> mapping = {8, 7, 6, 5, 4, 3, 2, 1, 0, 11, 10, 9};
        return mapping[static_cast<int>(year_zhi)];
    }

    // ============= 长生12神和博士12神 =============

    /**
     * @brief 获取长生12神起始位置
     * 
     * 口诀：
     * 水二局长生在申，木三局长生在亥，
     * 金四局长生在巳，土五局长生在申，
     * 火六局长生在寅。
     */
    int get_chang_sheng_12_start_index(WuXingJu wu_xing_ju) {
        switch (wu_xing_ju) {
            case WuXingJu::ShuiErJu:
                return 6; // 申
            case WuXingJu::MuSanJu:
                return 9; // 亥
            case WuXingJu::JinSiJu:
                return 3; // 巳
            case WuXingJu::TuWuJu:
                return 6; // 申
            case WuXingJu::HuoLiuJu:
                return 0; // 寅
            default:
                return 0;
        }
    }

    /**
     * @brief 安长生12神
     * 
     * 口诀：
     * 阳男阴女顺行，阴男阳女逆行，
     * 安长生、沐浴、冠带、临官、帝旺、衰、
     * 病、死、墓、绝、胎、养。
     */
    array<ChangSheng12, 12> arrange_chang_sheng_12(
        WuXingJu wu_xing_ju,
        bool is_male,
        DiZhi year_zhi
    ) {
        array<ChangSheng12, 12> result{};
        
        constexpr array<ChangSheng12, 12> shun_xu = {
            ChangSheng12::ChangSheng, ChangSheng12::MuYu, ChangSheng12::GuanDai,
            ChangSheng12::LinGuan, ChangSheng12::DiWang, ChangSheng12::Shuai,
            ChangSheng12::Bing, ChangSheng12::Si, ChangSheng12::Mu,
            ChangSheng12::Jue, ChangSheng12::Tai, ChangSheng12::Yang
        };
        
        int start_idx = get_chang_sheng_12_start_index(wu_xing_ju);
        
        // 判断阴阳：阳男阴女顺行，阴男阳女逆行
        int zhi_idx = static_cast<int>(year_zhi);
        bool yang_zhi = (zhi_idx % 2 == 0);
        bool shun_xing = (is_male == yang_zhi);
        
        for (int i = 0; i < 12; ++i) {
            int idx = shun_xing 
                ? fix_index(start_idx + i) 
                : fix_index(start_idx - i);
            result[idx] = shun_xu[i];
        }
        
        return result;
    }

    /**
     * @brief 安博士12神
     * 
     * 口诀：
     * 从禄存起，阳男阴女顺行，阴男阳女逆行。
     * 安博士、力士、青龙、小耗、将军、奏书、
     * 飞廉、喜神、病符、大耗、伏兵、官府。
     */
    array<BoShi12, 12> arrange_bo_shi_12(
        TianGan year_gan,
        DiZhi year_zhi,
        bool is_male
    ) {
        array<BoShi12, 12> result{};
        
        constexpr array<BoShi12, 12> shun_xu = {
            BoShi12::BoShi, BoShi12::LiShi, BoShi12::QingLong,
            BoShi12::XiaoHao2, BoShi12::JiangJun, BoShi12::ZouShu,
            BoShi12::FeiLian2, BoShi12::XiShen2, BoShi12::BingFu2,
            BoShi12::DaHao2, BoShi12::FuBing, BoShi12::GuanFu2
        };
        
        // 从禄存起
        int lu_cun_idx = get_lu_cun_index(year_gan);
        
        // 阳男阴女顺行，阴男阳女逆行
        int zhi_idx = static_cast<int>(year_zhi);
        bool yang_zhi = (zhi_idx % 2 == 0);
        bool shun_xing = (is_male == yang_zhi);
        
        for (int i = 0; i < 12; ++i) {
            int idx = shun_xing 
                ? fix_index(lu_cun_idx + i) 
                : fix_index(lu_cun_idx - i);
            result[idx] = shun_xu[i];
        }
        
        return result;
    }

    // ============= 流年诸星 =============

    /**
     * @brief 获取将前12神起始位置
     * 
     * 口诀：
     * 寅午戌年将星午，申子辰年子将星，
     * 巳酉丑将酉上驻，亥卯未将卯上停。
     */
    int get_jiang_qian_12_start_index(DiZhi year_zhi) {
        // 寅午戌年将星午，申子辰年子将星，巳酉丑将酉上驻，亥卯未将卯上停
        if (year_zhi == DiZhi::Yin || year_zhi == DiZhi::Wu || year_zhi == DiZhi::Xu) {
            return 4; // 午
        } else if (year_zhi == DiZhi::Shen || year_zhi == DiZhi::Zi || year_zhi == DiZhi::Chen) {
            return 10; // 子
        } else if (year_zhi == DiZhi::Si || year_zhi == DiZhi::You || year_zhi == DiZhi::Chou) {
            return 7; // 酉
        } else { // 亥卯未
            return 1; // 卯
        }
    }

    /**
     * @brief 安流年岁前12神
     * 
     * 口诀：
     * 流年地支起岁建，岁前首先是晦气，
     * 丧门贯索及官符，小耗大耗龙德继，
     * 白虎天德连吊客，病符居后须当记。
     */
    array<SuiQian12, 12> arrange_sui_qian_12(DiZhi year_zhi) {
        array<SuiQian12, 12> result{};
        
        constexpr array<SuiQian12, 12> shun_xu = {
            SuiQian12::SuiJian, SuiQian12::HuiQi, SuiQian12::SangMen,
            SuiQian12::GuanSuo, SuiQian12::GuanFu, SuiQian12::XiaoHao,
            SuiQian12::DaHao, SuiQian12::LongDe, SuiQian12::BaiHu,
            SuiQian12::TianDe2, SuiQian12::DiaoKe, SuiQian12::BingFu
        };
        
        int zhi_idx = static_cast<int>(year_zhi);
        int start_idx = di_zhi_to_index(year_zhi);
        
        for (int i = 0; i < 12; ++i) {
            int idx = fix_index(start_idx + i);
            result[idx] = shun_xu[i];
        }
        
        return result;
    }

    /**
     * @brief 安流年将前12神
     * 
     * 口诀：
     * 攀鞍岁驿并息神，华盖劫煞灾煞轻，
     * 天煞指背咸池续，月煞亡神次第行。
     */
    array<JiangQian12, 12> arrange_jiang_qian_12(DiZhi year_zhi) {
        array<JiangQian12, 12> result{};
        
        constexpr array<JiangQian12, 12> shun_xu = {
            JiangQian12::JiangXing, JiangQian12::PanAn, JiangQian12::SuiYi,
            JiangQian12::XiShen, JiangQian12::HuaGai2, JiangQian12::JieSha,
            JiangQian12::ZaiSha, JiangQian12::TianSha, JiangQian12::ZhiBei,
            JiangQian12::XianChi2, JiangQian12::YueSha, JiangQian12::WangShen
        };
        
        int start_idx = get_jiang_qian_12_start_index(year_zhi);
        
        for (int i = 0; i < 12; ++i) {
            int idx = fix_index(start_idx + i);
            result[idx] = shun_xu[i];
        }
        
        return result;
    }

} // namespace ZhouYi::ZiWei

