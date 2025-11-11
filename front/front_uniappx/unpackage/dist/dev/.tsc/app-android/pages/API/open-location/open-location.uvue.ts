import _easycom_page_head from '@/components/page-head/page-head.vue'
import {
    state,
    setLifeCycleNum
  } from '@/store/index.uts'

  // 响应式数据
  type DialogPagesNum = { __$originalPosition?: UTSSourceMapPosition<"DialogPagesNum", "pages/API/open-location/open-location.uvue", 51, 8>;
    value: number
  }

  
const __sfc__ = defineComponent({
  __name: 'open-location',
  setup(__props, { expose: __expose }: SetupContext): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

  const title = ref('openLocation')
  const longitude = ref(116.39747)
  const latitude = ref(39.9085)
  const name = ref('天安门')
  const address = ref('北京市东城区东长安街')
  // 自动化测试
  const dialogPagesNum = reactive({ value: -1 } as DialogPagesNum)

  // 生命周期钩子
  onPageShow(() => {
    console.log("Page Show", " at pages/API/open-location/open-location.uvue:71")
    // 自动化测试
    setLifeCycleNum(state.lifeCycleNum + 1)
  })

  onPageHide(() => {
    console.log("Page Hide", " at pages/API/open-location/open-location.uvue:77")
    // 自动化测试
    setLifeCycleNum(state.lifeCycleNum - 1)
  })

  // 自动化测试
  const test = () => {
    const pages = getCurrentPages()
    const page = pages[pages.length - 1]

    const dialogPages = page.getDialogPages()
    dialogPagesNum.value = dialogPages.length

  }

  // 方法
  const openLocation = () => {
    uni.openLocation({
      longitude: longitude.value,
      latitude: latitude.value,
      name: name.value,
      address: address.value
    })
    // 自动化测试
    setTimeout(() => {
      test()
    }, 500)
  }

  // 自动化测试
  const pageSetLifeCycleNum = (value: number) => {
    setLifeCycleNum(value)
  }

  // 自动化测试
  const getLifeCycleNum = (): number => {
    return state.lifeCycleNum
  }

  __expose({
    dialogPagesNum,
    openLocation,
    pageSetLifeCycleNum,
    getLifeCycleNum,
  })

return (): any | null => {

const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)
const _component_form = resolveComponent("form")

  return createElementVNode("view", null, [
    createVNode(_component_page_head, utsMapOf({ title: unref(title) }), null, 8 /* PROPS */, ["title"]),
    createElementVNode("view", utsMapOf({ class: "uni-common-mt" }), [
      createVNode(_component_form, utsMapOf({ onSubmit: openLocation }), utsMapOf({
        default: withSlotCtx((): any[] => [
          createElementVNode("view", utsMapOf({ class: "uni-list" }), [
            createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
              createElementVNode("view", utsMapOf({ class: "uni-list-cell-left" }), [
                createElementVNode("view", utsMapOf({ class: "uni-label" }), "经度")
              ]),
              createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), [
                createElementVNode("input", utsMapOf({
                  modelValue: unref(longitude),
                  onInput: ($event: InputEvent) => {trySetRefValue(longitude, looseToNumber($event.detail.value))},
                  class: "uni-input",
                  type: "text",
                  disabled: true
                }), null, 40 /* PROPS, NEED_HYDRATION */, ["modelValue"])
              ])
            ]),
            createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
              createElementVNode("view", utsMapOf({ class: "uni-list-cell-left" }), [
                createElementVNode("view", utsMapOf({ class: "uni-label" }), "纬度")
              ]),
              createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), [
                createElementVNode("input", utsMapOf({
                  modelValue: unref(latitude),
                  onInput: ($event: InputEvent) => {trySetRefValue(latitude, looseToNumber($event.detail.value))},
                  class: "uni-input",
                  type: "text",
                  disabled: true
                }), null, 40 /* PROPS, NEED_HYDRATION */, ["modelValue"])
              ])
            ]),
            createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
              createElementVNode("view", utsMapOf({ class: "uni-list-cell-left" }), [
                createElementVNode("view", utsMapOf({ class: "uni-label" }), "位置名称")
              ]),
              createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), [
                createElementVNode("input", utsMapOf({
                  modelValue: unref(name),
                  onInput: ($event: InputEvent) => {trySetRefValue(name, $event.detail.value)},
                  class: "uni-input",
                  type: "text",
                  disabled: true
                }), null, 40 /* PROPS, NEED_HYDRATION */, ["modelValue"])
              ])
            ]),
            createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
              createElementVNode("view", utsMapOf({ class: "uni-list-cell-left" }), [
                createElementVNode("view", utsMapOf({ class: "uni-label" }), "详细位置")
              ]),
              createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), [
                createElementVNode("input", utsMapOf({
                  modelValue: unref(address),
                  onInput: ($event: InputEvent) => {trySetRefValue(address, $event.detail.value)},
                  class: "uni-input",
                  type: "text",
                  disabled: true
                }), null, 40 /* PROPS, NEED_HYDRATION */, ["modelValue"])
              ])
            ])
          ]),
          createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
            createElementVNode("view", utsMapOf({ class: "tips" }), "注意：需要正确配置地图服务商的Key才能正常显示位置"),
            createElementVNode("view", utsMapOf({ class: "uni-btn-v uni-common-mt" }), [
              createElementVNode("button", utsMapOf({
                type: "primary",
                formType: "submit"
              }), "查看位置")
            ])
          ])
        ]),
        _: 1 /* STABLE */
      }))
    ])
  ])
}
}

})
export default __sfc__
const GenPagesAPIOpenLocationOpenLocationStyles = [utsMapOf([["uni-list-cell-left", padStyleMapOf(utsMapOf([["paddingTop", 0], ["paddingRight", 15], ["paddingBottom", 0], ["paddingLeft", 15]]))], ["tips", padStyleMapOf(utsMapOf([["fontSize", 12], ["marginTop", 15], ["opacity", 0.8]]))]])]
