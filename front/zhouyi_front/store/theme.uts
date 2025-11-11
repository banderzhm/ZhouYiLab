/**
 * 全局主题状态管理
 */

import { THEME_CONFIG, WUXING_COLORS, TIANGAN_WUXING_MAP, DIZHI_WUXING_MAP } from '../utils/theme.uts'
import type { ThemeType, WuxingType, TianganType, DizhiType } from '../utils/theme.uts'

// 主题状态
class ThemeStore {
	// 当前主题
	currentTheme : ThemeType = 'light'
	
	// 是否启用五行颜色
	enableWuxingColors : boolean = true
	
	// 获取当前主题配置
	getThemeConfig() {
		return THEME_CONFIG[this.currentTheme]
	}
	
	// 设置主题
	setTheme(theme : ThemeType) {
		this.currentTheme = theme
		// 存储到本地
		uni.setStorageSync('theme', theme)
		// 触发全局事件
		uni.$emit('themeChanged', theme)
	}
	
	// 切换主题（在浅色和深色间切换）
	toggleTheme() {
		const newTheme : ThemeType = this.currentTheme === 'light' ? 'dark' : 'light'
		this.setTheme(newTheme)
	}
	
	// 获取所有可用主题
	getAllThemes() : ThemeType[] {
		return ['light', 'dark', 'gold', 'green', 'tech', 'minimal']
	}
	
	// 获取主题名称
	getThemeName() : string {
		return THEME_CONFIG[this.currentTheme].name
	}
	
	// 切换五行颜色显示
	toggleWuxingColors() {
		this.enableWuxingColors = !this.enableWuxingColors
		uni.setStorageSync('enableWuxingColors', this.enableWuxingColors)
		uni.$emit('wuxingColorsChanged', this.enableWuxingColors)
	}
	
	// 获取天干颜色
	getTianganColor(tiangan : TianganType, variant : 'primary' | 'light' | 'dark' = 'primary') : string {
		if (!this.enableWuxingColors) {
			return this.getThemeConfig().textPrimary
		}
		const wuxing = TIANGAN_WUXING_MAP.get(tiangan)
		if (wuxing != null) {
			return WUXING_COLORS[wuxing][variant]
		}
		return this.getThemeConfig().textPrimary
	}
	
	// 获取地支颜色
	getDizhiColor(dizhi : DizhiType, variant : 'primary' | 'light' | 'dark' = 'primary') : string {
		if (!this.enableWuxingColors) {
			return this.getThemeConfig().textPrimary
		}
		const wuxing = DIZHI_WUXING_MAP.get(dizhi)
		if (wuxing != null) {
			return WUXING_COLORS[wuxing][variant]
		}
		return this.getThemeConfig().textPrimary
	}
	
	// 获取五行颜色
	getWuxingColor(wuxing : WuxingType, variant : 'primary' | 'light' | 'dark' = 'primary') : string {
		return WUXING_COLORS[wuxing][variant]
	}
	
	// 初始化主题（从本地存储读取）
	init() {
		const savedTheme = uni.getStorageSync('theme')
		const validThemes : ThemeType[] = ['light', 'dark', 'gold', 'green', 'tech', 'minimal']
		if (savedTheme != null && validThemes.includes(savedTheme as ThemeType)) {
			this.currentTheme = savedTheme as ThemeType
		}
		
		const savedWuxingColors = uni.getStorageSync('enableWuxingColors')
		if (savedWuxingColors != null) {
			this.enableWuxingColors = savedWuxingColors as boolean
		}
	}
}

// 导出单例
export const themeStore = new ThemeStore()

