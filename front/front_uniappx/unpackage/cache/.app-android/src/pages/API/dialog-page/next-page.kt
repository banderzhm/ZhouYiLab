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
import io.dcloud.uniapp.extapi.closeDialogPage as uni_closeDialogPage
import io.dcloud.uniapp.extapi.openDialogPage as uni_openDialogPage
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesAPIDialogPageNextPage : BasePage {
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
        onLoad(fun(_: OnLoadOptions) {
            console.log("dialogPage parent onLoad", " at pages/API/dialog-page/next-page.uvue:17")
        }
        , __ins)
        onPageShow(fun() {
            console.log("dialogPage parent onShow", " at pages/API/dialog-page/next-page.uvue:20")
        }
        , __ins)
        onReady(fun() {
            console.log("dialogPage parent onReady", " at pages/API/dialog-page/next-page.uvue:23")
        }
        , __ins)
        onPageHide(fun() {
            console.log("dialogPage parent onHide", " at pages/API/dialog-page/next-page.uvue:26")
        }
        , __ins)
        onUnload(fun() {
            console.log("dialogPage parent onUnload", " at pages/API/dialog-page/next-page.uvue:29")
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
            createElementVNode("button", utsMapOf("class" to "uni-common-mt", "onClick" to _ctx.openDialog1), "open dialog1", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("button", utsMapOf("class" to "uni-common-mt", "onClick" to _ctx.openDialog2), "open dialog2", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("button", utsMapOf("class" to "uni-common-mt", "onClick" to _ctx.openDialogPage1ToHomePage), "open dialog page 1 to home page", 8, utsArrayOf(
                "onClick"
            ))
        ))
    }
    open var openDialog1 = ::gen_openDialog1_fn
    open fun gen_openDialog1_fn() {
        uni_openDialogPage(OpenDialogPageOptions(url = "/pages/API/dialog-page/dialog-1?name=dialog1", success = fun(res) {
            console.log("openDialog1 success", res, " at pages/API/dialog-page/next-page.uvue:36")
        }
        , fail = fun(err) {
            console.log("openDialog1 fail", err, " at pages/API/dialog-page/next-page.uvue:39")
        }
        , complete = fun(res) {
            console.log("openDialog1 complete", res, " at pages/API/dialog-page/next-page.uvue:42")
        }
        ))
    }
    open var openDialog2 = ::gen_openDialog2_fn
    open fun gen_openDialog2_fn() {
        uni_openDialogPage(OpenDialogPageOptions(url = "/pages/API/dialog-page/dialog-2", disableEscBack = true, success = fun(res) {
            console.log("openDialog2 success", res, " at pages/API/dialog-page/next-page.uvue:51")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum + 1)
        }
        , fail = fun(err) {
            console.log("openDialog2 fail", err, " at pages/API/dialog-page/next-page.uvue:56")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum - 4)
        }
        , complete = fun(res) {
            console.log("openDialog2 complete", res, " at pages/API/dialog-page/next-page.uvue:61")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum + 1)
        }
        ))
    }
    open var closeDialog = ::gen_closeDialog_fn
    open fun gen_closeDialog_fn() {
        uni_closeDialogPage(CloseDialogPageOptions(success = fun(res) {
            console.log("closeDialog success", res, " at pages/API/dialog-page/next-page.uvue:70")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum + 1)
        }
        , fail = fun(err) {
            console.log("closeDialog fail", err, " at pages/API/dialog-page/next-page.uvue:75")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum - 4)
        }
        , complete = fun(res) {
            console.log("closeDialog complete", res, " at pages/API/dialog-page/next-page.uvue:80")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum + 1)
        }
        ))
    }
    open var openDialogPage1ToHomePage = ::gen_openDialogPage1ToHomePage_fn
    open fun gen_openDialogPage1ToHomePage_fn() {
        val pages = getCurrentPages()
        uni_openDialogPage(OpenDialogPageOptions(parentPage = pages[0], url = "/pages/API/dialog-page/dialog-1?name=dialog1", success = fun(res) {
            console.log("openDialogPage1ToHomePage success", res, " at pages/API/dialog-page/next-page.uvue:92")
            uni_showToast(ShowToastOptions(title = "在首页打开了 dialogPage"))
        }
        , fail = fun(err) {
            console.log("openDialogPage1ToHomePage fail", err, " at pages/API/dialog-page/next-page.uvue:96")
        }
        , complete = fun(res) {
            console.log("openDialogPage1ToHomePage complete", res, " at pages/API/dialog-page/next-page.uvue:99")
        }
        ))
    }
    open var setLifeCycleNum = ::gen_setLifeCycleNum_fn
    open fun gen_setLifeCycleNum_fn(value: Number) {
        uni.UNI740BA18.setLifeCycleNum(value)
    }
    open var getLifeCycleNum = ::gen_getLifeCycleNum_fn
    open fun gen_getLifeCycleNum_fn(): Number {
        return state.lifeCycleNum
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
