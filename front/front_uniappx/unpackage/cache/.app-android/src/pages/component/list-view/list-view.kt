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
open class GenPagesComponentListViewListView : BasePage {
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
                while(i < 10){
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
        val _component_boolean_data = resolveEasyComponent("boolean-data", GenComponentsBooleanDataBooleanDataClass)
        val _component_input_data = resolveEasyComponent("input-data", GenComponentsInputDataInputDataClass)
        val _component_enum_data = resolveEasyComponent("enum-data", GenComponentsEnumDataEnumDataClass)
        val _component_navigator = resolveComponent("navigator")
        return createElementVNode(Fragment, null, utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "main"), utsArrayOf(
                createElementVNode("list-view", utsMapOf("direction" to _ctx.scroll_direction, "bounces" to _ctx.bounces_boolean, "upper-threshold" to _ctx.upper_threshold_input, "lower-threshold" to _ctx.lower_threshold_input, "scroll-top" to _ctx.scroll_top_input, "scroll-left" to _ctx.scroll_left_input, "show-scrollbar" to _ctx.show_scrollbar_boolean, "scroll-into-view" to _ctx.scrollIntoView, "scroll-with-animation" to _ctx.scroll_with_animation_boolean, "refresher-enabled" to _ctx.refresher_enabled_boolean, "refresher-background" to _ctx.refresher_background_input, "refresher-triggered" to _ctx.refresher_triggered_boolean, "refresher-default-style" to _ctx.refresher_default_style_input, "onClick" to utsArrayOf(
                    _ctx.list_view_click,
                    _ctx.list_view_tap
                ), "onTouchstart" to _ctx.list_view_touchstart, "onTouchmove" to _ctx.list_view_touchmove, "onTouchcancel" to _ctx.list_view_touchcancel, "onTouchend" to _ctx.list_view_touchend, "onLongpress" to _ctx.list_view_longpress, "onRefresherpulling" to _ctx.list_view_refresherpulling, "onRefresherrefresh" to _ctx.list_view_refresherrefresh, "onRefresherrestore" to _ctx.list_view_refresherrestore, "onRefresherabort" to _ctx.list_view_refresherabort, "onScrolltoupper" to _ctx.list_view_scrolltoupper, "ref" to "listview", "onScrolltolower" to _ctx.list_view_scrolltolower, "onScroll" to _ctx.list_view_scroll, "onScrollend" to _ctx.list_view_scrollend, "style" to normalizeStyle(utsMapOf("width" to "100%")), "id" to "listview"), utsArrayOf(
                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.scrollData, fun(key, __key, __index, _cached): Any {
                        return createElementVNode("list-item", utsMapOf("key" to key, "id" to key, "onClick" to utsArrayOf(
                            _ctx.list_item_click,
                            _ctx.list_item_tap
                        ), "onTouchstart" to _ctx.list_item_touchstart, "onTouchmove" to _ctx.list_item_touchmove, "onTouchcancel" to _ctx.list_item_touchcancel, "onTouchend" to _ctx.list_item_touchend, "onLongpress" to _ctx.list_item_longpress, "class" to "list-item"), utsArrayOf(
                            createElementVNode("text", null, toDisplayString(key), 1)
                        ), 40, utsArrayOf(
                            "id",
                            "onClick",
                            "onTouchstart",
                            "onTouchmove",
                            "onTouchcancel",
                            "onTouchend",
                            "onLongpress"
                        ))
                    }
                    ), 128),
                    createElementVNode("list-item", utsMapOf("slot" to "refresher", "class" to "refresh-box"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "tip-text"), toDisplayString(_ctx.text[_ctx.state]), 1)
                    ))
                ), 44, utsArrayOf(
                    "direction",
                    "bounces",
                    "upper-threshold",
                    "lower-threshold",
                    "scroll-top",
                    "scroll-left",
                    "show-scrollbar",
                    "scroll-into-view",
                    "scroll-with-animation",
                    "refresher-enabled",
                    "refresher-background",
                    "refresher-triggered",
                    "refresher-default-style",
                    "onClick",
                    "onTouchstart",
                    "onTouchmove",
                    "onTouchcancel",
                    "onTouchend",
                    "onLongpress",
                    "onRefresherpulling",
                    "onRefresherrefresh",
                    "onRefresherrestore",
                    "onRefresherabort",
                    "onScrolltoupper",
                    "onScrolltolower",
                    "onScroll",
                    "onScrollend"
                ))
            )),
            createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1")), "direction" to "vertical"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "content"), utsArrayOf(
                    createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "设置当前下拉刷新状态，true 表示下拉刷新已经被触发，false 表示下拉刷新未被触发", "onChange" to _ctx.change_refresher_triggered_boolean), null, 8, utsArrayOf(
                        "onChange"
                    )),
                    createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "开启下拉刷新", "onChange" to _ctx.change_refresher_enabled_boolean), null, 8, utsArrayOf(
                        "onChange"
                    )),
                    createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "开启自定义样式", "onChange" to _ctx.change_refresher_style_boolean), null, 8, utsArrayOf(
                        "onChange"
                    )),
                    createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "是否在设置滚动条位置时使用滚动动画，设置false没有滚动动画", "onChange" to _ctx.change_scroll_with_animation_boolean), null, 8, utsArrayOf(
                        "onChange"
                    )),
                    createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "控制是否出现滚动条", "onChange" to _ctx.change_show_scrollbar_boolean), null, 8, utsArrayOf(
                        "onChange"
                    )),
                    createVNode(_component_boolean_data, utsMapOf("defaultValue" to true, "title" to "控制是否回弹效果", "onChange" to _ctx.change_bounces_boolean), null, 8, utsArrayOf(
                        "onChange"
                    )),
                    createVNode(_component_boolean_data, utsMapOf("defaultValue" to true, "title" to "允许纵向滚动", "onChange" to _ctx.change_scroll_y_boolean), null, 8, utsArrayOf(
                        "onChange"
                    )),
                    createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "允许横向滚动", "onChange" to _ctx.change_scroll_x_boolean), null, 8, utsArrayOf(
                        "onChange"
                    )),
                    createVNode(_component_input_data, utsMapOf("defaultValue" to "50", "title" to "距顶部/左边多远时（单位px），触发 scrolltoupper 事件", "type" to "number", "onConfirm" to _ctx.confirm_upper_threshold_input), null, 8, utsArrayOf(
                        "onConfirm"
                    )),
                    createVNode(_component_input_data, utsMapOf("defaultValue" to "50", "title" to "距底部/右边多远时（单位px），触发 scrolltolower 事件", "type" to "number", "onConfirm" to _ctx.confirm_lower_threshold_input), null, 8, utsArrayOf(
                        "onConfirm"
                    )),
                    createVNode(_component_input_data, utsMapOf("defaultValue" to "0", "title" to "设置竖向滚动条位置", "type" to "number", "onConfirm" to _ctx.confirm_scroll_top_input), null, 8, utsArrayOf(
                        "onConfirm"
                    )),
                    createVNode(_component_input_data, utsMapOf("defaultValue" to "0", "title" to "设置横向滚动条位置", "type" to "number", "onConfirm" to _ctx.confirm_scroll_left_input), null, 8, utsArrayOf(
                        "onConfirm"
                    )),
                    createVNode(_component_input_data, utsMapOf("defaultValue" to "#FFF", "title" to "设置下拉刷新区域背景颜色", "type" to "text", "onConfirm" to _ctx.confirm_refresher_background_input), null, 8, utsArrayOf(
                        "onConfirm"
                    )),
                    createVNode(_component_enum_data, utsMapOf("items" to _ctx.size_enum, "title" to "通过id位置跳转", "onChange" to _ctx.item_change_size_enum), null, 8, utsArrayOf(
                        "items",
                        "onChange"
                    )),
                    createVNode(_component_navigator, utsMapOf("url" to "/pages/component/list-view/list-view-refresh"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "class" to "button"), " list-view 下拉刷新 ")
                        )
                    }
                    ), "_" to 1)),
                    createVNode(_component_navigator, utsMapOf("url" to "/pages/component/list-view/list-view-multiplex"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "class" to "button"), " list-view 对list-item复用测试 ")
                        )
                    }
                    ), "_" to 1)),
                    createVNode(_component_navigator, utsMapOf("url" to "/pages/component/list-view/list-view-multiplex-input"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "class" to "button"), " list-view 复用测试（item中嵌入input） ")
                        )
                    }
                    ), "_" to 1)),
                    createVNode(_component_navigator, utsMapOf("url" to "/pages/component/list-view/list-view-multiplex-video"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "class" to "button"), " list-view 复用测试（item中嵌入video） ")
                        )
                    }
                    ), "_" to 1)),
                    createVNode(_component_navigator, utsMapOf("url" to "/pages/component/list-view/list-view-children-in-slot"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "class" to "button"), " list-view 测试插槽中子组件增删 ")
                        )
                    }
                    ), "_" to 1)),
                    createVNode(_component_navigator, utsMapOf("url" to "/pages/component/list-view/list-view-children-if-show"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "class" to "button"), " list-item v-if v-show 组合增删 ")
                        )
                    }
                    ), "_" to 1)),
                    createVNode(_component_navigator, utsMapOf("url" to "/pages/component/list-view/list-view-long-press"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "class" to "button"), " list-item 中设置长按事件测试 ")
                        )
                    }
                    ), "_" to 1))
                ))
            ), 4)
        ), 64)
    }
    open var refresher_triggered_boolean: Boolean by `$data`
    open var refresher_enabled_boolean: Boolean by `$data`
    open var scroll_with_animation_boolean: Boolean by `$data`
    open var show_scrollbar_boolean: Boolean by `$data`
    open var bounces_boolean: Boolean by `$data`
    open var scroll_y_boolean: Boolean by `$data`
    open var scroll_x_boolean: Boolean by `$data`
    open var scroll_direction: String by `$data`
    open var upper_threshold_input: Number by `$data`
    open var lower_threshold_input: Number by `$data`
    open var scroll_top_input: Number by `$data`
    open var scroll_left_input: Number by `$data`
    open var refresher_background_input: String by `$data`
    open var scrollData: UTSArray<String> by `$data`
    open var size_enum: UTSArray<ItemType> by `$data`
    open var scrollIntoView: String by `$data`
    open var refresherrefresh: Boolean by `$data`
    open var refresher_default_style_input: String by `$data`
    open var text: UTSArray<String> by `$data`
    open var state: Number by `$data`
    open var reset: Boolean by `$data`
    open var isScrollTest: String by `$data`
    open var isScrolltolowerTest: String by `$data`
    open var isScrolltoupperTest: String by `$data`
    open var scrollDetailTest: UniScrollEventDetail? by `$data`
    open var scrollEndDetailTest: UniScrollEventDetail? by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("refresher_triggered_boolean" to false, "refresher_enabled_boolean" to false, "scroll_with_animation_boolean" to false, "show_scrollbar_boolean" to false, "bounces_boolean" to true, "scroll_y_boolean" to true, "scroll_x_boolean" to false, "scroll_direction" to "vertical", "upper_threshold_input" to 50, "lower_threshold_input" to 50, "scroll_top_input" to 0, "scroll_left_input" to 0, "refresher_background_input" to "#FFF", "scrollData" to utsArrayOf<String>(), "size_enum" to utsArrayOf<ItemType>(ItemType(value = 0, name = "item---0"), ItemType(value = 3, name = "item---3")), "scrollIntoView" to "", "refresherrefresh" to false, "refresher_default_style_input" to "black", "text" to utsArrayOf(
            "继续下拉执行刷新",
            "释放立即刷新",
            "刷新中",
            ""
        ), "state" to 3, "reset" to true, "isScrollTest" to "", "isScrolltolowerTest" to "", "isScrolltoupperTest" to "", "scrollDetailTest" to null as UniScrollEventDetail?, "scrollEndDetailTest" to null as UniScrollEventDetail?)
    }
    open var list_view_click = ::gen_list_view_click_fn
    open fun gen_list_view_click_fn() {
        console.log("组件被点击时触发", " at pages/component/list-view/list-view.uvue:49")
    }
    open var list_view_touchstart = ::gen_list_view_touchstart_fn
    open fun gen_list_view_touchstart_fn() {
        console.log("手指触摸动作开始", " at pages/component/list-view/list-view.uvue:50")
    }
    open var list_view_touchmove = ::gen_list_view_touchmove_fn
    open fun gen_list_view_touchmove_fn() {
        console.log("手指触摸后移动", " at pages/component/list-view/list-view.uvue:51")
    }
    open var list_view_touchcancel = ::gen_list_view_touchcancel_fn
    open fun gen_list_view_touchcancel_fn() {
        console.log("手指触摸动作被打断，如来电提醒，弹窗", " at pages/component/list-view/list-view.uvue:52")
    }
    open var list_view_touchend = ::gen_list_view_touchend_fn
    open fun gen_list_view_touchend_fn() {
        console.log("手指触摸动作结束", " at pages/component/list-view/list-view.uvue:53")
    }
    open var list_view_tap = ::gen_list_view_tap_fn
    open fun gen_list_view_tap_fn() {
        console.log("手指触摸后马上离开", " at pages/component/list-view/list-view.uvue:54")
    }
    open var list_view_longpress = ::gen_list_view_longpress_fn
    open fun gen_list_view_longpress_fn() {
        console.log("如果一个组件被绑定了 longpress 事件，那么当用户长按这个组件时，该事件将会被触发。", " at pages/component/list-view/list-view.uvue:55")
    }
    open var list_view_refresherpulling = ::gen_list_view_refresherpulling_fn
    open fun gen_list_view_refresherpulling_fn(e: RefresherEvent) {
        console.log("下拉刷新控件被下拉", " at pages/component/list-view/list-view.uvue:57")
        if (this.reset) {
            if (e.detail.dy > 45) {
                this.state = 1
            } else {
                this.state = 0
            }
        }
    }
    open var list_view_refresherrefresh = ::gen_list_view_refresherrefresh_fn
    open fun gen_list_view_refresherrefresh_fn() {
        console.log("下拉刷新被触发 ", " at pages/component/list-view/list-view.uvue:67")
        this.refresherrefresh = true
        this.refresher_triggered_boolean = true
        this.state = 2
        this.reset = false
        setTimeout(fun(){
            this.refresher_triggered_boolean = false
        }
        , 1500)
    }
    open var list_view_refresherrestore = ::gen_list_view_refresherrestore_fn
    open fun gen_list_view_refresherrestore_fn() {
        this.refresherrefresh = false
        this.state = 3
        this.reset = true
        console.log("下拉刷新被复位", " at pages/component/list-view/list-view.uvue:80")
    }
    open var list_view_refresherabort = ::gen_list_view_refresherabort_fn
    open fun gen_list_view_refresherabort_fn() {
        console.log("下拉刷新被中止", " at pages/component/list-view/list-view.uvue:82")
    }
    open var list_view_scrolltoupper = ::gen_list_view_scrolltoupper_fn
    open fun gen_list_view_scrolltoupper_fn(e: UniScrollToUpperEvent) {
        console.log("滚动到顶部/左边，会触发 scrolltoupper 事件  direction=" + e.detail.direction, " at pages/component/list-view/list-view.uvue:84")
        this.checkEventTest(ScrollEventTest1(type = e.type, target = e.target, currentTarget = e.currentTarget, direction = e.detail.direction), "scrolltoupper")
    }
    open var list_view_scrolltolower = ::gen_list_view_scrolltolower_fn
    open fun gen_list_view_scrolltolower_fn(e: UniScrollToLowerEvent) {
        console.log("滚动到底部/右边，会触发 scrolltolower 事件  direction=" + e.detail.direction, " at pages/component/list-view/list-view.uvue:93")
        this.checkEventTest(ScrollEventTest1(type = e.type, target = e.target, currentTarget = e.currentTarget, direction = e.detail.direction), "scrolltolower")
    }
    open var list_view_scroll = ::gen_list_view_scroll_fn
    open fun gen_list_view_scroll_fn(e: UniScrollEvent) {
        console.log("滚动时触发，event.detail = {scrollLeft, scrollTop, scrollHeight, scrollWidth, deltaX, deltaY}", " at pages/component/list-view/list-view.uvue:102")
        this.scrollDetailTest = e.detail
        this.checkEventTest(ScrollEventTest1(type = e.type, target = e.target, currentTarget = e.currentTarget), "scroll")
    }
    open var list_view_scrollend = ::gen_list_view_scrollend_fn
    open fun gen_list_view_scrollend_fn(e: UniScrollEvent) {
        console.log("滚动结束时触发", e.detail, " at pages/component/list-view/list-view.uvue:111")
        this.scrollEndDetailTest = e.detail
        this.checkEventTest(ScrollEventTest1(type = e.type, target = e.target, currentTarget = e.currentTarget), "scrollend")
    }
    open var list_item_click = ::gen_list_item_click_fn
    open fun gen_list_item_click_fn() {
        console.log("list-item组件被点击时触发", " at pages/component/list-view/list-view.uvue:119")
    }
    open var list_item_touchstart = ::gen_list_item_touchstart_fn
    open fun gen_list_item_touchstart_fn() {
        console.log("手指触摸list-item组件动作开始", " at pages/component/list-view/list-view.uvue:120")
    }
    open var list_item_touchmove = ::gen_list_item_touchmove_fn
    open fun gen_list_item_touchmove_fn() {
        console.log("手指触摸list-item组件后移动", " at pages/component/list-view/list-view.uvue:121")
    }
    open var list_item_touchcancel = ::gen_list_item_touchcancel_fn
    open fun gen_list_item_touchcancel_fn() {
        console.log("手指触摸list-item组件动作被打断，如来电提醒，弹窗", " at pages/component/list-view/list-view.uvue:122")
    }
    open var list_item_touchend = ::gen_list_item_touchend_fn
    open fun gen_list_item_touchend_fn() {
        console.log("手指触摸list-item组件动作结束", " at pages/component/list-view/list-view.uvue:123")
    }
    open var list_item_tap = ::gen_list_item_tap_fn
    open fun gen_list_item_tap_fn() {
        console.log("手指触摸list-item组件后马上离开", " at pages/component/list-view/list-view.uvue:124")
    }
    open var list_item_longpress = ::gen_list_item_longpress_fn
    open fun gen_list_item_longpress_fn() {
        console.log("list-item组件被绑定了 longpress 事件，那么当用户长按这个组件时，该事件将会被触发。", " at pages/component/list-view/list-view.uvue:125")
    }
    open var change_refresher_triggered_boolean = ::gen_change_refresher_triggered_boolean_fn
    open fun gen_change_refresher_triggered_boolean_fn(checked: Boolean) {
        this.refresher_triggered_boolean = checked
    }
    open var change_refresher_enabled_boolean = ::gen_change_refresher_enabled_boolean_fn
    open fun gen_change_refresher_enabled_boolean_fn(checked: Boolean) {
        this.refresher_enabled_boolean = checked
    }
    open var change_scroll_with_animation_boolean = ::gen_change_scroll_with_animation_boolean_fn
    open fun gen_change_scroll_with_animation_boolean_fn(checked: Boolean) {
        this.scroll_with_animation_boolean = checked
    }
    open var change_show_scrollbar_boolean = ::gen_change_show_scrollbar_boolean_fn
    open fun gen_change_show_scrollbar_boolean_fn(checked: Boolean) {
        this.show_scrollbar_boolean = checked
    }
    open var change_bounces_boolean = ::gen_change_bounces_boolean_fn
    open fun gen_change_bounces_boolean_fn(checked: Boolean) {
        this.bounces_boolean = checked
    }
    open var change_scroll_y_boolean = ::gen_change_scroll_y_boolean_fn
    open fun gen_change_scroll_y_boolean_fn(checked: Boolean) {
        this.scroll_y_boolean = checked
        this.change_scroll_direction()
    }
    open var change_scroll_x_boolean = ::gen_change_scroll_x_boolean_fn
    open fun gen_change_scroll_x_boolean_fn(checked: Boolean) {
        this.scroll_x_boolean = checked
        this.change_scroll_direction()
    }
    open var change_scroll_direction = ::gen_change_scroll_direction_fn
    open fun gen_change_scroll_direction_fn() {
        if (this.scroll_y_boolean && this.scroll_x_boolean || this.scroll_y_boolean) {
            this.scroll_direction = "vertical"
        } else if (!this.scroll_y_boolean && !this.scroll_x_boolean) {
            this.scroll_direction = "none"
        } else if (!this.scroll_y_boolean && this.scroll_x_boolean) {
            this.scroll_direction = "horizontal"
        }
    }
    open var confirm_upper_threshold_input = ::gen_confirm_upper_threshold_input_fn
    open fun gen_confirm_upper_threshold_input_fn(value: Number) {
        this.upper_threshold_input = value
    }
    open var confirm_lower_threshold_input = ::gen_confirm_lower_threshold_input_fn
    open fun gen_confirm_lower_threshold_input_fn(value: Number) {
        this.lower_threshold_input = value
    }
    open var confirm_scroll_top_input = ::gen_confirm_scroll_top_input_fn
    open fun gen_confirm_scroll_top_input_fn(value: Number) {
        this.scroll_top_input = value
    }
    open var confirm_scroll_left_input = ::gen_confirm_scroll_left_input_fn
    open fun gen_confirm_scroll_left_input_fn(value: Number) {
        this.scroll_left_input = value
    }
    open var confirm_refresher_background_input = ::gen_confirm_refresher_background_input_fn
    open fun gen_confirm_refresher_background_input_fn(value: String) {
        this.refresher_background_input = value
    }
    open var item_change_size_enum = ::gen_item_change_size_enum_fn
    open fun gen_item_change_size_enum_fn(index: Number) {
        this.scrollIntoView = "item---" + index
    }
    open var setScrollIntoView = ::gen_setScrollIntoView_fn
    open fun gen_setScrollIntoView_fn(id: String) {
        this.scrollIntoView = id
    }
    open var checkEventTest = ::gen_checkEventTest_fn
    open fun gen_checkEventTest_fn(e: ScrollEventTest1, eventName: String) {
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
    open var check_scroll_height = ::gen_check_scroll_height_fn
    open fun gen_check_scroll_height_fn(): Boolean {
        var listElement = this.`$refs`["listview"] as UniElement
        console.log("check_scroll_height--" + listElement.scrollHeight, " at pages/component/list-view/list-view.uvue:177")
        if (listElement.scrollHeight > 2000) {
            return true
        }
        return false
    }
    open var check_scroll_width = ::gen_check_scroll_width_fn
    open fun gen_check_scroll_width_fn(): Boolean {
        var listElement = this.`$refs`["listview"] as UniElement
        console.log("check_scroll_width" + listElement.scrollWidth, " at pages/component/list-view/list-view.uvue:186")
        if (listElement.scrollWidth > 2000) {
            return true
        }
        return false
    }
    open var change_refresher_style_boolean = ::gen_change_refresher_style_boolean_fn
    open fun gen_change_refresher_style_boolean_fn(checked: Boolean) {
        if (checked) {
            this.refresher_default_style_input = "none"
        } else {
            this.refresher_default_style_input = "black"
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
                return utsMapOf("main" to padStyleMapOf(utsMapOf("maxHeight" to 250, "paddingTop" to 5, "paddingRight" to 0, "paddingBottom" to 5, "paddingLeft" to 0, "borderBottomWidth" to 1, "borderBottomStyle" to "solid", "borderBottomColor" to "rgba(0,0,0,0.06)", "flexDirection" to "row", "justifyContent" to "center")), "list-item" to utsMapOf(".main " to utsMapOf("width" to "100%", "height" to 250, "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#666666", "borderRightColor" to "#666666", "borderBottomColor" to "#666666", "borderLeftColor" to "#666666", "backgroundColor" to "#66ccff", "alignItems" to "center", "justifyContent" to "center")), "tip-text" to padStyleMapOf(utsMapOf("color" to "#888888", "fontSize" to 12)), "refresh-box" to padStyleMapOf(utsMapOf("justifyContent" to "center", "alignItems" to "center", "flexDirection" to "row", "height" to 45, "width" to "100%")), "button" to padStyleMapOf(utsMapOf("marginTop" to 15)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
