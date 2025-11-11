const __sfc__ = defineComponent({})
export default __sfc__
function GenPagesCSSFlexFlexBasisRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex-grow":"1"}))
  }), [
    createElementVNode("view", null, [
      createElementVNode("text", null, "flex-basis"),
      createElementVNode("view", utsMapOf({
        style: normalizeStyle(utsMapOf({"width":"250px","height":"150px","background-color":"gray","flex-direction":"row"}))
      }), [
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"flex-basis":"150px"}))
        }), [
          createElementVNode("text", null, "150px")
        ], 4 /* STYLE */),
        createElementVNode("view", utsMapOf({
          style: normalizeStyle(utsMapOf({"width":"50px","height":"50px","background-color":"green"}))
        }), null, 4 /* STYLE */),
        createElementVNode("view", utsMapOf({
          style: normalizeStyle(utsMapOf({"width":"50px","height":"50px","background-color":"blue"}))
        }), null, 4 /* STYLE */)
      ], 4 /* STYLE */)
    ])
  ], 4 /* STYLE */)
}
const GenPagesCSSFlexFlexBasisStyles = [utsMapOf([["common", padStyleMapOf(utsMapOf([["width", 50], ["height", 50], ["backgroundColor", "#FF0000"], ["justifyContent", "center"], ["alignItems", "center"]]))]])]
