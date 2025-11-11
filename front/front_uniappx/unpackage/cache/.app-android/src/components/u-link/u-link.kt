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
open class GenComponentsULinkULink : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var href: String by `$props`
    open var text: String by `$props`
    open var inWhiteList: Boolean by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenComponentsULinkULink) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenComponentsULinkULink
            val _cache = __ins.renderCache
            val openURL = fun(){}
            return fun(): Any? {
                return createElementVNode("view", utsMapOf("class" to "uni-row"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "text", "href" to _ctx.href, "onClick" to openURL, "inWhiteList" to _ctx.inWhiteList), toDisplayString(_ctx.text), 9, utsArrayOf(
                        "href",
                        "inWhiteList"
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
                return utsMapOf("text" to padStyleMapOf(utsMapOf("color" to "#7A7E83", "fontSize" to 14, "lineHeight" to "20px")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf("href" to utsMapOf("type" to "String", "default" to ""), "text" to utsMapOf("type" to "String", "default" to ""), "inWhiteList" to utsMapOf("type" to "Boolean", "default" to false)))
        var propsNeedCastKeys = utsArrayOf(
            "href",
            "text",
            "inWhiteList"
        )
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
