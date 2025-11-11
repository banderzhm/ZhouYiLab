
  const __sfc__ = defineComponent({
    data() {
      return {
        scrollTop: 0
      }
    },
    onLoad() {

    },
    onReady() {
    },
    methods: {
      toNextPage() {
        uni.navigateTo({
          url: '/pages/component/list-view/list-view'
        })
      },
      getScrollTop() {
        return (this.$refs['list'] as UniListViewElement).scrollTop
      },
      setScrollTop(top: number) {
        (this.$refs['list'] as UniListViewElement).scrollTop = top
      }
    }
  })

export default __sfc__
function GenPagesComponentListViewIssue16938Render(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("list-view", utsMapOf({
    ref: "list",
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode(Fragment, null, RenderHelpers.renderList(1000, (item, __key, __index, _cached): any => {
      return createElementVNode("list-item", utsMapOf({
        class: "item",
        onClick: _ctx.toNextPage
      }), toDisplayString(item) + "点击跳转下一页 ", 9 /* TEXT, PROPS */, ["onClick"])
    }), 64 /* STABLE_FRAGMENT */)
  ], 4 /* STYLE */)
}
const GenPagesComponentListViewIssue16938Styles = [utsMapOf([["item", padStyleMapOf(utsMapOf([["height", 100]]))]])]
