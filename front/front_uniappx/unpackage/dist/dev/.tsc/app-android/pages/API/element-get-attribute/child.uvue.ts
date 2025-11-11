
  const __sfc__ = defineComponent({
    data() {
      return {}
    },
  })

export default __sfc__
function GenPagesAPIElementGetAttributeChildRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({ class: "child-view" }), [
    createElementVNode("text", utsMapOf({ class: "child-text" }), "我是子组件child")
  ])
}
const GenPagesAPIElementGetAttributeChildStyles = [utsMapOf([["child-view", padStyleMapOf(utsMapOf([["marginTop", 15], ["height", 100], ["backgroundColor", "#1E90FF"]]))]])]
