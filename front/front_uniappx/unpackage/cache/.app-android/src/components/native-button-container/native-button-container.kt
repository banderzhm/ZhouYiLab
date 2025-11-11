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
import uts.sdk.modules.nativeButton.createNativeButtonContext
open class GenComponentsNativeButtonContainerNativeButtonContainer : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenComponentsNativeButtonContainerNativeButtonContainer) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenComponentsNativeButtonContainerNativeButtonContainer
            val _cache = __ins.renderCache
            val title = ref("组件能力封装示例")
            val testCallMethod = fun(){
                var context = createNativeButtonContext("helloView1", getCurrentInstance()?.proxy)
                context?.updateText("test code")
            }
            return fun(): Any? {
                val _component_native_button = resolveEasyComponent("native-button", GenUniModulesNativeButtonComponentsNativeButtonNativeButtonClass)
                return createElementVNode("view", null, utsArrayOf(
                    createElementVNode("button", utsMapOf("type" to "primary", "onClick" to testCallMethod), "调用组件方法"),
                    createVNode(_component_native_button, utsMapOf("id" to "helloView1", "class" to "native-button", "text" to "自定义组件"))
                ))
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf())
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
