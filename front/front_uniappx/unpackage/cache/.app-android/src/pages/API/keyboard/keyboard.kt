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
import io.dcloud.uniapp.extapi.hideKeyboard as uni_hideKeyboard
import io.dcloud.uniapp.extapi.offKeyboardHeightChange as uni_offKeyboardHeightChange
import io.dcloud.uniapp.extapi.onKeyboardHeightChange as uni_onKeyboardHeightChange
open class GenPagesAPIKeyboardKeyboard : BasePage {
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
        onLoad(fun(_: OnLoadOptions) {
            uni_onKeyboardHeightChange(fun(res){
                this.keyboardHeight = res.height
                this.keyboardStatus = if (res.height > 0) {
                    "显示中"
                } else {
                    "已隐藏"
                }
            }
            )
        }
        , __ins)
        onUnload(fun() {
            uni_offKeyboardHeightChange()
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("view", utsMapOf("class" to "container"), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "input-section"), utsArrayOf(
                createElementVNode("input", utsMapOf("id" to "uni-input-box", "class" to "input-box", "type" to "text", "value" to _ctx.inputValue, "placeholder" to "点击输入框显示键盘", "focus" to _ctx.isFocus, "hold-keyboard" to "true"), null, 8, utsArrayOf(
                    "value",
                    "focus"
                )),
                createElementVNode("button", utsMapOf("class" to "btn", "onClick" to _ctx.hideKeyboard), "隐藏键盘", 8, utsArrayOf(
                    "onClick"
                ))
            )),
            createElementVNode("view", utsMapOf("class" to "info-section"), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "info-text"), "键盘高度: " + toDisplayString(_ctx.keyboardHeight) + "px", 1),
                createElementVNode("text", utsMapOf("class" to "info-text"), "键盘状态: " + toDisplayString(_ctx.keyboardStatus), 1)
            ))
        ))
    }
    open var inputValue: String by `$data`
    open var isFocus: Boolean by `$data`
    open var keyboardHeight: Number by `$data`
    open var keyboardStatus: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("inputValue" to "", "isFocus" to false, "keyboardHeight" to 0, "keyboardStatus" to "未显示")
    }
    open var hideKeyboard = ::gen_hideKeyboard_fn
    open fun gen_hideKeyboard_fn() {
        uni_hideKeyboard(null)
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
                return utsMapOf("container" to padStyleMapOf(utsMapOf("paddingTop" to 20, "paddingRight" to 20, "paddingBottom" to 20, "paddingLeft" to 20)), "input-section" to padStyleMapOf(utsMapOf("marginBottom" to 20)), "input-box" to padStyleMapOf(utsMapOf("width" to "100%", "height" to 40, "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#cccccc", "borderRightColor" to "#cccccc", "borderBottomColor" to "#cccccc", "borderLeftColor" to "#cccccc", "borderTopLeftRadius" to 4, "borderTopRightRadius" to 4, "borderBottomRightRadius" to 4, "borderBottomLeftRadius" to 4, "paddingTop" to 0, "paddingRight" to 10, "paddingBottom" to 0, "paddingLeft" to 10, "marginBottom" to 10)), "btn" to padStyleMapOf(utsMapOf("backgroundColor" to "#007AFF", "color" to "#ffffff")), "info-section" to padStyleMapOf(utsMapOf("marginTop" to 20)), "info-text" to padStyleMapOf(utsMapOf("width" to "100%", "marginBottom" to 10, "fontSize" to 16, "color" to "#333333")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
