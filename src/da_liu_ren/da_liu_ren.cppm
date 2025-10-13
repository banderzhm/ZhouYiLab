// C++23 Module - 大六壬排盘系统
export module ZhouYi.DaLiuRen;

// 导入第三方库模块
import fmt;
import nlohmann.json;

// 导入自定义通用模块
import ZhouYi.BaZiBase;      // 八字基础数据结构（Pillar, BaZi）
import ZhouYi.GanZhi;        // 干支系统（包含地支关系）
import ZhouYi.WuXingUtils;   // 五行工具
import ZhouYi.tyme;          // 时间模块
import ZhouYi.DaLiuRen.ShenSha;  // 神煞系统

// 导入标准库模块（最后）
import std;

/**
 * @brief 大六壬命名空间
 * 
 * 大六壬是中国古代占卜术之一，与奇门遁甲、太乙神数并称三式。
 * 主要用于预测事物的发展趋势和吉凶。
 */
export namespace ZhouYi::DaLiuRen {

// 使用通用命名空间
using namespace ZhouYi::BaZiBase;
using namespace ZhouYi::GanZhi;
using namespace ZhouYi::WuXingUtils;
namespace Mapper = ZhouYi::GanZhi::Mapper;

// ==================== 月将对照表 ====================

/**
 * @brief 月将枚举
 * 
 * 大六壬中的十二月将，对应十二地支
 */
enum class YueJiang {
    DengMing = 0,   // 登明（亥）- 正月
    HeKui = 1,      // 河魁（戌）- 二月
    ChuanSong = 2,  // 传送（酉）- 三月
    XiaoJi = 3,     // 小吉（申）- 四月
    ShengGuang = 4, // 胜光（未）- 五月
    TaiYi = 5,      // 太乙（午）- 六月
    TianGang = 6,   // 天罡（巳）- 七月
    TaiChong = 7,   // 太冲（辰）- 八月
    GongCao = 8,    // 功曹（卯）- 九月
    DaJi = 9,       // 大吉（寅）- 十月
    ShenHou = 10,   // 神后（丑）- 十一月
    YinYang = 11    // 阴阳（子）- 十二月
};

/**
 * @brief 根据农历月份获取月将对应的地支
 * 
 * @param lunar_month 农历月份（1-12）
 * @return DiZhi 月将对应的地支
 */
inline DiZhi get_yue_jiang(int lunar_month) {
    constexpr std::array<DiZhi, 12> yue_jiang_map = {
        DiZhi::Hai,   // 正月 - 登明（亥）
        DiZhi::Xu,    // 二月 - 河魁（戌）
        DiZhi::You,   // 三月 - 传送（酉）
        DiZhi::Shen,  // 四月 - 小吉（申）
        DiZhi::Wei,   // 五月 - 胜光（未）
        DiZhi::Si,    // 六月 - 太乙（午）- 注意：此处为巳，与原代码保持一致
        DiZhi::Wu,    // 七月 - 天罡（巳）- 注意：此处为午，与原代码保持一致
        DiZhi::Chen,  // 八月 - 太冲（辰）
        DiZhi::Mao,   // 九月 - 功曹（卯）
        DiZhi::Yin,   // 十月 - 大吉（寅）
        DiZhi::Chou,  // 十一月 - 神后（丑）
        DiZhi::Zi     // 十二月 - 阴阳（子）
    };
    
    int index = (lunar_month - 1 + 12) % 12;
    return yue_jiang_map[index];
}

// ==================== 干支课类 ====================

/**
 * @brief 干支课（包含天干或地支与地支的组合）
 * 
 * 大六壬中的"课"分为两种：
 * 1. 干支课：天干 + 地支（上神）
 * 2. 支辰课：地支（下神）+ 地支（上神）
 */
struct GanZhiKe {
    union {
        TianGan gan;          // 天干（用于干支课）
        DiZhi lower_zhi;      // 下神地支（用于支辰课）
    };
    DiZhi upper_zhi;          // 上神地支
    bool is_gan_zhi;          // true: 干支课, false: 支辰课
    
    // 构造函数：干支课
    GanZhiKe(TianGan g, DiZhi upper) 
        : gan(g), upper_zhi(upper), is_gan_zhi(true) {}
    
