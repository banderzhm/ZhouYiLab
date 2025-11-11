const __sfc__ = defineComponent({
  __name: 'border-update',
  setup(__props, { expose: __expose }: SetupContext): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

	let borderStyle = ref("border:3px solid red;")
	const handleUpdate = () => {
		borderStyle.value = "border:7px solid red;"
	}

  const jest_border_update = ()=>{
    handleUpdate()
  }

  __expose({
    jest_border_update
  })

return (): any | null => {

  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("view", null, [
      createElementVNode("button", utsMapOf({ onClick: handleUpdate }), "点击更新border样式"),
      createElementVNode("view", utsMapOf({
        class: "border-view",
        style: normalizeStyle(unref(borderStyle))
      }), null, 4 /* STYLE */)
    ])
  ], 4 /* STYLE */)
}
}

})
export default __sfc__
const GenPagesCSSBorderBorderUpdateStyles = [utsMapOf([["border-view", padStyleMapOf(utsMapOf([["width", 150], ["height", 150]]))]])]
