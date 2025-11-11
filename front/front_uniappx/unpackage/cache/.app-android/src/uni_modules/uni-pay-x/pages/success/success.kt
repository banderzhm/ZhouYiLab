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
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo
open class GenUniModulesUniPayXPagesSuccessSuccess : BasePage {
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
            this.myOptions["adpid"] = options["adpid"]
            this.myOptions["order_no"] = if (options["order_no"] != null) {
                options["order_no"]
            } else {
                "-"
            }
            this.myOptions["out_trade_no"] = options["out_trade_no"]
            if (options["total_fee"] != null) {
                this.myOptions["total_fee"] = parseFloat(options["total_fee"] as String)
            }
            this.myOptions["pay_date"] = options["pay_date"]
            this.myOptions["return_url"] = options["return_url"]
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
        return createElementVNode("view", utsMapOf("class" to "app"), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "header"), utsArrayOf(
                createElementVNode("image", utsMapOf("src" to _ctx.successImages, "class" to "success-image"), null, 8, utsArrayOf(
                    "src"
                )),
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "success-title-text"), "支付成功")
                )),
                createElementVNode("view", utsMapOf("class" to "hr"))
            )),
            createElementVNode("view", utsMapOf("class" to "info-box"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "info-amount"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "text"), "¥ " + toDisplayString(_ctx.amountFormat(_ctx.myOptions.getNumber("total_fee"))), 1)
                )),
                createElementVNode("view", utsMapOf("class" to "left-circle")),
                createElementVNode("view", utsMapOf("class" to "right-circle")),
                createElementVNode("view", utsMapOf("class" to "info-main"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "info-cell"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "left"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "text"), "订单编号")
                        )),
                        createElementVNode("view", utsMapOf("class" to "right"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "text"), toDisplayString(_ctx.myOptions["order_no"]), 1)
                        ))
                    ))
                ))
            )),
            createElementVNode("view", utsMapOf("class" to "uni-ad")),
            if (isTrue(_ctx.myOptions["return_url"])) {
                createElementVNode("view", utsMapOf("key" to 0, "class" to "button-query", "onClick" to _ctx.queryOrder), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "text"), "查看订单")
                ), 8, utsArrayOf(
                    "onClick"
                ))
            } else {
                createCommentVNode("v-if", true)
            }
            ,
            createElementVNode("view", utsMapOf("class" to "footer-hr"))
        ))
    }
    open var myOptions: UTSJSONObject by `$data`
    open var successImages: String by `$data`
    open var color: UTSJSONObject by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("myOptions" to object : UTSJSONObject() {
            var adpid = ""
            var order_no = ""
            var out_trade_no = ""
            var total_fee: Number = 0
            var pay_date = ""
            var return_url = ""
        }, "successImages" to "data:image/png;base64,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", "color" to object : UTSJSONObject() {
            var wxpay = "#01be6e"
            var alipay = "#108ee9"
        })
    }
    open var queryOrder = ::gen_queryOrder_fn
    open fun gen_queryOrder_fn() {
        var return_url = this.myOptions["return_url"] as String
        var out_trade_no = this.myOptions["out_trade_no"] as String
        var order_no = this.myOptions["order_no"] as String
        var url = return_url + ("?out_trade_no=" + out_trade_no + "&order_no=" + order_no)
        if (url.indexOf("/") != 0) {
            url = "/" + url
        }
        uni_navigateTo(NavigateToOptions(url = url))
    }
    open var onaderror = ::gen_onaderror_fn
    open fun gen_onaderror_fn(e: Any) {
        console.log("ad-error", e, " at uni_modules/uni-pay-x/pages/success/success.uvue:86")
    }
    open var amountFormat = ::gen_amountFormat_fn
    open fun gen_amountFormat_fn(totalFee: Number?): String {
        if (totalFee == null || totalFee == 0) {
            return "0"
        } else {
            return (totalFee / 100).toFixed(2)
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
                return utsMapOf("app" to padStyleMapOf(utsMapOf("backgroundColor" to "#f3f3f3", "flex" to 1)), "header" to padStyleMapOf(utsMapOf("backgroundColor" to "#007aff", "display" to "flex", "alignItems" to "center", "paddingTop" to 40, "paddingRight" to 15, "paddingBottom" to 25, "paddingLeft" to 15)), "success-image" to utsMapOf(".header " to utsMapOf("width" to 60, "height" to 60)), "success-title-text" to utsMapOf(".header " to utsMapOf("color" to "#ffffff", "fontSize" to 17, "marginTop" to 20, "fontWeight" to "bold")), "hr" to utsMapOf(".header " to utsMapOf("marginTop" to 20, "width" to "100%", "height" to 15, "borderTopLeftRadius" to 10, "borderTopRightRadius" to 10, "borderBottomRightRadius" to 10, "borderBottomLeftRadius" to 10, "opacity" to 0.1, "backgroundColor" to "#000000")), "info-box" to padStyleMapOf(utsMapOf("marginTop" to -32, "marginRight" to 25, "marginBottom" to 0, "marginLeft" to 25, "position" to "relative", "backgroundColor" to "#ffffff")), "info-amount" to utsMapOf(".info-box " to utsMapOf("height" to 75, "borderBottomWidth" to 2, "borderBottomStyle" to "dashed", "borderBottomColor" to "#f3f3f3")), "text" to utsMapOf(".info-box .info-amount " to utsMapOf("lineHeight" to "75px", "color" to "#007aff", "fontWeight" to "bold", "fontSize" to 30, "textAlign" to "center"), ".info-box .info-main .info-cell .left " to utsMapOf("textAlign" to "left", "fontSize" to 13, "color" to "#333333"), ".info-box .info-main .info-cell .right " to utsMapOf("textAlign" to "right", "fontSize" to 13, "color" to "#333333"), ".button-query " to utsMapOf("color" to "#ffffff", "textAlign" to "center")), "left-circle" to utsMapOf(".info-box " to utsMapOf("backgroundColor" to "#f3f3f3", "position" to "absolute", "width" to 20, "height" to 20, "borderTopLeftRadius" to 10, "borderTopRightRadius" to 10, "borderBottomRightRadius" to 10, "borderBottomLeftRadius" to 10, "top" to 65, "left" to -10)), "right-circle" to utsMapOf(".info-box " to utsMapOf("backgroundColor" to "#f3f3f3", "position" to "absolute", "width" to 20, "height" to 20, "borderTopLeftRadius" to 10, "borderTopRightRadius" to 10, "borderBottomRightRadius" to 10, "borderBottomLeftRadius" to 10, "top" to 65, "right" to -10)), "info-main" to utsMapOf(".info-box " to utsMapOf("paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15)), "info-cell" to utsMapOf(".info-box .info-main " to utsMapOf("display" to "flex", "flexDirection" to "row")), "left" to utsMapOf(".info-box .info-main .info-cell " to utsMapOf("width" to 100)), "right" to utsMapOf(".info-box .info-main .info-cell " to utsMapOf("flex" to 1)), "uni-ad" to padStyleMapOf(utsMapOf("marginTop" to 25, "minHeight" to 50)), "ad-interactive" to utsMapOf(".uni-ad " to utsMapOf("textAlign" to "center")), "button-query" to padStyleMapOf(utsMapOf("backgroundColor" to "#007aff", "marginTop" to 25, "marginRight" to 30, "marginBottom" to 0, "marginLeft" to 30, "paddingTop" to 10, "paddingRight" to 15, "paddingBottom" to 10, "paddingLeft" to 15, "borderTopLeftRadius" to 25, "borderTopRightRadius" to 25, "borderBottomRightRadius" to 25, "borderBottomLeftRadius" to 25, "boxSizing" to "border-box", "opacity:active" to 0.7)), "footer-hr" to padStyleMapOf(utsMapOf("height" to 50)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
