const __sfc__ = defineComponent({})
export default __sfc__
function GenPagesComponentListViewListViewWrapperRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("list-view", null, [
    createElementVNode("list-item", null, "start"),
    renderSlot(_ctx.$slots, "default"),
    createElementVNode("list-item", null, "middle"),
    renderSlot(_ctx.$slots, "second"),
    createElementVNode("list-item", null, "end")
  ])
}
const GenPagesComponentListViewListViewWrapperStyles = []
