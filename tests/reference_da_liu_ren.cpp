// 大六壬跨实现对照数据导出器。
//
// 本程序只调用 ZhouYiLab 生产模块并输出结构化盘面，不包含参考库算法。
// 外部对照由 tests/reference/compare_da_liu_ren.cjs 完成。

import ZhouYi.BaZiBase;
import ZhouYi.DaLiuRen;
import ZhouYi.DaLiuRen.Controller;
import ZhouYi.GanZhi;
import nlohmann.json;
import std;

using ZhouYi::DaLiuRen::DaLiuRenResult;
using ZhouYi::DaLiuRen::GanZhiKe;
using ZhouYi::DaLiuRen::Controller::DaLiuRenController;
using ZhouYi::GanZhi::DiZhi;
using namespace std;

namespace {

/** @brief 将地支转换成稳定的中文名称。 */
string zhi_zh(DiZhi value) {
  return string(ZhouYi::GanZhi::Mapper::to_zh(value));
}

/** @brief 将一课转换成便于跨语言比较的上下神结构。 */
nlohmann::json lesson_json(const GanZhiKe &lesson) {
  return {
      {"lower", lesson.is_gan_zhi
                    ? string(ZhouYi::GanZhi::Mapper::to_zh(lesson.gan))
                    : zhi_zh(lesson.lower_zhi)},
      {"upper", zhi_zh(lesson.upper_zhi)},
  };
}

/** @brief 将单个 ZhouYiLab 盘面转换成参考测试所需的最小事实集。 */
nlohmann::json pan_json(const DaLiuRenResult &pan) {
  nlohmann::json tian_pan = nlohmann::json::array();
  for (const auto branch : pan.tian_di_pan.get_tian_pan())
    tian_pan.push_back(zhi_zh(branch));

  return {
      {"day_gan", string(ZhouYi::GanZhi::Mapper::to_zh(pan.ba_zi.day.gan))},
      {"day_zhi", zhi_zh(pan.ba_zi.day.zhi)},
      {"hour_zhi", zhi_zh(pan.ba_zi.hour.zhi)},
      {"yue_jiang", zhi_zh(pan.yue_jiang)},
      {"gui_ren", zhi_zh(pan.gui_ren)},
      {"is_day", pan.is_day},
      {"tian_pan", move(tian_pan)},
      {"si_ke",
       nlohmann::json::array(
           {lesson_json(pan.si_ke.first), lesson_json(pan.si_ke.second),
            lesson_json(pan.si_ke.third), lesson_json(pan.si_ke.fourth)})},
      {"san_chuan",
       {{"chu", zhi_zh(pan.san_chuan.get_chu_chuan())},
        {"zhong", zhi_zh(pan.san_chuan.get_zhong_chuan())},
        {"mo", zhi_zh(pan.san_chuan.get_mo_chuan())},
        {"patterns", pan.san_chuan.get_ke_shi()}}},
  };
}

} // namespace

int main() {
  try {
    nlohmann::json requests;
    cin >> requests;
    if (!requests.is_array())
      throw invalid_argument("输入必须是公历日期时间对象数组");

    auto output = nlohmann::json::array();
    for (const auto &request : requests) {
      const int year = request.at("year").get<int>();
      const int month = request.at("month").get<int>();
      const int day = request.at("day").get<int>();
      const int hour = request.at("hour").get<int>();
      const int minute = request.value("minute", 0);
      const auto pan =
          DaLiuRenController::pai_pan_solar(year, month, day, hour, minute);
      output.push_back({{"input", request}, {"pan", pan_json(pan)}});
    }
    cout << output.dump();
    return 0;
  } catch (const exception &error) {
    cerr << error.what() << '\n';
    return 1;
  }
}
