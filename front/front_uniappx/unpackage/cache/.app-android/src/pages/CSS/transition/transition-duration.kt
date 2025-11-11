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
open class GenPagesCSSTransitionTransitionDuration : BasePage {
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
        return createElementVNode("view", null, utsArrayOf(
            createElementVNode("view", utsMapOf("class" to normalizeClass(_ctx.`class`)), null, 2),
            createElementVNode("button", utsMapOf("onClick" to _ctx.start), "start", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("button", utsMapOf("onClick" to _ctx.reset), "reset", 8, utsArrayOf(
                "onClick"
            ))
        ))
    }
    open var `class`: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("class" to "box")
    }
    open var start = ::gen_start_fn
    open fun gen_start_fn() {
        this.`class` = "box ani"
    }
    open var reset = ::gen_reset_fn
    open fun gen_reset_fn() {
        this.`class` = "box"
    }
    open var jest_start = ::gen_jest_start_fn
    open fun gen_jest_start_fn() {
        this.start()
    }
    open var jest_reset = ::gen_jest_reset_fn
    open fun gen_jest_reset_fn() {
        this.reset()
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
                return utsMapOf("box" to padStyleMapOf(utsMapOf("width" to 100, "height" to 100, "backgroundColor" to "#0000FF")), "ani" to padStyleMapOf(utsMapOf("transitionDuration" to "5s", "width" to 200)), "@TRANSITION" to utsMapOf("ani" to utsMapOf("duration" to "5s")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
