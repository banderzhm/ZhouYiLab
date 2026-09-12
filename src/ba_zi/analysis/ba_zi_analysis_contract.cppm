// C++23 Module - 八字分析统一契约
// 只放各流派共用的出生排盘条件、命理依据和配置，不承载具体流派结论。
export module ZhouYi.BaZiAnalysis.Contract;

import ZhouYi.GanZhi;
import std;

export namespace ZhouYi::BaZiAnalysis {

using ZhouYi::GanZhi::TianGan;

/** 单条可追溯命理依据。 */
struct MingLiBasis {
  std::string rule;     ///< 稳定规则标识，供追踪和程序消费。
  std::string subject;  ///< 规则作用的干支、柱位或候选对象。
  std::string relation; ///< 命中的命理关系或规则状态。
  double points{};      ///< 对所属评价项的有符号贡献。
  std::string reason;   ///< 面向中文阅读的判定依据。
};

/** 出生时刻、节令及人元司令排定条件。 */
struct BirthContext {
  bool has_exact_time{};    ///< 是否具有可用于节气分日的精确公历时刻。
  int year{};               ///< 公历年。
  int month{};              ///< 公历月，范围 1～12。
  int day{};                ///< 公历日，范围 1～31。
  int hour{};               ///< 小时，范围 0～23。
  int minute{};             ///< 分钟，范围 0～59。
  int second{};             ///< 秒，范围 0～59。
  bool is_male{true};       ///< 性别，供岁运排法使用。
  std::string previous_jie; ///< 出生时刻之前最近的“节”。
  double days_since_jie{};  ///< 出生时刻距前一节的天数，可含小数。
  std::string command_table_version{"ziping-renyuan-v1"}; ///< 司令表版本。
  std::optional<TianGan> human_command; ///< 按分日表得到的人元司令。
  std::string human_command_reason;     ///< 司令判定或缺失原因。
};

/** 统一入口支持的八字分析流派。 */
enum class AnalysisMethod {
  Ziping,      ///< 子平法：月令取格、调候和格局病药。
  BlindSchool, ///< 盲派法：宾主、体用、做功和宫位取象。
};

/** 子平数值模型的权重、折减系数和阈值配置。 */
struct AnalysisConfig {
  double stem_weight{1.0};                     ///< 透干基础权重。
  double main_hidden_stem_weight{1.0};         ///< 地支本气权重。
  double middle_hidden_stem_weight{0.6};       ///< 地支中气权重。
  double residual_hidden_stem_weight{0.3};     ///< 地支余气权重。
  double month_branch_multiplier{1.5};         ///< 月令藏干季节倍数。
  double clashed_root_multiplier{0.5};         ///< 受冲根气保留系数。
  double low_capacity_threshold{40.0};         ///< 低承载能力复核阈值。
  double review_score_gap{5.0};                ///< 候选差距复核阈值。
  double useful_god_effective_threshold{55.0}; ///< 用神最低有效分。
  double kong_wang_root_multiplier{0.5};       ///< 旬空根气保留系数。
  double climate_sufficient_power{1.0};        ///< 调候已足最低力量。
  double climate_excessive_percent{35.0};      ///< 调候太过占比阈值。
  double adjacent_arch_weight{0.25};           ///< 紧贴虚拱权重。
  double distant_arch_weight{0.10};            ///< 隔位虚拱权重。
  double adjacent_stem_combine_penalty{12.0};  ///< 紧贴合绊折减。
  double distant_stem_combine_penalty{6.0};    ///< 隔位合绊折减。
};

/** 统一分析请求。 */
struct AnalysisRequest {
  AnalysisMethod method{AnalysisMethod::Ziping}; ///< 本次采用的分析流派。
  std::optional<BirthContext> birth_context;     ///< 可选出生时间覆盖值。
  AnalysisConfig config;                         ///< 本次子平参数配置。
};

} // namespace ZhouYi::BaZiAnalysis
