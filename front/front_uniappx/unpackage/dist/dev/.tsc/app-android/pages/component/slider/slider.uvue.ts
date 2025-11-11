
  const __sfc__ = defineComponent({
    data() {
      return {
        sliderValue: 50,
        sliderBlockSize: 20,
        sliderBackgroundColor: "#000000",
        sliderActiveColor: "#FFCC33",
        sliderBlockColor: "#8A6DE9",
        // 组件属性 autotest
        show_value_boolean: false,
        disabled_boolean: false,
        min_input: 0,
        max_input: 100,
        step_input: 1,
        value_input: 0,
        activeColor_input: "#007aff",
        backgroundColor_input: "#e9e9e9",
        block_size_input: 28,
        block_color_input: "#ffffff",
        valueColor: "#888888",
      };
    },
    methods: {
      sliderChange(e : UniSliderChangeEvent) {
        console.log("value 发生变化：" + e.detail.value, " at pages/component/slider/slider.uvue:26");
      },
      slider_click() {
        console.log("组件被点击时触发", " at pages/component/slider/slider.uvue:29");
      },
      slider_touchstart() {
        console.log("手指触摸动作开始", " at pages/component/slider/slider.uvue:32");
      },
      slider_touchmove() {
        console.log("手指触摸后移动", " at pages/component/slider/slider.uvue:35");
      },
      slider_touchcancel() {
        console.log("手指触摸动作被打断，如来电提醒，弹窗", " at pages/component/slider/slider.uvue:38");
      },
      slider_touchend() {
        console.log("手指触摸动作结束", " at pages/component/slider/slider.uvue:41");
      },
      slider_tap() {
        console.log("手指触摸后马上离开", " at pages/component/slider/slider.uvue:44");
      },
      slider_longpress() {
        console.log(
          "如果一个组件被绑定了 longpress 事件，那么当用户长按这个组件时，该事件将会被触发。"
        , " at pages/component/slider/slider.uvue:47");
      },
      slider_change() {
        console.log("完成一次拖动后触发的事件，event.detail = {value: value}", " at pages/component/slider/slider.uvue:52");
      },
      slider_changing() {
        console.log("拖动过程中触发的事件，event.detail = {value: value}", " at pages/component/slider/slider.uvue:55");
      },
      change_show_value_boolean(checked : boolean) {
        this.show_value_boolean = checked;
      },
      change_disabled_boolean(checked : boolean) {
        this.disabled_boolean = checked;
      },
      confirm_min_input(value : number) {
        this.min_input = value;
      },
      confirm_max_input(value : number) {
        this.max_input = value;
      },
      confirm_step_input(value : number) {
        this.step_input = value;
      },
      confirm_value_input(value : number) {
        this.value_input = value;
      },
      confirm_activeColor_input(value : string) {
        this.activeColor_input = value;
      },
      confirm_backgroundColor_input(value : string) {
        this.backgroundColor_input = value;
      },
      confirm_block_size_input(value : number) {
        this.block_size_input = value;
      },
      confirm_block_color_input(value : string) {
        this.block_color_input = value;
      },
      confirm_value_color_input(value : string) {
        this.valueColor = value;
      },
    },
  });

