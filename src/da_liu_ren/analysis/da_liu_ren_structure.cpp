// 大六壬课传结构分析实现
module ZhouYi.DaLiuRenAnalysis.Structure;

import ZhouYi.GanZhi;
import ZhouYi.WuXingUtils;
import ZhouYi.ZhMapper;
import std;

namespace ZhouYi::DaLiuRenAnalysis {
namespace {

using ZhouYi::DaLiuRen::DaLiuRenResult;
using ZhouYi::DaLiuRen::GanZhiKe;
using ZhouYi::GanZhi::WuXing;

std::string zhi_name(DiZhi value) {
  return std::string(ZhouYi::GanZhi::Mapper::to_zh(value));
}

std::string gan_name(TianGan value) {
  return std::string(ZhouYi::GanZhi::Mapper::to_zh(value));
}

TianJiang general_at(const ZhouYi::DaLiuRen::TianDiPan &pan,
                     DiZhi heaven_branch) {
  const auto &positions = pan.get_shen_jiang();
  for (std::size_t index = 0; index < positions.size(); ++index) {
    if (positions[index] == heaven_branch)
      return static_cast<TianJiang>(index);
  }
  return TianJiang::GuiRen;
}

LessonRelation lesson_relation(const GanZhiKe &lesson) {
  const WuXing lower = lesson.get_wu_xing();
  const WuXing upper = ZhouYi::GanZhi::get_wu_xing(lesson.upper_zhi);
  if (ZhouYi::GanZhi::wu_xing_ke(lower, upper))
    return LessonRelation::XiaZeiShang;
  if (ZhouYi::GanZhi::wu_xing_ke(upper, lower))
    return LessonRelation::ShangKeXia;
  if (ZhouYi::GanZhi::wu_xing_sheng(upper, lower))
    return LessonRelation::ShangShengXia;
  if (ZhouYi::GanZhi::wu_xing_sheng(lower, upper))
    return LessonRelation::XiaShengShang;
  return LessonRelation::BiHe;
}

EffectNature relation_nature(LessonRelation relation) {
  switch (relation) {
  case LessonRelation::ShangShengXia:
    return EffectNature::ShengFu;
  case LessonRelation::XiaZeiShang:
  case LessonRelation::ShangKeXia:
  case LessonRelation::XiaShengShang:
    return EffectNature::KeZhi;
  case LessonRelation::BiHe:
    return EffectNature::BiHe;
  }
  return EffectNature::DaiBian;
}

std::string relation_detail(LessonRelation relation) {
  switch (relation) {
  case LessonRelation::XiaZeiShang:
    return "下神克上神，构成下贼上，矛盾由下而发";
  case LessonRelation::ShangKeXia:
    return "上神克下神，构成上克下，压力由上而临";
  case LessonRelation::ShangShengXia:
    return "上神生下神，下神得上神生扶";
  case LessonRelation::XiaShengShang:
    return "下神生上神，下神之气脱于上神";
  case LessonRelation::BiHe:
    return "上下同气比和，课内未见直接生克";
  }
  return "上下关系待辨";
}

std::string seasonal_state(DiZhi branch, DiZhi month_branch) {
  const auto element = ZhouYi::GanZhi::get_wu_xing(branch);
  return ZhouYi::WuXingUtils::getWangShuai(
      std::string(ZhouYi::GanZhi::Mapper::to_zh(element)),
      zhi_name(month_branch));
}

KeChuanBasis branch_relation(DiZhi from, DiZhi to,
                             bool is_transmission = true) {
  const auto from_name = zhi_name(from);
  const auto to_name = zhi_name(to);
  if (from == to)
    return {"伏吟", from_name + to_name + "同支，结构有重复、留连之象",
            EffectNature::YinDong};
  if (ZhouYi::GanZhi::is_chong(from, to))
    return {"六冲",
            from_name + to_name +
                (is_transmission ? "相冲，传间有突变、离散之象"
                                 : "上下相冲，课内见动荡离散"),
            EffectNature::KeZhi};
  if (ZhouYi::GanZhi::is_he(from, to))
    return {"六合", from_name + to_name + "相合，须辨合成或合绊",
            EffectNature::YinDong};
  if (ZhouYi::GanZhi::is_xing(from, to) || ZhouYi::GanZhi::is_xing(to, from))
    return {"相刑",
            from_name + to_name +
                (is_transmission ? "相刑，传间多阻滞反复"
                                 : "上下相刑，课内见逼迫不和"),
            EffectNature::KeZhi};
  if (ZhouYi::GanZhi::is_hai(from, to))
    return {"六害",
            from_name + to_name +
                (is_transmission ? "相害，传间有暗损牵连"
                                 : "上下相害，课内见暗损牵连"),
            EffectNature::KeZhi};
  const auto from_element = ZhouYi::GanZhi::get_wu_xing(from);
  const auto to_element = ZhouYi::GanZhi::get_wu_xing(to);
  if (ZhouYi::GanZhi::wu_xing_sheng(from_element, to_element))
    return {is_transmission ? "传间相生" : "上下相生",
            from_name + "生" + to_name +
                (is_transmission ? "，前传生后传" : "，下神生上神"),
            EffectNature::ShengFu};
  if (ZhouYi::GanZhi::wu_xing_ke(from_element, to_element))
    return {is_transmission ? "传间递克" : "上下相克",
            from_name + "克" + to_name +
                (is_transmission ? "，前传制后传" : "，下神制上神"),
            EffectNature::KeZhi};
  if (ZhouYi::GanZhi::wu_xing_sheng(to_element, from_element))
    return {is_transmission ? "传间回生" : "上下相生",
            to_name + "生" + from_name +
                (is_transmission ? "，后传回生前传" : "，上神生下神"),
            EffectNature::ShengFu};
  if (ZhouYi::GanZhi::wu_xing_ke(to_element, from_element))
    return {is_transmission ? "传间回克" : "上下相克",
            to_name + "克" + from_name +
                (is_transmission ? "，后传回克前传" : "，上神制下神"),
            EffectNature::KeZhi};
  return {is_transmission ? "传间比和" : "上下比和",
          from_name + to_name + "五行同气", EffectNature::BiHe};
}

std::string method_key(std::string_view value) {
  std::string result(value);
  if (result.ends_with("卦"))
    result.resize(result.size() - std::string("卦").size());
  return result;
}

std::string half_kind_name(ZhouYi::GanZhi::SanHeHalfKind kind) {
  switch (kind) {
  case ZhouYi::GanZhi::SanHeHalfKind::ShengWang:
    return "生旺半合";
  case ZhouYi::GanZhi::SanHeHalfKind::WangMu:
    return "旺墓半合";
  case ZhouYi::GanZhi::SanHeHalfKind::ShengMu:
    return "生墓拱合";
  }
  return "三合半局";
}

void append_pair_relations(std::vector<BranchRelationAnalysis> &result,
                           DiZhi left, DiZhi right, std::string left_source,
                           std::string right_source, bool include_half = true) {
  const auto add = [&](BranchRelationKind kind, std::string detail,
                       EffectNature nature,
                       std::optional<DiZhi> missing = std::nullopt) {
    result.push_back({.kind = kind,
                      .branches = {left, right},
                      .sources = {left_source, right_source},
                      .missing_branch = missing,
                      .detail = std::move(detail),
                      .nature = nature});
  };
  const auto left_name = zhi_name(left);
  const auto right_name = zhi_name(right);
  const auto prefix =
      left_source + left_name + "与" + right_source + right_name;
  const auto left_element = ZhouYi::GanZhi::get_wu_xing(left);
  const auto right_element = ZhouYi::GanZhi::get_wu_xing(right);

  if (left == right)
    add(BranchRelationKind::TongLin, prefix + "同临", EffectNature::YinDong);
  if (left_element == right_element)
    add(BranchRelationKind::BiHe, prefix + "五行同气比和", EffectNature::BiHe);
  else if (ZhouYi::GanZhi::wu_xing_sheng(left_element, right_element))
    add(BranchRelationKind::Sheng,
        prefix + "，" + left_name + "生" + right_name, EffectNature::ShengFu);
  else if (ZhouYi::GanZhi::wu_xing_sheng(right_element, left_element))
    add(BranchRelationKind::Sheng,
        prefix + "，" + right_name + "回生" + left_name, EffectNature::ShengFu);
  else if (ZhouYi::GanZhi::wu_xing_ke(left_element, right_element))
    add(BranchRelationKind::Ke, prefix + "，" + left_name + "克" + right_name,
        EffectNature::KeZhi);
  else
    add(BranchRelationKind::Ke, prefix + "，" + right_name + "回克" + left_name,
        EffectNature::KeZhi);

  if (ZhouYi::GanZhi::is_xing(left, right) ||
      ZhouYi::GanZhi::is_xing(right, left))
    add(BranchRelationKind::Xing, prefix + "相刑", EffectNature::KeZhi);
  if (ZhouYi::GanZhi::is_chong(left, right))
    add(BranchRelationKind::Chong, prefix + "六冲", EffectNature::KeZhi);
  if (ZhouYi::GanZhi::is_he(left, right))
    add(BranchRelationKind::LiuHe, prefix + "六合，须辨合起或合绊",
        EffectNature::YinDong);
  if (ZhouYi::GanZhi::is_hai(left, right))
    add(BranchRelationKind::Hai, prefix + "六害，主暗损牵连",
        EffectNature::KeZhi);
  if (include_half && left != right) {
    if (const auto half = ZhouYi::GanZhi::get_san_he_half(left, right))
      add(BranchRelationKind::BanHe,
          prefix + half_kind_name(half->kind) + "，尚缺" +
              zhi_name(half->missing_branch) + "方成全局",
          EffectNature::YinDong, half->missing_branch);
  }
}

MissingBranchContext missing_branch_context(DiZhi branch,
                                            const DaLiuRenResult &pan) {
  MissingBranchContext context{
      .branch = branch,
      .heaven_lands_on_earth = pan.tian_di_pan.lin(branch),
      .heaven_over_earth = pan.tian_di_pan[branch],
      .lesson_upper_positions = {},
      .lesson_lower_positions = {},
      .transmission_positions = {},
      .is_month_command = branch == pan.ba_zi.month.zhi,
      .is_day_branch = branch == pan.ba_zi.day.zhi,
      .is_hour_branch = branch == pan.ba_zi.hour.zhi,
      .is_month_general = branch == pan.yue_jiang,
      .is_void = ZhouYi::GanZhi::is_kong_wang(branch, pan.ba_zi.day.gan,
                                              pan.ba_zi.day.zhi),
      .assessment = {}};
  const std::array<const GanZhiKe *, 4> lessons = {
      &pan.si_ke.first, &pan.si_ke.second, &pan.si_ke.third, &pan.si_ke.fourth};
  for (std::size_t index = 0; index < lessons.size(); ++index) {
    const auto position = static_cast<LessonPosition>(index);
    if (lessons[index]->upper_zhi == branch)
      context.lesson_upper_positions.push_back(position);
    if (!lessons[index]->is_gan_zhi && lessons[index]->lower_zhi == branch)
      context.lesson_lower_positions.push_back(position);
  }
  const std::array<DiZhi, 3> transmissions = {pan.san_chuan.get_chu_chuan(),
                                              pan.san_chuan.get_zhong_chuan(),
                                              pan.san_chuan.get_mo_chuan()};
  for (std::size_t index = 0; index < transmissions.size(); ++index)
    if (transmissions[index] == branch)
      context.transmission_positions.push_back(
          static_cast<TransmissionStage>(index));

  const bool in_lessons = !context.lesson_upper_positions.empty() ||
                          !context.lesson_lower_positions.empty();
  const bool in_transmissions = !context.transmission_positions.empty();
  const bool in_calendar = context.is_month_command || context.is_day_branch ||
                           context.is_hour_branch || context.is_month_general;
  if (in_lessons || in_transmissions)
    context.assessment =
        "所缺支已见四课或三传，可复核是否由同课其他层补足；不得重复计作外来支";
  else if (in_calendar)
    context.assessment =
        "所缺支未入四课三传，但见于月建、日辰、占时或月将，可作外部补局条件";
  else
    context.assessment =
        "所缺支在四课三传及月建、日辰、占时、月将均未见，须候该支外来填补";
  if (context.is_void)
    context.assessment += "；本支旬空，虽见亦须先候出空填实";
  else
    context.assessment += "；是否成局仍须合参旺衰、冲散与空破";
  return context;
}

void attach_missing_context(std::vector<BranchRelationAnalysis> &relations,
                            const DaLiuRenResult &pan) {
  for (auto &relation : relations)
    if (relation.missing_branch)
      relation.missing_context =
          missing_branch_context(*relation.missing_branch, pan);
}

DiZhi opposite_of(DiZhi branch) {
  return static_cast<DiZhi>((static_cast<int>(branch) + 6) % 12);
}

DiZhi combine_partner_of(DiZhi branch) {
  for (int index = 0; index < 12; ++index) {
    const auto candidate = static_cast<DiZhi>(index);
    if (ZhouYi::GanZhi::is_he(branch, candidate))
      return candidate;
  }
  return branch;
}

DiZhi san_he_center(WuXing element) {
  switch (element) {
  case WuXing::Mu:
    return DiZhi::Mao;
  case WuXing::Huo:
    return DiZhi::Wu;
  case WuXing::Tu:
    return DiZhi::Chen;
  case WuXing::Jin:
    return DiZhi::You;
  case WuXing::Shui:
    return DiZhi::Zi;
  }
  return DiZhi::Zi;
}

std::string timing_relation_suffix(DiZhi left, DiZhi right) {
  std::vector<std::string> relations;
  const auto left_element = ZhouYi::GanZhi::get_wu_xing(left);
  const auto right_element = ZhouYi::GanZhi::get_wu_xing(right);
  if (left_element == right_element)
    relations.emplace_back("比和");
  else if (ZhouYi::GanZhi::wu_xing_sheng(left_element, right_element))
    relations.push_back(zhi_name(left) + "生" + zhi_name(right));
  else if (ZhouYi::GanZhi::wu_xing_sheng(right_element, left_element))
    relations.push_back(zhi_name(right) + "回生" + zhi_name(left));
  else if (ZhouYi::GanZhi::wu_xing_ke(left_element, right_element))
    relations.push_back(zhi_name(left) + "克" + zhi_name(right));
  else
    relations.push_back(zhi_name(right) + "回克" + zhi_name(left));
  if (ZhouYi::GanZhi::is_xing(left, right) ||
      ZhouYi::GanZhi::is_xing(right, left))
    relations.emplace_back("相刑");
  if (ZhouYi::GanZhi::is_chong(left, right))
    relations.emplace_back("六冲");
  if (ZhouYi::GanZhi::is_he(left, right))
    relations.emplace_back("六合");
  if (ZhouYi::GanZhi::is_hai(left, right))
    relations.emplace_back("六害");
  if (const auto half = ZhouYi::GanZhi::get_san_he_half(left, right))
    relations.push_back(half_kind_name(half->kind));

  std::string result = "；承接关系：";
  for (std::size_t index = 0; index < relations.size(); ++index) {
    if (index)
      result += "、";
    result += relations[index];
  }
  return result;
}

} // namespace

JiuZongMen detect_method(const ZhouYi::DaLiuRen::SanChuan &san_chuan) {
  if (san_chuan.get_ke_shi().empty())
    return JiuZongMen::WeiZhi;
  for (const auto &name : san_chuan.get_ke_shi()) {
    if (name == "涉害" || name == "涉害卦" || name == "见机卦" ||
        name == "察微卦" || name == "复等卦")
      return JiuZongMen::SheHai;
  }
  if (std::ranges::find(san_chuan.get_ke_shi(), "比用") !=
      san_chuan.get_ke_shi().end())
    return JiuZongMen::BiYong;
  const auto key = method_key(san_chuan.get_ke_shi().front());
  if (key == "贼克")
    return JiuZongMen::ZeiKe;
  if (key == "比用")
    return JiuZongMen::BiYong;
  if (key == "涉害")
    return JiuZongMen::SheHai;
  if (key == "遥克")
    return JiuZongMen::YaoKe;
  if (key == "昴星")
    return JiuZongMen::MaoXing;
  if (key == "别责")
    return JiuZongMen::BieZe;
  if (key == "八专")
    return JiuZongMen::BaZhuan;
  if (key == "伏吟")
    return JiuZongMen::FuYin;
  if (key == "返吟")
    return JiuZongMen::FanYin;
  return JiuZongMen::WeiZhi;
}

std::vector<LessonAnalysis> analyze_lessons(const DaLiuRenResult &pan) {
  const std::array<const GanZhiKe *, 4> source = {
      &pan.si_ke.first, &pan.si_ke.second, &pan.si_ke.third, &pan.si_ke.fourth};
  std::vector<LessonAnalysis> result;
  result.reserve(4);
  for (std::size_t index = 0; index < source.size(); ++index) {
    const auto &lesson = *source[index];
    const auto position = static_cast<LessonPosition>(index);
    const auto relation = lesson_relation(lesson);
    LessonAnalysis item{
        .position = position,
        .door = index < 2 ? BaMen::WaiShi : BaMen::NeiShi,
        .lower = lesson.is_gan_zhi ? gan_name(lesson.gan)
                                   : zhi_name(lesson.lower_zhi),
        .lower_palace = lesson.is_gan_zhi
                            ? ZhouYi::GanZhi::get_ji_gong(lesson.gan)
                            : lesson.lower_zhi,
        .upper = lesson.upper_zhi,
        .relative =
            ZhouYi::GanZhi::get_liu_qin(pan.ba_zi.day.gan, lesson.upper_zhi),
        .general = general_at(pan.tian_di_pan, lesson.upper_zhi),
        .relation = relation,
        .sends_initial = lesson.upper_zhi == pan.san_chuan.get_chu_chuan(),
        .ke_chuan_basis = {{"课内上下", relation_detail(relation),
                            relation_nature(relation)}}};
    if (!lesson.is_gan_zhi) {
      const auto branch_effect =
          branch_relation(lesson.lower_zhi, lesson.upper_zhi, false);
      if (branch_effect.rule != "上下比和" ||
          lesson.lower_zhi == lesson.upper_zhi)
        item.ke_chuan_basis.push_back(branch_effect);
    }
    if (item.sends_initial)
      item.ke_chuan_basis.push_back({"本课发用",
                                     "本课上神入初传，为事情发端所由出",
                                     EffectNature::YinDong});
    result.push_back(std::move(item));
  }
  return result;
}

std::vector<TransmissionAnalysis>
analyze_transmissions(const DaLiuRenResult &pan) {
  const std::array<DiZhi, 3> branches = {pan.san_chuan.get_chu_chuan(),
                                         pan.san_chuan.get_zhong_chuan(),
                                         pan.san_chuan.get_mo_chuan()};
  const auto stems =
      pan.san_chuan.get_dun_gan(pan.ba_zi.day.gan, pan.ba_zi.day.zhi);
  std::vector<TransmissionAnalysis> result;
  result.reserve(3);
  for (std::size_t index = 0; index < branches.size(); ++index) {
    TransmissionAnalysis item{
        .stage = static_cast<TransmissionStage>(index),
        .branch = branches[index],
        .earth_position = pan.tian_di_pan.lin(branches[index]),
        .hidden_stem = stems[index],
        .relative =
            ZhouYi::GanZhi::get_liu_qin(pan.ba_zi.day.gan, branches[index]),
        .general = general_at(pan.tian_di_pan, branches[index]),
        .seasonal_state = seasonal_state(branches[index], pan.ba_zi.month.zhi),
        .is_void = ZhouYi::GanZhi::is_kong_wang(
            branches[index], pan.ba_zi.day.gan, pan.ba_zi.day.zhi),
        .shen_sha = pan.shen_sha.get_shensha_on_zhi(branches[index]),
        .ke_chuan_basis = {}};
    if (item.is_void)
      item.ke_chuan_basis.push_back(
          {"旬空",
           zhi_name(item.branch) + "传临旬空，所主之象先虚，须待出空填实",
           EffectNature::KeZhi});
    if (index > 0)
      item.ke_chuan_basis.push_back(
          branch_relation(branches[index - 1], branches[index]));
    result.push_back(std::move(item));
  }
  const auto [is_group, element] =
      ZhouYi::GanZhi::is_san_he(branches[0], branches[1], branches[2]);
  if (is_group) {
    result.front().ke_chuan_basis.push_back(
        {"三传三合",
         "初中末三传构成完整三合局，课传之气归于" +
             std::string(ZhouYi::GanZhi::Mapper::to_zh(element)),
         EffectNature::YinDong});
  }
  return result;
}

std::vector<PersonalMarkerAnalysis>
analyze_personal_markers(const DaLiuRenResult &pan,
                         const AnalysisRequest &request) {
  const std::array<std::pair<PersonalMarkerKind, std::optional<DiZhi>>, 2>
      markers = {{{PersonalMarkerKind::BenMing, request.ben_ming},
                  {PersonalMarkerKind::XingNian, request.xing_nian}}};
  const std::array<std::pair<TransmissionStage, DiZhi>, 3> transmissions = {
      {{TransmissionStage::Chu, pan.san_chuan.get_chu_chuan()},
       {TransmissionStage::Zhong, pan.san_chuan.get_zhong_chuan()},
       {TransmissionStage::Mo, pan.san_chuan.get_mo_chuan()}}};

  std::vector<PersonalMarkerAnalysis> result;
  result.reserve(2);
  for (const auto &[kind, branch] : markers) {
    if (!branch)
      continue;
    const auto marker_name = kind == PersonalMarkerKind::BenMing
                                 ? std::string("本命")
                                 : std::string("行年");
    const auto upper = pan.tian_di_pan[*branch];
    PersonalMarkerAnalysis item{
        .kind = kind,
        .branch = *branch,
        .upper = upper,
        .general = general_at(pan.tian_di_pan, upper),
        .relative = ZhouYi::GanZhi::get_liu_qin(pan.ba_zi.day.gan, upper),
        .branch_is_void = ZhouYi::GanZhi::is_kong_wang(
            *branch, pan.ba_zi.day.gan, pan.ba_zi.day.zhi),
        .upper_is_void = ZhouYi::GanZhi::is_kong_wang(upper, pan.ba_zi.day.gan,
                                                      pan.ba_zi.day.zhi),
        .transmission_relations = {},
        .ke_chuan_basis = {}};

    for (const auto &[stage, transmission] : transmissions) {
      const auto stage_name =
          stage == TransmissionStage::Chu     ? std::string("初传")
          : stage == TransmissionStage::Zhong ? std::string("中传")
                                              : std::string("末传");
      append_pair_relations(item.transmission_relations, upper, transmission,
                            marker_name + "上神", stage_name);
    }

    std::string detail =
        marker_name + zhi_name(*branch) + "宫上见" + zhi_name(upper) + "，乘" +
        std::string(ZhouYi::Mapper::to_zh(item.general)) + "，以" +
        std::string(ZhouYi::GanZhi::liu_qin_to_zh(item.relative)) +
        "入课传取象";
    if (item.branch_is_void)
      detail += "；本支旬空，所主根基先虚";
    if (item.upper_is_void)
      detail += "；上神旬空，所临之象须待出空填实";
    item.ke_chuan_basis.push_back(
        {marker_name + "临盘", std::move(detail), EffectNature::YinDong});
    result.push_back(std::move(item));
  }
  return result;
}

std::vector<BaMenAnalysis> analyze_eight_doors(
    const DaLiuRenResult &pan, const AnalysisRequest &request,
    const std::vector<LessonAnalysis> &lessons,
    const std::vector<TransmissionAnalysis> &transmissions,
    const std::vector<PersonalMarkerAnalysis> &personal_markers) {
  std::vector<BaMenAnalysis> result;
  result.reserve(8);
  result.push_back({BaMen::XianFeng,
                    zhi_name(pan.ba_zi.hour.zhi),
                    pan.ba_zi.hour.zhi == pan.san_chuan.get_chu_chuan()
                        ? "占时即发用，先锋与发端同临，事情应在眼前起动"
                        : "占时为课成之先兆，须与发用及所乘天将合参",
                    {{"占时", "以时支为先锋门，主眼下先兆与事情迟速",
                      EffectNature::YinDong}}});
  result.push_back(
      {BaMen::ZhiShi,
       zhi_name(pan.yue_jiang),
       "月将主值事之气，所临宫位为当下天时落处",
       {{"月将", "月将加占时布天盘，不能与月建混称", EffectNature::YinDong}}});
  result.push_back({BaMen::WaiShi, "日干及一、二课",
                    "外事门见" + zhi_name(lessons[0].upper) + "、" +
                        zhi_name(lessons[1].upper) +
                        "，主求测方表里状态与外部作为",
                    lessons[0].ke_chuan_basis});
  result.push_back({BaMen::NeiShi, "日支及三、四课",
                    "内事门见" + zhi_name(lessons[2].upper) + "、" +
                        zhi_name(lessons[3].upper) + "，主对方、宅舍及内部情状",
                    lessons[2].ke_chuan_basis});
  result.push_back(
      {BaMen::FaDuan, zhi_name(transmissions[0].branch),
       "发用为事之起点，临" +
           std::string(
               ZhouYi::GanZhi::liu_qin_to_zh(transmissions[0].relative)) +
           "乘" + std::string(ZhouYi::Mapper::to_zh(transmissions[0].general)),
       transmissions[0].ke_chuan_basis});
  result.push_back({BaMen::YiYi, zhi_name(transmissions[1].branch),
                    "中传为移易门，主事情中途转折，临" +
                        std::string(ZhouYi::GanZhi::liu_qin_to_zh(
                            transmissions[1].relative)),
                    transmissions[1].ke_chuan_basis});
  result.push_back(
      {BaMen::GuiJi, zhi_name(transmissions[2].branch),
       "末传为归计门，主事情归宿，临" +
           std::string(
               ZhouYi::GanZhi::liu_qin_to_zh(transmissions[2].relative)) +
           "乘" + std::string(ZhouYi::Mapper::to_zh(transmissions[2].general)),
       transmissions[2].ke_chuan_basis});
  std::string bian_ti;
  if (!request.ben_ming && !request.xing_nian)
    bian_ti = "未提供本命与行年，不推测变体门";
  else {
    for (std::size_t index = 0; index < personal_markers.size(); ++index) {
      if (index)
        bian_ti += "；";
      const auto &marker = personal_markers[index];
      bian_ti +=
          std::string(marker.kind == PersonalMarkerKind::BenMing ? "本命"
                                                                 : "行年") +
          zhi_name(marker.branch) + "宫上见" + zhi_name(marker.upper) + "，乘" +
          std::string(ZhouYi::Mapper::to_zh(marker.general)) + "临" +
          std::string(ZhouYi::GanZhi::liu_qin_to_zh(marker.relative));
      if (marker.branch_is_void || marker.upper_is_void)
        bian_ti += "，并见旬空";
    }
    bian_ti += "；其与初中末三传的生克刑冲合害、半合另见本命行年临盘";
  }
  result.push_back({BaMen::BianTi,
                    "本命与行年",
                    bian_ti,
                    {{"变体门", "本命、行年用于变易公共课象对具体求测者的作用",
                      EffectNature::DaiBian}}});
  return result;
}

std::vector<PatternAnalysis> analyze_patterns(const DaLiuRenResult &pan) {
  std::vector<PatternAnalysis> result;
  for (const auto &name : pan.gua_ti) {
    result.push_back(
        {name,
         "课体已由排盘规则命中；其吉凶须随占类、旺衰、空破和所乘天将变断",
         {{"课体成立", "排盘课体判定命中“" + name + "”",
           EffectNature::YinDong}}});
  }
  return result;
}

std::vector<PalaceAnalysis> analyze_palaces(const DaLiuRenResult &pan) {
  std::vector<PalaceAnalysis> result;
  result.reserve(12);
  const auto &earth = pan.tian_di_pan.get_di_pan();
  const auto &heaven = pan.tian_di_pan.get_tian_pan();
  for (std::size_t index = 0; index < earth.size(); ++index) {
    PalaceAnalysis palace{.earth = earth[index],
                          .heaven = heaven[index],
                          .general = general_at(pan.tian_di_pan, heaven[index]),
                          .relative = ZhouYi::GanZhi::get_liu_qin(
                              pan.ba_zi.day.gan, heaven[index]),
                          .relations = {}};
    append_pair_relations(palace.relations, heaven[index], earth[index], "天盘",
                          "地盘");
    result.push_back(std::move(palace));
  }
  return result;
}

std::vector<BranchRelationAnalysis>
analyze_cross_layer_relations(const DaLiuRenResult &pan,
                              const std::vector<LessonAnalysis> &lessons) {
  struct Node {
    DiZhi branch;
    std::string source;
    bool transmission;
  };
  const std::array<DiZhi, 3> transmission_branches = {
      pan.san_chuan.get_chu_chuan(), pan.san_chuan.get_zhong_chuan(),
      pan.san_chuan.get_mo_chuan()};
  constexpr std::array<std::string_view, 3> transmission_names = {
      "初传", "中传", "末传"};
  std::vector<BranchRelationAnalysis> result;
  std::vector<Node> nodes;
  nodes.reserve(transmission_branches.size() + lessons.size());

  for (std::size_t index = 0; index < transmission_branches.size(); ++index) {
    const auto branch = transmission_branches[index];
    const auto source = std::string(transmission_names[index]);
    nodes.push_back({branch, source, true});
    append_pair_relations(result, branch, pan.tian_di_pan.lin(branch), source,
                          "所临地盘");
    for (const auto &lesson : lessons)
      append_pair_relations(
          result, branch, lesson.upper, source,
          std::string(ZhouYi::Mapper::to_zh(lesson.position)) + "上神");
  }
  for (const auto &lesson : lessons) {
    const auto lesson_source =
        std::string(ZhouYi::Mapper::to_zh(lesson.position));
    append_pair_relations(result, lesson.upper, lesson.lower_palace,
                          lesson_source + "上神",
                          lesson_source + "下神所居地盘");
    nodes.push_back({lesson.upper, lesson_source + "上神", false});
  }
  for (std::size_t left = 0; left < lessons.size(); ++left) {
    for (std::size_t right = left + 1; right < lessons.size(); ++right) {
      append_pair_relations(
          result, lessons[left].upper, lessons[right].upper,
          std::string(ZhouYi::Mapper::to_zh(lessons[left].position)) + "上神",
          std::string(ZhouYi::Mapper::to_zh(lessons[right].position)) + "上神");
    }
  }

  std::set<std::array<int, 3>> emitted_groups;
  for (std::size_t first = 0; first < nodes.size(); ++first) {
    for (std::size_t second = first + 1; second < nodes.size(); ++second) {
      for (std::size_t third = second + 1; third < nodes.size(); ++third) {
        if (nodes[first].transmission && nodes[second].transmission &&
            nodes[third].transmission)
          continue;
        std::array<int, 3> key = {static_cast<int>(nodes[first].branch),
                                  static_cast<int>(nodes[second].branch),
                                  static_cast<int>(nodes[third].branch)};
        std::ranges::sort(key);
        if (key[0] == key[1] || key[1] == key[2] ||
            emitted_groups.contains(key))
          continue;
        const auto [is_group, element] = ZhouYi::GanZhi::is_san_he(
            nodes[first].branch, nodes[second].branch, nodes[third].branch);
        if (!is_group)
          continue;
        emitted_groups.insert(key);
        const bool is_cross_layer = nodes[first].transmission ||
                                    nodes[second].transmission ||
                                    nodes[third].transmission;
        result.push_back(
            {.kind = BranchRelationKind::SanHe,
             .branches = {nodes[first].branch, nodes[second].branch,
                          nodes[third].branch},
             .sources = {nodes[first].source, nodes[second].source,
                         nodes[third].source},
             .missing_branch = std::nullopt,
             .detail = nodes[first].source + zhi_name(nodes[first].branch) +
                       "、" + nodes[second].source +
                       zhi_name(nodes[second].branch) + "、" +
                       nodes[third].source + zhi_name(nodes[third].branch) +
                       std::string(is_cross_layer ? "跨层" : "四课上神") +
                       "构成三合局，归气于" +
                       std::string(ZhouYi::GanZhi::Mapper::to_zh(element)),
             .nature = EffectNature::YinDong});
      }
    }
  }
  attach_missing_context(result, pan);
  return result;
}

std::vector<BranchRelationAnalysis>
analyze_transmission_relations(const DaLiuRenResult &pan) {
  const std::array<DiZhi, 3> branches = {pan.san_chuan.get_chu_chuan(),
                                         pan.san_chuan.get_zhong_chuan(),
                                         pan.san_chuan.get_mo_chuan()};
  constexpr std::array<std::string_view, 3> stage_names = {"初传", "中传",
                                                           "末传"};
  std::vector<BranchRelationAnalysis> result;
  const auto [is_san_he, san_he_element] =
      ZhouYi::GanZhi::is_san_he(branches[0], branches[1], branches[2]);
  const auto [is_san_hui, san_hui_element] =
      ZhouYi::GanZhi::is_san_hui(branches[0], branches[1], branches[2]);

  for (std::size_t left_index = 0; left_index < branches.size(); ++left_index) {
    for (std::size_t right_index = left_index + 1;
         right_index < branches.size(); ++right_index) {
      append_pair_relations(result, branches[left_index], branches[right_index],
                            std::string(stage_names[left_index]),
                            std::string(stage_names[right_index]), !is_san_he);
    }
  }

  if (is_san_he) {
    result.push_back(
        {.kind = BranchRelationKind::SanHe,
         .branches = std::vector<DiZhi>(branches.begin(), branches.end()),
         .sources = {"初传", "中传", "末传"},
         .missing_branch = std::nullopt,
         .detail = "三传构成完整三合局，归气于" +
                   std::string(ZhouYi::GanZhi::Mapper::to_zh(san_he_element)),
         .nature = EffectNature::YinDong});
  }
  if (is_san_hui) {
    result.push_back(
        {.kind = BranchRelationKind::SanHui,
         .branches = std::vector<DiZhi>(branches.begin(), branches.end()),
         .sources = {"初传", "中传", "末传"},
         .missing_branch = std::nullopt,
         .detail = "三传构成完整三会局，汇气于" +
                   std::string(ZhouYi::GanZhi::Mapper::to_zh(san_hui_element)),
         .nature = EffectNature::YinDong});
  }
  attach_missing_context(result, pan);
  return result;
}

std::vector<TimingCandidate> analyze_timing(const DaLiuRenResult &pan,
                                            const LeiShenSelection &lei_shen) {
  const auto initial = pan.san_chuan.get_chu_chuan();
  const auto middle = pan.san_chuan.get_zhong_chuan();
  const auto final = pan.san_chuan.get_mo_chuan();
  std::vector<TimingCandidate> result;
  int priority = 1;
  const auto is_void = [&](DiZhi branch) {
    return ZhouYi::GanZhi::is_kong_wang(branch, pan.ba_zi.day.gan,
                                        pan.ba_zi.day.zhi);
  };
  const auto pace_of = [&](DiZhi branch,
                           bool waits_for_condition = false) -> YingQiPace {
    if (waits_for_condition || is_void(branch))
      return YingQiPace::DaiShi;
    if (branch == pan.ba_zi.day.zhi || branch == pan.ba_zi.hour.zhi)
      return YingQiPace::Su;
    const auto state = seasonal_state(branch, pan.ba_zi.month.zhi);
    if (state == "旺")
      return YingQiPace::Su;
    if (state == "相")
      return YingQiPace::JiaoSu;
    if (state == "囚" || state == "死")
      return YingQiPace::JiaoChi;
    return YingQiPace::Ping;
  };
  const auto scope_of = [&](DiZhi branch) {
    if (branch == pan.ba_zi.hour.zhi)
      return std::string("未定占限：短占先候时、日，长占再候月");
    if (branch == pan.ba_zi.day.zhi)
      return std::string("未定占限：短占先候日，长占再候月");
    return std::string("未定占限：短占候值日，长事兼候值月");
  };
  const auto constraints_of = [&](DiZhi branch) {
    std::vector<std::string> items;
    if (is_void(branch))
      items.emplace_back("本支旬空，须先出空填实");
    if (branch == pan.ba_zi.day.zhi)
      items.emplace_back("与日辰同临，主近而催发");
    if (branch == pan.ba_zi.hour.zhi)
      items.emplace_back("与占时同临，主眼前先兆催发");
    const auto state = seasonal_state(branch, pan.ba_zi.month.zhi);
    items.push_back("月令气势为" + state);
    const auto shen_sha = pan.shen_sha.get_shensha_on_zhi(branch);
    if (std::ranges::any_of(shen_sha, [](const std::string &name) {
          return name.find("驿马") != std::string::npos;
        }))
      items.emplace_back("驿马同临，动中较易应");
    if (std::ranges::find(lei_shen.appearances, branch) !=
        lei_shen.appearances.end())
      items.push_back("主类神“" + lei_shen.name +
                      "”在四课三传同支，须兼验所占之事");
    else if (std::ranges::find(lei_shen.plate_positions, branch) !=
             lei_shen.plate_positions.end())
      items.push_back("主类神“" + lei_shen.name +
                      "”在天盘见于此支，可作为专项应期佐证");
    std::string text;
    for (std::size_t index = 0; index < items.size(); ++index) {
      if (index)
        text += "；";
      text += items[index];
    }
    return text;
  };
  const auto append = [&](DiZhi trigger, YingQiRule rule, YingQiPhase phase,
                          YingQiPace pace, std::string condition,
                          std::string manifestation, std::string constraints,
                          std::string priority_reason,
                          std::vector<KeChuanBasis> ke_chuan_basis) {
    const auto description = condition + "；" + manifestation;
    result.push_back({.priority = priority++,
                      .trigger = trigger,
                      .rule = rule,
                      .phase = phase,
                      .pace = pace,
                      .time_scope = scope_of(trigger),
                      .condition = std::move(condition),
                      .manifestation = std::move(manifestation),
                      .constraints = std::move(constraints),
                      .priority_reason = std::move(priority_reason),
                      .description = description,
                      .ke_chuan_basis = std::move(ke_chuan_basis)});
  };

  const bool initial_void = is_void(initial);
  append(
      initial, initial_void ? YingQiRule::ChuKong : YingQiRule::FaYongZhi,
      YingQiPhase::FaDuan, pace_of(initial, initial_void),
      "逢" + zhi_name(initial) +
          (initial_void ? "出空填实并值日、值月" : "值日或值月，发用得以落实"),
      "发用所主之事开始显动，先验事情是否真正启动", constraints_of(initial),
      "初传为发端门，故列第一候",
      {{initial_void ? "发用出空" : "发用值期",
        "以初传定发端；空则先候填实，不空则候其值期", EffectNature::YinDong}});

  const std::array<DiZhi, 5> references = {pan.ba_zi.month.zhi,
                                           pan.ba_zi.day.zhi,
                                           pan.ba_zi.hour.zhi, middle, final};
  const bool initial_is_combined =
      std::ranges::any_of(references, [&](DiZhi value) {
        return ZhouYi::GanZhi::is_he(initial, value);
      });
  const bool initial_is_clashed =
      std::ranges::any_of(references, [&](DiZhi value) {
        return ZhouYi::GanZhi::is_chong(initial, value);
      });
  if (initial_is_combined) {
    const auto trigger = opposite_of(initial);
    append(trigger, YingQiRule::FaYongChong, YingQiPhase::ChuFa,
           YingQiPace::DaiShi,
           "逢" + zhi_name(trigger) + "冲开被合的发用" + zhi_name(initial),
           "合绊解除，原先胶着之事转为显动",
           constraints_of(trigger) + "；发用现被合绊，未冲开前不作实应",
           "发用受合，解除发用之绊优先于中末传",
           {{"合待冲开",
             zhi_name(initial) + "见合，须候" + zhi_name(trigger) + "冲开",
             EffectNature::YinDong}});
  } else if (initial_is_clashed) {
    const auto trigger = combine_partner_of(initial);
    append(trigger, YingQiRule::FaYongHe, YingQiPhase::ChuFa,
           YingQiPace::DaiShi,
           "逢" + zhi_name(trigger) + "合住受冲的发用" + zhi_name(initial),
           "冲动之势得到承接，事情由散动转为可落实",
           constraints_of(trigger) + "；发用现受冲，须辨冲散还是冲动",
           "发用受冲，先列合住发用的解除条件",
           {{"冲待合住",
             zhi_name(initial) + "见冲，须候" + zhi_name(trigger) + "合住",
             EffectNature::YinDong}});
  } else {
    const auto trigger = opposite_of(initial);
    append(trigger, YingQiRule::FaYongChong, YingQiPhase::ChuFa,
           pace_of(trigger),
           "逢" + zhi_name(trigger) + "冲动发用" + zhi_name(initial),
           "外力触发发用，事情由潜伏转为显现", constraints_of(trigger),
           "发用本身未见合冲，以冲动作为第二触发候选",
           {{"冲动发用",
             "以" + zhi_name(trigger) + "冲" + zhi_name(initial) +
                 "，观察事情显动",
             EffectNature::YinDong}});
  }

  append(middle, YingQiRule::ZhongChuanZhi, YingQiPhase::YiYi, pace_of(middle),
         "逢" + zhi_name(middle) + "值日或值月",
         "中传所主的过程变化、交接或转折出现" +
             timing_relation_suffix(initial, middle),
         constraints_of(middle), "中传为移易门，承接发用之后列第三候",
         {{"中传值期", "以中传值期观察事情中途移易", EffectNature::YinDong}});
  append(final, YingQiRule::MoChuanZhi, YingQiPhase::GuiJi, pace_of(final),
         "逢" + zhi_name(final) + "值日或值月",
         "末传所主的归宿、成败或收尾显现" +
             timing_relation_suffix(middle, final),
         constraints_of(final), "末传为归计门，故列第四候验最终结果",
         {{"末传值期", "以末传值期观察事情归计", EffectNature::YinDong}});

  const auto [is_group, element] =
      ZhouYi::GanZhi::is_san_he(initial, middle, final);
  if (is_group) {
    const auto center = san_he_center(element);
    append(center, YingQiRule::SanHeWangShen, YingQiPhase::ChengJu,
           pace_of(center), "逢三合旺神" + zhi_name(center) + "值日或值月",
           "三传之气聚于" +
               std::string(ZhouYi::GanZhi::Mapper::to_zh(element)) +
               "局，事情呈集中成势之象",
           constraints_of(center), "三传已经成局，以旺神作为第五聚气候",
           {{"三合旺神", "完整三合以旺神值期验其聚气", EffectNature::YinDong}});
  } else {
    std::optional<ZhouYi::GanZhi::SanHeHalfResult> half;
    for (const auto pair : std::array<std::pair<DiZhi, DiZhi>, 3>{
             std::pair{initial, middle}, std::pair{middle, final},
             std::pair{initial, final}}) {
      half = ZhouYi::GanZhi::get_san_he_half(pair.first, pair.second);
      if (half)
        break;
    }
    if (half) {
      append(half->missing_branch, YingQiRule::BanHeBuQi, YingQiPhase::ChengJu,
             pace_of(half->missing_branch),
             "逢" + zhi_name(half->missing_branch) + "补齐三合体系",
             "半合由待聚转为成局，事情的合力增强",
             constraints_of(half->missing_branch),
             "三传仅成半合，以补齐所缺支作为第五结构候",
             {{"半合补齐", "以所缺支补齐三合体系验其成局",
               EffectNature::YinDong}});
    } else {
      const auto pioneer = pan.ba_zi.hour.zhi;
      append(
          pioneer, YingQiRule::XianFengChongLin, YingQiPhase::ChuFa,
          pace_of(pioneer), "逢占时支" + zhi_name(pioneer) + "再次重临",
          "先锋门重现，作为眼前先兆再次发动的辅助验期", constraints_of(pioneer),
          "三传未成合局，以先锋重临作为第五辅助候",
          {{"先锋重临", "以占时重临辅助观察眼前先兆", EffectNature::DaiBian}});
    }
  }
  return result;
}

} // namespace ZhouYi::DaLiuRenAnalysis
