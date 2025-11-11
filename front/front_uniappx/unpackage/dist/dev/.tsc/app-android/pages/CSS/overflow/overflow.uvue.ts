
  const __sfc__ = defineComponent({
    data() {
      return {

      }
    },
    methods: {

    }
  })

export default __sfc__
function GenPagesCSSOverflowOverflowRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("text", utsMapOf({
      style: normalizeStyle(utsMapOf({"font-size":"15px"}))
    }), "overflow=hidden效果子元素是view border圆角", 4 /* STYLE */),
    createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
      createElementVNode("view", utsMapOf({ class: "box-hidden-border-radius" }), [
        createElementVNode("view", utsMapOf({
          style: normalizeStyle(utsMapOf({"width":"50px","height":"150px","background-color":"greenyellow"}))
        }), null, 4 /* STYLE */)
      ])
    ]),
    createElementVNode("text", utsMapOf({
      style: normalizeStyle(utsMapOf({"font-size":"15px"}))
    }), "overflow=hidden效果 子元素是view border边框", 4 /* STYLE */),
    createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
      createElementVNode("view", utsMapOf({ class: "box-hidden-border-width" }), [
        createElementVNode("view", utsMapOf({
          style: normalizeStyle(utsMapOf({"width":"50px","height":"150px","background-color":"greenyellow"}))
        }), null, 4 /* STYLE */)
      ])
    ]),
    createElementVNode("text", utsMapOf({
      style: normalizeStyle(utsMapOf({"font-size":"15px"}))
    }), "overflow=visible效果 子元素是view border圆角", 4 /* STYLE */),
    createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
      createElementVNode("view", utsMapOf({ class: "box-visible-border-radius" }), [
        createElementVNode("view", utsMapOf({
          style: normalizeStyle(utsMapOf({"width":"50px","height":"150px","background-color":"greenyellow"}))
        }), null, 4 /* STYLE */)
      ])
    ]),
    createElementVNode("text", utsMapOf({
      style: normalizeStyle(utsMapOf({"font-size":"15px"}))
    }), "overflow=visible效果 子元素是view border边框", 4 /* STYLE */),
    createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
      createElementVNode("view", utsMapOf({ class: "box-visible-border-width" }), [
        createElementVNode("view", utsMapOf({
          style: normalizeStyle(utsMapOf({"width":"50px","height":"150px","background-color":"greenyellow"}))
        }), null, 4 /* STYLE */)
      ])
    ]),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"flex-grow":"1"}))
    }), [
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"font-size":"15px"}))
      }), "overflow=hidden效果 子元素是text", 4 /* STYLE */),
      createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
        createElementVNode("view", utsMapOf({ class: "box-hidden-border-radius" }), [
          createElementVNode("text", utsMapOf({ class: "text1" }), "ABCDEFG")
        ])
      ]),
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"font-size":"15px"}))
      }), "overflow=visible效果 子元素是text", 4 /* STYLE */),
      createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
        createElementVNode("view", utsMapOf({ class: "box-visible-border-radius" }), [
          createElementVNode("text", utsMapOf({ class: "text1" }), "ABCDEFG")
        ])
      ]),
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"font-size":"15px"}))
      }), "overflow=hidden效果 子元素是image", 4 /* STYLE */),
      createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
        createElementVNode("view", utsMapOf({ class: "box-hidden-border-radius" }), [
          createElementVNode("image", utsMapOf({
            style: normalizeStyle(utsMapOf({"width":"150px","height":"150px"})),
            src: "/static/uni.png"
          }), null, 4 /* STYLE */)
        ])
      ]),
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"font-size":"15px"}))
      }), "overflow=visible效果 子元素是image", 4 /* STYLE */),
      createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
        createElementVNode("view", utsMapOf({ class: "box-visible-border-radius" }), [
          createElementVNode("image", utsMapOf({
            style: normalizeStyle(utsMapOf({"width":"150px","height":"150px"})),
            src: "/static/uni.png"
          }), null, 4 /* STYLE */)
        ])
      ]),
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"font-size":"15px"}))
      }), "overflow=visible效果 子元素是view 父是scroll-view", 4 /* STYLE */),
      createElementVNode("scroll-view", utsMapOf({ class: "backgroundview" }), [
        createElementVNode("view", utsMapOf({ class: "box-visible-border-radius" }), [
          createElementVNode("view", utsMapOf({
            style: normalizeStyle(utsMapOf({"width":"50px","height":"150px","background-color":"greenyellow"}))
          }), null, 4 /* STYLE */)
        ])
      ]),
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"font-size":"15px"}))
      }), "overflow=visible 设置阴影显示文字", 4 /* STYLE */),
      createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
        createElementVNode("view", utsMapOf({
          class: "",
          style: normalizeStyle(utsMapOf({"overflow":"visible","width":"100px","height":"100px","background-color":"green","box-shadow":"10px 10px #000","margin-top":"10px"}))
        }), [
          createElementVNode("text", utsMapOf({
            style: normalizeStyle(utsMapOf({"width":"170px","background-color":"greenyellow"}))
          }), "文字文字文字文字文字", 4 /* STYLE */)
        ], 4 /* STYLE */)
      ])
    ], 4 /* STYLE */)
  ], 4 /* STYLE */)
}
const GenPagesCSSOverflowOverflowStyles = [utsMapOf([["backgroundview", padStyleMapOf(utsMapOf([["width", 300], ["height", 300], ["marginBottom", 20], ["backgroundColor", "#FFFFFF"], ["justifyContent", "center"], ["alignItems", "center"]]))], ["box-hidden-border-radius", padStyleMapOf(utsMapOf([["width", 100], ["height", 100], ["borderTopLeftRadius", 20], ["borderTopRightRadius", 20], ["borderBottomRightRadius", 20], ["borderBottomLeftRadius", 20], ["overflow", "hidden"], ["backgroundColor", "#008000"]]))], ["box-hidden-border-width", padStyleMapOf(utsMapOf([["width", 100], ["height", 100], ["borderTopWidth", 2], ["borderRightWidth", 2], ["borderBottomWidth", 2], ["borderLeftWidth", 2], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["backgroundColor", "#008000"]]))], ["box-visible-border-radius", padStyleMapOf(utsMapOf([["width", 100], ["height", 100], ["borderTopLeftRadius", 20], ["borderTopRightRadius", 20], ["borderBottomRightRadius", 20], ["borderBottomLeftRadius", 20], ["overflow", "visible"], ["backgroundColor", "#008000"]]))], ["box-visible-border-width", padStyleMapOf(utsMapOf([["width", 100], ["height", 100], ["borderTopWidth", 2], ["borderRightWidth", 2], ["borderBottomWidth", 2], ["borderLeftWidth", 2], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["overflow", "visible"], ["backgroundColor", "#008000"]]))], ["text1", padStyleMapOf(utsMapOf([["fontSize", 50], ["lines", 1]]))]])]
