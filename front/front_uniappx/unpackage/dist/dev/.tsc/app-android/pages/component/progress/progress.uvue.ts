
  import { state, setEventCallbackNum } from '@/store/index.uts'
  import { ItemType } from '@/components/enum-data/enum-data-types'
  const __sfc__ = defineComponent({
    data() {
      return {
        title: 'progress',
        pgList: [0, 0, 0, 0] as number[],
        curPercent: 0,
        showInfo: true,
        borderRadius: 0,
        fontSize: 16,
        strokeWidth: 3,
        backgroundColor: '#EBEBEB',

        // 组件属性 autotest
        active_boolean: false,
        show_info_boolean: false,
        duration_input: 30,
        percent_input: 0,
        stroke_width_input: 6,
        activeColor_input: "#09BB07",
        backgroundColor_input: "#EBEBEB",
        active_mode_enum: [{ "value": 0, "name": "backwards" }, { "value": 1, "name": "forwards" }] as ItemType[],
        active_mode_enum_current: 0
      }
    },
    methods: {
      // 自动化测试
      getEventCallbackNum() : number {
        return state.eventCallbackNum
      },
      // 自动化测试
      setEventCallbackNum(num : number) {
        setEventCallbackNum(num)
      },

      setProgress() {
        this.pgList = [20, 40, 60, 80] as number[]
      },
      clearProgress() {
        this.pgList = [0, 0, 0, 0] as number[]
      },
      activeend(e : UniProgressActiveendEvent) {
        // 自动化测试
        if ((e.target?.tagName ?? '').includes('PROGRESS')) {
          this.setEventCallbackNum(state.eventCallbackNum + 1)
        }
        if (e.type === 'activeend') {
          this.setEventCallbackNum(state.eventCallbackNum + 2)
        }
        this.curPercent = e.detail.curPercent
      },
      progress_touchstart() { console.log("手指触摸动作开始", " at pages/component/progress/progress.uvue:54") },
      progress_touchmove() { console.log("手指触摸后移动", " at pages/component/progress/progress.uvue:55") },
      progress_touchcancel() { console.log("手指触摸动作被打断，如来电提醒，弹窗", " at pages/component/progress/progress.uvue:56") },
      progress_touchend() { console.log("手指触摸动作结束", " at pages/component/progress/progress.uvue:57") },
      progress_tap() { console.log("手指触摸后马上离开", " at pages/component/progress/progress.uvue:58") },
      change_active_boolean(checked : boolean) { this.active_boolean = checked },
      change_show_info_boolean(checked : boolean) { this.show_info_boolean = checked },
      confirm_duration_input(value : number) { this.duration_input = value },
      confirm_percent_input(value : number) { this.percent_input = value },
      confirm_stroke_width_input(value : number) { this.stroke_width_input = value },
      confirm_activeColor_input(value : string) { this.activeColor_input = value },
      confirm_backgroundColor_input(value : string) { this.backgroundColor_input = value },
      radio_change_active_mode_enum(checked : number) { this.active_mode_enum_current = checked }
    }
  })

