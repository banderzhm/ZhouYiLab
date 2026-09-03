// C++23 - 盲派公共辅助实现
module ZhouYi.BaZiAnalysis.MangPai.Common;

import ZhouYi.GanZhi;

namespace ZhouYi::BaZiAnalysis::MangPai::Common {

bool is_body_god(ZhouYi::GanZhi::ShiShen god) {
  using enum ZhouYi::GanZhi::ShiShen;
  return god == BiJian || god == JieCai || god == ZhengYin || god == PianYin ||
         god == ShiShen;
}

bool is_target_god(ZhouYi::GanZhi::ShiShen god) {
  using enum ZhouYi::GanZhi::ShiShen;
  return god == ZhengCai || god == PianCai || god == ZhengGuan ||
         god == QiSha || god == ShangGuan;
}

} // namespace ZhouYi::BaZiAnalysis::MangPai::Common
