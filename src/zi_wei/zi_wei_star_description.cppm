// 紫微斗数星耀特性系统模块（接口）
export module ZhouYi.ZiWei.StarDescription;

import std;
import ZhouYi.ZiWei.Constants;
import ZhouYi.ZhMapper;

export namespace ZhouYi::ZiWei {
    using namespace std;

    /**
     * @brief 星耀特性枚举
     */
    enum class XingYaoTeXing {
        TaoHua,      // 桃花星
        CaiXing,     // 财星
        QuanXing,    // 权星
        WenXing,     // 文星
        ShouXing,    // 寿星
        YiMaXing,    // 驿马星
        GuXing,      // 孤星
        JiXing,      // 吉星
        ShaXing,     // 煞星
        ZhuXing,     // 主星（14正曜）
        FuXing,      // 辅星
        ZaYao        // 杂耀
    };

    /**
     * @brief 星耀五行属性
     */
    enum class XingYaoWuXing {
        Jin,         // 金
        Mu,          // 木
        Shui,        // 水
        Huo,         // 火
        Tu           // 土
    };

    /**
     * @brief 星耀阴阳属性
     */
    enum class XingYaoYinYang {
        Yang,        // 阳
        Yin          // 阴
    };

    /**
     * @brief 星耀完整特性信息
     */
    struct XingYaoInfo {
        string name;                        // 星耀名称
        vector<XingYaoTeXing> te_xing_list; // 特性列表
        XingYaoWuXing wu_xing;              // 五行属性
        XingYaoYinYang yin_yang;            // 阴阳属性
        string xing_qing;                   // 星情（性质描述）
        string zhu_shi;                     // 主事（主管事项）
        
        bool has_te_xing(XingYaoTeXing te_xing) const {
            return find(te_xing_list.begin(), te_xing_list.end(), te_xing) != te_xing_list.end();
        }
        
        string to_string() const;
    };

    /**
     * @brief 获取星耀特性信息
     */
    XingYaoInfo get_xing_yao_info(const string& star_name);

    /**
     * @brief 判断是否桃花星
     */
    bool is_tao_hua_xing(const string& star_name);

    /**
     * @brief 判断是否财星
     */
    bool is_cai_xing(const string& star_name);

    /**
     * @brief 判断是否权星
     */
    bool is_quan_xing(const string& star_name);

    /**
     * @brief 判断是否文星
     */
    bool is_wen_xing(const string& star_name);

    /**
     * @brief 判断是否吉星
     */
    bool is_ji_xing(const string& star_name);

    /**
     * @brief 判断是否煞星
     */
    bool is_sha_xing(const string& star_name);

    /**
     * @brief 获取所有桃花星列表
     */
    vector<string> get_all_tao_hua_xing();

    /**
     * @brief 获取所有财星列表
     */
    vector<string> get_all_cai_xing();

    /**
     * @brief 获取所有权星列表
     */
    vector<string> get_all_quan_xing();

    /**
     * @brief 获取所有文星列表
     */
    vector<string> get_all_wen_xing();

    /**
     * @brief 获取所有吉星列表
     */
    vector<string> get_all_ji_xing();

    /**
     * @brief 获取所有煞星列表
     */
    vector<string> get_all_sha_xing();

} // namespace ZhouYi::ZiWei

// ============= ZhMapper 特化 =============

export namespace ZhouYi::Mapper {
    using namespace ZhouYi::ZiWei;
    
    template<>
    struct ZhMap<XingYaoTeXing> {
        static constexpr auto get_map() {
            using namespace std::literals;
            return std::array{
                "桃花星"sv, "财星"sv, "权星"sv, "文星"sv,
                "寿星"sv, "驿马星"sv, "孤星"sv, "吉星"sv,
                "煞星"sv, "主星"sv, "辅星"sv, "杂耀"sv
            };
        }
    };
    
    template<>
    struct ZhMap<XingYaoWuXing> {
        static constexpr auto get_map() {
            using namespace std::literals;
            return std::array{
                "金"sv, "木"sv, "水"sv, "火"sv, "土"sv
            };
        }
    };
    
    template<>
    struct ZhMap<XingYaoYinYang> {
        static constexpr auto get_map() {
            using namespace std::literals;
            return std::array{
                "阳"sv, "阴"sv
            };
        }
    };
    
} // namespace ZhouYi::Mapper

