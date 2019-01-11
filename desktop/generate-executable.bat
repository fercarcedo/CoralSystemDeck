@echo off
java -jar packr.jar packr-config.json
rh.exe -open "executable/Baraja Sistema Coral.exe" -save "executable/Baraja Sistema Coral.exe" -action addskip -res ic_launcher.ico -mask ICONGROUP,MAINICON
exit