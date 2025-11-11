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
open class GenPagesComponentImageImageFormat : BasePage {
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
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt"), utsArrayOf(
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.supportFormats, fun(item, index, __index, _cached): Any {
                    return createElementVNode("view", utsMapOf("key" to index), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), toDisplayString(item.format), 1),
                        createElementVNode("view", utsMapOf("class" to "uni-center", "style" to normalizeStyle(utsMapOf("background" to "#FFFFFF"))), utsArrayOf(
                            createElementVNode("image", utsMapOf("class" to "image", "mode" to "widthFix", "src" to if (item.errorImage == null) {
                                item.src
                            } else {
                                item.errorImage
                            }
                            , "onError" to fun(`$event`: Any){
                                _ctx.imageErrorEvent(index, `$event` as ImageErrorEvent)
                            }
                            ), null, 40, utsArrayOf(
                                "src",
                                "onError"
                            ))
                        ), 4)
                    ))
                }
                ), 128)
            ))
        ), 4)
    }
    open var title: String by `$data`
    open var supportFormats: UTSArray<ImageFormat> by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "image-format", "supportFormats" to utsArrayOf<ImageFormat>(ImageFormat(format = "bmp", src = "/static/test-image/logo.bmp"), ImageFormat(format = "gif", src = "/static/test-image/logo.gif"), ImageFormat(format = "ico", src = "/static/test-image/logo.ico"), ImageFormat(format = "jpg", src = "/static/test-image/logo.jpg"), ImageFormat(format = "png", src = "/static/test-image/logo.png"), ImageFormat(format = "webp", src = "/static/test-image/logo.webp"), ImageFormat(format = "heic（App-Android10+支持）", src = "/static/test-image/logo.heic"), ImageFormat(format = "avif（仅部分浏览器支持）", src = "/static/test-image/logo.avif"), ImageFormat(format = "tif（仅部分浏览器支持）", src = "/static/test-image/logo.tif"), ImageFormat(format = "svg", src = "/static/test-image/logo.svg"), ImageFormat(format = "svg网络路径", src = "https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/w3c.svg")))
    }
    open var imageErrorEvent = ::gen_imageErrorEvent_fn
    open fun gen_imageErrorEvent_fn(index: Number, e: ImageErrorEvent) {
        console.log("图片加载错误", e.detail, " at pages/component/image/image-format.uvue:99")
        this.supportFormats[index].errorImage = "/static/template/drop-card/dislike.png"
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
                return utsMapOf("image" to padStyleMapOf(utsMapOf("marginTop" to 40, "marginRight" to "auto", "marginBottom" to 40, "marginLeft" to "auto", "width" to 100)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
