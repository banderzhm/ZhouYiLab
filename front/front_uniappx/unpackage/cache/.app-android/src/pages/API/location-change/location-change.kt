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
import io.dcloud.uniapp.extapi.getProviderSync as uni_getProviderSync
import io.dcloud.uniapp.extapi.offLocationChange as uni_offLocationChange
import io.dcloud.uniapp.extapi.offLocationChangeError as uni_offLocationChangeError
import io.dcloud.uniapp.extapi.onLocationChange as uni_onLocationChange
import io.dcloud.uniapp.extapi.onLocationChangeError as uni_onLocationChangeError
import io.dcloud.uniapp.extapi.showToast as uni_showToast
import io.dcloud.uniapp.extapi.startLocationUpdate as uni_startLocationUpdate
import io.dcloud.uniapp.extapi.startLocationUpdateBackground as uni_startLocationUpdateBackground
import io.dcloud.uniapp.extapi.stopLocationUpdate as uni_stopLocationUpdate
open class GenPagesAPILocationChangeLocationChange : BasePage {
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
        onLoad(fun(_: OnLoadOptions) {
            this.getProvider()
        }
        , __ins)
        onUnload(fun() {
            uni_stopLocationUpdate(StopLocationUpdateOptions())
            uni_offLocationChange(null)
            uni_offLocationChangeError(null)
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_radio = resolveComponent("radio")
        val _component_radio_group = resolveComponent("radio-group")
        return createElementVNode(Fragment, null, utsArrayOf(
            createElementVNode("text", null, "显示简易操作日志(可滚动查看)"),
            createElementVNode("button", utsMapOf("size" to "mini", "onClick" to fun(){
                _ctx.log = ""
            }
            ), "清空日志", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("max-height" to "300px"))), utsArrayOf(
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "2px", "padding" to "2px", "border" to "1px solid #000000"))), toDisplayString(_ctx.log), 5)
            ), 4),
            createElementVNode("view", utsMapOf("class" to "uni-list"), utsArrayOf(
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-bottom" to "4px"))), " 请选择定位服务提供商：", 4),
                createVNode(_component_radio_group, utsMapOf("class" to "uni-flex uni-row", "onChange" to _ctx.providerChange, "style" to normalizeStyle(utsMapOf("flex-wrap" to "wrap"))), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.providerList, fun(item, index, __index, _cached): Any {
                            return createVNode(_component_radio, utsMapOf("class" to "uni-list-cell", "style" to normalizeStyle(utsMapOf("margin-right" to "15px")), "key" to item.id, "value" to item.id, "checked" to (index === _ctx.currentSelectedProvider)), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                return utsArrayOf(
                                    toDisplayString(item.name)
                                )
                            }
                            ), "_" to 2), 1032, utsArrayOf(
                                "style",
                                "value",
                                "checked"
                            ))
                        }
                        ), 128)
                    )
                }
                ), "_" to 1), 8, utsArrayOf(
                    "onChange",
                    "style"
                ))
            )),
            createElementVNode("view", utsMapOf("class" to "uni-list"), utsArrayOf(
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-bottom" to "4px"))), " 请选择坐标系：", 4),
                createVNode(_component_radio_group, utsMapOf("class" to "uni-flex uni-row", "onChange" to _ctx.typeChange, "style" to normalizeStyle(utsMapOf("flex-wrap" to "wrap"))), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.types, fun(item, index, __index, _cached): Any {
                            return createVNode(_component_radio, utsMapOf("class" to "uni-list-cell", "style" to normalizeStyle(utsMapOf("margin-right" to "15px")), "key" to item.value, "value" to item.value, "checked" to (index === _ctx.currentSelectedType)), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                return utsArrayOf(
                                    toDisplayString(item.name)
                                )
                            }
                            ), "_" to 2), 1032, utsArrayOf(
                                "style",
                                "value",
                                "checked"
                            ))
                        }
                        ), 128)
                    )
                }
                ), "_" to 1), 8, utsArrayOf(
                    "onChange",
                    "style"
                ))
            )),
            createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.startLocationUpdate, "id" to "startLocationUpdate"), "点击连续定位", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.startLocationUpdateBackground, "id" to "startLocationUpdateBackground"), "后台点击连续定位", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.stopLocationUpdate, "id" to "stopLocationUpdate"), "点击关闭定位", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.onLocationChange, "id" to "onLocationChange"), "onLocationChange", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.offLocationChange, "id" to "offLocationChange"), "offLocationChange", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.onLocationChangeError, "id" to "onLocationChangeError"), "onLocationChangeError", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.offLocationChangeError, "id" to "offLocationChangeError"), "offLocationChangeError", 8, utsArrayOf(
                    "onClick"
                ))
            ), 4)
        ), 64)
    }
    open var log: String by `$data`
    open var logAble: Boolean by `$data`
    open var providerList: UTSArray<LocationItem1> by `$data`
    open var types: UTSArray<ItemType7> by `$data`
    open var currentSelectedProvider: Number by `$data`
    open var currentSelectedType: Number by `$data`
    open var startSuccess: Boolean by `$data`
    open var stopSuccess: Boolean by `$data`
    open var errCode: Number by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("log" to "", "logAble" to true, "providerList" to utsArrayOf<LocationItem1>(), "types" to utsArrayOf<ItemType7>(ItemType7(value = "wgs84", name = "wgs84"), ItemType7(value = "gcj02", name = "gcj02")), "currentSelectedProvider" to 0, "currentSelectedType" to 0, "startSuccess" to false, "stopSuccess" to false, "errCode" to 0)
    }
    open var getProvider = ::gen_getProvider_fn
    open fun gen_getProvider_fn() {
        var provider = uni_getProviderSync(GetProviderSyncOptions(service = "location"))
        console.log(provider, " at pages/API/location-change/location-change.uvue:89")
        provider.providerObjects.forEach(fun(value: UniProvider){
            var currentProvider = value
            this.providerList.push(LocationItem1(name = currentProvider.description, id = currentProvider.id, provider = currentProvider))
        }
        )
        this.providerList.forEach(fun(value, index){
            if (value.id == "system") {
                this.currentSelectedProvider = index
            }
        }
        )
    }
    open var providerChange = ::gen_providerChange_fn
    open fun gen_providerChange_fn(e: UniRadioGroupChangeEvent) {
        run {
            var i: Number = 0
            while(i < this.providerList.length){
                if (this.providerList[i].id === e.detail.value) {
                    this.currentSelectedProvider = i
                    break
                }
                i++
            }
        }
        if (e.detail.value == "system") {
            this.currentSelectedType = 0
        } else if (e.detail.value == "tencent") {
            this.currentSelectedType = 1
        }
    }
    open var typeChange = ::gen_typeChange_fn
    open fun gen_typeChange_fn(e: UniRadioGroupChangeEvent) {
        run {
            var i: Number = 0
            while(i < this.types.length){
                if (this.types[i].value === e.detail.value) {
                    this.currentSelectedType = i
                    break
                }
                i++
            }
        }
    }
    open var offLocationChangeError = ::gen_offLocationChangeError_fn
    open fun gen_offLocationChangeError_fn() {
        if (this.logAble) {
            this.log += "\u5173\u95EDoffLocationChangeError\u76D1\u542C\n\n"
        }
        console.log("关闭onLocationChangeError监听", " at pages/API/location-change/location-change.uvue:132")
        uni_offLocationChangeError(null)
    }
    open var onLocationChangeError = ::gen_onLocationChangeError_fn
    open fun gen_onLocationChangeError_fn() {
        uni_offLocationChangeError(null)
        if (this.logAble) {
            this.log += "\u5F00\u542FonLocationChangeError\u76D1\u542C\n\n"
        }
        console.log("开启onLocationChangeError监听", " at pages/API/location-change/location-change.uvue:140")
        uni_onLocationChangeError(fun(e){
            console.log("onLocationChangeError回调: ", e, " at pages/API/location-change/location-change.uvue:142")
            if (this.logAble) {
                this.log += JSON.stringify(e) + "\n\n"
            }
        }
        )
    }
    open var onLocationChange = ::gen_onLocationChange_fn
    open fun gen_onLocationChange_fn() {
        uni_offLocationChange(null)
        console.log("开启onLocationChange监听", " at pages/API/location-change/location-change.uvue:150")
        if (this.logAble) {
            this.log += "\u5F00\u542FonLocationChange\u76D1\u542C\n\n"
        }
        uni_onLocationChange(fun(e){
            console.log("onLocationChange持续监听回调: ", e, " at pages/API/location-change/location-change.uvue:155")
            if (this.logAble) {
                this.log += "provider= " + this.providerList[this.currentSelectedProvider].id + "\n" + JSON.stringify(e) + "\n\n"
            }
        }
        )
    }
    open var offLocationChange = ::gen_offLocationChange_fn
    open fun gen_offLocationChange_fn() {
        if (this.logAble) {
            this.log += "\u5173\u95EDoffLocationChange\u76D1\u542C\n\n"
        }
        console.log("关闭offLocationChange监听", " at pages/API/location-change/location-change.uvue:165")
        uni_offLocationChange(null)
    }
    open var startLocationUpdate = ::gen_startLocationUpdate_fn
    open fun gen_startLocationUpdate_fn() {
        if (this.providerList.length == 0) {
            uni_showToast(ShowToastOptions(title = "未获取到provider，请确定基座中包含location功能", icon = "error"))
            console.log("未获取到provider，请确定基座中包含location功能", " at pages/API/location-change/location-change.uvue:175")
            return
        }
        uni_startLocationUpdate(StartLocationUpdateOptions(provider = this.providerList[this.currentSelectedProvider].id, type = this.types[this.currentSelectedType].value, success = fun(_){
            if (this.logAble) {
                this.log += "开启startLocationUpdate成功, provider= " + this.providerList[this.currentSelectedProvider].id + "\n\n"
            }
            console.log("持续定位启动成功", " at pages/API/location-change/location-change.uvue:187")
            this.startSuccess = true
        }
        , fail = fun(err){
            if (this.logAble) {
                this.log += "启动startLocationUpdate失败：erroCode=" + err.errCode + "\n\n"
            }
            console.log("持续定位启动失败", " at pages/API/location-change/location-change.uvue:195")
            this.startSuccess = false
            this.errCode = err.errCode
        }
        ))
    }
    open var startLocationUpdateBackground = ::gen_startLocationUpdateBackground_fn
    open fun gen_startLocationUpdateBackground_fn() {
        if (this.providerList.length == 0) {
            uni_showToast(ShowToastOptions(title = "未获取到provider，请确定基座中包含location功能", icon = "error"))
            console.log("未获取到provider，请确定基座中包含location功能", " at pages/API/location-change/location-change.uvue:209")
            return
        }
        uni_startLocationUpdateBackground(StartLocationUpdateBackgroundOptions(provider = this.providerList[this.currentSelectedProvider].id, type = this.types[this.currentSelectedType].value, success = fun(_){
            if (this.logAble) {
                this.log += "开启startLocationUpdateBackground成功, provider= " + this.providerList[this.currentSelectedProvider].id + "\n\n"
            }
            console.log("后台持续定位api启动成功", " at pages/API/location-change/location-change.uvue:220")
            this.startSuccess = true
        }
        , fail = fun(err){
            if (this.logAble) {
                this.log += "启动startLocationUpdateBackground失败：erroCode=" + err.errCode + "\n\n"
            }
            console.log("后台持续定位启动失败", " at pages/API/location-change/location-change.uvue:226")
            this.startSuccess = false
            this.errCode = err.errCode
        }
        ))
    }
    open var stopLocationUpdate = ::gen_stopLocationUpdate_fn
    open fun gen_stopLocationUpdate_fn() {
        uni_stopLocationUpdate(StopLocationUpdateOptions(success = fun(_){
            if (this.logAble) {
                this.log += "\u6210\u529F\u5173\u95EDstopLocationUpdate\u5B9A\u4F4D\n\n"
            }
            console.log("成功关闭stopLocationUpdate定位", " at pages/API/location-change/location-change.uvue:239")
            this.stopSuccess = true
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
                return utsMapOf("uni-list" to padStyleMapOf(utsMapOf("borderBottomWidth" to 0, "borderBottomStyle" to "none", "borderBottomColor" to "#000000", "backgroundColor" to "rgba(0,0,0,0)", "marginLeft" to 10, "marginRight" to 10, "marginTop" to 4, "marginBottom" to 4)), "uni-list-cell" to padStyleMapOf(utsMapOf("position" to "relative", "display" to "flex", "flexDirection" to "row", "justifyContent" to "space-between", "alignItems" to "center")), "btnstyle" to padStyleMapOf(utsMapOf("marginTop" to 4, "marginRight" to 4, "marginBottom" to 4, "marginLeft" to 4)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
