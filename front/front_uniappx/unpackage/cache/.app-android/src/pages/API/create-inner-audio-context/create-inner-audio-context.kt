@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER", "NAME_SHADOWING", "UNNECESSARY_NOT_NULL_ASSERTION")
package uni.UNI740BA18
import io.dcloud.uniapp.*
import io.dcloud.uniapp.extapi.*
import io.dcloud.uniapp.framework.*
import io.dcloud.uniapp.runtime.*
import io.dcloud.uniapp.vue.*
import io.dcloud.uniapp.vue.shared.*
import io.dcloud.unicloud.*
import io.dcloud.uts.*
import io.dcloud.uts.Map
import io.dcloud.uts.Set
import io.dcloud.uts.UTSAndroid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import io.dcloud.uniapp.extapi.createInnerAudioContext as uni_createInnerAudioContext
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesAPICreateInnerAudioContextCreateInnerAudioContext : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {
        onLoad(fun(_: OnLoadOptions) {
            stat_instance.onLoad(this)
        }
        , __ins)
        onPageShow(fun() {
            stat_instance.onShow(this)
        }
        , __ins)
        onPageHide(fun() {
            stat_instance.onHide(this)
        }
        , __ins)
        onUnload(fun() {
            stat_instance.onUnload(this)
        }
        , __ins)
        onReady(fun() {
            this._audioContext = uni_createInnerAudioContext()
            this._audioContext!!.src = audioUrl
            this.volume = this._audioContext!!.volume
            this.onCanplay()
            this._audioContext!!.onPlay(fun(_result){
                this.isPaused = false
                this.isPlaying = true
                console.log("开始播放", this.isPaused, " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:112")
            }
            )
            this.onTimeUpdateCb = fun(res: Any){
                if (this._isChanging) {
                    return
                }
                this.currentTime = this._audioContext!!.currentTime
                this.buffered = this._audioContext!!.buffered
                console.log("onTimeUpdateCb", this.currentTime, " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:119")
                if (this.currentTime > this.buffered) {
                    console.log("缓冲不足", " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:129")
                }
            }
            this.onWaitingCb = fun(res: Any){
                console.log("音频加载中事件", " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:134")
                this.onWaitingTest = true
            }
            this.onTimeUpdate()
            this.onError()
            this.onEnded()
        }
        , __ins)
        onUnload(fun() {
            if (this._audioContext != null) {
                if (this.isPlaying) {
                    this.stop()
                }
                this._audioContext!!.destroy()
            }
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        val _component_slider = resolveComponent("slider")
        val _component_boolean_data = resolveEasyComponent("boolean-data", GenComponentsBooleanDataBooleanDataClass)
        val _component_radio = resolveComponent("radio")
        val _component_radio_group = resolveComponent("radio-group")
        val _component_navigator = resolveComponent("navigator")
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                createVNode(_component_page_head, utsMapOf("title" to "audio")),
                createElementVNode("view", utsMapOf("class" to "uni-common-mt"), utsArrayOf(
                    createVNode(_component_slider, utsMapOf("ref" to "slider", "value" to _ctx.position, "min" to 0, "max" to _ctx.duration, "onChanging" to _ctx.onchanging, "onChange" to _ctx.onchange), null, 8, utsArrayOf(
                        "value",
                        "max",
                        "onChanging",
                        "onChange"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "uni-title-text"), "属性示例")
                )),
                createElementVNode("text", utsMapOf("class" to "uni-text-box uni-common-mt"), "当前音频播放位置（保留小数点后 3 位）：" + toDisplayString(_ctx.currentTime) + " s", 1),
                createElementVNode("text", utsMapOf("class" to "uni-text-box"), "音频的长度（单位:s）：" + toDisplayString(_ctx.duration) + " s", 1),
                createElementVNode("text", utsMapOf("class" to "uni-text-box"), "当前是否停止状态：" + toDisplayString(_ctx.isPaused), 1),
                createElementVNode("text", utsMapOf("class" to "uni-text-box"), "音频缓冲的时间点：" + toDisplayString(_ctx.buffered) + " s", 1),
                createElementVNode("text", utsMapOf("class" to "uni-text-box"), "当前音量：" + toDisplayString(_ctx.volume), 1),
                createElementVNode("button", utsMapOf("plain" to "", "disabled" to (_ctx.volume == 1), "onClick" to _ctx.increaseVolume), "增加音量", 8, utsArrayOf(
                    "disabled",
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("plain" to "", "disabled" to (_ctx.volume == 0), "onClick" to _ctx.decreaseVolume), "减少音量", 8, utsArrayOf(
                    "disabled",
                    "onClick"
                )),
                createElementVNode("text", utsMapOf("class" to "uni-subtitle-text uni-title"), "开始播放的位置（单位：s）"),
                createElementVNode("input", utsMapOf("value" to _ctx.startTime, "type" to "number", "placeholder" to "开始播放的位置（单位：s）", "class" to "uni-input", "onInput" to _ctx.startTimeInput), null, 40, utsArrayOf(
                    "value",
                    "onInput"
                )),
                createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "是否自动开始播放", "onChange" to _ctx.setAutoplay), null, 8, utsArrayOf(
                    "onChange"
                )),
                createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "是否循环播放", "onChange" to _ctx.setLoop), null, 8, utsArrayOf(
                    "onChange"
                )),
                createElementVNode("text", utsMapOf("class" to "uni-subtitle-text uni-title", "style" to normalizeStyle(utsMapOf("padding-left" to "10px", "padding-top" to "10px", "padding-right" to "10px"))), "播放倍率(Web/HarmonyOS 不支持)", 4),
                createVNode(_component_radio_group, utsMapOf("class" to "uni-flex uni-row radio-group", "onChange" to _ctx.playbackRateChange, "style" to normalizeStyle(utsMapOf("flex-wrap" to "wrap", "padding" to "10px"))), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createVNode(_component_radio, utsMapOf("value" to "0.5", "style" to normalizeStyle(utsMapOf("margin-right" to "3px"))), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                            return utsArrayOf(
                                "0.5 "
                            )
                        }
                        ), "_" to 1), 8, utsArrayOf(
                            "style"
                        )),
                        createVNode(_component_radio, utsMapOf("value" to "0.8", "style" to normalizeStyle(utsMapOf("margin-right" to "3px"))), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                            return utsArrayOf(
                                "0.8"
                            )
                        }
                        ), "_" to 1), 8, utsArrayOf(
                            "style"
                        )),
                        createVNode(_component_radio, utsMapOf("value" to "1.0", "style" to normalizeStyle(utsMapOf("margin-right" to "3px")), "checked" to _ctx.playbackRateChecked), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                            return utsArrayOf(
                                "1.0"
                            )
                        }
                        ), "_" to 1), 8, utsArrayOf(
                            "style",
                            "checked"
                        )),
                        createVNode(_component_radio, utsMapOf("value" to "1.25", "style" to normalizeStyle(utsMapOf("margin-right" to "3px"))), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                            return utsArrayOf(
                                "1.25"
                            )
                        }
                        ), "_" to 1), 8, utsArrayOf(
                            "style"
                        )),
                        createVNode(_component_radio, utsMapOf("value" to "1.5", "style" to normalizeStyle(utsMapOf("margin-right" to "3px"))), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                            return utsArrayOf(
                                "1.5"
                            )
                        }
                        ), "_" to 1), 8, utsArrayOf(
                            "style"
                        )),
                        createVNode(_component_radio, utsMapOf("value" to "2.0"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                            return utsArrayOf(
                                "2.0"
                            )
                        }
                        ), "_" to 1))
                    )
                }
                ), "_" to 1), 8, utsArrayOf(
                    "onChange",
                    "style"
                )),
                createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "uni-title-text"), "方法示例")
                )),
                createElementVNode("button", utsMapOf("disabled" to _ctx.isPlaying, "onClick" to _ctx.play, "class" to "uni-btn"), "播放", 8, utsArrayOf(
                    "disabled",
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("disabled" to !_ctx.isPlaying, "onClick" to _ctx.pause, "class" to "uni-btn"), "暂停", 8, utsArrayOf(
                    "disabled",
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("disabled" to !_ctx.isPlaying, "onClick" to _ctx.stop, "class" to "uni-btn"), "停止", 8, utsArrayOf(
                    "disabled",
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("onClick" to fun(){
                    _ctx.onchangeValue(20)
                }
                , "class" to "uni-btn"), "跳转到指定位置20", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("onClick" to _ctx.onTimeUpdate, "class" to "uni-btn"), "onTimeUpdate", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("onClick" to _ctx.offTimeUpdate, "class" to "uni-btn"), "offTimeUpdate", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("onClick" to _ctx.onWaiting, "class" to "uni-btn"), "onWaiting", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("onClick" to _ctx.offWaiting, "class" to "uni-btn"), "offWaiting", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("color" to "red", "font-size" to "15px", "margin-top" to "10px"))), "tip:销毁后请重新进入此界面再播放", 4),
                createElementVNode("button", utsMapOf("onClick" to _ctx.destory, "class" to "uni-btn"), "销毁", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "uni-title-text"), "格式/路径示例")
                )),
                createVNode(_component_navigator, utsMapOf("url" to "/pages/API/create-inner-audio-context/inner-audio-format", "class" to "uni-btn"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("button", utsMapOf("onClick" to _ctx.pause), "音频格式示例", 8, utsArrayOf(
                            "onClick"
                        ))
                    )
                }
                ), "_" to 1)),
                createVNode(_component_navigator, utsMapOf("url" to "/pages/API/create-inner-audio-context/inner-audio-path", "class" to "uni-btn"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("button", utsMapOf("onClick" to _ctx.pause), "音频路径示例", 8, utsArrayOf(
                            "onClick"
                        ))
                    )
                }
                ), "_" to 1)),
                createVNode(_component_navigator, utsMapOf("url" to "/pages/API/create-inner-audio-context/inner-audio-mult", "class" to "uni-btn"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("button", utsMapOf("onClick" to _ctx.pause), "多音频同时播放", 8, utsArrayOf(
                            "onClick"
                        ))
                    )
                }
                ), "_" to 1))
            ))
        ), 4)
    }
    open var title: String by `$data`
    open var currentTime: Number by `$data`
    open var duration: Number by `$data`
    open var startTime: Number by `$data`
    open var buffered: Number by `$data`
    open var volume: Number by `$data`
    open var isCanplay: Boolean by `$data`
    open var isPlaying: Boolean by `$data`
    open var isPaused: Boolean by `$data`
    open var isPlayEnd: Boolean by `$data`
    open var _isChanging: Boolean by `$data`
    open var _audioContext: InnerAudioContext? by `$data`
    open var onSeekingTest: Boolean by `$data`
    open var onSeekedTest: Boolean by `$data`
    open var onWaitingTest: Boolean by `$data`
    open var playbackRateChecked: Boolean by `$data`
    open var onTimeUpdateCb: (res: Any) -> Unit by `$data`
    open var onWaitingCb: (res: Any) -> Unit by `$data`
    open var position: Number by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "innerAudioContext", "currentTime" to 0, "duration" to 100, "startTime" to 0, "buffered" to 0, "volume" to 0.5, "isCanplay" to false, "isPlaying" to false, "isPaused" to true, "isPlayEnd" to false, "_isChanging" to false, "_audioContext" to null as InnerAudioContext?, "onSeekingTest" to false, "onSeekedTest" to false, "onWaitingTest" to false, "playbackRateChecked" to true, "onTimeUpdateCb" to fun(res: Any){}, "onWaitingCb" to fun(res: Any){}, "position" to computed<Number>(fun(): Number {
            return if (this.isPlayEnd) {
                0
            } else {
                this.currentTime
            }
        }
        ))
    }
    open var onCanplay = ::gen_onCanplay_fn
    open fun gen_onCanplay_fn() {
        this._audioContext!!.onCanplay(fun(_result){
            console.log("音频进入可以播放状态事件", " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:154")
            this.isCanplay = true
            this.buffered = this._audioContext!!.buffered
            this.duration = this._audioContext!!.duration
        }
        )
    }
    open var onchanging = ::gen_onchanging_fn
    open fun gen_onchanging_fn() {
        this._isChanging = true
    }
    open var onchange = ::gen_onchange_fn
    open fun gen_onchange_fn(e: UniSliderChangeEvent) {
        var pos = e.detail.value
        console.log("pos", pos, " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:170")
        this.onSeeking()
        this.onSeeked()
        this._audioContext!!.seek(pos)
        this._isChanging = false
    }
    open var onchangeValue = ::gen_onchangeValue_fn
    open fun gen_onchangeValue_fn(pos: Number) {
        this.onSeeking()
        this.onSeeked()
        this._audioContext!!.seek(pos)
        this._isChanging = false
    }
    open var startTimeInput = ::gen_startTimeInput_fn
    open fun gen_startTimeInput_fn(e: UniInputEvent) {
        var startTimeValue = parseInt(e.detail.value)
        this._audioContext!!.startTime = startTimeValue
        this.onchangeValue(startTimeValue)
    }
    open var setAutoplay = ::gen_setAutoplay_fn
    open fun gen_setAutoplay_fn() {
        this._audioContext!!.autoplay = !this._audioContext!!.autoplay
        console.log(this._audioContext!!.autoplay, "autoplay", " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:189")
    }
    open var setLoop = ::gen_setLoop_fn
    open fun gen_setLoop_fn() {
        this._audioContext!!.loop = !this._audioContext!!.loop
        console.log(this._audioContext!!.loop, "loop", " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:193")
    }
    open var play = ::gen_play_fn
    open fun gen_play_fn() {
        if (!this.isCanplay) {
            uni_showToast(ShowToastOptions(title = "音频未进入可以播放状态，请稍后再试", icon = "error"))
            return
        }
        this.isPlaying = true
        this._audioContext!!.play()
        this.isPlayEnd = false
        if (this._audioContext!!.startTime > 0) {
            this.onchangeValue(this._audioContext!!.startTime)
        }
    }
    open var onSeeking = ::gen_onSeeking_fn
    open fun gen_onSeeking_fn() {
        this._audioContext!!.onSeeking(fun(_result){
            console.log("音频进行 seek 操作事件", " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:212")
            this.onSeekingTest = true
        }
        )
    }
    open var onSeeked = ::gen_onSeeked_fn
    open fun gen_onSeeked_fn() {
        this._audioContext!!.onSeeked(fun(_result){
            console.log("音频完成 seek 操作事件", " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:218")
            this.onSeekedTest = true
        }
        )
    }
    open var onWaiting = ::gen_onWaiting_fn
    open fun gen_onWaiting_fn() {
        this._audioContext!!.onWaiting(this.onWaitingCb)
    }
    open var offWaiting = ::gen_offWaiting_fn
    open fun gen_offWaiting_fn() {
        this._audioContext!!.offWaiting(this.onWaitingCb)
    }
    open var onTimeUpdate = ::gen_onTimeUpdate_fn
    open fun gen_onTimeUpdate_fn() {
        this._audioContext!!.onTimeUpdate(this.onTimeUpdateCb)
    }
    open var offTimeUpdate = ::gen_offTimeUpdate_fn
    open fun gen_offTimeUpdate_fn() {
        this._audioContext!!.offTimeUpdate(this.onTimeUpdateCb)
    }
    open var increaseVolume = ::gen_increaseVolume_fn
    open fun gen_increaseVolume_fn() {
        this.volume = Math.min(this.volume + 0.1, 1)
        this.volume = parseFloat(this.volume.toFixed(1))
        this._audioContext!!.volume = this.volume
        console.log("增加音量", this.volume, " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:238")
    }
    open var decreaseVolume = ::gen_decreaseVolume_fn
    open fun gen_decreaseVolume_fn() {
        this.volume = Math.max(this.volume - 0.1, 0)
        this.volume = parseFloat(this.volume.toFixed(1))
        console.log("减少音量", this.volume, " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:243")
        this._audioContext!!.volume = this.volume
    }
    open var onEnded = ::gen_onEnded_fn
    open fun gen_onEnded_fn() {
        this._audioContext!!.onEnded(fun(_result){
            console.log("播放结束", " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:248")
            this.currentTime = 0
            this.startTime = 0
            this.isPlaying = false
            this.isPaused = true
            this.isPlayEnd = true
            (this.`$refs`["slider"] as UniSliderElement).value = 0
        }
        )
    }
    open var onError = ::gen_onError_fn
    open fun gen_onError_fn() {
        this._audioContext!!.onError(fun(err){
            console.log("err", err, " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:259")
            this.isPlaying = false
            this.isPaused = true
        }
        )
    }
    open var pause = ::gen_pause_fn
    open fun gen_pause_fn() {
        this._audioContext!!.onPause(fun(_result){
            console.log("音频暂停事件", " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:266")
            this.isPaused = true
        }
        )
        this._audioContext!!.pause()
        this.isPlaying = false
    }
    open var stop = ::gen_stop_fn
    open fun gen_stop_fn() {
        console.log("stop", " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:273")
        this._audioContext!!.onStop(fun(_result){
            this.isPaused = true
            console.log("音频停止事件", " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:277")
        }
        )
        this._audioContext!!.stop()
        this.isPlaying = false
        console.log("stop", this.isPaused, " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:281")
    }
    open var destory = ::gen_destory_fn
    open fun gen_destory_fn() {
        if (this._audioContext != null) {
            this.isPlaying = false
            this._audioContext!!.destroy()
        }
    }
    open var playbackRateChange = ::gen_playbackRateChange_fn
    open fun gen_playbackRateChange_fn(e: UniRadioGroupChangeEvent) {
        console.log(parseFloat(e.detail.value), " at pages/API/create-inner-audio-context/create-inner-audio-context.uvue:291")
        this._audioContext!!.playbackRate = parseFloat(e.detail.value)
    }
    open var setSrc = ::gen_setSrc_fn
    open fun gen_setSrc_fn(src: String) {
        if (this._audioContext != null) {
            this._audioContext!!.src = src
        }
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ), utsArrayOf(
                GenApp.styles
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("play-time-area" to padStyleMapOf(utsMapOf("display" to "flex", "flexDirection" to "row", "marginTop" to 20)), "duration" to padStyleMapOf(utsMapOf("marginLeft" to "auto")), "play-button-area" to padStyleMapOf(utsMapOf("display" to "flex", "flexDirection" to "row", "justifyContent" to "center", "marginTop" to 50, "marginRight" to 0, "marginBottom" to 50, "marginLeft" to 0)), "icon-play" to padStyleMapOf(utsMapOf("width" to 60, "height" to 60)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
