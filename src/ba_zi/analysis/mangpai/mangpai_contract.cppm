// C++23 Module - 盲派分析数据契约
// 盲派结论独立于子平强弱、格局和用神数据结构。
export module ZhouYi.BaZiAnalysis.MangPai.Contract;

export import ZhouYi.BaZiAnalysis.Contract;
import std;

export namespace ZhouYi::BaZiAnalysis {

/** 盲派宫位的固定语义与主宾角色。 */
struct BlindPalaceAssignment {
  int position{};     ///< 柱位：0年、1月、2日、3时。
  std::string palace; ///< 宫位名称。
  std::string role;   ///< 主位、宾位或主宾交界。
  std::string reason; ///< 主宾判定依据。
};

/** 盲派单条做功链摘要。 */
struct BlindWorkSummary {
  std::string source;                     ///< 发起作用的干支或组合。
  std::string target;                     ///< 被作用的干支或组合。
  std::string direction;                  ///< 主→宾、宾→主或主位内部。
  std::string relation;                   ///< 制、合、冲、刑、穿、生、墓等。
  std::string result;                     ///< 由做功推导的主要人事结果。
  int source_position{-1};                ///< 作用方柱位；未知时为 -1。
  int target_position{-1};                ///< 被作用方柱位；未知时为 -1。
  std::string source_ten_god;             ///< 作用方相对日主的十神。
  std::string target_ten_god;             ///< 被作用方相对日主的十神。
  double effective_power{};               ///< 双方有效力量的保守值。
  bool effective{};                       ///< 是否形成有效做功。
  std::vector<MingLiBasis> ming_li_basis; ///< 支撑做功判定的命理依据。
};

/** 盲派结构映射出的应事分项。 */
struct BlindEventImpact {
  std::string type;                       ///< 财务、事业、婚姻、身体或迁动。
  double pressure{};                      ///< 结构压力，不代表事件概率。
  std::vector<std::string> triggers;      ///< 触发该分项的做功链。
  std::vector<std::string> ming_li_basis; ///< 面向展示层的应象依据。
};

/** 盲派墓库的关系与开闭状态。 */
struct BlindTombSummary {
  std::string tomb_branch;      ///< 墓库地支。
  std::string stored_stem;      ///< 被墓收纳的天干。
  int tomb_position{-1};        ///< 墓库柱位。
  int stored_stem_position{-1}; ///< 被墓天干柱位。
  std::string relation;         ///< 冲、刑、合、穿或无。
  std::string state;            ///< 闭库、开库、破库或待复核。
  bool opened{};                ///< 是否存在明确开库作用。
  std::string ming_li_basis;    ///< 判定墓库开闭的命理依据。
};

/** 完整盲派理论层结果。 */
struct BlindAnalysisDetail {
  std::string rule_set{"mangpai-v1"};          ///< 盲派规则集版本。
  std::vector<BlindPalaceAssignment> palaces;  ///< 宫位与主宾角色。
  std::vector<std::string> body;               ///< 体的清单。
  std::vector<std::string> targets;            ///< 用的清单。
  std::vector<BlindWorkSummary> work_chains;   ///< 主、辅做功链。
  std::vector<BlindEventImpact> event_impacts; ///< 应事类型分项。
  std::vector<BlindTombSummary> tombs;         ///< 墓库状态明细。
  std::vector<std::string> useful_gods;        ///< 功神摘要。
  std::vector<std::string> waste_gods;         ///< 废神摘要。
  std::vector<std::string> structures;         ///< 做功结构。
  std::vector<std::string> symbols;            ///< 人事取象。
  std::vector<std::string> transit_impacts;    ///< 岁运引动摘要。
  std::vector<std::string> warnings;           ///< 待复核事项。
};

} // namespace ZhouYi::BaZiAnalysis
