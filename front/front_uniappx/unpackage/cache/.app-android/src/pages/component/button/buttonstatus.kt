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
open class GenPagesComponentButtonButtonstatus : BasePage {
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
        return createElementVNode("scroll-view", utsMapOf("direction" to "vertical", "style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "main"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "container"), utsArrayOf(
                    createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "10px")), "disabled" to _ctx.disabled_boolean, "size" to _ctx.size_enum[_ctx.size_enum_current].name, "type" to _ctx.type_enum[0].name, "plain" to _ctx.plain_boolean, "onClick" to utsArrayOf(
                        _ctx.button_click,
                        _ctx.button_tap
                    ), "onTouchstart" to _ctx.button_touchstart, "onTouchmove" to _ctx.button_touchmove, "onTouchcancel" to _ctx.button_touchcancel, "onTouchend" to _ctx.button_touchend, "onLongpress" to _ctx.button_longpress, "class" to normalizeClass(utsArrayOf(
                        "btn",
                        if (_ctx.default_style) {
                            "custom-btn"
                        } else {
                            ""
                        }
                    ))), " type-default ", 46, utsArrayOf(
                        "disabled",
                        "size",
                        "type",
                        "plain",
                        "onClick",
                        "onTouchstart",
                        "onTouchmove",
                        "onTouchcancel",
                        "onTouchend",
                        "onLongpress"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "container"), utsArrayOf(
                    createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "10px")), "disabled" to _ctx.disabled_boolean, "size" to _ctx.size_enum[_ctx.size_enum_current].name, "type" to _ctx.type_enum[1].name, "plain" to _ctx.plain_boolean, "onClick" to utsArrayOf(
                        _ctx.button_click,
                        _ctx.button_tap
                    ), "onTouchstart" to _ctx.button_touchstart, "onTouchmove" to _ctx.button_touchmove, "onTouchcancel" to _ctx.button_touchcancel, "onTouchend" to _ctx.button_touchend, "onLongpress" to _ctx.button_longpress, "class" to normalizeClass(utsArrayOf(
                        "btn",
                        if (_ctx.default_style) {
                            "custom-btn"
                        } else {
                            ""
                        }
                    ))), " type-primary ", 46, utsArrayOf(
                        "disabled",
                        "size",
                        "type",
                        "plain",
                        "onClick",
                        "onTouchstart",
                        "onTouchmove",
                        "onTouchcancel",
                        "onTouchend",
                        "onLongpress"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "container"), utsArrayOf(
                    createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "10px")), "disabled" to _ctx.disabled_boolean, "size" to _ctx.size_enum[_ctx.size_enum_current].name, "type" to _ctx.type_enum[2].name, "plain" to _ctx.plain_boolean, "onClick" to utsArrayOf(
                        _ctx.button_click,
                        _ctx.button_tap
                    ), "onTouchstart" to _ctx.button_touchstart, "onTouchmove" to _ctx.button_touchmove, "onTouchcancel" to _ctx.button_touchcancel, "onTouchend" to _ctx.button_touchend, "onLongpress" to _ctx.button_longpress, "class" to normalizeClass(utsArrayOf(
                        "btn",
                        if (_ctx.default_style) {
                            "custom-btn"
                        } else {
                            ""
                        }
                    ))), " type-warn ", 46, utsArrayOf(
                        "disabled",
                        "size",
                        "type",
                        "plain",
                        "onClick",
                        "onTouchstart",
                        "onTouchmove",
                        "onTouchcancel",
                        "onTouchend",
                        "onLongpress"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "container"), utsArrayOf(
                    createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "10px")), "disabled" to _ctx.disabled_boolean, "size" to _ctx.size_enum[0].name, "type" to _ctx.type_enum[_ctx.type_enum_current].name, "plain" to _ctx.plain_boolean, "onClick" to utsArrayOf(
                        _ctx.button_click,
                        _ctx.button_tap
                    ), "onTouchstart" to _ctx.button_touchstart, "onTouchmove" to _ctx.button_touchmove, "onTouchcancel" to _ctx.button_touchcancel, "onTouchend" to _ctx.button_touchend, "onLongpress" to _ctx.button_longpress, "class" to normalizeClass(utsArrayOf(
                        "btn",
                        if (_ctx.default_style) {
                            "custom-btn"
                        } else {
                            ""
                        }
                    )), "hover-class" to if (_ctx.default_style) {
                        "is-hover"
                    } else {
                        "button-hover"
                    }
                    ), " size-default ", 46, utsArrayOf(
                        "disabled",
                        "size",
                        "type",
                        "plain",
                        "onClick",
                        "onTouchstart",
                        "onTouchmove",
                        "onTouchcancel",
                        "onTouchend",
                        "onLongpress",
                        "hover-class"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "container"), utsArrayOf(
                    createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "10px")), "disabled" to _ctx.disabled_boolean, "size" to _ctx.size_enum[1].name, "type" to _ctx.type_enum[_ctx.type_enum_current].name, "plain" to _ctx.plain_boolean, "onClick" to utsArrayOf(
                        _ctx.button_click,
                        _ctx.button_tap
                    ), "onTouchstart" to _ctx.button_touchstart, "onTouchmove" to _ctx.button_touchmove, "onTouchcancel" to _ctx.button_touchcancel, "onTouchend" to _ctx.button_touchend, "onLongpress" to _ctx.button_longpress, "class" to normalizeClass(utsArrayOf(
                        "btn",
                        if (_ctx.default_style) {
                            "custom-btn"
                        } else {
                            ""
                        }
                    ))), " size-mini ", 46, utsArrayOf(
                        "disabled",
                        "size",
                        "type",
                        "plain",
                        "onClick",
                        "onTouchstart",
                        "onTouchmove",
                        "onTouchcancel",
                        "onTouchend",
                        "onLongpress"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "container"), utsArrayOf(
                    createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "10px")), "disabled" to _ctx.disabled_boolean, "size" to _ctx.size_enum[_ctx.size_enum_current].name, "type" to _ctx.type_enum[_ctx.type_enum_current].name, "plain" to true, "onClick" to utsArrayOf(
                        _ctx.button_click,
                        _ctx.button_tap
                    ), "onTouchstart" to _ctx.button_touchstart, "onTouchmove" to _ctx.button_touchmove, "onTouchcancel" to _ctx.button_touchcancel, "onTouchend" to _ctx.button_touchend, "onLongpress" to _ctx.button_longpress, "class" to normalizeClass(utsArrayOf(
                        "btn",
                        if (_ctx.default_style) {
                            "custom-btn"
                        } else {
                            ""
                        }
                    )), "hover-class" to if (_ctx.default_style) {
                        "is-hover"
                    } else {
                        "button-hover"
                    }
                    ), " plain=true ", 46, utsArrayOf(
                        "disabled",
                        "size",
                        "type",
                        "onClick",
                        "onTouchstart",
                        "onTouchmove",
                        "onTouchcancel",
                        "onTouchend",
                        "onLongpress",
                        "hover-class"
                    )),
                    createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "10px")), "disabled" to _ctx.disabled_boolean, "size" to _ctx.size_enum[_ctx.size_enum_current].name, "type" to "primary", "plain" to true, "onClick" to utsArrayOf(
                        _ctx.button_click,
                        _ctx.button_tap
                    ), "onTouchstart" to _ctx.button_touchstart, "onTouchmove" to _ctx.button_touchmove, "onTouchcancel" to _ctx.button_touchcancel, "onTouchend" to _ctx.button_touchend, "onLongpress" to _ctx.button_longpress, "class" to normalizeClass(utsArrayOf(
                        "btn",
                        if (_ctx.default_style) {
                            "custom-btn"
                        } else {
                            ""
                        }
                    )), "hover-class" to if (_ctx.default_style) {
                        "is-hover"
                    } else {
                        "button-hover"
                    }
                    ), " plain=true type=primary ", 46, utsArrayOf(
                        "disabled",
                        "size",
                        "onClick",
                        "onTouchstart",
                        "onTouchmove",
                        "onTouchcancel",
                        "onTouchend",
                        "onLongpress",
                        "hover-class"
                    )),
                    createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "10px")), "disabled" to _ctx.disabled_boolean, "size" to _ctx.size_enum[_ctx.size_enum_current].name, "type" to "warn", "plain" to true, "onClick" to utsArrayOf(
                        _ctx.button_click,
                        _ctx.button_tap
                    ), "onTouchstart" to _ctx.button_touchstart, "onTouchmove" to _ctx.button_touchmove, "onTouchcancel" to _ctx.button_touchcancel, "onTouchend" to _ctx.button_touchend, "onLongpress" to _ctx.button_longpress, "class" to normalizeClass(utsArrayOf(
                        "btn",
                        if (_ctx.default_style) {
                            "custom-btn"
                        } else {
                            ""
                        }
                    )), "hover-class" to if (_ctx.default_style) {
                        "is-hover"
                    } else {
                        "button-hover"
                    }
                    ), " plain=true type=warn ", 46, utsArrayOf(
                        "disabled",
                        "size",
                        "onClick",
                        "onTouchstart",
                        "onTouchmove",
                        "onTouchcancel",
                        "onTouchend",
                        "onLongpress",
                        "hover-class"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "container"), utsArrayOf(
                    createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "10px")), "disabled" to _ctx.disabled_boolean, "size" to _ctx.size_enum[_ctx.size_enum_current].name, "type" to _ctx.type_enum[_ctx.type_enum_current].name, "plain" to false, "onClick" to utsArrayOf(
                        _ctx.button_click,
                        _ctx.button_tap
                    ), "onTouchstart" to _ctx.button_touchstart, "onTouchmove" to _ctx.button_touchmove, "onTouchcancel" to _ctx.button_touchcancel, "onTouchend" to _ctx.button_touchend, "onLongpress" to _ctx.button_longpress, "class" to normalizeClass(utsArrayOf(
                        "btn",
                        if (_ctx.default_style) {
                            "custom-btn"
                        } else {
                            ""
                        }
                    ))), " plain-false ", 46, utsArrayOf(
                        "disabled",
                        "size",
                        "type",
                        "onClick",
                        "onTouchstart",
                        "onTouchmove",
                        "onTouchcancel",
                        "onTouchend",
                        "onLongpress"
                    ))
                ))
            )),
            createElementVNode("view", utsMapOf("class" to "container"), utsArrayOf(
                createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "10px", "position" to "relative", "text-align" to "left", "padding" to "5px", "color" to "#ffffff", "background-color" to "#1AAD19", "font-size" to "10px", "line-height" to "2")), "disabled" to _ctx.disabled_boolean, "hover-class" to "is-hover"), " custom-btn ", 12, utsArrayOf(
                    "disabled"
                )),
                createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "10px", "position" to "relative", "text-align" to "left", "padding" to "5px", "color" to "#ffffff", "background-color" to "#1AAD19", "font-size" to "10px", "line-height" to "2")), "plain" to true, "disabled" to _ctx.disabled_boolean, "hover-class" to "is-hover"), " custom-btn ", 12, utsArrayOf(
                    "disabled"
                ))
            )),
            createElementVNode("view", utsMapOf("class" to "container"), utsArrayOf(
                createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "10px", "font-size" to "15px", "color" to "red")), "type" to "warn", "plain" to "true", "disabled" to _ctx.disabled_boolean), " custom-btn-plain-warn ", 12, utsArrayOf(
                    "disabled"
                ))
            )),
            createElementVNode("view", utsMapOf("class" to "content"), utsArrayOf(
                createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "10px")), "type" to "primary", "size" to "default", "disabled" to _ctx.disabled_boolean, "hover-class" to "none"), "hover-class none", 12, utsArrayOf(
                    "disabled"
                ))
            )),
            createElementVNode("view", utsMapOf("class" to "content"), utsArrayOf(
                createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "10px")), "type" to "primary", "size" to "default", "disabled" to _ctx.disabled_boolean), "uni-app x", 12, utsArrayOf(
                    "disabled"
                )),
                createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "10px")), "type" to "primary", "onClick" to fun(){
                    _ctx.change_disabled_boolean(!_ctx.disabled_boolean)
                }
                ), "修改disabled为" + toDisplayString(!_ctx.disabled_boolean), 13, utsArrayOf(
                    "onClick"
                ))
            )),
            createElementVNode("view", utsMapOf("class" to "container"), utsArrayOf(
                createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "10px")), "disabled" to false, "size" to _ctx.size_enum[_ctx.size_enum_current].name, "type" to _ctx.type_enum[_ctx.type_enum_current].name, "plain" to _ctx.plain_boolean, "onClick" to utsArrayOf(
                    _ctx.button_click,
                    _ctx.button_tap
                ), "onTouchstart" to _ctx.button_touchstart, "onTouchmove" to _ctx.button_touchmove, "onTouchcancel" to _ctx.button_touchcancel, "onTouchend" to _ctx.button_touchend, "onLongpress" to _ctx.button_longpress, "class" to normalizeClass(utsArrayOf(
                    "btn",
                    if (_ctx.default_style) {
                        "custom-btn"
                    } else {
                        ""
                    }
                ))), " disable-false ", 46, utsArrayOf(
                    "size",
                    "type",
                    "plain",
                    "onClick",
                    "onTouchstart",
                    "onTouchmove",
                    "onTouchcancel",
                    "onTouchend",
                    "onLongpress"
                ))
            )),
            createElementVNode("view", utsMapOf("class" to "container"), utsArrayOf(
                createElementVNode("button", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "10px")), "disabled" to true, "size" to _ctx.size_enum[_ctx.size_enum_current].name, "type" to _ctx.type_enum[_ctx.type_enum_current].name, "plain" to _ctx.plain_boolean, "onClick" to utsArrayOf(
                    _ctx.button_click,
                    _ctx.button_tap
                ), "onTouchstart" to _ctx.button_touchstart, "onTouchmove" to _ctx.button_touchmove, "onTouchcancel" to _ctx.button_touchcancel, "onTouchend" to _ctx.button_touchend, "onLongpress" to _ctx.button_longpress, "class" to normalizeClass(utsArrayOf(
                    "btn",
                    if (_ctx.default_style) {
                        "custom-btn"
                    } else {
                        ""
                    }
                ))), " disable-true ", 46, utsArrayOf(
                    "size",
                    "type",
                    "plain",
                    "onClick",
                    "onTouchstart",
                    "onTouchmove",
                    "onTouchcancel",
                    "onTouchend",
                    "onLongpress"
                ))
            ))
        ), 4)
    }
    open var plain_boolean: Boolean by `$data`
    open var disabled_boolean: Boolean by `$data`
    open var default_style: Boolean by `$data`
    open var size_enum: UTSArray<ItemType> by `$data`
    open var size_enum_current: Number by `$data`
    open var type_enum: UTSArray<ItemType> by `$data`
    open var type_enum_current: Number by `$data`
    open var count: Number by `$data`
    open var text: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("plain_boolean" to false, "disabled_boolean" to false, "default_style" to false, "size_enum" to utsArrayOf<ItemType>(ItemType(value = 0, name = "default"), ItemType(value = 1, name = "mini")), "size_enum_current" to 0, "type_enum" to utsArrayOf<ItemType>(ItemType(value = 0, name = "default"), ItemType(value = 1, name = "primary"), ItemType(value = 2, name = "warn")), "type_enum_current" to 0, "count" to 0, "text" to "uni-app-x")
    }
    open var button_click = ::gen_button_click_fn
    open fun gen_button_click_fn() {
        console.log("组件被点击时触发", " at pages/component/button/buttonstatus.uvue:19")
        this.count++
    }
    open var button_touchstart = ::gen_button_touchstart_fn
    open fun gen_button_touchstart_fn() {
        console.log("手指触摸动作开始", " at pages/component/button/buttonstatus.uvue:22")
    }
    open var button_touchmove = ::gen_button_touchmove_fn
    open fun gen_button_touchmove_fn() {
        console.log("手指触摸后移动", " at pages/component/button/buttonstatus.uvue:23")
    }
    open var button_touchcancel = ::gen_button_touchcancel_fn
    open fun gen_button_touchcancel_fn() {
        console.log("手指触摸动作被打断，如来电提醒，弹窗", " at pages/component/button/buttonstatus.uvue:24")
    }
    open var button_touchend = ::gen_button_touchend_fn
    open fun gen_button_touchend_fn() {
        console.log("手指触摸动作结束", " at pages/component/button/buttonstatus.uvue:25")
    }
    open var button_tap = ::gen_button_tap_fn
    open fun gen_button_tap_fn() {
        console.log("手指触摸后马上离开", " at pages/component/button/buttonstatus.uvue:26")
    }
    open var button_longpress = ::gen_button_longpress_fn
    open fun gen_button_longpress_fn() {
        console.log("如果一个组件被绑定了 longpress 事件，那么当用户长按这个组件时，该事件将会被触发。", " at pages/component/button/buttonstatus.uvue:27")
    }
    open var change_plain_boolean = ::gen_change_plain_boolean_fn
    open fun gen_change_plain_boolean_fn(checked: Boolean) {
        this.plain_boolean = checked
    }
    open var change_disabled_boolean = ::gen_change_disabled_boolean_fn
    open fun gen_change_disabled_boolean_fn(checked: Boolean) {
        this.disabled_boolean = checked
    }
    open var change_default_style = ::gen_change_default_style_fn
    open fun gen_change_default_style_fn(checked: Boolean) {
        this.default_style = checked
    }
    open var radio_change_size_enum = ::gen_radio_change_size_enum_fn
    open fun gen_radio_change_size_enum_fn(checked: Number) {
        this.size_enum_current = checked
    }
    open var radio_change_type_enum = ::gen_radio_change_type_enum_fn
    open fun gen_radio_change_type_enum_fn(checked: Number) {
        this.type_enum_current = checked
    }
    open var confirm_text_input = ::gen_confirm_text_input_fn
    open fun gen_confirm_text_input_fn(value: String) {
        this.text = value
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
                return utsMapOf("main" to padStyleMapOf(utsMapOf("paddingTop" to 5, "paddingRight" to 0, "paddingBottom" to 5, "paddingLeft" to 0, "borderBottomWidth" to 1, "borderBottomStyle" to "solid", "borderBottomColor" to "rgba(0,0,0,0.06)", "flexDirection" to "column", "justifyContent" to "center")), "list-item" to utsMapOf(".main " to utsMapOf("width" to "100%", "height" to 100, "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#666666", "borderRightColor" to "#666666", "borderBottomColor" to "#666666", "borderLeftColor" to "#666666")), "custom-btn" to padStyleMapOf(utsMapOf("color" to "#ffffff", "backgroundColor" to "#1AAD19", "borderTopColor" to "#1AAD19", "borderRightColor" to "#1AAD19", "borderBottomColor" to "#1AAD19", "borderLeftColor" to "#1AAD19", "borderTopLeftRadius" to 5, "borderTopRightRadius" to 5, "borderBottomRightRadius" to 5, "borderBottomLeftRadius" to 5, "borderTopWidth" to 0.5, "borderRightWidth" to 0.5, "borderBottomWidth" to 0.5, "borderLeftWidth" to 0.5, "height" to 53)), "is-hover" to padStyleMapOf(utsMapOf("color" to "rgba(255,255,255,0.6)", "backgroundColor" to "#179b16", "borderTopColor" to "#179b16", "borderRightColor" to "#179b16", "borderBottomColor" to "#179b16", "borderLeftColor" to "#179b16")), "container" to padStyleMapOf(utsMapOf("width" to "100%")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
