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
open class GenPagesComponentShareElementShareElement : BasePage {
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
        val _component_boolean_data = resolveEasyComponent("boolean-data", GenComponentsBooleanDataBooleanDataClass)
        val _component_radio = resolveComponent("radio")
        val _component_radio_group = resolveComponent("radio-group")
        return createElementVNode(Fragment, null, utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to "share-element")),
            createElementVNode("view", utsMapOf("class" to "main"), utsArrayOf(
                createElementVNode("share-element", utsMapOf("class" to "share-element", "share-key" to "left", "shuttle-on-pop" to _ctx.shuttleOnPopType, "transition-on-gesture" to _ctx.transitionOnGesture, "shuttle-on-push" to _ctx.shuttleOnPushType, "easing-function" to _ctx.easingFunctionType, "onClick" to fun(){
                    _ctx.openPage()
                }
                ), utsArrayOf(
                    createElementVNode("image", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "100px", "height" to "150px")), "src" to "https://web-ext-storage.dcloud.net.cn/hello-uni-app-x/drop-card-1.jpg"), null, 4)
                ), 8, utsArrayOf(
                    "shuttle-on-pop",
                    "transition-on-gesture",
                    "shuttle-on-push",
                    "easing-function",
                    "onClick"
                ))
            )),
            createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.openPage, "class" to "button"), " 打开share-element页面 ", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "content"), utsArrayOf(
                    createVNode(_component_boolean_data, utsMapOf("defaultValue" to _ctx.transitionOnGesture, "title" to "transition-on-gesture= true(仅iOS生效)", "onChange" to _ctx.changeTransitionOnGesture), null, 8, utsArrayOf(
                        "defaultValue",
                        "onChange"
                    )),
                    createElementVNode("text", utsMapOf("class" to "uni-common-mt choose-property-title"), "easing-function:"),
                    createVNode(_component_radio_group, utsMapOf("class" to "choose-property-type-radio-group", "onChange" to _ctx.handleEasingFunction), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.easingFunctionTypeList, fun(item, __key, __index, _cached): Any {
                                return createVNode(_component_radio, utsMapOf("class" to "ml-10 uni-common-mt", "key" to item, "value" to item, "checked" to (_ctx.easingFunctionType == item)), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
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
                    )),
                    createElementVNode("text", utsMapOf("class" to "uni-common-mt choose-property-title"), "shuttle-on-push(仅iOS生效):"),
                    createVNode(_component_radio_group, utsMapOf("class" to "choose-property-type-radio-group", "onChange" to _ctx.handleShuttleOnPushType), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.shuttleOnTypeList, fun(item, __key, __index, _cached): Any {
                                return createVNode(_component_radio, utsMapOf("class" to "ml-10 uni-common-mt", "key" to item, "value" to item, "checked" to (_ctx.shuttleOnPushType == item)), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
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
                    )),
                    createElementVNode("text", utsMapOf("class" to "uni-common-mt choose-property-title"), "shuttle-on-pop(仅iOS生效):"),
                    createVNode(_component_radio_group, utsMapOf("class" to "choose-property-type-radio-group", "onChange" to _ctx.handleShuttleOnPopType), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.shuttleOnTypeList, fun(item, __key, __index, _cached): Any {
                                return createVNode(_component_radio, utsMapOf("class" to "ml-10 uni-common-mt", "key" to item, "value" to item, "checked" to (_ctx.shuttleOnPopType == item)), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
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
                    )),
                    createElementVNode("text", utsMapOf("class" to "uni-common-mt choose-property-title"), "animationType(页面动画降级):"),
                    createVNode(_component_radio_group, utsMapOf("class" to "choose-property-type-radio-group", "onChange" to _ctx.handleOpenAnimationType), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
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
            ), 4),
            createElementVNode("button", utsMapOf("onClick" to _ctx.gotoShareElementWithSwiper, "class" to "button"), " 打开share-element-with-swiper页面 ", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("height" to "80px"))), null, 4),
            createElementVNode("share-element", utsMapOf("class" to "bottomWrap", "share-key" to "bottom", "onClick" to fun(){
                _ctx.openPage()
            }
            , "transitionOnGesture" to "true"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "bottom"), utsArrayOf(
                    createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("color" to "white"))), "share-element(底部固定)", 4)
                ))
            ), 8, utsArrayOf(
                "onClick"
            ))
        ), 64)
    }
    open var transitionOnGesture: Boolean by `$data`
    open var shuttleOnPopType: ShuttleOnType by `$data`
    open var shuttleOnPushType: ShuttleOnType by `$data`
    open var shuttleOnTypeList: UTSArray<String> by `$data`
    open var openAnimationType: ShareElementOpenAnimationType by `$data`
    open var openAnimationTypeList: UTSArray<String> by `$data`
    open var easingFunctionType: ShareElementEasingFunctionType by `$data`
    open var easingFunctionTypeList: UTSArray<String> by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("transitionOnGesture" to true, "shuttleOnPopType" to "to" as ShuttleOnType, "shuttleOnPushType" to "to" as ShuttleOnType, "shuttleOnTypeList" to utsArrayOf(
            "from",
            "to"
        ), "openAnimationType" to "slide-in-right" as ShareElementOpenAnimationType, "openAnimationTypeList" to utsArrayOf(
            "auto",
            "none",
            "slide-in-right",
            "slide-in-left",
            "slide-in-top",
            "slide-in-bottom",
            "fade-in",
            "pop-in",
            "zoom-out",
            "zoom-fade-out"
        ), "easingFunctionType" to "ease" as ShareElementEasingFunctionType, "easingFunctionTypeList" to utsArrayOf(
            "ease",
            "ease-in",
            "ease-out",
            "ease-in-out",
            "linear"
        ))
    }
    open var openPage = ::gen_openPage_fn
    open fun gen_openPage_fn() {
        uni_navigateTo(NavigateToOptions(animationType = this.openAnimationType, url = "/pages/component/share-element/share-element-to?shuttleOnPush=" + this.shuttleOnPushType + "&transitionOnGesture=" + this.transitionOnGesture))
    }
    open var gotoShareElementWithSwiper = ::gen_gotoShareElementWithSwiper_fn
    open fun gen_gotoShareElementWithSwiper_fn() {
        uni_navigateTo(NavigateToOptions(url = "/pages/component/share-element/share-element-with-swiper"))
    }
    open var changeTransitionOnGesture = ::gen_changeTransitionOnGesture_fn
    open fun gen_changeTransitionOnGesture_fn(checked: Boolean) {
        console.log("changeTransitionOnGesture:" + checked, " at pages/component/share-element/share-element.uvue:130")
        this.transitionOnGesture = checked
    }
    open var handleOpenAnimationType = ::gen_handleOpenAnimationType_fn
    open fun gen_handleOpenAnimationType_fn(e: RadioGroupChangeEvent) {
        this.openAnimationType = e.detail.value as ShareElementOpenAnimationType
    }
    open var handleEasingFunction = ::gen_handleEasingFunction_fn
    open fun gen_handleEasingFunction_fn(e: RadioGroupChangeEvent) {
        this.easingFunctionType = e.detail.value as ShareElementEasingFunctionType
    }
    open var handleShuttleOnPopType = ::gen_handleShuttleOnPopType_fn
    open fun gen_handleShuttleOnPopType_fn(e: RadioGroupChangeEvent) {
        this.shuttleOnPopType = e.detail.value as ShuttleOnType
    }
    open var handleShuttleOnPushType = ::gen_handleShuttleOnPushType_fn
    open fun gen_handleShuttleOnPushType_fn(e: RadioGroupChangeEvent) {
        this.shuttleOnPushType = e.detail.value as ShuttleOnType
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
                return utsMapOf("ml-10" to padStyleMapOf(utsMapOf("marginLeft" to 10)), "choose-property-title" to padStyleMapOf(utsMapOf("fontWeight" to "bold")), "choose-property-type-radio-group" to padStyleMapOf(utsMapOf("flexDirection" to "row", "flexWrap" to "wrap")), "bottomWrap" to padStyleMapOf(utsMapOf("width" to "100%", "bottom" to 0, "height" to 80, "position" to "fixed")), "bottom" to padStyleMapOf(utsMapOf("width" to "100%", "height" to "100%", "alignItems" to "center", "justifyContent" to "center", "backgroundColor" to "#007aff")), "main" to padStyleMapOf(utsMapOf("paddingTop" to 5, "paddingRight" to 0, "paddingBottom" to 5, "paddingLeft" to 0, "alignItems" to "center", "justifyContent" to "center")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
