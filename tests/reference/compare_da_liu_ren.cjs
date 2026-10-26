#!/usr/bin/env node
'use strict';

const fs = require('node:fs');
const path = require('node:path');
const { spawnSync } = require('node:child_process');

const ZHI = [...'子丑寅卯辰巳午未申酉戌亥'];

function parseArgs(argv) {
  const args = {};
  for (let index = 2; index < argv.length; index += 2) {
    const key = argv[index];
    const value = argv[index + 1];
    if (!key?.startsWith('--') || value === undefined) {
      throw new Error('用法：--probe <exe> --liuyao-root <dir> --kinliuren-root <dir> --output-dir <dir>');
    }
    args[key.slice(2)] = value;
  }
  for (const required of ['probe', 'liuyao-root', 'kinliuren-root', 'output-dir']) {
    if (!args[required]) throw new Error(`缺少 --${required}`);
  }
  return args;
}

function addDays(date, days) {
  const result = new Date(Date.UTC(date.year, date.month - 1, date.day + days));
  return { year: result.getUTCFullYear(), month: result.getUTCMonth() + 1, day: result.getUTCDate() };
}

// 外部基准领入：完整六十日循环 × 十二时辰；另加入中气换将、世纪边界与闰日。
function buildCorpus() {
  const dates = [];
  for (let offset = 0; offset < 60; offset++) dates.push(addDays({ year: 2026, month: 1, day: 1 }, offset));
  for (const month of Array.from({ length: 12 }, (_, index) => index + 1)) {
    for (const day of [4, 19, 20, 21]) dates.push({ year: 2025, month, day });
  }
  dates.push(
    { year: 1900, month: 1, day: 31 }, { year: 2000, month: 2, day: 29 },
    { year: 2033, month: 12, day: 22 }, { year: 2099, month: 12, day: 31 },
  );
  const unique = new Map(dates.map((date) => [`${date.year}-${date.month}-${date.day}`, date]));
  return [...unique.values()].flatMap((date) =>
    Array.from({ length: 12 }, (_, index) => ({ ...date, hour: index * 2 + 1, minute: 30 })));
}

function run(command, args, options = {}) {
  const result = spawnSync(command, args, { encoding: 'utf8', maxBuffer: 64 * 1024 * 1024, ...options });
  if (result.status !== 0) throw new Error(`${command} 执行失败：${result.stderr || result.stdout}`);
  return result.stdout;
}

function normalizeMethod(patterns) {
  const text = Array.isArray(patterns) ? patterns.join('/') : String(patterns ?? '');
  for (const method of ['伏吟', '返吟', '反吟', '涉害', '遥克', '昴星', '别责', '八专', '贼克']) {
    if (text.includes(method)) return method === '反吟' ? '返吟' : method;
  }
  return text;
}

function same(left, right) { return JSON.stringify(left) === JSON.stringify(right); }

function compareCase(input, actual, reference, source) {
  const rows = [];
  const push = (field, left, right, classification = '', disposition = 'strict') => rows.push({
    input, source, field, match: same(left, right), zhouyilab: left, reference: right,
    classification, disposition,
  });
  const dayMatches = actual.day_gan === reference.day_gan && actual.day_zhi === reference.day_zhi;
  const dayDisposition = input.hour === 23 ? 'school' : 'strict';
  push('日干', actual.day_gan, reference.day_gan, '子初换日与民用午夜换日口径', dayDisposition);
  push('日支', actual.day_zhi, reference.day_zhi, '子初换日与民用午夜换日口径', dayDisposition);
  push('时支', actual.hour_zhi, reference.hour_zhi, '历法');
  const generalMatches = actual.yue_jiang === reference.yue_jiang;
  push('月将', actual.yue_jiang, reference.yue_jiang,
    '太阳过宫歌诀与中气换将口径', 'school');
  push('天盘', actual.tian_pan, reference.tian_pan, '月将加时',
    generalMatches ? 'strict' : 'cascade');
  const plateMatches = same(actual.tian_pan, reference.tian_pan);
  push('四课', actual.si_ke, reference.si_ke, '日干寄宫与四课递推',
    dayMatches && plateMatches ? 'strict' : 'cascade');
  const lessonsMatch = same(actual.si_ke, reference.si_ke);
  const actualMethod = normalizeMethod(actual.san_chuan.patterns);
  const referenceGap = source === 'Crazycreate/liuyao'
    && ['别责', '八专'].includes(actualMethod)
    && !String(reference.method).includes(actualMethod);
  const methodDisposition = referenceGap
    ? 'reference-gap'
    : actualMethod === '伏吟'
      ? 'classical-adjudicated'
      : actualMethod === '涉害' && String(reference.method).includes('涉害')
        ? 'school'
        : 'strict';
  push('三传', [actual.san_chuan.chu, actual.san_chuan.zhong, actual.san_chuan.mo],
    [reference.san_chuan.chu, reference.san_chuan.zhong, reference.san_chuan.mo],
    `九宗门取传：ZhouYiLab=${actualMethod}；参考=${reference.method}`,
    lessonsMatch ? methodDisposition : 'cascade');
  push('贵人', actual.gui_ren, reference.gui_ren, '协纪昼夜贵人表',
    dayMatches ? 'strict' : 'cascade');
  push('昼夜', actual.is_day, reference.is_day, '卯至申为昼、酉至寅为夜');
  return rows;
}

