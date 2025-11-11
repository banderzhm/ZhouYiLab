
  const __sfc__ = defineComponent({
    data() {
      return {
        uniIcon: '\ue100',
      }
    },
    methods: {
      openUniIcon() {
        uni.navigateTo({
          url: '/pages/CSS/text/font-family-icon'
        })
      }
    }
  })

export default __sfc__
function GenPagesCSSTextFontFamilyRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode(Fragment, null, [
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
      createElementVNode("text", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({"font-family":"monospace"}))
      }), "font-family: monospace", 4 /* STYLE */),
      createElementVNode("text", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({"font-family":"cursive"}))
      }), "font-family: cursive", 4 /* STYLE */),
      createElementVNode("text", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({"font-family":"AlimamaDaoLiTiTTF"}))
      }), "font-family: 阿里妈妈刀隶体-ttf（网络字体下载后生效）", 4 /* STYLE */),
      createElementVNode("text", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({"font-family":"AlimamaDaoLiTiOTF"}))
      }), "font-family: 阿里妈妈刀隶体-otf", 4 /* STYLE */),
      createElementVNode("text", utsMapOf({
        class: "common",
        style: normalizeStyle(utsMapOf({"font-family":"AlimamaDaoLiTiOTF","font-weight":"bold","font-style":"italic"}))
      }), "font-family: 阿里妈妈刀隶体-otf（粗斜体）", 4 /* STYLE */),
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"font-family":"UniFontFamily"}))
      }), "style 加载非 static 目录字体文件：" + toDisplayString(_ctx.uniIcon), 5 /* TEXT, STYLE */)
    ]),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"margin":"24px 12px"}))
    }), [
      createElementVNode("button", utsMapOf({
        type: "default",
        onClick: _ctx.openUniIcon
      }), "内置字体图标uni-icon示例", 8 /* PROPS */, ["onClick"])
    ], 4 /* STYLE */)
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesCSSTextFontFamilyStyles = [utsMapOf([["common", padStyleMapOf(utsMapOf([["fontSize", 20], ["lineHeight", "40px"]]))], ["@FONT-FACE", utsMapOf([["0", utsMapOf([["fontFamily", "AlimamaDaoLiTiTTF"], ["src", "url('https://qiniu-web-assets.dcloud.net.cn/uni-app-x/static/font/AlimamaDaoLiTi.ttf')"]])], ["1", utsMapOf([["fontFamily", "AlimamaDaoLiTiOTF"], ["src", "url('/static/font/AlimamaDaoLiTi.otf')"]])], ["2", utsMapOf([["fontFamily", "UniFontFamily"], ["src", "url('/assets/uni.173d2b94.ttf')"]])]])]])]
