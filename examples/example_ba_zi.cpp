// 八字系统示例
import ZhouYi.BaZiController;
import ZhouYi.BaZiAnalysis;
import ZhouYi.GanZhi;
import ZhouYi.ZhMapper;
import fmt;
import std;

using namespace ZhouYi::BaZiController;
using namespace ZhouYi::GanZhi;
using namespace ZhouYi::Mapper;
using namespace std;

int main() {
  // AI 提示词输出
  fmt::print(R"(
- 角色：传统文化与命理学专家
- 背景：用户希望从传统文化角度理解八字排盘、命局结构和岁运变化。
- 专业设定：熟悉子平法、月令取格、调候、格局病药、十神组合及干支关系。
- 分析能力：能够核对排盘，分析五行生克、旺衰、格局、喜忌与岁运，并给出可追溯依据。
- 目标：依据排盘结果说明命局结构、格局成败、取用依据及岁运作用。
- 约束：不得夸大或作确定性吉凶承诺；所有结论仅供传统文化研究与参考。
- 输出格式：先列排盘事实，再列算法口径、命理判断、证据和待复核事项。
)");

  fmt::print("\n");
  fmt::print("\n");
  fmt::print("                    八字排盘系统                            \n");
  fmt::print("\n");
  fmt::print("\n");

  try {
    // 农历日期参数
    // int lunar_year = 2005, lunar_month = 11, lunar_day = 2, lunar_hour = 8,
    // lunar_minute =30 ;
    int lunar_year = 2000, lunar_month = 6, lunar_day = 15, lunar_hour = 16,
        lunar_minute = 30;
    // int lunar_year = 2005, lunar_month = 10, lunar_day = 23, lunar_hour = 19,
    // lunar_minute =30 ; int lunar_year = 2001, lunar_month = 5, lunar_day =
    // 25, lunar_hour = 4, lunar_minute =30 ;
    bool is_male = true;

    auto result = pai_pan_lunar(lunar_year, lunar_month, lunar_day, lunar_hour,
                                lunar_minute, is_male);

    // 从排盘结果中获取信息生成标题
    fmt::print("【八字排盘示例】农历{}年{}月{}日{}时{}分（{}）\n", lunar_year,
               lunar_month, lunar_day, result.birth_hour, result.birth_minute,
               result.is_male ? "男" : "女");
    fmt::print("\n");
    display_result(result);

    // 分析层：输出五行力量、日主承载能力和可解释的用神候选。
    const auto analysis = analyze_ba_zi(result);
    fmt::print("\n");
    display_analysis(analysis);

    // 岁运联合层：既保留2017回归样例，也输出未来重点流年。
    const auto &da_yun_list = result.da_yun_system.get_da_yun_list();
    const std::array<std::pair<int, std::size_t>, 8> transit_cases = {{
        {2017, 1},
        {2023, 1},
        {2025, 1},
        {2026, 1},
        {2027, 2},
        {2029, 2},
        {2033, 2},
        {2041, 3},
    }};
    std::vector<int> transit_years;
    std::vector<ZhouYi::BaZiAnalysis::TransitAnalysis> transit_results;
    for (const auto &[year, dayun_index] : transit_cases) {
      if (dayun_index >= da_yun_list.size())
        continue;
      transit_years.push_back(year);
      transit_results.push_back(
          analyze_sui_yun(result, da_yun_list[dayun_index].pillar, year));
    }
    ZhouYi::BaZiAnalysis::annotate_sample_extremes(transit_results);
    for (std::size_t index = 0; index < transit_results.size(); ++index) {
      fmt::print("\n【{}年重点岁运】\n", transit_years[index]);
      ZhouYi::BaZiAnalysis::write_zh(std::cout, transit_results[index]);
    }

    fmt::print("\n【八字详细信息】\n");
    fmt::print("\n");
    const auto &ba_zi = result.ba_zi;

    // 显示四柱
    fmt::print("年柱：{}\n", ba_zi.year.to_string());
    fmt::print("月柱：{}\n", ba_zi.month.to_string());
    fmt::print("日柱：{}\n", ba_zi.day.to_string());
    fmt::print("时柱：{}\n", ba_zi.hour.to_string());
    fmt::print("\n");

    // 显示十神关系
    fmt::print("【十神关系】\n");
    fmt::print("\n");
    fmt::print("年干对日干：{}\n",
               shi_shen_to_zh(get_shi_shen(ba_zi.day.gan, ba_zi.year.gan)));
    fmt::print("月干对日干：{}\n",
               shi_shen_to_zh(get_shi_shen(ba_zi.day.gan, ba_zi.month.gan)));
    fmt::print("时干对日干：{}\n",
               shi_shen_to_zh(get_shi_shen(ba_zi.day.gan, ba_zi.hour.gan)));
    fmt::print("\n");

    // 显示大运信息
    fmt::print("【大运信息】\n");
    fmt::print("\n");
    display_da_yun(result, 10); // 显示前5个大运
    fmt::print("\n");

    // 示例5：查看流年信息
    fmt::print("流年信息（2020-2030）\n");
    fmt::print("\n");
    display_liu_nian(result, 2020, 30);
    fmt::print("\n");

    /*fmt::print("2025年流月信息\n");
    fmt::print("\n");
    display_liu_yue(result, 2025);
    fmt::print("\n");*/

    fmt::print("2026年流月信息\n");
    fmt::print("\n");
    display_liu_yue(result, 2026);
    fmt::print("\n");

    /*
    fmt::print("2027年流月信息\n");
    fmt::print("\n");
    display_liu_yue(result, 2027);
    fmt::print("\n");
    */

    // 示例7：查看流日信息
    fmt::print("2026年9月流日信息（前90天）\n");
    fmt::print("\n");
    display_liu_ri(result, 2026, 9, 90);
    fmt::print("\n");

  } catch (const std::exception &e) {
    fmt::print(" 错误：{}\n", e.what());
    return 1;
  }

  return 0;
}
