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
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo
open class GenPagesComponentImageImage : BasePage {
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
        return createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                "title"
            )),
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "uni-center", "style" to normalizeStyle(utsMapOf("background" to "#FFFFFF"))), utsArrayOf(
                    createElementVNode("image", utsMapOf("class" to "image", "fade-show" to true, "mode" to "widthFix", "src" to _ctx.imageSrc, "onError" to _ctx.error, "onLoad" to _ctx.load), null, 40, utsArrayOf(
                        "src",
                        "onError",
                        "onLoad"
                    ))
                ), 4),
                createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                    createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.imageFormat), "图片格式示例", 8, utsArrayOf(
                        "onClick"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                    createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.imageMode), "图片缩放模式示例", 8, utsArrayOf(
                        "onClick"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                    createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.imagePath), "图片路径示例", 8, utsArrayOf(
                        "onClick"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                    createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.imageLarge), "大图示例", 8, utsArrayOf(
                        "onClick"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                    createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.imageLong), "长图示例", 8, utsArrayOf(
                        "onClick"
                    ))
                ))
            )),
            if (isTrue(_ctx.autoTest)) {
                createElementVNode("view", utsMapOf("key" to 0), utsArrayOf(
                    createElementVNode("image", utsMapOf("src" to _ctx.setCookieImage), null, 8, utsArrayOf(
                        "src"
                    )),
                    createElementVNode("image", utsMapOf("src" to _ctx.verifyCookieImage, "onError" to _ctx.error), null, 40, utsArrayOf(
                        "src",
                        "onError"
                    ))
                ))
            } else {
                createCommentVNode("v-if", true)
            }
        ), 4)
    }
    open var title: String by `$data`
    open var imageSrc: String by `$data`
    open var loadError: Boolean by `$data`
    open var autoTest: Boolean by `$data`
    open var setCookieImage: String by `$data`
    open var verifyCookieImage: String by `$data`
    open var eventLoad: UTSJSONObject? by `$data`
    open var eventError: UTSJSONObject? by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "image", "imageSrc" to "/static/test-image/logo.png" as String, "loadError" to false, "autoTest" to false, "setCookieImage" to "", "verifyCookieImage" to "", "eventLoad" to null as UTSJSONObject?, "eventError" to null as UTSJSONObject?)
    }
    open var error = ::gen_error_fn
    open fun gen_error_fn(event: ImageErrorEvent) {
        this.loadError = true
        console.log(event.type, event.detail, " at pages/component/image/image.uvue:48")
        if (this.autoTest) {
            this.eventError = object : UTSJSONObject() {
                var tagName = event.target?.tagName
                var type = event.type
            }
        }
    }
    open var load = ::gen_load_fn
    open fun gen_load_fn(event: ImageLoadEvent) {
        console.log(event.type, event.detail, " at pages/component/image/image.uvue:58")
        if (this.autoTest) {
            this.eventLoad = object : UTSJSONObject() {
                var tagName = event.target?.tagName
                var type = event.type
                var width = event.detail.width
                var height = event.detail.height
            }
        }
    }
    open var imageFormat = ::gen_imageFormat_fn
    open fun gen_imageFormat_fn() {
        uni_navigateTo(NavigateToOptions(url = "/pages/component/image/image-format"))
    }
    open var imageMode = ::gen_imageMode_fn
    open fun gen_imageMode_fn() {
        uni_navigateTo(NavigateToOptions(url = "/pages/component/image/image-mode"))
    }
    open var imagePath = ::gen_imagePath_fn
    open fun gen_imagePath_fn() {
        uni_navigateTo(NavigateToOptions(url = "/pages/component/image/image-path"))
    }
    open var imageLarge = ::gen_imageLarge_fn
    open fun gen_imageLarge_fn() {
        uni_navigateTo(NavigateToOptions(url = "/pages/component/image/image-large"))
    }
    open var imageLong = ::gen_imageLong_fn
    open fun gen_imageLong_fn() {
        uni_navigateTo(NavigateToOptions(url = "/pages/component/image/image-long"))
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
                return utsMapOf("image" to padStyleMapOf(utsMapOf("marginTop" to 20, "marginRight" to "auto", "marginBottom" to 20, "marginLeft" to "auto", "width" to 100)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
