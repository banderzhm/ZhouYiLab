"""Offline independent chart fixtures. Run with kerykeion==5.12.6.

This test tool is separate from the production C++ library. Package versions and
input conventions accompany the fixed expected data. It never derives expected
values from ZhouYiLab output.
"""
import datetime as dt
import importlib.metadata
import json
import pathlib
import random
import warnings
import swisseph as swe
from kerykeion import AstrologicalSubject, AspectsFactory, CompositeSubjectFactory, PlanetaryReturnFactory

warnings.filterwarnings("ignore", category=UserWarning)
root = pathlib.Path(__file__).resolve().parents[2]
randomizer = random.Random(713822)
names = "sun moon mercury venus mars jupiter saturn uranus neptune pluto".split()
house_names = "first second third fourth fifth sixth seventh eighth ninth tenth eleventh twelfth".split()
sites = [(0.01, 0.01), (116.4, 39.9), (-74.0, 40.7), (151.2, -33.9),
         (18.4, -33.9), (179.99, 65.0), (-179.99, -65.0), (0, 66.0)]
dates = [dt.datetime(1900,1,1), dt.datetime(2000,1,1,12), dt.datetime(2000,2,29),
         dt.datetime(2024,2,29,23,59), dt.datetime(2100,12,30,23,59)]
for _ in range(95):
    dates.append(dt.datetime(1900,1,1) + dt.timedelta(minutes=randomizer.randrange(105000000)))
cases = []
expected = []
models=[]
active_aspects=[dict(name=name,orb=orb) for name,orb in
    [("conjunction",8),("semi-sextile",2),("semi-square",2),("sextile",6),("quintile",2),
     ("square",7),("trine",7),("sesquiquadrate",2),("biquintile",2),("quincunx",3),("opposition",8)]]
def export_aspects(result):
    return [dict(first=names.index(a.p1_name.lower()),second=names.index(a.p2_name.lower()),
        angle=a.aspect_degrees,orb=abs(a.orbit),applying=a.aspect_movement=="Applying",
        movement=a.aspect_movement,relative_speed=abs(a.p2_speed-a.p1_speed)) for a in result.aspects]
for index, date in enumerate(dates):
    lon, lat = sites[index % len(sites)]
    for house_index, identifier in enumerate(("W", "A", "P")):
        subject = AstrologicalSubject(name=f"case-{index}", year=date.year, month=date.month,
            day=date.day,hour=date.hour,minute=date.minute,lng=lon,lat=lat,
            tz_str="Etc/UTC",online=False,houses_system_identifier=identifier,
            zodiac_type="Tropical",disable_chiron=True)
        model = subject.model().model_dump()
        models.append(subject.model())
        if abs(model["lat"]-lat)>1e-9 or abs(model["lng"]-lon)>1e-9:
            raise ValueError("Reference changed coordinates")
        cases.append(dict(jd=model["julian_day"],lon=lon,lat=lat,houses=house_index))
        expected.append(dict(
            longitude=[model[name]["abs_pos"] for name in names],
            latitude=[swe.calc_ut(model["julian_day"],body,swe.FLG_SWIEPH|swe.FLG_SPEED)[0][1] for body in range(10)],
            distance_au=[swe.calc_ut(model["julian_day"],body,swe.FLG_SWIEPH|swe.FLG_SPEED)[0][2] for body in range(10)],
            speed=[model[name]["speed"] for name in names],
            house=[house_names.index(model[name]["house"].split("_")[0].lower())+1 for name in names],
            cusps=[model[name+"_house"]["abs_pos"] for name in house_names],
            ascendant=model["ascendant"]["abs_pos"],midheaven=model["medium_coeli"]["abs_pos"]))
        if house_index==2:
            expected[-1]["aspects"]=export_aspects(AspectsFactory.single_chart_aspects(subject.model(),
                active_points=[n.title() for n in names],active_aspects=active_aspects))
techniques=[]
for index in range(10):
    first_index=3*(index+5)+2
    second_index=3*(index+30)+2
    first,second=models[first_index],models[second_index]
    composite=CompositeSubjectFactory(first,second).get_midpoint_composite_subject_model().model_dump()
    # This reference constructor rejects zero longitude by truthiness. Only UTC
    # return instants are exported, which are independent of observer location.
    factory=PlanetaryReturnFactory(first,lng=first.lng or 0.0001,lat=first.lat,tz_str="Etc/UTC",online=False)
    start="2025-01-01T00:00:00+00:00"
    solar=factory.next_return_from_iso_formatted_time(start,"Solar")
    lunar=factory.next_return_from_iso_formatted_time(start,"Lunar")
    target=swe.julday(2025,1,1,0)
    progressed_jd=first.julian_day+(target-first.julian_day)/365.2422
    progressed=[swe.calc_ut(progressed_jd,body,swe.FLG_SWIEPH|swe.FLG_SPEED)[0][0] for body in range(10)]
    progressed_cusps=swe.houses_ex(progressed_jd,first.lat,first.lng,b"P")[0]
    arc=progressed[0]-first.sun.abs_pos
    techniques.append(dict(first=first_index,second=second_index,
        composite_longitudes=[composite[n]["abs_pos"] for n in names],
        composite_cusps=[composite[n+"_house"]["abs_pos"] for n in house_names],
        solar_return_jd=solar.julian_day,lunar_return_jd=lunar.julian_day,
        target_jd=target,progressed_longitudes=progressed,progressed_cusps=progressed_cusps,
        solar_arc_longitudes=[(first.model_dump()[n]["abs_pos"]+arc)%360 for n in names],
        synastry=export_aspects(AspectsFactory.dual_chart_aspects(first,second,
            active_points=[n.title() for n in names],active_aspects=active_aspects)),
        transits=export_aspects(AspectsFactory.dual_chart_aspects(first,second,
            active_points=[n.title() for n in names],active_aspects=active_aspects,first_subject_is_fixed=True))))
polar_cases=[]
for lat in (-80.0,-66.5,66.5,80.0):
    for jd in (2451545.0,2451635.0,2451725.0,2451815.0):
        for house_index,identifier in enumerate((b"W",b"A",b"P")):
            if abs(lat)>66.5 and house_index==2: continue
            cusps,angles=swe.houses_ex(jd,lat,179.99,identifier)
            polar_cases.append(dict(jd=jd,lat=lat,lon=179.99,houses=house_index,
                cusps=cusps,ascendant=angles[0],midheaven=angles[1]))
output = dict(reference="Kerykeion",version=importlib.metadata.version("kerykeion"),
    swisseph_version=importlib.metadata.version("pyswisseph"),
    convention="Apparent geocentric, tropical, true ecliptic of date, UTC, W/A/P houses",
    cases=cases,expected=expected,techniques=techniques,polar_cases=polar_cases)
destination = root / "tests/reference/astrology_kerykeion.json"
destination.write_text(json.dumps(output,ensure_ascii=False,indent=2)+"\n",encoding="utf-8")
print(f"Generated {len(cases)} independent charts")
