# Verification status

## Rewritten from bytecode (decompiler failed or was wrong) - logic follows the original
- `Resources.lookupString` (was `g.b`) - interpreted from bytecode; all 161 ids -> `docs/STRING_IDS.md`
- `Ui.wrap` (`i.a(String,int,int,Font)`), `Ui.e(Graphics)` (title ticker), `Ui.b(Graphics)` (list painter),
  `Ui.a(Graphics,boolean)` (paged text), `Ui.a(int)` (scroll) - the decompiler mixed locals with static fields
- `MenuController.d()` / `d(int)` (menu data parser), name-entry listener
- `HighScores.b()`, `HighScores.a(Graphics,int,boolean)`, score insertion
- `GameMIDlet.run` tail (try/catch), `Renderer3D.lookAt` (parameter/field clash)
- `CityMode.loadState/saveState` (field order verified against bytecode), `CityMode.h()` (link extents),
  `CityMode.paint` cursorCol sites, `CityMode.update` (dt vs cursorCol), `CityMode.startPlacement`
- `House.y()` (camera ease/shake; decompiler failure), `House.F()` (drop/landing/combo/score), `House.s(int)` (block physics),
  `House.a(String,Font,int)` (line wrap), `House()` constructor, `House.e(Graphics)`, `House.j(Graphics)` tail,
  `House.v()`, `House.paintBackground`, I()/a(boolean)/save/load static-field clashes

## NOT verified - treat as decompiler output
- Every other method of `House` (about 90) and `CityMode`, `Ui`, `HighScores`, `MenuController`, `SoundPlayer`, `Renderer3D`.
  Field-access cross-check against bytecode flagged no further mismatches, but branch structure was not compared.
- `Resources.openStream`: try/catch placement is inferred.
- **18 sites marked `FIXME(bytecode)`**: the decompiler dropped a branch, so a local is initialised to a default
  (0/false/null) just to compile. The value on that path is NOT original:

```
out/CityBloxx_reconstructed/src/CityMode.java:213: int i3 = 0; // FIXME(bytecode): decompiler dropped a branch, value on
out/CityBloxx_reconstructed/src/CityMode.java:473: Image image2 = null; // FIXME(bytecode): decompiler dropped a branch,
out/CityBloxx_reconstructed/src/CityMode.java:474: Graphics graphics3 = null; // FIXME(bytecode): decompiler dropped a b
out/CityBloxx_reconstructed/src/CityMode.java:672: int i3 = 0; // FIXME(bytecode): decompiler dropped a branch, value on
out/CityBloxx_reconstructed/src/CityMode.java:674: String message = null; // FIXME(bytecode): decompiler dropped a branc
out/CityBloxx_reconstructed/src/CityMode.java:800: boolean z = false; // FIXME(bytecode): decompiler dropped a branch, v
out/CityBloxx_reconstructed/src/House.java:1556: Mesh3D dVar = null; // FIXME(bytecode): decompiler dropped a branch, va
out/CityBloxx_reconstructed/src/House.java:2009: int i3 = 0; // FIXME(bytecode): decompiler dropped a branch, value on t
out/CityBloxx_reconstructed/src/House.java:2031: int i8 = 0; // FIXME(bytecode): decompiler dropped a branch, value on t
out/CityBloxx_reconstructed/src/House.java:2032: Graphics graphics2 = null; // FIXME(bytecode): decompiler dropped a bra
out/CityBloxx_reconstructed/src/House.java:2122: int i2 = 0; // FIXME(bytecode): decompiler dropped a branch, value on t
out/CityBloxx_reconstructed/src/House.java:2628: int i5 = 0; // FIXME(bytecode): decompiler dropped a branch, value on t
out/CityBloxx_reconstructed/src/House.java:2629: int[] iArr2 = null; // FIXME(bytecode): decompiler dropped a branch, va
out/CityBloxx_reconstructed/src/House.java:2630: int i6 = 0; // FIXME(bytecode): decompiler dropped a branch, value on t
out/CityBloxx_reconstructed/src/House.java:2775: int[] iArr3 = null; // FIXME(bytecode): decompiler dropped a branch, va
out/CityBloxx_reconstructed/src/House.java:2963: int i3 = 0; // FIXME(bytecode): decompiler dropped a branch, value on t
out/CityBloxx_reconstructed/src/House.java:3144: Image image = null; // FIXME(bytecode): decompiler dropped a branch, va
out/CityBloxx_reconstructed/src/House.java:3250: int i2 = 0; // FIXME(bytecode): decompiler dropped a branch, value on t
```

## Names
Only the members listed in `tools/renames.txt` were renamed. Most `House`/`CityMode` fields keep their obfuscated
names (e.g. `bs`, `bt`, `cg`) because their meaning was not established. Known: `House.e` mode, `House.f` state,
`CityMode.population/level/grid/cursorCol/cursorRow/levelThresholds`.
