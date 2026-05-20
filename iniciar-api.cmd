@echo off
cd /d "%~dp0"
echo Iniciando Comissao API em http://localhost:8082
echo Para parar a API, pressione Ctrl+C nesta janela.
call .\mvnw.cmd spring-boot:run
pause
