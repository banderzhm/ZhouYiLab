// C++23 - 大六壬行年（年宫）推算实现
module;

module ZhouYi.DaLiuRen.XingNian;

import ZhouYi.BaZiBase;
import ZhouYi.GanZhi;
import ZhouYi.tyme;
import std;

namespace ZhouYi::DaLiuRen {

namespace {

using ZhouYi::BaZiBase::BaZi;

/**
 * @brief 取公历时刻所处的「太岁年」（立春换年）与年干支。
 *
 * 不用公历年直接当年柱：立春前出生属上一年。这里复用上游 BaZiBase::from_solar
 * 算年柱，再反查该年柱对应的年份，避免在本模块另写一套节气判断。
 */
/**
 * @brief 干支的六十甲子序号（0 = 甲子，…，57 = 辛酉）。
 *
 * 六十甲子满足 index % 10 == 干序 且 index % 12 == 支序；对给定干序 g、支序 z，
 * 该同余方程组在 [0, 60) 内唯一解，用中国剩余定理直接解出，不查表、不引外部映射。
 */
int cycle_index_of(int gan, int zhi) {
  for (int index = 0; index < 60; ++index) {
    if (index % 10 == gan && index % 12 == zhi)
      return index;
  }
  return 0;
}

int cycle_year_of(int year, int month, int day, int hour, int minute) {
  const auto ba_zi = BaZi::from_solar(year, month, day, hour, minute);
  const int year_index =
      cycle_index_of(static_cast<int>(ba_zi.year.gan), static_cast<int>(ba_zi.year.zhi));
  // 年柱干支每 60 年循环一次；取与公历年最接近的那一个（立春前后最多差 1 年）。
  // 注意 SixtyCycleYear::get_name() 带「年」后缀（如 "辛酉年"），不能直接与年柱比较，
  // 因此统一比较六十甲子序号。
  for (int candidate : {year, year - 1, year + 1}) {
    if (tyme::SixtyCycleYear::from_year(candidate).get_sixty_cycle().get_index() == year_index)
      return candidate;
  }
  return year;
}

/// @brief 行年起点干支：古法按生年旬定，现代法固定。
/// @param birth_cycle_index 生年干支在六十甲子中的序号（0 = 甲子，…，57 = 辛酉）。
/// @param is_male 男命顺行、女命逆行。
/// @param rule 口径。
/// @return 一岁所值干支。
tyme::SixtyCycle starting_cycle(int birth_cycle_index, bool is_male,
                                XingNianRule rule) {
  if (rule == XingNianRule::Modern) {
    // 男一岁起丙寅，女一岁起壬申（壬申为丙寅之冲位，相差六支）。
    static const auto bing_yin = tyme::SixtyCycle::from_name("丙寅");
    return is_male ? bing_yin : tyme::SixtyCycle::from_name("壬申");
  }

  // 古法：按生年所值之旬定起点。原文六旬起点为
  //   甲子旬 男丙寅/女壬申、甲戌旬 丙子/壬午、甲申旬 丙戌/壬辰、
  //   甲午旬 丙申/壬寅、甲辰旬 丙午/壬子、甲寅旬 丙辰/壬戌。
  // 规律：男命天干恒为丙，地支随旬序每旬退两位（寅→子→戌→申→午→辰）；
  // 女命天干恒为壬，地支为男命起点之冲（相差六位），干支序号即男命起点 +6。
  const int decade_index = birth_cycle_index / 10; // 0=甲子旬 … 5=甲寅旬
  const int male_branch = ((2 - 2 * decade_index) % 12 + 12) % 12;
  int male_index = 0;
  for (int index = 0; index < 60; ++index) {
    if (index % 10 == 2 /*丙*/ && index % 12 == male_branch) {
      male_index = index;
      break;
    }
  }
  return tyme::SixtyCycle::from_index(is_male ? male_index
                                              : (male_index + 6) % 60);
}

} // namespace

DiZhi ben_ming_of(int year, int month, int day, int hour, int minute) {
  const auto ba_zi = BaZi::from_solar(year, month, day, hour, minute);
  return ba_zi.year.zhi;
}

XingNianResult calculate_xing_nian(const XingNianInput &input,
                                   XingNianRule rule) {
  const int birth_cycle =
      cycle_year_of(input.birth_year, input.birth_month, input.birth_day,
                    input.birth_hour, input.birth_minute);
  const int casting_cycle =
      cycle_year_of(input.casting_year, input.casting_month, input.casting_day,
                    input.casting_hour, input.casting_minute);
  if (casting_cycle < birth_cycle) {
    throw std::invalid_argument(
        "casting time is earlier than the birth time");
  }

  const int birth_cycle_index =
      tyme::SixtyCycleYear::from_year(birth_cycle).get_sixty_cycle().get_index();
  const auto start = starting_cycle(birth_cycle_index, input.is_male, rule);
  // 虚岁：出生当年记一岁；行年 = 起点顺（男）/逆（女）行 (虚岁 − 1) 位。
  const int nominal_age = casting_cycle - birth_cycle + 1;
  const int step = nominal_age - 1;
  // 男顺女逆；C++ 的 % 对负数返回负值，这里统一折回 [0, 60)。
  const int offset = input.is_male ? step : -step;
  const auto cycle = tyme::SixtyCycle::from_index(((start.get_index() + offset) % 60 + 60) % 60);

  XingNianResult result;
  result.nominal_age = nominal_age;
  result.birth_cycle_year = birth_cycle;
  result.ben_ming = ben_ming_of(input.birth_year, input.birth_month,
                                input.birth_day, input.birth_hour,
                                input.birth_minute);
  result.xing_nian =
      ZhouYi::GanZhi::Mapper::from_zh_zhi(cycle.get_earth_branch().get_name())
          .value_or(ZhouYi::GanZhi::DiZhi::Zi);
  result.xing_nian_gan_zhi = cycle.get_name();
  result.rule = rule;
  return result;
}

} // namespace ZhouYi::DaLiuRen
