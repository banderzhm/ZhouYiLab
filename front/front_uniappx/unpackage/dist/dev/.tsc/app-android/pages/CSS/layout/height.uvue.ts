const __sfc__ = defineComponent({})
export default __sfc__
function GenPagesCSSLayoutHeightRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex-grow":"1"}))
  }), [
    createElementVNode("view", null, [
      createElementVNode("text", null, "height: 250px"),
      createElementVNode("view", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({"height":"250px"}))
      }), null, 4 /* STYLE */)
    ]),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"height":"250px"}))
    }), [
      createElementVNode("text", null, "height: 50%"),
      createElementVNode("view", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({"height":"50%"}))
      }), null, 4 /* STYLE */)
    ], 4 /* STYLE */)
  ], 4 /* STYLE */)
}
const GenPagesCSSLayoutHeightStyles = [utsMapOf([["common", padStyleMapOf(utsMapOf([["width", 250], ["backgroundColor", "#FF0000"]]))]])]
