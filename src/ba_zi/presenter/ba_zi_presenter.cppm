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

/**
 * @brief 生成基本排盘、藏干、旬空与大运摘要的中文文本。
 *
 * 内容与 display_result 写出的完全一致，供需要把排盘交给下游（控制器、适配器、
 * 大模型提示词、报告文件）的调用方使用；不必为了拿字符串去重定向 stdout。
 */
std::string to_zh(const BaZiResult &result);

/**
 * @brief 将基本排盘、藏干、旬空与大运摘要写入输出流。
 * @param output 目标流。
 * @param result 排盘结果。
 */
void write_zh(std::ostream &output, const BaZiResult &result);

/** @brief 输出基本排盘、藏干、旬空与大运摘要（等价于把 to_zh 写到 stdout）。 */
void display_result(const BaZiResult &result);

/** @brief 输出指定数量的大运。 */
void display_da_yun(const BaZiResult &result, int max_count = 10);

/** @brief 生成大运表文本（干支、年龄、年份、天干/地支十神）。 */
std::string to_zh_da_yun(const BaZiResult &result, int max_count = 10);

/** @brief 将大运表写入输出流。 */
void write_zh_da_yun(std::ostream &output, const BaZiResult &result,
                     int max_count = 10);

/** @brief 从指定年份开始输出流年。 */
void display_liu_nian(const BaZiResult &result, int start_year, int count = 10);

/** @brief 生成流年表文本（年份、干支、年龄、天干/地支十神）。 */
std::string to_zh_liu_nian(const BaZiResult &result, int start_year,
                           int count = 10);

/** @brief 将流年表写入输出流。 */
void write_zh_liu_nian(std::ostream &output, const BaZiResult &result,
                       int start_year, int count = 10);

/** @brief 输出指定公历年的节气流月。 */
void display_liu_yue(const BaZiResult &result, int year);

/** @brief 生成流月表文本（农历月份、干支、公历起始日期、天干/地支十神）。 */
std::string to_zh_liu_yue(const BaZiResult &result, int year);

/** @brief 将流月表写入输出流。 */
void write_zh_liu_yue(std::ostream &output, const BaZiResult &result, int year);

/** @brief 输出指定公历月的流日。 */
void display_liu_ri(const BaZiResult &result, int year, int month,
                    int day_count = 30);

/** @brief 生成流日表文本（公历日期、干支、天干/地支十神）。 */
std::string to_zh_liu_ri(const BaZiResult &result, int year, int month,
                         int day_count = 30);

/** @brief 将流日表写入输出流。 */
void write_zh_liu_ri(std::ostream &output, const BaZiResult &result, int year,
                     int month, int day_count = 30);

/** @brief 输出童限与精确起运信息。 */
void display_child_limit_detail(const BaZiResult &result);

/** @brief 生成童限与精确起运信息文本（起运年龄、精确年月日时分、出生/起运时刻）。 */
std::string to_zh_child_limit_detail(const BaZiResult &result);

/** @brief 将童限与精确起运信息写入输出流。 */
void write_zh_child_limit_detail(std::ostream &output,
                                 const BaZiResult &result);

/** @brief 输出 tyme 提供的单步大运明细。 */
void display_tyme_decade_fortune(const BaZiResult &result, int index);

/** @brief 生成 tyme 口径的单步大运明细文本。 */
std::string to_zh_tyme_decade_fortune(const BaZiResult &result, int index);

/** @brief 将 tyme 口径的单步大运明细写入输出流。 */
void write_zh_tyme_decade_fortune(std::ostream &output,
                                  const BaZiResult &result, int index);

} // namespace ZhouYi::BaZiPresenter
