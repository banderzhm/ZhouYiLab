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
import io.dcloud.uniapp.extapi.getAppBaseInfo as uni_getAppBaseInfo
import io.dcloud.uniapp.extapi.getProviderSync as uni_getProviderSync
import io.dcloud.uniapp.extapi.hideLoading as uni_hideLoading
import io.dcloud.uniapp.extapi.request as uni_request
import io.dcloud.uniapp.extapi.requestPayment as uni_requestPayment
import io.dcloud.uniapp.extapi.showLoading as uni_showLoading
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesAPIRequestPaymentRequestPayment : BasePage {
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
            var provider = uni_getProviderSync(GetProviderSyncOptions(service = "payment"))
            console.log(provider, " at pages/API/request-payment/request-payment.uvue:34")
            provider.providerObjects.forEach(fun(value: UniProvider){
                when (value.id) {
                    "alipay" -> 
                        {
                            var aliPayProvider = value as UniPaymentAlipayProvider
                            console.log("alipay", aliPayProvider, " at pages/API/request-payment/request-payment.uvue:39")
                            this.providerList.push(PayItem(name = aliPayProvider.description, id = aliPayProvider.id, provider = aliPayProvider))
                        }
                    "wxpay" -> 
                        {
                            var wxPayProvider = value as UniPaymentWxpayProvider
                            console.log("wxpay", wxPayProvider, " at pages/API/request-payment/request-payment.uvue:48")
                            this.providerList.push(PayItem(name = wxPayProvider.description, id = wxPayProvider.id, provider = wxPayProvider))
                        }
                    else -> 
                        {}
                }
            }
            )
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        return createElementVNode(Fragment, null, utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to "发起支付")),
            createElementVNode("view", utsMapOf("class" to "uni-common-mt", "style" to normalizeStyle(utsMapOf("padding" to "0 10px"))), utsArrayOf(
                createElementVNode("text", null, "如对当前页面的支付示例功能有任何疑问，通过电子邮件：service@dcloud.io 联系我们")
            ), 4),
            if (_ctx.providerList.length > 0) {
                createElementVNode(Fragment, utsMapOf("key" to 0), RenderHelpers.renderList(_ctx.providerList, fun(item, index, __index, _cached): Any {
                    return createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("margin-top" to "20px")), "type" to "primary", "key" to index, "onClick" to fun(){
                        _ctx.requestPayment(item)
                    }), toDisplayString(item.name), 13, utsArrayOf(
                        "onClick"
                    ))
                }), 128)
            } else {
                createCommentVNode("v-if", true)
            }
        ), 64)
    }
    open var btnText: String by `$data`
    open var btnType: String by `$data`
    open var orderInfo: String by `$data`
    open var errorCode: Number by `$data`
    open var errorMsg: String by `$data`
    open var complete: Boolean by `$data`
    open var fail: Boolean by `$data`
    open var providerList: UTSArray<PayItem> by `$data`
    open var outTradeNo: String by `$data`
    open var openid: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("btnText" to "支付宝支付", "btnType" to "primary", "orderInfo" to "", "errorCode" to 0, "errorMsg" to "", "complete" to false, "fail" to false, "providerList" to utsArrayOf<PayItem>(), "outTradeNo" to "", "openid" to "")
    }
    open var requestPayment = ::gen_requestPayment_fn
    open fun gen_requestPayment_fn(e: PayItem) {
        val provider = e.id
        if (provider == "alipay") {
            this.payAli(provider)
        } else if (provider == "wxpay") {
            if (e.provider != null && e.provider is UniPaymentWxpayProvider && !(e.provider as UniPaymentWxpayProvider).isWeChatInstalled) {
                uni_showToast(ShowToastOptions(title = "微信没有安装", icon = "error"))
            } else {
                this.payWX(provider)
            }
        }
    }
    open var payAli = ::gen_payAli_fn
    open fun gen_payAli_fn(id: String) {
        uni_showLoading(ShowLoadingOptions(title = "请求中..."))
        uni_request<Any>(RequestOptions(url = "https://demo.dcloud.net.cn/payment/alipay/?total=0.01", method = "GET", timeout = 6000, success = fun(res){
            this.orderInfo = JSON.stringify(res.data)
            console.log("====" + this.orderInfo, " at pages/API/request-payment/request-payment.uvue:115")
            uni_hideLoading()
            uni_requestPayment(RequestPaymentOptions(provider = id, orderInfo = res.data as String, fail = fun(res){
                console.log(JSON.stringify(res), " at pages/API/request-payment/request-payment.uvue:121")
                this.errorCode = res.errCode
                uni_showToast(ShowToastOptions(icon = "error", title = "errorCode:" + this.errorCode))
            }
            , success = fun(res){
                console.log(JSON.stringify(res), " at pages/API/request-payment/request-payment.uvue:129")
                uni_showToast(ShowToastOptions(icon = "success", title = "支付成功"))
            }
            ))
        }
        , fail = fun(e){
            console.log(e, " at pages/API/request-payment/request-payment.uvue:138")
            uni_hideLoading()
        }
        ))
    }
    open var payWX = ::gen_payWX_fn
    open fun gen_payWX_fn(id: String) {
        uni_showLoading(ShowLoadingOptions(title = "请求中..."))
        var url = "https://demo.dcloud.net.cn/payment/wxpayv3.__UNI__uniappx/?total=0.01"
        val res = uni_getAppBaseInfo(null)
        var packageName: String?
        packageName = res.packageName
        if (packageName == "io.dcloud.hellouniappx") {
            url = "https://demo.dcloud.net.cn/payment/wxpayv3.__UNI__HelloUniAppX/?total=0.01"
        }
        uni_request<Any>(RequestOptions(url = url, method = "GET", timeout = 6000, header = object : UTSJSONObject() {
            var `Content-Type` = "application/json"
        }, success = fun(res){
            console.log(res.data, " at pages/API/request-payment/request-payment.uvue:170")
            uni_hideLoading()
            uni_requestPayment(RequestPaymentOptions(provider = id, orderInfo = JSON.stringify(res.data), fail = fun(res){
                console.log(JSON.stringify(res), " at pages/API/request-payment/request-payment.uvue:176")
                this.errorCode = res.errCode
                uni_showToast(ShowToastOptions(duration = 5000, icon = "error", title = "errorCode:" + this.errorCode))
            }
            , success = fun(res){
                console.log(JSON.stringify(res), " at pages/API/request-payment/request-payment.uvue:185")
                uni_showToast(ShowToastOptions(duration = 5000, icon = "success", title = "支付成功"))
            }
            ))
        }
        , fail = fun(res){
            uni_hideLoading()
            console.log(res, " at pages/API/request-payment/request-payment.uvue:196")
        }
        ))
    }
    open var jest_pay = ::gen_jest_pay_fn
    open fun gen_jest_pay_fn() {
        uni_requestPayment(RequestPaymentOptions(provider = "alipay", orderInfo = this.orderInfo, fail = fun(res: RequestPaymentFail){
            this.errorCode = res.errCode
            this.complete = true
            this.fail = true
        }
        , success = fun(res: RequestPaymentSuccess){
            console.log(JSON.stringify(res), " at pages/API/request-payment/request-payment.uvue:301")
            this.complete = true
            this.fail = false
        }
        ))
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
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
