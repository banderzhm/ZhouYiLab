
  const __sfc__ = defineComponent({
    data() {
      return {
        clickTriger: false,
        longClickTriger: false
      }
    },
    methods: {
      onclick(event : UniPointerEvent) {
        this.clickTriger = true
        uni.showToast({
          title: "触发了点击事件"
        })
      },

      onLongPress(event : UniTouchEvent) {
        this.longClickTriger = true
        uni.showToast({
          title: "触发了长按事件"
        })
      }

    }
  })

export default __sfc__
function GenPagesComponentGlobalEventsGlobalEventsTransformRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode(Fragment, null, [
    createElementVNode("view", null, [
      createElementVNode("view", utsMapOf({
        onClick: _ctx.onclick,
        style: normalizeStyle(utsMapOf({"transform":"rotate(180deg)","background-color":"brown","width":"100px","height":"100px","margin":"8px auto"})),
        onLongpress: _ctx.onLongPress,
        id: "event-transform"
      }), null, 44 /* STYLE, PROPS, NEED_HYDRATION */, ["onClick", "onLongpress"])
    ]),
    createElementVNode("text", utsMapOf({
      style: normalizeStyle(utsMapOf({"margin":"0px auto"}))
    }), "点击上面view触发点击事件", 4 /* STYLE */)
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesComponentGlobalEventsGlobalEventsTransformStyles = []
