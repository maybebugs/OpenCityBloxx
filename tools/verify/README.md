# Verification tools (JDK 21 runtime with jdk.compiler and jdk.jdeps modules is enough; no javac/javap binaries)
javap.sh        wrapper for `java -m jdk.jdeps/com.sun.tools.javap.Main`
SemRename.java  symbol-resolving renamer (javac Tree API): `java SemRename <srcdir> <spec> [force]`
                spec lines: `F Class old new` | `M Class old(Type,..) new` | `L Class method(Type,..) old new`
bccheck.py      bytecode equivalence: `python3 bccheck.py <origClasses> <newClasses>` (env ADDED / EXCL / MAPOUT / FULLOUT)
run_bc.sh       bccheck with the added-constants allowance used for this tree
Diff2.java      differential test of one method on random states
mutate.sh       plants a bug in the new source and re-runs Diff2 (sensitivity check)
spec_*.txt      the rename specs applied on top of the hand-made rename map