function crazyReference(castLiuren, input) {
  const result = castLiuren(input);
  return {
    day_gan: result.moment.dayGan, day_zhi: result.moment.dayZhi,
    hour_zhi: result.moment.shiZhi, yue_jiang: result.moment.yuejiang,
    tian_pan: result.tianpan,
    si_ke: result.sike.map(({ xia, shang }) => ({ lower: xia, upper: shang })),
    san_chuan: result.sanchuan, gui_ren: result.tianjiang.noble,
    is_day: result.tianjiang.isDay, method: result.sanchuan.method,
  };
}

function runKinliuren(root, cases) {
  const adapter = String.raw`
import json, sys
sys.path.insert(0, sys.argv[1])
from kinliuren import kinliuren
ZHI = list('子丑寅卯辰巳午未申酉戌亥')
JQ = {'亥':'雨水','戌':'春分','酉':'穀雨','申':'小滿','未':'夏至','午':'大暑','巳':'處暑','辰':'秋分','卯':'霜降','寅':'小雪','丑':'冬至','子':'大寒'}
out = []
for item in json.load(sys.stdin):
    try:
        value = kinliuren.Liuren(JQ[item['yue_jiang']], '正', item['day_gan'] + item['day_zhi'], '甲' + item['hour_zhi']).result(0)
        sky = value['天地盤']['天盤']; earth = value['天地盤']['地盤']; generals = value['天地盤']['天將']
        sky_at = dict(zip(earth, sky)); four = value['四課']; lessons = []
        for key in ['一課','二課','三課','四課']:
            pair = four[key][0]; lessons.append({'lower': pair[1], 'upper': pair[0]})
        transmissions = value['三傳']
        noble = next(sky[index] for index, general in enumerate(generals) if general == '貴')
        out.append({'ok': True, 'tian_pan': [sky_at[z] for z in ZHI], 'si_ke': lessons,
                    'san_chuan': {'chu': transmissions['初傳'][0], 'zhong': transmissions['中傳'][0], 'mo': transmissions['末傳'][0]},
                    'gui_ren': noble, 'method': value['格局'][0]})
    except Exception as error:
        out.append({'ok': False, 'error': type(error).__name__ + ': ' + str(error)})
json.dump(out, sys.stdout, ensure_ascii=False)
`;
  const result = spawnSync('python', ['-c', adapter, path.join(root, 'src')], {
    input: JSON.stringify(cases), encoding: 'utf8', maxBuffer: 64 * 1024 * 1024,
    env: { ...process.env, PYTHONIOENCODING: 'utf-8' },
  });
  if (result.status !== 0) throw new Error(`kinliuren 适配器执行失败：${result.stderr || result.stdout}`);
  return JSON.parse(result.stdout);
}

function markdown(summary, methodCoverage, mismatches, reverseCases, kinSummary) {
  const dispositions = Object.fromEntries(
    ['strict', 'school', 'cascade', 'reference-gap', 'classical-adjudicated', 'consensus-adjudicated'].map((name) => [
      name, mismatches.filter((item) => item.disposition === name).length,
    ]),
  );
  const lines = [
    '# 大六壬跨实现双向对照', '',
    `- ZhouYiLab ↔ Crazycreate/liuyao：${summary.matched}/${summary.total} 字段一致（${summary.cases} 课）`,
    `- 三角抽样（另与 kinliuren 比较）：${kinSummary.matched}/${kinSummary.total} 字段一致（${kinSummary.cases} 课，异常 ${kinSummary.errors}）`,
    `- 差异处置：待裁决 ${dispositions.strict}，古籍已裁决 ${dispositions['classical-adjudicated']}，两实现共识 ${dispositions['consensus-adjudicated']}，流派口径 ${dispositions.school}，上游级联 ${dispositions.cascade}，参考实现缺口 ${dispositions['reference-gap']}`,
    '', '## 九宗门覆盖', '', '| 方法 | 课数 | 反向代表输入 |', '| --- | ---: | --- |',
    ...Object.entries(methodCoverage).sort().map(([method, count]) => {
      const representative = reverseCases[method];
      const label = representative ? `${representative.year}-${representative.month}-${representative.day} ${representative.hour}:30` : '未覆盖';
      return `| ${method} | ${count} | ${label} |`;
    }),
    '', '## 不一致项（最多列 100 项）', '',
  ];
  if (mismatches.length === 0) lines.push('无。');
  else {
    lines.push('| 来源 | 输入 | 字段 | ZhouYiLab | 参考实现 | 分类 | 处置 |', '| --- | --- | --- | --- | --- | --- | --- |');
    for (const item of mismatches.slice(0, 100)) {
      const input = `${item.input.year}-${item.input.month}-${item.input.day} ${item.input.hour}:${String(item.input.minute).padStart(2, '0')}`;
      const value = (entry) => JSON.stringify(entry).replaceAll('|', '\\|');
      lines.push(`| ${item.source} | ${input} | ${item.field} | ${value(item.zhouyilab)} | ${value(item.reference)} | ${item.classification} | ${item.disposition} |`);
    }
  }
  lines.push('');
  return lines.join('\n');
}

