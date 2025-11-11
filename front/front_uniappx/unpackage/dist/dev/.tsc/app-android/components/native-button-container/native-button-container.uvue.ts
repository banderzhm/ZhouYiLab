import _easycom_native_button from '@/uni_modules/native-button/components/native-button/native-button.uvue'
import { createNativeButtonContext } from "@/uni_modules/native-button";
	
const __sfc__ = defineComponent({
  __name: 'native-button-container',
  setup(__props): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

	const title = ref('组件能力封装示例');
	const ontap = (e : UniNativeViewEvent) => {
		console.log("ontap----------" + e.type, " at components/native-button-container/native-button-container.uvue:12")
	};
	const testCallMethod = () => {
		let context = createNativeButtonContext("helloView1", getCurrentInstance()?.proxy)
		context?.updateText("test code")
	};

return (): any | null => {

const _component_native_button = resolveEasyComponent("native-button",_easycom_native_button)

  return createElementVNode("view", null, [
    createElementVNode("button", utsMapOf({
      type: "primary",
      onClick: testCallMethod
    }), "调用组件方法"),
    createVNode(_component_native_button, utsMapOf({
      id: "helloView1",
      class: "native-button",
      text: "自定义组件"
    }))
  ])
}
}

})
export default __sfc__
export type NativeButtonContainerComponentPublicInstance = InstanceType<typeof __sfc__>;
const GenComponentsNativeButtonContainerNativeButtonContainerStyles = []
