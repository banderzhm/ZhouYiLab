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
open class GenPagesComponentScrollViewScrollViewCustomRefresherProps : BasePage {
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
        val _component_refresh_box = resolveComponent("refresh-box")
        return createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("overflow" to "visible", "flex" to "1"))), utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to "list-view 下拉刷新")),
            createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1")), "refresher-enabled" to true, "refresher-triggered" to _ctx.refresherTriggered, "refresher-default-style" to "none", "onRefresherpulling" to _ctx.onRefresherpulling, "onRefresherrefresh" to _ctx.onRefresherrefresh, "onRefresherrestore" to _ctx.onRefreshrestore, "refresher-max-drag-distance" to "200px"), utsArrayOf(
                createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("height" to "25px"))), null, 4),
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.listCount, fun(i, __key, __index, _cached): Any {
                    return createElementVNode("view", utsMapOf("class" to "content-item"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "text"), "item-" + toDisplayString(i), 1)
                    ))
                }
                ), 256),
                createVNode(_component_refresh_box, utsMapOf("slot" to "refresher", "state" to _ctx.state), null, 8, utsArrayOf(
                    "state"
                ))
            ), 44, utsArrayOf(
                "refresher-triggered",
                "onRefresherpulling",
                "onRefresherrefresh",
                "onRefresherrestore"
            ))
        ), 4)
    }
    open var listCount: Number by `$data`
    open var refresherTriggered: Boolean by `$data`
    open var pullingDistance: Number by `$data`
    open var resetting: Boolean by `$data`
    open var triggered: Boolean by `$data`
    open var state: Number by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("listCount" to 20, "refresherTriggered" to false, "pullingDistance" to 0, "resetting" to false, "triggered" to false, "state" to computed<Number>(fun(): Number {
            if (this.resetting) {
                return 3
            }
            if (this.refresherTriggered) {
                return 2
            }
            if (this.pullingDistance > 45) {
                return 1
            } else {
                return 0
            }
        }
        ))
    }
    open var onRefresherpulling = ::gen_onRefresherpulling_fn
    open fun gen_onRefresherpulling_fn(e: RefresherEvent) {
        this.pullingDistance = e.detail.dy
        this.triggered = true
    }
    open var onRefresherrefresh = ::gen_onRefresherrefresh_fn
    open fun gen_onRefresherrefresh_fn() {
        this.refresherTriggered = true
        setTimeout(fun(){
            this.refresherTriggered = false
            this.resetting = true
        }
        , 1500)
    }
    open var onRefreshrestore = ::gen_onRefreshrestore_fn
    open fun gen_onRefreshrestore_fn() {
        this.pullingDistance = 0
        this.resetting = false
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
                return utsMapOf("content-item" to padStyleMapOf(utsMapOf("paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15, "marginTop" to 5, "marginRight" to 0, "marginBottom" to 5, "marginLeft" to 0, "backgroundColor" to "#ffffff", "borderTopLeftRadius" to 5, "borderTopRightRadius" to 5, "borderBottomRightRadius" to 5, "borderBottomLeftRadius" to 5)), "text" to padStyleMapOf(utsMapOf("fontSize" to 14, "color" to "#666666", "lineHeight" to "20px")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf("refreshBox" to GenPagesComponentScrollViewRefreshBoxRefreshBoxClass)
    }
}
