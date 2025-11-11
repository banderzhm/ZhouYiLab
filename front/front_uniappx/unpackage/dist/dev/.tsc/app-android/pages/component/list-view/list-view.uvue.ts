
  type ScrollEventTest = { __$originalPosition?: UTSSourceMapPosition<"ScrollEventTest", "pages/component/list-view/list-view.uvue", 2, 8>;
    type : string;
    target : UniElement | null;
    currentTarget : UniElement | null;
    direction ?: string
  }
  import { ItemType } from '@/components/enum-data/enum-data-types'
  const __sfc__ = defineComponent({
    data() {
      return {
        refresher_triggered_boolean: false,
        refresher_enabled_boolean: false,
        scroll_with_animation_boolean: false,
        show_scrollbar_boolean: false,
        bounces_boolean: true,
        scroll_y_boolean: true,
        scroll_x_boolean: false,
        scroll_direction: "vertical",
        upper_threshold_input: 50,
        lower_threshold_input: 50,
        scroll_top_input: 0,
        scroll_left_input: 0,
        refresher_background_input: "#FFF",
        scrollData: [] as Array<string>,
        size_enum: [{ "value": 0, "name": "item---0" }, { "value": 3, "name": "item---3" }] as ItemType[],
        scrollIntoView: "",
        refresherrefresh: false,
        refresher_default_style_input: "black",
        text: ['继续下拉执行刷新', '释放立即刷新', '刷新中', ""],
        state: 3,
        reset: true,
        // 自动化测试
        isScrollTest: '',
        isScrolltolowerTest: '',
        isScrolltoupperTest: '',
        scrollDetailTest: null as UniScrollEventDetail | null,
        scrollEndDetailTest: null as UniScrollEventDetail | null,
      }
    },
    onLoad() {
      let lists : Array<string> = []
      for (let i = 0; i < 10; i++) {
        lists.push("item---" + i)
      }
      this.scrollData = lists
    },
    methods: {
      list_view_click() { console.log("组件被点击时触发", " at pages/component/list-view/list-view.uvue:49") },
      list_view_touchstart() { console.log("手指触摸动作开始", " at pages/component/list-view/list-view.uvue:50") },
      list_view_touchmove() { console.log("手指触摸后移动", " at pages/component/list-view/list-view.uvue:51") },
      list_view_touchcancel() { console.log("手指触摸动作被打断，如来电提醒，弹窗", " at pages/component/list-view/list-view.uvue:52") },
      list_view_touchend() { console.log("手指触摸动作结束", " at pages/component/list-view/list-view.uvue:53") },
      list_view_tap() { console.log("手指触摸后马上离开", " at pages/component/list-view/list-view.uvue:54") },
      list_view_longpress() { console.log("如果一个组件被绑定了 longpress 事件，那么当用户长按这个组件时，该事件将会被触发。", " at pages/component/list-view/list-view.uvue:55") },
      list_view_refresherpulling(e : RefresherEvent) {
        console.log("下拉刷新控件被下拉", " at pages/component/list-view/list-view.uvue:57")
        if (this.reset) {
          if (e.detail.dy > 45) {
            this.state = 1
          } else {
            this.state = 0
          }
        }
      },
      list_view_refresherrefresh() {
        console.log("下拉刷新被触发 ", " at pages/component/list-view/list-view.uvue:67")
        this.refresherrefresh = true
        this.refresher_triggered_boolean = true
        this.state = 2
        this.reset = false;
        setTimeout(() => {
          this.refresher_triggered_boolean = false
        }, 1500)
      },
      list_view_refresherrestore() {
        this.refresherrefresh = false
        this.state = 3
        this.reset = true
        console.log("下拉刷新被复位", " at pages/component/list-view/list-view.uvue:80")
      },
      list_view_refresherabort() { console.log("下拉刷新被中止", " at pages/component/list-view/list-view.uvue:82") },
      list_view_scrolltoupper(e : UniScrollToUpperEvent) {
        console.log("滚动到顶部/左边，会触发 scrolltoupper 事件  direction=" + e.detail.direction, " at pages/component/list-view/list-view.uvue:84")
        this.checkEventTest({
          type: e.type,
          target: e.target,
          currentTarget: e.currentTarget,
          direction: e.detail.direction,
        } as ScrollEventTest, 'scrolltoupper')
      },
      list_view_scrolltolower(e : UniScrollToLowerEvent) {
        console.log("滚动到底部/右边，会触发 scrolltolower 事件  direction=" + e.detail.direction, " at pages/component/list-view/list-view.uvue:93")
        this.checkEventTest({
          type: e.type,
          target: e.target,
          currentTarget: e.currentTarget,
          direction: e.detail.direction,
        } as ScrollEventTest, 'scrolltolower')
      },
      list_view_scroll(e : UniScrollEvent) {
        console.log("滚动时触发，event.detail = {scrollLeft, scrollTop, scrollHeight, scrollWidth, deltaX, deltaY}", " at pages/component/list-view/list-view.uvue:102")
        this.scrollDetailTest = e.detail
        this.checkEventTest({
          type: e.type,
          target: e.target,
          currentTarget: e.currentTarget
        } as ScrollEventTest, 'scroll')
      },
      list_view_scrollend(e : UniScrollEvent) {
        console.log("滚动结束时触发", e.detail, " at pages/component/list-view/list-view.uvue:111")
        this.scrollEndDetailTest = e.detail
        this.checkEventTest({
          type: e.type,
          target: e.target,
          currentTarget: e.currentTarget
        } as ScrollEventTest, 'scrollend')
      },
      list_item_click() { console.log("list-item组件被点击时触发", " at pages/component/list-view/list-view.uvue:119") },
      list_item_touchstart() { console.log("手指触摸list-item组件动作开始", " at pages/component/list-view/list-view.uvue:120") },
      list_item_touchmove() { console.log("手指触摸list-item组件后移动", " at pages/component/list-view/list-view.uvue:121") },
      list_item_touchcancel() { console.log("手指触摸list-item组件动作被打断，如来电提醒，弹窗", " at pages/component/list-view/list-view.uvue:122") },
      list_item_touchend() { console.log("手指触摸list-item组件动作结束", " at pages/component/list-view/list-view.uvue:123") },
      list_item_tap() { console.log("手指触摸list-item组件后马上离开", " at pages/component/list-view/list-view.uvue:124") },
      list_item_longpress() { console.log("list-item组件被绑定了 longpress 事件，那么当用户长按这个组件时，该事件将会被触发。", " at pages/component/list-view/list-view.uvue:125") },
      change_refresher_triggered_boolean(checked : boolean) { this.refresher_triggered_boolean = checked },
      change_refresher_enabled_boolean(checked : boolean) { this.refresher_enabled_boolean = checked },
      change_scroll_with_animation_boolean(checked : boolean) { this.scroll_with_animation_boolean = checked },
      change_show_scrollbar_boolean(checked : boolean) { this.show_scrollbar_boolean = checked },
      change_bounces_boolean(checked : boolean) { this.bounces_boolean = checked },
      change_scroll_y_boolean(checked : boolean) {
        this.scroll_y_boolean = checked
        this.change_scroll_direction()
      },
      change_scroll_x_boolean(checked : boolean) {
        this.scroll_x_boolean = checked
        this.change_scroll_direction()
      },
      change_scroll_direction() {
        if (this.scroll_y_boolean && this.scroll_x_boolean || this.scroll_y_boolean) {
          this.scroll_direction = "vertical"
        } else if (!this.scroll_y_boolean && !this.scroll_x_boolean) {
          this.scroll_direction = "none"
        } else if (!this.scroll_y_boolean && this.scroll_x_boolean) {
          this.scroll_direction = "horizontal"
        }
      },
      confirm_upper_threshold_input(value : number) { this.upper_threshold_input = value },
      confirm_lower_threshold_input(value : number) { this.lower_threshold_input = value },
      confirm_scroll_top_input(value : number) { this.scroll_top_input = value },
      confirm_scroll_left_input(value : number) { this.scroll_left_input = value },
      confirm_refresher_background_input(value : string) { this.refresher_background_input = value },
      item_change_size_enum(index : number) { this.scrollIntoView = "item---" + index },
      //自动化测试专用
      setScrollIntoView(id : string) { this.scrollIntoView = id },
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
      //自动化测试例专用
      check_scroll_height() : Boolean {
        var listElement = this.$refs["listview"] as UniElement
        console.log("check_scroll_height--" + listElement.scrollHeight, " at pages/component/list-view/list-view.uvue:177")
        if (listElement.scrollHeight > 2000) {
          return true
        }
        return false
      },
      //自动化测试例专用
      check_scroll_width() : Boolean {
        var listElement = this.$refs["listview"] as UniElement
        console.log("check_scroll_width" + listElement.scrollWidth, " at pages/component/list-view/list-view.uvue:186")
        if (listElement.scrollWidth > 2000) {
          return true
        }
        return false
      },
      change_refresher_style_boolean(checked : boolean) {
        if (checked) {
          this.refresher_default_style_input = "none"
        } else {
          this.refresher_default_style_input = "black"
        }
      }
    }
  })

