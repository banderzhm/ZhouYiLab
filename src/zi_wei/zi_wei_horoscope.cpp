// 紫微斗数运限系统模块（实现）
module ZhouYi.ZiWei.Horoscope;

import ZhouYi.GanZhi;
import ZhouYi.ZiWei.Constants;
import ZhouYi.ZiWei.Palace;
import ZhouYi.ZiWei.SiHua;
import ZhouYi.ZiWei.Star;
import fmt;
import std;
import ZhouYi.ZhMapper;

namespace ZhouYi::ZiWei {
using namespace std;
using namespace ZhouYi::GanZhi;
using namespace ZhouYi::Mapper;

// ============= 数据结构 to_string 实现 =============

string DaXianData::to_string() const {
    return fmt::format("大限 {}~{} 岁 [{}-{}宫] 四化: {}", start_age, end_age,
                       string(GanZhi::Mapper::to_zh(tian_gan)),
                       string(GanZhi::Mapper::to_zh(di_zhi)), fmt::join(si_hua, " "));
}

string XiaoXianData::to_string() const {
    return fmt::format("小限 {} 岁 [第{}宫]", age, gong_index);
}

string LiuNianData::to_string() const {
    return fmt::format("流年 {} 年 [{}-{}宫] 四化: {}", year,
                       string(GanZhi::Mapper::to_zh(tian_gan)),
                       string(GanZhi::Mapper::to_zh(di_zhi)), fmt::join(si_hua, " "));
}

string LiuYueData::to_string() const {
    return fmt::format("流月 {} 月 [{}-{}宫] 四化: {}", month,
                       string(GanZhi::Mapper::to_zh(tian_gan)),
                       string(GanZhi::Mapper::to_zh(di_zhi)), fmt::join(si_hua, " "));
}

string LiuRiData::to_string() const {
    return fmt::format("流日 {} 日 [{}-{}宫] 四化: {}", day,
                       string(GanZhi::Mapper::to_zh(tian_gan)),
                       string(GanZhi::Mapper::to_zh(di_zhi)), fmt::join(si_hua, " "));
}

string LiuShiData::to_string() const {
    return fmt::format("流时 {}时 [{}-{}宫] 四化: {}", string(GanZhi::Mapper::to_zh(shi_chen)),
                       string(GanZhi::Mapper::to_zh(tian_gan)),
                       string(GanZhi::Mapper::to_zh(di_zhi)), fmt::join(si_hua, " "));
}

string HoroscopeResult::to_string() const {
    string result = fmt::format("{}\n{}\n{}\n{}\n{}\n{}\n", da_xian.to_string(),
                                xiao_xian.to_string(), liu_nian.to_string(), liu_yue.to_string(),
                                liu_ri.to_string(), liu_shi.to_string());

    // 十二宫角标与目标流年口径神煞：宫序与 ZiWeiResult::palaces 一致，
    // 自寅宫起 0。没有角标的宫只列神煞，不用占位符补空栏目。
    result += "【十二宫运限角标与流年神煞（宫序自寅宫起 0）】\n";
    for (const auto &tag : palace_tags) {
        result += "宫" + std::to_string(tag.gong_index);
        if (tag.is_da_xian)
            result += " [大限]";
        if (tag.is_xiao_xian)
            result += " [小限]";
        if (tag.is_liu_nian)
            result += " [流年]";
        result += fmt::format(" 岁前:{} 将前:{}\n", string(to_zh(tag.sui_qian)),
                              string(to_zh(tag.jiang_qian)));
    }

    // 五组运限流曜：只列实际落星的宫位，空宫不出行。
    const array<pair<string_view, const array<HoroscopeStarData, 12> *>, 5> star_groups = {
        {{"大限流曜", &da_xian_stars},
         {"流年流曜", &liu_nian_stars},
         {"流月流曜", &liu_yue_stars},
         {"流日流曜", &liu_ri_stars},
         {"流时流曜", &liu_shi_stars}}};
    string star_lines;
    for (const auto &[label, group] : star_groups) {
        string line;
        for (const auto &entry : *group) {
            if (entry.stars.empty())
                continue;
            if (!line.empty())
                line += "；";
            line += fmt::format("宫{} {}", entry.gong_index, fmt::join(entry.stars, "、"));
        }
        if (!line.empty())
            star_lines += fmt::format("{}：{}\n", label, line);
    }
    if (!star_lines.empty())
        result += "【运限流曜】\n" + star_lines;

    return result;
}

namespace {

/**
 * @brief 按禄→权→科→忌把一限的四化写入星名序列与明细
 *
 * 四化表以 get_si_hua_entries 为唯一来源，覆盖十四主星与左辅、右弼、文昌、
 * 文曲；不再遍历 map<ZhuXing, SiHua>，避免按星曜枚举顺序把化科、化忌排错位。
 *
 * @param gan 限内天干
 * @param si_hua 输出星名序列，下标即 SiHua 枚举值（0 禄、1 权、2 科、3 忌）
 * @param entries 输出四化明细，顺序与 si_hua 相同
 */
void fill_si_hua(TianGan gan, array<string, 4> &si_hua, vector<SiHuaEntry> &entries) {
    entries = get_si_hua_entries(gan);
    si_hua = {};
    for (size_t index = 0; index < entries.size() && index < si_hua.size(); ++index)
        si_hua[index] = string(to_zh(entries[index].xing));
}

} // namespace

// ============= 大限算法 =============

/**
 * @brief 安大限诀
 *
 * 自命宫起每十年过一宫，阳男阴女顺行、阴男阳女逆行；大限干支直接取该宫
 * 自身的宫干支，使运限与大限流曜都跟命盘同宫保持一致。
 */
array<DaXianData, 12> arrange_da_xian(int ming_index, WuXingJu wu_xing_ju, bool is_male,
                                      DiZhi year_zhi, const vector<GongWeiData> &palaces) {
    if (palaces.size() != 12)
        throw invalid_argument("安大限需要寅起宫序的十二宫干支");

    array<DaXianData, 12> result{};

    // 起运年龄取五行局数；顺逆只看生年支阴阳与性别，与限序无关。
    const int qi_yun_age = static_cast<int>(wu_xing_ju);
    const bool yang_zhi = (static_cast<int>(year_zhi) % 2 == 0);
    const bool shun_xing = (is_male == yang_zhi);

    for (int i = 0; i < 12; ++i) {
        const int idx = shun_xing ? fix_index(ming_index + i) : fix_index(ming_index - i);
        const int start_age = qi_yun_age + 10 * i;

        // 宫支自寅宫顺布十二支、宫干由年干按五虎遁布出，两者都取自命盘的
        // GongWeiData；不按限序另起甲乙丙，也不把寅起宫序直转地支枚举。
        const GongWeiData &palace = palaces[idx];

        DaXianData da_xian{.start_age = start_age,
                           .end_age = start_age + 9,
                           .gong_index = idx,
                           .tian_gan = palace.tian_gan,
                           .di_zhi = palace.di_zhi,
                           .si_hua = {},
                           .si_hua_entries = {}};
        fill_si_hua(palace.tian_gan, da_xian.si_hua, da_xian.si_hua_entries);
        result[idx] = move(da_xian);
    }

    return result;
}
// ============= 小限算法 =============

/**
 * @brief 小限起宫：按生年支三合局定 1 岁所在宫
 *
 * 寅午戌年辰宫起 1 岁、申子辰年戌宫起 1 岁、亥卯未年丑宫起 1 岁、
 * 巳酉丑年未宫起 1 岁。本函数是起宫规则的唯一实现，命盘的
 * PalaceInfo::xiao_xian_ages 与 get_xiao_xian 共用同一份表。
 */
int get_xiao_xian_start_gong(DiZhi year_zhi) {
    switch (year_zhi) {
    case DiZhi::Yin:
    case DiZhi::Wu:
    case DiZhi::Xu:
        return 2; // 辰宫
    case DiZhi::Shen:
    case DiZhi::Zi:
    case DiZhi::Chen:
        return 8; // 戌宫
    case DiZhi::Hai:
    case DiZhi::Mao:
    case DiZhi::Wei:
        return 11; // 丑宫
    default:
        return 5; // 巳酉丑年未宫
    }
}

/**
 * @brief 获取小限宫位
 *
 * 自三合局起宫起 1 岁、每岁一宫，阳男阴女顺行、阴男阳女逆行；1-6 岁不另设
 * 童限跳宫，保证与命盘记录的小限年龄逐岁同宫。
 */
XiaoXianData get_xiao_xian(int age, bool is_male, DiZhi year_zhi) {
    const int start_gong = get_xiao_xian_start_gong(year_zhi);
    const bool yang_zhi = (static_cast<int>(year_zhi) % 2 == 0);
    const bool shun_xing = (is_male == yang_zhi);
    const int offset = age - 1;

    return XiaoXianData{.age = age,
                        .gong_index = shun_xing ? fix_index(start_gong + offset)
                                                : fix_index(start_gong - offset)};
}
// ============= 流年算法 =============

/**
 * @brief 获取流年宫位
 *
 * 流年以流年地支定宫：子年在子宫、丑年在丑宫，余仿此；天干只用于取流年四化。
 */
LiuNianData get_liu_nian(int year, TianGan year_gan, DiZhi year_zhi, int ming_index) {
    // 地支枚举以子为 0，宫序自寅宫起 0，两者相差两宫。
    const int liu_nian_index = (static_cast<int>(year_zhi) + 10) % 12;

    // 流年四化按禄→权→科→忌取自有序四化表，覆盖主星与文昌、文曲等辅星。
    array<string, 4> si_hua = {};
    vector<SiHuaEntry> si_hua_entries;
    fill_si_hua(year_gan, si_hua, si_hua_entries);

    return LiuNianData{.year = year,
                       .tian_gan = year_gan,
                       .di_zhi = year_zhi,
                       .gong_index = liu_nian_index,
                       .si_hua = si_hua,
                       .si_hua_entries = move(si_hua_entries)};
}

// ============= 流月算法 =============

/**
 * @brief 获取流月宫位
 *
 * 自流年宫起命宫、逆数到生月所在宫，再自该宫起正月顺数到目标农历月。
 */
LiuYueData get_liu_yue(int lunar_month, int birth_month, TianGan month_gan, DiZhi month_zhi,
                       DiZhi year_zhi, int ming_index) {
    const int liu_nian_index = (static_cast<int>(year_zhi) + 10) % 12;

    // 自流年宫逆数到生月，再自生月宫顺数到目标月。
    const int birth_month_index = fix_index(liu_nian_index - (birth_month - 1));
    const int liu_yue_index = fix_index(birth_month_index + (lunar_month - 1));

    // 流月四化按禄→权→科→忌取自有序四化表，覆盖主星与文昌、文曲等辅星。
    array<string, 4> si_hua = {};
    vector<SiHuaEntry> si_hua_entries;
    fill_si_hua(month_gan, si_hua, si_hua_entries);

    return LiuYueData{.month = lunar_month,
                      .tian_gan = month_gan,
                      .di_zhi = month_zhi,
                      .gong_index = liu_yue_index,
                      .si_hua = si_hua,
                      .si_hua_entries = move(si_hua_entries)};
}

// ============= 流日算法 =============

/**
 * @brief 获取流日宫位
 *
 * 自流月宫起初一，顺数到目标农历日。
 */
LiuRiData get_liu_ri(int lunar_day, TianGan day_gan, DiZhi day_zhi, int liu_yue_index) {
    const int liu_ri_index = fix_index(liu_yue_index + (lunar_day - 1));

    // 流日四化按禄→权→科→忌取自有序四化表，覆盖主星与文昌、文曲等辅星。
    array<string, 4> si_hua = {};
    vector<SiHuaEntry> si_hua_entries;
    fill_si_hua(day_gan, si_hua, si_hua_entries);

    return LiuRiData{.day = lunar_day,
                     .tian_gan = day_gan,
                     .di_zhi = day_zhi,
                     .gong_index = liu_ri_index,
                     .si_hua = si_hua,
                     .si_hua_entries = move(si_hua_entries)};
}

// ============= 流时算法 =============

/**
 * @brief 获取流时宫位
 *
 * 自流日宫起子时，顺数到目标时辰；天干只用于取流时四化。
 */
LiuShiData get_liu_shi(DiZhi hour_zhi, TianGan hour_gan, int liu_ri_index) {
    const int liu_shi_index = fix_index(liu_ri_index + static_cast<int>(hour_zhi));

    // 流时四化按禄→权→科→忌取自有序四化表，覆盖主星与文昌、文曲等辅星。
    array<string, 4> si_hua = {};
    vector<SiHuaEntry> si_hua_entries;
    fill_si_hua(hour_gan, si_hua, si_hua_entries);

    return LiuShiData{.shi_chen = hour_zhi,
                      .tian_gan = hour_gan,
                      .di_zhi = hour_zhi,
                      .gong_index = liu_shi_index,
                      .si_hua = si_hua,
                      .si_hua_entries = move(si_hua_entries)};
}
// ============= 运限流耀星算法 =============

/**
 * @brief 获取运限流耀星（魁钺昌曲禄羊陀马鸾喜）
 *
 * 根据不同作用域返回对应的流耀星名称
 */
array<HoroscopeStarData, 12> get_horoscope_stars(TianGan gan, DiZhi zhi, Scope scope) {
    array<HoroscopeStarData, 12> result{};

    // 初始化每个宫位的星耀列表
    for (int i = 0; i < 12; ++i) {
        result[i].gong_index = i;
        result[i].stars = {};
    }

    // 获取各种星耀的位置
    auto [kui_idx, yue_idx] = get_kui_yue_index(gan);
    auto [chang_idx, qu_idx] = get_chang_qu_index(zhi);
    int lu_idx = get_lu_cun_index(gan);
    auto [yang_idx, tuo_idx] = get_yang_tuo_index(lu_idx);
    auto [hong_luan_idx, tian_xi_idx] = get_hong_luan_tian_xi_index(zhi);

    // 天马索引（按地支）
    int ma_idx = 0;
    if (zhi == DiZhi::Yin || zhi == DiZhi::Wu || zhi == DiZhi::Xu) {
        ma_idx = 6; // 申
    } else if (zhi == DiZhi::Shen || zhi == DiZhi::Zi || zhi == DiZhi::Chen) {
        ma_idx = 0; // 寅
    } else if (zhi == DiZhi::Si || zhi == DiZhi::You || zhi == DiZhi::Chou) {
        ma_idx = 9; // 亥
    } else {        // 亥卯未
        ma_idx = 3; // 巳
    }

    // 根据作用域确定星耀前缀
    string prefix;
    switch (scope) {
    case Scope::Origin:
        prefix = "";
        break;
    case Scope::Decadal:
        prefix = "运";
        break;
    case Scope::Yearly:
        prefix = "流";
        // 流年还有年解
        result[get_nian_jie_index(zhi)].stars.push_back("年解");
        break;
    case Scope::Monthly:
        prefix = "月";
        break;
    case Scope::Daily:
        prefix = "日";
        break;
    case Scope::Hourly:
        prefix = "时";
        break;
    }

    // 添加流耀星
    result[kui_idx].stars.push_back(prefix + "魁");
    result[yue_idx].stars.push_back(prefix + "钺");
    result[chang_idx].stars.push_back(prefix + "昌");
    result[qu_idx].stars.push_back(prefix + "曲");
    result[lu_idx].stars.push_back(prefix + "禄");
    result[yang_idx].stars.push_back(prefix + "羊");
    result[tuo_idx].stars.push_back(prefix + "陀");
    result[ma_idx].stars.push_back(prefix + "马");
    result[hong_luan_idx].stars.push_back(prefix + "鸾");
    result[tian_xi_idx].stars.push_back(prefix + "喜");

    return result;
}

} // namespace ZhouYi::ZiWei
