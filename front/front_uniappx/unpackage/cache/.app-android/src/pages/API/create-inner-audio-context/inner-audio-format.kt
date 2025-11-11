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
open class GenPagesAPICreateInnerAudioContextInnerAudioFormat : BasePage {
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
            this._audioContext!!.onPlay(fun(_result){
                console.log("开始播放", " at pages/API/create-inner-audio-context/inner-audio-format.uvue:105")
            }
            )
            this._audioContext!!.onPause(fun(_result){
                console.log("播放暂停", " at pages/API/create-inner-audio-context/inner-audio-format.uvue:108")
            }
            )
            this._audioContext!!.onEnded(fun(_result){
                console.log("播放结束", " at pages/API/create-inner-audio-context/inner-audio-format.uvue:111")
                this.isPlaying = false
            }
            )
            this._audioContext!!.onError(fun(err){
                this.isPlaying = false
                console.log("err", err, " at pages/API/create-inner-audio-context/inner-audio-format.uvue:116")
            }
            )
        }
        , __ins)
        onUnload(fun() {
            if (this._audioContext != null) {
                this.pause()
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
        return createElementVNode(Fragment, null, utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                "title"
            )),
            createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt"), utsArrayOf(
                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.supportFormats, fun(item, index, __index, _cached): Any {
                        return createElementVNode("view", utsMapOf("class" to "formats", "key" to index), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), toDisplayString(item.format), 1),
                            createElementVNode("image", utsMapOf("class" to "icon-play", "src" to if ((_ctx.isPlaying && _ctx.playIndex == index)) {
                                "/static/pause.png"
                            } else {
                                "/static/play.png"
                            }
                            , "onClick" to fun(){
                                _ctx.play(item.src, index)
                            }
                            ), null, 8, utsArrayOf(
                                "src",
                                "onClick"
                            ))
                        ))
                    }
                    ), 128),
                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.notSupportFormats, fun(item, index, __index, _cached): Any {
                        return createElementVNode("view", utsMapOf("class" to "formats", "key" to index), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), toDisplayString(item.format + item.support), 1),
                            createElementVNode("image", utsMapOf("class" to "icon-play", "src" to if ((_ctx.isPlaying && _ctx.playIndex == index)) {
                                "/static/pause.png"
                            } else {
                                "/static/play.png"
                            }
                            , "onClick" to fun(){
                                _ctx.play(item.src, index)
                            }
                            ), null, 8, utsArrayOf(
                                "src",
                                "onClick"
                            ))
                        ))
                    }
                    ), 128)
                ))
            ), 4)
        ), 64)
    }
    open var title: String by `$data`
    open var playIndex: Number by `$data`
    open var isPlaying: Boolean by `$data`
    open var _audioContext: InnerAudioContext? by `$data`
    open var supportFormats: UTSArray<AudioFormat> by `$data`
    open var notSupportFormats: UTSArray<AudioFormat> by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "audio-format", "playIndex" to 0, "isPlaying" to false, "_audioContext" to null as InnerAudioContext?, "supportFormats" to utsArrayOf<AudioFormat>(AudioFormat(format = "mp3", src = "https://web-ext-storage.dcloud.net.cn/uni-app-x/audio/ForElise.mp3"), AudioFormat(format = "mp4", src = "https://web-ext-storage.dcloud.net.cn/uni-app-x/audio/ForElise.mp4"), AudioFormat(format = "m4a", src = "https://web-ext-storage.dcloud.net.cn/uni-app-x/audio/ForElise.m4a"), AudioFormat(format = "aac", src = "https://web-ext-storage.dcloud.net.cn/uni-app-x/audio/ForElise.aac"), AudioFormat(format = "flac", src = "https://web-ext-storage.dcloud.net.cn/uni-app-x/audio/ForElise.flac"), AudioFormat(format = "wav", src = "https://web-ext-storage.dcloud.net.cn/uni-app-x/audio/ForElise.wav"), AudioFormat(format = "m3u8", src = "https://qiniu-web-assets.dcloud.net.cn/audio/sample/m3u8/ForElise.m3u8")), "notSupportFormats" to utsArrayOf<AudioFormat>(AudioFormat(format = "ogg", support = "(iOS 不支持)", src = "https://web-ext-storage.dcloud.net.cn/uni-app-x/audio/ForElise.ogg"), AudioFormat(format = "wma", support = "(iOS/Android/web/Harmony 不支持)", src = "https://web-ext-storage.dcloud.net.cn/uni-app-x/audio/ForElise.wma"), AudioFormat(format = "aiff", support = "(Android/web/Harmony 不支持)", src = "https://web-ext-storage.dcloud.net.cn/uni-app-x/audio/ForElise.aiff"), AudioFormat(format = "caf", support = "(Android/web/Harmony 不支持)", src = "https://web-ext-storage.dcloud.net.cn/uni-app-x/audio/ForElise.caf"), AudioFormat(format = "dash", support = "(iOS 不支持)", src = "https://qiniu-web-assets.dcloud.net.cn/audio/sample/dash/ForElise.mpd"), AudioFormat(format = "错误格式", support = "(iOS/Android/web/Harmony 不支持)", src = "https://web-ext-storage.dcloud.net.cn/uni-app-x/audio/ForElise.wmaa")))
    }
    open var pause = ::gen_pause_fn
    open fun gen_pause_fn() {
        this._audioContext!!.pause()
        this.isPlaying = false
    }
    open var play = ::gen_play_fn
    open fun gen_play_fn(audioUrl: String, index: Number) {
        if (this.isPlaying && this.playIndex == index) {
            this.pause()
            return
        }
        this.playIndex = index
        this._audioContext!!.src = audioUrl
        this._audioContext!!.play()
        this.isPlaying = true
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
                return utsMapOf("formats" to padStyleMapOf(utsMapOf("alignItems" to "center")), "icon-play" to padStyleMapOf(utsMapOf("width" to 60, "height" to 60, "marginTop" to 10, "marginRight" to 10, "marginBottom" to 10, "marginLeft" to 10)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
