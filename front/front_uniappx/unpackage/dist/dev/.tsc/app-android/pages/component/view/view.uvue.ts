
  import { ItemType } from '@/components/enum-data/enum-data-types'
  import { state } from '@/store/index.uts'
  const __sfc__ = defineComponent({
    data() {
      return {
        hover_class: false,
        stop_propagation: false,
        start_time: 50,
        stay_time: 400,
        start_time_enum: [{ "value": 50, "name": "50毫秒" }, { "value": 200, "name": "200毫秒" }] as ItemType[],
        stay_time_enum: [{ "value": 400, "name": "400毫秒" }, { "value": 200, "name": "200毫秒" }] as ItemType[]
      }
    },
    computed: {
      isDarkMode() : boolean {
        return state.isDarkMode
      }
    },
    methods: {
      change_hover_class_boolean(checked : boolean) {
        this.hover_class = checked
      },
      change_stop_propagation_boolean(checked : boolean) {
        this.stop_propagation = checked
      },
      radio_change_start_time_enum(time : number) {
        this.start_time = time
      },
      radio_change_stay_time_enum(time : number) {
        this.stay_time = time
      }
    }
  })

export default __sfc__
function GenPagesComponentViewViewRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)
const _component_boolean_data = resolveEasyComponent("boolean-data",_easycom_boolean_data)
const _component_enum_data = resolveEasyComponent("enum-data",_easycom_enum_data)

  return createElementVNode(Fragment, null, [
    createVNode(_component_page_head, utsMapOf({ title: "view" })),
    createElementVNode("view", utsMapOf({
      id: "view",
      class: "main",
      "hover-class": _ctx.hover_class ? 'is-parent-hover' : 'none'
    }), [
      createElementVNode("view", utsMapOf({
        id: "view-child1",
        class: normalizeClass(["test-view", _ctx.isDarkMode ? 'theme-dark' : 'theme-light']),
        "hover-class": _ctx.hover_class ? 'is-hover' : 'none',
        "hover-stop-propagation": _ctx.stop_propagation,
        "hover-start-time": _ctx.start_time,
        "hover-stay-time": _ctx.stay_time
      }), null, 10 /* CLASS, PROPS */, ["hover-class", "hover-stop-propagation", "hover-start-time", "hover-stay-time"])
    ], 8 /* PROPS */, ["hover-class"]),
    createElementVNode("scroll-view", utsMapOf({
      style: normalizeStyle(utsMapOf({"flex":"1"}))
    }), [
      createElementVNode("view", utsMapOf({ class: "content" }), [
        createVNode(_component_boolean_data, utsMapOf({
          defaultValue: false,
          title: "是否指定按下去的样式类",
          onChange: _ctx.change_hover_class_boolean
        }), null, 8 /* PROPS */, ["onChange"]),
        createVNode(_component_boolean_data, utsMapOf({
          defaultValue: false,
          title: "是否阻止本节点的祖先节点出现点击态",
          onChange: _ctx.change_stop_propagation_boolean
        }), null, 8 /* PROPS */, ["onChange"]),
        createVNode(_component_enum_data, utsMapOf({
          items: _ctx.start_time_enum,
          title: "按住后多久出现点击态",
          onChange: _ctx.radio_change_start_time_enum
        }), null, 8 /* PROPS */, ["items", "onChange"]),
        createVNode(_component_enum_data, utsMapOf({
          items: _ctx.stay_time_enum,
          title: "手指松开后点击态保留时间",
          onChange: _ctx.radio_change_stay_time_enum
        }), null, 8 /* PROPS */, ["items", "onChange"])
      ])
    ], 4 /* STYLE */)
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesComponentViewViewStyles = [utsMapOf([["main", padStyleMapOf(utsMapOf([["paddingTop", 5], ["paddingRight", 0], ["paddingBottom", 5], ["paddingLeft", 0], ["flexDirection", "row"], ["justifyContent", "center"]]))], ["test-view", padStyleMapOf(utsMapOf([["height", 200], ["width", 200], ["backgroundColor", "var(--list-background-color,#ffffff)"]]))], ["is-hover", padStyleMapOf(utsMapOf([["backgroundColor", "#179b16"]]))], ["is-parent-hover", padStyleMapOf(utsMapOf([["backgroundColor", "#aa0000"]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
import _easycom_boolean_data from '@/components/boolean-data/boolean-data.vue'
import _easycom_enum_data from '@/components/enum-data/enum-data.vue'
