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
import uts.sdk.modules.uniUsercapturescreen.OnUserCaptureScreenCallbackResult
import uts.sdk.modules.uniUsercapturescreen.SetUserCaptureScreenSuccess
import uts.sdk.modules.uniUsercapturescreen.SetUserCaptureScreenOptions as SetUserCaptureScreenOptions
import uts.sdk.modules.uniUsercapturescreen.offUserCaptureScreen as uni_offUserCaptureScreen
import uts.sdk.modules.uniUsercapturescreen.onUserCaptureScreen as uni_onUserCaptureScreen
import uts.sdk.modules.uniUsercapturescreen.setUserCaptureScreen as uni_setUserCaptureScreen
open class GenPagesAPICaptureScreenCaptureScreen : BasePage {
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
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesAPICaptureScreenCaptureScreen) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesAPICaptureScreenCaptureScreen
            val _cache = __ins.renderCache
            val title = "截屏监听"
            val allowCapture = ref(state.allowCapture)
            val captureStatus = ref("未监听")
            val captureCallback = ref<((res: OnUserCaptureScreenCallbackResult) -> Unit)?>(null)
            val toggleCaptureScreen = fun(checked: Boolean){
                uni_setUserCaptureScreen(SetUserCaptureScreenOptions(enable = checked, success = fun(res: SetUserCaptureScreenSuccess){
                    allowCapture.value = checked
                    setAllowCapture(checked)
                    console.log("设置截屏状态成功：", res, " at pages/API/capture-screen/capture-screen.uvue:33")
                }
                , fail = fun(err: IUniError){
                    console.log("设置截屏状态失败：", err, " at pages/API/capture-screen/capture-screen.uvue:36")
                }
                ))
            }
            val startCaptureListener = fun(){
                captureCallback.value = fun(res: OnUserCaptureScreenCallbackResult){
                    captureStatus.value = "检测到截屏"
                    console.log("检测到用户截屏", res, " at pages/API/capture-screen/capture-screen.uvue:44")
                }
                uni_onUserCaptureScreen(captureCallback.value)
                captureStatus.value = "正在监听"
                console.log("开始监听截屏", " at pages/API/capture-screen/capture-screen.uvue:48")
            }
            val stopCaptureListener = fun(){
                if (captureCallback.value != null) {
                    uni_offUserCaptureScreen(captureCallback.value)
                    captureStatus.value = "未监听"
                    console.log("停止监听截屏", " at pages/API/capture-screen/capture-screen.uvue:55")
                }
            }
            onUnmounted(fun(){
                stopCaptureListener()
            }
            )
            return fun(): Any? {
                val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
                val _component_switch = resolveComponent("switch")
                return createElementVNode("view", utsMapOf("class" to "uni-container"), utsArrayOf(
                    createVNode(_component_page_head, utsMapOf("title" to title)),
                    createElementVNode("view", utsMapOf("class" to "uni-common-mt"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-title"), "截屏状态：" + toDisplayString(unref(captureStatus)), 1),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex-direction" to "row"))), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "uni-title"), "是否允许截屏"),
                            createVNode(_component_switch, utsMapOf("checked" to unref(allowCapture), "onChange" to fun(e: UniSwitchChangeEvent){
                                return toggleCaptureScreen(e.detail.value)
                            }
                            ), null, 8, utsArrayOf(
                                "checked",
                                "onChange"
                            ))
                        ), 4),
                        createElementVNode("view", utsMapOf("class" to "uni-btn"), utsArrayOf(
                            createElementVNode("button", utsMapOf("onClick" to startCaptureListener, "type" to "primary", "class" to "uni-common-mt"), "开启截屏监听"),
                            createElementVNode("button", utsMapOf("onClick" to stopCaptureListener, "class" to "uni-common-mt"), "关闭截屏监听")
                        ))
                    ))
                ))
            }
        }
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