export default __sfc__
function GenPagesComponentProgressProgressRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_progress = resolveComponent("progress", true)
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)
const _component_boolean_data = resolveEasyComponent("boolean-data",_easycom_boolean_data)
const _component_input_data = resolveEasyComponent("input-data",_easycom_input_data)
const _component_enum_data = resolveEasyComponent("enum-data",_easycom_enum_data)

  return createElementVNode(Fragment, null, [
    createElementVNode("view", utsMapOf({ class: "main" }), [
      createVNode(_component_progress, utsMapOf({
        duration: _ctx.duration_input,
        percent: _ctx.percent_input,
        "show-info": _ctx.show_info_boolean,
        "stroke-width": _ctx.stroke_width_input,
        activeColor: _ctx.activeColor_input,
        backgroundColor: _ctx.backgroundColor_input,
        active: _ctx.active_boolean,
        "active-mode": _ctx.active_mode_enum[_ctx.active_mode_enum_current].name,
        onTouchstart: _ctx.progress_touchstart,
        onTouchmove: _ctx.progress_touchmove,
        onTouchcancel: _ctx.progress_touchcancel,
        onTouchend: _ctx.progress_touchend,
        onClick: _ctx.progress_tap,
        style: normalizeStyle(utsMapOf({"width":"80%"}))
      }), null, 8 /* PROPS */, ["duration", "percent", "show-info", "stroke-width", "activeColor", "backgroundColor", "active", "active-mode", "onTouchstart", "onTouchmove", "onTouchcancel", "onTouchend", "onClick", "style"])
    ]),
    createElementVNode("scroll-view", utsMapOf({
      style: normalizeStyle(utsMapOf({"flex":"1"}))
    }), [
      createElementVNode("view", utsMapOf({ class: "content" }), [
        createVNode(_component_page_head, utsMapOf({ title: "组件属性" })),
        createVNode(_component_boolean_data, utsMapOf({
          defaultValue: false,
          title: "进度条从左往右的动画",
          onChange: _ctx.change_active_boolean
        }), null, 8 /* PROPS */, ["onChange"]),
        createVNode(_component_boolean_data, utsMapOf({
          defaultValue: false,
          title: "在进度条右侧显示百分比",
          onChange: _ctx.change_show_info_boolean
        }), null, 8 /* PROPS */, ["onChange"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "30",
          title: "进度增加1%所需毫秒数",
          type: "number",
          onConfirm: _ctx.confirm_duration_input
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "0",
          title: "百分比0~100",
          type: "number",
          onConfirm: _ctx.confirm_percent_input
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "6",
          title: "进度条线的宽度，单位px",
          type: "number",
          onConfirm: _ctx.confirm_stroke_width_input
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "#09BB07",
          title: "已选择的进度条的颜色",
          type: "text",
          onConfirm: _ctx.confirm_activeColor_input
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: "#EBEBEB",
          title: "未选择的进度条的颜色",
          type: "text",
          onConfirm: _ctx.confirm_backgroundColor_input
        }), null, 8 /* PROPS */, ["onConfirm"]),
        createVNode(_component_enum_data, utsMapOf({
          items: _ctx.active_mode_enum,
          title: "backwards: 动画从头播；forwards：动画从上次结束点接着播",
          onChange: _ctx.radio_change_active_mode_enum
        }), null, 8 /* PROPS */, ["items", "onChange"])
      ]),
      createElementVNode("view", null, [
        createVNode(_component_page_head, utsMapOf({ title: "默认及使用" })),
        createElementVNode("view", utsMapOf({ class: "uni-padding-wrap uni-common-mt" }), [
          createElementVNode("view", utsMapOf({ class: "progress-box" }), [
            createVNode(_component_progress, utsMapOf({
              percent: _ctx.pgList[0],
              active: true,
              "border-radius": _ctx.borderRadius,
              "show-info": _ctx.showInfo,
              "font-size": _ctx.fontSize,
              "stroke-width": _ctx.strokeWidth,
              "background-color": _ctx.backgroundColor,
              class: "progress p",
              onActiveend: _ctx.activeend
            }), null, 8 /* PROPS */, ["percent", "border-radius", "show-info", "font-size", "stroke-width", "background-color", "onActiveend"])
          ]),
          createElementVNode("view", utsMapOf({ class: "progress-box" }), [
            createVNode(_component_progress, utsMapOf({
              percent: _ctx.pgList[1],
              "stroke-width": 3,
              class: "progress p1"
            }), null, 8 /* PROPS */, ["percent"])
          ]),
          createElementVNode("view", utsMapOf({ class: "progress-box" }), [
            createVNode(_component_progress, utsMapOf({
              percent: _ctx.pgList[2],
              "stroke-width": 3,
              class: "progress p2"
            }), null, 8 /* PROPS */, ["percent"])
          ]),
          createElementVNode("view", utsMapOf({ class: "progress-box" }), [
            createVNode(_component_progress, utsMapOf({
              percent: _ctx.pgList[3],
              activeColor: "#10AEFF",
              "stroke-width": 3,
              class: "progress p3"
            }), null, 8 /* PROPS */, ["percent"])
          ]),
          createElementVNode("view", utsMapOf({ class: "progress-control" }), [
            createElementVNode("button", utsMapOf({
              type: "primary",
              onClick: _ctx.setProgress,
              class: "button"
            }), " 设置进度 ", 8 /* PROPS */, ["onClick"]),
            createElementVNode("button", utsMapOf({
              type: "warn",
              onClick: _ctx.clearProgress,
              class: "button"
            }), " 清除进度 ", 8 /* PROPS */, ["onClick"])
          ])
        ])
      ])
    ], 4 /* STYLE */)
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesComponentProgressProgressStyles = [utsMapOf([["main", padStyleMapOf(utsMapOf([["maxHeight", 250], ["paddingTop", 5], ["paddingRight", 0], ["paddingBottom", 5], ["paddingLeft", 0], ["borderBottomWidth", 1], ["borderBottomStyle", "solid"], ["borderBottomColor", "rgba(0,0,0,0.06)"], ["flexDirection", "row"], ["justifyContent", "center"]]))], ["progress-box", padStyleMapOf(utsMapOf([["height", 25], ["marginBottom", 30]]))], ["button", padStyleMapOf(utsMapOf([["marginTop", 10]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
import _easycom_boolean_data from '@/components/boolean-data/boolean-data.vue'
import _easycom_input_data from '@/components/input-data/input-data.vue'
import _easycom_enum_data from '@/components/enum-data/enum-data.vue'
