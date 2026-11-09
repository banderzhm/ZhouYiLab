"""Verify the downloaded deck independently of the download script."""
import hashlib
import json
import pathlib
import unittest

DECK = pathlib.Path(__file__).resolve().parents[1] / "assets/tarot/waite_1918"


class TarotAssetsTest(unittest.TestCase):
    def test_complete_deck(self):
        manifest = json.loads((DECK / "manifest.json").read_text(encoding="utf-8-sig"))
        cards = manifest["cards"]
        self.assertEqual(list(range(78)), [card["id"] for card in cards])
        self.assertEqual(78, len({card["key"] for card in cards}))
        self.assertEqual(78, len({card["image"] for card in cards}))
        self.assertEqual(78, len({card["name_zh"] for card in cards}))
        for card in cards:
            with self.subTest(card=card["key"]):
                data = (DECK / card["image"]).read_bytes()
                self.assertTrue(data.startswith(b"\xff\xd8"))
                self.assertTrue(data.endswith(b"\xff\xd9"))
                self.assertEqual(len(data), card["bytes"])
                self.assertEqual(hashlib.sha256(data).hexdigest(), card["sha256"])
        self.assertIn("PROJECT GUTENBERG", (DECK / "source_book.txt").read_text(encoding="utf-8-sig").upper())


if __name__ == "__main__":
    unittest.main()
