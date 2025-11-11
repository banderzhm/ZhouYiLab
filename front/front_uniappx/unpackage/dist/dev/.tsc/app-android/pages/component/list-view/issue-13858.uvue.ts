
  import Item from './issue-13858-item.uvue'

  const __sfc__ = defineComponent({
    data() {
      return {
        childMountedTriggeredCount: 0
      }
    },
    components: {
      Item
    },
    onLoad() {

    },
    methods: {
      itemMounted() {
        this.childMountedTriggeredCount++
      }
    }
  })

export default __sfc__
function GenPagesComponentListViewIssue13858Render(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_Item = resolveComponent("Item")

  return createElementVNode("view", null, [
    createElementVNode("list-view", null, [
      createElementVNode("list-item", null, [
        createVNode(_component_Item, utsMapOf({ onItemMounted: _ctx.itemMounted }), null, 8 /* PROPS */, ["onItemMounted"])
      ])
    ])
  ])
}
const GenPagesComponentListViewIssue13858Styles = [utsMapOf([["title", padStyleMapOf(utsMapOf([["textAlign", "center"], ["justifyContent", "center"], ["alignItems", "center"]]))]])]
