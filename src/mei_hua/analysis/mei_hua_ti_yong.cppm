/**
 * @file mei_hua_ti_yong.cppm
 * @brief 梅花易数旺衰与体党用党推演接口。
 */
export module ZhouYi.MeiHua.Analysis.TiYong;

export import ZhouYi.MeiHua.Analysis.Contract;
import ZhouYi.GanZhi;
import std;

export namespace ZhouYi::MeiHuaAnalysis {

/** 依农历月令判定某五行的旺相休囚死。 */
SeasonalStrength assess_seasonal_strength(ZhouYi::GanZhi::WuXing element,
                                          int lunar_month);

/** 将旺相休囚死换算为仅供体用力量比较的有效系数。 */
double seasonal_force_factor(SeasonalStrength strength);

/** 汇合体卦、本用、上下互卦和变用，完成体党用党计力。 */
PartyBalance
build_party_balance(const ZhouYi::MeiHua::MeiHuaPan &pan,
                    const TrigramReading &ben_yong,
                    const std::vector<TrigramReading> &hu_influences,
                    const TrigramReading &bian_influence);

} // namespace ZhouYi::MeiHuaAnalysis
