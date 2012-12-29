@echo off

cd %~dp0

set tmpdir=upgrade.tmp

IF not "%1" == "delay" GOTO nodelay
  timeout 2

:nodelay

IF NOT EXIST "%tmpdir%" GOTO startapp

  xcopy /Q /Y "%tmpdir%\*" .
  rd /Q /S %tmpdir%

:startapp
java -jar lightwarehouse.jar
