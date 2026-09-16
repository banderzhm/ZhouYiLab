// 紫微斗数运限系统模块（接口）
export module ZhouYi.ZiWei.Horoscope;

import std;
import ZhouYi.GanZhi;
import ZhouYi.ZiWei.Constants;
import ZhouYi.ZiWei.Palace;
import ZhouYi.ZiWei.SiHua;
import ZhouYi.ZiWei.Star;
import ZhouYi.ZhMapper;

export namespace ZhouYi::ZiWei {
using namespace std;

/**
 * @brief 运限作用域
 */
enum class Scope {
    Origin,  // 本命盘（地盘）
    Decadal, // 大限盘（天盘）
    Yearly,  // 流年盘（人盘）
    Monthly, // 流月盘
    Daily,   // 流日盘
    Hourly   // 流时盘
};

/**
 * @brief 大限数据结构（10年一大限）
 */
struct DaXianData {
    int start_age;           // 起始年龄
    int end_age;             // 结束年龄
    int gong_index;          // 所在宫位索引
    TianGan tian_gan;        // 大限天干，取该宫真实宫干
    DiZhi di_zhi;            // 大限地支，取该宫真实宫支
    array<string, 4> si_hua; // 大限四化星名，按禄→权→科→忌排列

    /**
     * @brief 大限四化的星曜与化象明细
     *
     * 与 si_hua 同源，覆盖十四主星与左辅、右弼、文昌、文曲；顺序固定为
     * 化禄、化权、化科、化忌。si_hua[k] 即本字段第 k 项的星名。
     */
    vector<SiHuaEntry> si_hua_entries;

    string to_string() const;
};

/**
 * @brief 小限数据结构（虚岁对应的宫位）
 */
struct XiaoXianData {
    int age;        // 虚岁
    int gong_index; // 所在宫位索引

    string to_string() const;
};

/**
 * @brief 流年数据结构
 */
struct LiuNianData {
    int year;                // 公历年份
    TianGan tian_gan;        // 流年天干
    DiZhi di_zhi;            // 流年地支
    int gong_index;          // 所在宫位索引（流年地支对应）
    array<string, 4> si_hua; // 流年四化星名，按禄→权→科→忌排列

    /** @brief 流年四化的星曜与化象明细，口径同 DaXianData::si_hua_entries。 */
    vector<SiHuaEntry> si_hua_entries;

    string to_string() const;
};

/**
 * @brief 流月数据结构
 */
struct LiuYueData {
    int month;               // 农历月份
    TianGan tian_gan;        // 流月天干
    DiZhi di_zhi;            // 流月地支
    int gong_index;          // 所在宫位索引
    array<string, 4> si_hua; // 流月四化星名，按禄→权→科→忌排列

    /** @brief 流月四化的星曜与化象明细，口径同 DaXianData::si_hua_entries。 */
    vector<SiHuaEntry> si_hua_entries;

    string to_string() const;
};

/**
 * @brief 流日数据结构
 */
struct LiuRiData {
    int day;                 // 农历日
    TianGan tian_gan;        // 流日天干
    DiZhi di_zhi;            // 流日地支
    int gong_index;          // 所在宫位索引
    array<string, 4> si_hua; // 流日四化星名，按禄→权→科→忌排列

    /** @brief 流日四化的星曜与化象明细，口径同 DaXianData::si_hua_entries。 */
    vector<SiHuaEntry> si_hua_entries;

    string to_string() const;
};

/**
 * @brief 流时数据结构
 */
struct LiuShiData {
    DiZhi shi_chen;          // 时辰地支
    TianGan tian_gan;        // 流时天干
    DiZhi di_zhi;            // 流时地支
    int gong_index;          // 所在宫位索引
    array<string, 4> si_hua; // 流时四化星名，按禄→权→科→忌排列

    /** @brief 流时四化的星曜与化象明细，口径同 DaXianData::si_hua_entries。 */
    vector<SiHuaEntry> si_hua_entries;

