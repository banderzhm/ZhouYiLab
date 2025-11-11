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
import io.dcloud.uniapp.extapi.getElementById as uni_getElementById
import io.dcloud.uniapp.extapi.getSystemInfoSync as uni_getSystemInfoSync
open class GenPagesComponentTextareaTextarea : BasePage {
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
            val textarea = uni_getElementById("uni-textarea")
            this.textareaRect = textarea?.getBoundingClientRect()
            this.textareaRect!!.y += uni_getSystemInfoSync().safeArea.top + 44
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_boolean_data = resolveEasyComponent("boolean-data", GenComponentsBooleanDataBooleanDataClass)
        val _component_enum_data = resolveEasyComponent("enum-data", GenComponentsEnumDataEnumDataClass)
        val _component_switch = resolveComponent("switch")
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "main"), utsArrayOf(
                createElementVNode("textarea", utsMapOf("value" to _ctx.default_value, "id" to "uni-textarea", "class" to "uni-textarea", "auto-focus" to true, "focus" to _ctx.focus_boolean, "confirm-hold" to _ctx.confirm_hold_boolean, "auto-height" to _ctx.auto_height_boolean, "fixed" to _ctx.fixed_boolean, "show-confirm-bar" to _ctx.show_confirm_bar_boolean, "adjust-position" to _ctx.adjust_position_boolean, "cursor-color" to _ctx.cursor_color, "cursor" to _ctx.cursor, "placeholder" to _ctx.placeholder_value, "inputmode" to _ctx.inputmode_enum[_ctx.inputmode_enum_current].name, "confirm-type" to _ctx.confirm_type_list[_ctx.confirm_type_current].name, "disabled" to _ctx.disabled, "onClick" to utsArrayOf(
                    _ctx.textarea_click,
                    _ctx.textarea_tap
                ), "onTouchstart" to _ctx.textarea_touchstart, "onTouchmove" to _ctx.textarea_touchmove, "onTouchcancel" to _ctx.textarea_touchcancel, "onTouchend" to _ctx.textarea_touchend, "onLongpress" to _ctx.textarea_longpress, "onConfirm" to _ctx.textarea_confirm, "onInput" to _ctx.textarea_input, "onLinechange" to _ctx.textarea_linechange, "onBlur" to _ctx.textarea_blur, "onKeyboardheightchange" to _ctx.textarea_keyboardheightchange, "onFocus" to _ctx.textarea_focus, "onChange" to _ctx.textarea_change, "style" to normalizeStyle(utsMapOf("padding" to "10px", "border" to "1px solid #666", "height" to "200px"))), null, 44, utsArrayOf(
                    "value",
                    "focus",
                    "confirm-hold",
                    "auto-height",
                    "fixed",
                    "show-confirm-bar",
                    "adjust-position",
                    "cursor-color",
                    "cursor",
                    "placeholder",
                    "inputmode",
                    "confirm-type",
                    "disabled",
                    "onClick",
                    "onTouchstart",
                    "onTouchmove",
                    "onTouchcancel",
                    "onTouchend",
                    "onLongpress",
                    "onConfirm",
                    "onInput",
                    "onLinechange",
                    "onBlur",
                    "onKeyboardheightchange",
                    "onFocus",
                    "onChange"
                ))
            )),
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("margin-bottom" to "40px"))), utsArrayOf(
                createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "键盘弹起时，是否自动上推页面（限非 Web 平台）", "onChange" to _ctx.change_adjust_position_boolean), null, 8, utsArrayOf(
                    "onChange"
                )),
                createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "是否自动增高，设置auto-height时，style.height不生效", "onChange" to _ctx.change_auto_height_boolean), null, 8, utsArrayOf(
                    "onChange"
                )),
                createVNode(_component_boolean_data, utsMapOf("defaultValue" to _ctx.focus_boolean, "title" to "获取焦点", "onChange" to _ctx.change_focus_boolean), null, 8, utsArrayOf(
                    "defaultValue",
                    "onChange"
                )),
                createVNode(_component_boolean_data, utsMapOf("defaultValue" to true, "title" to "首次自动获取焦点", "onChange" to _ctx.change_auto_focus_boolean), null, 8, utsArrayOf(
                    "onChange"
                )),
                createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "改变光标颜色为透明", "onChange" to _ctx.change_cursor_color_boolean), null, 8, utsArrayOf(
                    "onChange"
                )),
                createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "设置禁用输入框", "onChange" to _ctx.change_disabled_boolean), null, 8, utsArrayOf(
                    "onChange"
                )),
                createVNode(_component_enum_data, utsMapOf("items" to _ctx.confirm_type_list, "title" to "confirm-type，设置键盘右下角按钮。", "onChange" to _ctx.radio_change_confirm_type), null, 8, utsArrayOf(
                    "items",
                    "onChange"
                )),
                createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "点击软键盘右下角按钮时是否保持键盘不收起(confirm-type为return时必然不收起)", "onChange" to _ctx.change_confirm_hold_boolean), null, 8, utsArrayOf(
                    "onChange"
                )),
                createVNode(_component_enum_data, utsMapOf("items" to _ctx.inputmode_enum, "title" to "input-mode，控制软键盘类型。（仅限 Web 平台符合条件的高版本浏览器或webview）。", "onChange" to _ctx.radio_change_inputmode_enum), null, 8, utsArrayOf(
                    "items",
                    "onChange"
                )),
                createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "是否显示键盘上方带有“完成”按钮那一栏（仅限小程序平台）", "onChange" to _ctx.change_show_confirm_bar_boolean), null, 8, utsArrayOf(
                    "onChange"
                )),
                createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "如果 textarea 是在一个 position:fixed 的区域，需要显示指定属性 fixed 为 true（仅限小程序平台）", "onChange" to _ctx.change_fixed_boolean), null, 8, utsArrayOf(
                    "onChange"
                )),
                createElementVNode("view", utsMapOf("class" to "title-wrap"), utsArrayOf(
                    createElementVNode("view", null, "maxlength 输入最大长度为10")
                )),
                createElementVNode("view", utsMapOf("class" to "textarea-wrap"), utsArrayOf(
                    createElementVNode("textarea", utsMapOf("id" to "textarea-instance-maxlength", "class" to "textarea-instance", "value" to _ctx.textareaMaxLengthValue, "maxlength" to 10), null, 8, utsArrayOf(
                        "value"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "title-wrap"), utsArrayOf(
                    createElementVNode("view", null, "cursor-spacing、placeholder-class、placeholder-style例子(harmony 不支持设置 placeholder backgroundColor)")
                )),
                createElementVNode("view", utsMapOf("class" to "textarea-wrap"), utsArrayOf(
                    createElementVNode("textarea", utsMapOf("id" to "textarea-height-exception", "class" to "textarea-instance", "placeholder" to "底部textarea测试键盘遮挡", "placeholder-class" to "placeholder", "placeholder-style" to "background-color:red", "cursor-spacing" to 300))
                )),
                createElementVNode("view", utsMapOf("class" to "title-wrap"), utsArrayOf(
                    createElementVNode("view", utsMapOf("onClick" to fun(){
                        _ctx.setSelection(2, 5)
                    }
                    ), "设置输入框聚焦时光标的起始位置和结束位置（点击生效）", 8, utsArrayOf(
                        "onClick"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "textarea-wrap"), utsArrayOf(
                    createElementVNode("textarea", utsMapOf("id" to "textarea-instance-2", "class" to "textarea-instance", "value" to "Hello UniApp X Textarea TestCase", "focus" to _ctx.isSelectionFocus, "selection-start" to _ctx.selectionStart, "selection-end" to _ctx.selectionEnd, "onBlur" to _ctx.onSelectionBlurChange), null, 40, utsArrayOf(
                        "focus",
                        "selection-start",
                        "selection-end",
                        "onBlur"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "title-wrap"), utsArrayOf(
                    createElementVNode("view", null, "设置hold-keyboard"),
                    createVNode(_component_switch, utsMapOf("style" to normalizeStyle(utsMapOf("margin-left" to "10px")), "onChange" to _ctx.changeHoldKeyboard, "checked" to _ctx.hold_keyboard), null, 8, utsArrayOf(
                        "style",
                        "onChange",
                        "checked"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "textarea-wrap"), utsArrayOf(
                    createElementVNode("textarea", utsMapOf("class" to "textarea-instance", "hold-keyboard" to _ctx.hold_keyboard), null, 8, utsArrayOf(
                        "hold-keyboard"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "title-wrap"), utsArrayOf(
                    createElementVNode("view", null, "同时存在 v-model 和 value")
                )),
                createElementVNode("view", utsMapOf("class" to "textarea-wrap"), utsArrayOf(
                    createElementVNode("textarea", utsMapOf("id" to "both-model-value", "class" to "textarea-instance", "modelValue" to _ctx.defaultModel, "onInput" to fun(`$event`: InputEvent){
                        _ctx.defaultModel = `$event`.detail.value
                    }
                    , "value" to "456"), null, 40, utsArrayOf(
                        "modelValue",
                        "onInput"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "title-wrap"), utsArrayOf(
                    createElementVNode("view", null, "设置adjust-position"),
                    createVNode(_component_switch, utsMapOf("style" to normalizeStyle(utsMapOf("margin-left" to "10px")), "onChange" to _ctx.changeAdjustPosition, "checked" to _ctx.adjust_position), null, 8, utsArrayOf(
                        "style",
                        "onChange",
                        "checked"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "textarea-wrap"), utsArrayOf(
                    createElementVNode("textarea", utsMapOf("class" to "textarea-instance", "adjust-position" to _ctx.adjust_position), null, 8, utsArrayOf(
                        "adjust-position"
                    ))
                )),
                if (isTrue(_ctx.isAutoTest)) {
                    createElementVNode("view", utsMapOf("key" to 0, "class" to "textarea-wrap"), utsArrayOf(
                        createElementVNode("textarea", utsMapOf("id" to "test-width", "class" to "test-width", "value" to "123456", "placeholder" to ""))
                    ))
                } else {
                    createCommentVNode("v-if", true)
                }
                ,
                createElementVNode("view", utsMapOf("class" to "title-wrap"), utsArrayOf(
                    createElementVNode("view", null, "设置line-height")
                )),
                createElementVNode("view", utsMapOf("class" to "textarea-wrap"), utsArrayOf(
                    createElementVNode("textarea", utsMapOf("class" to "textarea-instance", "style" to normalizeStyle(utsMapOf("line-height" to "1.2em")), "value" to "设置line-height为1.2em"), null, 4)
                )),
                createElementVNode("view", utsMapOf("class" to "title-wrap"), utsArrayOf(
                    createElementVNode("view", null, "设置max-height与auto-height")
                )),
                createElementVNode("view", utsMapOf("class" to "textarea-wrap"), utsArrayOf(
                    createElementVNode("textarea", utsMapOf("class" to "textarea-instance", "style" to normalizeStyle(utsMapOf("max-height" to "50px")), "auto-height" to "true", "value" to _ctx.default_value), null, 12, utsArrayOf(
                        "value"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "title-wrap"), utsArrayOf(
                    createElementVNode("view", null, "同时设置value与text-align")
                )),
                createElementVNode("view", utsMapOf("class" to "textarea-wrap"), utsArrayOf(
                    createElementVNode("textarea", utsMapOf("class" to "textarea-instance", "style" to normalizeStyle(utsMapOf("height" to "50px", "text-align" to "right")), "value" to "同时设置value与text-align"), null, 4)
                )),
                createElementVNode("view", utsMapOf("class" to "title-wrap"), utsArrayOf(
                    createElementVNode("view", null, "scroll-view嵌套textarea滚动")
                )),
                createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("height" to "150px")), "direction" to "vertical"), utsArrayOf(
                    createElementVNode("textarea", utsMapOf("class" to "textarea-instance", "adjust-position" to false, "auto-height" to true, "value" to _ctx.value2), null, 8, utsArrayOf(
                        "value"
                    ))
                ), 4)
            ), 4)
        ), 4)
    }
    open var value2: String by `$data`
    open var adjust_position_boolean: Boolean by `$data`
    open var show_confirm_bar_boolean: Boolean by `$data`
    open var fixed_boolean: Boolean by `$data`
    open var auto_height_boolean: Boolean by `$data`
    open var confirm_hold_boolean: Boolean by `$data`
    open var focus_boolean: Boolean by `$data`
    open var auto_focus_boolean: Boolean by `$data`
    open var default_value: String by `$data`
    open var inputmode_enum: UTSArray<ItemType> by `$data`
    open var confirm_type_list: UTSArray<ItemType> by `$data`
    open var cursor_color: String by `$data`
    open var cursor: Number by `$data`
    open var inputmode_enum_current: Number by `$data`
    open var confirm_type_current: Number by `$data`
    open var placeholder_value: String by `$data`
    open var defaultModel: String by `$data`
    open var textareaMaxLengthValue: String by `$data`
    open var isSelectionFocus: Boolean by `$data`
    open var selectionStart: Number by `$data`
    open var selectionEnd: Number by `$data`
    open var hold_keyboard: Boolean by `$data`
    open var adjust_position: Boolean by `$data`
    open var disabled: Boolean by `$data`
    open var jest_result: Boolean by `$data`
    open var isAutoTest: Boolean by `$data`
    open var changeValue: String by `$data`
    open var textareaRect: DOMRect? by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("value2" to "第一行\n第二行\n第三行\n第四行\n第五行\n第六行\n第七行\n第八行\n第九行\n第十行\n十一行", "adjust_position_boolean" to false, "show_confirm_bar_boolean" to false, "fixed_boolean" to false, "auto_height_boolean" to false, "confirm_hold_boolean" to false, "focus_boolean" to true, "auto_focus_boolean" to false, "default_value" to "1\n2\n3\n4\n5\n6", "inputmode_enum" to utsArrayOf<ItemType>(ItemType(value = 1, name = "text"), ItemType(value = 2, name = "decimal"), ItemType(value = 3, name = "numeric"), ItemType(value = 4, name = "tel"), ItemType(value = 5, name = "search"), ItemType(value = 6, name = "email"), ItemType(value = 7, name = "url"), ItemType(value = 0, name = "none")), "confirm_type_list" to utsArrayOf<ItemType>(ItemType(value = 0, name = "return"), ItemType(value = 1, name = "done"), ItemType(value = 2, name = "send"), ItemType(value = 3, name = "search"), ItemType(value = 4, name = "next"), ItemType(value = 5, name = "go")), "cursor_color" to "#3393E2", "cursor" to 0, "inputmode_enum_current" to 0, "confirm_type_current" to 0, "placeholder_value" to "请输入", "defaultModel" to "123", "textareaMaxLengthValue" to "", "isSelectionFocus" to false, "selectionStart" to -1, "selectionEnd" to -1, "hold_keyboard" to false, "adjust_position" to false, "disabled" to false, "jest_result" to false, "isAutoTest" to false, "changeValue" to "", "textareaRect" to null as DOMRect?)
    }
    open var textarea_click = ::gen_textarea_click_fn
    open fun gen_textarea_click_fn() {
        console.log("组件被点击时触发", " at pages/component/textarea/textarea.uvue:45")
    }
    open var textarea_touchstart = ::gen_textarea_touchstart_fn
    open fun gen_textarea_touchstart_fn() {
        console.log("手指触摸动作开始", " at pages/component/textarea/textarea.uvue:46")
    }
    open var textarea_touchmove = ::gen_textarea_touchmove_fn
    open fun gen_textarea_touchmove_fn() {
        console.log("手指触摸后移动", " at pages/component/textarea/textarea.uvue:47")
    }
    open var textarea_touchcancel = ::gen_textarea_touchcancel_fn
    open fun gen_textarea_touchcancel_fn() {
        console.log("手指触摸动作被打断，如来电提醒，弹窗", " at pages/component/textarea/textarea.uvue:48")
    }
    open var textarea_touchend = ::gen_textarea_touchend_fn
    open fun gen_textarea_touchend_fn() {
        console.log("手指触摸动作结束", " at pages/component/textarea/textarea.uvue:49")
    }
    open var textarea_tap = ::gen_textarea_tap_fn
    open fun gen_textarea_tap_fn() {
        console.log("手指触摸后马上离开", " at pages/component/textarea/textarea.uvue:50")
    }
    open var textarea_longpress = ::gen_textarea_longpress_fn
    open fun gen_textarea_longpress_fn() {
        console.log("如果一个组件被绑定了 longpress 事件，那么当用户长按这个组件时，该事件将会被触发。", " at pages/component/textarea/textarea.uvue:51")
    }
    open var textarea_confirm = ::gen_textarea_confirm_fn
    open fun gen_textarea_confirm_fn() {
        console.log("点击完成时， 触发 confirm 事件，event.detail = {value: value}", " at pages/component/textarea/textarea.uvue:52")
    }
    open var textarea_input = ::gen_textarea_input_fn
    open fun gen_textarea_input_fn(e: UniInputEvent) {
        console.log("当键盘输入时，触发 input 事件，event.detail = {value, cursor}， @input 处理函数的返回值并不会反映到 textarea 上", " at pages/component/textarea/textarea.uvue:54")
        this.jest_result = e.detail.value == "1\n2\n3\n4\n5\n61"
    }
    open var textarea_linechange = ::gen_textarea_linechange_fn
    open fun gen_textarea_linechange_fn() {
        console.log("输入框行数变化时调用，event.detail = {height: 0, height: 0, lineCount: 0}", " at pages/component/textarea/textarea.uvue:62")
    }
    open var textarea_blur = ::gen_textarea_blur_fn
    open fun gen_textarea_blur_fn() {
        console.log("输入框失去焦点时触发，event.detail = {value, cursor}", " at pages/component/textarea/textarea.uvue:63")
    }
    open var textarea_keyboardheightchange = ::gen_textarea_keyboardheightchange_fn
    open fun gen_textarea_keyboardheightchange_fn() {
        console.log("键盘高度发生变化的时候触发此事件，event.detail = {height: height, duration: duration}", " at pages/component/textarea/textarea.uvue:64")
    }
    open var textarea_focus = ::gen_textarea_focus_fn
    open fun gen_textarea_focus_fn(event: UniTextareaFocusEvent) {
        this.jest_result = event.detail.height >= 0
    }
    open var textarea_change = ::gen_textarea_change_fn
    open fun gen_textarea_change_fn(event: UniInputChangeEvent) {
        console.log("textarea_change", event.detail.value, " at pages/component/textarea/textarea.uvue:69")
        this.changeValue = event.detail.value
    }
    open var change_adjust_position_boolean = ::gen_change_adjust_position_boolean_fn
    open fun gen_change_adjust_position_boolean_fn(checked: Boolean) {
        this.adjust_position_boolean = checked
    }
    open var change_show_confirm_bar_boolean = ::gen_change_show_confirm_bar_boolean_fn
    open fun gen_change_show_confirm_bar_boolean_fn(checked: Boolean) {
        this.show_confirm_bar_boolean = checked
    }
    open var change_fixed_boolean = ::gen_change_fixed_boolean_fn
    open fun gen_change_fixed_boolean_fn(checked: Boolean) {
        this.fixed_boolean = checked
    }
    open var change_auto_height_boolean = ::gen_change_auto_height_boolean_fn
    open fun gen_change_auto_height_boolean_fn(checked: Boolean) {
        this.auto_height_boolean = checked
    }
    open var change_confirm_hold_boolean = ::gen_change_confirm_hold_boolean_fn
    open fun gen_change_confirm_hold_boolean_fn(checked: Boolean) {
        this.confirm_hold_boolean = checked
    }
    open var change_focus_boolean = ::gen_change_focus_boolean_fn
    open fun gen_change_focus_boolean_fn(checked: Boolean) {
        this.focus_boolean = checked
    }
    open var change_auto_focus_boolean = ::gen_change_auto_focus_boolean_fn
    open fun gen_change_auto_focus_boolean_fn(checked: Boolean) {
        this.auto_focus_boolean = checked
    }
    open var change_cursor_color_boolean = ::gen_change_cursor_color_boolean_fn
    open fun gen_change_cursor_color_boolean_fn(checked: Boolean) {
        if (checked) {
            this.cursor_color = "transparent"
        } else {
            this.cursor_color = "#3393E2"
        }
    }
    open var radio_change_inputmode_enum = ::gen_radio_change_inputmode_enum_fn
    open fun gen_radio_change_inputmode_enum_fn(checked: Number) {
        this.inputmode_enum_current = checked
    }
    open var radio_change_confirm_type = ::gen_radio_change_confirm_type_fn
    open fun gen_radio_change_confirm_type_fn(checked: Number) {
        this.confirm_type_current = checked
    }
    open var setSelection = ::gen_setSelection_fn
    open fun gen_setSelection_fn(selectionStart: Number, selectionEnd: Number) {
        this.isSelectionFocus = true
        this.selectionStart = selectionStart
        this.selectionEnd = selectionEnd
    }
    open var onSelectionBlurChange = ::gen_onSelectionBlurChange_fn
    open fun gen_onSelectionBlurChange_fn() {
        this.isSelectionFocus = false
        this.selectionEnd = 0
    }
    open var changeHoldKeyboard = ::gen_changeHoldKeyboard_fn
    open fun gen_changeHoldKeyboard_fn(event: UniSwitchChangeEvent) {
        val checked = event.detail.value
        this.hold_keyboard = checked
    }
    open var changeAdjustPosition = ::gen_changeAdjustPosition_fn
    open fun gen_changeAdjustPosition_fn(event: UniSwitchChangeEvent) {
        val checked = event.detail.value
        this.adjust_position = checked
    }
    open var change_disabled_boolean = ::gen_change_disabled_boolean_fn
    open fun gen_change_disabled_boolean_fn(checked: Boolean) {
        this.disabled = checked
    }
    open var getBoundingClientRectForTest = ::gen_getBoundingClientRectForTest_fn
    open fun gen_getBoundingClientRectForTest_fn(): DOMRect? {
        return uni_getElementById("test-width")?.getBoundingClientRect()
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
                return utsMapOf("main" to padStyleMapOf(utsMapOf("minHeight" to 100, "paddingTop" to 5, "paddingRight" to 0, "paddingBottom" to 5, "paddingLeft" to 0, "borderBottomWidth" to 1, "borderBottomStyle" to "solid", "borderBottomColor" to "rgba(0,0,0,0.06)", "flexDirection" to "row", "justifyContent" to "center")), "test-width" to padStyleMapOf(utsMapOf("width" to 100, "height" to 100, "backgroundColor" to "#00FFFF")), "textarea-wrap" to padStyleMapOf(utsMapOf("flexDirection" to "row", "justifyContent" to "center")), "title-wrap" to padStyleMapOf(utsMapOf("flexDirection" to "row", "alignItems" to "center", "marginLeft" to 10)), "textarea-instance" to padStyleMapOf(utsMapOf("flex" to 1, "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#666666", "borderRightColor" to "#666666", "borderBottomColor" to "#666666", "borderLeftColor" to "#666666", "marginTop" to 10, "marginRight" to 10, "marginBottom" to 10, "marginLeft" to 10)), "placeholder" to padStyleMapOf(utsMapOf("backgroundColor" to "#FFFF00")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
