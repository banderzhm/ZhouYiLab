param([string]$Destination = (Join-Path $PSScriptRoot '../assets/tarot/waite_1918'), [switch]$ReuseDownloaded)
$ErrorActionPreference = 'Stop'
$base = 'https://www.gutenberg.org/cache/epub/43548/'
$pageUrl = $base + 'pg43548-images.html'
$page = Invoke-WebRequest $pageUrl
$cards = @($page.Images | Where-Object { $_.alt -and $_.alt -notmatch '^ornament|^diagram' })
# The frontispiece repeats the ace of cups. The numbered deck uses the later plate.
$cards = @($cards | Where-Object { $_.src -ne 'images/i_013.jpg' })
if ($cards.Count -ne 78) { throw "Expected 78 card plates, received $($cards.Count)" }
New-Item -ItemType Directory -Force -Path $Destination | Out-Null
$majorNames = @('The Fool','The Magician','The High Priestess','The Empress','The Emperor','The Hierophant','The Lovers','The Chariot','Strength','The Hermit','Wheel of Fortune','Justice','The hanged man','Death','Temperance','The Devil','The Tower','The Star','The Moon','The Sun','The Judgement','The World')
$majorZh = @('愚人','魔术师','女祭司','皇后','皇帝','教皇','恋人','战车','力量','隐者','命运之轮','正义','倒吊人','死神','节制','恶魔','高塔','星星','月亮','太阳','审判','世界')
$ranks = @('Ace','Two','Three','Four','Five','Six','Seven','Eight','Nine','Ten','Page','Knight','Queen','King')
$rankZh = @('王牌','二','三','四','五','六','七','八','九','十','侍从','骑士','王后','国王')
$suits = @('Wands','Cups','Swords','Pentacles')
$suitZh = @('权杖','圣杯','宝剑','星币')
$entries = foreach ($card in $cards) {
    $major = [Array]::IndexOf($majorNames, [string]$card.alt)
    if ($major -ge 0) {
        $id = $major
        $nameZh = $majorZh[$major]
        $key = 'major_{0:d2}' -f $major
    } else {
        if ($card.alt -notmatch '^(\w+) of (\w+)$') { throw "Unknown plate $($card.alt)" }
        $rank = [Array]::IndexOf($ranks, $Matches[1])
        $suit = [Array]::IndexOf($suits, $Matches[2])
        if ($rank -lt 0 -or $suit -lt 0) { throw "Unknown minor card $($card.alt)" }
        $id = 22 + $suit * 14 + $rank
        $nameZh = $suitZh[$suit] + $rankZh[$rank]
        $key = '{0}_{1:d2}' -f $suits[$suit].ToLowerInvariant(), ($rank + 1)
    }
    $file = $key + '.jpg'
    $path = Join-Path $Destination $file
    $url = $base + $card.src
    if (!$ReuseDownloaded -or !(Test-Path -LiteralPath $path)) {
        Invoke-WebRequest $url -OutFile $path
    }
    $bytes = [IO.File]::ReadAllBytes($path)
    if ($bytes.Length -lt 1000 -or $bytes[0] -ne 255 -or $bytes[1] -ne 216) { throw "Invalid JPEG $file" }
    [pscustomobject][ordered]@{ id=$id; key=$key; name_zh=$nameZh; name_en=[string]$card.alt; image=$file; source_url=$url; sha256=(Get-FileHash $path -Algorithm SHA256).Hash.ToLowerInvariant(); bytes=$bytes.Length }
}
if (@($entries.id | Sort-Object -Unique).Count -ne 78) { throw 'Duplicate card IDs' }
$manifest = [ordered]@{ schema_version=1; deck='waite_1918_scan'; source_page=$pageUrl; illustrator='Pamela Colman Smith'; source_edition_year=1918; source_rights='Project Gutenberg #43548, public domain in the USA; see source license and jurisdiction terms'; cards=@($entries | Sort-Object id) }
# Downloaded metadata is generated data, kept alongside the original image bytes.
$manifest | ConvertTo-Json -Depth 6 | Set-Content (Join-Path $Destination 'manifest.json') -Encoding utf8
Invoke-WebRequest 'https://www.gutenberg.org/cache/epub/43548/pg43548.txt' -OutFile (Join-Path $Destination 'source_book.txt')
Write-Output "Verified $($entries.Count) unique card images in $Destination"
