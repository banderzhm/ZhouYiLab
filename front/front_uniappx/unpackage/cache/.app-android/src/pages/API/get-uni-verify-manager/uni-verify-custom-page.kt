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
import io.dcloud.uniapp.extapi.getUniVerifyManager as uni_getUniVerifyManager
import io.dcloud.uniapp.extapi.openDialogPage as uni_openDialogPage
import io.dcloud.uniapp.extapi.showModal as uni_showModal
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesAPIGetUniVerifyManagerUniVerifyCustomPage : BasePage {
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
        onLoad(fun(options: OnLoadOptions) {
            this.uniVerifyManager = uni_getUniVerifyManager()
            this.phone = options["phone"] as String
            this.slogan = options["slogan"] as String
            this.privacyName = options["name"] as String
            this.privacyUrl = options["link"] as String
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_checkbox = resolveComponent("checkbox")
        return createElementVNode("view", utsMapOf("class" to "container"), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "safe_content"), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "close_icon", "onClick" to _ctx.closePage), toDisplayString(_ctx.closeIcon), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("text", utsMapOf("class" to "title"), "这是一个普通uvue的dialogPage页面"),
                createElementVNode("view", utsMapOf("class" to "number"), utsArrayOf(
                    createElementVNode("text", utsMapOf("id" to "number-text", "class" to "number-text", "ref" to "number-text"), toDisplayString(_ctx.phone), 513),
                    createElementVNode("text", utsMapOf("id" to "slogan-text", "class" to "slogan-text"), toDisplayString(_ctx.slogan), 1)
                )),
                createElementVNode("button", utsMapOf("id" to "login-button", "class" to "login-button", "onClick" to _ctx.loginIn), "本机号码一键登录", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("view", utsMapOf("class" to "privacy"), utsArrayOf(
                    createVNode(_component_checkbox, utsMapOf("id" to "privacy-checkbox", "class" to "privacy-checkbox", "ref" to "privacy-checkbox", "checked" to false), null, 512),
                    createElementVNode("text", utsMapOf("class" to "privacy-normal-text"), "登录即同意"),
                    createElementVNode("text", utsMapOf("id" to "privacy-text", "class" to "privacy-text", "onClick" to _ctx.openLink), toDisplayString(_ctx.privacyName), 9, utsArrayOf(
                        "onClick"
                    ))
                )),
                createElementVNode("text", utsMapOf("class" to "other", "onClick" to _ctx.otherLogin), "其他登录方式", 8, utsArrayOf(
                    "onClick"
                ))
            ))
        ))
    }
    open var uniVerifyManager: UniVerifyManager? by `$data`
    open var phone: String by `$data`
    open var slogan: String by `$data`
    open var privacyName: String by `$data`
    open var privacyUrl: String by `$data`
    open var closeIcon: String by `$data`
    open var isLoading: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("uniVerifyManager" to null as UniVerifyManager?, "phone" to "", "slogan" to "", "privacyName" to "", "privacyUrl" to "", "closeIcon" to "\uE650", "isLoading" to false)
    }
    open var closePage = ::gen_closePage_fn
    open fun gen_closePage_fn() {
        uni_closeDialogPage(CloseDialogPageOptions(dialogPage = this.`$page`, animationType = "slide-out-bottom", success = fun(res) {
            console.log("closeThisDialog success", res, " at pages/API/get-uni-verify-manager/uni-verify-custom-page.uvue:47")
        }
        , fail = fun(err) {
            console.log("closeThisDialog fail", err, " at pages/API/get-uni-verify-manager/uni-verify-custom-page.uvue:50")
        }
        ))
    }
    open var openLink = ::gen_openLink_fn
    open fun gen_openLink_fn() {
        var url = "/pages/API/get-uni-verify-manager/full-webview-page?url=" + this.privacyUrl + "&title=" + this.privacyName + "&animationType=slide-out-bottom"
        uni_openDialogPage(OpenDialogPageOptions(url = url, animationType = "slide-in-bottom", success = fun(res) {
            console.log("打开隐私协议", " at pages/API/get-uni-verify-manager/uni-verify-custom-page.uvue:60")
        }
        , fail = fun(err) {
            console.log(err, " at pages/API/get-uni-verify-manager/uni-verify-custom-page.uvue:63")
        }
        ))
    }
    open var loginIn = ::gen_loginIn_fn
    open fun gen_loginIn_fn() {
        if (this.isLoading) {
            uni_showToast(ShowToastOptions(title = "正在登录中，请稍后", position = "bottom", duration = 2000))
            return
        } else {
            this.isLoading = true
        }
        val numberTextElement = this.`$page`.getElementById("number-text")
        val sloganTextElement = this.`$page`.getElementById("slogan-text")
        val loginButtonElement = this.`$page`.getElementById("login-button")
        val privacyCheckBoxElement = this.`$page`.getElementById("privacy-checkbox")
        val privacyTextElement = this.`$page`.getElementById("privacy-text")
        if (numberTextElement != null && sloganTextElement != null && loginButtonElement != null && privacyCheckBoxElement != null && privacyTextElement != null) {
            this.uniVerifyManager?.customLogin(UniVerifyManagerCustomLoginOptions(numberTextElement = numberTextElement, sloganTextElement = sloganTextElement, loginButtonElement = loginButtonElement, privacyCheckBoxElement = privacyCheckBoxElement, privacyTextElement = privacyTextElement, success = fun(res){
                console.log(res, " at pages/API/get-uni-verify-manager/uni-verify-custom-page.uvue:92")
                this.takePhoneNumber(res.accessToken, res.openId)
            }, fail = fun(error){
                if (error.errCode == 40001) {
                    uni_showToast(ShowToastOptions(title = "请同意服务条款", position = "bottom", duration = 2000))
                } else if (error.errCode == 40002) {
                    uni_showToast(ShowToastOptions(title = "授权页不符合规范", position = "bottom", duration = 2000))
                } else {
                    val errorMsg = UTSAndroid.consoleDebugError(JSON.parseObject(error.cause?.cause?.message ?: ""), " at pages/API/get-uni-verify-manager/uni-verify-custom-page.uvue:109")?.getString("errorDesc") ?: error.errMsg
                    uni_showToast(ShowToastOptions(title = errorMsg, position = "bottom", duration = 2000))
                }
            }, complete = fun(_res){
                this.isLoading = false
            }))
        } else {
            uni_showToast(ShowToastOptions(title = "未获取到页面元素", position = "bottom", duration = 2000))
            this.isLoading = false
        }
    }
    open var takePhoneNumber = ::gen_takePhoneNumber_fn
    open fun gen_takePhoneNumber_fn(token: String, openId: String) {
        uniCloud.callFunction(UniCloudCallFunctionOptions(name = "univerify", data = object : UTSJSONObject() {
            var access_token = token
            var openid = openId
        })).then(fun(res){
            this.closePage()
            setTimeout(fun(){
                uni_showModal(ShowModalOptions(title = "取号成功", content = res.result.getJSON("res")?.getString("phoneNumber"), showCancel = false))
            }
            , 100)
        }
        ).`catch`(fun(err){
            console.error(JSON.stringify(err), " at pages/API/get-uni-verify-manager/uni-verify-custom-page.uvue:150")
            this.closePage()
            setTimeout(fun(){
                uni_showModal(ShowModalOptions(title = "取号失败", content = (err as UTSError).message, showCancel = false))
            }
            , 100)
        }
        )
    }
    open var otherLogin = ::gen_otherLogin_fn
    open fun gen_otherLogin_fn() {
        uni_showToast(ShowToastOptions(title = "使用其他方式登录", position = "bottom"))
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
                return utsMapOf("container" to padStyleMapOf(utsMapOf("backgroundColor" to "#FFFFFF", "flex" to 1, "width" to "100%")), "safe_content" to padStyleMapOf(utsMapOf("paddingTop" to CSS_VAR_STATUS_BAR_HEIGHT, "width" to "100%", "height" to "100%")), "close_icon" to padStyleMapOf(utsMapOf("left" to "90%", "top" to 15, "fontFamily" to "uni-icon", "fontSize" to 24)), "title" to padStyleMapOf(utsMapOf("alignSelf" to "center", "top" to 20)), "number" to padStyleMapOf(utsMapOf("width" to "100%", "top" to "25%", "position" to "absolute", "height" to 120)), "number-text" to padStyleMapOf(utsMapOf("width" to "100%", "textAlign" to "center", "fontSize" to 28, "fontWeight" to "bold", "alignSelf" to "center", "height" to 30)), "slogan-text" to padStyleMapOf(utsMapOf("marginTop" to 10, "width" to "100%", "fontSize" to 13, "textAlign" to "center", "alignSelf" to "center", "color" to "#808080", "height" to 20)), "login-button" to padStyleMapOf(utsMapOf("width" to "80%", "top" to "40%", "position" to "absolute", "alignSelf" to "center", "backgroundColor" to "#FF4500", "fontSize" to 16, "color" to "#FFFFFF")), "privacy" to padStyleMapOf(utsMapOf("marginTop" to 10, "flexDirection" to "row", "flexWrap" to "wrap", "top" to "45%", "width" to "100%", "justifyContent" to "center", "alignSelf" to "center", "position" to "absolute")), "privacy-checkbox" to padStyleMapOf(utsMapOf("transform" to "scale(0.65)")), "privacy-text" to padStyleMapOf(utsMapOf("marginTop" to 4, "color" to "#007aff", "fontSize" to 14)), "privacy-normal-text" to padStyleMapOf(utsMapOf("marginTop" to 4, "color" to "#808080", "fontSize" to 14)), "other" to padStyleMapOf(utsMapOf("bottom" to "7%", "transform" to "translateY(50%)", "position" to "absolute", "alignSelf" to "center", "fontSize" to 14)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