export default __sfc__
function GenPagesComponentListViewListViewRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_boolean_data = resolveEasyComponent("boolean-data",_easycom_boolean_data)
const _component_input_data = resolveEasyComponent("input-data",_easycom_input_data)
const _component_enum_data = resolveEasyComponent("enum-data",_easycom_enum_data)
const _component_navigator = resolveComponent("navigator")

  return createElementVNode(Fragment, null, [
    createElementVNode("view", utsMapOf({ class: "main" }), [
      createElementVNode("list-view", utsMapOf({
        direction: _ctx.scroll_direction,
        bounces: _ctx.bounces_boolean,
        "upper-threshold": _ctx.upper_threshold_input,
        "lower-threshold": _ctx.lower_threshold_input,
        "scroll-top": _ctx.scroll_top_input,
        "scroll-left": _ctx.scroll_left_input,
        "show-scrollbar": _ctx.show_scrollbar_boolean,
        "scroll-into-view": _ctx.scrollIntoView,
        "scroll-with-animation": _ctx.scroll_with_animation_boolean,
        "refresher-enabled": _ctx.refresher_enabled_boolean,
        "refresher-background": _ctx.refresher_background_input,
        "refresher-triggered": _ctx.refresher_triggered_boolean,
        "refresher-default-style": _ctx.refresher_default_style_input,
        onClick: [_ctx.list_view_click, _ctx.list_view_tap],
        onTouchstart: _ctx.list_view_touchstart,
        onTouchmove: _ctx.list_view_touchmove,
        onTouchcancel: _ctx.list_view_touchcancel,
        onTouchend: _ctx.list_view_touchend,
        onLongpress: _ctx.list_view_longpress,
        onRefresherpulling: _ctx.list_view_refresherpulling,
        onRefresherrefresh: _ctx.list_view_refresherrefresh,
        onRefresherrestore: _ctx.list_view_refresherrestore,
        onRefresherabort: _ctx.list_view_refresherabort,
        onScrolltoupper: _ctx.list_view_scrolltoupper,
        ref: "listview",
        onScrolltolower: _ctx.list_view_scrolltolower,
        onScroll: _ctx.list_view_scroll,
        onScrollend: _ctx.list_view_scrollend,
        style: normalizeStyle(utsMapOf({"width":"100%"})),
        id: "listview"
      }), [
        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.scrollData, (key, __key, __index, _cached): any => {
          return createElementVNode("list-item", utsMapOf({
            key: key,
            id: key,
            onClick: [_ctx.list_item_click, _ctx.list_item_tap],
            onTouchstart: _ctx.list_item_touchstart,
            onTouchmove: _ctx.list_item_touchmove,
            onTouchcancel: _ctx.list_item_touchcancel,
            onTouchend: _ctx.list_item_touchend,
            onLongpress: _ctx.list_item_longpress,
            class: "list-item"
          }), [
            createElementVNode("text", null, toDisplayString(key), 1 /* TEXT */)
          ], 40 /* PROPS, NEED_HYDRATION */, ["id", "onClick", "onTouchstart", "onTouchmove", "onTouchcancel", "onTouchend", "onLongpress"])
        }), 128 /* KEYED_FRAGMENT */),
        createElementVNode("list-item", utsMapOf({
          slot: "refresher",
          class: "refresh-box"
        }), [
          createElementVNode("text", utsMapOf({ class: "tip-text" }), toDisplayString(_ctx.text[_ctx.state]), 1 /* TEXT */)
        ])
      ], 44 /* STYLE, PROPS, NEED_HYDRATION */, ["direction", "bounces", "upper-threshold", "lower-threshold", "scroll-top", "scroll-left", "show-scrollbar", "scroll-into-view", "scroll-with-animation", "refresher-enabled", "refresher-background", "refresher-triggered", "refresher-default-style", "onClick", "onTouchstart", "onTouchmove", "onTouchcancel", "onTouchend", "onLongpress", "onRefresherpulling", "onRefresherrefresh", "onRefresherrestore", "onRefresherabort", "onScrolltoupper", "onScrolltolower", "onScroll", "onScrollend"])
    ]),
    createElementVNode("scroll-view", utsMapOf({
      style: normalizeStyle(utsMapOf({"flex":"1"})),
      direction: "vertical"
    }), [
      createElementVNode("view", utsMapOf({ class: "content" }), [
        createVNode(_component_boolean_data, utsMapOf({
          defaultValue: false,
          title: "设置当前下拉刷新状态，true 表示下拉刷新已经被触发，false 表示下拉刷新未被触发",
          onChange: _ctx.change_refresher_triggered_boolean
        }), null, 8 /* PROPS */, ["onChange"]),
        createVNode(_component_boolean_data, utsMapOf({
          defaultValue: false,
          title: "开启下拉刷新",
          onChange: _ctx.change_refresher_enabled_boolean
        }), null, 8 /* PROPS */, ["onChange"]),
        createVNode(_component_boolean_data, utsMapOf({
          defaultValue: false,
          title: "开启自定义样式",
          onChange: _ctx.change_refresher_style_boolean
        }), null, 8 /* PROPS */, ["onChange"]),
        createVNode(_component_boolean_data, utsMapOf({
          defaultValue: false,
          title: "是否在设置滚动条位置时使用滚动动画，设置false没有滚动动画",
          onChange: _ctx.change_scroll_with_animation_boolean
        }), null, 8 /* PROPS */, ["onChange"]),
        createVNode(_component_boolean_data, utsMapOf({
          defaultValue: false,
          title: "控制是否出现滚动条",
          onChange: _ctx.change_show_scrollbar_boolean
        }), null, 8 /* PROPS */, ["onChange"]),
        createVNode(_component_boolean_data, utsMapOf({
          defaultValue: true,
          title: "控制是否回弹效果",
          onChange: _ctx.change_bounces_boolean
        }), null, 8 /* PROPS */, ["onChange"]),
        createVNode(_component_boolean_data, utsMapOf({
          defaultValue: true,
          title: "允许纵向滚动",
          onChange: _ctx.change_scroll_y_boolean
        }), null, 8 /* PROPS */, ["onChange"]),
        createVNode(_component_boolean_data, utsMapOf({
          defaultValue: false,
          title: "允许横向滚动",
          onChange: _ctx.change_scroll_x_boolean
        }), null, 8 /* PROPS */, ["onChange"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "50",
          title: "距顶部/左边多远时（单位px），触发 scrolltoupper 事件",
          type: "number",
          onConfirm: _ctx.confirm_upper_threshold_input
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "50",
          title: "距底部/右边多远时（单位px），触发 scrolltolower 事件",
          type: "number",
          onConfirm: _ctx.confirm_lower_threshold_input
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "0",
          title: "设置竖向滚动条位置",
          type: "number",
          onConfirm: _ctx.confirm_scroll_top_input
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "0",
          title: "设置横向滚动条位置",
          type: "number",
          onConfirm: _ctx.confirm_scroll_left_input
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "#FFF",
          title: "设置下拉刷新区域背景颜色",
          type: "text",
          onConfirm: _ctx.confirm_refresher_background_input
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createVNode(_component_enum_data, utsMapOf({
          items: _ctx.size_enum,
          title: "通过id位置跳转",
          onChange: _ctx.item_change_size_enum
        }), null, 8 /* PROPS */, ["items", "onChange"]),
        createVNode(_component_navigator, utsMapOf({ url: "/pages/component/list-view/list-view-refresh" }), utsMapOf({
          default: withSlotCtx((): any[] => [
            createElementVNode("button", utsMapOf({
              type: "primary",
              class: "button"
            }), " list-view 下拉刷新 ")
          ]),
          _: 1 /* STABLE */
        })),
        createVNode(_component_navigator, utsMapOf({ url: "/pages/component/list-view/list-view-multiplex" }), utsMapOf({
          default: withSlotCtx((): any[] => [
            createElementVNode("button", utsMapOf({
              type: "primary",
              class: "button"
            }), " list-view 对list-item复用测试 ")
          ]),
          _: 1 /* STABLE */
        })),
        createVNode(_component_navigator, utsMapOf({ url: "/pages/component/list-view/list-view-multiplex-input" }), utsMapOf({
          default: withSlotCtx((): any[] => [
            createElementVNode("button", utsMapOf({
              type: "primary",
              class: "button"
            }), " list-view 复用测试（item中嵌入input） ")
          ]),
          _: 1 /* STABLE */
        })),
        createVNode(_component_navigator, utsMapOf({ url: "/pages/component/list-view/list-view-multiplex-video" }), utsMapOf({
          default: withSlotCtx((): any[] => [
            createElementVNode("button", utsMapOf({
              type: "primary",
              class: "button"
            }), " list-view 复用测试（item中嵌入video） ")
          ]),
          _: 1 /* STABLE */
        })),
        createVNode(_component_navigator, utsMapOf({ url: "/pages/component/list-view/list-view-children-in-slot" }), utsMapOf({
          default: withSlotCtx((): any[] => [
            createElementVNode("button", utsMapOf({
              type: "primary",
              class: "button"
            }), " list-view 测试插槽中子组件增删 ")
          ]),
          _: 1 /* STABLE */
        })),
        createVNode(_component_navigator, utsMapOf({ url: "/pages/component/list-view/list-view-children-if-show" }), utsMapOf({
          default: withSlotCtx((): any[] => [
            createElementVNode("button", utsMapOf({
              type: "primary",
              class: "button"
            }), " list-item v-if v-show 组合增删 ")
          ]),
          _: 1 /* STABLE */
        })),
        createVNode(_component_navigator, utsMapOf({ url: "/pages/component/list-view/list-view-long-press" }), utsMapOf({
          default: withSlotCtx((): any[] => [
            createElementVNode("button", utsMapOf({
              type: "primary",
              class: "button"
            }), " list-item 中设置长按事件测试 ")
          ]),
          _: 1 /* STABLE */
        }))
      ])
    ], 4 /* STYLE */)
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesComponentListViewListViewStyles = [utsMapOf([["main", padStyleMapOf(utsMapOf([["maxHeight", 250], ["paddingTop", 5], ["paddingRight", 0], ["paddingBottom", 5], ["paddingLeft", 0], ["borderBottomWidth", 1], ["borderBottomStyle", "solid"], ["borderBottomColor", "rgba(0,0,0,0.06)"], ["flexDirection", "row"], ["justifyContent", "center"]]))], ["list-item", utsMapOf([[".main ", utsMapOf([["width", "100%"], ["height", 250], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#666666"], ["borderRightColor", "#666666"], ["borderBottomColor", "#666666"], ["borderLeftColor", "#666666"], ["backgroundColor", "#66ccff"], ["alignItems", "center"], ["justifyContent", "center"]])]])], ["tip-text", padStyleMapOf(utsMapOf([["color", "#888888"], ["fontSize", 12]]))], ["refresh-box", padStyleMapOf(utsMapOf([["justifyContent", "center"], ["alignItems", "center"], ["flexDirection", "row"], ["height", 45], ["width", "100%"]]))], ["button", padStyleMapOf(utsMapOf([["marginTop", 15]]))]])]

import _easycom_boolean_data from '@/components/boolean-data/boolean-data.vue'
import _easycom_input_data from '@/components/input-data/input-data.vue'
import _easycom_enum_data from '@/components/enum-data/enum-data.vue'
