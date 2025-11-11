
  type ItemType = { __$originalPosition?: UTSSourceMapPosition<"ItemType", "pages/component/checkbox/checkbox.uvue", 2, 8>;
    value : string
    name : string
    checked : boolean
  }
  const __sfc__ = defineComponent({
    data() {
      return {
        items: [
          {
            value: 'CHN',
            name: '中国',
            checked: true,
          },
          {
            value: 'USA',
            name: '美国',
            checked: false,
          },
          {
            value: 'BRA',
            name: '巴西',
            checked: false,
          },
          {
            value: 'JPN',
            name: '日本',
            checked: false,
          },
          {
            value: 'ENG',
            name: '英国',
            checked: false,
          },
          {
            value: 'FRA',
            name: '法国',
            checked: false,
          },
        ] as ItemType[],
        testEvent: false,
        text: '未选中',
        wrapText: 'uni-app x，终极跨平台方案\nuts，大一统语言',
        value: [] as string[],
        disabled: true,
        checked: true,
        color: '#007aff',
        iconColor: '#211cfe',
        foreColor: '#ff0000',
        // 组件属性 autotest
        checked_boolean: false,
        disabled_boolean: false,
        color_input: "#007aff",
        backgroundColor_input: "#ffffff",
        borderColor_input: "#d1d1d1",
        activeBackgroundColor_input: "#ffffff",
        activeBorderColor_input: "#d1d1d1",
        iconColor_input: "#007aff",
        foreColor_input: '#ff0000'
      }
    },
    methods: {

      checkboxChange: function (e : UniCheckboxGroupChangeEvent) {
        // 自动化测试
        if ((e.target?.tagName ?? '') == 'CHECKBOX-GROUP' && e.type === 'change') {
          this.testEvent = true
        }

        const selectedNames : string[] = []
        this.items.forEach((item) => {
          if (e.detail.value.includes(item.value)) {
            selectedNames.push(item.name)
          }
        })
        uni.showToast({
          icon: 'none',
          title: '当前选中:' + selectedNames.join(','),
        })
      },
      testChange: function (e : UniCheckboxGroupChangeEvent) {
        this.value = e.detail.value
      },
      checkbox_click() { console.log("组件被点击时触发", " at pages/component/checkbox/checkbox.uvue:85") },
      checkbox_touchstart() { console.log("手指触摸动作开始", " at pages/component/checkbox/checkbox.uvue:86") },
      checkbox_touchmove() { console.log("手指触摸后移动", " at pages/component/checkbox/checkbox.uvue:87") },
      checkbox_touchcancel() { console.log("手指触摸动作被打断，如来电提醒，弹窗", " at pages/component/checkbox/checkbox.uvue:88") },
      checkbox_touchend() { console.log("手指触摸动作结束", " at pages/component/checkbox/checkbox.uvue:89") },
      checkbox_tap() { console.log("手指触摸后马上离开", " at pages/component/checkbox/checkbox.uvue:90") },
      checkbox_longpress() { console.log("如果一个组件被绑定了 longpress 事件，那么当用户长按这个组件时，该事件将会被触发。", " at pages/component/checkbox/checkbox.uvue:91") },
      change_checked_boolean(checked : boolean) { this.checked_boolean = checked },
      change_disabled_boolean(checked : boolean) { this.disabled_boolean = checked },
      confirm_color_input(value : string) { this.color_input = value },
      confirm_backgroundColor_input(value : string) { this.backgroundColor_input = value },
      confirm_borderColor_input(value : string) { this.borderColor_input = value },
      confirm_activeBackgroundColor_input(value : string) { this.activeBackgroundColor_input = value },
      confirm_activeBorderColor_input(value : string) { this.activeBorderColor_input = value },
      confirm_iconColor_input(value : string) { this.iconColor_input = value },
      confirm_foreColor_input(value : string) { this.foreColor_input = value }
    }
  })

