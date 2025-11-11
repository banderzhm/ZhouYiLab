
  const __sfc__ = defineComponent({
    data() {
      return {
        log: '',
        title: 'choose-file',
        src: '',
        _audioContext: null as InnerAudioContext | null,
      }
    },
    unmounted() {
      if (this._audioContext != null) {
        this._audioContext!.destroy()
      }
    },
    methods: {

      getPath(chooseFils : ChooseFileTempFile[]) : string {
        var urls = new Array<string>()
        chooseFils.forEach(value => {
          urls.push(value.path)
        })
        return urls.join(', ')
      },
      chooseVideo() {
        uni.chooseFile({
          type: 'video',
          count: 1,
          success: (res) => {
            console.log(res, " at pages/API/choose-file/choose-file.uvue:69")
            if (res.tempFiles.length > 0) {
              this.log += this.getPath(res.tempFiles) + '\n\n'
            }
          },
          complete: (res) => {
            console.log(res, " at pages/API/choose-file/choose-file.uvue:75")
          }
        })
      },
      chooseVideoMul() {
        uni.chooseFile({
          type: 'video',
          success: (res) => {
            console.log(res, " at pages/API/choose-file/choose-file.uvue:83")
            if (res.tempFiles.length > 0) {
              this.log += this.getPath(res.tempFiles) + '\n\n'
            }
          },
          complete: (res) => {
            console.log(res, " at pages/API/choose-file/choose-file.uvue:89")
          }
        })
      },
      playVideo() {
        uni.chooseFile({
          type: 'video',
          count: 1,
          success: (res) => {
            console.log(res, " at pages/API/choose-file/choose-file.uvue:98")
            if (res.tempFiles.length > 0) {
              this.src = res.tempFiles[0].path
              this._audioContext?.destroy()
              this._audioContext = null
            }
          },
          complete: (res) => {
            console.log(res, " at pages/API/choose-file/choose-file.uvue:106")
          }
        })
      },
      chooseImage() {
        uni.chooseFile({
          type: 'image',
          count: 1,
          success: (res) => {
            console.log(res, " at pages/API/choose-file/choose-file.uvue:115")
            if (res.tempFiles.length > 0) {
              this.log += this.getPath(res.tempFiles) + '\n\n'
            }
          },
          complete: (res) => {
            console.log(res, " at pages/API/choose-file/choose-file.uvue:121")
          }
        })
      },
      chooseImageMul() {
        uni.chooseFile({
          type: 'image',
          count: 90,
          success: (res) => {
            console.log(res, " at pages/API/choose-file/choose-file.uvue:130")
            if (res.tempFiles.length > 0) {
              this.log += this.getPath(res.tempFiles) + '\n\n'
            }
          },
          complete: (res) => {
            console.log(res, " at pages/API/choose-file/choose-file.uvue:136")
          }
        })
      },
      viewImg() {
        uni.chooseFile({
          type: 'image',
          success: (res) => {
            console.log(res, " at pages/API/choose-file/choose-file.uvue:144")
            if (res.tempFiles.length > 0) {
              const tempFiles: Array<string> = res.tempFiles.map((value) => {
              	return value.path
              })
              uni.previewImage({
                current: 0,
                urls: tempFiles,
                success: (res) => {
                  console.log(res, " at pages/API/choose-file/choose-file.uvue:153")
                },
                fail: (err) => {
                  console.log(err, " at pages/API/choose-file/choose-file.uvue:156")
                }
              })
            }
          },
          complete: (res) => {
            console.log(res, " at pages/API/choose-file/choose-file.uvue:162")
          }
        })
      },
      chooseAudio() {
        uni.chooseFile({
          type: 'audio',
          count: 1,
          success: (res) => {
            console.log(res, " at pages/API/choose-file/choose-file.uvue:171")
            if (res.tempFiles.length > 0) {
              this.log += this.getPath(res.tempFiles) + '\n\n'
            }
          },
          complete: (res) => {
            console.log(res, " at pages/API/choose-file/choose-file.uvue:177")
          }
        })
      },
      chooseAudioMul() {
        uni.chooseFile({
          type: 'audio',
          success: (res) => {
            console.log(res, " at pages/API/choose-file/choose-file.uvue:185")
            if (res.tempFiles.length > 0) {
              this.log += this.getPath(res.tempFiles) + '\n\n'
            }
          },
          complete: (res) => {
            console.log(res, " at pages/API/choose-file/choose-file.uvue:191")
          }
        })
      },
      playAudio() {
        uni.chooseFile({
          type: 'audio',
          count: 1,
          success: (res) => {
            console.log(res, " at pages/API/choose-file/choose-file.uvue:200");
            if (res.tempFiles.length > 0) {
              if (this._audioContext == null) {
              this.src = ''
                this._audioContext = uni.createInnerAudioContext()
                this._audioContext!.autoplay = true
              } else if (!this._audioContext!.paused) {
                this._audioContext!.stop()
              }
              this._audioContext!.src = res.tempFiles[0].path
            }
          },
          complete: (res) => {
            console.log(res, " at pages/API/choose-file/choose-file.uvue:213");
          }
        });

      },
      chooseAll() {
        uni.chooseFile({
          type: 'all',
          count: 1,
          success: (res) => {
            console.log(res, " at pages/API/choose-file/choose-file.uvue:223")
            if (res.tempFiles.length > 0) {
              this.log += this.getPath(res.tempFiles) + '\n\n'
            }
          },
          complete: (res) => {
            console.log(res, " at pages/API/choose-file/choose-file.uvue:229")
          }
        })
      },
      chooseAllMul() {
        uni.chooseFile({
          type: 'all',
          success: (res) => {
            console.log(res, " at pages/API/choose-file/choose-file.uvue:237")
            if (res.tempFiles.length > 0) {
              this.log += this.getPath(res.tempFiles) + '\n\n'
            }
          },
          complete: (res) => {
            console.log(res, " at pages/API/choose-file/choose-file.uvue:243")
          }
        })
      }
    }
  })

