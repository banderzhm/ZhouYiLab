
  const __sfc__ = defineComponent({
    data() {
      return {
        data: [] as Array<number>,
        loadMoreText: "加载中...",
        showLoadMore: false,
        max: 0,
        pulldownRefreshTriggered: false
      }
    },
    onReady() {
      uni.startPullDownRefresh();
      this.initData();
    },
    onReachBottom() {
      console.log("onReachBottom", " at pages/API/pull-down-refresh/pull-down-refresh.uvue:30");
      if (this.max > 40) {
        this.loadMoreText = "没有更多数据了!"
        return;
      }
      this.showLoadMore = true;
      setTimeout(() => {
        this.setListData();
      }, 300);
    },
    onPullDownRefresh() {
      console.log('onPullDownRefresh', " at pages/API/pull-down-refresh/pull-down-refresh.uvue:41");
      this.pulldownRefreshTriggered = true
      this.initData();
    },
    methods: {
      initData() {
        setTimeout(() => {
          this.max = 0;
          this.data = [];
          let data : Array<number> = [];
          this.max += 20;
          for (let i : number = this.max - 19; i < this.max + 1; i++) {
            data.push(i)
          }
          this.data = this.data.concat(data);
          uni.stopPullDownRefresh();
        }, 1000);
      },
      setListData() {
        let data : Array<number> = [];
        this.max += 10;
        for (let i : number = this.max - 9; i < this.max + 1; i++) {
          data.push(i)
        }
        this.data = this.data.concat(data);
      }
    }
  })

export default __sfc__
function GenPagesAPIPullDownRefreshPullDownRefreshRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap uni-common-mt" }), [
      createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.data, (num, index, __index, _cached): any => {
        return createElementVNode("text", utsMapOf({
          class: "text",
          key: index
        }), "list - " + toDisplayString(num), 1 /* TEXT */)
      }), 128 /* KEYED_FRAGMENT */),
      isTrue(_ctx.showLoadMore)
        ? createElementVNode("view", utsMapOf({ key: 0 }), toDisplayString(_ctx.loadMoreText), 1 /* TEXT */)
        : createCommentVNode("v-if", true)
    ])
  ], 4 /* STYLE */)
}
const GenPagesAPIPullDownRefreshPullDownRefreshStyles = [utsMapOf([["text", padStyleMapOf(utsMapOf([["marginTop", 6], ["marginRight", 0], ["marginBottom", 6], ["marginLeft", 0], ["width", "100%"], ["backgroundColor", "#ffffff"], ["height", 52], ["lineHeight", "52px"], ["textAlign", "center"], ["color", "#555555"], ["borderTopLeftRadius", 4], ["borderTopRightRadius", 4], ["borderBottomRightRadius", 4], ["borderBottomLeftRadius", 4]]))]])]
