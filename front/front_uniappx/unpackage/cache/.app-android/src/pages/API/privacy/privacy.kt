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
import io.dcloud.uniapp.extapi.getPrivacySetting as uni_getPrivacySetting
import io.dcloud.uniapp.extapi.offPrivacyAuthorizationChange as uni_offPrivacyAuthorizationChange
import io.dcloud.uniapp.extapi.onPrivacyAuthorizationChange as uni_onPrivacyAuthorizationChange
import io.dcloud.uniapp.extapi.openDialogPage as uni_openDialogPage
import io.dcloud.uniapp.extapi.resetPrivacyAuthorization as uni_resetPrivacyAuthorization
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesAPIPrivacyPrivacy : BasePage {
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
        onReady(fun() {
            val id = uni_onPrivacyAuthorizationChange(fun(res){
                this.appPrivacy = if (res.needAuthorization) {
                    "未同意"
                } else {
                    "已同意"
                }
                val privacyState = "监听到隐私协议状态已变更为 " + this.appPrivacy
                uni_showToast(ShowToastOptions(position = "bottom", title = privacyState))
            }
            )
            this.listenId = id
            uni_showToast(ShowToastOptions(position = "bottom", title = "开启监听隐私协议状态"))
        }
        , __ins)
        onUnload(fun() {
            uni_offPrivacyAuthorizationChange(this.listenId)
            this.listenId = 0
            uni_showToast(ShowToastOptions(position = "bottom", title = "已停止监听隐私协议状态"))
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        return createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                "title"
            )),
            createElementVNode("view", utsMapOf("class" to "item-box"), utsArrayOf(
                createElementVNode("text", null, "当前应用隐私授权状态:"),
                createElementVNode("text", null, toDisplayString(_ctx.appPrivacy), 1)
            )),
            createElementVNode("view", null, utsArrayOf(
                createElementVNode("button", utsMapOf("class" to "privacy-button", "type" to "primary", "onClick" to _ctx.getPrivacySetting), " 获取隐私协议授权状态 ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "privacy-button", "type" to "primary", "open-type" to "agreePrivacyAuthorization"), " 同意隐私协议专用按钮 "),
                createElementVNode("button", utsMapOf("class" to "privacy-button", "type" to "primary", "onClick" to _ctx.resetPrivacyAuthorization), " 重置隐私协议授权状态 ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "privacy-button", "onClick" to _ctx.openPrivacyDialog), " 显示隐私政策弹框 ", 8, utsArrayOf(
                    "onClick"
                ))
            ))
        ))
    }
    open var title: String by `$data`
    open var appPrivacy: String by `$data`
    open var privacyContractName: String by `$data`
    open var listenId: Number by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "隐私信息授权", "appPrivacy" to "未获取", "privacyContractName" to "", "listenId" to 0)
    }
    open var getPrivacySetting = ::gen_getPrivacySetting_fn
    open fun gen_getPrivacySetting_fn() {
        uni_getPrivacySetting(GetPrivacySettingOptions(success = fun(res){
            this.appPrivacy = if (res.needAuthorization) {
                "未同意"
            } else {
                "已同意"
            }
        }
        ))
    }
    open var resetPrivacyAuthorization = ::gen_resetPrivacyAuthorization_fn
    open fun gen_resetPrivacyAuthorization_fn() {
        uni_resetPrivacyAuthorization()
    }
    open var openPrivacyDialog = ::gen_openPrivacyDialog_fn
    open fun gen_openPrivacyDialog_fn() {
        uni_openDialogPage(OpenDialogPageOptions(url = "/pages/component/button/privacy"))
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
                return utsMapOf("item-box" to padStyleMapOf(utsMapOf("marginBottom" to 10, "display" to "flex", "flexDirection" to "row", "justifyContent" to "space-between")), "privacy-button" to padStyleMapOf(utsMapOf("marginTop" to 5, "marginBottom" to 5)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
