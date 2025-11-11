
  type ScrollEventTest = { __$originalPosition?: UTSSourceMapPosition<"ScrollEventTest", "pages/component/scroll-view/scroll-view.uvue", 68, 8>;
    type : string;
    target : UniElement | null;
    currentTarget : UniElement | null;
    direction ?: string
  }
  const __sfc__ = defineComponent({
    data() {
      return {
        scrollTop: 0,
        oldScrollTop: 0,
        scrollLeft: 120,
        showScrollbar: true,
        // 自动化测试
        isScrollTest: '',
        isScrolltolowerTest: '',
        isScrolltoupperTest: '',
        scrollDetailTest: null as UniScrollEventDetail | null,
        scrollEndDetailTest: null as UniScrollEventDetail | null,
      }
    },
    methods: {
      upper: function (e : UniScrollToUpperEvent) {
        console.log('滚动到顶部/左边', e, " at pages/component/scroll-view/scroll-view.uvue:91")
        this.checkEventTest({
          type: e.type,
          target: e.target,
          currentTarget: e.currentTarget,
          direction: e.detail.direction,
        } as ScrollEventTest, 'scrolltoupper')
      },
      lower: function (e : UniScrollToLowerEvent) {
        console.log('滚动到底部/右边', e, " at pages/component/scroll-view/scroll-view.uvue:100")
        this.checkEventTest({
          type: e.type,
          target: e.target,
          currentTarget: e.currentTarget,
          direction: e.detail.direction,
        } as ScrollEventTest, 'scrolltolower')
      },
      scroll: function (e : UniScrollEvent) {
        this.scrollDetailTest = e.detail
        this.checkEventTest({
          type: e.type,
          target: e.target,
          currentTarget: e.currentTarget
        } as ScrollEventTest, 'scroll')
        this.oldScrollTop = e.detail.scrollTop
      },
      end: function (e : UniScrollEvent) {
        console.log('滚动结束时触发', e, " at pages/component/scroll-view/scroll-view.uvue:118")
        this.scrollEndDetailTest = e.detail
        this.checkEventTest({
          type: e.type,
          target: e.target,
          currentTarget: e.currentTarget
        } as ScrollEventTest, 'scrollend')
      },
      goTop: function () {
        // 解决view层不同步的问题
        this.scrollTop = this.oldScrollTop
        this.$nextTick(() => {
          this.scrollTop = 0
        })
        uni.showToast({
          icon: 'none',
          title: '纵向滚动 scrollTop 值已被修改为 0',
        })
      },
      // 自动化测试专用（由于事件event参数对象中存在循环引用，在ios端JSON.stringify报错，自动化测试无法page.data获取）
      checkEventTest(e : ScrollEventTest, eventName : String) {

        const isPass = e.type === eventName && e.target instanceof UniElement && e.currentTarget instanceof UniElement;




        const result = isPass ? `${eventName}:Success` : `${eventName}:Fail`;
        switch (eventName) {
          case 'scroll':
            this.isScrollTest = result
            break;
          case 'scrolltolower':
            this.isScrolltolowerTest = result + `-${e.direction}`
            break;
          case 'scrolltoupper':
            this.isScrolltoupperTest = result + `-${e.direction}`
            break;
          default:
            break;
        }
      },
      // 自动化测试专用
      setVerticalScrollBy(y : number) {
        const element = uni.getElementById("verticalScrollView")
        if (element != null) {
          element.scrollBy(0, y)
        }
      }
    },
  })

