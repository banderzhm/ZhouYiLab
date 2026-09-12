/**
 * @file mei_hua_analysis_contract.cppm
 * @brief 梅花易数占断结果契约。
 */
export module ZhouYi.MeiHua.Analysis.Contract;

export import ZhouYi.MeiHua;
import ZhouYi.ZhMapper;
import std;

export namespace ZhouYi::MeiHuaAnalysis {

/** 占问门类。 */
enum class QuestionKind {
  FanZhan,   ///< 泛占。
  GongMing,  ///< 功名事业。
  QiuCai,    ///< 求财经营。
  HunLian,   ///< 婚恋关系。
  JiBing,    ///< 疾病医药。
  ChuXing,   ///< 出行迁动。
  XueYe,     ///< 学业考试。
  ShiWu,     ///< 寻人失物。
  TianShi,   ///< 天时晴雨。
  RenShi,    ///< 人事进退。
  JiaZhai,   ///< 家宅人口。
  WuShe,     ///< 屋舍营造。
  ShengChan, ///< 生产孕育。
  YinShi,    ///< 饮食宴享。
  QiuMou,    ///< 求谋成败。
  JiaoYi,    ///< 交易买卖。
  XingRen,   ///< 行人归期。
  YeJian,    ///< 谒见访求。
  GuanSong,  ///< 官讼争端。
  FenMu      ///< 坟墓葬地。
};

/** 可供策略表与回归测试共用的占问门类数量。 */
inline constexpr std::size_t question_kind_count = 20;

/** 本、互、变三段卦势。 */
enum class GuaStage {
  Ben, ///< 本卦主始。
  Hu,  ///< 互卦主中。
  Bian ///< 变卦主终。
};

/** 本、互、变推演中各经卦的职责。 */
enum class TrigramRole {
  TiGenBen, ///< 体卦根本。
  YongGua,  ///< 本卦之用。
  HuXia,    ///< 下互卦。
  HuShang,  ///< 上互卦。
  BianYong  ///< 变卦动位之经卦。
};

/** 占断依据门类。 */
enum class BasisKind {
  BenTiYong,  ///< 本卦体用。
  HuXia,      ///< 下互作用。
  HuShang,    ///< 上互作用。
  BianGuiSu,  ///< 变卦归宿。
  TiYongDang, ///< 体党用党。
  WaiYing     ///< 外应参断。
};

/** 外卦对体卦形成的五行作用。 */
enum class TiYongRelation {
  BiHe,        ///< 体用比和。
  YongShengTi, ///< 用生体。
  TiShengYong, ///< 体生用。
  TiKeYong,    ///< 体克用。
  YongKeTi     ///< 用克体。
};

/** 卦势综合趋向。 */
enum class Judgment {
  DaJi,   ///< 大吉。
  Ji,     ///< 吉。
  Ping,   ///< 平。
  YouZu,  ///< 有阻。
  Xiong,  ///< 凶。
  DaiBian ///< 象意相持，待辨。
};

/** 八卦五行在月令中的气势。 */
enum class SeasonalStrength {
  Wang,   ///< 当令而旺。
  Xiang,  ///< 得令神所生而相。
  Xiu,    ///< 生令神而休。
  Qiu,    ///< 克令神而囚。
  Si,     ///< 受令神所克而死。
  WeiDing ///< 数字卦未带月令。
};

/** 互变诸卦归入体党或用党。 */
enum class PartySide {
  Ti,      ///< 生扶、比和体卦者归体党。
  Yong,    ///< 克泄体卦者归用党。
  XiangChi ///< 仅用于汇总，表示两党相持。
};

/** 一条占断依据对卦势的作用性质。 */
enum class BasisDisposition {
  ZhuYi,   ///< 生扶、比和或可制，形成助益。
  ZuAi,    ///< 克体、耗体或外局成阻。
  XiangChi ///< 两党相持或外应尚未归象。
};

/** 外应门类。 */
enum class OmenKind {
  ShengYin, ///< 声音次数与性质。
  RenWu,    ///< 来人及人伦之象。
  DongWu,   ///< 动物之象。
  QiWu,     ///< 器物、颜色及形制。
  TianHou,  ///< 天候明暗风雨。
  FangWei,  ///< 来去方位。
  QiTa      ///< 其他即时外应。
};

/** 所占迟速，用于参定应期单位。 */
enum class EventPace {
  ZiDong,  ///< 由动静、内外卦和旺衰自动参定。
  JiYing,  ///< 即时之事，以时辰参看。
  JinQi,   ///< 近日之事，以日参看。
  ZhongQi, ///< 中程之事，以月参看。
  YuanQi   ///< 久远之事，以年参看。
};

/** 应期候选的取数来源。 */
enum class TimingKind {
  DongYao,  ///< 动爻数，主近应。
  YongGua,  ///< 用卦先天数，主事应。
  BianGua,  ///< 变卦先天数，主终应。
  TiDeLing, ///< 体卦得令、得生之期。
  QuanGua   ///< 体、用、互、变全卦合数。
};

/** 应期候选单位。 */
enum class TimingUnit {
  Shi,      ///< 时辰。
  Ri,       ///< 日。
  Yue,      ///< 月。
  Nian,     ///< 年。
  ShiHuoRi, ///< 时辰或日。
  RiHuoYue  ///< 日或月。
};

/** 一项起卦时同步取得的外应。 */
struct ExternalOmen {
  OmenKind kind{OmenKind::QiTa};                  ///< 外应门类。
  std::string observation;                        ///< 求测当下所见、所闻之象。
  std::optional<ZhouYi::MeiHua::Trigram> trigram; ///< 已归类的八卦象。
  std::optional<int> number;                      ///< 声音次数、物数等应数。
};

/** 一条可追溯的梅花占断依据。 */
struct MeiHuaBasis {
  BasisKind rule{BasisKind::BenTiYong}; ///< 占断依据门类。
  std::string detail;                   ///< 对应本卦、互卦或变卦的专业说明。
  GuaStage stage{GuaStage::Ben};        ///< 所属卦势阶段。
  BasisDisposition disposition{BasisDisposition::XiangChi}; ///< 作用性质。
};

/** 占问输入。 */
struct AnalysisRequest {
  QuestionKind question_kind{QuestionKind::FanZhan}; ///< 占问门类。
  std::string question;                              ///< 占问原文。
  EventPace event_pace{EventPace::ZiDong};           ///< 所占事情迟速。
  std::vector<ExternalOmen> external_omens;          ///< 起卦同时所得外应。
};

/** 一个外卦对体卦的作用。 */
struct TrigramReading {
  GuaStage stage{GuaStage::Ben};          ///< 本、互、变阶段。
  TrigramRole role{TrigramRole::YongGua}; ///< 经卦职责。
  ZhouYi::MeiHua::Trigram trigram{
      ZhouYi::MeiHua::Trigram::Qian};            ///< 参与作用的八卦。
  TiYongRelation relation{TiYongRelation::BiHe}; ///< 与体卦的五行生克。
  SeasonalStrength seasonal_state{SeasonalStrength::WeiDing}; ///< 月令气势。
  std::string interpretation; ///< 该阶段的卦势断意。
};

/** 互变一卦归党后的有效力量。 */
struct PartyMember {
  GuaStage stage{GuaStage::Ben};          ///< 本、互、变阶段。
  TrigramRole role{TrigramRole::YongGua}; ///< 经卦职责。
  ZhouYi::MeiHua::Trigram trigram{ZhouYi::MeiHua::Trigram::Qian}; ///< 卦象。
  PartySide side{PartySide::Ti};                        ///< 所归体党或用党。
  TiYongRelation relation{TiYongRelation::BiHe};        ///< 对体生克。
  SeasonalStrength strength{SeasonalStrength::WeiDing}; ///< 月令气势。
  double effective_force{}; ///< 结合阶段与旺衰后的有效卦力。
  std::string basis;        ///< 归党与计力依据。
};

/** 体党、用党力量汇总。 */
struct PartyBalance {
  std::vector<PartyMember> members;        ///< 参加归党的本互变诸卦。
  double ti_force{};                       ///< 体党有效卦力。
  double yong_force{};                     ///< 用党有效卦力。
  PartySide dominant{PartySide::XiangChi}; ///< 得势一方或两党相持。
  std::string interpretation;              ///< 党势强弱及制化断意。
};

/** 按占问门类落成的专门断意。 */
struct QuestionReading {
  std::string focus;                          ///< 本门占问的体用落点。
  std::vector<std::string> favorable_signs;   ///< 可取之象。
  std::vector<std::string> obstructive_signs; ///< 阻滞之象。
  std::string interpretation;                 ///< 结合门类后的断语。
};

/** 一项外应与体卦的呼应结果。 */
struct OmenReading {
  OmenKind kind{OmenKind::QiTa};                  ///< 外应门类。
  std::string observation;                        ///< 原始外应描述。
  std::optional<ZhouYi::MeiHua::Trigram> trigram; ///< 所归八卦。
  std::optional<TiYongRelation> relation;         ///< 外应卦对体作用。
  std::optional<int> number;                      ///< 原始外应象数。
  bool favorable{};                               ///< 是否扶体或受体节制。
  std::string interpretation;                     ///< 外应断意。
};

/** 吉凶总断中的一项可追溯卦力。 */
struct JudgmentComponent {
  GuaStage stage{GuaStage::Ben};                        ///< 本、互、变阶段。
  TrigramRole role{TrigramRole::YongGua};               ///< 经卦职责。
  TiYongRelation relation{TiYongRelation::BiHe};        ///< 对体生克。
  SeasonalStrength strength{SeasonalStrength::WeiDing}; ///< 月令气势。
  double relation_value{};                              ///< 生克基础值。
  double seasonal_factor{};                             ///< 旺相休囚死系数。
  double stage_factor{}; ///< 本、互、变阶段系数。
  double contribution{}; ///< 本项进入总断的卦力。
  std::string basis;     ///< 本项算法说明。
};

/** 吉凶总断的完整计算过程。 */
struct JudgmentDetail {
  std::string rule_version{"mei-hua-judgment/1.0"}; ///< 规则版本。
  std::vector<JudgmentComponent> components;        ///< 本互变各项卦力。
  double party_adjustment{};                        ///< 体党用党校正。
  double total{};                                   ///< 综合卦势值。
  std::string formula;                              ///< 计算口径。
  std::string interpretation;                       ///< 分值对应卦意。
};

/** 动爻爻位取象。 */
struct MovingLineReading {
  int position{};             ///< 动爻爻位。
  std::string place;          ///< 初、二、三、四、五、上。
  std::string domain;         ///< 内外、上下及进程取象。
  std::string interpretation; ///< 动爻所示变化方式。
};

/** 应期候选。 */
struct TimingCandidate {
  int priority{};                       ///< 候选次序，从一开始。
  TimingKind kind{TimingKind::DongYao}; ///< 取应门类。
  int number{};                         ///< 取应象数。
  TimingUnit unit{TimingUnit::Ri};      ///< 应期候选单位。
  std::string direction;                ///< 应方。
  std::string trigger;                  ///< 应象触发条件。
  std::string basis;                    ///< 象数取应依据。
};

/** 梅花易数完整占断。 */
struct AnalysisResult {
  std::string schema_version{"mei-hua-analysis/2.0"}; ///< 占断契约版本。
  AnalysisRequest request;                            ///< 占问输入快照。
  TrigramReading ben_yong;                            ///< 本卦体用作用。
  std::vector<TrigramReading> hu_influences;          ///< 互卦上下卦对体作用。
  TrigramReading bian_influence;          ///< 变卦动位之卦对体作用。
  MovingLineReading moving_line;          ///< 动爻取象。
  std::vector<std::string> images;        ///< 体、用、互、变万物类象。
  PartyBalance party_balance;             ///< 体党、用党有效力量。
  QuestionReading question_reading;       ///< 依占问门类形成的专断。
  std::vector<OmenReading> omen_readings; ///< 外应与体卦的呼应。
  std::vector<MeiHuaBasis> mei_hua_basis; ///< 全部梅花断验依据。
  std::vector<TimingCandidate> timing;    ///< 应期候选。
  Judgment judgment{Judgment::DaiBian};   ///< 综合卦势。
  JudgmentDetail judgment_detail;         ///< 吉凶总断的计算依据。
  std::string conclusion;                 ///< 子句式总断。
  std::vector<std::string> review_points; ///< 须随占问背景参看的事项。
};

} // namespace ZhouYi::MeiHuaAnalysis

