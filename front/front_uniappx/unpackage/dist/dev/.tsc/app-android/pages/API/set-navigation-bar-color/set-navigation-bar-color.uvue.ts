
  import { state, setLifeCycleNum } from '@/store/index.uts'

  const __sfc__ = defineComponent({
    methods: {
      setNavigationBarColor1() {
        uni.setNavigationBarColor({
          frontColor: '#ffffff',
          backgroundColor: '#00ff00',
          success: () => {
            console.log('setNavigationBarColor success', " at pages/API/set-navigation-bar-color/set-navigation-bar-color.uvue:26")
            this.setLifeCycleNum(state.lifeCycleNum + 1)
          },
          fail: () => {
            console.log('setNavigationBarColor fail', " at pages/API/set-navigation-bar-color/set-navigation-bar-color.uvue:30")
            this.setLifeCycleNum(state.lifeCycleNum - 1)
          },
          complete: () => {
            console.log('setNavigationBarColor complete', " at pages/API/set-navigation-bar-color/set-navigation-bar-color.uvue:34")
            this.setLifeCycleNum(state.lifeCycleNum + 1)
          }
        })
      },
      setNavigationBarColor2() {
        uni.setNavigationBarColor({
          frontColor: '#000000',
          backgroundColor: '#ff0000',
          success: () => {
            console.log('setNavigationBarColor success', " at pages/API/set-navigation-bar-color/set-navigation-bar-color.uvue:44")
            this.setLifeCycleNum(state.lifeCycleNum + 1)
          },
          fail: () => {
            console.log('setNavigationBarColor fail', " at pages/API/set-navigation-bar-color/set-navigation-bar-color.uvue:48")
            this.setLifeCycleNum(state.lifeCycleNum - 1)
          },
          complete: () => {
            console.log('setNavigationBarColor complete', " at pages/API/set-navigation-bar-color/set-navigation-bar-color.uvue:52")
            this.setLifeCycleNum(state.lifeCycleNum + 1)
          }
        })
      },
      // 自动化测试
      getLifeCycleNum() : number {
        return state.lifeCycleNum
      },
      // 自动化测试
      setLifeCycleNum(num : number) {
        setLifeCycleNum(num)
      },
      goNavbarLite() {
        uni.navigateTo({
          url: '/pages/template/navbar-lite/navbar-lite'
        })
      }
    },
  })

export default __sfc__
function GenPagesAPISetNavigationBarColorSetNavigationBarColorRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode(Fragment, null, [
    createVNode(_component_page_head, utsMapOf({ title: "setNavigationBarColor" })),
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap uni-common-mt" }), [
      createElementVNode("button", utsMapOf({
        onClick: _ctx.setNavigationBarColor1,
        class: "uni-btn"
      }), " 设置导航条背景绿色，标题白色 ", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        onClick: _ctx.setNavigationBarColor2,
        class: "uni-btn"
      }), " 设置导航条背景红色，标题黑色 ", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        onClick: _ctx.goNavbarLite,
        class: "uni-btn"
      }), " 跳转自定义导航栏页面 ", 8 /* PROPS */, ["onClick"])
    ])
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesAPISetNavigationBarColorSetNavigationBarColorStyles = []

import _easycom_page_head from '@/components/page-head/page-head.vue'
