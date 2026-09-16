// 紫微斗数核心排盘模块
export module ZhouYi.ZiWei;

import std;
import ZhouYi.TrueSolarTime;
import ZhouYi.GanZhi;
import ZhouYi.BaZiBase;
import ZhouYi.ZiWei.Constants;
import ZhouYi.ZiWei.Brightness;
import ZhouYi.ZiWei.Palace;
import ZhouYi.ZiWei.Star;
import ZhouYi.ZiWei.SiHua;
import ZhouYi.ZiWei.Horoscope;
import ZhouYi.ZhMapper;
import ZhouYi.tyme;
import fmt;

export namespace ZhouYi::ZiWei {
using namespace std;
using namespace ZhouYi::GanZhi;
using namespace ZhouYi::BaZiBase;
using namespace ZhouYi::Mapper;

/**
 * @brief 宫位完整数据（包含星耀）
 */
struct PalaceInfo {
  GongWeiData gong_data;     // 宫位基本信息
  vector<StarData> zhu_xing; // 主星列表
  vector<StarData> fu_xing;  // 辅星列表
  vector<StarData> sha_xing; // 煞星列表
  vector<StarData> za_yao;   // 杂耀列表

  // 空宫借星结构；直接保存在排盘结果中，展示与分析层不得从文案反查。
  bool is_empty = false;             // 本宫是否无十四主星。
  int opposite_index = 0;            // 对宫索引。
  optional<int> borrowed_from_index; // 空宫借星来源宫位。
  vector<string> borrowed_stars;     // 从对宫借入的十四主星。

  // 神煞系统
  optional<ChangSheng12> chang_sheng; // 长生12神
  optional<BoShi12> bo_shi;           // 博士12神
  optional<SuiQian12> sui_qian;       // 岁前12神
  optional<JiangQian12> jiang_qian;   // 将前12神

  // 大限信息
  int da_xian_start = 0; // 大限起始年龄
  int da_xian_end = 0;   // 大限结束年龄

  // 小限年龄列表（该宫位对应的虚岁）
  vector<int> xiao_xian_ages;

  // 流年年龄列表（该宫位对应的虚岁）
  vector<int> liu_nian_ages;

  string to_string() const {
    string result = fmt::format("【{}】", gong_data.to_string());

    if (!zhu_xing.empty()) {
      result += "\n  主星：";
      for (size_t index = 0; index < zhu_xing.size(); ++index)
        result += (index == 0 ? "" : " ") + zhu_xing[index].to_string();
    }

    if (!fu_xing.empty()) {
      result += "\n  辅星：";
      for (size_t index = 0; index < fu_xing.size(); ++index)
        result += (index == 0 ? "" : " ") + fu_xing[index].to_string();
    }

    if (!sha_xing.empty()) {
      result += "\n  煞星：";
      for (size_t index = 0; index < sha_xing.size(); ++index)
        result += (index == 0 ? "" : " ") + sha_xing[index].to_string();
    }

    if (!za_yao.empty()) {
      result += "\n  杂耀：";
      for (size_t index = 0; index < za_yao.size(); ++index)
        result += (index == 0 ? "" : " ") + za_yao[index].to_string();
    }

    // 神煞
    bool has_shen_sha = sui_qian.has_value() || jiang_qian.has_value() ||
                        chang_sheng.has_value() || bo_shi.has_value();
    if (has_shen_sha) {
      result += "\n  神煞";
      if (sui_qian.has_value()) {
        result +=
            fmt::format("\n    ├岁前星 : {}", string(to_zh(sui_qian.value())));
      }
      if (jiang_qian.has_value()) {
        result += fmt::format("\n    ├将前星 : {}",
                              string(to_zh(jiang_qian.value())));
      }
      if (chang_sheng.has_value()) {
        result += fmt::format("\n    ├十二长生 : {}",
                              string(to_zh(chang_sheng.value())));
      }
      if (bo_shi.has_value()) {
        result += fmt::format("\n    └博士十二神 : {}",
                              string(to_zh(bo_shi.value())));
      }
    }

    // 大限
    if (da_xian_start > 0 || da_xian_end > 0) {
      result += fmt::format("\n  大限：{}~{}虚岁", da_xian_start, da_xian_end);
    }

    // 小限
    if (!xiao_xian_ages.empty()) {
      result += "\n  小限：";
      for (size_t i = 0; i < xiao_xian_ages.size() && i < 5; ++i) {
        if (i > 0)
          result += ",";
        result += std::to_string(xiao_xian_ages[i]);
      }
      result += "虚岁";
    }

    // 流年
    if (!liu_nian_ages.empty()) {
      result += "\n  流年：";
      for (size_t i = 0; i < liu_nian_ages.size() && i < 5; ++i) {
        if (i > 0)
          result += ",";
        result += std::to_string(liu_nian_ages[i]);
      }
      result += "虚岁";
    }

    return result;
  }
};

/** 紫微排盘可切换的流派配置。 */
struct ZiWeiConfig {
  /** 星曜亮度所采用的资料口径。 */
  BrightnessSchool brightness_school{BrightnessSchool::MetisDefault};

