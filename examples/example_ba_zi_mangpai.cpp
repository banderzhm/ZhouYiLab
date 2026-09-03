// 盲派八字分析示例
import ZhouYi.BaZiController;
import ZhouYi.BaZiAnalysis;
import ZhouYi.BaZiAnalysis.MangPai.Transit;
import ZhouYi.GanZhi;
import fmt;
import std;

using namespace ZhouYi::BaZiController;
using namespace ZhouYi::GanZhi;

namespace {

void write_report(
    std::ostream &out, const BaZiResult &result, const AnalysisResult &analysis,
    const std::vector<int> &years,
    const std::vector<ZhouYi::BaZiAnalysis::MangPai::Transit::Analysis>
        &transits) {
  const auto &bazi = result.ba_zi;
  out << "==================== 盲派八字排盘与分析 ====================\n\n";
  out << "【基本信息】\n";
  out << "性别：" << (result.is_male ? "男" : "女") << "\n";
  if (result.lunar_year > 0) {
    out << "出生：农历" << result.lunar_year << "年" << result.lunar_month
        << "月" << result.lunar_day << "日 " << result.birth_hour << "时"
        << result.birth_minute << "分\n";
    out << "对应公历：" << result.birth_year << "年" << result.birth_month
        << "月" << result.birth_day << "日\n";
  } else {
    out << "出生：公历" << result.birth_year << "年" << result.birth_month
        << "月" << result.birth_day << "日 " << result.birth_hour << "时"
        << result.birth_minute << "分\n";
  }
  out << "\n【四柱八字】\n";
  out << "年柱：" << bazi.year.to_string() << "\n月柱："
      << bazi.month.to_string() << "\n日柱：" << bazi.day.to_string()
      << "\n时柱：" << bazi.hour.to_string() << "\n";
  out << "旬空：" << bazi.xun_kong_1 << bazi.xun_kong_2 << "\n\n";

  out << "【藏干】\n";
  const std::array<std::pair<std::string_view, DiZhi>, 4> branches = {
      {{"年支", bazi.year.zhi},
       {"月支", bazi.month.zhi},
       {"日支", bazi.day.zhi},
       {"时支", bazi.hour.zhi}}};
  for (const auto &[label, branch] : branches) {
    out << label << "：";
    const auto hidden = get_cang_gan(branch);
    for (std::size_t i = 0; i < hidden.size(); ++i) {
      if (i != 0)
        out << "、";
      out << Mapper::to_zh(hidden[i]);
    }
    out << "\n";
  }

  out << "\n【盲派分析】\n";
  ZhouYi::BaZiAnalysis::write_zh(out, analysis);
  for (std::size_t i = 0; i < transits.size(); ++i) {
    out << "\n【" << years[i] << "年重点岁运】\n";
    ZhouYi::BaZiAnalysis::MangPai::Transit::write_zh(out, transits[i]);
  }

  out << "\n【大运】\n";
  const auto &dayuns = result.da_yun_system.get_da_yun_list();
  for (std::size_t i = 0; i < dayuns.size(); ++i) {
    const auto &fortune = dayuns[i];
    out << "第" << (i + 1) << "步：" << fortune.pillar.to_string() << "（"
        << fortune.start_age << "-" << fortune.end_age << "岁，"
        << fortune.start_year << "-" << fortune.end_year << "年）\n";
  }
  out << "\n============================================================\n";
}

} // namespace

int main() {
  const auto chart = pai_pan_lunar(2000, 6, 15, 16, 30, true);
  AnalysisRequest request;
  request.method = AnalysisMethod::BlindSchool;
  const auto analysis = analyze_ba_zi(chart, request);
  display_analysis(analysis);

  const auto &dayuns = chart.da_yun_system.get_da_yun_list();
  const std::array<std::pair<int, std::size_t>, 8> cases = {{{2017, 1},
                                                             {2023, 1},
                                                             {2025, 1},
                                                             {2026, 1},
                                                             {2027, 2},
                                                             {2029, 2},
                                                             {2033, 2},
                                                             {2041, 3}}};
  std::vector<int> years;
  std::vector<ZhouYi::BaZiAnalysis::MangPai::Transit::Analysis> transits;
  for (const auto &[year, index] : cases) {
    if (index < dayuns.size()) {
      years.push_back(year);
      transits.push_back(ZhouYi::BaZiAnalysis::MangPai::Transit::analyze(
          chart.ba_zi, dayuns[index].pillar, chart.get_liu_nian(year).pillar,
          year, year == dayuns[index].start_year));
    }
  }
  std::ofstream out("docs/example_ba_zi_mangpai_output.txt");
  if (!out)
    return 1;
  write_report(out, chart, analysis, years, transits);
  return 0;
}
