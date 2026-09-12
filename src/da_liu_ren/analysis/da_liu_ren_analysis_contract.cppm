// C++23 Module - 大六壬分析契约
export module ZhouYi.DaLiuRenAnalysis.Contract;

export import ZhouYi.DaLiuRen.Types;

import ZhouYi.GanZhi;
import ZhouYi.ZhMapper;
import std;

/** @brief 大六壬分析的稳定输入与输出契约。 */
export namespace ZhouYi::DaLiuRenAnalysis {

using ZhouYi::DaLiuRen::JiuZongMen;
using ZhouYi::DaLiuRen::TianJiang;
using ZhouYi::GanZhi::DiZhi;
using ZhouYi::GanZhi::LiuQin;
using ZhouYi::GanZhi::TianGan;

/** @brief 占问门类，用于选择类神和断课策略。 */
enum class QuestionKind {
  FanZhan,     ///< 未限定事项的泛占。
  QiuCai,      ///< 求财、交易、回款。
  GongMing,    ///< 官职、事业、任用。
  HunYin,      ///< 婚姻、感情、对象。
  JiBing,      ///< 疾病、医药、身体。
  ChuXing,     ///< 出行、迁移、行人。
  GuanSong,    ///< 官讼、争议、规则。
  KaoShi,      ///< 考试、录取、文凭。
  XunRenShiWu, ///< 寻人、失物。
  XiaoXiWenShu ///< 消息、合同、文书。
};

/** @brief 求测者性别；婚姻占用于区分财爻与官鬼类神。 */
enum class SeekerGender { Nan, Nv };

/** @brief 八门占断位置。 */
enum class BaMen {
  XianFeng, ///< 先锋门，占时。
  ZhiShi,   ///< 值事门，月将。
  WaiShi,   ///< 外事门，日干及一二课。
  NeiShi,   ///< 内事门，日支及三四课。
  FaDuan,   ///< 发端门，初传。
  YiYi,     ///< 移易门，中传。
  GuiJi,    ///< 归计门，末传。
  BianTi    ///< 变体门，本命、行年。
};

/** @brief 课位。 */
enum class LessonPosition { First, Second, Third, Fourth };

/** @brief 三传阶段。 */
enum class TransmissionStage { Chu, Zhong, Mo };

/** @brief 求测者本命、行年的临盘标识。 */
enum class PersonalMarkerKind {
  BenMing, ///< 本命。
  XingNian ///< 行年。
};

/** @brief 课传作用性质；吉凶须结合占类，不由该枚举直接决定。 */
enum class EffectNature {
  ShengFu, ///< 生扶、救应。
  KeZhi,   ///< 克制、阻隔。
  YinDong, ///< 合冲刑害所致引动。
  BiHe,    ///< 同气比和。
  DaiBian  ///< 条件不足，须随占类变断。
};

/** @brief 四课上下神的五行关系。 */
enum class LessonRelation {
  XiaZeiShang,   ///< 下神克上神，下贼上。
  ShangKeXia,    ///< 上神克下神，上克下。
  ShangShengXia, ///< 上神生下神。
  XiaShengShang, ///< 下神生上神，脱耗于上。
  BiHe           ///< 上下同气。
};

/** @brief 综合课象趋向，不使用单一数值压平吉凶与救应。 */
enum class Judgment { Ji, PianJi, Ping, PianXiong, Xiong, DaiDing };

/** @brief 应期触发规则。 */
enum class YingQiRule {
  FaYongZhi,       ///< 发用值期。
  FaYongChong,     ///< 冲动或冲开发用。
  FaYongHe,        ///< 合起或合住发用。
  ZhongChuanZhi,   ///< 中传值期。
  MoChuanZhi,      ///< 末传值期。
  ChuKong,         ///< 空亡出空填实。
  BanHeBuQi,       ///< 半合补齐所缺支。
  SanHeWangShen,   ///< 完整三合以旺神为枢纽。
  XianFengChongLin ///< 占时先锋重临。
};

/** @brief 应期候选在事情进程中所主的阶段。 */
enum class YingQiPhase {
  FaDuan, ///< 发端：事情开始显动。
  ChuFa,  ///< 触发：冲开、合住或填实既有课象。
  YiYi,   ///< 移易：事情中途变化。
  GuiJi,  ///< 归计：事情形成结果。
  ChengJu ///< 成局：课传之气聚合成势。
};

/** @brief 依据旺衰、空亡、日辰和占时评估的应期迟速。 */
enum class YingQiPace {
  Su,      ///< 速：旺相或日辰、占时直接引动。
  JiaoSu,  ///< 较速：得气且少阻。
  Ping,    ///< 平：无明显催发或阻滞。
  JiaoChi, ///< 较迟：休囚死或须经传递。
  DaiShi   ///< 待时：空、合、冲等条件尚待解除。
};

/** @brief 课传地支之间可并存的结构关系。 */
enum class BranchRelationKind {
  TongLin, ///< 同一地支重临。
  Sheng,   ///< 五行相生。
  Ke,      ///< 五行相克。
  BiHe,    ///< 五行比和。
  Xing,    ///< 地支相刑。
  Chong,   ///< 地支六冲。
  LiuHe,   ///< 地支六合。
  Hai,     ///< 地支六害。
  BanHe,   ///< 三合半局或生墓拱合。
  SanHe,   ///< 完整三合局。
  SanHui   ///< 完整三会局。
};

/** @brief 大六壬分析请求。 */
struct AnalysisRequest {
  QuestionKind question_kind{QuestionKind::FanZhan}; ///< 占问门类。
  std::string question;                              ///< 占问原文。
  std::optional<DiZhi> ben_ming;      ///< 求测者本命；未提供不推测。
  std::optional<DiZhi> xing_nian;     ///< 求测者行年；未提供不推测。
  std::optional<SeekerGender> gender; ///< 求测者性别；非相关占类可不提供。
  std::optional<LiuQin> specified_lei_shen; ///< 人工指定六亲类神。
};

/** @brief 一条可追溯的课传依据。 */
struct KeChuanBasis {
  std::string rule;                           ///< 稳定规则名称。
  std::string detail;                         ///< 大六壬术语化说明。
  EffectNature nature{EffectNature::DaiBian}; ///< 作用性质。
};

/** @brief 单课分析。 */
struct LessonAnalysis {
  LessonPosition position{LessonPosition::First}; ///< 第一至第四课。
  BaMen door{BaMen::WaiShi};                      ///< 所属外事门或内事门。
  std::string lower;                              ///< 下神，第一课为日干。
  DiZhi lower_palace{DiZhi::Zi};                  ///< 下神所对应的地盘宫位。
  DiZhi upper{DiZhi::Zi};                         ///< 上神。
  LiuQin relative{LiuQin::XiongDi};               ///< 上神对日干的六亲。
  TianJiang general{TianJiang::GuiRen};           ///< 上神所乘天将。
  LessonRelation relation{LessonRelation::BiHe};  ///< 上下神生克。
  bool sends_initial{};                           ///< 本课上神是否发用。
  std::vector<KeChuanBasis> ke_chuan_basis;       ///< 课内关系依据。
};

/** @brief 单传分析。 */
struct TransmissionAnalysis {
  TransmissionStage stage{TransmissionStage::Chu}; ///< 初、中、末传。
  DiZhi branch{DiZhi::Zi};                         ///< 传神。
  DiZhi earth_position{DiZhi::Zi};                 ///< 传神所临地盘宫位。
  std::optional<TianGan> hidden_stem;              ///< 遁干；空亡时为空。
  LiuQin relative{LiuQin::XiongDi};                ///< 对日干六亲。
  TianJiang general{TianJiang::GuiRen};            ///< 所乘天将。
  std::string seasonal_state;                      ///< 旺、相、休、囚、死。
  bool is_void{};                                  ///< 是否旬空。
  std::vector<std::string> shen_sha;               ///< 仅记录落在本传的神煞。
  std::vector<KeChuanBasis> ke_chuan_basis;        ///< 本传及传间作用。
};

/** @brief 八门之一的占断结果。 */
struct BaMenAnalysis {
  BaMen door{BaMen::XianFeng};              ///< 门名。
  std::string subject;                      ///< 对应课中对象。
  std::string conclusion;                   ///< 本门断意。
  std::vector<KeChuanBasis> ke_chuan_basis; ///< 本门课传依据。
};

/** @brief 类神选取结果。 */
struct LeiShenSelection {
  std::string name;                           ///< 类神名称。
  std::optional<LiuQin> primary_relative;     ///< 主六亲类神。
  std::vector<TianJiang> supporting_generals; ///< 辅助天将类神。
  std::vector<DiZhi> appearances;             ///< 类神在四课三传中的落支。
  std::vector<DiZhi> plate_positions;         ///< 类神在完整天盘中的所在支神。
  std::string reason;                         ///< 取类神依据。
};

/** @brief 课体或毕法结构的命中结果。 */
struct PatternAnalysis {
  std::string name;                         ///< 课体名称。
  std::string implication;                  ///< 依当前占类解释后的课意。
  std::vector<KeChuanBasis> ke_chuan_basis; ///< 课体成立依据。
};

/** @brief 半合所缺地支在课盘各层的落处与出现情况。 */
struct MissingBranchContext {
  DiZhi branch{DiZhi::Zi};                ///< 半合所缺之支。
  DiZhi heaven_lands_on_earth{DiZhi::Zi}; ///< 该支在天盘所临地盘宫。
  DiZhi heaven_over_earth{DiZhi::Zi};     ///< 该支作为地盘宫时所乘天盘支神。
  std::vector<LessonPosition>
      lesson_upper_positions; ///< 出现于四课上神的位置。
  std::vector<LessonPosition>
      lesson_lower_positions; ///< 出现于四课下神的位置。
  std::vector<TransmissionStage> transmission_positions; ///< 出现于三传的位置。
  bool is_month_command{};                               ///< 是否与月建同支。
  bool is_day_branch{};                                  ///< 是否与日辰同支。
  bool is_hour_branch{};                                 ///< 是否与占时同支。
  bool is_month_general{};                               ///< 是否与月将同支。
  bool is_void{};                                        ///< 是否落本旬空亡。
  std::string assessment; ///< 所缺支能否由原课或外来时令补入的判断。
};

/** @brief 三传之间的一条结构关系；同一对支可同时有五行和支象关系。 */
struct BranchRelationAnalysis {
  BranchRelationKind kind{BranchRelationKind::BiHe}; ///< 关系种类。
  std::vector<DiZhi> branches;                       ///< 参与关系的传神。
  std::vector<std::string> sources;    ///< 各支来自天地盘、四课或三传的位置。
  std::optional<DiZhi> missing_branch; ///< 半合所缺之支。
  std::optional<MissingBranchContext> missing_context; ///< 所缺支课盘落处。
  std::string detail;                                  ///< 专业关系说明。
  EffectNature nature{EffectNature::DaiBian};          ///< 作用性质。
};

/** @brief 本命或行年落入天地盘后，与三传发生的作用。 */
struct PersonalMarkerAnalysis {
  PersonalMarkerKind kind{PersonalMarkerKind::BenMing}; ///< 本命或行年。
  DiZhi branch{DiZhi::Zi};                              ///< 本命或行年地支。
  DiZhi upper{DiZhi::Zi};               ///< 该地盘本宫所乘的天盘上神。
  TianJiang general{TianJiang::GuiRen}; ///< 上神所乘天将。
  LiuQin relative{LiuQin::XiongDi};     ///< 上神对日干所主六亲。
  bool branch_is_void{};                ///< 本命或行年地支是否旬空。
  bool upper_is_void{};                 ///< 所乘上神是否旬空。
  std::vector<BranchRelationAnalysis>
      transmission_relations;               ///< 所乘上神与初、中、末传的作用。
  std::vector<KeChuanBasis> ke_chuan_basis; ///< 本命或行年临盘取象依据。
};

/** @brief 天地盘一个宫位的上下作用。 */
struct PalaceAnalysis {
  DiZhi earth{DiZhi::Zi};                        ///< 地盘宫位。
  DiZhi heaven{DiZhi::Zi};                       ///< 加临本宫的天盘支神。
  TianJiang general{TianJiang::GuiRen};          ///< 天盘支神所乘天将。
  LiuQin relative{LiuQin::XiongDi};              ///< 天盘支神对日干的六亲。
  std::vector<BranchRelationAnalysis> relations; ///< 天盘与地盘的全部作用。
};

/** @brief 应期候选。 */
struct TimingCandidate {
  int priority{};                           ///< 从 1 开始连续排列。
  DiZhi trigger{DiZhi::Zi};                 ///< 候选触发支。
  YingQiRule rule{YingQiRule::FaYongZhi};   ///< 触发规则。
  YingQiPhase phase{YingQiPhase::FaDuan};   ///< 对应发端、移易或归计阶段。
  YingQiPace pace{YingQiPace::Ping};        ///< 当前课盘所示迟速。
  std::string time_scope;                   ///< 建议采用的时、日、月候期层级。
  std::string condition;                    ///< 候选成立或触发的条件。
  std::string manifestation;                ///< 触发后主要应象。
  std::string constraints;                  ///< 空、合、冲、休囚等限制。
  std::string priority_reason;              ///< 排在当前次序的依据。
  std::string description;                  ///< 兼容用的完整应期摘要。
  std::vector<KeChuanBasis> ke_chuan_basis; ///< 应期推导依据。
};

/** @brief 完整大六壬分析结果。 */
struct AnalysisResult {
  std::string schema_version{"da-liu-ren-analysis/1.1"}; ///< 契约版本。
  AnalysisRequest request;                               ///< 占问输入快照。
  JiuZongMen method{JiuZongMen::WeiZhi};                 ///< 九宗门取传法。
  std::vector<std::string> lesson_patterns;              ///< 排盘已有课式。
  std::vector<LessonAnalysis> lessons;                   ///< 四课分析。
  std::vector<TransmissionAnalysis> transmissions;       ///< 三传分析。
  std::vector<PersonalMarkerAnalysis>
      personal_markers;                   ///< 本命、行年临盘分析。
  std::vector<BaMenAnalysis> eight_doors; ///< 八门占断。
  LeiShenSelection lei_shen;              ///< 类神定位。
  std::vector<PatternAnalysis> patterns;  ///< 课体与毕法结构。
  std::vector<PalaceAnalysis> palaces;    ///< 天地盘逐宫作用。
  std::vector<BranchRelationAnalysis>
      transmission_relations; ///< 三传生克刑冲合害及成局。
  std::vector<BranchRelationAnalysis>
      cross_layer_relations;              ///< 天地盘、四课、三传跨层作用。
  std::vector<KeChuanBasis> favorable;    ///< 得助与救应通道。
  std::vector<KeChuanBasis> unfavorable;  ///< 受制与阻隔通道。
  std::vector<TimingCandidate> timing;    ///< 应期候选。
  Judgment judgment{Judgment::DaiDing};   ///< 综合课象趋向。
  std::string conclusion;                 ///< 总断。
  std::vector<std::string> review_points; ///< 缺失输入和复核项。
};

} // namespace ZhouYi::DaLiuRenAnalysis

