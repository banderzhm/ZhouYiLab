
  import {
    state,
    setLifeCycleNum
  } from '@/store/index.uts'

  type Location = { __$originalPosition?: UTSSourceMapPosition<"Location", "pages/API/choose-location/choose-location.uvue", 39, 8>;
    latitude: string[]
    longitude: string[]
  }
  const __sfc__ = defineComponent({
    data() {
      return {
        title: 'chooseLocation',
        hasLocation: false,
        location: {
          latitude: [],
          longitude: []
        } as Location,
        locationName: '',
        locationAddress: '',
        dialogPagesNum: -1,
        hoverLocation: false,
        hoverKeyword: false,
        hoverPayload: false
      }
    },
    onShow() {
      console.log("Page Show", " at pages/API/choose-location/choose-location.uvue:61");
      // 自动化测试
      setLifeCycleNum(state.lifeCycleNum + 1)
    },
    onHide() {
      console.log("Page Hide", " at pages/API/choose-location/choose-location.uvue:66");
      // 自动化测试
      setLifeCycleNum(state.lifeCycleNum - 1)
    },
    methods: {
      chooseLocation: function () {
        let chooseLocationOptions = {
          success: (res) => {
            console.log('chooseLocation success', res, " at pages/API/choose-location/choose-location.uvue:74")
            this.hasLocation = true
            this.location = this.formatLocation(res.longitude, res.latitude)
            this.locationName = res.name
            this.locationAddress = res.address
          }
        } as ChooseLocationOptions
        if (this.hoverLocation) {
          chooseLocationOptions.latitude = 39.908823
          chooseLocationOptions.longitude = 116.39747
        }
        if (this.hoverKeyword) {
          chooseLocationOptions.keyword = '公园'
        }

        if (this.hoverPayload) {
          chooseLocationOptions.payload = {
            token: 'xxx'
          }
        }

        uni.chooseLocation(chooseLocationOptions)
        // 自动化测试
        setTimeout(() => {
          this.test()
        }, 500)
      },
      formatLocation: function(longitude:number, latitude:number):Location {
        const longitudeArr = longitude.toString().split('.')
        const latitudeArr = latitude.toString().split('.')
        if(longitudeArr.length>1){
          longitudeArr[1] = longitudeArr[1].substring(0,2)
        }
        if(latitudeArr.length>1){
          latitudeArr[1] = latitudeArr[1].substring(0,2)
        }
        return {
          longitude: longitudeArr,
          latitude: latitudeArr
        }
      },
      clear: function () {
        this.hasLocation = false
      },
      changeLocationBoolean(checked : boolean) {
        this.hoverLocation = checked
      },
      changeKeywordBoolean(checked : boolean) {
        this.hoverKeyword = checked
      },
      changePayloadBoolean(checked : boolean) {
        this.hoverPayload = checked
      },





      // 自动化测试
      test() {
        const pages = getCurrentPages()
        const page = pages[pages.length - 1]

        const dialogPages = page.getDialogPages()
        this.dialogPagesNum = dialogPages.length

      },
      // 自动化测试
      setLifeCycleNum(value : number) {
        setLifeCycleNum(value)
      },
      // 自动化测试
      getLifeCycleNum() : number {
        return state.lifeCycleNum
      },
    }
  })

export default __sfc__
function GenPagesAPIChooseLocationChooseLocationRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)
const _component_boolean_data = resolveEasyComponent("boolean-data",_easycom_boolean_data)

  return createElementVNode("view", null, [
    createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
      createElementVNode("view", utsMapOf({ class: "uni-column uni-container align-center" }), [
        createElementVNode("text", utsMapOf({ class: "uni-hello-text" }), "位置信息"),
        isTrue(!_ctx.hasLocation)
          ? createElementVNode("text", utsMapOf({
              key: 0,
              class: "uni-title-text uni-common-mt"
            }), "未选择位置")
          : createCommentVNode("v-if", true),
        isTrue(_ctx.hasLocation)
          ? createElementVNode("view", utsMapOf({
              key: 1,
              class: "align-center"
            }), [
              createElementVNode("text", utsMapOf({ class: "uni-common-mt" }), toDisplayString(_ctx.locationName), 1 /* TEXT */),
              createElementVNode("text", utsMapOf({ class: "uni-common-mt" }), toDisplayString(_ctx.locationAddress), 1 /* TEXT */),
              _ctx.location.latitude.length>1
                ? createElementVNode("view", utsMapOf({
                    key: 0,
                    class: "uni-common-mt"
                  }), [
                    createElementVNode("text", null, "E: " + toDisplayString(_ctx.location.longitude[0]) + "°" + toDisplayString(_ctx.location.longitude[1]) + "′", 1 /* TEXT */),
                    createElementVNode("text", null, "\nN: " + toDisplayString(_ctx.location.latitude[0]) + "°" + toDisplayString(_ctx.location.latitude[1]) + "′", 1 /* TEXT */)
                  ])
                : createCommentVNode("v-if", true)
            ])
          : createCommentVNode("v-if", true)
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
        createElementVNode("text", utsMapOf({ class: "tips" }), "注意：\n1. Web和App需要正确配置地图服务商的Key并且保证Key的权限和余额足够，才能正常选择位置\n2. 若没有关联uniCloud空间，则只能全屏地图选点，不能根据POI选择位置\n3. payload参数会原样透传给uni-map-co，可用于用户鉴权"),
        createVNode(_component_boolean_data, utsMapOf({
          defaultValue: false,
          title: "是否指定位置为天安门",
          onChange: _ctx.changeLocationBoolean
        }), null, 8 /* PROPS */, ["onChange"]),
        createVNode(_component_boolean_data, utsMapOf({
          defaultValue: false,
          title: "是否携带keyword参数",
          onChange: _ctx.changeKeywordBoolean
        }), null, 8 /* PROPS */, ["onChange"]),
        createVNode(_component_boolean_data, utsMapOf({
          defaultValue: false,
          title: "是否携带payload参数",
          onChange: _ctx.changePayloadBoolean
        }), null, 8 /* PROPS */, ["onChange"]),
        createElementVNode("button", utsMapOf({
          class: "uni-btn",
          type: "primary",
          onClick: _ctx.chooseLocation
        }), "选择位置", 8 /* PROPS */, ["onClick"]),
        createElementVNode("button", utsMapOf({
          class: "uni-btn",
          onClick: _ctx.clear
        }), "清空", 8 /* PROPS */, ["onClick"])
      ])
    ])
  ])
}
const GenPagesAPIChooseLocationChooseLocationStyles = [utsMapOf([["page-body-info", padStyleMapOf(utsMapOf([["paddingBottom", 0], ["height", 220]]))], ["align-center", padStyleMapOf(utsMapOf([["alignItems", "center"]]))], ["tips", padStyleMapOf(utsMapOf([["fontSize", 12], ["marginTop", 15], ["marginRight", 0], ["marginBottom", 15], ["marginLeft", 0], ["opacity", 0.8]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
import _easycom_boolean_data from '@/components/boolean-data/boolean-data.vue'
