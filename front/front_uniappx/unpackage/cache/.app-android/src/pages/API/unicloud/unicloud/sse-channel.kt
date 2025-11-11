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
open class GenPagesAPIUnicloudUnicloudSseChannel : BasePage {
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
                createElementVNode("button", utsMapOf("onClick" to _ctx.receiveMessage), "接收服务端通知", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.messages, fun(item, __key, __index, _cached): Any {
                        return createElementVNode("view", null, toDisplayString(item), 1)
                    }
                    ), 256)
                ))
            ))
        ))
    }
    open var title: String by `$data`
    open var messages: UTSArray<String> by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "服务端通知通道", "messages" to utsArrayOf<String>())
    }
    open var receiveMessage = ::gen_receiveMessage_fn
    open fun gen_receiveMessage_fn(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                this.messages.splice(0, this.messages.length)
                val sseChannel = uniCloud.SSEChannel()
                sseChannel.on("message", fun(message: Any?){
                    if (UTSAndroid.`typeof`(message) === "string") {
                        this.messages.push(message as String)
                    }
                }
                )
                sseChannel.on("end", fun(message: Any?){
                    if (UTSAndroid.`typeof`(message) === "string") {
                        this.messages.push(message as String)
                    }
                }
                )
                sseChannel.on("open", fun(){
                    console.log("sseChannel open", " at pages/API/unicloud/unicloud/sse-channel.uvue:40")
                }
                )
                sseChannel.on("close", fun(){
                    console.log("sseChannel close", " at pages/API/unicloud/unicloud/sse-channel.uvue:43")
                }
                )
                sseChannel.on("error", fun(error: UniCloudError){
                    console.log("sseChannel error: " + error.message, " at pages/API/unicloud/unicloud/sse-channel.uvue:46")
                }
                )
                await(sseChannel.open())
                val res = await(uniCloud.callFunction(UniCloudCallFunctionOptions(name = "sse", data = UTSJSONObject(Map<String, Any?>(utsArrayOf(
                    utsArrayOf(
                        "sseChannel",
                        sseChannel
                    )
                ))))))
                console.log(res, " at pages/API/unicloud/unicloud/sse-channel.uvue:55")
        })
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(), utsArrayOf(
                GenApp.styles
            ))
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
