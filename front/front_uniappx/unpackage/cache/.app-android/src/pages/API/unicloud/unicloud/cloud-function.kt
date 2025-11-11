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
import io.dcloud.uniapp.extapi.hideLoading as uni_hideLoading
import io.dcloud.uniapp.extapi.hideToast as uni_hideToast
import io.dcloud.uniapp.extapi.showLoading as uni_showLoading
import io.dcloud.uniapp.extapi.showModal as uni_showModal
open class GenPagesAPIUnicloudUnicloudCloudFunction : BasePage {
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
        onUnload(fun() {
            if (this.isUniTest) {
                uni_hideToast()
            }
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        return createElementVNode("scroll-view", utsMapOf("class" to "page-scroll-view"), utsArrayOf(
            createElementVNode("view", null, utsArrayOf(
                createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                    "title"
                )),
                createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-btn-v uni-common-mt"), utsArrayOf(
                        createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.callFunction), "请求云函数", 8, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.callFunctionWithGeneric), "请求云函数传入泛型", 8, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.callEncryptionFunction), "请求安全网络加密云函数", 8, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.callVerifyFunction), "请求安全网络客户端校验云函数", 8, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("view", null, utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "tips"), "安全网络相关功能需要打包自定义基座方可正常使用")
                        ))
                    ))
                ))
            ))
        ))
    }
    open var title: String by `$data`
    open var callFunctionResult: UTSJSONObject by `$data`
    open var callFunctionResult_Detail_functionName: String by `$data`
    open var callFunctionError: UTSJSONObject by `$data`
    open var genericDemoShowMessage: String by `$data`
    open var isUniTest: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "请求云函数", "callFunctionResult" to UTSJSONObject(), "callFunctionResult_Detail_functionName" to "", "callFunctionError" to UTSJSONObject(), "genericDemoShowMessage" to "", "isUniTest" to false)
    }
    open var notify = ::gen_notify_fn
    open fun gen_notify_fn(content: String, title: String) {
        if (!this.isUniTest) {
            uni_showModal(ShowModalOptions(title = title, content = content, showCancel = false))
        } else {
            console.log(title, content, " at pages/API/unicloud/unicloud/cloud-function.uvue:52")
        }
    }
    open var callFunctionWithGeneric = ::gen_callFunctionWithGeneric_fn
    open fun gen_callFunctionWithGeneric_fn(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                open class EchoCfResult (
                    @JsonNotNull
                    open var showMessage: String,
                ) : UTSObject(), IUTSSourceMap {
                    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
                        return UTSSourceMapPosition("EchoCfResult", "pages/API/unicloud/unicloud/cloud-function.uvue", 56, 14)
                    }
                }
                uni_showLoading(ShowLoadingOptions(title = "加载中..."))
                await(uniCloud.callFunction<EchoCfResult>(UniCloudCallFunctionOptions(name = "echo-cf", data = object : UTSJSONObject() {
                    var num: Number = 1
                    var str = "ABC"
                })).then(fun(res){
                    val result = res.result
                    uni_hideLoading()
                    this.genericDemoShowMessage = result.showMessage
                    this.notify(result.showMessage, "提示")
                }
                ).`catch`(fun(err: Any?){
                    val error = err as UniCloudError
                    this.callFunctionError = object : UTSJSONObject() {
                        var errCode = error.errCode
                        var errMsg = error.errMsg
                    }
                    uni_hideLoading()
                    this.notify(error.errMsg, "错误")
                }
                ))
        })
    }
    open var callFunction = ::gen_callFunction_fn
    open fun gen_callFunction_fn(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                uni_showLoading(ShowLoadingOptions(title = "加载中..."))
                await(uniCloud.callFunction(UniCloudCallFunctionOptions(name = "echo-cf", data = object : UTSJSONObject() {
                    var num: Number = 1
                    var str = "ABC"
                })).then(fun(res){
                    val result = res.result
                    this.callFunctionResult = result
                    val detail = result.get("detail") as UTSJSONObject
                    this.callFunctionResult_Detail_functionName = detail.get("functionName") as String
                    console.log("this.callFunctionResult_Detail_functionName: " + this.callFunctionResult_Detail_functionName, " at pages/API/unicloud/unicloud/cloud-function.uvue:98")
                    console.log(JSON.stringify(result), " at pages/API/unicloud/unicloud/cloud-function.uvue:99")
                    uni_hideLoading()
                    this.notify(result["showMessage"] as String, "提示")
                }
                ).`catch`(fun(err: Any?){
                    uni_hideLoading()
                    if (err is UniCloudError) {
                        val error = err as UniCloudError
                        this.callFunctionError = object : UTSJSONObject() {
                            var errCode = error.errCode
                            var errMsg = error.errMsg
                        }
                        this.notify(error.errMsg, "错误")
                    } else {
                        console.error(err, " at pages/API/unicloud/unicloud/cloud-function.uvue:112")
                    }
                }
                ))
        })
    }
    open var callEncryptionFunction = ::gen_callEncryptionFunction_fn
    open fun gen_callEncryptionFunction_fn() {
        uni_showLoading(ShowLoadingOptions(title = "加载中..."))
        uniCloud.callFunction(UniCloudCallFunctionOptions(name = "encryption", data = UTSJSONObject(), secretType = "both")).then(fun(res){
            uni_hideLoading()
            this.notify(JSON.stringify(res.result), "提示")
        }
        ).`catch`(fun(err: Any?){
            uni_hideLoading()
            if (err is UniCloudError) {
                val error = err as UniCloudError
                this.callFunctionError = object : UTSJSONObject() {
                    var errCode = error.errCode
                    var errMsg = error.errMsg
                }
                this.notify(error.errMsg, "错误")
            } else {
                console.error(err, " at pages/API/unicloud/unicloud/cloud-function.uvue:137")
            }
        }
        )
    }
    open var callVerifyFunction = ::gen_callVerifyFunction_fn
    open fun gen_callVerifyFunction_fn() {
        uni_showLoading(ShowLoadingOptions(title = "加载中..."))
        uniCloud.callFunction(UniCloudCallFunctionOptions(name = "verify-client", data = UTSJSONObject())).then(fun(res){
            uni_hideLoading()
            this.notify(JSON.stringify(res.result), "提示")
        }
        ).`catch`(fun(err: Any?){
            uni_hideLoading()
            if (err is UniCloudError) {
                val error = err as UniCloudError
                this.callFunctionError = object : UTSJSONObject() {
                    var errCode = error.errCode
                    var errMsg = error.errMsg
                }
                this.notify(error.errMsg, "错误")
            } else {
                console.error(err, " at pages/API/unicloud/unicloud/cloud-function.uvue:161")
            }
        }
        )
    }
    open var jest_UniCloudError = ::gen_jest_UniCloudError_fn
    open fun gen_jest_UniCloudError_fn(): Boolean {
        return UTSError() is UniCloudError
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
                return utsMapOf("tips" to padStyleMapOf(utsMapOf("color" to "#999999", "fontSize" to 12, "paddingTop" to 10, "paddingRight" to 0, "paddingBottom" to 10, "paddingLeft" to 0)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
