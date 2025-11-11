
  const __sfc__ = defineComponent({
    data() {
      return {
        uniIcon1: '\ue100',
        uniIcon2: '\ue101',
      }
    }
  })

export default __sfc__
function GenPagesAPILoadFontFaceLoadFontFaceChildRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode(Fragment, null, [
    createVNode(_component_page_head, utsMapOf({ title: "loadFontFace-child" })),
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
      createElementVNode("text", utsMapOf({ class: "font-size-20" }), "上一页全局加载字体："),
      createElementVNode("text", utsMapOf({
        class: "font-size-20 line-height-40",
        style: normalizeStyle(utsMapOf({"font-family":"UniFontFamily"}))
      }), "font-family: uni.ttf", 4 /* STYLE */),
      createElementVNode("view", utsMapOf({
        style: normalizeStyle(utsMapOf({"flex-direction":"row"}))
      }), [
        createElementVNode("text", utsMapOf({
          style: normalizeStyle(utsMapOf({"font-family":"UniFontFamily","font-size":"20px"}))
        }), toDisplayString(_ctx.uniIcon1), 5 /* TEXT, STYLE */),
        createElementVNode("text", utsMapOf({
          style: normalizeStyle(utsMapOf({"margin-left":"5px","margin-right":"20px","line-height":"22px"}))
        }), "\\ue100", 4 /* STYLE */),
        createElementVNode("text", utsMapOf({
          style: normalizeStyle(utsMapOf({"font-family":"UniFontFamily","font-size":"20px"}))
        }), toDisplayString(_ctx.uniIcon2), 5 /* TEXT, STYLE */),
        createElementVNode("text", utsMapOf({
          style: normalizeStyle(utsMapOf({"margin-left":"5px","line-height":"22px"}))
        }), "\\ue101", 4 /* STYLE */)
      ], 4 /* STYLE */),
      createElementVNode("text", utsMapOf({ class: "uni-common-mt font-size-20" }), "上一页非全局加载字体（不生效为正常）："),
      createElementVNode("text", utsMapOf({
        class: "font-size-20 line-height-40",
        style: normalizeStyle(utsMapOf({"font-family":"AlimamaDaoLiTiTTF"}))
      }), "font-family: 阿里妈妈刀隶体-ttf", 4 /* STYLE */),
      createElementVNode("text", utsMapOf({
        class: "font-size-20 line-height-40",
        style: normalizeStyle(utsMapOf({"font-family":"AlimamaDaoLiTiOTF"}))
      }), "font-family: 阿里妈妈刀隶体-otf", 4 /* STYLE */)
    ])
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesAPILoadFontFaceLoadFontFaceChildStyles = [utsMapOf([["font-size-20", padStyleMapOf(utsMapOf([["fontSize", 20]]))], ["line-height-40", padStyleMapOf(utsMapOf([["lineHeight", "40px"]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
