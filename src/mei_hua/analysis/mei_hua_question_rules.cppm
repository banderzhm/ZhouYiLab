/**
 * @file mei_hua_question_rules.cppm
 * @brief 梅花易数分门占问策略接口。
 */
export module ZhouYi.MeiHua.Analysis.QuestionRules;

export import ZhouYi.MeiHua.Analysis.Contract;

export namespace ZhouYi::MeiHuaAnalysis {

/** 依占问门类选择对应策略，形成专门取象与断语。 */
QuestionReading build_question_reading(const ZhouYi::MeiHua::MeiHuaPan &pan,
                                       const AnalysisRequest &request,
                                       const TrigramReading &ben_yong,
                                       const TrigramReading &bian_influence,
                                       const PartyBalance &party_balance);

} // namespace ZhouYi::MeiHuaAnalysis
