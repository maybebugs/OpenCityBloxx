#!/usr/bin/env python3
"""One-time migration: res/jar_resources (r0 archive + loose files) -> assets/ tree + manifest.txt.

Usage: python3 tools/migrate_resources.py [res_dir] [assets_dir]
The 3D model file (res id 90, M3G) is NOT copied into assets; it is converted to
OBJ by tools/M3gToObj.java and the original is kept in tools/source/.
"""
import os, shutil, struct, sys

RES = sys.argv[1] if len(sys.argv) > 1 else 'res/jar_resources'
OUT = sys.argv[2] if len(sys.argv) > 2 else 'assets'

NAMES = {
    # id: (folder, name, ext)
    0: ('images/ui', 'arrow', 'png'),
    1: ('images/menu', 'icon_play', 'png'),
    2: ('images/menu', 'icon_city_mode', 'png'),
    3: ('images/menu', 'icon_high_scores', 'png'),
    4: ('images/menu', 'icon_help', 'png'),
    5: ('images/menu', 'icon_awards', 'png'),
    6: ('images/ui', 'score_icon_row', 'png'),
    7: ('images/ui', 'score_icon_header', 'png'),
    8: ('images/ui', 'score_icon_unused', 'png'),
    9: ('images/title', 'loading', 'png'),
    10: ('images/ui', 'softkey_arrow', 'png'),
    11: ('images/title', 'logo', 'png'),
    12: ('images/tower', 'citizen_a', 'png'),
    13: ('images/tower', 'citizen_b', 'png'),
    14: ('images/hud', 'level_medals', 'png'),
    15: ('images/fonts', 'digits_15', 'png'),
    16: ('images/fonts', 'digits_16', 'png'),
    17: ('images/fonts', 'digits_17', 'png'),
    18: ('images/hud', 'hud_icons', 'png'),
    19: ('images/hud', 'life_icons', 'png'),
    20: ('images/hud', 'floor_counter_frame', 'png'),
    21: ('images/city', 'population_gauge', 'png'),
    22: ('images/city', 'status_icons', 'png'),
    23: ('images/city', 'hud_bar', 'png'),
    24: ('images/city', 'build_icon', 'png'),
    25: ('images/city', 'building_0', 'png'),
    26: ('images/city', 'building_1', 'png'),
    27: ('images/city', 'building_2', 'png'),
    28: ('images/city', 'building_3', 'png'),
    29: ('images/city', 'building_4', 'png'),
    30: ('images/city', 'tile_effect_frames', 'png'),
    31: ('images/city', 'dust_effect_frames', 'png'),
    32: ('images/tower', 'crane_hook_frames', 'png'),
    33: ('images/tower', 'fence_container', 'png'),
    34: ('images/tower', 'fence_chain', 'png'),
    35: ('images/tower', 'fence_wood', 'png'),
    36: ('images/tower', 'tree', 'png'),
    37: ('images/effects', 'spark', 'png'),
    38: ('images/effects', 'combo_star', 'png'),
    39: ('images/effects', 'combo_sparkle', 'png'),
    40: ('images/effects', 'water_splash', 'png'),
    41: ('images/tower', 'crane_arm', 'png'),
    42: ('images/sky', 'clouds_wispy_a', 'png'),
    43: ('images/sky', 'clouds_wispy_b', 'png'),
    44: ('images/sky', 'blimp', 'png'),
    45: ('images/sky', 'hot_air_balloon', 'png'),
    46: ('images/sky', 'cloud_bank', 'png'),
    47: ('images/sky', 'birds', 'png'),
    48: ('images/sky', 'cloud_small_peak', 'png'),
    49: ('images/sky', 'plane_small', 'png'),
    50: ('images/sky', 'plane_jet', 'png'),
    51: ('images/sky', 'cloud_layer', 'png'),
    52: ('images/sky', 'cloud_peak', 'png'),
    53: ('images/sky', 'rockets', 'png'),
    54: ('images/sky', 'moon', 'png'),
    55: ('images/sky', 'satellite', 'png'),
    56: ('images/sky', 'star_dot', 'png'),
    57: ('images/sky', 'star_sparkle_pink', 'png'),
    58: ('images/sky', 'mars', 'png'),
    59: ('images/sky', 'star_sparkle_cyan', 'png'),
    60: ('images/sky', 'asteroid_field', 'png'),
    61: ('images/sky', 'jupiter', 'png'),
    62: ('images/sky', 'plane_trail', 'png'),
    63: ('images/sky', 'ufo', 'png'),
    64: ('images/sky', 'saturn', 'png'),
    65: ('images/sky', 'uranus', 'png'),
    66: ('images/sky', 'neptune', 'png'),
    67: ('images/sky', 'small_moon', 'png'),
    68: ('images/sky', 'whales', 'png'),
    69: ('images/sky', 'cloud_large', 'png'),
    70: ('images/sky', 'cloud_small', 'png'),
    71: ('images/badges', 'level_0', 'png'),
    72: ('images/badges', 'level_1', 'png'),
    73: ('images/badges', 'level_2', 'png'),
    74: ('images/badges', 'level_3', 'png'),
    75: ('images/badges', 'level_4', 'png'),
    76: ('images/badges', 'level_5', 'png'),
    77: ('images/badges', 'level_6', 'png'),
    78: ('images/badges', 'level_7', 'png'),
    79: ('images/badges', 'level_8', 'png'),
    80: ('audio/music', 'title_theme', 'mid'),
    81: ('audio/music', 'tower_theme', 'mid'),
    82: ('audio/music', 'city_theme', 'mid'),
    83: ('audio/sfx', 'drop_miss', 'mid'),
    84: ('audio/sfx', 'drop_good', 'mid'),
    85: ('audio/sfx', 'drop_perfect', 'mid'),
    86: ('audio/sfx', 'sfx_86', 'mid'),
    87: ('audio/sfx', 'sfx_87', 'mid'),
    88: ('audio/sfx', 'sfx_88', 'mid'),
    89: ('data', 'res_89', 'bin'),
}

