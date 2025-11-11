
# uni-loading 动画加载

动画加载组件使用场景非常多，如在页面内数据加载时，提供一个loading动画，列表的上拉加载，下拉刷新中也需要加载动画。

**注意：当前版本只支持 uni-app x**

## 使用组件

### 基本用法

在 ``template`` 中使用组件，独立显示加载图标和加载文本

```html
<!-- 只显示加载图标 -->
<uni-loading></uni-loading>
<!-- 自定义加载图标颜色 -->
<uni-loading color="#409EFF"></uni-loading>
<!-- 修改加载图标大小 -->
<uni-loading :size="30"></uni-loading>
<!-- 修改加载图标类型 ： 当前只支持 circle-->
<uni-loading type="circle"></uni-loading>

```

### 覆盖元素的加载动画

在 ``template`` 中使用组件，在原始布局元素上面覆盖遮罩和加载动画

```html
<uni-loading type="circle">
	<text>第一行文本</text>
	<text>第二行文本</text>
	<text>第三行文本</text>
</uni-loading>
```
**注意：原理是在原始元素外增加一层 view 节点，可能会影响页面布局，如发生布局影响，直接在 <uni-loading> 上修改样式即可，结构等同于：**

```html
<view class="uni-loading">
	<text>第一行文本</text>
	<text>第二行文本</text>
	<text>第三行文本</text>
</view>
```


### 取消加载动画

使用 `loading` 属性可以关闭加载动画，为了优化组件性能，建议组件在页面不可见时，设置 `loading:false`，关闭加载动画


```html
<uni-loading :loading="false"></uni-loading>
```

## 属性/方法

### Loading Props

|属性名		|类型	|默认值					|说明										|
|:-:		|:-:	|:-:					|:-:										|
|loading	|Boolean|true					|是否显示加载动画								|
|type		|String	|circle					|加载图标显示类型，当前只支持circle,其他类型或者自定义获取提供	|
<!-- |iconType	|String	|loop					|自定义图标类型，值详见[uni-icons](https://uniapp.dcloud.net.cn/component/uniui/uni-icons.html#%E5%9B%BE%E6%A0%87%E7%A4%BA%E4%BE%8B)	| -->
|background	|String	|rgba(255,255,255,0.6)	|加载动画遮罩颜色								|
|color		|String	|#333333				|加载图标以及加载文字颜色						|
|size		|Number	|20						|加载图标大小									|
|text		|String	|-						|加载文本										|



### Loading Slot
|插槽名	|说明		|
|:-:		|:-:		|
|default|默认插槽	|
