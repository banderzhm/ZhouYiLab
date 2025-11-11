
  import datacom from './datacom.uvue';

  const __sfc__ = defineComponent({
    components: {
      datacom
    },
    data() {
      return {
      }
    },
    methods: {
    }
  })

export default __sfc__
function GenPagesComponentUnicloudDbUnicloudDbMixinDatacomMixinDatacomRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_datacom = resolveComponent("datacom")

  return createElementVNode("view", utsMapOf({ class: "content" }), [
    createVNode(_component_datacom, utsMapOf({
      class: "datacom",
      collection: "unicloud-db-test"
    }))
  ])
}
const GenPagesComponentUnicloudDbUnicloudDbMixinDatacomMixinDatacomStyles = [utsMapOf([["content", padStyleMapOf(utsMapOf([["flex", 1]]))]])]
