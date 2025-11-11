
  type ScrollEventTest = { __$originalPosition?: UTSSourceMapPosition<"ScrollEventTest", "pages/component/waterflow/waterflow.uvue", 2, 8>;
    type : string;
    target : UniElement | null;
    currentTarget : UniElement | null;
    direction ?: string
  }
  type flowItemData = { __$originalPosition?: UTSSourceMapPosition<"flowItemData", "pages/component/waterflow/waterflow.uvue", 8, 8>;
    height : number,
    text : string
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
        upper_threshold_input: 50,
        lower_threshold_input: 50,
        scroll_top_input: 0,
        scroll_left_input: 0,
        refresher_background_input: "#FFF",
        scrollData: [] as Array<flowItemData>,
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
        cross_axis_count: 2,
        main_axis_gap: 2,
        cross_axis_gap: 2,
        waterflowPadding: [10, 5, 10, 5] as Array<number>,
        loadMore: true,
        isLoadMore: true,
        waterflow: null as UniElement | null
      }
    },
    onLoad() {
      //静态瀑布流数据
      this.scrollData = [
        { height: 300, text: "item---0" },
        { height: 150, text: "item---1" },
        { height: 120, text: "item---2" },
        { height: 100, text: "item---3" },
        { height: 100, text: "item---4" },
        { height: 150, text: "item---5" },
        { height: 140, text: "item---6" },
        { height: 190, text: "item---7" },
        { height: 160, text: "item---8" },
        { height: 120, text: "item---9" },
        { height: 109, text: "item---10" },
        { height: 102, text: "item---11" },
        { height: 123, text: "item---12" },
        { height: 156, text: "item---13" },
        { height: 177, text: "item---14" },
        { height: 105, text: "item---15" },
        { height: 110, text: "item---16" },
        { height: 90, text: "item---17" },
        { height: 130, text: "item---18" },
        { height: 140, text: "item---19" },
      ] as Array<flowItemData>
    },
    onReady() {
      // 组件ready时，获取组件实例
      this.waterflow = this.$refs["waterflow"] as UniElement
    },
    methods: {
      waterflow_click() { console.log("组件被点击时触发", " at pages/component/waterflow/waterflow.uvue:79") },
      waterflow_touchstart() { console.log("手指触摸动作开始", " at pages/component/waterflow/waterflow.uvue:80") },
      waterflow_touchmove() { console.log("手指触摸后移动", " at pages/component/waterflow/waterflow.uvue:81") },
      waterflow_touchcancel() { console.log("手指触摸动作被打断，如来电提醒，弹窗", " at pages/component/waterflow/waterflow.uvue:82") },
      waterflow_touchend() { console.log("手指触摸动作结束", " at pages/component/waterflow/waterflow.uvue:83") },
      waterflow_tap() { console.log("手指触摸后马上离开", " at pages/component/waterflow/waterflow.uvue:84") },
      waterflow_longpress() { console.log("如果一个组件被绑定了 longpress 事件，那么当用户长按这个组件时，该事件将会被触发。", " at pages/component/waterflow/waterflow.uvue:85") },
      waterflow_refresherpulling(e : RefresherEvent) {
        console.log("下拉刷新控件被下拉", " at pages/component/waterflow/waterflow.uvue:87")
        if (this.reset) {
          if (e.detail.dy > 45) {
            this.state = 1
          } else {
            this.state = 0
          }
        }
      },
      waterflow_refresherrefresh() {
        console.log("下拉刷新被触发 ", " at pages/component/waterflow/waterflow.uvue:97")
        this.refresherrefresh = true
        this.refresher_triggered_boolean = true
        this.state = 2
        this.reset = false;
        setTimeout(() => {
          this.refresher_triggered_boolean = false
        }, 1500)
      },
      waterflow_refresherrestore() {
        this.refresherrefresh = false
        this.state = 3
        this.reset = true
        console.log("下拉刷新被复位", " at pages/component/waterflow/waterflow.uvue:110")
      },
      waterflow_refresherabort() { console.log("下拉刷新被中止", " at pages/component/waterflow/waterflow.uvue:112") },
      waterflow_scrolltoupper(e : UniScrollToUpperEvent) {
        console.log("滚动到顶部/左边，会触发 scrolltoupper 事件  direction=" + e.detail.direction, " at pages/component/waterflow/waterflow.uvue:114")
        this.checkEventTest({
          type: e.type,
          target: e.target,
          currentTarget: e.currentTarget,
          direction: e.detail.direction,
        } as ScrollEventTest, 'scrolltoupper')
      },
      waterflow_scrolltolower(e : UniScrollToLowerEvent) {
        console.log("滚动到底部/右边，会触发 scrolltolower 事件  direction=" + e.detail.direction, " at pages/component/waterflow/waterflow.uvue:123")
        this.checkEventTest({
          type: e.type,
          target: e.target,
          currentTarget: e.currentTarget,
          direction: e.detail.direction,
        } as ScrollEventTest, 'scrolltolower')
      },
      waterflow_scroll(e : UniScrollEvent) {
        console.log("滚动时触发，event.detail = ", e.detail, " at pages/component/waterflow/waterflow.uvue:132")
        this.scrollDetailTest = e.detail
        this.checkEventTest({
          type: e.type,
          target: e.target,
          currentTarget: e.currentTarget
        } as ScrollEventTest, 'scroll')
      },
      waterflow_scrollend(e : UniScrollEvent) {
        console.log("滚动结束时触发", e.detail, " at pages/component/waterflow/waterflow.uvue:141")
        this.scrollEndDetailTest = e.detail
        this.checkEventTest({
          type: e.type,
          target: e.target,
          currentTarget: e.currentTarget
        } as ScrollEventTest, 'scrollend')
      },
      flow_item_click() { console.log("flow-item组件被点击时触发", " at pages/component/waterflow/waterflow.uvue:149") },
      flow_item_touchstart() { console.log("手指触摸flow-item组件动作开始", " at pages/component/waterflow/waterflow.uvue:150") },
      flow_item_touchmove() { console.log("手指触摸flow-item组件后移动", " at pages/component/waterflow/waterflow.uvue:151") },
      flow_item_touchcancel() { console.log("手指触摸flow-item组件动作被打断，如来电提醒，弹窗", " at pages/component/waterflow/waterflow.uvue:152") },
      flow_item_touchend() { console.log("手指触摸flow-item组件动作结束", " at pages/component/waterflow/waterflow.uvue:153") },
      flow_item_tap() { console.log("手指触摸flow-item组件后马上离开", " at pages/component/waterflow/waterflow.uvue:154") },
      flow_item_longpress() { console.log("flow-item组件被绑定了 longpress 事件，那么当用户长按这个组件时，该事件将会被触发。", " at pages/component/waterflow/waterflow.uvue:155") },
      change_refresher_triggered_boolean(checked : boolean) { this.refresher_triggered_boolean = checked },
      change_refresher_enabled_boolean(checked : boolean) { this.refresher_enabled_boolean = checked },
      change_scroll_with_animation_boolean(checked : boolean) { this.scroll_with_animation_boolean = checked },
      change_show_scrollbar_boolean(checked : boolean) { this.show_scrollbar_boolean = checked },
      change_bounces_boolean(checked : boolean) { this.bounces_boolean = checked },
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
        var listElement = this.$refs["waterflow"] as UniElement
        console.log("check_scroll_height--" + listElement.scrollHeight, " at pages/component/waterflow/waterflow.uvue:190")
        if (listElement.scrollHeight > 1400) {
          return true
        }
        return false
      },
      getScrollTop() : number {
        var ret = this.waterflow?.scrollTop ?? 0
        console.log(ret, " at pages/component/waterflow/waterflow.uvue:198")
        return ret
      },
      change_refresher_style_boolean(checked : boolean) {
        if (checked) {
          this.refresher_default_style_input = "none"
        } else {
          this.refresher_default_style_input = "black"
        }
      },
      change_load_more_boolean(checked : boolean) {
        this.isLoadMore = checked
      },
      handleChangeCrossAxisCount(value : number) {
        if (value < 1) {
          uni.showToast({ title: "cross-axis-count 最小值为 1 请重新设置" })
          return
        }
        this.cross_axis_count = value
      },
      handleChangeCrossAxisGap(e : UniSliderChangeEvent) {
        this.cross_axis_gap = e.detail.value
      },
      handleChangeMainAxisGap(e : UniSliderChangeEvent) {
        this.main_axis_gap = e.detail.value
      },
      //仅自动化测试调用
      testModifyWaterflowProps() {
        this.cross_axis_count = 4
        this.main_axis_gap = 6
        this.cross_axis_gap = 6
        this.waterflowPadding = [5, 10, 5, 10] as Array<number>
      },
      //仅自动化测试调用
      testModifyWaterflowSingleRow() {
        this.cross_axis_count = 1
        this.main_axis_gap = 6
        this.cross_axis_gap = 6
      }

    }
  })

