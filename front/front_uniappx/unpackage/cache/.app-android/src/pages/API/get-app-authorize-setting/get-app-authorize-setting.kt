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
import io.dcloud.uniapp.extapi.getAppAuthorizeSetting as uni_getAppAuthorizeSetting
open class GenPagesAPIGetAppAuthorizeSettingGetAppAuthorizeSetting : BasePage {
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
                            createElementVNode("view", utsMapOf("class" to "uni-label", "style" to normalizeStyle(utsMapOf("width" to "180px"))), "是否授权使用相册", 4)
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), utsArrayOf(
                            createElementVNode("input", utsMapOf("type" to "text", "disabled" to true, "placeholder" to "未获取", "value" to _ctx.albumAuthorized), null, 8, utsArrayOf(
                                "value"
                            ))
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-pd"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-label", "style" to normalizeStyle(utsMapOf("width" to "180px"))), "是否授权使用蓝牙", 4)
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), utsArrayOf(
                            createElementVNode("input", utsMapOf("type" to "text", "disabled" to true, "placeholder" to "未获取", "value" to _ctx.bluetoothAuthorized), null, 8, utsArrayOf(
                                "value"
                            ))
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-pd"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-label", "style" to normalizeStyle(utsMapOf("width" to "180px"))), "是否授权使用摄像头", 4)
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), utsArrayOf(
                            createElementVNode("input", utsMapOf("type" to "text", "disabled" to true, "placeholder" to "未获取", "value" to _ctx.cameraAuthorized), null, 8, utsArrayOf(
                                "value"
                            ))
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-pd"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-label", "style" to normalizeStyle(utsMapOf("width" to "180px"))), "是否授权使用定位", 4)
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), utsArrayOf(
                            createElementVNode("input", utsMapOf("type" to "text", "disabled" to true, "placeholder" to "未获取", "value" to _ctx.locationAuthorized), null, 8, utsArrayOf(
                                "value"
                            ))
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-pd"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-label", "style" to normalizeStyle(utsMapOf("width" to "180px"))), "定位准确度", 4)
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), utsArrayOf(
                            createElementVNode("input", utsMapOf("type" to "text", "disabled" to true, "placeholder" to "未获取", "value" to _ctx.locationAccuracy), null, 8, utsArrayOf(
                                "value"
                            ))
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-pd"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-label", "style" to normalizeStyle(utsMapOf("width" to "180px"))), "是否授权使用麦克风", 4)
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), utsArrayOf(
                            createElementVNode("input", utsMapOf("type" to "text", "disabled" to true, "placeholder" to "未获取", "value" to _ctx.microphoneAuthorized), null, 8, utsArrayOf(
                                "value"
                            ))
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-pd"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-label", "style" to normalizeStyle(utsMapOf("width" to "180px"))), "是否授权通知", 4)
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), utsArrayOf(
                            createElementVNode("input", utsMapOf("type" to "text", "disabled" to true, "placeholder" to "未获取", "value" to _ctx.notificationAuthorized), null, 8, utsArrayOf(
                                "value"
                            ))
                        ))
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                        createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.getAppAuthorizeSetting), "获取App授权设置", 8, utsArrayOf(
                            "onClick"
                        ))
                    ))
                ))
            ))
        ), 64)
    }
    open var title: String by `$data`
    open var cameraAuthorized: String by `$data`
    open var albumAuthorized: String by `$data`
    open var locationAuthorized: String by `$data`
    open var locationAccuracy: String by `$data`
    open var microphoneAuthorized: String by `$data`
    open var bluetoothAuthorized: String by `$data`
    open var notificationAuthorized: String by `$data`
    open var notificationAlertAuthorized: String by `$data`
    open var notificationBadgeAuthorized: String by `$data`
    open var notificationSoundAuthorized: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "getAppAuthorizeSetting", "cameraAuthorized" to "", "albumAuthorized" to "", "locationAuthorized" to "", "locationAccuracy" to "", "microphoneAuthorized" to "", "bluetoothAuthorized" to "", "notificationAuthorized" to "", "notificationAlertAuthorized" to "", "notificationBadgeAuthorized" to "", "notificationSoundAuthorized" to "")
    }
    open var getAppAuthorizeSetting = ::gen_getAppAuthorizeSetting_fn
    open fun gen_getAppAuthorizeSetting_fn() {
        val res = uni_getAppAuthorizeSetting()
        this.albumAuthorized = res.albumAuthorized
        this.bluetoothAuthorized = res.bluetoothAuthorized
        this.cameraAuthorized = res.cameraAuthorized
        this.locationAuthorized = res.locationAuthorized
        this.locationAccuracy = res.locationAccuracy ?: "unsupported"
        this.microphoneAuthorized = res.microphoneAuthorized
        this.notificationAuthorized = res.notificationAuthorized
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
