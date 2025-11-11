@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER", "NAME_SHADOWING", "UNNECESSARY_NOT_NULL_ASSERTION")
package uts.sdk.modules.uniStat
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
import io.dcloud.uniapp.extapi.__deleteAppCrashInfo as uni___deleteAppCrashInfo
import io.dcloud.uniapp.extapi.__getAppCrashInfo as uni___getAppCrashInfo
import io.dcloud.uniapp.extapi.addInterceptor as uni_addInterceptor
import io.dcloud.uniapp.extapi.getAppBaseInfo as uni_getAppBaseInfo
import io.dcloud.uniapp.extapi.getDeviceInfo as uni_getDeviceInfo
import io.dcloud.uniapp.extapi.getFileSystemManager as uni_getFileSystemManager
import io.dcloud.uniapp.extapi.getNetworkType as uni_getNetworkType
import io.dcloud.uniapp.extapi.getStorageSync as uni_getStorageSync
import io.dcloud.uniapp.extapi.getSystemInfoSync as uni_getSystemInfoSync
import io.dcloud.uniapp.extapi.setStorageSync as uni_setStorageSync
open class ReportSuccess (
    @JsonNotNull
    open var errMsg: String,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("ReportSuccess", "uni_modules/uni-stat/utssdk/interface.uts", 4, 13)
    }
}
typealias ReportErrorCode = Number
interface ReportError : IUniError {
    override var errCode: ReportErrorCode
}
typealias ReportFail = ReportError
typealias ReportSuccessCallback = (res: ReportSuccess) -> Unit
typealias ReportFailCallback = (err: ReportFail) -> Unit
typealias ReportCompleteCallback = (res: Any) -> Unit
open class ReportOptions (
    @JsonNotNull
    open var name: String,
    open var options: Any? = null,
    open var success: ReportSuccessCallback? = null,
    open var fail: ReportFailCallback? = null,
    open var complete: ReportCompleteCallback? = null,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("ReportOptions", "uni_modules/uni-stat/utssdk/interface.uts", 78, 13)
    }
}
typealias Report = (options: ReportOptions) -> Unit
open class UniStatOptions (
    open var enable: Boolean? = null,
    open var debug: Boolean? = null,
    open var reportInterval: Number? = null,
    open var uniCloud: UniCloudInitOptions? = null,
    open var collectItems: UniStatCollectItemsOptions? = null,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("UniStatOptions", "uni_modules/uni-stat/utssdk/interface.uts", 153, 13)
    }
}
open class UniStatCollectItemsOptions (
    open var uniPushClientID: Boolean? = null,
    open var uniStatPageLog: Boolean? = null,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("UniStatCollectItemsOptions", "uni_modules/uni-stat/utssdk/interface.uts", 174, 13)
    }
}
open class OnLaunchOptionsWithCst (
    open var path: String? = null,
    open var cst: Number? = null,
    open var scene: Number? = null,
    open var query: String? = null,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("OnLaunchOptionsWithCst", "uni_modules/uni-stat/utssdk/interface.uts", 189, 13)
    }
}
open class ResidenceTimeReturn (
    @JsonNotNull
    open var residenceTime: Number,
    @JsonNotNull
    open var overtime: Boolean = false,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("ResidenceTimeReturn", "uni_modules/uni-stat/utssdk/interface.uts", 202, 13)
    }
}
open class RouteParams (
    @JsonNotNull
    open var path: String,
    @JsonNotNull
    open var fullpath: String,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("RouteParams", "uni_modules/uni-stat/utssdk/interface.uts", 211, 13)
    }
}
open class TitleConfigParams (
    @JsonNotNull
    open var config: String,
    @JsonNotNull
    open var page: String,
    @JsonNotNull
    open var report: String,
    @JsonNotNull
    open var lt: String,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("TitleConfigParams", "uni_modules/uni-stat/utssdk/interface.uts", 218, 13)
    }
}
open class PageParams (
    open var url: String? = null,
    open var ttpj: String? = null,
    open var ttn: String? = null,
    open var ttc: String? = null,
    open var ttct: String? = null,
    @JsonNotNull
    open var urlref: String,
    @JsonNotNull
    open var urlref_ts: Number,
    open var urlref_tt: String? = null,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("PageParams", "uni_modules/uni-stat/utssdk/interface.uts", 231, 13)
    }
}
open class RequestData (
    @JsonNotNull
    open var usv: String,
    @JsonNotNull
    open var t: Number,
    @JsonNotNull
    open var requests: String,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("RequestData", "uni_modules/uni-stat/utssdk/interface.uts", 252, 13)
    }
}
open class EventParams (
    @JsonNotNull
    open var key: String,
    open var value: String? = null,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("EventParams", "uni_modules/uni-stat/utssdk/interface.uts", 278, 13)
    }
}
open class StatDefault (
    @JsonNotNull
    open var uuid: String,
    @JsonNotNull
    open var ak: String,
    @JsonNotNull
    open var p: String,
    @JsonNotNull
    open var ut: String,
    open var mpn: String? = null,
    open var usv: String? = null,
    open var v: String? = null,
    open var ch: String? = null,
    open var cn: String? = null,
    open var pn: String? = null,
    open var ct: String? = null,
    open var t: Number? = null,
    open var tt: String? = null,
    open var brand: String? = null,
    open var md: String? = null,
    open var sv: String? = null,
    open var mpsdk: String? = null,
    open var mpv: String? = null,
    open var lang: String? = null,
    open var pr: Number? = null,
    open var ww: Number? = null,
    open var wh: Number? = null,
    open var sw: Number? = null,
    open var sh: Number? = null,
    open var sc: Number? = null,
    open var lt: String? = null,
    open var odid: String? = null,
    open var url: String? = null,
    open var fvts: Number? = null,
    open var lvts: Number? = null,
    open var tvc: Number? = null,
    open var cst: Number? = null,
    open var urlref_ts: Number? = null,
    open var urlref: String? = null,
    open var ttpj: String? = null,
    open var ttn: String? = null,
    open var ttc: String? = null,
    open var ttct: String? = null,
    open var cid: String? = null,
    open var e_n: String? = null,
    open var e_v: String? = null,
    open var lat: String? = null,
    open var lng: String? = null,
    open var net: String? = null,
    open var em: String? = null,
    open var root: Number? = null,
    open var pv: String? = null,
    open var log: String? = null,
    open var did: String? = null,
    open var os: String? = null,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("StatDefault", "uni_modules/uni-stat/utssdk/interface.uts", 287, 13)
    }
}
typealias ErrorCallback = (is_err: Boolean, code: ReportErrorCode) -> Unit
val ReportUniErrorSubject = "uni-report"
val ReportUniErrors: Map<Number, String> = Map(utsArrayOf(
    utsArrayOf(
        61000,
        "应用已集成uni统计，但未关联服务空间，请在uniCloud目录右键关联服务空间!"
    ),
    utsArrayOf(
        61001,
        "统计服务尚未初始化，请在main.uts中引入统计插件！"
    ),
    utsArrayOf(
        61002,
        "uni-app-launch 下 options 参数必填，请检查！"
    ),
    utsArrayOf(
        61003,
        "Report的 name参数必填"
    ),
    utsArrayOf(
        61004,
        "Report的name参数类型必须为字符串"
    ),
    utsArrayOf(
        61005,
        "Report的name参数长度最大为255"
    ),
    utsArrayOf(
        61006,
        "Report的options参数只能为String或者Object类型"
    ),
    utsArrayOf(
        61007,
        "Report的options参数若为String类型，则长度最大为255"
    ),
    utsArrayOf(
        61008,
        "Report的name参数为title时，options参数类型只能为String"
    )
))
open class ReportFailImpl : UniError, ReportError, IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("ReportFailImpl", "uni_modules/uni-stat/utssdk/unierror.uts", 33, 14)
    }
    override var errCode: ReportErrorCode
    constructor(errCode: ReportErrorCode) : super() {
        this.errSubject = ReportUniErrorSubject
        this.errCode = errCode
        this.errMsg = ReportUniErrors.get(errCode) ?: ""
    }
}
val APPID = uni_getSystemInfoSync().appId
fun <T> dbSet(name: String, value: T) {
    var data = uni_getStorageSync("UNI_STAT_DATA:" + APPID)
    var newData: UTSJSONObject = object : UTSJSONObject(UTSSourceMapPosition("newData", "uni_modules/uni-stat/utssdk/common/utils/db.uts", 4, 9)) {
    }
    if (data != null && UTSAndroid.`typeof`(data) == "object") {
        newData = data as UTSJSONObject
    }
    newData[name] = value
    uni_setStorageSync("UNI_STAT_DATA:" + APPID, newData)
}
fun <T> dbGet(name: String): T? {
    var data = uni_getStorageSync("UNI_STAT_DATA:" + APPID)
    if (data == null) {
        return null
    }
    if (UTSAndroid.`typeof`(data) != "object") {
        return null
    }
    val newData = data as UTSJSONObject
    return newData[name] as T
}
fun dbRemove(name: String) {
    var data = uni_getStorageSync("UNI_STAT_DATA:" + APPID)
    if (data == "") {
        data = UTSJSONObject()
    }
    if (data != null) {
        var newData = data as UTSJSONObject
        if (newData[name] != null) {
            newData[name] = null
            uni_setStorageSync("UNI_STAT_DATA:" + APPID, newData)
        }
    }
}
val sys = uni_getSystemInfoSync()
val device = uni_getDeviceInfo(null)
val sysAppBase = uni_getAppBaseInfo(null)
val STAT_VERSION = sys.uniCompilerVersion
val PAGE_PVER_TIME: Number = 1800
val APP_PVER_TIME: Number = 300
val OPERATING_TIME: Number = 10
open class ConfigData : IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("ConfigData", "uni_modules/uni-stat/utssdk/common/config.uts", 11, 7)
    }
    private var options: UniStatOptions? = null
    private constructor(){}
    open fun setOptions(reassignedOptions: UniStatOptions?) {
        var options = reassignedOptions
        if (options == null) {
            options = UniStatOptions()
        }
        if (options.enable == null) {
            options.enable = true
        }
        if (options.debug == null) {
            options.debug = false
        }
        if (options.reportInterval == null) {
            options.reportInterval = 10
        }
        if (options.collectItems == null) {
            options.collectItems = UniStatCollectItemsOptions()
        }
        var collectItems = options.collectItems as UniStatCollectItemsOptions
        if (collectItems.uniPushClientID == null) {
            collectItems.uniPushClientID = false
        }
        if (collectItems.uniStatPageLog == null) {
            collectItems.uniStatPageLog = true
        }
        options.collectItems = collectItems
        this.options = options
    }
    open fun getOptions(): UniStatOptions {
        if (this.options == null) {
            this.setOptions(null)
        }
        return this.options as UniStatOptions
    }
    companion object {
        var __config_instance: ConfigData? = null
        public fun getInstance(): ConfigData {
            if (ConfigData.__config_instance == null) {
                ConfigData.__config_instance = ConfigData()
            }
            return ConfigData.__config_instance as ConfigData
        }
    }
}
val Config = ConfigData.getInstance()
val FIRST_VISIT_TIME_KEY = "__first__visit__time"
val LAST_VISIT_TIME_KEY = "__last__visit__time"
val TOTAL_VISIT_COUNT = "__total__visit__count"
val FIRST_TIME = "__first_time"
val PAGE_RESIDENCE_TIME = "__page__residence__time"
var First_Page_Residence_Time: Number = 0
var Last_Page_Residence_Time: Number = 0
val get_time = fun(): Number {
    return Math.floor(Date().getTime() / 1000)
}
val set_first_time = fun(): Number {
    var time = get_time()
    dbSet(FIRST_TIME, time)
    return time
}
val get_first_visit_time = fun(): Number {
    val timeStorge = dbGet<Number>(FIRST_VISIT_TIME_KEY)
    var time: Number
    if (timeStorge != null && timeStorge != 0) {
        time = timeStorge as Number
    } else {
        time = get_time()
        dbSet(FIRST_VISIT_TIME_KEY, time)
        dbRemove(LAST_VISIT_TIME_KEY)
    }
    return time
}
val get_last_visit_time = fun(): Number {
    val timeStorge = dbGet<Number>(LAST_VISIT_TIME_KEY)
    var time: Number = 0
    if (timeStorge != null && timeStorge != 0) {
        time = timeStorge as Number
    }
    dbSet(LAST_VISIT_TIME_KEY, get_time())
    return time
}
val get_total_visit_count = fun(): Number {
    val timeStorge = dbGet<Number>(TOTAL_VISIT_COUNT)
    var count: Number = 1
    if (timeStorge != null) {
        count = timeStorge as Number
        count++
    }
    dbSet(TOTAL_VISIT_COUNT, count)
    return count
}
val get_residence_time = fun(type: String): ResidenceTimeReturn {
    var residenceTime: Number = 0
    val last_time = get_time()
    val first_time = (dbGet(FIRST_TIME) ?: last_time) as Number
    if (first_time != 0) {
        residenceTime = last_time - first_time
    }
    residenceTime = if (residenceTime < 1) {
        1
    } else {
        residenceTime
    }
    var timeData = ResidenceTimeReturn(residenceTime = residenceTime, overtime = false)
    if (type === "app") {
        var overtime = if (residenceTime > APP_PVER_TIME) {
            true
        } else {
            false
        }
        timeData.overtime = overtime
        return timeData
    }
    if (type === "page") {
        var overtime = if (residenceTime > PAGE_PVER_TIME) {
            true
        } else {
            false
        }
        timeData.overtime = overtime
        return timeData
    }
    return timeData
}
val set_page_residence_time = fun(): Number {
    First_Page_Residence_Time = get_time()
    dbSet(PAGE_RESIDENCE_TIME, First_Page_Residence_Time)
    return First_Page_Residence_Time
}
val get_page_residence_time = fun(): Number {
    Last_Page_Residence_Time = get_time()
    First_Page_Residence_Time = dbGet<Number>(PAGE_RESIDENCE_TIME) ?: 0
    val diff: Number = Last_Page_Residence_Time - First_Page_Residence_Time
    return diff
}
open class StatType : IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("StatType", "uni_modules/uni-stat/utssdk/common/core/stat-type.uts", 4, 14)
    }
    companion object {
        var LifeCycleLaunch: Number = 1
        var LifeCycleLoad: Number = 2
        var LifeCycleAppShow: Number = 3
        var LifeCycleAppHide: Number = 4
        var LifeCyclePageShow: Number = 5
        var LifeCyclePageHide: Number = 6
        var LifeCyclePageUnLoad: Number = 7
        var LifeCycleError: Number = 8
        var Crash: Number = 9
    }
}
open class ReportType : IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("ReportType", "uni_modules/uni-stat/utssdk/common/core/stat-type.uts", 27, 14)
    }
    companion object {
        var AppStart: String = "1"
        var AppShow: String = "2"
        var AppHide: String = "3"
        var AppExit: String = "4"
        var PageShow: String = "11"
        var PageHide: String = "12"
        var Event: String = "21"
        var UTSError: String = "31"
        var Push: String = "101"
    }
}
open class ReprotCstType : IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("ReprotCstType", "uni_modules/uni-stat/utssdk/common/core/stat-type.uts", 55, 14)
    }
    companion object {
        var AppNormal: Number = 1
        var AppAwakenTimeout: Number = 2
        var PageStayTimeout: Number = 3
    }
}
val AppShowParamsKeys = utsArrayOf(
    "uuid",
    "ak",
    "lt",
    "ut",
    "mpsdk",
    "mpv",
    "mpn",
    "v",
    "p",
    "sv",
    "net",
    "brand",
    "md",
    "lang",
    "lat",
    "lng",
    "pr",
    "ww",
    "wh",
    "sw",
    "sh",
    "url",
    "tt",
    "ch",
    "fvts",
    "lvts",
    "cn",
    "pn",
    "ct",
    "sc",
    "tvc",
    "usv",
    "t",
    "odid",
    "cst"
)
val AppHideParamsKeys = utsArrayOf(
    "ak",
    "uuid",
    "ttn",
    "ttpj",
    "ttc",
    "lt",
    "ut",
    "p",
    "urlref",
    "urlref_ts",
    "ch",
    "usv",
    "t"
)
val PageShowParamsKeys = utsArrayOf(
    "ak",
    "uuid",
    "lt",
    "ut",
    "p",
    "url",
    "ttpj",
    "ttn",
    "ttc",
    "ttct",
    "urlref",
    "urlref_ts",
    "ch",
    "usv",
    "t"
)
val PushParamsKeys = utsArrayOf(
    "lt",
    "cid",
    "t",
    "ut"
)
val EventParamsKeys = utsArrayOf(
    "ak",
    "uuid",
    "p",
    "lt",
    "ut",
    "url",
    "ch",
    "e_n",
    "e_v",
    "usv",
    "t"
)
val ErrorParamsKeys = utsArrayOf(
    "ak",
    "uuid",
    "p",
    "lt",
    "url",
    "ut",
    "ch",
    "mpsdk",
    "mpv",
    "v",
    "em",
    "usv",
    "t"
)
val CrashParamsKeys = utsArrayOf(
    "lt",
    "ak",
    "v",
    "ut",
    "p",
    "ch",
    "mpsdk",
    "did",
    "net",
    "os",
    "sv",
    "brand",
    "md",
    "root",
    "pn",
    "pv",
    "log"
)
fun Serialize(obj: UTSJSONObject): String {
    var str: UTSArray<String> = utsArrayOf()
    for(p in resolveUTSKeyIterator(obj)){
        if (obj.hasOwnProperty(p)) {
            var key = obj[p]
            if (obj[p] == null) {
                key = ""
            }
            val text = p + "=" + key
            str.push(text)
        }
    }
    return "?" + str.join("&")
}
fun IsNumber(value: Any?): Boolean {
    return UTSAndroid.`typeof`(value) === "number"
}
fun <T> FilterParam(keys: UTSArray<String> = utsArrayOf(), data: StatDefault): T {
    var result: UTSJSONObject = object : UTSJSONObject(UTSSourceMapPosition("result", "uni_modules/uni-stat/utssdk/common/utils/index.uts", 38, 9)) {
    }
    keys.forEach(fun(key){
        result[key] = data[key]
    }
    )
    var formdata: T = result as T
    return formdata
}
fun Log(data: StatDefault, type: Boolean = false) {
    var logData = getEventData(data.lt!!, data)
    var msg_type: String = ""
    when (data.lt) {
        "1" -> 
            msg_type = "应用启动"
        "3" -> 
            msg_type = "应用进入后台"
        "11" -> 
            msg_type = "页面切换"
        "21" -> 
            msg_type = "事件触发"
        "31" -> 
            msg_type = "应用错误"
        "41" -> 
            msg_type = "应用崩溃"
        "101" -> 
            msg_type = "PUSH"
    }
    if (type) {
        console.log("=== \u7EDF\u8BA1\u961F\u5217\u6570\u636E\u4E0A\u62A5 :", logData, " at uni_modules/uni-stat/utssdk/common/utils/index.uts:87")
        return
    }
    if (msg_type != "") {
        console.log("=== \u7EDF\u8BA1\u6570\u636E\u91C7\u96C6\uFF1A" + msg_type + " :", logData, " at uni_modules/uni-stat/utssdk/common/utils/index.uts:94")
    }
}
fun Map2String(statData: Map<String, UTSArray<StatDefault>>): String {
    var statDataJson: UTSJSONObject = object : UTSJSONObject(UTSSourceMapPosition("statDataJson", "uni_modules/uni-stat/utssdk/common/utils/index.uts", 92, 9)) {
    }
    statData.forEach(fun(rd: UTSArray<StatDefault>, key: String){
        statDataJson[key] = rd
    }
    )
    return JSON.stringify(statDataJson)
}
fun Map2Json(statData: Map<String, UTSArray<StatDefault>>): UTSJSONObject {
    var statDataJson: UTSJSONObject = object : UTSJSONObject(UTSSourceMapPosition("statDataJson", "uni_modules/uni-stat/utssdk/common/utils/index.uts", 99, 9)) {
    }
    statData.forEach(fun(rd: UTSArray<StatDefault>, key: String){
        var arr: UTSArray<Any> = utsArrayOf()
        rd.forEach(fun(elm: StatDefault){
            var data = getEventData(key, elm)
            arr.push(data)
        }
        )
        statDataJson[key] = arr
    }
    )
    return statDataJson
}
val handle_data = fun(statData: Map<String, UTSArray<StatDefault>>): String {
    var firstArr: UTSArray<Any> = utsArrayOf()
    var contentArr: UTSArray<Any> = utsArrayOf()
    var lastArr: UTSArray<Any> = utsArrayOf()
    statData.forEach(fun(rd: UTSArray<StatDefault>, key: String){
        rd.forEach(fun(elm: StatDefault){
            var data = getEventData(key, elm)
            if (key == "1") {
                firstArr.push(data)
            } else if (key == "4") {
                lastArr.push(data)
            } else {
                contentArr.push(data)
            }
        }
        )
    }
    )
    firstArr.push(*contentArr.toTypedArray(), *lastArr.toTypedArray())
    return JSON.stringify(firstArr)
}
fun getEventData(lt: String, elm: StatDefault): Any {
    var data: Any = UTSJSONObject()
    when (lt) {
        "1" -> 
            data = FilterParam(AppShowParamsKeys, elm)
        "3" -> 
            data = FilterParam(AppHideParamsKeys, elm)
        "11" -> 
            data = FilterParam(PageShowParamsKeys, elm)
        "21" -> 
            data = FilterParam(EventParamsKeys, elm)
        "31" -> 
            data = FilterParam(ErrorParamsKeys, elm)
        "41" -> 
            data = FilterParam(CrashParamsKeys, elm)
        "101" -> 
            data = FilterParam(PushParamsKeys, elm)
    }
    return data
}
val APPID1 = sys.appId
val RUNTIME_VERSION = sys.appVersion
val get_uuid = fun(): String {
    return sys.deviceId
}
val get_odid = fun(): String {
    return sys.deviceId
}
val get_platform_name = fun(): String {
    var platformList: UTSJSONObject = object : UTSJSONObject(UTSSourceMapPosition("platformList", "uni_modules/uni-stat/utssdk/common/utils/pageInfo.uts", 51, 9)) {
        var app = "n"
        var `app-plus` = "n"
        var h5 = "h5"
        var web = "web"
        var `mp-weixin` = "wx"
        var `mp-baidu` = "bd"
        var `mp-toutiao` = "tt"
        var `mp-qq` = "qq"
        var `quickapp-native` = "qn"
        var `mp-kuaishou` = "ks"
        var `mp-lark` = "lark"
        var `quickapp-webview` = "qw"
    }
    val aliArr = utsArrayOf(
        "y",
        "a",
        "p",
        "mp-ali"
    )
    val aliKey = aliArr.reverse().join("")
    platformList[aliKey] = "ali"
    val platform = sys.uniPlatform
    return platformList[platform] as String
}
val get_pack_name = fun(): String {
    var packName = ""
    if (get_platform_name() === "n") {
        if (sys.osName == "android") {
            packName = sysAppBase.packageName ?: ""
        }
        if (sys.osName == "ios") {
            packName = sysAppBase.bundleId ?: ""
        }
    }
    return packName
}
val get_channel = fun(): String {
    val platformName = get_platform_name()
    var channel: String = ""
    if (platformName === "n") {
        channel = sysAppBase.channel ?: ""
    }
    return channel
}
val get_scene = fun(options: OnLaunchOptionsWithCst): Number {
    var scene: Number = 1001
    if (options.scene != null) {
        return options.scene ?: 1001
    }
    return scene
}
val is_page = fun(appInstance: ComponentPublicInstance): Boolean {
    return true
}
val get_page_name = fun(routepath: String): String {
    var page = get_page_vm()
    if (page == null) {
        return ""
    }
    var route: String
    route = page.route
    if (route != routepath) {
        val pages = getCurrentPages()
        var page_now = pages.find(fun(p): Boolean {
            return p.route == routepath
        }
        )
        if (page_now == null) {
            return ""
        }
        page = page_now.vm!!
    }
    var titleText: String
    val pageStyle = (page.`$page` as UniPage).getPageStyle()
    titleText = (pageStyle["navigationBarTitleText"] as String)
    return titleText as String
}
val get_page_vm = fun(): Page? {
    var pages = getCurrentPages()
    if (pages.length == 0) {
        return null
    }
    var page = pages[pages.length - 1]
    return page.vm
}
fun get_route(page: Page? = null): RouteParams {
    var _self = page ?: get_page_vm()
    if (_self == null) {
        val data = RouteParams(path = "", fullpath = "")
        return data
    }
    var url_params: String
    var options: Any
    options = _self.options
    val opts = UTSJSONObject(options, UTSSourceMapPosition("opts", "uni_modules/uni-stat/utssdk/common/utils/pageInfo.uts", 196, 11))
    url_params = Serialize(opts)
    var params = ""
    if (url_params != "?") {
        params = url_params
    }
    var route: String
    route = _self.route
    val data = RouteParams(path = route, fullpath = route + params)
    return data
}
val is_page_report = fun(): Boolean {
    val uniStatConfig = Config.getOptions()
    val collectItems = uniStatConfig.collectItems
    if (collectItems != null) {
        val statPageLog = collectItems.uniStatPageLog
        if (statPageLog == null) {
            return true
        }
        return if (UTSAndroid.`typeof`(statPageLog) == "boolean") {
            statPageLog
        } else {
            true
        }
    }
    return true
}
val IS_HANDLE_DEVECE_ID = "is_handle_device_id"
val is_handle_device = fun(): Boolean {
    var isHandleDevice = dbGet(IS_HANDLE_DEVECE_ID) ?: ""
    dbSet(IS_HANDLE_DEVECE_ID, "1")
    return isHandleDevice === "1"
}
val get_net = fun(): UTSPromise<String> {
    return UTSPromise(fun(resolve, reject){
        uni_getNetworkType(GetNetworkTypeOptions(success = fun(e: GetNetworkTypeSuccess) {
            val net = e.networkType
            resolve(net)
        }
        , fail = fun(_) {
            reject("")
        }
        ))
    }
    )
}
val get_default_data = fun(): StatDefault {
    var isRoot: Number
    if (device.isRoot == true) {
        isRoot = 1
    } else {
        isRoot = 0
    }
    var statData = StatDefault(uuid = get_uuid(), ak = APPID1, p = if (sys.osName == "android") {
        "a"
    } else {
        "i"
    }
    , ut = get_platform_name(), mpn = get_pack_name(), usv = STAT_VERSION, v = RUNTIME_VERSION, ch = get_channel(), cn = "", pn = get_pack_name(), ct = "", t = get_time(), tt = "", brand = sys.deviceBrand, md = sys.deviceModel, sv = sys.osVersion.replace(UTSRegExp("(Android|iOS)\\s", ""), ""), mpsdk = sys.SDKVersion, mpv = sys.uniCompilerVersionCode.toString(10), lang = sys.osLanguage, pr = sys.devicePixelRatio, ww = sys.windowWidth, wh = sys.windowHeight, sw = sys.screenWidth, sh = sys.screenHeight, lat = "", lng = "", net = "", odid = "", pv = sysAppBase.appVersion, root = isRoot)
    return statData
}
val get_report_Interval = fun(defaultTime: Number): Number {
    val uniStatConfig = Config.getOptions()
    var time = uniStatConfig.reportInterval
    if (!IsNumber(time)) {
        return defaultTime
    }
    if (time == 0) {
        return 0
    }
    return time as Number
}
val uni_cloud_config = fun(): UniCloudInitOptions? {
    return null
}
val get_space = fun(config: UniCloudInitOptions): UniCloudInitOptions? {
    val uniCloudConfig = uni_cloud_config()
    if (uniCloudConfig == null) {
        if (config.spaceId != "") {
            return config
        }
        return null
    }
    var spaceId = uniCloudConfig.spaceId
    var provider = uniCloudConfig.provider
    var clientSecret = uniCloudConfig.clientSecret
    var secretKey = uniCloudConfig.secretKey
    var accessKey = uniCloudConfig.accessKey
    val space_type = utsArrayOf(
        "tcb",
        "tencent",
        "aliyun",
        "alipay"
    )
    val is_space_id = spaceId != ""
    val is_provider = space_type.indexOf(provider) != -1
    val is_aliyun = provider == "aliyun" && is_space_id && clientSecret != null
    val is_tcb = (provider == "tcb" || provider === "tencent") && is_space_id
    val is_alipay = provider == "alipay" && is_space_id && secretKey != null && accessKey != null
    if (is_provider && (is_aliyun || is_tcb || is_alipay)) {
        return uniCloudConfig
    }
    return null
}
val get_crash_logs = fun(fn: (value: UTSArray<String>) -> Unit){
    val crash_data = uni___getAppCrashInfo()
    if (crash_data != null && crash_data.length > 0) {
        val sysManager = uni_getFileSystemManager()
        val reportLogs: UTSArray<UTSPromise<String>> = utsArrayOf()
        run {
            var i: Number = 0
            while(i < crash_data.length){
                val item = crash_data[i]
                var file_text: String
                file_text = item.get("file") as String
                val logPromise = UTSPromise<String>(fun(resolve, reject){
                    sysManager.readFile(ReadFileOptions(filePath = file_text, encoding = "utf-8", success = fun(res){
                        val crash_text = res.data as String
                        resolve(crash_text)
                    }
                    , fail = fun(err: Any){
                        reject(err)
                    }
                    ))
                }
                )
                reportLogs.push(logPromise)
                i++
            }
        }
        UTSPromise.all(reportLogs).then(fun(res){
            fn(res)
        }
        ).`catch`(fun(err){
            console.log("Errorlogs:", err, " at uni_modules/uni-stat/utssdk/common/utils/pageInfo.uts:504")
        }
        )
    }
}
val calibration = fun(eventName: String, options: Any?): ReportErrorCode? {
    if (eventName == "") {
        return 61003
    }
    if (UTSAndroid.`typeof`(eventName) != "string") {
        return 61004
    }
    if (eventName.length > 255) {
        return 61005
    }
    if (UTSAndroid.`typeof`(options) != "string" && UTSAndroid.`typeof`(options) != "object") {
        return 61006
    }
    if (UTSAndroid.`typeof`(options) == "string" && (options as String).length > 255) {
        return 61007
    }
    if (eventName == "title" && UTSAndroid.`typeof`(options) != "string") {
        return 61008
    }
    return null
}
open class CloudObjectToDo : InternalUniCloudCloudObjectCaller, IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("CloudObjectToDo", "uni_modules/uni-stat/utssdk/common/core/report.uts", 10, 7)
    }
    constructor(obj: InternalUniCloudCloudObject) : super(obj) {}
    open fun report(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return this._obj.callMethod("report", this._getArgs(*do_not_transform_spread))
    }
}
open class Report1 : IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("Report", "uni_modules/uni-stat/utssdk/common/core/report.uts", 20, 14)
    }
    open var licationShow: Boolean = false
    open var licationHide: Boolean = false
    open var statData: StatDefault
    open var eportInterval = get_report_Interval(OPERATING_TIME)
    open var _navigationBarTitle: TitleConfigParams = TitleConfigParams(config = "", page = "", report = "", lt = "")
    constructor(){
        this.statData = get_default_data()
        var registerInterceptor = UTSAndroid.`typeof`(uni_addInterceptor) === "function"
        if (registerInterceptor) {
            this.interceptSetNavBar()
            this.interceptLogin()
            this.interceptShare(true)
            this.interceptRequestPayment()
        }
    }
    open fun interceptSetNavBar() {
        var self = this
        uni_addInterceptor("setNavigationBarTitle", Interceptor(invoke = fun(args: SetNavigationBarTitleOptions) {
            self._navigationBarTitle.page = args.title
        }
        ))
    }
    open fun interceptLogin() {
        var self = this
        uni_addInterceptor("login", Interceptor(complete = fun() {
            self._login()
        }
        ))
    }
    open fun interceptShare(type: Boolean = false) {
        var self = this
        if (!type) {
            self._share()
            return
        }
        uni_addInterceptor("share", Interceptor(complete = fun() {
            self._share()
        }
        ))
    }
    open fun interceptRequestPayment() {
        var self = this
        uni_addInterceptor("requestPayment", Interceptor(success = fun() {
            self._payment("pay_success")
        }
        , fail = fun() {
            self._payment("pay_fail")
        }
        ))
    }
    private fun _login() {
        this.sendEventRequest(EventParams(key = "login"))
    }
    private fun _share() {
        this.sendEventRequest(EventParams(key = "share"))
    }
    private fun _payment(key: String) {
        this.sendEventRequest(EventParams(key = key))
    }
    open fun launch(options: Any) {
        set_page_residence_time()
        this.licationShow = true
        dbSet("__launch_options", options)
        var request_option = OnLaunchOptionsWithCst(path = (options as OnLaunchOptions).path, cst = ReprotCstType.AppNormal)
        this.sendAppRequest(request_option, true)
    }
    open fun load() {}
    open fun appShow() {
        if (!this.licationHide) {
            return
        }
        val time = get_residence_time("app")
        if (time.overtime) {
            var lastPageRoute = uni_getStorageSync("UNI_STAT_LAST_PAGE_ROUTE") as String
            var options = OnLaunchOptionsWithCst(path = lastPageRoute, scene = this.statData.sc, cst = ReprotCstType.AppAwakenTimeout)
            this.sendAppRequest(options)
        }
        this.licationHide = false
    }
    open fun appHide(type: Boolean = false) {
        this.licationHide = true
        val time = get_residence_time("outher")
        val route = get_route().fullpath
        uni_setStorageSync("UNI_STAT_LAST_PAGE_ROUTE", route)
        val formdata = PageParams(url = route, urlref = route, urlref_ts = time.residenceTime)
        this.sendHideRequest(formdata, type)
        set_first_time()
    }
    open fun pageShow(self: Page) {
        this._navigationBarTitle = TitleConfigParams(config = "", page = "", report = "", lt = "")
        val _get_route = get_route(self)
        val path = _get_route.path
        val fullpath = _get_route.fullpath
        val pageTitle = get_page_name(path)
        this._navigationBarTitle.config = pageTitle
        if (this.licationShow) {
            set_first_time()
            uni_setStorageSync("UNI_STAT_LAST_PAGE_ROUTE", fullpath)
            this.licationShow = false
            return
        }
        val time = get_residence_time("page")
        if (time.overtime) {
            var options = OnLaunchOptionsWithCst(path = fullpath, scene = this.statData.sc, cst = ReprotCstType.PageStayTimeout)
            this.sendAppRequest(options)
        }
        set_first_time()
    }
    open fun pageHide(self: Page) {
        if (this.licationHide) {
            return
        }
        val time = get_residence_time("page")
        var fullpath = get_route(self).fullpath
        var lastPageRoute = uni_getStorageSync("UNI_STAT_LAST_PAGE_ROUTE")
        if (lastPageRoute == null) {
            lastPageRoute = fullpath
        }
        uni_setStorageSync("UNI_STAT_LAST_PAGE_ROUTE", fullpath)
        val options = PageParams(url = fullpath, urlref = lastPageRoute as String, urlref_ts = time.residenceTime, urlref_tt = "")
        this.sendPageRequest(options)
    }
    open fun appError(em: Any) {
        var errmsg: String
        if (em is Exception) {
            errmsg = (em as Exception).stackTraceToString()
        } else if (em is UTSError) {
            errmsg = (em as UTSError).message
        } else {
            errmsg = JSON.stringify(em)
        }
        var route = get_route().path
        var options = StatDefault(ak = this.statData.ak, uuid = this.statData.uuid, p = this.statData.p, lt = "31", url = route, ut = this.statData.ut, ch = this.statData.ch, mpsdk = this.statData.mpsdk, mpv = this.statData.mpv, v = this.statData.v, em = errmsg, usv = this.statData.usv, t = get_time())
        this.request(options, false)
    }
    open fun appCrash(crash_data: UTSArray<String>) {
        val statdata = this.statData
        var options = StatDefault(lt = "41", uuid = statdata.uuid, ak = statdata.ak, v = statdata.v, ut = statdata.ut, p = statdata.p, ch = statdata.ch, mpsdk = statdata.mpsdk, did = statdata.uuid, net = statdata.net, os = statdata.mpn, sv = statdata.sv, brand = statdata.brand, md = statdata.md, root = statdata.root, pn = statdata.pn, pv = statdata.pv, log = "")
        get_net().then(fun(net: String){
            options.net = net
            this.crashRequest(options, crash_data)
        }
        ).`catch`(fun(){
            this.crashRequest(options, crash_data)
        }
        )
    }
    open fun sendAppRequest(options: OnLaunchOptionsWithCst, type: Boolean = false) {
        var is_opt = options.query != null && JSON.stringify(options.query) != "{}"
        var query = if (is_opt) {
            "?" + JSON.stringify(options.query)
        } else {
            ""
        }
        val first_time = get_first_visit_time()
        val last_time = get_last_visit_time()
        if (last_time != 0) {
            val odid = get_odid()
            val have_device = is_handle_device()
            if (!have_device) {
                this.statData.odid = odid
            }
        }
        this.statData.lt = ReportType.AppStart
        this.statData.url = options.path + query
        this.statData.t = get_time()
        this.statData.sc = get_scene(options)
        this.statData.fvts = first_time
        this.statData.lvts = last_time
        this.statData.tvc = get_total_visit_count()
        this.statData.cst = options.cst ?: ReprotCstType.AppNormal
        this.request(this.statData, type)
    }
    open fun sendPageRequest(opt: PageParams) {
        var url = opt.url
        var urlref = opt.urlref
        var urlref_ts = opt.urlref_ts
        this._navigationBarTitle.lt = ReportType.PageShow
        var options = StatDefault(ak = this.statData.ak, uuid = this.statData.uuid, p = this.statData.p, lt = ReportType.PageShow, ut = this.statData.ut, url = url, tt = this.statData.tt, urlref = urlref, urlref_ts = urlref_ts, ch = this.statData.ch, usv = this.statData.usv, t = get_time())
        this.request(options)
    }
    open fun sendHideRequest(opt: PageParams, type: Boolean) {
        var urlref = opt.urlref
        var urlref_ts = opt.urlref_ts
        var options = StatDefault(ak = this.statData.ak, uuid = this.statData.uuid, p = this.statData.p, lt = ReportType.AppHide, ut = this.statData.ut, urlref = urlref, urlref_ts = urlref_ts, ch = this.statData.ch, usv = this.statData.usv, t = get_time())
        this.request(options, type)
    }
    open fun sendPushRequest(options: Any, cid: String) {
        var time = get_time()
        val statData = StatDefault(uuid = this.statData.uuid, p = this.statData.p, ak = this.statData.ak, lt = ReportType.Push, cid = cid, t = time, ut = this.statData.ut)
        val dataStr = JSON.stringify(statData)
        var uniStatData = UTSAndroid.consoleDebugError(JSON.parse<Map<String, UTSArray<StatDefault>>>(dataStr), " at uni_modules/uni-stat/utssdk/common/core/report.uts:436")
        if (uniStatData == null) {
            uniStatData = Map(utsArrayOf())
        }
        val hasKey = uniStatData.has(ReportType.Push)
        if (!hasKey) {
            uniStatData.set(ReportType.Push, utsArrayOf())
        }
        uniStatData.get(ReportType.Push)!!.push(statData)
        val stat_data = handle_data(uniStatData)
        var optionsData = RequestData(usv = STAT_VERSION, t = time, requests = stat_data)
        this.sendRequest(optionsData)
    }
    open fun sendEventRequest(data: EventParams) {
        val key = data.key
        val value = data.value ?: ""
        var routepath = get_route().fullpath
        val lt = ReportType.Event
        this._navigationBarTitle.config = get_page_name(routepath)
        this._navigationBarTitle.lt = lt
        var options = StatDefault(ak = this.statData.ak, uuid = this.statData.uuid, p = this.statData.p, lt = lt, ut = this.statData.ut, url = routepath, ch = this.statData.ch, e_n = key, e_v = if (UTSAndroid.`typeof`(value) === "object") {
            JSON.stringify(value)
        } else {
            value.toString()
        }
        , usv = this.statData.usv, t = get_time())
        this.request(options)
    }
    open fun crashRequest(data: StatDefault, crash_data: UTSArray<String>) {
        val statConfig = Config.getOptions()
        var time = get_time()
        val title = this._navigationBarTitle
        data.ttn = title.page
        data.ttpj = title.config
        data.ttc = title.report
        var statData = dbGet<String>("__UNI__STAT__DATA")
        if (statData == null) {
            statData = "{}"
        }
        var uniStatData = UTSAndroid.consoleDebugError(JSON.parse<Map<String, UTSArray<StatDefault>>>(statData), " at uni_modules/uni-stat/utssdk/common/core/report.uts:499")
        if (uniStatData == null) {
            uniStatData = Map(utsArrayOf())
        }
        run {
            var i: Number = 0
            while(i < crash_data.length){
                val log = crash_data[i]
                data.log = log
                if (statConfig.debug!!) {
                    Log(data)
                }
                val lt = data.lt!!
                val hasKey = uniStatData.has(lt)
                if (!hasKey) {
                    uniStatData.set(lt, utsArrayOf())
                }
                uniStatData.get(lt)!!.push(data)
                i++
            }
        }
        val statDataStr = Map2String(uniStatData)
        dbSet("__UNI__STAT__DATA", statDataStr)
        if (statConfig.debug!!) {
            val statJSON = Map2Json(uniStatData)
            console.log("=== 统计待上传队列数据 :", statJSON, " at uni_modules/uni-stat/utssdk/common/core/report.uts:622")
        }
        val stat_data = handle_data(uniStatData)
        var optionsData = RequestData(usv = STAT_VERSION, t = time, requests = stat_data)
        dbRemove("__UNI__STAT__DATA")
        this.sendRequest(optionsData, true)
    }
    open fun request(data: StatDefault, type: Boolean = false) {
        val statConfig = Config.getOptions()
        var time = get_time()
        val title = this._navigationBarTitle
        data.ttn = title.page
        data.ttpj = title.config
        data.ttc = title.report
        if (statConfig.debug!!) {
            Log(data)
        }
        val lt = data.lt!!
        var statData = dbGet<String>("__UNI__STAT__DATA")
        if (statData == null) {
            statData = "{}"
        }
        var uniStatData = UTSAndroid.consoleDebugError(JSON.parse<Map<String, UTSArray<StatDefault>>>(statData), " at uni_modules/uni-stat/utssdk/common/core/report.uts:563")
        if (uniStatData == null) {
            uniStatData = Map(utsArrayOf())
        }
        val hasKey = uniStatData.has(lt)
        if (!hasKey) {
            uniStatData.set(lt, utsArrayOf())
        }
        uniStatData.get(lt)!!.push(data)
        val statDataStr = Map2String(uniStatData)
        dbSet("__UNI__STAT__DATA", statDataStr)
        var page_residence_time = get_page_residence_time()
        val is_timeout = page_residence_time < this.eportInterval && !type
        if (is_timeout) {
            return
        }
        set_page_residence_time()
        if (statConfig.debug!!) {
            val statJSON = Map2Json(uniStatData)
            console.log("=== 统计待上传队列数据 :", statJSON, " at uni_modules/uni-stat/utssdk/common/core/report.uts:701")
        }
        val stat_data = handle_data(uniStatData)
        var optionsData = RequestData(usv = STAT_VERSION, t = time, requests = stat_data)
        dbRemove("__UNI__STAT__DATA")
        this.sendRequest(optionsData)
    }
    open fun sendRequest(options: RequestData, delCrash: Boolean = false) {
        val statConfig = Config.getOptions()
        if (Report1.uniCloudInstance != null) {
            val app = Report1.uniCloudInstance!!
            val uniCloudObj = app.importObject("uni-stat-receiver", UniCloudImportObjectOptions(customUI = true), CloudObjectToDo::class.java)
            uniCloudObj.report(options).then(fun(){
                if (statConfig.debug!!) {
                    console.log("=== \u7EDF\u8BA1\u961F\u5217\u6570\u636E\u4E0A\u62A5 :", options, " at uni_modules/uni-stat/utssdk/common/core/report.uts:751")
                }
                if (delCrash) {
                    try {
                        uni___deleteAppCrashInfo(null)
                    }
                     catch (err: Throwable) {
                        console.log("=== 应用崩溃日志上传失败 :", JSON.stringify(err), " at uni_modules/uni-stat/utssdk/common/core/report.uts:758")
                    }
                }
            }
            ).`catch`(fun(err: Any?){
                console.log("=== 统计上报错误 :", JSON.stringify(err), " at uni_modules/uni-stat/utssdk/common/core/report.uts:765")
            }
            )
        }
    }
    companion object {
        var uniCloudInstance: UniCloud? = null
    }
}
val reportInstance = Report1()
open class Stat : IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("Stat", "uni_modules/uni-stat/utssdk/common/core/stat.uts", 7, 14)
    }
    open var appInstance: Page? = null
    private var isHide: Boolean = false
    constructor(){}
    open fun init(options: UTSJSONObject) {
        Stat.is_register = true
        Config.setOptions(UTSJSONObject.assign<UniStatOptions>(UTSJSONObject(), options) as UniStatOptions)
        val uniStatConfig = Config.getOptions()
        reportInstance.eportInterval = uniStatConfig.reportInterval ?: 10
        get_crash_logs(fun(logs){
            this.registerEvent(StatType.Crash, null, logs)
        }
        )
    }
    open fun onLaunch(options: OnLaunchOptions, appInstance: ComponentPublicInstance) {}
    open fun onLoad(appInstance: Page) {
        this.registerEvent(StatType.LifeCycleLoad, appInstance)
    }
    open fun onShow(appInstance: Page) {
        this.isHide = false
        val mptype = is_page(appInstance)
        if (mptype) {
            this.registerEvent(StatType.LifeCyclePageShow, appInstance, null)
        }
    }
    open fun onHide(appInstance: Page) {
        this.isHide = true
        val mptype = is_page(appInstance)
        if (mptype) {
            this.registerEvent(StatType.LifeCyclePageHide, appInstance, null)
        }
    }
    open fun onUnload(appInstance: Page) {
        if (this.isHide) {
            this.isHide = false
            return
        }
        this.registerEvent(StatType.LifeCyclePageUnLoad, appInstance, null)
    }
    open fun onError(error: String) {
        this.error(error)
    }
    open fun registerEvent(EventType: Number, appInstance: Page?, options: Any? = null, error: Any? = "") {
        val statConfig = Config.getOptions()
        if (statConfig.enable == false) {
            return
        }
        val isPageReport = is_page_report()
        when (EventType) {
            StatType.LifeCycleLaunch -> 
                reportInstance.launch(options!!)
            StatType.LifeCycleAppShow -> 
                reportInstance.appShow()
            StatType.LifeCycleAppHide -> 
                reportInstance.appHide(true)
            StatType.LifeCycleLoad -> 
                {}
            StatType.LifeCyclePageShow -> 
                if (isPageReport) {
                    reportInstance.pageShow(appInstance!!)
                }
            StatType.LifeCyclePageHide -> 
                if (isPageReport) {
                    reportInstance.pageHide(appInstance!!)
                }
            StatType.LifeCyclePageUnLoad -> 
                if (isPageReport) {
                    reportInstance.pageHide(appInstance!!)
                }
            StatType.LifeCycleError -> 
                if (error != null) {
                    reportInstance.appError(error)
                }
            StatType.Crash -> 
                reportInstance.appCrash(options as UTSArray<String>)
        }
    }
    open fun error(em: String) {}
    open fun appEvent(name: String, options: Any? = null, fn: ErrorCallback) {
        val statConfig = Config.getOptions()
        if (statConfig.enable == false) {
            return
        }
        if (Stat.no_space) {
            fn(false, 61000 as ReportErrorCode)
            return
        }
        if (!Stat.is_register) {
            fn(false, 61001 as ReportErrorCode)
            return
        }
        if (name == "uni-app-launch" && options == null) {
            fn(false, 61002 as ReportErrorCode)
            return
        }
        if (name == "uni-app-launch") {
            this.registerEvent(StatType.LifeCycleLaunch, null, options)
            fn(true, 61001 as ReportErrorCode)
            return
        }
        if (name == "uni-app-show") {
            this.registerEvent(StatType.LifeCycleAppShow, null, null)
            fn(true, 61001 as ReportErrorCode)
            return
        }
        if (name == "uni-app-hide") {
            this.registerEvent(StatType.LifeCycleAppHide, null, null)
            fn(true, 61001 as ReportErrorCode)
            return
        }
        if (name == "uni-page-show") {
            reportInstance.pageShow(options as Page)
            fn(true, 61001 as ReportErrorCode)
            return
        }
        if (name == "uni-page-hide") {
            reportInstance.pageHide(options as Page)
            fn(true, 61001 as ReportErrorCode)
            return
        }
        if (name == "uni-app-error") {
            this.registerEvent(StatType.LifeCycleError, null, null, options)
            fn(true, 61001 as ReportErrorCode)
            return
        }
        val is_calibration = calibration(name, options)
        if (is_calibration != null) {
            fn(false, is_calibration)
            return
        }
        if (name === "title") {
            reportInstance._navigationBarTitle.report = (options as String)
        }
        val value = if (UTSAndroid.`typeof`(options) === "object") {
            JSON.stringify(options)
        } else {
            options
        }
         as String
        val data = EventParams(key = name, value = value as String)
        reportInstance.sendEventRequest(data)
        fn(true, 61001 as ReportErrorCode)
    }
    companion object {
        var __stat_instance: Stat? = null
        var is_register: Boolean = false
        var no_space: Boolean = false
        fun getInstance(): Stat {
            var space = get_space(uniCloud.config)
            if (Report1.uniCloudInstance == null) {
                if (space != null) {
                    var spaceData = UniCloudInitOptions(provider = space.provider, spaceId = space.spaceId, clientSecret = space.clientSecret)
                    val endpoint = space.endpoint
                    if (endpoint != null) {
                        spaceData.endpoint = space.endpoint
                    }
                    if (space.provider == "alipay") {
                        spaceData.secretKey = space.secretKey
                        spaceData.accessKey = space.accessKey
                        spaceData.spaceAppId = space.spaceAppId
                    }
                    Report1.uniCloudInstance = uniCloud.init(spaceData)
                } else {
                    if (!Stat.no_space) {
                        console.log("\u001b[31m应用已集成uni统计，但未关联服务空间，请在uniCloud目录右键关联服务空间\u001b[0m", " at uni_modules/uni-stat/utssdk/common/core/stat.uts:50")
                        Stat.no_space = true
                    }
                }
            }
            if (this.__stat_instance == null) {
                this.__stat_instance = Stat()
            }
            return this.__stat_instance as Stat
        }
    }
}
val stat = Stat.getInstance()
val report: Report = fun(options: ReportOptions) {
    val name = options.name
    val option = options.options
    stat.appEvent(name, option, fun(type: Boolean, code: ReportErrorCode){
        if (type) {
            val res = ReportSuccess(errMsg = "report:ok")
            options.success?.invoke(res)
            options.complete?.invoke(res)
        } else {
            var err = ReportFailImpl(code)
            options.fail?.invoke(err)
            options.complete?.invoke(err)
        }
    }
    )
}
