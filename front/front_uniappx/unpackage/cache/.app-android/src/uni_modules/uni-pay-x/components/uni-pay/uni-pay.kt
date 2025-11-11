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
import io.dcloud.uniapp.extapi.getDeviceInfo as uni_getDeviceInfo
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo
import io.dcloud.uniapp.extapi.requestPayment as uni_requestPayment
import io.dcloud.uniapp.extapi.showModal as uni_showModal
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenUniModulesUniPayXComponentsUniPayUniPay : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {
        onMounted(fun() {
            var insideData: UTSJSONObject = let {
                object : UTSJSONObject(UTSSourceMapPosition("insideData", "uni_modules/uni-pay-x/components/uni-pay/uni-pay.uvue", 245, 8)) {
                    var images = it.images as UTSJSONObject
                    var originalRroviders = it.originalRroviders as UTSArray<String>
                    var currentProviders = it.currentProviders as UTSArray<String>
                }
            }
            this.`$emit`("mounted", insideData)
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_uni_pay_popup = resolveEasyComponent("uni-pay-popup", GenUniModulesUniPayXComponentsUniPayPopupUniPayPopupClass)
        return createElementVNode("view", utsMapOf("class" to "uni-pay"), utsArrayOf(
            if (_ctx.modeCom == "pc") {
                createVNode(_component_uni_pay_popup, utsMapOf("key" to 0, "ref" to "payPopup", "type" to "center", "safe-area" to false), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "pc-pay-popup"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "pc-pay-popup-title"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "text"), "收银台")
                            )),
                            createElementVNode("view", utsMapOf("class" to "pc-pay-popup-flex"), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "pc-pay-popup-qrcode-box"), utsArrayOf(
                                    createElementVNode("image", utsMapOf("class" to "pc-pay-popup-qrcode-image", "src" to _ctx.orderRes["qr_code_image"]), null, 8, utsArrayOf(
                                        "src"
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "pc-pay-popup-amount-box"), utsArrayOf(
                                        createElementVNode("view", utsMapOf("class" to "pc-pay-popup-amount-tips"), utsArrayOf(
                                            if (_ctx.orderRes["provider"] == "wxpay") {
                                                createElementVNode("text", utsMapOf("key" to 0, "class" to "text"), "微信扫一扫付款")
                                            } else {
                                                if (_ctx.orderRes["provider"] == "alipay") {
                                                    createElementVNode("text", utsMapOf("key" to 1, "class" to "text"), "支付宝扫一扫付款")
                                                } else {
                                                    createElementVNode("text", utsMapOf("key" to 2, "class" to "text"), "扫一扫付款")
                                                }
                                            }
                                        )),
                                        createElementVNode("view", utsMapOf("class" to "pc-pay-popup-amount"), utsArrayOf(
                                            createElementVNode("text", utsMapOf("class" to "text"), toDisplayString((_ctx.totalFeeCom / 100).toFixed(2)), 1)
                                        ))
                                    )),
                                    if (isTrue(_ctx.orderRes["qr_code_image"])) {
                                        createElementVNode("view", utsMapOf("key" to 0, "class" to "pc-pay-popup-complete-button"), utsArrayOf(
                                            createElementVNode("button", utsMapOf("type" to "primary", "onClick" to fun(){
                                                _ctx._getOrder()
                                            }), "我已完成支付", 8, utsArrayOf(
                                                "onClick"
                                            ))
                                        ))
                                    } else {
                                        createCommentVNode("v-if", true)
                                    }
                                )),
                                createElementVNode("view", utsMapOf("class" to "pc-pay-popup-provider-list"), utsArrayOf(
                                    if (_ctx.currentProviders.indexOf("wxpay") > -1) {
                                        createElementVNode("view", utsMapOf("key" to 0, "class" to normalizeClass(utsArrayOf(
                                            "pc-pay-popup-provider-item",
                                            if (_ctx.myOptions["provider"] == "wxpay") {
                                                "active"
                                            } else {
                                                ""
                                            }
                                        )), "onClick" to fun(){
                                            _ctx._pcChooseProvider("wxpay")
                                        }), utsArrayOf(
                                            createElementVNode("image", utsMapOf("src" to _ctx.images["wxpay"], "class" to "pc-pay-popup-provider-image"), null, 8, utsArrayOf(
                                                "src"
                                            )),
                                            createElementVNode("text", utsMapOf("class" to "pc-pay-popup-provider-text"), "微信支付")
                                        ), 10, utsArrayOf(
                                            "onClick"
                                        ))
                                    } else {
                                        createCommentVNode("v-if", true)
                                    },
                                    if (_ctx.currentProviders.indexOf("alipay") > -1) {
                                        createElementVNode("view", utsMapOf("key" to 1, "class" to normalizeClass(utsArrayOf(
                                            "pc-pay-popup-provider-item",
                                            if (_ctx.myOptions["provider"] == "alipay") {
                                                "active"
                                            } else {
                                                ""
                                            }
                                        )), "onClick" to fun(){
                                            _ctx._pcChooseProvider("alipay")
                                        }), utsArrayOf(
                                            createElementVNode("image", utsMapOf("src" to _ctx.images["alipay"], "class" to "pc-pay-popup-provider-image"), null, 8, utsArrayOf(
                                                "src"
                                            )),
                                            createElementVNode("text", utsMapOf("class" to "pc-pay-popup-provider-text"), "支付宝支付")
                                        ), 10, utsArrayOf(
                                            "onClick"
                                        ))
                                    } else {
                                        createCommentVNode("v-if", true)
                                    },
                                    createElementVNode("view", utsMapOf("class" to "pc-pay-popup-logo"), utsArrayOf(
                                        createElementVNode("image", utsMapOf("class" to "image", "src" to _ctx.logo, "mode" to "widthFix"), null, 8, utsArrayOf(
                                            "src"
                                        ))
                                    ))
                                ))
                            ))
                        ))
                    )
                }), "_" to 1), 512)
            } else {
                createVNode(_component_uni_pay_popup, utsMapOf("key" to 1, "ref" to "payPopup", "type" to "bottom", "safe-area" to false), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "mobile-pay-popup", "style" to normalizeStyle("min-height: " + _ctx.height + ";")), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "mobile-pay-popup-title"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "text"), "收银台")
                            )),
                            createElementVNode("view", utsMapOf("class" to "mobile-pay-popup-amount-box"), utsArrayOf(
                                createElementVNode("view", null, utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "text"), "待支付金额：")
                                )),
                                createElementVNode("view", utsMapOf("class" to "mobile-pay-popup-amount"), utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "text"), toDisplayString((_ctx.totalFeeCom / 100).toFixed(2)), 1)
                                ))
                            )),
                            createElementVNode("view", utsMapOf("class" to "mobile-pay-popup-provider-list"), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "uni-list"), utsArrayOf(
                                    if (_ctx.currentProviders.indexOf("wxpay") > -1) {
                                        createElementVNode("view", utsMapOf("key" to 0, "class" to "uni-list-item", "onClick" to fun(){
                                            _ctx.createOrderByProvider("wxpay")
                                        }), utsArrayOf(
                                            createElementVNode("view", utsMapOf("class" to "uni-list-item__container container--right"), utsArrayOf(
                                                createElementVNode("view", utsMapOf("class" to "uni-list-item__header"), utsArrayOf(
                                                    createElementVNode("image", utsMapOf("src" to _ctx.images["wxpay"], "class" to "image"), null, 8, utsArrayOf(
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
                                    if (_ctx.currentProviders.indexOf("alipay") > -1) {
                                        createElementVNode("view", utsMapOf("key" to 1, "class" to "uni-list-item", "onClick" to fun(){
                                            _ctx.createOrderByProvider("alipay")
                                        }), utsArrayOf(
                                            createElementVNode("view", utsMapOf("class" to "uni-list-item__container container--right"), utsArrayOf(
                                                createElementVNode("view", utsMapOf("class" to "uni-list-item__header"), utsArrayOf(
                                                    createElementVNode("image", utsMapOf("src" to _ctx.images["alipay"], "class" to "image"), null, 8, utsArrayOf(
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
                        ), 4)
                    )
                }
                ), "_" to 1), 512)
            }
            ,
            createVNode(_component_uni_pay_popup, utsMapOf("ref" to "qrcodePopup", "type" to "center", "safe-area" to false, "animation" to false, "mask-click" to false, "onClose" to _ctx.clearQrcode), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                return utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "qrcode-popup-content"), utsArrayOf(
                        createElementVNode("image", utsMapOf("src" to _ctx.orderRes["qr_code_image"], "class" to "qrcode-image"), null, 8, utsArrayOf(
                            "src"
                        )),
                        createElementVNode("view", utsMapOf("class" to "qrcode-popup-info"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "qrcode-popup-info-fee-box"), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "qrcode-popup-info-fee"), utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "text"), toDisplayString((_ctx.totalFeeCom / 100).toFixed(2)), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "qrcode-popup-info-fee-unit"), utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "text"), "元")
                                ))
                            )),
                            if (_ctx.myOptions["provider"] == "wxpay") {
                                createElementVNode("view", utsMapOf("key" to 0), utsArrayOf(
                                    createElementVNode("text", null, "请用微信扫码支付")
                                ))
                            } else {
                                if (_ctx.myOptions["provider"] == "alipay") {
                                    createElementVNode("view", utsMapOf("key" to 1), utsArrayOf(
                                        createElementVNode("text", null, "请用支付宝扫码支付")
                                    ))
                                } else {
                                    createCommentVNode("v-if", true)
                                }
                            }
                        )),
                        createElementVNode("button", utsMapOf("type" to "primary", "onClick" to fun(){
                            _ctx._getOrder()
                        }
                        , "class" to "qrcode-popup-btn-primary"), "我已完成支付", 8, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("view", utsMapOf("class" to "qrcode-popup-cancel", "onClick" to fun(){
                            _ctx.closePopup("qrcodePopup")
                        }
                        ), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "qrcode-popup-cancel-text"), "暂不支付")
                        ), 8, utsArrayOf(
                            "onClick"
                        ))
                    ))
                )
            }
            ), "_" to 1), 8, utsArrayOf(
                "onClose"
            )),
            createVNode(_component_uni_pay_popup, utsMapOf("ref" to "payConfirmPopup", "type" to "center", "safe-area" to false, "animation" to false, "mask-click" to false), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                return utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "pay-confirm-popup-content"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "pay-confirm-popup-title"), utsArrayOf(
                            createElementVNode("text", null, "请确认支付是否已完成")
                        )),
                        createElementVNode("view", null, utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "onClick" to fun(){
                                _ctx._getOrder()
                            }
                            ), "已完成支付", 8, utsArrayOf(
                                "onClick"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "pay-confirm-popup-refresh"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "default", "onClick" to fun(){
                                _ctx._afreshPayment()
                            }
                            ), "支付遇到问题，重新支付", 8, utsArrayOf(
                                "onClick"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "pay-confirm-popup-cancel", "onClick" to fun(){
                            _ctx.closePopup("payConfirmPopup")
                        }
                        ), utsArrayOf(
                            createElementVNode("text", null, "暂不支付")
                        ), 8, utsArrayOf(
                            "onClick"
                        ))
                    ))
                )
            }
            ), "_" to 1), 512)
        ))
    }
    open var adpid: String by `$props`
    open var toSuccessPage: Boolean by `$props`
    open var returnUrl: String by `$props`
    open var mainColor: String by `$props`
    open var mode: String by `$props`
    open var logo: String by `$props`
    open var height: String by `$props`
    open var debug: Boolean by `$props`
    open var myOptions: UTSJSONObject by `$data`
    open var orderRes: UTSJSONObject by `$data`
    open var images: UTSJSONObject by `$data`
    open var originalRroviders: UTSArray<String> by `$data`
    open var currentProviders: UTSArray<String> by `$data`
    open var openid: String by `$data`
    open var modeCom: String by `$data`
    open var totalFeeCom: Number by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("myOptions" to UTSJSONObject(), "orderRes" to object : UTSJSONObject() {
            var order = ""
            var order_no = ""
            var out_trade_no = ""
            var provider = ""
            var provider_pay_type = ""
            var errCode: Number = 0
        }, "images" to object : UTSJSONObject() {
            var wxpay = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAAAXNSR0IArs4c6QAABC9JREFUeF7tWk1a20AMlUzv0bDr13AAYAOcpLCBcoqQU1DYEE6C2QAHIP26q3sPPOqniU2cZMYj+SeGxN5kEXlm9ObpjaQxwpY/uOX+Qw9Az4AtR6APgS0nQC+CfQi0FQLfrvcHXwAGPP4bQMK/fy5f7O9HehphwPfb/dOIogEhHQHBcamDCDESPoIxMQPTNSi1ABj+OrwDpNMaO5og4P2bMZOugFADwNTewWhU0/FVzAgnKZnxuoFQAbB3vX9MET7U2PHgq4R09vv8ZRI0bMhADMDw9uAhGN8NLQrWyAYRAGt1PgcRIU5TOms7JIIAdOL8nElJauikTRBKAdi7ObwioFFTzHaMw3mBzRV8DwKOXy+ertpagxcAq/YR/g2d6TlNrUDu4EiiE0Why4T1rgyINoXRC4DgjE+mF8+7RYAkp4RrRyVztRUKTgCkuz89fz4pAiB5z7WbklBrKxScAEgWxI6joZPXy5c4B0H0nkPdhzcHFIxxhHgZ8OA7AgMnAMObA479UnF6H5twQpF5RBMdibPDvB4AAAL6IZ0rNbTb9IngAyC8IwJ0K5okQBgzqFEKSV4wcXg17bxl8fIiJXFc0bHAgYLjYlHEFaZlVUQDoAIbcVZaN1VRrgAgUfImASiKW6Yh4pAohmHVQqpLABI0dMYiKhJPCeoV0ueuQsDmEJrkSeJ/bqNJnOqfApqVzWznzrdYWkvzhnUDYGnPKLTdV5gpfLiOqJUIaTefF8RKH6wxtAOX2IdA8NcCmmRItmBLfVF5jRBnR58kGQtWlGUJlBeAxpQ5A4eFKTu/ufLzPQv1f2mRRDiZ/nyyYwYrypI0OlQOc/9PsgshDsh2v+BUwTFnD3K5DglVlD4WlDZEsqywNgiK2F9gQBkLi7EtyV59WhBsiTURCjy5QZMgYRn9cxbZWgCQ+IKlnH2sFQYTURHmCYMgAJaKs9aYPkXNXGK6QhQdt9xeC4UhTC+eV/wVASASmrKj6IMA4NIBMQDsX1VN4IlbuU0K7vmiQS0G5EOpmiW6I1Dpjtp8pYc5yxYVj0RtXcMJcwDFSiqYLh2x+QgqAJwnAuEEydxbkZtdj+fKPVfwbPIq7KngqvMVX4WoAmDBAcH9HTMmXw23s0LJSlPOOsZx0l8VAu/0Fzjuc2Td3aY5zf1VoZgBvPgmvuhoIrFSMSXQThcDoJo0YLxGLfBSv5IINgVC1XxCOb/oZrkTBtRJqkQgKG6ROgPgPbGq/6HVIiYK51WngAj5ikbBhoZi3FALbHmozhlQXFChTc75g6wRM2ufzb9N/IwMcG0wg8HZJf9HBF/tFZnBBBH+cW/BpBDnd4XLDNJcon4oBiiY7jS194mEI0IaSz+12ygAclSYEcXvFsqA3UgANEzqAdCgtYm2PQM2cVc1PvUM0KC1ibY9AzZxVzU+bT0D/gPs/oxfcUEcJAAAAABJRU5ErkJggg=="
            var alipay = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAAAXNSR0IArs4c6QAAA2FJREFUeF7tmU122jAQx2cMB2hp9yULeK+naHISwhJyiIRDBJaQkwRO0fdgEWff0h4ANH0yFc+m+hhbckKNvJUsaX76z4ckhAv/8MLthwggKuDCCUQXuHABxCAYXSC6wIUTiC7wFgL4MPveTaj9optrO+696ya8yeQRQFRAdIEYAxoVBD/PNtdCwHWpDIJwr+1PMCk1DgAkCSx/jHrLsv/p+lfKAp3HzQOYDAqxKtcYBJPtXe/B1Y3TXgmATGst0WIrgAC7JmBINOQsNN8HE0zfVQFlFxzrgFgHNLQOkNJuQ7vrcgkS1CXEua5fgnDj+l+172CX/h59Tbn9Of0qBUE1cGe2ngPhLWeiEH0krFDBT63HC8Cnx/VtFuFDfgl90UOldDvuX4WcSo7lBSD0YuR4H6ebZwRNkRUw9xdSah1G+IzZmW5IW7ERDX/e9Rc+YwerBEMvQo1nrhfqkf/ZuYCxxK5J/t4AjkFQBi71CXxFoFSWq2XTlkn+AndXodNf5SwgT4J7gnttoNL6BqUEmCZET/tkvzQZYj5g1Sf/0goIkfcJYIkEK5HsFnkYnen6BXQptUb5lwJgMz4zCihXD/BqAwVDuoy+Uqx399kACrkZaYECVjY5qxJZXpoQwrcDHB6UghfVvPssAMo35W4R7oZVg5EMmAJxUCZ2CNzfVJ2Pm6qdleDBNwFClaHZdRrQAEhWe25VSPCuAMo1tnQhdIzMNUixfDYBqAOGVQEKQB15OFMCwXPV3QsFww7g73E39Pudr/Gn0EyplQPXCkBF/5AKsBtPKRA+AdKAEx/0BhYLL9nHFkhZLiBvbkOcxFzG5wPtoe7gBUrrTiMttqO+8ebZCkAtWErs17jHvrrSLcj+lkCpKeV5g/ABIA05lqgVM4Er2nPhZgev7DHGnToLG+ALIC9budgWwoRzyuMUPlzj8waVBuELIFOB5iksi7xIKQh8PS4wu8/j+a3vBScbRAgABVfg5BZbH6SFgP0kVIl7UCjNja4RCkAGwecaPLDhp4yNsSYkADlp/mncdNLLu8fpud9XQK7//wERGoBrAefSfgRBsLI9pTtPg+diUNV1yLuJypVg1Un/p/8arwDXZkQALkJNb48KaPoOu+yLCnARanp7VEDTd9hlX1SAi1DT2/8AaakVXysj5qkAAAAASUVORK5CYII="
        }, "originalRroviders" to utsArrayOf<String>("wxpay", "alipay"), "currentProviders" to utsArrayOf<String>("wxpay", "alipay"), "openid" to "", "modeCom" to computed<String>(fun(): String {
            var mode = this.mode as String
            if (mode != "") {
                return mode
            }
            var systemInfo = uni_getDeviceInfo(null)
            return if (systemInfo.deviceType == "pc") {
                "pc"
            } else {
                "mobile"
            }
        }
        ), "totalFeeCom" to computed<Number>(fun(): Number {
            var totalFee = this.myOptions.getNumber("total_fee")
            return if (totalFee != null) {
                totalFee
            } else {
                0
            }
        }
        ))
    }
    open var open = ::gen_open_fn
    open fun gen_open_fn(options: UTSJSONObject): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                var provider = options["provider"] as String?
                if (provider != null && provider != "") {
                    var providers: UTSArray<String> = utsArrayOf()
                    this.originalRroviders.map(fun(item: String){
                        if (provider == item) {
                            providers.push(item)
                        }
                    })
                    this.currentProviders = providers
                    options["provider"] = ""
                } else {
                    this.currentProviders = UTSAndroid.consoleDebugError(JSON.parse(JSON.stringify(this.originalRroviders)), " at uni_modules/uni-pay-x/components/uni-pay/uni-pay.uvue:266") as UTSArray<String>
                }
                this.myOptions = options
                if (this.currentProviders.length == 1) {
                    this.createOrder(let {
                        object : UTSJSONObject() {
                            var provider = it.currentProviders[0]
                        }
                    })
                } else {
                    if (this.modeCom == "pc") {
                        this._pcChooseProvider(this.currentProviders[0])
                    }
                    this.openPopup("payPopup")
                }
        })
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
    open var createOrder = ::gen_createOrder_fn
    open fun gen_createOrder_fn(data: UTSJSONObject): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                var options = this.myOptions
                options["qr_code"] = false
                options = objectAssign(options, data)
                if (options["provider"] == "appleiap") {
                    uni_showModal(ShowModalOptions(title = "提示", content = "请在iOS系统中执行", showCancel = false))
                }
                if (options["provider"] == "wxpay") {
                }
                var createOrderData: UTSJSONObject = object : UTSJSONObject(UTSSourceMapPosition("createOrderData", "uni_modules/uni-pay-x/components/uni-pay/uni-pay.uvue", 320, 9)) {
                    var provider = options["provider"]
                    var total_fee = options.getNumber("total_fee")
                    var order_no = options["order_no"]
                    var out_trade_no = options["out_trade_no"]
                    var description = options["description"]
                    var type = options["type"]
                    var qr_code = options.getBoolean("qr_code")
                    var custom = options.getJSON("custom")
                    var other = options.getJSON("other")
                    var wxpay_virtual = options.getJSON("wxpay_virtual")
                }
                try {
                    val uniPayCo = uniCloud.importObject("uni-pay-co", GenCloudObjUniPayCo::class.java)
                    var res = await(uniPayCo.createOrder(createOrderData))
                    if (res["errCode"] == 0) {
                        this.`$emit`("create", res)
                        if (res["qr_code"] != null && res["qr_code"] == true && options.getBoolean("cancel_popup") != true) {
                            this.orderRes = res
                            if (this.modeCom == "pc") {
                                this.openPopup("payPopup")
                                this._pcChooseProvider(options["provider"] as String)
                            } else {
                                this.openPopup("qrcodePopup")
                            }
                        } else {
                            this.orderPayment(res)
                        }
                    } else {
                        this.`$emit`("fail", res)
                    }
                }
                 catch (err: Throwable) {
                    this.`$emit`("fail", object : UTSJSONObject() {
                        var errCode: Number = -1
                        var errMsg = (err as UTSError).message
                    })
                }
        })
    }
    open var orderPayment = ::gen_orderPayment_fn
    open fun gen_orderPayment_fn(res: UTSJSONObject) {
        this.orderRes = res
        if (res["qr_code"] != null && res["qr_code"] != "") {
            this.`$emit`("qrcode", res)
        }
        var _order = res.get("order")
        var orderStr = if (UTSAndroid.`typeof`(_order) == "string") {
            _order as String
        } else {
            JSON.stringify(_order) as String
        }
        console.log("orderStr: ", orderStr, " at uni_modules/uni-pay-x/components/uni-pay/uni-pay.uvue:407")
        uni_requestPayment(RequestPaymentOptions(provider = res["provider"] as String, orderInfo = orderStr, success = fun(res){
            console.log("requestPaymentSuccess", JSON.stringify(res), " at uni_modules/uni-pay-x/components/uni-pay/uni-pay.uvue:415")
            this._getOrder()
        }
        , fail = fun(err){
            console.log("requestPaymentFail", JSON.stringify(err), " at uni_modules/uni-pay-x/components/uni-pay/uni-pay.uvue:419")
            var errCode = err.errCode
            var errMsg = err.errMsg
            if (errCode == 700601 || errMsg.indexOf("fail cancel") > -1) {
                this.`$emit`("cancel", UTSJSONObject(Map<String, Any?>(utsArrayOf(
                    utsArrayOf(
                        "errCode",
                        errCode
                    ),
                    utsArrayOf(
                        "errMsg",
                        errMsg
                    )
                ))))
            } else {
                console.error("uni.requestPayment:fail", err, " at uni_modules/uni-pay-x/components/uni-pay/uni-pay.uvue:430")
                this.`$emit`("fail", UTSJSONObject(Map<String, Any?>(utsArrayOf(
                    utsArrayOf(
                        "errCode",
                        errCode
                    ),
                    utsArrayOf(
                        "errMsg",
                        errMsg
                    )
                ))))
            }
        }
        ))
    }
    open var openPopup = ::gen_openPopup_fn
    open fun gen_openPopup_fn(name: String) {
        var popupRef = this.`$refs`[name] as UniPayPopupComponentPublicInstance
        popupRef.open()
    }
    open var closePopup = ::gen_closePopup_fn
    open fun gen_closePopup_fn(name: String) {
        var popupRef = this.`$refs`[name] as UniPayPopupComponentPublicInstance
        popupRef.close()
    }
    open var getOrder = ::gen_getOrder_fn
    open fun gen_getOrder_fn(data: UTSJSONObject): UTSPromise<UTSJSONObject> {
        return wrapUTSPromise(suspend w@{
                try {
                    val uniPayCo = uniCloud.importObject("uni-pay-co", GenCloudObjUniPayCo::class.java)
                    var res = await(uniPayCo.getOrder(data))
                    return@w res
                }
                 catch (err: Throwable) {
                    return@w object : UTSJSONObject() {
                        var errCode: Number = -1
                        var errMsg = (err as UTSError).message
                    }
                }
        })
    }
    open var refund = ::gen_refund_fn
    open fun gen_refund_fn(data: UTSJSONObject): UTSPromise<UTSJSONObject> {
        return wrapUTSPromise(suspend w@{
                try {
                    val uniPayCo = uniCloud.importObject("uni-pay-co", GenCloudObjUniPayCo::class.java)
                    var res = await(uniPayCo.refund(data))
                    return@w res
                }
                 catch (err: Throwable) {
                    return@w object : UTSJSONObject() {
                        var errCode: Number = -1
                        var errMsg = (err as UTSError).message
                    }
                }
        })
    }
    open var getRefund = ::gen_getRefund_fn
    open fun gen_getRefund_fn(data: UTSJSONObject): UTSPromise<UTSJSONObject> {
        return wrapUTSPromise(suspend w@{
                try {
                    val uniPayCo = uniCloud.importObject("uni-pay-co", GenCloudObjUniPayCo::class.java)
                    var res = await(uniPayCo.getRefund(data))
                    return@w res
                }
                 catch (err: Throwable) {
                    return@w object : UTSJSONObject() {
                        var errCode: Number = -1
                        var errMsg = (err as UTSError).message
                    }
                }
        })
    }
    open var closeOrder = ::gen_closeOrder_fn
    open fun gen_closeOrder_fn(data: UTSJSONObject): UTSPromise<UTSJSONObject> {
        return wrapUTSPromise(suspend w@{
                try {
                    val uniPayCo = uniCloud.importObject("uni-pay-co", GenCloudObjUniPayCo::class.java)
                    var res = await(uniPayCo.closeOrder(data))
                    return@w res
                }
                 catch (err: Throwable) {
                    return@w object : UTSJSONObject() {
                        var errCode: Number = -1
                        var errMsg = (err as UTSError).message
                    }
                }
        })
    }
    open var getPayProviderFromCloud = ::gen_getPayProviderFromCloud_fn
    open fun gen_getPayProviderFromCloud_fn(data: UTSJSONObject): UTSPromise<UTSJSONObject> {
        return wrapUTSPromise(suspend w@{
                try {
                    val uniPayCo = uniCloud.importObject("uni-pay-co", GenCloudObjUniPayCo::class.java)
                    var res = await(uniPayCo.getPayProviderFromCloud(data))
                    return@w res
                }
                 catch (err: Throwable) {
                    return@w object : UTSJSONObject() {
                        var errCode: Number = -1
                        var errMsg = (err as UTSError).message
                    }
                }
        })
    }
    open var getProviderAppId = ::gen_getProviderAppId_fn
    open fun gen_getProviderAppId_fn(data: UTSJSONObject): UTSPromise<UTSJSONObject> {
        return wrapUTSPromise(suspend w@{
                try {
                    val uniPayCo = uniCloud.importObject("uni-pay-co", GenCloudObjUniPayCo::class.java)
                    var res = await(uniPayCo.getProviderAppId(data))
                    return@w res
                }
                 catch (err: Throwable) {
                    return@w object : UTSJSONObject() {
                        var errCode: Number = -1
                        var errMsg = (err as UTSError).message
                    }
                }
        })
    }
    open var getOpenid = ::gen_getOpenid_fn
    open fun gen_getOpenid_fn(data: UTSJSONObject): UTSPromise<UTSJSONObject> {
        return wrapUTSPromise(suspend w@{
                try {
                    val uniPayCo = uniCloud.importObject("uni-pay-co", GenCloudObjUniPayCo::class.java)
                    var res = await(uniPayCo.getOpenid(data))
                    return@w res
                }
                 catch (err: Throwable) {
                    return@w object : UTSJSONObject() {
                        var errCode: Number = -1
                        var errMsg = (err as UTSError).message
                    }
                }
        })
    }
    open var verifyReceiptFromAppleiap = ::gen_verifyReceiptFromAppleiap_fn
    open fun gen_verifyReceiptFromAppleiap_fn(data: UTSJSONObject): UTSPromise<UTSJSONObject> {
        return wrapUTSPromise(suspend w@{
                try {
                    val uniPayCo = uniCloud.importObject("uni-pay-co", GenCloudObjUniPayCo::class.java)
                    var res = await(uniPayCo.verifyReceiptFromAppleiap(data))
                    return@w res
                }
                 catch (err: Throwable) {
                    return@w object : UTSJSONObject() {
                        var errCode: Number = -1
                        var errMsg = (err as UTSError).message
                    }
                }
        })
    }
    open var paySuccess = ::gen_paySuccess_fn
    open fun gen_paySuccess_fn(res: UTSJSONObject) {
        this.closePopup("payPopup")
        this.closePopup("payConfirmPopup")
        this.clearQrcode()
        var toSuccessPage = this.toSuccessPage as Boolean
        if (toSuccessPage) {
            this.pageToSuccess(res)
        }
        this.`$emit`("success", res)
    }
    open var pageToSuccess = ::gen_pageToSuccess_fn
    open fun gen_pageToSuccess_fn(res: UTSJSONObject) {
        var out_trade_no = res["out_trade_no"] as String
        var pay_order = res.getJSON("pay_order") as UTSJSONObject
        var order_no = pay_order["order_no"] as String
        var total_fee = pay_order.getNumber("total_fee")
        if (total_fee == null) {
            total_fee = 0
        }
        var returnUrl = this.returnUrl as String
        var adpid = this.adpid as String
        var mainColor = this.mainColor as String
        if (this.modeCom != "pc") {
            uni_navigateTo(NavigateToOptions(url = "/uni_modules/uni-pay-x/pages/success/success?out_trade_no=" + out_trade_no + "&order_no=" + order_no + "&total_fee=" + total_fee + "&adpid=" + adpid + "&return_url=" + returnUrl + "&main_color=" + mainColor))
        } else {
            if (returnUrl != "") {
                var url = returnUrl + ("?out_trade_no=" + out_trade_no + "&order_no=" + order_no)
                if (url.indexOf("/") != 0) {
                    url = "/" + url
                }
                uni_navigateTo(NavigateToOptions(url = url))
            }
        }
    }
    open var clearQrcode = ::gen_clearQrcode_fn
    open fun gen_clearQrcode_fn() {
        this.orderRes["codeUrl"] = ""
        this.orderRes["qr_code_image"] = ""
    }
    open var _getOrder = ::gen__getOrder_fn
    open fun gen__getOrder_fn(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                var out_trade_no = this.orderRes["out_trade_no"] as String
                var res = await(this.getOrder(UTSJSONObject(Map<String, Any?>(utsArrayOf(
                    utsArrayOf(
                        "out_trade_no",
                        out_trade_no
                    ),
                    utsArrayOf(
                        "await_notify",
                        true
                    )
                )))))
                if (res["errCode"] == 0) {
                    var has_paid = res.getBoolean("has_paid")
                    if (has_paid != null && has_paid == true) {
                        this.closePopup("qrcodePopup")
                        this.paySuccess(res)
                    }
                }
        })
    }
    open var _afreshPayment = ::gen__afreshPayment_fn
    open fun gen__afreshPayment_fn() {
        this.orderPayment(this.orderRes)
    }
    open var _pcChooseProvider = ::gen__pcChooseProvider_fn
    open fun gen__pcChooseProvider_fn(provider: String) {
        var _provider: String = this.myOptions["provider"] as String
        if (provider != _provider) {
            this.createOrder(UTSJSONObject(Map<String, Any?>(utsArrayOf(
                utsArrayOf(
                    "provider",
                    provider
                )
            ))))
        }
    }
    open var _appleiapCreateOrder = ::gen__appleiapCreateOrder_fn
    open fun gen__appleiapCreateOrder_fn(options: UTSJSONObject): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                uni_showToast(ShowToastOptions(title = "请在iOS系统中打开", icon = "none"))
        })
    }
    open var appleiapRestore = ::gen_appleiapRestore_fn
    open fun gen_appleiapRestore_fn() {}
    companion object {
        var name = "uni-pay"
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("mobile-pay-popup" to padStyleMapOf(utsMapOf("width" to "100%", "minHeight" to 450, "backgroundColor" to "#f3f3f3", "borderTopLeftRadius" to 15, "borderTopRightRadius" to 15, "borderBottomRightRadius" to 0, "borderBottomLeftRadius" to 0, "overflow" to "hidden")), "mobile-pay-popup-title" to utsMapOf(".mobile-pay-popup " to utsMapOf("backgroundColor" to "#ffffff", "paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10)), "text" to utsMapOf(".mobile-pay-popup .mobile-pay-popup-title " to utsMapOf("textAlign" to "center", "fontWeight" to "bold", "fontSize" to 20), ".mobile-pay-popup .mobile-pay-popup-amount-box .mobile-pay-popup-amount " to utsMapOf("color" to "#e43d33", "fontSize" to 30), ".mobile-pay-popup .mobile-pay-popup-provider-list .uni-list .uni-list-item .uni-list-item__content " to utsMapOf("color" to "#3b4144", "fontSize" to 14), ".pc-pay-popup .pc-pay-popup-title " to utsMapOf("textAlign" to "center", "fontWeight" to "bold", "fontSize" to 20, "lineHeight" to "66px"), ".pc-pay-popup .pc-pay-popup-flex .pc-pay-popup-qrcode-box .pc-pay-popup-amount-box .pc-pay-popup-amount-tips " to utsMapOf("textAlign" to "center", "color" to "#333333", "fontSize" to 20), ".pc-pay-popup .pc-pay-popup-flex .pc-pay-popup-qrcode-box .pc-pay-popup-amount-box .pc-pay-popup-amount " to utsMapOf("textAlign" to "center", "color" to "#dd524d", "fontWeight" to "bold", "fontSize" to 32), ".qrcode-popup-content .qrcode-popup-info .qrcode-popup-info-fee-box .qrcode-popup-info-fee " to utsMapOf("color" to "#FF0000", "fontSize" to 30, "fontWeight" to "bold", "textAlign" to "center")), "mobile-pay-popup-amount-box" to utsMapOf(".mobile-pay-popup " to utsMapOf("backgroundColor" to "#ffffff", "paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15)), "mobile-pay-popup-amount" to utsMapOf(".mobile-pay-popup .mobile-pay-popup-amount-box " to utsMapOf("marginTop" to 10)), "mobile-pay-popup-provider-list" to utsMapOf(".mobile-pay-popup " to utsMapOf("backgroundColor" to "#ffffff", "marginTop" to 10)), "uni-list" to utsMapOf(".mobile-pay-popup .mobile-pay-popup-provider-list " to utsMapOf("display" to "flex", "backgroundColor" to "#ffffff", "position" to "relative", "flexDirection" to "column")), "uni-list-item" to utsMapOf(".mobile-pay-popup .mobile-pay-popup-provider-list .uni-list " to utsMapOf("display" to "flex", "position" to "relative", "justifyContent" to "space-between", "alignItems" to "center", "backgroundColor" to "#ffffff", "flexDirection" to "row", "borderBottomWidth" to 1, "borderBottomStyle" to "solid", "borderBottomColor" to "#f8f8f8", "backgroundColor:hover" to "#f1f1f1")), "uni-list-item__container" to utsMapOf(".mobile-pay-popup .mobile-pay-popup-provider-list .uni-list .uni-list-item " to utsMapOf("position" to "relative", "display" to "flex", "flexDirection" to "row", "paddingTop" to 12, "paddingRight" to 15, "paddingBottom" to 12, "paddingLeft" to 15, "flex" to 1, "overflow" to "hidden")), "uni-list-item__header" to utsMapOf(".mobile-pay-popup .mobile-pay-popup-provider-list .uni-list .uni-list-item .uni-list-item__container " to utsMapOf("display" to "flex", "flexDirection" to "row", "alignItems" to "center")), "image" to utsMapOf(".mobile-pay-popup .mobile-pay-popup-provider-list .uni-list .uni-list-item .uni-list-item__container .uni-list-item__header " to utsMapOf("width" to 26, "height" to 26, "marginRight" to 9), ".pc-pay-popup .pc-pay-popup-flex .pc-pay-popup-provider-list .pc-pay-popup-logo " to utsMapOf("width" to 120)), "container--right" to utsMapOf(".mobile-pay-popup .mobile-pay-popup-provider-list .uni-list .uni-list-item " to utsMapOf("paddingRight" to 0)), "uni-list-item__content" to utsMapOf(".mobile-pay-popup .mobile-pay-popup-provider-list .uni-list .uni-list-item " to utsMapOf("display" to "flex", "paddingRight" to 8, "flex" to 1, "flexDirection" to "column", "justifyContent" to "space-between", "overflow" to "hidden")), "uni-list-item__content--center" to utsMapOf(".mobile-pay-popup .mobile-pay-popup-provider-list .uni-list .uni-list-item " to utsMapOf("justifyContent" to "center")), "arrowright" to utsMapOf(".mobile-pay-popup .mobile-pay-popup-provider-list .uni-list .uni-list-item " to utsMapOf("borderTopWidth" to 1, "borderTopStyle" to "solid", "borderTopColor" to "#bbbbbb", "borderRightWidth" to 1, "borderRightStyle" to "solid", "borderRightColor" to "#bbbbbb", "width" to 8, "height" to 8, "marginRight" to 15, "transform" to "rotate(45deg)")), "pc-pay-popup" to padStyleMapOf(utsMapOf("width" to 800, "height" to 600, "backgroundColor" to "#f3f3f3", "borderTopLeftRadius" to 10, "borderTopRightRadius" to 10, "borderBottomRightRadius" to 10, "borderBottomLeftRadius" to 10, "overflow" to "hidden")), "pc-pay-popup-title" to utsMapOf(".pc-pay-popup " to utsMapOf("backgroundColor" to "#ffffff", "height" to 66)), "pc-pay-popup-flex" to utsMapOf(".pc-pay-popup " to utsMapOf("width" to "100%", "display" to "flex", "flexDirection" to "row")), "pc-pay-popup-qrcode-box" to utsMapOf(".pc-pay-popup .pc-pay-popup-flex " to utsMapOf("height" to 534, "flex" to 1, "backgroundColor" to "#ffffff", "display" to "flex", "flexDirection" to "column", "justifyContent" to "center", "alignItems" to "center")), "pc-pay-popup-qrcode-image" to utsMapOf(".pc-pay-popup .pc-pay-popup-flex .pc-pay-popup-qrcode-box " to utsMapOf("width" to 225, "height" to 225)), "pc-pay-popup-amount-tips" to utsMapOf(".pc-pay-popup .pc-pay-popup-flex .pc-pay-popup-qrcode-box .pc-pay-popup-amount-box " to utsMapOf("marginTop" to 20)), "pc-pay-popup-amount" to utsMapOf(".pc-pay-popup .pc-pay-popup-flex .pc-pay-popup-qrcode-box .pc-pay-popup-amount-box " to utsMapOf("marginTop" to 20)), "pc-pay-popup-complete-button" to utsMapOf(".pc-pay-popup .pc-pay-popup-flex .pc-pay-popup-qrcode-box " to utsMapOf("marginTop" to 20)), "pc-pay-popup-provider-list" to utsMapOf(".pc-pay-popup .pc-pay-popup-flex " to utsMapOf("width" to 300, "display" to "flex", "flexDirection" to "column")), "pc-pay-popup-provider-item" to utsMapOf(".pc-pay-popup .pc-pay-popup-flex .pc-pay-popup-provider-list " to utsMapOf("paddingTop" to 20, "paddingRight" to 20, "paddingBottom" to 20, "paddingLeft" to 20, "display" to "flex", "flexDirection" to "row", "alignItems" to "center", "backgroundColor:hover" to "#ffffff"), ".pc-pay-popup .pc-pay-popup-flex .pc-pay-popup-provider-list .active" to utsMapOf("backgroundColor" to "#ffffff")), "pc-pay-popup-provider-image" to utsMapOf(".pc-pay-popup .pc-pay-popup-flex .pc-pay-popup-provider-list .pc-pay-popup-provider-item " to utsMapOf("width" to 60, "height" to 60)), "pc-pay-popup-provider-text" to utsMapOf(".pc-pay-popup .pc-pay-popup-flex .pc-pay-popup-provider-list .pc-pay-popup-provider-item " to utsMapOf("color" to "#333333", "fontSize" to 20, "marginLeft" to 10)), "pc-pay-popup-logo" to utsMapOf(".pc-pay-popup .pc-pay-popup-flex .pc-pay-popup-provider-list " to utsMapOf("flex" to 1, "display" to "flex", "flexDirection" to "row", "alignItems" to "center", "justifyContent" to "center")), "qrcode-popup-content" to padStyleMapOf(utsMapOf("width" to 300, "backgroundColor" to "#ffffff", "borderTopLeftRadius" to 5, "borderTopRightRadius" to 5, "borderBottomRightRadius" to 5, "borderBottomLeftRadius" to 5, "paddingTop" to 20, "paddingRight" to 20, "paddingBottom" to 20, "paddingLeft" to 20, "boxSizing" to "border-box", "textAlign" to "center", "display" to "flex", "flexDirection" to "column", "alignItems" to "center", "justifyContent" to "center")), "qrcode-image" to utsMapOf(".qrcode-popup-content " to utsMapOf("width" to 225, "height" to 225)), "qrcode-popup-info" to utsMapOf(".qrcode-popup-content " to utsMapOf("paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10, "display" to "flex", "flexDirection" to "column", "alignItems" to "center")), "qrcode-popup-info-fee-box" to utsMapOf(".qrcode-popup-content .qrcode-popup-info " to utsMapOf("display" to "flex", "flexDirection" to "row", "marginBottom" to 3)), "qrcode-popup-info-fee-unit" to utsMapOf(".qrcode-popup-content .qrcode-popup-info .qrcode-popup-info-fee-box " to utsMapOf("display" to "flex", "flexDirection" to "row", "alignItems" to "flex-end", "paddingBottom" to 5)), "qrcode-popup-btn-primary" to utsMapOf(".qrcode-popup-content " to utsMapOf("width" to 260)), "qrcode-popup-cancel" to utsMapOf(".qrcode-popup-content " to utsMapOf("marginTop" to 10, "width" to 260)), "qrcode-popup-cancel-text" to utsMapOf(".qrcode-popup-content .qrcode-popup-cancel " to utsMapOf("textAlign" to "center")), "pay-confirm-popup-content" to padStyleMapOf(utsMapOf("width" to 275, "backgroundColor" to "#ffffff", "borderTopLeftRadius" to 5, "borderTopRightRadius" to 5, "borderBottomRightRadius" to 5, "borderBottomLeftRadius" to 5, "paddingTop" to 20, "paddingRight" to 20, "paddingBottom" to 20, "paddingLeft" to 20)), "pay-confirm-popup-title" to utsMapOf(".pay-confirm-popup-content " to utsMapOf("textAlign" to "center", "paddingTop" to 10, "paddingRight" to 0, "paddingBottom" to 10, "paddingLeft" to 0, "marginBottom" to 15)), "pay-confirm-popup-refresh" to utsMapOf(".pay-confirm-popup-content " to utsMapOf("marginTop" to 10)), "pay-confirm-popup-cancel" to utsMapOf(".pay-confirm-popup-content " to utsMapOf("marginTop" to 10, "textAlign" to "center")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf("success" to null, "cancel" to null, "fail" to null, "create" to null, "mounted" to null, "qrcode" to null)
        var props = normalizePropsOptions(utsMapOf("adpid" to utsMapOf("type" to "String", "default" to ""), "toSuccessPage" to utsMapOf("type" to "Boolean", "default" to true), "returnUrl" to utsMapOf("type" to "String", "default" to ""), "mainColor" to utsMapOf("type" to "String", "default" to ""), "mode" to utsMapOf("type" to "String", "default" to ""), "logo" to utsMapOf("type" to "String", "default" to "/static/logo.png"), "height" to utsMapOf("type" to utsArrayOf(
            "String"
        ), "default" to "70vh"), "debug" to utsMapOf("type" to "Boolean", "default" to false)))
        var propsNeedCastKeys = utsArrayOf(
            "adpid",
            "toSuccessPage",
            "returnUrl",
            "mainColor",
            "mode",
            "logo",
            "height",
            "debug"
        )
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
