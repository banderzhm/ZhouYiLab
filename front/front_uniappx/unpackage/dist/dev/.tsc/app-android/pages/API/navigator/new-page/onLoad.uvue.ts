
  const __sfc__ = defineComponent({
    data() {
      return {
        isTrue: false,
        isFalse: true,
        msg: 'default msg'
      }
    },
    onLoad(options : OnLoadOptions) {
      const type = options['type']
      switch (type) {
        case 'adjustData':
          this.adjustData()
          break;
        case 'navigateTo':
          this.navigateTo()
          break;
        case 'navigateBack':
          this.navigateBack()
          break;
        case 'redirectTo':
          this.redirectTo()
          break;
        case 'reLaunch':
          this.reLaunch()
          break;
        case 'switchTab':
          this.switchTab()
          break;
        case 'showToast':
          this.showToast()
          break;
        case 'showLoading':
          this.showLoading()
          break;
        case 'showModal':
          this.showModal()
          break;
        case 'showActionSheet':
          this.showActionSheet()
          break;
      }
    },















    methods: {
      adjustData() {
        this.isTrue = true
        this.isFalse = false
        this.msg = 'new msg'
      },
      navigateTo() {
        uni.navigateTo({
          url: '/pages/API/navigator/new-page/new-page-3'
        })
      },
      navigateBack() {
        uni.navigateBack()
      },
      redirectTo() {
        uni.redirectTo({
          url: '/pages/API/navigator/new-page/new-page-3'
        })
      },
      reLaunch() {
        uni.reLaunch({
          url: '/pages/API/navigator/new-page/new-page-3'
        })
      },
      switchTab() {
        uni.switchTab({
          url: '/pages/tabBar/component'
        })
      },
      showToast() {
        uni.showToast({
          title: 'test title',
          icon: 'success',
          duration: 2000
        })
      },
      showLoading() {
        uni.showLoading({
          title: 'test title',
        })
        setTimeout(() => {
          uni.hideLoading()
        }, 2000)
      },
      showModal() {
        uni.showModal({
          title: 'test title',
          content: 'test content'
        })
      },
      showActionSheet() {
        uni.showActionSheet({
          title: 'test title',
          itemList: ['1', '2', '3']
        })
      }
    }
  })

export default __sfc__
function GenPagesAPINavigatorNewPageOnLoadRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
      createVNode(_component_page_head, utsMapOf({ title: "onLoad 生命周期调用 uni api 测试" })),
      isTrue(_ctx.isTrue)
        ? createElementVNode("text", utsMapOf({ key: 0 }), "v-if with true")
        : createCommentVNode("v-if", true),
      isTrue(_ctx.isFalse)
        ? createElementVNode("text", utsMapOf({ key: 1 }), "v-if with false")
        : createCommentVNode("v-if", true),
      withDirectives(createElementVNode("text", null, "v-show with true", 512 /* NEED_PATCH */), [
        [vShow, _ctx.isTrue]
      ]),
      withDirectives(createElementVNode("text", null, "v-show with false", 512 /* NEED_PATCH */), [
        [vShow, _ctx.isFalse]
      ]),
      createElementVNode("text", null, "msg: " + toDisplayString(_ctx.msg), 1 /* TEXT */)
    ])
  ], 4 /* STYLE */)
}
const GenPagesAPINavigatorNewPageOnLoadStyles = []

import _easycom_page_head from '@/components/page-head/page-head.vue'
