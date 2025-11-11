
	const __sfc__ = defineComponent({
		data() {
			return {
        safeareaInsetTop: 0,
        safeareaInsetLeft: 0,
        safeareaInsetRight: 0,
        safeareaInsetBottom: 0,
			}
		},
		methods: {

		},
    onReady() {
      this.safeareaInsetTop=this.$page.safeAreaInsets.top;
      this.safeareaInsetLeft=this.$page.safeAreaInsets.left;
      this.safeareaInsetRight=this.$page.safeAreaInsets.right;
      this.safeareaInsetBottom=this.$page.safeAreaInsets.bottom;
    },
    onResize() {
      this.safeareaInsetTop=this.$page.safeAreaInsets.top;
      this.safeareaInsetLeft=this.$page.safeAreaInsets.left;
      this.safeareaInsetRight=this.$page.safeAreaInsets.right;
      this.safeareaInsetBottom=this.$page.safeAreaInsets.bottom;
    }
	})

export default __sfc__
function GenPagesCSSFunctionFunctionRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({ class: "padding-safe-area-inset" }), [
    createElementVNode("view", utsMapOf({ class: "text-background" }), "我在状态栏下边"),
    createElementVNode("view", utsMapOf({ class: "content" }), [
      createElementVNode("text", null, "此页面使用env()函数适配安全区域，仅在app平台生效。"),
      createElementVNode("text", null, "safe-area-inset-top: " + toDisplayString(_ctx.safeareaInsetTop) + "px", 1 /* TEXT */),
      createElementVNode("text", null, "safe-area-inset-left: " + toDisplayString(_ctx.safeareaInsetLeft) + "px", 1 /* TEXT */),
      createElementVNode("text", null, "safe-area-inset-right: " + toDisplayString(_ctx.safeareaInsetRight) + "px", 1 /* TEXT */),
      createElementVNode("text", null, "safe-area-inset-bottom: " + toDisplayString(_ctx.safeareaInsetBottom) + "px", 1 /* TEXT */)
    ]),
    createElementVNode("view", utsMapOf({ class: "text-background" }), "我在导航栏上边")
  ])
}
const GenPagesCSSFunctionFunctionStyles = [utsMapOf([["text-background", padStyleMapOf(utsMapOf([["backgroundColor", "#FF0000"]]))], ["padding-safe-area-inset", padStyleMapOf(utsMapOf([["flex", 1], ["justifyContent", "space-between"], ["paddingTop", "env(safe-area-inset-top,0px)"], ["paddingLeft", "env(safe-area-inset-left,0px)"], ["paddingRight", "env(safe-area-inset-right,0px)"], ["paddingBottom", "env(safe-area-inset-bottom,0px)"]]))], ["content", padStyleMapOf(utsMapOf([["paddingTop", 20], ["paddingRight", 20], ["paddingBottom", 20], ["paddingLeft", 20]]))]])]
