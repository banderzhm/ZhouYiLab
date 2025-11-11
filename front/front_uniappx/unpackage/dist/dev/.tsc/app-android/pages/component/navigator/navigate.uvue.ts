
  const __sfc__ = defineComponent({
    data() {
      return {
        title: '新建的页面'
      }
    }
  })

export default __sfc__
function GenPagesComponentNavigatorNavigateRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"])
}
const GenPagesComponentNavigatorNavigateStyles = []

import _easycom_page_head from '@/components/page-head/page-head.vue'
