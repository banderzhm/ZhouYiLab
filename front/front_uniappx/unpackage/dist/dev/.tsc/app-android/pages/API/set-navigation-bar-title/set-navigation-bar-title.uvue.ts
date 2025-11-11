
  import { state, setLifeCycleNum } from '@/store/index.uts'

  const __sfc__ = defineComponent({
    data() {
      return {
        newTitle: 'new title',
        longTitle: 'long title long title long title long title long title long title long title long title long title long title'
      }
    },
    methods: {




























      setNavigationBarNewTitle() {
        uni.setNavigationBarTitle({
          title: this.newTitle,
          success: () => {
            console.log('setNavigationBarTitle success', " at pages/API/set-navigation-bar-title/set-navigation-bar-title.uvue:64")
            this.setLifeCycleNum(state.lifeCycleNum + 1)
          },
          fail: () => {
            console.log('setNavigationBarTitle fail', " at pages/API/set-navigation-bar-title/set-navigation-bar-title.uvue:68")
            this.setLifeCycleNum(state.lifeCycleNum - 1)
          },
          complete: () => {
            console.log('setNavigationBarTitle complete', " at pages/API/set-navigation-bar-title/set-navigation-bar-title.uvue:72")
            this.setLifeCycleNum(state.lifeCycleNum + 1)
          }
        })
      },
      setNavigationBarLongTitle() {
        uni.setNavigationBarTitle({
          title: this.longTitle,
          success() {
            console.log('setNavigationBarTitle success', " at pages/API/set-navigation-bar-title/set-navigation-bar-title.uvue:81")
          },
          fail() {
            console.log('setNavigationBarTitle fail', " at pages/API/set-navigation-bar-title/set-navigation-bar-title.uvue:84")
          },
          complete() {
            console.log('setNavigationBarTitle complete', " at pages/API/set-navigation-bar-title/set-navigation-bar-title.uvue:87")
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
      }
    },
  })

export default __sfc__
function GenPagesAPISetNavigationBarTitleSetNavigationBarTitleRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode(Fragment, null, [
    createVNode(_component_page_head, utsMapOf({ title: "setNavigationBarTitle" })),
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap uni-common-mt" }), [
      createElementVNode("button", utsMapOf({
        onClick: _ctx.setNavigationBarNewTitle,
        class: "uni-btn"
      }), " 设置当前页面标题为: " + toDisplayString(_ctx.newTitle), 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        onClick: _ctx.setNavigationBarLongTitle,
        class: "uni-btn"
      }), " 设置超长标题 ", 8 /* PROPS */, ["onClick"])
    ])
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesAPISetNavigationBarTitleSetNavigationBarTitleStyles = []

import _easycom_page_head from '@/components/page-head/page-head.vue'
