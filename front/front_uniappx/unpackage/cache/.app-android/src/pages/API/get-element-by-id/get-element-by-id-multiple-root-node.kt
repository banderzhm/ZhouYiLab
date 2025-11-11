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
import io.dcloud.uniapp.extapi.getElementById as uni_getElementById
open class GenPagesAPIGetElementByIdGetElementByIdMultipleRootNode : BasePage {
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
        return createElementVNode(Fragment, null, utsArrayOf(
            createVNode(_component_page_head, utsMapOf("id" to "page-head", "title" to "getElementById-multiple-root-node")),
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                createElementVNode("text", utsMapOf("id" to "text"), "this is text"),
                createElementVNode("view", utsMapOf("id" to "view", "class" to "uni-common-mt", "style" to normalizeStyle(utsMapOf("border" to "1px solid red"))), "this is view", 4),
                createElementVNode("button", utsMapOf("class" to "uni-btn", "onClick" to _ctx.changePageHeadBackgroundColor), " 修改 page-head 背景色 ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "uni-btn", "onClick" to _ctx.changeTextColor), "修改 text 字体颜色", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "uni-btn", "onClick" to _ctx.changeViewStyle), " 修改 view 宽高及背景色 ", 8, utsArrayOf(
                    "onClick"
                ))
            ))
        ), 64)
    }
    open var checked: Boolean by `$data`
    open var homePagePath: String by `$data`
    open var launchOptionsPath: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("checked" to false, "homePagePath" to "/pages/tabBar/component", "launchOptionsPath" to "")
    }
    open var getElementByNotExistId = ::gen_getElementByNotExistId_fn
    open fun gen_getElementByNotExistId_fn(): Element? {
        return uni_getElementById("not-exist-id")
    }
    open var changePageHeadBackgroundColor = ::gen_changePageHeadBackgroundColor_fn
    open fun gen_changePageHeadBackgroundColor_fn() {
        val pageHead = uni_getElementById("page-head")!!
        pageHead.style.setProperty("background-color", "red")
    }
    open var changeTextColor = ::gen_changeTextColor_fn
    open fun gen_changeTextColor_fn() {
        val text = uni_getElementById("text")!!
        text.style.setProperty("color", "red")
    }
    open var changeViewStyle = ::gen_changeViewStyle_fn
    open fun gen_changeViewStyle_fn() {
        val view = uni_getElementById<UniViewElement>("view")
        if (view != null) {
            view.style.setProperty("width", "90%")
            view.style.setProperty("height", "50px")
            view.style.setProperty("background-color", "#007AFF")
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
