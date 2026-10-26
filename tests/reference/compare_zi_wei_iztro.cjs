#!/usr/bin/env node
'use strict';

const fs = require('node:fs');
const path = require('node:path');
const { spawnSync } = require('node:child_process');

function parseArgs(argv) {
  const args = {};
  for (let index = 2; index < argv.length; index += 2) {
    const key = argv[index];
    const value = argv[index + 1];
    if (!key?.startsWith('--') || value === undefined) {
      throw new Error('用法：--probe <exe> --iztro-root <dir> --output-dir <dir>');
    }
    args[key.slice(2)] = value;
  }
  for (const required of ['probe', 'iztro-root', 'output-dir']) {
    if (!args[required]) throw new Error(`缺少 --${required}`);
  }
  return args;
}

function normalize(value) {
  if (Array.isArray(value)) return value.map(normalize);
  if (value && typeof value === 'object') {
    return Object.fromEntries(
      Object.entries(value)
        .sort(([left], [right]) => left.localeCompare(right, 'zh-CN'))
        .map(([key, item]) => [key, normalize(item)]),
    );
  }
  return value;
}

function same(left, right) {
  return JSON.stringify(normalize(left)) === JSON.stringify(normalize(right));
}

function starGroups(groups) {
  return groups.map((group = []) =>
    group.map((star) => star.name).sort((left, right) => left.localeCompare(right, 'zh-CN')),
  );
}

function iztroLayer(layer, includeStars = true) {
  const result = {
    index: layer.index,
    heavenly_stem: layer.heavenlyStem,
    earthly_branch: layer.earthlyBranch,
    mutagen: layer.mutagen,
  };
  if (includeStars) result.stars = starGroups(layer.stars);
  return result;
}

function formatValue(value) {
  if (Array.isArray(value)) return value.join('、');
  if (value && typeof value === 'object') return JSON.stringify(value);
  return String(value);
}

function comparisonRows(caseId, actual, expected) {
  const rows = [];
  const push = (scope, field, left, right, classification = '') => {
    rows.push({
      caseId,
      scope,
      field,
      match: same(left, right),
      zhouyilab: left,
      iztro: right,
      classification,
    });
  };

  const starPlacements = (palaces) => Object.fromEntries(
    palaces.flatMap((palace) => palace.major_stars.map((star) => [
      star.name, `${palace.index}:${star.brightness}`,
    ])),
  );
  const flowStarPlacements = (groups) => Object.fromEntries(
    groups.flatMap((stars, index) => stars.map((star) => [star, index])),
  );
  const pushFlowStars = (scope, left, right) => {
    const leftPositions = flowStarPlacements(left);
    const rightPositions = flowStarPlacements(right);
    const names = [...new Set([...Object.keys(leftPositions), ...Object.keys(rightPositions)])]
      .sort((a, b) => a.localeCompare(b, 'zh-CN'));
    for (const name of names) {
      let classification = '';
      if (name.endsWith('昌') || name.endsWith('曲')) {
        classification = '流昌流曲起法：ZhouYiLab 按地支，iztro 按天干';
      } else if (name.endsWith('魁') || name.endsWith('钺')) {
        classification = '天魁天钺安法口径';
      } else {
        classification = '流曜安置';
      }
      push(scope, `${name}宫位`, leftPositions[name] ?? '未见',
        rightPositions[name] ?? '未见', classification);
    }
  };

  push('本命', '生年干支', actual.natal.birth_year_pillar,
    expected.natal.birth_year_pillar, '农历年与立春年柱边界');
  push('本命', '命宫索引', actual.natal.soul_index, expected.natal.soul_index);
  push('本命', '身宫索引', actual.natal.body_index, expected.natal.body_index);
  push('本命', '五行局', actual.natal.five_elements_class, expected.natal.five_elements_class);
  const actualMajorStars = starPlacements(actual.natal.palaces);
  const expectedMajorStars = starPlacements(expected.natal.palaces);
  for (const name of Object.keys(expectedMajorStars).sort((a, b) => a.localeCompare(b, 'zh-CN'))) {
    push('本命', `${name}落宫:亮度`, actualMajorStars[name], expectedMajorStars[name],
      '十四主星安置或生年边界');
  }
  for (const scope of ['decadal', 'age', 'yearly', 'monthly', 'daily']) {
    const labels = {
      decadal: '大限',
      age: '小限',
      yearly: '流年',
      monthly: '流月',
      daily: '流日',
    };
    push(labels[scope], '宫位索引', actual.horoscope[scope].index,
      expected.horoscope[scope].index,
      ['monthly', 'daily'].includes(scope)
        ? '流月起宫漏计出生时辰；流日继承流月偏移'
        : scope === 'age'
          ? '小限顺逆：ZhouYiLab 按阴阳年＋性别；iztro 男顺女逆'
          : '');
    if (scope === 'age') continue;
    if (scope === 'decadal') {
      push(labels[scope], '起始虚岁', actual.horoscope[scope].start_age,
        expected.horoscope[scope].start_age, '起限年龄');
      push(labels[scope], '结束虚岁', actual.horoscope[scope].end_age,
        expected.horoscope[scope].end_age, '起限年龄');
    }
    push(labels[scope], '干支',
      `${actual.horoscope[scope].heavenly_stem}${actual.horoscope[scope].earthly_branch}`,
      `${expected.horoscope[scope].heavenly_stem}${expected.horoscope[scope].earthly_branch}`);
    push(labels[scope], '四化', actual.horoscope[scope].mutagen,
      expected.horoscope[scope].mutagen);
    pushFlowStars(labels[scope], actual.horoscope[scope].stars,
      expected.horoscope[scope].stars);
  }
  return rows;
}

