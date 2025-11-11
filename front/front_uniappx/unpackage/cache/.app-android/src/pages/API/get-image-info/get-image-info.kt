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
import io.dcloud.uniapp.extapi.chooseImage as uni_chooseImage
import io.dcloud.uniapp.extapi.getImageInfo as uni_getImageInfo
import io.dcloud.uniapp.extapi.showModal as uni_showModal
open class GenPagesAPIGetImageInfoGetImageInfo : BasePage {
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
        onReady(fun() {
            uni_getImageInfo(GetImageInfoOptions(src = this.relativeImagePath, success = fun(res){
                console.log("getImageInfo success", JSON.stringify(res), " at pages/API/get-image-info/get-image-info.uvue:74")
                this.absoluteImageInfo = "\u56FE\u7247\u5BBD\u5EA6: " + res.width + "\n\u56FE\u7247\u9AD8\u5EA6: " + res.height + "\n\u56FE\u7247\u8DEF\u5F84: " + res.path + "\n\u56FE\u7247\u65B9\u5411: " + res.orientation + "\n\u56FE\u7247\u683C\u5F0F: " + res.type
                this.imageInfoForTest = object : UTSJSONObject() {
                    var width = res.width
                    var height = res.height
                    var path = res.path.slice(res.path.indexOf("static/") + 7)
                    var orientation = res.orientation
                    var type = res.type
                }
            }
            , fail = fun(err){
                uni_showModal(ShowModalOptions(title = "获取图片信息失败", content = JSON.stringify(err), showCancel = false))
                this.imageInfoForTest = null
            }
            ))
            uni_getImageInfo(GetImageInfoOptions(src = this.remoteImagePath, success = fun(res){
                console.log("getImageInfo success", JSON.stringify(res), " at pages/API/get-image-info/get-image-info.uvue:96")
                this.remoteImageInfo = "\u56FE\u7247\u5BBD\u5EA6: " + res.width + "\n\u56FE\u7247\u9AD8\u5EA6: " + res.height + "\n\u56FE\u7247\u8DEF\u5F84: " + res.path + "\n\u56FE\u7247\u65B9\u5411: " + res.orientation + "\n\u56FE\u7247\u683C\u5F0F: " + res.type
            }
            , fail = fun(err){
                uni_showModal(ShowModalOptions(title = "获取图片信息失败", content = JSON.stringify(err), showCancel = false))
            }
            ))
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
                createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "获取本地相对路径图片信息")
                )),
                createElementVNode("image", utsMapOf("class" to "image", "src" to _ctx.relativeImagePath, "mode" to "aspectFit"), null, 8, utsArrayOf(
                    "src"
                )),
                createElementVNode("text", utsMapOf("class" to "margin-top-10"), toDisplayString(_ctx.absoluteImageInfo), 1),
                createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "获取网络路径图片信息")
                )),
                createElementVNode("image", utsMapOf("class" to "image", "src" to _ctx.remoteImagePath, "mode" to "aspectFit"), null, 8, utsArrayOf(
                    "src"
                )),
                createElementVNode("text", utsMapOf("class" to "margin-top-10"), toDisplayString(_ctx.remoteImageInfo), 1),
                createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "获取本地绝对路径图片信息")
                )),
                createElementVNode("image", utsMapOf("class" to "image", "src" to _ctx.absoluteImagePath, "mode" to "aspectFit"), null, 8, utsArrayOf(
                    "src"
                )),
                createElementVNode("text", utsMapOf("class" to "margin-top-10"), toDisplayString(_ctx.relativeImageInfo), 1),
                createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                    createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.chooseImage), "拍摄照片或从相册中选择照片", 8, utsArrayOf(
                        "onClick"
                    ))
                ))
            ))
        ), 4)
    }
    open var title: String by `$data`
    open var relativeImagePath: String by `$data`
    open var relativeImageInfo: String by `$data`
    open var absoluteImagePath: String by `$data`
    open var absoluteImageInfo: String by `$data`
    open var remoteImagePath: String by `$data`
    open var remoteImageInfo: String by `$data`
    open var imageInfoForTest: UTSJSONObject? by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "getImageInfo", "relativeImagePath" to "/static/test-image/logo.png", "relativeImageInfo" to "", "absoluteImagePath" to "", "absoluteImageInfo" to "", "remoteImagePath" to "https://request.dcloud.net.cn/api/http/contentType/image/png", "remoteImageInfo" to "", "imageInfoForTest" to null as UTSJSONObject?)
    }
    open var chooseImage = ::gen_chooseImage_fn
    open fun gen_chooseImage_fn() {
        uni_chooseImage(ChooseImageOptions(count = 1, success = fun(res){
            this.absoluteImagePath = res.tempFilePaths[0]
            uni_getImageInfo(GetImageInfoOptions(src = res.tempFilePaths[0], success = fun(_res){
                console.log("getImageInfo success", JSON.stringify(_res), " at pages/API/get-image-info/get-image-info.uvue:55")
                this.relativeImageInfo = "\u56FE\u7247\u5BBD\u5EA6: " + _res.width + "\n\u56FE\u7247\u9AD8\u5EA6: " + _res.height + "\n\u56FE\u7247\u8DEF\u5F84: " + _res.path + "\n\u56FE\u7247\u65B9\u5411: " + _res.orientation + "\n\u56FE\u7247\u683C\u5F0F: " + _res.type
            }
            , fail = fun(err){
                uni_showModal(ShowModalOptions(title = "获取图片信息失败", content = JSON.stringify(err), showCancel = false))
            }
            ))
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
                return utsMapOf("image" to padStyleMapOf(utsMapOf("alignSelf" to "center")), "margin-top-10" to padStyleMapOf(utsMapOf("marginTop" to 10)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
