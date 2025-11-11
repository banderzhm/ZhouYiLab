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
import io.dcloud.uniapp.extapi.saveImageToPhotosAlbum as uni_saveImageToPhotosAlbum
import io.dcloud.uniapp.extapi.showModal as uni_showModal
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesAPISaveImageToPhotosAlbumSaveImageToPhotosAlbum : BasePage {
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
                createElementVNode("image", utsMapOf("class" to "image", "src" to "/static/uni.png")),
                createElementVNode("button", utsMapOf("class" to "margin-top-10", "type" to "primary", "onClick" to _ctx.saveImage), "将图片保存到手机相册", 8, utsArrayOf(
                    "onClick"
                ))
            ))
        ), 4)
    }
    open var title: String by `$data`
    open var success: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "saveImageToPhotosAlbum", "success" to false)
    }
    open var saveImage = ::gen_saveImage_fn
    open fun gen_saveImage_fn() {
        uni_saveImageToPhotosAlbum(SaveImageToPhotosAlbumOptions(filePath = "/static/uni.png", success = fun(res){
            console.log("saveImageToPhotosAlbum success", JSON.stringify(res), " at pages/API/save-image-to-photos-album/save-image-to-photos-album.uvue:29")
            uni_showToast(ShowToastOptions(position = "center", icon = "none", title = "图片保存成功，请到手机相册查看"))
            this.success = true
        }
        , fail = fun(err){
            uni_showModal(ShowModalOptions(title = "保存图片到相册失败", content = JSON.stringify(err), showCancel = false))
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
                return utsMapOf("margin-top-10" to padStyleMapOf(utsMapOf("marginTop" to 10)), "image" to padStyleMapOf(utsMapOf("width" to 196, "height" to 196, "alignSelf" to "center")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
