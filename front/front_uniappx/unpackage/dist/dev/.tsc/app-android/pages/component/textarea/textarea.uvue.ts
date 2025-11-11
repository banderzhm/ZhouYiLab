
  import { ItemType } from '@/components/enum-data/enum-data-types'
  const __sfc__ = defineComponent({
    data() {
      return {
        value2: '第一行\n第二行\n第三行\n第四行\n第五行\n第六行\n第七行\n第八行\n第九行\n第十行\n十一行',
        adjust_position_boolean: false,
        show_confirm_bar_boolean: false,
        fixed_boolean: false,
        auto_height_boolean: false,
        confirm_hold_boolean: false,
        focus_boolean: true,
        auto_focus_boolean: false,
        default_value: "1\n2\n3\n4\n5\n6",
        inputmode_enum: [{ "value": 1, "name": "text" }, { "value": 2, "name": "decimal" }, { "value": 3, "name": "numeric" }, { "value": 4, "name": "tel" }, { "value": 5, "name": "search" }, { "value": 6, "name": "email" }, { "value": 7, "name": "url" }, { "value": 0, "name": "none" }] as ItemType[],
        confirm_type_list: [{ "value": 0, "name": "return" }, { "value": 1, "name": "done" }, { "value": 2, "name": "send" }, { "value": 3, "name": "search" }, { "value": 4, "name": "next" }, { "value": 5, "name": "go" }] as ItemType[],
        cursor_color: "#3393E2",
        cursor: 0,
        inputmode_enum_current: 0,
        confirm_type_current: 0,
        placeholder_value: "请输入",
        defaultModel: '123',
        textareaMaxLengthValue: "",
        isSelectionFocus: false,
        selectionStart: -1,
        selectionEnd: -1,
        hold_keyboard: false,
        adjust_position: false,
        disabled: false,
        jest_result: false,
        isAutoTest: false,
        changeValue: "",
        textareaRect: null as DOMRect | null,
      }
    },

    onReady() {
      const textarea = uni.getElementById("uni-textarea")
      this.textareaRect = textarea?.getBoundingClientRect()
      // 加上导航栏及状态栏高度
      this.textareaRect!.y += uni.getSystemInfoSync().safeArea.top + 44
    },

    methods: {
      textarea_click() { console.log("组件被点击时触发", " at pages/component/textarea/textarea.uvue:45") },
      textarea_touchstart() { console.log("手指触摸动作开始", " at pages/component/textarea/textarea.uvue:46") },
      textarea_touchmove() { console.log("手指触摸后移动", " at pages/component/textarea/textarea.uvue:47") },
      textarea_touchcancel() { console.log("手指触摸动作被打断，如来电提醒，弹窗", " at pages/component/textarea/textarea.uvue:48") },
      textarea_touchend() { console.log("手指触摸动作结束", " at pages/component/textarea/textarea.uvue:49") },
      textarea_tap() { console.log("手指触摸后马上离开", " at pages/component/textarea/textarea.uvue:50") },
      textarea_longpress() { console.log("如果一个组件被绑定了 longpress 事件，那么当用户长按这个组件时，该事件将会被触发。", " at pages/component/textarea/textarea.uvue:51") },
      textarea_confirm() { console.log("点击完成时， 触发 confirm 事件，event.detail = {value: value}", " at pages/component/textarea/textarea.uvue:52") },
      textarea_input(e: UniInputEvent) {
        console.log("当键盘输入时，触发 input 事件，event.detail = {value, cursor}， @input 处理函数的返回值并不会反映到 textarea 上", " at pages/component/textarea/textarea.uvue:54")




        this.jest_result = e.detail.value == '1\n2\n3\n4\n5\n61'

      },
      textarea_linechange() { console.log("输入框行数变化时调用，event.detail = {height: 0, height: 0, lineCount: 0}", " at pages/component/textarea/textarea.uvue:62") },
      textarea_blur() { console.log("输入框失去焦点时触发，event.detail = {value, cursor}", " at pages/component/textarea/textarea.uvue:63") },
      textarea_keyboardheightchange() { console.log("键盘高度发生变化的时候触发此事件，event.detail = {height: height, duration: duration}", " at pages/component/textarea/textarea.uvue:64") },
      textarea_focus(event: UniTextareaFocusEvent) {
        this.jest_result = event.detail.height >= 0
      },
      textarea_change(event: UniInputChangeEvent) {
        console.log("textarea_change", event.detail.value, " at pages/component/textarea/textarea.uvue:69");
        this.changeValue = event.detail.value
      },
      change_adjust_position_boolean(checked : boolean) { this.adjust_position_boolean = checked },
      change_show_confirm_bar_boolean(checked : boolean) { this.show_confirm_bar_boolean = checked },
      change_fixed_boolean(checked : boolean) { this.fixed_boolean = checked },
      change_auto_height_boolean(checked : boolean) { this.auto_height_boolean = checked },
      change_confirm_hold_boolean(checked : boolean) { this.confirm_hold_boolean = checked },
      change_focus_boolean(checked : boolean) { this.focus_boolean = checked },
      change_auto_focus_boolean(checked : boolean) { this.auto_focus_boolean = checked },
      change_cursor_color_boolean(checked : boolean) { if (checked) { this.cursor_color = "transparent" } else { this.cursor_color = "#3393E2" } },
      radio_change_inputmode_enum(checked : number) { this.inputmode_enum_current = checked },
      radio_change_confirm_type(checked : number) { this.confirm_type_current = checked },
      setSelection: function (selectionStart : number, selectionEnd : number) {
        this.isSelectionFocus = true;
        this.selectionStart = selectionStart;
        this.selectionEnd = selectionEnd;
      },
      onSelectionBlurChange() {
      this.isSelectionFocus = false;
        this.selectionEnd = 0;
      },
      changeHoldKeyboard(event : UniSwitchChangeEvent) {
        const checked = event.detail.value;
        this.hold_keyboard = checked;
      },
      changeAdjustPosition(event : UniSwitchChangeEvent) {
        const checked = event.detail.value;
        this.adjust_position = checked;
      },
      change_disabled_boolean(checked : boolean) {
        this.disabled = checked
      },
      getBoundingClientRectForTest() : DOMRect | null {
        return uni.getElementById('test-width')?.getBoundingClientRect();
      }
    }
  })

