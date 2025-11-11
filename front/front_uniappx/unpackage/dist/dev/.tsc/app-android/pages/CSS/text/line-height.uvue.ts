const __sfc__ = defineComponent({})
export default __sfc__
function GenPagesCSSTextLineHeightRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex-grow":"1"}))
  }), [
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"height":"500px","background-color":"gray","justify-content":"center","align-items":"center"}))
    }), [
      createElementVNode("text", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({"line-height":"75px"}))
      }), "line-height: 75px", 4 /* STYLE */),
      createElementVNode("text", utsMapOf({ class: "line-height-3 common" }), "line-height: 3"),
      createElementVNode("text", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({"line-height":"3em"}))
      }), "line-height: 3em", 4 /* STYLE */),
      createElementVNode("text", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({"line-height":"3"}))
      }), "line-height: 3\nline-height: 3\nline-height: 3", 4 /* STYLE */)
    ], 4 /* STYLE */)
  ], 4 /* STYLE */)
}
const GenPagesCSSTextLineHeightStyles = [utsMapOf([["common", padStyleMapOf(utsMapOf([["fontSize", 20], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#FF0000"], ["borderRightColor", "#FF0000"], ["borderBottomColor", "#FF0000"], ["borderLeftColor", "#FF0000"], ["marginTop", 10], ["marginRight", 0], ["marginBottom", 10], ["marginLeft", 0], ["paddingTop", 0], ["paddingRight", 10], ["paddingBottom", 0], ["paddingLeft", 10]]))], ["line-height-3", padStyleMapOf(utsMapOf([["lineHeight", 3]]))]])]