  /** 辛年天魁、天钺起例所采用的安星口径。 */
  KuiYueSchool kui_yue_school{KuiYueSchool::QuanShuOne};
};

/**
 * @brief 紫微斗数排盘结果（本命盘/地盘）
 *
 * 天地人三盘说明：
 * - 地盘（Origin/本命盘）：出生时的命盘，固定不变
 * - 天盘（Decadal/大限盘）：10年一大限，大限宫位起算
 * - 人盘（Yearly/流年盘）：流年、流月、流日、流时
 */
struct ZiWeiResult {
  // 基本信息
  tyme::SolarDay solar_day;   // 阳历日期
  tyme::LunarDay lunar_day;   // 农历日期
  tyme::LunarHour lunar_hour; // 农历时辰
  bool is_male;               // 性别

  // 四柱
  Pillar year_pillar;  // 年柱
  Pillar month_pillar; // 月柱
  Pillar day_pillar;   // 日柱
  Pillar hour_pillar;  // 时柱

  // 排盘所用的农历月序（1-12）
  // 闰月前十五日仍按本月、十六日起按下月，与本命十二宫排列口径一致；运限模块
  // 据此定位流月宫位，调用方不必再判断闰月。0 表示未填写的默认值。
  int pai_pan_lunar_month = 0;

  // 命盘核心数据
  int ming_gong_index;  // 命宫索引（以寅宫为0）
  int shen_gong_index;  // 身宫索引
  WuXingJu wu_xing_ju;  // 五行局
  int zi_wei_index;     // 紫微星索引
  int tian_fu_index;    // 天府星索引
  string ming_zhu_xing; // 命主星
  string shen_zhu_xing; // 身主星

  // 十二宫详细信息
  array<PalaceInfo, 12> palaces;      // 从寅宫开始的十二宫（地盘）
  array<DaXianData, 12> da_xian_data; // 大限数据（天盘）

  /**
   * @brief 根据宫位类型获取宫位信息
   */
  const PalaceInfo &get_palace(GongWei gw) const {
    for (const auto &palace : palaces) {
      if (palace.gong_data.gong_wei == gw) {
        return palace;
      }
    }
    throw runtime_error("未找到指定宫位");
  }

  /**
   * @brief 根据索引获取宫位信息
   */
  const PalaceInfo &get_palace_by_index(int index) const {
    return palaces[fix_index(index)];
  }

