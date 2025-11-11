
	const audioUrl = 'https://web-ext-storage.dcloud.net.cn/uni-app/ForElise.mp3'
	const __sfc__ = defineComponent({
		data() {
			return {
				title: "innerAudioContext",
				currentTime: 0,
				duration: 100,
				startTime: 0,
				buffered: 0,
				volume: 0.5,
				isCanplay: false,
				isPlaying: false,
				isPaused: true,
				isPlayEnd: false,
				_isChanging: false,
				_audioContext: null as InnerAudioContext | null,
				// 自动化测试
				onSeekingTest: false,
				onSeekedTest: false,
				onWaitingTest: false,
				playbackRateChecked: true,
				onTimeUpdateCb: (res : any) => { },
				onWaitingCb: (res : any) => { }
			}
		},
		computed: {
			position() {
				return this.isPlayEnd ? 0 : this.currentTime;
			},
		},
		onReady() {
			this._audioContext = uni.createInnerAudioContext();
			this._audioContext!.src = audioUrl;
			this.volume = this._audioContext!.volume;
			this.onCanplay()
			this._audioContext!.onPlay(() => {
				this.isPaused = false;
				this.isPlaying = true;
				console.log('开始播放', this.isPaused, " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:112");
			});

			this.onTimeUpdateCb = (res : any) => {
				if (this._isChanging) { return; }
				this.currentTime = this._audioContext!.currentTime;
				this.buffered = this._audioContext!.buffered;
				console.log('onTimeUpdateCb', this.currentTime, " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:119")








				if (this.currentTime > this.buffered) {
					console.log('缓冲不足', " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:129");
				}
			};

			this.onWaitingCb = (res : any) => {
				console.log('音频加载中事件', " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:134");
				this.onWaitingTest = true
			}

			this.onTimeUpdate()
			// this.onWaiting()
			this.onError()
			this.onEnded()
		},
		onUnload() {
			if (this._audioContext != null) {
				if (this.isPlaying) {
					this.stop();
				}
				this._audioContext!.destroy()
			}
		},
		methods: {
			onCanplay() {
				this._audioContext!.onCanplay(() => {
					console.log('音频进入可以播放状态事件', " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:154");
					this.isCanplay = true;




					// 当音频可以播放时，获取缓冲信息
					this.buffered = this._audioContext!.buffered;
					this.duration = this._audioContext!.duration
				});
			},
			onchanging() {
				this._isChanging = true;
			},
			onchange(e : UniSliderChangeEvent) {
				let pos = e.detail.value;
				console.log('pos', pos, " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:170");
				this.onSeeking()
				this.onSeeked()
				this._audioContext!.seek(pos);
				this._isChanging = false;
			},
			onchangeValue(pos : number) {
				this.onSeeking()
				this.onSeeked()
				this._audioContext!.seek(pos);
				this._isChanging = false;
			},
			startTimeInput(e : UniInputEvent) {
				let startTimeValue = parseInt(e.detail.value)
				this._audioContext!.startTime = startTimeValue;
				this.onchangeValue(startTimeValue)
			},
			setAutoplay() {
				this._audioContext!.autoplay = !this._audioContext!.autoplay;
				console.log(this._audioContext!.autoplay, 'autoplay', " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:189");
			},
			setLoop() {
				this._audioContext!.loop = !this._audioContext!.loop;
				console.log(this._audioContext!.loop, 'loop', " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:193");
			},
			play() {
				if (!this.isCanplay) {
					uni.showToast({
						title: '音频未进入可以播放状态，请稍后再试',
						icon: 'error'
					});
					return;
				}
				this.isPlaying = true;
				this._audioContext!.play();
				this.isPlayEnd = false;
				if (this._audioContext!.startTime > 0) {
					this.onchangeValue(this._audioContext!.startTime)
				}
			},
			onSeeking() {
				this._audioContext!.onSeeking(() => {
					console.log('音频进行 seek 操作事件', " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:212");
					this.onSeekingTest = true
				});
			},
			onSeeked() {
				this._audioContext!.onSeeked(() => {
					console.log('音频完成 seek 操作事件', " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:218");
					this.onSeekedTest = true
				});
			},
			onWaiting() {
				this._audioContext!.onWaiting(this.onWaitingCb);
			},
			offWaiting() {
				this._audioContext!.offWaiting(this.onWaitingCb);
			},
			onTimeUpdate() {
				this._audioContext!.onTimeUpdate(this.onTimeUpdateCb);
			},
			offTimeUpdate() {
				this._audioContext!.offTimeUpdate(this.onTimeUpdateCb);
			},
			increaseVolume() {
				this.volume = Math.min(this.volume + 0.1, 1);
				this.volume = parseFloat(this.volume.toFixed(1));
				this._audioContext!.volume = this.volume
				console.log('增加音量', this.volume, " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:238");
			},
			decreaseVolume() {
				this.volume = Math.max(this.volume - 0.1, 0);
				this.volume = parseFloat(this.volume.toFixed(1));
				console.log('减少音量', this.volume, " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:243");
				this._audioContext!.volume = this.volume
			},
			onEnded() {
				this._audioContext!.onEnded(() => {
					console.log('播放结束', " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:248");
					this.currentTime = 0;
					this.startTime = 0
					this.isPlaying = false;
					this.isPaused = true;
					this.isPlayEnd = true;
					(this.$refs["slider"] as UniSliderElement).value = 0
				});
			},
			onError() {
				this._audioContext!.onError((err) => {
					console.log('err', err, " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:259");
					this.isPlaying = false;
					this.isPaused = true;
				});
			},
			pause() {
				this._audioContext!.onPause(() => {
					console.log('音频暂停事件', " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:266");
					this.isPaused = true;
				});
				this._audioContext!.pause();
				this.isPlaying = false;
			},
			stop() {
				console.log('stop', " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:273");
				this._audioContext!.onStop(() => {
					// 第一次点停止时，不触发
					this.isPaused = true;
					console.log('音频停止事件', " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:277");
				});
				this._audioContext!.stop();
				this.isPlaying = false;
				console.log('stop', this.isPaused, " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:281");
			},
			destory() {
				if (this._audioContext != null) {
					this.isPlaying = false;
					this._audioContext!.destroy()
				}
			},
			playbackRateChange(e : UniRadioGroupChangeEvent) {
				// if (this._audioContext != null && this.isPlaying) {
					console.log(parseFloat(e.detail.value), " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:291")
					this._audioContext!.playbackRate = parseFloat(e.detail.value)
				// }
			},
      //just for test
      setSrc(src:string){
        if(	this._audioContext!=null){
          this._audioContext!.src = src
        }
      }
		}
	})

