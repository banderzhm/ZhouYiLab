// 大六壬类神策略实现
module ZhouYi.DaLiuRenAnalysis.LeiShen;

import ZhouYi.GanZhi;
import std;

namespace ZhouYi::DaLiuRenAnalysis {
namespace {

class FixedQuestionStrategy final : public QuestionStrategy {
public:
  FixedQuestionStrategy(std::string name, std::optional<LiuQin> relative,
                        std::vector<TianJiang> generals, std::string reason)
      : name_(std::move(name)), relative_(relative),
        generals_(std::move(generals)), reason_(std::move(reason)) {}

  LeiShenSelection select(const AnalysisRequest &request) const override {
    const auto relative =
        request.specified_lei_shen ? request.specified_lei_shen : relative_;
    return LeiShenSelection{
        .name = request.specified_lei_shen ? "人工指定六亲类神" : name_,
        .primary_relative = relative,
        .supporting_generals = generals_,
        .appearances = {},
        .plate_positions = {},
        .reason = request.specified_lei_shen
                      ? "调用方明确指定六亲类神，优先于默认占类策略"
                      : reason_};
  }

private:
  std::string name_;
  std::optional<LiuQin> relative_;
  std::vector<TianJiang> generals_;
  std::string reason_;
};

} // namespace

std::unique_ptr<QuestionStrategy> make_question_strategy(QuestionKind kind) {
  using LQ = LiuQin;
  using TJ = TianJiang;
  switch (kind) {
  case QuestionKind::QiuCai:
    return std::make_unique<FixedQuestionStrategy>(
        "日财", LQ::QiCai, std::vector<TJ>{TJ::QingLong},
        "求财以日财为主类神，青龙为财喜辅助类神");
  case QuestionKind::GongMing:
    return std::make_unique<FixedQuestionStrategy>(
        "日官鬼", LQ::GuanGui, std::vector<TJ>{TJ::GuiRen, TJ::QingLong},
        "功名事业以日官鬼为主，兼察贵人与青龙");
  case QuestionKind::HunYin:
    return std::make_unique<FixedQuestionStrategy>(
        "婚姻类神", std::nullopt, std::vector<TJ>{TJ::LiuHe, TJ::TianHou},
        "婚姻男占以妻财、女占以官鬼为主，兼察六合与天后");
  case QuestionKind::JiBing:
    return std::make_unique<FixedQuestionStrategy>(
        "病神与救神", LQ::GuanGui, std::vector<TJ>{TJ::BaiHu},
        "疾病以日鬼为病，兼察白虎；子孙及天医等医药神另作救应");
  case QuestionKind::ChuXing:
    return std::make_unique<FixedQuestionStrategy>(
        "行人道路类神", std::nullopt, std::vector<TJ>{TJ::XuanWu, TJ::BaiHu},
        "出行先察驿马、道路、发用与末传，天将仅作取象佐证");
  case QuestionKind::GuanSong:
    return std::make_unique<FixedQuestionStrategy>(
        "日官鬼", LQ::GuanGui, std::vector<TJ>{TJ::GouChen, TJ::ZhuQue},
        "官讼以日鬼为事，勾陈主牵滞，朱雀主口舌文书");
  case QuestionKind::KaoShi:
    return std::make_unique<FixedQuestionStrategy>(
        "日父母", LQ::FuMu, std::vector<TJ>{TJ::ZhuQue, TJ::GuiRen},
        "考试以父母文书为主，兼察朱雀与贵人");
  case QuestionKind::XunRenShiWu:
    return std::make_unique<FixedQuestionStrategy>(
        "所寻类神", LQ::QiCai, std::vector<TJ>{TJ::XuanWu},
        "失物暂以日财为类神；寻人须由调用方按人物身份另指定六亲");
  case QuestionKind::XiaoXiWenShu:
    return std::make_unique<FixedQuestionStrategy>(
        "日父母", LQ::FuMu, std::vector<TJ>{TJ::ZhuQue},
        "消息文书以父母为体，朱雀为消息文书之象");
  case QuestionKind::FanZhan:
    return std::make_unique<FixedQuestionStrategy>(
        "未定类神", std::nullopt, std::vector<TJ>{},
        "泛占不强定类神，以日干、日支和发用为主线");
  }
  throw std::invalid_argument("不支持的大六壬占问门类");
}

} // namespace ZhouYi::DaLiuRenAnalysis
