// 大六壬神煞系统实现

module ZhouYi.DaLiuRen.ShenSha;

import fmt;
import nlohmann.json;

namespace ZhouYi::DaLiuRen::ShenSha {

using namespace ZhouYi::GanZhi;
namespace Mapper = ZhouYi::GanZhi::Mapper;

namespace {

using ZhiTable = std::array<DiZhi, 12>;
using GanTable = std::array<DiZhi, 10>;

// 表序统一使用子、丑……亥或甲、乙……癸。年、月、干、支、旬各自采用
// 固定查表口径，流派异表不在运行时混用。
constexpr ZhiTable kYiMaByZhi = {
    DiZhi::Yin,  DiZhi::Hai, DiZhi::Shen, DiZhi::Si,  DiZhi::Yin,  DiZhi::Hai,
    DiZhi::Shen, DiZhi::Si,  DiZhi::Yin,  DiZhi::Hai, DiZhi::Shen, DiZhi::Si};
constexpr ZhiTable kWangShenByZhi = {
    DiZhi::Hai, DiZhi::Shen, DiZhi::Si,  DiZhi::Yin,  DiZhi::Hai, DiZhi::Shen,
    DiZhi::Si,  DiZhi::Yin,  DiZhi::Hai, DiZhi::Shen, DiZhi::Si,  DiZhi::Yin};
constexpr ZhiTable kJieShaByZhi = {
    DiZhi::Si,  DiZhi::Yin,  DiZhi::Hai, DiZhi::Shen, DiZhi::Si,  DiZhi::Yin,
    DiZhi::Hai, DiZhi::Shen, DiZhi::Si,  DiZhi::Yin,  DiZhi::Hai, DiZhi::Shen};
constexpr ZhiTable kZaiShaByZhi = {
    DiZhi::Wu, DiZhi::Mao, DiZhi::Zi, DiZhi::You, DiZhi::Wu, DiZhi::Mao,
    DiZhi::Zi, DiZhi::You, DiZhi::Wu, DiZhi::Mao, DiZhi::Zi, DiZhi::You};
constexpr ZhiTable kTaoHuaByZhi = {
    DiZhi::You, DiZhi::Wu, DiZhi::Mao, DiZhi::Zi, DiZhi::You, DiZhi::Wu,
    DiZhi::Mao, DiZhi::Zi, DiZhi::You, DiZhi::Wu, DiZhi::Mao, DiZhi::Zi};
constexpr ZhiTable kHuaGaiByZhi = {DiZhi::Chen, DiZhi::Chou, DiZhi::Xu,
                                   DiZhi::Wei,  DiZhi::Chen, DiZhi::Chou,
                                   DiZhi::Xu,   DiZhi::Wei,  DiZhi::Chen,
                                   DiZhi::Chou, DiZhi::Xu,   DiZhi::Wei};
constexpr ZhiTable kPoSuiByZhi = {
    DiZhi::Si, DiZhi::Chou, DiZhi::You, DiZhi::Si, DiZhi::Chou, DiZhi::You,
    DiZhi::Si, DiZhi::Chou, DiZhi::You, DiZhi::Si, DiZhi::Chou, DiZhi::You};
constexpr ZhiTable kXingByZhi = {
    DiZhi::Mao, DiZhi::Xu,   DiZhi::Si,  DiZhi::Zi,  DiZhi::Chen, DiZhi::Shen,
    DiZhi::Wu,  DiZhi::Chou, DiZhi::Yin, DiZhi::You, DiZhi::Wei,  DiZhi::Hai};
constexpr ZhiTable kZhiYiByZhi = {
    DiZhi::Wu,  DiZhi::Si,   DiZhi::Chen, DiZhi::Mao, DiZhi::Yin, DiZhi::Chou,
    DiZhi::Wei, DiZhi::Shen, DiZhi::You,  DiZhi::Xu,  DiZhi::Hai, DiZhi::Zi};
constexpr ZhiTable kSiShenByZhi = {
    DiZhi::Mao, DiZhi::Chen, DiZhi::Si,  DiZhi::Wu, DiZhi::Wei,  DiZhi::Shen,
    DiZhi::You, DiZhi::Xu,   DiZhi::Hai, DiZhi::Zi, DiZhi::Chou, DiZhi::Yin};

constexpr GanTable kRiDeByGan = {
    DiZhi::Yin, DiZhi::Shen, DiZhi::Si, DiZhi::Hai, DiZhi::Si,
    DiZhi::Yin, DiZhi::Shen, DiZhi::Si, DiZhi::Hai, DiZhi::Si};
constexpr GanTable kRiLuByGan = {
    DiZhi::Yin, DiZhi::Mao,  DiZhi::Si,  DiZhi::Wu,  DiZhi::Si,
    DiZhi::Wu,  DiZhi::Shen, DiZhi::You, DiZhi::Hai, DiZhi::Zi};
constexpr GanTable kRiQiByGan = {
    DiZhi::Wu,   DiZhi::Si,  DiZhi::Chen, DiZhi::Mao, DiZhi::Yin,
    DiZhi::Chou, DiZhi::Wei, DiZhi::Shen, DiZhi::You, DiZhi::Xu};
constexpr GanTable kYangRenByGan = {
    DiZhi::Mao, DiZhi::Chen, DiZhi::Wu, DiZhi::Wei, DiZhi::Wu,
    DiZhi::Wei, DiZhi::You,  DiZhi::Xu, DiZhi::Zi,  DiZhi::Chou};
constexpr GanTable kSuiHeByGan = {
    DiZhi::Chou, DiZhi::Zi,  DiZhi::Hai, DiZhi::Xu, DiZhi::You,
    DiZhi::Shen, DiZhi::Wei, DiZhi::Wu,  DiZhi::Si, DiZhi::Chen};

constexpr std::size_t zhi_index(DiZhi zhi) {
  return static_cast<std::size_t>(zhi);
}

constexpr std::size_t gan_index(TianGan gan) {
  return static_cast<std::size_t>(gan);
}

constexpr std::size_t month_index(DiZhi month_zhi) {
  return static_cast<std::size_t>(
      (static_cast<int>(month_zhi) - static_cast<int>(DiZhi::Yin) + 12) % 12);
}

constexpr std::size_t season_index(DiZhi month_zhi) {
  return month_index(month_zhi) / 3;
}

constexpr DiZhi by_month(DiZhi month_zhi, const ZhiTable &table) {
  return table[month_index(month_zhi)];
}

} // namespace

// ==================== 构造函数 ====================

ShenShaEngine::ShenShaEngine(const BaZi &ba_zi, DiZhi day_zhi)
    : ba_zi_(ba_zi), day_zhi_(day_zhi) {}

// ==================== 年神煞计算 ====================

/**
 * 计算年驿马
 * 用途：主奔走、动荡、远行、迁移。逢吉则有升迁之喜，逢凶则有奔波之苦。
 * 驿马临传主外出、变动、走失；临日辰主有远行之事。
 */
DiZhi ShenShaEngine::calc_nian_yi_ma() const {
  return kYiMaByZhi[zhi_index(ba_zi_.year.zhi)];
}

/**
 * 计算大耗
 * 用途：主破财、损耗、消费、散财。临财爻主财物损失，临官爻主官讼破财。
 * 大耗发动主大额开支，临日辰主破财难免。
 */
DiZhi ShenShaEngine::calc_da_hao() const {
  DiZhi nian_zhi = ba_zi_.year.zhi;
  return nian_zhi + 6;
}

/**
 * 计算小耗
 * 用途：主小的损耗、日常开支、琐碎破费。比大耗轻微，主零星消费。
 * 小耗临传主有小额花费，临财爻主日常开销增加。
 */
DiZhi ShenShaEngine::calc_xiao_hao() const {
  DiZhi nian_zhi = ba_zi_.year.zhi;
  return nian_zhi + 5;
}

/**
 * 计算病符
 * 用途：主疾病、健康问题、医药之事。临日辰主自身有病，临他人主他人患病。
 * 病符发动主病情加重或新病发生，宜防范健康问题。
 */
DiZhi ShenShaEngine::calc_bing_fu() const {
  DiZhi nian_zhi = ba_zi_.year.zhi;
  return nian_zhi + (-1);
}

// ==================== 月神煞计算 ====================

/**
 * 计算月驿马
 * 用途：同年驿马，但以月建为主。主当月的走动、变化、迁移。
 * 月驿马临传主本月有外出之事，逢吉神主出行顺利。
 */
DiZhi ShenShaEngine::calc_yue_yi_ma() const {
  return kYiMaByZhi[zhi_index(ba_zi_.month.zhi)];
}

/**
 * 计算皇书
 * 用途：主文书、诏命、公文、契约、官方文件。为吉神，主有喜庆文书。
 * 皇书临传主有公文到达，求官得遇，考试有喜，文书顺利。
 */
DiZhi ShenShaEngine::calc_huang_shu() const {
  DiZhi yue_zhi = ba_zi_.month.zhi;

  if (yue_zhi == DiZhi::Yin || yue_zhi == DiZhi::Mao ||
      yue_zhi == DiZhi::Chen) {
    return DiZhi::Yin;
  }
  if (yue_zhi == DiZhi::Si || yue_zhi == DiZhi::Wu || yue_zhi == DiZhi::Wei) {
    return DiZhi::Si;
  }
  if (yue_zhi == DiZhi::Shen || yue_zhi == DiZhi::You || yue_zhi == DiZhi::Xu) {
    return DiZhi::Shen;
  }
  return DiZhi::Hai;
}

/**
 * 计算皇恩
 * 用途：主恩赐、恩惠、上级照顾、贵人提携。为大吉之神。
 * 皇恩临传主得贵人相助，上级赏识，有恩惠降临。
 */
DiZhi ShenShaEngine::calc_huang_en() const {
  DiZhi yue_zhi = ba_zi_.month.zhi;
  int offset =
      (static_cast<int>(yue_zhi) - static_cast<int>(DiZhi::Yin) + 12) % 12;
  return DiZhi::Wei + (offset * 2);
}

/**
 * 计算天诏/飞魂
 * 用途：天诏主诏令、召唤、通知；飞魂主魂魄不定、精神恍惚、心神不宁。
 * 临传主有消息传来，或精神状态不佳，需结合课式判断吉凶。
 */
DiZhi ShenShaEngine::calc_tian_zhao_fei_hun() const {
  DiZhi yue_zhi = ba_zi_.month.zhi;
  return yue_zhi + (-3);
}

/**
 * 计算天喜
 * 用途：主喜庆、婚姻、添丁、喜事。为吉神，主有喜庆之事。
 * 天喜临传主婚姻美满，家有喜事，添丁进口，百事皆喜。
 */
DiZhi ShenShaEngine::calc_tian_xi() const {
  DiZhi yue_zhi = ba_zi_.month.zhi;

  if (yue_zhi == DiZhi::Yin || yue_zhi == DiZhi::Mao ||
      yue_zhi == DiZhi::Chen) {
    return DiZhi::Xu;
  }
  if (yue_zhi == DiZhi::Si || yue_zhi == DiZhi::Wu || yue_zhi == DiZhi::Wei) {
    return DiZhi::Chou;
  }
  if (yue_zhi == DiZhi::Shen || yue_zhi == DiZhi::You || yue_zhi == DiZhi::Xu) {
    return DiZhi::Chen;
  }
  return DiZhi::Wei;
}

/**
 * 计算生气
 * 用途：主生机、活力、生长、新生。为吉神，主万物复苏，生机勃勃。
 * 生气临传主事业兴旺，身体健康，谋事有成，生生不息。
 */
DiZhi ShenShaEngine::calc_sheng_qi() const {
  DiZhi yue_zhi = ba_zi_.month.zhi;
  return yue_zhi + (-2);
}

/**
 * 计算天马
 * 用途：主快速、迅捷、奔驰、传递。与驿马类似但更强调速度。
 * 天马临传主事情进展迅速，消息传递快捷，行动敏捷。
 */
DiZhi ShenShaEngine::calc_tian_ma() const {
  DiZhi yue_zhi = ba_zi_.month.zhi;
  int offset =
      (static_cast<int>(yue_zhi) - static_cast<int>(DiZhi::Yin) + 12) % 12;
  return DiZhi::Wu + (offset * 2);
}

/**
 * 计算三丘
 * 用途：主坟墓、阴宅、死亡、丧葬之事。为凶神，主有丧亲之事。
 * 三丘临传主家中有丧事，或涉及坟墓阴宅之事，需防丧服之忧。
 */
DiZhi ShenShaEngine::calc_san_qiu() const {
  static constexpr std::array table = {DiZhi::Chou, DiZhi::Chen, DiZhi::Wei,
                                       DiZhi::Xu};
  return table[season_index(ba_zi_.month.zhi)];
}

/**
 * 计算五墓
 * 用途：主墓库、收藏、埋藏、隐伏。事物入库，难以显露。
 * 五墓临传主事情停滞不前，有埋没之象，财物收藏，难以发挥。
 */
DiZhi ShenShaEngine::calc_wu_mu() const {
  static constexpr std::array table = {DiZhi::Wei, DiZhi::Xu, DiZhi::Chou,
                                       DiZhi::Chen};
  return table[season_index(ba_zi_.month.zhi)];
}

/**
 * 计算死气/谩语
 * 用途：死气主死亡、衰败、终结；谩语主谎言、诈骗、虚假。
 * 临传主事业衰败或受人欺骗，言语不实，需防虚假消息和衰败之事。
 */
DiZhi ShenShaEngine::calc_si_qi_man_yu() const {
  DiZhi yue_zhi = ba_zi_.month.zhi;
  return yue_zhi + 4;
}

/**
 * 计算孤辰
 * 用途：主男子孤独、孤单、六亲缘薄。与寡宿配合论孤寡。
 * 孤辰临传主男子形单影只，六亲无靠，婚姻不顺，孤独寂寞。
 */
DiZhi ShenShaEngine::calc_gu_chen() const {
  static constexpr std::array table = {DiZhi::Si, DiZhi::Shen, DiZhi::Hai,
                                       DiZhi::Yin};
  return table[season_index(ba_zi_.month.zhi)];
}

/**
 * 计算寡宿
 * 用途：主女子孤寡、独居、六亲缘薄。与孤辰配合论孤寡。
 * 寡宿临传主女子孤寡无依，六亲缘薄，婚姻不顺，易有离别之苦。
 */
DiZhi ShenShaEngine::calc_gua_su() const {
  static constexpr std::array table = {DiZhi::Chou, DiZhi::Chen, DiZhi::Wei,
                                       DiZhi::Xu};
  return table[season_index(ba_zi_.month.zhi)];
}

/**
 * 计算天医
 * 用途：主医药、治疗、康复、医疗之事。为吉神，主疾病可治。
 * 天医临传主疾病可愈，医药得力，身体康复，医疗顺利。
 */
DiZhi ShenShaEngine::calc_tian_yi() const {
  static constexpr ZhiTable table = {
      DiZhi::Xu,   DiZhi::Hai, DiZhi::Zi, DiZhi::Chou, DiZhi::Yin,  DiZhi::Mao,
      DiZhi::Chen, DiZhi::Si,  DiZhi::Wu, DiZhi::Wei,  DiZhi::Shen, DiZhi::You};
  return by_month(ba_zi_.month.zhi, table);
}

/**
 * 计算地医
 * 用途：主医药、治疗，与天医类似但偏重地方医药、民间治疗。
 * 地医临传主可求民间医药，偏方有效，疾病可治。
 */
DiZhi ShenShaEngine::calc_di_yi() const {
  static constexpr ZhiTable table = {
      DiZhi::Chen, DiZhi::Si,  DiZhi::Wu, DiZhi::Wei,  DiZhi::Shen, DiZhi::You,
      DiZhi::Xu,   DiZhi::Hai, DiZhi::Zi, DiZhi::Chou, DiZhi::Yin,  DiZhi::Mao};
  return by_month(ba_zi_.month.zhi, table);
}

/**
 * 计算破碎
 * 用途：主破损、碎裂、毁坏、破败。为凶神，主器物损坏，事情败坏。
 * 破碎临传主物品损坏，事情破败，计划破裂，难以成全。
 */
DiZhi ShenShaEngine::calc_po_sui() const {
  static constexpr ZhiTable table = {
      DiZhi::You, DiZhi::Si, DiZhi::Chou, DiZhi::You, DiZhi::Si, DiZhi::Chou,
      DiZhi::You, DiZhi::Si, DiZhi::Chou, DiZhi::You, DiZhi::Si, DiZhi::Chou};
  return by_month(ba_zi_.month.zhi, table);
}

/**
 * 计算月厌
 * 用途：主厌恶、憎恨、不喜、厌倦。为凶神，主有令人厌恶之事。
 * 月厌临传主令人生厌，不受欢迎，遭人嫌弃，诸事不顺。
 */
DiZhi ShenShaEngine::calc_yue_yan() const {
  static constexpr ZhiTable table = {
      DiZhi::Xu,   DiZhi::You, DiZhi::Shen, DiZhi::Wei,  DiZhi::Wu, DiZhi::Si,
      DiZhi::Chen, DiZhi::Mao, DiZhi::Yin,  DiZhi::Chou, DiZhi::Zi, DiZhi::Hai};
  return by_month(ba_zi_.month.zhi, table);
}

/**
 * 计算血支
 * 用途：主血光、伤灾、流血之事。为凶神，主有血光之灾。
 * 血支临传主有血光之灾，刀伤出血，或产妇分娩，需防伤害。
 */
DiZhi ShenShaEngine::calc_xue_zhi() const { return ba_zi_.month.zhi + (-1); }

/**
 * 计算血忌
 * 用途：主血光、手术、外伤。与血支类似但更凶，主严重血光。
 * 血忌临传主有严重血光之灾，手术刀伤，需特别防范。
 */
DiZhi ShenShaEngine::calc_xue_ji() const {
  static constexpr ZhiTable table = {
      DiZhi::Chou, DiZhi::Wei, DiZhi::Yin, DiZhi::Shen, DiZhi::Mao, DiZhi::You,
      DiZhi::Chen, DiZhi::Xu,  DiZhi::Si,  DiZhi::Hai,  DiZhi::Wu,  DiZhi::Zi};
  return by_month(ba_zi_.month.zhi, table);
}

/**
 * 计算丧车
 * 用途：主丧葬、送葬、丧服、哀悼。为凶神，主有丧亲送葬之事。
 * 丧车临传主家有丧事，或参与丧葬，披麻戴孝，悲伤哀痛。
 */
DiZhi ShenShaEngine::calc_sang_che() const {
  static constexpr std::array table = {DiZhi::You, DiZhi::Zi, DiZhi::Mao,
                                       DiZhi::Wu};
  return table[season_index(ba_zi_.month.zhi)];
}

/**
 * 计算信神
 * 用途：主信息、消息、音讯、传递。主有消息到来或传信之事。
 * 信神临传主有消息到达，音讯灵通，或有人传信，信息准确。
 */
DiZhi ShenShaEngine::calc_xin_shen() const {
  static constexpr ZhiTable table = {DiZhi::Shen, DiZhi::Xu,   DiZhi::Yin,
                                     DiZhi::Chou, DiZhi::Hai,  DiZhi::Chen,
                                     DiZhi::Si,   DiZhi::Wei,  DiZhi::Si,
                                     DiZhi::Wei,  DiZhi::Shen, DiZhi::Xu};
  return by_month(ba_zi_.month.zhi, table);
}

/**
 * 计算天鸡
 * 用途：主报晓、传信、消息灵通、通讯。鸡司晨，主消息传递。
 * 天鸡临传主有消息传来，通讯顺畅，或有人报信，音讯及时。
 */
DiZhi ShenShaEngine::calc_tian_ji() const {
  DiZhi yue_zhi = ba_zi_.month.zhi;
  int offset =
      (static_cast<int>(DiZhi::Yin) - static_cast<int>(yue_zhi) + 12) % 12;
  return DiZhi::You + offset;
}

/**
 * 计算丧魂
 * 用途：主魂魄离散、精神恍惚、失魂落魄。为凶神，主精神不济。
 * 丧魂临传主精神恍惚，心神不定，失魂落魄，易受惊吓，魂不守舍。
 */
DiZhi ShenShaEngine::calc_sang_hun() const {
  static constexpr ZhiTable table = {DiZhi::Wei,  DiZhi::Chen, DiZhi::Chou,
                                     DiZhi::Xu,   DiZhi::Wei,  DiZhi::Chen,
                                     DiZhi::Chou, DiZhi::Xu,   DiZhi::Wei,
                                     DiZhi::Chen, DiZhi::Chou, DiZhi::Xu};
  return by_month(ba_zi_.month.zhi, table);
}

/**
 * 计算天鬼
 * 用途：主鬼魅、邪祟、怪异、不祥。为凶神，主有鬼魅作祟。
 * 天鬼临传主有怪异之事，鬼魅作祟，心神不宁，易见异象，需防邪祟。
 */
DiZhi ShenShaEngine::calc_tian_gui() const {
  static constexpr ZhiTable table = {
      DiZhi::You, DiZhi::Wu, DiZhi::Mao, DiZhi::Zi, DiZhi::You, DiZhi::Wu,
      DiZhi::Mao, DiZhi::Zi, DiZhi::You, DiZhi::Wu, DiZhi::Mao, DiZhi::Zi};
  return by_month(ba_zi_.month.zhi, table);
}

/**
 * 计算大时
 * 用途：主时运、时机、机遇。逢吉则时运亨通，逢凶则时运不济。
 * 大时临传主时机到来，把握得当则事半功倍，失时则错失良机。
 */
DiZhi ShenShaEngine::calc_da_shi() const {
  static constexpr std::array table = {DiZhi::Mao, DiZhi::Zi, DiZhi::You,
                                       DiZhi::Wu};
  return table[month_index(ba_zi_.month.zhi) % table.size()];
}

/**
 * 计算小时
 * 用途：主小的时机、短暂机会、片刻时光。比大时轻微，主短期时运。
 * 小时临传主有短暂机会，需及时把握，稍纵即逝，时机转瞬即过。
 */
DiZhi ShenShaEngine::calc_xiao_shi() const { return ba_zi_.month.zhi; }

// ==================== 日神煞计算 ====================

/**
 * 计算日驿马
 * 用途：主当日的奔走、动荡、外出、迁移。同年月驿马，但以日为主。
 * 日驿马临传主当日有走动之事，出行办事，或有远方消息。
 */
DiZhi ShenShaEngine::calc_ri_yi_ma() const {
  return kYiMaByZhi[zhi_index(day_zhi_)];
}

/**
 * 计算旬奇
 * 用途：主奇遇、奇特、意外之喜。为吉神，主有奇特之事或意外收获。
 * 旬奇临传主有奇遇，意外之喜，或遇贵人，得意外之财。
 */
DiZhi ShenShaEngine::calc_xun_qi() const {
  auto kong_wang = get_kong_wang(ba_zi_.day.gan, ba_zi_.day.zhi);
  DiZhi xun_shou = kong_wang[1] + 1;

  if (xun_shou == DiZhi::Xu || xun_shou == DiZhi::Zi) {
    return DiZhi::Chou;
  }
  if (xun_shou == DiZhi::Shen || xun_shou == DiZhi::Wu) {
    return DiZhi::Zi;
  }
  if (xun_shou == DiZhi::Yin || xun_shou == DiZhi::Chen) {
    return DiZhi::Hai;
  }
  return DiZhi::Chou; // 默认
}

/**
 * 计算日奇
 * 用途：主奇遇、奇特、非凡之事。以日干定之，主当日奇特之事。
 * 日奇临传主有奇遇，非凡之事，或遇异人，得意外之助。
 */
DiZhi ShenShaEngine::calc_ri_qi() const {
  return kRiQiByGan[gan_index(ba_zi_.day.gan)];
}

/**
 * 计算旬仪
 * 用途：主威仪、仪表、礼仪、规矩。为吉神，主有威严仪表之事。
 * 旬仪临传主威仪端庄，仪表堂堂，或有礼仪场合，规矩严谨。
 */
DiZhi ShenShaEngine::calc_xun_yi() const {
  auto kong_wang = get_kong_wang(ba_zi_.day.gan, ba_zi_.day.zhi);
  return kong_wang[1] + 1;
}

/**
 * 计算支仪
 * 用途：主威仪、仪表，以日支定之。同旬仪，主威严仪表。
 * 支仪临传主威仪端正，仪表不凡，或有庄重场合，礼数周全。
 */
DiZhi ShenShaEngine::calc_zhi_yi() const {
  return kZhiYiByZhi[zhi_index(day_zhi_)];
}

// ==================== 主计算函数 ====================

ShenShaResult ShenShaEngine::calculate() const {
  ShenShaResult result;

  // 基础神煞
  result.tai_sui = ba_zi_.year.zhi;
  result.yue_jian = ba_zi_.month.zhi;
  result.ri_jian = ba_zi_.day.zhi;
  result.yue_po = ba_zi_.month.zhi + 6; // 月破=月建对冲
  result.ri_po = ba_zi_.day.zhi + 6;    // 日破=日建对冲

  // 年神煞
  result.nian_yi_ma = calc_nian_yi_ma();
  result.da_hao = calc_da_hao();
  result.xiao_hao = calc_xiao_hao();
  result.bing_fu = calc_bing_fu();
  result.sui_de = calc_sui_de();
  result.sui_xing = calc_sui_xing();
  result.sui_he = calc_sui_he();
  result.jie_sha = calc_jie_sha();
  result.fei_lian = calc_fei_lian();
  result.sang_men = calc_sang_men();
  result.diao_ke = calc_diao_ke();
  result.wang_shen = calc_wang_shen();
  result.si_fu = calc_si_fu();
  result.hong_luan = calc_hong_luan();

  // 月神煞
  result.yue_yi_ma = calc_yue_yi_ma();
  result.huang_shu = calc_huang_shu();
  result.huang_en = calc_huang_en();
  result.tian_zhao = calc_tian_zhao_fei_hun();
  result.fei_hun = calc_tian_zhao_fei_hun();
  result.tian_xi = calc_tian_xi();
  result.sheng_qi = calc_sheng_qi();
  result.tian_ma = calc_tian_ma();
  result.cheng_shen = calc_cheng_shen();
  result.san_qiu = calc_san_qiu();
  result.wu_mu = calc_wu_mu();
  result.si_qi = calc_si_qi_man_yu();
  result.man_yu = calc_si_qi_man_yu();
  result.gu_chen = calc_gu_chen();
  result.gua_su = calc_gua_su();
  result.tian_yi = calc_tian_yi();
  result.di_yi = calc_di_yi();
  result.tian_wu = ba_zi_.month.zhi + 2; // 天巫正月起辰顺行
  result.jie_shen = calc_jie_shen();
  result.po_sui = calc_po_sui();
  result.yue_yan = calc_yue_yan();
  result.xue_zhi = calc_xue_zhi();
  result.xue_ji = calc_xue_ji();
  result.sang_che = calc_sang_che();
  result.xin_shen = calc_xin_shen();
  result.tian_ji = calc_tian_ji();
  result.sang_hun = calc_sang_hun();
  result.tian_gui = calc_tian_gui();
  result.da_shi = calc_da_shi();
  result.xiao_shi = calc_xiao_shi();
  result.yue_he = calc_yue_he();
  result.yue_xing = calc_yue_xing();
  result.huo_gui = calc_huo_gui();
  result.tian_mu = calc_tian_mu();
  result.jian_shen = calc_jian_shen();
  result.jian_men = calc_jian_men();
  result.yao_shen = calc_yao_shen();
  result.fei_huo = calc_fei_huo();

  // 日神煞
  result.ri_yi_ma = calc_ri_yi_ma();
  result.xun_qi = calc_xun_qi();
  result.ri_qi = calc_ri_qi();
  result.xun_yi = calc_xun_yi();
  result.zhi_yi = calc_zhi_yi();
  result.ri_de = calc_ri_de();
  result.ri_lu = calc_ri_lu();
  result.xun_ding = calc_xun_ding();
  result.tao_hua = calc_tao_hua();
  result.xian_chi = calc_xian_chi();
  result.ri_hua_gai = kHuaGaiByZhi[zhi_index(ba_zi_.day.zhi)];
  result.ri_jie_sha = kJieShaByZhi[zhi_index(ba_zi_.day.zhi)];
  result.ri_zai_sha = kZaiShaByZhi[zhi_index(ba_zi_.day.zhi)];
  result.ri_wang_shen = kWangShenByZhi[zhi_index(ba_zi_.day.zhi)];
  result.ri_po_sui = kPoSuiByZhi[zhi_index(ba_zi_.day.zhi)];
  result.zei_fu = calc_zei_fu();
  result.si_shen = calc_si_shen();
  result.zhi_de = calc_zhi_de();
  result.yang_ren = calc_yang_ren();

  // 构建地支到神煞的映射
  result.build_zhi_mapping();

  return result;
}

// ==================== 地支映射函数 ====================

// 构建地支到神煞的映射
void ShenShaResult::build_zhi_mapping() {
  // 清空之前的映射
  zhi_to_shensha_map.clear();

  // 基础神煞
  zhi_to_shensha_map[tai_sui].push_back("太岁");
  zhi_to_shensha_map[yue_jian].push_back("月建");
  zhi_to_shensha_map[ri_jian].push_back("日建");
  zhi_to_shensha_map[yue_po].push_back("月破");
  zhi_to_shensha_map[ri_po].push_back("日破");

  // 年神煞
  zhi_to_shensha_map[nian_yi_ma].push_back("年驿马");
  zhi_to_shensha_map[da_hao].push_back("大耗");
  zhi_to_shensha_map[xiao_hao].push_back("小耗");
  zhi_to_shensha_map[bing_fu].push_back("病符");
  zhi_to_shensha_map[sui_de].push_back("岁德");
  zhi_to_shensha_map[sui_xing].push_back("岁刑");
  zhi_to_shensha_map[sui_he].push_back("岁合");
  zhi_to_shensha_map[jie_sha].push_back("劫煞");
  zhi_to_shensha_map[fei_lian].push_back("飞廉");
  zhi_to_shensha_map[sang_men].push_back("丧门");
  zhi_to_shensha_map[diao_ke].push_back("吊客");
  zhi_to_shensha_map[wang_shen].push_back("亡神");
  zhi_to_shensha_map[si_fu].push_back("死符");
  zhi_to_shensha_map[hong_luan].push_back("红鸾");

  // 月神煞
  zhi_to_shensha_map[yue_yi_ma].push_back("月驿马");
  zhi_to_shensha_map[huang_shu].push_back("皇书");
  zhi_to_shensha_map[huang_en].push_back("皇恩");
  zhi_to_shensha_map[tian_zhao].push_back("天诏");
  zhi_to_shensha_map[fei_hun].push_back("飞魂");
  zhi_to_shensha_map[tian_xi].push_back("天喜");
  zhi_to_shensha_map[sheng_qi].push_back("生气");
  zhi_to_shensha_map[tian_ma].push_back("天马");
  zhi_to_shensha_map[cheng_shen].push_back("成神");
  zhi_to_shensha_map[san_qiu].push_back("三丘");
  zhi_to_shensha_map[wu_mu].push_back("五墓");
  zhi_to_shensha_map[si_qi].push_back("死气");
  zhi_to_shensha_map[man_yu].push_back("谩语");
  zhi_to_shensha_map[gu_chen].push_back("孤辰");
  zhi_to_shensha_map[gua_su].push_back("寡宿");
  zhi_to_shensha_map[tian_yi].push_back("天医");
  zhi_to_shensha_map[di_yi].push_back("地医");
  zhi_to_shensha_map[tian_wu].push_back("天巫");
  zhi_to_shensha_map[jie_shen].push_back("解神");
  zhi_to_shensha_map[po_sui].push_back("破碎");
  zhi_to_shensha_map[yue_yan].push_back("月厌");
  zhi_to_shensha_map[xue_zhi].push_back("血支");
  zhi_to_shensha_map[xue_ji].push_back("血忌");
  zhi_to_shensha_map[sang_che].push_back("丧车");
  zhi_to_shensha_map[xin_shen].push_back("信神");
  zhi_to_shensha_map[tian_ji].push_back("天鸡");
  zhi_to_shensha_map[sang_hun].push_back("丧魂");
  zhi_to_shensha_map[tian_gui].push_back("天鬼");
  zhi_to_shensha_map[da_shi].push_back("大时");
  zhi_to_shensha_map[xiao_shi].push_back("小时");
  zhi_to_shensha_map[yue_he].push_back("月合");
  zhi_to_shensha_map[yue_xing].push_back("月刑");
  zhi_to_shensha_map[huo_gui].push_back("火鬼");
  zhi_to_shensha_map[tian_mu].push_back("天目");
  zhi_to_shensha_map[jian_shen].push_back("奸神");
  zhi_to_shensha_map[jian_men].push_back("奸门");
  zhi_to_shensha_map[yao_shen].push_back("钥神");
  zhi_to_shensha_map[fei_huo].push_back("飞祸");

  // 日神煞
  zhi_to_shensha_map[ri_yi_ma].push_back("日驿马");
  zhi_to_shensha_map[xun_qi].push_back("旬奇");
  zhi_to_shensha_map[ri_qi].push_back("日奇");
  zhi_to_shensha_map[xun_yi].push_back("旬仪");
  zhi_to_shensha_map[zhi_yi].push_back("支仪");
  zhi_to_shensha_map[ri_de].push_back("日德");
  zhi_to_shensha_map[ri_lu].push_back("日禄");
  zhi_to_shensha_map[xun_ding].push_back("旬丁");
  zhi_to_shensha_map[tao_hua].push_back("桃花");
  zhi_to_shensha_map[xian_chi].push_back("咸池");
  zhi_to_shensha_map[ri_hua_gai].push_back("华盖");
  zhi_to_shensha_map[ri_jie_sha].push_back("日劫煞");
  zhi_to_shensha_map[ri_zai_sha].push_back("灾煞");
  zhi_to_shensha_map[ri_wang_shen].push_back("日亡神");
  zhi_to_shensha_map[ri_po_sui].push_back("日破碎");
  zhi_to_shensha_map[zei_fu].push_back("贼神");
  zhi_to_shensha_map[si_shen].push_back("死神");
  zhi_to_shensha_map[zhi_de].push_back("支德");
  zhi_to_shensha_map[yang_ren].push_back("阳刃");
}

// 获取某个地支上的神煞列表
std::vector<std::string> ShenShaResult::get_shensha_on_zhi(DiZhi zhi) const {
  auto it = zhi_to_shensha_map.find(zhi);
  if (it != zhi_to_shensha_map.end()) {
    return it->second;
  }
  return {}; // 返回空列表
}

// ==================== 输出函数 ====================

std::string ShenShaResult::to_string() const {
  std::string result = "【神煞】\n";

  // 基础神煞
  result += "基础神煞:\n";
  result += fmt::format("  太岁: {}\n", Mapper::to_zh(tai_sui));
  result += fmt::format("  月建: {}\n", Mapper::to_zh(yue_jian));
  result += fmt::format("  日建: {}\n", Mapper::to_zh(ri_jian));
  result += fmt::format("  月破: {}\n", Mapper::to_zh(yue_po));
  result += fmt::format("  日破: {}\n", Mapper::to_zh(ri_po));

  // 年神煞
  result += "年神煞:\n";
  result += fmt::format("  年驿马: {}\n", Mapper::to_zh(nian_yi_ma));
  result += fmt::format("  大耗: {}\n", Mapper::to_zh(da_hao));
  result += fmt::format("  小耗: {}\n", Mapper::to_zh(xiao_hao));
  result += fmt::format("  病符: {}\n", Mapper::to_zh(bing_fu));
  result += fmt::format("  岁德: {}\n", Mapper::to_zh(sui_de));
  result += fmt::format("  岁刑: {}\n", Mapper::to_zh(sui_xing));
  result += fmt::format("  岁合: {}\n", Mapper::to_zh(sui_he));
  result += fmt::format("  劫煞: {}\n", Mapper::to_zh(jie_sha));
  result += fmt::format("  飞廉: {}\n", Mapper::to_zh(fei_lian));
  result += fmt::format("  丧门: {}\n", Mapper::to_zh(sang_men));
  result += fmt::format("  吊客: {}\n", Mapper::to_zh(diao_ke));
  result += fmt::format("  亡神: {}\n", Mapper::to_zh(wang_shen));
  result += fmt::format("  死符: {}\n", Mapper::to_zh(si_fu));
  result += fmt::format("  红鸾: {}\n", Mapper::to_zh(hong_luan));

  // 月神煞
  result += "月神煞:\n";
  result += fmt::format("  月驿马: {}\n", Mapper::to_zh(yue_yi_ma));
  result += fmt::format("  皇书: {}\n", Mapper::to_zh(huang_shu));
  result += fmt::format("  皇恩: {}\n", Mapper::to_zh(huang_en));
  result += fmt::format("  天诏: {}\n", Mapper::to_zh(tian_zhao));
  result += fmt::format("  飞魂: {}\n", Mapper::to_zh(fei_hun));
  result += fmt::format("  天喜: {}\n", Mapper::to_zh(tian_xi));
  result += fmt::format("  生气: {}\n", Mapper::to_zh(sheng_qi));
  result += fmt::format("  天马: {}\n", Mapper::to_zh(tian_ma));
  result += fmt::format("  成神: {}\n", Mapper::to_zh(cheng_shen));
  result += fmt::format("  三丘: {}\n", Mapper::to_zh(san_qiu));
  result += fmt::format("  五墓: {}\n", Mapper::to_zh(wu_mu));
  result += fmt::format("  死气: {}\n", Mapper::to_zh(si_qi));
  result += fmt::format("  谩语: {}\n", Mapper::to_zh(man_yu));
  result += fmt::format("  孤辰: {}\n", Mapper::to_zh(gu_chen));
  result += fmt::format("  寡宿: {}\n", Mapper::to_zh(gua_su));
  result += fmt::format("  天医: {}\n", Mapper::to_zh(tian_yi));
  result += fmt::format("  地医: {}\n", Mapper::to_zh(di_yi));
  result += fmt::format("  天巫: {}\n", Mapper::to_zh(tian_wu));
  result += fmt::format("  解神: {}\n", Mapper::to_zh(jie_shen));
  result += fmt::format("  破碎: {}\n", Mapper::to_zh(po_sui));
  result += fmt::format("  月厌: {}\n", Mapper::to_zh(yue_yan));
  result += fmt::format("  血支: {}\n", Mapper::to_zh(xue_zhi));
  result += fmt::format("  血忌: {}\n", Mapper::to_zh(xue_ji));
  result += fmt::format("  丧车: {}\n", Mapper::to_zh(sang_che));
  result += fmt::format("  信神: {}\n", Mapper::to_zh(xin_shen));
  result += fmt::format("  天鸡: {}\n", Mapper::to_zh(tian_ji));
  result += fmt::format("  丧魂: {}\n", Mapper::to_zh(sang_hun));
  result += fmt::format("  天鬼: {}\n", Mapper::to_zh(tian_gui));
  result += fmt::format("  大时: {}\n", Mapper::to_zh(da_shi));
  result += fmt::format("  小时: {}\n", Mapper::to_zh(xiao_shi));
  result += fmt::format("  月合: {}\n", Mapper::to_zh(yue_he));
  result += fmt::format("  月刑: {}\n", Mapper::to_zh(yue_xing));
  result += fmt::format("  火鬼: {}\n", Mapper::to_zh(huo_gui));
  result += fmt::format("  天目: {}\n", Mapper::to_zh(tian_mu));
  result += fmt::format("  奸神: {}\n", Mapper::to_zh(jian_shen));
  result += fmt::format("  奸门: {}\n", Mapper::to_zh(jian_men));
  result += fmt::format("  钥神: {}\n", Mapper::to_zh(yao_shen));
  result += fmt::format("  飞祸: {}\n", Mapper::to_zh(fei_huo));

  // 日神煞
  result += "日神煞:\n";
  result += fmt::format("  日驿马: {}\n", Mapper::to_zh(ri_yi_ma));
  result += fmt::format("  旬奇: {}\n", Mapper::to_zh(xun_qi));
  result += fmt::format("  日奇: {}\n", Mapper::to_zh(ri_qi));
  result += fmt::format("  旬仪: {}\n", Mapper::to_zh(xun_yi));
  result += fmt::format("  支仪: {}\n", Mapper::to_zh(zhi_yi));
  result += fmt::format("  日德: {}\n", Mapper::to_zh(ri_de));
  result += fmt::format("  日禄: {}\n", Mapper::to_zh(ri_lu));
  result += fmt::format("  旬丁: {}\n", Mapper::to_zh(xun_ding));
  result += fmt::format("  桃花: {}\n", Mapper::to_zh(tao_hua));
  result += fmt::format("  咸池: {}\n", Mapper::to_zh(xian_chi));
  result += fmt::format("  华盖: {}\n", Mapper::to_zh(ri_hua_gai));
  result += fmt::format("  劫煞: {}\n", Mapper::to_zh(ri_jie_sha));
  result += fmt::format("  灾煞: {}\n", Mapper::to_zh(ri_zai_sha));
  result += fmt::format("  亡神: {}\n", Mapper::to_zh(ri_wang_shen));
  result += fmt::format("  破碎: {}\n", Mapper::to_zh(ri_po_sui));
  result += fmt::format("  贼神: {}\n", Mapper::to_zh(zei_fu));
  result += fmt::format("  死神: {}\n", Mapper::to_zh(si_shen));
  result += fmt::format("  支德: {}\n", Mapper::to_zh(zhi_de));
  result += fmt::format("  阳刃: {}\n", Mapper::to_zh(yang_ren));

  // 地支神煞映射
  result += "\n各地支神煞:\n";
  for (int i = 0; i < 12; ++i) {
    DiZhi zhi = static_cast<DiZhi>(i);
    auto shensha_list = get_shensha_on_zhi(zhi);
    if (!shensha_list.empty()) {
      result += fmt::format("  {}: {}\n", Mapper::to_zh(zhi),
                            fmt::join(shensha_list, "、"));
    }
  }

  return result;
}

nlohmann::json ShenShaResult::to_json() const {
  nlohmann::json j;

  // 基础神煞
  nlohmann::json jichu;
  jichu["太岁"] = std::string(Mapper::to_zh(tai_sui));
  jichu["月建"] = std::string(Mapper::to_zh(yue_jian));
  jichu["日建"] = std::string(Mapper::to_zh(ri_jian));
  jichu["月破"] = std::string(Mapper::to_zh(yue_po));
  jichu["日破"] = std::string(Mapper::to_zh(ri_po));
  j["基础"] = jichu;

  // 年神煞
  nlohmann::json nian;
  nian["驿马"] = std::string(Mapper::to_zh(nian_yi_ma));
  nian["大耗"] = std::string(Mapper::to_zh(da_hao));
  nian["小耗"] = std::string(Mapper::to_zh(xiao_hao));
  nian["病符"] = std::string(Mapper::to_zh(bing_fu));
  nian["岁德"] = std::string(Mapper::to_zh(sui_de));
  nian["岁刑"] = std::string(Mapper::to_zh(sui_xing));
  nian["岁合"] = std::string(Mapper::to_zh(sui_he));
  nian["劫煞"] = std::string(Mapper::to_zh(jie_sha));
  nian["飞廉"] = std::string(Mapper::to_zh(fei_lian));
  nian["丧门"] = std::string(Mapper::to_zh(sang_men));
  nian["吊客"] = std::string(Mapper::to_zh(diao_ke));
  nian["亡神"] = std::string(Mapper::to_zh(wang_shen));
  nian["死符"] = std::string(Mapper::to_zh(si_fu));
  nian["红鸾"] = std::string(Mapper::to_zh(hong_luan));
  j["年"] = nian;

  // 月神煞
  nlohmann::json yue;
  yue["驿马"] = std::string(Mapper::to_zh(yue_yi_ma));
  yue["皇书"] = std::string(Mapper::to_zh(huang_shu));
  yue["皇恩"] = std::string(Mapper::to_zh(huang_en));
  yue["天诏"] = std::string(Mapper::to_zh(tian_zhao));
  yue["飞魂"] = std::string(Mapper::to_zh(fei_hun));
  yue["天喜"] = std::string(Mapper::to_zh(tian_xi));
  yue["生气"] = std::string(Mapper::to_zh(sheng_qi));
  yue["天马"] = std::string(Mapper::to_zh(tian_ma));
  yue["成神"] = std::string(Mapper::to_zh(cheng_shen));
  yue["三丘"] = std::string(Mapper::to_zh(san_qiu));
  yue["五墓"] = std::string(Mapper::to_zh(wu_mu));
  yue["死气"] = std::string(Mapper::to_zh(si_qi));
  yue["谩语"] = std::string(Mapper::to_zh(man_yu));
  yue["孤辰"] = std::string(Mapper::to_zh(gu_chen));
  yue["寡宿"] = std::string(Mapper::to_zh(gua_su));
  yue["天医"] = std::string(Mapper::to_zh(tian_yi));
  yue["地医"] = std::string(Mapper::to_zh(di_yi));
  yue["天巫"] = std::string(Mapper::to_zh(tian_wu));
  yue["解神"] = std::string(Mapper::to_zh(jie_shen));
  yue["破碎"] = std::string(Mapper::to_zh(po_sui));
  yue["月厌"] = std::string(Mapper::to_zh(yue_yan));
  yue["血支"] = std::string(Mapper::to_zh(xue_zhi));
  yue["血忌"] = std::string(Mapper::to_zh(xue_ji));
  yue["丧车"] = std::string(Mapper::to_zh(sang_che));
  yue["信神"] = std::string(Mapper::to_zh(xin_shen));
  yue["天鸡"] = std::string(Mapper::to_zh(tian_ji));
  yue["丧魂"] = std::string(Mapper::to_zh(sang_hun));
  yue["天鬼"] = std::string(Mapper::to_zh(tian_gui));
  yue["大时"] = std::string(Mapper::to_zh(da_shi));
  yue["小时"] = std::string(Mapper::to_zh(xiao_shi));
  yue["月合"] = std::string(Mapper::to_zh(yue_he));
  yue["月刑"] = std::string(Mapper::to_zh(yue_xing));
  yue["火鬼"] = std::string(Mapper::to_zh(huo_gui));
  yue["天目"] = std::string(Mapper::to_zh(tian_mu));
  yue["奸神"] = std::string(Mapper::to_zh(jian_shen));
  yue["奸门"] = std::string(Mapper::to_zh(jian_men));
  yue["钥神"] = std::string(Mapper::to_zh(yao_shen));
  yue["飞祸"] = std::string(Mapper::to_zh(fei_huo));
  j["月"] = yue;

  // 日神煞
  nlohmann::json ri;
  ri["驿马"] = std::string(Mapper::to_zh(ri_yi_ma));
  ri["旬奇"] = std::string(Mapper::to_zh(xun_qi));
  ri["日奇"] = std::string(Mapper::to_zh(ri_qi));
  ri["旬仪"] = std::string(Mapper::to_zh(xun_yi));
  ri["支仪"] = std::string(Mapper::to_zh(zhi_yi));
  ri["日德"] = std::string(Mapper::to_zh(ri_de));
  ri["日禄"] = std::string(Mapper::to_zh(ri_lu));
  ri["旬丁"] = std::string(Mapper::to_zh(xun_ding));
  ri["桃花"] = std::string(Mapper::to_zh(tao_hua));
  ri["咸池"] = std::string(Mapper::to_zh(xian_chi));
  ri["华盖"] = std::string(Mapper::to_zh(ri_hua_gai));
  ri["劫煞"] = std::string(Mapper::to_zh(ri_jie_sha));
  ri["灾煞"] = std::string(Mapper::to_zh(ri_zai_sha));
  ri["亡神"] = std::string(Mapper::to_zh(ri_wang_shen));
  ri["破碎"] = std::string(Mapper::to_zh(ri_po_sui));
  ri["贼神"] = std::string(Mapper::to_zh(zei_fu));
  ri["死神"] = std::string(Mapper::to_zh(si_shen));
  ri["支德"] = std::string(Mapper::to_zh(zhi_de));
  ri["阳刃"] = std::string(Mapper::to_zh(yang_ren));
  j["日"] = ri;

  // 地支神煞映射
  nlohmann::json zhi_map;
  for (int i = 0; i < 12; ++i) {
    DiZhi zhi = static_cast<DiZhi>(i);
    auto shensha_list = get_shensha_on_zhi(zhi);
    if (!shensha_list.empty()) {
      zhi_map[std::string(Mapper::to_zh(zhi))] = shensha_list;
    }
  }
  j["地支神煞"] = zhi_map;

  return j;
}

// ==================== 年神煞（补充实现） ====================

/**
 * 岁德：按年干取岁德所在之支
 */
DiZhi ShenShaEngine::calc_sui_de() const {
  return kRiDeByGan[gan_index(ba_zi_.year.gan)];
}

/**
 * 岁刑：年支的三刑
 */
DiZhi ShenShaEngine::calc_sui_xing() const {
  return kXingByZhi[zhi_index(ba_zi_.year.zhi)];
}

/**
 * 岁合：年支的六合
 */
DiZhi ShenShaEngine::calc_sui_he() const {
  return kSuiHeByGan[gan_index(ba_zi_.year.gan)];
}

/**
 * 劫煞：年支逆数三位
 */
DiZhi ShenShaEngine::calc_jie_sha() const {
  return kJieShaByZhi[zhi_index(ba_zi_.year.zhi)];
}

/**
 * 飞廉：年支逆数五位
 */
DiZhi ShenShaEngine::calc_fei_lian() const { return ba_zi_.year.zhi + 8; }

/**
 * 丧门：年支顺数三位
 */
DiZhi ShenShaEngine::calc_sang_men() const { return ba_zi_.year.zhi + 2; }

/**
 * 吊客：年支逆数三位
 */
DiZhi ShenShaEngine::calc_diao_ke() const { return ba_zi_.year.zhi + (-2); }

/**
 * 亡神：年支找孟位逆数两位
 */
DiZhi ShenShaEngine::calc_wang_shen() const {
  return kWangShenByZhi[zhi_index(ba_zi_.year.zhi)];
}

// ==================== 月神煞（补充实现） ====================

/**
 * 月合：月建的六合
 */
DiZhi ShenShaEngine::calc_yue_he() const {
  DiZhi month_zhi = ba_zi_.month.zhi;
  static const std::map<DiZhi, DiZhi> he_map = {
      {DiZhi::Zi, DiZhi::Chou},  {DiZhi::Chou, DiZhi::Zi},
      {DiZhi::Yin, DiZhi::Hai},  {DiZhi::Hai, DiZhi::Yin},
      {DiZhi::Mao, DiZhi::Xu},   {DiZhi::Xu, DiZhi::Mao},
      {DiZhi::Chen, DiZhi::You}, {DiZhi::You, DiZhi::Chen},
      {DiZhi::Si, DiZhi::Shen},  {DiZhi::Shen, DiZhi::Si},
      {DiZhi::Wu, DiZhi::Wei},   {DiZhi::Wei, DiZhi::Wu}};
  auto it = he_map.find(month_zhi);
  if (it != he_map.end()) {
    return it->second;
  }
  return DiZhi::Zi;
}

/**
 * 月刑：月建的三刑
 */
DiZhi ShenShaEngine::calc_yue_xing() const {
  return kXingByZhi[zhi_index(ba_zi_.month.zhi)];
}

/**
 * 火鬼：春巳、夏申、秋亥、冬寅
 */
DiZhi ShenShaEngine::calc_huo_gui() const {
  static constexpr std::array table = {DiZhi::Wu, DiZhi::You, DiZhi::Zi,
                                       DiZhi::Mao};
  return table[season_index(ba_zi_.month.zhi)];
}

/**
 * 天目：月建顺数四位
 */
DiZhi ShenShaEngine::calc_tian_mu() const {
  static constexpr std::array table = {DiZhi::Chen, DiZhi::Wei, DiZhi::Xu,
                                       DiZhi::Chou};
  return table[season_index(ba_zi_.month.zhi)];
}

/**
 * 奸神：月建前三位
 * 用途：主奸诈、暗昧、阴私之事。为凶神，主有隐秘、欺诈、不光明之事。
 */
DiZhi ShenShaEngine::calc_jian_shen() const {
  static constexpr std::array table = {DiZhi::Yin, DiZhi::Hai, DiZhi::Shen,
                                       DiZhi::Si};
  return table[season_index(ba_zi_.month.zhi)];
}

/**
 * 奸门：月建后三位
 * 用途：同奸神，主暗昧奸私。为凶神，主有隐晦、不正之门户。
 */
DiZhi ShenShaEngine::calc_jian_men() const {
  static constexpr ZhiTable table = {
      DiZhi::Shen, DiZhi::Hai, DiZhi::Yin,  DiZhi::Si,  DiZhi::Shen, DiZhi::Hai,
      DiZhi::Yin,  DiZhi::Si,  DiZhi::Shen, DiZhi::Hai, DiZhi::Yin,  DiZhi::Si};
  return by_month(ba_zi_.month.zhi, table);
}

/**
 * 钥神：月建顺数五位
 * 用途：主开启、钥匙、要津、关键。为吉神，主有开启之机，得关键之助。
 */
DiZhi ShenShaEngine::calc_yao_shen() const {
  static constexpr std::array table = {DiZhi::Si, DiZhi::Shen, DiZhi::Hai,
                                       DiZhi::Yin};
  return table[season_index(ba_zi_.month.zhi)];
}

/**
 * 飞祸：年支逆数四位
 * 用途：主飞来横祸、意外灾祸。为凶神，主有突发、意外之祸患。
 */
DiZhi ShenShaEngine::calc_fei_huo() const {
  static constexpr std::array table = {DiZhi::Shen, DiZhi::Yin, DiZhi::Si,
                                       DiZhi::Hai};
  return table[season_index(ba_zi_.month.zhi)];
}

/**
 * 红鸾：子年起卯，按年支逆行十二宫。
 */
DiZhi ShenShaEngine::calc_hong_luan() const {
  static constexpr ZhiTable table = {
      DiZhi::Mao, DiZhi::Yin,  DiZhi::Chou, DiZhi::Zi, DiZhi::Hai, DiZhi::Xu,
      DiZhi::You, DiZhi::Shen, DiZhi::Wei,  DiZhi::Wu, DiZhi::Si,  DiZhi::Chen};
  return table[zhi_index(ba_zi_.year.zhi)];
}

// ==================== 日神煞（补充实现） ====================

/**
 * 日德：甲戊庚牛羊，乙己鼠猴乡，丙丁猪鸡位，壬癸兔蛇藏，六辛逢马虎
 * 返回符合日德条件的地支
 */
DiZhi ShenShaEngine::calc_ri_de() const {
  return kRiDeByGan[gan_index(ba_zi_.day.gan)];
}

/**
 * 日禄：甲禄寅、乙禄卯、丙戊禄巳、丁己禄午、庚禄申、辛禄酉、壬禄亥、癸禄子
 * 返回符合日禄条件的地支
 */
DiZhi ShenShaEngine::calc_ri_lu() const {
  return kRiLuByGan[gan_index(ba_zi_.day.gan)];
}

/**
 * 旬丁：空亡后第二位
 */
DiZhi ShenShaEngine::calc_xun_ding() const {
  auto kong_wang = get_kong_wang(ba_zi_.day.gan, ba_zi_.day.zhi);
  return kong_wang[1] + 4;
}

/**
 * 成神：正月起巳，按巳、申、亥、寅循环
 */
DiZhi ShenShaEngine::calc_cheng_shen() const {
  static constexpr ZhiTable table = {
      DiZhi::Si,  DiZhi::Shen, DiZhi::Hai, DiZhi::Yin,  DiZhi::Si,  DiZhi::Shen,
      DiZhi::Hai, DiZhi::Yin,  DiZhi::Si,  DiZhi::Shen, DiZhi::Hai, DiZhi::Yin};
  return by_month(ba_zi_.month.zhi, table);
}

/**
 * 桃花：申子辰在酉，寅午戌在卯，巳酉丑在午，亥卯未在子
 */
DiZhi ShenShaEngine::calc_tao_hua() const {
  return kTaoHuaByZhi[zhi_index(ba_zi_.day.zhi)];
}

/**
 * 咸池：同桃花
 */
DiZhi ShenShaEngine::calc_xian_chi() const { return calc_tao_hua(); }

/**
 * 贼符：日支找孟位顺数七位
 */
DiZhi ShenShaEngine::calc_zei_fu() const { return calc_xun_yi() + 1; }

/**
 * 死符：岁后五辰
 */
DiZhi ShenShaEngine::calc_si_fu() const { return ba_zi_.year.zhi + 5; }

/**
 * 死神：日支找孟位逆数一位
 */
DiZhi ShenShaEngine::calc_si_shen() const {
  return kSiShenByZhi[zhi_index(ba_zi_.day.zhi)];
}

/**
 * 支德：日支顺数五位
 */
DiZhi ShenShaEngine::calc_zhi_de() const { return ba_zi_.day.zhi + 5; }

/**
 * 阳刃：甲卯、乙辰、丙午、丁未、戊午、己未、庚酉、辛戌、壬子、癸丑
 */
DiZhi ShenShaEngine::calc_yang_ren() const {
  return kYangRenByGan[gan_index(ba_zi_.day.gan)];
}

/**
 * 解神：正二月申、三四月戌，依双月顺行
 */
DiZhi ShenShaEngine::calc_jie_shen() const {
  static constexpr ZhiTable table = {
      DiZhi::Shen, DiZhi::Shen, DiZhi::Xu,   DiZhi::Xu,   DiZhi::Zi, DiZhi::Zi,
      DiZhi::Yin,  DiZhi::Yin,  DiZhi::Chen, DiZhi::Chen, DiZhi::Wu, DiZhi::Wu};
  return by_month(ba_zi_.month.zhi, table);
}

} // namespace ZhouYi::DaLiuRen::ShenSha
