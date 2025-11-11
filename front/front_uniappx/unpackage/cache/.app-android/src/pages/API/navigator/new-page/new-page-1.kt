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
import io.dcloud.uniapp.extapi.navigateBack as uni_navigateBack
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo
open class GenPagesAPINavigatorNewPageNewPage1 : BasePage {
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
        onLoad(fun(options: OnLoadOptions) {
            if (options["data"] != null) {
                this.data = options["data"]!!
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
            createVNode(_component_page_head, utsMapOf("title" to "new-page-1")),
            createElementVNode("share-element", utsMapOf("share-key" to "test-share-element-key"), utsArrayOf(
                createElementVNode("image", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "250px", "height" to "176px")), "src" to "/static/shuijiao.jpg", "mode" to "scaleToFill"), null, 4)
            )),
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                createElementVNode("text", null, "onLoad 接收到参数"),
                createElementVNode("text", null, "data: " + toDisplayString(_ctx.data), 1),
                createElementVNode("button", utsMapOf("onClick" to _ctx.navigateBackWithDelta2, "class" to "uni-btn"), " 回退到上上层页面 ", 8, utsArrayOf(
                    "onClick"
                ))
            ))
        ), 64)
    }
    open var data: String by `$data`
    open var checked: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("data" to "", "checked" to false)
    }
    open var getLifeCycleNum = ::gen_getLifeCycleNum_fn
    open fun gen_getLifeCycleNum_fn(): Number {
        return state.lifeCycleNum
    }
    open var navigateBackWithDelta2 = ::gen_navigateBackWithDelta2_fn
    open fun gen_navigateBackWithDelta2_fn() {
        uni_navigateBack(NavigateBackOptions(delta = 2, success = fun(_) {
            console.log("回退上上层页面成功", " at pages/API/navigator/new-page/new-page-1.uvue:39")
        }
        , fail = fun(error) {
            console.warn("\u56DE\u9000\u4E0A\u4E0A\u5C42\u9875\u9762\u5931\u8D25: " + error.errMsg, " at pages/API/navigator/new-page/new-page-1.uvue:42")
        }
        ))
    }
    open var navigateToOnLoadWithType = ::gen_navigateToOnLoadWithType_fn
    open fun gen_navigateToOnLoadWithType_fn(type: String) {
        uni_navigateTo(NavigateToOptions(url = "/pages/API/navigator/new-page/onLoad?type=" + type))
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
