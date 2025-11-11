
  const __sfc__ = defineComponent({
    data() {
      return {
        tips: false
      }
    },
    methods: {
      pushList() {
        uni.navigateTo({
          url: '/pages/component/ad/list-view-ad',
        })
      },
      pushVideoAd() {
        uni.navigateTo({
          url: '/pages/component/ad/video-ad',
        })
      },
      pushVideoAdL() {
        uni.navigateTo({
          url: '/pages/component/ad/landscape-video-ad',
        })
      },
      pushVideoAdP() {
        uni.navigateTo({
          url: '/pages/component/ad/portrait-video-ad',
        })
      },
      loadFun() {
        uni.showToast({
          position: "bottom",
          title: "信息流广告加载成功"
        })
      },
      errorFun() {
        this.tips = true;
      }
    }
  })

export default __sfc__
function GenPagesComponentAdAdRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode("view", null, [
    createVNode(_component_page_head, utsMapOf({ title: "信息流广告" })),
    createElementVNode("ad", utsMapOf({
      adpid: "1111111111",
      style: normalizeStyle(utsMapOf({"width":"100%"})),
      onLoad: _ctx.loadFun,
      onError: _ctx.errorFun
    }), null, 44 /* STYLE, PROPS, NEED_HYDRATION */, ["onLoad", "onError"]),
    isTrue(_ctx.tips)
      ? createElementVNode("view", utsMapOf({
          key: 0,
          class: "uni-center"
        }), "信息流广告加载失败，请退出当前页面重试。")
      : createCommentVNode("v-if", true),
    createElementVNode("button", utsMapOf({ onClick: _ctx.pushList }), "列表-信息流", 8 /* PROPS */, ["onClick"]),
    createElementVNode("button", utsMapOf({ onClick: _ctx.pushVideoAd }), "视频贴片-非全屏", 8 /* PROPS */, ["onClick"]),
    createElementVNode("button", utsMapOf({ onClick: _ctx.pushVideoAdL }), "视频贴片-横向全屏", 8 /* PROPS */, ["onClick"]),
    createElementVNode("button", utsMapOf({ onClick: _ctx.pushVideoAdP }), "视频贴片-竖向全屏", 8 /* PROPS */, ["onClick"])
  ])
}
const GenPagesComponentAdAdStyles = []

import _easycom_page_head from '@/components/page-head/page-head.vue'
