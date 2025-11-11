
  function getDate(type ?: string) : string {
    const date = new Date();

    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();

    if (type === 'start') {
      year = year - 10;
    } else if (type === 'end') {
      year = year + 10;
    }

    const monthString = month > 9 ? month.toString() : '0' + month;
    const dayString = day > 9 ? day.toString() : '0' + day;

    return `${year}-${monthString}-${dayString}`;
  }
  const __sfc__ = defineComponent({
    data() {
      let hours = new Array<string>()
      let minutes = new Array<string>()
      for (let i = 1; i <= 24; i++) {
        hours.push(i.toString())
      }
      for (let i = 1; i <= 60; i++) {
        minutes.push(i.toString())
      }
      const date = new Date()
      return {
        nickname: '',
        gender: '0',
        age: 18,
        loves: ['0'],
        switch: true,
        timeZoneIndex: 0,
        timeZoneList: ['中国', '美国', '巴西', '日本'] as Array<string>,
        multiArray: [
          ['亚洲', '欧洲'],
          ['中国', '日本'],
          ['北京', '上海', '广州']
        ] as Array<Array<string>>,
        multiIndex: [0, 0, 0] as Array<number>,
        datePickerValue: getDate(null),
        startDate: getDate('start'),
        endDate: getDate('end'),
        timePickerValue: '12:01',
        time: [date.getHours() - 1, date.getMinutes() - 1] as Array<number>,
        hours: hours as Array<string>,
        minutes: minutes as Array<string>,
        comment: '',
        formData: {} as UTSJSONObject,
        // 仅测试
        testVerifySubmit: false,
        testVerifyReset: false,
      }
    },
    computed: {
      formDataText() : string {
        return JSON.stringify(this.formData)
      }
    },
    methods: {


























































      onFormSubmit(e : UniFormSubmitEvent) {
        this.formData = e.detail.value

        // 仅测试
        this.testVerifySubmit = (e.type == 'submit' && (e.target?.tagName ?? '') == "FORM")
      },
      onFormReset(e : UniFormResetEvent) {
        this.formData = {}
        this.timeZoneIndex = 0

        // 仅测试
        this.testVerifyReset = (e.type == 'reset' && (e.target?.tagName ?? '') == "FORM")
      }
    }
  })