function buildExpected(probe, iztroRoot) {
  const iztroPackage = path.join(iztroRoot, 'node_modules', 'iztro');
  const { astro } = require(iztroPackage);
  const { lunar2solar } = require(path.join(iztroRoot, 'node_modules', 'lunar-lite'));
  const { STARS_INFO } = require(path.join(iztroPackage, 'lib', 'data', 'stars.js'));
  const nameByKey = {
    ziweiMaj: '紫微', tianjiMaj: '天机', taiyangMaj: '太阳', wuquMaj: '武曲',
    tiantongMaj: '天同', lianzhenMaj: '廉贞', tianfuMaj: '天府', taiyinMaj: '太阴',
    tanlangMaj: '贪狼', jumenMaj: '巨门', tianxiangMaj: '天相',
    tianliangMaj: '天梁', qishaMaj: '七杀', pojunMaj: '破军',
    wenchangMin: '文昌', wenquMin: '文曲', huoxingMin: '火星',
    lingxingMin: '铃星', qingyangMin: '擎羊', tuoluoMin: '陀罗',
  };
  const brightnessName = {
    miao: '庙', wang: '旺', de: '得', li: '利', ping: '平', xian: '陷', bu: '不',
    '': '平',
  };
  const brightnessTables = Object.fromEntries(
    Object.entries(nameByKey).map(([key, name]) => [
      name,
      STARS_INFO[key].brightness.map((value) => brightnessName[value]),
    ]),
  );

  const cases = probe.cases.map((item) => {
    const chart = astro.withOptions({
      type: 'solar',
      dateStr: item.birth.solar_date,
      timeIndex: item.birth.time_index,
      gender: item.birth.gender,
      fixLeap: true,
      language: 'zh-CN',
      config: {
        algorithm: 'default',
        yearDivide: 'normal',
        horoscopeDivide: 'normal',
        ageDivide: 'normal',
        dayDivide: 'forward',
      },
    });
    const targetLunar = `${item.target.lunar_year}-${item.target.lunar_month}-${item.target.lunar_day}`;
    const targetSolar = lunar2solar(targetLunar).toString();
    const horoscope = chart.horoscope(targetSolar, item.target.time_index).toJSON();
    const palaces = chart.palaces.map((palace) => ({
      index: palace.index,
      heavenly_stem: palace.heavenlyStem,
      earthly_branch: palace.earthlyBranch,
      palace_name: palace.name,
      major_stars: palace.majorStars
        .map((star) => ({ name: star.name, brightness: star.brightness ?? '' }))
        .sort((left, right) => left.name.localeCompare(right.name, 'zh-CN')),
    }));
    return {
      id: item.id,
      natal: {
        birth_year_pillar: chart.rawDates.chineseDate.yearly.join(''),
        soul_index: chart.palaces.find((palace) => palace.earthlyBranch ===
          chart.earthlyBranchOfSoulPalace).index,
        body_index: chart.palaces.find((palace) => palace.earthlyBranch ===
          chart.earthlyBranchOfBodyPalace).index,
        five_elements_class: chart.fiveElementsClass,
        palaces,
      },
      horoscope: {
        decadal: {
          ...iztroLayer(horoscope.decadal),
          start_age: chart.palaces[horoscope.decadal.index].decadal.range[0],
          end_age: chart.palaces[horoscope.decadal.index].decadal.range[1],
        },
        age: { index: horoscope.age.index, nominal_age: horoscope.age.nominalAge },
        yearly: iztroLayer(horoscope.yearly),
        monthly: iztroLayer(horoscope.monthly),
        daily: iztroLayer(horoscope.daily),
      },
    };
  });
  return { brightnessTables, cases };
}

