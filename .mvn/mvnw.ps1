$ErrorActionPreference = 'Stop'

$mavenVersion = '3.9.9'
$baseDir = Split-Path -Parent $PSScriptRoot
$mavenDir = Join-Path $PSScriptRoot "apache-maven-$mavenVersion"
$mvnCmd = Join-Path $mavenDir 'bin\mvn.cmd'

if (-not (Test-Path $mvnCmd)) {
    $zipPath = Join-Path $PSScriptRoot "apache-maven-$mavenVersion-bin.zip"
    $downloadUrl = "https://archive.apache.org/dist/maven/maven-3/$mavenVersion/binaries/apache-maven-$mavenVersion-bin.zip"

    Write-Host "Baixando Apache Maven $mavenVersion..."
    Invoke-WebRequest -Uri $downloadUrl -OutFile $zipPath

    Write-Host "Extraindo Apache Maven $mavenVersion..."
    Expand-Archive -Path $zipPath -DestinationPath $PSScriptRoot -Force
    Remove-Item $zipPath
}

Set-Location $baseDir
& $mvnCmd @args
exit $LASTEXITCODE
