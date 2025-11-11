
  import { ItemType } from '@/components/enum-data/enum-data-types';
  type VideoInfoForTest = { __$originalPosition?: UTSSourceMapPosition<"VideoInfoForTest", "pages/API/compress-video/compress-video.uvue", 38, 8>;
    width: number;
    height: number;
    isSizeReduce: boolean;
  }
  const __sfc__ = defineComponent({
    data() {
      return {
        title: "compressVideo",
        beforeCompressVideoInfo: "",
        afterCompressVideoInfo: "",
        beforeCompressPath: "",
        afterCompressPath: "",
        beforeCoverImagePath: "",
        afterCoverImagePath: "",
        quality: null as string | null,
        bitrate: null as number | null,
        fps: null as number | null,
        resolution: null as number | null,
        qualityItemTypes: [{ "value": 0, "name": "low(低)" }, { "value": 1, "name": "medium(中)" }, { "value": 2, "name": "high(高)" }] as ItemType[],
        qualityItems: ["low", "medium", "high"],
        // 自动化测试
        videoInfoForTest: null as VideoInfoForTest | null,
        videoSrcForTest: '/static/test-video/10second-demo.mp4',
        videoSrcForTestWidth: 0,
        videoSrcForTestHeight: 0
      }
    },
    methods: {
      compressVideo() {
        if (this.beforeCompressPath == "") {
          uni.showToast({
            title: "请先选择视频",
            icon: "error"
          });
          return;
        }
        uni.showLoading({
          title: "视频压缩中"
        });
        uni.compressVideo({
          src: this.beforeCompressPath,
          quality: this.quality,
          resolution: this.resolution,
          success: (res) => {
            console.log("compressVideo success", JSON.stringify(res), " at pages/API/compress-video/compress-video.uvue:83");
            this.afterCompressPath = res.tempFilePath;
            uni.showToast({
              title: "压缩成功",
              icon: null
            });
            uni.getVideoInfo({
              src: res.tempFilePath,
              success: (_res) => {
                this.afterCompressVideoInfo = `视频画面方向: ${_res.orientation}\n视频格式: ${_res.type}\n视频长度: ${_res.duration}s\n视频大小: ${_res.size}KB\n视频宽度: ${_res.width}\n视频高度: ${_res.height}\n视频帧率: ${_res.fps}fps\n视频码率: ${_res.bitrate}kbps`;

                this.afterCompressVideoInfo = this.afterCompressVideoInfo + `\n视频字节大小: ${_res.byteSize}B\n视频首帧图片路径: ${_res.thumbTempFilePath}`
                if(_res.thumbTempFilePath != null) {
                this.afterCoverImagePath = _res.thumbTempFilePath!
                }

              }
            });
          },
          fail: (err) => {
            uni.showModal({
              title: "压缩视频失败",
              content: JSON.stringify(err),
              showCancel: false
            });
          },
          complete: (_) => {
            uni.hideLoading();
          }
        });
      },
      chooseVideo() {
        uni.chooseVideo({
          sourceType: ["album"],
          compressed: false,
          success: (res) => {
            this.beforeCompressPath = res.tempFilePath;
            uni.getVideoInfo({
              src: res.tempFilePath,
              success: (_res) => {
                this.beforeCompressVideoInfo = `视频画面方向: ${_res.orientation}\n视频格式: ${_res.type}\n视频长度: ${_res.duration}s\n视频大小: ${_res.size}KB\n视频宽度: ${_res.width}\n视频高度: ${_res.height}\n视频帧率: ${_res.fps}fps\n视频码率: ${_res.bitrate}kbps`;

                this.beforeCompressVideoInfo = this.beforeCompressVideoInfo + `\n视频字节大小: ${_res.byteSize}B\n视频首帧图片路径: ${_res.thumbTempFilePath}`
                if(_res.thumbTempFilePath != null) {
                this.beforeCoverImagePath = _res.thumbTempFilePath!
                }

              }
            });
          }
        });
      },
      onQualityChange(value : number) {
        this.quality = this.qualityItems[value];
      },
      onResolutionChange(event : UniSliderChangeEvent) {
        this.resolution = event.detail.value;
      },
      testCompressVideo() {
        let beforeCompressSize : number, afterComoressSize : number;
        uni.compressVideo({
          src: this.videoSrcForTest,
          quality: 'medium',
          success: (res) => {
            uni.getVideoInfo({
              src: this.videoSrcForTest,
              success: (_res) => {
                beforeCompressSize = Math.trunc(_res.size);
                this.videoSrcForTestWidth = _res.width
                this.videoSrcForTestHeight = _res.height
                uni.getVideoInfo({
                  src: res.tempFilePath,
                  success: (__res) => {
                    afterComoressSize = Math.trunc(__res.size);
                    this.videoInfoForTest = {
                      "width": __res.width,
                      "height": __res.height,
                      "isSizeReduce": afterComoressSize < beforeCompressSize
                    } as VideoInfoForTest;
                  },
                  fail(err) {
                    console.log('>>>>>> 压缩失败', err.errMsg, " at pages/API/compress-video/compress-video.uvue:164")
                  }
                });
              }
            });
          },
          fail: (_) => {
            this.videoInfoForTest = null;
          }
        });
      }
    }
  })

