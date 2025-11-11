
  const __sfc__ = defineComponent({
    data() {
      return {
        title: 'global-events',
        touchStartEvent: null as TouchEvent | null,
        touchCancelEvent: null as TouchEvent | null,
        touchMoveEvent: null as TouchEvent | null,
        longPressEvent: null as TouchEvent | null,
        touchEndEvent: null as TouchEvent | null,
        tapEvent: null as PointerEvent | null,
        clickEvent: null as PointerEvent | null,
      }
    },
    methods: {
      onTouchStart(e : TouchEvent) {
        this.touchStartEvent = e
        console.log('onTouchStart', e, " at pages/component/global-events/global-events.uvue:449")
      },
      onTouchCancel(e : TouchEvent) {
        this.touchCancelEvent = e
        console.log('onTouchCancel', " at pages/component/global-events/global-events.uvue:453")
      },
      onTouchMove(e : TouchEvent) {
        this.touchMoveEvent = e
        console.log('onTouchMove', e, " at pages/component/global-events/global-events.uvue:457")
      },
      onLongPress(e : TouchEvent) {
        this.longPressEvent = e
        console.log('onLongPress', e, " at pages/component/global-events/global-events.uvue:461")
      },
      onTouchEnd(e : TouchEvent) {
        this.touchEndEvent = e
        console.log('onTouchEnd', e, " at pages/component/global-events/global-events.uvue:465")
      },
      onTap(e : PointerEvent) {
        this.tapEvent = e
        console.log('onTap', e, " at pages/component/global-events/global-events.uvue:469")
      },
      onClick(e : PointerEvent) {
        this.clickEvent = e
        console.log('onClick', e, " at pages/component/global-events/global-events.uvue:473")
      }
    },
  })

