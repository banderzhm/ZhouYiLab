const __sfc__ = defineComponent({})
export default __sfc__
function GenPagesCSSFlexFlexGrowRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex-grow":"1"}))
  }), [
    createElementVNode("view", null, [
      createElementVNode("text", null, "flex-grow"),
      createElementVNode("view", utsMapOf({
        style: normalizeStyle(utsMapOf({"width":"250px","height":"150px","background-color":"gray","flex-direction":"row"}))
      }), [
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"background-color":"red","flex-grow":"0.5"}))
        }), [
          createElementVNode("text", null, "0.5")
        ], 4 /* STYLE */),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"background-color":"green","flex-grow":"1"}))
        }), [
          createElementVNode("text", null, "1")
        ], 4 /* STYLE */),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"background-color":"blue","flex-grow":"2"}))
        }), [
          createElementVNode("text", null, "2")
        ], 4 /* STYLE */)
      ], 4 /* STYLE */)
    ])
  ], 4 /* STYLE */)
}
const GenPagesCSSFlexFlexGrowStyles = [utsMapOf([["common", padStyleMapOf(utsMapOf([["width", 50], ["height", 50], ["justifyContent", "center"], ["alignItems", "center"]]))]])]
