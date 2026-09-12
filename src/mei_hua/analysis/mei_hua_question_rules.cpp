/**
 * @file mei_hua_question_rules.cpp
 * @brief 梅花易数分门占问策略实现。
 */
module ZhouYi.MeiHua.Analysis.QuestionRules;

import ZhouYi.MeiHua;
import std;

namespace ZhouYi::MeiHuaAnalysis {
namespace {
using ZhouYi::MeiHua::Trigram;

bool supportive(TiYongRelation relation) {
  return relation == TiYongRelation::BiHe ||
         relation == TiYongRelation::YongShengTi;
}

bool controlling(TiYongRelation relation) {
  return relation == TiYongRelation::YongKeTi;
}

bool is_one_of(Trigram trigram, std::initializer_list<Trigram> values) {
  return std::ranges::find(values, trigram) != values.end();
}

void append_common(QuestionReading &reading, const TrigramReading &ben,
                   const TrigramReading &bian, const PartyBalance &balance) {
  if (supportive(ben.relation))
    reading.favorable_signs.push_back("本卦比和或用来生体，起始得助");
  if (supportive(bian.relation))
    reading.favorable_signs.push_back("变用比和或生体，终局有归");
  if (controlling(ben.relation))
    reading.obstructive_signs.push_back("本卦用来克体，眼前受制");
  if (controlling(bian.relation))
    reading.obstructive_signs.push_back("变用克体，后势反制");
  if (balance.dominant == PartySide::Ti)
    reading.favorable_signs.push_back("体党得势，自身承应之力较足");
  else if (balance.dominant == PartySide::Yong)
    reading.obstructive_signs.push_back("用党得势，外局牵制较重");
}

QuestionReading general_strategy(const ZhouYi::MeiHua::MeiHuaPan &,
                                 const TrigramReading &ben,
                                 const TrigramReading &bian,
                                 const PartyBalance &balance) {
  QuestionReading reading{.focus = "以体为己、用为事，统看始中终三段卦势"};
  append_common(reading, ben, bian, balance);
  reading.interpretation = balance.interpretation;
  return reading;
}

QuestionReading career_strategy(const ZhouYi::MeiHua::MeiHuaPan &pan,
                                const TrigramReading &ben,
                                const TrigramReading &bian,
                                const PartyBalance &balance) {
  QuestionReading reading{.focus =
                              "体主本人根基，用主职位、项目、上级与外部规则"};
  append_common(reading, ben, bian, balance);
  if (is_one_of(pan.ti_yong.yong, {Trigram::Qian, Trigram::Zhen, Trigram::Li}))
    reading.favorable_signs.push_back(
        "用卦见乾、震或离，得刚健、发动、文明之象");
  if (pan.moving_line >= 4)
    reading.favorable_signs.push_back("动在外卦，职位平台与外部机会先有变动");
  reading.interpretation = supportive(bian.relation)
                               ? "后势归于生体或比和，谋事可借平台与外援推进"
                               : "后势未能扶体，宜先固根基、审规则，再谋进取";
  return reading;
}

QuestionReading wealth_strategy(const ZhouYi::MeiHua::MeiHuaPan &pan,
                                const TrigramReading &ben,
                                const TrigramReading &bian,
                                const PartyBalance &balance) {
  QuestionReading reading{.focus = "体主求财者，用主财源、交易与对手盘"};
  append_common(reading, ben, bian, balance);
  if (ben.relation == TiYongRelation::TiKeYong)
    reading.favorable_signs.push_back("体克用，财可为我所取，但须亲力经营");
  if (ben.relation == TiYongRelation::TiShengYong)
    reading.obstructive_signs.push_back("体生用，先见投入、垫资或让利");
  if (is_one_of(pan.ti_yong.yong, {Trigram::Dui, Trigram::Gen, Trigram::Kun}))
    reading.favorable_signs.push_back(
        "用卦见兑、艮或坤，财货、积蓄、地产之象可取");
  reading.interpretation =
      bian.relation == TiYongRelation::YongKeTi
          ? "变用反克体，后段防回款受制、成本反压或财来伤身"
          : "变局未见重克体，财路可循体用制化渐次落实";
  return reading;
}

QuestionReading relationship_strategy(const ZhouYi::MeiHua::MeiHuaPan &pan,
                                      const TrigramReading &ben,
                                      const TrigramReading &bian,
                                      const PartyBalance &balance) {
  QuestionReading reading{.focus =
                              "体主求测一方，用主对方，体用生克察亲疏聚散"};
  append_common(reading, ben, bian, balance);
  if (ben.relation == TiYongRelation::BiHe)
    reading.favorable_signs.push_back("体用比和，双方气类相投");
  if (pan.moving_line == 3 || pan.moving_line == 4)
    reading.obstructive_signs.push_back("动临内外交界，关系进退易有反复");
  reading.interpretation = supportive(bian.relation)
                               ? "终局仍能生体或比和，关系有回护与相合之机"
                               : "终局见耗泄或克体，须防一方迁就过重或渐生离意";
  return reading;
}

QuestionReading illness_strategy(const ZhouYi::MeiHua::MeiHuaPan &pan,
                                 const TrigramReading &ben,
                                 const TrigramReading &bian,
                                 const PartyBalance &balance) {
  const auto &body = ZhouYi::MeiHua::trigram_info(pan.ti_yong.ti);
  QuestionReading reading{.focus = "体为身，用为病势，生体者为药，克体者为病"};
  append_common(reading, ben, bian, balance);
  reading.favorable_signs.push_back("体卦人体类象重点参看：" + body.body_image);
  if (ben.relation == TiYongRelation::YongKeTi)
    reading.obstructive_signs.push_back("用来克体，病势直接犯身");
  if (bian.relation == TiYongRelation::YongShengTi)
    reading.favorable_signs.push_back("变用生体，后程得药得护");
  reading.interpretation =
      controlling(bian.relation)
          ? "变用仍克体，病象未退，宜循体卦所主部位审慎调护"
          : "后势未再重克体，若体党得令，病势有缓解之象";
  return reading;
}

QuestionReading travel_strategy(const ZhouYi::MeiHua::MeiHuaPan &pan,
                                const TrigramReading &ben,
                                const TrigramReading &bian,
                                const PartyBalance &balance) {
  QuestionReading reading{.focus =
                              "体主行人，用主所往之地与路途，内外卦定远近"};
  append_common(reading, ben, bian, balance);
  if (pan.moving_line >= 4)
    reading.favorable_signs.push_back("外卦发动，出行、迁动之象已显");
  else
    reading.obstructive_signs.push_back(
        "内卦发动，心动先于身动，行程尚有内务牵绊");
  const auto &destination = ZhouYi::MeiHua::trigram_info(pan.ti_yong.yong);
  reading.interpretation =
      "用卦应方在" + destination.direction + "，" +
      (controlling(bian.relation) ? "变局克体，路途宜缓行避险"
                                  : "变局未见重克，行止可随势安排");
  return reading;
}

QuestionReading study_strategy(const ZhouYi::MeiHua::MeiHuaPan &pan,
                               const TrigramReading &ben,
                               const TrigramReading &bian,
                               const PartyBalance &balance) {
  QuestionReading reading{.focus = "体主学人，用主文书、考题、师长与名次"};
  append_common(reading, ben, bian, balance);
  if (is_one_of(pan.ti_yong.yong, {Trigram::Li, Trigram::Xun, Trigram::Qian}))
    reading.favorable_signs.push_back(
        "用见离、巽或乾，文章、条理与进取之象可取");
  reading.interpretation = supportive(bian.relation)
                               ? "变局生体或比和，后程理解与文书较能落实"
                               : "变局耗克体，须防分心、失序或临场受压";
  return reading;
}

QuestionReading lost_strategy(const ZhouYi::MeiHua::MeiHuaPan &pan,
                              const TrigramReading &ben,
                              const TrigramReading &bian,
                              const PartyBalance &balance) {
  const auto &object = ZhouYi::MeiHua::trigram_info(pan.ti_yong.yong);
  QuestionReading reading{.focus = "体主寻者，用主失物或所寻之人"};
  append_common(reading, ben, bian, balance);
  if (ben.relation == TiYongRelation::TiKeYong)
    reading.favorable_signs.push_back("体能制用，尚有寻回之机");
  if (ben.relation == TiYongRelation::YongKeTi)
    reading.obstructive_signs.push_back("用反克体，物远难取或受人阻隔");
  reading.interpretation =
      "先向" + object.direction + "寻访，并察" + object.nature + "形之处；" +
      (supportive(bian.relation) ? "终局有回归之象" : "终局未扶体，宜速寻勿迟");
  return reading;
}

QuestionReading weather_strategy(const ZhouYi::MeiHua::MeiHuaPan &pan,
                                 const TrigramReading &ben,
                                 const TrigramReading &bian,
                                 const PartyBalance &balance) {
  QuestionReading reading{.focus =
                              "体察天候根本，用察当下气象，互变定转晴转雨"};
  append_common(reading, ben, bian, balance);
  const auto active = pan.ti_yong.yong;
  if (is_one_of(active, {Trigram::Li, Trigram::Qian}))
    reading.favorable_signs.push_back("用见离乾，有晴明开朗之象");
  if (is_one_of(active, {Trigram::Kan, Trigram::Dui}))
    reading.obstructive_signs.push_back("用见坎兑，有雨泽湿润之象");
  reading.interpretation =
      "本用主眼前天候，变用主后势；兼察震雷、巽风、坤阴、艮止之象";
  return reading;
}

QuestionReading affairs_strategy(const ZhouYi::MeiHua::MeiHuaPan &,
                                 const TrigramReading &ben,
                                 const TrigramReading &bian,
                                 const PartyBalance &balance) {
  QuestionReading reading{.focus = "体主本人，用主所接人事与外来进退"};
  append_common(reading, ben, bian, balance);
  reading.interpretation = supportive(bian.relation)
                               ? "人事后势归于生体比和，可顺势相与"
                               : "人事后势耗克体卦，宜守分辨向背";
  return reading;
}

QuestionReading household_strategy(const ZhouYi::MeiHua::MeiHuaPan &pan,
                                   const TrigramReading &ben,
                                   const TrigramReading &bian,
                                   const PartyBalance &balance) {
  QuestionReading reading{.focus =
                              "体主家宅根基，用主宅中人口、门庭与外来扰动"};
  append_common(reading, ben, bian, balance);
  if (is_one_of(pan.ti_yong.yong, {Trigram::Kun, Trigram::Gen}))
    reading.favorable_signs.push_back("用见坤艮，宅基、门户与田土之象显");
  reading.interpretation = controlling(bian.relation)
                               ? "变用克体，宅中后势受扰，宜先安门庭根基"
                               : "变局未重克宅体，家宅可随生扶之象调和";
  return reading;
}

QuestionReading building_strategy(const ZhouYi::MeiHua::MeiHuaPan &pan,
                                  const TrigramReading &ben,
                                  const TrigramReading &bian,
                                  const PartyBalance &balance) {
  QuestionReading reading{.focus = "体主屋舍根基，用主营造、修葺及外部形势"};
  append_common(reading, ben, bian, balance);
  const auto &image = ZhouYi::MeiHua::trigram_info(pan.ti_yong.yong);
  reading.favorable_signs.push_back("用卦场所类象：" + image.place_image);
  reading.interpretation = supportive(bian.relation)
                               ? "变局扶体，修造之后较能安定成形"
                               : "变局耗克屋体，施工、结构或周边形势须慎";
  return reading;
}

QuestionReading birth_strategy(const ZhouYi::MeiHua::MeiHuaPan &pan,
                               const TrigramReading &ben,
                               const TrigramReading &bian,
                               const PartyBalance &balance) {
  QuestionReading reading{.focus = "体主产者，用主胎息与生产过程，变卦察产后"};
  append_common(reading, ben, bian, balance);
  if (pan.moving_line == 3 || pan.moving_line == 4)
    reading.obstructive_signs.push_back("动临内外交界，生产过程转折较显");
  reading.interpretation = controlling(bian.relation)
                               ? "变用仍克体，产后承应不足，宜慎护体气"
                               : "后势未重克体，若再得生体之象则较安顺";
  return reading;
}

QuestionReading food_strategy(const ZhouYi::MeiHua::MeiHuaPan &pan,
                              const TrigramReading &ben,
                              const TrigramReading &bian,
                              const PartyBalance &balance) {
  QuestionReading reading{.focus = "体主饮食者，用主食物、宴席与入口之物"};
  append_common(reading, ben, bian, balance);
  const auto &food = ZhouYi::MeiHua::trigram_info(pan.ti_yong.yong);
  reading.favorable_signs.push_back("食物器皿参用卦类象：" + food.object_image);
  reading.interpretation =
      controlling(ben.relation) || controlling(bian.relation)
          ? "用或变来克体，饮食宜慎洁净与节制"
          : "体用未见重克，宴饮之象可随卦气取舍";
  return reading;
}

QuestionReading planning_strategy(const ZhouYi::MeiHua::MeiHuaPan &pan,
                                  const TrigramReading &ben,
                                  const TrigramReading &bian,
                                  const PartyBalance &balance) {
  QuestionReading reading{.focus =
                              "体主谋者，用主所谋之事，互卦察过程，变卦定成否"};
  append_common(reading, ben, bian, balance);
  if (pan.moving_line <= 3)
    reading.favorable_signs.push_back("内卦发动，谋议先从内部落实");
  reading.interpretation = supportive(bian.relation)
                               ? "所谋后势扶体，得时得助则可成"
                               : "所谋后势耗克，宜改其路径或缓图";
  return reading;
}

QuestionReading trade_strategy(const ZhouYi::MeiHua::MeiHuaPan &pan,
                               const TrigramReading &ben,
                               const TrigramReading &bian,
                               const PartyBalance &balance) {
  QuestionReading reading{.focus =
                              "体主己方，用主对方、货物与价契，体用察成交得失"};
  append_common(reading, ben, bian, balance);
  if (ben.relation == TiYongRelation::TiKeYong)
    reading.favorable_signs.push_back("体能制用，议价与货权较能由己掌握");
  if (pan.moving_line >= 4)
    reading.obstructive_signs.push_back("外卦发动，市场或对方条件先变");
  reading.interpretation = controlling(bian.relation)
                               ? "变用反制，后段防毁约、压价或交割受阻"
                               : "变局未反克体，交易可循契约逐步落实";
  return reading;
}

QuestionReading traveler_strategy(const ZhouYi::MeiHua::MeiHuaPan &pan,
                                  const TrigramReading &ben,
                                  const TrigramReading &bian,
                                  const PartyBalance &balance) {
  QuestionReading reading{.focus = "体主问者，用主在外行人，变卦与应方察归期"};
  append_common(reading, ben, bian, balance);
  const auto &destination = ZhouYi::MeiHua::trigram_info(bian.trigram);
  reading.interpretation =
      supportive(bian.relation)
          ? "变局回生于体，有归来相见之象；先察" + destination.direction
          : "变局未归体，行人尚受外事牵留；先察" + destination.direction;
  return reading;
}

QuestionReading audience_strategy(const ZhouYi::MeiHua::MeiHuaPan &pan,
                                  const TrigramReading &ben,
                                  const TrigramReading &bian,
                                  const PartyBalance &balance) {
  QuestionReading reading{.focus = "体主求见者，用主所谒之人与门庭接引"};
  append_common(reading, ben, bian, balance);
  if (pan.moving_line >= 4)
    reading.favorable_signs.push_back("外卦发动，门庭已有回应之象");
  reading.interpretation = supportive(ben.relation) || supportive(bian.relation)
                               ? "用变有生扶比和，所谒较有接纳之机"
                               : "用变耗克体卦，所谒恐有阻隔或不得其时";
  return reading;
}

QuestionReading litigation_strategy(const ZhouYi::MeiHua::MeiHuaPan &,
                                    const TrigramReading &ben,
                                    const TrigramReading &bian,
                                    const PartyBalance &balance) {
  QuestionReading reading{.focus = "体主己方，用主对方与讼事，克体者主受制"};
  append_common(reading, ben, bian, balance);
  if (ben.relation == TiYongRelation::TiKeYong)
    reading.favorable_signs.push_back("体克用，己方较能制其讼端");
  if (controlling(ben.relation) || controlling(bian.relation))
    reading.obstructive_signs.push_back("用或变来克体，讼势对己不利");
  reading.interpretation = controlling(bian.relation)
                               ? "末后仍受制，宜和解息争，不宜强进"
                               : "末后未重克体，仍须察体卦旺衰及外应向背";
  return reading;
}

QuestionReading grave_strategy(const ZhouYi::MeiHua::MeiHuaPan &pan,
                               const TrigramReading &ben,
                               const TrigramReading &bian,
                               const PartyBalance &balance) {
  QuestionReading reading{.focus = "体主葬地主气，用主山水来去与穴场外势"};
  append_common(reading, ben, bian, balance);
  const auto &terrain = ZhouYi::MeiHua::trigram_info(pan.ti_yong.yong);
  reading.favorable_signs.push_back("外势场所取象：" + terrain.place_image +
                                    "；来方取" + terrain.direction);
  reading.interpretation = supportive(bian.relation)
                               ? "变局生扶或比和，后势较能聚气护体"
                               : "变局耗克体卦，穴场后势恐有泄散冲制";
  return reading;
}

using Strategy = QuestionReading (*)(const ZhouYi::MeiHua::MeiHuaPan &,
                                     const TrigramReading &,
                                     const TrigramReading &,
                                     const PartyBalance &);

constexpr std::array<Strategy, question_kind_count> strategies{
    general_strategy,      career_strategy,    wealth_strategy,
    relationship_strategy, illness_strategy,   travel_strategy,
    study_strategy,        lost_strategy,      weather_strategy,
    affairs_strategy,      household_strategy, building_strategy,
    birth_strategy,        food_strategy,      planning_strategy,
    trade_strategy,        traveler_strategy,  audience_strategy,
    litigation_strategy,   grave_strategy};
} // namespace

QuestionReading build_question_reading(const ZhouYi::MeiHua::MeiHuaPan &pan,
                                       const AnalysisRequest &request,
                                       const TrigramReading &ben_yong,
                                       const TrigramReading &bian_influence,
                                       const PartyBalance &party_balance) {
  const auto index = static_cast<std::size_t>(request.question_kind);
  return strategies.at(index)(pan, ben_yong, bian_influence, party_balance);
}

} // namespace ZhouYi::MeiHuaAnalysis
