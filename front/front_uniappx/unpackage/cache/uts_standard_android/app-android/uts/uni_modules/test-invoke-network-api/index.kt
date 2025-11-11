@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER", "NAME_SHADOWING", "UNNECESSARY_NOT_NULL_ASSERTION")
package uts.sdk.modules.testInvokeNetworkApi
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
import io.dcloud.uniapp.extapi.env as uni_env
import io.dcloud.uniapp.extapi.request as uni_request
import io.dcloud.uniapp.extapi.uploadFile as uni_uploadFile
open class CommonOptions (
    open var success: ((res: Any) -> Unit)? = null,
    open var fail: ((res: Any) -> Unit)? = null,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("CommonOptions", "uni_modules/test-invoke-network-api/utssdk/interface.uts", 1, 13)
    }
}
fun testInovkeRequest(options: CommonOptions): Unit {
    uni_request<Any>(RequestOptions(url = "https://request.dcloud.net.cn/api/http/method/post", method = "POST", header = object : UTSJSONObject() {
        var Cookie = "token11222"
    }, timeout = 6000, sslVerify = false, withCredentials = false, data = object : UTSJSONObject() {
        var platform = object : UTSJSONObject() {
            var abc = "xyq"
        }
    }, firstIpv4 = false, success = fun(res: RequestSuccess<Any>){
        options.success?.invoke(res)
    }
    , fail = fun(e: RequestFail) {
        options.fail?.invoke(e)
    }
    ))
}
fun testInovkeUploadFile(options: CommonOptions): Unit {
    val imageSrc = "/static/uni.png"
    uni_uploadFile(UploadFileOptions(url = "https://unidemo.dcloud.net.cn/upload", files = utsArrayOf(
        UploadFileOptionFiles(name = "file1", uri = imageSrc),
        UploadFileOptionFiles(name = "file2", uri = imageSrc)
    ), success = fun(res: UploadFileSuccess){
        options.success?.invoke(res)
    }
    , fail = fun(err: UploadFileFail){
        options.fail?.invoke(err)
    }
    ))
}
fun testInovkeDownloadFile(options: CommonOptions): Unit {
    uni_downloadFile(DownloadFileOptions(url = "https://qiniu-web-assets.dcloud.net.cn/unidoc/zh/uni-app.png", filePath = "" + uni_env.CACHE_PATH + "/halo/world/", success = fun(res: DownloadFileSuccess) {
        options.success?.invoke(res)
    }
    , fail = fun(e: DownloadFileFail) {
        options.fail?.invoke(e)
    }
    ))
}