export default __sfc__
function GenPagesComponentGlobalEventsGlobalEventsRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createVNode(_component_page_head, utsMapOf({ title: "触摸方块测试相关事件" })),
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap uni-common-mt container" }), [
      createElementVNode("view", utsMapOf({
        class: "target",
        id: "touch-target",
        onTouchstart: _ctx.onTouchStart,
        onTouchcancel: _ctx.onTouchCancel,
        onTouchmove: _ctx.onTouchMove,
        onTouchend: _ctx.onTouchEnd
      }), null, 40 /* PROPS, NEED_HYDRATION */, ["onTouchstart", "onTouchcancel", "onTouchmove", "onTouchend"]),
      createElementVNode("view", utsMapOf({
        class: "target",
        id: "longpress-target",
        onClick: [_ctx.onTap, _ctx.onClick],
        onLongpress: _ctx.onLongPress
      }), null, 40 /* PROPS, NEED_HYDRATION */, ["onClick", "onLongpress"]),
      _ctx.touchStartEvent !== null
        ? createElementVNode("view", utsMapOf({ key: 0 }), [
            createElementVNode("text", utsMapOf({ class: "title1" }), "touchStart Event: "),
            createElementVNode("text", utsMapOf({ class: "title2" }), "touches: "),
            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.touchStartEvent!.touches, (touch, index, __index, _cached): any => {
              return createElementVNode(Fragment, utsMapOf({ key: index }), [
                createElementVNode("text", utsMapOf({ class: "title3" }), "touch[" + toDisplayString(index) + "]:", 1 /* TEXT */),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "identifier: "),
                  createElementVNode("text", utsMapOf({ id: "touch-start-touch-identifier" }), toDisplayString(touch.identifier), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "pageX: "),
                  createElementVNode("text", utsMapOf({ id: "touch-start-touch-page-x" }), toDisplayString(touch.pageX), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "pageY: "),
                  createElementVNode("text", utsMapOf({ id: "touch-start-touch-page-y" }), toDisplayString(touch.pageY), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "clientX: "),
                  createElementVNode("text", utsMapOf({ id: "touch-start-touch-client-x" }), toDisplayString(touch.clientX), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "clientY: "),
                  createElementVNode("text", utsMapOf({ id: "touch-start-touch-client-y" }), toDisplayString(touch.clientY), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "screenX: "),
                  createElementVNode("text", utsMapOf({ id: "touch-start-touch-screen-x" }), toDisplayString(touch.screenX), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "screenY: "),
                  createElementVNode("text", utsMapOf({ id: "touch-start-touch-screen-y" }), toDisplayString(touch.screenY), 1 /* TEXT */)
                ])
              ], 64 /* STABLE_FRAGMENT */)
            }), 128 /* KEYED_FRAGMENT */),
            createElementVNode("text", utsMapOf({ class: "title2 uni-common-mt" }), "changedTouches: "),
            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.touchStartEvent!.changedTouches, (touch, index, __index, _cached): any => {
              return createElementVNode(Fragment, utsMapOf({ key: index }), [
                createElementVNode("text", utsMapOf({ class: "title3" }), "touch[" + toDisplayString(index) + "]:", 1 /* TEXT */),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "identifier: "),
                  createElementVNode("text", utsMapOf({ id: "touch-start-changed-touch-identifier" }), toDisplayString(touch.identifier), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "pageX: "),
                  createElementVNode("text", utsMapOf({ id: "touch-start-changed-touch-page-x" }), toDisplayString(touch.pageX), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "pageY: "),
                  createElementVNode("text", utsMapOf({ id: "touch-start-changed-touch-page-y" }), toDisplayString(touch.pageY), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "clientX: "),
                  createElementVNode("text", utsMapOf({ id: "touch-start-changed-touch-client-x" }), toDisplayString(touch.clientX), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "clientY: "),
                  createElementVNode("text", utsMapOf({ id: "touch-start-changed-touch-client-y" }), toDisplayString(touch.clientY), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "screenX: "),
                  createElementVNode("text", utsMapOf({ id: "touch-start-changed-touch-screen-x" }), toDisplayString(touch.screenX), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "screenY: "),
                  createElementVNode("text", utsMapOf({ id: "touch-start-changed-touch-screen-y" }), toDisplayString(touch.screenY), 1 /* TEXT */)
                ])
              ], 64 /* STABLE_FRAGMENT */)
            }), 128 /* KEYED_FRAGMENT */)
          ])
        : createCommentVNode("v-if", true),
      _ctx.touchCancelEvent !== null
        ? createElementVNode("view", utsMapOf({ key: 1 }), [
            createElementVNode("text", utsMapOf({ class: "title1" }), "touchCancel Event: "),
            createElementVNode("text", utsMapOf({ class: "title2" }), "touches: "),
            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.touchCancelEvent!.touches, (touch, index, __index, _cached): any => {
              return createElementVNode(Fragment, utsMapOf({ key: index }), [
                createElementVNode("text", utsMapOf({ class: "title3" }), "touch[" + toDisplayString(index) + "]:", 1 /* TEXT */),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "identifier: "),
                  createElementVNode("text", utsMapOf({ id: "touch-cancel-touch-identifier" }), toDisplayString(touch.identifier), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "pageX: "),
                  createElementVNode("text", utsMapOf({ id: "touch-cancel-touch-page-x" }), toDisplayString(touch.pageX), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "pageY: "),
                  createElementVNode("text", utsMapOf({ id: "touch-cancel-touch-page-y" }), toDisplayString(touch.pageY), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "clientX: "),
                  createElementVNode("text", utsMapOf({ id: "touch-cancel-touch-client-x" }), toDisplayString(touch.clientX), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "clientY: "),
                  createElementVNode("text", utsMapOf({ id: "touch-cancel-touch-client-y" }), toDisplayString(touch.clientY), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "screenX: "),
                  createElementVNode("text", utsMapOf({ id: "touch-cancel-touch-screen-x" }), toDisplayString(touch.screenX), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "screenY: "),
                  createElementVNode("text", utsMapOf({ id: "touch-cancel-touch-screen-y" }), toDisplayString(touch.screenY), 1 /* TEXT */)
                ])
              ], 64 /* STABLE_FRAGMENT */)
            }), 128 /* KEYED_FRAGMENT */),
            createElementVNode("text", utsMapOf({ class: "title2 uni-common-mt" }), "changedTouches: "),
            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.touchCancelEvent!.changedTouches, (touch, index, __index, _cached): any => {
              return createElementVNode(Fragment, utsMapOf({ key: index }), [
                createElementVNode("text", utsMapOf({ class: "title3" }), "touch[" + toDisplayString(index) + "]:", 1 /* TEXT */),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "identifier: "),
                  createElementVNode("text", utsMapOf({ id: "touch-cancel-changed-touch-identifier" }), toDisplayString(touch.identifier), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "pageX: "),
                  createElementVNode("text", utsMapOf({ id: "touch-cancel-changed-touch-page-x" }), toDisplayString(touch.pageX), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "pageY: "),
                  createElementVNode("text", utsMapOf({ id: "touch-cancel-changed-touch-page-y" }), toDisplayString(touch.pageY), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "clientX: "),
                  createElementVNode("text", utsMapOf({ id: "touch-cancel-changed-touch-client-x" }), toDisplayString(touch.clientX), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "clientY: "),
                  createElementVNode("text", utsMapOf({ id: "touch-cancel-changed-touch-client-y" }), toDisplayString(touch.clientY), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "screenX: "),
                  createElementVNode("text", utsMapOf({ id: "touch-cancel-changed-touch-screen-x" }), toDisplayString(touch.screenX), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "screenY: "),
                  createElementVNode("text", utsMapOf({ id: "touch-cancel-changed-touch-screen-y" }), toDisplayString(touch.screenY), 1 /* TEXT */)
                ])
              ], 64 /* STABLE_FRAGMENT */)
            }), 128 /* KEYED_FRAGMENT */)
          ])
        : createCommentVNode("v-if", true),
      _ctx.touchMoveEvent !== null
        ? createElementVNode("view", utsMapOf({ key: 2 }), [
            createElementVNode("text", utsMapOf({ class: "title1" }), "touchMove Event: "),
            createElementVNode("text", utsMapOf({ class: "title2" }), "touches: "),
            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.touchMoveEvent!.touches, (touch, index, __index, _cached): any => {
              return createElementVNode(Fragment, utsMapOf({ key: index }), [
                createElementVNode("text", utsMapOf({ class: "title3" }), "touch[" + toDisplayString(index) + "]:", 1 /* TEXT */),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "identifier: "),
                  createElementVNode("text", utsMapOf({ id: "touch-move-touch-identifier" }), toDisplayString(touch.identifier), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "pageX: "),
                  createElementVNode("text", utsMapOf({ id: "touch-move-touch-page-x" }), toDisplayString(touch.pageX), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "pageY: "),
                  createElementVNode("text", utsMapOf({ id: "touch-move-touch-page-y" }), toDisplayString(touch.pageY), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "clientX: "),
                  createElementVNode("text", utsMapOf({ id: "touch-move-touch-client-x" }), toDisplayString(touch.clientX), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "clientY: "),
                  createElementVNode("text", utsMapOf({ id: "touch-move-touch-client-y" }), toDisplayString(touch.clientY), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "screenX: "),
                  createElementVNode("text", utsMapOf({ id: "touch-move-touch-screen-x" }), toDisplayString(touch.screenX), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "screenY: "),
                  createElementVNode("text", utsMapOf({ id: "touch-move-touch-screen-y" }), toDisplayString(touch.screenY), 1 /* TEXT */)
                ])
              ], 64 /* STABLE_FRAGMENT */)
            }), 128 /* KEYED_FRAGMENT */),
            createElementVNode("text", utsMapOf({ class: "title2 uni-common-mt" }), "changedTouches: "),
            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.touchMoveEvent!.changedTouches, (touch, index, __index, _cached): any => {
              return createElementVNode(Fragment, utsMapOf({ key: index }), [
                createElementVNode("text", utsMapOf({ class: "title3" }), "touch[" + toDisplayString(index) + "]:", 1 /* TEXT */),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "identifier: "),
                  createElementVNode("text", utsMapOf({ id: "touch-move-changed-touch-identifier" }), toDisplayString(touch.identifier), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "pageX: "),
                  createElementVNode("text", utsMapOf({ id: "touch-move-changed-touch-page-x" }), toDisplayString(touch.pageX), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "pageY: "),
                  createElementVNode("text", utsMapOf({ id: "touch-move-changed-touch-page-y" }), toDisplayString(touch.pageY), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "clientX: "),
                  createElementVNode("text", utsMapOf({ id: "touch-move-changed-touch-client-x" }), toDisplayString(touch.clientX), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "clientY: "),
                  createElementVNode("text", utsMapOf({ id: "touch-move-changed-touch-client-y" }), toDisplayString(touch.clientY), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "screenX: "),
                  createElementVNode("text", utsMapOf({ id: "touch-move-changed-touch-screen-x" }), toDisplayString(touch.screenX), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "screenY: "),
                  createElementVNode("text", utsMapOf({ id: "touch-move-changed-touch-screen-y" }), toDisplayString(touch.screenY), 1 /* TEXT */)
                ])
              ], 64 /* STABLE_FRAGMENT */)
            }), 128 /* KEYED_FRAGMENT */)
          ])
        : createCommentVNode("v-if", true),
      _ctx.touchEndEvent !== null
        ? createElementVNode("view", utsMapOf({ key: 3 }), [
            createElementVNode("text", utsMapOf({ class: "title1" }), "touchEnd Event: "),
            createElementVNode("text", utsMapOf({ class: "title2" }), "touches: "),
            _ctx.touchEndEvent!.touches.length > 0
              ? createElementVNode(Fragment, utsMapOf({ key: 0 }), RenderHelpers.renderList(_ctx.touchEndEvent!.touches, (touch, index, __index, _cached): any => {
                  return createElementVNode(Fragment, utsMapOf({ key: index }), [
                    createElementVNode("text", utsMapOf({ class: "title3" }), "touch[" + toDisplayString(index) + "]:", 1 /* TEXT */),
                    createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                      createElementVNode("text", null, "identifier: "),
                      createElementVNode("text", utsMapOf({ id: "touch-end-touch-identifier" }), toDisplayString(touch.identifier), 1 /* TEXT */)
                    ]),
                    createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                      createElementVNode("text", null, "pageX: "),
                      createElementVNode("text", utsMapOf({ id: "touch-end-touch-page-x" }), toDisplayString(touch.pageX), 1 /* TEXT */)
                    ]),
                    createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                      createElementVNode("text", null, "pageY: "),
                      createElementVNode("text", utsMapOf({ id: "touch-end-touch-page-y" }), toDisplayString(touch.pageY), 1 /* TEXT */)
                    ]),
                    createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                      createElementVNode("text", null, "clientX: "),
                      createElementVNode("text", utsMapOf({ id: "touch-end-touch-client-x" }), toDisplayString(touch.clientX), 1 /* TEXT */)
                    ]),
                    createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                      createElementVNode("text", null, "clientY: "),
                      createElementVNode("text", utsMapOf({ id: "touch-end-touch-client-y" }), toDisplayString(touch.clientY), 1 /* TEXT */)
                    ]),
                    createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                      createElementVNode("text", null, "screenX: "),
                      createElementVNode("text", utsMapOf({ id: "touch-end-touch-screen-x" }), toDisplayString(touch.screenX), 1 /* TEXT */)
                    ]),
                    createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                      createElementVNode("text", null, "screenY: "),
                      createElementVNode("text", utsMapOf({ id: "touch-end-touch-screen-y" }), toDisplayString(touch.screenY), 1 /* TEXT */)
                    ])
                  ], 64 /* STABLE_FRAGMENT */)
                }), 128 /* KEYED_FRAGMENT */)
              : createCommentVNode("v-if", true),
            createElementVNode("text", utsMapOf({ class: "title2 uni-common-mt" }), "changedTouches: "),
            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.touchEndEvent!.changedTouches, (touch, index, __index, _cached): any => {
              return createElementVNode(Fragment, utsMapOf({ key: index }), [
                createElementVNode("text", utsMapOf({ class: "title3" }), "touch[" + toDisplayString(index) + "]:", 1 /* TEXT */),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "identifier: "),
                  createElementVNode("text", utsMapOf({ id: "touch-end-changed-touch-identifier" }), toDisplayString(touch.identifier), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "pageX: "),
                  createElementVNode("text", utsMapOf({ id: "touch-end-changed-touch-page-x" }), toDisplayString(touch.pageX), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "pageY: "),
                  createElementVNode("text", utsMapOf({ id: "touch-end-changed-touch-page-y" }), toDisplayString(touch.pageY), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "clientX: "),
                  createElementVNode("text", utsMapOf({ id: "touch-end-changed-touch-client-x" }), toDisplayString(touch.clientX), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "clientY: "),
                  createElementVNode("text", utsMapOf({ id: "touch-end-changed-touch-client-y" }), toDisplayString(touch.clientY), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "screenX: "),
                  createElementVNode("text", utsMapOf({ id: "touch-end-changed-touch-screen-x" }), toDisplayString(touch.screenX), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "screenY: "),
                  createElementVNode("text", utsMapOf({ id: "touch-end-changed-touch-screen-y" }), toDisplayString(touch.screenY), 1 /* TEXT */)
                ])
              ], 64 /* STABLE_FRAGMENT */)
            }), 128 /* KEYED_FRAGMENT */)
          ])
        : createCommentVNode("v-if", true),
      _ctx.longPressEvent !== null
        ? createElementVNode("view", utsMapOf({ key: 4 }), [
            createElementVNode("text", utsMapOf({ class: "title1" }), "longPress Event: "),
            createElementVNode("text", utsMapOf({ class: "title2" }), "touches: "),
            _ctx.longPressEvent!.touches.length > 0
              ? createElementVNode(Fragment, utsMapOf({ key: 0 }), RenderHelpers.renderList(_ctx.longPressEvent!.touches, (touch, index, __index, _cached): any => {
                  return createElementVNode(Fragment, utsMapOf({ key: index }), [
                    createElementVNode("text", utsMapOf({ class: "title3" }), "touch[" + toDisplayString(index) + "]:", 1 /* TEXT */),
                    createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                      createElementVNode("text", null, "identifier: "),
                      createElementVNode("text", utsMapOf({ id: "long-press-touch-identifier" }), toDisplayString(touch.identifier), 1 /* TEXT */)
                    ]),
                    createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                      createElementVNode("text", null, "pageX: "),
                      createElementVNode("text", utsMapOf({ id: "long-press-touch-page-x" }), toDisplayString(touch.pageX), 1 /* TEXT */)
                    ]),
                    createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                      createElementVNode("text", null, "pageY: "),
                      createElementVNode("text", utsMapOf({ id: "long-press-touch-page-y" }), toDisplayString(touch.pageY), 1 /* TEXT */)
                    ]),
                    createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                      createElementVNode("text", null, "clientX: "),
                      createElementVNode("text", utsMapOf({ id: "long-press-touch-client-x" }), toDisplayString(touch.clientX), 1 /* TEXT */)
                    ]),
                    createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                      createElementVNode("text", null, "clientY: "),
                      createElementVNode("text", utsMapOf({ id: "long-press-touch-client-y" }), toDisplayString(touch.clientY), 1 /* TEXT */)
                    ]),
                    createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                      createElementVNode("text", null, "screenX: "),
                      createElementVNode("text", utsMapOf({ id: "long-press-touch-screen-x" }), toDisplayString(touch.screenX), 1 /* TEXT */)
                    ]),
                    createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                      createElementVNode("text", null, "screenY: "),
                      createElementVNode("text", utsMapOf({ id: "long-press-touch-screen-y" }), toDisplayString(touch.screenY), 1 /* TEXT */)
                    ])
                  ], 64 /* STABLE_FRAGMENT */)
                }), 128 /* KEYED_FRAGMENT */)
              : createCommentVNode("v-if", true),
            createElementVNode("text", utsMapOf({ class: "title2 uni-common-mt" }), "changedTouches: "),
            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.longPressEvent!.changedTouches, (touch, index, __index, _cached): any => {
              return createElementVNode(Fragment, utsMapOf({ key: index }), [
                createElementVNode("text", utsMapOf({ class: "title3" }), "touch[" + toDisplayString(index) + "]:", 1 /* TEXT */),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "identifier: "),
                  createElementVNode("text", utsMapOf({ id: "long-press-changed-touch-identifier" }), toDisplayString(touch.identifier), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "pageX: "),
                  createElementVNode("text", utsMapOf({ id: "long-press-changed-touch-page-x" }), toDisplayString(touch.pageX), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "pageY: "),
                  createElementVNode("text", utsMapOf({ id: "long-press-changed-touch-page-y" }), toDisplayString(touch.pageY), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "clientX: "),
                  createElementVNode("text", utsMapOf({ id: "long-press-changed-touch-client-x" }), toDisplayString(touch.clientX), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "clientY: "),
                  createElementVNode("text", utsMapOf({ id: "long-press-changed-touch-client-y" }), toDisplayString(touch.clientY), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "screenX: "),
                  createElementVNode("text", utsMapOf({ id: "long-press-changed-touch-screen-x" }), toDisplayString(touch.screenX), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
                  createElementVNode("text", null, "screenY: "),
                  createElementVNode("text", utsMapOf({ id: "long-press-changed-touch-screen-y" }), toDisplayString(touch.screenY), 1 /* TEXT */)
                ])
              ], 64 /* STABLE_FRAGMENT */)
            }), 128 /* KEYED_FRAGMENT */)
          ])
        : createCommentVNode("v-if", true),
      _ctx.tapEvent !== null
        ? createElementVNode("view", utsMapOf({ key: 5 }), [
            createElementVNode("text", utsMapOf({ class: "title1" }), "tap Event: "),
            createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
              createElementVNode("text", null, "x: "),
              createElementVNode("text", utsMapOf({ id: "tap-event-x" }), toDisplayString(_ctx.tapEvent!.x), 1 /* TEXT */)
            ]),
            createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
              createElementVNode("text", null, "y: "),
              createElementVNode("text", utsMapOf({ id: "tap-event-y" }), toDisplayString(_ctx.tapEvent!.y), 1 /* TEXT */)
            ])
          ])
        : createCommentVNode("v-if", true),
      _ctx.clickEvent !== null
        ? createElementVNode("view", utsMapOf({ key: 6 }), [
            createElementVNode("text", utsMapOf({ class: "title1" }), "click Event: "),
            createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
              createElementVNode("text", null, "x: "),
              createElementVNode("text", utsMapOf({ id: "click-event-x" }), toDisplayString(_ctx.clickEvent!.x), 1 /* TEXT */)
            ]),
            createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
              createElementVNode("text", null, "y: "),
              createElementVNode("text", utsMapOf({ id: "click-event-y" }), toDisplayString(_ctx.clickEvent!.y), 1 /* TEXT */)
            ])
          ])
        : createCommentVNode("v-if", true)
    ])
  ], 4 /* STYLE */)
}
const GenPagesComponentGlobalEventsGlobalEventsStyles = [utsMapOf([["container", padStyleMapOf(utsMapOf([["paddingBottom", 10]]))], ["target", padStyleMapOf(utsMapOf([["marginTop", 20], ["marginRight", 0], ["marginBottom", 0], ["marginLeft", 50], ["width", 200], ["height", 100], ["backgroundColor", "#00FFFF"]]))], ["title1", padStyleMapOf(utsMapOf([["marginTop", 15], ["fontSize", 20]]))], ["title2", padStyleMapOf(utsMapOf([["marginTop", 10], ["fontSize", 18]]))], ["title3", padStyleMapOf(utsMapOf([["marginTop", 5], ["fontSize", 16]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
