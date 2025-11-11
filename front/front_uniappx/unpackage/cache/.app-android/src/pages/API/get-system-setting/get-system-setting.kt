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
import io.dcloud.uniapp.extapi.getSystemSetting as uni_getSystemSetting
open class GenPagesAPIGetSystemSettingGetSystemSetting : BasePage {
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
        onUnload(fun() {}, __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        return createElementVNode(Fragment, null, utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                "title"
            )),
            createElementVNode("view", utsMapOf("class" to "uni-common-mt"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "uni-list"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-pd"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-label", "style" to normalizeStyle(utsMapOf("width" to "180px"))), "蓝牙的系统开关", 4)
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), utsArrayOf(
                            createElementVNode("input", utsMapOf("type" to "text", "disabled" to true, "placeholder" to "未获取", "value" to _ctx.bluetoothEnabled), null, 8, utsArrayOf(
                                "value"
                            ))
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-pd"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-label", "style" to normalizeStyle(utsMapOf("width" to "180px"))), "地理位置的系统开关", 4)
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), utsArrayOf(
                            createElementVNode("input", utsMapOf("type" to "text", "disabled" to true, "placeholder" to "未获取", "value" to _ctx.locationEnabled), null, 8, utsArrayOf(
                                "value"
                            ))
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-pd"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-label", "style" to normalizeStyle(utsMapOf("width" to "180px"))), "Wi-Fi 的系统开关", 4)
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), utsArrayOf(
                            createElementVNode("input", utsMapOf("type" to "text", "disabled" to true, "placeholder" to "未获取", "value" to _ctx.wifiEnabled), null, 8, utsArrayOf(
                                "value"
                            ))
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-pd"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-label", "style" to normalizeStyle(utsMapOf("width" to "180px"))), "设备方向", 4)
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), utsArrayOf(
                            createElementVNode("input", utsMapOf("type" to "text", "disabled" to true, "placeholder" to "未获取", "value" to _ctx.deviceOrientation), null, 8, utsArrayOf(
                                "value"
                            ))
                        ))
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                        createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.getSystemSetting), "获取系统设置", 8, utsArrayOf(
                            "onClick"
                        ))
                    ))
                ))
            ))
        ), 64)
    }
    open var title: String by `$data`
    open var bluetoothEnabled: String by `$data`
    open var locationEnabled: String by `$data`
    open var wifiEnabled: String by `$data`
    open var deviceOrientation: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "getSystemSetting", "bluetoothEnabled" to "", "locationEnabled" to "", "wifiEnabled" to "", "deviceOrientation" to "")
    }
    open var getSystemSetting = ::gen_getSystemSetting_fn
    open fun gen_getSystemSetting_fn() {
        val res = uni_getSystemSetting()
        this.bluetoothEnabled = if ((res.bluetoothEnabled ?: false)) {
            "开启"
        } else {
            "关闭"
        }
        this.locationEnabled = if (res.locationEnabled) {
            "开启"
        } else {
            "关闭"
        }
        this.wifiEnabled = if ((res.wifiEnabled ?: false)) {
            "开启"
        } else {
            "关闭"
        }
        this.deviceOrientation = res.deviceOrientation
        if (res.bluetoothError != null) {
            this.bluetoothEnabled = "无蓝牙权限"
        }
        if (res.wifiError != null) {
            this.wifiEnabled = "无WiFi权限"
        }
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
                return utsMapOf("uni-pd" to padStyleMapOf(utsMapOf("paddingLeft" to 15)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
