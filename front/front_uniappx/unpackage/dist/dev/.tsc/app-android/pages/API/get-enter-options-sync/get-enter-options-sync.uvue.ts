
  const __sfc__ = defineComponent({
    data() {
      return {
        enterOptionsString: '',
        testResult: false
      }
    },
    onReady() {
      const app = getApp()
      const appOnShow = app.globalData.onShowOption
      const onShowOption = uni.getEnterOptionsSync()
      this.enterOptionsString = JSON.stringify(onShowOption, null, 2)
      this.testResult = (onShowOption.path == appOnShow.path && onShowOption.appScheme == appOnShow.appScheme && onShowOption.appLink == appOnShow.appLink)
    }
  })

export default __sfc__
function GenPagesAPIGetEnterOptionsSyncGetEnterOptionsSyncRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode(Fragment, null, [
    createVNode(_component_page_head, utsMapOf({ title: "getEnterOptionsSync" })),
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
      createElementVNode("view", utsMapOf({ class: "uni-common-mt" }), [
        createElementVNode("text", null, "应用本次启动路径："),
        createElementVNode("text", utsMapOf({
          style: normalizeStyle(utsMapOf({"margin-top":"5px"}))
        }), toDisplayString(_ctx.enterOptionsString), 5 /* TEXT, STYLE */)
      ])
    ])
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesAPIGetEnterOptionsSyncGetEnterOptionsSyncStyles = []

import _easycom_page_head from '@/components/page-head/page-head.vue'