    string to_string() const;
};

/**
 * @brief 运限星耀系统
 * 大限、流年、流月、流日、流时的魁钺昌曲禄羊陀马鸾喜
 */
struct HoroscopeStarData {
    int gong_index;       // 宫位索引
    vector<string> stars; // 流耀星名称列表
};

/**
 * @brief 安大限诀
 *
 * 口诀：
 * 大限由命宫起，阳男阴女顺行，
 * 阴男阳女逆行，每十年过一宫限。
 *
 * 自命宫起按顺逆方向每十年过一宫，起运年龄取五行局数，大限干支取该宫自身的
 * 宫干支（宫支自寅宫顺布十二支，宫干由年干按五虎遁布出，即
 * ZiWeiResult::palaces[i].gong_data），不按限序另起甲乙丙，也不把寅起宫序当地支
 * 枚举直转。运限流曜随大限干支外布，口径由此与命盘同宫干支一致。
 *
 * @param ming_index 命宫索引（寅宫起 0），范围 0-11
 * @param wu_xing_ju 五行局，决定起运虚岁（水二局 2 岁起、火六局 6 岁起）
 * @param is_male 是否为男性；阳男阴女顺行、阴男阳女逆行
 * @param year_zhi 生年地支，用于判断顺逆
 * @param palaces 寅起宫序的十二宫数据，提供各宫真实干支，取自 ZiWeiResult::palaces
 * @return 12 个大限数据，下标为宫序（寅起 0）
 * @throws std::invalid_argument palaces 不是十二宫时抛出
 */
array<DaXianData, 12> arrange_da_xian(int ming_index, WuXingJu wu_xing_ju, bool is_male,
                                      DiZhi year_zhi, const vector<GongWeiData> &palaces);

/**
 * @brief 小限起宫：按生年支的三合局定 1 岁所在宫
 *
 * 口诀：寅午戌年生人辰宫起 1 岁，申子辰年生人戌宫起 1 岁，
 * 亥卯未年生人丑宫起 1 岁，巳酉丑年生人未宫起 1 岁。
 *
 * 本函数是起宫规则的唯一实现：ZiWeiResult::palaces[i].xiao_xian_ages 与
 * get_xiao_xian 都调用它，排盘与运限不得各写一份起宫表。
 *
 * @param year_zhi 生年地支
 * @return 1 岁所在宫位索引，范围 0-11，寅宫起 0
 */
int get_xiao_xian_start_gong(DiZhi year_zhi);

/**
 * @brief 获取指定虚岁的小限宫位
 *
 * 口径：按生年支的三合局起宫（见 get_xiao_xian_start_gong），自起宫起 1 岁、
 * 每岁一宫，男命顺行、女命逆行，顺逆判断与阳男阴女顺行一致。不对 1-6 岁另设
 * 童限跳宫，保证与 ZiWeiResult::palaces[i].xiao_xian_ages 逐岁同宫。
 *
 * @param age 虚岁，最小为 1；超出命盘记录的年龄按同一周期推算
 * @param is_male 是否为男性
 * @param year_zhi 生年地支
 * @return 小限数据，gong_index 为寅起宫序，范围 0-11
 */
XiaoXianData get_xiao_xian(int age, bool is_male, DiZhi year_zhi);

/**
 * @brief 获取流年宫位
 *
 * 算法：流年以地支定宫，如甲子年在子宫
 *
 * @param year_gan 流年天干
 * @param year_zhi 流年地支
 * @param ming_index 命宫索引
 * @return 流年数据
 */
LiuNianData get_liu_nian(int year, TianGan year_gan, DiZhi year_zhi, int ming_index);

/**
 * @brief 获取流月宫位
 *
 * 算法：
 * 1. 从流年地支起命宫，逆数到生月所在宫位
 * 2. 再从该宫位起正月，顺数到流月
 *
 * @param lunar_month 农历月份
 * @param birth_month 出生月份
 * @param year_zhi 流年地支
 * @param ming_index 命宫索引
 * @return 流月数据
 */
LiuYueData get_liu_yue(int lunar_month, int birth_month, TianGan month_gan, DiZhi month_zhi,
                       DiZhi year_zhi, int ming_index);

/**
 * @brief 获取流日宫位
 *
 * 算法：从流月宫位起初一，顺数到流日
 *
 * @param lunar_day 农历日
 * @param day_gan 流日天干
 * @param day_zhi 流日地支
 * @param liu_yue_index 流月宫位索引
 * @return 流日数据
 */
LiuRiData get_liu_ri(int lunar_day, TianGan day_gan, DiZhi day_zhi, int liu_yue_index);

/**
 * @brief 获取流时宫位
 *
 * 算法：从流日宫位起子时，顺数到流时
 *
 * @param hour_zhi 时辰地支
 * @param hour_gan 流时天干
 * @param liu_ri_index 流日宫位索引
 * @return 流时数据
 */
LiuShiData get_liu_shi(DiZhi hour_zhi, TianGan hour_gan, int liu_ri_index);

/**
 * @brief 获取运限流耀星（魁钺昌曲禄羊陀马鸾喜）
 *
 * @param gan 天干
 * @param zhi 地支
 * @param scope 作用域
 * @return 流耀星数据（12个宫位）
 */
array<HoroscopeStarData, 12> get_horoscope_stars(TianGan gan, DiZhi zhi, Scope scope);

/**
 * @brief 单宫运限归属与目标流年口径神煞
 *
 * 供前端在十二宫上标注运限角标。索引空间与 ZiWeiResult::palaces 相同，
 * 均为寅宫起的宫序（0=寅、1=卯、2=辰、3=巳、4=午、5=未、6=申、7=酉、
 * 8=戌、9=亥、10=子、11=丑），不是 DiZhi 枚举值。
 */
struct PalaceHoroscopeTag {
    /** @brief 本宫索引，范围 0-11，寅宫起 0。 */
    int gong_index = 0;

