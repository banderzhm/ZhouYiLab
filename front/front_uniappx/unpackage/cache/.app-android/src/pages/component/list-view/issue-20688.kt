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
open class GenPagesComponentListViewIssue20688 : BasePage {
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
        return createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "100%", "flex" to "1"))), utsArrayOf(
            createElementVNode("list-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                createElementVNode("list-item", utsMapOf("style" to normalizeStyle(utsMapOf("background-color" to "black", "flex-direction" to "column"))), utsArrayOf(
                    createElementVNode("image", utsMapOf("mode" to "widthFix", "src" to "/static/logo.png")),
                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "100%", "height" to "100px", "background-color" to "rgba(170, 170, 255, 0.5)"))), null, 4)
                ), 4)
            ), 4),
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1", "background-color" to "red", "flex-direction" to "column"))), utsArrayOf(
                createElementVNode("image", utsMapOf("mode" to "widthFix", "src" to "/static/logo.png")),
                createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "100%", "height" to "100px", "background-color" to "rgba(170, 170, 255, 0.5)"))), null, 4)
            ), 4)
        ), 4)
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
