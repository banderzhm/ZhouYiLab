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
import io.dcloud.uniapp.extapi.chooseVideo as uni_chooseVideo
import io.dcloud.uniapp.extapi.getElementById as uni_getElementById
import io.dcloud.uniapp.extapi.hideToast as uni_hideToast
import io.dcloud.uniapp.extapi.shareWithSystem as uni_shareWithSystem
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesAPIShareWithSystemShareWithSystem : BasePage {
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
        return createElementVNode("scroll-view", utsMapOf("direction" to "vertical", "style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("view", utsMapOf("id" to "viewshot"), utsArrayOf(
                createElementVNode("button", utsMapOf("class" to "button", "onClick" to fun(){
                    _ctx.shareText()
                }
                ), "分享文本", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "button", "onClick" to fun(){
                    _ctx.shareLink()
                }
                ), "分享链接", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "button", "onClick" to fun(){
                    _ctx.sharePrivateImg()
                }
                ), "分享单个本地图片", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "button", "onClick" to fun(){
                    _ctx.sharePrivateImgs()
                }
                ), "分享多个本地图片", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "button", "onClick" to fun(){
                    _ctx.shareAll()
                }
                ), "分享链接、文本、一张图片", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "button", "onClick" to fun(){
                    _ctx.sharePrivateVideo()
                }
                ), "分享video文件(单个)", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "button", "onClick" to fun(){
                    _ctx.sharePrivateVideos()
                }
                ), "分享video文件(多个)", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "button", "onClick" to fun(){
                    _ctx.sharePrivateAudio()
                }
                ), "分享Audio文件(单个)", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "button", "onClick" to fun(){
                    _ctx.sharePrivateAudios()
                }
                ), "分享Audio文件(多个)", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "button", "onClick" to fun(){
                    _ctx.sharePrivateFile()
                }
                ), "分享文件(单个)", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "button", "onClick" to fun(){
                    _ctx.sharePrivateFiles()
                }
                ), "分享文件(多个)", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "button", "onClick" to fun(){
                    _ctx.sharePubImg()
                }
                ), "选择图片并分享", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "button", "onClick" to fun(){
                    _ctx.sharePubMedias()
                }
                ), "选择video并分享", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "button", "onClick" to fun(){
                    _ctx.shareSnapShot()
                }
                ), "指定view截图并分享", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "button", "type" to "warn", "onClick" to fun(){
                    _ctx.sharePrivateErrorImg()
                }
                ), "分享单个本地图片(错误路径)", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "button", "type" to "warn", "onClick" to fun(){
                    _ctx.sharePrivateErrorImgs()
                }
                ), "分享多个本地图片(含有错误路径)", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "button", "type" to "warn", "onClick" to fun(){
                    _ctx.sharePrivateErrorVideos()
                }
                ), "分享Video文件(错误路径)", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "button", "type" to "warn", "onClick" to fun(){
                    _ctx.sharePrivateErrorAudios()
                }
                ), "分享Audio文件(错误路径)", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "button", "type" to "warn", "onClick" to fun(){
                    _ctx.sharePrivateErrorFiles()
                }
                ), "分享文件(错误路径)", 8, utsArrayOf(
                    "onClick"
                ))
            ))
        ), 4)
    }
    open var summary: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("summary" to "欢迎使用hello uniapp-x")
    }
    open var sharePrivateErrorAudios = ::gen_sharePrivateErrorAudios_fn
    open fun gen_sharePrivateErrorAudios_fn() {
        uni_hideToast()
        val path1: String = "/static/test-audio/ForElise.mp3"
        val path2: String = "/static/test-audio/ForElise.mp32"
        uni_shareWithSystem(ShareWithSystemOptions(audioPaths = utsArrayOf(
            path1,
            path2
        ), type = "audio", success = fun(_) {
            console.log("Shared----------------------------success", " at pages/API/share-with-system/share-with-system.uvue:49")
        }
        , fail = fun(res) {
            console.log("Share failed, res.errCode =" + res.errCode + "---res.errMsg= " + res.errMsg, " at pages/API/share-with-system/share-with-system.uvue:52")
            uni_showToast(ShowToastOptions(icon = "error", title = "errorCode=" + res.errCode))
        }
        , complete = fun(_) {}))
    }
    open var sharePrivateErrorFiles = ::gen_sharePrivateErrorFiles_fn
    open fun gen_sharePrivateErrorFiles_fn() {
        uni_hideToast()
        val path1: String = "/static/filemanager1/to.zip"
        val path2: String = "/static/filemanager/11.txt.br"
        uni_shareWithSystem(ShareWithSystemOptions(filePaths = utsArrayOf(
            path1,
            path2
        ), type = "file", success = fun(_) {
            console.log("Shared----------------------------success", " at pages/API/share-with-system/share-with-system.uvue:73")
        }
        , fail = fun(res) {
            console.log("Share failed, res.errCode =" + res.errCode + "---res.errMsg= " + res.errMsg, " at pages/API/share-with-system/share-with-system.uvue:76")
            uni_showToast(ShowToastOptions(icon = "error", title = "errorCode=" + res.errCode))
        }
        , complete = fun(_) {}))
    }
    open var sharePrivateFile = ::gen_sharePrivateFile_fn
    open fun gen_sharePrivateFile_fn() {
        uni_hideToast()
        val path1: String = "/static/filemanager/to.zip"
        uni_shareWithSystem(ShareWithSystemOptions(filePaths = utsArrayOf(
            path1
        ), type = "file", success = fun(_) {
            console.log("Shared----------------------------success", " at pages/API/share-with-system/share-with-system.uvue:95")
        }
        , fail = fun(res) {
            console.log("Share failed, res.errCode =" + res.errCode + "---res.errMsg= " + res.errMsg, " at pages/API/share-with-system/share-with-system.uvue:98")
            uni_showToast(ShowToastOptions(icon = "error", title = "errorCode=" + res.errCode))
        }
        , complete = fun(_) {}))
    }
    open var sharePrivateFiles = ::gen_sharePrivateFiles_fn
    open fun gen_sharePrivateFiles_fn() {
        uni_hideToast()
        val path1: String = "/static/filemanager/to.zip"
        val path2: String = "/static/filemanager/1.txt.br"
        uni_shareWithSystem(ShareWithSystemOptions(filePaths = utsArrayOf(
            path1,
            path2
        ), type = "file", success = fun(_) {
            console.log("Shared----------------------------success", " at pages/API/share-with-system/share-with-system.uvue:118")
        }
        , fail = fun(res) {
            console.log("Share failed, res.errCode =" + res.errCode + "---res.errMsg= " + res.errMsg, " at pages/API/share-with-system/share-with-system.uvue:121")
            uni_showToast(ShowToastOptions(icon = "error", title = "errorCode=" + res.errCode))
        }
        , complete = fun(_) {}))
    }
    open var sharePrivateAudio = ::gen_sharePrivateAudio_fn
    open fun gen_sharePrivateAudio_fn() {
        uni_hideToast()
        val path1: String = "/static/test-audio/ForElise.mp3"
        uni_shareWithSystem(ShareWithSystemOptions(audioPaths = utsArrayOf(
            path1
        ), type = "audio", success = fun(_) {
            console.log("Shared----------------------------success", " at pages/API/share-with-system/share-with-system.uvue:140")
        }
        , fail = fun(res) {
            console.log("Share failed, res.errCode =" + res.errCode + "---res.errMsg= " + res.errMsg, " at pages/API/share-with-system/share-with-system.uvue:143")
            uni_showToast(ShowToastOptions(icon = "error", title = "errorCode=" + res.errCode))
        }
        , complete = fun(_) {}))
    }
    open var sharePrivateAudios = ::gen_sharePrivateAudios_fn
    open fun gen_sharePrivateAudios_fn() {
        uni_hideToast()
        val path1: String = "/static/test-audio/ForElise.mp3"
        val path2: String = "/static/test-audio/ForElise.mp3"
        uni_shareWithSystem(ShareWithSystemOptions(audioPaths = utsArrayOf(
            path1,
            path2
        ), type = "audio", success = fun(_) {
            console.log("Shared----------------------------success", " at pages/API/share-with-system/share-with-system.uvue:163")
        }
        , fail = fun(res) {
            console.log("Share failed, res.errCode =" + res.errCode + "---res.errMsg= " + res.errMsg, " at pages/API/share-with-system/share-with-system.uvue:166")
            uni_showToast(ShowToastOptions(icon = "error", title = "errorCode=" + res.errCode))
        }
        , complete = fun(_) {}))
    }
    open var sharePrivateErrorVideos = ::gen_sharePrivateErrorVideos_fn
    open fun gen_sharePrivateErrorVideos_fn() {
        uni_hideToast()
        val path1: String = "/static/test-video/10second-demo.mp4"
        val path2: String = "/static/test-video/10second-demo1.mp4"
        uni_shareWithSystem(ShareWithSystemOptions(videoPaths = utsArrayOf(
            path1,
            path2
        ), type = "video", success = fun(_) {
            console.log("Shared----------------------------success", " at pages/API/share-with-system/share-with-system.uvue:186")
        }
        , fail = fun(res) {
            console.log("Share failed, res.errCode =" + res.errCode + "---res.errMsg= " + res.errMsg, " at pages/API/share-with-system/share-with-system.uvue:189")
            uni_showToast(ShowToastOptions(icon = "error", title = "errorCode=" + res.errCode))
        }
        , complete = fun(_) {}))
    }
    open var sharePrivateVideo = ::gen_sharePrivateVideo_fn
    open fun gen_sharePrivateVideo_fn() {
        uni_hideToast()
        val path1: String = "/static/test-video/10second-demo.mp4"
        uni_shareWithSystem(ShareWithSystemOptions(videoPaths = utsArrayOf(
            path1
        ), type = "video", success = fun(_) {
            console.log("Shared----------------------------success", " at pages/API/share-with-system/share-with-system.uvue:208")
        }
        , fail = fun(res) {
            console.log("Share failed, res.errCode =" + res.errCode + "---res.errMsg= " + res.errMsg, " at pages/API/share-with-system/share-with-system.uvue:211")
            uni_showToast(ShowToastOptions(icon = "error", title = "errorCode=" + res.errCode))
        }
        , complete = fun(_) {}))
    }
    open var sharePrivateVideos = ::gen_sharePrivateVideos_fn
    open fun gen_sharePrivateVideos_fn() {
        uni_hideToast()
        val path1: String = "/static/test-video/10second-demo.mp4"
        val path2: String = "/static/test-video/10second-demo.mp4"
        uni_shareWithSystem(ShareWithSystemOptions(videoPaths = utsArrayOf(
            path1,
            path2
        ), type = "video", success = fun(_) {
            console.log("Shared----------------------------success", " at pages/API/share-with-system/share-with-system.uvue:232")
        }
        , fail = fun(res) {
            console.log("Share failed, res.errCode =" + res.errCode + "---res.errMsg= " + res.errMsg, " at pages/API/share-with-system/share-with-system.uvue:235")
            uni_showToast(ShowToastOptions(icon = "error", title = "errorCode=" + res.errCode))
        }
        , complete = fun(_) {}))
    }
    open var sharePubMedias = ::gen_sharePubMedias_fn
    open fun gen_sharePubMedias_fn() {
        uni_hideToast()
        uni_chooseVideo(ChooseVideoOptions(success = fun(res) {
            uni_shareWithSystem(ShareWithSystemOptions(videoPaths = utsArrayOf(
                res.tempFilePath
            ), type = "video", success = fun(_) {
                console.log("Shared----------------------------success", " at pages/API/share-with-system/share-with-system.uvue:256")
            }
            , fail = fun(res) {
                console.log("Share failed, res.errCode =" + res.errCode + "---res.errMsg= " + res.errMsg, " at pages/API/share-with-system/share-with-system.uvue:259")
                uni_showToast(ShowToastOptions(icon = "error", title = "errorCode=" + res.errCode))
            }
            , complete = fun(_) {}))
        }
        ))
    }
    open var shareText = ::gen_shareText_fn
    open fun gen_shareText_fn() {
        uni_hideToast()
        uni_shareWithSystem(ShareWithSystemOptions(summary = this.summary, type = "text", success = fun(_) {
            console.log("Shared----------------------------success", " at pages/API/share-with-system/share-with-system.uvue:279")
        }
        , fail = fun(res) {
            console.log("Share failed, res.errCode =" + res.errCode + "---res.errMsg= " + res.errMsg, " at pages/API/share-with-system/share-with-system.uvue:282")
            uni_showToast(ShowToastOptions(icon = "error", title = "errorCode=" + res.errCode))
        }
        , complete = fun(_) {}))
    }
    open var shareLink = ::gen_shareLink_fn
    open fun gen_shareLink_fn() {
        uni_hideToast()
        uni_shareWithSystem(ShareWithSystemOptions(type = "text", href = "https://uniapp.dcloud.io", success = fun(_) {
            console.log("Shared----------------------------success", " at pages/API/share-with-system/share-with-system.uvue:299")
        }
        , fail = fun(res) {
            console.log("Share failed, res.errCode =" + res.errCode + "---res.errMsg= " + res.errMsg, " at pages/API/share-with-system/share-with-system.uvue:302")
            uni_showToast(ShowToastOptions(icon = "error", title = "errorCode=" + res.errCode))
        }
        , complete = fun(_) {}))
    }
    open var sharePrivateImg = ::gen_sharePrivateImg_fn
    open fun gen_sharePrivateImg_fn() {
        uni_hideToast()
        val imageSrc: String = "/static/test-image/logo.gif"
        uni_shareWithSystem(ShareWithSystemOptions(type = "image", imageUrl = imageSrc, success = fun(_) {
            console.log("Shared----------------------------success", " at pages/API/share-with-system/share-with-system.uvue:320")
        }
        , fail = fun(res) {
            console.log("Share failed, res.errCode =" + res.errCode + "---res.errMsg= " + res.errMsg, " at pages/API/share-with-system/share-with-system.uvue:324")
            uni_showToast(ShowToastOptions(icon = "error", title = "errorCode=" + res.errCode))
        }
        , complete = fun(_) {}))
    }
    open var sharePrivateErrorImg = ::gen_sharePrivateErrorImg_fn
    open fun gen_sharePrivateErrorImg_fn() {
        uni_hideToast()
        val imageSrc: String = "/static/test-image/logo.jpg11"
        uni_shareWithSystem(ShareWithSystemOptions(imageUrl = imageSrc, type = "image", success = fun(_) {
            console.log("Shared----------------------------success", " at pages/API/share-with-system/share-with-system.uvue:343")
        }
        , fail = fun(res) {
            console.log("Share failed, res.errCode =" + res.errCode + "---res.errMsg= " + res.errMsg, " at pages/API/share-with-system/share-with-system.uvue:347")
            uni_showToast(ShowToastOptions(icon = "error", title = "errorCode=" + res.errCode))
        }
        , complete = fun(_) {}))
    }
    open var sharePrivateImgs = ::gen_sharePrivateImgs_fn
    open fun gen_sharePrivateImgs_fn() {
        uni_hideToast()
        val errorImageSrc2: String = "/static/test-image/logo.png"
        val imageSrc: String = "/static/test-image/logo.jpg"
        var imageUrlList: UTSArray<String> = UTSArray()
        imageUrlList.push(errorImageSrc2)
        imageUrlList.push(imageSrc)
        uni_shareWithSystem(ShareWithSystemOptions(imagePaths = imageUrlList, type = "image", success = fun(_) {
            console.log("Shared----------------------------success", " at pages/API/share-with-system/share-with-system.uvue:373")
        }
        , fail = fun(res) {
            console.log("Share failed, res.errCode =" + res.errCode + "---res.errMsg= " + res.errMsg, " at pages/API/share-with-system/share-with-system.uvue:377")
            uni_showToast(ShowToastOptions(icon = "error", title = "errorCode=" + res.errCode))
        }
        , complete = fun(_) {}))
    }
    open var sharePrivateErrorImgs = ::gen_sharePrivateErrorImgs_fn
    open fun gen_sharePrivateErrorImgs_fn() {
        uni_hideToast()
        val errorImageSrc1: String = "/static/test-image/logo.jpg1"
        val errorImageSrc2: String = "/static/test-image/logo.jpg3"
        val imageSrc: String = "/static/test-image/logo.jpg"
        var imageUrlList: UTSArray<String> = UTSArray()
        imageUrlList.push(imageSrc)
        imageUrlList.push(errorImageSrc1)
        imageUrlList.push(errorImageSrc2)
        uni_shareWithSystem(ShareWithSystemOptions(imagePaths = imageUrlList, type = "image", success = fun(_) {
            console.log("Shared----------------------------success", " at pages/API/share-with-system/share-with-system.uvue:401")
        }
        , fail = fun(res) {
            console.log("Share failed, res.errCode =" + res.errCode + "---res.errMsg= " + res.errMsg, " at pages/API/share-with-system/share-with-system.uvue:405")
            uni_showToast(ShowToastOptions(icon = "error", title = "errorCode=" + res.errCode))
        }
        , complete = fun(_) {}))
    }
    open var shareAll = ::gen_shareAll_fn
    open fun gen_shareAll_fn() {
        uni_hideToast()
        val imageSrc: String = "/static/test-video/fast-forward.png"
        var imageUrlList: UTSArray<String> = UTSArray()
        imageUrlList.push(imageSrc)
        uni_shareWithSystem(ShareWithSystemOptions(summary = this.summary, href = "https://uniapp.dcloud.io", imagePaths = imageUrlList, type = "image", success = fun(_) {
            console.log("Shared----------------------------success", " at pages/API/share-with-system/share-with-system.uvue:427")
        }
        , fail = fun(res) {
            console.log("Share failed, res.errCode =" + res.errCode + "---res.errMsg= " + res.errMsg, " at pages/API/share-with-system/share-with-system.uvue:431")
            uni_showToast(ShowToastOptions(icon = "error", title = "errorCode=" + res.errCode))
        }
        , complete = fun(_) {}))
    }
    open var sharePubImg = ::gen_sharePubImg_fn
    open fun gen_sharePubImg_fn() {
        uni_hideToast()
        uni_chooseImage(ChooseImageOptions(count = 3, sourceType = utsArrayOf(
            "camera",
            "album"
        ), success = fun(e) {
            console.log(e, " at pages/API/share-with-system/share-with-system.uvue:448")
            console.log(JSON.stringify(e), " at pages/API/share-with-system/share-with-system.uvue:449")
            uni_shareWithSystem(ShareWithSystemOptions(imagePaths = e.tempFilePaths, type = "image", success = fun(_) {
                console.log("Shared----------------------------success", " at pages/API/share-with-system/share-with-system.uvue:456")
            }
            , fail = fun(res) {
                console.log("Share failed, res.errCode =" + res.errCode + "---res.errMsg= " + res.errMsg, " at pages/API/share-with-system/share-with-system.uvue:460")
                uni_showToast(ShowToastOptions(icon = "error", title = "errorCode=" + res.errCode))
            }
            , complete = fun(_) {}))
        }
        ))
    }
    open var shareSnapShot = ::gen_shareSnapShot_fn
    open fun gen_shareSnapShot_fn() {
        uni_hideToast()
        uni_getElementById("viewshot")?.takeSnapshot(TakeSnapshotOptions(success = fun(res) {
            uni_shareWithSystem(ShareWithSystemOptions(imageUrl = res.tempFilePath, type = "image", success = fun(_) {
                console.log("Shared----------------------------success", " at pages/API/share-with-system/share-with-system.uvue:483")
            }
            , fail = fun(res) {
                console.log("Share failed, res.errCode =" + res.errCode + "---res.errMsg= " + res.errMsg, " at pages/API/share-with-system/share-with-system.uvue:487")
                uni_showToast(ShowToastOptions(icon = "error", title = "errorCode=" + res.errCode))
            }
            , complete = fun(_) {}))
        }
        , fail = fun(res) {
            console.log(res, " at pages/API/share-with-system/share-with-system.uvue:501")
            uni_showToast(ShowToastOptions(icon = "error", title = "截图失败"))
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
                return utsMapOf("button" to padStyleMapOf(utsMapOf("marginLeft" to 30, "marginRight" to 30, "marginBottom" to 15)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
