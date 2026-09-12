// C++23 Module - 大六壬领域类型
export module ZhouYi.DaLiuRen.Types;

import ZhouYi.ZhMapper;
import std;

/**
 * @brief 大六壬稳定领域枚举。
 *
 * 本模块不依赖排盘实现，用于避免分析规则和展示层散落中文常量。
 */
export namespace ZhouYi::DaLiuRen {

/** @brief 十二天将，顺序固定为贵、蛇、朱、六、勾、青、空、白、常、玄、阴、后。
 */
enum class TianJiang {
  GuiRen,   ///< 贵人。
  TengShe,  ///< 螣蛇。
  ZhuQue,   ///< 朱雀。
  LiuHe,    ///< 六合。
  GouChen,  ///< 勾陈。
  QingLong, ///< 青龙。
  TianKong, ///< 天空。
  BaiHu,    ///< 白虎。
  TaiChang, ///< 太常。
  XuanWu,   ///< 玄武。
  TaiYin,   ///< 太阴。
  TianHou   ///< 天后。
};

/** @brief 九宗门取传方法。 */
enum class JiuZongMen {
  ZeiKe,   ///< 贼克法。
  BiYong,  ///< 比用法。
  SheHai,  ///< 涉害法。
  YaoKe,   ///< 遥克法。
  MaoXing, ///< 昴星法。
  BieZe,   ///< 别责法。
  BaZhuan, ///< 八专法。
  FuYin,   ///< 伏吟法。
  FanYin,  ///< 返吟法。
  WeiZhi   ///< 现有排盘结果未能结构化识别。
};

} // namespace ZhouYi::DaLiuRen

namespace ZhouYi::Mapper {

/** @brief 十二天将中文映射。 */
template <> struct ZhMap<ZhouYi::DaLiuRen::TianJiang> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 12>{"贵人", "螣蛇", "朱雀", "六合",
                                            "勾陈", "青龙", "天空", "白虎",
                                            "太常", "玄武", "太阴", "天后"};
  }
};

/** @brief 九宗门中文映射。 */
template <> struct ZhMap<ZhouYi::DaLiuRen::JiuZongMen> {
  static constexpr auto get_map() {
    return std::array<std::string_view, 10>{"贼克", "比用",  "涉害", "遥克",
                                            "昴星", "别责",  "八专", "伏吟",
                                            "返吟", "未识别"};
  }
};

} // namespace ZhouYi::Mapper
