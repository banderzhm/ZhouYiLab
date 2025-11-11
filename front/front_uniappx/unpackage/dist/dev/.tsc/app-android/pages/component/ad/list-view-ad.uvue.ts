
  const __sfc__ = defineComponent({
    data() {
      return {
        listcount: 10
      }
    },
    methods: {
      onScrollTolower(_ : ScrollToLowerEvent) {
        setTimeout(() => {
          this.listcount = this.listcount + 10
        }, 300)
      },
      onAdLoad() {
        console.log("广告加载成功", " at pages/component/ad/list-view-ad.uvue:30")
        uni.showToast({
          position: "center",
          title: "广告加载成功"
        })
      },
      onAdError(e : UniAdErrorEvent) {
        console.log("广告加载失败" + (e.detail), " at pages/component/ad/list-view-ad.uvue:37")
      },
      onAdClose() {
        console.log("广告关闭了", " at pages/component/ad/list-view-ad.uvue:40")
      }
    }
  })

export default __sfc__
function GenPagesComponentAdListViewAdRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("list-view", utsMapOf({
    onScrolltolower: _ctx.onScrollTolower,
    style: normalizeStyle(utsMapOf({"flex":"1"})),
    "show-scrollbar": "false"
  }), [
    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.listcount, (index, __key, __index, _cached): any => {
      return createElementVNode("list-item", utsMapOf({
        type: (index != 0 && index%10 == 6) ? 0:1
      }), [
        isTrue(index != 0 && index%10 == 6)
          ? createElementVNode("ad", utsMapOf({
              key: 0,
              adpid: "1111111111",
              onLoad: _ctx.onAdLoad,
              onError: _ctx.onAdError,
              onClose: _ctx.onAdClose
            }), null, 40 /* PROPS, NEED_HYDRATION */, ["onLoad", "onError", "onClose"])
          : createCommentVNode("v-if", true),
        isTrue(index == 0 || index%10 != 6)
          ? createElementVNode("text", utsMapOf({
              key: 1,
              style: normalizeStyle(utsMapOf({"width":"100%","height":"200px","background-color":"aquamarine","margin-top":"10px"}))
            }), "这是一条占位的信息" + toDisplayString(index), 5 /* TEXT, STYLE */)
          : createCommentVNode("v-if", true)
      ], 8 /* PROPS */, ["type"])
    }), 256 /* UNKEYED_FRAGMENT */)
  ], 44 /* STYLE, PROPS, NEED_HYDRATION */, ["onScrolltolower"])
}
const GenPagesComponentAdListViewAdStyles = []
