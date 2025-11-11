import _easycom_page_head from '@/components/page-head/page-head.vue'

const __sfc__ = defineComponent({
  __name: 'get-accessibility-info',
  setup(__props): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

  const accessibilityInfo = ref("")
  accessibilityInfo.value = JSON.stringify(uni.getAccessibilityInfo())

return (): any | null => {

const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("view", null, [
      createVNode(_component_page_head, utsMapOf({ title: 'getAccessibilityInfo' })),
      createElementVNode("view", utsMapOf({ class: "uni-common-mt" }), [
        createElementVNode("text", utsMapOf({
          style: normalizeStyle(utsMapOf({"margin-left":"8px"}))
        }), "无障碍服务信息如下：", 4 /* STYLE */),
        createElementVNode("text", utsMapOf({
          style: normalizeStyle(utsMapOf({"margin-top":"15px","padding":"8px"}))
        }), toDisplayString(unref(accessibilityInfo)), 5 /* TEXT, STYLE */)
      ])
    ])
  ], 4 /* STYLE */)
}
}

})
export default __sfc__
const GenPagesAPIGetAccessibilityInfoGetAccessibilityInfoStyles = []
