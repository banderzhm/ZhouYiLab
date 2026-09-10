// C++23 Module - 八字展示接口
// 将排盘与分析结果转换为面向人的中文输出，不参与任何命理计算。
export module ZhouYi.BaZiPresenter;

import ZhouYi.BaZi;
import ZhouYi.BaZiAnalysis.Report;
import ZhouYi.BaZiAnalysis.ZiPing.Contract;
import std;

export namespace ZhouYi::BaZiPresenter {

using ZhouYi::BaZi::BaZiResult;
using ZhouYi::BaZiAnalysis::AnalysisResult;

/** @brief 输出完整中文分析报告。 */
void display_analysis(const AnalysisResult &analysis);

/** @brief 输出基本排盘、藏干、旬空与大运摘要。 */
void display_result(const BaZiResult &result);

/** @brief 输出指定数量的大运。 */
void display_da_yun(const BaZiResult &result, int max_count = 10);

/** @brief 从指定年份开始输出流年。 */
void display_liu_nian(const BaZiResult &result, int start_year, int count = 10);

/** @brief 输出指定公历年的节气流月。 */
void display_liu_yue(const BaZiResult &result, int year);

/** @brief 输出指定公历月的流日。 */
void display_liu_ri(const BaZiResult &result, int year, int month,
                    int day_count = 30);

/** @brief 输出童限与精确起运信息。 */
void display_child_limit_detail(const BaZiResult &result);

/** @brief 输出 tyme 提供的单步大运明细。 */
void display_tyme_decade_fortune(const BaZiResult &result, int index);

} // namespace ZhouYi::BaZiPresenter
