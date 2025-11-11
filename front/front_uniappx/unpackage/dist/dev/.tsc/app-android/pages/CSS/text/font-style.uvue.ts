const __sfc__ = defineComponent({})
export default __sfc__
function GenPagesCSSTextFontStyleRender(this: InstanceType<typeof __sfc__>): any | null {
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
        style: normalizeStyle(utsMapOf({"font-style":"italic"}))
      }), "font-style: italic", 4 /* STYLE */),
      createElementVNode("text", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({"font-style":"normal"}))
      }), "font-style: normal", 4 /* STYLE */)
    ], 4 /* STYLE */)
  ], 4 /* STYLE */)
}
const GenPagesCSSTextFontStyleStyles = [utsMapOf([["common", padStyleMapOf(utsMapOf([["fontSize", 20]]))]])]
