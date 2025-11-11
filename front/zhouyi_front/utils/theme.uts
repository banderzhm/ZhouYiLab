/**
 * 主题管理工具
 * 提供全局主题切换和五行颜色映射功能
 */

// 主题类型
export type ThemeType = 'light' | 'dark' | 'gold' | 'green' | 'tech' | 'minimal'

// 五行类型
export type WuxingType = 'mu' | 'huo' | 'tu' | 'jin' | 'shui'

// 天干类型
export type TianganType = '甲' | '乙' | '丙' | '丁' | '戊' | '己' | '庚' | '辛' | '壬' | '癸'

// 地支类型
export type DizhiType = '子' | '丑' | '寅' | '卯' | '辰' | '巳' | '午' | '未' | '申' | '酉' | '戌' | '亥'

// 主题配置
export const THEME_CONFIG = {
	light: {
		name: '浅色主题',
		bgPrimary: '#f8f9fa',
		bgSecondary: '#ffffff',
		bgTertiary: '#e9ecef',
		borderPrimary: '#dee2e6',
		borderSecondary: '#ced4da',
		borderLight: '#f1f3f5',
		textPrimary: '#212529',
		textSecondary: '#6c757d',
		textTertiary: '#495057',
		shadow: 'rgba(0, 0, 0, 0.075)'
	},
	dark: {
		name: '深色主题',
		bgPrimary: '#1a1d20',
		bgSecondary: '#2d3338',
		bgTertiary: '#3e4347',
		borderPrimary: '#495057',
		borderSecondary: '#6c757d',
		borderLight: '#495057',
		textPrimary: '#f8f9fa',
		textSecondary: '#adb5bd',
		textTertiary: '#dee2e6',
		shadow: 'rgba(0, 0, 0, 0.3)'
	},
	gold: {
		name: '黑金主题',
		bgPrimary: '#0a0a0a',
		bgSecondary: '#1a1a1a',
		bgTertiary: '#2a2a2a',
		borderPrimary: '#ffd700',
		borderSecondary: '#daa520',
		borderLight: '#3a3a3a',
		textPrimary: '#ffd700',
		textSecondary: '#daa520',
		textTertiary: '#b8860b',
		shadow: 'rgba(255, 215, 0, 0.2)'
	},
	green: {
		name: '护眼主题',
		bgPrimary: '#e8f5e9',
		bgSecondary: '#f1f8f4',
		bgTertiary: '#c8e6c9',
		borderPrimary: '#a5d6a7',
		borderSecondary: '#81c784',
		borderLight: '#dcedc8',
		textPrimary: '#1b5e20',
		textSecondary: '#388e3c',
		textTertiary: '#4caf50',
		shadow: 'rgba(76, 175, 80, 0.1)'
	},
	tech: {
		name: '科技主题',
		bgPrimary: '#0d1117',
		bgSecondary: '#161b22',
		bgTertiary: '#21262d',
		borderPrimary: '#30363d',
		borderSecondary: '#00d9ff',
		borderLight: '#30363d',
		textPrimary: '#c9d1d9',
		textSecondary: '#8b949e',
		textTertiary: '#58a6ff',
		shadow: 'rgba(0, 217, 255, 0.15)'
	},
	minimal: {
		name: '简洁主题',
		bgPrimary: '#ffffff',
		bgSecondary: '#ffffff',
		bgTertiary: '#fafafa',
		borderPrimary: '#e0e0e0',
		borderSecondary: '#bdbdbd',
		borderLight: '#f5f5f5',
		textPrimary: '#212121',
		textSecondary: '#757575',
		textTertiary: '#9e9e9e',
		shadow: 'rgba(0, 0, 0, 0.05)'
	}
}

// 五行颜色配置
export const WUXING_COLORS = {
	mu: {
		primary: '#28a745',
		light: '#d4edda',
		dark: '#198754'
	},
	huo: {
		primary: '#dc3545',
		light: '#f8d7da',
		dark: '#b02a37'
	},
	tu: {
		primary: '#8b7355',
		light: '#e8dfd6',
		dark: '#6b5644'
	},
	jin: {
		primary: '#ffd700',
		light: '#fff8dc',
		dark: '#daa520'
	},
	shui: {
		primary: '#0d6efd',
		light: '#cfe2ff',
		dark: '#0a58ca'
	}
}

// 天干到五行映射
export const TIANGAN_WUXING_MAP : Map<TianganType, WuxingType> = new Map([
	['甲', 'mu'],
	['乙', 'mu'],
	['丙', 'huo'],
	['丁', 'huo'],
	['戊', 'tu'],
	['己', 'tu'],
	['庚', 'jin'],
	['辛', 'jin'],
	['壬', 'shui'],
	['癸', 'shui']
])

// 地支到五行映射
export const DIZHI_WUXING_MAP : Map<DizhiType, WuxingType> = new Map([
	['子', 'shui'],
	['丑', 'tu'],
	['寅', 'mu'],
	['卯', 'mu'],
	['辰', 'tu'],
	['巳', 'huo'],
	['午', 'huo'],
	['未', 'tu'],
	['申', 'jin'],
	['酉', 'jin'],
	['戌', 'tu'],
	['亥', 'shui']
])

// 主题管理类
class ThemeManager {
	private currentTheme : ThemeType = 'light'
	
	// 获取当前主题
	getTheme() : ThemeType {
		return this.currentTheme
	}
	
	// 设置主题
	setTheme(theme : ThemeType) {
		this.currentTheme = theme
		// 可以在这里触发全局样式更新事件
		uni.$emit('themeChanged', theme)
	}
	
	// 切换主题
	toggleTheme() {
		this.currentTheme = this.currentTheme === 'light' ? 'dark' : 'light'
		uni.$emit('themeChanged', this.currentTheme)
	}
	
	// 获取当前主题配置
	getThemeConfig() {
		return THEME_CONFIG[this.currentTheme]
	}
	
	// 获取天干颜色
	getTianganColor(tiangan : TianganType, variant : 'primary' | 'light' | 'dark' = 'primary') : string {
		const wuxing = TIANGAN_WUXING_MAP.get(tiangan)
		if (wuxing) {
			return WUXING_COLORS[wuxing][variant]
		}
		return '#212529'
	}
	
	// 获取地支颜色
	getDizhiColor(dizhi : DizhiType, variant : 'primary' | 'light' | 'dark' = 'primary') : string {
		const wuxing = DIZHI_WUXING_MAP.get(dizhi)
		if (wuxing) {
			return WUXING_COLORS[wuxing][variant]
		}
		return '#212529'
	}
	
	// 获取五行颜色
	getWuxingColor(wuxing : WuxingType, variant : 'primary' | 'light' | 'dark' = 'primary') : string {
		return WUXING_COLORS[wuxing][variant]
	}
}

// 导出单例
export const themeManager = new ThemeManager()

