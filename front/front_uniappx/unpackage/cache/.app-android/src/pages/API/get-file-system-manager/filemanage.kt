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
import io.dcloud.uniapp.extapi.getElementById as uni_getElementById
import io.dcloud.uniapp.extapi.getFileSystemManager as uni_getFileSystemManager
import io.dcloud.uniapp.extapi.openDocument as uni_openDocument
import io.dcloud.uniapp.extapi.previewImage as uni_previewImage
import io.dcloud.uniapp.extapi.showModal as uni_showModal
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesAPIGetFileSystemManagerFilemanage : BasePage {
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
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesAPIGetFileSystemManagerFilemanage) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesAPIGetFileSystemManagerFilemanage
            val _cache = __ins.renderCache
            var fileList = ref(utsArrayOf<fileListType>())
            var count = ref(0)
            var size = ref(0)
            var rootPath = ref("")
            var dialogShow = ref(false)
            val uniIcon = ref("\uE661")
            var currentFile = ref(fileListType(path = "", shotPath = "", size = 0, isFile = false, modifyTime = ""))
            var cacheOrData = ref(uni_env.USER_DATA_PATH)
            val switchCacheOrData = fun(e: UniRadioGroupChangeEvent){
                cacheOrData.value = if ((e.detail.value == "cache")) {
                    uni_env.CACHE_PATH
                } else {
                    uni_env.USER_DATA_PATH
                }
            }
            val refreshFile = fun(){
                val fileManager = uni_getFileSystemManager()
                fileManager.stat(StatOptions(path = cacheOrData.value, recursive = true, success = fun(res: StatSuccessResult){
                    console.log("statFileInfo success1", " at pages/API/get-file-system-manager/filemanage.uvue:79")
                    console.log("res.stats", res.stats, " at pages/API/get-file-system-manager/filemanage.uvue:80")
                    console.log("uni.env.CACHE_PATH: ", uni_env.CACHE_PATH, " at pages/API/get-file-system-manager/filemanage.uvue:81")
                    console.log("uni.env.USER_DATA_PATH: ", uni_env.USER_DATA_PATH, " at pages/API/get-file-system-manager/filemanage.uvue:82")
                    console.log("uni.env.SANDBOX_PATH: ", uni_env.SANDBOX_PATH, " at pages/API/get-file-system-manager/filemanage.uvue:83")
                    var tempFileList = utsArrayOf<fileListType>()
                    var tempSize: Number = 0
                    run {
                        var i: Number = 0
                        while(i < res.stats.length){
                            console.log("resitem", res.stats[i].path, " at pages/API/get-file-system-manager/filemanage.uvue:87")
                            if (i == 0) {
                                rootPath.value = res.stats[i].path
                                i++
                                continue
                            }
                            var tempFileItem = fileListType(path = "", shotPath = "", size = 0, isFile = false, modifyTime = "")
                            tempFileItem.shotPath = res.stats[i].path.replace(rootPath.value, "")
                            tempFileItem.path = res.stats[i].path
                            tempFileItem.size = res.stats[i].stats.size / 1024
                            tempFileItem.isFile = res.stats[i].stats.isFile()
                            val mdate = Date(res.stats[i].stats.lastModifiedTime * 1000)
                            tempFileItem.modifyTime = mdate.getFullYear().toString(10) + "-" + (mdate.getMonth() + 1).toString(10) + "-" + mdate.getDate().toString(10) + " " + mdate.getHours().toString(10) + ":" + mdate.getMinutes().toString(10) + ":" + mdate.getSeconds().toString(10)
                            tempSize = tempSize + res.stats[i].stats.size
                            tempFileList.push(tempFileItem)
                            i++
                        }
                    }
                    fileList.value = tempFileList
                    size.value = tempSize / 1024 / 1024
                    count.value = tempFileList.length
                }
                , fail = fun(res: IUniError){
                    uni_showModal(ShowModalOptions(title = "获取文件状态失败", content = res.errMsg, showCancel = false))
                    console.error("statFileInfo fail", res, " at pages/API/get-file-system-manager/filemanage.uvue:120")
                }
                , complete = fun(res: Any){
                    console.log("statFileInfo complete", res, " at pages/API/get-file-system-manager/filemanage.uvue:123")
                }
                ))
            }
            val showDialog = fun(item: fileListType){
                currentFile.value = item
                dialogShow.value = true
            }
            val closeDialog = fun(){
                dialogShow.value = false
            }
            val deleteFile = fun(fileItem: fileListType){
                val fileManager = uni_getFileSystemManager()
                var path = cacheOrData.value + fileItem.path
                if (fileItem.isFile) {
                    fileManager.unlink(UnLinkOptions(filePath = path, success = fun(_res){
                        refreshFile()
                        closeDialog()
                    }, fail = fun(err: IFileSystemManagerFail){
                        uni_showModal(ShowModalOptions(title = "删除文件失败", content = err.errMsg, showCancel = false))
                    }))
                } else {
                    fileManager.rmdir(RmDirOptions(dirPath = path, recursive = true, success = fun(_res){
                        refreshFile()
                        closeDialog()
                    }
                    , fail = fun(err: IFileSystemManagerFail){
                        uni_showModal(ShowModalOptions(title = "删除目录失败", content = err.errMsg, showCancel = false))
                    }
                    ))
                }
            }
            val clearFile = fun(){
                val fileManager = uni_getFileSystemManager()
                fileManager.rmdir(RmDirOptions(dirPath = uni_env.CACHE_PATH, recursive = true, success = fun(res: FileManagerSuccessResult){
                    console.log("rmdir success", res, " at pages/API/get-file-system-manager/filemanage.uvue:180")
                    fileList.value = utsArrayOf<fileListType>()
                    size.value = 0
                    count.value = 0
                    uni_showToast(ShowToastOptions(title = "清空缓存目录成功", icon = "success"))
                }
                , fail = fun(res: IFileSystemManagerFail){
                    console.error("清空缓存目录失败", res, " at pages/API/get-file-system-manager/filemanage.uvue:190")
                    uni_showModal(ShowModalOptions(title = "清空缓存目录失败", content = res.errMsg, showCancel = false))
                }
                , complete = fun(res: Any){
                    console.log("rmdir complete", res, " at pages/API/get-file-system-manager/filemanage.uvue:198")
                }
                ))
            }
            val openFile = fun(fileItem: fileListType){
                var path = cacheOrData.value + fileItem.path
                var suffix = path.split(".").pop()
                when (suffix) {
                    "jpg", "png", "gif", "jpeg", "webp", "bmp", "ico", "heic", "heif", "tif" -> 
                        {
                            console.log("path:", path, " at pages/API/get-file-system-manager/filemanage.uvue:217")
                            uni_previewImage(PreviewImageOptions(urls = utsArrayOf(
                                path
                            )))
                        }
                    else -> 
                        uni_openDocument(OpenDocumentOptions(filePath = path, success = fun(res){
                            console.log("openDocument success", res, " at pages/API/get-file-system-manager/filemanage.uvue:227")
                        }
                        , fail = fun(res){
                            console.error("openDocument fail", res, " at pages/API/get-file-system-manager/filemanage.uvue:230")
                            uni_showModal(ShowModalOptions(title = "打开文件失败", content = res.errMsg, showCancel = false))
                        }
                        ))
                }
            }
            val snapshot = fun(){
                uni_getElementById("rootview")!!.takeSnapshot(TakeSnapshotOptions())
            }
            val getIndentStyle = fun(path: String): UTSJSONObject {
                val matches = path.match(UTSRegExp("\\/", "g"))
                var level: Number = 0
                if (matches != null) {
                    level = matches.length
                }
                return object : UTSJSONObject() {
                    var paddingLeft = "" + level * 20 + "px"
                }
            }
            return fun(): Any? {
                val _component_radio = resolveComponent("radio")
                val _component_radio_group = resolveComponent("radio-group")
                return createElementVNode(Fragment, null, utsArrayOf(
                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex-direction" to "row")), "id" to "rootview"), utsArrayOf(
                        createVNode(_component_radio_group, utsMapOf("onChange" to switchCacheOrData), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                            return utsArrayOf(
                                createVNode(_component_radio, utsMapOf("value" to "data", "checked" to true), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                    return utsArrayOf(
                                        "数据目录"
                                    )
                                }
                                ), "_" to 1)),
                                createVNode(_component_radio, utsMapOf("value" to "cache"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                    return utsArrayOf(
                                        "缓存目录"
                                    )
                                }
                                ), "_" to 1))
                            )
                        }
                        ), "_" to 1)),
                        createElementVNode("button", utsMapOf("onClick" to refreshFile), "刷新"),
                        createElementVNode("button", utsMapOf("onClick" to clearFile), "清空缓存"),
                        createElementVNode("button", utsMapOf("onClick" to snapshot), "截图")
                    ), 4),
                    createElementVNode("text", null, "文件总数：" + toDisplayString(unref(count)) + "；\\n文件总大小：" + toDisplayString(unref(size)) + " M", 1),
                    createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("border" to "1px solid #ccc", "margin" to "5px"))), "根路径：" + toDisplayString(unref(rootPath)), 5),
                    createElementVNode("list-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                        createElementVNode(Fragment, null, RenderHelpers.renderList(unref(fileList), fun(item, index, __index, _cached): Any {
                            return createElementVNode("list-item", utsMapOf("key" to index, "style" to normalizeStyle(utsArrayOf(
                                utsMapOf("padding" to "5px", "border-bottom" to "1px solid #ccc"),
                                getIndentStyle(item.shotPath)
                            )), "onClick" to fun(){
                                showDialog(item)
                            }
                            ), utsArrayOf(
                                createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex-direction" to "row"))), utsArrayOf(
                                    if (isTrue(!item.isFile)) {
                                        createElementVNode("text", utsMapOf("key" to 0, "style" to normalizeStyle(utsMapOf("font-family" to "uni-icon"))), toDisplayString(unref(uniIcon)), 5)
                                    } else {
                                        createCommentVNode("v-if", true)
                                    }
                                    ,
                                    createElementVNode("text", null, toDisplayString((item.shotPath as String)), 1)
                                ), 4),
                                if (isTrue(item.isFile)) {
                                    createElementVNode("text", utsMapOf("key" to 0, "style" to normalizeStyle(utsMapOf("font-size" to "12px", "color" to "#ccc"))), toDisplayString(item.size) + "k", 5)
                                } else {
                                    createCommentVNode("v-if", true)
                                }
                            ), 12, utsArrayOf(
                                "onClick"
                            ))
                        }
                        ), 128)
                    ), 4),
                    if (isTrue(unref(dialogShow))) {
                        createElementVNode("view", utsMapOf("key" to 0, "style" to normalizeStyle(utsMapOf("position" to "fixed", "top" to "0", "bottom" to "0", "left" to "0", "right" to "0", "align-items" to "center", "justify-content" to "center", "z-index" to "1000", "background-color" to "rgba(16, 16, 16, 0.5)"))), utsArrayOf(
                            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("background-color" to "#fff", "padding" to "10px", "border-radius" to "10px", "width" to "90%", "margin" to "0 auto"))), utsArrayOf(
                                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "20px", "font-weight" to "bold"))), toDisplayString(unref(currentFile).shotPath), 5),
                                createElementVNode("text", null, "类型：" + toDisplayString(if (unref(currentFile).isFile) {
                                    "文件"
                                } else {
                                    "目录"
                                }), 1),
                                createElementVNode("text", null, "路径：" + toDisplayString(unref(currentFile).path), 1),
                                createElementVNode("text", null, "大小：" + toDisplayString(unref(currentFile).size) + "k", 1),
                                createElementVNode("text", null, "修改时间：" + toDisplayString(unref(currentFile).modifyTime), 1),
                                createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex-direction" to "row", "width" to "100%", "justify-content" to "space-between", "padding" to "10px"))), utsArrayOf(
                                    if (isTrue(unref(currentFile).isFile)) {
                                        createElementVNode("button", utsMapOf("key" to 0, "onClick" to fun(){
                                            openFile(unref(currentFile))
                                        }, "size" to "mini"), "打开", 8, utsArrayOf(
                                            "onClick"
                                        ))
                                    } else {
                                        createCommentVNode("v-if", true)
                                    },
                                    createElementVNode("button", utsMapOf("onClick" to fun(){
                                        deleteFile(unref(currentFile))
                                    }, "size" to "mini"), "删除", 8, utsArrayOf(
                                        "onClick"
                                    )),
                                    createElementVNode("button", utsMapOf("onClick" to closeDialog, "size" to "mini"), "关闭")
                                ), 4)
                            ), 4)
                        ), 4)
                    } else {
                        createCommentVNode("v-if", true)
                    }
                ), 64)
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(), utsArrayOf(
                GenApp.styles
            ))
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
