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
open class GenPagesCSSFunctionFunction : BasePage {
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
            this.safeareaInsetTop = this.`$page`.safeAreaInsets.top
            this.safeareaInsetLeft = this.`$page`.safeAreaInsets.left
            this.safeareaInsetRight = this.`$page`.safeAreaInsets.right
            this.safeareaInsetBottom = this.`$page`.safeAreaInsets.bottom
        }
        , __ins)
        onResize(fun(_: OnResizeOptions) {
            this.safeareaInsetTop = this.`$page`.safeAreaInsets.top
            this.safeareaInsetLeft = this.`$page`.safeAreaInsets.left
            this.safeareaInsetRight = this.`$page`.safeAreaInsets.right
            this.safeareaInsetBottom = this.`$page`.safeAreaInsets.bottom
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("view", utsMapOf("class" to "padding-safe-area-inset"), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "text-background"), "我在状态栏下边"),
            createElementVNode("view", utsMapOf("class" to "content"), utsArrayOf(
                createElementVNode("text", null, "此页面使用env()函数适配安全区域，仅在app平台生效。"),
                createElementVNode("text", null, "safe-area-inset-top: " + toDisplayString(_ctx.safeareaInsetTop) + "px", 1),
                createElementVNode("text", null, "safe-area-inset-left: " + toDisplayString(_ctx.safeareaInsetLeft) + "px", 1),
                createElementVNode("text", null, "safe-area-inset-right: " + toDisplayString(_ctx.safeareaInsetRight) + "px", 1),
                createElementVNode("text", null, "safe-area-inset-bottom: " + toDisplayString(_ctx.safeareaInsetBottom) + "px", 1)
            )),
            createElementVNode("view", utsMapOf("class" to "text-background"), "我在导航栏上边")
        ))
    }
    open var safeareaInsetTop: Number by `$data`
    open var safeareaInsetLeft: Number by `$data`
    open var safeareaInsetRight: Number by `$data`
    open var safeareaInsetBottom: Number by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("safeareaInsetTop" to 0, "safeareaInsetLeft" to 0, "safeareaInsetRight" to 0, "safeareaInsetBottom" to 0)
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
                return utsMapOf("text-background" to padStyleMapOf(utsMapOf("backgroundColor" to "#FF0000")), "padding-safe-area-inset" to padStyleMapOf(utsMapOf("flex" to 1, "justifyContent" to "space-between", "paddingTop" to "env(safe-area-inset-top,0px)", "paddingLeft" to "env(safe-area-inset-left,0px)", "paddingRight" to "env(safe-area-inset-right,0px)", "paddingBottom" to "env(safe-area-inset-bottom,0px)")), "content" to padStyleMapOf(utsMapOf("paddingTop" to 20, "paddingRight" to 20, "paddingBottom" to 20, "paddingLeft" to 20)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
