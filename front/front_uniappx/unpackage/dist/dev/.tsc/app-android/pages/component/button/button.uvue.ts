
  import { ItemType } from '@/components/enum-data/enum-data-types'
  const __sfc__ = defineComponent({
    data() {
      return {
        plain_boolean: false,
        disabled_boolean: false,
        default_style: false,
        size_enum: [{ "value": 0, "name": "default" }, { "value": 1, "name": "mini" }] as ItemType[],
        size_enum_current: 0,
        type_enum: [{ "value": 0, "name": "default" }, { "value": 1, "name": "primary" }, { "value": 2, "name": "warn" }] as ItemType[],
        type_enum_current: 0,
        count: 0,
        text: ''
      }
    },
    onReady() {
      this.text = 'uni-app-x'
    },
    methods: {
      button_click() {
        console.log("组件被点击时触发", " at pages/component/button/button.uvue:22")
        this.count++
      },
      button_touchstart() { console.log("手指触摸动作开始", " at pages/component/button/button.uvue:25") },
      button_touchmove() { console.log("手指触摸后移动", " at pages/component/button/button.uvue:26") },
      button_touchcancel() { console.log("手指触摸动作被打断，如来电提醒，弹窗", " at pages/component/button/button.uvue:27") },
      button_touchend() { console.log("手指触摸动作结束", " at pages/component/button/button.uvue:28") },
      button_tap() { console.log("手指触摸后马上离开", " at pages/component/button/button.uvue:29") },
      button_longpress() { console.log("如果一个组件被绑定了 longpress 事件，那么当用户长按这个组件时，该事件将会被触发。", " at pages/component/button/button.uvue:30") },
      change_plain_boolean(checked : boolean) { this.plain_boolean = checked },
      change_disabled_boolean(checked : boolean) { this.disabled_boolean = checked },
      change_default_style(checked : boolean) { this.default_style = checked },
      radio_change_size_enum(checked : number) { this.size_enum_current = checked },
      radio_change_type_enum(checked : number) { this.type_enum_current = checked },
      confirm_text_input(value : string) { this.text = value },
      navigateToChild() {
        uni.navigateTo({
          url: 'buttonstatus',
        })
      },
      openPrivacyDialog() {
        uni.openDialogPage({
          url: '/pages/component/button/privacy',
        })
      },
      //用于自动化测试
      checkUniButtonElement() : boolean {
        const button = uni.getElementById("testButton")
        if (button != null && button instanceof UniButtonElement) {
          return true
        }
        return false
      }
    }
  })