    // 构造函数：支辰课
    GanZhiKe(DiZhi lower, DiZhi upper) 
        : lower_zhi(lower), upper_zhi(upper), is_gan_zhi(false) {}
    
    /**
     * @brief 获取五行
     */
    WuXing get_wu_xing() const {
        if (is_gan_zhi) {
            return ZhouYi::GanZhi::get_wu_xing(gan);
        } else {
            return ZhouYi::GanZhi::get_wu_xing(lower_zhi);
        }
    }
    
    /**
     * @brief 判断是否为阳
     */
    bool is_yang() const {
        if (is_gan_zhi) {
            return ZhouYi::GanZhi::get_yin_yang(gan) == YinYang::Yang;
        } else {
            return ZhouYi::GanZhi::get_yin_yang(lower_zhi) == YinYang::Yang;
        }
    }
    
    /**
     * @brief 判断是否相等
     */
    bool operator==(const GanZhiKe& other) const {
        if (is_gan_zhi != other.is_gan_zhi) return false;
        if (upper_zhi != other.upper_zhi) return false;
        
        if (is_gan_zhi) {
            return gan == other.gan;
        } else {
            return lower_zhi == other.lower_zhi;
        }
    }
    
    /**
     * @brief 转换为字符串
     */
    std::string to_string() const {
        if (is_gan_zhi) {
            return std::format("{}{}", 
                Mapper::to_zh(gan), 
                Mapper::to_zh(upper_zhi));
        } else {
            return std::format("{}{}", 
                Mapper::to_zh(lower_zhi), 
                Mapper::to_zh(upper_zhi));
        }
    }
};

// ==================== 四课类 ====================

/**
 * @brief 四课
 * 
 * 大六壬的四课：
 * - 第一课（干上神）：日干寄宫的上神
 * - 第二课（干上神的上神）
 * - 第三课（日支的上神）
 * - 第四课（日支上神的上神）
 */
struct SiKe {
    GanZhiKe first;       // 第一课（干上神）
    GanZhiKe second;      // 第二课
    GanZhiKe third;       // 第三课（支上神）
    GanZhiKe fourth;      // 第四课
    DiZhi gan_yang_shen;  // 干阳神（第一课的上神）
    DiZhi zhi_yang_shen;  // 支阳神（第三课的上神）
    
    SiKe(const GanZhiKe& f, const GanZhiKe& s, 
         const GanZhiKe& t, const GanZhiKe& fo,
         DiZhi gys, DiZhi zys)
        : first(f), second(s), third(t), fourth(fo),
          gan_yang_shen(gys), zhi_yang_shen(zys) {}
    
    // Getter 方法（用于卦体判定）
    TianGan get_gan() const { return first.is_gan_zhi ? first.gan : TianGan::Jia; }
    DiZhi get_zhi() const { return third.lower_zhi; }
    DiZhi get_gan_yang_shen() const { return gan_yang_shen; }
    DiZhi get_gan_yin_shen() const { return second.upper_zhi; }
    DiZhi get_zhi_yang_shen() const { return zhi_yang_shen; }
    DiZhi get_zhi_yin_shen() const { return fourth.upper_zhi; }
};

// ==================== 天地盘类 ====================

/**
 * @brief 天地盘
 * 
 * 包含：
 * - 地盘：固定的十二地支（子丑寅卯辰巳午未申酉戌亥）
 * - 天盘：从月将开始顺布的地支
 * - 十二神将：从贵人开始顺或逆布
 */
class TianDiPan {
private:
    std::array<DiZhi, 12> di_pan_;        // 地盘（固定）
    std::array<DiZhi, 12> tian_pan_;      // 天盘
    std::array<DiZhi, 12> shen_jiang_;    // 十二神将位置
    
public:
    /**
     * @brief 构造函数
     * 
     * @param yue_jiang 月将地支
     * @param hour_zhi 时辰地支
     * @param gui_ren 贵人地支
     * @param is_clockwise 神将是否顺布
     */
    TianDiPan(DiZhi yue_jiang, DiZhi hour_zhi, DiZhi gui_ren, bool is_clockwise) {
        // 初始化地盘（固定）
        for (int i = 0; i < 12; ++i) {
            di_pan_[i] = static_cast<DiZhi>(i);
        }
        
        // 初始化天盘（月将落在时辰位置，然后顺布）
        int hour_offset = static_cast<int>(hour_zhi);
        for (int i = 0; i < 12; ++i) {
            int tian_index = (hour_offset + i) % 12;  // 从时辰开始的位置
            int yue_jiang_offset = (static_cast<int>(yue_jiang) + i) % 12;  // 从月将开始递增
            tian_pan_[tian_index] = static_cast<DiZhi>(yue_jiang_offset);
        }
         
        // 初始化神将（从贵人开始顺布或逆布）
        int gui_ren_idx = static_cast<int>(gui_ren);
        int step = is_clockwise ? -1 : 1;  // 修正方向：顺布用-1，逆布用1
        for (int i = 0; i < 12; ++i) {
            int pos = (gui_ren_idx + i * step + 12) % 12;
            shen_jiang_[i] = static_cast<DiZhi>(pos);
        }
    }
    
