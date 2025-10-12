// 地支模块 - 使用 C++23 modules
export module ZhouYi.DiZhi;

// 导入第三方库模块
import magic_enum;

// 导入标准库模块（最后）
import std;

// 导出地支相关功能
export namespace ZhouYi {

/**
 * @brief 地支类
 * 
 * 表示中国传统的十二地支
 */
class DiZhi {
public:
    enum class Type {
        Zi = 0,   // 子（鼠）
        Chou,     // 丑（牛）
        Yin,      // 寅（虎）
        Mao,      // 卯（兔）
        Chen,     // 辰（龙）
        Si,       // 巳（蛇）
        Wu,       // 午（马）
        Wei,      // 未（羊）
        Shen,     // 申（猴）
        You,      // 酉（鸡）
        Xu,       // 戌（狗）
        Hai       // 亥（猪）
    };

    /**
     * @brief 构造函数
     * @param type 地支类型
     */
    explicit DiZhi(Type type) : type_(type) {}

    /**
     * @brief 从索引创建地支
     * @param index 索引 (0-11)
     * @return 地支对象
     */
    static DiZhi from_index(int index) {
        return DiZhi(static_cast<Type>(index % 12));
    }

    /**
     * @brief 获取地支的中文名称
     * @return 中文名称
     */
    std::string to_string() const {
        static const std::vector<std::string> names = {
            "子", "丑", "寅", "卯", "辰", "巳",
            "午", "未", "申", "酉", "戌", "亥"
        };
        return names[static_cast<int>(type_)];
    }

    /**
     * @brief 获取对应的生肖
     * @return 生肖名称
     */
    std::string sheng_xiao() const {
        static const std::vector<std::string> animals = {
            "鼠", "牛", "虎", "兔", "龙", "蛇",
            "马", "羊", "猴", "鸡", "狗", "猪"
        };
        return animals[static_cast<int>(type_)];
    }

    /**
     * @brief 获取地支索引
     * @return 索引值 (0-11)
     */
    int index() const {
        return static_cast<int>(type_);
    }

    /**
     * @brief 获取五行属性
     * @return 五行名称
     */
    std::string wu_xing() const {
        static const std::vector<std::string> elements = {
            "水", "土", "木", "木", "土", "火",
            "火", "土", "金", "金", "土", "水"
        };
        return elements[static_cast<int>(type_)];
    }

    /**
     * @brief 获取阴阳属性
     * @return true 为阳，false 为阴
     */
    bool is_yang() const {
        return static_cast<int>(type_) % 2 == 0;
    }

    /**
     * @brief 获取对应的时辰
     * @return 时辰描述
     */
    std::string shi_chen() const {
        static const std::vector<std::string> times = {
            "23-01时", "01-03时", "03-05时", "05-07时",
            "07-09时", "09-11时", "11-13时", "13-15时",
            "15-17时", "17-19时", "19-21时", "21-23时"
        };
        return times[static_cast<int>(type_)];
    }

private:
    Type type_;
};

/**
 * @brief 获取所有地支
 * @return 地支列表
 */
std::vector<DiZhi> get_all_di_zhi() {
    std::vector<DiZhi> result;
    for (int i = 0; i < 12; ++i) {
        result.push_back(DiZhi::from_index(i));
    }
    return result;
}

/**
 * @brief 计算干支纪年
 * @param year 公历年份
 * @return 干支年对应的地支
 */
DiZhi year_to_di_zhi(int year) {
    return DiZhi::from_index((year - 4) % 12);
}

/**
 * @brief 地支枚举的中文映射辅助函数
 */
namespace DiZhiMapper {
    // 获取地支中文名称
    constexpr auto to_zh(DiZhi::Type type) -> std::string_view {
        constexpr std::array<std::string_view, 12> names = {
            "子", "丑", "寅", "卯", "辰", "巳",
            "午", "未", "申", "酉", "戌", "亥"
        };
        return names[static_cast<int>(type)];
    }
    
    // 从中文名称获取地支枚举
    constexpr auto from_zh(std::string_view zh_name) -> std::optional<DiZhi::Type> {
        constexpr std::array<std::string_view, 12> names = {
            "子", "丑", "寅", "卯", "辰", "巳",
            "午", "未", "申", "酉", "戌", "亥"
        };
        
        for (std::size_t i = 0; i < names.size(); ++i) {
            if (names[i] == zh_name) {
                return static_cast<DiZhi::Type>(i);
            }
        }
        return std::nullopt;
    }
    
    // 获取生肖中文名称
    constexpr auto sheng_xiao_zh(DiZhi::Type type) -> std::string_view {
        constexpr std::array<std::string_view, 12> animals = {
            "鼠", "牛", "虎", "兔", "龙", "蛇",
            "马", "羊", "猴", "鸡", "狗", "猪"
        };
        return animals[static_cast<int>(type)];
    }
    
    // 使用 magic_enum 获取英文名称
    constexpr auto to_en(DiZhi::Type type) -> std::string_view {
        return magic_enum::enum_name(type);
    }
    
    // 从英文名称获取枚举
    constexpr auto from_en(std::string_view en_name) -> std::optional<DiZhi::Type> {
        return magic_enum::enum_cast<DiZhi::Type>(en_name);
    }
}

} // namespace ZhouYi