
  const __sfc__ = defineComponent({
    data() {
      return {
        top: 0
      }
    },
    mounted() {
      uni.createSelectorQuery().in(this).select('.selector-query-child-view').boundingClientRect().exec((ret) => {
        if (ret.length == 1) {
          const nodeInfo = ret[0] as NodeInfo;
          this.top = nodeInfo.top!
        }
      })
    }
  })

export default __sfc__
function GenPagesAPICreateSelectorQueryNodesInfoChildRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({ class: "selector-query-child-view" }), [
    createElementVNode("text", utsMapOf({ class: "selector-query-child-text" }), "selector-query-child")
  ])
}
const GenPagesAPICreateSelectorQueryNodesInfoChildStyles = [utsMapOf([["selector-query-child-view", padStyleMapOf(utsMapOf([["marginTop", 15]]))]])]
