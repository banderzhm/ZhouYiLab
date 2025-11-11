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
import io.dcloud.uniapp.extapi.closeDialogPage as uni_closeDialogPage
open class GenPagesComponentNativeViewNativeViewTimePickerDialog : BasePage {
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
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_native_time_picker = resolveEasyComponent("native-time-picker", GenUniModulesNativeTimePickerComponentsNativeTimePickerNativeTimePickerClass)
        return createElementVNode("view", utsMapOf("class" to "dialog-container"), utsArrayOf(
            createVNode(_component_native_time_picker, utsMapOf("class" to "native-time-picker", "hour" to 2, "minute" to 3, "onChanged" to _ctx.onChanged), null, 8, utsArrayOf(
                "onChanged"
            )),
            createElementVNode("button", utsMapOf("onClick" to _ctx.sure), "确定", 8, utsArrayOf(
                "onClick"
            ))
        ))
    }
    open var onChanged = ::gen_onChanged_fn
    open fun gen_onChanged_fn(e: UniNativeViewEvent) {
        console.log("onchanged-----", e.detail, " at pages/component/native-view/native-view-time-picker-dialog.uvue:18")
    }
    open var sure = ::gen_sure_fn
    open fun gen_sure_fn() {
        uni_closeDialogPage(CloseDialogPageOptions(dialogPage = this.`$page`))
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
                return utsMapOf("native-time-picker" to padStyleMapOf(utsMapOf("marginTop" to 10, "marginRight" to "auto", "marginBottom" to 25, "marginLeft" to "auto", "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopLeftRadius" to 5, "borderTopRightRadius" to 5, "borderBottomRightRadius" to 5, "borderBottomLeftRadius" to 5, "backgroundColor" to "#FFFFFF")), "dialog-container" to padStyleMapOf(utsMapOf("width" to "100%", "height" to "100%", "backgroundColor" to "rgba(0,0,0,0.3)", "display" to "flex", "justifyContent" to "center", "alignItems" to "center")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
