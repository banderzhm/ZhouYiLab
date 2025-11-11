
  type ItemType = { __$originalPosition?: UTSSourceMapPosition<"ItemType", "pages/API/modal/modal.uvue", 85, 8>;
    value : string,
    name : string,
  }
  const __sfc__ = defineComponent({
    data() {
      return {
        title: 'modal',
        showCancelSelect: false,
        showLongContent: false,
        illegalColorSelect:false,
        illegalButtonTextSelect:false,
        cancelTextSelect: false,
        confirmTextSelect: false,
        editableSelect: false,
        placeholderTextSelect: false,
        exeRet: "",
        lastModal : null as UniPage | null,
        items: [{
          value: '标题',
          name: '有标题'
        },
        {
          value: '',
          name: '无标题'
        },
        {
          value: '超长标题测试内容，测试超过显示最大范围之后的样式-超长标题测试内容，测试超过显示最大范围之后的样式',
          name: '超长标题'
        }
        ] as ItemType[],
        current: 0,
        timesShowRet:[] as Array<any>,
      }
    },
  onLoad(options: OnLoadOptions) {
      if(options['onLoadShowModal'] == 'false') {
        return
      }
      uni.showModal({
        title: "onLoad 调用示例,请手动取消"
      })
    },
    methods: {
      showLongContentChange: function (e : UniSwitchChangeEvent) {
        this.showLongContent = e.detail.value
      },
      illegalColorChange: function (e : UniSwitchChangeEvent) {
        this.illegalColorSelect = e.detail.value
      },
      illegalButtonTextChange: function (e : UniSwitchChangeEvent) {
        this.illegalButtonTextSelect = e.detail.value
      },
      showCancelChange: function (e : UniSwitchChangeEvent) {
        this.showCancelSelect = e.detail.value
      },
      cancelTextChange: function (e : UniSwitchChangeEvent) {
        this.cancelTextSelect = e.detail.value
      },
      confirmTextChange: function (e : UniSwitchChangeEvent) {
        this.confirmTextSelect = e.detail.value
      },
      editableChange: function (e : UniSwitchChangeEvent) {
        this.editableSelect = e.detail.value
      },
      placeholderTextChange: function (e : UniSwitchChangeEvent) {
        this.editableSelect = e.detail.value
        this.placeholderTextSelect = e.detail.value
      },
      radioChange(e : UniRadioGroupChangeEvent) {
        for (let i = 0; i < this.items.length; i++) {
          if (this.items[i].value === e.detail.value) {
            this.current = i;
            break;
          }
        }
      },
      closeLastModal: function () {
        let that = this
        setTimeout(function(){
          uni.hideModal({
            modalPage:that.lastModal,
            success: (res) => {
              that.exeRet = JSON.stringify(res)
            },
            fail: (res) => {
              that.exeRet = JSON.stringify(res)
            },
            complete: (res) => {
              that.timesShowRet.push(res)
            }
          })
        },3000)
      },
      closeAllModal: function () {
        let that = this
        setTimeout(function(){
          uni.hideModal({
            modalPage:null,
            success: (res) => {
              that.exeRet = JSON.stringify(res)
            },
            fail: (res) => {
              that.exeRet = JSON.stringify(res)
            },
            complete: (res) => {
              that.timesShowRet.push(res)
            }
          })
        },3000)

      },
      modalTapTimes: function () {
        // 多次弹出前清空结果集合
        this.timesShowRet = []
        this.modalTap()
        this.modalTap()
        let that = this
        setTimeout(function(){
          that.modalTap()
        },200)
      },
      modalTap: function () {
         // 单次弹出前清空多次结果集合
        this.timesShowRet = []

        let cancelTextVal : string|null = null
        let cancelColorVal = ''
        if (this.cancelTextSelect) {
          cancelTextVal = "修改后的取消文本"
          cancelColorVal = "#ff00ff"
        }else{



        }

        let confirmTextVal : string|null = null
        let confirmColorVal = ''
        if (this.confirmTextSelect) {
          confirmTextVal = "修改后的确定文本"
          confirmColorVal = "#0ff"
        }else{



        }
        let placeholderTextVal = ''
        let contentVal = "弹窗内容，告知当前状态、信息和解决方法，描述文字尽量控制在三行内"

        if (this.showLongContent) {
          contentVal = "弹窗内容，告知当前状态、信息和解决方法，描述文字原则上可以无限多！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！"
        }
        if (this.placeholderTextSelect) {
          placeholderTextVal = "定制提示信息"
        }
        if (this.illegalColorSelect) {
          cancelColorVal = "#WW00ff"
          confirmColorVal = "#0ffff"
        }
        if (this.illegalButtonTextSelect) {
          confirmTextVal = "超长版文字-修改后的确定文本-超长版本文字"
          cancelTextVal = "超长版文字-修改后的取消文本-超长版本文字"
        }

        let op = {
          title: this.items[this.current].value,
          editable: this.editableSelect,
          placeholderText: placeholderTextVal,
          content: contentVal,
          showCancel: this.showCancelSelect,
          cancelText: cancelTextVal,
          cancelColor: cancelColorVal,
          confirmText: confirmTextVal,
          confirmColor: confirmColorVal,
          success: (res) => {
            this.exeRet = JSON.stringify(res)
          },
          fail: (res) => {
            this.exeRet = JSON.stringify(res)
          },
          complete: (res) => {
            this.timesShowRet.push(res)
          }
        } as ShowModalOptions
        // console.log(op)
        this.lastModal = uni.showModal(op)
      }

    }
  })

