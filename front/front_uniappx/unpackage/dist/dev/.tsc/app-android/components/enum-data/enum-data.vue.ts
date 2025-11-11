import { ItemType } from './enum-data-types'
import { state } from '@/store/index.uts'


const __sfc__ = defineComponent({
  __name: 'enum-data',
  props: {
  title: {
    type: String,
    default: ''
  },
  items: {
    type: Array as PropType<Array<ItemType>>,
    required: true
  }
},
  emits: ['change'],
  setup(__props): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

function emit(event: string, ...do_not_transform_spread: Array<any | null>) {
__ins.emit(event, ...do_not_transform_spread)
}
const props = __props

const isDarkMode = computed(() => state.isDarkMode)
const current = ref(0)

// @ts-ignore
function _change(e : RadioGroupChangeEvent) {
  const selected = props.items.find((item : ItemType) : boolean => {
    return item.value.toString() == e.detail.value
  })
  if (selected != null) {
    emit('change', selected.value)
    uni.showToast({
      icon: 'none',
      title: '当前选中:' + selected.name,
    })
  }
}

return (): any | null => {

const _component_radio = resolveComponent("radio")
const _component_radio_group = resolveComponent("radio-group")

  return createElementVNode(Fragment, null, [
    createElementVNode("view", utsMapOf({
      class: normalizeClass(["uni-padding-wrap", unref(isDarkMode) ? 'theme-dark' : 'theme-light'])
    }), [
      createElementVNode("view", utsMapOf({ class: "uni-title uni-common-mt" }), [
        createElementVNode("text", utsMapOf({ class: "uni-title-text" }), toDisplayString(_ctx.title), 1 /* TEXT */)
      ])
    ], 2 /* CLASS */),
    createElementVNode("view", utsMapOf({
      class: normalizeClass(["uni-list uni-common-pl", unref(isDarkMode) ? 'theme-dark' : 'theme-light'])
    }), [
      createVNode(_component_radio_group, utsMapOf({ onChange: _change }), utsMapOf({
        default: withSlotCtx((): any[] => [
          createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.items, (item, index, __index, _cached): any => {
            return createVNode(_component_radio, utsMapOf({
              class: normalizeClass(["uni-list-cell uni-list-cell-pd", index < _ctx.items.length - 1 ? 'uni-list-cell-line' : '']),
              key: item.name,
              value: item.value + '',
              color: unref(isDarkMode) ? '#a8a8b7' : '#007AFF'
            }), utsMapOf({
              default: withSlotCtx((): any[] => [
                createElementVNode("text", utsMapOf({ class: "radio-text" }), toDisplayString(item.name), 1 /* TEXT */)
              ]),
              _: 2 /* DYNAMIC */
            }), 1032 /* PROPS, DYNAMIC_SLOTS */, ["class", "value", "color"])
          }), 128 /* KEYED_FRAGMENT */)
        ]),
        _: 1 /* STABLE */
      }))
    ], 2 /* CLASS */)
  ], 64 /* STABLE_FRAGMENT */)
}
}

})
export default __sfc__
export type EnumDataComponentPublicInstance = InstanceType<typeof __sfc__>;
const GenComponentsEnumDataEnumDataStyles = [utsMapOf([["radio-text", utsMapOf([[".uni-list ", utsMapOf([["color", "var(--text-color, #333333)"]])]])]])]
