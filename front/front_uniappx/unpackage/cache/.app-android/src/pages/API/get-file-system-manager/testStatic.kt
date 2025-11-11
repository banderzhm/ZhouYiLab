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
open class GenPagesAPIGetFileSystemManagerTestStatic : BasePage {
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
        return createElementVNode(Fragment, null, utsArrayOf(
            createElementVNode("text", null, "查看通过uni.getFileSystemManager()直接处理Static目录的操作日志"),
            createElementVNode("button", utsMapOf("size" to "mini", "onClick" to fun(){
                _ctx.log = ""
            }
            ), "清空日志", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("max-height" to "300px"))), utsArrayOf(
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "2px", "padding" to "2px", "border" to "1px solid #000000"))), toDisplayString(_ctx.log), 5)
            ), 4),
            createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.testAccessFile, "id" to "testAccessFile"), "判断Static中的文件是否存在", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.testAccessDir, "id" to "testAccessDir"), "判断Static中的目录是否存在", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.testCopyFile, "id" to "testCopyFile"), "复制Static中的文件", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.testReadFile, "id" to "testReadFile"), "读取Static中的文件", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.testReadDir, "id" to "testReadDir"), "读取Static中filemanager目录内文件列表", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.testFstatFile, "id" to "testFstatFile"), "获取Static中的文件状态信息", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.testReadZipEntry, "id" to "testReadZipEntry"), "获取Static中压缩包内的文件", 8, utsArrayOf(
                    "onClick"
                ))
            ), 4)
        ), 64)
    }
    open var log: String by `$data`
    open var logAble: Boolean by `$data`
    open var staticPath: String by `$data`
    open var isSuccess: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("log" to "", "logAble" to true, "staticPath" to "/static/filemanager/to.zip", "isSuccess" to false)
    }
    open var testReadZipEntry = ::gen_testReadZipEntry_fn
    open fun gen_testReadZipEntry_fn() {
        val fileManager = uni_getFileSystemManager()
        fileManager.readZipEntry(ReadZipEntryOptions(filePath = this.staticPath, encoding = "utf-8", success = fun(res: EntriesResult){
            if (this.logAble) {
                this.log += "testReadZipEntry success\n\n"
            }
            console.log("testReadZipEntry success", res, " at pages/API/get-file-system-manager/testStatic.uvue:44")
            this.isSuccess = true
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "testReadZipEntry fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("testReadZipEntry fail", res, " at pages/API/get-file-system-manager/testStatic.uvue:51")
            this.isSuccess = false
        }
        ))
    }
    open var openFileSyncTest = ::gen_openFileSyncTest_fn
    open fun gen_openFileSyncTest_fn(param: String): String {
        val fileManager = uni_getFileSystemManager()
        var fd = fileManager.openSync(OpenFileSyncOptions(filePath = this.staticPath, flag = param))
        return fd
    }
    open var testFstatFile = ::gen_testFstatFile_fn
    open fun gen_testFstatFile_fn() {
        val fileManager = uni_getFileSystemManager()
        fileManager.fstat(FStatOptions(fd = this.openFileSyncTest("r"), success = fun(res: FStatSuccessResult){
            if (this.logAble) {
                this.log += "testFstatFile success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("testFstatFile success", res, " at pages/API/get-file-system-manager/testStatic.uvue:72")
            this.isSuccess = true
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "testFstatFile fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("testFstatFile fail", res, " at pages/API/get-file-system-manager/testStatic.uvue:79")
            this.isSuccess = false
        }
        ))
    }
    open var testAccessFile = ::gen_testAccessFile_fn
    open fun gen_testAccessFile_fn() {
        val fileManager = uni_getFileSystemManager()
        fileManager.access(AccessOptions(path = this.staticPath, success = fun(res: FileManagerSuccessResult){
            if (this.logAble) {
                this.log += "testAccessFile success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("success", res, " at pages/API/get-file-system-manager/testStatic.uvue:92")
            this.isSuccess = true
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "testAccessFile fail:" + JSON.stringify(res) + "\n\n"
            }
            this.isSuccess = false
        }
        ))
    }
    open var testAccessDir = ::gen_testAccessDir_fn
    open fun gen_testAccessDir_fn() {
        val fileManager = uni_getFileSystemManager()
        fileManager.access(AccessOptions(path = "/static/filemanager", success = fun(res: FileManagerSuccessResult){
            if (this.logAble) {
                this.log += "testAccessDir success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("success", res, " at pages/API/get-file-system-manager/testStatic.uvue:112")
            this.isSuccess = true
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "testAccessDir fail:" + JSON.stringify(res) + "\n\n"
            }
            this.isSuccess = false
        }
        ))
    }
    open var testReadFile = ::gen_testReadFile_fn
    open fun gen_testReadFile_fn() {
        var fileManager = uni_getFileSystemManager()
        fileManager.readFile(ReadFileOptions(filePath = this.staticPath, encoding = "utf-8", success = fun(res: ReadFileSuccessResult){
            if (this.logAble) {
                this.log += "testReadFile success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("testReadFile success", res, " at pages/API/get-file-system-manager/testStatic.uvue:134")
            this.isSuccess = true
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "testReadFile fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("testReadFile", res, " at pages/API/get-file-system-manager/testStatic.uvue:141")
            this.isSuccess = false
        }
        ))
    }
    open var testReadDir = ::gen_testReadDir_fn
    open fun gen_testReadDir_fn() {
        var fileManager = uni_getFileSystemManager()
        fileManager.readdir(ReadDirOptions(dirPath = "/static/filemanager", success = fun(res: ReadDirSuccessResult){
            if (this.logAble) {
                this.log += "testReadDir success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("testReadDir success", res, " at pages/API/get-file-system-manager/testStatic.uvue:156")
            this.isSuccess = true
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "testReadDir fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("testReadDir", res, " at pages/API/get-file-system-manager/testStatic.uvue:163")
            this.isSuccess = false
        }
        ))
    }
    open var testCopyFile = ::gen_testCopyFile_fn
    open fun gen_testCopyFile_fn() {
        var fileManager = uni_getFileSystemManager()
        fileManager.copyFile(CopyFileOptions(srcPath = this.staticPath, destPath = "" + uni_env.USER_DATA_PATH + "/filemanager/to.zip", success = fun(res: FileManagerSuccessResult){
            if (this.logAble) {
                this.log += "testCopyFile success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("testCopyFile success", res, " at pages/API/get-file-system-manager/testStatic.uvue:179")
            this.isSuccess = true
        }
        , fail = fun(res: IFileSystemManagerFail){
            if (this.logAble) {
                this.log += "testCopyFile fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("testCopyFile", res, " at pages/API/get-file-system-manager/testStatic.uvue:186")
            this.isSuccess = false
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
                return utsMapOf("btnstyle" to padStyleMapOf(utsMapOf("marginTop" to 4, "marginRight" to 4, "marginBottom" to 4, "marginLeft" to 4)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
