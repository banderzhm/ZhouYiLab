
  const __sfc__ = defineComponent({
    data() {
      return {
        compId: 'comp2-id'
      }
    }
  })

export default __sfc__
function GenPagesTemplateIssue14765Comp2Render(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({
    id: _ctx.compId,
    ref: "uxToastItemRef"
  }), [
    createElementVNode("text", null, "hello"),
    renderSlot(_ctx.$slots, "default")
  ], 8 /* PROPS */, ["id"])
}
const GenPagesTemplateIssue14765Comp2Styles = []
