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
import io.dcloud.uniapp.extapi.makePhoneCall as uni_makePhoneCall
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesAPIMakePhoneCallMakePhoneCall : BasePage {
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
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        return createElementVNode("view", null, utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                "title"
            )),
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "uni-hello-text uni-center"), "请在下方输入电话号码"),
                createElementVNode("input", utsMapOf("class" to "input uni-common-mt", "type" to "number", "name" to "input", "onInput" to _ctx.bindInput), null, 40, utsArrayOf(
                    "onInput"
                )),
                createElementVNode("view", utsMapOf("class" to "uni-btn-v uni-common-mt"), utsArrayOf(
                    createElementVNode("button", utsMapOf("onClick" to _ctx.makePhoneCall, "type" to "primary", "disabled" to _ctx.disabled), "拨打", 8, utsArrayOf(
                        "onClick",
                        "disabled"
                    ))
                ))
            ))
        ))
    }
    open var title: String by `$data`
    open var disabled: Boolean by `$data`
    open var inputValue: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "makePhoneCall", "disabled" to true, "inputValue" to "")
    }
    open var bindInput = ::gen_bindInput_fn
    open fun gen_bindInput_fn(e: UniInputEvent) {
        this.inputValue = e.detail.value
        if (this.inputValue.length > 0) {
            this.disabled = false
        } else {
            this.disabled = true
        }
    }
    open var makePhoneCall = ::gen_makePhoneCall_fn
    open fun gen_makePhoneCall_fn() {
        uni_makePhoneCall(MakePhoneCallOptions(phoneNumber = this.inputValue, success = fun(_){
            console.log("成功拨打电话", " at pages/API/make-phone-call/make-phone-call.uvue:35")
        }
        , fail = fun(err){
            console.log(err.errCode, " at pages/API/make-phone-call/make-phone-call.uvue:38")
            uni_showToast(ShowToastOptions(title = "错误码：" + err.errCode.toString(), icon = "error"))
        }
        ))
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
                return utsMapOf("input" to padStyleMapOf(utsMapOf("height" to 60, "lineHeight" to "60px", "fontSize" to 39, "borderBottomWidth" to 1, "borderBottomStyle" to "solid", "borderBottomColor" to "#E2E2E2", "textAlign" to "center")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
