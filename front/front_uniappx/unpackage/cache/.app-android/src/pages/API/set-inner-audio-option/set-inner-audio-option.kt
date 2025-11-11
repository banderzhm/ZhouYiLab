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
import io.dcloud.uniapp.extapi.getBackgroundAudioManager as uni_getBackgroundAudioManager
import io.dcloud.uniapp.extapi.setInnerAudioOption as uni_setInnerAudioOption
open class GenPagesAPISetInnerAudioOptionSetInnerAudioOption : BasePage {
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
            this.backgroundManager?.stop()
            this.innerAudio?.stop()
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
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to "setInnerAudioOption")),
            createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-left" to "8px"))), "是否允许与其他音频同时播放", 4),
            createVNode(_component_radio_group, utsMapOf("class" to "uni-flex", "style" to normalizeStyle(utsMapOf("margin" to "8px")), "onChange" to _ctx.onMixChanged), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                return utsArrayOf(
                    createVNode(_component_radio, utsMapOf("value" to "1", "checked" to true), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            "是"
                        )
                    }
                    ), "_" to 1)),
                    createVNode(_component_radio, utsMapOf("value" to "0", "style" to normalizeStyle(utsMapOf("margin-left" to "16px"))), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            "否"
                        )
                    }
                    ), "_" to 1), 8, utsArrayOf(
                        "style"
                    ))
                )
            }
            ), "_" to 1), 8, utsArrayOf(
                "style",
                "onChange"
            )),
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("padding" to "8px 8px"))), utsArrayOf(
                createElementVNode("button", utsMapOf("onClick" to _ctx.playBackgroundMusic, "type" to "primary"), "播放背景音频", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("onClick" to _ctx.playInnerMusic, "style" to normalizeStyle(utsMapOf("margin-top" to "8px"))), "播放音频", 12, utsArrayOf(
                    "onClick"
                ))
            ), 4),
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("padding" to "16px 8px"))), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "uni-row"), utsArrayOf(
                    createElementVNode("view", null, "1. "),
                    "uni.setInnerAudioOption需要与uni.createInnerAudioContext搭配才会生效 "
                )),
                createElementVNode("view", utsMapOf("class" to "uni-row"), utsArrayOf(
                    createElementVNode("view", null, "2. "),
                    "设置mixWithOther为true时，会暂停其他App的音频和背景音频 "
                ))
            ), 4)
        ), 4)
    }
    open var backgroundManager: BackgroundAudioManager? by `$data`
    open var innerAudio: InnerAudioContext? by `$data`
    open var isBackgroundAudioPaused: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("backgroundManager" to null as BackgroundAudioManager?, "innerAudio" to null as InnerAudioContext?, "isBackgroundAudioPaused" to false)
    }
    open var playBackgroundMusic = ::gen_playBackgroundMusic_fn
    open fun gen_playBackgroundMusic_fn() {
        if (this.backgroundManager == null) {
            this.backgroundManager = uni_getBackgroundAudioManager()
            this.backgroundManager.onPause(fun(_result){
                this.isBackgroundAudioPaused = true
            })
        } else {
            this.backgroundManager!!.stop()
        }
        this.backgroundManager!!.src = "https://web-ext-storage.dcloud.net.cn/uni-app/ForElise.mp3"
        this.backgroundManager!!.coverImgUrl = "https://qiniu-web-assets.dcloud.net.cn/unidoc/zh/music-a.png"
        this.backgroundManager!!.play()
    }
    open var playInnerMusic = ::gen_playInnerMusic_fn
    open fun gen_playInnerMusic_fn() {
        if (this.innerAudio == null) {
            this.innerAudio = uni_createInnerAudioContext()
        } else {
            this.innerAudio!!.stop()
        }
        this.innerAudio!!.src = "https://web-ext-storage.dcloud.net.cn/uni-app/ForElise.mp3"
        this.innerAudio!!.play()
    }
    open var onMixChanged = ::gen_onMixChanged_fn
    open fun gen_onMixChanged_fn(event: UniRadioGroupChangeEvent) {
        uni_setInnerAudioOption(SetInnerAudioOptionOptions(mixWithOther = event.detail.value == "1"))
    }
    open var testInnerAudioOption = ::gen_testInnerAudioOption_fn
    open fun gen_testInnerAudioOption_fn() {
        uni_setInnerAudioOption(SetInnerAudioOptionOptions(mixWithOther = false))
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(), utsArrayOf(
                GenApp.styles
            ))
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
