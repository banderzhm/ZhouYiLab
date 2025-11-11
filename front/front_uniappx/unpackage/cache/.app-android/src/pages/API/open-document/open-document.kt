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
import io.dcloud.uniapp.extapi.downloadFile as uni_downloadFile
import io.dcloud.uniapp.extapi.hideLoading as uni_hideLoading
import io.dcloud.uniapp.extapi.openDocument as uni_openDocument
import io.dcloud.uniapp.extapi.showLoading as uni_showLoading
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesAPIOpenDocumentOpenDocument : BasePage {
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
        var setup: (__props: GenPagesAPIOpenDocumentOpenDocument) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesAPIOpenDocumentOpenDocument
            val _cache = __ins.renderCache
            val title = "openDocument"
            val fileList = ref(utsArrayOf<FileItem>(FileItem(type = "pdf", url = "https://web-assets.dcloud.net.cn/unidoc/zh/helloworld.pdf"), FileItem(type = "doc", url = "https://web-ext-storage.dcloud.net.cn/uni-app-x/file/helloworld.doc"), FileItem(type = "docx", url = "https://web-ext-storage.dcloud.net.cn/uni-app-x/file/helloworld.docx"), FileItem(type = "ppt", url = "https://web-ext-storage.dcloud.net.cn/uni-app-x/file/helloworld.ppt"), FileItem(type = "pptx", url = "https://web-ext-storage.dcloud.net.cn/uni-app-x/file/helloworld.pptx"), FileItem(type = "xls", url = "https://web-ext-storage.dcloud.net.cn/uni-app-x/file/helloworld.xls"), FileItem(type = "xlsx", url = "https://web-ext-storage.dcloud.net.cn/uni-app-x/file/helloworld.xlsx"), FileItem(type = "zip", url = "https://web-ext-storage.dcloud.net.cn/uni-app-x/file/to.zip"), FileItem(type = "br", url = "/static/filemanager/1.txt.br"), FileItem(type = "mp3", url = "/static/test-audio/ForElise.mp3"), FileItem(type = "mp4", url = "/static/test-video/10second-demo.mp4"), FileItem(type = "svg", url = "/static/test-image/logo.svg")))
            val openDocument = fun(item: FileItem){
                if (item.url.startsWith("http")) {
                    uni_showLoading(ShowLoadingOptions(title = "下载中", mask = true))
                    uni_downloadFile(DownloadFileOptions(url = item.url, success = fun(res){
                        uni_openDocument(OpenDocumentOptions(filePath = res.tempFilePath, success = fun(_){
                            uni_hideLoading()
                            console.log("打开文档成功", " at pages/API/open-document/open-document.uvue:88")
                        }, fail = fun(err){
                            uni_hideLoading()
                            console.log("打开文档失败", err, " at pages/API/open-document/open-document.uvue:92")
                            uni_showToast(ShowToastOptions(title = "错误码：" + err.errCode.toString(), icon = "error"))
                        }))
                    }, fail = fun(err){
                        uni_hideLoading()
                        console.log("下载失败", err, " at pages/API/open-document/open-document.uvue:102")
                        uni_showToast(ShowToastOptions(title = "下载失败：" + err.errCode.toString(), icon = "error"))
                    }))
                } else {
                    uni_openDocument(OpenDocumentOptions(filePath = item.url, success = fun(_){
                        console.log("打开文档成功", " at pages/API/open-document/open-document.uvue:113")
                    }
                    , fail = fun(err){
                        console.log("打开文档失败", err, " at pages/API/open-document/open-document.uvue:116")
                        uni_showToast(ShowToastOptions(title = "错误码：" + err.errCode.toString(), icon = "error"))
                    }
                    ))
                }
            }
            return fun(): Any? {
                val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
                return createElementVNode(Fragment, null, utsArrayOf(
                    createVNode(_component_page_head, utsMapOf("title" to title)),
                    createElementVNode("scroll-view", utsMapOf("direction" to "vertical", "style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-common-mt"), utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList(unref(fileList), fun(item, index, __index, _cached): Any {
                                return createElementVNode("button", utsMapOf("key" to index, "onClick" to fun(){
                                    openDocument(item)
                                }
                                , "style" to normalizeStyle(utsMapOf("margin" to "10px"))), " 打开 " + toDisplayString(item.type) + " 文件 ", 13, utsArrayOf(
                                    "onClick"
                                ))
                            }
                            ), 128)
                        ))
                    ), 4)
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
