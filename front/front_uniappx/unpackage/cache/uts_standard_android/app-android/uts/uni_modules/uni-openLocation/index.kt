@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER", "NAME_SHADOWING", "UNNECESSARY_NOT_NULL_ASSERTION")
package uts.sdk.modules.uniOpenLocation
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
import io.dcloud.uniapp.extapi.`$off` as uni__off
import io.dcloud.uniapp.extapi.`$on` as uni__on
import io.dcloud.uniapp.extapi.openDialogPage as uni_openDialogPage
typealias OpenLocationErrorCode = Number
interface OpenLocationSuccess {
    var errMsg: String
}
typealias OpenLocationSuccessCallback = (result: OpenLocationSuccess) -> Unit
interface OpenLocationFail : IUniError {
    override var errCode: OpenLocationErrorCode
}
typealias OpenLocationFailCallback = (result: OpenLocationFail) -> Unit
typealias OpenLocationComplete = Any
typealias OpenLocationCompleteCallback = (result: OpenLocationComplete) -> Unit
open class OpenLocationOptions (
    @JsonNotNull
    open var latitude: Number,
    @JsonNotNull
    open var longitude: Number,
    open var scale: Number? = null,
    open var name: String? = null,
    open var address: String? = null,
    open var success: OpenLocationSuccessCallback? = null,
    open var fail: OpenLocationFailCallback? = null,
    open var complete: OpenLocationCompleteCallback? = null,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("OpenLocationOptions", "uni_modules/uni-openLocation/utssdk/interface.uts", 51, 13)
    }
}
typealias OpenLocation = (options: OpenLocationOptions) -> Unit
open class OpenLocationSuccessImpl : OpenLocationSuccess, IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("OpenLocationSuccessImpl", "uni_modules/uni-openLocation/utssdk/interface.uts", 405, 14)
    }
    override var errMsg: String
    constructor(errMsg: String = "chooseLocation:ok"){
        this.errMsg = errMsg
    }
}
val UniErrorSubject = "uni-openLocation"
val UniErrors: Map<OpenLocationErrorCode, String> = Map(utsArrayOf(
    utsArrayOf(
        4,
        "internal error"
    )
))
open class OpenLocationFailImpl : UniError, OpenLocationFail, IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("OpenLocationFailImpl", "uni_modules/uni-openLocation/utssdk/unierror.uts", 16, 14)
    }
    override var errCode: OpenLocationErrorCode
    constructor(errCode: OpenLocationErrorCode) : super() {
        this.errSubject = UniErrorSubject
        this.errCode = errCode
        this.errMsg = UniErrors.get(errCode) ?: ""
    }
}
val openLocation: OpenLocation = fun(options: OpenLocationOptions) {
    val uuid = "" + Date.now() + Math.floor(Math.random() * 1e7)
    val baseEventName = "uni_open_location_" + uuid
    val readyEventName = "" + baseEventName + "_ready"
    val optionsEventName = "" + baseEventName + "_options"
    val successEventName = "" + baseEventName + "_success"
    val failEventName = "" + baseEventName + "_fail"
    val readyEventId = uni__on(readyEventName, fun(){
        uni__emit(optionsEventName, UTSAndroid.consoleDebugError(JSON.parse(JSON.stringify(options)), " at uni_modules/uni-openLocation/utssdk/app-android/index.uts:11"))
    }
    )
    val successEventId = uni__on(successEventName, fun(){
        val res = OpenLocationSuccessImpl()
        options.success?.invoke(res)
        options.complete?.invoke(res)
    }
    )
    val failEventId = uni__on(failEventName, fun(errCode: OpenLocationErrorCode){
        val res = OpenLocationFailImpl(errCode)
        options.fail?.invoke(res)
        options.complete?.invoke(res)
    }
    )
    uni_openDialogPage(OpenDialogPageOptions(url = "/uni_modules/uni-openLocation/pages/openLocation/openLocation?readyEventName=" + readyEventName + "&optionsEventName=" + optionsEventName + "&successEventName=" + successEventName + "&failEventName=" + failEventName, fail = fun(err) {
        val res = OpenLocationFailImpl(4)
        options.fail?.invoke(res)
        options.complete?.invoke(res)
        uni__off(readyEventName, readyEventId)
        uni__off(successEventName, successEventId)
        uni__off(failEventName, failEventId)
    }
    ))
}
