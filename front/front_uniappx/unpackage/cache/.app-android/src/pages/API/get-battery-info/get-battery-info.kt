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
import uts.sdk.modules.uniGetbatteryinfo.GetBatteryInfoOptions as GetBatteryInfoOptions
import uts.sdk.modules.uniGetbatteryinfo.getBatteryInfo as uni_getBatteryInfo
import io.dcloud.uniapp.extapi.showModal as uni_showModal
open class GenPagesAPIGetBatteryInfoGetBatteryInfo : BasePage {
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
            try {
                uni_getBatteryInfo(GetBatteryInfoOptions(success = fun(res){
                    this.level = res.level
                    this.isCharging = res.isCharging
                }
                ))
            }
             catch (e: Throwable) {
                console.error((e as UTSError).message, " at pages/API/get-battery-info/get-battery-info.uvue:25")
                uni_showModal(ShowModalOptions(content = (e as UTSError).message, showCancel = false))
            }
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("view", null, utsArrayOf(
            createElementVNode("text", null, "当前电量：" + toDisplayString(_ctx.level) + "%", 1),
            createElementVNode("text", null, "是否充电中：" + toDisplayString(_ctx.isCharging), 1)
        ))
    }
    open var level: Number by `$data`
    open var isCharging: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("level" to 0, "isCharging" to false)
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
