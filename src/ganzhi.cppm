// 干支系统模块 - 天干地支核心功能
// 整合自旧代码，提供完整的天干地支功能

export module ZhouYi.GanZhi;

// 导入反射库
import magic_enum;

// 导入标准库（最后）
import std;

/**
 * @brief 干支系统命名空间
 * 
 * 提供天干地支的核心定义和相关功能
 */
export namespace ZhouYi::GanZhi {

// ==================== 枚举定义 ====================

/**
 * @brief 天干枚举
 */
enum class TianGan {
    Jia = 0,   // 甲
    Yi,        // 乙
    Bing,      // 丙
    Ding,      // 丁
    Wu,        // 戊
    Ji,        // 己
    Geng,      // 庚
    Xin,       // 辛
    Ren,       // 壬
    Gui        // 癸
};

/**
 * @brief 地支枚举
 */
enum class DiZhi {
    Zi = 0,    // 子
    Chou,      // 丑
    Yin,       // 寅
    Mao,       // 卯
    Chen,      // 辰
    Si,        // 巳
    Wu,        // 午
    Wei,       // 未
    Shen,      // 申
    You,       // 酉
    Xu,        // 戌
    Hai        // 亥
};

/**
 * @brief 五行枚举
 */
enum class WuXing {
    Mu = 1,    // 木
    Huo = 2,   // 火
    Tu = 3,    // 土
    Jin = 4,   // 金
    Shui = 5   // 水
};

/**
 * @brief 阴阳枚举
 */
enum class YinYang {
    Yin = 0,   // 阴
    Yang = 1   // 阳
};

// ==================== 运算符重载 ====================

/**
 * @brief 天干加法运算
 */
constexpr TianGan operator+(TianGan gan, int num) {
    int value = static_cast<int>(gan) + num;
    return static_cast<TianGan>((value % 10 + 10) % 10);
}

/**
 * @brief 地支加法运算
 */
constexpr DiZhi operator+(DiZhi zhi, int num) {
    int value = static_cast<int>(zhi) + num;
    return static_cast<DiZhi>((value % 12 + 12) % 12);
}

/**
 * @brief 地支减法运算（获取相距数）
 */
constexpr int operator-(DiZhi left, DiZhi right) {
    return (static_cast<int>(left) - static_cast<int>(right) + 12) % 12;
}

/**
 * @brief 地支前置自增
 */
constexpr DiZhi& operator++(DiZhi& zhi) {
    zhi = static_cast<DiZhi>((static_cast<int>(zhi) + 1) % 12);
    return zhi;
}

/**
 * @brief 地支前置自减
 */
constexpr DiZhi& operator--(DiZhi& zhi) {
    zhi = static_cast<DiZhi>((static_cast<int>(zhi) - 1 + 12) % 12);
    return zhi;
}

// ==================== 中文名称映射 ====================

namespace Mapper {
    // 天干中文名称
    constexpr auto to_zh(TianGan gan) -> std::string_view {
        constexpr std::array<std::string_view, 10> names = {
            "甲", "乙", "丙", "丁", "戊", 
            "己", "庚", "辛", "壬", "癸"
        };
        return names[static_cast<int>(gan)];
    }
    
    // 地支中文名称
    constexpr auto to_zh(DiZhi zhi) -> std::string_view {
        constexpr std::array<std::string_view, 12> names = {
            "子", "丑", "寅", "卯", "辰", "巳",
            "午", "未", "申", "酉", "戌", "亥"
        };
        return names[static_cast<int>(zhi)];
    }
    
    // 五行中文名称
    constexpr auto to_zh(WuXing wx) -> std::string_view {
        constexpr std::array<std::string_view, 5> names = {
            "木", "火", "土", "金", "水"
        };
        return names[static_cast<int>(wx) - 1];
    }
    
    // 阴阳中文名称
    constexpr auto to_zh(YinYang yy) -> std::string_view {
        return yy == YinYang::Yang ? "阳" : "阴";
    }
    
    // 生肖名称
    constexpr auto sheng_xiao_zh(DiZhi zhi) -> std::string_view {
        constexpr std::array<std::string_view, 12> names = {
            "鼠", "牛", "虎", "兔", "龙", "蛇",
            "马", "羊", "猴", "鸡", "狗", "猪"
        };
        return names[static_cast<int>(zhi)];
    }
    
