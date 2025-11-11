
	import { themeStore } from '../store/theme.uts'
	import type { ThemeType } from '../utils/theme.uts'
	
	const __sfc__ = defineComponent({
		data() {
			return {
				currentTheme: 'light' as ThemeType,
				enableWuxingColors: true
			}
		},
		
		onLoad() {
			this.currentTheme = themeStore.currentTheme
			this.enableWuxingColors = themeStore.enableWuxingColors
			
			// 监听主题变化
			uni.$on('themeChanged', (theme : ThemeType) => {
				this.currentTheme = theme
			})
		},
		
		onUnload() {
			uni.$off('themeChanged')
		},
		
		methods: {
			// 选择主题
			selectTheme(theme : ThemeType) {
				this.currentTheme = theme
				themeStore.setTheme(theme)
				
				// 显示提示
				uni.showToast({
					title: '主题已切换',
					icon: 'success',
					duration: 1500
				})
			},
			
			// 切换五行配色
			onWuxingColorsChange(e : any) {
				this.enableWuxingColors = e.detail.value
				themeStore.toggleWuxingColors()
			}
		}
	})

export default __sfc__
function GenComponentsThemeSettingsRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_switch = resolveComponent("switch")

  return createElementVNode("view", utsMapOf({ class: "theme-settings" }), [
    createElementVNode("view", utsMapOf({ class: "settings-header" }), [
      createElementVNode("text", utsMapOf({ class: "settings-title" }), "主题设置")
    ]),
    createElementVNode("view", utsMapOf({ class: "settings-section" }), [
      createElementVNode("text", utsMapOf({ class: "section-title" }), "选择主题"),
      createElementVNode("view", utsMapOf({ class: "theme-grid" }), [
        createElementVNode("view", utsMapOf({
          class: normalizeClass(["theme-card", utsMapOf({'theme-active': _ctx.currentTheme === 'light'})]),
          onClick: () => {_ctx.selectTheme('light')}
        }), [
          createElementVNode("view", utsMapOf({ class: "theme-preview theme-preview-light" })),
          createElementVNode("text", utsMapOf({ class: "theme-name" }), "浅色主题")
        ], 10 /* CLASS, PROPS */, ["onClick"]),
        createElementVNode("view", utsMapOf({
          class: normalizeClass(["theme-card", utsMapOf({'theme-active': _ctx.currentTheme === 'dark'})]),
          onClick: () => {_ctx.selectTheme('dark')}
        }), [
          createElementVNode("view", utsMapOf({ class: "theme-preview theme-preview-dark" })),
          createElementVNode("text", utsMapOf({ class: "theme-name" }), "深色主题")
        ], 10 /* CLASS, PROPS */, ["onClick"]),
        createElementVNode("view", utsMapOf({
          class: normalizeClass(["theme-card", utsMapOf({'theme-active': _ctx.currentTheme === 'gold'})]),
          onClick: () => {_ctx.selectTheme('gold')}
        }), [
          createElementVNode("view", utsMapOf({ class: "theme-preview theme-preview-gold" })),
          createElementVNode("text", utsMapOf({ class: "theme-name" }), "黑金主题")
        ], 10 /* CLASS, PROPS */, ["onClick"]),
        createElementVNode("view", utsMapOf({
          class: normalizeClass(["theme-card", utsMapOf({'theme-active': _ctx.currentTheme === 'green'})]),
          onClick: () => {_ctx.selectTheme('green')}
        }), [
          createElementVNode("view", utsMapOf({ class: "theme-preview theme-preview-green" })),
          createElementVNode("text", utsMapOf({ class: "theme-name" }), "护眼主题")
        ], 10 /* CLASS, PROPS */, ["onClick"]),
        createElementVNode("view", utsMapOf({
          class: normalizeClass(["theme-card", utsMapOf({'theme-active': _ctx.currentTheme === 'tech'})]),
          onClick: () => {_ctx.selectTheme('tech')}
        }), [
          createElementVNode("view", utsMapOf({ class: "theme-preview theme-preview-tech" })),
          createElementVNode("text", utsMapOf({ class: "theme-name" }), "科技主题")
        ], 10 /* CLASS, PROPS */, ["onClick"]),
        createElementVNode("view", utsMapOf({
          class: normalizeClass(["theme-card", utsMapOf({'theme-active': _ctx.currentTheme === 'minimal'})]),
          onClick: () => {_ctx.selectTheme('minimal')}
        }), [
          createElementVNode("view", utsMapOf({ class: "theme-preview theme-preview-minimal" })),
          createElementVNode("text", utsMapOf({ class: "theme-name" }), "简洁主题")
        ], 10 /* CLASS, PROPS */, ["onClick"])
      ])
    ]),
    createElementVNode("view", utsMapOf({ class: "settings-section" }), [
      createElementVNode("view", utsMapOf({ class: "setting-item" }), [
        createElementVNode("view", utsMapOf({ class: "setting-label" }), [
          createElementVNode("text", utsMapOf({ class: "label-text" }), "五行配色"),
          createElementVNode("text", utsMapOf({ class: "label-desc" }), "按五行属性显示颜色")
        ]),
        createVNode(_component_switch, utsMapOf({
          checked: _ctx.enableWuxingColors,
          onChange: _ctx.onWuxingColorsChange
        }), null, 8 /* PROPS */, ["checked", "onChange"])
      ])
    ]),
    createElementVNode("view", utsMapOf({ class: "settings-section" }), [
      createElementVNode("text", utsMapOf({ class: "section-title" }), "五行颜色预览"),
      createElementVNode("view", utsMapOf({ class: "wuxing-preview" }), [
        createElementVNode("view", utsMapOf({ class: "wuxing-item" }), [
          createElementVNode("text", utsMapOf({ class: "wuxing-mu" }), "木"),
          createElementVNode("text", utsMapOf({ class: "wuxing-label" }), "#28a745")
        ]),
        createElementVNode("view", utsMapOf({ class: "wuxing-item" }), [
          createElementVNode("text", utsMapOf({ class: "wuxing-huo" }), "火"),
          createElementVNode("text", utsMapOf({ class: "wuxing-label" }), "#dc3545")
        ]),
        createElementVNode("view", utsMapOf({ class: "wuxing-item" }), [
          createElementVNode("text", utsMapOf({ class: "wuxing-tu" }), "土"),
          createElementVNode("text", utsMapOf({ class: "wuxing-label" }), "#8b7355")
        ]),
        createElementVNode("view", utsMapOf({ class: "wuxing-item" }), [
          createElementVNode("text", utsMapOf({ class: "wuxing-jin" }), "金"),
          createElementVNode("text", utsMapOf({ class: "wuxing-label" }), "#ffd700")
        ]),
        createElementVNode("view", utsMapOf({ class: "wuxing-item" }), [
          createElementVNode("text", utsMapOf({ class: "wuxing-shui" }), "水"),
          createElementVNode("text", utsMapOf({ class: "wuxing-label" }), "#0d6efd")
        ])
      ])
    ])
  ])
}
const GenComponentsThemeSettingsStyles = [utsMapOf([["theme-settings", padStyleMapOf(utsMapOf([["backgroundColor", "#ffffff"], ["borderTopLeftRadius", 12], ["borderTopRightRadius", 12], ["borderBottomRightRadius", 12], ["borderBottomLeftRadius", 12], ["paddingTop", 20], ["paddingRight", 20], ["paddingBottom", 20], ["paddingLeft", 20], ["marginTop", 16], ["marginRight", 16], ["marginBottom", 16], ["marginLeft", 16]]))], ["settings-header", padStyleMapOf(utsMapOf([["marginBottom", 20], ["borderBottomWidth", 1], ["borderBottomStyle", "solid"], ["borderBottomColor", "#dee2e6"], ["paddingBottom", 12]]))], ["settings-title", padStyleMapOf(utsMapOf([["fontSize", 18], ["color", "#212529"]]))], ["settings-section", padStyleMapOf(utsMapOf([["marginBottom", 24]]))], ["setting-item", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["justifyContent", "space-between"], ["alignItems", "center"], ["paddingTop", 16], ["paddingRight", 0], ["paddingBottom", 16], ["paddingLeft", 0], ["borderBottomWidth", 1], ["borderBottomStyle", "solid"], ["borderBottomColor", "#f1f3f5"], ["borderBottomWidth:last-child", "medium"], ["borderBottomStyle:last-child", "none"], ["borderBottomColor:last-child", "#000000"]]))], ["setting-label", padStyleMapOf(utsMapOf([["flexDirection", "column"], ["flex", 1]]))], ["label-text", padStyleMapOf(utsMapOf([["fontSize", 16], ["color", "#212529"], ["marginBottom", 4]]))], ["label-desc", padStyleMapOf(utsMapOf([["fontSize", 13], ["color", "#6c757d"]]))], ["section-title", padStyleMapOf(utsMapOf([["fontSize", 14], ["color", "#495057"], ["marginBottom", 12]]))], ["theme-grid", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["flexWrap", "wrap"], ["justifyContent", "space-between"]]))], ["theme-card", padStyleMapOf(utsMapOf([["width", "48%"], ["marginBottom", 12], ["paddingTop", 12], ["paddingRight", 12], ["paddingBottom", 12], ["paddingLeft", 12], ["backgroundColor", "#ffffff"], ["borderTopWidth", 2], ["borderRightWidth", 2], ["borderBottomWidth", 2], ["borderLeftWidth", 2], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#e0e0e0"], ["borderRightColor", "#e0e0e0"], ["borderBottomColor", "#e0e0e0"], ["borderLeftColor", "#e0e0e0"], ["borderTopLeftRadius", 8], ["borderTopRightRadius", 8], ["borderBottomRightRadius", 8], ["borderBottomLeftRadius", 8], ["flexDirection", "column"], ["alignItems", "center"]]))], ["theme-active", padStyleMapOf(utsMapOf([["borderTopColor", "#28a745"], ["borderRightColor", "#28a745"], ["borderBottomColor", "#28a745"], ["borderLeftColor", "#28a745"], ["boxShadow", "0 2px 8px rgba(40, 167, 69, 0.2)"]]))], ["theme-preview", padStyleMapOf(utsMapOf([["width", "100%"], ["height", 60], ["borderTopLeftRadius", 6], ["borderTopRightRadius", 6], ["borderBottomRightRadius", 6], ["borderBottomLeftRadius", 6], ["marginBottom", 8]]))], ["theme-preview-light", padStyleMapOf(utsMapOf([["backgroundImage", "linear-gradient(135deg, #f8f9fa 0%, #ffffff 100%)"], ["backgroundColor", "rgba(0,0,0,0)"], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#dee2e6"], ["borderRightColor", "#dee2e6"], ["borderBottomColor", "#dee2e6"], ["borderLeftColor", "#dee2e6"]]))], ["theme-preview-dark", padStyleMapOf(utsMapOf([["backgroundImage", "linear-gradient(135deg, #1a1d20 0%, #2d3338 100%)"], ["backgroundColor", "rgba(0,0,0,0)"], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#495057"], ["borderRightColor", "#495057"], ["borderBottomColor", "#495057"], ["borderLeftColor", "#495057"]]))], ["theme-preview-gold", padStyleMapOf(utsMapOf([["backgroundImage", "linear-gradient(135deg, #0a0a0a 0%, #1a1a1a 100%)"], ["backgroundColor", "rgba(0,0,0,0)"], ["borderTopWidth", 2], ["borderRightWidth", 2], ["borderBottomWidth", 2], ["borderLeftWidth", 2], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#ffd700"], ["borderRightColor", "#ffd700"], ["borderBottomColor", "#ffd700"], ["borderLeftColor", "#ffd700"]]))], ["theme-preview-green", padStyleMapOf(utsMapOf([["backgroundImage", "linear-gradient(135deg, #e8f5e9 0%, #f1f8f4 100%)"], ["backgroundColor", "rgba(0,0,0,0)"], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#a5d6a7"], ["borderRightColor", "#a5d6a7"], ["borderBottomColor", "#a5d6a7"], ["borderLeftColor", "#a5d6a7"]]))], ["theme-preview-tech", padStyleMapOf(utsMapOf([["backgroundImage", "linear-gradient(135deg, #0d1117 0%, #161b22 100%)"], ["backgroundColor", "rgba(0,0,0,0)"], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#00d9ff"], ["borderRightColor", "#00d9ff"], ["borderBottomColor", "#00d9ff"], ["borderLeftColor", "#00d9ff"], ["boxShadow", "0 0 10px rgba(0, 217, 255, 0.3)"]]))], ["theme-preview-minimal", padStyleMapOf(utsMapOf([["backgroundImage", "none"], ["backgroundColor", "#ffffff"], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#e0e0e0"], ["borderRightColor", "#e0e0e0"], ["borderBottomColor", "#e0e0e0"], ["borderLeftColor", "#e0e0e0"]]))], ["theme-name", padStyleMapOf(utsMapOf([["fontSize", 13], ["color", "#212529"]]))], ["wuxing-preview", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["justifyContent", "space-around"], ["paddingTop", 16], ["paddingRight", 16], ["paddingBottom", 16], ["paddingLeft", 16], ["backgroundColor", "#f8f9fa"], ["borderTopLeftRadius", 8], ["borderTopRightRadius", 8], ["borderBottomRightRadius", 8], ["borderBottomLeftRadius", 8]]))], ["wuxing-item", padStyleMapOf(utsMapOf([["flexDirection", "column"], ["alignItems", "center"]]))], ["wuxing-label", padStyleMapOf(utsMapOf([["fontSize", 10], ["color", "#6c757d"]]))]])]
