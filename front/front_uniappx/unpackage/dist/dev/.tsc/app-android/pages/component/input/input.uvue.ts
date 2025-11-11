
  const __sfc__ = defineComponent({
    data() {
      return {
        title: 'input',
        showClearIcon: false,
        inputClearValue: '',
        showPassword: true,
        cursor: -1,
        cursorInputFocus: false,
        cursor_color: "#3393E2",
        cursorColorInputFocus: false,
        selectionStart: -1,
        selectionEnd: -1,
        selectionInputFocus: false,
        inputEventDetail: '',
        focusAndBlurEventDetail: '',
        confirmEventDetail: '',
        changeEventDetail: '',
        keyboardHeightChangeEventDetail: '',
        focus: true,
        inputPassword: true,
        inputTypeTel: "tel",
        inputPlaceHolderStyle: "color:red",
        placeholderStyle: "font-family: AlimamaDaoLiTiOTF;font-weight: bold;text-align: right",
        inputPlaceHolderClass: "uni-input-placeholder-class" as string.ClassString,
        inputMaxLengthValue: "",
        onMaxLengthInputValue: "",
        inputMaxLengthFocus: false,
        inputPasswordValue: "cipher",
        inputFocusKeyBoardChangeValue: true,
        holdKeyboard: false,
        keyboardHeight: 0,
        focusedForKeyboardHeightChangeTest: false,
        demoValue: '123',
        demoValue2: '123',
        adjustPosition: false,
        syncDisplayValue: '',
        triggerFocus: false,
        triggerBlur: false,
      }
    },
    methods: {
      inputFocusKeyBoardChange(e : UniInputKeyboardHeightChangeEvent) {
        this.inputFocusKeyBoardChangeValue = e.detail.height > 50
      },
      onMaxLengthInput(event : UniInputEvent) {
        this.onMaxLengthInputValue = event.detail.value
      },
      setCursor: function (cursor : number) {
        // (this.$refs['input'] as UniInputElement).focus();
        this.cursorInputFocus = true
        this.cursor = cursor;
      },
      onCursorBlurChange() {
        this.cursor = 0
        this.cursorInputFocus = false
      },
      setSelection: function (selectionStart : number, selectionEnd : number) {
        // (this.$refs['input2'] as UniInputElement).focus();
        this.selectionInputFocus = true
        this.selectionStart = selectionStart;
        this.selectionEnd = selectionEnd;
      },
      onSelectionBlurChange() {
        this.selectionEnd = 0;
        this.selectionInputFocus = false
      },
      clearInput: function (event : UniInputEvent) {
        this.inputClearValue = event.detail.value
        if (event.detail.value.length > 0) {
          this.showClearIcon = true
        } else {
          this.showClearIcon = false
        }
      },
      clearIcon: function () {
        this.inputClearValue = ''
        this.showClearIcon = false
      },
      changePassword: function () {
        this.showPassword = !this.showPassword
      },
      onInput: function (event : UniInputEvent) {
        console.log("键盘输入", JSON.stringify(event.detail), " at pages/component/input/input.uvue:428");
        this.inputEventDetail = JSON.stringify(event.detail)
      },
      onFocus: function (event : UniInputFocusEvent) {
        this.triggerFocus = true
        console.log("输入框聚焦", JSON.stringify(event.detail), " at pages/component/input/input.uvue:433");
        this.focusAndBlurEventDetail = JSON.stringify(event.detail);
      },
      onBlur: function (event : UniInputBlurEvent) {
        this.triggerBlur = true
        console.log("输入框失去焦点", JSON.stringify(event.detail), " at pages/component/input/input.uvue:438");
        this.focusAndBlurEventDetail = JSON.stringify(event.detail);
      },
      onConfirm: function (event : UniInputConfirmEvent) {
        console.log("点击完成按钮", JSON.stringify(event.detail), " at pages/component/input/input.uvue:442");
        this.confirmEventDetail = JSON.stringify(event.detail);
      },
      onChange: function (event : UniInputChangeEvent) {
        console.log("输入内容变化", JSON.stringify(event.detail), " at pages/component/input/input.uvue:446");
        this.changeEventDetail = JSON.stringify(event.detail);
      },
      onKeyboardHeightChange: function (event : UniInputKeyboardHeightChangeEvent) {
        console.log("键盘高度发生变化", JSON.stringify(event.detail), " at pages/component/input/input.uvue:450");
        this.keyboardHeightChangeEventDetail = JSON.stringify(event.detail);
        this.keyboardHeight = event.detail.height;
      },
      test_check_input_value() : number {
        return this.onMaxLengthInputValue.length
      },
      changeCursorColor(event : UniSwitchChangeEvent) {
        const checked = event.detail.value;
        if (checked) {
          this.cursor_color = "#FF0000"
        } else {
          this.cursor_color = "#3393E2"
        }
        // const input = uni.getElementById<UniInputElement>("uni-input-cursor-color")
        // input?.focus()
        this.cursorColorInputFocus = true
      },
      cursorColorInputBlur(event : UniInputBlurEvent) {
        this.cursorColorInputFocus = false
      },
      changeHoldKeyboard(event : UniSwitchChangeEvent) {
        const checked = event.detail.value;
        this.holdKeyboard = checked
      },
      changeAdjustPosition(event : UniSwitchChangeEvent) {
        const checked = event.detail.value;
        this.adjustPosition = checked
      },
      triggerFocusOrBlur(){
        const input = uni.getElementById("trigger-focus-blur-input")!
        if(this.triggerFocus){
          this.triggerFocus = false
          input.blur()
        } else {
          this.triggerBlur = false
          input.focus()
        }
      }
    }
  })