    // 从中文查找天干
    constexpr auto from_zh_gan(std::string_view zh_name) -> std::optional<TianGan> {
        constexpr std::array<std::string_view, 10> names = {
            "甲", "乙", "丙", "丁", "戊", 
            "己", "庚", "辛", "壬", "癸"
        };
        
        for (std::size_t i = 0; i < names.size(); ++i) {
            if (names[i] == zh_name) {
                return static_cast<TianGan>(i);
            }
        }
        return std::nullopt;
    }
    
    // 从中文查找地支
    constexpr auto from_zh_zhi(std::string_view zh_name) -> std::optional<DiZhi> {
        constexpr std::array<std::string_view, 12> names = {
            "子", "丑", "寅", "卯", "辰", "巳",
            "午", "未", "申", "酉", "戌", "亥"
        };
        
        for (std::size_t i = 0; i < names.size(); ++i) {
            if (names[i] == zh_name) {
                return static_cast<DiZhi>(i);
            }
        }
        return std::nullopt;
    }
}

// ==================== 五行属性 ====================

/**
 * @brief 获取天干五行
 */
constexpr WuXing get_wu_xing(TianGan gan) {
    constexpr std::array<WuXing, 10> elements = {
        WuXing::Mu, WuXing::Mu,     // 甲乙木
        WuXing::Huo, WuXing::Huo,   // 丙丁火
        WuXing::Tu, WuXing::Tu,     // 戊己土
        WuXing::Jin, WuXing::Jin,   // 庚辛金
        WuXing::Shui, WuXing::Shui  // 壬癸水
    };
    return elements[static_cast<int>(gan)];
}

/**
 * @brief 获取地支五行
 */
constexpr WuXing get_wu_xing(DiZhi zhi) {
    constexpr std::array<WuXing, 12> elements = {
        WuXing::Shui,               // 子水
        WuXing::Tu,                 // 丑土
        WuXing::Mu, WuXing::Mu,     // 寅卯木
        WuXing::Tu,                 // 辰土
        WuXing::Huo, WuXing::Huo,   // 巳午火
        WuXing::Tu,                 // 未土
        WuXing::Jin, WuXing::Jin,   // 申酉金
        WuXing::Tu,                 // 戌土
        WuXing::Shui                // 亥水
    };
    return elements[static_cast<int>(zhi)];
}

// ==================== 阴阳属性 ====================

/**
 * @brief 获取天干阴阳
 */
constexpr YinYang get_yin_yang(TianGan gan) {
    // 甲丙戊庚壬为阳，乙丁己辛癸为阴
    return (static_cast<int>(gan) % 2 == 0) ? YinYang::Yang : YinYang::Yin;
}

/**
 * @brief 获取地支阴阳
 */
constexpr YinYang get_yin_yang(DiZhi zhi) {
    // 子寅辰午申戌为阳，丑卯巳未酉亥为阴
    return (static_cast<int>(zhi) % 2 == 0) ? YinYang::Yang : YinYang::Yin;
}

// ==================== 五行生克关系 ====================

/**
 * @brief 判断五行相生（x生y）
 */
constexpr bool wu_xing_sheng(WuXing x, WuXing y) {
    // 木生火、火生土、土生金、金生水、水生木
    int ix = static_cast<int>(x);
    int iy = static_cast<int>(y);
    return (ix == 1 && iy == 2) || (ix == 2 && iy == 3) || 
           (ix == 3 && iy == 4) || (ix == 4 && iy == 5) || (ix == 5 && iy == 1);
}

/**
 * @brief 判断五行相克（x克y）
 */
constexpr bool wu_xing_ke(WuXing x, WuXing y) {
    // 木克土、土克水、水克火、火克金、金克木
    int ix = static_cast<int>(x);
    int iy = static_cast<int>(y);
    return (ix == 1 && iy == 3) || (ix == 3 && iy == 5) || 
           (ix == 5 && iy == 2) || (ix == 2 && iy == 4) || (ix == 4 && iy == 1);
}

// ==================== 地支关系 ====================

/**
 * @brief 判断地支相冲（六冲）
 */
constexpr bool is_chong(DiZhi zhi1, DiZhi zhi2) {
    // 子午冲、丑未冲、寅申冲、卯酉冲、辰戌冲、巳亥冲
    return (static_cast<int>(zhi1) + 6) % 12 == static_cast<int>(zhi2);
}

/**
 * @brief 判断地支相刑
 */
constexpr bool is_xing(DiZhi zhi1, DiZhi zhi2) {
    // 子卯刑、寅巳申三刑、丑戌未三刑、辰午酉亥自刑
    int i1 = static_cast<int>(zhi1);
    int i2 = static_cast<int>(zhi2);
    
    // 子卯相刑
    if ((i1 == 0 && i2 == 3) || (i1 == 3 && i2 == 0)) return true;
    
    // 寅巳申三刑
    if ((i1 == 2 && i2 == 5) || (i1 == 5 && i2 == 9) || (i1 == 9 && i2 == 2)) return true;
    
    // 丑戌未三刑
    if ((i1 == 1 && i2 == 10) || (i1 == 10 && i2 == 7) || (i1 == 7 && i2 == 1)) return true;
    
    // 自刑：辰辰、午午、酉酉、亥亥
    if (i1 == i2 && (i1 == 4 || i1 == 6 || i1 == 9 || i1 == 11)) return true;
    
    return false;
}

/**
 * @brief 判断地支相合（六合）
 */
constexpr bool is_he(DiZhi zhi1, DiZhi zhi2) {
    // 子丑合、寅亥合、卯戌合、辰酉合、巳申合、午未合
    constexpr std::array<std::pair<int, int>, 6> he_pairs = {{
        {0, 1}, {2, 11}, {3, 10}, {4, 9}, {5, 8}, {6, 7}
    }};
    
    int i1 = static_cast<int>(zhi1);
    int i2 = static_cast<int>(zhi2);
    
    for (const auto& [a, b] : he_pairs) {
        if ((i1 == a && i2 == b) || (i1 == b && i2 == a)) {
            return true;
        }
    }
    return false;
}

/**
 * @brief 判断地支相害（六害）
 */
constexpr bool is_hai(DiZhi zhi1, DiZhi zhi2) {
    // 子未害、丑午害、寅巳害、卯辰害、申亥害、酉戌害
    constexpr std::array<std::pair<int, int>, 6> hai_pairs = {{
        {0, 7}, {1, 6}, {2, 5}, {3, 4}, {8, 11}, {9, 10}
    }};
    
    int i1 = static_cast<int>(zhi1);
    int i2 = static_cast<int>(zhi2);
    
    for (const auto& [a, b] : hai_pairs) {
        if ((i1 == a && i2 == b) || (i1 == b && i2 == a)) {
            return true;
        }
    }
    return false;
}

/**
 * @brief 判断地支三合
 * @return pair<bool, WuXing> - 是否三合，合化的五行
 */
constexpr auto is_san_he(DiZhi zhi1, DiZhi zhi2, DiZhi zhi3) -> std::pair<bool, WuXing> {
    std::array<int, 3> arr = {
        static_cast<int>(zhi1),
        static_cast<int>(zhi2),
        static_cast<int>(zhi3)
    };
    std::sort(arr.begin(), arr.end());
    
    // 申子辰合水局
    if (arr[0] == 0 && arr[1] == 4 && arr[2] == 8) return {true, WuXing::Shui};
    
    // 亥卯未合木局
    if (arr[0] == 3 && arr[1] == 7 && arr[2] == 11) return {true, WuXing::Mu};
    
    // 寅午戌合火局
    if (arr[0] == 2 && arr[1] == 6 && arr[2] == 10) return {true, WuXing::Huo};
    
    // 巳酉丑合金局
    if (arr[0] == 1 && arr[1] == 5 && arr[2] == 9) return {true, WuXing::Jin};
    
    return {false, WuXing::Mu};
}

// ==================== 天干寄宫 ====================

/**
 * @brief 获取天干寄宫地支
 */
constexpr DiZhi get_ji_gong(TianGan gan) {
    constexpr std::array<DiZhi, 10> palaces = {
        DiZhi::Yin,    // 甲寄寅
        DiZhi::Chen,   // 乙寄辰
        DiZhi::Si,     // 丙寄巳
        DiZhi::Wei,    // 丁寄未
        DiZhi::Si,     // 戊寄巳
        DiZhi::Wei,    // 己寄未
        DiZhi::Shen,   // 庚寄申
        DiZhi::Xu,     // 辛寄戌
        DiZhi::Hai,    // 壬寄亥
        DiZhi::Chou    // 癸寄丑
    };
    return palaces[static_cast<int>(gan)];
}

/**
 * @brief 获取地支中寄居的天干
 */
constexpr auto get_ji_gan(DiZhi zhi) -> std::vector<TianGan> {
    std::vector<TianGan> result;
    
    switch (zhi) {
    case DiZhi::Yin:
        result.push_back(TianGan::Jia);
        break;
    case DiZhi::Chen:
        result.push_back(TianGan::Yi);
        break;
    case DiZhi::Si:
        result.push_back(TianGan::Bing);
        result.push_back(TianGan::Wu);
        break;
    case DiZhi::Wei:
        result.push_back(TianGan::Ding);
        result.push_back(TianGan::Ji);
        break;
    case DiZhi::Shen:
        result.push_back(TianGan::Geng);
        break;
    case DiZhi::Xu:
        result.push_back(TianGan::Xin);
        break;
    case DiZhi::Hai:
        result.push_back(TianGan::Ren);
        break;
    case DiZhi::Chou:
        result.push_back(TianGan::Gui);
        break;
    default:
        // 子、卯、午、酉无天干寄宫
        break;
    }
    
    return result;
}

// ==================== 贵人表 ====================

/**
 * @brief 获取天干贵人地支
 * @param is_day true为阳贵人（昼），false为阴贵人（夜）
 */
constexpr DiZhi get_gui_ren(TianGan gan, bool is_day) {
    // 贵人歌诀：甲戊庚牛羊，乙己鼠猴乡，丙丁猪鸡位，壬癸蛇兔藏，六辛逢马虎
    constexpr std::array<std::pair<DiZhi, DiZhi>, 10> table = {{
        {DiZhi::Chou, DiZhi::Wei},  // 甲 - 丑/未（牛/羊）
        {DiZhi::Zi, DiZhi::Shen},   // 乙 - 子/申（鼠/猴）
        {DiZhi::Hai, DiZhi::You},   // 丙 - 亥/酉（猪/鸡）
        {DiZhi::Hai, DiZhi::You},   // 丁 - 亥/酉（猪/鸡）
        {DiZhi::Chou, DiZhi::Wei},  // 戊 - 丑/未（牛/羊）
        {DiZhi::Zi, DiZhi::Shen},   // 己 - 子/申（鼠/猴）
        {DiZhi::Chou, DiZhi::Wei},  // 庚 - 丑/未（牛/羊）
        {DiZhi::Wu, DiZhi::Yin},    // 辛 - 午/寅（马/虎）
        {DiZhi::Si, DiZhi::Mao},    // 壬 - 巳/卯（蛇/兔）
        {DiZhi::Si, DiZhi::Mao}     // 癸 - 巳/卯（蛇/兔）
    }};
    
    auto [yang, yin] = table[static_cast<int>(gan)];
    return is_day ? yang : yin;
}

/**
 * @brief 判断是否为白天（卯时到申时）
 */
constexpr bool is_daytime(DiZhi hour) {
    int idx = static_cast<int>(hour);
    return (idx >= 3 && idx <= 8); // 3=卯, 8=申
}

// ==================== 地支藏干 ====================

/**
 * @brief 获取地支藏干（主气、中气、余气）
 */
constexpr auto get_cang_gan(DiZhi zhi) -> std::vector<TianGan> {
    constexpr std::array<std::vector<TianGan>, 12> cang_gan_table = {{
        {TianGan::Gui},                            // 子：癸
        {TianGan::Ji, TianGan::Gui, TianGan::Xin}, // 丑：己癸辛
        {TianGan::Jia, TianGan::Bing, TianGan::Wu}, // 寅：甲丙戊
        {TianGan::Yi},                             // 卯：乙
        {TianGan::Wu, TianGan::Yi, TianGan::Gui},  // 辰：戊乙癸
        {TianGan::Bing, TianGan::Wu, TianGan::Geng}, // 巳：丙戊庚
        {TianGan::Ding, TianGan::Ji},              // 午：丁己
        {TianGan::Ji, TianGan::Ding, TianGan::Yi}, // 未：己丁乙
        {TianGan::Geng, TianGan::Ren, TianGan::Wu}, // 申：庚壬戊
        {TianGan::Xin},                            // 酉：辛
        {TianGan::Wu, TianGan::Xin, TianGan::Ding}, // 戌：戊辛丁
        {TianGan::Ren, TianGan::Jia}               // 亥：壬甲
    }};
    
    return cang_gan_table[static_cast<int>(zhi)];
}

// ==================== 六十甲子 ====================

/**
 * @brief 六十甲子类
 */
class LiuShiJiaZi {
public:
    TianGan gan;
    DiZhi zhi;
    