export default __sfc__
function GenPagesAPIModalModalRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)
const _component_radio = resolveComponent("radio")
const _component_radio_group = resolveComponent("radio-group")
const _component_switch = resolveComponent("switch")

  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("view", null, [
      createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
      createElementVNode("view", utsMapOf({ class: "uni-list" }), [
        createVNode(_component_radio_group, utsMapOf({ onChange: _ctx.radioChange }), utsMapOf({
          default: withSlotCtx((): any[] => [
            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.items, (item, index, __index, _cached): any => {
              return createVNode(_component_radio, utsMapOf({
                class: normalizeClass(["uni-list-cell uni-list-cell-pd", index < _ctx.items.length - 1 ? 'uni-list-cell-line' : '']),
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
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-list" }), [
        createElementVNode("view", utsMapOf({ class: "uni-list-cell uni-list-cell-pd" }), [
          createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), "是否显示过长文字"),
          createVNode(_component_switch, utsMapOf({
            checked: _ctx.showLongContent,
            onChange: _ctx.showLongContentChange
          }), null, 8 /* PROPS */, ["checked", "onChange"])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-list-cell uni-list-cell-pd" }), [
          createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), "是否显示取消按钮"),
          createVNode(_component_switch, utsMapOf({
            checked: _ctx.showCancelSelect,
            onChange: _ctx.showCancelChange
          }), null, 8 /* PROPS */, ["checked", "onChange"])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-list-cell uni-list-cell-pd" }), [
          createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), "定制取消文案"),
          createVNode(_component_switch, utsMapOf({
            checked: _ctx.cancelTextSelect,
            onChange: _ctx.cancelTextChange
          }), null, 8 /* PROPS */, ["checked", "onChange"])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-list-cell uni-list-cell-pd" }), [
          createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), "定制确认文案"),
          createVNode(_component_switch, utsMapOf({
            checked: _ctx.confirmTextSelect,
            onChange: _ctx.confirmTextChange
          }), null, 8 /* PROPS */, ["checked", "onChange"])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-list-cell uni-list-cell-pd" }), [
          createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), "测试非法的颜色"),
          createVNode(_component_switch, utsMapOf({
            checked: _ctx.illegalColorSelect,
            onChange: _ctx.illegalColorChange
          }), null, 8 /* PROPS */, ["checked", "onChange"])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-list-cell uni-list-cell-pd" }), [
          createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), "测试超长的按钮文本"),
          createVNode(_component_switch, utsMapOf({
            checked: _ctx.illegalButtonTextSelect,
            onChange: _ctx.illegalButtonTextChange
          }), null, 8 /* PROPS */, ["checked", "onChange"])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-list-cell uni-list-cell-pd" }), [
          createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), "是否显示输入框"),
          createVNode(_component_switch, utsMapOf({
            checked: _ctx.editableSelect,
            onChange: _ctx.editableChange
          }), null, 8 /* PROPS */, ["checked", "onChange"])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-list-cell uni-list-cell-pd" }), [
          createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), "是否定制输入提示词"),
          createVNode(_component_switch, utsMapOf({
            checked: _ctx.placeholderTextSelect,
            onChange: _ctx.placeholderTextChange
          }), null, 8 /* PROPS */, ["checked", "onChange"])
        ])
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-padding-wrap uni-common-mt" }), [
        createElementVNode("text", null, "complete 执行结果：" + toDisplayString(_ctx.timesShowRet), 1 /* TEXT */),
        createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
          createElementVNode("button", utsMapOf({
            class: "uni-btn-v",
            type: "primary",
            onClick: _ctx.modalTap,
            id: "btn-modal-show"
          }), " modal单次弹出 ", 8 /* PROPS */, ["onClick"])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
          createElementVNode("button", utsMapOf({
            class: "uni-btn-v",
            type: "default",
            onClick: _ctx.modalTapTimes,
            id: "btn-modal-show-multitime"
          }), " modal多次弹出 ", 8 /* PROPS */, ["onClick"])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
          createElementVNode("button", utsMapOf({
            class: "uni-btn-v",
            type: "default",
            onClick: _ctx.closeAllModal,
            id: "btn-modal-hide-all"
          }), " 延迟3s关闭全部弹窗 ", 8 /* PROPS */, ["onClick"])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
          createElementVNode("button", utsMapOf({
            class: "uni-btn-v",
            type: "default",
            onClick: _ctx.closeLastModal,
            id: "btn-modal-hide-last"
          }), " 延迟3s关闭最后一个弹窗 ", 8 /* PROPS */, ["onClick"])
        ]),
        createElementVNode("text", null, " success/fail 执行结果：" + toDisplayString(_ctx.exeRet), 1 /* TEXT */)
      ])
    ])
  ], 4 /* STYLE */)
}
const GenPagesAPIModalModalStyles = []

import _easycom_page_head from '@/components/page-head/page-head.vue'
