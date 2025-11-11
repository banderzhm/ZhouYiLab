
  import { COLLECTION_NAME, UNICLOUD_DB_CONTACTS_ADD, UNICLOUD_DB_CONTACTS_UPDATE, UNICLOUD_DB_CONTACTS_DELETE } from './types.uts'

  const __sfc__ = defineComponent({
    data() {
      return {
        collection: COLLECTION_NAME,
        uniCloudElement: null as UniCloudDBElement | null
      }
    },
    onReady() {
      // TODO 后续通过 EventChannel 实现
      uni.$on(UNICLOUD_DB_CONTACTS_DELETE, this.onDataChange);
      this.uniCloudElement = this.$refs['udb'] as UniCloudDBElement
      this.uniCloudElement!.loadData()
    },
    onUnload() {
      // TODO 后续通过 EventChannel 实现
      uni.$off(UNICLOUD_DB_CONTACTS_ADD, this.onDataChange);
      uni.$off(UNICLOUD_DB_CONTACTS_UPDATE, this.onDataChange);
      uni.$off(UNICLOUD_DB_CONTACTS_DELETE, this.onDataChange);
    },
    onShow() {
      // TODO 后续通过 EventChannel 实现
      uni.$off(UNICLOUD_DB_CONTACTS_ADD, this.onDataChange);
      uni.$off(UNICLOUD_DB_CONTACTS_UPDATE, this.onDataChange);
    },
    onPullDownRefresh() {
      this.uniCloudElement!.loadData({
        clear: true,
        success: (_ : UniCloudDBGetResult) => {
          uni.stopPullDownRefresh()
        }
      })
    },
    methods: {
      loadMore() {
        this.uniCloudElement!.loadMore()
      },
      gotoAddPage() {
        // TODO 后续通过 EventChannel 实现
        uni.$on(UNICLOUD_DB_CONTACTS_ADD, this.onDataChange);
        uni.navigateTo({
          url: './add'
        })
      },
      gotoDetailPage(id : string) {
        // TODO 后续通过 EventChannel 实现
        uni.$on(UNICLOUD_DB_CONTACTS_UPDATE, this.onDataChange);
        uni.navigateTo({
          url: './detail?id=' + id
        })
      },
      onDataChange(_ : string) {
        this.uniCloudElement!.loadData({
          clear: true
        })
      }
    }
  })

export default __sfc__
function GenPagesComponentUnicloudDbUnicloudDbContactsListRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_uni_loading = resolveEasyComponent("uni-loading",_easycom_uni_loading)
const _component_unicloud_db = resolveEasyComponent("unicloud-db",_easycom_unicloud_db)

  return createElementVNode("view", utsMapOf({ class: "page" }), [
    createVNode(_component_unicloud_db, utsMapOf({
      ref: "udb",
      class: "udb",
      collection: _ctx.collection,
      "page-size": 15,
      getcount: true,
      loadtime: "manual"
    }), utsMapOf({
      default: withScopedSlotCtx((slotProps: Record<string, any | null>): any[] => {
      const data = slotProps["data"]
      const pagination = slotProps["pagination"]
      const loading = slotProps["loading"]
      const hasMore = slotProps["hasMore"]
      const error = slotProps["error"]
      return [
        error!=null
          ? createElementVNode("view", utsMapOf({
              key: 0,
              class: "error"
            }), toDisplayString(error.errMsg), 1 /* TEXT */)
          : createCommentVNode("v-if", true),
        createElementVNode("scroll-view", utsMapOf({
          ref: "listView",
          class: "list-view",
          onScrolltolower: () => {_ctx.loadMore()}
        }), [
          createElementVNode(Fragment, null, RenderHelpers.renderList(data, (item, _, __index, _cached): any => {
            return createElementVNode("view", utsMapOf({
              class: "list-item",
              onClick: () => {_ctx.gotoDetailPage(item['_id'] as string)}
            }), [
              createElementVNode("view", utsMapOf({ class: "list-item-fill" }), [
                createElementVNode("text", utsMapOf({ class: "name" }), toDisplayString(item['username']), 1 /* TEXT */),
                createElementVNode("text", utsMapOf({ class: "mobile" }), toDisplayString(item['mobile']), 1 /* TEXT */)
              ])
            ], 8 /* PROPS */, ["onClick"])
          }), 256 /* UNKEYED_FRAGMENT */)
        ], 40 /* PROPS, NEED_HYDRATION */, ["onScrolltolower"]),
        isTrue(loading)
          ? createVNode(_component_uni_loading, utsMapOf({
              key: 1,
              class: "loading",
              color: "#999",
              text: "正在加载..."
            }))
          : createCommentVNode("v-if", true),
        data.length>0
          ? createElementVNode("view", utsMapOf({
              key: 2,
              class: "pagination"
            }), [
              createElementVNode("text", utsMapOf({ class: "pagination-item" }), toDisplayString(data.length) + " / " + toDisplayString(pagination.count), 1 /* TEXT */)
            ])
          : createCommentVNode("v-if", true)
      ]}),
      _: 1 /* STABLE */
    }), 8 /* PROPS */, ["collection"]),
    createElementVNode("view", utsMapOf({
      class: "btn-plus",
      onClick: _ctx.gotoAddPage
    }), [
      createElementVNode("text", utsMapOf({ class: "btn-plus-text" }), "+")
    ], 8 /* PROPS */, ["onClick"])
  ])
}
const GenPagesComponentUnicloudDbUnicloudDbContactsListStyles = [utsMapOf([["page", padStyleMapOf(utsMapOf([["flex", 1], ["flexDirection", "column"]]))], ["loading", padStyleMapOf(utsMapOf([["marginTop", 20], ["marginRight", 20], ["marginBottom", 20], ["marginLeft", 20], ["alignSelf", "center"]]))], ["udb", padStyleMapOf(utsMapOf([["flex", 1], ["flexDirection", "column"]]))], ["list-view", padStyleMapOf(utsMapOf([["flex", 1], ["flexDirection", "column"]]))], ["list-item", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["paddingTop", 10], ["paddingRight", 10], ["paddingBottom", 10], ["paddingLeft", 10], ["backgroundColor", "#ffffff"], ["marginBottom", 1]]))], ["mobile", padStyleMapOf(utsMapOf([["marginTop", 5]]))], ["btn-plus", padStyleMapOf(utsMapOf([["position", "absolute"], ["width", 64], ["height", 64], ["right", 20], ["bottom", 20], ["alignItems", "center"], ["justifyContent", "center"], ["backgroundColor", "#1e90ff"], ["borderTopLeftRadius", 64], ["borderTopRightRadius", 64], ["borderBottomRightRadius", 64], ["borderBottomLeftRadius", 64]]))], ["btn-plus-text", padStyleMapOf(utsMapOf([["fontSize", 30], ["color", "#ffffff"]]))], ["pagination", padStyleMapOf(utsMapOf([["alignItems", "center"], ["backgroundColor", "#f8f8f8"], ["paddingTop", 15], ["paddingRight", 15], ["paddingBottom", 15], ["paddingLeft", 15]]))]])]

import _easycom_uni_loading from '@/uni_modules/uni-loading/components/uni-loading/uni-loading.uvue'
import _easycom_unicloud_db from '@/node-modules/@dcloudio/uni-components/lib-x/unicloud-db/unicloud-db.vue'
