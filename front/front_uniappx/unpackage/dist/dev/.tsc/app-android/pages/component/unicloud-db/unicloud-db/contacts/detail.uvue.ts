
  import { COLLECTION_NAME, GenderType, GenderList, UNICLOUD_DB_CONTACTS_UPDATE } from './types.uts'

  const __sfc__ = defineComponent({
    data() {
      return {
        collection: '',
        where: '',
        whereID: '',
        uniCloudElement: null as UniCloudDBElement | null
      }
    },
    onLoad(options) {
      this.collection = COLLECTION_NAME;
      this.whereID = options['id'] as string;
      this.where = `_id=='${this.whereID}'`;
    },
    onShow() {
      // TODO 后续通过 EventChannel 实现
      uni.$off(UNICLOUD_DB_CONTACTS_UPDATE, this.onDataChange);
    },
    onUnload() {
      // TODO 后续通过 EventChannel 实现
      uni.$off(UNICLOUD_DB_CONTACTS_UPDATE, this.onDataChange);
    },
    onReady() {
      this.uniCloudElement = this.$refs['udb'] as UniCloudDBElement
    },
    methods: {
      displayGender(value : number | null) : string {
        const str = value ?? -1
        return (GenderList as GenderType[]).find((item : GenderType) : boolean => {
          return item.value == str
        })!.text;
      },
      gotoUpdatePage(id : string | null) {
        // TODO 后续通过 EventChannel 实现
        uni.$on(UNICLOUD_DB_CONTACTS_UPDATE, this.onDataChange);
        uni.navigateTo({
          url: './edit?id=' + id
        })
      },
      onDataChange(_ : string) {
        this.uniCloudElement!.loadData()
      }
    }
  })

export default __sfc__
function GenPagesComponentUnicloudDbUnicloudDbContactsDetailRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_unicloud_db = resolveEasyComponent("unicloud-db",_easycom_unicloud_db)

  return createElementVNode("scroll-view", utsMapOf({ class: "page" }), [
    createVNode(_component_unicloud_db, utsMapOf({
      ref: "udb",
      collection: _ctx.collection,
      where: _ctx.where,
      "page-data": "replace"
    }), utsMapOf({
      default: withScopedSlotCtx((slotProps: Record<string, any | null>): any[] => {
      const data = slotProps["data"]
      const loading = slotProps["loading"]
      const error = slotProps["error"]
      return [
        error!=null
          ? createElementVNode("view", utsMapOf({
              key: 0,
              class: "error"
            }), toDisplayString(error.errMsg), 1 /* TEXT */)
          : createCommentVNode("v-if", true),
        isTrue(loading)
          ? createElementVNode("view", utsMapOf({
              key: 1,
              class: "loading"
            }), "正在加载...")
          : createCommentVNode("v-if", true),
        data.length>0
          ? createElementVNode("view", utsMapOf({ key: 2 }), [
              createElementVNode("view", utsMapOf({ class: "form-item" }), [
                createElementVNode("text", utsMapOf({ class: "form-item-label" }), "姓名"),
                createElementVNode("text", utsMapOf({ class: "form-item-input" }), toDisplayString(data[0]['username']), 1 /* TEXT */)
              ]),
              createElementVNode("view", utsMapOf({ class: "form-item" }), [
                createElementVNode("text", utsMapOf({ class: "form-item-label" }), "电话"),
                createElementVNode("text", utsMapOf({ class: "form-item-input" }), toDisplayString(data[0]['mobile']), 1 /* TEXT */)
              ]),
              createElementVNode("view", utsMapOf({ class: "form-item" }), [
                createElementVNode("text", utsMapOf({ class: "form-item-label" }), "邮箱"),
                createElementVNode("text", utsMapOf({ class: "form-item-input" }), toDisplayString(data[0]['email']), 1 /* TEXT */)
              ]),
              createElementVNode("view", utsMapOf({ class: "form-item" }), [
                createElementVNode("text", utsMapOf({ class: "form-item-label" }), "备注"),
                createElementVNode("text", utsMapOf({ class: "form-item-input" }), toDisplayString(data[0]['comment']), 1 /* TEXT */)
              ]),
              createElementVNode("view", utsMapOf({ class: "form-item" }), [
                createElementVNode("text", utsMapOf({ class: "form-item-label" }), "性别"),
                createElementVNode("text", utsMapOf({ class: "form-item-input" }), toDisplayString(_ctx.displayGender(data[0].getNumber('gender'))), 1 /* TEXT */)
              ]),
              createElementVNode("button", utsMapOf({
                type: "default",
                onClick: () => {_ctx.gotoUpdatePage(data[0].getString('_id'))}
              }), "编辑", 8 /* PROPS */, ["onClick"])
            ])
          : createCommentVNode("v-if", true)
      ]}),
      _: 1 /* STABLE */
    }), 8 /* PROPS */, ["collection", "where"])
  ])
}
const GenPagesComponentUnicloudDbUnicloudDbContactsDetailStyles = [utsMapOf([["page", padStyleMapOf(utsMapOf([["flex", 1], ["paddingTop", 15], ["paddingRight", 15], ["paddingBottom", 15], ["paddingLeft", 15]]))], ["loading", padStyleMapOf(utsMapOf([["alignItems", "center"]]))], ["form-item", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["marginBottom", 15], ["alignItems", "center"], ["paddingTop", 8], ["paddingRight", 0], ["paddingBottom", 8], ["paddingLeft", 0]]))], ["form-item-label", padStyleMapOf(utsMapOf([["width", 45], ["marginRight", 10]]))], ["form-item-input", padStyleMapOf(utsMapOf([["flex", 1], ["fontSize", 14], ["color", "#666666"]]))]])]

import _easycom_unicloud_db from '@/node-modules/@dcloudio/uni-components/lib-x/unicloud-db/unicloud-db.vue'
