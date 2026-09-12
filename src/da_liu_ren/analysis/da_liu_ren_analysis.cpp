// 大六壬分析统一入口实现
module ZhouYi.DaLiuRenAnalysis;

import ZhouYi.DaLiuRenAnalysis.LeiShen;
import ZhouYi.DaLiuRenAnalysis.Structure;
import ZhouYi.GanZhi;
import ZhouYi.ZhMapper;
import std;

namespace ZhouYi::DaLiuRenAnalysis {
namespace {

void locate_lei_shen(LeiShenSelection &selection,
                     const ZhouYi::DaLiuRen::DaLiuRenResult &pan,
                     const std::vector<LessonAnalysis> &lessons,
                     const std::vector<TransmissionAnalysis> &transmissions) {
  if (!selection.primary_relative)
    return;
  for (const auto branch : pan.tian_di_pan.get_tian_pan()) {
    if (ZhouYi::GanZhi::get_liu_qin(pan.ba_zi.day.gan, branch) ==
        *selection.primary_relative)
      selection.plate_positions.push_back(branch);
  }
  for (const auto &lesson : lessons) {
    if (lesson.relative == *selection.primary_relative)
      selection.appearances.push_back(lesson.upper);
  }
  for (const auto &transmission : transmissions) {
    if (transmission.relative == *selection.primary_relative)
      selection.appearances.push_back(transmission.branch);
  }
  std::ranges::sort(selection.appearances, [](DiZhi left, DiZhi right) {
    return static_cast<int>(left) < static_cast<int>(right);
  });
  const auto unique = std::ranges::unique(selection.appearances);
  selection.appearances.erase(unique.begin(), unique.end());
}

void collect_channels(AnalysisResult &result) {
  const auto append_unique = [](std::vector<KeChuanBasis> &target,
                                const KeChuanBasis &ke_chuan_basis) {
    const bool exists =
        std::ranges::any_of(target, [&](const KeChuanBasis &current) {
          return current.rule == ke_chuan_basis.rule &&
                 current.detail == ke_chuan_basis.detail;
        });
    if (!exists)
      target.push_back(ke_chuan_basis);
  };
  const auto collect = [&](const KeChuanBasis &ke_chuan_basis) {
    if (ke_chuan_basis.nature == EffectNature::ShengFu)
      append_unique(result.favorable, ke_chuan_basis);
    else if (ke_chuan_basis.nature == EffectNature::KeZhi)
      append_unique(result.unfavorable, ke_chuan_basis);
  };
  for (const auto &lesson : result.lessons)
    for (const auto &ke_chuan_basis : lesson.ke_chuan_basis)
      collect(ke_chuan_basis);
  for (const auto &transmission : result.transmissions)
    for (const auto &ke_chuan_basis : transmission.ke_chuan_basis)
      if (ke_chuan_basis.rule == "旬空")
        collect(ke_chuan_basis);
  for (const auto &relation : result.transmission_relations)
    collect(
        KeChuanBasis{.rule = std::string(ZhouYi::Mapper::to_zh(relation.kind)),
                     .detail = relation.detail,
                     .nature = relation.nature});
}

Judgment judge(const AnalysisResult &result) {
  if (result.request.question_kind == QuestionKind::FanZhan)
    return Judgment::DaiDing;
  if (!result.transmissions.empty() && result.transmissions.front().is_void)
    return Judgment::DaiDing;
  if (result.unfavorable.size() >= result.favorable.size() + 3)
    return Judgment::PianXiong;
  if (result.favorable.size() >= result.unfavorable.size() + 3)
    return Judgment::PianJi;
  return Judgment::Ping;
}

std::string conclusion_for(const AnalysisResult &result) {
  if (result.request.question_kind == QuestionKind::FanZhan)
    return "未限定占问门类，当前仅陈列公共课象；须先定类神，方可依主客与三传落"
           "断。";
  const auto &first = result.transmissions.front();
  const auto &last = result.transmissions.back();
  std::string text =
      "事情由" + std::string(ZhouYi::GanZhi::liu_qin_to_zh(first.relative)) +
      "发端，初传乘" + std::string(ZhouYi::Mapper::to_zh(first.general)) +
      "；中传主其移易，末传归于" +
      std::string(ZhouYi::GanZhi::liu_qin_to_zh(last.relative)) + "乘" +
      std::string(ZhouYi::Mapper::to_zh(last.general)) + "。";
  if (first.is_void)
    text += "发用空亡，事情先有其象而落实不足，宜候出空填实再验。";
  else if (!result.unfavorable.empty() && !result.favorable.empty())
    text += "课中制碍与救应并见，不作一端断，须以类神及末传所归定成败。";
  else if (!result.unfavorable.empty())
    text += "课传制碍较显，所占之事不宜躁进。";
  else
    text += "课传生扶较显，但仍须核对类神是否旺相、入传并得将。";
  return text;
}

} // namespace

AnalysisResult analyze(const ZhouYi::DaLiuRen::DaLiuRenResult &pan,
                       const AnalysisRequest &request) {
  AnalysisResult result;
  result.request = request;
  result.method = detect_method(pan.san_chuan);
  result.lesson_patterns = pan.san_chuan.get_ke_shi();
  result.lessons = analyze_lessons(pan);
  result.transmissions = analyze_transmissions(pan);
  result.personal_markers = analyze_personal_markers(pan, request);
  result.eight_doors =
      analyze_eight_doors(pan, request, result.lessons, result.transmissions,
                          result.personal_markers);
  auto strategy = make_question_strategy(request.question_kind);
  result.lei_shen = strategy->select(request);
  if (request.question_kind == QuestionKind::HunYin &&
      !request.specified_lei_shen && request.gender) {
    result.lei_shen.primary_relative =
        *request.gender == SeekerGender::Nan ? LiuQin::QiCai : LiuQin::GuanGui;
    result.lei_shen.name =
        *request.gender == SeekerGender::Nan ? "妻财" : "官鬼";
  }
  locate_lei_shen(result.lei_shen, pan, result.lessons, result.transmissions);
  result.patterns = analyze_patterns(pan);
  result.palaces = analyze_palaces(pan);
  result.transmission_relations = analyze_transmission_relations(pan);
  result.cross_layer_relations =
      analyze_cross_layer_relations(pan, result.lessons);
  result.timing = analyze_timing(pan, result.lei_shen);
  collect_channels(result);
  result.judgment = judge(result);
  result.conclusion = conclusion_for(result);

  if (!request.ben_ming)
    result.review_points.push_back(
        "未提供本命，变体门不能判断本命上神对课传吉凶的转化。");
  if (!request.xing_nian)
    result.review_points.push_back(
        "未提供行年，不能判断行年入课、入传或制化类神。");
  if (request.question_kind == QuestionKind::HunYin &&
      !request.specified_lei_shen && !request.gender)
    result.review_points.push_back(
        "婚姻占未提供求测者性别，不能确定以妻财或官鬼为主类神。");
  if (result.lei_shen.primary_relative && result.lei_shen.appearances.empty())
    result.review_points.push_back(
        "主类神未入四课三传，须转查类神所临天地盘、所乘天将及本命行年。");
  if (result.method == JiuZongMen::WeiZhi)
    result.review_points.push_back(
        "现有三传仅保存课式名称，未保存完整取传轨迹，九宗门依据需要复核。");
  return result;
}

} // namespace ZhouYi::DaLiuRenAnalysis
