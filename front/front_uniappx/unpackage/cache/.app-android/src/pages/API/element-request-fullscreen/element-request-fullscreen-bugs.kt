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
open class GenPagesAPIElementRequestFullscreenElementRequestFullscreenBugs : BasePage {
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
            this.fullscreenElement = uni_getElementById("fullscreen") as UniElement
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("view", null, utsArrayOf(
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("padding" to "8px"))), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "position-error", "id" to "fullscreen", "onClick" to _ctx.requestfullscreen), utsArrayOf(
                    createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("color" to "white"))), "测试position：fixed在安卓上的bug", 4)
                ), 8, utsArrayOf(
                    "onClick"
                ))
            ), 4)
        ))
    }
    open var fullscreenElement: UniElement? by `$data`
    open var isFullscreen: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("fullscreenElement" to null as UniElement?, "isFullscreen" to false)
    }
    open var getCurrentPage = ::gen_getCurrentPage_fn
    open fun gen_getCurrentPage_fn(): UniPage {
        val pages = getCurrentPages()
        return pages[pages.length - 1]
    }
    open var requestfullscreen = ::gen_requestfullscreen_fn
    open fun gen_requestfullscreen_fn() {
        if (this.isFullscreen) {
            val page = this.getCurrentPage()
            page.exitFullscreen(ExitFullscreenOptions(success = fun(){}, fail = fun(_error){}, complete = fun(_result){}))
        } else {
            this.fullscreenElement?.requestFullscreen(RequestFullscreenOptions(success = fun(){}, fail = fun(_error){}, complete = fun(_result){}))
        }
        this.isFullscreen = !this.isFullscreen
    }
    open var exitfullscreen = ::gen_exitfullscreen_fn
    open fun gen_exitfullscreen_fn() {}
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
                return utsMapOf("position-error" to padStyleMapOf(utsMapOf("width" to 200, "height" to 200, "position" to "fixed", "backgroundColor" to "#A52A2A")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