export default __sfc__
function GenPagesComponentFormFormRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_radio = resolveComponent("radio")
const _component_radio_group = resolveComponent("radio-group")
const _component_checkbox = resolveComponent("checkbox")
const _component_checkbox_group = resolveComponent("checkbox-group")
const _component_slider = resolveComponent("slider")
const _component_switch = resolveComponent("switch")
const _component_picker_view_column = resolveComponent("picker-view-column")
const _component_picker_view = resolveComponent("picker-view")
const _component_form = resolveComponent("form", true)

  return createElementVNode("scroll-view", utsMapOf({ class: "scroll-view" }), [
    createElementVNode("view", utsMapOf({ class: "page" }), [
      createVNode(_component_form, utsMapOf({
        onSubmit: _ctx.onFormSubmit,
        onReset: _ctx.onFormReset
      }), utsMapOf({
        default: withSlotCtx((): any[] => [
          createElementVNode("view", utsMapOf({ class: "uni-form-item" }), [
            createElementVNode("text", utsMapOf({ class: "title" }), "姓名"),
            createElementVNode("input", utsMapOf({
              class: "uni-input",
              name: "nickname",
              value: _ctx.nickname,
              placeholder: "请输入姓名",
              maxlength: "-1"
            }), null, 8 /* PROPS */, ["value"])
          ]),
          createElementVNode("view", utsMapOf({ class: "uni-form-item" }), [
            createElementVNode("text", utsMapOf({ class: "title" }), "性别"),
            createVNode(_component_radio_group, utsMapOf({
              name: "gender",
              class: "flex-row"
            }), utsMapOf({
              default: withSlotCtx((): any[] => [
                createElementVNode("view", utsMapOf({ class: "group-item" }), [
                  createVNode(_component_radio, utsMapOf({
                    value: "0",
                    checked: _ctx.gender=='0'
                  }), null, 8 /* PROPS */, ["checked"]),
                  createElementVNode("text", null, "男")
                ]),
                createElementVNode("view", utsMapOf({ class: "group-item" }), [
                  createVNode(_component_radio, utsMapOf({
                    value: "1",
                    checked: _ctx.gender=='1'
                  }), null, 8 /* PROPS */, ["checked"]),
                  createElementVNode("text", null, "女")
                ])
              ]),
              _: 1 /* STABLE */
            }))
          ]),
          createElementVNode("view", utsMapOf({ class: "uni-form-item" }), [
            createElementVNode("text", utsMapOf({ class: "title" }), "爱好"),
            createVNode(_component_checkbox_group, utsMapOf({
              name: "loves",
              class: "flex-row"
            }), utsMapOf({
              default: withSlotCtx((): any[] => [
                createElementVNode("view", utsMapOf({ class: "group-item" }), [
                  createVNode(_component_checkbox, utsMapOf({
                    value: "0",
                    checked: _ctx.loves.indexOf('0')>-1
                  }), null, 8 /* PROPS */, ["checked"]),
                  createElementVNode("text", null, "读书")
                ]),
                createElementVNode("view", utsMapOf({ class: "group-item" }), [
                  createVNode(_component_checkbox, utsMapOf({
                    value: "1",
                    checked: _ctx.loves.indexOf('1')>-1
                  }), null, 8 /* PROPS */, ["checked"]),
                  createElementVNode("text", null, "写字")
                ])
              ]),
              _: 1 /* STABLE */
            }))
          ]),
          createElementVNode("view", utsMapOf({ class: "uni-form-item" }), [
            createElementVNode("text", utsMapOf({ class: "title" }), "年龄"),
            createVNode(_component_slider, utsMapOf({
              name: "age",
              value: _ctx.age,
              "show-value": true
            }), null, 8 /* PROPS */, ["value"])
          ]),
          createElementVNode("view", utsMapOf({ class: "uni-form-item" }), [
            createElementVNode("text", utsMapOf({ class: "title" }), "保留选项"),
            createElementVNode("view", null, [
              createVNode(_component_switch, utsMapOf({
                name: "switch",
                checked: _ctx.switch
              }), null, 8 /* PROPS */, ["checked"])
            ])
          ]),
          createElementVNode("view", utsMapOf({ class: "uni-form-item" }), [
            createElementVNode("text", utsMapOf({ class: "title" }), "备注"),
            createElementVNode("textarea", utsMapOf({
              name: "comment",
              value: _ctx.comment,
              placeholder: "请输入备注",
              style: normalizeStyle(utsMapOf({"background":"#FFF"}))
            }), null, 12 /* STYLE, PROPS */, ["value"])
          ]),
          createElementVNode("view", utsMapOf({ class: "uni-form-item" }), [
            createElementVNode("text", utsMapOf({ class: "title" }), "时间"),
            createVNode(_component_picker_view, utsMapOf({
              class: "picker-view",
              name: "time",
              value: _ctx.time,
              "indicator-style": "height:50px"
            }), utsMapOf({
              default: withSlotCtx((): any[] => [
                createVNode(_component_picker_view_column, null, utsMapOf({
                  default: withSlotCtx((): any[] => [
                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.hours, (item, index, __index, _cached): any => {
                      return createElementVNode("view", utsMapOf({
                        class: "picker-view-item",
                        key: index
                      }), [
                        createElementVNode("text", utsMapOf({ class: "picker-view-text" }), toDisplayString(item) + "时", 1 /* TEXT */)
                      ])
                    }), 128 /* KEYED_FRAGMENT */)
                  ]),
                  _: 1 /* STABLE */
                })),
                createVNode(_component_picker_view_column, null, utsMapOf({
                  default: withSlotCtx((): any[] => [
                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.minutes, (item, index, __index, _cached): any => {
                      return createElementVNode("view", utsMapOf({
                        class: "picker-view-item",
                        key: index
                      }), [
                        createElementVNode("text", utsMapOf({ class: "picker-view-text" }), toDisplayString(item) + "分", 1 /* TEXT */)
                      ])
                    }), 128 /* KEYED_FRAGMENT */)
                  ]),
                  _: 1 /* STABLE */
                }))
              ]),
              _: 1 /* STABLE */
            }), 8 /* PROPS */, ["value"])
          ]),
          createElementVNode("view", utsMapOf({ class: "flex-row" }), [
            createElementVNode("button", utsMapOf({
              class: "btn btn-submit",
              "form-type": "submit",
              type: "primary"
            }), "Submit"),
            createElementVNode("button", utsMapOf({
              class: "btn btn-reset",
              type: "default",
              "form-type": "reset"
            }), "Reset")
          ])
        ]),
        _: 1 /* STABLE */
      }), 8 /* PROPS */, ["onSubmit", "onReset"]),
      createElementVNode("view", utsMapOf({ class: "result" }), "提交的表单数据"),
      createElementVNode("textarea", utsMapOf({
        class: "textarea",
        value: _ctx.formDataText,
        maxlength: -1,
        "auto-height": true
      }), null, 8 /* PROPS */, ["value"])
    ])
  ])
}
const GenPagesComponentFormFormStyles = [utsMapOf([["scroll-view", padStyleMapOf(utsMapOf([["flex", 1]]))], ["page", padStyleMapOf(utsMapOf([["paddingTop", 15], ["paddingRight", 15], ["paddingBottom", 15], ["paddingLeft", 15]]))], ["flex-row", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["alignItems", "center"]]))], ["uni-form-item", padStyleMapOf(utsMapOf([["paddingTop", 15], ["paddingRight", 0], ["paddingBottom", 15], ["paddingLeft", 0]]))], ["title", padStyleMapOf(utsMapOf([["marginBottom", 10], ["opacity", 0.8]]))], ["picker", padStyleMapOf(utsMapOf([["marginLeft", 15]]))], ["group-item", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["marginRight", 20]]))], ["picker-view", padStyleMapOf(utsMapOf([["width", 200], ["height", 320], ["marginTop", 10]]))], ["picker-view-item", padStyleMapOf(utsMapOf([["height", 50]]))], ["picker-view-text", padStyleMapOf(utsMapOf([["lineHeight", "50px"], ["textAlign", "center"]]))], ["btn", padStyleMapOf(utsMapOf([["flex", 1]]))], ["btn-submit", padStyleMapOf(utsMapOf([["marginRight", 5]]))], ["btn-reset", padStyleMapOf(utsMapOf([["marginLeft", 5]]))], ["result", padStyleMapOf(utsMapOf([["marginTop", 30]]))], ["textarea", padStyleMapOf(utsMapOf([["marginTop", 5], ["paddingTop", 5], ["paddingRight", 5], ["paddingBottom", 5], ["paddingLeft", 5], ["backgroundColor", "#ffffff"]]))]])]
