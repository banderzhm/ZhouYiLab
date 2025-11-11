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
import io.dcloud.uniapp.extapi.`$emit` as uni__emit
import io.dcloud.uniapp.extapi.`$once` as uni__once
import io.dcloud.uniapp.extapi.chooseMedia as uni_chooseMedia
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo
import io.dcloud.uniapp.extapi.previewImage as uni_previewImage
import io.dcloud.uniapp.extapi.showActionSheet as uni_showActionSheet
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesAPIChooseMediaChooseMedia : BasePage {
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
        return createElementVNode("scroll-view", utsMapOf("class" to "page-scroll-view"), utsArrayOf(
            createElementVNode("view", null, utsArrayOf(
                createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                    "title"
                )),
                createElementVNode("view", utsMapOf("class" to "uni-common-mt"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-list"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell cell-pd"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-list-cell-left uni-label"), " 来源 "),
                            createElementVNode("view", utsMapOf("class" to "uni-list-cell-right", "onClick" to _ctx.chooseMediaSource), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "click-t"), toDisplayString(_ctx.sourceTypes[_ctx.sourceTypeIndex].title), 1)
                            ), 8, utsArrayOf(
                                "onClick"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell cell-pd"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-list-cell-left uni-label"), " 方式 "),
                            createElementVNode("view", utsMapOf("class" to "uni-list-cell-right", "onClick" to _ctx.chooseMediaType), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "click-t"), toDisplayString((_ctx.mediaTypes[_ctx.mediaTypeIndex] as ChooseSource).title), 1)
                            ), 8, utsArrayOf(
                                "onClick"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell cell-pd"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-list-cell-left uni-label"), " 数量限制 "),
                            createElementVNode("view", utsMapOf("class" to "uni-list-cell-right"), utsArrayOf(
                                createElementVNode("input", utsMapOf("class" to "click-t", "ref" to "refCountInput", "value" to _ctx.count, "type" to "number", "maxlength" to 1, "onBlur" to _ctx.chooseMediaCount), null, 40, utsArrayOf(
                                    "value",
                                    "onBlur"
                                ))
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell cell-pd"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-list-cell-left uni-label"), " 屏幕方向 "),
                            createElementVNode("view", utsMapOf("class" to "uni-list-cell-right", "onClick" to _ctx.chooseOrientationType), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "click-t"), toDisplayString(_ctx.orientationTypes[_ctx.orientationTypeIndex].title), 1)
                            ), 8, utsArrayOf(
                                "onClick"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell cell-pd"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-list-cell-left uni-label"), " 摄像头 "),
                            createElementVNode("view", utsMapOf("class" to "uni-list-cell-right", "onClick" to _ctx.chooseCameraType), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "click-t"), toDisplayString(_ctx.cameraTypes[_ctx.cameraTypeIndex].title), 1)
                            ), 8, utsArrayOf(
                                "onClick"
                            ))
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list list-pd", "style" to normalizeStyle(utsMapOf("padding" to "15px"))), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-flex", "style" to normalizeStyle(utsMapOf("margin-bottom" to "10px"))), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-list-cell-left"), "点击预览"),
                            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("margin-left" to "auto"))), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "click-t"), toDisplayString(_ctx.mediaList.length) + "/" + toDisplayString(_ctx.count), 1)
                            ), 4)
                        ), 4),
                        createElementVNode("view", utsMapOf("class" to "uni-flex", "style" to normalizeStyle(utsMapOf("flex-wrap" to "wrap"))), utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.mediaList, fun(file, index, __index, _cached): Any {
                                return createElementVNode("view", utsMapOf("key" to index, "class" to "uni-uploader__input-box", "style" to normalizeStyle(utsMapOf("border" to "0"))), utsArrayOf(
                                    createElementVNode("image", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "104px", "height" to "104px")), "src" to file.imagePath, "data-src" to file.imagePath, "onClick" to fun(){
                                        _ctx.previewMedia(index)
                                    }
                                    ), null, 12, utsArrayOf(
                                        "src",
                                        "data-src",
                                        "onClick"
                                    )),
                                    createElementVNode("image", utsMapOf("src" to "/static/plus.png", "class" to "image-remove", "onClick" to fun(){
                                        _ctx.removeMedia(index)
                                    }
                                    ), null, 8, utsArrayOf(
                                        "onClick"
                                    ))
                                ), 4)
                            }
                            ), 128),
                            createElementVNode("image", utsMapOf("class" to "uni-uploader__input-box", "onClick" to _ctx.chooseMedia, "src" to "/static/plus.png"), null, 8, utsArrayOf(
                                "onClick"
                            ))
                        ), 4)
                    ), 4)
                ))
            ))
        ))
    }
    open var title: String by `$data`
    open var mediaList: UTSArray<FileSource> by `$data`
    open var sourceTypeIndex: Number by `$data`
    open var mediaTypeIndex: Number by `$data`
    open var cameraTypeIndex: Number by `$data`
    open var orientationTypeIndex: Number by `$data`
    open var albumModeTypeIndex: Number by `$data`
    open var count: Number by `$data`
    open var maxDuration: Number by `$data`
    open var sourceTypes: UTSArray<ChooseSource> by `$data`
    open var mediaTypes: UTSArray<ChooseSource> by `$data`
    open var cameraTypes: UTSArray<ChooseSource> by `$data`
    open var orientationTypes: UTSArray<ChooseSource> by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "chooseMedia", "mediaList" to utsArrayOf<FileSource>(), "sourceTypeIndex" to 2, "mediaTypeIndex" to 2, "cameraTypeIndex" to 1, "orientationTypeIndex" to 0, "albumModeTypeIndex" to 0, "count" to 9, "maxDuration" to 10, "sourceTypes" to sourceTypeList as UTSArray<ChooseSource>, "mediaTypes" to mediaTypeList as UTSArray<ChooseSource>, "cameraTypes" to cameraTypeList as UTSArray<ChooseSource>, "orientationTypes" to orientationTypeList as UTSArray<ChooseSource>)
    }
    open var chooseMediaSource = ::gen_chooseMediaSource_fn
    open fun gen_chooseMediaSource_fn() {
        uni_showActionSheet(ShowActionSheetOptions(itemList = utsArrayOf(
            "拍摄",
            "相册",
            "拍摄或相册"
        ), success = fun(e){
            this.sourceTypeIndex = e.tapIndex
        }
        ))
    }
    open var chooseMediaType = ::gen_chooseMediaType_fn
    open fun gen_chooseMediaType_fn() {
        uni_showActionSheet(ShowActionSheetOptions(itemList = utsArrayOf(
            "仅图片",
            "仅视频",
            "不限制"
        ), success = fun(e){
            this.mediaTypeIndex = e.tapIndex
        }
        ))
    }
    open var chooseMediaCount = ::gen_chooseMediaCount_fn
    open fun gen_chooseMediaCount_fn(event: UniInputBlurEvent) {
        var count = parseInt(event.detail.value)
        if (count < 1 || count > 9 || isNaN(count)) {
            uni_showToast(ShowToastOptions(position = "bottom", title = "图片数量应该不小于1不大于9"))
            return
        }
        this.count = count
    }
    open var chooseOrientationType = ::gen_chooseOrientationType_fn
    open fun gen_chooseOrientationType_fn() {
        uni_showActionSheet(ShowActionSheetOptions(itemList = utsArrayOf(
            "竖屏",
            "横屏",
            "自动"
        ), success = fun(e){
            this.orientationTypeIndex = e.tapIndex
        }
        ))
    }
    open var chooseCameraType = ::gen_chooseCameraType_fn
    open fun gen_chooseCameraType_fn() {
        uni_showActionSheet(ShowActionSheetOptions(itemList = utsArrayOf(
            "前置",
            "后置"
        ), success = fun(e){
            this.cameraTypeIndex = e.tapIndex
        }
        ))
    }
    open var onMaxDurationConfirm = ::gen_onMaxDurationConfirm_fn
    open fun gen_onMaxDurationConfirm_fn(value: Number) {
        this.maxDuration = value
    }
    open var chooseMedia = ::gen_chooseMedia_fn
    open fun gen_chooseMedia_fn() {
        if (this.mediaList.length >= this.count) {
            val message = "已经有" + this.count + "个了，请删除部分后重新选择"
            uni_showToast(ShowToastOptions(position = "bottom", title = message))
            return
        }
        uni_chooseMedia(ChooseMediaOptions(count = this.count - this.mediaList.length, sourceType = sourceTypeList[this.sourceTypeIndex].value, mediaType = mediaTypeList[this.mediaTypeIndex].value, camera = cameraTypeList[this.cameraTypeIndex].value[0], pageOrientation = orientationTypeList[this.orientationTypeIndex].value[0], success = fun(res){
            val tempFiles: UTSArray<ChooseMediaTempFile> = res.tempFiles as UTSArray<ChooseMediaTempFile>
            run {
                var i: Number = 0
                while(i < tempFiles.length){
                    val tempFile: ChooseMediaTempFile = tempFiles[i]
                    val imagePath = if (tempFile.fileType == "image") {
                        tempFile.tempFilePath
                    } else {
                        tempFile.thumbTempFilePath
                    }
                    val file = FileSource(imagePath = imagePath!!, filePath = tempFile.tempFilePath, fileType = tempFile.fileType)
                    this.mediaList.push(file)
                    i++
                }
            }
        }
        , fail = fun(err){
            console.log("err: ", JSON.stringify(err), " at pages/API/choose-media/choose-media.uvue:243")
            uni_showToast(ShowToastOptions(title = "choose media error.code:" + err.errCode + ";message:" + err.errMsg, position = "bottom"))
        }
        ))
    }
    open var previewMedia = ::gen_previewMedia_fn
    open fun gen_previewMedia_fn(index: Number) {
        val file: FileSource = this.mediaList[index]
        if (file.fileType == "image") {
            uni_previewImage(PreviewImageOptions(current = 0, urls = utsArrayOf(
                file.filePath
            )))
        } else {
            uni__once("__ONFULLVIDEOLOAD", fun(){
                uni__emit("__ONRECEIVEURL", object : UTSJSONObject() {
                    var url = file.filePath
                    var cover = file.imagePath
                })
            }
            )
            val url = "/pages/API/choose-media/fullscreen-video"
            uni_navigateTo(NavigateToOptions(url = url))
        }
    }
    open var removeMedia = ::gen_removeMedia_fn
    open fun gen_removeMedia_fn(index: Number) {
        this.mediaList.splice(index, 1)
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
                return utsMapOf("cell-pd" to padStyleMapOf(utsMapOf("paddingTop" to 11, "paddingRight" to 15, "paddingBottom" to 11, "paddingLeft" to 15)), "click-t" to padStyleMapOf(utsMapOf("color" to "#A9A9A9")), "list-pd" to padStyleMapOf(utsMapOf("marginTop" to 25)), "uni-uploader__input-box" to padStyleMapOf(utsMapOf("marginTop" to 5, "marginRight" to 5, "marginBottom" to 5, "marginLeft" to 5, "width" to 104, "height" to 104, "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#D9D9D9", "borderRightColor" to "#D9D9D9", "borderBottomColor" to "#D9D9D9", "borderLeftColor" to "#D9D9D9")), "uni-uploader__input" to padStyleMapOf(utsMapOf("position" to "absolute", "zIndex" to 1, "top" to 0, "left" to 0, "width" to "100%", "height" to "100%", "opacity" to 0)), "image-remove" to padStyleMapOf(utsMapOf("transform" to "rotate(45deg)", "width" to 25, "height" to 25, "position" to "absolute", "top" to 0, "right" to 0, "borderTopLeftRadius" to 13, "borderTopRightRadius" to 13, "borderBottomRightRadius" to 13, "borderBottomLeftRadius" to 13, "backgroundColor" to "rgba(200,200,200,0.8)")), "item_width" to padStyleMapOf(utsMapOf("width" to 130)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
