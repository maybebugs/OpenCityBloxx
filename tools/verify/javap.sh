#!/bin/sh
exec java -m jdk.jdeps/com.sun.tools.javap.Main "$@"