export default __sfc__
function GenPagesComponentTextareaTextareaRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_boolean_data = resolveEasyComponent("boolean-data",_easycom_boolean_data)
const _component_enum_data = resolveEasyComponent("enum-data",_easycom_enum_data)
const _component_switch = resolveComponent("switch")

  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("view", utsMapOf({ class: "main" }), [
      createElementVNode("textarea", utsMapOf({
        value: _ctx.default_value,
        id: "uni-textarea",
        class: "uni-textarea",
        "auto-focus": true,
        focus: _ctx.focus_boolean,
        "confirm-hold": _ctx.confirm_hold_boolean,
        "auto-height": _ctx.auto_height_boolean,
        fixed: _ctx.fixed_boolean,
        "show-confirm-bar": _ctx.show_confirm_bar_boolean,
        "adjust-position": _ctx.adjust_position_boolean,
        "cursor-color": _ctx.cursor_color,
        cursor: _ctx.cursor,
        placeholder: _ctx.placeholder_value,
        inputmode: _ctx.inputmode_enum[_ctx.inputmode_enum_current].name,
        "confirm-type": _ctx.confirm_type_list[_ctx.confirm_type_current].name,
        disabled: _ctx.disabled,
        onClick: [_ctx.textarea_click, _ctx.textarea_tap],
        onTouchstart: _ctx.textarea_touchstart,
        onTouchmove: _ctx.textarea_touchmove,
        onTouchcancel: _ctx.textarea_touchcancel,
        onTouchend: _ctx.textarea_touchend,
        onLongpress: _ctx.textarea_longpress,
        onConfirm: _ctx.textarea_confirm,
        onInput: _ctx.textarea_input,
        onLinechange: _ctx.textarea_linechange,
        onBlur: _ctx.textarea_blur,
        onKeyboardheightchange: _ctx.textarea_keyboardheightchange,
        onFocus: _ctx.textarea_focus,
        onChange: _ctx.textarea_change,
        style: normalizeStyle(utsMapOf({"padding":"10px","border":"1px solid #666","height":"200px"}))
      }), null, 44 /* STYLE, PROPS, NEED_HYDRATION */, ["value", "focus", "confirm-hold", "auto-height", "fixed", "show-confirm-bar", "adjust-position", "cursor-color", "cursor", "placeholder", "inputmode", "confirm-type", "disabled", "onClick", "onTouchstart", "onTouchmove", "onTouchcancel", "onTouchend", "onLongpress", "onConfirm", "onInput", "onLinechange", "onBlur", "onKeyboardheightchange", "onFocus", "onChange"])
    ]),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"margin-bottom":"40px"}))
    }), [
      createVNode(_component_boolean_data, utsMapOf({
        defaultValue: false,
        title: "键盘弹起时，是否自动上推页面（限非 Web 平台）",
        onChange: _ctx.change_adjust_position_boolean
      }), null, 8 /* PROPS */, ["onChange"]),
      createVNode(_component_boolean_data, utsMapOf({
        defaultValue: false,
        title: "是否自动增高，设置auto-height时，style.height不生效",
        onChange: _ctx.change_auto_height_boolean
      }), null, 8 /* PROPS */, ["onChange"]),
      createVNode(_component_boolean_data, utsMapOf({
        defaultValue: _ctx.focus_boolean,
        title: "获取焦点",
        onChange: _ctx.change_focus_boolean
      }), null, 8 /* PROPS */, ["defaultValue", "onChange"]),
      createVNode(_component_boolean_data, utsMapOf({
        defaultValue: true,
        title: "首次自动获取焦点",
        onChange: _ctx.change_auto_focus_boolean
      }), null, 8 /* PROPS */, ["onChange"]),
      createVNode(_component_boolean_data, utsMapOf({
        defaultValue: false,
        title: "改变光标颜色为透明",
        onChange: _ctx.change_cursor_color_boolean
      }), null, 8 /* PROPS */, ["onChange"]),
      createVNode(_component_boolean_data, utsMapOf({
        defaultValue: false,
        title: "设置禁用输入框",
        onChange: _ctx.change_disabled_boolean
      }), null, 8 /* PROPS */, ["onChange"]),
      createVNode(_component_enum_data, utsMapOf({
        items: _ctx.confirm_type_list,
        title: "confirm-type，设置键盘右下角按钮。",
        onChange: _ctx.radio_change_confirm_type
      }), null, 8 /* PROPS */, ["items", "onChange"]),
      createVNode(_component_boolean_data, utsMapOf({
        defaultValue: false,
        title: "点击软键盘右下角按钮时是否保持键盘不收起(confirm-type为return时必然不收起)",
        onChange: _ctx.change_confirm_hold_boolean
      }), null, 8 /* PROPS */, ["onChange"]),
      createVNode(_component_enum_data, utsMapOf({
        items: _ctx.inputmode_enum,
        title: "input-mode，控制软键盘类型。（仅限 Web 平台符合条件的高版本浏览器或webview）。",
        onChange: _ctx.radio_change_inputmode_enum
      }), null, 8 /* PROPS */, ["items", "onChange"]),
      createVNode(_component_boolean_data, utsMapOf({
        defaultValue: false,
        title: "是否显示键盘上方带有“完成”按钮那一栏（仅限小程序平台）",
        onChange: _ctx.change_show_confirm_bar_boolean
      }), null, 8 /* PROPS */, ["onChange"]),
      createVNode(_component_boolean_data, utsMapOf({
        defaultValue: false,
        title: "如果 textarea 是在一个 position:fixed 的区域，需要显示指定属性 fixed 为 true（仅限小程序平台）",
        onChange: _ctx.change_fixed_boolean
      }), null, 8 /* PROPS */, ["onChange"]),
      createElementVNode("view", utsMapOf({ class: "title-wrap" }), [
        createElementVNode("view", null, "maxlength 输入最大长度为10")
      ]),
      createElementVNode("view", utsMapOf({ class: "textarea-wrap" }), [
        createElementVNode("textarea", utsMapOf({
          id: "textarea-instance-maxlength",
          class: "textarea-instance",
          value: _ctx.textareaMaxLengthValue,
          maxlength: 10
        }), null, 8 /* PROPS */, ["value"])
      ]),
      createElementVNode("view", utsMapOf({ class: "title-wrap" }), [
        createElementVNode("view", null, "cursor-spacing、placeholder-class、placeholder-style例子(harmony 不支持设置 placeholder backgroundColor)")
      ]),
      createElementVNode("view", utsMapOf({ class: "textarea-wrap" }), [
        createElementVNode("textarea", utsMapOf({
          id: "textarea-height-exception",
          class: "textarea-instance",
          placeholder: "底部textarea测试键盘遮挡",
          "placeholder-class": "placeholder",
          "placeholder-style": "background-color:red",
          "cursor-spacing": 300
        }))
      ]),
      createElementVNode("view", utsMapOf({ class: "title-wrap" }), [
        createElementVNode("view", utsMapOf({
          onClick: () => {_ctx.setSelection(2, 5)}
        }), "设置输入框聚焦时光标的起始位置和结束位置（点击生效）", 8 /* PROPS */, ["onClick"])
      ]),
      createElementVNode("view", utsMapOf({ class: "textarea-wrap" }), [
        createElementVNode("textarea", utsMapOf({
          id: "textarea-instance-2",
          class: "textarea-instance",
          value: "Hello UniApp X Textarea TestCase",
          focus: _ctx.isSelectionFocus,
          "selection-start": _ctx.selectionStart,
          "selection-end": _ctx.selectionEnd,
          onBlur: _ctx.onSelectionBlurChange
        }), null, 40 /* PROPS, NEED_HYDRATION */, ["focus", "selection-start", "selection-end", "onBlur"])
      ]),
      createElementVNode("view", utsMapOf({ class: "title-wrap" }), [
        createElementVNode("view", null, "设置hold-keyboard"),
        createVNode(_component_switch, utsMapOf({
          style: normalizeStyle(utsMapOf({"margin-left":"10px"})),
          onChange: _ctx.changeHoldKeyboard,
          checked: _ctx.hold_keyboard
        }), null, 8 /* PROPS */, ["style", "onChange", "checked"])
      ]),
      createElementVNode("view", utsMapOf({ class: "textarea-wrap" }), [
        createElementVNode("textarea", utsMapOf({
          class: "textarea-instance",
          "hold-keyboard": _ctx.hold_keyboard
        }), null, 8 /* PROPS */, ["hold-keyboard"])
      ]),
      createElementVNode("view", utsMapOf({ class: "title-wrap" }), [
        createElementVNode("view", null, "同时存在 v-model 和 value")
      ]),
      createElementVNode("view", utsMapOf({ class: "textarea-wrap" }), [
        createElementVNode("textarea", utsMapOf({
          id: "both-model-value",
          class: "textarea-instance",
          modelValue: _ctx.defaultModel,
          onInput: ($event: InputEvent) => {(_ctx.defaultModel) = $event.detail.value},
          value: "456"
        }), null, 40 /* PROPS, NEED_HYDRATION */, ["modelValue", "onInput"])
      ]),
      createElementVNode("view", utsMapOf({ class: "title-wrap" }), [
        createElementVNode("view", null, "设置adjust-position"),
        createVNode(_component_switch, utsMapOf({
          style: normalizeStyle(utsMapOf({"margin-left":"10px"})),
          onChange: _ctx.changeAdjustPosition,
          checked: _ctx.adjust_position
        }), null, 8 /* PROPS */, ["style", "onChange", "checked"])
      ]),
      createElementVNode("view", utsMapOf({ class: "textarea-wrap" }), [
        createElementVNode("textarea", utsMapOf({
          class: "textarea-instance",
          "adjust-position": _ctx.adjust_position
        }), null, 8 /* PROPS */, ["adjust-position"])
      ]),
      isTrue(_ctx.isAutoTest)
        ? createElementVNode("view", utsMapOf({
            key: 0,
            class: "textarea-wrap"
          }), [
            createElementVNode("textarea", utsMapOf({
              id: "test-width",
              class: "test-width",
              value: "123456",
              placeholder: ""
            }))
          ])
        : createCommentVNode("v-if", true),
      createElementVNode("view", utsMapOf({ class: "title-wrap" }), [
        createElementVNode("view", null, "设置line-height")
      ]),
      createElementVNode("view", utsMapOf({ class: "textarea-wrap" }), [
        createElementVNode("textarea", utsMapOf({
          class: "textarea-instance",
          style: normalizeStyle(utsMapOf({"line-height":"1.2em"})),
          value: "设置line-height为1.2em"
        }), null, 4 /* STYLE */)
      ]),
      createElementVNode("view", utsMapOf({ class: "title-wrap" }), [
        createElementVNode("view", null, "设置max-height与auto-height")
      ]),
      createElementVNode("view", utsMapOf({ class: "textarea-wrap" }), [
        createElementVNode("textarea", utsMapOf({
          class: "textarea-instance",
          style: normalizeStyle(utsMapOf({"max-height":"50px"})),
          "auto-height": "true",
          value: _ctx.default_value
        }), null, 12 /* STYLE, PROPS */, ["value"])
      ]),
      createElementVNode("view", utsMapOf({ class: "title-wrap" }), [
        createElementVNode("view", null, "同时设置value与text-align")
      ]),
      createElementVNode("view", utsMapOf({ class: "textarea-wrap" }), [
        createElementVNode("textarea", utsMapOf({
          class: "textarea-instance",
          style: normalizeStyle(utsMapOf({"height":"50px","text-align":"right"})),
          value: "同时设置value与text-align"
        }), null, 4 /* STYLE */)
      ]),
      createElementVNode("view", utsMapOf({ class: "title-wrap" }), [
        createElementVNode("view", null, "scroll-view嵌套textarea滚动")
      ]),
      createElementVNode("scroll-view", utsMapOf({
        style: normalizeStyle(utsMapOf({"height":"150px"})),
        direction: "vertical"
      }), [
        createElementVNode("textarea", utsMapOf({
          class: "textarea-instance",
          "adjust-position": false,
          "auto-height": true,
          value: _ctx.value2
        }), null, 8 /* PROPS */, ["value"])
      ], 4 /* STYLE */)
    ], 4 /* STYLE */)
  ], 4 /* STYLE */)
}
const GenPagesComponentTextareaTextareaStyles = [utsMapOf([["main", padStyleMapOf(utsMapOf([["minHeight", 100], ["paddingTop", 5], ["paddingRight", 0], ["paddingBottom", 5], ["paddingLeft", 0], ["borderBottomWidth", 1], ["borderBottomStyle", "solid"], ["borderBottomColor", "rgba(0,0,0,0.06)"], ["flexDirection", "row"], ["justifyContent", "center"]]))], ["test-width", padStyleMapOf(utsMapOf([["width", 100], ["height", 100], ["backgroundColor", "#00FFFF"]]))], ["textarea-wrap", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["justifyContent", "center"]]))], ["title-wrap", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["alignItems", "center"], ["marginLeft", 10]]))], ["textarea-instance", padStyleMapOf(utsMapOf([["flex", 1], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#666666"], ["borderRightColor", "#666666"], ["borderBottomColor", "#666666"], ["borderLeftColor", "#666666"], ["marginTop", 10], ["marginRight", 10], ["marginBottom", 10], ["marginLeft", 10]]))], ["placeholder", padStyleMapOf(utsMapOf([["backgroundColor", "#FFFF00"]]))]])]

import _easycom_boolean_data from '@/components/boolean-data/boolean-data.vue'
import _easycom_enum_data from '@/components/enum-data/enum-data.vue'
