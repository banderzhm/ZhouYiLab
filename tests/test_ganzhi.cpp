// 干支系统测试

// Use global module fragment to isolate doctest from C++23 modules
module;
#include <doctest/doctest.h>

import ZhouYi.GanZhi;
import magic_enum;
import std;

using namespace ZhouYi::GanZhi;

TEST_SUITE("干支系统测试") {
    
    TEST_CASE("天干枚举反射") {
        SUBCASE("枚举值数量") {
            constexpr auto values = magic_enum::enum_values<TianGan>();
            CHECK(values.size() == 10);
        }
        
        SUBCASE("枚举名称获取") {
            auto name = magic_enum::enum_name(TianGan::Jia);
            CHECK(name == "Jia");
        }
    }
    
    TEST_CASE("地支枚举反射") {
        SUBCASE("枚举值数量") {
            constexpr auto values = magic_enum::enum_values<DiZhi>();
            CHECK(values.size() == 12);
        }
        
        SUBCASE("枚举名称获取") {
            auto name = magic_enum::enum_name(DiZhi::Zi);
            CHECK(name == "Zi");
        }
    }
    
    TEST_CASE("中文映射功能") {
        SUBCASE("天干中文映射") {
            CHECK(Mapper::to_zh(TianGan::Jia) == "甲");
            CHECK(Mapper::to_zh(TianGan::Yi) == "乙");
            CHECK(Mapper::to_zh(TianGan::Bing) == "丙");
            CHECK(Mapper::to_zh(TianGan::Ding) == "丁");
            CHECK(Mapper::to_zh(TianGan::Wu) == "戊");
            CHECK(Mapper::to_zh(TianGan::Ji) == "己");
            CHECK(Mapper::to_zh(TianGan::Geng) == "庚");
            CHECK(Mapper::to_zh(TianGan::Xin) == "辛");
            CHECK(Mapper::to_zh(TianGan::Ren) == "壬");
            CHECK(Mapper::to_zh(TianGan::Gui) == "癸");
        }
        
        SUBCASE("地支中文映射") {
            CHECK(Mapper::to_zh(DiZhi::Zi) == "子");
            CHECK(Mapper::to_zh(DiZhi::Chou) == "丑");
            CHECK(Mapper::to_zh(DiZhi::Yin) == "寅");
            CHECK(Mapper::to_zh(DiZhi::Mao) == "卯");
            CHECK(Mapper::to_zh(DiZhi::Chen) == "辰");
            CHECK(Mapper::to_zh(DiZhi::Si) == "巳");
            CHECK(Mapper::to_zh(DiZhi::Wu) == "午");
            CHECK(Mapper::to_zh(DiZhi::Wei) == "未");
            CHECK(Mapper::to_zh(DiZhi::Shen) == "申");
            CHECK(Mapper::to_zh(DiZhi::You) == "酉");
            CHECK(Mapper::to_zh(DiZhi::Xu) == "戌");
            CHECK(Mapper::to_zh(DiZhi::Hai) == "亥");
        }
        
        SUBCASE("反向映射 - 天干") {
            auto result = Mapper::from_zh_gan("甲");
            REQUIRE(result.has_value());
            CHECK(result.value() == TianGan::Jia);
        }
        
        SUBCASE("反向映射 - 地支") {
            auto result = Mapper::from_zh_zhi("子");
            REQUIRE(result.has_value());
            CHECK(result.value() == DiZhi::Zi);
        }
        
        SUBCASE("无效映射") {
            auto result1 = Mapper::from_zh_gan("无效");
            CHECK_FALSE(result1.has_value());
            
            auto result2 = Mapper::from_zh_zhi("无效");
            CHECK_FALSE(result2.has_value());
        }
    }
    
    TEST_CASE("地支关系测试") {
        SUBCASE("六冲关系") {
            CHECK(is_chong(DiZhi::Zi, DiZhi::Wu));   // 子午冲
            CHECK(is_chong(DiZhi::Chou, DiZhi::Wei)); // 丑未冲
            CHECK(is_chong(DiZhi::Yin, DiZhi::Shen)); // 寅申冲
            CHECK(is_chong(DiZhi::Mao, DiZhi::You));  // 卯酉冲
            CHECK(is_chong(DiZhi::Chen, DiZhi::Xu));  // 辰戌冲
            CHECK(is_chong(DiZhi::Si, DiZhi::Hai));   // 巳亥冲
            
            // 非冲关系
            CHECK_FALSE(is_chong(DiZhi::Zi, DiZhi::Zi));
            CHECK_FALSE(is_chong(DiZhi::Zi, DiZhi::Chou));
        }
        
        SUBCASE("六合关系") {
            CHECK(is_he(DiZhi::Zi, DiZhi::Chou));  // 子丑合
            CHECK(is_he(DiZhi::Yin, DiZhi::Hai));  // 寅亥合
            CHECK(is_he(DiZhi::Mao, DiZhi::Xu));   // 卯戌合
            CHECK(is_he(DiZhi::Chen, DiZhi::You)); // 辰酉合
            CHECK(is_he(DiZhi::Si, DiZhi::Shen));  // 巳申合
            CHECK(is_he(DiZhi::Wu, DiZhi::Wei));   // 午未合
        }
        
        SUBCASE("相刑关系") {
            CHECK(is_xing(DiZhi::Zi, DiZhi::Mao));  // 子卯刑
            CHECK(is_xing(DiZhi::Yin, DiZhi::Si));  // 寅巳刑
            CHECK(is_xing(DiZhi::Chou, DiZhi::Xu)); // 丑戌刑
            
            // 自刑
            CHECK(is_xing(DiZhi::Chen, DiZhi::Chen)); // 辰辰自刑
            CHECK(is_xing(DiZhi::Wu, DiZhi::Wu));     // 午午自刑
            CHECK(is_xing(DiZhi::You, DiZhi::You));   // 酉酉自刑
            CHECK(is_xing(DiZhi::Hai, DiZhi::Hai));   // 亥亥自刑
        }
        
        SUBCASE("六害关系") {
            CHECK(is_hai(DiZhi::Zi, DiZhi::Wei));   // 子未害
            CHECK(is_hai(DiZhi::Chou, DiZhi::Wu));  // 丑午害
            CHECK(is_hai(DiZhi::Yin, DiZhi::Si));   // 寅巳害
            CHECK(is_hai(DiZhi::Mao, DiZhi::Chen)); // 卯辰害
            CHECK(is_hai(DiZhi::Shen, DiZhi::Hai)); // 申亥害
            CHECK(is_hai(DiZhi::You, DiZhi::Xu));   // 酉戌害
        }
    }
    
    TEST_CASE("十二长生测试") {
        SUBCASE("长生位置") {
            CHECK(is_chang_sheng(TianGan::Jia, DiZhi::Hai));
            CHECK(is_chang_sheng(TianGan::Bing, DiZhi::Yin));
            CHECK(is_chang_sheng(TianGan::Geng, DiZhi::Si));
        }
        
        SUBCASE("帝旺位置") {
            CHECK(is_di_wang(TianGan::Jia, DiZhi::Mao));
            CHECK(is_di_wang(TianGan::Bing, DiZhi::Wu));
            CHECK(is_di_wang(TianGan::Geng, DiZhi::You));
        }
        
        SUBCASE("墓库位置") {
            CHECK(is_mu_ku(TianGan::Jia, DiZhi::Wei));
            CHECK(is_mu_ku(TianGan::Bing, DiZhi::Xu));
            CHECK(is_mu_ku(TianGan::Geng, DiZhi::Chou));
        }
        
        SUBCASE("绝地位置") {
            CHECK(is_jue_di(TianGan::Jia, DiZhi::Shen));
            CHECK(is_jue_di(TianGan::Bing, DiZhi::Hai));
            CHECK(is_jue_di(TianGan::Geng, DiZhi::Yin));
        }
        
        SUBCASE("获取特定位置地支") {
            CHECK(get_chang_sheng_zhi(TianGan::Jia) == DiZhi::Hai);
            CHECK(get_di_wang_zhi(TianGan::Jia) == DiZhi::Mao);
            CHECK(get_mu_ku_zhi(TianGan::Jia) == DiZhi::Wei);
        }
        
        SUBCASE("完整长生状态") {
            auto cs = get_shi_er_chang_sheng(TianGan::Jia, DiZhi::Hai);
            CHECK(cs == ShiErChangSheng::ChangSheng);
            
            cs = get_shi_er_chang_sheng(TianGan::Jia, DiZhi::Mao);
            CHECK(cs == ShiErChangSheng::DiWang);
            
            cs = get_shi_er_chang_sheng(TianGan::Jia, DiZhi::Wei);
            CHECK(cs == ShiErChangSheng::Mu);
        }
    }
}

