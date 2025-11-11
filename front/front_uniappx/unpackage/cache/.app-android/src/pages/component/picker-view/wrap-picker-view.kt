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
open class GenPagesComponentPickerViewWrapPickerView : BasePage {
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
        val _component_MyPickerView = resolveComponent("MyPickerView")
        return createElementVNode("view", null, utsArrayOf(
            if (isTrue(_ctx.visible)) {
                createVNode(_component_MyPickerView, utsMapOf("key" to 0, "ref" to "pickerViewRef"), null, 512)
            } else {
                createCommentVNode("v-if", true)
            }
            ,
            createElementVNode("button", utsMapOf("class" to "btn_toggle", "onClick" to _ctx.toggle), "btn", 8, utsArrayOf(
                "onClick"
            ))
        ))
    }
    open var visible: Boolean by `$data`
    open var pickerViewRef: Any? by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("visible" to false, "pickerViewRef" to null)
    }
    open var setValue = ::gen_setValue_fn
    open fun gen_setValue_fn() {
        val instance = this.`$refs`["pickerViewRef"]
        if (instance != null) {
            (instance as ComponentPublicInstance).`$callMethod`("setValue")
        }
    }
    open var toggle = ::gen_toggle_fn
    open fun gen_toggle_fn() {
        this.visible = !this.visible
        if (this.visible) {
            nextTick(fun(){
                this.setValue()
            }
            )
        }
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(), utsArrayOf(
                GenApp.styles
            ))
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf("MyPickerView" to GenPagesComponentPickerViewPickerViewClass1)
    }
}
