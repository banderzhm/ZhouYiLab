
  type GetLocationType = 'wgs84' | 'gcj02'
  export type LocationItem = { __$originalPosition?: UTSSourceMapPosition<"LocationItem", "pages/API/get-location/get-location.uvue", 61, 15>; id : string, name : string, provider ?: UniProvider }
  export type ItemType = { __$originalPosition?: UTSSourceMapPosition<"ItemType", "pages/API/get-location/get-location.uvue", 62, 15>; value : GetLocationType, name : GetLocationType }

  const __sfc__ = defineComponent({
    data() {
      return {
        title: 'get-location',
        altitudeSelect: false,
        isHighAccuracySelect: false,
        geocodeSelect: false,
        exeRet: '',
        items: [
          {
            value: 'wgs84',
            name: 'wgs84'
          },
          {
            value: 'gcj02',
            name: 'gcj02'
          }
        ] as ItemType[],
        providerList: [] as LocationItem[],
        current: 0,
        currentProvider: 0,
        jest_provider: '',
        jest_type: 'wgs84' as GetLocationType,
        jest_isAltitude: false,
        jest_isGeocode: false,
        jest_isHighAccuracy: false,
        jest_altitude: -1000,
        jest_longitude: 200,
        jest_latitude: 100,
        jest_address: '',
        jest_errCode: 0,
        jest_complete: false
      }
    },
    onLoad: function () {

      this.getProvider()

    },
    methods: {
      getProvider() {


        let provider = uni.getProviderSync({
          service: "location",
        } as GetProviderSyncOptions)
        console.log(provider, " at pages/API/get-location/get-location.uvue:110")
        provider.providerObjects.forEach((value : UniProvider) => {
          var currentProvider = value
          // if (value.id == 'system') {
          //   currentProvider = value as UniLocationSystemProvider
          // } else if (value.id == 'tencent') {
          //   currentProvider = value as UniLocationTencentProvider
          // }
          this.providerList.push({
            name: currentProvider.description,
            id: currentProvider.id,
            provider: currentProvider
          } as LocationItem);
        })

        this.providerList.forEach((value, index) => {
          if (value.id == "system") {
            this.currentProvider = index
          }
        })

      },
      altitudeChange: function (e : UniSwitchChangeEvent) {
        this.altitudeSelect = e.detail.value
      },
      geocodeChange: function (e : UniSwitchChangeEvent) {
        this.geocodeSelect = e.detail.value
      },
      highAccuracySelectChange: function (e : UniSwitchChangeEvent) {
        this.isHighAccuracySelect = e.detail.value
      },
      radioChange(e : UniRadioGroupChangeEvent) {
        for (let i = 0; i < this.items.length; i++) {
          if (this.items[i].value === e.detail.value) {
            this.current = i;
            break;
          }
        }
      },
      radioChangePV(e : UniRadioGroupChangeEvent) {
        for (let i = 0; i < this.providerList.length; i++) {
          if (this.providerList[i].id === e.detail.value) {
            this.currentProvider = i;
            break;
          }
        }
        if (e.detail.value == "system") {
          this.current = 0
        } else if (e.detail.value == "tencent") {
          this.current = 1
        }
      },
      getLocationTap: function () {

        if (this.providerList.length == 0) {
          uni.showToast({
            title: '未获取到provider，请确定基座中包含location功能',
            icon: "error"
          })
          console.log("未获取到provider，请确定基座中包含location功能", " at pages/API/get-location/get-location.uvue:169")
          return
        }

        uni.showLoading({
          title: '定位中'
        })
        uni.getLocation(({

          provider: this.providerList[this.currentProvider].id,

          type: this.items[this.current].value,
          altitude: this.altitudeSelect,
          isHighAccuracy: this.isHighAccuracySelect,
          geocode: this.geocodeSelect,
          success: (res : any) => {
            uni.hideLoading()
            this.exeRet = JSON.stringify(res)
          },
          fail: (res : any) => {
            uni.hideLoading()
            this.exeRet = JSON.stringify(res)
          },
          complete: (res : any) => {
            uni.hideLoading()
            this.exeRet = JSON.stringify(res)
          }
        }));
      },

      // 仅用于自动化测试
      jestGetLocation() {
        this.jest_complete = false
        this.jest_errCode = 0
        uni.getLocation(({

          provider: this.jest_provider,

          type: this.jest_type,
          altitude: this.jest_isAltitude,
          isHighAccuracy: this.jest_isHighAccuracy,
          geocode: this.jest_isGeocode,
          success: (res) => {
            if (res.address != null) {
              this.jest_address = res.address!
            }
            this.jest_longitude = res.longitude
            this.jest_latitude = res.latitude
            this.jest_altitude = res.altitude
            this.jest_complete = true
          },
          fail: (err) => {
            this.jest_errCode = err.errCode
            this.jest_complete = true
          }
        }));
      }
    }
  })

