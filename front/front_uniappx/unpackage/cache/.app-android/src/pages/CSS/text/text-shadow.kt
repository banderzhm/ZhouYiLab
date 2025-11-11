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
open class GenPagesCSSTextTextShadow : BasePage {
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
        val _cache = this.`$`.renderCache
        return createElementVNode("view", utsMapOf("class" to "container"), utsArrayOf(
            createElementVNode("text", utsMapOf("class" to "text", "style" to normalizeStyle(utsMapOf("text-shadow" to "1px 1px 2px pink"))), "text-shadow: 1px 1px 2px pink", 4),
            createElementVNode("text", utsMapOf("class" to "text", "style" to normalizeStyle(utsMapOf("text-shadow" to "#fc0 1px 0 10px"))), "text-shadow: #fc0 1px 0 10px", 4),
            createElementVNode("text", utsMapOf("class" to "text", "style" to normalizeStyle(utsMapOf("text-shadow" to "5px 5px #558abb"))), "text-shadow: 5px 5px #558abb", 4),
            createElementVNode("text", utsMapOf("class" to "text", "style" to normalizeStyle(utsMapOf("text-shadow" to "red 2px 5px"))), "text-shadow: red 2px 5px", 4),
            createElementVNode("text", utsMapOf("class" to "text", "style" to normalizeStyle(utsMapOf("text-shadow" to "5px 10px"))), "text-shadow: 5px 10px", 4),
            createElementVNode("text", utsMapOf("class" to "text", "style" to normalizeStyle(utsMapOf("text-shadow" to "1px 2px 4px rgb(34, 202, 43)"))), "text-shadow: 1px 2px 4px rgb(34, 202, 43)", 4),
            createElementVNode("text", utsMapOf("class" to "text", "style" to normalizeStyle(utsMapOf("text-shadow" to "2px 4px rgba(202, 207, 17, 0.5)"))), "text-shadow: 2px 4px rgba(202, 207, 17, 0.5)", 4)
        ))
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
                return utsMapOf("container" to padStyleMapOf(utsMapOf("backgroundColor" to "#808080", "justifyContent" to "center")), "text" to padStyleMapOf(utsMapOf("width" to "100%", "height" to 40, "fontSize" to 20, "textAlign" to "center")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
