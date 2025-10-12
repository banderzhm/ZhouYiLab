// 干支综合模块 - 演示模块之间的依赖
export module ZhouYi.GanZhi;

// 导入标准库模块
import std;

// 导入自定义模块
import ZhouYi.TianGan;
import ZhouYi.DiZhi;

// 导出干支组合功能
export namespace ZhouYi {

/**
 * @brief 干支组合类
 * 
 * 表示天干地支的组合（如甲子、乙丑等）
 */
class GanZhi {
public:
    /**
     * @brief 构造函数
     * @param gan 天干
     * @param zhi 地支
     */
    GanZhi(const TianGan& gan, const DiZhi& zhi)
        : gan_(gan), zhi_(zhi) {}

    /**
     * @brief 从年份创建干支
     * @param year 公历年份
     * @return 干支对象
     */
    static GanZhi from_year(int year) {
        int gan_index = (year - 4) % 10;
        int zhi_index = (year - 4) % 12;
        return GanZhi(
            TianGan::from_index(gan_index),
            DiZhi::from_index(zhi_index)
        );
    }

    /**
     * @brief 获取干支的字符串表示
     * @return 干支名称（如"甲子"）
     */
    std::string to_string() const {
        return gan_.to_string() + zhi_.to_string();
    }

    /**
     * @brief 获取详细信息
     * @return 包含天干、地支、五行等信息的字符串
     */
    std::string get_info() const {
        std::ostringstream oss;
        oss << "干支: " << to_string() << "\n"
            << "天干: " << gan_.to_string() 
            << " (五行: " << gan_.wu_xing() 
            << ", " << (gan_.is_yang() ? "阳" : "阴") << ")\n"
            << "地支: " << zhi_.to_string() 
            << " (生肖: " << zhi_.sheng_xiao()
            << ", 五行: " << zhi_.wu_xing()
            << ", " << (zhi_.is_yang() ? "阳" : "阴") << ")\n"
            << "时辰: " << zhi_.shi_chen();
        return oss.str();
    }

    /**
     * @brief 获取纳音五行
     * @return 纳音五行名称
     */
    std::string na_yin() const {
        // 简化的纳音五行计算
        static const std::vector<std::string> na_yin_table = {
            "海中金", "炉中火", "大林木", "路旁土", "剑锋金", "山头火",
            "涧下水", "城头土", "白蜡金", "杨柳木", "泉中水", "屋上土",
            "霹雳火", "松柏木", "长流水", "沙中金", "山下火", "平地木",
            "壁上土", "金箔金", "覆灯火", "天河水", "大驿土", "钗钏金",
            "桑柘木", "大溪水", "沙中土", "天上火", "石榴木", "大海水"
        };
        int index = (gan_.index() / 2 * 5 + zhi_.index() / 2) % 30;
        return na_yin_table[index];
    }

    /**
     * @brief 获取天干
     */
    const TianGan& get_tian_gan() const { return gan_; }

    /**
     * @brief 获取地支
     */
    const DiZhi& get_di_zhi() const { return zhi_; }

private:
    TianGan gan_;
    DiZhi zhi_;
};

/**
 * @brief 生成六十甲子
 * @return 六十甲子列表
 */
std::vector<GanZhi> generate_liu_shi_jia_zi() {
    std::vector<GanZhi> result;
    for (int i = 0; i < 60; ++i) {
        result.emplace_back(
            TianGan::from_index(i % 10),
            DiZhi::from_index(i % 12)
        );
    }
    return result;
}

/**
 * @brief 打印六十甲子表
 */
void print_liu_shi_jia_zi() {
    auto jia_zi = generate_liu_shi_jia_zi();
    std::cout << "六十甲子表:\n";
    std::cout << "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n";
    for (size_t i = 0; i < jia_zi.size(); ++i) {
        std::cout << std::format("{:2}. {:4}", i + 1, jia_zi[i].to_string());
        if ((i + 1) % 6 == 0) {
            std::cout << "\n";
        } else {
            std::cout << "  ";
        }
    }
    std::cout << "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n";
}

} // namespace ZhouYi

