
	const __sfc__ = defineComponent({
		data() {
			return {
				inputValue: '',
        isFocus: false,
				keyboardHeight: 0,
				keyboardStatus: '未显示'
			}
		},
		onLoad() {
			// 监听键盘高度变化
			uni.onKeyboardHeightChange(res => {
				this.keyboardHeight = res.height;
				this.keyboardStatus = res.height > 0 ? '显示中' : '已隐藏';
			});
		},
		onUnload() {
			// 页面卸载时移除监听
			uni.offKeyboardHeightChange();
		},
		methods: {
			hideKeyboard() {
				uni.hideKeyboard();
			}
		}
	})

export default __sfc__
function GenPagesAPIKeyboardKeyboardRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({ class: "container" }), [
    createElementVNode("view", utsMapOf({ class: "input-section" }), [
      createElementVNode("input", utsMapOf({
        id: "uni-input-box",
        class: "input-box",
        type: "text",
        value: _ctx.inputValue,
        placeholder: "点击输入框显示键盘",
        focus: _ctx.isFocus,
        "hold-keyboard": "true"
      }), null, 8 /* PROPS */, ["value", "focus"]),
      createElementVNode("button", utsMapOf({
        class: "btn",
        onClick: _ctx.hideKeyboard
      }), "隐藏键盘", 8 /* PROPS */, ["onClick"])
    ]),
    createElementVNode("view", utsMapOf({ class: "info-section" }), [
      createElementVNode("text", utsMapOf({ class: "info-text" }), "键盘高度: " + toDisplayString(_ctx.keyboardHeight) + "px", 1 /* TEXT */),
      createElementVNode("text", utsMapOf({ class: "info-text" }), "键盘状态: " + toDisplayString(_ctx.keyboardStatus), 1 /* TEXT */)
    ])
  ])
}
const GenPagesAPIKeyboardKeyboardStyles = [utsMapOf([["container", padStyleMapOf(utsMapOf([["paddingTop", 20], ["paddingRight", 20], ["paddingBottom", 20], ["paddingLeft", 20]]))], ["input-section", padStyleMapOf(utsMapOf([["marginBottom", 20]]))], ["input-box", padStyleMapOf(utsMapOf([["width", "100%"], ["height", 40], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#cccccc"], ["borderRightColor", "#cccccc"], ["borderBottomColor", "#cccccc"], ["borderLeftColor", "#cccccc"], ["borderTopLeftRadius", 4], ["borderTopRightRadius", 4], ["borderBottomRightRadius", 4], ["borderBottomLeftRadius", 4], ["paddingTop", 0], ["paddingRight", 10], ["paddingBottom", 0], ["paddingLeft", 10], ["marginBottom", 10]]))], ["btn", padStyleMapOf(utsMapOf([["backgroundColor", "#007AFF"], ["color", "#ffffff"]]))], ["info-section", padStyleMapOf(utsMapOf([["marginTop", 20]]))], ["info-text", padStyleMapOf(utsMapOf([["width", "100%"], ["marginBottom", 10], ["fontSize", 16], ["color", "#333333"]]))]])]
