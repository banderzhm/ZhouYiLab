// 干支系统模块 - 天干地支核心功能
// 整合自旧代码，提供完整的天干地支功能

export module ZhouYi.GanZhi;

// 导入反射库
import magic_enum;

// 导入标准库（最后）
import std;

/**
 * @brief 干支系统命名空间
 *
 * 提供天干地支的核心定义和相关功能
 */
export namespace ZhouYi::GanZhi {

// ==================== 枚举定义 ====================

/**
 * @brief 天干枚举
 */
enum class TianGan {
  Jia = 0, // 甲
  Yi,      // 乙
  Bing,    // 丙
  Ding,    // 丁
  Wu,      // 戊
  Ji,      // 己
  Geng,    // 庚
  Xin,     // 辛
  Ren,     // 壬
  Gui      // 癸
};

/**
 * @brief 地支枚举
 */
enum class DiZhi {
  Zi = 0, // 子
  Chou,   // 丑
  Yin,    // 寅
  Mao,    // 卯
  Chen,   // 辰
  Si,     // 巳
  Wu,     // 午
  Wei,    // 未
  Shen,   // 申
  You,    // 酉
  Xu,     // 戌
  Hai     // 亥
};

/**
 * @brief 五行枚举
 */
enum class WuXing {
  Mu = 1,  // 木
  Huo = 2, // 火
  Tu = 3,  // 土
  Jin = 4, // 金
  Shui = 5 // 水
};

/**
 * @brief 阴阳枚举
 */
enum class YinYang {
  Yin = 0, // 阴
  Yang = 1 // 阳
};

// ==================== 运算符重载 ====================

/**
 * @brief 天干加法运算
 */
TianGan operator+(TianGan gan, int num);

/**
 * @brief 地支加法运算
 */
DiZhi operator+(DiZhi zhi, int num);

/**
 * @brief 地支减法运算（获取相距数）
 */
int operator-(DiZhi left, DiZhi right);

/**
 * @brief 地支前置自增
 */
DiZhi &operator++(DiZhi &zhi);

/**
 * @brief 地支前置自减
 */
DiZhi &operator--(DiZhi &zhi);

// ==================== 中文名称映射 ====================

namespace Mapper {
// 天干中文名称
auto to_zh(TianGan gan) -> std::string_view;

// 地支中文名称
auto to_zh(DiZhi zhi) -> std::string_view;

// 五行中文名称
auto to_zh(WuXing wx) -> std::string_view;

// 阴阳中文名称
auto to_zh(YinYang yy) -> std::string_view;

// 生肖名称
auto sheng_xiao_zh(DiZhi zhi) -> std::string_view;

// 从中文查找天干
auto from_zh_gan(std::string_view zh_name) -> std::optional<TianGan>;

// 从中文查找地支
auto from_zh_zhi(std::string_view zh_name) -> std::optional<DiZhi>;
} // namespace Mapper

// ==================== 五行属性 ====================

/**
 * @brief 获取天干五行
 */
WuXing get_wu_xing(TianGan gan);

/**
 * @brief 获取地支五行
 */
WuXing get_wu_xing(DiZhi zhi);

// ==================== 阴阳属性 ====================

/**
 * @brief 获取天干阴阳
 */
YinYang get_yin_yang(TianGan gan);

/**
 * @brief 获取地支阴阳
 */
YinYang get_yin_yang(DiZhi zhi);

// ==================== 五行生克关系 ====================

/**
 * @brief 判断五行相生（x生y）
 *
 * 五行相生规律：
 * - 木生火：木材燃烧生火，火赖木生
 * - 火生土：火焚木成灰土，土赖火生
 * - 土生金：土中蕴藏金矿，金赖土生
 * - 金生水：金属融化成水，水赖金生（又说金寒生水）
 * - 水生木：水滋润树木生长，木赖水生
 *
 * 生我者为母，我生者为子，故称"母子相生"
 *
 * @param x 生者（母）
 * @param y 被生者（子）
 * @return true 如果 x 生 y
 */
bool wu_xing_sheng(WuXing x, WuXing y);

/**
 * @brief 判断五行相克（x克y）
 *
 * 五行相克规律：
 * - 木克土：树木扎根于土，木能疏土（木胜土）
 * - 土克水：土能防水、吸水，水来土掩（土胜水）
 * - 水克火：水能灭火，水火不容（水胜火）
 * - 火克金：火能熔金，烈火炼真金（火胜金）
 * - 金克木：金属可以砍伐树木，金刚克木（金胜木）
 *
 * 克我者为所不胜，我克者为所胜，故称"克制相胜"
 *
 * @param x 克者（所胜）
 * @param y 被克者（所不胜）
 * @return true 如果 x 克 y
 */
bool wu_xing_ke(WuXing x, WuXing y);

// ==================== 地支关系 ====================

/**
 * @brief 判断地支相冲（六冲）
 *
 * 地支六冲，又称"六位相冲"，是地支相距六位的对冲关系。
 * 相冲代表对立、冲突、动荡、变化，主破坏之力。
 *
 * 六冲对照：
 * - 子午冲：水火相冲（子水 vs 午火），北方与南方相冲
 * - 丑未冲：土土相冲（丑土 vs 未土），东北与西南相冲
 * - 寅申冲：木金相冲（寅木 vs 申金），东北与西南相冲
 * - 卯酉冲：木金相冲（卯木 vs 酉金），东方与西方相冲，日出与日落
 * - 辰戌冲：土土相冲（辰土 vs 戌土），东南与西北相冲
 * - 巳亥冲：火水相冲（巳火 vs 亥水），东南与西北相冲
 *
 * 规律：地支相距六位即相冲（180度对冲）
 * 影响：主变动、搬迁、离散、破坏、疾病等
 *
 * @param zhi1 第一个地支
 * @param zhi2 第二个地支
 * @return true 如果两地支相冲
 */
bool is_chong(DiZhi zhi1, DiZhi zhi2);

/**
 * @brief 判断地支相刑
 *
 * 地支相刑是地支之间的刑罚关系，代表刑伤、灾祸、官非、病痛。
 * 刑分为四种类型：
 *
 * 1. 无礼之刑（子卯刑）：
 *    - 子刑卯、卯刑子
 *    - 子为水，卯为木，水生木，本为相生，但因过度则为刑
 *    - 主无礼、淫乱、犯上、礼教败坏
 *    - 子卯为桃花，相刑主感情纠葛、桃色事件
 *
 * 2. 无恩之刑（寅巳申三刑）：
 *    - 寅刑巳、巳刑申、申刑寅（循环相刑）
 *    - 寅木、巳火、申金，三者既相生又相克，恩中藏害
 *    - 主忘恩负义、恩将仇报、背信弃义
 *    - 又称"持势之刑"，刑罚最重
 *
 * 3. 恃势之刑（丑戌未三刑）：
 *    - 丑刑戌、戌刑未、未刑丑（循环相刑）
 *    - 三者皆为土，土旺则相刑，主霸道、恃强凌弱
 *    - 主自以为是、仗势欺人、专横跋扈
 *    - 又称"倚势之刑"、"土刑"
 *
 * 4. 自刑（辰辰、午午、酉酉、亥亥）：
 *    - 自己刑自己，即同一地支相见则刑
 *    - 辰辰自刑：辰为天罡，水土相战
 *    - 午午自刑：午为阳火，火过旺则自焚
 *    - 酉酉自刑：酉为金，金刚过盛则自损
 *    - 亥亥自刑：亥为水，水漫过头则自困
 *    - 主自我矛盾、内耗、自残、自寻烦恼
 *
 * 影响：主刑伤、官非、牢狱、疾病、灾祸、是非
 *
 * @param zhi1 第一个地支
 * @param zhi2 第二个地支
 * @return true 如果两地支相刑
 */
bool is_xing(DiZhi zhi1, DiZhi zhi2);

/**
 * @brief 判断地支相合（六合）
 *
 * 地支六合是地支之间的合化关系，代表和谐、亲密、合作、喜庆。
 * 相合主吉祥、团结、婚姻、缘分，力量较三合为弱。
 *
 * 六合对照及合化五行：
 * - 子丑合化土：子水配丑土，阴阳相合，水土相济，北方合（鼠牛合）
 * - 寅亥合化木：寅木配亥水，木得水生，木旺相生，东北合（虎猪合）
 * - 卯戌合化火：卯木配戌土，木火通明，文明之合，东西合（兔狗合）
 * - 辰酉合化金：辰土配酉金，土生金旺，金玉良缘，东南西合（龙鸡合）
 * - 巳申合化水：巳火配申金，火金相融，水火既济，南西合（蛇猴合）
 * - 午未合化土：午火配未土，火土相生，中正之合，南方合（马羊合）
 *
 * 合化条件（一般需要）：
 * 1. 有化神当令（月令临合化之五行）
 * 2. 有化神透干（天干透出合化五行）
 * 3. 无强烈冲刑破害
 *
 * 规律特点：
 * - 阴阳相配：阳支合阴支（子阳丑阴、寅阳亥阴等）
 * - 方位对应：多为东西、南北或对角方位相合
 * - 五行和谐：合化后五行多与双方有生助关系
 *
 * 影响：主婚姻美满、合作愉快、贵人相助、喜庆吉利
 *
 * @param zhi1 第一个地支
 * @param zhi2 第二个地支
 * @return
 * 六合的传统合化五行方向；不相合时为空。返回五行不表示已经合化。

 */
std::optional<WuXing> get_he_wu_xing(DiZhi zhi1, DiZhi zhi2);

/**
 * @brief 判断两地支是否构成六合。
 */
bool is_he(DiZhi zhi1, DiZhi zhi2);

/**
 * @brief 返回天干五合的化气方向。
 * @return
 * 甲己土、乙庚金、丙辛水、丁壬木、戊癸火；不合时为空。

 * *
 * @note
 * 返回化气方向不表示月令、根气等化气条件已经成立。
 */
std::optional<WuXing> stem_combine_element(TianGan first, TianGan second);

/**
 * @brief 判断地支相害（六害）
 *
 * 地支六害，又称"六穿"、"相穿"，是地支之间的破坏关系。
 * 相害是因为破坏了相合关系而产生的，代表暗中伤害、小人陷害。
 * 害比冲、刑的力量弱，但其作用隐蔽，不易察觉，更加阴险。
 *
 * 六害对照及成因：
 * - 子未害：子丑合，未冲丑，故子未相害（穿六合）
 *   子水克未土，未为木库，水土交战，主被人拖累
 *
 * - 丑午害：丑子合，午未合，午冲子，故丑午相害
 *   丑土晦午火，午火伤丑金，主损耗、消磨
 *
 * - 寅巳害：寅亥合，巳申合，寅巳刑，故寅巳相害（无恩之害）
 *   寅木生巳火，巳火反制寅木，主恩中带害、养虎为患
 *
 * - 卯辰害：卯戌合，辰酉合，卯辰相邻，故卯辰相害
 *   卯木克辰土，辰为湿土，主阴湿、暗昧、抑郁
 *
 * - 申亥害：申巳合，亥寅合，申亥相穿，故申亥相害
 *   申金克亥中甲木，亥水生申金中气，主互相伤害
 *
 * - 酉戌害：酉辰合，戌卯合，酉戌相邻，故酉戌相害
 *   酉金克戌中丁火，戌土生酉金，主暗害、背叛
 *
 * 规律特点：
 * - 相害是因破坏六合而成，合中带害
 * - 相害之地支往往与第三方有刑冲合的关系
 * - 六害主小人、暗害、牵连、拖累
 *
 * 影响：主小人陷害、暗箭伤人、拖累牵连、明合暗斗、
 *       感情不和、疾病缠身、小灾小难
 *
 * @param zhi1 第一个地支
 * @param zhi2 第二个地支
 * @return true 如果两地支相害
 */
bool is_hai(DiZhi zhi1, DiZhi zhi2);

/**
 * @brief 判断地支三合
 *
 * 地支三合局是三个地支合化成一个五行的组合，力量最强。
 * 三合局按照五行的"生、旺、墓"三个阶段组成，代表从生到旺到归藏的完整循环。
 *
 * 四组三合局及其组成（生旺墓）：
 *
 * 1. 申子辰合水局：
 *    - 申（长生）：水长生于申，申为水的起源
 *    - 子（帝旺）：子为水之本位，水最旺盛
 *    - 辰（墓库）：辰为水库，水归藏之地
 *    - 方位：西  北  东，跨越三方
 *    - 季节：秋末  冬  春初，水气旺盛之时
 *
 * 2. 亥卯未合木局：
 *    - 亥（长生）：木长生于亥，水生木之始
 *    - 卯（帝旺）：卯为木之本位，木最旺盛
 *    - 未（墓库）：未为木库，木归藏之地
 *    - 方位：北  东  南，顺时针三合
 *    - 季节：冬末  春  夏初，木气生发之时
 *
 * 3. 寅午戌合火局：
 *    - 寅（长生）：火长生于寅，木生火之始
 *    - 午（帝旺）：午为火之本位，火最旺盛
 *    - 戌（墓库）：戌为火库，火归藏之地
 *    - 方位：东  南  西，三合成局
 *    - 季节：春末  夏  秋初，火气炎盛之时
 *
 * 4. 巳酉丑合金局：
 *    - 巳（长生）：金长生于巳，火炼金成
 *    - 酉（帝旺）：酉为金之本位，金最旺盛
 *    - 丑（墓库）：丑为金库，金归藏之地
 *    - 方位：南  西  北，逆时针三合
 *    - 季节：夏末  秋  冬初，金气肃杀之时
 *
 * 规律特点：
 * - 三合局相距四位（120度），形成等边三角形
 * - 生旺墓三者齐全，力量最强，可改变原有五行属性
 * - 即使缺一，只要有两个也有一定的合力
 * - 三合局以"旺"为主导，有旺支则合局有力
 *
 * 合化条件：
 * 1. 三支齐全（完美三合）
 * 2. 月令当旺（月令临合化之五行）
 * 3. 无强烈冲破（冲克旺支或生支）
 * 4. 有化神透干（天干透出合化五行更佳）
 *
 * 影响：主力量强大、成就事业、团结合作、势力庞大、
 *       聚财聚势、贵人相助、婚姻美满（比六合更稳固）
 *
 * @param zhi1 第一个地支
 * @param zhi2 第二个地支
 * @param zhi3 第三个地支
 * @return pair<bool, WuXing> - 是否三合，合化的五行
 */
auto is_san_he(DiZhi zhi1, DiZhi zhi2, DiZhi zhi3) -> std::pair<bool, WuXing>;

/** 三合半局中两支所处的生、旺、墓阶段组合。 */
enum class SanHeHalfKind {
  ShengWang, ///< 生支与旺支相见，通常为较有力的生旺半合。
  WangMu,    ///< 旺支与墓支相见，通常为旺墓半合。
  ShengMu,   ///< 生支与墓支相见，无旺支，仅作拱合候选。
};

/** 地支三合半局的基础判定结果。 */
struct SanHeHalfResult {
  WuXing element;       ///< 三合体系所归属的五行。
  SanHeHalfKind kind;   ///< 生旺、旺墓或生墓组合类型。
  DiZhi missing_branch; ///< 补齐完整三合局所缺的地支。
};

/**
 * @brief 判断两地支是否属于同一组三合体系
 *
 *
 * 本函数只返回半合或拱合的干支事实，不直接认定合化，也不判断喜忌。
 *

 * *
 * @return
 * 成立时返回五行、组合类型和所缺地支，否则返回空值

 */
auto get_san_he_half(DiZhi zhi1, DiZhi zhi2) -> std::optional<SanHeHalfResult>;

/**
 * @brief 判断地支三会方局。
 * @return pair<bool,
 * WuXing>：是否三会以及三会五行。
 */
auto is_san_hui(DiZhi zhi1, DiZhi zhi2, DiZhi zhi3) -> std::pair<bool, WuXing>;

// ==================== 天干寄宫 ====================

/**
 * @brief 获取天干寄宫地支
 *
 * 大六壬十天干寄宫口诀：
 *   甲课寅兮乙课辰，
 *   丙戊课巳不须论，
 *   丁己课未庚申上，
 *   辛戌壬亥是其真，
 *   癸课原来丑宫坐，
 *   分明不用四正神。
 *
 * 规则：
 *   1. 阳干（甲丙戊庚壬）寄禄位，如甲禄在寅
 *   2. 阴干（乙丁己辛癸）寄冠带位，如乙冠带在辰
 *   3. 丙戊同寄巳，丁己同寄未
 *   4. 子午卯酉四正位不参与寄宫
 */
DiZhi get_ji_gong(TianGan gan);

/**
 * @brief 获取地支中寄居的天干
 */
auto get_ji_gan(DiZhi zhi) -> std::vector<TianGan>;

// ==================== 贵人表 ====================

/**
 * @brief 获取天干贵人地支
 * @param is_day true为阳贵人（昼），false为阴贵人（夜）
 */
DiZhi get_gui_ren(TianGan gan, bool is_day);

/**
 * @brief 判断是否为白天（卯时到申时）
 */
bool is_daytime(DiZhi hour);

// ==================== 地支藏干 ====================

/**
 * @brief 获取地支藏干（主气、中气、余气）
 * @return
 * std::vector 包含藏干的向量
 */
auto get_cang_gan(DiZhi zhi) -> std::vector<TianGan>;

// ==================== 月令人元司令 ====================

/**
 * @brief 人元司令的单段分日规则
 *
 *
 * 描述某个月支内，一位藏干连续主事的日数。
 */
struct HumanCommandSegment {
  TianGan stem; ///< 该时段司令的月令藏干。
  int days;     ///< 该藏干连续司令的日数。
};

/**
 * @brief 获取指定月支的人元司令分日规则
 *
 *
 * 返回顺序即节后依次主事的藏干顺序。本函数只提供干支基础规则，

 * * 不负责计算出生时刻距节的日数。
 *
 * @param month 月支
 *
 * @return 该月支的人元司令分段只读视图
 */
auto get_human_command_segments(DiZhi month)
    -> std::span<const HumanCommandSegment>;

// ==================== 六十甲子 ====================

/**
 * @brief 六十甲子类
 */
class LiuShiJiaZi {
public:
  TianGan gan;
  DiZhi zhi;

