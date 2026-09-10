// C++23 Module - 八字分析服务门面
// 主模块只公开用例级函数；数据模型分别由公共、子平和盲派契约维护。
export module ZhouYi.BaZiAnalysis;

export import ZhouYi.BaZiAnalysis.Common;
export import ZhouYi.BaZiAnalysis.MangPai.Contract;
export import ZhouYi.BaZiAnalysis.ZiPing.Contract;
import std;

export namespace ZhouYi::BaZiAnalysis {

/** @brief 按默认出生上下文分析原局。 */
AnalysisResult analyze(const BaZi &chart, const AnalysisConfig &config = {});

/** @brief 分析原局并评估所给岁运柱。 */
AnalysisResult analyze(const BaZi &chart, const std::vector<Pillar> &fortunes,
                       const AnalysisConfig &config = {});

/** @brief 使用精确出生上下文分析原局。 */
AnalysisResult analyze(const BaZi &chart, const BirthContext &birth_context,
                       const AnalysisConfig &config = {});

/** @brief 补全出生时刻对应的节令与人元司令。 */
BirthContext resolve_birth_context(const BirthContext &input);

/** @brief 使用出生上下文分析原局并评估岁运柱。 */
AnalysisResult analyze(const BaZi &chart, const BirthContext &birth_context,
                       const std::vector<Pillar> &fortunes,
                       const AnalysisConfig &config = {});

/** @brief 基于子平原局结论评估单个大运或流年柱。 */
FortuneImpact analyze_fortune(const Pillar &fortune,
                              const AnalysisResult &analysis);

/** @brief 联合分析同一时点的大运、流年和流月。 */
TransitAnalysis analyze_transit(const TransitContext &context,
                                const AnalysisResult &analysis);

/** @brief 标记批量岁运样本中的最深单线。 */
void annotate_sample_extremes(std::vector<TransitAnalysis> &analyses);

} // namespace ZhouYi::BaZiAnalysis
