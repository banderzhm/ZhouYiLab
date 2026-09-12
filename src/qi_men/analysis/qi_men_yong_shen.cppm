/**
 * @file qi_men_yong_shen.cppm
 * @brief 奇门占类用神取宫策略接口。
 */
export module ZhouYi.QiMen.Analysis.YongShen;

import ZhouYi.QiMen.Analysis.Contract;
import ZhouYi.QiMen;
import std;

export namespace ZhouYi::QiMenAnalysis {

/** 按日干、时干、年命及占类专用门星选取用神落宫。 */
std::vector<YongShenSelection>
select_yong_shen(const ZhouYi::QiMen::QiMenPan &pan,
                 const AnalysisRequest &request);

/** 判定日干主宫与时干事宫的五行生克。 */
PalaceRelation compare_main_guest(ZhouYi::QiMen::Palace host,
                                  ZhouYi::QiMen::Palace guest);

} // namespace ZhouYi::QiMenAnalysis
