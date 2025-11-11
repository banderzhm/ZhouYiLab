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
import io.dcloud.uniapp.extapi.navigateBack as uni_navigateBack
open class GenPagesComponentCameraCameraScanCode : BasePage {
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
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_camera = resolveComponent("camera")
        return createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createVNode(_component_camera, utsMapOf("style" to normalizeStyle(utsMapOf("width" to "100%", "height" to "300px")), "resolution" to "high", "mode" to "scanCode", "onScancode" to _ctx.handleScanCode), null, 8, utsArrayOf(
                "style",
                "onScancode"
            )),
            createElementVNode("view", utsMapOf("class" to "camera-scan-code-back-wrap"), utsArrayOf(
                createElementVNode("button", utsMapOf("type" to "default", "onClick" to _ctx.navigateBack), "返回正常模式", 8, utsArrayOf(
                    "onClick"
                ))
            )),
            createElementVNode("view", utsMapOf("class" to "camera-scan-code-table"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "camera-scan-code-table-pair"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "camera-scan-code-table-pair-label"), utsArrayOf(
                        createElementVNode("text", null, "类型")
                    )),
                    createElementVNode("view", utsMapOf("class" to "camera-scan-code-table-pair-value"), utsArrayOf(
                        createElementVNode("text", null, toDisplayString(_ctx.result?.type ?: ""), 1)
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "camera-scan-code-table-pair camera-scan-code-table-top-line"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "camera-scan-code-table-pair-label"), utsArrayOf(
                        createElementVNode("text", null, "结果")
                    )),
                    createElementVNode("view", utsMapOf("class" to "camera-scan-code-table-pair-value"), utsArrayOf(
                        createElementVNode("text", null, toDisplayString(_ctx.result?.result ?: ""), 1)
                    ))
                ))
            ))
        ), 4)
    }
    open var result: CameraScanCodeResult? by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("result" to null as CameraScanCodeResult?)
    }
    open var navigateBack = ::gen_navigateBack_fn
    open fun gen_navigateBack_fn() {
        uni_navigateBack(null)
    }
    open var handleScanCode = ::gen_handleScanCode_fn
    open fun gen_handleScanCode_fn(ev: UniCameraScanCodeEvent) {
        val deatil = ev.detail
        this.result = CameraScanCodeResult(type = deatil.type, result = deatil.result)
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
                return utsMapOf("camera-scan-code-back-wrap" to padStyleMapOf(utsMapOf("display" to "flex", "justifyContent" to "center", "alignItems" to "center")), "camera-scan-code-table" to padStyleMapOf(utsMapOf("backgroundColor" to "#FFFFFF", "marginTop" to 20)), "camera-scan-code-table-pair" to padStyleMapOf(utsMapOf("height" to 50, "flexDirection" to "row", "justifyContent" to "space-between", "alignItems" to "center")), "camera-scan-code-table-pair-label" to padStyleMapOf(utsMapOf("flexGrow" to 1, "marginLeft" to 15)), "camera-scan-code-table-pair-value" to padStyleMapOf(utsMapOf("flexGrow" to 2)), "camera-scan-code-table-top-line" to padStyleMapOf(utsMapOf("borderTopWidth" to 1, "borderTopStyle" to "solid", "borderTopColor" to "#eeeeee")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
