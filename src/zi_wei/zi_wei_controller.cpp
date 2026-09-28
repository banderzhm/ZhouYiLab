// 紫微斗数控制器实现
module ZhouYi.ZiWei.Controller;

import ZhouYi.GanZhi;
import ZhouYi.ZiWei;
import ZhouYi.ZiWei.SiHua;
import ZhouYi.ZiWei.SanFang;
import ZhouYi.ZiWei.GeJu;
import ZhouYi.ZiWei.Star;
import ZhouYi.ZiWei.StarDocument;
import ZhouYi.ZiWei.Horoscope;
import ZhouYi.ZhMapper;
import ZhouYi.tyme;
import fmt;
import nlohmann.json;
import std;

using json = nlohmann::json;

namespace ZhouYi::ZiWei {
using namespace std;
using namespace ZhouYi::GanZhi;
using namespace ZhouYi::Mapper;

void pai_pan_and_print_solar(int year, int month, int day, int hour,
                             bool is_male) {
  try {
    auto result = pai_pan_solar(year, month, day, hour, is_male);
    fmt::print("{}\n", result.to_string());
  } catch (const exception &e) {
    fmt::print("[错误] 排盘错误: {}\n", e.what());
  }
}

void pai_pan_and_print_lunar(int year, int month, int day, int hour,
                             bool is_male, bool is_leap_month) {
  try {
    auto result = pai_pan_lunar(year, month, day, hour, is_male, is_leap_month);
    fmt::print("{}\n", result.to_string());
  } catch (const exception &e) {
    fmt::print("[错误] 排盘错误: {}\n", e.what());
  }
}

void display_palace_detail(const ZiWeiResult &result, GongWei gong_wei) {
  try {
    const auto &palace = result.get_palace(gong_wei);
    fmt::print("\n{}\n", palace.to_string());
  } catch (const exception &e) {
    fmt::print("[错误] 获取宫位错误: {}\n", e.what());
  }
}

void display_ming_gong_san_fang_si_zheng(const ZiWeiResult &result) {
  fmt::print("\n\n");
  fmt::print("       命宫三方四正\n");
  fmt::print("\n\n");

  // 命宫
  fmt::print("【命宫】\n");
  display_palace_detail(result, GongWei::MingGong);

  // 财帛宫（三方）
  fmt::print("\n【财帛宫（三方）】\n");
  display_palace_detail(result, GongWei::CaiBoGong);

  // 官禄宫（三方）
  fmt::print("\n【官禄宫（三方）】\n");
  display_palace_detail(result, GongWei::GuanLuGong);

  // 迁移宫（对宫，四正）
  fmt::print("\n【迁移宫（对宫）】\n");
  display_palace_detail(result, GongWei::QianYiGong);
}

string export_to_json(const ZiWeiResult &result) {
  json j;

  // 基本信息
  j["solar_date"] = result.solar_day.to_string();
  j["lunar_date"] = result.lunar_day.to_string();
  j["lunar_hour"] = result.lunar_hour.to_string();
  j["gender"] = result.is_male ? "男" : "女";

  // 四柱
  j["si_zhu"]["year"] = result.year_pillar.to_string();
  j["si_zhu"]["month"] = result.month_pillar.to_string();
  j["si_zhu"]["day"] = result.day_pillar.to_string();
  j["si_zhu"]["hour"] = result.hour_pillar.to_string();

  // 命盘核心数据
  j["wu_xing_ju"] = string(to_zh(result.wu_xing_ju));
  j["ming_gong_index"] = result.ming_gong_index;
  j["shen_gong_index"] = result.shen_gong_index;
  j["zi_wei_index"] = result.zi_wei_index;
  j["tian_fu_index"] = result.tian_fu_index;
  j["ming_zhu"] = result.ming_zhu_xing;
  j["shen_zhu"] = result.shen_zhu_xing;

  // 十二宫
  j["palaces"] = json::array();
  for (const auto &palace : result.palaces) {
    json p;
    p["name"] = string(to_zh(palace.gong_data.gong_wei));
    p["gan_zhi"] = fmt::format(
        "{}{}", string(GanZhi::Mapper::to_zh(palace.gong_data.tian_gan)),
        string(GanZhi::Mapper::to_zh(palace.gong_data.di_zhi)));
    p["is_ming_palace"] = palace.gong_data.is_ming_palace;
    p["is_body_palace"] = palace.gong_data.is_body_palace;
    p["branch_index"] = static_cast<int>(palace.gong_data.di_zhi);
    p["stem_index"] = static_cast<int>(palace.gong_data.tian_gan);
    p["is_empty"] = palace.is_empty;
    p["opposite_index"] = palace.opposite_index;
    p["borrowed_from_index"] = palace.borrowed_from_index
                                   ? json(*palace.borrowed_from_index)
                                   : json(nullptr);
    p["borrowed_stars"] = palace.borrowed_stars;
    p["da_xian"] = {{"start_age", palace.da_xian_start},
                    {"end_age", palace.da_xian_end}};
    p["xiao_xian_ages"] = palace.xiao_xian_ages;
    p["liu_nian_ages"] = palace.liu_nian_ages;

    const auto star_json = [](const StarData &star, string_view category) {
      json value{{"name", star.name}, {"category", category}};
      value["brightness"] =
          star.liang_du ? json(string(to_zh(*star.liang_du))) : json(nullptr);
      if (star.si_hua)
        value["si_hua"] = string(to_zh(*star.si_hua));
      return value;
    };
    p["stars"] = json::array();

    // 主星
    p["zhu_xing"] = json::array();
    for (const auto &star : palace.zhu_xing) {
      json s;
      s["name"] = star.name;
      s["liang_du"] =
          star.liang_du ? json(string(to_zh(*star.liang_du))) : json(nullptr);
      if (star.si_hua.has_value()) {
        s["si_hua"] = string(to_zh(*star.si_hua));
      }
      p["zhu_xing"].push_back(s);
      p["stars"].push_back(star_json(star, "主星"));
    }

    // 辅星
    p["fu_xing"] = json::array();
    for (const auto &star : palace.fu_xing) {
      p["fu_xing"].push_back(star.name);
      p["stars"].push_back(star_json(star, "辅星"));
    }

    // 煞星
    p["sha_xing"] = json::array();
    for (const auto &star : palace.sha_xing) {
      p["sha_xing"].push_back(star.name);
      p["stars"].push_back(star_json(star, "煞星"));
    }

    p["za_yao"] = json::array();
    for (const auto &star : palace.za_yao) {
      p["za_yao"].push_back(star.name);
      p["stars"].push_back(star_json(star, "杂曜"));
    }

    j["palaces"].push_back(p);
  }

  return j.dump(2);
}

// ============= 辅助函数：准备数据 =============

namespace {
// 准备宫干地支数据
array<pair<TianGan, DiZhi>, 12>
prepare_gong_gan_zhi(const ZiWeiResult &result) {
  array<pair<TianGan, DiZhi>, 12> gong_gan_zhi;
  for (int i = 0; i < 12; ++i) {
    gong_gan_zhi[i] = {result.palaces[i].gong_data.tian_gan,
                       result.palaces[i].gong_data.di_zhi};
  }
  return gong_gan_zhi;
}

// 准备星耀列表
array<vector<string>, 12> prepare_stars_in_gong(const ZiWeiResult &result) {
  array<vector<string>, 12> stars_in_gong;
  for (int i = 0; i < 12; ++i) {
    for (const auto &star : result.palaces[i].zhu_xing) {
      stars_in_gong[i].push_back(star.name);
    }
    for (const auto &star : result.palaces[i].fu_xing) {
      stars_in_gong[i].push_back(star.name);
    }
    for (const auto &star : result.palaces[i].sha_xing) {
      stars_in_gong[i].push_back(star.name);
    }
    for (const auto &star : result.palaces[i].za_yao) {
      stars_in_gong[i].push_back(star.name);
    }
  }
  return stars_in_gong;
}

/**
 * 将完整排盘转换为格局引擎的强类型查询上下文。
 *
 *
 * <p>转换只执行一次，保留星曜分类、庙旺利陷与生年四化；格局规则不再

 * * 从展示字符串反查命盘星曜落宫。</p>
 */
PatternChart prepare_pattern_chart(const ZiWeiResult &result) {
  PatternChart chart;
  chart.set_ming_palace(result.ming_gong_index);
  chart.set_body_palace(result.shen_gong_index);

  for (int index = 0; index < 12; ++index) {
    const auto &palace = result.palaces[index];
    chart.set_palace(index, palace.gong_data.gong_wei, palace.gong_data.di_zhi,
                     palace.gong_data.is_body_palace);

    const auto add_group = [&](const vector<StarData> &stars,
                               PatternStarKind kind) {
      for (const auto &star : stars) {
        chart.add_star(index, star.name, kind, star.liang_du, star.si_hua);
      }
    };
    add_group(palace.zhu_xing, PatternStarKind::Major);
    add_group(palace.fu_xing, PatternStarKind::Benefic);
    add_group(palace.sha_xing, PatternStarKind::Malefic);
    add_group(palace.za_yao, PatternStarKind::Minor);
  }
  return chart;
}

// 准备地支列表
array<DiZhi, 12> prepare_gong_di_zhi(const ZiWeiResult &result) {
  array<DiZhi, 12> gong_di_zhi;
  for (int i = 0; i < 12; ++i) {
    gong_di_zhi[i] = result.palaces[i].gong_data.di_zhi;
  }
  return gong_di_zhi;
}

/**
 * 输出某一行限落宫的本命星情与三方四正。
 *
 *
 * 行限宫只改变当层的命宫起点，本命宫垣、安星与宫干仍以原盘为根；此处不

 * *
 * 将“同宫”直接写成吉凶断语，只列出需要同参的盘面依据。

 */
void display_limit_palace_context(const ZiWeiResult &result,
                                  string_view layer_name, int palace_index,
                                  const array<string, 4> &si_hua) {
  const auto &palace = result.get_palace_by_index(palace_index);
  const auto san_fang = get_san_fang_si_zheng(palace_index);

  vector<string> zhu_xing;
  vector<string> fu_xing;
  vector<string> sha_xing;
  for (const auto &star : palace.zhu_xing)
    zhu_xing.push_back(star.to_string());
  for (const auto &star : palace.fu_xing)
    fu_xing.push_back(star.to_string());
  for (const auto &star : palace.sha_xing)
    sha_xing.push_back(star.to_string());

  fmt::print("### {}\n", layer_name);
  fmt::print("- 行限命宫：{}{}（本命{}，寅起第{}宫）\n",
             string(GanZhi::Mapper::to_zh(palace.gong_data.tian_gan)),
             string(GanZhi::Mapper::to_zh(palace.gong_data.di_zhi)),
             string(to_zh(palace.gong_data.gong_wei)), palace_index);
  fmt::print("- 三方四正：本宫{}、对宫{}、三合{}、{}\n",
             string(to_zh(result.get_palace_by_index(san_fang.ben_gong_index)
                              .gong_data.gong_wei)),
             string(to_zh(result.get_palace_by_index(san_fang.dui_gong_index)
                              .gong_data.gong_wei)),
             string(to_zh(result.get_palace_by_index(san_fang.cai_bo_index)
                              .gong_data.gong_wei)),
             string(to_zh(result.get_palace_by_index(san_fang.guan_lu_index)
                              .gong_data.gong_wei)));
  if (palace.is_empty) {
    fmt::print("- 空宫借星：对宫第{}宫借{}\n", palace.opposite_index,
               fmt::join(palace.borrowed_stars, "、"));
  }
  if (!zhu_xing.empty())
    fmt::print("- 主星：{}\n", fmt::join(zhu_xing, "、"));
  if (!fu_xing.empty())
    fmt::print("- 辅曜：{}\n", fmt::join(fu_xing, "、"));
  if (!sha_xing.empty())
    fmt::print("- 煞曜：{}\n", fmt::join(sha_xing, "、"));

  vector<string_view> active_si_hua;
  for (const auto &star : si_hua) {
    if (!star.empty())
      active_si_hua.push_back(star);
  }
  if (!active_si_hua.empty())
    fmt::print("- 本层四化：{}\n", fmt::join(active_si_hua, "、"));
}

/** 返回目标公历日对应的流年月日干支，以避免各展示函数各自取值后失去联动。 */
struct TransitCycles {
  TianGan year_gan;
  DiZhi year_zhi;
  TianGan month_gan;
  DiZhi month_zhi;
  TianGan day_gan;
  DiZhi day_zhi;
  int lunar_month;
  int lunar_day;
};

TransitCycles resolve_transit_cycles(int target_year, int target_month,
                                     int target_day) {
  const auto solar_day =
      tyme::SolarDay::from_ymd(target_year, target_month, target_day);
  const auto lunar_day = solar_day.get_lunar_day();
  const auto sixty_cycle_day = solar_day.get_sixty_cycle_day();
  const auto year_cycle = sixty_cycle_day.get_year();
  const auto month_cycle = sixty_cycle_day.get_month();
  const auto day_cycle = sixty_cycle_day.get_sixty_cycle();
  return {
      .year_gan =
          static_cast<TianGan>(year_cycle.get_heaven_stem().get_index()),
      .year_zhi = static_cast<DiZhi>(year_cycle.get_earth_branch().get_index()),
      .month_gan =
          static_cast<TianGan>(month_cycle.get_heaven_stem().get_index()),
      .month_zhi =
          static_cast<DiZhi>(month_cycle.get_earth_branch().get_index()),
      .day_gan = static_cast<TianGan>(day_cycle.get_heaven_stem().get_index()),
      .day_zhi = static_cast<DiZhi>(day_cycle.get_earth_branch().get_index()),
      .lunar_month = lunar_day.get_lunar_month().get_month(),
      .lunar_day = lunar_day.get_day(),
  };
}
} // namespace

// ============= 四化分析功能实现 =============

void display_gong_gan_si_hua(const ZiWeiResult &result) {
  fmt::print("\n\n");
  fmt::print("       宫干四化分析\n");
  fmt::print("\n\n");

  auto gong_gan_zhi = prepare_gong_gan_zhi(result);
  auto stars_in_gong = prepare_stars_in_gong(result);

  SiHuaSystem si_hua_system(gong_gan_zhi, stars_in_gong);

  for (const auto &gong_si_hua : si_hua_system.get_all_gong_gan_si_hua()) {
    fmt::print("{}\n", gong_si_hua.to_string());
  }
}

void display_zi_hua_analysis(const ZiWeiResult &result) {
  fmt::print("\n\n");
  fmt::print("       自化分析\n");
  fmt::print("\n\n");

  auto gong_gan_zhi = prepare_gong_gan_zhi(result);
  auto stars_in_gong = prepare_stars_in_gong(result);

  SiHuaSystem si_hua_system(gong_gan_zhi, stars_in_gong);
  auto zi_hua_list = si_hua_system.get_all_zi_hua();

  if (zi_hua_list.empty()) {
    fmt::print("无自化现象\n");
  } else {
    for (const auto &zi_hua : zi_hua_list) {
      fmt::print("{}\n", zi_hua.to_string());
    }
  }
}

void display_fei_hua_analysis(const ZiWeiResult &result, int from_gong,
                              SiHua si_hua_type) {
  fmt::print("\n\n");
  fmt::print("       第{}宫 {} 飞化链分析\n", from_gong,
             string(to_zh(si_hua_type)));
  fmt::print("\n\n");

  auto gong_gan_zhi = prepare_gong_gan_zhi(result);
  auto stars_in_gong = prepare_stars_in_gong(result);

  SiHuaSystem si_hua_system(gong_gan_zhi, stars_in_gong);
  auto chains = si_hua_system.get_fei_hua_chains(from_gong, si_hua_type, 4);

  if (chains.empty()) {
    fmt::print("无飞化链\n");
  } else {
    for (const auto &chain : chains) {
      fmt::print("{}\n", chain.to_string());
    }
  }
}

// ============= 格局分析功能实现 =============

void display_ge_ju_analysis(const ZiWeiResult &result) {
  fmt::print("\n\n");
  fmt::print("       格局分析\n");
  fmt::print("\n\n");

  GeJuAnalyzer analyzer(prepare_pattern_chart(result));
  const auto all_patterns = analyzer.analyze_all();
  const int total_score = accumulate(
      all_patterns.begin(), all_patterns.end(), 0,
      [](int sum, const GeJuInfo &value) { return sum + value.score; });
  const auto ji_count = ranges::count(all_patterns, true, &GeJuInfo::is_ji);
  const auto xiong_count = ranges::count(all_patterns, false, &GeJuInfo::is_ji);

  fmt::print("【命盘总分】：{}\n\n", total_score);

  if (ji_count > 0) {
    fmt::print("【吉格】（共{}个）\n", ji_count);
    for (const auto &geju : all_patterns)
      if (geju.is_ji)
        fmt::print("  {}\n", geju.to_string());
    fmt::print("\n");
  }

  if (xiong_count > 0) {
    fmt::print("【凶格】（共{}个）\n", xiong_count);
    for (const auto &geju : all_patterns)
      if (!geju.is_ji)
        fmt::print("  {}\n", geju.to_string());
  }
}

void display_ji_ge(const ZiWeiResult &result) {
  GeJuAnalyzer analyzer(prepare_pattern_chart(result));
  auto ji_ge = analyzer.analyze_ji_ge();

  fmt::print("\n【吉格列表】（共{}个）\n", ji_ge.size());
  for (const auto &geju : ji_ge) {
    fmt::print("{}\n", geju.to_string());
  }
}

void display_xiong_ge(const ZiWeiResult &result) {
  GeJuAnalyzer analyzer(prepare_pattern_chart(result));
  auto xiong_ge = analyzer.analyze_xiong_ge();

  fmt::print("\n【凶格列表】（共{}个）\n", xiong_ge.size());
  for (const auto &geju : xiong_ge) {
    fmt::print("{}\n", geju.to_string());
  }
}

void display_shuang_xing_zu_he(const ZiWeiResult &result) {
  GeJuAnalyzer analyzer(prepare_pattern_chart(result));
  auto shuang_xing = analyzer.analyze_shuang_xing();

  fmt::print("\n【双星组合】（共{}组）\n", shuang_xing.size());
  for (const auto &zu_he : shuang_xing) {
    fmt::print("{}\n", zu_he.to_string());
  }
}

// ============= 三方四正分析功能实现 =============

void display_san_fang_si_zheng(const ZiWeiResult &result, GongWei gong_wei) {
  int gong_index = result.get_palace(gong_wei).gong_data.index;
  auto san_fang = get_san_fang_si_zheng(gong_index);

  fmt::print("\n\n");
  fmt::print("       {} 三方四正\n", string(to_zh(gong_wei)));
  fmt::print("\n\n");

  fmt::print("{}\n\n", san_fang.to_string());

  for (int idx : san_fang.get_all_indices()) {
    const auto &palace = result.palaces[idx];
    fmt::print("{}\n\n", palace.to_string());
  }
}

void display_kong_gong_jie_xing(const ZiWeiResult &result) {
  auto stars_in_gong = prepare_stars_in_gong(result);

  SanFangAnalyzer analyzer(stars_in_gong);
  auto kong_gong_list = analyzer.get_all_kong_gong();

  fmt::print("\n【空宫借星】（共{}个）\n", kong_gong_list.size());
  if (kong_gong_list.empty()) {
    fmt::print("无空宫\n");
  } else {
    for (const auto &kong_gong : kong_gong_list) {
      fmt::print("{}\n", kong_gong.to_string());
    }
  }
}

// ============= 星耀特性查询功能实现 =============

void display_star_info(const string &star_name) {
  auto doc = get_star_document(star_name);
  if (doc.has_value()) {
    fmt::print("\n{}\n", doc->to_string());
  } else {
    fmt::print("\n未找到星曜「{}」的详细信息\n", star_name);
  }
}

void display_tao_hua_xing() {
  // 交际类杂耀包含桃花星
  auto tao_hua_list = StarDoc::get_za_yao_by_category(ZaYaoCategory::JiaoJi);
  fmt::print("\n【桃花星列表】：{}\n", fmt::join(tao_hua_list, "、"));
}

void display_cai_xing() {
  // 财星主要是禄存、天马（二助星）
  auto cai_xing_list = StarDoc::get_fu_xing_by_category(FuXingCategory::ErZhu);
  fmt::print("\n【财星列表】：{}\n", fmt::join(cai_xing_list, "、"));
}

// ============= 运限分析功能实现 =============

void display_da_xian_analysis(const ZiWeiResult &result) {
  fmt::print("\n\n");
  fmt::print("       大限分析\n");
  fmt::print("\n\n");

  for (int i = 0; i < 12; ++i) {
    const auto &da_xian = result.da_xian_data[i];
    // 大限干支即该限宫的宫干支，与命盘同宫一致。
    fmt::print(
        "第{}限：{}岁-{}岁 - 大限宫位：第{}宫 {} {}{}\n", i + 1,
        da_xian.start_age, da_xian.end_age, da_xian.gong_index,
        string(to_zh(result.palaces[da_xian.gong_index].gong_data.gong_wei)),
        string(GanZhi::Mapper::to_zh(da_xian.tian_gan)),
        string(GanZhi::Mapper::to_zh(da_xian.di_zhi)));
  }
}

void display_xiao_xian_analysis(const ZiWeiResult &result, int current_age) {
  fmt::print("\n\n");
  fmt::print("       小限分析（{}岁）\n", current_age);
  fmt::print("\n\n");

  auto xiao_xian_data =
      get_xiao_xian(current_age, result.is_male, result.year_pillar.zhi);

  fmt::print(
      "小限宫位：第{}宫 {}\n", xiao_xian_data.gong_index,
      string(
          to_zh(result.palaces[xiao_xian_data.gong_index].gong_data.gong_wei)));

  fmt::print("\n{}\n", result.palaces[xiao_xian_data.gong_index].to_string());
}

void display_liu_nian_analysis(const ZiWeiResult &result, int target_year,
                               int current_age) {
  fmt::print("\n\n");
  fmt::print("       {}年流年分析（{}岁）\n", target_year, current_age);
  fmt::print("\n\n");

  // 流年干支只由目标干支年决定，与农历月日无关；统一走 get_horoscope，
  // 不再用公历 1 月 1 日反查节气年（1 月 1 日落在上一节气年，会取到上一年）。
  const HoroscopeResult horoscope =
      result.get_horoscope(target_year, 1, 1, DiZhi::Zi, current_age);
  const LiuNianData &liu_nian_data = horoscope.liu_nian;

  fmt::print("流年干支：{}{} ({}年)\n",
             string(GanZhi::Mapper::to_zh(liu_nian_data.tian_gan)),
             string(GanZhi::Mapper::to_zh(liu_nian_data.di_zhi)), target_year);
  fmt::print("流年宫位：第{}宫 {}\n", liu_nian_data.gong_index,
             string(to_zh(
                 result.palaces[liu_nian_data.gong_index].gong_data.gong_wei)));

  // 流年四化由运限模块按禄→权→科→忌给出，覆盖主星与文昌、文曲等辅星。
  fmt::print("\n流年四化：\n");
  for (const SiHuaEntry &entry : liu_nian_data.si_hua_entries)
    fmt::print("  {} - {}\n", string(to_zh(entry.hua)),
               string(to_zh(entry.xing)));

  fmt::print("\n流年宫位详情：\n");
  fmt::print("{}\n", result.palaces[liu_nian_data.gong_index].to_string());
}

void display_liu_yue_analysis(const ZiWeiResult &result, int target_year,
                              int target_month, int current_age) {
  fmt::print("\n\n");
  fmt::print("       {}年{}月流月分析（{}岁）\n", target_year, target_month,
             current_age);
  fmt::print("\n\n");

  // target_month 与 get_horoscope 同为农历月序；流月干支由该函数按五虎遁自
  // 流年干推出，不再用公历 15 日反查月柱。
  const HoroscopeResult horoscope = result.get_horoscope(
      target_year, target_month, 1, DiZhi::Zi, current_age);
  const LiuYueData &liu_yue_data = horoscope.liu_yue;

  fmt::print("流月干支：{}{} (农历{}月)\n",
             string(GanZhi::Mapper::to_zh(liu_yue_data.tian_gan)),
             string(GanZhi::Mapper::to_zh(liu_yue_data.di_zhi)),
             liu_yue_data.month);
  fmt::print("流月宫位：第{}宫 {}\n", liu_yue_data.gong_index,
             string(to_zh(
                 result.palaces[liu_yue_data.gong_index].gong_data.gong_wei)));

  // 流月四化由运限模块按禄→权→科→忌给出，覆盖主星与文昌、文曲等辅星。
  fmt::print("\n流月四化：\n");
  for (const SiHuaEntry &entry : liu_yue_data.si_hua_entries)
    fmt::print("  {} - {}\n", string(to_zh(entry.hua)),
               string(to_zh(entry.xing)));

  fmt::print("\n流月宫位详情：\n");
  fmt::print("{}\n", result.palaces[liu_yue_data.gong_index].to_string());
}

void display_liu_ri_analysis(const ZiWeiResult &result, int target_year,
                             int target_month, int target_day,
                             int current_age) {
  fmt::print("\n\n");
  fmt::print("       {}年{}月{}日流日分析（{}岁）\n", target_year, target_month,
             target_day, current_age);
  fmt::print("\n\n");

  // 农历月日与 get_horoscope 同一口径；流日干支直接取该日干支，不再按公历日
  // 反查，也不再借公历 15 日的月柱另算一套流月。
  const HoroscopeResult horoscope = result.get_horoscope(
      target_year, target_month, target_day, DiZhi::Zi, current_age);
  const LiuRiData &liu_ri_data = horoscope.liu_ri;

  fmt::print("流日干支：{}{} (农历{}日)\n",
             string(GanZhi::Mapper::to_zh(liu_ri_data.tian_gan)),
             string(GanZhi::Mapper::to_zh(liu_ri_data.di_zhi)),
             liu_ri_data.day);
  fmt::print(
      "流日宫位：第{}宫 {}\n", liu_ri_data.gong_index,
      string(to_zh(result.palaces[liu_ri_data.gong_index].gong_data.gong_wei)));

  // 流日四化由运限模块按禄→权→科→忌给出，覆盖主星与文昌、文曲等辅星。
  fmt::print("\n流日四化：\n");
  for (const SiHuaEntry &entry : liu_ri_data.si_hua_entries)
    fmt::print("  {} - {}\n", string(to_zh(entry.hua)),
               string(to_zh(entry.xing)));

  fmt::print("\n流日宫位详情：\n");
  fmt::print("{}\n", result.palaces[liu_ri_data.gong_index].to_string());
}

void display_liu_shi_analysis(const ZiWeiResult &result, int target_year,
                              int target_month, int target_day,
                              DiZhi target_hour, int current_age) {
  fmt::print("\n\n");
  fmt::print("       {}年{}月{}日 {} 流时分析（{}岁）\n", target_year,
             target_month, target_day, string(to_zh(target_hour)), current_age);
  fmt::print("\n\n");

  // 流时干支由 get_horoscope 按日干五鼠遁定出，与本命、大限、流年共用一份口径。
  const HoroscopeResult horoscope = result.get_horoscope(
      target_year, target_month, target_day, target_hour, current_age);
  const LiuShiData &liu_shi_data = horoscope.liu_shi;

  fmt::print("流时干支：{}{}\n",
             string(GanZhi::Mapper::to_zh(liu_shi_data.tian_gan)),
             string(GanZhi::Mapper::to_zh(liu_shi_data.di_zhi)));
  fmt::print("流时宫位：第{}宫 {}\n", liu_shi_data.gong_index,
             string(to_zh(
                 result.palaces[liu_shi_data.gong_index].gong_data.gong_wei)));

  // 流时四化由运限模块按禄→权→科→忌给出，覆盖主星与文昌、文曲等辅星。
  fmt::print("\n流时四化：\n");
  for (const SiHuaEntry &entry : liu_shi_data.si_hua_entries)
    fmt::print("  {} - {}\n", string(to_zh(entry.hua)),
               string(to_zh(entry.xing)));

  fmt::print("\n流时宫位详情：\n");
  fmt::print("{}\n", result.palaces[liu_shi_data.gong_index].to_string());
}

void display_yun_xian_full_analysis(const ZiWeiResult &result, int target_year,
                                    int target_month, int target_day,
                                    DiZhi target_hour, int current_age) {
  fmt::print("\n");
  fmt::print("\n");
  fmt::print("              完整运限分析报告                              \n");
  fmt::print("       {}年{}月{}日 {} （{}岁）            \n", target_year,
             target_month, target_day,
             string(GanZhi::Mapper::to_zh(target_hour)), current_age);
  fmt::print("\n");

  // 大限
  display_da_xian_analysis(result);

  // 小限
  display_xiao_xian_analysis(result, current_age);

  // 流年
  display_liu_nian_analysis(result, target_year, current_age);

  // 流月
  display_liu_yue_analysis(result, target_year, target_month, current_age);

  // 流日
  display_liu_ri_analysis(result, target_year, target_month, target_day,
                          current_age);

  // 流时
  display_liu_shi_analysis(result, target_year, target_month, target_day,
                           target_hour, current_age);

  // 命局是根、大限定十年趋势、流年定应期；月日时仅在指定时刻继续细分。
  // 联动区使用同一组干支计算，避免各段单独取月中日期后出现层级不一致。
  display_yun_xian_linkage_analysis(result, target_year, target_month,
                                    target_day, target_hour, current_age);

  fmt::print("\n");
  fmt::print("\n");
  fmt::print("       运限分析完成\n");
  fmt::print("\n");
}

void display_yun_xian_linkage_analysis(const ZiWeiResult &result,
                                       int target_year, int target_month,
                                       int target_day, DiZhi target_hour,
                                       int current_age) {
  const auto cycles =
      resolve_transit_cycles(target_year, target_month, target_day);
  const auto xiao_xian =
      get_xiao_xian(current_age, result.is_male, result.year_pillar.zhi);
  const auto liu_nian = get_liu_nian(target_year, cycles.year_gan,
                                     cycles.year_zhi, result.ming_gong_index);
  const int birth_month = result.month_pillar.zhi == DiZhi::Zi
                              ? 11
                              : static_cast<int>(result.month_pillar.zhi) - 1;
  const auto liu_yue =
      get_liu_yue(cycles.lunar_month, birth_month, cycles.month_gan,
                  cycles.month_zhi, cycles.year_zhi, result.ming_gong_index);
  const auto liu_ri = get_liu_ri(cycles.lunar_day, cycles.day_gan,
                                 cycles.day_zhi, liu_yue.gong_index);
  const int hour_gan_index = (static_cast<int>(cycles.day_gan) % 5 * 2 +
                              static_cast<int>(target_hour)) %
                             10;
  const auto liu_shi = get_liu_shi(
      target_hour, static_cast<TianGan>(hour_gan_index), liu_ri.gong_index);
  const auto da_xian = ranges::find_if(
      result.da_xian_data, [current_age](const DaXianData &item) {
        return current_age >= item.start_age && current_age <= item.end_age;
      });

  fmt::print("\n\n【命局—限年同参】\n");
  fmt::print("口径：命局为根，大限定十年章法，小限与流年定当年落宫；"
             "流月、流日、流时只作已指定时刻的层层细分。\n\n");

  if (da_xian != result.da_xian_data.end()) {
    display_limit_palace_context(result, "大限（十年）", da_xian->gong_index,
                                 da_xian->si_hua);
  }
  display_limit_palace_context(result, "小限（当岁）", xiao_xian.gong_index,
                               {});
  display_limit_palace_context(result, "流年（应期）", liu_nian.gong_index,
                               liu_nian.si_hua);
  display_limit_palace_context(result, "流月", liu_yue.gong_index,
                               liu_yue.si_hua);
  display_limit_palace_context(result, "流日", liu_ri.gong_index,
                               liu_ri.si_hua);
  display_limit_palace_context(result, "流时", liu_shi.gong_index,
                               liu_shi.si_hua);

  fmt::print("\n【层级交会】\n");
  const auto same_palace = [&result](string_view left, int left_index,
                                     string_view right, int right_index) {
    if (left_index != right_index)
      return;
    fmt::print("- {}与{}同临{}{}，该宫本命星系、四化与三方四正须并参。\n", left,
               right,
               string(GanZhi::Mapper::to_zh(
                   result.get_palace_by_index(left_index).gong_data.di_zhi)),
               string(to_zh(
                   result.get_palace_by_index(left_index).gong_data.gong_wei)));
  };
  if (da_xian != result.da_xian_data.end())
    same_palace("大限", da_xian->gong_index, "流年", liu_nian.gong_index);
  same_palace("小限", xiao_xian.gong_index, "流年", liu_nian.gong_index);
  same_palace("流年", liu_nian.gong_index, "流月", liu_yue.gong_index);
  same_palace("流月", liu_yue.gong_index, "流日", liu_ri.gong_index);
  same_palace("流日", liu_ri.gong_index, "流时", liu_shi.gong_index);
}

void display_ming_gong_star_analysis(const ZiWeiResult &result) {
  const auto &ming = result.get_palace_by_index(result.ming_gong_index);
  const string branch = string(GanZhi::Mapper::to_zh(ming.gong_data.di_zhi));

  fmt::print("\n【主星坐命解说】\n");
  fmt::print("命宫在{}宫；星性以十四主星为纲，同宫组合、庙陷、生年四化及三方"
             "四正为佐。\n\n",
             branch);

  if (ming.is_empty) {
    const auto &opposite = result.get_palace_by_index(ming.opposite_index);
    fmt::print("### 命宫空宫，借{}宫主星\n",
               string(GanZhi::Mapper::to_zh(opposite.gong_data.di_zhi)));
    fmt::print("命宫无十四主星，以对宫{}同参；仍须连同命宫三方四正、辅煞与四化"
               "取象。\n",
               fmt::join(ming.borrowed_stars, "、"));
    return;
  }

  vector<string> ming_stars;
  ming_stars.reserve(ming.zhu_xing.size());
  for (const auto &star : ming.zhu_xing)
    ming_stars.push_back(star.name);

  const auto ming_document =
      StarDoc::get_ming_gong_document(ming_stars, ming.gong_data.di_zhi);
  if (ming_document) {
    fmt::print("### {}\n", ming_document->title);
    fmt::print("- 守命纲领：{}\n", ming_document->summary);
    fmt::print("- 守命摘要：{}\n", ming_document->description);
    for (const auto &section : ming_document->sections)
      fmt::print("- {}：{}\n", section.title, section.content);
    fmt::print("\n");
  }

  for (const auto &star : ming.zhu_xing) {
    fmt::print("### {}坐{}守命\n", star.name, branch);
    const auto document = get_star_document(star.name);
    if (document) {
      const auto wu_xing = document->wu_xing ? string(to_zh(*document->wu_xing))
                                             : string("未载");
      const auto yin_yang = document->yin_yang
                                ? string(to_zh(*document->yin_yang))
                                : string("未载");
      fmt::print("- 星性：五行{}，{}；化气{}；职司{}。\n", wu_xing, yin_yang,
                 document->hua_qi, document->zhi_wu);
      if (!ming_document && !document->key_trait.empty())
        fmt::print("- 要旨：{}。\n", document->key_trait);
      if (!ming_document && !document->description.empty())
        fmt::print("- 主星本义：{}\n", document->description);
    }
    if (star.liang_du)
      fmt::print("- 入{}宫亮度：{}。\n", branch, string(to_zh(*star.liang_du)));
    if (star.si_hua)
      fmt::print("- 生年四化：{}{}。\n", star.name,
                 string(to_zh(*star.si_hua)));
    fmt::print("\n");
  }

  if (ming.zhu_xing.size() == 2) {
    const auto &first = ming.zhu_xing[0].name;
    const auto &second = ming.zhu_xing[1].name;
    if (const auto dual = StarDoc::get_dual_star_document(first, second)) {
      fmt::print("### {}同宫本义\n", dual->name);
      if (!dual->ge_ju.empty())
        fmt::print("- 格局称谓：{}。\n", dual->ge_ju);
      fmt::print("- 组合要旨：{}。\n", dual->key_trait);
      fmt::print("- 组合星情：{}\n\n", dual->description);
    }
  }
}

void display_ming_ju_focus_analysis(const ZiWeiResult &result) {
  fmt::print("\n【命局焦点宫】\n");
  fmt::print("口径：以宫垣主星为纲，辅煞为佐，连同庙陷、空宫、四化及三方"
             "四正列出需要同参的宫位；不以单一星曜直接定吉凶。\n\n");

  bool found_focus = false;
  for (int index = 0; index < 12; ++index) {
    const auto &palace = result.palaces[index];
    vector<string> causes;
    vector<string> bright_stars;
    vector<string> dim_stars;
    vector<string> transformed_stars;

    if (palace.is_empty)
      causes.push_back("空宫借对宫");
    if (!palace.sha_xing.empty())
      causes.push_back("煞曜同垣");

    const auto inspect_stars = [&](const vector<StarData> &stars) {
      for (const auto &star : stars) {
        if (is_bright(star.liang_du))
          bright_stars.push_back(star.name);
        if (is_dim(star.liang_du))
          dim_stars.push_back(star.name);
        if (star.si_hua)
          transformed_stars.push_back(star.name + string(to_zh(*star.si_hua)));
      }
    };
    inspect_stars(palace.zhu_xing);
    inspect_stars(palace.fu_xing);
    inspect_stars(palace.sha_xing);
    inspect_stars(palace.za_yao);

    if (!dim_stars.empty())
      causes.push_back("主辅曜见落陷");
    if (!transformed_stars.empty())
      causes.push_back("生年四化入宫");
    if (index == result.ming_gong_index)
      causes.push_back("命宫本位");
    if (index == result.shen_gong_index)
      causes.push_back("身宫同参");
    if (causes.empty())
      continue;

    found_focus = true;
    const auto san_fang = get_san_fang_si_zheng(index);
    fmt::print("- {}{} {}：{}\n",
               string(GanZhi::Mapper::to_zh(palace.gong_data.tian_gan)),
               string(GanZhi::Mapper::to_zh(palace.gong_data.di_zhi)),
               string(to_zh(palace.gong_data.gong_wei)),
               fmt::join(causes, "、"));
    if (!bright_stars.empty())
      fmt::print("  - 庙旺：{}\n", fmt::join(bright_stars, "、"));
    if (!dim_stars.empty())
      fmt::print("  - 落陷：{}\n", fmt::join(dim_stars, "、"));
    if (!transformed_stars.empty())
      fmt::print("  - 四化：{}\n", fmt::join(transformed_stars, "、"));
    fmt::print("  - 三方四正：{}、{}、{}、{}\n",
               string(to_zh(result.get_palace_by_index(san_fang.ben_gong_index)
                                .gong_data.gong_wei)),
               string(to_zh(result.get_palace_by_index(san_fang.dui_gong_index)
                                .gong_data.gong_wei)),
               string(to_zh(result.get_palace_by_index(san_fang.cai_bo_index)
                                .gong_data.gong_wei)),
               string(to_zh(result.get_palace_by_index(san_fang.guan_lu_index)
                                .gong_data.gong_wei)));
  }
  if (!found_focus)
    fmt::print(
        "- 未检出需单列的空宫、煞曜、庙陷或四化焦点；仍应按十二宫全盘参看。\n");
}

// ============= 综合分析功能实现 =============

void display_full_analysis(const ZiWeiResult &result) {
  fmt::print("\n");
  fmt::print("\n");
  fmt::print("              紫微斗数完整命盘分析报告                      \n");
  fmt::print("\n");

  // 基本信息
  fmt::print("\n【基本信息】\n");
  fmt::print("{}\n", result.to_string());

  // 格局分析
  display_ge_ju_analysis(result);

  // 主星守命为命局取象的起点；星情文本来自已登记的十四主星与双星文档。
  display_ming_gong_star_analysis(result);

  // 命局焦点宫：供后续大限、流年逐层同参。
  display_ming_ju_focus_analysis(result);

  // 四化分析
  display_gong_gan_si_hua(result);

  // 自化分析
  display_zi_hua_analysis(result);

  // 空宫分析
  display_kong_gong_jie_xing(result);

  // 大限分析
  display_da_xian_analysis(result);
}

string export_to_json_full(const ZiWeiResult &result) {
  json j = json::parse(export_to_json(result));

  auto stars_in_gong = prepare_stars_in_gong(result);
  auto gong_di_zhi = prepare_gong_di_zhi(result);
  auto gong_gan_zhi = prepare_gong_gan_zhi(result);

  // 添加格局信息
  GeJuAnalyzer ge_ju_analyzer(prepare_pattern_chart(result));
  const auto patterns = ge_ju_analyzer.analyze_all();

  const auto append_pattern = [&j](const GeJuInfo &geju, const char *group) {
    json g;
    g["name"] = geju.name;
    g["description"] = geju.description;
    g["score"] = geju.score;
    g["status"] = string(ZhouYi::Mapper::to_zh(geju.status));
    g["level"] = string(ZhouYi::Mapper::to_zh(geju.level));
    g["source"] = geju.source;
    g["geju_basis"]["required"] = geju.basis.required;
    g["geju_basis"]["bonus"] = geju.basis.bonus;
    g["geju_basis"]["breaking"] = geju.basis.breaking;
    g["key_stars"] = geju.key_stars;
    g["key_palaces"] = geju.key_gongs;
    j["ge_ju"][group].push_back(std::move(g));
  };

  j["ge_ju"]["ji_ge"] = json::array();
  j["ge_ju"]["xiong_ge"] = json::array();
  int total_score = 0;
  for (const auto &geju : patterns) {
    append_pattern(geju, geju.is_ji ? "ji_ge" : "xiong_ge");
    total_score += geju.score;
  }

  j["ge_ju"]["total_score"] = total_score;

  // 添加四化信息
  SiHuaSystem si_hua_system(gong_gan_zhi, stars_in_gong);
  j["si_hua"]["gong_gan_si_hua"] = json::array();
  for (const auto &gong_si_hua : si_hua_system.get_all_gong_gan_si_hua()) {
    json sh;
    sh["gong_index"] = gong_si_hua.gong_index;
    sh["gong_gan"] = string(to_zh(gong_si_hua.gong_gan));
    sh["si_hua_list"] = json::array();
    for (const auto &si_hua_info : gong_si_hua.si_hua_list) {
      if (!si_hua_info.star_name.empty()) {
        json info;
        info["star"] = si_hua_info.star_name;
        info["type"] = string(to_zh(si_hua_info.type));
        info["to_gong"] = si_hua_info.gong_index;
        sh["si_hua_list"].push_back(info);
      }
    }
    j["si_hua"]["gong_gan_si_hua"].push_back(sh);
  }

  auto zi_hua_list = si_hua_system.get_all_zi_hua();
  j["si_hua"]["zi_hua"] = json::array();
  for (const auto &zi_hua : zi_hua_list) {
    json zh;
    zh["gong_index"] = zi_hua.gong_index;
    zh["types"] = json::array();
    for (const auto &type : zi_hua.zi_hua_types) {
      zh["types"].push_back(string(to_zh(type)));
    }
    j["si_hua"]["zi_hua"].push_back(zh);
  }

  // 添加大限信息
  j["da_xian"] = json::array();
  for (const auto &dx : result.da_xian_data) {
    json dxj;
    dxj["start_age"] = dx.start_age;
    dxj["end_age"] = dx.end_age;
    dxj["gong_index"] = dx.gong_index;
    j["da_xian"].push_back(dxj);
  }

  return j.dump(2);
}

} // namespace ZhouYi::ZiWei
