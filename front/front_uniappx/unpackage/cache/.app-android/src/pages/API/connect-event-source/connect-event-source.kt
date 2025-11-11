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
import io.dcloud.uniapp.extapi.connectEventSource as uni_connectEventSource
import io.dcloud.uniapp.extapi.hideLoading as uni_hideLoading
import io.dcloud.uniapp.extapi.showLoading as uni_showLoading
open class GenPagesAPIConnectEventSourceConnectEventSource : BasePage {
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
        onUnmounted(fun() {
            if (this.eventSource != null) {
                this.eventSource?.close()
            }
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        return createElementVNode(Fragment, null, utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                "title"
            )),
            createElementVNode("button", utsMapOf("class" to "button", "type" to "primary", "onClick" to _ctx.connect), "连接", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("button", utsMapOf("class" to "button", "type" to "primary", "onClick" to _ctx.close), "关闭", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "100%", "text-align" to "center", "margin-bottom" to "5px"))), " 显示简易操作日志(可滚动查看) ", 4),
                    createElementVNode("button", utsMapOf("size" to "mini", "onClick" to fun(){
                        _ctx.logList = utsArrayOf()
                    }
                    ), "清空日志", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("margin-top" to "10px"))), utsArrayOf(
                        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.logList, fun(item, index, __index, _cached): Any {
                            return createElementVNode("view", utsMapOf("key" to index), utsArrayOf(
                                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-left" to "20px", "margin-right" to "20px"))), toDisplayString(item), 5)
                            ))
                        }
                        ), 128)
                    ), 4)
                ))
            ), 4)
        ), 64)
    }
    open var logList: UTSArray<String> by `$data`
    open var title: String by `$data`
    open var url: String by `$data`
    open var eventSource: UniEventSource? by `$data`
    open var open: Boolean by `$data`
    open var receiveMessage: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("logList" to utsArrayOf<String>(), "title" to "sse", "url" to "https://request.dcloud.net.cn/api/sse/connect", "eventSource" to null as UniEventSource?, "open" to false, "receiveMessage" to false)
    }
    open var connect = ::gen_connect_fn
    open fun gen_connect_fn() {
        console.log("connect start", " at pages/API/connect-event-source/connect-event-source.uvue:47")
        uni_showLoading(ShowLoadingOptions(title = "", mask = true))
        this.eventSource?.close()
        var headers: UTSJSONObject = UTSJSONObject(UTSSourceMapPosition("headers", "pages/API/connect-event-source/connect-event-source.uvue", 53, 9))
        headers.set("header1", "value1")
        headers.set("header2", "value3")
        this.eventSource = uni_connectEventSource(ConnectEventSourceOptions(url = this.url, header = headers))
        this.eventSource?.onMessage(fun(ev){
            val log = "onMessage callback:\ntype: " + ev.type + "\n" + "data: " + ev.data + "\n\n"
            this.logList.push(log)
            this.receiveMessage = true
            uni_hideLoading()
        }
        )
        this.eventSource?.onOpen(fun(ev){
            val log = "onOpen callback: " + ev.type + "\n\n"
            this.logList.push(log)
            this.open = true
        }
        )
        this.eventSource?.onError(fun(err){
            val log = "onError callback: " + err + " \n\n"
            this.logList.push(log)
            uni_hideLoading()
        }
        )
    }
    open var close = ::gen_close_fn
    open fun gen_close_fn() {
        this.eventSource?.close()
        val log = "connect close\n\n"
        this.logList.push(log)
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
                return utsMapOf("button" to padStyleMapOf(utsMapOf("marginLeft" to 30, "marginRight" to 30, "marginBottom" to 15)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