  /**
   * @brief 获取指定虚岁的运限盘（天地人三盘）
   *
   * 大限取本命盘已排定大限中当前虚岁所在限（干支即该限宫的宫干支）；小限按
   * get_xiao_xian 的三合局起宫口径（见 get_xiao_xian_start_gong，男顺女逆，
   * 与 palaces[i].xiao_xian_ages 同源）；流年以目标流年地支定宫。目标流年
   * 干支取自公共 tyme 干支年模块，不在本模块另写年干支换算；流月、流日、流时
   * 天干分别按五虎遁、日干支、五鼠遁定出。十二宫角标与目标流年口径的岁前／将前
   * 见 HoroscopeResult::palace_tags。
   *
   * @param target_year 目标年份：同时作为流年的干支年与目标农历月日所属的农历年
   * @param target_month 目标农历月份，范围 1-12；闰月按本月月序传入
   * @param target_day 目标农历日，范围 1-30
   * @param target_hour 目标时辰；子时按早子时口径，不顺延到次日
   * @param current_age 当前虚岁，最小为 1
   * @return
   * 运限结果（大限、小限、流年、流月、流日、流时、各组流曜及十二宫角标）
   * @throws std::invalid_argument 虚岁小于 1、农历月不在 1-12
   * 或农历日非法时抛出
   */
  HoroscopeResult get_horoscope(int target_year, int target_month,
                                int target_day, DiZhi target_hour,
                                int current_age) const;

