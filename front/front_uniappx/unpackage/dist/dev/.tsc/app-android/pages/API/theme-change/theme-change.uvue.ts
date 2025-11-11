
  const __sfc__ = defineComponent({
    data() {
      return {
        osThemeChangeId: 0,
        appThemeChangeId: 0,
        osTheme: "light" as string,
        appTheme: "light" as string,
        originalTheme: "light" as string,
        current: 0,
        items: [
          "light",
          "dark",
          "auto"
        ] as string[]
      }
    },
    methods: {
      bindOsThemeChange() : number {
        //注册osTheme变化监听
        return uni.onOsThemeChange((res : OsThemeChangeResult) => {
          this.osTheme = res.osTheme
        })
      },
      bindAppThemeChange() : number {

        //注册appTheme变化监听
        return uni.onAppThemeChange((res : AppThemeChangeResult) => {
          this.appTheme = res.appTheme
        })






      },
      radioChange(e : UniRadioGroupChangeEvent) {
        const theme = e.detail.value
        this.setAppTheme(theme)
        uni.showToast({
          icon: 'none',
          title: '当前选中:' + theme,
        })
      },
      setAppTheme(value : string) {
        uni.setAppTheme({
          theme: value as 'light' | 'dark' | 'auto',
          success: function () {
            console.log("设置appTheme为", value, "成功", " at pages/API/theme-change/theme-change.uvue:82")
          },
          fail: function (e : IAppThemeFail) {
            console.log("设置appTheme为", value, "失败,原因:", e.errMsg, " at pages/API/theme-change/theme-change.uvue:85")
          }
        })
      }
    },
    onReady() {
      uni.getSystemInfo({
        success: (res : GetSystemInfoResult) => {

          this.osTheme = res.osTheme!
          this.originalTheme = res.appTheme!
          this.appTheme = res.appTheme == "auto" ? res.osTheme! : res.appTheme!
          this.current = this.items.indexOf(res.appTheme!)




        }
      })

      this.osThemeChangeId = this.bindOsThemeChange()

      this.appThemeChangeId = this.bindAppThemeChange()
    },
    onUnload() {
      //注销监听

      uni.offAppThemeChange(this.appThemeChangeId)
      uni.offOsThemeChange(this.osThemeChangeId)




      //暂时屏蔽 避免5.1安卓设备自动化测试不过
      // uni.showToast({
      //   "position": "bottom",
      //   "title": "已停止监听主题切换"
      // })
    }
  })

export default __sfc__
function GenPagesAPIThemeChangeThemeChangeRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_radio = resolveComponent("radio")
const _component_radio_group = resolveComponent("radio-group")

  return createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
    createElementVNode("view", utsMapOf({ class: "uni-common-mt item-box" }), [
      createElementVNode("text", null, "osTheme:"),
      createElementVNode("text", utsMapOf({ id: "theme" }), toDisplayString(_ctx.osTheme), 1 /* TEXT */)
    ]),
    createElementVNode("view", utsMapOf({ class: "uni-common-mt item-box" }), [
      createElementVNode("text", null, "应用当前主题:"),
      createElementVNode("text", utsMapOf({ id: "theme" }), toDisplayString(_ctx.appTheme), 1 /* TEXT */)
    ]),
    createElementVNode("view", null, [
      createElementVNode("view", utsMapOf({ class: "uni-title uni-common-mt" }), [
        createElementVNode("text", utsMapOf({ class: "uni-title-text" }), " 修改appTheme主题（此处仅为演示API，本应用并未完整适配暗黑模式） ")
      ])
    ]),
    createElementVNode("view", utsMapOf({ class: "uni-list uni-common-pl" }), [
      createVNode(_component_radio_group, utsMapOf({
        onChange: _ctx.radioChange,
        class: "radio-group"
      }), utsMapOf({
        default: withSlotCtx((): any[] => [
          createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.items, (item, index, __index, _cached): any => {
            return createVNode(_component_radio, utsMapOf({
              class: normalizeClass(["uni-list-cell uni-list-cell-pd radio", index < _ctx.items.length - 1 ? 'uni-list-cell-line' : '']),
              key: item,
              value: item,
              checked: index === _ctx.current
            }), utsMapOf({
              default: withSlotCtx((): any[] => [toDisplayString(item)]),
              _: 2 /* DYNAMIC */
            }), 1032 /* PROPS, DYNAMIC_SLOTS */, ["class", "value", "checked"])
          }), 128 /* KEYED_FRAGMENT */)
        ]),
        _: 1 /* STABLE */
      }), 8 /* PROPS */, ["onChange"])
    ])
  ])
}
const GenPagesAPIThemeChangeThemeChangeStyles = [utsMapOf([["item-box", padStyleMapOf(utsMapOf([["display", "flex"], ["flexDirection", "row"], ["justifyContent", "space-between"]]))], ["uni-list-cell", padStyleMapOf(utsMapOf([["justifyContent", "flex-start"]]))]])]
