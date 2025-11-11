const __sfc__ = defineComponent({})
export default __sfc__
function GenPagesCSSTextTextDecorationLineRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex-grow":"1"}))
  }), [
    createElementVNode("view", utsMapOf({ class: "content" }), [
      createElementVNode("text", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({"text-decoration-line":"underline"}))
      }), "text-decoration-line: underline", 4 /* STYLE */),
      createElementVNode("text", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({"text-decoration-line":"line-through"}))
      }), "text-decoration-line: line-through", 4 /* STYLE */)
    ]),
    createElementVNode("view", utsMapOf({ class: "content" }), [
      createElementVNode("text", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({"text-decoration-line":"underline","color":"blue"}))
      }), "text-decoration-line: underline", 4 /* STYLE */),
      createElementVNode("text", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({"text-decoration-line":"line-through","color":"blue"}))
      }), "text-decoration-line: line-through", 4 /* STYLE */)
    ])
  ], 4 /* STYLE */)
}
const GenPagesCSSTextTextDecorationLineStyles = [utsMapOf([["common", padStyleMapOf(utsMapOf([["fontSize", 20]]))], ["content", padStyleMapOf(utsMapOf([["height", 250], ["backgroundColor", "#808080"], ["justifyContent", "center"], ["alignItems", "center"], ["marginBottom", 16]]))]])]
