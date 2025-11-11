const __sfc__ = defineComponent({})
export default __sfc__
function GenPagesCSSBorderBorderLeftRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex-grow":"1"}))
  }), [
    createElementVNode("view", null, [
      createElementVNode("text", null, "border-left: 5px solid blue"),
      createElementVNode("view", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({"border-left":"5px solid blue"}))
      }), null, 4 /* STYLE */)
    ])
  ], 4 /* STYLE */)
}
const GenPagesCSSBorderBorderLeftStyles = [utsMapOf([["common", padStyleMapOf(utsMapOf([["width", 250], ["height", 250], ["backgroundColor", "#808080"]]))]])]
