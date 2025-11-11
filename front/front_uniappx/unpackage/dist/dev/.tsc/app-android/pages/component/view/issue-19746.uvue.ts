const __sfc__ = defineComponent({
  __name: 'issue-19746',
  setup(__props, { expose: __expose }: SetupContext): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;


    const clickCount = ref(0);
		const viewEle = ref<UniElement | null>(null);

		function setPointEventAuto() {
			viewEle.value?.style.setProperty('pointer-events', 'auto')
		}

		function onClick() {
			clickCount.value += 1
			console.log(clickCount.value, " at pages/component/view/issue-19746.uvue:19")
		}

		function getClickCount() {
			return clickCount.value
		}

    onMounted(() => {
				viewEle.value = uni.getElementById("subView")
    });

    __expose({
			getClickCount,
			setPointEventAuto
    })


return (): any | null => {

  return createElementVNode("view", null, [
    createElementVNode("view", utsMapOf({
      id: "subView",
      class: "subView",
      onClick: onClick
    }))
  ])
}
}

})
export default __sfc__
const GenPagesComponentViewIssue19746Styles = [utsMapOf([["subView", padStyleMapOf(utsMapOf([["width", 200], ["height", 500], ["backgroundColor", "#FF0000"], ["pointerEvents", "none"]]))]])]