export default __sfc__
function GenPagesComponentScrollViewScrollViewRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)
const _component_navigator = resolveComponent("navigator")

  return createElementVNode("scroll-view", utsMapOf({ class: "page-scroll-view" }), [
    createElementVNode("view", null, [
      createVNode(_component_page_head, utsMapOf({ title: "scroll-view,区域滚动视图" })),
      createElementVNode("view", utsMapOf({ class: "uni-padding-wrap uni-common-mt" }), [
        createElementVNode("view", utsMapOf({ class: "uni-title uni-common-mt" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "Vertical Scroll"),
          createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "纵向滚动")
        ]),
        createElementVNode("view", null, [
          createElementVNode("scroll-view", utsMapOf({
            "scroll-top": _ctx.scrollTop,
            direction: "vertical",
            class: "scroll-Y",
            "scroll-with-animation": "true",
            onScrolltoupper: _ctx.upper,
            onScrolltolower: _ctx.lower,
            onScroll: _ctx.scroll,
            onScrollend: _ctx.end,
            "show-scrollbar": _ctx.showScrollbar,
            id: "verticalScrollView"
          }), [
            createElementVNode("view", utsMapOf({ class: "scroll-view-item uni-bg-red" }), [
              createElementVNode("text", utsMapOf({ class: "text" }), "A")
            ]),
            createElementVNode("view", utsMapOf({ class: "scroll-view-item uni-bg-green" }), [
              createElementVNode("text", utsMapOf({ class: "text" }), "B")
            ]),
            createElementVNode("view", utsMapOf({ class: "scroll-view-item uni-bg-blue" }), [
              createElementVNode("text", utsMapOf({ class: "text" }), "C")
            ])
          ], 40 /* PROPS, NEED_HYDRATION */, ["scroll-top", "onScrolltoupper", "onScrolltolower", "onScroll", "onScrollend", "show-scrollbar"])
        ]),
        createElementVNode("view", utsMapOf({
          onClick: _ctx.goTop,
          class: "uni-center uni-common-mt"
        }), [
          createElementVNode("text", utsMapOf({ class: "uni-link" }), "点击这里返回顶部")
        ], 8 /* PROPS */, ["onClick"]),
        createElementVNode("view", utsMapOf({ class: "uni-title uni-common-mt" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "Horizontal Scroll"),
          createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "横向滚动")
        ]),
        createElementVNode("view", null, [
          createElementVNode("scroll-view", utsMapOf({
            class: "scroll-view_H",
            direction: "horizontal",
            onScroll: _ctx.scroll,
            onScrollend: _ctx.end,
            "scroll-left": _ctx.scrollLeft,
            "show-scrollbar": _ctx.showScrollbar
          }), [
            createElementVNode("view", utsMapOf({ class: "scroll-view-item_H uni-bg-red" }), [
              createElementVNode("text", utsMapOf({ class: "text" }), "A")
            ]),
            createElementVNode("view", utsMapOf({ class: "scroll-view-item_H uni-bg-green" }), [
              createElementVNode("text", utsMapOf({ class: "text" }), "B")
            ]),
            createElementVNode("view", utsMapOf({ class: "scroll-view-item_H uni-bg-blue" }), [
              createElementVNode("text", utsMapOf({ class: "text" }), "C")
            ])
          ], 40 /* PROPS, NEED_HYDRATION */, ["onScroll", "onScrollend", "scroll-left", "show-scrollbar"])
        ]),
        createVNode(_component_navigator, utsMapOf({
          url: "/pages/component/scroll-view/scroll-view-props",
          "hover-class": "none"
        }), utsMapOf({
          default: withSlotCtx((): any[] => [
            createElementVNode("button", utsMapOf({
              type: "primary",
              class: "button"
            }), " 非下拉刷新的属性示例 ")
          ]),
          _: 1 /* STABLE */
        })),
        createElementVNode("view", utsMapOf({ class: "uni-common-pb" })),
        createVNode(_component_navigator, utsMapOf({
          url: "/pages/component/scroll-view/scroll-view-refresher-props",
          "hover-class": "none"
        }), utsMapOf({
          default: withSlotCtx((): any[] => [
            createElementVNode("button", utsMapOf({
              type: "primary",
              class: "button"
            }), " 下拉刷新的属性示例 ")
          ]),
          _: 1 /* STABLE */
        })),
        createElementVNode("view", utsMapOf({ class: "uni-common-pb" })),
        createVNode(_component_navigator, utsMapOf({
          url: "/pages/component/scroll-view/scroll-view-refresher",
          "hover-class": "none"
        }), utsMapOf({
          default: withSlotCtx((): any[] => [
            createElementVNode("button", utsMapOf({
              type: "primary",
              class: "button"
            }), " 默认下拉刷新示例 ")
          ]),
          _: 1 /* STABLE */
        })),
        createElementVNode("view", utsMapOf({ class: "uni-common-pb" })),
        createVNode(_component_navigator, utsMapOf({
          url: "/pages/component/scroll-view/scroll-view-custom-refresher-props",
          "hover-class": "none"
        }), utsMapOf({
          default: withSlotCtx((): any[] => [
            createElementVNode("button", utsMapOf({
              type: "primary",
              class: "button"
            }), " 自定义下拉刷新示例 ")
          ]),
          _: 1 /* STABLE */
        })),
        createElementVNode("view", utsMapOf({ class: "uni-common-pb" }))
      ])
    ])
  ])
}
const GenPagesComponentScrollViewScrollViewStyles = [utsMapOf([["scroll-Y", padStyleMapOf(utsMapOf([["height", 150]]))], ["scroll-view_H", padStyleMapOf(utsMapOf([["width", "100%"], ["flexDirection", "row"]]))], ["scroll-view-item", padStyleMapOf(utsMapOf([["height", 150], ["justifyContent", "center"], ["alignItems", "center"]]))], ["scroll-view-item_H", padStyleMapOf(utsMapOf([["width", "100%"], ["height", 150], ["justifyContent", "center"], ["alignItems", "center"]]))], ["text", padStyleMapOf(utsMapOf([["fontSize", 18], ["color", "#ffffff"]]))], ["button", padStyleMapOf(utsMapOf([["marginTop", 15]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
