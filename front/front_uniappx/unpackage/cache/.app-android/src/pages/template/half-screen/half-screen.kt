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
open class GenPagesTemplateHalfScreenHalfScreen : BasePage {
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
        onReady(fun() {
            this.halfNode = this.`$refs`["halfScreen"] as UniElement
            this.scrollNode = this.`$refs`["halfScroll"] as UniElement
            this.halfNode!!.getBoundingClientRectAsync()!!.then(fun(rect: DOMRect){
                this.halfHeight = rect.height
            }
            )
            (this.`$refs`["page"] as UniElement).getBoundingClientRectAsync()!!.then(fun(rect: DOMRect){
                this.totalHeight = rect.height
                this.halfNode?.style?.setProperty("top", this.totalHeight.toFixed(2) + "px")
            }
            )
        }
        , __ins)
        onResize(fun(_: OnResizeOptions) {
            this.halfNode?.getBoundingClientRectAsync()!!.then(fun(rect: DOMRect){
                this.halfHeight = rect.height
            }
            )
            this.totalHeight = uni_getWindowInfo().windowHeight
            this.halfNode?.style?.setProperty("top", this.totalHeight.toFixed(2) + "px")
            this.halfNode?.style?.setProperty("visibility", "hidden")
        }
        , __ins)
        onBackPress(fun(_: OnBackPressOptions): Boolean {
            if ("visible" == this.halfNode?.style?.getPropertyValue("visibility")) {
                this.switchHalfScreen(false)
                return true
            }
            return false
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("view", utsMapOf("ref" to "page", "class" to "page"), utsArrayOf(
            createElementVNode("text", utsMapOf("class" to "tip"), "半屏弹窗，演示了弹出层内scroll-view滚动到顶时由滚变拖。本效果是通过监听TouchEvent实现，当半屏窗口移动时禁用scroll-view的滚动，避免两者的冲突。"),
            createElementVNode("button", utsMapOf("class" to "bottomButton", "onClick" to fun(){
                _ctx.switchHalfScreen(true)
            }
            ), "打开弹窗", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("view", utsMapOf("ref" to "halfScreen", "class" to "halfScreen", "onTouchstart" to _ctx.onHalfTouchStart, "onTouchmove" to _ctx.onHalfTouchMove, "onTouchend" to _ctx.onHalfTouchEnd), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "halfTitle"), "半屏弹窗标题"),
                createElementVNode("scroll-view", utsMapOf("ref" to "halfScroll", "class" to "halfScroll", "bounces" to "true", "direction" to _ctx.scrollDirection), utsArrayOf(
                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.list, fun(item, index, __index, _cached): Any {
                        return createElementVNode("view", utsMapOf("key" to index, "class" to "item"), " half screen content-" + toDisplayString(item), 1)
                    }
                    ), 128)
                ), 8, utsArrayOf(
                    "direction"
                ))
            ), 40, utsArrayOf(
                "onTouchstart",
                "onTouchmove",
                "onTouchend"
            ))
        ), 512)
    }
    open var list: UTSArray<String> by `$data`
    open var totalHeight: Number by `$data`
    open var halfMove: Boolean by `$data`
    open var halfScreenY: Number by `$data`
    open var halfOffset: Number by `$data`
    open var halfHeight: Number by `$data`
    open var lastY: Number by `$data`
    open var lastY2: Number by `$data`
    open var bAnimation: Boolean by `$data`
    open var halfNode: UniElement? by `$data`
    open var scrollNode: UniElement? by `$data`
    open var scrollDirection: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("list" to utsArrayOf(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15"
        ), "totalHeight" to 0, "halfMove" to false, "halfScreenY" to 0, "halfOffset" to 0, "halfHeight" to 0, "lastY" to 0, "lastY2" to 0, "bAnimation" to false, "halfNode" to null as UniElement?, "scrollNode" to null as UniElement?, "scrollDirection" to "vertical")
    }
    open var onHalfTouchStart = ::gen_onHalfTouchStart_fn
    open fun gen_onHalfTouchStart_fn(_0: TouchEvent) {
        this.halfNode?.style?.setProperty("transition-duration", 0)
    }
    open var onHalfTouchMove = ::gen_onHalfTouchMove_fn
    open fun gen_onHalfTouchMove_fn(e: TouchEvent) {
        if (this.bAnimation) {
            return
        }
        var top: Number = this.scrollNode?.scrollTop ?: 0
        var p = e.touches[0]
        this.lastY2 = this.lastY
        this.lastY = p.screenY
        if (top <= 0.01 || this.halfMove) {
            if (this.halfScreenY == 0) {
                this.halfScreenY = p.screenY
            }
            var offset = p.screenY - this.halfScreenY
            if (offset > 0) {
                this.halfMove = true
                this.scrollNode?.setAttribute("direction", "none")
                this.halfNode?.style?.setProperty("transform", "translateY(" + offset.toFixed(2) + "px)")
                this.halfOffset = offset
            } else if (this.halfOffset > 0) {
                offset = this.halfScreenY - p.screenY
                if (offset > this.halfOffset) {
                    offset = 0
                    this.halfMove = false
                }
                this.scrollNode?.setAttribute("direction", "vertical")
                this.halfNode?.style?.setProperty("transform", "translateY(" + offset.toFixed(2) + "px)")
                this.halfOffset = offset
            }
        }
    }
    open var onHalfTouchEnd = ::gen_onHalfTouchEnd_fn
    open fun gen_onHalfTouchEnd_fn(_0: TouchEvent) {
        this.halfScreenY = 0
        if (this.bAnimation) {
            return
        }
        var top: Number = this.scrollNode?.scrollTop ?: 0
        var bHide = (this.halfHeight - this.halfOffset) < this.halfHeight / 4
        if (bHide) {
            bHide = this.lastY2 > 0 && this.lastY2 <= this.lastY
        } else if (top <= 0.01) {
            bHide = (this.lastY - this.lastY2) > 3
        }
        if (bHide) {
            this.switchHalfScreen(false)
        } else if (this.halfOffset > 0) {
            this.resumeHalfScreen()
        }
    }
    open var switchHalfScreen = ::gen_switchHalfScreen_fn
    open fun gen_switchHalfScreen_fn(show: Boolean) {
        if (show && ("visible" == this.halfNode?.style?.getPropertyValue("visibility"))) {
            console.log("quick click button!!!", " at pages/template/half-screen/half-screen.uvue:104")
            return
        }
        this.halfMove = false
        this.scrollNode?.setAttribute("direction", "vertical")
        this.halfScreenY = 0
        this.halfOffset = 0
        var top = this.totalHeight
        var time: Number = 300
        if (show) {
            top = this.totalHeight * 30 / 100
            this.halfNode?.style?.setProperty("visibility", "visible")
            this.halfNode?.style?.setProperty("transition-timing-function", "ease-in-out")
        } else {
            this.halfNode?.style?.setProperty("transition-timing-function", "linear")
            time *= (this.halfHeight / this.totalHeight)
        }
        this.halfNode?.style?.setProperty("transition-duration", time.toFixed(0) + "ms")
        this.halfNode?.style?.setProperty("transition-property", "top")
        this.halfNode?.style?.setProperty("top", top.toFixed(2) + "px")
        setTimeout(fun(){
            if (!show) {
                this.halfNode?.style?.setProperty("visibility", "hidden")
                this.halfNode?.style?.setProperty("transition-duration", 0)
                this.halfNode?.style?.setProperty("transform", "")
            }
            this.halfNode?.style?.setProperty("transition-property", "none")
            this.bAnimation = false
        }
        , time)
        this.bAnimation = true
    }
    open var resumeHalfScreen = ::gen_resumeHalfScreen_fn
    open fun gen_resumeHalfScreen_fn() {
        var time: Number = 300
        this.halfNode?.style?.setProperty("transition-duration", time.toFixed(0) + "ms")
        this.halfNode?.style?.setProperty("transition-timing-function", "ease-in-out")
        this.halfNode?.style?.setProperty("transition-property", "transform")
        this.halfNode?.style?.setProperty("transform", "translateY(0px)")
        this.halfMove = false
        this.scrollNode?.setAttribute("direction", "vertical")
        this.halfScreenY = 0
        this.halfOffset = 0
        setTimeout(fun(){
            this.bAnimation = false
            this.halfNode?.style?.setProperty("transition-property", "none")
        }
        , time)
        this.bAnimation = true
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
                return utsMapOf("page" to padStyleMapOf(utsMapOf("flex" to 1, "backgroundColor" to "#A9A9A9")), "tip" to padStyleMapOf(utsMapOf("marginTop" to 10, "marginRight" to 10, "marginBottom" to 10, "marginLeft" to 10)), "bottomButton" to padStyleMapOf(utsMapOf("position" to "absolute", "width" to "100%", "bottom" to 0, "paddingBottom" to "env(safe-area-inset-bottom,0px)")), "halfScreen" to padStyleMapOf(utsMapOf("position" to "absolute", "top" to "100%", "width" to "100%", "height" to "70%", "transitionTimingFunction" to "ease-in-out", "transitionProperty" to "top", "transitionDuration" to "0ms", "visibility" to "hidden")), "halfTitle" to padStyleMapOf(utsMapOf("alignItems" to "center", "justifyContent" to "center", "height" to 48, "backgroundColor" to "#F8F8FF", "borderTopLeftRadius" to 10, "borderTopRightRadius" to 10, "borderBottomRightRadius" to 0, "borderBottomLeftRadius" to 0)), "halfScroll" to padStyleMapOf(utsMapOf("backgroundColor" to "#FFFFFF", "flex" to 1)), "item" to padStyleMapOf(utsMapOf("height" to 100)), "@TRANSITION" to utsMapOf("halfScreen" to utsMapOf("timingFunction" to "ease-in-out", "property" to "top", "duration" to "0ms")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
