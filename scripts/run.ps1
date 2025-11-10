Set-Location -Path "$PSScriptRoot/../"

Write-Host "`n================================" -ForegroundColor Cyan
Write-Host "  Servidor rodando!" -ForegroundColor Green
Write-Host "  URL: http://localhost:4567/" -ForegroundColor Yellow
Write-Host "================================`n" -ForegroundColor Cyan
java -jar target/blackjack-1.0.jar