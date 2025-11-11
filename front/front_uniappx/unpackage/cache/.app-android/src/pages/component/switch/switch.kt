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
open class GenPagesComponentSwitchSwitch : BasePage {
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
        val _component_switch = resolveComponent("switch", true)
        return createElementVNode("view", null, utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "uni-title"), "默认样式"),
                createElementVNode("view", utsMapOf("class" to "flex-row"), utsArrayOf(
                    createVNode(_component_switch, utsMapOf("class" to "switch-checked", "checked" to _ctx.checked, "onChange" to _ctx.switch1Change), null, 8, utsArrayOf(
                        "checked",
                        "onChange"
                    )),
                    createVNode(_component_switch, utsMapOf("onChange" to _ctx.switch2Change), null, 8, utsArrayOf(
                        "onChange"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-title"), "暗黑样式"),
                createElementVNode("view", utsMapOf("class" to "flex-row"), utsArrayOf(
                    createVNode(_component_switch, utsMapOf("id" to "darkChecked", "background-color" to "#1f1f1f", "activeBackgroundColor" to "#007aff", "foreColor" to "#f0f0f0", "activeForeColor" to "#ffffff", "checked" to _ctx.checked), null, 8, utsArrayOf(
                        "checked"
                    )),
                    createVNode(_component_switch, utsMapOf("id" to "dark", "background-color" to "#1f1f1f", "activeBackgroundColor" to "#007aff", "foreColor" to "#f0f0f0", "activeForeColor" to "#ffffff"))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-title"), "禁用样式"),
                createElementVNode("view", utsMapOf("class" to "flex-row"), utsArrayOf(
                    createVNode(_component_switch, utsMapOf("class" to "switch-checked", "checked" to _ctx.checked, "disabled" to true), null, 8, utsArrayOf(
                        "checked"
                    )),
                    createVNode(_component_switch, utsMapOf("disabled" to true))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-title"), "不同颜色和尺寸的switch"),
                createElementVNode("view", utsMapOf("class" to "flex-row"), utsArrayOf(
                    createVNode(_component_switch, utsMapOf("class" to "switch-color-checked", "color" to _ctx.color, "style" to normalizeStyle(utsMapOf("transform" to "scale(0.7)")), "checked" to true), null, 8, utsArrayOf(
                        "color",
                        "style"
                    )),
                    createVNode(_component_switch, utsMapOf("class" to "switch-color", "color" to _ctx.color, "style" to normalizeStyle(utsMapOf("transform" to "scale(0.7)"))), null, 8, utsArrayOf(
                        "color",
                        "style"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-title"), "推荐展示样式")
            )),
            createElementVNode("view", utsMapOf("class" to "uni-list"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "uni-list-cell uni-list-cell-padding"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), "开启中"),
                    createVNode(_component_switch, utsMapOf("checked" to true))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-list-cell uni-list-cell-padding"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), "关闭"),
                    createVNode(_component_switch)
                ))
            ))
        ))
    }
    open var title: String by `$data`
    open var checked: Boolean by `$data`
    open var color: String by `$data`
    open var clickCheckedValue: Boolean by `$data`
    open var testVerifyEvent: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "switch 开关", "checked" to true, "color" to "#FFCC33", "clickCheckedValue" to true, "testVerifyEvent" to false)
    }
    open var switch1Change = ::gen_switch1Change_fn
    open fun gen_switch1Change_fn(e: UniSwitchChangeEvent) {
        this.clickCheckedValue = e.detail.value
        console.log("switch1 发生 change 事件，携带值为", e.detail.value, " at pages/component/switch/switch.uvue:55")
        this.testVerifyEvent = (e.type == "change" && (e.target?.tagName ?: "") == "SWITCH")
    }
    open var switch2Change = ::gen_switch2Change_fn
    open fun gen_switch2Change_fn(e: UniSwitchChangeEvent) {
        console.log("switch2 发生 change 事件，携带值为", e.detail.value, " at pages/component/switch/switch.uvue:61")
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
                return utsMapOf("flex-row" to padStyleMapOf(utsMapOf("flexDirection" to "row")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
