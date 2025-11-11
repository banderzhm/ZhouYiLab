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
open class GenPagesComponentVideoVideoFormat : BasePage {
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
    open var isError: UTSJSONObject
        get() {
            return unref(this.`$exposed`["isError"]) as UTSJSONObject
        }
        set(value) {
            setRefValue(this.`$exposed`, "isError", value)
        }
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesComponentVideoVideoFormat, _arg1: SetupContext) -> Any? = fun(__props, ref1): Any? {
            var __expose = ref1.expose
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesComponentVideoVideoFormat
            val _cache = __ins.renderCache
            var supportFormats = ref(utsArrayOf<VideoFormat>(VideoFormat(format = "mp4", src = "https://qiniu-web-assets.dcloud.net.cn/video/sample/2minute-demo.mp4"), VideoFormat(format = "m4v", src = "https://qiniu-web-assets.dcloud.net.cn/video/sample/2minute-demo.m4v"), VideoFormat(format = "mov", src = "https://qiniu-web-assets.dcloud.net.cn/video/sample/2minute-demo.mov"), VideoFormat(format = "webm（iOS、Safari、鸿蒙不支持）", src = "https://qiniu-web-assets.dcloud.net.cn/video/sample/2minute-demo.webm"), VideoFormat(format = "3gp", src = "https://qiniu-web-assets.dcloud.net.cn/video/sample/2minute-demo.3gp"), VideoFormat(format = "flv", src = "https://qiniu-web-assets.dcloud.net.cn/video/sample/2minute-demo.flv"), VideoFormat(format = "m3u8", src = "https://qiniu-web-assets.dcloud.net.cn/video/sample/2minute-demo.m3u8"), VideoFormat(format = "本地m3u8", src = "/static/test-video/2minute-demo.m3u8"), VideoFormat(format = "错误路径", src = "https://www.dcloud.net.cn/errorpath.mp4")))
            var notSupportFormats = ref(utsArrayOf<VideoFormat>(VideoFormat(format = "ogg", src = "https://qiniu-web-assets.dcloud.net.cn/video/sample/2minute-demo.ogg"), VideoFormat(format = "avi", src = "https://qiniu-web-assets.dcloud.net.cn/video/sample/2minute-demo.avi")))
            val isError = reactive(object : UTSJSONObject() {
                var value = false
            })
            val onError = fun(format: String, e: UniVideoErrorEvent){
                console.log(format + ":" + JSON.stringify(e), " at pages/component/video/video-format.uvue:101")
                if (format != "错误路径") {
                    isError["value"] = true
                }
            }
            onReady(fun(){})
            __expose(utsMapOf("isError" to isError))
            return fun(): Any? {
                val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
                return createElementVNode("scroll-view", utsMapOf("class" to "page-scroll-view"), utsArrayOf(
                    createVNode(_component_page_head, utsMapOf("title" to "video-format")),
                    createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "uni-title-text"), "支持的视频格式示例")
                        )),
                        createElementVNode(Fragment, null, RenderHelpers.renderList(unref(supportFormats), fun(item, index, __index, _cached): Any {
                            return createElementVNode("view", utsMapOf("key" to index), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), toDisplayString(item.format), 1),
                                createElementVNode("video", utsMapOf("id" to ("video-" + item.format), "class" to "video", "src" to item.src, "controls" to true, "direction" to -90, "onError" to fun(`$event`: Any){
                                    onError(item.format, `$event` as UniVideoErrorEvent)
                                }
                                ), null, 40, utsArrayOf(
                                    "id",
                                    "src",
                                    "onError"
                                ))
                            ))
                        }
                        ), 128),
                        createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "uni-title-text"), "暂不支持的格式")
                        )),
                        createElementVNode(Fragment, null, RenderHelpers.renderList(unref(notSupportFormats), fun(item, index, __index, _cached): Any {
                            return createElementVNode("view", utsMapOf("key" to index), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), toDisplayString(item.format), 1),
                                createElementVNode("video", utsMapOf("id" to ("video-" + item.format), "ref_for" to true, "ref" to ("videoRef-" + item.format), "class" to "video", "src" to item.src, "controls" to true, "direction" to -90), null, 8, utsArrayOf(
                                    "id",
                                    "src"
                                ))
                            ))
                        }
                        ), 128)
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
                return utsMapOf("video" to padStyleMapOf(utsMapOf("height" to 200)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
