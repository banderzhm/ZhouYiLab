
  const __sfc__ = defineComponent({
    data() {
      return {
        title: 'saveVideoToPhotosAlbum',
        src: '/static/test-video/10second-demo.mp4',
        // 自动化测试
        success: false
      }
    },
    methods: {
      saveVideo() {
        uni.saveVideoToPhotosAlbum({
          filePath: this.src,
          success: (_) => {
            console.log("saveVideoToPhotosAlbum success", " at pages/API/save-video-to-photos-album/save-video-to-photos-album.uvue:30");
            uni.showToast({
              position: "center",
              icon: "none",
              title: "视频保存成功，请到手机相册查看"
            });
            this.success = true;
          },
          fail: (err) => {
            uni.showModal({
              title: "保存视频到相册失败",
              content: JSON.stringify(err),
              showCancel: false
            });
            this.success = false;
          }
        });
      }
    }
  })

export default __sfc__
function GenPagesAPISaveVideoToPhotosAlbumSaveVideoToPhotosAlbumRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
      createElementVNode("video", utsMapOf({
        class: "video",
        src: _ctx.src,
        controls: true
      }), null, 8 /* PROPS */, ["src"]),
      createElementVNode("button", utsMapOf({
        type: "primary",
        class: "margin-top-10",
        onClick: _ctx.saveVideo
      }), "将视频保存到手机相册", 8 /* PROPS */, ["onClick"])
    ])
  ], 4 /* STYLE */)
}
const GenPagesAPISaveVideoToPhotosAlbumSaveVideoToPhotosAlbumStyles = [utsMapOf([["video", padStyleMapOf(utsMapOf([["alignSelf", "center"]]))], ["margin-top-10", padStyleMapOf(utsMapOf([["marginTop", 10]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
