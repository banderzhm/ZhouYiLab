// 紫微斗数格局判断系统模块（接口）
export module ZhouYi.ZiWei.GeJu;

import std;
import ZhouYi.GanZhi;
import ZhouYi.ZiWei.Constants;
export import ZhouYi.ZiWei.Pattern;
import ZhouYi.ZhMapper;

export namespace ZhouYi::ZiWei {
using namespace std;
using namespace ZhouYi::GanZhi;

/**
 * @brief 格局类型枚举
 */
enum class GeJuType {
  // ========= 富贵格局 =========
  ZiFuTongGong,    // 紫府同宫
  ZiFuChaoYuan,    // 紫府朝垣
  TianFuChaoYuan,  // 天府朝垣
  JunChenQingHui,  // 君臣庆会
  FuXiangChaoYuan, // 府相朝垣

  // 机月同梁格
  JiYueTongLiang, // 机月同梁格
  JiLiangJiaHui,  // 机梁夹会

  // 日月格局
  RiYueBingMing,  // 日月并明（卯酉宫）
  RiZhaoLeiMen,   // 日照雷门（卯宫）
  YueLangTianMen, // 月朗天门（亥宫）
  MingZhuChuHai,  // 明珠出海（酉宫太阴）
  RiYueBoZhao,    // 日月夹照

  // 阳梁格局
  YangLiangChangLu, // 阳梁昌禄格

  // 贪狼格局
  TanWuTongXing, // 贪武同行（丑未宫）
  TanLingJiaHui, // 贪铃夹会
  HuoTanGeJu,    // 火贪格局
  LingTanGeJu,   // 铃贪格局

  // ========= 权贵格局 =========
  SanQiJiaHui,     // 三奇嘉会（禄权科）
  ShuangLuJiaMing, // 双禄夹命
  ShuangLuJiaCai,  // 双禄夹财
  KeQuanLuJia,     // 科权禄夹
  ZuoYouJiaMing,   // 左右夹命/财
  ChangQuJiaMing,  // 昌曲夹命
  ChangQuTongHui,  // 昌曲同会命宫三方四正
  KuiYueJiaMing,   // 魁钺夹命

  // ========= 凶格 =========
  LingChangTuoWu,    // 铃昌陀武（铃星文昌陀罗武曲同宫）
  JiJiTongGong,      // 巨机同宫（辰戌宫）
  JuRiTongGong,      // 巨日同宫（最忌）
  MingXiangLiangJia, // 命无正曜（空宫）
  MaTouDaiJian,      // 马头带箭（午宫擎羊守命）
  LiangJiJiaMing,    // 羊陀夹命
  HuoLingJiaMing,    // 火铃夹命
  KongJieJiaMing,    // 空劫夹命
  YangTuoJiaJi,      // 羊陀夹忌
  SiShaChongMing,    // 四煞冲命

  // ========= 其他重要格局 =========
  LuMaJiaoChiGeJu,   // 禄马交驰
  QuanLuXunFeng,     // 权禄巡逢
  MinggongWuZhuXing, // 命宫无主星

  // ========= 参考实现补齐 =========
  ShaPoLang,
  LianZhenTianXiang,
  WuQuQiSha,
  TianTongTianLiang,
  RiYueTongGong,
  RiYueJiaMing,
  ShiZhongYinYu,
  ZiWeiRuMing,
  HuaLuRuMing,
  HuaJiRuMingQian,
  LianShaYang,
  JuHuoYang,
  ShuangLuChaoYuan,
  LuCunShouMing,
  TianMaRuMing,
  HuaLuRuCai,
  HuaQuanRuGuan,
  HuaKeRuMingShen,
  JiYueTongLiangPartial,
  FuBiTongHui,
  KuiYueTongHui,
  KeQuanShuangHui,
};

/** 格局成立程度。 */
enum class GeJuStatus { Established, Weakened, Broken };

/** 格局综合等级。 */
enum class GeJuLevel { Excellent, Good, Neutral, Caution };

/** 格局成立、增益与破格的分层依据。 */
struct GeJuBasis {
  /** 已满足的必要条件。 */
  vector<string> required;
  /** 已触发的增益条件。 */
  vector<string> bonus;
  /** 已触发的破格或减力条件。 */
  vector<string> breaking;
};

/**
 * @brief 格局信息
 */
struct GeJuInfo {
  /** 稳定的格局类型标识。 */
  GeJuType type;
  /** 面向中文报告的格局名称。 */
  string name;
  /** 格局的命理含义与适用边界。 */
  string description;
  /** 是否归入吉格通道；警示格为 false。 */
  bool is_ji;
  /** 为兼容旧接口保留的相对分值，正式判断应读取等级与格局依据。 */
  int score;
  /** 参与成格的关键星曜中文名。 */
  vector<string> key_stars;
  /** 参与成格的宫位索引。 */
  vector<int> key_gongs;

