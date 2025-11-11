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
import io.dcloud.uniapp.extapi.addInterceptor as uni_addInterceptor
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo
import io.dcloud.uniapp.extapi.removeInterceptor as uni_removeInterceptor
import io.dcloud.uniapp.extapi.showToast as uni_showToast
import io.dcloud.uniapp.extapi.switchTab as uni_switchTab
open class GenPagesAPIInterceptorInterceptor : BasePage {
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
        onBeforeUnmount(fun() {
            uni_removeInterceptor("navigateTo", null)
            uni_removeInterceptor("switchTab", null)
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_navigator = resolveComponent("navigator")
        return createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("button", utsMapOf("onClick" to _ctx.addInterceptor), "添加路由拦截器", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("button", utsMapOf("onClick" to _ctx.removeInterceptor), "移除路由拦截器", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("text", null, "点击下方按钮" + toDisplayString(_ctx.msg), 1),
            createElementVNode("button", utsMapOf("onClick" to _ctx.navigateTo), "navigatorTo API跳转到测试页面", 8, utsArrayOf(
                "onClick"
            )),
            createVNode(_component_navigator, utsMapOf("url" to "./page1"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                return utsArrayOf(
                    createElementVNode("button", utsMapOf("class" to "navigatorButton"), "navigator组件跳转到测试页面")
                )
            }
            ), "_" to 1)),
            createElementVNode("button", utsMapOf("onClick" to _ctx.addSwitchTabInterceptor), "添加switchTab拦截器", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("button", utsMapOf("onClick" to _ctx.removeSwitchTabInterceptor), "移除switchTab拦截器", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("button", utsMapOf("onClick" to _ctx.switchTab), "switchTab API", 8, utsArrayOf(
                "onClick"
            ))
        ), 4)
    }
    open var msg: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("msg" to "会跳转到测试页面1")
    }
    open var addInterceptor = ::gen_addInterceptor_fn
    open fun gen_addInterceptor_fn() {
        uni_addInterceptor("navigateTo", navigateToInterceptor)
        uni_showToast(ShowToastOptions(title = "页面跳转/切换tabbar已拦截"))
        this.msg = "，路由被劫持到测试页面2"
    }
    open var removeInterceptor = ::gen_removeInterceptor_fn
    open fun gen_removeInterceptor_fn() {
        uni_removeInterceptor("navigateTo", navigateToInterceptor)
        uni_showToast(ShowToastOptions(title = "拦截器已移除"))
        this.msg = "会跳转到测试页面1"
    }
    open var addSwitchTabInterceptor = ::gen_addSwitchTabInterceptor_fn
    open fun gen_addSwitchTabInterceptor_fn() {
        uni_addInterceptor("switchTab", switchTabInterceptor)
    }
    open var removeSwitchTabInterceptor = ::gen_removeSwitchTabInterceptor_fn
    open fun gen_removeSwitchTabInterceptor_fn() {
        uni_removeInterceptor("switchTab", switchTabInterceptor)
    }
    open var navigateTo = ::gen_navigateTo_fn
    open fun gen_navigateTo_fn() {
        uni_navigateTo(NavigateToOptions(url = "./page1", success = fun(res) {
            console.log("res:", res, " at pages/API/interceptor/interceptor.uvue:91")
        }
        , fail = fun(err) {
            console.error("err:", err, " at pages/API/interceptor/interceptor.uvue:94")
        }
        , complete = fun(res) {
            console.log("res:", res, " at pages/API/interceptor/interceptor.uvue:97")
        }
        ))
    }
    open var switchTab = ::gen_switchTab_fn
    open fun gen_switchTab_fn() {
        uni_switchTab(SwitchTabOptions(url = "/pages/tabBar/component", success = fun(res) {
            console.log("res:", res, " at pages/API/interceptor/interceptor.uvue:105")
        }
        , fail = fun(err) {
            console.error("err:", err, " at pages/API/interceptor/interceptor.uvue:108")
        }
        , complete = fun(res) {
            console.log("res:", res, " at pages/API/interceptor/interceptor.uvue:111")
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
