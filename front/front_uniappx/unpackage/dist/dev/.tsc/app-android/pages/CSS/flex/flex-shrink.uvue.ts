const __sfc__ = defineComponent({})
export default __sfc__
function GenPagesCSSFlexFlexShrinkRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex-grow":"1"}))
  }), [
    createElementVNode("view", null, [
      createElementVNode("text", null, "flex-shrink"),
      createElementVNode("view", utsMapOf({
        style: normalizeStyle(utsMapOf({"width":"250px","height":"150px","background-color":"gray","flex-direction":"row"}))
      }), [
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"background-color":"red","flex-shrink":"1"}))
        }), [
          createElementVNode("text", null, "1")
        ], 4 /* STYLE */),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"background-color":"green","flex-shrink":"2"}))
        }), [
          createElementVNode("text", null, "2")
        ], 4 /* STYLE */),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"background-color":"blue","flex-shrink":"3"}))
        }), [
          createElementVNode("text", null, "3")
        ], 4 /* STYLE */)
      ], 4 /* STYLE */)
    ])
  ], 4 /* STYLE */)
}
const GenPagesCSSFlexFlexShrinkStyles = [utsMapOf([["common", padStyleMapOf(utsMapOf([["width", 125], ["height", 50], ["justifyContent", "center"], ["alignItems", "center"]]))]])]
