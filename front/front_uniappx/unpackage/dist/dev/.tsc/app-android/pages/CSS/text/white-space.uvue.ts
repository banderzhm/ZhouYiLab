
  import { ItemType } from '@/components/enum-data/enum-data-types'
  const __sfc__ = defineComponent({
    data() {
      return {
        textStyle: "white-space: normal;",
        white_space: [
          { "value": 0, "name": "normal" },
          { "value": 1, "name": "nowrap" },
          { "value": 2, "name": "pre" },
          { "value": 3, "name": "pre-wrap" },
          { "value": 4, "name": "pre-line" },
          { "value": 5, "name": "break-spaces" }] as ItemType[],
        multiLineText: `HBuilderX，
	轻巧、
		极速，
			极客编辑器；
				uni-app x，
					终极跨平台方案；
				uts，
			大一统语言

HBuilderX，轻巧、极速，极客编辑器；uni-app x，终极跨平台方案；uts，大一统语言`
      }
    },
    methods: {
      radio_change(checked : number) {
        this.textStyle = `white-space: ${this.white_space[checked].name};`
      }
    },
  })

export default __sfc__
function GenPagesCSSTextWhiteSpaceRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_enum_data = resolveEasyComponent("enum-data",_easycom_enum_data)

  return createElementVNode("view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex-grow":"1"}))
  }), [
    createElementVNode("scroll-view", utsMapOf({
      style: normalizeStyle(utsMapOf({"padding":"10px 0px","background-color":"gray","justify-content":"center"})),
      direction: "horizontal"
    }), [
      createElementVNode("text", utsMapOf({
        class: "text",
        style: normalizeStyle(_ctx.textStyle)
      }), toDisplayString(_ctx.multiLineText), 5 /* TEXT, STYLE */)
    ], 4 /* STYLE */),
    createElementVNode("scroll-view", utsMapOf({
      style: normalizeStyle(utsMapOf({"flex":"1"}))
    }), [
      createElementVNode("view", utsMapOf({ class: "content" }), [
        createVNode(_component_enum_data, utsMapOf({
          items: _ctx.white_space,
          title: "white-space",
          onChange: _ctx.radio_change
        }), null, 8 /* PROPS */, ["items", "onChange"])
      ])
    ], 4 /* STYLE */)
  ], 4 /* STYLE */)
}
const GenPagesCSSTextWhiteSpaceStyles = [utsMapOf([["text", padStyleMapOf(utsMapOf([["fontSize", 20], ["alignSelf", "flex-start"]]))]])]

import _easycom_enum_data from '@/components/enum-data/enum-data.vue'
