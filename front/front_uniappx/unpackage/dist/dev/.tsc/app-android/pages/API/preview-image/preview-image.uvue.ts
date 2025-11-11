
  type Indicator = "number" | "default" | "none"
  type ItemType = { __$originalPosition?: UTSSourceMapPosition<"ItemType", "pages/API/preview-image/preview-image.uvue", 56, 8>;
    value : Indicator,
    name : string
  }

  type ImageType = { __$originalPosition?: UTSSourceMapPosition<"ImageType", "pages/API/preview-image/preview-image.uvue", 61, 8>;
    src : string,
    error : boolean
  }
  const __sfc__ = defineComponent({
    data() {
      return {
        imageList: [
          { src: "https://qiniu-web-assets.dcloud.net.cn/unidoc/zh/uni@2x.png", error: false },
          { src: "/static/uni.png", error: false },
          { src: "/static/test-image/logo.svg", error: false },

          { src: "/static/uni2.png", error: false },

        ] as ImageType[],
        indicator: [{
          value: "default",
          name: "圆点"
        }, {
          value: "number",
          name: "数字"
        }, {
          value: "none",
          name: "不显示"
        }] as ItemType[],
        currentIndicator: "default" as Indicator,




        isWeb: false,





        isIOS: false,

        isLongPress: true,
        isLoop: true,
      }
    },
    methods: {
      previewImage(index : number) {
        let list = [] as Array<string>
        this.imageList.forEach((item : ImageType) => {
          list.push(item.src)
        })
        uni.previewImage({
          urls: list,
          current: index,
          indicator: this.currentIndicator,
          loop: this.isLoop,
          longPressActions: (this.isLongPress ? ({
            itemList: ["按钮1", "按钮2", "按钮3"],
            itemColor: "#ccc",
            success: (e : LongPressActionsSuccessResult) => {
              uni.showToast({
                title: "用户选中了第" + (e.index + 1) + "张图片，并选中了第" + (e.tapIndex + 1) + "个选项",
                position: "bottom"
              })
            },
            fail: (e : LongPressActionsFailResult) => {
              uni.showToast({
                title: "用户关闭了action sheet",
                position: "bottom"
              })
            }
          } as LongPressActionsOptions) : null)
        })
      },
      chooseImage() {
        uni.chooseImage({
          sourceType: ['album'],
          count: 1,
          success: (e) => {
            this.imageList = this.imageList.concat({ src: e.tempFilePaths[0], error: false } as ImageType)
            // this.imageList = this.imageList.concat(e.tempFilePaths)
          },
          fail(_) {
          }
        })
      },
      onIndicatorChanged(e : UniRadioGroupChangeEvent) {
        this.currentIndicator = e.detail.value as Indicator
      },
      onCheckboxChange(_ : UniCheckboxGroupChangeEvent) {
        this.isLoop = !this.isLoop
      },
      onLongPressCheckboxChange() {
        this.isLongPress = !this.isLongPress
      },
      onImageLoadError(index : number, error : UniImageErrorEvent) {
        this.imageList[index].error = true
      }
    }
  })

