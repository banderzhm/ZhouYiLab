
  const __sfc__ = defineComponent({
    data() {
      return {
        shuttleOnPush: "to",
        transitionOnGesture: false
      }
    },
    onLoad(event : OnLoadOptions) {
      this.shuttleOnPush = event["shuttleOnPush"] as string
      this.transitionOnGesture = event["transitionOnGesture"] === "true"
      console.log(`shuttleOnPush:${this.shuttleOnPush} transitionOnGesture:${this.transitionOnGesture}`, " at pages/component/share-element/share-element-to.uvue:30")
    },
    methods: {
      openPage() {
        uni.navigateBack()
      }
    },
  })

export default __sfc__
function GenPagesComponentShareElementShareElementToRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode(Fragment, null, [
    createElementVNode("view", utsMapOf({ class: "main" }), [
      createElementVNode("share-element", utsMapOf({
        class: "share-element",
        "share-key": "left",
        "shuttle-on-pop": _ctx.shuttleOnPush,
        "transition-on-gesture": _ctx.transitionOnGesture,
        onClick: () => {_ctx.openPage()}
      }), [
        createElementVNode("image", utsMapOf({
          src: "https://web-ext-storage.dcloud.net.cn/hello-uni-app-x/drop-card-1.jpg",
          mode: "widthFix"
        })),
        createElementVNode("view", utsMapOf({ class: "text-label" }), [
          createElementVNode("text", utsMapOf({
            style: normalizeStyle(utsMapOf({"color":"white"}))
          }), "每一口，都是幸福", 4 /* STYLE */)
        ])
      ], 8 /* PROPS */, ["shuttle-on-pop", "transition-on-gesture", "onClick"])
    ]),
    createElementVNode("share-element", utsMapOf({
      class: "bottomWrap",
      "share-key": "bottom",
      onClick: () => {_ctx.openPage()},
      transitionOnGesture: "true"
    }), [
      createElementVNode("view", utsMapOf({ class: "bottom" }), [
        createElementVNode("text", utsMapOf({
          style: normalizeStyle(utsMapOf({"color":"white"}))
        }), "share-element(底部固定)", 4 /* STYLE */)
      ])
    ], 8 /* PROPS */, ["onClick"])
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesComponentShareElementShareElementToStyles = [utsMapOf([["bottomWrap", padStyleMapOf(utsMapOf([["width", "100%"], ["bottom", 0], ["height", 80], ["position", "fixed"]]))], ["bottom", padStyleMapOf(utsMapOf([["width", "100%"], ["height", "100%"], ["alignItems", "center"], ["justifyContent", "center"], ["backgroundColor", "#007aff"]]))], ["text-label", padStyleMapOf(utsMapOf([["position", "absolute"], ["backgroundColor", "#007aff"], ["opacity", 0.8], ["textAlign", "center"], ["justifyContent", "center"], ["alignItems", "center"], ["bottom", 0], ["left", 0], ["right", 0], ["height", 30]]))], ["main", padStyleMapOf(utsMapOf([["paddingTop", 5], ["paddingRight", 5], ["paddingBottom", 5], ["paddingLeft", 5], ["alignItems", "center"], ["justifyContent", "center"]]))]])]
