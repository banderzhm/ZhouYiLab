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
open class GenPagesComponentViewView : BasePage {
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
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        val _component_boolean_data = resolveEasyComponent("boolean-data", GenComponentsBooleanDataBooleanDataClass)
        val _component_enum_data = resolveEasyComponent("enum-data", GenComponentsEnumDataEnumDataClass)
        return createElementVNode(Fragment, null, utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to "view")),
            createElementVNode("view", utsMapOf("id" to "view", "class" to "main", "hover-class" to if (_ctx.hover_class) {
                "is-parent-hover"
            } else {
                "none"
            }
            ), utsArrayOf(
                createElementVNode("view", utsMapOf("id" to "view-child1", "class" to normalizeClass(utsArrayOf(
                    "test-view",
                    if (_ctx.isDarkMode) {
                        "theme-dark"
                    } else {
                        "theme-light"
                    }
                )), "hover-class" to if (_ctx.hover_class) {
                    "is-hover"
                } else {
                    "none"
                }
                , "hover-stop-propagation" to _ctx.stop_propagation, "hover-start-time" to _ctx.start_time, "hover-stay-time" to _ctx.stay_time), null, 10, utsArrayOf(
                    "hover-class",
                    "hover-stop-propagation",
                    "hover-start-time",
                    "hover-stay-time"
                ))
            ), 8, utsArrayOf(
                "hover-class"
            )),
            createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "content"), utsArrayOf(
                    createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "是否指定按下去的样式类", "onChange" to _ctx.change_hover_class_boolean), null, 8, utsArrayOf(
                        "onChange"
                    )),
                    createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "是否阻止本节点的祖先节点出现点击态", "onChange" to _ctx.change_stop_propagation_boolean), null, 8, utsArrayOf(
                        "onChange"
                    )),
                    createVNode(_component_enum_data, utsMapOf("items" to _ctx.start_time_enum, "title" to "按住后多久出现点击态", "onChange" to _ctx.radio_change_start_time_enum), null, 8, utsArrayOf(
                        "items",
                        "onChange"
                    )),
                    createVNode(_component_enum_data, utsMapOf("items" to _ctx.stay_time_enum, "title" to "手指松开后点击态保留时间", "onChange" to _ctx.radio_change_stay_time_enum), null, 8, utsArrayOf(
                        "items",
                        "onChange"
                    ))
                ))
            ), 4)
        ), 64)
    }
    open var hover_class: Boolean by `$data`
    open var stop_propagation: Boolean by `$data`
    open var start_time: Number by `$data`
    open var stay_time: Number by `$data`
    open var start_time_enum: UTSArray<ItemType> by `$data`
    open var stay_time_enum: UTSArray<ItemType> by `$data`
    open var isDarkMode: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("hover_class" to false, "stop_propagation" to false, "start_time" to 50, "stay_time" to 400, "start_time_enum" to utsArrayOf<ItemType>(ItemType(value = 50, name = "50毫秒"), ItemType(value = 200, name = "200毫秒")), "stay_time_enum" to utsArrayOf<ItemType>(ItemType(value = 400, name = "400毫秒"), ItemType(value = 200, name = "200毫秒")), "isDarkMode" to computed<Boolean>(fun(): Boolean {
            return state.isDarkMode
        }
        ))
    }
    open var change_hover_class_boolean = ::gen_change_hover_class_boolean_fn
    open fun gen_change_hover_class_boolean_fn(checked: Boolean) {
        this.hover_class = checked
    }
    open var change_stop_propagation_boolean = ::gen_change_stop_propagation_boolean_fn
    open fun gen_change_stop_propagation_boolean_fn(checked: Boolean) {
        this.stop_propagation = checked
    }
    open var radio_change_start_time_enum = ::gen_radio_change_start_time_enum_fn
    open fun gen_radio_change_start_time_enum_fn(time: Number) {
        this.start_time = time
    }
    open var radio_change_stay_time_enum = ::gen_radio_change_stay_time_enum_fn
    open fun gen_radio_change_stay_time_enum_fn(time: Number) {
        this.stay_time = time
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
                return utsMapOf("main" to padStyleMapOf(utsMapOf("paddingTop" to 5, "paddingRight" to 0, "paddingBottom" to 5, "paddingLeft" to 0, "flexDirection" to "row", "justifyContent" to "center")), "test-view" to padStyleMapOf(utsMapOf("height" to 200, "width" to 200, "backgroundColor" to "var(--list-background-color,#ffffff)")), "is-hover" to padStyleMapOf(utsMapOf("backgroundColor" to "#179b16")), "is-parent-hover" to padStyleMapOf(utsMapOf("backgroundColor" to "#aa0000")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
