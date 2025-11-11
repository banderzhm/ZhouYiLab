import { NativeTimePicker } from "@/uni_modules/native-time-picker";

  // 定义 props
  
const __sfc__ = defineComponent({
  __name: 'native-time-picker',
  props: {
    "hour": {
      type: Number,
      default: 0
    },
    "minute": {
      type: Number,
      default: 0
    }
  },
  emits: ['load', 'changed'],
  setup(__props): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

	const props = __props;

  // 定义 emits
  function emit(event: string, ...do_not_transform_spread: Array<any | null>) {
__ins.emit(event, ...do_not_transform_spread)
}

  // 响应式状态
  const picker = ref<NativeTimePicker | null>(null);
  const hourValue = ref(props.hour);
  const minuteValue = ref(props.minute);


  // 监听 hour prop 的变化
  watch(():number => props.hour, (newValue: number) => {
    if (newValue < 23 && newValue >= 0) {
      hourValue.value = newValue;
      picker.value?.setHour(hourValue.value);
    }
  }, {
    immediate: true
  });
  // 监听 minute prop 的变化
  watch(():number => props.minute, (newValue: number) => {
    if (newValue < 59 && newValue >= 0) {
      minuteValue.value = newValue;
      picker.value?.setMinute(minuteValue.value);
    }
  }, {
    immediate: true
  });



  // 方法
  function onviewinit(e: UniNativeViewInitEvent) {
    picker.value = new NativeTimePicker(e.detail.element, hourValue.value, minuteValue.value);
    emit("load");
  }

  function ontimechanged(e: UniNativeViewEvent) {
    emit("changed", e);
  }


return (): any | null => {

  return createElementVNode("view", utsMapOf({ class: "def-picker" }), [
    createElementVNode("native-view", utsMapOf({
      style: normalizeStyle(utsMapOf({"width":"100%","height":"100%"})),
      onInit: onviewinit,
      onTimechanged: ontimechanged
    }), null, 36 /* STYLE, NEED_HYDRATION */)
  ])
}
}

})
export default __sfc__
export type NativeTimePickerComponentPublicInstance = InstanceType<typeof __sfc__>;
const GenUniModulesNativeTimePickerComponentsNativeTimePickerNativeTimePickerStyles = [utsMapOf([["def-picker", padStyleMapOf(utsMapOf([["minWidth", 300], ["minHeight", 380], ["maxWidth", 400], ["maxHeight", 400]]))]])]
