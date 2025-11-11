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
open class GenPagesAPIAnimationFrameAnimationFrame : BasePage {
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
        onUnload(fun() {
            if (this.taskId > 0) {
                this.stopRequestAnimationFrame()
            }
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        return createElementVNode("view", utsMapOf("class" to "page"), utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                "title"
            )),
            createElementVNode("button", utsMapOf("onClick" to _ctx.startRequestAnimationFrame), "requestAnimationFrame", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("button", utsMapOf("onClick" to _ctx.stopRequestAnimationFrame), "cancelAnimationFrame", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("text", utsMapOf("class" to "frame-count"), "FPS: " + toDisplayString(_ctx.FPSString), 1),
            createElementVNode("text", utsMapOf("class" to "frame-count"), "FrameCount: " + toDisplayString(_ctx.testFrameCount), 1),
            createElementVNode("text", utsMapOf("class" to "tips"), "提示: 在当前测试例子中，每增加一次调用 requestAnimationFrame 帧率翻倍，cancelAnimationFrame 后恢复")
        ))
    }
    open var title: String by `$data`
    open var taskId: Number by `$data`
    open var FPSString: String by `$data`
    open var lastTime: Number by `$data`
    open var frameCount: Number by `$data`
    open var testFrameCount: Number by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "AnimationFrame", "taskId" to 0, "FPSString" to "- / -ms", "lastTime" to 0, "frameCount" to 0, "testFrameCount" to 0)
    }
    open var startRequestAnimationFrame = ::gen_startRequestAnimationFrame_fn
    open fun gen_startRequestAnimationFrame_fn() {
        this.taskId = requestAnimationFrame(fun(timestamp: Number){
            this.updateFPS(timestamp)
            this.testFrameCount++
            this.startRequestAnimationFrame()
        }
        )
    }
    open var stopRequestAnimationFrame = ::gen_stopRequestAnimationFrame_fn
    open fun gen_stopRequestAnimationFrame_fn() {
        cancelAnimationFrame(this.taskId)
        this.lastTime = 0
        this.frameCount = 0
        this.FPSString = "- / -ms"
    }
    open var updateFPS = ::gen_updateFPS_fn
    open fun gen_updateFPS_fn(timestamp: Number) {
        this.frameCount++
        if (timestamp - this.lastTime >= 1000) {
            val timeOfFrame = ((1000 as Number) / this.frameCount)
            this.FPSString = "" + this.frameCount + " / " + timeOfFrame.toFixed(3) + "ms"
            this.frameCount = 0
            this.lastTime = timestamp
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
                return utsMapOf("page" to padStyleMapOf(utsMapOf("paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15)), "frame-count" to padStyleMapOf(utsMapOf("marginTop" to 15)), "tips" to padStyleMapOf(utsMapOf("fontSize" to 12, "marginTop" to 30, "opacity" to 0.7)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
