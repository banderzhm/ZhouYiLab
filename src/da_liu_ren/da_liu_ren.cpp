// 大六壬实现文件
module ZhouYi.DaLiuRen;

import std;
import fmt;
import ZhouYi.DaLiuRen.GuaTi;

namespace ZhouYi::DaLiuRen {

// ==================== SanChuan 辅助函数 ====================

// 获取三传的遁干
std::array<std::optional<TianGan>, 3> SanChuan::get_dun_gan(TianGan day_gan, DiZhi day_zhi) const {
    std::array<std::optional<TianGan>, 3> dun_gan;
    std::array<DiZhi, 3> san_chuan = {chu_chuan_, zhong_chuan_, mo_chuan_};
    
    for (int i = 0; i < 3; ++i) {
        dun_gan[i] = ZhouYi::GanZhi::get_dun_gan(san_chuan[i], day_gan, day_zhi);
    }
    
    return dun_gan;
}

// 获取三传的六亲关系
std::array<std::string_view, 3> SanChuan::get_liu_qin(TianGan day_gan) const {
    std::array<std::string_view, 3> liu_qin;
    std::array<DiZhi, 3> san_chuan = {chu_chuan_, zhong_chuan_, mo_chuan_};
    
    for (int i = 0; i < 3; ++i) {
        auto lq = ZhouYi::GanZhi::get_liu_qin(day_gan, san_chuan[i]);
        liu_qin[i] = ZhouYi::GanZhi::liu_qin_to_zh(lq);
    }
    
    return liu_qin;
}

// ==================== SanChuan 实现 ====================

// 去除重复课的辅助函数
std::vector<GanZhiKe> SanChuan::remove_duplicate_lessons(const std::vector<GanZhiKe>& lessons) const {
    std::vector<GanZhiKe> result;
    std::vector<TianGan> gan_list;
    std::vector<DiZhi> zhi_list;
    
    for (const auto& lesson : lessons) {
        if (lesson.is_gan_zhi) {
            if (std::find(gan_list.begin(), gan_list.end(), lesson.gan) == gan_list.end()) {
                gan_list.push_back(lesson.gan);
                result.push_back(lesson);
            }
        } else {
            if (std::find(zhi_list.begin(), zhi_list.end(), lesson.lower_zhi) == zhi_list.end()) {
                zhi_list.push_back(lesson.lower_zhi);
                result.push_back(lesson);
            }
        }
    }
    
    return result;
}

// 查找有贼克的课（地支五行克天干五行）
std::vector<GanZhiKe> SanChuan::have_conquerors() const {
    std::vector<GanZhiKe> result;
    std::vector<GanZhiKe> lessons = {
        si_ke_.first, si_ke_.second, si_ke_.third, si_ke_.fourth
    };
    
    for (const auto& lesson : lessons) {
        WuXing upper_wx = ZhouYi::GanZhi::get_wu_xing(lesson.upper_zhi);
        WuXing lesson_wx = lesson.get_wu_xing();
        
        if (wu_xing_ke(upper_wx, lesson_wx)) {
            result.push_back(lesson);
        }
    }
    
    return remove_duplicate_lessons(result);
}

// 查找有克的课（天干五行克地支五行）
std::vector<GanZhiKe> SanChuan::have_overcomes() const {
    std::vector<GanZhiKe> result;
    std::vector<GanZhiKe> lessons = {
        si_ke_.first, si_ke_.second, si_ke_.third, si_ke_.fourth
    };
    
    for (const auto& lesson : lessons) {
        WuXing upper_wx = ZhouYi::GanZhi::get_wu_xing(lesson.upper_zhi);
        WuXing lesson_wx = lesson.get_wu_xing();
        
        if (wu_xing_ke(lesson_wx, upper_wx)) {
            result.push_back(lesson);
        }
    }
    
    return remove_duplicate_lessons(result);
}

// 贼克法取三传
std::array<DiZhi, 3> SanChuan::zei_ke() {
    auto conquerors = have_conquerors();
    
    if (!conquerors.empty()) {
        if (conquerors.size() == 1) {
            // 只有一个贼克课
            chu_chuan_ = conquerors[0].upper_zhi;
            zhong_chuan_ = tian_di_pan_[chu_chuan_];
            mo_chuan_ = tian_di_pan_[zhong_chuan_];
            ke_shi_.push_back("重审卦");
            return {chu_chuan_, zhong_chuan_, mo_chuan_};
        } else {
            // 多个贼克课，进入比用法
            return bi_yong(conquerors);
        }
    }
    
    auto overcomes = have_overcomes();
    
    if (!overcomes.empty()) {
        if (overcomes.size() == 1) {
            // 只有一个克课
            chu_chuan_ = overcomes[0].upper_zhi;
            zhong_chuan_ = tian_di_pan_[chu_chuan_];
            mo_chuan_ = tian_di_pan_[zhong_chuan_];
            ke_shi_.push_back("元首卦");
            return {chu_chuan_, zhong_chuan_, mo_chuan_};
        } else {
            // 多个克课，进入比用法
            return bi_yong(overcomes);
        }
    }
    
    // 没有贼克和克课，抛出异常
    throw std::runtime_error("不能用贼克取三传");
}

// 比用法取三传
std::array<DiZhi, 3> SanChuan::bi_yong(const std::vector<GanZhiKe>& lessons) {
    // 比用法：检查课的上神（天盘地支）与日干的阴阳是否相同
    std::vector<GanZhiKe> result;
    
    // 获取日干
    TianGan day_gan = si_ke_.first.is_gan_zhi ? si_ke_.first.gan : TianGan::Jia;
    // 日干的阴阳属性
    YinYang day_gan_yin_yang = get_yin_yang(day_gan);
    
    for (const auto& lesson : lessons) {
        // 课的上神（天盘地支）的阴阳属性
        YinYang upper_yin_yang = get_yin_yang(lesson.upper_zhi);
        if (day_gan_yin_yang == upper_yin_yang) {
            result.push_back(lesson);
        }
    }
    
    if (result.size() == 1) {
        // 只有一个符合比用条件的课 - 知一卦
        chu_chuan_ = result[0].upper_zhi;
        zhong_chuan_ = tian_di_pan_[chu_chuan_];
        mo_chuan_ = tian_di_pan_[zhong_chuan_];
        ke_shi_.push_back("比用");
        ke_shi_.push_back("知一卦");
        return {chu_chuan_, zhong_chuan_, mo_chuan_};
    } else if (result.empty()) {
        // 没有符合比用条件的课（俱不比），直接进入涉害法
        // 注意：这里不是比用，所以不添加"比用"标记
        return she_hai(lessons);
    } else {
        // 多个符合比用条件的课（多个俱比），直接进入涉害法
        // 注意：这里不是比用，所以不添加"比用"标记
        return she_hai(result);
    }
}

// 涉害法取三传
std::array<DiZhi, 3> SanChuan::she_hai(const std::vector<GanZhiKe>& lessons) {
    ke_shi_.push_back("涉害");  // 标记使用涉害法
    
    std::vector<std::pair<GanZhiKe, int>> harm_depths;
    
    for (const auto& lesson : lessons) {
        // 获取课的上神（天盘地支）所临的地盘地支
        DiZhi lin_di_pan = tian_di_pan_.lin(lesson.upper_zhi);
        int count = 0;
        
        // 从临地盘开始顺时针数到课的上神位置
        for (int i = 0; i < 12; ++i) {
            DiZhi b = lin_di_pan + i;
            if (b == lesson.upper_zhi) break;
            
            auto ji_gan_list = get_ji_gan(b);
            WuXing b_wx = ZhouYi::GanZhi::get_wu_xing(b);
            WuXing lesson_wx = lesson.get_wu_xing();
            WuXing upper_wx = ZhouYi::GanZhi::get_wu_xing(lesson.upper_zhi);
            
            // 判断是贼克还是克
            if (wu_xing_ke(upper_wx, lesson_wx)) {
                // 贼克：数沿途克我的地支和天干
                if (wu_xing_ke(b_wx, lesson_wx)) {
                    count++;
                }
                for (const auto& g : ji_gan_list) {
                    if (wu_xing_ke(ZhouYi::GanZhi::get_wu_xing(g), lesson_wx)) {
                        count++;
                    }
                }
            } else {
                // 克：数沿途我克的地支和天干
                if (wu_xing_ke(lesson_wx, b_wx)) {
                    count++;
                }
                for (const auto& g : ji_gan_list) {
                    if (wu_xing_ke(lesson_wx, ZhouYi::GanZhi::get_wu_xing(g))) {
                        count++;
                    }
                }
            }
        }
        
        harm_depths.emplace_back(lesson, count);
    }
    
    // 找出涉害深度最大的课
    int max_depth = 0;
    for (const auto& [_, depth] : harm_depths) {
        if (depth > max_depth) {
            max_depth = depth;
        }
    }
    
    std::vector<GanZhiKe> max_harm_lessons;
    for (const auto& [lesson, depth] : harm_depths) {
        if (depth == max_depth) {
            max_harm_lessons.push_back(lesson);
        }
    }
    
    if (max_harm_lessons.size() == 1) {
        chu_chuan_ = max_harm_lessons[0].upper_zhi;
        zhong_chuan_ = tian_di_pan_[chu_chuan_];
        mo_chuan_ = tian_di_pan_[zhong_chuan_];
        ke_shi_.push_back("涉害卦");
        return {chu_chuan_, zhong_chuan_, mo_chuan_};
    }
    
    // 涉害深度相同，看临地盘是否为孟神
    for (const auto& lesson : max_harm_lessons) {
        DiZhi lin_di_pan = tian_di_pan_.lin(lesson.upper_zhi);
        if (is_meng(lin_di_pan)) {
            chu_chuan_ = lesson.upper_zhi;
            zhong_chuan_ = tian_di_pan_[chu_chuan_];
            mo_chuan_ = tian_di_pan_[zhong_chuan_];
            ke_shi_.push_back("见机卦");
            return {chu_chuan_, zhong_chuan_, mo_chuan_};
        }
    }
    
    // 没有孟神，看临地盘是否为仲神
    for (const auto& lesson : max_harm_lessons) {
        DiZhi lin_di_pan = tian_di_pan_.lin(lesson.upper_zhi);
        if (is_zhong(lin_di_pan)) {
            chu_chuan_ = lesson.upper_zhi;
            zhong_chuan_ = tian_di_pan_[chu_chuan_];
            mo_chuan_ = tian_di_pan_[zhong_chuan_];
            ke_shi_.push_back("察微卦");
            return {chu_chuan_, zhong_chuan_, mo_chuan_};
        }
    }
    
    // 取阳神
    if (si_ke_.first.is_yang()) {
        chu_chuan_ = si_ke_.gan_yang_shen;
        zhong_chuan_ = tian_di_pan_[chu_chuan_];
        mo_chuan_ = tian_di_pan_[zhong_chuan_];
        ke_shi_.push_back("复等卦");
        return {chu_chuan_, zhong_chuan_, mo_chuan_};
    } else {
        chu_chuan_ = si_ke_.zhi_yang_shen;
        zhong_chuan_ = tian_di_pan_[chu_chuan_];
        mo_chuan_ = tian_di_pan_[zhong_chuan_];
        ke_shi_.push_back("复等卦");
        return {chu_chuan_, zhong_chuan_, mo_chuan_};
    }
}

// 遥克法取三传
std::array<DiZhi, 3> SanChuan::yao_ke() {
    if (is_ba_zhuan_day(si_ke_.first)) {
        throw std::runtime_error("八专日不用遥克");
    }
    
    std::vector<GanZhiKe> overcomes;
    // 获取日干五行
    TianGan day_gan = si_ke_.first.is_gan_zhi ? si_ke_.first.gan : TianGan::Jia;
    WuXing day_gan_wx = ZhouYi::GanZhi::get_wu_xing(day_gan);
    
    // 检查第二、三、四课的下神（地支）是否克日干
    WuXing second_wx = ZhouYi::GanZhi::get_wu_xing(si_ke_.second.lower_zhi);
    WuXing third_wx = ZhouYi::GanZhi::get_wu_xing(si_ke_.third.lower_zhi);
    WuXing fourth_wx = ZhouYi::GanZhi::get_wu_xing(si_ke_.fourth.lower_zhi);
    
    if (wu_xing_ke(second_wx, day_gan_wx)) {
        overcomes.push_back(si_ke_.second);
    }
    if (wu_xing_ke(third_wx, day_gan_wx)) {
        overcomes.push_back(si_ke_.third);
    }
    if (wu_xing_ke(fourth_wx, day_gan_wx)) {
        overcomes.push_back(si_ke_.fourth);
    }
    
    // 如果没有克日干的，则检查日干是否克第二、三、四课的下神
    if (overcomes.empty()) {
        if (wu_xing_ke(day_gan_wx, second_wx)) {
            overcomes.push_back(si_ke_.second);
        }
        if (wu_xing_ke(day_gan_wx, third_wx)) {
            overcomes.push_back(si_ke_.third);
        }
        if (wu_xing_ke(day_gan_wx, fourth_wx)) {
            overcomes.push_back(si_ke_.fourth);
        }
    }
    
    if (overcomes.empty()) {
        throw std::runtime_error("无遥克，不能用遥克取三传");
    }
    
    overcomes = remove_duplicate_lessons(overcomes);
    
    if (overcomes.size() == 1) {
        chu_chuan_ = overcomes[0].upper_zhi;
        zhong_chuan_ = tian_di_pan_[chu_chuan_];
        mo_chuan_ = tian_di_pan_[zhong_chuan_];
        ke_shi_.push_back("遥克卦");
        return {chu_chuan_, zhong_chuan_, mo_chuan_};
    } else {
        ke_shi_.push_back("遥克卦");
        return bi_yong(overcomes);
    }
}

// 昴星法取三传
std::array<DiZhi, 3> SanChuan::ang_xing() {
    std::vector<GanZhiKe> all_lessons = {
        si_ke_.first, si_ke_.second, si_ke_.third, si_ke_.fourth
    };
    all_lessons = remove_duplicate_lessons(all_lessons);
    
    if (all_lessons.size() != 4) {
        throw std::runtime_error("课不备，不能用昴星取三传");
    }
    
    if (si_ke_.first.is_yang()) {
        // 阳日：虎视卦
        // 初传：酉地盘位置对应的天盘地支
        chu_chuan_ = tian_di_pan_[DiZhi::You];
        zhong_chuan_ = si_ke_.zhi_yang_shen;
        mo_chuan_ = si_ke_.gan_yang_shen;
        ke_shi_.push_back("虎视卦");
    } else {
        // 阴日：冬蛇掩目
        // 初传：酉在天盘上所临的地盘地支
        chu_chuan_ = tian_di_pan_.lin(DiZhi::You);
        zhong_chuan_ = si_ke_.gan_yang_shen;
        mo_chuan_ = si_ke_.zhi_yang_shen;
        ke_shi_.push_back("冬蛇掩目");
    }
    
    return {chu_chuan_, zhong_chuan_, mo_chuan_};
}

// 别责法取三传
std::array<DiZhi, 3> SanChuan::bie_ze() {
    std::vector<GanZhiKe> all_lessons = {
        si_ke_.first, si_ke_.second, si_ke_.third, si_ke_.fourth
    };
    all_lessons = remove_duplicate_lessons(all_lessons);
    
    if (all_lessons.size() == 4) {
        throw std::runtime_error("四课全备，不能用别责取三传");
    }
    
    if (all_lessons.size() != 3) {
        throw std::runtime_error("用别责用于三课备取三传");
    }
    
    if (si_ke_.first.is_gan_zhi && si_ke_.first.is_yang()) {
        // 阳日
        TianGan target_gan = si_ke_.first.gan + 5;
        chu_chuan_ = tian_di_pan_[get_ji_gong(target_gan)];
    } else {
        // 阴日
        chu_chuan_ = si_ke_.fourth.upper_zhi + 4;
    }
    
    zhong_chuan_ = si_ke_.gan_yang_shen;
    mo_chuan_ = zhong_chuan_;
    ke_shi_.push_back("别责卦");
    return {chu_chuan_, zhong_chuan_, mo_chuan_};
}

// 八专法取三传
std::array<DiZhi, 3> SanChuan::ba_zhuan() {
    if (!is_ba_zhuan_day(si_ke_.first)) {
        throw std::runtime_error("不是八专日");
    }
    
    if (si_ke_.first.is_yang()) {
        // 阳日
        chu_chuan_ = si_ke_.gan_yang_shen + 2;
    } else {
        // 阴日
        DiZhi zhi_yin_shen = si_ke_.fourth.upper_zhi + 6;
        chu_chuan_ = zhi_yin_shen + (-2);
    }
    
    zhong_chuan_ = si_ke_.gan_yang_shen;
    mo_chuan_ = si_ke_.gan_yang_shen;
    ke_shi_.push_back("八专卦");
    return {chu_chuan_, zhong_chuan_, mo_chuan_};
}

// 伏吟法取三传
std::array<DiZhi, 3> SanChuan::fu_yin() {
    if (!si_ke_.first.is_gan_zhi) {
        throw std::runtime_error("第一课不是干支课，无法判断伏吟");
    }
    
    if (si_ke_.first.gan == TianGan::Gui) {
        // 六癸日
        chu_chuan_ = tian_di_pan_[DiZhi::Chou];
        zhong_chuan_ = DiZhi::Xu;
        mo_chuan_ = DiZhi::Wei;
        ke_shi_.push_back(si_ke_.first.is_yang() ? "自任卦-伏吟-六癸日" : "自信卦-伏吟-六癸日");
    } else if (si_ke_.first.gan == TianGan::Yi) {
        // 六乙日
        chu_chuan_ = DiZhi::Chen;
        zhong_chuan_ = si_ke_.zhi_yang_shen;
        
        // 查找中传的刑神
        mo_chuan_ = zhong_chuan_;
        for (int i = 0; i < 12; ++i) {
            DiZhi candidate = static_cast<DiZhi>(i);
            if (is_xing(zhong_chuan_, candidate)) {
                mo_chuan_ = candidate;
                break;
            }
        }
        
        // 中传自刑，则以中传冲位为末传
        if (mo_chuan_ == zhong_chuan_) {
            mo_chuan_ = zhong_chuan_ + 6;
        }
        
        ke_shi_.push_back(si_ke_.first.is_yang() ? "自任卦-伏吟-六乙日" : "自信卦-伏吟-六乙日");
    } else {
        if (si_ke_.first.is_yang()) {
            // 刚日
            chu_chuan_ = si_ke_.gan_yang_shen;
            
            // 查找初传的刑神
            zhong_chuan_ = chu_chuan_;
            for (int i = 0; i < 12; ++i) {
                DiZhi candidate = static_cast<DiZhi>(i);
                if (is_xing(chu_chuan_, candidate)) {
                    zhong_chuan_ = candidate;
                    break;
                }
            }
            
            // 初传自刑，则以日支支上神为中传
            if (zhong_chuan_ == chu_chuan_) {
                zhong_chuan_ = si_ke_.zhi_yang_shen;
            }
            
            // 查找中传的刑神
            mo_chuan_ = zhong_chuan_;
            for (int i = 0; i < 12; ++i) {
                DiZhi candidate = static_cast<DiZhi>(i);
                if (is_xing(zhong_chuan_, candidate)) {
                    mo_chuan_ = candidate;
                    break;
                }
            }
            
            // 中传自刑，则以中传冲位为末传
            if (mo_chuan_ == zhong_chuan_) {
                mo_chuan_ = zhong_chuan_ + 6;
            }
            
            ke_shi_.push_back("自任卦-伏吟-刚日");
        } else {
            // 柔日
            chu_chuan_ = si_ke_.zhi_yang_shen;
            
            // 查找初传的刑神
            zhong_chuan_ = chu_chuan_;
            for (int i = 0; i < 12; ++i) {
                DiZhi candidate = static_cast<DiZhi>(i);
                if (is_xing(chu_chuan_, candidate)) {
                    zhong_chuan_ = candidate;
                    break;
                }
            }
            
            // 初传自刑，则以干上神为中传
            if (zhong_chuan_ == chu_chuan_) {
                zhong_chuan_ = si_ke_.gan_yang_shen;
            }
            
            // 查找刑神作为末传
            mo_chuan_ = zhong_chuan_;
            for (int i = 0; i < 12; ++i) {
                DiZhi candidate = static_cast<DiZhi>(i);
                if (is_xing(zhong_chuan_, candidate)) {
                    mo_chuan_ = candidate;
                    break;
                }
            }
            
            // 末传自刑，则以末传冲位为末传
            if (mo_chuan_ == zhong_chuan_) {
                mo_chuan_ = zhong_chuan_ + 6;
            }
            
            ke_shi_.push_back("自信卦-伏吟-柔日");
        }
    }
    
    return {chu_chuan_, zhong_chuan_, mo_chuan_};
}

// 返吟法取三传
std::array<DiZhi, 3> SanChuan::fan_yin() {
    try {
        return zei_ke();
    } catch (const std::exception&) {
        // 驿马计算
        DiZhi branch = si_ke_.fourth.upper_zhi;
        for (int i = 0; i < 3; ++i) {
            if (branch == DiZhi::Yin || branch == DiZhi::Shen || 
                branch == DiZhi::Si || branch == DiZhi::Hai) {
                chu_chuan_ = branch + 6;
                break;
            }
            branch = branch + 4;
        }
        
        zhong_chuan_ = si_ke_.zhi_yang_shen;
        mo_chuan_ = si_ke_.gan_yang_shen;
        ke_shi_.push_back("无依卦");
        return {chu_chuan_, zhong_chuan_, mo_chuan_};
    }
}

// 判断是否为孟神
bool SanChuan::is_meng(DiZhi zhi) const {
    return zhi == DiZhi::Yin || zhi == DiZhi::Si || 
           zhi == DiZhi::Shen || zhi == DiZhi::Hai;
}

// 判断是否为仲神
bool SanChuan::is_zhong(DiZhi zhi) const {
    return zhi == DiZhi::Zi || zhi == DiZhi::Mao || 
           zhi == DiZhi::Wu || zhi == DiZhi::You;
}

// 判断是否为八专日
bool SanChuan::is_ba_zhuan_day(const GanZhiKe& ke) const {
    if (!ke.is_gan_zhi) return false;
    
    static const std::array<std::pair<TianGan, DiZhi>, 8> ba_zhuan_list = {{
        {TianGan::Jia, DiZhi::Yin},
        {TianGan::Yi, DiZhi::Mao},
        {TianGan::Bing, DiZhi::Wu},
        {TianGan::Ding, DiZhi::Wei},
        {TianGan::Geng, DiZhi::Shen},
        {TianGan::Xin, DiZhi::You},
        {TianGan::Ren, DiZhi::Zi},
        {TianGan::Gui, DiZhi::Chou}
    }};
    
    for (const auto& [gan, zhi] : ba_zhuan_list) {
        if (ke.gan == gan && ke.upper_zhi == zhi) {
            return true;
        }
    }
    
    return false;
}

// 判断是否为伏吟课
bool SanChuan::is_fu_yin_lesson() const {
    for (int i = 0; i < 12; ++i) {
        DiZhi current = static_cast<DiZhi>(i);
        if (tian_di_pan_[current] != current) {
            return false;
        }
    }
    return true;
}

// 判断是否为返吟课
bool SanChuan::is_fan_yin_lesson() const {
    for (int i = 0; i < 12; ++i) {
        DiZhi current = static_cast<DiZhi>(i);
        DiZhi opposite = current + 6;
        if (tian_di_pan_[current] != opposite) {
            return false;
        }
    }
    return true;
}

// 构造函数：计算三传
SanChuan::SanChuan(const TianDiPan& tdp, const SiKe& sk)
    : tian_di_pan_(tdp), si_ke_(sk) {
    
    std::array<DiZhi, 3> result;
    std::string jiu_zong_men;  // 九宗门名称
    
    // 优先处理伏吟课
    if (is_fu_yin_lesson()) {
        jiu_zong_men = "伏吟";
        result = fu_yin();
    }
    // 其次处理返吟课
    else if (is_fan_yin_lesson()) {
        jiu_zong_men = "返吟";
        result = fan_yin();
    }
    // 正常课式处理流程
    else {
        try {
            // 1. 先尝试贼克法（含比用、涉害）
            jiu_zong_men = "贼克";
            result = zei_ke();
        } catch (const std::exception&) {
            try {
                // 2. 尝试遥克法
                jiu_zong_men = "遥克";
                result = yao_ke();
            } catch (const std::exception&) {
                try {
                    // 3. 尝试昴星法
                    jiu_zong_men = "昴星";
                    result = ang_xing();
                } catch (const std::exception&) {
                    try {
                        // 4. 尝试别责法
                        jiu_zong_men = "别责";
                        result = bie_ze();
                    } catch (const std::exception&) {
                        try {
                            // 5. 处理八专日
                            jiu_zong_men = "八专";
                            result = ba_zhuan();
                        } catch (const std::exception&) {
                            throw std::runtime_error("所有方法均无法确定三传");
                        }
                    }
                }
            }
        }
    }
    
    chu_chuan_ = result[0];
    zhong_chuan_ = result[1];
    mo_chuan_ = result[2];
    
    // 将九宗门名称插入到课式列表的开头（如果不为空）
    if (!jiu_zong_men.empty()) {
        ke_shi_.insert(ke_shi_.begin(), jiu_zong_men);
    }
}

// ==================== DaLiuRenResult 实现 ====================

nlohmann::json DaLiuRenResult::to_json() const {
    nlohmann::json j;
    
    j["ba_zi"] = ba_zi;
    j["yue_jiang"] = std::string(Mapper::to_zh(yue_jiang));
    j["gui_ren"] = std::string(Mapper::to_zh(gui_ren));
    j["is_day"] = is_day;
    
    j["si_ke"] = {
        {"first", si_ke.first.to_string()},
        {"second", si_ke.second.to_string()},
        {"third", si_ke.third.to_string()},
        {"fourth", si_ke.fourth.to_string()}
    };
    
    j["san_chuan"] = {
        {"chu_chuan", std::string(Mapper::to_zh(san_chuan.get_chu_chuan()))},
        {"zhong_chuan", std::string(Mapper::to_zh(san_chuan.get_zhong_chuan()))},
        {"mo_chuan", std::string(Mapper::to_zh(san_chuan.get_mo_chuan()))},
        {"ke_shi", san_chuan.get_ke_shi()}
    };
    
    return j;
}

std::string DaLiuRenResult::to_string() const {
    return fmt::format(
        "【大六壬排盘】\n"
        "八字: {}\n"
        "月将: {}\n"
        "贵人: {}\n"
        "昼夜: {}\n"
        "四课: {} {} {} {}\n"
        "三传: {} -> {} -> {}\n"
        "课式: {}",
        ba_zi,
        Mapper::to_zh(yue_jiang),
        Mapper::to_zh(gui_ren),
        is_day ? "白天" : "夜晚",
        si_ke.first.to_string(),
        si_ke.second.to_string(),
        si_ke.third.to_string(),
        si_ke.fourth.to_string(),
        Mapper::to_zh(san_chuan.get_chu_chuan()),
        Mapper::to_zh(san_chuan.get_zhong_chuan()),
        Mapper::to_zh(san_chuan.get_mo_chuan()),
        fmt::join(san_chuan.get_ke_shi(), ", ")
    );
}

// ==================== DaLiuRenEngine 实现 ====================

DaLiuRenResult DaLiuRenEngine::pai_pan(int year, int month, int day, int hour) {
    // 获取八字
    BaZi ba_zi = BaZi::from_solar(year, month, day, hour);
    
    // 获取农历月份
    auto solar_time = tyme::SolarTime::from_ymd_hms(year, month, day, hour, 0, 0);
    auto lunar_hour = solar_time.get_lunar_hour();
    auto lunar_month = lunar_hour.get_lunar_day().get_lunar_month();
    int lunar_month_num = lunar_month.get_month();
    
    // 计算月将
    DiZhi yue_jiang = get_yue_jiang(lunar_month_num);
    
    // 计算时辰地支
    DiZhi hour_zhi = static_cast<DiZhi>((hour + 1) / 2 % 12);
    
    // 判断昼夜
    bool is_day = is_daytime(hour_zhi);
    
    // 获取贵人
    DiZhi gui_ren = get_gui_ren(ba_zi.day.gan, is_day);
    
    // 判断神将顺布还是逆布
    bool is_clockwise = (gui_ren == DiZhi::Hai) || 
                       (static_cast<int>(gui_ren) >= static_cast<int>(DiZhi::Zi) && 
                        static_cast<int>(gui_ren) <= static_cast<int>(DiZhi::Chen));
    
    // 创建天地盘
    TianDiPan tian_di_pan(yue_jiang, hour_zhi, gui_ren, is_clockwise);
    
    // 计算四课
    // 第一课：干上神
    DiZhi gan_gong = get_ji_gong(ba_zi.day.gan);
    DiZhi first_upper = tian_di_pan[gan_gong];
    GanZhiKe first_ke(ba_zi.day.gan, first_upper);
    
    // 第二课：第一课上神在天盘对应位置
    DiZhi second_upper = tian_di_pan[first_upper];
    GanZhiKe second_ke(first_upper, second_upper);
    
    // 第三课：支上神
    DiZhi third_upper = tian_di_pan[ba_zi.day.zhi];
    GanZhiKe third_ke(ba_zi.day.zhi, third_upper);
    
    // 第四课：第三课上神在天盘对应位置
    DiZhi fourth_upper = tian_di_pan[third_upper];
    GanZhiKe fourth_ke(third_upper, fourth_upper);
    
    // 创建四课对象
    SiKe si_ke(first_ke, second_ke, third_ke, fourth_ke, first_upper, third_upper);
    
    // 计算三传
    SanChuan san_chuan(tian_di_pan, si_ke);
    
    // 计算神煞
    ShenSha::ShenShaEngine shen_sha_engine(ba_zi, ba_zi.day.zhi);
    auto shen_sha_result = shen_sha_engine.calculate();
    
    // 判定卦体
    auto gua_ti = GuaTi::GuaTiEngine::judge_all(
        ba_zi, yue_jiang, tian_di_pan, si_ke, san_chuan
    );
    
    return DaLiuRenResult(ba_zi, yue_jiang, gui_ren, is_day, 
                          tian_di_pan, si_ke, san_chuan, shen_sha_result, gua_ti);
}

DaLiuRenResult DaLiuRenEngine::pai_pan_lunar(int year, int month, int day, int hour) {
    // 获取八字
    BaZi ba_zi = BaZi::from_lunar(year, month, day, hour);
    
    return pai_pan_from_bazi(ba_zi, month, hour);
}

DaLiuRenResult DaLiuRenEngine::pai_pan_from_bazi(const BaZi& ba_zi, int lunar_month, int hour) {
    // 计算月将
    DiZhi yue_jiang = get_yue_jiang(lunar_month);
    
    // 计算时辰地支
    DiZhi hour_zhi = static_cast<DiZhi>((hour + 1) / 2 % 12);
    
    // 判断昼夜
    bool is_day = is_daytime(hour_zhi);
    
    // 获取贵人
    DiZhi gui_ren = get_gui_ren(ba_zi.day.gan, is_day);
    
    // 判断神将顺布还是逆布
    bool is_clockwise = (gui_ren == DiZhi::Hai) || 
                       (static_cast<int>(gui_ren) >= static_cast<int>(DiZhi::Zi) && 
                        static_cast<int>(gui_ren) <= static_cast<int>(DiZhi::Chen));
    
    // 创建天地盘
    TianDiPan tian_di_pan(yue_jiang, hour_zhi, gui_ren, is_clockwise);
    
    // 计算四课
    DiZhi gan_gong = get_ji_gong(ba_zi.day.gan);
    DiZhi first_upper = tian_di_pan[gan_gong];
    GanZhiKe first_ke(ba_zi.day.gan, first_upper);
    
    DiZhi second_upper = tian_di_pan[first_upper];
    GanZhiKe second_ke(first_upper, second_upper);
    
    DiZhi third_upper = tian_di_pan[ba_zi.day.zhi];
    GanZhiKe third_ke(ba_zi.day.zhi, third_upper);
    
    DiZhi fourth_upper = tian_di_pan[third_upper];
    GanZhiKe fourth_ke(third_upper, fourth_upper);
    
    SiKe si_ke(first_ke, second_ke, third_ke, fourth_ke, first_upper, third_upper);
    
    // 计算三传
    SanChuan san_chuan(tian_di_pan, si_ke);
    
    // 计算神煞
    ShenSha::ShenShaEngine shen_sha_engine(ba_zi, ba_zi.day.zhi);
    auto shen_sha_result = shen_sha_engine.calculate();
    
    // 判定卦体
    auto gua_ti = GuaTi::GuaTiEngine::judge_all(
        ba_zi, yue_jiang, tian_di_pan, si_ke, san_chuan
    );
    
    return DaLiuRenResult(ba_zi, yue_jiang, gui_ren, is_day, 
                          tian_di_pan, si_ke, san_chuan, shen_sha_result, gua_ti);
}

} // namespace ZhouYi::DaLiuRen

