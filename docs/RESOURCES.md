# Resource archive `r0` and standalone resources

Resource ids are packed ints (see `Resources.java`): bit31 = standalone file, bits 16-30 = archive no., bits 0-14 = table index.
Table = 92 big-endian ints at the head of `r0`; negative entry = standalone file of that (negated) length; entry 91 = archive size.

| index | kind | offset | size | content |
|---|---|---|---|---|
| 0 | in `r0` | 368 | 235 | PNG image |
| 1 | in `r0` | 603 | 211 | PNG image |
| 2 | in `r0` | 814 | 3666 | PNG image |
| 3 | in `r0` | 4480 | 205 | PNG image |
| 4 | in `r0` | 4685 | 3676 | PNG image |
| 5 | in `r0` | 8361 | 260 | PNG image |
| 6 | in `r0` | 8621 | 225 | PNG image |
| 7 | in `r0` | 8846 | 201 | PNG image |
| 8 | in `r0` | 9047 | 201 | PNG image |
| 9 | in `r0` | 9248 | 8804 | PNG image |
| 10 | in `r0` | 18052 | 170 | PNG image |
| 11 | in `r0` | 18222 | 12619 | PNG image |
| 12 | in `r0` | 30841 | 711 | PNG image |
| 13 | in `r0` | 31552 | 789 | PNG image |
| 14 | in `r0` | 32341 | 792 | PNG image |
| 15 | in `r0` | 33133 | 356 | PNG image |
| 16 | in `r0` | 33489 | 406 | PNG image |
| 17 | in `r0` | 33895 | 352 | PNG image |
| 18 | in `r0` | 34247 | 329 | PNG image |
| 19 | in `r0` | 34576 | 425 | PNG image |
| 20 | in `r0` | 35001 | 311 | PNG image |
| 21 | in `r0` | 35312 | 242 | PNG image |
| 22 | in `r0` | 35554 | 426 | PNG image |
| 23 | in `r0` | 35980 | 539 | PNG image |
| 24 | in `r0` | 36519 | 287 | PNG image |
| 25 | in `r0` | 36806 | 667 | PNG image |
| 26 | in `r0` | 37473 | 744 | PNG image |
| 27 | in `r0` | 38217 | 916 | PNG image |
| 28 | in `r0` | 39133 | 926 | PNG image |
| 29 | in `r0` | 40059 | 265 | PNG image |
| 30 | in `r0` | 40324 | 1352 | PNG image |
| 31 | in `r0` | 41676 | 1229 | PNG image |
| 32 | in `r0` | 42905 | 343 | PNG image |
| 33 | in `r0` | 43248 | 712 | PNG image |
| 34 | in `r0` | 43960 | 365 | PNG image |
| 35 | in `r0` | 44325 | 238 | PNG image |
| 36 | in `r0` | 44563 | 530 | PNG image |
| 37 | in `r0` | 45093 | 184 | PNG image |
| 38 | in `r0` | 45277 | 580 | PNG image |
| 39 | in `r0` | 45857 | 247 | PNG image |
| 40 | in `r0` | 46104 | 317 | PNG image |
| 41 | in `r0` | 46421 | 765 | PNG image |
| 42 | in `r0` | 47186 | 209 | PNG image |
| 43 | in `r0` | 47395 | 216 | PNG image |
| 44 | in `r0` | 47611 | 221 | PNG image |
| 45 | in `r0` | 47832 | 327 | PNG image |
| 46 | in `r0` | 48159 | 206 | PNG image |
| 47 | in `r0` | 48365 | 147 | PNG image |
| 48 | in `r0` | 48512 | 149 | PNG image |
| 49 | in `r0` | 48661 | 169 | PNG image |
| 50 | in `r0` | 48830 | 203 | PNG image |
| 51 | in `r0` | 49033 | 155 | PNG image |
| 52 | in `r0` | 49188 | 149 | PNG image |
| 53 | in `r0` | 49337 | 310 | PNG image |
| 54 | in `r0` | 49647 | 486 | PNG image |
| 55 | in `r0` | 50133 | 190 | PNG image |
| 56 | in `r0` | 50323 | 102 | PNG image |
| 57 | in `r0` | 50425 | 161 | PNG image |
| 58 | in `r0` | 50586 | 521 | PNG image |
| 59 | in `r0` | 51107 | 142 | PNG image |
| 60 | in `r0` | 51249 | 667 | PNG image |
| 61 | in `r0` | 51916 | 1024 | PNG image |
| 62 | in `r0` | 52940 | 180 | PNG image |
| 63 | in `r0` | 53120 | 311 | PNG image |
| 64 | in `r0` | 53431 | 1129 | PNG image |
| 65 | in `r0` | 54560 | 705 | PNG image |
| 66 | in `r0` | 55265 | 735 | PNG image |
| 67 | in `r0` | 56000 | 348 | PNG image |
| 68 | in `r0` | 56348 | 735 | PNG image |
| 69 | in `r0` | 57083 | 343 | PNG image |
| 70 | in `r0` | 57426 | 206 | PNG image |
| 71 | in `r0` | 57632 | 576 | PNG image |
| 72 | in `r0` | 58208 | 765 | PNG image |
| 73 | in `r0` | 58973 | 794 | PNG image |
| 74 | in `r0` | 59767 | 800 | PNG image |
| 75 | in `r0` | 60567 | 869 | PNG image |
| 76 | in `r0` | 61436 | 907 | PNG image |
| 77 | in `r0` | 62343 | 916 | PNG image |
| 78 | in `r0` | 63259 | 976 | PNG image |
| 79 | in `r0` | 64235 | 1034 | PNG image |
| 80 | standalone file `80` | - | 3847 | Standard MIDI |
| 81 | standalone file `81` | - | 10397 | Standard MIDI |
| 82 | standalone file `82` | - | 5098 | Standard MIDI |
| 83 | standalone file `83` | - | 2006 | Standard MIDI |
| 84 | standalone file `84` | - | 321 | Standard MIDI |
| 85 | standalone file `85` | - | 537 | Standard MIDI |
| 86 | standalone file `86` | - | 91 | Standard MIDI |
| 87 | standalone file `87` | - | 474 | Standard MIDI |
| 88 | standalone file `88` | - | 222 | Standard MIDI |
| 89 | in `r0` | 65269 | 686 | binary/data |
| 90 | standalone file `90` | - | 121614 | M3G (JSR-184) scene |
