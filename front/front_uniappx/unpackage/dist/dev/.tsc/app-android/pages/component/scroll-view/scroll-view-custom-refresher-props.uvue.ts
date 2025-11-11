
  import refreshBox from './refresh-box/refresh-box.uvue';
  const __sfc__ = defineComponent({
    components: { refreshBox },
    data() {
      return {
        listCount : 20,
        refresherTriggered: false,
        pullingDistance: 0,
        resetting: false,
        triggered: false
      }
    },
    computed: {
      state() : number {
        if (this.resetting) {
          return 3;
        }
        if (this.refresherTriggered) {
          return 2
        }
        if (this.pullingDistance > 45) {
          return 1
        } else {
          return 0;
        }
      }
    },
    methods: {
      onRefresherpulling(e : RefresherEvent) {
        this.pullingDistance = e.detail.dy;
        this.triggered = true;
      },
      onRefresherrefresh() {
        this.refresherTriggered = true
        setTimeout(() => {
          this.refresherTriggered = false
          this.resetting = true;
        }, 1500)
      },
      onRefreshrestore() {
        this.pullingDistance = 0
        this.resetting = false;
      }
    }
  })

export default __sfc__
function GenPagesComponentScrollViewScrollViewCustomRefresherPropsRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)
const _component_refresh_box = resolveComponent("refresh-box")

  return createElementVNode("view", utsMapOf({
    style: normalizeStyle(utsMapOf({"overflow":"visible","flex":"1"}))
  }), [
    createVNode(_component_page_head, utsMapOf({ title: "list-view 下拉刷新" })),
    createElementVNode("scroll-view", utsMapOf({
      style: normalizeStyle(utsMapOf({"flex":"1"})),
      "refresher-enabled": true,
      "refresher-triggered": _ctx.refresherTriggered,
      "refresher-default-style": "none",
      onRefresherpulling: _ctx.onRefresherpulling,
      onRefresherrefresh: _ctx.onRefresherrefresh,
      onRefresherrestore: _ctx.onRefreshrestore,
      "refresher-max-drag-distance": "200px"
    }), [
      createElementVNode("view", utsMapOf({
        style: normalizeStyle(utsMapOf({"height":"25px"}))
      }), null, 4 /* STYLE */),
      createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.listCount, (i, __key, __index, _cached): any => {
        return createElementVNode("view", utsMapOf({ class: "content-item" }), [
          createElementVNode("text", utsMapOf({ class: "text" }), "item-" + toDisplayString(i), 1 /* TEXT */)
        ])
      }), 256 /* UNKEYED_FRAGMENT */),
      createVNode(_component_refresh_box, utsMapOf({
        slot: "refresher",
        state: _ctx.state
      }), null, 8 /* PROPS */, ["state"])
    ], 44 /* STYLE, PROPS, NEED_HYDRATION */, ["refresher-triggered", "onRefresherpulling", "onRefresherrefresh", "onRefresherrestore"])
  ], 4 /* STYLE */)
}
const GenPagesComponentScrollViewScrollViewCustomRefresherPropsStyles = [utsMapOf([["content-item", padStyleMapOf(utsMapOf([["paddingTop", 15], ["paddingRight", 15], ["paddingBottom", 15], ["paddingLeft", 15], ["marginTop", 5], ["marginRight", 0], ["marginBottom", 5], ["marginLeft", 0], ["backgroundColor", "#ffffff"], ["borderTopLeftRadius", 5], ["borderTopRightRadius", 5], ["borderBottomRightRadius", 5], ["borderBottomLeftRadius", 5]]))], ["text", padStyleMapOf(utsMapOf([["fontSize", 14], ["color", "#666666"], ["lineHeight", "20px"]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
