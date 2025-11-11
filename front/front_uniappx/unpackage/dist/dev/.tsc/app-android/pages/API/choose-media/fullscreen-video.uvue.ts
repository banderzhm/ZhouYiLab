
  const __sfc__ = defineComponent({
    data() {
      return {
        url: "",
        coverPath: ""
      }
    },
    onLoad(options : OnLoadOptions) {
      uni.$once("__ONRECEIVEURL",(value:UTSJSONObject)=>{
        this.url = value["url"] as string;
        this.coverPath = value["cover"] as string;
      })
      uni.$emit("__ONFULLVIDEOLOAD",null)
    },
    methods: {
      back() {
        uni.navigateBack()
      }
    }
  })

export default __sfc__
function GenPagesAPIChooseMediaFullscreenVideoRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("view", utsMapOf({
      ref: "back",
      onClick: _ctx.back,
      class: "nav-back"
    }), [
      createElementVNode("image", utsMapOf({
        class: "back-img",
        src: "/static/template/scroll-fold-nav/back.png",
        mode: "widthFix"
      }))
    ], 8 /* PROPS */, ["onClick"]),
    createElementVNode("video", utsMapOf({
      ref: "video",
      class: "video-box",
      src: _ctx.url,
      controls: "false",
      autoplay: "true",
      "show-progress": "false",
      "show-fullscreen-btn": "false",
      "show-play-btn": "false",
      "show-center-play-btn": "false"
    }), null, 8 /* PROPS */, ["src"])
  ], 4 /* STYLE */)
}
const GenPagesAPIChooseMediaFullscreenVideoStyles = [utsMapOf([["nav-back", padStyleMapOf(utsMapOf([["position", "absolute"], ["top", 35], ["backgroundColor", "rgba(220,220,220,0.8)"], ["borderTopLeftRadius", 100], ["borderTopRightRadius", 100], ["borderBottomRightRadius", 100], ["borderBottomLeftRadius", 100], ["marginTop", 6], ["marginRight", 6], ["marginBottom", 6], ["marginLeft", 6], ["width", 32], ["height", 32], ["justifyContent", "center"], ["alignItems", "center"], ["zIndex", 10]]))], ["back-img", utsMapOf([[".nav-back ", utsMapOf([["width", 18], ["height", 18]])]])], ["video-box", padStyleMapOf(utsMapOf([["width", "100%"], ["flex", 1], ["height", "100%"]]))]])]
