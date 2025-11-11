
  const __sfc__ = defineComponent({
    data() {
      return {
        item_count: 10,
        src: 'https://qiniu-web-assets.dcloud.net.cn/video/sample/2minute-demo.mp4',
        isTesting: false
      }
    },
    methods: {
      onPause(e : UniEvent) {
        console.log(JSON.stringify(e), " at pages/component/list-view/list-view-multiplex-video.uvue:27");
      },
      onScrollTolower(_ : ScrollToLowerEvent) {
        setTimeout(() => {
          if (this.isTesting && this.item_count >= 10) {
            return
          }
          this.item_count += 10
        }, 300)
      },
    }
  })

export default __sfc__
function GenPagesComponentListViewListViewMultiplexVideoRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("list-view", utsMapOf({
    class: "list",
    onScrolltolower: _ctx.onScrollTolower
  }), [
    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.item_count, (_, index, __index, _cached): any => {
      return createElementVNode("list-item", utsMapOf({ class: "list-item" }), [
        createElementVNode("text", null, "第" + toDisplayString(index + 1) + "个视频", 1 /* TEXT */),
        createElementVNode("video", utsMapOf({
          class: "video",
          src: _ctx.src,
          controls: true,
          onPause: _ctx.onPause
        }), null, 40 /* PROPS, NEED_HYDRATION */, ["src", "onPause"])
      ])
    }), 256 /* UNKEYED_FRAGMENT */)
  ], 40 /* PROPS, NEED_HYDRATION */, ["onScrolltolower"])
}
const GenPagesComponentListViewListViewMultiplexVideoStyles = [utsMapOf([["list", padStyleMapOf(utsMapOf([["flex", 1]]))], ["list-item", padStyleMapOf(utsMapOf([["backgroundColor", "#FFFFFF"], ["marginBottom", 30]]))], ["video", padStyleMapOf(utsMapOf([["width", "100%"], ["height", 200]]))]])]
