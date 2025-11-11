
  const __sfc__ = defineComponent({
    data() {
      return {
        move: false,
        posX: 0,
        posY: 0,
        lastX: 0,
        lastY: 0,
        touchEvent: null as TouchEvent | null,
        icon: null as UniElement | null,
        touchTargets: "",
        touchTargetsCount: 0,
        iconRect : null as DOMRect | null
      }
    },
    onReady() {
      this.icon = uni.getElementById("icon")





    },
    methods: {
      onViewTouchStart(e : TouchEvent) {
        this.touchTargets += e.target!.tagName + e.currentTarget!.tagName
        this.touchTargetsCount++
      },
      onTouchStart(e : TouchEvent) {
        this.touchTargetsCount++
        this.touchTargets += e.target!.tagName + e.currentTarget!.tagName

        this.touchEvent = e
        if (!this.move) {
          this.move = true
          this.posX = e.touches[0].screenX
          this.posY = e.touches[0].screenY
        }
      },
      onTouchMove(e : TouchEvent) {
        e.preventDefault()
        this.touchEvent = e
        let p = e.touches[0]
        if (p.screenX == this.lastX && p.screenY == this.lastY) {
          return
        }
        let x = p.screenX - this.posX
        let y = p.screenY - this.posY
        this.lastX = p.screenX
        this.lastY = p.screenY
        this.icon?.style?.setProperty('transform', 'translate(' + x + 'px,' + y + 'px)')
      },
      onTouchEnd(e : TouchEvent) {
        if (e.touches.length == 0) {
          this.resetIcon()
          this.touchEvent = null
        }
      },
      onTouchCancel(_ : TouchEvent) {
        this.resetIcon()
        this.touchEvent = null
      },
      resetIcon() {
        this.move = false;
        this.posX = 0;
        this.posY = 0;
        this.icon?.style?.setProperty('transform', 'translate(0px,0px)')
      }
    }
  })

export default __sfc__
function GenPagesComponentGlobalEventsTouchEventsRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)
const _component_navigator = resolveComponent("navigator")

  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createVNode(_component_page_head, utsMapOf({ title: "拖拽图标测试相关事件(在小程序上本示例会卡顿,小程序上应使用movable-view)" })),
    createElementVNode("view", utsMapOf({
      class: "uni-padding-wrap uni-common-mt",
      style: normalizeStyle(utsMapOf({"bottom":"20px"}))
    }), [
      createVNode(_component_navigator, utsMapOf({
        url: "/pages/component/global-events/touch-events-case",
        "hover-class": "none"
      }), utsMapOf({
        default: withSlotCtx((): any[] => [
          createElementVNode("button", utsMapOf({ type: "default" }), " stopPropagation / preventDefault ")
        ]),
        _: 1 /* STABLE */
      }))
    ], 4 /* STYLE */),
    createElementVNode("view", utsMapOf({ class: "container" }), [
      createElementVNode("view", utsMapOf({
        class: "view-box",
        onTouchstart: _ctx.onViewTouchStart
      }), [
        createElementVNode("image", utsMapOf({
          class: "icon",
          id: "icon",
          src: _imports_0,
          onTouchstart: _ctx.onTouchStart,
          onTouchcancel: _ctx.onTouchCancel,
          onTouchmove: _ctx.onTouchMove,
          onTouchend: _ctx.onTouchEnd
        }), null, 40 /* PROPS, NEED_HYDRATION */, ["onTouchstart", "onTouchcancel", "onTouchmove", "onTouchend"])
      ], 40 /* PROPS, NEED_HYDRATION */, ["onTouchstart"])
    ]),
    _ctx.touchEvent !== null
      ? createElementVNode("view", utsMapOf({ key: 0 }), [
          createElementVNode("text", utsMapOf({ class: "title1" }), "touches: "),
          createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.touchEvent!.touches, (touch, index, __index, _cached): any => {
            return createElementVNode(Fragment, utsMapOf({ key: index }), [
              createElementVNode("text", utsMapOf({ class: "title2" }), "touch[" + toDisplayString(index) + "]:", 1 /* TEXT */),
              createElementVNode("text", null, "identifier: " + toDisplayString(touch.identifier), 1 /* TEXT */),
              createElementVNode("text", null, "pageX: " + toDisplayString(touch.pageX) + ", pageY: " + toDisplayString(touch.pageY), 1 /* TEXT */),
              createElementVNode("text", null, "clientX: " + toDisplayString(touch.clientX) + ", clientY: " + toDisplayString(touch.clientY), 1 /* TEXT */),
              createElementVNode("text", null, "screenX: " + toDisplayString(touch.screenX) + ", screenY: " + toDisplayString(touch.screenY), 1 /* TEXT */)
            ], 64 /* STABLE_FRAGMENT */)
          }), 128 /* KEYED_FRAGMENT */)
        ])
      : createCommentVNode("v-if", true)
  ], 4 /* STYLE */)
}
const GenPagesComponentGlobalEventsTouchEventsStyles = [utsMapOf([["container", padStyleMapOf(utsMapOf([["width", "100%"], ["flexDirection", "column"], ["alignItems", "center"]]))], ["view-box", padStyleMapOf(utsMapOf([["width", 300], ["height", 300], ["alignItems", "center"], ["justifyContent", "center"], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"]]))], ["icon", padStyleMapOf(utsMapOf([["width", 100], ["height", 100]]))], ["title1", padStyleMapOf(utsMapOf([["marginTop", 10], ["fontSize", 18]]))], ["title2", padStyleMapOf(utsMapOf([["marginTop", 5], ["fontSize", 16]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
import _imports_0 from '../image/logo.png'
