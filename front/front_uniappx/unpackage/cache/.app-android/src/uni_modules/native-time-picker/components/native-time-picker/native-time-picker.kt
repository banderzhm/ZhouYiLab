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
import uts.sdk.modules.nativeTimePicker.NativeTimePicker
open class GenUniModulesNativeTimePickerComponentsNativeTimePickerNativeTimePicker : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var hour: Number by `$props`
    open var minute: Number by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenUniModulesNativeTimePickerComponentsNativeTimePickerNativeTimePicker) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenUniModulesNativeTimePickerComponentsNativeTimePickerNativeTimePicker
            val _cache = __ins.renderCache
            val props = __props
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val picker = ref<NativeTimePicker?>(null)
            val hourValue = ref(props.hour)
            val minuteValue = ref(props.minute)
            watch(fun(): Number {
                return props.hour
            }
            , fun(newValue: Number){
                if (newValue < 23 && newValue >= 0) {
                    hourValue.value = newValue
                    picker.value?.setHour(hourValue.value)
                }
            }
            , WatchOptions(immediate = true))
            watch(fun(): Number {
                return props.minute
            }
            , fun(newValue: Number){
                if (newValue < 59 && newValue >= 0) {
                    minuteValue.value = newValue
                    picker.value?.setMinute(minuteValue.value)
                }
            }
            , WatchOptions(immediate = true))
            fun gen_onviewinit_fn(e: UniNativeViewInitEvent) {
                picker.value = NativeTimePicker(e.detail.element, hourValue.value, minuteValue.value)
                emit("load")
            }
            val onviewinit = ::gen_onviewinit_fn
            fun gen_ontimechanged_fn(e: UniNativeViewEvent) {
                emit("changed", e)
            }
            val ontimechanged = ::gen_ontimechanged_fn
            return fun(): Any? {
                return createElementVNode("view", utsMapOf("class" to "def-picker"), utsArrayOf(
                    createElementVNode("native-view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "100%", "height" to "100%")), "onInit" to onviewinit, "onTimechanged" to ontimechanged), null, 36)
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
                return utsMapOf("def-picker" to padStyleMapOf(utsMapOf("minWidth" to 300, "minHeight" to 380, "maxWidth" to 400, "maxHeight" to 400)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf("load" to null, "changed" to null)
        var props = normalizePropsOptions(utsMapOf("hour" to utsMapOf("type" to "Number", "default" to 0), "minute" to utsMapOf("type" to "Number", "default" to 0)))
        var propsNeedCastKeys = utsArrayOf(
            "hour",
            "minute"
        )
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
