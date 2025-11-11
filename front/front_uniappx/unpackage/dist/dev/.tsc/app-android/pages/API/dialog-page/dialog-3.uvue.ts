
  import { PageStyleItem, PageStyleArray } from './page-style.uts';
  type CloseAnimationType =
    'auto' |
    'none' |
    'slide-out-right' |
    'slide-out-left' |
    'slide-out-top' |
    'slide-out-bottom' |
    'fade-out' |
    'zoom-in' |
    'zoom-fade-in'

  const __sfc__ = defineComponent({
    data() {
      return {
        title: 'dialog 3',
        PageStyleArray: PageStyleArray as PageStyleItem[],
        currentPageStyle: {} as UTSJSONObject,
        closeAnimationType: 'auto' as CloseAnimationType,
        closeAnimationTypeList: [
          'auto',
          'none',
          'slide-out-right',
          'slide-out-left',
          'slide-out-top',
          'slide-out-bottom',
          'fade-out',
          'zoom-in',
          'zoom-fade-in'
        ]
      }
    },
    onLoad(_ : OnLoadOptions) {
      this.getPageStyle()
    },
    methods: {
      getPageStyle() {
        this.currentPageStyle = this.$page.getPageStyle()
      },
      radioChange(key : string, e : RadioGroupChangeEvent) {
        this.setStyleValue(key, e.detail.value);
      },
      sliderChange(key : string, e : UniSliderChangeEvent) {
        this.setStyleValue(key, e.detail.value);
      },
      switchChange(key : string, e : UniSwitchChangeEvent) {
        this.setStyleValue(key, e.detail.value);
      },
      setStyleValue(key : string, value : any) {
        const style = {__$originalPosition: new UTSSourceMapPosition("style", "pages/API/dialog-page/dialog-3.uvue", 94, 15),}
        style[key] = value
        this.setPageStyle(style)
        this.getPageStyle()
      },
      setPageStyle(style : UTSJSONObject) {
        this.$page.setPageStyle(style);
      },
      handleChooseAnimationType(e : RadioGroupChangeEvent){
        this.closeAnimationType = e.detail.value as CloseAnimationType
      },
      closeThisDialog() {
        uni.closeDialogPage({
          dialogPage: this.$page,
          animationType: this.closeAnimationType,
          success(res) {
            console.log('closeDialog success', res, " at pages/API/dialog-page/dialog-3.uvue:110")
          },
          fail(err) {
            console.log('closeDialog fail', err, " at pages/API/dialog-page/dialog-3.uvue:113")
          },
          complete(res) {
            console.log('closeDialog complete', res, " at pages/API/dialog-page/dialog-3.uvue:116")
          }
        })
      }
    }
  })

