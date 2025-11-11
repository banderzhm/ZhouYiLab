
  const __sfc__ = defineComponent({
    name: "parent",
    data() {
      return {
        items: [1, 2, 3]
      }
    }
  })

export default __sfc__
function GenComponentsIssue21223CompIssue21223CompRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({
    style: normalizeStyle(utsMapOf({"width":"100%","height":"300px","background-color":"red"}))
  }), [
    renderSlot(_ctx.$slots, "default", utsMapOf({ items: _ctx.items }))
  ], 4 /* STYLE */)
}
export type Issue21223CompComponentPublicInstance = InstanceType<typeof __sfc__>;
const GenComponentsIssue21223CompIssue21223CompStyles = []