export default __sfc__
function GenPagesAPIChooseFileChooseFileRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("view", null, [
      createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
      createElementVNode("button", utsMapOf({
        size: "mini",
        onClick: () => {_ctx.log=''}
      }), "清空日志", 8 /* PROPS */, ["onClick"]),
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"margin":"2px","padding":"2px","border":"1px solid #000000"}))
      }), toDisplayString(_ctx.log), 5 /* TEXT, STYLE */),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.chooseVideo
      }), "选择文件(video) 单选", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.chooseVideoMul
      }), "选择文件(video) 多选", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.playVideo
      }), "选择文件(video)并播放", 8 /* PROPS */, ["onClick"]),
      createElementVNode("video", utsMapOf({
        class: "video",
        src: _ctx.src,
        controls: true,
        autoplay: true,
        loop: true
      }), "111", 8 /* PROPS */, ["src"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.chooseImage
      }), "选择文件(image) 单选", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.chooseImageMul
      }), "选择文件(image) 多选", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.viewImg
      }), "选择文件(image) 并预览", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.chooseAudio
      }), "选择文件(audio) 单选", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.chooseAudioMul
      }), "选择文件(audio) 多选", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.playAudio
      }), "选择文件(audio) 并播放", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.chooseAll
      }), "选择文件(all) 单选", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.chooseAllMul
      }), "选择文件(all) 多选", 8 /* PROPS */, ["onClick"]),
      createElementVNode("view", utsMapOf({
        style: normalizeStyle(utsMapOf({"height":"4px"}))
      }), null, 4 /* STYLE */)
    ])
  ], 4 /* STYLE */)
}
const GenPagesAPIChooseFileChooseFileStyles = [utsMapOf([["btnstyle", padStyleMapOf(utsMapOf([["marginTop", 4], ["marginRight", 4], ["marginBottom", 4], ["marginLeft", 4]]))], ["video", padStyleMapOf(utsMapOf([["width", "100%"], ["height", 225]]))], ["uni-uploader__input-box", padStyleMapOf(utsMapOf([["marginTop", 5], ["marginRight", 5], ["marginBottom", 5], ["marginLeft", 5], ["width", 104], ["height", 104], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#D9D9D9"], ["borderRightColor", "#D9D9D9"], ["borderBottomColor", "#D9D9D9"], ["borderLeftColor", "#D9D9D9"]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
