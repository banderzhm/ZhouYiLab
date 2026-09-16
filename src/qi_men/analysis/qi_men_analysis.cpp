module ZhouYi.QiMen.Analysis;

import ZhouYi.QiMen.Analysis.Judgment;
import ZhouYi.ZhMapper;
import fmt;

namespace ZhouYi::QiMen {
namespace {
using ZhouYi::GanZhi::TianGan;
using ZhouYi::GanZhi::wu_xing_ke;
using ZhouYi::GanZhi::wu_xing_sheng;
using ZhouYi::GanZhi::WuXing;

bool heaven_has(const PalaceInfo &p, TianGan gan) {
  return p.tian_gan == gan || p.extra_tian_gan == gan;
}
bool earth_has(const PalaceInfo &p, TianGan gan) {
  return p.di_gan == gan || p.extra_di_gan == gan;
}
bool is_good_gate(Gate gate) {
  return gate == Gate::Kai || gate == Gate::Xiu || gate == Gate::Sheng;
}

std::size_t season_index(ZhouYi::GanZhi::DiZhi month_zhi) {
  using ZhouYi::GanZhi::DiZhi;
  switch (month_zhi) {
  case DiZhi::Yin:
  case DiZhi::Mao:
    return 0;
  case DiZhi::Si:
  case DiZhi::Wu:
    return 1;
  case DiZhi::Shen:
  case DiZhi::You:
    return 2;
  case DiZhi::Hai:
  case DiZhi::Zi:
    return 3;
  default:
    return 4;
  }
}

Strength star_strength(Star star, ZhouYi::GanZhi::DiZhi month_zhi) {
  constexpr std::array<std::array<Strength, 5>, 9> table{
      {{Strength::Wang, Strength::Xiu, Strength::Fei, Strength::Xiang,
        Strength::Qiu},
       {Strength::Qiu, Strength::Fei, Strength::Wang, Strength::Xiu,
        Strength::Xiang},
       {Strength::Xiang, Strength::Wang, Strength::Qiu, Strength::Fei,
        Strength::Xiu},
       {Strength::Xiang, Strength::Wang, Strength::Qiu, Strength::Fei,
        Strength::Xiu},
       {Strength::Qiu, Strength::Fei, Strength::Wang, Strength::Xiu,
        Strength::Xiang},
       {Strength::Xiu, Strength::Qiu, Strength::Xiang, Strength::Wang,
        Strength::Fei},
       {Strength::Xiu, Strength::Qiu, Strength::Xiang, Strength::Wang,
        Strength::Fei},
       {Strength::Qiu, Strength::Fei, Strength::Wang, Strength::Xiu,
        Strength::Xiang},
       {Strength::Fei, Strength::Xiang, Strength::Xiu, Strength::Qiu,
        Strength::Wang}}};
  return table[static_cast<std::size_t>(star)][season_index(month_zhi)];
}

Strength gate_strength(Gate gate, ZhouYi::GanZhi::DiZhi month_zhi) {
  if (gate == Gate::None)
    return Strength::Unknown;
  constexpr std::array<std::array<Strength, 5>, 8> table{
      {{Strength::Xiu, Strength::Qiu, Strength::Xiang, Strength::Wang,
        Strength::Si},
       {Strength::Si, Strength::Xiang, Strength::Xiu, Strength::Qiu,
        Strength::Wang},
       {Strength::Wang, Strength::Xiu, Strength::Si, Strength::Xiang,
        Strength::Qiu},
       {Strength::Wang, Strength::Xiu, Strength::Si, Strength::Xiang,
        Strength::Qiu},
       {Strength::Xiang, Strength::Wang, Strength::Qiu, Strength::Si,
        Strength::Xiu},
       {Strength::Si, Strength::Xiang, Strength::Xiu, Strength::Qiu,
        Strength::Wang},
       {Strength::Qiu, Strength::Si, Strength::Wang, Strength::Xiu,
        Strength::Xiang},
       {Strength::Qiu, Strength::Si, Strength::Wang, Strength::Xiu,
        Strength::Xiang}}};
  return table[static_cast<std::size_t>(gate)][season_index(month_zhi)];
}

void add(PalaceInfo &p, std::string id, std::string name, PatternNature nature,
         std::string pan_ju_basis) {
  p.patterns.push_back({std::move(id), std::move(name), nature, p.palace,
                        std::move(pan_ju_basis)});
}

void detect_palace_patterns(PalaceInfo &p, Gate zhi_shi,
                            const QiMenAnalysisContext &context) {
  const bool good_gate = is_good_gate(p.gate);
  const bool three_wonders_earth = earth_has(p, TianGan::Yi) ||
                                   earth_has(p, TianGan::Bing) ||
                                   earth_has(p, TianGan::Ding);
  const bool three_wonders_heaven = heaven_has(p, TianGan::Yi) ||
                                    heaven_has(p, TianGan::Bing) ||
                                    heaven_has(p, TianGan::Ding);
  const bool yin_group =
      p.spirit == Spirit::TaiYin || p.spirit == Spirit::LiuHe ||
      p.spirit == Spirit::JiuDi || p.spirit == Spirit::JiuTian;

  if (heaven_has(p, TianGan::Bing) && earth_has(p, TianGan::Ding) &&
      p.gate == Gate::Sheng)
    add(p, "jiu_dun.tian", "天遁", PatternNature::Auspicious,
        "天盘丙、地盘丁同临生门");
  if (heaven_has(p, TianGan::Yi) && earth_has(p, TianGan::Ji) &&
      p.gate == Gate::Kai)
    add(p, "jiu_dun.di", "地遁", PatternNature::Auspicious,
        "天盘乙、地盘己同临开门");
  if (heaven_has(p, TianGan::Ding) && p.gate == Gate::Xiu &&
      p.spirit == Spirit::TaiYin)
    add(p, "jiu_dun.ren", "人遁", PatternNature::Auspicious,
        "天盘丁、休门、太阴同宫");
  if (heaven_has(p, TianGan::Yi) && good_gate && p.palace == Palace::SouthEast)
    add(p, "jiu_dun.feng", "风遁", PatternNature::Auspicious,
        "天盘乙与开休生门临巽四宫");
  if (heaven_has(p, TianGan::Yi) && good_gate && earth_has(p, TianGan::Xin))
    add(p, "jiu_dun.yun", "云遁", PatternNature::Auspicious,
        "天盘乙与开休生门、地盘辛同宫");
  if (heaven_has(p, TianGan::Yi) && good_gate &&
      (p.palace == Palace::North || earth_has(p, TianGan::Gui)))
    add(p, "jiu_dun.long", "龙遁", PatternNature::Auspicious,
        "天盘乙与开休生门临坎宫或地盘癸");
  if ((heaven_has(p, TianGan::Yi) &&
       (p.gate == Gate::Xiu || p.gate == Gate::Sheng) &&
       (p.palace == Palace::NorthEast || earth_has(p, TianGan::Xin))) ||
      (heaven_has(p, TianGan::Geng) && p.gate == Gate::Kai &&
       p.palace == Palace::West))
    add(p, "jiu_dun.hu", "虎遁", PatternNature::Auspicious,
        "乙奇休生临艮辛，或庚临开门兑宫");
  if (heaven_has(p, TianGan::Bing) && p.gate == Gate::Sheng &&
      p.spirit == Spirit::JiuTian)
    add(p, "jiu_dun.shen", "神遁", PatternNature::Auspicious,
        "天盘丙、生门、九天同宫");
  if (heaven_has(p, TianGan::Ding) && p.gate == Gate::Du &&
      p.spirit == Spirit::JiuDi)
    add(p, "jiu_dun.gui", "鬼遁", PatternNature::Auspicious,
        "天盘丁、杜门、九地同宫");

  if (good_gate && p.spirit == Spirit::TaiYin)
    add(p, "san_zha.zhen", "真诈", PatternNature::Auspicious,
        "开休生门与太阴同宫");
  if (good_gate && three_wonders_earth && p.spirit == Spirit::LiuHe)
    add(p, "san_zha.xiu", "休诈", PatternNature::Auspicious,
        "地盘三奇、开休生门、六合同宫");
  if (good_gate && three_wonders_earth && p.spirit == Spirit::JiuDi)
    add(p, "san_zha.chong", "重诈", PatternNature::Auspicious,
        "地盘三奇、开休生门、九地同宫");
  if (three_wonders_heaven && p.gate == Gate::Jing &&
      p.spirit == Spirit::JiuTian)
    add(p, "wu_jia.tian", "天假", PatternNature::Auspicious,
        "天盘三奇、景门、九天同宫");
  const bool ding_ji_gui = earth_has(p, TianGan::Ding) ||
                           earth_has(p, TianGan::Ji) ||
                           earth_has(p, TianGan::Gui);
  if (ding_ji_gui && p.gate == Gate::Du &&
      (p.spirit == Spirit::JiuDi || p.spirit == Spirit::TaiYin ||
       p.spirit == Spirit::LiuHe))
    add(p, "wu_jia.di", "地假", PatternNature::Auspicious,
        "地盘丁己癸、杜门与九地太阴六合之一同宫");
  if (earth_has(p, TianGan::Ren) && p.gate == Gate::JingGate &&
      p.spirit == Spirit::JiuTian)
    add(p, "wu_jia.ren", "人假", PatternNature::Auspicious,
        "地盘壬、惊门、九天同宫");
  if (ding_ji_gui && p.gate == Gate::Shang && p.spirit == Spirit::JiuDi)
    add(p, "wu_jia.shen", "神假", PatternNature::Auspicious,
        "地盘丁己癸、伤门、九地同宫");
  if (ding_ji_gui && p.gate == Gate::Si && p.spirit == Spirit::JiuDi)
    add(p, "wu_jia.gui", "鬼假", PatternNature::Auspicious,
        "地盘丁己癸、死门、九地同宫");

  if (three_wonders_earth && good_gate && yin_group)
    add(p, "common.san_qi_zhi_ling", "三奇之灵", PatternNature::Auspicious,
        "地盘三奇与开休生门、太阴六合九地九天之一同宫");
  if (good_gate &&
      ((earth_has(p, TianGan::Yi) && p.palace == Palace::East) ||
       (earth_has(p, TianGan::Bing) && p.palace == Palace::SouthEast) ||
       (earth_has(p, TianGan::Ding) && p.palace == Palace::South)))
    add(p, "common.qi_you_lu_wei", "奇游禄位", PatternNature::Auspicious,
        "地盘三奇临本禄宫并得开休生门");
  if (three_wonders_earth && p.is_zhi_fu)
    add(p, "common.huan_yi", "欢怡", PatternNature::Auspicious,
        "地盘三奇与值符同宫");
  if ((earth_has(p, TianGan::Yi) && p.palace == Palace::East) ||
      (earth_has(p, TianGan::Bing) && p.palace == Palace::South) ||
      (earth_has(p, TianGan::Ding) && p.palace == Palace::West))
    add(p, "common.san_qi_sheng_dian", "三奇贵人升殿",
        PatternNature::Auspicious, "乙升震、丙升离或丁升兑");
  if (p.gate == zhi_shi && three_wonders_heaven)
    add(p, "common.san_qi_de_shi", "三奇得使", PatternNature::Auspicious,
        "天盘三奇落值使门");
  if (p.gate == zhi_shi && earth_has(p, TianGan::Ding))
    add(p, "common.yu_nu_shou_men", "玉女守门", PatternNature::Auspicious,
        "地盘丁奇落值使门");
  if (heaven_has(p, TianGan::Wu) && earth_has(p, TianGan::Bing))
    add(p, "common.qing_long_fan_shou", "青龙返首", PatternNature::Auspicious,
        "天盘戊加地盘丙");
  if (heaven_has(p, TianGan::Bing) && earth_has(p, TianGan::Wu))
    add(p, "common.fei_niao_die_xue", "飞鸟跌穴", PatternNature::Auspicious,
        "天盘丙加地盘戊");
  if (p.gate != Gate::None &&
      (wu_xing_sheng(gate_element(p.gate), palace_element(p.palace)) ||
       wu_xing_sheng(palace_element(p.palace), gate_element(p.gate))))
    add(p, "common.men_gong_he_yi", "门宫和义", PatternNature::Auspicious,
        "门宫五行相生");
  if (good_gate &&
      ((heaven_has(p, TianGan::Yi) && earth_has(p, TianGan::Geng)) ||
       (heaven_has(p, TianGan::Bing) && earth_has(p, TianGan::Xin)) ||
       (heaven_has(p, TianGan::Ding) && earth_has(p, TianGan::Ren))))
    add(p, "common.qi_he", "奇仪相和·奇和", PatternNature::Auspicious,
        "三奇在天盘与相合六仪同宫，并得开休生门");
  if (good_gate &&
      ((heaven_has(p, TianGan::Wu) && earth_has(p, TianGan::Gui)) ||
       (heaven_has(p, TianGan::Jia) && earth_has(p, TianGan::Ji))))
    add(p, "common.yi_he", "奇仪相和·仪和", PatternNature::Auspicious,
        "天盘戊甲与地盘癸己相合，并得开休生门");

  struct PairRule {
    TianGan heaven;
    TianGan earth;
    std::string_view id, name, pan_ju_basis;
  };
  constexpr std::array<PairRule, 12> bad{
      {{TianGan::Yi, TianGan::Xin, "bad.qing_long_tao_zou", "青龙逃走",
        "天盘乙加地盘辛"},
       {TianGan::Xin, TianGan::Yi, "bad.bai_hu_chang_kuang", "白虎猖狂",
        "天盘辛加地盘乙"},
       {TianGan::Bing, TianGan::Geng, "bad.ying_ru_tai_bai", "荧入太白",
        "天盘丙加地盘庚"},
       {TianGan::Geng, TianGan::Bing, "bad.tai_bai_ru_ying", "太白入荧",
        "天盘庚加地盘丙"},
       {TianGan::Ding, TianGan::Gui, "bad.zhu_que_tou_jiang", "朱雀投江",
        "天盘丁加地盘癸"},
       {TianGan::Gui, TianGan::Ding, "bad.teng_she_yao_jiao", "螣蛇夭矫",
        "天盘癸加地盘丁"},
       {TianGan::Geng, TianGan::Gui, "bad.da_ge", "大格", "天盘庚加地盘癸"},
       {TianGan::Geng, TianGan::Ren, "bad.xiao_ge", "小格", "天盘庚加地盘壬"},
       {TianGan::Geng, TianGan::Ji, "bad.xing_ge", "刑格", "天盘庚加地盘己"},
       {TianGan::Geng, TianGan::Wu, "bad.fu_gong_ge", "伏宫格",
        "天盘庚加地盘戊"},
       {TianGan::Wu, TianGan::Geng, "bad.fei_gong_ge", "飞宫格",
        "天盘戊加地盘庚"},
       {TianGan::Gui, TianGan::Gui, "bad.tian_wang_si_zhang", "天网四张",
        "天盘癸加地盘癸"}}};
  for (const auto &rule : bad)
    if (heaven_has(p, rule.heaven) && earth_has(p, rule.earth))
      add(p, std::string(rule.id), std::string(rule.name),
          PatternNature::Inauspicious, std::string(rule.pan_ju_basis));
  if (heaven_has(p, TianGan::Geng) &&
      (earth_has(p, TianGan::Yi) || earth_has(p, TianGan::Bing) ||
       earth_has(p, TianGan::Ding)))
    add(p, "bad.qi_ge", "奇格", PatternNature::Inauspicious,
        "天盘庚克地盘乙丙丁三奇");
  if ((heaven_has(p, TianGan::Yi) &&
       (p.palace == Palace::NorthWest || p.palace == Palace::SouthWest)) ||
      (heaven_has(p, TianGan::Bing) && p.palace == Palace::NorthWest) ||
      (heaven_has(p, TianGan::Ding) && p.palace == Palace::NorthEast)) {
    p.has_san_qi_tomb = true;
    add(p, "bad.san_qi_ru_mu", "三奇入墓", PatternNature::Inauspicious,
        "乙入乾坤、丙入乾或丁入艮墓");
  }
  if ((heaven_has(p, TianGan::Bing) &&
       (p.palace == Palace::North || earth_has(p, TianGan::Ren) ||
        earth_has(p, TianGan::Gui))) ||
      (heaven_has(p, TianGan::Ding) &&
       (p.palace == Palace::North || earth_has(p, TianGan::Ren) ||
        earth_has(p, TianGan::Gui))) ||
      (heaven_has(p, TianGan::Yi) &&
       (p.palace == Palace::NorthWest || p.palace == Palace::West ||
        earth_has(p, TianGan::Geng) || earth_has(p, TianGan::Xin))))
    add(p, "bad.san_qi_shou_xing", "三奇受刑", PatternNature::Inauspicious,
        "三奇临受制宫位或被地盘金水所制");
  if (heaven_has(p, TianGan::Bing) && p.is_zhi_fu ||
      earth_has(p, TianGan::Bing) && p.is_zhi_fu)
    add(p, "bad.bei_ge", "悖格", PatternNature::Inauspicious, "丙奇与值符同宫");
  if (heaven_has(p, TianGan::Geng) && earth_has(p, context.year_gan))
    add(p, "bad.sui_ge", "岁格", PatternNature::Inauspicious,
        "天盘庚加地盘年干");
  if (heaven_has(p, TianGan::Geng) && earth_has(p, context.month_gan))
    add(p, "bad.yue_ge", "月格", PatternNature::Inauspicious,
        "天盘庚加地盘月干");
  if (heaven_has(p, TianGan::Geng) && earth_has(p, context.day_gan)) {
    add(p, "bad.ri_ge", "日格", PatternNature::Inauspicious,
        "天盘庚加地盘日干");
    add(p, "bad.fu_gan_ge", "伏干格", PatternNature::Inauspicious,
        "天盘庚加地盘日干，日干伏于庚下");
  }
  if (heaven_has(p, context.day_gan) && earth_has(p, TianGan::Geng))
    add(p, "bad.fei_gan_ge", "飞干格", PatternNature::Inauspicious,
        "天盘日干加地盘庚");
  if (heaven_has(p, TianGan::Geng) && earth_has(p, context.hour_gan))
    add(p, "bad.shi_ge", "时格", PatternNature::Inauspicious,
        "天盘庚加地盘时干");

  const auto is_hour_tomb = [&](ZhouYi::GanZhi::DiZhi branch) {
    using ZhouYi::GanZhi::DiZhi;
    switch (context.hour_gan) {
    case TianGan::Yi:
      return branch == DiZhi::Wei || branch == DiZhi::Xu;
    case TianGan::Bing:
    case TianGan::Wu:
      return branch == DiZhi::Xu;
    case TianGan::Gui:
      return branch == DiZhi::Wei;
    case TianGan::Ding:
    case TianGan::Ji:
    case TianGan::Geng:
      return branch == DiZhi::Chou;
    case TianGan::Xin:
    case TianGan::Ren:
      return branch == DiZhi::Chen;
    default:
      return false;
    }
  };
  if (heaven_has(p, context.hour_gan) &&
      std::ranges::any_of(std::span(p.branches).first(p.branch_count),
                          is_hour_tomb))
    add(p, "bad.shi_gan_ru_mu", "时干入墓", PatternNature::Inauspicious,
        "天盘时干临其墓支所在宫");
}

void add_global(QiMenPan &pan, std::string id, std::string name,
                PatternNature nature, std::string pan_ju_basis) {
  pan.global_patterns.push_back({std::move(id), std::move(name), nature,
                                 Palace::Center, std::move(pan_ju_basis)});
}
} // namespace

void analyze_qi_men_pan(QiMenPan &pan, const QiMenAnalysisContext &context) {
  pan.global_patterns.clear();
  for (auto &p : pan.palaces) {
    p.patterns.clear();
    p.star_strength = star_strength(p.star, context.month_zhi);
    p.gate_strength = gate_strength(p.gate, context.month_zhi);
    p.has_gate_pressure =
        p.gate != Gate::None &&
        wu_xing_ke(gate_element(p.gate), palace_element(p.palace));
    if (p.has_gate_pressure)
      add(p, "bad.men_po", "门迫", PatternNature::Inauspicious,
          "八门五行克落宫五行");
    if (p.gate != Gate::None &&
        wu_xing_ke(palace_element(p.palace), gate_element(p.gate)))
      add(p, "bad.men_zhi", "门受制", PatternNature::Inauspicious,
          "落宫五行克八门五行");
    p.has_liu_yi_ji_xing =
        (p.tian_gan == TianGan::Wu && p.palace == Palace::East) ||
        (p.tian_gan == TianGan::Ji && p.palace == Palace::SouthWest) ||
        (p.tian_gan == TianGan::Geng && p.palace == Palace::NorthEast) ||
        (p.tian_gan == TianGan::Xin && p.palace == Palace::South) ||
        ((p.tian_gan == TianGan::Ren || p.tian_gan == TianGan::Gui) &&
         p.palace == Palace::SouthEast);
    if (p.has_liu_yi_ji_xing)
      add(p, "bad.liu_yi_ji_xing", "六仪击刑", PatternNature::Inauspicious,
          "旬首六仪落入所击刑之宫");
    detect_palace_patterns(p, pan.zhi_shi_gate, context);
  }

  const auto same_group = [](TianGan day, TianGan a, TianGan b) {
    return day == a || day == b;
  };
  const bool tian_xian =
      (same_group(context.day_gan, TianGan::Jia, TianGan::Ji) &&
       context.hour_gan == TianGan::Jia &&
       (pan.xun_shou == JiaXun::JiaZi || pan.xun_shou == JiaXun::JiaXu)) ||
      (same_group(context.day_gan, TianGan::Yi, TianGan::Geng) &&
       pan.xun_shou == JiaXun::JiaShen && context.hour_gan == TianGan::Jia) ||
      (same_group(context.day_gan, TianGan::Bing, TianGan::Xin) &&
       pan.xun_shou == JiaXun::JiaWu && context.hour_gan == TianGan::Jia) ||
      (same_group(context.day_gan, TianGan::Ding, TianGan::Ren) &&
       pan.xun_shou == JiaXun::JiaChen && context.hour_gan == TianGan::Jia) ||
      (same_group(context.day_gan, TianGan::Wu, TianGan::Gui) &&
       pan.xun_shou == JiaXun::JiaYin && context.hour_gan == TianGan::Jia);
  if (tian_xian)
    add_global(pan, "common.tian_xian_shi_ge", "天显时格",
               PatternNature::Auspicious, "日干与甲时旬首满足天显时格");
  const int expected_killer = (static_cast<int>(context.day_gan) + 6) % 10;
  if (static_cast<int>(context.hour_gan) == expected_killer)
    add_global(pan, "bad.wu_bu_yu_shi", "五不遇时", PatternNature::Inauspicious,
               "时干隔五克日干");
}

std::string format_qi_men_analysis(const QiMenPan &pan) {
  std::string out = "【格局闭环】\n";
  std::size_t count = 0;
  for (const auto &g : pan.global_patterns) {
    out += fmt::format("{}. [{}] {}：{}\n", ++count,
                       ZhouYi::Mapper::to_zh(g.nature), g.name, g.pan_ju_basis);
  }
  for (const auto &p : pan.palaces)
    for (const auto &g : p.patterns)
      out += fmt::format("{}. [{}] {}·{}：{}\n", ++count,
                         ZhouYi::Mapper::to_zh(g.nature), palace_name(p.palace),
                         g.name, g.pan_ju_basis);
  if (count == 0)
    out += "本盘未满足当前规则库中任何格局的全部成格条件。\n";
  out +=
      fmt::format("合计：{} 条；仅输出已经成格的盘局，不列待定占位。\n", count);
  return out;
}
} // namespace ZhouYi::QiMen

namespace ZhouYi::QiMenAnalysis {

AnalysisResult analyze(const ZhouYi::QiMen::QiMenPan &pan,
                       const AnalysisRequest &request) {
  return judge(pan, request);
}

} // namespace ZhouYi::QiMenAnalysis
