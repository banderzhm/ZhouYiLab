/**
 * @file mei_hua_analysis_presenter.cpp
 * @brief 梅花易数中文排盘与占断报告实现。
 */
module ZhouYi.MeiHua.Analysis.Presenter;

import ZhouYi.GanZhi;
import ZhouYi.MeiHua;
import ZhouYi.ZhMapper;
import fmt;
import std;

namespace ZhouYi::MeiHuaAnalysis {
namespace {
std::string_view zh(auto value) { return ZhouYi::Mapper::to_zh(value); }

std::string method_name(ZhouYi::MeiHua::CastingMethod method) {
  return std::string(zh(method));
}

std::string trigram_text(ZhouYi::MeiHua::Trigram trigram) {
  const auto &info = ZhouYi::MeiHua::trigram_info(trigram);
  return info.name + info.symbol + "·" +
         std::string(ZhouYi::GanZhi::Mapper::to_zh(info.element));
}

nlohmann::json reading_json(const TrigramReading &reading) {
  return {{"阶段", zh(reading.stage)},
          {"职责", zh(reading.role)},
          {"卦象", trigram_text(reading.trigram)},
          {"体用生克", zh(reading.relation)},
          {"月令气势", zh(reading.seasonal_state)},
          {"断意", reading.interpretation}};
}

nlohmann::json party_member_json(const PartyMember &member) {
  return {{"阶段", zh(member.stage)},
          {"职责", zh(member.role)},
          {"卦象", trigram_text(member.trigram)},
          {"归党", zh(member.side)},
          {"对体生克", zh(member.relation)},
          {"月令气势", zh(member.strength)},
          {"有效卦力", member.effective_force},
          {"归党依据", member.basis}};
}

nlohmann::json hexagram_json(const ZhouYi::MeiHua::HexagramSnapshot &gua) {
  return {{"卦名", gua.name},
          {"卦码", gua.code},
          {"上卦", trigram_text(gua.upper)},
          {"下卦", trigram_text(gua.lower)},
          {"卦义", gua.meaning}};
}

void write_hexagram_cell(std::ostream &output,
                         const ZhouYi::MeiHua::HexagramSnapshot &gua) {
  output << gua.name << "<br>上" << trigram_text(gua.upper) << "<br>下"
         << trigram_text(gua.lower) << "<br>卦码 " << gua.code;
}
} // namespace

nlohmann::json to_zh_json(const ZhouYi::MeiHua::MeiHuaPan &pan,
                          const AnalysisResult &analysis) {
  nlohmann::json output;
  output["契约版本"] = analysis.schema_version;
  output["起卦"] = {{"法门", method_name(pan.method)},
                    {"象数", pan.source_numbers},
                    {"上卦合数", pan.upper_total},
                    {"下卦合数", pan.lower_total},
                    {"动爻合数", pan.moving_total},
                    {"动爻", pan.moving_line},
                    {"民用时", pan.civil_time},
                    {"起卦时", pan.casting_time},
                    {"农历", pan.lunar_date},
                    {"闰月", pan.lunar_leap_month},
                    {"真太阳时", pan.true_solar_time_applied},
                    {"修正分钟", pan.time_offset_minutes}};
  output["卦盘"] = {{"本卦", hexagram_json(pan.ben_gua)},
                    {"互卦", hexagram_json(pan.hu_gua)},
                    {"变卦", hexagram_json(pan.bian_gua)}};
  output["体用"] = {{"体卦", trigram_text(pan.ti_yong.ti)},
                    {"用卦", trigram_text(pan.ti_yong.yong)},
                    {"分判", pan.ti_yong.basis},
                    {"本卦生克", zh(analysis.ben_yong.relation)}};
  output["互变推演"] = nlohmann::json::array();
  for (const auto &reading : analysis.hu_influences)
    output["互变推演"].push_back(reading_json(reading));
  output["互变推演"].push_back(reading_json(analysis.bian_influence));
  output["体党用党"] = {{"体党卦力", analysis.party_balance.ti_force},
                        {"用党卦力", analysis.party_balance.yong_force},
                        {"得势一方", zh(analysis.party_balance.dominant)},
                        {"党势断意", analysis.party_balance.interpretation},
                        {"诸卦归党", nlohmann::json::array()}};
  for (const auto &member : analysis.party_balance.members)
    output["体党用党"]["诸卦归党"].push_back(party_member_json(member));
  output["分门占断"] = {
      {"门类", zh(analysis.request.question_kind)},
      {"占问落点", analysis.question_reading.focus},
      {"可取之象", analysis.question_reading.favorable_signs},
      {"阻滞之象", analysis.question_reading.obstructive_signs},
      {"专断", analysis.question_reading.interpretation}};
  output["外应参断"] = nlohmann::json::array();
  for (const auto &omen : analysis.omen_readings) {
    nlohmann::json item{{"门类", zh(omen.kind)},
                        {"所见所闻", omen.observation},
                        {"断意", omen.interpretation}};
    if (omen.trigram)
      item["所归卦象"] = trigram_text(*omen.trigram);
    if (omen.relation)
      item["对体生克"] = zh(*omen.relation);
    if (omen.number)
      item["外应象数"] = *omen.number;
    output["外应参断"].push_back(std::move(item));
  }
  output["动爻取象"] = {{"爻位", analysis.moving_line.place},
                        {"所主", analysis.moving_line.domain},
                        {"变化", analysis.moving_line.interpretation}};
  output["万物类象"] = analysis.images;
  output["占断依据"] = nlohmann::json::array();
  for (const auto &basis : analysis.mei_hua_basis)
    output["占断依据"].push_back({{"门类", zh(basis.rule)},
                                  {"阶段", zh(basis.stage)},
                                  {"作用", zh(basis.disposition)},
                                  {"断法", basis.detail}});
  output["卦势判定"] = {{"规则版本", analysis.judgment_detail.rule_version},
                        {"算法口径", analysis.judgment_detail.formula},
                        {"党势校正", analysis.judgment_detail.party_adjustment},
                        {"综合卦势值", analysis.judgment_detail.total},
                        {"区间断意", analysis.judgment_detail.interpretation},
                        {"分项", nlohmann::json::array()}};
  for (const auto &component : analysis.judgment_detail.components)
    output["卦势判定"]["分项"].push_back(
        {{"阶段", zh(component.stage)},
         {"职责", zh(component.role)},
         {"对体生克", zh(component.relation)},
         {"月令气势", zh(component.strength)},
         {"生克基础值", component.relation_value},
         {"月令系数", component.seasonal_factor},
         {"阶段系数", component.stage_factor},
         {"本项卦力", component.contribution},
         {"算法依据", component.basis}});
  output["应期候选"] = nlohmann::json::array();
  for (const auto &item : analysis.timing)
    output["应期候选"].push_back({{"次序", item.priority},
                                  {"取应门类", zh(item.kind)},
                                  {"象数", item.number},
                                  {"单位", zh(item.unit)},
                                  {"方位", item.direction},
                                  {"触发", item.trigger},
                                  {"取应依据", item.basis}});
  output["总断"] = {{"门类", zh(analysis.request.question_kind)},
                    {"所问", analysis.request.question},
                    {"迟速", zh(analysis.request.event_pace)},
                    {"卦势", zh(analysis.judgment)},
                    {"断语", analysis.conclusion},
                    {"参看", analysis.review_points}};
  return output;
}

void write_zh(std::ostream &output, const ZhouYi::MeiHua::MeiHuaPan &pan,
              const AnalysisResult &analysis) {
  output << "==================== 梅花易数排盘与占断 ====================\n\n";
  output << "【起卦】\n";
  output << "法门：" << method_name(pan.method) << '\n';
  if (pan.method == ZhouYi::MeiHua::CastingMethod::LunarTime &&
      pan.source_numbers.size() == 4) {
    output << "象数：年支 " << pan.source_numbers[0] << "、月 "
           << pan.source_numbers[1] << "、日 " << pan.source_numbers[2]
           << "、时支 " << pan.source_numbers[3];
  } else {
    output << "象数：";
    for (std::size_t index = 0; index < pan.source_numbers.size(); ++index) {
      if (index)
        output << "、";
      output << pan.source_numbers[index];
    }
  }
  output << "；动爻：" << analysis.moving_line.place << '\n';
  output << "合数：上卦 " << pan.upper_total << "；下卦 " << pan.lower_total
         << "；动爻 " << pan.moving_total << '\n';
  if (!pan.casting_time.empty()) {
    output << "民用时：" << pan.civil_time << '\n';
    output << "起卦时：" << pan.casting_time;
    output << (pan.true_solar_time_applied ? "（已校正真太阳时）"
                                           : "（未提供地点，径用原时）")
           << '\n';
    output << "农历取数：" << pan.lunar_date << '\n';
  }

  output << "\n【本卦—互卦—变卦】\n\n";
  output << "| 本卦（始） | 互卦（中） | 变卦（终） |\n";
  output << "|---|---|---|\n| ";
  write_hexagram_cell(output, pan.ben_gua);
  output << " | ";
  write_hexagram_cell(output, pan.hu_gua);
  output << " | ";
  write_hexagram_cell(output, pan.bian_gua);
  output << " |\n";

  output << "\n【体用分判】\n";
  output << "体卦：" << trigram_text(pan.ti_yong.ti) << "；用卦："
         << trigram_text(pan.ti_yong.yong) << '\n';
  output << "定体用：" << pan.ti_yong.basis << '\n';
  output << "本卦作用：" << zh(analysis.ben_yong.relation) << "——"
         << analysis.ben_yong.interpretation << '\n';

  output << "\n【互变卦势】\n\n";
  output << "| 阶段 | 职责 | 卦象 | 对体生克 | 月令气势 | 断意 |\n";
  output << "|---|---|---|---|---|---|\n";
  for (const auto &reading : analysis.hu_influences)
    output << "| " << zh(reading.stage) << " | " << zh(reading.role) << " | "
           << trigram_text(reading.trigram) << " | " << zh(reading.relation)
           << " | " << zh(reading.seasonal_state) << " | "
           << reading.interpretation << " |\n";
  output << "| " << zh(analysis.bian_influence.stage) << " | "
         << zh(analysis.bian_influence.role) << " | "
         << trigram_text(analysis.bian_influence.trigram) << " | "
         << zh(analysis.bian_influence.relation) << " | "
         << zh(analysis.bian_influence.seasonal_state) << " | "
         << analysis.bian_influence.interpretation << " |\n";

  output << "\n【体党用党】\n\n";
  output << "| 阶段 | 职责 | 卦象 | 归党 | 对体生克 | 月令气势 | 有效卦力 | "
            "归党依据 |\n";
  output << "|---|---|---|---|---|---|---:|---|\n";
  for (const auto &member : analysis.party_balance.members)
    output << "| " << zh(member.stage) << " | " << zh(member.role) << " | "
           << trigram_text(member.trigram) << " | " << zh(member.side) << " | "
           << zh(member.relation) << " | " << zh(member.strength) << " | "
           << fmt::format("{:.2f}", member.effective_force) << " | "
           << member.basis << " |\n";
  output << "\n体党卦力："
         << fmt::format("{:.2f}", analysis.party_balance.ti_force)
         << "；用党卦力："
         << fmt::format("{:.2f}", analysis.party_balance.yong_force)
         << "；得势：" << zh(analysis.party_balance.dominant) << '\n';
  output << "党势：" << analysis.party_balance.interpretation << '\n';

  output << "\n【动爻取象】\n";
  output << analysis.moving_line.place << "：" << analysis.moving_line.domain
         << "；" << analysis.moving_line.interpretation << '\n';

  output << "\n【万物类象】\n";
  for (const auto &image : analysis.images)
    output << "- " << image << '\n';

  output << "\n【分门占断】\n";
  output << "占问落点：" << analysis.question_reading.focus << '\n';
  if (!analysis.question_reading.favorable_signs.empty()) {
    output << "可取之象：\n";
    for (const auto &sign : analysis.question_reading.favorable_signs)
      output << "- " << sign << '\n';
  }
  if (!analysis.question_reading.obstructive_signs.empty()) {
    output << "阻滞之象：\n";
    for (const auto &sign : analysis.question_reading.obstructive_signs)
      output << "- " << sign << '\n';
  }
  output << "专断：" << analysis.question_reading.interpretation << '\n';

  if (!analysis.omen_readings.empty()) {
    output << "\n【外应参断】\n\n";
    output << "| 门类 | 所见所闻 | 象数 | 所归卦象 | 对体生克 | 外应断意 |\n";
    output << "|---|---|---:|---|---|---|\n";
    for (const auto &omen : analysis.omen_readings) {
      output << "| " << zh(omen.kind) << " | " << omen.observation << " | ";
      if (omen.number)
        output << *omen.number;
      else
        output << "—";
      output << " | ";
      if (omen.trigram)
        output << trigram_text(*omen.trigram);
      else
        output << "待归象";
      output << " | ";
      if (omen.relation)
        output << zh(*omen.relation);
      else
        output << "待合参";
      output << " | " << omen.interpretation << " |\n";
    }
  }

  output << "\n【占断依据】\n\n";
  output << "| 门类 | 阶段 | 作用 | 断法 |\n";
  output << "|---|---|---|---|\n";
  for (const auto &basis : analysis.mei_hua_basis)
    output << "| " << zh(basis.rule) << " | " << zh(basis.stage) << " | "
           << zh(basis.disposition) << " | " << basis.detail << " |\n";

  output << "\n【卦势判定｜生克基础值 × 月令气势 × 阶段系数＋党势校正】\n\n";
  output << "规则版本：" << analysis.judgment_detail.rule_version << '\n';
  output << "算法口径：" << analysis.judgment_detail.formula << "\n\n";
  output << "| 阶段 | 职责 | 对体生克 | 月令气势 | 生克基础值 | 月令系数 | "
            "阶段系数 | 本项卦力 |\n";
  output << "|---|---|---|---|---:|---:|---:|---:|\n";
  for (const auto &component : analysis.judgment_detail.components)
    output << "| " << zh(component.stage) << " | " << zh(component.role)
           << " | " << zh(component.relation) << " | " << zh(component.strength)
           << " | " << fmt::format("{:.2f}", component.relation_value) << " | "
           << fmt::format("{:.2f}", component.seasonal_factor) << " | "
           << fmt::format("{:.2f}", component.stage_factor) << " | "
           << fmt::format("{:.2f}", component.contribution) << " |\n";
  output << "\n党势校正："
         << fmt::format("{:.2f}", analysis.judgment_detail.party_adjustment)
         << "；综合卦势值："
         << fmt::format("{:.2f}", analysis.judgment_detail.total) << '\n';
  output << "区间断意：" << analysis.judgment_detail.interpretation << '\n';

  output << "\n【应期候选】\n\n";
  output << "| 次序 | 取应门类 | 象数 | 候选单位 | 应方 | 触发 | 取应依据 |\n";
  output << "|---:|---|---:|---|---|---|---|\n";
  for (const auto &item : analysis.timing)
    output << "| " << item.priority << " | " << zh(item.kind) << " | "
           << item.number << " | " << zh(item.unit) << " | " << item.direction
           << " | " << item.trigger << " | " << item.basis << " |\n";

  output << "\n【总断】\n";
  output << "占问：" << zh(analysis.request.question_kind);
  if (!analysis.request.question.empty())
    output << "——" << analysis.request.question;
  output << '\n';
  output << "事情迟速：" << zh(analysis.request.event_pace) << '\n';
  output << "卦势：" << zh(analysis.judgment) << '\n';
  output << "断语：" << analysis.conclusion << '\n';
  if (!analysis.review_points.empty()) {
    output << "参看：\n";
    for (const auto &point : analysis.review_points)
      output << "- " << point << '\n';
  }
}

} // namespace ZhouYi::MeiHuaAnalysis
