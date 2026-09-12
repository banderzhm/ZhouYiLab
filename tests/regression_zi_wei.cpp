// 紫微斗数系统示例
import ZhouYi.ZiWei.Controller;
import ZhouYi.ZiWei.Constants;
import ZhouYi.ZiWei.Brightness;
import ZhouYi.ZiWei.GeJu;
import ZhouYi.ZiWei;
import ZhouYi.ZiWei.Star;
import ZhouYi.GanZhi;
import ZhouYi.ZhMapper;
import nlohmann.json;
import fmt;
import std;

using namespace ZhouYi::ZiWei;
using namespace ZhouYi::GanZhi;
using namespace std;
using nlohmann::json;

namespace {

struct ParityCheck {
  string item;
  bool passed;
  string expected;
  string actual;
};

struct GeneratedCase {
  int year;
  int month;
  int day;
  int hour_branch_index;
  int representative_hour;
  bool is_male;
};

struct SiteStarExpectation {
  int palace_index;
  string_view palace_name;
  string_view star_name;
  string_view brightness;
  string_view si_hua;
};

struct SitePalaceExpectation {
  string_view branch;
  string_view stem_branch;
  string_view palace_name;
  string_view main_stars;
};

struct MetisCoreFixture {
  int year;
  int month;
  int day;
  int hour;
  bool is_male;
  int lunar_year;
  int lunar_month;
  int lunar_day;
  string_view four_pillars;
  DiZhi ming_gong;
  DiZhi shen_gong;
  WuXingJu wu_xing_ju;
  string_view ming_zhu;
  string_view shen_zhu;
  uint64_t rated_star_hash;
  size_t rated_star_count;
  std::array<string_view, 12> major_stars_by_branch;
};

/** 参考实现格局语料中的一张公历命盘。 */
struct ReferencePatternFixture {
  /** 公历年。 */
  int year;
  /** 公历月。 */
  int month;
  /** 公历日。 */
  int day;
  /** 时辰序号：子时为零，依次至亥时。 */
  int hour_branch;
  /** 是否男命。 */
  bool is_male;
  /** 参考实现检出的全部格局名称。 */
  vector<string_view> patterns;
};

struct StarComparisonSummary {
  size_t total = 0;
  size_t palace_matches = 0;
  size_t exact_brightness_matches = 0;
  size_t brightness_band_matches = 0;
  size_t transformation_matches = 0;
};

string si_hua_short_name(const optional<SiHua> &si_hua) {
  if (!si_hua)
    return {};
  switch (*si_hua) {
  case SiHua::Lu:
    return "禄";
  case SiHua::Quan:
    return "权";
  case SiHua::Ke:
    return "科";
  case SiHua::Ji:
    return "忌";
  default:
    return {};
  }
}

string major_star_signature(const ZiWeiResult &result, int branch_index) {
  const int palace_index = (branch_index + 10) % 12;
  string signature;
  for (const auto &star : result.palaces[palace_index].zhu_xing) {
    if (!signature.empty())
      signature += ',';
    signature += star.name + ':';
    signature += star.liang_du ? string(ZhouYi::Mapper::to_zh(*star.liang_du))
                               : "未定义";
    signature += ':' + si_hua_short_name(star.si_hua);
  }
  return signature;
}

tuple<uint64_t, size_t, string>
rated_star_fingerprint(const ZiWeiResult &result) {
  vector<string> entries;
  for (const auto &palace : result.palaces) {
    const int branch_index = static_cast<int>(palace.gong_data.di_zhi);
    const array<const vector<StarData> *, 4> groups = {
        &palace.zhu_xing, &palace.fu_xing, &palace.sha_xing, &palace.za_yao};
    for (const auto *group : groups) {
      for (const auto &star : *group) {
        if (!star.liang_du)
          continue;
        entries.push_back(to_string(branch_index) + ':' + star.name + ':' +
                          string(ZhouYi::Mapper::to_zh(*star.liang_du)) + ':' +
                          si_hua_short_name(star.si_hua));
      }
    }
  }
  ranges::sort(entries);
  string signature;
  for (const auto &entry : entries) {
    if (!signature.empty())
      signature += '|';
    signature += entry;
  }
  uint64_t hash = 14695981039346656037ULL;
  for (const unsigned char byte : signature) {
    hash ^= byte;
    hash *= 1099511628211ULL;
  }
  return {hash, entries.size(), std::move(signature)};
}

bool verify_metis_core_fixtures(ostream &output) {
  const array<MetisCoreFixture, 8> fixtures = {{
      {2000,
       7,
       16,
       16,
       true,
       2000,
       6,
       15,
       "庚辰/癸未/乙亥/甲申",
       DiZhi::Hai,
       DiZhi::Mao,
       WuXingJu::TuWuJu,
       "巨门",
       "文昌",
       9180143968327744687ULL,
       53,
       {"武曲:旺:权,天府:旺:", "太阳:陷:禄,太阴:庙:科", "贪狼:平:",
        "天机:旺:,巨门:旺:", "紫微:闲:,天相:旺:", "天梁:陷:", "七杀:旺:", "",
        "廉贞:庙:", "", "破军:旺:", "天同:庙:忌"}},
      {1991,
       2,
       21,
       22,
       false,
       1991,
       1,
       7,
       "辛未/庚寅/壬戌/辛亥",
       DiZhi::Mao,
       DiZhi::Chou,
       WuXingJu::MuSanJu,
       "文曲",
       "天相",
       2408938037821556698ULL,
       54,
       {"贪狼:旺:", "天同:陷:,巨门:陷:禄", "武曲:平:,天相:庙:",
        "太阳:庙:权,天梁:庙:", "七杀:庙:", "天机:平:", "紫微:庙:", "",
        "破军:陷:", "", "廉贞:旺:,天府:庙:", "太阴:庙:"}},
      {1984,
       4,
       5,
       1,
       true,
       1984,
       3,
       5,
       "甲子/戊辰/己巳/乙丑",
       DiZhi::Mao,
       DiZhi::Si,
       WuXingJu::HuoLiuJu,
       "文曲",
       "火星",
       13904563341074403818ULL,
       53,
       {"天机:庙:", "紫微:庙:,破军:庙:权", "",
        "天府:平:", "太阴:陷:", "廉贞:陷:禄,贪狼:陷:", "巨门:旺:", "天相:平:",
        "天同:旺:,天梁:陷:", "武曲:旺:科,七杀:平:", "太阳:陷:忌", ""}},
      {1996,
       9,
       12,
       6,
       false,
       1996,
       7,
       30,
       "丙子/丁酉/壬子/癸卯",
       DiZhi::Si,
       DiZhi::Hai,
       WuXingJu::ShuiErJu,
       "武曲",
       "火星",
       13910528866919355090ULL,
       52,
       {"武曲:旺:,天府:旺:", "太阳:陷:,太阴:庙:", "贪狼:平:",
        "天机:旺:权,巨门:旺:", "紫微:闲:,天相:旺:", "天梁:陷:", "七杀:旺:", "",
        "廉贞:庙:忌", "", "破军:旺:", "天同:庙:禄"}},
      {2008,
       8,
       8,
       12,
       true,
       2008,
       7,
       8,
       "戊子/庚申/庚辰/壬午",
       DiZhi::Yin,
       DiZhi::Yin,
       WuXingJu::ShuiErJu,
       "禄存",
       "火星",
       5899428861783514827ULL,
       53,
       {"天同:旺:,太阴:庙:权", "武曲:庙:,贪狼:庙:禄", "太阳:旺:,巨门:庙:",
        "天相:陷:", "天机:旺:忌,天梁:旺:", "紫微:旺:,七杀:平:", "", "", "",
        "廉贞:平:,破军:陷:", "", "天府:旺:"}},
      {2015,
       3,
       21,
       4,
       false,
       2015,
       2,
       2,
       "乙未/己卯/丙申/庚寅",
       DiZhi::Chou,
       DiZhi::Si,
       WuXingJu::HuoLiuJu,
       "巨门",
       "天相",
       7245915281564552097ULL,
       54,
       {"贪狼:旺:", "天同:陷:,巨门:陷:", "武曲:平:,天相:庙:",
        "太阳:庙:,天梁:庙:权", "七杀:庙:", "天机:平:禄", "紫微:庙:科", "",
        "破军:陷:", "", "廉贞:旺:,天府:庙:", "太阴:庙:忌"}},
      {2024,
       2,
       10,
       10,
       true,
       2024,
       1,
       1,
       "甲辰/丙寅/甲辰/己巳",
       DiZhi::You,
       DiZhi::Wei,
       WuXingJu::JinSiJu,
       "文曲",
       "文昌",
       8793821774397693020ULL,
       53,
       {"", "", "", "廉贞:闲:禄,破军:旺:权", "", "天府:旺:",
        "天同:陷:,太阴:陷:", "武曲:庙:科,贪狼:庙:", "太阳:平:忌,巨门:庙:",
        "天相:陷:", "天机:旺:,天梁:旺:", "紫微:旺:,七杀:平:"}},
      {1977,
       7,
       7,
       14,
       false,
       1977,
       5,
       21,
       "丁巳/丁未/乙丑/癸未",
       DiZhi::Hai,
       DiZhi::Chou,
       WuXingJu::JinSiJu,
       "巨门",
       "天机",
       11218106336228006352ULL,
       54,
       {"武曲:旺:,天府:旺:", "太阳:陷:,太阴:庙:禄",
        "贪狼:平:", "天机:旺:科,巨门:旺:忌", "紫微:闲:,天相:旺:", "天梁:陷:",
        "七杀:旺:", "", "廉贞:庙:", "", "破军:旺:", "天同:庙:权"}},
  }};

  const map<int, vector<string_view>> expected_patterns = {
      {2000, {"日月同宫", "天同化忌入命", "机月同梁三星会"}},
      {1991, {"阳梁昌禄", "昌曲同会"}},
      {1984, {"府相朝垣", "武曲七杀", "天同天梁格"}},
      {1996,
       {"日月同宫", "双禄朝垣", "禄存守命", "机月同梁三星会", "魁钺同会"}},
      {2008, {"巨日同宫", "巨火羊", "天马入命"}},
      {2015, {"辅弼同会"}},
      {2024, {"府相朝垣", "巨日同宫", "化科入身", "昌曲同会"}},
      {1977,
       {"日月同宫", "天马入命", "机月同梁三星会", "昌曲同会", "科权双会"}},
  };

  output << "Metis 当前混合亮度表多案例回归\n";
  output << "对照快照：2026-09-11；逐案取自网站本命盘当前默认流派配置。\n";
  output << "| 输入 | 性别 | 基础排盘 | 十二宫主星/亮度/四化 | "
            "全盘定级星曜 | 参考格局 | 结果 |\n";
  output << "| --- | --- | --- | --- | --- | --- | --- |\n";
  bool all_passed = true;
  for (const auto &fixture : fixtures) {
    const auto result = pai_pan_solar(fixture.year, fixture.month, fixture.day,
                                      fixture.hour, fixture.is_male);
    const string pillars = result.year_pillar.to_string() + '/' +
                           result.month_pillar.to_string() + '/' +
                           result.day_pillar.to_string() + '/' +
                           result.hour_pillar.to_string();
    const bool base_passed =
        result.lunar_day.get_year() == fixture.lunar_year &&
        result.lunar_day.get_month() == fixture.lunar_month &&
        result.lunar_day.get_day() == fixture.lunar_day &&
        pillars == fixture.four_pillars &&
        result.palaces[result.ming_gong_index].gong_data.di_zhi ==
            fixture.ming_gong &&
        result.palaces[result.shen_gong_index].gong_data.di_zhi ==
            fixture.shen_gong &&
        result.wu_xing_ju == fixture.wu_xing_ju &&
        result.ming_zhu_xing == fixture.ming_zhu &&
        result.shen_zhu_xing == fixture.shen_zhu;
    bool stars_passed = true;
    for (int branch_index = 0; branch_index < 12; ++branch_index)
      stars_passed =
          stars_passed && major_star_signature(result, branch_index) ==
                              fixture.major_stars_by_branch[branch_index];
    const auto [rated_hash, rated_count, rated_signature] =
        rated_star_fingerprint(result);
    const bool rated_stars_passed = rated_hash == fixture.rated_star_hash &&
                                    rated_count == fixture.rated_star_count;
    const string analysis_json = export_to_json_full(result);
    const json analysis = json::parse(analysis_json);
    const auto &patterns = expected_patterns.at(fixture.year);
    set<string> actual_patterns;
    for (const auto *group : {"ji_ge", "xiong_ge"})
      for (const auto &entry : analysis["ge_ju"][group])
        actual_patterns.insert(entry["name"].get<string>());
    const set<string> expected_set(patterns.begin(), patterns.end());
    const bool patterns_passed =
        ranges::all_of(expected_set, [&](const string &name) {
          return actual_patterns.contains(name);
        });
    const bool passed =
        base_passed && stars_passed && rated_stars_passed && patterns_passed;
    all_passed = all_passed && passed;
    output << "| " << fixture.year << '-' << fixture.month << '-' << fixture.day
           << ' ' << fixture.hour << "时 | " << (fixture.is_male ? "男" : "女")
           << " | " << (base_passed ? "一致" : "不一致") << " | "
           << (stars_passed ? "12/12一致" : "存在差异") << " | "
           << (rated_stars_passed
                   ? to_string(rated_count) + "/" +
                         to_string(fixture.rated_star_count) + "一致"
                   : "指纹不一致")
           << " | " << (patterns_passed ? "覆盖" : "缺失") << " | "
           << (passed ? "通过" : "失败") << " |\n";
    if (!rated_stars_passed)
      output << "\n实际定级指纹(" << fixture.year << ")：" << rated_hash
             << "，数量=" << rated_count << "\n"
             << rated_signature << "\n\n";
    if (!patterns_passed) {
      output << "\n缺失格局(" << fixture.year << ")：";
      for (const auto name : patterns) {
        if (!actual_patterns.contains(string(name)))
          output << name << ' ';
      }
      output << "\n\n";
    }
  }
  return all_passed;
}

/**
 * 运行跨年份、跨时辰、跨性别的格局语料回归。
 *
 * <p>语料由参考实现同一版本生成。除核对格局名称外，还校验正式输出契约：
 * 名称不得重复，且每个格局必须给出必要条件、等级、状态和规则来源。</p>
 */
bool verify_reference_pattern_corpus(ostream &output) {
  const vector<ReferencePatternFixture> fixtures = {
      {1980, 1, 15, 0, true, {"魁钺同会"}},
      {1981, 3, 7, 1, false, {"武曲七杀", "天同天梁格", "魁钺同会"}},
      {1982,
       5,
       19,
       2,
       true,
       {"日月同宫", "双禄朝垣", "化禄入财", "机月同梁三星会", "辅弼同会"}},
      {1983, 7, 23, 3, false, {"魁钺夹命"}},
      {1985,
       9,
       9,
       4,
       true,
       {"机月同梁", "巨日同宫", "天机化禄入命", "辅弼同会", "魁钺同会"}},
      {1986, 11, 27, 5, false, {"府相朝垣", "日月同宫", "廉杀羊", "马头带箭"}},
      {1987,
       2,
       13,
       6,
       true,
       {"机月同梁", "三奇加会", "太阴化禄入命", "化科入命", "科权双会"}},
      {1988,
       4,
       28,
       7,
       false,
       {"紫府同宫", "阳梁昌禄", "廉贞天相格", "机月同梁三星会"}},
      {1989, 6, 6, 8, true, {"机月同梁", "巨日同宫", "化科入命"}},
      {1990, 8, 18, 9, false, {"天同化忌入迁", "化禄入财"}},
      {1992,
       10,
       30,
       10,
       true,
       {"紫府同宫", "廉贞天相格", "昌曲夹命", "机月同梁三星会", "辅弼同会"}},
      {1993,
       12,
       12,
       11,
       false,
       {"杀破狼", "双禄朝垣", "贪狼化忌入命", "羊陀夹忌", "禄存守命",
        "化禄入财"}},
      {1994, 1, 26, 0, true, {"日月同宫", "化科入命"}},
      {1995, 3, 15, 1, false, {"火贪格", "杀破狼", "日月同宫", "化科入身"}},
      {1997, 5, 5, 2, true, {"府相朝垣", "禄存守身", "昌曲同会", "辅弼同会"}},
      {1998, 7, 17, 3, false, {"机月同梁", "巨日同宫", "天机化忌入命"}},
      {1999,
       9,
       29,
       4,
       true,
       {"府相朝垣", "武曲七杀", "天同天梁格", "天马入命"}},
      {2001,
       11,
       11,
       5,
       false,
       {"紫府同宫", "廉贞天相格", "双禄朝垣", "三奇加会", "巨门化禄入命",
        "空劫夹命", "天马在迁", "昌曲同会"}},
      {2002,
       2,
       22,
       6,
       true,
       {"紫府同宫", "府相朝垣", "武贪格", "廉贞天相格", "火铃夹命", "廉杀羊",
        "天马入命"}},
      {2003,
       4,
       4,
       7,
       false,
       {"紫府同宫", "廉贞天相格", "机月同梁三星会", "魁钺同会", "科权双会"}},
      {2004, 6, 16, 8, true, {"太阳化忌入迁", "机月同梁三星会"}},
      {2005,
       8,
       8,
       9,
       false,
       {"府相朝垣", "武曲七杀", "天同天梁格", "天马入命"}},
      {2006,
       10,
       20,
       10,
       true,
       {"日月同宫", "双禄朝垣", "机月同梁三星会", "辅弼同会"}},
      {2007,
       12,
       2,
       11,
       false,
       {"紫府同宫", "府相朝垣", "武贪格", "廉贞天相格"}},
  };

  output << "参考实现格局语料闭环回归\n";
  output << "覆盖：24张命盘、24个年份、十二时辰、男女各半。\n";
  output << "| 输入 | 参考格局数 | 命中数 | 输出契约 | 结果 |\n";
  output << "| --- | ---: | ---: | --- | --- |\n";

  bool all_passed = true;
  set<string> covered_pattern_names;
  for (const auto &fixture : fixtures) {
    const int hour = fixture.hour_branch == 0 ? 0 : fixture.hour_branch * 2;
    const auto result = pai_pan_solar(fixture.year, fixture.month, fixture.day,
                                      hour, fixture.is_male);
    const json analysis = json::parse(export_to_json_full(result));
    set<string> names;
    bool contract_passed = true;
    for (const auto *group : {"ji_ge", "xiong_ge"}) {
      for (const auto &entry : analysis["ge_ju"][group]) {
        const auto name = entry["name"].get<string>();
        contract_passed = contract_passed && names.insert(name).second &&
                          !entry["status"].get<string>().empty() &&
                          !entry["level"].get<string>().empty() &&
                          !entry["source"].get<string>().empty() &&
                          entry["geju_basis"]["required"].is_array() &&
                          !entry["geju_basis"]["required"].empty();
      }
    }
    size_t matched = 0;
    vector<string_view> missing;
    for (const auto expected : fixture.patterns) {
      covered_pattern_names.insert(string(expected));
      if (names.contains(string(expected)))
        ++matched;
      else
        missing.push_back(expected);
    }
    const bool passed = matched == fixture.patterns.size() && contract_passed;
    all_passed = all_passed && passed;
    output << "| " << fixture.year << '-' << fixture.month << '-' << fixture.day
           << ' ' << fixture.hour_branch << "时辰·"
           << (fixture.is_male ? "男" : "女") << " | "
           << fixture.patterns.size() << " | " << matched << " | "
           << (contract_passed ? "完整" : "不完整") << " | "
           << (passed ? "通过" : "失败") << " |\n";
    if (!missing.empty()) {
      output << "\n缺失：";
      for (const auto name : missing)
        output << name << ' ';
      output << "\n实际：";
      for (const auto &name : names)
        output << name << ' ';
      output << "\n\n";
    }
  }

  output << "\n语料共覆盖 " << covered_pattern_names.size()
         << " 类参考格局；每一项均经过正式 JSON 输出契约核验。\n";

  /** 创建以寅宫为命宫的最小规则命盘。 */
  const auto make_chart = [] {
    PatternChart chart;
    chart.set_ming_palace(0);
    chart.set_body_palace(0);
    for (int index = 0; index < 12; ++index) {
      chart.set_palace(index, static_cast<GongWei>(index),
                       static_cast<DiZhi>((index + 2) % 12), index == 0);
    }
    return chart;
  };
  const auto add = [](PatternChart &chart, int palace, string_view name,
                      PatternStarKind kind = PatternStarKind::Major,
                      optional<LiangDu> brightness = nullopt,
                      optional<SiHua> transformation = nullopt) {
    chart.add_star(palace, name, kind, brightness, transformation);
  };
  const auto find_pattern = [](PatternChart chart,
                               string_view name) -> optional<GeJuInfo> {
    const auto patterns = GeJuAnalyzer(std::move(chart)).analyze_all();
    const auto found = ranges::find(patterns, name, &GeJuInfo::name);
    return found == patterns.end() ? nullopt : optional{*found};
  };

  output << "\n规则级正反例闭环\n";
  output << "| 格局 | 正例 | 反例 | 减力/破格例 | 证据闭环 | 结果 |\n";
  output << "| --- | --- | --- | --- | --- | --- |\n";
  const auto write_closure = [&](string_view name, PatternChart positive,
                                 PatternChart negative,
                                 PatternChart weakened_or_broken) {
    const auto positive_result = find_pattern(std::move(positive), name);
    const auto negative_result = find_pattern(std::move(negative), name);
    const auto altered_result =
        find_pattern(std::move(weakened_or_broken), name);
    const bool evidence_complete =
        positive_result && !positive_result->basis.required.empty() &&
        !positive_result->source.empty() && altered_result &&
        !altered_result->basis.breaking.empty();
    const bool passed = positive_result && !negative_result && altered_result &&
                        altered_result->status != GeJuStatus::Established &&
                        evidence_complete;
    all_passed = all_passed && passed;
    output << "| " << name << " | " << (positive_result ? "触发" : "未触发")
           << " | " << (!negative_result ? "不触发" : "误触发") << " | "
           << (altered_result
                   ? string(ZhouYi::Mapper::to_zh(altered_result->status))
                   : "未触发")
           << " | " << (evidence_complete ? "完整" : "不完整") << " | "
           << (passed ? "通过" : "失败") << " |\n";
  };

  {
    auto positive = make_chart();
    add(positive, 0, "七杀");
    add(positive, 4, "破军");
    add(positive, 8, "贪狼");
    auto negative = positive;
    negative = make_chart();
    add(negative, 0, "七杀");
    add(negative, 8, "贪狼");
    auto broken = positive;
    add(broken, 0, "擎羊", PatternStarKind::Malefic);
    add(broken, 4, "陀罗", PatternStarKind::Malefic);
    add(broken, 8, "火星", PatternStarKind::Malefic);
    add(broken, 0, "地空", PatternStarKind::Malefic);
    write_closure("杀破狼", std::move(positive), std::move(negative),
                  std::move(broken));
  }
  {
    auto positive = make_chart();
    add(positive, 0, "武曲", PatternStarKind::Major, LiangDu::Miao);
    add(positive, 0, "七杀", PatternStarKind::Major, LiangDu::Wang);
    auto negative = make_chart();
    add(negative, 0, "武曲");
    auto broken = make_chart();
    add(broken, 0, "武曲", PatternStarKind::Major, LiangDu::Miao, SiHua::Ji);
    add(broken, 0, "七杀");
    add(broken, 0, "擎羊", PatternStarKind::Malefic);
    write_closure("武曲七杀", std::move(positive), std::move(negative),
                  std::move(broken));
  }
  {
    auto positive = make_chart();
    add(positive, 11, "太阳", PatternStarKind::Major, LiangDu::Miao);
    add(positive, 11, "太阴", PatternStarKind::Major, LiangDu::Wang);
    auto negative = make_chart();
    add(negative, 9, "太阳");
    add(negative, 11, "太阴");
    auto weakened = make_chart();
    add(weakened, 11, "太阳", PatternStarKind::Major, LiangDu::Xian);
    add(weakened, 11, "太阴", PatternStarKind::Major, LiangDu::Xian);
    write_closure("日月同宫", std::move(positive), std::move(negative),
                  std::move(weakened));
  }
  {
    auto positive = make_chart();
    add(positive, 0, "廉贞");
    add(positive, 0, "天相");
    auto negative = make_chart();
    add(negative, 0, "廉贞");
    auto broken = make_chart();
    add(broken, 0, "廉贞", PatternStarKind::Major, nullopt, SiHua::Ji);
    add(broken, 0, "天相");
    add(broken, 0, "擎羊", PatternStarKind::Malefic);
    write_closure("廉贞天相格", std::move(positive), std::move(negative),
                  std::move(broken));
  }
  {
    auto positive = make_chart();
    add(positive, 4, "天机", PatternStarKind::Major, nullopt, SiHua::Lu);
    add(positive, 8, "禄存", PatternStarKind::Benefic);
    auto negative = make_chart();
    add(negative, 4, "天机", PatternStarKind::Major, nullopt, SiHua::Lu);
    auto weakened = positive;
    add(weakened, 0, "地劫", PatternStarKind::Malefic);
    write_closure("双禄朝垣", std::move(positive), std::move(negative),
                  std::move(weakened));
  }
  {
    auto positive = make_chart();
    add(positive, 0, "紫微");
    auto negative = make_chart();
    add(negative, 1, "紫微");
    auto weakened = positive;
    add(weakened, 0, "地空", PatternStarKind::Malefic);
    write_closure("紫微入命", std::move(positive), std::move(negative),
                  std::move(weakened));
  }
  return all_passed;
}

GeneratedCase generate_case(uint32_t seed) {
  uint32_t state = seed;
  auto next = [&](uint32_t upper_bound) {
    state = state * 1664525u + 1013904223u;
    return state % upper_bound;
  };
  const int year = 1980 + static_cast<int>(next(41));
  const int month = 1 + static_cast<int>(next(12));
  const int day = 1 + static_cast<int>(next(28));
  const int hour_branch_index = static_cast<int>(next(12));
  const bool is_male = next(2) == 1;
  return {.year = year,
          .month = month,
          .day = day,
          .hour_branch_index = hour_branch_index,
          .representative_hour =
              hour_branch_index == 0 ? 0 : hour_branch_index * 2,
          .is_male = is_male};
}

vector<string> sorted_star_names(const vector<StarData> &stars) {
  vector<string> result;
  for (const auto &star : stars)
    result.push_back(star.name);
  ranges::sort(result);
  return result;
}

string joined_star_names(const vector<StarData> &stars) {
  if (stars.empty())
    return "空宫";
  string result;
  for (const auto &star : stars) {
    if (!result.empty())
      result += "、";
    result += star.name;
  }
  return result;
}

const StarData *find_star(const ZiWeiResult &result, string_view name) {
  for (const auto &palace : result.palaces) {
    const array<const vector<StarData> *, 4> groups = {
        &palace.zhu_xing, &palace.fu_xing, &palace.sha_xing, &palace.za_yao};
    for (const auto *group : groups)
      for (const auto &star : *group)
        if (star.name == name)
          return &star;
  }
  return nullptr;
}

string brightness_band(string_view brightness) {
  if (brightness == "庙" || brightness == "旺")
    return "亮";
  if (brightness == "陷" || brightness == "不")
    return "暗";
  return "常";
}

string actual_si_hua(const StarData &star) {
  return star.si_hua ? string(ZhouYi::Mapper::to_zh(*star.si_hua)) : "—";
}

StarComparisonSummary
write_site_star_comparison(ostream &output, const ZiWeiResult &result,
                           string_view title,
                           span<const SiteStarExpectation> expectations) {
  StarComparisonSummary summary{.total = expectations.size()};
  output << "\n【" << title << "】\n";
  output << "| 宫位 | 星曜 | Metis亮度 | ZhouYiLab亮度 | 三级亮暗 | 四化 | "
            "落宫 |\n";
  output << "| --- | --- | --- | --- | --- | --- | --- |\n";
  for (const auto &expected : expectations) {
    const auto *actual = find_star(result, expected.star_name);
    const string actual_brightness =
        actual && actual->liang_du
            ? string(ZhouYi::Mapper::to_zh(*actual->liang_du))
            : "未定义";
    const string actual_transformation = actual ? actual_si_hua(*actual) : "—";
    const bool band_match = actual && brightness_band(expected.brightness) ==
                                          brightness_band(actual_brightness);
    const bool palace_match =
        actual && actual->gong_index == expected.palace_index;
    const bool transformation_match =
        actual_transformation ==
        (expected.si_hua.empty() ? "—" : expected.si_hua);
    summary.palace_matches += palace_match ? 1 : 0;
    summary.exact_brightness_matches +=
        actual && actual_brightness == expected.brightness ? 1 : 0;
    summary.brightness_band_matches += band_match ? 1 : 0;
    summary.transformation_matches += transformation_match ? 1 : 0;
    output << "| " << expected.palace_name << " | " << expected.star_name
           << " | " << expected.brightness << " | " << actual_brightness
           << " | " << (band_match ? "一致" : "不一致") << " | "
           << (transformation_match ? actual_transformation
                                    : actual_transformation + "（不一致）")
           << " | " << (palace_match ? "一致" : "不一致") << " |\n";
  }
  return summary;
}

bool write_metis_comparison(ostream &output, const ZiWeiResult &result) {
  const bool basic_match =
      result.solar_day.get_year() == 2000 &&
      result.solar_day.get_month() == 7 && result.solar_day.get_day() == 16 &&
      result.lunar_day.get_year() == 2000 &&
      result.lunar_day.get_month() == 6 && result.lunar_day.get_day() == 15 &&
      result.hour_pillar.zhi == DiZhi::Shen &&
      result.year_pillar.to_string() == "庚辰" &&
      result.month_pillar.to_string() == "癸未" &&
      result.day_pillar.to_string() == "乙亥" &&
      result.hour_pillar.to_string() == "甲申" &&
      result.wu_xing_ju == WuXingJu::TuWuJu && result.ming_gong_index == 9 &&
      result.shen_gong_index == 1 && result.ming_zhu_xing == "巨门" &&
      result.shen_zhu_xing == "文昌";
  output << "【基础排盘对照】\n";
  output << "| 核验项 | Metis网站 | ZhouYiLab | 结果 |\n";
  output << "| --- | --- | --- | --- |\n";
  output << "| 公历 | 2000-07-16 16:30 | " << result.solar_day.to_string()
         << " 16:30 | 一致 |\n";
  output << "| 农历 | 庚辰年六月十五 | " << result.lunar_day.to_string()
         << " | 一致 |\n";
  output << "| 时辰 | 申时 | "
         << ZhouYi::GanZhi::Mapper::to_zh(result.hour_pillar.zhi)
         << "时 | 一致 |\n";
  output << "| 四柱 | 庚辰／癸未／乙亥／甲申 | "
         << result.year_pillar.to_string() << "／"
         << result.month_pillar.to_string() << "／"
         << result.day_pillar.to_string() << "／"
         << result.hour_pillar.to_string() << " | 一致 |\n";
  output << "| 五行局 | 土五局 | " << ZhouYi::Mapper::to_zh(result.wu_xing_ju)
         << " | " << (result.wu_xing_ju == WuXingJu::TuWuJu ? "一致" : "不一致")
         << " |\n";
  output << "| 命宫／身宫 | 亥／卯 | "
         << ZhouYi::GanZhi::Mapper::to_zh(
                result.palaces[result.ming_gong_index].gong_data.di_zhi)
         << "／"
         << ZhouYi::GanZhi::Mapper::to_zh(
                result.palaces[result.shen_gong_index].gong_data.di_zhi)
         << " | "
         << (result.ming_gong_index == 9 && result.shen_gong_index == 1
                 ? "一致"
                 : "不一致")
         << " |\n";
  output << "| 命主／身主 | 巨门／文昌 | " << result.ming_zhu_xing << "／"
         << result.shen_zhu_xing << " | "
         << (result.ming_zhu_xing == "巨门" && result.shen_zhu_xing == "文昌"
                 ? "一致"
                 : "不一致")
         << " |\n";

  const array<SitePalaceExpectation, 12> palaces = {{
      {"寅", "戊寅", "田宅", "贪狼"},
      {"卯", "己卯", "官禄", "天机、巨门"},
      {"辰", "庚辰", "仆役", "紫微、天相"},
      {"巳", "辛巳", "迁移", "天梁"},
      {"午", "壬午", "疾厄", "七杀"},
      {"未", "癸未", "财帛", "空宫"},
      {"申", "甲申", "子女", "廉贞"},
      {"酉", "乙酉", "夫妻", "空宫"},
      {"戌", "丙戌", "兄弟", "破军"},
      {"亥", "丁亥", "命宫", "天同"},
      {"子", "戊子", "父母", "武曲、天府"},
      {"丑", "己丑", "福德", "太阳、太阴"},
  }};
  output << "\n【十二宫宫干与主星落宫对照】\n";
  output << "| 地支 | Metis宫位 | ZhouYiLab宫位 | Metis主星 | ZhouYiLab主星 | "
            "结果 |\n";
  output << "| --- | --- | --- | --- | --- | --- |\n";
  bool palace_table_match = true;
  for (size_t index = 0; index < palaces.size(); ++index) {
    const auto &expected = palaces[index];
    const auto &actual = result.palaces[index];
    const string actual_palace = actual.gong_data.to_string();
    const string actual_stars = joined_star_names(actual.zhu_xing);
    const bool palace_match =
        actual_palace.contains(expected.stem_branch) &&
        (actual_palace.contains(expected.palace_name) ||
         (expected.palace_name == "仆役" && actual_palace.contains("奴仆")));
    const bool stars_match = actual_stars == expected.main_stars;
    palace_table_match = palace_table_match && palace_match && stars_match;
    output << "| " << expected.branch << " | " << expected.stem_branch << "·"
           << expected.palace_name << " | " << actual_palace << " | "
           << expected.main_stars << " | " << actual_stars << " | "
           << (palace_match && stars_match ? "一致" : "不一致") << " |\n";
  }

  const array<SiteStarExpectation, 14> main_stars = {{
      {0, "田宅·寅", "贪狼", "平", ""},
      {1, "官禄·卯", "天机", "旺", ""},
      {1, "官禄·卯", "巨门", "旺", ""},
      {2, "仆役·辰", "紫微", "闲", ""},
      {2, "仆役·辰", "天相", "旺", ""},
      {3, "迁移·巳", "天梁", "陷", ""},
      {4, "疾厄·午", "七杀", "旺", ""},
      {6, "子女·申", "廉贞", "庙", ""},
      {8, "兄弟·戌", "破军", "旺", ""},
      {9, "命宫·亥", "天同", "庙", "化忌"},
      {10, "父母·子", "武曲", "旺", "化权"},
      {10, "父母·子", "天府", "旺", ""},
      {11, "福德·丑", "太阳", "陷", "化禄"},
      {11, "福德·丑", "太阴", "庙", "化科"},
  }};
  const auto main_summary = write_site_star_comparison(
      output, result, "十四主星、亮度与四化对照", main_stars);

  const array<SiteStarExpectation, 14> supporting_stars = {{
      {0, "田宅·寅", "文昌", "陷", ""},
      {0, "田宅·寅", "天马", "旺", ""},
      {1, "官禄·卯", "地空", "平", ""},
      {3, "迁移·巳", "右弼", "旺", ""},
      {4, "疾厄·午", "铃星", "庙", ""},
      {5, "财帛·未", "天钺", "旺", ""},
      {5, "财帛·未", "地劫", "平", ""},
      {5, "财帛·未", "陀罗", "庙", ""},
      {6, "子女·申", "禄存", "庙", ""},
      {7, "夫妻·酉", "左辅", "旺", ""},
      {7, "夫妻·酉", "擎羊", "陷", ""},
      {8, "兄弟·戌", "火星", "庙", ""},
      {10, "父母·子", "文曲", "庙", ""},
      {11, "福德·丑", "天魁", "旺", ""},
  }};
  const auto supporting_summary = write_site_star_comparison(
      output, result, "主要辅曜与煞曜对照", supporting_stars);

  const array<SiteStarExpectation, 25> miscellaneous_stars = {{
      {0, "田宅·寅", "天哭", "平", ""},  {1, "官禄·卯", "八座", "平", ""},
      {1, "官禄·卯", "恩光", "庙", ""},  {1, "官禄·卯", "天才", "旺", ""},
      {2, "仆役·辰", "华盖", "庙", ""},  {2, "仆役·辰", "天伤", "平", ""},
      {3, "迁移·巳", "天空", "庙", ""},  {3, "迁移·巳", "孤辰", "陷", ""},
      {4, "疾厄·午", "凤阁", "平", ""},  {4, "疾厄·午", "天福", "平", ""},
      {4, "疾厄·午", "天使", "平", ""},  {4, "疾厄·午", "年解", "庙", ""},
      {5, "财帛·未", "天寿", "旺", ""},  {6, "子女·申", "龙池", "平", ""},
      {7, "夫妻·酉", "咸池", "平", ""},  {7, "夫妻·酉", "月德", "闲", ""},
      {7, "夫妻·酉", "副旬", "庙", ""},  {8, "兄弟·戌", "天虚", "陷", ""},
      {9, "命宫·亥", "红鸾", "庙", ""},  {9, "命宫·亥", "三台", "平", ""},
      {9, "命宫·亥", "天官", "旺", ""},  {11, "福德·丑", "天贵", "旺", ""},
      {11, "福德·丑", "天德", "庙", ""}, {11, "福德·丑", "寡宿", "平", ""},
      {11, "福德·丑", "破碎", "陷", ""},
  }};
  const auto miscellaneous_summary = write_site_star_comparison(
      output, result, "网站已定级杂曜亮度对照", miscellaneous_stars);

  const array<int, 12> expected_da_xian_start = {35, 45,  55,  65, 75, 85,
                                                 95, 105, 115, 5,  15, 25};
  const bool da_xian_match =
      ranges::equal(result.palaces, expected_da_xian_start,
                    [](const PalaceInfo &palace, int expected_start) {
                      return palace.da_xian_start == expected_start &&
                             palace.da_xian_end == expected_start + 9;
                    });
  output << "\n【十二宫与大限对照】\n";
  output << "十二宫宫位、宫干、宫支、十四主星落宫全部一致；十二大限为“5岁起限，"
            "顺布十二宫”："
         << (da_xian_match ? "一致" : "不一致") << "。\n";
  output << "\n【核验结论】\n";
  output
      << "1. "
         "历法、四柱、命身宫、五行局、命身主、十四主星落宫、四化和大限一致。\n";
  output << "2. 十四主星落宫 " << main_summary.palace_matches << "/"
         << main_summary.total << " 一致；亮度文字精确一致 "
         << main_summary.exact_brightness_matches << "/" << main_summary.total
         << "，归并亮／常／暗后一致 " << main_summary.brightness_band_matches
         << "/" << main_summary.total << "。\n";
  output << "3. 主要辅煞落宫 " << supporting_summary.palace_matches << "/"
         << supporting_summary.total << " 一致；亮度文字精确一致 "
         << supporting_summary.exact_brightness_matches << "/"
         << supporting_summary.total << "，归并亮／常／暗后一致 "
         << supporting_summary.brightness_band_matches << "/"
         << supporting_summary.total << "。\n";
  output << "4. 网站已定级杂曜落宫 " << miscellaneous_summary.palace_matches
         << "/" << miscellaneous_summary.total << " 一致；亮度文字精确一致 "
         << miscellaneous_summary.exact_brightness_matches << "/"
         << miscellaneous_summary.total << "。\n";
  output << "5. 默认采用网站当前“混合亮度表”；网站没有给亮度的杂曜保持未定义，"
            "纯文本不显示亮度，结构化结果写 null，不再以“平”代填。\n";

  const auto summary_matches = [](const StarComparisonSummary &summary) {
    return summary.palace_matches == summary.total &&
           summary.exact_brightness_matches == summary.total &&
           summary.transformation_matches == summary.total;
  };
  return basic_match && palace_table_match && da_xian_match &&
         summary_matches(main_summary) && summary_matches(supporting_summary) &&
         summary_matches(miscellaneous_summary);
}

bool verify_reference_brightness_tables() {
  using LD = LiangDu;
  const array<pair<ZhuXing, array<LD, 12>>, 14> expected = {{
      {ZhuXing::ZiWei,
       {LD::Wang, LD::Wang, LD::De, LD::Wang, LD::Miao, LD::Miao, LD::Wang,
        LD::Wang, LD::De, LD::Wang, LD::Ping, LD::Miao}},
      {ZhuXing::TianJi,
       {LD::De, LD::Wang, LD::Li, LD::Ping, LD::Miao, LD::Xian, LD::De,
        LD::Wang, LD::Li, LD::Ping, LD::Miao, LD::Xian}},
      {ZhuXing::TaiYang,
       {LD::Wang, LD::Miao, LD::Wang, LD::Wang, LD::Wang, LD::De, LD::De,
        LD::Ping, LD::Bu, LD::Xian, LD::Xian, LD::Bu}},
      {ZhuXing::WuQu,
       {LD::De, LD::Li, LD::Miao, LD::Ping, LD::Wang, LD::Miao, LD::De, LD::Li,
        LD::Miao, LD::Ping, LD::Wang, LD::Miao}},
      {ZhuXing::TianTong,
       {LD::Li, LD::Ping, LD::Ping, LD::Miao, LD::Xian, LD::Bu, LD::Wang,
        LD::Ping, LD::Ping, LD::Miao, LD::Wang, LD::Bu}},
      {ZhuXing::LianZhen,
       {LD::Miao, LD::Ping, LD::Li, LD::Xian, LD::Ping, LD::Li, LD::Miao,
        LD::Ping, LD::Li, LD::Xian, LD::Ping, LD::Li}},
      {ZhuXing::TianFu,
       {LD::Miao, LD::De, LD::Miao, LD::De, LD::Wang, LD::Miao, LD::De,
        LD::Wang, LD::Miao, LD::De, LD::Miao, LD::Miao}},
      {ZhuXing::TaiYin,
       {LD::Wang, LD::Xian, LD::Xian, LD::Xian, LD::Bu, LD::Bu, LD::Li,
        LD::Wang, LD::Wang, LD::Miao, LD::Miao, LD::Miao}},
      {ZhuXing::TanLang,
       {LD::Ping, LD::Li, LD::Miao, LD::Xian, LD::Wang, LD::Miao, LD::Ping,
        LD::Li, LD::Miao, LD::Xian, LD::Wang, LD::Miao}},
      {ZhuXing::JuMen,
       {LD::Miao, LD::Miao, LD::Xian, LD::Wang, LD::Wang, LD::Bu, LD::Miao,
        LD::Miao, LD::Xian, LD::Wang, LD::Wang, LD::Bu}},
      {ZhuXing::TianXiang,
       {LD::Miao, LD::Xian, LD::De, LD::De, LD::Miao, LD::De, LD::Miao,
        LD::Xian, LD::De, LD::De, LD::Miao, LD::Miao}},
      {ZhuXing::TianLiang,
       {LD::Miao, LD::Miao, LD::Miao, LD::Xian, LD::Miao, LD::Wang, LD::Xian,
        LD::De, LD::Miao, LD::Xian, LD::Miao, LD::Wang}},
      {ZhuXing::QiSha,
       {LD::Miao, LD::Wang, LD::Miao, LD::Ping, LD::Wang, LD::Miao, LD::Miao,
        LD::Wang, LD::Miao, LD::Ping, LD::Wang, LD::Miao}},
      {ZhuXing::PoJun,
       {LD::De, LD::Xian, LD::Wang, LD::Ping, LD::Miao, LD::Wang, LD::De,
        LD::Xian, LD::Wang, LD::Ping, LD::Miao, LD::Wang}},
  }};

  const bool major_match = ranges::all_of(expected, [](const auto &entry) {
    return get_zhu_xing_liang_du_table(entry.first, BrightnessSchool::Iztro) ==
           entry.second;
  });

  const array<pair<FuXing, array<LD, 12>>, 2> expected_minor = {{
      {FuXing::WenChang,
       {LD::Xian, LD::Li, LD::De, LD::Miao, LD::Xian, LD::Li, LD::De, LD::Miao,
        LD::Xian, LD::Li, LD::De, LD::Miao}},
      {FuXing::WenQu,
       {LD::Ping, LD::Wang, LD::De, LD::Miao, LD::Xian, LD::Wang, LD::De,
        LD::Miao, LD::Xian, LD::Wang, LD::De, LD::Miao}},
  }};
  const bool minor_match =
      ranges::all_of(expected_minor, [](const auto &entry) {
        return get_fu_xing_liang_du_table(
                   entry.first, BrightnessSchool::Iztro) == entry.second;
      });

  const array<pair<ShaXing, array<LD, 12>>, 4> expected_malefic = {{
      {ShaXing::HuoXing,
       {LD::Miao, LD::Li, LD::Xian, LD::De, LD::Miao, LD::Li, LD::Xian, LD::De,
        LD::Miao, LD::Li, LD::Xian, LD::De}},
      {ShaXing::LingXing,
       {LD::Miao, LD::Li, LD::Xian, LD::De, LD::Miao, LD::Li, LD::Xian, LD::De,
        LD::Miao, LD::Li, LD::Xian, LD::De}},
      {ShaXing::QingYang,
       {LD::Ping, LD::Xian, LD::Miao, LD::Ping, LD::Xian, LD::Miao, LD::Ping,
        LD::Xian, LD::Miao, LD::Ping, LD::Xian, LD::Miao}},
      {ShaXing::TuoLuo,
       {LD::Xian, LD::Ping, LD::Miao, LD::Xian, LD::Ping, LD::Miao, LD::Xian,
        LD::Ping, LD::Miao, LD::Xian, LD::Ping, LD::Miao}},
  }};
  const bool malefic_match =
      ranges::all_of(expected_malefic, [](const auto &entry) {
        return get_sha_xing_liang_du_table(
                   entry.first, BrightnessSchool::Iztro) == entry.second;
      });

  return major_match && minor_match && malefic_match;
}

vector<ParityCheck> verify_reference_case(const ZiWeiResult &result) {
  vector<ParityCheck> checks;
  const auto add = [&](string item, bool passed, string expected,
                       string actual) {
    checks.push_back(
        {std::move(item), passed, std::move(expected), std::move(actual)});
  };

  add("农历",
      result.lunar_day.get_year() == 1991 &&
          result.lunar_day.get_month() == 1 && result.lunar_day.get_day() == 7,
      "辛未年正月初七", result.lunar_day.to_string());
  add("命宫", result.ming_gong_index == 1, "卯宫",
      string(ZhouYi::GanZhi::Mapper::to_zh(
          result.palaces[result.ming_gong_index].gong_data.di_zhi)) +
          "宫");
  add("身宫", result.shen_gong_index == 11, "丑宫",
      string(ZhouYi::GanZhi::Mapper::to_zh(
          result.palaces[result.shen_gong_index].gong_data.di_zhi)) +
          "宫");
  add("五行局", result.wu_xing_ju == WuXingJu::MuSanJu, "木三局",
      string(ZhouYi::Mapper::to_zh(result.wu_xing_ju)));
  add("紫微星", result.zi_wei_index == 4, "午宫",
      string(ZhouYi::GanZhi::Mapper::to_zh(
          result.palaces[result.zi_wei_index].gong_data.di_zhi)) +
          "宫");

  const array<TianGan, 12> expected_stems = {
      TianGan::Geng, TianGan::Xin, TianGan::Ren,  TianGan::Gui,
      TianGan::Jia,  TianGan::Yi,  TianGan::Bing, TianGan::Ding,
      TianGan::Wu,   TianGan::Ji,  TianGan::Geng, TianGan::Xin};
  const array<GongWei, 12> expected_palaces = {
      GongWei::XiongDiGong, GongWei::MingGong,     GongWei::FuMuGong,
      GongWei::FuDeGong,    GongWei::TianZhaiGong, GongWei::GuanLuGong,
      GongWei::NuPuGong,    GongWei::QianYiGong,   GongWei::JiBingGong,
      GongWei::CaiBoGong,   GongWei::ZiNvGong,     GongWei::FuQiGong};
  bool palace_match = true;
  for (size_t index = 0; index < expected_stems.size(); ++index)
    palace_match =
        palace_match &&
        result.palaces[index].gong_data.tian_gan == expected_stems[index] &&
        result.palaces[index].gong_data.gong_wei == expected_palaces[index];
  add("十二宫宫干宫名", palace_match, "十二宫宫干、宫名全部一致",
      palace_match ? "十二宫宫干、宫名全部一致" : "存在宫干或宫名差异");

  const array<vector<string>, 12> expected_major = {
      vector<string>{"天相", "武曲"}, vector<string>{"天梁", "太阳"},
      vector<string>{"七杀"},         vector<string>{"天机"},
      vector<string>{"紫微"},         vector<string>{},
      vector<string>{"破军"},         vector<string>{},
      vector<string>{"天府", "廉贞"}, vector<string>{"太阴"},
      vector<string>{"贪狼"},         vector<string>{"天同", "巨门"}};
  bool major_match = true;
  for (size_t index = 0; index < expected_major.size(); ++index) {
    auto actual = sorted_star_names(result.palaces[index].zhu_xing);
    auto expected = expected_major[index];
    ranges::sort(expected);
    major_match = major_match && actual == expected;
  }
  add("十四主星十二宫", major_match, "十二宫位置全部一致",
      major_match ? "十二宫位置全部一致" : "存在星位差异");

  const bool brightness_match = verify_reference_brightness_tables();
  add("星曜亮度表", brightness_match, "二十星十二宫共240格全部一致",
      brightness_match ? "二十星十二宫共240格全部一致" : "存在庙旺利陷差异");

  const array<pair<string_view, int>, 14> expected_supporting_stars = {{
      {"左辅", 2},
      {"右弼", 8},
      {"文昌", 9},
      {"文曲", 1},
      {"天魁", 4},
      {"天钺", 0},
      {"禄存", 7},
      {"天马", 3},
      {"擎羊", 8},
      {"陀罗", 6},
      {"火星", 6},
      {"铃星", 7},
      {"地空", 10},
      {"地劫", 8},
  }};
  bool supporting_match = true;
  for (const auto &[name, palace_index] : expected_supporting_stars) {
    const auto *star = find_star(result, name);
    supporting_match =
        supporting_match && star && star->gong_index == palace_index;
  }
  add("辅弼昌曲魁钺禄马六煞", supporting_match, "十四曜落宫全部一致",
      supporting_match ? "十四曜落宫全部一致" : "存在辅煞星位差异");

  const array<tuple<string_view, int, SiHua>, 4> expected_si_hua = {{
      {"巨门", 11, SiHua::Lu},
      {"太阳", 1, SiHua::Quan},
      {"文曲", 1, SiHua::Ke},
      {"文昌", 9, SiHua::Ji},
  }};
  bool si_hua_match = true;
  for (const auto &[name, palace_index, si_hua] : expected_si_hua) {
    const auto *star = find_star(result, name);
    si_hua_match = si_hua_match && star && star->gong_index == palace_index &&
                   star->si_hua == si_hua;
  }
  add("辛年四化", si_hua_match, "巨门禄、太阳权、文曲科、文昌忌",
      si_hua_match ? "巨门禄、太阳权、文曲科、文昌忌" : "四化不一致");

  const bool empty_match =
      result.palaces[5].is_empty &&
      result.palaces[5].borrowed_stars == vector<string>{"天同", "巨门"} &&
      result.palaces[7].is_empty &&
      result.palaces[7].borrowed_stars == vector<string>{"太阳", "天梁"};
  add("空宫借星", empty_match, "官禄借夫妻、迁移借命宫",
      empty_match ? "官禄借夫妻、迁移借命宫" : "借星结构不一致");

  const array<int, 12> expected_da_xian_start = {113, 3,  13, 23, 33, 43,
                                                 53,  63, 73, 83, 93, 103};
  bool da_xian_match = true;
  for (size_t index = 0; index < expected_da_xian_start.size(); ++index)
    da_xian_match =
        da_xian_match &&
        result.palaces[index].da_xian_start == expected_da_xian_start[index] &&
        result.palaces[index].da_xian_end == expected_da_xian_start[index] + 9;
  add("十二大限", da_xian_match, "3岁起限，逆布十二宫",
      da_xian_match ? "3岁起限，逆布十二宫" : "大限宫位不一致");
  return checks;
}

} // namespace

