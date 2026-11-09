# 韦特系塔罗牌素材

本套使用 1918 年《The Illustrated Key to the Tarot》中的 78 张原书扫描插图，部分图带黄色底色。绘图者为 Pamela Colman Smith。来源为 Project Gutenberg #43548，完整原书及其许可文字随 `source_book.txt` 保存。来源页 https://www.gutenberg.org/ebooks/43548 。

来源标注为美国公版。Project Gutenberg 要求美国以外的使用者核对当地规定。牌图与书籍的来源条件独立于仓库代码的 MIT 许可。目录保留原始 JPEG 字节。

`manifest.json` 为 UTF-8，含 78 个唯一编号、中英文名、图片路径、原始地址、字节数和 SHA-256。大牌编号为愚人 0、力量 8、正义 11。小牌依权杖、圣杯、宝剑、星币排列，每组顺序为王牌至十，再接侍从、骑士、王后、国王。

在 PowerShell 7 运行 `./tools/fetch_tarot_assets.ps1` 可重新获取原图并生成清单。书籍卷首另有一幅圣杯王牌，本清单采用小牌章节中的版本。
