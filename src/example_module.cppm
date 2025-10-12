// C++23 Module 接口文件示例
// 文件扩展名: .cppm (跨平台) 或 .ixx (MSVC)

// 使用 C++23 标准库模块
export module ZhouYi.TianGan;

// 导入第三方库模块
import magic_enum;

// 导入标准库模块（最后）
import std;

// 导出的命名空间和类型
export namespace ZhouYi {

/**
 * @brief 天干类
 * 
 * 表示中国传统的十天干
 */
class TianGan {
public:
    enum class Type {
        Jia = 0,  // 甲
        Yi,       // 乙
        Bing,     // 丙
        Ding,     // 丁
        Wu,       // 戊
        Ji,       // 己
        Geng,     // 庚
        Xin,      // 辛
        Ren,      // 壬
        Gui       // 癸
    };

    /**
     * @brief 构造函数
     * @param type 天干类型
     */
    explicit TianGan(Type type) : type_(type) {}

    /**
     * @brief 从索引创建天干
     * @param index 索引 (0-9)
     * @return 天干对象
     */
    static TianGan from_index(int index) {
        return TianGan(static_cast<Type>(index % 10));
    }

    /**
     * @brief 获取天干的中文名称
     * @return 中文名称
     */
    std::string to_string() const {
        static const std::vector<std::string> names = {
            "甲", "乙", "丙", "丁", "戊", 
            "己", "庚", "辛", "壬", "癸"
        };
        return names[static_cast<int>(type_)];
    }

    /**
     * @brief 获取天干索引
     * @return 索引值 (0-9)
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
            "木", "木", "火", "火", "土",
            "土", "金", "金", "水", "水"
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

private:
    Type type_;
};

/**
 * @brief 获取所有天干
 * @return 天干列表
 */
std::vector<TianGan> get_all_tian_gan() {
    std::vector<TianGan> result;
    for (int i = 0; i < 10; ++i) {
        result.push_back(TianGan::from_index(i));
    }
    return result;
}

/**
 * @brief 天干枚举的中文映射辅助函数
 */
namespace TianGanMapper {
    // 获取中文名称
    constexpr auto to_zh(TianGan::Type type) -> std::string_view {
        constexpr std::array<std::string_view, 10> names = {
            "甲", "乙", "丙", "丁", "戊", 
            "己", "庚", "辛", "壬", "癸"
        };
        return names[static_cast<int>(type)];
    }
    
    // 从中文名称获取枚举
    constexpr auto from_zh(std::string_view zh_name) -> std::optional<TianGan::Type> {
        constexpr std::array<std::string_view, 10> names = {
            "甲", "乙", "丙", "丁", "戊", 
            "己", "庚", "辛", "壬", "癸"
        };
        
        for (std::size_t i = 0; i < names.size(); ++i) {
            if (names[i] == zh_name) {
                return static_cast<TianGan::Type>(i);
            }
        }
        return std::nullopt;
    }
    
    // 使用 magic_enum 获取英文名称
    constexpr auto to_en(TianGan::Type type) -> std::string_view {
        return magic_enum::enum_name(type);
    }
    
    // 从英文名称获取枚举
    constexpr auto from_en(std::string_view en_name) -> std::optional<TianGan::Type> {
        return magic_enum::enum_cast<TianGan::Type>(en_name);
    }
}

} // namespace ZhouYi

