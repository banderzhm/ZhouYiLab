const __sfc__ = defineComponent({})
export default __sfc__
function GenPagesCSSFunctionUrlRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({ class: "container" }), [
    createElementVNode("text", utsMapOf({ class: "intro-text" }), "url() 函数用于加载图片、字体等资源，支持 /static 目录下的本地文件和网络链接。"),
    createElementVNode("view", utsMapOf({ class: "section" }), [
      createElementVNode("text", utsMapOf({ class: "section-title" }), "字体示例"),
      createElementVNode("view", utsMapOf({ class: "font-grid" }), [
        createElementVNode("view", utsMapOf({ class: "font-item" }), [
          createElementVNode("text", utsMapOf({ class: "custom-font" }), "阿里妈妈刀隶体"),
          createElementVNode("text", utsMapOf({ class: "font-value" }), "@font-face { font-family: 'AlimamaDaoLiTi'; src: url('/static/font/AlimamaDaoLiTi.woff2') }")
        ]),
        createElementVNode("view", utsMapOf({ class: "font-item" }), [
          createElementVNode("text", utsMapOf({ class: "pacifico-font" }), "Pacifico Font Example"),
          createElementVNode("text", utsMapOf({ class: "font-value" }), "@font-face { font-family: 'Pacifico'; src: url('/static/font/Pacifico-Regular.ttf') }")
        ]),
        createElementVNode("view", utsMapOf({ class: "font-item" }), [
          createElementVNode("text", utsMapOf({ class: "web-font" }), "网络字体"),
          createElementVNode("text", utsMapOf({ class: "font-value" }), "@font-face { font-family: 'AlimamaDaoLiTiTTF'; src: url('https://qiniu-web-assets.dcloud.net.cn/uni-app-x/static/font/AlimamaDaoLiTi.ttf') }")
        ])
      ])
    ])
  ])
}
const GenPagesCSSFunctionUrlStyles = [utsMapOf([["container", padStyleMapOf(utsMapOf([["paddingTop", 20], ["paddingRight", 20], ["paddingBottom", 20], ["paddingLeft", 20]]))], ["intro-text", padStyleMapOf(utsMapOf([["fontSize", 14], ["color", "#333333"], ["marginBottom", 30], ["lineHeight", 1.6], ["paddingTop", 15], ["paddingRight", 15], ["paddingBottom", 15], ["paddingLeft", 15], ["backgroundColor", "#f8f9fa"], ["borderTopLeftRadius", 8], ["borderTopRightRadius", 8], ["borderBottomRightRadius", 8], ["borderBottomLeftRadius", 8]]))], ["section", padStyleMapOf(utsMapOf([["marginBottom", 30]]))], ["section-title", padStyleMapOf(utsMapOf([["fontSize", 18], ["fontWeight", "bold"], ["marginBottom", 15]]))], ["font-grid", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["flexWrap", "wrap"]]))], ["font-item", padStyleMapOf(utsMapOf([["width", "100%"], ["marginBottom", 20]]))], ["custom-font", padStyleMapOf(utsMapOf([["fontSize", 20], ["marginBottom", 8], ["fontFamily", "AlimamaDaoLiTi"]]))], ["pacifico-font", padStyleMapOf(utsMapOf([["fontSize", 20], ["marginBottom", 8], ["fontFamily", "Pacifico"]]))], ["web-font", padStyleMapOf(utsMapOf([["fontSize", 20], ["marginBottom", 8], ["fontFamily", "AlimamaDaoLiTiTTF"]]))], ["font-value", padStyleMapOf(utsMapOf([["fontSize", 12], ["color", "#999999"]]))], ["@FONT-FACE", utsMapOf([["0", utsMapOf([["fontFamily", "AlimamaDaoLiTi"], ["src", "url('/static/font/AlimamaDaoLiTi.woff2'),\n\t\t\t url('/static/font/AlimamaDaoLiTi.woff'),\n\t\t\t url('/static/font/AlimamaDaoLiTi.otf')"]])], ["1", utsMapOf([["fontFamily", "Pacifico"], ["src", "url('/static/font/Pacifico-Regular.ttf')"]])], ["2", utsMapOf([["fontFamily", "AlimamaDaoLiTiTTF"], ["src", "url('https://qiniu-web-assets.dcloud.net.cn/uni-app-x/static/font/AlimamaDaoLiTi.ttf')"]])]])]])]
