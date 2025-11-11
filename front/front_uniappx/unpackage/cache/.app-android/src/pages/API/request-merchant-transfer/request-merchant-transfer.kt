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
import io.dcloud.uniapp.extapi.requestMerchantTransfer as uni_requestMerchantTransfer
import io.dcloud.uniapp.extapi.requestPayment as uni_requestPayment
import io.dcloud.uniapp.extapi.showLoading as uni_showLoading
import io.dcloud.uniapp.extapi.showModal as uni_showModal
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesAPIRequestMerchantTransferRequestMerchantTransfer : BasePage {
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
        return createElementVNode("view", utsMapOf("class" to "content"), utsArrayOf(
            createElementVNode("button", utsMapOf("class" to "button", "onClick" to _ctx.pay), "支付0.2元", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("button", utsMapOf("class" to "button", "onClick" to _ctx.transfer, "disabled" to _ctx.disabled), "接受0.1元转账", 8, utsArrayOf(
                "onClick",
                "disabled"
            )),
            if (isTrue(_ctx.disabled)) {
                createElementVNode("view", utsMapOf("key" to 0, "class" to "tips", "style" to normalizeStyle(utsMapOf("color" to "#ff5a5f"))), "请先支付 0.2 元，才能体验接收转账 0.1 元", 4)
            } else {
                createElementVNode("view", utsMapOf("key" to 1, "class" to "tips"), utsArrayOf(
                    createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("color" to "#42b983"))), "已支付 0.2 元，请点击“接受0.1元转账”按钮", 4)
                ))
            }
        ))
    }
    open var outTradeNo: String by `$data`
    open var disabled: Boolean by `$data`
    open var openid: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("outTradeNo" to "", "disabled" to true, "openid" to "")
    }
    open var pay = ::gen_pay_fn
    open fun gen_pay_fn() {
        var bundleId = this.getBundleId()
        var random = Math.floor(Math.random() * 9000) + 1000
        this.outTradeNo = "test" + Date.now() + random
        console.log("outTradeNo: ", this.outTradeNo, " at pages/API/request-merchant-transfer/request-merchant-transfer.uvue:37")
        this.disabled = true
        uni_showLoading(ShowLoadingOptions(title = "请求中..."))
        val getProviderRes = uni_getProviderSync(GetProviderSyncOptions(service = "payment"))
        if (getProviderRes.providerIds.indexOf("wxpay") == -1) {
            uni_showToast(ShowToastOptions(title = "未添加微信支付依赖", icon = "error"))
            uni_hideLoading()
            return
        }
        uni_request<Any>(RequestOptions(url = "https://env-00jxt67zj8kj.dev-hz.cloudbasefunction.cn/uni-pay-api/getOrderInfo", method = "GET", data = let {
            object : UTSJSONObject() {
                var outTradeNo = it.outTradeNo
                var bundleId = bundleId
                var openid = it.openid
            }
        }, success = fun(res){
            uni_hideLoading()
            var data = res.data as UTSJSONObject
            var errCode = data["errCode"] as Number
            if (errCode != 0) {
                uni_showModal(ShowModalOptions(title = "提示", content = data["errMsg"] as String, showCancel = false))
                return
            }
            var orderInfo = data["orderInfo"] as String
            console.log("orderInfo: ", orderInfo, " at pages/API/request-merchant-transfer/request-merchant-transfer.uvue:76")
            uni_requestPayment(RequestPaymentOptions(provider = "wxpay", orderInfo = orderInfo, success = fun(res){
                console.log("res: ", res, " at pages/API/request-merchant-transfer/request-merchant-transfer.uvue:89")
                uni_showToast(ShowToastOptions(title = "支付成功", icon = "success"))
                this.disabled = false
            }
            , fail = fun(err){
                console.error("err", err, " at pages/API/request-merchant-transfer/request-merchant-transfer.uvue:97")
                uni_hideLoading()
                uni_showToast(ShowToastOptions(title = "支付失败", icon = "error"))
            }
            ))
        }
        , fail = fun(err){
            uni_hideLoading()
            console.error("request-err", err, " at pages/API/request-merchant-transfer/request-merchant-transfer.uvue:108")
        }
        ))
    }
    open var transfer = ::gen_transfer_fn
    open fun gen_transfer_fn() {
        uni_showLoading(ShowLoadingOptions(title = "请求中..."))
        try {
            var bundleId = this.getBundleId()
            uni_request<Any>(RequestOptions(url = "https://env-00jxt67zj8kj.dev-hz.cloudbasefunction.cn/uni-pay-api/transfer", method = "GET", data = let {
                object : UTSJSONObject() {
                    var outTradeNo = it.outTradeNo
                    var bundleId = bundleId
                }
            }, success = fun(res){
                var data = res.data as UTSJSONObject
                if (data["errCode"] != null && UTSAndroid.`typeof`(data["errCode"]) == "number" && data["errCode"] == 0) {
                    var options = data["options"] as UTSJSONObject
                    uni_requestMerchantTransfer(RequestMerchantTransferOptions(mchId = options["mchId"] as String, appId = options["appId"] as String, `package` = options["package"] as String, success = fun(res){
                        uni_hideLoading()
                        console.log("res: ", res, " at pages/API/request-merchant-transfer/request-merchant-transfer.uvue:135")
                        uni_showToast(ShowToastOptions(title = "转账成功", icon = "success"))
                        this.disabled = true
                    }, fail = fun(err){
                        uni_hideLoading()
                        var errMsg = err.errMsg
                        if (errMsg == "requestMerchantTransfer:fail cancel") {
                            errMsg = "取消转账"
                        }
                        uni_showToast(ShowToastOptions(title = errMsg, icon = "none"))
                        console.error("转账失败", err, " at pages/API/request-merchant-transfer/request-merchant-transfer.uvue:152")
                    }))
                } else {
                    uni_hideLoading()
                    if (data["errMsg"] != null) {
                        uni_showModal(ShowModalOptions(title = "提示", content = data["errMsg"] as String, showCancel = false))
                    }
                    return
                }
            }
            , fail = fun(err){
                console.error("request-err", err, " at pages/API/request-merchant-transfer/request-merchant-transfer.uvue:168")
                uni_hideLoading()
            }
            ))
        }
         catch (err: Throwable) {
            console.error("err: ", err, " at pages/API/request-merchant-transfer/request-merchant-transfer.uvue:173")
            uni_showToast(ShowToastOptions(title = "运行异常", icon = "error"))
            uni_hideLoading()
        }
    }
    open var getBundleId = ::gen_getBundleId_fn
    open fun gen_getBundleId_fn(): String? {
        var baseInfo = uni_getAppBaseInfo(null)
        var bundleId: String?
        bundleId = baseInfo.packageName
        return bundleId
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
                return utsMapOf("content" to padStyleMapOf(utsMapOf("display" to "flex", "flexDirection" to "column", "alignItems" to "center", "justifyContent" to "center", "paddingTop" to 20, "paddingRight" to 20, "paddingBottom" to 20, "paddingLeft" to 20)), "button" to padStyleMapOf(utsMapOf("width" to "100%", "marginBottom" to 20, "borderTopLeftRadius" to 5, "borderTopRightRadius" to 5, "borderBottomRightRadius" to 5, "borderBottomLeftRadius" to 5)), "tips" to padStyleMapOf(utsMapOf("color" to "#999999", "fontSize" to 14)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
