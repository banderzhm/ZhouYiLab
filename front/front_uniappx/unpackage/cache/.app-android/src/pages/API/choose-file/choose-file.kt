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
import io.dcloud.uniapp.extapi.chooseFile as uni_chooseFile
import io.dcloud.uniapp.extapi.createInnerAudioContext as uni_createInnerAudioContext
import io.dcloud.uniapp.extapi.previewImage as uni_previewImage
open class GenPagesAPIChooseFileChooseFile : BasePage {
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
        onUnmounted(fun() {
            if (this._audioContext != null) {
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
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("view", null, utsArrayOf(
                createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                    "title"
                )),
                createElementVNode("button", utsMapOf("size" to "mini", "onClick" to fun(){
                    _ctx.log = ""
                }
                ), "清空日志", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "2px", "padding" to "2px", "border" to "1px solid #000000"))), toDisplayString(_ctx.log), 5),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.chooseVideo), "选择文件(video) 单选", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.chooseVideoMul), "选择文件(video) 多选", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.playVideo), "选择文件(video)并播放", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("video", utsMapOf("class" to "video", "src" to _ctx.src, "controls" to true, "autoplay" to true, "loop" to true), "111", 8, utsArrayOf(
                    "src"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.chooseImage), "选择文件(image) 单选", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.chooseImageMul), "选择文件(image) 多选", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.viewImg), "选择文件(image) 并预览", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.chooseAudio), "选择文件(audio) 单选", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.chooseAudioMul), "选择文件(audio) 多选", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.playAudio), "选择文件(audio) 并播放", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.chooseAll), "选择文件(all) 单选", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.chooseAllMul), "选择文件(all) 多选", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("height" to "4px"))), null, 4)
            ))
        ), 4)
    }
    open var log: String by `$data`
    open var title: String by `$data`
    open var src: String by `$data`
    open var _audioContext: InnerAudioContext? by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("log" to "", "title" to "choose-file", "src" to "", "_audioContext" to null as InnerAudioContext?)
    }
    open var getPath = ::gen_getPath_fn
    open fun gen_getPath_fn(chooseFils: UTSArray<ChooseFileTempFile>): String {
        var urls = UTSArray<String>()
        chooseFils.forEach(fun(value){
            urls.push(value.path)
        }
        )
        return urls.join(", ")
    }
    open var chooseVideo = ::gen_chooseVideo_fn
    open fun gen_chooseVideo_fn() {
        uni_chooseFile(ChooseFileOptions(type = "video", count = 1, success = fun(res){
            console.log(res, " at pages/API/choose-file/choose-file.uvue:69")
            if (res.tempFiles.length > 0) {
                this.log += this.getPath(res.tempFiles) + "\n\n"
            }
        }
        , complete = fun(res){
            console.log(res, " at pages/API/choose-file/choose-file.uvue:75")
        }
        ))
    }
    open var chooseVideoMul = ::gen_chooseVideoMul_fn
    open fun gen_chooseVideoMul_fn() {
        uni_chooseFile(ChooseFileOptions(type = "video", success = fun(res){
            console.log(res, " at pages/API/choose-file/choose-file.uvue:83")
            if (res.tempFiles.length > 0) {
                this.log += this.getPath(res.tempFiles) + "\n\n"
            }
        }
        , complete = fun(res){
            console.log(res, " at pages/API/choose-file/choose-file.uvue:89")
        }
        ))
    }
    open var playVideo = ::gen_playVideo_fn
    open fun gen_playVideo_fn() {
        uni_chooseFile(ChooseFileOptions(type = "video", count = 1, success = fun(res){
            console.log(res, " at pages/API/choose-file/choose-file.uvue:98")
            if (res.tempFiles.length > 0) {
                this.src = res.tempFiles[0].path
                this._audioContext?.destroy()
                this._audioContext = null
            }
        }
        , complete = fun(res){
            console.log(res, " at pages/API/choose-file/choose-file.uvue:106")
        }
        ))
    }
    open var chooseImage = ::gen_chooseImage_fn
    open fun gen_chooseImage_fn() {
        uni_chooseFile(ChooseFileOptions(type = "image", count = 1, success = fun(res){
            console.log(res, " at pages/API/choose-file/choose-file.uvue:115")
            if (res.tempFiles.length > 0) {
                this.log += this.getPath(res.tempFiles) + "\n\n"
            }
        }
        , complete = fun(res){
            console.log(res, " at pages/API/choose-file/choose-file.uvue:121")
        }
        ))
    }
    open var chooseImageMul = ::gen_chooseImageMul_fn
    open fun gen_chooseImageMul_fn() {
        uni_chooseFile(ChooseFileOptions(type = "image", count = 90, success = fun(res){
            console.log(res, " at pages/API/choose-file/choose-file.uvue:130")
            if (res.tempFiles.length > 0) {
                this.log += this.getPath(res.tempFiles) + "\n\n"
            }
        }
        , complete = fun(res){
            console.log(res, " at pages/API/choose-file/choose-file.uvue:136")
        }
        ))
    }
    open var viewImg = ::gen_viewImg_fn
    open fun gen_viewImg_fn() {
        uni_chooseFile(ChooseFileOptions(type = "image", success = fun(res){
            console.log(res, " at pages/API/choose-file/choose-file.uvue:144")
            if (res.tempFiles.length > 0) {
                val tempFiles: UTSArray<String> = res.tempFiles.map(fun(value): String {
                    return value.path
                }
                )
                uni_previewImage(PreviewImageOptions(current = 0, urls = tempFiles, success = fun(res){
                    console.log(res, " at pages/API/choose-file/choose-file.uvue:153")
                }
                , fail = fun(err){
                    console.log(err, " at pages/API/choose-file/choose-file.uvue:156")
                }
                ))
            }
        }
        , complete = fun(res){
            console.log(res, " at pages/API/choose-file/choose-file.uvue:162")
        }
        ))
    }
    open var chooseAudio = ::gen_chooseAudio_fn
    open fun gen_chooseAudio_fn() {
        uni_chooseFile(ChooseFileOptions(type = "audio", count = 1, success = fun(res){
            console.log(res, " at pages/API/choose-file/choose-file.uvue:171")
            if (res.tempFiles.length > 0) {
                this.log += this.getPath(res.tempFiles) + "\n\n"
            }
        }
        , complete = fun(res){
            console.log(res, " at pages/API/choose-file/choose-file.uvue:177")
        }
        ))
    }
    open var chooseAudioMul = ::gen_chooseAudioMul_fn
    open fun gen_chooseAudioMul_fn() {
        uni_chooseFile(ChooseFileOptions(type = "audio", success = fun(res){
            console.log(res, " at pages/API/choose-file/choose-file.uvue:185")
            if (res.tempFiles.length > 0) {
                this.log += this.getPath(res.tempFiles) + "\n\n"
            }
        }
        , complete = fun(res){
            console.log(res, " at pages/API/choose-file/choose-file.uvue:191")
        }
        ))
    }
    open var playAudio = ::gen_playAudio_fn
    open fun gen_playAudio_fn() {
        uni_chooseFile(ChooseFileOptions(type = "audio", count = 1, success = fun(res){
            console.log(res, " at pages/API/choose-file/choose-file.uvue:200")
            if (res.tempFiles.length > 0) {
                if (this._audioContext == null) {
                    this.src = ""
                    this._audioContext = uni_createInnerAudioContext()
                    this._audioContext!!.autoplay = true
                } else if (!this._audioContext!!.paused) {
                    this._audioContext!!.stop()
                }
                this._audioContext!!.src = res.tempFiles[0].path
            }
        }
        , complete = fun(res){
            console.log(res, " at pages/API/choose-file/choose-file.uvue:213")
        }
        ))
    }
    open var chooseAll = ::gen_chooseAll_fn
    open fun gen_chooseAll_fn() {
        uni_chooseFile(ChooseFileOptions(type = "all", count = 1, success = fun(res){
            console.log(res, " at pages/API/choose-file/choose-file.uvue:223")
            if (res.tempFiles.length > 0) {
                this.log += this.getPath(res.tempFiles) + "\n\n"
            }
        }
        , complete = fun(res){
            console.log(res, " at pages/API/choose-file/choose-file.uvue:229")
        }
        ))
    }
    open var chooseAllMul = ::gen_chooseAllMul_fn
    open fun gen_chooseAllMul_fn() {
        uni_chooseFile(ChooseFileOptions(type = "all", success = fun(res){
            console.log(res, " at pages/API/choose-file/choose-file.uvue:237")
            if (res.tempFiles.length > 0) {
                this.log += this.getPath(res.tempFiles) + "\n\n"
            }
        }
        , complete = fun(res){
            console.log(res, " at pages/API/choose-file/choose-file.uvue:243")
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
                return utsMapOf("btnstyle" to padStyleMapOf(utsMapOf("marginTop" to 4, "marginRight" to 4, "marginBottom" to 4, "marginLeft" to 4)), "video" to padStyleMapOf(utsMapOf("width" to "100%", "height" to 225)), "uni-uploader__input-box" to padStyleMapOf(utsMapOf("marginTop" to 5, "marginRight" to 5, "marginBottom" to 5, "marginLeft" to 5, "width" to 104, "height" to 104, "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#D9D9D9", "borderRightColor" to "#D9D9D9", "borderBottomColor" to "#D9D9D9", "borderLeftColor" to "#D9D9D9")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
