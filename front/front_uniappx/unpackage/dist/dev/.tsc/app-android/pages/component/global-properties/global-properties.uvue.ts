
  const __sfc__ = defineComponent({
    data() {
      return {
        title: 'global-properties',
        generalId: 'general-id',
        generalClass: 'general-class',
        generalName: 'general-name',
        generalTitle: 'general-title',
        generalData: 'general-data',
        generalStyle: 'background-color: aqua',
        validateGeneralAttrText: '验证基础属性',
        hoverClass: 'hover-class',
        validateViewAttrText: '验证 view 属性',
        changeHeightByRefText: '通过 ref 修改高度',
      }
    },
    methods: {
      validateGeneralAttributes() {
        const generalTarget = this.$refs['general-target'] as UniElement
        const generalId = generalTarget.getAttribute('id')
        if (generalId != this.generalId) {
          this.validateGeneralAttrText = '基础属性 id 验证失败'
          return
        }

        if (!generalTarget.classList.includes('general-class')) {
          this.validateGeneralAttrText = '基础属性 class 验证失败'
          return
        }








        const generalName = generalTarget.getAttribute('name')
        if (generalName != this.generalName) {
          this.validateGeneralAttrText = '基础属性 name 验证失败'
          return
        }
        const generalTitle = generalTarget.getAttribute('title')
        if (generalTitle != this.generalTitle) {
          this.validateGeneralAttrText = '基础属性 title 验证失败'
          return
        }
        const generalData = generalTarget.getAttribute('data-test')
        if (generalData != this.generalData) {
          this.validateGeneralAttrText = '基础属性 data-test 验证失败'
          return
        }

        this.validateGeneralAttrText = '基础属性验证成功'
      },
      changeHeight() {
        const generalTarget = this.$refs['general-target'] as UniElement
        this.changeHeightByRefText = '已通过 ref 修改高度'
        generalTarget.style.setProperty('height', '200px')
      }
    },
  })

export default __sfc__
function GenPagesComponentGlobalPropertiesGlobalPropertiesRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("view", null, [
      createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
      createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
        createElementVNode("view", utsMapOf({
          id: _ctx.generalId,
          class: normalizeClass(_ctx.generalClass),
          name: _ctx.generalName,
          title: _ctx.generalTitle,
          "data-test": _ctx.generalData,
          style: normalizeStyle(_ctx.generalStyle),
          ref: "general-target"
        }), [
          createElementVNode("text", null, "id: " + toDisplayString(_ctx.generalId), 1 /* TEXT */),
          createElementVNode("text", null, "class: " + toDisplayString(_ctx.generalClass), 1 /* TEXT */),
          createElementVNode("text", null, "name: " + toDisplayString(_ctx.generalName), 1 /* TEXT */),
          createElementVNode("text", null, "title: " + toDisplayString(_ctx.generalTitle), 1 /* TEXT */),
          createElementVNode("text", null, "data-test: " + toDisplayString(_ctx.generalData), 1 /* TEXT */),
          createElementVNode("text", null, "style: " + toDisplayString(_ctx.generalStyle), 1 /* TEXT */)
        ], 14 /* CLASS, STYLE, PROPS */, ["id", "name", "title", "data-test"]),
        createElementVNode("view", utsMapOf({
          class: "btn btn-style uni-common-mt",
          onClick: _ctx.validateGeneralAttributes
        }), [
          createElementVNode("text", utsMapOf({ class: "btn-inner" }), toDisplayString(_ctx.validateGeneralAttrText), 1 /* TEXT */)
        ], 8 /* PROPS */, ["onClick"]),
        createElementVNode("view", utsMapOf({
          class: "btn btn-ref uni-common-mt",
          onClick: _ctx.changeHeight
        }), [
          createElementVNode("text", utsMapOf({ class: "btn-inner" }), toDisplayString(_ctx.changeHeightByRefText), 1 /* TEXT */)
        ], 8 /* PROPS */, ["onClick"]),
        createElementVNode("view", utsMapOf({
          class: "view-class",
          "hover-class": _ctx.hoverClass,
          ref: "view-target"
        }), [
          createElementVNode("text", utsMapOf({ class: "text" }), "按下 50 ms 后背景变红"),
          createElementVNode("text", utsMapOf({ class: "text" }), "抬起 400 ms 后背景恢复")
        ], 8 /* PROPS */, ["hover-class"]),
        createElementVNode("view", utsMapOf({
          class: "view-class",
          "hover-class": _ctx.hoverClass,
          "hover-start-time": 1000,
          "hover-stay-time": 1000,
          ref: "view-target"
        }), [
          createElementVNode("text", utsMapOf({ class: "text" }), "按下 1000 ms 后背景变红"),
          createElementVNode("text", utsMapOf({ class: "text" }), "抬起 1000 ms 后背景恢复")
        ], 8 /* PROPS */, ["hover-class"])
      ])
    ])
  ], 4 /* STYLE */)
}
const GenPagesComponentGlobalPropertiesGlobalPropertiesStyles = [utsMapOf([["btn", padStyleMapOf(utsMapOf([["height", 50], ["display", "flex"], ["alignItems", "center"], ["justifyContent", "center"], ["backgroundColor", "#409eff"], ["borderTopLeftRadius", 5], ["borderTopRightRadius", 5], ["borderBottomRightRadius", 5], ["borderBottomLeftRadius", 5]]))], ["btn-inner", padStyleMapOf(utsMapOf([["color", "#ffffff"]]))], ["general-class", padStyleMapOf(utsMapOf([["marginLeft", 40], ["paddingTop", 10], ["paddingRight", 10], ["paddingBottom", 10], ["paddingLeft", 10], ["width", 260], ["height", 160], ["backgroundColor", "#FAEBD7"]]))], ["view-class", padStyleMapOf(utsMapOf([["marginTop", 20], ["marginRight", 0], ["marginBottom", 0], ["marginLeft", 50], ["paddingTop", 10], ["paddingRight", 10], ["paddingBottom", 10], ["paddingLeft", 10], ["width", 240], ["height", 100], ["backgroundColor", "#FAEBD7"]]))], ["text", utsMapOf([[".view-class ", utsMapOf([["marginTop", 5], ["textAlign", "center"]])]])], ["hover-class", padStyleMapOf(utsMapOf([["backgroundColor", "#FF0000"]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
