
  import { COLLECTION_NAME, GenderType, GenderList, UNICLOUD_DB_CONTACTS_ADD } from './types.uts'

  const __sfc__ = defineComponent({
    data() {
      return {
        collection: COLLECTION_NAME,
        genderList: GenderList as GenderType[],
        uniCloudElement: null as UniCloudDBElement | null
      }
    },
    onReady() {
      this.uniCloudElement = this.$refs['udb'] as UniCloudDBElement
    },
    methods: {
      onFormSubmit: function (e : UniFormSubmitEvent) {
        const formData = e.detail.value
        const genderString = formData['gender'] as string
        formData['gender'] = (genderString.length > 0) ? parseInt(genderString) : -1
        console.log('formData', formData, " at pages/component/unicloud-db/unicloud-db/contacts/add.uvue:54")
        this.uniCloudElement!.add(formData, {
          showToast: false,
          needLoading: true,
          loadingTitle: "正在保存...",
          success: (_ : UniCloudDBAddResult) => {
            // TODO 后续通过 EventChannel 实现
            uni.$emit(UNICLOUD_DB_CONTACTS_ADD, '')
            setTimeout(() => {
              uni.navigateBack()
            }, 500)
          },
          fail: (err : any | null) => {
            const error = err as UniCloudError
            uni.showModal({
              content: error.errMsg,
              showCancel: false
            })
          }
        })
      }
    }
  })

export default __sfc__
function GenPagesComponentUnicloudDbUnicloudDbContactsAddRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_unicloud_db = resolveEasyComponent("unicloud-db",_easycom_unicloud_db)
const _component_radio = resolveComponent("radio")
const _component_radio_group = resolveComponent("radio-group")
const _component_form = resolveComponent("form")

  return createElementVNode("view", utsMapOf({ class: "page" }), [
    createVNode(_component_unicloud_db, utsMapOf({
      ref: "udb",
      collection: _ctx.collection,
      loadtime: "manual"
    }), null, 8 /* PROPS */, ["collection"]),
    createVNode(_component_form, utsMapOf({ onSubmit: _ctx.onFormSubmit }), utsMapOf({
      default: withSlotCtx((): any[] => [
        createElementVNode("view", utsMapOf({ class: "form-item" }), [
          createElementVNode("text", utsMapOf({ class: "form-item-label" }), "姓名"),
          createElementVNode("input", utsMapOf({
            class: "form-item-input",
            placeholder: "姓名",
            name: "username"
          }))
        ]),
        createElementVNode("view", utsMapOf({ class: "form-item" }), [
          createElementVNode("text", utsMapOf({ class: "form-item-label" }), "电话"),
          createElementVNode("input", utsMapOf({
            class: "form-item-input",
            placeholder: "电话",
            name: "mobile"
          }))
        ]),
        createElementVNode("view", utsMapOf({ class: "form-item" }), [
          createElementVNode("text", utsMapOf({ class: "form-item-label" }), "邮箱"),
          createElementVNode("input", utsMapOf({
            class: "form-item-input",
            placeholder: "邮箱地址",
            name: "email"
          }))
        ]),
        createElementVNode("view", utsMapOf({ class: "form-item" }), [
          createElementVNode("text", utsMapOf({ class: "form-item-label" }), "备注"),
          createElementVNode("textarea", utsMapOf({
            class: "form-item-input-comment",
            placeholder: "备注",
            name: "comment",
            maxlength: "-1"
          }))
        ]),
        createElementVNode("view", utsMapOf({ class: "form-item" }), [
          createElementVNode("text", utsMapOf({ class: "form-item-label" }), "性别"),
          createVNode(_component_radio_group, utsMapOf({
            class: "radio-list",
            name: "gender"
          }), utsMapOf({
            default: withSlotCtx((): any[] => [
              createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.genderList, (item, _, __index, _cached): any => {
                return createElementVNode("view", utsMapOf({
                  class: "radio-item",
                  key: item.value
                }), [
                  createVNode(_component_radio, utsMapOf({
                    value: item.value
                  }), null, 8 /* PROPS */, ["value"]),
                  createElementVNode("text", null, toDisplayString(item.text), 1 /* TEXT */)
                ])
              }), 128 /* KEYED_FRAGMENT */)
            ]),
            _: 1 /* STABLE */
          }))
        ]),
        createElementVNode("button", utsMapOf({
          class: "btn-add",
          type: "primary",
          "form-type": "submit"
        }), "保存")
      ]),
      _: 1 /* STABLE */
    }), 8 /* PROPS */, ["onSubmit"])
  ])
}
const GenPagesComponentUnicloudDbUnicloudDbContactsAddStyles = [utsMapOf([["page", padStyleMapOf(utsMapOf([["paddingTop", 15], ["paddingRight", 15], ["paddingBottom", 15], ["paddingLeft", 15]]))], ["form-item", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["marginBottom", 15], ["alignItems", "center"]]))], ["form-item-label", padStyleMapOf(utsMapOf([["width", 45], ["marginRight", 10]]))], ["form-item-input", padStyleMapOf(utsMapOf([["flex", 1], ["fontSize", 14], ["color", "#666666"], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#e5e5e5"], ["borderRightColor", "#e5e5e5"], ["borderBottomColor", "#e5e5e5"], ["borderLeftColor", "#e5e5e5"], ["borderTopLeftRadius", 5], ["borderTopRightRadius", 5], ["borderBottomRightRadius", 5], ["borderBottomLeftRadius", 5], ["height", 34], ["paddingLeft", 8], ["paddingRight", 8]]))], ["form-item-input-comment", padStyleMapOf(utsMapOf([["flex", 1], ["fontSize", 14], ["color", "#666666"], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#e5e5e5"], ["borderRightColor", "#e5e5e5"], ["borderBottomColor", "#e5e5e5"], ["borderLeftColor", "#e5e5e5"], ["borderTopLeftRadius", 5], ["borderTopRightRadius", 5], ["borderBottomRightRadius", 5], ["borderBottomLeftRadius", 5], ["paddingTop", 8], ["paddingRight", 8], ["paddingBottom", 8], ["paddingLeft", 8]]))], ["radio-list", padStyleMapOf(utsMapOf([["flexDirection", "row"]]))], ["radio-item", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["marginRight", 30], ["alignItems", "center"]]))], ["btn-add", padStyleMapOf(utsMapOf([["marginTop", 50]]))]])]

import _easycom_unicloud_db from '@/node-modules/@dcloudio/uni-components/lib-x/unicloud-db/unicloud-db.vue'
