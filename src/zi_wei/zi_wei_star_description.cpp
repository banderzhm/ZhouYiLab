// 紫微斗数星耀特性系统模块（实现）
module ZhouYi.ZiWei.StarDescription;

import ZhouYi.ZiWei.Constants;
import ZhouYi.ZhMapper;
import fmt;
import nlohmann.json;
import std;

using json = nlohmann::json;

namespace ZhouYi::ZiWei {
    using namespace std;

    string XingYaoInfo::to_string() const {
        string result = fmt::format("【{}】", name);
        result += fmt::format(" 五行:{} 阴阳:{}", 
            string(Mapper::to_zh(wu_xing)),
            string(Mapper::to_zh(yin_yang)));
        
        if (!te_xing_list.empty()) {
            result += " 特性:";
            for (const auto& tx : te_xing_list) {
                result += string(Mapper::to_zh(tx)) + " ";
            }
        }
        
        if (!xing_qing.empty()) {
            result += fmt::format("\n  星情: {}", xing_qing);
        }
        
        if (!zhu_shi.empty()) {
            result += fmt::format("\n  主事: {}", zhu_shi);
        }
        
        return result;
    }

    // ============= 星耀特性数据库（JSON格式）=============
    
    /**
     * 内嵌JSON数据库，方便后期扩展和修改
     * 
     * 格式说明：
     * - name: 星耀名称
     * - te_xing: 特性列表（桃花星、财星、权星、文星、寿星、驿马星、孤星、吉星、煞星、主星、辅星、杂耀）
     * - wu_xing: 五行属性（金、木、水、火、土）
     * - yin_yang: 阴阳属性（阳、阴）
     * - xing_qing: 星情描述
     * - zhu_shi: 主管事项
     */
    static const char* XING_YAO_JSON_DATABASE = R"({
  "主星": [
    {
      "name": "紫微",
      "te_xing": ["主星", "权星"],
      "wu_xing": "土",
      "yin_yang": "阳",
      "xing_qing": "帝座，尊贵之星",
      "zhu_shi": "官禄、权贵、领导"
    },
    {
      "name": "天机",
      "te_xing": ["主星", "文星"],
      "wu_xing": "木",
      "yin_yang": "阳",
      "xing_qing": "智慧之星，善变谋略",
      "zhu_shi": "智慧、机巧、兄弟"
    },
    {
      "name": "太阳",
      "te_xing": ["主星", "权星"],
      "wu_xing": "火",
      "yin_yang": "阳",
      "xing_qing": "光明正大，博爱无私",
      "zhu_shi": "官禄、父亲、男性贵人"
    },
    {
      "name": "武曲",
      "te_xing": ["主星", "财星"],
      "wu_xing": "金",
      "yin_yang": "阳",
      "xing_qing": "财星，刚毅果断",
      "zhu_shi": "财富、武职、刚强"
    },
    {
      "name": "天同",
      "te_xing": ["主星"],
      "wu_xing": "水",
      "yin_yang": "阳",
      "xing_qing": "福星，安逸享受",
      "zhu_shi": "福德、享受、懒散"
    },
    {
      "name": "廉贞",
      "te_xing": ["主星", "桃花星"],
      "wu_xing": "火",
      "yin_yang": "阳",
      "xing_qing": "囚星，桃花犯主",
      "zhu_shi": "是非、桃花、血光"
    },
    {
      "name": "天府",
      "te_xing": ["主星", "财星"],
      "wu_xing": "土",
      "yin_yang": "阳",
      "xing_qing": "财库，保守稳重",
      "zhu_shi": "财富、保守、稳定"
    },
    {
      "name": "太阴",
      "te_xing": ["主星", "财星"],
      "wu_xing": "水",
      "yin_yang": "阴",
      "xing_qing": "富星，清秀文雅",
      "zhu_shi": "财富、母亲、女性贵人"
    },
    {
      "name": "贪狼",
      "te_xing": ["主星", "桃花星"],
      "wu_xing": "木",
      "yin_yang": "阳",
      "xing_qing": "桃花星，多才多艺",
      "zhu_shi": "桃花、欲望、才艺"
    },
    {
      "name": "巨门",
      "te_xing": ["主星"],
      "wu_xing": "水",
      "yin_yang": "阴",
      "xing_qing": "暗星，口舌是非",
      "zhu_shi": "口才、是非、暗昧"
    },
    {
      "name": "天相",
      "te_xing": ["主星"],
      "wu_xing": "水",
      "yin_yang": "阳",
      "xing_qing": "印星，稳重厚道",
      "zhu_shi": "印信、衣食、助人"
    },
    {
      "name": "天梁",
      "te_xing": ["主星", "寿星"],
      "wu_xing": "土",
      "yin_yang": "阳",
      "xing_qing": "寿星，清高孤傲",
      "zhu_shi": "长寿、化解、清高"
    },
    {
      "name": "七杀",
      "te_xing": ["主星"],
      "wu_xing": "金",
      "yin_yang": "阳",
      "xing_qing": "将星，勇猛冲动",
      "zhu_shi": "权威、冲动、孤克"
    },
    {
      "name": "破军",
      "te_xing": ["主星"],
      "wu_xing": "水",
      "yin_yang": "阴",
      "xing_qing": "耗星，破坏变动",
      "zhu_shi": "变动、破坏、开创"
    }
  ],
  "辅星": [
    {
      "name": "左辅",
      "te_xing": ["辅星", "吉星"],
      "wu_xing": "土",
      "yin_yang": "阳",
      "xing_qing": "助星，贵人相助",
      "zhu_shi": "助力、贵人、辅佐"
    },
    {
      "name": "右弼",
      "te_xing": ["辅星", "吉星"],
      "wu_xing": "水",
      "yin_yang": "阴",
      "xing_qing": "助星，贵人相助",
      "zhu_shi": "助力、贵人、辅佐"
    },
    {
      "name": "文昌",
      "te_xing": ["辅星", "文星", "吉星"],
      "wu_xing": "金",
      "yin_yang": "阳",
      "xing_qing": "文星，科名功名",
      "zhu_shi": "文采、功名、考试"
    },
    {
      "name": "文曲",
      "te_xing": ["辅星", "文星", "吉星"],
      "wu_xing": "水",
      "yin_yang": "阴",
      "xing_qing": "文星，才艺口才",
      "zhu_shi": "才艺、口才、桃花"
    },
    {
      "name": "天魁",
      "te_xing": ["辅星", "吉星"],
      "wu_xing": "火",
      "yin_yang": "阳",
      "xing_qing": "贵人星，阳贵",
      "zhu_shi": "贵人、提携、男性贵人"
    },
    {
      "name": "天钺",
      "te_xing": ["辅星", "吉星"],
      "wu_xing": "火",
      "yin_yang": "阴",
      "xing_qing": "贵人星，阴贵",
      "zhu_shi": "贵人、提携、女性贵人"
    }
  ],
  "煞星": [
    {
      "name": "擎羊",
      "te_xing": ["煞星"],
      "wu_xing": "金",
      "yin_yang": "阳",
      "xing_qing": "刑星，刚烈冲动",
      "zhu_shi": "刑克、冲动、外伤"
    },
    {
      "name": "陀罗",
      "te_xing": ["煞星"],
      "wu_xing": "金",
      "yin_yang": "阴",
      "xing_qing": "拖延，进退失据",
      "zhu_shi": "拖延、纠缠、暗伤"
    },
    {
      "name": "火星",
      "te_xing": ["煞星"],
      "wu_xing": "火",
      "yin_yang": "阳",
      "xing_qing": "暴躁，性急火爆",
      "zhu_shi": "急躁、火灾、突发"
    },
    {
      "name": "铃星",
      "te_xing": ["煞星"],
      "wu_xing": "火",
      "yin_yang": "阴",
      "xing_qing": "阴险，暗中破坏",
      "zhu_shi": "阴险、暗害、内伤"
    },
    {
      "name": "地空",
      "te_xing": ["煞星"],
      "wu_xing": "火",
      "yin_yang": "阳",
      "xing_qing": "空劫，破财不聚",
      "zhu_shi": "破财、空虚、理想"
    },
    {
      "name": "地劫",
      "te_xing": ["煞星"],
      "wu_xing": "火",
      "yin_yang": "阴",
      "xing_qing": "空劫，破财劫夺",
      "zhu_shi": "破财、劫夺、动荡"
    }
  ],
  "杂耀": [
    {
      "name": "禄存",
      "te_xing": ["杂耀", "财星", "吉星"],
      "wu_xing": "土",
      "yin_yang": "阴",
      "xing_qing": "禄星，财禄丰厚",
      "zhu_shi": "财禄、守财、保守"
    },
    {
      "name": "天马",
      "te_xing": ["杂耀", "驿马星"],
      "wu_xing": "火",
      "yin_yang": "阳",
      "xing_qing": "驿马星，奔波变动",
      "zhu_shi": "变动、奔波、迁移"
    },
    {
      "name": "红鸾",
      "te_xing": ["杂耀", "桃花星"],
      "wu_xing": "水",
      "yin_yang": "阴",
      "xing_qing": "桃花星，婚姻喜庆",
      "zhu_shi": "婚姻、喜庆、桃花"
    },
    {
      "name": "天喜",
      "te_xing": ["杂耀", "桃花星"],
      "wu_xing": "水",
      "yin_yang": "阳",
      "xing_qing": "喜庆星，欢乐喜悦",
      "zhu_shi": "喜庆、桃花、欢乐"
    },
    {
      "name": "咸池",
      "te_xing": ["杂耀", "桃花星"],
      "wu_xing": "水",
      "yin_yang": "阴",
      "xing_qing": "桃花星，淫欲之星",
      "zhu_shi": "桃花、欲望、感情纠葛"
    },
    {
      "name": "孤辰",
      "te_xing": ["杂耀", "孤星"],
      "wu_xing": "火",
      "yin_yang": "阳",
      "xing_qing": "孤独星，孤克六亲",
      "zhu_shi": "孤独、孤僻、克亲"
    },
    {
      "name": "寡宿",
      "te_xing": ["杂耀", "孤星"],
      "wu_xing": "火",
      "yin_yang": "阴",
      "xing_qing": "孤独星，寡居之象",
      "zhu_shi": "孤独、寡居、克配偶"
    },
    {
      "name": "天寿",
      "te_xing": ["杂耀", "寿星"],
      "wu_xing": "土",
      "yin_yang": "阳",
      "xing_qing": "寿星，延年益寿",
      "zhu_shi": "长寿、健康、福德"
    },
    {
      "name": "天刑",
      "te_xing": ["杂耀"],
      "wu_xing": "火",
      "yin_yang": "阳",
      "xing_qing": "刑罚之星",
      "zhu_shi": "刑罚、法律、孤独"
    },
    {
      "name": "天姚",
      "te_xing": ["杂耀", "桃花星"],
      "wu_xing": "水",
      "yin_yang": "阴",
      "xing_qing": "桃花星，风流艳遇",
      "zhu_shi": "桃花、艳遇、魅力"
    },
    {
      "name": "天巫",
      "te_xing": ["杂耀"],
      "wu_xing": "火",
      "yin_yang": "阴",
      "xing_qing": "宗教玄学之星",
      "zhu_shi": "宗教、玄学、直觉"
    },
    {
      "name": "天月",
      "te_xing": ["杂耀"],
      "wu_xing": "土",
      "yin_yang": "阴",
      "xing_qing": "病符之星",
      "zhu_shi": "疾病、阴疾、慢性病"
    },
    {
      "name": "阴煞",
      "te_xing": ["杂耀"],
      "wu_xing": "金",
      "yin_yang": "阴",
      "xing_qing": "阴险暗害之星",
      "zhu_shi": "阴险、暗害、小人"
    },
    {
      "name": "解神",
      "te_xing": ["杂耀", "吉星"],
      "wu_xing": "土",
      "yin_yang": "阳",
      "xing_qing": "化解之星",
      "zhu_shi": "化解、转危为安"
    },
    {
      "name": "天德",
      "te_xing": ["杂耀", "吉星"],
      "wu_xing": "土",
      "yin_yang": "阳",
      "xing_qing": "德星，逢凶化吉",
      "zhu_shi": "德行、化险、福荫"
    },
    {
      "name": "月德",
      "te_xing": ["杂耀", "吉星"],
      "wu_xing": "土",
      "yin_yang": "阴",
      "xing_qing": "德星，阴德福荫",
      "zhu_shi": "阴德、福荫、女贵"
    }
  ]
})";

    // ============= JSON解析辅助函数 =============
    
    // 将中文特性转换为枚举
    static XingYaoTeXing parse_te_xing(const string& te_xing_str) {
        static const map<string, XingYaoTeXing> te_xing_map = {
            {"桃花星", XingYaoTeXing::TaoHua},
            {"财星", XingYaoTeXing::CaiXing},
            {"权星", XingYaoTeXing::QuanXing},
            {"文星", XingYaoTeXing::WenXing},
            {"寿星", XingYaoTeXing::ShouXing},
            {"驿马星", XingYaoTeXing::YiMaXing},
            {"孤星", XingYaoTeXing::GuXing},
            {"吉星", XingYaoTeXing::JiXing},
            {"煞星", XingYaoTeXing::ShaXing},
            {"主星", XingYaoTeXing::ZhuXing},
            {"辅星", XingYaoTeXing::FuXing},
            {"杂耀", XingYaoTeXing::ZaYao}
        };
        
        auto it = te_xing_map.find(te_xing_str);
        return it != te_xing_map.end() ? it->second : XingYaoTeXing::ZaYao;
    }
    
    // 将中文五行转换为枚举
    static XingYaoWuXing parse_wu_xing(const string& wu_xing_str) {
        static const map<string, XingYaoWuXing> wu_xing_map = {
            {"金", XingYaoWuXing::Jin},
            {"木", XingYaoWuXing::Mu},
            {"水", XingYaoWuXing::Shui},
            {"火", XingYaoWuXing::Huo},
            {"土", XingYaoWuXing::Tu}
        };
        
        auto it = wu_xing_map.find(wu_xing_str);
        return it != wu_xing_map.end() ? it->second : XingYaoWuXing::Tu;
    }
    
    // 将中文阴阳转换为枚举
    static XingYaoYinYang parse_yin_yang(const string& yin_yang_str) {
        return yin_yang_str == "阳" ? XingYaoYinYang::Yang : XingYaoYinYang::Yin;
    }
    
    // 从JSON加载星耀数据库
    static map<string, XingYaoInfo> load_xing_yao_database() {
        map<string, XingYaoInfo> database;
        
        try {
            auto j = json::parse(XING_YAO_JSON_DATABASE);
            
            // 遍历所有分类（主星、辅星、煞星、杂耀）
            for (auto it = j.begin(); it != j.end(); ++it) {
                const auto& stars = it.value();
                for (auto& star_json : stars) {
                    XingYaoInfo info;
                    info.name = star_json["name"].get<string>();
                    
                    // 解析特性列表
                    for (auto& te_xing_str : star_json["te_xing"]) {
                        info.te_xing_list.push_back(parse_te_xing(te_xing_str.get<string>()));
                    }
                    
                    // 解析五行和阴阳
                    info.wu_xing = parse_wu_xing(star_json["wu_xing"].get<string>());
                    info.yin_yang = parse_yin_yang(star_json["yin_yang"].get<string>());
                    
                    // 解析星情和主事
                    info.xing_qing = star_json["xing_qing"].get<string>();
                    info.zhu_shi = star_json["zhu_shi"].get<string>();
                    
                    database[info.name] = info;
                }
            }
        } catch (const json::exception& e) {
            // JSON解析错误，返回空数据库
            fmt::print("[错误] JSON解析错误: {}\n", e.what());
        }
        
        return database;
    }
    
    // 获取星耀数据库（单例模式）
    static const map<string, XingYaoInfo>& get_xing_yao_database() {
        static const auto database = load_xing_yao_database();
        return database;
    }

    // ============= 实现函数 =============

    XingYaoInfo get_xing_yao_info(const string& star_name) {
        const auto& database = get_xing_yao_database();
        auto it = database.find(star_name);
        
        if (it != database.end()) {
            return it->second;
        }
        
        // 返回默认信息
        return XingYaoInfo{
            .name = star_name,
            .te_xing_list = {},
            .wu_xing = XingYaoWuXing::Tu,
            .yin_yang = XingYaoYinYang::Yang,
            .xing_qing = "未知",
            .zhu_shi = "未知"
        };
    }

    bool is_tao_hua_xing(const string& star_name) {
        auto info = get_xing_yao_info(star_name);
        return info.has_te_xing(XingYaoTeXing::TaoHua);
    }

    bool is_cai_xing(const string& star_name) {
        auto info = get_xing_yao_info(star_name);
        return info.has_te_xing(XingYaoTeXing::CaiXing);
    }

    bool is_quan_xing(const string& star_name) {
        auto info = get_xing_yao_info(star_name);
        return info.has_te_xing(XingYaoTeXing::QuanXing);
    }

    bool is_wen_xing(const string& star_name) {
        auto info = get_xing_yao_info(star_name);
        return info.has_te_xing(XingYaoTeXing::WenXing);
    }

    bool is_ji_xing(const string& star_name) {
        auto info = get_xing_yao_info(star_name);
        return info.has_te_xing(XingYaoTeXing::JiXing);
    }

    bool is_sha_xing(const string& star_name) {
        auto info = get_xing_yao_info(star_name);
        return info.has_te_xing(XingYaoTeXing::ShaXing);
    }

    vector<string> get_all_tao_hua_xing() {
        vector<string> result;
        const auto& database = get_xing_yao_database();
        
        for (const auto& [name, info] : database) {
            if (info.has_te_xing(XingYaoTeXing::TaoHua)) {
                result.push_back(name);
            }
        }
        
        return result;
    }

    vector<string> get_all_cai_xing() {
        vector<string> result;
        const auto& database = get_xing_yao_database();
        
        for (const auto& [name, info] : database) {
            if (info.has_te_xing(XingYaoTeXing::CaiXing)) {
                result.push_back(name);
            }
        }
        
        return result;
    }

    vector<string> get_all_quan_xing() {
        vector<string> result;
        const auto& database = get_xing_yao_database();
        
        for (const auto& [name, info] : database) {
            if (info.has_te_xing(XingYaoTeXing::QuanXing)) {
                result.push_back(name);
            }
        }
        
        return result;
    }

    vector<string> get_all_wen_xing() {
        vector<string> result;
        const auto& database = get_xing_yao_database();
        
        for (const auto& [name, info] : database) {
            if (info.has_te_xing(XingYaoTeXing::WenXing)) {
                result.push_back(name);
            }
        }
        
        return result;
    }

    vector<string> get_all_ji_xing() {
        vector<string> result;
        const auto& database = get_xing_yao_database();
        
        for (const auto& [name, info] : database) {
            if (info.has_te_xing(XingYaoTeXing::JiXing)) {
                result.push_back(name);
            }
        }
        
        return result;
    }

    vector<string> get_all_sha_xing() {
        vector<string> result;
        const auto& database = get_xing_yao_database();
        
        for (const auto& [name, info] : database) {
            if (info.has_te_xing(XingYaoTeXing::ShaXing)) {
                result.push_back(name);
            }
        }
        
        return result;
    }

} // namespace ZhouYi::ZiWei
