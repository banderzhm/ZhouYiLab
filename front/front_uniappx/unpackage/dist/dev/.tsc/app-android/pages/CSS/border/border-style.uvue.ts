
  const __sfc__ = defineComponent({
    data() {
      return {
        isSolid: false,
        borderStyle: "border-style: none; border-width: 5px;",
      }
    },
    methods: {
      changeBorderStyle() {
        this.isSolid = !this.isSolid;
        const solid = "border-style: solid; border-width: 5px;";
        const none = "";
        this.borderStyle = this.isSolid ? solid : none;
      }
    }
  })

export default __sfc__
function GenPagesCSSBorderBorderStyleRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"flex-grow":"1"}))
    }), [
      createElementVNode("view", null, [
        createElementVNode("text", null, "border-style: dashed"),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"border-width":"5px","border-style":"dashed"}))
        }), null, 4 /* STYLE */)
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "border-left-style: dashed"),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"border-left-width":"5px","border-left-style":"dashed"}))
        }), null, 4 /* STYLE */)
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "border-top-style: dashed"),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"border-top-width":"5px","border-top-style":"dashed"}))
        }), null, 4 /* STYLE */)
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "border-right-style: dotted"),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"border-right-width":"5px","border-right-style":"dotted"}))
        }), null, 4 /* STYLE */)
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "border-bottom-style: dotted"),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"border-bottom-width":"5px","border-bottom-style":"dotted"}))
        }), null, 4 /* STYLE */)
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "border-style: solid (缺省 border-width)"),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"border-style":"solid"}))
        }), null, 4 /* STYLE */)
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "border-style: none"),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"border-style":"none","border-width":"5px"}))
        }), null, 4 /* STYLE */)
      ]),
      createElementVNode("view", utsMapOf({ onClick: _ctx.changeBorderStyle }), [
        createElementVNode("text", null, "border-style: 点击切换"),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(_ctx.borderStyle)
        }), null, 4 /* STYLE */)
      ], 8 /* PROPS */, ["onClick"])
    ], 4 /* STYLE */)
  ], 4 /* STYLE */)
}
const GenPagesCSSBorderBorderStyleStyles = [utsMapOf([["common", padStyleMapOf(utsMapOf([["width", 250], ["height", 250], ["backgroundColor", "#808080"]]))]])]
