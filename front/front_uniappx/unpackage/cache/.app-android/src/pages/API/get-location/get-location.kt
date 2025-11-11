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
import io.dcloud.uniapp.extapi.getLocation as uni_getLocation
import io.dcloud.uniapp.extapi.getProviderSync as uni_getProviderSync
import io.dcloud.uniapp.extapi.hideLoading as uni_hideLoading
import io.dcloud.uniapp.extapi.showLoading as uni_showLoading
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesAPIGetLocationGetLocation : BasePage {
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
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        val _component_radio = resolveComponent("radio")
        val _component_radio_group = resolveComponent("radio-group")
        val _component_switch = resolveComponent("switch")
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                "title"
            )),
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("padding" to "4px"))), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "hello-text"), " 定位功能默认调用操作系统定位API实现。也支持三方SDK定位\n 部分老款Android手机因gms问题可能导致无法使用系统定位。\n Web、Android、iOS平台，gcj国标、逆地理信息等功能需调用腾讯定位。")
            ), 4),
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), "定位服务商provider(如系统定位，腾讯定位等)"),
                createElementVNode("view", utsMapOf("class" to "uni-list", "style" to normalizeStyle(utsMapOf("margin-bottom" to "20px"))), utsArrayOf(
                    createVNode(_component_radio_group, utsMapOf("onChange" to _ctx.radioChangePV), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.providerList, fun(item, index, __index, _cached): Any {
                                return createVNode(_component_radio, utsMapOf("class" to normalizeClass(utsArrayOf(
                                    "uni-list-cell uni-list-cell-pd",
                                    if (index < _ctx.providerList.length - 1) {
                                        "uni-list-cell-line"
                                    } else {
                                        ""
                                    }
                                )), "key" to item.id, "value" to item.id, "checked" to (index === _ctx.currentProvider)), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                    return utsArrayOf(
                                        toDisplayString(item.name)
                                    )
                                }
                                ), "_" to 2), 1032, utsArrayOf(
                                    "class",
                                    "value",
                                    "checked"
                                ))
                            }
                            ), 128)
                        )
                    }
                    ), "_" to 1), 8, utsArrayOf(
                        "onChange"
                    ))
                ), 4),
                createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), "定位类型"),
                createElementVNode("view", utsMapOf("class" to "uni-list"), utsArrayOf(
                    createVNode(_component_radio_group, utsMapOf("onChange" to _ctx.radioChange), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.items, fun(item, index, __index, _cached): Any {
                                return createVNode(_component_radio, utsMapOf("class" to normalizeClass(utsArrayOf(
                                    "uni-list-cell uni-list-cell-pd",
                                    if (index < _ctx.items.length - 1) {
                                        "uni-list-cell-line"
                                    } else {
                                        ""
                                    }
                                )), "key" to item.value, "value" to item.value, "checked" to (index === _ctx.current)), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                    return utsArrayOf(
                                        toDisplayString(item.name)
                                    )
                                }
                                ), "_" to 2), 1032, utsArrayOf(
                                    "class",
                                    "value",
                                    "checked"
                                ))
                            }
                            ), 128)
                        )
                    }
                    ), "_" to 1), 8, utsArrayOf(
                        "onChange"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-list-cell uni-list-cell-pd", "style" to normalizeStyle(utsMapOf("margin-top" to "20px"))), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), "高度信息"),
                    createVNode(_component_switch, utsMapOf("checked" to _ctx.altitudeSelect, "onChange" to _ctx.altitudeChange), null, 8, utsArrayOf(
                        "checked",
                        "onChange"
                    ))
                ), 4),
                createElementVNode("view", utsMapOf("class" to "uni-list-cell uni-list-cell-pd"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), "开启高精度定位"),
                    createVNode(_component_switch, utsMapOf("checked" to _ctx.isHighAccuracySelect, "onChange" to _ctx.highAccuracySelectChange), null, 8, utsArrayOf(
                        "checked",
                        "onChange"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-list-cell uni-list-cell-pd"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), "是否解析地址信息"),
                    createVNode(_component_switch, utsMapOf("checked" to _ctx.geocodeSelect, "onChange" to _ctx.geocodeChange), null, 8, utsArrayOf(
                        "checked",
                        "onChange"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "get-location-result"), toDisplayString(_ctx.exeRet), 1),
                createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                    createElementVNode("button", utsMapOf("class" to "uni-btn", "type" to "default", "onClick" to _ctx.getLocationTap), " 获取定位 ", 8, utsArrayOf(
                        "onClick"
                    ))
                ))
            ))
        ), 4)
    }
    open var title: String by `$data`
    open var altitudeSelect: Boolean by `$data`
    open var isHighAccuracySelect: Boolean by `$data`
    open var geocodeSelect: Boolean by `$data`
    open var exeRet: String by `$data`
    open var items: UTSArray<ItemType6> by `$data`
    open var providerList: UTSArray<LocationItem> by `$data`
    open var current: Number by `$data`
    open var currentProvider: Number by `$data`
    open var jest_provider: String by `$data`
    open var jest_type: GetLocationType by `$data`
    open var jest_isAltitude: Boolean by `$data`
    open var jest_isGeocode: Boolean by `$data`
    open var jest_isHighAccuracy: Boolean by `$data`
    open var jest_altitude: Number by `$data`
    open var jest_longitude: Number by `$data`
    open var jest_latitude: Number by `$data`
    open var jest_address: String by `$data`
    open var jest_errCode: Number by `$data`
    open var jest_complete: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "get-location", "altitudeSelect" to false, "isHighAccuracySelect" to false, "geocodeSelect" to false, "exeRet" to "", "items" to utsArrayOf<ItemType6>(ItemType6(value = "wgs84", name = "wgs84"), ItemType6(value = "gcj02", name = "gcj02")), "providerList" to utsArrayOf<LocationItem>(), "current" to 0, "currentProvider" to 0, "jest_provider" to "", "jest_type" to "wgs84" as GetLocationType, "jest_isAltitude" to false, "jest_isGeocode" to false, "jest_isHighAccuracy" to false, "jest_altitude" to -1000, "jest_longitude" to 200, "jest_latitude" to 100, "jest_address" to "", "jest_errCode" to 0, "jest_complete" to false)
    }
    open var getProvider = ::gen_getProvider_fn
    open fun gen_getProvider_fn() {
        var provider = uni_getProviderSync(GetProviderSyncOptions(service = "location"))
        console.log(provider, " at pages/API/get-location/get-location.uvue:110")
        provider.providerObjects.forEach(fun(value: UniProvider){
            var currentProvider = value
            this.providerList.push(LocationItem(name = currentProvider.description, id = currentProvider.id, provider = currentProvider))
        }
        )
        this.providerList.forEach(fun(value, index){
            if (value.id == "system") {
                this.currentProvider = index
            }
        }
        )
    }
    open var altitudeChange = ::gen_altitudeChange_fn
    open fun gen_altitudeChange_fn(e: UniSwitchChangeEvent) {
        this.altitudeSelect = e.detail.value
    }
    open var geocodeChange = ::gen_geocodeChange_fn
    open fun gen_geocodeChange_fn(e: UniSwitchChangeEvent) {
        this.geocodeSelect = e.detail.value
    }
    open var highAccuracySelectChange = ::gen_highAccuracySelectChange_fn
    open fun gen_highAccuracySelectChange_fn(e: UniSwitchChangeEvent) {
        this.isHighAccuracySelect = e.detail.value
    }
    open var radioChange = ::gen_radioChange_fn
    open fun gen_radioChange_fn(e: UniRadioGroupChangeEvent) {
        run {
            var i: Number = 0
            while(i < this.items.length){
                if (this.items[i].value === e.detail.value) {
                    this.current = i
                    break
                }
                i++
            }
        }
    }
    open var radioChangePV = ::gen_radioChangePV_fn
    open fun gen_radioChangePV_fn(e: UniRadioGroupChangeEvent) {
        run {
            var i: Number = 0
            while(i < this.providerList.length){
                if (this.providerList[i].id === e.detail.value) {
                    this.currentProvider = i
                    break
                }
                i++
            }
        }
        if (e.detail.value == "system") {
            this.current = 0
        } else if (e.detail.value == "tencent") {
            this.current = 1
        }
    }
    open var getLocationTap = ::gen_getLocationTap_fn
    open fun gen_getLocationTap_fn() {
        if (this.providerList.length == 0) {
            uni_showToast(ShowToastOptions(title = "未获取到provider，请确定基座中包含location功能", icon = "error"))
            console.log("未获取到provider，请确定基座中包含location功能", " at pages/API/get-location/get-location.uvue:169")
            return
        }
        uni_showLoading(ShowLoadingOptions(title = "定位中"))
        uni_getLocation((GetLocationOptions(provider = this.providerList[this.currentProvider].id, type = this.items[this.current].value, altitude = this.altitudeSelect, isHighAccuracy = this.isHighAccuracySelect, geocode = this.geocodeSelect, success = fun(res: Any){
            uni_hideLoading()
            this.exeRet = JSON.stringify(res)
        }
        , fail = fun(res: Any){
            uni_hideLoading()
            this.exeRet = JSON.stringify(res)
        }
        , complete = fun(res: Any){
            uni_hideLoading()
            this.exeRet = JSON.stringify(res)
        }
        )))
    }
    open var jestGetLocation = ::gen_jestGetLocation_fn
    open fun gen_jestGetLocation_fn() {
        this.jest_complete = false
        this.jest_errCode = 0
        uni_getLocation((GetLocationOptions(provider = this.jest_provider, type = this.jest_type, altitude = this.jest_isAltitude, isHighAccuracy = this.jest_isHighAccuracy, geocode = this.jest_isGeocode, success = fun(res){
            if (res.address != null) {
                this.jest_address = res.address!!
            }
            this.jest_longitude = res.longitude
            this.jest_latitude = res.latitude
            this.jest_altitude = res.altitude
            this.jest_complete = true
        }
        , fail = fun(err){
            this.jest_errCode = err.errCode
            this.jest_complete = true
        }
        )))
    }
    companion object {
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
