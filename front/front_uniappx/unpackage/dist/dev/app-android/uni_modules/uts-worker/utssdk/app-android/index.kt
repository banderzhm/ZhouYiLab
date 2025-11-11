@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER", "NAME_SHADOWING", "UNNECESSARY_NOT_NULL_ASSERTION")
package uts.sdk.modules.utsWorker
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
open class UTSWorkerAddListenerOptions (
    open var success: UTSWorkerReceiveEventCallback? = null,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("UTSWorkerAddListenerOptions", "uni_modules/uts-worker/utssdk/interface.uts", 1, 13)
    }
}
open class UTSWorkerAddListenerOptionsSuccess (
    @JsonNotNull
    open var result: UTSJSONObject,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("UTSWorkerAddListenerOptionsSuccess", "uni_modules/uts-worker/utssdk/interface.uts", 4, 13)
    }
}
open class UTSWorkerSendWorkerMessageOptions (
    @JsonNotNull
    open var data: String,
    @JsonNotNull
    open var needReply: Boolean = false,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("UTSWorkerSendWorkerMessageOptions", "uni_modules/uts-worker/utssdk/interface.uts", 7, 13)
    }
}
open class UTSWorkerErrorCallbackSuccess (
    @JsonNotNull
    open var message: String,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("UTSWorkerErrorCallbackSuccess", "uni_modules/uts-worker/utssdk/interface.uts", 11, 13)
    }
}
open class UTSWorkerErrorOptions (
    open var success: UTSWorkerErrorCallback? = null,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("UTSWorkerErrorOptions", "uni_modules/uts-worker/utssdk/interface.uts", 14, 13)
    }
}
typealias UTSWorkerErrorCallback = (error: UTSWorkerErrorCallbackSuccess) -> Unit
typealias UTSWorkerReceiveEventCallback = (callback: UTSWorkerAddListenerOptionsSuccess) -> Unit
typealias SendWorkerMessage = (options: UTSWorkerSendWorkerMessageOptions) -> Unit
typealias OnWorkerMessage = (options: UTSWorkerAddListenerOptions) -> Unit
typealias OnWorkerError = (options: UTSWorkerErrorOptions) -> Unit
typealias CreateWorkers = () -> Unit
typealias DestroyWorker = () -> Unit
val createWorkers: CreateWorkers = fun(): Unit {
    UTSWorkerImpl.shared.create()
}
val sendWorkerMessage: SendWorkerMessage = fun(options: UTSWorkerSendWorkerMessageOptions): Unit {
    UTSWorkerImpl.shared.sendMessage(options)
}
val onWorkerMessage: OnWorkerMessage = fun(options: UTSWorkerAddListenerOptions): Unit {
    UTSWorkerImpl.shared.onMessage(options)
}
val destroyWorker: DestroyWorker = fun(): Unit {
    UTSWorkerImpl.shared.destroy()
}
val onWorkerError: OnWorkerError = fun(options: UTSWorkerErrorOptions): Unit {
    UTSWorkerImpl.shared.onError(options)
}
open class UTSWorkerImpl : IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("UTSWorkerImpl", "uni_modules/uts-worker/utssdk/index.uts", 18, 14)
    }
    private var workerImp: Worker? = null
    private var options: UTSWorkerAddListenerOptions? = null
    private var errorOptions: UTSWorkerErrorOptions? = null
    open fun create() {
        if (this.workerImp != null) {
            console.warn("[UTS Plugin] Worker已存在，先销毁旧的Worker", " at uni_modules/uts-worker/utssdk/index.uts:34")
            this.destroy()
        }
        console.log("[UTS Plugin] 创建Worker", " at uni_modules/uts-worker/utssdk/index.uts:38")
        val worker = uni_createWorker("workers/helloWorkerTask.uts")
        this.workerImp = worker
    }
    open fun sendMessage(options: UTSWorkerSendWorkerMessageOptions) {
        if (this.workerImp == null) {
            console.error("Worker未创建，无法发送消息", " at uni_modules/uts-worker/utssdk/index.uts:46")
            return
        }
        val msgData = options.data
        val needReply = options.needReply
        console.log("[UTS Plugin] \u5411Worker\u53D1\u9001\u6D88\u606F: " + msgData + " (\u9700\u8981\u56DE\u590D: " + needReply + ")", " at uni_modules/uts-worker/utssdk/index.uts:54")
        val messageToWorker: UTSJSONObject = UTSJSONObject(Map<String, Any?>(utsArrayOf(
            utsArrayOf(
                "__\$originalPosition",
                UTSSourceMapPosition("messageToWorker", "uni_modules/uts-worker/utssdk/index.uts", 42, 15)
            ),
            utsArrayOf(
                "data",
                msgData
            ),
            utsArrayOf(
                "needReply",
                needReply
            )
        )))
        this.workerImp!!.postMessage(messageToWorker, null)
    }
    open fun onMessage(options: UTSWorkerAddListenerOptions) {
        if (this.workerImp == null) {
            console.error("Worker未创建，无法监听消息", " at uni_modules/uts-worker/utssdk/index.uts:67")
            return
        }
        this.options = options
        this.workerImp!!.onMessage(fun(result){
            console.log("[UTS Plugin] \u6536\u5230Worker\u6D88\u606F:", result, " at uni_modules/uts-worker/utssdk/index.uts:74")
            if (this.options != null && this.options.success != null) {
                var success = UTSWorkerAddListenerOptionsSuccess(result = result as UTSJSONObject)
                this.options.success(success)
            }
        }
        )
    }
    open fun onError(options: UTSWorkerErrorOptions) {
        if (this.workerImp == null) {
            console.error("Worker未创建，无法监听错误", " at uni_modules/uts-worker/utssdk/index.uts:86")
            return
        }
        this.errorOptions = options
        this.workerImp!!.onError(fun(error){
            console.error("[UTS Plugin] Worker发生错误:", error, " at uni_modules/uts-worker/utssdk/index.uts:92")
            if (this.errorOptions != null && this.errorOptions.success != null) {
                val errorInfo = UTSWorkerErrorCallbackSuccess(message = "worker error")
                this.errorOptions.success(errorInfo)
            }
        }
        )
    }
    open fun destroy() {
        if (this.workerImp != null) {
            console.log("[UTS Plugin] 销毁Worker", " at uni_modules/uts-worker/utssdk/index.uts:108")
            this.workerImp.terminate()
            this.workerImp = null
            this.options = null
            this.errorOptions = null
        }
    }
    companion object {
        var shared: UTSWorkerImpl = UTSWorkerImpl()
    }
}
