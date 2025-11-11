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
import io.dcloud.uniapp.extapi.redirectTo as uni_redirectTo
open class GenUniModulesUniPayXPagesPayDeskPayDesk : BasePage {
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
            var optionsStr = options["options"] as String?
            if (optionsStr != null && optionsStr != "") {
                var newOptions = UTSAndroid.consoleDebugError(JSON.parse(UTSAndroid.consoleDebugError(decodeURI(optionsStr), " at uni_modules/uni-pay-x/pages/pay-desk/pay-desk.uvue:69")!!), " at uni_modules/uni-pay-x/pages/pay-desk/pay-desk.uvue:69") as UTSJSONObject
                this.myOptions = newOptions as UTSJSONObject
            }
            console.log("myOptions: ", this.myOptions, " at uni_modules/uni-pay-x/pages/pay-desk/pay-desk.uvue:72")
        }
        , __ins)
        onReady(fun() {}, __ins)
        onPageShow(fun() {}, __ins)
        onPageHide(fun() {}, __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_uni_pay = resolveEasyComponent("uni-pay", GenUniModulesUniPayXComponentsUniPayUniPayClass)
        return createElementVNode("view", utsMapOf("class" to "uni-pay"), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "mobile-pay-popup"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "mobile-pay-popup-amount-box"), utsArrayOf(
                    createElementVNode("view", null, utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "text"), "待支付金额：")
                    )),
                    createElementVNode("view", utsMapOf("class" to "mobile-pay-popup-amount"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "text"), toDisplayString(_ctx.amountFormat(_ctx.myOptions.getNumber("total_fee"))), 1)
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "mobile-pay-popup-provider-list"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-list"), utsArrayOf(
                        if (isTrue(_ctx.checkProvider("wxpay"))) {
                            createElementVNode("view", utsMapOf("key" to 0, "class" to "uni-list-item", "onClick" to fun(){
                                _ctx.createOrderByProvider("wxpay")
                            }), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "uni-list-item__container container--right"), utsArrayOf(
                                    createElementVNode("view", utsMapOf("class" to "uni-list-item__header"), utsArrayOf(
                                        createElementVNode("image", utsMapOf("src" to _ctx.insideData.getJSON("images")!!["wxpay"], "class" to "image"), null, 8, utsArrayOf(
                                            "src"
                                        ))
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "uni-list-item__content uni-list-item__content--center"), utsArrayOf(
                                        createElementVNode("text", utsMapOf("class" to "text"), "微信支付")
                                    ))
                                )),
                                createElementVNode("view", utsMapOf("class" to "arrowright"))
                            ), 8, utsArrayOf(
                                "onClick"
                            ))
                        } else {
                            createCommentVNode("v-if", true)
                        }
                        ,
                        if (isTrue(_ctx.checkProvider("alipay"))) {
                            createElementVNode("view", utsMapOf("key" to 1, "class" to "uni-list-item", "onClick" to fun(){
                                _ctx.createOrderByProvider("alipay")
                            }), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "uni-list-item__container container--right"), utsArrayOf(
                                    createElementVNode("view", utsMapOf("class" to "uni-list-item__header"), utsArrayOf(
                                        createElementVNode("image", utsMapOf("src" to _ctx.insideData.getJSON("images")!!["alipay"], "class" to "image"), null, 8, utsArrayOf(
                                            "src"
                                        ))
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "uni-list-item__content uni-list-item__content--center"), utsArrayOf(
                                        createElementVNode("text", utsMapOf("class" to "text"), "支付宝")
                                    ))
                                )),
                                createElementVNode("view", utsMapOf("class" to "arrowright"))
                            ), 8, utsArrayOf(
                                "onClick"
                            ))
                        } else {
                            createCommentVNode("v-if", true)
                        }
                    ))
                ))
            )),
            createVNode(_component_uni_pay, utsMapOf("ref" to "payRef", "height" to "450px", "to-success-page" to false, "onMounted" to _ctx.onMounted, "onSuccess" to _ctx.onSuccess, "onFail" to _ctx.onFail, "onCancel" to _ctx.onCancel), null, 8, utsArrayOf(
                "onMounted",
                "onSuccess",
                "onFail",
                "onCancel"
            ))
        ))
    }
    open var myOptions: UTSJSONObject by `$data`
    open var insideData: UTSJSONObject by `$data`
    open var adpid: String by `$data`
    open var return_url: String by `$data`
    open var main_color: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("myOptions" to object : UTSJSONObject() {
            var total_fee = ""
        }, "insideData" to object : UTSJSONObject() {
            var currentProviders = utsArrayOf<String>()
            var images = object : UTSJSONObject() {
                var alipay = ""
                var wxpay = ""
            }
        }, "adpid" to "", "return_url" to "", "main_color" to "")
    }
    open var onMounted = ::gen_onMounted_fn
    open fun gen_onMounted_fn(insideData: UTSJSONObject) {
        this.insideData["currentProviders"] = insideData.getArray("currentProviders") as UTSArray<String>
        this.insideData["images"] = insideData.getJSON("images") as UTSJSONObject
    }
    open var createOrder = ::gen_createOrder_fn
    open fun gen_createOrder_fn(data: UTSJSONObject) {
        this.myOptions = objectAssign(this.myOptions, data)
        val payInstance = this.`$refs`["payRef"] as UniPayComponentPublicInstance
        payInstance.createOrder(this.myOptions)
    }
    open var createOrderByProvider = ::gen_createOrderByProvider_fn
    open fun gen_createOrderByProvider_fn(provider: String) {
        this.createOrder(UTSJSONObject(Map<String, Any?>(utsArrayOf(
            utsArrayOf(
                "provider",
                provider
            )
        ))))
    }
    open var onSuccess = ::gen_onSuccess_fn
    open fun gen_onSuccess_fn(res: UTSJSONObject) {
        var out_trade_no = res["out_trade_no"] as String
        var pay_order = res.getJSON("pay_order") as UTSJSONObject
        var order_no = pay_order["order_no"] as String
        var total_fee = pay_order.getNumber("total_fee")
        if (total_fee == null) {
            total_fee = 0
        }
        var return_url = this.return_url as String
        var adpid = this.adpid as String
        var main_color = this.main_color as String
        console.log("success: ", res, " at uni_modules/uni-pay-x/pages/pay-desk/pay-desk.uvue:113")
        var user_order_success = res.getBoolean("user_order_success")
        if (user_order_success != null && user_order_success != true) {
            uni_redirectTo(RedirectToOptions(url = "/uni_modules/uni-pay-x/pages/success/success?out_trade_no=" + out_trade_no + "&order_no=" + order_no + "&total_fee=" + total_fee + "&adpid=" + adpid + "&return_url=" + return_url + "&main_color=" + main_color))
        }
    }
    open var onFail = ::gen_onFail_fn
    open fun gen_onFail_fn(err: RequestPaymentFail) {
        console.log("fail: ", err, " at uni_modules/uni-pay-x/pages/pay-desk/pay-desk.uvue:127")
    }
    open var onCancel = ::gen_onCancel_fn
    open fun gen_onCancel_fn(err: RequestPaymentFail) {
        console.log("cancel: ", err, " at uni_modules/uni-pay-x/pages/pay-desk/pay-desk.uvue:132")
    }
    open var amountFormat = ::gen_amountFormat_fn
    open fun gen_amountFormat_fn(totalFee: Number?): String {
        if (totalFee == null) {
            return "0"
        } else {
            return (totalFee / 100).toFixed(2)
        }
    }
    open var checkProvider = ::gen_checkProvider_fn
    open fun gen_checkProvider_fn(provider: String): Boolean {
        var currentProviders = this.insideData.getArray<String>("currentProviders")
        return if (currentProviders != null && currentProviders.indexOf(provider) > -1) {
            true
        } else {
            false
        }
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
                return utsMapOf("uni-pay" to padStyleMapOf(utsMapOf("flex" to 1, "display" to "flex", "flexDirection" to "column", "backgroundColor" to "#f3f3f3")), "mobile-pay-popup" to padStyleMapOf(utsMapOf("flex" to 1)), "mobile-pay-popup-amount-box" to utsMapOf(".mobile-pay-popup " to utsMapOf("backgroundColor" to "#ffffff", "paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15)), "mobile-pay-popup-amount" to utsMapOf(".mobile-pay-popup .mobile-pay-popup-amount-box " to utsMapOf("marginTop" to 10)), "text" to utsMapOf(".mobile-pay-popup .mobile-pay-popup-amount-box .mobile-pay-popup-amount " to utsMapOf("color" to "#e43d33", "fontSize" to 30), ".mobile-pay-popup .mobile-pay-popup-provider-list .uni-list .uni-list-item .uni-list-item__content " to utsMapOf("color" to "#3b4144", "fontSize" to 14)), "mobile-pay-popup-provider-list" to utsMapOf(".mobile-pay-popup " to utsMapOf("backgroundColor" to "#ffffff", "marginTop" to 10)), "uni-list" to utsMapOf(".mobile-pay-popup .mobile-pay-popup-provider-list " to utsMapOf("display" to "flex", "backgroundColor" to "#ffffff", "position" to "relative", "flexDirection" to "column")), "uni-list-item" to utsMapOf(".mobile-pay-popup .mobile-pay-popup-provider-list .uni-list " to utsMapOf("display" to "flex", "position" to "relative", "justifyContent" to "space-between", "alignItems" to "center", "backgroundColor" to "#ffffff", "flexDirection" to "row", "borderBottomWidth" to 1, "borderBottomStyle" to "solid", "borderBottomColor" to "#f8f8f8", "backgroundColor:hover" to "#f1f1f1")), "uni-list-item__container" to utsMapOf(".mobile-pay-popup .mobile-pay-popup-provider-list .uni-list .uni-list-item " to utsMapOf("position" to "relative", "display" to "flex", "flexDirection" to "row", "paddingTop" to 12, "paddingRight" to 15, "paddingBottom" to 12, "paddingLeft" to 15, "flex" to 1, "overflow" to "hidden")), "uni-list-item__header" to utsMapOf(".mobile-pay-popup .mobile-pay-popup-provider-list .uni-list .uni-list-item .uni-list-item__container " to utsMapOf("display" to "flex", "flexDirection" to "row", "alignItems" to "center")), "image" to utsMapOf(".mobile-pay-popup .mobile-pay-popup-provider-list .uni-list .uni-list-item .uni-list-item__container .uni-list-item__header " to utsMapOf("width" to 26, "height" to 26, "marginRight" to 9)), "container--right" to utsMapOf(".mobile-pay-popup .mobile-pay-popup-provider-list .uni-list .uni-list-item " to utsMapOf("paddingRight" to 0)), "uni-list-item__content" to utsMapOf(".mobile-pay-popup .mobile-pay-popup-provider-list .uni-list .uni-list-item " to utsMapOf("display" to "flex", "paddingRight" to 8, "flex" to 1, "flexDirection" to "column", "justifyContent" to "space-between", "overflow" to "hidden")), "uni-list-item__content--center" to utsMapOf(".mobile-pay-popup .mobile-pay-popup-provider-list .uni-list .uni-list-item " to utsMapOf("justifyContent" to "center")), "arrowright" to utsMapOf(".mobile-pay-popup .mobile-pay-popup-provider-list .uni-list .uni-list-item " to utsMapOf("borderTopWidth" to 1, "borderTopStyle" to "solid", "borderTopColor" to "#bbbbbb", "borderRightWidth" to 1, "borderRightStyle" to "solid", "borderRightColor" to "#bbbbbb", "width" to 8, "height" to 8, "marginRight" to 15, "transform" to "rotate(45deg)")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
