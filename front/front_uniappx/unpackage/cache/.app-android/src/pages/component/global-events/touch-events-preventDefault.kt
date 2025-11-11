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
open class GenPagesComponentGlobalEventsTouchEventsPreventDefault : BasePage {
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
    open var scrollTop: UTSJSONObject
        get() {
            return unref(this.`$exposed`["scrollTop"]) as UTSJSONObject
        }
        set(value) {
            setRefValue(this.`$exposed`, "scrollTop", value)
        }
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesComponentGlobalEventsTouchEventsPreventDefault, _arg1: SetupContext) -> Any? = fun(__props, ref1): Any? {
            var __expose = ref1.expose
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesComponentGlobalEventsTouchEventsPreventDefault
            val _cache = __ins.renderCache
            val scrollTop = reactive(object : UTSJSONObject() {
                var value: Number = 0
            })
            __expose(utsMapOf("scrollTop" to scrollTop))
            fun gen_getDirection_fn(x: Number, y: Number): String {
                if (x > y && x > 10) {
                    return "horizontal"
                }
                if (y > x && y > 10) {
                    return "vertical"
                }
                return ""
            }
            val getDirection = ::gen_getDirection_fn
            val startX = ref(0)
            val startY = ref(0)
            val direction = ref("")
            fun gen_touchstart_fn(e: UniTouchEvent) {
                direction.value = ""
                startX.value = e.touches[0].clientX
                startY.value = e.touches[0].clientY
            }
            val touchstart = ::gen_touchstart_fn
            fun gen_touchend_fn(e: UniTouchEvent) {
                console.log(">>>>> touchend", " at pages/component/global-events/touch-events-preventDefault.uvue:44")
                direction.value = ""
            }
            val touchend = ::gen_touchend_fn
            fun gen_touchmove_fn(e: UniTouchEvent) {
                val deltaX = e.touches[0].clientX - startX.value
                val deltaY = e.touches[0].clientY - startY.value
                if (direction.value == "") {
                    direction.value = getDirection(Math.abs(deltaX), Math.abs(deltaY))
                }
                if (direction.value != "horizontal") {
                    return
                }
                if (e.cancelable) {
                    e.preventDefault()
                }
            }
            val touchmove = ::gen_touchmove_fn
            fun gen_onScroll_fn(e: UniScrollEvent) {
                console.log(">>>>> onScroll", e.detail.scrollTop, " at pages/component/global-events/touch-events-preventDefault.uvue:68")
                scrollTop["value"] = e.detail.scrollTop
            }
            val onScroll = ::gen_onScroll_fn
            return fun(): Any? {
                val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
                return createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                    createVNode(_component_page_head, utsMapOf("title" to "横向滑动时阻止默认事件，即阻止页面滚动")),
                    createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt", "style" to normalizeStyle(utsMapOf("height" to "40px", "align-items" to "center"))), utsArrayOf(
                        createElementVNode("text", null, "手势方向：" + toDisplayString(unref(direction)), 1)
                    ), 4),
                    createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1")), "onScroll" to onScroll), utsArrayOf(
                        createElementVNode(Fragment, null, RenderHelpers.renderList(120, fun(item, __key, __index, _cached): Any {
                            return createElementVNode("view", utsMapOf("key" to item, "class" to "box", "onTouchstart" to touchstart, "onTouchmove" to touchmove, "onTouchend" to touchend), toDisplayString(item), 33)
                        }
                        ), 64)
                    ), 36)
                ), 4)
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ), utsArrayOf(
                GenApp.styles
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("box" to padStyleMapOf(utsMapOf("borderBottomWidth" to 1, "borderBottomStyle" to "solid", "borderBottomColor" to "#c6d1c3", "paddingTop" to 16, "paddingRight" to 10, "paddingBottom" to 16, "paddingLeft" to 10)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
