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
open class GenPagesComponentScrollViewScrollViewRefresher : BasePage {
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
            var lists: UTSArray<String> = utsArrayOf()
            run {
                var i: Number = 0
                while(i < 20){
                    lists.push("item---" + i)
                    i++
                }
            }
            this.scrollData = lists
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        return createElementVNode("view", utsMapOf("class" to "container"), utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to "scroll-view 下拉刷新")),
            createElementVNode("scroll-view", utsMapOf("class" to "scroll", "refresher-enabled" to "true", "refresher-triggered" to _ctx.refresherTriggered, "onRefresherrefresh" to _ctx.onRefresherrefresh, "onRefresherabort" to _ctx.onRefresherabort, "onRefresherrestore" to _ctx.onRefresherrestore, "onRefresherpulling" to _ctx.onRefresherpulling, "onScrolltolower" to _ctx.onScrolltolower, "show-scrollbar" to _ctx.showScrollbar), utsArrayOf(
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.scrollData, fun(key, __key, __index, _cached): Any {
                    return createElementVNode("view", utsMapOf("key" to key), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "scroll-item"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "scroll-item-title"), toDisplayString(key), 1)
                        ))
                    ))
                }
                ), 128)
            ), 40, utsArrayOf(
                "refresher-triggered",
                "onRefresherrefresh",
                "onRefresherabort",
                "onRefresherrestore",
                "onRefresherpulling",
                "onScrolltolower",
                "show-scrollbar"
            ))
        ))
    }
    open var scrollData: UTSArray<String> by `$data`
    open var refresherTriggered: Boolean by `$data`
    open var refresherrefresh: Boolean by `$data`
    open var refresherrefreshTimes: Number by `$data`
    open var showScrollbar: Boolean by `$data`
    open var refresherrefreshTest: String by `$data`
    open var onRefresherabortTest: String by `$data`
    open var onRefresherrestoreTest: String by `$data`
    open var onRefresherpullingTest: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("scrollData" to utsArrayOf<String>(), "refresherTriggered" to false, "refresherrefresh" to false, "refresherrefreshTimes" to 0, "showScrollbar" to false, "refresherrefreshTest" to "", "onRefresherabortTest" to "", "onRefresherrestoreTest" to "", "onRefresherpullingTest" to "")
    }
    open var onRefresherrefresh = ::gen_onRefresherrefresh_fn
    open fun gen_onRefresherrefresh_fn(e: UniRefresherEvent) {
        this.refresherrefresh = true
        console.log("onRefresherrefresh------下拉刷新触发", " at pages/component/scroll-view/scroll-view-refresher.uvue:48")
        this.checkEventTest(RefresherEventTest(type = e.type, target = e.target, currentTarget = e.currentTarget, dy = e.detail.dy), "refresherrefresh")
        this.refresherTriggered = true
        this.refresherrefreshTimes++
        setTimeout(fun(){
            this.refresherTriggered = false
        }
        , 1500)
    }
    open var onRefresherabort = ::gen_onRefresherabort_fn
    open fun gen_onRefresherabort_fn(e: UniRefresherEvent) {
        console.log("onRefresherabort------下拉刷新被中止", " at pages/component/scroll-view/scroll-view-refresher.uvue:62")
        this.checkEventTest(RefresherEventTest(type = e.type, target = e.target, currentTarget = e.currentTarget, dy = e.detail.dy), "refresherabort")
    }
    open var onRefresherrestore = ::gen_onRefresherrestore_fn
    open fun gen_onRefresherrestore_fn(e: UniRefresherEvent) {
        this.refresherrefresh = false
        console.log("onRefresherrestore------下拉刷新被复位", " at pages/component/scroll-view/scroll-view-refresher.uvue:72")
        this.checkEventTest(RefresherEventTest(type = e.type, target = e.target, currentTarget = e.currentTarget, dy = e.detail.dy), "refresherrestore")
    }
    open var onRefresherpulling = ::gen_onRefresherpulling_fn
    open fun gen_onRefresherpulling_fn(e: UniRefresherEvent) {
        console.log("onRefresherpulling------拉刷新控件被下拉-dy=" + e.detail.dy, " at pages/component/scroll-view/scroll-view-refresher.uvue:81")
        this.checkEventTest(RefresherEventTest(type = e.type, target = e.target, currentTarget = e.currentTarget, dy = e.detail.dy), "refresherpulling")
    }
    open var onScrolltolower = ::gen_onScrolltolower_fn
    open fun gen_onScrolltolower_fn(e: UniScrollToLowerEvent) {
        console.log("onScrolltolower 滚动到底部-----" + e.detail.direction, " at pages/component/scroll-view/scroll-view-refresher.uvue:90")
    }
    open var checkEventTest = ::gen_checkEventTest_fn
    open fun gen_checkEventTest_fn(e: RefresherEventTest, eventName: String) {
        val isPass = e.type === eventName && e.target is UniElement && e.currentTarget is UniElement && e.dy > 0
        val result = if (isPass) {
            "" + eventName + ":Success"
        } else {
            "" + eventName + ":Fail"
        }
        when (eventName) {
            "refresherrefresh" -> 
                this.refresherrefreshTest = result
            "refresherpulling" -> 
                this.onRefresherpullingTest = result
            "refresherrestore" -> 
                this.onRefresherrestoreTest = result
            "refresherabort" -> 
                this.onRefresherabortTest = result
            else -> 
                {}
        }
    }
    open var setPageStyle = ::gen_setPageStyle_fn
    open fun gen_setPageStyle_fn(pageStyle: UTSJSONObject) {
        val pages = getCurrentPages()
        val currentPage = pages[pages.length - 1]
        currentPage.setPageStyle(pageStyle)
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
                return utsMapOf("container" to padStyleMapOf(utsMapOf("display" to "flex", "flexDirection" to "column", "flex" to 1)), "scroll" to padStyleMapOf(utsMapOf("backgroundColor" to "#eeeeee", "position" to "relative", "width" to "100%", "flex" to 1, "display" to "flex", "flexDirection" to "column", "borderTopColor" to "#FF0000", "borderRightColor" to "#FF0000", "borderBottomColor" to "#FF0000", "borderLeftColor" to "#FF0000", "borderTopLeftRadius" to 6, "borderTopRightRadius" to 6, "borderBottomRightRadius" to 6, "borderBottomLeftRadius" to 6)), "scroll-item" to padStyleMapOf(utsMapOf("marginLeft" to 6, "marginRight" to 6, "marginTop" to 6, "backgroundColor" to "#ffffff", "borderTopLeftRadius" to 4, "borderTopRightRadius" to 4, "borderBottomRightRadius" to 4, "borderBottomLeftRadius" to 4)), "scroll-item-title" to padStyleMapOf(utsMapOf("width" to "100%", "height" to 60, "lineHeight" to "60px", "textAlign" to "center", "color" to "#555555")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
