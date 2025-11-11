
  const __sfc__ = defineComponent({
    data() {
      return {
        title: 'getAppAuthorizeSetting',
        cameraAuthorized: "",
        albumAuthorized: "",
        locationAuthorized: "",
        locationAccuracy: "",
        microphoneAuthorized: "",
        bluetoothAuthorized: "",
        notificationAuthorized: "",
        notificationAlertAuthorized: "",
        notificationBadgeAuthorized: "",
        notificationSoundAuthorized: ""
      }
    },
    onUnload: function () {
    },
    methods: {
      getAppAuthorizeSetting: function () {
        const res = uni.getAppAuthorizeSetting();
        this.albumAuthorized = res.albumAuthorized;
        this.bluetoothAuthorized = res.bluetoothAuthorized;
        this.cameraAuthorized = res.cameraAuthorized;
        this.locationAuthorized = res.locationAuthorized;
        this.locationAccuracy = res.locationAccuracy ?? "unsupported";
        this.microphoneAuthorized = res.microphoneAuthorized;
        this.notificationAuthorized = res.notificationAuthorized;






      }
    }
  })

export default __sfc__
function GenPagesAPIGetAppAuthorizeSettingGetAppAuthorizeSettingRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode(Fragment, null, [
    createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
    createElementVNode("view", utsMapOf({ class: "uni-common-mt" }), [
      createElementVNode("view", utsMapOf({ class: "uni-list" }), [
        createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
          createElementVNode("view", utsMapOf({ class: "uni-pd" }), [
            createElementVNode("view", utsMapOf({
              class: "uni-label",
              style: normalizeStyle(utsMapOf({"width":"180px"}))
            }), "是否授权使用相册", 4 /* STYLE */)
          ]),
          createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), [
            createElementVNode("input", utsMapOf({
              type: "text",
              disabled: true,
              placeholder: "未获取",
              value: _ctx.albumAuthorized
            }), null, 8 /* PROPS */, ["value"])
          ])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
          createElementVNode("view", utsMapOf({ class: "uni-pd" }), [
            createElementVNode("view", utsMapOf({
              class: "uni-label",
              style: normalizeStyle(utsMapOf({"width":"180px"}))
            }), "是否授权使用蓝牙", 4 /* STYLE */)
          ]),
          createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), [
            createElementVNode("input", utsMapOf({
              type: "text",
              disabled: true,
              placeholder: "未获取",
              value: _ctx.bluetoothAuthorized
            }), null, 8 /* PROPS */, ["value"])
          ])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
          createElementVNode("view", utsMapOf({ class: "uni-pd" }), [
            createElementVNode("view", utsMapOf({
              class: "uni-label",
              style: normalizeStyle(utsMapOf({"width":"180px"}))
            }), "是否授权使用摄像头", 4 /* STYLE */)
          ]),
          createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), [
            createElementVNode("input", utsMapOf({
              type: "text",
              disabled: true,
              placeholder: "未获取",
              value: _ctx.cameraAuthorized
            }), null, 8 /* PROPS */, ["value"])
          ])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
          createElementVNode("view", utsMapOf({ class: "uni-pd" }), [
            createElementVNode("view", utsMapOf({
              class: "uni-label",
              style: normalizeStyle(utsMapOf({"width":"180px"}))
            }), "是否授权使用定位", 4 /* STYLE */)
          ]),
          createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), [
            createElementVNode("input", utsMapOf({
              type: "text",
              disabled: true,
              placeholder: "未获取",
              value: _ctx.locationAuthorized
            }), null, 8 /* PROPS */, ["value"])
          ])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
          createElementVNode("view", utsMapOf({ class: "uni-pd" }), [
            createElementVNode("view", utsMapOf({
              class: "uni-label",
              style: normalizeStyle(utsMapOf({"width":"180px"}))
            }), "定位准确度", 4 /* STYLE */)
          ]),
          createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), [
            createElementVNode("input", utsMapOf({
              type: "text",
              disabled: true,
              placeholder: "未获取",
              value: _ctx.locationAccuracy
            }), null, 8 /* PROPS */, ["value"])
          ])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
          createElementVNode("view", utsMapOf({ class: "uni-pd" }), [
            createElementVNode("view", utsMapOf({
              class: "uni-label",
              style: normalizeStyle(utsMapOf({"width":"180px"}))
            }), "是否授权使用麦克风", 4 /* STYLE */)
          ]),
          createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), [
            createElementVNode("input", utsMapOf({
              type: "text",
              disabled: true,
              placeholder: "未获取",
              value: _ctx.microphoneAuthorized
            }), null, 8 /* PROPS */, ["value"])
          ])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
          createElementVNode("view", utsMapOf({ class: "uni-pd" }), [
            createElementVNode("view", utsMapOf({
              class: "uni-label",
              style: normalizeStyle(utsMapOf({"width":"180px"}))
            }), "是否授权通知", 4 /* STYLE */)
          ]),
          createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), [
            createElementVNode("input", utsMapOf({
              type: "text",
              disabled: true,
              placeholder: "未获取",
              value: _ctx.notificationAuthorized
            }), null, 8 /* PROPS */, ["value"])
          ])
        ])
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
        createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
          createElementVNode("button", utsMapOf({
            type: "primary",
            onClick: _ctx.getAppAuthorizeSetting
          }), "获取App授权设置", 8 /* PROPS */, ["onClick"])
        ])
      ])
    ])
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesAPIGetAppAuthorizeSettingGetAppAuthorizeSettingStyles = [utsMapOf([["uni-pd", padStyleMapOf(utsMapOf([["paddingLeft", 15]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
