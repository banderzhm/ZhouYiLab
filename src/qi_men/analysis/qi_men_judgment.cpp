module ZhouYi.QiMen.Analysis.Judgment;

import ZhouYi.QiMen.Analysis.YongShen;
import ZhouYi.GanZhi;
import fmt;
import std;

namespace ZhouYi::QiMenAnalysis {
namespace {
using namespace ZhouYi::QiMen;

const PalaceInfo &palace_at(const QiMenPan &pan, Palace palace) {
  return pan.palaces[get_number_from_palace(palace) - 1];
}

bool is_flourishing(Strength value) {
  return value == Strength::Wang || value == Strength::Xiang;
}

bool is_declining(Strength value) {
  return value == Strength::Qiu || value == Strength::Fei ||
         value == Strength::Si;
}

void append_unique(std::vector<PanJuBasis> &items, PanJuBasis pan_ju_basis) {
  const bool exists =
      std::ranges::any_of(items, [&](const PanJuBasis &current) {
        return current.rule == pan_ju_basis.rule &&
               current.detail == pan_ju_basis.detail &&
               current.palace == pan_ju_basis.palace;
      });
  if (!exists)
    items.push_back(std::move(pan_ju_basis));
}

std::string gate_image(Gate gate) {
  switch (gate) {
  case Gate::Kai:
    return "开门主开创、职位、经营与道路通达";
  case Gate::Xiu:
    return "休门主休养、会合、和顺与贵人往来";
  case Gate::Sheng:
    return "生门主生发、财源、产业与求取";
  case Gate::Shang:
    return "伤门主动荡、竞争、损伤，也主技术施为";
  case Gate::Du:
    return "杜门主闭藏、阻隔、保密与技术研究";
  case Gate::Jing:
    return "景门主文书、声名、传播与外在显象";
  case Gate::Si:
    return "死门主停滞、终结、病气与旧事收束";
  case Gate::JingGate:
    return "惊门主口舌、惊扰、诉讼与消息突发";
  case Gate::None:
    return "中宫无门，须随寄宫合参";
  }
  return {};
}

std::string star_image(Star star) {
  switch (star) {
  case Star::TianPeng:
    return "天蓬主胆略、风险与暗昧";
  case Star::TianRui:
    return "天芮主病患、问题与师友医药";
  case Star::TianChong:
    return "天冲主行动、速度与冲发";
  case Star::TianFu:
    return "天辅主文教、策划与辅佐";
  case Star::TianQin:
    return "天禽主中正、统摄与居中调停";
  case Star::TianXin:
    return "天心主治理、医药、谋略与决断";
  case Star::TianZhu:
    return "天柱主口舌、阻滞与破败反复";
  case Star::TianRen:
    return "天任主承载、田产与稳步经营";
  case Star::TianYing:
    return "天英主文采、光显与躁烈虚华";
  }
  return {};
}

std::string spirit_image(Spirit spirit) {
  switch (spirit) {
  case Spirit::ZhiFu:
    return "值符主权柄、统领与贵助";
  case Spirit::TengShe:
    return "螣蛇主虚惊、缠绕与反复";
  case Spirit::TaiYin:
    return "太阴主隐助、谋划与暗中成全";
  case Spirit::LiuHe:
    return "六合主合作、婚合与中介牵连";
  case Spirit::BaiHu:
    return "白虎主刚烈、伤灾与强力阻隔";
  case Spirit::XuanWu:
    return "玄武主隐情、失脱与虚诈";
  case Spirit::JiuDi:
    return "九地主伏藏、稳定与迟缓";
  case Spirit::JiuTian:
    return "九天主高举、远行与声势扩张";
  case Spirit::None:
    return "中宫无神，随寄宫取象";
  }
  return {};
}

PalaceReading read_palace(const QiMenPan &pan, Palace palace,
                          const std::vector<YongShenSelection> &selections) {
  const auto &source = palace_at(pan, palace);
  PalaceReading result{.palace = palace,
                       .star = source.star,
                       .gate = source.gate,
                       .spirit = source.spirit,
                       .heaven_stem = source.tian_gan,
                       .earth_stem = source.di_gan,
                       .star_strength = source.star_strength,
                       .gate_strength = source.gate_strength,
                       .patterns = source.patterns};
  for (const auto &selection : selections)
    if (selection.palace == palace)
      result.roles.push_back(selection.role);

  const auto add_good = [&](std::string rule, std::string detail) {
    append_unique(result.favorable, {.rule = std::move(rule),
                                     .detail = std::move(detail),
                                     .nature = EffectNature::ZhuLi,
                                     .palace = palace});
  };
  const auto add_bad = [&](std::string rule, std::string detail) {
    append_unique(result.unfavorable, {.rule = std::move(rule),
                                       .detail = std::move(detail),
                                       .nature = EffectNature::ZhiAi,
                                       .palace = palace});
  };
  if (is_flourishing(source.star_strength))
    add_good("九星得令", fmt::format("{}临{}，星气{}", star_name(source.star),
                                     palace_name(palace),
                                     strength_name(source.star_strength)));
  else if (is_declining(source.star_strength))
    add_bad("九星失令", fmt::format("{}临{}，星气{}", star_name(source.star),
                                    palace_name(palace),
                                    strength_name(source.star_strength)));
  if (source.gate != Gate::None && is_flourishing(source.gate_strength))
    add_good("八门得令", fmt::format("{}临{}，门气{}", gate_name(source.gate),
                                     palace_name(palace),
                                     strength_name(source.gate_strength)));
  else if (source.gate != Gate::None && is_declining(source.gate_strength))
    add_bad("八门失令", fmt::format("{}临{}，门气{}", gate_name(source.gate),
                                    palace_name(palace),
                                    strength_name(source.gate_strength)));
  if (source.is_zhi_fu)
    add_good("值符同宫", "值符临用神宫，主有统摄与贵助");
  if (source.is_zhi_shi)
    add_good("值使同宫", "值使临用神宫，事情门径与本宫相应");
  if (source.is_post_horse)
    append_unique(result.favorable,
                  {.rule = "驿马临宫",
                   .detail = "驿马发动，本宫应象主迁动、奔波或速变",
                   .nature = EffectNature::YinDong,
                   .palace = palace});
  if (source.is_void)
    add_bad("旬空", "用神宫逢旬空，先有其象而落实不足，须候填实或冲实");
  if (source.has_gate_pressure)
    add_bad("门迫", "八门克宫，门气迫宫，所谋易急迫失序");
  const auto has_pattern = [&](std::string_view name) {
    return std::ranges::any_of(source.patterns, [&](const auto &pattern) {
      return pattern.name == name;
    });
  };
  if (source.has_liu_yi_ji_xing && !has_pattern("六仪击刑"))
    add_bad("六仪击刑", "六仪临击刑之宫，主施为受刑伤与反复");
  if (source.has_san_qi_tomb && !has_pattern("三奇入墓"))
    add_bad("三奇入墓", "三奇入墓，奇气闭藏，助力难以伸展");
  for (const auto &pattern : source.patterns) {
    PanJuBasis pan_ju_basis{.rule = pattern.name,
                            .detail = pattern.pan_ju_basis,
                            .nature =
                                pattern.nature == PatternNature::Auspicious
                                    ? EffectNature::ZhuLi
                                : pattern.nature == PatternNature::Inauspicious
                                    ? EffectNature::ZhiAi
                                    : EffectNature::DaiBian,
                            .palace = palace};
    if (pan_ju_basis.nature == EffectNature::ZhuLi)
      append_unique(result.favorable, std::move(pan_ju_basis));
    else if (pan_ju_basis.nature == EffectNature::ZhiAi)
      append_unique(result.unfavorable, std::move(pan_ju_basis));
  }
  result.image =
      fmt::format("{}；{}；{}。", gate_image(source.gate),
                  star_image(source.star), spirit_image(source.spirit));
  return result;
}

std::string relation_image(PalaceRelation relation) {
  switch (relation) {
  case PalaceRelation::TongGong:
    return "日干与时干同宫，人与事相依，吉凶集中在同一宫内，应先辨该宫门星神仪"
           "。";
  case PalaceRelation::BiHe:
    return "主客两宫比和，人与事同气，推进较少外力冲突，但亦易彼此牵连。";
  case PalaceRelation::ZhuShengKe:
    return "主宫生事宫，我去生事，求测者付出较多，事情得我之力而成。";
  case PalaceRelation::KeShengZhu:
    return "事宫生主宫，事情来生我，外缘对求测者较为有助。";
  case PalaceRelation::ZhuKeKe:
    return "主宫克事宫，我能制事，宜主动经营，但仍须察主宫是否旺而不空。";
  case PalaceRelation::KeKeZhu:
    return "事宫克主宫，客来制主，事情对求测者形成压力，不宜强行躁进。";
  }
  return {};
}

Manifestation make_manifestation(QuestionKind kind,
                                 const AnalysisResult &analysis) {
  Manifestation result{.kind = kind};
  YongShenRole preferred_role = YongShenRole::ShiGan;
  switch (kind) {
  case QuestionKind::GongMing:
    result.subject = "功名事业";
    preferred_role = YongShenRole::ZhuanYongMen;
    break;
  case QuestionKind::QiuCai:
    result.subject = "求财经营";
    preferred_role = YongShenRole::ZhuanYongMen;
    break;
  case QuestionKind::HunLian:
    result.subject = "婚恋关系";
    preferred_role = YongShenRole::ZhuanYongMen;
    break;
  case QuestionKind::JiBing:
    result.subject = "疾病医药";
    preferred_role = YongShenRole::ZhuanYongXing;
    break;
  case QuestionKind::ChuXing:
    result.subject = "出行迁动";
    preferred_role = YongShenRole::ZhuanYongMen;
    break;
  case QuestionKind::GuanSong:
    result.subject = "官讼争议";
    preferred_role = YongShenRole::ZhuanYongMen;
    break;
  case QuestionKind::XueYe:
    result.subject = "学业考试";
    preferred_role = YongShenRole::ZhuanYongXing;
    break;
  case QuestionKind::XunWu:
    result.subject = "寻人失物";
    preferred_role = YongShenRole::ZhuanYongMen;
    break;
  case QuestionKind::FanZhan:
    result.subject = "泛占盘势";
    result.indication = "未定占类，仅以日时主客、值符值使和全局格局陈示盘势。";
    break;
  }
  const auto selection = std::ranges::find(analysis.yong_shen, preferred_role,
                                           &YongShenSelection::role);
  if (selection == analysis.yong_shen.end()) {
    result.pan_ju_basis = analysis.main_guest.pan_ju_basis;
    return result;
  }
  const auto reading = std::ranges::find(
      analysis.palace_readings, selection->palace, &PalaceReading::palace);
  if (reading == analysis.palace_readings.end())
    return result;

  result.pan_ju_basis = reading->favorable;
  result.pan_ju_basis.insert(result.pan_ju_basis.end(),
                             reading->unfavorable.begin(),
                             reading->unfavorable.end());
  result.pan_ju_basis.insert(result.pan_ju_basis.end(),
                             analysis.main_guest.pan_ju_basis.begin(),
                             analysis.main_guest.pan_ju_basis.end());
  const std::string tendency =
      reading->unfavorable.size() >= reading->favorable.size() + 2
          ? "本宫制碍偏重，所问先见闭塞，宜候出空、脱墓或得生扶再动"
      : reading->favorable.size() >= reading->unfavorable.size() + 2
          ? "本宫生扶较显，所问有可乘之机，宜循门星所主之道施为"
          : "本宫生扶与制碍并见，须分先后，不可以吉格抵销空墓刑迫";
  result.indication = fmt::format(
      "{}落{}，同宫{}、{}、{}，天盘{}加地盘{}；{}；{}", selection->name,
      palace_name(selection->palace), gate_name(reading->gate),
      star_name(reading->star), spirit_name(reading->spirit),
      ZhouYi::GanZhi::Mapper::to_zh(reading->heaven_stem),
      ZhouYi::GanZhi::Mapper::to_zh(reading->earth_stem), tendency,
      relation_image(analysis.main_guest.relation));
  return result;
}

std::string_view direction_name(Palace palace) {
  switch (palace) {
  case Palace::North:
    return "正北";
  case Palace::SouthWest:
    return "西南";
  case Palace::East:
    return "正东";
  case Palace::SouthEast:
    return "东南";
  case Palace::Center:
    return "中央";
  case Palace::NorthWest:
    return "西北";
  case Palace::West:
    return "正西";
  case Palace::NorthEast:
    return "东北";
  case Palace::South:
    return "正南";
  }
  return {};
}

std::vector<TimingDirection>
make_timing(const QiMenPan &pan,
            const std::vector<YongShenSelection> &selections,
            QuestionKind question_kind) {
  std::array role_order{
      YongShenRole::ZhuanYongMen,  YongShenRole::ZhuanYongXing,
      YongShenRole::ZhuanYongShen, YongShenRole::ZhuanYongGan,
      YongShenRole::ShiGan,        YongShenRole::ZhiShi,
      YongShenRole::RiGan};
  if (question_kind == QuestionKind::JiBing ||
      question_kind == QuestionKind::XueYe)
    std::swap(role_order[0], role_order[1]);
  std::vector<TimingDirection> result;
  std::set<int> visited;
  for (const auto role : role_order) {
    const auto selection =
        std::ranges::find(selections, role, &YongShenSelection::role);
    if (selection == selections.end() ||
        !visited.insert(static_cast<int>(selection->palace)).second)
      continue;
    const auto &source = palace_at(pan, selection->palace);
    const auto [branches, count] = palace_branches(selection->palace);
    TimingDirection item{
        .priority = static_cast<int>(result.size() + 1),
        .palace = selection->palace,
        .direction = std::string(direction_name(selection->palace)),
        .pace = source.is_void         ? "待填实"
                : source.is_post_horse ? "速动"
                : is_flourishing(source.gate_strength) ||
                        is_flourishing(source.star_strength)
                    ? "较速"
                    : "平候",
        .condition = source.is_void ? "候本宫地支填实或受冲而应"
                     : source.is_post_horse
                         ? "驿马临宫，逢本宫地支值期或冲动时应"
                         : "逢本宫地支值日、值时或方位发动时应",
        .basis = selection->name + "为当前占类的优先候应宫"};
    item.trigger_branches.assign(branches.begin(), branches.begin() + count);
    result.push_back(std::move(item));
    if (result.size() == 4)
      break;
  }
  return result;
}
} // namespace

AnalysisResult judge(const QiMenPan &pan, const AnalysisRequest &request) {
  AnalysisResult result;
  result.request = request;
  result.yong_shen = select_yong_shen(pan, request);

  std::set<int> visited;
  for (const auto &selection : result.yong_shen)
    if (visited.insert(static_cast<int>(selection.palace)).second)
      result.palace_readings.push_back(
          read_palace(pan, selection.palace, result.yong_shen));

  const auto host = std::ranges::find(result.yong_shen, YongShenRole::RiGan,
                                      &YongShenSelection::role);
  const auto guest = std::ranges::find(result.yong_shen, YongShenRole::ShiGan,
                                       &YongShenSelection::role);
  if (host != result.yong_shen.end() && guest != result.yong_shen.end()) {
    result.main_guest.host_palace = host->palace;
    result.main_guest.guest_palace = guest->palace;
    result.main_guest.relation =
        compare_main_guest(host->palace, guest->palace);
    result.main_guest.interpretation =
        relation_image(result.main_guest.relation);
    result.main_guest.pan_ju_basis.push_back(
        {.rule = "日时主客",
         .detail = fmt::format(
             "日干落{}为主，时干落{}为客；{}", palace_name(host->palace),
             palace_name(guest->palace), result.main_guest.interpretation),
         .nature = result.main_guest.relation == PalaceRelation::KeKeZhu
                       ? EffectNature::ZhiAi
                   : result.main_guest.relation == PalaceRelation::ZhuShengKe
                       ? EffectNature::DaiBian
                       : EffectNature::ZhuLi});
  } else {
    result.review_points.push_back(
        "排盘未携四柱，不能定位日干主宫与时干事宫。");
  }

  for (const auto &pattern : pan.global_patterns)
    append_unique(result.global_configuration,
                  {.rule = pattern.name,
                   .detail = pattern.pan_ju_basis,
                   .nature = pattern.nature == PatternNature::Auspicious
                                 ? EffectNature::ZhuLi
                             : pattern.nature == PatternNature::Inauspicious
                                 ? EffectNature::ZhiAi
                                 : EffectNature::DaiBian});
  for (const auto &reading : result.palace_readings) {
    for (const auto &item : reading.favorable)
      append_unique(result.favorable, item);
    for (const auto &item : reading.unfavorable)
      append_unique(result.unfavorable, item);
  }
  for (const auto &item : result.global_configuration) {
    if (item.nature == EffectNature::ZhuLi)
      append_unique(result.favorable, item);
    else if (item.nature == EffectNature::ZhiAi)
      append_unique(result.unfavorable, item);
  }
  if (!result.main_guest.pan_ju_basis.empty()) {
    const auto &item = result.main_guest.pan_ju_basis.front();
    if (item.nature == EffectNature::ZhuLi)
      append_unique(result.favorable, item);
    else if (item.nature == EffectNature::ZhiAi)
      append_unique(result.unfavorable, item);
  }

  result.manifestations.push_back(
      make_manifestation(request.question_kind, result));
  result.timing = make_timing(pan, result.yong_shen, request.question_kind);
  if (request.question_kind == QuestionKind::FanZhan) {
    result.judgment = Judgment::DaiDing;
    result.conclusion = "泛占未定专用门星，仅以日干为主、时干为事，合参值符值使"
                        "与宫内格局；须先定所问，方可依专用用神落断。";
    result.review_points.push_back("未限定占问门类，不能选取占类专用门星。");
  } else if (request.question_kind == QuestionKind::JiBing) {
    const auto disease =
        std::ranges::find(result.yong_shen, std::string_view("天芮星"),
                          [](const YongShenSelection &selection) {
                            return std::string_view(selection.name);
                          });
    const bool disease_void = disease != result.yong_shen.end() &&
                              palace_at(pan, disease->palace).is_void;
    const bool disease_flourishing =
        disease != result.yong_shen.end() &&
        is_flourishing(palace_at(pan, disease->palace).star_strength);
    if (disease_void) {
      result.judgment = Judgment::JiXiongBingJian;
      result.conclusion = "病神天芮落空，病象有虚退或尚未落实之一面；仍须合参死"
                          "门病气与天心医药宫，不以空亡径断无病。";
    } else if (disease_flourishing) {
      result.judgment = Judgment::ShouZu;
      result.conclusion = "病神天芮得令，病气有势；宜先取天心、乙奇及生门所示医"
                          "药救应，不宜因单见吉格而轻断。";
    } else {
      result.judgment = Judgment::JiXiongBingJian;
      result.conclusion = "病神未见旺发，仍须辨天芮受生受克、死门空迫与天心医药"
                          "是否得地，病药两端分列取断。";
    }
  } else {
    const auto &pan_ju_basis = result.manifestations.front().pan_ju_basis;
    const auto good = std::ranges::count(pan_ju_basis, EffectNature::ZhuLi,
                                         &PanJuBasis::nature);
    const auto bad = std::ranges::count(pan_ju_basis, EffectNature::ZhiAi,
                                        &PanJuBasis::nature);
    if (bad >= good + 2) {
      result.judgment = Judgment::ShouZu;
      result.conclusion = "占类用神宫制碍偏重，宜先解空墓刑迫，再候门星得令或有"
                          "制化时施为。";
    } else if (good >= bad + 2) {
      result.judgment = Judgment::YouLi;
      result.conclusion = "占类用神宫得门星神仪生扶，所占有可用之机；仍须按日时"
                          "主客辨由我推动或外缘来助。";
    } else {
      result.judgment = Judgment::JiXiongBingJian;
      result.conclusion = "占类用神宫吉格与制碍并见，助力不抵销阻隔；应分先后，"
                          "先验空墓刑迫，再取相生与吉格为救应。";
    }
  }
  if (!request.nian_ming)
    result.review_points.push_back(
        "未提供年命，不能复核求测者个体落宫与日干主宫是否同气。");
  return result;
}

} // namespace ZhouYi::QiMenAnalysis
