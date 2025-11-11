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
import io.dcloud.uniapp.extapi.getElementById as uni_getElementById
open class GenPagesComponentViewIssue19746 : BasePage {
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
    open var getClickCount: () -> Number
        get() {
            return unref(this.`$exposed`["getClickCount"]) as () -> Number
        }
        set(value) {
            setRefValue(this.`$exposed`, "getClickCount", value)
        }
    open var setPointEventAuto: () -> Unit
        get() {
            return unref(this.`$exposed`["setPointEventAuto"]) as () -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "setPointEventAuto", value)
        }
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesComponentViewIssue19746, _arg1: SetupContext) -> Any? = fun(__props, ref1): Any? {
            var __expose = ref1.expose
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesComponentViewIssue19746
            val _cache = __ins.renderCache
            val clickCount = ref(0)
            val viewEle = ref<UniElement?>(null)
            fun gen_setPointEventAuto_fn() {
                viewEle.value?.style?.setProperty("pointer-events", "auto")
            }
            val setPointEventAuto = ::gen_setPointEventAuto_fn
            fun gen_onClick_fn() {
                clickCount.value += 1
                console.log(clickCount.value, " at pages/component/view/issue-19746.uvue:19")
            }
            val onClick = ::gen_onClick_fn
            fun gen_getClickCount_fn(): Number {
                return clickCount.value
            }
            val getClickCount = ::gen_getClickCount_fn
            onMounted(fun(){
                viewEle.value = uni_getElementById("subView")
            }
            )
            __expose(utsMapOf("getClickCount" to getClickCount, "setPointEventAuto" to setPointEventAuto))
            return fun(): Any? {
                return createElementVNode("view", null, utsArrayOf(
                    createElementVNode("view", utsMapOf("id" to "subView", "class" to "subView", "onClick" to onClick))
                ))
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ), utsArrayOf(
                GenApp.styles
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("subView" to padStyleMapOf(utsMapOf("width" to 200, "height" to 500, "backgroundColor" to "#FF0000", "pointerEvents" to "none")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