export default __sfc__
function GenPagesComponentSliderSliderRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_slider = resolveComponent("slider", true)
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)
const _component_boolean_data = resolveEasyComponent("boolean-data",_easycom_boolean_data)
const _component_input_data = resolveEasyComponent("input-data",_easycom_input_data)
const _component_navigator = resolveComponent("navigator")

  return createElementVNode(Fragment, null, [
    createElementVNode("view", utsMapOf({ class: "main" }), [
      createVNode(_component_slider, utsMapOf({
        disabled: _ctx.disabled_boolean,
        min: _ctx.min_input,
        max: _ctx.max_input,
        step: _ctx.step_input,
        value: _ctx.value_input,
        activeColor: _ctx.activeColor_input,
        backgroundColor: _ctx.backgroundColor_input,
        "block-size": _ctx.block_size_input,
        "block-color": _ctx.block_color_input,
        "show-value": _ctx.show_value_boolean,
        valueColor: _ctx.valueColor,
        onClick: [_ctx.slider_click, _ctx.slider_tap],
        onTouchstart: _ctx.slider_touchstart,
        onTouchmove: _ctx.slider_touchmove,
        onTouchcancel: _ctx.slider_touchcancel,
        onTouchend: _ctx.slider_touchend,
        onLongpress: _ctx.slider_longpress,
        onChange: _ctx.slider_change,
        onChanging: _ctx.slider_changing,
        style: normalizeStyle(utsMapOf({"width":"90%"}))
      }), null, 8 /* PROPS */, ["disabled", "min", "max", "step", "value", "activeColor", "backgroundColor", "block-size", "block-color", "show-value", "valueColor", "onClick", "onTouchstart", "onTouchmove", "onTouchcancel", "onTouchend", "onLongpress", "onChange", "onChanging", "style"])
    ]),
    createElementVNode("scroll-view", utsMapOf({
      style: normalizeStyle(utsMapOf({"flex":"1"}))
    }), [
      createElementVNode("view", utsMapOf({ class: "content" }), [
        createVNode(_component_page_head, utsMapOf({ title: "组件属性" })),
        createVNode(_component_boolean_data, utsMapOf({
          defaultValue: false,
          title: "是否显示当前 value",
          onChange: _ctx.change_show_value_boolean
        }), null, 8 /* PROPS */, ["onChange"]),
        createVNode(_component_boolean_data, utsMapOf({
          defaultValue: false,
          title: "是否禁用",
          onChange: _ctx.change_disabled_boolean
        }), null, 8 /* PROPS */, ["onChange"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "0",
          title: "最小值(min)",
          type: "number",
          onConfirm: _ctx.confirm_min_input
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "100",
          title: "最大值(max)",
          type: "number",
          onConfirm: _ctx.confirm_max_input
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "1",
          title: "步长(step)，取值必须大于 0，并且可被(max - min)整除",
          type: "number",
          onConfirm: _ctx.confirm_step_input
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "0",
          title: "当前取值(value)",
          type: "number",
          onConfirm: _ctx.confirm_value_input
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "#007aff",
          title: "滑块左侧已选择部分的线条颜色(active-color)",
          type: "text",
          onConfirm: _ctx.confirm_activeColor_input
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "#e9e9e9",
          title: "背景条的颜色(background-color)",
          type: "text",
          onConfirm: _ctx.confirm_backgroundColor_input
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "28",
          title: "滑块的大小(block-size)，取值范围为 12 - 28",
          type: "number",
          onConfirm: _ctx.confirm_block_size_input
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "#ffffff",
          title: "滑块颜色(block-color)",
          type: "text",
          onConfirm: _ctx.confirm_block_color_input
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "#888888",
          title: "Value颜色(value-color)",
          type: "text",
          onConfirm: _ctx.confirm_value_color_input
        }), null, 8 /* PROPS */, ["onConfirm"])
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
        createVNode(_component_page_head, utsMapOf({ title: "默认及使用" })),
        createElementVNode("view", utsMapOf({ class: "uni-title" }), "显示当前value"),
        createElementVNode("view", null, [
          createVNode(_component_slider, utsMapOf({
            onChange: _ctx.sliderChange,
            value: 50,
            "show-value": true
          }), null, 8 /* PROPS */, ["onChange"])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-title" }), "设置步进:step=10跳动"),
        createElementVNode("view", null, [
          createElementVNode("view", utsMapOf({ class: "uni-row" }), [
            createElementVNode("text", null, "0"),
            createElementVNode("text", utsMapOf({ class: "m-l-a" }), "100")
          ]),
          createVNode(_component_slider, utsMapOf({
            onChange: _ctx.sliderChange,
            value: 60,
            step: 10
          }), null, 8 /* PROPS */, ["onChange"])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-title" }), "浮点步进:step=0.01跳动"),
        createElementVNode("view", null, [
          createVNode(_component_slider, utsMapOf({
            value: 0.5,
            min: 0,
            max: 1,
            step: 0.01,
            "show-value": true
          }))
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-title" }), "设置最小/最大值"),
        createElementVNode("view", null, [
          createVNode(_component_slider, utsMapOf({
            onChange: _ctx.sliderChange,
            value: 100,
            min: 50,
            max: 200,
            "show-value": true
          }), null, 8 /* PROPS */, ["onChange"])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-title" }), "不同颜色和大小的滑块"),
        createElementVNode("view", null, [
          createVNode(_component_slider, utsMapOf({
            id: "slider-custom-color-and-size",
            onChange: _ctx.sliderChange,
            value: _ctx.sliderValue,
            backgroundColor: _ctx.sliderBackgroundColor,
            activeColor: _ctx.sliderActiveColor,
            activeBackgroundColor: _ctx.sliderActiveColor,
            blockColor: _ctx.sliderBlockColor,
            foreColor: _ctx.sliderBlockColor,
            "block-size": _ctx.sliderBlockSize
          }), null, 8 /* PROPS */, ["onChange", "value", "backgroundColor", "activeColor", "activeBackgroundColor", "blockColor", "foreColor", "block-size"])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-title" }), "暗黑模式"),
        createElementVNode("view", null, [
          createVNode(_component_slider, utsMapOf({
            "show-value": true,
            backgroundColor: "rgba(32,32,32,0.5)",
            valueColor: "#555"
          }))
        ]),
        createVNode(_component_navigator, utsMapOf({
          class: "uni-common-mb",
          url: "./slider-in-swiper"
        }), utsMapOf({
          default: withSlotCtx((): any[] => [
            createElementVNode("button", null, "slider in swiper")
          ]),
          _: 1 /* STABLE */
        })),
        createVNode(_component_navigator, utsMapOf({
          class: "uni-common-mb",
          url: "./slider-maxValue"
        }), utsMapOf({
          default: withSlotCtx((): any[] => [
            createElementVNode("button", null, "slider maxValue")
          ]),
          _: 1 /* STABLE */
        }))
      ])
    ], 4 /* STYLE */)
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesComponentSliderSliderStyles = [utsMapOf([["main", padStyleMapOf(utsMapOf([["paddingTop", 5], ["paddingRight", 0], ["paddingBottom", 5], ["paddingLeft", 0], ["borderBottomWidth", 1], ["borderBottomStyle", "solid"], ["borderBottomColor", "rgba(0,0,0,0.06)"], ["flexDirection", "row"], ["justifyContent", "center"]]))], ["m-l-a", padStyleMapOf(utsMapOf([["marginLeft", "auto"]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
import _easycom_boolean_data from '@/components/boolean-data/boolean-data.vue'
import _easycom_input_data from '@/components/input-data/input-data.vue'
