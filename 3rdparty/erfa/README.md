# ERFA orientation routines

Sources from ERFA v2.0.1, commit `9915ba38c9365f8b0738269b8c2ac1fdd5f8dee3`.
Upstream https://github.com/liberfa/erfa/tree/v2.0.1

This subset contains `eraGst06a`, `eraNut06a`, `eraObl06` and their dependency closure. Original `.c` files are renamed `.cpp` for the C++ project. Source bodies and their copyright notices are retained. Complete redistribution terms are in `LICENSE`.

The routines implement IAU 2006 precession and IAU 2000A nutation for the chart axes. The subset also includes TT/TDB and apparent-place helpers used by the JPL backend. Astronomy Engine remains available as the lightweight planetary ephemeris backend.
