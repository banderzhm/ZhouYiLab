
  const __sfc__ = defineComponent({
    name: 'Item',
    data() {
      return {

      }
    },
    emits: ['itemMounted'],
    methods: {

    },
    mounted() {
      this.$emit('itemMounted')
    }
  })

export default __sfc__
function GenPagesComponentListViewIssue13858ItemRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", null, "bug")
}
const GenPagesComponentListViewIssue13858ItemStyles = []
