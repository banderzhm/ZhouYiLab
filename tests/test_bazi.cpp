// 八字系统测试

// Use global module fragment to isolate doctest from C++23 modules
module;
#include <doctest/doctest.h>

import ZhouYi.BaZiBase;
import ZhouYi.GanZhi;
import nlohmann.json;  // 添加 JSON 支持
import std;

using namespace ZhouYi::BaZiBase;
using namespace ZhouYi::GanZhi;

TEST_SUITE("八字系统测试") {
    
    TEST_CASE("Pillar 基本功能") {
        SUBCASE("构造函数 - 枚举") {
            Pillar p(TianGan::Jia, DiZhi::Zi);
            CHECK(p.gan == TianGan::Jia);
            CHECK(p.zhi == DiZhi::Zi);
        }
        
        SUBCASE("构造函数 - 字符串") {
            std::string gan_str = "甲";
            std::string zhi_str = "子";
            Pillar p(gan_str, zhi_str);
            CHECK(p.gan == TianGan::Jia);
            CHECK(p.zhi == DiZhi::Zi);
        }
        
        SUBCASE("构造函数 - string_view") {
            std::string_view gan_sv = "甲";
            std::string_view zhi_sv = "子";
            Pillar p(gan_sv, zhi_sv);
            CHECK(p.gan == TianGan::Jia);
            CHECK(p.zhi == DiZhi::Zi);
        }
        
        SUBCASE("获取中文名称") {
            Pillar p(TianGan::Jia, DiZhi::Zi);
            CHECK(p.stem() == "甲");
            CHECK(p.branch() == "子");
            CHECK(p.to_string() == "甲子");
        }
    }
    
    TEST_CASE("BaZi 结构") {
        SUBCASE("基本结构") {
            BaZi bazi;
            bazi.year = Pillar(TianGan::Jia, DiZhi::Zi);
            bazi.month = Pillar(TianGan::Yi, DiZhi::Chou);
            bazi.day = Pillar(TianGan::Bing, DiZhi::Yin);
            bazi.hour = Pillar(TianGan::Ding, DiZhi::Mao);
            
            CHECK(bazi.year.to_string() == "甲子");
            CHECK(bazi.month.to_string() == "乙丑");
            CHECK(bazi.day.to_string() == "丙寅");
            CHECK(bazi.hour.to_string() == "丁卯");
        }
    }
    
    TEST_CASE("公历转八字") {
        SUBCASE("2025年1月1日 00:00") {
            auto bazi = BaZi::from_solar(2025, 1, 1, 0, 0);
            
            // 验证年柱（2025年是乙巳年）
            CHECK(bazi.year.gan == TianGan::Yi);
            CHECK(bazi.year.zhi == DiZhi::Si);
            
            // 验证输出
            CHECK(bazi.year.to_string() == "乙巳");
        }
        
        SUBCASE("2024年10月13日 14:30") {
            auto bazi = BaZi::from_solar(2024, 10, 13, 14, 30);
            
            // 验证年柱（2024年是甲辰年）
            CHECK(bazi.year.gan == TianGan::Jia);
            CHECK(bazi.year.zhi == DiZhi::Chen);
        }
    }
    
    TEST_CASE("农历转八字") {
        SUBCASE("农历2024年九月初一") {
            auto bazi = BaZi::from_lunar(2024, 9, 1, 12, 0);
            
            // 验证年柱
            CHECK(bazi.year.gan == TianGan::Jia);
            CHECK(bazi.year.zhi == DiZhi::Chen);
        }
    }
    
    TEST_CASE("旬空计算") {
        SUBCASE("验证旬空规则") {
            auto bazi = BaZi::from_solar(2024, 10, 13, 14, 30);
            
            // 旬空应该是两个地支
            CHECK_FALSE(bazi.xun_kong_1.empty());
            CHECK_FALSE(bazi.xun_kong_2.empty());
            
            // 旬空地支不应该相同
            CHECK(bazi.xun_kong_1 != bazi.xun_kong_2);
        }
    }
    
    TEST_CASE("日期转换") {
        SUBCASE("公历转农历") {
            SolarDate solar(2024, 10, 13);
            auto lunar_str = solar.to_lunar_string();
            
            // 应该包含年月日信息
            CHECK_FALSE(lunar_str.empty());
        }
        
        SUBCASE("农历转公历") {
            LunarDate lunar(2024, 9, 1);
            auto solar_str = lunar.to_solar_string();
            
            // 应该包含年月日信息
            CHECK_FALSE(solar_str.empty());
        }
    }
    
    TEST_CASE("JSON 序列化") {
        SUBCASE("Pillar 序列化") {
            Pillar p(TianGan::Jia, DiZhi::Zi);
            nlohmann::json j = p;
            
            CHECK(j.contains("stem"));
            CHECK(j.contains("branch"));
            CHECK(j["stem"] == "甲");
            CHECK(j["branch"] == "子");
        }
        
        SUBCASE("BaZi 序列化") {
            BaZi bazi;
            bazi.year = Pillar(TianGan::Jia, DiZhi::Zi);
            bazi.month = Pillar(TianGan::Yi, DiZhi::Chou);
            bazi.day = Pillar(TianGan::Bing, DiZhi::Yin);
            bazi.hour = Pillar(TianGan::Ding, DiZhi::Mao);
            
            nlohmann::json j = bazi;
            
            CHECK(j.contains("year"));
            CHECK(j.contains("month"));
            CHECK(j.contains("day"));
            CHECK(j.contains("hour"));
        }
        
        SUBCASE("反序列化") {
            nlohmann::json j = {
                {"year", {{"stem", "甲"}, {"branch", "子"}}},
                {"month", {{"stem", "乙"}, {"branch", "丑"}}},
                {"day", {{"stem", "丙"}, {"branch", "寅"}}},
                {"hour", {{"stem", "丁"}, {"branch", "卯"}}},
                {"xun_kong_1", "戌"},
                {"xun_kong_2", "亥"}
            };
            
            BaZi bazi = j.get<BaZi>();
            
            CHECK(bazi.year.to_string() == "甲子");
            CHECK(bazi.month.to_string() == "乙丑");
            CHECK(bazi.day.to_string() == "丙寅");
            CHECK(bazi.hour.to_string() == "丁卯");
            CHECK(bazi.xun_kong_1 == "戌");
            CHECK(bazi.xun_kong_2 == "亥");
        }
    }
}

