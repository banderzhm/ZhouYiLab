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
import uts.sdk.modules.utsGetNativeView.canWebViewGoBack
import uts.sdk.modules.utsGetNativeView.canWebViewGoForward
import uts.sdk.modules.utsGetNativeView.hasNativeView
import io.dcloud.uniapp.extapi.getElementById as uni_getElementById
import io.dcloud.uniapp.extapi.showModal as uni_showModal
open class GenPagesComponentWebViewWebView : BasePage {
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
            webviewElement = uni_getElementById("web-view") as UniWebViewElement
        }
        , __ins)
        onUnload(fun() {
            webviewElement = null
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_navigator = resolveComponent("navigator")
        val _component_switch = resolveComponent("switch")
        return createElementVNode("view", utsMapOf("class" to "uni-flex-item"), utsArrayOf(
            createElementVNode("web-view", utsMapOf("id" to "web-view", "class" to "uni-flex-item", "style" to normalizeStyle(_ctx.webViewStyle), "src" to _ctx.src, "webview-styles" to _ctx.webview_styles, "horizontalScrollBarAccess" to _ctx.horizontalScrollBarAccess, "verticalScrollBarAccess" to _ctx.verticalScrollBarAccess, "bounces" to _ctx.bounces, "disable-user-select-menu" to _ctx.disableUserSelectMenu, "onMessage" to _ctx.message, "onError" to _ctx.error, "onLoading" to _ctx.loading, "onLoad" to _ctx.load, "onDownload" to _ctx.download, "onContentheightchange" to _ctx.contentheightchange, "onTouchstart" to _ctx.touchstart, "onClick" to _ctx.tap), null, 44, utsArrayOf(
                "src",
                "webview-styles",
                "horizontalScrollBarAccess",
                "verticalScrollBarAccess",
                "bounces",
                "disable-user-select-menu",
                "onMessage",
                "onError",
                "onLoading",
                "onLoad",
                "onDownload",
                "onContentheightchange",
                "onTouchstart",
                "onClick"
            )),
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                    createElementVNode("input", utsMapOf("class" to "uni-input", "confirmType" to "go", "placeholder" to "输入网址跳转", "onConfirm" to _ctx.confirm, "maxlength" to "-1"), null, 40, utsArrayOf(
                        "onConfirm"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-row uni-btn-v"), utsArrayOf(
                    createElementVNode("button", utsMapOf("class" to "uni-flex-item", "type" to "primary", "disabled" to !_ctx.canGoBack, "onClick" to _ctx.back), "后退", 8, utsArrayOf(
                        "disabled",
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "margin-left-5 uni-flex-item", "type" to "primary", "disabled" to !_ctx.canGoForward, "onClick" to _ctx.forward), "前进", 8, utsArrayOf(
                        "disabled",
                        "onClick"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-row uni-btn-v"), utsArrayOf(
                    createElementVNode("button", utsMapOf("class" to "uni-flex-item", "type" to "primary", "onClick" to _ctx.reload), "重新加载", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "margin-left-5 uni-flex-item", "type" to "primary", "onClick" to _ctx.stop), "停止加载", 8, utsArrayOf(
                        "onClick"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-row uni-btn-v"), utsArrayOf(
                    createElementVNode("button", utsMapOf("class" to "uni-flex-item", "type" to "primary", "onClick" to _ctx.nativeToWeb), "原生和Web通信", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "margin-left-5 uni-flex-item", "type" to "primary", "onClick" to _ctx.getContentHeight), "获取内容高度", 8, utsArrayOf(
                        "onClick"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-row uni-btn-v"), utsArrayOf(
                    createElementVNode("button", utsMapOf("class" to "uni-flex-item", "type" to "primary", "onClick" to _ctx.loadData), "加载页面内容", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("id" to "half-screen-toggle", "class" to "margin-left-5 uni-flex-item", "type" to "primary", "onClick" to _ctx.setHalfScreen), "宽窄屏切换", 8, utsArrayOf(
                        "onClick"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                    createVNode(_component_navigator, utsMapOf("url" to "/pages/component/web-view/web-view-scroll"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary"), "scroll-view嵌套web-view")
                        )
                    }
                    ), "_" to 1))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-row uni-btn-v"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-row uni-flex-item align-items-center"), utsArrayOf(
                        createElementVNode("text", null, "显示横向滚动条"),
                        createVNode(_component_switch, utsMapOf("checked" to true, "onChange" to _ctx.changeHorizontalScrollBarAccess), null, 8, utsArrayOf(
                            "onChange"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-row uni-flex-item align-items-center"), utsArrayOf(
                        createElementVNode("text", null, "显示竖向滚动条"),
                        createVNode(_component_switch, utsMapOf("checked" to true, "onChange" to _ctx.changeVerticalScrollBarAccess), null, 8, utsArrayOf(
                            "onChange"
                        ))
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-row uni-btn-v"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-row uni-flex-item align-items-center"), utsArrayOf(
                        createElementVNode("text", null, "开启bounces"),
                        createVNode(_component_switch, utsMapOf("checked" to true, "onChange" to _ctx.changeBounces), null, 8, utsArrayOf(
                            "onChange"
                        )),
                        createElementVNode("text", null, "禁用选择菜单"),
                        createVNode(_component_switch, utsMapOf("checked" to false, "onChange" to _ctx.changeDisableUserSelectMenu), null, 8, utsArrayOf(
                            "onChange"
                        ))
                    ))
                ))
            )),
            createElementVNode("view", utsMapOf("class" to "safe-area-inset-bottom"))
        ))
    }
    open var src: String by `$data`
    open var webview_styles: UTSJSONObject by `$data`
    open var halfWindowMode: Boolean by `$data`
    open var webviewContext: WebviewContext? by `$data`
    open var loadError: Boolean by `$data`
    open var horizontalScrollBarAccess: Boolean by `$data`
    open var verticalScrollBarAccess: Boolean by `$data`
    open var bounces: Boolean by `$data`
    open var disableUserSelectMenu: Boolean by `$data`
    open var canGoBack: Boolean by `$data`
    open var canGoForward: Boolean by `$data`
    open var autoTest: Boolean by `$data`
    open var eventLoading: UTSJSONObject? by `$data`
    open var eventLoad: UTSJSONObject? by `$data`
    open var eventError: UTSJSONObject? by `$data`
    open var eventContentHeightChange: UTSJSONObject? by `$data`
    open var pointerEvents: String by `$data`
    open var isTouchEnable: Boolean by `$data`
    open var loadingCount: Number by `$data`
    open var fullScreen: Boolean by `$data`
    open var webViewStyle: UTSJSONObject by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("src" to "https://www.dcloud.io", "webview_styles" to object : UTSJSONObject() {
            var progress = object : UTSJSONObject() {
                var color = "#FF3333"
            }
        }, "halfWindowMode" to false, "webviewContext" to null as WebviewContext?, "loadError" to false, "horizontalScrollBarAccess" to true, "verticalScrollBarAccess" to true, "bounces" to true, "disableUserSelectMenu" to false, "canGoBack" to false, "canGoForward" to false, "autoTest" to false, "eventLoading" to null as UTSJSONObject?, "eventLoad" to null as UTSJSONObject?, "eventError" to null as UTSJSONObject?, "eventContentHeightChange" to null as UTSJSONObject?, "pointerEvents" to "auto", "isTouchEnable" to false, "loadingCount" to 0, "fullScreen" to computed<Boolean>(fun(): Boolean {
            return !this.halfWindowMode
        }
        ), "webViewStyle" to computed<UTSJSONObject>(fun(): UTSJSONObject {
            return let {
                object : UTSJSONObject() {
                    var width = if (it.halfWindowMode) {
                        "50%"
                    } else {
                        "100%"
                    }
                    var `pointer-events` = it.pointerEvents
                }
            }
        }
        ))
    }
    open var setHalfScreen = ::gen_setHalfScreen_fn
    open fun gen_setHalfScreen_fn() {
        this.halfWindowMode = !this.halfWindowMode
    }
    open var getPackageName = ::gen_getPackageName_fn
    open fun gen_getPackageName_fn(): String {
        var packageName: String = ""
        return packageName
    }
    open var isProd = ::gen_isProd_fn
    open fun gen_isProd_fn(): Boolean {
        if (this.getPackageName() == "io.dcloud.hellouniappx") {
            return true
        }
        return false
    }
    open var back = ::gen_back_fn
    open fun gen_back_fn() {
        webviewElement?.back()
    }
    open var forward = ::gen_forward_fn
    open fun gen_forward_fn() {
        webviewElement?.forward()
    }
    open var reload = ::gen_reload_fn
    open fun gen_reload_fn() {
        this.loadingCount = 0
        webviewElement?.reload()
    }
    open var stop = ::gen_stop_fn
    open fun gen_stop_fn() {
        webviewElement?.stop()
    }
    open var nativeToWeb = ::gen_nativeToWeb_fn
    open fun gen_nativeToWeb_fn() {
        webviewElement?.evalJS("alert('hello uni-app x')")
    }
    open var getContentHeight = ::gen_getContentHeight_fn
    open fun gen_getContentHeight_fn(): Number {
        val height = webviewElement?.getContentHeight() ?: 0
        console.log("contentHeight", height, " at pages/component/web-view/web-view.uvue:172")
        if (!this.autoTest) {
            uni_showModal(ShowModalOptions(content = " 当前内容高度: " + height, showCancel = false))
        }
        return height
    }
    open var loadData = ::gen_loadData_fn
    open fun gen_loadData_fn() {
        webviewElement?.loadData(object : UTSJSONObject() {
            var data = "<p><a href=\"https://www.dcloud.io/hbuilderx.html\">HBuilderX</a><br/><img src=\"/unidoc/zh/uni@2x.png\"></img><h1>HBuilderX，轻巧、极速，极客编辑器</h1><p style=\"color:red;\"><small>HBuilderX，轻巧、极速，极客编辑器 </small><big>HBuilderX，轻巧、极速，极客编辑器</big><strong>HBuilderX，轻巧、极速，极客编辑器</strong><i>HBuilderX，轻巧、极速，极客编辑器 </i><u>HBuilderX，轻巧、极速，极客编辑器</u><del>HBuilderX，轻巧、极速，极客编辑器</del></p><h2>uni-app x，终极跨平台方案</h2>、<p style=\"background-color: yellow;\"><small>uni-app x，终极跨平台方案 </small><big>uni-app x，终极跨平台方案</big><strong>uni-appx，终极跨平台方案 </strong><i>uni-app x，终极跨平台方案 </i><u>uni-app x，终极跨平台方案 </u><del>uni-appx，终极跨平台方案</del></p><h3>uniCloud，js serverless云服务</h3><p style=\"text-decoration: line-through;\"><small>uniCloud，js serverless云服务 </small><big>uniCloud，jsserverless云服务</big><strong>uniCloud，js serverless云服务 </strong><i>uniCloud，js serverless云服务 </i><u>uniCloud，jsserverless云服务</u><del>uniCloud，js serverless云服务</del></p><h4>uts，大一统语言</h4><p style=\"text-align: center;\"><small>uts，大一统语言 </small><big>uts，大一统语言 </big><strong>uts，大一统语言</strong><i>uts，大一统语言</i><u>uts，大一统语言 </u><del>uts，大一统语言</del></p><h5>uniMPSdk，让你的App具备小程序能力</h5><h6>uni-admin，开源、现成的全端管理后台</h6><ul><li style=\"color: red; text-align: left;\">uni-app x，终极跨平台方案</li><li style=\"color: green; text-align: center;\">uni-app x，终极跨平台方案</li><li style=\"color: blue; text-align: right;\">uni-app x，终极跨平台方案</li></ul><a href=\"https://uniapp.dcloud.net.cn\">uni-app</a><br/><img src=\"/unidoc/zh/uni@2x.png\"></img></p>"
            var baseURL = "https://qiniu-web-assets.dcloud.net.cn"
        })
    }
    open var message = ::gen_message_fn
    open fun gen_message_fn(event: UniWebViewMessageEvent) {
        console.log(JSON.stringify(event.detail), " at pages/component/web-view/web-view.uvue:189")
    }
    open var error = ::gen_error_fn
    open fun gen_error_fn(event: UniWebViewErrorEvent) {
        this.loadError = true
        console.log(JSON.stringify(event.detail), " at pages/component/web-view/web-view.uvue:193")
        if (this.autoTest) {
            this.eventError = object : UTSJSONObject() {
                var tagName = event.target?.tagName
                var type = event.type
                var errCode = event.detail.errCode
                var errMsg = event.detail.errMsg
                var url = event.detail.url
                var fullUrl = event.detail.fullUrl
                var src = event.detail.src
            }
        }
    }
    open var loading = ::gen_loading_fn
    open fun gen_loading_fn(event: UniWebViewLoadingEvent) {
        this.loadingCount++
        console.log(JSON.stringify(event.detail), " at pages/component/web-view/web-view.uvue:208")
        if (this.autoTest) {
            this.eventLoading = object : UTSJSONObject() {
                var tagName = event.target?.tagName
                var type = event.type
                var src = event.detail.src
            }
        }
    }
    open var load = ::gen_load_fn
    open fun gen_load_fn(event: UniWebViewLoadEvent) {
        console.log(JSON.stringify(event.detail), " at pages/component/web-view/web-view.uvue:218")
        this.canGoBack = canWebViewGoBack("web-view")
        this.canGoForward = canWebViewGoForward("web-view")
        if (this.autoTest) {
            this.eventLoad = object : UTSJSONObject() {
                var tagName = event.target?.tagName
                var type = event.type
                var src = event.detail.src
                var url = event.detail.url
            }
        }
    }
    open var download = ::gen_download_fn
    open fun gen_download_fn(event: UniWebViewDownloadEvent) {
        console.log(JSON.stringify(event.detail), " at pages/component/web-view/web-view.uvue:233")
        uni_showModal(ShowModalOptions(content = "下载链接: " + event.detail.url + "\n文件大小: " + event.detail.contentLength / 1024 + "KB", showCancel = false))
    }
    open var contentheightchange = ::gen_contentheightchange_fn
    open fun gen_contentheightchange_fn(event: UniWebViewContentHeightChangeEvent) {
        console.log(JSON.stringify(event.detail), " at pages/component/web-view/web-view.uvue:240")
        this.eventContentHeightChange = object : UTSJSONObject() {
            var tagName = event.target?.tagName
            var type = event.type
            var isValidHeight = event.detail.height > 0
        }
    }
    open var confirm = ::gen_confirm_fn
    open fun gen_confirm_fn(event: UniInputConfirmEvent) {
        var url = event.detail.value
        if (!url.startsWith("https://") && !url.startsWith("http://")) {
            url = "https://" + url
        }
        this.src = url
    }
    open var changeHorizontalScrollBarAccess = ::gen_changeHorizontalScrollBarAccess_fn
    open fun gen_changeHorizontalScrollBarAccess_fn(event: UniSwitchChangeEvent) {
        this.horizontalScrollBarAccess = event.detail.value
    }
    open var changeVerticalScrollBarAccess = ::gen_changeVerticalScrollBarAccess_fn
    open fun gen_changeVerticalScrollBarAccess_fn(event: UniSwitchChangeEvent) {
        this.verticalScrollBarAccess = event.detail.value
    }
    open var changeBounces = ::gen_changeBounces_fn
    open fun gen_changeBounces_fn(event: UniSwitchChangeEvent) {
        this.bounces = event.detail.value
    }
    open var changeDisableUserSelectMenu = ::gen_changeDisableUserSelectMenu_fn
    open fun gen_changeDisableUserSelectMenu_fn(event: UniSwitchChangeEvent) {
        this.disableUserSelectMenu = event.detail.value
    }
    open var touchstart = ::gen_touchstart_fn
    open fun gen_touchstart_fn(event: UniTouchEvent) {
        if (this.autoTest) {
            this.isTouchEnable = event.touches[0].clientX > 0 && event.touches[0].clientY > 0
        }
    }
    open var tap = ::gen_tap_fn
    open fun gen_tap_fn(event: UniPointerEvent) {
        if (this.autoTest) {
            this.isTouchEnable = event.clientX > 0 && event.clientY > 0
        }
    }
    open var checkNativeWebView = ::gen_checkNativeWebView_fn
    open fun gen_checkNativeWebView_fn(): Boolean {
        return hasNativeView("web-view")
    }
    open var checkLoadingCount = ::gen_checkLoadingCount_fn
    open fun gen_checkLoadingCount_fn() {
        this.loadingCount = 0
        webviewElement?.reload()
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
                return utsMapOf("margin-left-5" to padStyleMapOf(utsMapOf("marginLeft" to 5)), "align-items-center" to padStyleMapOf(utsMapOf("alignItems" to "center")), "safe-area-inset-bottom" to padStyleMapOf(utsMapOf("height" to "var(--uni-safe-area-inset-bottom)")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
