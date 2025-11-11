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
import io.dcloud.uniapp.extapi.getSystemInfo as uni_getSystemInfo
import io.dcloud.uniapp.extapi.getSystemInfoSync as uni_getSystemInfoSync
open class GenPagesAPIGetSystemInfoGetSystemInfo : BasePage {
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
        onUnload(fun() {}, __ins)
        onReady(fun() {
            this.screenHeightAtReady = uni_getSystemInfoSync().screenHeight
            console.log("\u5168\u5C40\u83B7\u53D6\u5C4F\u5E55\u9AD8\u5EA6: " + globalScreenHeight + "  onReady\u5185\u83B7\u53D6\u5C4F\u5E55\u9AD8\u5EA6: " + this.screenHeightAtReady, " at pages/API/get-system-info/get-system-info.uvue:63")
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("view", null, utsArrayOf(
                createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                    "title"
                )),
                createElementVNode("view", utsMapOf("class" to "uni-common-mt"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-list"), utsArrayOf(
                        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.items, fun(item, _, __index, _cached): Any {
                            return createElementVNode("view", utsMapOf("class" to "uni-list-cell", "style" to normalizeStyle(utsMapOf("align-items" to "center"))), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "uni-pd"), utsArrayOf(
                                    createElementVNode("view", utsMapOf("class" to "uni-label", "style" to normalizeStyle(utsMapOf("width" to "180px"))), toDisplayString(item.label), 5)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "uni-list-cell-db-text"), toDisplayString(if (item.value == "") {
                                        "未获取"
                                    } else {
                                        item.value
                                    }
                                    ), 1)
                                ))
                            ), 4)
                        }
                        ), 256)
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.getSystemInfoSync), " 同步获取设备系统信息 ", 8, utsArrayOf(
                                "onClick"
                            )),
                            createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.getSystemInfo, "style" to normalizeStyle(utsMapOf("margin-top" to "20px"))), " 异步获取设备系统信息 ", 12, utsArrayOf(
                                "onClick"
                            ))
                        ))
                    ))
                ))
            ))
        ), 4)
    }
    open var title: String by `$data`
    open var items: UTSArray<Item2> by `$data`
    open var screenHeightAtReady: Number by `$data`
    open var jest_result: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "getSystemInfo", "items" to utsArrayOf<Item2>(), "screenHeightAtReady" to 0, "jest_result" to false)
    }
    open var getSystemInfo = ::gen_getSystemInfo_fn
    open fun gen_getSystemInfo_fn() {
        uni_getSystemInfo(GetSystemInfoOptions(success = fun(res){
            this.items = utsArrayOf<Item2>()
            val res_str = JSON.stringify(res)
            val res_obj = UTSAndroid.consoleDebugError(JSON.parseObject(res_str), " at pages/API/get-system-info/get-system-info.uvue:71")
            val res_map = res_obj!!.toMap()
            var keys = utsArrayOf<String>()
            res_map.forEach(fun(_, key){
                keys.push(key)
            }
            )
            keys.sort().forEach(fun(key){
                val value = res[key]
                if (value != null) {
                    val item = Item2(label = key, value = "" + (if ((UTSAndroid.`typeof`(value) == "object")) {
                        JSON.stringify(value)
                    } else {
                        value
                    }
                    ))
                    this.items.push(item)
                }
            }
            )
        }
        ))
    }
    open var getSystemInfoSync = ::gen_getSystemInfoSync_fn
    open fun gen_getSystemInfoSync_fn() {
        this.items = utsArrayOf<Item2>()
        val res = uni_getSystemInfoSync()
        val res_str = JSON.stringify(res)
        val res_obj = UTSAndroid.consoleDebugError(JSON.parseObject(res_str), " at pages/API/get-system-info/get-system-info.uvue:94")
        val res_map = res_obj!!.toMap()
        var keys = utsArrayOf<String>()
        res_map.forEach(fun(_, key){
            keys.push(key)
        }
        )
        keys.sort().forEach(fun(key){
            val value = res[key]
            if (value != null) {
                val item = Item2(label = key, value = "" + (if ((UTSAndroid.`typeof`(value) == "object")) {
                    JSON.stringify(value)
                } else {
                    value
                }
                ))
                this.items.push(item)
            }
        }
        )
    }
    open var jest_getSystemInfo = ::gen_jest_getSystemInfo_fn
    open fun gen_jest_getSystemInfo_fn(): GetSystemInfoResult {
        return uni_getSystemInfoSync()
    }
    open var jest_getScreenHeight_at_different_stages = ::gen_jest_getScreenHeight_at_different_stages_fn
    open fun gen_jest_getScreenHeight_at_different_stages_fn() {
        this.jest_result = (globalScreenHeight == this.screenHeightAtReady)
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
                return utsMapOf("uni-pd" to padStyleMapOf(utsMapOf("paddingLeft" to 15)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
