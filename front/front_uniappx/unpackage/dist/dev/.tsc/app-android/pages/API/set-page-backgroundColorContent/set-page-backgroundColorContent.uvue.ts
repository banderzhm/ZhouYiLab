
  const __sfc__ = defineComponent({
    data() {
      return {
        isChange: false,
        currentBackgroundColorContent: "" as any | null
      }
    },
    methods: {
      changeColor() {
        let pages = getCurrentPages()
        let page = pages[pages.length - 1]
        page.setPageStyle({ "backgroundColorContent": this.isChange ? "" : "red" })
        this.isChange = !this.isChange

        let pageJson = page.getPageStyle()
        this.currentBackgroundColorContent = pageJson["backgroundColorContent"]
      }
    }
  })

export default __sfc__
function GenPagesAPISetPageBackgroundColorContentSetPageBackgroundColorContentRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({
    class: "container",
    onClick: _ctx.changeColor
  }), [
    createElementVNode("text", null, "点击切换页面容器颜色")
  ], 8 /* PROPS */, ["onClick"])
}
const GenPagesAPISetPageBackgroundColorContentSetPageBackgroundColorContentStyles = [utsMapOf([["container", padStyleMapOf(utsMapOf([["flex", 1], ["alignItems", "center"], ["justifyContent", "center"]]))]])]