export default __sfc__
function GenPagesComponentCheckboxCheckboxRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_checkbox = resolveComponent("checkbox", true)
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)
const _component_boolean_data = resolveEasyComponent("boolean-data",_easycom_boolean_data)
const _component_input_data = resolveEasyComponent("input-data",_easycom_input_data)
const _component_checkbox_group = resolveComponent("checkbox-group")

  return createElementVNode(Fragment, null, [
    createElementVNode("view", utsMapOf({ class: "main" }), [
      createVNode(_component_checkbox, utsMapOf({
        disabled: _ctx.disabled_boolean,
        checked: _ctx.checked_boolean,
        color: _ctx.color_input,
        iconColor: _ctx.iconColor_input,
        foreColor: _ctx.foreColor_input,
        backgroundColor: _ctx.backgroundColor_input,
        borderColor: _ctx.borderColor_input,
        activeBackgroundColor: _ctx.activeBackgroundColor_input,
        activeBorderColor: _ctx.activeBorderColor_input,
        onClick: [_ctx.checkbox_click, _ctx.checkbox_tap],
        onTouchstart: _ctx.checkbox_touchstart,
        onTouchmove: _ctx.checkbox_touchmove,
        onTouchcancel: _ctx.checkbox_touchcancel,
        onTouchend: _ctx.checkbox_touchend,
        onLongpress: _ctx.checkbox_longpress
      }), utsMapOf({
        default: withSlotCtx((): any[] => [
          createElementVNode("text", null, "uni-app-x")
        ]),
        _: 1 /* STABLE */
      }), 8 /* PROPS */, ["disabled", "checked", "color", "iconColor", "foreColor", "backgroundColor", "borderColor", "activeBackgroundColor", "activeBorderColor", "onClick", "onTouchstart", "onTouchmove", "onTouchcancel", "onTouchend", "onLongpress"])
    ]),
    createElementVNode("scroll-view", utsMapOf({
      style: normalizeStyle(utsMapOf({"flex":"1"}))
    }), [
      createElementVNode("view", utsMapOf({ class: "content" }), [
        createVNode(_component_page_head, utsMapOf({ title: "组件属性" })),
        createVNode(_component_boolean_data, utsMapOf({
          defaultValue: false,
          title: "当前是否选中，可用来设置默认选中",
          onChange: _ctx.change_checked_boolean
        }), null, 8 /* PROPS */, ["onChange"]),
        createVNode(_component_boolean_data, utsMapOf({
          defaultValue: false,
          title: "是否禁用",
          onChange: _ctx.change_disabled_boolean
        }), null, 8 /* PROPS */, ["onChange"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "#007aff",
          title: "checkbox的颜色",
          type: "text",
          onConfirm: _ctx.confirm_color_input
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "#ffffff",
          title: "checkbox默认的背景颜色",
          type: "text",
          onConfirm: _ctx.confirm_backgroundColor_input
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "#d1d1d1",
          title: "checkbox默认的边框颜色",
          type: "text",
          onConfirm: _ctx.confirm_borderColor_input
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "#ffffff",
          title: "checkbox选中时的背景颜色",
          type: "text",
          onConfirm: _ctx.confirm_activeBackgroundColor_input
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "#d1d1d1",
          title: "checkbox选中时的边框颜色",
          type: "text",
          onConfirm: _ctx.confirm_activeBorderColor_input
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "#007aff",
          title: "iconColor: checkbox的图标颜色，优先级大于color属性",
          type: "text",
          onConfirm: _ctx.confirm_iconColor_input
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "#ff0000",
          title: "foreColor: checkbox的图标颜色，优先级大于color属性",
          type: "text",
          onConfirm: _ctx.confirm_foreColor_input
        }), null, 8 /* PROPS */, ["onConfirm"])
      ]),
      createElementVNode("view", null, [
        createVNode(_component_page_head, utsMapOf({ title: "默认及使用" })),
        createElementVNode("view", utsMapOf({ class: "uni-padding-wrap uni-common-mt" }), [
          createElementVNode("view", utsMapOf({ class: "uni-title uni-common-mt" }), [
            createElementVNode("text", utsMapOf({ class: "uni-title-text" }), " 默认样式 ")
          ]),
          createElementVNode("view", null, [
            createVNode(_component_checkbox_group, utsMapOf({
              class: "uni-flex uni-row checkbox-group",
              onChange: _ctx.testChange,
              style: normalizeStyle(utsMapOf({"flex-wrap":"wrap"}))
            }), utsMapOf({
              default: withSlotCtx((): any[] => [
                createVNode(_component_checkbox, utsMapOf({
                  value: "cb",
                  checked: _ctx.checked,
                  color: _ctx.color,
                  iconColor: _ctx.iconColor,
                  foreColor: _ctx.foreColor,
                  style: normalizeStyle(utsMapOf({"margin-right":"15px"})),
                  class: "checkbox cb"
                }), utsMapOf({
                  default: withSlotCtx((): any[] => ["选中 "]),
                  _: 1 /* STABLE */
                }), 8 /* PROPS */, ["checked", "color", "iconColor", "foreColor", "style"]),
                createVNode(_component_checkbox, utsMapOf({
                  value: "cb1",
                  style: normalizeStyle(utsMapOf({"margin-right":"15px"})),
                  class: "checkbox cb1"
                }), utsMapOf({
                  default: withSlotCtx((): any[] => [toDisplayString(_ctx.text)]),
                  _: 1 /* STABLE */
                }), 8 /* PROPS */, ["style"]),
                createVNode(_component_checkbox, utsMapOf({
                  value: "cb2",
                  disabled: _ctx.disabled,
                  class: "checkbox cb2"
                }), utsMapOf({
                  default: withSlotCtx((): any[] => ["禁用"]),
                  _: 1 /* STABLE */
                }), 8 /* PROPS */, ["disabled"]),
                createVNode(_component_checkbox, utsMapOf({
                  value: "cb3",
                  style: normalizeStyle(utsMapOf({"margin-top":"10px"})),
                  class: "checkbox cb3"
                }), utsMapOf({
                  default: withSlotCtx((): any[] => [toDisplayString(_ctx.wrapText)]),
                  _: 1 /* STABLE */
                }), 8 /* PROPS */, ["style"])
              ]),
              _: 1 /* STABLE */
            }), 8 /* PROPS */, ["onChange", "style"])
          ]),
          createElementVNode("view", utsMapOf({ class: "uni-title uni-common-mt" }), [
            createElementVNode("text", utsMapOf({ class: "uni-title-text" }), " 不同颜色和尺寸的checkbox ")
          ]),
          createElementVNode("view", null, [
            createVNode(_component_checkbox_group, utsMapOf({ class: "uni-flex uni-row checkbox-group" }), utsMapOf({
              default: withSlotCtx((): any[] => [
                createVNode(_component_checkbox, utsMapOf({
                  value: "cb1",
                  checked: true,
                  color: "#FFCC33",
                  style: normalizeStyle(utsMapOf({"transform":"scale(0.7)","margin-right":"15px"})),
                  class: "checkbox"
                }), utsMapOf({
                  default: withSlotCtx((): any[] => ["选中 "]),
                  _: 1 /* STABLE */
                }), 8 /* PROPS */, ["style"]),
                createVNode(_component_checkbox, utsMapOf({
                  value: "cb",
                  color: "#FFCC33",
                  style: normalizeStyle(utsMapOf({"transform":"scale(0.7)"})),
                  class: "checkbox"
                }), utsMapOf({
                  default: withSlotCtx((): any[] => ["未选中"]),
                  _: 1 /* STABLE */
                }), 8 /* PROPS */, ["style"])
              ]),
              _: 1 /* STABLE */
            }))
          ]),
          createElementVNode("view", utsMapOf({ class: "uni-title uni-common-mt" }), [
            createElementVNode("text", utsMapOf({ class: "uni-title-text" }), " 两端对齐样式测试 ")
          ]),
          createElementVNode("view", null, [
            createVNode(_component_checkbox_group, utsMapOf({ class: "uni-flex uni-row checkbox-group" }), utsMapOf({
              default: withSlotCtx((): any[] => [
                createVNode(_component_checkbox, utsMapOf({ class: "justify-test" }), utsMapOf({
                  default: withSlotCtx((): any[] => ["justify-content样式测试"]),
                  _: 1 /* STABLE */
                }))
              ]),
              _: 1 /* STABLE */
            }))
          ])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
          createElementVNode("view", utsMapOf({ class: "uni-title uni-common-mt" }), [
            createElementVNode("text", utsMapOf({ class: "uni-title-text" }), " 推荐展示样式 ")
          ])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-list uni-common-pl" }), [
          createVNode(_component_checkbox_group, utsMapOf({
            onChange: _ctx.checkboxChange,
            class: "checkbox-group",
            id: "trigger-change"
          }), utsMapOf({
            default: withSlotCtx((): any[] => [
              createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.items, (item, index, __index, _cached): any => {
                return createVNode(_component_checkbox, utsMapOf({
                  class: normalizeClass(["uni-list-cell uni-list-cell-pd checkbox", [
              index < _ctx.items.length - 1 ? 'uni-list-cell-line' : '',
              'checkbox-item-' + index,
            ]]),
                  key: item.value,
                  value: item.value,
                  checked: item.checked
                }), utsMapOf({
                  default: withSlotCtx((): any[] => [toDisplayString(item.name)]),
                  _: 2 /* DYNAMIC */
                }), 1032 /* PROPS, DYNAMIC_SLOTS */, ["value", "checked", "class"])
              }), 128 /* KEYED_FRAGMENT */)
            ]),
            _: 1 /* STABLE */
          }), 8 /* PROPS */, ["onChange"])
        ])
      ])
    ], 4 /* STYLE */)
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesComponentCheckboxCheckboxStyles = [utsMapOf([["main", padStyleMapOf(utsMapOf([["maxHeight", 250], ["paddingTop", 5], ["paddingRight", 0], ["paddingBottom", 5], ["paddingLeft", 0], ["borderBottomWidth", 1], ["borderBottomStyle", "solid"], ["borderBottomColor", "rgba(0,0,0,0.06)"], ["flexDirection", "row"], ["justifyContent", "center"]]))], ["list-item", utsMapOf([[".main ", utsMapOf([["width", "100%"], ["height", 100], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#666666"], ["borderRightColor", "#666666"], ["borderBottomColor", "#666666"], ["borderLeftColor", "#666666"]])]])], ["uni-list-cell", padStyleMapOf(utsMapOf([["justifyContent", "flex-start"]]))], ["justify-test", padStyleMapOf(utsMapOf([["width", "100%"], ["justifyContent", "space-between"]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
import _easycom_boolean_data from '@/components/boolean-data/boolean-data.vue'
import _easycom_input_data from '@/components/input-data/input-data.vue'
