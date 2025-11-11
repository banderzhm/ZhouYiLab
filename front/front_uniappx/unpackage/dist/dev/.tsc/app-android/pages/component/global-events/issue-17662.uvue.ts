
  const __sfc__ = defineComponent({
    data() {
      return {
        longpressItem: false,
        longpressText: false,
        isStopPropagation: false,
        scrollTop: 0
      };
    },
    methods: {
      longpress_item(_e : UniTouchEvent) {
        console.log("longpress listItem", " at pages/component/global-events/issue-17662.uvue:25")
        this.longpressItem = true
      },
      longpress_text(e : UniTouchEvent) {
        console.log("longpress text", " at pages/component/global-events/issue-17662.uvue:29")
        this.longpressText = true
        if (this.isStopPropagation) {
          e.stopPropagation()
        }
      }
    },
  });

export default __sfc__
function GenPagesComponentGlobalEventsIssue17662Render(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("list-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"})),
    "scroll-top": _ctx.scrollTop
  }), [
    createElementVNode(Fragment, null, RenderHelpers.renderList(50, (item, __key, __index, _cached): any => {
      return createElementVNode("list-item", utsMapOf({
        key: item,
        onLongpress: _ctx.longpress_item,
        style: normalizeStyle(utsMapOf({"border-bottom-style":"solid","border-bottom-width":"1px","border-bottom-color":"black"}))
      }), [
        createElementVNode("view", utsMapOf({
          style: normalizeStyle(utsMapOf({"width":"100%","height":"40px","background-color":"antiquewhite"}))
        }), [
          createElementVNode("text", utsMapOf({
            onLongpress: _ctx.longpress_text,
            style: normalizeStyle(utsMapOf({"font-size":"10px","width":"100px","height":"100%","background-color":"red"}))
          }), toDisplayString(item), 45 /* TEXT, STYLE, PROPS, NEED_HYDRATION */, ["onLongpress"])
        ], 4 /* STYLE */)
      ], 44 /* STYLE, PROPS, NEED_HYDRATION */, ["onLongpress"])
    }), 64 /* STABLE_FRAGMENT */)
  ], 12 /* STYLE, PROPS */, ["scroll-top"])
}
const GenPagesComponentGlobalEventsIssue17662Styles = []
