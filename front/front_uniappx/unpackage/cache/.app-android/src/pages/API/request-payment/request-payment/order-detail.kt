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
open class GenPagesAPIRequestPaymentRequestPaymentOrderDetail : BasePage {
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
        var setup: (__props: GenPagesAPIRequestPaymentRequestPaymentOrderDetail) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesAPIRequestPaymentRequestPaymentOrderDetail
            val _cache = __ins.renderCache
            val myOptions = reactive(UTSJSONObject())
            onLoad(fun(options){
                console.log("options: ", options, " at pages/API/request-payment/request-payment/order-detail.uvue:20")
                myOptions["order_no"] = options["order_no"] as String
                myOptions["out_trade_no"] = options["out_trade_no"] as String
            }
            )
            return fun(): Any? {
                return createElementVNode("view", utsMapOf("class" to "app"), utsArrayOf(
                    createElementVNode("view", null, utsArrayOf(
                        createElementVNode("text", null, "你自己的业务系统订单详情页面")
                    )),
                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("margin-top" to "10px"))), utsArrayOf(
                        createElementVNode("text", null, "order_no：" + toDisplayString(myOptions["order_no"]), 1)
                    ), 4),
                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("margin-top" to "10px"))), utsArrayOf(
                        createElementVNode("text", null, "out_trade_no：" + toDisplayString(myOptions["out_trade_no"]), 1)
                    ), 4)
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
                return utsMapOf("app" to padStyleMapOf(utsMapOf("paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
