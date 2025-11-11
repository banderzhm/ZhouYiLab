
  const __sfc__ = defineComponent({
    data() {
      return {
        uniIcon1: '\ue100',
        uniIcon2: '\ue101',
        uniIcon3: '\ue102',
        successTriggeredNum: 0,
      }
    },
  onLoad() {
      const self = this
      uni.loadFontFace({
        global: true,
        family: 'UniFontFamily',
        source: "url('/static/font/uni.ttf')",
        success() {
          self.successTriggeredNum++;
          console.log('global loadFontFace uni.ttf success', " at pages/API/load-font-face/load-font-face.uvue:54")
        },
        fail(error) {
          console.warn('global loadFontFace uni.ttf fail', error.errMsg, " at pages/API/load-font-face/load-font-face.uvue:57")
        },
      })
      uni.loadFontFace({
        family: 'UniFontFamily2',
        source: "url(data:font/ttf;charset=utf-8;base64,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)",
        success() {
          self.successTriggeredNum++;
          console.log('loadFontFace uni.ttf(base64 format) success', " at pages/API/load-font-face/load-font-face.uvue:65")
        },
        fail(error) {
          console.warn('loadFontFace uni.ttf(base64 format) fail', error.errMsg, " at pages/API/load-font-face/load-font-face.uvue:68")
        },
      })
      uni.loadFontFace({
        family: 'AlimamaDaoLiTiTTF',
        source:
          "url('https://qiniu-web-assets.dcloud.net.cn/uni-app-x/static/font/AlimamaDaoLiTi.ttf')",
        success() {
          self.successTriggeredNum++;
          console.log('loadFontFace Remote AlimamaDaoLiTi.ttf success', " at pages/API/load-font-face/load-font-face.uvue:77")
        },
        fail(error) {
          console.warn('loadFontFace Remote AlimamaDaoLiTi.ttf fail', error.errMsg, " at pages/API/load-font-face/load-font-face.uvue:80")
        },
      })
      uni.loadFontFace({
        family: 'AlimamaDaoLiTiOTF',
        source: "url('/static/font/AlimamaDaoLiTi.otf')",
        success() {
          self.successTriggeredNum++;
          console.log('loadFontFace AlimamaDaoLiTi.otf success', " at pages/API/load-font-face/load-font-face.uvue:88")
        },
        fail(error) {
          console.warn('loadFontFace AlimamaDaoLiTi.otf fail', error.errMsg, " at pages/API/load-font-face/load-font-face.uvue:91")
        },
      })
      uni.loadFontFace({
        family: 'AlimamaDaoLiTiWOFF',
        source: "url('/static/font/AlimamaDaoLiTi.woff')",
        success() {
          self.successTriggeredNum++;
          console.log('loadFontFace AlimamaDaoLiTi.woff success', " at pages/API/load-font-face/load-font-face.uvue:99")
        },
        fail(error) {
          console.warn('loadFontFace AlimamaDaoLiTi.woff fail', error.errMsg, " at pages/API/load-font-face/load-font-face.uvue:102")
        },
      })
      uni.loadFontFace({
        family: 'AlimamaDaoLiTiWOFF2',
        source: "url('/static/font/AlimamaDaoLiTi.woff2')",
        success() {
          self.successTriggeredNum++;
          console.log('loadFontFace AlimamaDaoLiTi.woff2 success', " at pages/API/load-font-face/load-font-face.uvue:110")
        },
        fail(error) {
          console.warn('loadFontFace AlimamaDaoLiTi.woff2 fail', error.errMsg, " at pages/API/load-font-face/load-font-face.uvue:113")
        },
      })
    },
    methods: {
      navigateToChild() {
        uni.navigateTo({
          url: '/pages/API/load-font-face/load-font-face-child',
        })
      },
    },
  })

