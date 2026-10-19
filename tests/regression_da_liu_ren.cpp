/**
 * @file regression_da_liu_ren.cpp
 * @brief 大六壬四课三传固定盘例回归。
 */
import ZhouYi.DaLiuRen.Controller;
import ZhouYi.GanZhi;
import std;

namespace {

using ZhouYi::DaLiuRen::Controller::DaLiuRenController;
using ZhouYi::GanZhi::DiZhi;

/**
 * @brief 核验丙寅日辰将加未时的遥克取传。
 *
 * 固定输入为公历 2026-10-19 13:09（北京时间，不提供地点、不作真太阳时修正）。
 * 独立参考盘的四课为寅/丙、亥/寅、亥/寅、申/亥；四课上下无直接贼克，
 * 二、三课上神亥水克日干丙火，应按蒿矢优先取亥发用，三传亥、申、巳。
 * 该案例专门防止把四课下神误当成遥克候选。
 */
void verify_hao_shi_uses_upper_deities() {
  const auto pan = DaLiuRenController::pai_pan_solar(2026, 10, 19, 13, 9);

  if (pan.si_ke.first.upper_zhi != DiZhi::Yin ||
      pan.si_ke.second.upper_zhi != DiZhi::Hai ||
      pan.si_ke.third.upper_zhi != DiZhi::Hai ||
      pan.si_ke.fourth.upper_zhi != DiZhi::Shen) {
    throw std::runtime_error("大六壬遥克固定盘四课上神偏离参考盘");
  }

  if (pan.san_chuan.get_chu_chuan() != DiZhi::Hai ||
      pan.san_chuan.get_zhong_chuan() != DiZhi::Shen ||
      pan.san_chuan.get_mo_chuan() != DiZhi::Si) {
    throw std::runtime_error("大六壬蒿矢固定盘应取亥、申、巳三传");
  }

  const auto &patterns = pan.san_chuan.get_ke_shi();
  if (patterns.size() < 2 || patterns.front() != "遥克" ||
      patterns[1] != "蒿矢卦") {
    throw std::runtime_error("大六壬遥克固定盘未标识蒿矢卦");
  }
}

} // namespace

int main() {
  try {
    verify_hao_shi_uses_upper_deities();
    return 0;
  } catch (const std::exception &error) {
    std::cerr << error.what() << '\n';
    return 1;
  }
}
