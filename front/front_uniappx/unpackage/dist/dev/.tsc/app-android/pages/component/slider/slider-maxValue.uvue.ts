
	const __sfc__ = defineComponent({
		data() {
			return {
        sliderX: 0,
        sliderY: 0,
        sliderHeight: 0,
        sliderValue: 0
			}
		},
    onReady() {












    },
		methods: {
      valueChange(e: UniSliderChangeEvent) {
				console.log('valueChange', e.detail.value, " at pages/component/slider/slider-maxValue.uvue:36")
        this.sliderValue = e.detail.value
			}
		}
	})

export default __sfc__
function GenPagesComponentSliderSliderMaxValueRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_slider = resolveComponent("slider")

  return createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
    createElementVNode("view", utsMapOf({ class: "uni-title" }), "最大值溢出测试（不能滑动到最右侧）"),
    createElementVNode("view", null, [
      createVNode(_component_slider, utsMapOf({
        id: "slider",
        value: 0,
        min: 0,
        max: 10.5,
        step: 1,
        "show-value": true,
        onChange: _ctx.valueChange
      }), null, 8 /* PROPS */, ["onChange"])
    ])
  ])
}
const GenPagesComponentSliderSliderMaxValueStyles = []
