
  import { ItemType } from '@/components/enum-data/enum-data-types';
  type Camera = "back" | "front"
  type Source = "album" | "camera"
  const __sfc__ = defineComponent({
    data() {
      return {
        title: "chooseVideo",
        src: "",
        orientationTypeItemTypes: [{ "value": 0, "name": "竖屏" }, { "value": 1, "name": "横屏" }, { "value": 2, "name": "自动" }] as ItemType[],
        sourceTypeItemTypes: [{ "value": 0, "name": "从相册中选择视频" }, { "value": 1, "name": "拍摄视频" }, { "value": 2, "name": "从相册中选择视频或拍摄视频" }] as ItemType[],
        sourceTypeItems: [["album"], ["camera"], ["album", "camera"]] as Source[][],
        cameraItemTypes: [{ "value": 0, "name": "后置摄像头" }, { "value": 1, "name": "前置摄像头" }] as ItemType[],
        albumModeTypes: [{ "value": 0, "name": "自定义视频选择器" }, { "value": 1, "name": "系统视频选择器" }] as ItemType[],
        albumModeTypeItems: ["custom", "system"],
        cameraItems: ["back", "front"] as Camera[],
        sourceType: ["album", "camera"] as Source[],
        orientationType: "portrait",
        orientationTypeItems: ["portrait", "landscape", "auto"],
        compressed: true,
        maxDuration: 60,
        camera: "back" as Camera,
        videoInfo: "",
        videoCoverImage: "",
        albumMode: "custom"
      }
    },
    onHide() {
      console.log("Page Hide", " at pages/API/choose-video/choose-video.uvue:63");
    },
    methods: {
      chooseVideo() {
        uni.chooseVideo({
          sourceType: this.sourceType,

          compressed: this.compressed,
          pageOrientation: this.orientationType,

          maxDuration: this.maxDuration,

          albumMode: this.albumMode,

          camera: this.camera,
          success: (res) => {
            console.log("chooseVideo success", JSON.stringify(res), " at pages/API/choose-video/choose-video.uvue:79");
            this.src = res.tempFilePath;
            this.videoInfo = `视频长度: ${res.duration}s\n视频大小: ${Math.ceil(res.size)}KB\n视频宽度: ${res.width}\n视频高度: ${res.height}\n`;

            uni.getVideoInfo({
              src: res.tempFilePath,
              success: (_res) => {
                if(_res.thumbTempFilePath != null) {
                this.videoCoverImage = _res.thumbTempFilePath!
                }
              }
            });

          },
          fail: (err) => {
            uni.showModal({
              title: "选择视频失败",
              content: JSON.stringify(err),
              showCancel: false
            });
          }
        });
      },
      onOrientationTypeChange(value : number) {
        this.orientationType = this.orientationTypeItems[value];
      },
      onSourceTypeChange(value : number) {
        this.sourceType = this.sourceTypeItems[value];
      },
      onCompressedChange(value : boolean) {
        this.compressed = value;
      },
      onMaxDurationConfirm(value : number) {
        this.maxDuration = value;
      },
      onCameraChange(value : number) {
        this.camera = this.cameraItems[value];
      },
      onAlbumModeChange(value : number) {
        this.albumMode = this.albumModeTypeItems[value]
      }
    }
  })

export default __sfc__
function GenPagesAPIChooseVideoChooseVideoRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)
const _component_enum_data = resolveEasyComponent("enum-data",_easycom_enum_data)
const _component_input_data = resolveEasyComponent("input-data",_easycom_input_data)
const _component_boolean_data = resolveEasyComponent("boolean-data",_easycom_boolean_data)

  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
      createElementVNode("video", utsMapOf({
        class: "video",
        src: _ctx.src,
        controls: true,
        poster: _ctx.videoCoverImage
      }), null, 8 /* PROPS */, ["src", "poster"]),
      createElementVNode("view", utsMapOf({ class: "uni-title" }), [
        createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "视频信息")
      ]),
      createElementVNode("text", null, toDisplayString(_ctx.videoInfo), 1 /* TEXT */),
      createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
        createElementVNode("button", utsMapOf({
          type: "primary",
          onClick: _ctx.chooseVideo
        }), "选取视频", 8 /* PROPS */, ["onClick"])
      ]),
      createVNode(_component_enum_data, utsMapOf({
        title: "视频来源",
        items: _ctx.sourceTypeItemTypes,
        onChange: _ctx.onSourceTypeChange
      }), null, 8 /* PROPS */, ["items", "onChange"]),
      createVNode(_component_enum_data, utsMapOf({
        title: "屏幕方向",
        items: _ctx.orientationTypeItemTypes,
        onChange: _ctx.onOrientationTypeChange
      }), null, 8 /* PROPS */, ["items", "onChange"]),
      createVNode(_component_enum_data, utsMapOf({
        title: "摄像头",
        items: _ctx.cameraItemTypes,
        onChange: _ctx.onCameraChange
      }), null, 8 /* PROPS */, ["items", "onChange"]),
      createVNode(_component_enum_data, utsMapOf({
        title: "相册模式",
        items: _ctx.albumModeTypes,
        onChange: _ctx.onAlbumModeChange
      }), null, 8 /* PROPS */, ["items", "onChange"])
    ]),
    createVNode(_component_input_data, utsMapOf({
      title: "最长拍摄时间，单位秒",
      defaultValue: "60",
      type: "number",
      onConfirm: _ctx.onMaxDurationConfirm
    }), null, 8 /* PROPS */, ["onConfirm"]),
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
      createVNode(_component_boolean_data, utsMapOf({
        title: "是否压缩（HamonyOS 不支持，推荐使用 uni.compressVideo 进行压缩）",
        defaultValue: true,
        onChange: _ctx.onCompressedChange
      }), null, 8 /* PROPS */, ["onChange"])
    ])
  ], 4 /* STYLE */)
}
const GenPagesAPIChooseVideoChooseVideoStyles = [utsMapOf([["video", padStyleMapOf(utsMapOf([["alignSelf", "center"], ["width", 300], ["height", 225]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
import _easycom_enum_data from '@/components/enum-data/enum-data.vue'
import _easycom_input_data from '@/components/input-data/input-data.vue'
import _easycom_boolean_data from '@/components/boolean-data/boolean-data.vue'
