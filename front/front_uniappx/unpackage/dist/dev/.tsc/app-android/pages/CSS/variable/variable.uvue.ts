
  const __sfc__ = defineComponent({
    data() {
      return {
        statusBarHeight: 0,
        statusBarHeight2: 0,
      }
    },
    onReady() {
      this.statusBarHeight = uni.getWindowInfo().statusBarHeight

      this.statusBarHeight2 = this.$page.statusBarHeight

    }
  })

export default __sfc__
function GenPagesCSSVariableVariableRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({ class: "page" }), [
    createElementVNode("view", utsMapOf({ class: "status-bar-height" }), [
      createElementVNode("text", null, "通过var(--status-bar-height)获取状态栏高度")
    ]),
    createElementVNode("view", utsMapOf({
      class: "status-bar-window",
      style: normalizeStyle(utsMapOf({ height: _ctx.statusBarHeight + 'px' }))
    }), [
      createElementVNode("text", null, "通过uni.getWindowInfo获取状态栏高度")
    ], 4 /* STYLE */),
    createElementVNode("view", utsMapOf({
      class: "status-bar-unipage",
      style: normalizeStyle(utsMapOf({ height: _ctx.statusBarHeight2 + 'px' }))
    }), [
      createElementVNode("text", null, "通过this.$page.statusBarHeight获取状态栏高度")
    ], 4 /* STYLE */),
    createElementVNode("view", utsMapOf({ class: "window-top" }), [
      createElementVNode("text", null, "window top")
    ]),
    createElementVNode("view", utsMapOf({ class: "window-bottom" }), [
      createElementVNode("text", null, "window bottom")
    ]),
    createElementVNode("view", utsMapOf({ class: "uni-safe-area-inset-top" }), [
      createElementVNode("text", null, "height:var(--uni-safe-area-inset-top)")
    ]),
    createElementVNode("view", utsMapOf({ class: "uni-safe-area-inset-left" }), [
      createElementVNode("text", null, "height:var(--uni-safe-area-inset-left)")
    ]),
    createElementVNode("view", utsMapOf({ class: "uni-safe-area-inset-right" }), [
      createElementVNode("text", null, "height:var(--uni-safe-area-inset-right)")
    ]),
    createElementVNode("view", utsMapOf({ class: "uni-safe-area-inset-bottom" }), [
      createElementVNode("text", null, "height:var(--uni-safe-area-inset-bottom)")
    ]),
    createElementVNode("view", utsMapOf({ class: "uni-fixed-bottom" }), [
      createElementVNode("text", null, "此区域应显示在安全区域内")
    ])
  ])
}
const GenPagesCSSVariableVariableStyles = [utsMapOf([["page", padStyleMapOf(utsMapOf([["flex", 1]]))], ["status-bar-height", padStyleMapOf(utsMapOf([["height", CSS_VAR_STATUS_BAR_HEIGHT], ["alignItems", "center"], ["justifyContent", "center"], ["backgroundColor", "#FF0000"]]))], ["status-bar-window", padStyleMapOf(utsMapOf([["backgroundColor", "#FFFF00"], ["alignItems", "center"], ["justifyContent", "center"]]))], ["status-bar-unipage", padStyleMapOf(utsMapOf([["alignItems", "center"], ["justifyContent", "center"], ["backgroundColor", "#ADFF2F"]]))], ["window-top", padStyleMapOf(utsMapOf([["height", CSS_VAR_WINDOW_TOP], ["alignItems", "center"], ["backgroundColor", "#008000"], ["marginTop", 2], ["marginRight", 0], ["marginBottom", 2], ["marginLeft", 0]]))], ["window-bottom", padStyleMapOf(utsMapOf([["height", CSS_VAR_WINDOW_BOTTOM], ["alignItems", "center"], ["backgroundColor", "#0000FF"], ["marginTop", 2], ["marginRight", 0], ["marginBottom", 2], ["marginLeft", 0]]))], ["uni-safe-area-inset-top", padStyleMapOf(utsMapOf([["height", "var(--uni-safe-area-inset-top)"], ["alignItems", "center"], ["backgroundColor", "#FFFF00"], ["marginTop", 2], ["marginRight", 0], ["marginBottom", 2], ["marginLeft", 0]]))], ["uni-safe-area-inset-left", padStyleMapOf(utsMapOf([["height", "var(--uni-safe-area-inset-left)"], ["alignItems", "center"], ["backgroundColor", "#ADFF2F"], ["marginTop", 2], ["marginRight", 0], ["marginBottom", 2], ["marginLeft", 0]]))], ["uni-safe-area-inset-right", padStyleMapOf(utsMapOf([["height", "var(--uni-safe-area-inset-right, 60px)"], ["alignItems", "center"], ["backgroundColor", "#8B4513"], ["marginTop", 2], ["marginRight", 0], ["marginBottom", 2], ["marginLeft", 0]]))], ["uni-safe-area-inset-bottom", padStyleMapOf(utsMapOf([["height", "var(--uni-safe-area-inset-bottom)"], ["alignItems", "center"], ["backgroundColor", "#FA8072"], ["marginTop", 2], ["marginRight", 0], ["marginBottom", 2], ["marginLeft", 0]]))], ["uni-fixed-bottom", padStyleMapOf(utsMapOf([["position", "fixed"], ["left", "var(--uni-safe-area-inset-left)"], ["right", "var(--uni-safe-area-inset-right)"], ["bottom", "var(--uni-safe-area-inset-bottom)"], ["alignItems", "center"], ["backgroundColor", "#8A2BE2"]]))]])]
