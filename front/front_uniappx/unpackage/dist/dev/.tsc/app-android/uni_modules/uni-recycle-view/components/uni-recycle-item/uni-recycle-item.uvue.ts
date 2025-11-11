const __sfc__ = defineComponent({
  __name: 'uni-recycle-item',
  props: {
    offset: {
      type: Number,
      default: 0
    }
  },
  setup(__props): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

  const props = __props

return (): any | null => {

  return createElementVNode("view", utsMapOf({
    class: "recycle-item",
    style: normalizeStyle(utsMapOf({transform: 'translateY(' + props.offset + 'px)'}))
  }), [
    renderSlot(_ctx.$slots, "default")
  ], 4 /* STYLE */)
}
}

})
export default __sfc__
export type UniRecycleItemComponentPublicInstance = InstanceType<typeof __sfc__>;
const GenUniModulesUniRecycleViewComponentsUniRecycleItemUniRecycleItemStyles = [utsMapOf([["recycle-item", padStyleMapOf(utsMapOf([["position", "absolute"], ["left", 0], ["top", 0], ["width", "100%"], ["height", "auto"]]))]])]
