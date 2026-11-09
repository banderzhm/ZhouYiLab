param([string]$Destination = (Join-Path $PSScriptRoot '../assets/ephemeris'))
$ErrorActionPreference='Stop'
$expected='c1c7feeab882263fc493a9d5a5b2ddd71b54826cdf65d8d17a76126b260a49f2'
New-Item -ItemType Directory -Force -Path $Destination | Out-Null
$target=Join-Path $Destination 'de440s.bsp'
if(Test-Path -LiteralPath $target) {
    if((Get-FileHash -LiteralPath $target -Algorithm SHA256).Hash.ToLowerInvariant() -ne $expected) {
        throw 'Existing de440s.bsp differs from the pinned checksum'
    }
    Write-Output 'Verified existing DE440s'
    return
}
$download=Join-Path $Destination ('de440s.'+[Guid]::NewGuid().ToString('N')+'.download')
try {
    Invoke-WebRequest 'https://naif.jpl.nasa.gov/pub/naif/generic_kernels/spk/planets/de440s.bsp' -OutFile $download
    if((Get-FileHash -LiteralPath $download -Algorithm SHA256).Hash.ToLowerInvariant() -ne $expected) {
        throw 'Downloaded DE440s checksum differs from the pinned version'
    }
    Move-Item -LiteralPath $download -Destination $target
    Write-Output 'Downloaded and verified DE440s'
} finally {
    if(Test-Path -LiteralPath $download) { Remove-Item -LiteralPath $download }
}
