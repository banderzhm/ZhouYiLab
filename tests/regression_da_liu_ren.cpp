/**
 * @file regression_da_liu_ren.cpp
 * @brief 大六壬四课三传固定盘例回归。
 */
import ZhouYi.DaLiuRen;
import ZhouYi.DaLiuRen.Controller;
import ZhouYi.GanZhi;
import std;

namespace {

using ZhouYi::DaLiuRen::GanZhiKe;
using ZhouYi::DaLiuRen::SanChuan;
using ZhouYi::DaLiuRen::SiKe;
using ZhouYi::DaLiuRen::TianDiPan;
using ZhouYi::DaLiuRen::Controller::DaLiuRenController;
using ZhouYi::GanZhi::DiZhi;
using ZhouYi::GanZhi::TianGan;

/**
 * @brief 核验指定民用时间的三传。
 * @param year 公历年。
 * @param month 公历月。
 * @param day 公历日。
 * @param hour 民用小时。
 * @param minute 分钟。
 * @param expected 预期初、中、末传。
 * @param message 失败时的诊断信息。
 */
void verify_transmissions(int year, int month, int day, int hour, int minute,
                          const std::array<DiZhi, 3> &expected,
                          std::string_view message) {
  const auto pan =
      DaLiuRenController::pai_pan_solar(year, month, day, hour, minute);
  const std::array actual = {pan.san_chuan.get_chu_chuan(),
                             pan.san_chuan.get_zhong_chuan(),
                             pan.san_chuan.get_mo_chuan()};
  if (actual != expected) {
    throw std::runtime_error(std::string(message));
  }
}

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

/**
 * @brief 核验寅巳申三刑的方向，防止把巳刑申误写成巳刑酉。
 */
void verify_yin_si_shen_punishment() {
  using ZhouYi::GanZhi::is_xing;

  if (!is_xing(DiZhi::Yin, DiZhi::Si) || !is_xing(DiZhi::Si, DiZhi::Shen) ||
      !is_xing(DiZhi::Shen, DiZhi::Yin) || is_xing(DiZhi::Si, DiZhi::You)) {
    throw std::runtime_error("寅巳申三刑方向错误");
  }
}

/**
 * @brief 核验伏吟发用、自刑换路与杜传。
 *
 * 三例分别覆盖柔日有克、刚日无克且支为自刑、刚日无克且干支两侧均需换路。
 * 口径采用《六壬大全·订讹》所载“有克仍从克发用；无克刚日自干上发用、柔日
 * 自支上发用；初传自刑则干发用取支、支发用取干，中传仍自刑取其冲”。
 */
void verify_fu_yin_classical_routes() {
  verify_transmissions(2026, 1, 1, 1, 30, {DiZhi::Chen, DiZhi::Hai, DiZhi::Si},
                       "乙亥日伏吟有克应取辰、亥、巳");
  verify_transmissions(2026, 1, 8, 1, 30, {DiZhi::Hai, DiZhi::Wu, DiZhi::Zi},
                       "壬午日伏吟杜传应取亥、午、子");
  verify_transmissions(2026, 1, 18, 1, 30, {DiZhi::Hai, DiZhi::Chen, DiZhi::Xu},
                       "壬辰日伏吟杜传应取亥、辰、戌");
}

/**
 * @brief 核验返吟无亲课按日支驿马发用。
 */
void verify_fan_yin_no_relation_uses_day_branch() {
  verify_transmissions(2026, 1, 3, 13, 30,
                       {DiZhi::Hai, DiZhi::Wei, DiZhi::Chou},
                       "丁丑日返吟无亲应按日支取亥、未、丑");
}

/**
 * @brief 核验古籍固定涉害例“甲辰日，戌加寅，取子发用”。
 *
 * 该例直接构造天地盘，排除历法、月将换将和贵人昼夜口径的干扰。候选子涉害五重，
 * 候选戌涉害四重，故三传应为子、申、辰。
 */
void verify_she_hai_fixed_classical_example() {
  const TianDiPan tian_di_pan(DiZhi::Xu, DiZhi::Yin, DiZhi::Chou, true);
  const DiZhi gan_gong = ZhouYi::GanZhi::get_ji_gong(TianGan::Jia);
  const DiZhi first_upper = tian_di_pan[gan_gong];
  const DiZhi second_upper = tian_di_pan[first_upper];
  const DiZhi third_upper = tian_di_pan[DiZhi::Chen];
  const DiZhi fourth_upper = tian_di_pan[third_upper];
  const SiKe si_ke(
      GanZhiKe(TianGan::Jia, first_upper), GanZhiKe(first_upper, second_upper),
      GanZhiKe(DiZhi::Chen, third_upper), GanZhiKe(third_upper, fourth_upper),
      first_upper, third_upper);
  const SanChuan san_chuan(tian_di_pan, si_ke);

  if (san_chuan.get_chu_chuan() != DiZhi::Zi ||
      san_chuan.get_zhong_chuan() != DiZhi::Shen ||
      san_chuan.get_mo_chuan() != DiZhi::Chen) {
    throw std::runtime_error("甲辰日戌加寅涉害固定例应取子、申、辰");
  }
}

/**
 * @brief 核验八专必须按日柱判定，并按柔日支阴神逆数三位发用。
 */
void verify_ba_zhuan_day_and_reverse_counting() {
  verify_transmissions(2026, 2, 2, 1, 30, {DiZhi::Mao, DiZhi::Wu, DiZhi::Wu},
                       "丁未日八专应从支阴神巳逆数取卯、午、午");
  verify_transmissions(2026, 2, 15, 1, 30, {DiZhi::You, DiZhi::Wei, DiZhi::Wei},
                       "庚申日八专应从干上未顺数取酉、未、未");
}

/**
 * @brief 核验柔日别责以日支三合前一辰发用。
 */
void verify_bie_ze_yin_day_uses_day_branch_trine() {
  verify_transmissions(2026, 1, 23, 3, 30, {DiZhi::Chou, DiZhi::Si, DiZhi::Si},
                       "丁酉日别责应取日支三合前一辰丑、巳、巳");
}

} // namespace

int main() {
  try {
    verify_hao_shi_uses_upper_deities();
    verify_yin_si_shen_punishment();
    verify_fu_yin_classical_routes();
    verify_fan_yin_no_relation_uses_day_branch();
    verify_she_hai_fixed_classical_example();
    verify_ba_zhuan_day_and_reverse_counting();
    verify_bie_ze_yin_day_uses_day_branch_trine();
    return 0;
  } catch (const std::exception &error) {
    std::cerr << error.what() << '\n';
    return 1;
  }
}
