/**
 * @file qi_men_analysis_contract.cppm
 * @brief 奇门遁甲占断的稳定输入与输出契约。
 */
export module ZhouYi.QiMen.Analysis.Contract;

export import ZhouYi.QiMen;

import ZhouYi.GanZhi;
import ZhouYi.ZhMapper;
import std;

export namespace ZhouYi::QiMenAnalysis {

using ZhouYi::GanZhi::TianGan;
using ZhouYi::QiMen::Gate;
using ZhouYi::QiMen::Palace;
using ZhouYi::QiMen::PatternFinding;
using ZhouYi::QiMen::Spirit;
using ZhouYi::QiMen::Star;
using ZhouYi::QiMen::Strength;

/** 占问门类，用于确定专用用神，不改变排盘。 */
enum class QuestionKind {
  FanZhan,  ///< 未限定事项的泛占。
  GongMing, ///< 功名、事业、任用。
  QiuCai,   ///< 求财、交易、回款。
  HunLian,  ///< 婚恋、对象、关系。
  JiBing,   ///< 疾病、医药、身体。
  ChuXing,  ///< 出行、迁移、行人。
  GuanSong, ///< 官讼、争议、规则。
  XueYe,    ///< 学业、考试、文凭。
  XunWu     ///< 寻人、失物。
};

/** 用神在占断中的职责。 */
enum class YongShenRole {
  RiGan,         ///< 日干，求测之人。
  ShiGan,        ///< 时干，所占之事。
  NianMing,      ///< 年命，求测者个体落宫。
  ZhiFu,         ///< 值符，主全局大势。
  ZhiShi,        ///< 值使，主事情门径。
  ZhuanYongMen,  ///< 占类专用八门。
  ZhuanYongXing, ///< 占类专用九星。
  ZhuanYongShen, ///< 占类专用八神。
  ZhuanYongGan   ///< 占类专用奇仪。
};

/** 两宫五行在主客之间的作用。 */
enum class PalaceRelation {
  TongGong,   ///< 主客同宫，彼此相依。
  BiHe,       ///< 两宫同气比和。
  ZhuShengKe, ///< 主宫生客宫，我去生事。
  KeShengZhu, ///< 客宫生主宫，事来生我。
  ZhuKeKe,    ///< 主宫克客宫，我能制事。
  KeKeZhu     ///< 客宫克主宫，事来制我。
};

/** 一条占断依据所呈现的作用。 */
enum class EffectNature {
  ZhuLi,   ///< 得门、得星、得神或得格相助。
  ZhiAi,   ///< 受克、入墓、击刑、门迫或空亡。
  YinDong, ///< 驿马、合冲或主客往来所致引动。
  DaiBian  ///< 吉凶随占类、旺衰或制化而变。
};

/** 综合盘势，不以单一分数抵销吉格与凶格。 */
enum class Judgment {
  DeShi,           ///< 得势，主用得地并有生扶。
  YouLi,           ///< 有利，助力多于制碍。
  JiXiongBingJian, ///< 吉凶并见，须分先后与制化。
  ShouZu,          ///< 受阻，主用受制较重。
  DaiDing          ///< 占类或个体信息不足，暂不落断。
};

/** 奇门占断请求。 */
struct AnalysisRequest {
  QuestionKind question_kind{QuestionKind::FanZhan}; ///< 占问门类。
  std::string question;                              ///< 占问原文。
  std::optional<TianGan> nian_ming; ///< 求测者出生年干；未提供不推测。
};

/** 可追溯的奇门盘局依据。 */
struct PanJuBasis {
  std::string rule;                           ///< 专业规则名称。
  std::string detail;                         ///< 盘局中的具体成因。
  EffectNature nature{EffectNature::DaiBian}; ///< 对主用的作用。
  std::optional<Palace> palace;               ///< 对应落宫；全局断法为空。
};

/** 一项用神取宫结果。 */
struct YongShenSelection {
  YongShenRole role{YongShenRole::RiGan}; ///< 用神职责。
  std::string name;                       ///< 专业名称。
  Palace palace{Palace::Center};          ///< 用神落宫。
  std::string basis;                      ///< 取用与遁甲依据。
};

/** 单宫组合的占断结果。 */
struct PalaceReading {
  Palace palace{Palace::Center};             ///< 所断宫位。
  std::vector<YongShenRole> roles;           ///< 本宫承载的用神职责。
  Star star{Star::TianQin};                  ///< 九星。
  Gate gate{Gate::None};                     ///< 八门。
  Spirit spirit{Spirit::None};               ///< 八神。
  TianGan heaven_stem{TianGan::Jia};         ///< 天盘奇仪。
  TianGan earth_stem{TianGan::Jia};          ///< 地盘奇仪。
  Strength star_strength{Strength::Unknown}; ///< 星旺衰。
  Strength gate_strength{Strength::Unknown}; ///< 门旺衰。
  std::vector<PatternFinding> patterns;      ///< 本宫已成格局。
  std::vector<PanJuBasis> favorable;         ///< 生扶、得地与救应。
  std::vector<PanJuBasis> unfavorable;       ///< 克制、门迫与刑墓空。
  std::string image;                         ///< 门、星、神、仪合参应象。
};

/** 日干主宫与时干事宫的主客合参。 */
struct MainGuestReading {
  Palace host_palace{Palace::Center};            ///< 日干主宫。
  Palace guest_palace{Palace::Center};           ///< 时干事宫。
  PalaceRelation relation{PalaceRelation::BiHe}; ///< 主客生克。
  std::string interpretation;                    ///< 主客关系断意。
  std::vector<PanJuBasis> pan_ju_basis;          ///< 主客合参依据。
};

/** 一个占类的应象归纳。 */
struct Manifestation {
  QuestionKind kind{QuestionKind::FanZhan}; ///< 所属占类。
  std::string subject;                      ///< 所断事项。
  std::string indication;                   ///< 盘局所示趋向。
  std::vector<PanJuBasis> pan_ju_basis;     ///< 支撑该应象的盘局依据。
};

/** 依用神宫方隅、宫支与空马旺衰推得的应期方位候选。 */
struct TimingDirection {
  int priority{};                                      ///< 从 1 开始连续排列。
  Palace palace{Palace::Center};                       ///< 候应宫位。
  std::string direction;                               ///< 后天宫方位。
  std::vector<ZhouYi::GanZhi::DiZhi> trigger_branches; ///< 候应地支。
  std::string pace;                                    ///< 速、缓或待填实。
  std::string condition; ///< 值期、冲实或引动条件。
  std::string basis;     ///< 候选排序依据。
};

/** 完整奇门占断结果。 */
struct AnalysisResult {
  std::string schema_version{"qi-men-analysis/2.0"}; ///< 契约版本。
  AnalysisRequest request;                           ///< 占问输入快照。
  std::vector<YongShenSelection> yong_shen;          ///< 用神落宫。
  std::vector<PalaceReading> palace_readings;        ///< 主用诸宫合参。
  MainGuestReading main_guest;                       ///< 日时主客关系。
  std::vector<PanJuBasis> global_configuration;      ///< 全局格局与时势。
  std::vector<PanJuBasis> favorable;                 ///< 全盘助力与救应。
  std::vector<PanJuBasis> unfavorable;               ///< 全盘制碍与闭塞。
  std::vector<Manifestation> manifestations;         ///< 分门应象。
  std::vector<TimingDirection> timing;               ///< 应期与方位候选。
  Judgment judgment{Judgment::DaiDing};              ///< 综合盘势。
  std::string conclusion;                            ///< 总断。
  std::vector<std::string> review_points;            ///< 待补信息与复核项。
};

} // namespace ZhouYi::QiMenAnalysis