  /** 成立、减力或破格状态。 */
  GeJuStatus status{GeJuStatus::Established};
  /** 格局等级，不以单一总分代替。 */
  GeJuLevel level{GeJuLevel::Neutral};
  /** 必要、增益与破格依据。 */
  GeJuBasis basis;
  /** 规则所依据的典籍或资料口径。 */
  string source;

  /** 返回适合终端和文本报告阅读的中文摘要。 */
  [[nodiscard]] string to_string() const;
};

/**
 * @brief 双星组合类型
 */
enum class ShuangXingType {
  // 紫微系组合
  ZiWei_TianFu,    // 紫微天府
  ZiWei_TanLang,   // 紫微贪狼
  ZiWei_TianXiang, // 紫微天相
  ZiWei_QiSha,     // 紫微七杀
  ZiWei_PoJun,     // 紫微破军

  // 天府系组合
  TianFu_WuQu,     // 天府武曲
  TianFu_LianZhen, // 天府廉贞

  // 武曲系组合
  WuQu_TianFu,    // 武曲天府
  WuQu_TanLang,   // 武曲贪狼
  WuQu_TianXiang, // 武曲天相
  WuQu_QiSha,     // 武曲七杀
  WuQu_PoJun,     // 武曲破军

  // 太阳系组合
  TaiYang_TaiYin,    // 太阳太阴
  TaiYang_TianLiang, // 太阳天梁
  TaiYang_JuMen,     // 太阳巨门

  // 其他重要组合
  TianTong_TaiYin,    // 天同太阴
  TianTong_TianLiang, // 天同天梁
  TianTong_JuMen,     // 天同巨门
  TianJi_TaiYin,      // 天机太阴
  TianJi_TianLiang,   // 天机天梁
  TianJi_JuMen,       // 天机巨门

  Unknown // 其他组合
};

/**
 * @brief 双星组合信息
 */
struct ShuangXingInfo {
  /** 双星组合的稳定类型标识。 */
  ShuangXingType type;
  /** 第一颗主星的规范中文名。 */
  string xing1_name;
  /** 第二颗主星的规范中文名。 */
  string xing2_name;
  /** 双星同宫的宫位索引。 */
  int gong_index;
  /** 组合性质，如“财官双美”。 */
  string xing_zhi;
  /** 双星组合的命理说明。 */
  string description;

  /** 返回适合文本报告阅读的中文摘要。 */
  [[nodiscard]] string to_string() const;
};

/**
 * @brief 格局判断引擎
 */
class GeJuAnalyzer {
public:
  /**
   * @brief 构造函数
   * @param chart 已建立强类型索引的排盘上下文
   */
  explicit GeJuAnalyzer(PatternChart chart);

  /**
   * @brief 分析所有格局
   */
  [[nodiscard]] vector<GeJuInfo> analyze_all() const;

  /**
   * @brief 分析吉格
   */
  [[nodiscard]] vector<GeJuInfo> analyze_ji_ge() const;

  /**
   * @brief 分析凶格
   */
  [[nodiscard]] vector<GeJuInfo> analyze_xiong_ge() const;

  /**
   * @brief 分析双星组合
   */
  [[nodiscard]] vector<ShuangXingInfo> analyze_shuang_xing() const;

  /**
   * @brief 获取命盘总评分
   */
  [[nodiscard]] int get_total_score() const;

private:
  PatternChart chart_;
  int ming_gong_index_;

  // 富贵格局判断
  optional<GeJuInfo> check_zi_fu_tong_gong() const;
  optional<GeJuInfo> check_zi_fu_chao_yuan() const;
  optional<GeJuInfo> check_tian_fu_chao_yuan() const;
  optional<GeJuInfo> check_jun_chen_qing_hui() const;
  optional<GeJuInfo> check_fu_xiang_chao_yuan() const;
  optional<GeJuInfo> check_ji_yue_tong_liang() const;
  optional<GeJuInfo> check_ji_liang_jia_hui() const;
  optional<GeJuInfo> check_ri_yue_bing_ming() const;
  optional<GeJuInfo> check_ri_zhao_lei_men() const;
  optional<GeJuInfo> check_yue_lang_tian_men() const;
  optional<GeJuInfo> check_ming_zhu_chu_hai() const;
  optional<GeJuInfo> check_ri_yue_bo_zhao() const;
  optional<GeJuInfo> check_yang_liang_chang_lu() const;
  optional<GeJuInfo> check_tan_wu_tong_xing() const;
  optional<GeJuInfo> check_tan_ling_jia_hui() const;
  optional<GeJuInfo> check_huo_tan() const;
  optional<GeJuInfo> check_ling_tan() const;

