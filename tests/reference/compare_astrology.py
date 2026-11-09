"""Compare the production exporter against fixed independent Kerykeion data."""
import json
import pathlib
import subprocess
import sys

root=pathlib.Path(__file__).resolve().parents[2]
fixture=json.loads((root/"tests/reference/astrology_kerykeion.json").read_text())
destination=pathlib.Path(sys.argv[2]).resolve()
destination.mkdir(parents=True,exist_ok=True)
inputs=destination/"inputs.json"
cases=[dict(c) for c in fixture["cases"]]
polar_cases=[dict(c) for c in fixture["polar_cases"]]
reference_files="--jpl" in sys.argv[3:] or "--swiss-files" in sys.argv[3:]
if "--jpl" in sys.argv[3:]:
    for case in cases+polar_cases: case["ephemeris_file"]=str(root/"assets/ephemeris/de440s.bsp")
expected=fixture["expected"]
if "--aligned-time" in sys.argv[3:]:
    context_name="astrology_jpl_reference.json" if reference_files else "astrology_time_context.json"
    contexts=json.loads((root/"tests/reference"/context_name).read_text())["contexts"]
    for case,context in zip(cases,contexts,strict=True): case["delta_t_seconds"]=context["delta_t_seconds"]
    expected=[context["expected"] for context in contexts]
inputs.write_text(json.dumps(cases+polar_cases),encoding="utf-8")
actual=json.loads(subprocess.check_output([sys.argv[1],str(inputs)],text=True))
def angle(a,b): return abs((a-b+180)%360-180)
metrics={key: {"maximum":0,"case":None} for key in ("longitude","speed","cusps","ascendant","midheaven")}
body_metrics=[dict(body=name,maximum=0,case=None) for name in ("Sun","Moon","Mercury","Venus","Mars","Jupiter","Saturn","Uranus","Neptune","Pluto")]
for index,(a,e) in enumerate(zip(actual[:len(fixture["cases"])],expected,strict=True)):
    for k,(body,value) in enumerate(zip(a["bodies"],e["longitude"],strict=True)):
        difference=angle(body["longitude"],value)
        if difference>body_metrics[k]["maximum"]: body_metrics[k].update(maximum=difference,case=index)
    values={
        "longitude":max(angle(b["longitude"],v) for b,v in zip(a["bodies"],e["longitude"],strict=True)),
        "speed":max(abs(b["speed_degrees_per_day"]-v) for b,v in zip(a["bodies"],e["speed"],strict=True)),
        "cusps":max(angle(b,v) for b,v in zip(a["cusps"],e["cusps"],strict=True)),
        "ascendant":angle(a["angles"]["ascendant"],e["ascendant"]),
        "midheaven":angle(a["angles"]["midheaven"],e["midheaven"])}
    for key,value in values.items():
        if value>metrics[key]["maximum"]: metrics[key]={"maximum":value,"case":index,"input":fixture["cases"][index]}
polar_errors=[]
for a,e in zip(actual[len(fixture["cases"]):],fixture["polar_cases"],strict=True):
    difference=max(angle(b,v) for b,v in zip(a["cusps"],e["cusps"],strict=True))
    if difference>=0.01: polar_errors.append(dict(input=e,actual=a["cusps"],difference=difference))
aligned="--aligned-time" in sys.argv[3:]
jpl="--jpl" in sys.argv[3:]
report={
    "charts":len(actual),
    "reference":"Swiss Ephemeris with ephemeris files" if reference_files and aligned else fixture["reference"],
    "version":"2.10.3.2" if reference_files and aligned else fixture["version"],
    "mode":("JPL_DE440s_" if jpl else "")+("common_delta_t_IAU2006_2000A" if aligned else "original_reference_defaults"),
    "metrics":metrics,
    "body_longitude_errors":body_metrics,
    "polar_errors":polar_errors,
}
(destination/"comparison.json").write_text(json.dumps(report,indent=2),encoding="utf-8")
print(json.dumps(report,indent=2))