export default __sfc__
function GenPagesAPIDialogPageDialog3Render(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_switch = resolveComponent("switch")
const _component_slider = resolveComponent("slider")
const _component_radio = resolveComponent("radio")
const _component_radio_group = resolveComponent("radio-group")

  return createElementVNode("view", utsMapOf({
    id: "dialog3",
    class: "dialog-container"
  }), [
    createElementVNode("scroll-view", utsMapOf({ class: "dialog-content" }), [
      createElementVNode("text", null, "title: " + toDisplayString(_ctx.title), 1 /* TEXT */),
      createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.PageStyleArray, (item, index, __index, _cached): any => {
        return createElementVNode(Fragment, null, [
          _ctx.currentPageStyle[item.key] != null
            ? createElementVNode("view", utsMapOf({
                class: "page-style-item",
                key: index
              }), [
                createElementVNode("view", utsMapOf({ class: "item-text" }), [
                  createElementVNode("text", utsMapOf({ class: "item-text-key" }), toDisplayString(item.key) + ":", 1 /* TEXT */),
                  createElementVNode("text", utsMapOf({ class: "item-text-value" }), toDisplayString(_ctx.currentPageStyle[item.key]), 1 /* TEXT */)
                ]),
                item.type == 'boolean'
                  ? createElementVNode("view", utsMapOf({
                      key: 0,
                      class: "mt-10"
                    }), [
                      createVNode(_component_switch, utsMapOf({
                        checked: _ctx.currentPageStyle.getBoolean(item.key),
                        onChange: ($event: any) => {_ctx.switchChange(item.key, $event as UniSwitchChangeEvent)}
                      }), null, 8 /* PROPS */, ["checked", "onChange"])
                    ])
                  : item.type == 'number'
                    ? createElementVNode("view", utsMapOf({
                        key: 1,
                        class: "mt-10"
                      }), [
                        createVNode(_component_slider, utsMapOf({
                          value: _ctx.currentPageStyle.getNumber(item.key),
                          "show-value": true,
                          onChange: ($event: any) => {_ctx.sliderChange(item.key, $event as UniSliderChangeEvent)}
                        }), null, 8 /* PROPS */, ["value", "onChange"])
                      ])
                    : item.type == 'string'
                      ? createElementVNode("view", utsMapOf({
                          key: 2,
                          class: "mt-10"
                        }), [
                          createVNode(_component_radio_group, utsMapOf({
                            class: "radio-set-value",
                            onChange: ($event: any) => {_ctx.radioChange(item.key, $event as RadioGroupChangeEvent)}
                          }), utsMapOf({
                            default: withSlotCtx((): any[] => [
                              createElementVNode(Fragment, null, RenderHelpers.renderList(item.value, (item2, index2, __index, _cached): any => {
                                return createVNode(_component_radio, utsMapOf({
                                  class: "ml-10",
                                  key: index2,
                                  value: item2,
                                  checked: _ctx.currentPageStyle[item.key] == item2
                                }), utsMapOf({
                                  default: withSlotCtx((): any[] => [toDisplayString(item2)]),
                                  _: 2 /* DYNAMIC */
                                }), 1032 /* PROPS, DYNAMIC_SLOTS */, ["value", "checked"])
                              }), 128 /* KEYED_FRAGMENT */)
                            ]),
                            _: 2 /* DYNAMIC */
                          }), 1032 /* PROPS, DYNAMIC_SLOTS */, ["onChange"])
                        ])
                      : createCommentVNode("v-if", true)
              ])
            : createCommentVNode("v-if", true)
        ], 64 /* STABLE_FRAGMENT */)
      }), 256 /* UNKEYED_FRAGMENT */),
      createElementVNode("text", utsMapOf({ class: "mt-10 choose-close-animation-type-title" }), "choose close dialogPage animationType"),
      createVNode(_component_radio_group, utsMapOf({
        class: "choose-close-animation-type-radio-group",
        onChange: _ctx.handleChooseAnimationType
      }), utsMapOf({
        default: withSlotCtx((): any[] => [
          createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.closeAnimationTypeList, (item, __key, __index, _cached): any => {
            return createVNode(_component_radio, utsMapOf({
              class: "ml-10 mt-10",
              key: item,
              value: item,
              checked: _ctx.closeAnimationType == item
            }), utsMapOf({
              default: withSlotCtx((): any[] => [toDisplayString(item)]),
              _: 2 /* DYNAMIC */
            }), 1032 /* PROPS, DYNAMIC_SLOTS */, ["value", "checked"])
          }), 128 /* KEYED_FRAGMENT */)
        ]),
        _: 1 /* STABLE */
      }), 8 /* PROPS */, ["onChange"]),
      createElementVNode("button", utsMapOf({
        class: "mt-10",
        onClick: _ctx.closeThisDialog
      }), " closeThisDialog ", 8 /* PROPS */, ["onClick"])
    ])
  ])
}
const GenPagesAPIDialogPageDialog3Styles = [utsMapOf([["dialog-container", padStyleMapOf(utsMapOf([["width", "100%"], ["height", "100%"], ["backgroundColor", "rgba(0,0,0,0.3)"], ["display", "flex"], ["justifyContent", "center"], ["alignItems", "center"]]))], ["dialog-content", padStyleMapOf(utsMapOf([["width", "90%"], ["height", 500], ["paddingTop", 10], ["paddingRight", 6], ["paddingBottom", 10], ["paddingLeft", 6], ["backgroundColor", "#ffffff"], ["borderTopLeftRadius", 6], ["borderTopRightRadius", 6], ["borderBottomRightRadius", 6], ["borderBottomLeftRadius", 6]]))], ["mt-10", padStyleMapOf(utsMapOf([["marginTop", 10]]))], ["ml-10", padStyleMapOf(utsMapOf([["marginLeft", 10]]))], ["page-style-item", padStyleMapOf(utsMapOf([["paddingTop", 6], ["paddingRight", 0], ["paddingBottom", 6], ["paddingLeft", 0], ["marginTop", 10], ["backgroundColor", "#ffffff"], ["borderTopLeftRadius", 5], ["borderTopRightRadius", 5], ["borderBottomRightRadius", 5], ["borderBottomLeftRadius", 5]]))], ["item-text", padStyleMapOf(utsMapOf([["flexDirection", "row"]]))], ["item-text-key", padStyleMapOf(utsMapOf([["fontWeight", "bold"]]))], ["item-text-value", padStyleMapOf(utsMapOf([["marginLeft", 5]]))], ["radio-set-value", padStyleMapOf(utsMapOf([["flexDirection", "row"]]))], ["choose-close-animation-type-title", padStyleMapOf(utsMapOf([["fontWeight", "bold"]]))], ["choose-close-animation-type-radio-group", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["flexWrap", "wrap"]]))]])]
