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
import io.dcloud.uniapp.extapi.chooseVideo as uni_chooseVideo
import io.dcloud.uniapp.extapi.getVideoInfo as uni_getVideoInfo
import io.dcloud.uniapp.extapi.showModal as uni_showModal
open class GenPagesAPIGetVideoInfoGetVideoInfo : BasePage {
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
            uni_getVideoInfo(GetVideoInfoOptions(src = this.relativeVideoPath, success = fun(res){
                console.log("getVideoInfo success", JSON.stringify(res), " at pages/API/get-video-info/get-video-info.uvue:50")
                this.relativeVideoInfo = "\u89C6\u9891\u753B\u9762\u65B9\u5411: " + res.orientation + "\n\u89C6\u9891\u683C\u5F0F: " + res.type + "\n\u89C6\u9891\u957F\u5EA6: " + res.duration + "s\n\u89C6\u9891\u5927\u5C0F: " + res.size + "KB\n\u89C6\u9891\u5BBD\u5EA6: " + res.width + "\n\u89C6\u9891\u9AD8\u5EA6: " + res.height + "\n\u89C6\u9891\u5E27\u7387: " + res.fps + "fps\n\u89C6\u9891\u7801\u7387: " + res.bitrate + "kbps"
                this.relativeVideoInfo = this.relativeVideoInfo + ("\n\u89C6\u9891\u5B57\u8282\u5927\u5C0F: " + res.byteSize + "B\n\u89C6\u9891\u9996\u5E27\u56FE\u7247\u8DEF\u5F84: " + res.thumbTempFilePath)
                if (res.thumbTempFilePath != null) {
                    this.relativeCoverImagePath = res.thumbTempFilePath!!
                }
            }
            , fail = fun(err){
                uni_showModal(ShowModalOptions(title = "获取视频信息失败", content = JSON.stringify(err), showCancel = false))
            }
            ))
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                "title"
            )),
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "获取本地绝对路径视频信息")
                )),
                createElementVNode("video", utsMapOf("class" to "video", "src" to _ctx.absoluteVideoPath, "controls" to true, "poster" to _ctx.absoluteCoverImagePath), null, 8, utsArrayOf(
                    "src",
                    "poster"
                )),
                createElementVNode("text", utsMapOf("class" to "margin-top-10"), toDisplayString(_ctx.absoluteVideoInfo), 1),
                createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                    createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.chooseVideo), "拍摄视频或从相册中选择视频", 8, utsArrayOf(
                        "onClick"
                    ))
                ))
            )),
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "获取本地相对路径视频信息")
                )),
                createElementVNode("video", utsMapOf("class" to "video", "src" to _ctx.relativeVideoPath, "controls" to true, "poster" to _ctx.relativeCoverImagePath), null, 8, utsArrayOf(
                    "src",
                    "poster"
                )),
                createElementVNode("text", utsMapOf("class" to "margin-top-10"), toDisplayString(_ctx.relativeVideoInfo), 1)
            ))
        ), 4)
    }
    open var title: String by `$data`
    open var relativeVideoPath: String by `$data`
    open var relativeVideoInfo: String by `$data`
    open var relativeCoverImagePath: String by `$data`
    open var absoluteVideoPath: String by `$data`
    open var absoluteVideoInfo: String by `$data`
    open var absoluteCoverImagePath: String by `$data`
    open var videoInfoForTest: UTSJSONObject? by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "getVideoInfo", "relativeVideoPath" to "/static/test-video/10second-demo.mp4", "relativeVideoInfo" to "", "relativeCoverImagePath" to "", "absoluteVideoPath" to "", "absoluteVideoInfo" to "", "absoluteCoverImagePath" to "", "videoInfoForTest" to null as UTSJSONObject?)
    }
    open var chooseVideo = ::gen_chooseVideo_fn
    open fun gen_chooseVideo_fn() {
        uni_chooseVideo(ChooseVideoOptions(compressed = false, success = fun(res){
            this.absoluteVideoPath = res.tempFilePath
            uni_getVideoInfo(GetVideoInfoOptions(src = res.tempFilePath, success = fun(_res){
                console.log("getVideoInfo success", JSON.stringify(_res), " at pages/API/get-video-info/get-video-info.uvue:79")
                this.absoluteVideoInfo = "\u89C6\u9891\u753B\u9762\u65B9\u5411: " + _res.orientation + "\n\u89C6\u9891\u683C\u5F0F: " + _res.type + "\n\u89C6\u9891\u957F\u5EA6: " + _res.duration + "s\n\u89C6\u9891\u5927\u5C0F: " + _res.size + "KB\n\u89C6\u9891\u5BBD\u5EA6: " + _res.width + "\n\u89C6\u9891\u9AD8\u5EA6: " + _res.height + "\n\u89C6\u9891\u5E27\u7387: " + _res.fps + "fps\n\u89C6\u9891\u7801\u7387: " + _res.bitrate + "kbps"
                this.absoluteVideoInfo = this.absoluteVideoInfo + ("\n\u89C6\u9891\u5B57\u8282\u5927\u5C0F: " + _res.byteSize + "B\n\u89C6\u9891\u9996\u5E27\u56FE\u7247\u8DEF\u5F84: " + _res.thumbTempFilePath)
                if (_res.thumbTempFilePath != null) {
                    this.absoluteCoverImagePath = _res.thumbTempFilePath!!
                }
            }
            , fail = fun(err){
                uni_showModal(ShowModalOptions(title = "获取视频信息失败", content = JSON.stringify(err), showCancel = false))
            }
            ))
        }
        ))
    }
    open var testGetVideoInfo = ::gen_testGetVideoInfo_fn
    open fun gen_testGetVideoInfo_fn() {
        uni_getVideoInfo(GetVideoInfoOptions(src = "/static/test-video/10second-demo.mp4", success = fun(res){
            this.videoInfoForTest = object : UTSJSONObject() {
                var orientation = res.orientation
                var type = res.type
                var duration = Math.trunc(res.duration)
                var size = res.size
                var width = res.width
                var height = res.height
                var fps = res.fps
                var bitrate = res.bitrate
            }
        }
        , fail = fun(_){
            this.videoInfoForTest = null
        }
        ))
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
                return utsMapOf("video" to padStyleMapOf(utsMapOf("width" to "100%")), "margin-top-10" to padStyleMapOf(utsMapOf("marginTop" to 10)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