namespace ZhouYi::Mapper {

/** QuestionKind 的奇门占问门类中文映射。 */
template <> struct ZhMap<ZhouYi::QiMenAnalysis::QuestionKind> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 9>{"泛占",     "功名事业", "求财经营",
                                           "婚恋关系", "疾病医药", "出行迁动",
                                           "官讼争议", "学业考试", "寻人失物"};
  }
};

/** YongShenRole 的用神职责中文映射。 */
template <> struct ZhMap<ZhouYi::QiMenAnalysis::YongShenRole> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 9>{"日干",   "时干",   "年命",
                                           "值符",   "值使",   "专用门",
                                           "专用星", "专用神", "专用奇仪"};
  }
};

/** PalaceRelation 的主客生克中文映射。 */
template <> struct ZhMap<ZhouYi::QiMenAnalysis::PalaceRelation> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 6>{"同宫",   "比和",   "主生客",
                                           "客生主", "主克客", "客克主"};
  }
};

/** EffectNature 的占断作用中文映射。 */
template <> struct ZhMap<ZhouYi::QiMenAnalysis::EffectNature> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 4>{"助力", "制碍", "引动", "待辨"};
  }
};

/** Judgment 的综合盘势中文映射。 */
template <> struct ZhMap<ZhouYi::QiMenAnalysis::Judgment> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 5>{"得势", "有利", "吉凶并见", "受阻",
                                           "待定"};
  }
};

} // namespace ZhouYi::Mapper
