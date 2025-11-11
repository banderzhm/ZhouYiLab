
  import refreshBox from './refresh-box/refresh-box.uvue';
  const __sfc__ = defineComponent({
    components: { refreshBox },
    data() {
      return {
        refresherTriggered: false,
        refresherThreshold: 40,
        pullingDistance: 0,
        resetting: false
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
        if (this.pullingDistance > this.refresherThreshold) {
          return 1
        } else {
          return 0
        }
      }
    },
    methods: {
      onRefresherpulling(e : RefresherEvent) {
        // console.log('onRefresherpulling',e.detail.dy)
        this.pullingDistance = e.detail.dy
      },
      onRefresherrefresh() {
        this.refresherTriggered = true
        setTimeout(() => {
          this.refresherTriggered = false
          this.resetting = true
        }, 1500)
      },
      onRefreshrestore() {
        this.pullingDistance = 0
        this.resetting = false;
      }
    }
  })

export default __sfc__
function GenPagesTemplateCustomRefresherCustomRefresherRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_refresh_box = resolveComponent("refresh-box")

  return createElementVNode("list-view", utsMapOf({
    class: "list-view",
    "refresher-enabled": true,
    "refresher-triggered": _ctx.refresherTriggered,
    "refresher-default-style": "none",
    onRefresherpulling: _ctx.onRefresherpulling,
    onRefresherrefresh: _ctx.onRefresherrefresh,
    onRefresherrestore: _ctx.onRefreshrestore,
    "refresher-threshold": _ctx.refresherThreshold,
    "refresher-max-drag-distance": "200px"
  }), [
    createElementVNode("sticky-header", null, [
      createElementVNode("view", utsMapOf({ class: "header" }), [
        createElementVNode("text", null, "sticky header")
      ])
    ]),
    createElementVNode(Fragment, null, RenderHelpers.renderList(20, (i, __key, __index, _cached): any => {
      return createElementVNode("list-item", utsMapOf({ class: "content-item" }), [
        createElementVNode("text", utsMapOf({ class: "text" }), "item-" + toDisplayString(i), 1 /* TEXT */)
      ])
    }), 64 /* STABLE_FRAGMENT */),
    createVNode(_component_refresh_box, utsMapOf({
      slot: "refresher",
      state: _ctx.state,
      pullingDistance: _ctx.pullingDistance
    }), null, 8 /* PROPS */, ["state", "pullingDistance"])
  ], 40 /* PROPS, NEED_HYDRATION */, ["refresher-triggered", "onRefresherpulling", "onRefresherrefresh", "onRefresherrestore", "refresher-threshold"])
}
const GenPagesTemplateCustomRefresherCustomRefresherStyles = [utsMapOf([["list-view", padStyleMapOf(utsMapOf([["flex", 1], ["backgroundColor", "#f5f5f5"]]))], ["header", padStyleMapOf(utsMapOf([["justifyContent", "center"], ["height", 50], ["backgroundColor", "#f5f5f5"], ["paddingTop", 15], ["paddingRight", 15], ["paddingBottom", 15], ["paddingLeft", 15]]))], ["content-item", padStyleMapOf(utsMapOf([["paddingTop", 15], ["paddingRight", 15], ["paddingBottom", 15], ["paddingLeft", 15], ["marginTop", 5], ["marginRight", 0], ["marginBottom", 5], ["marginLeft", 0], ["backgroundColor", "#ffffff"], ["borderTopLeftRadius", 5], ["borderTopRightRadius", 5], ["borderBottomRightRadius", 5], ["borderBottomLeftRadius", 5]]))], ["text", padStyleMapOf(utsMapOf([["fontSize", 14], ["color", "#666666"], ["lineHeight", "20px"]]))]])]