function markdownReport(probe, expected, rows, iztroVersion) {
  const failed = rows.filter((row) => !row.match);
  const failureGroups = Object.entries(
    failed.reduce((groups, row) => {
      const key = row.classification || '待核';
      groups[key] = (groups[key] ?? 0) + 1;
      return groups;
    }, {}),
  ).sort((left, right) => right[1] - left[1]);
  const lines = [
    `# ZhouYiLab 与 iztro 紫微斗数 ${probe.cases.length} 个命例对照`,
    '',
    `- ZhouYiLab 输出契约：\`${probe.schema}\``,
    `- 参考实现：\`SylarLong/iztro ${iztroVersion}\`（MIT）`,
    '- iztro 配置：default 安星、农历年分界、自然年小限、晚子时进次日',
    '- 亮度口径：ZhouYiLab `BrightnessSchool::Iztro`；擎羊/陀罗上游空值按普通档“平”归一化',
    `- 比对断言：${rows.length}，一致 ${rows.length - failed.length}，差异 ${failed.length}`,
    '',
    '## 亮度表',
    '',
    `20 星 × 12 宫共 240 格：${same(probe.brightness_tables, expected.brightnessTables) ? '全部一致' : '存在差异'}。`,
    '',
    '## 差异聚类',
    '',
    ...(failureGroups.length === 0
      ? ['未发现差异。']
      : ['| 分类 | 数量 |', '| --- | ---: |',
        ...failureGroups.map(([name, count]) => `| ${name} | ${count} |`)]),
    '',
    '## 命例覆盖',
    '',
    '| 命例 | 出生 | 性别 | 目标农历 | 覆盖方向 |',
    '| --- | --- | --- | --- | --- |',
  ];
  for (const item of probe.cases) {
    lines.push(`| ${item.id} | ${item.birth.solar_date} ${item.birth.hour}时 | ${item.birth.gender} | ` +
      `${item.target.lunar_year}-${item.target.lunar_month}-${item.target.lunar_day} | ${item.coverage} |`);
  }
  lines.push('', '## 分项汇总', '', '| 命例 | 本命 | 大限 | 小限 | 流年 | 流月 | 流日 |',
    '| --- | --- | --- | --- | --- | --- | --- |');
  for (const item of probe.cases) {
    const cells = ['本命', '大限', '小限', '流年', '流月', '流日'].map((scope) => {
      const subset = rows.filter((row) => row.caseId === item.id && row.scope === scope);
      const matched = subset.filter((row) => row.match).length;
      return matched === subset.length ? `一致（${matched}/${subset.length}）` :
        `通过 ${matched}/${subset.length}（差异 ${subset.length - matched}）`;
    });
    lines.push(`| ${item.id} | ${cells.join(' | ')} |`);
  }
  lines.push('', '## 差异明细', '');
  if (failed.length === 0) {
    lines.push('未发现差异。');
  } else {
    lines.push('| 命例 | 层次 | 字段 | ZhouYiLab | iztro | 分类 |',
      '| --- | --- | --- | --- | --- | --- |');
    for (const row of failed) {
      lines.push(`| ${row.caseId} | ${row.scope} | ${row.field} | ${formatValue(row.zhouyilab)} | ` +
        `${formatValue(row.iztro)} | ${row.classification || '待核'} |`);
    }
  }
  lines.push('', '## 判读边界', '',
    '- 本报告证明的是两个锁定实现及配置之间的字段一致性，不证明任何术数流派是唯一正确答案。',
    '- 小限只比较落宫；ZhouYiLab 当前契约没有小限干支、四化和流曜，不能伪称这些字段已经对齐。',
    '- 流月一旦起宫不同，流日会继承偏移；应先解决流月起法，再判断后续层级。',
    '');
  return lines.join('\n');
}

function main() {
  const args = parseArgs(process.argv);
  const outputDir = path.resolve(args['output-dir']);
  fs.mkdirSync(outputDir, { recursive: true });
  const probeJsonPath = path.join(outputDir, 'zhouyilab_zi_wei_20_cases.json');
  const run = spawnSync(path.resolve(args.probe), [probeJsonPath], { encoding: 'utf8' });
  if (run.status !== 0) {
    process.stderr.write(run.stdout ?? '');
    process.stderr.write(run.stderr ?? '');
    process.exit(run.status ?? 1);
  }

  const probe = JSON.parse(fs.readFileSync(probeJsonPath, 'utf8'));
  const expected = buildExpected(probe, path.resolve(args['iztro-root']));
  const rows = probe.cases.flatMap((item) =>
    comparisonRows(item.id, item, expected.cases.find((value) => value.id === item.id)),
  );
  rows.unshift({
    caseId: '全局', scope: '亮度', field: '20星×12宫',
    match: same(probe.brightness_tables, expected.brightnessTables),
    zhouyilab: probe.brightness_tables, iztro: expected.brightnessTables,
    classification: 'Iztro 亮度表',
  });

  const iztroPackage = JSON.parse(fs.readFileSync(
    path.join(path.resolve(args['iztro-root']), 'node_modules', 'iztro', 'package.json'), 'utf8'));
  const report = markdownReport(probe, expected, rows, iztroPackage.version);
  fs.writeFileSync(path.join(outputDir, 'zi_wei_iztro_20_case_comparison.md'), report, 'utf8');
  fs.writeFileSync(path.join(outputDir, 'zi_wei_iztro_20_case_comparison.json'),
    JSON.stringify({ iztro_version: iztroPackage.version, rows }, null, 2), 'utf8');

  const failed = rows.filter((row) => !row.match);
  console.log(`完成 ${rows.length} 项跨实现断言：一致 ${rows.length - failed.length}，差异 ${failed.length}`);
  console.log(path.join(outputDir, 'zi_wei_iztro_20_case_comparison.md'));
  process.exitCode = failed.length === 0 ? 0 : 1;
}

main();
