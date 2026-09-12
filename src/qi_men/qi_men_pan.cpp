module ZhouYi.QiMen.Pan;

import fmt;

namespace ZhouYi::QiMen {
namespace {
using ZhouYi::GanZhi::DiZhi;
using ZhouYi::GanZhi::TianGan;
using ZhouYi::GanZhi::Mapper::to_zh;

constexpr std::array<TianGan, 9> kSanQiLiuYi{
    TianGan::Wu,  TianGan::Ji,   TianGan::Geng, TianGan::Xin, TianGan::Ren,
    TianGan::Gui, TianGan::Ding, TianGan::Bing, TianGan::Yi};
constexpr std::array<Palace, 8> kClockwise{
    Palace::SouthWest, Palace::West, Palace::NorthWest, Palace::North,
    Palace::NorthEast, Palace::East, Palace::SouthEast, Palace::South};
constexpr std::array<Palace, 8> kCounterClockwise{
    Palace::SouthWest, Palace::South, Palace::SouthEast, Palace::East,
    Palace::NorthEast, Palace::North, Palace::NorthWest, Palace::West};
constexpr std::array<Star, 8> kStarSequence{
    Star::TianXin, Star::TianPeng, Star::TianRen, Star::TianChong,
    Star::TianFu,  Star::TianYing, Star::TianRui, Star::TianZhu};
constexpr std::array<Gate, 8> kGateSequence{
    Gate::Xiu,  Gate::Sheng, Gate::Shang,    Gate::Du,
    Gate::Jing, Gate::Si,    Gate::JingGate, Gate::Kai};
constexpr std::array<Spirit, 8> kSpiritSequence{
    Spirit::ZhiFu, Spirit::TengShe, Spirit::TaiYin, Spirit::LiuHe,
    Spirit::BaiHu, Spirit::XuanWu,  Spirit::JiuDi,  Spirit::JiuTian};
constexpr std::array<DiZhi, 6> kXunHeadBranches{
    DiZhi::Zi, DiZhi::Xu, DiZhi::Shen, DiZhi::Wu, DiZhi::Chen, DiZhi::Yin};

std::size_t index_of(Palace value, std::span<const Palace> sequence) {
  const auto found = std::ranges::find(sequence, value);
  if (found == sequence.end())
    throw std::logic_error("宫位不在转盘序列中");
  return static_cast<std::size_t>(found - sequence.begin());
}
template <typename T, std::size_t N>
std::size_t index_of(T value, const std::array<T, N> &sequence) {
  const auto found = std::ranges::find(sequence, value);
  if (found == sequence.end())
    throw std::logic_error("排盘序列缺少指定元素");
  return static_cast<std::size_t>(found - sequence.begin());
}
Palace find_earth_stem(const QiMenPan &pan, TianGan stem) {
  for (const auto &palace : pan.palaces)
    if (palace.di_gan == stem)
      return palace.palace;
  throw std::logic_error("地盘未找到指定奇仪");
}
Palace original_star_palace(Star star) {
  for (std::uint8_t number = 1; number <= 9; ++number)
    if (get_star_at_palace(get_palace_from_number(number)) == star)
      return get_palace_from_number(number);
  throw std::logic_error("原始九星落宫表不完整");
}

void initialize_palaces(QiMenPan &pan) {
  for (std::uint8_t number = 1; number <= 9; ++number) {
    auto &info = pan.palaces[number - 1];
    info.palace = get_palace_from_number(number);
    info.star = get_star_at_palace(info.palace);
    info.gate = get_gate_at_palace(info.palace);
    const auto [branches, count] = palace_branches(info.palace);
    info.branches = branches;
    info.branch_count = count;
  }
}

void arrange_earth_plate(QiMenPan &pan) {
  for (std::size_t index = 0; index < kSanQiLiuYi.size(); ++index) {
    const int offset = pan.dun == Dun::Yang ? static_cast<int>(index)
                                            : -static_cast<int>(index);
    const int position = (static_cast<int>(pan.ju) - 1 + offset + 18) % 9 + 1;
    pan.palaces[static_cast<std::size_t>(position - 1)].di_gan =
        kSanQiLiuYi[index];
  }
}

void arrange_stars_and_heaven_stems(QiMenPan &pan) {
  Star rotating_duty =
      pan.zhi_fu_star == Star::TianQin ? Star::TianRui : pan.zhi_fu_star;
  const std::size_t duty_index = index_of(rotating_duty, kStarSequence);
  const std::size_t landing_index = index_of(pan.zhi_fu_palace, kClockwise);
  for (std::size_t step = 0; step < 8; ++step) {
    const Star star = kStarSequence[(duty_index + step) % 8];
    auto &target = pan.palaces[get_number_from_palace(
                                   kClockwise[(landing_index + step) % 8]) -
                               1];
    target.star = star;
    target.tian_gan =
        pan.palaces[get_number_from_palace(original_star_palace(star)) - 1]
            .di_gan;
  }

  auto &center = pan.palaces[4];
  center.star = Star::TianQin;
  center.tian_gan = center.di_gan;
  auto &rui_palace =
      *std::ranges::find_if(pan.palaces, [](const PalaceInfo &item) {
        return item.star == Star::TianRui;
      });
  rui_palace.extra_star = Star::TianQin;
  rui_palace.extra_tian_gan = center.di_gan;
  const Palace ji_gong =
      pan.dun == Dun::Yang ? Palace::NorthEast : Palace::SouthWest;
  auto &lodging = pan.palaces[get_number_from_palace(ji_gong) - 1];
  lodging.is_ji_gong = true;
  lodging.extra_di_gan = center.di_gan;
}

void arrange_gates(QiMenPan &pan) {
  const std::size_t gate_index = index_of(pan.zhi_shi_gate, kGateSequence);
  const std::size_t landing_index = index_of(pan.zhi_shi_palace, kClockwise);
  for (std::size_t step = 0; step < 8; ++step) {
    auto &target = pan.palaces[get_number_from_palace(
                                   kClockwise[(landing_index + step) % 8]) -
                               1];
    target.gate = kGateSequence[(gate_index + step) % 8];
  }
  pan.palaces[4].gate = Gate::None;
}

void arrange_spirits(QiMenPan &pan) {
  const auto &sequence = pan.dun == Dun::Yang ? kClockwise : kCounterClockwise;
  const std::size_t landing_index = index_of(pan.zhi_fu_palace, sequence);
  for (std::size_t step = 0; step < 8; ++step) {
    auto &target = pan.palaces[get_number_from_palace(
                                   sequence[(landing_index + step) % 8]) -
                               1];
    target.spirit = kSpiritSequence[step];
  }
  pan.palaces[4].spirit = Spirit::None;
}

void arrange_hidden_stems(QiMenPan &pan, TianGan original_hour_gan,
                          TianGan actual_hour_gan) {
  Palace start = pan.zhi_shi_palace;
  if (original_hour_gan == TianGan::Jia &&
      actual_hour_gan != pan.palaces[4].di_gan)
    start = Palace::Center;
  else if (original_hour_gan != TianGan::Jia &&
           pan.zhi_fu_palace == pan.zhi_shi_palace)
    start = Palace::Center;
  int position = get_number_from_palace(start);
  for (std::size_t index = 0; index < kSanQiLiuYi.size(); ++index) {
    pan.palaces[static_cast<std::size_t>(position - 1)].hidden_gan =
        kSanQiLiuYi[(index_of(actual_hour_gan, kSanQiLiuYi) + index) % 9];
    position += pan.dun == Dun::Yang ? 1 : -1;
    if (position == 10)
      position = 1;
    if (position == 0)
      position = 9;
  }
}

void mark_dynamic_states(QiMenPan &pan, DiZhi hour_zhi) {
  const Palace horse = post_horse_palace(hour_zhi);
  for (auto &info : pan.palaces) {
    info.is_zhi_fu = info.palace == pan.zhi_fu_palace;
    info.is_zhi_shi = info.palace == pan.zhi_shi_palace;
    info.is_post_horse = info.palace == horse;
    for (std::size_t i = 0; i < info.branch_count; ++i)
      info.is_void = info.is_void || info.branches[i] == pan.void_branches[0] ||
                     info.branches[i] == pan.void_branches[1];
  }
}
} // namespace

auto QiMenPanGenerator::generate_pan(SolarTerm term, TianGan day_gan,
                                     DiZhi day_zhi, TianGan hour_gan,
                                     DiZhi hour_zhi)
    -> std::expected<QiMenPan, std::string> {
  try {
    QiMenPan pan;
    pan.solar_term = term;
    pan.dun = get_dun_from_solar_term(term);
    pan.yuan = get_yuan_from_gan_zhi(day_gan, day_zhi);
    pan.ju = get_ju_from_solar_term_and_yuan(term, pan.yuan);
    pan.xun_shou = get_jia_xun_from_gan_zhi(hour_gan, hour_zhi);
    pan.xun_hidden_gan = get_liu_yi_from_jia_xun(pan.xun_shou);
    pan.void_branches = get_void_branches(pan.xun_shou);
    initialize_palaces(pan);
    arrange_earth_plate(pan);

    const Palace xun_palace = find_earth_stem(pan, pan.xun_hidden_gan);
    pan.zhi_fu_star = get_star_at_palace(xun_palace);
    pan.zhi_shi_gate = get_gate_at_palace(
        xun_palace == Palace::Center ? Palace::SouthWest : xun_palace);
    const TianGan actual_hour_gan =
        hour_gan == TianGan::Jia ? pan.xun_hidden_gan : hour_gan;
    Palace duty_star_landing = find_earth_stem(pan, actual_hour_gan);
    if (duty_star_landing == Palace::Center)
      duty_star_landing = Palace::SouthWest;
    pan.zhi_fu_palace = duty_star_landing;

    const auto xun_branch =
        kXunHeadBranches[static_cast<std::size_t>(pan.xun_shou)];
    int steps =
        (static_cast<int>(hour_zhi) - static_cast<int>(xun_branch) + 12) % 12;
    int raw_gate_position = get_number_from_palace(
        xun_palace == Palace::Center ? Palace::SouthWest : xun_palace);
    raw_gate_position =
        (raw_gate_position - 1 + (pan.dun == Dun::Yang ? steps : -steps) + 18) %
            9 +
        1;
    if (raw_gate_position == 5)
      raw_gate_position = pan.dun == Dun::Yang ? 8 : 2;
    pan.zhi_shi_palace =
        get_palace_from_number(static_cast<std::uint8_t>(raw_gate_position));

    arrange_stars_and_heaven_stems(pan);
    arrange_gates(pan);
    arrange_spirits(pan);
    arrange_hidden_stems(pan, hour_gan, actual_hour_gan);
    mark_dynamic_states(pan, hour_zhi);
    return pan;
  } catch (const std::exception &error) {
    return std::unexpected(error.what());
  }
}

auto QiMenPanGenerator::generate_pan(SolarTerm term, std::uint8_t day_gan,
                                     std::uint8_t day_zhi,
                                     std::uint8_t hour_gan,
                                     std::uint8_t hour_zhi)
    -> std::expected<QiMenPan, std::string> {
  if (day_gan >= 10 || hour_gan >= 10 || day_zhi >= 12 || hour_zhi >= 12)
    return std::unexpected("干支索引超出有效范围");
  return generate_pan(
      term, static_cast<TianGan>(day_gan), static_cast<DiZhi>(day_zhi),
      static_cast<TianGan>(hour_gan), static_cast<DiZhi>(hour_zhi));
}

std::string format_qi_men_pan(const QiMenPan &pan) {
  std::string result = fmt::format(
      "【时家转盘奇门】\n{} {} {}局　{}　旬首{}　值符{}落{}　值使{}落{}\n\n",
      solar_term_name(pan.solar_term), pan.dun == Dun::Yang ? "阳遁" : "阴遁",
      pan.ju, yuan_name(pan.yuan), jia_xun_name(pan.xun_shou),
      star_name(pan.zhi_fu_star), palace_name(pan.zhi_fu_palace),
      gate_name(pan.zhi_shi_gate), palace_name(pan.zhi_shi_palace));
  result +=
      "| 宫位 | 八神 | 九星旺衰 | 八门旺衰 | 天盘干 | 地盘干 | 暗干 | 标记 |\n";
  result += "|---|---|---|---|---|---|---|---|\n";
  constexpr std::array<std::uint8_t, 9> layout{4, 9, 2, 3, 5, 7, 8, 1, 6};
  for (auto number : layout) {
    const auto &p = pan.palaces[number - 1];
    std::string stars(star_name(p.star));
    if (p.extra_star)
      stars += "/" + std::string(star_name(*p.extra_star));
    stars += fmt::format("〔{}〕", strength_name(p.star_strength));
    const std::string gate = fmt::format("{}〔{}〕", gate_name(p.gate),
                                         strength_name(p.gate_strength));
    std::string heaven(to_zh(p.tian_gan));
    if (p.extra_tian_gan)
      heaven += "/" + std::string(to_zh(*p.extra_tian_gan));
    std::string earth(to_zh(p.di_gan));
    if (p.extra_di_gan)
      earth += "/" + std::string(to_zh(*p.extra_di_gan));
    std::string marks;
    if (p.is_zhi_fu)
      marks += "值符 ";
    if (p.is_zhi_shi)
      marks += "值使 ";
    if (p.is_void)
      marks += "空亡 ";
    if (p.is_post_horse)
      marks += "驿马 ";
    result += fmt::format(
        "| {} | {} | {} | {} | {} | {} | {} | {} |\n", palace_name(p.palace),
        spirit_name(p.spirit), stars, gate, heaven, earth,
        p.hidden_gan ? to_zh(*p.hidden_gan) : "—", marks.empty() ? "—" : marks);
  }
  return result;
}
} // namespace ZhouYi::QiMen
