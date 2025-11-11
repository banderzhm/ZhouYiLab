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
import io.dcloud.uniapp.extapi.showToast as uni_showToast
import io.dcloud.uniapp.extapi.startPullDownRefresh as uni_startPullDownRefresh
import io.dcloud.uniapp.extapi.stopPullDownRefresh as uni_stopPullDownRefresh
open class GenPagesAPIGetCurrentPagesGetCurrentPages : BasePage {
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
            this.getPageStyle()
        }
        , __ins)
        onReady(fun() {
            this.setPageStyle(object : UTSJSONObject() {
                var androidThreeButtonNavigationBackgroundColor = "transparent"
                var androidThreeButtonNavigationStyle = "black"
            })
            this.getPageStyle()
        }
        , __ins)
        onPullDownRefresh(fun() {
            setTimeout(fun(){
                uni_stopPullDownRefresh()
            }
            , 2000)
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        val _component_switch = resolveComponent("switch")
        val _component_slider = resolveComponent("slider")
        val _component_radio = resolveComponent("radio")
        val _component_radio_group = resolveComponent("radio-group")
        return createElementVNode("scroll-view", utsMapOf("class" to "page-scroll-view"), utsArrayOf(
            createElementVNode("view", null, utsArrayOf(
                createVNode(_component_page_head, utsMapOf("title" to "getCurrentPages")),
                createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                    createElementVNode("button", utsMapOf("onClick" to _ctx._getCurrentPages), "getCurrentPages", 8, utsArrayOf(
                        "onClick"
                    )),
                    if (isTrue(_ctx.pages.length)) {
                        createElementVNode("view", utsMapOf("key" to 0, "style" to normalizeStyle(utsMapOf("padding" to "15px 0px"))), utsArrayOf(
                            createElementVNode("text", null, "当前页面栈中 " + toDisplayString(_ctx.pages.length) + " 个页面，列表如下：", 1),
                            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.pages, fun(page, index, __index, _cached): Any {
                                return createElementVNode("text", utsMapOf("key" to page.route, "style" to normalizeStyle(utsMapOf("margin-top" to "5px"))), "index: " + toDisplayString(index) + ", route: " + toDisplayString(page.route), 5)
                            }), 128)
                        ), 4)
                    } else {
                        createCommentVNode("v-if", true)
                    }
                    ,
                    createElementVNode("button", utsMapOf("class" to "uni-common-mt", "onClick" to _ctx.`check$page`), "check \$page", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "uni-common-mt", "onClick" to _ctx.checkGetParentPage), " check getParentPage ", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "uni-common-mt", "onClick" to _ctx.checkGetDialogPages), " check getDialogPages ", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("id" to "check-get-element-by-id-btn", "class" to "uni-common-mt", "onClick" to _ctx.checkGetElementById), " check getElementById ", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "uni-common-mt", "onClick" to _ctx.checkGetAndroidView), " check getAndroidView ", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "uni-common-mt", "onClick" to _ctx.checkGetIOSView), " check getIOSView ", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "uni-common-mt", "onClick" to _ctx.checkGetHTMLElement), " check getHTMLElement ", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "uni-common-mt", "onClick" to _ctx.checkGetAndroidActivity), " check getAndroidActivity ", 8, utsArrayOf(
                        "onClick"
                    ))
                )),
                createVNode(_component_page_head, utsMapOf("title" to "currentPageStyle")),
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.PageStyleArray, fun(item, index, __index, _cached): Any {
                    return createElementVNode(Fragment, null, utsArrayOf(
                        if (_ctx.currentPageStyle[item.key] != null) {
                            createElementVNode("view", utsMapOf("class" to "page-style-item", "key" to index), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "item-text"), utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "item-text-key"), toDisplayString(item.key) + ":", 1),
                                    createElementVNode("text", utsMapOf("class" to "item-text-value"), toDisplayString(_ctx.currentPageStyle[item.key]), 1)
                                )),
                                if (item.type == "boolean") {
                                    createElementVNode("view", utsMapOf("key" to 0, "class" to "set-value"), utsArrayOf(
                                        createVNode(_component_switch, utsMapOf("checked" to _ctx.currentPageStyle.getBoolean(item.key), "onChange" to fun(`$event`: Any){
                                            _ctx.switchChange(item.key, `$event` as UniSwitchChangeEvent)
                                        }), null, 8, utsArrayOf(
                                            "checked",
                                            "onChange"
                                        ))
                                    ))
                                } else {
                                    if (item.type == "number") {
                                        createElementVNode("view", utsMapOf("key" to 1, "class" to "set-value"), utsArrayOf(
                                            createVNode(_component_slider, utsMapOf("value" to _ctx.currentPageStyle.getNumber(item.key), "show-value" to true, "onChange" to fun(`$event`: Any){
                                                _ctx.sliderChange(item.key, `$event` as UniSliderChangeEvent)
                                            }), null, 8, utsArrayOf(
                                                "value",
                                                "onChange"
                                            ))
                                        ))
                                    } else {
                                        if (item.type == "string") {
                                            createElementVNode("view", utsMapOf("key" to 2, "class" to "set-value"), utsArrayOf(
                                                createVNode(_component_radio_group, utsMapOf("class" to "radio-set-value", "onChange" to fun(`$event`: Any){
                                                    _ctx.radioChange(item.key, `$event` as RadioGroupChangeEvent)
                                                }), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                                    return utsArrayOf(
                                                        createElementVNode(Fragment, null, RenderHelpers.renderList(item.value, fun(item2, index2, __index, _cached): Any {
                                                            return createVNode(_component_radio, utsMapOf("class" to "radio-value", "key" to index2, "value" to item2), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                                                return utsArrayOf(
                                                                    toDisplayString(item2)
                                                                )
                                                            }), "_" to 2), 1032, utsArrayOf(
                                                                "value"
                                                            ))
                                                        }), 128)
                                                    )
                                                }), "_" to 2), 1032, utsArrayOf(
                                                    "onChange"
                                                ))
                                            ))
                                        } else {
                                            createCommentVNode("v-if", true)
                                        }
                                    }
                                }
                            ))
                        } else {
                            createCommentVNode("v-if", true)
                        }
                    ), 64)
                }
                ), 256),
                createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "10px")), "onClick" to _ctx.goSetDisablePullDownRefresh), " go set disable pullDownRefresh ", 12, utsArrayOf(
                    "onClick"
                ))
            ))
        ))
    }
    open var checked: Boolean by `$data`
    open var pages: UTSArray<Page1> by `$data`
    open var PageStyleArray: UTSArray<PageStyleItem> by `$data`
    open var currentPageStyle: UTSJSONObject by `$data`
    open var testing: Boolean by `$data`
    open var pageStyleText: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("checked" to false, "pages" to utsArrayOf<Page1>(), "PageStyleArray" to uni.UNI740BA18.PageStyleArray as UTSArray<PageStyleItem>, "currentPageStyle" to UTSJSONObject(), "testing" to false, "pageStyleText" to computed<String>(fun(): String {
            return JSON.stringify(this.currentPageStyle)
        }
        ))
    }
    open var startPullDownRefresh = ::gen_startPullDownRefresh_fn
    open fun gen_startPullDownRefresh_fn() {
        uni_startPullDownRefresh(null)
    }
    open var _getCurrentPages = ::gen__getCurrentPages_fn
    open fun gen__getCurrentPages_fn() {
        this.pages.length = 0
        val pages = getCurrentPages()
        this.pages.push(Page1(pages[0].route))
        if (this.pages[0].route.includes("/tabBar/") || this.pages[0].route == "/") {
            this.checked = true
        }
        run {
            var i: Number = 1
            while(i < pages.length){
                this.pages.push(Page1(pages[i].route))
                if (pages[i].route.includes("/tabBar/")) {
                    this.checked = false
                }
                i++
            }
        }
    }
    open var radioChange = ::gen_radioChange_fn
    open fun gen_radioChange_fn(key: String, e: RadioGroupChangeEvent) {
        this.setStyleValue(key, e.detail.value)
    }
    open var sliderChange = ::gen_sliderChange_fn
    open fun gen_sliderChange_fn(key: String, e: UniSliderChangeEvent) {
        this.setStyleValue(key, e.detail.value)
    }
    open var switchChange = ::gen_switchChange_fn
    open fun gen_switchChange_fn(key: String, e: UniSwitchChangeEvent) {
        this.setStyleValue(key, e.detail.value)
    }
    open var setStyleValue = ::gen_setStyleValue_fn
    open fun gen_setStyleValue_fn(key: String, value: Any) {
        val style: UTSJSONObject = object : UTSJSONObject(UTSSourceMapPosition("style", "pages/API/get-current-pages/get-current-pages.uvue", 152, 15)) {
        }
        style[key] = value
        this.setPageStyle(style)
        this.getPageStyle()
    }
    open var getPageStyle = ::gen_getPageStyle_fn
    open fun gen_getPageStyle_fn(): UTSJSONObject {
        val pages = getCurrentPages()
        val currentPage = pages[pages.length - 1]
        this.currentPageStyle = currentPage.getPageStyle()
        console.log(this.currentPageStyle, " at pages/API/get-current-pages/get-current-pages.uvue:161")
        return this.currentPageStyle
    }
    open var setPageStyle = ::gen_setPageStyle_fn
    open fun gen_setPageStyle_fn(style: UTSJSONObject) {
        console.log("setPageStyle:", style, " at pages/API/get-current-pages/get-current-pages.uvue:165")
        val pages = getCurrentPages()
        val currentPage = pages[pages.length - 1]
        currentPage.setPageStyle(style)
    }
    open var goSetDisablePullDownRefresh = ::gen_goSetDisablePullDownRefresh_fn
    open fun gen_goSetDisablePullDownRefresh_fn() {
        uni_navigateTo(NavigateToOptions(url = "/pages/API/get-current-pages/set-page-style-disable-pull-down-refresh"))
    }
    open var getCurrentPage = ::gen_getCurrentPage_fn
    open fun gen_getCurrentPage_fn(): UniPage {
        val pages = getCurrentPages()
        return pages[pages.length - 1]
    }
    open var `check$page` = ::`gen_check$page_fn`
    open fun `gen_check$page_fn`(): Boolean {
        val page = this.getCurrentPage()
        var res = this.`$page` === page
        if (this.testing && res) {
            res = page.options["test"] == "123"
            if (res) {
                res = page.route == "pages/API/get-current-pages/get-current-pages"
            }
        }
        console.log("check \$page", res, " at pages/API/get-current-pages/get-current-pages.uvue:188")
        uni_showToast(if (res) {
            ShowToastOptions(title = "check success")
        } else {
            ShowToastOptions(title = "check fail", icon = "error")
        }
        )
        return res
    }
    open var checkGetParentPage = ::gen_checkGetParentPage_fn
    open fun gen_checkGetParentPage_fn(): Boolean {
        val page = this.getCurrentPage()
        val parentPage = page.getParentPage()
        val res = parentPage == null
        console.log("check getParentPage", res, " at pages/API/get-current-pages/get-current-pages.uvue:196")
        uni_showToast(if (res) {
            ShowToastOptions(title = "check success")
        } else {
            ShowToastOptions(title = "check fail", icon = "error")
        }
        )
        return res
    }
    open var checkGetDialogPages = ::gen_checkGetDialogPages_fn
    open fun gen_checkGetDialogPages_fn(): Boolean {
        val page = this.getCurrentPage()
        val dialogPages = page.getDialogPages()
        val res = UTSArray.isArray(dialogPages) && dialogPages.length == 0
        uni_showToast(if (res) {
            ShowToastOptions(title = "check success")
        } else {
            ShowToastOptions(title = "check fail", icon = "error")
        }
        )
        console.log("check getDialogPages", res, " at pages/API/get-current-pages/get-current-pages.uvue:205")
        return res
    }
    open var checkGetElementById = ::gen_checkGetElementById_fn
    open fun gen_checkGetElementById_fn(): Boolean {
        val page = this.getCurrentPage()
        val element = page.getElementById("check-get-element-by-id-btn")
        var res = element != null
        console.log("check getElementById", res, " at pages/API/get-current-pages/get-current-pages.uvue:219")
        uni_showToast(if (res) {
            ShowToastOptions(title = "check success")
        } else {
            ShowToastOptions(title = "check fail", icon = "error")
        }
        )
        return res
    }
    open var checkGetAndroidView = ::gen_checkGetAndroidView_fn
    open fun gen_checkGetAndroidView_fn(): Boolean {
        val page = this.getCurrentPage()
        val androidView = page.getAndroidView<android.view.View>()
        val res = androidView != null
        console.log("check getAndroidView", res, " at pages/API/get-current-pages/get-current-pages.uvue:227")
        uni_showToast(if (res) {
            ShowToastOptions(title = "check success")
        } else {
            ShowToastOptions(title = "check fail", icon = "error")
        }
        )
        return res
    }
    open var checkGetIOSView = ::gen_checkGetIOSView_fn
    open fun gen_checkGetIOSView_fn(): Boolean {
        val page = this.getCurrentPage()
        val IOSView = page.getIOSView()
        val res = IOSView != null
        console.log("check getIOSView", res, " at pages/API/get-current-pages/get-current-pages.uvue:235")
        uni_showToast(if (res) {
            ShowToastOptions(title = "check success")
        } else {
            ShowToastOptions(title = "仅 IOS uts 插件环境支持", icon = "error")
        }
        )
        return res
    }
    open var checkGetHTMLElement = ::gen_checkGetHTMLElement_fn
    open fun gen_checkGetHTMLElement_fn(): Boolean {
        val page = this.getCurrentPage()
        val HTMLView = page.getHTMLElement()
        val res = HTMLView != null
        console.log("check getHTMLElement", res, " at pages/API/get-current-pages/get-current-pages.uvue:243")
        uni_showToast(if (res) {
            ShowToastOptions(title = "check success")
        } else {
            ShowToastOptions(title = "check fail", icon = "error")
        }
        )
        return res
    }
    open var checkGetAndroidActivity = ::gen_checkGetAndroidActivity_fn
    open fun gen_checkGetAndroidActivity_fn(): Boolean {
        val page = this.getCurrentPage()
        val activity = page.getAndroidActivity()
        val res = activity != null
        console.log("check getAndroidActivity", res, " at pages/API/get-current-pages/get-current-pages.uvue:251")
        uni_showToast(if (res) {
            ShowToastOptions(title = "check success")
        } else {
            ShowToastOptions(title = "check getAndroidActivity", icon = "error")
        }
        )
        return res
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
                return utsMapOf("page" to padStyleMapOf(utsMapOf("flex" to 1, "paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10)), "page-style" to padStyleMapOf(utsMapOf("marginTop" to 15)), "page-style-item" to padStyleMapOf(utsMapOf("paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10, "marginTop" to 10, "backgroundColor" to "#ffffff", "borderTopLeftRadius" to 5, "borderTopRightRadius" to 5, "borderBottomRightRadius" to 5, "borderBottomLeftRadius" to 5)), "item-text" to padStyleMapOf(utsMapOf("flexDirection" to "row")), "item-text-key" to padStyleMapOf(utsMapOf("fontWeight" to "bold")), "item-text-value" to padStyleMapOf(utsMapOf("marginLeft" to 5)), "set-value" to padStyleMapOf(utsMapOf("marginTop" to 10)), "radio-set-value" to padStyleMapOf(utsMapOf("flexDirection" to "row")), "radio-value" to padStyleMapOf(utsMapOf("marginLeft" to 10)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