namespace ZhouYi::Mapper {

/** QuestionKind 的梅花专业中文映射。 */
template <> struct ZhMap<ZhouYi::MeiHuaAnalysis::QuestionKind> {
  static constexpr auto get_map() {
    return std::array<std::string_view,
                      ZhouYi::MeiHuaAnalysis::question_kind_count>{
        "泛占",     "功名事业", "求财经营", "婚恋关系", "疾病医药",
        "出行迁动", "学业考试", "寻人失物", "天时晴雨", "人事进退",
        "家宅人口", "屋舍营造", "生产孕育", "饮食宴享", "求谋成败",
        "交易买卖", "行人归期", "谒见访求", "官讼争端", "坟墓葬地"};
  }
};

/** GuaStage 的梅花专业中文映射。 */
template <> struct ZhMap<ZhouYi::MeiHuaAnalysis::GuaStage> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 3>{"本卦", "互卦", "变卦"};
  }
};

/** TrigramRole 的梅花专业中文映射。 */
template <> struct ZhMap<ZhouYi::MeiHuaAnalysis::TrigramRole> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 5>{"体卦根本", "本卦之用", "下互卦",
                                           "上互卦", "变卦之用"};
  }
};

/** BasisKind 的梅花专业中文映射。 */
template <> struct ZhMap<ZhouYi::MeiHuaAnalysis::BasisKind> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 6>{"本卦体用", "下互作用", "上互作用",
                                           "变卦归宿", "体党用党", "外应参断"};
  }
};

