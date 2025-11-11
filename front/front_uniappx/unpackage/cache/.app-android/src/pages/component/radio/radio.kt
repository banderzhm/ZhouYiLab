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
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesComponentRadioRadio : BasePage {
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
        val _component_radio = resolveComponent("radio", true)
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        val _component_boolean_data = resolveEasyComponent("boolean-data", GenComponentsBooleanDataBooleanDataClass)
        val _component_input_data = resolveEasyComponent("input-data", GenComponentsInputDataInputDataClass)
        val _component_radio_group = resolveComponent("radio-group")
        return createElementVNode(Fragment, null, utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "main"), utsArrayOf(
                createVNode(_component_radio, utsMapOf("disabled" to _ctx.disabled_boolean, "checked" to _ctx.checked_boolean, "color" to _ctx.color_input, "backgroundColor" to _ctx.backgroundColor_input, "borderColor" to _ctx.borderColor_input, "activeBackgroundColor" to _ctx.activeBackgroundColor_input, "activeBorderColor" to _ctx.activeBorderColor_input, "iconColor" to _ctx.iconColor_input, "onClick" to utsArrayOf(
                    _ctx.radio_click,
                    _ctx.radio_tap
                ), "onTouchstart" to _ctx.radio_touchstart, "onTouchmove" to _ctx.radio_touchmove, "onTouchcancel" to _ctx.radio_touchcancel, "onTouchend" to _ctx.radio_touchend, "onLongpress" to _ctx.radio_longpress), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("text", null, "uni-app-x")
                    )
                }
                ), "_" to 1), 8, utsArrayOf(
                    "disabled",
                    "checked",
                    "color",
                    "backgroundColor",
                    "borderColor",
                    "activeBackgroundColor",
                    "activeBorderColor",
                    "iconColor",
                    "onClick",
                    "onTouchstart",
                    "onTouchmove",
                    "onTouchcancel",
                    "onTouchend",
                    "onLongpress"
                ))
            )),
            createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "content"), utsArrayOf(
                    createVNode(_component_page_head, utsMapOf("title" to "组件属性")),
                    createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "<radio/> 当前是否选中", "onChange" to _ctx.change_checked_boolean), null, 8, utsArrayOf(
                        "onChange"
                    )),
                    createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "是否禁用", "onChange" to _ctx.change_disabled_boolean), null, 8, utsArrayOf(
                        "onChange"
                    )),
                    createVNode(_component_input_data, utsMapOf("defaultValue" to "#007AFF", "title" to "radio的颜色", "type" to "text", "onConfirm" to _ctx.confirm_color_input), null, 8, utsArrayOf(
                        "onConfirm"
                    )),
                    createVNode(_component_input_data, utsMapOf("defaultValue" to "#ffffff", "title" to "radio默认的背景颜色", "type" to "text", "onConfirm" to _ctx.confirm_backgroundColor_input), null, 8, utsArrayOf(
                        "onConfirm"
                    )),
                    createVNode(_component_input_data, utsMapOf("defaultValue" to "#d1d1d1", "title" to "radio默认的边框颜色", "type" to "text", "onConfirm" to _ctx.confirm_borderColor_input), null, 8, utsArrayOf(
                        "onConfirm"
                    )),
                    createVNode(_component_input_data, utsMapOf("defaultValue" to "#007AFF", "title" to "radio选中时的背景颜色，优先级大于color属性", "type" to "text", "onConfirm" to _ctx.confirm_activeBackgroundColor_input), null, 8, utsArrayOf(
                        "onConfirm"
                    )),
                    createVNode(_component_input_data, utsMapOf("defaultValue" to "", "title" to "radio选中时的边框颜色", "type" to "text", "onConfirm" to _ctx.confirm_activeBorderColor_input), null, 8, utsArrayOf(
                        "onConfirm"
                    )),
                    createVNode(_component_input_data, utsMapOf("defaultValue" to "#ffffff", "title" to "radio的图标颜色", "type" to "text", "onConfirm" to _ctx.confirm_iconColor_input), null, 8, utsArrayOf(
                        "onConfirm"
                    ))
                )),
                createElementVNode("view", null, utsArrayOf(
                    createVNode(_component_page_head, utsMapOf("title" to "默认及使用")),
                    createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-title uni-common-mt"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "uni-title-text"), " 默认样式 ")
                        )),
                        createVNode(_component_radio_group, utsMapOf("class" to "uni-flex uni-row radio-group", "onChange" to _ctx.testChange, "style" to normalizeStyle(utsMapOf("flex-wrap" to "wrap"))), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                            return utsArrayOf(
                                createVNode(_component_radio, utsMapOf("id" to "trigger-change", "value" to "r", "checked" to _ctx.checked, "color" to _ctx.color, "style" to normalizeStyle(utsMapOf("margin-right" to "15px")), "class" to "radio r"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                    return utsArrayOf(
                                        "选中 "
                                    )
                                }
                                ), "_" to 1), 8, utsArrayOf(
                                    "checked",
                                    "color",
                                    "style"
                                )),
                                createVNode(_component_radio, utsMapOf("value" to "r1", "style" to normalizeStyle(utsMapOf("margin-right" to "15px")), "class" to "radio r1"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                    return utsArrayOf(
                                        toDisplayString(_ctx.text)
                                    )
                                }
                                ), "_" to 1), 8, utsArrayOf(
                                    "style"
                                )),
                                createVNode(_component_radio, utsMapOf("value" to "r2", "disabled" to _ctx.disabled, "class" to "radio r2"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                    return utsArrayOf(
                                        "禁用"
                                    )
                                }
                                ), "_" to 1), 8, utsArrayOf(
                                    "disabled"
                                )),
                                createVNode(_component_radio, utsMapOf("value" to "r3", "class" to "radio r3", "style" to normalizeStyle(utsMapOf("margin-top" to "10px"))), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                    return utsArrayOf(
                                        toDisplayString(_ctx.wrapText)
                                    )
                                }
                                ), "_" to 1), 8, utsArrayOf(
                                    "style"
                                ))
                            )
                        }
                        ), "_" to 1), 8, utsArrayOf(
                            "onChange",
                            "style"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-title uni-common-mt"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "uni-title-text"), " 不同颜色和尺寸的radio ")
                        )),
                        createVNode(_component_radio_group, utsMapOf("class" to "uni-flex uni-row radio-group"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                            return utsArrayOf(
                                createVNode(_component_radio, utsMapOf("value" to "r1", "checked" to true, "color" to "#FFCC33", "style" to normalizeStyle(utsMapOf("transform" to "scale(0.7)", "margin-right" to "15px")), "class" to "radio"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                    return utsArrayOf(
                                        "选中 "
                                    )
                                }
                                ), "_" to 1), 8, utsArrayOf(
                                    "style"
                                )),
                                createVNode(_component_radio, utsMapOf("value" to "r2", "color" to "#FFCC33", "style" to normalizeStyle(utsMapOf("transform" to "scale(0.7)")), "class" to "radio"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                    return utsArrayOf(
                                        "未选中"
                                    )
                                }
                                ), "_" to 1), 8, utsArrayOf(
                                    "style"
                                ))
                            )
                        }
                        ), "_" to 1))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-title uni-common-mt"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "uni-title-text"), " 两端对齐样式测试 ")
                        )),
                        createVNode(_component_radio_group, utsMapOf("class" to "uni-flex uni-row radio-group"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                            return utsArrayOf(
                                createVNode(_component_radio, utsMapOf("class" to "justify-test"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                    return utsArrayOf(
                                        "justify-content样式测试"
                                    )
                                }
                                ), "_" to 1))
                            )
                        }
                        ), "_" to 1))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-title uni-common-mt"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "uni-title-text"), " 推荐展示样式 ")
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list uni-common-pl"), utsArrayOf(
                        createVNode(_component_radio_group, utsMapOf("onChange" to _ctx.radioChange, "class" to "radio-group"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                            return utsArrayOf(
                                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.items, fun(item, index, __index, _cached): Any {
                                    return createVNode(_component_radio, utsMapOf("class" to normalizeClass(utsArrayOf(
                                        "uni-list-cell uni-list-cell-pd radio recommand",
                                        if (index < _ctx.items.length - 1) {
                                            "uni-list-cell-line"
                                        } else {
                                            ""
                                        }
                                    )), "key" to item.value, "value" to item.value, "checked" to (index === _ctx.current)), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                        return utsArrayOf(
                                            toDisplayString(item.name)
                                        )
                                    }
                                    ), "_" to 2), 1032, utsArrayOf(
                                        "class",
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
                ))
            ), 4)
        ), 64)
    }
    open var items: UTSArray<ItemType1> by `$data`
    open var current: Number by `$data`
    open var eventTest: Boolean by `$data`
    open var value: String by `$data`
    open var text: String by `$data`
    open var wrapText: String by `$data`
    open var disabled: Boolean by `$data`
    open var checked: Boolean by `$data`
    open var color: String by `$data`
    open var checked_boolean: Boolean by `$data`
    open var disabled_boolean: Boolean by `$data`
    open var color_input: String by `$data`
    open var backgroundColor_input: String by `$data`
    open var borderColor_input: String by `$data`
    open var activeBackgroundColor_input: String by `$data`
    open var activeBorderColor_input: String by `$data`
    open var iconColor_input: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("items" to utsArrayOf<ItemType1>(ItemType1(value = "CHN", name = "中国"), ItemType1(value = "USA", name = "美国"), ItemType1(value = "BRA", name = "巴西"), ItemType1(value = "JPN", name = "日本"), ItemType1(value = "ENG", name = "英国"), ItemType1(value = "FRA", name = "法国")), "current" to 0, "eventTest" to false, "value" to "", "text" to "未选中", "wrapText" to "uni-app x，终极跨平台方案\nuts，大一统语言", "disabled" to true, "checked" to true, "color" to "#007aff", "checked_boolean" to false, "disabled_boolean" to false, "color_input" to "#007AFF", "backgroundColor_input" to "#ffffff", "borderColor_input" to "#d1d1d1", "activeBackgroundColor_input" to "#007AFF", "activeBorderColor_input" to "", "iconColor_input" to "#ffffff")
    }
    open var radioChange = ::gen_radioChange_fn
    open fun gen_radioChange_fn(e: UniRadioGroupChangeEvent) {
        console.log("test: radio event detail", e.target?.tagName, e.type, " at pages/component/radio/radio.uvue:61")
        if ((e.target?.tagName ?: "") == "RADIO-GROUP" && e.type == "change") {
            this.eventTest = true
        }
        val selected = this.items.find(fun(item): Boolean {
            return item.value == e.detail.value
        }
        )
        uni_showToast(ShowToastOptions(icon = "none", title = "当前选中:" + selected?.name))
    }
    open var testChange = ::gen_testChange_fn
    open fun gen_testChange_fn(e: UniRadioGroupChangeEvent) {
        this.value = e.detail.value
    }
    open var radio_click = ::gen_radio_click_fn
    open fun gen_radio_click_fn() {
        console.log("组件被点击时触发", " at pages/component/radio/radio.uvue:78")
    }
    open var radio_touchstart = ::gen_radio_touchstart_fn
    open fun gen_radio_touchstart_fn() {
        console.log("手指触摸动作开始", " at pages/component/radio/radio.uvue:79")
    }
    open var radio_touchmove = ::gen_radio_touchmove_fn
    open fun gen_radio_touchmove_fn() {
        console.log("手指触摸后移动", " at pages/component/radio/radio.uvue:80")
    }
    open var radio_touchcancel = ::gen_radio_touchcancel_fn
    open fun gen_radio_touchcancel_fn() {
        console.log("手指触摸动作被打断，如来电提醒，弹窗", " at pages/component/radio/radio.uvue:81")
    }
    open var radio_touchend = ::gen_radio_touchend_fn
    open fun gen_radio_touchend_fn() {
        console.log("手指触摸动作结束", " at pages/component/radio/radio.uvue:82")
    }
    open var radio_tap = ::gen_radio_tap_fn
    open fun gen_radio_tap_fn() {
        console.log("手指触摸后马上离开", " at pages/component/radio/radio.uvue:83")
    }
    open var radio_longpress = ::gen_radio_longpress_fn
    open fun gen_radio_longpress_fn() {
        console.log("如果一个组件被绑定了 longpress 事件，那么当用户长按这个组件时，该事件将会被触发。", " at pages/component/radio/radio.uvue:84")
    }
    open var change_checked_boolean = ::gen_change_checked_boolean_fn
    open fun gen_change_checked_boolean_fn(checked: Boolean) {
        this.checked_boolean = checked
    }
    open var change_disabled_boolean = ::gen_change_disabled_boolean_fn
    open fun gen_change_disabled_boolean_fn(checked: Boolean) {
        this.disabled_boolean = checked
    }
    open var confirm_color_input = ::gen_confirm_color_input_fn
    open fun gen_confirm_color_input_fn(value: String) {
        this.color_input = value
    }
    open var confirm_backgroundColor_input = ::gen_confirm_backgroundColor_input_fn
    open fun gen_confirm_backgroundColor_input_fn(value: String) {
        this.backgroundColor_input = value
    }
    open var confirm_borderColor_input = ::gen_confirm_borderColor_input_fn
    open fun gen_confirm_borderColor_input_fn(value: String) {
        this.borderColor_input = value
    }
    open var confirm_activeBackgroundColor_input = ::gen_confirm_activeBackgroundColor_input_fn
    open fun gen_confirm_activeBackgroundColor_input_fn(value: String) {
        this.activeBackgroundColor_input = value
    }
    open var confirm_activeBorderColor_input = ::gen_confirm_activeBorderColor_input_fn
    open fun gen_confirm_activeBorderColor_input_fn(value: String) {
        this.activeBorderColor_input = value
    }
    open var confirm_iconColor_input = ::gen_confirm_iconColor_input_fn
    open fun gen_confirm_iconColor_input_fn(value: String) {
        this.iconColor_input = value
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
                return utsMapOf("main" to padStyleMapOf(utsMapOf("maxHeight" to 250, "paddingTop" to 5, "paddingRight" to 0, "paddingBottom" to 5, "paddingLeft" to 0, "borderBottomWidth" to 1, "borderBottomStyle" to "solid", "borderBottomColor" to "rgba(0,0,0,0.06)", "flexDirection" to "row", "justifyContent" to "center")), "uni-list-cell" to padStyleMapOf(utsMapOf("justifyContent" to "flex-start")), "justify-test" to padStyleMapOf(utsMapOf("width" to "100%", "justifyContent" to "space-between")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
