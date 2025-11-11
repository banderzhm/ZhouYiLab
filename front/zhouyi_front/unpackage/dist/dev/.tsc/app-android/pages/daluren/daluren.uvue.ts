
	const __sfc__ = defineComponent({
		data() {
			return {
				// 基本信息
				dateInfo: '2025-04-16 (三月十九) 15:56 (真太阳)',
				pillars: ['乙 巳', '庚 辰', '乙 卯', '甲 申'],
				details: '乙卯 日 甲申 时 (正时) 戌 将',
				detailsExtra: '甲寅 旬 子丑 空 | 驿马: 巳 | 丁马: 巳 | 天马: 戌',
				chartType: '重审, 间传, 涉三渊',
				subject: '问感情发展',
				
				// 三传数据
				sanChuan: [
					{ relation: '官', dunGan: '庚', branch: '申', general: '勾' },
					{ relation: '财', dunGan: '壬', branch: '戌', general: '雀' },
					{ relation: '父', dunGan: '己', branch: '子', general: '贵' }
				],
				
				// 四课数据
				siKe: {
					generals: ['蛇', '青', '勾', '常'],
					heavenPlate: ['戌', '午', '未', '卯'],
					earthPlate: ['午', '寅', '卯', '壬']
				},
				
				// 排盘布局顺序 - 按行定义地支显示顺序
				layout: {
					row1: ['巳', '午', '未', '申'],  // 第一行
					row2: ['辰', '酉'],              // 第二行（左右两侧，中间是三传）
					row3: ['卯', '戌'],              // 第三行（左右两侧，中间是四课）
					row4: ['寅', '丑', '子', '亥']    // 第四行
				},
				
				// 12地支数据 - 按照地支顺序存储：子丑寅卯辰巳午未申酉戌亥
				dizhiData: {
					'子': {
						shensha: ['劫煞', '孤辰', '地医'],
						relations: { gan: '干:沐浴', zhi: '支:沐浴', tian: '天:冠带' },
						general: '后',
						heavenPlate: '卯'
					},
					'丑': {
						shensha: ['空亡', '华盖', '月刑'],
						relations: { gan: '干:冠带', zhi: '支:冠带', tian: '天:病' },
						general: '贵',
						heavenPlate: '辰'
					},
					'寅': {
						shensha: ['亡神', '天贼', '血支'],
						relations: { gan: '干:临官', zhi: '支:临官', tian: '天:冠带' },
						general: '蛇',
						heavenPlate: '巳'
					},
					'卯': {
						shensha: ['日禄', '桃花', '将星'],
						relations: { gan: '干:帝旺', zhi: '支:帝旺', tian: '天:病' },
						general: '雀',
						heavenPlate: '午'
					},
					'辰': {
						shensha: ['天喜', '小耗', '月破'],
						relations: { gan: '干:胎', zhi: '支:胎', tian: '天:绝' },
						general: '合',
						heavenPlate: '未'
					},
					'巳': {
						shensha: ['驿马', '火鬼', '医廉'],
						relations: { gan: '干:绝', zhi: '支:绝', tian: '天:沐浴' },
						general: '勾',
						heavenPlate: '申'
					},
					'午': {
						shensha: ['日德', '死符', '死气', '飞廉', '月厌'],
						relations: { gan: '干:病', zhi: '支:病', tian: '天:冠带' },
						general: '龙',
						heavenPlate: '酉'
					},
					'未': {
						shensha: ['月建', '破碎', '丧门', '咸池'],
						relations: { gan: '干:衰', zhi: '支:衰', tian: '天:冠带' },
						general: '空',
						heavenPlate: '戌'
					},
					'申': {
						shensha: ['天马', '天解', '神耗'],
						relations: { gan: '干:死', zhi: '支:死', tian: '天:沐浴' },
						general: '虎',
						heavenPlate: '亥'
					},
					'酉': {
						shensha: ['咸池', '大耗', '岁破'],
						relations: { gan: '干:墓', zhi: '支:墓', tian: '天:长生' },
						general: '常',
						heavenPlate: '子'
					},
					'戌': {
						shensha: ['月德', '合德', '天医'],
						relations: { gan: '干:长生', zhi: '支:长生', tian: '天:沐浴' },
						general: '阴',
						heavenPlate: '寅'
					},
					'亥': {
						shensha: ['官符', '寡宿', '月煞'],
						relations: { gan: '干:养', zhi: '支:养', tian: '天:长生' },
						general: '玄',
						heavenPlate: '丑'
					}
				},
				
				// 底部标签
				footerTabs: ['直指', '集应铃', '神煞', '案例', '占法', '取象', '批注', '⚙️']
			}
		},
		onLoad() {
			
		},
		methods: {
			// 根据地支名称获取数据
			getDizhiData(dizhi : string) {
				return this.dizhiData[dizhi]
			},
			
			// 获取天干的 CSS 类名
			getTianganClass(tiangan : string) : string {
				const tianganMap : Map<string, string> = new Map([
					['甲', 'tiangan-jia'],
					['乙', 'tiangan-yi'],
					['丙', 'tiangan-bing'],
					['丁', 'tiangan-ding'],
					['戊', 'tiangan-wu'],
					['己', 'tiangan-ji'],
					['庚', 'tiangan-geng'],
					['辛', 'tiangan-xin'],
					['壬', 'tiangan-ren'],
					['癸', 'tiangan-gui']
				])
				return tianganMap.get(tiangan) || ''
			},
			
			// 获取四课地盘的颜色类（可能是天干或地支）
			getEarthPlateClass(item : string) : string {
				// 先判断是否为天干
				const tianganClass = this.getTianganClass(item)
				if (tianganClass) {
					return tianganClass
				}
				// 否则是地支
				return 'dizhi-' + item
			}
		}
	})

