[CmdletBinding()]
param()

$ErrorActionPreference = "Stop"

$scriptFolder = Split-Path -Parent $MyInvocation.MyCommand.Path
$androidFolder = Split-Path -Parent $scriptFolder
$repoFolder = Split-Path -Parent $androidFolder

$outputFolder = Join-Path $androidFolder "app\build\outputs\connected_android_test_additional_output"
$folderOnPc = Join-Path $repoFolder "docs\screenshots\android"

if (-not $env:JAVA_HOME) {
    $studioJava = "C:\Program Files\Android\Android Studio\jbr"
    if (Test-Path $studioJava) {
        $env:JAVA_HOME = $studioJava
    }
}

if (Test-Path $outputFolder) {
    Remove-Item -Path $outputFolder -Recurse -Force
}

Write-Host "Walking through the screens on the emulator"
$gradle = Join-Path $androidFolder "gradlew.bat"
& $gradle -p $androidFolder connectedDebugAndroidTest `
    "-Pandroid.testInstrumentationRunnerArguments.annotation=lk.sliit.solargrid.screens.Screens" `
    --console=plain
if ($LASTEXITCODE -ne 0) {
    throw "The screenshot test failed. The report is in android\app\build\reports\androidTests\connected\debug\index.html"
}

$pictures = @(Get-ChildItem -Path $outputFolder -Filter *.png -Recurse -ErrorAction SilentlyContinue)
if ($pictures.Count -eq 0) {
    throw "The test ran but no pictures arrived in $outputFolder."
}

if (-not (Test-Path $folderOnPc)) {
    New-Item -ItemType Directory -Path $folderOnPc -Force | Out-Null
}

foreach ($picture in $pictures) {
    Copy-Item -Path $picture.FullName -Destination (Join-Path $folderOnPc $picture.Name) -Force
}

Write-Host ""
Write-Host "$($pictures.Count) pictures saved in $folderOnPc"
foreach ($picture in ($pictures | Sort-Object Name)) {
    Write-Host "  $($picture.Name)"
}
