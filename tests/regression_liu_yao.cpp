/**
 * @file regression_liu_yao.cpp
 * @brief 六爻神煞十干、十二支和十二月全表回归。
 */
import ZhouYi.BaZiBase;
import ZhouYi.GanZhi;
import ZhouYi.LiuYao;
import ZhouYi.LiuYaoController;
import nlohmann.json;
import std;

namespace {

using ZhouYi::BaZiBase::BaZi;
using ZhouYi::BaZiBase::Pillar;
using ZhouYi::GanZhi::DiZhi;
using ZhouYi::GanZhi::TianGan;
using ZhouYi::LiuYao::buildShenShaMap;
using ZhouYi::LiuYaoController::calculate_liu_yao;

using ShenShaMap = std::map<std::string, std::vector<std::string>>;

/**
 * @brief 构造仅用于神煞查表的四柱。
 * @param year_zhi 年支。
 * @param month_zhi 月支。
 * @param day_gan 日干。
 * @param day_zhi 日支。
 * @return 年干、月干和时柱固定的四柱。
 */
BaZi make_bazi(DiZhi year_zhi, DiZhi month_zhi, TianGan day_gan,
               DiZhi day_zhi) {
  return BaZi(Pillar(TianGan::Jia, year_zhi), Pillar(TianGan::Jia, month_zhi),
              Pillar(day_gan, day_zhi), Pillar(TianGan::Jia, DiZhi::Zi));
}

/**
 * @brief 将地支转为神煞输出使用的中文名称。
 * @param branch 地支。
 * @return 中文地支名称。
 */
std::string branch_name(DiZhi branch) {
  return std::string(ZhouYi::GanZhi::Mapper::to_zh(branch));
}

/**
 * @brief 核验单地支神煞。
 * @param values 神煞汇总。
 * @param name 神煞名称。
 * @param expected 预期地支。
 */
void expect_single(const ShenShaMap &values, std::string_view name,
                   DiZhi expected) {
  const auto iterator = values.find(std::string(name));
  if (iterator == values.end() || iterator->second.size() != 1 ||
      iterator->second.front() != branch_name(expected)) {
    throw std::runtime_error(std::string(name) + "神煞表不一致");
  }
}

/**
 * @brief 核验双地支神煞，不依赖展示排序。
 * @param values 神煞汇总。
 * @param name 神煞名称。
 * @param first 预期地支一。
 * @param second 预期地支二。
 */
void expect_pair(const ShenShaMap &values, std::string_view name, DiZhi first,
                 DiZhi second) {
  const auto iterator = values.find(std::string(name));
  if (iterator == values.end())
    throw std::runtime_error(std::string(name) + "缺失");
  auto actual = iterator->second;
  std::vector<std::string> expected{branch_name(first), branch_name(second)};
  std::ranges::sort(actual);
  std::ranges::sort(expected);
  if (actual != expected)
    throw std::runtime_error(std::string(name) + "双支表不一致");
}

/** @brief 核验日干所起的日禄、羊刃、文昌、日德和天乙贵人。 */
void verify_stem_tables() {
  constexpr std::array ri_lu{DiZhi::Yin, DiZhi::Mao, DiZhi::Si,   DiZhi::Wu,
                             DiZhi::Si,  DiZhi::Wu,  DiZhi::Shen, DiZhi::You,
                             DiZhi::Hai, DiZhi::Zi};
  constexpr std::array yang_ren{DiZhi::Mao, DiZhi::Yin, DiZhi::Wu,  DiZhi::Si,
                                DiZhi::Wu,  DiZhi::Si,  DiZhi::You, DiZhi::Shen,
                                DiZhi::Zi,  DiZhi::Hai};
  constexpr std::array wen_chang{
      DiZhi::Si,  DiZhi::Wu,  DiZhi::Shen, DiZhi::You, DiZhi::Shen,
      DiZhi::You, DiZhi::Hai, DiZhi::Zi,   DiZhi::Yin, DiZhi::Mao};
  constexpr std::array ri_de{DiZhi::Yin, DiZhi::Shen, DiZhi::Si,   DiZhi::Hai,
                             DiZhi::Si,  DiZhi::Yin,  DiZhi::Shen, DiZhi::Si,
                             DiZhi::Hai, DiZhi::Si};
  constexpr std::array<std::array<DiZhi, 2>, 10> gui_ren{{
      {DiZhi::Chou, DiZhi::Wei},
      {DiZhi::Zi, DiZhi::Shen},
      {DiZhi::Hai, DiZhi::You},
      {DiZhi::Hai, DiZhi::You},
      {DiZhi::Chou, DiZhi::Wei},
      {DiZhi::Zi, DiZhi::Shen},
      {DiZhi::Chou, DiZhi::Wei},
      {DiZhi::Wu, DiZhi::Yin},
      {DiZhi::Si, DiZhi::Mao},
      {DiZhi::Si, DiZhi::Mao},
  }};

  for (std::size_t index = 0; index < ri_lu.size(); ++index) {
    const auto stem = static_cast<TianGan>(index);
    const auto values =
        buildShenShaMap(make_bazi(DiZhi::Zi, DiZhi::Yin, stem, DiZhi::Zi));
    expect_single(values, "日禄", ri_lu[index]);
    expect_single(values, "羊刃", yang_ren[index]);
    expect_single(values, "文昌", wen_chang[index]);
    expect_single(values, "日德", ri_de[index]);
    expect_pair(values, "贵人", gui_ren[index][0], gui_ren[index][1]);
    if (ZhouYi::GanZhi::get_gui_ren(stem, true) != gui_ren[index][0] ||
        ZhouYi::GanZhi::get_gui_ren(stem, false) != gui_ren[index][1])
      throw std::runtime_error("公共天乙贵人表不一致");
  }
}

/** @brief 核验日支三合局所起的八项神煞。 */
void verify_day_branch_tables() {
  constexpr std::array yi_ma{DiZhi::Yin, DiZhi::Hai, DiZhi::Shen, DiZhi::Si,
                             DiZhi::Yin, DiZhi::Hai, DiZhi::Shen, DiZhi::Si,
                             DiZhi::Yin, DiZhi::Hai, DiZhi::Shen, DiZhi::Si};
  constexpr std::array tao_hua{DiZhi::You, DiZhi::Wu, DiZhi::Mao, DiZhi::Zi,
                               DiZhi::You, DiZhi::Wu, DiZhi::Mao, DiZhi::Zi,
                               DiZhi::You, DiZhi::Wu, DiZhi::Mao, DiZhi::Zi};
  constexpr std::array hua_gai{DiZhi::Chen, DiZhi::Chou, DiZhi::Xu, DiZhi::Wei,
                               DiZhi::Chen, DiZhi::Chou, DiZhi::Xu, DiZhi::Wei,
                               DiZhi::Chen, DiZhi::Chou, DiZhi::Xu, DiZhi::Wei};
  constexpr std::array jiang_xing{DiZhi::Zi, DiZhi::You, DiZhi::Wu, DiZhi::Mao,
                                  DiZhi::Zi, DiZhi::You, DiZhi::Wu, DiZhi::Mao,
                                  DiZhi::Zi, DiZhi::You, DiZhi::Wu, DiZhi::Mao};
  constexpr std::array jie_sha{DiZhi::Si, DiZhi::Yin, DiZhi::Hai, DiZhi::Shen,
                               DiZhi::Si, DiZhi::Yin, DiZhi::Hai, DiZhi::Shen,
                               DiZhi::Si, DiZhi::Yin, DiZhi::Hai, DiZhi::Shen};
  constexpr std::array zai_sha{DiZhi::Wu, DiZhi::Mao, DiZhi::Zi, DiZhi::You,
                               DiZhi::Wu, DiZhi::Mao, DiZhi::Zi, DiZhi::You,
                               DiZhi::Wu, DiZhi::Mao, DiZhi::Zi, DiZhi::You};
  constexpr std::array mou_xing{DiZhi::Xu,   DiZhi::Wei,  DiZhi::Chen,
                                DiZhi::Chou, DiZhi::Xu,   DiZhi::Wei,
                                DiZhi::Chen, DiZhi::Chou, DiZhi::Xu,
                                DiZhi::Wei,  DiZhi::Chen, DiZhi::Chou};

  for (std::size_t index = 0; index < yi_ma.size(); ++index) {
    const auto branch = static_cast<DiZhi>(index);
    const auto values =
        buildShenShaMap(make_bazi(DiZhi::Zi, DiZhi::Yin, TianGan::Jia, branch));
    expect_single(values, "驿马", yi_ma[index]);
    expect_single(values, "桃花", tao_hua[index]);
    expect_single(values, "华盖", hua_gai[index]);
    expect_single(values, "将星", jiang_xing[index]);
    expect_single(values, "劫煞", jie_sha[index]);
    expect_single(values, "灾煞", zai_sha[index]);
    expect_single(values, "谋星", mou_xing[index]);
  }
}

/** @brief 核验十二月天马及月日冲合。 */
void verify_month_and_relation_tables() {
  constexpr std::array tian_ma{
      DiZhi::Wu, DiZhi::Shen, DiZhi::Xu, DiZhi::Zi, DiZhi::Yin, DiZhi::Chen,
      DiZhi::Wu, DiZhi::Shen, DiZhi::Xu, DiZhi::Zi, DiZhi::Yin, DiZhi::Chen};
  constexpr std::array liu_he{DiZhi::Chou, DiZhi::Zi,   DiZhi::Hai, DiZhi::Xu,
                              DiZhi::You,  DiZhi::Shen, DiZhi::Wei, DiZhi::Wu,
                              DiZhi::Si,   DiZhi::Chen, DiZhi::Mao, DiZhi::Yin};

  for (std::size_t index = 0; index < tian_ma.size(); ++index) {
    const auto month = static_cast<DiZhi>((index + 2) % 12);
    const auto day = static_cast<DiZhi>(index);
    const auto year = static_cast<DiZhi>((index + 5) % 12);
    const auto values =
        buildShenShaMap(make_bazi(year, month, TianGan::Jia, day));
    expect_single(values, "太岁", year);
    expect_single(values, "月建", month);
    expect_single(values, "日辰", day);
    expect_single(values, "天马", tian_ma[index]);
    expect_single(values, "月破", month + 6);
    expect_single(values, "月合", liu_he[static_cast<std::size_t>(month)]);
    expect_single(values, "日破", day + 6);
    expect_single(values, "日合", liu_he[index]);
  }
}

/** @brief 核验生产排盘的英文键 JSON 与中文键 JSON 使用同一份神煞结果。 */
void verify_output_contract() {
  const auto bazi = make_bazi(DiZhi::Zi, DiZhi::Yin, TianGan::Yi, DiZhi::Chou);
  const auto result = calculate_liu_yao("111111", bazi, {}, true);
  if (!result.json_data.contains("shen_sa") ||
      !result.ai_read_json_data.contains("神煞") ||
      result.json_data.at("shen_sa") != result.ai_read_json_data.at("神煞"))
    throw std::runtime_error("六爻中英文 JSON 神煞结果不一致");

  constexpr std::array names{"太岁", "月建", "日辰", "月破", "月合",
                             "日破", "日合", "日德", "日禄", "羊刃",
                             "桃花", "驿马", "天马", "谋星", "文昌",
                             "华盖", "将星", "劫煞", "灾煞", "贵人"};
  const auto &values = result.json_data.at("shen_sa");
  for (const auto name : names) {
    if (!values.contains(name))
      throw std::runtime_error(std::string("六爻 JSON 遗漏神煞：") + name);
  }
}

} // namespace

int main() {
  try {
    verify_stem_tables();
    verify_day_branch_tables();
    verify_month_and_relation_tables();
    verify_output_contract();
    return 0;
  } catch (const std::exception &error) {
    std::println("六爻神煞回归失败：{}", error.what());
    return 1;
  }
}
