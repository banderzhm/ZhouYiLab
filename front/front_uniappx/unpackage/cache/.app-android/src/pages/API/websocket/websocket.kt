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
import io.dcloud.uniapp.extapi.closeSocket as uni_closeSocket
import io.dcloud.uniapp.extapi.connectSocket as uni_connectSocket
import io.dcloud.uniapp.extapi.getDeviceInfo as uni_getDeviceInfo
import io.dcloud.uniapp.extapi.hideLoading as uni_hideLoading
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo
import io.dcloud.uniapp.extapi.onSocketClose as uni_onSocketClose
import io.dcloud.uniapp.extapi.onSocketError as uni_onSocketError
import io.dcloud.uniapp.extapi.onSocketMessage as uni_onSocketMessage
import io.dcloud.uniapp.extapi.onSocketOpen as uni_onSocketOpen
import io.dcloud.uniapp.extapi.sendSocketMessage as uni_sendSocketMessage
import io.dcloud.uniapp.extapi.showLoading as uni_showLoading
import io.dcloud.uniapp.extapi.showModal as uni_showModal
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesAPIWebsocketWebsocket : BasePage {
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
            this.platform = uni_getDeviceInfo(null).platform as String
        }
        , __ins)
        onUnload(fun() {
            uni_closeSocket(CloseSocketOptions(code = 1000, reason = "close reason from client", success = fun(res: Any){
                console.log("uni.closeSocket success", res, " at pages/API/websocket/websocket.uvue:57")
            }
            , fail = fun(err: Any){
                console.log("uni.closeSocket fail", err, " at pages/API/websocket/websocket.uvue:60")
            }
            ))
            uni_hideLoading()
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        return createElementVNode(Fragment, null, utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to "websocket通讯示例")),
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "websocket-msg"), toDisplayString(_ctx.showMsg), 1),
                    createElementVNode("button", utsMapOf("class" to "uni-btn-v", "type" to "primary", "onClick" to _ctx.connect), " 连接websocket服务 ", 8, utsArrayOf(
                        "onClick"
                    )),
                    withDirectives(createElementVNode("button", utsMapOf("class" to "uni-btn-v", "type" to "primary", "onClick" to _ctx.send), " 发送一条消息 ", 8, utsArrayOf(
                        "onClick"
                    )), utsArrayOf(
                        utsArrayOf(
                            vShow,
                            _ctx.connected
                        )
                    )),
                    createElementVNode("button", utsMapOf("class" to "uni-btn-v", "type" to "primary", "onClick" to _ctx.close), " 断开websocket服务 ", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("text", utsMapOf("class" to "websocket-tips"), "发送消息后会收到一条服务器返回的消息（与发送的消息内容一致）"),
                    createElementVNode("text", utsMapOf("class" to "websocket-tips"), "web端和小程序端uni-push功能、app-android端和app-ios端的web-view组件日志回显、app-harmony端的日志回显会占用一个socket链接，此时使用全局的socket api会引发混乱。应使用socketTask操作websocket链接。"),
                    createElementVNode("text", utsMapOf("class" to "websocket-tips"), "小程序端日志回显功能也会占用一个socket链接，如果不希望使用此功能可以在HBuilderX控制台关闭日志回显。"),
                    createElementVNode("button", utsMapOf("class" to "uni-btn-v", "type" to "primary", "onClick" to _ctx.goSocketTask), " 跳转 socketTask 示例 ", 8, utsArrayOf(
                        "onClick"
                    ))
                ))
            ))
        ), 64)
    }
    open var connected: Boolean by `$data`
    open var connecting: Boolean by `$data`
    open var msg: String by `$data`
    open var roomId: String by `$data`
    open var platform: String by `$data`
    open var showMsg: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("connected" to false, "connecting" to false, "msg" to "", "roomId" to "", "platform" to "", "showMsg" to computed<String>(fun(): String {
            if (this.connected) {
                if (this.msg.length > 0) {
                    return "收到消息：" + this.msg
                } else {
                    return "等待接收消息"
                }
            } else {
                return "尚未连接"
            }
        }
        ))
    }
    open var connect = ::gen_connect_fn
    open fun gen_connect_fn() {
        if (this.connected || this.connecting) {
            uni_showModal(ShowModalOptions(content = "正在连接或者已经连接，请勿重复连接", showCancel = false))
            return
        }
        this.connecting = true
        uni_showLoading(ShowLoadingOptions(title = "连接中..."))
        uni_connectSocket(ConnectSocketOptions(url = "wss://websocket.dcloud.net.cn", header = null, protocols = null, success = fun(res: Any){
            console.log("uni.connectSocket success", res, " at pages/API/websocket/websocket.uvue:84")
        }
        , fail = fun(err: Any){
            console.log("uni.connectSocket fail", err, " at pages/API/websocket/websocket.uvue:88")
        }
        ))
        uni_onSocketOpen(fun(res){
            this.connecting = false
            this.connected = true
            uni_hideLoading()
            uni_showToast(ShowToastOptions(icon = "none", title = "连接成功"))
            console.log("onOpen", res, " at pages/API/websocket/websocket.uvue:100")
        }
        )
        uni_onSocketError(fun(err){
            this.connecting = false
            this.connected = false
            uni_hideLoading()
            uni_showModal(ShowModalOptions(content = "连接失败，可能是websocket服务不可用，请稍后再试", showCancel = false))
            console.log("onError", err, " at pages/API/websocket/websocket.uvue:111")
        }
        )
        uni_onSocketMessage(fun(res){
            if (res.data is ArrayBuffer) {
                var int8 = Int8Array(res.data as ArrayBuffer)
                this.msg = int8.toString()
                console.log("onMessage", res, " at pages/API/websocket/websocket.uvue:117")
            } else {
                this.msg = res.data as String
                console.log("onMessage", res, " at pages/API/websocket/websocket.uvue:120")
            }
        }
        )
        uni_onSocketClose(fun(res){
            this.connected = false
            this.msg = ""
            console.log("onClose", res, " at pages/API/websocket/websocket.uvue:126")
        }
        )
    }
    open var send = ::gen_send_fn
    open fun gen_send_fn() {
        uni_sendSocketMessage(SendSocketMessageOptions(data = "from " + this.platform + " : " + parseInt((Math.random() * 10000).toString(10)).toString(10), success = fun(res: Any){
            console.log(res, " at pages/API/websocket/websocket.uvue:137")
        }
        , fail = fun(err: Any){
            console.log(err, " at pages/API/websocket/websocket.uvue:140")
        }
        ))
    }
    open var close = ::gen_close_fn
    open fun gen_close_fn() {
        uni_closeSocket(CloseSocketOptions(code = 1000, reason = "close reason from client", success = fun(res: Any){
            console.log("uni.closeSocket success", res, " at pages/API/websocket/websocket.uvue:149")
        }
        , fail = fun(err: Any){
            console.log("uni.closeSocket fail", err, " at pages/API/websocket/websocket.uvue:152")
        }
        ))
    }
    open var goSocketTask = ::gen_goSocketTask_fn
    open fun gen_goSocketTask_fn() {
        uni_navigateTo(NavigateToOptions(url = "/pages/API/websocket/socketTask"))
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
                return utsMapOf("uni-btn-v" to padStyleMapOf(utsMapOf("paddingTop" to 5, "paddingRight" to 0, "paddingBottom" to 5, "paddingLeft" to 0, "marginTop" to 10, "marginRight" to 0, "marginBottom" to 10, "marginLeft" to 0)), "websocket-msg" to padStyleMapOf(utsMapOf("paddingTop" to 40, "paddingRight" to 0, "paddingBottom" to 40, "paddingLeft" to 0, "textAlign" to "center", "fontSize" to 14, "lineHeight" to "40px", "color" to "#666666")), "websocket-tips" to padStyleMapOf(utsMapOf("paddingTop" to 10, "paddingRight" to 0, "paddingBottom" to 10, "paddingLeft" to 0, "textAlign" to "center", "fontSize" to 14, "lineHeight" to "24px", "color" to "#666666")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
