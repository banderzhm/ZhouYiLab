#!/usr/bin/env node
'use strict';

const fs = require('node:fs');
const path = require('node:path');
const { spawnSync } = require('node:child_process');

const REFERENCE_COMMITS = {
  'three-meta-root': '9be1238cbb7b0118826a689f9d3f8100284f6df3',
  'idavailable-root': 'e02a63cd1f4446cc3b0c24c7108036eb6377e427',
};

function parseArgs(argv) {
  const args = {};
  for (let index = 2; index < argv.length; index += 2) {
    const key = argv[index];
    const value = argv[index + 1];
    if (!key?.startsWith('--') || value === undefined) {
      throw new Error('用法：--probe <exe> --three-meta-root <dir> --idavailable-root <dir> --output-dir <dir>');
    }
    args[key.slice(2)] = value;
  }
  for (const required of ['probe', 'three-meta-root', 'idavailable-root', 'output-dir']) {
    if (!args[required]) throw new Error(`缺少 --${required}`);
  }
  return args;
}

function addDays(date, days) {
  const value = new Date(Date.UTC(date.year, date.month - 1, date.day + days));
  return { year: value.getUTCFullYear(), month: value.getUTCMonth() + 1, day: value.getUTCDate() };
}

// 六十日完整循环 × 十二时辰，并覆盖节气附近、子初换日、闰日和远期年份。
function buildCorpus() {
  const cases = [];
  for (let offset = 0; offset < 60; offset += 1) {
    const date = addDays({ year: 2026, month: 1, day: 1 }, offset);
    for (let branch = 0; branch < 12; branch += 1) {
      cases.push({ ...date, hour: branch * 2 + 1, minute: 30, group: '六十日×十二时辰' });
    }
  }
  const termDays = [
    [1, 5], [1, 20], [2, 4], [2, 19], [3, 5], [3, 20],
    [4, 4], [4, 20], [5, 5], [5, 21], [6, 5], [6, 21],
    [7, 7], [7, 22], [8, 7], [8, 23], [9, 7], [9, 23],
    [10, 8], [10, 23], [11, 7], [11, 22], [12, 7], [12, 21],
  ];
  for (const [month, day] of termDays) {
    for (const delta of [-1, 0, 1]) {
      const date = addDays({ year: 2024, month, day }, delta);
      for (const [hour, minute] of [[0, 1], [11, 59], [23, 1]]) {
        cases.push({ ...date, hour, minute, group: '二十四节气边界' });
      }
    }
  }
  for (const date of [
    { year: 1901, month: 1, day: 1 }, { year: 2000, month: 2, day: 29 },
    { year: 2033, month: 12, day: 22 }, { year: 2099, month: 12, day: 31 },
  ]) {
    for (const [hour, minute] of [[0, 0], [22, 59], [23, 0], [23, 59]]) {
      cases.push({ ...date, hour, minute, group: '极端日期与子初换日' });
    }
  }
  const unique = new Map(cases.map((item) => [
    `${item.year}-${item.month}-${item.day}-${item.hour}-${item.minute}`, item,
  ]));
  return [...unique.values()];
}

function runProbe(probe, cases) {
  const result = spawnSync(probe, [], {
    input: JSON.stringify(cases), encoding: 'utf8', maxBuffer: 128 * 1024 * 1024,
  });
  if (result.status !== 0) throw new Error(`ZhouYiLab 导出失败：${result.stderr || result.stdout}`);
  return JSON.parse(result.stdout);
}

function verifyReferenceCommit(root, expected) {
  const result = spawnSync('git', ['-C', root, 'rev-parse', 'HEAD'], { encoding: 'utf8' });
  if (result.status !== 0) throw new Error(`无法读取参考库版本：${root}`);
  const actual = result.stdout.trim();
  if (actual !== expected) throw new Error(`参考库版本不一致：${root}\n预期 ${expected}\n实际 ${actual}`);
}

function first(value) { return Array.isArray(value) ? value[0] : value; }
function same(left, right) { return JSON.stringify(left) === JSON.stringify(right); }

