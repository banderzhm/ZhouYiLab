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
open class GenUniModulesUniRecycleViewComponentsUniRecycleItemUniRecycleItem : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var offset: Number by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenUniModulesUniRecycleViewComponentsUniRecycleItemUniRecycleItem) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenUniModulesUniRecycleViewComponentsUniRecycleItemUniRecycleItem
            val _cache = __ins.renderCache
            val props = __props
            return fun(): Any? {
                return createElementVNode("view", utsMapOf("class" to "recycle-item", "style" to normalizeStyle(utsMapOf("transform" to ("translateY(" + props.offset + "px)")))), utsArrayOf(
                    renderSlot(_ctx.`$slots`, "default")
                ), 4)
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("recycle-item" to padStyleMapOf(utsMapOf("position" to "absolute", "left" to 0, "top" to 0, "width" to "100%", "height" to "auto")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf("offset" to utsMapOf("type" to "Number", "default" to 0)))
        var propsNeedCastKeys = utsArrayOf(
            "offset"
        )
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
