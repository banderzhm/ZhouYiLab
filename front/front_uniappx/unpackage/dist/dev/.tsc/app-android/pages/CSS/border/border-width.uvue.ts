const __sfc__ = defineComponent({})
export default __sfc__
function GenPagesCSSBorderBorderWidthRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"flex-grow":"1"}))
    }), [
      createElementVNode("view", null, [
        createElementVNode("text", null, "border-width: 5px (without border-style)"),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"border-width":"5px"}))
        }), null, 4 /* STYLE */)
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "border-width: 5px"),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"border-width":"5px","border-style":"solid"}))
        }), null, 4 /* STYLE */)
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "border-left-width: 5px"),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"border-left-width":"5px","border-left-style":"solid"}))
        }), null, 4 /* STYLE */)
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "border-top-width: 10px"),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"border-top-width":"10px","border-top-style":"solid"}))
        }), null, 4 /* STYLE */)
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "border-right-width: 15px"),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"border-right-width":"15px","border-right-style":"solid"}))
        }), null, 4 /* STYLE */)
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "border-bottom-width: 20px"),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"border-bottom-width":"20px","border-bottom-style":"solid"}))
        }), null, 4 /* STYLE */)
      ])
    ], 4 /* STYLE */)
  ], 4 /* STYLE */)
}
const GenPagesCSSBorderBorderWidthStyles = [utsMapOf([["common", padStyleMapOf(utsMapOf([["width", 250], ["height", 250], ["backgroundColor", "#808080"]]))]])]
