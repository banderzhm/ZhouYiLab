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
import io.dcloud.uniapp.extapi.chooseFile as uni_chooseFile
import io.dcloud.uniapp.extapi.chooseImage as uni_chooseImage
import io.dcloud.uniapp.extapi.env as uni_env
import io.dcloud.uniapp.extapi.getFileSystemManager as uni_getFileSystemManager
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo
open class GenPagesAPIGetFileSystemManagerGetFileSystemManager : BasePage {
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
        onLoad(fun(_: OnLoadOptions) {}, __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode(Fragment, null, utsArrayOf(
            createElementVNode("text", null, "显示简易操作日志(可滚动查看),详细日志需真机运行查看"),
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
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.statFileInfoTest, "id" to "btn-stat-file"), "递归获取目录files的Stats对象" + toDisplayString(_ctx.statFile), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.mkdirTest, "id" to "btn-mkdir"), "创建文件夹" + toDisplayString(_ctx.mkdirFile), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.writeFileTest, "id" to "btn-write-file"), "覆盖写入文件" + toDisplayString(_ctx.writeFile), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.readDirTest, "id" to "btn-read-dir"), "读取文件夹" + toDisplayString(_ctx.readDir), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.readFileTest, "id" to "btn-read-file"), "读取文件" + toDisplayString(_ctx.readFile), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.copyFileTest, "id" to "btn-copy-file"), "复制文件" + toDisplayString(_ctx.copyFromFile) + "到" + toDisplayString(_ctx.copyToFile), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.renameFileTest, "id" to "btn-rename-file"), "重命名文件" + toDisplayString(_ctx.renameFromFile) + "到" + toDisplayString(_ctx.renameToFile), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.accessFileTest, "id" to "btn-access-file"), "判断文件" + toDisplayString(_ctx.accessFile) + "是否存在", 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.getFileInfoTest, "id" to "btn-get-file-info"), "获取文件信息" + toDisplayString(_ctx.getFileInfoFile), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.unlinkTest, "id" to "btn-unlink-file"), "删除文件" + toDisplayString(_ctx.unlinkFile), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.unlinkAllFileTest, "id" to "btn-clear-file"), "删除文件夹" + toDisplayString(_ctx.rmDirFile) + "下的所有文件", 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.rmdirTest, "id" to "btn-remove-dir"), "删除文件夹" + toDisplayString(_ctx.rmDirFile), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.statFileInfoSyncTest, "id" to "btn-stat-file-sync"), "同步递归获取目录files的Stats对象" + toDisplayString(_ctx.statFile), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.appendFileTest, "id" to "btn-append-file"), "在文件" + toDisplayString(_ctx.readFile) + "结尾追加内容", 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.appendFileSyncTest, "id" to "btn-append-file-sync"), "同步在文件" + toDisplayString(_ctx.readFile) + "结尾追加内容", 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.writeFileSyncTest, "id" to "btn-write-file-sync"), "同步覆盖写入文件" + toDisplayString(_ctx.writeFile), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.readFileSyncTest, "id" to "btn-read-file-sync"), "同步读取文件" + toDisplayString(_ctx.readFile), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.unlinkSyncTest, "id" to "btn-unlink-file-sync"), "同步删除文件" + toDisplayString(_ctx.unlinkFile), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.mkdirSyncTest, "id" to "btn-mkdir-sync"), "同步创建文件夹" + toDisplayString(_ctx.mkdirFile), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.rmdirSyncTest, "id" to "btn-remove-dir-sync"), "同步删除文件夹" + toDisplayString(_ctx.rmDirFile), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.readDirSyncTest, "id" to "btn-read-dir-sync"), "同步读取文件夹" + toDisplayString(_ctx.readDir), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.accessFileSyncTest, "id" to "btn-access-file-sync"), "同步判断文件" + toDisplayString(_ctx.accessFile) + "是否存在", 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.renameFileSync, "id" to "btn-rename-file-sync"), "同步重命名文件" + toDisplayString(_ctx.renameFromFile) + "到" + toDisplayString(_ctx.renameToFile), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.copyFileSyncTest, "id" to "btn-copy-file-sync"), "同步复制文件" + toDisplayString(_ctx.copyFromFile) + "到" + toDisplayString(_ctx.copyToFile), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.removeSavedFileTest, "id" to "btn-remove-saved-file"), "删除已保存的本地文件", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.getSavedFileListTest, "id" to "btn-getsaved-filelist"), "获取该已保存的本地缓存文件列表", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.truncateFileTest, "id" to "btn-truncate-file"), "对文件" + toDisplayString(_ctx.writeFile) + "内容进行截断操作", 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.openFileTest, "id" to "btn-open-file"), "打开文件" + toDisplayString(_ctx.readFile) + "，返回描述符", 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to fun(){
                    _ctx.openFileSyncTest("r", true)
                }
                , "id" to "btn-open-file-sync"), "同步打开文件" + toDisplayString(_ctx.readFile) + "，返回描述符", 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.closeTest, "id" to "btn-close-file"), "通过文件描述符关闭文件" + toDisplayString(_ctx.readFile), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.closeSyncTest, "id" to "btn-close-file-sync"), "通过文件描述符同步关闭文件" + toDisplayString(_ctx.readFile), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.writeTest, "id" to "btn-write"), "通过文件描述符写入文件" + toDisplayString(_ctx.readFile), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.writeSyncTest, "id" to "btn-write-sync"), "同步通过文件描述符写入文件" + toDisplayString(_ctx.readFile), 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.fstatTest, "id" to "btn-fstat-file"), "通过文件描述符获取" + toDisplayString(_ctx.statFile) + "的状态信息", 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.fstatSyncTest, "id" to "btn-fstat-file-sync"), "同步通过文件描述符获取" + toDisplayString(_ctx.statFile) + "的状态信息", 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.ftruncateFileTest, "id" to "btn-ftruncate-file"), "通过文件描述符对文件" + toDisplayString(_ctx.writeFile) + "内容进行截断", 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.ftruncateFileSyncTest, "id" to "btn-ftruncate-file-sync"), "同步通过文件描述符对文件" + toDisplayString(_ctx.writeFile) + "内容进行截断", 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.testWriteReadFileBuffer, "id" to "btn-writereadfile-buffer"), "写入/读取 ArrayBuffer", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.testWriteReadBuffer, "id" to "btn-writeread-buffer"), "通过文件描述符写入/读取 ArrayBuffer", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.testWriteReadSyncBuffer, "id" to "btn-writereadsync-buffer"), "通过文件描述符同步写入/读取 ArrayBuffer", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.testAppendFileBuffer, "id" to "btn-appendfile-buffer"), "在文件末尾追加 ArrayBuffer", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.testAppendFileBufferSync, "id" to "btn-appendfilesync-buffer"), "同步在文件末尾追加 ArrayBuffer", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.copyStaticToFilesTest, "id" to "btn-copyStatic-file"), "从static目录复制文件到a目录", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.saveFileTest, "id" to "btn-save-file"), "保存临时文件到本地, filePath=null", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.saveFileTest1, "id" to "btn-save-file1"), "保存临时文件到本地, filePath=xxx/path.txt", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.saveFileTest2, "id" to "btn-save-file2"), "保存临时文件到本地, filePath=xxx/path", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.saveFileTest3, "id" to "btn-save-file3"), "保存临时文件到本地, filePath=xxx/path/", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.saveFileSyncTest, "id" to "btn-save-file-sync"), "同步保存临时文件到本地", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.unzipFileTest, "id" to "btn-unzip-file-sync"), "解压文件", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.truncateFileSyncTest, "id" to "btn-truncate-file-sync"), "同步对文件" + toDisplayString(_ctx.writeFile) + "内容进行截断操作", 9, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.readCompressedFileTest, "id" to "btn-compressed-file"), "读取指定压缩类型的本地文件内容", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.readCompressedFileSyncTest, "id" to "btn-compressed-file-sync"), "同步读取指定压缩类型的本地文件内容", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.readZipEntry, "id" to "btn-readzip-entry"), "读取压缩包内的文件", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.testWriteReadFileSyncBuffer, "id" to "btn-writereadfilesync-buffer"), "同步写入/读取 ArrayBuffer", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to fun(){
                    _ctx.testReadFileEncoding("base64")
                }
                ), "readFile(content://base64)", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to fun(){
                    _ctx.testReadFileEncoding("utf-8")
                }
                ), "readFile(content://utf-8)", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to fun(){
                    _ctx.testReadFileEncoding("ascii")
                }
                ), "readFile(content://ascii)", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to fun(){
                    _ctx.testReadFileArrayBuffer()
                }
                ), "readFile(content://arraybuffer)", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to fun(){
                    _ctx.testReadFileSyncEncoding("base64")
                }
                ), "readFileSync(content://base64)", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to fun(){
                    _ctx.testReadFileSyncEncoding("utf-8")
                }
                ), "readFileSync(content://utf-8)", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to fun(){
                    _ctx.testReadFileSyncEncoding("ascii")
                }
                ), "readFileSync(content://ascii)", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to fun(){
                    _ctx.testReadFileSyncArrayBuffer()
                }
                ), "readFileSync(content://arraybuffer)", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to fun(){
                    _ctx.copyFileByContent()
                }
                ), "copyFile(content://)", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to fun(){
                    _ctx.copyFileSyncByContent()
                }
                ), "copyFileSync(content://)", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.getFileInfoByContent), "getFileInfo(content://)", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to fun(){
                    _ctx.gotoExplore()
                }
                ), "前往沙盒文件管理器", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to fun(){
                    _ctx.gotoTestStatic()
                }
                ), "前往Static文件测试", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("height" to "4px"))), null, 4)
            ), 4)
        ), 64)
    }
    open var log: String by `$data`
    open var logAble: Boolean by `$data`
    open var fileListSuccess: UTSArray<String> by `$data`
    open var fileListComplete: UTSArray<String> by `$data`
    open var accessFileRet: String by `$data`
    open var lastFailError: UniError by `$data`
    open var lastCompleteError: UniError by `$data`
    open var readDir: String by `$data`
    open var readFileRet: String by `$data`
    open var writeFileContent: String by `$data`
    open var appendFileContent: String by `$data`
    open var getFileInfoAlgorithm: String by `$data`
    open var getFileInfoSize: Number by `$data`
    open var getFileInfoDigest: String by `$data`
    open var unlinkFile: String by `$data`
    open var accessFile: String by `$data`
    open var writeFile: String by `$data`
    open var writeData: String by `$data`
    open var brFile: String by `$data`
    open var temFile: String by `$data`
    open var copyFromFile: String by `$data`
    open var copyToFile: String by `$data`
    open var renameFromFile: String by `$data`
    open var renameToFile: String by `$data`
    open var getFileInfoFile: String by `$data`
    open var statFile: String by `$data`
    open var rmDirFile: String by `$data`
    open var mkdirFile: String by `$data`
    open var readFile: String by `$data`
    open var recursiveVal: Boolean by `$data`
    open var done: Boolean by `$data`
    open var writeFileEncoding: String by `$data`
    open var readFileEncoding: String by `$data`
    open var statsRet: UTSArray<FileStats> by `$data`
    open var unzipFile: String by `$data`
    open var targetZip: String by `$data`
    open var renameFileRet: String by `$data`
    open var saveFileRet: String by `$data`
    open var removeSavedFileRet: String by `$data`
    open var fd: String by `$data`
    open var closeFileRet: String by `$data`
    open var bytesWritten: Number by `$data`
    open var fstat: Stats? by `$data`
    open var fstatSize: Number by `$data`
    open var ftruncateRet: String by `$data`
    open var readZipFile: String by `$data`
    open var getSavedFileListRet: String by `$data`
    open var arrayBufferRes: Number by `$data`
    open var basePath: Any? by `$data`
    open var copyToBasePath: Any? by `$data`
    open var globalTempPath: Any? by `$data`
    open var globalRootPath: Any? by `$data`
    open var globalUserDataPath: Any? by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("log" to "", "logAble" to true, "fileListSuccess" to utsArrayOf<String>(), "fileListComplete" to utsArrayOf<String>(), "accessFileRet" to "", "lastFailError" to UniError("uni-file-manager", 1300000, "mock error"), "lastCompleteError" to UniError("uni-file-manager", 1300000, "mock error"), "readDir" to "a", "readFileRet" to "", "writeFileContent" to "中文 en.\r\n\t换行", "appendFileContent" to "append content", "getFileInfoAlgorithm" to "md5", "getFileInfoSize" to -1, "getFileInfoDigest" to "", "unlinkFile" to "a/1.txt", "accessFile" to "a/1.txt", "writeFile" to "a/1.txt", "writeData" to "insert data哈哈哈", "brFile" to "a/1.txt.br", "temFile" to "a/1.txt", "copyFromFile" to "a/1.txt", "copyToFile" to "a/2.txt", "renameFromFile" to "a/2.txt", "renameToFile" to "a/3.txt", "getFileInfoFile" to "a/1.txt", "statFile" to "", "rmDirFile" to "a", "mkdirFile" to "a", "readFile" to "a/1.txt", "recursiveVal" to true, "done" to false, "writeFileEncoding" to "utf-8", "readFileEncoding" to "utf-8", "statsRet" to utsArrayOf<FileStats>(), "unzipFile" to "zip/1.zip", "targetZip" to "unzip", "renameFileRet" to "", "saveFileRet" to "", "removeSavedFileRet" to "", "fd" to "", "closeFileRet" to "", "bytesWritten" to 0, "fstat" to null as Stats?, "fstatSize" to 0, "ftruncateRet" to "", "readZipFile" to "to.zip", "getSavedFileListRet" to "", "arrayBufferRes" to 0, "basePath" to uni_env.USER_DATA_PATH, "copyToBasePath" to uni_env.USER_DATA_PATH, "globalTempPath" to uni_env.CACHE_PATH, "globalRootPath" to uni_env.SANDBOX_PATH, "globalUserDataPath" to uni_env.USER_DATA_PATH)
    }
    open var statFileInfoTest = ::gen_statFileInfoTest_fn
    open fun gen_statFileInfoTest_fn(_0: Any) {
        val fileManager = uni_getFileSystemManager()
        fileManager.stat(StatOptions(path = "" + this.basePath + this.statFile, recursive = this.recursiveVal, success = fun(res: StatSuccessResult){
            if (this.logAble) {
                this.log += "statFileInfoTest success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("statFileInfoTest success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:217")
            this.statsRet = res.stats
            console.log("this.statsRet", this.statsRet, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:219")
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "statFileInfoTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("statFileInfoTest fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:225")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
        }
        , complete = fun(res: Any){
            console.log("statFileInfoTest complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:229")
            this.done = true
            if (res is UniError) {
                this.lastCompleteError = res
            }
        }
        ))
    }
    open var getFileInfoTest = ::gen_getFileInfoTest_fn
    open fun gen_getFileInfoTest_fn() {
        val fileManager = uni_getFileSystemManager()
        fileManager.getFileInfo(GetFileInfoOptions(filePath = "" + this.basePath + this.getFileInfoFile, digestAlgorithm = this.getFileInfoAlgorithm, success = fun(res: GetFileInfoSuccessResult){
            if (this.logAble) {
                this.log += "getFileInfoTest success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:248")
            this.getFileInfoSize = res.size
            this.getFileInfoDigest = res.digest
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "getFileInfoTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:256")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
        }
        , complete = fun(res: Any){
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:260")
            this.done = true
            if (res is UniError) {
                this.lastCompleteError = res
            }
        }
        ))
    }
    open var copyFileTest = ::gen_copyFileTest_fn
    open fun gen_copyFileTest_fn() {
        val fileManager = uni_getFileSystemManager()
        fileManager.copyFile(CopyFileOptions(srcPath = "" + this.basePath + this.copyFromFile, destPath = "" + this.copyToBasePath + this.copyToFile, success = fun(res: FileManagerSuccessResult){
            if (this.logAble) {
                this.log += "copyFileTest success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:279")
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "copyFileTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:285")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
        }
        , complete = fun(res: Any){
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:289")
            this.done = true
            if (res is UniError) {
                this.lastCompleteError = res
            }
        }
        ))
    }
    open var renameFileTest = ::gen_renameFileTest_fn
    open fun gen_renameFileTest_fn() {
        val fileManager = uni_getFileSystemManager()
        fileManager.rename(RenameOptions(oldPath = "" + this.basePath + this.renameFromFile, newPath = "" + this.basePath + this.renameToFile, success = fun(res){
            if (this.logAble) {
                this.log += "renameFileTest success:" + JSON.stringify(res) + "\n\n"
            }
            this.renameFileRet = "rename:ok"
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:309")
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "renameFileTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:315")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
        }
        , complete = fun(res: Any){
            this.done = true
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:320")
            if (res is UniError) {
                this.lastCompleteError = res
            }
        }
        ))
    }
    open var readDirTest = ::gen_readDirTest_fn
    open fun gen_readDirTest_fn() {
        val fileManager = uni_getFileSystemManager()
        fileManager.readdir(ReadDirOptions(dirPath = "" + this.basePath + this.readDir, success = fun(res: ReadDirSuccessResult){
            if (this.logAble) {
                this.log += "readDirTest success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:336")
            this.fileListSuccess = res.files
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "readDirTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:343")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
        }
        , complete = fun(res: Any){
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:347")
            this.done = true
            if (res is UniError) {
                this.lastCompleteError = res
            } else {
                this.fileListComplete = (res as ReadDirSuccessResult).files
            }
        }
        ))
    }
    open var writeFileTest = ::gen_writeFileTest_fn
    open fun gen_writeFileTest_fn(_0: Any) {
        val fileManager = uni_getFileSystemManager()
        fileManager.writeFile(WriteFileOptions(filePath = "" + this.basePath + this.writeFile, data = this.writeFileContent, encoding = this.writeFileEncoding, success = fun(res){
            if (this.logAble) {
                this.log += "writeFileTest success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:369")
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "writeFileTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("fail", " at pages/API/get-file-system-manager/get-file-system-manager.uvue:375")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
        }
        , complete = fun(res: Any){
            this.done = true
            console.log("complete", " at pages/API/get-file-system-manager/get-file-system-manager.uvue:380")
            if (res is UniError) {
                this.lastCompleteError = res
            }
        }
        ))
    }
    open var readFileTest = ::gen_readFileTest_fn
    open fun gen_readFileTest_fn() {
        val fileManager = uni_getFileSystemManager()
        fileManager.readFile(ReadFileOptions(filePath = "" + this.basePath + this.readFile, encoding = this.readFileEncoding, success = fun(res: ReadFileSuccessResult){
            if (this.logAble) {
                this.log += "readFileTest success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:398")
            this.readFileRet = res.data.toString()
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "readFileTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:405")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
        }
        , complete = fun(res: Any){
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:409")
            this.done = true
            if (res is UniError) {
                this.lastCompleteError = res
            }
        }
        ))
    }
    open var rmdirTest = ::gen_rmdirTest_fn
    open fun gen_rmdirTest_fn() {
        val fileManager = uni_getFileSystemManager()
        fileManager.rmdir(RmDirOptions(dirPath = "" + this.basePath + this.rmDirFile, recursive = this.recursiveVal, success = fun(res: FileManagerSuccessResult){
            if (this.logAble) {
                this.log += "rmdirTest success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:427")
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "rmdirTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:433")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
        }
        , complete = fun(res: Any){
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:437")
            this.done = true
            if (res is UniError) {
                this.lastCompleteError = res
            }
        }
        ))
    }
    open var mkdirTest = ::gen_mkdirTest_fn
    open fun gen_mkdirTest_fn() {
        val fileManager = uni_getFileSystemManager()
        fileManager.mkdir(MkDirOptions(dirPath = "" + this.basePath + this.mkdirFile, recursive = this.recursiveVal, success = fun(res: FileManagerSuccessResult){
            if (this.logAble) {
                this.log += "mkdirTest success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:457")
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "mkdirTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:463")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
        }
        , complete = fun(res: Any){
            if (res is UniError) {
                this.lastCompleteError = res
            }
            this.done = true
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:472")
        }
        ))
    }
    open var accessFileTest = ::gen_accessFileTest_fn
    open fun gen_accessFileTest_fn() {
        this.accessFileRet = ""
        val fileManager = uni_getFileSystemManager()
        fileManager.access(AccessOptions(path = "" + this.basePath + this.accessFile, success = fun(res: FileManagerSuccessResult){
            if (this.logAble) {
                this.log += "accessFileTest success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:486")
            this.accessFileRet = res.errMsg
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "accessFileTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:493")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
        }
        , complete = fun(res: Any){
            if (res is UniError) {
                this.lastCompleteError = res
            }
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:501")
            this.done = true
        }
        ))
    }
    open var unlinkTest = ::gen_unlinkTest_fn
    open fun gen_unlinkTest_fn() {
        val fileManager = uni_getFileSystemManager()
        fileManager.unlink(UnLinkOptions(filePath = "" + this.basePath + this.unlinkFile, success = fun(res: FileManagerSuccessResult){
            if (this.logAble) {
                this.log += "unlinkTest success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:516")
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "unlinkTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:522")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
        }
        , complete = fun(res: Any){
            if (res is UniError) {
                this.lastCompleteError = res
            }
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:530")
            this.done = true
        }
        ))
    }
    open var unlinkAllFileTest = ::gen_unlinkAllFileTest_fn
    open fun gen_unlinkAllFileTest_fn() {
        val fileManager = uni_getFileSystemManager()
        fileManager.readdir(ReadDirOptions(dirPath = "" + this.basePath + this.rmDirFile, success = fun(res: ReadDirSuccessResult){
            console.log("success to readdir", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:540")
            res.files.forEach(fun(element){
                console.log(element, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:542")
                var filePath: String
                if (this.rmDirFile.length <= 0) {
                    filePath = "" + this.basePath + element
                } else {
                    filePath = "" + this.basePath + this.rmDirFile + "/" + element
                }
                fileManager.unlink(UnLinkOptions(filePath = filePath, success = fun(res: FileManagerSuccessResult){
                    if (this.logAble) {
                        this.log += "unlinkAllFileTest success:" + JSON.stringify(res) + "\n\n"
                    }
                    console.log("success unlink", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:555")
                }
                , fail = fun(res: IUniError){
                    if (this.logAble) {
                        this.log += "unlinkAllFileTest fail:" + JSON.stringify(res) + "\n\n"
                    }
                    console.log("fail unlink", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:561")
                    this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
                }
                , complete = fun(res: Any){
                    if (res is UniError) {
                        this.lastCompleteError = res
                    }
                    console.log("complete unlink", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:569")
                    this.done = true
                }
                ))
            }
            )
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "unlinkAllFileTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("fail to readdir", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:579")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
        }
        , complete = fun(res: Any){
            console.log("complete readdir", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:584")
            this.done = true
            if (res is UniError) {
                this.lastCompleteError = res
            } else {
                this.fileListComplete = (res as ReadDirSuccessResult).files
            }
        }
        ))
    }
    open var copyStaticToFilesTest = ::gen_copyStaticToFilesTest_fn
    open fun gen_copyStaticToFilesTest_fn() {
        val fileManager = uni_getFileSystemManager()
        fileManager.copyFile(CopyFileOptions(srcPath = "/static/list-mock/mock.json", destPath = "" + this.copyToBasePath + "/a/mock.json", success = fun(res: FileManagerSuccessResult){
            if (this.logAble) {
                this.log += "copyFileTest success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:604")
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "copyFileTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:610")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
        }
        , complete = fun(res: Any){
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:615")
            this.done = true
            if (res is UniError) {
                this.lastCompleteError = res
            }
        }
        ))
    }
    open var appendFileTest = ::gen_appendFileTest_fn
    open fun gen_appendFileTest_fn(_0: Any) {
        val fileManager = uni_getFileSystemManager()
        fileManager.appendFile(AppendFileOptions(filePath = "" + this.basePath + this.writeFile, data = this.appendFileContent, encoding = this.writeFileEncoding, success = fun(res: FileManagerSuccessResult){
            if (this.logAble) {
                this.log += "appendFileTest success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:634")
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "appendFileTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("fail", " at pages/API/get-file-system-manager/get-file-system-manager.uvue:640")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
        }
        , complete = fun(res: Any){
            this.done = true
            console.log("complete", " at pages/API/get-file-system-manager/get-file-system-manager.uvue:646")
            if (res is UniError) {
                this.lastCompleteError = res
            }
        }
        ))
    }
    open var writeFileSyncTest = ::gen_writeFileSyncTest_fn
    open fun gen_writeFileSyncTest_fn(_0: Any) {
        try {
            val fileManager = uni_getFileSystemManager()
            fileManager.writeFileSync("" + this.basePath + this.writeFile, this.writeFileContent, this.writeFileEncoding)
            if (this.logAble) {
                this.log += "writeFileSyncTest success:\n\n"
            }
            this.done = true
        }
         catch (e: Throwable) {
            if (this.logAble) {
                this.log += "writeFileSyncTest fail:" + e + "\n\n"
            }
            this.done = true
        }
    }
    open var readFileSyncTest = ::gen_readFileSyncTest_fn
    open fun gen_readFileSyncTest_fn() {
        try {
            val fileManager = uni_getFileSystemManager()
            var data = fileManager.readFileSync("" + this.basePath + this.readFile, this.readFileEncoding)
            if (this.logAble) {
                this.log += "readFileSyncTest result:" + data + "\n\n"
            }
            this.done = true
            this.readFileRet = data.toString()
        }
         catch (e: Throwable) {
            if (this.logAble) {
                this.log += "readFileSyncTest fail:" + e + "\n\n"
            }
            this.done = true
        }
    }
    open var unlinkSyncTest = ::gen_unlinkSyncTest_fn
    open fun gen_unlinkSyncTest_fn() {
        try {
            val fileManager = uni_getFileSystemManager()
            fileManager.unlinkSync("" + this.basePath + this.unlinkFile)
            this.done = true
        }
         catch (e: Throwable) {
            if (this.logAble) {
                this.log += "unlinkSyncTest fail:" + e + "\n\n"
            }
            this.done = true
        }
    }
    open var mkdirSyncTest = ::gen_mkdirSyncTest_fn
    open fun gen_mkdirSyncTest_fn() {
        try {
            val fileManager = uni_getFileSystemManager()
            fileManager.mkdirSync("" + this.basePath + this.mkdirFile, this.recursiveVal)
            this.done = true
        }
         catch (e: Throwable) {
            this.done = true
            if (this.logAble) {
                this.log += "mkdirSyncTest fail:" + e + "\n\n"
            }
        }
    }
    open var rmdirSyncTest = ::gen_rmdirSyncTest_fn
    open fun gen_rmdirSyncTest_fn() {
        try {
            val fileManager = uni_getFileSystemManager()
            fileManager.rmdirSync("" + this.basePath + this.rmDirFile, this.recursiveVal)
            this.done = true
        }
         catch (e: Throwable) {
            if (this.logAble) {
                this.log += "rmdirSyncTest fail:" + e + "\n\n"
            }
            this.done = true
        }
    }
    open var readDirSyncTest = ::gen_readDirSyncTest_fn
    open fun gen_readDirSyncTest_fn() {
        try {
            val fileManager = uni_getFileSystemManager()
            var res = fileManager.readdirSync("" + this.basePath + this.readDir)
            if (this.logAble) {
                this.log += "readDirTest success:" + JSON.stringify(res) + "\n\n"
            }
            if (res != null) {
                this.fileListSuccess = res
            }
            this.done = true
        }
         catch (e: Throwable) {
            if (this.logAble) {
                this.log += "rmdirSyncTest fail:" + e + "\n\n"
            }
            this.done = true
        }
    }
    open var accessFileSyncTest = ::gen_accessFileSyncTest_fn
    open fun gen_accessFileSyncTest_fn() {
        this.accessFileRet = ""
        val fileManager = uni_getFileSystemManager()
        try {
            fileManager.accessSync("" + this.basePath + this.accessFile)
            this.done = true
            this.accessFileRet = "access:ok"
        }
         catch (e: Throwable) {
            if (this.logAble) {
                this.log += "rmdirSyncTest fail:" + e + "\n\n"
            }
            this.done = true
        }
    }
    open var renameFileSync = ::gen_renameFileSync_fn
    open fun gen_renameFileSync_fn() {
        val fileManager = uni_getFileSystemManager()
        try {
            fileManager.renameSync("" + this.basePath + this.renameFromFile, "" + this.basePath + this.renameToFile)
            this.done = true
            this.renameFileRet = "rename:ok"
        }
         catch (e: Throwable) {
            if (this.logAble) {
                this.log += "rmdirSyncTest fail:" + e + "\n\n"
            }
            console.log("renameSync:" + e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:775")
            this.done = true
        }
    }
    open var copyFileSyncTest = ::gen_copyFileSyncTest_fn
    open fun gen_copyFileSyncTest_fn() {
        val fileManager = uni_getFileSystemManager()
        try {
            fileManager.copyFileSync("" + this.basePath + this.copyFromFile, "" + this.copyToBasePath + this.copyToFile)
            this.done = true
        }
         catch (e: Throwable) {
            if (this.logAble) {
                this.log += "rmdirSyncTest fail:" + e + "\n\n"
            }
            this.done = true
        }
    }
    open var appendFileSyncTest = ::gen_appendFileSyncTest_fn
    open fun gen_appendFileSyncTest_fn(_0: Any) {
        val fileManager = uni_getFileSystemManager()
        try {
            fileManager.appendFileSync("" + this.basePath + this.writeFile, this.appendFileContent, this.writeFileEncoding)
            this.done = true
        }
         catch (e: Throwable) {
            if (this.logAble) {
                this.log += "rmdirSyncTest fail:" + e + "\n\n"
            }
            this.done = true
        }
    }
    open var saveFileTest = ::gen_saveFileTest_fn
    open fun gen_saveFileTest_fn(_0: Any) {
        val fileManager = uni_getFileSystemManager()
        this.writeFileSyncTest("")
        fileManager.saveFile(SaveFileOptions(tempFilePath = "" + this.basePath + this.temFile, success = fun(res: SaveFileSuccessResult){
            if (this.logAble) {
                this.log += "saveFileTest success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:817")
            this.saveFileRet = res.savedFilePath
            this.done = true
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "saveFileTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("saveFileTest fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:825")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
            this.done = true
        }
        , complete = fun(_){
            this.done = true
        }
        ))
    }
    open var saveFileTest1 = ::gen_saveFileTest1_fn
    open fun gen_saveFileTest1_fn(_0: Any) {
        val fileManager = uni_getFileSystemManager()
        this.writeFileSyncTest("")
        fileManager.access(AccessOptions(path = "" + this.basePath + "local", success = fun(_res){
            if (fileManager.fstatSync(FStatSyncOptions(fd = fileManager.openSync(OpenFileSyncOptions(filePath = "" + this.basePath + "local", flag = "r")))).isDirectory()) {
                fileManager.rmdirSync("" + this.basePath + "local", true)
            } else {
                fileManager.unlinkSync("" + this.basePath + "local")
            }
            fileManager.saveFile(SaveFileOptions(tempFilePath = "" + this.basePath + this.temFile, filePath = "" + this.basePath + "local/1.txt", success = fun(res: SaveFileSuccessResult){
                if (this.logAble) {
                    this.log += "saveFileTest success:" + JSON.stringify(res) + "\n\n"
                }
                console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:853")
                this.saveFileRet = res.savedFilePath
                this.done = true
            }
            , fail = fun(res: IUniError){
                if (this.logAble) {
                    this.log += "saveFileTest fail:" + JSON.stringify(res) + "\n\n"
                }
                console.log("saveFileTest fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:861")
                this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
                this.done = true
            }
            , complete = fun(_){
                this.done = true
            }
            ))
        }
        , fail = fun(_res){
            fileManager.saveFile(SaveFileOptions(tempFilePath = "" + this.basePath + this.temFile, filePath = "" + this.basePath + "local/1.txt", success = fun(res: SaveFileSuccessResult){
                if (this.logAble) {
                    this.log += "saveFileTest success:" + JSON.stringify(res) + "\n\n"
                }
                console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:879")
                this.saveFileRet = res.savedFilePath
                this.done = true
            }
            , fail = fun(res: IUniError){
                if (this.logAble) {
                    this.log += "saveFileTest fail:" + JSON.stringify(res) + "\n\n"
                }
                console.log("saveFileTest fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:887")
                this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
                this.done = true
            }
            , complete = fun(_){
                this.done = true
            }
            ))
        }
        ))
    }
    open var saveFileTest2 = ::gen_saveFileTest2_fn
    open fun gen_saveFileTest2_fn(_0: Any) {
        val fileManager = uni_getFileSystemManager()
        this.writeFileSyncTest("")
        fileManager.access(AccessOptions(path = "" + this.basePath + "local", success = fun(_res){
            if (fileManager.fstatSync(FStatSyncOptions(fd = fileManager.openSync(OpenFileSyncOptions(filePath = "" + this.basePath + "local", flag = "r")))).isDirectory()) {
                fileManager.rmdirSync("" + this.basePath + "local", true)
            } else {
                fileManager.unlinkSync("" + this.basePath + "local")
            }
            fileManager.saveFile(SaveFileOptions(tempFilePath = "" + this.basePath + this.temFile, filePath = "" + this.basePath + "local", success = fun(res: SaveFileSuccessResult){
                if (this.logAble) {
                    this.log += "saveFileTest success:" + JSON.stringify(res) + "\n\n"
                }
                console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:917")
                this.saveFileRet = res.savedFilePath
                this.done = true
            }
            , fail = fun(res: IUniError){
                if (this.logAble) {
                    this.log += "saveFileTest fail:" + JSON.stringify(res) + "\n\n"
                }
                console.log("saveFileTest fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:925")
                this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
                this.done = true
            }
            , complete = fun(_){
                this.done = true
            }
            ))
        }
        ))
    }
    open var saveFileTest3 = ::gen_saveFileTest3_fn
    open fun gen_saveFileTest3_fn(_0: Any) {
        val fileManager = uni_getFileSystemManager()
        this.writeFileSyncTest("")
        fileManager.access(AccessOptions(path = "" + this.basePath + "local", success = fun(_res){
            if (fileManager.fstatSync(FStatSyncOptions(fd = fileManager.openSync(OpenFileSyncOptions(filePath = "" + this.basePath + "local", flag = "r")))).isDirectory()) {
                fileManager.rmdirSync("" + this.basePath + "local", true)
            } else {
                fileManager.unlinkSync("" + this.basePath + "local")
            }
            fileManager.saveFile(SaveFileOptions(tempFilePath = "" + this.basePath + this.temFile, filePath = "" + this.basePath + "local/", success = fun(res: SaveFileSuccessResult){
                if (this.logAble) {
                    this.log += "saveFileTest success:" + JSON.stringify(res) + "\n\n"
                }
                console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:956")
                this.saveFileRet = res.savedFilePath
                this.done = true
            }
            , fail = fun(res: IUniError){
                if (this.logAble) {
                    this.log += "saveFileTest fail:" + JSON.stringify(res) + "\n\n"
                }
                console.log("saveFileTest fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:964")
                this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
                this.done = true
            }
            , complete = fun(_){
                this.done = true
            }
            ))
        }
        ))
    }
    open var saveFileSyncTest = ::gen_saveFileSyncTest_fn
    open fun gen_saveFileSyncTest_fn(_0: Any) {
        val fileManager = uni_getFileSystemManager()
        this.writeFileSyncTest("")
        try {
            fileManager.saveFileSync("" + this.basePath + this.temFile, null)
            this.done = true
        }
         catch (e: Throwable) {
            console.log("saveFileSyncTest:" + e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:986")
            this.done = true
        }
    }
    open var unzipFileTest = ::gen_unzipFileTest_fn
    open fun gen_unzipFileTest_fn(_0: Any) {
        val fileManager = uni_getFileSystemManager()
        try {
            fileManager.mkdirSync("" + this.basePath + this.targetZip, true)
        }
         catch (e: Throwable) {
            console.error(e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:995")
        }
        fileManager.unzip(UnzipFileOptions(zipFilePath = "/static/filemanager/to.zip", targetPath = "" + this.basePath + this.targetZip, success = fun(res: FileManagerSuccessResult){
            if (this.logAble) {
                this.log += "unzipFileTest success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1004")
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "unzipFileTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1010")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
        }
        , complete = fun(_){
            this.done = true
        }
        ))
    }
    open var getSavedFileListTest = ::gen_getSavedFileListTest_fn
    open fun gen_getSavedFileListTest_fn() {
        val fileManager = uni_getFileSystemManager()
        fileManager.getSavedFileList(GetSavedFileListOptions(success = fun(res: GetSavedFileListResult){
            if (this.logAble) {
                this.log += "getSavedFileListTest success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("getSavedFileListTest success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1027")
            this.fileListSuccess = res.fileList
            this.getSavedFileListRet = "getSavedFileList:ok"
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "getSavedFileListTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("getSavedFileListTest fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1035")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
            this.getSavedFileListRet = JSON.stringify(res)
        }
        , complete = fun(res: Any){
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1041")
            this.done = true
            if (res is UniError) {
                this.lastCompleteError = res
            } else {
                this.fileListComplete = (res as GetSavedFileListResult).fileList
            }
        }
        ))
    }
    open var truncateFileTest = ::gen_truncateFileTest_fn
    open fun gen_truncateFileTest_fn() {
        val fileManager = uni_getFileSystemManager()
        fileManager.truncate(TruncateFileOptions(filePath = "" + this.basePath + this.writeFile, length = 6, success = fun(res: FileManagerSuccessResult){
            if (this.logAble) {
                this.log += "truncateFileTest success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1060")
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "truncateFileTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1066")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
        }
        , complete = fun(res: Any){
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1071")
            this.done = true
            if (res is UniError) {
                this.lastCompleteError = res
            }
        }
        ))
    }
    open var truncateFileSyncTest = ::gen_truncateFileSyncTest_fn
    open fun gen_truncateFileSyncTest_fn() {
        val fileManager = uni_getFileSystemManager()
        try {
            fileManager.truncateSync("" + this.basePath + this.writeFile, 3)
            this.done = true
        }
         catch (e: Throwable) {
            console.log(e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1087")
            this.done = true
        }
    }
    open var readCompressedFileTest = ::gen_readCompressedFileTest_fn
    open fun gen_readCompressedFileTest_fn() {
        val fileManager = uni_getFileSystemManager()
        fileManager.readCompressedFile(ReadCompressedFileOptions(filePath = "/static/filemanager/1.txt.br", compressionAlgorithm = "br", success = fun(res: ReadCompressedFileResult){
            if (this.logAble) {
                this.log += "readCompressedFileTest success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1100")
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "readCompressedFileTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1106")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
        }
        , complete = fun(_){
            this.done = true
        }
        ))
    }
    open var readCompressedFileSyncTest = ::gen_readCompressedFileSyncTest_fn
    open fun gen_readCompressedFileSyncTest_fn() {
        console.log("readCompressedFileSyncTest", " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1116")
        val fileManager = uni_getFileSystemManager()
        try {
            var data = fileManager.readCompressedFileSync("/static/filemanager/1.txt.br", "br")
            if (this.logAble) {
                this.log += data
            }
            this.done = true
        }
         catch (e: Throwable) {
            if (this.logAble) {
                this.log += "readCompressedFileSyncTest fail:" + e + "\n\n"
            }
            this.done = true
        }
    }
    open var removeSavedFileTest = ::gen_removeSavedFileTest_fn
    open fun gen_removeSavedFileTest_fn() {
        console.log("removeSavedFileTest enter", " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1135")
        val fileManager = uni_getFileSystemManager()
        fileManager.removeSavedFile(RemoveSavedFileOptions(filePath = "" + this.basePath + this.writeFile, success = fun(res: FileManagerSuccessResult){
            if (this.logAble) {
                this.log += "removeSavedFileTest success:" + JSON.stringify(res) + "\n\n"
            }
            this.removeSavedFileRet = res.errMsg
            console.log("removeSavedFileTest success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1144")
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "removeSavedFileTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("removeSavedFileTest fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1150")
        }
        , complete = fun(_){
            this.done = true
        }
        ))
    }
    open var statFileInfoSyncTest = ::gen_statFileInfoSyncTest_fn
    open fun gen_statFileInfoSyncTest_fn(_0: Any) {
        val fileManager = uni_getFileSystemManager()
        try {
            var res = fileManager.statSync("" + this.globalTempPath + this.statFile, this.recursiveVal)
            if (this.logAble) {
                this.log += "statFileInfoSyncTest success:" + JSON.stringify(res) + "\n\n"
            }
            this.statsRet = res
            this.done = true
        }
         catch (e: Throwable) {
            if (this.logAble) {
                this.log += "statFileInfoSyncTest fail:" + e + "\n\n"
            }
            this.done = true
        }
    }
    open var openFileTest = ::gen_openFileTest_fn
    open fun gen_openFileTest_fn() {
        val fileManager = uni_getFileSystemManager()
        fileManager.open(OpenFileOptions(filePath = "" + this.basePath + this.readFile, flag = "a", success = fun(res: OpenFileSuccessResult){
            if (this.logAble) {
                this.log += "openFileTest success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1187")
            this.fd = res.fd
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "openFileTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1194")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
        }
        , complete = fun(_){
            this.done = true
        }
        ))
    }
    open var openFileSyncTest = ::gen_openFileSyncTest_fn
    open fun gen_openFileSyncTest_fn(param: String, isTest: Boolean): String {
        val fileManager = uni_getFileSystemManager()
        try {
            var fd = fileManager.openSync(OpenFileSyncOptions(filePath = "" + this.basePath + this.readFile, flag = param))
            if (this.logAble && isTest) {
                this.log += "openFileSyncTest success:" + fd + "\n\n"
            }
            if (isTest) {
                this.done = true
            }
            this.fd = fd
            return fd
        }
         catch (e: Throwable) {
            if (this.logAble) {
                this.log += "openFileSyncTest fail:" + JSON.stringify(e) + "\n\n"
            }
            console.log("fail", e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1223")
            this.done = true
        }
        return ""
    }
    open var closeSyncTest = ::gen_closeSyncTest_fn
    open fun gen_closeSyncTest_fn() {
        console.log("closeSyncTest", " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1229")
        val fileManager = uni_getFileSystemManager()
        try {
            console.log("closeSync", " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1232")
            fileManager.closeSync(CloseSyncOptions(fd = this.openFileSyncTest("r", false)))
            if (this.logAble) {
                this.log += "closeSyncTest success:\n\n"
            }
            this.done = true
            this.closeFileRet = "close:ok"
        }
         catch (e: Throwable) {
            if (this.logAble) {
                this.log += "closeSyncTest fail:" + JSON.stringify(e) + "\n\n"
            }
            console.log("fail", e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1245")
            this.done = true
        }
    }
    open var closeTest = ::gen_closeTest_fn
    open fun gen_closeTest_fn() {
        val fileManager = uni_getFileSystemManager()
        fileManager.close(CloseOptions(fd = this.openFileSyncTest("r", false), success = fun(res: FileManagerSuccessResult){
            if (this.logAble) {
                this.log += "closeTest success:" + JSON.stringify(res) + "\n\n"
            }
            this.closeFileRet = res.errMsg
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1258")
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "closeTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1264")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
        }
        , complete = fun(_){
            this.done = true
        }
        ))
    }
    open var writeTest = ::gen_writeTest_fn
    open fun gen_writeTest_fn() {
        val fileManager = uni_getFileSystemManager()
        try {
            fileManager.mkdirSync("" + this.basePath + this.mkdirFile, true)
        }
         catch (e: Throwable) {
            console.error(e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1279")
        }
        fileManager.write(WriteOptions(fd = this.openFileSyncTest("w+", false), data = this.writeData, encoding = "utf-8", success = fun(res: WriteResult){
            if (this.logAble) {
                this.log += "writeTest success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1290")
            this.bytesWritten = res.bytesWritten
            this.lastFailError = UniError("uni-fileSystemManager", 0, "writeTest success:" + JSON.stringify(res))
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "writeTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1298")
            this.lastFailError = UniError(res.errSubject, res.errCode, "writeTest:" + res.errMsg)
        }
        , complete = fun(_){
            this.done = true
        }
        ))
    }
    open var writeSyncTest = ::gen_writeSyncTest_fn
    open fun gen_writeSyncTest_fn() {
        val fileManager = uni_getFileSystemManager()
        try {
            fileManager.mkdirSync("" + this.basePath + this.mkdirFile, true)
        }
         catch (e: Throwable) {
            console.error(e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1313")
        }
        fileManager.open(OpenFileOptions(filePath = "" + this.basePath + this.readFile, flag = "r+", success = fun(res: OpenFileSuccessResult){
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1319")
            if (res.fd.length <= 0) {
                this.done = true
                return
            }
            try {
                var ret = fileManager.writeSync(WriteSyncOptions(fd = res.fd, data = this.writeData, encoding = "utf-8"))
                if (this.logAble) {
                    this.log += "writeSyncTest success:" + JSON.stringify(ret) + "\n\n"
                }
                console.log("success", ret, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1333")
                this.done = true
                this.bytesWritten = ret.bytesWritten
            }
             catch (e: Throwable) {
                if (this.logAble) {
                    this.log += "writeSyncTest fail:" + JSON.stringify(e) + "\n\n"
                }
                console.log("fail", e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1340")
                this.done = true
            }
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "openFileTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1348")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
            this.done = true
        }
        ))
    }
    open var fstatTest = ::gen_fstatTest_fn
    open fun gen_fstatTest_fn() {
        val fileManager = uni_getFileSystemManager()
        fileManager.fstat(FStatOptions(fd = this.openFileSyncTest("r", false), success = fun(res: FStatSuccessResult){
            if (this.logAble) {
                this.log += "fstatTest success:" + JSON.stringify(res) + "\n\n"
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1364")
            this.fstat = res.stats
            this.fstatSize = res.stats.size
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "fstatTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1372")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
        }
        , complete = fun(_){
            this.done = true
        }
        ))
    }
    open var fstatSyncTest = ::gen_fstatSyncTest_fn
    open fun gen_fstatSyncTest_fn() {
        val fileManager = uni_getFileSystemManager()
        try {
            var stat = fileManager.fstatSync(FStatSyncOptions(fd = this.openFileSyncTest("r", false)))
            if (this.logAble) {
                this.log += "fstatSyncTest success:" + JSON.stringify(stat) + "\n\n"
            }
            this.done = true
            this.fstat = stat
            this.fstatSize = stat.size
        }
         catch (e: Throwable) {
            if (this.logAble) {
                this.log += "fstatSyncTest fail:" + JSON.stringify(e) + "\n\n"
            }
            this.done = true
        }
    }
    open var ftruncateFileTest = ::gen_ftruncateFileTest_fn
    open fun gen_ftruncateFileTest_fn() {
        val fileManager = uni_getFileSystemManager()
        fileManager.ftruncate(FTruncateFileOptions(fd = this.openFileSyncTest("r+", false), length = 6, success = fun(res: FileManagerSuccessResult){
            if (this.logAble) {
                this.log += "ftruncateFileTest success:" + JSON.stringify(res) + "\n\n"
            }
            this.ftruncateRet = res.errMsg
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "ftruncateFileTest fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1417")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
        }
        , complete = fun(res: Any){
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1422")
            this.done = true
            if (res is UniError) {
                this.lastCompleteError = res
            }
        }
        ))
    }
    open var ftruncateFileSyncTest = ::gen_ftruncateFileSyncTest_fn
    open fun gen_ftruncateFileSyncTest_fn() {
        val fileManager = uni_getFileSystemManager()
        try {
            fileManager.ftruncateSync(FTruncateFileSyncOptions(fd = this.openFileSyncTest("r+", false), length = 4))
            if (this.logAble) {
                this.log += "ftruncateFileSyncTest success:\n\n"
            }
            this.done = true
            this.ftruncateRet = "ftruncate:ok"
        }
         catch (e: Throwable) {
            if (this.logAble) {
                this.log += "ftruncateFileSyncTest fail:" + JSON.stringify(e) + "\n\n"
            }
            this.done = true
        }
    }
    open var readZipEntry = ::gen_readZipEntry_fn
    open fun gen_readZipEntry_fn() {
        val fileManager = uni_getFileSystemManager()
        fileManager.readZipEntry(ReadZipEntryOptions(filePath = "/static/filemanager/to.zip", encoding = "utf-8", success = fun(res: EntriesResult){
            if (this.logAble) {
                this.log += "readZipEntry success:size=" + res.result + "\n\n"
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1458")
        }
        , fail = fun(res: IUniError){
            if (this.logAble) {
                this.log += "readZipEntry fail:" + JSON.stringify(res) + "\n\n"
            }
            console.log("fail", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1464")
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
        }
        ))
    }
    open var testWriteReadFileBuffer = ::gen_testWriteReadFileBuffer_fn
    open fun gen_testWriteReadFileBuffer_fn() {
        val fs = uni_getFileSystemManager()
        var buffer = ArrayBuffer(16)
        var float64 = Float64Array(buffer)
        float64[1] = 1.2222222
        var that = this
        fs.writeFile(WriteFileOptions(filePath = "" + uni_env.USER_DATA_PATH + "/hello.txt", data = buffer, success = fun(res){
            if (this.logAble) {
                this.log += "testWriteReadFileBuffer ：" + res.errMsg + "\n\n"
            }
            console.log(res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1485")
            that.testReadFileBuffer()
        }
        , fail = fun(res){
            if (this.logAble) {
                this.log += "testWriteReadFileBuffer fail:" + JSON.stringify(res) + "\n\n"
            }
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
            console.error(res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1495")
            this.done = true
        }
        , complete = fun(_: Any){}))
    }
    open var testReadFileBuffer = ::gen_testReadFileBuffer_fn
    open fun gen_testReadFileBuffer_fn() {
        val fs = uni_getFileSystemManager()
        fs.readFile(ReadFileOptions(filePath = "" + uni_env.USER_DATA_PATH + "/hello.txt", success = fun(res){
            console.log(res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1510")
            var float64 = Float64Array(res.data as ArrayBuffer)
            if (this.logAble) {
                run {
                    var i: Number = 0
                    while(i < float64.length){
                        if (this.logAble) {
                            this.log += "testReadFileBuffer success ：" + ("Byte " + i + ": " + float64[i]) + "\n\n"
                        }
                        i++
                    }
                }
            }
            this.arrayBufferRes = float64[1]
            console.log(this.arrayBufferRes, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1524")
        }
        , fail = fun(res){
            if (this.logAble) {
                this.log += "testReadFileBuffer fail:" + JSON.stringify(res) + "\n\n"
            }
            this.lastFailError = UniError(res.errSubject, res.errCode, res.errMsg)
            console.error(res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1531")
        }
        , complete = fun(res: Any){
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1534")
            this.done = true
            if (res is UniError) {
                this.lastCompleteError = res
            }
        }
        ))
    }
    open var testWriteReadFileSyncBuffer = ::gen_testWriteReadFileSyncBuffer_fn
    open fun gen_testWriteReadFileSyncBuffer_fn() {
        try {
            val fs = uni_getFileSystemManager()
            var buffer = ArrayBuffer(16)
            var float64 = Float64Array(buffer)
            float64[1] = 1.333
            fs.writeFileSync("" + uni_env.USER_DATA_PATH + "/hello.txt", buffer, "utf-8")
            if (this.logAble) {
                this.log += "testWriteReadFileSyncBuffer success\n\n"
            }
            this.testReadFileSyncBuffer()
            this.done = true
        }
         catch (e: Throwable) {
            if (this.logAble) {
                this.log += "testWriteReadFileSyncBuffer fail:" + JSON.stringify(e) + "\n\n"
            }
            this.done = true
        }
    }
    open var testReadFileSyncBuffer = ::gen_testReadFileSyncBuffer_fn
    open fun gen_testReadFileSyncBuffer_fn() {
        try {
            val fs = uni_getFileSystemManager()
            var res = fs.readFileSync("" + uni_env.USER_DATA_PATH + "/hello.txt", null)
            console.log(res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1568")
            var float64 = Float64Array(res as ArrayBuffer)
            run {
                var i: Number = 0
                while(i < float64.length){
                    if (this.logAble) {
                        this.log += "testReadFileSyncBuffer success:" + ("Byte " + i + ": " + float64[i]) + "\n\n"
                    }
                    console.log("Byte " + i + ": " + float64[i], " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1574")
                    i++
                }
            }
            this.arrayBufferRes = float64[1]
            console.log(this.arrayBufferRes, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1577")
        }
         catch (e: Throwable) {
            if (this.logAble) {
                this.log += "testReadFileSyncBuffer fail:" + JSON.stringify(e) + "\n\n"
            }
            this.done = true
        }
    }
    open var testWriteReadBuffer = ::gen_testWriteReadBuffer_fn
    open fun gen_testWriteReadBuffer_fn() {
        val fileManager = uni_getFileSystemManager()
        try {
            fileManager.mkdirSync("" + this.basePath + this.mkdirFile, true)
        }
         catch (e: Throwable) {
            console.error(e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1592")
        }
        var buffer = ArrayBuffer(24)
        var float64 = Float64Array(buffer)
        float64[1] = 1.24
        float64[2] = 1.33
        fileManager.write(WriteOptions(fd = this.openFileSyncTest("w+", false), length = 16, offset = 8, data = buffer, position = 10, success = fun(res: WriteResult){
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1607")
            val ab = ArrayBuffer(24)
            fileManager.read(ReadOption(arrayBuffer = ab, fd = this.openFileSyncTest("a+", false), length = 8, position = 10, success = fun(res: ReadSuccessCallbackResult){
                console.log(res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1616")
                var value = Float64Array(res.arrayBuffer)
                run {
                    var i: Number = 0
                    while(i < value.length){
                        console.log("Byte " + i + ": " + value[i], " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1619")
                        if (this.logAble) {
                            this.log += "testWriteReadBuffer success:" + ("Byte " + i + ": " + value[i]) + "\n\n"
                        }
                        i++
                    }
                }
                this.arrayBufferRes = value[0]
                console.log(this.arrayBufferRes, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1625")
            }
            , fail = fun(res){
                console.log(res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1628")
                this.done = true
                if (res is UniError) {
                    this.lastCompleteError = res
                }
            }
            , complete = fun(res: Any){
                console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1635")
                this.done = true
                if (res is UniError) {
                    this.lastCompleteError = res
                }
            }
            ))
        }
        , fail = fun(res: IUniError){
            this.done = true
            if (res is UniError) {
                this.lastCompleteError = res
            }
        }
        , complete = fun(_){}))
    }
    open var testWriteReadSyncBuffer = ::gen_testWriteReadSyncBuffer_fn
    open fun gen_testWriteReadSyncBuffer_fn() {
        val fileManager = uni_getFileSystemManager()
        try {
            fileManager.mkdirSync("" + this.basePath + this.mkdirFile, true)
        }
         catch (e: Throwable) {}
        try {
            var buffer = ArrayBuffer(24)
            var float64 = Float64Array(buffer)
            float64[1] = 1.11
            float64[2] = 1.33
            var ret = fileManager.writeSync(WriteSyncOptions(fd = this.openFileSyncTest("w+", false), data = buffer, length = 16, offset = 8, position = 10, encoding = "utf-8"))
            console.log("success", ret, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1676")
            val ab = ArrayBuffer(24)
            var res = fileManager.readSync(ReadSyncOption(arrayBuffer = ab, fd = this.openFileSyncTest("a+", false), length = 8, offset = 8, position = 10))
            console.log(res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1686")
            var value = Float64Array(res.arrayBuffer)
            run {
                var i: Number = 0
                while(i < value.length){
                    if (this.logAble) {
                        this.log += "testWriteReadSyncBuffer success ：" + ("Byte " + i + ": " + value[i]) + "\n\n"
                    }
                    i++
                }
            }
            this.done = true
            this.arrayBufferRes = float64[1]
            console.log(this.arrayBufferRes, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1695")
        }
         catch (e: Throwable) {
            this.done = true
            console.error(e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1698")
        }
    }
    open var testAppendFileBufferSync = ::gen_testAppendFileBufferSync_fn
    open fun gen_testAppendFileBufferSync_fn() {
        val fs = uni_getFileSystemManager()
        try {
            var buffer = ArrayBuffer(24)
            var float64 = Float64Array(buffer)
            float64[1] = 1.2222222
            float64[2] = 1.33
            var ret = fs.writeSync(WriteSyncOptions(fd = fs.openSync(OpenFileSyncOptions(filePath = "" + uni_env.USER_DATA_PATH + "/hello.txt", flag = "w+")), data = buffer, length = 16, offset = 8, position = 10))
            console.log(ret, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1718")
            buffer = ArrayBuffer(16)
            float64 = Float64Array(buffer)
            float64[0] = 20
            fs.appendFileSync("" + uni_env.USER_DATA_PATH + "/hello.txt", buffer, null)
            val ab = ArrayBuffer(32)
            var readResult = fs.readSync(ReadSyncOption(fd = fs.openSync(OpenFileSyncOptions(filePath = "" + uni_env.USER_DATA_PATH + "/hello.txt", flag = "a+")), arrayBuffer = ab, length = 24, position = 10, offset = 8))
            console.log(readResult, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1738")
            float64 = Float64Array(readResult.arrayBuffer)
            run {
                var i: Number = 0
                while(i < float64.length){
                    if (this.logAble) {
                        this.log += "testAppendFileBufferSync success ：" + ("Byte " + i + ": " + float64[i]) + "\n\n"
                    }
                    console.log("Byte " + i + ": " + float64[i], " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1745")
                    i++
                }
            }
            this.arrayBufferRes = float64[3]
            console.log(this.arrayBufferRes, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1748")
            this.done = true
        }
         catch (e: Throwable) {
            this.done = true
            if (this.logAble) {
                this.log += "testAppendFileBufferSync fail:" + JSON.stringify(e) + "\n\n"
            }
            console.error(e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1755")
        }
    }
    open var testAppendFileBuffer = ::gen_testAppendFileBuffer_fn
    open fun gen_testAppendFileBuffer_fn() {
        val fs = uni_getFileSystemManager()
        try {
            var buffer = ArrayBuffer(24)
            var float64 = Float64Array(buffer)
            float64[1] = 1.2222222
            float64[2] = 1.33
            var ret = fs.writeSync(WriteSyncOptions(fd = fs.openSync(OpenFileSyncOptions(filePath = "" + uni_env.USER_DATA_PATH + "/hello.txt", flag = "w+")), data = buffer, length = 16, offset = 8, position = 10))
            console.log(ret, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1774")
            buffer = ArrayBuffer(16)
            float64 = Float64Array(buffer)
            float64[0] = 21
            fs.appendFile(AppendFileOptions(filePath = "" + uni_env.USER_DATA_PATH + "/hello.txt", data = buffer, success = fun(res){
                console.log(res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1783")
                val ab = ArrayBuffer(32)
                var readResult = fs.readSync(ReadSyncOption(fd = fs.openSync(OpenFileSyncOptions(filePath = "" + uni_env.USER_DATA_PATH + "/hello.txt", flag = "a+")), arrayBuffer = ab, length = 24, position = 10, offset = 8))
                console.log(readResult, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1797")
                float64 = Float64Array(readResult.arrayBuffer)
                run {
                    var i: Number = 0
                    while(i < float64.length){
                        console.log("Byte " + i + ": " + float64[i], " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1801")
                        if (this.logAble) {
                            this.log += "testAppendFileBuffer success ：" + ("Byte " + i + ": " + float64[i]) + "\n\n"
                        }
                        i++
                    }
                }
                this.arrayBufferRes = float64[3]
                console.log(this.arrayBufferRes, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1807")
            }
            , fail = fun(res){
                console.log(res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1810")
            }
            , complete = fun(_){
                this.done = true
            }
            ))
        }
         catch (e: Throwable) {
            this.done = true
            if (this.logAble) {
                this.log += "testAppendFileBufferSync fail:" + JSON.stringify(e) + "\n\n"
            }
            console.error(e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1822")
        }
    }
    open var testReadFileEncoding = ::gen_testReadFileEncoding_fn
    open fun gen_testReadFileEncoding_fn(encoding: String) {
        console.log("testEncoding", encoding, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1827")
        uni_chooseImage(ChooseImageOptions(count = 1, albumMode = "system", sizeType = utsArrayOf(
            "original"
        ), sourceType = utsArrayOf(
            "album"
        ), success = fun(e){
            console.log(e.tempFilePaths[0], " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1834")
            uni_getFileSystemManager().readFile(ReadFileOptions(filePath = e.tempFilePaths[0], encoding = encoding, success = fun(res){
                console.log("success:", (res.data as String).length, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1839")
            }
            ))
        }
        ))
    }
    open var testReadFileArrayBuffer = ::gen_testReadFileArrayBuffer_fn
    open fun gen_testReadFileArrayBuffer_fn() {
        uni_chooseImage(ChooseImageOptions(count = 1, albumMode = "system", sizeType = utsArrayOf(
            "original"
        ), sourceType = utsArrayOf(
            "album"
        ), success = fun(e){
            console.log(e.tempFilePaths[0], " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1852")
            uni_getFileSystemManager().readFile(ReadFileOptions(filePath = e.tempFilePaths[0], success = fun(res){
                console.log("success:", (res.data as ArrayBuffer).byteLength, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1856")
            }
            ))
        }
        ))
    }
    open var testReadFileSyncEncoding = ::gen_testReadFileSyncEncoding_fn
    open fun gen_testReadFileSyncEncoding_fn(encoding: String) {
        console.log("testEncoding", encoding, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1863")
        uni_chooseImage(ChooseImageOptions(count = 1, albumMode = "system", sizeType = utsArrayOf(
            "original"
        ), sourceType = utsArrayOf(
            "album"
        ), success = fun(e){
            console.log(e.tempFilePaths[0], " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1870")
            try {
                var res = uni_getFileSystemManager().readFileSync(e.tempFilePaths[0], encoding)
                console.log("success:", (res as String).length, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1873")
            }
             catch (e: Throwable) {
                console.log(e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1875")
            }
        }
        ))
    }
    open var testReadFileSyncArrayBuffer = ::gen_testReadFileSyncArrayBuffer_fn
    open fun gen_testReadFileSyncArrayBuffer_fn() {
        uni_chooseImage(ChooseImageOptions(count = 1, albumMode = "system", sizeType = utsArrayOf(
            "original"
        ), sourceType = utsArrayOf(
            "album"
        ), success = fun(e){
            console.log(e.tempFilePaths[0], " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1887")
            try {
                var res = uni_getFileSystemManager().readFileSync(e.tempFilePaths[0], null)
                console.log("success:", (res as ArrayBuffer).byteLength, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1890")
            }
             catch (e: Throwable) {
                console.log(e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1892")
            }
        }
        ))
    }
    open var copyFileByContent = ::gen_copyFileByContent_fn
    open fun gen_copyFileByContent_fn() {
        uni_chooseImage(ChooseImageOptions(count = 1, albumMode = "system", sizeType = utsArrayOf(
            "original"
        ), sourceType = utsArrayOf(
            "album"
        ), success = fun(e){
            console.log(e.tempFilePaths[0], " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1904")
            uni_getFileSystemManager().copyFile(CopyFileOptions(srcPath = e.tempFilePaths[0], destPath = uni_env.CACHE_PATH + "copyFileByContent.jpg", complete = fun(res){
                console.log("success:", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1909")
            }
            ))
        }
        ))
    }
    open var copyFileSyncByContent = ::gen_copyFileSyncByContent_fn
    open fun gen_copyFileSyncByContent_fn() {
        uni_chooseImage(ChooseImageOptions(count = 1, albumMode = "system", sizeType = utsArrayOf(
            "original"
        ), sourceType = utsArrayOf(
            "album"
        ), success = fun(e){
            console.log(e.tempFilePaths[0], " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1922")
            try {
                var res = uni_getFileSystemManager().copyFileSync(e.tempFilePaths[0], uni_env.CACHE_PATH + "copyFileSyncByContent.jpg")
                console.log("success:", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1925")
            }
             catch (e: Throwable) {
                console.log(e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1927")
            }
        }
        ))
    }
    open var gotoExplore = ::gen_gotoExplore_fn
    open fun gen_gotoExplore_fn() {
        uni_navigateTo(NavigateToOptions(url = "/pages/API/get-file-system-manager/filemanage"))
    }
    open var gotoTestStatic = ::gen_gotoTestStatic_fn
    open fun gen_gotoTestStatic_fn() {
        uni_navigateTo(NavigateToOptions(url = "/pages/API/get-file-system-manager/testStatic"))
    }
    open var getFileInfoByContent = ::gen_getFileInfoByContent_fn
    open fun gen_getFileInfoByContent_fn(event: UniPointerEvent) {
        uni_chooseFile(ChooseFileOptions(count = 1, success = fun(e) {
            console.log(JSON.stringify(e.tempFiles[0].path), " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1947")
            uni_getFileSystemManager().getFileInfo(GetFileInfoOptions(filePath = e.tempFiles[0].path, success = fun(e2) {
                console.log("success:", e2, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1951")
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