  LiuShiJiaZi(TianGan g, DiZhi z);

  /**
   * @brief 从索引创建（0-59）
   */
  static LiuShiJiaZi from_index(int index);

  /**
   * @brief 获取索引（0-59）
   */
  int to_index() const;

  /**
   * @brief 获取中文名称
   */
  std::string to_string() const;

  /**
   * @brief 获取纳音五行
   */
  WuXing get_na_yin() const;
};

/**
 * @brief 获取完整的六十甲子表
 */
auto get_liu_shi_jia_zi() -> std::vector<LiuShiJiaZi>;

// ==================== 十二长生 ====================

/**
 * @brief 十二长生枚举
 *
 * 表示天干在十二地支中的生命状态
 * 用于判断五行的旺衰
 */
enum class ShiErChangSheng {
  ChangSheng = 0, // 长生 - 如婴儿出生，开始生长
  MuYu = 1,       // 沐浴 - 如婴儿沐浴，易受侵害
  GuanDai = 2,    // 冠带 - 如成人加冠，渐趋成熟
  LinGuan = 3,    // 临官 - 如人临官得位，兴旺发达
  DiWang = 4,     // 帝旺 - 如人壮盛，达到顶峰
  Shuai = 5,      // 衰 - 如人衰老，开始衰退
  Bing = 6,       // 病 - 如人患病，气力衰弱
  Si = 7,         // 死 - 如人死亡，生机断绝
  Mu = 8,         // 墓 - 如人入墓，归于寂静（又称"库"）
  Jue = 9,        // 绝 - 如形体灭绝，无气之地
  Tai = 10,       // 胎 - 如受胎孕育，开始孕育
  Yang = 11       // 养 - 如婴儿养育，生命延续
};

/**
 * @brief 十二长生中文映射命名空间
 */
namespace ShiErChangShengMapper {
/**
 * @brief 转换为中文名称
 */
auto to_zh(ShiErChangSheng cs) -> std::string_view;

/**
 * @brief 从中文名称查找
 */
auto from_zh(std::string_view name) -> std::optional<ShiErChangSheng>;
} // namespace ShiErChangShengMapper

/**
 * @brief 获取天干在指定地支的十二长生状态
 *
 * @param gan 天干
 * @param zhi 地支
 * @return 十二长生状态
 *
 * 规则说明：
 * - 阳干（甲丙戊庚壬）顺行十二长生
 * - 阴干（乙丁己辛癸）逆行十二长生
 *
 * 长生起点：
 * - 甲木长生在亥，乙木长生在午
 * - 丙火长生在寅，丁火长生在酉
 * - 戊土长生在寅，己土长生在酉（土同火）
 * - 庚金长生在巳，辛金长生在子
 * - 壬水长生在申，癸水长生在卯
 *
 * @example
 * auto cs = get_shi_er_chang_sheng(TianGan::Jia, DiZhi::Hai); // 返回
 * ShiErChangSheng::ChangSheng
 */
auto get_shi_er_chang_sheng(TianGan gan, DiZhi zhi) -> ShiErChangSheng;

/**
 * @brief 判断天干在指定地支是否为长生状态
 */
bool is_chang_sheng(TianGan gan, DiZhi zhi);

/**
 * @brief 判断天干在指定地支是否为帝旺状态
 */
bool is_di_wang(TianGan gan, DiZhi zhi);

/**
 * @brief 判断天干在指定地支是否为墓库状态
 */
bool is_mu_ku(TianGan gan, DiZhi zhi);

/**
 * @brief 判断天干在指定地支是否为绝地状态
 */
bool is_jue_di(TianGan gan, DiZhi zhi);

/**
 * @brief 获取天干的长生地支
 *
 * @param gan 天干
 * @return 长生地支
 */
auto get_chang_sheng_zhi(TianGan gan) -> DiZhi;

/**
 * @brief 获取天干的帝旺地支
 *
 * @param gan 天干
 * @return 帝旺地支
 */
auto get_di_wang_zhi(TianGan gan) -> DiZhi;

/**
 * @brief 获取天干的墓库地支
 *
 * @param gan 天干
 * @return 墓库地支
 */
auto get_mu_ku_zhi(TianGan gan) -> DiZhi;

// ==================== 六亲系统 ====================

/**
 * @brief 六亲枚举
 *
 * 用于描述五行之间的六亲关系
 */
enum class LiuQin {
  FuMu,    // 父母（生我者）
  XiongDi, // 兄弟（比和者）
  ZiSun,   // 子孙（我生者）
  QiCai,   // 妻财（我克者）
  GuanGui  // 官鬼（克我者）
};

/**
 * @brief 获取六亲关系
 *
 * @param self_gan 日干（我）
 * @param other_zhi 他支
 * @return 六亲关系
 */
LiuQin get_liu_qin(TianGan self_gan, DiZhi other_zhi);

/**
 * @brief 获取六亲中文名称
 */
std::string_view liu_qin_to_zh(LiuQin lq);

// ==================== 十神系统 ====================

/**
 * @brief 十神枚举
 */
enum class ShiShenKind {
  BiJian,    // 比肩
  JieCai,    // 劫财
  ShiShen,   // 食神
  ShangGuan, // 伤官
  PianCai,   // 偏财
  ZhengCai,  // 正财
  QiSha,     // 七杀
  ZhengGuan, // 正官
  PianYin,   // 偏印
  ZhengYin   // 正印
};

/**
 * @brief 十神枚举的兼容类型名
 *
 * 实际枚举类型使用
 * ShiShenKind，避免“食神”枚举项 ShiShen
 *
 * 与封闭枚举同名而被
 * MSVC 拒绝。保留此别名以兼容既有公开接口。
 */
using ShiShen = ShiShenKind;

/**
 * @brief 获取十神关系
 *
 * @param self_gan 日干（我）
 * @param other_gan 他干
 * @return 十神关系
 */
ShiShen get_shi_shen(TianGan self_gan, TianGan other_gan);

/**
 * @brief 获取十神中文名称
 */
std::string_view shi_shen_to_zh(ShiShen ss);

// ==================== 地支遁干系统 ====================

/**
 * @brief 计算旬首地支
 *
 * @param day_gan 日干
 * @param day_zhi 日支
 * @return 旬首地支
 */
DiZhi get_xun_shou(TianGan day_gan, DiZhi day_zhi);

/**
 * @brief 计算地支遁干
 *
 * @param zhi 地支
 * @param day_gan 日干
 * @param day_zhi 日支
 * @return 遁干，如果空亡则返回 std::nullopt
 */
std::optional<TianGan> get_dun_gan(DiZhi zhi, TianGan day_gan, DiZhi day_zhi);

/**
 * @brief 判断地支是否空亡
 *
 * @param zhi 地支
 * @param day_gan 日干
 * @param day_zhi 日支
 * @return 是否空亡
 */
bool is_kong_wang(DiZhi zhi, TianGan day_gan, DiZhi day_zhi);

/**
 * @brief 获取旬空（空亡）的两个地支
 *
 * @param day_gan 日干
 * @param day_zhi 日支
 * @return 旬空的两个地支
 */
std::array<DiZhi, 2> get_kong_wang(TianGan day_gan, DiZhi day_zhi);

} // namespace ZhouYi::GanZhi
