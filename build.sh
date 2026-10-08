#!/bin/sh
# Linux/macOS build (same as build.bat)
cd "$(dirname "$0")" || exit 1
command -v javac >/dev/null || { echo "javac not found. Install a JDK 11+."; exit 1; }
rm -rf out && mkdir out
find src -name '*.java' > sources.txt
javac -nowarn -encoding UTF-8 -d out @sources.txt || { rm -f sources.txt; exit 1; }
rm -f sources.txt
cp -r assets out/assets
printf 'Main-Class: Main\n' > manifest.txt
jar cfm CityBloxx.jar manifest.txt -C out . || exit 1
rm -f manifest.txt
echo "Built CityBloxx.jar - run with ./run.sh"
