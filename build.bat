@echo off
setlocal
cd /d "%~dp0"
where javac >nul 2>nul || (echo javac not found. Install a JDK 11+ and add it to PATH. & exit /b 1)
if exist out rmdir /s /q out
mkdir out
dir /s /b src\*.java > sources.txt
javac -nowarn -encoding UTF-8 -d out @sources.txt || (del sources.txt & exit /b 1)
del sources.txt
rem Bundle assets inside the jar as a fallback; the assets\ folder next to the jar takes priority.
xcopy /e /i /y /q assets out\assets >nul
echo Main-Class: Main> manifest.txt
jar cfm CityBloxx.jar manifest.txt -C out . || exit /b 1
del manifest.txt
echo Built CityBloxx.jar - run with run.bat
