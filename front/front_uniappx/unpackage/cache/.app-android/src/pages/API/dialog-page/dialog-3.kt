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
open class GenPagesAPIDialogPageDialog3 : BasePage {
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
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_switch = resolveComponent("switch")
        val _component_slider = resolveComponent("slider")
        val _component_radio = resolveComponent("radio")
        val _component_radio_group = resolveComponent("radio-group")
        return createElementVNode("view", utsMapOf("id" to "dialog3", "class" to "dialog-container"), utsArrayOf(
            createElementVNode("scroll-view", utsMapOf("class" to "dialog-content"), utsArrayOf(
                createElementVNode("text", null, "title: " + toDisplayString(_ctx.title), 1),
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.PageStyleArray, fun(item, index, __index, _cached): Any {
                    return createElementVNode(Fragment, null, utsArrayOf(
                        if (_ctx.currentPageStyle[item.key] != null) {
                            createElementVNode("view", utsMapOf("class" to "page-style-item", "key" to index), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "item-text"), utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "item-text-key"), toDisplayString(item.key) + ":", 1),
                                    createElementVNode("text", utsMapOf("class" to "item-text-value"), toDisplayString(_ctx.currentPageStyle[item.key]), 1)
                                )),
                                if (item.type == "boolean") {
                                    createElementVNode("view", utsMapOf("key" to 0, "class" to "mt-10"), utsArrayOf(
                                        createVNode(_component_switch, utsMapOf("checked" to _ctx.currentPageStyle.getBoolean(item.key), "onChange" to fun(`$event`: Any){
                                            _ctx.switchChange(item.key, `$event` as UniSwitchChangeEvent)
                                        }), null, 8, utsArrayOf(
                                            "checked",
                                            "onChange"
                                        ))
                                    ))
                                } else {
                                    if (item.type == "number") {
                                        createElementVNode("view", utsMapOf("key" to 1, "class" to "mt-10"), utsArrayOf(
                                            createVNode(_component_slider, utsMapOf("value" to _ctx.currentPageStyle.getNumber(item.key), "show-value" to true, "onChange" to fun(`$event`: Any){
                                                _ctx.sliderChange(item.key, `$event` as UniSliderChangeEvent)
                                            }), null, 8, utsArrayOf(
                                                "value",
                                                "onChange"
                                            ))
                                        ))
                                    } else {
                                        if (item.type == "string") {
                                            createElementVNode("view", utsMapOf("key" to 2, "class" to "mt-10"), utsArrayOf(
                                                createVNode(_component_radio_group, utsMapOf("class" to "radio-set-value", "onChange" to fun(`$event`: Any){
                                                    _ctx.radioChange(item.key, `$event` as RadioGroupChangeEvent)
                                                }), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                                    return utsArrayOf(
                                                        createElementVNode(Fragment, null, RenderHelpers.renderList(item.value, fun(item2, index2, __index, _cached): Any {
                                                            return createVNode(_component_radio, utsMapOf("class" to "ml-10", "key" to index2, "value" to item2, "checked" to (_ctx.currentPageStyle[item.key] == item2)), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                                                return utsArrayOf(
                                                                    toDisplayString(item2)
                                                                )
                                                            }), "_" to 2), 1032, utsArrayOf(
                                                                "value",
                                                                "checked"
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
                createElementVNode("text", utsMapOf("class" to "mt-10 choose-close-animation-type-title"), "choose close dialogPage animationType"),
                createVNode(_component_radio_group, utsMapOf("class" to "choose-close-animation-type-radio-group", "onChange" to _ctx.handleChooseAnimationType), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.closeAnimationTypeList, fun(item, __key, __index, _cached): Any {
                            return createVNode(_component_radio, utsMapOf("class" to "ml-10 mt-10", "key" to item, "value" to item, "checked" to (_ctx.closeAnimationType == item)), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
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
                createElementVNode("button", utsMapOf("class" to "mt-10", "onClick" to _ctx.closeThisDialog), " closeThisDialog ", 8, utsArrayOf(
                    "onClick"
                ))
            ))
        ))
    }
    open var title: String by `$data`
    open var PageStyleArray: UTSArray<PageStyleItem1> by `$data`
    open var currentPageStyle: UTSJSONObject by `$data`
    open var closeAnimationType: CloseAnimationType by `$data`
    open var closeAnimationTypeList: UTSArray<String> by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "dialog 3", "PageStyleArray" to PageStyleArray1 as UTSArray<PageStyleItem1>, "currentPageStyle" to UTSJSONObject(), "closeAnimationType" to "auto" as CloseAnimationType, "closeAnimationTypeList" to utsArrayOf(
            "auto",
            "none",
            "slide-out-right",
            "slide-out-left",
            "slide-out-top",
            "slide-out-bottom",
            "fade-out",
            "zoom-in",
            "zoom-fade-in"
        ))
    }
    open var getPageStyle = ::gen_getPageStyle_fn
    open fun gen_getPageStyle_fn() {
        this.currentPageStyle = this.`$page`.getPageStyle()
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
        val style: UTSJSONObject = object : UTSJSONObject(UTSSourceMapPosition("style", "pages/API/dialog-page/dialog-3.uvue", 94, 15)) {
        }
        style[key] = value
        this.setPageStyle(style)
        this.getPageStyle()
    }
    open var setPageStyle = ::gen_setPageStyle_fn
    open fun gen_setPageStyle_fn(style: UTSJSONObject) {
        this.`$page`.setPageStyle(style)
    }
    open var handleChooseAnimationType = ::gen_handleChooseAnimationType_fn
    open fun gen_handleChooseAnimationType_fn(e: RadioGroupChangeEvent) {
        this.closeAnimationType = e.detail.value as CloseAnimationType
    }
    open var closeThisDialog = ::gen_closeThisDialog_fn
    open fun gen_closeThisDialog_fn() {
        uni_closeDialogPage(CloseDialogPageOptions(dialogPage = this.`$page`, animationType = this.closeAnimationType, success = fun(res) {
            console.log("closeDialog success", res, " at pages/API/dialog-page/dialog-3.uvue:110")
        }
        , fail = fun(err) {
            console.log("closeDialog fail", err, " at pages/API/dialog-page/dialog-3.uvue:113")
        }
        , complete = fun(res) {
            console.log("closeDialog complete", res, " at pages/API/dialog-page/dialog-3.uvue:116")
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
                return utsMapOf("dialog-container" to padStyleMapOf(utsMapOf("width" to "100%", "height" to "100%", "backgroundColor" to "rgba(0,0,0,0.3)", "display" to "flex", "justifyContent" to "center", "alignItems" to "center")), "dialog-content" to padStyleMapOf(utsMapOf("width" to "90%", "height" to 500, "paddingTop" to 10, "paddingRight" to 6, "paddingBottom" to 10, "paddingLeft" to 6, "backgroundColor" to "#ffffff", "borderTopLeftRadius" to 6, "borderTopRightRadius" to 6, "borderBottomRightRadius" to 6, "borderBottomLeftRadius" to 6)), "mt-10" to padStyleMapOf(utsMapOf("marginTop" to 10)), "ml-10" to padStyleMapOf(utsMapOf("marginLeft" to 10)), "page-style-item" to padStyleMapOf(utsMapOf("paddingTop" to 6, "paddingRight" to 0, "paddingBottom" to 6, "paddingLeft" to 0, "marginTop" to 10, "backgroundColor" to "#ffffff", "borderTopLeftRadius" to 5, "borderTopRightRadius" to 5, "borderBottomRightRadius" to 5, "borderBottomLeftRadius" to 5)), "item-text" to padStyleMapOf(utsMapOf("flexDirection" to "row")), "item-text-key" to padStyleMapOf(utsMapOf("fontWeight" to "bold")), "item-text-value" to padStyleMapOf(utsMapOf("marginLeft" to 5)), "radio-set-value" to padStyleMapOf(utsMapOf("flexDirection" to "row")), "choose-close-animation-type-title" to padStyleMapOf(utsMapOf("fontWeight" to "bold")), "choose-close-animation-type-radio-group" to padStyleMapOf(utsMapOf("flexDirection" to "row", "flexWrap" to "wrap")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
