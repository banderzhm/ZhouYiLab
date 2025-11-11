import { state } from '@/store/index.uts'


const __sfc__ = defineComponent({
  __name: 'boolean-data',
  props: {
  title: {
    type: String,
    default: ''
  },
  disabled: {
    type: Boolean,
    default: false
  },
  defaultValue: {
    type: Boolean,
    default: false
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
const _checked = ref(false)

onMounted(() => {
  _checked.value = props.defaultValue
})

// @ts-ignore
function _change(e : UniSwitchChangeEvent) {
  _checked.value = e.detail.value;
  emit('change', _checked.value)
}

return (): any | null => {

const _component_switch = resolveComponent("switch")

  return createElementVNode("view", utsMapOf({
    class: normalizeClass(["button-data-main uni-flex", unref(isDarkMode) ? 'theme-dark' : 'theme-light'])
  }), [
    createElementVNode("text", utsMapOf({
      class: "uni-title",
      style: normalizeStyle(utsMapOf({"width":"80%"}))
    }), toDisplayString(_ctx.title), 5 /* TEXT, STYLE */),
    createVNode(_component_switch, utsMapOf({
      checked: unref(_checked),
      disabled: _ctx.disabled,
      color: unref(isDarkMode) ? '#a8a8b7' : '#007AFF',
      onChange: _change
    }), null, 8 /* PROPS */, ["checked", "disabled", "color"])
  ], 2 /* CLASS */)
}
}

})
export default __sfc__
export type BooleanDataComponentPublicInstance = InstanceType<typeof __sfc__>;
const GenComponentsBooleanDataBooleanDataStyles = [utsMapOf([["button-data-main", padStyleMapOf(utsMapOf([["justifyContent", "space-between"], ["paddingTop", 10], ["paddingRight", 10], ["paddingBottom", 10], ["paddingLeft", 10], ["borderBottomWidth", 1], ["borderBottomStyle", "solid"], ["borderBottomColor", "var(--border-color,rgba(0,0,0,.06))"], ["alignItems", "center"]]))], ["uni-title", utsMapOf([[".button-data-main ", utsMapOf([["color", "var(--text-color, #333333)"]])]])]])]
