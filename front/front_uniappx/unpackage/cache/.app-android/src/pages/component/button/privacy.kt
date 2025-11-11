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
import io.dcloud.uniapp.extapi.offPrivacyAuthorizationChange as uni_offPrivacyAuthorizationChange
import io.dcloud.uniapp.extapi.onPrivacyAuthorizationChange as uni_onPrivacyAuthorizationChange
import uts.sdk.modules.utsOpenSchema.openSchema
import io.dcloud.uniapp.extapi.resetPrivacyAuthorization as uni_resetPrivacyAuthorization
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesComponentButtonPrivacy : BasePage {
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
        onUnmounted(fun() {
            uni_offPrivacyAuthorizationChange(this.number)
        }
        , __ins)
        onLoad(fun(_: OnLoadOptions) {
            this.number = uni_onPrivacyAuthorizationChange(fun(callback){
                uni_showToast(ShowToastOptions(title = "isPrivacyAgree:" + !callback.needAuthorization))
            }
            )
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("view", utsMapOf("class" to "dialog-container"), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "dialog-content"), utsArrayOf(
                createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "100%", "justify-content" to "center", "align-items" to "center", "background-color" to "#fff"))), utsArrayOf(
                    createElementVNode("image", utsMapOf("src" to default2, "style" to normalizeStyle(utsMapOf("margin-top" to "25px", "width" to "60px", "height" to "60px"))), null, 4)
                ), 4),
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("text-align" to "center", "padding-top" to "20px", "font-size" to "20px", "color" to "black", "background-color" to "#fff"))), "个人信息保护指引", 4),
                createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1", "align-content" to "center", "padding-top" to "10px", "padding-left" to "25px", "padding-right" to "25px", "background-color" to "#fff")), "show-scrollbar" to "false"), utsArrayOf(
                    createElementVNode("text", null, utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "privacy-text"), "欢迎使用 Hello uni-app x 应用，我们将通过"),
                        createElementVNode("text", utsMapOf("class" to "privacy-href", "onClick" to fun(){
                            _ctx.hrefClick(_ctx.hrefLicense)
                        }
                        ), "《用户服务协议》", 8, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("text", utsMapOf("class" to "privacy-text"), "及"),
                        createElementVNode("text", utsMapOf("class" to "privacy-text"), "相关个人信息处理规则帮助你了解我们如何收集、处理个人信息。根据《常见类型移动互联网应用程序必要个人信息范围规定》，应用在演示 uni-app x 能力时仅收集、处理相关必要个人信息及数据。我们通过"),
                        createElementVNode("text", utsMapOf("class" to "privacy-href", "onClick" to fun(){
                            _ctx.hrefClick(_ctx.hrefPrivacy)
                        }
                        ), "《隐私政策》", 8, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("text", utsMapOf("class" to "privacy-text"), "帮助你全面了解我们的服务及收集、处理个人信息的详细情况。")
                    )),
                    createElementVNode("text", utsMapOf("class" to "privacy-text"), "如你同意请点击“同意”按钮接受我们的服务。")
                ), 4),
                createElementVNode("button", utsMapOf("class" to "button", "onClick" to _ctx.reject), "不同意", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "button", "type" to "primary", "open-type" to "agreePrivacyAuthorization", "onClick" to _ctx.agree), "同意", 8, utsArrayOf(
                    "onClick"
                ))
            ))
        ))
    }
    open var number: Number by `$data`
    open var hrefLicense: String by `$data`
    open var hrefPrivacy: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("number" to 0, "hrefLicense" to "https://dcloud.io/license/DCloud.html", "hrefPrivacy" to "https://dcloud.io/license/hello-uni-app-x.html")
    }
    open var agree = ::gen_agree_fn
    open fun gen_agree_fn() {
        uni_closeDialogPage(CloseDialogPageOptions(dialogPage = this.`$page`))
    }
    open var reject = ::gen_reject_fn
    open fun gen_reject_fn() {
        uni_resetPrivacyAuthorization()
        uni_closeDialogPage(CloseDialogPageOptions(dialogPage = this.`$page`))
    }
    open var hrefClick = ::gen_hrefClick_fn
    open fun gen_hrefClick_fn(href: String) {
        openSchema(href)
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
                return utsMapOf("dialog-container" to padStyleMapOf(utsMapOf("width" to "100%", "height" to "100%", "backgroundColor" to "rgba(0,0,0,0.3)", "display" to "flex", "justifyContent" to "center", "alignItems" to "center")), "dialog-content" to padStyleMapOf(utsMapOf("height" to "60%", "width" to "80%", "borderTopLeftRadius" to 12, "borderTopRightRadius" to 12, "borderBottomRightRadius" to 12, "borderBottomLeftRadius" to 12)), "button" to padStyleMapOf(utsMapOf("borderTopLeftRadius" to 0, "borderTopRightRadius" to 0, "borderBottomRightRadius" to 0, "borderBottomLeftRadius" to 0, "borderTopWidth" to 0, "borderRightWidth" to 0, "borderBottomWidth" to 0, "borderLeftWidth" to 0, "borderTopStyle" to "none", "borderRightStyle" to "none", "borderBottomStyle" to "none", "borderLeftStyle" to "none", "borderTopColor" to "#000000", "borderRightColor" to "#000000", "borderBottomColor" to "#000000", "borderLeftColor" to "#000000", "fontSize" to 15, "color" to "#D3D3D3", "textAlign" to "center", "lineHeight" to "40px")), "button-hover1" to padStyleMapOf(utsMapOf("color" to "#000000", "backgroundColor" to "#ffffff")), "button-hover2" to padStyleMapOf(utsMapOf("color" to "#000000", "backgroundColor" to "#4169E1")), "privacy-text" to padStyleMapOf(utsMapOf("color" to "#808080")), "privacy-href" to padStyleMapOf(utsMapOf("color" to "#5F9EA0", "fontWeight" to "bold", "textDecorationLine" to "underline")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
