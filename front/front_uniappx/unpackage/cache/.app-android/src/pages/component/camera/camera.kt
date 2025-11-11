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
import io.dcloud.uniapp.extapi.createCameraContext as uni_createCameraContext
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo
open class GenPagesComponentCameraCamera : BasePage {
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
        onLoad(fun(_: OnLoadOptions) {}, __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_camera = resolveComponent("camera", true)
        val _component_slider = resolveComponent("slider")
        val _component_radio = resolveComponent("radio")
        val _component_radio_group = resolveComponent("radio-group")
        return createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createVNode(_component_camera, utsMapOf("style" to normalizeStyle(utsMapOf("width" to "100%", "height" to "300px")), "resolution" to "medium", "device-position" to _ctx.devicePosition, "photo-resolution" to "high", "flash" to _ctx.flash, "frame-size" to _ctx.frameSize, "onStop" to _ctx.handleStop, "onError" to _ctx.handleError, "onInitdone" to _ctx.handleInitDone), null, 8, utsArrayOf(
                "style",
                "device-position",
                "flash",
                "frame-size",
                "onStop",
                "onError",
                "onInitdone"
            )),
            createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("button", utsMapOf("type" to "default", "onClick" to _ctx.handleScanCode), "扫码", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("type" to "default", "onClick" to _ctx.switchDevicePosition), "切换前后摄像头", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("type" to "default", "onClick" to _ctx.switchFlash), "闪光灯", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("type" to "default", "onClick" to _ctx.setOnFrameListener), "设置帧数据监听", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("type" to "default", "onClick" to _ctx.startFrameListener), "开始捕捉帧数据", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("type" to "default", "onClick" to _ctx.stopFrameListener), "停止捕捉帧数据", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("view", null, utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "uni-title-text"), "设置预览缩放")
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-camera-wrapper"), utsArrayOf(
                            createVNode(_component_slider, utsMapOf("class" to "uni-camera-test-host", "disabled" to (_ctx.maxZoom == 0), "show-value" to true, "min" to 1, "max" to _ctx.maxZoom, "value" to 1, "onChange" to _ctx.zoomSliderChange), null, 8, utsArrayOf(
                                "disabled",
                                "max",
                                "onChange"
                            ))
                        ))
                    )),
                    createElementVNode("view", null, utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "uni-title-text"), "拍摄照片示例"),
                            createElementVNode("button", utsMapOf("type" to "default", "onClick" to _ctx.handleTakePhoto), "拍摄照片", 8, utsArrayOf(
                                "onClick"
                            )),
                            createVNode(_component_radio_group, utsMapOf("style" to normalizeStyle(utsMapOf("flex-direction" to "row")), "name" to "成像质量", "onChange" to _ctx.takePhotoQualityChange), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                return utsArrayOf(
                                    createVNode(_component_radio, utsMapOf("value" to "normal", "checked" to true), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                        return utsArrayOf(
                                            "普通质量"
                                        )
                                    }
                                    ), "_" to 1)),
                                    createVNode(_component_radio, utsMapOf("value" to "low"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                        return utsArrayOf(
                                            "低质量"
                                        )
                                    }
                                    ), "_" to 1)),
                                    createVNode(_component_radio, utsMapOf("value" to "high"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                        return utsArrayOf(
                                            "高质量"
                                        )
                                    }
                                    ), "_" to 1)),
                                    createVNode(_component_radio, utsMapOf("value" to "original"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                        return utsArrayOf(
                                            "原图"
                                        )
                                    }
                                    ), "_" to 1))
                                )
                            }
                            ), "_" to 1), 8, utsArrayOf(
                                "style",
                                "onChange"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-camera-wrapper"), utsArrayOf(
                            if (_ctx.imageSrc != "") {
                                createElementVNode("image", utsMapOf("key" to 0, "class" to "uni-camera-test-host-without-flex", "style" to normalizeStyle(utsMapOf("width" to "150px", "height" to "150px")), "src" to _ctx.imageSrc), null, 12, utsArrayOf(
                                    "src"
                                ))
                            } else {
                                createCommentVNode("v-if", true)
                            }
                        ))
                    )),
                    createElementVNode("view", null, utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "uni-title-text"), "录制视频示例"),
                            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex-direction" to "row", "margin-top" to "8px"))), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "uni-title-size"), "录制时长："),
                                createElementVNode("input", utsMapOf("class" to "uni-title-size", "style" to normalizeStyle(utsMapOf("width" to "50px", "margin-left" to "10px", "border" to "0.5px solid grey", "text-align" to "right")), "type" to "number", "onInput" to _ctx.timeoutInput, "value" to _ctx.timeout), null, 44, utsArrayOf(
                                    "onInput",
                                    "value"
                                )),
                                createElementVNode("text", utsMapOf("class" to "uni-title-size", "style" to normalizeStyle(utsMapOf("margin-left" to "8px"))), "秒", 4)
                            ), 4),
                            createElementVNode("button", utsMapOf("type" to "default", "style" to normalizeStyle(utsMapOf("font-family" to "monospace", "margin-top" to "8px")), "onClick" to _ctx.startRecord, "disabled" to _ctx.startRecordStatus), toDisplayString(if (_ctx.startRecordStatus) {
                                "" + _ctx.remain + "\u79D2"
                            } else {
                                "录制视频"
                            }
                            ), 13, utsArrayOf(
                                "onClick",
                                "disabled"
                            )),
                            createElementVNode("button", utsMapOf("type" to "default", "onClick" to _ctx.stopRecord), "停止录制", 8, utsArrayOf(
                                "onClick"
                            )),
                            createVNode(_component_radio_group, utsMapOf("style" to normalizeStyle(utsMapOf("flex-direction" to "row", "margin-top" to "8px")), "name" to "是否压缩", "onChange" to _ctx.startRecordCompressChange), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                return utsArrayOf(
                                    createVNode(_component_radio, utsMapOf("value" to "0", "checked" to true), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                        return utsArrayOf(
                                            "未启动视频压缩"
                                        )
                                    }
                                    ), "_" to 1)),
                                    createVNode(_component_radio, utsMapOf("value" to "1"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                        return utsArrayOf(
                                            "启动视频压缩"
                                        )
                                    }
                                    ), "_" to 1))
                                )
                            }
                            ), "_" to 1), 8, utsArrayOf(
                                "style",
                                "onChange"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-camera-wrapper"), utsArrayOf(
                            if (_ctx.videoSrc != "") {
                                createElementVNode("video", utsMapOf("key" to 0, "class" to "uni-camera-test-host-without-flex", "style" to normalizeStyle(utsMapOf("width" to "300px", "height" to "300px")), "src" to _ctx.videoSrc, "controls" to true), null, 12, utsArrayOf(
                                    "src"
                                ))
                            } else {
                                createCommentVNode("v-if", true)
                            }
                        ))
                    ))
                ))
            ), 4)
        ), 4)
    }
    open var devicePosition: String by `$data`
    open var flash: String by `$data`
    open var frameSize: String by `$data`
    open var listener: CameraContextCameraFrameListener? by `$data`
    open var maxZoom: Number by `$data`
    open var imageSrc: String by `$data`
    open var quality: String by `$data`
    open var timeout: Number by `$data`
    open var compressed: Boolean by `$data`
    open var videoSrc: String by `$data`
    open var startRecordStatus: Boolean by `$data`
    open var remain: Number by `$data`
    open var intervalId: Number by `$data`
    open var timeoutStr: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("devicePosition" to "back", "flash" to "off", "frameSize" to "medium", "listener" to null as CameraContextCameraFrameListener?, "maxZoom" to 0, "imageSrc" to "", "quality" to "normal", "timeout" to 30, "compressed" to false, "videoSrc" to "", "startRecordStatus" to false, "remain" to 0, "intervalId" to -1, "timeoutStr" to "30")
    }
    open var handleScanCode = ::gen_handleScanCode_fn
    open fun gen_handleScanCode_fn() {
        uni_navigateTo(NavigateToOptions(url = "/pages/component/camera/camera-scan-code"))
    }
    open var switchDevicePosition = ::gen_switchDevicePosition_fn
    open fun gen_switchDevicePosition_fn() {
        this.devicePosition = if (this.devicePosition == "back") {
            "front"
        } else {
            "back"
        }
    }
    open var switchFlash = ::gen_switchFlash_fn
    open fun gen_switchFlash_fn() {
        this.flash = if (this.flash == "torch") {
            "off"
        } else {
            "torch"
        }
    }
    open var handleStop = ::gen_handleStop_fn
    open fun gen_handleStop_fn(e: UniCameraStopEvent) {
        console.log("stop", e.detail, " at pages/component/camera/camera.uvue:111")
    }
    open var handleError = ::gen_handleError_fn
    open fun gen_handleError_fn(e: UniCameraErrorEvent) {
        console.log("error", e.detail, " at pages/component/camera/camera.uvue:114")
    }
    open var handleInitDone = ::gen_handleInitDone_fn
    open fun gen_handleInitDone_fn(e: UniCameraInitDoneEvent) {
        console.log("initdone", e.detail, " at pages/component/camera/camera.uvue:117")
        this.maxZoom = e.detail.maxZoom ?: 0
    }
    open var zoomSliderChange = ::gen_zoomSliderChange_fn
    open fun gen_zoomSliderChange_fn(event: UniSliderChangeEvent) {
        val value = event.detail.value
        val context = uni_createCameraContext()
        context?.setZoom(CameraContextSetZoomOptions(zoom = value, success = fun(e: Any){
            console.log(e, " at pages/component/camera/camera.uvue:126")
        }
        ))
    }
    open var handleTakePhoto = ::gen_handleTakePhoto_fn
    open fun gen_handleTakePhoto_fn() {
        val context = uni_createCameraContext()
        context?.takePhoto(CameraContextTakePhotoOptions(quality = this.quality, selfieMirror = false, success = fun(res: CameraContextTakePhotoResult){
            console.log("res.tempImagePath", res.tempImagePath, " at pages/component/camera/camera.uvue:136")
            this.imageSrc = res.tempImagePath ?: ""
        }
        , fail = fun(e: Any){
            console.log("take photo", e, " at pages/component/camera/camera.uvue:140")
        }
        ))
    }
    open var takePhotoQualityChange = ::gen_takePhotoQualityChange_fn
    open fun gen_takePhotoQualityChange_fn(event: UniRadioGroupChangeEvent) {
        this.quality = event.detail.value
        console.log("quality", this.quality, " at pages/component/camera/camera.uvue:146")
    }
    open var setOnFrameListener = ::gen_setOnFrameListener_fn
    open fun gen_setOnFrameListener_fn() {
        val context = uni_createCameraContext()
        this.listener = context?.onCameraFrame(fun(frame: CameraContextOnCameraFrame){
            console.log("OnFrame :", frame, " at pages/component/camera/camera.uvue:152")
        }
        )
    }
    open var startFrameListener = ::gen_startFrameListener_fn
    open fun gen_startFrameListener_fn() {
        this.listener?.start(CameraContextCameraFrameListenerStartOptions(success = fun(res: Any){
            console.log("startFrameListener success", res, " at pages/component/camera/camera.uvue:158")
        }
        ))
    }
    open var stopFrameListener = ::gen_stopFrameListener_fn
    open fun gen_stopFrameListener_fn() {
        this.listener?.stop(CameraContextCameraFrameListenerStopOptions(success = fun(res: Any){
            console.log("stopFrameListener success", res, " at pages/component/camera/camera.uvue:166")
        }
        ))
    }
    open var startRecord = ::gen_startRecord_fn
    open fun gen_startRecord_fn() {
        val context = uni_createCameraContext()
        var timeout = this.getTimeout()
        this.timeout = timeout
        context?.startRecord(CameraContextStartRecordOptions(timeout = timeout, selfieMirror = false, timeoutCallback = fun(res: Any){
            console.log("timeoutCallback", res, " at pages/component/camera/camera.uvue:178")
            this.startRecordStatus = false
            if (UTSAndroid.`typeof`(res) != "string") {
                val result = res as CameraContextStartRecordTimeoutResult
                this.videoSrc = result.tempVideoPath ?: ""
            }
            clearInterval(this.intervalId)
        }
        , success = fun(res: Any){
            this.startRecordStatus = true
            console.log("start record success", res, " at pages/component/camera/camera.uvue:188")
            this.remain = timeout
            this.intervalId = setInterval(fun(){
                if (this.remain <= 0) {
                    clearInterval(this.intervalId)
                } else {
                    this.remain--
                }
            }
            , 1000)
        }
        , fail = fun(res: Any){
            console.log("start record fail", res, " at pages/component/camera/camera.uvue:199")
            this.startRecordStatus = false
            this.remain = 0
            clearInterval(this.intervalId)
        }
        ))
    }
    open var stopRecord = ::gen_stopRecord_fn
    open fun gen_stopRecord_fn() {
        this.startRecordStatus = false
        val context = uni_createCameraContext()
        context?.stopRecord(CameraContextStopRecordOptions(compressed = this.compressed, success = fun(res: CameraContextStopRecordResult){
            console.log("stop record success", res, " at pages/component/camera/camera.uvue:212")
            this.videoSrc = res.tempVideoPath ?: ""
        }
        , fail = fun(res: Any){
            console.log("stop record fail", res, " at pages/component/camera/camera.uvue:216")
        }
        ))
        clearInterval(this.intervalId)
        this.remain = 0
    }
    open var startRecordCompressChange = ::gen_startRecordCompressChange_fn
    open fun gen_startRecordCompressChange_fn(event: UniRadioGroupChangeEvent) {
        this.compressed = parseInt(event.detail.value) == 1
    }
    open var timeoutInput = ::gen_timeoutInput_fn
    open fun gen_timeoutInput_fn(event: UniInputEvent) {
        this.timeoutStr = event.detail.value
    }
    open var getTimeout = ::gen_getTimeout_fn
    open fun gen_getTimeout_fn(): Number {
        var value = parseInt(this.timeoutStr)
        if (UTSNumber.isNaN(value)) {
            return 30
        } else {
            if (value < 1) {
                return 1
            } else if (value > 300) {
                return 300
            } else {
                return value
            }
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
                return utsMapOf("uni-title" to padStyleMapOf(utsMapOf("paddingTop" to 10, "paddingRight" to 0, "paddingBottom" to 10, "paddingLeft" to 0)), "uni-title-text" to padStyleMapOf(utsMapOf("fontSize" to 15, "fontWeight" to "bold")), "uni-camera-wrapper" to padStyleMapOf(utsMapOf("display" to "flex", "paddingTop" to 8, "paddingRight" to 13, "paddingBottom" to 8, "paddingLeft" to 13, "marginTop" to 5, "marginRight" to 0, "marginBottom" to 5, "marginLeft" to 0, "flexDirection" to "row", "flexWrap" to "nowrap", "backgroundColor" to "#ffffff")), "uni-camera-test-host" to padStyleMapOf(utsMapOf("height" to 28, "paddingTop" to 0, "paddingRight" to 0, "paddingBottom" to 0, "paddingLeft" to 0, "flex" to 1, "backgroundColor" to "#ffffff")), "uni-camera-test-host-without-flex" to padStyleMapOf(utsMapOf("height" to 28, "paddingTop" to 0, "paddingRight" to 0, "paddingBottom" to 0, "paddingLeft" to 0, "backgroundColor" to "#ffffff")), "uni-title-size" to padStyleMapOf(utsMapOf("fontSize" to 22)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
