// C++23 Module - 六爻分析契约
export module ZhouYi.LiuYaoAnalysis.Contract;

import ZhouYi.ZhMapper;
import std;

/**
 * @brief 六爻分析的稳定数据契约。
 *
 * 本模块只定义占问输入和断卦结果，不承担装卦、规则计算或中文展示。
 */
export namespace ZhouYi::LiuYaoAnalysis {

/** @brief 占问门类，用于依六亲确定用神。 */
enum class QuestionKind {
  ZiShen,         ///< 自身、泛占，以世爻为用。
  CaiYun,         ///< 财运，以妻财为用。
  GongMing,       ///< 功名事业，以官鬼为用。
  HunLianNan,     ///< 男问婚恋，以妻财为用。
  HunLianNv,      ///< 女问婚恋，以官鬼为用。
  JiBing,         ///< 疾病，以世爻为身、官鬼为病、子孙为医药。
  ZiNv,           ///< 子女，以子孙为用。
  FuMuWenShu,     ///< 父母、文书、房宅，以父母为用。
  XiongDiPengYou, ///< 兄弟朋友，以兄弟为用。
  GuanSi,         ///< 官讼，以官鬼为事、世应为双方。
  ShiWu           ///< 失物，以妻财为用。
};

/** @brief 爻在月建日辰下的气势。 */
enum class YaoStrength {
  WangXiang, ///< 旺相，得月日生扶或临月日。
  YouQi,     ///< 有气，虽不当令但尚有根援。
  XiuQiu,    ///< 休囚，受泄耗或不得月日。
  ShuaiRuo   ///< 衰弱，兼逢空破或重克。
};

/** @brief 用神在卦中的显隐状态。 */
enum class YongShenState {
  Xian,   ///< 用神上卦明现。
  FuCang, ///< 用神不上卦而伏藏。
  BuXian  ///< 用神与伏神皆不现。
};

/** @brief 单项作用的趋向。 */
enum class EffectNature {
  ShengFu, ///< 生扶、拱助或合起。
  KeZhi,   ///< 克制、冲破、刑害。
  YinDong, ///< 引动而吉凶未可一概而论。
  Ping     ///< 未形成显著作用。
};

/** @brief 综合断卦倾向。 */
enum class Judgment {
  Ji,        ///< 吉。
  PianJi,    ///< 偏吉。
  Ping,      ///< 平。
  PianXiong, ///< 偏凶。
  Xiong,     ///< 凶。
  DaiDing    ///< 用神不明或证据相持，待定。
};

/** @brief 六爻分析请求。 */
struct AnalysisRequest {
  QuestionKind question_kind{QuestionKind::ZiShen}; ///< 占问门类。
  std::string question; ///< 可选的占问原文，仅用于报告留档。
  std::optional<int>
      specified_yong_shen_position; ///< 人工指定用神爻位（1—6）。
};

/** @brief 一条可追溯的卦爻依据。 */
struct GuaYaoBasis {
  std::string rule;                        ///< 规则名称，如“月破”“回头生”。
  std::string detail;                      ///< 以六爻术语描述的具体作用。
  EffectNature nature{EffectNature::Ping}; ///< 该作用的趋向。
  std::vector<int> positions;              ///< 涉及的爻位；日月作用可为空。
};

/** @brief 单爻分析结果。 */
struct YaoAnalysis {
  int position{};                            ///< 爻位（1—6）。
  std::string relative;                      ///< 本卦六亲。
  std::string branch;                        ///< 本卦地支。
  YaoStrength strength{YaoStrength::XiuQiu}; ///< 月日综合气势。
  bool is_shi{};                             ///< 是否世爻。
  bool is_ying{};                            ///< 是否应爻。
  bool is_changing{};                        ///< 是否动爻。
  bool is_xun_kong{};                        ///< 是否落日旬空。
  bool is_month_broken{};                    ///< 是否受月建冲破。
  bool is_day_broken{};                      ///< 是否受日辰冲破。
  std::string transformation;                ///< 动化结果，如“回头生”“回头克”。
  std::vector<GuaYaoBasis> gua_yao_basis;    ///< 本爻全部卦爻依据。
};

/** @brief 被选中的用神。 */
struct YongShenSelection {
  std::string target;                         ///< 目标六亲或“世爻”。
  YongShenState state{YongShenState::BuXian}; ///< 明现、伏藏或不现。
  std::optional<int> position;                ///< 用神爻位；不现时为空。
  std::string branch;                         ///< 用神地支；伏藏时为伏神地支。
  std::optional<YaoStrength> strength;        ///< 用神自身在月建日辰下的气势。
  bool manually_specified{};                  ///< 是否由调用方人工指定。
  std::string reason;                         ///< 取用与择爻依据。
};

/** @brief 六爻完整分析结果。 */
struct AnalysisResult {
  std::string schema_version{"liu-yao-analysis/1.0"}; ///< 输出契约版本。
  AnalysisRequest request;                            ///< 本次占问输入快照。
  std::string main_hexagram;                          ///< 本卦名称。
  std::optional<std::string> changed_hexagram; ///< 变卦名称；无动爻时为空。
  YongShenSelection yong_shen;                 ///< 用神选择结果。
  std::vector<YaoAnalysis> yao;           ///< 六爻逐爻分析（从初爻到上爻）。
  std::vector<GuaYaoBasis> relations;     ///< 世用、世应及动爻作用汇总。
  Judgment judgment{Judgment::DaiDing};   ///< 综合趋向。
  std::string conclusion;                 ///< 面向读者的子句式断语。
  std::vector<std::string> review_points; ///< 需结合占问背景复核的事项。
};

} // namespace ZhouYi::LiuYaoAnalysis

namespace ZhouYi::Mapper {

/** QuestionKind 的六爻专业中文映射。 */
template <> struct ZhMap<ZhouYi::LiuYaoAnalysis::QuestionKind> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 11>{
        "自身泛占", "财运",     "功名事业", "男问婚恋", "女问婚恋", "疾病",
        "子女",     "父母文书", "兄弟朋友", "官讼",     "失物"};
  }
};

/** YaoStrength 的六爻专业中文映射。 */
template <> struct ZhMap<ZhouYi::LiuYaoAnalysis::YaoStrength> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 4>{"旺相", "有气", "休囚", "衰弱"};
  }
};

/** YongShenState 的六爻专业中文映射。 */
template <> struct ZhMap<ZhouYi::LiuYaoAnalysis::YongShenState> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 3>{"用神明现", "用神伏藏", "用神不现"};
  }
};

/** EffectNature 的六爻专业中文映射。 */
template <> struct ZhMap<ZhouYi::LiuYaoAnalysis::EffectNature> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 4>{"生扶", "克制", "引动", "平"};
  }
};

/** Judgment 的六爻专业中文映射。 */
template <> struct ZhMap<ZhouYi::LiuYaoAnalysis::Judgment> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 6>{"吉",   "偏吉", "平",
                                           "偏凶", "凶",   "待定"};
  }
};

} // namespace ZhouYi::Mapper
