# 主题系统使用指南

## 🎨 五行颜色调整

### ✅ 已修改

| 五行 | 新颜色 | 色值 | 说明 |
|-----|-------|------|------|
| 金 ⚙️ | 金黄色 | `#ffd700` | 原为灰色，现为金色 |
| 土 🏔️ | 土灰色 | `#8b7355` | 原为黄色，现为棕灰色 |

## 📱 六大预设主题

### 1. 浅色主题 (light) - 默认
- **背景**: 浅灰 `#f8f9fa` + 白色 `#ffffff`
- **文字**: 深色 `#212529`
- **特点**: 明亮、清晰、适合日间使用

### 2. 深色主题 (dark)
- **背景**: 深灰 `#1a1d20` + `#2d3338`
- **文字**: 浅色 `#f8f9fa`
- **特点**: 护眼、适合夜间使用

### 3. 黑金主题 (gold) ⭐
- **背景**: 纯黑 `#0a0a0a` + `#1a1a1a`
- **文字**: 金色 `#ffd700`
- **边框**: 金色 `#ffd700`
- **特点**: 奢华、神秘、易经古典风格

### 4. 护眼主题 (green) 👁️
- **背景**: 淡绿 `#e8f5e9` + `#f1f8f4`
- **文字**: 深绿 `#1b5e20`
- **特点**: 柔和、护眼、长时间使用舒适

### 5. 科技主题 (tech) 🚀
- **背景**: 深蓝灰 `#0d1117` + `#161b22`
- **文字**: 浅灰 `#c9d1d9`
- **边框**: 青色 `#00d9ff`
- **特点**: 现代、科技感、带荧光效果

### 6. 简洁主题 (minimal) 📄
- **背景**: 纯白 `#ffffff`
- **文字**: 深灰 `#212121`
- **边框**: 浅灰 `#e0e0e0`
- **特点**: 极简、纯净、专注内容

## 💻 使用方法

### 快速切换主题

```typescript
import { themeStore } from '@/store/theme.uts'

// 切换到黑金主题
themeStore.setTheme('gold')

// 切换到护眼主题
themeStore.setTheme('green')

// 切换到科技主题
themeStore.setTheme('tech')

// 切换到简洁主题
themeStore.setTheme('minimal')
```

### 在页面中应用主题

```vue
<template>
	<view :style="{backgroundColor: themeConfig.bgPrimary}">
		<text :style="{color: themeConfig.textPrimary}">文字</text>
	</view>
</template>

<script>
	import themeMixin from '@/mixins/theme-mixin.uts'
	
	export default {
		mixins: [themeMixin],
		// themeConfig 会自动响应主题变化
	}
</script>
```

### 使用五行颜色

```vue
<template>
	<!-- 直接使用工具类 -->
	<text class="wuxing-jin">申酉金</text>
	<text class="wuxing-tu">辰戌丑未土</text>
	
	<!-- 动态获取颜色 -->
	<text :style="{color: getDizhiColor('申')}">申</text>
	<text :style="{color: getTianganColor('庚')}">庚</text>
</template>
```

## 🎯 主题特色场景

### 黑金主题 - 适合
- 🌙 夜间使用
- 🎭 传统术数应用
- 📿 古典风格展示
- 🏯 易经卜卦场景

### 护眼主题 - 适合
- 📖 长时间阅读
- 📚 学习文档
- 🧘 冥想放松
- 👁️ 保护视力

### 科技主题 - 适合
- 💻 程序员用户
- 🔬 数据分析
- 🎮 现代化界面
- 🌐 科技感体验

### 简洁主题 - 适合
- 📄 打印输出
- 📊 专业报告
- 🎯 专注内容
- 🖨️ 文档导出

## 🔄 主题持久化

主题选择会自动保存到本地存储，下次打开应用时自动恢复：

```typescript
// 初始化时自动加载上次使用的主题
themeStore.init()

// 主题会保存到 uni.storage 中
// 键名: 'theme'
// 值: 'light' | 'dark' | 'gold' | 'green' | 'tech' | 'minimal'
```

## 📊 主题配色参考

### 黑金主题配色方案
```
背景主色: #0a0a0a  (纯黑)
背景次色: #1a1a1a  (深黑)
文字主色: #ffd700  (金色)
边框色:   #ffd700  (金色)
阴影:     rgba(255, 215, 0, 0.2)  (金色发光)
```

### 护眼主题配色方案
```
背景主色: #e8f5e9  (淡绿)
背景次色: #f1f8f4  (极淡绿)
文字主色: #1b5e20  (深绿)
边框色:   #a5d6a7  (中绿)
阴影:     rgba(76, 175, 80, 0.1)  (绿色柔光)
```

### 科技主题配色方案
```
背景主色: #0d1117  (GitHub深色)
背景次色: #161b22  (深灰蓝)
文字主色: #c9d1d9  (浅灰)
边框色:   #00d9ff  (青色荧光)
强调色:   #58a6ff  (蓝色)
阴影:     rgba(0, 217, 255, 0.15)  (青色辉光)
```

### 简洁主题配色方案
```
背景主色: #ffffff  (纯白)
背景次色: #fafafa  (极淡灰)
文字主色: #212121  (纯黑)
边框色:   #e0e0e0  (浅灰)
阴影:     rgba(0, 0, 0, 0.05)  (极淡阴影)
```

## 🎬 动画效果

主题切换支持平滑过渡：

```css
/* 为元素添加过渡效果 */
.my-element {
	transition: background-color 0.3s ease, color 0.3s ease;
}
```

## 📱 实际效果

访问 **主题设置** 页面可以：
1. ✅ 实时预览6种主题
2. ✅ 一键切换主题
3. ✅ 开启/关闭五行配色
4. ✅ 查看五行颜色预览

## 🚀 后续扩展

可以扩展的功能：
- [ ] 自定义主题颜色
- [ ] 主题分享功能
- [ ] 更多预设主题
- [ ] 主题跟随系统
- [ ] 定时自动切换（日间/夜间）





