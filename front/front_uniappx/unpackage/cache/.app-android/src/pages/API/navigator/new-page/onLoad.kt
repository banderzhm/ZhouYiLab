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
import io.dcloud.uniapp.extapi.hideLoading as uni_hideLoading
import io.dcloud.uniapp.extapi.navigateBack as uni_navigateBack
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo
import io.dcloud.uniapp.extapi.reLaunch as uni_reLaunch
import io.dcloud.uniapp.extapi.redirectTo as uni_redirectTo
import io.dcloud.uniapp.extapi.showActionSheet as uni_showActionSheet
import io.dcloud.uniapp.extapi.showLoading as uni_showLoading
import io.dcloud.uniapp.extapi.showModal as uni_showModal
import io.dcloud.uniapp.extapi.showToast as uni_showToast
import io.dcloud.uniapp.extapi.switchTab as uni_switchTab
open class GenPagesAPINavigatorNewPageOnLoad : BasePage {
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
            val type = options["type"]
            when (type) {
                "adjustData" -> 
                    this.adjustData()
                "navigateTo" -> 
                    this.navigateTo()
                "navigateBack" -> 
                    this.navigateBack()
                "redirectTo" -> 
                    this.redirectTo()
                "reLaunch" -> 
                    this.reLaunch()
                "switchTab" -> 
                    this.switchTab()
                "showToast" -> 
                    this.showToast()
                "showLoading" -> 
                    this.showLoading()
                "showModal" -> 
                    this.showModal()
                "showActionSheet" -> 
                    this.showActionSheet()
            }
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                createVNode(_component_page_head, utsMapOf("title" to "onLoad 生命周期调用 uni api 测试")),
                if (isTrue(_ctx.isTrue)) {
                    createElementVNode("text", utsMapOf("key" to 0), "v-if with true")
                } else {
                    createCommentVNode("v-if", true)
                }
                ,
                if (isTrue(_ctx.isFalse)) {
                    createElementVNode("text", utsMapOf("key" to 1), "v-if with false")
                } else {
                    createCommentVNode("v-if", true)
                }
                ,
                withDirectives(createElementVNode("text", null, "v-show with true", 512), utsArrayOf(
                    utsArrayOf(
                        vShow,
                        _ctx.isTrue
                    )
                )),
                withDirectives(createElementVNode("text", null, "v-show with false", 512), utsArrayOf(
                    utsArrayOf(
                        vShow,
                        _ctx.isFalse
                    )
                )),
                createElementVNode("text", null, "msg: " + toDisplayString(_ctx.msg), 1)
            ))
        ), 4)
    }
    open var isTrue: Boolean by `$data`
    open var isFalse: Boolean by `$data`
    open var msg: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("isTrue" to false, "isFalse" to true, "msg" to "default msg")
    }
    open var adjustData = ::gen_adjustData_fn
    open fun gen_adjustData_fn() {
        this.isTrue = true
        this.isFalse = false
        this.msg = "new msg"
    }
    open var navigateTo = ::gen_navigateTo_fn
    open fun gen_navigateTo_fn() {
        uni_navigateTo(NavigateToOptions(url = "/pages/API/navigator/new-page/new-page-3"))
    }
    open var navigateBack = ::gen_navigateBack_fn
    open fun gen_navigateBack_fn() {
        uni_navigateBack(null)
    }
    open var redirectTo = ::gen_redirectTo_fn
    open fun gen_redirectTo_fn() {
        uni_redirectTo(RedirectToOptions(url = "/pages/API/navigator/new-page/new-page-3"))
    }
    open var reLaunch = ::gen_reLaunch_fn
    open fun gen_reLaunch_fn() {
        uni_reLaunch(ReLaunchOptions(url = "/pages/API/navigator/new-page/new-page-3"))
    }
    open var switchTab = ::gen_switchTab_fn
    open fun gen_switchTab_fn() {
        uni_switchTab(SwitchTabOptions(url = "/pages/tabBar/component"))
    }
    open var showToast = ::gen_showToast_fn
    open fun gen_showToast_fn() {
        uni_showToast(ShowToastOptions(title = "test title", icon = "success", duration = 2000))
    }
    open var showLoading = ::gen_showLoading_fn
    open fun gen_showLoading_fn() {
        uni_showLoading(ShowLoadingOptions(title = "test title"))
        setTimeout(fun(){
            uni_hideLoading()
        }
        , 2000)
    }
    open var showModal = ::gen_showModal_fn
    open fun gen_showModal_fn() {
        uni_showModal(ShowModalOptions(title = "test title", content = "test content"))
    }
    open var showActionSheet = ::gen_showActionSheet_fn
    open fun gen_showActionSheet_fn() {
        uni_showActionSheet(ShowActionSheetOptions(title = "test title", itemList = utsArrayOf(
            "1",
            "2",
            "3"
        )))
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
