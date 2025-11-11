
	const __sfc__ = defineComponent({
		data() {
			return {
				fullscreenElement: null as UniElement | null,
				isFullscreen: false,
			}

		},
		onReady() {
			this.fullscreenElement = uni.getElementById('fullscreen') as UniElement
		},
		methods: {
			getCurrentPage() : UniPage {
				const pages = getCurrentPages()
				return pages[pages.length - 1]
			},
			requestfullscreen() {
				if (this.isFullscreen) {
					const page = this.getCurrentPage()
					page.exitFullscreen({
						success: () => {
						},
						fail: () => {
						},
						complete: () => {
						}
					})
				} else {
					this.fullscreenElement?.requestFullscreen({
						success: () => { },
						fail: () => { },
						complete: () => { }
					})
				}
				this.isFullscreen = !this.isFullscreen
			},
			exitfullscreen() {

			}
		}
	})

export default __sfc__
function GenPagesAPIElementRequestFullscreenElementRequestFullscreenBugsRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", null, [
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"padding":"8px"}))
    }), [
      createElementVNode("view", utsMapOf({
        class: "position-error",
        id: "fullscreen",
        onClick: _ctx.requestfullscreen
      }), [
        createElementVNode("text", utsMapOf({
          style: normalizeStyle(utsMapOf({"color":"white"}))
        }), "测试position：fixed在安卓上的bug", 4 /* STYLE */)
      ], 8 /* PROPS */, ["onClick"])
    ], 4 /* STYLE */)
  ])
}
const GenPagesAPIElementRequestFullscreenElementRequestFullscreenBugsStyles = [utsMapOf([["position-error", padStyleMapOf(utsMapOf([["width", 200], ["height", 200], ["position", "fixed"], ["backgroundColor", "#A52A2A"]]))]])]
