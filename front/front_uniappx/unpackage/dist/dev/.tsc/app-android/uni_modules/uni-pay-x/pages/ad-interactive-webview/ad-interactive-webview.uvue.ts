
	const __sfc__ = defineComponent({
		data() {
			return {
				url: '' as string | null
			}
		},
		onLoad(options) {
			let url = options["url"] as string | null;
			if (url != null) {
				this.url = UTSAndroid.consoleDebugError(decodeURIComponent(url), " at uni_modules/uni-pay-x/pages/ad-interactive-webview/ad-interactive-webview.uvue:15");
			}
		}
	})

export default __sfc__
function GenUniModulesUniPayXPagesAdInteractiveWebviewAdInteractiveWebviewRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("web-view", utsMapOf({ src: _ctx.url }), null, 8 /* PROPS */, ["src"])
}
const GenUniModulesUniPayXPagesAdInteractiveWebviewAdInteractiveWebviewStyles = []
