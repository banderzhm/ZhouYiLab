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
open class GenComponentsUniCollapseUniCollapse : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var accordion: Boolean by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenComponentsUniCollapseUniCollapse) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenComponentsUniCollapseUniCollapse
            val _cache = __ins.renderCache
            val props = __props
            var child_nodes = utsArrayOf<ItemChildType>()
            fun gen_registerChild_fn(child: ItemChildType) {
                child_nodes.push(child)
            }
            val registerChild = ::gen_registerChild_fn
            fun gen_toggle_fn(elId: String) {
                if (props.accordion && child_nodes.length > 0) {
                    child_nodes.forEach(fun(item: ItemChildType){
                        val is_open = item.is_open.value as Boolean
                        if (is_open && item.elId != elId) {
                            item.is_open.value = false
                            item.openOrClose(false)
                        }
                    }
                    )
                }
            }
            val toggle = ::gen_toggle_fn
            provide("uni-collapse-register-child", registerChild)
            provide("k-collapse-child-toggle", toggle)
            return fun(): Any? {
                return createElementVNode("view", null, utsArrayOf(
                    renderSlot(_ctx.`$slots`, "default")
                ))
            }
        }
        var name = "UniCollapse"
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf())
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf("accordion" to utsMapOf("type" to "Boolean", "default" to true)))
        var propsNeedCastKeys = utsArrayOf(
            "accordion"
        )
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
