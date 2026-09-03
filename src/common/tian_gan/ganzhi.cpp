// 干支系统模块 - 规则与查表实现
module ZhouYi.GanZhi;

import std;

namespace ZhouYi::GanZhi {

using namespace std::string_view_literals;

TianGan operator+(TianGan gan, int num) {
  const int value = static_cast<int>(gan) + num;
  return static_cast<TianGan>((value % 10 + 10) % 10);
}

DiZhi operator+(DiZhi zhi, int num) {
  const int value = static_cast<int>(zhi) + num;
  return static_cast<DiZhi>((value % 12 + 12) % 12);
}

int operator-(DiZhi left, DiZhi right) {
  return (static_cast<int>(left) - static_cast<int>(right) + 12) % 12;
}

DiZhi &operator++(DiZhi &zhi) {
  zhi = static_cast<DiZhi>((static_cast<int>(zhi) + 1) % 12);
  return zhi;
}

DiZhi &operator--(DiZhi &zhi) {
  zhi = static_cast<DiZhi>((static_cast<int>(zhi) - 1 + 12) % 12);
  return zhi;
}

WuXing get_wu_xing(TianGan gan) {
  static constexpr std::array elements = {
      WuXing::Mu,   WuXing::Mu,  // 甲乙木
      WuXing::Huo,  WuXing::Huo, // 丙丁火
      WuXing::Tu,   WuXing::Tu,  // 戊己土
      WuXing::Jin,  WuXing::Jin, // 庚辛金
      WuXing::Shui, WuXing::Shui // 壬癸水
  };
  return elements[static_cast<int>(gan)];
}

WuXing get_wu_xing(DiZhi zhi) {
  static constexpr std::array elements = {
      WuXing::Shui,              // 子水
      WuXing::Tu,                // 丑土
      WuXing::Mu,   WuXing::Mu,  // 寅卯木
      WuXing::Tu,                // 辰土
      WuXing::Huo,  WuXing::Huo, // 巳午火
      WuXing::Tu,                // 未土
      WuXing::Jin,  WuXing::Jin, // 申酉金
      WuXing::Tu,                // 戌土
      WuXing::Shui               // 亥水
  };
  return elements[static_cast<int>(zhi)];
}

YinYang get_yin_yang(TianGan gan) {
  // 甲丙戊庚壬为阳，乙丁己辛癸为阴
  return (static_cast<int>(gan) % 2 == 0) ? YinYang::Yang : YinYang::Yin;
}

YinYang get_yin_yang(DiZhi zhi) {
  // 子寅辰午申戌为阳，丑卯巳未酉亥为阴
  return (static_cast<int>(zhi) % 2 == 0) ? YinYang::Yang : YinYang::Yin;
}

bool wu_xing_sheng(WuXing x, WuXing y) {
  const int ix = static_cast<int>(x);
  const int iy = static_cast<int>(y);
  return (ix == 1 && iy == 2) || // 木(1)生火(2)
         (ix == 2 && iy == 3) || // 火(2)生土(3)
         (ix == 3 && iy == 4) || // 土(3)生金(4)
         (ix == 4 && iy == 5) || // 金(4)生水(5)
         (ix == 5 && iy == 1);   // 水(5)生木(1)
}

bool wu_xing_ke(WuXing x, WuXing y) {
  const int ix = static_cast<int>(x);
  const int iy = static_cast<int>(y);
  return (ix == 1 && iy == 3) || // 木(1)克土(3)
         (ix == 3 && iy == 5) || // 土(3)克水(5)
         (ix == 5 && iy == 2) || // 水(5)克火(2)
         (ix == 2 && iy == 4) || // 火(2)克金(4)
         (ix == 4 && iy == 1);   // 金(4)克木(1)
}

namespace {

template <typename Enum, std::size_t Size>
std::optional<Enum>
find_enum_name(std::string_view name,
               const std::array<std::string_view, Size> &names) {
  const auto found = std::find(names.begin(), names.end(), name);
  if (found == names.end())
    return std::nullopt;
  return static_cast<Enum>(std::distance(names.begin(), found));
}

} // namespace

namespace Mapper {

auto to_zh(TianGan gan) -> std::string_view {
  static constexpr std::array names = {"甲"sv, "乙"sv, "丙"sv, "丁"sv, "戊"sv,
                                       "己"sv, "庚"sv, "辛"sv, "壬"sv, "癸"sv};
  return names[static_cast<int>(gan)];
}

auto to_zh(DiZhi zhi) -> std::string_view {
  static constexpr std::array names = {"子"sv, "丑"sv, "寅"sv, "卯"sv,
                                       "辰"sv, "巳"sv, "午"sv, "未"sv,
                                       "申"sv, "酉"sv, "戌"sv, "亥"sv};
  return names[static_cast<int>(zhi)];
}

auto to_zh(WuXing element) -> std::string_view {
  static constexpr std::array names = {"木"sv, "火"sv, "土"sv, "金"sv, "水"sv};
  return names[static_cast<int>(element) - 1];
}

auto to_zh(YinYang yin_yang) -> std::string_view {
  return yin_yang == YinYang::Yang ? "阳" : "阴";
}

auto sheng_xiao_zh(DiZhi zhi) -> std::string_view {
  static constexpr std::array names = {"鼠"sv, "牛"sv, "虎"sv, "兔"sv,
                                       "龙"sv, "蛇"sv, "马"sv, "羊"sv,
                                       "猴"sv, "鸡"sv, "狗"sv, "猪"sv};
  return names[static_cast<int>(zhi)];
}

auto from_zh_gan(std::string_view name) -> std::optional<TianGan> {
  static constexpr std::array names = {"甲"sv, "乙"sv, "丙"sv, "丁"sv, "戊"sv,
                                       "己"sv, "庚"sv, "辛"sv, "壬"sv, "癸"sv};
  return find_enum_name<TianGan>(name, names);
}

auto from_zh_zhi(std::string_view name) -> std::optional<DiZhi> {
  static constexpr std::array names = {"子"sv, "丑"sv, "寅"sv, "卯"sv,
                                       "辰"sv, "巳"sv, "午"sv, "未"sv,
                                       "申"sv, "酉"sv, "戌"sv, "亥"sv};
  return find_enum_name<DiZhi>(name, names);
}

} // namespace Mapper

auto get_cang_gan(DiZhi zhi) -> std::vector<TianGan> {
  static const std::array<std::vector<TianGan>, 12> table = {{
      {TianGan::Gui},
      {TianGan::Ji, TianGan::Gui, TianGan::Xin},
      {TianGan::Jia, TianGan::Bing, TianGan::Wu},
      {TianGan::Yi},
      {TianGan::Wu, TianGan::Yi, TianGan::Gui},
      {TianGan::Bing, TianGan::Wu, TianGan::Geng},
      {TianGan::Ding, TianGan::Ji},
      {TianGan::Ji, TianGan::Ding, TianGan::Yi},
      {TianGan::Geng, TianGan::Ren, TianGan::Wu},
      {TianGan::Xin},
      {TianGan::Wu, TianGan::Xin, TianGan::Ding},
      {TianGan::Ren, TianGan::Jia},
  }};
  return table[static_cast<int>(zhi)];
}

auto get_human_command_segments(DiZhi month)
    -> std::span<const HumanCommandSegment> {
  static const std::array<std::vector<HumanCommandSegment>, 12> table = {{
      {{TianGan::Ren, 10}, {TianGan::Gui, 20}},
      {{TianGan::Gui, 9}, {TianGan::Xin, 3}, {TianGan::Ji, 18}},
      {{TianGan::Wu, 7}, {TianGan::Bing, 7}, {TianGan::Jia, 16}},
      {{TianGan::Jia, 10}, {TianGan::Yi, 20}},
      {{TianGan::Yi, 9}, {TianGan::Gui, 3}, {TianGan::Wu, 18}},
      {{TianGan::Wu, 5}, {TianGan::Geng, 9}, {TianGan::Bing, 16}},
      {{TianGan::Bing, 10}, {TianGan::Ji, 9}, {TianGan::Ding, 11}},
      {{TianGan::Ding, 9}, {TianGan::Yi, 3}, {TianGan::Ji, 18}},
      {{TianGan::Wu, 10}, {TianGan::Ren, 3}, {TianGan::Geng, 17}},
      {{TianGan::Geng, 10}, {TianGan::Xin, 20}},
      {{TianGan::Xin, 9}, {TianGan::Ding, 3}, {TianGan::Wu, 18}},
      {{TianGan::Wu, 7}, {TianGan::Jia, 5}, {TianGan::Ren, 18}},
  }};
  return table[static_cast<std::size_t>(month)];
}

DiZhi get_ji_gong(TianGan gan) {
  static constexpr std::array palaces = {
      DiZhi::Yin, DiZhi::Chen, DiZhi::Si, DiZhi::Wei, DiZhi::Si,
      DiZhi::Wei, DiZhi::Shen, DiZhi::Xu, DiZhi::Hai, DiZhi::Chou,
  };
  return palaces[static_cast<int>(gan)];
}

auto get_ji_gan(DiZhi zhi) -> std::vector<TianGan> {
  switch (zhi) {
  case DiZhi::Yin:
    return {TianGan::Jia};
  case DiZhi::Chen:
    return {TianGan::Yi};
  case DiZhi::Si:
    return {TianGan::Bing, TianGan::Wu};
  case DiZhi::Wei:
    return {TianGan::Ding, TianGan::Ji};
  case DiZhi::Shen:
    return {TianGan::Geng};
  case DiZhi::Xu:
    return {TianGan::Xin};
  case DiZhi::Hai:
    return {TianGan::Ren};
  case DiZhi::Chou:
    return {TianGan::Gui};
  default:
    return {};
  }
}

DiZhi get_gui_ren(TianGan gan, bool is_day) {
  static constexpr std::array<std::pair<DiZhi, DiZhi>, 10> table = {{
      {DiZhi::Chou, DiZhi::Wei},
      {DiZhi::Zi, DiZhi::Shen},
      {DiZhi::Hai, DiZhi::You},
      {DiZhi::Hai, DiZhi::You},
      {DiZhi::Chou, DiZhi::Wei},
      {DiZhi::Zi, DiZhi::Shen},
      {DiZhi::Chou, DiZhi::Wei},
      {DiZhi::Wu, DiZhi::Yin},
      {DiZhi::Si, DiZhi::Mao},
      {DiZhi::Si, DiZhi::Mao},
  }};
  const auto [day, night] = table[static_cast<int>(gan)];
  return is_day ? day : night;
}

bool is_daytime(DiZhi hour) {
  const int index = static_cast<int>(hour);
  return index >= 3 && index <= 8;
}

LiuShiJiaZi::LiuShiJiaZi(TianGan stem, DiZhi branch) : gan(stem), zhi(branch) {}

LiuShiJiaZi LiuShiJiaZi::from_index(int index) {
  index = ((index % 60) + 60) % 60;
  return {static_cast<TianGan>(index % 10), static_cast<DiZhi>(index % 12)};
}

int LiuShiJiaZi::to_index() const {
  const int stem = static_cast<int>(gan);
  const int branch = static_cast<int>(zhi);
  for (int index = stem; index < 60; index += 10) {
    if (index % 12 == branch)
      return index;
  }
  return 0;
}

std::string LiuShiJiaZi::to_string() const {
  return std::string(Mapper::to_zh(gan)) + std::string(Mapper::to_zh(zhi));
}

WuXing LiuShiJiaZi::get_na_yin() const {
  static constexpr std::array<WuXing, 60> table = {
      WuXing::Jin,  WuXing::Jin,  // 海中金
      WuXing::Huo,  WuXing::Huo,  // 炉中火
      WuXing::Mu,   WuXing::Mu,   // 大林木
      WuXing::Tu,   WuXing::Tu,   // 路旁土
      WuXing::Jin,  WuXing::Jin,  // 剑锋金
      WuXing::Huo,  WuXing::Huo,  // 山头火
      WuXing::Shui, WuXing::Shui, // 涧下水
      WuXing::Tu,   WuXing::Tu,   // 城头土
      WuXing::Jin,  WuXing::Jin,  // 白蜡金
      WuXing::Mu,   WuXing::Mu,   // 杨柳木
      WuXing::Shui, WuXing::Shui, // 泉中水
      WuXing::Tu,   WuXing::Tu,   // 屋上土
      WuXing::Huo,  WuXing::Huo,  // 霹雳火
      WuXing::Mu,   WuXing::Mu,   // 松柏木
      WuXing::Shui, WuXing::Shui, // 长流水
      WuXing::Jin,  WuXing::Jin,  // 沙中金
      WuXing::Huo,  WuXing::Huo,  // 山下火
      WuXing::Mu,   WuXing::Mu,   // 平地木
      WuXing::Tu,   WuXing::Tu,   // 壁上土
      WuXing::Jin,  WuXing::Jin,  // 金箔金
      WuXing::Huo,  WuXing::Huo,  // 覆灯火
      WuXing::Shui, WuXing::Shui, // 天河水
      WuXing::Tu,   WuXing::Tu,   // 大驿土
      WuXing::Jin,  WuXing::Jin,  // 钗钏金
      WuXing::Mu,   WuXing::Mu,   // 桑柘木
      WuXing::Shui, WuXing::Shui, // 大溪水
      WuXing::Tu,   WuXing::Tu,   // 沙中土
      WuXing::Huo,  WuXing::Huo,  // 天上火
      WuXing::Mu,   WuXing::Mu,   // 石榴木
      WuXing::Shui, WuXing::Shui, // 大海水
  };
  return table[to_index()];
}

auto get_liu_shi_jia_zi() -> std::vector<LiuShiJiaZi> {
  std::vector<LiuShiJiaZi> result;
  result.reserve(60);
  for (int index = 0; index < 60; ++index)
    result.push_back(LiuShiJiaZi::from_index(index));
  return result;
}

namespace ShiErChangShengMapper {

auto to_zh(ShiErChangSheng stage) -> std::string_view {
  static constexpr std::array names = {
      "长生"sv, "沐浴"sv, "冠带"sv, "临官"sv, "帝旺"sv, "衰"sv,
      "病"sv,   "死"sv,   "墓"sv,   "绝"sv,   "胎"sv,   "养"sv,
  };
  return names[static_cast<int>(stage)];
}

auto from_zh(std::string_view name) -> std::optional<ShiErChangSheng> {
  static constexpr std::array names = {
      "长生"sv, "沐浴"sv, "冠带"sv, "临官"sv, "帝旺"sv, "衰"sv,
      "病"sv,   "死"sv,   "墓"sv,   "绝"sv,   "胎"sv,   "养"sv,
  };
  return find_enum_name<ShiErChangSheng>(name, names);
}

} // namespace ShiErChangShengMapper

auto get_shi_er_chang_sheng(TianGan gan, DiZhi zhi) -> ShiErChangSheng {
  static constexpr std::array<int, 10> starts = {11, 6, 2, 9, 2, 9, 5, 0, 8, 3};
  const int stem = static_cast<int>(gan);
  const int branch = static_cast<int>(zhi);
  const int start = starts[stem];
  const int offset =
      stem % 2 == 0 ? (branch - start + 12) % 12 : (start - branch + 12) % 12;
  return static_cast<ShiErChangSheng>(offset);
}

bool is_chang_sheng(TianGan gan, DiZhi zhi) {
  return get_shi_er_chang_sheng(gan, zhi) == ShiErChangSheng::ChangSheng;
}

bool is_di_wang(TianGan gan, DiZhi zhi) {
  return get_shi_er_chang_sheng(gan, zhi) == ShiErChangSheng::DiWang;
}

bool is_mu_ku(TianGan gan, DiZhi zhi) {
  return get_shi_er_chang_sheng(gan, zhi) == ShiErChangSheng::Mu;
}

bool is_jue_di(TianGan gan, DiZhi zhi) {
  return get_shi_er_chang_sheng(gan, zhi) == ShiErChangSheng::Jue;
}

auto get_chang_sheng_zhi(TianGan gan) -> DiZhi {
  static constexpr std::array map = {
      DiZhi::Hai, DiZhi::Wu, DiZhi::Yin, DiZhi::You,  DiZhi::Yin,
      DiZhi::You, DiZhi::Si, DiZhi::Zi,  DiZhi::Shen, DiZhi::Mao,
  };
  return map[static_cast<int>(gan)];
}

auto get_di_wang_zhi(TianGan gan) -> DiZhi {
  const int start = static_cast<int>(get_chang_sheng_zhi(gan));
  const int stem = static_cast<int>(gan);
  return static_cast<DiZhi>(stem % 2 == 0 ? (start + 4) % 12
                                          : (start - 4 + 12) % 12);
}

auto get_mu_ku_zhi(TianGan gan) -> DiZhi {
  const int start = static_cast<int>(get_chang_sheng_zhi(gan));
  const int stem = static_cast<int>(gan);
  return static_cast<DiZhi>(stem % 2 == 0 ? (start + 8) % 12
                                          : (start - 8 + 12) % 12);
}

LiuQin get_liu_qin(TianGan self_gan, DiZhi other_zhi) {
  const WuXing self = get_wu_xing(self_gan);
  const WuXing other = get_wu_xing(other_zhi);
  if (wu_xing_sheng(other, self))
    return LiuQin::FuMu;
  if (wu_xing_sheng(self, other))
    return LiuQin::ZiSun;
  if (wu_xing_ke(self, other))
    return LiuQin::QiCai;
  if (wu_xing_ke(other, self))
    return LiuQin::GuanGui;
  return LiuQin::XiongDi;
}

std::string_view liu_qin_to_zh(LiuQin relation) {
  static constexpr std::array names = {"父母"sv, "兄弟"sv, "子孙"sv, "妻财"sv,
                                       "官鬼"sv};
  return names[static_cast<int>(relation)];
}

ShiShen get_shi_shen(TianGan self_gan, TianGan other_gan) {
  const WuXing self = get_wu_xing(self_gan);
  const WuXing other = get_wu_xing(other_gan);
  const bool same_yin_yang = get_yin_yang(self_gan) == get_yin_yang(other_gan);
  if (self == other)
    return same_yin_yang ? ShiShen::BiJian : ShiShen::JieCai;
  if (wu_xing_sheng(self, other))
    return same_yin_yang ? ShiShen::ShiShen : ShiShen::ShangGuan;
  if (wu_xing_ke(self, other))
    return same_yin_yang ? ShiShen::PianCai : ShiShen::ZhengCai;
  if (wu_xing_ke(other, self))
    return same_yin_yang ? ShiShen::QiSha : ShiShen::ZhengGuan;
  return same_yin_yang ? ShiShen::PianYin : ShiShen::ZhengYin;
}

std::string_view shi_shen_to_zh(ShiShen ten_god) {
  static constexpr std::array names = {
      "比肩"sv, "劫财"sv, "食神"sv, "伤官"sv, "偏财"sv,
      "正财"sv, "七杀"sv, "正官"sv, "偏印"sv, "正印"sv,
  };
  return names[static_cast<int>(ten_god)];
}

DiZhi get_xun_shou(TianGan day_gan, DiZhi day_zhi) {
  const int stem = static_cast<int>(day_gan);
  const int branch = static_cast<int>(day_zhi);
  return static_cast<DiZhi>((branch - stem + 12) % 12);
}

std::optional<TianGan> get_dun_gan(DiZhi zhi, TianGan day_gan, DiZhi day_zhi) {
  const int delta = (static_cast<int>(zhi) -
                     static_cast<int>(get_xun_shou(day_gan, day_zhi)) + 12) %
                    12;
  if (delta == 10 || delta == 11)
    return std::nullopt;
  return static_cast<TianGan>(delta % 10);
}

bool is_kong_wang(DiZhi zhi, TianGan day_gan, DiZhi day_zhi) {
  return !get_dun_gan(zhi, day_gan, day_zhi).has_value();
}

std::array<DiZhi, 2> get_kong_wang(TianGan day_gan, DiZhi day_zhi) {
  const int start = static_cast<int>(get_xun_shou(day_gan, day_zhi));
  return {static_cast<DiZhi>((start - 2 + 12) % 12),
          static_cast<DiZhi>((start - 1 + 12) % 12)};
}

bool is_chong(DiZhi zhi1, DiZhi zhi2) {
  return (static_cast<int>(zhi1) + 6) % 12 == static_cast<int>(zhi2);
}

bool is_xing(DiZhi zhi1, DiZhi zhi2) {
  const int first = static_cast<int>(zhi1);
  const int second = static_cast<int>(zhi2);
  if ((first == 0 && second == 3) || (first == 3 && second == 0))
    return true;
  if ((first == 2 && second == 5) || (first == 5 && second == 9) ||
      (first == 9 && second == 2))
    return true;
  if ((first == 1 && second == 10) || (first == 10 && second == 7) ||
      (first == 7 && second == 1))
    return true;
  return first == second &&
         (first == 4 || first == 6 || first == 9 || first == 11);
}

std::optional<WuXing> get_he_wu_xing(DiZhi zhi1, DiZhi zhi2) {
  static constexpr std::array<std::tuple<DiZhi, DiZhi, WuXing>, 6> rules = {{
      {DiZhi::Zi, DiZhi::Chou, WuXing::Tu},
      {DiZhi::Yin, DiZhi::Hai, WuXing::Mu},
      {DiZhi::Mao, DiZhi::Xu, WuXing::Huo},
      {DiZhi::Chen, DiZhi::You, WuXing::Jin},
      {DiZhi::Si, DiZhi::Shen, WuXing::Shui},
      {DiZhi::Wu, DiZhi::Wei, WuXing::Tu},
  }};
  for (const auto &[first, second, element] : rules) {
    if ((zhi1 == first && zhi2 == second) || (zhi1 == second && zhi2 == first))
      return element;
  }
  return std::nullopt;
}

bool is_he(DiZhi zhi1, DiZhi zhi2) {
  return get_he_wu_xing(zhi1, zhi2).has_value();
}

std::optional<WuXing> stem_combine_element(TianGan first, TianGan second) {
  static constexpr std::array<std::tuple<TianGan, TianGan, WuXing>, 5> rules = {
      {
          {TianGan::Jia, TianGan::Ji, WuXing::Tu},
          {TianGan::Yi, TianGan::Geng, WuXing::Jin},
          {TianGan::Bing, TianGan::Xin, WuXing::Shui},
          {TianGan::Ding, TianGan::Ren, WuXing::Mu},
          {TianGan::Wu, TianGan::Gui, WuXing::Huo},
      }};
  for (const auto &[left, right, element] : rules) {
    if ((first == left && second == right) ||
        (first == right && second == left))
      return element;
  }
  return std::nullopt;
}

bool is_hai(DiZhi zhi1, DiZhi zhi2) {
  static constexpr std::array<std::pair<DiZhi, DiZhi>, 6> rules = {{
      {DiZhi::Zi, DiZhi::Wei},
      {DiZhi::Chou, DiZhi::Wu},
      {DiZhi::Yin, DiZhi::Si},
      {DiZhi::Mao, DiZhi::Chen},
      {DiZhi::Shen, DiZhi::Hai},
      {DiZhi::You, DiZhi::Xu},
  }};
  return std::any_of(rules.begin(), rules.end(), [&](const auto &rule) {
    return (zhi1 == rule.first && zhi2 == rule.second) ||
           (zhi1 == rule.second && zhi2 == rule.first);
  });
}

auto is_san_he(DiZhi zhi1, DiZhi zhi2, DiZhi zhi3) -> std::pair<bool, WuXing> {
  std::array<int, 3> branches = {static_cast<int>(zhi1), static_cast<int>(zhi2),
                                 static_cast<int>(zhi3)};
  std::ranges::sort(branches);
  if (branches == std::array{0, 4, 8})
    return {true, WuXing::Shui};
  if (branches == std::array{3, 7, 11})
    return {true, WuXing::Mu};
  if (branches == std::array{2, 6, 10})
    return {true, WuXing::Huo};
  if (branches == std::array{1, 5, 9})
    return {true, WuXing::Jin};
  return {false, WuXing::Mu};
}

auto get_san_he_half(DiZhi zhi1, DiZhi zhi2) -> std::optional<SanHeHalfResult> {
  struct Rule {
    DiZhi sheng;
    DiZhi wang;
    DiZhi mu;
    WuXing element;
  };
  static const std::array<Rule, 4> rules = {{
      {DiZhi::Shen, DiZhi::Zi, DiZhi::Chen, WuXing::Shui},
      {DiZhi::Hai, DiZhi::Mao, DiZhi::Wei, WuXing::Mu},
      {DiZhi::Yin, DiZhi::Wu, DiZhi::Xu, WuXing::Huo},
      {DiZhi::Si, DiZhi::You, DiZhi::Chou, WuXing::Jin},
  }};
  const auto matches = [&](DiZhi left, DiZhi right) {
    return (zhi1 == left && zhi2 == right) || (zhi1 == right && zhi2 == left);
  };
  for (const auto &rule : rules) {
    if (matches(rule.sheng, rule.wang))
      return SanHeHalfResult{rule.element, SanHeHalfKind::ShengWang, rule.mu};
    if (matches(rule.wang, rule.mu))
      return SanHeHalfResult{rule.element, SanHeHalfKind::WangMu, rule.sheng};
    if (matches(rule.sheng, rule.mu))
      return SanHeHalfResult{rule.element, SanHeHalfKind::ShengMu, rule.wang};
  }
  return std::nullopt;
}

auto is_san_hui(DiZhi zhi1, DiZhi zhi2, DiZhi zhi3) -> std::pair<bool, WuXing> {
  std::array<int, 3> branches = {static_cast<int>(zhi1), static_cast<int>(zhi2),
                                 static_cast<int>(zhi3)};
  std::ranges::sort(branches);
  if (branches == std::array{2, 3, 4})
    return {true, WuXing::Mu};
  if (branches == std::array{5, 6, 7})
    return {true, WuXing::Huo};
  if (branches == std::array{8, 9, 10})
    return {true, WuXing::Jin};
  if (branches == std::array{0, 1, 11})
    return {true, WuXing::Shui};
  return {false, WuXing::Mu};
}

} // namespace ZhouYi::GanZhi
