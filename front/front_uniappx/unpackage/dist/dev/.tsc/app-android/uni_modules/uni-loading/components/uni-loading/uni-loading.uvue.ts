
	import LoadingCircle from './loading-circle.uvue'
  // TODO 性能问题，其他类型暂时不对外开放
	// import Icon from './icon.uvue'
	// import UniIcons from '@/uni_modules/uni-icons/components/uni-icons/uni-icons.uvue'

	// import { img_load_base } from './load-img.uts'

	/**
	 * Loading-x 加载动画
	 * @description 用于数据加载场景，使用loading等待数据返回
	 * @tutorial https://ext.dcloud.net.cn/plugin?name=uni-loading-x
	 * @property {Boolean} loading 是否显示加载动画，默认：true
	 * @property {String} type = [snow|circle|icon] 加载图标显示，默认：circle
	 * 	@value snow 显示雪花加载动画，性能问题暂时不支持
	 * 	@value circle 显示圆形加载动画
	 * 	@value icon 自定义图标 ,暂时不支持
	 * @property {String} background 加载遮罩颜色，支持 rgba 色值，默认：rgba(255,255,255,0.6)
	 * @property {String} color 加载图标以及加载文字颜色，默认：#333333
	 * @property {String} size 加载图标大小，默认：20
	 * @property {String} text 加载文本，默认：不显示
	 * @property {String} iconType 自定义图标类型，参考 uni-icons ，当前版本暂不支持
	 */

	const __sfc__ = defineComponent({
		name: "uni-loading",
		components: { LoadingCircle },
		props: {
			loading: {
				type: Boolean,
				default: true,
			},
			type: {
				type: String,
				default: ''
			},
			iconType: {
				type: String,
				default: 'gear-filled'
			},
			size: {
				type: Number,
				default: 0
			},
			text: {
				type: String,
				default: ''
			},
			background: {
				type: String,
				default: 'rgba(255,255,255,0.6)'
			},
			color: {
				type: String,
				default: '#333'
			}
		},
		data() {
			return {};
		},
		computed: {
			loadWidth() : number {
				let width = this.size
				if (width == 0) {
					return 20
				}
				return width
			},
			styles() : string {
				return `width:${this.loadWidth}px;height:${this.loadWidth}px;`
			}
		},
		created() {},
		methods: {}
	})

export default __sfc__
function GenUniModulesUniLoadingComponentsUniLoadingUniLoadingRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_LoadingCircle = resolveComponent("LoadingCircle")

  return createElementVNode("view", utsMapOf({
    class: normalizeClass(["uni-loading-main", utsMapOf({'load-inline':_ctx.$slots['default'] == null})])
  }), [
    isTrue(_ctx.loading)
      ? createElementVNode(Fragment, utsMapOf({ key: 0 }), [
          renderSlot(_ctx.$slots, "default"),
          _ctx.$slots['default'] == null
            ? createElementVNode(Fragment, utsMapOf({ key: 0 }), [
                createVNode(_component_LoadingCircle, utsMapOf({
                  speed: 16,
                  size: _ctx.loadWidth,
                  color: _ctx.color
                }), null, 8 /* PROPS */, ["size", "color"]),
                isTrue(_ctx.text)
                  ? createElementVNode("text", utsMapOf({
                      key: 0,
                      class: "inline-text",
                      style: normalizeStyle( utsMapOf({ color: _ctx.color }))
                    }), toDisplayString(_ctx.text), 5 /* TEXT, STYLE */)
                  : createCommentVNode("v-if", true)
              ], 64 /* STABLE_FRAGMENT */)
            : createElementVNode("view", utsMapOf({
                key: 1,
                class: "uni-loading-mask",
                style: normalizeStyle(utsMapOf({backgroundColor:_ctx.background}))
              }), [
                createVNode(_component_LoadingCircle, utsMapOf({
                  speed: 16,
                  size: _ctx.loadWidth,
                  color: _ctx.color
                }), null, 8 /* PROPS */, ["size", "color"]),
                isTrue(_ctx.text)
                  ? createElementVNode("text", utsMapOf({
                      key: 0,
                      class: "block-text",
                      style: normalizeStyle( utsMapOf({ color: _ctx.color }))
                    }), toDisplayString(_ctx.text), 5 /* TEXT, STYLE */)
                  : createCommentVNode("v-if", true)
              ], 4 /* STYLE */)
        ], 64 /* STABLE_FRAGMENT */)
      : renderSlot(_ctx.$slots, "default", utsMapOf({ key: 1 }))
  ], 2 /* CLASS */)
}
export type UniLoadingComponentPublicInstance = InstanceType<typeof __sfc__>;
const GenUniModulesUniLoadingComponentsUniLoadingUniLoadingStyles = [utsMapOf([["uni-loading-main", utsMapOf([["", utsMapOf([["position", "relative"]])], [".load-inline", utsMapOf([["display", "flex"], ["flexDirection", "row"], ["alignItems", "center"]])]])], ["block-text", padStyleMapOf(utsMapOf([["marginTop", 8], ["fontSize", 14]]))], ["inline-text", padStyleMapOf(utsMapOf([["marginLeft", 8], ["fontSize", 14]]))], ["uni-loading-mask", padStyleMapOf(utsMapOf([["position", "absolute"], ["width", "100%"], ["height", "100%"], ["top", 0], ["left", 0], ["display", "flex"], ["justifyContent", "center"], ["alignItems", "center"], ["backgroundColor", "rgba(0,0,0,0.3)"], ["zIndex", 2]]))], ["uni-load", padStyleMapOf(utsMapOf([["display", "flex"], ["flexDirection", "row"], ["justifyContent", "center"], ["alignItems", "center"]]))], ["load-text", padStyleMapOf(utsMapOf([["fontSize", 14], ["color", "#ffffff"], ["marginTop", 12]]))], ["image", utsMapOf([[".uni-load ", utsMapOf([["width", "100%"], ["height", "100%"]])]])], ["load-image", padStyleMapOf(utsMapOf([["width", "100%"], ["height", "100%"]]))], ["load-ani", padStyleMapOf(utsMapOf([["transitionProperty", "transform"], ["transitionDuration", "0.1s"], ["transitionTimingFunction", "linear"], ["transform", "rotate(0deg)"]]))], ["@TRANSITION", utsMapOf([["load-ani", utsMapOf([["property", "transform"], ["duration", "0.1s"], ["timingFunction", "linear"]])]])]])]
