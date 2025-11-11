

import { openSchema } from '@/uni_modules/uts-openSchema'

import { state } from '@/store/index.uts'
const __sfc__ = defineComponent({
  data() {
    return {
      version: '',
      arrowRightIcon: '/static/icons/arrow-right.png'
    }
  },
  onLoad() {
    this.version = uni.getAppBaseInfo().appVersion??''
  },
  computed: {
    isDarkMode() : boolean {
      return state.isDarkMode
    },
  },
  methods: {
    openUrl(url:string) {

      openSchema(url)









    }
  }
})

export default __sfc__
function GenPagesTemplateAboutAboutRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"})),
    class: normalizeClass(_ctx.isDarkMode ? 'theme-dark' : 'theme-light')
  }), [
    createElementVNode("view", utsMapOf({ class: "content" }), [
      createElementVNode("image", utsMapOf({
        class: "qrcode",
        src: "https://web-ext-storage.dcloud.net.cn/uni-app-x/hello-uniappx-qrcode.png"
      })),
      createElementVNode("text", utsMapOf({ class: "tip" }), "扫码体验 uni-app x"),
      createElementVNode("text", utsMapOf({ class: "desc" }), [
        createElementVNode("text", utsMapOf({ class: "code" }), "uni-app x "),
        createElementVNode("text", utsMapOf({ class: "desc" }), "是下一代uni-app，基于uts的、兼容vue语法的跨平台开发框架。")
      ]),
      createElementVNode("text", utsMapOf({ class: "source desc" }), "本应用源码获取方式："),
      createElementVNode("view", utsMapOf({ class: "sourcelist" }), [
        createElementVNode("text", utsMapOf({
          class: "desc",
          space: "nbsp"
        }), "1. "),
        createElementVNode("text", utsMapOf({ class: "desc" }), [
          createElementVNode("text", utsMapOf({ class: "desc" }), "在 HBuilderX 中新建 uni-app 项目时选择 "),
          createElementVNode("text", utsMapOf({ class: "code" }), "Hello uni-app x"),
          createElementVNode("text", utsMapOf({ class: "desc" }), " 模板")
        ])
      ]),
      createElementVNode("view", utsMapOf({ class: "sourcelist" }), [
        createElementVNode("text", utsMapOf({
          class: "desc",
          space: "nbsp"
        }), "2. "),
        createElementVNode("text", utsMapOf({
          class: "desc link",
          onClick: () => {_ctx.openUrl('https://gitcode.com/dcloud/hello-uni-app-x')}
        }), "https://gitcode.com/dcloud/hello-uni-app-x", 8 /* PROPS */, ["onClick"])
      ])
    ]),
    createElementVNode("view", utsMapOf({ class: "panel" }), [
      createElementVNode("text", utsMapOf({ class: "uni-panel-text title" }), "服务协议"),
      createElementVNode("view", utsMapOf({ class: "item" }), [
        createElementVNode("view", utsMapOf({
          style: normalizeStyle(utsMapOf({"padding-left":"18px","border-top-style":"none"})),
          class: "uni-navigate-item",
          "hover-class": "is--active",
          onClick: () => {_ctx.openUrl('https://dcloud.io/license/DCloud.html')}
        }), [
          createElementVNode("text", utsMapOf({ class: "uni-navigate-text" }), "用户协议"),
          createElementVNode("image", utsMapOf({
            src: _ctx.arrowRightIcon,
            class: "uni-icon-size"
          }), null, 8 /* PROPS */, ["src"])
        ], 12 /* STYLE, PROPS */, ["onClick"]),
        createElementVNode("view", utsMapOf({
          style: normalizeStyle(utsMapOf({"padding-left":"18px"})),
          class: "uni-navigate-item",
          "hover-class": "is--active",
          onClick: () => {_ctx.openUrl('https://dcloud.io/license/hello-uni-app-x.html')}
        }), [
          createElementVNode("text", utsMapOf({ class: "uni-navigate-text" }), "隐私政策"),
          createElementVNode("image", utsMapOf({
            src: _ctx.arrowRightIcon,
            class: "uni-icon-size"
          }), null, 8 /* PROPS */, ["src"])
        ], 12 /* STYLE, PROPS */, ["onClick"])
      ])
    ]),
    createElementVNode("text", utsMapOf({ class: "version" }), "当前版本：" + toDisplayString(_ctx.version), 1 /* TEXT */)
  ], 6 /* CLASS, STYLE */)
}
const GenPagesTemplateAboutAboutStyles = [utsMapOf([["content", padStyleMapOf(utsMapOf([["width", "100%"], ["paddingTop", 16], ["paddingRight", 16], ["paddingBottom", 16], ["paddingLeft", 16], ["alignItems", "center"]]))], ["qrcode", padStyleMapOf(utsMapOf([["width", 160], ["height", 160]]))], ["tip", padStyleMapOf(utsMapOf([["marginTop", 12], ["marginRight", 12], ["marginBottom", 12], ["marginLeft", 12], ["fontSize", 14], ["color", "var(--text-color)"]]))], ["desc", padStyleMapOf(utsMapOf([["fontSize", 14], ["lineHeight", "18px"], ["color", "var(--text-color)"]]))], ["code", padStyleMapOf(utsMapOf([["fontSize", 14], ["color", "#e96900"]]))], ["source", padStyleMapOf(utsMapOf([["alignSelf", "flex-start"], ["marginTop", 18], ["color", "var(--text-color)"]]))], ["sourcelist", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["alignSelf", "flex-start"], ["alignContent", "flex-start"]]))], ["link", padStyleMapOf(utsMapOf([["color", "#007AFF"], ["textDecorationLine", "underline"]]))], ["version", padStyleMapOf(utsMapOf([["width", "100%"], ["marginTop", 18], ["marginRight", 0], ["marginBottom", 18], ["marginLeft", 0], ["fontSize", 14], ["lineHeight", "18px"], ["textAlign", "center"], ["color", "#cccccc"]]))], ["panel", padStyleMapOf(utsMapOf([["marginTop", 18], ["marginRight", 18], ["marginBottom", 18], ["marginLeft", 18]]))], ["title", padStyleMapOf(utsMapOf([["marginTop", 8], ["marginRight", 8], ["marginBottom", 8], ["marginLeft", 8], ["fontSize", 14]]))], ["item", padStyleMapOf(utsMapOf([["borderTopLeftRadius", 6], ["borderTopRightRadius", 6], ["borderBottomRightRadius", 6], ["borderBottomLeftRadius", 6]]))]])]
