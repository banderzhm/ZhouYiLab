
  const __sfc__ = defineComponent({
    data() {
      return {
        mode: "center",//aspectFit
        snapImage: "/static/uni.png",
        // for testing
        completeTriggered: false
      }
    },
    methods: {
      takeSnapshotClick() {
        const view = uni.getElementById('snapshot-content')!
        view.takeSnapshot({
          success: (res) => {
            console.log('takeSnapshot success', res.tempFilePath, " at pages/API/element-takesnapshot/element-takesnapshot.uvue:30")
            this.snapImage = res.tempFilePath
            this.mode = 'widthFix'
            uni.showToast({
              title: '截图成功，路径：' + res.tempFilePath,
              icon: "none"
            })
          },
          fail: (res) => {
            console.log('takeSnapshot fail', res, " at pages/API/element-takesnapshot/element-takesnapshot.uvue:39")
            uni.showToast({
              icon: 'error',
              title: '截图失败'
            })
          },
          complete: (res) => {
            this.completeTriggered = true
            console.log('takeSnapshot complete', res, " at pages/API/element-takesnapshot/element-takesnapshot.uvue:47")
          }
        })
      },
      saveToAlbum(e : TouchEvent) {
        // console.log(e.currentTarget!.getAttribute("src"));
        let filePath : string = e.currentTarget!.getAttribute("src") as string
        uni.showActionSheet({
          itemList: ["保存"],
          success: res => {
            // console.log(res.tapIndex);
            if (res.tapIndex == 0) {
              uni.saveImageToPhotosAlbum({
                filePath: filePath,
                success() {
                  uni.showToast({
                    position: "center",
                    icon: "none",
                    title: "图片保存成功，请到手机相册查看"
                  })
                },
                fail(e) {
                  uni.showModal({
                    content: "保存相册失败，errCode：" + e.errCode + "，errMsg：" + e.errMsg + "，errSubject：" + e.errSubject,
                    showCancel: false
                  });
                }
              })
            }
          },
          fail: () => { },
          complete: () => { }
        });
      }
    }
  })

export default __sfc__
function GenPagesAPIElementTakesnapshotElementTakesnapshotRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode("view", utsMapOf({ id: "snapshot-content" }), [
    createVNode(_component_page_head, utsMapOf({ title: "对本页面根view截图" })),
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
      createElementVNode("text", null, "this is text")
    ]),
    createElementVNode("button", utsMapOf({
      class: "uni-btn btn-TakeSnapshot",
      type: "primary",
      onClick: _ctx.takeSnapshotClick
    }), " 点击截图并替换显示下方图片 ", 8 /* PROPS */, ["onClick"]),
    createElementVNode("image", utsMapOf({
      style: normalizeStyle(utsMapOf({"margin-left":"auto","margin-right":"auto","margin-top":"20px","width":"90%"})),
      src: _ctx.snapImage,
      mode: _ctx.mode,
      onLongpress: _ctx.saveToAlbum
    }), null, 44 /* STYLE, PROPS, NEED_HYDRATION */, ["src", "mode", "onLongpress"])
  ])
}
const GenPagesAPIElementTakesnapshotElementTakesnapshotStyles = []

import _easycom_page_head from '@/components/page-head/page-head.vue'
