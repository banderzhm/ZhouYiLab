/**
 * @file mei_hua.cpp
 * @brief 梅花易数排盘基础推演实现。
 */
module ZhouYi.MeiHua;

import ZhouYi.YiJing.HexagramCatalog;
import std;

namespace ZhouYi::MeiHua {
namespace {
using ZhouYi::GanZhi::WuXing;

constexpr std::array<std::string_view, 9> trigram_codes{
    "", "111", "110", "101", "100", "011", "010", "001", "000"};

const std::array<TrigramInfo, 8> trigram_table{{
    {Trigram::Qian, 1, "乾", "☰", WuXing::Jin, "天", "西北", "父", "首、骨",
     "白", "马", "官署、高处", "金玉、钟镜、圆物", "刚健果决"},
    {Trigram::Dui, 2, "兑", "☱", WuXing::Jin, "泽", "正西", "少女", "口、肺",
     "白", "羊", "池泽、酒肆", "乐器、缺口金器", "喜悦善言"},
    {Trigram::Li, 3, "离", "☲", WuXing::Huo, "火", "正南", "中女", "目、心",
     "赤", "雉", "炉灶、明堂", "文书、灯火、甲胄", "明敏附丽"},
    {Trigram::Zhen, 4, "震", "☳", WuXing::Mu, "雷", "正东", "长男", "足、肝",
     "青碧", "龙", "道路、林野", "竹木、车鼓", "迅疾善动"},
    {Trigram::Xun, 5, "巽", "☴", WuXing::Mu, "风", "东南", "长女", "股、胆",
     "青绿", "鸡", "园林、驿路", "绳索、木器", "入而不决"},
    {Trigram::Kan, 6, "坎", "☵", WuXing::Shui, "水", "正北", "中男", "耳、肾",
     "黑", "猪", "江河、暗处", "酒水、弓轮", "险陷多虑"},
    {Trigram::Gen, 7, "艮", "☶", WuXing::Tu, "山", "东北", "少男", "手、脾",
     "黄", "狗", "山径、门阙", "土石、箱柜", "静止守成"},
    {Trigram::Kun, 8, "坤", "☷", WuXing::Tu, "地", "西南", "母", "腹、胃",
     "黄黑", "牛", "田野、仓廪", "布帛、陶瓦", "柔顺承载"},
}};

int normalized(long long value, int modulus) {
  const auto remainder = value % modulus;
  return static_cast<int>(remainder <= 0 ? remainder + modulus : remainder);
}

Trigram trigram_from_code(std::string_view code) {
  for (int number = 1; number <= 8; ++number) {
    if (trigram_codes[static_cast<std::size_t>(number)] == code)
      return static_cast<Trigram>(number);
  }
  throw std::invalid_argument("无效三爻卦码");
}
} // namespace

const TrigramInfo &trigram_info(Trigram trigram) {
  const auto number = static_cast<int>(trigram);
  if (number < 1 || number > 8)
    throw std::invalid_argument("八卦枚举超出先天卦数范围");
  return trigram_table[static_cast<std::size_t>(number - 1)];
}

Trigram trigram_from_number(long long number) {
  return static_cast<Trigram>(normalized(number, 8));
}

HexagramSnapshot make_hexagram(std::string code) {
  if (code.size() != 6 || !std::ranges::all_of(code, [](char value) {
        return value == '0' || value == '1';
      }))
    throw std::invalid_argument("六十四卦爻码必须由六位阴阳数组成");
  const auto lower = trigram_from_code(std::string_view(code).substr(0, 3));
  const auto upper = trigram_from_code(std::string_view(code).substr(3, 3));
  const auto &info = ZhouYi::YiJing::hexagram_info(code);
  return {.code = std::move(code),
          .name = info.name,
          .meaning = info.meaning,
          .lower = lower,
          .upper = upper};
}

MeiHuaPan compose_pan(CastingMethod method, long long upper_number,
                      long long lower_number, long long moving_number) {
  const auto upper = trigram_from_number(upper_number);
  const auto lower = trigram_from_number(lower_number);
  const int moving_line = normalized(moving_number, 6);
  const std::string ben_code =
      std::string(trigram_codes[static_cast<std::size_t>(lower)]) +
      std::string(trigram_codes[static_cast<std::size_t>(upper)]);
  auto bian_code = ben_code;
  const auto moving_index = static_cast<std::size_t>(moving_line - 1);
  bian_code[moving_index] = bian_code[moving_index] == '1' ? '0' : '1';
  const std::string hu_code = ben_code.substr(1, 3) + ben_code.substr(2, 3);
  const bool moving_in_lower = moving_line <= 3;
  const auto ti = moving_in_lower ? upper : lower;
  const auto yong = moving_in_lower ? lower : upper;
  return {.method = method,
          .source_numbers = {upper_number, lower_number, moving_number},
          .upper_total = upper_number,
          .lower_total = lower_number,
          .moving_total = moving_number,
          .moving_line = moving_line,
          .ben_gua = make_hexagram(ben_code),
          .hu_gua = make_hexagram(hu_code),
          .bian_gua = make_hexagram(bian_code),
          .ti_yong = {.ti = ti,
                      .yong = yong,
                      .moving_line_in_lower = moving_in_lower,
                      .basis = moving_in_lower
                                   ? "动爻在下卦，下卦为用、上卦为体"
                                   : "动爻在上卦，上卦为用、下卦为体"}};
}

} // namespace ZhouYi::MeiHua
