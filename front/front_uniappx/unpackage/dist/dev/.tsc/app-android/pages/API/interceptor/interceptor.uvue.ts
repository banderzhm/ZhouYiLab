
  const navigateToInterceptor = {
    invoke: function (options : NavigateToOptions) {
      console.log('拦截 navigateTo 接口传入参数为：', options, " at pages/API/interceptor/interceptor.uvue:21")
      const url = './page2'
      uni.showToast({
        title: `重定向到页面:${url}`
      })
      options.url = url
    },
    success: function (res : NavigateToSuccess) {
      console.log('拦截 navigateTo 接口 success 返回参数为：', res, " at pages/API/interceptor/interceptor.uvue:29")
    },
    fail: function (err : NavigateToFail) {
      console.log('拦截 navigateTo 接口 fail 返回参数为：', err, " at pages/API/interceptor/interceptor.uvue:32")
    },
    complete: function (res : NavigateToComplete) {
      console.log('拦截 navigateTo 接口 complete 返回参数为：', res, " at pages/API/interceptor/interceptor.uvue:35")
    }
  } as AddInterceptorOptions

  const switchTabInterceptor = {
    invoke: function (options : SwitchTabOptions) {
      console.log('拦截 switchTab 接口传入参数为：', options, " at pages/API/interceptor/interceptor.uvue:41")
      options.url = '/pages/tabBar/API'
    },
    success: function (res : SwitchTabSuccess) {
      console.log('拦截 switchTab 接口 success 返回参数为：', res, " at pages/API/interceptor/interceptor.uvue:45")
    },
    fail: function (err : SwitchTabFail) {
      console.log('拦截 switchTab 接口 fail 返回参数为：', err, " at pages/API/interceptor/interceptor.uvue:48")
    },
    complete: function (res : SwitchTabComplete) {
      console.log('拦截 switchTab 接口 complete 返回参数为：', res, " at pages/API/interceptor/interceptor.uvue:51")
    }
  } as AddInterceptorOptions

  const __sfc__ = defineComponent({
    data() {
      return {
        msg: "会跳转到测试页面1"
      }
    },
    beforeUnmount() {
      // 移除 navigateTo 所有拦截器
      uni.removeInterceptor('navigateTo')
      uni.removeInterceptor('switchTab')
    },
    methods: {
      addInterceptor() {
        uni.addInterceptor('navigateTo', navigateToInterceptor)
        uni.showToast({
          title: '页面跳转/切换tabbar已拦截'
        })
        this.msg = "，路由被劫持到测试页面2"
      },
      removeInterceptor() {
        uni.removeInterceptor('navigateTo', navigateToInterceptor)
        uni.showToast({
          title: '拦截器已移除'
        })
        this.msg = "会跳转到测试页面1"
      },
      addSwitchTabInterceptor() {
        uni.addInterceptor('switchTab', switchTabInterceptor)
      },
      removeSwitchTabInterceptor() {
        uni.removeInterceptor('switchTab', switchTabInterceptor)
      },
      navigateTo() {
        uni.navigateTo({
          url: './page1',
          success(res) {
            console.log('res:', res, " at pages/API/interceptor/interceptor.uvue:91")
          },
          fail(err) {
            console.error('err:', err, " at pages/API/interceptor/interceptor.uvue:94")
          },
          complete(res) {
            console.log('res:', res, " at pages/API/interceptor/interceptor.uvue:97")
          }
        })
      },
      switchTab() {
        uni.switchTab({
          url: '/pages/tabBar/component',
          success(res) {
            console.log('res:', res, " at pages/API/interceptor/interceptor.uvue:105")
          },
          fail(err) {
            console.error('err:', err, " at pages/API/interceptor/interceptor.uvue:108")
          },
          complete(res) {
            console.log('res:', res, " at pages/API/interceptor/interceptor.uvue:111")
          }
        })
      }
    }
  })

export default __sfc__
function GenPagesAPIInterceptorInterceptorRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_navigator = resolveComponent("navigator")

  return createElementVNode("view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("button", utsMapOf({ onClick: _ctx.addInterceptor }), "添加路由拦截器", 8 /* PROPS */, ["onClick"]),
    createElementVNode("button", utsMapOf({ onClick: _ctx.removeInterceptor }), "移除路由拦截器", 8 /* PROPS */, ["onClick"]),
    createElementVNode("text", null, "点击下方按钮" + toDisplayString(_ctx.msg), 1 /* TEXT */),
    createElementVNode("button", utsMapOf({ onClick: _ctx.navigateTo }), "navigatorTo API跳转到测试页面", 8 /* PROPS */, ["onClick"]),
    createVNode(_component_navigator, utsMapOf({ url: "./page1" }), utsMapOf({
      default: withSlotCtx((): any[] => [
        createElementVNode("button", utsMapOf({ class: "navigatorButton" }), "navigator组件跳转到测试页面")
      ]),
      _: 1 /* STABLE */
    })),
    createElementVNode("button", utsMapOf({ onClick: _ctx.addSwitchTabInterceptor }), "添加switchTab拦截器", 8 /* PROPS */, ["onClick"]),
    createElementVNode("button", utsMapOf({ onClick: _ctx.removeSwitchTabInterceptor }), "移除switchTab拦截器", 8 /* PROPS */, ["onClick"]),
    createElementVNode("button", utsMapOf({ onClick: _ctx.switchTab }), "switchTab API", 8 /* PROPS */, ["onClick"])
  ], 4 /* STYLE */)
}
const GenPagesAPIInterceptorInterceptorStyles = []
