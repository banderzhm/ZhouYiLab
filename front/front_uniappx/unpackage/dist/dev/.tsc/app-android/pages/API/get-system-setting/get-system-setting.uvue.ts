
  const __sfc__ = defineComponent({
    data() {
      return {
        title: 'getSystemSetting',
        bluetoothEnabled: "",
        locationEnabled: "",
        wifiEnabled: "",
        deviceOrientation: ""
      }
    },
    onUnload: function () {
    },
    methods: {
      getSystemSetting: function () {
        const res = uni.getSystemSetting();
        this.bluetoothEnabled = (res.bluetoothEnabled ?? false) ? "开启" : "关闭";
        this.locationEnabled = res.locationEnabled ? "开启" : "关闭";
        this.wifiEnabled = (res.wifiEnabled ?? false) ? "开启" : "关闭";
        this.deviceOrientation = res.deviceOrientation

        if (res.bluetoothError != null) {
          this.bluetoothEnabled = "无蓝牙权限"
        }

        if (res.wifiError != null) {
          this.wifiEnabled = "无WiFi权限"
        }
      }
    }
  })

export default __sfc__
function GenPagesAPIGetSystemSettingGetSystemSettingRender(this: InstanceType<typeof __sfc__>): any | null {
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
            }), "蓝牙的系统开关", 4 /* STYLE */)
          ]),
          createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), [
            createElementVNode("input", utsMapOf({
              type: "text",
              disabled: true,
              placeholder: "未获取",
              value: _ctx.bluetoothEnabled
            }), null, 8 /* PROPS */, ["value"])
          ])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
          createElementVNode("view", utsMapOf({ class: "uni-pd" }), [
            createElementVNode("view", utsMapOf({
              class: "uni-label",
              style: normalizeStyle(utsMapOf({"width":"180px"}))
            }), "地理位置的系统开关", 4 /* STYLE */)
          ]),
          createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), [
            createElementVNode("input", utsMapOf({
              type: "text",
              disabled: true,
              placeholder: "未获取",
              value: _ctx.locationEnabled
            }), null, 8 /* PROPS */, ["value"])
          ])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
          createElementVNode("view", utsMapOf({ class: "uni-pd" }), [
            createElementVNode("view", utsMapOf({
              class: "uni-label",
              style: normalizeStyle(utsMapOf({"width":"180px"}))
            }), "Wi-Fi 的系统开关", 4 /* STYLE */)
          ]),
          createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), [
            createElementVNode("input", utsMapOf({
              type: "text",
              disabled: true,
              placeholder: "未获取",
              value: _ctx.wifiEnabled
            }), null, 8 /* PROPS */, ["value"])
          ])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
          createElementVNode("view", utsMapOf({ class: "uni-pd" }), [
            createElementVNode("view", utsMapOf({
              class: "uni-label",
              style: normalizeStyle(utsMapOf({"width":"180px"}))
            }), "设备方向", 4 /* STYLE */)
          ]),
          createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), [
            createElementVNode("input", utsMapOf({
              type: "text",
              disabled: true,
              placeholder: "未获取",
              value: _ctx.deviceOrientation
            }), null, 8 /* PROPS */, ["value"])
          ])
        ])
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
        createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
          createElementVNode("button", utsMapOf({
            type: "primary",
            onClick: _ctx.getSystemSetting
          }), "获取系统设置", 8 /* PROPS */, ["onClick"])
        ])
      ])
    ])
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesAPIGetSystemSettingGetSystemSettingStyles = [utsMapOf([["uni-pd", padStyleMapOf(utsMapOf([["paddingLeft", 15]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
