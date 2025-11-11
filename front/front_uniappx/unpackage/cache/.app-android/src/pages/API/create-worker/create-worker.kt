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
import io.dcloud.uniapp.extapi.createWorker as uni_createWorker
import uts.sdk.modules.utsWorker.onWorkerError
import uts.sdk.modules.utsWorker.createWorkers
import uts.sdk.modules.utsWorker.sendWorkerMessage
import uts.sdk.modules.utsWorker.onWorkerMessage
import uts.sdk.modules.utsWorker.destroyWorker
import uts.sdk.modules.utsWorker.UTSWorkerAddListenerOptions
import uts.sdk.modules.utsWorker.UTSWorkerReceiveEventCallback
import uts.sdk.modules.utsWorker.UTSWorkerSendWorkerMessageOptions
import uts.sdk.modules.utsWorker.UTSWorkerErrorOptions
open class GenPagesAPICreateWorkerCreateWorker : BasePage {
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
        onUnload(fun() {
            this.destory()
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("scroll-view", utsMapOf("class" to "container"), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "status-section"), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "status-label"), "Worker状态: "),
                createElementVNode("text", utsMapOf("class" to "status-text"), toDisplayString(_ctx.statusText), 1)
            )),
            createElementVNode("view", utsMapOf("class" to "button-group"), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "description-text"), "操作步骤：1.创建Worker 2.添加消息监听 3.发送数据测试"),
                createElementVNode("button", utsMapOf("class" to "btn", "type" to "primary", "disabled" to _ctx.created_boolean, "onClick" to _ctx.create), "创建Worker", 8, utsArrayOf(
                    "disabled",
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btn", "type" to "primary", "onClick" to _ctx.onWorkerMsg), "添加消息监听", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btn", "type" to "primary", "onClick" to _ctx.onWorkerError), "添加错误监听", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btn", "onClick" to _ctx.destory, "disabled" to (_ctx.workerStatus != "created")), "销毁Worker", 8, utsArrayOf(
                    "onClick",
                    "disabled"
                ))
            )),
            createElementVNode("view", utsMapOf("class" to "input-section"), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "section-title"), "输入测试值:"),
                createElementVNode("text", utsMapOf("class" to "description-text"), "点击发送按钮后，会将输入值传给WorkerTask，在子线程执行+1操作后返回结果"),
                createElementVNode("input", utsMapOf("class" to "input-field", "modelValue" to _ctx.inputValue, "onInput" to fun(`$event`: InputEvent){
                    _ctx.inputValue = `$event`.detail.value
                }
                , "type" to "number", "placeholder" to "请输入数字"), null, 40, utsArrayOf(
                    "modelValue",
                    "onInput"
                )),
                createElementVNode("button", utsMapOf("class" to "btn", "type" to "primary", "onClick" to _ctx.sendMessage, "disabled" to (_ctx.workerStatus != "created")), "发送到WorkerTask (值+1)", 8, utsArrayOf(
                    "onClick",
                    "disabled"
                ))
            )),
            createElementVNode("view", utsMapOf("class" to "log-section"), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "section-title"), "通信日志:"),
                createElementVNode("scroll-view", utsMapOf("class" to "log-container", "scroll-y" to "true"), utsArrayOf(
                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.logs, fun(log, index, __index, _cached): Any {
                        return createElementVNode("view", utsMapOf("key" to index, "class" to "log-item"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to normalizeClass(utsArrayOf(
                                "log-text",
                                log["type"]
                            ))), toDisplayString(log["message"]), 3)
                        ))
                    }
                    ), 128)
                )),
                createElementVNode("button", utsMapOf("onClick" to _ctx.clearLogs, "class" to "btn clear-btn"), "清空日志", 8, utsArrayOf(
                    "onClick"
                ))
            ))
        ))
    }
    open var created_boolean: Boolean by `$data`
    open var workerStatus: String by `$data`
    open var isListening: Boolean by `$data`
    open var logs: UTSArray<UTSJSONObject> by `$data`
    open var inputValue: String by `$data`
    open var taskResult: String by `$data`
    open var worker: Worker? by `$data`
    open var statusText: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("created_boolean" to false, "workerStatus" to "none", "isListening" to false, "logs" to utsArrayOf<UTSJSONObject>(), "inputValue" to "1", "taskResult" to "", "worker" to null as Worker?, "statusText" to computed<String>(fun(): String {
            when (this.workerStatus) {
                "none" -> 
                    return "未创建"
                "created" -> 
                    return "已创建"
                "destroyed" -> 
                    return "已销毁"
                else -> 
                    return "未知"
            }
        }
        ))
    }
    open fun addLog(message: String, type: String = "info") {
        val now = Date()
        val timeStr = "" + now.getHours().toString(10).padStart(2, "0") + ":" + now.getMinutes().toString(10).padStart(2, "0") + ":" + now.getSeconds().toString(10).padStart(2, "0")
        val logItem: UTSJSONObject = UTSJSONObject(Map<String, Any?>(utsArrayOf(
            utsArrayOf(
                "__\$originalPosition",
                UTSSourceMapPosition("logItem", "pages/API/create-worker/create-worker.uvue", 74, 15)
            ),
            utsArrayOf(
                "message",
                ("[" + timeStr + "] " + message)
            ),
            utsArrayOf(
                "type",
                type
            ),
            utsArrayOf(
                "time",
                timeStr
            )
        )))
        this.logs.unshift(logItem)
        if (this.logs.length > 50) {
            this.logs = this.logs.slice(0, 50)
        }
    }
    open var create = ::gen_create_fn
    open fun gen_create_fn() {
        this.worker = uni_createWorker("workers/helloWorkerTask.uts")
        this.workerStatus = "created"
        this.addLog("Worker创建成功", "success")
        this.created_boolean = true
    }
    open var onWorkerMsg = ::gen_onWorkerMsg_fn
    open fun gen_onWorkerMsg_fn() {
        if (this.worker == null) {
            this.addLog("请先创建worker", "warning")
            return
        }
        this.worker!!.onMessage(fun(result){
            console.log("\u6536\u5230Worker\u6D88\u606F:", result, " at pages/API/create-worker/create-worker.uvue:101")
            val res = result as UTSJSONObject
            val resultData = res["data"] as String
            this.taskResult = resultData
            this.inputValue = this.taskResult
            this.addLog("\u6536\u5230WorkerTask\u8FD4\u56DE: " + resultData, "receive")
        }
        )
    }
    open var onWorkerError = ::gen_onWorkerError_fn
    open fun gen_onWorkerError_fn() {
        if (this.worker == null) {
            this.addLog("请先创建worker", "warning")
            return
        }
        this.worker!!.onError(fun(error){
            console.error("Worker发生错误:", error, " at pages/API/create-worker/create-worker.uvue:115")
        }
        )
    }
    open var sendMessage = ::gen_sendMessage_fn
    open fun gen_sendMessage_fn() {
        if (this.inputValue == "") {
            this.addLog("请输入有效的数字", "warning")
            return
        }
        val options: UTSJSONObject = let {
            object : UTSJSONObject(UTSSourceMapPosition("options", "pages/API/create-worker/create-worker.uvue", 127, 15)) {
                var data = it.inputValue
                var needReply = true
            }
        }
        this.worker!!.postMessage(options, null)
        this.addLog("\u53D1\u9001\u503C\u5230WorkerTask: " + this.inputValue, "send")
    }
    open var destory = ::gen_destory_fn
    open fun gen_destory_fn() {
        if (this.worker == null) {
            this.addLog("没有创建worker,无法销毁", "warning")
            return
        }
        this.worker!!.terminate()
        this.workerStatus = "destroyed"
        this.isListening = false
        this.addLog("Worker已销毁", "warning")
        this.created_boolean = false
    }
    open var clearLogs = ::gen_clearLogs_fn
    open fun gen_clearLogs_fn() {
        this.logs = utsArrayOf()
    }
    open var test_resetInputValue = ::gen_test_resetInputValue_fn
    open fun gen_test_resetInputValue_fn() {
        this.inputValue = "1"
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
                return utsMapOf("container" to padStyleMapOf(utsMapOf("flex" to 1, "paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10)), "status-section" to padStyleMapOf(utsMapOf("display" to "flex", "alignItems" to "center", "justifyContent" to "center", "marginBottom" to 20, "paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10, "backgroundColor" to "#ffffff", "borderTopLeftRadius" to 8, "borderTopRightRadius" to 8, "borderBottomRightRadius" to 8, "borderBottomLeftRadius" to 8)), "status-label" to padStyleMapOf(utsMapOf("fontSize" to 16, "color" to "#666666")), "status-text" to padStyleMapOf(utsMapOf("fontSize" to 16, "fontWeight" to "bold", "marginLeft" to 8)), "button-group" to padStyleMapOf(utsMapOf("flexDirection" to "column", "marginBottom" to 10)), "input-section" to padStyleMapOf(utsMapOf("marginBottom" to 20, "paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15, "backgroundColor" to "#ffffff", "borderTopLeftRadius" to 8, "borderTopRightRadius" to 8, "borderBottomRightRadius" to 8, "borderBottomLeftRadius" to 8)), "input-field" to padStyleMapOf(utsMapOf("width" to "100%", "paddingTop" to 12, "paddingRight" to 12, "paddingBottom" to 12, "paddingLeft" to 12, "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#dddddd", "borderRightColor" to "#dddddd", "borderBottomColor" to "#dddddd", "borderLeftColor" to "#dddddd", "borderTopLeftRadius" to 6, "borderTopRightRadius" to 6, "borderBottomRightRadius" to 6, "borderBottomLeftRadius" to 6, "fontSize" to 16, "marginTop" to 10, "marginRight" to 0, "marginBottom" to 10, "marginLeft" to 0, "backgroundColor" to "#ffffff")), "btn" to padStyleMapOf(utsMapOf("marginBottom" to 10, "paddingTop" to 5, "paddingRight" to 10, "paddingBottom" to 5, "paddingLeft" to 10, "borderTopLeftRadius" to 6, "borderTopRightRadius" to 6, "borderBottomRightRadius" to 6, "borderBottomLeftRadius" to 6, "fontSize" to 14, "textAlign" to "center")), "log-section" to padStyleMapOf(utsMapOf("backgroundColor" to "#ffffff", "borderTopLeftRadius" to 8, "borderTopRightRadius" to 8, "borderBottomRightRadius" to 8, "borderBottomLeftRadius" to 8, "paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15)), "section-title" to padStyleMapOf(utsMapOf("fontSize" to 18, "fontWeight" to "bold", "color" to "#333333", "marginBottom" to 10)), "log-container" to padStyleMapOf(utsMapOf("height" to 300, "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#dddddd", "borderRightColor" to "#dddddd", "borderBottomColor" to "#dddddd", "borderLeftColor" to "#dddddd", "borderTopLeftRadius" to 4, "borderTopRightRadius" to 4, "borderBottomRightRadius" to 4, "borderBottomLeftRadius" to 4, "paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10, "marginTop" to 10, "marginRight" to 0, "marginBottom" to 10, "marginLeft" to 0, "backgroundColor" to "#fafafa")), "log-item" to padStyleMapOf(utsMapOf("marginBottom" to 5)), "log-text" to utsMapOf("" to utsMapOf("fontSize" to 12, "lineHeight" to 1.4), ".info" to utsMapOf("color" to "#2196F3"), ".success" to utsMapOf("color" to "#4CAF50"), ".warning" to utsMapOf("color" to "#ff9800"), ".error" to utsMapOf("color" to "#f44336"), ".send" to utsMapOf("color" to "#9C27B0"), ".receive" to utsMapOf("color" to "#009688")), "clear-btn" to padStyleMapOf(utsMapOf("backgroundColor" to "#ff9800", "fontSize" to 12, "paddingTop" to 8, "paddingRight" to 12, "paddingBottom" to 8, "paddingLeft" to 12, "color" to "#ffffff", "borderTopLeftRadius" to 4, "borderTopRightRadius" to 4, "borderBottomRightRadius" to 4, "borderBottomLeftRadius" to 4, "textAlign" to "center")), "description-text" to padStyleMapOf(utsMapOf("fontSize" to 14, "color" to "#666666", "lineHeight" to 1.4, "marginBottom" to 10)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
