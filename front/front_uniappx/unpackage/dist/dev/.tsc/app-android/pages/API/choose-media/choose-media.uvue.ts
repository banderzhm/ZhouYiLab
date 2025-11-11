
  type FileSource = { __$originalPosition?: UTSSourceMapPosition<"FileSource", "pages/API/choose-media/choose-media.uvue", 84, 8>;
    imagePath : string;
    filePath : string;
    fileType : string;
  };
  type ChooseSource = { __$originalPosition?: UTSSourceMapPosition<"ChooseSource", "pages/API/choose-media/choose-media.uvue", 89, 8>;
    value : string[];
    title : string;
  };
  const sourceTypeList : ChooseSource[] = [
    {
      value: ['camera'],
      title: '拍摄',
    },
    {
      value: ['album'],
      title: '相册',
    },
    {
      value: ['camera', 'album'],
      title: '拍摄或相册',
    }
  ];

  const mediaTypeList : ChooseSource[] = [
    {
      value: ['image'],
      title: '仅图片',
    },
    {
      value: ['video'],
      title: '仅视频',
    },
    {
      value: ['image', 'video'],
      title: '不限制',
    }
  ];

  const orientationTypeList : ChooseSource[] = [
    {
      value: ['portrait'],
      title: '竖屏',
    },
    {
      value: ['landscape'],
      title: '横屏',
    },
    {
      value: ['auto'],
      title: '自动',
    }
  ];
  const cameraTypeList : ChooseSource[] = [
    {
      value: ['front'],
      title: '前置摄像头',
    },
    {
      value: ['back'],
      title: '后置摄像头',
    }
  ];
  const __sfc__ = defineComponent({
    data() {
      return {
        title: 'chooseMedia',
        mediaList: [] as Array<FileSource>,
        sourceTypeIndex: 2,
        mediaTypeIndex: 2,
        cameraTypeIndex: 1,
        orientationTypeIndex: 0,
        albumModeTypeIndex: 0,
        count: 9,
        maxDuration: 10,
        sourceTypes: sourceTypeList as ChooseSource[],
        mediaTypes: mediaTypeList as ChooseSource[],
        cameraTypes: cameraTypeList as ChooseSource[],
        orientationTypes: orientationTypeList as ChooseSource[]
      }
    },
    methods: {
      chooseMediaSource() {
        uni.showActionSheet({
          itemList: ['拍摄', '相册', '拍摄或相册'],
          success: (e) => {
            this.sourceTypeIndex = e.tapIndex
          }
        })
      },
      chooseMediaType() {
        uni.showActionSheet({
          itemList: ['仅图片', '仅视频', '不限制'],
          success: (e) => {
            this.mediaTypeIndex = e.tapIndex
          }
        })
      },
      chooseMediaCount(event : UniInputBlurEvent) {
        let count = parseInt(event.detail.value)
        if (count < 1 || count > 9 || isNaN(count)) {
          uni.showToast({
            position: "bottom",
            title: "图片数量应该不小于1不大于9"
          })
          return
        }
        this.count = count
      },
      chooseOrientationType() {
        uni.showActionSheet({
          itemList: ['竖屏', '横屏', '自动'],
          success: (e) => {
            this.orientationTypeIndex = e.tapIndex
          }
        })
      },
      chooseCameraType() {
        uni.showActionSheet({
          itemList: ['前置', '后置'],
          success: (e) => {
            this.cameraTypeIndex = e.tapIndex
          }
        })
      },
      onMaxDurationConfirm(value : number) {
        this.maxDuration = value;
      },
      chooseMedia() {
        if (this.mediaList.length >= this.count) {
          const message = "已经有" + this.count + "个了，请删除部分后重新选择";
          uni.showToast({
            position: "bottom",
            title: message
          })
          return
        }

        uni.chooseMedia({
          count: this.count - this.mediaList.length,
          sourceType: sourceTypeList[this.sourceTypeIndex].value,
          mediaType: mediaTypeList[this.mediaTypeIndex].value,
          camera: cameraTypeList[this.cameraTypeIndex].value[0],




          pageOrientation: orientationTypeList[this.orientationTypeIndex].value[0],

          success: (res) => {
            const tempFiles : ChooseMediaTempFile[] = res.tempFiles as ChooseMediaTempFile[];
            for (let i = 0; i < tempFiles.length; i++) {
              const tempFile : ChooseMediaTempFile = tempFiles[i]
              const imagePath = tempFile.fileType == "image" ? tempFile.tempFilePath : tempFile.thumbTempFilePath;
              const file : FileSource = { imagePath: imagePath!, filePath: tempFile.tempFilePath, fileType: tempFile.fileType };
              this.mediaList.push(file);
            }
          },
          fail: (err) => {
            console.log("err: ", JSON.stringify(err), " at pages/API/choose-media/choose-media.uvue:243");
            uni.showToast({
              title:"choose media error.code:" + err.errCode+";message:"+err.errMsg,
              position:"bottom"
            })
          }
        })
      },
      previewMedia: function (index : number) {
        const file : FileSource = this.mediaList[index];
        if (file.fileType == "image") {
          uni.previewImage({
            current: 0,
            urls: [file.filePath]
          })
        } else {
          uni.$once("__ONFULLVIDEOLOAD", () => {
            uni.$emit("__ONRECEIVEURL", {
              "url": file.filePath,
              "cover": file.imagePath
            })
          })
          const url = "/pages/API/choose-media/fullscreen-video";
          uni.navigateTo({
            url: url,
          })
        }

      },
      removeMedia(index : number) {
        this.mediaList.splice(index, 1)
      },
    }
  })