export default __sfc__
function GenPagesComponentInputInputRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)
const _component_switch = resolveComponent("switch")

  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
    createElementVNode("view", utsMapOf({
      class: "uni-common-mt uni-padding-wrap",
      style: normalizeStyle(utsMapOf({"padding-bottom":"30px"}))
    }), [
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "设置输入框的初始内容")
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "uni-input-default",
            class: "uni-input",
            value: "hello uni-app x"
          }))
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "type取值（不同输入法表现可能不一致）")
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "uni-input-type-text",
            class: "uni-input",
            type: "text",
            placeholder: "文本输入键盘"
          }))
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "uni-input-type-number",
            class: "uni-input",
            type: "number",
            placeholder: "数字输入键盘"
          }))
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "uni-input-type-digit",
            class: "uni-input",
            type: "digit",
            placeholder: "带小数点的数字输入键盘"
          }))
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "uni-input-type-tel",
            class: "uni-input",
            type: _ctx.inputTypeTel,
            placeholder: "电话输入键盘"
          }), null, 8 /* PROPS */, ["type"])
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "uni-input-type-tel",
            class: "uni-input",
            type: "search",
            placeholder: "搜索输入键盘"
          }))
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "uni-input-type-tel",
            class: "uni-input",
            type: "email",
            placeholder: "邮箱输入键盘"
          }))
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "uni-input-type-tel",
            class: "uni-input",
            type: "url",
            placeholder: "url输入键盘"
          }))
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "uni-input-type-tel",
            class: "uni-input",
            type: "none",
            placeholder: "聚焦但不弹出键盘"
          }))
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "密码输入框")
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "uni-input-password",
            class: "uni-input",
            password: _ctx.inputPassword,
            type: "text",
            value: _ctx.inputPasswordValue
          }), null, 8 /* PROPS */, ["password", "value"])
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "无value设置的密码输入框")
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            class: "uni-input",
            password: true,
            type: "text"
          }))
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "占位符样式")
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "uni-input-placeholder1",
            class: "uni-input",
            "placeholder-style": _ctx.inputPlaceHolderStyle,
            placeholder: "占位符文字颜色为红色"
          }), null, 8 /* PROPS */, ["placeholder-style"])
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "uni-input-placeholder2",
            class: "uni-input",
            "placeholder-class": _ctx.inputPlaceHolderClass,
            placeholder: "占位符字体大小为10px"
          }), null, 8 /* PROPS */, ["placeholder-class"])
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "uni-input-placeholder3",
            class: "uni-input",
            "placeholder-class": "",
            placeholder: "placeholder-class 传入空字符串"
          }))
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            class: "uni-input",
            "placeholder-style": _ctx.inputPlaceHolderStyle,
            value: "不设置placeholder只设置placeholder-style"
          }), null, 8 /* PROPS */, ["placeholder-style"])
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "uni-input-placeholder2",
            style: normalizeStyle(utsMapOf({"font-family":"AlimamaDaoLiTiOTF","font-weight":"bold","text-align":"right"})),
            class: "uni-input",
            "placeholder-style": _ctx.placeholderStyle,
            placeholder: "占位符以及内容右对齐"
          }), null, 12 /* STYLE, PROPS */, ["placeholder-style"])
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "设置禁用输入框")
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "uni-input-disable",
            class: "uni-input",
            disabled: true
          }))
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "设置最大输入长度")
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "uni-input-maxlength",
            class: "uni-input",
            maxlength: 10,
            placeholder: "最大输入长度为10",
            value: _ctx.inputMaxLengthValue,
            onInput: _ctx.onMaxLengthInput,
            focus: _ctx.inputMaxLengthFocus
          }), null, 40 /* PROPS, NEED_HYDRATION */, ["value", "onInput", "focus"])
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "设置光标与键盘的距离")
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            class: "uni-input",
            "cursor-spacing": 1000,
            placeholder: "光标与键盘的距离为1000px"
          }))
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "自动获取焦点")
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "uni-input-focus",
            class: "uni-input",
            focus: _ctx.focus,
            onKeyboardheightchange: _ctx.inputFocusKeyBoardChange
          }), null, 40 /* PROPS, NEED_HYDRATION */, ["focus", "onKeyboardheightchange"])
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "confirm-type取值（不同输入法表现可能不一致）")
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "uni-input-confirm-send",
            class: "uni-input",
            confirmType: "send",
            placeholder: "键盘右下角按钮显示为发送"
          }))
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "uni-input-confirm-search",
            class: "uni-input",
            confirmType: "search",
            placeholder: "键盘右下角按钮显示为搜索"
          }))
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "uni-input-confirm-next",
            class: "uni-input",
            confirmType: "next",
            placeholder: "键盘右下角按钮显示为下一个"
          }))
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "uni-input-confirm-go",
            class: "uni-input",
            confirmType: "go",
            placeholder: "键盘右下角按钮显示为前往"
          }))
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "uni-input-confirm-done",
            class: "uni-input",
            confirmType: "done",
            placeholder: "键盘右下角按钮显示为完成"
          }))
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "点击键盘右下角按钮时保持键盘不收起")
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            class: "uni-input",
            "confirm-hold": true
          }))
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({
          class: "uni-title",
          onClick: () => {_ctx.setCursor(4)}
        }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "设置输入框聚焦时光标的位置（点这里）")
        ], 8 /* PROPS */, ["onClick"]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            ref: "input",
            class: "uni-input",
            value: "0123456789",
            focus: _ctx.cursorInputFocus,
            cursor: _ctx.cursor,
            onBlur: _ctx.onCursorBlurChange
          }), null, 40 /* PROPS, NEED_HYDRATION */, ["focus", "cursor", "onBlur"])
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({
          class: "uni-title",
          onClick: () => {_ctx.setSelection(0, 4)}
        }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "设置输入框聚焦时光标的起始位置和结束位置（点这里）")
        ], 8 /* PROPS */, ["onClick"]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            ref: "input2",
            class: "uni-input",
            value: "0123456789",
            "selection-start": _ctx.selectionStart,
            "selection-end": _ctx.selectionEnd,
            focus: _ctx.selectionInputFocus,
            onBlur: _ctx.onSelectionBlurChange
          }), null, 40 /* PROPS, NEED_HYDRATION */, ["selection-start", "selection-end", "focus", "onBlur"])
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({
          class: "uni-title",
          style: normalizeStyle(utsMapOf({"flex-direction":"row","align-items":"center"}))
        }), [
          createElementVNode("text", utsMapOf({
            class: "uni-title-text",
            style: normalizeStyle(utsMapOf({"flex-shrink":"1"}))
          }), "设置光标颜色为红色（Android的微信小程序非skyline下仅支持黑和绿）", 4 /* STYLE */),
          createVNode(_component_switch, utsMapOf({
            style: normalizeStyle(utsMapOf({"margin-left":"10px"})),
            onChange: _ctx.changeCursorColor,
            checked: _ctx.cursor_color == '#FF0000'
          }), null, 8 /* PROPS */, ["style", "onChange", "checked"])
        ], 4 /* STYLE */),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "uni-input-cursor-color",
            class: "uni-input",
            "cursor-color": _ctx.cursor_color,
            focus: _ctx.cursorColorInputFocus,
            onBlur: _ctx.cursorColorInputBlur,
            value: "光标颜色"
          }), null, 40 /* PROPS, NEED_HYDRATION */, ["cursor-color", "focus", "onBlur"])
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "键盘弹起时，自动上推页面")
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            class: "uni-input",
            "adjust-position": true
          }))
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({
          class: "uni-title",
          style: normalizeStyle(utsMapOf({"flex-direction":"row","align-items":"center"}))
        }), [
          createElementVNode("text", utsMapOf({
            class: "uni-title-text",
            style: normalizeStyle(utsMapOf({"flex-shrink":"1"}))
          }), "设置hold-keyboard", 4 /* STYLE */),
          createVNode(_component_switch, utsMapOf({
            style: normalizeStyle(utsMapOf({"margin-left":"10px"})),
            onChange: _ctx.changeHoldKeyboard,
            checked: _ctx.holdKeyboard
          }), null, 8 /* PROPS */, ["style", "onChange", "checked"])
        ], 4 /* STYLE */),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            class: "uni-input",
            "hold-keyboard": _ctx.holdKeyboard,
            value: "hold keyboard "
          }), null, 8 /* PROPS */, ["hold-keyboard"])
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "input事件"),
          isTrue(_ctx.inputEventDetail)
            ? createElementVNode("text", utsMapOf({
                key: 0,
                class: "uni-subtitle-text"
              }), toDisplayString(_ctx.inputEventDetail), 1 /* TEXT */)
            : createCommentVNode("v-if", true)
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            class: "uni-input",
            onInput: _ctx.onInput
          }), null, 40 /* PROPS, NEED_HYDRATION */, ["onInput"])
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({
            class: "uni-title-text",
            onClick: _ctx.triggerFocusOrBlur
          }), "点击触发focus事件或blur事件", 8 /* PROPS */, ["onClick"]),
          isTrue(_ctx.focusAndBlurEventDetail)
            ? createElementVNode("text", utsMapOf({
                key: 0,
                class: "uni-subtitle-text"
              }), toDisplayString(_ctx.focusAndBlurEventDetail), 1 /* TEXT */)
            : createCommentVNode("v-if", true)
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "trigger-focus-blur-input",
            class: "uni-input",
            onFocus: _ctx.onFocus,
            onBlur: _ctx.onBlur
          }), null, 40 /* PROPS, NEED_HYDRATION */, ["onFocus", "onBlur"])
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "confirm事件"),
          isTrue(_ctx.confirmEventDetail)
            ? createElementVNode("text", utsMapOf({
                key: 0,
                class: "uni-subtitle-text"
              }), toDisplayString(_ctx.confirmEventDetail), 1 /* TEXT */)
            : createCommentVNode("v-if", true)
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            class: "uni-input",
            onConfirm: _ctx.onConfirm
          }), null, 40 /* PROPS, NEED_HYDRATION */, ["onConfirm"])
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "change事件"),
          isTrue(_ctx.changeEventDetail)
            ? createElementVNode("text", utsMapOf({
                key: 0,
                class: "uni-subtitle-text"
              }), toDisplayString(_ctx.changeEventDetail), 1 /* TEXT */)
            : createCommentVNode("v-if", true)
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            class: "uni-input",
            onChange: _ctx.onChange
          }), null, 40 /* PROPS, NEED_HYDRATION */, ["onChange"])
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "带清除按钮的输入框")
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            class: "uni-input",
            placeholder: "带清除按钮的输入框",
            value: _ctx.inputClearValue,
            onInput: _ctx.clearInput
          }), null, 40 /* PROPS, NEED_HYDRATION */, ["value", "onInput"]),
          isTrue(_ctx.showClearIcon)
            ? createElementVNode("image", utsMapOf({
                key: 0,
                class: "uni-icon",
                src: "/static/icons/clear.png",
                onClick: _ctx.clearIcon
              }), null, 8 /* PROPS */, ["onClick"])
            : createCommentVNode("v-if", true)
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "可查看密码的输入框")
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            class: "uni-input",
            placeholder: "请输入密码",
            password: _ctx.showPassword
          }), null, 8 /* PROPS */, ["password"]),
          createElementVNode("image", utsMapOf({
            class: "uni-icon",
            src: 
              !_ctx.showPassword
                ? '/static/icons/eye-active.png'
                : '/static/icons/eye.png'
            ,
            onClick: _ctx.changePassword
          }), null, 8 /* PROPS */, ["src", "onClick"])
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "同时存在 v-model 和 value")
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "both-model-value",
            class: "uni-input",
            modelValue: _ctx.demoValue,
            onInput: ($event: InputEvent) => {(_ctx.demoValue) = $event.detail.value},
            value: "456"
          }), null, 40 /* PROPS, NEED_HYDRATION */, ["modelValue", "onInput"])
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "同时存在 v-model 和 change事件")
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            id: "both-model-value",
            class: "uni-input",
            modelValue: _ctx.demoValue2,
            onInput: ($event: InputEvent) => {(_ctx.demoValue2) = $event.detail.value},
            value: "123",
            onChange: _ctx.onChange
          }), null, 40 /* PROPS, NEED_HYDRATION */, ["modelValue", "onInput", "onChange"])
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({
          class: "uni-title",
          style: normalizeStyle(utsMapOf({"flex-direction":"row","align-items":"center"}))
        }), [
          createElementVNode("text", utsMapOf({
            class: "uni-title-text",
            style: normalizeStyle(utsMapOf({"flex-shrink":"1"}))
          }), "设置adjust-position", 4 /* STYLE */),
          createVNode(_component_switch, utsMapOf({
            style: normalizeStyle(utsMapOf({"margin-left":"10px"})),
            onChange: _ctx.changeAdjustPosition,
            checked: _ctx.adjustPosition
          }), null, 8 /* PROPS */, ["style", "onChange", "checked"])
        ], 4 /* STYLE */),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            class: "uni-input",
            "adjust-position": _ctx.adjustPosition
          }), null, 8 /* PROPS */, ["adjust-position"])
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "输入内容的同步更新UI")
        ]),
        createElementVNode("text", null, toDisplayString(_ctx.syncDisplayValue), 1 /* TEXT */),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            class: "uni-input",
            modelValue: _ctx.syncDisplayValue,
            onInput: ($event: InputEvent) => {(_ctx.syncDisplayValue) = $event.detail.value}
          }), null, 40 /* PROPS, NEED_HYDRATION */, ["modelValue", "onInput"])
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "maxlength初始化生效")
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            class: "uni-input",
            maxlength: "5",
            value: "123456789"
          }))
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "keyboardheightchange事件"),
          isTrue(_ctx.keyboardHeightChangeEventDetail)
            ? createElementVNode("text", utsMapOf({
                key: 0,
                class: "uni-subtitle-text"
              }), toDisplayString(_ctx.keyboardHeightChangeEventDetail), 1 /* TEXT */)
            : createCommentVNode("v-if", true)
        ]),
        createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
          createElementVNode("input", utsMapOf({
            class: "uni-input",
            onKeyboardheightchange: _ctx.onKeyboardHeightChange,
            focus: _ctx.focusedForKeyboardHeightChangeTest
          }), null, 40 /* PROPS, NEED_HYDRATION */, ["onKeyboardheightchange", "focus"])
        ])
      ])
    ], 4 /* STYLE */)
  ], 4 /* STYLE */)
}
const GenPagesComponentInputInputStyles = [utsMapOf([["input-wrapper", padStyleMapOf(utsMapOf([["display", "flex"], ["paddingTop", 8], ["paddingRight", 13], ["paddingBottom", 8], ["paddingLeft", 13], ["marginTop", 5], ["marginRight", 0], ["marginBottom", 5], ["marginLeft", 0], ["flexDirection", "row"], ["flexWrap", "nowrap"], ["backgroundColor", "#ffffff"]]))], ["uni-input", padStyleMapOf(utsMapOf([["height", 28], ["fontSize", 15], ["paddingTop", 0], ["paddingRight", 0], ["paddingBottom", 0], ["paddingLeft", 0], ["flex", 1], ["backgroundColor", "#ffffff"]]))], ["uni-icon", padStyleMapOf(utsMapOf([["width", 24], ["height", 24]]))], ["uni-input-placeholder-class", padStyleMapOf(utsMapOf([["fontSize", 10]]))], ["@FONT-FACE", utsMapOf([["0", utsMapOf([["fontFamily", "AlimamaDaoLiTiOTF"], ["src", "url('/static/font/AlimamaDaoLiTi.otf')"]])]])]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
