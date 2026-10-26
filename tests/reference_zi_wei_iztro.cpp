// 紫微斗数 iztro 跨实现对照数据导出器
//
// 本程序只调用 ZhouYiLab 生产模块并输出结构化结果，不包含 iztro 算法。
// 外部对照由 tests/reference/compare_zi_wei_iztro.cjs 完成。

import ZhouYi.GanZhi;
import ZhouYi.ZhMapper;
import ZhouYi.ZiWei;
import ZhouYi.ZiWei.Brightness;
import ZhouYi.ZiWei.Constants;
import ZhouYi.ZiWei.Horoscope;
import fmt;
import nlohmann.json;
import std;

using namespace ZhouYi::GanZhi;
using namespace ZhouYi::ZiWei;
using namespace std;

namespace {

/** @brief 单个跨实现对照命例；日期均为已锁定的公历出生日期与农历目标日期。 */
struct CaseSpec {
  string_view id;
  int birth_year;
  int birth_month;
  int birth_day;
  int birth_hour;
  bool is_male;
  int target_lunar_year;
  int target_lunar_month;
  int target_lunar_day;
  DiZhi target_hour;
  int nominal_age;
  string_view coverage;
};

constexpr array<CaseSpec, 20> CASES = {{
    {"C01", 1984, 2, 2, 0, true, 2024, 1, 1, DiZhi::Zi, 41,
     "春节当天且立春前／子时／农历年界"},
    {"C02", 1986, 1, 29, 2, false, 2025, 12, 20, DiZhi::Chou, 41,
     "乙丑阴女顺行／丑时／跨公历年目标"},
    {"C03", 1988, 5, 23, 4, false, 2026, 2, 18, DiZhi::Mao, 39,
     "戊辰阳女逆行／寅时／二月十八"},
    {"C04", 1990, 5, 28, 6, true, 2027, 8, 15, DiZhi::Chen, 38,
     "庚午阳男顺行／卯时／八月十五"},
    {"C05", 1991, 2, 21, 8, true, 2025, 11, 9, DiZhi::Si, 35,
     "辛未阴男逆行／辰时／旧本命基准复用"},
    {"C06", 1996, 9, 27, 10, false, 2026, 8, 15, DiZhi::Wu, 31,
     "丙子阳女逆行／巳时／中秋目标"},
    {"C07", 2000, 7, 16, 16, true, 2026, 3, 5, DiZhi::Wu, 27,
     "庚辰阳男顺行／申时／既有运限回归命例"},
    {"C08", 2004, 4, 9, 14, false, 2030, 12, 15, DiZhi::Shen, 27,
     "甲申阳女逆行／闰二月出生／未时"},
    {"C09", 2012, 5, 30, 18, true, 2032, 4, 10, DiZhi::Xu, 21,
     "壬辰阳男顺行／闰四月出生／酉时"},
    {"C10", 2020, 5, 23, 22, false, 2028, 6, 18, DiZhi::Hai, 9,
     "庚子阳女逆行／闰四月出生／亥时"},
    {"C11", 1984, 2, 1, 23, false, 1986, 1, 1, DiZhi::Zi, 4,
     "除夕末日／晚子时／首个大限起点"},
    {"C12", 1984, 2, 2, 12, false, 1987, 12, 29, DiZhi::Hai, 4,
     "春节当天且立春前／午时／首个大限起点"},
    {"C13", 1984, 2, 4, 12, true, 1996, 12, 29, DiZhi::Zi, 13,
     "立春当天／午时／首个大限终点"},
    {"C14", 2000, 2, 4, 20, true, 2004, 1, 1, DiZhi::Xu, 6,
     "立春日且春节前／戌时／首个大限起点"},
    {"C15", 2000, 2, 5, 12, false, 2010, 12, 30, DiZhi::Zi, 11,
     "春节次日／午时／首个大限终点／腊月三十"},
    {"C16", 1900, 1, 31, 0, true, 1904, 1, 1, DiZhi::Zi, 5,
     "参考历法下界／春节／子时／首个大限起点"},
    {"C17", 2033, 12, 22, 20, true, 2034, 1, 1, DiZhi::Hai, 2,
     "2033 闰十一月首日／戌时／次年首月"},
    {"C18", 2090, 1, 30, 22, false, 2094, 12, 29, DiZhi::Zi, 5,
     "远未来春节／亥时／首个大限起点／岁末"},
    {"C19", 2008, 2, 6, 0, true, 2009, 12, 30, DiZhi::Hai, 3,
     "除夕出生／子时／首个大限起点／腊月三十"},
    {"C20", 2008, 2, 7, 0, false, 2009, 1, 1, DiZhi::Zi, 2,
     "春节出生／子时／首个大限起点／正月初一"},
}};

string zh(auto value) { return string(ZhouYi::Mapper::to_zh(value)); }

string gan_zh(TianGan value) {
  return string(ZhouYi::GanZhi::Mapper::to_zh(value));
}

string zhi_zh(DiZhi value) {
  return string(ZhouYi::GanZhi::Mapper::to_zh(value));
}

nlohmann::json star_groups(const array<HoroscopeStarData, 12> &groups) {
  nlohmann::json result = nlohmann::json::array();
  for (int index = 0; index < 12; ++index)
    result.push_back(nlohmann::json::array());
  for (const auto &group : groups) {
    auto names = group.stars;
    ranges::sort(names);
    result[static_cast<size_t>(group.gong_index)] = std::move(names);
  }
  return result;
}

nlohmann::json limit_json(const DaXianData &value,
                          const array<HoroscopeStarData, 12> &stars) {
  return {{"index", value.gong_index},
          {"start_age", value.start_age},
          {"end_age", value.end_age},
          {"heavenly_stem", gan_zh(value.tian_gan)},
          {"earthly_branch", zhi_zh(value.di_zhi)},
          {"mutagen", value.si_hua},
          {"stars", star_groups(stars)}};
}

nlohmann::json limit_json(const LiuNianData &value,
                          const array<HoroscopeStarData, 12> &stars) {
  return {{"index", value.gong_index},
          {"heavenly_stem", gan_zh(value.tian_gan)},
          {"earthly_branch", zhi_zh(value.di_zhi)},
          {"mutagen", value.si_hua},
          {"stars", star_groups(stars)}};
}

nlohmann::json limit_json(const LiuYueData &value,
                          const array<HoroscopeStarData, 12> &stars) {
  return {{"index", value.gong_index},
          {"heavenly_stem", gan_zh(value.tian_gan)},
          {"earthly_branch", zhi_zh(value.di_zhi)},
          {"mutagen", value.si_hua},
          {"stars", star_groups(stars)}};
}

nlohmann::json limit_json(const LiuRiData &value,
                          const array<HoroscopeStarData, 12> &stars) {
  return {{"index", value.gong_index},
          {"heavenly_stem", gan_zh(value.tian_gan)},
          {"earthly_branch", zhi_zh(value.di_zhi)},
          {"mutagen", value.si_hua},
          {"stars", star_groups(stars)}};
}

nlohmann::json brightness_tables() {
  nlohmann::json result;
  constexpr array<ZhuXing, 14> major = {
      ZhuXing::ZiWei,  ZhuXing::TianJi,    ZhuXing::TaiYang,
      ZhuXing::WuQu,   ZhuXing::TianTong,  ZhuXing::LianZhen,
      ZhuXing::TianFu, ZhuXing::TaiYin,    ZhuXing::TanLang,
      ZhuXing::JuMen,  ZhuXing::TianXiang, ZhuXing::TianLiang,
      ZhuXing::QiSha,  ZhuXing::PoJun,
  };
  for (const ZhuXing star : major) {
    nlohmann::json values = nlohmann::json::array();
    for (const LiangDu value :
         get_zhu_xing_liang_du_table(star, BrightnessSchool::Iztro))
      values.push_back(zh(value));
    result[zh(star)] = std::move(values);
  }

  constexpr array<FuXing, 2> minor = {FuXing::WenChang, FuXing::WenQu};
  for (const FuXing star : minor) {
    nlohmann::json values = nlohmann::json::array();
    for (const LiangDu value :
         get_fu_xing_liang_du_table(star, BrightnessSchool::Iztro))
      values.push_back(zh(value));
    result[zh(star)] = std::move(values);
  }

  constexpr array<ShaXing, 4> malefic = {ShaXing::HuoXing, ShaXing::LingXing,
                                         ShaXing::QingYang, ShaXing::TuoLuo};
  for (const ShaXing star : malefic) {
    nlohmann::json values = nlohmann::json::array();
    for (const LiangDu value :
         get_sha_xing_liang_du_table(star, BrightnessSchool::Iztro))
      values.push_back(zh(value));
    result[zh(star)] = std::move(values);
  }
  return result;
}

nlohmann::json case_json(const CaseSpec &spec) {
  const ZiWeiResult chart =
      pai_pan_solar(spec.birth_year, spec.birth_month, spec.birth_day,
                    spec.birth_hour, spec.is_male, nullopt,
                    {.brightness_school = BrightnessSchool::Iztro,
                     .kui_yue_school = KuiYueSchool::QuanShuOne});
  const HoroscopeResult horoscope = chart.get_horoscope(
      spec.target_lunar_year, spec.target_lunar_month, spec.target_lunar_day,
      spec.target_hour, spec.nominal_age);

  nlohmann::json palaces = nlohmann::json::array();
  for (int index = 0; index < 12; ++index) {
    vector<nlohmann::json> major_stars;
    for (const StarData &star : chart.palaces[index].zhu_xing)
      major_stars.push_back(
          {{"name", star.name},
           {"brightness", star.liang_du ? zh(*star.liang_du) : ""}});
    ranges::sort(major_stars, [](const auto &left, const auto &right) {
      return left.at("name").template get<string>() <
             right.at("name").template get<string>();
    });
    palaces.push_back(
        {{"index", index},
         {"heavenly_stem", gan_zh(chart.palaces[index].gong_data.tian_gan)},
         {"earthly_branch", zhi_zh(chart.palaces[index].gong_data.di_zhi)},
         {"palace_name", zh(chart.palaces[index].gong_data.gong_wei)},
         {"major_stars", std::move(major_stars)}});
  }

  return {
      {"id", spec.id},
      {"coverage", spec.coverage},
      {"birth",
       {{"solar_date", fmt::format("{:04}-{:02}-{:02}", spec.birth_year,
                                   spec.birth_month, spec.birth_day)},
        {"hour", spec.birth_hour},
        {"time_index", static_cast<int>(chart.hour_pillar.zhi)},
        {"gender", spec.is_male ? "男" : "女"}}},
      {"target",
       {{"lunar_year", spec.target_lunar_year},
        {"lunar_month", spec.target_lunar_month},
        {"lunar_day", spec.target_lunar_day},
        {"time_index", static_cast<int>(spec.target_hour)},
        {"nominal_age", spec.nominal_age}}},
      {"natal",
       {{"birth_year_pillar", chart.lunar_year_pillar.to_string()},
        {"eight_char_year_pillar", chart.year_pillar.to_string()},
        {"soul_index", chart.ming_gong_index},
        {"body_index", chart.shen_gong_index},
        {"five_elements_class", zh(chart.wu_xing_ju)},
        {"palaces", std::move(palaces)}}},
      {"horoscope",
       {{"decadal", limit_json(horoscope.da_xian, horoscope.da_xian_stars)},
        {"age",
         {{"index", horoscope.xiao_xian.gong_index},
          {"nominal_age", horoscope.xiao_xian.age}}},
        {"yearly", limit_json(horoscope.liu_nian, horoscope.liu_nian_stars)},
        {"monthly", limit_json(horoscope.liu_yue, horoscope.liu_yue_stars)},
        {"daily", limit_json(horoscope.liu_ri, horoscope.liu_ri_stars)}}}};
}

} // namespace

int main(int argc, char **argv) {
  nlohmann::json output = {
      {"schema", "zhouyilab-iztro-parity/1.0"},
      {"engine", "ZhouYiLab"},
      {"reference_brightness_school", "Iztro"},
      {"brightness_palace_order", "寅卯辰巳午未申酉戌亥子丑"},
      {"brightness_tables", brightness_tables()},
      {"cases", nlohmann::json::array()},
  };
  for (const CaseSpec &spec : CASES)
    output["cases"].push_back(case_json(spec));

  if (argc > 1) {
    ofstream file(argv[1]);
    if (!file)
      throw runtime_error("无法创建紫微 iztro 对照输出文件");
    file << output.dump(2) << '\n';
  } else {
    cout << output.dump(2) << '\n';
  }
}
