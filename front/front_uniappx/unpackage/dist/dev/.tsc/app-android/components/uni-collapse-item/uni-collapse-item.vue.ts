import { ItemChildType } from '../uni-collapse/item.type.uts'

  
const __sfc__ = defineComponent({
  __name: 'uni-collapse-item',

    name: "UniCollapseItem"
  ,
  props: {
    title: { type: String, default: '' },
    open: { type: Boolean, default: false },
    disabled: { type: Boolean, default: false }
  },
  setup(__props, { expose: __expose }: SetupContext): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

  

  const props = __props
  let height = 0
  let is_open = ref<boolean>(props.open)
  let box_is_open = ref<boolean>(props.open)

  let boxRef = ref<UniViewElement | null>(null)
  let contentRef = ref<UniViewElement | null>(null)

  let openType = computed(() => props.open)

  // 组件唯一ID
  const elId = ref(`uni_collapse_item_${Math.ceil(Math.random() * 10e5).toString(36)}`)

  const registerChild = inject<((child : ItemChildType) => string) | null>('uni-collapse-register-child', null)
  const collapseToggle = inject<((elId : string) => string) | null>('k-collapse-child-toggle', null)

  function openOrClose(open : boolean) {
    setTimeout(() => {
      box_is_open.value = !box_is_open.value
    }, 10)

    const bNode = boxRef.value?.style!;
    const cNode = contentRef.value?.style!;
    let hide = open ? 'flex' : 'none';
    const opacity = open ? "1" : "0"
    let ani_transform = open ? 'translateY(0)' : 'translateY(-100%)';
    bNode.setProperty('display', hide);
    nextTick(() => {
      setTimeout(() => {
        cNode.setProperty('transform', ani_transform);
        cNode.setProperty('opacity', opacity);
      }, 10)
    })

  }

  // 开启或关闭折叠面板
  function openCollapse(open : boolean) {
    if (props.disabled) return
    // 关闭其他已打开
    if (collapseToggle != null) {
      collapseToggle(elId.value)
    }
    is_open.value = open
    openOrClose(open)
  }


  onMounted(() => {
    if (registerChild != null) {
      const child : ItemChildType = {
        is_open,
        elId: elId.value,
        openOrClose
      }
      registerChild(child)
    }
  })

  watch(openType, (value : boolean) => {
    if (boxRef.value != null) {
      openCollapse(value)
    }
  })

  __expose({
    is_open,
    openOrClose,
    openCollapse
  })

return (): any | null => {

  return createElementVNode("view", utsMapOf({ class: "uni-collapse-item" }), [
    createElementVNode("view", utsMapOf({
      class: "uni-collapse-item__title",
      onClick: () => {openCollapse(!unref(is_open))}
    }), [
      createElementVNode("text", utsMapOf({
        class: normalizeClass(["uni-collapse-item__title-text", utsMapOf({'is-disabled':_ctx.disabled,'open--active':unref(is_open)})])
      }), toDisplayString(_ctx.title), 3 /* TEXT, CLASS */),
      createElementVNode("view", utsMapOf({
        class: normalizeClass(["down_arrow", utsMapOf({'down_arrow--active': unref(is_open)})])
      }), null, 2 /* CLASS */)
    ], 8 /* PROPS */, ["onClick"]),
    createElementVNode("view", utsMapOf({
      ref_key: "boxRef",
      ref: boxRef,
      class: normalizeClass(["uni-collapse-item__content", utsMapOf({'box-open--active':unref(is_open)})])
    }), [
      createElementVNode("view", utsMapOf({
        ref_key: "contentRef",
        ref: contentRef,
        class: normalizeClass(["uni-collapse-item__content-box", utsMapOf({'content-open--active':unref(box_is_open)})])
      }), [
        renderSlot(_ctx.$slots, "default")
      ], 2 /* CLASS */)
    ], 2 /* CLASS */)
  ])
}
}

})
export default __sfc__
export type UniCollapseItemComponentPublicInstance = InstanceType<typeof __sfc__>;
const GenComponentsUniCollapseItemUniCollapseItemStyles = [utsMapOf([["uni-collapse-item__title", utsMapOf([[".uni-collapse-item ", utsMapOf([["flexDirection", "row"], ["alignItems", "center"], ["paddingTop", 12], ["paddingRight", 18], ["paddingBottom", 12], ["paddingLeft", 18], ["backgroundColor", "#ffffff"]])]])], ["down_arrow", utsMapOf([[".uni-collapse-item ", utsMapOf([["width", 8], ["height", 8], ["transform", "rotate(45deg)"], ["borderRightWidth", 1], ["borderRightStyle", "solid"], ["borderRightColor", "#999999"], ["borderBottomWidth", 1], ["borderBottomStyle", "solid"], ["borderBottomColor", "#999999"], ["marginTop", -3], ["transitionProperty", "transform"], ["transitionDuration", "0.2s"]])]])], ["down_arrow--active", utsMapOf([[".uni-collapse-item ", utsMapOf([["transform", "rotate(-135deg)"], ["marginTop", 0]])]])], ["uni-collapse-item__title-text", utsMapOf([[".uni-collapse-item ", utsMapOf([["flex", 1], ["color", "#000000"], ["fontSize", 14], ["fontWeight", "400"]])]])], ["open--active", utsMapOf([[".uni-collapse-item ", utsMapOf([["color", "#bbbbbb"]])]])], ["is-disabled", utsMapOf([[".uni-collapse-item ", utsMapOf([["color", "#999999"]])]])], ["uni-collapse-item__content", utsMapOf([[".uni-collapse-item ", utsMapOf([["display", "none"], ["position", "relative"], ["overflow", "hidden"]])]])], ["box-open--active", utsMapOf([[".uni-collapse-item ", utsMapOf([["display", "flex"]])]])], ["uni-collapse-item__content-box", utsMapOf([[".uni-collapse-item ", utsMapOf([["width", "100%"], ["transitionProperty", "transform,opacity"], ["transitionDuration", "0.2s"], ["transform", "translateY(-100%)"], ["opacity", 0]])]])], ["content-open--active", utsMapOf([[".uni-collapse-item ", utsMapOf([["transform", "translateY(0%)"], ["opacity", 1]])]])], ["@TRANSITION", utsMapOf([["down_arrow", utsMapOf([["property", "transform"], ["duration", "0.2s"]])], ["uni-collapse-item__content-box", utsMapOf([["property", "transform,opacity"], ["duration", "0.2s"]])]])]])]