def split_archive(path):
    r = open(path, 'rb').read()
    off = list(struct.unpack('>92i', r[:368]))
    out = {}
    for i in range(91):
        if off[i] < 0:
            continue
        n = i + 1
        while off[n] < 0:
            n += 1
        end = off[n] if (n < 91 and off[n] > off[i]) else off[91]
        out[i] = r[off[i]:end]
    return out

def main():
    if os.path.exists(OUT):
        shutil.rmtree(OUT)
    os.makedirs(OUT)
    archive = split_archive(os.path.join(RES, 'r0'))
    manifest = []
    for rid in sorted(NAMES):
        folder, name, ext = NAMES[rid]
        if rid in archive:
            data = archive[rid]
        else:
            data = open(os.path.join(RES, str(rid)), 'rb').read()  # standalone midi
        rel = '%s/%s.%s' % (folder, name, ext)
        os.makedirs(os.path.join(OUT, folder), exist_ok=True)
        open(os.path.join(OUT, rel), 'wb').write(data)
        manifest.append((rid, rel))
    # loose files
    os.makedirs(os.path.join(OUT, 'lang'), exist_ok=True)
    os.makedirs(os.path.join(OUT, 'data'), exist_ok=True)
    for f in sorted(os.listdir(RES)):
        if f.startswith('lang.'):
            shutil.copy(os.path.join(RES, f), os.path.join(OUT, 'lang', f))
    shutil.copy(os.path.join(RES, 'm'), os.path.join(OUT, 'data', 'menu.dat'))
    shutil.copy(os.path.join(RES, 'icon.png'), os.path.join(OUT, 'images', 'icon.png'))
    # keep original M3G for the converter
    os.makedirs('tools/source', exist_ok=True)
    shutil.copy(os.path.join(RES, '90'), 'tools/source/models.m3g')
    with open(os.path.join(OUT, 'manifest.txt'), 'w') as f:
        f.write('# resource-id <TAB> path relative to assets/\n')
        f.write('# ids are the low 15 bits of the packed ids used in the code\n')
        for rid, rel in manifest:
            f.write('%d\t%s\n' % (rid, rel))
    print('wrote', len(manifest), 'manifest entries')

main()
