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
import io.dcloud.uniapp.extapi.getNetworkType as uni_getNetworkType
import io.dcloud.uniapp.extapi.showModal as uni_showModal
open class GenPagesAPIGetNetworkTypeGetNetworkType : BasePage {
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
            this.networkType = ""
            this.hasNetworkType = false
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
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "uni-container"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-center"), "网络状态"),
                    if (_ctx.hasNetworkType == false) {
                        createElementVNode("view", utsMapOf("key" to 0), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-center uni-common-mt"), "未获取"),
                            createElementVNode("view", utsMapOf("class" to "uni-center uni-common-mt"), "请点击下面按钮获取网络状态")
                        ))
                    } else {
                        createCommentVNode("v-if", true)
                    }
                    ,
                    if (_ctx.hasNetworkType == true) {
                        createElementVNode("view", utsMapOf("key" to 1), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-center uni-common-mt"), toDisplayString(_ctx.networkType), 1)
                        ))
                    } else {
                        createCommentVNode("v-if", true)
                    }
                )),
                createElementVNode("view", utsMapOf("class" to "uni-btn-v uni-common-mt"), utsArrayOf(
                    createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.getNetworkType), "获取设备网络状态", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "uni-common-mt", "onClick" to _ctx.clear), "清空", 8, utsArrayOf(
                        "onClick"
                    ))
                ))
            ))
        ), 64)
    }
    open var title: String by `$data`
    open var hasNetworkType: Boolean by `$data`
    open var networkType: String by `$data`
    open var connectedWifi: String by `$data`
    open var jest_result: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "getNetworkType", "hasNetworkType" to false, "networkType" to "", "connectedWifi" to "", "jest_result" to false)
    }
    open var getNetworkType = ::gen_getNetworkType_fn
    open fun gen_getNetworkType_fn() {
        uni_getNetworkType(GetNetworkTypeOptions(success = fun(res){
            console.log(res, " at pages/API/get-network-type/get-network-type.uvue:42")
            this.hasNetworkType = true
            this.networkType = res.networkType
        }
        , fail = fun(_){
            uni_showModal(ShowModalOptions(content = "获取失败！", showCancel = false))
        }
        ))
    }
    open var clear = ::gen_clear_fn
    open fun gen_clear_fn() {
        this.hasNetworkType = false
        this.networkType = ""
        this.connectedWifi = ""
    }
    open var jest_getNetworkType = ::gen_jest_getNetworkType_fn
    open fun gen_jest_getNetworkType_fn() {
        uni_getNetworkType(GetNetworkTypeOptions(success = fun(_){
            this.jest_result = true
        }
        , fail = fun(_){
            this.jest_result = false
        }
        ))
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
