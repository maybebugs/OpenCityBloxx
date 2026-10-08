@echo off
rem Re-extracts assets\models from tools\source\models.m3g (only needed if you edit the converter)
cd /d "%~dp0\.."
if not exist out_tools mkdir out_tools
javac -nowarn -d out_tools tools\M3gReader.java tools\M3gToObj.java || exit /b 1
java -Djava.awt.headless=true -cp out_tools M3gToObj tools\source\models.m3g assets\models
