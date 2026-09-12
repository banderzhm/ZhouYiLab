/**
 * @file mei_hua_omen.cpp
 * @brief 梅花易数外应参断实现。
 */
module ZhouYi.MeiHua.Analysis.Omen;

import ZhouYi.GanZhi;
import ZhouYi.MeiHua;
import std;

namespace ZhouYi::MeiHuaAnalysis {
namespace {
TiYongRelation relation_to_ti(ZhouYi::MeiHua::Trigram ti,
                              ZhouYi::MeiHua::Trigram omen) {
  const auto ti_element = ZhouYi::MeiHua::trigram_info(ti).element;
  const auto omen_element = ZhouYi::MeiHua::trigram_info(omen).element;
  if (ti_element == omen_element)
    return TiYongRelation::BiHe;
  if (ZhouYi::GanZhi::wu_xing_sheng(omen_element, ti_element))
    return TiYongRelation::YongShengTi;
  if (ZhouYi::GanZhi::wu_xing_sheng(ti_element, omen_element))
    return TiYongRelation::TiShengYong;
  if (ZhouYi::GanZhi::wu_xing_ke(ti_element, omen_element))
    return TiYongRelation::TiKeYong;
  return TiYongRelation::YongKeTi;
}

bool favorable_relation(TiYongRelation relation) {
  return relation == TiYongRelation::BiHe ||
         relation == TiYongRelation::YongShengTi ||
         relation == TiYongRelation::TiKeYong;
}
} // namespace

std::vector<OmenReading>
analyze_external_omens(const ZhouYi::MeiHua::MeiHuaPan &pan,
                       const std::vector<ExternalOmen> &omens) {
  std::vector<OmenReading> readings;
  readings.reserve(omens.size());
  for (const auto &omen : omens) {
    OmenReading reading{.kind = omen.kind,
                        .observation = omen.observation,
                        .trigram = omen.trigram,
                        .number = omen.number};
    if (omen.trigram) {
      const auto relation = relation_to_ti(pan.ti_yong.ti, *omen.trigram);
      const auto &info = ZhouYi::MeiHua::trigram_info(*omen.trigram);
      reading.relation = relation;
      reading.favorable = favorable_relation(relation);
      switch (relation) {
      case TiYongRelation::BiHe:
        reading.interpretation =
            "外应归“" + info.name + "”而与体比和，外象相应";
        break;
      case TiYongRelation::YongShengTi:
        reading.interpretation = "外应归“" + info.name + "”而生体，为来助之应";
        break;
      case TiYongRelation::TiShengYong:
        reading.interpretation = "外应归“" + info.name + "”而泄体，主因事耗力";
        break;
      case TiYongRelation::TiKeYong:
        reading.interpretation =
            "外应归“" + info.name + "”而受体所制，尚可掌握";
        break;
      case TiYongRelation::YongKeTi:
        reading.interpretation =
            "外应归“" + info.name + "”而来克体，须防外象成阻";
        break;
      }
    } else if (omen.number) {
      const auto trigram = ZhouYi::MeiHua::trigram_from_number(*omen.number);
      const auto relation = relation_to_ti(pan.ti_yong.ti, trigram);
      reading.trigram = trigram;
      reading.relation = relation;
      reading.favorable = favorable_relation(relation);
      reading.interpretation =
          "外应数“" + std::to_string(*omen.number) + "”归先天卦数“" +
          ZhouYi::MeiHua::trigram_info(trigram).name + "”，再与体卦合参";
    } else {
      reading.interpretation = "外应尚未归入具体八卦，只留其象待占者参验";
    }
    readings.push_back(std::move(reading));
  }
  return readings;
}

} // namespace ZhouYi::MeiHuaAnalysis
