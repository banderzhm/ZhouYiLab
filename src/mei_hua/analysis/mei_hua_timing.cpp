/**
 * @file mei_hua_timing.cpp
 * @brief 梅花易数应期候选推演实现。
 */
module ZhouYi.MeiHua.Analysis.Timing;

import ZhouYi.MeiHua;
import std;

namespace ZhouYi::MeiHuaAnalysis {
namespace {

bool vigorous(SeasonalStrength strength) {
  return strength == SeasonalStrength::Wang ||
         strength == SeasonalStrength::Xiang;
}

TimingUnit fixed_unit(EventPace pace) {
  switch (pace) {
  case EventPace::JiYing:
    return TimingUnit::Shi;
  case EventPace::JinQi:
    return TimingUnit::Ri;
  case EventPace::ZhongQi:
    return TimingUnit::Yue;
  case EventPace::YuanQi:
    return TimingUnit::Nian;
  case EventPace::ZiDong:
    return TimingUnit::RiHuoYue;
  }
  return TimingUnit::RiHuoYue;
}

TimingUnit automatic_unit(TimingKind kind, bool moving_in_lower,
                          SeasonalStrength strength, bool has_month) {
  switch (kind) {
  case TimingKind::DongYao:
    return moving_in_lower ? TimingUnit::ShiHuoRi : TimingUnit::Ri;
  case TimingKind::YongGua:
    if (!has_month)
      return TimingUnit::RiHuoYue;
    return vigorous(strength) ? TimingUnit::Ri : TimingUnit::Yue;
  case TimingKind::BianGua:
    return moving_in_lower && vigorous(strength) ? TimingUnit::Ri
                                                 : TimingUnit::Yue;
  case TimingKind::TiDeLing:
    return TimingUnit::Yue;
  case TimingKind::QuanGua:
    return TimingUnit::RiHuoYue;
  }
  return TimingUnit::RiHuoYue;
}

TimingUnit resolve_unit(EventPace pace, TimingKind kind, bool moving_in_lower,
                        SeasonalStrength strength, bool has_month) {
  return pace == EventPace::ZiDong
             ? automatic_unit(kind, moving_in_lower, strength, has_month)
             : fixed_unit(pace);
}

void append(std::vector<TimingCandidate> &items, TimingKind kind, int number,
            TimingUnit unit, std::string direction, std::string trigger,
            std::string basis) {
  items.push_back({.priority = static_cast<int>(items.size()) + 1,
                   .kind = kind,
                   .number = number,
                   .unit = unit,
                   .direction = std::move(direction),
                   .trigger = std::move(trigger),
                   .basis = std::move(basis)});
}

} // namespace

std::vector<TimingCandidate> build_timing_candidates(
    const ZhouYi::MeiHua::MeiHuaPan &pan, const AnalysisRequest &request,
    const TrigramReading &ben_yong, const TrigramReading &bian_influence) {
  const auto &ti = ZhouYi::MeiHua::trigram_info(pan.ti_yong.ti);
  const auto &yong = ZhouYi::MeiHua::trigram_info(pan.ti_yong.yong);
  const auto &bian = ZhouYi::MeiHua::trigram_info(bian_influence.trigram);
  const auto &hu_xia = ZhouYi::MeiHua::trigram_info(pan.hu_gua.lower);
  const auto &hu_shang = ZhouYi::MeiHua::trigram_info(pan.hu_gua.upper);
  const bool has_month = pan.lunar_month != 0;
  const bool moving_in_lower = pan.ti_yong.moving_line_in_lower;
  std::vector<TimingCandidate> items;
  items.reserve(5);

  append(items, TimingKind::DongYao, pan.moving_line,
         resolve_unit(request.event_pace, TimingKind::DongYao, moving_in_lower,
                      ben_yong.seasonal_state, has_month),
         yong.direction, "逢动爻数、用卦方位或用卦之象先应",
         "动爻主发动，列为近应第一候选");
  append(items, TimingKind::YongGua, yong.number,
         resolve_unit(request.event_pace, TimingKind::YongGua, moving_in_lower,
                      ben_yong.seasonal_state, has_month),
         yong.direction, "逢用卦先天数、方位或用卦气势当值",
         "用为所占之事，以用卦象数取主应");
  append(items, TimingKind::BianGua, bian.number,
         resolve_unit(request.event_pace, TimingKind::BianGua, moving_in_lower,
                      bian_influence.seasonal_state, has_month),
         bian.direction, "逢变卦先天数、方位或变卦气势当值",
         "变卦主末后，以变卦象数取终应");
  append(items, TimingKind::TiDeLing, ti.number,
         resolve_unit(request.event_pace, TimingKind::TiDeLing, moving_in_lower,
                      ben_yong.seasonal_state, has_month),
         ti.direction, "逢体卦得令、得生或临体卦方位",
         "体旺又得生则吉应易至；克体卦气旺时须防阻应");

  const int whole_number =
      ti.number + yong.number + hu_xia.number + hu_shang.number + bian.number;
  append(items, TimingKind::QuanGua, whole_number,
         resolve_unit(request.event_pace, TimingKind::QuanGua, moving_in_lower,
                      bian_influence.seasonal_state, has_month),
         "体用互变诸方", "逢全卦象数交会，兼验本、互、变诸象",
         "依体、用、上下互及变用的先天数合计取约期");
  return items;
}

} // namespace ZhouYi::MeiHuaAnalysis
