/**
 * @file mei_hua.cppm
 * @brief 梅花易数排盘契约与基础推演接口。
 */
export module ZhouYi.MeiHua;

import ZhouYi.GanZhi;
import ZhouYi.ZhMapper;
import std;

export namespace ZhouYi::MeiHua {

/** 起卦法门。 */
enum class CastingMethod {
  LunarTime,    ///< 农历年月日时起卦。
  TwoNumbers,   ///< 两数起卦，前数为上卦、后数为下卦。
  ThreeNumbers, ///< 三数起卦，第三数专取动爻。
  StrokeCount,  ///< 字占笔画起卦，上下字组分别取卦。
  SoundCount    ///< 声音起卦，声数取上卦，声数加时支数取下卦与动爻。
};

/** 八卦，枚举值即邵雍先天卦数。 */
enum class Trigram {
  Qian = 1, ///< 乾一。
  Dui = 2,  ///< 兑二。
  Li = 3,   ///< 离三。
  Zhen = 4, ///< 震四。
  Xun = 5,  ///< 巽五。
  Kan = 6,  ///< 坎六。
  Gen = 7,  ///< 艮七。
  Kun = 8   ///< 坤八。
};

/** 一卦的固定象数属性。 */
struct TrigramInfo {
  Trigram trigram{Trigram::Qian};   ///< 八卦枚举。
  int number{1};                    ///< 先天卦数。
  std::string name;                 ///< 卦名。
  std::string symbol;               ///< Unicode 卦符。
  ZhouYi::GanZhi::WuXing element{}; ///< 五行。
  std::string nature;               ///< 天、泽、火等自然取象。
  std::string direction;            ///< 后天方位。
  std::string family;               ///< 家庭取象。
  std::string body_image;           ///< 人体取象。
  std::string color;                ///< 五色取象。
  std::string animal;               ///< 动物取象。
  std::string place_image;          ///< 场所取象。
  std::string object_image;         ///< 器物取象。
  std::string temperament;          ///< 性情取象。
};

/** 六十四卦在本卦、互卦或变卦阶段的快照。 */
struct HexagramSnapshot {
  std::string code;             ///< 初爻至上爻的阴阳码。
  std::string name;             ///< 六十四卦卦名。
  std::string meaning;          ///< 卦义提要。
  Trigram lower{Trigram::Qian}; ///< 下卦。
  Trigram upper{Trigram::Qian}; ///< 上卦。
};

/** 体用分判。 */
struct TiYong {
  Trigram ti{Trigram::Qian};   ///< 体卦，主己与所占之根本。
  Trigram yong{Trigram::Qian}; ///< 用卦，主事与外来作用。
  bool moving_line_in_lower{}; ///< 动爻是否落在下卦。
  std::string basis;           ///< 定体定用的梅花依据。
};

/** 梅花易数完整排盘。 */
struct MeiHuaPan {
  std::string schema_version{"mei-hua-pan/1.0"};   ///< 排盘契约版本。
  CastingMethod method{CastingMethod::TwoNumbers}; ///< 起卦法门。
  std::vector<long long> source_numbers;           ///< 实际参与取余的象数。
  long long upper_total{};                         ///< 取上卦的合数。
  long long lower_total{};                         ///< 取下卦的合数。
  long long moving_total{};                        ///< 取动爻的合数。
  int moving_line{1};                              ///< 动爻，初爻至上爻。
  HexagramSnapshot ben_gua;                        ///< 本卦，主事情初始。
  HexagramSnapshot hu_gua;                         ///< 互卦，主事情中段。
  HexagramSnapshot bian_gua;                       ///< 变卦，主事情归宿。
  TiYong ti_yong;                                  ///< 体用分判。
  std::string civil_time;         ///< 输入的民用时刻；数字起卦时为空。
  std::string casting_time;       ///< 实际用于起卦的时刻。
  std::string lunar_date;         ///< 时间起卦采用的农历日期。
  int lunar_month{};              ///< 农历月份，数字起卦时为零。
  bool lunar_leap_month{};        ///< 时间卦是否落在农历闰月。
  int year_branch_number{};       ///< 年支序数，子一至亥十二。
  int hour_branch_number{};       ///< 时支序数，子一至亥十二。
  bool true_solar_time_applied{}; ///< 是否使用真太阳时。
  double time_offset_minutes{};   ///< 真太阳时修正分钟数。
};

/** 返回八卦的固定象数资料。 */
const TrigramInfo &trigram_info(Trigram trigram);

/** 将任意整数按先天八卦数归一到一至八。 */
Trigram trigram_from_number(long long number);

/** 将初爻至上爻的六位阴阳码组成六十四卦快照。 */
HexagramSnapshot make_hexagram(std::string code);

/** 依上、下卦和动爻排出本卦、互卦、变卦及体用。 */
MeiHuaPan compose_pan(CastingMethod method, long long upper_number,
                      long long lower_number, long long moving_number);

} // namespace ZhouYi::MeiHua

namespace ZhouYi::Mapper {

/** CastingMethod 的梅花专业中文映射。 */
template <> struct ZhMap<ZhouYi::MeiHua::CastingMethod> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 5>{
        "年月日时起卦", "两数起卦", "三数起卦", "字占笔画起卦", "闻声起卦"};
  }
};

} // namespace ZhouYi::Mapper