export default __sfc__
function GenPagesAPICreateInnerAudioContextCreateInnerAudioContextRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)
const _component_slider = resolveComponent("slider")
const _component_boolean_data = resolveEasyComponent("boolean-data",_easycom_boolean_data)
const _component_radio = resolveComponent("radio")
const _component_radio_group = resolveComponent("radio-group")
const _component_navigator = resolveComponent("navigator")

  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
      createVNode(_component_page_head, utsMapOf({ title: "audio" })),
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
      createElementVNode("view", utsMapOf({ class: "uni-title" }), [
        createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "属性示例")
      ]),
      createElementVNode("text", utsMapOf({ class: "uni-text-box uni-common-mt" }), "当前音频播放位置（保留小数点后 3 位）：" + toDisplayString(_ctx.currentTime) + " s", 1 /* TEXT */),
      createElementVNode("text", utsMapOf({ class: "uni-text-box" }), "音频的长度（单位:s）：" + toDisplayString(_ctx.duration) + " s", 1 /* TEXT */),
      createElementVNode("text", utsMapOf({ class: "uni-text-box" }), "当前是否停止状态：" + toDisplayString(_ctx.isPaused), 1 /* TEXT */),
      createElementVNode("text", utsMapOf({ class: "uni-text-box" }), "音频缓冲的时间点：" + toDisplayString(_ctx.buffered) + " s", 1 /* TEXT */),
      createElementVNode("text", utsMapOf({ class: "uni-text-box" }), "当前音量：" + toDisplayString(_ctx.volume), 1 /* TEXT */),
      createElementVNode("button", utsMapOf({
        plain: "",
        disabled: _ctx.volume == 1,
        onClick: _ctx.increaseVolume
      }), "增加音量", 8 /* PROPS */, ["disabled", "onClick"]),
      createElementVNode("button", utsMapOf({
        plain: "",
        disabled: _ctx.volume == 0,
        onClick: _ctx.decreaseVolume
      }), "减少音量", 8 /* PROPS */, ["disabled", "onClick"]),
      createElementVNode("text", utsMapOf({ class: "uni-subtitle-text uni-title" }), "开始播放的位置（单位：s）"),
      createElementVNode("input", utsMapOf({
        value: _ctx.startTime,
        type: "number",
        placeholder: "开始播放的位置（单位：s）",
        class: "uni-input",
        onInput: _ctx.startTimeInput
      }), null, 40 /* PROPS, NEED_HYDRATION */, ["value", "onInput"]),
      createVNode(_component_boolean_data, utsMapOf({
        defaultValue: false,
        title: "是否自动开始播放",
        onChange: _ctx.setAutoplay
      }), null, 8 /* PROPS */, ["onChange"]),
      createVNode(_component_boolean_data, utsMapOf({
        defaultValue: false,
        title: "是否循环播放",
        onChange: _ctx.setLoop
      }), null, 8 /* PROPS */, ["onChange"]),
      createElementVNode("text", utsMapOf({
        class: "uni-subtitle-text uni-title",
        style: normalizeStyle(utsMapOf({"padding-left":"10px","padding-top":"10px","padding-right":"10px"}))
      }), "播放倍率(Web/HarmonyOS 不支持)", 4 /* STYLE */),
      createVNode(_component_radio_group, utsMapOf({
        class: "uni-flex uni-row radio-group",
        onChange: _ctx.playbackRateChange,
        style: normalizeStyle(utsMapOf({"flex-wrap":"wrap","padding":"10px"}))
      }), utsMapOf({
        default: withSlotCtx((): any[] => [
          createVNode(_component_radio, utsMapOf({
            value: "0.5",
            style: normalizeStyle(utsMapOf({"margin-right":"3px"}))
          }), utsMapOf({
            default: withSlotCtx((): any[] => ["0.5 "]),
            _: 1 /* STABLE */
          }), 8 /* PROPS */, ["style"]),
          createVNode(_component_radio, utsMapOf({
            value: "0.8",
            style: normalizeStyle(utsMapOf({"margin-right":"3px"}))
          }), utsMapOf({
            default: withSlotCtx((): any[] => ["0.8"]),
            _: 1 /* STABLE */
          }), 8 /* PROPS */, ["style"]),
          createVNode(_component_radio, utsMapOf({
            value: "1.0",
            style: normalizeStyle(utsMapOf({"margin-right":"3px"})),
            checked: _ctx.playbackRateChecked
          }), utsMapOf({
            default: withSlotCtx((): any[] => ["1.0"]),
            _: 1 /* STABLE */
          }), 8 /* PROPS */, ["style", "checked"]),
          createVNode(_component_radio, utsMapOf({
            value: "1.25",
            style: normalizeStyle(utsMapOf({"margin-right":"3px"}))
          }), utsMapOf({
            default: withSlotCtx((): any[] => ["1.25"]),
            _: 1 /* STABLE */
          }), 8 /* PROPS */, ["style"]),
          createVNode(_component_radio, utsMapOf({
            value: "1.5",
            style: normalizeStyle(utsMapOf({"margin-right":"3px"}))
          }), utsMapOf({
            default: withSlotCtx((): any[] => ["1.5"]),
            _: 1 /* STABLE */
          }), 8 /* PROPS */, ["style"]),
          createVNode(_component_radio, utsMapOf({ value: "2.0" }), utsMapOf({
            default: withSlotCtx((): any[] => ["2.0"]),
            _: 1 /* STABLE */
          }))
        ]),
        _: 1 /* STABLE */
      }), 8 /* PROPS */, ["onChange", "style"]),
      createElementVNode("view", utsMapOf({ class: "uni-title" }), [
        createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "方法示例")
      ]),
      createElementVNode("button", utsMapOf({
        disabled: _ctx.isPlaying,
        onClick: _ctx.play,
        class: "uni-btn"
      }), "播放", 8 /* PROPS */, ["disabled", "onClick"]),
      createElementVNode("button", utsMapOf({
        disabled: !_ctx.isPlaying,
        onClick: _ctx.pause,
        class: "uni-btn"
      }), "暂停", 8 /* PROPS */, ["disabled", "onClick"]),
      createElementVNode("button", utsMapOf({
        disabled: !_ctx.isPlaying,
        onClick: _ctx.stop,
        class: "uni-btn"
      }), "停止", 8 /* PROPS */, ["disabled", "onClick"]),
      createElementVNode("button", utsMapOf({
        onClick: () => {_ctx.onchangeValue(20)},
        class: "uni-btn"
      }), "跳转到指定位置20", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        onClick: _ctx.onTimeUpdate,
        class: "uni-btn"
      }), "onTimeUpdate", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        onClick: _ctx.offTimeUpdate,
        class: "uni-btn"
      }), "offTimeUpdate", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        onClick: _ctx.onWaiting,
        class: "uni-btn"
      }), "onWaiting", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        onClick: _ctx.offWaiting,
        class: "uni-btn"
      }), "offWaiting", 8 /* PROPS */, ["onClick"]),
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"color":"red","font-size":"15px","margin-top":"10px"}))
      }), "tip:销毁后请重新进入此界面再播放", 4 /* STYLE */),
      createElementVNode("button", utsMapOf({
        onClick: _ctx.destory,
        class: "uni-btn"
      }), "销毁", 8 /* PROPS */, ["onClick"]),
      createElementVNode("view", utsMapOf({ class: "uni-title" }), [
        createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "格式/路径示例")
      ]),
      createVNode(_component_navigator, utsMapOf({
        url: "/pages/API/create-inner-audio-context/inner-audio-format",
        class: "uni-btn"
      }), utsMapOf({
        default: withSlotCtx((): any[] => [
          createElementVNode("button", utsMapOf({ onClick: _ctx.pause }), "音频格式示例", 8 /* PROPS */, ["onClick"])
        ]),
        _: 1 /* STABLE */
      })),
      createVNode(_component_navigator, utsMapOf({
        url: "/pages/API/create-inner-audio-context/inner-audio-path",
        class: "uni-btn"
      }), utsMapOf({
        default: withSlotCtx((): any[] => [
          createElementVNode("button", utsMapOf({ onClick: _ctx.pause }), "音频路径示例", 8 /* PROPS */, ["onClick"])
        ]),
        _: 1 /* STABLE */
      })),
      createVNode(_component_navigator, utsMapOf({
        url: "/pages/API/create-inner-audio-context/inner-audio-mult",
        class: "uni-btn"
      }), utsMapOf({
        default: withSlotCtx((): any[] => [
          createElementVNode("button", utsMapOf({ onClick: _ctx.pause }), "多音频同时播放", 8 /* PROPS */, ["onClick"])
        ]),
        _: 1 /* STABLE */
      }))
    ])
  ], 4 /* STYLE */)
}
const GenPagesAPICreateInnerAudioContextCreateInnerAudioContextStyles = [utsMapOf([["play-time-area", padStyleMapOf(utsMapOf([["display", "flex"], ["flexDirection", "row"], ["marginTop", 20]]))], ["duration", padStyleMapOf(utsMapOf([["marginLeft", "auto"]]))], ["play-button-area", padStyleMapOf(utsMapOf([["display", "flex"], ["flexDirection", "row"], ["justifyContent", "center"], ["marginTop", 50], ["marginRight", 0], ["marginBottom", 50], ["marginLeft", 0]]))], ["icon-play", padStyleMapOf(utsMapOf([["width", 60], ["height", 60]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
import _easycom_boolean_data from '@/components/boolean-data/boolean-data.vue'