export default __sfc__
function GenPagesComponentButtonButtonRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_boolean_data = resolveEasyComponent("boolean-data",_easycom_boolean_data)
const _component_enum_data = resolveEasyComponent("enum-data",_easycom_enum_data)
const _component_input_data = resolveEasyComponent("input-data",_easycom_input_data)

  return createElementVNode(Fragment, null, [
    createElementVNode("view", utsMapOf({ class: "main" }), [
      createElementVNode("button", utsMapOf({
        id: "testButton",
        disabled: _ctx.disabled_boolean,
        size: _ctx.size_enum[_ctx.size_enum_current].name,
        type: _ctx.type_enum[_ctx.type_enum_current].name,
        plain: _ctx.plain_boolean,
        onClick: [_ctx.button_click, _ctx.button_tap],
        onTouchstart: _ctx.button_touchstart,
        onTouchmove: _ctx.button_touchmove,
        onTouchcancel: _ctx.button_touchcancel,
        onTouchend: _ctx.button_touchend,
        onLongpress: _ctx.button_longpress,
        class: normalizeClass(["btn", _ctx.default_style ? (_ctx.disabled_boolean ? 'custom-btn-disable' : 'custom-btn') : '']),
        "hover-class": _ctx.default_style ? 'is-hover' : 'button-hover'
      }), toDisplayString(_ctx.text), 43 /* TEXT, CLASS, PROPS, NEED_HYDRATION */, ["disabled", "size", "type", "plain", "onClick", "onTouchstart", "onTouchmove", "onTouchcancel", "onTouchend", "onLongpress", "hover-class"])
    ]),
    createElementVNode("scroll-view", utsMapOf({
      style: normalizeStyle(utsMapOf({"flex":"1"}))
    }), [
      createElementVNode("view", utsMapOf({ class: "content" }), [
        createVNode(_component_boolean_data, utsMapOf({
          defaultValue: false,
          title: "按钮是否镂空，背景色透明",
          onChange: _ctx.change_plain_boolean
        }), null, 8 /* PROPS */, ["onChange"]),
        createVNode(_component_boolean_data, utsMapOf({
          defaultValue: false,
          title: "是否禁用",
          onChange: _ctx.change_disabled_boolean
        }), null, 8 /* PROPS */, ["onChange"]),
        createVNode(_component_boolean_data, utsMapOf({
          defaultValue: false,
          title: "修改默认样式和点击效果(高优先)",
          onChange: _ctx.change_default_style
        }), null, 8 /* PROPS */, ["onChange"]),
        createVNode(_component_enum_data, utsMapOf({
          items: _ctx.size_enum,
          title: "按钮的大小",
          onChange: _ctx.radio_change_size_enum
        }), null, 8 /* PROPS */, ["items", "onChange"]),
        createVNode(_component_enum_data, utsMapOf({
          items: _ctx.type_enum,
          title: "按钮的类型",
          onChange: _ctx.radio_change_type_enum
        }), null, 8 /* PROPS */, ["items", "onChange"]),
        createVNode(_component_input_data, utsMapOf({
          defaultValue: _ctx.text,
          title: "按钮的文案",
          type: "text",
          onConfirm: _ctx.confirm_text_input
        }), null, 8 /* PROPS */, ["defaultValue", "onConfirm"]),
        createElementVNode("view", utsMapOf({
          style: normalizeStyle(utsMapOf({"height":"10px"}))
        }), null, 4 /* STYLE */),
        createElementVNode("button", utsMapOf({ onClick: _ctx.navigateToChild }), "更多示例", 8 /* PROPS */, ["onClick"]),
        createElementVNode("view", utsMapOf({
          style: normalizeStyle(utsMapOf({"height":"10px"}))
        }), null, 4 /* STYLE */),
        createElementVNode("button", utsMapOf({ onClick: _ctx.openPrivacyDialog }), "open-type实现App隐私政策弹框", 8 /* PROPS */, ["onClick"]),
        createElementVNode("view", utsMapOf({
          style: normalizeStyle(utsMapOf({"height":"10px"}))
        }), null, 4 /* STYLE */)
      ])
    ], 4 /* STYLE */)
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesComponentButtonButtonStyles = [utsMapOf([["main", padStyleMapOf(utsMapOf([["paddingTop", 5], ["paddingRight", 0], ["paddingBottom", 5], ["paddingLeft", 0], ["borderBottomWidth", 1], ["borderBottomStyle", "solid"], ["borderBottomColor", "rgba(0,0,0,0.06)"], ["flexDirection", "row"], ["justifyContent", "center"]]))], ["custom-btn", padStyleMapOf(utsMapOf([["color", "#ffffff"], ["backgroundColor", "#1AAD19"], ["borderTopColor", "#1AAD19"], ["borderRightColor", "#1AAD19"], ["borderBottomColor", "#1AAD19"], ["borderLeftColor", "#1AAD19"]]))], ["custom-btn-disable", padStyleMapOf(utsMapOf([["color", "rgba(255,255,255,0.7)"], ["backgroundColor", "rgba(26,173,25,0.7)"], ["borderTopColor", "rgba(26,173,25,0.7)"], ["borderRightColor", "rgba(26,173,25,0.7)"], ["borderBottomColor", "rgba(26,173,25,0.7)"], ["borderLeftColor", "rgba(26,173,25,0.7)"]]))], ["is-hover", padStyleMapOf(utsMapOf([["color", "rgba(255,255,255,0.6)"], ["backgroundColor", "#179b16"], ["borderTopColor", "#179b16"], ["borderRightColor", "#179b16"], ["borderBottomColor", "#179b16"], ["borderLeftColor", "#179b16"]]))], ["button-hover", padStyleMapOf(utsMapOf([["color", "rgba(0,0,0,0.6)"], ["backgroundColor", "#dedede"]]))], ["default-button", padStyleMapOf(utsMapOf([["color", "#000000"], ["backgroundColor", "#f8f8f8"], ["borderTopColor", "rgba(0,0,0,0.2)"], ["borderRightColor", "rgba(0,0,0,0.2)"], ["borderBottomColor", "rgba(0,0,0,0.2)"], ["borderLeftColor", "rgba(0,0,0,0.2)"]]))]])]

import _easycom_boolean_data from '@/components/boolean-data/boolean-data.vue'
import _easycom_enum_data from '@/components/enum-data/enum-data.vue'
import _easycom_input_data from '@/components/input-data/input-data.vue'
