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
open class GenPagesComponentScrollViewScrollViewRefresherProps : BasePage {
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
        onLoad(fun(_: OnLoadOptions) {
            run {
                var i: Number = 0
                while(i < 10){
                    val item = Item1(id = "item" + i, label = "item" + i)
                    this.items.push(item)
                    i++
                }
            }
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        val _component_switch = resolveComponent("switch")
        return createElementVNode("view", utsMapOf("class" to "page-scroll-view"), utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to "下拉刷新的scroll-view属性示例")),
            createElementVNode("view", utsMapOf("class" to "uni-margin-wrap"), utsArrayOf(
                createElementVNode("scroll-view", utsMapOf("direction" to "vertical", "refresher-enabled" to _ctx.refresherEnabled, "refresher-threshold" to _ctx.refresherThreshold, "refresher-default-style" to _ctx.refresherDefaultStyle, "refresher-background" to _ctx.refresherBackground, "refresher-triggered" to _ctx.refresherTriggered, "onRefresherpulling" to _ctx.refresherpulling, "onRefresherrefresh" to _ctx.refresherrefresh, "onRefresherrestore" to _ctx.refresherrestore, "onRefresherabort" to _ctx.refresherabort, "style" to normalizeStyle(utsMapOf("width" to "100%", "height" to "100%"))), utsArrayOf(
                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.items, fun(item, _, __index, _cached): Any {
                        return createElementVNode("view", utsMapOf("class" to "item", "id" to item.id), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "uni-text"), toDisplayString(item.label), 1)
                        ), 8, utsArrayOf(
                            "id"
                        ))
                    }
                    ), 256)
                ), 44, utsArrayOf(
                    "refresher-enabled",
                    "refresher-threshold",
                    "refresher-default-style",
                    "refresher-background",
                    "refresher-triggered",
                    "onRefresherpulling",
                    "onRefresherrefresh",
                    "onRefresherrestore",
                    "onRefresherabort"
                ))
            )),
            createElementVNode("scroll-view", utsMapOf("class" to "uni-list", "style" to normalizeStyle(utsMapOf("padding-top" to "16px")), "showScrollbar" to true), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "uni-list-cell-padding"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "refresher-tips"), "**下拉刷新的属性设置需要先打开下拉刷新开关**")
                )),
                createElementVNode("view", utsMapOf("class" to "uni-list-cell uni-list-cell-padding"), utsArrayOf(
                    createElementVNode("text", null, "是否开启下拉刷新"),
                    createVNode(_component_switch, utsMapOf("checked" to _ctx.refresherEnabled, "onChange" to _ctx.handleTrunOnRefresher), null, 8, utsArrayOf(
                        "checked",
                        "onChange"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-list-cell uni-list-cell-padding"), utsArrayOf(
                    createElementVNode("text", null, "设置下拉刷新状态"),
                    createVNode(_component_switch, utsMapOf("disabled" to !_ctx.refresherEnabled, "checked" to _ctx.refresherTriggered, "onChange" to fun(){
                        _ctx.refresherTriggered = !_ctx.refresherTriggered
                    }
                    ), null, 8, utsArrayOf(
                        "disabled",
                        "checked",
                        "onChange"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-list-cell uni-list-cell-padding"), utsArrayOf(
                    createElementVNode("text", null, "设置下拉刷新阈值"),
                    createElementVNode("input", utsMapOf("class" to "uni-list-cell-input", "disabled" to !_ctx.refresherEnabled, "value" to _ctx.refresherThreshold, "type" to "number", "onInput" to _ctx.handleRefresherThresholdInput), null, 40, utsArrayOf(
                        "disabled",
                        "value",
                        "onInput"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-list-cell uni-list-cell-padding"), utsArrayOf(
                    createElementVNode("text", null, "设置下拉刷新区域背景颜色"),
                    createElementVNode("input", utsMapOf("class" to "uni-list-cell-input", "disabled" to !_ctx.refresherEnabled, "value" to _ctx.refresherBackground, "onInput" to _ctx.handleRefresherBackground), null, 40, utsArrayOf(
                        "disabled",
                        "value",
                        "onInput"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-list-cell-padding"), utsArrayOf(
                    createElementVNode("text", null, "设置下拉刷新默认样式"),
                    createElementVNode("view", utsMapOf("class" to "switch-refresher-group"), utsArrayOf(
                        createElementVNode("button", utsMapOf("class" to "switch-refresher-style", "type" to "primary", "size" to "mini", "onClick" to fun(){
                            _ctx.refresherDefaultStyle = "none"
                        }
                        ), "none", 8, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("button", utsMapOf("class" to "switch-refresher-style", "type" to "primary", "size" to "mini", "onClick" to fun(){
                            _ctx.refresherDefaultStyle = "black"
                        }
                        ), "black", 8, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("button", utsMapOf("class" to "switch-refresher-style", "type" to "primary", "size" to "mini", "onClick" to fun(){
                            _ctx.refresherDefaultStyle = "white"
                        }
                        ), "white", 8, utsArrayOf(
                            "onClick"
                        ))
                    ))
                ))
            ), 4)
        ))
    }
    open var items: UTSArray<Item1> by `$data`
    open var refresherEnabled: Boolean by `$data`
    open var refresherTriggered: Boolean by `$data`
    open var refresherThreshold: Number by `$data`
    open var refresherDefaultStyle: String by `$data`
    open var refresherBackground: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("items" to utsArrayOf<Item1>(), "refresherEnabled" to true, "refresherTriggered" to false, "refresherThreshold" to 45, "refresherDefaultStyle" to "black", "refresherBackground" to "transparent")
    }
    open var handleTrunOnRefresher = ::gen_handleTrunOnRefresher_fn
    open fun gen_handleTrunOnRefresher_fn() {
        this.refresherTriggered = false
        setTimeout(fun(){
            this.refresherEnabled = !this.refresherEnabled
        }
        , 0)
    }
    open var handleRefresherThresholdInput = ::gen_handleRefresherThresholdInput_fn
    open fun gen_handleRefresherThresholdInput_fn(e: InputEvent) {
        val value = e.detail.value
        if (value == "") {
            this.refresherThreshold = 45
        } else {
            this.refresherThreshold = parseInt(e.detail.value)
        }
    }
    open var handleRefresherBackground = ::gen_handleRefresherBackground_fn
    open fun gen_handleRefresherBackground_fn(e: InputEvent) {
        val value = e.detail.value
        this.refresherBackground = value
    }
    open var refresherpulling = ::gen_refresherpulling_fn
    open fun gen_refresherpulling_fn() {
        console.log("下拉刷新控件被下拉", " at pages/component/scroll-view/scroll-view-refresher-props.uvue:109")
    }
    open var refresherrefresh = ::gen_refresherrefresh_fn
    open fun gen_refresherrefresh_fn() {
        console.log("下拉刷新被触发", " at pages/component/scroll-view/scroll-view-refresher-props.uvue:112")
        this.refresherTriggered = true
        setTimeout(fun(){
            this.refresherTriggered = false
        }
        , 1500)
    }
    open var refresherrestore = ::gen_refresherrestore_fn
    open fun gen_refresherrestore_fn() {
        console.log("下拉刷新被复位", " at pages/component/scroll-view/scroll-view-refresher-props.uvue:120")
    }
    open var refresherabort = ::gen_refresherabort_fn
    open fun gen_refresherabort_fn() {
        console.log("下拉刷新被中止", " at pages/component/scroll-view/scroll-view-refresher-props.uvue:123")
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
                return utsMapOf("uni-margin-wrap" to padStyleMapOf(utsMapOf("height" to 200, "marginTop" to 0, "marginRight" to 25, "marginBottom" to 25, "marginLeft" to 25)), "item" to padStyleMapOf(utsMapOf("justifyContent" to "center", "alignItems" to "center", "height" to 200, "width" to "100%", "backgroundColor" to "#F0FFFF", "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#D2691E", "borderRightColor" to "#D2691E", "borderBottomColor" to "#D2691E", "borderLeftColor" to "#D2691E")), "refresher-tips" to padStyleMapOf(utsMapOf("fontSize" to 12, "textAlign" to "center", "color" to "#FF0000")), "uni-text" to padStyleMapOf(utsMapOf("color" to "#000000", "fontSize" to 50)), "uni-list" to padStyleMapOf(utsMapOf("flex" to 1)), "uni-list-cell-input" to padStyleMapOf(utsMapOf("maxWidth" to 100, "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#cccccc", "borderRightColor" to "#cccccc", "borderBottomColor" to "#cccccc", "borderLeftColor" to "#cccccc", "textAlign" to "center")), "switch-refresher-group" to padStyleMapOf(utsMapOf("flexDirection" to "row", "marginTop" to 5)), "switch-refresher-style" to padStyleMapOf(utsMapOf("paddingTop" to 2, "paddingRight" to 10, "paddingBottom" to 2, "paddingLeft" to 10, "marginRight" to 5)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