function normalizeZhou(pan) {
  return {
    term: pan.solar_term, dun: pan.dun, ju: pan.ju, yuan: pan.yuan,
    xun: pan.xun_shou.replace(/旬$/, ''), zfStar: pan.zhi_fu.star,
    zfPalace: pan.zhi_fu.palace, zsGate: pan.zhi_shi.gate,
    zsPalace: pan.zhi_shi.palace,
    palaces: Object.fromEntries(pan.palaces.map((item) => [item.palace, {
      earth: item.earth_stem, heaven: item.heaven_stem, star: item.star,
      gate: item.gate, spirit: item.spirit,
    }])),
  };
}

function normalizeThreeMeta(chart) {
  return {
    term: chart.timeInfo.solarTerm, dun: chart.ju.type, ju: chart.ju.number,
    yuan: chart.yuan, xun: chart.timeInfo.xunShou,
    zfStar: chart.zhiFu.star, zfPalace: chart.zhiFu.position,
    zsGate: chart.zhiShi.gate, zsPalace: chart.zhiShi.position,
    palaces: Object.fromEntries(chart.palaces.map((item) => [item.position, {
      earth: first(item.earthlyStem), heaven: first(item.heavenlyStem),
      star: first(item.star), gate: item.gate, spirit: item.deity,
    }])),
  };
}

function normalizeIdavailable(value) {
  const yuan = ['上元', '中元', '下元'][value.meta.yuan];
  return {
    term: value.meta.term, dun: value.dun === 1 ? '阳遁' : '阴遁', ju: value.ju,
    yuan, xun: value.xunName, zfStar: value.zfStar, zfPalace: value.zfTo,
    zsGate: value.zsDoor, zsPalace: value.zsLuo,
    palaces: Object.fromEntries(Array.from({ length: 9 }, (_, index) => {
      const palace = index + 1;
      return [palace, {
        earth: value.di[palace], heaven: value.tian[palace],
        star: value.tianStar[palace],
        gate: value.door[palace] === '—' ? '无门' : value.door[palace],
        spirit: value.god[palace] === '—' ? '无神' : value.god[palace].replace('螣', '腾'),
      }];
    })),
  };
}

function compare(input, actual, reference, source) {
  const rows = [];
  const upstream = {
    term: actual.term === reference.term,
    yuan: actual.yuan === reference.yuan,
    ju: actual.ju === reference.ju && actual.dun === reference.dun,
    xun: actual.xun === reference.xun,
  };
  const push = (field, left, right, disposition = 'strict') => rows.push({
    input, source, field, match: same(left, right), zhouyilab: left, reference: right, disposition,
  });
  push('节气', actual.term, reference.term, 'term-boundary');
  push('阴阳遁', actual.dun, reference.dun, upstream.term ? 'strict' : 'cascade');
  push('三元', actual.yuan, reference.yuan, input.hour === 23 ? 'day-boundary' : 'strict');
  push('局数', actual.ju, reference.ju, upstream.term && upstream.yuan ? 'strict' : 'cascade');
  push('旬首', actual.xun, reference.xun, input.hour === 23 ? 'day-boundary' : 'strict');
  const coreDisposition = upstream.ju && upstream.xun ? 'strict' : 'cascade';
  push('值符星', actual.zfStar, reference.zfStar, coreDisposition);
  push('值符落宫', actual.zfPalace, reference.zfPalace, coreDisposition);
  push('值使门', actual.zsGate, reference.zsGate, coreDisposition);
  push('值使落宫', actual.zsPalace, reference.zsPalace, coreDisposition);
  for (let palace = 1; palace <= 9; palace += 1) {
    for (const [key, label] of Object.entries({ earth: '地盘干', heaven: '天盘干', star: '九星', gate: '八门', spirit: '八神' })) {
      push(`${palace}宫${label}`, actual.palaces[palace][key], reference.palaces[palace][key], coreDisposition);
    }
  }
  return rows;
}

