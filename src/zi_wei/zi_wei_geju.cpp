// 紫微斗数格局判断系统模块（实现）
module ZhouYi.ZiWei.GeJu;

import ZhouYi.GanZhi;
import ZhouYi.ZiWei.Constants;
import ZhouYi.ZiWei.Pattern;
import ZhouYi.ZiWei.SanFang;
import fmt;
import std;
import ZhouYi.ZhMapper;

namespace ZhouYi::ZiWei {
using namespace std;

namespace {
constexpr initializer_list<PatternStar> hard_sha = {
    PatternStar::QingYang, PatternStar::TuoLuo, PatternStar::HuoXing,
    PatternStar::LingXing};

bool transformed(const PatternChart &chart, int palace, SiHua value,
                 bool major_only = false) {
  return chart.has_transformation(palace, value, major_only);
}

optional<PatternStar> transformed_star(const PatternChart &chart, int palace,
                                       SiHua value, bool major_only = false) {
  for (int i = 0; i < static_cast<int>(PatternStar::Count); ++i) {
    const auto star = static_cast<PatternStar>(i);
    const auto *state = chart.state(palace, star);
    if (state && (!major_only || state->kind == PatternStarKind::Major) &&
        state->transformation == value)
      return star;
  }
  return nullopt;
}

bool transformed_in_sanfang(const PatternChart &chart, SiHua value,
                            bool major_only = false) {
  const int ming = chart.ming_palace();
  return ranges::any_of(array{0, 4, 6, 8}, [&](int offset) {
    return transformed(chart, ming + offset, value, major_only);
  });
}

int hard_sha_in_palace(const PatternChart &chart, int palace) {
  return static_cast<int>(ranges::count_if(
      hard_sha, [&](auto star) { return chart.has(palace, star); }));
}

int hard_sha_in_sanfang(const PatternChart &chart) {
  return static_cast<int>(ranges::count_if(
      hard_sha, [&](auto star) { return chart.in_ming_sanfang(star); }));
}

GeJuInfo pattern(GeJuType type, string name, string description,
                 bool auspicious, int score, vector<PatternStar> stars,
                 vector<int> palaces, GeJuLevel level, GeJuBasis basis,
                 string source) {
  vector<string> names;
  names.reserve(stars.size());
  ranges::transform(stars, back_inserter(names),
                    [](auto star) { return string(pattern_star_name(star)); });
  const auto status = basis.breaking.empty()
                          ? GeJuStatus::Established
                          : (basis.breaking.size() >= 2 ? GeJuStatus::Broken
                                                        : GeJuStatus::Weakened);
  return {.type = type,
          .name = std::move(name),
          .description = std::move(description),
          .is_ji = auspicious,
          .score = score,
          .key_stars = std::move(names),
          .key_gongs = std::move(palaces),
          .status = status,
          .level = level,
          .basis = std::move(basis),
          .source = std::move(source)};
}
} // namespace

// ============= 辅助函数 =============

string GeJuInfo::to_string() const {
  string ji_xiong = is_ji ? "[吉格]" : "[凶格]";
  return fmt::format("{} {} [{}·{}] (分数:{}) - {}", ji_xiong, name,
                     ZhouYi::Mapper::to_zh(status),
                     ZhouYi::Mapper::to_zh(level), score, description);
}

string ShuangXingInfo::to_string() const {
  return fmt::format("{}+{} 在第{}宫 - {} ({})", xing1_name, xing2_name,
                     gong_index, xing_zhi, description);
}

// ============= GeJuAnalyzer 实现 =============

GeJuAnalyzer::GeJuAnalyzer(PatternChart chart)
    : chart_(std::move(chart)), ming_gong_index_(chart_.ming_palace()) {}

vector<GeJuInfo> GeJuAnalyzer::analyze_all() const {
  vector<GeJuInfo> all_geju;

  auto ji_ge = analyze_ji_ge();
  auto xiong_ge = analyze_xiong_ge();

  all_geju.insert(all_geju.end(), ji_ge.begin(), ji_ge.end());
  all_geju.insert(all_geju.end(), xiong_ge.begin(), xiong_ge.end());

  // 兼容既有规则的同时保证正式输出契约完整，并按格局名消除重叠规则。
  unordered_set<string> emitted;
  erase_if(all_geju, [&](GeJuInfo &value) {
    if (!emitted.insert(value.name).second)
      return true;
    if (value.basis.required.empty())
      value.basis.required.push_back("命盘满足“" + value.name + "”的成格条件");
    if (value.source.empty())
      value.source = "ZhouYiLab 传统格局规则库";
    if (value.level == GeJuLevel::Neutral) {
      if (!value.is_ji)
        value.level = GeJuLevel::Caution;
      else if (value.score >= 70)
        value.level = GeJuLevel::Excellent;
      else if (value.score >= 35)
        value.level = GeJuLevel::Good;
    }
    return false;
  });

  return all_geju;
}

vector<GeJuInfo> GeJuAnalyzer::analyze_ji_ge() const {
  vector<GeJuInfo> ji_ge_list;

  // 检查各种吉格
  if (auto geju = check_zi_fu_tong_gong(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_zi_fu_chao_yuan(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_tian_fu_chao_yuan(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_jun_chen_qing_hui(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_fu_xiang_chao_yuan(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_ji_yue_tong_liang(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_ji_liang_jia_hui(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_ri_yue_bing_ming(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_ri_zhao_lei_men(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_yue_lang_tian_men(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_ming_zhu_chu_hai(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_ri_yue_bo_zhao(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_yang_liang_chang_lu(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_tan_wu_tong_xing(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_tan_ling_jia_hui(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_huo_tan(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_ling_tan(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_san_qi_jia_hui(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_shuang_lu_jia_ming(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_shuang_lu_jia_cai(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_ke_quan_lu_jia(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_zuo_you_jia(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_chang_qu_jia_ming(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_chang_qu_tong_hui(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_kui_yue_jia_ming(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_lu_ma_jiao_chi(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_quan_lu_xun_feng(); geju.has_value()) {
    ji_ge_list.push_back(geju.value());
  }

  if (auto geju = check_ju_ri_tong_gong(); geju && geju->is_ji)
    ji_ge_list.push_back(std::move(*geju));

  // 参考实现对齐规则。保留独立规则名，避免用近似格局冒充。
  const array supplemental = {&GeJuAnalyzer::check_sha_po_lang,
                              &GeJuAnalyzer::check_lian_zhen_tian_xiang,
                              &GeJuAnalyzer::check_wu_qu_qi_sha,
                              &GeJuAnalyzer::check_tian_tong_tian_liang,
                              &GeJuAnalyzer::check_ri_yue_tong_gong,
                              &GeJuAnalyzer::check_ri_yue_jia_ming,
                              &GeJuAnalyzer::check_shi_zhong_yin_yu,
                              &GeJuAnalyzer::check_zi_wei_ru_ming,
                              &GeJuAnalyzer::check_hua_lu_ru_ming,
                              &GeJuAnalyzer::check_shuang_lu_chao_yuan,
                              &GeJuAnalyzer::check_lu_cun_shou_ming,
                              &GeJuAnalyzer::check_tian_ma_ru_ming,
                              &GeJuAnalyzer::check_hua_lu_ru_cai,
                              &GeJuAnalyzer::check_hua_quan_ru_guan,
                              &GeJuAnalyzer::check_hua_ke_ru_ming_shen,
                              &GeJuAnalyzer::check_ji_yue_tong_liang_partial,
                              &GeJuAnalyzer::check_fu_bi_tong_hui,
                              &GeJuAnalyzer::check_kui_yue_tong_hui,
                              &GeJuAnalyzer::check_ke_quan_shuang_hui};
  for (const auto rule : supplemental) {
    if (auto geju = (this->*rule)(); geju)
      ji_ge_list.push_back(std::move(*geju));
  }

  return ji_ge_list;
}

vector<GeJuInfo> GeJuAnalyzer::analyze_xiong_ge() const {
  vector<GeJuInfo> xiong_ge_list;

  if (auto geju = check_ling_chang_tuo_wu(); geju.has_value()) {
    xiong_ge_list.push_back(geju.value());
  }

  if (auto geju = check_ji_ji_tong_gong(); geju.has_value()) {
    xiong_ge_list.push_back(geju.value());
  }

  if (auto geju = check_ju_ri_tong_gong(); geju && !geju->is_ji)
    xiong_ge_list.push_back(std::move(*geju));

  if (auto geju = check_ma_tou_dai_jian(); geju.has_value()) {
    xiong_ge_list.push_back(geju.value());
  }

  if (auto geju = check_liang_ji_jia_ming(); geju.has_value()) {
    xiong_ge_list.push_back(geju.value());
  }

  if (auto geju = check_sha_xing_jia_ming(); geju.has_value()) {
    xiong_ge_list.push_back(geju.value());
  }

  if (auto geju = check_kong_jie_jia_ming(); geju.has_value()) {
    xiong_ge_list.push_back(geju.value());
  }

  if (auto geju = check_yang_tuo_jia_ji(); geju.has_value()) {
    xiong_ge_list.push_back(geju.value());
  }

  if (auto geju = check_si_sha_chong_ming(); geju.has_value()) {
    xiong_ge_list.push_back(geju.value());
  }

  if (auto geju = check_ming_gong_wu_zhu_xing(); geju.has_value()) {
    xiong_ge_list.push_back(geju.value());
  }

  const array supplemental = {&GeJuAnalyzer::check_hua_ji_ru_ming_qian,
                              &GeJuAnalyzer::check_lian_sha_yang,
                              &GeJuAnalyzer::check_ju_huo_yang};
  for (const auto rule : supplemental) {
    if (auto geju = (this->*rule)(); geju)
      xiong_ge_list.push_back(std::move(*geju));
  }

  return xiong_ge_list;
}

vector<ShuangXingInfo> GeJuAnalyzer::analyze_shuang_xing() const {
  vector<ShuangXingInfo> shuang_xing_list;

  for (int i = 0; i < 12; ++i) {
    auto zhu_xing = get_zhu_xing_in_gong(i);

    if (zhu_xing.size() == 2) {
      string xing1 = zhu_xing[0];
      string xing2 = zhu_xing[1];

      ShuangXingInfo info{.type = ShuangXingType::Unknown,
                          .xing1_name = xing1,
                          .xing2_name = xing2,
                          .gong_index = i,
                          .xing_zhi = "",
                          .description = ""};

      // 判断组合类型
      if ((xing1 == "紫微" && xing2 == "天府") ||
          (xing1 == "天府" && xing2 == "紫微")) {
        info.type = ShuangXingType::ZiWei_TianFu;
        info.xing_zhi = "帝王格";
        info.description = "紫微天府同宫，尊贵之格，主富贵双全";
      } else if ((xing1 == "紫微" && xing2 == "贪狼") ||
                 (xing1 == "贪狼" && xing2 == "紫微")) {
        info.type = ShuangXingType::ZiWei_TanLang;
        info.xing_zhi = "桃花犯主";
        info.description = "紫微贪狼，桃花犯主，利于交际";
      } else if ((xing1 == "紫微" && xing2 == "七杀") ||
                 (xing1 == "七杀" && xing2 == "紫微")) {
        info.type = ShuangXingType::ZiWei_QiSha;
        info.xing_zhi = "雄宿乾元";
        info.description = "紫微七杀，威权显赫，主将相之材";
      } else if ((xing1 == "武曲" && xing2 == "贪狼") ||
                 (xing1 == "贪狼" && xing2 == "武曲")) {
        info.type = ShuangXingType::WuQu_TanLang;
        info.xing_zhi = "财星会桃花";
        info.description = "武曲贪狼，先贫后富，晚年发达";
      } else if ((xing1 == "太阳" && xing2 == "太阴") ||
                 (xing1 == "太阴" && xing2 == "太阳")) {
        info.type = ShuangXingType::TaiYang_TaiYin;
        info.xing_zhi = "日月同辉";
        info.description = "太阳太阴，阴阳调和，富贵之格";
      }
      // 可以继续添加更多组合...

      // 未注册组合不输出占位记录，避免 Unknown 泄漏到展示层。
      if (info.type != ShuangXingType::Unknown)
        shuang_xing_list.push_back(std::move(info));
    }
  }

  return shuang_xing_list;
}

int GeJuAnalyzer::get_total_score() const {
  int total = 0;

  for (const auto &geju : analyze_all()) {
    total += geju.score;
  }

  return total;
}

// ============= 格局判断具体实现 =============

optional<GeJuInfo> GeJuAnalyzer::check_zi_fu_tong_gong() const {
  const auto zi_wei = chart_.find(PatternStar::ZiWei);
  const auto tian_fu = chart_.find(PatternStar::TianFu);
  if (!zi_wei || !tian_fu || *zi_wei != *tian_fu)
    return nullopt;

  const bool in_ming = *zi_wei == ming_gong_index_;
  GeJuBasis ge_ju_basis{
      {in_ming ? "紫微天府同入命宫" : "紫微天府同宫，会照层次减力"}, {}, {}};
  if (chart_.ming_sanfang_has_all({PatternStar::ZuoFu, PatternStar::YouBi}))
    ge_ju_basis.bonus.push_back("左辅右弼同会");
  if (chart_.has(*zi_wei, PatternStar::DiKong) ||
      chart_.has(*zi_wei, PatternStar::DiJie))
    ge_ju_basis.breaking.push_back("紫府宫坐空劫");
  if (hard_sha_in_palace(chart_, *zi_wei) >= 2)
    ge_ju_basis.breaking.push_back("紫府宫见双煞同坐");
  return pattern(GeJuType::ZiFuTongGong, "紫府同宫",
                 in_ming ? "紫微天府同入命宫，帝相并临。"
                         : "紫微天府同宫而未坐命，以会照之力论。",
                 true, in_ming ? 80 : 55,
                 {PatternStar::ZiWei, PatternStar::TianFu}, {*zi_wei},
                 in_ming ? GeJuLevel::Excellent : GeJuLevel::Good,
                 std::move(ge_ju_basis), "《紫微斗数全书·紫府同宫格》");
}

optional<GeJuInfo> GeJuAnalyzer::check_zi_fu_chao_yuan() const {
  // 紫微在命或财官，天府在三方四正
  if (gong_has_star(ming_gong_index_, "紫微") &&
      san_fang_has_star(ming_gong_index_, "天府")) {
    return GeJuInfo{.type = GeJuType::ZiFuChaoYuan,
                    .name = "紫府朝垣",
                    .description = "紫微天府相朝，主贵显荣华",
                    .is_ji = true,
                    .score = 70,
                    .key_stars = {"紫微", "天府"},
                    .key_gongs = {ming_gong_index_}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_tian_fu_chao_yuan() const {
  if (gong_has_star(ming_gong_index_, "天府") &&
      san_fang_has_any_star(ming_gong_index_, {"禄存", "化禄"})) {
    return GeJuInfo{.type = GeJuType::TianFuChaoYuan,
                    .name = "天府朝垣",
                    .description = "天府守命逢禄，富贵之格",
                    .is_ji = true,
                    .score = 65,
                    .key_stars = {"天府"},
                    .key_gongs = {ming_gong_index_}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_jun_chen_qing_hui() const {
  // 紫微为君，左右为辅弼之臣。
  if (gong_has_star(ming_gong_index_, "紫微") &&
      san_fang_has_all_stars(ming_gong_index_, {"左辅", "右弼"})) {
    return GeJuInfo{.type = GeJuType::JunChenQingHui,
                    .name = "君臣庆会",
                    .description = "紫微坐命，左辅右弼同会，君臣庆会",
                    .is_ji = true,
                    .score = 75,
                    .key_stars = {"紫微", "左辅", "右弼"},
                    .key_gongs = {ming_gong_index_}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_fu_xiang_chao_yuan() const {
  const auto tian_fu = chart_.find(PatternStar::TianFu);
  const auto tian_xiang = chart_.find(PatternStar::TianXiang);
  if (tian_fu && tian_xiang && *tian_fu != *tian_xiang &&
      chart_.in_ming_sanfang(PatternStar::TianFu) &&
      chart_.in_ming_sanfang(PatternStar::TianXiang)) {
    return GeJuInfo{.type = GeJuType::FuXiangChaoYuan,
                    .name = "府相朝垣",
                    .description = "天府天相分守命宫三方四正，府相朝垣",
                    .is_ji = true,
                    .score = 70,
                    .key_stars = {"天府", "天相"},
                    .key_gongs = {*tian_fu, *tian_xiang}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_ji_yue_tong_liang() const {
  // 天机、天同、天梁、太阴会于寅申巳亥宫
  vector<string> ji_yue_stars = {"天机", "太阴", "天同", "天梁"};

  if (san_fang_has_all_stars(ming_gong_index_, ji_yue_stars)) {
    return GeJuInfo{.type = GeJuType::JiYueTongLiang,
                    .name = "机月同梁",
                    .description =
                        "天机太阴天同天梁会合，清贵之格，适宜公职、学术",
                    .is_ji = true,
                    .score = 60,
                    .key_stars = ji_yue_stars,
                    .key_gongs = {ming_gong_index_}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_ri_yue_bing_ming() const {
  // 太阳太阴在卯酉宫
  if (gong_has_all_stars(ming_gong_index_, {"太阳", "太阴"})) {
    DiZhi di_zhi = get_gong_di_zhi(ming_gong_index_);
    // 检查是否在卯宫或酉宫
    if (di_zhi == DiZhi::Mao || di_zhi == DiZhi::You) {
      return GeJuInfo{.type = GeJuType::RiYueBingMing,
                      .name = "日月并明",
                      .description = "太阳太阴在卯酉宫，日月同辉，富贵之格",
                      .is_ji = true,
                      .score = 80,
                      .key_stars = {"太阳", "太阴"},
                      .key_gongs = {ming_gong_index_}};
    }
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_yue_lang_tian_men() const {
  // 太阴在亥宫
  if (gong_has_star(ming_gong_index_, "太阴")) {
    DiZhi di_zhi = get_gong_di_zhi(ming_gong_index_);
    // 检查是否在亥宫
    if (di_zhi == DiZhi::Hai) {
      return GeJuInfo{.type = GeJuType::YueLangTianMen,
                      .name = "月朗天门",
                      .description = "太阴居亥宫旺地，月朗天门，清贵之格",
                      .is_ji = true,
                      .score = 70,
                      .key_stars = {"太阴"},
                      .key_gongs = {ming_gong_index_}};
    }
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_ming_zhu_chu_hai() const {
  // 命在未而空，对宫丑见日月同宫。
  if (get_gong_di_zhi(ming_gong_index_) == DiZhi::Wei &&
      chart_.is_empty(ming_gong_index_)) {
    const int opposite = fix_index(ming_gong_index_ + 6);
    if (gong_has_all_stars(opposite, {"太阳", "太阴"})) {
      return GeJuInfo{.type = GeJuType::MingZhuChuHai,
                      .name = "明珠出海",
                      .description = "未宫空命，丑宫日月同照，明珠出海",
                      .is_ji = true,
                      .score = 65,
                      .key_stars = {"太阴"},
                      .key_gongs = {ming_gong_index_, opposite}};
    }
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_yang_liang_chang_lu() const {
  if (san_fang_has_all_stars(ming_gong_index_,
                             {"太阳", "天梁", "文昌", "禄存"})) {
    return GeJuInfo{.type = GeJuType::YangLiangChangLu,
                    .name = "阳梁昌禄",
                    .description = "太阳天梁会昌禄，清贵之格，主功名显赫",
                    .is_ji = true,
                    .score = 70,
                    .key_stars = {"太阳", "天梁", "文昌", "禄存"},
                    .key_gongs = {ming_gong_index_}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_tan_wu_tong_xing() const {
  const auto wu_qu = chart_.find(PatternStar::WuQu);
  const auto tan_lang = chart_.find(PatternStar::TanLang);
  if (!wu_qu || !tan_lang)
    return nullopt;
  const int delta = fix_index(*tan_lang - *wu_qu);
  if ((delta != 0 && delta != 6) ||
      (!chart_.in_ming_sanfang(PatternStar::WuQu) &&
       !chart_.in_ming_sanfang(PatternStar::TanLang)))
    return nullopt;

  GeJuBasis ge_ju_basis{{delta == 0 ? "武曲贪狼同宫" : "武曲贪狼对宫拱照",
                         "武曲贪狼会照命宫三方"},
                        {},
                        {}};
  if (chart_.ming_sanfang_has_any(
          {PatternStar::HuoXing, PatternStar::LingXing}))
    ge_ju_basis.bonus.push_back("再遇火星或铃星");
  if (chart_.has(*wu_qu, PatternStar::QingYang) ||
      chart_.has(*wu_qu, PatternStar::TuoLuo))
    ge_ju_basis.breaking.push_back("武贪宫见羊陀");
  if (chart_.has(*wu_qu, PatternStar::DiKong) ||
      chart_.has(*wu_qu, PatternStar::DiJie))
    ge_ju_basis.breaking.push_back("武贪宫遇空劫");
  return pattern(GeJuType::TanWuTongXing, "武贪格",
                 "武曲贪狼同宫或对宫拱照命垣，厚积而后发。", true, 65,
                 {PatternStar::WuQu, PatternStar::TanLang}, {*wu_qu, *tan_lang},
                 GeJuLevel::Excellent, std::move(ge_ju_basis),
                 "《紫微斗数骨髓赋》");
}

optional<GeJuInfo> GeJuAnalyzer::check_huo_tan() const {
  const auto tan = chart_.find(PatternStar::TanLang);
  const auto fire = chart_.find(PatternStar::HuoXing);
  const int delta = tan && fire ? fix_index(*fire - *tan) : -1;
  if (tan && fire && chart_.in_ming_sanfang(PatternStar::TanLang) &&
      (delta == 0 || delta == 4 || delta == 6 || delta == 8)) {
    return GeJuInfo{.type = GeJuType::HuoTanGeJu,
                    .name = "火贪格",
                    .description = "火星贪狼，突发之财，主横发",
                    .is_ji = true,
                    .score = 60,
                    .key_stars = {"贪狼", "火星"},
                    .key_gongs = {*tan, *fire}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_ling_tan() const {
  const auto tan = chart_.find(PatternStar::TanLang);
  const auto bell = chart_.find(PatternStar::LingXing);
  const int delta = tan && bell ? fix_index(*bell - *tan) : -1;
  if (tan && bell && chart_.in_ming_sanfang(PatternStar::TanLang) &&
      (delta == 0 || delta == 4 || delta == 6 || delta == 8)) {
    return GeJuInfo{.type = GeJuType::LingTanGeJu,
                    .name = "铃贪格",
                    .description = "铃星贪狼，暗发之财",
                    .is_ji = true,
                    .score = 55,
                    .key_stars = {"贪狼", "铃星"},
                    .key_gongs = {*tan, *bell}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_ji_liang_jia_hui() const {
  // 天机天梁在三方四正会合
  if ((gong_has_star(ming_gong_index_, "天机") &&
       san_fang_has_star(ming_gong_index_, "天梁")) ||
      (gong_has_star(ming_gong_index_, "天梁") &&
       san_fang_has_star(ming_gong_index_, "天机"))) {
    return GeJuInfo{.type = GeJuType::JiLiangJiaHui,
                    .name = "机梁夹会",
                    .description = "天机天梁会合，清贵之格，宜学术研究",
                    .is_ji = true,
                    .score = 60,
                    .key_stars = {"天机", "天梁"},
                    .key_gongs = {ming_gong_index_}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_ri_zhao_lei_men() const {
  // 太阳在卯宫
  if (gong_has_star(ming_gong_index_, "太阳")) {
    DiZhi di_zhi = get_gong_di_zhi(ming_gong_index_);
    if (di_zhi == DiZhi::Mao) {
      return GeJuInfo{.type = GeJuType::RiZhaoLeiMen,
                      .name = "日照雷门",
                      .description = "太阳居卯宫旺地，日照雷门，富贵之格",
                      .is_ji = true,
                      .score = 70,
                      .key_stars = {"太阳"},
                      .key_gongs = {ming_gong_index_}};
    }
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_ri_yue_bo_zhao() const {
  // 太阳太阴在三方夹照命宫
  auto san_fang = get_san_fang_si_zheng(ming_gong_index_);
  bool has_tai_yang = false;
  bool has_tai_yin = false;

  for (int gong : san_fang.get_all_indices()) {
    if (gong != ming_gong_index_) { // 不包括本宫
      if (gong_has_star(gong, "太阳"))
        has_tai_yang = true;
      if (gong_has_star(gong, "太阴"))
        has_tai_yin = true;
    }
  }

  if (has_tai_yang && has_tai_yin) {
    return GeJuInfo{.type = GeJuType::RiYueBoZhao,
                    .name = "日月夹照",
                    .description = "日月在三方夹照命宫，阴阳和合，富贵之格",
                    .is_ji = true,
                    .score = 65,
                    .key_stars = {"太阳", "太阴"},
                    .key_gongs = {ming_gong_index_}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_tan_ling_jia_hui() const {
  // 贪狼铃星在三方会合
  if ((gong_has_star(ming_gong_index_, "贪狼") &&
       san_fang_has_star(ming_gong_index_, "铃星")) ||
      (gong_has_star(ming_gong_index_, "铃星") &&
       san_fang_has_star(ming_gong_index_, "贪狼"))) {
    return GeJuInfo{.type = GeJuType::TanLingJiaHui,
                    .name = "贪铃夹会",
                    .description = "贪狼铃星会合，横发之财",
                    .is_ji = true,
                    .score = 55,
                    .key_stars = {"贪狼", "铃星"},
                    .key_gongs = {ming_gong_index_}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_san_qi_jia_hui() const {
  // 化禄、化权、化科会于三方四正；四化是星曜状态，不是独立星名。
  if (transformed_in_sanfang(chart_, SiHua::Lu) &&
      transformed_in_sanfang(chart_, SiHua::Quan) &&
      transformed_in_sanfang(chart_, SiHua::Ke)) {
    return GeJuInfo{.type = GeJuType::SanQiJiaHui,
                    .name = "三奇加会",
                    .description = "化禄化权化科会于三方四正，极贵之格",
                    .is_ji = true,
                    .score = 80,
                    .key_stars = {"化禄", "化权", "化科"},
                    .key_gongs = {ming_gong_index_}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_shuang_lu_jia_ming() const {
  auto jia_gong = get_jia_gong_info(ming_gong_index_);

  bool left_has_lu = gong_has_any_star(jia_gong.left_gong, {"禄存", "化禄"});
  bool right_has_lu = gong_has_any_star(jia_gong.right_gong, {"禄存", "化禄"});

  if (left_has_lu && right_has_lu) {
    return GeJuInfo{.type = GeJuType::ShuangLuJiaMing,
                    .name = "双禄夹命",
                    .description = "禄存化禄夹命，财禄丰厚",
                    .is_ji = true,
                    .score = 70,
                    .key_stars = {"禄存", "化禄"},
                    .key_gongs = {ming_gong_index_, jia_gong.left_gong,
                                  jia_gong.right_gong}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_shuang_lu_jia_cai() const {
  // 找到财帛宫
  auto san_fang = get_san_fang_si_zheng(ming_gong_index_);
  int cai_bo_gong = san_fang.cai_bo_index;

  auto jia_gong = get_jia_gong_info(cai_bo_gong);

  bool left_has_lu = gong_has_any_star(jia_gong.left_gong, {"禄存", "化禄"});
  bool right_has_lu = gong_has_any_star(jia_gong.right_gong, {"禄存", "化禄"});

  if (left_has_lu && right_has_lu) {
    return GeJuInfo{
        .type = GeJuType::ShuangLuJiaCai,
        .name = "双禄夹财",
        .description = "禄存化禄夹财帛宫，财源广进",
        .is_ji = true,
        .score = 68,
        .key_stars = {"禄存", "化禄"},
        .key_gongs = {cai_bo_gong, jia_gong.left_gong, jia_gong.right_gong}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_ke_quan_lu_jia() const {
  auto jia_gong = get_jia_gong_info(ming_gong_index_);

  // 检查左右宫是否有化科、化权、化禄中的任意两个
  vector<string> si_hua_list = {"化科", "化权", "化禄"};
  int count = 0;

  for (const auto &si_hua : si_hua_list) {
    if (gong_has_star(jia_gong.left_gong, si_hua) ||
        gong_has_star(jia_gong.right_gong, si_hua)) {
      count++;
    }
  }

  if (count >= 2) {
    return GeJuInfo{.type = GeJuType::KeQuanLuJia,
                    .name = "科权禄夹",
                    .description = "化科化权化禄夹命，权贵之格",
                    .is_ji = true,
                    .score = 75,
                    .key_stars = {"化科", "化权", "化禄"},
                    .key_gongs = {ming_gong_index_, jia_gong.left_gong,
                                  jia_gong.right_gong}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_zuo_you_jia() const {
  auto jia_gong = get_jia_gong_info(ming_gong_index_);

  bool left_has_zuo = gong_has_star(jia_gong.left_gong, "左辅");
  bool right_has_you = gong_has_star(jia_gong.right_gong, "右弼");
  bool left_has_you = gong_has_star(jia_gong.left_gong, "右弼");
  bool right_has_zuo = gong_has_star(jia_gong.right_gong, "左辅");

  if ((left_has_zuo && right_has_you) || (left_has_you && right_has_zuo)) {
    return GeJuInfo{.type = GeJuType::ZuoYouJiaMing,
                    .name = "左右夹命",
                    .description = "左辅右弼夹命，贵人扶持",
                    .is_ji = true,
                    .score = 65,
                    .key_stars = {"左辅", "右弼"},
                    .key_gongs = {ming_gong_index_, jia_gong.left_gong,
                                  jia_gong.right_gong}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_chang_qu_jia_ming() const {
  auto jia_gong = get_jia_gong_info(ming_gong_index_);

  bool left_has_chang = gong_has_star(jia_gong.left_gong, "文昌");
  bool right_has_qu = gong_has_star(jia_gong.right_gong, "文曲");
  bool left_has_qu = gong_has_star(jia_gong.left_gong, "文曲");
  bool right_has_chang = gong_has_star(jia_gong.right_gong, "文昌");

  if ((left_has_chang && right_has_qu) || (left_has_qu && right_has_chang)) {
    return GeJuInfo{.type = GeJuType::ChangQuJiaMing,
                    .name = "昌曲夹命",
                    .description = "文昌文曲夹命，主文名科甲",
                    .is_ji = true,
                    .score = 70,
                    .key_stars = {"文昌", "文曲"},
                    .key_gongs = {ming_gong_index_, jia_gong.left_gong,
                                  jia_gong.right_gong}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_chang_qu_tong_hui() const {
  const bool has_chang = san_fang_has_star(ming_gong_index_, "文昌");
  const bool has_qu = san_fang_has_star(ming_gong_index_, "文曲");
  if (!has_chang || !has_qu)
    return nullopt;

  return GeJuInfo{.type = GeJuType::ChangQuTongHui,
                  .name = "昌曲同会",
                  .description =
                      "文昌、文曲同会命宫三方四正，主才学、文章与名声",
                  .is_ji = true,
                  .score = 35,
                  .key_stars = {"文昌", "文曲"},
                  .key_gongs = {ming_gong_index_}};
}

optional<GeJuInfo> GeJuAnalyzer::check_kui_yue_jia_ming() const {
  auto jia_gong = get_jia_gong_info(ming_gong_index_);

  bool left_has_kui = gong_has_star(jia_gong.left_gong, "天魁");
  bool right_has_yue = gong_has_star(jia_gong.right_gong, "天钺");
  bool left_has_yue = gong_has_star(jia_gong.left_gong, "天钺");
  bool right_has_kui = gong_has_star(jia_gong.right_gong, "天魁");

  if ((left_has_kui && right_has_yue) || (left_has_yue && right_has_kui)) {
    return GeJuInfo{.type = GeJuType::KuiYueJiaMing,
                    .name = "魁钺夹命",
                    .description = "天魁天钺夹命，贵人提携，主显贵",
                    .is_ji = true,
                    .score = 72,
                    .key_stars = {"天魁", "天钺"},
                    .key_gongs = {ming_gong_index_, jia_gong.left_gong,
                                  jia_gong.right_gong}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_lu_ma_jiao_chi() const {
  // 禄存天马同宫或会合
  if (gong_has_all_stars(ming_gong_index_, {"禄存", "天马"}) ||
      (gong_has_star(ming_gong_index_, "禄存") &&
       san_fang_has_star(ming_gong_index_, "天马")) ||
      (gong_has_star(ming_gong_index_, "天马") &&
       san_fang_has_star(ming_gong_index_, "禄存"))) {
    return GeJuInfo{.type = GeJuType::LuMaJiaoChiGeJu,
                    .name = "禄马交驰",
                    .description = "禄存天马交会，主富贵双全，利经商远行",
                    .is_ji = true,
                    .score = 68,
                    .key_stars = {"禄存", "天马"},
                    .key_gongs = {ming_gong_index_}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_quan_lu_xun_feng() const {
  // 化权化禄在命宫或三方四正
  if (san_fang_has_all_stars(ming_gong_index_, {"化权", "化禄"})) {
    return GeJuInfo{.type = GeJuType::QuanLuXunFeng,
                    .name = "权禄巡逢",
                    .description = "化权化禄巡逢，主权贵富足",
                    .is_ji = true,
                    .score = 70,
                    .key_stars = {"化权", "化禄"},
                    .key_gongs = {ming_gong_index_}};
  }

  return nullopt;
}

// ============= 凶格判断 =============

optional<GeJuInfo> GeJuAnalyzer::check_ling_chang_tuo_wu() const {
  if (san_fang_has_all_stars(ming_gong_index_,
                             {"铃星", "文昌", "陀罗", "武曲"})) {
    return GeJuInfo{.type = GeJuType::LingChangTuoWu,
                    .name = "铃昌陀武",
                    .description = "铃星文昌陀罗武曲同宫，大凶之格，易遭刑克",
                    .is_ji = false,
                    .score = -70,
                    .key_stars = {"铃星", "文昌", "陀罗", "武曲"},
                    .key_gongs = {ming_gong_index_}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_ji_ji_tong_gong() const {
  if (gong_has_all_stars(ming_gong_index_, {"巨门", "天机"})) {
    DiZhi di_zhi = get_gong_di_zhi(ming_gong_index_);
    // 检查是否在辰宫或戌宫
    if (di_zhi == DiZhi::Chen || di_zhi == DiZhi::Xu) {
      return GeJuInfo{.type = GeJuType::JiJiTongGong,
                      .name = "巨机同宫",
                      .description = "巨门天机同宫于辰戌，口舌是非多，劳碌奔波",
                      .is_ji = false,
                      .score = -50,
                      .key_stars = {"巨门", "天机"},
                      .key_gongs = {ming_gong_index_}};
    }
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_ma_tou_dai_jian() const {
  // 擎羊在午宫守命
  if (gong_has_star(ming_gong_index_, "擎羊")) {
    DiZhi di_zhi = get_gong_di_zhi(ming_gong_index_);
    // 检查是否在午宫
    if (di_zhi == DiZhi::Wu) {
      return GeJuInfo{.type = GeJuType::MaTouDaiJian,
                      .name = "马头带箭",
                      .description =
                          "擎羊居午宫守命，马头带箭，刑克严重，易有血光之灾",
                      .is_ji = false,
                      .score = -65,
                      .key_stars = {"擎羊"},
                      .key_gongs = {ming_gong_index_}};
    }
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_sha_xing_jia_ming() const {
  const auto adjacent = chart_.ming_adjacent();
  const bool matched = (chart_.has(adjacent[0], PatternStar::HuoXing) &&
                        chart_.has(adjacent[1], PatternStar::LingXing)) ||
                       (chart_.has(adjacent[1], PatternStar::HuoXing) &&
                        chart_.has(adjacent[0], PatternStar::LingXing));
  if (matched) {
    return GeJuInfo{.type = GeJuType::HuoLingJiaMing,
                    .name = "火铃夹命",
                    .description = "火星铃星分居命宫前后两宫夹命",
                    .is_ji = false,
                    .score = -55,
                    .key_stars = {"火星", "铃星"},
                    .key_gongs = {ming_gong_index_, adjacent[0], adjacent[1]}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_kong_jie_jia_ming() const {
  auto jia_gong = get_jia_gong_info(ming_gong_index_);

  bool left_has_kong = gong_has_star(jia_gong.left_gong, "地空");
  bool right_has_jie = gong_has_star(jia_gong.right_gong, "地劫");
  bool left_has_jie = gong_has_star(jia_gong.left_gong, "地劫");
  bool right_has_kong = gong_has_star(jia_gong.right_gong, "地空");

  if ((left_has_kong && right_has_jie) || (left_has_jie && right_has_kong)) {
    return GeJuInfo{.type = GeJuType::KongJieJiaMing,
                    .name = "空劫夹命",
                    .description = "地空地劫夹命，财运不聚，理想高远",
                    .is_ji = false,
                    .score = -50,
                    .key_stars = {"地空", "地劫"},
                    .key_gongs = {ming_gong_index_, jia_gong.left_gong,
                                  jia_gong.right_gong}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_ju_ri_tong_gong() const {
  const auto ju = chart_.find(PatternStar::JuMen);
  const auto sun = chart_.find(PatternStar::TaiYang);
  if (!ju || ju != sun)
    return nullopt;
  const auto branch = chart_.at(*ju).branch;
  if (branch != DiZhi::Yin && branch != DiZhi::Shen)
    return nullopt;
  GeJuBasis ge_ju_basis{
      {branch == DiZhi::Yin ? "巨日同入寅宫" : "巨日同入申宫"}, {}, {}};
  const auto *state = chart_.state(*ju, PatternStar::JuMen);
  if (state && (state->transformation == SiHua::Lu ||
                state->transformation == SiHua::Quan))
    ge_ju_basis.bonus.push_back("巨门化禄或化权");
  if (state && state->transformation == SiHua::Ji)
    ge_ju_basis.breaking.push_back("巨门化忌");
  if (branch == DiZhi::Shen)
    ge_ju_basis.breaking.push_back("申宫太阳偏西，格力减弱");
  const bool favorable = ge_ju_basis.breaking.empty();
  return pattern(GeJuType::JuRiTongGong, "巨日同宫",
                 "巨门太阳同宫，以口才、传播与专业能力取用；寅优于申。",
                 favorable, favorable ? 40 : -25,
                 {PatternStar::JuMen, PatternStar::TaiYang}, {*ju},
                 favorable ? GeJuLevel::Excellent : GeJuLevel::Caution,
                 std::move(ge_ju_basis), "《紫微斗数全书·巨日同宫》");
}

optional<GeJuInfo> GeJuAnalyzer::check_ming_xiang_liang_jia() const {
  // 命宫无正曜（空宫）
  auto zhu_xing = get_zhu_xing_in_gong(ming_gong_index_);

  if (zhu_xing.empty()) {
    return GeJuInfo{.type = GeJuType::MingXiangLiangJia,
                    .name = "命无正曜",
                    .description = "命宫空宫无主星，需借对宫之星，命运多变",
                    .is_ji = false,
                    .score = -30,
                    .key_stars = {},
                    .key_gongs = {ming_gong_index_}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_liang_ji_jia_ming() const {
  auto jia_gong = get_jia_gong_info(ming_gong_index_);

  bool left_has_yang = gong_has_star(jia_gong.left_gong, "擎羊");
  bool right_has_tuo = gong_has_star(jia_gong.right_gong, "陀罗");
  bool left_has_tuo = gong_has_star(jia_gong.left_gong, "陀罗");
  bool right_has_yang = gong_has_star(jia_gong.right_gong, "擎羊");

  if ((left_has_yang && right_has_tuo) || (left_has_tuo && right_has_yang)) {
    return GeJuInfo{.type = GeJuType::LiangJiJiaMing,
                    .name = "羊陀夹命",
                    .description = "擎羊陀罗夹命，进退失据，劳碌辛苦",
                    .is_ji = false,
                    .score = -60,
                    .key_stars = {"擎羊", "陀罗"},
                    .key_gongs = {ming_gong_index_, jia_gong.left_gong,
                                  jia_gong.right_gong}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_yang_tuo_jia_ji() const {
  // 化忌被擎羊陀罗夹
  auto jia_gong = get_jia_gong_info(ming_gong_index_);

  bool ming_has_ji = gong_has_star(ming_gong_index_, "化忌");
  bool left_has_yang_or_tuo =
      gong_has_any_star(jia_gong.left_gong, {"擎羊", "陀罗"});
  bool right_has_yang_or_tuo =
      gong_has_any_star(jia_gong.right_gong, {"擎羊", "陀罗"});

  if (ming_has_ji && left_has_yang_or_tuo && right_has_yang_or_tuo) {
    return GeJuInfo{.type = GeJuType::YangTuoJiaJi,
                    .name = "羊陀夹忌",
                    .description = "化忌被羊陀夹持，凶上加凶，灾祸频生",
                    .is_ji = false,
                    .score = -70,
                    .key_stars = {"擎羊", "陀罗", "化忌"},
                    .key_gongs = {ming_gong_index_, jia_gong.left_gong,
                                  jia_gong.right_gong}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_si_sha_chong_ming() const {
  // 四煞（擎羊陀罗火星铃星）在三方四正冲照命宫
  int sha_count = 0;
  vector<string> si_sha = {"擎羊", "陀罗", "火星", "铃星"};

  for (const auto &sha : si_sha) {
    if (san_fang_has_star(ming_gong_index_, sha)) {
      sha_count++;
    }
  }

  if (sha_count >= 3) {
    return GeJuInfo{.type = GeJuType::SiShaChongMing,
                    .name = "四煞冲命",
                    .description = "四煞聚会冲命，劳碌奔波，多灾多难",
                    .is_ji = false,
                    .score = -65,
                    .key_stars = si_sha,
                    .key_gongs = {ming_gong_index_}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_ming_gong_wu_zhu_xing() const {
  // 与check_ming_xiang_liang_jia类似，但更侧重于无主星的情况
  auto zhu_xing = get_zhu_xing_in_gong(ming_gong_index_);

  if (zhu_xing.empty()) {
    return GeJuInfo{.type = GeJuType::MinggongWuZhuXing,
                    .name = "命宫无主星",
                    .description = "命宫无正曜主星，格局较弱，需借对宫",
                    .is_ji = false,
                    .score = -25,
                    .key_stars = {},
                    .key_gongs = {ming_gong_index_}};
  }

  return nullopt;
}

optional<GeJuInfo> GeJuAnalyzer::check_sha_po_lang() const {
  if (!chart_.ming_sanfang_has_all(
          {PatternStar::QiSha, PatternStar::PoJun, PatternStar::TanLang}))
    return nullopt;
  GeJuBasis ge_ju_basis{{"七杀、破军、贪狼齐入命宫三方四正"}, {}, {}};
  if (transformed_in_sanfang(chart_, SiHua::Lu) ||
      transformed_in_sanfang(chart_, SiHua::Quan))
    ge_ju_basis.bonus.push_back("三方见化禄或化权");
  if (hard_sha_in_sanfang(chart_) >= 3)
    ge_ju_basis.breaking.push_back("三方四煞过重，动而难成");
  if (chart_.has(ming_gong_index_, PatternStar::DiKong) ||
      chart_.has(ming_gong_index_, PatternStar::DiJie))
    ge_ju_basis.breaking.push_back("命宫坐空劫，开创过程多反复");
  return pattern(GeJuType::ShaPoLang, "杀破狼",
                 "杀破狼三星会命，主开创、变动与突破，须兼察吉煞制化。", true,
                 45,
                 {PatternStar::QiSha, PatternStar::PoJun, PatternStar::TanLang},
                 {ming_gong_index_}, GeJuLevel::Good, std::move(ge_ju_basis),
                 "《紫微斗数全书·杀破狼》");
}

optional<GeJuInfo> GeJuAnalyzer::check_lian_zhen_tian_xiang() const {
  const auto lian = chart_.find(PatternStar::LianZhen);
  const auto xiang = chart_.find(PatternStar::TianXiang);
  if (!lian || lian != xiang)
    return nullopt;
  GeJuBasis ge_ju_basis{{"廉贞、天相同宫"}, {}, {}};
  if (transformed(chart_, *lian, SiHua::Lu) ||
      chart_.has(*lian, PatternStar::LuCun))
    ge_ju_basis.bonus.push_back("廉贞化禄或同宫见禄存");
  if (transformed(chart_, *lian, SiHua::Ji))
    ge_ju_basis.breaking.push_back("廉贞化忌");
  if (chart_.has(*lian, PatternStar::QingYang))
    ge_ju_basis.breaking.push_back("廉相同宫见擎羊");
  return pattern(GeJuType::LianZhenTianXiang, "廉贞天相格",
                 "廉贞天相同宫，重在秩序、行政与权责，见忌煞则减力。", true, 40,
                 {PatternStar::LianZhen, PatternStar::TianXiang}, {*lian},
                 ge_ju_basis.breaking.empty() ? GeJuLevel::Good
                                              : GeJuLevel::Caution,
                 std::move(ge_ju_basis), "《紫微斗数全书》");
}

optional<GeJuInfo> GeJuAnalyzer::check_wu_qu_qi_sha() const {
  const auto wu = chart_.find(PatternStar::WuQu);
  if (!wu || wu != chart_.find(PatternStar::QiSha))
    return nullopt;
  GeJuBasis ge_ju_basis{{"武曲、七杀同宫"}, {}, {}};
  const auto *state = chart_.state(*wu, PatternStar::WuQu);
  if (state && (state->transformation == SiHua::Lu ||
                state->transformation == SiHua::Quan))
    ge_ju_basis.bonus.push_back("武曲化禄或化权");
  if (state && state->transformation == SiHua::Ji)
    ge_ju_basis.breaking.push_back("武曲化忌");
  if (hard_sha_in_palace(chart_, *wu) > 0)
    ge_ju_basis.breaking.push_back("武杀宫再见四煞");
  return pattern(GeJuType::WuQuQiSha, "武曲七杀",
                 "武曲七杀同宫，财星配将星，主果决开创；忌化忌与煞重。", true,
                 45, {PatternStar::WuQu, PatternStar::QiSha}, {*wu},
                 ge_ju_basis.breaking.empty() ? GeJuLevel::Good
                                              : GeJuLevel::Caution,
                 std::move(ge_ju_basis), "《紫微斗数全书》");
}

optional<GeJuInfo> GeJuAnalyzer::check_tian_tong_tian_liang() const {
  const auto tong = chart_.find(PatternStar::TianTong);
  if (!tong || tong != chart_.find(PatternStar::TianLiang))
    return nullopt;
  GeJuBasis ge_ju_basis{{"天同、天梁同宫"}, {}, {}};
  if (chart_.in_ming_sanfang(PatternStar::WenChang))
    ge_ju_basis.bonus.push_back("文昌会照");
  if (hard_sha_in_palace(chart_, *tong) > 0)
    ge_ju_basis.breaking.push_back("同梁宫见四煞");
  return pattern(GeJuType::TianTongTianLiang, "天同天梁格",
                 "天同天梁同宫，主荫庇、服务与清贵，见煞则安逸之性受损。", true,
                 35, {PatternStar::TianTong, PatternStar::TianLiang}, {*tong},
                 GeJuLevel::Good, std::move(ge_ju_basis), "《紫微斗数全书》");
}

optional<GeJuInfo> GeJuAnalyzer::check_ri_yue_tong_gong() const {
  const auto sun = chart_.find(PatternStar::TaiYang);
  if (!sun || sun != chart_.find(PatternStar::TaiYin))
    return nullopt;
  const auto branch = chart_.at(*sun).branch;
  if (branch != DiZhi::Chou && branch != DiZhi::Wei)
    return nullopt;
  GeJuBasis ge_ju_basis{{"太阳、太阴同宫于丑或未"}, {}, {}};
  const auto *sun_state = chart_.state(*sun, PatternStar::TaiYang);
  const auto *moon_state = chart_.state(*sun, PatternStar::TaiYin);
  if ((sun_state && is_bright(sun_state->brightness)) ||
      (moon_state && is_bright(moon_state->brightness)))
    ge_ju_basis.bonus.push_back("日月至少一曜庙旺");
  if ((sun_state && is_dim(sun_state->brightness)) &&
      (moon_state && is_dim(moon_state->brightness)))
    ge_ju_basis.breaking.push_back("日月俱陷");
  return pattern(GeJuType::RiYueTongGong, "日月同宫",
                 "太阳太阴同居丑未，阴阳并临，格局高下须结合亮度与煞曜。", true,
                 40, {PatternStar::TaiYang, PatternStar::TaiYin}, {*sun},
                 GeJuLevel::Good, std::move(ge_ju_basis), "《紫微斗数全书》");
}

optional<GeJuInfo> GeJuAnalyzer::check_ri_yue_jia_ming() const {
  const auto adjacent = chart_.ming_adjacent();
  const bool matched = (chart_.has(adjacent[0], PatternStar::TaiYang) &&
                        chart_.has(adjacent[1], PatternStar::TaiYin)) ||
                       (chart_.has(adjacent[1], PatternStar::TaiYang) &&
                        chart_.has(adjacent[0], PatternStar::TaiYin));
  if (!matched)
    return nullopt;
  return pattern(GeJuType::RiYueJiaMing, "日月夹命",
                 "太阳太阴分居命宫两邻宫夹命。", true, 45,
                 {PatternStar::TaiYang, PatternStar::TaiYin},
                 {ming_gong_index_, adjacent[0], adjacent[1]}, GeJuLevel::Good,
                 {{"太阳、太阴分居命宫左右夹宫"}, {}, {}}, "《紫微斗数全书》");
}

optional<GeJuInfo> GeJuAnalyzer::check_shi_zhong_yin_yu() const {
  if (!chart_.has(ming_gong_index_, PatternStar::JuMen))
    return nullopt;
  const auto branch = chart_.at(ming_gong_index_).branch;
  if (branch != DiZhi::Zi && branch != DiZhi::Wu)
    return nullopt;
  GeJuBasis ge_ju_basis{{"巨门守命于子或午"}, {}, {}};
  if (transformed(chart_, ming_gong_index_, SiHua::Lu) ||
      transformed(chart_, ming_gong_index_, SiHua::Quan))
    ge_ju_basis.bonus.push_back("巨门得禄权");
  if (transformed(chart_, ming_gong_index_, SiHua::Ji))
    ge_ju_basis.breaking.push_back("巨门化忌");
  return pattern(GeJuType::ShiZhongYinYu, "石中隐玉",
                 "巨门在子午守命，如玉藏石中，宜经磨炼后显达。", true, 35,
                 {PatternStar::JuMen}, {ming_gong_index_}, GeJuLevel::Good,
                 std::move(ge_ju_basis), "《紫微斗数全书·石中隐玉格》");
}

optional<GeJuInfo> GeJuAnalyzer::check_zi_wei_ru_ming() const {
  if (!chart_.has(ming_gong_index_, PatternStar::ZiWei))
    return nullopt;
  GeJuBasis ge_ju_basis{{"紫微入命"}, {}, {}};
  if (chart_.ming_sanfang_has_all({PatternStar::ZuoFu, PatternStar::YouBi}))
    ge_ju_basis.bonus.push_back("左右同会");
  if (chart_.has(ming_gong_index_, PatternStar::DiKong) ||
      chart_.has(ming_gong_index_, PatternStar::DiJie))
    ge_ju_basis.breaking.push_back("命宫同坐空劫");
  return pattern(GeJuType::ZiWeiRuMing, "紫微入命",
                 "紫微坐命，以辅弼吉煞定其格局层次。", true, 25,
                 {PatternStar::ZiWei}, {ming_gong_index_}, GeJuLevel::Neutral,
                 std::move(ge_ju_basis), "《紫微斗数全书》");
}

optional<GeJuInfo> GeJuAnalyzer::check_hua_lu_ru_ming() const {
  const auto star = transformed_star(chart_, ming_gong_index_, SiHua::Lu, true);
  if (!star)
    return nullopt;
  const string name = string(pattern_star_name(*star)) + "化禄入命";
  return pattern(GeJuType::HuaLuRuMing, name, "生年化禄随原星入命。", true, 25,
                 {*star}, {ming_gong_index_}, GeJuLevel::Good, {{name}, {}, {}},
                 "生年四化格局");
}

optional<GeJuInfo> GeJuAnalyzer::check_shuang_lu_chao_yuan() const {
  if (!chart_.in_ming_sanfang(PatternStar::LuCun) ||
      !transformed_in_sanfang(chart_, SiHua::Lu))
    return nullopt;
  GeJuBasis ge_ju_basis{{"禄存会命宫三方四正", "化禄会命宫三方四正"}, {}, {}};
  if (chart_.has(ming_gong_index_, PatternStar::DiKong) ||
      chart_.has(ming_gong_index_, PatternStar::DiJie))
    ge_ju_basis.breaking.push_back("命坐空劫，双禄聚财之力减弱");
  return pattern(GeJuType::ShuangLuChaoYuan, "双禄朝垣",
                 "禄存与生年化禄同会命宫三方四正。", true, 45,
                 {PatternStar::LuCun}, {ming_gong_index_}, GeJuLevel::Excellent,
                 std::move(ge_ju_basis), "《紫微斗数全书·双禄朝垣》");
}

optional<GeJuInfo> GeJuAnalyzer::check_hua_ji_ru_ming_qian() const {
  const int opposite = fix_index(ming_gong_index_ + 6);
  const auto in_ming =
      transformed_star(chart_, ming_gong_index_, SiHua::Ji, true);
  const auto in_migration = transformed_star(chart_, opposite, SiHua::Ji, true);
  if (!in_ming && !in_migration)
    return nullopt;
  const auto star = in_ming ? *in_ming : *in_migration;
  const string name =
      string(pattern_star_name(star)) + "化忌入" + (in_ming ? "命" : "迁");
  return pattern(GeJuType::HuaJiRuMingQian, name,
                 "生年化忌落命宫或迁移宫，须结合原星庙陷与吉曜制化。", false,
                 -35, {star}, {in_ming ? ming_gong_index_ : opposite},
                 GeJuLevel::Caution, {{name}, {}, {}}, "生年四化格局");
}

optional<GeJuInfo> GeJuAnalyzer::check_lian_sha_yang() const {
  if (!chart_.ming_sanfang_has_all(
          {PatternStar::LianZhen, PatternStar::QiSha, PatternStar::QingYang}))
    return nullopt;
  return pattern(
      GeJuType::LianShaYang, "廉杀羊",
      "廉贞七杀同宫再见擎羊，刚烈煞气叠加，宜审慎论断。", false, -55,
      {PatternStar::LianZhen, PatternStar::QiSha, PatternStar::QingYang},
      {ming_gong_index_}, GeJuLevel::Caution,
      {{"廉贞、七杀、擎羊三星会照三方四正"}, {}, {}}, "《紫微斗数全书》");
}

optional<GeJuInfo> GeJuAnalyzer::check_ju_huo_yang() const {
  if (!chart_.ming_sanfang_has_all(
          {PatternStar::JuMen, PatternStar::HuoXing, PatternStar::QingYang}))
    return nullopt;
  return pattern(
      GeJuType::JuHuoYang, "巨火羊",
      "巨门、火星、擎羊同宫，口舌与刚烈之性相激。", false, -50,
      {PatternStar::JuMen, PatternStar::HuoXing, PatternStar::QingYang},
      {ming_gong_index_}, GeJuLevel::Caution,
      {{"巨门、火星、擎羊三星会照三方四正"}, {}, {}}, "《紫微斗数全书》");
}

optional<GeJuInfo> GeJuAnalyzer::check_lu_cun_shou_ming() const {
  const bool in_ming = chart_.has(ming_gong_index_, PatternStar::LuCun);
  const bool in_body = chart_.has(chart_.body_palace(), PatternStar::LuCun);
  if (!in_ming && !in_body)
    return nullopt;
  const string name = in_ming ? "禄存守命" : "禄存守身";
  return pattern(GeJuType::LuCunShouMing, name, "禄存坐命身，主守成聚禄。",
                 true, 20, {PatternStar::LuCun},
                 {in_ming ? ming_gong_index_ : chart_.body_palace()},
                 GeJuLevel::Good, {{name}, {}, {}}, "《紫微斗数全书》");
}

optional<GeJuInfo> GeJuAnalyzer::check_tian_ma_ru_ming() const {
  const int migration = fix_index(ming_gong_index_ + 6);
  const bool in_ming = chart_.has(ming_gong_index_, PatternStar::TianMa);
  const bool in_migration = chart_.has(migration, PatternStar::TianMa);
  if (!in_ming && !in_migration)
    return nullopt;
  const string name = in_ming ? "天马入命" : "天马在迁";
  const int palace = in_ming ? ming_gong_index_ : migration;
  return pattern(GeJuType::TianMaRuMing, name,
                 in_ming ? "天马坐命，主迁动奔走。"
                         : "天马在迁移宫，宜结合禄煞判断动中得失。",
                 true, 10, {PatternStar::TianMa}, {palace}, GeJuLevel::Neutral,
                 {{name}, {}, {}}, "《紫微斗数全书》");
}

optional<GeJuInfo> GeJuAnalyzer::check_hua_lu_ru_cai() const {
  int index = -1;
  for (int i = 0; i < 12; ++i)
    if (chart_.at(i).palace == GongWei::CaiBoGong)
      index = i;
  if (index < 0 || !transformed(chart_, index, SiHua::Lu, true))
    return nullopt;
  return pattern(GeJuType::HuaLuRuCai, "化禄入财", "生年化禄随原星入财帛宫。",
                 true, 20, {}, {index}, GeJuLevel::Good,
                 {{"财帛宫见化禄"}, {}, {}}, "生年四化格局");
}

optional<GeJuInfo> GeJuAnalyzer::check_hua_quan_ru_guan() const {
  int index = -1;
  for (int i = 0; i < 12; ++i)
    if (chart_.at(i).palace == GongWei::GuanLuGong)
      index = i;
  if (index < 0 || !transformed(chart_, index, SiHua::Quan, true))
    return nullopt;
  return pattern(GeJuType::HuaQuanRuGuan, "化权入官",
                 "生年化权随原星入官禄宫。", true, 20, {}, {index},
                 GeJuLevel::Good, {{"官禄宫见化权"}, {}, {}}, "生年四化格局");
}

optional<GeJuInfo> GeJuAnalyzer::check_hua_ke_ru_ming_shen() const {
  const bool in_ming = transformed(chart_, ming_gong_index_, SiHua::Ke, true);
  const bool in_body =
      transformed(chart_, chart_.body_palace(), SiHua::Ke, true);
  if (!in_ming && !in_body)
    return nullopt;
  const string name = in_ming ? "化科入命" : "化科入身";
  return pattern(GeJuType::HuaKeRuMingShen, name,
                 "生年化科随原星入命宫或身宫。", true, 20, {},
                 {ming_gong_index_, chart_.body_palace()}, GeJuLevel::Good,
                 {{"命宫或身宫见化科"}, {}, {}}, "生年四化格局");
}

optional<GeJuInfo> GeJuAnalyzer::check_ji_yue_tong_liang_partial() const {
  const int count = chart_.count_in_ming_sanfang(
      {PatternStar::TianJi, PatternStar::TaiYin, PatternStar::TianTong,
       PatternStar::TianLiang});
  if (count != 3)
    return nullopt;
  return pattern(GeJuType::JiYueTongLiangPartial, "机月同梁三星会",
                 "机月同梁四曜中有三星会入命宫三方，格意成立但不及四曜齐会。",
                 true, 15, {}, {ming_gong_index_}, GeJuLevel::Neutral,
                 {{"机月同梁四曜中恰有三星会命"}, {}, {}}, "机月同梁辅格");
}

optional<GeJuInfo> GeJuAnalyzer::check_fu_bi_tong_hui() const {
  if (!chart_.ming_sanfang_has_all({PatternStar::ZuoFu, PatternStar::YouBi}))
    return nullopt;
  return pattern(GeJuType::FuBiTongHui, "辅弼同会", "左辅右弼同会命宫三方。",
                 true, 18, {PatternStar::ZuoFu, PatternStar::YouBi},
                 {ming_gong_index_}, GeJuLevel::Good,
                 {{"左辅右弼同会命宫三方"}, {}, {}}, "辅曜格局");
}

optional<GeJuInfo> GeJuAnalyzer::check_kui_yue_tong_hui() const {
  if (!chart_.ming_sanfang_has_all(
          {PatternStar::TianKui, PatternStar::TianYue}))
    return nullopt;
  return pattern(GeJuType::KuiYueTongHui, "魁钺同会", "天魁天钺同会命宫三方。",
                 true, 18, {PatternStar::TianKui, PatternStar::TianYue},
                 {ming_gong_index_}, GeJuLevel::Good,
                 {{"天魁天钺同会命宫三方"}, {}, {}}, "辅曜格局");
}

optional<GeJuInfo> GeJuAnalyzer::check_ke_quan_shuang_hui() const {
  if (!transformed_in_sanfang(chart_, SiHua::Ke, true) ||
      !transformed_in_sanfang(chart_, SiHua::Quan, true))
    return nullopt;
  return pattern(GeJuType::KeQuanShuangHui, "科权双会",
                 "化科化权同会命宫三方。", true, 20, {}, {ming_gong_index_},
                 GeJuLevel::Good, {{"化科、化权同会命宫三方"}, {}, {}},
                 "生年四化格局");
}

// ============= 辅助函数 =============

/**
 * @brief 获取宫位的地支
 */
DiZhi GeJuAnalyzer::get_gong_di_zhi(int gong_index) const {
  return chart_.at(gong_index).branch;
}

bool GeJuAnalyzer::gong_has_star(int gong_index,
                                 const string &star_name) const {
  if (star_name == "化禄" || star_name == "化权" || star_name == "化科" ||
      star_name == "化忌") {
    const auto expected = star_name == "化禄"   ? SiHua::Lu
                          : star_name == "化权" ? SiHua::Quan
                          : star_name == "化科" ? SiHua::Ke
                                                : SiHua::Ji;
    return chart_.has_transformation(gong_index, expected);
  }
  const auto star = to_pattern_star(star_name);
  return star && chart_.has(gong_index, *star);
}

bool GeJuAnalyzer::gong_has_all_stars(int gong_index,
                                      const vector<string> &stars) const {
  return ranges::all_of(stars, [this, gong_index](const auto &star) {
    return gong_has_star(gong_index, star);
  });
}

bool GeJuAnalyzer::gong_has_any_star(int gong_index,
                                     const vector<string> &stars) const {
  return ranges::any_of(stars, [this, gong_index](const auto &star) {
    return gong_has_star(gong_index, star);
  });
}

bool GeJuAnalyzer::san_fang_has_star(int gong_index, const string &star) const {
  for (int offset : {0, 4, 6, 8}) {
    if (gong_has_star(fix_index(gong_index + offset), star))
      return true;
  }
  return false;
}

bool GeJuAnalyzer::san_fang_has_all_stars(int gong_index,
                                          const vector<string> &stars) const {
  return ranges::all_of(stars, [this, gong_index](const auto &star) {
    return san_fang_has_star(gong_index, star);
  });
}

bool GeJuAnalyzer::san_fang_has_any_star(int gong_index,
                                         const vector<string> &stars) const {
  return ranges::any_of(stars, [this, gong_index](const auto &star) {
    return san_fang_has_star(gong_index, star);
  });
}

vector<string> GeJuAnalyzer::get_zhu_xing_in_gong(int gong_index) const {
  vector<string> zhu_xing;
  for (int i = static_cast<int>(PatternStar::ZiWei);
       i <= static_cast<int>(PatternStar::PoJun); ++i) {
    const auto star = static_cast<PatternStar>(i);
    if (chart_.has(gong_index, star))
      zhu_xing.emplace_back(pattern_star_name(star));
  }

  return zhu_xing;
}

} // namespace ZhouYi::ZiWei
