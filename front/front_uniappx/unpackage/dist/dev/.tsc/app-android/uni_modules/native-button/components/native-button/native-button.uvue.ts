import { NativeButton } from "@/uni_modules/native-button";
	
const __sfc__ = defineComponent({
  __name: 'native-button',
  props: {
    text: { type: String, required: true }
  },
  emits: ["load", "buttonTap"],
  setup(__props): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

	let button : NativeButton | null = null

	//声明属性
	const props = __props

	//声明事件
	function emit(event: string, ...do_not_transform_spread: Array<any | null>) {
__ins.emit(event, ...do_not_transform_spread)
}

	//声明方法
	function updateText(value : string) {
		button?.updateText(value)
	}

	//监听属性变化
	watchEffect(() => {
		const text = props.text
		updateText(text)
	})

	//native-view初始化时触发此方法
	function onviewinit(e : UniNativeViewInitEvent) {
		//获取UniNativeViewElement 传递给NativeButton对象
		button = new NativeButton(e.detail.element);
		updateText(props.text)
		emit("load")
	}

	function ontap(e : UniNativeViewEvent) {
		emit("buttonTap", e)
	}

	function onUnmounted() {
		// iOS平台需要主动释放 uts 实例
		button?.destroy()
	}

return (): any | null => {

  return createElementVNode("view", null, [
    createElementVNode("native-view", utsMapOf({
      style: normalizeStyle(utsMapOf({"height":"100px"})),
      onInit: onviewinit,
      "on:customClick": ontap
    }), null, 36 /* STYLE, NEED_HYDRATION */),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"width":"50%","height":"100px"}))
    }), [
      createElementVNode("button", null, "测试按钮")
    ], 4 /* STYLE */)
  ])
}
}

})
export default __sfc__
export type NativeButtonComponentPublicInstance = InstanceType<typeof __sfc__>;
const GenUniModulesNativeButtonComponentsNativeButtonNativeButtonStyles = []
