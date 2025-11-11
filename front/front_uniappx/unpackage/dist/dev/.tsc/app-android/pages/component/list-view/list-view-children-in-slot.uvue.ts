
  import ListViewWrapper from './ListViewWrapper.uvue'

  const __sfc__ = defineComponent({
    components: { ListViewWrapper },
    data() {
      return {
        list: [0, 1, 2]
      }
    },
    methods: {
      addItem() {
        this.list.push(this.list.length)
      },
      emptyList() {
        this.list = []
      }
    }
  })

export default __sfc__
function GenPagesComponentListViewListViewChildrenInSlotRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)
const _component_list_view_wrapper = resolveComponent("list-view-wrapper")

  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1","padding-bottom":"20px"}))
  }), [
    createElementVNode("view", null, [
      createVNode(_component_page_head, utsMapOf({ title: "getApp" })),
      createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
        createVNode(_component_list_view_wrapper, null, utsMapOf({
          default: withSlotCtx((): any[] => [
            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.list, (item, __key, __index, _cached): any => {
              return createElementVNode("list-item", utsMapOf({ key: item }), [
                createElementVNode("text", utsMapOf({ class: "text-in-list-item" }), toDisplayString(item), 1 /* TEXT */)
              ])
            }), 128 /* KEYED_FRAGMENT */)
          ]),
          second: withSlotCtx((): any[] => [
            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.list, (item, __key, __index, _cached): any => {
              return createElementVNode("list-item", utsMapOf({ key: item }), [
                createElementVNode("text", utsMapOf({ class: "text-in-list-item" }), toDisplayString(item), 1 /* TEXT */)
              ])
            }), 128 /* KEYED_FRAGMENT */)
          ]),
          _: 1 /* STABLE */
        })),
        createElementVNode("button", utsMapOf({
          id: "add-btn",
          class: "uni-common-mt",
          onClick: _ctx.addItem
        }), "add item", 8 /* PROPS */, ["onClick"]),
        createElementVNode("button", utsMapOf({
          id: "empty-btn",
          class: "uni-common-mt",
          onClick: _ctx.emptyList
        }), "empty list", 8 /* PROPS */, ["onClick"])
      ])
    ])
  ], 4 /* STYLE */)
}
const GenPagesComponentListViewListViewChildrenInSlotStyles = []

import _easycom_page_head from '@/components/page-head/page-head.vue'
