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
import io.dcloud.uniapp.extapi.createPushMessage as uni_createPushMessage
import io.dcloud.uniapp.extapi.getAppAuthorizeSetting as uni_getAppAuthorizeSetting
import io.dcloud.uniapp.extapi.getDeviceInfo as uni_getDeviceInfo
import io.dcloud.uniapp.extapi.getPushChannelManager as uni_getPushChannelManager
import io.dcloud.uniapp.extapi.getPushClientId as uni_getPushClientId
import io.dcloud.uniapp.extapi.hideLoading as uni_hideLoading
import io.dcloud.uniapp.extapi.hideToast as uni_hideToast
import io.dcloud.uniapp.extapi.offPushMessage as uni_offPushMessage
import io.dcloud.uniapp.extapi.onPushMessage as uni_onPushMessage
import io.dcloud.uniapp.extapi.setAppBadgeNumber as uni_setAppBadgeNumber
import io.dcloud.uniapp.extapi.showLoading as uni_showLoading
import io.dcloud.uniapp.extapi.showModal as uni_showModal
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesAPIUniPushUniPush : BasePage {
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
    open var jestResult: TypeJestResult1
        get() {
            return unref(this.`$exposed`["jestResult"]) as TypeJestResult1
        }
        set(value) {
            setRefValue(this.`$exposed`, "jestResult", value)
        }
    open var autoTest: Boolean
        get() {
            return unref(this.`$exposed`["autoTest"]) as Boolean
        }
        set(value) {
            setRefValue(this.`$exposed`, "autoTest", value)
        }
    open var updateAutoTest: (value: Boolean) -> Unit
        get() {
            return unref(this.`$exposed`["updateAutoTest"]) as (value: Boolean) -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "updateAutoTest", value)
        }
    open var isRegister: TypeIsRegister
        get() {
            return unref(this.`$exposed`["isRegister"]) as TypeIsRegister
        }
        set(value) {
            setRefValue(this.`$exposed`, "isRegister", value)
        }
    open var handleSendPushMessage: () -> UTSPromise<Unit>
        get() {
            return unref(this.`$exposed`["handleSendPushMessage"]) as () -> UTSPromise<Unit>
        }
        set(value) {
            setRefValue(this.`$exposed`, "handleSendPushMessage", value)
        }
    open var handleGetClientId: () -> UTSPromise<Unit>
        get() {
            return unref(this.`$exposed`["handleGetClientId"]) as () -> UTSPromise<Unit>
        }
        set(value) {
            setRefValue(this.`$exposed`, "handleGetClientId", value)
        }
    open var handleOnPushMessage: () -> Unit
        get() {
            return unref(this.`$exposed`["handleOnPushMessage"]) as () -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "handleOnPushMessage", value)
        }
    open var handleOffPushMessage: () -> Unit
        get() {
            return unref(this.`$exposed`["handleOffPushMessage"]) as () -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "handleOffPushMessage", value)
        }
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesAPIUniPushUniPush, _arg1: SetupContext) -> Any? = fun(__props, ref1): Any? {
            var __expose = ref1.expose
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesAPIUniPushUniPush
            val _cache = __ins.renderCache
            val jestResult = reactive(TypeJestResult1(clientId = "", sendPushMessageRes = -1, onPushMessageType = "", onPushMessageCallbackInfo = ""))
            val autoTest = ref(false)
            val updateAutoTest = fun(value: Boolean){
                autoTest.value = value
            }
            val channelInfo = ref("")
            val onPushMessageCallback = fun(res: OnPushMessageCallbackResult){
                jestResult.onPushMessageType = res.type
                jestResult.onPushMessageCallbackInfo = JSON.stringify(res.data)
                if (!autoTest.value) {
                    uni_showModal(ShowModalOptions(title = "onPushMessage回调信息", content = "type\uFF1A" + res.type + " \n data\uFF1A" + JSON.stringify(res.data)))
                }
            }
            val isRegister = reactive(TypeIsRegister(state = false))
            val handleOnPushMessage = fun(){
                if (isRegister.state) {
                    uni_showToast(ShowToastOptions(icon = "error", title = "无需重复注册"))
                    return
                }
                uni_onPushMessage(onPushMessageCallback)
                isRegister.state = true
                uni_showToast(ShowToastOptions(title = "成功注册"))
            }
            val handleOffPushMessage = fun(){
                if (!isRegister.state) {
                    uni_showToast(ShowToastOptions(icon = "error", title = "未注册, 无需注销"))
                    return
                }
                uni_offPushMessage(onPushMessageCallback)
                isRegister.state = false
                uni_showToast(ShowToastOptions(title = "成功注销"))
            }
            val handleCreateChannel = fun(showToast: Boolean){
                val manager = uni_getPushChannelManager()
                manager.setPushChannel(SetPushChannelOptions(channelId = "msg-pass", channelDesc = "留言审核通过", soundName = "#填写配置的声音文件名#", enableLights = true, enableVibration = true, importance = 4, lockscreenVisibility = 1))
                if (showToast) {
                    uni_showToast(ShowToastOptions(title = "设置渠道成功"))
                }
            }
            val handleGetAllChannels = fun(){
                val manager = uni_getPushChannelManager()
                console.log("channels : " + manager.getAllChannels(), " at pages/API/uni-push/uni-push.uvue:142")
                channelInfo.value = "\u6E20\u9053\u4FE1\u606F\u4E3A: \n " + manager.getAllChannels()
            }
            val handleCreateLocalNotification = fun(){
                if (uni_getAppAuthorizeSetting().notificationAuthorized == "authorized") {
                    handleCreateChannel(false)
                    val date = Date()
                    val hour = date.getHours()
                    val minute = date.getMinutes()
                    val second = date.getSeconds()
                    val formateTime = fun(target: Number): String {
                        return if (target < 10) {
                            "0" + target
                        } else {
                            "" + target
                        }
                    }
                    uni_createPushMessage(CreatePushMessageOptions(title = "主标题(title)", content = "\u5185\u5BB9(content)\uFF0C\u521B\u5EFA\u65F6\u95F4: " + formateTime(hour) + ":" + formateTime(minute) + ":" + formateTime(second), cover = false, channelId = "msg-pass", `when` = Date.now() + 10000, icon = "/static/uni.png", sound = "system", delay = 1, payload = object : UTSJSONObject() {
                        var pkey = "pvalue1"
                    }, category = "IM", success = fun(res) {
                        console.log("res: " + res, " at pages/API/uni-push/uni-push.uvue:175")
                        uni_hideToast()
                        uni_showToast(ShowToastOptions(title = "创建本地通知消息成功"))
                    }, fail = fun(e) {
                        console.log("fail :" + e, " at pages/API/uni-push/uni-push.uvue:182")
                        uni_hideToast()
                        uni_showToast(ShowToastOptions(title = "创建本地通知消息失败", icon = "error"))
                    }))
                } else {
                    uni_showToast(ShowToastOptions(title = "请在设置中开启通知权限", icon = "error"))
                }
            }
            fun gen_getPushClientId_fn(): UTSPromise<String> {
                return wrapUTSPromise(suspend w@{
                        var pushClientId = ""
                        var res: Unit = await(UTSPromise<Unit>(fun(resolve, _reject){
                            uni_getPushClientId(GetPushClientIdOptions(success = fun(res: GetPushClientIdSuccess){
                                console.log(res.cid, " at pages/API/uni-push/uni-push.uvue:203")
                                pushClientId = res.cid
                                resolve(Unit)
                            }
                            , fail = fun(err: GetPushClientIdFail){
                                resolve(Unit)
                                console.error(err, " at pages/API/uni-push/uni-push.uvue:209")
                                if (err.message.includes("uniPush is not enabled")) {
                                    uni_showModal(ShowModalOptions(title = "获取cid失败", content = "当前项目未启用uni-push，检查manifest.json中的uni-push配置", showCancel = false))
                                } else if (err.message.includes("getPushClientId:fail register fail: {\"errorCode\":1,\"errorMsg\":\"\"}")) {
                                    uni_showModal(ShowModalOptions(title = "获取cid失败", content = "当前项目未开通uni-push，开通文档：https://uniapp.dcloud.net.cn/unipush-v2.html#%E7%AC%AC%E4%B8%80%E6%AD%A5-%E5%BC%80%E9%80%9A", showCancel = false))
                                } else {
                                    uni_showToast(ShowToastOptions(title = "\u83B7\u53D6cid\u5931\u8D25", icon = "error"))
                                }
                            }
                            ))
                        }
                        ))
                        return@w pushClientId
                })
            }
            val getPushClientId = ::gen_getPushClientId_fn
            val handleGetClientId = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        uni_showLoading(ShowLoadingOptions(title = "正在获取cid"))
                        val cid = await(getPushClientId())
                        if (cid != "") {
                            jestResult.clientId = cid
                            if (!autoTest.value) {
                                uni_showModal(ShowModalOptions(title = "获取cid", content = "获取cid成功" + cid, showCancel = false))
                            }
                        }
                        uni_hideLoading()
                })
            }
            val handleSendPushMessage = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w@{
                        val pushClientId = await(getPushClientId())
                        if (pushClientId == "") {
                            return@w
                        }
                        val uniPushCo = uniCloud.importObject("uni-push-co", GenCloudObjUniPushCo::class.java)
                        try {
                            val res = await(uniPushCo.sendPushMessage(pushClientId))
                            jestResult.sendPushMessageRes = res["errCode"] as Number
                            if (!autoTest.value) {
                                uni_showToast(ShowToastOptions(title = "发送通知消息成功"))
                            }
                        }
                         catch (err: Throwable) {
                            val error = err as UniCloudError
                            console.error(error, " at pages/API/uni-push/uni-push.uvue:269")
                            if (!autoTest.value) {
                                uni_showToast(ShowToastOptions(title = "发送通知消息失败", icon = "error"))
                            }
                        }
                })
            }
            val handleSetBadge = fun(){
                if (uni_getDeviceInfo(null).deviceBrand?.toLowerCase() == "xiaomi") {
                    if (uni_getAppAuthorizeSetting().notificationAuthorized == "authorized") {
                        uni_setAppBadgeNumber(5, BadgeOptions(title = "AppName", content = "您有5条未读消息"))
                        uni_showToast(ShowToastOptions(title = "设置应用角标数为5"))
                    } else {
                        uni_showToast(ShowToastOptions(title = "请在设置中开启通知权限", icon = "error"))
                    }
                } else {
                    uni_setAppBadgeNumber(5, null)
                    uni_showToast(ShowToastOptions(title = "设置应用角标数为5"))
                }
            }
            val handleCleanBadge = fun(){
                if (uni_getDeviceInfo(null).deviceBrand?.toLowerCase() == "xiaomi") {
                    if (uni_getAppAuthorizeSetting().notificationAuthorized == "authorized") {
                        uni_setAppBadgeNumber(0, BadgeOptions())
                        uni_showToast(ShowToastOptions(title = "清空应用角标数"))
                    } else {
                        uni_showToast(ShowToastOptions(title = "请在设置中开启通知权限", icon = "error"))
                    }
                } else {
                    uni_setAppBadgeNumber(0, null)
                    uni_showToast(ShowToastOptions(title = "清空应用角标数"))
                }
            }
            __expose(utsMapOf("jestResult" to jestResult, "autoTest" to autoTest, "updateAutoTest" to updateAutoTest, "isRegister" to isRegister, "handleSendPushMessage" to handleSendPushMessage, "handleGetClientId" to handleGetClientId, "handleOnPushMessage" to handleOnPushMessage, "handleOffPushMessage" to handleOffPushMessage))
            return fun(): Any? {
                return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                    createElementVNode("view", null, utsArrayOf(
                        createElementVNode("button", utsMapOf("class" to "normal-button", "type" to "default", "onClick" to fun(){
                            handleCreateChannel(true)
                        }
                        ), " 创建通知渠道 | setPushChannel ", 8, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("button", utsMapOf("class" to "normal-button", "type" to "default", "onClick" to handleGetAllChannels), " 获取所有通知渠道信息 | getAllChannels "),
                        createElementVNode("textarea", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "100%")), "disabled" to true, "value" to unref(channelInfo)), null, 12, utsArrayOf(
                            "value"
                        )),
                        createElementVNode("button", utsMapOf("class" to "normal-button", "type" to "default", "onClick" to handleCreateLocalNotification), " 创建本地通知消息 | createPushMessage "),
                        createElementVNode("text", utsMapOf("class" to "instructions"), " 不同手机厂商的角标显示规则不同，有部分设备的rom版本不支持显示角标，另有部分rom需要在应用的通知管理里开启`桌面角标`配置，才可以设置角标成功。\n 部分rom需要在设置中同时开启`通知开关`和`桌面角标`配置，才允许设置角标，例如鸿蒙4.2。 \n 另外针对高版本小米设备，会借助创建通知栏消息来设置角标数，所以设置时需要注意是否有权限创建通知栏消息。 "),
                        createElementVNode("button", utsMapOf("class" to "normal-button", "type" to "default", "onClick" to handleSetBadge), " 设置角标为5 | setAppBadgeNumber(5) "),
                        createElementVNode("button", utsMapOf("class" to "normal-button", "type" to "default", "onClick" to handleCleanBadge), " 清空角标 | setAppBadgeNumber(0) "),
                        createElementVNode("button", utsMapOf("class" to "normal-button", "type" to "default", "onClick" to handleSendPushMessage), " 发送通知消息 | sendPushMessage "),
                        createElementVNode("button", utsMapOf("class" to "normal-button uni-common-mb", "type" to "default", "onClick" to handleGetClientId), " 获取cid | getPushClientId "),
                        createElementVNode("button", utsMapOf("class" to "normal-button", "type" to "default", "onClick" to handleOnPushMessage), " 注册回调 | onPushMessage "),
                        createElementVNode("button", utsMapOf("class" to "normal-button", "type" to "default", "onClick" to handleOffPushMessage), " 注销回调 | offPushMessage ")
                    ))
                ), 4)
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ), utsArrayOf(
                GenApp.styles
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("normal-button" to padStyleMapOf(utsMapOf("width" to "100%")), "instructions" to padStyleMapOf(utsMapOf("marginTop" to 10, "marginLeft" to 10, "marginRight" to 10, "backgroundColor" to "#eeeeee")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
