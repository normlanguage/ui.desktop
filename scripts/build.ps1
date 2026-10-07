param([string]$NormHome = (Join-Path (Split-Path $PSScriptRoot -Parent) '.norm-home'))
$ErrorActionPreference = 'Stop'
$root = Split-Path $PSScriptRoot -Parent
$licenseDirectory = Join-Path $root 'ui/desktop/resources/META-INF/licenses/ui.desktop'
New-Item -ItemType Directory -Force $licenseDirectory | Out-Null
Copy-Item -LiteralPath (Join-Path $root 'LICENSE') -Destination (Join-Path $licenseDirectory 'LICENSE') -Force
& (Join-Path $root 'gradlew.bat') -p $root publish --console=plain
if ($LASTEXITCODE -ne 0) { throw 'Adapter build failed' }
$maven = Join-Path $NormHome '.norm/cache/maven'
New-Item -ItemType Directory -Force $maven | Out-Null
Copy-Item -Path (Join-Path $root 'build/repository/*') -Destination $maven -Recurse -Force