    constexpr LiuShiJiaZi(TianGan g, DiZhi z) : gan(g), zhi(z) {}
    
    /**
     * @brief 从索引创建（0-59）
     */
    static constexpr LiuShiJiaZi from_index(int index) {
        index = ((index % 60) + 60) % 60;
        return LiuShiJiaZi(
            static_cast<TianGan>(index % 10),
            static_cast<DiZhi>(index % 12)
        );
    }
    
    /**
     * @brief 获取索引（0-59）
     */
    constexpr int to_index() const {
        // 使用中国剩余定理求解
        int g = static_cast<int>(gan);
        int z = static_cast<int>(zhi);
        
        // 寻找满足条件的索引
        for (int i = g; i < 60; i += 10) {
            if (i % 12 == z) {
                return i;
            }
        }
        return 0;
    }
    
    /**
     * @brief 获取中文名称
     */
    std::string to_string() const {
        return std::string(Mapper::to_zh(gan)) + std::string(Mapper::to_zh(zhi));
    }
    
    /**
     * @brief 获取纳音五行
     */
    constexpr WuXing get_na_yin() const {
        // 纳音五行表
        constexpr std::array<WuXing, 60> na_yin_table = {
            WuXing::Jin, WuXing::Jin,   // 甲子乙丑海中金
            WuXing::Huo, WuXing::Huo,   // 丙寅丁卯炉中火
            WuXing::Mu, WuXing::Mu,     // 戊辰己巳大林木
            WuXing::Jin, WuXing::Jin,   // 庚午辛未路旁土
            WuXing::Tu, WuXing::Tu,     // 壬申癸酉剑锋金
            WuXing::Shui, WuXing::Shui, // 甲戌乙亥山头火
            WuXing::Huo, WuXing::Huo,   // 丙子丁丑涧下水
            WuXing::Tu, WuXing::Tu,     // 戊寅己卯城头土
            WuXing::Mu, WuXing::Mu,     // 庚辰辛巳白蜡金
            WuXing::Jin, WuXing::Jin,   // 壬午癸未杨柳木
            WuXing::Shui, WuXing::Shui, // 甲申乙酉泉中水
            WuXing::Tu, WuXing::Tu,     // 丙戌丁亥屋上土
            WuXing::Mu, WuXing::Mu,     // 戊子己丑霹雳火
            WuXing::Huo, WuXing::Huo,   // 庚寅辛卯松柏木
            WuXing::Jin, WuXing::Jin,   // 壬辰癸巳长流水
            WuXing::Tu, WuXing::Tu,     // 甲午乙未沙中金
            WuXing::Shui, WuXing::Shui, // 丙申丁酉山下火
            WuXing::Huo, WuXing::Huo,   // 戊戌己亥平地木
            WuXing::Mu, WuXing::Mu,     // 庚子辛丑壁上土
            WuXing::Jin, WuXing::Jin,   // 壬寅癸卯金箔金
            WuXing::Tu, WuXing::Tu,     // 甲辰乙巳佛灯火
            WuXing::Shui, WuXing::Shui, // 丙午丁未天河水
            WuXing::Huo, WuXing::Huo,   // 戊申己酉大驿土
            WuXing::Mu, WuXing::Mu,     // 庚戌辛亥钗钏金
            WuXing::Jin, WuXing::Jin,   // 壬子癸丑桑柘木
            WuXing::Tu, WuXing::Tu,     // 甲寅乙卯大溪水
            WuXing::Shui, WuXing::Shui, // 丙辰丁巳沙中土
            WuXing::Huo, WuXing::Huo,   // 戊午己未天上火
            WuXing::Mu, WuXing::Mu,     // 庚申辛酉石榴木
            WuXing::Jin, WuXing::Jin    // 壬戌癸亥大海水
        };
        
        return na_yin_table[to_index()];
    }
};

/**
 * @brief 获取完整的六十甲子表
 */
inline auto get_liu_shi_jia_zi() -> std::vector<LiuShiJiaZi> {
    std::vector<LiuShiJiaZi> result;
    result.reserve(60);
    
    for (int i = 0; i < 60; ++i) {
        result.push_back(LiuShiJiaZi::from_index(i));
    }
    
    return result;
}

} // namespace ZhouYi::GanZhi

