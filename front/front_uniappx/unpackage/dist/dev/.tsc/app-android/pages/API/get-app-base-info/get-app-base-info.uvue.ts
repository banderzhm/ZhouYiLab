
  type Item = { __$originalPosition?: UTSSourceMapPosition<"Item", "pages/API/get-app-base-info/get-app-base-info.uvue", 22, 8>;
    label : string,
    value : string,
  }
  const __sfc__ = defineComponent({
    data() {
      return {
        title: 'getAppBaseInfo',
        items: [] as Item[],
      }
    },
    onUnload: function () {
    },
    methods: {
      getAppBaseInfo: function () {
        const res = uni.getAppBaseInfo();
        const res_str = JSON.stringify(res);
        const res_obj = UTSAndroid.consoleDebugError(JSON.parseObject(res_str), " at pages/API/get-app-base-info/get-app-base-info.uvue:39");
        const res_map = res_obj!.toMap();
        let keys = [] as string[]
        res_map.forEach((_, key) => {
          keys.push(key);
        });

        this.items = [] as Item[];
        keys.sort().forEach(key => {
          const value = res[key];
          if (value != null) {
            const item = {
              label: key,
              value: "" + ((typeof value == "object") ? JSON.stringify(value) : value)
            } as Item;
            this.items.push(item);
          }
        });

      }
    }
  })

export default __sfc__
function GenPagesAPIGetAppBaseInfoGetAppBaseInfoRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode(Fragment, null, [
    createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
    createElementVNode("view", utsMapOf({ class: "uni-common-mt" }), [
      createElementVNode("view", utsMapOf({ class: "uni-list" }), [
        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.items, (item, _, __index, _cached): any => {
          return createElementVNode("view", utsMapOf({
            class: "uni-list-cell",
            style: normalizeStyle(utsMapOf({"align-items":"center"}))
          }), [
            createElementVNode("view", utsMapOf({ class: "uni-pd" }), [
              createElementVNode("view", utsMapOf({
                class: "uni-label",
                style: normalizeStyle(utsMapOf({"width":"180px"}))
              }), toDisplayString(item.label), 5 /* TEXT, STYLE */)
            ]),
            createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), [
              createElementVNode("text", utsMapOf({ class: "uni-list-cell-db-text" }), toDisplayString(item.value == '' ? '未获取' : item.value), 1 /* TEXT */)
            ])
          ], 4 /* STYLE */)
        }), 256 /* UNKEYED_FRAGMENT */)
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
        createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
          createElementVNode("button", utsMapOf({
            type: "primary",
            onClick: _ctx.getAppBaseInfo
          }), "获取App基础信息", 8 /* PROPS */, ["onClick"])
        ])
      ])
    ])
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesAPIGetAppBaseInfoGetAppBaseInfoStyles = [utsMapOf([["uni-pd", padStyleMapOf(utsMapOf([["paddingLeft", 15]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
