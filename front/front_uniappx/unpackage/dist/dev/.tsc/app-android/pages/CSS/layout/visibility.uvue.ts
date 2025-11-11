
  const __sfc__ = defineComponent({
    data() {
      return {
        visibility: 'visible',
        flag: true
      }
    },
    methods: {
      changeVisibility() {
        this.flag = !this.flag;
        if (this.flag) {
          this.visibility = 'visible';
        } else {
          this.visibility = 'hidden';
        }
      }
    }
  })

export default __sfc__
function GenPagesCSSLayoutVisibilityRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex-grow":"1"}))
  }), [
    createElementVNode("view", utsMapOf({ onClick: _ctx.changeVisibility }), [
      createElementVNode("text", null, "visibility: " + toDisplayString(_ctx.visibility) + "（点击切换）", 1 /* TEXT */),
      createElementVNode("view", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({'visibility': _ctx.visibility}))
      }), null, 4 /* STYLE */)
    ], 8 /* PROPS */, ["onClick"])
  ], 4 /* STYLE */)
}
const GenPagesCSSLayoutVisibilityStyles = [utsMapOf([["common", padStyleMapOf(utsMapOf([["width", 250], ["height", 250], ["backgroundColor", "#FF0000"]]))]])]