function markdown(cases, rows, references) {
  const mismatch = rows.filter((item) => !item.match);
  const strict = mismatch.filter((item) => item.disposition === 'strict');
  const counts = Object.fromEntries(references.map((source) => {
    const selected = rows.filter((item) => item.source === source);
    return [source, { matched: selected.filter((item) => item.match).length, total: selected.length }];
  }));
  const lines = [
    '# 奇门遁甲三向对照', '',
    `- 样本：${cases.length} 局（六十日×十二时辰、二十四节气边界、子初换日、闰日、2033 与 2099 极端日期）`,
    `- 3meta 固定提交：${REFERENCE_COMMITS['three-meta-root']}`,
    `- idavailable/qimen 固定提交：${REFERENCE_COMMITS['idavailable-root']}`,
    ...references.map((source) => `- ZhouYiLab ↔ ${source}：${counts[source].matched}/${counts[source].total} 字段一致`),
    `- 严格待处理差异：${strict.length}；参考实现单方差异 ${mismatch.filter((x) => x.disposition === 'reference-divergence').length}；节气边界 ${mismatch.filter((x) => x.disposition === 'term-boundary').length}；换日口径 ${mismatch.filter((x) => x.disposition === 'day-boundary').length}；上游级联 ${mismatch.filter((x) => x.disposition === 'cascade').length}`,
    '- 口径：时家转盘、拆补、标准民用时间；idavailable 使用默认中五寄坤二，单方差异由 3meta 同字段一致结果裁决。',
    '', '## 严格差异（最多 200 项）', '',
  ];
  if (strict.length === 0) lines.push('无。');
  else {
    lines.push('| 参考实现 | 输入 | 字段 | ZhouYiLab | 参考值 |', '| --- | --- | --- | --- | --- |');
    for (const item of strict.slice(0, 200)) {
      const input = item.input;
      const stamp = `${input.year}-${input.month}-${input.day} ${input.hour}:${String(input.minute).padStart(2, '0')}`;
      lines.push(`| ${item.source} | ${stamp} | ${item.field} | ${JSON.stringify(item.zhouyilab)} | ${JSON.stringify(item.reference)} |`);
    }
  }
  lines.push('', '## 全部差异处置', '', '| 类别 | 数量 |', '| --- | ---: |');
  for (const disposition of ['strict', 'reference-divergence', 'term-boundary', 'day-boundary', 'cascade']) {
    lines.push(`| ${disposition} | ${mismatch.filter((item) => item.disposition === disposition).length} |`);
  }
  lines.push('');
  return lines.join('\n');
}

function main() {
  const args = parseArgs(process.argv);
  for (const [key, commit] of Object.entries(REFERENCE_COMMITS)) {
    verifyReferenceCommit(args[key], commit);
  }
  const { QimenChart } = require(path.join(args['three-meta-root'], 'lib', 'index.js'));
  const engine = require(path.join(args['idavailable-root'], 'public', 'engine.js'));
  const cases = buildCorpus();
  const actual = runProbe(args.probe, cases);
  const rows = [];
  for (let index = 0; index < cases.length; index += 1) {
    const input = cases[index];
    const stamp = `${input.year}-${String(input.month).padStart(2, '0')}-${String(input.day).padStart(2, '0')}T${String(input.hour).padStart(2, '0')}:${String(input.minute).padStart(2, '0')}:00`;
    const zhou = normalizeZhou(actual[index].pan);
    const threeMeta = normalizeThreeMeta(QimenChart.byDatetime(stamp));
    const idavailableValue = engine.cast(input.year, input.month, input.day, input.hour, input.minute, {
      clock: 'std', lon: 120, ziSwap: true, method: 'chaibu', pan: 'zhuan', tianqin: 'suirui', jg: 2,
    });
    const idavailable = normalizeIdavailable(idavailableValue);
    const threeRows = compare(input, zhou, threeMeta, '3meta 2.6.0');
    const idavailableRows = compare(input, zhou, idavailable, 'idavailable/qimen');
    const threeByField = new Map(threeRows.map((item) => [item.field, item]));
    for (const item of idavailableRows) {
      if (!item.match && item.disposition === 'strict' && threeByField.get(item.field)?.match) {
        item.disposition = 'reference-divergence';
      }
    }
    rows.push(...threeRows, ...idavailableRows);
  }
  fs.mkdirSync(args['output-dir'], { recursive: true });
  fs.writeFileSync(path.join(args['output-dir'], 'qi_men_comparison.json'), JSON.stringify({ cases, rows }, null, 2));
  fs.writeFileSync(path.join(args['output-dir'], 'qi_men_comparison.md'), markdown(cases, rows, ['3meta 2.6.0', 'idavailable/qimen']));
  const strict = rows.filter((item) => !item.match && item.disposition === 'strict').length;
  console.log(`奇门三向对照完成：${cases.length} 局，${rows.length} 字段，严格差异 ${strict}。`);
  if (strict !== 0) process.exitCode = 1;
}

main();