    /**
     * @brief 获取地支对应的天盘地支
     */
    DiZhi operator[](DiZhi zhi) const {
        int index = static_cast<int>(zhi);
        return tian_pan_[index];
    }
    
    /**
     * @brief 获取地盘
     */
    const std::array<DiZhi, 12>& get_di_pan() const { return di_pan_; }
    
    /**
     * @brief 获取天盘
     */
    const std::array<DiZhi, 12>& get_tian_pan() const { return tian_pan_; }
    
    /**
     * @brief 获取神将
     */
    const std::array<DiZhi, 12>& get_shen_jiang() const { return shen_jiang_; }
    
    /**
     * @brief 获取地支对应的神将
     */
    DiZhi get_shen_jiang_at(DiZhi zhi) const {
        int index = static_cast<int>(zhi);
        return shen_jiang_[index];
    }
    
    /**
     * @brief 反向查找：给定天盘地支，找到它所临的地盘地支
     * 
     * @param tian_zhi 天盘地支
     * @return 对应的地盘地支
     */
    DiZhi lin(DiZhi tian_zhi) const {
        for (int i = 0; i < 12; ++i) {
            if (tian_pan_[i] == tian_zhi) {
                return di_pan_[i];
            }
        }
        // 理论上不会到达这里
        return DiZhi::Zi;
    }
};

// ==================== 三传类 ====================

/**
 * @brief 三传
 * 
 * 大六壬的核心：初传、中传、末传
 */
class SanChuan {
private:
    const TianDiPan& tian_di_pan_;
    const SiKe& si_ke_;
    DiZhi chu_chuan_;      // 初传
    DiZhi zhong_chuan_;    // 中传
    DiZhi mo_chuan_;       // 末传
    std::vector<std::string> ke_shi_;  // 课式名称
    
    // ==================== 辅助函数 ====================
    
    /**
     * @brief 去除重复课（相同天干的课）
     */
    std::vector<GanZhiKe> remove_duplicate_lessons(const std::vector<GanZhiKe>& lessons) const;
    
    /**
     * @brief 查找有贼克的课（地支五行克天干五行）
     */
    std::vector<GanZhiKe> have_conquerors() const;
    
    /**
     * @brief 查找有克的课（天干五行克地支五行）
     */
    std::vector<GanZhiKe> have_overcomes() const;
    
    /**
     * @brief 贼克法取三传
     */
    std::array<DiZhi, 3> zei_ke();
    
    /**
     * @brief 比用法取三传
     */
    std::array<DiZhi, 3> bi_yong(const std::vector<GanZhiKe>& lessons);
    
    /**
     * @brief 涉害法取三传
     */
    std::array<DiZhi, 3> she_hai(const std::vector<GanZhiKe>& lessons);
    
    /**
     * @brief 遥克法取三传
     */
    std::array<DiZhi, 3> yao_ke();
    
    /**
     * @brief 昴星法取三传
     */
    std::array<DiZhi, 3> ang_xing();
    
    /**
     * @brief 别责法取三传
     */
    std::array<DiZhi, 3> bie_ze();
    
    /**
     * @brief 八专法取三传
     */
    std::array<DiZhi, 3> ba_zhuan();
    