export default __sfc__
function GenPagesAPILoadFontFaceLoadFontFaceRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode(Fragment, null, [
    createVNode(_component_page_head, utsMapOf({ title: "loadFontFace" })),
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
      createElementVNode("text", utsMapOf({ class: "font-size-20" }), "全局加载字体："),
      createElementVNode("text", utsMapOf({
        class: "font-size-20 line-height-40",
        style: normalizeStyle(utsMapOf({"font-family":"UniFontFamily"}))
      }), "font-family: uni.ttf", 4 /* STYLE */),
      createElementVNode("view", utsMapOf({
        style: normalizeStyle(utsMapOf({"flex-direction":"row"}))
      }), [
        createElementVNode("text", utsMapOf({
          class: "font-size-20",
          style: normalizeStyle(utsMapOf({"font-family":"UniFontFamily"}))
        }), toDisplayString(_ctx.uniIcon1), 5 /* TEXT, STYLE */),
        createElementVNode("text", utsMapOf({ class: "icon-text" }), "\\ue100"),
        createElementVNode("text", utsMapOf({
          class: "font-size-20",
          style: normalizeStyle(utsMapOf({"font-family":"UniFontFamily"}))
        }), toDisplayString(_ctx.uniIcon2), 5 /* TEXT, STYLE */),
        createElementVNode("text", utsMapOf({
          style: normalizeStyle(utsMapOf({"margin-left":"5px","line-height":"22px"}))
        }), "\\ue101", 4 /* STYLE */)
      ], 4 /* STYLE */),
      createElementVNode("text", utsMapOf({ class: "uni-common-mt font-size-20" }), "非全局加载字体："),
      createElementVNode("text", utsMapOf({ class: "font-size-20 line-height-40" }), "font-family: uni.ttf(base64格式)"),
      createElementVNode("view", utsMapOf({
        style: normalizeStyle(utsMapOf({"flex-direction":"row"}))
      }), [
        createElementVNode("text", utsMapOf({
          class: "font-size-20",
          style: normalizeStyle(utsMapOf({"font-family":"UniFontFamily2"}))
        }), toDisplayString(_ctx.uniIcon3), 5 /* TEXT, STYLE */),
        createElementVNode("text", utsMapOf({ class: "icon-text" }), "\\ue102")
      ], 4 /* STYLE */),
      createElementVNode("text", utsMapOf({
        class: "font-size-20 line-height-40",
        style: normalizeStyle(utsMapOf({"font-family":"AlimamaDaoLiTiTTF"}))
      }), "font-family: 阿里妈妈刀隶体-ttf (网络字体下载后生效)", 4 /* STYLE */),
      createElementVNode("text", utsMapOf({
        class: "font-size-20 line-height-40",
        style: normalizeStyle(utsMapOf({"font-family":"AlimamaDaoLiTiOTF"}))
      }), "font-family: 阿里妈妈刀隶体-otf", 4 /* STYLE */),
      createElementVNode("text", utsMapOf({
        class: "item",
        style: normalizeStyle(utsMapOf({"font-family":"AlimamaDaoLiTiWOFF"}))
      }), "font-family: 阿里妈妈刀隶体-woff", 4 /* STYLE */),
      createElementVNode("text", utsMapOf({
        class: "item",
        style: normalizeStyle(utsMapOf({"font-family":"AlimamaDaoLiTiWOFF2"}))
      }), "font-family: 阿里妈妈刀隶体-woff2", 4 /* STYLE */),
      createElementVNode("button", utsMapOf({
        class: "uni-btn",
        onClick: _ctx.navigateToChild
      }), "跳转子页面测试字体生效范围", 8 /* PROPS */, ["onClick"])
    ])
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesAPILoadFontFaceLoadFontFaceStyles = [utsMapOf([["font-size-20", padStyleMapOf(utsMapOf([["fontSize", 20]]))], ["line-height-40", padStyleMapOf(utsMapOf([["lineHeight", "40px"]]))], ["icon-text", padStyleMapOf(utsMapOf([["marginLeft", 5], ["marginRight", 20], ["lineHeight", "22px"]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