function pathToFileUrl(file) {
  const resolved = path.resolve(file).replaceAll('\\', '/');
  return new URL(`file:///${resolved}`);
}

async function main() {
  const args = parseArgs(process.argv);
  const liuyaoModule = await import(pathToFileUrl(path.join(args['liuyao-root'], 'dist/liuren/index.js')));
  const corpus = buildCorpus();
  const actual = JSON.parse(run(args.probe, [], { input: JSON.stringify(corpus) }));
  const crazy = corpus.map((input) => crazyReference(liuyaoModule.castLiuren, input));
  const rows = actual.flatMap((item, index) => compareCase(item.input, item.pan, crazy[index], 'Crazycreate/liuyao'));

  // ZhouYiLab 反向领出：按自身九宗门结果挑第一课代表，再交给两个外部实现复核。
  const reverseCases = {};
  const methodCoverage = {};
  for (let index = 0; index < actual.length; index++) {
    const method = normalizeMethod(actual[index].pan.san_chuan.patterns);
    methodCoverage[method] = (methodCoverage[method] ?? 0) + 1;
    reverseCases[method] ??= corpus[index];
  }

  const selectedIndices = [...new Set([
    ...Array.from({ length: 32 }, (_, index) => index * 12),
    ...Object.values(reverseCases).map((input) => corpus.findIndex((candidate) => same(candidate, input))),
  ])].filter((index) => index >= 0);
  const kinInputs = selectedIndices.map((index) => crazy[index]);
  const kinResults = runKinliuren(args['kinliuren-root'], kinInputs);
  const kinRows = [];
  let kinErrors = 0;
  const kinErrorDetails = [];
  for (let offset = 0; offset < selectedIndices.length; offset++) {
    const index = selectedIndices[offset];
    const reference = kinResults[offset];
    if (!reference.ok) {
      kinErrors++;
      kinErrorDetails.push({ input: corpus[index], error: reference.error });
      continue;
    }
    const actualMethod = normalizeMethod(actual[index].pan.san_chuan.patterns);
    for (const [field, left, right, classification] of [
      ['天盘', actual[index].pan.tian_pan, reference.tian_pan, '月将加时'],
      ['四课', actual[index].pan.si_ke, reference.si_ke, '日干寄宫与四课递推'],
      ['三传', [actual[index].pan.san_chuan.chu, actual[index].pan.san_chuan.zhong, actual[index].pan.san_chuan.mo],
        [reference.san_chuan.chu, reference.san_chuan.zhong, reference.san_chuan.mo], '九宗门取传'],
      ['贵人', actual[index].pan.gui_ren, reference.gui_ren, '协纪昼夜贵人表'],
    ]) {
      const crazyValue = {
        天盘: crazy[index].tian_pan,
        四课: crazy[index].si_ke,
        三传: [crazy[index].san_chuan.chu, crazy[index].san_chuan.zhong, crazy[index].san_chuan.mo],
        贵人: crazy[index].gui_ren,
      }[field];
      const disposition = field === '三传' && ['伏吟', '八专'].includes(actualMethod)
        ? 'classical-adjudicated'
        : same(left, crazyValue)
          ? 'consensus-adjudicated'
          : 'strict';
      kinRows.push({
        input: corpus[index], source: 'kinliuren', field, match: same(left, right),
        zhouyilab: left, reference: right,
        classification: field === '三传'
          ? `${classification}：ZhouYiLab=${actualMethod}；参考=${reference.method}`
          : classification,
        disposition,
      });
    }
  }

  const allRows = [...rows, ...kinRows];
  const mismatches = allRows.filter((row) => !row.match);
  const strictMismatches = mismatches.filter((row) => row.disposition === 'strict');
  const summary = { cases: corpus.length, total: rows.length, matched: rows.filter((row) => row.match).length };
  const kinSummary = { cases: selectedIndices.length, total: kinRows.length, matched: kinRows.filter((row) => row.match).length, errors: kinErrors };
  fs.mkdirSync(args['output-dir'], { recursive: true });
  fs.writeFileSync(path.join(args['output-dir'], 'da_liu_ren_comparison.json'), JSON.stringify({ summary, kinSummary, methodCoverage, reverseCases, kinErrorDetails, strictMismatchCount: strictMismatches.length, mismatches }, null, 2));
  fs.writeFileSync(path.join(args['output-dir'], 'da_liu_ren_comparison.md'), markdown(summary, methodCoverage, mismatches, reverseCases, kinSummary));
  console.log(JSON.stringify({ summary, kinSummary, methodCoverage, mismatches: mismatches.length, strictMismatches: strictMismatches.length }, null, 2));
  process.exitCode = strictMismatches.length === 0 ? 0 : 2;
}

main().catch((error) => { console.error(error.stack || error); process.exitCode = 1; });
