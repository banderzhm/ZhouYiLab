// C++23 Module - 八字分析引擎接口
// 只声明稳定的数据契约和公共门面。
// 主流程位于 ba_zi_analysis.cpp；关系识别、岁运策略与结果展示分别由独立 cppm +
// cpp 内部模块实现。
export module ZhouYi.BaZiAnalysis;

import nlohmann.json;
import ZhouYi.BaZiBase;
import ZhouYi.GanZhi;
import ZhouYi.ZhMapper;
import std;

export namespace ZhouYi::BaZiAnalysis {

using ZhouYi::BaZiBase::BaZi;
using ZhouYi::BaZiBase::Pillar;
using ZhouYi::GanZhi::DiZhi;
using ZhouYi::GanZhi::ShiShen;
using ZhouYi::GanZhi::TianGan;
using ZhouYi::GanZhi::WuXing;

/** 正格判定所处的确认阶段。 */
enum class PatternStatus {
  Confirmed,   ///< 取格与成格条件均已确认。
  Candidate,   ///< 格局方向可立，但尚需继续验证。
  NeedsReview, ///< 条件冲突或证据不足，需要复核。
};

/** 特殊格局的成立、候选、复核或排除状态。 */
enum class SpecialPatternStatus {
  Confirmed,   ///< 特殊格局条件成立。
  Candidate,   ///< 接近成立，但仍有关键条件待验。
  NeedsReview, ///< 边界情形，需要人工复核。
  Excluded,    ///< 至少一个必要条件明确不成立。
};

/** 用神结论的可信状态，不等同于吉凶概率。 */
enum class UsefulGodStatus {
  Confirmed,   ///< 主路线已锁定且用神效力验证通过。
  Candidate,   ///< 已产生排序候选，但尚未达到定用条件。
  NeedsReview, ///< 候选冲突、分差过小或效力验证未通过。
};

/** 格局成败及病药是否足以救应的综合状态。 */
enum class PatternCondition {
  Established,      ///< 格局成立且无明显病处。
  DiseasedRelieved, ///< 格局有病，但药神足以救应。
  MedicineWeak,     ///< 格可立且见药，但药力不足。
  Broken,           ///< 破格条件明显，当前格局不能成立。
};

/** 子平正格、建禄、月劫与羊刃的具体格名。 */
enum class PatternKind {
  ZhengGuan, ///< 正官格。
  QiSha,     ///< 七杀格。
  ZhengCai,  ///< 正财格。
  PianCai,   ///< 偏财格。
  ZhengYin,  ///< 正印格。
  PianYin,   ///< 偏印格。
  ShiShen,   ///< 食神格。
  ShangGuan, ///< 伤官格。
  JianLu,    ///< 建禄格。
  YueJie,    ///< 月劫格。
  YangRen,   ///< 羊刃格。
};

/** 格局所属的大类，用于区分正格和禄刃体系。 */
enum class PatternType {
  Regular, ///< 月令官、杀、财、印、食伤等正格。
  JianLu,  ///< 建禄体系。
  YueJie,  ///< 月劫体系。
  YangRen, ///< 羊刃体系。
};

/** 从格、专旺格和化气格等特殊格局名称。 */
enum class SpecialPatternKind {
  FollowWealth, ///< 从财格。
  FollowKiller, ///< 从杀格。
  FollowOutput, ///< 从儿格。
  QuZhi,        ///< 木专旺之曲直格。
  YanShang,     ///< 火专旺之炎上格。
  JiaSe,        ///< 土专旺之稼穑格。
  CongGe,       ///< 金专旺之从革格。
  RunXia,       ///< 水专旺之润下格。
  HuaQi,        ///< 天干五合化气格。
};

/** 调候用字在原局中的实际可用状态。 */
enum class ClimateState {
  NotApplicable, ///< 当前规则未锁定单一调候用字。
  Missing,       ///< 所需调候天干在原局未见。
  Insufficient,  ///< 调候字虽见，但有效力量不足。
  Satisfied,     ///< 调候用字已备，无需重复补药。
  Excessive,     ///< 调候同类力量太过，继续增加反成偏性。
};

/** 调候相对于格局病药和扶抑分析的优先级。 */
enum class ClimateUrgency {
  None,      ///< 无需另取调候用字。
  Secondary, ///< 调候作为兼顾因素。
  Urgent,    ///< 调候优先于一般扶抑考虑。
};

/** 取格所依据的月令层次或分日司令。 */
enum class PatternBasis {
  MonthMainQi,   ///< 按月令本气取格。
  ExposedHidden, ///< 按月令藏干透出取格。
  HumanCommand,  ///< 按出生时刻的分日司令取格。
};

/** 日主综合强弱的离散等级。 */
enum class StrengthLevel {
  ExtremelyWeak,   ///< 日主极弱。
  Weak,            ///< 日主偏弱。
  Balanced,        ///< 日主中和。
  Strong,          ///< 日主偏强。
  ExtremelyStrong, ///< 日主极旺。
};

/** 天干在原局中的透藏层级。 */
enum class HiddenStemLevel {
  None,       ///< 非藏干，表示天干透出。
  MainQi,     ///< 地支本气。
  MiddleQi,   ///< 地支中气。
  ResidualQi, ///< 地支余气。
};

/** 合局、会局和虚拱结构的作用强度。 */
enum class RelationStrength {
  None,           ///< 尚未确定结构强度。
  Medium,         ///< 半合等中等结构。
  VeryStrong,     ///< 三合、三会等完整结构。
  Arching,        ///< 紧贴两支暗拱中神。
  DistantArching, ///< 隔位两支暗拱中神。
};

/** 用神候选采用的传统取用方法。 */
enum class UsefulGodMethod {
  SpecialPattern,     ///< 按从格、专旺格或化气格顺势取用。
  Climate,            ///< 以寒暖燥湿调候为主取用。
  PatternMedicine,    ///< 围绕格局之病选取药神。
  PatternCirculation, ///< 以疏通格局生克链为主取用。
  Balance,            ///< 按日主强弱进行扶抑取用。
};

/** 最终锁定的主取用路线，用于约束喜忌角色相容性。 */
enum class UsefulGodRoute {
  CandidatePrimaryEffect, ///< 尚未锁定专门路线，按候选主效应排序。
  FollowSpecialPattern,   ///< 特殊格局成立后顺其主势取用。
  OfficerProtectsWealth,  ///< 官杀制比劫以护财。
  OutputGeneratesWealth,  ///< 食伤泄比劫并转而生财。
};

/** 十神组合对格局的作用性质。 */
enum class ComboSeverity {
  Warning,   ///< 组合构成格局病处。
  Resolved,  ///< 病处存在，但已有制化或救应。
  Favorable, ///< 组合彼此相成，对格局有利。
};

/** 原局地支之间可识别的合冲刑害及组合关系。 */
enum class BranchRelationKind {
  Clash,           ///< 六冲。
  Combine,         ///< 六合。
  Harm,            ///< 六害。
  Punishment,      ///< 两支有方向的相刑。
  SelfPunishment,  ///< 辰、午、酉、亥自刑。
  ThreeHarmony,    ///< 三合局三支齐全。
  HalfHarmony,     ///< 含中神的三合半局。
  Arching,         ///< 生支与墓支虚拱中神。
  ThreeMeeting,    ///< 三会方局三支齐全。
  ThreePunishment, ///< 寅巳申或丑戌未三刑齐全。
};

/** 原局天干关系类型；当前实现为天干五合。 */
enum class StemRelationKind {
  FiveCombine, ///< 甲己、乙庚、丙辛、丁壬、戊癸五合。
};

/** 需要结合透藏和有效力量判断的十神组合。 */
enum class TenGodComboKind {
  MixedOfficerKiller,         ///< 官杀混杂。
  HurtingOfficerMeetsOfficer, ///< 伤官见官。
  OwlSeizesFood,              ///< 枭印夺食。
  KillerSealGenerate,         ///< 杀印相生。
  FoodControlsKiller,         ///< 食神制杀。
  HurtingOfficerWithSeal,     ///< 伤官配印。
  PeersSeizeWealth,           ///< 比劫夺财。
  WealthExcess,               ///< 财星力量偏多。
};

/** 单条可追溯的规则证据及其分数贡献。 */
struct Evidence {
  std::string rule;     ///< 稳定的规则标识，供追踪和程序消费。
  std::string subject;  ///< 当前证据作用的天干、地支、柱位或候选对象。
  std::string relation; ///< 命理关系或规则命中状态。
  double points{};      ///< 对所属评分项的有符号贡献值。
  std::string reason;   ///< 面向中文阅读的判定依据。
};

/** 单个五行的加权力量、归一化占比及证据。 */
struct ElementStat {
  WuXing element;                 ///< 当前统计的五行。
  double raw{};                   ///< 按配置权重累加、尚未归一化的力量。
  double percent{};               ///< 当前五行占全部五行力量的百分比。
  std::vector<Evidence> evidence; ///< 构成原始力量的逐项证据。
};

/** 一组地支关系及其方向、成局状态和影响说明。 */
struct BranchRelation {
  BranchRelationKind type{BranchRelationKind::Clash}; ///< 关系类型。
  DiZhi first;                                        ///< 第一参与地支。
  DiZhi second;                                       ///< 第二参与地支。
  int first_position{};           ///< 第一地支柱位：0年、1月、2日、3时。
  int second_position{};          ///< 第二地支柱位：0年、1月、2日、3时。
  bool symmetric{true};           ///< 关系是否双向成立。
  std::string impact;             ///< 对后续分析的中文影响说明。
  std::vector<Evidence> evidence; ///< 识别关系与判定有效性的证据。
  std::vector<DiZhi> members;     ///< 三合、三会、三刑等全部参与地支。
  bool effective{};               ///< 是否满足成局或成化条件。
  std::string direction;          ///< 刑的作用方向；对称关系通常为“双向”。
  RelationStrength strength{RelationStrength::None}; ///< 组合结构强度。
  std::optional<DiZhi> virtual_branch; ///< 拱合所缺中神；非拱合为空。
};

/** 一组天干关系及其合化有效性。 */
struct StemRelation {
  TianGan first;         ///< 第一参与天干。
  TianGan second;        ///< 第二参与天干。
  int first_position{};  ///< 第一天干柱位：0年、1月、2日、3时。
  int second_position{}; ///< 第二天干柱位：0年、1月、2日、3时。
  StemRelationKind type{StemRelationKind::FiveCombine}; ///< 天干关系类型。
  std::string transform;          ///< “仅合不化”或实际合化五行说明。
  bool effective{};               ///< 合化条件是否全部成立。
  std::vector<Evidence> evidence; ///< 合、化及破化条件的证据。
};

/** 单柱纳音名称和所属五行。 */
struct NayinInfo {
  std::string pillar; ///< 对应干支柱的中文文本。
  std::string name;   ///< 纳音名称。
  WuXing element;     ///< 纳音所属五行。
};

/** 调候需求、用字到位程度和有效力量。 */
struct ClimateResult {
  bool needed{};              ///< 当前月令和日主是否需要专门调候。
  bool present{};             ///< 至少一个指定调候天干是否在原局出现。
  WuXing element{WuXing::Tu}; ///< 调候所需五行；无需调候时不参与判断。
  std::vector<TianGan> preferred_stems; ///< 按优先次序排列的调候天干。
  std::vector<TianGan> present_stems;   ///< 原局实际出现的指定调候天干。
  ClimateState state{ClimateState::NotApplicable}; ///< 未见、不足、已备或太过。
  double usable_power{}; ///< 调候天干按透藏、冲空折算后的有效力量。
  ClimateUrgency urgency{ClimateUrgency::None}; ///< 调候优先级。
  std::string reason;                           ///< 调候结论摘要。
  std::vector<Evidence> evidence;               ///< 调候用字和状态证据。
};

/** 正格取格、成败病药和竞争格局的判定结果。 */
struct PatternResult {
  PatternKind name{PatternKind::ZhengGuan};       ///< 最终采用或首选的格名。
  PatternType pattern_type{PatternType::Regular}; ///< 格局大类。
  PatternStatus status{PatternStatus::Candidate}; ///< 格局确认状态。
  bool cheng_ge{}; ///< 是否满足当前规则集的成格条件。
  PatternCondition condition{PatternCondition::Established}; ///< 成败病药状态。
  double disease_power{};                        ///< 破坏格局的病神有效力量。
  double medicine_power{};                       ///< 制病救格的药神有效力量。
  std::vector<Evidence> evidence;                ///< 取格与病药计算证据。
  std::vector<std::string> supports;             ///< 有利于格局成立的条件。
  std::vector<std::string> conflicts;            ///< 格局病处或破格条件。
  PatternBasis basis{PatternBasis::MonthMainQi}; ///< 取格依据。
  std::optional<TianGan> pattern_stem;  ///< 对应格神；无法落实到天干时为空。
  std::optional<TianGan> human_command; ///< 分日司令；无精确时间时为空。
  std::vector<PatternKind> competing_patterns; ///< 同时出现、需要比较的格局。
};

/** 单个特殊格局的成立条件、结论和依据。 */
struct SpecialPatternResult {
  SpecialPatternKind name{SpecialPatternKind::FollowWealth}; ///< 特殊格局名称。
  SpecialPatternStatus status{SpecialPatternStatus::Excluded}; ///< 判定状态。
  std::vector<Evidence> evidence;   ///< 成立或排除该格的量化证据。
  std::vector<std::string> reasons; ///< 成立、不足或排除原因。
  std::optional<WuXing> element;    ///< 顺势、专旺或化气所对应五行。
};

/** 十神组合的性质、说明和参与证据。 */
struct TenGodCombo {
  TenGodComboKind kind;           ///< 十神组合名称。
  ComboSeverity severity;         ///< 有碍、有制可解或相成。
  std::string note;               ///< 透藏、力量和制化说明。
  std::vector<Evidence> evidence; ///< 实际参与组合的十神证据。
};

/** 一个透干或藏干十神在指定柱位的有效力量记录。 */
struct TenGodOccurrence {
  TianGan stem;          ///< 对应的具体天干，避免只按五行判断。
  ShiShen ten_god;       ///< 该天干相对日主的十神。
  std::string position;  ///< “年干”“月支本气”等可读位置。
  int pillar_position{}; ///< 柱位：0年、1月、2日、3时。
  bool exposed{};        ///< 是否直接透于天干。
  HiddenStemLevel hidden_level{HiddenStemLevel::None}; ///< 透干或藏干层级。
  double raw_power{};       ///< 未计冲空折减的原始权重。
  double effective_power{}; ///< 计入月令、冲、旬空后的有效力量。
  bool kong_wang{};         ///< 所在地支是否落旬空。
  bool clashed{};           ///< 所在地支是否受冲。
};

/** 日柱旬空及其对命局各柱根气的折减结果。 */
struct KongWangResult {
  std::array<DiZhi, 2> branches{};     ///< 日柱所在旬的两个旬空地支。
  std::vector<int> affected_positions; ///< 原局命中旬空的柱位。
  double root_multiplier{0.5};         ///< 旬空支中根气和十神的保留系数。
  std::vector<Evidence> evidence;      ///< 各柱旬空命中证据。
};

/** 出生时刻及分日司令计算所需的上下文。 */
struct BirthContext {
  bool has_exact_time{};    ///< 是否提供可用于节气分日的精确公历时刻。
  int year{};               ///< 公历年。
  int month{};              ///< 公历月，范围 1～12。
  int day{};                ///< 公历日，范围 1～31。
  int hour{};               ///< 小时，范围 0～23。
  int minute{};             ///< 分钟，范围 0～59。
  int second{};             ///< 秒，范围 0～59。
  bool is_male{true};       ///< 性别；供后续岁运扩展使用。
  std::string previous_jie; ///< 出生时刻之前最近的“节”。
  double days_since_jie{};  ///< 出生时刻距前一节的天数，可含小数。
  std::string command_table_version{"ziping-renyuan-v1"}; ///< 分日司令表版本。
  std::optional<TianGan> human_command; ///< 按分日表得到的人元司令。
  std::string human_command_reason;     ///< 司令判定或无法判定的原因。
};

/** 八字分析流派；入口统一，规则实现按流派路由。 */
enum class AnalysisMethod {
  Ziping,      ///< 子平法：月令取格、调候和病药取用。
  BlindSchool, ///< 盲派法：宾主、体用和做功规则，不与子平规则混用。
};

/** 五行生克链、断点和流通状态。 */
struct ShengKeChainResult {
  std::vector<WuXing> chain;       ///< 从印比食财官回到印的五行链。
  std::vector<std::string> breaks; ///< 力量不足或受阻的链段说明。
  bool smooth{};                   ///< 全部关键链段是否达到流通要求。
  std::vector<Evidence> evidence;  ///< 各链段力量和断点证据。
};

/** 岁运应事类型；各类型独立累计，避免以单一总分混淆风险性质。 */
enum class TransitEventType {
  BodySafety,      ///< 身体、伤灾及直接攻身风险。
  WealthDebt,      ///< 财务、借贷、破耗及现金流。
  CareerDirection, ///< 事业方向、技术输出及谋生渠道。
  RulesDisputes,   ///< 合同、制度、税务及是非纠纷。
  Relationship,    ///< 人际、合作及配偶宫关系。
  MovementChange,  ///< 迁动、环境变化及生活结构调整。
};

/** 岁运作用通道的吉凶性质。 */
enum class TransitEffectNature {
  Favorable, ///< 有利通道或制化救应。
  Adverse,   ///< 不利通道或结构损伤。
  Neutral,   ///< 仅表示结构被引动，暂不定吉凶。
};

/** 财务应事的作用机制；用于区分收入受阻、支出负担与收入机会。 */
enum class TransitFinanceSubtype {
  IncomeObstruction, ///< 薪酬、回款或财源取得受阻。
  ExpenseDebt,       ///< 支出破耗、透支或债务压力。
  IncomeOpportunity, ///< 技艺生财、财星到位等收入机会。
  GeneralChange,     ///< 暂时无法进一步归类的财务变化。
};

/** 同一干支在特定机制下产生的一条独立作用通道。 */
struct TransitEffectChannel {
  std::string rule;                      ///< 稳定规则标识。
  std::string subject;                   ///< 产生作用的天干、地支或组合。
  std::string name;                      ///< 专业作用名称。
  TransitEffectNature nature{};          ///< 有利、不利或中性。
  double score{};                        ///< 该通道自身的有符号结构分。
  std::vector<TransitEventType> domains; ///< 主要影响的应事类型。
  std::string reason;                    ///< 成立条件、制化和限制说明。
};

/** 单步大运或流年相对原局喜忌的规则作用。 */
struct FortuneImpact {
  Pillar pillar;                    ///< 被评估的大运或流年干支。
  std::string label;                ///< 岁运类型或作用摘要。
  double score{};                   ///< 规则作用净分，仅用于结构排序。
  std::vector<std::string> reasons; ///< 构成净分的命理理由。
  std::vector<Evidence> evidence;   ///< 每项加减分的结构化证据。
  std::vector<std::string>
      review_notes; ///< 七杀制化、填实、冲宫位及成局等重点复核事项。
  std::vector<TransitEffectChannel> channels; ///< 吉凶并行的独立作用通道。
};

/** 岁运柱在联合分析中的时间层级。 */
enum class TransitLayer {
  DaYun,   ///< 十年大运，决定阶段性气势。
  LiuNian, ///< 公历流年，主要用于应期判断。
  LiuYue,  ///< 节气流月，用于进一步缩小应期。
};

/** 联合岁运分析的风险提示等级，不代表确定性事件。 */
enum class TransitRiskLevel {
  Stable,  ///< 未见显著结构冲突。
  Watch,   ///< 有一项重要作用，需要关注。
  Warning, ///< 多项不利结构叠加，需要重点复核。
  Critical ///< 重大刑冲、填实或岁运相战同时出现。
};

/** 组合级关系当前实际采用的成势状态。 */
enum class TransitRelationState {
  Effective, ///< 条件相对完整，按当前规则正常计力。
  Partial,   ///< 半合、拱合等不完整结构，按部分力量计入。
  Contested, ///< 同支另见合冲刑，关系受争夺，仅保留折减后的力量。
};

/** 一条发生在岁运层之间或岁运与原局之间的组合级作用。 */
struct TransitInteraction {
  std::string rule;       ///< 稳定规则标识。
  std::string subject;    ///< 参与作用的柱或干支。
  std::string name;       ///< 伏吟、三刑、争合等专业名称。
  double score{};         ///< 组合级有符号修正分。
  std::string reason;     ///< 成立条件和命理影响说明。
  bool requires_review{}; ///< 是否必须进入重点复核。
  TransitRelationState state{TransitRelationState::Effective}; ///< 成势状态。
  double effectiveness{1.0}; ///< 关系有效系数，范围0～1。
};

/** 某一应事类型下分别累计的有利与不利结构。 */
struct TransitEventImpact {
  TransitEventType type{};  ///< 身体、财务、事业等应事类型。
  double favorable_score{}; ///< 有利通道累计分。
  double adverse_score{};   ///< 不利通道绝对值累计分。
  double net_score{}; ///< 有利减不利后的参考净分；身体项不可据此抵销先伤。
  bool rescue_offsets_adverse{true}; ///< 展示时救应是否允许与不利通道合并解读。
  std::vector<std::string> reasons;  ///< 进入该类型的规则依据。
};

/** 某一财务机制下分别累计的有利与不利作用。 */
struct TransitFinanceImpact {
  TransitFinanceSubtype type{};     ///< 收入受阻、支出债务等财务子型。
  double favorable_score{};         ///< 该子型的有利通道累计分。
  double adverse_score{};           ///< 该子型的不利通道绝对值累计分。
  std::vector<std::string> reasons; ///< 纳入该子型的规则依据。
};

/** 同一条岁运机制同时外溢到多个应事类型的汇总。 */
struct TransitSourceSpill {
  std::string source;                    ///< 共同来源的干支或组合。
  std::string mechanism;                 ///< 产生外溢的专业作用名称。
  double score{};                        ///< 该共同来源的有符号结构分。
  std::vector<TransitEventType> domains; ///< 同时受到影响的应事类型。
  std::string reason;                    ///< 同源判定及影响路径说明。
};

/** 同一时点的大运、流年和流月输入；未提供的层级保持为空。 */
struct TransitContext {
  std::optional<Pillar> da_yun;         ///< 当前大运柱。
  std::optional<Pillar> liu_nian;       ///< 当前流年柱。
  std::optional<Pillar> liu_yue;        ///< 当前流月柱。
  std::optional<int> da_yun_start_year; ///< 当前大运实际起始公历年。
  std::optional<int> current_year;      ///< 本次联合分析对应的公历年。
};

/** 多层岁运联合分析结果。 */
struct TransitAnalysis {
  std::string schema_version{"1.7"};        ///< 岁运 JSON 与中文报告契约版本。
  TransitContext context;                   ///< 本次联合分析的输入快照。
  std::vector<FortuneImpact> layer_impacts; ///< 各时间层的单柱作用。
  std::vector<TransitInteraction> interactions;  ///< 层间及与原局的组合作用。
  std::vector<TransitEffectChannel> channels;    ///< 全部吉凶并行作用通道。
  std::vector<TransitEventImpact> event_impacts; ///< 分类型应事强度。
  std::vector<TransitFinanceImpact> finance_impacts; ///< 财务机制分项。
  std::vector<TransitSourceSpill> source_spills; ///< 一源多溢的共同来源汇总。
  double projected_strength{}; ///< 岁运作用后的日主强弱估计分，范围0～100。
  bool requires_reselection{}; ///< 强弱跨越阈值时是否需要重新取用复核。
  double total_score{};        ///< 单柱分与组合修正之和，仅作结构排序。
  TransitRiskLevel risk{TransitRiskLevel::Stable}; ///< 综合提示等级。
  std::vector<std::string> review_notes;           ///< 联合分析的重点复核清单。
  std::optional<TransitEventType>
      sample_peak_type;       ///< 批量样本中的单线极值类型。
  double sample_peak_score{}; ///< 批量样本中的单线极值压力。
};

/** 日主强弱总分及得令、得地、得势等分项。 */
struct StrengthResult {
  double score{}; ///< 日主综合强弱分，范围 0～100。
  StrengthLevel level{StrengthLevel::Balanced}; ///< 综合分对应的强弱等级。
  double month_command{};                       ///< 得令分项，范围 0～100。
  bool month_tomb{};                            ///< 月令是否为日主五行之墓库。
  bool tomb_opened{};                           ///< 墓库是否因冲或刑而开。
  std::string tomb_opening_cause{"none"}; ///< 开库原因：none、冲、刑或冲刑。
  double roots{};                         ///< 得地分项，范围 0～100。
  double stem_support{};                  ///< 得势分项，范围 0～100。
  double season_adjustment{};             ///< 季节寒暖燥湿修正分，范围 0～100。
  double relation_adjustment{};   ///< 合冲刑害及拱局修正分，范围 0～100。
  std::vector<Evidence> evidence; ///< 各分项计算证据。
};

/** 日主兑现候选作用的综合承载能力。 */
struct CarryingCapacity {
  double overall{};                ///< 综合承载能力分，范围 0～100。
  double root_stability{};         ///< 根气稳定性，范围 0～100。
  double climate_workability{};    ///< 寒暖燥湿条件下的可发挥度，范围 0～100。
  double circulation{};            ///< 生克流通能力，范围 0～100。
  std::vector<Evidence> penalties; ///< 过旺、无根、受冲等折减证据。
  std::vector<Evidence> evidence;  ///< 各承载分项的计算证据。
};

/** 用神候选参与命局作用的分类。 */
enum class CandidateEffectType {
  ClimateWarmth,      ///< 以火暖局的调候作用。
  ClimateMoisture,    ///< 以水润局的调候作用。
  ResourceActivation, ///< 修正原局资源与结构条件。
  PeerSupport,        ///< 印比生扶或帮助日主。
  ControlWealth,      ///< 日主任财、耗身或护财作用。
  OutputDrain,        ///< 食伤泄秀并疏通生克链。
  PatternMedicine,    ///< 针对格局病处的药神作用。
};

/** 用神候选的一项作用、理论得分和承载要求。 */
struct CandidateEffect {
  CandidateEffectType type; ///< 候选所承担的命理作用。
  double score{};           ///< 该作用的有符号理论分。
  bool requires_capacity{}; ///< 是否需要按日主承载能力折算。
  std::string reason;       ///< 该作用成立的命理依据。
};

/** 单个天干作为用神、喜神等角色时的评分资料。 */
struct ShenCandidate {
  TianGan stem;                         ///< 被评价的具体天干。
  WuXing element;                       ///< 候选天干所属五行。
  std::string role;                     ///< 当前分配的用、喜、忌、仇或闲角色。
  double raw_score{};                   ///< 各作用相加后的理论初评分。
  double effective_score{};             ///< 经承载、合冲和命局校正后的有效分。
  double percent{};                     ///< 占全部正向候选有效分的百分比。
  bool present_in_chart{};              ///< 该候选本干是否在原局透出或藏见。
  std::vector<CandidateEffect> effects; ///< 候选承担的全部作用。
  std::vector<Evidence> reasons;        ///< 支持候选的加分证据。
  std::vector<Evidence> conflicts;      ///< 限制候选的减分或冲突证据。
};

/** 对候选用神本干进行透干、藏根、合冲和承载验证的结果。 */
struct UsefulGodVerification {
  std::string root_level;            ///< 无根、余气弱根、中气中根或本气强根。
  bool tou_gan{};                    ///< 候选本干是否在原局天干透出。
  bool is_chonged{};                 ///< 候选本干根气是否受地支冲。
  bool is_he{};                      ///< 候选本干是否参与天干五合。
  bool is_transformed{};             ///< 候选本干参与的五合是否真正合化。
  bool effective{};                  ///< 最终效力是否达到可定用阈值。
  double power{};                    ///< 用神验证效力，范围 0～100。
  double combine_penalty{};          ///< 合绊或合化造成的折减分。
  double carrying_capacity{};        ///< 验证时采用的日主承载能力分。
  std::vector<Evidence> exact_roots; ///< 候选本干的精确藏根证据。
  std::vector<Evidence> same_element_support; ///< 同五行异干的辅助力量。
  std::vector<Evidence> evidence; ///< 透干、根气、合冲和承载的汇总证据。
};

/** 用神路线、五神互斥角色、候选排名和复核提示。 */
struct UsefulGodResult {
  UsefulGodStatus status{UsefulGodStatus::NeedsReview}; ///< 用神确认状态。
  UsefulGodMethod method{UsefulGodMethod::PatternCirculation}; ///< 主取用方法。
  std::string reason; ///< 锁定取用方法和路线的总理由。
  UsefulGodRoute route{
      UsefulGodRoute::CandidatePrimaryEffect}; ///< 主取用路线。
  std::optional<ShenCandidate> yong_shen;      ///< 主治命局之病的用神。
  std::optional<ShenCandidate> xi_shen;        ///< 生扶用神或协助成格的喜神。
  std::optional<ShenCandidate> ji_shen;        ///< 加重命局主要病处的忌神。
  std::optional<ShenCandidate> chou_shen;      ///< 生助忌神或损伤用神的仇神。
  std::optional<ShenCandidate> xian_shen;      ///< 当前路线中作用有限的闲神。
  std::optional<UsefulGodVerification> verification; ///< 主用本干效力验证。
  std::vector<ShenCandidate> candidates; ///< 按有效分降序排列的候选。
  std::vector<std::string> role_notes;   ///< 五神互斥及具体天干定性说明。
  std::vector<std::string> warnings;     ///< 需要岁运或人工复核的事项。
};

/** 八字分析的权重、折减系数和判定阈值配置。 */
struct AnalysisConfig {
  double stem_weight{1.0};               ///< 每个透干计入五行和十神的基础权重。
  double main_hidden_stem_weight{1.0};   ///< 地支本气权重。
  double middle_hidden_stem_weight{0.6}; ///< 地支中气权重。
  double residual_hidden_stem_weight{0.3}; ///< 地支余气权重。
  double month_branch_multiplier{1.5};     ///< 月令藏干的季节加权倍数。
  double clashed_root_multiplier{0.5};     ///< 受冲地支中根气的保留系数。
  double low_capacity_threshold{40.0};     ///< 低于此承载分时保留人工复核。
  double review_score_gap{5.0}; ///< 前两候选分差小于此值时不贸然定用。
  double useful_god_effective_threshold{55.0}; ///< 用神验证通过的最低效力分。
  double kong_wang_root_multiplier{0.5};       ///< 旬空地支中根气的保留系数。
  double climate_sufficient_power{1.0};        ///< 判定调候已足的最低有效力量。
  double climate_excessive_percent{35.0};      ///< 调候五行判定太过的占比阈值。
  double adjacent_arch_weight{0.25};           ///< 紧贴两支虚拱中神的修正权重。
  double distant_arch_weight{0.10};            ///< 隔位两支虚拱中神的修正权重。
  double adjacent_stem_combine_penalty{12.0};  ///< 紧贴天干合而不化的折减分。
  double distant_stem_combine_penalty{6.0};    ///< 隔位天干合而不化的折减分。
};

/** 统一分析请求；出生时间可覆盖排盘结果中的默认时间上下文。 */
struct AnalysisRequest {
  AnalysisMethod method{AnalysisMethod::Ziping}; ///< 要采用的分析流派。
  std::optional<BirthContext> birth_context;     ///< 可选的精确出生时间。
  AnalysisConfig config;                         ///< 本次分析配置。
};

/** 盲派宫位的固定语义与本次主宾角色。 */
struct BlindPalaceAssignment {
  int position{};     ///< 柱位：0年、1月、2日、3时。
  std::string palace; ///< 四柱宫位名称。
  std::string role;   ///< 主位、宾位或主宾交界。
  std::string reason; ///< 角色判定依据。
};

/** 盲派单条做功链的中文化摘要；详细规则由 mangpai 模块生成。 */
struct BlindWorkSummary {
  std::string source;             ///< 发起作用的干支或组合。
  std::string target;             ///< 被作用的干支或组合。
  std::string direction;          ///< 主→宾、宾→主或主位内部。
  std::string relation;           ///< 制、合、冲、刑、穿、生、墓等。
  std::string result;             ///< 由该做功推导的主要人事结果。
  int source_position{-1};        ///< 作用方柱位；未知时为 -1。
  int target_position{-1};        ///< 被作用方柱位；未知时为 -1。
  std::string source_ten_god;     ///< 作用方相对日主的十神。
  std::string target_ten_god;     ///< 被作用方相对日主的十神。
  double effective_power{};       ///< 参与双方有效力量的保守值。
  bool effective{};               ///< 是否形成有效做功。
  std::vector<Evidence> evidence; ///< 支撑该做功的证据。
};

/** 盲派结构映射出的独立应事分项。 */
struct BlindEventImpact {
  std::string type;                  ///< 财务、事业、婚姻、身体或迁动等类型。
  double pressure{};                 ///< 原始作用压力，非概率或命运确定性。
  std::vector<std::string> triggers; ///< 触发该分项的做功链和组合。
  std::vector<std::string> evidence; ///< 面向中文展示的证据摘要。
};

/** 盲派墓库状态；关系和开闭状态分开保存。 */
struct BlindTombSummary {
  std::string tomb_branch;      ///< 墓库地支。
  std::string stored_stem;      ///< 被墓收纳的天干。
  int tomb_position{-1};        ///< 墓库所在柱位。
  int stored_stem_position{-1}; ///< 被墓天干所在柱位。
  std::string relation;         ///< 冲、刑、合、穿或无。
  std::string state;            ///< 闭库、开库、破库或待复核。
  bool opened{};                ///< 是否存在明确开库作用。
  std::string evidence;         ///< 判定墓库状态的依据摘要。
};

/** 盲派理论层结果；与子平强弱、用神结果并列而不互相覆盖。 */
struct BlindAnalysisDetail {
  std::string rule_set{"mangpai-v1"};          ///< 盲派规则集版本。
  std::vector<BlindPalaceAssignment> palaces;  ///< 固定宫位及动态主宾角色。
  std::vector<std::string> body;               ///< 主位体和辅助体。
  std::vector<std::string> targets;            ///< 目标用及其所在宾位。
  std::vector<BlindWorkSummary> work_chains;   ///< 主、辅做功链。
  std::vector<BlindEventImpact> event_impacts; ///< 财务、事业等应事分项。
  std::vector<BlindTombSummary> tombs;         ///< 墓库状态明细。
  std::vector<std::string> useful_gods;        ///< 功神摘要。
  std::vector<std::string> waste_gods;         ///< 废神摘要。
  std::vector<std::string> structures;      ///< 制用、合用、化用、生用、墓用。
  std::vector<std::string> symbols;         ///< 结构转译的人事象。
  std::vector<std::string> transit_impacts; ///< 大运、流年对原局的引动摘要。
  std::vector<std::string> warnings;        ///< 证据不足或流派差异提示。
};

/** 一次完整子平分析的聚合结果，也是展示层的唯一输入。 */
struct AnalysisResult {
  std::string schema_version{"1.7"}; ///< JSON 和中文报告的数据契约版本。
  AnalysisMethod method{AnalysisMethod::Ziping};      ///< 实际采用的分析流派。
  std::string rule_set{"ziping-v3-route-consistent"}; ///< 子平规则集标识。
  AnalysisConfig config;                      ///< 本次分析实际采用的配置快照。
  BirthContext birth_context;                 ///< 出生时刻和分日司令上下文。
  std::array<Pillar, 4> input_pillars;        ///< 年、月、日、时四柱输入快照。
  TianGan day_master;                         ///< 日主天干。
  std::array<NayinInfo, 4> nayin;             ///< 四柱纳音。
  std::array<ElementStat, 5> element_balance; ///< 五行力量与占比。
  std::vector<BranchRelation> relations;      ///< 原局地支关系。
  std::vector<StemRelation> stem_relations;   ///< 原局天干关系。
  KongWangResult kong_wang;                   ///< 旬空判定。
  ClimateResult climate;                      ///< 调候判定。
  PatternResult pattern;                      ///< 正格和病药判定。
  std::vector<SpecialPatternResult> special_patterns; ///< 特殊格局判定。
  std::vector<TenGodCombo> ten_god_combos;            ///< 有效十神组合。
  std::vector<TenGodOccurrence> ten_god_occurrences;  ///< 透藏十神明细。
  StrengthResult strength;                            ///< 日主强弱及分项。
  CarryingCapacity carrying_capacity;                 ///< 日主承载能力。
  UsefulGodResult useful_gods;       ///< 用神路线、候选和五神角色。
  ShengKeChainResult sheng_ke_chain; ///< 五行生克流通链。
  std::optional<BlindAnalysisDetail> blind_analysis; ///< 盲派分析详情。
  std::vector<FortuneImpact> fortune_impacts;        ///< 已传入岁运的评估结果。
  std::vector<std::string> warnings;                 ///< 汇总后的待复核提示。
};

/** 按默认出生上下文分析原局。 */
AnalysisResult analyze(const BaZi &chart, const AnalysisConfig &config = {});
/** 分析原局并同时评估所给岁运柱。 */
AnalysisResult analyze(const BaZi &chart, const std::vector<Pillar> &fortunes,
                       const AnalysisConfig &config = {});
/** 使用精确出生上下文分析原局，以支持分日司令。 */
AnalysisResult analyze(const BaZi &chart, const BirthContext &birth_context,
                       const AnalysisConfig &config = {});
/**
 * @brief 补全出生上下文中的人元司令信息。
 *
 * 子平与盲派共用同一套节令和分日司令事实，分析流派只解释结果。
 */
BirthContext resolve_birth_context(const BirthContext &input);
/** 使用完整出生上下文分析原局，并同时评估所给岁运柱。 */
AnalysisResult analyze(const BaZi &chart, const BirthContext &birth_context,
                       const std::vector<Pillar> &fortunes,
                       const AnalysisConfig &config = {});
/** 基于既有原局分析结果评估单个大运或流年柱。 */
FortuneImpact analyze_fortune(const Pillar &fortune,
                              const AnalysisResult &analysis);
/** 联合分析同一时点的大运、流年和流月及其相互作用。 */
TransitAnalysis analyze_transit(const TransitContext &context,
                                const AnalysisResult &analysis);

/** 标记一组岁运结果中达到极值阈值的最深单线，供批量回归报告使用。 */
void annotate_sample_extremes(std::vector<TransitAnalysis> &analyses);
/** 将完整分析结果序列化为稳定的 JSON 数据契约。 */
nlohmann::json to_json(const AnalysisResult &result);
/** 将联合岁运分析序列化为 JSON。 */
nlohmann::json to_json(const TransitAnalysis &result);
/** 返回面向中文阅读/大模型消费的完整、可追溯分析报告。 */
std::string to_zh(const AnalysisResult &result);
/** 返回联合岁运分析的中文报告。 */
std::string to_zh(const TransitAnalysis &result);
/** 将中文分析报告写入任意输出流。 */
void write_zh(std::ostream &output, const AnalysisResult &result);
/** 将联合岁运中文报告写入任意输出流。 */
void write_zh(std::ostream &output, const TransitAnalysis &result);
/** 兼容原有控制台摘要接口。 */
std::string format_analysis(const AnalysisResult &result);

} // namespace ZhouYi::BaZiAnalysis

