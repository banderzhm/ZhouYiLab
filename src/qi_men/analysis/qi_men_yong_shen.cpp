module ZhouYi.QiMen.Analysis.YongShen;

import ZhouYi.GanZhi;
import std;

namespace ZhouYi::QiMenAnalysis {
namespace {
using namespace ZhouYi::QiMen;
using ZhouYi::GanZhi::TianGan;
using ZhouYi::GanZhi::wu_xing_ke;
using ZhouYi::GanZhi::wu_xing_sheng;

const PalaceInfo *find_heaven_stem(const QiMenPan &pan, TianGan stem) {
  const TianGan sought = stem == TianGan::Jia ? pan.xun_hidden_gan : stem;
  const auto found =
      std::ranges::find_if(pan.palaces, [&](const PalaceInfo &p) {
        return p.tian_gan == sought || p.extra_tian_gan == sought;
      });
  return found == pan.palaces.end() ? nullptr : &*found;
}

const PalaceInfo *find_gate(const QiMenPan &pan, Gate gate) {
  const auto found = std::ranges::find(pan.palaces, gate, &PalaceInfo::gate);
  return found == pan.palaces.end() ? nullptr : &*found;
}

const PalaceInfo *find_star(const QiMenPan &pan, Star star) {
  const auto found =
      std::ranges::find_if(pan.palaces, [&](const PalaceInfo &p) {
        return p.star == star || p.extra_star == star;
      });
  return found == pan.palaces.end() ? nullptr : &*found;
}

const PalaceInfo *find_spirit(const QiMenPan &pan, Spirit spirit) {
  const auto found =
      std::ranges::find(pan.palaces, spirit, &PalaceInfo::spirit);
  return found == pan.palaces.end() ? nullptr : &*found;
}

void append_unique(std::vector<YongShenSelection> &result,
                   YongShenSelection selection) {
  const bool exists = std::ranges::any_of(result, [&](const auto &current) {
    return current.role == selection.role && current.palace == selection.palace;
  });
  if (!exists)
    result.push_back(std::move(selection));
}

void append_gate(std::vector<YongShenSelection> &result, const QiMenPan &pan,
                 Gate gate, std::string name, std::string basis) {
  if (const auto *palace = find_gate(pan, gate))
    append_unique(result, {.role = YongShenRole::ZhuanYongMen,
                           .name = std::move(name),
                           .palace = palace->palace,
                           .basis = std::move(basis)});
}

void append_star(std::vector<YongShenSelection> &result, const QiMenPan &pan,
                 Star star, std::string name, std::string basis) {
  if (const auto *palace = find_star(pan, star))
    append_unique(result, {.role = YongShenRole::ZhuanYongXing,
                           .name = std::move(name),
                           .palace = palace->palace,
                           .basis = std::move(basis)});
}

void append_spirit(std::vector<YongShenSelection> &result, const QiMenPan &pan,
                   Spirit spirit, std::string name, std::string basis) {
  if (const auto *palace = find_spirit(pan, spirit))
    append_unique(result, {.role = YongShenRole::ZhuanYongShen,
                           .name = std::move(name),
                           .palace = palace->palace,
                           .basis = std::move(basis)});
}

void append_stem(std::vector<YongShenSelection> &result, const QiMenPan &pan,
                 TianGan stem, std::string name, std::string basis) {
  if (const auto *palace = find_heaven_stem(pan, stem))
    append_unique(result, {.role = YongShenRole::ZhuanYongGan,
                           .name = std::move(name),
                           .palace = palace->palace,
                           .basis = std::move(basis)});
}
} // namespace

std::vector<YongShenSelection>
select_yong_shen(const QiMenPan &pan, const AnalysisRequest &request) {
  std::vector<YongShenSelection> result;
  if (pan.ba_zi) {
    if (const auto *palace = find_heaven_stem(pan, pan.ba_zi->day.gan))
      append_unique(result, {.role = YongShenRole::RiGan,
                             .name = "日干主宫",
                             .palace = palace->palace,
                             .basis = "以日干为求测之人，甲干依旬首遁仪取宫"});
    if (const auto *palace = find_heaven_stem(pan, pan.ba_zi->hour.gan))
      append_unique(result, {.role = YongShenRole::ShiGan,
                             .name = "时干事宫",
                             .palace = palace->palace,
                             .basis = "以时干为所占之事，甲干依旬首遁仪取宫"});
  }
  if (request.nian_ming)
    if (const auto *palace = find_heaven_stem(pan, *request.nian_ming))
      append_unique(result, {.role = YongShenRole::NianMing,
                             .name = "年命落宫",
                             .palace = palace->palace,
                             .basis = "以出生年干复核求测者个体应象"});
  append_unique(result, {.role = YongShenRole::ZhiFu,
                         .name = "值符宫",
                         .palace = pan.zhi_fu_palace,
                         .basis = "值符统摄盘局大势"});
  append_unique(result, {.role = YongShenRole::ZhiShi,
                         .name = "值使宫",
                         .palace = pan.zhi_shi_palace,
                         .basis = "值使主事情门径与施行状态"});

  switch (request.question_kind) {
  case QuestionKind::GongMing:
    append_gate(result, pan, Gate::Kai, "开门", "功名事业以开门为职事用神");
    break;
  case QuestionKind::QiuCai:
    append_gate(result, pan, Gate::Sheng, "生门", "求财以生门为财源用神");
    break;
  case QuestionKind::HunLian:
    append_gate(result, pan, Gate::Xiu, "休门", "婚恋合参休门与六合");
    append_spirit(result, pan, Spirit::LiuHe, "六合",
                  "六合主婚合、媒介与双方牵连");
    append_stem(result, pan, TianGan::Yi, "乙奇", "婚恋以乙奇象女方");
    append_stem(result, pan, TianGan::Geng, "庚仪", "婚恋以庚仪象男方");
    break;
  case QuestionKind::JiBing:
    append_star(result, pan, Star::TianRui, "天芮星", "疾病以天芮为病神");
    append_gate(result, pan, Gate::Si, "死门", "疾病兼察死门所临与制化");
    append_star(result, pan, Star::TianXin, "天心星", "疾病以天心兼察医药救应");
    break;
  case QuestionKind::ChuXing:
    append_gate(result, pan, Gate::Kai, "开门", "出行先察开门道路是否通达");
    append_spirit(result, pan, Spirit::JiuTian, "九天",
                  "九天主远行、高举与速发");
    break;
  case QuestionKind::GuanSong:
    append_gate(result, pan, Gate::JingGate, "惊门",
                "官讼争议以惊门察口舌惊扰");
    append_spirit(result, pan, Spirit::BaiHu, "白虎", "官讼兼察白虎强制与刑伤");
    break;
  case QuestionKind::XueYe:
    append_star(result, pan, Star::TianFu, "天辅星",
                "学业考试以天辅察文教助力");
    append_gate(result, pan, Gate::Jing, "景门", "学业兼察景门文章名誉");
    break;
  case QuestionKind::XunWu:
    append_gate(result, pan, Gate::Du, "杜门", "寻人失物以杜门察隐匿闭塞");
    append_spirit(result, pan, Spirit::XuanWu, "玄武",
                  "寻失兼察玄武隐情与失脱");
    break;
  case QuestionKind::FanZhan:
    break;
  }
  return result;
}

PalaceRelation compare_main_guest(Palace host, Palace guest) {
  if (host == guest)
    return PalaceRelation::TongGong;
  const auto host_element = palace_element(host);
  const auto guest_element = palace_element(guest);
  if (host_element == guest_element)
    return PalaceRelation::BiHe;
  if (wu_xing_sheng(host_element, guest_element))
    return PalaceRelation::ZhuShengKe;
  if (wu_xing_sheng(guest_element, host_element))
    return PalaceRelation::KeShengZhu;
  if (wu_xing_ke(host_element, guest_element))
    return PalaceRelation::ZhuKeKe;
  return PalaceRelation::KeKeZhu;
}

} // namespace ZhouYi::QiMenAnalysis