/** TiYongRelation 的梅花专业中文映射。 */
template <> struct ZhMap<ZhouYi::MeiHuaAnalysis::TiYongRelation> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 5>{"体用比和", "用生体", "体生用",
                                           "体克用", "用克体"};
  }
};

/** Judgment 的梅花专业中文映射。 */
template <> struct ZhMap<ZhouYi::MeiHuaAnalysis::Judgment> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 6>{"大吉", "吉", "平",
                                           "有阻", "凶", "待辨"};
  }
};

/** SeasonalStrength 的梅花专业中文映射。 */
template <> struct ZhMap<ZhouYi::MeiHuaAnalysis::SeasonalStrength> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 6>{"旺", "相", "休",
                                           "囚", "死", "未定"};
  }
};

/** PartySide 的梅花专业中文映射。 */
template <> struct ZhMap<ZhouYi::MeiHuaAnalysis::PartySide> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 3>{"体党", "用党", "两党相持"};
  }
};

/** BasisDisposition 的梅花专业中文映射。 */
template <> struct ZhMap<ZhouYi::MeiHuaAnalysis::BasisDisposition> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 3>{"助益", "阻滞", "相持待参"};
  }
};

/** OmenKind 的梅花专业中文映射。 */
template <> struct ZhMap<ZhouYi::MeiHuaAnalysis::OmenKind> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 7>{"声音外应", "人物外应", "动物外应",
                                           "器物外应", "天候外应", "方位外应",
                                           "其他外应"};
  }
};

/** EventPace 的梅花专业中文映射。 */
template <> struct ZhMap<ZhouYi::MeiHuaAnalysis::EventPace> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 5>{"自动参定", "即时", "近日", "中程",
                                           "久远"};
  }
};

/** TimingKind 的梅花专业中文映射。 */
template <> struct ZhMap<ZhouYi::MeiHuaAnalysis::TimingKind> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 5>{"动爻近应", "用卦事应", "变卦终应",
                                           "体卦旺应", "全卦数应"};
  }
};

/** TimingUnit 的梅花专业中文映射。 */
template <> struct ZhMap<ZhouYi::MeiHuaAnalysis::TimingUnit> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 6>{"时", "日",     "月",
                                           "年", "时或日", "日或月"};
  }
};

} // namespace ZhouYi::Mapper
