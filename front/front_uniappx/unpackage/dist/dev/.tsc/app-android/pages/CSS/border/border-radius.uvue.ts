const __sfc__ = defineComponent({})
export default __sfc__
function GenPagesCSSBorderBorderRadiusRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"flex-grow":"1"}))
    }), [
      createElementVNode("view", null, [
        createElementVNode("text", null, "border-radius: 10px"),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"border-radius":"10px"}))
        }), null, 4 /* STYLE */)
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "border-top-left-radius: 10px"),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"border-top-left-radius":"10px"}))
        }), null, 4 /* STYLE */)
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "border-top-right-radius: 10px"),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"border-top-right-radius":"10px"}))
        }), null, 4 /* STYLE */)
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "border-bottom-left-radius: 10px"),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"border-bottom-left-radius":"10px"}))
        }), null, 4 /* STYLE */)
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "border-bottom-right-radius: 10px"),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"border-bottom-right-radius":"10px"}))
        }), null, 4 /* STYLE */)
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "border-radius: 250px（与长宽相同形成正圆）"),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"border-radius":"250px"}))
        }), null, 4 /* STYLE */)
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "border-radius: 10px（包含子视图）"),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"margin-left":"5px","border-radius":"10px"}))
        }), [
          createElementVNode("view", utsMapOf({
            style: normalizeStyle(utsMapOf({"background-color":"wheat","width":"100%","height":"20px"}))
          }), null, 4 /* STYLE */)
        ], 4 /* STYLE */)
      ]),
      createElementVNode("view", utsMapOf({
        style: normalizeStyle(utsMapOf({"margin-top":"10px"}))
      }), [
        createElementVNode("text", null, "border-bottom-left-radius: 10px \nborder-bottom-right-radius: 10px \n(包含子视图）"),
        createElementVNode("view", utsMapOf({
          class: "common",
          style: normalizeStyle(utsMapOf({"margin-left":"5px","border-top-left-radius":"10px","border-top-right-radius":"10px"}))
        }), [
          createElementVNode("view", utsMapOf({
            style: normalizeStyle(utsMapOf({"background-color":"wheat","width":"100%","height":"20px"}))
          }), null, 4 /* STYLE */)
        ], 4 /* STYLE */)
      ], 4 /* STYLE */)
    ], 4 /* STYLE */)
  ], 4 /* STYLE */)
}
const GenPagesCSSBorderBorderRadiusStyles = [utsMapOf([["common", padStyleMapOf(utsMapOf([["width", 250], ["height", 250], ["backgroundColor", "#808080"]]))]])]
