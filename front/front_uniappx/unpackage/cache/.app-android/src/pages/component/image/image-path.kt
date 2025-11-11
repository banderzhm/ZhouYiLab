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
import io.dcloud.uniapp.extapi.env as uni_env
import io.dcloud.uniapp.extapi.getFileSystemManager as uni_getFileSystemManager
open class GenPagesComponentImageImagePath : BasePage {
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
        onLoad(fun(_: OnLoadOptions) {
            var fileManager = uni_getFileSystemManager()
            try {
                fileManager.accessSync("" + uni_env.USER_DATA_PATH + "/uni.png")
            }
             catch (e: Throwable) {
                fileManager.copyFileSync("/static/uni.png", "" + uni_env.USER_DATA_PATH + "/uni.png")
            }
            try {
                fileManager.accessSync("" + uni_env.CACHE_PATH + "/uni.png")
                fileManager.accessSync("" + uni_env.SANDBOX_PATH + "/uni.png")
            }
             catch (e: Throwable) {
                console.log("CACHE_PATH  error", " at pages/component/image/image-path.uvue:156")
                fileManager.copyFileSync("/static/uni.png", "" + uni_env.CACHE_PATH + "/uni.png")
                fileManager.copyFileSync("/static/uni.png", "" + uni_env.SANDBOX_PATH + "/uni.png")
            }
            try {
                fileManager.accessSync("" + uni_env.ANDROID_INTERNAL_SANDBOX_PATH + "/uni.png")
            }
             catch (e: Throwable) {
                fileManager.copyFileSync("/static/uni.png", "" + uni_env.ANDROID_INTERNAL_SANDBOX_PATH + "/uni.png")
            }
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        val _component_test_image_path = resolveEasyComponent("test-image-path", GenUniModulesTestImagePathComponentsTestImagePathTestImagePathClass)
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("view", null, utsArrayOf(
                createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                    "title"
                )),
                createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-title-text"), "图片路径示例")
                    )),
                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.data, fun(item, index, __index, _cached): Any {
                        return createElementVNode("view", utsMapOf("key" to index), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), toDisplayString(item.description), 1),
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
                    ), 128),
                    createVNode(_component_test_image_path),
                    createElementVNode("view", null, utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "非static目录的src静态路径：./logo.png"),
                        createElementVNode("view", utsMapOf("class" to "uni-center", "style" to normalizeStyle(utsMapOf("background" to "#FFFFFF"))), utsArrayOf(
                            createElementVNode("image", utsMapOf("class" to "image", "mode" to "widthFix", "src" to default2))
                        ), 4)
                    ))
                ))
            ))
        ), 4)
    }
    open var title: String by `$data`
    open var outsideStaticPath: String by `$data`
    open var outsideStaticErrorPath: String? by `$data`
    open var data: UTSArray<ImagePath> by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "image-path", "outsideStaticPath" to "./logo.png" as String, "outsideStaticErrorPath" to null as String?, "data" to utsArrayOf<ImagePath>(ImagePath(src = "/static/test-image/logo.png", description = "本地路径：/static方式"), ImagePath(src = "../../../static/test-image/logo.png", description = "本地路径：../static/"), ImagePath(src = "../../../static/Test-Image/Logo.png", description = "本地相对路径忽略大小写（需注意iOS、HarmonyOS平台不支持，Android平台云端打包后无效）"), ImagePath(src = default2, description = "import方式"), ImagePath(src = "file:///android_asset/apps/" + appid + "/www/static/test-image/logo.png", description = "本地绝对路径file://方式（仅限Android平台，云端打包后生效）"), ImagePath(src = "testerror.jpg", description = "错误的本地路径"), ImagePath(src = "data:image/*;base64,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", description = "Base64"), ImagePath(src = "https://request.dcloud.net.cn/api/http/contentType/image/png", description = "正确网络路径"), ImagePath(src = "https://request.dcloud.net.cn/api/http/contentType/wrongImage/gif", description = "错误网络格式，假gif实为jpeg"), ImagePath(src = "https://request.dcloud.net.cn/api/http/contentType/wrongImage/jpeg", description = "错误网络格式，假jpg实为png"), ImagePath(src = "https://request.dcloud.net.cn/api/http/contentType/wrongImage/png", description = "错误网络格式，假png实为gif"), ImagePath(src = "https://request.dcloud.net.cn/api/http/contentType/404", description = "错误网络地址404接口"), ImagePath(src = "https://request.dcloud.net.cn/api/http/contentType/404.png", description = "错误网络地址，不存在的图片"), ImagePath(src = "./logo.png", description = "非static目录的动态路径（不显示是对的）"), ImagePath(src = "data:image/*;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz4KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDI0LjEuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPgo8c3ZnIHZlcnNpb249IjEuMSIgaWQ9IuWbvuWxgl8xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB4PSIwcHgiCiAgeT0iMHB4IiB2aWV3Qm94PSIwIDAgMTk2IDE5NiIgc3R5bGU9ImVuYWJsZS1iYWNrZ3JvdW5kOm5ldyAwIDAgMTk2IDE5NjsiIHhtbDpzcGFjZT0icHJlc2VydmUiPgogIDxzdHlsZSB0eXBlPSJ0ZXh0L2NzcyI+CiAgICAuc3QwIHsKICAgICAgZmlsbDogIzI0OTgzQTsKICAgIH0KCiAgICAuc3QxIHsKICAgICAgZmlsbDogI0ZGRkZGRjsKICAgIH0KICA8L3N0eWxlPgogIDxlbGxpcHNlIGNsYXNzPSJzdDAiIGN4PSI5Ny45IiBjeT0iOTguMSIgcng9Ijk2LjgiIHJ5PSI5Ni44IiAvPgogIDxwb2x5Z29uIGNsYXNzPSJzdDEiIHBvaW50cz0iMTQyLjgsMTQ5LjggMTI1LjgsMTQ5LjggMTI1LjYsMTQ5LjggNzAuMiwxNDkuOCA3MC4yLDE0OS44IDUzLjEsMTQ5LjggNTMuMSw1Ni43IDcwLjMsNTYuNwoJNzAuMywxMzIuNiAxMjUuNiwxMzIuNiAxMjUuNiw1Ni43IDE0Mi44LDU2LjcgIiAvPgo8L3N2Zz4K", description = "base64-svg"), ImagePath(src = "https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/w3c.svg", description = "正确的svg网络路径"), ImagePath(src = uni_env.USER_DATA_PATH + "/uni.png", description = "uni.env.USER_DATA_PATH 路径"), ImagePath(src = uni_env.CACHE_PATH + "/uni.png", description = "uni.env.CACHE_PATH 路径"), ImagePath(src = uni_env.SANDBOX_PATH + "/uni.png", description = "uni.env.SANDBOX_PATH 路径"), ImagePath(src = uni_env.ANDROID_INTERNAL_SANDBOX_PATH + "/uni.png", description = "uni.env.ANDROID_INTERNAL_SANDBOX_PATH 路径")))
    }
    open var imageErrorEvent = ::gen_imageErrorEvent_fn
    open fun gen_imageErrorEvent_fn(index: Number, e: ImageErrorEvent) {
        console.log("图片加载错误", e.detail.errMsg, " at pages/component/image/image-path.uvue:184")
        this.data[index].errorImage = "/static/template/drop-card/dislike.png"
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
