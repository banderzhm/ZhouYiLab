// C++23 Module - 六爻分析接口
export module ZhouYi.LiuYaoAnalysis;

export import ZhouYi.LiuYaoAnalysis.Contract;

import ZhouYi.BaZiBase;
import ZhouYi.LiuYao;
import std;

export namespace ZhouYi::LiuYaoAnalysis {

/**
 * @brief 依纳甲六爻法分析已经完成装卦的数据。
 *
 * @param yao_list 六爻排盘结果，顺序必须为初爻至上爻。
 * @param main_info 本卦卦宫资料。
 * @param changed_info 变卦资料；无动爻时为空。
 * @param bazi 起卦时四柱，其中月支为月建、日支为日辰。
 * @param request 占问门类及可选的人工取用。
 * @return 可供 JSON 或中文报告展示的结构化分析结果。
 */
AnalysisResult
analyze(const std::vector<ZhouYi::LiuYao::YaoDetails> &yao_list,
        const ZhouYi::LiuYao::HexagramInfo &main_info,
        const std::optional<ZhouYi::LiuYao::HexagramInfo> &changed_info,
        const ZhouYi::BaZiBase::BaZi &bazi,
        const AnalysisRequest &request = {});

} // namespace ZhouYi::LiuYaoAnalysis
