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
open class GenPagesComponentNestedScrollBodyNestedScrollBody : BasePage {
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
            setTimeout(fun(){
                this.swiperList = utsArrayOf(
                    object : UTSJSONObject() {
                        var type = "list1"
                    },
                    object : UTSJSONObject() {
                        var type = "list2"
                    },
                    object : UTSJSONObject() {
                        var type = "list3"
                    }
                )
                var lists: UTSArray<String> = utsArrayOf()
                run {
                    var i: Number = 0
                    while(i < 30){
                        lists.push("item---" + i)
                        i++
                    }
                }
                this.scrollData = lists
            }
            , 500)
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_refresh_box = resolveComponent("refresh-box")
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1")), "type" to "nested", "direction" to "vertical", "refresher-enabled" to "true", "refresher-default-style" to "none", "bounces" to "false", "refresher-triggered" to _ctx.refresherTriggered, "onRefresherpulling" to _ctx.onRefresherpulling, "onRefresherrefresh" to _ctx.onRefresherrefresh, "onRefresherrestore" to _ctx.onRefreshrestore, "onScrollend" to _ctx.scrollEnd), utsArrayOf(
            createElementVNode("nested-scroll-header", null, utsArrayOf(
                createElementVNode("swiper", utsMapOf("ref" to "header", "indicator-dots" to "true", "circular" to "true"), utsArrayOf(
                    createElementVNode(Fragment, null, RenderHelpers.renderList(3, fun(i, __key, __index, _cached): Any {
                        return createElementVNode("swiper-item", utsMapOf("item-id" to i), utsArrayOf(
                            createElementVNode("image", utsMapOf("src" to "/static/shuijiao.jpg", "style" to normalizeStyle(utsMapOf("width" to "100%", "height" to "240px"))), null, 4)
                        ), 8, utsArrayOf(
                            "item-id"
                        ))
                    }
                    ), 64)
                ), 512)
            )),
            createElementVNode("nested-scroll-body", null, utsArrayOf(
                createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex-direction" to "row"))), utsArrayOf(
                        createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("padding" to "12px 15px"))), "nested-scroll-body", 4)
                    ), 4),
                    createElementVNode("swiper", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.swiperList, fun(item, __key, __index, _cached): Any {
                            return createElementVNode("swiper-item", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                                createElementVNode("list-view", utsMapOf("bounces" to "false", "id" to "body-list", "scroll-top" to _ctx.scrollTop, "style" to normalizeStyle(utsMapOf("flex" to "1")), "key" to item["type"], "associative-container" to "nested-scroll-view", "refresher-enabled" to false), utsArrayOf(
                                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.scrollData, fun(key, __key, __index, _cached): Any {
                                        return createElementVNode("list-item", null, utsArrayOf(
                                            createElementVNode("view", utsMapOf("class" to "scroll-item"), utsArrayOf(
                                                createElementVNode("text", utsMapOf("class" to "scroll-item-title"), toDisplayString(key), 1)
                                            ))
                                        ))
                                    }
                                    ), 256)
                                ), 12, utsArrayOf(
                                    "scroll-top"
                                ))
                            ), 4)
                        }
                        ), 256)
                    ), 4)
                ), 4),
                createElementVNode("text", null, "不会渲染，因为 nested-scroll-body 只会渲染第一个子节点")
            )),
            createVNode(_component_refresh_box, utsMapOf("slot" to "refresher", "state" to _ctx.state), null, 8, utsArrayOf(
                "state"
            ))
        ), 44, utsArrayOf(
            "refresher-triggered",
            "onRefresherpulling",
            "onRefresherrefresh",
            "onRefresherrestore",
            "onScrollend"
        ))
    }
    open var scrollData: UTSArray<String> by `$data`
    open var swiperList: UTSArray<UTSJSONObject> by `$data`
    open var scrollTop: Number by `$data`
    open var refresherTriggered: Boolean by `$data`
    open var pullingDistance: Number by `$data`
    open var resetting: Boolean by `$data`
    open var testScrollTop: Number by `$data`
    open var state: Number by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("scrollData" to utsArrayOf<String>(), "swiperList" to utsArrayOf<UTSJSONObject>(), "scrollTop" to 0, "refresherTriggered" to false, "pullingDistance" to 0, "resetting" to false, "testScrollTop" to 0, "state" to computed<Number>(fun(): Number {
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
    open var testBodyScrollBy = ::gen_testBodyScrollBy_fn
    open fun gen_testBodyScrollBy_fn(y: Number) {
        this.scrollTop = y
    }
    open var scrollEnd = ::gen_scrollEnd_fn
    open fun gen_scrollEnd_fn(e: UniScrollEvent) {
        this.testScrollTop = e.detail.scrollTop
        console.log("testScrollTop:", this.testScrollTop, " at pages/component/nested-scroll-body/nested-scroll-body.uvue:103")
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
                return utsMapOf("scroll-item" to padStyleMapOf(utsMapOf("marginLeft" to 6, "marginRight" to 6, "marginTop" to 6, "backgroundColor" to "#ffffff", "borderTopLeftRadius" to 4, "borderTopRightRadius" to 4, "borderBottomRightRadius" to 4, "borderBottomLeftRadius" to 4)), "scroll-item-title" to padStyleMapOf(utsMapOf("width" to "100%", "height" to 60, "lineHeight" to "60px", "textAlign" to "center", "color" to "#555555")), "scroll-header-tiem" to padStyleMapOf(utsMapOf("height" to 200, "backgroundColor" to "#66ccff", "alignItems" to "center", "justifyContent" to "center")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf("refreshBox" to GenPagesTemplateCustomRefresherRefreshBoxRefreshBoxClass)
    }
}
