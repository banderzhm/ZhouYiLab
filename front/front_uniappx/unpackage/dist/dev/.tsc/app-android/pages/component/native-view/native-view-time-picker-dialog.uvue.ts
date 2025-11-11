
	const __sfc__ = defineComponent({
		data() {
			return {

			}
		},
		methods: {
			onChanged(e : UniNativeViewEvent) {
			  console.log("onchanged-----", e.detail, " at pages/component/native-view/native-view-time-picker-dialog.uvue:18")
			},
      sure() {
        uni.closeDialogPage({
          dialogPage: this.$page
        })
      }
		}
	})

export default __sfc__
function GenPagesComponentNativeViewNativeViewTimePickerDialogRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_native_time_picker = resolveEasyComponent("native-time-picker",_easycom_native_time_picker)

  return createElementVNode("view", utsMapOf({ class: "dialog-container" }), [
    createVNode(_component_native_time_picker, utsMapOf({
      class: "native-time-picker",
      hour: 2,
      minute: 3,
      onChanged: _ctx.onChanged
    }), null, 8 /* PROPS */, ["onChanged"]),
    createElementVNode("button", utsMapOf({ onClick: _ctx.sure }), "确定", 8 /* PROPS */, ["onClick"])
  ])
}
const GenPagesComponentNativeViewNativeViewTimePickerDialogStyles = [utsMapOf([["native-time-picker", padStyleMapOf(utsMapOf([["marginTop", 10], ["marginRight", "auto"], ["marginBottom", 25], ["marginLeft", "auto"], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopLeftRadius", 5], ["borderTopRightRadius", 5], ["borderBottomRightRadius", 5], ["borderBottomLeftRadius", 5], ["backgroundColor", "#FFFFFF"]]))], ["dialog-container", padStyleMapOf(utsMapOf([["width", "100%"], ["height", "100%"], ["backgroundColor", "rgba(0,0,0,0.3)"], ["display", "flex"], ["justifyContent", "center"], ["alignItems", "center"]]))]])]

import _easycom_native_time_picker from '@/uni_modules/native-time-picker/components/native-time-picker/native-time-picker.uvue'
