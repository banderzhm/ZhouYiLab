module ZhouYi.ZiWei.Pattern;

import std;
import ZhouYi.ZiWei.Constants;

namespace ZhouYi::ZiWei {
namespace {
constexpr std::size_t offset(PatternStar star) noexcept {
  return static_cast<std::size_t>(star);
}
constexpr int normalize(int index) noexcept { return (index % 12 + 12) % 12; }

constexpr std::uint64_t star_hash(std::string_view value) noexcept {
  std::uint64_t hash = 14695981039346656037ULL;
  for (const unsigned char byte : value) {
    hash ^= byte;
    hash *= 1099511628211ULL;
  }
  return hash;
}
} // namespace

PatternChart::PatternChart() { locations_.fill(-1); }

void PatternChart::set_ming_palace(int index) noexcept {
  ming_palace_ = normalize(index);
}

void PatternChart::set_body_palace(int index) noexcept {
  body_palace_ = normalize(index);
}

void PatternChart::set_palace(int index, GongWei palace, GanZhi::DiZhi branch,
                              bool is_body_palace) noexcept {
  auto &target = palaces_[normalize(index)];
  target.index = normalize(index);
  target.palace = palace;
  target.branch = branch;
  target.is_body_palace = is_body_palace;
}

void PatternChart::add_star(int palace_index, std::string_view name,
                            PatternStarKind kind,
                            std::optional<LiangDu> brightness,
                            std::optional<SiHua> transformation) {
  const auto star = to_pattern_star(name);
  if (!star)
    return;
  const int normalized = normalize(palace_index);
  palaces_[normalized].stars[offset(*star)] = {.present = true,
                                               .kind = kind,
                                               .brightness = brightness,
                                               .transformation =
                                                   transformation};
  if (transformation) {
    const auto transformation_index = static_cast<std::size_t>(*transformation);
    palaces_[normalized].transformations[transformation_index] = true;
    if (kind == PatternStarKind::Major)
      palaces_[normalized].major_transformations[transformation_index] = true;
  }
  locations_[offset(*star)] = static_cast<std::int8_t>(normalized);
}

int PatternChart::ming_palace() const noexcept { return ming_palace_; }
int PatternChart::body_palace() const noexcept { return body_palace_; }
const PatternPalace &PatternChart::at(int index) const noexcept {
  return palaces_[normalize(index)];
}
bool PatternChart::has(int palace_index, PatternStar star) const noexcept {
  return at(palace_index).stars[offset(star)].present;
}
std::optional<int> PatternChart::find(PatternStar star) const noexcept {
  const auto value = locations_[offset(star)];
  return value < 0 ? std::nullopt : std::optional<int>{value};
}
const PatternStarState *PatternChart::state(int palace_index,
                                            PatternStar star) const noexcept {
  const auto &value = at(palace_index).stars[offset(star)];
  return value.present ? &value : nullptr;
}
bool PatternChart::has_transformation(int palace_index, SiHua value,
                                      bool major_only) const noexcept {
  const auto index = static_cast<std::size_t>(value);
  return major_only ? at(palace_index).major_transformations[index]
                    : at(palace_index).transformations[index];
}
bool PatternChart::in_ming_sanfang(PatternStar star) const noexcept {
  const auto location = find(star);
  if (!location)
    return false;
  const int delta = normalize(*location - ming_palace_);
  return delta == 0 || delta == 4 || delta == 6 || delta == 8;
}
bool PatternChart::ming_sanfang_has_all(
    std::initializer_list<PatternStar> stars) const noexcept {
  return std::ranges::all_of(
      stars, [this](auto star) { return in_ming_sanfang(star); });
}
bool PatternChart::ming_sanfang_has_any(
    std::initializer_list<PatternStar> stars) const noexcept {
  return std::ranges::any_of(
      stars, [this](auto star) { return in_ming_sanfang(star); });
}
int PatternChart::count_in_ming_sanfang(
    std::initializer_list<PatternStar> stars) const noexcept {
  return static_cast<int>(std::ranges::count_if(
      stars, [this](auto star) { return in_ming_sanfang(star); }));
}
std::array<int, 2> PatternChart::ming_adjacent() const noexcept {
  return {normalize(ming_palace_ - 1), normalize(ming_palace_ + 1)};
}
bool PatternChart::is_empty(int palace_index) const noexcept {
  for (std::size_t i = offset(PatternStar::ZiWei);
       i <= offset(PatternStar::PoJun); ++i) {
    if (at(palace_index).stars[i].present)
      return false;
  }
  return true;
}

std::optional<PatternStar> to_pattern_star(std::string_view name) noexcept {
#define ZHOUYI_PATTERN_STAR_CASE(text, value)                                  \
  case star_hash(text):                                                        \
    return name == text ? std::optional{PatternStar::value} : std::nullopt
  switch (star_hash(name)) {
    ZHOUYI_PATTERN_STAR_CASE("紫微", ZiWei);
    ZHOUYI_PATTERN_STAR_CASE("天机", TianJi);
    ZHOUYI_PATTERN_STAR_CASE("太阳", TaiYang);
    ZHOUYI_PATTERN_STAR_CASE("武曲", WuQu);
    ZHOUYI_PATTERN_STAR_CASE("天同", TianTong);
    ZHOUYI_PATTERN_STAR_CASE("廉贞", LianZhen);
    ZHOUYI_PATTERN_STAR_CASE("天府", TianFu);
    ZHOUYI_PATTERN_STAR_CASE("太阴", TaiYin);
    ZHOUYI_PATTERN_STAR_CASE("贪狼", TanLang);
    ZHOUYI_PATTERN_STAR_CASE("巨门", JuMen);
    ZHOUYI_PATTERN_STAR_CASE("天相", TianXiang);
    ZHOUYI_PATTERN_STAR_CASE("天梁", TianLiang);
    ZHOUYI_PATTERN_STAR_CASE("七杀", QiSha);
    ZHOUYI_PATTERN_STAR_CASE("破军", PoJun);
    ZHOUYI_PATTERN_STAR_CASE("左辅", ZuoFu);
    ZHOUYI_PATTERN_STAR_CASE("右弼", YouBi);
    ZHOUYI_PATTERN_STAR_CASE("文昌", WenChang);
    ZHOUYI_PATTERN_STAR_CASE("文曲", WenQu);
    ZHOUYI_PATTERN_STAR_CASE("天魁", TianKui);
    ZHOUYI_PATTERN_STAR_CASE("天钺", TianYue);
    ZHOUYI_PATTERN_STAR_CASE("禄存", LuCun);
    ZHOUYI_PATTERN_STAR_CASE("天马", TianMa);
    ZHOUYI_PATTERN_STAR_CASE("擎羊", QingYang);
    ZHOUYI_PATTERN_STAR_CASE("陀罗", TuoLuo);
    ZHOUYI_PATTERN_STAR_CASE("火星", HuoXing);
    ZHOUYI_PATTERN_STAR_CASE("铃星", LingXing);
    ZHOUYI_PATTERN_STAR_CASE("地空", DiKong);
    ZHOUYI_PATTERN_STAR_CASE("地劫", DiJie);
  default:
    return std::nullopt;
  }
#undef ZHOUYI_PATTERN_STAR_CASE
}

std::string_view pattern_star_name(PatternStar star) noexcept {
  static constexpr std::array<std::string_view,
                              static_cast<std::size_t>(PatternStar::Count)>
      names = {"紫微", "天机", "太阳", "武曲", "天同", "廉贞", "天府",
               "太阴", "贪狼", "巨门", "天相", "天梁", "七杀", "破军",
               "左辅", "右弼", "文昌", "文曲", "天魁", "天钺", "禄存",
               "天马", "擎羊", "陀罗", "火星", "铃星", "地空", "地劫"};
  return names[offset(star)];
}

bool is_bright(std::optional<LiangDu> brightness) noexcept {
  return brightness == LiangDu::Miao || brightness == LiangDu::Wang;
}
bool is_dim(std::optional<LiangDu> brightness) noexcept {
  return brightness == LiangDu::Xian;
}

} // namespace ZhouYi::ZiWei
