
	const __sfc__ = defineComponent({
		data() {
			return {
				title: 'backgroundAudio',
				bgAudioMannager: null as BackgroundAudioManager | null,
				dataUrl: 'https://web-ext-storage.dcloud.net.cn/uni-app/ForElise.mp3',
				playing: false,
				playTime: 0,
				formatedPlayTime: '00:00:00',
				count: 100,
				isPlayEnd: false,
				duration: 100,
				currentTime: 0,
				_isChanging: false,
				buffered: 0,
				isLoop: false
			}
		},
		computed: {
			position() {
				return this.isPlayEnd ? 0 : this.currentTime;
			},
		},
		onLoad: function () {
			let bgAudioMannager = uni.getBackgroundAudioManager();
			bgAudioMannager.title = '致爱丽丝' + this.count;
			bgAudioMannager.epname = '专辑名：致爱丽丝' + this.count
			bgAudioMannager.singer = '歌手：暂无' + this.count;
			bgAudioMannager.coverImgUrl = 'https://web-assets.dcloud.net.cn/unidoc/zh/Alice.jpeg';
			bgAudioMannager.src = this.dataUrl;
			this.currentTime = bgAudioMannager.currentTime
			this.duration = bgAudioMannager.duration
			bgAudioMannager.onCanplay(() => {
				console.log("音频进入可以播放状态事件", " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:67");
				this.buffered = bgAudioMannager.buffered;
				this.duration = bgAudioMannager.duration
			})
			bgAudioMannager.onPlay(() => {
				console.log("开始播放", " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:72");
				this.playing = true;
			})
			bgAudioMannager.onPause(() => {
				console.log("暂停播放", " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:76");
				this.playing = false;
			})
			bgAudioMannager.onStop(() => {
				console.log("停止播放", " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:80");
				this.playing = false;
			})
			bgAudioMannager.onEnded(() => {
				if (this.isLoop == false) {
					console.log("播放结束", " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:85");
					this.playing = false;
					this.currentTime = 0;
					this.isPlayEnd = true;
					(this.$refs["slider"] as UniSliderElement).value = 0
				} else {
					console.log("播放结束, 开始循环播放", " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:91");
					this.bgAudioMannager!.src = this.dataUrl;
					this.bgAudioMannager?.play()
				}
			})
			bgAudioMannager.onNext(() => {
				this.count++
				console.log("下一曲", this.count, " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:98");
				this.bgAudioMannager?.stop()
				bgAudioMannager.title = '致爱丽丝' + this.count;
				bgAudioMannager.singer = '歌手：暂无' + this.count;
				this.dataUrl = 'https://web-ext-storage.dcloud.net.cn/uni-app/ForElise.mp3'
				bgAudioMannager.coverImgUrl = 'https://qiniu-web-assets.dcloud.net.cn/unidoc/zh/music-a.png';
				this.bgAudioMannager!.src = this.dataUrl;
				this.bgAudioMannager?.play()
			})
			bgAudioMannager.onPrev(() => {
				this.count--
				console.log("上一曲", this.count, " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:109");
				this.bgAudioMannager?.stop()
				bgAudioMannager.title = '致爱丽丝' + this.count;
				bgAudioMannager.singer = '歌手：暂无' + this.count;
				this.dataUrl = 'https://web-ext-storage.dcloud.net.cn/uni-app/ForElise.mp3'
				bgAudioMannager.coverImgUrl = 'https://web-assets.dcloud.net.cn/unidoc/zh/Alice.jpeg';
				this.bgAudioMannager!.src = this.dataUrl;
				this.bgAudioMannager?.play()
			})
			bgAudioMannager.onSeeking(() => {
				console.log('音频进行 seek 操作事件', " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:119");
			})
			bgAudioMannager.onSeeked(() => {
				console.log('音频完成 seek 操作事件', " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:122");
			})
			bgAudioMannager.onWaiting(() => {
				console.log('音频加载中事件', " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:125");
			})
			bgAudioMannager.onTimeUpdate(() => {
				console.log('onTimeUpdate', bgAudioMannager.currentTime, " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:128")
				if (this._isChanging) { return; }
				this.currentTime = this.bgAudioMannager!.currentTime;
				this.buffered = this.bgAudioMannager!.buffered;
				console.log('onTimeUpdateCb', this.currentTime, " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:132")








				if (this.currentTime > this.buffered) {
					console.log('缓冲不足', " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:142");
				}
			})
			bgAudioMannager.onError((err) => {
				console.log('播放出错err', err, " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:146");
			})
			this.bgAudioMannager = bgAudioMannager;
			this.playing = !bgAudioMannager.paused
			console.log('currentTime=', this.bgAudioMannager!.currentTime, this.bgAudioMannager!.currentTime == 0, " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:150")
		},
		methods: {
			play: function () {
				console.log('play', " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:154")
				this.isPlayEnd = false;
				this.bgAudioMannager!.play()
			},
			pause: function () {
				this.bgAudioMannager?.pause();
			},
			stop: function () {
				this.bgAudioMannager?.stop();
				this.playing = false
			},
			onchanging() {
				this._isChanging = true;
			},
			onchange(e : UniSliderChangeEvent) {
				let pos = e.detail.value;
				console.log('pos', pos, " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:170");
				this.bgAudioMannager!.seek(pos);
				this._isChanging = false;
			},
			setLoop() {
				this.isLoop = !this.isLoop;
				console.log('当前是否设置循环播放，loop= ', this.isLoop, " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:176");
			}
		}
	})

