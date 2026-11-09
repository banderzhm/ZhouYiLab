"""Generate explicit time inputs independently, without changing chart expected data."""
import json
import pathlib
import ctypes
import argparse
import hashlib
import swisseph as swe

root=pathlib.Path(__file__).resolve().parents[2]
parser=argparse.ArgumentParser()
parser.add_argument("--ephe-directory",type=pathlib.Path)
parser.add_argument("--output",type=pathlib.Path,default=root/"tests/reference/astrology_time_context.json")
arguments=parser.parse_args()
if arguments.ephe_directory: swe.set_ephe_path(str(arguments.ephe_directory.resolve()))
source=json.loads((root/"tests/reference/astrology_kerykeion.json").read_text())
contexts=[]
library=ctypes.CDLL(swe.__file__)
set_models=library.swe_set_astro_models
set_models.argtypes=[ctypes.c_char_p,ctypes.c_int]
set_models.restype=None
# Swiss public model configuration: DeltaT 2016, IAU2006 precession,
# IAU2000A nutation, IAU2006 bias, IERS2010 sidereal time.
set_models(b"5,8,8,3,3,0,0,3",0)
for case in source["cases"]:
    jd=case["jd"]
    calculated=[swe.calc_ut(jd,body,swe.FLG_SWIEPH|swe.FLG_SPEED) for body in range(10)]
    if arguments.ephe_directory and any(not(flags&swe.FLG_SWIEPH) for _,flags in calculated):
        raise RuntimeError("Reference fell back from Swiss ephemeris files")
    coordinates=[p for p,_ in calculated]
    cusps,angles=swe.houses_ex(jd,case["lat"],case["lon"],(b"W",b"A",b"P")[case["houses"]])
    contexts.append(dict(delta_t_seconds=swe.deltat(jd)*86400,
        expected=dict(longitude=[c[0] for c in coordinates],latitude=[c[1] for c in coordinates],
        distance_au=[c[2] for c in coordinates],speed=[c[3] for c in coordinates],cusps=cusps,
        ascendant=angles[0],midheaven=angles[1])))
files={}
if arguments.ephe_directory:
    files={name:hashlib.sha256((arguments.ephe_directory/name).read_bytes()).hexdigest() for name in ("sepl_18.se1","semo_18.se1")}
arguments.output.write_text(json.dumps(dict(reference="pyswisseph 2.10.3.2",source="Swiss ephemeris files" if files else "Moshier fallback",files=files,models="5,8,8,3,3,0,0,3",contexts=contexts),indent=2)+"\n",encoding="utf-8")
