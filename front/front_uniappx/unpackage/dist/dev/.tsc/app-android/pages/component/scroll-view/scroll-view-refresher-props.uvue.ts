
  type Item = { __$originalPosition?: UTSSourceMapPosition<"Item", "pages/component/scroll-view/scroll-view-refresher-props.uvue", 62, 8>;
    id : string,
    label : string,
  }

  const __sfc__ = defineComponent({
    data() {
      return {
        items: [] as Item[],
        refresherEnabled: true,
        refresherTriggered: false,
        refresherThreshold: 45,
        refresherDefaultStyle: "black",
        refresherBackground: "transparent",
      }
    },
    onLoad() {
      for (let i = 0; i < 10; i++) {
        const item = {
          id: "item" + i,
          label: "item" + i,
        } as Item;
        this.items.push(item);
      }
    },
    methods: {
      handleTrunOnRefresher() {
        this.refresherTriggered = false;
        //不能同时关闭下拉状态和关闭下拉刷新。
        setTimeout(() => {
          this.refresherEnabled = !this.refresherEnabled;
        }, 0)
      },
      handleRefresherThresholdInput(e : InputEvent) {
        const value = e.detail.value;
        if (value == "") {
          this.refresherThreshold = 45;
        } else {
          this.refresherThreshold = parseInt(e.detail.value);
        }
      },
      handleRefresherBackground(e : InputEvent) {
        const value = e.detail.value;
        this.refresherBackground = value;
      },
      //响应事件
      refresherpulling() {
        console.log("下拉刷新控件被下拉", " at pages/component/scroll-view/scroll-view-refresher-props.uvue:109");
      },
      refresherrefresh() {
        console.log("下拉刷新被触发", " at pages/component/scroll-view/scroll-view-refresher-props.uvue:112");
        this.refresherTriggered = true;
        //不能同时关闭下拉状态和关闭下拉刷新。
        setTimeout(() => {
          this.refresherTriggered = false;
        }, 1500)
      },
      refresherrestore() {
        console.log("下拉刷新被复位", " at pages/component/scroll-view/scroll-view-refresher-props.uvue:120");
      },
      refresherabort() {
        console.log("下拉刷新被中止", " at pages/component/scroll-view/scroll-view-refresher-props.uvue:123");
      }
    }
  })

