
  import Item from './issue-2199-item.uvue'

  const __sfc__ = defineComponent({
    components: {
      Item
    }
  })

export default __sfc__
function GenPagesComponentListViewIssue2199Render(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_Item = resolveComponent("Item")

  return createElementVNode("view", null, [
    createElementVNode("list-view", null, [
      createElementVNode("list-item", null, [
        createElementVNode("view", utsMapOf({ class: "title" }), " 1 ")
      ]),
      createVNode(_component_Item, null, utsMapOf({
        default: withSlotCtx((): any[] => [
          createElementVNode("view", utsMapOf({ class: "title" }), " 2 ")
        ]),
        _: 1 /* STABLE */
      }))
    ])
  ])
}
const GenPagesComponentListViewIssue2199Styles = [utsMapOf([["title", padStyleMapOf(utsMapOf([["textAlign", "center"], ["justifyContent", "center"], ["alignItems", "center"]]))]])]
