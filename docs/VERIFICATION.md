# Verification status

Everything below was measured, not assumed. "Original" means the decompiled sources as first committed
(they compile to the same 53 classes); it is NOT the untouched game JAR, which was not available.

## 1. Renames are behaviour-neutral (proven)
`tools/verify/bccheck.py` compiles the original and the current tree, aligns every field and method by
declaration order, maps old names to new names, and compares every instruction of every method
(opcodes, constants, branch structure, field/method references).

Result: **53/53 classes identical**, 508 members renamed, 0 differing methods.
Only `House.updateBlockPhysics` is excluded (see 2) plus the added constants/helper (`BLOCK_*`, `PHASE_*`,
`GAME_MODE_*`, `easeAngle`, ...). Replacing literals by `static final` constants is also covered: constants
inline to the same bytecode.

## 2. Rewritten logic: `House.updateBlockPhysics` (tested, not proven)
Rewritten by hand from the decompiled code (named states, constants, `easeAngle` helper).
`tools/verify/Diff2.java` loads the original and new `House` in separate class loaders, applies identical random
game states, runs one frame at a time and compares **every static primitive/array field** and any exception.

* 30,000 trials / 135,265 frames: **0 mismatches** (roof entry 136x, round over 206x, debris spawn, miss, re-hang).
* Sensitivity check, 6 deliberately broken variants (gravity 200->201, re-hang 400->401, return stop 512->511,
  roof trigger off by one, x-velocity divisor, swapped side-effect order): **6/6 detected**.
* Limits: ~10% of frames throw (random states outside valid ranges); both versions throw identically but those
  frames test little. Random states are not real gameplay.

## 3. What is NOT verified
* The old claim of 18 `FIXME(bytecode)` placeholder sites is stale: no FIXME markers remain in the source, but
  placeholder-style locals (`int i3 = 0;`, `Image image = null;`, ...) are still there. I inspected five of them
  (render `f(Graphics)`, `paintLoading`, `keyPressed`/`b(int)`, `update`, `t(int)`): each default is either
  null-guarded or overwritten on every path, so they look benign. Not proven. Resolving them needs the
  original `.class` files.
* Names are interpretations. See `docs/NAMING_NOTES.md` for corrections made and names that are inferred.
* Rendering code was only checked for bytecode equivalence with the original, not rewritten.
