/**
 * @file mei_hua_ti_yong.cpp
 * @brief 梅花易数旺衰与体党用党推演实现。
 */
module ZhouYi.MeiHua.Analysis.TiYong;

import ZhouYi.GanZhi;
import ZhouYi.MeiHua;
import std;

namespace ZhouYi::MeiHuaAnalysis {
namespace {
using ZhouYi::GanZhi::WuXing;

WuXing commanding_element(int lunar_month) {
  const int month = (std::abs(lunar_month) - 1) % 12 + 1;
  if (month == 3 || month == 6 || month == 9 || month == 12)
    return WuXing::Tu;
  if (month <= 2)
    return WuXing::Mu;
  if (month <= 5)
    return WuXing::Huo;
  if (month <= 8)
    return WuXing::Jin;
  return WuXing::Shui;
}

PartySide party_side(TiYongRelation relation) {
  return relation == TiYongRelation::BiHe ||
                 relation == TiYongRelation::YongShengTi
             ? PartySide::Ti
             : PartySide::Yong;
}

double stage_factor(GuaStage stage) {
  switch (stage) {
  case GuaStage::Ben:
    return 1.0;
  case GuaStage::Hu:
    return 0.65;
  case GuaStage::Bian:
    return 1.0;
  }
  return 1.0;
}

void append_member(PartyBalance &balance, const TrigramReading &reading) {
  const auto side = party_side(reading.relation);
  const double force = stage_factor(reading.stage) *
                       seasonal_force_factor(reading.seasonal_state);
  balance.members.push_back({.stage = reading.stage,
                             .role = reading.role,
                             .trigram = reading.trigram,
                             .side = side,
                             .relation = reading.relation,
                             .strength = reading.seasonal_state,
                             .effective_force = force,
                             .basis = side == PartySide::Ti
                                          ? "比和或生体，归入体党"
                                          : "克体、泄体或受体所制，归入用党"});
  if (side == PartySide::Ti)
    balance.ti_force += force;
  else
    balance.yong_force += force;
}
} // namespace

SeasonalStrength assess_seasonal_strength(WuXing element, int lunar_month) {
  if (lunar_month == 0)
    return SeasonalStrength::WeiDing;
  const auto commanding = commanding_element(lunar_month);
  if (element == commanding)
    return SeasonalStrength::Wang;
  if (ZhouYi::GanZhi::wu_xing_sheng(commanding, element))
    return SeasonalStrength::Xiang;
  if (ZhouYi::GanZhi::wu_xing_sheng(element, commanding))
    return SeasonalStrength::Xiu;
  if (ZhouYi::GanZhi::wu_xing_ke(element, commanding))
    return SeasonalStrength::Qiu;
  return SeasonalStrength::Si;
}

double seasonal_force_factor(SeasonalStrength strength) {
  switch (strength) {
  case SeasonalStrength::Wang:
    return 1.50;
  case SeasonalStrength::Xiang:
    return 1.25;
  case SeasonalStrength::Xiu:
    return 0.90;
  case SeasonalStrength::Qiu:
    return 0.65;
  case SeasonalStrength::Si:
    return 0.45;
  case SeasonalStrength::WeiDing:
    return 1.00;
  }
  return 1.00;
}

PartyBalance
build_party_balance(const ZhouYi::MeiHua::MeiHuaPan &pan,
                    const TrigramReading &ben_yong,
                    const std::vector<TrigramReading> &hu_influences,
                    const TrigramReading &bian_influence) {
  PartyBalance balance;
  const auto ti_strength = assess_seasonal_strength(
      ZhouYi::MeiHua::trigram_info(pan.ti_yong.ti).element, pan.lunar_month);
  const double ti_root_force = seasonal_force_factor(ti_strength);
  balance.members.push_back({.stage = GuaStage::Ben,
                             .role = TrigramRole::TiGenBen,
                             .trigram = pan.ti_yong.ti,
                             .side = PartySide::Ti,
                             .relation = TiYongRelation::BiHe,
                             .strength = ti_strength,
                             .effective_force = ti_root_force,
                             .basis = "体卦自身为体党根本"});
  balance.ti_force = ti_root_force;
  append_member(balance, ben_yong);
  for (const auto &reading : hu_influences)
    append_member(balance, reading);
  append_member(balance, bian_influence);
  const double difference = std::abs(balance.ti_force - balance.yong_force);
  if (difference < 0.35) {
    balance.dominant = PartySide::XiangChi;
    balance.interpretation = "体用两党相持，须以动爻、外应和所占门类定夺";
  } else if (balance.ti_force > balance.yong_force) {
    balance.dominant = PartySide::Ti;
    balance.interpretation = "体党得势，生扶与自主之力较足，所占较能由己掌握";
  } else {
    balance.dominant = PartySide::Yong;
    balance.interpretation =
        "用党得势，外局牵制或耗泄较重，所占宜借势而不可强为";
  }
  return balance;
}

} // namespace ZhouYi::MeiHuaAnalysis
