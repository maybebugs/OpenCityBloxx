#!/bin/bash
# usage: mutate.sh "<old>" "<new>" "<label>"
rm -rf mut && mkdir mut && cp -r user3/src mut/src
python3 - "$1" "$2" <<'PY'
import sys
p='/tmp/w/mut/src/House.java'
s=open(p,encoding='utf-8',newline='').read()
a=sys.argv[1]; assert a in s,'pattern missing: '+a
open(p,'w',encoding='utf-8',newline='').write(s.replace(a,sys.argv[2],1))
PY
(cd mut && find src -name '*.java' > srcs.txt && mkdir out && java -m jdk.compiler/com.sun.tools.javac.Main -nowarn -encoding UTF-8 -d out @srcs.txt 2>/dev/null)
echo -n "$3 => "; java -XX:-OmitStackTraceInFastThrow -cp harness Diff2 orig/out mut/out 20000 mapping.txt map_user3.txt 2>&1 | grep -E '^trials' | sed -E 's/framesWithException.*//'
