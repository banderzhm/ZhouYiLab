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
import io.dcloud.uniapp.extapi.getSystemInfoSync as uni_getSystemInfoSync
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo
import io.dcloud.uniapp.extapi.openDialogPage as uni_openDialogPage
open class GenPagesAPIDialogPageDialogPage : BasePage {
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
            console.log("dialogPage parent onLoad", " at pages/API/dialog-page/dialog-page.uvue:116")
        }
        , __ins)
        onPageShow(fun() {
            console.log("dialogPage parent onShow", " at pages/API/dialog-page/dialog-page.uvue:119")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum + 10)
        }
        , __ins)
        onReady(fun() {
            console.log("dialogPage parent onReady", " at pages/API/dialog-page/dialog-page.uvue:123")
            val currentPage = this.`$page`
            this.pageBody = currentPage.pageBody
            this.safeAreaInsets = currentPage.safeAreaInsets
            this.width = currentPage.width
            this.height = currentPage.height
            this.statusBarHeight = currentPage.statusBarHeight
        }
        , __ins)
        onPageHide(fun() {
            console.log("dialogPage parent onHide", " at pages/API/dialog-page/dialog-page.uvue:134")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum - 10)
        }
        , __ins)
        onUnload(fun() {
            console.log("dialogPage parent onUnload", " at pages/API/dialog-page/dialog-page.uvue:138")
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_radio = resolveComponent("radio")
        val _component_radio_group = resolveComponent("radio-group")
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
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
                        createElementVNode("text", utsMapOf("id" to "page-width"), toDisplayString(_ctx.width!!), 1)
                    ))
                } else {
                    createCommentVNode("v-if", true)
                }
                ,
                if (_ctx.height != null) {
                    createElementVNode("view", utsMapOf("key" to 3, "class" to "uni-common-mt flex-row"), utsArrayOf(
                        "height: ",
                        createElementVNode("text", utsMapOf("id" to "page-height"), toDisplayString(_ctx.height!!), 1)
                    ))
                } else {
                    createCommentVNode("v-if", true)
                }
                ,
                if (_ctx.statusBarHeight != null) {
                    createElementVNode("view", utsMapOf("key" to 4, "class" to "uni-common-mt flex-row"), utsArrayOf(
                        "statusBarHeight: ",
                        createElementVNode("text", utsMapOf("id" to "page-statusBarHeight"), toDisplayString(_ctx.statusBarHeight!!), 1)
                    ))
                } else {
                    createCommentVNode("v-if", true)
                }
                ,
                createElementVNode("button", utsMapOf("class" to "uni-common-mt", "id" to "go-next-page", "onClick" to _ctx.goNextPage), " go next page ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "uni-common-mt", "id" to "open-dialog1", "onClick" to _ctx.openDialog1), " open dialog 1 ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "uni-common-mt", "id" to "open-dialog1", "onClick" to _ctx.openDialog11), " open dialog 1-1 ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "uni-common-mt", "id" to "open-dialog1-wrong-path", "onClick" to _ctx.openDialog1WrongPath), " open dialog page 1 with wrong path ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "uni-common-mt", "id" to "go-next-page-open-dialog1", "onClick" to _ctx.goNextPageOpenDialog1), " go next page & open dialog1 ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "uni-common-mt", "id" to "open-dialog1", "onClick" to _ctx.openDialog3), " open dialog 3 test page style ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "uni-common-mt", "id" to "open-dialog4", "onClick" to _ctx.openDialogWithTriggerParentHide), " openDialog with triggerParentHide ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "uni-common-mt", "id" to "open-dialog5", "onClick" to _ctx.openDialogCheckMoreAttribute), " openDialog check more attribute ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "uni-common-mt", "id" to "open-dialog5", "onClick" to _ctx.openDialogWithRelativePath), " openDialog with relative path ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "uni-common-mt", "onClick" to _ctx.openDialogWithTextarea), " openDialog with textarea ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "uni-common-mt", "onClick" to _ctx.openDialogWithInput), " openDialog with input ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("text", utsMapOf("class" to "uni-common-mt choose-open-animation-type-title"), "choose open dialogPage animationType"),
                createVNode(_component_radio_group, utsMapOf("class" to "choose-open-animation-type-radio-group", "onChange" to _ctx.handleOpenAnimationType), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.openAnimationTypeList, fun(item, __key, __index, _cached): Any {
                            return createVNode(_component_radio, utsMapOf("class" to "ml-10 uni-common-mt", "key" to item, "value" to item, "checked" to (_ctx.openAnimationType == item)), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                return utsArrayOf(
                                    toDisplayString(item)
                                )
                            }
                            ), "_" to 2), 1032, utsArrayOf(
                                "value",
                                "checked"
                            ))
                        }
                        ), 128)
                    )
                }
                ), "_" to 1), 8, utsArrayOf(
                    "onChange"
                ))
            ))
        ), 4)
    }
    open var pageBody: UniPageBody? by `$data`
    open var safeAreaInsets: UniSafeAreaInsets? by `$data`
    open var width: Number? by `$data`
    open var height: Number? by `$data`
    open var statusBarHeight: Number? by `$data`
    open var jest_click_x: Number by `$data`
    open var jest_click_y: Number by `$data`
    open var openAnimationType: OpenAnimationType by `$data`
    open var openAnimationTypeList: UTSArray<String> by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("pageBody" to null as UniPageBody?, "safeAreaInsets" to null as UniSafeAreaInsets?, "width" to null as Number?, "height" to null as Number?, "statusBarHeight" to null as Number?, "jest_click_x" to -1, "jest_click_y" to -1, "openAnimationType" to "none" as OpenAnimationType, "openAnimationTypeList" to utsArrayOf(
            "auto",
            "none",
            "slide-in-right",
            "slide-in-left",
            "slide-in-top",
            "slide-in-bottom",
            "fade-in",
            "zoom-out",
            "zoom-fade-out"
        ))
    }
    open var goNextPage = ::gen_goNextPage_fn
    open fun gen_goNextPage_fn() {
        uni_navigateTo(NavigateToOptions(url = "/pages/API/dialog-page/next-page"))
    }
    open var openDialog1 = ::gen_openDialog1_fn
    open fun gen_openDialog1_fn() {
        uni_openDialogPage(OpenDialogPageOptions(url = "/pages/API/dialog-page/dialog-1?name=dialog1", animationType = this.openAnimationType, success = fun(res) {
            console.log("openDialogPage1 success", res, " at pages/API/dialog-page/dialog-page.uvue:151")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum + 1)
        }
        , fail = fun(err) {
            console.log("openDialogPage1 fail", err, " at pages/API/dialog-page/dialog-page.uvue:156")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum - 4)
        }
        , complete = fun(res) {
            console.log("openDialogPage1 complete", res, " at pages/API/dialog-page/dialog-page.uvue:160")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum + 1)
        }
        ))
    }
    open var openDialog11 = ::gen_openDialog11_fn
    open fun gen_openDialog11_fn() {
        uni_openDialogPage(OpenDialogPageOptions(url = "/pages/API/dialog-page/dialog-1-1", animationType = this.openAnimationType))
    }
    open var openDialog2 = ::gen_openDialog2_fn
    open fun gen_openDialog2_fn() {
        uni_openDialogPage(OpenDialogPageOptions(url = "/pages/API/dialog-page/dialog-2", animationType = this.openAnimationType, disableEscBack = true, success = fun(res) {
            console.log("openDialog2 success", res, " at pages/API/dialog-page/dialog-page.uvue:178")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum + 1)
        }
        , fail = fun(err) {
            console.log("openDialog2 fail", err, " at pages/API/dialog-page/dialog-page.uvue:183")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum - 4)
        }
        , complete = fun(res) {
            console.log("openDialog2 complete", res, " at pages/API/dialog-page/dialog-page.uvue:188")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum + 1)
        }
        ))
    }
    open var openDialog1WrongPath = ::gen_openDialog1WrongPath_fn
    open fun gen_openDialog1WrongPath_fn() {
        uni_openDialogPage(OpenDialogPageOptions(url = "/pages/API/dialog-page/dialog-11?name=dialog1", success = fun(res) {
            console.log("openDialogPage1 success", res, " at pages/API/dialog-page/dialog-page.uvue:198")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum + 1)
        }
        , fail = fun(err) {
            console.log("openDialogPage1 fail", err, " at pages/API/dialog-page/dialog-page.uvue:203")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum - 4)
        }
        , complete = fun(res) {
            console.log("openDialogPage1 complete", res, " at pages/API/dialog-page/dialog-page.uvue:207")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum + 1)
        }
        ))
    }
    open var goNextPageOpenDialog1 = ::gen_goNextPageOpenDialog1_fn
    open fun gen_goNextPageOpenDialog1_fn() {
        uni_navigateTo(NavigateToOptions(url = "/pages/API/dialog-page/next-page", success = fun(_) {
            setTimeout(fun(){
                uni_openDialogPage(OpenDialogPageOptions(url = "/pages/API/dialog-page/dialog-1?name=dialog1", animationType = this.openAnimationType, success = fun(res) {
                    console.log("openDialogPage1 success", res, " at pages/API/dialog-page/dialog-page.uvue:222")
                    uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum + 1)
                }
                , fail = fun(err) {
                    console.log("openDialogPage1 fail", err, " at pages/API/dialog-page/dialog-page.uvue:227")
                    uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum - 4)
                }
                , complete = fun(res) {
                    console.log("openDialogPage1 complete", res, " at pages/API/dialog-page/dialog-page.uvue:232")
                    uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum + 1)
                }
                ))
            }
            , 2000)
        }
        ))
    }
    open var closeDialog = ::gen_closeDialog_fn
    open fun gen_closeDialog_fn() {
        uni_closeDialogPage(CloseDialogPageOptions(success = fun(res) {
            console.log("closeDialog success", res, " at pages/API/dialog-page/dialog-page.uvue:244")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum + 1)
        }
        , fail = fun(err) {
            console.log("closeDialog fail", err, " at pages/API/dialog-page/dialog-page.uvue:249")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum - 4)
        }
        , complete = fun(res) {
            console.log("closeDialog complete", res, " at pages/API/dialog-page/dialog-page.uvue:254")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum + 1)
        }
        ))
    }
    open var closeSpecifiedDialog = ::gen_closeSpecifiedDialog_fn
    open fun gen_closeSpecifiedDialog_fn(index: Number) {
        val dialogPages = this.`$page`.getDialogPages()
        uni_closeDialogPage(CloseDialogPageOptions(dialogPage = dialogPages[index], success = fun(res) {
            console.log("closeSomeOneDialog success", res, " at pages/API/dialog-page/dialog-page.uvue:265")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum + 1)
        }
        , fail = fun(err) {
            console.log("closeSomeOneDialog fail", err, " at pages/API/dialog-page/dialog-page.uvue:270")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum - 4)
        }
        , complete = fun(res) {
            console.log("closeSomeOneDialog complete", res, " at pages/API/dialog-page/dialog-page.uvue:275")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum + 1)
        }
        ))
    }
    open var openDialog4 = ::gen_openDialog4_fn
    open fun gen_openDialog4_fn() {
        uni_openDialogPage(OpenDialogPageOptions(url = "/pages/API/dialog-page/dialog-4"))
    }
    open var openDialogWithTriggerParentHide = ::gen_openDialogWithTriggerParentHide_fn
    open fun gen_openDialogWithTriggerParentHide_fn() {
        uni_openDialogPage(OpenDialogPageOptions(url = "/pages/API/dialog-page/dialog-4?tag=" + Date.now(), triggerParentHide = true, success = fun(res) {
            console.log("openDialogWithTriggerParentHide success", res, " at pages/API/dialog-page/dialog-page.uvue:292")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum + 1)
        }
        , fail = fun(err) {
            console.log("openDialogWithTriggerParentHide fail", err, " at pages/API/dialog-page/dialog-page.uvue:297")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum - 4)
        }
        , complete = fun(res) {
            console.log("openDialogWithTriggerParentHide complete", res, " at pages/API/dialog-page/dialog-page.uvue:302")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum + 1)
        }
        ))
    }
    open var openDialogCheckMoreAttribute = ::gen_openDialogCheckMoreAttribute_fn
    open fun gen_openDialogCheckMoreAttribute_fn() {
        uni_openDialogPage(OpenDialogPageOptions(url = "/pages/API/dialog-page/dialog-5"))
    }
    open var openDialogWithTextarea = ::gen_openDialogWithTextarea_fn
    open fun gen_openDialogWithTextarea_fn() {
        uni_openDialogPage(OpenDialogPageOptions(url = "/pages/API/dialog-page/dialog-textarea"))
    }
    open var openDialogWithInput = ::gen_openDialogWithInput_fn
    open fun gen_openDialogWithInput_fn() {
        uni_openDialogPage(OpenDialogPageOptions(url = "/pages/API/dialog-page/dialog-input"))
    }
    open var setLifeCycleNum = ::gen_setLifeCycleNum_fn
    open fun gen_setLifeCycleNum_fn(value: Number) {
        uni.UNI740BA18.setLifeCycleNum(value)
    }
    open var getLifeCycleNum = ::gen_getLifeCycleNum_fn
    open fun gen_getLifeCycleNum_fn(): Number {
        return state.lifeCycleNum
    }
    open var closeDialogSimple = ::gen_closeDialogSimple_fn
    open fun gen_closeDialogSimple_fn() {
        uni_closeDialogPage(null)
    }
    open var jest_getTapPoint = ::gen_jest_getTapPoint_fn
    open fun gen_jest_getTapPoint_fn() {
        val systemInfo = uni_getSystemInfoSync()
        var ratio: Number = 1
        if (systemInfo.platform == "android") {
            ratio = systemInfo.devicePixelRatio
        }
        this.jest_click_x = systemInfo.screenWidth / 2 * ratio
        this.jest_click_y = systemInfo.statusBarHeight * ratio + 10
    }
    open var openDialog2Simple = ::gen_openDialog2Simple_fn
    open fun gen_openDialog2Simple_fn() {
        uni_openDialogPage(OpenDialogPageOptions(url = "/pages/API/dialog-page/dialog-2"))
    }
    open var setPageStyleForTest = ::gen_setPageStyleForTest_fn
    open fun gen_setPageStyleForTest_fn(style: UTSJSONObject) {
        val pages = this.`$page`.getDialogPages()
        if (pages.length > 0) {
            pages[pages.length - 1].setPageStyle(style)
        }
    }
    open var setPageStyleForTest2 = ::gen_setPageStyleForTest2_fn
    open fun gen_setPageStyleForTest2_fn(style: UTSJSONObject) {
        this.`$page`.setPageStyle(style)
    }
    open var openDialog3 = ::gen_openDialog3_fn
    open fun gen_openDialog3_fn() {
        uni_openDialogPage(OpenDialogPageOptions(url = "/pages/API/dialog-page/dialog-3", animationType = this.openAnimationType))
    }
    open var handleOpenAnimationType = ::gen_handleOpenAnimationType_fn
    open fun gen_handleOpenAnimationType_fn(e: RadioGroupChangeEvent) {
        this.openAnimationType = e.detail.value as OpenAnimationType
    }
    open var openDialogWithRelativePath = ::gen_openDialogWithRelativePath_fn
    open fun gen_openDialogWithRelativePath_fn() {
        uni_openDialogPage(OpenDialogPageOptions(url = "./dialog-1?name=dialog1", animationType = this.openAnimationType, success = fun(res) {
            console.log("openDialogPage1 success", res, " at pages/API/dialog-page/dialog-page.uvue:364")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum + 1)
        }
        , fail = fun(err) {
            console.log("openDialogPage1 fail", err, " at pages/API/dialog-page/dialog-page.uvue:369")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum - 4)
        }
        , complete = fun(res) {
            console.log("openDialogPage1 complete", res, " at pages/API/dialog-page/dialog-page.uvue:373")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum + 1)
        }
        ))
    }
    open var getDialogPage = ::gen_getDialogPage_fn
    open fun gen_getDialogPage_fn(): UniPage? {
        val dialogPages = this.`$page`.getDialogPages()
        return if (dialogPages.length > 0) {
            dialogPages[0]
        } else {
            null
        }
    }
    open var getDialogPageRoute = ::gen_getDialogPageRoute_fn
    open fun gen_getDialogPageRoute_fn(): String {
        val dialogPage = this.getDialogPage()
        if (dialogPage != null) {
            return dialogPage.route
        }
        return ""
    }
    open var dialogPageCheckGetDialogPages = ::gen_dialogPageCheckGetDialogPages_fn
    open fun gen_dialogPageCheckGetDialogPages_fn(): Boolean {
        val dialogPage = this.getDialogPage()!!
        val dialogPages = dialogPage.getDialogPages()
        val res = dialogPages.length == 0
        return res
    }
    open var dialogPageGetPageStyle = ::gen_dialogPageGetPageStyle_fn
    open fun gen_dialogPageGetPageStyle_fn(): UTSJSONObject {
        val dialogPage = this.getDialogPage()!!
        return dialogPage.getPageStyle()
    }
    open var dialogPageSetPageStyle = ::gen_dialogPageSetPageStyle_fn
    open fun gen_dialogPageSetPageStyle_fn() {
        val dialogPage = this.getDialogPage()!!
        dialogPage.setPageStyle(object : UTSJSONObject() {
            var backgroundColorContent = "red"
        })
    }
    open var dialogPageCheckGetElementById = ::gen_dialogPageCheckGetElementById_fn
    open fun gen_dialogPageCheckGetElementById_fn(): Boolean {
        val dialogPage = this.getDialogPage()!!
        val element = dialogPage.getElementById("dialog1-go-next-page")
        var res = element != null
        return res
    }
    open var dialogCheckGetAndroidView = ::gen_dialogCheckGetAndroidView_fn
    open fun gen_dialogCheckGetAndroidView_fn(): Boolean {
        val dialogPage = this.getDialogPage()!!
        val androidView = dialogPage.getAndroidView<android.view.View>()
        val res = androidView != null
        return res
    }
    open var dialogCheckGetIOSView = ::gen_dialogCheckGetIOSView_fn
    open fun gen_dialogCheckGetIOSView_fn(): Boolean {
        val dialogPage = this.getDialogPage()!!
        val IOSView = dialogPage.getIOSView()
        val res = IOSView != null
        return res
    }
    open var dialogCheckGetHTMLElement = ::gen_dialogCheckGetHTMLElement_fn
    open fun gen_dialogCheckGetHTMLElement_fn(): Boolean {
        val dialogPage = this.getDialogPage()!!
        val HTMLView = dialogPage.getHTMLElement()
        val res = HTMLView != null
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
                return utsMapOf("uni-padding-wrap" to padStyleMapOf(utsMapOf("paddingBottom" to "var(--uni-safe-area-inset-bottom)")), "ml-10" to padStyleMapOf(utsMapOf("marginLeft" to 10)), "choose-open-animation-type-title" to padStyleMapOf(utsMapOf("fontWeight" to "bold")), "choose-open-animation-type-radio-group" to padStyleMapOf(utsMapOf("flexDirection" to "row", "flexWrap" to "wrap", "marginBottom" to 20)), "flex-row" to padStyleMapOf(utsMapOf("flexDirection" to "row", "flexWrap" to "wrap")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
