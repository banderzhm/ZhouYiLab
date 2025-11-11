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
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo
import io.dcloud.uniapp.extapi.setNavigationBarColor as uni_setNavigationBarColor
open class GenPagesAPISetNavigationBarColorSetNavigationBarColor : BasePage {
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
            createVNode(_component_page_head, utsMapOf("title" to "setNavigationBarColor")),
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt"), utsArrayOf(
                createElementVNode("button", utsMapOf("onClick" to _ctx.setNavigationBarColor1, "class" to "uni-btn"), " 设置导航条背景绿色，标题白色 ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("onClick" to _ctx.setNavigationBarColor2, "class" to "uni-btn"), " 设置导航条背景红色，标题黑色 ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("onClick" to _ctx.goNavbarLite, "class" to "uni-btn"), " 跳转自定义导航栏页面 ", 8, utsArrayOf(
                    "onClick"
                ))
            ))
        ), 64)
    }
    open var setNavigationBarColor1 = ::gen_setNavigationBarColor1_fn
    open fun gen_setNavigationBarColor1_fn() {
        uni_setNavigationBarColor(SetNavigationBarColorOptions(frontColor = "#ffffff", backgroundColor = "#00ff00", success = fun(_){
            console.log("setNavigationBarColor success", " at pages/API/set-navigation-bar-color/set-navigation-bar-color.uvue:26")
            this.setLifeCycleNum(state.lifeCycleNum + 1)
        }
        , fail = fun(_){
            console.log("setNavigationBarColor fail", " at pages/API/set-navigation-bar-color/set-navigation-bar-color.uvue:30")
            this.setLifeCycleNum(state.lifeCycleNum - 1)
        }
        , complete = fun(_){
            console.log("setNavigationBarColor complete", " at pages/API/set-navigation-bar-color/set-navigation-bar-color.uvue:34")
            this.setLifeCycleNum(state.lifeCycleNum + 1)
        }
        ))
    }
    open var setNavigationBarColor2 = ::gen_setNavigationBarColor2_fn
    open fun gen_setNavigationBarColor2_fn() {
        uni_setNavigationBarColor(SetNavigationBarColorOptions(frontColor = "#000000", backgroundColor = "#ff0000", success = fun(_){
            console.log("setNavigationBarColor success", " at pages/API/set-navigation-bar-color/set-navigation-bar-color.uvue:44")
            this.setLifeCycleNum(state.lifeCycleNum + 1)
        }
        , fail = fun(_){
            console.log("setNavigationBarColor fail", " at pages/API/set-navigation-bar-color/set-navigation-bar-color.uvue:48")
            this.setLifeCycleNum(state.lifeCycleNum - 1)
        }
        , complete = fun(_){
            console.log("setNavigationBarColor complete", " at pages/API/set-navigation-bar-color/set-navigation-bar-color.uvue:52")
            this.setLifeCycleNum(state.lifeCycleNum + 1)
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
    open var goNavbarLite = ::gen_goNavbarLite_fn
    open fun gen_goNavbarLite_fn() {
        uni_navigateTo(NavigateToOptions(url = "/pages/template/navbar-lite/navbar-lite"))
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
