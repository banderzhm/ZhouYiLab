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
open class GenPagesCSSTextLineHeight : BasePage {
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
        return createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex-grow" to "1"))), utsArrayOf(
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("height" to "500px", "background-color" to "gray", "justify-content" to "center", "align-items" to "center"))), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("line-height" to "75px"))), "line-height: 75px", 4),
                createElementVNode("text", utsMapOf("class" to "line-height-3 common"), "line-height: 3"),
                createElementVNode("text", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("line-height" to "3em"))), "line-height: 3em", 4),
                createElementVNode("text", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("line-height" to "3"))), "line-height: 3\nline-height: 3\nline-height: 3", 4)
            ), 4)
        ), 4)
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
                return utsMapOf("common" to padStyleMapOf(utsMapOf("fontSize" to 20, "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#FF0000", "borderRightColor" to "#FF0000", "borderBottomColor" to "#FF0000", "borderLeftColor" to "#FF0000", "marginTop" to 10, "marginRight" to 0, "marginBottom" to 10, "marginLeft" to 0, "paddingTop" to 0, "paddingRight" to 10, "paddingBottom" to 0, "paddingLeft" to 10)), "line-height-3" to padStyleMapOf(utsMapOf("lineHeight" to 3)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
