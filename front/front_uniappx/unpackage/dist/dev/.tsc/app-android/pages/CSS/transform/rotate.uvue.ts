const __sfc__ = defineComponent({})
export default __sfc__
function GenPagesCSSTransformRotateRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("scroll-view", utsMapOf({ class: "page" }), [
    createElementVNode("view", utsMapOf({ class: "trace" }), [
      createElementVNode("view", utsMapOf({ class: "base reserve" }), [
        createElementVNode("text", utsMapOf({ class: "reserve-text" }), "rotate(20deg)"),
        createElementVNode("text", utsMapOf({ class: "reserve-text" }), "转变前位置")
      ]),
      createElementVNode("view", utsMapOf({ class: "base reserve" }), [
        createElementVNode("text", utsMapOf({ class: "reserve-text" }), "rotateX(50deg)"),
        createElementVNode("text", utsMapOf({ class: "reserve-text" }), "转变前位置")
      ]),
      createElementVNode("view", utsMapOf({ class: "base reserve" }), [
        createElementVNode("text", utsMapOf({ class: "reserve-text" }), "rotateY(50deg)"),
        createElementVNode("text", utsMapOf({ class: "reserve-text" }), "转变前位置")
      ]),
      createElementVNode("view", utsMapOf({ class: "base reserve" }), [
        createElementVNode("text", utsMapOf({ class: "reserve-text" }), "rotateZ(50deg)"),
        createElementVNode("text", utsMapOf({ class: "reserve-text" }), "转变前位置")
      ]),
      createElementVNode("view", utsMapOf({ class: "base reserve" }), [
        createElementVNode("text", utsMapOf({ class: "reserve-text" }), "rotateX(180deg)"),
        createElementVNode("text", utsMapOf({ class: "reserve-text" }), "转变前位置")
      ]),
      createElementVNode("view", utsMapOf({ class: "base reserve" }), [
        createElementVNode("text", utsMapOf({ class: "reserve-text" }), "rotateY(180deg)"),
        createElementVNode("text", utsMapOf({ class: "reserve-text" }), "转变前位置")
      ])
    ]),
    createElementVNode("view", utsMapOf({
      class: "base transform",
      style: normalizeStyle(utsMapOf({"transform":"rotate(20deg)"}))
    }), [
      createElementVNode("text", null, "rotate(20deg)"),
      createElementVNode("text", null, "转变后位置")
    ], 4 /* STYLE */),
    createElementVNode("view", utsMapOf({
      class: "base transform",
      style: normalizeStyle(utsMapOf({"transform":"rotateX(50deg)"}))
    }), [
      createElementVNode("text", null, "rotateX(50deg)"),
      createElementVNode("text", null, "转变后位置")
    ], 4 /* STYLE */),
    createElementVNode("view", utsMapOf({
      class: "base transform",
      style: normalizeStyle(utsMapOf({"transform":"rotateY(50deg)"}))
    }), [
      createElementVNode("text", null, "rotateY(50deg)"),
      createElementVNode("text", null, "转变后位置")
    ], 4 /* STYLE */),
    createElementVNode("view", utsMapOf({
      class: "base transform",
      style: normalizeStyle(utsMapOf({"transform":"rotateZ(50deg)"}))
    }), [
      createElementVNode("text", null, "rotateZ(50deg)"),
      createElementVNode("text", null, "转变后位置")
    ], 4 /* STYLE */),
    createElementVNode("view", utsMapOf({
      class: "base transform",
      style: normalizeStyle(utsMapOf({"transform":"rotateX(180deg)"}))
    }), [
      createElementVNode("text", null, "rotateX(180deg)"),
      createElementVNode("text", null, "转变后位置")
    ], 4 /* STYLE */),
    createElementVNode("view", utsMapOf({
      class: "base transform",
      style: normalizeStyle(utsMapOf({"transform":"rotateY(180deg)"}))
    }), [
      createElementVNode("text", null, "rotateY(180deg)"),
      createElementVNode("text", null, "转变后位置")
    ], 4 /* STYLE */)
  ])
}
const GenPagesCSSTransformRotateStyles = [utsMapOf([["page", padStyleMapOf(utsMapOf([["flex", 1], ["alignItems", "center"]]))], ["trace", padStyleMapOf(utsMapOf([["position", "absolute"], ["top", 0], ["left", 0], ["width", "100%"], ["alignItems", "center"]]))], ["base", padStyleMapOf(utsMapOf([["marginTop", 10], ["marginRight", 10], ["marginBottom", 10], ["marginLeft", 10], ["width", 150], ["height", 150], ["alignItems", "center"], ["justifyContent", "center"]]))], ["reserve", padStyleMapOf(utsMapOf([["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "dotted"], ["borderRightStyle", "dotted"], ["borderBottomStyle", "dotted"], ["borderLeftStyle", "dotted"], ["borderTopColor", "#558888"], ["borderRightColor", "#558888"], ["borderBottomColor", "#558888"], ["borderLeftColor", "#558888"], ["backgroundColor", "#dddddd"]]))], ["reserve-text", padStyleMapOf(utsMapOf([["color", "#cccccc"]]))], ["transform", padStyleMapOf(utsMapOf([["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#0000ff"], ["borderRightColor", "#0000ff"], ["borderBottomColor", "#0000ff"], ["borderLeftColor", "#0000ff"], ["backgroundColor", "rgba(0,255,255,0.5)"]]))]])]
