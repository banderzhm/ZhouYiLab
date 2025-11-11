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
open class GenComponentsInputDataInputData : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var title: String by `$props`
    open var type: String by `$props`
    open var defaultValue: String by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenComponentsInputDataInputData) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenComponentsInputDataInputData
            val _cache = __ins.renderCache
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val props = __props
            val inputClearValue = ref<InputValue>("")
            val showClearIcon = ref(false)
            val inputType = ref("text")
            fun _getValue(value: Any): InputValue {
                when (props.type) {
                    "number" -> 
                        return parseFloat(value as String)
                }
                return value
            }
            onMounted(fun(){
                when (props.type) {
                    "number" -> 
                        inputType.value = "number"
                }
                inputClearValue.value = _getValue(props.defaultValue)
            }
            )
            fun gen_input_fn(event: InputEvent) {
                inputClearValue.value = event.detail.value
                if ((inputClearValue.value as String).length > 0) {
                    showClearIcon.value = true
                } else {
                    showClearIcon.value = false
                }
                emit("confirm", _getValue(inputClearValue.value as String))
            }
            val input = ::gen_input_fn
            fun gen_clearIcon_fn() {
                inputClearValue.value = ""
                showClearIcon.value = false
                emit("confirm", _getValue(inputClearValue.value as String))
            }
            val clearIcon = ::gen_clearIcon_fn
            fun gen_blur_fn() {
                showClearIcon.value = false
            }
            val blur = ::gen_blur_fn
            fun gen_focus_fn() {
                var inputValue = inputClearValue.value
                if (UTSAndroid.`typeof`(inputValue) !== "string") {
                    inputValue = (inputValue as Number).toString()
                }
                if ((inputValue as String).length > 0) {
                    showClearIcon.value = true
                } else {
                    showClearIcon.value = false
                }
            }
            val focus = ::gen_focus_fn
            return fun(): Any? {
                return createElementVNode(Fragment, null, utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-title uni-common-mt"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "uni-title-text"), toDisplayString(_ctx.title), 1)
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "input-wrapper"), utsArrayOf(
                        createElementVNode("input", utsMapOf("class" to "uni-input", "type" to unref(inputType), "value" to unref(inputClearValue), "placeholder" to _ctx.title, "maxlength" to "-1", "onInput" to input, "onBlur" to blur, "onFocus" to focus), null, 40, utsArrayOf(
                            "type",
                            "value",
                            "placeholder"
                        )),
                        if (isTrue(unref(showClearIcon))) {
                            createElementVNode("image", utsMapOf("key" to 0, "class" to "input-wrapper_image", "src" to "/static/icons/clear.png", "onTouchstart" to clearIcon), null, 32)
                        } else {
                            createCommentVNode("v-if", true)
                        }
                    ))
                ), 64)
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("input-wrapper" to padStyleMapOf(utsMapOf("borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "rgba(0,0,0,0.08)", "borderRightColor" to "rgba(0,0,0,0.08)", "borderBottomColor" to "rgba(0,0,0,0.08)", "borderLeftColor" to "rgba(0,0,0,0.08)", "flexDirection" to "row", "justifyContent" to "center", "paddingTop" to 0, "paddingRight" to 0, "paddingBottom" to 0, "paddingLeft" to 0, "marginTop" to 0, "marginRight" to 10, "marginBottom" to 0, "marginLeft" to 10, "flexWrap" to "nowrap", "backgroundColor" to "#ffffff")), "input-wrapper_image" to padStyleMapOf(utsMapOf("width" to 22, "height" to 22, "alignSelf" to "center", "marginRight" to 5)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf("confirm" to null)
        var props = normalizePropsOptions(utsMapOf("title" to utsMapOf("type" to "String", "required" to true), "type" to utsMapOf("type" to "String", "required" to true), "defaultValue" to utsMapOf("type" to "String", "required" to true, "default" to "")))
        var propsNeedCastKeys = utsArrayOf(
            "defaultValue"
        )
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
