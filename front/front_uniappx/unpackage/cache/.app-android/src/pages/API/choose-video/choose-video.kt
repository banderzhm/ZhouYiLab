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
open class GenPagesAPIChooseVideoChooseVideo : BasePage {
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
        onPageHide(fun() {
            console.log("Page Hide", " at pages/API/choose-video/choose-video.uvue:63")
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        val _component_enum_data = resolveEasyComponent("enum-data", GenComponentsEnumDataEnumDataClass)
        val _component_input_data = resolveEasyComponent("input-data", GenComponentsInputDataInputDataClass)
        val _component_boolean_data = resolveEasyComponent("boolean-data", GenComponentsBooleanDataBooleanDataClass)
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                "title"
            )),
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                createElementVNode("video", utsMapOf("class" to "video", "src" to _ctx.src, "controls" to true, "poster" to _ctx.videoCoverImage), null, 8, utsArrayOf(
                    "src",
                    "poster"
                )),
                createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "视频信息")
                )),
                createElementVNode("text", null, toDisplayString(_ctx.videoInfo), 1),
                createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                    createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.chooseVideo), "选取视频", 8, utsArrayOf(
                        "onClick"
                    ))
                )),
                createVNode(_component_enum_data, utsMapOf("title" to "视频来源", "items" to _ctx.sourceTypeItemTypes, "onChange" to _ctx.onSourceTypeChange), null, 8, utsArrayOf(
                    "items",
                    "onChange"
                )),
                createVNode(_component_enum_data, utsMapOf("title" to "屏幕方向", "items" to _ctx.orientationTypeItemTypes, "onChange" to _ctx.onOrientationTypeChange), null, 8, utsArrayOf(
                    "items",
                    "onChange"
                )),
                createVNode(_component_enum_data, utsMapOf("title" to "摄像头", "items" to _ctx.cameraItemTypes, "onChange" to _ctx.onCameraChange), null, 8, utsArrayOf(
                    "items",
                    "onChange"
                )),
                createVNode(_component_enum_data, utsMapOf("title" to "相册模式", "items" to _ctx.albumModeTypes, "onChange" to _ctx.onAlbumModeChange), null, 8, utsArrayOf(
                    "items",
                    "onChange"
                ))
            )),
            createVNode(_component_input_data, utsMapOf("title" to "最长拍摄时间，单位秒", "defaultValue" to "60", "type" to "number", "onConfirm" to _ctx.onMaxDurationConfirm), null, 8, utsArrayOf(
                "onConfirm"
            )),
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                createVNode(_component_boolean_data, utsMapOf("title" to "是否压缩（HamonyOS 不支持，推荐使用 uni.compressVideo 进行压缩）", "defaultValue" to true, "onChange" to _ctx.onCompressedChange), null, 8, utsArrayOf(
                    "onChange"
                ))
            ))
        ), 4)
    }
    open var title: String by `$data`
    open var src: String by `$data`
    open var orientationTypeItemTypes: UTSArray<ItemType> by `$data`
    open var sourceTypeItemTypes: UTSArray<ItemType> by `$data`
    open var sourceTypeItems: UTSArray<UTSArray<Source>> by `$data`
    open var cameraItemTypes: UTSArray<ItemType> by `$data`
    open var albumModeTypes: UTSArray<ItemType> by `$data`
    open var albumModeTypeItems: UTSArray<String> by `$data`
    open var cameraItems: UTSArray<Camera> by `$data`
    open var sourceType: UTSArray<Source> by `$data`
    open var orientationType: String by `$data`
    open var orientationTypeItems: UTSArray<String> by `$data`
    open var compressed: Boolean by `$data`
    open var maxDuration: Number by `$data`
    open var camera: Camera by `$data`
    open var videoInfo: String by `$data`
    open var videoCoverImage: String by `$data`
    open var albumMode: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "chooseVideo", "src" to "", "orientationTypeItemTypes" to utsArrayOf<ItemType>(ItemType(value = 0, name = "竖屏"), ItemType(value = 1, name = "横屏"), ItemType(value = 2, name = "自动")), "sourceTypeItemTypes" to utsArrayOf<ItemType>(ItemType(value = 0, name = "从相册中选择视频"), ItemType(value = 1, name = "拍摄视频"), ItemType(value = 2, name = "从相册中选择视频或拍摄视频")), "sourceTypeItems" to utsArrayOf<UTSArray<Source>>(utsArrayOf(
            "album"
        ), utsArrayOf(
            "camera"
        ), utsArrayOf(
            "album",
            "camera"
        )), "cameraItemTypes" to utsArrayOf<ItemType>(ItemType(value = 0, name = "后置摄像头"), ItemType(value = 1, name = "前置摄像头")), "albumModeTypes" to utsArrayOf<ItemType>(ItemType(value = 0, name = "自定义视频选择器"), ItemType(value = 1, name = "系统视频选择器")), "albumModeTypeItems" to utsArrayOf(
            "custom",
            "system"
        ), "cameraItems" to utsArrayOf<Camera>("back", "front"), "sourceType" to utsArrayOf<Source>("album", "camera"), "orientationType" to "portrait", "orientationTypeItems" to utsArrayOf(
            "portrait",
            "landscape",
            "auto"
        ), "compressed" to true, "maxDuration" to 60, "camera" to "back" as Camera, "videoInfo" to "", "videoCoverImage" to "", "albumMode" to "custom")
    }
    open var chooseVideo = ::gen_chooseVideo_fn
    open fun gen_chooseVideo_fn() {
        uni_chooseVideo(ChooseVideoOptions(sourceType = this.sourceType, compressed = this.compressed, pageOrientation = this.orientationType, maxDuration = this.maxDuration, albumMode = this.albumMode, camera = this.camera, success = fun(res){
            console.log("chooseVideo success", JSON.stringify(res), " at pages/API/choose-video/choose-video.uvue:79")
            this.src = res.tempFilePath
            this.videoInfo = "\u89C6\u9891\u957F\u5EA6: " + res.duration + "s\n\u89C6\u9891\u5927\u5C0F: " + Math.ceil(res.size) + "KB\n\u89C6\u9891\u5BBD\u5EA6: " + res.width + "\n\u89C6\u9891\u9AD8\u5EA6: " + res.height + "\n"
            uni_getVideoInfo(GetVideoInfoOptions(src = res.tempFilePath, success = fun(_res){
                if (_res.thumbTempFilePath != null) {
                    this.videoCoverImage = _res.thumbTempFilePath!!
                }
            }
            ))
        }
        , fail = fun(err){
            uni_showModal(ShowModalOptions(title = "选择视频失败", content = JSON.stringify(err), showCancel = false))
        }
        ))
    }
    open var onOrientationTypeChange = ::gen_onOrientationTypeChange_fn
    open fun gen_onOrientationTypeChange_fn(value: Number) {
        this.orientationType = this.orientationTypeItems[value]
    }
    open var onSourceTypeChange = ::gen_onSourceTypeChange_fn
    open fun gen_onSourceTypeChange_fn(value: Number) {
        this.sourceType = this.sourceTypeItems[value]
    }
    open var onCompressedChange = ::gen_onCompressedChange_fn
    open fun gen_onCompressedChange_fn(value: Boolean) {
        this.compressed = value
    }
    open var onMaxDurationConfirm = ::gen_onMaxDurationConfirm_fn
    open fun gen_onMaxDurationConfirm_fn(value: Number) {
        this.maxDuration = value
    }
    open var onCameraChange = ::gen_onCameraChange_fn
    open fun gen_onCameraChange_fn(value: Number) {
        this.camera = this.cameraItems[value]
    }
    open var onAlbumModeChange = ::gen_onAlbumModeChange_fn
    open fun gen_onAlbumModeChange_fn(value: Number) {
        this.albumMode = this.albumModeTypeItems[value]
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
                return utsMapOf("video" to padStyleMapOf(utsMapOf("alignSelf" to "center", "width" to 300, "height" to 225)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
