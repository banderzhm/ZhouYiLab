// C++23 Module - 大六壬类神策略
export module ZhouYi.DaLiuRenAnalysis.LeiShen;

export import ZhouYi.DaLiuRenAnalysis.Contract;

import std;

export namespace ZhouYi::DaLiuRenAnalysis {

/** @brief 占类策略接口；每种占问独立决定主类神和辅助天将。 */
class QuestionStrategy {
public:
  virtual ~QuestionStrategy() = default;
  /** @brief 生成当前占类的类神配置。 */
  virtual LeiShenSelection select(const AnalysisRequest &request) const = 0;
};

/** @brief 按占问门类创建类神策略。 */
std::unique_ptr<QuestionStrategy> make_question_strategy(QuestionKind kind);

} // namespace ZhouYi::DaLiuRenAnalysis
