@echo off
:loop
IF EXIST bin rmdir /s /q bin
mkdir bin

SET "sources=src/wireworld/.java"

javac -encoding UTF-8 -d .\bin %sources% 2>nul

java -cp bin wireworld.Menu

echo Press Ctrl+C to exit or any key to restart...
pause > nul
goto loop