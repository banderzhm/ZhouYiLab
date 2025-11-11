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
open class GenComponentsPageHeadPageHead : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var title: String by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenComponentsPageHeadPageHead) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenComponentsPageHeadPageHead
            val _cache = __ins.renderCache
            return fun(): Any? {
                return createElementVNode("view", utsMapOf("class" to "common-page-head"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "common-page-head-title-box"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "common-page-head-title"), toDisplayString(_ctx.title), 1)
                    ))
                ))
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("common-page-head" to padStyleMapOf(utsMapOf("paddingTop" to 20, "paddingRight" to 20, "paddingBottom" to 20, "paddingLeft" to 20, "alignItems" to "center")), "common-page-head-title-box" to padStyleMapOf(utsMapOf("paddingTop" to 0, "paddingRight" to 20, "paddingBottom" to 0, "paddingLeft" to 20, "borderBottomWidth" to 1, "borderBottomStyle" to "solid", "borderBottomColor" to "#D8D8D8")), "common-page-head-title" to padStyleMapOf(utsMapOf("lineHeight" to "40px", "color" to "#BEBEBE")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf("title" to utsMapOf("type" to "String", "default" to "")))
        var propsNeedCastKeys = utsArrayOf(
            "title"
        )
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
