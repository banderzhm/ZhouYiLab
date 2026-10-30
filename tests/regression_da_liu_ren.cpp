/**
 * @file regression_da_liu_ren.cpp
 * @brief 大六壬四课三传固定盘例回归。
 */
import ZhouYi.DaLiuRen;
import ZhouYi.DaLiuRen.Controller;
import ZhouYi.DaLiuRen.ShenSha;
import ZhouYi.BaZiBase;
import ZhouYi.GanZhi;
import std;

namespace {

using ZhouYi::BaZiBase::BaZi;
using ZhouYi::BaZiBase::Pillar;
using ZhouYi::DaLiuRen::GanZhiKe;
using ZhouYi::DaLiuRen::SanChuan;
using ZhouYi::DaLiuRen::SiKe;
using ZhouYi::DaLiuRen::TianDiPan;
using ZhouYi::DaLiuRen::Controller::DaLiuRenController;
using ZhouYi::DaLiuRen::ShenSha::ShenShaEngine;
using ZhouYi::GanZhi::DiZhi;
using ZhouYi::GanZhi::TianGan;

/**
 * @brief 用指定年月日干支构造神煞测试输入。
 * @param year_gan 年干。
 * @param year_zhi 年支。
 * @param month_zhi 月支。
 * @param day_gan 日干。
 * @param day_zhi 日支。
 * @return 供神煞引擎使用的四柱；时柱固定为甲子。
 */
BaZi make_shensha_bazi(TianGan year_gan, DiZhi year_zhi, DiZhi month_zhi,
                       TianGan day_gan, DiZhi day_zhi) {
  return BaZi(Pillar(year_gan, year_zhi), Pillar(TianGan::Jia, month_zhi),
              Pillar(day_gan, day_zhi), Pillar(TianGan::Jia, DiZhi::Zi));
}

/**
 * @brief 核验日支六项神煞表。
 *
 * 循环覆盖十二日支，防止三合分组只在四个孟支上碰巧正确。
 */
void verify_day_branch_shensha_tables() {
  constexpr std::array yi_ma = {DiZhi::Yin, DiZhi::Hai, DiZhi::Shen, DiZhi::Si,
                                DiZhi::Yin, DiZhi::Hai, DiZhi::Shen, DiZhi::Si,
                                DiZhi::Yin, DiZhi::Hai, DiZhi::Shen, DiZhi::Si};
  constexpr std::array tao_hua = {DiZhi::You, DiZhi::Wu, DiZhi::Mao, DiZhi::Zi,
                                  DiZhi::You, DiZhi::Wu, DiZhi::Mao, DiZhi::Zi,
                                  DiZhi::You, DiZhi::Wu, DiZhi::Mao, DiZhi::Zi};
  constexpr std::array hua_gai = {DiZhi::Chen, DiZhi::Chou, DiZhi::Xu,
                                  DiZhi::Wei,  DiZhi::Chen, DiZhi::Chou,
                                  DiZhi::Xu,   DiZhi::Wei,  DiZhi::Chen,
                                  DiZhi::Chou, DiZhi::Xu,   DiZhi::Wei};
  constexpr std::array jie_sha = {DiZhi::Si,   DiZhi::Yin,  DiZhi::Hai,
                                  DiZhi::Shen, DiZhi::Si,   DiZhi::Yin,
                                  DiZhi::Hai,  DiZhi::Shen, DiZhi::Si,
                                  DiZhi::Yin,  DiZhi::Hai,  DiZhi::Shen};
  constexpr std::array zai_sha = {DiZhi::Wu, DiZhi::Mao, DiZhi::Zi, DiZhi::You,
                                  DiZhi::Wu, DiZhi::Mao, DiZhi::Zi, DiZhi::You,
                                  DiZhi::Wu, DiZhi::Mao, DiZhi::Zi, DiZhi::You};
  constexpr std::array wang_shen = {
      DiZhi::Hai, DiZhi::Shen, DiZhi::Si,  DiZhi::Yin,  DiZhi::Hai, DiZhi::Shen,
      DiZhi::Si,  DiZhi::Yin,  DiZhi::Hai, DiZhi::Shen, DiZhi::Si,  DiZhi::Yin};
  constexpr std::array po_sui = {
      DiZhi::Si, DiZhi::Chou, DiZhi::You, DiZhi::Si, DiZhi::Chou, DiZhi::You,
      DiZhi::Si, DiZhi::Chou, DiZhi::You, DiZhi::Si, DiZhi::Chou, DiZhi::You};
  constexpr std::array si_shen = {
      DiZhi::Mao, DiZhi::Chen, DiZhi::Si,  DiZhi::Wu, DiZhi::Wei,  DiZhi::Shen,
      DiZhi::You, DiZhi::Xu,   DiZhi::Hai, DiZhi::Zi, DiZhi::Chou, DiZhi::Yin};
  constexpr std::array zhi_yi = {
      DiZhi::Wu,  DiZhi::Si,   DiZhi::Chen, DiZhi::Mao, DiZhi::Yin, DiZhi::Chou,
      DiZhi::Wei, DiZhi::Shen, DiZhi::You,  DiZhi::Xu,  DiZhi::Hai, DiZhi::Zi};
  constexpr std::array zhi_de = {
      DiZhi::Si,  DiZhi::Wu, DiZhi::Wei,  DiZhi::Shen, DiZhi::You, DiZhi::Xu,
      DiZhi::Hai, DiZhi::Zi, DiZhi::Chou, DiZhi::Yin,  DiZhi::Mao, DiZhi::Chen};

  for (std::size_t index = 0; index < 12; ++index) {
    const auto day_zhi = static_cast<DiZhi>(index);
    const auto day_gan = static_cast<TianGan>(index % 10);
    const auto bazi = make_shensha_bazi(TianGan::Jia, DiZhi::Zi, DiZhi::Yin,
                                        day_gan, day_zhi);
    const auto result = ShenShaEngine(bazi).calculate();
    if (result.ri_yi_ma != yi_ma[index] || result.tao_hua != tao_hua[index] ||
        result.xian_chi != tao_hua[index] ||
        result.ri_hua_gai != hua_gai[index] ||
        result.ri_jie_sha != jie_sha[index] ||
        result.ri_zai_sha != zai_sha[index] ||
        result.ri_wang_shen != wang_shen[index] ||
        result.ri_po_sui != po_sui[index] ||
        result.ri_si_shen != si_shen[index] || result.zhi_yi != zhi_yi[index] ||
        result.zhi_de != zhi_de[index]) {
      throw std::runtime_error("大六壬日支神煞十二支表不一致");
    }
  }
}

/**
 * @brief 核验十二个月的逐月表、三合表与四季表。
 */
void verify_month_shensha_tables() {
  constexpr std::array yue_yi_ma = {DiZhi::Shen, DiZhi::Si,   DiZhi::Yin,
                                    DiZhi::Hai,  DiZhi::Shen, DiZhi::Si,
                                    DiZhi::Yin,  DiZhi::Hai,  DiZhi::Shen,
                                    DiZhi::Si,   DiZhi::Yin,  DiZhi::Hai};
  constexpr std::array huang_shu = {DiZhi::Yin,  DiZhi::Yin,  DiZhi::Yin,
                                    DiZhi::Si,   DiZhi::Si,   DiZhi::Si,
                                    DiZhi::Shen, DiZhi::Shen, DiZhi::Shen,
                                    DiZhi::Hai,  DiZhi::Hai,  DiZhi::Hai};
  constexpr std::array tian_zhao = {
      DiZhi::Hai, DiZhi::Zi, DiZhi::Chou, DiZhi::Yin,  DiZhi::Mao, DiZhi::Chen,
      DiZhi::Si,  DiZhi::Wu, DiZhi::Wei,  DiZhi::Shen, DiZhi::You, DiZhi::Xu};
  constexpr std::array tian_ma = {
      DiZhi::Wu, DiZhi::Shen, DiZhi::Xu, DiZhi::Zi, DiZhi::Yin, DiZhi::Chen,
      DiZhi::Wu, DiZhi::Shen, DiZhi::Xu, DiZhi::Zi, DiZhi::Yin, DiZhi::Chen};
  constexpr std::array cheng_shen = {
      DiZhi::Si,  DiZhi::Shen, DiZhi::Hai, DiZhi::Yin,  DiZhi::Si,  DiZhi::Shen,
      DiZhi::Hai, DiZhi::Yin,  DiZhi::Si,  DiZhi::Shen, DiZhi::Hai, DiZhi::Yin};
  constexpr std::array tian_yi = {
      DiZhi::Chen, DiZhi::Si,  DiZhi::Wu, DiZhi::Wei,  DiZhi::Shen, DiZhi::You,
      DiZhi::Xu,   DiZhi::Hai, DiZhi::Zi, DiZhi::Chou, DiZhi::Yin,  DiZhi::Mao};
  constexpr std::array di_yi = {
      DiZhi::Xu,   DiZhi::Hai, DiZhi::Zi, DiZhi::Chou, DiZhi::Yin,  DiZhi::Mao,
      DiZhi::Chen, DiZhi::Si,  DiZhi::Wu, DiZhi::Wei,  DiZhi::Shen, DiZhi::You};
  constexpr std::array fei_lian = {
      DiZhi::Xu,   DiZhi::Si,  DiZhi::Wu, DiZhi::Wei,  DiZhi::Shen, DiZhi::You,
      DiZhi::Chen, DiZhi::Hai, DiZhi::Zi, DiZhi::Chou, DiZhi::Yin,  DiZhi::Mao};
  constexpr std::array huang_en = {
      DiZhi::Xu, DiZhi::Chou, DiZhi::Chen, DiZhi::Wei, DiZhi::You,  DiZhi::Mao,
      DiZhi::Zi, DiZhi::Wu,   DiZhi::Yin,  DiZhi::Si,  DiZhi::Shen, DiZhi::Hai};
  constexpr std::array sang_po = {DiZhi::Wei,  DiZhi::Chen, DiZhi::Chou,
                                  DiZhi::Xu,   DiZhi::Wei,  DiZhi::Chen,
                                  DiZhi::Chou, DiZhi::Xu,   DiZhi::Wei,
                                  DiZhi::Chen, DiZhi::Chou, DiZhi::Xu};
  constexpr std::array jie_shen = {
      DiZhi::Shen, DiZhi::Shen, DiZhi::Xu,   DiZhi::Xu,   DiZhi::Zi, DiZhi::Zi,
      DiZhi::Yin,  DiZhi::Yin,  DiZhi::Chen, DiZhi::Chen, DiZhi::Wu, DiZhi::Wu};
  constexpr std::array xue_ji = {
      DiZhi::Chou, DiZhi::Wei, DiZhi::Yin, DiZhi::Shen, DiZhi::Mao, DiZhi::You,
      DiZhi::Chen, DiZhi::Xu,  DiZhi::Si,  DiZhi::Hai,  DiZhi::Wu,  DiZhi::Zi};
  constexpr std::array xin_shen = {DiZhi::Shen, DiZhi::Xu,   DiZhi::Yin,
                                   DiZhi::Chou, DiZhi::Hai,  DiZhi::Chen,
                                   DiZhi::Si,   DiZhi::Wei,  DiZhi::Si,
                                   DiZhi::Wei,  DiZhi::Shen, DiZhi::Xu};
  constexpr std::array tian_ji = {
      DiZhi::You, DiZhi::Shen, DiZhi::Wei,  DiZhi::Wu, DiZhi::Si,  DiZhi::Chen,
      DiZhi::Mao, DiZhi::Yin,  DiZhi::Chou, DiZhi::Zi, DiZhi::Hai, DiZhi::Xu};
  constexpr std::array yue_xing = {
      DiZhi::Si,  DiZhi::Zi,  DiZhi::Chen, DiZhi::Shen, DiZhi::Wu,  DiZhi::Chou,
      DiZhi::Yin, DiZhi::You, DiZhi::Wei,  DiZhi::Hai,  DiZhi::Mao, DiZhi::Xu};
  constexpr std::array jian_men = {
      DiZhi::Shen, DiZhi::Hai, DiZhi::Yin,  DiZhi::Si,  DiZhi::Shen, DiZhi::Hai,
      DiZhi::Yin,  DiZhi::Si,  DiZhi::Shen, DiZhi::Hai, DiZhi::Yin,  DiZhi::Si};
  constexpr std::array hua_gai = {DiZhi::Xu,   DiZhi::Wei,  DiZhi::Chen,
                                  DiZhi::Chou, DiZhi::Xu,   DiZhi::Wei,
                                  DiZhi::Chen, DiZhi::Chou, DiZhi::Xu,
                                  DiZhi::Wei,  DiZhi::Chen, DiZhi::Chou};
  constexpr std::array jie_sha = {
      DiZhi::Hai, DiZhi::Shen, DiZhi::Si,  DiZhi::Yin,  DiZhi::Hai, DiZhi::Shen,
      DiZhi::Si,  DiZhi::Yin,  DiZhi::Hai, DiZhi::Shen, DiZhi::Si,  DiZhi::Yin};
  constexpr std::array zai_sha = {DiZhi::Zi, DiZhi::You, DiZhi::Wu, DiZhi::Mao,
                                  DiZhi::Zi, DiZhi::You, DiZhi::Wu, DiZhi::Mao,
                                  DiZhi::Zi, DiZhi::You, DiZhi::Wu, DiZhi::Mao};
  constexpr std::array wang_shen = {DiZhi::Si,   DiZhi::Yin,  DiZhi::Hai,
                                    DiZhi::Shen, DiZhi::Si,   DiZhi::Yin,
                                    DiZhi::Hai,  DiZhi::Shen, DiZhi::Si,
                                    DiZhi::Yin,  DiZhi::Hai,  DiZhi::Shen};
  constexpr std::array si_shen = {
      DiZhi::Si,  DiZhi::Wu, DiZhi::Wei,  DiZhi::Shen, DiZhi::You, DiZhi::Xu,
      DiZhi::Hai, DiZhi::Zi, DiZhi::Chou, DiZhi::Yin,  DiZhi::Mao, DiZhi::Chen};
  constexpr std::array zei_shen = {
      DiZhi::Mao, DiZhi::Mao, DiZhi::Mao, DiZhi::Wu, DiZhi::Wu, DiZhi::Wu,
      DiZhi::You, DiZhi::You, DiZhi::You, DiZhi::Zi, DiZhi::Zi, DiZhi::Zi};

  for (std::size_t index = 0; index < 12; ++index) {
    const auto month_zhi = static_cast<DiZhi>((index + 2) % 12);
    const auto bazi = make_shensha_bazi(TianGan::Jia, DiZhi::Zi, month_zhi,
                                        TianGan::Jia, DiZhi::Zi);
    const auto result = ShenShaEngine(bazi).calculate();
    if (result.yue_jian != month_zhi || result.yue_po != month_zhi + 6 ||
        result.xiao_shi != month_zhi || result.yue_yi_ma != yue_yi_ma[index] ||
        result.huang_shu != huang_shu[index] ||
        result.huang_en != huang_en[index] ||
        result.tian_zhao != tian_zhao[index] ||
        result.fei_hun != tian_zhao[index] ||
        result.tian_ma != tian_ma[index] ||
        result.cheng_shen != cheng_shen[index] ||
        result.tian_yi != tian_yi[index] || result.di_yi != di_yi[index] ||
        result.jie_shen != jie_shen[index] || result.xue_ji != xue_ji[index] ||
        result.xin_shen != xin_shen[index] ||
        result.tian_ji != tian_ji[index] || result.sang_po != sang_po[index] ||
        result.yue_xing != yue_xing[index] ||
        result.jian_men != jian_men[index] ||
        result.yue_fei_lian != fei_lian[index] ||
        result.yue_hua_gai != hua_gai[index] ||
        result.yue_jie_sha != jie_sha[index] ||
        result.yue_zai_sha != zai_sha[index] ||
        result.yue_wang_shen != wang_shen[index] ||
        result.yue_si_shen != si_shen[index] ||
        result.zei_shen != zei_shen[index]) {
      throw std::runtime_error("大六壬月神煞十二月表不一致");
    }
  }
}

/**
 * @brief 核验日干神煞不依赖日支是否恰好落在目标宫。
 */
void verify_day_stem_shensha_tables() {
  constexpr std::array ri_de = {
      DiZhi::Yin, DiZhi::Shen, DiZhi::Si, DiZhi::Hai, DiZhi::Si,
      DiZhi::Yin, DiZhi::Shen, DiZhi::Si, DiZhi::Hai, DiZhi::Si};
  constexpr std::array ri_lu = {DiZhi::Yin, DiZhi::Mao, DiZhi::Si,   DiZhi::Wu,
                                DiZhi::Si,  DiZhi::Wu,  DiZhi::Shen, DiZhi::You,
                                DiZhi::Hai, DiZhi::Zi};
  constexpr std::array yang_ren = {
      DiZhi::Mao, DiZhi::Chen, DiZhi::Wu, DiZhi::Wei, DiZhi::Wu,
      DiZhi::Wei, DiZhi::You,  DiZhi::Xu, DiZhi::Zi,  DiZhi::Chou};
  constexpr std::array ri_qi = {
      DiZhi::Wu,   DiZhi::Si,  DiZhi::Chen, DiZhi::Mao, DiZhi::Yin,
      DiZhi::Chou, DiZhi::Wei, DiZhi::Shen, DiZhi::You, DiZhi::Xu};

  for (std::size_t index = 0; index < 10; ++index) {
    const auto day_gan = static_cast<TianGan>(index);
    const auto day_zhi = static_cast<DiZhi>(index);
    const auto bazi = make_shensha_bazi(TianGan::Jia, DiZhi::Zi, DiZhi::Yin,
                                        day_gan, day_zhi);
    const auto result = ShenShaEngine(bazi).calculate();
    if (result.ri_de != ri_de[index] || result.ri_lu != ri_lu[index] ||
        result.ri_qi != ri_qi[index] || result.yang_ren != yang_ren[index]) {
      throw std::runtime_error("大六壬日德、日禄或羊刃干表错误");
    }
  }
}

/**
 * @brief 核验十二年支与十二月支中的关键神煞。
 */
void verify_year_and_month_shensha_tables() {
  constexpr std::array year_yi_ma = {
      DiZhi::Yin,  DiZhi::Hai, DiZhi::Shen, DiZhi::Si,  DiZhi::Yin,  DiZhi::Hai,
      DiZhi::Shen, DiZhi::Si,  DiZhi::Yin,  DiZhi::Hai, DiZhi::Shen, DiZhi::Si};
  constexpr std::array year_xing = {
      DiZhi::Mao, DiZhi::Xu,   DiZhi::Si,  DiZhi::Zi,  DiZhi::Chen, DiZhi::Shen,
      DiZhi::Wu,  DiZhi::Chou, DiZhi::Yin, DiZhi::You, DiZhi::Wei,  DiZhi::Hai};
  constexpr std::array year_he = {
      DiZhi::Chou, DiZhi::Zi, DiZhi::Hai, DiZhi::Xu,   DiZhi::You, DiZhi::Shen,
      DiZhi::Wei,  DiZhi::Wu, DiZhi::Si,  DiZhi::Chen, DiZhi::Mao, DiZhi::Yin};
  constexpr std::array year_jie_sha = {DiZhi::Si,   DiZhi::Yin,  DiZhi::Hai,
                                       DiZhi::Shen, DiZhi::Si,   DiZhi::Yin,
                                       DiZhi::Hai,  DiZhi::Shen, DiZhi::Si,
                                       DiZhi::Yin,  DiZhi::Hai,  DiZhi::Shen};
  constexpr std::array year_wang_shen = {
      DiZhi::Hai, DiZhi::Shen, DiZhi::Si,  DiZhi::Yin,  DiZhi::Hai, DiZhi::Shen,
      DiZhi::Si,  DiZhi::Yin,  DiZhi::Hai, DiZhi::Shen, DiZhi::Si,  DiZhi::Yin};
  constexpr std::array hong_luan = {
      DiZhi::Mao, DiZhi::Yin,  DiZhi::Chou, DiZhi::Zi, DiZhi::Hai, DiZhi::Xu,
      DiZhi::You, DiZhi::Shen, DiZhi::Wei,  DiZhi::Wu, DiZhi::Si,  DiZhi::Chen};
  constexpr std::array month_po_sui = {
      DiZhi::You, DiZhi::Si, DiZhi::Chou, DiZhi::You, DiZhi::Si, DiZhi::Chou,
      DiZhi::You, DiZhi::Si, DiZhi::Chou, DiZhi::You, DiZhi::Si, DiZhi::Chou};
  constexpr std::array month_yan = {
      DiZhi::Xu,   DiZhi::You, DiZhi::Shen, DiZhi::Wei,  DiZhi::Wu, DiZhi::Si,
      DiZhi::Chen, DiZhi::Mao, DiZhi::Yin,  DiZhi::Chou, DiZhi::Zi, DiZhi::Hai};

  for (std::size_t index = 0; index < 12; ++index) {
    const auto branch = static_cast<DiZhi>(index);
    const auto stem = static_cast<TianGan>(index % 10);
    const auto month_zhi = static_cast<DiZhi>((index + 2) % 12);
    const auto bazi =
        make_shensha_bazi(stem, branch, month_zhi, TianGan::Jia, DiZhi::Zi);
    const auto result = ShenShaEngine(bazi).calculate();
    if (result.tai_sui != branch || result.da_hao != branch + 6 ||
        result.xiao_hao != branch + 5 || result.bing_fu != branch + (-1) ||
        result.nian_yi_ma != year_yi_ma[index] ||
        result.sui_xing != year_xing[index] ||
        result.sui_he != year_he[index] ||
        result.jie_sha != year_jie_sha[index] ||
        result.wang_shen != year_wang_shen[index] ||
        result.hong_luan != hong_luan[index] ||
        result.yue_he != year_he[(index + 2) % 12] ||
        result.sang_men != branch + 2 || result.diao_ke != branch + (-2) ||
        result.po_sui != month_po_sui[index] ||
        result.yue_yan != month_yan[index]) {
      throw std::runtime_error("大六壬年煞或月煞十二支表错误");
    }
  }
}

/** @brief 核验岁德与死符按年干取值，不受年支变化影响。 */
void verify_year_stem_shensha_tables() {
  constexpr std::array sui_de = {
      DiZhi::Yin, DiZhi::Shen, DiZhi::Si, DiZhi::Hai, DiZhi::Si,
      DiZhi::Yin, DiZhi::Shen, DiZhi::Si, DiZhi::Hai, DiZhi::Si};
  constexpr std::array si_fu = {
      DiZhi::Shen, DiZhi::Shen, DiZhi::Hai, DiZhi::Hai,  DiZhi::Yin,
      DiZhi::Yin,  DiZhi::Si,   DiZhi::Si,  DiZhi::Chen, DiZhi::Chen};

  for (std::size_t index = 0; index < sui_de.size(); ++index) {
    const auto year_gan = static_cast<TianGan>(index);
    const auto bazi = make_shensha_bazi(year_gan, DiZhi::Zi, DiZhi::Yin,
                                        TianGan::Jia, DiZhi::Zi);
    const auto result = ShenShaEngine(bazi).calculate();
    if (result.sui_de != sui_de[index] || result.si_fu != si_fu[index])
      throw std::runtime_error("大六壬岁德或死符年干表不一致");
  }
}

/**
 * @brief 核验六旬的旬首、旬奇和旬丁。
 */
void verify_xun_shensha() {
  constexpr std::array xun_heads = {DiZhi::Zi, DiZhi::Xu,   DiZhi::Shen,
                                    DiZhi::Wu, DiZhi::Chen, DiZhi::Yin};
  constexpr std::array xun_qi = {DiZhi::Chou, DiZhi::Chou, DiZhi::Zi,
                                 DiZhi::Zi,   DiZhi::Hai,  DiZhi::Hai};
  for (std::size_t index = 0; index < xun_heads.size(); ++index) {
    const auto bazi = make_shensha_bazi(TianGan::Jia, DiZhi::Zi, DiZhi::Yin,
                                        TianGan::Jia, xun_heads[index]);
    const auto result = ShenShaEngine(bazi).calculate();
    if (result.xun_yi != xun_heads[index] || result.xun_qi != xun_qi[index] ||
        result.xun_ding != xun_heads[index] + 3 ||
        result.xun_yi_sha != xun_heads[index] + 1) {
      throw std::runtime_error("大六壬旬仪、旬奇、旬丁或旬乙错误");
    }
  }
}

/**
 * @brief 核验结构化映射没有遗漏日德与日禄。
 */
void verify_shensha_mapping_contains_stem_rules() {
  const auto bazi = make_shensha_bazi(TianGan::Jia, DiZhi::Zi, DiZhi::Yin,
                                      TianGan::Yi, DiZhi::Chou);
  const auto result = ShenShaEngine(bazi).calculate();
  const auto ri_de_names = result.get_shensha_on_zhi(DiZhi::Shen);
  const auto ri_lu_names = result.get_shensha_on_zhi(DiZhi::Mao);
  if (!std::ranges::contains(ri_de_names, std::string("日德")) ||
      !std::ranges::contains(ri_lu_names, std::string("日禄"))) {
    throw std::runtime_error("大六壬地支神煞映射遗漏日德或日禄");
  }
}

/** @brief 穷举年支、月支和六十甲子，确保同一地支不重复登记同名神煞。 */
void verify_shensha_mapping_has_no_duplicate_names() {
  for (std::size_t year_index = 0; year_index < 12; ++year_index) {
    for (std::size_t month_index = 0; month_index < 12; ++month_index) {
      for (std::size_t day_index = 0; day_index < 60; ++day_index) {
        const auto year_gan = static_cast<TianGan>(year_index % 10);
        const auto year_zhi = static_cast<DiZhi>(year_index);
        const auto month_zhi = static_cast<DiZhi>(month_index);
        const auto day_gan = static_cast<TianGan>(day_index % 10);
        const auto day_zhi = static_cast<DiZhi>(day_index % 12);
        const auto bazi =
            make_shensha_bazi(year_gan, year_zhi, month_zhi, day_gan, day_zhi);
        const auto result = ShenShaEngine(bazi).calculate();
        for (const auto &[branch, names] : result.zhi_to_shensha_map) {
          auto sorted_names = names;
          std::ranges::sort(sorted_names);
          if (std::ranges::adjacent_find(sorted_names) != sorted_names.end())
            throw std::runtime_error("大六壬地支神煞映射存在同名重复项");
        }
      }
    }
  }
}

/**
 * @brief 核验中文报告与 JSON 按年、月、旬、日归类同一批神煞。
 */
void verify_shensha_report_contract() {
  const auto bazi = make_shensha_bazi(TianGan::Jia, DiZhi::Zi, DiZhi::Yin,
                                      TianGan::Yi, DiZhi::Chou);
  const auto result = ShenShaEngine(bazi).calculate();
  const auto json = result.to_json();
  const auto text = result.to_string();

  if (!json.at("年").contains("死符") || !json.at("年").contains("红鸾") ||
      json.at("年").contains("飞廉") || json.at("月").contains("红鸾") ||
      !json.at("月").contains("成神") || !json.at("月").contains("解神") ||
      !json.at("月").contains("天巫") || !json.at("月").contains("飞廉") ||
      !json.at("月").contains("丧魄") || json.at("月").contains("丧魂") ||
      json.at("月").contains("游魂") || !json.at("月").contains("飞魂") ||
      !json.at("月").contains("华盖") || !json.at("月").contains("劫煞") ||
      !json.at("月").contains("灾煞") || !json.at("月").contains("亡神") ||
      !json.at("月").contains("死神") || !json.at("月").contains("贼神") ||
      !json.at("旬").contains("旬奇") || !json.at("旬").contains("旬仪") ||
      !json.at("旬").contains("旬丁") || !json.at("旬").contains("旬乙") ||
      json.at("日").contains("旬奇") || json.at("日").contains("贼神") ||
      !json.at("日").contains("华盖") || !json.at("日").contains("劫煞") ||
      !json.at("日").contains("灾煞") || !json.at("日").contains("亡神") ||
      !json.at("日").contains("破碎") || !json.at("日").contains("支死神") ||
      text.contains("红弯")) {
    throw std::runtime_error("大六壬神煞中文报告或 JSON 分类错误");
  }
}

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
    verify_day_branch_shensha_tables();
    verify_day_stem_shensha_tables();
    verify_year_and_month_shensha_tables();
    verify_year_stem_shensha_tables();
    verify_month_shensha_tables();
    verify_xun_shensha();
    verify_shensha_mapping_contains_stem_rules();
    verify_shensha_mapping_has_no_duplicate_names();
    verify_shensha_report_contract();
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
