
  const __sfc__ = defineComponent({
    data() {
      return {
        title: '当前页'
      }
    }
  })

export default __sfc__
function GenPagesComponentNavigatorRedirectRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"])
}
const GenPagesComponentNavigatorRedirectStyles = []

import _easycom_page_head from '@/components/page-head/page-head.vue'
