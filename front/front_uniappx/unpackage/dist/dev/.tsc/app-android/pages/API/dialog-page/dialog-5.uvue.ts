
  const __sfc__ = defineComponent({
    data() {
      return {
        title: 'dialog 5',
        pageBody : null as UniPageBody | null,
        safeAreaInsets: null as UniSafeAreaInsets | null,
        width: null as number | null,
        height: null as number | null,
        statusBarHeight: null as number | null,
      }
    },
    onShow() {
      console.log('dialog 5 onShow', " at pages/API/dialog-page/dialog-5.uvue:48")
    },
    onReady(){
      const currentPage = this.$page
      this.pageBody = currentPage.pageBody;
      this.safeAreaInsets = currentPage.safeAreaInsets

      this.width = currentPage.width
      this.height = currentPage.height
      this.statusBarHeight = currentPage.statusBarHeight

    },
    onUnload() {
      console.log('dialog 5 onUnload', " at pages/API/dialog-page/dialog-5.uvue:61")
    },
    methods: {
      closeThisDialog() {
        uni.closeDialogPage({
          dialogPage: this.$page,
          success(res) {
            console.log('closeThisDialog success', res, " at pages/API/dialog-page/dialog-5.uvue:68")
          },
          fail(err) {
            console.log('closeThisDialog fail', err, " at pages/API/dialog-page/dialog-5.uvue:71")
          },
          complete(res) {
            console.log('closeThisDialog complete', res, " at pages/API/dialog-page/dialog-5.uvue:74")
          }
        })
      }
    }
  })

export default __sfc__
function GenPagesAPIDialogPageDialog5Render(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({ class: "dialog-container" }), [
    createElementVNode("view", utsMapOf({ class: "dialog-content" }), [
      createElementVNode("text", null, "title: " + toDisplayString(_ctx.title), 1 /* TEXT */),
      _ctx.pageBody != null
        ? createElementVNode("view", utsMapOf({
            key: 0,
            class: "uni-common-mt flex-row"
          }), [
            "pageBody: { top: ",
            createElementVNode("text", utsMapOf({ id: "page-body-top" }), toDisplayString(_ctx.pageBody!.top), 1 /* TEXT */),
            ", right: ",
            createElementVNode("text", utsMapOf({ id: "page-body-right" }), toDisplayString(_ctx.pageBody!.right), 1 /* TEXT */),
            ", bottom: ",
            createElementVNode("text", utsMapOf({ id: "page-body-bottom" }), toDisplayString(_ctx.pageBody!.bottom), 1 /* TEXT */),
            ", left: ",
            createElementVNode("text", utsMapOf({ id: "page-body-left" }), toDisplayString(_ctx.pageBody!.left), 1 /* TEXT */),
            ", width: ",
            createElementVNode("text", utsMapOf({ id: "page-body-width" }), toDisplayString(_ctx.pageBody!.width), 1 /* TEXT */),
            ", height: ",
            createElementVNode("text", utsMapOf({ id: "page-body-height" }), toDisplayString(_ctx.pageBody!.height), 1 /* TEXT */),
            " } "
          ])
        : createCommentVNode("v-if", true),
      _ctx.safeAreaInsets != null
        ? createElementVNode("view", utsMapOf({
            key: 1,
            class: "uni-common-mt flex-row"
          }), [
            "safeAreaInsets: { top: ",
            createElementVNode("text", utsMapOf({ id: "page-safe-area-insets-top" }), toDisplayString(_ctx.safeAreaInsets!.top), 1 /* TEXT */),
            ", right: ",
            createElementVNode("text", utsMapOf({ id: "page-safe-area-insets-right" }), toDisplayString(_ctx.safeAreaInsets!.right), 1 /* TEXT */),
            ", bottom: ",
            createElementVNode("text", utsMapOf({ id: "page-safe-area-insets-bottom" }), toDisplayString(_ctx.safeAreaInsets!.bottom), 1 /* TEXT */),
            ", left: ",
            createElementVNode("text", utsMapOf({ id: "page-safe-area-insets-left" }), toDisplayString(_ctx.safeAreaInsets!.left), 1 /* TEXT */),
            "} "
          ])
        : createCommentVNode("v-if", true),
      _ctx.width != null
        ? createElementVNode("view", utsMapOf({
            key: 2,
            class: "uni-common-mt flex-row"
          }), [
            "width: ",
            createElementVNode("text", null, toDisplayString(_ctx.width!), 1 /* TEXT */)
          ])
        : createCommentVNode("v-if", true),
      _ctx.height != null
        ? createElementVNode("view", utsMapOf({
            key: 3,
            class: "uni-common-mt flex-row"
          }), [
            "height: ",
            createElementVNode("text", null, toDisplayString(_ctx.height!), 1 /* TEXT */)
          ])
        : createCommentVNode("v-if", true),
      _ctx.statusBarHeight != null
        ? createElementVNode("view", utsMapOf({
            key: 4,
            class: "uni-common-mt flex-row"
          }), [
            "statusBarHeight: ",
            createElementVNode("text", null, toDisplayString(_ctx.statusBarHeight!), 1 /* TEXT */)
          ])
        : createCommentVNode("v-if", true),
      createElementVNode("button", utsMapOf({
        class: "mt-10",
        onClick: _ctx.closeThisDialog
      }), " closeThisDialog ", 8 /* PROPS */, ["onClick"])
    ])
  ])
}
const GenPagesAPIDialogPageDialog5Styles = [utsMapOf([["dialog-container", padStyleMapOf(utsMapOf([["width", "100%"], ["height", "100%"], ["backgroundColor", "rgba(0,0,0,0.3)"], ["display", "flex"], ["justifyContent", "center"], ["alignItems", "center"]]))], ["dialog-content", padStyleMapOf(utsMapOf([["width", "80%"], ["paddingTop", 10], ["paddingRight", 10], ["paddingBottom", 10], ["paddingLeft", 10], ["backgroundColor", "#ffffff"], ["borderTopLeftRadius", 6], ["borderTopRightRadius", 6], ["borderBottomRightRadius", 6], ["borderBottomLeftRadius", 6]]))], ["mt-10", padStyleMapOf(utsMapOf([["marginTop", 10]]))], ["flex-row", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["flexWrap", "wrap"]]))]])]
