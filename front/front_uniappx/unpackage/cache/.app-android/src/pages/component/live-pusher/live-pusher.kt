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
import io.dcloud.uniapp.extapi.createLivePusherContext as uni_createLivePusherContext
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesComponentLivePusherLivePusher : BasePage {
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
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesComponentLivePusherLivePusher) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesComponentLivePusherLivePusher
            val _cache = __ins.renderCache
            val context = ref(null as LivePusherContext?)
            val instance = ref(null as ComponentPublicInstance?)
            val url = ref("rtmp://test")
            val beauty = ref(0)
            val whiteness = ref(0)
            val remoteMirror = ref(false)
            val localMirror = ref("auto")
            val devicePosition = ref("front")
            val mode = ref("SD")
            val autoFocus = ref(true)
            val muted = ref(false)
            val orientation = ref("orientation")
            val enableCamera = ref(true)
            val enableMic = ref(true)
            val audioQuality = ref("high")
            val minBitrate = ref(200)
            val maxBitrate = ref(1000)
            val audioVolumeType = ref("media")
            val aspect = ref("3:4")
            val backgroundMute = ref(false)
            val waitingImage = ref("")
            val zoom = ref(1)
            val videoWidth = ref(0)
            val videoHeight = ref(0)
            val fps = ref(0)
            val isPermissionGranted = ref(false)
            val isUrlSet = ref(true)
            val initState = ref(true)
            val connectedState = ref(false)
            val stopState = ref(false)
            val previewState = ref(true)
            onReady(fun(){
                instance.value = getCurrentInstance()?.proxy
                val permissions = utsArrayOf(
                    "android.permission.CAMERA",
                    "android.permission.RECORD_AUDIO"
                )
                UTSAndroid.requestSystemPermission(UTSAndroid.getUniActivity()!!, permissions, fun(allRight: Boolean, grantedList: UTSArray<String>){
                    if (allRight) {
                        isPermissionGranted.value = true
                    }
                }
                , fun(doNotAskAgain: Boolean, grantedList: UTSArray<String>){})
            }
            )
            val statechange = fun(e: UniLivePusherStatechangeEvent){
                console.log("statechange", e, " at pages/component/live-pusher/live-pusher.uvue:123")
                when (e.detail.code) {
                    1002 -> 
                        {
                            initState.value = false
                            connectedState.value = true
                            stopState.value = false
                        }
                    3004 -> 
                        connectedState.value = false
                }
            }
            val netstatus = fun(e: UniLivePusherNetstatusEvent){
                console.log("netstatus", e, " at pages/component/live-pusher/live-pusher.uvue:136")
            }
            val error = fun(e: UniLivePusherErrorEvent){
                console.log("error", e, " at pages/component/live-pusher/live-pusher.uvue:139")
            }
            val isUrlValid = fun(): Boolean {
                val valid = url.value != "rtmp://test"
                if (!valid) {
                    uni_showToast(ShowToastOptions(title = "请输入推流地址", icon = "none"))
                }
                return valid
            }
            val start = fun(){
                if (!isUrlValid()) {
                    return
                }
                if (context.value == null) {
                    context.value = uni_createLivePusherContext("live-pusher", instance.value)
                }
                context.value?.start(object : UTSJSONObject() {
                    var success = fun(res){
                        console.log("start", JSON.stringify(res), " at pages/component/live-pusher/live-pusher.uvue:158")
                    }
                    var fail = fun(err){
                        console.log("start", JSON.stringify(err), " at pages/component/live-pusher/live-pusher.uvue:161")
                    }
                    var complete = fun(res){
                        console.log("start", JSON.stringify(res), " at pages/component/live-pusher/live-pusher.uvue:164")
                    }
                })
            }
            val pause = fun(){
                if (!isUrlValid()) {
                    return
                }
                if (context.value == null) {
                    context.value = uni_createLivePusherContext("live-pusher", instance.value)
                }
                context.value?.pause(object : UTSJSONObject() {
                    var success = fun(res){
                        console.log("pause", JSON.stringify(res), " at pages/component/live-pusher/live-pusher.uvue:175")
                    }
                    var fail = fun(err){
                        console.log("pause", JSON.stringify(err), " at pages/component/live-pusher/live-pusher.uvue:178")
                    }
                    var complete = fun(res){
                        console.log("pause", JSON.stringify(res), " at pages/component/live-pusher/live-pusher.uvue:181")
                    }
                })
            }
            val resume = fun(){
                if (!isUrlValid()) {
                    return
                }
                if (context.value == null) {
                    context.value = uni_createLivePusherContext("live-pusher", instance.value)
                }
                context.value?.resume(object : UTSJSONObject() {
                    var success = fun(res){
                        console.log("resume", JSON.stringify(res), " at pages/component/live-pusher/live-pusher.uvue:192")
                    }
                    var fail = fun(err){
                        console.log("resume", JSON.stringify(err), " at pages/component/live-pusher/live-pusher.uvue:195")
                    }
                    var complete = fun(res){
                        console.log("resume", JSON.stringify(res), " at pages/component/live-pusher/live-pusher.uvue:198")
                    }
                })
            }
            val stop = fun(){
                if (!isUrlValid()) {
                    return
                }
                stopState.value = true
                if (context.value == null) {
                    context.value = uni_createLivePusherContext("live-pusher", instance.value)
                }
                context.value?.stop(object : UTSJSONObject() {
                    var success = fun(res){
                        console.log("stop", JSON.stringify(res), " at pages/component/live-pusher/live-pusher.uvue:210")
                    }
                    var fail = fun(err){
                        console.log("stop", JSON.stringify(err), " at pages/component/live-pusher/live-pusher.uvue:213")
                    }
                    var complete = fun(res){
                        console.log("stop", JSON.stringify(res), " at pages/component/live-pusher/live-pusher.uvue:216")
                    }
                })
            }
            val switchCamera = fun(){
                if (context.value == null) {
                    context.value = uni_createLivePusherContext("live-pusher", instance.value)
                }
                context.value?.switchCamera(object : UTSJSONObject() {
                    var success = fun(res){
                        console.log("switchCamera", JSON.stringify(res), " at pages/component/live-pusher/live-pusher.uvue:226")
                    }
                    var fail = fun(err){
                        console.log("switchCamera", JSON.stringify(err), " at pages/component/live-pusher/live-pusher.uvue:229")
                    }
                    var complete = fun(res){
                        console.log("switchCamera", JSON.stringify(res), " at pages/component/live-pusher/live-pusher.uvue:232")
                    }
                })
            }
            val snapshot = fun(){
                if (context.value == null) {
                    context.value = uni_createLivePusherContext("live-pusher", instance.value)
                }
                context.value?.snapshot(object : UTSJSONObject() {
                    var success = fun(res){
                        console.log("snapshot", JSON.stringify(res), " at pages/component/live-pusher/live-pusher.uvue:242")
                    }
                    var fail = fun(err){
                        console.log("snapshot", JSON.stringify(err), " at pages/component/live-pusher/live-pusher.uvue:245")
                    }
                    var complete = fun(res){
                        console.log("snapshot", JSON.stringify(res), " at pages/component/live-pusher/live-pusher.uvue:248")
                    }
                })
            }
            val startPreview = fun(){
                if (context.value == null) {
                    context.value = uni_createLivePusherContext("live-pusher", instance.value)
                }
                previewState.value = true
                context.value?.startPreview(object : UTSJSONObject() {
                    var success = fun(res){
                        console.log("startPreview", JSON.stringify(res), " at pages/component/live-pusher/live-pusher.uvue:259")
                    }
                    var fail = fun(err){
                        console.log("startPreview", JSON.stringify(err), " at pages/component/live-pusher/live-pusher.uvue:262")
                    }
                    var complete = fun(res){
                        console.log("startPreview", JSON.stringify(res), " at pages/component/live-pusher/live-pusher.uvue:265")
                    }
                })
            }
            val stopPreview = fun(){
                if (context.value == null) {
                    context.value = uni_createLivePusherContext("live-pusher", instance.value)
                }
                previewState.value = false
                context.value?.stopPreview(object : UTSJSONObject() {
                    var success = fun(res){
                        console.log("stopPreview", JSON.stringify(res), " at pages/component/live-pusher/live-pusher.uvue:276")
                    }
                    var fail = fun(err){
                        console.log("stopPreview", JSON.stringify(err), " at pages/component/live-pusher/live-pusher.uvue:279")
                    }
                    var complete = fun(res){
                        console.log("stopPreview", JSON.stringify(res), " at pages/component/live-pusher/live-pusher.uvue:282")
                    }
                })
            }
            val localMirrorItemTypes = utsArrayOf<ItemType>(ItemType(value = 0, name = "auto(前置摄像头镜像，后置摄像头不镜像)"), ItemType(value = 1, name = "enable(前后置摄像头均镜像)"), ItemType(value = 2, name = "disable(前后置摄像头均不镜像)"))
            val localMirrorItems = utsArrayOf(
                "auto",
                "enable",
                "disable"
            )
            val modeItemTypes = utsArrayOf<ItemType>(ItemType(value = 0, name = "SD(标清)"), ItemType(value = 1, name = "HD(高清)"), ItemType(value = 2, name = "FHD(超清)"))
            val modeItems = utsArrayOf(
                "SD",
                "HD",
                "FHD"
            )
            val orientationItemTypes = utsArrayOf<ItemType>(ItemType(value = 0, name = "vertical"), ItemType(value = 1, name = "horizontal"))
            val orientationItems = utsArrayOf(
                "vertical",
                "horizontal"
            )
            val aspectItemTypes = utsArrayOf<ItemType>(ItemType(value = 0, name = "3:4"), ItemType(value = 1, name = "9:16"))
            val aspectItems = utsArrayOf(
                "3:4",
                "9:16"
            )
            val audioQualityItemTypes = utsArrayOf<ItemType>(ItemType(value = 0, name = "high(高音质(48KHz))"), ItemType(value = 1, name = "low(低音质(16KHz))"))
            val audioQualityItems = utsArrayOf(
                "high",
                "low"
            )
            val devicePositionItemTypes = utsArrayOf<ItemType>(ItemType(value = 0, name = "front"), ItemType(value = 1, name = "back"))
            val devicePositionItems = utsArrayOf(
                "front",
                "back"
            )
            val onUrlComfirm = fun(event: UniInputConfirmEvent){
                var value = event.detail.value
                if (value == "") {
                    return
                }
                context.value = null
                isUrlSet.value = false
                setTimeout(fun(){
                    url.value = value
                    isUrlSet.value = true
                }
                , 200)
                console.log("url ->", value, " at pages/component/live-pusher/live-pusher.uvue:308")
            }
            val onMinBitrateComfirm = fun(event: UniInputConfirmEvent){
                var value = event.detail.value
                if (value == "") {
                    return
                }
                minBitrate.value = parseInt(value)
                console.log("min-bitrate ->", value, " at pages/component/live-pusher/live-pusher.uvue:314")
            }
            val onMaxBitrateComfirm = fun(event: UniInputConfirmEvent){
                var value = event.detail.value
                if (value == "") {
                    return
                }
                maxBitrate.value = parseInt(value)
                console.log("max-bitrate ->", value, " at pages/component/live-pusher/live-pusher.uvue:320")
            }
            val onWaitingImageComfirm = fun(event: UniInputConfirmEvent){
                var value = event.detail.value
                if (value == "") {
                    return
                }
                waitingImage.value = value
                console.log("waiting-image ->", value, " at pages/component/live-pusher/live-pusher.uvue:326")
            }
            val onZoomComfirm = fun(event: UniInputConfirmEvent){
                var value = event.detail.value
                if (value == "") {
                    return
                }
                zoom.value = parseInt(value)
                console.log("zoom ->", value, " at pages/component/live-pusher/live-pusher.uvue:332")
            }
            val onBeautyChange = fun(event: UniSliderChangeEvent){
                beauty.value = event.detail.value
                console.log("beauty ->", beauty.value, " at pages/component/live-pusher/live-pusher.uvue:336")
            }
            val onWhitenessChange = fun(event: UniSliderChangeEvent){
                whiteness.value = event.detail.value
                console.log("whiteness ->", whiteness.value, " at pages/component/live-pusher/live-pusher.uvue:340")
            }
            val onMutedChange = fun(value: Boolean){
                muted.value = value
                console.log("muted ->", muted.value, " at pages/component/live-pusher/live-pusher.uvue:352")
            }
            val onBackgroundMuteChange = fun(value: Boolean){
                backgroundMute.value = value
                console.log("background-mute ->", backgroundMute.value, " at pages/component/live-pusher/live-pusher.uvue:356")
            }
            val onAutoFocusChange = fun(value: Boolean){
                autoFocus.value = value
                console.log("auto-focus ->", backgroundMute.value, " at pages/component/live-pusher/live-pusher.uvue:360")
            }
            val onRemoteMirrorChange = fun(value: Boolean){
                remoteMirror.value = value
                console.log("remote-mirror ->", remoteMirror.value, " at pages/component/live-pusher/live-pusher.uvue:364")
            }
            val onLocalMirrorChange = fun(value: Number){
                localMirror.value = localMirrorItems[value]
                console.log("local-mirror ->", localMirrorItems[value], " at pages/component/live-pusher/live-pusher.uvue:368")
            }
            val onDevicePositionChange = fun(value: Number){
                devicePosition.value = devicePositionItems[value]
                console.log("device-position ->", devicePosition.value, " at pages/component/live-pusher/live-pusher.uvue:372")
            }
            val onModeChange = fun(value: Number){
                mode.value = modeItems[value]
                console.log("mode ->", modeItems[value], " at pages/component/live-pusher/live-pusher.uvue:376")
            }
            val onAspectChange = fun(value: Number){
                aspect.value = aspectItems[value]
                console.log("aspect ->", aspectItems[value], " at pages/component/live-pusher/live-pusher.uvue:380")
            }
            val onOrientationChange = fun(value: Number){
                orientation.value = orientationItems[value]
                console.log("orientation ->", orientation.value, " at pages/component/live-pusher/live-pusher.uvue:384")
            }
            val onAudioQualityChange = fun(value: Number){
                audioQuality.value = audioQualityItems[value]
                console.log("audio-quality ->", audioQuality.value, " at pages/component/live-pusher/live-pusher.uvue:388")
            }
            return fun(): Any? {
                val _component_slider = resolveComponent("slider")
                val _component_boolean_data = resolveEasyComponent("boolean-data", GenComponentsBooleanDataBooleanDataClass)
                val _component_enum_data = resolveEasyComponent("enum-data", GenComponentsEnumDataEnumDataClass)
                return createElementVNode("view", utsMapOf("class" to "uni-flex-item"), utsArrayOf(
                    if (isTrue(unref(isPermissionGranted) && unref(isUrlSet))) {
                        createElementVNode("live-pusher", utsMapOf("key" to 0, "id" to "live-pusher", "class" to "live-pusher", "url" to unref(url), "beauty" to unref(beauty), "whiteness" to unref(whiteness), "remote-mirror" to unref(remoteMirror), "local-mirror" to unref(localMirror), "device-position" to unref(devicePosition), "mode" to unref(mode), "auto-focus" to unref(autoFocus), "muted" to unref(muted), "orientation" to unref(orientation), "enable-camera" to unref(enableCamera), "enable-mic" to unref(enableMic), "audio-quality" to unref(audioQuality), "min-bitrate" to unref(minBitrate), "max-bitrate" to unref(maxBitrate), "audio-volume-type" to unref(audioVolumeType), "aspect" to unref(aspect), "background-mute" to unref(backgroundMute), "waiting-image" to unref(waitingImage), "zoom" to unref(zoom), "onStatechange" to statechange, "onNetstatus" to netstatus, "onError" to error), null, 40, utsArrayOf(
                            "url",
                            "beauty",
                            "whiteness",
                            "remote-mirror",
                            "local-mirror",
                            "device-position",
                            "mode",
                            "auto-focus",
                            "muted",
                            "orientation",
                            "enable-camera",
                            "enable-mic",
                            "audio-quality",
                            "min-bitrate",
                            "max-bitrate",
                            "audio-volume-type",
                            "aspect",
                            "background-mute",
                            "waiting-image",
                            "zoom"
                        ))
                    } else {
                        createCommentVNode("v-if", true)
                    }
                    ,
                    createElementVNode("scroll-view", utsMapOf("class" to "uni-padding-wrap uni-common-mt uni-flex-item"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "uni-title-text"), "API示例")
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "onClick" to start, "disabled" to (unref(connectedState) || !unref(previewState))), "开始推流", 8, utsArrayOf(
                                "disabled"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "onClick" to pause, "disabled" to (!unref(connectedState) || !unref(previewState))), "暂停推流", 8, utsArrayOf(
                                "disabled"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "onClick" to resume, "disabled" to (unref(initState) || unref(connectedState) || unref(stopState) || !unref(previewState))), "恢复推流", 8, utsArrayOf(
                                "disabled"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "onClick" to stop, "disabled" to (!unref(connectedState) || !unref(previewState))), "停止推流", 8, utsArrayOf(
                                "disabled"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "onClick" to switchCamera, "disabled" to (unref(stopState) || !unref(previewState))), "切换前后摄像头", 8, utsArrayOf(
                                "disabled"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "onClick" to snapshot, "disabled" to (unref(stopState) || !unref(previewState))), "快照", 8, utsArrayOf(
                                "disabled"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "onClick" to startPreview, "disabled" to (unref(stopState) || unref(previewState))), "开启摄像头预览", 8, utsArrayOf(
                                "disabled"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "onClick" to stopPreview, "disabled" to (unref(stopState) || !unref(previewState))), "关闭摄像头预览", 8, utsArrayOf(
                                "disabled"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "uni-title-text"), "属性示例")
                        )),
                        createElementVNode("input", utsMapOf("class" to "input margin-10", "type" to "string", "placeholder" to "设置推流地址", "onConfirm" to onUrlComfirm), null, 32),
                        createElementVNode("input", utsMapOf("class" to "input margin-10", "type" to "string", "placeholder" to "设置最小码率", "onConfirm" to onMinBitrateComfirm), null, 32),
                        createElementVNode("input", utsMapOf("class" to "input margin-10", "type" to "string", "placeholder" to "设置最大码率", "onConfirm" to onMaxBitrateComfirm), null, 32),
                        createElementVNode("input", utsMapOf("class" to "input margin-10", "type" to "string", "placeholder" to "设置进入后台时推流的等待画面", "onConfirm" to onWaitingImageComfirm), null, 32),
                        createElementVNode("input", utsMapOf("class" to "input margin-10", "type" to "string", "placeholder" to "设置焦距", "onConfirm" to onZoomComfirm), null, 32),
                        createElementVNode("view", utsMapOf("class" to "margin-10"), utsArrayOf(
                            createElementVNode("text", null, "美颜, 取值范围0-9(iOS取值范围为1), 0表示关闭"),
                            createVNode(_component_slider, utsMapOf("min" to 0, "max" to 9, "onChange" to onBeautyChange))
                        )),
                        createElementVNode("view", utsMapOf("class" to "margin-10"), utsArrayOf(
                            createElementVNode("text", null, "美白, 取值范围0-9(iOS取值范围为1), 0表示关闭"),
                            createVNode(_component_slider, utsMapOf("min" to 0, "max" to 9, "onChange" to onWhitenessChange))
                        )),
                        createVNode(_component_boolean_data, utsMapOf("title" to "设置是否静音", "defaultValue" to unref(muted), "onChange" to onMutedChange), null, 8, utsArrayOf(
                            "defaultValue"
                        )),
                        createVNode(_component_boolean_data, utsMapOf("title" to "设置是否自动聚焦", "defaultValue" to unref(autoFocus), "onChange" to onAutoFocusChange), null, 8, utsArrayOf(
                            "defaultValue"
                        )),
                        createVNode(_component_boolean_data, utsMapOf("title" to "设置进入后台时是否静音", "defaultValue" to unref(backgroundMute), "onChange" to onBackgroundMuteChange), null, 8, utsArrayOf(
                            "defaultValue"
                        )),
                        createVNode(_component_boolean_data, utsMapOf("title" to "设置推流画面是否镜像", "defaultValue" to unref(remoteMirror), "onChange" to onRemoteMirrorChange), null, 8, utsArrayOf(
                            "defaultValue"
                        )),
                        createVNode(_component_enum_data, utsMapOf("title" to "设置本地预览画面是否镜像", "items" to localMirrorItemTypes, "onChange" to onLocalMirrorChange)),
                        createVNode(_component_enum_data, utsMapOf("title" to "设置使用前置或后置摄像头", "items" to devicePositionItemTypes, "onChange" to onDevicePositionChange)),
                        createVNode(_component_enum_data, utsMapOf("title" to "设置推流视频模式", "items" to modeItemTypes, "onChange" to onModeChange)),
                        createVNode(_component_enum_data, utsMapOf("title" to "设置视频宽高比例", "items" to aspectItemTypes, "onChange" to onAspectChange)),
                        createVNode(_component_enum_data, utsMapOf("title" to "设置画面方向", "items" to orientationItemTypes, "onChange" to onOrientationChange)),
                        createVNode(_component_enum_data, utsMapOf("title" to "设置音质", "items" to audioQualityItemTypes, "onChange" to onAudioQualityChange))
                    ))
                ))
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ), utsArrayOf(
                GenApp.styles
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("live-pusher" to padStyleMapOf(utsMapOf("width" to "100%", "height" to "50%")), "input" to padStyleMapOf(utsMapOf("height" to 40, "backgroundImage" to "none", "backgroundColor" to "#FFFFFF", "paddingTop" to 8, "paddingRight" to 13, "paddingBottom" to 8, "paddingLeft" to 13)), "margin-10" to padStyleMapOf(utsMapOf("marginTop" to 10, "marginRight" to 10, "marginBottom" to 10, "marginLeft" to 10)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
