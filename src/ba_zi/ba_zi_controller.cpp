// C++23 - 八字应用控制器实现
module ZhouYi.BaZiController;

import ZhouYi.BaZi;
import ZhouYi.BaZiAnalysis;
import ZhouYi.BaZiAnalysis.MangPai;
import ZhouYi.BaZiBase;
import ZhouYi.TrueSolarTime;
import ZhouYi.tyme;
import std;

namespace ZhouYi::BaZiController {

using namespace ZhouYi::BaZi;
using namespace ZhouYi::BaZiBase;
using namespace ZhouYi::BaZiAnalysis;

namespace {

BirthContext birth_context_from_result(const BaZiResult &result) {
  BirthContext context;
  context.has_exact_time = true;
  context.year = result.birth_year;
  context.month = result.birth_month;
  context.day = result.birth_day;
  context.hour = result.birth_hour;
  context.minute = result.birth_minute;
  context.second = result.birth_second;
  context.is_male = result.is_male;
  return context;
}

std::vector<Pillar> fortunes_from_result(const BaZiResult &result) {
  std::vector<Pillar> fortunes;
  for (const auto &da_yun : result.da_yun_system.get_da_yun_list())
    fortunes.push_back(da_yun.pillar);
  return fortunes;
}

} // namespace

BaZiResult pai_pan_solar(int year, int month, int day, int hour, int minute,
                         bool is_male) {
  auto bazi =
      ZhouYi::BaZiBase::BaZi::from_solar(year, month, day, hour, minute);
  return BaZiResult(bazi, is_male, year, month, day, hour, minute, 0);
}

BaZiResult
pai_pan_solar(int year, int month, int day, int hour, int minute, bool is_male,
              const std::optional<ZhouYi::Time::Location> &location) {
  const auto corrected =
      ZhouYi::Time::correct(year, month, day, hour, minute, 0, location);
  return pai_pan_solar(
      corrected.solar_time.get_year(), corrected.solar_time.get_month(),
      corrected.solar_time.get_day(), corrected.solar_time.get_hour(),
      corrected.solar_time.get_minute(), is_male);
}

BaZiResult pai_pan_lunar(int year, int month, int day, int hour, int minute,
                         bool is_male) {
  auto bazi =
      ZhouYi::BaZiBase::BaZi::from_lunar(year, month, day, hour, minute);
  auto lunar_hour =
      tyme::LunarHour::from_ymd_hms(year, month, day, hour, minute, 0);
  auto solar_time = lunar_hour.get_solar_time();
  return BaZiResult(bazi, is_male, solar_time.get_year(),
                    solar_time.get_month(), solar_time.get_day(), hour, minute,
                    0, year, month, day);
}

AnalysisResult analyze_ba_zi(const BaZiResult &result,
                             const AnalysisConfig &config) {
  AnalysisRequest request;
  request.config = config;
  return analyze_ba_zi(result, request);
}

AnalysisResult analyze_ba_zi(const BaZiResult &result,
                             const AnalysisRequest &request) {
  const auto context =
      request.birth_context.value_or(birth_context_from_result(result));
  if (request.method == AnalysisMethod::BlindSchool)
    return MangPai::analyze(result.ba_zi, context, fortunes_from_result(result),
                            request.config);
  auto analysis = analyze(result.ba_zi, context, fortunes_from_result(result),
                          request.config);
  analysis.method = request.method;
  return analysis;
}

FortuneImpact analyze_liu_nian(const BaZiResult &result, int year,
                               const AnalysisConfig &config) {
  const auto analysis = analyze_ba_zi(result, config);
  return analyze_fortune(result.get_liu_nian(year).pillar, analysis);
}

TransitAnalysis analyze_sui_yun(const BaZiResult &result, const Pillar &da_yun,
                                int year, const std::optional<Pillar> &liu_yue,
                                const AnalysisConfig &config) {
  const auto analysis = analyze_ba_zi(result, config);
  TransitContext context;
  context.da_yun = da_yun;
  context.liu_nian = result.get_liu_nian(year).pillar;
  context.liu_yue = liu_yue;
  context.current_year = year;
  const auto &da_yun_list = result.da_yun_system.get_da_yun_list();
  const auto matched = std::find_if(
      da_yun_list.begin(), da_yun_list.end(), [&](const auto &item) {
        return item.pillar.gan == da_yun.gan && item.pillar.zhi == da_yun.zhi &&
               year >= item.start_year && year <= item.end_year;
      });
  if (matched != da_yun_list.end())
    context.da_yun_start_year = matched->start_year;
  return analyze_transit(context, analysis);
}

std::vector<BaZiResult> batch_pai_pan(
    const std::vector<std::tuple<int, int, int, int, bool>> &requests) {
  std::vector<BaZiResult> results;
  results.reserve(requests.size());
  for (const auto &[year, month, day, hour, is_male] : requests)
    results.push_back(pai_pan_solar(year, month, day, hour, 0, is_male));
  return results;
}

} // namespace ZhouYi::BaZiController