export default __sfc__
function GenPagesAPIGetBackgroundAudioManagerGetBackgroundAudioManagerRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)
const _component_boolean_data = resolveEasyComponent("boolean-data",_easycom_boolean_data)
const _component_slider = resolveComponent("slider")

  return createElementVNode("view", null, [
    createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
      createElementVNode("text", null, " 注意：1.离开当前页面后背景音乐将保持播放；\n 2. 硬退出app、调用stop api、播放结束都会清理后台控制中心和锁屏信息显示 "),
      createVNode(_component_boolean_data, utsMapOf({
        defaultValue: false,
        title: "是否循环播放",
        onChange: _ctx.setLoop
      }), null, 8 /* PROPS */, ["onChange"]),
      createElementVNode("view", utsMapOf({ class: "uni-common-mt" }), [
        createVNode(_component_slider, utsMapOf({
          ref: "slider",
          value: _ctx.position,
          min: 0,
          max: _ctx.duration,
          onChanging: _ctx.onchanging,
          onChange: _ctx.onchange
        }), null, 8 /* PROPS */, ["value", "max", "onChanging", "onChange"])
      ]),
      createElementVNode("view", utsMapOf({ class: "page-body-buttons" }), [
        isTrue(_ctx.playing)
          ? createElementVNode(Fragment, utsMapOf({ key: 0 }), [
              createElementVNode("view", utsMapOf({
                class: "page-body-button",
                onClick: _ctx.stop
              }), [
                createElementVNode("image", utsMapOf({
                  class: "image",
                  src: "/static/stop.png"
                }))
              ], 8 /* PROPS */, ["onClick"]),
              createElementVNode("view", utsMapOf({
                class: "page-body-button",
                onClick: _ctx.pause,
                style: normalizeStyle(utsMapOf({"margin-top":"20px"}))
              }), [
                createElementVNode("image", utsMapOf({
                  class: "image",
                  src: "/static/pause.png"
                }))
              ], 12 /* STYLE, PROPS */, ["onClick"])
            ], 64 /* STABLE_FRAGMENT */)
          : createCommentVNode("v-if", true),
        isTrue(!_ctx.playing)
          ? createElementVNode("view", utsMapOf({
              key: 1,
              class: "page-body-button",
              onClick: _ctx.play
            }), [
              createElementVNode("image", utsMapOf({
                class: "image",
                src: "/static/play.png"
              }))
            ], 8 /* PROPS */, ["onClick"])
          : createCommentVNode("v-if", true),
        createElementVNode("view", utsMapOf({ class: "page-body-button" }))
      ])
    ])
  ])
}
const GenPagesAPIGetBackgroundAudioManagerGetBackgroundAudioManagerStyles = [utsMapOf([["image", padStyleMapOf(utsMapOf([["width", 75], ["height", 75]]))], ["page-body-text", padStyleMapOf(utsMapOf([["paddingTop", 0], ["paddingRight", 15], ["paddingBottom", 0], ["paddingLeft", 15]]))], ["page-body-wrapper", padStyleMapOf(utsMapOf([["marginTop", 0]]))], ["page-body-info", padStyleMapOf(utsMapOf([["paddingBottom", 25]]))], ["time-big", padStyleMapOf(utsMapOf([["fontSize", 30], ["marginTop", 10], ["marginRight", 10], ["marginBottom", 10], ["marginLeft", 10]]))], ["slider", padStyleMapOf(utsMapOf([["width", 315]]))], ["play-time", padStyleMapOf(utsMapOf([["width", "100%"], ["paddingTop", 10], ["paddingRight", 0], ["paddingBottom", 10], ["paddingLeft", 0], ["display", "flex"], ["justifyContent", "space-between"], ["boxSizing", "border-box"]]))], ["page-body-buttons", padStyleMapOf(utsMapOf([["display", "flex"], ["justifyContent", "center"], ["marginTop", 50], ["flexDirection", "column"]]))], ["page-body-button", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["justifyContent", "center"]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
import _easycom_boolean_data from '@/components/boolean-data/boolean-data.vue'
