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
import io.dcloud.uniapp.extapi.createLivePlayerContext as uni_createLivePlayerContext
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesComponentLivePlayerLivePlayer : BasePage {
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
        var setup: (__props: GenPagesComponentLivePlayerLivePlayer) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesComponentLivePlayerLivePlayer
            val _cache = __ins.renderCache
            val context = ref(null as LivePlayerContext?)
            val src = ref("")
            val autoplay = ref(false)
            val muted = ref(false)
            val objectFit = ref("contain")
            val backgroundMute = ref(false)
            val soundMode = ref("speaker")
            val orientation = ref("vertical")
            val initState = ref(true)
            val playState = ref(false)
            val stopState = ref(false)
            onReady(fun(){
                context.value = uni_createLivePlayerContext("live-player", getCurrentInstance()!!.proxy)
            }
            )
            val statechange = fun(e: UniLivePlayerStatechangeEvent){
                console.log("statechange", e, " at pages/component/live-player/live-player.uvue:66")
                when (e.detail.code) {
                    10004 -> 
                        {
                            initState.value = false
                            playState.value = true
                            stopState.value = false
                        }
                    10009 -> 
                        {
                            stopState.value = true
                            playState.value = false
                        }
                    10006, 10007, 10010, 10011 -> 
                        playState.value = false
                }
            }
            val fullscreenchange = fun(e: UniLivePlayerFullscreenchangeEvent){
                console.log("fullscreenchange", e, " at pages/component/live-player/live-player.uvue:84")
            }
            val error = fun(e: UniLivePlayerErrorEvent){
                console.log("error", e, " at pages/component/live-player/live-player.uvue:87")
            }
            val isSrcValid = fun(): Boolean {
                val length = src.value.length
                if (length <= 0) {
                    uni_showToast(ShowToastOptions(title = "请输入播放地址", icon = "none"))
                }
                return length > 0
            }
            val play = fun(){
                if (!isSrcValid()) {
                    return
                }
                context.value?.play(object : UTSJSONObject() {
                    var success = fun(res){
                        console.log("play", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:103")
                    }
                    var fail = fun(err){
                        console.log("play", JSON.stringify(err), " at pages/component/live-player/live-player.uvue:106")
                    }
                    var complete = fun(res){
                        console.log("play", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:109")
                    }
                })
            }
            val pause = fun(){
                if (!isSrcValid()) {
                    return
                }
                context.value?.pause(object : UTSJSONObject() {
                    var success = fun(res){
                        console.log("pause", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:117")
                    }
                    var fail = fun(err){
                        console.log("pause", JSON.stringify(err), " at pages/component/live-player/live-player.uvue:120")
                    }
                    var complete = fun(res){
                        console.log("pause", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:123")
                    }
                })
            }
            val resume = fun(){
                if (!isSrcValid()) {
                    return
                }
                context.value?.resume(object : UTSJSONObject() {
                    var success = fun(res){
                        console.log("resume", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:131")
                    }
                    var fail = fun(err){
                        console.log("resume", JSON.stringify(err), " at pages/component/live-player/live-player.uvue:134")
                    }
                    var complete = fun(res){
                        console.log("resume", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:137")
                    }
                })
            }
            val stop = fun(){
                if (!isSrcValid()) {
                    return
                }
                context.value?.stop(object : UTSJSONObject() {
                    var success = fun(res){
                        console.log("stop", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:145")
                    }
                    var fail = fun(err){
                        console.log("stop", JSON.stringify(err), " at pages/component/live-player/live-player.uvue:148")
                    }
                    var complete = fun(res){
                        console.log("stop", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:151")
                    }
                })
            }
            val mute = fun(){
                if (!isSrcValid()) {
                    return
                }
                context.value?.mute(object : UTSJSONObject() {
                    var success = fun(res){
                        console.log("mute", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:159")
                    }
                    var fail = fun(err){
                        console.log("mute", JSON.stringify(err), " at pages/component/live-player/live-player.uvue:162")
                    }
                    var complete = fun(res){
                        console.log("mute", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:165")
                    }
                })
            }
            val requestFullScreen = fun(){
                if (!isSrcValid()) {
                    return
                }
                context.value?.requestFullScreen(object : UTSJSONObject() {
                    var success = fun(res){
                        console.log("requestFullScreen", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:173")
                    }
                    var fail = fun(err){
                        console.log("requestFullScreen", JSON.stringify(err), " at pages/component/live-player/live-player.uvue:176")
                    }
                    var complete = fun(res){
                        console.log("requestFullScreen", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:179")
                    }
                })
            }
            val exitFullScreen = fun(){
                if (!isSrcValid()) {
                    return
                }
                context.value?.exitFullScreen(object : UTSJSONObject() {
                    var success = fun(res){
                        console.log("exitFullScreen", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:187")
                    }
                    var fail = fun(err){
                        console.log("exitFullScreen", JSON.stringify(err), " at pages/component/live-player/live-player.uvue:190")
                    }
                    var complete = fun(res){
                        console.log("exitFullScreen", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:193")
                    }
                })
            }
            val objectFitItemTypes = utsArrayOf<ItemType>(ItemType(value = 0, name = "contain"), ItemType(value = 1, name = "fillCrop"))
            val objectFitItems = utsArrayOf(
                "contain",
                "fillCrop"
            )
            val soundModeItemTypes = utsArrayOf<ItemType>(ItemType(value = 0, name = "speaker"), ItemType(value = 1, name = "ear"))
            val soundModeItems = utsArrayOf(
                "speaker",
                "ear"
            )
            val orientationItemTypes = utsArrayOf<ItemType>(ItemType(value = 0, name = "vertical"), ItemType(value = 1, name = "horizontal"))
            val orientationItems = utsArrayOf(
                "vertical",
                "horizontal"
            )
            val onSrcComfirm = fun(event: UniInputConfirmEvent){
                var value = event.detail.value
                if (value == "") {
                    return
                }
                src.value = value
                console.log("src ->", value, " at pages/component/live-player/live-player.uvue:208")
            }
            val onAutoplayChange = fun(value: Boolean){
                autoplay.value = value
                console.log("autoplay ->", autoplay.value, " at pages/component/live-player/live-player.uvue:212")
            }
            val onMutedChange = fun(value: Boolean){
                muted.value = value
                console.log("muted ->", muted.value, " at pages/component/live-player/live-player.uvue:216")
            }
            val onBackgroundMuteChange = fun(value: Boolean){
                backgroundMute.value = value
                console.log("background-mute ->", backgroundMute.value, " at pages/component/live-player/live-player.uvue:220")
            }
            val onObjectFitChange = fun(value: Number){
                objectFit.value = objectFitItems[value]
                console.log("object-fit ->", objectFit.value, " at pages/component/live-player/live-player.uvue:224")
            }
            val onSoundModeChange = fun(value: Number){
                soundMode.value = soundModeItems[value]
                console.log("sound-mode ->", soundMode.value, " at pages/component/live-player/live-player.uvue:228")
            }
            val onOrientationChange = fun(value: Number){
                orientation.value = orientationItems[value]
                console.log("orientation ->", orientation.value, " at pages/component/live-player/live-player.uvue:232")
            }
            return fun(): Any? {
                val _component_boolean_data = resolveEasyComponent("boolean-data", GenComponentsBooleanDataBooleanDataClass)
                val _component_enum_data = resolveEasyComponent("enum-data", GenComponentsEnumDataEnumDataClass)
                return createElementVNode("view", utsMapOf("class" to "uni-flex-item"), utsArrayOf(
                    createElementVNode("live-player", utsMapOf("id" to "live-player", "class" to "live-player", "src" to unref(src), "autoplay" to unref(autoplay), "muted" to unref(muted), "object-fit" to unref(objectFit), "background-mute" to unref(backgroundMute), "sound-mode" to unref(soundMode), "orientation" to unref(orientation), "onStatechange" to statechange, "onFullscreenchange" to fullscreenchange, "onError" to error), null, 40, utsArrayOf(
                        "src",
                        "autoplay",
                        "muted",
                        "object-fit",
                        "background-mute",
                        "sound-mode",
                        "orientation"
                    )),
                    createElementVNode("scroll-view", utsMapOf("class" to "uni-padding-wrap uni-common-mt uni-flex-item"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "uni-title-text"), "API示例")
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "onClick" to play, "disabled" to unref(playState)), "播放", 8, utsArrayOf(
                                "disabled"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "onClick" to pause, "disabled" to !unref(playState)), "暂停", 8, utsArrayOf(
                                "disabled"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "onClick" to resume, "disabled" to (unref(initState) || unref(playState) || unref(stopState))), "恢复", 8, utsArrayOf(
                                "disabled"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "onClick" to stop, "disabled" to !unref(playState)), "停止", 8, utsArrayOf(
                                "disabled"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "onClick" to mute, "disabled" to !unref(playState)), "静音", 8, utsArrayOf(
                                "disabled"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "onClick" to requestFullScreen, "disabled" to !unref(playState)), "进入全屏", 8, utsArrayOf(
                                "disabled"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "onClick" to exitFullScreen, "disabled" to !unref(playState)), "退出全屏", 8, utsArrayOf(
                                "disabled"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "uni-title-text"), "属性示例")
                        )),
                        createElementVNode("input", utsMapOf("class" to "input margin-10", "type" to "string", "placeholder" to "设置播放地址", "onConfirm" to onSrcComfirm), null, 32),
                        createVNode(_component_boolean_data, utsMapOf("title" to "设置是否自动播放", "defaultValue" to unref(autoplay), "onChange" to onAutoplayChange), null, 8, utsArrayOf(
                            "defaultValue"
                        )),
                        createVNode(_component_boolean_data, utsMapOf("title" to "设置是否静音", "defaultValue" to unref(muted), "onChange" to onMutedChange), null, 8, utsArrayOf(
                            "defaultValue"
                        )),
                        createVNode(_component_boolean_data, utsMapOf("title" to "设置进入后台时是否静音", "defaultValue" to unref(backgroundMute), "onChange" to onBackgroundMuteChange), null, 8, utsArrayOf(
                            "defaultValue"
                        )),
                        createVNode(_component_enum_data, utsMapOf("title" to "设置填充模式", "items" to objectFitItemTypes, "onChange" to onObjectFitChange)),
                        createVNode(_component_enum_data, utsMapOf("title" to "设置声音输出方式", "items" to soundModeItemTypes, "onChange" to onSoundModeChange)),
                        createVNode(_component_enum_data, utsMapOf("title" to "设置画面方向", "items" to orientationItemTypes, "onChange" to onOrientationChange))
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
                return utsMapOf("live-player" to padStyleMapOf(utsMapOf("width" to "100%", "height" to "40%")), "input" to padStyleMapOf(utsMapOf("height" to 40, "backgroundImage" to "none", "backgroundColor" to "#FFFFFF", "paddingTop" to 8, "paddingRight" to 13, "paddingBottom" to 8, "paddingLeft" to 13)), "margin-10" to padStyleMapOf(utsMapOf("marginTop" to 10, "marginRight" to 10, "marginBottom" to 10, "marginLeft" to 10)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