    /**
     * @brief 伏吟法取三传
     */
    std::array<DiZhi, 3> fu_yin();
    
    /**
     * @brief 返吟法取三传
     */
    std::array<DiZhi, 3> fan_yin();
    
    /**
     * @brief 判断是否为孟神（寅巳申亥）
     */
    bool is_meng(DiZhi zhi) const;
    
    /**
     * @brief 判断是否为仲神（子卯午酉）
     */
    bool is_zhong(DiZhi zhi) const;
    
    /**
     * @brief 判断是否为八专日
     */
    bool is_ba_zhuan_day(const GanZhiKe& ke) const;
    
    /**
     * @brief 判断是否为伏吟课
     */
    bool is_fu_yin_lesson() const;
    
    /**
     * @brief 判断是否为返吟课
     */
    bool is_fan_yin_lesson() const;
    
public:
    /**
     * @brief 构造函数
     * 
     * 根据四课和天地盘计算三传
     */
    SanChuan(const TianDiPan& tdp, const SiKe& sk);
    
    /**
     * @brief 获取初传
     */
    DiZhi get_chu_chuan() const { return chu_chuan_; }
    
    /**
     * @brief 获取中传
     */
    DiZhi get_zhong_chuan() const { return zhong_chuan_; }
    
    /**
     * @brief 获取末传
     */
    DiZhi get_mo_chuan() const { return mo_chuan_; }
    
    /**
     * @brief 获取课式
     */
    const std::vector<std::string>& get_ke_shi() const { return ke_shi_; }
    
    /**
     * @brief 获取三传的遁干
     * @param day_gan 日干
     * @param day_zhi 日支
     * @return 三个天干，对应初传、中传、末传。空亡时返回空optional
     */
    std::array<std::optional<TianGan>, 3> get_dun_gan(TianGan day_gan, DiZhi day_zhi) const;
    
    /**
     * @brief 获取三传的六亲关系
     * @param day_gan 日干
     * @return 三个六亲名称，对应初传、中传、末传
     */
    std::array<std::string_view, 3> get_liu_qin(TianGan day_gan) const;
};

// ==================== 大六壬排盘结果 ====================

/**
 * @brief 大六壬排盘结果
 */
struct DaLiuRenResult {
    BaZi ba_zi;                            // 八字信息
    DiZhi yue_jiang;                       // 月将
    DiZhi gui_ren;                         // 贵人
    bool is_day;                           // 是否白天
    TianDiPan tian_di_pan;                 // 天地盘
    SiKe si_ke;                            // 四课
    SanChuan san_chuan;                    // 三传
    ShenSha::ShenShaResult shen_sha;       // 神煞
    std::vector<std::string> gua_ti;       // 卦体
    
    DaLiuRenResult(const BaZi& bz, DiZhi yj, DiZhi gr, bool id,
                   const TianDiPan& tdp, const SiKe& sk, const SanChuan& sc,
                   const ShenSha::ShenShaResult& ss, const std::vector<std::string>& gt)
        : ba_zi(bz), yue_jiang(yj), gui_ren(gr), is_day(id),
          tian_di_pan(tdp), si_ke(sk), san_chuan(sc), shen_sha(ss), gua_ti(gt) {}
    
    /**
     * @brief 转换为 JSON
     */
    nlohmann::json to_json() const;
    
    /**
     * @brief 格式化输出
     */
    std::string to_string() const;
};

// ==================== 大六壬排盘引擎 ====================

/**
 * @brief 大六壬排盘引擎
 * 
 * 提供大六壬的排盘功能
 */
class DaLiuRenEngine {
public:
    /**
     * @brief 从公历日期时间排盘
     * 
     * @param year 公历年
     * @param month 公历月
     * @param day 公历日
     * @param hour 公历时
     * @return DaLiuRenResult 排盘结果
     */
    static DaLiuRenResult pai_pan(int year, int month, int day, int hour);
    
    /**
     * @brief 从农历日期时间排盘
     */
    static DaLiuRenResult pai_pan_lunar(int year, int month, int day, int hour);
    
    /**
     * @brief 从八字排盘
     */
    static DaLiuRenResult pai_pan_from_bazi(const BaZi& ba_zi, int lunar_month, int hour);
};

} // namespace ZhouYi::DaLiuRen

