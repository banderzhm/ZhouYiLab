/**
 * 紫微斗数格局查询上下文。
 *
 * <p>本模块只负责把命盘星曜宫垣转换成高效的强类型索引，不包含任何格局
 * 取舍。格局引擎据此查询星曜、亮度、四化、三方四正与夹宫，避免在
 * 每条规则中反复扫描中文名称。</p>
 */
export module ZhouYi.ZiWei.Pattern;

import std;
import ZhouYi.GanZhi;
import ZhouYi.ZiWei.Constants;

export namespace ZhouYi::ZiWei {

/** 格局规则需要识别的星曜。 */
enum class PatternStar : std::uint8_t {
  ZiWei,
  TianJi,
  TaiYang,
  WuQu,
  TianTong,
  LianZhen,
  TianFu,
  TaiYin,
  TanLang,
  JuMen,
  TianXiang,
  TianLiang,
  QiSha,
  PoJun,
  ZuoFu,
  YouBi,
  WenChang,
  WenQu,
  TianKui,
  TianYue,
  LuCun,
  TianMa,
  QingYang,
  TuoLuo,
  HuoXing,
  LingXing,
  DiKong,
  DiJie,
  Count
};

/** 星曜在某宫的分类。 */
enum class PatternStarKind : std::uint8_t { Major, Benefic, Malefic, Minor };

/** 单颗星曜参与格局判断的星曜落宫记录。 */
struct PatternStarState {
  /** 星曜是否在本宫。 */
  bool present{false};
  /** 星曜分类。 */
  PatternStarKind kind{PatternStarKind::Minor};
  /** 庙旺利陷；资料口径未定义时为空。 */
  std::optional<LiangDu> brightness;
  /** 生年四化；没有四化时为空。 */
  std::optional<SiHua> transformation;
};

/** 单宫参与格局判断的宫垣星系记录。 */
struct PatternPalace {
  /** 宫位索引，以寅宫为零。 */
  int index{0};
  /** 宫位十二事项。 */
  GongWei palace{GongWei::MingGong};
  /** 宫位地支。 */
  GanZhi::DiZhi branch{GanZhi::DiZhi::Yin};
  /** 是否兼为身宫。 */
  bool is_body_palace{false};
  /** 按星曜枚举直接寻址的状态表。 */
  std::array<PatternStarState, static_cast<std::size_t>(PatternStar::Count)>
      stars{};
  /** 本宫四化存在位，按禄、权、科、忌直接寻址。 */
  std::array<bool, 4> transformations{};
  /** 本宫十四主星四化存在位。 */
  std::array<bool, 4> major_transformations{};
};

/**
 * 一次构建、只读复用的格局命盘上下文。
 *
 * <p>查询复杂度均为 O(1) 或固定十二宫 O(12)，不会在规则之间重复建立
 * 字符串集合。</p>
 */
class PatternChart {
public:
  /** 创建空的十二宫上下文。 */
  PatternChart();

  /** 设置命宫索引。 */
  void set_ming_palace(int index) noexcept;
  /** 设置身宫索引。 */
  void set_body_palace(int index) noexcept;
  /** 设置一个宫位的星曜宫垣。 */
  void set_palace(int index, GongWei palace, GanZhi::DiZhi branch,
                  bool is_body_palace) noexcept;
  /** 写入一颗星曜；不属于格局词表的杂曜会被安全忽略。 */
  void add_star(int palace_index, std::string_view name, PatternStarKind kind,
                std::optional<LiangDu> brightness,
                std::optional<SiHua> transformation);

  /** 返回命宫索引。 */
  [[nodiscard]] int ming_palace() const noexcept;
  /** 返回身宫索引。 */
  [[nodiscard]] int body_palace() const noexcept;
  /** 返回指定宫位。 */
  [[nodiscard]] const PatternPalace &at(int index) const noexcept;
  /** 判断指定宫位是否有某星。 */
  [[nodiscard]] bool has(int palace_index, PatternStar star) const noexcept;
  /** 返回星曜所在宫；未入盘时为空。 */
  [[nodiscard]] std::optional<int> find(PatternStar star) const noexcept;
  /** 返回星曜在指定宫的状态；不存在时为空。 */
  [[nodiscard]] const PatternStarState *state(int palace_index,
                                              PatternStar star) const noexcept;
  /** 判断本宫是否存在指定四化。 */
  [[nodiscard]] bool has_transformation(int palace_index, SiHua value,
                                        bool major_only = false) const noexcept;
  /** 判断星曜是否在命宫三方四正。 */
  [[nodiscard]] bool in_ming_sanfang(PatternStar star) const noexcept;
  /** 判断命宫三方四正是否同时包含给定星曜。 */
  [[nodiscard]] bool
  ming_sanfang_has_all(std::initializer_list<PatternStar> stars) const noexcept;
  /** 判断命宫三方四正是否包含任一给定星曜。 */
  [[nodiscard]] bool
  ming_sanfang_has_any(std::initializer_list<PatternStar> stars) const noexcept;
  /** 统计命宫三方四正的给定星曜数量。 */
  [[nodiscard]] int count_in_ming_sanfang(
      std::initializer_list<PatternStar> stars) const noexcept;
  /** 返回命宫左右夹宫索引。 */
  [[nodiscard]] std::array<int, 2> ming_adjacent() const noexcept;
  /** 返回宫位是否无十四主星。 */
  [[nodiscard]] bool is_empty(int palace_index) const noexcept;

private:
  std::array<PatternPalace, 12> palaces_{};
  std::array<std::int8_t, static_cast<std::size_t>(PatternStar::Count)>
      locations_{};
  int ming_palace_{0};
  int body_palace_{0};
};

/** 将排盘星名转换为格局星曜枚举。 */
[[nodiscard]] std::optional<PatternStar>
to_pattern_star(std::string_view name) noexcept;
/** 返回格局星曜的规范中文名。 */
[[nodiscard]] std::string_view pattern_star_name(PatternStar star) noexcept;
/** 判断亮度是否属于庙旺档。 */
[[nodiscard]] bool is_bright(std::optional<LiangDu> brightness) noexcept;
/** 判断亮度是否属于落陷档。 */
[[nodiscard]] bool is_dim(std::optional<LiangDu> brightness) noexcept;

} // namespace ZhouYi::ZiWei
