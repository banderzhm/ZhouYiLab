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
import io.dcloud.uniapp.extapi.setNavigationBarTitle as uni_setNavigationBarTitle
open class GenPagesAPISetNavigationBarTitleSetNavigationBarTitle : BasePage {
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
            createVNode(_component_page_head, utsMapOf("title" to "setNavigationBarTitle")),
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt"), utsArrayOf(
                createElementVNode("button", utsMapOf("onClick" to _ctx.setNavigationBarNewTitle, "class" to "uni-btn"), " 设置当前页面标题为: " + toDisplayString(_ctx.newTitle), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("onClick" to _ctx.setNavigationBarLongTitle, "class" to "uni-btn"), " 设置超长标题 ", 8, utsArrayOf(
                    "onClick"
                ))
            ))
        ), 64)
    }
    open var newTitle: String by `$data`
    open var longTitle: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("newTitle" to "new title", "longTitle" to "long title long title long title long title long title long title long title long title long title long title")
    }
    open var setNavigationBarNewTitle = ::gen_setNavigationBarNewTitle_fn
    open fun gen_setNavigationBarNewTitle_fn() {
        uni_setNavigationBarTitle(SetNavigationBarTitleOptions(title = this.newTitle, success = fun(_){
            console.log("setNavigationBarTitle success", " at pages/API/set-navigation-bar-title/set-navigation-bar-title.uvue:64")
            this.setLifeCycleNum(state.lifeCycleNum + 1)
        }
        , fail = fun(_){
            console.log("setNavigationBarTitle fail", " at pages/API/set-navigation-bar-title/set-navigation-bar-title.uvue:68")
            this.setLifeCycleNum(state.lifeCycleNum - 1)
        }
        , complete = fun(_){
            console.log("setNavigationBarTitle complete", " at pages/API/set-navigation-bar-title/set-navigation-bar-title.uvue:72")
            this.setLifeCycleNum(state.lifeCycleNum + 1)
        }
        ))
    }
    open var setNavigationBarLongTitle = ::gen_setNavigationBarLongTitle_fn
    open fun gen_setNavigationBarLongTitle_fn() {
        uni_setNavigationBarTitle(SetNavigationBarTitleOptions(title = this.longTitle, success = fun(_) {
            console.log("setNavigationBarTitle success", " at pages/API/set-navigation-bar-title/set-navigation-bar-title.uvue:81")
        }
        , fail = fun(_) {
            console.log("setNavigationBarTitle fail", " at pages/API/set-navigation-bar-title/set-navigation-bar-title.uvue:84")
        }
        , complete = fun(_) {
            console.log("setNavigationBarTitle complete", " at pages/API/set-navigation-bar-title/set-navigation-bar-title.uvue:87")
        }
        ))
    }
    open var getLifeCycleNum = ::gen_getLifeCycleNum_fn
    open fun gen_getLifeCycleNum_fn(): Number {
        return state.lifeCycleNum
    }
    open var setLifeCycleNum = ::gen_setLifeCycleNum_fn
    open fun gen_setLifeCycleNum_fn(num: Number) {
        uni.UNI740BA18.setLifeCycleNum(num)
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
