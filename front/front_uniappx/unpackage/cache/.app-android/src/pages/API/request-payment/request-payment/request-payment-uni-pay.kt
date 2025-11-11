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
import io.dcloud.uniapp.extapi.getSystemInfoSync as uni_getSystemInfoSync
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo
import io.dcloud.uniapp.extapi.showModal as uni_showModal
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesAPIRequestPaymentRequestPaymentRequestPaymentUniPay : BasePage {
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
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesAPIRequestPaymentRequestPaymentRequestPaymentUniPay) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesAPIRequestPaymentRequestPaymentRequestPaymentUniPay
            val _cache = __ins.renderCache
            val total_fee = ref("1")
            val order_no = ref("")
            val out_trade_no = ref("")
            val description = ref("测试订单")
            val type = ref("test")
            val openid = ref("")
            val custom = ref(object : UTSJSONObject() {
                var a = "a"
                var b: Number = 1
            })
            val adpid = ref("1000000001")
            val transaction_id = ref("")
            val getOrderRes = ref(UTSJSONObject())
            val useUniCloud = ref(true)
            val errMsg = ref("")
            val payRef = ref<UniPayComponentPublicInstance?>(null)
            val getOrderPopupRef = ref<UniPayPopupComponentPublicInstance?>(null)
            val h5Env = computed(fun(): String {
                return ""
            }
            )
            val isIosAppCom = computed(fun(): Boolean {
                var info = uni_getSystemInfoSync()
                return if (info.uniPlatform == "app" && info.osName == "ios") {
                    true
                } else {
                    false
                }
            }
            )
            val isPcCom = computed(fun(): Boolean {
                var isPC = false
                return isPC
            }
            )
            val open = fun(){
                order_no.value = "test" + Date.now()
                out_trade_no.value = "" + order_no.value + "-1"
                val payInstance = payRef.value as UniPayComponentPublicInstance
                payInstance.open(object : UTSJSONObject() {
                    var total_fee = parseInt(total_fee.value)
                    var order_no = order_no.value
                    var out_trade_no = out_trade_no.value
                    var description = description.value
                    var type = type.value
                    var openid = openid.value
                    var custom = custom.value
                    var qr_code = isPcCom.value
                })
            }
            val createOrder = fun(provider: String){
                order_no.value = "test" + Date.now()
                out_trade_no.value = "" + order_no.value + "-1"
                val payInstance = payRef.value as UniPayComponentPublicInstance
                payInstance.createOrder(UTSJSONObject(Map<String, Any?>(utsArrayOf(
                    utsArrayOf(
                        "provider",
                        provider
                    ),
                    utsArrayOf(
                        "total_fee",
                        parseInt(total_fee.value)
                    ),
                    utsArrayOf(
                        "order_no",
                        order_no.value
                    ),
                    utsArrayOf(
                        "out_trade_no",
                        out_trade_no.value
                    ),
                    utsArrayOf(
                        "description",
                        description.value
                    ),
                    utsArrayOf(
                        "type",
                        type.value
                    ),
                    utsArrayOf(
                        "openid",
                        openid.value
                    ),
                    utsArrayOf(
                        "custom",
                        custom.value
                    ),
                    utsArrayOf(
                        "qr_code",
                        isPcCom.value
                    )
                ))))
            }
            val createQRcode = fun(provider: String){
                order_no.value = "test" + Date.now()
                out_trade_no.value = "" + order_no.value + "-1"
                val payInstance = payRef.value as UniPayComponentPublicInstance
                payInstance.createOrder(UTSJSONObject(Map<String, Any?>(utsArrayOf(
                    utsArrayOf(
                        "provider",
                        provider
                    ),
                    utsArrayOf(
                        "total_fee",
                        parseInt(total_fee.value)
                    ),
                    utsArrayOf(
                        "order_no",
                        order_no.value
                    ),
                    utsArrayOf(
                        "out_trade_no",
                        out_trade_no.value
                    ),
                    utsArrayOf(
                        "description",
                        description.value
                    ),
                    utsArrayOf(
                        "type",
                        type.value
                    ),
                    utsArrayOf(
                        "qr_code",
                        true
                    ),
                    utsArrayOf(
                        "openid",
                        openid.value
                    ),
                    utsArrayOf(
                        "custom",
                        custom.value
                    )
                ))))
            }
            val toPayDesk = fun(){
                order_no.value = "test" + Date.now()
                out_trade_no.value = "" + order_no.value + "-1"
                var options: UTSJSONObject = object : UTSJSONObject(UTSSourceMapPosition("options", "pages/API/request-payment/request-payment/request-payment-uni-pay.uvue", 249, 9)) {
                    var total_fee = parseInt(total_fee.value)
                    var order_no = order_no.value
                    var out_trade_no = out_trade_no.value
                    var description = description.value
                    var type = type.value
                    var openid = openid.value
                    var custom = custom.value
                }
                var optionsStr = UTSAndroid.consoleDebugError(encodeURI(JSON.stringify(options)), " at pages/API/request-payment/request-payment/request-payment-uni-pay.uvue:258")
                uni_navigateTo(NavigateToOptions(url = "/uni_modules/uni-pay-x/pages/pay-desk/pay-desk?options=" + optionsStr))
            }
            val getOrderPopup = fun(key: Boolean){
                val getOrderPopupInstance = getOrderPopupRef.value as UniPayPopupComponentPublicInstance
                if (key) {
                    getOrderPopupInstance.open()
                } else {
                    getOrderPopupInstance.close()
                }
            }
            val getOrder = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        getOrderRes.value = UTSJSONObject()
                        val payInstance = payRef.value as UniPayComponentPublicInstance
                        var getOrderData: UTSJSONObject = object : UTSJSONObject(UTSSourceMapPosition("getOrderData", "pages/API/request-payment/request-payment/request-payment-uni-pay.uvue", 278, 9)) {
                            var await_notify = true
                        }
                        if (transaction_id.value != "") {
                            getOrderData["transaction_id"] = transaction_id.value
                        } else if (out_trade_no.value != "") {
                            getOrderData["out_trade_no"] = out_trade_no.value
                        }
                        var res = await(payInstance.getOrder(getOrderData))
                        if (res["errCode"] == 0) {
                            getOrderRes.value = res.getJSON("pay_order") as UTSJSONObject
                            var obj: UTSJSONObject = object : UTSJSONObject(UTSSourceMapPosition("obj", "pages/API/request-payment/request-payment/request-payment-uni-pay.uvue", 289, 11)) {
                                var `-1` = "已关闭"
                                var `1` = "已支付"
                                var `0` = "未支付"
                                var `2` = "已部分退款"
                                var `3` = "已全额退款"
                            }
                            var status = res["status"] as Number
                            var statusStr = status + ""
                            var title = obj[statusStr] as String
                            uni_showToast(ShowToastOptions(title = title, icon = "none"))
                        }
                })
            }
            val onCreate = fun(res: UTSJSONObject){
                console.log("create: ", res, " at pages/API/request-payment/request-payment/request-payment-uni-pay.uvue:386")
            }
            val onSuccess = fun(res: UTSJSONObject){
                console.log("success: ", res, " at pages/API/request-payment/request-payment/request-payment-uni-pay.uvue:392")
                var user_order_success = res.getBoolean("user_order_success")
                if (user_order_success != null && user_order_success != true) {
                }
            }
            val onFail = fun(err: UTSJSONObject){
                console.log("fail: ", err, " at pages/API/request-payment/request-payment/request-payment-uni-pay.uvue:405")
            }
            val onCancel = fun(err: UTSJSONObject){
                console.log("cancel: ", err, " at pages/API/request-payment/request-payment/request-payment-uni-pay.uvue:411")
                uni_showToast(ShowToastOptions(title = "用户取消了支付", icon = "none"))
            }
            val pageTo = fun(url: String){
                uni_navigateTo(NavigateToOptions(url = url))
            }
            val providerFormat = fun(provider: String?): String {
                if (provider == null) {
                    return ""
                }
                var providerObj: UTSJSONObject = object : UTSJSONObject(UTSSourceMapPosition("providerObj", "pages/API/request-payment/request-payment/request-payment-uni-pay.uvue", 430, 9)) {
                    var wxpay = "微信支付"
                    var alipay = "支付宝支付"
                    var appleiap = "ios内购"
                }
                var providerStr = providerObj[provider] as String
                return providerStr
            }
            val amountFormat = fun(totalFee: Number?): String {
                if (totalFee == null) {
                    return "0"
                } else {
                    return (totalFee / 100).toFixed(2)
                }
            }
            val checkUniCloud = fun(): Boolean {
                var useUniCloudResult = false
                if (UTSAndroid.`typeof`(uniCloud) != "undefined" && UTSAndroid.`typeof`(uniCloud.config) == "object" && uniCloud.config.spaceId != "") {
                    useUniCloudResult = true
                }
                if (!useUniCloudResult) {
                    var errMsgText = "本示例依赖 uniCloud，请先关联服务空间，填写正确的支付配置，支付配置文件地址：/uni_modules/uni-config-center/uniCloud/cloudfunctions/common/uni-config-center/uni-pay/config.js"
                    errMsg.value = errMsgText
                    uni_showModal(ShowModalOptions(title = "提示", content = errMsgText, showCancel = false))
                    console.error(errMsgText, " at pages/API/request-payment/request-payment/request-payment-uni-pay.uvue:461")
                }
                return useUniCloudResult
            }
            onLoad(fun(options){
                console.log("onLoad: ", options, " at pages/API/request-payment/request-payment/request-payment-uni-pay.uvue:468")
                useUniCloud.value = checkUniCloud()
            }
            )
            return fun(): Any? {
                val _component_uni_pay_popup = resolveEasyComponent("uni-pay-popup", GenUniModulesUniPayXComponentsUniPayPopupUniPayPopupClass)
                val _component_uni_pay = resolveEasyComponent("uni-pay", GenUniModulesUniPayXComponentsUniPayUniPayClass)
                return createElementVNode("view", utsMapOf("class" to "app"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-common-mb"), utsArrayOf(
                        createElementVNode("text", null, "如对当前页面的支付示例功能有任何疑问，通过电子邮件：service@dcloud.io 联系我们")
                    )),
                    if (errMsg.value != "") {
                        createElementVNode("view", utsMapOf("key" to 0), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "err-msg"), "注意：" + toDisplayString(errMsg.value), 1)
                        ))
                    } else {
                        createCommentVNode("v-if", true)
                    }
                    ,
                    createElementVNode("view", null, utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "label"), "支付单号："),
                        createElementVNode("view", null, utsArrayOf(
                            createElementVNode("input", utsMapOf("class" to "input", "modelValue" to out_trade_no.value, "onInput" to fun(`$event`: InputEvent){
                                out_trade_no.value = `$event`.detail.value
                            }
                            , "placeholder" to "点击发起支付会自动生成"), null, 40, utsArrayOf(
                                "modelValue",
                                "onInput"
                            ))
                        ))
                    )),
                    createElementVNode("view", null, utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "label"), "支付金额（单位分，100=1元）："),
                        createElementVNode("view", null, utsArrayOf(
                            createElementVNode("input", utsMapOf("class" to "input", "modelValue" to total_fee.value, "onInput" to fun(`$event`: InputEvent){
                                total_fee.value = `$event`.detail.value
                            }
                            ), null, 40, utsArrayOf(
                                "modelValue",
                                "onInput"
                            ))
                        ))
                    )),
                    createElementVNode("button", utsMapOf("class" to "button", "onClick" to fun(){
                        open()
                    }
                    ), "打开收银台（弹窗模式）", 8, utsArrayOf(
                        "onClick"
                    )),
                    if (isTrue(!isPcCom.value)) {
                        createElementVNode("view", utsMapOf("key" to 1), utsArrayOf(
                            createElementVNode("button", utsMapOf("class" to "button", "onClick" to toPayDesk), "打开收银台（新页面模式）")
                        ))
                    } else {
                        createCommentVNode("v-if", true)
                    }
                    ,
                    createElementVNode("button", utsMapOf("class" to "button", "onClick" to fun(){
                        createOrder("wxpay")
                    }
                    ), "发起支付（微信）", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "button", "onClick" to fun(){
                        createOrder("alipay")
                    }
                    ), "发起支付（支付宝）", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "button", "onClick" to fun(){
                        createQRcode("alipay")
                    }
                    ), "APP扫码支付（支付宝）", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "button", "onClick" to fun(){
                        getOrderPopup(true)
                    }
                    ), "查询支付状态", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "button", "onClick" to fun(){
                        pageTo("/uni_modules/uni-pay-x/pages/success/success?out_trade_no=test2024030501-1&order_no=test2024030501&total_fee=1&adpid=1000000001&return_url=/pages/API/request-payment/request-payment/order-detail")
                    }
                    ), "支付成功页面示例", 8, utsArrayOf(
                        "onClick"
                    )),
                    createVNode(_component_uni_pay_popup, utsMapOf("ref_key" to "getOrderPopupRef", "ref" to getOrderPopupRef, "type" to "center"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode("scroll-view", utsMapOf("direction" to "vertical", "class" to "get-order-popup"), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "label"), "插件支付单号："),
                                createElementVNode("view", utsMapOf("class" to "mt20"), utsArrayOf(
                                    createElementVNode("input", utsMapOf("class" to "input pd2030", "modelValue" to out_trade_no.value, "onInput" to fun(`$event`: InputEvent){
                                        out_trade_no.value = `$event`.detail.value
                                    }
                                    , "placeholder" to "请输入"), null, 40, utsArrayOf(
                                        "modelValue",
                                        "onInput"
                                    )),
                                    createElementVNode("view", null, utsArrayOf(
                                        createElementVNode("text", utsMapOf("class" to "tips"), "插件支付单号和第三方交易单号2选1填即可")
                                    ))
                                )),
                                createElementVNode("view", utsMapOf("class" to "label"), "第三方交易单号："),
                                createElementVNode("view", utsMapOf("class" to "mt20"), utsArrayOf(
                                    createElementVNode("input", utsMapOf("class" to "input pd2030", "modelValue" to transaction_id.value, "onInput" to fun(`$event`: InputEvent){
                                        transaction_id.value = `$event`.detail.value
                                    }
                                    , "placeholder" to "请输入"), null, 40, utsArrayOf(
                                        "modelValue",
                                        "onInput"
                                    )),
                                    createElementVNode("view", null, utsArrayOf(
                                        createElementVNode("text", utsMapOf("class" to "tips"), "可从支付宝账单（订单号）、微信账单（交易单号）中复制")
                                    ))
                                )),
                                createElementVNode("view", utsMapOf("class" to "mt20"), utsArrayOf(
                                    createElementVNode("button", utsMapOf("class" to "button", "onClick" to getOrder), "查询支付状态")
                                )),
                                if (isTrue(getOrderRes.value["transaction_id"])) {
                                    createElementVNode("view", utsMapOf("key" to 0, "class" to "mt20"), utsArrayOf(
                                        createElementVNode("view", utsMapOf("class" to "table"), utsArrayOf(
                                            createElementVNode("view", utsMapOf("class" to "table-tr"), utsArrayOf(
                                                createElementVNode("view", utsMapOf("class" to "table-td label"), utsArrayOf(
                                                    createElementVNode("text", utsMapOf("class" to "text align-left"), "订单描述")
                                                )),
                                                createElementVNode("view", utsMapOf("class" to "table-td"), utsArrayOf(
                                                    createElementVNode("text", utsMapOf("class" to "text align-right"), toDisplayString(getOrderRes.value["description"]), 1)
                                                ))
                                            )),
                                            createElementVNode("view", utsMapOf("class" to "table-tr"), utsArrayOf(
                                                createElementVNode("view", utsMapOf("class" to "table-td label"), utsArrayOf(
                                                    createElementVNode("text", utsMapOf("class" to "text align-left"), "支付金额")
                                                )),
                                                createElementVNode("view", utsMapOf("class" to "table-td"), utsArrayOf(
                                                    createElementVNode("text", utsMapOf("class" to "text align-right"), toDisplayString(amountFormat(getOrderRes.value.getNumber("total_fee"))), 1)
                                                ))
                                            )),
                                            createElementVNode("view", utsMapOf("class" to "table-tr"), utsArrayOf(
                                                createElementVNode("view", utsMapOf("class" to "table-td label"), utsArrayOf(
                                                    createElementVNode("text", utsMapOf("class" to "text align-left"), "支付方式")
                                                )),
                                                createElementVNode("view", utsMapOf("class" to "table-td"), utsArrayOf(
                                                    createElementVNode("text", utsMapOf("class" to "text align-right"), toDisplayString(providerFormat(getOrderRes.value["provider"] as String)), 1)
                                                ))
                                            )),
                                            createElementVNode("view", utsMapOf("class" to "table-tr"), utsArrayOf(
                                                createElementVNode("view", utsMapOf("class" to "table-td label"), utsArrayOf(
                                                    createElementVNode("text", utsMapOf("class" to "text align-left"), "第三方交易单号")
                                                )),
                                                createElementVNode("view", utsMapOf("class" to "table-td"), utsArrayOf(
                                                    createElementVNode("text", utsMapOf("class" to "text align-right"), toDisplayString(getOrderRes.value["transaction_id"]), 1)
                                                ))
                                            )),
                                            createElementVNode("view", utsMapOf("class" to "table-tr"), utsArrayOf(
                                                createElementVNode("view", utsMapOf("class" to "table-td label"), utsArrayOf(
                                                    createElementVNode("text", utsMapOf("class" to "text align-left"), "插件支付单号")
                                                )),
                                                createElementVNode("view", utsMapOf("class" to "table-td"), utsArrayOf(
                                                    createElementVNode("text", utsMapOf("class" to "text align-right"), toDisplayString(getOrderRes.value["out_trade_no"]), 1)
                                                ))
                                            )),
                                            createElementVNode("view", utsMapOf("class" to "table-tr"), utsArrayOf(
                                                createElementVNode("view", utsMapOf("class" to "table-td label"), utsArrayOf(
                                                    createElementVNode("text", utsMapOf("class" to "text align-left"), "回调状态")
                                                )),
                                                createElementVNode("view", utsMapOf("class" to "table-td"), utsArrayOf(
                                                    createElementVNode("text", utsMapOf("class" to "text align-right"), toDisplayString(if (getOrderRes.value.getBoolean("user_order_success") != null && getOrderRes.value.getBoolean("user_order_success") == true) {
                                                        "成功"
                                                    } else {
                                                        "异常"
                                                    }), 1)
                                                ))
                                            ))
                                        ))
                                    ))
                                } else {
                                    createCommentVNode("v-if", true)
                                }
                            ))
                        )
                    }
                    ), "_" to 1), 512),
                    createVNode(_component_uni_pay, utsMapOf("ref_key" to "payRef", "ref" to payRef, "adpid" to adpid.value, "height" to "450px", "return-url" to "/pages/API/request-payment/request-payment/order-detail", "logo" to "/static/logo.png", "onSuccess" to onSuccess, "onCreate" to onCreate, "onFail" to onFail, "onCancel" to onCancel), null, 8, utsArrayOf(
                        "adpid"
                    ))
                ))
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ), utsArrayOf(
                GenApp.styles
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("app" to padStyleMapOf(utsMapOf("paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15)), "input" to padStyleMapOf(utsMapOf("borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#f3f3f3", "borderRightColor" to "#f3f3f3", "borderBottomColor" to "#f3f3f3", "borderLeftColor" to "#f3f3f3", "paddingTop" to 5, "paddingRight" to 5, "paddingBottom" to 5, "paddingLeft" to 5, "width" to "100%", "boxSizing" to "border-box", "height" to 40, "backgroundImage" to "none", "backgroundColor" to "#FFFFFF")), "button" to padStyleMapOf(utsMapOf("marginTop" to 10)), "label" to utsMapOf("" to utsMapOf("marginTop" to 5, "marginRight" to 0, "marginBottom" to 5, "marginLeft" to 0), ".table " to utsMapOf("width" to 90)), "tips" to padStyleMapOf(utsMapOf("marginTop" to 10, "marginRight" to 0, "marginBottom" to 10, "marginLeft" to 0, "fontSize" to 12, "color" to "#565656")), "get-order-popup" to padStyleMapOf(utsMapOf("backgroundColor" to "#ffffff", "paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15, "height" to 450, "borderTopLeftRadius" to 10, "borderTopRightRadius" to 10, "borderBottomRightRadius" to 10, "borderBottomLeftRadius" to 10, "width" to "100%")), "mt20" to padStyleMapOf(utsMapOf("marginTop" to 10)), "pd2030" to padStyleMapOf(utsMapOf("paddingTop" to 10, "paddingRight" to 15, "paddingBottom" to 10, "paddingLeft" to 15)), "table-tr" to utsMapOf(".table " to utsMapOf("display" to "flex", "flexDirection" to "row", "justifyContent" to "center", "alignItems" to "center", "paddingTop" to 5, "paddingRight" to 0, "paddingBottom" to 5, "paddingLeft" to 0)), "table-td" to utsMapOf(".table " to utsMapOf("flex" to 1)), "align-left" to utsMapOf(".table " to utsMapOf("textAlign" to "left")), "align-right" to utsMapOf(".table " to utsMapOf("textAlign" to "right")), "text" to utsMapOf(".table " to utsMapOf("fontSize" to 12)), "err-msg" to padStyleMapOf(utsMapOf("color" to "#FF0000", "fontSize" to 14)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
