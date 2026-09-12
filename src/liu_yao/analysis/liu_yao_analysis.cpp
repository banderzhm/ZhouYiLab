// 六爻分析实现
module ZhouYi.LiuYaoAnalysis;

import ZhouYi.GanZhi;
import ZhouYi.LiuYaoAnalysis.Relations;
import ZhouYi.WuXingUtils;
import std;

namespace ZhouYi::LiuYaoAnalysis {
namespace {

using ZhouYi::BaZiBase::BaZi;
using ZhouYi::GanZhi::DiZhi;
using ZhouYi::GanZhi::WuXing;
using ZhouYi::LiuYao::HexagramInfo;
using ZhouYi::LiuYao::YaoDetails;

std::string target_relative(QuestionKind kind) {
  switch (kind) {
  case QuestionKind::ZiShen:
    return "世爻";
  case QuestionKind::CaiYun:
  case QuestionKind::HunLianNan:
  case QuestionKind::ShiWu:
    return "妻财";
  case QuestionKind::GongMing:
  case QuestionKind::HunLianNv:
  case QuestionKind::JiBing:
  case QuestionKind::GuanSi:
    return "官鬼";
  case QuestionKind::ZiNv:
    return "子孙";
  case QuestionKind::FuMuWenShu:
    return "父母";
  case QuestionKind::XiongDiPengYou:
    return "兄弟";
  }
  return "世爻";
}

int month_strength(std::string_view value) {
  if (value == "旺")
    return 5;
  if (value == "相")
    return 4;
  if (value == "休")
    return 2;
  if (value == "囚")
    return 0;
  if (value == "死")
    return -1;
  return 1;
}

YaoStrength strength_from(int value) {
  if (value >= 6)
    return YaoStrength::WangXiang;
  if (value >= 3)
    return YaoStrength::YouQi;
  if (value >= 0)
    return YaoStrength::XiuQiu;
  return YaoStrength::ShuaiRuo;
}

int branch_strength(DiZhi branch, const BaZi &bazi) {
  const auto element = ZhouYi::GanZhi::get_wu_xing(branch);
  int strength = month_strength(ZhouYi::WuXingUtils::getWangShuai(
      std::string(ZhouYi::GanZhi::Mapper::to_zh(element)),
      bazi.month.branch()));
  const auto day_element = ZhouYi::GanZhi::get_wu_xing(bazi.day.zhi);
  if (element == day_element ||
      ZhouYi::GanZhi::wu_xing_sheng(day_element, element))
    strength += 2;
  else if (ZhouYi::GanZhi::wu_xing_ke(day_element, element))
    strength -= 2;
  else
    --strength;
  if (ZhouYi::GanZhi::is_kong_wang(branch, bazi.day.gan, bazi.day.zhi))
    strength -= 3;
  if (ZhouYi::GanZhi::is_chong(branch, bazi.month.zhi))
    strength -= 3;
  if (ZhouYi::GanZhi::is_chong(branch, bazi.day.zhi))
    strength -= 2;
  return strength;
}

std::string_view strength_name(YaoStrength strength) {
  switch (strength) {
  case YaoStrength::WangXiang:
    return "旺相";
  case YaoStrength::YouQi:
    return "有气";
  case YaoStrength::XiuQiu:
    return "休囚";
  case YaoStrength::ShuaiRuo:
    return "衰弱";
  }
  return "休囚";
}

int strength_tendency(YaoStrength strength) {
  switch (strength) {
  case YaoStrength::WangXiang:
    return 6;
  case YaoStrength::YouQi:
    return 3;
  case YaoStrength::XiuQiu:
    return 0;
  case YaoStrength::ShuaiRuo:
    return -2;
  }
  return 0;
}

void add_evidence(YaoAnalysis &analysis, std::string rule, std::string detail,
                  EffectNature nature) {
  analysis.gua_yao_basis.push_back(
      GuaYaoBasis{.rule = std::move(rule),
                  .detail = std::move(detail),
                  .nature = nature,
                  .positions = {analysis.position}});
}

int apply_day_relation(YaoAnalysis &analysis, WuXing line_element,
                       WuXing day_element) {
  if (line_element == day_element) {
    add_evidence(analysis, "日辰比扶", "爻支与日辰同气，得日辰比扶",
                 EffectNature::ShengFu);
    return 2;
  }
  if (ZhouYi::GanZhi::wu_xing_sheng(day_element, line_element)) {
    add_evidence(analysis, "日辰生爻", "爻支得日辰相生", EffectNature::ShengFu);
    return 2;
  }
  if (ZhouYi::GanZhi::wu_xing_ke(day_element, line_element)) {
    add_evidence(analysis, "日辰克爻", "爻支受日辰克制", EffectNature::KeZhi);
    return -2;
  }
  if (ZhouYi::GanZhi::wu_xing_sheng(line_element, day_element)) {
    add_evidence(analysis, "爻生日辰", "爻气泄于日辰", EffectNature::KeZhi);
    return -1;
  }
  add_evidence(analysis, "爻克日辰", "爻气耗于制日辰", EffectNature::KeZhi);
  return -1;
}

void add_branch_relations(YaoAnalysis &analysis, DiZhi line_branch,
                          DiZhi time_branch, std::string_view source) {
  const auto branch_name =
      std::string(ZhouYi::GanZhi::Mapper::to_zh(time_branch));
  if (line_branch == time_branch) {
    add_evidence(analysis, std::string(source) + "临爻",
                 std::string(source) + branch_name + "临爻",
                 EffectNature::ShengFu);
  }
  if (ZhouYi::GanZhi::is_he(line_branch, time_branch)) {
    add_evidence(analysis, std::string(source) + "合爻",
                 std::string(source) + branch_name + "合爻，须辨合起或合绊",
                 EffectNature::YinDong);
  }
  if (ZhouYi::GanZhi::is_xing(line_branch, time_branch)) {
    add_evidence(analysis, std::string(source) + "刑爻",
                 std::string(source) + branch_name + "刑爻",
                 EffectNature::KeZhi);
  }
  if (ZhouYi::GanZhi::is_hai(line_branch, time_branch)) {
    add_evidence(analysis, std::string(source) + "害爻",
                 std::string(source) + branch_name + "害爻",
                 EffectNature::KeZhi);
  }
}

std::string transformation_of(const YaoDetails &line) {
  if (!line.isChanging)
    return {};
  const auto main_element = ZhouYi::GanZhi::get_wu_xing(line.mainPillar.zhi);
  const auto changed_element =
      ZhouYi::GanZhi::get_wu_xing(line.changedPillar.zhi);
  if (ZhouYi::GanZhi::wu_xing_sheng(changed_element, main_element))
    return "回头生";
  if (ZhouYi::GanZhi::wu_xing_ke(changed_element, main_element))
    return "回头克";
  if (main_element == changed_element)
    return line.mainPillar.zhi == line.changedPillar.zhi ? "化伏吟" : "化比和";
  if (ZhouYi::GanZhi::wu_xing_sheng(main_element, changed_element))
    return "化泄";
  return "化耗";
}

EffectNature transformation_nature(std::string_view transformation) {
  if (transformation == "回头生" || transformation == "化比和")
    return EffectNature::ShengFu;
  if (transformation == "回头克" || transformation == "化泄" ||
      transformation == "化耗")
    return EffectNature::KeZhi;
  return EffectNature::YinDong;
}

int selection_rank(const YaoAnalysis &analysis) {
  int rank = 0;
  switch (analysis.strength) {
  case YaoStrength::WangXiang:
    rank += 8;
    break;
  case YaoStrength::YouQi:
    rank += 5;
    break;
  case YaoStrength::XiuQiu:
    rank += 2;
    break;
  case YaoStrength::ShuaiRuo:
    break;
  }
  rank += analysis.is_changing ? 2 : 0;
  rank += analysis.is_shi ? 1 : 0;
  rank -= analysis.is_xun_kong ? 3 : 0;
  rank -= analysis.is_month_broken ? 3 : 0;
  return rank;
}

std::optional<std::size_t>
select_visible_yong_shen(const std::vector<YaoAnalysis> &analyses,
                         std::string_view target) {
  std::optional<std::size_t> selected;
  int best_rank = std::numeric_limits<int>::min();
  for (std::size_t index = 0; index < analyses.size(); ++index) {
    const bool matches = target == "世爻" ? analyses[index].is_shi
                                          : analyses[index].relative == target;
    if (!matches)
      continue;
    const int rank = selection_rank(analyses[index]);
    if (!selected || rank > best_rank) {
      selected = index;
      best_rank = rank;
    }
  }
  return selected;
}

std::string line_label(const YaoAnalysis &line) {
  return "第" + std::to_string(line.position) + "爻";
}

void add_relation(std::vector<GuaYaoBasis> &relations, std::string rule,
                  std::string detail, EffectNature nature,
                  std::vector<int> positions) {
  relations.push_back(GuaYaoBasis{.rule = std::move(rule),
                                  .detail = std::move(detail),
                                  .nature = nature,
                                  .positions = std::move(positions)});
}

} // namespace

AnalysisResult analyze(const std::vector<YaoDetails> &yao_list,
                       const HexagramInfo &main_info,
                       const std::optional<HexagramInfo> &changed_info,
                       const BaZi &bazi, const AnalysisRequest &request) {
  if (yao_list.size() != 6)
    throw std::invalid_argument("六爻分析必须接收六个完整爻位");
  if (request.specified_yong_shen_position &&
      (*request.specified_yong_shen_position < 1 ||
       *request.specified_yong_shen_position > 6))
    throw std::invalid_argument("人工指定用神爻位必须在1—6之间");

  AnalysisResult result;
  result.request = request;
  result.main_hexagram = main_info.palaceType + "宫·" + main_info.name;
  if (changed_info)
    result.changed_hexagram =
        changed_info->palaceType + "宫·" + changed_info->name;

  std::vector<int> internal_strength;
  internal_strength.reserve(6);
  result.yao.reserve(6);
  for (const auto &line : yao_list) {
    YaoAnalysis analysis;
    analysis.position = line.position;
    analysis.relative = line.mainRelative;
    analysis.branch = line.mainPillar.branch();
    analysis.is_shi = line.shiYingMark == "世";
    analysis.is_ying = line.shiYingMark == "应";
    analysis.is_changing = line.isChanging;
    analysis.is_xun_kong = ZhouYi::GanZhi::is_kong_wang(
        line.mainPillar.zhi, bazi.day.gan, bazi.day.zhi);
    analysis.is_month_broken =
        ZhouYi::GanZhi::is_chong(line.mainPillar.zhi, bazi.month.zhi);
    analysis.is_day_broken =
        ZhouYi::GanZhi::is_chong(line.mainPillar.zhi, bazi.day.zhi);

    int strength = month_strength(line.wangShuai);
    add_evidence(analysis, "月建旺衰", "爻气于月建为" + line.wangShuai,
                 strength >= 4 ? EffectNature::ShengFu : EffectNature::Ping);
    strength += apply_day_relation(
        analysis, ZhouYi::GanZhi::get_wu_xing(line.mainPillar.zhi),
        ZhouYi::GanZhi::get_wu_xing(bazi.day.zhi));
    add_branch_relations(analysis, line.mainPillar.zhi, bazi.month.zhi, "月建");
    add_branch_relations(analysis, line.mainPillar.zhi, bazi.day.zhi, "日辰");

    if (analysis.is_xun_kong) {
      strength -= 3;
      add_evidence(analysis, "旬空", "爻支落日旬空，眼下难以落实",
                   EffectNature::KeZhi);
    }
    if (analysis.is_month_broken) {
      strength -= 3;
      add_evidence(analysis, "月破", "爻支受月建冲破", EffectNature::KeZhi);
    }
    if (analysis.is_day_broken) {
      strength -= 2;
      add_evidence(analysis, "日破", "爻支受日辰冲破", EffectNature::KeZhi);
    }
    if (analysis.is_changing) {
      ++strength;
      analysis.transformation = transformation_of(line);
      add_evidence(analysis, analysis.transformation,
                   "动爻变" + line.changedPillar.branch() + "，" +
                       analysis.transformation,
                   transformation_nature(analysis.transformation));
    }
    analysis.strength = strength_from(strength);
    internal_strength.push_back(strength);
    result.yao.push_back(std::move(analysis));
  }

  result.yong_shen.target = target_relative(request.question_kind);
  std::optional<std::size_t> selected;
  if (request.specified_yong_shen_position) {
    selected =
        static_cast<std::size_t>(*request.specified_yong_shen_position - 1);
    result.yong_shen.manually_specified = true;
    result.yong_shen.reason = "依调用方指定爻位取用";
  } else {
    selected = select_visible_yong_shen(result.yao, result.yong_shen.target);
  }

  if (selected) {
    result.yong_shen.state = YongShenState::Xian;
    result.yong_shen.position = result.yao[*selected].position;
    result.yong_shen.branch = result.yao[*selected].branch;
    result.yong_shen.strength = result.yao[*selected].strength;
    if (!result.yong_shen.manually_specified) {
      result.yong_shen.reason = result.yong_shen.target == "世爻"
                                    ? "自身泛占，以世爻为用"
                                    : "同类多现时择旺、有气或发动者为用";
    }
  } else {
    for (const auto &line : yao_list) {
      if (line.hiddenRelative == result.yong_shen.target) {
        result.yong_shen.state = YongShenState::FuCang;
        result.yong_shen.position = line.position;
        result.yong_shen.branch = line.hiddenPillar.branch();
        result.yong_shen.strength =
            strength_from(branch_strength(line.hiddenPillar.zhi, bazi));
        result.yong_shen.reason = "用神不上卦，于飞神之下伏藏";
        break;
      }
    }
  }
  if (result.yong_shen.state == YongShenState::BuXian) {
    result.yong_shen.reason = "用神在本卦及伏神中皆不现";
    result.review_points.push_back(
        "用神不现，须结合日月是否引出及占问原委复核");
  }

  auto branch_relations = Detail::analyze_branch_relations(
      yao_list, bazi, result.yong_shen.position);
  result.relations.insert(result.relations.end(),
                          std::make_move_iterator(branch_relations.begin()),
                          std::make_move_iterator(branch_relations.end()));

  const auto shi = std::ranges::find_if(
      result.yao, [](const auto &line) { return line.is_shi; });
  const auto ying = std::ranges::find_if(
      result.yao, [](const auto &line) { return line.is_ying; });
  if (shi != result.yao.end() && ying != result.yao.end()) {
    const auto shi_wx =
        ZhouYi::GanZhi::get_wu_xing(yao_list[shi->position - 1].mainPillar.zhi);
    const auto ying_wx = ZhouYi::GanZhi::get_wu_xing(
        yao_list[ying->position - 1].mainPillar.zhi);
    EffectNature nature = EffectNature::Ping;
    std::string detail = "世应比和，双方气势相持";
    if (ZhouYi::GanZhi::wu_xing_sheng(ying_wx, shi_wx)) {
      nature = EffectNature::ShengFu;
      detail = "应爻生世爻，外缘来助于我";
    } else if (ZhouYi::GanZhi::wu_xing_ke(ying_wx, shi_wx)) {
      nature = EffectNature::KeZhi;
      detail = "应爻克世爻，外部对我形成制约";
    } else if (ZhouYi::GanZhi::wu_xing_sheng(shi_wx, ying_wx)) {
      nature = EffectNature::KeZhi;
      detail = "世爻生应爻，我方泄气趋就对方";
    } else if (ZhouYi::GanZhi::wu_xing_ke(shi_wx, ying_wx)) {
      nature = EffectNature::YinDong;
      detail = "世爻克应爻，我方主动求取而事有阻力";
    }
    add_relation(result.relations, "世应作用", std::move(detail), nature,
                 {shi->position, ying->position});
  }

  int tendency = 0;
  if (selected) {
    auto &yong = result.yao[*selected];
    tendency += internal_strength[*selected];
    if (yong.is_changing) {
      const auto nature = transformation_nature(yong.transformation);
      tendency += nature == EffectNature::ShengFu ? 3 : -3;
    }
    if (shi != result.yao.end() && shi->position != yong.position) {
      const auto shi_wx = ZhouYi::GanZhi::get_wu_xing(
          yao_list[shi->position - 1].mainPillar.zhi);
      const auto yong_wx = ZhouYi::GanZhi::get_wu_xing(
          yao_list[yong.position - 1].mainPillar.zhi);
      EffectNature nature = EffectNature::Ping;
      std::string detail = "世用比和，同气相求";
      if (ZhouYi::GanZhi::wu_xing_sheng(yong_wx, shi_wx)) {
        nature = EffectNature::ShengFu;
        detail = "用神生世，所占之事来就于我";
        tendency += 2;
      } else if (ZhouYi::GanZhi::wu_xing_ke(yong_wx, shi_wx)) {
        nature = EffectNature::KeZhi;
        detail = "用神克世，所占之事反制于我";
        tendency -= 2;
      } else if (ZhouYi::GanZhi::wu_xing_sheng(shi_wx, yong_wx)) {
        nature = EffectNature::KeZhi;
        detail = "世爻生用，我方为事泄气";
        --tendency;
      } else if (ZhouYi::GanZhi::wu_xing_ke(shi_wx, yong_wx)) {
        nature = EffectNature::YinDong;
        detail = "世爻克用，我方求取用神，得失须看世用旺衰";
      }
      add_relation(result.relations, "世用作用", std::move(detail), nature,
                   {shi->position, yong.position});
    }

    const auto yong_wx = ZhouYi::GanZhi::get_wu_xing(
        yao_list[result.yao[*selected].position - 1].mainPillar.zhi);
    for (const auto &line : result.yao) {
      if (!line.is_changing || line.position == result.yao[*selected].position)
        continue;
      const auto moving_wx = ZhouYi::GanZhi::get_wu_xing(
          yao_list[line.position - 1].mainPillar.zhi);
      if (ZhouYi::GanZhi::wu_xing_sheng(moving_wx, yong_wx)) {
        tendency += 2;
        add_relation(result.relations, "动爻生用",
                     line_label(line) + "发动生扶用神", EffectNature::ShengFu,
                     {line.position, result.yao[*selected].position});
      } else if (ZhouYi::GanZhi::wu_xing_ke(moving_wx, yong_wx)) {
        tendency -= 2;
        add_relation(result.relations, "动爻克用",
                     line_label(line) + "发动克制用神", EffectNature::KeZhi,
                     {line.position, result.yao[*selected].position});
      }
    }
  }

  if (!selected && result.yong_shen.state == YongShenState::FuCang &&
      result.yong_shen.strength) {
    tendency = strength_tendency(*result.yong_shen.strength) - 2;
    const auto position = *result.yong_shen.position;
    const auto index = static_cast<std::size_t>(position - 1);
    const auto hidden_wx =
        ZhouYi::GanZhi::get_wu_xing(yao_list[index].hiddenPillar.zhi);
    const auto flying_wx =
        ZhouYi::GanZhi::get_wu_xing(yao_list[index].mainPillar.zhi);
    if (ZhouYi::GanZhi::wu_xing_sheng(flying_wx, hidden_wx)) {
      tendency += 2;
      add_relation(result.relations, "飞神生伏神",
                   "飞神生扶伏藏用神，伏神较易得出", EffectNature::ShengFu,
                   {position});
    } else if (ZhouYi::GanZhi::wu_xing_ke(flying_wx, hidden_wx)) {
      tendency -= 2;
      add_relation(result.relations, "飞神克伏神",
                   "飞神压制伏藏用神，须待冲飞引伏", EffectNature::KeZhi,
                   {position});
    } else if (ZhouYi::GanZhi::wu_xing_sheng(hidden_wx, flying_wx)) {
      --tendency;
      add_relation(result.relations, "伏神生飞神",
                   "伏藏用神泄气生飞，眼下难以得用", EffectNature::KeZhi,
                   {position});
    }
  }

  if (result.yong_shen.state == YongShenState::BuXian) {
    result.judgment = Judgment::DaiDing;
    result.conclusion = "用神不现，现有卦象不足以径下吉凶。";
  } else {
    if (tendency >= 7)
      result.judgment = Judgment::Ji;
    else if (tendency >= 4)
      result.judgment = Judgment::PianJi;
    else if (tendency >= 1)
      result.judgment = Judgment::Ping;
    else if (tendency >= -2)
      result.judgment = Judgment::PianXiong;
    else
      result.judgment = Judgment::Xiong;

    const auto position = *result.yong_shen.position;
    result.conclusion = "用神" + result.yong_shen.target;
    if (result.yong_shen.state == YongShenState::FuCang)
      result.conclusion += "伏于第" + std::to_string(position) + "爻飞神之下";
    else
      result.conclusion += "落第" + std::to_string(position) + "爻";
    result.conclusion += "，自身月日气势为" +
                         std::string(strength_name(*result.yong_shen.strength));
    result.conclusion += "；须合看世用、动变及空破后定应期。";
  }

  if (request.question_kind == QuestionKind::JiBing)
    result.review_points.push_back(
        "疾病占须另看官鬼为病、子孙为医药，不以单一吉凶替代诊疗");
  if (std::ranges::none_of(result.yao,
                           [](const auto &line) { return line.is_changing; }))
    result.review_points.push_back(
        "卦中无动爻，以世用和日月旺衰为主，不强取变卦之象");

  return result;
}

} // namespace ZhouYi::LiuYaoAnalysis
