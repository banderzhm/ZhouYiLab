type InputValue = string | number

const __sfc__ = defineComponent({
  __name: 'input-data',
  props: {
  title: {
    type: String,
    required: true
  },
  type: {
    type: String,
    required: true
  },
  defaultValue: {
    type: String,
    required: true,
    default: ''
  }
},
  emits: ['confirm'],
  setup(__props): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

function emit(event: string, ...do_not_transform_spread: Array<any | null>) {
__ins.emit(event, ...do_not_transform_spread)
}
const props = __props

const inputClearValue = ref<InputValue>('')
const showClearIcon = ref(false)
const inputType = ref('text')

function _getValue(value : InputValue) : InputValue {
  switch (props.type) {
    case 'number':
      return parseFloat(value as string)
  }
  return value
}

onMounted(() => {
  switch (props.type) {
    case 'number':
      inputType.value = 'number'
      break;
  }
  inputClearValue.value = _getValue(props.defaultValue)
})

function input(event : InputEvent) {
  // @ts-ignore
  inputClearValue.value = event.detail.value
  if ((inputClearValue.value as string).length > 0) {
    showClearIcon.value = true
  } else {
    showClearIcon.value = false
  }
  emit('confirm', _getValue(inputClearValue.value))
}

function clearIcon() {
  inputClearValue.value = ''
  showClearIcon.value = false
  emit('confirm', _getValue(inputClearValue.value))
}

function blur() {
  showClearIcon.value = false
}

function focus() {
  let inputValue = inputClearValue.value
  if (typeof inputValue !== 'string') {
    inputValue = inputValue.toString()
  }
  if (inputValue.length > 0) {
    showClearIcon.value = true
  } else {
    showClearIcon.value = false
  }
}

return (): any | null => {

  return createElementVNode(Fragment, null, [
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
      createElementVNode("view", utsMapOf({ class: "uni-title uni-common-mt" }), [
        createElementVNode("text", utsMapOf({ class: "uni-title-text" }), toDisplayString(_ctx.title), 1 /* TEXT */)
      ])
    ]),
    createElementVNode("view", utsMapOf({ class: "input-wrapper" }), [
      createElementVNode("input", utsMapOf({
        class: "uni-input",
        type: unref(inputType),
        value: unref(inputClearValue),
        placeholder: _ctx.title,
        maxlength: "-1",
        onInput: input,
        onBlur: blur,
        onFocus: focus
      }), null, 40 /* PROPS, NEED_HYDRATION */, ["type", "value", "placeholder"]),
      isTrue(unref(showClearIcon))
        ? createElementVNode("image", utsMapOf({
            key: 0,
            class: "input-wrapper_image",
            src: "/static/icons/clear.png",
            onTouchstart: clearIcon
          }), null, 32 /* NEED_HYDRATION */)
        : createCommentVNode("v-if", true)
    ])
  ], 64 /* STABLE_FRAGMENT */)
}
}

})
export default __sfc__
export type InputDataComponentPublicInstance = InstanceType<typeof __sfc__>;
const GenComponentsInputDataInputDataStyles = [utsMapOf([["input-wrapper", padStyleMapOf(utsMapOf([["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "rgba(0,0,0,0.08)"], ["borderRightColor", "rgba(0,0,0,0.08)"], ["borderBottomColor", "rgba(0,0,0,0.08)"], ["borderLeftColor", "rgba(0,0,0,0.08)"], ["flexDirection", "row"], ["justifyContent", "center"], ["paddingTop", 0], ["paddingRight", 0], ["paddingBottom", 0], ["paddingLeft", 0], ["marginTop", 0], ["marginRight", 10], ["marginBottom", 0], ["marginLeft", 10], ["flexWrap", "nowrap"], ["backgroundColor", "#ffffff"]]))], ["input-wrapper_image", padStyleMapOf(utsMapOf([["width", 22], ["height", 22], ["alignSelf", "center"], ["marginRight", 5]]))]])]
