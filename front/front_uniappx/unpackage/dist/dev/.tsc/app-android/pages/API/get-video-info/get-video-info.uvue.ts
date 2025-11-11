
  const __sfc__ = defineComponent({
    data() {
      return {
        title: "getVideoInfo",
        relativeVideoPath: "/static/test-video/10second-demo.mp4",
        relativeVideoInfo: "",
        relativeCoverImagePath: "",
        absoluteVideoPath: "",
        absoluteVideoInfo: "",
        absoluteCoverImagePath: "",
        // 自动化测试
        videoInfoForTest: null as UTSJSONObject | null
      }
    },
    onReady() {

      uni.getVideoInfo({
        src: this.relativeVideoPath,
        success: (res) => {
          console.log("getVideoInfo success", JSON.stringify(res), " at pages/API/get-video-info/get-video-info.uvue:50");
          this.relativeVideoInfo = `视频画面方向: ${res.orientation}\n视频格式: ${res.type}\n视频长度: ${res.duration}s\n视频大小: ${res.size}KB\n视频宽度: ${res.width}\n视频高度: ${res.height}\n视频帧率: ${res.fps}fps\n视频码率: ${res.bitrate}kbps`;

          this.relativeVideoInfo = this.relativeVideoInfo + `\n视频字节大小: ${res.byteSize}B\n视频首帧图片路径: ${res.thumbTempFilePath}`
          if(res.thumbTempFilePath != null) {
          this.relativeCoverImagePath = res.thumbTempFilePath!;
          }


        },
        fail: (err) => {
          uni.showModal({
            title: "获取视频信息失败",
            content: JSON.stringify(err),
            showCancel: false
          });
        }
      });

    },
    methods: {
      chooseVideo() {
        uni.chooseVideo({
          compressed: false,
          success: (res) => {
            this.absoluteVideoPath = res.tempFilePath;
            uni.getVideoInfo({
              src: res.tempFilePath,
              success: (_res) => {
                console.log("getVideoInfo success", JSON.stringify(_res), " at pages/API/get-video-info/get-video-info.uvue:79");
                this.absoluteVideoInfo = `视频画面方向: ${_res.orientation}\n视频格式: ${_res.type}\n视频长度: ${_res.duration}s\n视频大小: ${_res.size}KB\n视频宽度: ${_res.width}\n视频高度: ${_res.height}\n视频帧率: ${_res.fps}fps\n视频码率: ${_res.bitrate}kbps`;

                this.absoluteVideoInfo = this.absoluteVideoInfo + `\n视频字节大小: ${_res.byteSize}B\n视频首帧图片路径: ${_res.thumbTempFilePath}`
                if(_res.thumbTempFilePath != null) {
                this.absoluteCoverImagePath = _res.thumbTempFilePath!
                }

              },
              fail: (err) => {
                uni.showModal({
                  title: "获取视频信息失败",
                  content: JSON.stringify(err),
                  showCancel: false
                });
              }
            });
          }
        });
      },
      testGetVideoInfo() {
        uni.getVideoInfo({
          src: '/static/test-video/10second-demo.mp4',
          success: (res) => {
            this.videoInfoForTest = {
              "orientation": res.orientation,
              "type": res.type,
              "duration": Math.trunc(res.duration),
              "size": res.size,
              "width": res.width,
              "height": res.height,
              "fps": res.fps,
              "bitrate": res.bitrate
            };
          },
          fail: (_) => {
            this.videoInfoForTest = null;
          }
        });
      }
    }
  })

export default __sfc__
function GenPagesAPIGetVideoInfoGetVideoInfoRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
      createElementVNode("view", utsMapOf({ class: "uni-title" }), [
        createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "获取本地绝对路径视频信息")
      ]),
      createElementVNode("video", utsMapOf({
        class: "video",
        src: _ctx.absoluteVideoPath,
        controls: true,
        poster: _ctx.absoluteCoverImagePath
      }), null, 8 /* PROPS */, ["src", "poster"]),
      createElementVNode("text", utsMapOf({ class: "margin-top-10" }), toDisplayString(_ctx.absoluteVideoInfo), 1 /* TEXT */),
      createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
        createElementVNode("button", utsMapOf({
          type: "primary",
          onClick: _ctx.chooseVideo
        }), "拍摄视频或从相册中选择视频", 8 /* PROPS */, ["onClick"])
      ])
    ]),
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
      createElementVNode("view", utsMapOf({ class: "uni-title" }), [
        createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "获取本地相对路径视频信息")
      ]),
      createElementVNode("video", utsMapOf({
        class: "video",
        src: _ctx.relativeVideoPath,
        controls: true,
        poster: _ctx.relativeCoverImagePath
      }), null, 8 /* PROPS */, ["src", "poster"]),
      createElementVNode("text", utsMapOf({ class: "margin-top-10" }), toDisplayString(_ctx.relativeVideoInfo), 1 /* TEXT */)
    ])
  ], 4 /* STYLE */)
}
const GenPagesAPIGetVideoInfoGetVideoInfoStyles = [utsMapOf([["video", padStyleMapOf(utsMapOf([["width", "100%"]]))], ["margin-top-10", padStyleMapOf(utsMapOf([["marginTop", 10]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
