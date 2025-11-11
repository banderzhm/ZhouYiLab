# 周易实验室 - 主题系统使用指南

## 📚 概述

周易实验室提供了完整的主题系统，支持：
- ✅ 浅色/深色主题切换
- ✅ 五行颜色配置（金木水火土）
- ✅ 天干地支颜色映射
- ✅ 全局样式变量

## 🎨 五行颜色系统

### 五行基础颜色

| 五行 | 颜色 | 色值 | 说明 |
|-----|-----|------|------|
| 木 🌲 | 绿色 | `#28a745` | 代表生长、生机 |
| 火 🔥 | 红色 | `#dc3545` | 代表热情、能量 |
| 土 🏔️ | 黄色 | `#ffc107` | 代表稳重、中和 |
| 金 ⚙️ | 灰色 | `#6c757d` | 代表坚固、收敛 |
| 水 💧 | 蓝色 | `#0d6efd` | 代表流动、智慧 |

### 天干颜色映射

```
甲、乙 → 木 → 绿色 (#28a745)
丙、丁 → 火 → 红色 (#dc3545)
戊、己 → 土 → 黄色 (#ffc107)
庚、辛 → 金 → 灰色 (#6c757d)
壬、癸 → 水 → 蓝色 (#0d6efd)
```

### 地支颜色映射

```
子、亥 → 水 → 蓝色 (#0d6efd)
寅、卯 → 木 → 绿色 (#28a745)
巳、午 → 火 → 红色 (#dc3545)
申、酉 → 金 → 灰色 (#6c757d)
丑、辰、未、戌 → 土 → 黄色 (#ffc107)
```

## 📖 使用方法

### 1. 在 CSS 中使用工具类

```vue
<template>
	<!-- 使用五行颜色 -->
	<text class="wuxing-mu">木</text>
	<text class="wuxing-huo">火</text>
	<text class="wuxing-tu">土</text>
	<text class="wuxing-jin">金</text>
	<text class="wuxing-shui">水</text>
	
	<!-- 使用天干颜色 -->
	<text class="tiangan-jia">甲</text>
	<text class="tiangan-geng">庚</text>
	
	<!-- 使用地支颜色 -->
	<text class="dizhi-zi">子</text>
	<text class="dizhi-wu">午</text>
	
	<!-- 使用术数特定颜色 -->
	<text class="color-general">勾陈</text>
	<text class="color-heaven">天盘</text>
	<text class="color-earth">地盘</text>
</template>
```

### 2. 在 Script 中动态获取颜色

```vue
<script>
	import { themeStore } from '@/store/theme.uts'
	
	export default {
		data() {
			return {
				// 获取甲木的颜色
				jiaColor: themeStore.getTianganColor('甲'),
				// 获取子水的颜色
				ziColor: themeStore.getDizhiColor('子'),
				// 获取木的颜色
				muColor: themeStore.getWuxingColor('mu')
			}
		},
		
		methods: {
			// 切换主题
			switchTheme() {
				themeStore.toggleTheme()
			},
			
			// 切换五行颜色
			switchWuxingColors() {
				themeStore.toggleWuxingColors()
			}
		}
	}
</script>
```

### 3. 使用 Mixin（推荐）

```vue
<script>
	import themeMixin from '@/mixins/theme-mixin.uts'
	
	export default {
		mixins: [themeMixin],
		
		computed: {
			// 自动获取当前主题配置
			myColor() {
				return this.getTianganColor('甲')
			}
		},
		
		methods: {
			onToggleTheme() {
				// 直接使用 mixin 提供的方法
				this.toggleTheme()
			}
		}
	}
</script>
```

## 🔧 主题配置

### uni.scss 变量

所有颜色变量都在 `uni.scss` 中定义，可以直接在 SCSS 中使用：

```scss
.my-element {
	color: $wuxing-mu;              // 木色
	background-color: $wuxing-mu-light;  // 木色浅色背景
	border-color: $theme-border-primary; // 主题边框色
}
```

### 主题切换

```typescript
import { themeStore } from '@/store/theme.uts'

// 设置为深色主题
themeStore.setTheme('dark')

// 设置为浅色主题
themeStore.setTheme('light')

// 切换主题
themeStore.toggleTheme()
```

## 📱 实际应用示例

### 在大六壬排盘中应用五行颜色

```vue
<template>
	<!-- 地盘显示五行颜色 -->
	<text :class="'earth-plate dizhi-' + dizhi.toLowerCase()">{{ dizhi }}</text>
	
	<!-- 天干显示五行颜色 -->
	<text :class="'tiangan-' + tiangan">{{ tiangan }}</text>
</template>

<script>
	export default {
		data() {
			return {
				dizhi: '子',  // 会显示蓝色（水）
				tiangan: '甲' // 会显示绿色（木）
			}
		}
	}
</script>
```

