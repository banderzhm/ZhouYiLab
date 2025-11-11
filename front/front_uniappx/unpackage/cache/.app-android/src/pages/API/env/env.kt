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
import io.dcloud.uniapp.extapi.env as uni_env
import io.dcloud.uniapp.extapi.getFileSystemManager as uni_getFileSystemManager
open class GenPagesAPIEnvEnv : BasePage {
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
        val _component_boolean_data = resolveEasyComponent("boolean-data", GenComponentsBooleanDataBooleanDataClass)
        return createElementVNode(Fragment, null, utsArrayOf(
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "12px"))), utsArrayOf(
                createVNode(_component_page_head, utsMapOf("title" to "环境变量 - 文件系统")),
                createElementVNode("button", utsMapOf("class" to "button", "type" to "primary", "onClick" to fun(){
                    _ctx.getDirInfo(_ctx.uni.env.USER_DATA_PATH)
                }
                ), "USER_DATA_PATH", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "button", "type" to "primary", "onClick" to fun(){
                    _ctx.getDirInfo(_ctx.cachePath)
                }
                ), "CACHE_PATH", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "button", "type" to "primary", "onClick" to fun(){
                    _ctx.getDirInfo(_ctx.sandboxPath)
                }
                ), "SANDBOX_PATH", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "button", "type" to "primary", "onClick" to fun(){
                    _ctx.getDirInfo(_ctx.androidInternalSandboxPath)
                }
                ), "ANDROID_INTERNAL_SANDBOX_PATH", 8, utsArrayOf(
                    "onClick"
                )),
                createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "是否递归获取", "onChange" to _ctx.switchRecursive), null, 8, utsArrayOf(
                    "onChange"
                ))
            ), 4),
            createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1", "padding" to "16px 0px"))), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "result"), toDisplayString(_ctx.result), 1),
                createElementVNode("text", utsMapOf("class" to "error"), toDisplayString(_ctx.error), 1),
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.list, fun(stat, index, __index, _cached): Any {
                    return createElementVNode("view", utsMapOf("class" to "stat", "key" to index), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "path"), toDisplayString(stat.path), 1),
                        createElementVNode("text", utsMapOf("class" to "size"), toDisplayString(stat.size), 1)
                    ))
                }
                ), 128)
            ), 4)
        ), 64)
    }
    open var result: String by `$data`
    open var error: String by `$data`
    open var list: UTSArray<StatInfo> by `$data`
    open var recursive: Boolean by `$data`
    open var cachePath: String by `$data`
    open var sandboxPath: String by `$data`
    open var androidInternalSandboxPath: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("result" to "", "error" to "", "list" to utsArrayOf<StatInfo>(), "recursive" to false, "cachePath" to uni_env.CACHE_PATH as String, "sandboxPath" to uni_env.SANDBOX_PATH as String, "androidInternalSandboxPath" to uni_env.ANDROID_INTERNAL_SANDBOX_PATH as String)
    }
    open var switchRecursive = ::gen_switchRecursive_fn
    open fun gen_switchRecursive_fn() {
        this.recursive = !this.recursive
    }
    open var getDirInfo = ::gen_getDirInfo_fn
    open fun gen_getDirInfo_fn(dirPath: String) {
        val fm = uni_getFileSystemManager()
        this.list = utsArrayOf()
        fm.stat(StatOptions(path = dirPath, recursive = this.recursive, success = fun(res: StatSuccessResult){
            this.result = "\u83B7\u53D6 \"" + dirPath + "\" \u6210\u529F(success)"
            console.log(this.result, " at pages/API/env/env.uvue:59")
            res.stats.forEach(fun(item){
                this.list.push(StatInfo(path = item.path, size = "" + item.stats.size + " Bytes"))
            }
            )
        }
        , fail = fun(err){
            this.result = "\u83B7\u53D6 \"" + dirPath + "\" \u5931\u8D25(fail)"
            console.log(this.result, " at pages/API/env/env.uvue:69")
            this.error = JSON.stringify(err)
            console.log(this.error, " at pages/API/env/env.uvue:71")
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
                return utsMapOf("button" to padStyleMapOf(utsMapOf("marginBottom" to 4, "whiteSpace" to "nowrap", "textOverflow" to "ellipsis")), "result" to padStyleMapOf(utsMapOf("fontSize" to 18, "fontWeight" to "bold", "textAlign" to "center", "width" to "100%")), "error" to padStyleMapOf(utsMapOf("color" to "#B22222")), "stat" to padStyleMapOf(utsMapOf("paddingTop" to 8, "paddingRight" to 16, "paddingBottom" to 8, "paddingLeft" to 16)), "path" to padStyleMapOf(utsMapOf("color" to "#A9A9A9")), "size" to padStyleMapOf(utsMapOf("color" to "#A9A9A9")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
