// 紫微斗数运限（ZiWeiResult::get_horoscope）回归
//
// 命例：农历 2000 年六月十五日 16 时（公历 2000-07-16 16:00），申时，男命。
// 该命例的基础排盘基准已由 tests/regression_zi_wei.cpp 对照 Metis 网站固定：
// 庚辰年、土五局、命宫在亥（寅起宫序 9）、身宫在卯、命主巨门、身主文昌。
// 本用例只验证运限契约，不重复验证安星。
//
// 目标运限：农历 2026 年三月初五午时，虚岁 27（2026 - 2000 + 1）。
// 固定预期来自公共基准与传统口诀，不由被测代码反算：
//   1) 公元 2026 年干支为丙午（2024 甲辰、2025 乙巳、2026 丙午）；
//   2) 流年以流年地支定宫，宫序自寅宫起 0，午宫为第 4 宫；
//   3) 大限：土五局 5 岁起运，庚辰阳年男命顺行，自命宫亥宫起布，
//      宫 9 为 5-14 岁、宫 10 为 15-24 岁、宫 11 为 25-34 岁；
//   4) 大限宫干支即该宫真实宫干支：乙庚之年戊寅头（五虎遁），故宫 0 起戊寅，
//      宫 11 为丑宫得己丑；旧实现按限序起甲乙、并把宫序直转地支枚举，均属错位；
//   5) 小限按三合局起宫：庚辰属申子辰，戌宫（宫 8）起 1 岁，男命顺行，
//      27 岁落在宫 10；与 palaces[i].xiao_xian_ages 必须同宫；
//   6) 岁前十二神自流年地支起岁建顺布，将前十二神自三合将星起顺布
//      （寅午戌年将星在午）；
//   7) 本命盘岁前／将前仍以生年支庚辰起例，因此必须与流年口径不同；
//   8) 四化顺序固定为禄→权→科→忌：丙年天同禄、天机权、文昌科、廉贞忌，
//      辛年巨门禄、太阳权、文曲科、文昌忌。
// 未提供 location 时不校正真太阳时；本用例不传 location。

import ZhouYi.GanZhi;
import ZhouYi.ZiWei;
import ZhouYi.ZiWei.Constants;
import ZhouYi.ZiWei.Horoscope;
import ZhouYi.ZiWei.SiHua;
import ZhouYi.ZiWei.Star;
import ZhouYi.ZhMapper;
import fmt;
import std;

using namespace ZhouYi::GanZhi;
using namespace ZhouYi::ZiWei;
using namespace std;

