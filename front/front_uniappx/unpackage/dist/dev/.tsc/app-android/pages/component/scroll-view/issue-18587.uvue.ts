
  const __sfc__ = defineComponent({
    data() {
      return {
        scrollEndTriggeredTimes: 0
      }
    },
    onLoad() {

    },
    methods: {
      scrollTo(scrollTop: number) {
        const scroll = uni.getElementById('scroll') as UniScrollViewElement
        scroll.scrollTop = scrollTop
      },
      onScrollEnd() {
        console.log('scrollEnd触发了', " at pages/component/scroll-view/issue-18587.uvue:26")
        this.scrollEndTriggeredTimes++
      }
    }
  })

export default __sfc__
function GenPagesComponentScrollViewIssue18587Render(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("button", utsMapOf({
      id: "test-btn",
      onClick: () => {_ctx.scrollTo(100)}
    }), "滚动到100px位置", 8 /* PROPS */, ["onClick"]),
    createElementVNode("scroll-view", utsMapOf({
      id: "scroll",
      style: normalizeStyle(utsMapOf({"flex":"1"})),
      direction: "vertical",
      onScrollend: _ctx.onScrollEnd
    }), [
      createElementVNode("view", utsMapOf({
        style: normalizeStyle(utsMapOf({"border":"dashed 10px black","height":"3000px"}))
      }), null, 4 /* STYLE */)
    ], 44 /* STYLE, PROPS, NEED_HYDRATION */, ["onScrollend"])
  ], 4 /* STYLE */)
}
const GenPagesComponentScrollViewIssue18587Styles = []
