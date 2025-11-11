
  type AudioPath = { __$originalPosition?: UTSSourceMapPosition<"AudioPath", "pages/API/create-inner-audio-context/inner-audio-path.uvue", 16, 8>;
    description : string
    src : string
  }
  const __sfc__ = defineComponent({
    data() {
      return {
        title: 'audio-path',
        playIndex: 0,
        isPlaying: false,

        nativePath: uni.env.CACHE_PATH + 'uni-audio/test/test.mp3' as string,
        // sdcardPath: 'sdcard/uni-audio/test.mp3',

        _audioContext: null as InnerAudioContext | null,
        supportPaths: [
          {
            description: '本地路径：/static方式',
            src: '/static/test-audio/ForElise.mp3'
          },
          {
            description: '本地路径：../static/',
            src: '../../../static/test-audio/ForElise.mp3'
          },

          {
            description: '本地路径：env方式',
            src: 'env'
          },

          {
            description: '网络路径',
            src: 'https://web-ext-storage.dcloud.net.cn/uni-app-x/audio/ForElise.mp3'
          },
          {
            description: '不存在的音频',
            src: 'https://web-ext-storage.dcloud.net.cn/uni-app-x/audio/invalid_url.mp3'
          },
          {
            description: '错误路径',
            src: '../static/test-audio/ForElise22.mp3'
          },
        ] as Array<AudioPath>
      }
    },
    onReady() {
      this._audioContext = uni.createInnerAudioContext();
      this._audioContext!.onPlay(() => {
        console.log('开始播放', " at pages/API/create-inner-audio-context/inner-audio-path.uvue:64");
      });
      this._audioContext!.onEnded(() => {
        console.log('播放结束', " at pages/API/create-inner-audio-context/inner-audio-path.uvue:67");
        this.isPlaying = false;
      });
      this._audioContext!.onError((err) => {
        this.isPlaying = false;
        console.log('err', err, " at pages/API/create-inner-audio-context/inner-audio-path.uvue:72");
      });


      const fileManager = uni.getFileSystemManager()
      fileManager.mkdir({
        dirPath: uni.env.CACHE_PATH + 'uni-audio/test',
        recursive: true,
        success: (res) => {
          fileManager.copyFile({
            srcPath: '/static/test-audio/ForElise.mp3',
            destPath: this.nativePath,
            success: () => {
              console.log("copy成功： ", res, " at pages/API/create-inner-audio-context/inner-audio-path.uvue:85")
            }
          })
        },
        fail: (err) => {
          console.log("创建路径失败： ", err.errMsg, " at pages/API/create-inner-audio-context/inner-audio-path.uvue:90")
          if (err.errMsg.includes("file already exists")) {
            console.log("已经包含该路径", " at pages/API/create-inner-audio-context/inner-audio-path.uvue:92")
            fileManager.copyFile({
              srcPath: '/static/test-audio/ForElise.mp3',
              destPath: this.nativePath,
              success: (res) => {
                console.log("copy成功： ", res, " at pages/API/create-inner-audio-context/inner-audio-path.uvue:97")
              },
              fail: (err) => {
                console.log("copy失败： ", err, " at pages/API/create-inner-audio-context/inner-audio-path.uvue:100")
              }
            })
          }
        }
      })

    },

    onUnload() {
      if (this._audioContext != null) {
        this.pause();
        this._audioContext!.destroy()
      }
    },
    methods: {
      pause() {
        this._audioContext!.pause();
        this.isPlaying = false;
      },
      play(audioUrl : string, index : number) {
        console.log(index, audioUrl, " at pages/API/create-inner-audio-context/inner-audio-path.uvue:121");
        if (this.isPlaying && this.playIndex == index) {
          this.pause();
          return;
        }

        if (audioUrl == 'env') {
          audioUrl = this.nativePath
        }


        this.playIndex = index
        this._audioContext!.src = audioUrl;
        this._audioContext!.play();
        this.isPlaying = true;
      }
    }
  })

export default __sfc__
function GenPagesAPICreateInnerAudioContextInnerAudioPathRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode(Fragment, null, [
    createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap uni-common-mt" }), [
      createElementVNode("view", utsMapOf({ class: "uni-title" }), [
        createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "音频路径示例")
      ]),
      createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.supportPaths, (item, index, __index, _cached): any => {
        return createElementVNode("view", utsMapOf({
          class: "formats",
          key: index
        }), [
          createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), toDisplayString(item.description), 1 /* TEXT */),
          createElementVNode("image", utsMapOf({
            class: "icon-play",
            src: (_ctx.isPlaying && _ctx.playIndex==index)?'/static/pause.png':'/static/play.png',
            onClick: () => {_ctx.play(item.src,index)}
          }), null, 8 /* PROPS */, ["src", "onClick"])
        ])
      }), 128 /* KEYED_FRAGMENT */)
    ])
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesAPICreateInnerAudioContextInnerAudioPathStyles = [utsMapOf([["formats", padStyleMapOf(utsMapOf([["alignItems", "center"]]))], ["icon-play", padStyleMapOf(utsMapOf([["width", 60], ["height", 60], ["marginTop", 10], ["marginRight", 10], ["marginBottom", 10], ["marginLeft", 10]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