export default __sfc__
function GenPagesComponentWaterflowWaterflowRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_boolean_data = resolveEasyComponent("boolean-data",_easycom_boolean_data)
const _component_input_data = resolveEasyComponent("input-data",_easycom_input_data)
const _component_slider = resolveComponent("slider")
const _component_enum_data = resolveEasyComponent("enum-data",_easycom_enum_data)
const _component_navigator = resolveComponent("navigator")

  return createElementVNode(Fragment, null, [
    createElementVNode("view", utsMapOf({ class: "main" }), [
      createElementVNode("waterflow", utsMapOf({
        "cross-axis-count": _ctx.cross_axis_count,
        "main-axis-gap": _ctx.main_axis_gap,
        "cross-axis-gap": _ctx.cross_axis_gap,
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
        onClick: [_ctx.waterflow_click, _ctx.waterflow_tap],
        onTouchstart: _ctx.waterflow_touchstart,
        onTouchmove: _ctx.waterflow_touchmove,
        onTouchcancel: _ctx.waterflow_touchcancel,
        onTouchend: _ctx.waterflow_touchend,
        onLongpress: _ctx.waterflow_longpress,
        onRefresherpulling: _ctx.waterflow_refresherpulling,
        onRefresherrefresh: _ctx.waterflow_refresherrefresh,
        onRefresherrestore: _ctx.waterflow_refresherrestore,
        onRefresherabort: _ctx.waterflow_refresherabort,
        onScrolltoupper: _ctx.waterflow_scrolltoupper,
        ref: "waterflow",
        onScrolltolower: _ctx.waterflow_scrolltolower,
        onScroll: _ctx.waterflow_scroll,
        onScrollend: _ctx.waterflow_scrollend,
        style: normalizeStyle(utsMapOf({"width":"100%"})),
        id: "waterflow",
        padding: _ctx.waterflowPadding
      }), [
        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.scrollData, (item, index, __index, _cached): any => {
          return createElementVNode("flow-item", utsMapOf({
            key: index,
            id: item.text,
            onClick: [_ctx.flow_item_click, _ctx.flow_item_tap],
            style: normalizeStyle(utsMapOf({'height' : item.height})),
            onTouchstart: _ctx.flow_item_touchstart,
            onTouchmove: _ctx.flow_item_touchmove,
            onTouchcancel: _ctx.flow_item_touchcancel,
            onTouchend: _ctx.flow_item_touchend,
            onLongpress: _ctx.flow_item_longpress,
            class: "flow-item",
            type: "1"
          }), [
            createElementVNode("text", null, toDisplayString(item.text), 1 /* TEXT */)
          ], 44 /* STYLE, PROPS, NEED_HYDRATION */, ["id", "onClick", "onTouchstart", "onTouchmove", "onTouchcancel", "onTouchend", "onLongpress"])
        }), 128 /* KEYED_FRAGMENT */),
        createElementVNode("flow-item", utsMapOf({
          slot: "refresher",
          id: "refresher",
          type: "2",
          class: "refresh-box"
        }), [
          createElementVNode("text", utsMapOf({ class: "tip-text" }), toDisplayString(_ctx.text[_ctx.state]), 1 /* TEXT */)
        ]),
        withDirectives(createElementVNode("flow-item", utsMapOf({
          slot: "load-more",
          id: "loadmore",
          type: "6",
          class: "load-more-box"
        }), [
          createElementVNode("text", null, "加载更多")
        ], 512 /* NEED_PATCH */), [
          [vShow, _ctx.isLoadMore]
        ])
      ], 44 /* STYLE, PROPS, NEED_HYDRATION */, ["cross-axis-count", "main-axis-gap", "cross-axis-gap", "bounces", "upper-threshold", "lower-threshold", "scroll-top", "scroll-left", "show-scrollbar", "scroll-into-view", "scroll-with-animation", "refresher-enabled", "refresher-background", "refresher-triggered", "refresher-default-style", "onClick", "onTouchstart", "onTouchmove", "onTouchcancel", "onTouchend", "onLongpress", "onRefresherpulling", "onRefresherrefresh", "onRefresherrestore", "onRefresherabort", "onScrolltoupper", "onScrolltolower", "onScroll", "onScrollend", "padding"])
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
          title: "是否显示加载更多",
          onChange: _ctx.change_load_more_boolean
        }), null, 8 /* PROPS */, ["onChange"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "2",
          title: "设置cross-axis-count，触发 scrolltoupper 事件",
          type: "number",
          onConfirm: _ctx.handleChangeCrossAxisCount
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createElementVNode("view", utsMapOf({ class: "uni-slider uni-list-cell-padding" }), [
          createElementVNode("text", null, "拖动设置cross-axis-gap")
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-slider uni-list-cell-padding" }), [
          createVNode(_component_slider, utsMapOf({
            max: 20,
            min: 0,
            step: 1,
            value: _ctx.cross_axis_gap,
            "show-value": true,
            onChange: _ctx.handleChangeCrossAxisGap
          }), null, 8 /* PROPS */, ["value", "onChange"])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-slider uni-list-cell-padding" }), [
          createElementVNode("text", null, "拖动设置main-axis-gap")
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-slider uni-list-cell-padding" }), [
          createVNode(_component_slider, utsMapOf({
            max: 20,
            min: 0,
            step: 1,
            value: _ctx.main_axis_gap,
            "show-value": true,
            onChange: _ctx.handleChangeMainAxisGap
          }), null, 8 /* PROPS */, ["value", "onChange"])
        ]),
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
        createVNode(_component_navigator, utsMapOf({ url: "/pages/component/waterflow/waterflow-fit-height" }), utsMapOf({
          default: withSlotCtx((): any[] => [
            createElementVNode("button", utsMapOf({
              type: "primary",
              class: "button"
            }), " item高度自适应 ")
          ]),
          _: 1 /* STABLE */
        }))
      ])
    ], 4 /* STYLE */)
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesComponentWaterflowWaterflowStyles = [utsMapOf([["main", padStyleMapOf(utsMapOf([["maxHeight", 250], ["paddingTop", 5], ["paddingRight", 0], ["paddingBottom", 5], ["paddingLeft", 0], ["borderBottomWidth", 1], ["borderBottomStyle", "solid"], ["borderBottomColor", "rgba(0,0,0,0.06)"], ["flexDirection", "row"], ["justifyContent", "center"]]))], ["flow-item", padStyleMapOf(utsMapOf([["width", "100%"], ["height", 200], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#666666"], ["borderRightColor", "#666666"], ["borderBottomColor", "#666666"], ["borderLeftColor", "#666666"], ["backgroundColor", "#66ccff"], ["alignItems", "center"], ["justifyContent", "center"]]))], ["tip-text", padStyleMapOf(utsMapOf([["color", "#888888"], ["fontSize", 12]]))], ["refresh-box", padStyleMapOf(utsMapOf([["justifyContent", "center"], ["alignItems", "center"], ["flexDirection", "row"], ["height", 45], ["width", "100%"]]))], ["load-more-box", padStyleMapOf(utsMapOf([["justifyContent", "center"], ["alignItems", "center"], ["flexDirection", "row"], ["height", 45], ["width", "100%"], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"]]))], ["button", padStyleMapOf(utsMapOf([["marginTop", 15]]))]])]

import _easycom_boolean_data from '@/components/boolean-data/boolean-data.vue'
import _easycom_input_data from '@/components/input-data/input-data.vue'
import _easycom_enum_data from '@/components/enum-data/enum-data.vue'
