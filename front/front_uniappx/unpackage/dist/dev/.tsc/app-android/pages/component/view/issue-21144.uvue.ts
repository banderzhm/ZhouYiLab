
	const __sfc__ = defineComponent({
		data() {
			return {
        scrollTop:0
			}
		},
		methods: {
			checkTestView():boolean {
				let ele = uni.getElementById('testView') as UniElement
				console.log(ele.getBoundingClientRect().y, " at pages/component/view/issue-21144.uvue:19")
        // TODO 鸿蒙低版本偏差稍大
				return ele.getBoundingClientRect().y > 98
			}
		}
	})

export default __sfc__
function GenPagesComponentViewIssue21144Render(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"})),
    "scroll-top": _ctx.scrollTop
  }), [
    createElementVNode("view", utsMapOf({
      id: "testView",
      class: "btn-plus"
    })),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"width":"100%","height":"2000px"})),
      onClick: _ctx.checkTestView
    }), null, 12 /* STYLE, PROPS */, ["onClick"])
  ], 12 /* STYLE, PROPS */, ["scroll-top"])
}
const GenPagesComponentViewIssue21144Styles = [utsMapOf([["btn-plus", padStyleMapOf(utsMapOf([["position", "fixed"], ["top", 100], ["left", 10], ["width", 50], ["height", 50], ["backgroundColor", "#FF0000"], ["boxShadow", "0px 0px 0px rgba(0, 0, 0, 1)"]]))]])]
