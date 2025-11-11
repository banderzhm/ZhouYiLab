const __sfc__ = defineComponent({})
export default __sfc__
function GenPagesCSSTextFontWeightRender(this: InstanceType<typeof __sfc__>): any | null {
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
        style: normalizeStyle(utsMapOf({"font-weight":"normal"}))
      }), "font-weight: normal", 4 /* STYLE */),
      createElementVNode("text", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({"font-weight":"bold"}))
      }), "font-weight: bold", 4 /* STYLE */),
      createElementVNode("text", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({"font-weight":"400"}))
      }), "font-weight: 400", 4 /* STYLE */),
      createElementVNode("text", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({"font-weight":"700"}))
      }), "font-weight: 700", 4 /* STYLE */)
    ], 4 /* STYLE */)
  ], 4 /* STYLE */)
}
const GenPagesCSSTextFontWeightStyles = [utsMapOf([["common", padStyleMapOf(utsMapOf([["fontSize", 20]]))]])]
