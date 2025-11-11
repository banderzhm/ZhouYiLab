const __sfc__ = defineComponent({})
export default __sfc__
function GenPagesCSSLayoutMinWidthRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex-grow":"1"}))
  }), [
    createElementVNode("view", null, [
      createElementVNode("text", null, "min-width: 250px"),
      createElementVNode("view", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({"min-width":"250px"}))
      }), [
        createElementVNode("text", null, "width: 50px")
      ], 4 /* STYLE */)
    ])
  ], 4 /* STYLE */)
}
const GenPagesCSSLayoutMinWidthStyles = [utsMapOf([["common", padStyleMapOf(utsMapOf([["width", 50], ["height", 250], ["backgroundColor", "#FF0000"], ["justifyContent", "center"], ["alignItems", "center"]]))]])]
