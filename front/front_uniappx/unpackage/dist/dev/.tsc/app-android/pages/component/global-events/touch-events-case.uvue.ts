
	const __sfc__ = defineComponent({
		data() {
			return {
				swiperChangeEvent: false,
				viewTouchEvent: false,
				swiperItemTouchEvent: false,
				swiperTouchEvent: false,
        stopPropagation: false,
        preventDefault: false,
        touchResult: ""
			}
		},
		methods: {
      changeStopPropagation(value: boolean) {
        this.stopPropagation = value
      },
      changePreventDefault(value: boolean) {
        this.preventDefault = value
      },
			swiperChange(e: UniSwiperChangeEvent) {
				console.log("swiperChange", e.detail.current, " at pages/component/global-events/touch-events-case.uvue:46")
				this.swiperChangeEvent = true
			},
			viewTouchStart(e: UniTouchEvent) {
				console.log("viewTouchStart", " at pages/component/global-events/touch-events-case.uvue:50")
        this.touchResult = "viewTouchStart"
				this.viewTouchEvent = true
        if (this.stopPropagation) e.stopPropagation()
				if (this.preventDefault) e.preventDefault()
			},
      viewTouchMove(e: UniTouchEvent) {
        console.log("viewTouchMove", " at pages/component/global-events/touch-events-case.uvue:57")
        this.touchResult = "viewTouchMove"
        this.viewTouchEvent = true
        if (this.stopPropagation) e.stopPropagation()
				if (this.preventDefault) e.preventDefault()
      },
      viewTouchEnd(e: UniTouchEvent) {
        console.log("viewTouchEnd", " at pages/component/global-events/touch-events-case.uvue:64")
        this.touchResult = "viewTouchEnd"
        if (this.stopPropagation) e.stopPropagation()
      },
			swiperItemTouchStart(e: UniTouchEvent) {
				console.log("swiperItemTouchStart", " at pages/component/global-events/touch-events-case.uvue:69")
        this.touchResult += " -> swiperItemTouchStart"
				this.swiperItemTouchEvent = true
			},
      swiperItemTouchMove(e: UniTouchEvent) {
        console.log("swiperItemTouchMove", " at pages/component/global-events/touch-events-case.uvue:74")
        this.touchResult += " -> swiperItemTouchMove"
      },
      swiperItemTouchEnd(e: UniTouchEvent) {
        console.log("swiperItemTouchEnd", " at pages/component/global-events/touch-events-case.uvue:78")
        this.touchResult += " -> swiperItemTouchEnd"
      },
			swiperTouchStart(e: UniTouchEvent) {
				console.log("swiperTouchStart", " at pages/component/global-events/touch-events-case.uvue:82")
        this.touchResult += " -> swiperTouchStart"
				this.swiperTouchEvent = true
			},
      swiperTouchMove(e: UniTouchEvent) {
        console.log("swiperTouchMove", " at pages/component/global-events/touch-events-case.uvue:87")
        this.touchResult += " -> swiperTouchMove"
      },
      swiperTouchEnd(e: UniTouchEvent) {
        console.log("swiperTouchEnd", " at pages/component/global-events/touch-events-case.uvue:91")
        this.touchResult += " -> swiperTouchEnd"
      },
      resetEvent() {
        this.swiperChangeEvent = false
        this.viewTouchEvent = false
        this.swiperItemTouchEvent = false
        this.swiperTouchEvent = false
        this.stopPropagation = true
        this.preventDefault = true
      },
      isPassTest1() {
        console.log("swiperChangeEvent:", this.swiperChangeEvent, " at pages/component/global-events/touch-events-case.uvue:103")
        return this.swiperChangeEvent == false
      },
      isPassTest2() {
        console.log("viewTouchEvent:", this.viewTouchEvent, " at pages/component/global-events/touch-events-case.uvue:107")
        console.log("swiperItemTouchEvent:", this.swiperItemTouchEvent, " at pages/component/global-events/touch-events-case.uvue:108")
        console.log("swiperTouchEvent:", this.swiperTouchEvent, " at pages/component/global-events/touch-events-case.uvue:109")
        return this.viewTouchEvent == true && this.swiperItemTouchEvent == true  && this.swiperTouchEvent == true
      }
		}
	})

