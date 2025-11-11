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
import io.dcloud.uniapp.extapi.createCanvasContextAsync as uni_createCanvasContextAsync
import io.dcloud.uniapp.extapi.getWindowInfo as uni_getWindowInfo
open class GenPagesComponentCanvasCanvasDoodle : BasePage {
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
            uni_createCanvasContextAsync(CreateCanvasContextAsyncOptions(id = "tablet", success = fun(context: CanvasContext){
                console.log("success", " at pages/component/canvas/canvas/doodle.uvue:29")
                this.renderingContext = context.getContext("2d")!!
                val canvas = this.renderingContext!!.canvas
                val dpr = uni_getWindowInfo().pixelRatio
                canvas.width = canvas.offsetWidth * dpr
                canvas.height = canvas.offsetHeight * dpr
                this.renderingContext!!.scale(dpr, dpr)
                val elRect = canvas.getBoundingClientRect()
                this.offsetX = elRect.left
                this.offsetY = elRect.top
            }
            ))
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("view", null, utsArrayOf(
            createElementVNode("canvas", utsMapOf("class" to "drawing", "id" to "tablet", "onTouchstart" to _ctx.touchStart, "onTouchmove" to _ctx.touchMove, "onTouchend" to _ctx.touchEnd), null, 40, utsArrayOf(
                "onTouchstart",
                "onTouchmove",
                "onTouchend"
            )),
            createElementVNode("button", utsMapOf("onClick" to fun(){
                _ctx.doClear()
            }
            ), "清空", 8, utsArrayOf(
                "onClick"
            ))
        ))
    }
    open var lastPointX: Number by `$data`
    open var lastPointY: Number by `$data`
    open var offsetX: Number by `$data`
    open var offsetY: Number by `$data`
    open var renderingContext: CanvasRenderingContext2D? by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("lastPointX" to 0, "lastPointY" to 0, "offsetX" to 0, "offsetY" to 0, "renderingContext" to null as CanvasRenderingContext2D?)
    }
    open var touchStart = ::gen_touchStart_fn
    open fun gen_touchStart_fn(event: TouchEvent) {
        val position = this.getPosition(event)
        this.lastPointX = position.x
        this.lastPointY = position.y
    }
    open var touchMove = ::gen_touchMove_fn
    open fun gen_touchMove_fn(event: TouchEvent) {
        val position = this.getPosition(event)
        val x = position.x
        val y = position.y
        val ctx = this.renderingContext!!
        ctx.lineWidth = 5
        ctx.lineCap = "round"
        ctx.lineJoin = "round"
        ctx.beginPath()
        ctx.moveTo(this.lastPointX, this.lastPointY)
        ctx.lineTo(x, y)
        ctx.stroke()
        this.lastPointX = x
        this.lastPointY = y
    }
    open var touchEnd = ::gen_touchEnd_fn
    open fun gen_touchEnd_fn(_0: TouchEvent) {}
    open var doClear = ::gen_doClear_fn
    open fun gen_doClear_fn() {
        if (this.renderingContext != null) {
            this.renderingContext!!.clearRect(0, 0, this.renderingContext!!.canvas.width, this.renderingContext!!.canvas.height)
        }
    }
    open var getPosition = ::gen_getPosition_fn
    open fun gen_getPosition_fn(event: TouchEvent): Point {
        val touch = event.touches[0]
        return Point(x = touch.clientX - this.offsetX, y = touch.clientY - this.offsetY)
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
                return utsMapOf("drawing" to padStyleMapOf(utsMapOf("width" to "100%", "height" to 500, "backgroundColor" to "#D3D3D3", "marginBottom" to 15)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
