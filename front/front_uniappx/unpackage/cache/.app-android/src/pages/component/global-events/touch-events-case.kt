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
open class GenPagesComponentGlobalEventsTouchEventsCase : BasePage {
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
        val _component_boolean_data = resolveEasyComponent("boolean-data", GenComponentsBooleanDataBooleanDataClass)
        val _component_navigator = resolveComponent("navigator")
        return createElementVNode("view", null, utsArrayOf(
            createElementVNode("swiper", utsMapOf("ref" to "header", "class" to "h-300", "indicator-dots" to "true", "circular" to "true", "onChange" to _ctx.swiperChange, "onTouchstart" to _ctx.swiperTouchStart, "onTouchmove" to _ctx.swiperTouchMove, "onTouchend" to _ctx.swiperTouchEnd), utsArrayOf(
                createElementVNode(Fragment, null, RenderHelpers.renderList(3, fun(i, __key, __index, _cached): Any {
                    return createElementVNode("swiper-item", utsMapOf("item-id" to i, "onTouchstart" to _ctx.swiperItemTouchStart, "onTouchmove" to _ctx.swiperItemTouchMove, "onTouchend" to _ctx.swiperItemTouchEnd), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "h-300 header-tiem", "onTouchstart" to _ctx.viewTouchStart, "onTouchmove" to _ctx.viewTouchMove, "onTouchend" to _ctx.viewTouchEnd), utsArrayOf(
                            createElementVNode("text", null, toDisplayString(i), 1)
                        ), 40, utsArrayOf(
                            "onTouchstart",
                            "onTouchmove",
                            "onTouchend"
                        ))
                    ), 40, utsArrayOf(
                        "item-id",
                        "onTouchstart",
                        "onTouchmove",
                        "onTouchend"
                    ))
                }
                ), 64)
            ), 40, utsArrayOf(
                "onChange",
                "onTouchstart",
                "onTouchmove",
                "onTouchend"
            )),
            createElementVNode("view", utsMapOf("class" to "content"), utsArrayOf(
                createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "stopPropagation", "onChange" to _ctx.changeStopPropagation), null, 8, utsArrayOf(
                    "onChange"
                )),
                createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "preventDefault", "onChange" to _ctx.changePreventDefault), null, 8, utsArrayOf(
                    "onChange"
                )),
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("padding" to "10px"))), toDisplayString(_ctx.touchResult), 5),
                createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt", "style" to normalizeStyle(utsMapOf("bottom" to "20px"))), utsArrayOf(
                    createVNode(_component_navigator, utsMapOf("url" to "/pages/component/global-events/touch-events-preventDefault", "hover-class" to "none"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "default", "class" to "button"), " 测试 preventDefault ")
                        )
                    }
                    ), "_" to 1))
                ), 4)
            ))
        ))
    }
    open var swiperChangeEvent: Boolean by `$data`
    open var viewTouchEvent: Boolean by `$data`
    open var swiperItemTouchEvent: Boolean by `$data`
    open var swiperTouchEvent: Boolean by `$data`
    open var stopPropagation: Boolean by `$data`
    open var preventDefault: Boolean by `$data`
    open var touchResult: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("swiperChangeEvent" to false, "viewTouchEvent" to false, "swiperItemTouchEvent" to false, "swiperTouchEvent" to false, "stopPropagation" to false, "preventDefault" to false, "touchResult" to "")
    }
    open var changeStopPropagation = ::gen_changeStopPropagation_fn
    open fun gen_changeStopPropagation_fn(value: Boolean) {
        this.stopPropagation = value
    }
    open var changePreventDefault = ::gen_changePreventDefault_fn
    open fun gen_changePreventDefault_fn(value: Boolean) {
        this.preventDefault = value
    }
    open var swiperChange = ::gen_swiperChange_fn
    open fun gen_swiperChange_fn(e: UniSwiperChangeEvent) {
        console.log("swiperChange", e.detail.current, " at pages/component/global-events/touch-events-case.uvue:46")
        this.swiperChangeEvent = true
    }
    open var viewTouchStart = ::gen_viewTouchStart_fn
    open fun gen_viewTouchStart_fn(e: UniTouchEvent) {
        console.log("viewTouchStart", " at pages/component/global-events/touch-events-case.uvue:50")
        this.touchResult = "viewTouchStart"
        this.viewTouchEvent = true
        if (this.stopPropagation) {
            e.stopPropagation()
        }
        if (this.preventDefault) {
            e.preventDefault()
        }
    }
    open var viewTouchMove = ::gen_viewTouchMove_fn
    open fun gen_viewTouchMove_fn(e: UniTouchEvent) {
        console.log("viewTouchMove", " at pages/component/global-events/touch-events-case.uvue:57")
        this.touchResult = "viewTouchMove"
        this.viewTouchEvent = true
        if (this.stopPropagation) {
            e.stopPropagation()
        }
        if (this.preventDefault) {
            e.preventDefault()
        }
    }
    open var viewTouchEnd = ::gen_viewTouchEnd_fn
    open fun gen_viewTouchEnd_fn(e: UniTouchEvent) {
        console.log("viewTouchEnd", " at pages/component/global-events/touch-events-case.uvue:64")
        this.touchResult = "viewTouchEnd"
        if (this.stopPropagation) {
            e.stopPropagation()
        }
    }
    open var swiperItemTouchStart = ::gen_swiperItemTouchStart_fn
    open fun gen_swiperItemTouchStart_fn(e: UniTouchEvent) {
        console.log("swiperItemTouchStart", " at pages/component/global-events/touch-events-case.uvue:69")
        this.touchResult += " -> swiperItemTouchStart"
        this.swiperItemTouchEvent = true
    }
    open var swiperItemTouchMove = ::gen_swiperItemTouchMove_fn
    open fun gen_swiperItemTouchMove_fn(e: UniTouchEvent) {
        console.log("swiperItemTouchMove", " at pages/component/global-events/touch-events-case.uvue:74")
        this.touchResult += " -> swiperItemTouchMove"
    }
    open var swiperItemTouchEnd = ::gen_swiperItemTouchEnd_fn
    open fun gen_swiperItemTouchEnd_fn(e: UniTouchEvent) {
        console.log("swiperItemTouchEnd", " at pages/component/global-events/touch-events-case.uvue:78")
        this.touchResult += " -> swiperItemTouchEnd"
    }
    open var swiperTouchStart = ::gen_swiperTouchStart_fn
    open fun gen_swiperTouchStart_fn(e: UniTouchEvent) {
        console.log("swiperTouchStart", " at pages/component/global-events/touch-events-case.uvue:82")
        this.touchResult += " -> swiperTouchStart"
        this.swiperTouchEvent = true
    }
    open var swiperTouchMove = ::gen_swiperTouchMove_fn
    open fun gen_swiperTouchMove_fn(e: UniTouchEvent) {
        console.log("swiperTouchMove", " at pages/component/global-events/touch-events-case.uvue:87")
        this.touchResult += " -> swiperTouchMove"
    }
    open var swiperTouchEnd = ::gen_swiperTouchEnd_fn
    open fun gen_swiperTouchEnd_fn(e: UniTouchEvent) {
        console.log("swiperTouchEnd", " at pages/component/global-events/touch-events-case.uvue:91")
        this.touchResult += " -> swiperTouchEnd"
    }
    open var resetEvent = ::gen_resetEvent_fn
    open fun gen_resetEvent_fn() {
        this.swiperChangeEvent = false
        this.viewTouchEvent = false
        this.swiperItemTouchEvent = false
        this.swiperTouchEvent = false
        this.stopPropagation = true
        this.preventDefault = true
    }
    open var isPassTest1 = ::gen_isPassTest1_fn
    open fun gen_isPassTest1_fn(): Boolean {
        console.log("swiperChangeEvent:", this.swiperChangeEvent, " at pages/component/global-events/touch-events-case.uvue:103")
        return this.swiperChangeEvent == false
    }
    open var isPassTest2 = ::gen_isPassTest2_fn
    open fun gen_isPassTest2_fn(): Boolean {
        console.log("viewTouchEvent:", this.viewTouchEvent, " at pages/component/global-events/touch-events-case.uvue:107")
        console.log("swiperItemTouchEvent:", this.swiperItemTouchEvent, " at pages/component/global-events/touch-events-case.uvue:108")
        console.log("swiperTouchEvent:", this.swiperTouchEvent, " at pages/component/global-events/touch-events-case.uvue:109")
        return this.viewTouchEvent == true && this.swiperItemTouchEvent == true && this.swiperTouchEvent == true
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
                return utsMapOf("h-300" to padStyleMapOf(utsMapOf("height" to 300)), "header-tiem" to padStyleMapOf(utsMapOf("backgroundColor" to "#89ff8d", "alignItems" to "center", "justifyContent" to "center")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