export default __sfc__
function GenPagesComponentGlobalEventsTouchEventsCaseRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_boolean_data = resolveEasyComponent("boolean-data",_easycom_boolean_data)
const _component_navigator = resolveComponent("navigator")

  return createElementVNode("view", null, [
    createElementVNode("swiper", utsMapOf({
      ref: "header",
      class: "h-300",
      "indicator-dots": "true",
      circular: "true",
      onChange: _ctx.swiperChange,
      onTouchstart: _ctx.swiperTouchStart,
      onTouchmove: _ctx.swiperTouchMove,
      onTouchend: _ctx.swiperTouchEnd
    }), [
      createElementVNode(Fragment, null, RenderHelpers.renderList(3, (i, __key, __index, _cached): any => {
        return createElementVNode("swiper-item", utsMapOf({
          "item-id": i,
          onTouchstart: _ctx.swiperItemTouchStart,
          onTouchmove: _ctx.swiperItemTouchMove,
          onTouchend: _ctx.swiperItemTouchEnd
        }), [
          createElementVNode("view", utsMapOf({
            class: "h-300 header-tiem",
            onTouchstart: _ctx.viewTouchStart,
            onTouchmove: _ctx.viewTouchMove,
            onTouchend: _ctx.viewTouchEnd
          }), [
            createElementVNode("text", null, toDisplayString(i), 1 /* TEXT */)
          ], 40 /* PROPS, NEED_HYDRATION */, ["onTouchstart", "onTouchmove", "onTouchend"])
        ], 40 /* PROPS, NEED_HYDRATION */, ["item-id", "onTouchstart", "onTouchmove", "onTouchend"])
      }), 64 /* STABLE_FRAGMENT */)
    ], 40 /* PROPS, NEED_HYDRATION */, ["onChange", "onTouchstart", "onTouchmove", "onTouchend"]),
    createElementVNode("view", utsMapOf({ class: "content" }), [
      createVNode(_component_boolean_data, utsMapOf({
        defaultValue: false,
        title: "stopPropagation",
        onChange: _ctx.changeStopPropagation
      }), null, 8 /* PROPS */, ["onChange"]),
      createVNode(_component_boolean_data, utsMapOf({
        defaultValue: false,
        title: "preventDefault",
        onChange: _ctx.changePreventDefault
      }), null, 8 /* PROPS */, ["onChange"]),
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"padding":"10px"}))
      }), toDisplayString(_ctx.touchResult), 5 /* TEXT, STYLE */),
      createElementVNode("view", utsMapOf({
        class: "uni-padding-wrap uni-common-mt",
        style: normalizeStyle(utsMapOf({"bottom":"20px"}))
      }), [
        createVNode(_component_navigator, utsMapOf({
          url: "/pages/component/global-events/touch-events-preventDefault",
          "hover-class": "none"
        }), utsMapOf({
          default: withSlotCtx((): any[] => [
            createElementVNode("button", utsMapOf({
              type: "default",
              class: "button"
            }), " 测试 preventDefault ")
          ]),
          _: 1 /* STABLE */
        }))
      ], 4 /* STYLE */)
    ])
  ])
}
const GenPagesComponentGlobalEventsTouchEventsCaseStyles = [utsMapOf([["h-300", padStyleMapOf(utsMapOf([["height", 300]]))], ["header-tiem", padStyleMapOf(utsMapOf([["backgroundColor", "#89ff8d"], ["alignItems", "center"], ["justifyContent", "center"]]))]])]

import _easycom_boolean_data from '@/components/boolean-data/boolean-data.vue'
