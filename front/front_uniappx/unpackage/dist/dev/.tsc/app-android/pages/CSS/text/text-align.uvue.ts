const __sfc__ = defineComponent({})
export default __sfc__
function GenPagesCSSTextTextAlignRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex-grow":"1"}))
  }), [
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"height":"250px","background-color":"gray","justify-content":"center","align-items":"center"}))
    }), [
      createElementVNode("text", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({"text-align":"left"}))
      }), "text-align: left", 4 /* STYLE */),
      createElementVNode("text", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({"text-align":"center"}))
      }), "text-align: center", 4 /* STYLE */),
      createElementVNode("text", utsMapOf({
        class: "common2",
        style: normalizeStyle(utsMapOf({"text-align":"center"}))
      }), "text-align: center", 4 /* STYLE */),
      createElementVNode("text", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({"text-align":"right"}))
      }), "text-align: right", 4 /* STYLE */)
    ], 4 /* STYLE */)
  ], 4 /* STYLE */)
}
const GenPagesCSSTextTextAlignStyles = [utsMapOf([["common", padStyleMapOf(utsMapOf([["width", 250], ["fontSize", 20]]))], ["common2", padStyleMapOf(utsMapOf([["minWidth", 250], ["fontSize", 20]]))]])]
