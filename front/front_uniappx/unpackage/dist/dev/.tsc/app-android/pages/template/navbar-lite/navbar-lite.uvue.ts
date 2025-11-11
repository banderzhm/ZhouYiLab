
  import { state, setLifeCycleNum } from '@/store/index.uts'
  const __sfc__ = defineComponent({
    data() {
      return {
        title: 'Hello uni-app',
        isLeft: false,
        navigationBarTextColor: '#000',
        indexView: 0,
        inputBottom: '0px'
      }
    },
    methods: {
      onClick() {
        this.isLeft = !this.isLeft
      },
      setNavigationBarColor1() {
        uni.setNavigationBarColor({
          frontColor: '#ffffff',
          backgroundColor: '#0000',
          success: () => {
            this.navigationBarTextColor = '#fff'
            console.log('setNavigationBarColor success', " at pages/template/navbar-lite/navbar-lite.uvue:69")
            this.setLifeCycleNum(state.lifeCycleNum + 1)
          },
          fail: () => {
            console.log('setNavigationBarColor fail', " at pages/template/navbar-lite/navbar-lite.uvue:73")
            this.setLifeCycleNum(state.lifeCycleNum - 1)
          },
          complete: () => {
            console.log('setNavigationBarColor complete', " at pages/template/navbar-lite/navbar-lite.uvue:77")
            this.setLifeCycleNum(state.lifeCycleNum + 1)
          }
        })
      },
      setNavigationBarColor2() {
        uni.setNavigationBarColor({
          frontColor: '#000000',
          backgroundColor: '#0000',
          success: () => {
            this.navigationBarTextColor = '#000'
            console.log('setNavigationBarColor success', " at pages/template/navbar-lite/navbar-lite.uvue:88")
            this.setLifeCycleNum(state.lifeCycleNum + 1)
          },
          fail: () => {
            console.log('setNavigationBarColor fail', " at pages/template/navbar-lite/navbar-lite.uvue:92")
            this.setLifeCycleNum(state.lifeCycleNum - 1)
          },
          complete: () => {
            console.log('setNavigationBarColor complete', " at pages/template/navbar-lite/navbar-lite.uvue:96")
            this.setLifeCycleNum(state.lifeCycleNum + 1)
          }
        })
      },
      ChangeView(e:UniRadioGroupChangeEvent){
        this.indexView = parseInt(e.detail.value)
      },
      onInputBlur(_:UniInputBlurEvent) {
        this.inputBottom = '0px';
      },
      onInputKeyboardChange(e:UniInputKeyboardHeightChangeEvent) {
        let height = e.detail.height;





        this.inputBottom = `${height}px`;
      },
      // 自动化测试
      getLifeCycleNum() : number {
        return state.lifeCycleNum
      },
      // 自动化测试
      setLifeCycleNum(num : number) {
        setLifeCycleNum(num)
      }
    },
    onReady() {
    },
    onResize() {
    }
  })

