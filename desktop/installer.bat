@echo off
xcopy /s "executable" "%USERPROFILE%\Documents\Baraja Sistema Coral\"
mklink "%USERPROFILE%\Desktop\Baraja Sistema Coral" "%USERPROFILE%\Documents\Baraja Sistema Coral\Baraja Sistema Coral.exe"
mkdir %USERPROFILE%\.coralsystemdeck