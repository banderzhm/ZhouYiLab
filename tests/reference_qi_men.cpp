// 奇门遁甲跨实现对照数据导出器。
//
// 本程序只调用 ZhouYiLab 生产模块并输出结构化盘面，不包含参考库算法。
// 外部对照由 tests/reference/compare_qi_men.cjs 完成。

import ZhouYi.QiMen.Controller;
import nlohmann.json;
import std;

using ZhouYi::QiMen::QiMenController;
using namespace std;

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
          QiMenController::pai_pan_solar(year, month, day, hour, minute);
      if (!pan)
        throw runtime_error(pan.error());
      output.push_back({{"input", request},
                        {"pan", QiMenController::get_pan_json_object(*pan)}});
    }
    cout << output.dump();
    return 0;
  } catch (const exception &error) {
    cerr << error.what() << '\n';
    return 1;
  }
}