export default __sfc__
function GenPagesTemplateNavbarLiteNavbarLiteRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_uni_navbar_lite = resolveEasyComponent("uni-navbar-lite",_easycom_uni_navbar_lite)
const _component_radio = resolveComponent("radio")
const _component_radio_group = resolveComponent("radio-group")

  return createElementVNode("view", utsMapOf({ class: "content" }), [
    createVNode(_component_uni_navbar_lite, utsMapOf({
      "status-bar": true,
      stat: "",
      title: _ctx.title,
      "is-left": _ctx.isLeft,
      "text-color": _ctx.navigationBarTextColor
    }), null, 8 /* PROPS */, ["title", "is-left", "text-color"]),
    createElementVNode("view", utsMapOf({
      class: "content-item",
      onClick: _ctx.onClick
    }), [
      createElementVNode("text", null, "点击此处，将标题切换为" + toDisplayString(_ctx.isLeft?'居中':'左侧') + "显示", 1 /* TEXT */)
    ], 8 /* PROPS */, ["onClick"]),
    createElementVNode("view", utsMapOf({
      class: "content-item",
      onClick: _ctx.setNavigationBarColor1
    }), [
      createElementVNode("text", null, "设置自定义导航栏前景色白色")
    ], 8 /* PROPS */, ["onClick"]),
    createElementVNode("view", utsMapOf({
      class: "content-item",
      onClick: _ctx.setNavigationBarColor2
    }), [
      createElementVNode("text", null, "设置自定义导航栏前景色黑色")
    ], 8 /* PROPS */, ["onClick"]),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"align-items":"center","height":"60px"}))
    }), [
      createElementVNode("text", null, "测试输入框上推页面"),
      createVNode(_component_radio_group, utsMapOf({
        onChange: _ctx.ChangeView,
        style: normalizeStyle(utsMapOf({"flex-direction":"row"}))
      }), utsMapOf({
        default: withSlotCtx((): any[] => [
          createVNode(_component_radio, utsMapOf({
            value: "0",
            checked: true
          }), utsMapOf({
            default: withSlotCtx((): any[] => [
              createElementVNode("text", null, "scroll-view")
            ]),
            _: 1 /* STABLE */
          })),
          createVNode(_component_radio, utsMapOf({ value: "1" }), utsMapOf({
            default: withSlotCtx((): any[] => [
              createElementVNode("text", null, "list-view")
            ]),
            _: 1 /* STABLE */
          })),
          createVNode(_component_radio, utsMapOf({ value: "2" }), utsMapOf({
            default: withSlotCtx((): any[] => [
              createElementVNode("text", null, "web-view")
            ]),
            _: 1 /* STABLE */
          }))
        ]),
        _: 1 /* STABLE */
      }), 8 /* PROPS */, ["onChange", "style"])
    ], 4 /* STYLE */),
    _ctx.indexView==0
      ? createElementVNode("scroll-view", utsMapOf({
          key: 0,
          class: "scroll-view",
          "scroll-y": true,
          "refresher-enabled": false
        }), [
          createElementVNode(Fragment, null, RenderHelpers.renderList(10, (item, __key, __index, _cached): any => {
            return createElementVNode("view", utsMapOf({ class: "content-item" }), [
              createElementVNode("view", utsMapOf({ class: "cell-item" }), [
                createElementVNode("text", utsMapOf({ class: "text" }), "内容：" + toDisplayString(item), 1 /* TEXT */),
                createElementVNode("input", utsMapOf({
                  class: "text",
                  style: normalizeStyle(utsMapOf({"margin-top":"8px"})),
                  placeholder: "备注输入框:"
                }), null, 4 /* STYLE */)
              ])
            ])
          }), 64 /* STABLE_FRAGMENT */)
        ])
      : createCommentVNode("v-if", true),
    _ctx.indexView==1
      ? createElementVNode("list-view", utsMapOf({
          key: 1,
          class: "scroll-view"
        }), [
          createElementVNode(Fragment, null, RenderHelpers.renderList(10, (item, __key, __index, _cached): any => {
            return createElementVNode("list-item", utsMapOf({ class: "content-item" }), [
              createElementVNode("view", utsMapOf({ class: "cell-item" }), [
                createElementVNode("text", utsMapOf({ class: "text" }), "列表项内容：" + toDisplayString(item), 1 /* TEXT */),
                createElementVNode("input", utsMapOf({
                  class: "text",
                  style: normalizeStyle(utsMapOf({"margin-top":"8px"})),
                  placeholder: "备注输入框:"
                }), null, 4 /* STYLE */)
              ])
            ])
          }), 64 /* STABLE_FRAGMENT */)
        ])
      : createCommentVNode("v-if", true),
    _ctx.indexView==2
      ? createElementVNode("web-view", utsMapOf({
          key: 2,
          src: "/hybrid/html/local.html",
          id: "webv",
          class: "scroll-view"
        }))
      : createCommentVNode("v-if", true),
    createElementVNode("view", utsMapOf({
      class: "bottomInput",
      style: normalizeStyle(utsMapOf({bottom: _ctx.inputBottom}))
    }), [
      createElementVNode("input", utsMapOf({
        id: "input",
        style: normalizeStyle(utsMapOf({"background-color":"white"})),
        placeholder: "滚动视图外底部输入框，焦点时手动控制显示位置",
        "adjust-position": false,
        onBlur: _ctx.onInputBlur,
        onKeyboardheightchange: _ctx.onInputKeyboardChange
      }), null, 44 /* STYLE, PROPS, NEED_HYDRATION */, ["onBlur", "onKeyboardheightchange"])
    ], 4 /* STYLE */)
  ])
}
const GenPagesTemplateNavbarLiteNavbarLiteStyles = [utsMapOf([["content", padStyleMapOf(utsMapOf([["display", "flex"], ["flexDirection", "column"], ["flex", 1]]))], ["scroll-view", padStyleMapOf(utsMapOf([["flex", 1], ["backgroundColor", "#f5f5f5"], ["paddingTop", 5], ["paddingRight", 0], ["paddingBottom", 5], ["paddingLeft", 0]]))], ["content-item", padStyleMapOf(utsMapOf([["paddingTop", 15], ["paddingRight", 15], ["paddingBottom", 15], ["paddingLeft", 15], ["marginTop", 5], ["marginRight", 10], ["marginBottom", 5], ["marginLeft", 10], ["backgroundColor", "#ffffff"], ["borderTopLeftRadius", 5], ["borderTopRightRadius", 5], ["borderBottomRightRadius", 5], ["borderBottomLeftRadius", 5]]))], ["cell-item", padStyleMapOf(utsMapOf([["display", "flex"], ["flexDirection", "column"]]))], ["text", padStyleMapOf(utsMapOf([["fontSize", 14], ["color", "#666666"]]))], ["bottomInput", padStyleMapOf(utsMapOf([["position", "relative"], ["zIndex", 1000], ["paddingTop", 0], ["paddingRight", 5], ["paddingBottom", 0], ["paddingLeft", 5], ["marginBottom", "var(--uni-safe-area-inset-bottom)"]]))]])]

import _easycom_uni_navbar_lite from '@/components/uni-navbar-lite/uni-navbar-lite.uvue'
