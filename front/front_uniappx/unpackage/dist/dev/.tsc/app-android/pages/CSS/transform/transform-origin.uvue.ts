
  const __sfc__ = defineComponent({
    data() {
      return {
        count: 0
      }
    },
    methods: {
      changetransform() {
        var element = this.$refs["transformView"] as UniElement | null

        if (this.count == 0) {
          element?.style.setProperty("transform-origin", "60px 60px")
          element?.style.setProperty("transform", "scale(1)")
        }
        else if (this.count == 1) {
          element?.style.setProperty("transform-origin", "100% 0%")
          element?.style.setProperty("transform", "rotate(-20deg)")
        }

        this.count++
      }
    }
  })

export default __sfc__
function GenPagesCSSTransformTransformOriginRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("view", utsMapOf({
      ref: "transformView",
      class: "view",
      onClick: _ctx.changetransform
    }), null, 8 /* PROPS */, ["onClick"])
  ], 4 /* STYLE */)
}
const GenPagesCSSTransformTransformOriginStyles = [utsMapOf([["view", padStyleMapOf(utsMapOf([["width", 100], ["height", 100], ["transformOrigin", "10 10"], ["backgroundColor", "#00FFFF"], ["transform", "translate(50px, 50px) scale(2)"], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopColor", "#000000"], ["borderRightColor", "#000000"], ["borderBottomColor", "#000000"], ["borderLeftColor", "#000000"], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"]]))]])]
