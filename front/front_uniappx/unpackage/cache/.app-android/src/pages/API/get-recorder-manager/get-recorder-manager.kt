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
import io.dcloud.uniapp.extapi.getRecorderManager as uni_getRecorderManager
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesAPIGetRecorderManagerGetRecorderManager : BasePage {
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
        onUnload(fun() {
            this.end()
        }
        , __ins)
        onLoad(fun(_: OnLoadOptions) {
            this.music = uni_createInnerAudioContext()
            this.music!!.onEnded(fun(_result){
                clearInterval(this.playTimeInterval)
                var playTime: Number = 0
                console.log("play voice finished", " at pages/API/get-recorder-manager/get-recorder-manager.uvue:96")
                this.playing = false
                this.formatedPlayTime = this.formatTime(playTime)
                this.playTime = playTime
            }
            )
            this.recorderManager = uni_getRecorderManager()
            this.recorderManager!!.onStart(fun(_result){
                console.log("recorder onStart", " at pages/API/get-recorder-manager/get-recorder-manager.uvue:104")
                this.disableStartBtn = true
                this.disablePauseBtn = false
                this.disableResumeBtn = false
                this.recording = true
                this.recordTime = 0
                this.recordTimeInterval = setInterval(fun(){
                    this.recordTime += 1
                    this.formatedRecordTime = this.formatTime(this.recordTime)
                }
                , 1000)
            }
            )
            this.recorderManager!!.onStop(fun(res){
                console.log("on stop", res.tempFilePath, " at pages/API/get-recorder-manager/get-recorder-manager.uvue:116")
                this.disablePauseBtn = false
                this.disableResumeBtn = false
                this.disableStartBtn = false
                this.music!!.src = res.tempFilePath
                clearInterval(this.recordTimeInterval)
                this.hasRecord = true
                this.recording = false
            }
            )
            this.recorderManager!!.onError(fun(res){
                console.log("recorder onError", JSON.stringify(res), " at pages/API/get-recorder-manager/get-recorder-manager.uvue:126")
                this.disablePauseBtn = false
                this.disableResumeBtn = false
                this.disableStartBtn = false
                this.registerError = true
                uni_showToast(ShowToastOptions(title = JSON.stringify(res)))
            }
            )
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        val _component_radio = resolveComponent("radio")
        val _component_radio_group = resolveComponent("radio-group")
        return createElementVNode(Fragment, null, utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                "title"
            )),
            createElementVNode("view", utsMapOf("class" to "page-body-time"), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "time-big"), toDisplayString(_ctx.formatedRecordTime), 1)
            )),
            createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("button", utsMapOf("class" to "btnstyle", "size" to "default", "onClick" to _ctx.registerOnStart), "注册onStart", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "btnstyle", "size" to "default", "onClick" to _ctx.registeronStop), "注册onStop", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "btnstyle", "size" to "default", "id" to "btn-error", "onClick" to _ctx.registeronError), "注册onError", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "btnstyle", "size" to "default", "onClick" to _ctx.registeronPause), "注册onPause", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "btnstyle", "size" to "default", "onClick" to _ctx.registeronResume), "注册onResume", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "btnstyle", "size" to "default", "onClick" to _ctx.registeronInterruptionBegin), "注册onInterruptionBegin", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "btnstyle", "size" to "default", "onClick" to _ctx.registeronInterruptionEnd), "注册onInterruptionEnd", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list"), utsArrayOf(
                        createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-bottom" to "10px"))), " 请选择录音格式：", 4),
                        createVNode(_component_radio_group, utsMapOf("class" to "uni-flex uni-row", "onChange" to _ctx.radioChange, "style" to normalizeStyle(utsMapOf("flex-wrap" to "wrap"))), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                            return utsArrayOf(
                                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.items, fun(item, index, __index, _cached): Any {
                                    return createVNode(_component_radio, utsMapOf("class" to "uni-list-cell", "style" to normalizeStyle(utsMapOf("margin-right" to "15px")), "key" to item.value, "value" to item.value, "checked" to (index === _ctx.current)), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                        return utsArrayOf(
                                            toDisplayString(item.name)
                                        )
                                    }
                                    ), "_" to 2), 1032, utsArrayOf(
                                        "style",
                                        "value",
                                        "checked"
                                    ))
                                }
                                ), 128)
                            )
                        }
                        ), "_" to 1), 8, utsArrayOf(
                            "onChange",
                            "style"
                        ))
                    )),
                    createElementVNode("button", utsMapOf("class" to "btnstyle", "size" to "default", "disabled" to _ctx.disableStartBtn, "id" to "btn-startRecord", "onClick" to fun(){
                        _ctx.startRecord()
                    }
                    ), "开始录制", 8, utsArrayOf(
                        "disabled",
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "btnstyle", "size" to "default", "disabled" to _ctx.disablePauseBtn, "onClick" to _ctx.pauseRecord), "暂停录制", 8, utsArrayOf(
                        "disabled",
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "btnstyle", "size" to "default", "disabled" to _ctx.disableResumeBtn, "onClick" to _ctx.resumeRecord), "继续录制", 8, utsArrayOf(
                        "disabled",
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "btnstyle", "size" to "default", "id" to "btn-stopRecord", "onClick" to _ctx.stopRecord), "停止录制", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "btnstyle", "size" to "default", "id" to "btn-startPlay", "onClick" to _ctx.playVoice), "开始播放", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("class" to "btnstyle", "size" to "default", "id" to "btn-stopPlay", "onClick" to _ctx.stopVoice), "停止播放", 8, utsArrayOf(
                        "onClick"
                    ))
                ))
            ), 4)
        ), 64)
    }
    open var disableStartBtn: Boolean by `$data`
    open var disableResumeBtn: Boolean by `$data`
    open var disablePauseBtn: Boolean by `$data`
    open var title: String by `$data`
    open var recording: Boolean by `$data`
    open var playing: Boolean by `$data`
    open var registerError: Boolean by `$data`
    open var hasRecord: Boolean by `$data`
    open var playTimeInterval: Number by `$data`
    open var recordTimeInterval: Number by `$data`
    open var tempFilePath: String by `$data`
    open var recordTime: Number by `$data`
    open var current: Number by `$data`
    open var playTime: Number by `$data`
    open var formatedRecordTime: String by `$data`
    open var formatedPlayTime: String by `$data`
    open var recorderManager: RecorderManager? by `$data`
    open var music: InnerAudioContext? by `$data`
    open var items: UTSArray<ItemType9> by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("disableStartBtn" to false, "disableResumeBtn" to false, "disablePauseBtn" to false, "title" to "start/stopRecord、play/stopVoice", "recording" to false, "playing" to false, "registerError" to false, "hasRecord" to false, "playTimeInterval" to 0, "recordTimeInterval" to 0, "tempFilePath" to "", "recordTime" to 0, "current" to 0, "playTime" to 0, "formatedRecordTime" to "00:00:00", "formatedPlayTime" to "00:00:00", "recorderManager" to null as RecorderManager?, "music" to null as InnerAudioContext?, "items" to utsArrayOf<ItemType9>(ItemType9(value = "aac", name = "aac"), ItemType9(value = "mp3", name = "mp3"), ItemType9(value = "wav", name = "wav"), ItemType9(value = "pcm", name = "pcm")))
    }
    open var radioChange = ::gen_radioChange_fn
    open fun gen_radioChange_fn(e: UniRadioGroupChangeEvent) {
        run {
            var i: Number = 0
            while(i < this.items.length){
                if (this.items[i].value === e.detail.value) {
                    this.current = i
                    break
                }
                i++
            }
        }
    }
    open var registerOnStart = ::gen_registerOnStart_fn
    open fun gen_registerOnStart_fn() {
        uni_showToast(ShowToastOptions(title = "already registerOnStart"))
        this.recorderManager!!.onStart(fun(_result){
            console.log("recorder on start", " at pages/API/get-recorder-manager/get-recorder-manager.uvue:152")
            this.recordTime = 0
            this.recording = true
            this.recordTimeInterval = setInterval(fun(){
                this.recordTime += 1
                this.formatedRecordTime = this.formatTime(this.recordTime)
            }
            , 1000)
        }
        )
    }
    open var registeronStop = ::gen_registeronStop_fn
    open fun gen_registeronStop_fn() {
        uni_showToast(ShowToastOptions(title = "already registeronStop"))
        this.recorderManager!!.onStop(fun(res){
            console.log("on stop", res, " at pages/API/get-recorder-manager/get-recorder-manager.uvue:166")
            this.music!!.src = res.tempFilePath
            clearInterval(this.recordTimeInterval)
            this.hasRecord = true
            this.recording = false
        }
        )
    }
    open var registeronError = ::gen_registeronError_fn
    open fun gen_registeronError_fn() {
        uni_showToast(ShowToastOptions(title = "already registeronError"))
        this.registerError = true
        this.recorderManager!!.onError(fun(res){
            console.log("recorder onError", JSON.stringify(res), " at pages/API/get-recorder-manager/get-recorder-manager.uvue:179")
        }
        )
    }
    open var registeronPause = ::gen_registeronPause_fn
    open fun gen_registeronPause_fn() {
        uni_showToast(ShowToastOptions(title = "already registeronPause"))
        this.recorderManager?.onPause(fun(_result){
            console.log("recorder onPause", " at pages/API/get-recorder-manager/get-recorder-manager.uvue:187")
        }
        )
    }
    open var registeronResume = ::gen_registeronResume_fn
    open fun gen_registeronResume_fn() {
        uni_showToast(ShowToastOptions(title = "already registeronStop"))
        this.recorderManager?.onResume(fun(_result){
            console.log("recorder onResume", " at pages/API/get-recorder-manager/get-recorder-manager.uvue:195")
        }
        )
    }
    open var registeronOnFrameRecorded = ::gen_registeronOnFrameRecorded_fn
    open fun gen_registeronOnFrameRecorded_fn() {
        uni_showToast(ShowToastOptions(title = "already registeronOnFrameRecorded"))
        this.recorderManager?.onFrameRecorded(fun(res){
            console.log("recorder onFrameRecorded----", res, " at pages/API/get-recorder-manager/get-recorder-manager.uvue:203")
        }
        )
    }
    open var registeronInterruptionBegin = ::gen_registeronInterruptionBegin_fn
    open fun gen_registeronInterruptionBegin_fn() {
        uni_showToast(ShowToastOptions(title = "already registeronInterruptionBegin"))
        this.recorderManager?.onInterruptionBegin(fun(_result){
            console.log("recorder onInterruptionBegin", " at pages/API/get-recorder-manager/get-recorder-manager.uvue:211")
        }
        )
    }
    open var registeronInterruptionEnd = ::gen_registeronInterruptionEnd_fn
    open fun gen_registeronInterruptionEnd_fn() {
        uni_showToast(ShowToastOptions(title = "already registeronInterruptionEnd"))
        this.recorderManager?.onInterruptionBegin(fun(_result){
            console.log("recorder registeronInterruptionEnd", " at pages/API/get-recorder-manager/get-recorder-manager.uvue:219")
        }
        )
    }
    open var pauseRecord = ::gen_pauseRecord_fn
    open fun gen_pauseRecord_fn() {
        console.log("recorder pause", " at pages/API/get-recorder-manager/get-recorder-manager.uvue:223")
        this.recorderManager?.pause()
        if (this.recording) {
            this.disableStartBtn = false
            this.disablePauseBtn = true
            this.disableResumeBtn = false
        }
        clearInterval(this.recordTimeInterval)
    }
    open var resumeRecord = ::gen_resumeRecord_fn
    open fun gen_resumeRecord_fn() {
        console.log("recorder resume ", this.recorderManager, " at pages/API/get-recorder-manager/get-recorder-manager.uvue:233")
        this.recorderManager?.resume()
        this.recorderManager?.onResume(fun(_result){
            console.log("recorder onResume", " at pages/API/get-recorder-manager/get-recorder-manager.uvue:236")
        }
        )
        if (this.recording) {
            this.disableStartBtn = false
            this.disablePauseBtn = false
            this.disableResumeBtn = true
            this.recordTimeInterval = setInterval(fun(){
                this.recordTime += 1
                this.formatedRecordTime = this.formatTime(this.recordTime)
            }
            , 1000)
        }
    }
    open var formatTime = ::gen_formatTime_fn
    open fun gen_formatTime_fn(reassignedTime: Number): String {
        var time = reassignedTime
        if (UTSAndroid.`typeof`(time) !== "number" || time < 0) {
            return time.toString(10)
        }
        var hour = parseInt((time / 3600).toString(10))
        time = time % 3600
        var minute = parseInt((time / 60).toString(10))
        time = time % 60
        var second = time
        return utsArrayOf(
            hour,
            minute,
            second
        ).map(fun(n: Number): String {
            var str = n.toString(10)
            return if (str.length > 1) {
                str
            } else {
                "0" + str
            }
        }
        ).join(":")
    }
    open var startRecord = ::gen_startRecord_fn
    open fun gen_startRecord_fn() {
        if (this.recording) {
            uni_showToast(ShowToastOptions(title = if (this.disablePauseBtn) {
                "当前是录音暂停状态"
            } else {
                "当前正在录音"
            }
            ))
            return
        }
        console.log("startRecord", this.items[this.current].value, " at pages/API/get-recorder-manager/get-recorder-manager.uvue:272")
        this.recorderManager?.start(RecorderManagerStartOptions(format = this.items[this.current].value, sampleRate = 8000, numberOfChannels = 2, encodeBitRate = 48000, frameSize = 2))
    }
    open var stopRecord = ::gen_stopRecord_fn
    open fun gen_stopRecord_fn() {
        this.recorderManager?.stop()
        this.disableStartBtn = false
        this.disablePauseBtn = false
        this.disableResumeBtn = false
    }
    open var playVoice = ::gen_playVoice_fn
    open fun gen_playVoice_fn() {
        if (this.recording) {
            uni_showToast(ShowToastOptions(title = "当前录音还未结束"))
            return
        }
        console.log("play voice", " at pages/API/get-recorder-manager/get-recorder-manager.uvue:295")
        if (this.playing) {
            return
        }
        this.playing = true
        this.playTimeInterval = setInterval(fun(){
            if (this.playTime < this.recordTime) {
                this.playTime += 1
            }
            this.formatedRecordTime = this.formatTime(this.playTime)
        }
        , 1000)
        this.music?.play()
    }
    open var stopVoice = ::gen_stopVoice_fn
    open fun gen_stopVoice_fn() {
        if (this.recording) {
            uni_showToast(ShowToastOptions(title = "当前录音还未结束"))
            return
        }
        clearInterval(this.playTimeInterval)
        this.playing = false
        this.formatedRecordTime = this.formatTime(0)
        this.playTime = 0
        this.music?.stop()
    }
    open var end = ::gen_end_fn
    open fun gen_end_fn() {
        this.music?.stop()
        this.recorderManager?.stop()
        clearInterval(this.recordTimeInterval)
        clearInterval(this.playTimeInterval)
        this.recording = false
        this.playing = false
        this.hasRecord = false
        this.playTime = 0
        this.recordTime = 0
        this.formatedRecordTime = "00:00:00"
        this.formatedRecordTime = "00:00:00"
    }
    open var clear = ::gen_clear_fn
    open fun gen_clear_fn() {
        this.end()
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
                return utsMapOf("page-body-time" to padStyleMapOf(utsMapOf("display" to "flex", "flexDirection" to "column", "alignItems" to "center")), "time-big" to padStyleMapOf(utsMapOf("fontSize" to 30, "marginTop" to 10, "marginRight" to 10, "marginBottom" to 10, "marginLeft" to 10)), "btnstyle" to padStyleMapOf(utsMapOf("marginLeft" to 30, "marginRight" to 30, "marginTop" to 10)), "uni-list" to padStyleMapOf(utsMapOf("borderBottomWidth" to 0, "borderBottomStyle" to "none", "borderBottomColor" to "#000000", "backgroundColor" to "rgba(0,0,0,0)", "marginLeft" to 30, "marginRight" to 30, "marginTop" to 10, "marginBottom" to 10)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
