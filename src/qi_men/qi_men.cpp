module ZhouYi.QiMen;

import ZhouYi.ZhMapper;

namespace ZhouYi::QiMen {
namespace {
constexpr std::array<TianGan, 10> kStems{
    TianGan::Jia, TianGan::Yi,   TianGan::Bing, TianGan::Ding, TianGan::Wu,
    TianGan::Ji,  TianGan::Geng, TianGan::Xin,  TianGan::Ren,  TianGan::Gui};
constexpr std::array<DiZhi, 12> kBranches{
    DiZhi::Zi, DiZhi::Chou, DiZhi::Yin,  DiZhi::Mao, DiZhi::Chen, DiZhi::Si,
    DiZhi::Wu, DiZhi::Wei,  DiZhi::Shen, DiZhi::You, DiZhi::Xu,   DiZhi::Hai};

int sexagenary_index(TianGan gan, DiZhi zhi) {
  for (int index = 0; index < 60; ++index)
    if (kStems[index % 10] == gan && kBranches[index % 12] == zhi)
      return index;
  throw std::invalid_argument("天干地支阴阳不配，不能组成六十甲子");
}
} // namespace

std::string_view palace_name(Palace value) noexcept {
  constexpr std::array<std::string_view, 9> names{"坎一宫", "坤二宫", "震三宫",
                                                  "巽四宫", "中五宫", "乾六宫",
                                                  "兑七宫", "艮八宫", "离九宫"};
  return names[static_cast<std::size_t>(value) - 1];
}
std::string_view trigram_name(Palace value) noexcept {
  constexpr std::array<std::string_view, 9> names{"坎", "坤", "震", "巽", "中",
                                                  "乾", "兑", "艮", "离"};
  return names[static_cast<std::size_t>(value) - 1];
}
std::string_view gate_name(Gate value) noexcept {
  constexpr std::array<std::string_view, 9> names{
      "休门", "生门", "伤门", "杜门", "景门", "死门", "惊门", "开门", "无门"};
  return names[static_cast<std::size_t>(value)];
}
std::string_view star_name(Star value) noexcept {
  constexpr std::array<std::string_view, 9> names{
      "天蓬", "天芮", "天冲", "天辅", "天禽", "天心", "天柱", "天任", "天英"};
  return names[static_cast<std::size_t>(value)];
}
std::string_view spirit_name(Spirit value) noexcept {
  constexpr std::array<std::string_view, 9> names{
      "值符", "腾蛇", "太阴", "六合", "白虎", "玄武", "九地", "九天", "无神"};
  return names[static_cast<std::size_t>(value)];
}
std::string_view solar_term_name(SolarTerm value) noexcept {
  constexpr std::array<std::string_view, 24> names{
      "冬至", "小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明",
      "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋",
      "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪"};
  return names[static_cast<std::size_t>(value)];
}
std::string_view yuan_name(Yuan value) noexcept {
  constexpr std::array<std::string_view, 3> names{"上元", "中元", "下元"};
  return names[static_cast<std::size_t>(value)];
}
std::string_view jia_xun_name(JiaXun value) noexcept {
  constexpr std::array<std::string_view, 6> names{"甲子旬", "甲戌旬", "甲申旬",
                                                  "甲午旬", "甲辰旬", "甲寅旬"};
  return names[static_cast<std::size_t>(value)];
}
std::string_view strength_name(Strength value) noexcept {
  constexpr std::array<std::string_view, 7> names{"旺", "相", "休",  "囚",
                                                  "废", "死", "未判"};
  return names[static_cast<std::size_t>(value)];
}

Dun get_dun_from_solar_term(SolarTerm term) noexcept {
  return static_cast<std::uint8_t>(term) < 12 ? Dun::Yang : Dun::Yin;
}
std::uint8_t get_ju_from_solar_term_and_yuan(SolarTerm term,
                                             Yuan yuan) noexcept {
  constexpr std::array<std::array<std::uint8_t, 3>, 24> table{
      {{1, 7, 4}, {2, 8, 5}, {3, 9, 6}, {8, 5, 2}, {9, 6, 3}, {1, 7, 4},
       {3, 9, 6}, {4, 1, 7}, {5, 2, 8}, {4, 1, 7}, {5, 2, 8}, {6, 3, 9},
       {9, 3, 6}, {8, 2, 5}, {7, 1, 4}, {2, 5, 8}, {1, 4, 7}, {9, 3, 6},
       {7, 1, 4}, {6, 9, 3}, {5, 8, 2}, {6, 9, 3}, {5, 8, 2}, {4, 7, 1}}};
  return table[static_cast<std::size_t>(term)][static_cast<std::size_t>(yuan)];
}
Yuan get_yuan_from_gan_zhi(TianGan gan, DiZhi zhi) {
  return static_cast<Yuan>((sexagenary_index(gan, zhi) % 15) / 5);
}
JiaXun get_jia_xun_from_gan_zhi(TianGan gan, DiZhi zhi) {
  return static_cast<JiaXun>(sexagenary_index(gan, zhi) / 10);
}
TianGan get_liu_yi_from_jia_xun(JiaXun xun) noexcept {
  constexpr std::array<TianGan, 6> values{TianGan::Wu,   TianGan::Ji,
                                          TianGan::Geng, TianGan::Xin,
                                          TianGan::Ren,  TianGan::Gui};
  return values[static_cast<std::size_t>(xun)];
}
std::array<DiZhi, 2> get_void_branches(JiaXun xun) noexcept {
  constexpr std::array<std::array<DiZhi, 2>, 6> values{
      {{DiZhi::Xu, DiZhi::Hai},
       {DiZhi::Shen, DiZhi::You},
       {DiZhi::Wu, DiZhi::Wei},
       {DiZhi::Chen, DiZhi::Si},
       {DiZhi::Yin, DiZhi::Mao},
       {DiZhi::Zi, DiZhi::Chou}}};
  return values[static_cast<std::size_t>(xun)];
}
Palace get_palace_from_number(std::uint8_t number) {
  if (number < 1 || number > 9)
    throw std::out_of_range("奇门宫数必须在 1..9 范围内");
  return static_cast<Palace>(number);
}
std::uint8_t get_number_from_palace(Palace value) noexcept {
  return static_cast<std::uint8_t>(value);
}
Star get_star_at_palace(Palace value) {
  constexpr std::array<Star, 9> values{
      Star::TianPeng, Star::TianRui, Star::TianChong,
      Star::TianFu,   Star::TianQin, Star::TianXin,
      Star::TianZhu,  Star::TianRen, Star::TianYing};
  return values[get_number_from_palace(value) - 1];
}
Gate get_gate_at_palace(Palace value) noexcept {
  constexpr std::array<Gate, 9> values{Gate::Xiu,      Gate::Si,    Gate::Shang,
                                       Gate::Du,       Gate::None,  Gate::Kai,
                                       Gate::JingGate, Gate::Sheng, Gate::Jing};
  return values[get_number_from_palace(value) - 1];
}
WuXing palace_element(Palace value) {
  constexpr std::array<WuXing, 9> values{WuXing::Shui, WuXing::Tu, WuXing::Mu,
                                         WuXing::Mu,   WuXing::Tu, WuXing::Jin,
                                         WuXing::Jin,  WuXing::Tu, WuXing::Huo};
  return values[get_number_from_palace(value) - 1];
}
WuXing gate_element(Gate value) {
  constexpr std::array<WuXing, 9> values{WuXing::Shui, WuXing::Tu,  WuXing::Mu,
                                         WuXing::Mu,   WuXing::Huo, WuXing::Tu,
                                         WuXing::Jin,  WuXing::Jin, WuXing::Tu};
  return values[static_cast<std::size_t>(value)];
}
WuXing star_element(Star value) {
  constexpr std::array<WuXing, 9> values{WuXing::Shui, WuXing::Tu, WuXing::Mu,
                                         WuXing::Mu,   WuXing::Tu, WuXing::Jin,
                                         WuXing::Jin,  WuXing::Tu, WuXing::Huo};
  return values[static_cast<std::size_t>(value)];
}
std::pair<std::array<DiZhi, 2>, std::uint8_t>
palace_branches(Palace value) noexcept {
  switch (value) {
  case Palace::North:
    return {{DiZhi::Zi, DiZhi::Zi}, 1};
  case Palace::SouthWest:
    return {{DiZhi::Wei, DiZhi::Shen}, 2};
  case Palace::East:
    return {{DiZhi::Mao, DiZhi::Mao}, 1};
  case Palace::SouthEast:
    return {{DiZhi::Chen, DiZhi::Si}, 2};
  case Palace::NorthWest:
    return {{DiZhi::Xu, DiZhi::Hai}, 2};
  case Palace::West:
    return {{DiZhi::You, DiZhi::You}, 1};
  case Palace::NorthEast:
    return {{DiZhi::Chou, DiZhi::Yin}, 2};
  case Palace::South:
    return {{DiZhi::Wu, DiZhi::Wu}, 1};
  default:
    return {{DiZhi::Zi, DiZhi::Zi}, 0};
  }
}
Palace post_horse_palace(DiZhi value) noexcept {
  switch (value) {
  case DiZhi::Shen:
  case DiZhi::Zi:
  case DiZhi::Chen:
    return Palace::NorthEast;
  case DiZhi::Yin:
  case DiZhi::Wu:
  case DiZhi::Xu:
    return Palace::SouthWest;
  case DiZhi::Hai:
  case DiZhi::Mao:
  case DiZhi::Wei:
    return Palace::SouthEast;
  default:
    return Palace::NorthWest;
  }
}

void to_json(nlohmann::json &json, const QiMenPan &pan) {
  const auto pattern_json = [](const PatternFinding &finding) {
    return nlohmann::json{
        {"rule_id", finding.rule_id},
        {"name", finding.name},
        {"nature", std::string(ZhouYi::Mapper::to_zh(finding.nature))},
        {"palace", get_number_from_palace(finding.palace)},
        {"pan_ju_basis", finding.pan_ju_basis}};
  };
  json = {{"solar_date",
           {{"year", pan.solar_year},
            {"month", pan.solar_month},
            {"day", pan.solar_day},
            {"hour", pan.hour},
            {"minute", pan.minute}}},
          {"dun", std::string(ZhouYi::Mapper::to_zh(pan.dun))},
          {"yuan", yuan_name(pan.yuan)},
          {"ju", pan.ju},
          {"solar_term", solar_term_name(pan.solar_term)},
          {"xun_shou", jia_xun_name(pan.xun_shou)}};
  json["zhi_fu"] = {{"star", star_name(pan.zhi_fu_star)},
                    {"palace", get_number_from_palace(pan.zhi_fu_palace)}};
  json["zhi_shi"] = {{"gate", gate_name(pan.zhi_shi_gate)},
                     {"palace", get_number_from_palace(pan.zhi_shi_palace)}};
  json["palaces"] = nlohmann::json::array();
  for (const auto &p : pan.palaces) {
    nlohmann::json item{
        {"palace", get_number_from_palace(p.palace)},
        {"name", palace_name(p.palace)},
        {"star", star_name(p.star)},
        {"star_strength", strength_name(p.star_strength)},
        {"gate", gate_name(p.gate)},
        {"gate_strength", strength_name(p.gate_strength)},
        {"spirit", spirit_name(p.spirit)},
        {"heaven_stem", ZhouYi::GanZhi::Mapper::to_zh(p.tian_gan)},
        {"earth_stem", ZhouYi::GanZhi::Mapper::to_zh(p.di_gan)},
        {"zhi_fu", p.is_zhi_fu},
        {"zhi_shi", p.is_zhi_shi},
        {"void", p.is_void},
        {"post_horse", p.is_post_horse}};
    if (p.extra_star)
      item["extra_star"] = star_name(*p.extra_star);
    if (p.extra_tian_gan)
      item["extra_heaven_stem"] =
          ZhouYi::GanZhi::Mapper::to_zh(*p.extra_tian_gan);
    if (p.extra_di_gan)
      item["extra_earth_stem"] = ZhouYi::GanZhi::Mapper::to_zh(*p.extra_di_gan);
    if (p.hidden_gan)
      item["hidden_stem"] = ZhouYi::GanZhi::Mapper::to_zh(*p.hidden_gan);
    item["patterns"] = nlohmann::json::array();
    for (const auto &finding : p.patterns)
      item["patterns"].push_back(pattern_json(finding));
    json["palaces"].push_back(std::move(item));
  }
  json["global_patterns"] = nlohmann::json::array();
  for (const auto &finding : pan.global_patterns)
    json["global_patterns"].push_back(pattern_json(finding));
}
} // namespace ZhouYi::QiMen
