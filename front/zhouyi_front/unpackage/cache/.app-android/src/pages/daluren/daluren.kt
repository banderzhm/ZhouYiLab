@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER", "NAME_SHADOWING", "UNNECESSARY_NOT_NULL_ASSERTION")
package uni.UNICA45710
import io.dcloud.uniapp.*
import io.dcloud.uniapp.extapi.*
import io.dcloud.uniapp.framework.*
import io.dcloud.uniapp.runtime.*
import io.dcloud.uniapp.vue.*
import io.dcloud.uniapp.vue.shared.*
import io.dcloud.unicloud.*
import io.dcloud.uts.*
import io.dcloud.uts.Map
import io.dcloud.uts.Set
import io.dcloud.uts.UTSAndroid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
open class GenPagesDalurenDaluren : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {
        onLoad(fun(_: OnLoadOptions) {}, __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("scroll-view", utsMapOf("class" to "page-body"), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "chart-container"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "info-section"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "info-date"), toDisplayString(_ctx.dateInfo), 1),
                    createElementVNode("view", utsMapOf("class" to "pillars"), utsArrayOf(
                        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.pillars, fun(pillar, index, __index, _cached): Any {
                            return createElementVNode("view", utsMapOf("class" to "pillar-span", "key" to index), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to normalizeClass(_ctx.getTianganClass(pillar.split(" ")[0]))), toDisplayString(pillar.split(" ")[0]), 3),
                                createElementVNode("text"),
                                createElementVNode("text", utsMapOf("class" to normalizeClass("dizhi-" + pillar.split(" ")[1])), toDisplayString(pillar.split(" ")[1]), 3)
                            ))
                        }
                        ), 128)
                    )),
                    createElementVNode("text", utsMapOf("class" to "details"), toDisplayString(_ctx.details), 1),
                    createElementVNode("text", utsMapOf("class" to "details details-muted"), toDisplayString(_ctx.detailsExtra), 1),
                    createElementVNode("text", utsMapOf("class" to "chart-type"), toDisplayString(_ctx.chartType), 1)
                )),
                createElementVNode("view", utsMapOf("class" to "plate-outer-padding"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "plate-grid-area"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "plate-row"), utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.layout.row1, fun(dizhi, index, __index, _cached): Any {
                                return createElementVNode("view", utsMapOf("class" to "grid-cell plate-position", "key" to dizhi), utsArrayOf(
                                    createElementVNode("view", utsMapOf("class" to "plate-shensha"), utsArrayOf(
                                        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.dizhiData[dizhi].shensha, fun(sha, idx, __index, _cached): Any {
                                            return createElementVNode("text", utsMapOf("class" to "shensha-item", "key" to idx), toDisplayString(sha), 1)
                                        }
                                        ), 128)
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "plate-bottom-content"), utsArrayOf(
                                        createElementVNode("view", utsMapOf("class" to "plate-relations"), utsArrayOf(
                                            createElementVNode("text", null, toDisplayString(_ctx.dizhiData[dizhi].relations.gan), 1),
                                            createElementVNode("text", null, toDisplayString(_ctx.dizhiData[dizhi].relations.zhi), 1),
                                            createElementVNode("text", null, toDisplayString(_ctx.dizhiData[dizhi].relations.tian), 1)
                                        )),
                                        createElementVNode("view", utsMapOf("class" to "plate-main"), utsArrayOf(
                                            createElementVNode("text", utsMapOf("class" to "general"), toDisplayString(_ctx.dizhiData[dizhi].general), 1),
                                            createElementVNode("view", utsMapOf("class" to "branches"), utsArrayOf(
                                                createElementVNode("text", utsMapOf("class" to normalizeClass("earth-plate dizhi-" + dizhi)), toDisplayString(dizhi), 3),
                                                createElementVNode("text", utsMapOf("class" to normalizeClass("heaven-plate dizhi-" + _ctx.dizhiData[dizhi].heavenPlate)), toDisplayString(_ctx.dizhiData[dizhi].heavenPlate), 3)
                                            ))
                                        ))
                                    ))
                                ))
                            }
                            ), 128)
                        )),
                        createElementVNode("view", utsMapOf("class" to "plate-row"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "grid-cell plate-position"), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "plate-shensha"), utsArrayOf(
                                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.dizhiData["辰"].shensha, fun(sha, idx, __index, _cached): Any {
                                        return createElementVNode("text", utsMapOf("class" to "shensha-item", "key" to idx), toDisplayString(sha), 1)
                                    }
                                    ), 128)
                                )),
                                createElementVNode("view", utsMapOf("class" to "plate-bottom-content"), utsArrayOf(
                                    createElementVNode("view", utsMapOf("class" to "plate-relations"), utsArrayOf(
                                        createElementVNode("text", null, toDisplayString(_ctx.dizhiData["辰"].relations.gan), 1),
                                        createElementVNode("text", null, toDisplayString(_ctx.dizhiData["辰"].relations.zhi), 1),
                                        createElementVNode("text", null, toDisplayString(_ctx.dizhiData["辰"].relations.tian), 1)
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "plate-main"), utsArrayOf(
                                        createElementVNode("text", utsMapOf("class" to "general"), toDisplayString(_ctx.dizhiData["辰"].general), 1),
                                        createElementVNode("view", utsMapOf("class" to "branches"), utsArrayOf(
                                            createElementVNode("text", utsMapOf("class" to "earth-plate dizhi-chen"), "辰"),
                                            createElementVNode("text", utsMapOf("class" to normalizeClass("heaven-plate dizhi-" + _ctx.dizhiData["辰"].heavenPlate)), toDisplayString(_ctx.dizhiData["辰"].heavenPlate), 3)
                                        ))
                                    ))
                                ))
                            )),
                            createElementVNode("view", utsMapOf("class" to "center-content-cell"), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "center-san-chuan"), utsArrayOf(
                                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.sanChuan, fun(sc, index, __index, _cached): Any {
                                        return createElementVNode("view", utsMapOf("class" to "san-chuan-line", "key" to index), utsArrayOf(
                                            createElementVNode("text", utsMapOf("class" to "relation"), toDisplayString(sc.relation), 1),
                                            createElementVNode("text", utsMapOf("class" to normalizeClass("dun-gan " + _ctx.getTianganClass(sc.dunGan))), toDisplayString(sc.dunGan), 3),
                                            createElementVNode("text", utsMapOf("class" to normalizeClass("branch dizhi-" + sc.branch)), toDisplayString(sc.branch), 3),
                                            createElementVNode("text", utsMapOf("class" to "general-name"), toDisplayString(sc.general), 1)
                                        ))
                                    }
                                    ), 128)
                                ))
                            )),
                            createElementVNode("view", utsMapOf("class" to "grid-cell plate-position"), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "plate-shensha"), utsArrayOf(
                                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.dizhiData["酉"].shensha, fun(sha, idx, __index, _cached): Any {
                                        return createElementVNode("text", utsMapOf("class" to "shensha-item", "key" to idx), toDisplayString(sha), 1)
                                    }
                                    ), 128)
                                )),
                                createElementVNode("view", utsMapOf("class" to "plate-bottom-content"), utsArrayOf(
                                    createElementVNode("view", utsMapOf("class" to "plate-relations"), utsArrayOf(
                                        createElementVNode("text", null, toDisplayString(_ctx.dizhiData["酉"].relations.gan), 1),
                                        createElementVNode("text", null, toDisplayString(_ctx.dizhiData["酉"].relations.zhi), 1),
                                        createElementVNode("text", null, toDisplayString(_ctx.dizhiData["酉"].relations.tian), 1)
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "plate-main"), utsArrayOf(
                                        createElementVNode("text", utsMapOf("class" to "general"), toDisplayString(_ctx.dizhiData["酉"].general), 1),
                                        createElementVNode("view", utsMapOf("class" to "branches"), utsArrayOf(
                                            createElementVNode("text", utsMapOf("class" to "earth-plate dizhi-you"), "酉"),
                                            createElementVNode("text", utsMapOf("class" to normalizeClass("heaven-plate dizhi-" + _ctx.dizhiData["酉"].heavenPlate)), toDisplayString(_ctx.dizhiData["酉"].heavenPlate), 3)
                                        ))
                                    ))
                                ))
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "plate-row"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "grid-cell plate-position"), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "plate-shensha"), utsArrayOf(
                                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.dizhiData["卯"].shensha, fun(sha, idx, __index, _cached): Any {
                                        return createElementVNode("text", utsMapOf("class" to "shensha-item", "key" to idx), toDisplayString(sha), 1)
                                    }
                                    ), 128)
                                )),
                                createElementVNode("view", utsMapOf("class" to "plate-bottom-content"), utsArrayOf(
                                    createElementVNode("view", utsMapOf("class" to "plate-relations"), utsArrayOf(
                                        createElementVNode("text", null, toDisplayString(_ctx.dizhiData["卯"].relations.gan), 1),
                                        createElementVNode("text", null, toDisplayString(_ctx.dizhiData["卯"].relations.zhi), 1),
                                        createElementVNode("text", null, toDisplayString(_ctx.dizhiData["卯"].relations.tian), 1)
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "plate-main"), utsArrayOf(
                                        createElementVNode("text", utsMapOf("class" to "general"), toDisplayString(_ctx.dizhiData["卯"].general), 1),
                                        createElementVNode("view", utsMapOf("class" to "branches"), utsArrayOf(
                                            createElementVNode("text", utsMapOf("class" to "earth-plate dizhi-mao"), "卯"),
                                            createElementVNode("text", utsMapOf("class" to normalizeClass("heaven-plate dizhi-" + _ctx.dizhiData["卯"].heavenPlate)), toDisplayString(_ctx.dizhiData["卯"].heavenPlate), 3)
                                        ))
                                    ))
                                ))
                            )),
                            createElementVNode("view", utsMapOf("class" to "center-content-cell"), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "center-si-ke"), utsArrayOf(
                                    createElementVNode("view", utsMapOf("class" to "si-ke-row"), utsArrayOf(
                                        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.siKe.generals, fun(gen, idx, __index, _cached): Any {
                                            return createElementVNode("text", utsMapOf("class" to "si-ke-item si-ke-general", "key" to ("gen" + idx)), toDisplayString(gen), 1)
                                        }
                                        ), 128)
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "si-ke-row"), utsArrayOf(
                                        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.siKe.heavenPlate, fun(hp, idx, __index, _cached): Any {
                                            return createElementVNode("text", utsMapOf("class" to normalizeClass("si-ke-item si-ke-heaven dizhi-" + hp), "key" to ("hp" + idx)), toDisplayString(hp), 3)
                                        }
                                        ), 128)
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "si-ke-row"), utsArrayOf(
                                        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.siKe.earthPlate, fun(ep, idx, __index, _cached): Any {
                                            return createElementVNode("text", utsMapOf("class" to normalizeClass("si-ke-item si-ke-earth " + _ctx.getEarthPlateClass(ep)), "key" to ("ep" + idx)), toDisplayString(ep), 3)
                                        }
                                        ), 128)
                                    ))
                                ))
                            )),
                            createElementVNode("view", utsMapOf("class" to "grid-cell plate-position"), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "plate-shensha"), utsArrayOf(
                                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.dizhiData["戌"].shensha, fun(sha, idx, __index, _cached): Any {
                                        return createElementVNode("text", utsMapOf("class" to "shensha-item", "key" to idx), toDisplayString(sha), 1)
                                    }
                                    ), 128)
                                )),
                                createElementVNode("view", utsMapOf("class" to "plate-bottom-content"), utsArrayOf(
                                    createElementVNode("view", utsMapOf("class" to "plate-relations"), utsArrayOf(
                                        createElementVNode("text", null, toDisplayString(_ctx.dizhiData["戌"].relations.gan), 1),
                                        createElementVNode("text", null, toDisplayString(_ctx.dizhiData["戌"].relations.zhi), 1),
                                        createElementVNode("text", null, toDisplayString(_ctx.dizhiData["戌"].relations.tian), 1)
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "plate-main"), utsArrayOf(
                                        createElementVNode("text", utsMapOf("class" to "general"), toDisplayString(_ctx.dizhiData["戌"].general), 1),
                                        createElementVNode("view", utsMapOf("class" to "branches"), utsArrayOf(
                                            createElementVNode("text", utsMapOf("class" to "earth-plate dizhi-xu"), "戌"),
                                            createElementVNode("text", utsMapOf("class" to normalizeClass("heaven-plate dizhi-" + _ctx.dizhiData["戌"].heavenPlate)), toDisplayString(_ctx.dizhiData["戌"].heavenPlate), 3)
                                        ))
                                    ))
                                ))
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "plate-row"), utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.layout.row4, fun(dizhi, index, __index, _cached): Any {
                                return createElementVNode("view", utsMapOf("class" to "grid-cell plate-position", "key" to dizhi), utsArrayOf(
                                    createElementVNode("view", utsMapOf("class" to "plate-shensha"), utsArrayOf(
                                        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.dizhiData[dizhi].shensha, fun(sha, idx, __index, _cached): Any {
                                            return createElementVNode("text", utsMapOf("class" to "shensha-item", "key" to idx), toDisplayString(sha), 1)
                                        }
                                        ), 128)
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "plate-bottom-content"), utsArrayOf(
                                        createElementVNode("view", utsMapOf("class" to "plate-relations"), utsArrayOf(
                                            createElementVNode("text", null, toDisplayString(_ctx.dizhiData[dizhi].relations.gan), 1),
                                            createElementVNode("text", null, toDisplayString(_ctx.dizhiData[dizhi].relations.zhi), 1),
                                            createElementVNode("text", null, toDisplayString(_ctx.dizhiData[dizhi].relations.tian), 1)
                                        )),
                                        createElementVNode("view", utsMapOf("class" to "plate-main"), utsArrayOf(
                                            createElementVNode("text", utsMapOf("class" to "general"), toDisplayString(_ctx.dizhiData[dizhi].general), 1),
                                            createElementVNode("view", utsMapOf("class" to "branches"), utsArrayOf(
                                                createElementVNode("text", utsMapOf("class" to normalizeClass("earth-plate dizhi-" + dizhi)), toDisplayString(dizhi), 3),
                                                createElementVNode("text", utsMapOf("class" to normalizeClass("heaven-plate dizhi-" + _ctx.dizhiData[dizhi].heavenPlate)), toDisplayString(_ctx.dizhiData[dizhi].heavenPlate), 3)
                                            ))
                                        ))
                                    ))
                                ))
                            }
                            ), 128)
                        ))
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "chart-subject"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "subject-strong"), "占事: "),
                    createElementVNode("text", utsMapOf("class" to "subject-text"), toDisplayString(_ctx.subject), 1)
                )),
                createElementVNode("view", utsMapOf("class" to "footer-tabs"), utsArrayOf(
                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.footerTabs, fun(tab, index, __index, _cached): Any {
                        return createElementVNode("text", utsMapOf("class" to "tab-span", "key" to index), toDisplayString(tab), 1)
                    }
                    ), 128)
                ))
            ))
        ))
    }
    open var dateInfo: String by `$data`
    open var pillars: UTSArray<String> by `$data`
    open var details: String by `$data`
    open var detailsExtra: String by `$data`
    open var chartType: String by `$data`
    open var subject: String by `$data`
    open var sanChuan: UTSArray<UTSJSONObject> by `$data`
    open var siKe: UTSJSONObject by `$data`
    open var layout: UTSJSONObject by `$data`
    open var dizhiData: UTSJSONObject by `$data`
    open var footerTabs: UTSArray<String> by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("dateInfo" to "2025-04-16 (三月十九) 15:56 (真太阳)", "pillars" to utsArrayOf(
            "乙 巳",
            "庚 辰",
            "乙 卯",
            "甲 申"
        ), "details" to "乙卯 日 甲申 时 (正时) 戌 将", "detailsExtra" to "甲寅 旬 子丑 空 | 驿马: 巳 | 丁马: 巳 | 天马: 戌", "chartType" to "重审, 间传, 涉三渊", "subject" to "问感情发展", "sanChuan" to utsArrayOf(
            object : UTSJSONObject() {
                var relation = "官"
                var dunGan = "庚"
                var branch = "申"
                var general = "勾"
            },
            object : UTSJSONObject() {
                var relation = "财"
                var dunGan = "壬"
                var branch = "戌"
                var general = "雀"
            },
            object : UTSJSONObject() {
                var relation = "父"
                var dunGan = "己"
                var branch = "子"
                var general = "贵"
            }
        ), "siKe" to object : UTSJSONObject() {
            var generals = utsArrayOf(
                "蛇",
                "青",
                "勾",
                "常"
            )
            var heavenPlate = utsArrayOf(
                "戌",
                "午",
                "未",
                "卯"
            )
            var earthPlate = utsArrayOf(
                "午",
                "寅",
                "卯",
                "壬"
            )
        }, "layout" to object : UTSJSONObject() {
            var row1 = utsArrayOf(
                "巳",
                "午",
                "未",
                "申"
            )
            var row2 = utsArrayOf(
                "辰",
                "酉"
            )
            var row3 = utsArrayOf(
                "卯",
                "戌"
            )
            var row4 = utsArrayOf(
                "寅",
                "丑",
                "子",
                "亥"
            )
        }, "dizhiData" to object : UTSJSONObject() {
            var 子 = object : UTSJSONObject() {
                var shensha = utsArrayOf(
                    "劫煞",
                    "孤辰",
                    "地医"
                )
                var relations = object : UTSJSONObject() {
                    var gan = "干:沐浴"
                    var zhi = "支:沐浴"
                    var tian = "天:冠带"
                }
                var general = "后"
                var heavenPlate = "卯"
            }
            var 丑 = object : UTSJSONObject() {
                var shensha = utsArrayOf(
                    "空亡",
                    "华盖",
                    "月刑"
                )
                var relations = object : UTSJSONObject() {
                    var gan = "干:冠带"
                    var zhi = "支:冠带"
                    var tian = "天:病"
                }
                var general = "贵"
                var heavenPlate = "辰"
            }
            var 寅 = object : UTSJSONObject() {
                var shensha = utsArrayOf(
                    "亡神",
                    "天贼",
                    "血支"
                )
                var relations = object : UTSJSONObject() {
                    var gan = "干:临官"
                    var zhi = "支:临官"
                    var tian = "天:冠带"
                }
                var general = "蛇"
                var heavenPlate = "巳"
            }
            var 卯 = object : UTSJSONObject() {
                var shensha = utsArrayOf(
                    "日禄",
                    "桃花",
                    "将星"
                )
                var relations = object : UTSJSONObject() {
                    var gan = "干:帝旺"
                    var zhi = "支:帝旺"
                    var tian = "天:病"
                }
                var general = "雀"
                var heavenPlate = "午"
            }
            var 辰 = object : UTSJSONObject() {
                var shensha = utsArrayOf(
                    "天喜",
                    "小耗",
                    "月破"
                )
                var relations = object : UTSJSONObject() {
                    var gan = "干:胎"
                    var zhi = "支:胎"
                    var tian = "天:绝"
                }
                var general = "合"
                var heavenPlate = "未"
            }
            var 巳 = object : UTSJSONObject() {
                var shensha = utsArrayOf(
                    "驿马",
                    "火鬼",
                    "医廉"
                )
                var relations = object : UTSJSONObject() {
                    var gan = "干:绝"
                    var zhi = "支:绝"
                    var tian = "天:沐浴"
                }
                var general = "勾"
                var heavenPlate = "申"
            }
            var 午 = object : UTSJSONObject() {
                var shensha = utsArrayOf(
                    "日德",
                    "死符",
                    "死气",
                    "飞廉",
                    "月厌"
                )
                var relations = object : UTSJSONObject() {
                    var gan = "干:病"
                    var zhi = "支:病"
                    var tian = "天:冠带"
                }
                var general = "龙"
                var heavenPlate = "酉"
            }
            var 未 = object : UTSJSONObject() {
                var shensha = utsArrayOf(
                    "月建",
                    "破碎",
                    "丧门",
                    "咸池"
                )
                var relations = object : UTSJSONObject() {
                    var gan = "干:衰"
                    var zhi = "支:衰"
                    var tian = "天:冠带"
                }
                var general = "空"
                var heavenPlate = "戌"
            }
            var 申 = object : UTSJSONObject() {
                var shensha = utsArrayOf(
                    "天马",
                    "天解",
                    "神耗"
                )
                var relations = object : UTSJSONObject() {
                    var gan = "干:死"
                    var zhi = "支:死"
                    var tian = "天:沐浴"
                }
                var general = "虎"
                var heavenPlate = "亥"
            }
            var 酉 = object : UTSJSONObject() {
                var shensha = utsArrayOf(
                    "咸池",
                    "大耗",
                    "岁破"
                )
                var relations = object : UTSJSONObject() {
                    var gan = "干:墓"
                    var zhi = "支:墓"
                    var tian = "天:长生"
                }
                var general = "常"
                var heavenPlate = "子"
            }
            var 戌 = object : UTSJSONObject() {
                var shensha = utsArrayOf(
                    "月德",
                    "合德",
                    "天医"
                )
                var relations = object : UTSJSONObject() {
                    var gan = "干:长生"
                    var zhi = "支:长生"
                    var tian = "天:沐浴"
                }
                var general = "阴"
                var heavenPlate = "寅"
            }
            var 亥 = object : UTSJSONObject() {
                var shensha = utsArrayOf(
                    "官符",
                    "寡宿",
                    "月煞"
                )
                var relations = object : UTSJSONObject() {
                    var gan = "干:养"
                    var zhi = "支:养"
                    var tian = "天:长生"
                }
                var general = "玄"
                var heavenPlate = "丑"
            }
        }, "footerTabs" to utsArrayOf(
            "直指",
            "集应铃",
            "神煞",
            "案例",
            "占法",
            "取象",
            "批注",
            "⚙️"
        ))
    }
    open var getDizhiData = ::gen_getDizhiData_fn
    open fun gen_getDizhiData_fn(dizhi: String) {
        return this.dizhiData[dizhi]
    }
    open var getTianganClass = ::gen_getTianganClass_fn
    open fun gen_getTianganClass_fn(tiangan: String): String {
        val tianganMap: Map<String, String> = Map(utsArrayOf(
            utsArrayOf(
                "甲",
                "tiangan-jia"
            ),
            utsArrayOf(
                "乙",
                "tiangan-yi"
            ),
            utsArrayOf(
                "丙",
                "tiangan-bing"
            ),
            utsArrayOf(
                "丁",
                "tiangan-ding"
            ),
            utsArrayOf(
                "戊",
                "tiangan-wu"
            ),
            utsArrayOf(
                "己",
                "tiangan-ji"
            ),
            utsArrayOf(
                "庚",
                "tiangan-geng"
            ),
            utsArrayOf(
                "辛",
                "tiangan-xin"
            ),
            utsArrayOf(
                "壬",
                "tiangan-ren"
            ),
            utsArrayOf(
                "癸",
                "tiangan-gui"
            )
        ))
        return tianganMap.get(tiangan) || ""
    }
    open var getEarthPlateClass = ::gen_getEarthPlateClass_fn
    open fun gen_getEarthPlateClass_fn(item: String): String {
        val tianganClass = this.getTianganClass(item)
        if (tianganClass) {
            return tianganClass
        }
        return "dizhi-" + item
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ), utsArrayOf(
                GenApp.styles
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("page-body" to padStyleMapOf(utsMapOf("backgroundColor" to "#f8f9fa", "height" to "100%")), "chart-container" to padStyleMapOf(utsMapOf("maxWidth" to 600, "backgroundColor" to "#ffffff", "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#dee2e6", "borderRightColor" to "#dee2e6", "borderBottomColor" to "#dee2e6", "borderLeftColor" to "#dee2e6", "borderTopLeftRadius" to 8, "borderTopRightRadius" to 8, "borderBottomRightRadius" to 8, "borderBottomLeftRadius" to 8, "boxShadow" to "0 2px 4px rgba(0, 0, 0, 0.075)", "marginTop" to 8, "marginRight" to "auto", "marginBottom" to 8, "marginLeft" to "auto", "paddingTop" to 0, "paddingRight" to 0, "paddingBottom" to 0, "paddingLeft" to 0, "overflow" to "hidden")), "info-section" to padStyleMapOf(utsMapOf("paddingTop" to 16, "paddingRight" to 16, "paddingBottom" to 16, "paddingLeft" to 16, "borderBottomWidth" to 1, "borderBottomStyle" to "solid", "borderBottomColor" to "#dee2e6", "textAlign" to "center", "fontSize" to 14.4)), "info-date" to padStyleMapOf(utsMapOf("fontSize" to 14.4, "color" to "#212529", "marginBottom" to 4)), "pillars" to padStyleMapOf(utsMapOf("flexDirection" to "row", "justifyContent" to "center", "alignItems" to "center", "marginTop" to 4, "marginRight" to 0, "marginBottom" to 4, "marginLeft" to 0)), "pillar-span" to padStyleMapOf(utsMapOf("fontSize" to 16, "marginTop" to 0, "marginRight" to 12, "marginBottom" to 0, "marginLeft" to 12, "flexDirection" to "row", "alignItems" to "center")), "tiangan-jia" to utsMapOf(".pillar-span " to utsMapOf("color" to "#28a745")), "tiangan-yi" to utsMapOf(".pillar-span " to utsMapOf("color" to "#28a745")), "tiangan-bing" to utsMapOf(".pillar-span " to utsMapOf("color" to "#dc3545")), "tiangan-ding" to utsMapOf(".pillar-span " to utsMapOf("color" to "#dc3545")), "tiangan-wu" to utsMapOf(".pillar-span " to utsMapOf("color" to "#8b7355")), "tiangan-ji" to utsMapOf(".pillar-span " to utsMapOf("color" to "#8b7355")), "tiangan-geng" to utsMapOf(".pillar-span " to utsMapOf("color" to "#ffd700")), "tiangan-xin" to utsMapOf(".pillar-span " to utsMapOf("color" to "#ffd700")), "tiangan-ren" to utsMapOf(".pillar-span " to utsMapOf("color" to "#0d6efd")), "tiangan-gui" to utsMapOf(".pillar-span " to utsMapOf("color" to "#0d6efd")), "dizhi-zi" to utsMapOf(".pillar-span " to utsMapOf("color" to "#0d6efd")), "dizhi-hai" to utsMapOf(".pillar-span " to utsMapOf("color" to "#0d6efd")), "dizhi-yin" to utsMapOf(".pillar-span " to utsMapOf("color" to "#28a745")), "dizhi-mao" to utsMapOf(".pillar-span " to utsMapOf("color" to "#28a745")), "dizhi-si" to utsMapOf(".pillar-span " to utsMapOf("color" to "#dc3545")), "dizhi-wu" to utsMapOf(".pillar-span " to utsMapOf("color" to "#dc3545")), "dizhi-shen" to utsMapOf(".pillar-span " to utsMapOf("color" to "#ffd700")), "dizhi-you" to utsMapOf(".pillar-span " to utsMapOf("color" to "#ffd700")), "dizhi-chou" to utsMapOf(".pillar-span " to utsMapOf("color" to "#8b7355")), "dizhi-chen" to utsMapOf(".pillar-span " to utsMapOf("color" to "#8b7355")), "dizhi-wei" to utsMapOf(".pillar-span " to utsMapOf("color" to "#8b7355")), "dizhi-xu" to utsMapOf(".pillar-span " to utsMapOf("color" to "#8b7355")), "details" to padStyleMapOf(utsMapOf("fontSize" to 12.8, "color" to "#6c757d", "marginBottom" to 4)), "details-muted" to padStyleMapOf(utsMapOf("color" to "#6c757d")), "chart-type" to padStyleMapOf(utsMapOf("fontSize" to 14.4, "color" to "#dc3545", "marginTop" to 4, "marginBottom" to 0)), "plate-outer-padding" to padStyleMapOf(utsMapOf("paddingTop" to 12, "paddingRight" to 12, "paddingBottom" to 12, "paddingLeft" to 12)), "plate-grid-area" to padStyleMapOf(utsMapOf("borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#ced4da", "borderRightColor" to "#ced4da", "borderBottomColor" to "#ced4da", "borderLeftColor" to "#ced4da", "borderTopLeftRadius" to 4, "borderTopRightRadius" to 4, "borderBottomRightRadius" to 4, "borderBottomLeftRadius" to 4, "paddingTop" to 12, "paddingRight" to 12, "paddingBottom" to 12, "paddingLeft" to 12)), "plate-row" to padStyleMapOf(utsMapOf("flexDirection" to "row", "marginBottom" to 4, "marginBottom:last-child" to 0)), "grid-cell" to padStyleMapOf(utsMapOf("width" to "25%", "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#f1f3f5", "borderRightColor" to "#f1f3f5", "borderBottomColor" to "#f1f3f5", "borderLeftColor" to "#f1f3f5", "paddingTop" to 4, "paddingRight" to 4, "paddingBottom" to 4, "paddingLeft" to 4, "flexDirection" to "column", "overflow" to "hidden", "minHeight" to 95)), "plate-position" to padStyleMapOf(utsMapOf("fontSize" to 12, "lineHeight" to 1.2)), "plate-shensha" to padStyleMapOf(utsMapOf("color" to "#6c757d", "fontSize" to 10.4, "textAlign" to "left", "lineHeight" to 1.15, "flexDirection" to "row", "alignItems" to "flex-start", "overflow" to "hidden", "flexShrink" to 0)), "shensha-item" to padStyleMapOf(utsMapOf("fontSize" to 10.4, "color" to "#6c757d", "whiteSpace" to "nowrap", "marginRight" to 2, "lineHeight" to 1.1, "writingMode" to "vertical-lr")), "plate-bottom-content" to padStyleMapOf(utsMapOf("flexDirection" to "row", "justifyContent" to "space-between", "alignItems" to "flex-end", "width" to "100%", "marginTop" to "auto", "flexGrow" to 1)), "plate-relations" to padStyleMapOf(utsMapOf("color" to "#495057", "fontSize" to 9.5, "textAlign" to "left", "lineHeight" to 1.15, "paddingRight" to 2, "flexShrink" to 1, "flexDirection" to "column", "overflow" to "hidden")), "plate-main" to padStyleMapOf(utsMapOf("textAlign" to "right", "flexShrink" to 0, "flexDirection" to "column", "alignItems" to "flex-end")), "general" to padStyleMapOf(utsMapOf("color" to "#198754", "fontSize" to 13.6, "marginBottom" to 1)), "branches" to padStyleMapOf(utsMapOf("flexDirection" to "row", "fontSize" to 14.4)), "earth-plate" to utsMapOf("" to utsMapOf("fontSize" to 14.4, "marginRight" to 2), ".dizhi-zi" to utsMapOf("color" to "#0d6efd"), ".dizhi-hai" to utsMapOf("color" to "#0d6efd"), ".dizhi-yin" to utsMapOf("color" to "#28a745"), ".dizhi-mao" to utsMapOf("color" to "#28a745"), ".dizhi-si" to utsMapOf("color" to "#dc3545"), ".dizhi-wu" to utsMapOf("color" to "#dc3545"), ".dizhi-shen" to utsMapOf("color" to "#ffd700"), ".dizhi-you" to utsMapOf("color" to "#ffd700"), ".dizhi-chou" to utsMapOf("color" to "#8b7355"), ".dizhi-chen" to utsMapOf("color" to "#8b7355"), ".dizhi-wei" to utsMapOf("color" to "#8b7355"), ".dizhi-xu" to utsMapOf("color" to "#8b7355")), "heaven-plate" to utsMapOf("" to utsMapOf("fontSize" to 14.4, "fontWeight" to "400"), ".dizhi-zi" to utsMapOf("color" to "#0d6efd"), ".dizhi-hai" to utsMapOf("color" to "#0d6efd"), ".dizhi-yin" to utsMapOf("color" to "#28a745"), ".dizhi-mao" to utsMapOf("color" to "#28a745"), ".dizhi-si" to utsMapOf("color" to "#dc3545"), ".dizhi-wu" to utsMapOf("color" to "#dc3545"), ".dizhi-shen" to utsMapOf("color" to "#ffd700"), ".dizhi-you" to utsMapOf("color" to "#ffd700"), ".dizhi-chou" to utsMapOf("color" to "#8b7355"), ".dizhi-chen" to utsMapOf("color" to "#8b7355"), ".dizhi-wei" to utsMapOf("color" to "#8b7355"), ".dizhi-xu" to utsMapOf("color" to "#8b7355")), "center-content-cell" to padStyleMapOf(utsMapOf("width" to "50%", "paddingTop" to 8, "paddingRight" to 8, "paddingBottom" to 8, "paddingLeft" to 8, "fontSize" to 12, "lineHeight" to 1.3, "textAlign" to "center", "flexDirection" to "column", "justifyContent" to "flex-end", "alignItems" to "center", "minHeight" to 95)), "center-san-chuan" to padStyleMapOf(utsMapOf("width" to "100%", "textAlign" to "center", "fontSize" to 13.6)), "san-chuan-line" to padStyleMapOf(utsMapOf("flexDirection" to "row", "justifyContent" to "center", "lineHeight" to 1.35)), "relation" to padStyleMapOf(utsMapOf("marginRight" to 5)), "dun-gan" to utsMapOf("" to utsMapOf("marginTop" to 0, "marginRight" to 3, "marginBottom" to 0, "marginLeft" to 3), ".tiangan-jia" to utsMapOf("color" to "#28a745"), ".tiangan-yi" to utsMapOf("color" to "#28a745"), ".tiangan-bing" to utsMapOf("color" to "#dc3545"), ".tiangan-ding" to utsMapOf("color" to "#dc3545"), ".tiangan-wu" to utsMapOf("color" to "#8b7355"), ".tiangan-ji" to utsMapOf("color" to "#8b7355"), ".tiangan-geng" to utsMapOf("color" to "#ffd700"), ".tiangan-xin" to utsMapOf("color" to "#ffd700"), ".tiangan-ren" to utsMapOf("color" to "#0d6efd"), ".tiangan-gui" to utsMapOf("color" to "#0d6efd")), "branch" to utsMapOf("" to utsMapOf("marginTop" to 0, "marginRight" to 3, "marginBottom" to 0, "marginLeft" to 3, "fontSize" to 15.2), ".dizhi-zi" to utsMapOf("color" to "#0d6efd"), ".dizhi-hai" to utsMapOf("color" to "#0d6efd"), ".dizhi-yin" to utsMapOf("color" to "#28a745"), ".dizhi-mao" to utsMapOf("color" to "#28a745"), ".dizhi-si" to utsMapOf("color" to "#dc3545"), ".dizhi-wu" to utsMapOf("color" to "#dc3545"), ".dizhi-shen" to utsMapOf("color" to "#ffd700"), ".dizhi-you" to utsMapOf("color" to "#ffd700"), ".dizhi-chou" to utsMapOf("color" to "#8b7355"), ".dizhi-chen" to utsMapOf("color" to "#8b7355"), ".dizhi-wei" to utsMapOf("color" to "#8b7355"), ".dizhi-xu" to utsMapOf("color" to "#8b7355")), "general-name" to padStyleMapOf(utsMapOf("color" to "#198754", "marginLeft" to 5)), "center-si-ke" to padStyleMapOf(utsMapOf("width" to "100%", "fontSize" to 14.4, "lineHeight" to 1.35, "textAlign" to "center", "marginTop" to 8)), "si-ke-row" to padStyleMapOf(utsMapOf("flexDirection" to "row", "justifyContent" to "space-around", "marginBottom" to 0)), "si-ke-item" to padStyleMapOf(utsMapOf("width" to "25%", "textAlign" to "center", "paddingTop" to 0, "paddingRight" to 1, "paddingBottom" to 0, "paddingLeft" to 1)), "si-ke-general" to padStyleMapOf(utsMapOf("color" to "#198754")), "si-ke-heaven" to utsMapOf(".dizhi-zi" to utsMapOf("color" to "#0d6efd"), ".dizhi-hai" to utsMapOf("color" to "#0d6efd"), ".dizhi-yin" to utsMapOf("color" to "#28a745"), ".dizhi-mao" to utsMapOf("color" to "#28a745"), ".dizhi-si" to utsMapOf("color" to "#dc3545"), ".dizhi-wu" to utsMapOf("color" to "#dc3545"), ".dizhi-shen" to utsMapOf("color" to "#ffd700"), ".dizhi-you" to utsMapOf("color" to "#ffd700"), ".dizhi-chou" to utsMapOf("color" to "#8b7355"), ".dizhi-chen" to utsMapOf("color" to "#8b7355"), ".dizhi-wei" to utsMapOf("color" to "#8b7355"), ".dizhi-xu" to utsMapOf("color" to "#8b7355")), "si-ke-earth" to utsMapOf(".tiangan-jia" to utsMapOf("color" to "#28a745"), ".tiangan-yi" to utsMapOf("color" to "#28a745"), ".tiangan-bing" to utsMapOf("color" to "#dc3545"), ".tiangan-ding" to utsMapOf("color" to "#dc3545"), ".tiangan-wu" to utsMapOf("color" to "#8b7355"), ".tiangan-ji" to utsMapOf("color" to "#8b7355"), ".tiangan-geng" to utsMapOf("color" to "#ffd700"), ".tiangan-xin" to utsMapOf("color" to "#ffd700"), ".tiangan-ren" to utsMapOf("color" to "#0d6efd"), ".tiangan-gui" to utsMapOf("color" to "#0d6efd"), ".dizhi-zi" to utsMapOf("color" to "#0d6efd"), ".dizhi-hai" to utsMapOf("color" to "#0d6efd"), ".dizhi-yin" to utsMapOf("color" to "#28a745"), ".dizhi-mao" to utsMapOf("color" to "#28a745"), ".dizhi-si" to utsMapOf("color" to "#dc3545"), ".dizhi-wu" to utsMapOf("color" to "#dc3545"), ".dizhi-shen" to utsMapOf("color" to "#ffd700"), ".dizhi-you" to utsMapOf("color" to "#ffd700"), ".dizhi-chou" to utsMapOf("color" to "#8b7355"), ".dizhi-chen" to utsMapOf("color" to "#8b7355"), ".dizhi-wei" to utsMapOf("color" to "#8b7355"), ".dizhi-xu" to utsMapOf("color" to "#8b7355")), "chart-subject" to padStyleMapOf(utsMapOf("paddingTop" to 16, "paddingRight" to 16, "paddingBottom" to 16, "paddingLeft" to 16, "borderTopWidth" to 1, "borderTopStyle" to "solid", "borderTopColor" to "#dee2e6", "fontSize" to 14.4, "flexDirection" to "row")), "subject-text" to padStyleMapOf(utsMapOf("color" to "#212529")), "footer-tabs" to padStyleMapOf(utsMapOf("flexDirection" to "row", "justifyContent" to "space-around", "paddingTop" to 12, "paddingRight" to 12, "paddingBottom" to 12, "paddingLeft" to 12, "borderTopWidth" to 1, "borderTopStyle" to "solid", "borderTopColor" to "#dee2e6", "backgroundColor" to "#f8f9fa", "fontSize" to 12.8, "color" to "#6c757d")), "tab-span" to padStyleMapOf(utsMapOf("paddingTop" to 3, "paddingRight" to 6, "paddingBottom" to 3, "paddingLeft" to 6, "borderTopLeftRadius" to 4, "borderTopRightRadius" to 4, "borderBottomRightRadius" to 4, "borderBottomLeftRadius" to 4)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
