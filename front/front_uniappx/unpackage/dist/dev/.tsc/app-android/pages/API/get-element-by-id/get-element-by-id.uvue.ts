
  const __sfc__ = defineComponent({
    data() {
      return {
        checked: false,
        homePagePath: '/pages/tabBar/component',
        launchOptionsPath: '',
      }
    },
    methods: {
      getElementByNotExistId() : Element | null {
        return uni.getElementById('not-exist-id')
      },
      changePageHeadBackgroundColor() {
        const pageHead = uni.getElementById('page-head')!
        pageHead.style.setProperty('background-color', 'red')
      },
      changeTextColor() {
        const text = uni.getElementById('text')!
        text.style.setProperty('color', 'red')
      },
      changeViewStyle() {
        const view = uni.getElementById<UniViewElement>('view')
        if (view !== null) {
          view.style.setProperty('width', '90%')
          view.style.setProperty('height', '50px')
          view.style.setProperty('background-color', '#007AFF')
        }
      },
      goMultipleRootNode() {
        uni.navigateTo({ url: '/pages/API/get-element-by-id/get-element-by-id-multiple-root-node' })
      },
      //自动化测试获取text元素的offsetLeft属性值
      getTextOffsetLeft() : number {
        const text = uni.getElementById('text')!
        return text.offsetLeft
      }
    }
  })

export default __sfc__
function GenPagesAPIGetElementByIdGetElementByIdRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode(Fragment, null, [
    createVNode(_component_page_head, utsMapOf({
      id: "page-head",
      title: "getElementById"
    })),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"margin":"0 15px"}))
    }), [
      createElementVNode("text", utsMapOf({ id: "text" }), "this is text"),
      createElementVNode("view", utsMapOf({
        id: "view",
        class: "uni-common-mt",
        style: normalizeStyle(utsMapOf({"border":"1px solid red"}))
      }), "this is view", 4 /* STYLE */),
      createElementVNode("button", utsMapOf({
        class: "uni-btn",
        onClick: _ctx.changePageHeadBackgroundColor
      }), " 修改 page-head 背景色 ", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "uni-btn",
        onClick: _ctx.changeTextColor
      }), " 修改 text 字体颜色 ", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "uni-btn",
        onClick: _ctx.changeViewStyle
      }), " 修改 view 宽高及背景色 ", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "uni-btn",
        onClick: _ctx.goMultipleRootNode
      }), " 跳转多根节点示例 ", 8 /* PROPS */, ["onClick"])
    ], 4 /* STYLE */)
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesAPIGetElementByIdGetElementByIdStyles = []

import _easycom_page_head from '@/components/page-head/page-head.vue'
