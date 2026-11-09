"""Independent SPK reader fixtures from jplephem 2.24 and the original DE440s."""
import json
import pathlib
import random
from jplephem.spk import SPK

root=pathlib.Path(__file__).resolve().parents[2]
kernel=SPK.open(str(root/"assets/ephemeris/de440s.bsp"))
targets=[s.target for s in kernel.segments]
segments={s.target:s for s in kernel.segments}
def state(target,days):
    segment=segments[target]
    p,v=segment.compute_and_differentiate(2451545.0,days)
    if segment.center:
        cp,cv=state(segment.center,days)
        p=p+cp
        v=v+cv*86400
    return p,v/86400
first=kernel.segments[0].start_jd-2451545.0
last=kernel.segments[0].end_jd-2451545.0
times=[first,last,0,-32,32,32-1e-8,32+1e-8,-1e-8,1e-8]
randomizer=random.Random(928311)
times += [randomizer.uniform(first,last) for _ in range(128)]
rows=[]
for days in times:
    for target in targets:
        p,v=state(target,days)
        rows.append(dict(target=target,days=days,position=list(p),velocity=list(v)))
output=dict(reference="jplephem 2.24",kernel="de440s.bsp",rows=rows)
(root/"tests/reference/jpl_states.json").write_text(json.dumps(output,indent=2)+"\n",encoding="utf-8")
print(f"Generated {len(rows)} independent position/velocity records")
