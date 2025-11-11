
  const __sfc__ = defineComponent({
    data() {
      return {
        isTranstionWidthOrHeight: false,
        widthOrHeight: null as UniElement | null,
        widthProgress: null as UniElement | null,
        progressWidth: 200,
        pointerEvents1: 'auto',
        pointerEvents2: 'auto',
      }
    },
    onReady() {
      this.widthOrHeight = uni.getElementById("widthOrHeight")
      this.widthProgress = uni.getElementById("widthProgress")
    },
    methods: {
      changeWidthOrHeight() {
        this.widthOrHeight?.style?.setProperty("width", this.isTranstionWidthOrHeight
          ? '200px'
          : '300px')
        this.isTranstionWidthOrHeight = !this.isTranstionWidthOrHeight
      },
      changeWidthProgress() {
        this.progressWidth += 20
        this.widthProgress?.style?.setProperty("width", this.progressWidth + 'px')
      },
      onChange1(e : UniSwitchChangeEvent) {
        this.pointerEvents1 = e.detail.value ? 'auto' : 'none'
      },
      onChange2(e : UniSwitchChangeEvent) {
        this.pointerEvents2 = e.detail.value ? 'auto' : 'none'
      }
    }
  })

export default __sfc__
function GenPagesCSSPointerEventsPointerEventsRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_switch = resolveComponent("switch")

  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("view", null, [
      createElementVNode("view", utsMapOf({ class: "container1" }), [
        createElementVNode("text", null, "控制父视图pointer-events打开时可以点击"),
        createVNode(_component_switch, utsMapOf({
          checked: true,
          onChange: _ctx.onChange1
        }), null, 8 /* PROPS */, ["onChange"])
      ]),
      createElementVNode("view", utsMapOf({
        class: "container",
        style: normalizeStyle(utsMapOf({ 'pointer-events': _ctx.pointerEvents1 }))
      }), [
        createElementVNode("text", utsMapOf({ class: "text" }), "点击修改宽度"),
        createElementVNode("view", utsMapOf({
          class: "base-style transition-width",
          id: "widthOrHeight",
          onClick: _ctx.changeWidthOrHeight
        }), null, 8 /* PROPS */, ["onClick"])
      ], 4 /* STYLE */),
      createElementVNode("view", utsMapOf({ class: "container1" }), [
        createElementVNode("text", null, "控制遮罩层pointer-events关闭时可以点击"),
        createVNode(_component_switch, utsMapOf({
          checked: true,
          onChange: _ctx.onChange2
        }), null, 8 /* PROPS */, ["onChange"])
      ]),
      createElementVNode("view", utsMapOf({ class: "container" }), [
        createElementVNode("text", utsMapOf({ class: "text" }), "点击修改宽度(递增)"),
        createElementVNode("view", utsMapOf({
          class: "width-progress transition-width",
          id: "widthProgress",
          onClick: _ctx.changeWidthProgress
        }), null, 8 /* PROPS */, ["onClick"]),
        createElementVNode("view", utsMapOf({
          class: "mask",
          style: normalizeStyle(utsMapOf({ 'pointer-events': _ctx.pointerEvents2 }))
        }), null, 4 /* STYLE */)
      ])
    ])
  ], 4 /* STYLE */)
}
const GenPagesCSSPointerEventsPointerEventsStyles = [utsMapOf([["container1", padStyleMapOf(utsMapOf([["marginTop", 7], ["marginRight", 0], ["marginBottom", 7], ["marginLeft", 7], ["display", "flex"], ["flexDirection", "row"], ["justifyContent", "space-between"], ["alignItems", "center"]]))], ["container", padStyleMapOf(utsMapOf([["marginTop", 7], ["marginRight", 7], ["marginBottom", 7], ["marginLeft", 7], ["backgroundColor", "#FFFFFF"]]))], ["text", padStyleMapOf(utsMapOf([["marginTop", 10], ["marginBottom", 16]]))], ["base-style", padStyleMapOf(utsMapOf([["width", 200], ["height", 200], ["backgroundColor", "#A52A2A"]]))], ["width-progress", padStyleMapOf(utsMapOf([["width", 200], ["height", 200], ["backgroundColor", "#A52A2A"]]))], ["transition-width", padStyleMapOf(utsMapOf([["transitionProperty", "width"], ["transitionDuration", "1s"]]))], ["mask", padStyleMapOf(utsMapOf([["position", "absolute"], ["bottom", 0], ["left", 0], ["width", "100%"], ["height", 200], ["backgroundColor", "rgba(0,0,0,0.5)"]]))], ["@TRANSITION", utsMapOf([["transition-width", utsMapOf([["property", "width"], ["duration", "1s"]])]])]])]