namespace {

/** @brief 断言失败计数；为 0 时进程返回 0。 */
int failures = 0;

/** @brief 已执行断言数，用于报告。 */
int checks_run = 0;

/** @brief 按寅起宫序取地支，只用于报告展示，不参与被测逻辑。 */
DiZhi palace_zhi(int gong_index) {
  return static_cast<DiZhi>((gong_index + 2) % 12);
}

string zhi_zh(DiZhi zhi) { return string(ZhouYi::GanZhi::Mapper::to_zh(zhi)); }

string gan_zh(TianGan gan) {
  return string(ZhouYi::GanZhi::Mapper::to_zh(gan));
}

string sui_zh(SuiQian12 value) { return string(ZhouYi::Mapper::to_zh(value)); }

string jiang_zh(JiangQian12 value) {
  return string(ZhouYi::Mapper::to_zh(value));
}

string xing_zh(SiHuaXing value) { return string(ZhouYi::Mapper::to_zh(value)); }

string hua_zh(SiHua value) { return string(ZhouYi::Mapper::to_zh(value)); }

/** @brief 记录一条断言结果。 */
void check(bool passed, string_view item, const string &expected,
           const string &actual, ostream &output) {
  ++checks_run;
  if (!passed)
    ++failures;
  output << "| " << item << " | " << (passed ? "一致" : "不一致") << " | "
         << expected << " | " << actual << " |\n";
}

/** @brief 收集一组运限流曜的全部星名，排序后返回，便于与基准集合逐值比较。 */
vector<string> star_names(const array<HoroscopeStarData, 12> &group) {
  vector<string> names;
  for (const auto &entry : group)
    names.insert(names.end(), entry.stars.begin(), entry.stars.end());
  ranges::sort(names);
  return names;
}

/** @brief 取指定宫位的流曜名列表，空宫返回空串。 */
string stars_at(const array<HoroscopeStarData, 12> &group, int gong_index) {
  string result;
  for (const string &name : group[gong_index].stars) {
    if (!result.empty())
      result += "、";
    result += name;
  }
  return result.empty() ? "无" : result;
}

/** @brief 返回流曜所在宫序；未落宫时返回 -1，用于逐宫定位校验。 */
int palace_of_star(const array<HoroscopeStarData, 12> &group,
                   string_view name) {
  for (const auto &entry : group)
    if (ranges::find(entry.stars, name) != entry.stars.end())
      return entry.gong_index;
  return -1;
}

/** @brief 把基准星名表排序，使两侧比较不受书写次序影响。 */
template <size_t N>
vector<string> sorted_names(const array<string_view, N> &expected) {
  vector<string> names(expected.begin(), expected.end());
  ranges::sort(names);
  return names;
}

/** @brief 判断一组 12 宫数据的 gong_index 是否恰好覆盖 0-11 各一次。 */
bool covers_every_palace(const array<HoroscopeStarData, 12> &group) {
  array<bool, 12> seen{};
  for (const auto &entry : group)
    seen[entry.gong_index] = true;
  return ranges::all_of(seen, [](bool value) { return value; });
}

/** @brief 判断 12 个宫序是否恰好覆盖 0-11 各一次。 */
bool covers_every_palace(const vector<int> &gong_indices) {
  if (gong_indices.size() != 12)
    return false;
  array<bool, 12> seen{};
  for (const int index : gong_indices) {
    if (index < 0 || index > 11)
      return false;
    seen[index] = true;
  }
  return ranges::all_of(seen, [](bool value) { return value; });
}

/** @brief 判断 12 个岁前神是否恰好覆盖十二神各一次。 */
bool covers_every_sui_qian(const array<SuiQian12, 12> &values) {
  array<bool, 12> seen{};
  for (const SuiQian12 value : values)
    seen[static_cast<int>(value)] = true;
  return ranges::all_of(seen, [](bool value) { return value; });
}

/** @brief 判断 12 个将前神是否恰好覆盖十二神各一次。 */
bool covers_every_jiang_qian(const array<JiangQian12, 12> &values) {
  array<bool, 12> seen{};
  for (const JiangQian12 value : values)
    seen[static_cast<int>(value)] = true;
  return ranges::all_of(seen, [](bool value) { return value; });
}

/** @brief 把一条四化序列写成“化禄-星名”形式，用于逐项比对与报告。 */
string si_hua_text(const vector<SiHuaEntry> &entries) {
  string result;
  for (const SiHuaEntry &entry : entries) {
    if (!result.empty())
      result += "、";
    result += hua_zh(entry.hua) + xing_zh(entry.xing);
  }
  return result;
}

/** @brief 断言四化序列的类型次序为化禄、化权、化科、化忌。 */
bool hua_order_is_lu_quan_ke_ji(const vector<SiHuaEntry> &entries) {
  if (entries.size() != 4)
    return false;
  for (size_t index = 0; index < entries.size(); ++index)
    if (static_cast<int>(entries[index].hua) != static_cast<int>(index))
      return false;
  return true;
}

/** @brief 断言四化的星名序列与明细逐项同源。 */
bool si_hua_names_match_entries(const array<string, 4> &names,
                                const vector<SiHuaEntry> &entries) {
  if (entries.size() != names.size())
    return false;
  for (size_t index = 0; index < names.size(); ++index)
    if (names[index] != xing_zh(entries[index].xing))
      return false;
  return true;
}

/** @brief 取出命盘中记录该虚岁的宫位；没有记录时返回 -1。 */
int palace_of_age(const ZiWeiResult &chart, int age) {
  for (int index = 0; index < 12; ++index)
    if (ranges::find(chart.palaces[index].xiao_xian_ages, age) !=
        chart.palaces[index].xiao_xian_ages.end())
      return index;
  return -1;
}

} // namespace

