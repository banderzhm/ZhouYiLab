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
import io.dcloud.uniapp.extapi.getLaunchOptionsSync as uni_getLaunchOptionsSync
open class GenPagesAPIGetLaunchOptionsSyncGetLaunchOptionsSync : BasePage {
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
        onReady(fun() {
            this.compareOnLaunchRes()
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        return createElementVNode(Fragment, null, utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to "getLaunchOptionsSync")),
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                createElementVNode("button", utsMapOf("onClick" to _ctx.getLaunchOptionsSync), "getLaunchOptionsSync", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("view", utsMapOf("class" to "uni-common-mt"), utsArrayOf(
                    createElementVNode("text", null, "应用本次启动路径："),
                    createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-top" to "5px"))), toDisplayString(_ctx.launchOptionsPath), 5)
                )),
                createElementVNode("view", utsMapOf("class" to "uni-common-mt"), utsArrayOf(
                    createElementVNode("text", null, "应用本次启动："),
                    createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-top" to "5px"))), toDisplayString(_ctx.launchOptionsString), 5)
                ))
            ))
        ), 64)
    }
    open var checked: Boolean by `$data`
    open var homePagePath: String by `$data`
    open var launchOptionsPath: String by `$data`
    open var launchOptionsString: String by `$data`
    open var testResult: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("checked" to false, "homePagePath" to "pages/tabBar/component", "launchOptionsPath" to "", "launchOptionsString" to "", "testResult" to false)
    }
    open var compareOnLaunchRes = ::gen_compareOnLaunchRes_fn
    open fun gen_compareOnLaunchRes_fn() {
        val launchOptions = uni_getLaunchOptionsSync()
        this.launchOptionsString = JSON.stringify(launchOptions, null, 2)
        val app = getApp()
        val appOnLaunch = app.globalData.launchOptions
        val isPathSame = launchOptions.path == appOnLaunch.path
        val isAppSchemeSame = launchOptions.appScheme == appOnLaunch.appScheme
        val isAppLinkSame = launchOptions.appLink == appOnLaunch.appLink
        this.testResult = isPathSame && isAppSchemeSame && isAppLinkSame
    }
    open var getLaunchOptionsSync = ::gen_getLaunchOptionsSync_fn
    open fun gen_getLaunchOptionsSync_fn() {
        val launchOptions = uni_getLaunchOptionsSync()
        this.launchOptionsPath = launchOptions.path
        if (launchOptions.path == this.homePagePath) {
            this.checked = true
        }
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
