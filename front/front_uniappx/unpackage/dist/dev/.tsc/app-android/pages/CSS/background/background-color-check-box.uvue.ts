const __sfc__ = defineComponent({})
export default __sfc__
function GenPagesCSSBackgroundBackgroundColorCheckBoxRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_checkbox = resolveComponent("checkbox")

  return createElementVNode("view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("view", utsMapOf({ class: "item" }), [
      createElementVNode("text", null, "check-box设置background-color"),
      createElementVNode("view", utsMapOf({ class: "case" }), [
        createElementVNode("view", utsMapOf({ class: "warp" }), [
          createVNode(_component_checkbox, null, utsMapOf({
            default: withSlotCtx((): any[] => ["select"]),
            _: 1 /* STABLE */
          }))
        ])
      ])
    ])
  ], 4 /* STYLE */)
}
const GenPagesCSSBackgroundBackgroundColorCheckBoxStyles = [utsMapOf([["item", padStyleMapOf(utsMapOf([["marginTop", 15], ["marginRight", 15], ["marginBottom", 15], ["marginLeft", 15]]))], ["case", padStyleMapOf(utsMapOf([["marginTop", 10]]))], ["warp", padStyleMapOf(utsMapOf([["paddingTop", 16], ["paddingRight", 16], ["paddingBottom", 16], ["paddingLeft", 16], ["backgroundColor", "#000000"], ["boxShadow", "0px 0px 2px 0px rgba(0, 0, 0, 0.7)"]]))]])]