int main() {
  ostream &output = cout;
  fmt::print("\n【紫微斗数运限 get_horoscope 回归】\n\n");

  // 命例基准：与 tests/regression_zi_wei.cpp 的 Metis 对照案例一致。
  const ZiWeiResult chart = pai_pan_lunar(2000, 6, 15, 16, true);
  const int target_year = 2026;
  const int target_month = 3; // 农历三月
  const int target_day = 5;   // 农历初五
  constexpr DiZhi target_hour = DiZhi::Wu;
  const int current_age = 27; // 虚岁

  const HoroscopeResult horoscope = chart.get_horoscope(
      target_year, target_month, target_day, target_hour, current_age);

  std::ostringstream table;
  table << "| 核验项 | 结果 | 基准 | ZhouYiLab |\n";
  table << "| --- | --- | --- | --- |\n";

  // 1. 命例基础值，确认回归前提成立。
  check(chart.ming_gong_index == 9 && chart.shen_gong_index == 1 &&
            chart.wu_xing_ju == WuXingJu::TuWuJu &&
            chart.year_pillar.zhi == DiZhi::Chen,
        "命例基础（命宫亥、身宫卯、土五局、庚辰年）", "9／1／土五局／辰",
        fmt::format("{}／{}／{}／{}", chart.ming_gong_index,
                    chart.shen_gong_index,
                    ZhouYi::Mapper::to_zh(chart.wu_xing_ju),
                    zhi_zh(chart.year_pillar.zhi)),
        table);
  check(chart.pai_pan_lunar_month == 6, "排盘农历月", "6",
        std::to_string(chart.pai_pan_lunar_month), table);

  // 2. 参数校验：虚岁与农历月非法时必须抛 invalid_argument。
  bool age_rejected = false;
  bool month_rejected_low = false;
  bool month_rejected_high = false;
  try {
    static_cast<void>(chart.get_horoscope(target_year, target_month, target_day,
                                          target_hour, 0));
  } catch (const invalid_argument &) {
    age_rejected = true;
  }
  try {
    static_cast<void>(chart.get_horoscope(target_year, 0, target_day,
                                          target_hour, current_age));
  } catch (const invalid_argument &) {
    month_rejected_low = true;
  }
  try {
    static_cast<void>(chart.get_horoscope(target_year, 13, target_day,
                                          target_hour, current_age));
  } catch (const invalid_argument &) {
    month_rejected_high = true;
  }
  check(age_rejected && month_rejected_low && month_rejected_high,
        "非法入参拒绝", "虚岁0、月0、月13 均 invalid_argument",
        age_rejected && month_rejected_low && month_rejected_high
            ? "均 invalid_argument"
            : "存在未拒绝的非法入参",
        table);

  // 3. 流年干支与流年宫：2026 丙午，午宫为寅起第 4 宫。
  check(horoscope.liu_nian.year == target_year &&
            horoscope.liu_nian.tian_gan == TianGan::Bing &&
            horoscope.liu_nian.di_zhi == DiZhi::Wu,
        "流年干支", "2026 丙午",
        fmt::format(
            "{} {}{}", horoscope.liu_nian.year,
            string(ZhouYi::GanZhi::Mapper::to_zh(horoscope.liu_nian.tian_gan)),
            zhi_zh(horoscope.liu_nian.di_zhi)),
        table);
  check(horoscope.liu_nian.gong_index == 4 &&
            chart.palaces[4].gong_data.di_zhi == DiZhi::Wu,
        "流年宫", "第4宫（午宫）",
        fmt::format(
            "第{}宫 宫支{}", horoscope.liu_nian.gong_index,
            zhi_zh(
                chart.palaces[horoscope.liu_nian.gong_index].gong_data.di_zhi)),
        table);

  // 4. 大限：虚岁 27 落第 3 限（宫 11，丑宫，25-34 岁）。
  check(
      horoscope.da_xian.gong_index == 11 && horoscope.da_xian.start_age == 25 &&
          horoscope.da_xian.end_age == 34 &&
          chart.palaces[11].da_xian_start == 25,
      "大限宫与年龄区间", "宫11（丑宫）25-34岁",
      fmt::format(
          "宫{}（{}宫）{}-{}岁", horoscope.da_xian.gong_index,
          zhi_zh(chart.palaces[horoscope.da_xian.gong_index].gong_data.di_zhi),
          horoscope.da_xian.start_age, horoscope.da_xian.end_age),
      table);

  // 5. 十二大限干支：基准为五虎遁“乙庚之年戊寅头”的庚辰年宫干支，
  //    并与命盘 palaces 的同宫干支逐宫交叉核对。
  const array<TianGan, 12> expected_gong_gan = {
      TianGan::Wu,   TianGan::Ji,   TianGan::Geng, TianGan::Xin,
      TianGan::Ren,  TianGan::Gui,  TianGan::Jia,  TianGan::Yi,
      TianGan::Bing, TianGan::Ding, TianGan::Wu,   TianGan::Ji};
  const array<DiZhi, 12> expected_gong_zhi = {
      DiZhi::Yin,  DiZhi::Mao, DiZhi::Chen, DiZhi::Si,  DiZhi::Wu, DiZhi::Wei,
      DiZhi::Shen, DiZhi::You, DiZhi::Xu,   DiZhi::Hai, DiZhi::Zi, DiZhi::Chou};
  bool da_xian_gan_zhi_match = true;
  bool da_xian_baseline_match = true;
  string da_xian_gong_zhi_text;
  for (int index = 0; index < 12; ++index) {
    const DaXianData &da_xian = chart.da_xian_data[index];
    da_xian_gan_zhi_match =
        da_xian_gan_zhi_match &&
        da_xian.tian_gan == chart.palaces[index].gong_data.tian_gan &&
        da_xian.di_zhi == chart.palaces[index].gong_data.di_zhi;
    da_xian_baseline_match = da_xian_baseline_match &&
                             da_xian.tian_gan == expected_gong_gan[index] &&
                             da_xian.di_zhi == expected_gong_zhi[index];
    if (!da_xian_gong_zhi_text.empty())
      da_xian_gong_zhi_text += "、";
    da_xian_gong_zhi_text += fmt::format(
        "宫{}{}{}", index, gan_zh(da_xian.tian_gan), zhi_zh(da_xian.di_zhi));
  }
  check(da_xian_baseline_match, "十二大限干支（五虎遁基准）",
        "宫0戊寅…宫11己丑", da_xian_gong_zhi_text, table);
  check(da_xian_gan_zhi_match, "大限宫干支与命盘同宫一致",
        "da_xian_data[i] 与 palaces[i].gong_data 逐宫相等",
        da_xian_gan_zhi_match ? "十二宫全部相等" : "存在不一致的宫", table);
  check(horoscope.da_xian.tian_gan == TianGan::Ji &&
            horoscope.da_xian.di_zhi == DiZhi::Chou,
        "当前大限干支", "己丑",
        fmt::format("{}{}", gan_zh(horoscope.da_xian.tian_gan),
                    zhi_zh(horoscope.da_xian.di_zhi)),
        table);

  // 6. 大限流曜随大限干支外布：己年禄存在午（宫4），擎羊在禄前未（宫5），
  //    陀罗在禄后巳（宫3）。
  check(palace_of_star(horoscope.da_xian_stars, "运禄") == 4 &&
            palace_of_star(horoscope.da_xian_stars, "运羊") == 5 &&
            palace_of_star(horoscope.da_xian_stars, "运陀") == 3,
        "大限流曜落宫（己丑限）", "运禄宫4、运羊宫5、运陀宫3",
        fmt::format("运禄宫{}、运羊宫{}、运陀宫{}",
                    palace_of_star(horoscope.da_xian_stars, "运禄"),
                    palace_of_star(horoscope.da_xian_stars, "运羊"),
                    palace_of_star(horoscope.da_xian_stars, "运陀")),
        table);

  // 7. 小限：三合局起宫口径。庚辰属申子辰，戌宫（宫 8）起 1 岁，男命顺行。
  const array<int, 12> expected_xiao_xian_ages_1_to_12 = {8, 9, 10, 11, 0, 1,
                                                          2, 3, 4,  5,  6, 7};
  bool xiao_xian_age_table_match = true;
  string xiao_xian_age_text;
  for (int age = 1; age <= 12; ++age) {
    const int gong = get_xiao_xian(age, true, chart.year_pillar.zhi).gong_index;
    xiao_xian_age_table_match =
        xiao_xian_age_table_match &&
        gong == expected_xiao_xian_ages_1_to_12[age - 1];
    if (age > 1)
      xiao_xian_age_text += "、";
    xiao_xian_age_text += fmt::format("{}岁宫{}", age, gong);
  }
  check(xiao_xian_age_table_match, "小限起宫（申子辰戌宫起1岁，男顺行）",
        "1-12岁落宫 8,9,10,11,0,1,2,3,4,5,6,7", xiao_xian_age_text, table);
  check(horoscope.xiao_xian.age == current_age &&
            horoscope.xiao_xian.gong_index == 10,
        "27岁小限宫", "宫10（子宫）",
        fmt::format("宫{}（{}宫）", horoscope.xiao_xian.gong_index,
                    zhi_zh(chart.palaces[horoscope.xiao_xian.gong_index]
                               .gong_data.di_zhi)),
        table);

  // 8. 小限交叉一致：命盘记录的每个小限虚岁都必须落在同一个宫。
  bool xiao_xian_cross_match = true;
  int xiao_xian_pairs = 0;
  for (int index = 0; index < 12; ++index) {
    for (const int age : chart.palaces[index].xiao_xian_ages) {
      ++xiao_xian_pairs;
      xiao_xian_cross_match =
          xiao_xian_cross_match &&
          get_xiao_xian(age, chart.is_male, chart.year_pillar.zhi).gong_index ==
              index;
    }
  }
  check(xiao_xian_cross_match, "小限与 palaces[i].xiao_xian_ages 交叉一致",
        "命盘记录的 60 组（宫,虚岁）全部同宫",
        fmt::format("核验 {} 组，{}", xiao_xian_pairs,
                    xiao_xian_cross_match ? "全部同宫" : "存在不同宫"),
        table);
  check(palace_of_age(chart, current_age) == 10 &&
            get_xiao_xian(current_age, chart.is_male, chart.year_pillar.zhi)
                    .gong_index == 10,
        "27岁小限反查命盘宫位", "宫10",
        fmt::format("命盘记录宫{}", palace_of_age(chart, current_age)), table);

  // 9. 四宫起例穷举：三合局四组生年支的起宫各按口诀断言。
  check(get_xiao_xian_start_gong(DiZhi::Yin) == 2 &&
            get_xiao_xian_start_gong(DiZhi::Wu) == 2 &&
            get_xiao_xian_start_gong(DiZhi::Xu) == 2 &&
            get_xiao_xian_start_gong(DiZhi::Shen) == 8 &&
            get_xiao_xian_start_gong(DiZhi::Zi) == 8 &&
            get_xiao_xian_start_gong(DiZhi::Chen) == 8 &&
            get_xiao_xian_start_gong(DiZhi::Hai) == 11 &&
            get_xiao_xian_start_gong(DiZhi::Mao) == 11 &&
            get_xiao_xian_start_gong(DiZhi::Wei) == 11 &&
            get_xiao_xian_start_gong(DiZhi::Si) == 5 &&
            get_xiao_xian_start_gong(DiZhi::You) == 5 &&
            get_xiao_xian_start_gong(DiZhi::Chou) == 5,
        "小限四组起宫", "寅午戌→宫2、申子辰→宫8、亥卯未→宫11、巳酉丑→宫5",
        fmt::format("寅{}、申{}、亥{}、巳{}",
                    get_xiao_xian_start_gong(DiZhi::Yin),
                    get_xiao_xian_start_gong(DiZhi::Shen),
                    get_xiao_xian_start_gong(DiZhi::Hai),
                    get_xiao_xian_start_gong(DiZhi::Si)),
        table);

  // 10. 女命逆行：同一生年支起宫不变，方向相反。
  {
    const array<int, 12> expected_female = {8, 7, 6, 5,  4,  3,
                                            2, 1, 0, 11, 10, 9};
    bool female_match = true;
    for (int age = 1; age <= 12; ++age)
      female_match =
          female_match &&
          get_xiao_xian(age, false, chart.year_pillar.zhi).gong_index ==
              expected_female[age - 1];
    check(female_match, "小限女命逆行", "1-12岁落宫 8,7,6,5,4,3,2,1,0,11,10,9",
          female_match ? "十二岁全部一致" : "存在不一致", table);
  }

  // 11. 流月、流日、流时宫位：起点分别为流年宫、流月宫、流日宫。
  check(horoscope.liu_yue.gong_index == 1 && horoscope.liu_yue.month == 3,
        "流月宫", "第1宫（卯宫）",
        fmt::format("第{}宫 月{}", horoscope.liu_yue.gong_index,
                    horoscope.liu_yue.month),
        table);
  check(horoscope.liu_ri.gong_index == 5 && horoscope.liu_ri.day == 5, "流日宫",
        "第5宫（未宫）",
        fmt::format("第{}宫 日{}", horoscope.liu_ri.gong_index,
                    horoscope.liu_ri.day),
        table);
  check(horoscope.liu_shi.gong_index == 11 &&
            horoscope.liu_shi.shi_chen == DiZhi::Wu,
        "流时宫", "第11宫（丑宫）",
        fmt::format("第{}宫 {}", horoscope.liu_shi.gong_index,
                    zhi_zh(horoscope.liu_shi.shi_chen)),
        table);

  // 12. 目标流年口径的岁前、将前：午年岁建在午、将星在午，各顺布十二宫。
  const array<SuiQian12, 12> expected_sui_qian = {
      SuiQian12::BaiHu,   SuiQian12::TianDe2, SuiQian12::DiaoKe,
      SuiQian12::BingFu,  SuiQian12::SuiJian, SuiQian12::HuiQi,
      SuiQian12::SangMen, SuiQian12::GuanSuo, SuiQian12::GuanFu,
      SuiQian12::XiaoHao, SuiQian12::DaHao,   SuiQian12::LongDe,
  };
  const array<JiangQian12, 12> expected_jiang_qian = {
      JiangQian12::ZhiBei,   JiangQian12::XianChi2,  JiangQian12::YueSha,
      JiangQian12::WangShen, JiangQian12::JiangXing, JiangQian12::PanAn,
      JiangQian12::SuiYi,    JiangQian12::XiShen,    JiangQian12::HuaGai2,
      JiangQian12::JieSha,   JiangQian12::ZaiSha,    JiangQian12::TianSha,
  };
  array<SuiQian12, 12> actual_sui_qian{};
  array<JiangQian12, 12> actual_jiang_qian{};
  bool sui_qian_match = true;
  bool jiang_qian_match = true;
  bool tags_index_match = true;
  bool da_xian_mark_match = true;
  bool xiao_xian_mark_match = true;
  bool liu_nian_mark_match = true;
  int da_xian_marks = 0;
  int xiao_xian_marks = 0;
  int liu_nian_marks = 0;
  for (int index = 0; index < 12; ++index) {
    const PalaceHoroscopeTag &tag = horoscope.palace_tags[index];
    actual_sui_qian[index] = tag.sui_qian;
    actual_jiang_qian[index] = tag.jiang_qian;
    sui_qian_match = sui_qian_match && tag.sui_qian == expected_sui_qian[index];
    jiang_qian_match =
        jiang_qian_match && tag.jiang_qian == expected_jiang_qian[index];
    tags_index_match = tags_index_match && tag.gong_index == index;
    da_xian_marks += tag.is_da_xian ? 1 : 0;
    xiao_xian_marks += tag.is_xiao_xian ? 1 : 0;
    liu_nian_marks += tag.is_liu_nian ? 1 : 0;
    da_xian_mark_match = da_xian_mark_match && tag.is_da_xian == (index == 11);
    xiao_xian_mark_match =
        xiao_xian_mark_match && tag.is_xiao_xian == (index == 10);
    liu_nian_mark_match =
        liu_nian_mark_match && tag.is_liu_nian == (index == 4);
  }
  string sui_qian_actual;
  string jiang_qian_actual;
  for (int index = 0; index < 12; ++index) {
    sui_qian_actual +=
        fmt::format("宫{}{}{}", index, sui_zh(actual_sui_qian[index]),
                    index == 11 ? "" : "、");
    jiang_qian_actual +=
        fmt::format("宫{}{}{}", index, jiang_zh(actual_jiang_qian[index]),
                    index == 11 ? "" : "、");
  }
  check(sui_qian_match, "流年口径岁前十二神", "午宫岁建顺布", sui_qian_actual,
        table);
  check(jiang_qian_match, "流年口径将前十二神", "午宫将星顺布",
        jiang_qian_actual, table);
  check(covers_every_sui_qian(actual_sui_qian), "岁前十二神各出现一次",
        "十二神恰好各一次", "见上表", table);
  check(covers_every_jiang_qian(actual_jiang_qian), "将前十二神各出现一次",
        "十二神恰好各一次", "见上表", table);

  // 13. 与生年口径区分：本命盘岁建在辰宫、将星在子宫，流年盘岁建／将星在午宫。
  check(chart.palaces[2].sui_qian == SuiQian12::SuiJian &&
            chart.palaces[10].jiang_qian == JiangQian12::JiangXing &&
            actual_sui_qian[4] == SuiQian12::SuiJian &&
            actual_jiang_qian[4] == JiangQian12::JiangXing,
        "生年口径与流年口径分离",
        "本命辰宫岁建、子宫将星；流年午宫岁建、午宫将星",
        fmt::format("本命宫2={}、宫10={}；流年宫4={}／{}",
                    sui_zh(*chart.palaces[2].sui_qian),
                    jiang_zh(*chart.palaces[10].jiang_qian),
                    sui_zh(actual_sui_qian[4]), jiang_zh(actual_jiang_qian[4])),
        table);

  // 14. 十二宫角标：宫序覆盖 0-11，大限／小限／流年各只有一个宫。
  check(tags_index_match, "十二宫角标索引", "宫0-宫11 各一次", "逐宫核对",
        table);
  check(da_xian_marks == 1 && xiao_xian_marks == 1 && liu_nian_marks == 1,
        "角标数量", "大限1、小限1、流年1",
        fmt::format("大限{}、小限{}、流年{}", da_xian_marks, xiao_xian_marks,
                    liu_nian_marks),
        table);
  check(da_xian_mark_match && xiao_xian_mark_match && liu_nian_mark_match,
        "角标落宫", "大限宫11、小限宫10、流年宫4",
        fmt::format(
            "大限宫{}、小限宫{}、流年宫{}", horoscope.da_xian.gong_index,
            horoscope.xiao_xian.gong_index, horoscope.liu_nian.gong_index),
        table);

  // 15. 十二宫各出现一次：大限、小限、流年三条轴分别穷举一遍。
  vector<int> da_xian_palaces;
  vector<int> da_xian_starts;
  for (const DaXianData &da_xian : chart.da_xian_data) {
    da_xian_palaces.push_back(da_xian.gong_index);
    da_xian_starts.push_back(da_xian.start_age);
  }
  ranges::sort(da_xian_starts);
  bool da_xian_ages_ok = true;
  for (int index = 0; index < 12; ++index)
    da_xian_ages_ok =
        da_xian_ages_ok && da_xian_starts[index] == 5 + 10 * index;
  check(covers_every_palace(da_xian_palaces) && da_xian_ages_ok,
        "十二大限各落一宫", "宫0-宫11 各一次、5岁起每限10年",
        fmt::format(
            "起限年龄 {}-{}，宫序去重后 {} 个", da_xian_starts.front(),
            da_xian_starts.back(),
            set<int>(da_xian_palaces.begin(), da_xian_palaces.end()).size()),
        table);

  vector<int> xiao_xian_palaces;
  for (int age = 7; age <= 18; ++age)
    xiao_xian_palaces.push_back(
        get_xiao_xian(age, chart.is_male, chart.year_pillar.zhi).gong_index);
  check(
      covers_every_palace(xiao_xian_palaces), "小限十二宫轮转",
      "7-18 岁宫0-宫11 各一次",
      fmt::format(
          "宫序去重后 {} 个",
          set<int>(xiao_xian_palaces.begin(), xiao_xian_palaces.end()).size()),
      table);

  vector<int> liu_nian_palaces;
  for (int year = 2026; year <= 2037; ++year)
    liu_nian_palaces.push_back(chart
                                   .get_horoscope(year, target_month,
                                                  target_day, target_hour,
                                                  current_age)
                                   .liu_nian.gong_index);
  check(covers_every_palace(liu_nian_palaces), "流年十二宫轮转",
        "2026-2037 年宫0-宫11 各一次",
        fmt::format(
            "宫序去重后 {} 个",
            set<int>(liu_nian_palaces.begin(), liu_nian_palaces.end()).size()),
        table);

  // 16. 四化：丙年（2026 丙午）天同禄、天机权、文昌科、廉贞忌，顺序即禄权科忌。
  const vector<SiHuaEntry> expected_bing = {{SiHuaXing::TianTong, SiHua::Lu},
                                            {SiHuaXing::TianJi, SiHua::Quan},
                                            {SiHuaXing::WenChang, SiHua::Ke},
                                            {SiHuaXing::LianZhen, SiHua::Ji}};
  check(horoscope.liu_nian.si_hua_entries == expected_bing &&
            horoscope.liu_nian.si_hua ==
                array<string, 4>{"天同", "天机", "文昌", "廉贞"},
        "丙年流年四化", "化禄天同、化权天机、化科文昌、化忌廉贞",
        si_hua_text(horoscope.liu_nian.si_hua_entries), table);
  check(hua_order_is_lu_quan_ke_ji(horoscope.liu_nian.si_hua_entries),
        "流年四化次序", "禄→权→科→忌",
        hua_order_is_lu_quan_ke_ji(horoscope.liu_nian.si_hua_entries)
            ? "禄→权→科→忌"
            : "次序不符",
        table);

  // 17. 辛年（2021 辛丑）巨门禄、太阳权、文曲科、文昌忌：辅曜四化必须装得下。
  const HoroscopeResult xin_horoscope = chart.get_horoscope(
      2021, target_month, target_day, target_hour, current_age);
  const vector<SiHuaEntry> expected_xin = {{SiHuaXing::JuMen, SiHua::Lu},
                                           {SiHuaXing::TaiYang, SiHua::Quan},
                                           {SiHuaXing::WenQu, SiHua::Ke},
                                           {SiHuaXing::WenChang, SiHua::Ji}};
  check(xin_horoscope.liu_nian.di_zhi == DiZhi::Chou &&
            xin_horoscope.liu_nian.si_hua_entries == expected_xin,
        "辛年流年四化（辅曜）", "化禄巨门、化权太阳、化科文曲、化忌文昌",
        fmt::format("{}{} {}", gan_zh(xin_horoscope.liu_nian.tian_gan),
                    zhi_zh(xin_horoscope.liu_nian.di_zhi),
                    si_hua_text(xin_horoscope.liu_nian.si_hua_entries)),
        table);
  check(get_si_hua_entries(TianGan::Xin) == expected_xin &&
            get_si_hua_entries(TianGan::Bing) == expected_bing,
        "四化表逐项", "辛、丙两年四星与化象逐项一致",
        fmt::format("辛：{}；丙：{}",
                    si_hua_text(get_si_hua_entries(TianGan::Xin)),
                    si_hua_text(get_si_hua_entries(TianGan::Bing))),
        table);

  // 18. 大限四化：己丑限武曲禄、贪狼权、天梁科、文曲忌（文曲为辅曜）。
  const vector<SiHuaEntry> expected_ji = {{SiHuaXing::WuQu, SiHua::Lu},
                                          {SiHuaXing::TanLang, SiHua::Quan},
                                          {SiHuaXing::TianLiang, SiHua::Ke},
                                          {SiHuaXing::WenQu, SiHua::Ji}};
  check(horoscope.da_xian.si_hua_entries == expected_ji &&
            horoscope.da_xian.si_hua ==
                array<string, 4>{"武曲", "贪狼", "天梁", "文曲"},
        "大限四化", "化禄武曲、化权贪狼、化科天梁、化忌文曲",
        si_hua_text(horoscope.da_xian.si_hua_entries), table);

  // 19. 流月四化：丙午年农历三月为壬辰月，天梁禄、紫微权、左辅科、武曲忌。
  check(horoscope.liu_yue.di_zhi == DiZhi::Chen &&
            horoscope.liu_yue.si_hua ==
                array<string, 4>{"天梁", "紫微", "左辅", "武曲"},
        "流月四化", "壬辰月：化禄天梁、化权紫微、化科左辅、化忌武曲",
        fmt::format("{}{} {}", gan_zh(horoscope.liu_yue.tian_gan),
                    zhi_zh(horoscope.liu_yue.di_zhi),
                    si_hua_text(horoscope.liu_yue.si_hua_entries)),
        table);

  // 20. 五组 four 化同源：星名序列与明细逐项一致，且都按禄权科忌排列。
  check(hua_order_is_lu_quan_ke_ji(horoscope.da_xian.si_hua_entries) &&
            hua_order_is_lu_quan_ke_ji(horoscope.liu_nian.si_hua_entries) &&
            hua_order_is_lu_quan_ke_ji(horoscope.liu_yue.si_hua_entries) &&
            hua_order_is_lu_quan_ke_ji(horoscope.liu_ri.si_hua_entries) &&
            hua_order_is_lu_quan_ke_ji(horoscope.liu_shi.si_hua_entries),
        "五组运限四化次序", "大限／流年／流月／流日／流时均为禄权科忌",
        "逐组核对", table);
  check(si_hua_names_match_entries(horoscope.da_xian.si_hua,
                                   horoscope.da_xian.si_hua_entries) &&
            si_hua_names_match_entries(horoscope.liu_nian.si_hua,
                                       horoscope.liu_nian.si_hua_entries) &&
            si_hua_names_match_entries(horoscope.liu_yue.si_hua,
                                       horoscope.liu_yue.si_hua_entries) &&
            si_hua_names_match_entries(horoscope.liu_ri.si_hua,
                                       horoscope.liu_ri.si_hua_entries) &&
            si_hua_names_match_entries(horoscope.liu_shi.si_hua,
                                       horoscope.liu_shi.si_hua_entries),
        "四化星名与明细同源", "si_hua[i] 等于 si_hua_entries[i] 的星名",
        "逐组核对", table);

  // 21. 兼容：既有 get_si_hua_table 仍只装十四主星，条目与旧口径一致。
  const auto geng_table = get_si_hua_table(TianGan::Geng);
  const auto xin_table = get_si_hua_table(TianGan::Xin);
  check(
      geng_table.size() == 4 && geng_table.at(ZhuXing::TaiYang) == SiHua::Lu &&
          geng_table.at(ZhuXing::WuQu) == SiHua::Quan &&
          geng_table.at(ZhuXing::TaiYin) == SiHua::Ke &&
          geng_table.at(ZhuXing::TianTong) == SiHua::Ji &&
          xin_table.size() == 2 && xin_table.at(ZhuXing::JuMen) == SiHua::Lu &&
          xin_table.at(ZhuXing::TaiYang) == SiHua::Quan,
      "get_si_hua_table 兼容", "庚年四主星、辛年两主星（辅曜不入门）",
      fmt::format("庚年{}条、辛年{}条", geng_table.size(), xin_table.size()),
      table);

  // 22. 运限流曜：名称集合与落宫索引契约。
  const array<string_view, 10> da_xian_names = {"运魁", "运钺", "运昌", "运曲",
                                                "运禄", "运羊", "运陀", "运马",
                                                "运鸾", "运喜"};
  const array<string_view, 11> liu_nian_names = {"流魁", "流钺", "流昌", "流曲",
                                                 "流禄", "流羊", "流陀", "流马",
                                                 "流鸾", "流喜", "年解"};
  const array<string_view, 10> liu_yue_names = {"月魁", "月钺", "月昌", "月曲",
                                                "月禄", "月羊", "月陀", "月马",
                                                "月鸾", "月喜"};
  const array<string_view, 10> liu_ri_names = {"日魁", "日钺", "日昌", "日曲",
                                               "日禄", "日羊", "日陀", "日马",
                                               "日鸾", "日喜"};
  const array<string_view, 10> liu_shi_names = {"时魁", "时钺", "时昌", "时曲",
                                                "时禄", "时羊", "时陀", "时马",
                                                "时鸾", "时喜"};

  check(star_names(horoscope.da_xian_stars) == sorted_names(da_xian_names),
        "大限流曜名称与数量", "运魁钺昌曲禄羊陀马鸾喜 共10",
        fmt::format("{} 个星名", star_names(horoscope.da_xian_stars).size()),
        table);
  check(star_names(horoscope.liu_nian_stars) == sorted_names(liu_nian_names),
        "流年流曜名称与数量", "流魁钺昌曲禄羊陀马鸾喜＋年解 共11",
        fmt::format("{} 个星名", star_names(horoscope.liu_nian_stars).size()),
        table);
  check(star_names(horoscope.liu_yue_stars) == sorted_names(liu_yue_names),
        "流月流曜名称与数量", "月魁钺昌曲禄羊陀马鸾喜 共10",
        fmt::format("{} 个星名", star_names(horoscope.liu_yue_stars).size()),
        table);
  check(star_names(horoscope.liu_ri_stars) == sorted_names(liu_ri_names),
        "流日流曜名称与数量", "日魁钺昌曲禄羊陀马鸾喜 共10",
        fmt::format("{} 个星名", star_names(horoscope.liu_ri_stars).size()),
        table);
  check(star_names(horoscope.liu_shi_stars) == sorted_names(liu_shi_names),
        "流时流曜名称与数量", "时魁钺昌曲禄羊陀马鸾喜 共10",
        fmt::format("{} 个星名", star_names(horoscope.liu_shi_stars).size()),
        table);
  check(covers_every_palace(horoscope.da_xian_stars) &&
            covers_every_palace(horoscope.liu_nian_stars) &&
            covers_every_palace(horoscope.liu_yue_stars) &&
            covers_every_palace(horoscope.liu_ri_stars) &&
            covers_every_palace(horoscope.liu_shi_stars),
        "五组流曜宫序", "各组宫0-宫11 各一次", "逐组核对", table);

  std::filesystem::create_directories("docs/ziwei");
  std::ofstream report("docs/ziwei/example_zi_wei_horoscope_regression.txt");
  if (!report)
    throw runtime_error("无法创建紫微斗数运限回归报告");

  report << "命例：农历2000年六月十五日16时，申时，男命（公历2000-07-16）\n";
  report << "目标运限：农历2026年三月初五午时，虚岁27\n";
  report << "时间口径：未提供出生地，不校正真太阳时\n\n";
  report << "【运限契约核验】\n";
  report << table.str();
  report << "\n【十二宫大限干支（与命盘同宫一致）】\n";
  report << "| 宫序 | 宫支 | 宫干支 | 大限年龄 | 大限四化（禄权科忌） |\n";
  report << "| --- | --- | --- | --- | --- |\n";
  for (int index = 0; index < 12; ++index) {
    const DaXianData &da_xian = chart.da_xian_data[index];
    report << "| " << index << " | " << zhi_zh(palace_zhi(index)) << " | "
           << gan_zh(da_xian.tian_gan) << zhi_zh(da_xian.di_zhi) << " | "
           << da_xian.start_age << "-" << da_xian.end_age << " | "
           << si_hua_text(da_xian.si_hua_entries) << " |\n";
  }
  report << "\n【小限逐岁落宫（三合局起宫，男顺女逆）】\n";
  report << "| 虚岁 | 男命宫 | 女命宫 | 命盘记录宫 |\n";
  report << "| --- | --- | --- | --- |\n";
  for (int age = 1; age <= 18; ++age) {
    report << "| " << age << " | "
           << get_xiao_xian(age, true, chart.year_pillar.zhi).gong_index
           << " | "
           << get_xiao_xian(age, false, chart.year_pillar.zhi).gong_index
           << " | " << palace_of_age(chart, age) << " |\n";
  }
  report << "\n【get_horoscope().to_string()】\n";
  report << horoscope.to_string();
  report << "\n【命盘运势口径（本命盘岁前／将前，生年支起例）】\n";
  report
      << "| 宫序 | 宫支 | 本命岁前 | 本命将前 | 流年岁前 | 流年将前 | 大限 | "
         "小限 | 流年 |\n";
  report << "| --- | --- | --- | --- | --- | --- | --- | --- | --- |\n";
  for (int index = 0; index < 12; ++index) {
    const PalaceHoroscopeTag &tag = horoscope.palace_tags[index];
    report << "| " << index << " | " << zhi_zh(palace_zhi(index)) << " | "
           << (chart.palaces[index].sui_qian
                   ? sui_zh(*chart.palaces[index].sui_qian)
                   : "未提供")
           << " | "
           << (chart.palaces[index].jiang_qian
                   ? jiang_zh(*chart.palaces[index].jiang_qian)
                   : "未提供")
           << " | " << sui_zh(tag.sui_qian) << " | " << jiang_zh(tag.jiang_qian)
           << " | " << (tag.is_da_xian ? "是" : "否") << " | "
           << (tag.is_xiao_xian ? "是" : "否") << " | "
           << (tag.is_liu_nian ? "是" : "否") << " |\n";
  }
  report.close();

  output << "\n【运限契约核验】\n" << table.str();
  output << "\n【get_horoscope().to_string()】\n";
  output << horoscope.to_string() << '\n';

  fmt::print("断言 {}/{} 通过，报告已写入 "
             "docs/ziwei/example_zi_wei_horoscope_regression.txt\n\n",
             checks_run - failures, checks_run);
  if (failures != 0) {
    fmt::print("紫微斗数运限回归失败：{} 项不一致\n", failures);
    return 1;
  }
  fmt::print("紫微斗数运限回归通过！\n\n");
  return 0;
}
