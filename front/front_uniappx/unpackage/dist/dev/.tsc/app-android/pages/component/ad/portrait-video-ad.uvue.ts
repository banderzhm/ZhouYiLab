
	const __sfc__ = defineComponent({
		data() {
			return {
				title: '广告演示',
				src: "https://qiniu-web-assets.dcloud.net.cn/video/sample/2minute-demo.mp4",
				showAd: false,
				adVisible: false,
				countdown: 10,
				adTimer: null
			}
		},
		onLoad() {
			// 页面加载逻辑
		},
		methods: {
			setfs(){
				const videoContext = uni.createVideoContext('myVideo1')
				videoContext!.requestFullScreen({direction:0});
			},
			// 视频播放时触发广告
			onVideoPlay() {
				if (!this.showAd) {
					const videoContext = uni.createVideoContext('myVideo1')
					videoContext!.stop();
					this.showAd = true;
					videoContext!.requestFullScreen({direction:0});
					this.adVisible = true;
				}
			},
			// 关闭广告
			closeAd() {
				console.log("执行了关闭", " at pages/component/ad/portrait-video-ad.uvue:46");
				// this.showAd = false
				this.adVisible = false;
				// 确保视频恢复播放
				const videoContext = uni.createVideoContext('myVideo1')
				videoContext!.play()
			},
			loadFun(){
				console.log("加载成功", " at pages/component/ad/portrait-video-ad.uvue:54");
			},
			errorFun(e : UniAdErrorEvent){
				console.log(e.detail, " at pages/component/ad/portrait-video-ad.uvue:57");
			}
		}
	})

export default __sfc__
function GenPagesComponentAdPortraitVideoAdRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({ class: "container" }), [
    createElementVNode("video", utsMapOf({
      id: "myVideo1",
      src: _ctx.src,
      controls: true,
      autoplay: "false",
      class: "video",
      onPlay: _ctx.onVideoPlay
    }), [
      isTrue(_ctx.adVisible)
        ? createElementVNode("view", utsMapOf({
            key: 0,
            class: "ad-container"
          }), [
            createElementVNode("ad", utsMapOf({
              adpid: "1597617406",
              style: normalizeStyle(utsMapOf({"width":"100%","height":"100%"})),
              onLoad: _ctx.loadFun,
              onError: _ctx.errorFun,
              onClose: _ctx.closeAd
            }), null, 44 /* STYLE, PROPS, NEED_HYDRATION */, ["onLoad", "onError", "onClose"])
          ])
        : createCommentVNode("v-if", true)
    ], 40 /* PROPS, NEED_HYDRATION */, ["src", "onPlay"])
  ])
}
const GenPagesComponentAdPortraitVideoAdStyles = [utsMapOf([["container", padStyleMapOf(utsMapOf([["position", "relative"], ["width", "100%"], ["height", "100%"], ["display", "flex"], ["flexDirection", "column"], ["alignItems", "center"]]))], ["video", padStyleMapOf(utsMapOf([["width", "100%"], ["height", "420rpx"]]))], ["ad-container", padStyleMapOf(utsMapOf([["width", "100%"], ["height", "100%"], ["backgroundColor", "rgba(0,0,0,0.7)"], ["display", "flex"], ["alignItems", "center"], ["justifyContent", "center"]]))]])]
