
const __sfc__ = defineComponent({
  methods: {
    closeDialog() {
      uni.closeDialogPage({
        success(res) {
          console.log("closeDialog success", res, " at pages/API/dialog-page/dialog-input.uvue:18");
        },
        fail(err) {
          console.log("closeDialog fail", err, " at pages/API/dialog-page/dialog-input.uvue:21");
        },
        complete(res) {
          console.log("closeDialog complete", res, " at pages/API/dialog-page/dialog-input.uvue:24");
        },
      });
    },
  },
});

export default __sfc__
function GenPagesAPIDialogPageDialogInputRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({ class: "dialog-container" }), [
    createElementVNode("view", utsMapOf({ class: "dialog-content" }), [
      createElementVNode("button", utsMapOf({
        class: "mt-10",
        id: "dialog1-close-dialog",
        onClick: _ctx.closeDialog
      }), " closeDialog ", 8 /* PROPS */, ["onClick"]),
      createElementVNode("input", utsMapOf({
        class: "dialog-input",
        placeholder: "请输入...",
        focus: true
      }))
    ])
  ])
}
const GenPagesAPIDialogPageDialogInputStyles = [utsMapOf([["dialog-container", padStyleMapOf(utsMapOf([["width", "100%"], ["height", "100%"], ["backgroundColor", "rgba(0,0,0,0.3)"], ["display", "flex"], ["justifyContent", "center"], ["alignItems", "center"]]))], ["dialog-content", padStyleMapOf(utsMapOf([["width", "90%"], ["height", "80%"], ["paddingTop", 10], ["paddingRight", 10], ["paddingBottom", 10], ["paddingLeft", 10], ["backgroundColor", "#ffffff"], ["borderTopLeftRadius", 6], ["borderTopRightRadius", 6], ["borderBottomRightRadius", 6], ["borderBottomLeftRadius", 6], ["position", "relative"]]))], ["dialog-input", padStyleMapOf(utsMapOf([["position", "absolute"], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#000000"], ["borderRightColor", "#000000"], ["borderBottomColor", "#000000"], ["borderLeftColor", "#000000"], ["left", 10], ["right", 10], ["bottom", 10]]))]])]