namespace ZhouYi::Mapper {
template <> struct ZhMap<ZhouYi::DaLiuRenAnalysis::QuestionKind> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 10>{
        "泛占",     "求财", "功名事业", "婚姻感情", "疾病",
        "出行行人", "官讼", "考试",     "寻人失物", "消息文书"};
  }
};
template <> struct ZhMap<ZhouYi::DaLiuRenAnalysis::SeekerGender> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 2>{"男", "女"};
  }
};
template <> struct ZhMap<ZhouYi::DaLiuRenAnalysis::BaMen> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 8>{"先锋门", "值事门", "外事门",
                                           "内事门", "发端门", "移易门",
                                           "归计门", "变体门"};
  }
};
template <> struct ZhMap<ZhouYi::DaLiuRenAnalysis::LessonPosition> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 4>{"第一课", "第二课", "第三课",
                                           "第四课"};
  }
};
template <> struct ZhMap<ZhouYi::DaLiuRenAnalysis::TransmissionStage> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 3>{"初传", "中传", "末传"};
  }
};
template <> struct ZhMap<ZhouYi::DaLiuRenAnalysis::PersonalMarkerKind> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 2>{"本命", "行年"};
  }
};
template <> struct ZhMap<ZhouYi::DaLiuRenAnalysis::EffectNature> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 5>{"生扶", "克制", "引动", "比和",
                                           "待辨"};
  }
};
template <> struct ZhMap<ZhouYi::DaLiuRenAnalysis::LessonRelation> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 5>{"下贼上", "上克下", "上生下",
                                           "下生上", "比和"};
  }
};
template <> struct ZhMap<ZhouYi::DaLiuRenAnalysis::Judgment> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 6>{"吉",   "偏吉", "平",
                                           "偏凶", "凶",   "待定"};
  }
};
template <> struct ZhMap<ZhouYi::DaLiuRenAnalysis::YingQiRule> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 9>{"发用值期", "冲动发用", "合起发用",
                                           "中传值期", "末传值期", "出空填实",
                                           "半合补齐", "三合旺神", "先锋重临"};
  }
};
template <> struct ZhMap<ZhouYi::DaLiuRenAnalysis::YingQiPhase> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 5>{"发端", "触发", "移易", "归计",
                                           "成局"};
  }
};
template <> struct ZhMap<ZhouYi::DaLiuRenAnalysis::YingQiPace> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 5>{"速", "较速", "平", "较迟", "待时"};
  }
};
template <> struct ZhMap<ZhouYi::DaLiuRenAnalysis::BranchRelationKind> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 11>{"同临", "相生", "相克", "比和",
                                            "相刑", "六冲", "六合", "六害",
                                            "半合", "三合", "三会"};
  }
};
} // namespace ZhouYi::Mapper
