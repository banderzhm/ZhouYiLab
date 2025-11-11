const __sfc__ = defineComponent({})
export default __sfc__
function GenPagesCSSLayoutMaxHeightRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex-grow":"1"}))
  }), [
    createElementVNode("view", null, [
      createElementVNode("text", null, "max-height: 250px"),
      createElementVNode("view", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({"max-height":"250px"}))
      }), [
        createElementVNode("text", null, "height: 500px")
      ], 4 /* STYLE */)
    ])
  ], 4 /* STYLE */)
}
const GenPagesCSSLayoutMaxHeightStyles = [utsMapOf([["common", padStyleMapOf(utsMapOf([["width", 250], ["height", 500], ["backgroundColor", "#FF0000"], ["justifyContent", "center"], ["alignItems", "center"]]))]])]
