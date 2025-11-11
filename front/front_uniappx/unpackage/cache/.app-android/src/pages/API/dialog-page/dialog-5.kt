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
open class GenPagesAPIDialogPageDialog5 : BasePage {
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
        onPageShow(fun() {
            console.log("dialog 5 onShow", " at pages/API/dialog-page/dialog-5.uvue:48")
        }
        , __ins)
        onReady(fun() {
            val currentPage = this.`$page`
            this.pageBody = currentPage.pageBody
            this.safeAreaInsets = currentPage.safeAreaInsets
            this.width = currentPage.width
            this.height = currentPage.height
            this.statusBarHeight = currentPage.statusBarHeight
        }
        , __ins)
        onUnload(fun() {
            console.log("dialog 5 onUnload", " at pages/API/dialog-page/dialog-5.uvue:61")
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("view", utsMapOf("class" to "dialog-container"), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "dialog-content"), utsArrayOf(
                createElementVNode("text", null, "title: " + toDisplayString(_ctx.title), 1),
                if (_ctx.pageBody != null) {
                    createElementVNode("view", utsMapOf("key" to 0, "class" to "uni-common-mt flex-row"), utsArrayOf(
                        "pageBody: { top: ",
                        createElementVNode("text", utsMapOf("id" to "page-body-top"), toDisplayString(_ctx.pageBody!!.top), 1),
                        ", right: ",
                        createElementVNode("text", utsMapOf("id" to "page-body-right"), toDisplayString(_ctx.pageBody!!.right), 1),
                        ", bottom: ",
                        createElementVNode("text", utsMapOf("id" to "page-body-bottom"), toDisplayString(_ctx.pageBody!!.bottom), 1),
                        ", left: ",
                        createElementVNode("text", utsMapOf("id" to "page-body-left"), toDisplayString(_ctx.pageBody!!.left), 1),
                        ", width: ",
                        createElementVNode("text", utsMapOf("id" to "page-body-width"), toDisplayString(_ctx.pageBody!!.width), 1),
                        ", height: ",
                        createElementVNode("text", utsMapOf("id" to "page-body-height"), toDisplayString(_ctx.pageBody!!.height), 1),
                        " } "
                    ))
                } else {
                    createCommentVNode("v-if", true)
                }
                ,
                if (_ctx.safeAreaInsets != null) {
                    createElementVNode("view", utsMapOf("key" to 1, "class" to "uni-common-mt flex-row"), utsArrayOf(
                        "safeAreaInsets: { top: ",
                        createElementVNode("text", utsMapOf("id" to "page-safe-area-insets-top"), toDisplayString(_ctx.safeAreaInsets!!.top), 1),
                        ", right: ",
                        createElementVNode("text", utsMapOf("id" to "page-safe-area-insets-right"), toDisplayString(_ctx.safeAreaInsets!!.right), 1),
                        ", bottom: ",
                        createElementVNode("text", utsMapOf("id" to "page-safe-area-insets-bottom"), toDisplayString(_ctx.safeAreaInsets!!.bottom), 1),
                        ", left: ",
                        createElementVNode("text", utsMapOf("id" to "page-safe-area-insets-left"), toDisplayString(_ctx.safeAreaInsets!!.left), 1),
                        "} "
                    ))
                } else {
                    createCommentVNode("v-if", true)
                }
                ,
                if (_ctx.width != null) {
                    createElementVNode("view", utsMapOf("key" to 2, "class" to "uni-common-mt flex-row"), utsArrayOf(
                        "width: ",
                        createElementVNode("text", null, toDisplayString(_ctx.width!!), 1)
                    ))
                } else {
                    createCommentVNode("v-if", true)
                }
                ,
                if (_ctx.height != null) {
                    createElementVNode("view", utsMapOf("key" to 3, "class" to "uni-common-mt flex-row"), utsArrayOf(
                        "height: ",
                        createElementVNode("text", null, toDisplayString(_ctx.height!!), 1)
                    ))
                } else {
                    createCommentVNode("v-if", true)
                }
                ,
                if (_ctx.statusBarHeight != null) {
                    createElementVNode("view", utsMapOf("key" to 4, "class" to "uni-common-mt flex-row"), utsArrayOf(
                        "statusBarHeight: ",
                        createElementVNode("text", null, toDisplayString(_ctx.statusBarHeight!!), 1)
                    ))
                } else {
                    createCommentVNode("v-if", true)
                }
                ,
                createElementVNode("button", utsMapOf("class" to "mt-10", "onClick" to _ctx.closeThisDialog), " closeThisDialog ", 8, utsArrayOf(
                    "onClick"
                ))
            ))
        ))
    }
    open var title: String by `$data`
    open var pageBody: UniPageBody? by `$data`
    open var safeAreaInsets: UniSafeAreaInsets? by `$data`
    open var width: Number? by `$data`
    open var height: Number? by `$data`
    open var statusBarHeight: Number? by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "dialog 5", "pageBody" to null as UniPageBody?, "safeAreaInsets" to null as UniSafeAreaInsets?, "width" to null as Number?, "height" to null as Number?, "statusBarHeight" to null as Number?)
    }
    open var closeThisDialog = ::gen_closeThisDialog_fn
    open fun gen_closeThisDialog_fn() {
        uni_closeDialogPage(CloseDialogPageOptions(dialogPage = this.`$page`, success = fun(res) {
            console.log("closeThisDialog success", res, " at pages/API/dialog-page/dialog-5.uvue:68")
        }
        , fail = fun(err) {
            console.log("closeThisDialog fail", err, " at pages/API/dialog-page/dialog-5.uvue:71")
        }
        , complete = fun(res) {
            console.log("closeThisDialog complete", res, " at pages/API/dialog-page/dialog-5.uvue:74")
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
                return utsMapOf("dialog-container" to padStyleMapOf(utsMapOf("width" to "100%", "height" to "100%", "backgroundColor" to "rgba(0,0,0,0.3)", "display" to "flex", "justifyContent" to "center", "alignItems" to "center")), "dialog-content" to padStyleMapOf(utsMapOf("width" to "80%", "paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10, "backgroundColor" to "#ffffff", "borderTopLeftRadius" to 6, "borderTopRightRadius" to 6, "borderBottomRightRadius" to 6, "borderBottomLeftRadius" to 6)), "mt-10" to padStyleMapOf(utsMapOf("marginTop" to 10)), "flex-row" to padStyleMapOf(utsMapOf("flexDirection" to "row", "flexWrap" to "wrap")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
