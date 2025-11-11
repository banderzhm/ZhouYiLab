
  const __sfc__ = defineComponent({
    data() {
      return {
        class: 'box',
      }
    },
    methods: {
      start() {
        this.class = 'box ani'
      },
      reset() {
        this.class = 'box'
      },
      jest_start(){
        this.start()
      },
      jest_reset(){
        this.reset()
      },
    }
  })

export default __sfc__
function GenPagesCSSTransitionTransitionDurationRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", null, [
    createElementVNode("view", utsMapOf({
      class: normalizeClass(_ctx.class)
    }), null, 2 /* CLASS */),
    createElementVNode("button", utsMapOf({ onClick: _ctx.start }), "start", 8 /* PROPS */, ["onClick"]),
    createElementVNode("button", utsMapOf({ onClick: _ctx.reset }), "reset", 8 /* PROPS */, ["onClick"])
  ])
}
const GenPagesCSSTransitionTransitionDurationStyles = [utsMapOf([["box", padStyleMapOf(utsMapOf([["width", 100], ["height", 100], ["backgroundColor", "#0000FF"]]))], ["ani", padStyleMapOf(utsMapOf([["transitionDuration", "5s"], ["width", 200]]))], ["@TRANSITION", utsMapOf([["ani", utsMapOf([["duration", "5s"]])]])]])]
