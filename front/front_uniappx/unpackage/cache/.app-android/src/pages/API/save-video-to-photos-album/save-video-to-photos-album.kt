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
import io.dcloud.uniapp.extapi.saveVideoToPhotosAlbum as uni_saveVideoToPhotosAlbum
import io.dcloud.uniapp.extapi.showModal as uni_showModal
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesAPISaveVideoToPhotosAlbumSaveVideoToPhotosAlbum : BasePage {
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
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                "title"
            )),
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                createElementVNode("video", utsMapOf("class" to "video", "src" to _ctx.src, "controls" to true), null, 8, utsArrayOf(
                    "src"
                )),
                createElementVNode("button", utsMapOf("type" to "primary", "class" to "margin-top-10", "onClick" to _ctx.saveVideo), "将视频保存到手机相册", 8, utsArrayOf(
                    "onClick"
                ))
            ))
        ), 4)
    }
    open var title: String by `$data`
    open var src: String by `$data`
    open var success: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "saveVideoToPhotosAlbum", "src" to "/static/test-video/10second-demo.mp4", "success" to false)
    }
    open var saveVideo = ::gen_saveVideo_fn
    open fun gen_saveVideo_fn() {
        uni_saveVideoToPhotosAlbum(SaveVideoToPhotosAlbumOptions(filePath = this.src, success = fun(_){
            console.log("saveVideoToPhotosAlbum success", " at pages/API/save-video-to-photos-album/save-video-to-photos-album.uvue:30")
            uni_showToast(ShowToastOptions(position = "center", icon = "none", title = "视频保存成功，请到手机相册查看"))
            this.success = true
        }
        , fail = fun(err){
            uni_showModal(ShowModalOptions(title = "保存视频到相册失败", content = JSON.stringify(err), showCancel = false))
            this.success = false
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
                return utsMapOf("video" to padStyleMapOf(utsMapOf("alignSelf" to "center")), "margin-top-10" to padStyleMapOf(utsMapOf("marginTop" to 10)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
