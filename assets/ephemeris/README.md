# JPL DE440s

原始文件来自 NASA/JPL NAIF 公布的 DE440s，大小 32,726,016 字节。

下载地址 https://naif.jpl.nasa.gov/pub/naif/generic_kernels/spk/planets/de440s.bsp

SHA-256 为 `c1c7feeab882263fc493a9d5a5b2ddd71b54826cdf65d8d17a76126b260a49f2`。

运行 `./tools/fetch_de440s.ps1` 可下载或核验固定版本。原始文件头与注释区保留 JPL 的数据说明。数据覆盖约 1849—2150 年，现代西占入口继续使用 1900—2100 年的契约范围。

说明 https://ssd.jpl.nasa.gov/planets/eph_export.html

数据访问说明 https://naif.jpl.nasa.gov/naif/public.html

解析器实现 DAF/SPK Type 2，采用切比雪夫多项式并解析中心链，输出 J2000/ICRF 几何质心状态。视位置按 TDB、光行时、太阳引力偏折、光行差和 IAU 2006/2000A 坐标转换计算。使用该后端时，`ChartRequest.ephemeris_file` 指定本文件路径。
