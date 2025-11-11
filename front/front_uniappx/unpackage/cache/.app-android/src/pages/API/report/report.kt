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
import uts.sdk.modules.uniStat.ReportOptions as ReportOptions
import io.dcloud.uniapp.extapi.getLaunchOptionsSync as uni_getLaunchOptionsSync
import uts.sdk.modules.uniStat.report as uni_report
open class GenPagesAPIReportReport : BasePage {
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
            uni_report(ReportOptions(name = "自定义上报-report页面打开", options = "1"))
        }
        , __ins)
        onUnload(fun() {
            uni_report(ReportOptions(name = "自定义上报-report页面关闭", options = "1"))
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to "report")),
            createElementVNode("view", utsMapOf("class" to "tips"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "tips-title"), "调用信息："),
                createElementVNode("view", utsMapOf("class" to "tips-content"), toDisplayString(_ctx.msg), 1)
            )),
            createElementVNode("view", utsMapOf("class" to "page"), utsArrayOf(
                createElementVNode("button", utsMapOf("class" to "normal-button", "type" to "default", "onClick" to _ctx.handleAppLaunch), " 模拟应用启动 ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "normal-button", "type" to "default", "onClick" to _ctx.handleAppHide), " 模拟应用切入后台 ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "normal-button", "type" to "default", "onClick" to _ctx.handleAppShow), " 模拟应用切入前台 ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "normal-button", "type" to "default", "onClick" to _ctx.handleAppError), " 模拟应用错误 ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "normal-button", "type" to "default", "onClick" to _ctx.handleTitle), " 模拟自定义title ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "normal-button", "type" to "default", "onClick" to _ctx.handleEvent), " 模拟自定义事件 ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("text", utsMapOf("class" to "instructions"), " 当前页面调用API均为模拟，请查看文档，在特定场景下使用以上 API。请在main.uts中设置统计debug配置为true，并点击按钮查控制台输出。 ")
            ))
        ), 4)
    }
    open var msg: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("msg" to "点击按钮，测试上报")
    }
    open var handleAppLaunch = ::gen_handleAppLaunch_fn
    open fun gen_handleAppLaunch_fn() {
        val options = uni_getLaunchOptionsSync()
        uni_report(ReportOptions(name = "uni-app-launch", options = options, success = fun(res){
            this.msg = "onLaunch --> " + res.errMsg
            console.log(res, " at pages/API/report/report.uvue:69")
        }
        , fail = fun(err){
            this.msg = "onLaunch --> " + err.errMsg
            console.log(err, " at pages/API/report/report.uvue:72")
        }
        ))
    }
    open var handleAppHide = ::gen_handleAppHide_fn
    open fun gen_handleAppHide_fn() {
        uni_report(ReportOptions(name = "uni-app-hide", success = fun(res){
            this.msg = "onAppHide --> " + res.errMsg
            console.log(res, " at pages/API/report/report.uvue:81")
        }
        , fail = fun(err){
            this.msg = "onAppHide --> " + err.errMsg
            console.log(err, " at pages/API/report/report.uvue:84")
        }
        ))
    }
    open var handleAppShow = ::gen_handleAppShow_fn
    open fun gen_handleAppShow_fn() {
        uni_report(ReportOptions(name = "uni-app-show", success = fun(res){
            this.msg = "onAppShow --> " + res.errMsg
            console.log(res, " at pages/API/report/report.uvue:94")
        }
        , fail = fun(err){
            this.msg = "onAppShow --> " + err.errMsg
            console.log(err, " at pages/API/report/report.uvue:97")
        }
        ))
    }
    open var handleAppError = ::gen_handleAppError_fn
    open fun gen_handleAppError_fn() {
        val errmsg = "测试错误"
        uni_report(ReportOptions(name = "uni-app-error", options = errmsg, success = fun(res){
            this.msg = "onAppError --> " + res.errMsg
            console.log(res, " at pages/API/report/report.uvue:108")
        }
        , fail = fun(err){
            this.msg = "onAppError --> " + err.errMsg
            console.log(err, " at pages/API/report/report.uvue:111")
        }
        ))
    }
    open var handleEvent = ::gen_handleEvent_fn
    open fun gen_handleEvent_fn() {
        uni_report(ReportOptions(name = "custom-event", options = object : UTSJSONObject() {
            var title = "自定义事件"
            var total: Number = 1
        }, success = fun(res){
            this.msg = "自定义事件 --> " + res.errMsg
            console.log(res, " at pages/API/report/report.uvue:125")
        }
        , fail = fun(err){
            this.msg = "自定义事件 --> " + err.errMsg
            console.log(err, " at pages/API/report/report.uvue:128")
        }
        ))
    }
    open var handleTitle = ::gen_handleTitle_fn
    open fun gen_handleTitle_fn() {
        uni_report(ReportOptions(name = "title", options = "自定义title测试上报", success = fun(res){
            this.msg = "自定义title --> " + res.errMsg
            console.log(res, " at pages/API/report/report.uvue:139")
        }
        , fail = fun(err){
            this.msg = "自定义title --> " + err.errMsg
            console.log(err, " at pages/API/report/report.uvue:142")
        }
        ))
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
                return utsMapOf("page" to padStyleMapOf(utsMapOf("paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15)), "tips" to padStyleMapOf(utsMapOf("marginTop" to 15, "marginRight" to 15, "marginBottom" to 15, "marginLeft" to 15, "paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15, "backgroundColor" to "#f5f5f5", "fontSize" to 14, "textAlign" to "center")), "tips-title" to padStyleMapOf(utsMapOf("fontSize" to 16, "color" to "#333333", "marginBottom" to 10)), "tips-content" to padStyleMapOf(utsMapOf("fontSize" to 14, "color" to "#999999")), "normal-button" to padStyleMapOf(utsMapOf("width" to "100%", "marginBottom" to 10)), "instructions" to padStyleMapOf(utsMapOf("marginTop" to 10, "marginLeft" to 10, "marginRight" to 10, "backgroundColor" to "#eeeeee", "fontSize" to 12, "color" to "#999999")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
