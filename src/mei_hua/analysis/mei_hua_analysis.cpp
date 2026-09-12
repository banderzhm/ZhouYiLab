/**
 * @file mei_hua_analysis.cpp
 * @brief 梅花易数体用占断实现。
 */
module ZhouYi.MeiHua.Analysis;

import ZhouYi.GanZhi;
import ZhouYi.MeiHua;
import ZhouYi.MeiHua.Analysis.Omen;
import ZhouYi.MeiHua.Analysis.QuestionRules;
import ZhouYi.MeiHua.Analysis.TiYong;
import ZhouYi.MeiHua.Analysis.Timing;
import std;

namespace ZhouYi::MeiHuaAnalysis {
namespace {
using ZhouYi::MeiHua::Trigram;

TiYongRelation relation_to_ti(Trigram ti, Trigram other) {
  const auto ti_element = ZhouYi::MeiHua::trigram_info(ti).element;
  const auto other_element = ZhouYi::MeiHua::trigram_info(other).element;
  if (ti_element == other_element)
    return TiYongRelation::BiHe;
  if (ZhouYi::GanZhi::wu_xing_sheng(other_element, ti_element))
    return TiYongRelation::YongShengTi;
  if (ZhouYi::GanZhi::wu_xing_sheng(ti_element, other_element))
    return TiYongRelation::TiShengYong;
  if (ZhouYi::GanZhi::wu_xing_ke(ti_element, other_element))
    return TiYongRelation::TiKeYong;
  return TiYongRelation::YongKeTi;
}

std::string relation_text(TiYongRelation relation) {
  switch (relation) {
  case TiYongRelation::BiHe:
    return "比和相应，内外同气，谋为易合";
  case TiYongRelation::YongShengTi:
    return "用卦生体，外来之势扶体，所占得助";
  case TiYongRelation::TiShengYong:
    return "体卦生用，体气外泄，先有付出而后见事";
  case TiYongRelation::TiKeYong:
    return "体卦克用，事可受我节制，但成之较劳";
  case TiYongRelation::YongKeTi:
    return "用卦克体，外事制身，宜守不宜强进";
  }
  return "体用之势待辨";
}

bool favorable(TiYongRelation relation) {
  return relation == TiYongRelation::BiHe ||
         relation == TiYongRelation::YongShengTi ||
         relation == TiYongRelation::TiKeYong;
}

BasisDisposition disposition(TiYongRelation relation) {
  return favorable(relation) ? BasisDisposition::ZhuYi : BasisDisposition::ZuAi;
}

double tendency(TiYongRelation relation) {
  switch (relation) {
  case TiYongRelation::YongShengTi:
    return 3;
  case TiYongRelation::BiHe:
    return 2;
  case TiYongRelation::TiKeYong:
    return 1;
  case TiYongRelation::TiShengYong:
    return -1;
  case TiYongRelation::YongKeTi:
    return -3;
  }
  return 0;
}

TrigramReading make_reading(GuaStage stage, TrigramRole role, Trigram trigram,
                            Trigram ti, int lunar_month) {
  const auto relation = relation_to_ti(ti, trigram);
  return {.stage = stage,
          .role = std::move(role),
          .trigram = trigram,
          .relation = relation,
          .seasonal_state = assess_seasonal_strength(
              ZhouYi::MeiHua::trigram_info(trigram).element, lunar_month),
          .interpretation = relation_text(relation)};
}

std::string line_name(int position) {
  constexpr std::array<std::string_view, 6> names{"初爻", "二爻", "三爻",
                                                  "四爻", "五爻", "上爻"};
  return std::string(names[static_cast<std::size_t>(position - 1)]);
}

MovingLineReading moving_reading(int position) {
  constexpr std::array<std::string_view, 6> domains{
      "事机初萌，主自身与近处先动",     "事入内中，主同伴与基层承接",
      "内外之交，进退转换最为明显",     "外局初动，主门庭、往来与执行层",
      "居中得位，主核心人物与成败枢纽", "事至其极，主远方、终局与去留"};
  constexpr std::array<std::string_view, 6> changes{
      "变化方起，宜察先兆", "由内渐显，宜循序承应", "临界而动，防反复",
      "外缘来引，宜审来者", "枢纽发动，成败较速",   "物极将变，宜知止转机"};
  return {.position = position,
          .place = line_name(position),
          .domain =
              std::string(domains[static_cast<std::size_t>(position - 1)]),
          .interpretation =
              std::string(changes[static_cast<std::size_t>(position - 1)])};
}

JudgmentComponent make_component(const TrigramReading &reading,
                                 double stage_factor) {
  const double relation_value = tendency(reading.relation);
  const double seasonal_factor = seasonal_force_factor(reading.seasonal_state);
  return {.stage = reading.stage,
          .role = reading.role,
          .relation = reading.relation,
          .strength = reading.seasonal_state,
          .relation_value = relation_value,
          .seasonal_factor = seasonal_factor,
          .stage_factor = stage_factor,
          .contribution = relation_value * seasonal_factor * stage_factor,
          .basis = "生克基础值 × 月令气势系数 × 阶段系数"};
}
} // namespace

AnalysisResult analyze(const ZhouYi::MeiHua::MeiHuaPan &pan,
                       const AnalysisRequest &request) {
  AnalysisResult result;
  result.request = request;
  const auto ti = pan.ti_yong.ti;
  const auto ben_yong = pan.ti_yong.yong;
  result.ben_yong = make_reading(GuaStage::Ben, TrigramRole::YongGua, ben_yong,
                                 ti, pan.lunar_month);
  result.hu_influences.push_back(make_reading(
      GuaStage::Hu, TrigramRole::HuXia, pan.hu_gua.lower, ti, pan.lunar_month));
  result.hu_influences.push_back(
      make_reading(GuaStage::Hu, TrigramRole::HuShang, pan.hu_gua.upper, ti,
                   pan.lunar_month));
  const auto changed_active = pan.ti_yong.moving_line_in_lower
                                  ? pan.bian_gua.lower
                                  : pan.bian_gua.upper;
  result.bian_influence = make_reading(GuaStage::Bian, TrigramRole::BianYong,
                                       changed_active, ti, pan.lunar_month);
  result.moving_line = moving_reading(pan.moving_line);
  result.party_balance = build_party_balance(
      pan, result.ben_yong, result.hu_influences, result.bian_influence);
  result.question_reading =
      build_question_reading(pan, request, result.ben_yong,
                             result.bian_influence, result.party_balance);
  result.omen_readings = analyze_external_omens(pan, request.external_omens);

  const auto &ti_image = ZhouYi::MeiHua::trigram_info(ti);
  const auto &yong_image = ZhouYi::MeiHua::trigram_info(ben_yong);
  const auto &hu_lower_image = ZhouYi::MeiHua::trigram_info(pan.hu_gua.lower);
  const auto &hu_upper_image = ZhouYi::MeiHua::trigram_info(pan.hu_gua.upper);
  const auto &bian_image = ZhouYi::MeiHua::trigram_info(changed_active);
  result.images = {
      "体卦“" + ti_image.name + "”取己身：" + ti_image.nature + "象、" +
          ti_image.family + "象，人体应" + ti_image.body_image + "，性情应" +
          ti_image.temperament,
      "用卦“" + yong_image.name + "”取所占之外应：主" + yong_image.nature +
          "，应方" + yong_image.direction + "，五色应" + yong_image.color +
          "，动物应" + yong_image.animal + "，场所应" + yong_image.place_image +
          "，器物应" + yong_image.object_image,
      "互卦中程见“" + hu_lower_image.name + "、" + hu_upper_image.name +
          "”，兼察" + hu_lower_image.nature + "与" + hu_upper_image.nature +
          "之象",
      "变卦动位化“" + bian_image.name + "”，终局趋向" + bian_image.nature +
          "象，应方" + bian_image.direction,
  };

  result.mei_hua_basis.push_back(
      {.rule = BasisKind::BenTiYong,
       .detail = relation_text(result.ben_yong.relation),
       .stage = GuaStage::Ben,
       .disposition = disposition(result.ben_yong.relation)});
  for (std::size_t index = 0; index < result.hu_influences.size(); ++index) {
    const auto &reading = result.hu_influences[index];
    result.mei_hua_basis.push_back(
        {.rule = index == 0 ? BasisKind::HuXia : BasisKind::HuShang,
         .detail = reading.interpretation,
         .stage = GuaStage::Hu,
         .disposition = disposition(reading.relation)});
  }
  result.mei_hua_basis.push_back(
      {.rule = BasisKind::BianGuiSu,
       .detail = result.bian_influence.interpretation,
       .stage = GuaStage::Bian,
       .disposition = disposition(result.bian_influence.relation)});
  const auto party_disposition =
      result.party_balance.dominant == PartySide::Ti ? BasisDisposition::ZhuYi
      : result.party_balance.dominant == PartySide::Yong
          ? BasisDisposition::ZuAi
          : BasisDisposition::XiangChi;
  result.mei_hua_basis.push_back({.rule = BasisKind::TiYongDang,
                                  .detail = result.party_balance.interpretation,
                                  .stage = GuaStage::Bian,
                                  .disposition = party_disposition});
  for (const auto &omen : result.omen_readings)
    result.mei_hua_basis.push_back(
        {.rule = BasisKind::WaiYing,
         .detail = omen.interpretation,
         .stage = GuaStage::Ben,
         .disposition = omen.relation
                            ? (omen.favorable ? BasisDisposition::ZhuYi
                                              : BasisDisposition::ZuAi)
                            : BasisDisposition::XiangChi});

  result.judgment_detail.components = {
      make_component(result.ben_yong, 2.0),
      make_component(result.hu_influences[0], 0.65),
      make_component(result.hu_influences[1], 0.65),
      make_component(result.bian_influence, 2.0)};
  result.judgment_detail.party_adjustment =
      (result.party_balance.ti_force - result.party_balance.yong_force) * 0.8;
  result.judgment_detail.total = result.judgment_detail.party_adjustment;
  for (const auto &component : result.judgment_detail.components)
    result.judgment_detail.total += component.contribution;
  result.judgment_detail.formula =
      "各卦生克基础值乘月令气势与阶段系数，再加体党、用党差额的八成校正";
  const double total = result.judgment_detail.total;
  if (total >= 8)
    result.judgment = Judgment::DaJi;
  else if (total >= 3)
    result.judgment = Judgment::Ji;
  else if (total >= 0)
    result.judgment = Judgment::Ping;
  else if (total >= -5)
    result.judgment = Judgment::YouZu;
  else
    result.judgment = Judgment::Xiong;
  result.judgment_detail.interpretation =
      total >= 8    ? "体用生扶与终局助力占优，卦势较顺"
      : total >= 3  ? "助体之象多于耗克，卦势可取"
      : total >= 0  ? "生克相参，卦势平衡"
      : total >= -5 ? "耗克之象偏多，所占有阻"
                    : "克泄体卦之势较重，宜守待变";

  result.timing = build_timing_candidates(pan, request, result.ben_yong,
                                          result.bian_influence);

  result.conclusion = result.question_reading.focus + "：本卦“" +
                      pan.ben_gua.name + "”，" +
                      result.ben_yong.interpretation + "；互卦“" +
                      pan.hu_gua.name + "”察中程，变卦“" + pan.bian_gua.name +
                      "”定归宿，" + result.bian_influence.interpretation +
                      "。" + result.party_balance.interpretation + "；" +
                      result.question_reading.interpretation + "。";
  if (pan.lunar_month == 0)
    result.review_points.push_back(
        "数字起卦未带月令，体用旺衰未作得令、休囚校正");
  result.review_points.push_back(
      "应期须随所占之事的迟速、远近及动静再定时、日、月之单位");
  return result;
}

} // namespace ZhouYi::MeiHuaAnalysis
