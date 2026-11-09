import std;
import ZhouYi.Astrology;
import ZhouYi.Astrology.Presenter;
int main(int argc, char **argv) {
  using namespace ZhouYi::Astrology;
  if (argc > 2)
    return 2;
  ChartRequest birth{civil_to_julian(1990, 7, 15, 10, 30, 0, 60), -0.1276,
                     51.5074};
  if (argc == 2)
    birth.ephemeris_file = argv[1];
  const auto natal = calculate_chart(birth);
  ChartRequest partner_request{civil_to_julian(1992, 6, 15, 14, 30, 0, 480),
                               121.47, 31.23};
  partner_request.ephemeris_file = birth.ephemeris_file;
  const auto partner = calculate_chart(partner_request);
  const auto rules = default_aspect_rules();
  Presenter::write_zh(std::cout, natal, natal_aspects(natal, rules));
  Presenter::write_zh(std::cout, compare_charts(natal, partner, rules));
  auto transit_request = birth;
  transit_request.julian_day_utc = civil_to_julian(2026, 11, 9, 12, 0, 0, 0);
  auto transit = calculate_chart(transit_request);
  transit.method = "行运盘（与出生盘比较）";
  Presenter::write_zh(std::cout, compare_charts(natal, transit, rules, true));
  for (const auto &chart :
       {composite_chart(natal, partner),
        secondary_progression(birth, transit_request.julian_day_utc),
        solar_arc_progression(birth, transit_request.julian_day_utc),
        return_chart(birth, Body::Sun, transit_request.julian_day_utc, -0.1276,
                     51.5074),
        return_chart(birth, Body::Moon, transit_request.julian_day_utc, -0.1276,
                     51.5074)})
    Presenter::write_zh(std::cout, chart, natal_aspects(chart, rules));
}