  /**
   * @brief 格式化输出命盘
   */
  string to_string() const {
    string result = "\n";
    result += "           紫微斗数命盘\n";
    result += "\n\n";

    result += fmt::format("阳历：{}\n", solar_day.to_string());
    result += fmt::format("农历：{} {}\n", lunar_day.to_string(),
                          lunar_hour.to_string());
    result += fmt::format("性别：{}\n", is_male ? "男" : "女");
    result += fmt::format("四柱：{} {} {} {}\n", year_pillar.to_string(),
                          month_pillar.to_string(), day_pillar.to_string(),
                          hour_pillar.to_string());
    result += fmt::format("五行局：{}\n", string(to_zh(wu_xing_ju)));
    result += fmt::format("命宫：{}{}，命主：{}\n",
                          string(GanZhi::Mapper::to_zh(
                              palaces[ming_gong_index].gong_data.tian_gan)),
                          string(GanZhi::Mapper::to_zh(
                              palaces[ming_gong_index].gong_data.di_zhi)),
                          ming_zhu_xing);
    result += fmt::format("身宫：{}{}，身主：{}\n\n",
                          string(GanZhi::Mapper::to_zh(
                              palaces[shen_gong_index].gong_data.tian_gan)),
                          string(GanZhi::Mapper::to_zh(
                              palaces[shen_gong_index].gong_data.di_zhi)),
                          shen_zhu_xing);

    result += "\n";
    result += "           十二宫详情\n";
    result += "\n\n";

    for (int i = 0; i < 12; ++i) {
      result += palaces[i].to_string() + "\n\n";
    }

    return result;
  }
};

/**
 * @brief 紫微斗数排盘（阳历）
 *
 * @param year 阳历年
 * @param month 阳历月
 * @param day 阳历日
 * @param hour 时辰（0-23）
 * @param is_male 性别（true为男性）
 * @return 排盘结果
 */
inline ZiWeiResult pai_pan_solar(
    int year, int month, int day, int hour, bool is_male,
    const std::optional<ZhouYi::Time::Location> &location = std::nullopt,
    ZiWeiConfig config = {}) {
  if (location) {
    const auto corrected =
        ZhouYi::Time::correct(year, month, day, hour, 0, 0, location);
    year = corrected.solar_time.get_year();
    month = corrected.solar_time.get_month();
    day = corrected.solar_time.get_day();
    hour = corrected.solar_time.get_hour();
  }
  // 创建阳历日期
  tyme::SolarDay solar_day = tyme::SolarDay::from_ymd(year, month, day);

  // 转换为农历并获取四柱
  auto solar_time = tyme::SolarTime::from_ymd_hms(year, month, day, hour, 0, 0);
  tyme::LunarDay lunar_day = solar_day.get_lunar_day();
  tyme::LunarHour lunar_hour = solar_time.get_lunar_hour();
  tyme::EightChar bazi = lunar_hour.get_eight_char();

  // 转换为我们的 Pillar 类型
  auto convert_cycle = [](const tyme::SixtyCycle &cycle) -> Pillar {
    return Pillar(cycle.get_heaven_stem().get_name(),
                  cycle.get_earth_branch().get_name());
  };

  Pillar year_pillar = convert_cycle(bazi.get_year());
  Pillar month_pillar = convert_cycle(bazi.get_month());
  Pillar day_pillar = convert_cycle(bazi.get_day());
  Pillar hour_pillar = convert_cycle(bazi.get_hour());

  // 获取农历月份和日期
  // 闰月前十五日仍按本月，十六日起按下月安命身诸宫；负号只表达
  // 闰月身份，不能直接参与宫位运算。该口径与当前参考排盘一致。
  const int raw_lunar_month = lunar_day.get_month();
  int lunar_month = std::abs(raw_lunar_month);
  if (raw_lunar_month < 0 && lunar_day.get_day() > 15)
    lunar_month = lunar_month % 12 + 1;
  int lunar_day_num = lunar_day.get_day();

  // 获取时辰地支
  DiZhi hour_zhi = hour_pillar.zhi;

  // 排布十二宫
  vector<GongWeiData> palaces =
      arrange_twelve_palaces(year_pillar.gan, lunar_month, hour_zhi);

  // 找到命宫索引
  int ming_index = -1;
  int shen_index = -1;
  for (size_t i = 0; i < palaces.size(); ++i) {
    if (palaces[i].is_ming_palace) {
      ming_index = palaces[i].index;
    }
    if (palaces[i].is_body_palace) {
      shen_index = palaces[i].index;
    }
  }

  // 获取五行局
  WuXingJu wu_xing_ju = palaces[ming_index].wu_xing_ju;

  // 定紫微星和天府星位置
  int zi_wei_idx = get_zi_wei_index(lunar_day_num, wu_xing_ju);
  int tian_fu_idx = get_tian_fu_index(zi_wei_idx);

  // 安主星
  auto zi_wei_group = arrange_zi_wei_group(zi_wei_idx);
  auto tian_fu_group = arrange_tian_fu_group(tian_fu_idx);

  // 获取四化表
  auto si_hua_table = get_si_hua_table(year_pillar.gan);

  // 安辅星
  auto [zuo_idx, you_idx] = get_zuo_you_index(lunar_month);
  auto [chang_idx, qu_idx] = get_chang_qu_index(hour_zhi);
  auto [kui_idx, yue_idx] =
      get_kui_yue_index(year_pillar.gan, config.kui_yue_school);

  // 安煢星
  int lu_cun_idx = get_lu_cun_index(year_pillar.gan);
  auto [yang_idx, tuo_idx] = get_yang_tuo_index(lu_cun_idx);
  auto [huo_idx, ling_idx] = get_huo_ling_index(year_pillar.zhi, hour_zhi);
  auto [kong_idx, jie_idx] = get_kong_jie_index(hour_zhi);

  // 计算命主星和身主星
  // 命主星：通用派以命宫地支找命主，中州派以年支找命主
  DiZhi ming_gong_zhi = palaces[ming_index].di_zhi;
  string ming_zhu = get_ming_zhu_xing(ming_gong_zhi);
  // 身主星：以年支找身主
  string shen_zhu = get_shen_zhu_xing(year_pillar.zhi);

  // ============= 安杂耀 =============
  // 桃花星
  auto [hong_luan_idx, tian_xi_idx] =
      get_hong_luan_tian_xi_index(year_pillar.zhi);
  int tian_yao_idx = get_tian_yao_index(lunar_month);
  int xian_chi_idx = get_xian_chi_index(year_pillar.zhi);

  // 贵人星
  int jie_shen_idx = get_jie_shen_index(lunar_month);
  int tian_wu_idx = get_tian_wu_index(lunar_month);
  auto [tian_guan_idx, tian_fu2_idx] =
      get_tian_guan_tian_fu_index(year_pillar.gan);
  int tian_chu_idx = get_tian_chu_index(year_pillar.gan);
  int tian_ma_idx = get_tian_ma_index(year_pillar.zhi);

  // 吉星
  auto [san_tai_idx, ba_zuo_idx] =
      get_san_tai_ba_zuo_index(lunar_month, lunar_day_num, hour_zhi);
  auto [en_guang_idx, tian_gui_idx] =
      get_en_guang_tian_gui_index(lunar_month, lunar_day_num, hour_zhi);
  auto [long_chi_idx, feng_ge_idx] =
      get_long_chi_feng_ge_index(year_pillar.zhi);
  auto [tian_cai_idx, tian_shou_idx] =
      get_tian_cai_tian_shou_index(year_pillar.zhi, ming_index, shen_index);
  auto [tai_fu_idx, feng_gao_idx] = get_tai_fu_feng_gao_index(hour_zhi);
  int hua_gai_idx = get_hua_gai_index(year_pillar.zhi);
  int tian_yue2_idx = get_tian_yue_index(lunar_month);
  auto [tian_de_idx, yue_de_idx] = get_tian_de_yue_de_index(year_pillar.zhi);

  // 凶星
  auto [gu_chen_idx, gua_su_idx] = get_gu_chen_gua_su_index(year_pillar.zhi);
  int fei_lian_idx = get_fei_lian_index(year_pillar.zhi);
  int po_sui_idx = get_po_sui_index(year_pillar.zhi);
  int tian_xing_idx = get_tian_xing_index(lunar_month);
  int yin_sha_idx = get_yin_sha_index(lunar_month);
  int tian_kong2_idx = get_tian_kong_index(year_pillar.zhi);
  auto [tian_ku_idx, tian_xu_idx] = get_tian_ku_tian_xu_index(year_pillar.zhi);
  auto [tian_shi_idx, tian_shang_idx] =
      get_tian_shi_tian_shang_index(ming_index, is_male, year_pillar.zhi);
  int nian_jie_idx = get_nian_jie_index(year_pillar.zhi);
  auto [xun_kong1_idx, xun_kong2_idx] =
      get_xun_kong_index(year_pillar.gan, year_pillar.zhi);
  auto [jie_lu_idx, kong_wang_idx] =
      get_jie_lu_kong_wang_index(year_pillar.gan);
  const int da_hao_idx = get_da_hao_index(year_pillar.zhi);

  // ============= 安神煞 =============
  // 长生12神
  auto chang_sheng_arr =
      arrange_chang_sheng_12(wu_xing_ju, is_male, year_pillar.zhi);
  // 博士12神
  auto bo_shi_arr =
      arrange_bo_shi_12(year_pillar.gan, year_pillar.zhi, is_male);
  // 岁前12神
  auto sui_qian_arr = arrange_sui_qian_12(year_pillar.zhi);
  const auto long_de_position = ranges::find(sui_qian_arr, SuiQian12::LongDe);
  const int long_de2_idx =
      static_cast<int>(distance(sui_qian_arr.begin(), long_de_position));
  // 将前12神
  auto jiang_qian_arr = arrange_jiang_qian_12(year_pillar.zhi);

  // ============= 安大限 =============
  // 大限宫的干支取命盘同宫的真实宫干支，顺逆仍只看生年支与性别。
  auto da_xian_arr = arrange_da_xian(ming_index, wu_xing_ju, is_male,
                                     year_pillar.zhi, palaces);

  // 创建结果对象（使用聚合初始化）
  ZiWeiResult result{.solar_day = solar_day,
                     .lunar_day = lunar_day,
                     .lunar_hour = lunar_hour,
                     .is_male = is_male,
                     .year_pillar = year_pillar,
                     .month_pillar = month_pillar,
                     .day_pillar = day_pillar,
                     .hour_pillar = hour_pillar,
                     .pai_pan_lunar_month = lunar_month,
                     .ming_gong_index = ming_index,
                     .shen_gong_index = shen_index,
                     .wu_xing_ju = wu_xing_ju,
                     .zi_wei_index = zi_wei_idx,
                     .tian_fu_index = tian_fu_idx,
                     .ming_zhu_xing = ming_zhu,
                     .shen_zhu_xing = shen_zhu,
                     .palaces = {},
                     .da_xian_data = da_xian_arr};

  // 填充每个宫位的星耀
  for (int i = 0; i < 12; ++i) {
    PalaceInfo palace_info;
    palace_info.gong_data = palaces[i];

    // 添加紫微星系主星
    for (const auto &[star, idx] : zi_wei_group) {
      if (idx == i) {
        auto liang_du_table =
            get_zhu_xing_liang_du_table(star, config.brightness_school);
        StarData star_data;
        star_data.name = string(to_zh(star));
        star_data.liang_du = liang_du_table[i];
        star_data.gong_index = i;

        // 检查四化
        if (si_hua_table.contains(star)) {
          star_data.si_hua = si_hua_table[star];
        }

        palace_info.zhu_xing.push_back(star_data);
      }
    }

    // 添加天府星系主星
    for (const auto &[star, idx] : tian_fu_group) {
      if (idx == i) {
        auto liang_du_table =
            get_zhu_xing_liang_du_table(star, config.brightness_school);
        StarData star_data;
        star_data.name = string(to_zh(star));
        star_data.liang_du = liang_du_table[i];
        star_data.gong_index = i;

        // 检查四化
        if (si_hua_table.contains(star)) {
          star_data.si_hua = si_hua_table[star];
        }

        palace_info.zhu_xing.push_back(star_data);
      }
    }

    // 添加辅星
    if (i == zuo_idx) {
      palace_info.fu_xing.push_back(
          StarData{.name = string(to_zh(FuXing::ZuoFu)),
                   .liang_du = get_fu_xing_liang_du_table(
                       FuXing::ZuoFu, config.brightness_school)[i],
                   .gong_index = i});
    }
    if (i == you_idx) {
      palace_info.fu_xing.push_back(
          StarData{.name = string(to_zh(FuXing::YouBi)),
                   .liang_du = get_fu_xing_liang_du_table(
                       FuXing::YouBi, config.brightness_school)[i],
                   .gong_index = i});
    }
    if (i == chang_idx) {
      palace_info.fu_xing.push_back(
          StarData{.name = string(to_zh(FuXing::WenChang)),
                   .liang_du = get_fu_xing_liang_du_table(
                       FuXing::WenChang, config.brightness_school)[i],
                   .gong_index = i});
    }
    if (i == qu_idx) {
      palace_info.fu_xing.push_back(
          StarData{.name = string(to_zh(FuXing::WenQu)),
                   .liang_du = get_fu_xing_liang_du_table(
                       FuXing::WenQu, config.brightness_school)[i],
                   .gong_index = i});
    }
    if (i == kui_idx) {
      palace_info.fu_xing.push_back(
          StarData{.name = string(to_zh(FuXing::TianKui)),
                   .liang_du = get_fu_xing_liang_du_table(
                       FuXing::TianKui, config.brightness_school)[i],
                   .gong_index = i});
    }
    if (i == yue_idx) {
      palace_info.fu_xing.push_back(
          StarData{.name = string(to_zh(FuXing::TianYue)),
                   .liang_du = get_fu_xing_liang_du_table(
                       FuXing::TianYue, config.brightness_school)[i],
                   .gong_index = i});
    }
    // 禄存是吉星，放在辅星中
    if (i == lu_cun_idx) {
      palace_info.fu_xing.push_back(
          StarData{.name = string(to_zh(FuXing::LuCun)),
                   .liang_du = get_fu_xing_liang_du_table(
                       FuXing::LuCun, config.brightness_school)[i],
                   .gong_index = i});
    }

    // 添加煞星
    if (i == yang_idx) {
      palace_info.sha_xing.push_back(
          StarData{.name = string(to_zh(ShaXing::QingYang)),
                   .liang_du = get_sha_xing_liang_du_table(
                       ShaXing::QingYang, config.brightness_school)[i],
                   .gong_index = i});
    }
    if (i == tuo_idx) {
      palace_info.sha_xing.push_back(
          StarData{.name = string(to_zh(ShaXing::TuoLuo)),
                   .liang_du = get_sha_xing_liang_du_table(
                       ShaXing::TuoLuo, config.brightness_school)[i],
                   .gong_index = i});
    }
    if (i == huo_idx) {
      palace_info.sha_xing.push_back(
          StarData{.name = string(to_zh(ShaXing::HuoXing)),
                   .liang_du = get_sha_xing_liang_du_table(
                       ShaXing::HuoXing, config.brightness_school)[i],
                   .gong_index = i});
    }
    if (i == ling_idx) {
      palace_info.sha_xing.push_back(
          StarData{.name = string(to_zh(ShaXing::LingXing)),
                   .liang_du = get_sha_xing_liang_du_table(
                       ShaXing::LingXing, config.brightness_school)[i],
                   .gong_index = i});
    }
    if (i == kong_idx) {
      palace_info.sha_xing.push_back(
          StarData{.name = string(to_zh(ShaXing::DiKong)),
                   .liang_du = get_sha_xing_liang_du_table(
                       ShaXing::DiKong, config.brightness_school)[i],
                   .gong_index = i});
    }
    if (i == jie_idx) {
      palace_info.sha_xing.push_back(
          StarData{.name = string(to_zh(ShaXing::DiJie)),
                   .liang_du = get_sha_xing_liang_du_table(
                       ShaXing::DiJie, config.brightness_school)[i],
                   .gong_index = i});
    }

    // 杂曜仅声明“星曜—落宫”，亮度由所选流派的独立策略表统一给出。
    // 未收录亮度的杂曜保持空值，展示层不得用“平”冒充资料结论。
    const array<pair<ZaYao, int>, 42> za_yao_placements = {{
        {ZaYao::HongLuan, hong_luan_idx},   {ZaYao::TianXi, tian_xi_idx},
        {ZaYao::TianYao, tian_yao_idx},     {ZaYao::XianChi, xian_chi_idx},
        {ZaYao::JieShen, jie_shen_idx},     {ZaYao::TianWu, tian_wu_idx},
        {ZaYao::TianGuan, tian_guan_idx},   {ZaYao::TianFu2, tian_fu2_idx},
        {ZaYao::TianChu, tian_chu_idx},     {ZaYao::TianMa, tian_ma_idx},
        {ZaYao::SanTai, san_tai_idx},       {ZaYao::BaZuo, ba_zuo_idx},
        {ZaYao::EnGuang, en_guang_idx},     {ZaYao::TianGui, tian_gui_idx},
        {ZaYao::LongChi, long_chi_idx},     {ZaYao::FengGe, feng_ge_idx},
        {ZaYao::TianCai, tian_cai_idx},     {ZaYao::TianShou, tian_shou_idx},
        {ZaYao::TaiFu, tai_fu_idx},         {ZaYao::FengGao, feng_gao_idx},
        {ZaYao::HuaGai, hua_gai_idx},       {ZaYao::TianYue2, tian_yue2_idx},
        {ZaYao::TianDe, tian_de_idx},       {ZaYao::YueDe, yue_de_idx},
        {ZaYao::GuChen, gu_chen_idx},       {ZaYao::GuaSu, gua_su_idx},
        {ZaYao::FeiLian, fei_lian_idx},     {ZaYao::PoSui, po_sui_idx},
        {ZaYao::TianXing, tian_xing_idx},   {ZaYao::YinSha, yin_sha_idx},
        {ZaYao::TianKong2, tian_kong2_idx}, {ZaYao::TianKu, tian_ku_idx},
        {ZaYao::TianXu, tian_xu_idx},       {ZaYao::TianShi, tian_shi_idx},
        {ZaYao::TianShang, tian_shang_idx}, {ZaYao::NianJie, nian_jie_idx},
        {ZaYao::XunKong, xun_kong1_idx},    {ZaYao::FuXunKong, xun_kong2_idx},
        {ZaYao::JieKong, jie_lu_idx},       {ZaYao::FuJieKong, kong_wang_idx},
        {ZaYao::DaHao, da_hao_idx},         {ZaYao::LongDe2, long_de2_idx},
    }};
    for (const auto &[star, palace_index] : za_yao_placements) {
      if (i == palace_index) {
        palace_info.za_yao.push_back(StarData{
            .name = string(to_zh(star)),
            .liang_du =
                get_za_yao_liang_du_table(star, config.brightness_school)[i],
            .gong_index = i});
      }
    }

    // 生年四化必须覆盖主星与辅曜，不能因星曜被分组存储而漏标。
    const auto apply_birth_si_hua = [&](vector<StarData> &stars) {
      for (auto &star : stars)
        star.si_hua = get_star_si_hua_type(year_pillar.gan, star.name);
    };
    apply_birth_si_hua(palace_info.zhu_xing);
    apply_birth_si_hua(palace_info.fu_xing);
    apply_birth_si_hua(palace_info.sha_xing);
    apply_birth_si_hua(palace_info.za_yao);

    // 填充神煞数据
    palace_info.chang_sheng = chang_sheng_arr[i];
    palace_info.bo_shi = bo_shi_arr[i];
    palace_info.sui_qian = sui_qian_arr[i];
    palace_info.jiang_qian = jiang_qian_arr[i];

    // 填充大限数据
    palace_info.da_xian_start = da_xian_arr[i].start_age;
    palace_info.da_xian_end = da_xian_arr[i].end_age;

    // 填充小限年龄（每12年一个周期，显示前5个）
    // 起宫规则由 get_xiao_xian_start_gong 统一给出（三合局起宫），运限模块的
    // get_xiao_xian 调用同一份实现，两处不再各写一份表。
    {
      const int start_gong = get_xiao_xian_start_gong(year_pillar.zhi);

      // 男命顺行、女命逆行；先算该宫对应的第一个小限虚岁，再每 12 年记一次。
      const int diff =
          is_male ? fix_index(i - start_gong) : fix_index(start_gong - i);
      const int first_age = diff + 1;

      for (int k = 0; k < 5; ++k) {
        palace_info.xiao_xian_ages.push_back(first_age + k * 12);
      }
    }
    // 填充流年年龄（基于地支，每12年一个周期）
    {
      // 流年以地支定宫，如子年在子宫
      // 找出该宫位对应的第一个流年虚岁
      // 宫位i=0是寅宫，对应寅年
      // 宫位索引转地支: 0=寅 1=卯 2=辰 3=巳 4=午 5=未 6=申 7=酉 8=戌 9=亥 10=子
      // 11=丑
      constexpr int gong_idx_to_di_zhi[] = {2, 3, 4,  5,  6, 7,
                                            8, 9, 10, 11, 0, 1};
      int gong_zhi_idx = gong_idx_to_di_zhi[i];

      // 计算从出生年到该地支年的差距
      int birth_zhi_idx = static_cast<int>(year_pillar.zhi);
      int diff = (gong_zhi_idx - birth_zhi_idx + 12) % 12;
      int first_age = diff + 1; // 虚岁1岁是出生年

      // 每12年一个周期，记录5个
      for (int k = 0; k < 5; ++k) {
        palace_info.liu_nian_ages.push_back(first_age + k * 12);
      }
    }

    result.palaces[i] = palace_info;
  }

  // 空宫借对宫主星属于命盘结构，不应只在控制器展示时临时计算。
  for (int index = 0; index < 12; ++index) {
    auto &palace = result.palaces[index];
    palace.opposite_index = fix_index(index + 6);
    palace.is_empty = palace.zhu_xing.empty();
    if (!palace.is_empty)
      continue;
    palace.borrowed_from_index = palace.opposite_index;
    for (const auto &star : result.palaces[palace.opposite_index].zhu_xing)
      palace.borrowed_stars.push_back(star.name);
  }

  return result;
}

/**
 * @brief 紫微斗数排盘（农历）
 *
 * @param year 农历年
 * @param month 农历月
 * @param day 农历日
 * @param hour 时辰（0-23）
 * @param is_male 性别（true为男性）
 * @param is_leap_month
 * 是否闰月

 * *
 * @param config 排盘流派配置
 * @return 排盘结果
 */
inline ZiWeiResult pai_pan_lunar(int year, int month, int day, int hour,
                                 bool is_male, bool is_leap_month = false,
                                 ZiWeiConfig config = {}) {
  // 创建农历日期
  tyme::LunarDay lunar_day = tyme::LunarDay::from_ymd(year, month, day);

  // 转换为阳历
  tyme::SolarDay solar_day = lunar_day.get_solar_day();

  // 调用阳历排盘
  return pai_pan_solar(solar_day.get_year(), solar_day.get_month(),
                       solar_day.get_day(), hour, is_male, std::nullopt,
                       config);
}

} // namespace ZhouYi::ZiWei
