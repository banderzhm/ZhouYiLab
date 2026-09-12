"""Behavioral tests use temporary repositories and never change project rules."""

import json
from pathlib import Path
import subprocess
import sys
import tempfile
import unittest

import generate_agents as generator


class AggregationTests(unittest.TestCase):
    def setUp(self):
        self.temporary = tempfile.TemporaryDirectory()
        self.addCleanup(self.temporary.cleanup)
        self.root = Path(self.temporary.name)
        self.skills = self.root / "skills"
        self.skills.mkdir()
        self.add_skill("alpha")
        self.add_skill("beta")
        self.manifest(["beta", "alpha"])

    def add_skill(self, name, body="## 规则\n\n保留排盘。\n"):
        folder = self.skills / name
        folder.mkdir(exist_ok=True)
        source = f"---\nname: {name}\ndescription: 修改排盘时使用。\n---\n\n{body}"
        (folder / "SKILL.md").write_text(source, encoding="utf-8")

    def manifest(self, names):
        (self.skills / "manifest.json").write_text(
            json.dumps({"version": 1, "skills": names}), encoding="utf-8")

    def test_order_content_and_idempotence(self):
        generator.generate(self.root)
        output = self.root / "AGENTS.md"
        before = output.read_bytes()
        modified = output.stat().st_mtime_ns
        self.assertLess(before.index(b"Source: skills/beta"), before.index(b"Source: skills/alpha"))
        self.assertEqual(before.decode().count("保留排盘。"), 2)
        self.assertNotIn(b"description:", before)
        self.assertNotIn(b"\r", before)
        generator.generate(self.root)
        self.assertEqual(output.stat().st_mtime_ns, modified)
        self.assertEqual(output.read_bytes(), before)
        self.assertTrue(generator.generate(self.root, check=True))

    def test_check_never_writes_and_detects_both_kinds_of_drift(self):
        self.assertFalse(generator.generate(self.root, check=True))
        self.assertFalse((self.root / "AGENTS.md").exists())
        generator.generate(self.root)
        output = self.root / "AGENTS.md"
        before = output.read_bytes()
        self.add_skill("alpha", "## 新规则\n\n新排盘约定。\n")
        self.assertFalse(generator.generate(self.root, check=True))
        self.assertEqual(output.read_bytes(), before)
        generator.generate(self.root)
        output.write_bytes(output.read_bytes() + b"manual change\n")
        self.assertFalse(generator.generate(self.root, check=True))

    def test_unmanaged_file_preserved(self):
        output = self.root / "AGENTS.md"
        output.write_text("existing project rules", encoding="utf-8")
        with self.assertRaises(generator.SpecificationError):
            generator.generate(self.root)
        self.assertEqual(output.read_text(), "existing project rules")

    def test_reference_links_are_rebased_without_inlining(self):
        folder = self.skills / "alpha" / "references"
        folder.mkdir()
        (folder / "usage.md").write_text("detailed example", encoding="utf-8")
        self.add_skill("alpha", "## 用法\n\n[调用指南](references/usage.md)\n")
        rendered = generator.render(self.root).decode("utf-8")
        self.assertIn("](skills/alpha/references/usage.md)", rendered)
        self.assertNotIn("detailed example", rendered)

    def test_missing_or_escaping_references_fail(self):
        for link in ("references/missing.md", "references/../../outside.md"):
            with self.subTest(link=link):
                self.add_skill("alpha", f"## 用法\n\n[指南]({link})\n")
                with self.assertRaises(generator.SpecificationError):
                    generator.render(self.root)

    def test_reference_examples_inside_code_are_not_resolved(self):
        body = "## 用法\n\n`[样例](references/example.md)`\n\n```text\n[样例](references/example.md)\n```\n"
        self.add_skill("alpha", body)
        rendered = generator.render(self.root).decode("utf-8")
        self.assertEqual(rendered.count("](references/example.md)"), 2)

    def test_invalid_manifests(self):
        for names in (["alpha", "alpha"], ["../outside"], ["/absolute"],
                      ["alpha"], ["alpha", "beta", "missing"], [], [False]):
            with self.subTest(names=names):
                self.manifest(names)
                with self.assertRaises(generator.SpecificationError):
                    generator.render(self.root)

    def test_metadata_and_empty_body(self):
        source = self.skills / "alpha" / "SKILL.md"
        invalid = (
            "no metadata", "---\nname: alpha", "---\nname: wrong\ndescription: test\n---\nbody",
            "---\nname: alpha\nname: alpha\ndescription: test\n---\nbody",
            "---\nname: alpha\ndescription: |\n---\nbody",
            "---\nname: alpha\ndescription: test\n---\n",
            "---\nname: alpha\ndescription: test\n---\n# Bad heading",
        )
        for content in invalid:
            with self.subTest(content=content):
                source.write_text(content, encoding="utf-8")
                with self.assertRaises(generator.SpecificationError):
                    generator.render(self.root)

    def test_crlf_and_bom_inputs_are_normalized(self):
        expected = generator.render(self.root)
        source = self.skills / "alpha" / "SKILL.md"
        normalized = source.read_text(encoding="utf-8").replace("\r\n", "\n")
        source.write_bytes(b"\xef\xbb\xbf" + normalized.replace("\n", "\r\n").encode("utf-8"))
        self.assertEqual(generator.render(self.root), expected)

    def test_generated_crlf_output_can_be_regenerated(self):
        generator.generate(self.root)
        output = self.root / "AGENTS.md"
        expected = output.read_bytes()
        output.write_bytes(expected.replace(b"\n", b"\r\n"))
        self.assertFalse(generator.generate(self.root, check=True))
        generator.generate(self.root)
        self.assertEqual(output.read_bytes(), expected)

    def test_symlink_skill_is_rejected(self):
        source = self.skills / "alpha" / "SKILL.md"
        target = self.root / "outside.md"
        source.rename(target)
        try:
            source.symlink_to(target)
        except OSError:
            self.skipTest("Symlink creation is unavailable on this host")
        with self.assertRaises(generator.SpecificationError):
            generator.render(self.root)

    def test_invalid_input_leaves_existing_output_untouched(self):
        generator.generate(self.root)
        output = self.root / "AGENTS.md"
        before = output.read_bytes()
        self.manifest(["missing"])
        with self.assertRaises(generator.SpecificationError):
            generator.generate(self.root)
        self.assertEqual(output.read_bytes(), before)

    def test_symlink_output_is_rejected(self):
        target = self.root / "other.md"
        target.write_text("do not overwrite", encoding="utf-8")
        try:
            (self.root / "AGENTS.md").symlink_to(target)
        except OSError:
            self.skipTest("Symlink creation is unavailable on this host")
        with self.assertRaises(generator.SpecificationError):
            generator.generate(self.root)
        self.assertEqual(target.read_text(), "do not overwrite")

    def test_cli_works_from_other_directory(self):
        # Mirror the script location so its own root resolution is exercised.
        script = self.skills / "zhouyilab-skill-maintenance" / "scripts" / "generate_agents.py"
        script.parent.mkdir(parents=True)
        script.write_bytes(Path(generator.__file__).read_bytes())
        result = subprocess.run([sys.executable, str(script), "--check"],
                                cwd=self.skills, capture_output=True)
        self.assertEqual(result.returncode, 1, result.stderr)
        result = subprocess.run([sys.executable, str(script)], cwd=self.skills, capture_output=True)
        self.assertEqual(result.returncode, 0, result.stderr)
        result = subprocess.run([sys.executable, str(script), "--check"],
                                cwd=self.skills, capture_output=True)
        self.assertEqual(result.returncode, 0, result.stderr)
        self.manifest(["missing"])
        result = subprocess.run([sys.executable, str(script), "--check"],
                                cwd=self.skills, capture_output=True)
        self.assertEqual(result.returncode, 2, result.stderr)


if __name__ == "__main__":
    unittest.main()