export default __sfc__
function GenPagesAPIGetLocationGetLocationRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)
const _component_radio = resolveComponent("radio")
const _component_radio_group = resolveComponent("radio-group")
const _component_switch = resolveComponent("switch")

  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"padding":"4px"}))
    }), [
      createElementVNode("text", utsMapOf({ class: "hello-text" }), " 定位功能默认调用操作系统定位API实现。也支持三方SDK定位\n 部分老款Android手机因gms问题可能导致无法使用系统定位。\n Web、Android、iOS平台，gcj国标、逆地理信息等功能需调用腾讯定位。")
    ], 4 /* STYLE */),
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap uni-common-mt" }), [
      createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), "定位服务商provider(如系统定位，腾讯定位等)"),
      createElementVNode("view", utsMapOf({
        class: "uni-list",
        style: normalizeStyle(utsMapOf({"margin-bottom":"20px"}))
      }), [
        createVNode(_component_radio_group, utsMapOf({ onChange: _ctx.radioChangePV }), utsMapOf({
          default: withSlotCtx((): any[] => [
            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.providerList, (item, index, __index, _cached): any => {
              return createVNode(_component_radio, utsMapOf({
                class: normalizeClass(["uni-list-cell uni-list-cell-pd", index < _ctx.providerList.length - 1 ? 'uni-list-cell-line' : '']),
                key: item.id,
                value: item.id,
                checked: index === _ctx.currentProvider
              }), utsMapOf({
                default: withSlotCtx((): any[] => [toDisplayString(item.name)]),
                _: 2 /* DYNAMIC */
              }), 1032 /* PROPS, DYNAMIC_SLOTS */, ["class", "value", "checked"])
            }), 128 /* KEYED_FRAGMENT */)
          ]),
          _: 1 /* STABLE */
        }), 8 /* PROPS */, ["onChange"])
      ], 4 /* STYLE */),
      createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), "定位类型"),
      createElementVNode("view", utsMapOf({ class: "uni-list" }), [
        createVNode(_component_radio_group, utsMapOf({ onChange: _ctx.radioChange }), utsMapOf({
          default: withSlotCtx((): any[] => [
            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.items, (item, index, __index, _cached): any => {
              return createVNode(_component_radio, utsMapOf({
                class: normalizeClass(["uni-list-cell uni-list-cell-pd", index < _ctx.items.length - 1 ? 'uni-list-cell-line' : '']),
                key: item.value,
                value: item.value,
                checked: index === _ctx.current
              }), utsMapOf({
                default: withSlotCtx((): any[] => [toDisplayString(item.name)]),
                _: 2 /* DYNAMIC */
              }), 1032 /* PROPS, DYNAMIC_SLOTS */, ["class", "value", "checked"])
            }), 128 /* KEYED_FRAGMENT */)
          ]),
          _: 1 /* STABLE */
        }), 8 /* PROPS */, ["onChange"])
      ]),
      createElementVNode("view", utsMapOf({
        class: "uni-list-cell uni-list-cell-pd",
        style: normalizeStyle(utsMapOf({"margin-top":"20px"}))
      }), [
        createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), "高度信息"),
        createVNode(_component_switch, utsMapOf({
          checked: _ctx.altitudeSelect,
          onChange: _ctx.altitudeChange
        }), null, 8 /* PROPS */, ["checked", "onChange"])
      ], 4 /* STYLE */),
      createElementVNode("view", utsMapOf({ class: "uni-list-cell uni-list-cell-pd" }), [
        createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), "开启高精度定位"),
        createVNode(_component_switch, utsMapOf({
          checked: _ctx.isHighAccuracySelect,
          onChange: _ctx.highAccuracySelectChange
        }), null, 8 /* PROPS */, ["checked", "onChange"])
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-list-cell uni-list-cell-pd" }), [
        createElementVNode("view", utsMapOf({ class: "uni-list-cell-db" }), "是否解析地址信息"),
        createVNode(_component_switch, utsMapOf({
          checked: _ctx.geocodeSelect,
          onChange: _ctx.geocodeChange
        }), null, 8 /* PROPS */, ["checked", "onChange"])
      ]),
      createElementVNode("view", utsMapOf({ class: "get-location-result" }), toDisplayString(_ctx.exeRet), 1 /* TEXT */),
      createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
        createElementVNode("button", utsMapOf({
          class: "uni-btn",
          type: "default",
          onClick: _ctx.getLocationTap
        }), " 获取定位 ", 8 /* PROPS */, ["onClick"])
      ])
    ])
  ], 4 /* STYLE */)
}
const GenPagesAPIGetLocationGetLocationStyles = []

import _easycom_page_head from '@/components/page-head/page-head.vue'