## 🎯 主题设置组件

已提供 `components/theme-settings.uvue` 组件，可以直接在页面中使用：

```vue
<template>
	<view>
		<theme-settings />
	</view>
</template>

<script>
	import ThemeSettings from '@/components/theme-settings.uvue'
	
	export default {
		components: {
			ThemeSettings
		}
	}
</script>
```

## 📝 API 参考

### ThemeStore

```typescript
// 获取当前主题
themeStore.currentTheme // 'light' | 'dark'

// 获取主题配置
themeStore.getThemeConfig()

// 设置主题
themeStore.setTheme('dark')

// 切换主题
themeStore.toggleTheme()

// 获取天干颜色
themeStore.getTianganColor('甲', 'primary')

// 获取地支颜色
themeStore.getDizhiColor('子', 'primary')

// 获取五行颜色
themeStore.getWuxingColor('mu', 'primary')

// 切换五行颜色显示
themeStore.toggleWuxingColors()
```

### 颜色变体

所有颜色函数都支持三种变体：
- `'primary'` - 主色调（默认）
- `'light'` - 浅色调（适合背景）
- `'dark'` - 深色调（适合强调）

## 🌟 工具类参考

### 五行颜色类

```css
.wuxing-mu      /* 木色文字 */
.wuxing-huo     /* 火色文字 */
.wuxing-tu      /* 土色文字 */
.wuxing-jin     /* 金色文字 */
.wuxing-shui    /* 水色文字 */

.wuxing-mu-bg   /* 木色背景 */
.wuxing-huo-bg  /* 火色背景 */
.wuxing-tu-bg   /* 土色背景 */
.wuxing-jin-bg  /* 金色背景 */
.wuxing-shui-bg /* 水色背景 */
```

### 天干地支颜色类

```css
/* 天干 */
.tiangan-jia, .tiangan-yi       /* 甲乙木 */
.tiangan-bing, .tiangan-ding    /* 丙丁火 */
.tiangan-wu, .tiangan-ji        /* 戊己土 */
.tiangan-geng, .tiangan-xin     /* 庚辛金 */
.tiangan-ren, .tiangan-gui      /* 壬癸水 */

/* 地支 */
.dizhi-zi, .dizhi-hai           /* 子亥水 */
.dizhi-yin, .dizhi-mao          /* 寅卯木 */
.dizhi-si, .dizhi-wu            /* 巳午火 */
.dizhi-shen, .dizhi-you         /* 申酉金 */
.dizhi-chou, .dizhi-chen, .dizhi-wei, .dizhi-xu  /* 丑辰未戌土 */
```

### 术数专用颜色类

```css
.color-general   /* 将神颜色 #198754 */
.color-heaven    /* 天盘颜色 #0d6efd */
.color-earth     /* 地盘颜色 #212529 */
.color-branch    /* 课传地支 #dc3545 */
.color-shensha   /* 神煞颜色 #6c757d */
.color-relation  /* 关系信息 #495057 */
```

## 💡 最佳实践

1. **使用语义化颜色类**
   ```vue
   <!-- 好的做法 -->
   <text class="color-general">勾陈</text>
   
   <!-- 避免硬编码颜色 -->
   <text style="color: #198754">勾陈</text>
   ```

2. **响应主题变化**
   ```vue
   <script>
   import themeMixin from '@/mixins/theme-mixin.uts'
   
   export default {
       mixins: [themeMixin],
       // 自动响应主题变化
   }
   </script>
   ```

3. **使用五行配色增强可读性**
   - 在八字排盘中，用五行颜色区分天干地支
   - 在紫微斗数中，用五行颜色标注五行局
   - 在六爻中，用五行颜色标注卦象

## 🚀 扩展建议

后续可以扩展：
- 自定义主题颜色
- 更多预设主题（古典、现代、护眼等）
- 动态调整字体大小
- 色盲友好模式

## 📦 文件结构

```
zhouyi_front/
├── uni.scss                      # 全局样式变量（包含五行颜色定义）
├── App.uvue                      # 全局样式（工具类定义）
├── store/
│   └── theme.uts                 # 主题状态管理
├── utils/
│   └── theme.uts                 # 主题工具函数
├── mixins/
│   └── theme-mixin.uts           # 主题 Mixin
└── components/
    └── theme-settings.uvue       # 主题设置组件
```





