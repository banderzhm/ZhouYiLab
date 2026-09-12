/**
 * @file hexagram_catalog.cppm
 * @brief 六十四卦公共卦名、卦义与八宫资料契约。
 */
export module ZhouYi.YiJing.HexagramCatalog;

import std;

export namespace ZhouYi::YiJing {

/** 六十四卦固定资料；爻码按初爻至上爻排列。 */
struct HexagramInfo {
  std::string name;          ///< 卦名。
  std::string meaning;       ///< 卦义提要。
  std::string fiveElement;   ///< 八宫所属五行。
  int shiYaoPosition{};      ///< 世爻位置，一至六。
  int yingYaoPosition{};     ///< 应爻位置，一至六。
  bool isYangHexagram{};     ///< 所属八宫是否为阳宫。
  std::string palaceType;    ///< 所属八宫。
  std::string innerHexagram; ///< 下卦。
  std::string outerHexagram; ///< 上卦。
  std::string structureType; ///< 本宫、游魂、归魂等结构。
};

/** 返回全项目唯一的六十四卦固定资料表。 */
const std::unordered_map<std::string, HexagramInfo> &hexagram_catalog();

/** 按初爻至上爻的六位阴阳码取得卦象资料。 */
const HexagramInfo &hexagram_info(std::string_view code);

} // namespace ZhouYi::YiJing
