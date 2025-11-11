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
import io.dcloud.uniapp.extapi.compressVideo as uni_compressVideo
import io.dcloud.uniapp.extapi.getVideoInfo as uni_getVideoInfo
import io.dcloud.uniapp.extapi.hideLoading as uni_hideLoading
import io.dcloud.uniapp.extapi.showLoading as uni_showLoading
import io.dcloud.uniapp.extapi.showModal as uni_showModal
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesAPICompressVideoCompressVideo : BasePage {
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
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        val _component_enum_data = resolveEasyComponent("enum-data", GenComponentsEnumDataEnumDataClass)
        val _component_slider = resolveComponent("slider")
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                "title"
            )),
            createElementVNode("view", null, utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                    createElementVNode("video", utsMapOf("class" to "video", "src" to _ctx.beforeCompressPath, "controls" to true, "poster" to _ctx.beforeCoverImagePath), null, 8, utsArrayOf(
                        "src",
                        "poster"
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "压缩前视频信息")
                    )),
                    createElementVNode("text", null, toDisplayString(_ctx.beforeCompressVideoInfo), 1),
                    createElementVNode("video", utsMapOf("class" to "video", "src" to _ctx.afterCompressPath, "controls" to true, "poster" to _ctx.afterCoverImagePath), null, 8, utsArrayOf(
                        "src",
                        "poster"
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "压缩后视频信息")
                    )),
                    createElementVNode("text", null, toDisplayString(_ctx.afterCompressVideoInfo), 1),
                    createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                        createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.chooseVideo), "从相册中选取待压缩的视频", 8, utsArrayOf(
                            "onClick"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                        createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.compressVideo), "压缩视频", 8, utsArrayOf(
                            "onClick"
                        ))
                    )),
                    createVNode(_component_enum_data, utsMapOf("title" to "压缩质量", "items" to _ctx.qualityItemTypes, "onChange" to _ctx.onQualityChange), null, 8, utsArrayOf(
                        "items",
                        "onChange"
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-common-mt"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-title uni-title-text"), "相对于原视频的分辨率比例，取值范围(0, 1]"),
                        createVNode(_component_slider, utsMapOf("min" to 0.1, "max" to 1, "step" to 0.1, "show-value" to true, "onChange" to _ctx.onResolutionChange), null, 8, utsArrayOf(
                            "onChange"
                        ))
                    ))
                ))
            ))
        ), 4)
    }
    open var title: String by `$data`
    open var beforeCompressVideoInfo: String by `$data`
    open var afterCompressVideoInfo: String by `$data`
    open var beforeCompressPath: String by `$data`
    open var afterCompressPath: String by `$data`
    open var beforeCoverImagePath: String by `$data`
    open var afterCoverImagePath: String by `$data`
    open var quality: String? by `$data`
    open var bitrate: Number? by `$data`
    open var fps: Number? by `$data`
    open var resolution: Number? by `$data`
    open var qualityItemTypes: UTSArray<ItemType> by `$data`
    open var qualityItems: UTSArray<String> by `$data`
    open var videoInfoForTest: VideoInfoForTest? by `$data`
    open var videoSrcForTest: String by `$data`
    open var videoSrcForTestWidth: Number by `$data`
    open var videoSrcForTestHeight: Number by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "compressVideo", "beforeCompressVideoInfo" to "", "afterCompressVideoInfo" to "", "beforeCompressPath" to "", "afterCompressPath" to "", "beforeCoverImagePath" to "", "afterCoverImagePath" to "", "quality" to null as String?, "bitrate" to null as Number?, "fps" to null as Number?, "resolution" to null as Number?, "qualityItemTypes" to utsArrayOf<ItemType>(ItemType(value = 0, name = "low(低)"), ItemType(value = 1, name = "medium(中)"), ItemType(value = 2, name = "high(高)")), "qualityItems" to utsArrayOf(
            "low",
            "medium",
            "high"
        ), "videoInfoForTest" to null as VideoInfoForTest?, "videoSrcForTest" to "/static/test-video/10second-demo.mp4", "videoSrcForTestWidth" to 0, "videoSrcForTestHeight" to 0)
    }
    open var compressVideo = ::gen_compressVideo_fn
    open fun gen_compressVideo_fn() {
        if (this.beforeCompressPath == "") {
            uni_showToast(ShowToastOptions(title = "请先选择视频", icon = "error"))
            return
        }
        uni_showLoading(ShowLoadingOptions(title = "视频压缩中"))
        uni_compressVideo(CompressVideoOptions(src = this.beforeCompressPath, quality = this.quality, resolution = this.resolution, success = fun(res){
            console.log("compressVideo success", JSON.stringify(res), " at pages/API/compress-video/compress-video.uvue:83")
            this.afterCompressPath = res.tempFilePath
            uni_showToast(ShowToastOptions(title = "压缩成功", icon = null))
            uni_getVideoInfo(GetVideoInfoOptions(src = res.tempFilePath, success = fun(_res){
                this.afterCompressVideoInfo = "\u89C6\u9891\u753B\u9762\u65B9\u5411: " + _res.orientation + "\n\u89C6\u9891\u683C\u5F0F: " + _res.type + "\n\u89C6\u9891\u957F\u5EA6: " + _res.duration + "s\n\u89C6\u9891\u5927\u5C0F: " + _res.size + "KB\n\u89C6\u9891\u5BBD\u5EA6: " + _res.width + "\n\u89C6\u9891\u9AD8\u5EA6: " + _res.height + "\n\u89C6\u9891\u5E27\u7387: " + _res.fps + "fps\n\u89C6\u9891\u7801\u7387: " + _res.bitrate + "kbps"
                this.afterCompressVideoInfo = this.afterCompressVideoInfo + ("\n\u89C6\u9891\u5B57\u8282\u5927\u5C0F: " + _res.byteSize + "B\n\u89C6\u9891\u9996\u5E27\u56FE\u7247\u8DEF\u5F84: " + _res.thumbTempFilePath)
                if (_res.thumbTempFilePath != null) {
                    this.afterCoverImagePath = _res.thumbTempFilePath!!
                }
            }
            ))
        }
        , fail = fun(err){
            uni_showModal(ShowModalOptions(title = "压缩视频失败", content = JSON.stringify(err), showCancel = false))
        }
        , complete = fun(_){
            uni_hideLoading()
        }
        ))
    }
    open var chooseVideo = ::gen_chooseVideo_fn
    open fun gen_chooseVideo_fn() {
        uni_chooseVideo(ChooseVideoOptions(sourceType = utsArrayOf(
            "album"
        ), compressed = false, success = fun(res){
            this.beforeCompressPath = res.tempFilePath
            uni_getVideoInfo(GetVideoInfoOptions(src = res.tempFilePath, success = fun(_res){
                this.beforeCompressVideoInfo = "\u89C6\u9891\u753B\u9762\u65B9\u5411: " + _res.orientation + "\n\u89C6\u9891\u683C\u5F0F: " + _res.type + "\n\u89C6\u9891\u957F\u5EA6: " + _res.duration + "s\n\u89C6\u9891\u5927\u5C0F: " + _res.size + "KB\n\u89C6\u9891\u5BBD\u5EA6: " + _res.width + "\n\u89C6\u9891\u9AD8\u5EA6: " + _res.height + "\n\u89C6\u9891\u5E27\u7387: " + _res.fps + "fps\n\u89C6\u9891\u7801\u7387: " + _res.bitrate + "kbps"
                this.beforeCompressVideoInfo = this.beforeCompressVideoInfo + ("\n\u89C6\u9891\u5B57\u8282\u5927\u5C0F: " + _res.byteSize + "B\n\u89C6\u9891\u9996\u5E27\u56FE\u7247\u8DEF\u5F84: " + _res.thumbTempFilePath)
                if (_res.thumbTempFilePath != null) {
                    this.beforeCoverImagePath = _res.thumbTempFilePath!!
                }
            }
            ))
        }
        ))
    }
    open var onQualityChange = ::gen_onQualityChange_fn
    open fun gen_onQualityChange_fn(value: Number) {
        this.quality = this.qualityItems[value]
    }
    open var onResolutionChange = ::gen_onResolutionChange_fn
    open fun gen_onResolutionChange_fn(event: UniSliderChangeEvent) {
        this.resolution = event.detail.value
    }
    open var testCompressVideo = ::gen_testCompressVideo_fn
    open fun gen_testCompressVideo_fn() {
        var beforeCompressSize: Number
        var afterComoressSize: Number
        uni_compressVideo(CompressVideoOptions(src = this.videoSrcForTest, quality = "medium", success = fun(res){
            uni_getVideoInfo(GetVideoInfoOptions(src = this.videoSrcForTest, success = fun(_res){
                beforeCompressSize = Math.trunc(_res.size)
                this.videoSrcForTestWidth = _res.width
                this.videoSrcForTestHeight = _res.height
                uni_getVideoInfo(GetVideoInfoOptions(src = res.tempFilePath, success = fun(__res){
                    afterComoressSize = Math.trunc(__res.size)
                    this.videoInfoForTest = VideoInfoForTest(width = __res.width, height = __res.height, isSizeReduce = afterComoressSize < beforeCompressSize)
                }
                , fail = fun(err) {
                    console.log(">>>>>> 压缩失败", err.errMsg, " at pages/API/compress-video/compress-video.uvue:164")
                }
                ))
            }
            ))
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
                return utsMapOf("video" to padStyleMapOf(utsMapOf("alignSelf" to "center")), "image-container" to padStyleMapOf(utsMapOf("flexDirection" to "row")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