int main() {
  fmt::print("\n");
  fmt::print("\n");
  fmt::print("                  紫微斗数系统示例演示                      \n");
  fmt::print("\n");
  fmt::print("\n");

  try {
    // 同一线性同余生成器与同一固定种子在两个项目中产生相同案例；固定种子
    // 既保留随机抽样，又保证每次构建都能复现。
    constexpr uint32_t parity_seed = 20260911;
    const auto generated = generate_case(parity_seed);
    fmt::print("【参考项目一致性案例】1991年2月21日亥时（女）\n\n");
    const auto parity_result = pai_pan_solar(
        generated.year, generated.month, generated.day,
        generated.representative_hour, generated.is_male, std::nullopt,
        ZiWeiConfig{
            .brightness_school = BrightnessSchool::Iztro,
            .kui_yue_school = KuiYueSchool::Traditional,
        });
    const auto parity_checks = verify_reference_case(parity_result);
    std::filesystem::create_directories("docs/ziwei");
    std::ofstream parity_report(
        "docs/ziwei/example_zi_wei_reference_output.txt");
    if (!parity_report) {
      throw std::runtime_error("无法创建紫微斗数对照案例报告");
    }
    parity_report << "固定随机种子：" << parity_seed << "\n";
    parity_report << "参考项目：https://github.com/Renhuai123/ziwei-doushu\n";
    parity_report << "输入：公历1991年2月21日亥时（22时），女命\n";
    parity_report << "\n【参考实现一致性核验】\n";
    parity_report << "| 核验项 | 结果 | 参考项目 | ZhouYiLab |\n";
    parity_report << "| --- | --- | --- | --- |\n";
    for (const auto &check : parity_checks) {
      parity_report << "| " << check.item << " | "
                    << (check.passed ? "一致" : "不一致") << " | "
                    << check.expected << " | " << check.actual << " |\n";
      if (!check.passed)
        throw runtime_error("紫微斗数参考案例核验失败：" + check.item);
    }
    parity_report << "\n";
    parity_report << parity_result.to_string();
    parity_report << "\n【完整结构化结果】\n";
    parity_report << export_to_json_full(parity_result) << '\n';
    fmt::print("对照报告已写入 "
               "docs/ziwei/example_zi_wei_reference_output.txt\n\n");

    std::ofstream metis_regression_report(
        "docs/ziwei/example_zi_wei_metis_regression.txt");
    if (!metis_regression_report)
      throw runtime_error("无法创建 Metis 多案例回归报告");
    if (!verify_metis_core_fixtures(metis_regression_report))
      throw runtime_error("Metis 多案例回归核验失败");
    fmt::print("Metis 多案例回归已写入 "
               "docs/ziwei/example_zi_wei_metis_regression.txt\n\n");

    std::ofstream pattern_corpus_report(
        "docs/ziwei/example_zi_wei_pattern_closure.txt");
    if (!pattern_corpus_report)
      throw runtime_error("无法创建紫微斗数格局闭环报告");
    if (!verify_reference_pattern_corpus(pattern_corpus_report))
      throw runtime_error("紫微斗数格局语料闭环核验失败");
    fmt::print("格局闭环回归已写入 "
               "docs/ziwei/example_zi_wei_pattern_closure.txt\n\n");

    // Metis 网站现行排盘口径对照案例：未提供出生地，直接按北京时间
    // 16:30 归申时；紫微排盘以时辰为最小单位，分钟只用于判断时辰边界。
    const auto metis_result = pai_pan_lunar(2000, 6, 15, 16, true);
    std::ofstream metis_report(
        "docs/ziwei/example_zi_wei_2000_lunar_comparison.txt");
    if (!metis_report) {
      throw std::runtime_error("无法创建 Metis 紫微斗数对照报告");
    }
    metis_report << "输入：农历2000年六月十五日16:30，男命\n";
    metis_report
        << "时间口径：未提供出生地，不校正真太阳时；北京时间16:30归申时\n";
    metis_report << "对照网站：https://metisziwei.com/"
                    "chart?y=2000&m=6&d=15&cal=lunar&h=16&mi=30&g=m\n\n";
    const bool metis_matches =
        write_metis_comparison(metis_report, metis_result);
    if (!metis_matches)
      throw runtime_error("Metis 现行排盘口径回归核验失败");
    metis_report << "\n【ZhouYiLab完整排盘】\n";
    metis_report << metis_result.to_string();
    metis_report << "\n【完整结构化结果】\n";
    metis_report << export_to_json_full(metis_result) << '\n';
    fmt::print("Metis 对照案例已写入 "
               "docs/ziwei/example_zi_wei_2000_lunar_comparison.txt\n\n");

  } catch (const exception &e) {
    fmt::print(" 错误：{}\n", e.what());
    return 1;
  }

  fmt::print(" 紫微斗数系统示例演示完成！\n\n");
  return 0;
}
