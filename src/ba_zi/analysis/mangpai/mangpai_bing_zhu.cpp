// C++23 - 盲派宾主体用规则实现
module ZhouYi.BaZiAnalysis.MangPai.BingZhu;

import ZhouYi.BaZiAnalysis.MangPai.Common;
import ZhouYi.BaZiBase;
import ZhouYi.GanZhi;
import std;

namespace ZhouYi::BaZiAnalysis::MangPai::BingZhu {
namespace {
using namespace ZhouYi::GanZhi;
namespace Mapper = ZhouYi::GanZhi::Mapper;
} // namespace

void build(AnalysisResult &result, const BaZi &chart) {
  const auto ss = ZhouYi::BaZiBase::get_stems(chart);
  const auto zz = ZhouYi::BaZiBase::get_branches(chart);
  for (int i = 0; i < 4; ++i) {
    const auto exposed = ss[static_cast<std::size_t>(i)];
    // 日干是命主立极点，不将其自身错误罗列为“比肩”。
    if (i == 2) {
      result.blind_analysis->body.push_back(
          "日主" + std::string(Mapper::to_zh(exposed)));
    } else {
      const auto god = get_shi_shen(chart.day.gan, exposed);
      result.ten_god_occurrences.push_back(
          {exposed, god, ZhouYi::BaZiBase::pillar_position_name(i) + "透干", i,
           true, HiddenStemLevel::None, 1.0, 1.0, false, false});
      const auto text = ZhouYi::BaZiBase::pillar_position_name(i) + "透" +
                        std::string(shi_shen_to_zh(god));
      if (Common::is_body_god(god))
        result.blind_analysis->body.push_back(text);
      if (Common::is_target_god(god))
        result.blind_analysis->targets.push_back(text);
    }

    const auto hidden = get_cang_gan(zz[static_cast<std::size_t>(i)]);
    const bool kong_wang =
        std::find(result.kong_wang.affected_positions.begin(),
                  result.kong_wang.affected_positions.end(),
                  i) != result.kong_wang.affected_positions.end();
    for (std::size_t j = 0; j < hidden.size(); ++j) {
      const auto hidden_god = get_shi_shen(chart.day.gan, hidden[j]);
      const auto level = j == 0 ? "本气" : (j == 1 ? "中气" : "余气");
      const auto hidden_level = j == 0 ? HiddenStemLevel::MainQi
                                       : (j == 1 ? HiddenStemLevel::MiddleQi
                                                 : HiddenStemLevel::ResidualQi);
      const double raw_power = j == 0 ? 1.0 : (j == 1 ? 0.6 : 0.3);
      result.ten_god_occurrences.push_back(
          {hidden[j], hidden_god,
           ZhouYi::BaZiBase::pillar_position_name(i) + "藏" + level, i, false,
           hidden_level, raw_power,
           kong_wang ? raw_power * result.kong_wang.root_multiplier : raw_power,
           kong_wang, false});
      const auto hidden_text = ZhouYi::BaZiBase::pillar_position_name(i) +
                               "藏" + level +
                               std::string(shi_shen_to_zh(hidden_god));
      if (Common::is_body_god(hidden_god))
        result.blind_analysis->body.push_back(hidden_text);
      if (Common::is_target_god(hidden_god))
        result.blind_analysis->targets.push_back(hidden_text);
    }
  }
}
} // namespace ZhouYi::BaZiAnalysis::MangPai::BingZhu