export default __sfc__
function GenPagesComponentScrollViewScrollViewRefresherPropsRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)
const _component_switch = resolveComponent("switch")

  return createElementVNode("view", utsMapOf({ class: "page-scroll-view" }), [
    createVNode(_component_page_head, utsMapOf({ title: "下拉刷新的scroll-view属性示例" })),
    createElementVNode("view", utsMapOf({ class: "uni-margin-wrap" }), [
      createElementVNode("scroll-view", utsMapOf({
        direction: "vertical",
        "refresher-enabled": _ctx.refresherEnabled,
        "refresher-threshold": _ctx.refresherThreshold,
        "refresher-default-style": _ctx.refresherDefaultStyle,
        "refresher-background": _ctx.refresherBackground,
        "refresher-triggered": _ctx.refresherTriggered,
        onRefresherpulling: _ctx.refresherpulling,
        onRefresherrefresh: _ctx.refresherrefresh,
        onRefresherrestore: _ctx.refresherrestore,
        onRefresherabort: _ctx.refresherabort,
        style: normalizeStyle(utsMapOf({"width":"100%","height":"100%"}))
      }), [
        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.items, (item, _, __index, _cached): any => {
          return createElementVNode("view", utsMapOf({
            class: "item",
            id: item.id
          }), [
            createElementVNode("text", utsMapOf({ class: "uni-text" }), toDisplayString(item.label), 1 /* TEXT */)
          ], 8 /* PROPS */, ["id"])
        }), 256 /* UNKEYED_FRAGMENT */)
      ], 44 /* STYLE, PROPS, NEED_HYDRATION */, ["refresher-enabled", "refresher-threshold", "refresher-default-style", "refresher-background", "refresher-triggered", "onRefresherpulling", "onRefresherrefresh", "onRefresherrestore", "onRefresherabort"])
    ]),
    createElementVNode("scroll-view", utsMapOf({
      class: "uni-list",
      style: normalizeStyle(utsMapOf({"padding-top":"16px"})),
      showScrollbar: true
    }), [
      createElementVNode("view", utsMapOf({ class: "uni-list-cell-padding" }), [
        createElementVNode("text", utsMapOf({ class: "refresher-tips" }), "**下拉刷新的属性设置需要先打开下拉刷新开关**")
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-list-cell uni-list-cell-padding" }), [
        createElementVNode("text", null, "是否开启下拉刷新"),
        createVNode(_component_switch, utsMapOf({
          checked: _ctx.refresherEnabled,
          onChange: _ctx.handleTrunOnRefresher
        }), null, 8 /* PROPS */, ["checked", "onChange"])
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-list-cell uni-list-cell-padding" }), [
        createElementVNode("text", null, "设置下拉刷新状态"),
        createVNode(_component_switch, utsMapOf({
          disabled: !_ctx.refresherEnabled,
          checked: _ctx.refresherTriggered,
          onChange: () => {_ctx.refresherTriggered=!_ctx.refresherTriggered}
        }), null, 8 /* PROPS */, ["disabled", "checked", "onChange"])
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-list-cell uni-list-cell-padding" }), [
        createElementVNode("text", null, "设置下拉刷新阈值"),
        createElementVNode("input", utsMapOf({
          class: "uni-list-cell-input",
          disabled: !_ctx.refresherEnabled,
          value: _ctx.refresherThreshold,
          type: "number",
          onInput: _ctx.handleRefresherThresholdInput
        }), null, 40 /* PROPS, NEED_HYDRATION */, ["disabled", "value", "onInput"])
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-list-cell uni-list-cell-padding" }), [
        createElementVNode("text", null, "设置下拉刷新区域背景颜色"),
        createElementVNode("input", utsMapOf({
          class: "uni-list-cell-input",
          disabled: !_ctx.refresherEnabled,
          value: _ctx.refresherBackground,
          onInput: _ctx.handleRefresherBackground
        }), null, 40 /* PROPS, NEED_HYDRATION */, ["disabled", "value", "onInput"])
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-list-cell-padding" }), [
        createElementVNode("text", null, "设置下拉刷新默认样式"),
        createElementVNode("view", utsMapOf({ class: "switch-refresher-group" }), [
          createElementVNode("button", utsMapOf({
            class: "switch-refresher-style",
            type: "primary",
            size: "mini",
            onClick: () => {_ctx.refresherDefaultStyle = `none`}
          }), "none", 8 /* PROPS */, ["onClick"]),
          createElementVNode("button", utsMapOf({
            class: "switch-refresher-style",
            type: "primary",
            size: "mini",
            onClick: () => {_ctx.refresherDefaultStyle = `black`}
          }), "black", 8 /* PROPS */, ["onClick"]),
          createElementVNode("button", utsMapOf({
            class: "switch-refresher-style",
            type: "primary",
            size: "mini",
            onClick: () => {_ctx.refresherDefaultStyle = `white`}
          }), "white", 8 /* PROPS */, ["onClick"])
        ])
      ])
    ], 4 /* STYLE */)
  ])
}
const GenPagesComponentScrollViewScrollViewRefresherPropsStyles = [utsMapOf([["uni-margin-wrap", padStyleMapOf(utsMapOf([["height", 200], ["marginTop", 0], ["marginRight", 25], ["marginBottom", 25], ["marginLeft", 25]]))], ["item", padStyleMapOf(utsMapOf([["justifyContent", "center"], ["alignItems", "center"], ["height", 200], ["width", "100%"], ["backgroundColor", "#F0FFFF"], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#D2691E"], ["borderRightColor", "#D2691E"], ["borderBottomColor", "#D2691E"], ["borderLeftColor", "#D2691E"]]))], ["refresher-tips", padStyleMapOf(utsMapOf([["fontSize", 12], ["textAlign", "center"], ["color", "#FF0000"]]))], ["uni-text", padStyleMapOf(utsMapOf([["color", "#000000"], ["fontSize", 50]]))], ["uni-list", padStyleMapOf(utsMapOf([["flex", 1]]))], ["uni-list-cell-input", padStyleMapOf(utsMapOf([["maxWidth", 100], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#cccccc"], ["borderRightColor", "#cccccc"], ["borderBottomColor", "#cccccc"], ["borderLeftColor", "#cccccc"], ["textAlign", "center"]]))], ["switch-refresher-group", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["marginTop", 5]]))], ["switch-refresher-style", padStyleMapOf(utsMapOf([["paddingTop", 2], ["paddingRight", 10], ["paddingBottom", 2], ["paddingLeft", 10], ["marginRight", 5]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
