@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER", "NAME_SHADOWING", "UNNECESSARY_NOT_NULL_ASSERTION")
package uni.UNI740BA18
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
import io.dcloud.uniapp.extapi.getElementById as uni_getElementById
import io.dcloud.uniapp.extapi.showModal as uni_showModal
open class GenPagesComponentTextTextProps : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {
        onLoad(fun(_: OnLoadOptions) {
            stat_instance.onLoad(this)
        }
        , __ins)
        onPageShow(fun() {
            stat_instance.onShow(this)
        }
        , __ins)
        onPageHide(fun() {
            stat_instance.onHide(this)
        }
        , __ins)
        onUnload(fun() {
            stat_instance.onUnload(this)
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("view", null, utsArrayOf(
                createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                    "title"
                )),
                createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-title-text"), "text相关属性示例")
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "根据宽度自动折行")
                    )),
                    createElementVNode("view", utsMapOf("class" to "text-box"), utsArrayOf(
                        createElementVNode("text", null, toDisplayString(_ctx.multiLineText), 1)
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "\\n换行")
                    )),
                    createElementVNode("view", utsMapOf("class" to "text-box"), utsArrayOf(
                        createElementVNode("text", null, "\n 换行"),
                        createElementVNode("text", null, "\\n 换行"),
                        createElementVNode("text", null, "\\\n 换行"),
                        createElementVNode("text", null, "\n 换行 \\n 换行 \\\n 换行 \\\\n 换行 \\\\\n 换行"),
                        createElementVNode("text", utsMapOf("space" to "nbsp"), "HBuilderX，轻巧、极速，极客编辑器;\nuni-app x，终极跨平台方案;")
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "截断（clip）")
                    )),
                    createElementVNode("view", utsMapOf("class" to "text-box"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-flex-item", "style" to normalizeStyle(utsMapOf("width" to "100%", "text-overflow" to "clip", "white-space" to "nowrap"))), toDisplayString(_ctx.multiLineText), 5)
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "截断（ellipsis）")
                    )),
                    createElementVNode("view", utsMapOf("class" to "text-box"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-flex-item", "style" to normalizeStyle(utsMapOf("width" to "100%", "text-overflow" to "ellipsis", "white-space" to "nowrap"))), toDisplayString(_ctx.multiLineText), 5)
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "selectable")
                    )),
                    createElementVNode("view", utsMapOf("class" to "text-box"), utsArrayOf(
                        createElementVNode("text", utsMapOf("selectable" to true), toDisplayString(_ctx.singleLineText), 1)
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "space"),
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "依次为nbsp ensp emsp效果")
                    )),
                    createElementVNode("view", utsMapOf("class" to "text-box"), utsArrayOf(
                        createElementVNode("text", utsMapOf("space" to "nbsp"), toDisplayString(_ctx.singleLineText), 1),
                        createElementVNode("text", utsMapOf("space" to "ensp"), toDisplayString(_ctx.singleLineText), 1),
                        createElementVNode("text", utsMapOf("space" to "emsp"), toDisplayString(_ctx.singleLineText), 1)
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "decode"),
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "依次为lt gt amp apos nbsp ensp emsp效果")
                    )),
                    createElementVNode("view", utsMapOf("class" to "text-box"), utsArrayOf(
                        createElementVNode("text", utsMapOf("decode" to true), toDisplayString(_ctx.decodeStr), 1),
                        createElementVNode("text", utsMapOf("decode" to true), "uni-app x，终极跨平台方案"),
                        createElementVNode("text", utsMapOf("decode" to true), "uni-app x，终极跨平台方案"),
                        createElementVNode("text", utsMapOf("decode" to true), "uni-app x，终极跨平台方案")
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "嵌套1")
                    )),
                    createElementVNode("view", utsMapOf("class" to "text-box"), utsArrayOf(
                        createElementVNode("text", utsMapOf("onClick" to _ctx.nestedText1Tap), utsArrayOf(
                            "一级节点黑色 ",
                            createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("color" to "red", "background-color" to "yellow")), "onClick" to _ctx.nestedText2Tap), utsArrayOf(
                                "二级节点红色且背景色黄色 ",
                                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("text-decoration-line" to "underline", "color" to "blue")), "onClick" to _ctx.nestedText3Tap), "App三级节点不继承二级的颜色", 12, utsArrayOf(
                                    "onClick"
                                ))
                            ), 12, utsArrayOf(
                                "onClick"
                            )),
                            createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "50px"))), "二级节点大字体", 4)
                        ), 8, utsArrayOf(
                            "onClick"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "嵌套2")
                    )),
                    createElementVNode("view", utsMapOf("class" to "text-box"), utsArrayOf(
                        createElementVNode("text", null, utsArrayOf(
                            createElementVNode("text", null, "文字应居中显示")
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "padding和border")
                    )),
                    createElementVNode("view", utsMapOf("class" to "text-box"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "text-padding-border"), "hello uni-app x"),
                        createElementVNode("text", utsMapOf("class" to "text-padding-border", "style" to normalizeStyle(utsMapOf("width" to "200px"))), "hello uni-app x", 4),
                        createElementVNode("text", utsMapOf("class" to "text-padding-border", "style" to normalizeStyle(utsMapOf("height" to "100px"))), "hello uni-app x", 4),
                        createElementVNode("text", utsMapOf("class" to "text-padding-border", "style" to normalizeStyle(utsMapOf("width" to "200px", "height" to "100px"))), "hello uni-app x", 4)
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "含换行符的多行文本(3行)，但不自动换行，可横向滚动")
                    )),
                    createElementVNode("scroll-view", utsMapOf("class" to "text-container", "direction" to "horizontal"), utsArrayOf(
                        createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("white-space" to "nowrap", "align-self" to "flex-start"))), "HBuilderX，轻巧、极速，极客编辑器；\nuni-app x，是下一代 uni-app，是一个跨平台应用开发引擎。uni-app x 是一个庞大的工程，它包括uts语言、uvue渲染引擎、uni的组件和API、以及扩展机制。\nuts是一门类ts的、跨平台的、新语言。", 4)
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "点击事件测试")
                    )),
                    createElementVNode("view", utsMapOf("class" to "text-box", "style" to normalizeStyle(utsMapOf("flex-direction" to "row"))), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "text-icon", "style" to normalizeStyle(utsMapOf("background-color" to "aqua")), "onClick" to _ctx.iconTap), toDisplayString(String.fromCharCode(parseInt("E650", 16))), 13, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("text", utsMapOf("class" to "text-icon", "style" to normalizeStyle(utsMapOf("background-color" to "yellow")), "onClick" to _ctx.iconTap2), toDisplayString(String.fromCharCode(parseInt("EA08", 16))), 13, utsArrayOf(
                            "onClick"
                        ))
                    ), 4),
                    if (isTrue(_ctx.autoTest)) {
                        createElementVNode("view", utsMapOf("key" to 0), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-row"), utsArrayOf(
                                createElementVNode("text", utsMapOf("id" to "empty-text"))
                            )),
                            createElementVNode("view", utsMapOf("class" to "uni-row"), utsArrayOf(
                                createElementVNode("text", utsMapOf("id" to "empty-text2"), toDisplayString(_ctx.emptyText), 1)
                            )),
                            createElementVNode("view", utsMapOf("class" to "uni-row"), utsArrayOf(
                                createElementVNode("text", utsMapOf("id" to "empty-text3", "style" to normalizeStyle(utsMapOf("width" to "100px", "height" to "100px"))), toDisplayString(_ctx.emptyText), 5)
                            )),
                            createElementVNode("text", null, utsArrayOf(
                                "一级节点文本 ",
                                createElementVNode("text", null, utsArrayOf(
                                    "二级节点文本 ",
                                    createElementVNode("text", utsMapOf("id" to "nested-text"), toDisplayString(_ctx.nestedText), 1)
                                ))
                            )),
                            createElementVNode("text", utsMapOf("id" to "height-text", "style" to normalizeStyle(utsMapOf("height" to "50px"))), toDisplayString(_ctx.heightText), 5),
                            createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("position" to "fixed")), "id" to "nested-text2", "onClick" to _ctx.nestedText1TapForTest), utsArrayOf(
                                "1 ",
                                createElementVNode("text", utsMapOf("onClick" to _ctx.nestedText2TapForTest), utsArrayOf(
                                    "2 ",
                                    createElementVNode("text", null, "3")
                                ), 8, utsArrayOf(
                                    "onClick"
                                ))
                            ), 12, utsArrayOf(
                                "onClick"
                            ))
                        ))
                    } else {
                        createCommentVNode("v-if", true)
                    }
                ))
            ))
        ), 4)
    }
    open var decodeStr: String by `$data`
    open var title: String by `$data`
    open var multiLineText: String by `$data`
    open var singleLineText: String by `$data`
    open var autoTest: Boolean by `$data`
    open var nestedText: String by `$data`
    open var emptyText: String by `$data`
    open var heightText: String by `$data`
    open var isNestedText1TapTriggered: Boolean by `$data`
    open var isNestedText2TapTriggered: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("decodeStr" to "&lt; &gt; &amp; &apos;", "title" to "text-props", "multiLineText" to "HBuilderX，轻巧、极速，极客编辑器；uni-app x，终极跨平台方案；uts，大一统语言；HBuilderX，轻巧、极速，极客编辑器；uni-app x，终极跨平台方案；uts，大一统语言", "singleLineText" to "uni-app x，终极跨平台方案", "autoTest" to false, "nestedText" to "三级节点文本", "emptyText" to "空文本", "heightText" to "设置高度文本", "isNestedText1TapTriggered" to false, "isNestedText2TapTriggered" to false)
    }
    open var nestedText1Tap = ::gen_nestedText1Tap_fn
    open fun gen_nestedText1Tap_fn() {
        uni_showModal(ShowModalOptions(title = "点击了", content = "一级节点黑色", showCancel = false))
    }
    open var nestedText2Tap = ::gen_nestedText2Tap_fn
    open fun gen_nestedText2Tap_fn(e: UniPointerEvent) {
        e.stopPropagation()
        uni_showModal(ShowModalOptions(title = "点击了", content = "二级节点红色且背景色黄色", showCancel = false))
    }
    open var nestedText3Tap = ::gen_nestedText3Tap_fn
    open fun gen_nestedText3Tap_fn(e: UniPointerEvent) {
        e.stopPropagation()
        uni_showModal(ShowModalOptions(title = "点击了", content = "App三级节点不继承二级的颜色", showCancel = false))
    }
    open var iconTap = ::gen_iconTap_fn
    open fun gen_iconTap_fn() {
        uni_showModal(ShowModalOptions(title = "点击了", content = "字体图标1", showCancel = false))
    }
    open var iconTap2 = ::gen_iconTap2_fn
    open fun gen_iconTap2_fn() {
        uni_showModal(ShowModalOptions(title = "点击了", content = "字体图标2", showCancel = false))
    }
    open var setNestedText = ::gen_setNestedText_fn
    open fun gen_setNestedText_fn() {
        this.nestedText = "修改三级节点文本"
    }
    open var setEmptyText = ::gen_setEmptyText_fn
    open fun gen_setEmptyText_fn() {
        this.emptyText = ""
    }
    open var setHeightText = ::gen_setHeightText_fn
    open fun gen_setHeightText_fn() {
        this.heightText = "修改设置高度文本"
    }
    open var getBoundingClientRectForTest = ::gen_getBoundingClientRectForTest_fn
    open fun gen_getBoundingClientRectForTest_fn(): DOMRect? {
        return uni_getElementById("nested-text2")?.getBoundingClientRect()
    }
    open var nestedText1TapForTest = ::gen_nestedText1TapForTest_fn
    open fun gen_nestedText1TapForTest_fn() {
        this.isNestedText1TapTriggered = true
    }
    open var nestedText2TapForTest = ::gen_nestedText2TapForTest_fn
    open fun gen_nestedText2TapForTest_fn() {
        this.isNestedText2TapTriggered = true
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
                return utsMapOf("text-box" to padStyleMapOf(utsMapOf("marginBottom" to 20, "paddingTop" to 20, "paddingRight" to 0, "paddingBottom" to 20, "paddingLeft" to 0, "backgroundColor" to "#ffffff", "justifyContent" to "center", "alignItems" to "center")), "text-container" to padStyleMapOf(utsMapOf("width" to "100%", "backgroundColor" to "#ffffff", "paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10)), "text" to padStyleMapOf(utsMapOf("fontSize" to 15, "color" to "#353535", "lineHeight" to "27px", "textAlign" to "center")), "text-padding-border" to padStyleMapOf(utsMapOf("marginTop" to 5, "paddingTop" to 20, "paddingRight" to 20, "paddingBottom" to 20, "paddingLeft" to 20, "borderTopWidth" to 5, "borderRightWidth" to 5, "borderBottomWidth" to 5, "borderLeftWidth" to 5, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#FF0000", "borderRightColor" to "#FF0000", "borderBottomColor" to "#FF0000", "borderLeftColor" to "#FF0000", "textAlign" to "center")), "text-icon" to padStyleMapOf(utsMapOf("fontFamily" to "uni-icon", "fontSize" to 100)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
