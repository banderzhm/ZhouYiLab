// C++23 - 盲派做功结构实现
module ZhouYi.BaZiAnalysis.MangPai.Structure;

import ZhouYi.GanZhi;
import ZhouYi.BaZiAnalysis.MangPai.Common;
import std;

namespace ZhouYi::BaZiAnalysis::MangPai::Structure {
namespace {
using ZhouYi::GanZhi::ShiShen;

bool has(const AnalysisResult &result, ShiShen god) {
  return std::any_of(result.ten_god_occurrences.begin(),
                     result.ten_god_occurrences.end(),
                     [god](const auto &item) { return item.ten_god == god; });
}

double power(const AnalysisResult &result,
             std::initializer_list<ShiShen> gods) {
  return std::accumulate(
      gods.begin(), gods.end(), 0.0, [&](double total, ShiShen god) {
        return total + std::accumulate(result.ten_god_occurrences.begin(),
                                       result.ten_god_occurrences.end(), 0.0,
                                       [god](double value, const auto &item) {
                                         return value +
                                                (item.ten_god == god
                                                     ? item.effective_power
                                                     : 0.0);
                                       });
      });
}

void add(AnalysisResult &result, std::string text) {
  result.blind_analysis->structures.push_back(std::move(text));
}
void add_event(AnalysisResult &result, std::string type, double pressure,
               std::string trigger, std::string evidence) {
  auto &events = result.blind_analysis->event_impacts;
  const auto it =
      std::find_if(events.begin(), events.end(),
                   [&](const auto &item) { return item.type == type; });
  if (it == events.end()) {
    events.push_back({std::move(type),
                      pressure,
                      {std::move(trigger)},
                      {std::move(evidence)}});
  } else {
    it->pressure += pressure;
    it->triggers.push_back(std::move(trigger));
    it->evidence.push_back(std::move(evidence));
  }
}

bool participates_in_work(const TenGodOccurrence &occurrence,
                          const std::vector<BlindWorkSummary> &works) {
  return std::any_of(works.begin(), works.end(), [&](const auto &work) {
    return work.source.find(occurrence.position) != std::string::npos ||
           work.target.find(occurrence.position) != std::string::npos;
  });
}

void classify_waste(AnalysisResult &result) {
  auto &blind = *result.blind_analysis;
  // 没有有效做功链时，不能把所有未参与关系的十神直接称为废神；
  // 此时只能说明做功条件尚未落实，等待根气、空亡和岁运复核。
  if (!std::any_of(blind.work_chains.begin(), blind.work_chains.end(),
                   [](const auto &work) { return work.effective; })) {
    blind.warnings.push_back("当前没有已确认的有效做功链，暂不判定废神");
    return;
  }
  for (const auto &occurrence : result.ten_god_occurrences) {
    // 体神和目标用是独立于宫位的两条轴：体（印、比、食伤）与用（财、官、杀）
    // 均不得被归为废神；只有不参与做功且不属于体用的闲字才可列废。
    if (Common::is_body_god(occurrence.ten_god) ||
        Common::is_target_god(occurrence.ten_god))
      continue;
    if (participates_in_work(occurrence, blind.work_chains))
      continue;
    const auto text = occurrence.position + "的" +
                      std::string(shi_shen_to_zh(occurrence.ten_god));
    if (std::find(blind.waste_gods.begin(), blind.waste_gods.end(), text) ==
        blind.waste_gods.end())
      blind.waste_gods.push_back(text);
  }
}
} // namespace

void build(AnalysisResult &result) {
  if (!result.blind_analysis)
    return;
  const bool food = has(result, ShiShen::ShiShen);
  const bool hurting = has(result, ShiShen::ShangGuan);
  const bool killer = has(result, ShiShen::QiSha);
  const bool seal =
      has(result, ShiShen::ZhengYin) || has(result, ShiShen::PianYin);
  const bool wealth =
      has(result, ShiShen::ZhengCai) || has(result, ShiShen::PianCai);
  const bool peers =
      has(result, ShiShen::BiJian) || has(result, ShiShen::JieCai);

  if (food && killer)
    add(result, "食神制杀候选：需验证食神是否有力并实际制住七杀");
  if (killer && seal)
    add(result, "杀印相生候选：需验证印星是否承接七杀并回到主位");
  if ((food || hurting) && wealth)
    add(result, "食伤生财候选：需验证输出是否真正落到财星");
  if (peers && wealth)
    add(result, "比劫夺财风险：需验证财星是否被主位有效控制");
  const double peer_power = power(result, {ShiShen::BiJian, ShiShen::JieCai});
  const double wealth_power =
      power(result, {ShiShen::ZhengCai, ShiShen::PianCai});
  const double food_power = power(result, {ShiShen::ShiShen});
  const double hurting_power = power(result, {ShiShen::ShangGuan});
  const double killer_power = power(result, {ShiShen::QiSha});
  if (peer_power >= 0.5 && wealth_power >= 0.5)
    add_event(result, "财务·收入受阻", 1.0, "比劫与财星同现",
              "竞争者或分夺关系可能使财源难以落袋");
  if ((food_power + hurting_power) >= 0.5 && wealth_power >= 0.5)
    add_event(result, "事业·技术输出", 0.8, "食伤与财星同现",
              "技能、产品或表达具备转化为收入的路径");
  if (food_power >= 0.5 && killer_power >= 0.5)
    add_event(result, "事业·规则压力", 0.7, "食神与七杀同现",
              "专业能力可能用于处理制度、压力或竞争");
  if (result.blind_analysis->structures.empty())
    add(result, "未归纳出明确的十神主结构，需结合具体做功链人工复核");
  classify_waste(result);
}
} // namespace ZhouYi::BaZiAnalysis::MangPai::Structure
