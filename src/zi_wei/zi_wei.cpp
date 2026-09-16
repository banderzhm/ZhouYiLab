// 紫微斗数核心排盘模块（实现）
module ZhouYi.ZiWei;

import std;
import fmt;
import ZhouYi.GanZhi;
import ZhouYi.ZhMapper;
import ZhouYi.tyme;
import ZhouYi.ZiWei.Constants;
import ZhouYi.ZiWei.Horoscope;
import ZhouYi.ZiWei.Star;

namespace ZhouYi::ZiWei {
using namespace std;
using namespace ZhouYi::GanZhi;

namespace {

/**
 * @brief 把 tyme 干支的天干映射为本仓库 TianGan
 *
 * 名称映射失败说明公共干支命名与 tyme 已经不一致，此处直接抛异常，不用索引
 * 硬转或静默降级掩盖口径漂移。
 */
TianGan to_tian_gan(const tyme::SixtyCycle &cycle) {
  const auto gan =
      GanZhi::Mapper::from_zh_gan(cycle.get_heaven_stem().get_name());
  if (!gan)
    throw runtime_error("tyme 天干名称无法映射到 TianGan：" +
                        cycle.get_heaven_stem().get_name());
  return *gan;
}

/** @brief 把 tyme 干支的地支映射为本仓库 DiZhi；映射失败即抛异常。 */
DiZhi to_di_zhi(const tyme::SixtyCycle &cycle) {
  const auto zhi =
      GanZhi::Mapper::from_zh_zhi(cycle.get_earth_branch().get_name());
  if (!zhi)
    throw runtime_error("tyme 地支名称无法映射到 DiZhi：" +
                        cycle.get_earth_branch().get_name());
  return *zhi;
}

/**
 * @brief 定位当前虚岁所属大限在 da_xian_data 中的存放下标
 *
 * 大限数据按宫位存放，同一虚岁只有一限成立，因此需要按年龄区间反查。
 * 边界口径：虚岁未到起运年龄时取起运限，超过末限结束年龄时取末限，两处都
 * 取最接近的既有大限，不额外造限，也不把童限算成另一套大限。
 *
 * @param da_xian_data 本命盘已排定的十二大限，下标为寅起宫序
 * @param age 当前虚岁
 * @return da_xian_data 的下标，范围 0-11
 */
int find_da_xian_slot(const array<DaXianData, 12> &da_xian_data, int age) {
  int first_slot = 0;
  int last_slot = 0;
  for (int slot = 0; slot < 12; ++slot) {
    const DaXianData &da_xian = da_xian_data[slot];
    if (age >= da_xian.start_age && age <= da_xian.end_age)
      return slot;
    if (da_xian.start_age < da_xian_data[first_slot].start_age)
      first_slot = slot;
    if (da_xian.start_age > da_xian_data[last_slot].start_age)
      last_slot = slot;
  }
  return age < da_xian_data[first_slot].start_age ? first_slot : last_slot;
}

} // namespace

HoroscopeResult ZiWeiResult::get_horoscope(int target_year, int target_month,
                                           int target_day, DiZhi target_hour,
                                           int current_age) const {
  if (current_age < 1)
    throw invalid_argument("虚岁必须从 1 起算");
  if (target_month < 1 || target_month > 12)
    throw invalid_argument("目标农历月必须在 1-12 之间");

  // 目标流年干支交给公共 tyme 干支年模块换算，本模块不另写年干支算法，
  // 也不顺着公历 1 月 1 日的节气月反查年份。
  const tyme::SixtyCycleYear liu_nian_year =
      tyme::SixtyCycleYear::from_year(target_year);
  const TianGan liu_nian_gan = to_tian_gan(liu_nian_year.get_sixty_cycle());
  const DiZhi liu_nian_zhi = to_di_zhi(liu_nian_year.get_sixty_cycle());

  // 流月干按五虎遁自流年干起正月（寅月），与流年干同源。
  const tyme::SixtyCycle liu_yue_cycle =
      liu_nian_year.get_first_month().next(target_month - 1).get_sixty_cycle();

  // 目标农历月日先换算到公历，再取流日干支；时辰地支取该支的起点钟点，
  // 子时因此落在 0 时，属早子时口径，不把流日顺延到次日。
  const tyme::SolarDay target_solar_day =
      tyme::LunarDay::from_ymd(target_year, target_month, target_day)
          .get_solar_day();
  const tyme::EightChar target_ba_zi =
      tyme::SolarTime::from_ymd_hms(
          target_solar_day.get_year(), target_solar_day.get_month(),
          target_solar_day.get_day(), 2 * static_cast<int>(target_hour), 0, 0)
          .get_lunar_hour()
          .get_eight_char();

  HoroscopeResult result{};
  result.da_xian = da_xian_data[find_da_xian_slot(da_xian_data, current_age)];
  result.xiao_xian = get_xiao_xian(current_age, is_male, year_pillar.zhi);
  result.liu_nian =
      get_liu_nian(target_year, liu_nian_gan, liu_nian_zhi, ming_gong_index);
  result.liu_yue =
      get_liu_yue(target_month, pai_pan_lunar_month, to_tian_gan(liu_yue_cycle),
                  to_di_zhi(liu_yue_cycle), liu_nian_zhi, ming_gong_index);
  result.liu_ri =
      get_liu_ri(target_day, to_tian_gan(target_ba_zi.get_day()),
                 to_di_zhi(target_ba_zi.get_day()), result.liu_yue.gong_index);
  result.liu_shi =
      get_liu_shi(target_hour, to_tian_gan(target_ba_zi.get_hour()),
                  result.liu_ri.gong_index);

  // 运限流曜：作用域决定前缀与额外星曜，落宫随各限自身的干支。
  result.da_xian_stars = get_horoscope_stars(
      result.da_xian.tian_gan, result.da_xian.di_zhi, Scope::Decadal);
  result.liu_nian_stars = get_horoscope_stars(
      result.liu_nian.tian_gan, result.liu_nian.di_zhi, Scope::Yearly);
  result.liu_yue_stars = get_horoscope_stars(
      result.liu_yue.tian_gan, result.liu_yue.di_zhi, Scope::Monthly);
  result.liu_ri_stars = get_horoscope_stars(result.liu_ri.tian_gan,
                                            result.liu_ri.di_zhi, Scope::Daily);
  result.liu_shi_stars = get_horoscope_stars(
      result.liu_shi.tian_gan, result.liu_shi.di_zhi, Scope::Hourly);

  // 岁前、将前是固定神煞表而非规则判断：只把输入地支从生年支换成目标流年支，
  // 继续复用既有定义表，不重写第二套口诀。
  const array<SuiQian12, 12> sui_qian = arrange_sui_qian_12(liu_nian_zhi);
  const array<JiangQian12, 12> jiang_qian = arrange_jiang_qian_12(liu_nian_zhi);

  for (int index = 0; index < 12; ++index) {
    PalaceHoroscopeTag &tag = result.palace_tags[index];
    tag.gong_index = index;
    tag.is_da_xian = (index == result.da_xian.gong_index);
    tag.is_xiao_xian = (index == result.xiao_xian.gong_index);
    tag.is_liu_nian = (index == result.liu_nian.gong_index);
    tag.sui_qian = sui_qian[index];
    tag.jiang_qian = jiang_qian[index];
  }

  return result;
}

} // namespace ZhouYi::ZiWei
