import _easycom_page_head from '@/components/page-head/page-head.vue'

const __sfc__ = defineComponent({
  __name: 'slider-100',
  setup(__props, { expose: __expose }: SetupContext): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

const title = ref('sliderx100通信性能测试(小程序卡为正常)')
const sliderValue = ref(50)
const safeAreaInsetsBottom = ref(0)
const sliderRef = ref<UniSliderElement[]>([])


function updateSliderValue(value: number) {

  // TODO 跳过vue框架，直接修改原生组件
  sliderRef.value.forEach((item) => {
    item.value = value
  })





}

function sliderChange(e: UniSliderChangeEvent) {
  updateSliderValue(e.detail.value)
}

function sliderChanging(e: UniSliderChangeEvent) {
  updateSliderValue(e.detail.value)
}

onReady(() => {
  safeAreaInsetsBottom.value = uni.getWindowInfo().safeAreaInsets.bottom
})

// 自动化测试
const updateSliderValueTest = (value : number) => {
  sliderValue.value = value
}
__expose({updateSliderValueTest})

return (): any | null => {

const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)
const _component_slider = resolveComponent("slider")

  return createElementVNode(Fragment, null, [
    createElementVNode("scroll-view", utsMapOf({ class: "page" }), [
      createVNode(_component_page_head, utsMapOf({ title: unref(title) }), null, 8 /* PROPS */, ["title"]),
      createElementVNode("view", utsMapOf({ class: "grid-view" }), [
        createElementVNode(Fragment, null, RenderHelpers.renderList(100, (_, index, __index, _cached): any => {
          return createVNode(_component_slider, utsMapOf({
            ref_for: true,
            ref_key: "sliderRef",
            ref: sliderRef,
            class: "slider",
            key: index,
            onChanging: sliderChanging,
            onChange: sliderChange,
            value: unref(sliderValue),
            "block-size": 20,
            "show-value": true
          }), null, 8 /* PROPS */, ["value"])
        }), 64 /* STABLE_FRAGMENT */)
      ])
    ]),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({'height': unref(safeAreaInsetsBottom)}))
    }), null, 4 /* STYLE */)
  ], 64 /* STABLE_FRAGMENT */)
}
}

})
export default __sfc__
const GenPagesTemplateSlider100Slider100Styles = [utsMapOf([["page", padStyleMapOf(utsMapOf([["flex", 1]]))], ["grid-view", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["flexWrap", "wrap"]]))], ["slider", padStyleMapOf(utsMapOf([["width", "25%"], ["marginTop", 1], ["marginLeft", 0], ["marginRight", 0], ["marginBottom", 1]]))]])]