export default __sfc__
function GenPagesDalurenDalurenRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("scroll-view", utsMapOf({ class: "page-body" }), [
    createElementVNode("view", utsMapOf({ class: "chart-container" }), [
      createElementVNode("view", utsMapOf({ class: "info-section" }), [
        createElementVNode("text", utsMapOf({ class: "info-date" }), toDisplayString(_ctx.dateInfo), 1 /* TEXT */),
        createElementVNode("view", utsMapOf({ class: "pillars" }), [
          createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.pillars, (pillar, index, __index, _cached): any => {
            return createElementVNode("view", utsMapOf({
              class: "pillar-span",
              key: index
            }), [
              createElementVNode("text", utsMapOf({
                class: normalizeClass(_ctx.getTianganClass(pillar.split(' ')[0]))
              }), toDisplayString(pillar.split(' ')[0]), 3 /* TEXT, CLASS */),
              createElementVNode("text"),
              createElementVNode("text", utsMapOf({
                class: normalizeClass('dizhi-' + pillar.split(' ')[1])
              }), toDisplayString(pillar.split(' ')[1]), 3 /* TEXT, CLASS */)
            ])
          }), 128 /* KEYED_FRAGMENT */)
        ]),
        createElementVNode("text", utsMapOf({ class: "details" }), toDisplayString(_ctx.details), 1 /* TEXT */),
        createElementVNode("text", utsMapOf({ class: "details details-muted" }), toDisplayString(_ctx.detailsExtra), 1 /* TEXT */),
        createElementVNode("text", utsMapOf({ class: "chart-type" }), toDisplayString(_ctx.chartType), 1 /* TEXT */)
      ]),
      createElementVNode("view", utsMapOf({ class: "plate-outer-padding" }), [
        createElementVNode("view", utsMapOf({ class: "plate-grid-area" }), [
          createElementVNode("view", utsMapOf({ class: "plate-row" }), [
            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.layout.row1, (dizhi, index, __index, _cached): any => {
              return createElementVNode("view", utsMapOf({
                class: "grid-cell plate-position",
                key: dizhi
              }), [
                createElementVNode("view", utsMapOf({ class: "plate-shensha" }), [
                  createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.dizhiData[dizhi].shensha, (sha, idx, __index, _cached): any => {
                    return createElementVNode("text", utsMapOf({
                      class: "shensha-item",
                      key: idx
                    }), toDisplayString(sha), 1 /* TEXT */)
                  }), 128 /* KEYED_FRAGMENT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "plate-bottom-content" }), [
                  createElementVNode("view", utsMapOf({ class: "plate-relations" }), [
                    createElementVNode("text", null, toDisplayString(_ctx.dizhiData[dizhi].relations.gan), 1 /* TEXT */),
                    createElementVNode("text", null, toDisplayString(_ctx.dizhiData[dizhi].relations.zhi), 1 /* TEXT */),
                    createElementVNode("text", null, toDisplayString(_ctx.dizhiData[dizhi].relations.tian), 1 /* TEXT */)
                  ]),
                  createElementVNode("view", utsMapOf({ class: "plate-main" }), [
                    createElementVNode("text", utsMapOf({ class: "general" }), toDisplayString(_ctx.dizhiData[dizhi].general), 1 /* TEXT */),
                    createElementVNode("view", utsMapOf({ class: "branches" }), [
                      createElementVNode("text", utsMapOf({
                        class: normalizeClass('earth-plate dizhi-' + dizhi)
                      }), toDisplayString(dizhi), 3 /* TEXT, CLASS */),
                      createElementVNode("text", utsMapOf({
                        class: normalizeClass('heaven-plate dizhi-' + _ctx.dizhiData[dizhi].heavenPlate)
                      }), toDisplayString(_ctx.dizhiData[dizhi].heavenPlate), 3 /* TEXT, CLASS */)
                    ])
                  ])
                ])
              ])
            }), 128 /* KEYED_FRAGMENT */)
          ]),
          createElementVNode("view", utsMapOf({ class: "plate-row" }), [
            createElementVNode("view", utsMapOf({ class: "grid-cell plate-position" }), [
              createElementVNode("view", utsMapOf({ class: "plate-shensha" }), [
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.dizhiData['辰'].shensha, (sha, idx, __index, _cached): any => {
                  return createElementVNode("text", utsMapOf({
                    class: "shensha-item",
                    key: idx
                  }), toDisplayString(sha), 1 /* TEXT */)
                }), 128 /* KEYED_FRAGMENT */)
              ]),
              createElementVNode("view", utsMapOf({ class: "plate-bottom-content" }), [
                createElementVNode("view", utsMapOf({ class: "plate-relations" }), [
                  createElementVNode("text", null, toDisplayString(_ctx.dizhiData['辰'].relations.gan), 1 /* TEXT */),
                  createElementVNode("text", null, toDisplayString(_ctx.dizhiData['辰'].relations.zhi), 1 /* TEXT */),
                  createElementVNode("text", null, toDisplayString(_ctx.dizhiData['辰'].relations.tian), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "plate-main" }), [
                  createElementVNode("text", utsMapOf({ class: "general" }), toDisplayString(_ctx.dizhiData['辰'].general), 1 /* TEXT */),
                  createElementVNode("view", utsMapOf({ class: "branches" }), [
                    createElementVNode("text", utsMapOf({ class: "earth-plate dizhi-chen" }), "辰"),
                    createElementVNode("text", utsMapOf({
                      class: normalizeClass('heaven-plate dizhi-' + _ctx.dizhiData['辰'].heavenPlate)
                    }), toDisplayString(_ctx.dizhiData['辰'].heavenPlate), 3 /* TEXT, CLASS */)
                  ])
                ])
              ])
            ]),
            createElementVNode("view", utsMapOf({ class: "center-content-cell" }), [
              createElementVNode("view", utsMapOf({ class: "center-san-chuan" }), [
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.sanChuan, (sc, index, __index, _cached): any => {
                  return createElementVNode("view", utsMapOf({
                    class: "san-chuan-line",
                    key: index
                  }), [
                    createElementVNode("text", utsMapOf({ class: "relation" }), toDisplayString(sc.relation), 1 /* TEXT */),
                    createElementVNode("text", utsMapOf({
                      class: normalizeClass('dun-gan ' + _ctx.getTianganClass(sc.dunGan))
                    }), toDisplayString(sc.dunGan), 3 /* TEXT, CLASS */),
                    createElementVNode("text", utsMapOf({
                      class: normalizeClass('branch dizhi-' + sc.branch)
                    }), toDisplayString(sc.branch), 3 /* TEXT, CLASS */),
                    createElementVNode("text", utsMapOf({ class: "general-name" }), toDisplayString(sc.general), 1 /* TEXT */)
                  ])
                }), 128 /* KEYED_FRAGMENT */)
              ])
            ]),
            createElementVNode("view", utsMapOf({ class: "grid-cell plate-position" }), [
              createElementVNode("view", utsMapOf({ class: "plate-shensha" }), [
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.dizhiData['酉'].shensha, (sha, idx, __index, _cached): any => {
                  return createElementVNode("text", utsMapOf({
                    class: "shensha-item",
                    key: idx
                  }), toDisplayString(sha), 1 /* TEXT */)
                }), 128 /* KEYED_FRAGMENT */)
              ]),
              createElementVNode("view", utsMapOf({ class: "plate-bottom-content" }), [
                createElementVNode("view", utsMapOf({ class: "plate-relations" }), [
                  createElementVNode("text", null, toDisplayString(_ctx.dizhiData['酉'].relations.gan), 1 /* TEXT */),
                  createElementVNode("text", null, toDisplayString(_ctx.dizhiData['酉'].relations.zhi), 1 /* TEXT */),
                  createElementVNode("text", null, toDisplayString(_ctx.dizhiData['酉'].relations.tian), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "plate-main" }), [
                  createElementVNode("text", utsMapOf({ class: "general" }), toDisplayString(_ctx.dizhiData['酉'].general), 1 /* TEXT */),
                  createElementVNode("view", utsMapOf({ class: "branches" }), [
                    createElementVNode("text", utsMapOf({ class: "earth-plate dizhi-you" }), "酉"),
                    createElementVNode("text", utsMapOf({
                      class: normalizeClass('heaven-plate dizhi-' + _ctx.dizhiData['酉'].heavenPlate)
                    }), toDisplayString(_ctx.dizhiData['酉'].heavenPlate), 3 /* TEXT, CLASS */)
                  ])
                ])
              ])
            ])
          ]),
          createElementVNode("view", utsMapOf({ class: "plate-row" }), [
            createElementVNode("view", utsMapOf({ class: "grid-cell plate-position" }), [
              createElementVNode("view", utsMapOf({ class: "plate-shensha" }), [
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.dizhiData['卯'].shensha, (sha, idx, __index, _cached): any => {
                  return createElementVNode("text", utsMapOf({
                    class: "shensha-item",
                    key: idx
                  }), toDisplayString(sha), 1 /* TEXT */)
                }), 128 /* KEYED_FRAGMENT */)
              ]),
              createElementVNode("view", utsMapOf({ class: "plate-bottom-content" }), [
                createElementVNode("view", utsMapOf({ class: "plate-relations" }), [
                  createElementVNode("text", null, toDisplayString(_ctx.dizhiData['卯'].relations.gan), 1 /* TEXT */),
                  createElementVNode("text", null, toDisplayString(_ctx.dizhiData['卯'].relations.zhi), 1 /* TEXT */),
                  createElementVNode("text", null, toDisplayString(_ctx.dizhiData['卯'].relations.tian), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "plate-main" }), [
                  createElementVNode("text", utsMapOf({ class: "general" }), toDisplayString(_ctx.dizhiData['卯'].general), 1 /* TEXT */),
                  createElementVNode("view", utsMapOf({ class: "branches" }), [
                    createElementVNode("text", utsMapOf({ class: "earth-plate dizhi-mao" }), "卯"),
                    createElementVNode("text", utsMapOf({
                      class: normalizeClass('heaven-plate dizhi-' + _ctx.dizhiData['卯'].heavenPlate)
                    }), toDisplayString(_ctx.dizhiData['卯'].heavenPlate), 3 /* TEXT, CLASS */)
                  ])
                ])
              ])
            ]),
            createElementVNode("view", utsMapOf({ class: "center-content-cell" }), [
              createElementVNode("view", utsMapOf({ class: "center-si-ke" }), [
                createElementVNode("view", utsMapOf({ class: "si-ke-row" }), [
                  createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.siKe.generals, (gen, idx, __index, _cached): any => {
                    return createElementVNode("text", utsMapOf({
                      class: "si-ke-item si-ke-general",
                      key: 'gen' + idx
                    }), toDisplayString(gen), 1 /* TEXT */)
                  }), 128 /* KEYED_FRAGMENT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "si-ke-row" }), [
                  createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.siKe.heavenPlate, (hp, idx, __index, _cached): any => {
                    return createElementVNode("text", utsMapOf({
                      class: normalizeClass('si-ke-item si-ke-heaven dizhi-' + hp),
                      key: 'hp' + idx
                    }), toDisplayString(hp), 3 /* TEXT, CLASS */)
                  }), 128 /* KEYED_FRAGMENT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "si-ke-row" }), [
                  createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.siKe.earthPlate, (ep, idx, __index, _cached): any => {
                    return createElementVNode("text", utsMapOf({
                      class: normalizeClass('si-ke-item si-ke-earth ' + _ctx.getEarthPlateClass(ep)),
                      key: 'ep' + idx
                    }), toDisplayString(ep), 3 /* TEXT, CLASS */)
                  }), 128 /* KEYED_FRAGMENT */)
                ])
              ])
            ]),
            createElementVNode("view", utsMapOf({ class: "grid-cell plate-position" }), [
              createElementVNode("view", utsMapOf({ class: "plate-shensha" }), [
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.dizhiData['戌'].shensha, (sha, idx, __index, _cached): any => {
                  return createElementVNode("text", utsMapOf({
                    class: "shensha-item",
                    key: idx
                  }), toDisplayString(sha), 1 /* TEXT */)
                }), 128 /* KEYED_FRAGMENT */)
              ]),
              createElementVNode("view", utsMapOf({ class: "plate-bottom-content" }), [
                createElementVNode("view", utsMapOf({ class: "plate-relations" }), [
                  createElementVNode("text", null, toDisplayString(_ctx.dizhiData['戌'].relations.gan), 1 /* TEXT */),
                  createElementVNode("text", null, toDisplayString(_ctx.dizhiData['戌'].relations.zhi), 1 /* TEXT */),
                  createElementVNode("text", null, toDisplayString(_ctx.dizhiData['戌'].relations.tian), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "plate-main" }), [
                  createElementVNode("text", utsMapOf({ class: "general" }), toDisplayString(_ctx.dizhiData['戌'].general), 1 /* TEXT */),
                  createElementVNode("view", utsMapOf({ class: "branches" }), [
                    createElementVNode("text", utsMapOf({ class: "earth-plate dizhi-xu" }), "戌"),
                    createElementVNode("text", utsMapOf({
                      class: normalizeClass('heaven-plate dizhi-' + _ctx.dizhiData['戌'].heavenPlate)
                    }), toDisplayString(_ctx.dizhiData['戌'].heavenPlate), 3 /* TEXT, CLASS */)
                  ])
                ])
              ])
            ])
          ]),
          createElementVNode("view", utsMapOf({ class: "plate-row" }), [
            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.layout.row4, (dizhi, index, __index, _cached): any => {
              return createElementVNode("view", utsMapOf({
                class: "grid-cell plate-position",
                key: dizhi
              }), [
                createElementVNode("view", utsMapOf({ class: "plate-shensha" }), [
                  createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.dizhiData[dizhi].shensha, (sha, idx, __index, _cached): any => {
                    return createElementVNode("text", utsMapOf({
                      class: "shensha-item",
                      key: idx
                    }), toDisplayString(sha), 1 /* TEXT */)
                  }), 128 /* KEYED_FRAGMENT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "plate-bottom-content" }), [
                  createElementVNode("view", utsMapOf({ class: "plate-relations" }), [
                    createElementVNode("text", null, toDisplayString(_ctx.dizhiData[dizhi].relations.gan), 1 /* TEXT */),
                    createElementVNode("text", null, toDisplayString(_ctx.dizhiData[dizhi].relations.zhi), 1 /* TEXT */),
                    createElementVNode("text", null, toDisplayString(_ctx.dizhiData[dizhi].relations.tian), 1 /* TEXT */)
                  ]),
                  createElementVNode("view", utsMapOf({ class: "plate-main" }), [
                    createElementVNode("text", utsMapOf({ class: "general" }), toDisplayString(_ctx.dizhiData[dizhi].general), 1 /* TEXT */),
                    createElementVNode("view", utsMapOf({ class: "branches" }), [
                      createElementVNode("text", utsMapOf({
                        class: normalizeClass('earth-plate dizhi-' + dizhi)
                      }), toDisplayString(dizhi), 3 /* TEXT, CLASS */),
                      createElementVNode("text", utsMapOf({
                        class: normalizeClass('heaven-plate dizhi-' + _ctx.dizhiData[dizhi].heavenPlate)
                      }), toDisplayString(_ctx.dizhiData[dizhi].heavenPlate), 3 /* TEXT, CLASS */)
                    ])
                  ])
                ])
              ])
            }), 128 /* KEYED_FRAGMENT */)
          ])
        ])
      ]),
      createElementVNode("view", utsMapOf({ class: "chart-subject" }), [
        createElementVNode("text", utsMapOf({ class: "subject-strong" }), "占事: "),
        createElementVNode("text", utsMapOf({ class: "subject-text" }), toDisplayString(_ctx.subject), 1 /* TEXT */)
      ]),
      createElementVNode("view", utsMapOf({ class: "footer-tabs" }), [
        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.footerTabs, (tab, index, __index, _cached): any => {
          return createElementVNode("text", utsMapOf({
            class: "tab-span",
            key: index
          }), toDisplayString(tab), 1 /* TEXT */)
        }), 128 /* KEYED_FRAGMENT */)
      ])
    ])
  ])
}
const GenPagesDalurenDalurenStyles = [utsMapOf([["page-body", padStyleMapOf(utsMapOf([["backgroundColor", "#f8f9fa"], ["height", "100%"]]))], ["chart-container", padStyleMapOf(utsMapOf([["maxWidth", 600], ["backgroundColor", "#ffffff"], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#dee2e6"], ["borderRightColor", "#dee2e6"], ["borderBottomColor", "#dee2e6"], ["borderLeftColor", "#dee2e6"], ["borderTopLeftRadius", 8], ["borderTopRightRadius", 8], ["borderBottomRightRadius", 8], ["borderBottomLeftRadius", 8], ["boxShadow", "0 2px 4px rgba(0, 0, 0, 0.075)"], ["marginTop", 8], ["marginRight", "auto"], ["marginBottom", 8], ["marginLeft", "auto"], ["paddingTop", 0], ["paddingRight", 0], ["paddingBottom", 0], ["paddingLeft", 0], ["overflow", "hidden"]]))], ["info-section", padStyleMapOf(utsMapOf([["paddingTop", 16], ["paddingRight", 16], ["paddingBottom", 16], ["paddingLeft", 16], ["borderBottomWidth", 1], ["borderBottomStyle", "solid"], ["borderBottomColor", "#dee2e6"], ["textAlign", "center"], ["fontSize", 14.4]]))], ["info-date", padStyleMapOf(utsMapOf([["fontSize", 14.4], ["color", "#212529"], ["marginBottom", 4]]))], ["pillars", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["justifyContent", "center"], ["alignItems", "center"], ["marginTop", 4], ["marginRight", 0], ["marginBottom", 4], ["marginLeft", 0]]))], ["pillar-span", padStyleMapOf(utsMapOf([["fontSize", 16], ["marginTop", 0], ["marginRight", 12], ["marginBottom", 0], ["marginLeft", 12], ["flexDirection", "row"], ["alignItems", "center"]]))], ["tiangan-jia", utsMapOf([[".pillar-span ", utsMapOf([["color", "#28a745"]])]])], ["tiangan-yi", utsMapOf([[".pillar-span ", utsMapOf([["color", "#28a745"]])]])], ["tiangan-bing", utsMapOf([[".pillar-span ", utsMapOf([["color", "#dc3545"]])]])], ["tiangan-ding", utsMapOf([[".pillar-span ", utsMapOf([["color", "#dc3545"]])]])], ["tiangan-wu", utsMapOf([[".pillar-span ", utsMapOf([["color", "#8b7355"]])]])], ["tiangan-ji", utsMapOf([[".pillar-span ", utsMapOf([["color", "#8b7355"]])]])], ["tiangan-geng", utsMapOf([[".pillar-span ", utsMapOf([["color", "#ffd700"]])]])], ["tiangan-xin", utsMapOf([[".pillar-span ", utsMapOf([["color", "#ffd700"]])]])], ["tiangan-ren", utsMapOf([[".pillar-span ", utsMapOf([["color", "#0d6efd"]])]])], ["tiangan-gui", utsMapOf([[".pillar-span ", utsMapOf([["color", "#0d6efd"]])]])], ["dizhi-zi", utsMapOf([[".pillar-span ", utsMapOf([["color", "#0d6efd"]])]])], ["dizhi-hai", utsMapOf([[".pillar-span ", utsMapOf([["color", "#0d6efd"]])]])], ["dizhi-yin", utsMapOf([[".pillar-span ", utsMapOf([["color", "#28a745"]])]])], ["dizhi-mao", utsMapOf([[".pillar-span ", utsMapOf([["color", "#28a745"]])]])], ["dizhi-si", utsMapOf([[".pillar-span ", utsMapOf([["color", "#dc3545"]])]])], ["dizhi-wu", utsMapOf([[".pillar-span ", utsMapOf([["color", "#dc3545"]])]])], ["dizhi-shen", utsMapOf([[".pillar-span ", utsMapOf([["color", "#ffd700"]])]])], ["dizhi-you", utsMapOf([[".pillar-span ", utsMapOf([["color", "#ffd700"]])]])], ["dizhi-chou", utsMapOf([[".pillar-span ", utsMapOf([["color", "#8b7355"]])]])], ["dizhi-chen", utsMapOf([[".pillar-span ", utsMapOf([["color", "#8b7355"]])]])], ["dizhi-wei", utsMapOf([[".pillar-span ", utsMapOf([["color", "#8b7355"]])]])], ["dizhi-xu", utsMapOf([[".pillar-span ", utsMapOf([["color", "#8b7355"]])]])], ["details", padStyleMapOf(utsMapOf([["fontSize", 12.8], ["color", "#6c757d"], ["marginBottom", 4]]))], ["details-muted", padStyleMapOf(utsMapOf([["color", "#6c757d"]]))], ["chart-type", padStyleMapOf(utsMapOf([["fontSize", 14.4], ["color", "#dc3545"], ["marginTop", 4], ["marginBottom", 0]]))], ["plate-outer-padding", padStyleMapOf(utsMapOf([["paddingTop", 12], ["paddingRight", 12], ["paddingBottom", 12], ["paddingLeft", 12]]))], ["plate-grid-area", padStyleMapOf(utsMapOf([["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#ced4da"], ["borderRightColor", "#ced4da"], ["borderBottomColor", "#ced4da"], ["borderLeftColor", "#ced4da"], ["borderTopLeftRadius", 4], ["borderTopRightRadius", 4], ["borderBottomRightRadius", 4], ["borderBottomLeftRadius", 4], ["paddingTop", 12], ["paddingRight", 12], ["paddingBottom", 12], ["paddingLeft", 12]]))], ["plate-row", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["marginBottom", 4], ["marginBottom:last-child", 0]]))], ["grid-cell", padStyleMapOf(utsMapOf([["width", "25%"], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#f1f3f5"], ["borderRightColor", "#f1f3f5"], ["borderBottomColor", "#f1f3f5"], ["borderLeftColor", "#f1f3f5"], ["paddingTop", 4], ["paddingRight", 4], ["paddingBottom", 4], ["paddingLeft", 4], ["flexDirection", "column"], ["overflow", "hidden"], ["minHeight", 95]]))], ["plate-position", padStyleMapOf(utsMapOf([["fontSize", 12], ["lineHeight", 1.2]]))], ["plate-shensha", padStyleMapOf(utsMapOf([["color", "#6c757d"], ["fontSize", 10.4], ["textAlign", "left"], ["lineHeight", 1.15], ["flexDirection", "row"], ["alignItems", "flex-start"], ["overflow", "hidden"], ["flexShrink", 0]]))], ["shensha-item", padStyleMapOf(utsMapOf([["fontSize", 10.4], ["color", "#6c757d"], ["whiteSpace", "nowrap"], ["marginRight", 2], ["lineHeight", 1.1], ["writingMode", "vertical-lr"]]))], ["plate-bottom-content", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["justifyContent", "space-between"], ["alignItems", "flex-end"], ["width", "100%"], ["marginTop", "auto"], ["flexGrow", 1]]))], ["plate-relations", padStyleMapOf(utsMapOf([["color", "#495057"], ["fontSize", 9.5], ["textAlign", "left"], ["lineHeight", 1.15], ["paddingRight", 2], ["flexShrink", 1], ["flexDirection", "column"], ["overflow", "hidden"]]))], ["plate-main", padStyleMapOf(utsMapOf([["textAlign", "right"], ["flexShrink", 0], ["flexDirection", "column"], ["alignItems", "flex-end"]]))], ["general", padStyleMapOf(utsMapOf([["color", "#198754"], ["fontSize", 13.6], ["marginBottom", 1]]))], ["branches", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["fontSize", 14.4]]))], ["earth-plate", utsMapOf([["", utsMapOf([["fontSize", 14.4], ["marginRight", 2]])], [".dizhi-zi", utsMapOf([["color", "#0d6efd"]])], [".dizhi-hai", utsMapOf([["color", "#0d6efd"]])], [".dizhi-yin", utsMapOf([["color", "#28a745"]])], [".dizhi-mao", utsMapOf([["color", "#28a745"]])], [".dizhi-si", utsMapOf([["color", "#dc3545"]])], [".dizhi-wu", utsMapOf([["color", "#dc3545"]])], [".dizhi-shen", utsMapOf([["color", "#ffd700"]])], [".dizhi-you", utsMapOf([["color", "#ffd700"]])], [".dizhi-chou", utsMapOf([["color", "#8b7355"]])], [".dizhi-chen", utsMapOf([["color", "#8b7355"]])], [".dizhi-wei", utsMapOf([["color", "#8b7355"]])], [".dizhi-xu", utsMapOf([["color", "#8b7355"]])]])], ["heaven-plate", utsMapOf([["", utsMapOf([["fontSize", 14.4], ["fontWeight", "400"]])], [".dizhi-zi", utsMapOf([["color", "#0d6efd"]])], [".dizhi-hai", utsMapOf([["color", "#0d6efd"]])], [".dizhi-yin", utsMapOf([["color", "#28a745"]])], [".dizhi-mao", utsMapOf([["color", "#28a745"]])], [".dizhi-si", utsMapOf([["color", "#dc3545"]])], [".dizhi-wu", utsMapOf([["color", "#dc3545"]])], [".dizhi-shen", utsMapOf([["color", "#ffd700"]])], [".dizhi-you", utsMapOf([["color", "#ffd700"]])], [".dizhi-chou", utsMapOf([["color", "#8b7355"]])], [".dizhi-chen", utsMapOf([["color", "#8b7355"]])], [".dizhi-wei", utsMapOf([["color", "#8b7355"]])], [".dizhi-xu", utsMapOf([["color", "#8b7355"]])]])], ["center-content-cell", padStyleMapOf(utsMapOf([["width", "50%"], ["paddingTop", 8], ["paddingRight", 8], ["paddingBottom", 8], ["paddingLeft", 8], ["fontSize", 12], ["lineHeight", 1.3], ["textAlign", "center"], ["flexDirection", "column"], ["justifyContent", "flex-end"], ["alignItems", "center"], ["minHeight", 95]]))], ["center-san-chuan", padStyleMapOf(utsMapOf([["width", "100%"], ["textAlign", "center"], ["fontSize", 13.6]]))], ["san-chuan-line", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["justifyContent", "center"], ["lineHeight", 1.35]]))], ["relation", padStyleMapOf(utsMapOf([["marginRight", 5]]))], ["dun-gan", utsMapOf([["", utsMapOf([["marginTop", 0], ["marginRight", 3], ["marginBottom", 0], ["marginLeft", 3]])], [".tiangan-jia", utsMapOf([["color", "#28a745"]])], [".tiangan-yi", utsMapOf([["color", "#28a745"]])], [".tiangan-bing", utsMapOf([["color", "#dc3545"]])], [".tiangan-ding", utsMapOf([["color", "#dc3545"]])], [".tiangan-wu", utsMapOf([["color", "#8b7355"]])], [".tiangan-ji", utsMapOf([["color", "#8b7355"]])], [".tiangan-geng", utsMapOf([["color", "#ffd700"]])], [".tiangan-xin", utsMapOf([["color", "#ffd700"]])], [".tiangan-ren", utsMapOf([["color", "#0d6efd"]])], [".tiangan-gui", utsMapOf([["color", "#0d6efd"]])]])], ["branch", utsMapOf([["", utsMapOf([["marginTop", 0], ["marginRight", 3], ["marginBottom", 0], ["marginLeft", 3], ["fontSize", 15.2]])], [".dizhi-zi", utsMapOf([["color", "#0d6efd"]])], [".dizhi-hai", utsMapOf([["color", "#0d6efd"]])], [".dizhi-yin", utsMapOf([["color", "#28a745"]])], [".dizhi-mao", utsMapOf([["color", "#28a745"]])], [".dizhi-si", utsMapOf([["color", "#dc3545"]])], [".dizhi-wu", utsMapOf([["color", "#dc3545"]])], [".dizhi-shen", utsMapOf([["color", "#ffd700"]])], [".dizhi-you", utsMapOf([["color", "#ffd700"]])], [".dizhi-chou", utsMapOf([["color", "#8b7355"]])], [".dizhi-chen", utsMapOf([["color", "#8b7355"]])], [".dizhi-wei", utsMapOf([["color", "#8b7355"]])], [".dizhi-xu", utsMapOf([["color", "#8b7355"]])]])], ["general-name", padStyleMapOf(utsMapOf([["color", "#198754"], ["marginLeft", 5]]))], ["center-si-ke", padStyleMapOf(utsMapOf([["width", "100%"], ["fontSize", 14.4], ["lineHeight", 1.35], ["textAlign", "center"], ["marginTop", 8]]))], ["si-ke-row", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["justifyContent", "space-around"], ["marginBottom", 0]]))], ["si-ke-item", padStyleMapOf(utsMapOf([["width", "25%"], ["textAlign", "center"], ["paddingTop", 0], ["paddingRight", 1], ["paddingBottom", 0], ["paddingLeft", 1]]))], ["si-ke-general", padStyleMapOf(utsMapOf([["color", "#198754"]]))], ["si-ke-heaven", utsMapOf([[".dizhi-zi", utsMapOf([["color", "#0d6efd"]])], [".dizhi-hai", utsMapOf([["color", "#0d6efd"]])], [".dizhi-yin", utsMapOf([["color", "#28a745"]])], [".dizhi-mao", utsMapOf([["color", "#28a745"]])], [".dizhi-si", utsMapOf([["color", "#dc3545"]])], [".dizhi-wu", utsMapOf([["color", "#dc3545"]])], [".dizhi-shen", utsMapOf([["color", "#ffd700"]])], [".dizhi-you", utsMapOf([["color", "#ffd700"]])], [".dizhi-chou", utsMapOf([["color", "#8b7355"]])], [".dizhi-chen", utsMapOf([["color", "#8b7355"]])], [".dizhi-wei", utsMapOf([["color", "#8b7355"]])], [".dizhi-xu", utsMapOf([["color", "#8b7355"]])]])], ["si-ke-earth", utsMapOf([[".tiangan-jia", utsMapOf([["color", "#28a745"]])], [".tiangan-yi", utsMapOf([["color", "#28a745"]])], [".tiangan-bing", utsMapOf([["color", "#dc3545"]])], [".tiangan-ding", utsMapOf([["color", "#dc3545"]])], [".tiangan-wu", utsMapOf([["color", "#8b7355"]])], [".tiangan-ji", utsMapOf([["color", "#8b7355"]])], [".tiangan-geng", utsMapOf([["color", "#ffd700"]])], [".tiangan-xin", utsMapOf([["color", "#ffd700"]])], [".tiangan-ren", utsMapOf([["color", "#0d6efd"]])], [".tiangan-gui", utsMapOf([["color", "#0d6efd"]])], [".dizhi-zi", utsMapOf([["color", "#0d6efd"]])], [".dizhi-hai", utsMapOf([["color", "#0d6efd"]])], [".dizhi-yin", utsMapOf([["color", "#28a745"]])], [".dizhi-mao", utsMapOf([["color", "#28a745"]])], [".dizhi-si", utsMapOf([["color", "#dc3545"]])], [".dizhi-wu", utsMapOf([["color", "#dc3545"]])], [".dizhi-shen", utsMapOf([["color", "#ffd700"]])], [".dizhi-you", utsMapOf([["color", "#ffd700"]])], [".dizhi-chou", utsMapOf([["color", "#8b7355"]])], [".dizhi-chen", utsMapOf([["color", "#8b7355"]])], [".dizhi-wei", utsMapOf([["color", "#8b7355"]])], [".dizhi-xu", utsMapOf([["color", "#8b7355"]])]])], ["chart-subject", padStyleMapOf(utsMapOf([["paddingTop", 16], ["paddingRight", 16], ["paddingBottom", 16], ["paddingLeft", 16], ["borderTopWidth", 1], ["borderTopStyle", "solid"], ["borderTopColor", "#dee2e6"], ["fontSize", 14.4], ["flexDirection", "row"]]))], ["subject-text", padStyleMapOf(utsMapOf([["color", "#212529"]]))], ["footer-tabs", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["justifyContent", "space-around"], ["paddingTop", 12], ["paddingRight", 12], ["paddingBottom", 12], ["paddingLeft", 12], ["borderTopWidth", 1], ["borderTopStyle", "solid"], ["borderTopColor", "#dee2e6"], ["backgroundColor", "#f8f9fa"], ["fontSize", 12.8], ["color", "#6c757d"]]))], ["tab-span", padStyleMapOf(utsMapOf([["paddingTop", 3], ["paddingRight", 6], ["paddingBottom", 3], ["paddingLeft", 6], ["borderTopLeftRadius", 4], ["borderTopRightRadius", 4], ["borderBottomRightRadius", 4], ["borderBottomLeftRadius", 4]]))]])]
