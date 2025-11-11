
	const __sfc__ = defineComponent({
		emits: ["close", "clickMask"],
		data() {
			return {
				isShow: false,
				isOpen: false
			}
		},
		props: {
			maskClick: {
				type: Boolean,
				default: true
			},
			type: {
				type: String,
				default: "center"
			}
		},
		watch: {
			// 设置show = true 时，如果没有 open 需要设置为 open
			isShow: {
				handler(newVal : boolean) {
					if (newVal && this.isOpen == false) {
						this.isOpen = true;
					}
				},
				immediate: true
			},
			// 设置isOpen = true 时，如果没有 isShow 需要设置为 isShow
			isOpen: {
				handler(newVal : boolean) {
					if (newVal && this.isShow == false) {
						this.isShow = true;
					}
				},
				immediate: true
			}
		},
		methods: {
			open() {
				this.isOpen = true;
			},
			clickMask() {
				if (this.maskClick == true) {
					this.$emit('clickMask');
					this.close();
				}
			},
			close() : void {
				this.isOpen = false;
				this.$emit('close');
			},
			hiden() {
				this.isShow = false;
			},
			show() {
				this.isShow = true;
			}
		}
	})

export default __sfc__
function GenUniModulesUniPayXComponentsUniPayPopupUniPayPopupRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return isTrue(_ctx.isOpen)
    ? withDirectives(createElementVNode("view", utsMapOf({
        key: 0,
        class: normalizeClass(["popup-root", 'popup-'+_ctx.type]),
        onClick: _ctx.clickMask
      }), [
        createElementVNode("view", utsMapOf({
          onClick: withModifiers(() => {}, ["stop"]),
          class: "popup-box"
        }), [
          renderSlot(_ctx.$slots, "default")
        ], 8 /* PROPS */, ["onClick"])
      ], 10 /* CLASS, PROPS */, ["onClick"]), [
        [vShow, _ctx.isShow]
      ])
    : createCommentVNode("v-if", true)
}
export type UniPayPopupComponentPublicInstance = InstanceType<typeof __sfc__>;
const GenUniModulesUniPayXComponentsUniPayPopupUniPayPopupStyles = [utsMapOf([["popup-root", padStyleMapOf(utsMapOf([["position", "fixed"], ["top", 0], ["left", 0], ["width", "100%"], ["height", "100%"], ["flex", 1], ["backgroundColor", "rgba(0,0,0,0.4)"], ["justifyContent", "center"], ["alignItems", "center"], ["zIndex", 99]]))], ["popup-box", padStyleMapOf(utsMapOf([["width", "100%"], ["justifyContent", "center"], ["alignItems", "center"]]))], ["popup-bottom", padStyleMapOf(utsMapOf([["justifyContent", "flex-end"]]))]])]
