
  const __sfc__ = defineComponent({
    data() {
      return {
        title: 'AnimationFrame',
        taskId: 0,
        FPSString: '- / -ms',
        lastTime: 0,
        frameCount: 0,
        testFrameCount: 0
      }
    },
    onUnload() {
      if (this.taskId > 0) {
        this.stopRequestAnimationFrame()
      }
    },
    methods: {
      startRequestAnimationFrame() {
        this.taskId = requestAnimationFrame((timestamp : number) => {
          this.updateFPS(timestamp)
          this.testFrameCount++
          this.startRequestAnimationFrame()
        })
      },
      stopRequestAnimationFrame() {
        cancelAnimationFrame(this.taskId)
        this.lastTime = 0
        this.frameCount = 0
        this.FPSString = '- / -ms'
      },
      updateFPS(timestamp : number) {
        this.frameCount++
        if (timestamp - this.lastTime >= 1000) {
          const timeOfFrame = (1000 / this.frameCount)
          this.FPSString = `${this.frameCount} / ${timeOfFrame.toFixed(3)}ms`
          this.frameCount = 0
          this.lastTime = timestamp
        }
      }
    }
  })

export default __sfc__
function GenPagesAPIAnimationFrameAnimationFrameRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode("view", utsMapOf({ class: "page" }), [
    createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
    createElementVNode("button", utsMapOf({ onClick: _ctx.startRequestAnimationFrame }), "requestAnimationFrame", 8 /* PROPS */, ["onClick"]),
    createElementVNode("button", utsMapOf({ onClick: _ctx.stopRequestAnimationFrame }), "cancelAnimationFrame", 8 /* PROPS */, ["onClick"]),
    createElementVNode("text", utsMapOf({ class: "frame-count" }), "FPS: " + toDisplayString(_ctx.FPSString), 1 /* TEXT */),
    createElementVNode("text", utsMapOf({ class: "frame-count" }), "FrameCount: " + toDisplayString(_ctx.testFrameCount), 1 /* TEXT */),
    createElementVNode("text", utsMapOf({ class: "tips" }), "提示: 在当前测试例子中，每增加一次调用 requestAnimationFrame 帧率翻倍，cancelAnimationFrame 后恢复")
  ])
}
const GenPagesAPIAnimationFrameAnimationFrameStyles = [utsMapOf([["page", padStyleMapOf(utsMapOf([["paddingTop", 15], ["paddingRight", 15], ["paddingBottom", 15], ["paddingLeft", 15]]))], ["frame-count", padStyleMapOf(utsMapOf([["marginTop", 15]]))], ["tips", padStyleMapOf(utsMapOf([["fontSize", 12], ["marginTop", 30], ["opacity", 0.7]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
