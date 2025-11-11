export const leftWindow = {
	path: "windows/left-window.uvue",
	style: {
		width: "350px"
	}
};
export const topWindow = {
	path: "windows/top-window.uvue",
	style: {
		height: "60px"
	}
};
export const pages = [
	{
		path: "pages/tabBar/component",
		style: {
			navigationBarTitleText: "内置组件",
			backgroundColorContent: "@tabBarPagebackgroundColorContent"
		}
	},
	{
		path: "pages/component/view/view",
		group: "0,1,0",
		style: {
			navigationBarTitleText: "view | 基本视图容器"
		}
	},
	{
		path: "pages/component/view/issue-19746",
		style: {
			navigationBarTitleText: "issue-19746"
		}
	},
	{
		path: "pages/component/view/issue-20486",
		style: {
			navigationBarTitleText: "issue-20486"
		}
	},
	{
		path: "pages/component/view/issue-21144",
		style: {
			navigationBarTitleText: "issue-21144"
		}
	},
	{
		path: "pages/component/scroll-view/scroll-view",
		group: "0,1,1",
		style: {
			navigationBarTitleText: "scroll-view | 可滚动视图容器"
		}
	},
	{
		path: "pages/component/scroll-view/scroll-view-refresher",
		style: {
			navigationBarTitleText: "scroll-view-refresher"
		}
	},
	{
		path: "pages/component/scroll-view/scroll-view-props",
		style: {
			navigationBarTitleText: "非下拉刷新的scroll-view属性示例"
		}
	},
	{
		path: "pages/component/scroll-view/scroll-view-refresher-props",
		style: {
			navigationBarTitleText: "下拉刷新的scroll-view属性示例"
		}
	},
	{
		path: "pages/component/scroll-view/scroll-view-custom-refresher-props",
		style: {
			navigationBarTitleText: "自定义下拉刷新的scroll-view属性示例"
		}
	},
	{
		path: "pages/component/scroll-view/issue-18587",
		style: {
			navigationBarTitleText: "issue-18587"
		}
	},
	{
		path: "pages/component/scroll-view/issue-18773",
		style: {
			navigationBarTitleText: "issue-18773"
		}
	},
	{
		path: "pages/component/swiper/swiper",
		group: "0,1,2",
		style: {
			navigationBarTitleText: "swiper | 滑块视图容器"
		}
	},
	{
		path: "pages/component/swiper/swiper-anim",
		style: {
			navigationBarTitleText: "swiper | 滑块视图容器 - 动画测试"
		}
	},
	{
		path: "pages/component/match-media/match-media",
		group: "0,1,3",
		style: {
			navigationBarTitleText: "match-media | 匹配检测节点"
		}
	},
	{
		path: "pages/component/list-view/list-view",
		group: "0,1,6",
		style: {
			navigationBarTitleText: "list-view | 列表容器"
		}
	},
	{
		path: "pages/component/list-view/list-view-refresh",
		style: {
			navigationBarTitleText: "list-view-refresh",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/component/list-view/list-view-multiplex",
		style: {
			navigationBarTitleText: "list-view-multiplex"
		}
	},
	{
		path: "pages/component/list-view/list-view-multiplex-input",
		style: {
			navigationBarTitleText: "list-view复用input",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/component/list-view/list-view-long-press",
		style: {
			navigationBarTitleText: "list-view长按事件",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/component/list-view/list-view-multiplex-video",
		style: {
			navigationBarTitleText: "list-view-multiplex-video"
		}
	},
	{
		path: "pages/component/list-view/list-view-children-in-slot",
		style: {
			navigationBarTitleText: "list-view-children-in-slot"
		}
	},
	{
		path: "pages/component/list-view/list-view-children-if-show",
		style: {
			navigationBarTitleText: "list-item v-if & v-show"
		}
	},
	{
		path: "pages/component/sticky-section/sticky-section",
		group: "0,1,8,1",
		style: {
			navigationBarTitleText: "sticky-section | 吸顶布局容器"
		}
	},
	{
		path: "pages/component/sticky-section/issues-16118",
		style: {
			navigationBarTitleText: ""
		}
	},
	{
		path: "pages/component/list-view/issue-20688",
		style: {
			navigationBarTitleText: "issue-20688"
		}
	},
	{
		path: "pages/component/sticky-header/sticky-header",
		group: "0,1,8,0",
		style: {
			navigationBarTitleText: "sticky-header | 吸顶布局容器",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/component/text/text",
		group: "0,2,1",
		style: {
			navigationBarTitleText: "text | 文本"
		}
	},
	{
		path: "pages/component/text/issues18068",
		style: {
			navigationBarTitleText: "text | 文本"
		}
	},
	{
		path: "pages/component/text/text-props",
		style: {
			navigationBarTitleText: "text-props"
		}
	},
	{
		path: "pages/component/text/text-layout",
		style: {
			navigationBarTitleText: "text-layout"
		}
	},
	{
		path: "pages/component/rich-text/rich-text",
		group: "0,2,2",
		style: {
			navigationBarTitleText: "rich-text | 富文本"
		}
	},
	{
		path: "pages/component/rich-text/rich-text-tags",
		style: {
			navigationBarTitleText: "rich-text-tags"
		}
	},
	{
		path: "pages/component/rich-text/rich-text-complex",
		style: {
			navigationBarTitleText: "rich-text-complex"
		}
	},
	{
		path: "pages/component/rich-text/rich-text-list",
		style: {
			navigationBarTitleText: "rich-text-list"
		}
	},
	{
		path: "pages/component/progress/progress",
		group: "0,2,3",
		style: {
			navigationBarTitleText: "progress | 进度条"
		}
	},
	{
		path: "pages/component/form/form",
		group: "0,3,2",
		style: {
			navigationBarTitleText: "form | 表单"
		}
	},
	{
		path: "pages/component/button/button",
		group: "0,3,0",
		style: {
			navigationBarTitleText: "button | 按钮"
		}
	},
	{
		path: "pages/component/button/buttonstatus",
		style: {
			navigationBarTitleText: "buttonstatus"
		}
	},
	{
		path: "pages/component/radio/radio",
		group: "0,3,8",
		style: {
			navigationBarTitleText: "radio | 单选框"
		}
	},
	{
		path: "pages/component/checkbox/checkbox",
		group: "0,3,1",
		style: {
			navigationBarTitleText: "checkbox | 多选框"
		}
	},
	{
		path: "pages/component/input/input",
		group: "0,3,3",
		style: {
			navigationBarTitleText: "input | 输入框"
		}
	},
	{
		path: "pages/component/textarea/textarea",
		group: "0,3,11",
		style: {
			navigationBarTitleText: "textarea | 多行输入框"
		}
	},
	{
		path: "pages/component/slider/slider",
		group: "0,3,9",
		style: {
			navigationBarTitleText: "slider | 滑动选择器"
		}
	},
	{
		path: "pages/component/slider/slider-in-swiper",
		style: {
			navigationBarTitleText: "slider-in-swiper"
		}
	},
	{
		path: "pages/component/slider/slider-maxValue",
		style: {
			navigationBarTitleText: "slider-maxValue-test"
		}
	},
	{
		path: "pages/component/picker-view/picker-view",
		group: "0,3,7",
		style: {
			navigationBarTitleText: "picker-view | 嵌入页面的滚动选择器"
		}
	},
	{
		path: "pages/component/picker-view/wrap-picker-view",
		style: {
			navigationBarTitleText: "picker-view | 嵌入页面的滚动选择器"
		}
	},
	{
		path: "pages/component/switch/switch",
		group: "0,3,10",
		style: {
			navigationBarTitleText: "switch | 开关选择器"
		}
	},
	{
		path: "pages/component/image/image",
		group: "0,5,0",
		style: {
			navigationBarTitleText: "image | 图片"
		}
	},
	{
		path: "pages/component/image/image-format",
		style: {
			navigationBarTitleText: "image-format"
		}
	},
	{
		path: "pages/component/image/image-mode",
		style: {
			navigationBarTitleText: "image-mode"
		}
	},
	{
		path: "pages/component/image/image-path",
		style: {
			navigationBarTitleText: "image-path"
		}
	},
	{
		path: "pages/component/image/image-large",
		style: {
			navigationBarTitleText: "大图测试"
		}
	},
	{
		path: "pages/component/video/video",
		group: "0,5,1",
		style: {
			navigationBarTitleText: "video | 视频"
		}
	},
	{
		path: "pages/component/video/video-dialog-page",
		style: {
			navigationBarTitleText: "test dislogPage video"
		}
	},
	{
		path: "pages/component/video/video-format",
		style: {
			navigationBarTitleText: "video-format"
		}
	},
	{
		path: "pages/component/navigator/navigator",
		group: "0,4,0",
		style: {
			navigationBarTitleText: "navigator | 页面链接"
		}
	},
	{
		path: "pages/component/navigator/navigate",
		style: {
			navigationBarTitleText: "navigatePage"
		}
	},
	{
		path: "pages/component/navigator/redirect",
		style: {
			navigationBarTitleText: "redirectPage"
		}
	},
	{
		path: "pages/component/web-view/web-view",
		group: "0,9",
		style: {
			navigationBarTitleText: "web-view"
		}
	},
	{
		path: "pages/component/web-view/web-view-scroll",
		style: {
			navigationBarTitleText: "web-view-scroll"
		}
	},
	{
		path: "pages/component/web-view/web-view/web-view-local",
		group: "0,9",
		style: {
			navigationBarTitleText: "本地网页"
		}
	},
	{
		path: "pages/component/unicloud-db/unicloud-db/contacts/list",
		group: "0,11",
		style: {
			navigationBarTitleText: "联系人",
			enablePullDownRefresh: true
		}
	},
	{
		path: "pages/component/unicloud-db/unicloud-db/contacts/add",
		style: {
			navigationBarTitleText: "新增联系人"
		}
	},
	{
		path: "pages/component/unicloud-db/unicloud-db/contacts/edit",
		style: {
			navigationBarTitleText: "编辑联系人"
		}
	},
	{
		path: "pages/component/unicloud-db/unicloud-db/contacts/detail",
		style: {
			navigationBarTitleText: ""
		}
	},
	{
		path: "pages/component/unicloud-db/unicloud-db/mixin-datacom/mixin-datacom",
		group: "0,11",
		style: {
			navigationBarTitleText: "mixinDatacom"
		}
	},
	{
		path: "pages/component/global-events/issue-17662",
		style: {
			navigationBarTitleText: "issue-17662"
		}
	},
	{
		path: "pages/component/global-properties/global-properties",
		group: "0,0",
		style: {
			navigationBarTitleText: "全局属性"
		}
	},
	{
		path: "pages/component/global-events/global-events",
		group: "0,0",
		style: {
			navigationBarTitleText: "全局事件"
		}
	},
	{
		path: "pages/component/global-events/global-events-transform",
		group: "0,0",
		style: {
			navigationBarTitleText: "点击事件-transform"
		}
	},
	{
		path: "pages/component/global-events/transition-events",
		group: "0,0",
		style: {
			navigationBarTitleText: "Transition Events"
		}
	},
	{
		path: "pages/component/global-events/touch-events",
		group: "0,0",
		style: {
			navigationBarTitleText: "Touch Events"
		}
	},
	{
		path: "pages/component/global-events/touch-events-bubbles",
		style: {
			navigationBarTitleText: "Touch Events bubbles"
		}
	},
	{
		path: "pages/component/global-events/touch-events-case",
		style: {
			navigationBarTitleText: "Touch Events case"
		}
	},
	{
		path: "pages/component/global-events/touch-events-preventDefault",
		style: {
			navigationBarTitleText: "preventDefault"
		}
	},
	{
		path: "pages/component/nested-scroll-header/nested-scroll-header",
		group: "0,1,9,0",
		style: {
			navigationBarTitleText: "nested-scroll-header"
		}
	},
	{
		path: "pages/component/nested-scroll-body/nested-scroll-body",
		group: "0,1,9,1",
		style: {
			navigationBarTitleText: "nested-scroll-body"
		}
	},
	{
		path: "pages/component/swiper/swiper-list-view",
		style: {
			navigationBarTitleText: "swiper嵌套list-view",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/component/map/map",
		group: "0,6",
		style: {
			navigationBarTitleText: "map | 地图"
		}
	},
	{
		path: "pages/component/cover-view/cover-view",
		group: "0,1,5,0",
		style: {
			navigationBarTitleText: "cover-view"
		}
	},
	{
		path: "pages/component/list-view/issue-17030",
		style: {
			navigationBarTitleText: "issue-17030"
		}
	},
	{
		path: "pages/component/list-view/issue-2199",
		style: {
			navigationBarTitleText: "issue-2199",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/component/list-view/issue-15701",
		style: {
			navigationBarTitleText: "issue-15701",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/component/list-view/issue-13858",
		style: {
			navigationBarTitleText: "issue-13858",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/component/list-view/issue-16126",
		style: {
			navigationBarTitleText: "issue-16126"
		}
	},
	{
		path: "pages/component/list-view/issue-16938",
		style: {
			navigationBarTitleText: "issue-16938"
		}
	},
	{
		path: "pages/component/list-view/issue-15878",
		style: {
			navigationBarTitleText: "issue-15878",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/component/canvas/canvas",
		group: "0,7",
		style: {
			navigationBarTitleText: "canvas | 画布"
		}
	},
	{
		path: "pages/component/canvas/canvas-context",
		style: {
			navigationBarTitleText: "createCanvasContextAsync"
		}
	},
	{
		path: "pages/component/canvas/canvas/ball",
		group: "0,7",
		style: {
			navigationBarTitleText: "ball"
		}
	},
	{
		path: "pages/component/canvas/canvas/doodle",
		group: "0,7",
		style: {
			navigationBarTitleText: "涂鸦"
		}
	},
	{
		path: "pages/component/native-view/native-view",
		group: "0,2,4",
		style: {
			navigationBarTitleText: "native-view | 自定义原生View组件"
		}
	},
	{
		path: "pages/component/ad/ad",
		group: "0,8",
		style: {
			navigationBarTitleText: "ad | 信息流广告",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/component/waterflow/waterflow",
		group: "0,1,7",
		style: {
			navigationBarTitleText: "waterflow | 瀑布流容器"
		}
	},
	{
		path: "pages/component/waterflow/waterflow-fit-height",
		style: {
			navigationBarTitleText: "waterflow | 瀑布流容器"
		}
	},
	{
		path: "pages/component/ad/list-view-ad",
		style: {
			navigationBarTitleText: "ad | 信息流广告",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/component/ad/video-ad",
		style: {
			navigationBarTitleText: "ad | 视频贴片",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/component/ad/portrait-video-ad",
		style: {
			navigationBarTitleText: "ad | 视频贴片竖向全屏",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/component/ad/landscape-video-ad",
		style: {
			navigationBarTitleText: "ad | 视频贴片横向全屏",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/component/share-element/share-element",
		group: "0,1,10",
		style: {
			navigationBarTitleText: "share-element | 共享元素视图容器"
		}
	},
	{
		path: "pages/component/share-element/share-element-with-swiper",
		style: {
			navigationBarTitleText: "share-element-with-swiper"
		}
	},
	{
		path: "pages/component/share-element/share-element-to",
		style: {
			navigationBarTitleText: ""
		}
	},
	{
		path: "pages/component/camera/camera",
		group: "0,5,3",
		style: {
			navigationBarTitleText: "camera | 相机"
		}
	},
	{
		path: "pages/component/camera/camera-scan-code",
		style: {
			navigationBarTitleText: "camera-scan-code | 相机扫码"
		}
	},
	{
		path: "pages/component/live-player/live-player",
		group: "0,5,4",
		style: {
			navigationBarTitleText: "live-player | 实时音视频播放"
		}
	},
	{
		path: "pages/component/live-pusher/live-pusher",
		group: "0,5,5",
		style: {
			navigationBarTitleText: "live-pusher | 实时音视频录制"
		}
	},
	{
		path: "pages/tabBar/API",
		style: {
			navigationBarTitleText: "接口",
			backgroundColorContent: "@tabBarPagebackgroundColorContent"
		}
	},
	{
		path: "pages/API/get-app/get-app",
		group: "1,0,0",
		style: {
			navigationBarTitleText: "getApp | 获取当前应用实例"
		}
	},
	{
		path: "pages/API/exit/exit",
		group: "1,1,5",
		style: {
			navigationBarTitleText: "uni.exit | 退出应用"
		}
	},
	{
		path: "pages/API/install-apk/install-apk",
		group: "1,6,7",
		style: {
			navigationBarTitleText: "uni.installApk | 安装 APK",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/API/get-current-pages/get-current-pages",
		group: "1,0,1",
		style: {
			navigationBarTitleText: "getCurrentPages | 获取当前页面栈",
			enablePullDownRefresh: true
		}
	},
	{
		path: "pages/API/get-current-pages/set-page-style-disable-pull-down-refresh",
		style: {
			navigationBarTitleText: "setPageStyle pull default false",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/API/get-launch-options-sync/get-launch-options-sync",
		group: "1,1,4,0",
		style: {
			navigationBarTitleText: "uni.getLaunchOptionsSync | 获取启动参数"
		}
	},
	{
		path: "pages/API/get-enter-options-sync/get-enter-options-sync",
		group: "1,1,4,1",
		style: {
			navigationBarTitleText: "uni.getEnterOptionsSync | 获取本次启动时的参数"
		}
	},
	{
		path: "pages/API/animation-frame/animation-frame",
		group: "1,0,2",
		style: {
			navigationBarTitleText: "animationFrame | 动画帧"
		}
	},
	{
		path: "pages/API/navigator/navigator",
		group: "1,2,0",
		style: {
			navigationBarTitleText: "navigator | 页面跳转"
		}
	},
	{
		path: "pages/API/set-navigation-bar-color/set-navigation-bar-color",
		group: "1,2,2",
		style: {
			navigationBarTitleText: "uni.setNavigationBarColor | 设置导航条颜色"
		}
	},
	{
		path: "pages/API/set-navigation-bar-title/set-navigation-bar-title",
		group: "1,2,3",
		style: {
			navigationBarTitleText: "uni.setNavigationBarTitle | 设置导航条标题"
		}
	},
	{
		path: "pages/API/set-page-backgroundColorContent/set-page-backgroundColorContent",
		group: "1,2",
		style: {
			navigationBarTitleText: "设置页面容器背景色"
		}
	},
	{
		path: "pages/API/navigator/new-page/new-page-1",
		style: {
			navigationBarTitleText: "新页面-1"
		}
	},
	{
		path: "pages/API/navigator/new-page/new-page-3",
		style: {
			navigationBarTitleText: "新页面-3"
		}
	},
	{
		path: "pages/API/navigator/new-page/onLoad-call-api",
		style: {
			navigationBarTitleText: "onLoad call api"
		}
	},
	{
		path: "pages/API/navigator/new-page/onLoad",
		style: {
			navigationBarTitleText: "onLoad 生命周期测试"
		}
	},
	{
		path: "pages/API/pull-down-refresh/pull-down-refresh",
		group: "1,2,6",
		style: {
			navigationBarTitleText: "pullDownRefresh | 页面下拉刷新",
			enablePullDownRefresh: true
		}
	},
	{
		path: "pages/API/storage/storage",
		group: "1,9",
		style: {
			navigationBarTitleText: "storage | key-value本地数据存储"
		}
	},
	{
		path: "pages/API/storage/storagemanage",
		style: {
			navigationBarTitleText: "storage管理器"
		}
	},
	{
		path: "pages/API/env/env",
		group: "1,1,0",
		style: {
			navigationBarTitleText: "uni.env | 环境变量"
		}
	},
	{
		path: "pages/API/action-sheet/action-sheet",
		group: "1,4,1",
		style: {
			navigationBarTitleText: "actionSheet | 操作菜单"
		}
	},
	{
		path: "pages/API/modal/modal",
		group: "1,4,2",
		style: {
			navigationBarTitleText: "modal | 模态弹窗"
		}
	},
	{
		path: "pages/API/loading/loading",
		group: "1,4,3",
		style: {
			navigationBarTitleText: "loading | loading 加载框"
		}
	},
	{
		path: "pages/API/toast/toast",
		group: "1,4,4",
		style: {
			navigationBarTitleText: "toast | toast 轻提示"
		}
	},
	{
		path: "pages/API/load-font-face/load-font-face",
		group: "1,4,5",
		style: {
			navigationBarTitleText: "uni.loadFontFace | 动态加载字体"
		}
	},
	{
		path: "pages/API/load-font-face/load-font-face-child",
		style: {
			navigationBarTitleText: "动态加载字体-子页面"
		}
	},
	{
		path: "pages/API/get-location/get-location",
		group: "1,11,0",
		style: {
			navigationBarTitleText: "uni.getLocation | 获取当前位置"
		}
	},
	{
		path: "pages/API/location-change/location-change",
		group: "1,11,3",
		style: {
			navigationBarTitleText: "locationChange | 位置更新"
		}
	},
	{
		path: "pages/API/open-location/open-location",
		group: "1,11,1",
		style: {
			navigationBarTitleText: "uni.openLocation | 使用地图查看位置"
		}
	},
	{
		path: "pages/API/choose-location/choose-location",
		group: "1,11,2",
		style: {
			navigationBarTitleText: "uni.chooseLocation | 使用地图选择位置"
		}
	},
	{
		path: "pages/API/interceptor/interceptor",
		group: "1,1,3",
		style: {
			navigationBarTitleText: "interceptor | 拦截器"
		}
	},
	{
		path: "pages/API/interceptor/page1",
		style: {
			navigationBarTitleText: "拦截器测试页面 1"
		}
	},
	{
		path: "pages/API/interceptor/page2",
		style: {
			navigationBarTitleText: "拦截器测试页面 2"
		}
	},
	{
		path: "pages/API/request/request",
		group: "1,5,0",
		style: {
			navigationBarTitleText: "uni.request | 发起网络请求"
		}
	},
	{
		path: "pages/API/upload-file/upload-file",
		group: "1,5,1",
		style: {
			navigationBarTitleText: "uni.uploadFile | 上传文件"
		}
	},
	{
		path: "pages/API/download-file/download-file",
		group: "1,5,2",
		style: {
			navigationBarTitleText: "uni.downloadFile | 下载文件"
		}
	},
	{
		path: "pages/API/websocket/socketTask",
		style: {
			navigationBarTitleText: "socketTask"
		}
	},
	{
		path: "pages/API/websocket/websocket",
		group: "1,5,5",
		style: {
			navigationBarTitleText: "websocket"
		}
	},
	{
		path: "pages/API/connect-event-source/connect-event-source",
		group: "1,5,6",
		style: {
			navigationBarTitleText: "uni.connectEventSource | SSE"
		}
	},
	{
		path: "pages/API/unicloud/unicloud/cloud-function",
		group: "1,20,0",
		style: {
			navigationBarTitleText: "cloud function | 云函数"
		}
	},
	{
		path: "pages/API/unicloud/unicloud/cloud-object",
		group: "1,20,1",
		style: {
			navigationBarTitleText: "cloud object | 云对象"
		}
	},
	{
		path: "pages/API/unicloud/unicloud/sse-channel",
		group: "1,20,4",
		style: {
			navigationBarTitleText: "SSEChannel | 服务端通知通道"
		}
	},
	{
		path: "pages/API/get-system-info/get-system-info",
		group: "1,6,0",
		style: {
			navigationBarTitleText: "uni.getSystemInfo | 获取系统信息"
		}
	},
	{
		path: "pages/API/get-device-info/get-device-info",
		group: "1,6,1",
		style: {
			navigationBarTitleText: "uni.getDeviceInfo | 获取设备信息"
		}
	},
	{
		path: "pages/API/get-app-base-info/get-app-base-info",
		group: "1,6,3",
		style: {
			navigationBarTitleText: "uni.getAppBaseInfo | 获取APP基础信息"
		}
	},
	{
		path: "pages/API/get-system-setting/get-system-setting",
		group: "1,6,6",
		style: {
			navigationBarTitleText: "uni.getSystemSetting | 获取系统设置"
		}
	},
	{
		path: "pages/API/element-get-bounding-client-rect-async/element-get-bounding-client-rect-async",
		group: "1,30",
		style: {
			navigationBarTitleText: "getBoundingClientRectAsync | 异步获取元素信息"
		}
	},
	{
		path: "pages/API/element-get-attribute/element-get-attribute",
		group: "1,30",
		style: {
			navigationBarTitleText: "getAttribute | 获取元素的属性值"
		}
	},
	{
		path: "pages/API/element-takesnapshot/element-takesnapshot",
		group: "1,30",
		style: {
			navigationBarTitleText: "takeSnapshot | 截图",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/API/get-app-authorize-setting/get-app-authorize-setting",
		group: "1,6,4",
		style: {
			navigationBarTitleText: "uni.getAppAuthorizeSetting | 获取APP授权设置"
		}
	},
	{
		path: "pages/API/preview-image/preview-image",
		group: "1,7,1",
		style: {
			navigationBarTitleText: "uni.previewImage | 图片预览"
		}
	},
	{
		path: "pages/API/choose-image/choose-image",
		group: "1,7,0",
		style: {
			navigationBarTitleText: "uni.chooseImage | 拍照或从相册选择图片"
		}
	},
	{
		path: "pages/API/get-image-info/get-image-info",
		group: "1,7,3",
		style: {
			navigationBarTitleText: "uni.getImageInfo | 获取图片信息"
		}
	},
	{
		path: "pages/API/compress-image/compress-image",
		group: "1,7,4",
		style: {
			navigationBarTitleText: "uni.compressImage | 压缩图片"
		}
	},
	{
		path: "pages/API/choose-video/choose-video",
		group: "1,7,5",
		style: {
			navigationBarTitleText: "uni.chooseVideo | 拍摄视频或从相册中选择视频"
		}
	},
	{
		path: "pages/API/compress-video/compress-video",
		group: "1,7,8",
		style: {
			navigationBarTitleText: "uni.compressVideo | 压缩视频"
		}
	},
	{
		path: "pages/API/choose-media/choose-media",
		group: "1,7,9",
		style: {
			navigationBarTitleText: "uni.chooseMedia | 拍摄或从相册选择图片或视频"
		}
	},
	{
		path: "pages/API/choose-media/fullscreen-video",
		style: {
			navigationStyle: "custom"
		}
	},
	{
		path: "pages/API/choose-file/choose-file",
		group: "1,7,10",
		style: {
			navigationBarTitleText: "uni.chooseFile | 选择文件"
		}
	},
	{
		path: "pages/API/get-network-type/get-network-type",
		group: "1,5,3",
		style: {
			navigationBarTitleText: "uni.getNetworkType | 获取网络类型"
		}
	},
	{
		path: "pages/API/network-status-change/network-status-change",
		group: "1,5,4",
		style: {
			navigationBarTitleText: "NetworkStatusChange | 监听网络状态变化"
		}
	},
	{
		path: "pages/API/page-scroll-to/page-scroll-to",
		group: "1,2,7",
		style: {
			navigationBarTitleText: "uni.pageScrollTo | 将页面滚动到指定位置"
		}
	},
	{
		path: "pages/API/event-bus/event-bus",
		group: "1,1,1",
		style: {
			navigationBarTitleText: "eventBus | 事件总线"
		}
	},
	{
		path: "pages/API/unicloud/unicloud/cloud-storage",
		group: "1,20,2",
		style: {
			navigationBarTitleText: "cloud storage | 云存储"
		}
	},
	{
		path: "pages/API/unicloud/unicloud/database",
		group: "1,20,3",
		style: {
			navigationBarTitleText: "database | 数据库"
		}
	},
	{
		path: "pages/API/get-battery-info/get-battery-info",
		group: "1,6,8",
		style: {
			navigationBarTitleText: "uni.getBatteryInfo | 获取电池电量信息"
		}
	},
	{
		path: "pages/API/get-window-info/get-window-info",
		group: "1,6,2",
		style: {
			navigationBarTitleText: "uni.getWindowInfo | 获取窗口信息"
		}
	},
	{
		path: "pages/API/get-window-info/window-area",
		style: {
			navigationBarTitleText: "window area",
			navigationStyle: "custom"
		}
	},
	{
		path: "pages/API/element-draw/element-draw",
		group: "1,30",
		style: {
			navigationBarTitleText: "getDrawableContext | 获取绘制对象",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/API/element-request-fullscreen/element-request-fullscreen",
		group: "1,30",
		style: {
			navigationBarTitleText: "requestFullscreen | 请求全屏显示"
		}
	},
	{
		path: "pages/API/element-request-fullscreen/element-request-fullscreen-bugs",
		style: {
			navigationBarTitleText: "request-fullscreen-bugs"
		}
	},
	{
		path: "pages/API/facial-recognition-meta-info/facial-recognition-meta-info",
		group: "1,12,2",
		style: {
			navigationBarTitleText: "facialRecognitionMetaInfo | 金融级实人认证",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/API/get-uni-verify-manager/get-uni-verify-manager",
		group: "1,12,1",
		style: {
			navigationBarTitleText: "uni.getUniVerifyManager | App一键登录",
			navigationBarTextStyle: "white",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/API/get-uni-verify-manager/uni-verify-custom-page",
		style: {
			navigationBarTextStyle: "black",
			navigationStyle: "custom"
		}
	},
	{
		path: "pages/API/get-uni-verify-manager/full-webview-page",
		style: {
			navigationBarTextStyle: "white",
			navigationStyle: "custom"
		}
	},
	{
		path: "pages/API/rpx2px/rpx2px",
		group: "1,4,6",
		style: {
			navigationBarTitleText: "uni.rpx2px | 将rpx单位值转换成px",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/API/create-request-permission-listener/create-request-permission-listener",
		group: "1,6,17",
		style: {
			navigationBarTitleText: "uni.createRequestPermissionListener | 监听权限申请",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/API/request-payment/request-payment",
		group: "1,14,0",
		style: {
			navigationBarTitleText: "uni.requestPayment | 支付",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/API/request-merchant-transfer/request-merchant-transfer",
		group: "1,14,2",
		style: {
			navigationBarTitleText: "uni.requestMerchantTransfer | 转账确认收款",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/API/create-rewarded-video-ad/create-rewarded-video-ad",
		group: "1,13,0",
		style: {
			navigationBarTitleText: "uni.createRewardedVideoAd | 激励视频广告",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/API/request-payment/request-payment/request-payment-uni-pay",
		group: "1,14",
		style: {
			navigationBarTitleText: "uni-pay示例",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/API/request-payment/request-payment/order-detail",
		style: {
			navigationBarTitleText: "订单详情示例",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/API/make-phone-call/make-phone-call",
		group: "1,6,9",
		style: {
			navigationBarTitleText: "uni.makePhoneCall | 打电话"
		}
	},
	{
		path: "pages/API/clipboard/clipboard",
		group: "1,6,11",
		style: {
			navigationBarTitleText: "clipboard | 剪切板"
		}
	},
	{
		path: "pages/API/scan-code/scan-code",
		group: "1,6,18",
		style: {
			navigationBarTitleText: "uni.scanCode | 扫码"
		}
	},
	{
		path: "pages/API/theme-change/theme-change",
		group: "1,4,7",
		style: {
			navigationBarTitleText: "themeChange | 主题切换",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/API/get-element-by-id/get-element-by-id",
		group: "1,3,0",
		style: {
			navigationBarTitleText: "uni.getElementById | 通过 id 获取 element"
		}
	},
	{
		path: "pages/API/get-element-by-id/get-element-by-id-multiple-root-node",
		style: {
			navigationBarTitleText: "获取节点-多根节点"
		}
	},
	{
		path: "pages/API/create-selector-query/create-selector-query",
		group: "1,3,2",
		style: {
			navigationBarTitleText: "uni.createSelectorQuery | 创建 SelectorQuery 实例"
		}
	},
	{
		path: "pages/API/animate/animate",
		group: "1,30",
		style: {
			navigationBarTitleText: "animate | 动画"
		}
	},
	{
		path: "pages/API/create-selector-query/create-selector-query-onScroll",
		style: {
			navigationBarTitleText: "createSelectorQuery"
		}
	},
	{
		path: "pages/API/get-native-view/element-getnativeview",
		group: "1,30",
		style: {
			navigationBarTitleText: "getNativeView | 获取原生 View",
			backgroundColor: "#F8F8F8"
		}
	},
	{
		path: "pages/API/uni-resize-observer/uni-resize-observer",
		group: "1,30",
		style: {
			navigationBarTitleText: "UniResizeObserver"
		}
	},
	{
		path: "pages/API/provider/provider",
		group: "1,1,6",
		style: {
			navigationBarTitleText: "provider | 服务提供商"
		}
	},
	{
		path: "pages/API/privacy/privacy",
		group: "1,1,8",
		style: {
			navigationBarTitleText: "privacy | 隐私信息授权"
		}
	},
	{
		path: "pages/API/base64/base64",
		group: "1,1,2",
		style: {
			navigationBarTitleText: "Base64"
		}
	},
	{
		path: "pages/API/uni-push/uni-push",
		group: "1,16,0",
		style: {
			navigationBarTitleText: "uni-push示例"
		}
	},
	{
		path: "pages/API/report/report",
		group: "1,17,0",
		style: {
			navigationBarTitleText: "uni.report | 统计采集上报"
		}
	},
	{
		path: "pages/API/dialog-page/dialog-page",
		group: "1,2,1",
		style: {
			navigationBarTitleText: "dialogPage"
		}
	},
	{
		path: "pages/API/dialog-page/next-page",
		style: {
			navigationBarTitleText: "dialogNextPage"
		}
	},
	{
		path: "pages/API/dialog-page/dialog-1",
		style: {
			navigationBarTitleText: "dialogPage1"
		}
	},
	{
		path: "pages/API/dialog-page/dialog-1-1",
		style: {
			navigationBarTitleText: "dialogPage1-1"
		}
	},
	{
		path: "pages/API/dialog-page/dialog-2",
		style: {
			navigationBarTitleText: "dialogPage2"
		}
	},
	{
		path: "pages/API/dialog-page/dialog-3",
		style: {
			navigationBarTitleText: "dialogPage3",
			navigationBarTextStyle: "black",
			backgroundColorContent: "#007aff",
			navigationStyle: "default"
		}
	},
	{
		path: "pages/API/dialog-page/dialog-4",
		style: {
			navigationBarTitleText: "dialogPage4",
			navigationBarTextStyle: "black"
		}
	},
	{
		path: "pages/API/dialog-page/dialog-5",
		style: {
			navigationBarTitleText: "dialogPage5"
		}
	},
	{
		path: "pages/API/dialog-page/dialog-textarea",
		style: {
			navigationBarTitleText: "dialog-textarea"
		}
	},
	{
		path: "pages/API/dialog-page/dialog-input",
		style: {
			navigationBarTitleText: "dialog-input"
		}
	},
	{
		path: "pages/API/create-interstitial-ad/create-interstitial-ad",
		group: "1,13,1",
		style: {
			navigationBarTitleText: "uni.createInterstitialAd | 插屏广告",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/API/get-background-audio-manager/get-background-audio-manager",
		group: "1,7,13",
		style: {
			navigationBarTitleText: "uni.getBackgroundAudioManager | 背景音频",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/API/set-inner-audio-option/set-inner-audio-option",
		group: "1,7,12",
		style: {
			navigationBarTitleText: "uni.setInnerAudioOption | 音频播放选项",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/API/get-recorder-manager/get-recorder-manager",
		group: "1,7,14",
		style: {
			navigationBarTitleText: "uni.getRecorderManager | 录音",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/API/share-with-system/share-with-system",
		group: "1,15,1",
		style: {
			navigationBarTitleText: "uni.shareWithSystem | 系统分享",
			enablePullDownRefresh: false
		}
	},
	{
		path: "pages/API/open-app-authorize-setting/open-app-authorize-setting",
		group: "1,6,5",
		style: {
			navigationBarTitleText: "uni.openAppAuthorizeSetting | 跳转系统授权管理页"
		}
	},
	{
		path: "pages/API/is-simulator/is-simulator",
		group: "1,6,19",
		style: {
			navigationBarTitleText: "uni.isSimulator | 判断是否为模拟器"
		}
	},
	{
		path: "pages/API/get-accessibility-info/get-accessibility-info",
		group: "1,6,20",
		style: {
			navigationBarTitleText: "uni.getAccessibilityInfo | 获取无障碍服务信息"
		}
	},
	{
		path: "pages/API/capture-screen/capture-screen",
		group: "1,6,16",
		style: {
			navigationBarTitleText: "captureScreen | 截屏事件"
		}
	},
	{
		path: "pages/API/open-document/open-document",
		group: "1,8,1",
		style: {
			navigationBarTitleText: "uni.openDocument | 打开文档"
		}
	},
	{
		path: "pages/API/get-video-info/get-video-info",
		group: "1,7,7",
		style: {
			navigationBarTitleText: "uni.getVideoInfo | 获取视频信息"
		}
	},
	{
		path: "pages/API/save-image-to-photos-album/save-image-to-photos-album",
		group: "1,7,2",
		style: {
			navigationBarTitleText: "uni.saveImageToPhotosAlbum | 保存图片到相册"
		}
	},
	{
		path: "pages/API/save-video-to-photos-album/save-video-to-photos-album",
		group: "1,7,6",
		style: {
			navigationBarTitleText: "uni.saveVideoToPhotosAlbum | 保存视频到相册"
		}
	},
	{
		path: "pages/API/keyboard/keyboard",
		group: "1,6,21",
		style: {
			navigationBarTitleText: "keyboard | 键盘"
		}
	},
	{
		path: "pages/API/create-worker/create-worker",
		group: "1,19,0",
		style: {
			navigationBarTitleText: "uni.createWorker | 创建 worker 对象"
		}
	},
	{
		path: "pages/API/create-worker/uts-create-worker",
		group: "1,19,0",
		style: {
			navigationBarTitleText: "uni.createWorker | UTS插件示例"
		}
	},
	{
		path: "pages/tabBar/CSS",
		style: {
			navigationBarTitleText: "CSS",
			backgroundColorContent: "@tabBarPagebackgroundColorContent"
		}
	},
	{
		path: "pages/tabBar/template",
		style: {
			navigationBarTitleText: "模板",
			backgroundColorContent: "@tabBarPagebackgroundColorContent"
		}
	},
	{
		path: "uni_modules/uni-pay-x/pages/success/success",
		style: {
			navigationBarTitleText: "",
			backgroundColor: "#F8F8F8",
			navigationBarBackgroundColor: "#007aff",
			navigationBarTextStyle: "white"
		}
	},
	{
		path: "uni_modules/uni-pay-x/pages/ad-interactive-webview/ad-interactive-webview",
		style: {
			navigationBarTitleText: "ad",
			backgroundColor: "#F8F8F8"
		}
	},
	{
		path: "uni_modules/uni-pay-x/pages/pay-desk/pay-desk",
		style: {
			navigationBarTitleText: "收银台",
			backgroundColor: "#F8F8F8"
		}
	},
	{
		path: "uni_modules/uni-openLocation/pages/openLocation/openLocation",
		style: {
			navigationBarTitleText: " ",
			navigationStyle: "custom",
			disableSwipeBack: false
		}
	},
	{
		path: "pages/component/button/privacy",
		style: {
			navigationBarTitleText: ""
		}
	},
	{
		path: "uni_modules/uni-upgrade-center-app/pages/uni-app-x/upgrade-popup",
		style: {
			navigationBarTitleText: ""
		}
	},
	{
		path: "pages/component/native-view/native-view-time-picker-dialog",
		style: {
			navigationBarTitleText: ""
		}
	},
	{
		path: "pages/component/image/image-long",
		style: {
			navigationBarTitleText: ""
		}
	},
	{
		path: "pages/API/request/requestTask",
		style: {
			navigationBarTitleText: "requestTask | 发起流式请求"
		}
	}
];
export const subPackages = [
	{
		root: "pages/API/get-file-system-manager",
		pages: [
			{
				path: "get-file-system-manager",
				group: "1,8,0",
				style: {
					navigationBarTitleText: "uni.getFileSystemManager | 获取文件管理器"
				}
			},
			{
				path: "filemanage",
				style: {
					navigationBarTitleText: "沙盒文件管理器"
				}
			},
			{
				path: "testStatic",
				style: {
					navigationBarTitleText: "测试文件管理类直接操作Static"
				}
			}
		]
	},
	{
		root: "pages/API/create-inner-audio-context",
		pages: [
			{
				path: "create-inner-audio-context",
				group: "1,7,11",
				style: {
					navigationBarTitleText: "uni.createInnerAudioContext | 音频播放"
				}
			},
			{
				path: "inner-audio-format",
				style: {
					navigationBarTitleText: "inner-audio-format"
				}
			},
			{
				path: "inner-audio-path",
				style: {
					navigationBarTitleText: "inner-audio-path"
				}
			},
			{
				path: "inner-audio-mult",
				style: {
					navigationBarTitleText: "inner-audio-mult"
				}
			}
		]
	},
	{
		root: "pages/template",
		pages: [
			{
				path: "nav-transparent/nav-transparent",
				style: {
					navigationBarTitleText: "渐变导航栏",
					navigationStyle: "custom"
				}
			},
			{
				path: "list-news/list-news",
				style: {
					navigationBarTitleText: "列表到详情示例（吸顶）"
				}
			},
			{
				path: "list-news/detail/detail",
				style: {
					navigationBarTitleText: "详情示例"
				}
			},
			{
				path: "drop-card/drop-card",
				style: {
					navigationBarTitleText: "drop-card"
				}
			},
			{
				path: "swiper-list/swiper-list",
				style: {
					navigationBarTitleText: "swiper-list"
				}
			},
			{
				path: "swiper-list2/swiper-list2",
				style: {
					navigationBarTitleText: "swiper-list2"
				}
			},
			{
				path: "slider-100/slider-100",
				style: {
					navigationBarTitleText: "slider x 100"
				}
			},
			{
				path: "swiper-vertical-video/swiper-vertical-video",
				style: {
					navigationStyle: "custom",
					backgroundColorContent: "#000000"
				}
			},
			{
				path: "scroll-sticky/scroll-sticky",
				style: {
					navigationBarTitleText: "scroll-view自定义滚动吸顶"
				}
			},
			{
				path: "scroll-fold-nav/scroll-fold-nav",
				style: {
					navigationStyle: "custom",
					navigationBarTextStyle: "black"
				}
			},
			{
				path: "custom-refresher/custom-refresher",
				style: {
					navigationBarTitleText: "自定义下拉刷新"
				}
			},
			{
				path: "half-screen/half-screen",
				style: {
					navigationBarTitleText: "半屏弹窗"
				}
			},
			{
				path: "long-list/long-list",
				style: {
					navigationBarTitleText: "Android顶部搜索框随时下移长列表",
					enablePullDownRefresh: true
				}
			},
			{
				path: "long-list2/long-list2",
				style: {
					navigationBarTitleText: "顶部banner长列表嵌套滚动示例",
					enablePullDownRefresh: true
				}
			},
			{
				path: "long-list-nested/long-list-nested",
				style: {
					navigationBarTitleText: "顶部banner长列表嵌套滚动示例",
					enablePullDownRefresh: true
				}
			},
			{
				path: "pull-zoom-image/pull-zoom-image",
				style: {
					navigationStyle: "custom"
				}
			},
			{
				path: "long-waterflow-nested/long-waterflow-nested",
				style: {
					navigationBarTitleText: "顶部banner瀑布流长列表嵌套滚动示例",
					enablePullDownRefresh: true
				}
			},
			{
				path: "navbar-lite/navbar-lite",
				style: {
					navigationBarTitleText: "自定义导航栏",
					navigationStyle: "custom"
				}
			},
			{
				path: "custom-tab-bar/custom-tab-bar",
				style: {
					navigationBarTitleText: "自定义TabBar"
				}
			},
			{
				path: "calendar/calendar",
				style: {
					navigationBarTitleText: "日历"
				}
			},
			{
				path: "schema/schema",
				style: {
					navigationBarTitleText: "打开schema示例"
				}
			},
			{
				path: "custom-long-list/custom-long-list",
				style: {
					navigationBarTitleText: "回收复用长列表",
					enablePullDownRefresh: false
				}
			},
			{
				path: "long-list-batch/long-list-batch",
				style: {
					navigationBarTitleText: "分批加载长列表"
				}
			},
			{
				path: "test-background-color-content/test-background-color-content",
				style: {
					navigationBarTitleText: "",
					backgroundColorContent: "#fffae8"
				}
			},
			{
				path: "issue-14765/issue-14765",
				style: {
					navigationBarTitleText: "issue-14765"
				}
			},
			{
				path: "test-uts-button/test-uts-button",
				style: {
					navigationBarTitleText: ""
				}
			},
			{
				path: "about/about",
				style: {
					navigationBarTitleText: "关于"
				}
			},
			{
				path: "custom-long-list/detail",
				style: {
					navigationBarTitleText: "测试详情页"
				}
			}
		]
	},
	{
		root: "pages/CSS",
		pages: [
			{
				path: "background/background-color",
				group: "2,8,1",
				style: {
					navigationBarTitleText: "background-color"
				}
			},
			{
				path: "background/background-color-check-box",
				style: {
					navigationBarTitleText: "background-color-check-box"
				}
			},
			{
				path: "background/background-image",
				group: "2,8,2",
				style: {
					navigationBarTitleText: "background-image"
				}
			},
			{
				path: "border/border-bottom",
				group: "2,13,22",
				style: {
					navigationBarTitleText: "border-bottom"
				}
			},
			{
				path: "border/border-color",
				group: "2,13,11",
				style: {
					navigationBarTitleText: "border-color"
				}
			},
			{
				path: "border/border-left",
				group: "2,13,23",
				style: {
					navigationBarTitleText: "border-left"
				}
			},
			{
				path: "border/border-radius",
				group: "2,13,16",
				style: {
					navigationBarTitleText: "border-radius"
				}
			},
			{
				path: "border/border-right",
				group: "2,13,24",
				style: {
					navigationBarTitleText: "border-right"
				}
			},
			{
				path: "border/border-style",
				group: "2,13,1",
				style: {
					navigationBarTitleText: "border-style"
				}
			},
			{
				path: "border/border-top",
				group: "2,13,21",
				style: {
					navigationBarTitleText: "border-top"
				}
			},
			{
				path: "border/border-width",
				group: "2,13,6",
				style: {
					navigationBarTitleText: "border-width"
				}
			},
			{
				path: "border/border",
				group: "2,13,0",
				style: {
					navigationBarTitleText: "border"
				}
			},
			{
				path: "border/complex-border/complex-border",
				group: "2,13",
				style: {
					navigationBarTitleText: "border属性组合示例"
				}
			},
			{
				path: "border/dynamic-border",
				group: "2,13",
				style: {
					navigationBarTitleText: "border动态修改"
				}
			},
			{
				path: "border/border-update",
				group: "2,13",
				style: {
					navigationBarTitleText: "border更新样式"
				}
			},
			{
				path: "box-shadow/box-shadow",
				group: "2,15",
				style: {
					navigationBarTitleText: "box-shadow"
				}
			},
			{
				path: "display/flex",
				group: "2,5",
				style: {
					navigationBarTitleText: "flex"
				}
			},
			{
				path: "display/none",
				group: "2,5",
				style: {
					navigationBarTitleText: "none"
				}
			},
			{
				path: "flex/align-content",
				group: "2,7,6",
				style: {
					navigationBarTitleText: "align-content"
				}
			},
			{
				path: "flex/align-items",
				group: "2,7,4",
				style: {
					navigationBarTitleText: "align-items"
				}
			},
			{
				path: "flex/flex-basis",
				group: "2,7,9",
				style: {
					navigationBarTitleText: "flex-basis"
				}
			},
			{
				path: "flex/flex-direction",
				group: "2,7,1",
				style: {
					navigationBarTitleText: "flex-direction"
				}
			},
			{
				path: "flex/flex-flow",
				group: "2,7,10",
				style: {
					navigationBarTitleText: "flex-flow"
				}
			},
			{
				path: "flex/flex-grow",
				group: "2,7,7",
				style: {
					navigationBarTitleText: "flex-grow"
				}
			},
			{
				path: "flex/flex-shrink",
				group: "2,7,8",
				style: {
					navigationBarTitleText: "flex-shrink"
				}
			},
			{
				path: "flex/flex",
				group: "2,7,0",
				style: {
					navigationBarTitleText: "flex"
				}
			},
			{
				path: "flex/justify-content",
				group: "2,7,2",
				style: {
					navigationBarTitleText: "justify-content"
				}
			},
			{
				path: "layout/height",
				group: "2,1,0",
				style: {
					navigationBarTitleText: "height"
				}
			},
			{
				path: "layout/max-height",
				group: "2,1,2",
				style: {
					navigationBarTitleText: "max-height"
				}
			},
			{
				path: "layout/max-width",
				group: "2,0,2",
				style: {
					navigationBarTitleText: "max-width"
				}
			},
			{
				path: "layout/min-height",
				group: "2,1,1",
				style: {
					navigationBarTitleText: "min-height"
				}
			},
			{
				path: "layout/min-width",
				group: "2,0,1",
				style: {
					navigationBarTitleText: "min-width"
				}
			},
			{
				path: "layout/position",
				group: "2,9,0",
				style: {
					navigationBarTitleText: "position"
				}
			},
			{
				path: "layout/width",
				group: "2,0,0",
				style: {
					navigationBarTitleText: "width"
				}
			},
			{
				path: "layout/z-index",
				group: "2,10",
				style: {
					navigationBarTitleText: "z-index"
				}
			},
			{
				path: "layout/visibility",
				group: "2,6",
				style: {
					navigationBarTitleText: "visibility"
				}
			},
			{
				path: "margin/margin-bottom",
				group: "2,2,2",
				style: {
					navigationBarTitleText: "margin-bottom"
				}
			},
			{
				path: "margin/margin-left",
				group: "2,2,3",
				style: {
					navigationBarTitleText: "margin-left"
				}
			},
			{
				path: "margin/margin-right",
				group: "2,2,4",
				style: {
					navigationBarTitleText: "margin-right"
				}
			},
			{
				path: "margin/margin-top",
				group: "2,2,1",
				style: {
					navigationBarTitleText: "margin-top"
				}
			},
			{
				path: "margin/margin",
				group: "2,2,0",
				style: {
					navigationBarTitleText: "margin"
				}
			},
			{
				path: "overflow/overflow",
				group: "2,11",
				style: {
					navigationBarTitleText: "overflow"
				}
			},
			{
				path: "padding/padding-bottom",
				group: "2,3,2",
				style: {
					navigationBarTitleText: "padding-bottom"
				}
			},
			{
				path: "padding/padding-left",
				group: "2,3,3",
				style: {
					navigationBarTitleText: "padding-left"
				}
			},
			{
				path: "padding/padding-right",
				group: "2,3,4",
				style: {
					navigationBarTitleText: "padding-right"
				}
			},
			{
				path: "padding/padding-top",
				group: "2,3,1",
				style: {
					navigationBarTitleText: "padding-top"
				}
			},
			{
				path: "padding/padding",
				group: "2,3,0",
				style: {
					navigationBarTitleText: "padding"
				}
			},
			{
				path: "text/color",
				group: "2,14,0",
				style: {
					navigationBarTitleText: "color"
				}
			},
			{
				path: "text/font-family",
				group: "2,14,2",
				style: {
					navigationBarTitleText: "font-family"
				}
			},
			{
				path: "text/font-size",
				group: "2,14,1",
				style: {
					navigationBarTitleText: "font-size"
				}
			},
			{
				path: "text/font-style",
				group: "2,14,3",
				style: {
					navigationBarTitleText: "font-style"
				}
			},
			{
				path: "text/font-weight",
				group: "2,14,4",
				style: {
					navigationBarTitleText: "font-weight"
				}
			},
			{
				path: "text/letter-spacing",
				group: "2,14,15",
				style: {
					navigationBarTitleText: "letter-spacing"
				}
			},
			{
				path: "text/white-space",
				group: "2,14,16",
				style: {
					navigationBarTitleText: "white-space"
				}
			},
			{
				path: "text/font-family-icon",
				style: {
					navigationBarTitleText: "font-family: uni-icon"
				}
			},
			{
				path: "text/line-height",
				group: "2,14,13",
				style: {
					navigationBarTitleText: "line-height"
				}
			},
			{
				path: "text/text-align",
				group: "2,14,10",
				style: {
					navigationBarTitleText: "text-align"
				}
			},
			{
				path: "text/text-overflow",
				group: "2,14,11",
				style: {
					navigationBarTitleText: "text-overflow"
				}
			},
			{
				path: "text/text-decoration-line",
				group: "2,14,6",
				style: {
					navigationBarTitleText: "text-decoration-line"
				}
			},
			{
				path: "text/text-shadow",
				group: "2,14,12",
				style: {
					navigationBarTitleText: "text-shadow"
				}
			},
			{
				path: "transition/transition",
				group: "2,16",
				style: {
					navigationBarTitleText: "transition"
				}
			},
			{
				path: "pointer-events/pointer-events",
				group: "2,18",
				style: {
					navigationBarTitleText: "pointer-events"
				}
			},
			{
				path: "pointer-events/issue-20923",
				style: {
					navigationBarTitleText: "issue-20923"
				}
			},
			{
				path: "transform/translate",
				group: "2,17",
				style: {
					navigationBarTitleText: "translate"
				}
			},
			{
				path: "transform/scale",
				group: "2,17",
				style: {
					navigationBarTitleText: "scale"
				}
			},
			{
				path: "transform/rotate",
				group: "2,17",
				style: {
					navigationBarTitleText: "rotate"
				}
			},
			{
				path: "variable/variable",
				group: "2,20",
				style: {
					navigationBarTitleText: "预置css变量",
					pageOrientation: "auto"
				}
			},
			{
				path: "overflow/overflow-visible-event",
				group: "2,11",
				style: {
					navigationBarTitleText: "overflow-visible-event"
				}
			},
			{
				path: "overflow/issue-21223",
				style: {
					navigationBarTitleText: "issue-21223"
				}
			},
			{
				path: "transform/transform-origin",
				style: {
					navigationBarTitleText: "transform-origin"
				}
			},
			{
				path: "variable/custom_variable",
				group: "2,20",
				style: {
					navigationBarTitleText: "自定义css变量",
					pageOrientation: "auto"
				}
			},
			{
				path: "function/function",
				group: "2,20",
				style: {
					navigationBarTitleText: "env 函数",
					pageOrientation: "auto"
				}
			},
			{
				path: "transition/transition-duration",
				style: {
					navigationBarTitleText: ""
				}
			},
			{
				path: "function/rgb",
				group: "2,20",
				style: {
					navigationBarTitleText: "rgb 函数"
				}
			},
			{
				path: "function/rgba",
				group: "2,20",
				style: {
					navigationBarTitleText: "rgba 函数"
				}
			},
			{
				path: "function/url",
				group: "2,20",
				style: {
					navigationBarTitleText: "url 函数"
				}
			}
		]
	}
];
export const globalStyle = {
	pageOrientation: "portrait",
	navigationBarTitleText: "Hello uniapp x",
	navigationBarTextStyle: "@navigationBarTextStyle",
	navigationBarBackgroundColor: "@navigationBarBackgroundColor",
	backgroundColorContent: "@backgroundColorContent",
	backgroundColor: "@backgroundColor",
	backgroundTextStyle: "@backgroundTextStyle"
};
export const tabBar = {
	color: "@tabBarColor",
	selectedColor: "@tabBarSelectedColor",
	borderStyle: "@tabBarBorderStyle",
	backgroundColor: "@tabBarBackgroundColor",
	list: [
		{
			pagePath: "pages/tabBar/component",
			iconPath: "@tabBarComponentIconPath",
			selectedIconPath: "@tabBarComponentSelectedIconPath",
			text: "组件"
		},
		{
			pagePath: "pages/tabBar/API",
			iconPath: "@tabBarAPIIconPath",
			selectedIconPath: "@tabBarAPISelectedIconPath",
			text: "接口"
		},
		{
			pagePath: "pages/tabBar/CSS",
			iconPath: "@tabBarCSSIconPath",
			selectedIconPath: "@tabBarCSSSelectedIconPath",
			text: "CSS"
		},
		{
			pagePath: "pages/tabBar/template",
			iconPath: "@tabBarTemplateIconPath",
			selectedIconPath: "@tabBarTemplateSelectedIconPath",
			text: "模板"
		}
	]
};
export const condition = {
	current: 0,
	list: [
		{
			name: "",
			path: "",
			query: ""
		}
	]
};
export const groups = [
	{
		id: "component",
		name: "组件",
		children: [
			{
				id: "component.global-properties-events",
				name: "全局属性和事件"
			},
			{
				id: "component.view-container",
				name: "视图容器",
				children: [
					{
						id: "component.view-container.view",
						name: "view"
					},
					{
						id: "component.view-container.scroll-view",
						name: "scroll-view"
					},
					{
						id: "component.view-container.swiper",
						name: "swiper"
					},
					{
						id: "component.view-container.match-media",
						name: "match-media"
					},
					{
						id: "component.view-container.movable",
						name: "movable",
						children: [
							null,
							{
								id: "component.view-container.movable.movable-view",
								name: "movable-view"
							}
						]
					},
					{
						id: "component.view-container.cover",
						name: "cover",
						children: [
							{
								id: "component.view-container.cover.cover-view",
								name: "cover-view"
							}
						]
					},
					{
						id: "component.view-container.list-view",
						name: "list-view"
					},
					{
						id: "component.view-container.waterflow",
						name: "waterflow"
					},
					{
						id: "component.view-container.sticky",
						name: "sticky",
						children: [
							{
								id: "component.view-container.sticky.sticky-header",
								name: "sticky-header"
							},
							{
								id: "component.view-container.sticky.sticky-section",
								name: "sticky-section"
							}
						]
					},
					{
						id: "component.view-container.nested-scroll",
						name: "nested-scroll",
						children: [
							{
								id: "component.view-container.nested-scroll.nested-scroll-header",
								name: "nested-scroll-header"
							},
							{
								id: "component.view-container.nested-scroll.nested-scroll-body",
								name: "nested-scroll-body"
							}
						]
					},
					{
						id: "component.view-container.share-element",
						name: "share-element"
					}
				]
			},
			{
				id: "component.basic-content",
				name: "基础内容",
				children: [
					null,
					{
						id: "component.basic-content.text",
						name: "text"
					},
					{
						id: "component.basic-content.rich-text",
						name: "rich-text"
					},
					{
						id: "component.basic-content.progress",
						name: "progress"
					},
					{
						id: "component.basic-content.native-view",
						name: "native-view"
					}
				]
			},
			{
				id: "component.form-component",
				name: "表单组件",
				children: [
					{
						id: "component.form-component.button",
						name: "button"
					},
					{
						id: "component.form-component.checkbox",
						name: "checkbox"
					},
					{
						id: "component.form-component.form",
						name: "form"
					},
					{
						id: "component.form-component.input",
						name: "input"
					},
					{
						id: "component.form-component.editor",
						name: "editor"
					},
					{
						id: "component.form-component.label",
						name: "label"
					},
					{
						id: "component.form-component.picker",
						name: "picker"
					},
					{
						id: "component.form-component.picker-view",
						name: "picker-view"
					},
					{
						id: "component.form-component.radio",
						name: "radio"
					},
					{
						id: "component.form-component.slider",
						name: "slider"
					},
					{
						id: "component.form-component.switch",
						name: "switch"
					},
					{
						id: "component.form-component.textarea",
						name: "textarea"
					}
				]
			},
			{
				id: "component.navigation",
				name: "导航",
				children: [
					{
						id: "component.navigation.navigator",
						name: "navigator"
					}
				]
			},
			{
				id: "component.media",
				name: "媒体组件",
				children: [
					{
						id: "component.media.image",
						name: "image"
					},
					{
						id: "component.media.video",
						name: "video"
					},
					null,
					{
						id: "component.media.camera",
						name: "camera"
					},
					{
						id: "component.media.live-player",
						name: "live-player"
					},
					{
						id: "component.media.live-pusher",
						name: "live-pusher"
					}
				]
			},
			{
				id: "component.map",
				name: "地图"
			},
			{
				id: "component.canvas",
				name: "画布"
			},
			{
				id: "component.ad",
				name: "广告"
			},
			{
				id: "component.web-view",
				name: "网页"
			},
			{
				id: "component.page-meta",
				name: "page-meta"
			},
			{
				id: "component.unicloud",
				name: "unicloud"
			}
		]
	},
	{
		id: "API",
		name: "API",
		children: [
			{
				id: "api.global",
				name: "全局",
				children: [
					{
						id: "api.global.getApp",
						name: "getApp"
					},
					{
						id: "api.global.getCurrentPages",
						name: "getCurrentPages"
					},
					{
						id: "api.global.animationFrame",
						name: "animationFrame"
					}
				]
			},
			{
				id: "api.base",
				name: "基础",
				children: [
					{
						id: "api.base.env",
						name: "uni.env"
					},
					{
						id: "api.base.eventBus",
						name: "eventBus"
					},
					{
						id: "api.base.base64",
						name: "Base64"
					},
					{
						id: "api.base.interceptor",
						name: "interceptor"
					},
					{
						id: "api.base.launch",
						name: "启动",
						children: [
							{
								id: "api.base.launch.getLaunchOptionsSync",
								name: "uni.getLaunchOptionsSync"
							},
							{
								id: "api.base.launch.getEnterOptionsSync",
								name: "uni.getEnterOptionsSync"
							}
						]
					},
					{
						id: "api.base.exit",
						name: "uni.exit"
					},
					{
						id: "api.base.provider",
						name: "provider"
					},
					null,
					{
						id: "api.base.privacy",
						name: "privacy"
					}
				]
			},
			{
				id: "api.page",
				name: "页面和路由",
				children: [
					{
						id: "api.page.navigator",
						name: "navigator"
					},
					{
						id: "api.page.dialogPage",
						name: "dialogPage"
					},
					{
						id: "api.page.setNavigationBarColor",
						name: "uni.setNavigationBarColor"
					},
					{
						id: "api.page.setNavigationBarTitle",
						name: "uni.setNavigationBarTitle"
					},
					null,
					null,
					{
						id: "api.page.pullDownRefresh",
						name: "pullDownRefresh"
					},
					{
						id: "api.page.pageScrollTo",
						name: "uni.pageScrollTo"
					}
				]
			},
			{
				id: "api.dom",
				name: "element 和 node",
				children: [
					{
						id: "api.dom.getElementById",
						name: "uni.getElementById"
					},
					null,
					{
						id: "api.dom.createSelectorQuery",
						name: "uni.createSelectorQuery"
					}
				]
			},
			{
				id: "api.ui",
				name: "界面",
				children: [
					{
						id: "api.ui.createIntersectionObserver",
						name: "uni.createIntersectionObserver"
					},
					{
						id: "api.ui.actionSheet",
						name: "actionSheet"
					},
					{
						id: "api.ui.modal",
						name: "modal"
					},
					{
						id: "api.ui.loading",
						name: "loading"
					},
					{
						id: "api.ui.toast",
						name: "toast"
					},
					{
						id: "api.ui.loadFontFace",
						name: "uni.loadFontFace"
					},
					{
						id: "api.ui.rpx2px",
						name: "uni.rpx2px"
					},
					{
						id: "api.ui.themeChange",
						name: "themeChange"
					}
				]
			},
			{
				id: "api.network",
				name: "网络",
				children: [
					{
						id: "api.network.request",
						name: "uni.request"
					},
					{
						id: "api.network.uploadFile",
						name: "uni.uploadFile"
					},
					{
						id: "api.network.downloadFile",
						name: "uni.downloadFile"
					},
					{
						id: "api.network.getNetworkType",
						name: "uni.getNetworkType"
					},
					{
						id: "api.network.networkStatusChange",
						name: "NetworkStatusChange"
					},
					{
						id: "api.network.websocket",
						name: "websocket"
					},
					{
						id: "api.network.connectEventSource",
						name: "uni.connectEventSource"
					}
				]
			},
			{
				id: "api.device",
				name: "设备",
				children: [
					{
						id: "api.device.getSystemInfo",
						name: "getSystemInfo"
					},
					{
						id: "api.device.getDeviceInfo",
						name: "uni.getDeviceInfo"
					},
					{
						id: "api.device.getWindowInfo",
						name: "uni.getWindowInfo"
					},
					{
						id: "api.device.getAppBaseInfo",
						name: "uni.getAppBaseInfo"
					},
					{
						id: "api.device.getAppAuthorizeSetting",
						name: "uni.getAppAuthorizeSetting"
					},
					{
						id: "api.device.openAppAuthorizeSetting",
						name: "uni.openAppAuthorizeSetting"
					},
					{
						id: "api.device.getSystemSetting",
						name: "uni.getSystemSetting"
					},
					{
						id: "api.device.installApk",
						name: "uni.installApk"
					},
					{
						id: "api.device.getBatteryInfo",
						name: "getBatteryInfo"
					},
					{
						id: "api.device.makePhoneCall",
						name: "uni.makePhoneCall"
					},
					null,
					{
						id: "api.device.clipboard",
						name: "clipboard"
					},
					{
						id: "api.device.compass",
						name: "compass"
					},
					null,
					null,
					null,
					{
						id: "api.device.captureScreen",
						name: "captureScreen"
					},
					{
						id: "api.device.createRequestPermissionListener",
						name: "uni.createRequestPermissionListener"
					},
					{
						id: "api.device.scanCode",
						name: "uni.scanCode"
					},
					{
						id: "api.device.isSimulator",
						name: "uni.isSimulator"
					},
					{
						id: "api.device.getAccessibilityInfo",
						name: "uni.getAccessibilityInfo"
					},
					{
						id: "api.device.keyboard",
						name: "keyboard"
					}
				]
			},
			{
				id: "api.media",
				name: "媒体",
				children: [
					{
						id: "api.media.chooseImage",
						name: "uni.chooseImage"
					},
					{
						id: "api.media.previewImage",
						name: "previewImage"
					},
					{
						id: "api.media.saveImageToPhotosAlbum",
						name: "uni.saveImageToPhotosAlbum"
					},
					{
						id: "api.media.getImageInfo",
						name: "uni.getImageInfo"
					},
					{
						id: "api.media.compressImage",
						name: "uni.compressImage"
					},
					{
						id: "api.media.chooseVideo",
						name: "uni.chooseVideo"
					},
					{
						id: "api.media.saveVideoToPhotosAlbum",
						name: "uni.saveVideoToPhotosAlbum"
					},
					{
						id: "api.media.getVideoInfo",
						name: "uni.getVideoInfo"
					},
					{
						id: "api.media.compressVideo",
						name: "uni.compressVideo"
					},
					{
						id: "api.media.chooseMedia",
						name: "uni.chooseMedia"
					},
					{
						id: "api.media.chooseFile",
						name: "uni.chooseFile"
					},
					{
						id: "api.media.createInnerAudioContext",
						name: "uni.createInnerAudioContext"
					},
					{
						id: "api.media.setInnerAudioOption",
						name: "uni.setInnerAudioOption"
					},
					{
						id: "api.media.getBackgroundAudioManager",
						name: "uni.getBackgroundAudioManager"
					},
					{
						id: "api.media.getRecorderManager",
						name: "uni.getRecorderManager"
					}
				]
			},
			{
				id: "api.file",
				name: "文件",
				children: [
					{
						id: "api.file.getFileSystemManager",
						name: "uni.getFileSystemManager"
					},
					{
						id: "api.file.openDocument",
						name: "uni.openDocument"
					}
				]
			},
			{
				id: "api.storage",
				name: "数据存储"
			},
			null,
			{
				id: "api.location",
				name: "位置",
				children: [
					{
						id: "api.location.getLocation",
						name: "uni.getLocation"
					},
					{
						id: "api.location.openLocation",
						name: "uni.openLocation"
					},
					{
						id: "api.location.chooseLocation",
						name: "uni.chooseLocation"
					},
					{
						id: "api.location.locationChange",
						name: "locationChange"
					}
				]
			},
			{
				id: "api.loginVerify",
				name: "登录与认证",
				children: [
					{
						id: "api.loginVerify.oauth",
						name: "oauth"
					},
					{
						id: "api.loginVerify.getUniVerifyManager",
						name: "uni.getUniVerifyManager"
					},
					{
						id: "api.loginVerify.facialRecognitionMetaInfo",
						name: "facialRecognitionMetaInfo"
					},
					{
						id: "api.loginVerify.bioAuth",
						name: "bioAuth"
					}
				]
			},
			{
				id: "api.ad",
				name: "广告",
				children: [
					{
						id: "api.ad.createRewardedVideoAd",
						name: "uni.createRewardedVideoAd"
					},
					{
						id: "api.ad.createInterstitialAd",
						name: "uni.createInterstitialAd"
					}
				]
			},
			{
				id: "api.payment",
				name: "支付",
				children: [
					{
						id: "api.payment.requestPayment",
						name: "uni.requestPayment"
					},
					{
						id: "api.payment.virtualPayment",
						name: "virtualPayment"
					},
					{
						id: "api.payment.requestMerchantTransfer",
						name: "uni.requestMerchantTransfer"
					}
				]
			},
			{
				id: "api.share",
				name: "分享",
				children: [
					{
						id: "api.share.uniShare",
						name: "uni.share"
					},
					{
						id: "api.share.shareWithSystem",
						name: "uni.shareWithSystem"
					}
				]
			},
			{
				id: "api.push",
				name: "推送",
				children: [
					{
						id: "api.push.uni-push",
						name: "push"
					}
				]
			},
			{
				id: "api.stat",
				name: "统计",
				children: [
					{
						id: "api.stat.report",
						name: "uni.report"
					}
				]
			},
			null,
			{
				id: "api.worker",
				name: "Worker",
				children: [
					{
						id: "api.worker.createWorker",
						name: "uni.createWorker"
					}
				]
			},
			{
				id: "api.unicloud",
				name: "uniCloud客户端API",
				children: [
					{
						id: "api.unicloud.cloudFunction",
						name: "cloudFunction"
					},
					{
						id: "api.unicloud.cloudObject",
						name: "cloudObject"
					},
					{
						id: "api.unicloud.cloudStorage",
						name: "cloudStorage"
					},
					{
						id: "api.unicloud.database",
						name: "database"
					},
					{
						id: "api.unicloud.sseChannel",
						name: "sseChannel"
					}
				]
			},
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			{
				id: "api.elementInstance",
				name: "UniElement 实例"
			}
		]
	},
	{
		id: "CSS",
		name: "组件",
		children: [
			{
				id: "css.properties.width",
				name: "width",
				children: [
					{
						id: "css.properties.width.width",
						name: "width"
					},
					{
						id: "css.properties.width.min-width",
						name: "min-width"
					},
					{
						id: "css.properties.width.max-width",
						name: "max-width"
					}
				]
			},
			{
				id: "css.properties.height",
				name: "height",
				children: [
					{
						id: "css.properties.height.height",
						name: "height"
					},
					{
						id: "css.properties.height.min-height",
						name: "min-height"
					},
					{
						id: "css.properties.height.max-height",
						name: "max-height"
					}
				]
			},
			{
				id: "css.properties.margin",
				name: "margin",
				children: [
					{
						id: "css.properties.margin.margin",
						name: "margin"
					},
					{
						id: "css.properties.margin.margin-top",
						name: "margin-top"
					},
					{
						id: "css.properties.margin.margin-bottom",
						name: "margin-bottom"
					},
					{
						id: "css.properties.margin.margin-left",
						name: "margin-left"
					},
					{
						id: "css.properties.margin.margin-right",
						name: "margin-right"
					}
				]
			},
			{
				id: "css.properties.padding",
				name: "padding",
				children: [
					{
						id: "css.properties.padding.padding",
						name: "padding"
					},
					{
						id: "css.properties.padding.padding-top",
						name: "padding-top"
					},
					{
						id: "css.properties.padding.padding-bottom",
						name: "padding-bottom"
					},
					{
						id: "css.properties.padding.padding-left",
						name: "padding-left"
					},
					{
						id: "css.properties.padding.padding-right",
						name: "padding-right"
					}
				]
			},
			null,
			{
				id: "css.properties.display",
				name: "display"
			},
			{
				id: "css.properties.visibility",
				name: "visibility"
			},
			{
				id: "css.properties.flex",
				name: "flex",
				children: [
					{
						id: "css.properties.flex.flex",
						name: "flex"
					},
					{
						id: "css.properties.flex.flex-direction",
						name: "flex-direction"
					},
					{
						id: "css.properties.flex.justify-content",
						name: "justify-content"
					},
					null,
					{
						id: "css.properties.flex.align-items",
						name: "align-items"
					},
					null,
					{
						id: "css.properties.flex.align-content",
						name: "align-content"
					},
					{
						id: "css.properties.flex.flex-grow",
						name: "flex-grow"
					},
					{
						id: "css.properties.flex.flex-shrink",
						name: "flex-shrink"
					},
					{
						id: "css.properties.flex.flex-basis",
						name: "flex-basis"
					},
					{
						id: "css.properties.flex.flex-flow",
						name: "flex-flow"
					}
				]
			},
			{
				id: "css.properties.background",
				name: "background",
				children: [
					null,
					{
						id: "css.properties.background.background-color",
						name: "background-color"
					},
					{
						id: "css.properties.background.background-image",
						name: "background-image"
					}
				]
			},
			{
				id: "css.properties.position",
				name: "position",
				children: [
					{
						id: "css.properties.position.position",
						name: "position"
					}
				]
			},
			{
				id: "css.properties.z-index",
				name: "z-index"
			},
			{
				id: "css.properties.overflow",
				name: "overflow"
			},
			null,
			{
				id: "css.properties.border",
				name: "border",
				children: [
					{
						id: "css.properties.border.border",
						name: "border"
					},
					{
						id: "css.properties.border.border-style",
						name: "border-style"
					},
					null,
					null,
					null,
					null,
					{
						id: "css.properties.border.border-width",
						name: "border-width"
					},
					null,
					null,
					null,
					null,
					{
						id: "css.properties.border.border-color",
						name: "border-color"
					},
					null,
					null,
					null,
					null,
					{
						id: "css.properties.border.border-radius",
						name: "border-radius"
					},
					null,
					null,
					null,
					null,
					{
						id: "css.properties.border.border-top",
						name: "border-top"
					},
					{
						id: "css.properties.border.border-bottom",
						name: "border-bottom"
					},
					{
						id: "css.properties.border.border-left",
						name: "border-left"
					},
					{
						id: "css.properties.border.border-right",
						name: "border-right"
					}
				]
			},
			{
				id: "css.properties.text",
				name: "text",
				children: [
					{
						id: "css.properties.text.color",
						name: "color"
					},
					{
						id: "css.properties.text.font-size",
						name: "font-size"
					},
					{
						id: "css.properties.text.font-family",
						name: "font-family"
					},
					{
						id: "css.properties.text.font-style",
						name: "font-style"
					},
					{
						id: "css.properties.text.font-weight",
						name: "font-weight"
					},
					null,
					{
						id: "css.properties.text.text-decoration-line",
						name: "text-decoration-line"
					},
					null,
					null,
					null,
					{
						id: "css.properties.text.text-align",
						name: "text-align"
					},
					{
						id: "css.properties.text.text-overflow",
						name: "text-overflow"
					},
					{
						id: "css.properties.text.text-shadow",
						name: "text-shadow"
					},
					{
						id: "css.properties.text.line-height",
						name: "line-height"
					},
					null,
					{
						id: "css.properties.text.letter-spacing",
						name: "letter-spacing"
					},
					{
						id: "css.properties.text.white-space",
						name: "white-space"
					}
				]
			},
			{
				id: "css.properties.box-shadow",
				name: "box-shadow"
			},
			{
				id: "css.properties.transition",
				name: "transition"
			},
			{
				id: "css.properties.transform",
				name: "transform"
			},
			{
				id: "css.properties.pointer-events",
				name: "pointer-events"
			},
			null,
			{
				id: "function",
				name: "函数",
				children: [
					{
						id: "variable",
						name: "variable"
					},
					{
						id: "function",
						name: "function"
					}
				]
			}
		]
	}
];
export default {
	leftWindow: leftWindow,
	topWindow: topWindow,
	pages: pages,
	subPackages: subPackages,
	globalStyle: globalStyle,
	tabBar: tabBar,
	condition: condition,
	groups: groups
};
