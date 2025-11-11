# 快速开始 - 主题系统

## 🚀 快速使用

### 1. 切换主题

在首页点击 **🎨 主题设置**，选择你喜欢的主题：

```
首页 → 主题设置 → 选择主题
```

### 2. 在代码中使用

#### 方式一：使用工具类（推荐）

```vue
<template>
	<!-- 五行颜色 -->
	<text class="wuxing-jin">申酉金</text>  <!-- 显示为金黄色 #ffd700 -->
	<text class="wuxing-tu">辰戌丑未土</text>  <!-- 显示为土灰色 #8b7355 -->
	
	<!-- 天干颜色 -->
	<text class="tiangan-geng">庚</text>  <!-- 金 - 金黄色 -->
	<text class="tiangan-wu">戊</text>  <!-- 土 - 土灰色 -->
	
	<!-- 地支颜色 -->
	<text class="dizhi-shen">申</text>  <!-- 金 - 金黄色 -->
	<text class="dizhi-chou">丑</text>  <!-- 土 - 土灰色 -->
</template>
```

#### 方式二：动态获取颜色

```vue
<template>
	<text :style="{color: getTianganColor('庚')}">庚金</text>
	<text :style="{color: getDizhiColor('申')}">申金</text>
</template>

<script>
	import themeMixin from '@/mixins/theme-mixin.uts'
	
	export default {
		mixins: [themeMixin]
	}
</script>
```

## 🎨 主题预览效果

### 黑金主题 (gold)

```
┌────────────────────────┐
│   黑色背景 #0a0a0a    │
│                        │
│   金色文字 #ffd700    │
│   金色边框 ──────    │
│                        │
└────────────────────────┘
```

**最佳场景**：易经卜卦、传统术数、夜间使用

### 护眼主题 (green)

```
┌────────────────────────┐
│   淡绿背景 #e8f5e9    │
│                        │
│   深绿文字 #1b5e20    │
│   绿色边框 ──────    │
│                        │
└────────────────────────┘
```

**最佳场景**：长时间阅读、学习文档、保护视力

### 科技主题 (tech)

```
┌────────────────────────┐
│   深色背景 #0d1117    │
│                        │
│   浅灰文字 #c9d1d9    │
│   青色边框 ──────    │
│   荧光效果 ✨        │
└────────────────────────┘
```

**最佳场景**：现代化界面、数据分析、程序员审美

### 简洁主题 (minimal)

```
┌────────────────────────┐
│   纯白背景 #ffffff    │
│                        │
│   深色文字 #212121    │
│   极简边框 ──────    │
│                        │
└────────────────────────┘
```

**最佳场景**：专注内容、打印输出、专业报告

## 📝 五行颜色更新说明

### 新配色方案

| 五行 | 颜色 | 色值 | 变化 |
|-----|-----|------|------|
| 木 🌲 | 绿色 | `#28a745` | 无变化 |
| 火 🔥 | 红色 | `#dc3545` | 无变化 |
| 土 🏔️ | 土灰色 | `#8b7355` | ✅ 从黄色改为棕灰色 |
| 金 ⚙️ | 金黄色 | `#ffd700` | ✅ 从灰色改为金色 |
| 水 💧 | 蓝色 | `#0d6efd` | 无变化 |

### 使用示例

```vue
<template>
	<!-- 天干五行配色 -->
	<text class="tiangan-geng">庚</text>  <!-- 金 - 显示为金黄色 -->
	<text class="tiangan-wu">戊</text>  <!-- 土 - 显示为土灰色 -->
	
	<!-- 地支五行配色 -->
	<text class="dizhi-shen">申</text>  <!-- 金 - 显示为金黄色 -->
	<text class="dizhi-chen">辰</text>  <!-- 土 - 显示为土灰色 -->
</template>
```

## 🎯 实际应用示例

### 在大六壬排盘中启用五行配色

修改 `pages/daluren/daluren.uvue`：

```vue
<template>
	<!-- 地盘使用五行颜色 -->
	<text :class="'earth-plate dizhi-' + dizhi">{{ dizhi }}</text>
	
	<!-- 天盘使用五行颜色 -->
	<text :class="'heaven-plate dizhi-' + heavenPlate">{{ heavenPlate }}</text>
</template>

<script>
	export default {
		data() {
			return {
				dizhi: '申',      // 会显示金黄色
				heavenPlate: '亥' // 会显示蓝色（水）
			}
		}
	}
</script>
```

## 💡 快捷方式

### 快速访问主题设置

1. 首页 → 点击 **🎨 主题设置**
2. 或使用代码：
   ```typescript
   uni.navigateTo({
       url: '/pages/settings/settings'
   })
   ```

### 快速切换常用主题

```typescript
import { themeStore } from '@/store/theme.uts'

// 夜间模式
themeStore.setTheme('dark')

// 易经古典风格
themeStore.setTheme('gold')

// 护眼模式
themeStore.setTheme('green')
```

## 📦 相关文件

- `uni.scss` - 全局样式变量定义
- `App.uvue` - 全局工具类定义
- `utils/theme.uts` - 主题配置和工具函数
- `store/theme.uts` - 主题状态管理
- `mixins/theme-mixin.uts` - 主题 Mixin
- `components/theme-settings.uvue` - 主题设置组件
- `pages/settings/settings.uvue` - 主题设置页面

## ⚡ 性能优化

- 主题配置使用常量，无运行时计算开销
- 工具类直接使用 CSS，性能最优
- 动态颜色使用缓存，避免重复计算
- 主题切换平滑过渡，用户体验好

## 🎓 学习资源

详细文档请查看：
- `THEME.md` - 完整主题系统文档
- `THEME_USAGE.md` - 主题使用指南

现在就去试试不同的主题吧！🎉





