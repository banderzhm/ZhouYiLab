
  type ItemType = { __$originalPosition?: UTSSourceMapPosition<"ItemType", "pages/API/loading/loading.uvue", 34, 8>;
    value : string
    name : string
  }
  const __sfc__ = defineComponent({
    data() {
      return {
        title: 'loading',
        items: [
          {
            value: 'null',
            name: '无标题',
          },
          {
            value: '三秒后自动关闭',
            name: '普通标题',
          },
          {
            value: '超长文本内容，测试超出范围-超长文本内容，测试超出范围-三秒后自动关闭',
            name: '长标题',
          },
        ] as ItemType[],
        current: 0,
        maskSelect: false,
        titleSelect: "null"
      }
    },
    onLoad() {
      uni.showLoading({
        title: 'onLoad 调用示例,2秒后消失'
      })
      setTimeout(function () {
        uni.hideLoading()
      }, 2000);
    },
    methods: {
      //自动化测试例专用
      jest_getWindowInfo() : GetWindowInfoResult {
        return uni.getWindowInfo();
      },

      radioChange(e : UniRadioGroupChangeEvent) {
        const selected = this.items.find((item) : boolean => {
          return item.value == e.detail.value
        })
        if (selected != null) {
          this.titleSelect = selected.value
        }
      },
      maskChange: function (e : UniSwitchChangeEvent) {
        this.maskSelect = e.detail.value
      },
      showLoading: function () {

        console.log(this.titleSelect, " at pages/API/loading/loading.uvue:88")
        if (this.titleSelect == "null") {
          uni.showLoading({
            title: "",
            mask: this.maskSelect
          });
        } else {
          uni.showLoading({
            title: this.titleSelect,
            mask: this.maskSelect
          });
        }
        setTimeout(() => {
          this.hideLoading();
        }, 3000);
      },
      hideLoading: function () {
        uni.hideLoading();
      }
    }
  })

export default __sfc__
function GenPagesAPILoadingLoadingRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)
const _component_switch = resolveComponent("switch")
const _component_radio = resolveComponent("radio")
const _component_radio_group = resolveComponent("radio-group")

  return createElementVNode("view", null, [
    createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
    createElementVNode("view", utsMapOf({ class: "uni-list" }), [
      createElementVNode("view", utsMapOf({ class: "uni-list-cell uni-list-cell-pd" }), [
        createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), "是否显示透明蒙层-屏蔽点击事件"),
        createVNode(_component_switch, utsMapOf({
          checked: _ctx.maskSelect,
          onChange: _ctx.maskChange
        }), null, 8 /* PROPS */, ["checked", "onChange"])
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
        createElementVNode("view", utsMapOf({ class: "uni-title uni-common-mt" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), " 设置标题 ")
        ])
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-list uni-common-pl" }), [
        createVNode(_component_radio_group, utsMapOf({ onChange: _ctx.radioChange }), utsMapOf({
          default: withSlotCtx((): any[] => [
            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.items, (item, index, __index, _cached): any => {
              return createVNode(_component_radio, utsMapOf({
                class: normalizeClass(["uni-list-cell uni-list-cell-pd radio", index < _ctx.items.length - 1 ? 'uni-list-cell-line' : '']),
                key: item.value,
                value: item.value,
                checked: index === _ctx.current
              }), utsMapOf({
                default: withSlotCtx((): any[] => [toDisplayString(item.name)]),
                _: 2 /* DYNAMIC */
              }), 1032 /* PROPS, DYNAMIC_SLOTS */, ["class", "value", "checked"])
            }), 128 /* KEYED_FRAGMENT */)
          ]),
          _: 1 /* STABLE */
        }), 8 /* PROPS */, ["onChange"])
      ])
    ]),
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
      createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
        createElementVNode("button", utsMapOf({
          class: "uni-btn-v",
          type: "primary",
          onClick: _ctx.showLoading
        }), "显示 loading 提示框", 8 /* PROPS */, ["onClick"]),
        createElementVNode("button", utsMapOf({
          class: "uni-btn-v",
          onClick: _ctx.hideLoading
        }), "隐藏 loading 提示框", 8 /* PROPS */, ["onClick"]),
        createElementVNode("text", null, "为方便演示，loading弹出3秒后自动关闭")
      ])
    ])
  ])
}
const GenPagesAPILoadingLoadingStyles = []

import _easycom_page_head from '@/components/page-head/page-head.vue'
