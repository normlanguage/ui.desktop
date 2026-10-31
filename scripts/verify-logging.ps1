param([string]$NormExecutable = $(if ($env:NORM_EXECUTABLE) { $env:NORM_EXECUTABLE } else { 'norm' }))
$ErrorActionPreference = 'Stop'
$root = Split-Path $PSScriptRoot -Parent
$diagnostics = New-TemporaryFile
$previousLevel = $env:NORM_LOG_LEVEL
$previousOutputs = $env:NORM_LOG_OUTPUTS
try {
    $env:NORM_LOG_LEVEL = 'error'
    $env:NORM_LOG_OUTPUTS = '[{"name":"std.logging.ConsoleLogOutputer"}]'
    $output = & $NormExecutable test (Join-Path $root 'ui/desktop') --filter ui.desktop.test.logging --format json 2> $diagnostics
    if ($LASTEXITCODE -ne 0) { throw "Logging window failed: $output" }
    $report = ($output -join [Environment]::NewLine) | ConvertFrom-Json
    if ($report.status -ne 'success' -or $report.tests.passed -ne 1) { throw "Unexpected logging test result: $output" }
    $messages = [IO.File]::ReadAllText($diagnostics)
    if ($messages -notmatch '\[ERROR\] ui\.desktop Unable to save changes') { throw "Missing application error event: $messages" }
    Write-Output 'JavaFX failure logging verified'
} finally {
    $env:NORM_LOG_LEVEL = $previousLevel
    $env:NORM_LOG_OUTPUTS = $previousOutputs
    Remove-Item -LiteralPath $diagnostics -Force
}
