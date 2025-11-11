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
open class GenPagesComponentProgressProgress : BasePage {
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
        val _component_progress = resolveComponent("progress", true)
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        val _component_boolean_data = resolveEasyComponent("boolean-data", GenComponentsBooleanDataBooleanDataClass)
        val _component_input_data = resolveEasyComponent("input-data", GenComponentsInputDataInputDataClass)
        val _component_enum_data = resolveEasyComponent("enum-data", GenComponentsEnumDataEnumDataClass)
        return createElementVNode(Fragment, null, utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "main"), utsArrayOf(
                createVNode(_component_progress, utsMapOf("duration" to _ctx.duration_input, "percent" to _ctx.percent_input, "show-info" to _ctx.show_info_boolean, "stroke-width" to _ctx.stroke_width_input, "activeColor" to _ctx.activeColor_input, "backgroundColor" to _ctx.backgroundColor_input, "active" to _ctx.active_boolean, "active-mode" to _ctx.active_mode_enum[_ctx.active_mode_enum_current].name, "onTouchstart" to _ctx.progress_touchstart, "onTouchmove" to _ctx.progress_touchmove, "onTouchcancel" to _ctx.progress_touchcancel, "onTouchend" to _ctx.progress_touchend, "onClick" to _ctx.progress_tap, "style" to normalizeStyle(utsMapOf("width" to "80%"))), null, 8, utsArrayOf(
                    "duration",
                    "percent",
                    "show-info",
                    "stroke-width",
                    "activeColor",
                    "backgroundColor",
                    "active",
                    "active-mode",
                    "onTouchstart",
                    "onTouchmove",
                    "onTouchcancel",
                    "onTouchend",
                    "onClick",
                    "style"
                ))
            )),
            createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "content"), utsArrayOf(
                    createVNode(_component_page_head, utsMapOf("title" to "组件属性")),
                    createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "进度条从左往右的动画", "onChange" to _ctx.change_active_boolean), null, 8, utsArrayOf(
                        "onChange"
                    )),
                    createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "在进度条右侧显示百分比", "onChange" to _ctx.change_show_info_boolean), null, 8, utsArrayOf(
                        "onChange"
                    )),
                    createVNode(_component_input_data, utsMapOf("defaultValue" to "30", "title" to "进度增加1%所需毫秒数", "type" to "number", "onConfirm" to _ctx.confirm_duration_input), null, 8, utsArrayOf(
                        "onConfirm"
                    )),
                    createVNode(_component_input_data, utsMapOf("defaultValue" to "0", "title" to "百分比0~100", "type" to "number", "onConfirm" to _ctx.confirm_percent_input), null, 8, utsArrayOf(
                        "onConfirm"
                    )),
                    createVNode(_component_input_data, utsMapOf("defaultValue" to "6", "title" to "进度条线的宽度，单位px", "type" to "number", "onConfirm" to _ctx.confirm_stroke_width_input), null, 8, utsArrayOf(
                        "onConfirm"
                    )),
                    createVNode(_component_input_data, utsMapOf("defaultValue" to "#09BB07", "title" to "已选择的进度条的颜色", "type" to "text", "onConfirm" to _ctx.confirm_activeColor_input), null, 8, utsArrayOf(
                        "onConfirm"
                    )),
                    createVNode(_component_input_data, utsMapOf("defaultValue" to "#EBEBEB", "title" to "未选择的进度条的颜色", "type" to "text", "onConfirm" to _ctx.confirm_backgroundColor_input), null, 8, utsArrayOf(
                        "onConfirm"
                    )),
                    createVNode(_component_enum_data, utsMapOf("items" to _ctx.active_mode_enum, "title" to "backwards: 动画从头播；forwards：动画从上次结束点接着播", "onChange" to _ctx.radio_change_active_mode_enum), null, 8, utsArrayOf(
                        "items",
                        "onChange"
                    ))
                )),
                createElementVNode("view", null, utsArrayOf(
                    createVNode(_component_page_head, utsMapOf("title" to "默认及使用")),
                    createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "progress-box"), utsArrayOf(
                            createVNode(_component_progress, utsMapOf("percent" to _ctx.pgList[0], "active" to true, "border-radius" to _ctx.borderRadius, "show-info" to _ctx.showInfo, "font-size" to _ctx.fontSize, "stroke-width" to _ctx.strokeWidth, "background-color" to _ctx.backgroundColor, "class" to "progress p", "onActiveend" to _ctx.activeend), null, 8, utsArrayOf(
                                "percent",
                                "border-radius",
                                "show-info",
                                "font-size",
                                "stroke-width",
                                "background-color",
                                "onActiveend"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "progress-box"), utsArrayOf(
                            createVNode(_component_progress, utsMapOf("percent" to _ctx.pgList[1], "stroke-width" to 3, "class" to "progress p1"), null, 8, utsArrayOf(
                                "percent"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "progress-box"), utsArrayOf(
                            createVNode(_component_progress, utsMapOf("percent" to _ctx.pgList[2], "stroke-width" to 3, "class" to "progress p2"), null, 8, utsArrayOf(
                                "percent"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "progress-box"), utsArrayOf(
                            createVNode(_component_progress, utsMapOf("percent" to _ctx.pgList[3], "activeColor" to "#10AEFF", "stroke-width" to 3, "class" to "progress p3"), null, 8, utsArrayOf(
                                "percent"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "progress-control"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.setProgress, "class" to "button"), " 设置进度 ", 8, utsArrayOf(
                                "onClick"
                            )),
                            createElementVNode("button", utsMapOf("type" to "warn", "onClick" to _ctx.clearProgress, "class" to "button"), " 清除进度 ", 8, utsArrayOf(
                                "onClick"
                            ))
                        ))
                    ))
                ))
            ), 4)
        ), 64)
    }
    open var title: String by `$data`
    open var pgList: UTSArray<Number> by `$data`
    open var curPercent: Number by `$data`
    open var showInfo: Boolean by `$data`
    open var borderRadius: Number by `$data`
    open var fontSize: Number by `$data`
    open var strokeWidth: Number by `$data`
    open var backgroundColor: String by `$data`
    open var active_boolean: Boolean by `$data`
    open var show_info_boolean: Boolean by `$data`
    open var duration_input: Number by `$data`
    open var percent_input: Number by `$data`
    open var stroke_width_input: Number by `$data`
    open var activeColor_input: String by `$data`
    open var backgroundColor_input: String by `$data`
    open var active_mode_enum: UTSArray<ItemType> by `$data`
    open var active_mode_enum_current: Number by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "progress", "pgList" to utsArrayOf<Number>(0, 0, 0, 0), "curPercent" to 0, "showInfo" to true, "borderRadius" to 0, "fontSize" to 16, "strokeWidth" to 3, "backgroundColor" to "#EBEBEB", "active_boolean" to false, "show_info_boolean" to false, "duration_input" to 30, "percent_input" to 0, "stroke_width_input" to 6, "activeColor_input" to "#09BB07", "backgroundColor_input" to "#EBEBEB", "active_mode_enum" to utsArrayOf<ItemType>(ItemType(value = 0, name = "backwards"), ItemType(value = 1, name = "forwards")), "active_mode_enum_current" to 0)
    }
    open var getEventCallbackNum = ::gen_getEventCallbackNum_fn
    open fun gen_getEventCallbackNum_fn(): Number {
        return state.eventCallbackNum
    }
    open var setEventCallbackNum = ::gen_setEventCallbackNum_fn
    open fun gen_setEventCallbackNum_fn(num: Number) {
        uni.UNI740BA18.setEventCallbackNum(num)
    }
    open var setProgress = ::gen_setProgress_fn
    open fun gen_setProgress_fn() {
        this.pgList = utsArrayOf<Number>(20, 40, 60, 80)
    }
    open var clearProgress = ::gen_clearProgress_fn
    open fun gen_clearProgress_fn() {
        this.pgList = utsArrayOf<Number>(0, 0, 0, 0)
    }
    open var activeend = ::gen_activeend_fn
    open fun gen_activeend_fn(e: UniProgressActiveendEvent) {
        if ((e.target?.tagName ?: "").includes("PROGRESS")) {
            this.setEventCallbackNum(state.eventCallbackNum + 1)
        }
        if (e.type === "activeend") {
            this.setEventCallbackNum(state.eventCallbackNum + 2)
        }
        this.curPercent = e.detail.curPercent
    }
    open var progress_touchstart = ::gen_progress_touchstart_fn
    open fun gen_progress_touchstart_fn() {
        console.log("手指触摸动作开始", " at pages/component/progress/progress.uvue:54")
    }
    open var progress_touchmove = ::gen_progress_touchmove_fn
    open fun gen_progress_touchmove_fn() {
        console.log("手指触摸后移动", " at pages/component/progress/progress.uvue:55")
    }
    open var progress_touchcancel = ::gen_progress_touchcancel_fn
    open fun gen_progress_touchcancel_fn() {
        console.log("手指触摸动作被打断，如来电提醒，弹窗", " at pages/component/progress/progress.uvue:56")
    }
    open var progress_touchend = ::gen_progress_touchend_fn
    open fun gen_progress_touchend_fn() {
        console.log("手指触摸动作结束", " at pages/component/progress/progress.uvue:57")
    }
    open var progress_tap = ::gen_progress_tap_fn
    open fun gen_progress_tap_fn() {
        console.log("手指触摸后马上离开", " at pages/component/progress/progress.uvue:58")
    }
    open var change_active_boolean = ::gen_change_active_boolean_fn
    open fun gen_change_active_boolean_fn(checked: Boolean) {
        this.active_boolean = checked
    }
    open var change_show_info_boolean = ::gen_change_show_info_boolean_fn
    open fun gen_change_show_info_boolean_fn(checked: Boolean) {
        this.show_info_boolean = checked
    }
    open var confirm_duration_input = ::gen_confirm_duration_input_fn
    open fun gen_confirm_duration_input_fn(value: Number) {
        this.duration_input = value
    }
    open var confirm_percent_input = ::gen_confirm_percent_input_fn
    open fun gen_confirm_percent_input_fn(value: Number) {
        this.percent_input = value
    }
    open var confirm_stroke_width_input = ::gen_confirm_stroke_width_input_fn
    open fun gen_confirm_stroke_width_input_fn(value: Number) {
        this.stroke_width_input = value
    }
    open var confirm_activeColor_input = ::gen_confirm_activeColor_input_fn
    open fun gen_confirm_activeColor_input_fn(value: String) {
        this.activeColor_input = value
    }
    open var confirm_backgroundColor_input = ::gen_confirm_backgroundColor_input_fn
    open fun gen_confirm_backgroundColor_input_fn(value: String) {
        this.backgroundColor_input = value
    }
    open var radio_change_active_mode_enum = ::gen_radio_change_active_mode_enum_fn
    open fun gen_radio_change_active_mode_enum_fn(checked: Number) {
        this.active_mode_enum_current = checked
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
                return utsMapOf("main" to padStyleMapOf(utsMapOf("maxHeight" to 250, "paddingTop" to 5, "paddingRight" to 0, "paddingBottom" to 5, "paddingLeft" to 0, "borderBottomWidth" to 1, "borderBottomStyle" to "solid", "borderBottomColor" to "rgba(0,0,0,0.06)", "flexDirection" to "row", "justifyContent" to "center")), "progress-box" to padStyleMapOf(utsMapOf("height" to 25, "marginBottom" to 30)), "button" to padStyleMapOf(utsMapOf("marginTop" to 10)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
