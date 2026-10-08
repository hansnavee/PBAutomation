param(
    [ValidateSet("serve", "report")]
    [string]$Mode = "serve"
)

$ErrorActionPreference = "Stop"
$root = Split-Path -Parent $PSScriptRoot
Set-Location $root

if ($Mode -eq "report") {
    & mvn -B allure:report
} else {
    & mvn -B allure:serve
}

if ($LASTEXITCODE -ne 0) {
    exit $LASTEXITCODE
}
