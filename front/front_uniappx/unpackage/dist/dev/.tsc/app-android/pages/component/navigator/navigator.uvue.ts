
  const __sfc__ = defineComponent({
    data() {
      return {
        title: 'navigator'
      }
    }
  })

export default __sfc__
function GenPagesComponentNavigatorNavigatorRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)
const _component_navigator = resolveComponent("navigator", true)

  return createElementVNode(Fragment, null, [
    createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap uni-common-mt" }), [
      createVNode(_component_navigator, utsMapOf({
        id: "navigate",
        class: "navigator",
        url: "navigate?title=navigate"
      }), utsMapOf({
        default: withSlotCtx((): any[] => [
          createElementVNode("button", utsMapOf({ type: "default" }), "跳转到新页面")
        ]),
        _: 1 /* STABLE */
      })),
      createVNode(_component_navigator, utsMapOf({
        id: "redirect",
        class: "navigator",
        url: "redirect?title=redirect",
        "open-type": "redirect"
      }), utsMapOf({
        default: withSlotCtx((): any[] => [
          createElementVNode("button", utsMapOf({ type: "default" }), "在当前页打开redirect")
        ]),
        _: 1 /* STABLE */
      })),
      createVNode(_component_navigator, utsMapOf({
        id: "switchTab",
        class: "navigator",
        url: "/pages/tabBar/template",
        "open-type": "switchTab"
      }), utsMapOf({
        default: withSlotCtx((): any[] => [
          createElementVNode("button", utsMapOf({ type: "default" }), "切换到模板选项卡switchTab")
        ]),
        _: 1 /* STABLE */
      })),
      createVNode(_component_navigator, utsMapOf({
        id: "reLaunch",
        class: "navigator",
        url: "/pages/tabBar/component",
        "open-type": "reLaunch"
      }), utsMapOf({
        default: withSlotCtx((): any[] => [
          createElementVNode("button", utsMapOf({ type: "default" }), "关闭所有页面回首页reLaunch")
        ]),
        _: 1 /* STABLE */
      })),
      createVNode(_component_navigator, utsMapOf({
        id: "reLaunch",
        class: "navigator",
        "open-type": "navigateBack"
      }), utsMapOf({
        default: withSlotCtx((): any[] => [
          createElementVNode("button", utsMapOf({ type: "default" }), "返回上一页navigateBack")
        ]),
        _: 1 /* STABLE */
      })),
      createVNode(_component_navigator, utsMapOf({
        id: "navigateToErrorPage",
        class: "navigator",
        url: "/pages/error-page/error-page"
      }), utsMapOf({
        default: withSlotCtx((): any[] => [
          createElementVNode("button", utsMapOf({ type: "default" }), " 打开不存在的页面 ")
        ]),
        _: 1 /* STABLE */
      })),
      createVNode(_component_navigator, utsMapOf({
        id: "navigateToErrorPage",
        class: "navigator"
      }), utsMapOf({
        default: withSlotCtx((): any[] => [
          createElementVNode("button", utsMapOf({ type: "default" }), " 未指定URL的跳转 ")
        ]),
        _: 1 /* STABLE */
      })),
      createVNode(_component_navigator, utsMapOf({
        style: normalizeStyle(utsMapOf({"flex-direction":"row","justify-content":"space-between"}))
      }), utsMapOf({
        default: withSlotCtx((): any[] => [
          createElementVNode("text", null, "两端对齐样式测试"),
          createElementVNode("view", utsMapOf({
            style: normalizeStyle(utsMapOf({"width":"20px","height":"20px","background-color":"aqua"}))
          }), null, 4 /* STYLE */)
        ]),
        _: 1 /* STABLE */
      }), 8 /* PROPS */, ["style"])
    ])
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesComponentNavigatorNavigatorStyles = [utsMapOf([["navigator", padStyleMapOf(utsMapOf([["marginBottom", 15]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
