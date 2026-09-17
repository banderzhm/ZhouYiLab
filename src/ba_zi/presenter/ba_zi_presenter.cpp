// C++23 - 八字展示实现
module;

module ZhouYi.BaZiPresenter;

// 导入需要的模块
import fmt;
import ZhouYi.BaZiBase;
import ZhouYi.BaZi;
import ZhouYi.BaZiAnalysis;
import ZhouYi.BaZiAnalysis.Report;
import ZhouYi.GanZhi;
import ZhouYi.tyme;
import std;

namespace ZhouYi::BaZiPresenter {

using namespace ZhouYi::BaZi;
using namespace ZhouYi::BaZiBase;

void display_analysis(const AnalysisResult &analysis) {
  ZhouYi::BaZiAnalysis::write_zh(std::cout, analysis);
}

namespace {

/**
 * @brief 渲染完整中文排盘文本（基本信息、四柱、十神、藏干、旬空、大运）。
 *
 * 逐行内容与改造前 display_result 直接写 stdout 的输出逐字节一致；下游（控制器、
 * 适配器、报告）需要把排盘交给别人时用它，不必为了拿字符串去重定向 stdout。
 */
std::string render_result(const BaZiResult &result) {
  std::string text;
  text += fmt::format("==================== 八字排盘 ====================\n");
  text += fmt::format("\n");

  // 基本信息
  text += fmt::format("【基本信息】\n");
  text += fmt::format("性别：{}\n", result.is_male ? "男" : "女");

  // 根据是否有农历日期信息来判断显示方式
  if (result.lunar_year > 0) {
    // 农历排盘
    text += fmt::format("出生：农历{}年{}月{}日 {}时{}分\n", result.lunar_year,
                        result.lunar_month, result.lunar_day, result.birth_hour,
                        result.birth_minute);
    text += fmt::format("对应公历：{}年{}月{}日\n", result.birth_year,
                        result.birth_month, result.birth_day);
  } else {
    // 阳历排盘
    text += fmt::format("出生：公历{}年{}月{}日 {}时{}分\n", result.birth_year,
                        result.birth_month, result.birth_day, result.birth_hour,
                        result.birth_minute);
  }
  text += fmt::format("\n");

  // 四柱八字
  text += fmt::format("【四柱八字】\n");
  const auto &bazi = result.ba_zi;

  // 四柱干支
  text += fmt::format("年柱：{}  月柱：{}  日柱：{}  时柱：{}\n",
                      bazi.year.to_string(), bazi.month.to_string(),
                      bazi.day.to_string(), bazi.hour.to_string());

  // 天干十神
  auto shi_shen_arr = result.get_si_zhu_shi_shen();
  text += fmt::format("天干十神：{}（{}）  {}（{}）  {}（{}）  {}（{}）\n",
                      std::string(ZhouYi::GanZhi::Mapper::to_zh(bazi.year.gan)),
                      shi_shen_to_zh(shi_shen_arr[0]),
                      std::string(ZhouYi::GanZhi::Mapper::to_zh(bazi.month.gan)),
                      shi_shen_to_zh(shi_shen_arr[1]),
                      std::string(ZhouYi::GanZhi::Mapper::to_zh(bazi.day.gan)),
                      shi_shen_to_zh(shi_shen_arr[2]),
                      std::string(ZhouYi::GanZhi::Mapper::to_zh(bazi.hour.gan)),
                      shi_shen_to_zh(shi_shen_arr[3]));

  // 地支十神（使用地支藏干的主气）
  auto year_cang_gan = get_cang_gan(bazi.year.zhi);
  auto month_cang_gan = get_cang_gan(bazi.month.zhi);
  auto day_cang_gan = get_cang_gan(bazi.day.zhi);
  auto hour_cang_gan = get_cang_gan(bazi.hour.zhi);

  text += fmt::format("地支十神：{}（{}）  {}（{}）  {}（{}）  {}（{}）\n",
                      std::string(ZhouYi::GanZhi::Mapper::to_zh(bazi.year.zhi)),
                      shi_shen_to_zh(get_shi_shen(bazi.day.gan, year_cang_gan[0])),
                      std::string(ZhouYi::GanZhi::Mapper::to_zh(bazi.month.zhi)),
                      shi_shen_to_zh(get_shi_shen(bazi.day.gan, month_cang_gan[0])),
                      std::string(ZhouYi::GanZhi::Mapper::to_zh(bazi.day.zhi)),
                      shi_shen_to_zh(get_shi_shen(bazi.day.gan, day_cang_gan[0])),
                      std::string(ZhouYi::GanZhi::Mapper::to_zh(bazi.hour.zhi)),
                      shi_shen_to_zh(get_shi_shen(bazi.day.gan, hour_cang_gan[0])));

  // 藏干详情
  text += fmt::format("\n【藏干详情】\n");

  const auto cang_gan_line = [&bazi](std::string_view label,
                                     const std::vector<TianGan> &stems) {
    std::string line = fmt::format("{}藏干：", label);
    for (std::size_t i = 0; i < stems.size(); ++i) {
      if (i > 0)
        line += " ";
      line += fmt::format("{}（{}）",
                          std::string(ZhouYi::GanZhi::Mapper::to_zh(stems[i])),
                          shi_shen_to_zh(get_shi_shen(bazi.day.gan, stems[i])));
    }
    line += "\n";
    return line;
  };
  text += cang_gan_line("年支", year_cang_gan);
  text += cang_gan_line("月支", month_cang_gan);
  text += cang_gan_line("日支", day_cang_gan);
  text += cang_gan_line("时支", hour_cang_gan);

  // 旬空
  if (!bazi.xun_kong_1.empty()) {
    text += fmt::format("\n旬空：{}{}\n", bazi.xun_kong_1, bazi.xun_kong_2);
  }

  text += fmt::format("\n");

  // 大运信息
  text += fmt::format("【大运】\n");
  const auto &da_yun_system = result.da_yun_system;
  text += fmt::format("起运年龄：{}岁\n", da_yun_system.get_qi_yun_age());
  text += fmt::format("排运方式：{}\n", da_yun_system.is_shun_pai() ? "顺排" : "逆排");
  text += fmt::format("\n");

  // 打印前5个大运
  const auto &da_yun_list = da_yun_system.get_da_yun_list();
  int count = std::min(5, static_cast<int>(da_yun_list.size()));
  for (int i = 0; i < count; ++i) {
    const auto &dy = da_yun_list[i];
    text += fmt::format("  {}（{}—{}岁，{}—{}年）\n", dy.pillar.to_string(),
                        dy.start_age, dy.end_age, dy.start_year, dy.end_year);
  }

  text += fmt::format("\n");
  text += fmt::format("==================================================\n");
  return text;
}

} // namespace

/**
 * @brief 生成完整中文排盘文本（基本信息、四柱、十神、藏干、旬空、大运）。
 */
std::string to_zh(const BaZiResult &result) { return render_result(result); }

/**
 * @brief 将完整中文排盘文本写入输出流。
 */
void write_zh(std::ostream &output, const BaZiResult &result) {
  output << render_result(result);
}

/**
 * @brief 显示八字排盘结果（实现）；输出与 to_zh 逐字节一致。
 */
void display_result(const BaZiResult &result) {
  fmt::print("{}", render_result(result));
}

namespace {

/**
 * @brief 渲染大运表文本（干支/年龄/年份/天干十神/地支十神）。
 */
std::string render_da_yun(const BaZiResult &result, int max_count) {
  std::string text;
  text += fmt::format("==================== 大运信息 ====================\n");
  text += fmt::format("\n");

  const auto &da_yun_list = result.da_yun_system.get_da_yun_list();
  int count = std::min(max_count, static_cast<int>(da_yun_list.size()));

  text += fmt::format("{:<12} {:<14} {:<14} {:<8} {:<8}\n", "干支", "年龄",
                      "年份", "天干十神", "地支十神");
  text += fmt::format("{:-<68}\n", "");

  for (int i = 0; i < count; ++i) {
    const auto &dy = da_yun_list[i];
    text += fmt::format("{:<12} {:<14} {:<14} {:<8} {:<8}\n",
                        dy.pillar.to_string(),
                        fmt::format("{}-{:>2}岁", dy.start_age, dy.end_age),
                        fmt::format("{}-{}年", dy.start_year, dy.end_year),
                        shi_shen_to_zh(dy.gan_shi_shen),
                        shi_shen_to_zh(dy.zhi_shi_shen));
  }

  text += fmt::format("\n");
  text += fmt::format("==================================================\n");
  return text;
}

} // namespace

/**
 * @brief 生成大运表文本。
 */
std::string to_zh_da_yun(const BaZiResult &result, int max_count) {
  return render_da_yun(result, max_count);
}

/**
 * @brief 将大运表写入输出流。
 */
void write_zh_da_yun(std::ostream &output, const BaZiResult &result,
                     int max_count) {
  output << render_da_yun(result, max_count);
}

/**
 * @brief 显示大运信息（实现）；输出与 to_zh_da_yun 逐字节一致。
 */
void display_da_yun(const BaZiResult &result, int max_count) {
  fmt::print("{}", render_da_yun(result, max_count));
}

namespace {

/**
 * @brief 渲染流年表文本（年份/干支/年龄/天干十神/地支十神）。
 */
std::string render_liu_nian(const BaZiResult &result, int start_year,
                            int count) {
  std::string text;
  text += fmt::format("==================== 流年信息 ====================\n");
  text += fmt::format("\n");

  text += fmt::format("{:<8} {:<8} {:<6} {:<8} {:<8}\n", "年份", "干支", "年龄",
                      "天干十神", "地支十神");
  text += fmt::format("{:-<48}\n", "");

  for (int i = 0; i < count; ++i) {
    int year = start_year + i;
    auto liu_nian = result.get_liu_nian(year);

    text += fmt::format("{:<8} {:<8} {:<6} {:<8} {:<8}\n", year,
                        liu_nian.pillar.to_string(),
                        fmt::format("{}岁", liu_nian.age),
                        shi_shen_to_zh(liu_nian.gan_shi_shen),
                        shi_shen_to_zh(liu_nian.zhi_shi_shen));
  }

  text += fmt::format("\n");
  text += fmt::format("==================================================\n");
  return text;
}

} // namespace

/**
 * @brief 生成流年表文本。
 */
std::string to_zh_liu_nian(const BaZiResult &result, int start_year,
                           int count) {
  return render_liu_nian(result, start_year, count);
}

/**
 * @brief 将流年表写入输出流。
 */
void write_zh_liu_nian(std::ostream &output, const BaZiResult &result,
                       int start_year, int count) {
  output << render_liu_nian(result, start_year, count);
}

/**
 * @brief 显示流年信息（实现）；输出与 to_zh_liu_nian 逐字节一致。
 */
void display_liu_nian(const BaZiResult &result, int start_year, int count) {
  fmt::print("{}", render_liu_nian(result, start_year, count));
}

namespace {

/**
 * @brief 渲染流月表文本（农历月份/干支/公历起始日期/天干十神/地支十神）。
 */
std::string render_liu_yue(const BaZiResult &result, int year) {
  std::string text;
  text += fmt::format(
      "==================== {}年流月信息（节气月）====================\n",
      year);
  text += fmt::format("\n");

  text += fmt::format("{:<8} {:<8} {:<14} {:<8} {:<8}\n", "农历月份", "干支",
                      "公历起始日期", "天干十神", "地支十神");
  text += fmt::format("{:-<56}\n", "");

  auto liu_yue_list = result.get_liu_yue_list(year);

  for (const auto &liu_yue : liu_yue_list) {
    text += fmt::format("{:<8} {:<8} {:<14} {:<8} {:<8}\n",
                        fmt::format("{}月", liu_yue.lunar_month_index),
                        liu_yue.pillar.to_string(), liu_yue.start_date,
                        shi_shen_to_zh(liu_yue.gan_shi_shen),
                        shi_shen_to_zh(liu_yue.zhi_shi_shen));
  }

  text += fmt::format("\n");
  text += fmt::format("========================================================\n");
  return text;
}

} // namespace

/**
 * @brief 生成流月表文本。
 */
std::string to_zh_liu_yue(const BaZiResult &result, int year) {
  return render_liu_yue(result, year);
}

/**
 * @brief 将流月表写入输出流。
 */
void write_zh_liu_yue(std::ostream &output, const BaZiResult &result,
                      int year) {
  output << render_liu_yue(result, year);
}

/**
 * @brief 显示流月信息（实现）；输出与 to_zh_liu_yue 逐字节一致。
 */
void display_liu_yue(const BaZiResult &result, int year) {
  fmt::print("{}", render_liu_yue(result, year));
}

namespace {

/**
 * @brief 渲染流日表文本（公历日期/干支/天干十神/地支十神）。
 */
std::string render_liu_ri(const BaZiResult &result, int year, int month,
                          int day_count) {
  std::string text;
  text += fmt::format(
      "==================== {}年{}月流日信息（公历）====================\n",
      year, month);
  text += fmt::format("\n");

  text += fmt::format("{:<14} {:<8} {:<8} {:<8}\n", "公历日期", "干支",
                      "天干十神", "地支十神");
  text += fmt::format("{:-<46}\n", "");

  // 获取该月的天数
  int days_in_month = 31;
  if (month == 2) {
    // 判断闰年
    bool is_leap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    days_in_month = is_leap ? 29 : 28;
  } else if (month == 4 || month == 6 || month == 9 || month == 11) {
    days_in_month = 30;
  }

  // 限制显示天数
  int actual_count = std::min(day_count, days_in_month);

  for (int day = 1; day <= actual_count; ++day) {
    auto liu_ri = result.get_liu_ri(year, month, day);
    text += fmt::format("{:<14} {:<8} {:<8} {:<8}\n",
                        fmt::format("{}月{}日", month, day),
                        liu_ri.pillar.to_string(),
                        shi_shen_to_zh(liu_ri.gan_shi_shen),
                        shi_shen_to_zh(liu_ri.zhi_shi_shen));
  }

  text += fmt::format("\n");
  text +=
      fmt::format("============================================================\n");
  return text;
}

} // namespace

/**
 * @brief 生成流日表文本。
 */
std::string to_zh_liu_ri(const BaZiResult &result, int year, int month,
                         int day_count) {
  return render_liu_ri(result, year, month, day_count);
}

/**
 * @brief 将流日表写入输出流。
 */
void write_zh_liu_ri(std::ostream &output, const BaZiResult &result, int year,
                     int month, int day_count) {
  output << render_liu_ri(result, year, month, day_count);
}

/**
 * @brief 显示流日信息（实现）；输出与 to_zh_liu_ri 逐字节一致。
 */
void display_liu_ri(const BaZiResult &result, int year, int month,
                    int day_count) {
  fmt::print("{}", render_liu_ri(result, year, month, day_count));
}

namespace {

/**
 * @brief 渲染童限详情文本（起运年龄、精确年月日时分、出生与起运时刻）。
 */
std::string render_child_limit_detail(const BaZiResult &result) {
  std::string text;
  text += fmt::format("==================== 童限详细信息 ====================\n");
  text += fmt::format("\n");

  auto detail = result.get_child_limit_detail();

  text += fmt::format("【起运信息】\n");
  text += fmt::format("起运年龄: {}岁\n", detail.start_age);
  text += fmt::format("\n");

  text += fmt::format("【精确计算】\n");
  text += fmt::format("年: {}年\n", detail.year_count);
  text += fmt::format("月: {}月\n", detail.month_count);
  text += fmt::format("日: {}天\n", detail.day_count);
  text += fmt::format("时: {}小时\n", detail.hour_count);
  text += fmt::format("分: {}分钟\n", detail.minute_count);
  text += fmt::format("\n");

  text += fmt::format("【出生时刻】\n");
  text += fmt::format("{}\n", detail.start_time.to_string());
  text += fmt::format("\n");

  text += fmt::format("【起运时刻】\n");
  text += fmt::format("{}\n", detail.end_time.to_string());
  text += fmt::format("\n");

  text += fmt::format("==================================================\n");
  return text;
}

} // namespace

/**
 * @brief 生成童限详情文本。
 */
std::string to_zh_child_limit_detail(const BaZiResult &result) {
  return render_child_limit_detail(result);
}

/**
 * @brief 将童限详情写入输出流。
 */
void write_zh_child_limit_detail(std::ostream &output,
                                 const BaZiResult &result) {
  output << render_child_limit_detail(result);
}

/**
 * @brief 显示童限详细信息（实现）；输出与 to_zh_child_limit_detail 逐字节一致。
 */
void display_child_limit_detail(const BaZiResult &result) {
  fmt::print("{}", render_child_limit_detail(result));
}

namespace {

/**
 * @brief 渲染单步大运（tyme 口径）明细文本。
 */
std::string render_tyme_decade_fortune(const BaZiResult &result, int index) {
  std::string text;
  text += fmt::format(
      "==================== 第{}大运详细信息 ====================\n",
      index + 1);
  text += fmt::format("\n");

  auto decade_fortune = result.get_tyme_decade_fortune(index);

  text += fmt::format("【基本信息】\n");
  text += fmt::format("干支: {}\n", decade_fortune.get_name());
  text += fmt::format("起始年龄: {}岁\n", decade_fortune.get_start_age());
  text += fmt::format("结束年龄: {}岁\n", decade_fortune.get_end_age());
  text += fmt::format("\n");

  text += fmt::format("【起止年份】\n");
  auto start_year = decade_fortune.get_start_sixty_cycle_year();
  auto end_year = decade_fortune.get_end_sixty_cycle_year();
  text += fmt::format("开始: {}\n", start_year.get_name());
  text += fmt::format("结束: {}\n", end_year.get_name());
  text += fmt::format("\n");

  text += fmt::format("【小运起点】\n");
  auto start_fortune = decade_fortune.get_start_fortune();
  text += fmt::format("年龄: {}岁\n", start_fortune.get_age());
  text += fmt::format("干支: {}\n", start_fortune.get_name());
  text += fmt::format("\n");

  text +=
      fmt::format("========================================================\n");
  return text;
}

} // namespace

/**
 * @brief 生成单步大运（tyme 口径）明细文本。
 */
std::string to_zh_tyme_decade_fortune(const BaZiResult &result, int index) {
  return render_tyme_decade_fortune(result, index);
}

/**
 * @brief 将单步大运明细写入输出流。
 */
void write_zh_tyme_decade_fortune(std::ostream &output,
                                  const BaZiResult &result, int index) {
  output << render_tyme_decade_fortune(result, index);
}

/**
 * @brief 显示 tyme 库大运详细信息（实现）；输出与 to_zh_tyme_decade_fortune 逐字节一致。
 */
void display_tyme_decade_fortune(const BaZiResult &result, int index) {
  fmt::print("{}", render_tyme_decade_fortune(result, index));
}

} // namespace ZhouYi::BaZiPresenter
