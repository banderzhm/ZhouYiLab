
  const __sfc__ = defineComponent({
    methods: {
      exitAppClick: function () {
        uni.exit({
          success: function (res) {
            console.log(res, " at pages/API/exit/exit.uvue:14")
          },
          fail: function(error){
            console.log(error, " at pages/API/exit/exit.uvue:17")
          }
        })
      }
    }
  })

export default __sfc__
function GenPagesAPIExitExitRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", null, [
    createElementVNode("button", utsMapOf({ onClick: _ctx.exitAppClick }), "退出应用", 8 /* PROPS */, ["onClick"]),
    createElementVNode("text", null, "注:iOS仅在uni-app x SDK模式中支持应用退出")
  ])
}
const GenPagesAPIExitExitStyles = []
