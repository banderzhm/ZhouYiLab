// node tests/reference/generate_astronomy_reference.cjs /path/to/astronomy.cjs
// Astronomy Engine v2.1.19, geometric heliocentric J2000 equatorial vectors.
const fs = require('node:fs');
const path = require('node:path');
const crypto = require('node:crypto');
const referencePath = path.resolve(process.argv[2]);
const referenceHash = crypto.createHash('sha256').update(fs.readFileSync(referencePath)).digest('hex');
if (referenceHash !== '729c0ce37cc1a8096034a689039a5f04585ee8184177c638e8c74dec4fa3185a') {
    throw new Error('Reference must be unmodified Astronomy Engine v2.1.19 astronomy.js');
}
const engine = require(referencePath);
const planets = ['Mercury', 'Venus', 'Earth', 'Mars', 'Jupiter', 'Saturn', 'Uranus', 'Neptune'];
const times = [-36524.5, 0, 36890.5, -Number.EPSILON, Number.EPSILON];
for (let i = 0; i <= 1000; ++i) times.push(-36524.5 + (73415 * i) / 1000);
let seed = 0x79ac2d83;
for (let i = 0; i < 4096; ++i) {
    seed ^= seed << 13; seed ^= seed >>> 17; seed ^= seed << 5;
    times.push(-36524.5 + 73415 * (seed >>> 0) / 4294967296);
}
const rows = [];
for (const tt of times) {
    const time = engine.AstroTime.FromTerrestrialTime(tt);
    planets.forEach((planet, index) => {
        const v = engine.HelioVector(planet, time);
        rows.push([index, tt, v.x, v.y, v.z].join(' '));
    });
}
const destination = path.join(__dirname, 'astronomy_heliocentric.txt');
fs.writeFileSync(destination, rows.join('\n') + '\n');
const metadata = {version:'v2.1.19', source:'https://github.com/cosinekitty/astronomy/tree/v2.1.19', source_sha256:crypto.createHash('sha256').update(fs.readFileSync(referencePath)).digest('hex'), coordinates:'J2000 equatorial, geometric heliocentric, AU', time:'TT days since J2000', rows:rows.length, shared_coefficients:true};
fs.writeFileSync(destination + '.json', JSON.stringify(metadata, null, 2) + '\n');
console.log(metadata);
