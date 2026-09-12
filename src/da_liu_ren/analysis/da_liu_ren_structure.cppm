// C++23 Module - 大六壬课传结构分析
export module ZhouYi.DaLiuRenAnalysis.Structure;

export import ZhouYi.DaLiuRenAnalysis.Contract;

import ZhouYi.DaLiuRen;
import std;

export namespace ZhouYi::DaLiuRenAnalysis {

/** @brief 分析四课上下神、天将及发用来源。 */
std::vector<LessonAnalysis>
analyze_lessons(const ZhouYi::DaLiuRen::DaLiuRenResult &pan);

/** @brief 分析初中末三传的六亲、天将、旺衰、空亡和递进关系。 */
std::vector<TransmissionAnalysis>
analyze_transmissions(const ZhouYi::DaLiuRen::DaLiuRenResult &pan);

/** @brief 分析本命、行年所乘上神、天将、六亲、空亡及其与三传的作用。 */
std::vector<PersonalMarkerAnalysis>
analyze_personal_markers(const ZhouYi::DaLiuRen::DaLiuRenResult &pan,
                         const AnalysisRequest &request);

/** @brief 依据排盘课式识别九宗门。 */
JiuZongMen detect_method(const ZhouYi::DaLiuRen::SanChuan &san_chuan);

/** @brief 生成八门占断；变体门在本命、行年缺失时明确留空。 */
std::vector<BaMenAnalysis> analyze_eight_doors(
    const ZhouYi::DaLiuRen::DaLiuRenResult &pan, const AnalysisRequest &request,
    const std::vector<LessonAnalysis> &lessons,
    const std::vector<TransmissionAnalysis> &transmissions,
    const std::vector<PersonalMarkerAnalysis> &personal_markers);

/** @brief 将现有课体结果转换为带依据的分析条目。 */
std::vector<PatternAnalysis>
analyze_patterns(const ZhouYi::DaLiuRen::DaLiuRenResult &pan);

/** @brief 分析十二宫天地盘上下支神的生克、刑冲合害与半合。 */
std::vector<PalaceAnalysis>
analyze_palaces(const ZhouYi::DaLiuRen::DaLiuRenResult &pan);

/** @brief 检查三传两两及三支整体的生克、刑冲合害、半合、三合与三会。 */
std::vector<BranchRelationAnalysis>
analyze_transmission_relations(const ZhouYi::DaLiuRen::DaLiuRenResult &pan);

/** @brief 联查天地盘、四课与三传的两两作用及跨层三合成局。 */
std::vector<BranchRelationAnalysis>
analyze_cross_layer_relations(const ZhouYi::DaLiuRen::DaLiuRenResult &pan,
                              const std::vector<LessonAnalysis> &lessons);

/**
 * @brief 生成五级应期候选，并说明阶段、迟速、候期层级、触发条件及限制。
 *
 * 未提供明确占限时只给时、日、月层级，不承诺唯一公历日期。
 */
std::vector<TimingCandidate>
analyze_timing(const ZhouYi::DaLiuRen::DaLiuRenResult &pan,
               const LeiShenSelection &lei_shen);

} // namespace ZhouYi::DaLiuRenAnalysis
