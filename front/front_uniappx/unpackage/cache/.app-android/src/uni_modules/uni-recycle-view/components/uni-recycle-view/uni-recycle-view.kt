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
import io.dcloud.uniapp.extapi.getWindowInfo as uni_getWindowInfo
open class GenUniModulesUniRecycleViewComponentsUniRecycleViewUniRecycleView : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var itemHeight: Number by `$props`
    open var list: UTSArray<Any> by `$props`
    open var type: String by `$props`
    open var direction: String by `$props`
    open var associativeContainer: String by `$props`
    open var enableBackToTop: Boolean by `$props`
    open var bounces: Boolean by `$props`
    open var upperThreshold: Number by `$props`
    open var lowerThreshold: Number by `$props`
    open var scrollTop: Number by `$props`
    open var scrollLeft: Number by `$props`
    open var scrollIntoView: String by `$props`
    open var scrollWithAnimation: Boolean by `$props`
    open var refresherEnabled: Boolean by `$props`
    open var refresherThreshold: Number by `$props`
    open var refresherMaxDragDistance: Number? by `$props`
    open var refresherDefaultStyle: String by `$props`
    open var refresherBackground: String by `$props`
    open var refresherTriggered: Boolean by `$props`
    open var showScrollbar: Boolean by `$props`
    open var customNestedScroll: Boolean by `$props`
    open var nestedScrollChild: String by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenUniModulesUniRecycleViewComponentsUniRecycleViewUniRecycleView) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenUniModulesUniRecycleViewComponentsUniRecycleViewUniRecycleView
            val _cache = __ins.renderCache
            val props = __props
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val windowInfo = uni_getWindowInfo()
            val windowHeight = if (windowInfo.windowHeight == 0) {
                windowInfo.screenHeight
            } else {
                windowInfo.windowHeight
            }
            val cachedCount: Number = 10
            val renderItemCount = computed(fun(): Number {
                return Math.min(props.list.length, Math.ceil(windowHeight / props.itemHeight) + 1 + cachedCount)
            }
            )
            val placeholderHeight = computed(fun(): Number {
                return props.list.length * props.itemHeight
            }
            )
            val scrollTop = ref(0)
            val startIndex = computed(fun(): Number {
                return Math.min(Math.max(Math.floor(scrollTop.value / props.itemHeight) - Math.floor(cachedCount / 2), 0), props.list.length - renderItemCount.value)
            }
            )
            val items = computed<UTSArray<Any>>(fun(): UTSArray<Any> {
                return props.list.slice(startIndex.value, Math.min(props.list.length, startIndex.value + renderItemCount.value))
            }
            )
            val layoutItems = ref(utsArrayOf<LayoutItem>())
            watch(utsArrayOf(
                items,
                startIndex
            ), fun(){
                if (layoutItems.value.length !== items.value.length) {
                    layoutItems.value.splice(0, layoutItems.value.length)
                    items.value.forEach(fun(item, index){
                        layoutItems.value.push(LayoutItem(id = index, offset = (startIndex.value + index) * props.itemHeight, item = item))
                    })
                } else {
                    val newItems: UTSArray<ItemAndIndex> = utsArrayOf()
                    val aliveItems: UTSArray<ItemAndIndex> = utsArrayOf()
                    run {
                        var i: Number = 0
                        while(i < items.value.length){
                            val item = items.value[i]
                            val layoutItemIndex = layoutItems.value.findIndex(fun(layoutItem): Boolean {
                                return item == layoutItem.item
                            }
                            )
                            if (layoutItemIndex > -1) {
                                aliveItems.push(ItemAndIndex(item = item, index = i))
                            } else {
                                newItems.push(ItemAndIndex(item = item, index = i))
                            }
                            i++
                        }
                    }
                    run {
                        var i: Number = 0
                        while(i < layoutItems.value.length){
                            val layoutItem = layoutItems.value[i]
                            val aliveItemAndIndex = aliveItems.find(fun(aliveItem): Boolean {
                                return aliveItem.item == layoutItem.item
                            }
                            )
                            if (aliveItemAndIndex != null) {
                                layoutItem.offset = (startIndex.value + aliveItemAndIndex!!.index) * props.itemHeight
                            } else {
                                val newItemAndIndex = newItems.pop()!!
                                layoutItem.item = newItemAndIndex.item
                                layoutItem.offset = (startIndex.value + newItemAndIndex.index) * props.itemHeight
                            }
                            i++
                        }
                    }
                }
                layoutItems.value.sort(fun(a, b): Number {
                    return items.value.indexOf(a.item) - items.value.indexOf(b.item)
                }
                )
            }
            , WatchOptions(immediate = true))
            fun gen_onScroll_fn(e: UniScrollEvent) {
                val _scrollTop = e.detail.scrollTop
                scrollTop.value = if (_scrollTop < 0) {
                    0
                } else {
                    _scrollTop
                }
                emit("scroll", e)
            }
            val onScroll = ::gen_onScroll_fn
            fun gen_onScrollToLower_fn(e: UniScrollToLowerEvent) {
                emit("scrolltolower", e)
            }
            val onScrollToLower = ::gen_onScrollToLower_fn
            fun gen_onScrollToUpper_fn(e: UniScrollToUpperEvent) {
                emit("scrolltoupper", e)
            }
            val onScrollToUpper = ::gen_onScrollToUpper_fn
            fun gen_onScrollEnd_fn(e: UniScrollEvent) {
                emit("scrollend", e)
            }
            val onScrollEnd = ::gen_onScrollEnd_fn
            fun gen_onRefresherAbort_fn(e: UniRefresherEvent) {
                emit("refresherabort", e)
            }
            val onRefresherAbort = ::gen_onRefresherAbort_fn
            fun gen_onRefresherPulling_fn(e: UniRefresherEvent) {
                emit("refresherpulling", e)
            }
            val onRefresherPulling = ::gen_onRefresherPulling_fn
            fun gen_onRefresherRefresh_fn(e: UniRefresherEvent) {
                emit("refresherrefresh", e)
            }
            val onRefresherRefresh = ::gen_onRefresherRefresh_fn
            fun gen_onRefresherRestore_fn(e: UniRefresherEvent) {
                emit("refresherrestore", e)
            }
            val onRefresherRestore = ::gen_onRefresherRestore_fn
            return fun(): Any? {
                return createElementVNode("scroll-view", utsMapOf("scroll-y" to "true", "style" to normalizeStyle(utsMapOf("flex" to "1")), "type" to props.type, "direction" to props.direction, "associative-container" to props.associativeContainer, "enable-back-to-top" to props.enableBackToTop, "bounces" to props.bounces, "upper-threshold" to props.upperThreshold, "lower-threshold" to props.lowerThreshold, "scroll-top" to props.scrollTop, "scroll-left" to props.scrollLeft, "scroll-into-view" to props.scrollIntoView, "scroll-with-animation" to props.scrollWithAnimation, "refresher-enabled" to props.refresherEnabled, "refresher-threshold" to props.refresherThreshold, "refresher-max-drag-distance" to props.refresherMaxDragDistance, "refresher-default-style" to props.refresherDefaultStyle, "refresher-background" to props.refresherBackground, "refresher-triggered" to props.refresherTriggered, "show-scrollbar" to props.showScrollbar, "custom-nested-scroll" to props.customNestedScroll, "nested-scroll-child" to props.nestedScrollChild, "onScroll" to onScroll, "onScrollend" to onScrollEnd, "onScrolltolower" to onScrollToLower, "onScrolltoupper" to onScrollToUpper, "onRefresherabort" to onRefresherAbort, "onRefresherpulling" to onRefresherPulling, "onRefresherrefresh" to onRefresherRefresh, "onRefresherrestore" to onRefresherRestore), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "placeholder", "style" to normalizeStyle(utsMapOf("height" to (unref(placeholderHeight) + "px")))), utsArrayOf(
                        renderSlot(_ctx.`$slots`, "default", utsMapOf("layoutItems" to unref(layoutItems), "items" to unref(items)))
                    ), 4),
                    renderSlot(_ctx.`$slots`, "load-more")
                ), 44, utsArrayOf(
                    "type",
                    "direction",
                    "associative-container",
                    "enable-back-to-top",
                    "bounces",
                    "upper-threshold",
                    "lower-threshold",
                    "scroll-top",
                    "scroll-left",
                    "scroll-into-view",
                    "scroll-with-animation",
                    "refresher-enabled",
                    "refresher-threshold",
                    "refresher-max-drag-distance",
                    "refresher-default-style",
                    "refresher-background",
                    "refresher-triggered",
                    "show-scrollbar",
                    "custom-nested-scroll",
                    "nested-scroll-child"
                ))
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf())
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf("scroll" to null, "scrollend" to null, "scrolltolower" to null, "scrolltoupper" to null, "refresherabort" to null, "refresherpulling" to null, "refresherrefresh" to null, "refresherrestore" to null)
        var props = normalizePropsOptions(utsMapOf("itemHeight" to utsMapOf("type" to "Number", "default" to 40, "required" to true), "list" to utsMapOf("type" to "Array", "default" to utsArrayOf<Any>()), "type" to utsMapOf("type" to "String", "default" to ""), "direction" to utsMapOf("type" to "String", "default" to "vertical"), "associativeContainer" to utsMapOf("type" to "String", "default" to ""), "enableBackToTop" to utsMapOf("type" to "Boolean", "default" to false), "bounces" to utsMapOf("type" to "Boolean", "default" to true), "upperThreshold" to utsMapOf("type" to "Number", "default" to 50), "lowerThreshold" to utsMapOf("type" to "Number", "default" to 50), "scrollTop" to utsMapOf("type" to "Number", "default" to 0), "scrollLeft" to utsMapOf("type" to "Number", "default" to 0), "scrollIntoView" to utsMapOf("type" to "String", "default" to ""), "scrollWithAnimation" to utsMapOf("type" to "Boolean", "default" to false), "refresherEnabled" to utsMapOf("type" to "Boolean", "default" to false), "refresherThreshold" to utsMapOf("type" to "Number", "default" to 45), "refresherMaxDragDistance" to utsMapOf("type" to "Number"), "refresherDefaultStyle" to utsMapOf("type" to "String", "default" to "black"), "refresherBackground" to utsMapOf("type" to "String", "default" to "transparent"), "refresherTriggered" to utsMapOf("type" to "Boolean", "default" to false), "showScrollbar" to utsMapOf("type" to "Boolean", "default" to true), "customNestedScroll" to utsMapOf("type" to "Boolean", "default" to false), "nestedScrollChild" to utsMapOf("type" to "String", "default" to "")))
        var propsNeedCastKeys = utsArrayOf(
            "itemHeight",
            "list",
            "type",
            "direction",
            "associativeContainer",
            "enableBackToTop",
            "bounces",
            "upperThreshold",
            "lowerThreshold",
            "scrollTop",
            "scrollLeft",
            "scrollIntoView",
            "scrollWithAnimation",
            "refresherEnabled",
            "refresherThreshold",
            "refresherDefaultStyle",
            "refresherBackground",
            "refresherTriggered",
            "showScrollbar",
            "customNestedScroll",
            "nestedScrollChild"
        )
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
