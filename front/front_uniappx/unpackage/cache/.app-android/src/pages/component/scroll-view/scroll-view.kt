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
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesComponentScrollViewScrollView : BasePage {
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
        val _component_navigator = resolveComponent("navigator")
        return createElementVNode("scroll-view", utsMapOf("class" to "page-scroll-view"), utsArrayOf(
            createElementVNode("view", null, utsArrayOf(
                createVNode(_component_page_head, utsMapOf("title" to "scroll-view,区域滚动视图")),
                createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-title uni-common-mt"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-title-text"), "Vertical Scroll"),
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "纵向滚动")
                    )),
                    createElementVNode("view", null, utsArrayOf(
                        createElementVNode("scroll-view", utsMapOf("scroll-top" to _ctx.scrollTop, "direction" to "vertical", "class" to "scroll-Y", "scroll-with-animation" to "true", "onScrolltoupper" to _ctx.upper, "onScrolltolower" to _ctx.lower, "onScroll" to _ctx.scroll, "onScrollend" to _ctx.end, "show-scrollbar" to _ctx.showScrollbar, "id" to "verticalScrollView"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "scroll-view-item uni-bg-red"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "text"), "A")
                            )),
                            createElementVNode("view", utsMapOf("class" to "scroll-view-item uni-bg-green"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "text"), "B")
                            )),
                            createElementVNode("view", utsMapOf("class" to "scroll-view-item uni-bg-blue"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "text"), "C")
                            ))
                        ), 40, utsArrayOf(
                            "scroll-top",
                            "onScrolltoupper",
                            "onScrolltolower",
                            "onScroll",
                            "onScrollend",
                            "show-scrollbar"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("onClick" to _ctx.goTop, "class" to "uni-center uni-common-mt"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-link"), "点击这里返回顶部")
                    ), 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-title uni-common-mt"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-title-text"), "Horizontal Scroll"),
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "横向滚动")
                    )),
                    createElementVNode("view", null, utsArrayOf(
                        createElementVNode("scroll-view", utsMapOf("class" to "scroll-view_H", "direction" to "horizontal", "onScroll" to _ctx.scroll, "onScrollend" to _ctx.end, "scroll-left" to _ctx.scrollLeft, "show-scrollbar" to _ctx.showScrollbar), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "scroll-view-item_H uni-bg-red"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "text"), "A")
                            )),
                            createElementVNode("view", utsMapOf("class" to "scroll-view-item_H uni-bg-green"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "text"), "B")
                            )),
                            createElementVNode("view", utsMapOf("class" to "scroll-view-item_H uni-bg-blue"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "text"), "C")
                            ))
                        ), 40, utsArrayOf(
                            "onScroll",
                            "onScrollend",
                            "scroll-left",
                            "show-scrollbar"
                        ))
                    )),
                    createVNode(_component_navigator, utsMapOf("url" to "/pages/component/scroll-view/scroll-view-props", "hover-class" to "none"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "class" to "button"), " 非下拉刷新的属性示例 ")
                        )
                    }
                    ), "_" to 1)),
                    createElementVNode("view", utsMapOf("class" to "uni-common-pb")),
                    createVNode(_component_navigator, utsMapOf("url" to "/pages/component/scroll-view/scroll-view-refresher-props", "hover-class" to "none"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "class" to "button"), " 下拉刷新的属性示例 ")
                        )
                    }
                    ), "_" to 1)),
                    createElementVNode("view", utsMapOf("class" to "uni-common-pb")),
                    createVNode(_component_navigator, utsMapOf("url" to "/pages/component/scroll-view/scroll-view-refresher", "hover-class" to "none"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "class" to "button"), " 默认下拉刷新示例 ")
                        )
                    }
                    ), "_" to 1)),
                    createElementVNode("view", utsMapOf("class" to "uni-common-pb")),
                    createVNode(_component_navigator, utsMapOf("url" to "/pages/component/scroll-view/scroll-view-custom-refresher-props", "hover-class" to "none"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "class" to "button"), " 自定义下拉刷新示例 ")
                        )
                    }
                    ), "_" to 1)),
                    createElementVNode("view", utsMapOf("class" to "uni-common-pb"))
                ))
            ))
        ))
    }
    open var scrollTop: Number by `$data`
    open var oldScrollTop: Number by `$data`
    open var scrollLeft: Number by `$data`
    open var showScrollbar: Boolean by `$data`
    open var isScrollTest: String by `$data`
    open var isScrolltolowerTest: String by `$data`
    open var isScrolltoupperTest: String by `$data`
    open var scrollDetailTest: UniScrollEventDetail? by `$data`
    open var scrollEndDetailTest: UniScrollEventDetail? by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("scrollTop" to 0, "oldScrollTop" to 0, "scrollLeft" to 120, "showScrollbar" to true, "isScrollTest" to "", "isScrolltolowerTest" to "", "isScrolltoupperTest" to "", "scrollDetailTest" to null as UniScrollEventDetail?, "scrollEndDetailTest" to null as UniScrollEventDetail?)
    }
    open var upper = ::gen_upper_fn
    open fun gen_upper_fn(e: UniScrollToUpperEvent) {
        console.log("滚动到顶部/左边", e, " at pages/component/scroll-view/scroll-view.uvue:91")
        this.checkEventTest(ScrollEventTest(type = e.type, target = e.target, currentTarget = e.currentTarget, direction = e.detail.direction), "scrolltoupper")
    }
    open var lower = ::gen_lower_fn
    open fun gen_lower_fn(e: UniScrollToLowerEvent) {
        console.log("滚动到底部/右边", e, " at pages/component/scroll-view/scroll-view.uvue:100")
        this.checkEventTest(ScrollEventTest(type = e.type, target = e.target, currentTarget = e.currentTarget, direction = e.detail.direction), "scrolltolower")
    }
    open var scroll = ::gen_scroll_fn
    open fun gen_scroll_fn(e: UniScrollEvent) {
        this.scrollDetailTest = e.detail
        this.checkEventTest(ScrollEventTest(type = e.type, target = e.target, currentTarget = e.currentTarget), "scroll")
        this.oldScrollTop = e.detail.scrollTop
    }
    open var end = ::gen_end_fn
    open fun gen_end_fn(e: UniScrollEvent) {
        console.log("滚动结束时触发", e, " at pages/component/scroll-view/scroll-view.uvue:118")
        this.scrollEndDetailTest = e.detail
        this.checkEventTest(ScrollEventTest(type = e.type, target = e.target, currentTarget = e.currentTarget), "scrollend")
    }
    open var goTop = ::gen_goTop_fn
    open fun gen_goTop_fn() {
        this.scrollTop = this.oldScrollTop
        this.`$nextTick`(fun(){
            this.scrollTop = 0
        }
        )
        uni_showToast(ShowToastOptions(icon = "none", title = "纵向滚动 scrollTop 值已被修改为 0"))
    }
    open var checkEventTest = ::gen_checkEventTest_fn
    open fun gen_checkEventTest_fn(e: ScrollEventTest, eventName: String) {
        val isPass = e.type === eventName && e.target is UniElement && e.currentTarget is UniElement
        val result = if (isPass) {
            "" + eventName + ":Success"
        } else {
            "" + eventName + ":Fail"
        }
        when (eventName) {
            "scroll" -> 
                this.isScrollTest = result
            "scrolltolower" -> 
                this.isScrolltolowerTest = result + ("-" + e.direction)
            "scrolltoupper" -> 
                this.isScrolltoupperTest = result + ("-" + e.direction)
            else -> 
                {}
        }
    }
    open var setVerticalScrollBy = ::gen_setVerticalScrollBy_fn
    open fun gen_setVerticalScrollBy_fn(y: Number) {
        val element = uni_getElementById("verticalScrollView")
        if (element != null) {
            element.scrollBy(0, y)
        }
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
                return utsMapOf("scroll-Y" to padStyleMapOf(utsMapOf("height" to 150)), "scroll-view_H" to padStyleMapOf(utsMapOf("width" to "100%", "flexDirection" to "row")), "scroll-view-item" to padStyleMapOf(utsMapOf("height" to 150, "justifyContent" to "center", "alignItems" to "center")), "scroll-view-item_H" to padStyleMapOf(utsMapOf("width" to "100%", "height" to 150, "justifyContent" to "center", "alignItems" to "center")), "text" to padStyleMapOf(utsMapOf("fontSize" to 18, "color" to "#ffffff")), "button" to padStyleMapOf(utsMapOf("marginTop" to 15)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
