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
import io.dcloud.uniapp.extapi.getBackgroundAudioManager as uni_getBackgroundAudioManager
open class GenPagesAPIGetBackgroundAudioManagerGetBackgroundAudioManager : BasePage {
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
        onLoad(fun(_: OnLoadOptions) {
            var bgAudioMannager = uni_getBackgroundAudioManager()
            bgAudioMannager.title = "致爱丽丝" + this.count
            bgAudioMannager.epname = "专辑名：致爱丽丝" + this.count
            bgAudioMannager.singer = "歌手：暂无" + this.count
            bgAudioMannager.coverImgUrl = "https://web-assets.dcloud.net.cn/unidoc/zh/Alice.jpeg"
            bgAudioMannager.src = this.dataUrl
            this.currentTime = bgAudioMannager.currentTime
            this.duration = bgAudioMannager.duration
            bgAudioMannager.onCanplay(fun(_result){
                console.log("音频进入可以播放状态事件", " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:67")
                this.buffered = bgAudioMannager.buffered
                this.duration = bgAudioMannager.duration
            }
            )
            bgAudioMannager.onPlay(fun(_result){
                console.log("开始播放", " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:72")
                this.playing = true
            }
            )
            bgAudioMannager.onPause(fun(_result){
                console.log("暂停播放", " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:76")
                this.playing = false
            }
            )
            bgAudioMannager.onStop(fun(_result){
                console.log("停止播放", " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:80")
                this.playing = false
            }
            )
            bgAudioMannager.onEnded(fun(_result){
                if (this.isLoop == false) {
                    console.log("播放结束", " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:85")
                    this.playing = false
                    this.currentTime = 0
                    this.isPlayEnd = true
                    (this.`$refs`["slider"] as UniSliderElement).value = 0
                } else {
                    console.log("播放结束, 开始循环播放", " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:91")
                    this.bgAudioMannager!!.src = this.dataUrl
                    this.bgAudioMannager?.play()
                }
            }
            )
            bgAudioMannager.onNext(fun(_result){
                this.count++
                console.log("下一曲", this.count, " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:98")
                this.bgAudioMannager?.stop()
                bgAudioMannager.title = "致爱丽丝" + this.count
                bgAudioMannager.singer = "歌手：暂无" + this.count
                this.dataUrl = "https://web-ext-storage.dcloud.net.cn/uni-app/ForElise.mp3"
                bgAudioMannager.coverImgUrl = "https://qiniu-web-assets.dcloud.net.cn/unidoc/zh/music-a.png"
                this.bgAudioMannager!!.src = this.dataUrl
                this.bgAudioMannager?.play()
            }
            )
            bgAudioMannager.onPrev(fun(_result){
                this.count--
                console.log("上一曲", this.count, " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:109")
                this.bgAudioMannager?.stop()
                bgAudioMannager.title = "致爱丽丝" + this.count
                bgAudioMannager.singer = "歌手：暂无" + this.count
                this.dataUrl = "https://web-ext-storage.dcloud.net.cn/uni-app/ForElise.mp3"
                bgAudioMannager.coverImgUrl = "https://web-assets.dcloud.net.cn/unidoc/zh/Alice.jpeg"
                this.bgAudioMannager!!.src = this.dataUrl
                this.bgAudioMannager?.play()
            }
            )
            bgAudioMannager.onSeeking(fun(_result){
                console.log("音频进行 seek 操作事件", " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:119")
            }
            )
            bgAudioMannager.onSeeked(fun(_result){
                console.log("音频完成 seek 操作事件", " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:122")
            }
            )
            bgAudioMannager.onWaiting(fun(_result){
                console.log("音频加载中事件", " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:125")
            }
            )
            bgAudioMannager.onTimeUpdate(fun(_result){
                console.log("onTimeUpdate", bgAudioMannager.currentTime, " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:128")
                if (this._isChanging) {
                    return
                }
                this.currentTime = this.bgAudioMannager!!.currentTime
                this.buffered = this.bgAudioMannager!!.buffered
                console.log("onTimeUpdateCb", this.currentTime, " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:132")
                if (this.currentTime > this.buffered) {
                    console.log("缓冲不足", " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:142")
                }
            }
            )
            bgAudioMannager.onError(fun(err){
                console.log("播放出错err", err, " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:146")
            }
            )
            this.bgAudioMannager = bgAudioMannager
            this.playing = !bgAudioMannager.paused
            console.log("currentTime=", this.bgAudioMannager!!.currentTime, this.bgAudioMannager!!.currentTime == 0, " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:150")
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        val _component_boolean_data = resolveEasyComponent("boolean-data", GenComponentsBooleanDataBooleanDataClass)
        val _component_slider = resolveComponent("slider")
        return createElementVNode("view", null, utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                "title"
            )),
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                createElementVNode("text", null, " 注意：1.离开当前页面后背景音乐将保持播放；\n 2. 硬退出app、调用stop api、播放结束都会清理后台控制中心和锁屏信息显示 "),
                createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "是否循环播放", "onChange" to _ctx.setLoop), null, 8, utsArrayOf(
                    "onChange"
                )),
                createElementVNode("view", utsMapOf("class" to "uni-common-mt"), utsArrayOf(
                    createVNode(_component_slider, utsMapOf("ref" to "slider", "value" to _ctx.position, "min" to 0, "max" to _ctx.duration, "onChanging" to _ctx.onchanging, "onChange" to _ctx.onchange), null, 8, utsArrayOf(
                        "value",
                        "max",
                        "onChanging",
                        "onChange"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "page-body-buttons"), utsArrayOf(
                    if (isTrue(_ctx.playing)) {
                        createElementVNode(Fragment, utsMapOf("key" to 0), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "page-body-button", "onClick" to _ctx.stop), utsArrayOf(
                                createElementVNode("image", utsMapOf("class" to "image", "src" to "/static/stop.png"))
                            ), 8, utsArrayOf(
                                "onClick"
                            )),
                            createElementVNode("view", utsMapOf("class" to "page-body-button", "onClick" to _ctx.pause, "style" to normalizeStyle(utsMapOf("margin-top" to "20px"))), utsArrayOf(
                                createElementVNode("image", utsMapOf("class" to "image", "src" to "/static/pause.png"))
                            ), 12, utsArrayOf(
                                "onClick"
                            ))
                        ), 64)
                    } else {
                        createCommentVNode("v-if", true)
                    }
                    ,
                    if (isTrue(!_ctx.playing)) {
                        createElementVNode("view", utsMapOf("key" to 1, "class" to "page-body-button", "onClick" to _ctx.play), utsArrayOf(
                            createElementVNode("image", utsMapOf("class" to "image", "src" to "/static/play.png"))
                        ), 8, utsArrayOf(
                            "onClick"
                        ))
                    } else {
                        createCommentVNode("v-if", true)
                    }
                    ,
                    createElementVNode("view", utsMapOf("class" to "page-body-button"))
                ))
            ))
        ))
    }
    open var title: String by `$data`
    open var bgAudioMannager: BackgroundAudioManager? by `$data`
    open var dataUrl: String by `$data`
    open var playing: Boolean by `$data`
    open var playTime: Number by `$data`
    open var formatedPlayTime: String by `$data`
    open var count: Number by `$data`
    open var isPlayEnd: Boolean by `$data`
    open var duration: Number by `$data`
    open var currentTime: Number by `$data`
    open var _isChanging: Boolean by `$data`
    open var buffered: Number by `$data`
    open var isLoop: Boolean by `$data`
    open var position: Number by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "backgroundAudio", "bgAudioMannager" to null as BackgroundAudioManager?, "dataUrl" to "https://web-ext-storage.dcloud.net.cn/uni-app/ForElise.mp3", "playing" to false, "playTime" to 0, "formatedPlayTime" to "00:00:00", "count" to 100, "isPlayEnd" to false, "duration" to 100, "currentTime" to 0, "_isChanging" to false, "buffered" to 0, "isLoop" to false, "position" to computed<Number>(fun(): Number {
            return if (this.isPlayEnd) {
                0
            } else {
                this.currentTime
            }
        }
        ))
    }
    open var play = ::gen_play_fn
    open fun gen_play_fn() {
        console.log("play", " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:154")
        this.isPlayEnd = false
        this.bgAudioMannager!!.play()
    }
    open var pause = ::gen_pause_fn
    open fun gen_pause_fn() {
        this.bgAudioMannager?.pause()
    }
    open var stop = ::gen_stop_fn
    open fun gen_stop_fn() {
        this.bgAudioMannager?.stop()
        this.playing = false
    }
    open var onchanging = ::gen_onchanging_fn
    open fun gen_onchanging_fn() {
        this._isChanging = true
    }
    open var onchange = ::gen_onchange_fn
    open fun gen_onchange_fn(e: UniSliderChangeEvent) {
        var pos = e.detail.value
        console.log("pos", pos, " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:170")
        this.bgAudioMannager!!.seek(pos)
        this._isChanging = false
    }
    open var setLoop = ::gen_setLoop_fn
    open fun gen_setLoop_fn() {
        this.isLoop = !this.isLoop
        console.log("当前是否设置循环播放，loop= ", this.isLoop, " at pages/API/get-background-audio-manager/get-background-audio-manager.uvue:176")
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
                return utsMapOf("image" to padStyleMapOf(utsMapOf("width" to 75, "height" to 75)), "page-body-text" to padStyleMapOf(utsMapOf("paddingTop" to 0, "paddingRight" to 15, "paddingBottom" to 0, "paddingLeft" to 15)), "page-body-wrapper" to padStyleMapOf(utsMapOf("marginTop" to 0)), "page-body-info" to padStyleMapOf(utsMapOf("paddingBottom" to 25)), "time-big" to padStyleMapOf(utsMapOf("fontSize" to 30, "marginTop" to 10, "marginRight" to 10, "marginBottom" to 10, "marginLeft" to 10)), "slider" to padStyleMapOf(utsMapOf("width" to 315)), "play-time" to padStyleMapOf(utsMapOf("width" to "100%", "paddingTop" to 10, "paddingRight" to 0, "paddingBottom" to 10, "paddingLeft" to 0, "display" to "flex", "justifyContent" to "space-between", "boxSizing" to "border-box")), "page-body-buttons" to padStyleMapOf(utsMapOf("display" to "flex", "justifyContent" to "center", "marginTop" to 50, "flexDirection" to "column")), "page-body-button" to padStyleMapOf(utsMapOf("flexDirection" to "row", "justifyContent" to "center")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
