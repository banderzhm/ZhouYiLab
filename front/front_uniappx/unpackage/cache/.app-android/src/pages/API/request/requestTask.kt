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
import io.dcloud.uniapp.extapi.request as uni_request
import io.dcloud.uniapp.extapi.showModal as uni_showModal
open class GenPagesAPIRequestRequestTask : BasePage {
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
            this.onHeadersReceivedObseves.push(1)
            this.onChunkReceivedObseves.push(1)
        }
        , __ins)
        onUnload(fun() {
            uni_hideLoading()
            this.task?.abort()
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "uni-common-mt", "style" to normalizeStyle(utsMapOf("border-width" to "2px", "border-style" to "solid", "border-radius" to "4px"))), utsArrayOf(
                    createElementVNode("textarea", utsMapOf("value" to _ctx.res, "class" to "uni-textarea", "style" to normalizeStyle(utsMapOf("width" to "100%"))), null, 12, utsArrayOf(
                        "value"
                    ))
                ), 4),
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("text", null, "地址 : " + toDisplayString(_ctx.host + _ctx.url), 1),
                    createElementVNode("text", null, "请求方式 : " + toDisplayString(_ctx.method), 1)
                )),
                createElementVNode("view", utsMapOf("class" to "uni-btn-v uni-common-mt"), utsArrayOf(
                    createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.checkRequestTask, "id" to "checkRequestTask"), "发起流式请求（设置监听需重新点击、勿重复点击）", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-common-pb")),
                    createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.abort, "id" to "abort"), "中断流式请求", 8, utsArrayOf(
                        "onClick"
                    ))
                ))
            )),
            createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1")), "show-scrollbar" to "true"), utsArrayOf(
                createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("padding" to "20px"))), utsArrayOf(
                    createElementVNode("text", null, "添加或者移除流式监听"),
                    createElementVNode("view", utsMapOf("class" to "uni-common-pb")),
                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex-direction" to "row", "flex-wrap" to "wrap"))), utsArrayOf(
                        createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("padding" to "5px")), "type" to "primary", "size" to "mini", "onClick" to _ctx.onHeadersReceived_observe_1, "id" to "onHeadersReceived_observe_1"), "onHeadersReceived监听1", 12, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("padding" to "5px")), "type" to "primary", "size" to "mini", "onClick" to _ctx.onHeadersReceived_observe_2, "id" to "onHeadersReceived_observe_2"), "onHeadersReceived监听2", 12, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("padding" to "5px")), "type" to "primary", "size" to "mini", "onClick" to _ctx.onChunkReceived_observe_1, "id" to "onChunkReceived_observe_1"), "onChunkReceived监听1", 12, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("padding" to "5px")), "type" to "primary", "size" to "mini", "onClick" to _ctx.onChunkReceived_observe_2, "id" to "onChunkReceived_observe_2"), "onChunkReceived监听2", 12, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("padding" to "5px")), "type" to "primary", "size" to "mini", "onClick" to _ctx.offHeadersReceived_id, "id" to "offHeadersReceived_id"), "offHeadersReceived(id)", 12, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("padding" to "5px")), "type" to "primary", "size" to "mini", "onClick" to _ctx.offHeadersReceived_observe, "id" to "offHeadersReceived_observe"), "offHeadersReceived移除所有", 12, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("padding" to "5px")), "type" to "primary", "size" to "mini", "onClick" to _ctx.offChunkReceived_observe, "id" to "offChunkReceived_observe"), "offChunkReceived移除所有", 12, utsArrayOf(
                            "onClick"
                        ))
                    ), 4)
                ), 4)
            ), 4)
        ), 4)
    }
    open var res: String by `$data`
    open var task: RequestTask? by `$data`
    open var host: String by `$data`
    open var url: String by `$data`
    open var method: RequestMethod by `$data`
    open var onHeadersReceivedObseves: UTSArray<Number> by `$data`
    open var onChunkReceivedObseves: UTSArray<Number> by `$data`
    open var onHeadersReceived_returnid_1: Number by `$data`
    open var onHeadersReceived_returnid_2: Number by `$data`
    open var isAbort: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("res" to "", "task" to null as RequestTask?, "host" to "https://request.dcloud.net.cn", "url" to "/api/http/contentType/eventStream?limit=10", "method" to "POST" as RequestMethod, "onHeadersReceivedObseves" to utsArrayOf<Number>(), "onChunkReceivedObseves" to utsArrayOf<Number>(), "onHeadersReceived_returnid_1" to -1, "onHeadersReceived_returnid_2" to -1, "isAbort" to false)
    }
    open var onHeadersReceived_observe_1 = ::gen_onHeadersReceived_observe_1_fn
    open fun gen_onHeadersReceived_observe_1_fn() {
        this.onHeadersReceivedObseves.push(1)
    }
    open var onHeadersReceived_observe_2 = ::gen_onHeadersReceived_observe_2_fn
    open fun gen_onHeadersReceived_observe_2_fn() {
        this.onHeadersReceivedObseves.push(2)
    }
    open var offHeadersReceived_observe = ::gen_offHeadersReceived_observe_fn
    open fun gen_offHeadersReceived_observe_fn() {
        this.onHeadersReceivedObseves = utsArrayOf()
    }
    open var offHeadersReceived_id = ::gen_offHeadersReceived_id_fn
    open fun gen_offHeadersReceived_id_fn() {
        this.onHeadersReceivedObseves = utsArrayOf()
        this.onHeadersReceivedObseves.push(1)
    }
    open var onChunkReceived_observe_1 = ::gen_onChunkReceived_observe_1_fn
    open fun gen_onChunkReceived_observe_1_fn() {
        this.onChunkReceivedObseves.push(1)
    }
    open var onChunkReceived_observe_2 = ::gen_onChunkReceived_observe_2_fn
    open fun gen_onChunkReceived_observe_2_fn() {
        this.onChunkReceivedObseves.push(2)
    }
    open var offChunkReceived_observe = ::gen_offChunkReceived_observe_fn
    open fun gen_offChunkReceived_observe_fn() {
        this.onChunkReceivedObseves = utsArrayOf()
    }
    open var checkRequestTask = ::gen_checkRequestTask_fn
    open fun gen_checkRequestTask_fn() {
        this.isAbort = false
        this.res = "发起post流式请求 \n\n"
        this.task = uni_request<Any>(RequestOptions(url = "https://request.dcloud.net.cn/api/http/contentType/eventStream?limit=10", timeout = 600000, method = this.method, enableChunked = true, success = fun(res){
            console.log("request success", JSON.stringify(res.data), " at pages/API/request/requestTask.uvue:97")
            console.log("request success header is :", JSON.stringify(res.header), " at pages/API/request/requestTask.uvue:98")
            this.res += "流式请求结束 \n\n"
            console.log("请求结果 : " + JSON.stringify(res), " at pages/API/request/requestTask.uvue:100")
        }
        , fail = fun(err){
            var content = err.errMsg
            if (this.isAbort) {
                content = "中断成功"
            }
            console.log("request fail", err, " at pages/API/request/requestTask.uvue:107")
            uni_showModal(ShowModalOptions(content = content, showCancel = false))
        }
        , complete = fun(_){
            this.task = null
        }
        ))
        val onHeadersReceivedCallback1 = fun(res: RequestTaskOnHeadersReceivedListenerResult){
            console.log("-------onHeadersReceived监听1------", res, " at pages/API/request/requestTask.uvue:119")
            this.res += "onHeadersReceived\u76D1\u542C1\uFF1A\n " + JSON.stringify(res) + " \n\n"
        }
        val onHeadersReceivedCallback2 = fun(res: RequestTaskOnHeadersReceivedListenerResult){
            console.log("-------onHeadersReceived监听2------", res, " at pages/API/request/requestTask.uvue:123")
            this.res += "onHeadersReceived\u76D1\u542C2\uFF1A\n " + JSON.stringify(res) + "  \n\n"
        }
        if (this.onHeadersReceivedObseves.includes(1)) {
            this.onHeadersReceived_returnid_1 = this.task?.onHeadersReceived(onHeadersReceivedCallback1) ?: -1
        }
        if (this.onHeadersReceivedObseves.length == 0) {
            this.task?.offHeadersReceived(null)
            this.res += "点击了 offHeadersReceived \n\n"
        } else if (this.onHeadersReceivedObseves.length == 1) {
            this.task?.offHeadersReceived(this.onHeadersReceived_returnid_2)
        }
        val onChunkReceivedCallback1 = fun(res: RequestTaskOnChunkReceivedListenerResult){
            val chunkText: String = TextDecoder().decode(res.data)
            console.log("-------onChunkReceived监听1------", chunkText, " at pages/API/request/requestTask.uvue:140")
            this.res += "onChunkReceived\u76D1\u542C1\uFF1A\n " + chunkText
        }
        val onChunkReceivedCallback2 = fun(res: RequestTaskOnChunkReceivedListenerResult){
            val chunkText: String = TextDecoder().decode(res.data)
            console.log("-------onChunkReceived监听2------", chunkText, " at pages/API/request/requestTask.uvue:145")
            this.res += "onChunkReceived\u76D1\u542C2\uFF1A\n " + chunkText
        }
        if (this.onHeadersReceivedObseves.includes(2)) {
            this.onHeadersReceived_returnid_2 = this.task?.onHeadersReceived(onHeadersReceivedCallback2) ?: -1
        }
        if (this.onChunkReceivedObseves.includes(1)) {
            this.task?.onChunkReceived(onChunkReceivedCallback1)
        }
        if (this.onChunkReceivedObseves.includes(2)) {
            this.task?.onChunkReceived(onChunkReceivedCallback2)
        }
        if (this.onChunkReceivedObseves.length == 0) {
            this.task?.offChunkReceived(null)
            this.res += "点击了 offChunkReceived \n\n"
        }
    }
    @get:JvmName("getAbort0")
    @set:JvmName("setAbort0")
    open var abort = ::gen_abort_fn
    open fun gen_abort_fn() {
        this.isAbort = true
        this.task?.abort()
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
                return utsMapOf("uni-textarea" to padStyleMapOf(utsMapOf("paddingTop" to 9, "paddingRight" to 9, "paddingBottom" to 9, "paddingLeft" to 9, "fontSize" to 14)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