  // 权贵格局判断
  optional<GeJuInfo> check_san_qi_jia_hui() const;
  optional<GeJuInfo> check_shuang_lu_jia_ming() const;
  optional<GeJuInfo> check_shuang_lu_jia_cai() const;
  optional<GeJuInfo> check_ke_quan_lu_jia() const;
  optional<GeJuInfo> check_zuo_you_jia() const;
  optional<GeJuInfo> check_chang_qu_jia_ming() const;
  optional<GeJuInfo> check_chang_qu_tong_hui() const;
  optional<GeJuInfo> check_kui_yue_jia_ming() const;

  // 凶格判断
  optional<GeJuInfo> check_ling_chang_tuo_wu() const;
  optional<GeJuInfo> check_ji_ji_tong_gong() const;
  optional<GeJuInfo> check_ju_ri_tong_gong() const;
  optional<GeJuInfo> check_ming_xiang_liang_jia() const;
  optional<GeJuInfo> check_ma_tou_dai_jian() const;
  optional<GeJuInfo> check_liang_ji_jia_ming() const;
  optional<GeJuInfo> check_sha_xing_jia_ming() const;
  optional<GeJuInfo> check_kong_jie_jia_ming() const;
  optional<GeJuInfo> check_yang_tuo_jia_ji() const;
  optional<GeJuInfo> check_si_sha_chong_ming() const;

  // 其他重要格局
  optional<GeJuInfo> check_lu_ma_jiao_chi() const;
  optional<GeJuInfo> check_quan_lu_xun_feng() const;
  optional<GeJuInfo> check_ming_gong_wu_zhu_xing() const;

  // 参考实现补齐规则
  optional<GeJuInfo> check_sha_po_lang() const;
  optional<GeJuInfo> check_lian_zhen_tian_xiang() const;
  optional<GeJuInfo> check_wu_qu_qi_sha() const;
  optional<GeJuInfo> check_tian_tong_tian_liang() const;
  optional<GeJuInfo> check_ri_yue_tong_gong() const;
  optional<GeJuInfo> check_ri_yue_jia_ming() const;
  optional<GeJuInfo> check_shi_zhong_yin_yu() const;
  optional<GeJuInfo> check_zi_wei_ru_ming() const;
  optional<GeJuInfo> check_hua_lu_ru_ming() const;
  optional<GeJuInfo> check_shuang_lu_chao_yuan() const;
  optional<GeJuInfo> check_hua_ji_ru_ming_qian() const;
  optional<GeJuInfo> check_lian_sha_yang() const;
  optional<GeJuInfo> check_ju_huo_yang() const;
  optional<GeJuInfo> check_lu_cun_shou_ming() const;
  optional<GeJuInfo> check_tian_ma_ru_ming() const;
  optional<GeJuInfo> check_hua_lu_ru_cai() const;
  optional<GeJuInfo> check_hua_quan_ru_guan() const;
  optional<GeJuInfo> check_hua_ke_ru_ming_shen() const;
  optional<GeJuInfo> check_ji_yue_tong_liang_partial() const;
  optional<GeJuInfo> check_fu_bi_tong_hui() const;
  optional<GeJuInfo> check_kui_yue_tong_hui() const;
  optional<GeJuInfo> check_ke_quan_shuang_hui() const;

  // 辅助函数
  DiZhi get_gong_di_zhi(int gong_index) const;
  bool gong_has_star(int gong_index, const string &star_name) const;
  bool gong_has_all_stars(int gong_index, const vector<string> &stars) const;
  bool gong_has_any_star(int gong_index, const vector<string> &stars) const;
  bool san_fang_has_star(int gong_index, const string &star) const;
  bool san_fang_has_all_stars(int gong_index,
                              const vector<string> &stars) const;
  bool san_fang_has_any_star(int gong_index, const vector<string> &stars) const;
  vector<string> get_zhu_xing_in_gong(int gong_index) const;
};

} // namespace ZhouYi::ZiWei

export namespace ZhouYi::Mapper {

/** 中文映射：格局成立程度。 */
template <> struct ZhMap<ZiWei::GeJuStatus> {
  /** 返回与枚举声明顺序一致的专业中文术语。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 3>{"成立", "减力", "破格"};
  }
};

/** 中文映射：格局综合等级。 */
template <> struct ZhMap<ZiWei::GeJuLevel> {
  /** 返回与枚举声明顺序一致的中文等级。 */
  static constexpr auto get_map() {
    return std::array<std::string_view, 4>{"上格", "佳格", "辅格", "警示"};
  }
};

} // namespace ZhouYi::Mapper