namespace ZhouYi::Mapper {

/** PatternStatus 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::PatternStatus> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 3>{"格局成立", "格局候选",
                                           "格局待复核"};
  }
};
/** SpecialPatternStatus 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::SpecialPatternStatus> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 4>{"成格", "格局待定", "待考",
                                           "不入格"};
  }
};
/** UsefulGodStatus 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::UsefulGodStatus> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 3>{"用神可定", "用神候选", "用神待定"};
  }
};
/** PatternCondition 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::PatternCondition> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 4>{"格局成立", "格局带病而药力可任",
                                           "格可立，带病见药但药力不足，待复核",
                                           "破格条件明显"};
  }
};
/** PatternKind 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::PatternKind> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 11>{
        "正官格", "七杀格", "正财格", "偏财格", "正印格", "偏印格",
        "食神格", "伤官格", "建禄格", "月劫格", "羊刃格"};
  }
};
/** PatternType 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::PatternType> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 4>{"正格", "建禄", "月劫", "羊刃"};
  }
};
/** SpecialPatternKind 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::SpecialPatternKind> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 9>{"从财格", "从杀格", "从儿格",
                                           "曲直格", "炎上格", "稼穑格",
                                           "从革格", "润下格", "化气格"};
  }
};
/** ClimateState 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::ClimateState> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 5>{
        "无需调候", "调候字未见", "调候已见而力不足", "调候已备", "调候太过"};
  }
};
/** ClimateUrgency 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::ClimateUrgency> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 3>{"无需另取", "兼顾调候", "调候为先"};
  }
};
/** PatternBasis 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::PatternBasis> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 3>{"月令本气取格", "月令藏干透出取格",
                                           "分日司令取格"};
  }
};
/** StrengthLevel 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::StrengthLevel> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 5>{"极弱", "身弱", "中和", "身强",
                                           "极旺"};
  }
};
/** HiddenStemLevel 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::HiddenStemLevel> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 4>{"透干", "本气", "中气", "余气"};
  }
};
/** RelationStrength 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::RelationStrength> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 5>{"未定", "中等", "极强", "暗拱",
                                           "隔位暗拱"};
  }
};
/** UsefulGodMethod 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::UsefulGodMethod> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 5>{
        "特殊格局取用", "调候取用", "格局病药取用", "格局流通取用", "扶抑取用"};
  }
};
/** UsefulGodRoute 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::UsefulGodRoute> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 4>{
        "按候选主效应取用", "特殊格局顺势取用", "官制比护财", "食伤泄比生财"};
  }
};
/** ComboSeverity 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::ComboSeverity> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 3>{"有碍格局", "有制可解", "相成"};
  }
};
/** BranchRelationKind 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::BranchRelationKind> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 10>{"冲",     "合",     "害",   "刑",
                                            "自刑",   "三合局", "半合", "拱合",
                                            "三会局", "三刑"};
  }
};
/** StemRelationKind 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::StemRelationKind> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 1>{"天干五合"};
  }
};
/** TenGodComboKind 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::TenGodComboKind> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 8>{"官杀混杂", "伤官见官", "枭印夺食",
                                           "杀印相生", "食神制杀", "伤官配印",
                                           "比劫夺财", "财星偏多"};
  }
};
/** CandidateEffectType 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::CandidateEffectType> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 7>{
        "调候暖局", "调候润局", "命局结构校正", "比劫助身",
        "任财耗身", "食伤泄秀", "格局病药"};
  }
};

/** AnalysisMethod 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::AnalysisMethod> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 2>{"子平法", "盲派法"};
  }
};

/** TransitLayer 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::TransitLayer> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 3>{"大运", "流年", "流月"};
  }
};

/** TransitRiskLevel 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::TransitRiskLevel> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 4>{"平稳", "留意", "预警", "重点预警"};
  }
};

/** TransitEventType 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::TransitEventType> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 6>{"身体伤灾", "财务借贷", "事业技艺",
                                           "合同规则", "人际感情", "迁动变更"};
  }
};

/** TransitEffectNature 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::TransitEffectNature> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 3>{"有利通道", "不利通道", "结构引动"};
  }
};

/** TransitFinanceSubtype 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::TransitFinanceSubtype> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 4>{"收入受阻", "支出与债务", "收入机会",
                                           "财务变动"};
  }
};

/** TransitRelationState 的专业中文映射。 */
template <> struct ZhMap<ZhouYi::BaZiAnalysis::TransitRelationState> {
  /** 返回与枚举序值严格同序的专业中文文本。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 3>{"成势", "部分成势", "竞合受制"};
  }
};

} // namespace ZhouYi::Mapper