export default __sfc__
function GenPagesAPICompressVideoCompressVideoRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)
const _component_enum_data = resolveEasyComponent("enum-data",_easycom_enum_data)
const _component_slider = resolveComponent("slider")

  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
    createElementVNode("view", null, [
      createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
        createElementVNode("video", utsMapOf({
          class: "video",
          src: _ctx.beforeCompressPath,
          controls: true,
          poster: _ctx.beforeCoverImagePath
        }), null, 8 /* PROPS */, ["src", "poster"]),
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "压缩前视频信息")
        ]),
        createElementVNode("text", null, toDisplayString(_ctx.beforeCompressVideoInfo), 1 /* TEXT */),
        createElementVNode("video", utsMapOf({
          class: "video",
          src: _ctx.afterCompressPath,
          controls: true,
          poster: _ctx.afterCoverImagePath
        }), null, 8 /* PROPS */, ["src", "poster"]),
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "压缩后视频信息")
        ]),
        createElementVNode("text", null, toDisplayString(_ctx.afterCompressVideoInfo), 1 /* TEXT */),
        createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
          createElementVNode("button", utsMapOf({
            type: "primary",
            onClick: _ctx.chooseVideo
          }), "从相册中选取待压缩的视频", 8 /* PROPS */, ["onClick"])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
          createElementVNode("button", utsMapOf({
            type: "primary",
            onClick: _ctx.compressVideo
          }), "压缩视频", 8 /* PROPS */, ["onClick"])
        ]),
        createVNode(_component_enum_data, utsMapOf({
          title: "压缩质量",
          items: _ctx.qualityItemTypes,
          onChange: _ctx.onQualityChange
        }), null, 8 /* PROPS */, ["items", "onChange"]),
        createElementVNode("view", utsMapOf({ class: "uni-common-mt" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title uni-title-text" }), "相对于原视频的分辨率比例，取值范围(0, 1]"),
          createVNode(_component_slider, utsMapOf({
            min: 0.1,
            max: 1,
            step: 0.1,
            "show-value": true,
            onChange: _ctx.onResolutionChange
          }), null, 8 /* PROPS */, ["onChange"])
        ])
      ])
    ])
  ], 4 /* STYLE */)
}
const GenPagesAPICompressVideoCompressVideoStyles = [utsMapOf([["video", padStyleMapOf(utsMapOf([["alignSelf", "center"]]))], ["image-container", padStyleMapOf(utsMapOf([["flexDirection", "row"]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
import _easycom_enum_data from '@/components/enum-data/enum-data.vue'
