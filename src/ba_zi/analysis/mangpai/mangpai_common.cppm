// C++23 Module - 盲派公共辅助接口
export module ZhouYi.BaZiAnalysis.MangPai.Common;

import ZhouYi.GanZhi;

export namespace ZhouYi::BaZiAnalysis::MangPai::Common {

/** 判断十神是否属于命主可调用的体神。 */
bool is_body_god(ZhouYi::GanZhi::ShiShen god);
/** 判断十神是否属于盲派常用目标对象。 */
bool is_target_god(ZhouYi::GanZhi::ShiShen god);

} // namespace ZhouYi::BaZiAnalysis::MangPai::Common
