
  const __sfc__ = defineComponent({
    data() {
      return {
        isPermissionAlertShow: false,
        permissionAlert: null as UniElement | null,
        timeoutId: -1,
        permissionListener: null as RequestPermissionListener | null
      }
    },

    onReady() {
      this.watchPermissionRRequest()
    },
    onUnload() {
      this.permissionListener?.stop()
      this.permissionListener = null
      clearTimeout(this.timeoutId)
    },
    methods: {
      watchPermissionRRequest() {
        this.permissionListener = uni.createRequestPermissionListener()
        this.permissionListener!.onConfirm((_) => {
          // TODO 目前onConfirm监听实现的在时间上不够精确，暂时需要延迟弹框，后续修复
          // TODO 这里的弹框仅为演示，实际开发中监听权限申请的代码应该在app.uvue中，弹框应全局处理，可参考https://gitcode.net/dcloud/uni-api/-/tree/master/uni_modules/uni-prompt/utssdk/app-android 代码自行封装一个uts的全局弹框
          this.timeoutId = setTimeout(() => {
            this.isPermissionAlertShow = true
          }, 100)
        })
        this.permissionListener!.onComplete((_) => {
          clearTimeout(this.timeoutId)
          this.isPermissionAlertShow = false
        })
      },
      requestPermission() {

        if (UTSAndroid.checkSystemPermissionGranted(UTSAndroid.getUniActivity()!, ["android.permission.READ_CALENDAR"])) {
          uni.showToast({
            title: "权限已经同意了，不需要再申请",
            position: "bottom"
          })
          return
        }
        UTSAndroid.requestSystemPermission(UTSAndroid.getUniActivity()!, ["android.permission.READ_CALENDAR"], (_ : boolean, p : string[]) => {
          console.log(p, " at pages/API/create-request-permission-listener/create-request-permission-listener.uvue:62")
        }, (_ : boolean, p : string[]) => {
          uni.showToast({
            title: "权限被拒绝了",
            position: "bottom"
          })
          console.log(p, " at pages/API/create-request-permission-listener/create-request-permission-listener.uvue:68")
        })

      }
    }
  })

export default __sfc__
function GenPagesAPICreateRequestPermissionListenerCreateRequestPermissionListenerRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createVNode(_component_page_head, utsMapOf({ title: "权限申请监听" })),
    createElementVNode("view", utsMapOf({
      class: "permission-alert",
      id: "permission-alert",
      style: normalizeStyle(utsMapOf({'transform':_ctx.isPermissionAlertShow ? 'translateY(0)':'translateY(-110px)'}))
    }), [
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"font-size":"20px","margin-bottom":"10px","margin-top":"5px"}))
      }), "访问日历权限申请说明：", 4 /* STYLE */),
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"color":"darkgray"}))
      }), "uni-app x正在申请访问日历权限用于演示，允许或拒绝均不会获取任何隐私信息。", 4 /* STYLE */)
    ], 4 /* STYLE */),
    createElementVNode("button", utsMapOf({
      type: "primary",
      style: normalizeStyle(utsMapOf({"margin":"10px"})),
      onClick: _ctx.requestPermission
    }), "点击申请日历权限", 12 /* STYLE, PROPS */, ["onClick"])
  ], 4 /* STYLE */)
}
const GenPagesAPICreateRequestPermissionListenerCreateRequestPermissionListenerStyles = [utsMapOf([["permission-alert", padStyleMapOf(utsMapOf([["width", "90%"], ["height", 100], ["marginTop", 10], ["marginRight", "5%"], ["marginBottom", 10], ["marginLeft", "5%"], ["position", "absolute"], ["top", 0], ["zIndex", 3], ["borderTopLeftRadius", 5], ["borderTopRightRadius", 5], ["borderBottomRightRadius", 5], ["borderBottomLeftRadius", 5], ["transitionProperty", "transform"], ["transitionDuration", "200ms"], ["backgroundColor", "#FFFFFF"], ["paddingTop", 10], ["paddingRight", 10], ["paddingBottom", 10], ["paddingLeft", 10]]))], ["@TRANSITION", utsMapOf([["permission-alert", utsMapOf([["property", "transform"], ["duration", "200ms"]])]])]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
