module ZhouYi.QiMen.Analysis.Presenter;

import ZhouYi.GanZhi;
import ZhouYi.ZhMapper;
import fmt;
import std;

namespace ZhouYi::QiMenAnalysis {
namespace {
using namespace ZhouYi::QiMen;
using namespace ZhouYi::Mapper;

std::string gan(ZhouYi::GanZhi::TianGan value) {
  return std::string(ZhouYi::GanZhi::Mapper::to_zh(value));
}

std::string zhi(ZhouYi::GanZhi::DiZhi value) {
  return std::string(ZhouYi::GanZhi::Mapper::to_zh(value));
}

std::string
joined_branches(const std::vector<ZhouYi::GanZhi::DiZhi> &branches) {
  std::string result;
  for (std::size_t i = 0; i < branches.size(); ++i) {
    if (i)
      result += "、";
    result += zhi(branches[i]);
  }
  return result.empty() ? "中宫随寄宫" : result;
}

std::string roles_text(const std::vector<YongShenRole> &roles) {
  std::string result;
  for (std::size_t i = 0; i < roles.size(); ++i) {
    if (i)
      result += "、";
    result += to_zh(roles[i]);
  }
  return result.empty() ? "—" : result;
}

nlohmann::json evidence_json(const PanJuBasis &item) {
  return {
      {"断法", item.rule},
      {"盘局依据", item.detail},
      {"作用", to_zh(item.nature)},
      {"落宫", item.palace ? std::string(palace_name(*item.palace)) : "全局"}};
}

void write_evidence(std::ostream &output, const std::vector<PanJuBasis> &items,
                    std::string_view empty_text) {
  if (items.empty()) {
    output << empty_text << '\n';
    return;
  }
  for (const auto &item : items)
    output << "- " << item.rule << "【" << to_zh(item.nature) << "】："
           << item.detail << '\n';
}

std::string palace_marks(const PalaceInfo &palace) {
  std::vector<std::string_view> marks;
  if (palace.is_zhi_fu)
    marks.emplace_back("值符");
  if (palace.is_zhi_shi)
    marks.emplace_back("值使");
  if (palace.is_void)
    marks.emplace_back("空亡");
  if (palace.is_post_horse)
    marks.emplace_back("驿马");
  if (palace.has_gate_pressure)
    marks.emplace_back("门迫");
  if (palace.has_liu_yi_ji_xing)
    marks.emplace_back("击刑");
  if (palace.has_san_qi_tomb)
    marks.emplace_back("入墓");
  std::string result;
  for (std::size_t i = 0; i < marks.size(); ++i) {
    if (i)
      result += "、";
    result += marks[i];
  }
  return result.empty() ? "—" : result;
}

void write_palace_cell(std::ostream &output, const PalaceInfo &palace) {
  output << palace_name(palace.palace) << "<br>" << spirit_name(palace.spirit)
         << "·" << star_name(palace.star) << "·" << gate_name(palace.gate)
         << "<br>天盘" << gan(palace.tian_gan) << "／地盘" << gan(palace.di_gan)
         << "<br>" << palace_marks(palace);
}
} // namespace

nlohmann::json to_zh_json(const QiMenPan &pan, const AnalysisResult &analysis) {
  nlohmann::json output;
  output["契约版本"] = analysis.schema_version;
  output["占问"] = {{"门类", to_zh(analysis.request.question_kind)},
                    {"所问", analysis.request.question},
                    {"年命", analysis.request.nian_ming
                                 ? gan(*analysis.request.nian_ming)
                                 : "未提供"}};
  nlohmann::json pan_json = pan;
  output["排盘"] = std::move(pan_json);
  output["用神落宫"] = nlohmann::json::array();
  for (const auto &item : analysis.yong_shen)
    output["用神落宫"].push_back({{"职责", to_zh(item.role)},
                                  {"用神", item.name},
                                  {"落宫", palace_name(item.palace)},
                                  {"取用", item.basis}});
  output["日时主客"] = {{"主宫", palace_name(analysis.main_guest.host_palace)},
                        {"客宫", palace_name(analysis.main_guest.guest_palace)},
                        {"生克", to_zh(analysis.main_guest.relation)},
                        {"断意", analysis.main_guest.interpretation}};
  output["主用诸宫"] = nlohmann::json::array();
  for (const auto &reading : analysis.palace_readings) {
    nlohmann::json favorable = nlohmann::json::array();
    nlohmann::json unfavorable = nlohmann::json::array();
    for (const auto &item : reading.favorable)
      favorable.push_back(evidence_json(item));
    for (const auto &item : reading.unfavorable)
      unfavorable.push_back(evidence_json(item));
    output["主用诸宫"].push_back(
        {{"宫位", palace_name(reading.palace)},
         {"职责", roles_text(reading.roles)},
         {"九星", star_name(reading.star)},
         {"八门", gate_name(reading.gate)},
         {"八神", spirit_name(reading.spirit)},
         {"天盘奇仪", gan(reading.heaven_stem)},
         {"地盘奇仪", gan(reading.earth_stem)},
         {"星气", strength_name(reading.star_strength)},
         {"门气", strength_name(reading.gate_strength)},
         {"宫象", reading.image},
         {"生扶救应", std::move(favorable)},
         {"克制闭塞", std::move(unfavorable)}});
  }
  output["应期方位"] = nlohmann::json::array();
  for (const auto &item : analysis.timing)
    output["应期方位"].push_back(
        {{"次序", item.priority},
         {"宫位", palace_name(item.palace)},
         {"方位", item.direction},
         {"候支", joined_branches(item.trigger_branches)},
         {"迟速", item.pace},
         {"触发", item.condition},
         {"依据", item.basis}});
  output["总断"] = {{"盘势", to_zh(analysis.judgment)},
                    {"断语", analysis.conclusion},
                    {"复核", analysis.review_points}};
  return output;
}

void write_zh(std::ostream &output, const QiMenPan &pan,
              const AnalysisResult &analysis) {
  output << "==================== 奇门遁甲排盘与占断 ====================\n\n";
  output << "【占问资料】\n";
  output << "占类：" << to_zh(analysis.request.question_kind) << '\n';
  if (!analysis.request.question.empty())
    output << "所问：" << analysis.request.question << '\n';
  output << "年命："
         << (analysis.request.nian_ming ? gan(*analysis.request.nian_ming)
                                        : "未提供")
         << '\n';

  output << "\n【起局资料】\n";
  output << "公历："
         << fmt::format("{:04}-{:02}-{:02} {:02}:{:02}", pan.solar_year,
                        pan.solar_month, pan.solar_day, pan.hour, pan.minute)
         << '\n';
  if (pan.ba_zi)
    output << "四柱：" << pan.ba_zi->year.to_string() << " / "
           << pan.ba_zi->month.to_string() << " / "
           << pan.ba_zi->day.to_string() << " / " << pan.ba_zi->hour.to_string()
           << '\n';
  output << "节气：" << solar_term_name(pan.solar_term) << '\n';
  output << "遁局：" << to_zh(pan.dun) << static_cast<int>(pan.ju) << "局·"
         << yuan_name(pan.yuan) << '\n';
  output << "旬首：" << jia_xun_name(pan.xun_shou) << "遁"
         << gan(pan.xun_hidden_gan) << '\n';
  output << "值符：" << star_name(pan.zhi_fu_star) << "落"
         << palace_name(pan.zhi_fu_palace) << '\n';
  output << "值使：" << gate_name(pan.zhi_shi_gate) << "落"
         << palace_name(pan.zhi_shi_palace) << '\n';

  output << "\n【九宫排盘】\n";
  output << "| 巽四宫 | 离九宫 | 坤二宫 |\n| --- | --- | --- |\n| ";
  write_palace_cell(output, pan.palaces[3]);
  output << " | ";
  write_palace_cell(output, pan.palaces[8]);
  output << " | ";
  write_palace_cell(output, pan.palaces[1]);
  output << " |\n| 震三宫 | 中五宫 | 兑七宫 |\n| ";
  write_palace_cell(output, pan.palaces[2]);
  output << " | ";
  write_palace_cell(output, pan.palaces[4]);
  output << " | ";
  write_palace_cell(output, pan.palaces[6]);
  output << " |\n| 艮八宫 | 坎一宫 | 乾六宫 |\n| ";
  write_palace_cell(output, pan.palaces[7]);
  output << " | ";
  write_palace_cell(output, pan.palaces[0]);
  output << " | ";
  write_palace_cell(output, pan.palaces[5]);
  output << " |\n";

  output << "\n【用神落宫】\n";
  output << "| 职责 | 用神 | 落宫 | 取用依据 |\n| --- | --- | --- | --- |\n";
  for (const auto &item : analysis.yong_shen)
    output << "| " << to_zh(item.role) << " | " << item.name << " | "
           << palace_name(item.palace) << " | " << item.basis << " |\n";

  output << "\n【日时主客】\n";
  output << "主宫：" << palace_name(analysis.main_guest.host_palace)
         << "；客宫：" << palace_name(analysis.main_guest.guest_palace)
         << "；主客：" << to_zh(analysis.main_guest.relation) << "。\n";
  output << "断意：" << analysis.main_guest.interpretation << '\n';

  output << "\n【主用诸宫合参】\n";
  output << "| 宫位 | 用神职责 | 门星神仪 | 旺衰 | 空墓刑迫 | 宫象 |\n";
  output << "| --- | --- | --- | --- | --- | --- |\n";
  for (const auto &reading : analysis.palace_readings) {
    const auto &source =
        pan.palaces[get_number_from_palace(reading.palace) - 1];
    output << "| " << palace_name(reading.palace) << " | "
           << roles_text(reading.roles) << " | " << gate_name(reading.gate)
           << "·" << star_name(reading.star) << "·"
           << spirit_name(reading.spirit) << "·" << gan(reading.heaven_stem)
           << "加" << gan(reading.earth_stem) << " | 星"
           << strength_name(reading.star_strength) << "、门"
           << strength_name(reading.gate_strength) << " | "
           << palace_marks(source) << " | " << reading.image << " |\n";
  }

  output << "\n【全局格局】\n";
  write_evidence(output, analysis.global_configuration,
                 "本局未见已登记的全局格局成立。");
  output << "\n【生扶与救应】\n";
  write_evidence(output, analysis.favorable, "主用诸宫未见明确生扶救应。");
  output << "\n【克制与闭塞】\n";
  write_evidence(output, analysis.unfavorable, "主用诸宫未见明确克制闭塞。");

  output << "\n【分门应象】\n";
  for (const auto &item : analysis.manifestations)
    output << "- " << item.subject << "：" << item.indication << '\n';

  output << "\n【应期与方位】\n";
  output << "| 次序 | 候应宫 | 方位 | 候支 | 迟速 | 触发条件 | 排序依据 |\n";
  output << "| --- | --- | --- | --- | --- | --- | --- |\n";
  for (const auto &item : analysis.timing)
    output << "| " << item.priority << " | " << palace_name(item.palace)
           << " | " << item.direction << " | "
           << joined_branches(item.trigger_branches) << " | " << item.pace
           << " | " << item.condition << " | " << item.basis << " |\n";

  output << "\n【总断】\n";
  output << "盘势：" << to_zh(analysis.judgment) << '\n';
  output << "断语：" << analysis.conclusion << '\n';
  if (!analysis.review_points.empty()) {
    output << "复核：\n";
    for (const auto &point : analysis.review_points)
      output << "- " << point << '\n';
  }
}

} // namespace ZhouYi::QiMenAnalysis
