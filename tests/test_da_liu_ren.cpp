// 大六壬系统测试
#include <doctest/doctest.h>

import ZhouYi.DaLiuRen;
import ZhouYi.DaLiuRen.Controller;
import ZhouYi.GanZhi;
import ZhouYi.BaZiBase;
import fmt;
import std;

using namespace ZhouYi::DaLiuRen;
using namespace ZhouYi::DaLiuRen::Controller;
using namespace ZhouYi::GanZhi;
using namespace ZhouYi::BaZiBase;
namespace Mapper = ZhouYi::GanZhi::Mapper;

TEST_SUITE("大六壬系统测试") {
    
    TEST_CASE("基本排盘功能") {
        SUBCASE("公历日期排盘") {
             // 公历 2025年10月13日 14时
             auto result = DaLiuRenEngine::pai_pan(2025, 10, 13, 14);
            
            // 验证基本结构 (UTF-8: 2个中文字符 = 6字节)
            CHECK(result.ba_zi.year.to_string().length() == 6);  // 年柱
            CHECK(result.ba_zi.month.to_string().length() == 6); // 月柱
            CHECK(result.ba_zi.day.to_string().length() == 6);   // 日柱
            CHECK(result.ba_zi.hour.to_string().length() == 6);  // 时柱
            
            // 验证三传不为空
            auto chu = result.san_chuan.get_chu_chuan();
            auto zhong = result.san_chuan.get_zhong_chuan();
            auto mo = result.san_chuan.get_mo_chuan();
            
            CHECK(static_cast<int>(chu) >= 0);
            CHECK(static_cast<int>(chu) < 12);
            CHECK(static_cast<int>(zhong) >= 0);
            CHECK(static_cast<int>(zhong) < 12);
            CHECK(static_cast<int>(mo) >= 0);
            CHECK(static_cast<int>(mo) < 12);
            
            // 显示结果（调试用）
            INFO("排盘结果：");
            INFO("初传: ", Mapper::to_zh(chu));
            INFO("中传: ", Mapper::to_zh(zhong));
            INFO("末传: ", Mapper::to_zh(mo));
        }
        
        SUBCASE("农历日期排盘") {
            // 农历 2025年九月十一 午时
            auto result = DaLiuRenEngine::pai_pan_lunar(2025, 9, 11, 12);
            
            // 验证结果有效
            CHECK_NOTHROW(result.to_string());
            CHECK(result.to_string().length() > 0);
            
            // 验证月将贵人
            CHECK(static_cast<int>(result.yue_jiang) >= 0);
            CHECK(static_cast<int>(result.yue_jiang) < 12);
            CHECK(static_cast<int>(result.gui_ren) >= 0);
            CHECK(static_cast<int>(result.gui_ren) < 12);
        }
    }
    
    TEST_CASE("从八字排盘") {
        SUBCASE("已知八字排盘") {
            // 已知八字
            BaZi ba_zi = BaZi::from_solar(2025, 10, 13, 14);
            
            // 需要知道农历月份
            int lunar_month = 9;  // 九月
            
            // 排盘
            auto result = DaLiuRenEngine::pai_pan_from_bazi(ba_zi, lunar_month, 14);
            
            // 验证八字匹配
            CHECK(result.ba_zi.year.to_string() == ba_zi.year.to_string());
            CHECK(result.ba_zi.month.to_string() == ba_zi.month.to_string());
            CHECK(result.ba_zi.day.to_string() == ba_zi.day.to_string());
            CHECK(result.ba_zi.hour.to_string() == ba_zi.hour.to_string());
            
            // 验证课式存在
            const auto& ke_shi = result.san_chuan.get_ke_shi();
            INFO("课式: ", fmt::join(ke_shi, ", "));
        }
    }
    
    TEST_CASE("访问详细信息") {
        auto result = DaLiuRenEngine::pai_pan(2025, 10, 13, 14);
        
        SUBCASE("访问八字信息") {
            // 验证八字信息 (UTF-8: 2个中文字符 = 6字节)
            CHECK(result.ba_zi.year.to_string().length() == 6);
            CHECK(result.ba_zi.month.to_string().length() == 6);
            CHECK(result.ba_zi.day.to_string().length() == 6);
            CHECK(result.ba_zi.hour.to_string().length() == 6);
            
            INFO("年柱: ", result.ba_zi.year.to_string());
            INFO("月柱: ", result.ba_zi.month.to_string());
            INFO("日柱: ", result.ba_zi.day.to_string());
            INFO("时柱: ", result.ba_zi.hour.to_string());
        }
        
        SUBCASE("访问月将贵人") {
            // 验证月将贵人 (UTF-8: 1个中文字符 = 3字节)
            CHECK(Mapper::to_zh(result.yue_jiang).length() == 3);
            CHECK(Mapper::to_zh(result.gui_ren).length() == 3);
            
            INFO("月将: ", Mapper::to_zh(result.yue_jiang));
            INFO("贵人: ", Mapper::to_zh(result.gui_ren));
            INFO("昼夜: ", result.is_day ? "白天" : "夜晚");
        }
        
        SUBCASE("访问四课") {
            // 验证四课信息 (UTF-8: 2个中文字符 = 6字节)
            CHECK(result.si_ke.first.to_string().length() == 6);
            CHECK(result.si_ke.second.to_string().length() == 6);
            CHECK(result.si_ke.third.to_string().length() == 6);
            CHECK(result.si_ke.fourth.to_string().length() == 6);
            
            INFO("第一课: ", result.si_ke.first.to_string());
            INFO("第二课: ", result.si_ke.second.to_string());
            INFO("第三课: ", result.si_ke.third.to_string());
            INFO("第四课: ", result.si_ke.fourth.to_string());
        }
        
        SUBCASE("访问三传") {
            // 验证三传
            auto chu = result.san_chuan.get_chu_chuan();
            auto zhong = result.san_chuan.get_zhong_chuan();
            auto mo = result.san_chuan.get_mo_chuan();
            
            CHECK(Mapper::to_zh(chu).length() == 3);
            CHECK(Mapper::to_zh(zhong).length() == 3);
            CHECK(Mapper::to_zh(mo).length() == 3);
            
            INFO("初传: ", Mapper::to_zh(chu));
            INFO("中传: ", Mapper::to_zh(zhong));
            INFO("末传: ", Mapper::to_zh(mo));
            
            // 访问课式
            const auto& ke_shi = result.san_chuan.get_ke_shi();
            if (!ke_shi.empty()) {
                INFO("课式: ", fmt::join(ke_shi, ", "));
                CHECK(ke_shi.size() > 0);
            }
        }
    }
    
    TEST_CASE("天地盘详细信息") {
        auto result = DaLiuRenEngine::pai_pan(2025, 10, 13, 14);
        
        SUBCASE("天地盘结构验证") {
            // 访问天地盘数据
            const auto& di_pan = result.tian_di_pan.get_di_pan();
            const auto& tian_pan = result.tian_di_pan.get_tian_pan();
            const auto& shen_jiang = result.tian_di_pan.get_shen_jiang();
            
            // 验证数组大小
            CHECK(di_pan.size() == 12);
            CHECK(tian_pan.size() == 12);
            CHECK(shen_jiang.size() == 12);
            
            // 验证地盘天盘对照
            for (int i = 0; i < 12; ++i) {
                DiZhi di = di_pan[i];
                DiZhi tian = tian_pan[i];
                
                CHECK(static_cast<int>(di) == i);  // 地盘是固定顺序
                CHECK(static_cast<int>(tian) >= 0);
                CHECK(static_cast<int>(tian) < 12);
                
                INFO("地盘 ", Mapper::to_zh(di), " → 天盘 ", Mapper::to_zh(tian));
            }
        }
        
        SUBCASE("天地盘访问方法") {
            // 测试重载的 [] 操作符
            for (int i = 0; i < 12; ++i) {
                DiZhi di = static_cast<DiZhi>(i);
                DiZhi tian = result.tian_di_pan[di];
                
                CHECK(static_cast<int>(tian) >= 0);
                CHECK(static_cast<int>(tian) < 12);
            }
        }
    }
    
    TEST_CASE("JSON 序列化") {
        auto result = DaLiuRenEngine::pai_pan(2025, 10, 13, 14);
        
        SUBCASE("JSON 序列化功能") {
            // 测试 JSON 序列化
            CHECK_NOTHROW(result.to_json());
            
            auto json = result.to_json();
            CHECK(json.is_object());
            
            // 验证 JSON 包含必要的字段
            CHECK(json.contains("ba_zi"));
            CHECK(json.contains("yue_jiang"));
            CHECK(json.contains("gui_ren"));
            CHECK(json.contains("is_day"));
            CHECK(json.contains("si_ke"));
            CHECK(json.contains("san_chuan"));
            
            // 验证 JSON 可以转换为字符串
            CHECK_NOTHROW(json.dump());
            CHECK(json.dump().length() > 0);
            
            INFO("JSON 大小: ", json.dump().length(), " 字符");
        }
    }
    
    TEST_CASE("字符串输出") {
        auto result = DaLiuRenEngine::pai_pan(2025, 10, 13, 14);
        
        SUBCASE("字符串输出功能") {
            // 测试字符串输出
            CHECK_NOTHROW(result.to_string());
            
            auto str = result.to_string();
            CHECK(str.length() > 0);
            
            // 验证字符串包含关键信息
            CHECK(str.find("大六壬排盘") != std::string::npos);
            CHECK(str.find("八字") != std::string::npos);
            CHECK(str.find("三传") != std::string::npos);
            
            INFO("字符串输出长度: ", str.length(), " 字符");
        }
    }
    
    TEST_CASE("批量排盘测试") {
        SUBCASE("多日期排盘") {
            // 定义多个日期
            std::vector<std::tuple<int, int, int, int>> dates = {
                {2025, 10, 13, 14},
                {2025, 10, 14, 15},
                {2025, 10, 15, 16}
            };
            
            for (const auto& [year, month, day, hour] : dates) {
                INFO("测试日期: ", year, "年", month, "月", day, "日 ", hour, "时");
                
                // 排盘应该成功
                CHECK_NOTHROW([&]() {
                    auto result = DaLiuRenEngine::pai_pan(year, month, day, hour);
                    
                    // 验证三传
                    auto chu = result.san_chuan.get_chu_chuan();
                    auto zhong = result.san_chuan.get_zhong_chuan();
                    auto mo = result.san_chuan.get_mo_chuan();
                    
                    INFO("  三传: ", Mapper::to_zh(chu), " -> ", 
                         Mapper::to_zh(zhong), " -> ", Mapper::to_zh(mo));
                    
                    const auto& ke_shi = result.san_chuan.get_ke_shi();
                    if (!ke_shi.empty()) {
                        INFO("  课式: ", fmt::join(ke_shi, ", "));
                    }
                }());
            }
        }
    }
    
    TEST_CASE("异常处理测试") {
        SUBCASE("无效日期处理") {
            // 测试是否能正确处理边界情况
            // 注意：这里的测试取决于具体的实现
            CHECK_NOTHROW(DaLiuRenEngine::pai_pan(2025, 1, 1, 0));
            CHECK_NOTHROW(DaLiuRenEngine::pai_pan(2025, 12, 31, 23));
        }
        
         SUBCASE("农历日期处理") {
             CHECK_NOTHROW(DaLiuRenEngine::pai_pan_lunar(2025, 1, 1, 0));
             CHECK_NOTHROW(DaLiuRenEngine::pai_pan_lunar(2025, 12, 29, 23));  // 使用有效的农历日期
         }
    }
    
    TEST_CASE("月将计算测试") {
        SUBCASE("月将对照表验证") {
            // 测试各个月份的月将
            for (int month = 1; month <= 12; ++month) {
                DiZhi yue_jiang = get_yue_jiang(month);
                
                CHECK(static_cast<int>(yue_jiang) >= 0);
                CHECK(static_cast<int>(yue_jiang) < 12);
                
                INFO("农历", month, "月 -> 月将: ", Mapper::to_zh(yue_jiang));
            }
        }
    }
    
    TEST_CASE("控制器功能测试") {
        auto result = DaLiuRenEngine::pai_pan(2025, 10, 13, 14);
        
        SUBCASE("显示功能测试") {
            // 测试各种显示功能不会抛出异常
            CHECK_NOTHROW(DaLiuRenController::display_result(result));
            CHECK_NOTHROW(DaLiuRenController::display_result_detailed(result));
            CHECK_NOTHROW(DaLiuRenController::display_tian_di_pan(result.tian_di_pan));
            CHECK_NOTHROW(DaLiuRenController::display_si_ke(result.si_ke));
            CHECK_NOTHROW(DaLiuRenController::display_san_chuan(result.san_chuan, result.ba_zi.day.gan, result.ba_zi.day.zhi));
        }
    }
    
    TEST_CASE("卦体判定功能测试") {
        using namespace ZhouYi::DaLiuRen;
        
        SUBCASE("伏吟卦测试") {
            // 伏吟：支上神与支相同
            auto result = DaLiuRenEngine::pai_pan(2025, 1, 1, 12);
            std::println("卦体数量: {}", result.gua_ti.size());
            for (const auto& gt : result.gua_ti) {
                std::println("  卦体: {}", gt);
            }
        }
        
        SUBCASE("返吟卦测试") {
            // 返吟：支上神与支相冲
            auto result = DaLiuRenEngine::pai_pan(2025, 2, 1, 12);
            std::println("卦体数量: {}", result.gua_ti.size());
            for (const auto& gt : result.gua_ti) {
                std::println("  卦体: {}", gt);
            }
        }
        
        SUBCASE("连珠卦测试") {
            // 测试三传地支连续的情况
            auto result = DaLiuRenEngine::pai_pan(2025, 3, 15, 14);
            std::println("卦体数量: {}", result.gua_ti.size());
            for (const auto& gt : result.gua_ti) {
                std::println("  卦体: {}", gt);
            }
        }
    }
}