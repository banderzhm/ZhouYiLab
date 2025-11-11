
  const __sfc__ = defineComponent({
    data() {
      return {
        disabled: false
      }
    },
    onReady() {
      this.$nextTick(() => {
        let element = uni.getElementById("dynamic-height-textarea")
        element?.style.setProperty("height", "52px")
      })
    },
    methods: {
      changed() {
        this.disabled = !this.disabled
      }
    },
  })

export default __sfc__
function GenPagesCSSBoxShadowBoxShadowRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"flex-grow":"1"}))
    }), [
      createElementVNode("view", null, [
        createElementVNode("text", null, "box-shadow: 5px 5px black"),
        createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
          createElementVNode("view", utsMapOf({
            class: "common",
            style: normalizeStyle(utsMapOf({"box-shadow":"5px 5px black"}))
          }), null, 4 /* STYLE */)
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "box-shadow: 5px 5px 5px #00FF00"),
        createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
          createElementVNode("view", utsMapOf({
            class: "common",
            style: normalizeStyle(utsMapOf({"box-shadow":"5px 5px 5px #00FF00"}))
          }), null, 4 /* STYLE */)
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "box-shadow: 5px 5px 5px rgb(0,0,255)"),
        createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
          createElementVNode("view", utsMapOf({
            class: "common",
            style: normalizeStyle(utsMapOf({"box-shadow":"5px 5px 5px rgb(0,0,255)"}))
          }), null, 4 /* STYLE */)
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "box-shadow: 5px 5px 5px rgba(0,255,255,0.5)"),
        createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
          createElementVNode("view", utsMapOf({
            class: "common",
            style: normalizeStyle(utsMapOf({"box-shadow":"5px 5px 5px rgba(0,255,255,0.5)"}))
          }), null, 4 /* STYLE */)
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.5)"),
        createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
          createElementVNode("view", utsMapOf({
            class: "common",
            style: normalizeStyle(utsMapOf({"box-shadow":"5px 5px 5px rgba(0, 0, 0, 0.5)"}))
          }), null, 4 /* STYLE */)
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "box-shadow: 5px 5px 5px black"),
        createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
          createElementVNode("view", utsMapOf({
            class: "common",
            style: normalizeStyle(utsMapOf({"box-shadow":"5px 5px 5px black"}))
          }), null, 4 /* STYLE */)
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "box-shadow: 5px 10px 5px black"),
        createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
          createElementVNode("view", utsMapOf({
            class: "common",
            style: normalizeStyle(utsMapOf({"box-shadow":"5px 10px 5px black"}))
          }), null, 4 /* STYLE */)
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "box-shadow: 5px 5px 5px 5px black（harmony暂不支持阴影扩散半径）"),
        createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
          createElementVNode("view", utsMapOf({
            class: "common",
            style: normalizeStyle(utsMapOf({"box-shadow":"5px 5px 5px 5px black"}))
          }), null, 4 /* STYLE */)
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "box-shadow: -5px -5px 5px black"),
        createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
          createElementVNode("view", utsMapOf({
            class: "common",
            style: normalizeStyle(utsMapOf({"box-shadow":"-5px -5px 5px black"}))
          }), null, 4 /* STYLE */)
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "box-shadow: inset 5px 5px black（harmony暂不支持inset）"),
        createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
          createElementVNode("view", utsMapOf({
            class: "common",
            style: normalizeStyle(utsMapOf({"box-shadow":"inset 5px 5px black"}))
          }), null, 4 /* STYLE */)
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "box-shadow: inset 5px 5px 5px black（harmony暂不支持inset）"),
        createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
          createElementVNode("view", utsMapOf({
            class: "common",
            style: normalizeStyle(utsMapOf({"box-shadow":"inset 5px 5px 5px black"}))
          }), null, 4 /* STYLE */)
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "box-shadow: inset 5px 10px 5px black（harmony暂不支持inset）"),
        createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
          createElementVNode("view", utsMapOf({
            class: "common",
            style: normalizeStyle(utsMapOf({"box-shadow":"inset 5px 10px 5px black"}))
          }), null, 4 /* STYLE */)
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "box-shadow: inset 5px 5px 5px 5px black（harmony暂不支持inset）"),
        createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
          createElementVNode("view", utsMapOf({
            class: "common",
            style: normalizeStyle(utsMapOf({"box-shadow":"inset 5px 5px 5px 5px black"}))
          }), null, 4 /* STYLE */)
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "box-shadow: inset -5px -5px 5px black（harmony暂不支持inset）"),
        createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
          createElementVNode("view", utsMapOf({
            class: "common",
            style: normalizeStyle(utsMapOf({"box-shadow":"inset -5px -5px 5px black"}))
          }), null, 4 /* STYLE */)
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "box-shadow: 0px 1px 3px rgba(0,0,0,0.4)"),
        createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
          createElementVNode("view", utsMapOf({
            class: "common",
            style: normalizeStyle(utsMapOf({"box-shadow":"0px 1px 3px rgba(0, 0, 0, 0.4)"}))
          }), null, 4 /* STYLE */)
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "circle: box-shadow: 5px 5px black"),
        createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
          createElementVNode("view", utsMapOf({
            class: "common",
            style: normalizeStyle(utsMapOf({"border-radius":"62px","box-shadow":"5px 5px black"}))
          }), null, 4 /* STYLE */)
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "circle: box-shadow: 5px 5px 5px black"),
        createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
          createElementVNode("view", utsMapOf({
            class: "common",
            style: normalizeStyle(utsMapOf({"border-radius":"62px","box-shadow":"5px 5px 5px black"}))
          }), null, 4 /* STYLE */)
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "circle: box-shadow: 5px 10px 5px black"),
        createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
          createElementVNode("view", utsMapOf({
            class: "common",
            style: normalizeStyle(utsMapOf({"border-radius":"62px","box-shadow":"5px 10px 5px black"}))
          }), null, 4 /* STYLE */)
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "circle: box-shadow: 5px 5px 5px 5px black"),
        createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
          createElementVNode("view", utsMapOf({
            class: "common",
            style: normalizeStyle(utsMapOf({"border-radius":"62px","box-shadow":"5px 5px 5px 5px black"}))
          }), null, 4 /* STYLE */)
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "circle: box-shadow: -5px -5px 5px black"),
        createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
          createElementVNode("view", utsMapOf({
            class: "common",
            style: normalizeStyle(utsMapOf({"border-radius":"62px","box-shadow":"-5px -5px 5px black"}))
          }), null, 4 /* STYLE */)
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "circle: box-shadow: 0px 1px 3px rgba(0,0,0,0.4)"),
        createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
          createElementVNode("view", utsMapOf({
            class: "common",
            style: normalizeStyle(utsMapOf({"border-radius":"62px","box-shadow":"0px 1px 3px rgba(0, 0, 0, 0.4)"}))
          }), null, 4 /* STYLE */)
        ])
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "点击动态切换 box-shadow: none"),
        createElementVNode("view", utsMapOf({
          class: "backgroundview",
          onClick: _ctx.changed
        }), [
          createElementVNode("view", utsMapOf({
            class: normalizeClass(['common', _ctx.disabled ? 'disabledShadow' : 'shadow'])
          }), null, 2 /* CLASS */)
        ], 8 /* PROPS */, ["onClick"])
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "点击动态切换 box-shadow: 非法值"),
        createElementVNode("view", utsMapOf({
          class: "backgroundview",
          onClick: _ctx.changed
        }), [
          createElementVNode("view", utsMapOf({
            class: normalizeClass(['common', _ctx.disabled ? 'invalidShadow' : 'shadow'])
          }), null, 2 /* CLASS */)
        ], 8 /* PROPS */, ["onClick"])
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "box-shadow父视图动态改变高度的渲染效果"),
        createElementVNode("view", utsMapOf({ class: "backgroundview" }), [
          createElementVNode("view", utsMapOf({
            style: normalizeStyle(utsMapOf({"justify-content":"center","align-items":"center","width":"150px","background-color":"red","box-shadow":"0 0 10px"}))
          }), [
            createElementVNode("view", null, [
              createElementVNode("textarea", utsMapOf({
                id: "dynamic-height-textarea",
                style: normalizeStyle(utsMapOf({"margin":"20px","background-color":"aqua","height":"32px","width":"110px"})),
                value: "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
              }), null, 4 /* STYLE */)
            ])
          ], 4 /* STYLE */)
        ])
      ])
    ], 4 /* STYLE */)
  ], 4 /* STYLE */)
}
const GenPagesCSSBoxShadowBoxShadowStyles = [utsMapOf([["common", padStyleMapOf(utsMapOf([["width", 125], ["height", 125], ["backgroundColor", "#A52A2A"]]))], ["backgroundview", padStyleMapOf(utsMapOf([["width", 250], ["height", 250], ["backgroundColor", "#FFFFFF"], ["justifyContent", "center"], ["alignItems", "center"]]))], ["shadow", padStyleMapOf(utsMapOf([["boxShadow", "5px 5px black"]]))], ["disabledShadow", padStyleMapOf(utsMapOf([["boxShadow", "none"]]))], ["invalidShadow", padStyleMapOf(utsMapOf([["boxShadow", "abcd"]]))]])]
