@echo off
set SAVECLASSPATH=%CLASSPATH%

set CLASSPATH=lib/antlr-4.6-complete.jar;./bin

java org.antlr.v4.gui.TestRig logoparsing.Logo programme -gui programs/logo-prg.txt
pause

set CLASSPATH=%SAVECLASSPATH%
