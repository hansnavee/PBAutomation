param(
    [ValidateSet("smoke", "regression")]
    [string]$Suite = "smoke",
    [string]$Tags = "",
    [bool]$Headless = $true
)

$ErrorActionPreference = "Stop"
$root = Split-Path -Parent $PSScriptRoot
Set-Location $root

$headlessValue = if ($Headless) { "true" } else { "false" }

if ($Tags) {
    & mvn test "-Dheadless=$headlessValue" "-Dcucumber.tags=$Tags"
} elseif ($Suite -eq "regression") {
    & mvn test -Pregression "-Dheadless=$headlessValue"
} else {
    & mvn test -Psmoke "-Dheadless=$headlessValue"
}

if ($LASTEXITCODE -ne 0) {
    exit $LASTEXITCODE
}
