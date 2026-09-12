// 紫微斗数星曜亮度流派模块（实现）
module ZhouYi.ZiWei.Brightness;

import std;
import ZhouYi.ZiWei.Constants;

namespace ZhouYi::ZiWei {
namespace {

using LD = LiangDu;

constexpr std::array<LD, 12> all_normal() {
  return {LD::Ping, LD::Ping, LD::Ping, LD::Ping, LD::Ping, LD::Ping,
          LD::Ping, LD::Ping, LD::Ping, LD::Ping, LD::Ping, LD::Ping};
}

constexpr std::array<std::optional<LD>, 12> all_undefined() { return {}; }

std::array<LD, 12> get_metis_major_table(ZhuXing star) {
  switch (star) {
  case ZhuXing::ZiWei:
    return {LD::Miao, LD::Wang, LD::XianYao, LD::Wang, LD::Miao,    LD::Miao,
            LD::Wang, LD::Ping, LD::Xian,    LD::Wang, LD::XianYao, LD::Miao};
  case ZhuXing::TianJi:
    return {LD::Wang, LD::Wang, LD::Wang, LD::Ping, LD::Miao, LD::Xian,
            LD::Wang, LD::Wang, LD::Wang, LD::Ping, LD::Miao, LD::Xian};
  case ZhuXing::TaiYang:
    return {LD::Wang, LD::Miao, LD::Wang, LD::Wang, LD::Miao, LD::Wang,
            LD::Ping, LD::Ping, LD::Xian, LD::Xian, LD::Xian, LD::Xian};
  case ZhuXing::WuQu:
    return {LD::Ping, LD::Xian, LD::Miao, LD::Ping, LD::Wang, LD::Miao,
            LD::Ping, LD::Wang, LD::Miao, LD::Ping, LD::Wang, LD::Miao};
  case ZhuXing::TianTong:
    return {LD::Ping, LD::Miao, LD::Wang, LD::Miao, LD::Xian, LD::Xian,
            LD::Wang, LD::Ping, LD::Ping, LD::Miao, LD::Wang, LD::Xian};
  case ZhuXing::LianZhen:
    return {LD::Miao, LD::XianYao, LD::Wang, LD::Xian, LD::Ping, LD::Miao,
            LD::Miao, LD::Ping,    LD::Wang, LD::Xian, LD::Ping, LD::Wang};
  case ZhuXing::TianFu:
    return {LD::Miao, LD::Ping, LD::Miao, LD::Wang, LD::Wang, LD::Miao,
            LD::Wang, LD::Ping, LD::Miao, LD::Wang, LD::Wang, LD::Miao};
  case ZhuXing::TaiYin:
    return {LD::Xian, LD::Xian, LD::Xian, LD::Xian, LD::Xian, LD::Xian,
            LD::Wang, LD::Wang, LD::Miao, LD::Miao, LD::Miao, LD::Miao};
  case ZhuXing::TanLang:
    return {LD::Ping, LD::Wang, LD::Miao, LD::Xian, LD::Wang, LD::Miao,
            LD::Ping, LD::Wang, LD::Miao, LD::Xian, LD::Wang, LD::Miao};
  case ZhuXing::JuMen:
    return {LD::Miao, LD::Wang, LD::Xian, LD::Miao, LD::Wang, LD::Xian,
            LD::Miao, LD::Wang, LD::Xian, LD::Miao, LD::Wang, LD::Xian};
  case ZhuXing::TianXiang:
    return {LD::Miao, LD::Xian, LD::Wang, LD::Ping, LD::Wang, LD::Ping,
            LD::Miao, LD::Xian, LD::Xian, LD::Ping, LD::Miao, LD::Miao};
  case ZhuXing::TianLiang:
    return {LD::Miao, LD::Miao, LD::Wang, LD::Xian, LD::Miao, LD::Wang,
            LD::Xian, LD::Ping, LD::Wang, LD::Xian, LD::Miao, LD::Wang};
  case ZhuXing::QiSha:
    return {LD::Miao, LD::Xian, LD::Miao, LD::Ping, LD::Wang, LD::Wang,
            LD::Miao, LD::Ping, LD::Miao, LD::Ping, LD::Wang, LD::Wang};
  case ZhuXing::PoJun:
    return {LD::Xian, LD::Wang, LD::Wang, LD::Ping, LD::Miao, LD::Miao,
            LD::Xian, LD::Xian, LD::Wang, LD::Ping, LD::Miao, LD::Miao};
  default:
    return all_normal();
  }
}

std::array<LD, 12> get_iztro_major_table(ZhuXing star) {
  switch (star) {
  case ZhuXing::ZiWei:
    return {LD::Wang, LD::Wang, LD::De, LD::Wang, LD::Miao, LD::Miao,
            LD::Wang, LD::Wang, LD::De, LD::Wang, LD::Ping, LD::Miao};
  case ZhuXing::TianJi:
    return {LD::De, LD::Wang, LD::Li, LD::Ping, LD::Miao, LD::Xian,
            LD::De, LD::Wang, LD::Li, LD::Ping, LD::Miao, LD::Xian};
  case ZhuXing::TaiYang:
    return {LD::Wang, LD::Miao, LD::Wang, LD::Wang, LD::Wang, LD::De,
            LD::De,   LD::Ping, LD::Bu,   LD::Xian, LD::Xian, LD::Bu};
  case ZhuXing::WuQu:
    return {LD::De, LD::Li, LD::Miao, LD::Ping, LD::Wang, LD::Miao,
            LD::De, LD::Li, LD::Miao, LD::Ping, LD::Wang, LD::Miao};
  case ZhuXing::TianTong:
    return {LD::Li,   LD::Ping, LD::Ping, LD::Miao, LD::Xian, LD::Bu,
            LD::Wang, LD::Ping, LD::Ping, LD::Miao, LD::Wang, LD::Bu};
  case ZhuXing::LianZhen:
    return {LD::Miao, LD::Ping, LD::Li, LD::Xian, LD::Ping, LD::Li,
            LD::Miao, LD::Ping, LD::Li, LD::Xian, LD::Ping, LD::Li};
  case ZhuXing::TianFu:
    return {LD::Miao, LD::De,   LD::Miao, LD::De, LD::Wang, LD::Miao,
            LD::De,   LD::Wang, LD::Miao, LD::De, LD::Miao, LD::Miao};
  case ZhuXing::TaiYin:
    return {LD::Wang, LD::Xian, LD::Xian, LD::Xian, LD::Bu,   LD::Bu,
            LD::Li,   LD::Wang, LD::Wang, LD::Miao, LD::Miao, LD::Miao};
  case ZhuXing::TanLang:
    return {LD::Ping, LD::Li, LD::Miao, LD::Xian, LD::Wang, LD::Miao,
            LD::Ping, LD::Li, LD::Miao, LD::Xian, LD::Wang, LD::Miao};
  case ZhuXing::JuMen:
    return {LD::Miao, LD::Miao, LD::Xian, LD::Wang, LD::Wang, LD::Bu,
            LD::Miao, LD::Miao, LD::Xian, LD::Wang, LD::Wang, LD::Bu};
  case ZhuXing::TianXiang:
    return {LD::Miao, LD::Xian, LD::De, LD::De, LD::Miao, LD::De,
            LD::Miao, LD::Xian, LD::De, LD::De, LD::Miao, LD::Miao};
  case ZhuXing::TianLiang:
    return {LD::Miao, LD::Miao, LD::Miao, LD::Xian, LD::Miao, LD::Wang,
            LD::Xian, LD::De,   LD::Miao, LD::Xian, LD::Miao, LD::Wang};
  case ZhuXing::QiSha:
    return {LD::Miao, LD::Wang, LD::Miao, LD::Ping, LD::Wang, LD::Miao,
            LD::Miao, LD::Wang, LD::Miao, LD::Ping, LD::Wang, LD::Miao};
  case ZhuXing::PoJun:
    return {LD::De, LD::Xian, LD::Wang, LD::Ping, LD::Miao, LD::Wang,
            LD::De, LD::Xian, LD::Wang, LD::Ping, LD::Miao, LD::Wang};
  default:
    return all_normal();
  }
}

} // namespace

std::array<LiangDu, 12> get_zhu_xing_liang_du_table(ZhuXing star,
                                                    BrightnessSchool school) {
  return school == BrightnessSchool::Iztro ? get_iztro_major_table(star)
                                           : get_metis_major_table(star);
}

std::array<LiangDu, 12> get_fu_xing_liang_du_table(FuXing star,
                                                   BrightnessSchool school) {
  if (school == BrightnessSchool::Iztro) {
    switch (star) {
    case FuXing::WenChang:
      return {LD::Xian, LD::Li,   LD::De,   LD::Miao, LD::Xian, LD::Li,
              LD::De,   LD::Miao, LD::Xian, LD::Li,   LD::De,   LD::Miao};
    case FuXing::WenQu:
      return {LD::Ping, LD::Wang, LD::De,   LD::Miao, LD::Xian, LD::Wang,
              LD::De,   LD::Miao, LD::Xian, LD::Wang, LD::De,   LD::Miao};
    default:
      return all_normal();
    }
  }

  switch (star) {
  case FuXing::ZuoFu:
  case FuXing::YouBi:
    return {LD::Wang, LD::Miao, LD::Miao, LD::Wang, LD::Wang, LD::Miao,
            LD::Wang, LD::Wang, LD::Miao, LD::Wang, LD::Wang, LD::Miao};
  case FuXing::WenChang:
    return {LD::Xian, LD::Ping, LD::Wang, LD::Miao, LD::XianYao, LD::Ping,
            LD::Wang, LD::Miao, LD::Xian, LD::Ping, LD::Wang,    LD::Miao};
  case FuXing::WenQu:
    return {LD::Xian, LD::Ping, LD::Wang, LD::Miao, LD::Ping, LD::Wang,
            LD::Wang, LD::Miao, LD::Xian, LD::Ping, LD::Miao, LD::Wang};
  case FuXing::TianKui:
    return {LD::Miao, LD::Miao, LD::Ping, LD::Ping, LD::Ping, LD::Ping,
            LD::Ping, LD::Ping, LD::Ping, LD::Miao, LD::Miao, LD::Wang};
  case FuXing::TianYue:
    return {LD::Ping, LD::Ping, LD::Ping, LD::Miao, LD::Miao, LD::Wang,
            LD::Miao, LD::Miao, LD::Ping, LD::Ping, LD::Ping, LD::Ping};
  case FuXing::LuCun:
    return {LD::Miao, LD::Wang, LD::Ping, LD::Miao, LD::Wang, LD::Ping,
            LD::Miao, LD::Wang, LD::Ping, LD::Miao, LD::Wang, LD::Ping};
  default:
    return all_normal();
  }
}

std::array<LiangDu, 12> get_sha_xing_liang_du_table(ShaXing star,
                                                    BrightnessSchool school) {
  if (school == BrightnessSchool::Iztro) {
    switch (star) {
    case ShaXing::HuoXing:
    case ShaXing::LingXing:
      return {LD::Miao, LD::Li, LD::Xian, LD::De, LD::Miao, LD::Li,
              LD::Xian, LD::De, LD::Miao, LD::Li, LD::Xian, LD::De};
    case ShaXing::QingYang:
      return {LD::Ping, LD::Xian, LD::Miao, LD::Ping, LD::Xian, LD::Miao,
              LD::Ping, LD::Xian, LD::Miao, LD::Ping, LD::Xian, LD::Miao};
    case ShaXing::TuoLuo:
      return {LD::Xian, LD::Ping, LD::Miao, LD::Xian, LD::Ping, LD::Miao,
              LD::Xian, LD::Ping, LD::Miao, LD::Xian, LD::Ping, LD::Miao};
    default:
      return all_normal();
    }
  }

  switch (star) {
  case ShaXing::QingYang:
    return {LD::Ping, LD::Xian, LD::Miao, LD::Ping, LD::Xian, LD::Miao,
            LD::Ping, LD::Xian, LD::Miao, LD::Ping, LD::Xian, LD::Miao};
  case ShaXing::TuoLuo:
    return {LD::Xian, LD::Ping, LD::Miao, LD::Xian, LD::Ping, LD::Miao,
            LD::Xian, LD::Ping, LD::Miao, LD::Xian, LD::Ping, LD::Miao};
  case ShaXing::HuoXing:
  case ShaXing::LingXing:
    return {LD::Miao, LD::Wang, LD::Xian, LD::Ping, LD::Miao, LD::Wang,
            LD::Xian, LD::Ping, LD::Miao, LD::Wang, LD::Xian, LD::Ping};
  case ShaXing::DiKong:
    return {LD::Xian, LD::Ping, LD::Xian, LD::Miao, LD::Miao, LD::Ping,
            LD::Miao, LD::Miao, LD::Xian, LD::Xian, LD::Ping, LD::Xian};
  case ShaXing::DiJie:
    return {LD::Ping, LD::Ping, LD::Xian, LD::XianYao, LD::Miao, LD::Ping,
            LD::Miao, LD::Ping, LD::Ping, LD::Wang,    LD::Xian, LD::Xian};
  default:
    return all_normal();
  }
}

std::array<std::optional<LiangDu>, 12>
get_za_yao_liang_du_table(ZaYao star, BrightnessSchool school) {
  if (school == BrightnessSchool::Iztro)
    return all_undefined();

  switch (star) {
  case ZaYao::HongLuan:
    return {LD::Wang, LD::Miao, LD::Miao, LD::Wang, LD::Wang, LD::Xian,
            LD::Miao, LD::Wang, LD::Xian, LD::Miao, LD::Miao, LD::Xian};
  case ZaYao::XianChi:
    return {LD::Ping, LD::Ping, LD::Ping, LD::Ping, LD::Xian, LD::Ping,
            LD::Ping, LD::Ping, LD::Ping, LD::Ping, LD::Xian, LD::Ping};
  case ZaYao::TianGuan:
    return {LD::Ping, LD::Wang, LD::Wang, LD::Wang, LD::Miao, LD::Miao,
            LD::Ping, LD::Ping, LD::Ping, LD::Wang, LD::Ping, LD::Ping};
  case ZaYao::TianFu2:
    return {LD::Wang, LD::Ping, LD::Ping, LD::Wang, LD::Ping, LD::Ping,
            LD::Miao, LD::Miao, LD::Ping, LD::Miao, LD::Ping, LD::Ping};
  case ZaYao::TianMa:
    return {LD::Wang, LD::Ping, LD::Ping, LD::Ping, LD::Ping, LD::Ping,
            LD::Wang, LD::Ping, LD::Ping, LD::Ping, LD::Ping, LD::Ping};
  case ZaYao::SanTai:
    return {LD::Ping, LD::Xian, LD::Miao, LD::Ping, LD::Wang, LD::Miao,
            LD::Wang, LD::Miao, LD::Wang, LD::Ping, LD::Ping, LD::Ping};
  case ZaYao::BaZuo:
    return {LD::Miao, LD::Ping, LD::Wang, LD::Miao, LD::Wang, LD::Ping,
            LD::Miao, LD::Miao, LD::Ping, LD::Miao, LD::Ping, LD::Ping};
  case ZaYao::EnGuang:
    return {LD::Ping, LD::Miao, LD::Miao, LD::Ping,    LD::Miao, LD::Ping,
            LD::Ping, LD::Ping, LD::Miao, LD::XianYao, LD::Ping, LD::Miao};
  case ZaYao::TianGui:
    return {LD::Ping, LD::Wang, LD::Wang, LD::Ping, LD::Miao, LD::Wang,
            LD::Xian, LD::Ping, LD::Wang, LD::Ping, LD::Ping, LD::Wang};
  case ZaYao::LongChi:
    return {LD::Ping, LD::Miao, LD::Miao, LD::Xian, LD::XianYao, LD::Ping,
            LD::Ping, LD::Miao, LD::Xian, LD::Wang, LD::Wang,    LD::Ping};
  case ZaYao::FengGe:
    return {LD::Miao,    LD::Wang, LD::Xian, LD::Miao, LD::Ping, LD::Ping,
            LD::XianYao, LD::Miao, LD::Miao, LD::Wang, LD::Miao, LD::Ping};
  case ZaYao::TianCai:
    return {LD::Miao, LD::Wang, LD::Xian, LD::Miao, LD::Wang, LD::Ping,
            LD::Miao, LD::Wang, LD::Xian, LD::Miao, LD::Wang, LD::Ping};
  case ZaYao::TianShou:
    return {LD::Wang, LD::Ping, LD::Miao, LD::Ping, LD::Ping, LD::Wang,
            LD::Wang, LD::Ping, LD::Miao, LD::Wang, LD::Ping, LD::Miao};
  case ZaYao::HuaGai:
    return {LD::Ping, LD::Ping, LD::Miao, LD::Ping, LD::Ping, LD::Xian,
            LD::Ping, LD::Ping, LD::Ping, LD::Ping, LD::Ping, LD::Xian};
  case ZaYao::TianDe:
    return {LD::Ping, LD::Ping,    LD::Miao, LD::Wang, LD::Wang, LD::Miao,
            LD::Ping, LD::XianYao, LD::Miao, LD::Ping, LD::Miao, LD::Miao};
  case ZaYao::YueDe:
    return {LD::Xian, LD::XianYao, LD::Ping, LD::Xian, LD::Wang, LD::Ping,
            LD::Xian, LD::XianYao, LD::Ping, LD::Xian, LD::Wang, LD::Ping};
  case ZaYao::GuChen:
    return {LD::Ping, LD::Ping, LD::Ping, LD::Xian, LD::Ping, LD::Ping,
            LD::Ping, LD::Ping, LD::Ping, LD::Xian, LD::Ping, LD::Ping};
  case ZaYao::GuaSu:
    return {LD::Ping, LD::Ping, LD::Xian, LD::Ping, LD::Ping, LD::XianYao,
            LD::Ping, LD::Ping, LD::Xian, LD::Ping, LD::Ping, LD::Ping};
  case ZaYao::PoSui:
    return {LD::Ping, LD::Ping, LD::Ping, LD::Xian, LD::Ping, LD::Ping,
            LD::Ping, LD::Ping, LD::Ping, LD::Ping, LD::Ping, LD::Xian};
  case ZaYao::TianKong2:
    return {LD::Xian, LD::Ping, LD::Miao, LD::Miao, LD::Miao, LD::Xian,
            LD::Wang, LD::Wang, LD::Xian, LD::Ping, LD::Xian, LD::Ping};
  case ZaYao::TianKu:
    return {LD::Ping, LD::Miao,    LD::Ping, LD::XianYao, LD::Xian, LD::Ping,
            LD::Miao, LD::XianYao, LD::Ping, LD::Ping,    LD::Ping, LD::Miao};
  case ZaYao::TianXu:
    return {LD::Wang, LD::Miao, LD::Xian, LD::Wang, LD::Ping, LD::Xian,
            LD::Miao, LD::Wang, LD::Xian, LD::Ping, LD::Xian, LD::Miao};
  case ZaYao::NianJie:
    return {LD::Miao,    LD::Miao, LD::Miao, LD::Wang, LD::Miao, LD::Ping,
            LD::XianYao, LD::Wang, LD::Miao, LD::Ping, LD::Miao, LD::Ping};
  case ZaYao::TianShi:
    return {std::nullopt, std::nullopt, LD::Xian, std::nullopt,
            LD::Ping,     LD::Ping,     LD::Ping, LD::Xian,
            LD::Xian,     std::nullopt, LD::Xian, LD::Xian};
  case ZaYao::TianShang:
    return {LD::Ping, std::nullopt, LD::Ping,     LD::Ping,
            LD::Xian, LD::Xian,     LD::Ping,     std::nullopt,
            LD::Ping, LD::Wang,     std::nullopt, std::nullopt};
  case ZaYao::FuXunKong:
    return {std::nullopt, LD::Ping, LD::Xian,     std::nullopt,
            std::nullopt, LD::Xian, std::nullopt, LD::Miao,
            LD::Xian,     LD::Ping, LD::Xian,     std::nullopt};
  case ZaYao::JieKong:
    return {std::nullopt, LD::Ping,     std::nullopt, LD::Miao,
            std::nullopt, LD::Miao,     std::nullopt, std::nullopt,
            std::nullopt, std::nullopt, std::nullopt, std::nullopt};
  default:
    return all_undefined();
  }
}

} // namespace ZhouYi::ZiWei
