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
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesComponentGlobalEventsGlobalEventsTransform : BasePage {
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
        return createElementVNode(Fragment, null, utsArrayOf(
            createElementVNode("view", null, utsArrayOf(
                createElementVNode("view", utsMapOf("onClick" to _ctx.onclick, "style" to normalizeStyle(utsMapOf("transform" to "rotate(180deg)", "background-color" to "brown", "width" to "100px", "height" to "100px", "margin" to "8px auto")), "onLongpress" to _ctx.onLongPress, "id" to "event-transform"), null, 44, utsArrayOf(
                    "onClick",
                    "onLongpress"
                ))
            )),
            createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "0px auto"))), "点击上面view触发点击事件", 4)
        ), 64)
    }
    open var clickTriger: Boolean by `$data`
    open var longClickTriger: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("clickTriger" to false, "longClickTriger" to false)
    }
    open var onclick = ::gen_onclick_fn
    open fun gen_onclick_fn(event: UniPointerEvent) {
        this.clickTriger = true
        uni_showToast(ShowToastOptions(title = "触发了点击事件"))
    }
    open var onLongPress = ::gen_onLongPress_fn
    open fun gen_onLongPress_fn(event: UniTouchEvent) {
        this.longClickTriger = true
        uni_showToast(ShowToastOptions(title = "触发了长按事件"))
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(), utsArrayOf(
                GenApp.styles
            ))
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
