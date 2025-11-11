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
import io.dcloud.uniapp.extapi.previewImage as uni_previewImage
import io.dcloud.uniapp.extapi.showActionSheet as uni_showActionSheet
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesAPIChooseImageChooseImage : BasePage {
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
        onPageHide(fun() {
            console.log("Page Hide", " at pages/API/choose-image/choose-image.uvue:163")
        }
        , __ins)
        onUnload(fun() {
            this.imageList = utsArrayOf()
            this.sourceTypeIndex = 2
            this.sourceType = utsArrayOf(
                "拍照",
                "相册",
                "拍照或相册"
            )
            this.sizeTypeIndex = 2
            this.sizeType = utsArrayOf(
                "压缩",
                "原图",
                "压缩或原图"
            )
            this.orientationTypeIndex = 0
            this.orientationType = utsArrayOf(
                "竖屏",
                "横屏",
                "自动"
            )
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        val _component_switch = resolveComponent("switch")
        return createElementVNode("scroll-view", utsMapOf("class" to "page-scroll-view"), utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                "title"
            )),
            createElementVNode("view", utsMapOf("class" to "uni-common-mt"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "uni-list"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell cell-pd"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-list-cell-left uni-label"), " 图片来源 "),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-right", "onClick" to _ctx.chooseImageSource), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "click-t"), toDisplayString(_ctx.sourceType[_ctx.sourceTypeIndex]), 1)
                        ), 8, utsArrayOf(
                            "onClick"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell cell-pd"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-list-cell-left uni-label"), " 图片质量 "),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-right", "onClick" to _ctx.chooseImageType), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "click-t"), toDisplayString(_ctx.sizeType[_ctx.sizeTypeIndex]), 1)
                        ), 8, utsArrayOf(
                            "onClick"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell cell-pd"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-list-cell-left uni-label"), " 数量限制 "),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-right"), utsArrayOf(
                            createElementVNode("input", utsMapOf("class" to "click-t", "value" to _ctx.count, "type" to "number", "maxlength" to 1, "onBlur" to _ctx.chooseImageCount), null, 40, utsArrayOf(
                                "value",
                                "onBlur"
                            ))
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell cell-pd"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-list-cell-left uni-label"), " 屏幕方向 "),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-right", "onClick" to _ctx.chooseOrientationType), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "click-t"), toDisplayString(_ctx.orientationType[_ctx.orientationTypeIndex]), 1)
                        ), 8, utsArrayOf(
                            "onClick"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell cell-pd"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-list-cell-left uni-label"), " 相册模式 "),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-right", "onClick" to _ctx.albumModeChange), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "click-t"), toDisplayString(_ctx.albumModeType[_ctx.albumModeTypeIndex]), 1)
                        ), 8, utsArrayOf(
                            "onClick"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell cell-pd"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-list-cell-left uni-label"), " 图像裁剪 "),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-right"), utsArrayOf(
                            createVNode(_component_switch, utsMapOf("checked" to _ctx.isCrop, "onChange" to _ctx.switchCrop), null, 8, utsArrayOf(
                                "checked",
                                "onChange"
                            ))
                        ))
                    )),
                    createElementVNode("view", utsMapOf("ref" to "cropOptionNode", "class" to "crop-option", "style" to normalizeStyle(utsMapOf("height" to if (_ctx.isCrop) {
                        "200px"
                    } else {
                        "0px"
                    }
                    , "margin-bottom" to if (_ctx.isCrop) {
                        "11px"
                    } else {
                        "0px"
                    }
                    ))), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell cell-pd"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-list-cell-left item_width"), " 图片质量(%) "),
                            createElementVNode("view", utsMapOf("class" to "uni-list-cell-right"), utsArrayOf(
                                createElementVNode("input", utsMapOf("value" to _ctx.cropPercent, "onConfirm" to _ctx.cropPercentConfim, "type" to "number", "maxlength" to "-1"), null, 40, utsArrayOf(
                                    "value",
                                    "onConfirm"
                                ))
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell cell-pd"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-list-cell-left item_width"), " 裁剪宽度(px) "),
                            createElementVNode("view", utsMapOf("class" to "uni-list-cell-right"), utsArrayOf(
                                createElementVNode("input", utsMapOf("value" to _ctx.cropWidth, "onConfirm" to _ctx.cropWidthConfim, "type" to "number", "maxlength" to "-1"), null, 40, utsArrayOf(
                                    "value",
                                    "onConfirm"
                                ))
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell cell-pd"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-list-cell-left item_width"), " 裁剪高度(px) "),
                            createElementVNode("view", utsMapOf("class" to "uni-list-cell-right"), utsArrayOf(
                                createElementVNode("input", utsMapOf("value" to _ctx.cropHeight, "onConfirm" to _ctx.cropHeightConfim, "type" to "number", "maxlength" to "-1"), null, 40, utsArrayOf(
                                    "value",
                                    "onConfirm"
                                ))
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell cell-pd"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-list-cell-left item_width"), " 保留原宽高 "),
                            createElementVNode("view", utsMapOf("class" to "uni-list-cell-right"), utsArrayOf(
                                createVNode(_component_switch, utsMapOf("checked" to _ctx.cropResize, "onChange" to _ctx.cropResizeChange), null, 8, utsArrayOf(
                                    "checked",
                                    "onChange"
                                ))
                            ))
                        ))
                    ), 4)
                )),
                createElementVNode("view", utsMapOf("class" to "uni-list list-pd", "style" to normalizeStyle(utsMapOf("padding" to "15px"))), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-flex", "style" to normalizeStyle(utsMapOf("margin-bottom" to "10px"))), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-left"), "点击可预览选好的图片"),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("margin-left" to "auto"))), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "click-t"), toDisplayString(_ctx.imageList.length) + "/" + toDisplayString(_ctx.count), 1)
                        ), 4)
                    ), 4),
                    createElementVNode("view", utsMapOf("class" to "uni-flex", "style" to normalizeStyle(utsMapOf("flex-wrap" to "wrap"))), utsArrayOf(
                        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.imageList, fun(image, index, __index, _cached): Any {
                            return createElementVNode("view", utsMapOf("key" to index, "class" to "uni-uploader__input-box", "style" to normalizeStyle(utsMapOf("border" to "0"))), utsArrayOf(
                                createElementVNode("image", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "104px", "height" to "104px")), "src" to image, "data-src" to image, "onClick" to fun(){
                                    _ctx.previewImage(index)
                                }
                                ), null, 12, utsArrayOf(
                                    "src",
                                    "data-src",
                                    "onClick"
                                )),
                                createElementVNode("image", utsMapOf("src" to "/static/plus.png", "class" to "image-remove", "onClick" to fun(){
                                    _ctx.removeImage(index)
                                }
                                ), null, 8, utsArrayOf(
                                    "onClick"
                                ))
                            ), 4)
                        }
                        ), 128),
                        createElementVNode("image", utsMapOf("class" to "uni-uploader__input-box", "onClick" to _ctx.chooseImage, "src" to "/static/plus.png"), null, 8, utsArrayOf(
                            "onClick"
                        ))
                    ), 4)
                ), 4)
            ))
        ))
    }
    open var title: String by `$data`
    open var imageList: UTSArray<String> by `$data`
    open var sourceTypeIndex: Number by `$data`
    open var sourceType: UTSArray<String> by `$data`
    open var sizeTypeIndex: Number by `$data`
    open var sizeType: UTSArray<String> by `$data`
    open var orientationTypeIndex: Number by `$data`
    open var orientationType: UTSArray<String> by `$data`
    open var albumModeTypeIndex: Number by `$data`
    open var albumModeType: UTSArray<String> by `$data`
    open var count: Number by `$data`
    open var isCrop: Boolean by `$data`
    open var cropPercent: Number by `$data`
    open var cropWidth: Number by `$data`
    open var cropHeight: Number by `$data`
    open var cropResize: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "chooseImage", "imageList" to utsArrayOf<String>(), "sourceTypeIndex" to 2, "sourceType" to utsArrayOf(
            "拍照",
            "相册",
            "拍照或相册"
        ), "sizeTypeIndex" to 2, "sizeType" to utsArrayOf(
            "压缩",
            "原图",
            "压缩或原图"
        ), "orientationTypeIndex" to 0, "orientationType" to utsArrayOf(
            "竖屏",
            "横屏",
            "自动"
        ), "albumModeTypeIndex" to 0, "albumModeType" to utsArrayOf(
            "自定义相册",
            "系统相册"
        ), "count" to 9, "isCrop" to false, "cropPercent" to 80, "cropWidth" to 100, "cropHeight" to 100, "cropResize" to false)
    }
    open var cropHeightConfim = ::gen_cropHeightConfim_fn
    open fun gen_cropHeightConfim_fn(e: InputConfirmEvent) {
        var value = parseInt(e.detail.value)
        if (value > 0) {
            this.cropHeight = value
        } else {
            uni_showToast(ShowToastOptions(position = "bottom", title = "裁剪高度需要大于0"))
        }
    }
    open var cropWidthConfim = ::gen_cropWidthConfim_fn
    open fun gen_cropWidthConfim_fn(e: InputConfirmEvent) {
        var value = parseInt(e.detail.value)
        if (value > 0) {
            this.cropWidth = value
        } else {
            uni_showToast(ShowToastOptions(position = "bottom", title = "裁剪宽度需要大于0"))
        }
    }
    open var cropPercentConfim = ::gen_cropPercentConfim_fn
    open fun gen_cropPercentConfim_fn(e: InputConfirmEvent) {
        var value = parseInt(e.detail.value)
        if (value > 0 && value <= 100) {
            this.cropPercent = value
        } else {
            uni_showToast(ShowToastOptions(position = "bottom", title = "请输入0~100之间的值"))
        }
    }
    open var albumModeChange = ::gen_albumModeChange_fn
    open fun gen_albumModeChange_fn() {
        uni_showActionSheet(ShowActionSheetOptions(itemList = this.albumModeType, success = fun(e){
            this.albumModeTypeIndex = e.tapIndex
        }
        ))
    }
    open var cropResizeChange = ::gen_cropResizeChange_fn
    open fun gen_cropResizeChange_fn(e: UniSwitchChangeEvent) {
        this.cropResize = e.detail.value
    }
    open var switchCrop = ::gen_switchCrop_fn
    open fun gen_switchCrop_fn(e: UniSwitchChangeEvent) {
        this.isCrop = e.detail.value
    }
    open var removeImage = ::gen_removeImage_fn
    open fun gen_removeImage_fn(index: Number) {
        this.imageList.splice(index, 1)
    }
    open var chooseImageSource = ::gen_chooseImageSource_fn
    open fun gen_chooseImageSource_fn() {
        uni_showActionSheet(ShowActionSheetOptions(itemList = utsArrayOf(
            "拍照",
            "相册",
            "拍照或相册"
        ), success = fun(e){
            this.sourceTypeIndex = e.tapIndex
        }
        ))
    }
    open var chooseImageType = ::gen_chooseImageType_fn
    open fun gen_chooseImageType_fn() {
        uni_showActionSheet(ShowActionSheetOptions(itemList = utsArrayOf(
            "压缩",
            "原图",
            "压缩或原图"
        ), success = fun(e){
            this.sizeTypeIndex = e.tapIndex
        }
        ))
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
    open var chooseImageCount = ::gen_chooseImageCount_fn
    open fun gen_chooseImageCount_fn(event: InputBlurEvent) {
        var count = parseInt(event.detail.value)
        if (count < 0) {
            uni_showToast(ShowToastOptions(position = "bottom", title = "图片数量应该大于0"))
            return
        }
        this.count = count
    }
    open var chooseImage = ::gen_chooseImage_fn
    open fun gen_chooseImage_fn() {
        if (this.imageList.length >= this.count) {
            uni_showToast(ShowToastOptions(position = "bottom", title = "\u5DF2\u7ECF\u6709 " + this.count + " \u5F20\u56FE\u7247\u4E86\uFF0C\u8BF7\u5220\u9664\u90E8\u5206\u56FE\u7247\u4E4B\u540E\u91CD\u65B0\u9009\u62E9"))
            return
        }
        uni_chooseImage(ChooseImageOptions(sourceType = sourceTypeArray[this.sourceTypeIndex], sizeType = sizeTypeArray[this.sizeTypeIndex], crop = if (this.isCrop) {
            ChooseImageCropOptions(quality = this.cropPercent, width = this.cropWidth, height = this.cropHeight, resize = this.cropResize)
        } else {
            null
        }
        , count = this.count - this.imageList.length, pageOrientation = orientationTypeArray[this.orientationTypeIndex], albumMode = albumModeTypeArray[this.albumModeTypeIndex], success = fun(res){
            this.imageList = this.imageList.concat(res.tempFilePaths)
            console.log("this.imageList: ", this.imageList, " at pages/API/choose-image/choose-image.uvue:281")
        }
        , fail = fun(err){
            console.log("err: ", JSON.stringify(err), " at pages/API/choose-image/choose-image.uvue:284")
            uni_showToast(ShowToastOptions(title = "choose image error.code:" + err.errCode + ";message:" + err.errMsg, position = "bottom"))
        }
        ))
    }
    open var previewImage = ::gen_previewImage_fn
    open fun gen_previewImage_fn(index: Number) {
        uni_previewImage(PreviewImageOptions(current = index, urls = this.imageList))
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
                return utsMapOf("cell-pd" to padStyleMapOf(utsMapOf("paddingTop" to 11, "paddingRight" to 15, "paddingBottom" to 11, "paddingLeft" to 15)), "click-t" to padStyleMapOf(utsMapOf("color" to "#A9A9A9")), "list-pd" to padStyleMapOf(utsMapOf("marginTop" to 25)), "uni-uploader__input-box" to padStyleMapOf(utsMapOf("marginTop" to 5, "marginRight" to 5, "marginBottom" to 5, "marginLeft" to 5, "width" to 104, "height" to 104, "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#D9D9D9", "borderRightColor" to "#D9D9D9", "borderBottomColor" to "#D9D9D9", "borderLeftColor" to "#D9D9D9")), "uni-uploader__input" to padStyleMapOf(utsMapOf("position" to "absolute", "zIndex" to 1, "top" to 0, "left" to 0, "width" to "100%", "height" to "100%", "opacity" to 0)), "image-remove" to padStyleMapOf(utsMapOf("transform" to "rotate(45deg)", "width" to 25, "height" to 25, "position" to "absolute", "top" to 0, "right" to 0, "borderTopLeftRadius" to 13, "borderTopRightRadius" to 13, "borderBottomRightRadius" to 13, "borderBottomLeftRadius" to 13, "backgroundColor" to "rgba(200,200,200,0.8)")), "item_width" to padStyleMapOf(utsMapOf("width" to 130)), "crop-option" to padStyleMapOf(utsMapOf("marginLeft" to 11, "marginRight" to 11, "borderTopLeftRadius" to 11, "borderTopRightRadius" to 11, "borderBottomRightRadius" to 11, "borderBottomLeftRadius" to 11, "backgroundColor" to "#eeeeee", "transitionProperty" to "height,marginBottom", "transitionDuration" to "200ms")), "@TRANSITION" to utsMapOf("crop-option" to utsMapOf("property" to "height,marginBottom", "duration" to "200ms")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