export default __sfc__
function GenPagesAPIPreviewImagePreviewImageRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_radio = resolveComponent("radio")
const _component_radio_group = resolveComponent("radio-group")
const _component_checkbox = resolveComponent("checkbox")
const _component_checkbox_group = resolveComponent("checkbox-group")

  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"padding-left":"8px","padding-right":"8px"}))
    }), [
      createElementVNode("view", null, [
        createElementVNode("text", utsMapOf({ class: "text-desc" }), "图片指示器样式"),
        createVNode(_component_radio_group, utsMapOf({
          class: "cell-ct",
          style: normalizeStyle(utsMapOf({"background-color":"white"})),
          onChange: _ctx.onIndicatorChanged
        }), utsMapOf({
          default: withSlotCtx((): any[] => [
            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.indicator, (item, index, __index, _cached): any => {
              return createElementVNode("view", utsMapOf({
                class: "indicator-it",
                key: item.value
              }), [
                createVNode(_component_radio, utsMapOf({
                  disabled: _ctx.isWeb,
                  checked: index == 0,
                  value: item.value
                }), utsMapOf({
                  default: withSlotCtx((): any[] => [toDisplayString(item.name)]),
                  _: 2 /* DYNAMIC */
                }), 1032 /* PROPS, DYNAMIC_SLOTS */, ["disabled", "checked", "value"])
              ])
            }), 128 /* KEYED_FRAGMENT */)
          ]),
          _: 1 /* STABLE */
        }), 8 /* PROPS */, ["style", "onChange"])
      ]),
      createElementVNode("view", null, [
        createVNode(_component_checkbox_group, utsMapOf({
          onChange: _ctx.onCheckboxChange,
          style: normalizeStyle(utsMapOf({"margin-top":"16px","margin-bottom":"16px","margin-left":"8px"}))
        }), utsMapOf({
          default: withSlotCtx((): any[] => [
            createVNode(_component_checkbox, utsMapOf({
              disabled: _ctx.isWeb,
              checked: _ctx.isLoop,
              style: normalizeStyle(utsMapOf({"margin-right":"15px"}))
            }), utsMapOf({
              default: withSlotCtx((): any[] => ["循环播放"]),
              _: 1 /* STABLE */
            }), 8 /* PROPS */, ["disabled", "checked", "style"])
          ]),
          _: 1 /* STABLE */
        }), 8 /* PROPS */, ["onChange", "style"])
      ]),
      createElementVNode("view", null, [
        createVNode(_component_checkbox_group, utsMapOf({
          onChange: _ctx.onLongPressCheckboxChange,
          style: normalizeStyle(utsMapOf({"margin-top":"16px","margin-bottom":"16px","margin-left":"8px"}))
        }), utsMapOf({
          default: withSlotCtx((): any[] => [
            createVNode(_component_checkbox, utsMapOf({
              disabled: _ctx.isWeb,
              checked: _ctx.isLongPress,
              style: normalizeStyle(utsMapOf({"margin-right":"15px"}))
            }), utsMapOf({
              default: withSlotCtx((): any[] => ["支持长按事件"]),
              _: 1 /* STABLE */
            }), 8 /* PROPS */, ["disabled", "checked", "style"])
          ]),
          _: 1 /* STABLE */
        }), 8 /* PROPS */, ["onChange", "style"])
      ]),
      createElementVNode("view", utsMapOf({
        style: normalizeStyle(utsMapOf({"background-color":"white"}))
      }), [
        createElementVNode("text", utsMapOf({ class: "text-desc" }), "点击图片开始预览"),
        createElementVNode("view", utsMapOf({
          class: "cell-ct",
          style: normalizeStyle(utsMapOf({"margin":"8px"}))
        }), [
          createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.imageList, (image, index, __index, _cached): any => {
            return createElementVNode("view", utsMapOf({
              class: "cell cell-choose-image",
              key: index
            }), [
              isTrue(image.error)
                ? createElementVNode("text", utsMapOf({
                    key: 0,
                    style: normalizeStyle(utsMapOf({"width":"100px","height":"100px","background-color":"lightgray","color":"red","text-align":"center","line-height":"100px","font-size":"14px"})),
                    onClick: () => {_ctx.previewImage(index)}
                  }), "图片路径非法", 12 /* STYLE, PROPS */, ["onClick"])
                : createCommentVNode("v-if", true),
              isTrue(!image.error)
                ? createElementVNode("image", utsMapOf({
                    key: 1,
                    style: normalizeStyle(utsMapOf({"width":"100px","height":"100px","background-color":"white"})),
                    mode: "aspectFit",
                    src: image.src,
                    onClick: () => {_ctx.previewImage(index)},
                    onError: ($event: any) => {_ctx.onImageLoadError(index,$event as ImageErrorEvent)}
                  }), null, 44 /* STYLE, PROPS, NEED_HYDRATION */, ["src", "onClick", "onError"])
                : createCommentVNode("v-if", true)
            ])
          }), 128 /* KEYED_FRAGMENT */),
          createElementVNode("image", utsMapOf({
            class: "cell cell-choose-image",
            src: "/static/plus.png",
            onClick: _ctx.chooseImage
          }), [
            createElementVNode("view")
          ], 8 /* PROPS */, ["onClick"])
        ], 4 /* STYLE */)
      ], 4 /* STYLE */),
      createElementVNode("view", utsMapOf({
        style: normalizeStyle(utsMapOf({"margin":"8px"}))
      }), [
        createElementVNode("text", utsMapOf({
          style: normalizeStyle(utsMapOf({"color":"black","font-size":"18px","margin-bottom":"4px"}))
        }), "注意事项:", 4 /* STYLE */),
        createElementVNode("text", utsMapOf({
          style: normalizeStyle(utsMapOf({"font-size":"17px","margin-left":"4px","color":"darkgray"}))
        }), "1、indicator属性仅App平台支持。", 4 /* STYLE */),
        createElementVNode("text", utsMapOf({
          style: normalizeStyle(utsMapOf({"font-size":"17px","margin-left":"4px","color":"darkgray"}))
        }), "2、Web平台不支持loop属性。", 4 /* STYLE */)
      ], 4 /* STYLE */)
    ], 4 /* STYLE */)
  ], 4 /* STYLE */)
}
const GenPagesAPIPreviewImagePreviewImageStyles = [utsMapOf([["text-desc", padStyleMapOf(utsMapOf([["marginTop", 16], ["marginLeft", 8], ["marginBottom", 16], ["fontWeight", "bold"]]))], ["cell-ct", padStyleMapOf(utsMapOf([["display", "flex"], ["flexWrap", "wrap"], ["flexDirection", "row"]]))], ["cell", padStyleMapOf(utsMapOf([["marginLeft", 3], ["marginRight", 3], ["width", 100], ["height", 100]]))], ["cell-choose-image", padStyleMapOf(utsMapOf([["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#D3D3D3"], ["borderRightColor", "#D3D3D3"], ["borderBottomColor", "#D3D3D3"], ["borderLeftColor", "#D3D3D3"]]))], ["indicator-it", padStyleMapOf(utsMapOf([["marginTop", 8], ["marginRight", 8], ["marginBottom", 8], ["marginLeft", 8]]))], ["cell-pd", padStyleMapOf(utsMapOf([["paddingTop", 11], ["paddingRight", 0], ["paddingBottom", 11], ["paddingLeft", 0]]))]])]
