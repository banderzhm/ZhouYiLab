
  const __sfc__ = defineComponent({
    data() {
      return {
        title: 'image-large',
        src: 'https://qiniu-web-assets.dcloud.net.cn/uni-app-x/static/img/building.jpg',
        width: 0,
        height: 0,
        memory: 0
      }
    },
    methods: {
      load(event : ImageLoadEvent) {
        this.width = event.detail.width;
        this.height = event.detail.height;
        this.memory = Math.round(this.width * this.height * 4 / 1024 / 1024 * 100) / 100;
      }
    }
  })

export default __sfc__
function GenPagesComponentImageImageLargeRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap uni-common-mt" }), [
      createElementVNode("view", utsMapOf({ class: "uni-title" }), [
        createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "大图示例")
      ]),
      createElementVNode(Fragment, null, RenderHelpers.renderList(10, (_, __key, __index, _cached): any => {
        return createElementVNode("view", utsMapOf({ class: "uni-common-mt" }), [
          createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "width=" + toDisplayString(_ctx.width) + "px height=" + toDisplayString(_ctx.height) + "px memory=" + toDisplayString(_ctx.memory) + "MB", 1 /* TEXT */),
          createElementVNode("image", utsMapOf({
            class: "image",
            mode: "widthFix",
            src: _ctx.src,
            onLoad: _ctx.load
          }), null, 40 /* PROPS, NEED_HYDRATION */, ["src", "onLoad"])
        ])
      }), 64 /* STABLE_FRAGMENT */)
    ])
  ], 4 /* STYLE */)
}
const GenPagesComponentImageImageLargeStyles = [utsMapOf([["image", padStyleMapOf(utsMapOf([["width", "100%"]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
