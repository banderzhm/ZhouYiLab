// C++23 Module - 五行工具实现
module ZhouYi.WuXingUtils;

import std;

namespace ZhouYi::WuXingUtils {

WuXing element_from_name(std::string_view name) {
  if (name.ends_with("木"))
    return WuXing::Mu;
  if (name.ends_with("火"))
    return WuXing::Huo;
  if (name.ends_with("土"))
    return WuXing::Tu;
  if (name.ends_with("金"))
    return WuXing::Jin;
  if (name.ends_with("水"))
    return WuXing::Shui;
  throw std::invalid_argument("名称末尾不是有效的五行中文字");
}

} // namespace ZhouYi::WuXingUtils