export default __sfc__
function GenPagesAPIChooseMediaChooseMediaRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode("scroll-view", utsMapOf({ class: "page-scroll-view" }), [
    createElementVNode("view", null, [
      createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
      createElementVNode("view", utsMapOf({ class: "uni-common-mt" }), [
        createElementVNode("view", utsMapOf({ class: "uni-list" }), [
          createElementVNode("view", utsMapOf({ class: "uni-list-cell cell-pd" }), [
            createElementVNode("view", utsMapOf({ class: "uni-list-cell-left uni-label" }), " 来源 "),
            createElementVNode("view", utsMapOf({
              class: "uni-list-cell-right",
              onClick: _ctx.chooseMediaSource
            }), [
              createElementVNode("text", utsMapOf({ class: "click-t" }), toDisplayString(_ctx.sourceTypes[_ctx.sourceTypeIndex].title), 1 /* TEXT */)
            ], 8 /* PROPS */, ["onClick"])
          ]),
          createElementVNode("view", utsMapOf({ class: "uni-list-cell cell-pd" }), [
            createElementVNode("view", utsMapOf({ class: "uni-list-cell-left uni-label" }), " 方式 "),
            createElementVNode("view", utsMapOf({
              class: "uni-list-cell-right",
              onClick: _ctx.chooseMediaType
            }), [
              createElementVNode("text", utsMapOf({ class: "click-t" }), toDisplayString((_ctx.mediaTypes[_ctx.mediaTypeIndex] as ChooseSource).title), 1 /* TEXT */)
            ], 8 /* PROPS */, ["onClick"])
          ]),
          createElementVNode("view", utsMapOf({ class: "uni-list-cell cell-pd" }), [
            createElementVNode("view", utsMapOf({ class: "uni-list-cell-left uni-label" }), " 数量限制 "),
            createElementVNode("view", utsMapOf({ class: "uni-list-cell-right" }), [
              createElementVNode("input", utsMapOf({
                class: "click-t",
                ref: "refCountInput",
                value: _ctx.count,
                type: "number",
                maxlength: 1,
                onBlur: _ctx.chooseMediaCount
              }), null, 40 /* PROPS, NEED_HYDRATION */, ["value", "onBlur"])
            ])
          ]),
          createElementVNode("view", utsMapOf({ class: "uni-list-cell cell-pd" }), [
            createElementVNode("view", utsMapOf({ class: "uni-list-cell-left uni-label" }), " 屏幕方向 "),
            createElementVNode("view", utsMapOf({
              class: "uni-list-cell-right",
              onClick: _ctx.chooseOrientationType
            }), [
              createElementVNode("text", utsMapOf({ class: "click-t" }), toDisplayString(_ctx.orientationTypes[_ctx.orientationTypeIndex].title), 1 /* TEXT */)
            ], 8 /* PROPS */, ["onClick"])
          ]),
          createElementVNode("view", utsMapOf({ class: "uni-list-cell cell-pd" }), [
            createElementVNode("view", utsMapOf({ class: "uni-list-cell-left uni-label" }), " 摄像头 "),
            createElementVNode("view", utsMapOf({
              class: "uni-list-cell-right",
              onClick: _ctx.chooseCameraType
            }), [
              createElementVNode("text", utsMapOf({ class: "click-t" }), toDisplayString(_ctx.cameraTypes[_ctx.cameraTypeIndex].title), 1 /* TEXT */)
            ], 8 /* PROPS */, ["onClick"])
          ])
        ]),
        createElementVNode("view", utsMapOf({
          class: "uni-list list-pd",
          style: normalizeStyle(utsMapOf({"padding":"15px"}))
        }), [
          createElementVNode("view", utsMapOf({
            class: "uni-flex",
            style: normalizeStyle(utsMapOf({"margin-bottom":"10px"}))
          }), [
            createElementVNode("view", utsMapOf({ class: "uni-list-cell-left" }), "点击预览"),
            createElementVNode("view", utsMapOf({
              style: normalizeStyle(utsMapOf({"margin-left":"auto"}))
            }), [
              createElementVNode("text", utsMapOf({ class: "click-t" }), toDisplayString(_ctx.mediaList.length) + "/" + toDisplayString(_ctx.count), 1 /* TEXT */)
            ], 4 /* STYLE */)
          ], 4 /* STYLE */),
          createElementVNode("view", utsMapOf({
            class: "uni-flex",
            style: normalizeStyle(utsMapOf({"flex-wrap":"wrap"}))
          }), [
            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.mediaList, (file, index, __index, _cached): any => {
              return createElementVNode("view", utsMapOf({
                key: index,
                class: "uni-uploader__input-box",
                style: normalizeStyle(utsMapOf({"border":"0"}))
              }), [
                createElementVNode("image", utsMapOf({
                  style: normalizeStyle(utsMapOf({"width":"104px","height":"104px"})),
                  src: file.imagePath,
                  "data-src": file.imagePath,
                  onClick: () => {_ctx.previewMedia(index)}
                }), null, 12 /* STYLE, PROPS */, ["src", "data-src", "onClick"]),
                createElementVNode("image", utsMapOf({
                  src: "/static/plus.png",
                  class: "image-remove",
                  onClick: () => {_ctx.removeMedia(index)}
                }), null, 8 /* PROPS */, ["onClick"])
              ], 4 /* STYLE */)
            }), 128 /* KEYED_FRAGMENT */),
            createElementVNode("image", utsMapOf({
              class: "uni-uploader__input-box",
              onClick: _ctx.chooseMedia,
              src: "/static/plus.png"
            }), null, 8 /* PROPS */, ["onClick"])
          ], 4 /* STYLE */)
        ], 4 /* STYLE */)
      ])
    ])
  ])
}
const GenPagesAPIChooseMediaChooseMediaStyles = [utsMapOf([["cell-pd", padStyleMapOf(utsMapOf([["paddingTop", 11], ["paddingRight", 15], ["paddingBottom", 11], ["paddingLeft", 15]]))], ["click-t", padStyleMapOf(utsMapOf([["color", "#A9A9A9"]]))], ["list-pd", padStyleMapOf(utsMapOf([["marginTop", 25]]))], ["uni-uploader__input-box", padStyleMapOf(utsMapOf([["marginTop", 5], ["marginRight", 5], ["marginBottom", 5], ["marginLeft", 5], ["width", 104], ["height", 104], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#D9D9D9"], ["borderRightColor", "#D9D9D9"], ["borderBottomColor", "#D9D9D9"], ["borderLeftColor", "#D9D9D9"]]))], ["uni-uploader__input", padStyleMapOf(utsMapOf([["position", "absolute"], ["zIndex", 1], ["top", 0], ["left", 0], ["width", "100%"], ["height", "100%"], ["opacity", 0]]))], ["image-remove", padStyleMapOf(utsMapOf([["transform", "rotate(45deg)"], ["width", 25], ["height", 25], ["position", "absolute"], ["top", 0], ["right", 0], ["borderTopLeftRadius", 13], ["borderTopRightRadius", 13], ["borderBottomRightRadius", 13], ["borderBottomLeftRadius", 13], ["backgroundColor", "rgba(200,200,200,0.8)"]]))], ["item_width", padStyleMapOf(utsMapOf([["width", 130]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