    /** @brief 本宫是否为当前虚岁所落的大限宫；虚岁未到起运年龄时以起运限为准。 */
    bool is_da_xian = false;

    /** @brief 本宫是否为当前虚岁所落的小限宫；口径见 ZiWeiResult::get_horoscope。 */
    bool is_xiao_xian = false;

    /** @brief 本宫是否为目标流年的流年命宫，即目标流年地支所在宫。 */
    bool is_liu_nian = false;

    /** @brief 目标流年口径的岁前十二神（流年地支起岁建，顺布十二宫）。 */
    SuiQian12 sui_qian = SuiQian12::SuiJian;

    /** @brief 目标流年口径的将前十二神：以三合将星起宫，顺布十二宫。 */
    JiangQian12 jiang_qian = JiangQian12::JiangXing;
};

/**
 * @brief 综合运限结果
 */
struct HoroscopeResult {
    DaXianData da_xian;     // 大限
    XiaoXianData xiao_xian; // 小限
    LiuNianData liu_nian;   // 流年
    LiuYueData liu_yue;     // 流月
    LiuRiData liu_ri;       // 流日
    LiuShiData liu_shi;     // 流时

    // 各运限的流耀星
    array<HoroscopeStarData, 12> da_xian_stars;  // 大限星（运魁、运钺...）
    array<HoroscopeStarData, 12> liu_nian_stars; // 流年星（流魁、流钺...）
    array<HoroscopeStarData, 12> liu_yue_stars;  // 流月星（月魁、月钺...）
    array<HoroscopeStarData, 12> liu_ri_stars;   // 流日星（日魁、日钺...）
    array<HoroscopeStarData, 12> liu_shi_stars;  // 流时星（时魁、时钺...）

    // 目标流年口径的十二宫角标与神煞
    /** @brief 十二宫的运限归属与目标流年口径岁前／将前，索引为寅起宫序。 */
    array<PalaceHoroscopeTag, 12> palace_tags{};

    /**
     * @brief 输出运限摘要
     *
     * 依次输出大限、小限、流年、流月、流日、流时，随后附十二宫运限角标、
     * 目标流年口径岁前／将前，以及各组运限流曜的落宫。
     *
     * @return 多行中文文本，行内不使用 Tab，宫序统一为寅起 0。
     */
    string to_string() const;
};

} // namespace ZhouYi::ZiWei
