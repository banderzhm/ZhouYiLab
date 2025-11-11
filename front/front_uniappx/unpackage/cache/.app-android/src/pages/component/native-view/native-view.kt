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
import io.dcloud.uniapp.extapi.openDialogPage as uni_openDialogPage
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesComponentNativeViewNativeView : BasePage {
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
        onLoad(fun(_: OnLoadOptions) {}, __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_native_button = resolveEasyComponent("native-button", GenUniModulesNativeButtonComponentsNativeButtonNativeButtonClass)
        val _component_native_button_container = resolveEasyComponent("native-button-container", GenComponentsNativeButtonContainerNativeButtonContainerClass)
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("padding-bottom" to "50px"))), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "tips"), "说明：如果本地无 uts 插件编译环境请提交打包自定义基座查看效果"),
                createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.testCallMethod), "调用组件方法", 8, utsArrayOf(
                    "onClick"
                )),
                createVNode(_component_native_button, utsMapOf("id" to "helloView", "class" to "native-button", "style" to normalizeStyle(utsMapOf("width" to "200px", "height" to "100px")), "text" to _ctx.buttonText, "onButtonTap" to _ctx.ontap, "onLoad" to _ctx.onload), null, 8, utsArrayOf(
                    "style",
                    "text",
                    "onButtonTap",
                    "onLoad"
                )),
                createVNode(_component_native_button_container),
                createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.gotoTimePicker), "调用native-time-picker", 8, utsArrayOf(
                    "onClick"
                ))
            ), 4)
        ), 4)
    }
    open var title: String by `$data`
    open var buttonText: String by `$data`
    open var isLoad: Boolean by `$data`
    open var clickCount: Number by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "Hello", "buttonText" to "native-button", "isLoad" to false, "clickCount" to 0)
    }
    open var ontap = ::gen_ontap_fn
    open fun gen_ontap_fn(e: UniNativeViewEvent) {
        uni_showToast(ShowToastOptions(title = "按钮被点击了"))
        this.clickCount++
        this.buttonText = "native-button" + this.clickCount
    }
    open var onload = ::gen_onload_fn
    open fun gen_onload_fn() {
        this.isLoad = true
    }
    open var testCallMethod = ::gen_testCallMethod_fn
    open fun gen_testCallMethod_fn() {
        var context = createNativeButtonContext("helloView")
        context?.updateText("test code")
    }
    open var gotoTimePicker = ::gen_gotoTimePicker_fn
    open fun gen_gotoTimePicker_fn() {
        uni_openDialogPage(OpenDialogPageOptions(url = "/pages/component/native-view/native-view-time-picker-dialog", animationType = "fade-in"))
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
                return utsMapOf("tips" to padStyleMapOf(utsMapOf("fontSize" to 14, "color" to "#BEBEBE", "marginTop" to 25, "marginRight" to "auto", "marginBottom" to 25, "marginLeft" to "auto", "textAlign" to "center")), "native-button" to padStyleMapOf(utsMapOf("height" to 100, "width" to 100, "marginTop" to 25, "marginRight" to "auto", "marginBottom" to 25, "marginLeft" to "auto")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
